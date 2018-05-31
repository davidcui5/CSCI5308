package dal.csci5308;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlRootElement(name="order")
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
