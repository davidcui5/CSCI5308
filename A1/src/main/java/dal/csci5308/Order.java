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








    //validate Order?

    //Item list: If the list is empty, there is no item, it should be invalid. But I can't think of more cases.
}
