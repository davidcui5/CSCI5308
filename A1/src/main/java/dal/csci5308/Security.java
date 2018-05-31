package dal.csci5308;

public interface Security
{
    // Returns whether the dealer is authorized to order parts on behalf of their customers.
    public boolean IsDealerAuthorized(String dealerid, String dealeraccesskey);
}
