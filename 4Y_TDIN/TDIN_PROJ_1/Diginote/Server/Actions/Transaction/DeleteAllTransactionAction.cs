using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Common.Client;
using Common.Data;
using MongoDB.Driver.Builders;
using Server.Database;
using Server.Database.Config;
using Model = Server.Database.Model;
namespace Server.Actions.Transaction
{
    class DeleteAllTransactionAction : TransactionAction
    {
        public void Run(Session session, Dictionary<String, Model.User> sessions, Dictionary<String, IUser> subsUsers)
        {
            var user = sessions[session.SessionKey];
            var query = Query.EQ("OwnerRef.$id", user.Id);
            var purchaseColl = Collection<Model.TransactionPurchase>.GetCollection();
            var sellColl = Collection<Model.TransactionSale>.GetCollection();
            
            purchaseColl.Remove(query);
            sellColl.Remove(query);
            
            subsUsers[user.Username].RefreshSession();
        }
    }
}
