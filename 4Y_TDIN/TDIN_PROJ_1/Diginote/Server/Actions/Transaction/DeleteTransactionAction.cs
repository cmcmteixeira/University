
using System;
using System.Collections.Generic;
using Common.Client;
using Common.Data;
using MongoDB.Driver.Builders;
using Server.Database;
using Model = Server.Database.Model;

namespace Server.Actions.Transaction
{
    class DeleteTransactionAction : TransactionAction
    {
        public void Run(Session session,Common.Data.Transaction transaction, Dictionary<String, Model.User> sessions,Dictionary<String,IUser> subsUsers )
        {
            StartTransaction();

            var user = sessions[session.SessionKey];
            var query = Query.And(Query.EQ("Date", transaction.Date), Query.EQ("OwnerRef.$id", user.Id));
            if (transaction.GetType() == typeof (TransactionPurchase))
            {
                var purchaseColl = Collection<Model.TransactionPurchase>.GetCollection();
                purchaseColl.Remove(query);
            }
            else if (transaction.GetType() == typeof (TransactionSale))
            {
                var sellColl = Collection<Model.TransactionSale>.GetCollection();
                sellColl.Remove(query);
            }
            EndTransaction();
            subsUsers[session.Username].RefreshSession();

        }

    }
}
