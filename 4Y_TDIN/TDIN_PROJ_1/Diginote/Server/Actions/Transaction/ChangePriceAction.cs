using System;
using System.Collections.Generic;
using Common.Client;
using Common.Data;
using MongoDB.Driver.Builders;
using Server.Database;
using Model = Server.Database.Model;

namespace Server.Actions.Transaction
{
    class ChangePriceAction : TransactionAction
    {
        public bool Run(Session session, double newPrice, Dictionary<String, Model.User> sessions, Dictionary<String, IUser> subsUsers)
        {
            var user = sessions[session.SessionKey];
            var quoteCollection = Collection<Model.Quote>.GetCollection();
            bool valid;
            if (newPrice == Server.Quote.Value)
            {
                return true;
            }
            if (newPrice < Server.Quote.Value)
            {
                var collection = Collection<Model.TransactionSale>.GetCollection();
                var transaction = collection.FindOne(Query.EQ("OwnerRef.$id", user.Id));
                valid = (transaction != null);
            }
            else
            {
                var collection = Collection<Model.TransactionPurchase>.GetCollection();
                var transaction = collection.FindOne(Query.EQ("OwnerRef.$id", user.Id));
                valid = (transaction != null);
            }
            if (!valid)
            {
                return false;
            }
            StartTransaction();
            Server.Quote.Value = newPrice;
            Blocked = true;
            Thread.Start();
            EndTransaction();

            quoteCollection.Save(Server.Quote);
            foreach (var subUser in subsUsers)
            {
                try
                {
                    if(subUser.Key != user.Username)
                    subUser.Value.PriceChanged(newPrice);
                }
                catch (Exception)
                {
                    // ignored
                }
            }
            
            return true;
        }
    }
}
