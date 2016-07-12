using System;

namespace Common.Data
{
    [Serializable]
    public class Session
    {
        public Session(String username, String nickname, String password)
        {
            Username = username;
            Nickname = nickname;
            Password = password;
        }
        public Session()
        {

        }

        public String Username {get ; set;}
        public String Nickname { get; set; }
        public String Password { get; set; }
        public double Money    { get; set; }
        public double DigiAmount { get; set; }

        public String SessionKey;

        public double CurrentQuote;

        public TransactionPurchase[] purchases;
        public TransactionSale[] sales;
        public History[] history;
    }
}
