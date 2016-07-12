using Common.Data;

namespace Common.Controllers
{
    public interface ITransactionManager
    {
        TransactionPurchase Buy(Session session, int amount);
        TransactionSale Sell(Session session, int amount);
        bool ChangePrice(Session session, double newPrice);
        void DeleteTransaction(Session session, Transaction transaction);
        void DeleteAllTransactions(Session session);
    }
}
