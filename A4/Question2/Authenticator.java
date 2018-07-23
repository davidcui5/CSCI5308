public class Authenticator implements IAuthenticator
{
	@Override
	public boolean Authenticate(LoginCredential credential)
	{
		return (credential.GetUserName().equals("joe") && credential.GetPassword().equals("joepass"));
	}
}