using System;
using System.Collections.Generic;
using Common.Data;
using MongoDB.Driver.Builders;
using Server.Database;

namespace Server.Actions.User
{
    class LoginAction : Action.Action
    {
        public Session Run(String name, String password, Dictionary<String,Database.Model.User> session)
        {
            var userCollection = Collection<Database.Model.User>.GetCollection();
            var query          = Query.Or(Query.EQ("Username", name), Query.EQ("Nickname", name));
            var user           = userCollection.FindOne(query);
            if (user == null)
            {
                return null;
            }
            var remoteUser = (Session)user;
            remoteUser.SessionKey = user.Username+ DateTime.Now.ToString();
            
            session.Add(remoteUser.SessionKey,user);
            return remoteUser;
        }
    }
}
