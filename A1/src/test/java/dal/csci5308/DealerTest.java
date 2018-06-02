package dal.csci5308;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

//test dealer class
public class DealerTest {

    //these are the XML Strings I use to make Dealer object and do tests
    //this one is an authorized dealer
    static final String AUTHORIZED_STR = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealerid>XXX-1234-ABCD-1234</dealerid>\n" +
            "	<dealeraccesskey>kkklas8882kk23nllfjj88290</dealeraccesskey>\n" +
            "</dealer>";

    //unauthorized dealer because ID is FAKE_DEALER_ID
    static final String UNAUTHORIZED_ID = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealerid>FAKE_DEALER_ID</dealerid>\n" +
            "	<dealeraccesskey>kkklas8882kk23nllfjj88290</dealeraccesskey>\n" +
            "</dealer>";

    //unauthorized dealer because Key is FAKE_DEALER_ACCESSKEY
    static final String UNAUTHORIZED_KEY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealerid>XXX-1234-ABCD-1234</dealerid>\n" +
            "	<dealeraccesskey>FAKE_DEALER_ACCESSKEY</dealeraccesskey>\n" +
            "</dealer>";

    //this one has empty ID
    static final String NO_ID_VALUE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealerid></dealerid>\n" +
            "	<dealeraccesskey>kkklas8882kk23nllfjj88290</dealeraccesskey>\n" +
            "</dealer>";

    //empty key
    static final String NO_KEY_VALUE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealerid>XXX-1234-ABCD-1234</dealerid>\n" +
            "	<dealeraccesskey></dealeraccesskey>\n" +
            "</dealer>";

    //no ID element
    static final String NO_ID_ELEMENT = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealeraccesskey>kkklas8882kk23nllfjj88290</dealeraccesskey>\n" +
            "</dealer>";

    //no key element
    static final String NO_KEY_ELEMENT = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<dealer>\n" +
            "	<dealerid>XXX-1234-ABCD-1234</dealerid>\n" +
            "</dealer>";

    //this is the mock Security object, call it authorizer
    static Security authorizer;

    // This method is written by Robert Hawkey
    // I borrowed this from Robert Hawkey's lecture sample code, it is used to turn the XML Strings to Dealer objects
    Dealer deserializeXMLToDealerObject(String xml)
    {
        try
        {
            StringReader reader = new StringReader(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Dealer.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Dealer dealer = (Dealer) jaxbUnmarshaller.unmarshal(reader);
            return dealer;
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //construct mock Security
    @BeforeAll
    static void initAll(){
        authorizer = new SecurityMock();
    }

    @Test
    @DisplayName("Test gets and sets methods")
    void getAndSetTest(){
        Dealer dealer = new Dealer();
        dealer.setDealerid("id");
        dealer.setDealeraccesskey("key");
        assertEquals("id",dealer.getDealerid());
        assertEquals("key",dealer.getDealeraccesskey());
    }

    @Test
    @DisplayName("AUTHORIZED_STR: test deserialize method, Dealer's get, validate, isAuthorized methods")
    void authorizedDealerTest(){
        Dealer dealer = deserializeXMLToDealerObject(AUTHORIZED_STR);
        assertNotNull(dealer);
        assertEquals("XXX-1234-ABCD-1234",dealer.getDealerid());
        assertEquals("kkklas8882kk23nllfjj88290",dealer.getDealeraccesskey());
        assertTrue(dealer.validate());
        assertTrue(dealer.isDealerAuthorized(authorizer));
    }

    @Test
    @DisplayName("UNAUTHORIZED_ID: test validate and isAuthorized methods")
    void unauthorizedIdTest(){
        Dealer dealer = deserializeXMLToDealerObject(UNAUTHORIZED_ID);
        assertTrue(dealer.validate());
        assertTrue(!dealer.isDealerAuthorized(authorizer));
    }

    @Test
    @DisplayName("UNAUTHORIZED_KEY: test validate and isAuthorized methods")
    void unauthorizedKeyTest(){
        Dealer dealer = deserializeXMLToDealerObject(UNAUTHORIZED_KEY);
        assertTrue(dealer.validate());
        assertTrue(!dealer.isDealerAuthorized(authorizer));
    }

    //the following two are for cases when ID or KEY is empty, but the element tag is there.
    @Test
    @DisplayName("NO_ID_VALUE: test validate and isAuthorized methods")
    void noIdValueTest(){
        Dealer dealer = deserializeXMLToDealerObject(NO_ID_VALUE);
        assertTrue(!dealer.validate());
        assertTrue(!dealer.isDealerAuthorized(authorizer));
    }

    @Test
    @DisplayName("NO_KEY_VALUE: test validate and isAuthorized methods")
    void noKeyValueTest(){
        Dealer dealer = deserializeXMLToDealerObject(NO_KEY_VALUE);
        assertTrue(!dealer.validate());
        assertTrue(!dealer.isDealerAuthorized(authorizer));
    }

    //the following 2 tests are for cases where ID or KEY element is not there.
    @Test
    @DisplayName("NO_ID_ELEMENT: test validate and isAuthorized methods")
    void noIdElementTest(){
        Dealer dealer = deserializeXMLToDealerObject(NO_ID_ELEMENT);
        assertTrue(!dealer.validate());
        assertTrue(!dealer.isDealerAuthorized(authorizer));
    }

    @Test
    @DisplayName("NO_KEY_ELEMENT: test validate and isAuthorized methods")
    void noKeyElementTest(){
        Dealer dealer = deserializeXMLToDealerObject(NO_KEY_ELEMENT);
        assertTrue(!dealer.validate());
        assertTrue(!dealer.isDealerAuthorized(authorizer));
    }
}
