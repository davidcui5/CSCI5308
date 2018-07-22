public class Authenticator implements IAuthenticator {
	@Override
	public boolean authenticate(String userName, String password) {
		return (userName.equals("joe") && password.equals("joepass"));
	}
}