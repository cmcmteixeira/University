using System;
using Common.Controllers;
using Common.Data;
using Server.Actions.Transaction;


namespace Server
{
    class TransactionController : MarshalByRefObject ,ITransactionManager
    {
        public TransactionPurchase Buy(Session session, int amount)
        {
            Log.Logger.Write("[Buy]",session.Username,"User {0} is trying to buy: {1} diginotes",session.Username,amount);
            var buyAction = new BuyAction();
            var retVal = buyAction.Run(session, amount, Server.Session, Server.SubscribeUsers);
            return retVal;
        }

        public TransactionSale Sell(Session session, int amount)
        {
            Log.Logger.Write("[Sell]", session.Username, "User {0} is trying to sell: {1} diginotes", session.Username, amount);
            var sellAction = new SellAction();
            var retVal = sellAction.Run(session, amount, Server.Session, Server.SubscribeUsers);
            return retVal;
        }

        public bool ChangePrice(Session session, double newPrice)
        {
            Log.Logger.Write("[Sell]", session.Username, "User {0} is trying to change price: {1} ", session.Username, newPrice);
            var changeAction = new ChangePriceAction();
            return changeAction.Run(session,newPrice,Server.Session,Server.SubscribeUsers);
        }

        public void DeleteTransaction(Session session, Transaction transaction)
        {
            Log.Logger.Write("[DELETE]",session.Username,"User{0} is trying to delete a transaction",session.Username);
            var deleteAction = new DeleteTransactionAction();
            deleteAction.Run(session, transaction, Server.Session,Server.SubscribeUsers);
        }

        public void DeleteAllTransactions(Session session)
        {
            Log.Logger.Write("[DELETE]", session.Username, "User{0} is trying to delete all transaction", session.Username);
            var deleteAction = new DeleteAllTransactionAction();
            deleteAction.Run(session, Server.Session, Server.SubscribeUsers);
        }
    }
}
