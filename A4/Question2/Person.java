public class Person
{
	//extracted new classes, PhoneContact and LoginCredential
	private String name;
	private PhoneContact phoneContact;
	private LoginCredential loginCredential;

	public Person(String name)
	{
		this.name = name;
		phoneContact = new PhoneContact();
		loginCredential = new LoginCredential();
	}

	public void SetAreaCode(String areaCode)
	{
		phoneContact.SetAreaCode(areaCode);
	}

	public String GetAreaCode()
	{
		return phoneContact.GetAreaCode();
	}

	public void SetPhoneNumber(String phoneNumber)
	{
		phoneContact.SetPhoneNumber(phoneNumber);
	}

	public String GetPhoneNumber()
	{
		return phoneContact.GetPhoneNumber();
	}

	public void SetLoginCredentials(String userName, String password)
	{
		loginCredential.SetUserName(userName);
		loginCredential.SetPassword(password);
	}

	//extracted class to do authentication
	public boolean AuthenticateUser()
	{
		IAuthenticator auth = new Authenticator();
		return auth.Authenticate(loginCredential);
	}
}