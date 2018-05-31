package dal.csci5308;

public class SecurityMock implements Security {
    //authenticate dealer, as long as dealerid is not FAKE_DEALER_ID or accesskey is not FAKE_DEALER_ACCESSKEY
    public boolean IsDealerAuthorized(String dealerid, String dealeraccesskey){
        if(dealerid.equals("FAKE_DEALER_ID"))
            return false;
        if(dealeraccesskey.equals("FAKE_DEALER_ACCESSKEY"))
            return false;
        return true;
    }
}
