public class BankAccount
{
    private float balance;
    private float exchangeRate;   //exchangeRate is now a field in base class

    //exchangeRate is to be provided when instantiating BankAccount objects
    public BankAccount(float exchangeRate)
    {
        this.exchangeRate = exchangeRate;
    }

    public float getBalance()
    {
        return balance;
    }

    //this method allows exchangeRate to change in the future, which I think is logical.
    //for example, say we have 100 some currency in our balance, and then rate changes, we still have the 100 currency,
    // but any new credit/debit will take effect of the new rate.
    public void setExchangeRate(float exchangeRate) {
        this.exchangeRate = exchangeRate;
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