
using System;

namespace Server.Database.Model
{
    class History : GenericModel
    {
        public const int Sale = 1;
        public const int Purchase = 2;

        public int DiginoteAmount;
        public double Money;
        public String Buyer;
        public String Seller;
        public int Type;
        public DateTime Date = DateTime.Now;
        
        public static explicit operator Common.Data.History(History h)
        {
            return new Common.Data.History
            {
                DiginoteAmount = h.DiginoteAmount,
                Buyer = h.Buyer,
                Seller = h.Seller,
                Type = h.Type,
                Date = h.Date,
                Money = h.Money
            };
        }
    }
}
