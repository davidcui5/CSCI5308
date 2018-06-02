package dal.csci5308;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class OrderTest {

    //I make various XML strings that can be deserialized to Order objects
    // these objects are used to test methods in Order class
    // these strings leads to different outputs

    //no dealer element
    static final String  MISSING_DEALER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
                "<orderitems>" +
                "<item>\n" +
                "	<partnumber>1234</partnumber>\n" +
                "	<quantity>2</quantity>\n" +
                "</item>" +
                "</orderitems>" +
                "<deliveryaddress>\n" +
                "	<name>Mrs. Jane Smith</name>\n" +
                "	<street>35 Streetname</street>\n" +
                "	<city>Halifax</city>\n" +
                "	<province>NS</province>\n" +
                "	<postalcode>B2T1A4</postalcode>\n" +
                "</deliveryaddress>" +
            "</order>";

    //no dealer id and key element
    static final String MISSING_DEALER_ID_KEY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
                "<dealer>\n" +
                "</dealer>" +
                "<orderitems>" +
                "<item>\n" +
                "	<partnumber>1234</partnumber>\n" +
                "	<quantity>2</quantity>\n" +
                "</item>" +
                "</orderitems>" +
                "<deliveryaddress>\n" +
                "	<name>Mrs. Jane Smith</name>\n" +
                "</deliveryaddress>" +
            "</order>";

    //no dealer id element
    static final String MISSING_DEALER_ID = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
                "<dealer>\n" +
                "	<dealeraccesskey>GOOD_KEY</dealeraccesskey>\n" +
                "</dealer>" +
                "<orderitems>" +
                "</orderitems>" +
                "<deliveryaddress>\n" +
                "	<province>NS</province>\n" +
                "	<postalcode>B2T1A4</postalcode>\n" +
                "</deliveryaddress>" +
            "</order>";

    //no dealer key element
    static final String MISSING_DEALER_KEY = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
                "<dealer>\n" +
                "	<dealerid>GOOD_DEALER</dealerid>\n" +
                "</dealer>" +
            "</order>";

    //dealer with bad ID
    static final String INVALID_DEALER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
                "<dealer>\n" +
                "	<dealerid>FAKE_DEALER_ID</dealerid>\n" +
                "	<dealeraccesskey>GOOD_KEY</dealeraccesskey>\n" +
                "</dealer>" +
            "</order>";

    //dealer is authorized, but has no address
    static final String NO_ADDRESS = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
            "<dealer>\n" +
            "	<dealerid>GOOD_DEALER</dealerid>\n" +
            "	<dealeraccesskey>GOOD_KEY</dealeraccesskey>\n" +
            "</dealer>" +
            "</order>";

    //address missing some elements
    static final String BAD_ADDRESS = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
            "<dealer>\n" +
            "	<dealerid>GOOD_DEALER</dealerid>\n" +
            "	<dealeraccesskey>GOOD_KEY</dealeraccesskey>\n" +
            "</dealer>" +
            "<orderitems>" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>" +
            "</orderitems>" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" + //missing province, postal code
            "</deliveryaddress>" +
            "</order>";

    //missing item list
    static final String NO_ITEM_LIST = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
            "<dealer>\n" +
            "	<dealerid>GOOD_DEALER</dealerid>\n" +
            "	<dealeraccesskey>GOOD_KEY</dealeraccesskey>\n" +
            "</dealer>" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>" +
            "</order>";

    //item list is empty (no items)
    static final String EMPTY_ITEM_LIST = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
            "<dealer>\n" +
            "	<dealerid>GOOD_DEALER</dealerid>\n" +
            "	<dealeraccesskey>GOOD_KEY</dealeraccesskey>\n" +
            "</dealer>" +
            "<orderitems>" +
            "</orderitems>" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>" +
            "</order>";

    //item is empty(no partnumber, quantity)
    static final String EMPTY_ITEM = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
            "<dealer>\n" +
            "	<dealerid>GOOD_DEALER</dealerid>\n" +
            "	<dealeraccesskey>GOOD_KEY</dealeraccesskey>\n" +
            "</dealer>" +
            "<orderitems>" +
            "<item>\n" +
            "</item>" +
            "</orderitems>" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>" +
            "</order>";

    //item is invalid in database
    static final String INVALID_PART = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
            "<dealer>\n" +
            "	<dealerid>GOOD_DEALER</dealerid>\n" +
            "	<dealeraccesskey>GOOD_KEY</dealeraccesskey>\n" +
            "</dealer>" +
            "<orderitems>" +
            "<item>\n" +
            "	<partnumber>5678</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>" +
            "</orderitems>" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>" +
            "</order>";

    //item out of stock
    static final String OUT_OF_STOCK = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
            "<dealer>\n" +
            "	<dealerid>GOOD_DEALER</dealerid>\n" +
            "	<dealeraccesskey>GOOD_KEY</dealeraccesskey>\n" +
            "</dealer>" +
            "<orderitems>" +
            "<item>\n" +
            "	<partnumber>3456</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>" +
            "</orderitems>" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>" +
            "</order>";

    //item no longer manufactured
    static final String NO_LONGER_MANUFACTURED = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
            "<dealer>\n" +
            "	<dealerid>GOOD_DEALER</dealerid>\n" +
            "	<dealeraccesskey>GOOD_KEY</dealeraccesskey>\n" +
            "</dealer>" +
            "<orderitems>" +
            "<item>\n" +
            "	<partnumber>4567</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>" +
            "</orderitems>" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>" +
            "</order>";

    //item successfully processed
    static final String SUCCESS = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<order>\n" +
            "<dealer>\n" +
            "	<dealerid>GOOD_DEALER</dealerid>\n" +
            "	<dealeraccesskey>GOOD_KEY</dealeraccesskey>\n" +
            "</dealer>" +
            "<orderitems>" +
            "<item>\n" +
            "	<partnumber>1234</partnumber>\n" +
            "	<quantity>2</quantity>\n" +
            "</item>" +
            "</orderitems>" +
            "<deliveryaddress>\n" +
            "	<name>Mrs. Jane Smith</name>\n" +
            "	<street>35 Streetname</street>\n" +
            "	<city>Halifax</city>\n" +
            "	<province>NS</province>\n" +
            "	<postalcode>B2T1A4</postalcode>\n" +
            "</deliveryaddress>" +
            "</order>";

    // This method is written by Robert Hawkey, it converts XML string to object
    Order deserializeXMLToOrderObject(String xml)
    {
        try
        {
            StringReader reader = new StringReader(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Order order = (Order) jaxbUnmarshaller.unmarshal(reader);
            return order;
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    @DisplayName("Test processing Not Authorized Orders")
    //In this test, we test all the XML that results in not authorized response
    void testNotAuthorized(){
        OrderResponse response;
        Order order = deserializeXMLToOrderObject(MISSING_DEALER);
        assertTrue(order.validateOrderItems()); //also test the validateOrderItems method
        response = order.processOrder();
        assertEquals("failure",response.getResult());
        assertEquals("Not authorized",response.getError());
        assertEquals(null,response.getErrorMessage());
        assertEquals(null,response.getOrderItems());

        order = deserializeXMLToOrderObject(MISSING_DEALER_ID_KEY);
        assertTrue(order.validateOrderItems()); //also test the validateOrderItems method
        response = order.processOrder();
        assertEquals("failure",response.getResult());
        assertEquals("Not authorized",response.getError());
        assertEquals(null,response.getErrorMessage());
        assertEquals(null,response.getOrderItems());

        order = deserializeXMLToOrderObject(MISSING_DEALER_ID);
        assertTrue(!order.validateOrderItems()); //also test the validateOrderItems method
        response = order.processOrder();
        assertEquals("failure",response.getResult());
        assertEquals("Not authorized",response.getError());
        assertEquals(null,response.getErrorMessage());
        assertEquals(null,response.getOrderItems());

        order = deserializeXMLToOrderObject(MISSING_DEALER_KEY);
        assertTrue(!order.validateOrderItems()); //also test the validateOrderItems method
        response = order.processOrder();
        assertEquals("failure",response.getResult());
        assertEquals("Not authorized",response.getError());
        assertEquals(null,response.getErrorMessage());
        assertEquals(null,response.getOrderItems());

        order = deserializeXMLToOrderObject(INVALID_DEALER);
        assertTrue(!order.validateOrderItems()); //also test the validateOrderItems method
        response = order.processOrder();
        assertEquals("failure",response.getResult());
        assertEquals("Not authorized",response.getError());
        assertEquals(null,response.getErrorMessage());
        assertEquals(null,response.getOrderItems());
    }

    @Test
    @DisplayName("Test processing Invalid Order Orders")
        //In this test, we test all the XML that results in Invalid Order response
    void testInvalidOrder(){
        OrderResponse response;
        Order order = deserializeXMLToOrderObject(NO_ADDRESS);
        response = order.processOrder();
        assertEquals("failure",response.getResult());
        assertEquals("Invalid order",response.getError());
        assertEquals("Invalid delivery address",response.getErrorMessage());
        assertEquals(null,response.getOrderItems());

        order = deserializeXMLToOrderObject(BAD_ADDRESS);
        response = order.processOrder();
        assertEquals("failure",response.getResult());
        assertEquals("Invalid order",response.getError());
        assertEquals("Invalid delivery address",response.getErrorMessage());
        assertEquals(null,response.getOrderItems());

        order = deserializeXMLToOrderObject(NO_ITEM_LIST);
        response = order.processOrder();
        assertEquals("failure",response.getResult());
        assertEquals("Invalid order",response.getError());
        assertEquals("Invalid order item list",response.getErrorMessage());
        assertEquals(null,response.getOrderItems());

        order = deserializeXMLToOrderObject(EMPTY_ITEM_LIST);
        response = order.processOrder();
        assertEquals("failure",response.getResult());
        assertEquals("Invalid order",response.getError());
        assertEquals("Invalid order item list",response.getErrorMessage());
        assertEquals(null,response.getOrderItems());

        order = deserializeXMLToOrderObject(EMPTY_ITEM);
        response = order.processOrder();
        assertEquals("failure",response.getResult());
        assertEquals("Invalid order",response.getError());
        assertEquals("Invalid order item entry",response.getErrorMessage());
        assertEquals(null,response.getOrderItems());
    }

    @Test
    @DisplayName("Test processing Authorized and Valid Orders")
        //In this test, we test all the XML that results in responses including success, out of stock,
        // no longer manufactured, invalid part.
    void testAuthorizedOrder() {
        OrderResponse response;
        Order order = deserializeXMLToOrderObject(INVALID_PART);
        response = order.processOrder();
        assertEquals(null,response.getResult());
        assertEquals(null,response.getError());
        assertEquals(null,response.getErrorMessage());
        assertEquals("failure",response.getOrderItems().get(0).getResult());
        assertEquals("invalid part",response.getOrderItems().get(0).getErrorMessage());

        order = deserializeXMLToOrderObject(OUT_OF_STOCK);
        response = order.processOrder();
        assertEquals(null,response.getResult());
        assertEquals(null,response.getError());
        assertEquals(null,response.getErrorMessage());
        assertEquals("failure",response.getOrderItems().get(0).getResult());
        assertEquals("out of stock",response.getOrderItems().get(0).getErrorMessage());

        order = deserializeXMLToOrderObject(NO_LONGER_MANUFACTURED);
        response = order.processOrder();
        assertEquals(null,response.getResult());
        assertEquals(null,response.getError());
        assertEquals(null,response.getErrorMessage());
        assertEquals("failure",response.getOrderItems().get(0).getResult());
        assertEquals("no longer manufactured",response.getOrderItems().get(0).getErrorMessage());

        order = deserializeXMLToOrderObject(SUCCESS);
        response = order.processOrder();
        assertEquals(null,response.getResult());
        assertEquals(null,response.getError());
        assertEquals(null,response.getErrorMessage());
        assertEquals("success",response.getOrderItems().get(0).getResult());
        assertEquals("",response.getOrderItems().get(0).getErrorMessage());
    }

}
