public class LoginCredential
{
	//stores login credentials
	private String userName;
	private String password;

	public void SetUserName(String userName)
	{
		this.userName = userName;
	}

	public String GetUserName()
	{
		return userName;
	}

	public void SetPassword(String password)
	{
		this.password = password;
	}

	public String GetPassword()
	{
		return password;
	}
}