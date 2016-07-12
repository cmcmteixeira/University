using System;
using System.Collections.Generic;
using Common;
using Common.Data;
using Model = Server.Database.Model;

namespace Server.Actions.User
{
    class LogoutAction : Action.Action
    {
        public void Run(Session user, Dictionary<String, Model.User> sessions)
        {
            sessions.Remove(user.SessionKey);
        }
    }
}
