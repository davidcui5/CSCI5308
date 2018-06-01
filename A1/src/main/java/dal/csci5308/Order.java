package dal.csci5308;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

//this Order object contains Dealer, orderitems (a list of Item), and DeliveryAddress
//I use this class and JAXB to serialize XML order to Order object
@XmlRootElement(name="order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"dealer","orderItems","deliveryAddress"})
public class Order {
    @XmlElement(name="dealer")
    Dealer dealer;
    @XmlElementWrapper(name="orderitems")
    @XmlElement(name="item")
    ArrayList<Item> orderItems;
    @XmlElement(name="deliveryaddress")
    DeliveryAddress deliveryAddress;

    //validate the orderItems, invalid if it's null (no orderitems element) or if it's empty (no items)
    public boolean validateOrderList(){
        if (orderItems == null || orderItems.isEmpty())
            return false;
        else
            return true;
    }

    public OrderResponse processOrder(){
        //0. Make OrderResponse Object
        OrderResponse response = new OrderResponse();

        //1. Authenticate dealer with Security mock
        boolean authorized = dealer.isDealerAuthorized(new SecurityMock());
        if(!authorized){
            //not authorized, make appropriate response and return it
            response.setResult("failure");
            response.setError("Not authorized");
            return response;
        }

        //2.1. Validate FORMAT of incoming order XML (Dealer authorized, so must be valid, only validate other elements
        //When validating, first check DeliveryAddress, then orderItems, then each Item entry
        //check DeliveryAddress
        boolean validAddr = deliveryAddress.validate();
        if (!validAddr){
            //DeliveryAddress NOT VALID, make response and return it
            response.setResult("failure");
            response.setError("Invalid order");
            response.setErrorMessage("Invalid delivery address");
            return response;
        }

        //check orderItems
        boolean validList = validateOrderList();
        if(!validList){
            //orderItems list NOT VALID, make response and return it
            response.setResult("failure");
            response.setError("Invalid order");
            response.setErrorMessage("Invalid order item list");
            return response;
        }

        //check Item entries
        boolean validItem = true;
        for(Item item : orderItems){
            if(!item.validate()){
                validItem = false;
                break;
            }
        }
        if(!validItem){
            //at least 1 Item entry is NOT VALID, make response and return it
            response.setResult("failure");
            response.setError("Invalid order");
            response.setErrorMessage("Invalid order item entry");
            return response;
        }

        //2.2 AND 3. For each Item, validate with Database. If fails, update Item with "failure" and "invalid part".
        //If success, check it with PARTMANAGER. If fails, update Item with "failure" and errormessage.
        //If success, update Item with "success". Then, add orderItems to OrderResponse and return response.
        DatabaseInterfaceMock dbMock = new DatabaseInterfaceMock();
        PARTMANAGERMock pmMock = new PARTMANAGERMock();
        for(Item item : orderItems){
            if(item.validatePartnumber(dbMock)){
                //Item is validated with Database
                //submit Item to PARTMANAGER and change Item's fields
                int partNumber = Integer.parseInt(item.getPartnumber());
                int quantity = Integer.parseInt(item.getQuantity());
                PARTMANAGER.PartResponse pmResponse = pmMock.SubmitPartForManufactureAndDelivery(partNumber,quantity,deliveryAddress);
                switch (pmResponse){
                    case SUCCESS:
                        item.setResult("success");
                        item.setErrorMessage("");
                        break;
                    case OUT_OF_STOCK:
                        item.setResult("failure");
                        item.setErrorMessage("out of stock");
                        break;
                    case NO_LONGER_MANUFACTURED:
                        item.setResult("failure");
                        item.setErrorMessage("no longer manufactured");
                        break;
                }
            }
            else{
                //Item not validated with Database
                //Change Item's fields
                item.setResult("failure");
                item.setErrorMessage("invalid part");
            }
        }
        //each Item has been processed, add orderItems to response and return it
        response.setOrderItems(orderItems);
        return response;
    }
}
