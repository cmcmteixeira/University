using System;
using Common.Client;
using Common.Controllers;
using Common.Data;
using Log;
using Server.Actions.User;

namespace Server
{   
    class UserController : MarshalByRefObject, IUserManager
    {
        public Session Register(String name, String nickname, String password)
        {
            Logger.Write("[Register]",name,"User {0} is trying to register",name);
            var register = new RegisterAction();
            return register.Run(name, nickname, password, Server.Session);
        }
        public Session Login(String name, String password)
        {
            Logger.Write("[Login]", name, "User {0} is trying to login", name);
            var action = new LoginAction();
            return action.Run(name,password,Server.Session);
        }

        public void Logout(Session user)
        {
            Logger.Write("[Logout]", user.Username, "User {0} is trying to logout", user.Username);
            var logoutAction = new LogoutAction();
            logoutAction.Run(user,Server.Session);
        }

        public Session Subscribe(Session session,IUser client)
        {
            Logger.Write("[Subscribe]", session.Username, "User {0} is trying to subscribe", session.Username);
            var subscribe = new SubscribeAction();
            return subscribe.Run(session, client, Server.Session, Server.SubscribeUsers);
        }

        public Session RefreshSession(Session session)
        {
            Logger.Write("[SessionRefres]",session.Username,"User{0} is tryigin to refresh his session",session.Username);
            var refresh = new RefreshSessionAction();
            return refresh.Run(session,Server.Session);
        }
    }
}