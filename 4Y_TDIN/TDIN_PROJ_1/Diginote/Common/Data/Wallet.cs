using System;

namespace Common.Data
{
    [Serializable]
    public abstract class Wallet
    {
        protected TransactionPurchase TransactionPurchase{get;set;}
        protected TransactionSale TransactionSale { get; set; }

    }
}
