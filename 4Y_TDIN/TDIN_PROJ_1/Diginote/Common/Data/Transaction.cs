using System;
using System.Collections;

namespace Common.Data
{

    [Serializable]
    public abstract class Transaction
    {
        public int DiginoteAmount;
        public DateTime Date;

        protected Transaction()
        {
            Date = DateTime.Now;
            DiginoteAmount = 0;
        }
        
    }


    [Serializable]
    public class TransactionPurchase : Transaction
    {
        public TransactionPurchase()
        {
            
        }
        public TransactionPurchase(int ammount,DateTime date)
        {
            DiginoteAmount = ammount;
            Date = date;
        }
        


    }
    [Serializable]
    public class TransactionSale : Transaction
    {
        public TransactionSale()
        {
            
        }
        public TransactionSale(int ammount,DateTime date)
        {
            DiginoteAmount = ammount;
            Date = date;

        }


        
    }
}
