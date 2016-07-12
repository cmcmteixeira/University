using System;
using System.Linq;

using System.Collections.Generic;
using System.Runtime.Remoting;
using Common.Client;
using MongoDB.Driver.Builders;
using Server.Database;
using Server.Database.Model;
using Log;

namespace Server
{
    class Server
    {
        public static Dictionary<String,IUser> SubscribeUsers = new Dictionary<String,IUser>();
        public static Dictionary<String, User> Session = new Dictionary<String, User>();
        public static Quote Quote;
        public static void Main()
        {
            var quoteColl = Collection<Quote>.GetCollection();
            try
            {
                Quote = quoteColl
                    .FindAll()
                    .SetSortOrder(SortBy.Descending("createdAt"))
                    .SetLimit(1)
                    .DefaultIfEmpty(new Quote())
                    .FirstOrDefault();
            }
            catch (Exception)
            {
                Logger.Write("[ERROR]","Server","An instance of mongodb was not found");
            }

            
            RemotingConfiguration.Configure("Server.exe.config", false);
            Logger.Write("[START]", "SERVER", "The server has started");
            Console.ReadLine();
        }
    }


}