

using System;
using System.Collections.Generic;
using Common.Data;
using MongoDB.Driver.Builders;
using Server.Database;

namespace Server.Actions.User
{
    class RefreshSessionAction
    {
        public Session Run(Session session, Dictionary<String, Database.Model.User> sessions)
        {
            var userCollection = Collection<Database.Model.User>.GetCollection();
            var user = userCollection.FindOne(Query.EQ("Username", session.Username));
            var remoteUser = (Session)user;
            remoteUser.SessionKey = session.SessionKey;
            sessions.Remove(session.SessionKey);
            sessions.Add(remoteUser.SessionKey, user);
            
            return remoteUser;
        }
            
    }
}
