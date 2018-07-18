public class Person
{
	private String name;

	private String areaCode;
	private String phoneNumber;

	private String userName;
	private String password;

	public Person(String name)
	{
		this.name = name;
	}

	public void SetAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}
	public String GetAreaCode()
	{
		return areaCode;
	}
	public void SetPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	public String GetPhoneNumber()
	{
		if (areaCode != null && areaCode != "")
		{
			return "(" + areaCode + ") " + phoneNumber; 
		}
		return phoneNumber;
	}

	public void SetLoginCredentials(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}
	public boolean AuthenticateUser()
	{
		return (userName.equals("joe") && password.equals("joepass"));
	}
}