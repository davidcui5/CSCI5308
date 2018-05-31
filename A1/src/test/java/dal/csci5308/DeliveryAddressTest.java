package dal.csci5308;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class DeliveryAddressTest {

    //this is a valid XML
    static final String VALID_ADDR = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>";

    //this is also valid, but name is empty string
    static final String VALID_EMPTY_NAME = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>\n" +
            "	<name></name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>";

    //the following 5 are missing each of the 5 elements
    static final String INVALID_MISSING_NAME = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>";

    static final String INVALID_MISSING_STREET = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>";

    static final String INVALID_MISSING_CITY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>";

    static final String INVALID_MISSING_PROVINCE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>";

    static final String INVALID_MISSING_POST = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "</deliveryaddress>";

    //the following 4 have each of 4 required elements empty (street, city, province, postal code)
    static final String INVALID_EMPTY_STREET = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street></street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>";

    static final String INVALID_EMPTY_CITY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city></city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>";

    static final String INVALID_EMPTY_PROVINCE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province></province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>";

    static final String INVALID_EMPTY_POST = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode></postalcode>\n" +
            "</deliveryaddress>";

    // This method is written by Robert Hawkey
    DeliveryAddress deserializeXMLToAddrObject(String xml)
    {
        try
        {
            StringReader reader = new StringReader(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(DeliveryAddress.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            DeliveryAddress addr = (DeliveryAddress) jaxbUnmarshaller.unmarshal(reader);
            return addr;
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    @DisplayName("VALID_ADDR: test get, deserialize, validate methods")
    void validAddrTest(){
        DeliveryAddress addr = deserializeXMLToAddrObject(VALID_ADDR);
        assertNotNull(addr);
        assertEquals("Mrs. Jane Smith",addr.getName());
        assertEquals("35 Streetname",addr.getStreet());
        assertEquals("Halifax",addr.getCity());
        assertEquals("NS",addr.getProvince());
        assertEquals("B2T1A4",addr.getPostalCode());
        assertTrue(addr.validate());
    }

    @Test
    @DisplayName("VALID_EMPTY_NAME: test validate")
    void validEmptyNameTest(){
        DeliveryAddress addr = deserializeXMLToAddrObject(VALID_EMPTY_NAME);
        assertTrue(addr.validate());
    }

    @Test
    @DisplayName("INVALID_MISSING_NAME: test validate")
    void invalidMissingNameTest(){
        DeliveryAddress addr = deserializeXMLToAddrObject(INVALID_MISSING_NAME);
        assertTrue(!addr.validate());
    }
    @Test
    @DisplayName("INVALID_MISSING_STREET: test validate")
    void invalidMissingStTest(){
        DeliveryAddress addr = deserializeXMLToAddrObject(INVALID_MISSING_STREET);
        assertTrue(!addr.validate());
    }
    @Test
    @DisplayName("INVALID_MISSING_CITY: test validate")
    void invalidMissingCityTest(){
        DeliveryAddress addr = deserializeXMLToAddrObject(INVALID_MISSING_CITY);
        assertTrue(!addr.validate());
    }
    @Test
    @DisplayName("INVALID_MISSING_NAME: test validate")
    void invalidMissingProvinceTest(){
        DeliveryAddress addr = deserializeXMLToAddrObject(INVALID_MISSING_PROVINCE);
        assertTrue(!addr.validate());
    }
    @Test
    @DisplayName("INVALID_MISSING_POST: test validate")
    void invalidMissingPostTest(){
        DeliveryAddress addr = deserializeXMLToAddrObject(INVALID_MISSING_POST);
        assertTrue(!addr.validate());
    }

    @Test
    @DisplayName("INVALID_EMPTY_STREET: test validate")
    void invalidEmptyStTest(){
        DeliveryAddress addr = deserializeXMLToAddrObject(INVALID_EMPTY_STREET);
        assertTrue(!addr.validate());
    }
    @Test
    @DisplayName("INVALID_EMPTY_CITY: test validate")
    void invalidEmptyCityTest(){
        DeliveryAddress addr = deserializeXMLToAddrObject(INVALID_EMPTY_CITY);
        assertTrue(!addr.validate());
    }
    @Test
    @DisplayName("INVALID_EMPTY_NAME: test validate")
    void invalidEmptyProvinceTest(){
        DeliveryAddress addr = deserializeXMLToAddrObject(INVALID_EMPTY_PROVINCE);
        assertTrue(!addr.validate());
    }
    @Test
    @DisplayName("INVALID_EMPTY_POST: test validate")
    void invalidEmptyPostTest(){
        DeliveryAddress addr = deserializeXMLToAddrObject(INVALID_EMPTY_POST);
        assertTrue(!addr.validate());
    }

}
