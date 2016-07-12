using System;
using Common.Client;
using Common.Data;

namespace Common.Controllers
{
    public interface IUserManager
    {
        Session Register(String name,String nickname,String password);
        Session Login(String name, String password);
        Session Subscribe(Session session,IUser client);
        Session RefreshSession(Session session);

        void Logout(Session user);
    }
}
