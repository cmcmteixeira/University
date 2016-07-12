using System;
using Common.Client;
using Common.Controllers;

namespace Client
{
    class ServerUser : MarshalByRefObject , IUser
    {
        private readonly UserInterfaceManager _uim;

        private delegate void ShowForm();
        private delegate void Refresh(bool server);
        private delegate void PriceChang(double price);

        private readonly NewPriceForm _neprc;
        private readonly ClientForm _cli;
        private double _currentPrice;

        public ServerUser(UserInterfaceManager uim)
        {
            _uim = uim;
            _neprc = uim.NewPriceForm;
            _cli = uim.CliForm;
        }
        public void RefreshSession()
        {
            _cli.BeginInvoke(new Refresh(_uim.Refresh),true);
        }

        public void PriceChanged(double newPrice)
        {
            _cli.BeginInvoke(new PriceChang(PriceChange), newPrice);
        }

        private void PriceChange(double newPrice)
        {
            _cli.Hide();
            _uim.DeleteForm.NewPriceLabel.Text = newPrice.ToString();
            _uim.DeleteForm.Show();
        }
        
        public void SetNewPrice(double oldPrice)
        {
            _currentPrice = oldPrice;
            _cli.BeginInvoke(new ShowForm(GetNewPrice));
        }

        private void GetNewPrice()
        {
            _cli.Hide();
            _neprc.CurrentPrice.Text = _currentPrice.ToString();
            _neprc.Show();

        }


    }
}
