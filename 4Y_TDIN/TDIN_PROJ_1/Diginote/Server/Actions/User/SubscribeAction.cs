using System;
using System.Collections.Generic;
using Common;
using Common.Client;
using Common.Data;
using Model = Server.Database.Model;

namespace Server.Actions.User
{
    class SubscribeAction : Action.Action
    {
        public Session Run(Session session, IUser client,Dictionary<String,Model.User> sessions , Dictionary<String,IUser> subsUsers)
        {
            var user = sessions[session.SessionKey];
            if (user == null)
            {
                return null;
            }
            subsUsers.Add(session.Username, client);
            return session;
        }
    }
}
