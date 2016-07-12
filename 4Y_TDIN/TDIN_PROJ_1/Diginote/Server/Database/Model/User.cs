using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Common.Data;
using MongoDB.Driver;
using MongoDB.Driver.Builders;

namespace Server.Database.Model
{
    class User : GenericModel
    {

        public String Username;
        public String Nickname;
        public String Password;

        public MongoDBRef Wallet;



        public User(Session user) {
            Username = user.Username;
            Nickname = user.Nickname;
            Password = user.Password;
        }


        public static explicit operator Session(User user)
        {
            if (user == null)
            {
                return null;
            }
            var session = new Session(user.Username, user.Nickname, user.Password);
            var wall =  Collection<Wallet>.FollowReference(user.Wallet);
            session.history =  Collection<History>.GetCollection().Find(Query.Or(Query.EQ("Buyer",user.Username),Query.EQ("Seller",user.Username))).Select(p => (Common.Data.History)p).ToArray();
            session.Money = wall.Money;
            session.DigiAmount = wall.Diginotes.Length;
            session.purchases = Collection<TransactionPurchase>.GetCollection().Find(Query.EQ("OwnerRef.$id", user.Id)).Select(p => (Common.Data.TransactionPurchase) p).ToArray();
            session.sales = Collection<TransactionSale>.GetCollection().Find(Query.EQ("OwnerRef.$id", user.Id)).Select(s => (Common.Data.TransactionSale) s).ToArray();
            
            session.CurrentQuote = Server.Quote.Value;
            return session;
        } 

    }
}
