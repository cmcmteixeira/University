using System;

namespace Common.Data
{
    [Serializable]
    public class History
    {
        public const int Sale = 1;
        public const int Purchase = 2;

        public int DiginoteAmount;
        public double Money;
        public String Buyer;
        public String Seller;
        public int Type;

        public DateTime Date;
    }
}
