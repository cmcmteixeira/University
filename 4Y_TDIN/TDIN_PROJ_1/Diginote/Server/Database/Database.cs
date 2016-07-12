using System;
using System.Collections.Generic;

using MongoDB.Driver;
using Server.Database.Config;

namespace Server.Database
{
    internal class Database
    {
       internal static MongoClient Client;
       internal static MongoDatabase Db;
       internal static String Dbname;
       public static Dictionary<Type, String> Collections;
       
        public static MongoDatabase GetDatabase(){
            if (null != Client) return Db;
            
            var config = new DatabaseConfig();
            Collections = new Dictionary<Type,string>();
            Client = new MongoClient("mongodb://"+config.Host+":"+config.Port);
            Db = Client.GetServer().GetDatabase(config.Dbname);

            foreach(var conf in config.Models){
                Collections.Add(conf.Type, conf.Collection);
            }
            return Db;     
       }
    }

}
