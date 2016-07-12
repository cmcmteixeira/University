using System;
using System.Collections.Generic;
using System.Linq;
using Common.Client;
using Common.Data;
using MongoDB.Driver;
using MongoDB.Driver.Builders;
using Server.Database;
using Model = Server.Database.Model;
namespace Server.Actions.Transaction
{
    class SellAction : TransactionAction
    {
        public TransactionSale Run(Session session, int amount, Dictionary<String, Model.User> sessions, Dictionary<String, IUser> subsUsers)
        {
            var user         = sessions[session.SessionKey];
            var userCallable = subsUsers[user.Username];
            var wallet       = Collection<Model.Wallet>.FollowReference(user.Wallet);

            var sale        = new Model.TransactionSale(amount, user);
            var sellColl    = Collection<Model.TransactionSale>.GetCollection();
            var purchColl   = Collection<Model.TransactionPurchase>.GetCollection();
            var purchItems  = purchColl.Find(Query.GT("DiginoteAmount",0)).SetSortOrder("Date");
            var buyers      = new List<Tuple<MongoDBRef, Model.History>>();

            StartTransaction();
            foreach (var purchase in purchItems.TakeWhile(purchase => sale.DiginoteAmount > 0))
            {
                var history = sale.Sell(purchase);
                history.Buyer = user.Username;
                history.Money = history.DiginoteAmount * Server.Quote.Value;
                if (purchase.DiginoteAmount > 0)
                {
                    purchColl.Save(purchase);
                }
                else
                {
                    purchColl.Remove(Query.EQ("_id", purchase.Id));
                }

                if (purchase.OwnerRef.Id == user.Id)
                {
                    history.Seller = user.Username;
                    history.Money = 0;
                    history.Type = History.Sale;
                    Collection<Model.History>.GetCollection().Save(history);
                    continue;
                }
                buyers.Add(new Tuple<MongoDBRef, Model.History>(purchase.OwnerRef, history));
            }

            UpdateNotifyPriceChange(buyers, subsUsers, wallet);

            if (sale.DiginoteAmount > 0)
            {
                sellColl.Insert(sale);
                userCallable.SetNewPrice(Server.Quote.Value);
            }
            EndTransaction();
            userCallable.RefreshSession();
            return (TransactionSale)sale;
        }
    }
}
