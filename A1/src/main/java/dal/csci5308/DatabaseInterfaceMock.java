package dal.csci5308;

//simple mockup for DatabaseInterface
public class DatabaseInterfaceMock implements DatabaseInterface {

    //partNumber other than 5678 are valid, 5678 is invalid.
    @Override
    public boolean isPartnumberValid(String partnumber) {
        if(partnumber.equals("5678"))
            return false;
        else
            return true;
    }
}
