namespace Common.Client
{
    public interface IUser
    {
        void SetNewPrice(double oldPrice);
        void RefreshSession();
        void PriceChanged(double newPrice);
    }
}
