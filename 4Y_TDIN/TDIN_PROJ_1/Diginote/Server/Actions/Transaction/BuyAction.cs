using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using Common.Client;
using Common.Data;
using Log;
using MongoDB.Driver;
using MongoDB.Driver.Builders;
using Server.Database;
using TransactionSale = Server.Database.Model.TransactionSale;
using Wallet = Server.Database.Model.Wallet;
using Model = Server.Database.Model;

namespace Server.Actions.Transaction
{
    class BuyAction : TransactionAction
    {
        public TransactionPurchase Run(Session session, int amount, Dictionary<String, Database.Model.User> sessions,Dictionary<String,IUser> subsUsers)
        {
            var user        = sessions[session.SessionKey];
            var userCallable= subsUsers[user.Username];
            var wallet      = Collection<Wallet>.FollowReference(user.Wallet);
            var sellers     = new List<Tuple<MongoDBRef, Model.History>>();

            if (wallet.Money < amount * Server.Quote.Value) return null;

            StartTransaction();


            var purchase    = new Model.TransactionPurchase(amount, user);
            var purchColl   = Collection<Model.TransactionPurchase>.GetCollection();
            var sellColl    = Collection<TransactionSale>.GetCollection();
            var sellItems   = sellColl.Find(Query.GT("DiginoteAmount",0)).SetSortOrder("Date");

            foreach (var sell in sellItems.TakeWhile(sell => purchase.DiginoteAmount > 0))
            {
                var history = purchase.Buy(sell);
                history.Buyer = user.Username;
                history.Money = history.DiginoteAmount*Server.Quote.Value;
                if (sell.DiginoteAmount > 0)
                {
                    sellColl.Save(sell);
                }
                else
                {
                    sellColl.Remove(Query.EQ("_id", sell.Id));
                }
                if (sell.OwnerRef.Id == user.Id)
                {
                    history.Seller = user.Username;
                    history.Money = 0;
                    history.Type = History.Purchase;
                    Collection<Model.History>.GetCollection().Save(history);
                    continue;
                }
                sellers.Add(new Tuple<MongoDBRef, Model.History>(sell.OwnerRef, history));
            }
            Collection<Wallet>.GetCollection().Save(wallet);
            UpdateNotifyPriceChange(sellers, subsUsers, wallet);

            if (purchase.DiginoteAmount > 0)
            {
                purchColl.Insert(purchase);
                userCallable.SetNewPrice(Server.Quote.Value);
            }
            EndTransaction();
            userCallable.RefreshSession();
            return (TransactionPurchase)purchase;
        }
    }
}
