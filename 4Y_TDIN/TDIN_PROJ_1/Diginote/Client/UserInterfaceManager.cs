using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting;
using Common.Controllers;
using Common.Data;

namespace Client
{
    class UserInterfaceManager
    {
        public RegistryForm RegForm { get; private set; }
        public LoginForm LogForm { get; private set; }
        public InitialForm InitForm { get; private set; }
        public ClientForm CliForm { get; private set; }
        public DeleteAllForm DeleteForm { get; private set; }
        public NewPriceForm NewPriceForm { get; private set; }
        internal readonly IUserManager RUserManager;
        internal readonly ITransactionManager RTransactionManager;
        internal Session Session;
        private readonly ServerUser _serverCall;


        public UserInterfaceManager()
        {
            RegForm = new RegistryForm();
            LogForm = new LoginForm();
            InitForm = new InitialForm();
            CliForm = new ClientForm();
            NewPriceForm = new NewPriceForm();
            DeleteForm = new DeleteAllForm();
            RUserManager = (IUserManager)RemotingServices.Connect(typeof(IUserManager), "tcp://localhost:9000/Server/User");
            RTransactionManager = (ITransactionManager)RemotingServices.Connect(typeof(ITransactionManager), "tcp://localhost:9000/Server/Transaction");
            _serverCall = new ServerUser(this);

            InitForm.LoginButton.Click += OnInitFormLoginButtonClick;
            InitForm.RegistryButton.Click += OnInitFormRegistryButtonClick;
            RegForm.Submit.Click += OnRegFormSubmitClick;
            LogForm.Submit.Click += OnLogFormSubmitClick;
            LogForm.Shown += OnlogFormShown;
            CliForm.Shown += OnCliFormShow;
            CliForm.BuyButton.Click += OnCliFormBuyButtonClick;
            CliForm.SellButton.Click += OnCliFormSellButtonClick;
            CliForm.DeleteTransaction.Click += OnDeleteTransactionDeleteButton;
            NewPriceForm.SubmitButton.Click += OnNewPriceFormChangeButton;

            
            DeleteForm.AcceptButton.Click += OnDeleteFormAccept;
            DeleteForm.RefuseButton.Click += OnDeleteFormRefuse;



        }
        private void OnInitFormLoginButtonClick(object sender, EventArgs e)
        {
            InitForm.Hide();
            LogForm.Show();
        }
        private void OnInitFormRegistryButtonClick(object sender, EventArgs e)
        {
            InitForm.Hide();
            RegForm.Show();
        }
        private void OnRegFormSubmitClick(object sender, EventArgs e)
        {
            Session = RUserManager.Register(
                RegForm.UserName.Text,
                RegForm.UserNickname.Text,
                RegForm.UserPassword.Text
            );
            if (Session == null)
            {
                return;
            }
            RUserManager.Subscribe(Session, _serverCall);
            Refresh(false);
            RegForm.Hide();
            LogForm.Show();
        }
        private void OnLogFormSubmitClick(object sender, EventArgs e)
        {
            Session = RUserManager.Login(
                LogForm.UserName.Text,
                LogForm.UserPassword.Text
            );
            if (Session == null)
            {
                return;
            }
            RUserManager.Subscribe(Session, _serverCall);
            Refresh(false);
            LogForm.Hide();
            CliForm.Show();

        }

        private void OnCliFormShow(object sender, EventArgs e)
        {
            if (Session == null) return;
            LogForm.Hide();
            Refresh();
            CliForm.Show();
        }
        private void OnlogFormShown(object sender, EventArgs e)
        {
            if (Session == null) return;
            LogForm.Hide();
            Refresh();
            CliForm.Show();
        }
        private void OnCliFormBuyButtonClick(object sender, EventArgs e)
        {
            Int32 ammount;
            try
            {
                ammount = Int32.Parse(CliForm.DiginoteAmount.Text);
            }
            catch(Exception)
            {
                return;
            }
            RTransactionManager.Buy(Session, ammount);
        }
        private void OnCliFormSellButtonClick(object sender, EventArgs e)
        {
            Int32 ammount;
            try
            {
                ammount = Int32.Parse(CliForm.DiginoteAmount.Text);
            }
            catch (Exception)
            {
                return;
            }
            RTransactionManager.Sell(Session, ammount);
        }
        private void OnNewPriceFormChangeButton(object sender, EventArgs e)
        {
            Double ammount;
            try
            {
                ammount = Double.Parse(NewPriceForm.NewPriceInput.Text);
            }
            catch (Exception)
            {
                return;
            }
            RTransactionManager.ChangePrice(Session,ammount);
            NewPriceForm.Hide();
            Refresh();
            CliForm.Show();
            
        }

        private void OnDeleteTransactionDeleteButton(object sender, EventArgs e)
        {
            var currentSelection = CliForm.currentTransactions.CurrentCellAddress.Y;
            var transactions = new List<Transaction>();
            transactions.AddRange(Session.sales ?? new TransactionSale[0]);
            transactions.AddRange(Session.purchases ?? new TransactionPurchase[0]);
            var orderTrans = transactions.OrderByDescending(transaction => transaction.Date).ToArray();

            if (currentSelection >= 0 && orderTrans.Length > 0 && currentSelection < orderTrans.Length)
            {
                RTransactionManager.DeleteTransaction(Session, orderTrans[currentSelection]);
            }
        }

        private void OnDeleteFormAccept(object sender, EventArgs e)
        {
            CliForm.CurrentQuote.Text = DeleteForm.NewPriceLabel.Text;
            DeleteForm.Hide();
            CliForm.Show();
        }

        private void OnDeleteFormRefuse(object sender, EventArgs e)
        {
            RTransactionManager.DeleteAllTransactions(Session);
            DeleteForm.Hide();
            CliForm.Show();
        }


        public void Refresh(bool server = true)
        {
            if(server)
            Session =RUserManager.RefreshSession(Session);
            
            CliForm.UserDiginoteAmount.Text = Session.DigiAmount.ToString();
            CliForm.UserMoneyAmount.Text    = Session.Money.ToString();
            CliForm.CurrentQuote.Text       = Session.CurrentQuote.ToString();

            CliForm.CurrentQuote.Text = Session.CurrentQuote.ToString();
            CliForm.UserName.Text = Session.Username;

            var transactions = new List<Transaction>();
            transactions.AddRange(Session.sales ?? new TransactionSale[0]);
            transactions.AddRange(Session.purchases ?? new TransactionPurchase[0]);

            var orderTrans = transactions.OrderByDescending(transaction => transaction.Date);
            CliForm.currentTransactions.Rows.Clear();

            foreach (var transaction in orderTrans)
            {
                var transSale = transaction.GetType() == typeof(TransactionSale) ? (TransactionSale) transaction : null;
                CliForm.currentTransactions.Rows.Add(transaction.Date,transSale == null ? "[BUY]":"[SELL]",transaction.DiginoteAmount );
            }
            CliForm.pastTransactions.Rows.Clear();
            if(Session.history  != null)
            foreach (var hist in Session.history.OrderByDescending(h => h.Date))
            {
                CliForm.pastTransactions.Rows.Add(
                    hist.Date,
                    hist.Type == History.Sale ? "[SELL]" : "[BUY]",
                    hist.DiginoteAmount.ToString(),
                    hist.Buyer,
                    hist.Seller,
                    hist.Type == History.Sale ? hist.Money : -hist.Money
                );
            }
        }

    }


}
