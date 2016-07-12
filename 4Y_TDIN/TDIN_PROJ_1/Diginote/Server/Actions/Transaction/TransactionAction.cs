using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using Common.Client;
using Log;
using MongoDB.Driver;
using Model = Server.Database.Model;

namespace Server.Actions.Transaction
{
    abstract class TransactionAction : Action.Action
    {
        static readonly Mutex Mutex = new Mutex();
        protected static bool Blocked;
        protected static Thread Thread ;
        

        protected TransactionAction()
        {
            Thread = new Thread(Wait60Seconds);
        }

        public void UpdateNotifyPriceChange(List<Tuple<MongoDBRef, Model.History>> owners, Dictionary<String, IUser> subsUsers,Model.Wallet userWallet)
        {
            foreach (var owner in owners)
            {
                var ownerObj    = Database.Collection<Model.User>.FollowReference(owner.Item1); 
                var ownerWallet = Database.Collection<Model.Wallet>.FollowReference(ownerObj.Wallet);

                ownerWallet.Money += owner.Item2.DiginoteAmount * Server.Quote.Value;
                userWallet.Money  -= owner.Item2.DiginoteAmount * Server.Quote.Value;

                var provider = new List<Model.Diginote>(owner.Item2.DiginoteAmount > 0 ? ownerWallet.Diginotes : userWallet.Diginotes);
                var receiver = new List<Model.Diginote>(owner.Item2.DiginoteAmount > 0 ? userWallet.Diginotes : ownerWallet.Diginotes);

                var transferDiginotes = provider.Take(owner.Item2.DiginoteAmount > 0 ? owner.Item2.DiginoteAmount : -owner.Item2.DiginoteAmount);
                provider = provider.Except(transferDiginotes).ToList();
                receiver.InsertRange(0,transferDiginotes);

                ownerWallet.Diginotes = owner.Item2.DiginoteAmount > 0 ? provider.ToArray() : receiver.ToArray();
                userWallet.Diginotes  = owner.Item2.DiginoteAmount > 0 ? receiver.ToArray() : provider.ToArray();

                owner.Item2.Seller = ownerObj.Username;
                owner.Item2.Money  = owner.Item2.DiginoteAmount*Server.Quote.Value;

                Database.Collection<Model.Wallet>.GetCollection().Save(ownerWallet);
                Database.Collection<Model.History>.GetCollection().Save(owner.Item2);
                try
                {
                    subsUsers[ownerObj.Username].RefreshSession();
                }
                catch
                {
                    // ignored
                }
            }
            Database.Collection<Model.Wallet>.GetCollection().Save(userWallet);

        }

        public void StartTransaction(bool checkBlocked = true)
        {
            while (checkBlocked && Blocked)
            {
                Thread.Sleep(500);
            }
            Mutex.WaitOne();
        }

        public void EndTransaction()
        {
            Mutex.ReleaseMutex();
        }



        public void Wait60Seconds()
        {
            Logger.Write("[Sleep]","Server","Locking Server");
            Thread.Sleep(60000);
            Logger.Write("[Wake]", "Server", "Unlocking Server");
            Blocked = false;
        }

    }
}
