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

public class ItemTest {

    static final String VALID_ITEM = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";

    static final String INVALID_PARTNUMBER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>5678</partnumber>\n" +
            "	<quantity>25</quantity>\n" +
            "</item>";

    static final String INVALID_EMPTY_PARTNUMBER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber></partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";

    static final String INVALID_NO_PARTNUMBER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";

    static final String INVALID_ZERO_PARTNUMBER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>0</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";

    static final String INVALID_NEGATIVE_PARTNUMBER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>-1000</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";

    static final String INVALID_NOT_INT_PARTNUMBER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>abcd</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>";

    static final String INVALID_EMPTY_QUANTITY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity></quantity>\n" +
            "</item>";

    static final String INVALID_NO_QUANTITY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "</item>";

    static final String INVALID_ZERO_QUANTITY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>0</quantity>\n" +
            "</item>";

    static final String INVALID_NEGATIVE_QUANTITY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>-10</quantity>\n" +
            "</item>";

    static final String INVALID_NOT_INT_QUANTITY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>abcd</quantity>\n" +
            "</item>";

    //mock database interface
    static DatabaseInterface db;

    // This method is written by Robert Hawkey, it converts XML string to object
    Item deserializeXMLToItemObject(String xml)
    {
        try
        {
            StringReader reader = new StringReader(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Item.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Item item = (Item) jaxbUnmarshaller.unmarshal(reader);
            return item;
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //initialize by constructing database interface mock
    @BeforeAll
    static void initAll(){
        db = new DatabaseInterfaceMock();
    }

    @Test
    @DisplayName("Test get and set methods")
    void getAndSetTest(){
        Item item = new Item();
        item.setPartnumber("1111");
        assertEquals("1111",item.getPartnumber());
        item.setQuantity("11");
        assertEquals("11",item.getQuantity());
        item.setResult("success");
        assertEquals("success",item.getResult());
        item.setErrorMessage("");
        assertEquals("",item.getErrorMessage());
    }

    //tests: check for valid item, item with invalid partnumber, item missing partnumber/quantity,
    //empty partnumber/quantity, and non-positive integer partnumber/quantity
    @Test
    @DisplayName("VALID_ITEM: test get, deserialize, validate, validatePartnumber methods")
    void validItemTest(){
        Item item = deserializeXMLToItemObject(VALID_ITEM);
        assertNotNull(item);
        assertEquals("1234",item.getPartnumber());
        assertEquals("2",item.getQuantity());
        assertEquals(null,item.getResult());
        assertEquals(null,item.getErrorMessage());
        assertTrue(item.validate());
        assertTrue(item.validatePartnumber(db));
    }

    @Test
    @DisplayName("INVALID_PARTNUMBER: test get, deserialize, validate, validatePartnumber methods")
    void invalidPartnumberTest(){
        Item item = deserializeXMLToItemObject(INVALID_PARTNUMBER);
        assertNotNull(item);
        assertEquals("5678",item.getPartnumber());
        assertEquals("25",item.getQuantity());
        assertEquals(null,item.getResult());
        assertEquals(null,item.getErrorMessage());
        assertTrue(item.validate());
        assertTrue(!item.validatePartnumber(db));
    }

    @Test
    @DisplayName("INVALID_EMPTY_PARTNUMBER: test validate")
    void emptyPartnumberTest(){
        Item item = deserializeXMLToItemObject(INVALID_EMPTY_PARTNUMBER);
        assertTrue(!item.validate());
        assertTrue(!item.validatePartnumber(db));
    }

    @Test
    @DisplayName("INVALID_NO_PARTNUMBER: test validate")
    void noPartnumberTest(){
        Item item = deserializeXMLToItemObject(INVALID_NO_PARTNUMBER);
        assertTrue(!item.validate());
        assertTrue(!item.validatePartnumber(db));
    }

    @Test
    @DisplayName("INVALID_ZERO_PARTNUMBER: test validate")
    void zeroPartnumberTest(){
        Item item = deserializeXMLToItemObject(INVALID_ZERO_PARTNUMBER);
        assertTrue(!item.validate());
        assertTrue(!item.validatePartnumber(db));
    }

    @Test
    @DisplayName("INVALID_NEGATIVE_PARTNUMBER: test validate")
    void negativePartnumberTest(){
        Item item = deserializeXMLToItemObject(INVALID_NEGATIVE_PARTNUMBER);
        assertTrue(!item.validate());
        assertTrue(!item.validatePartnumber(db));
    }

    @Test
    @DisplayName("INVALID_NOT_INT_PARTNUMBER: test validate")
    void notIntPartnumberTest(){
        Item item = deserializeXMLToItemObject(INVALID_NOT_INT_PARTNUMBER);
        assertTrue(!item.validate());
        assertTrue(!item.validatePartnumber(db));
    }

    @Test
    @DisplayName("INVALID_EMPTY_QUANTITY: test validate")
    void emptyQuantityTest(){
        Item item = deserializeXMLToItemObject(INVALID_EMPTY_QUANTITY);
        assertTrue(!item.validate());
        assertTrue(!item.validatePartnumber(db));
    }

    @Test
    @DisplayName("INVALID_NO_QUANTITY: test validate")
    void noQuantityTest(){
        Item item = deserializeXMLToItemObject(INVALID_NO_QUANTITY);
        assertTrue(!item.validate());
        assertTrue(!item.validatePartnumber(db));
    }

    @Test
    @DisplayName("INVALID_ZERO_QUANTITY: test validate")
    void zeroQuantityTest(){
        Item item = deserializeXMLToItemObject(INVALID_ZERO_QUANTITY);
        assertTrue(!item.validate());
        assertTrue(!item.validatePartnumber(db));
    }

    @Test
    @DisplayName("INVALID_NEGATIVE_QUANTITY: test validate")
    void negativeQuantityTest(){
        Item item = deserializeXMLToItemObject(INVALID_NEGATIVE_QUANTITY);
        assertTrue(!item.validate());
        assertTrue(!item.validatePartnumber(db));
    }

    @Test
    @DisplayName("INVALID_NOT_INT_QUANTITY: test validate")
    void notIntQuantityTest(){
        Item item = deserializeXMLToItemObject(INVALID_NOT_INT_QUANTITY);
        assertTrue(!item.validate());
        assertTrue(!item.validatePartnumber(db));
    }
}
