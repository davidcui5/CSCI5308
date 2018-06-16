public class BankAccount
{
    private float balance;
    //exchangeRate is now a field in base class, it is protected so USDollarAccount can inherit it and change it
    // in its constructor
    protected float exchangeRate;

    public BankAccount()
    {
        exchangeRate = 1.00f;
    }

    public float getBalance()
    {
        return balance;
    }

    public float getExchangeRate() {
        return exchangeRate;
    }

    public void credit(float amount)
    {
        balance += amount * exchangeRate;
    }

    public void debit(float amount)
    {
        balance -= amount * exchangeRate;
    }
}