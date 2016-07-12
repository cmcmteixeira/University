using System;
using System.Collections;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Driver;

namespace Server.Database.Model
{
    class Transaction : GenericModel
    {
        public int DiginoteAmount;
        public int MoneyAmount;
        public int Status;

        public int InitialDigiAmount = 0;

        public ArrayList Diginotes;
        public DateTime Date;

        public MongoDBRef OwnerRef;
        public ObjectId OwnerId;
        [BsonIgnore]
        public User Owner
        {
            get
            {
                return  Database.GetDatabase().FetchDBRefAs<User>(OwnerRef); ;
            }
            set
            {
                OwnerId = value.Id;
                OwnerRef = new MongoDBRef(DatabaseName,Collection<User>.GetCollection().Name,value.Id);
            }
        }


        public Transaction()
        {
            this.Date = DateTime.Now;
        }

    }
    class TransactionPurchase : Transaction
    {

        public TransactionPurchase(int amount,User buyer):base()
        {
            DiginoteAmount = amount;
            Owner = buyer;
        }

        public History Buy(TransactionSale sale,bool sell = false)
        {
            var demmand = this.DiginoteAmount;
            var offer = sale.DiginoteAmount;
            
            sale.DiginoteAmount = offer - demmand;
            sale.DiginoteAmount = sale.DiginoteAmount > 0 ? sale.DiginoteAmount : 0;

            DiginoteAmount = demmand - offer;
            DiginoteAmount = DiginoteAmount > 0 ? DiginoteAmount : 0;

            var history = new Model.History();
            history.DiginoteAmount = demmand - DiginoteAmount;
            history.Money = history.DiginoteAmount*history.DiginoteAmount;
            history.Type = sell ? History.Sale : History.Purchase;
            return history;
        }
        public static explicit operator Common.Data.TransactionPurchase(TransactionPurchase purchase)
        {
            return new Common.Data.TransactionPurchase
            {
                Date = purchase.Date,
                DiginoteAmount = purchase.DiginoteAmount,
            };
        }
    }
    class TransactionSale : Transaction
    {
        public TransactionSale(int amount, User seller): base()
        {
            this.DiginoteAmount = amount;
            this.Owner = seller;
        }

        public History Sell(TransactionPurchase purchase)
        {
            return purchase.Buy(this,true);
        }

        public static explicit operator Common.Data.TransactionSale(TransactionSale sale)
        {
            var trans = new Common.Data.TransactionSale
            {
                Date = sale.Date,
                DiginoteAmount = sale.DiginoteAmount,
            };

            return trans;
        }
    }
    
}
