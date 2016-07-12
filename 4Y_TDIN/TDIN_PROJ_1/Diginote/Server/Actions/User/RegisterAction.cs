using System;
using System.Collections.Generic;
using Common;
using Common.Data;
using MongoDB.Driver;
using Server.Database;
using Model = Server.Database.Model;

namespace Server.Actions.User
{
    class RegisterAction : Action.Action
    {
        public Session Run(String name, String nickname , String password, Dictionary<String,Model.User> session)
        {
            var remoteUser = new Session(name, nickname, password);
            var user = new Model.User(remoteUser);
            var wallet = new Model.Wallet();
            wallet.Initialize();
            var userCollection = Collection<Model.User>.GetCollection();
            var walletCollection = Collection<Model.Wallet>.GetCollection();
            walletCollection.Insert(wallet);
            user.Wallet = new MongoDBRef(wallet.DatabaseName, walletCollection.Name, wallet.Id);
            userCollection.Insert(user);

            remoteUser.SessionKey = user.Username + DateTime.Now.ToString();
            session.Add(remoteUser.SessionKey, user);


            return remoteUser;
        }
    }
}
