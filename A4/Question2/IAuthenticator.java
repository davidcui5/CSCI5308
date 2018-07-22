public interface IAuthenticator {
	boolean authenticate(String userName, String password);
}