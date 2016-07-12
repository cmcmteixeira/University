using System;
using Server.Database.Model;

namespace Server.Database.Config
{

    public class DatabaseConfig
    {
        public String Host = "127.0.0.1";
        public String Port = "27017";
        public String Dbname = "TDIN";
        public Model[] Models;

        public DatabaseConfig(){
            Models = new Model[6];
            Models[0] = new Model(typeof(User),"User");
            Models[1] = new Model(typeof(TransactionPurchase),"TransactionPurchase");
            Models[2] = new Model(typeof(TransactionSale), "TransactionSale");
            Models[3] = new Model(typeof(Quote), "Quote");
            Models[4] = new Model(typeof(Wallet), "Wallet");
            Models[5] = new Model(typeof(History), "History");
        }

    }

    public class Model {
        public Model(Type type, String collection)
        {
            Collection = collection;
            Type = type;
        }
        public Model() { }
        public Type Type;
        public String Collection;
    }
}
