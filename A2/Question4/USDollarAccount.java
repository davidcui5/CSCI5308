public class USDollarAccount extends BankAccount
{
    //USDollarAccount can store USD in balance, but you have to give it the USD exchange rate.
    //it can substitute BankAccount object, new BankAccount(1.00f) is same as new USDollarAccount(1.00f)
    public USDollarAccount(float exchangeRate){
        super(exchangeRate);
    }
}