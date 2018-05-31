package dal.csci5308;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class Main {

    public static void main (String[] args) throws Exception {

        LowercaseXMLElementStreamReader reader = new LowercaseXMLElementStreamReader("C:\\Users\\david2\\CSCI5308\\A1\\src\\main\\java\\dal\\csci5308\\order.xml", Order.class);
        Order order = (Order) reader.DeserializeXMLIntoObject();

        System.out.println(order.dealer.getDealerid());
        System.out.println(order.dealer.getDealeraccesskey());
        System.out.println(order.dealer.validate());

        System.out.println(order.orderItems.isEmpty());
        System.out.println(order.orderItems.get(0).getPartnumber());
        System.out.println(order.orderItems.get(0).getQuantity());
        System.out.println(order.orderItems.get(0).validate());
        System.out.println(order.orderItems.get(1).getPartnumber());
        System.out.println(order.orderItems.get(1).getQuantity());
        System.out.println(order.orderItems.get(1).validate());


        System.out.println(order.deliveryAddress.getName());
        System.out.println(order.deliveryAddress.getStreet());
        System.out.println(order.deliveryAddress.getCity());
        System.out.println(order.deliveryAddress.getProvince());
        System.out.println(order.deliveryAddress.getPostalCode());
        System.out.println(order.deliveryAddress.validate());

        try
        {
            File file = new File("output.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(order, file);
            jaxbMarshaller.marshal(order, System.out);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }


        //authenticate dealer with security interface

        //validate incoming order

        //Check items are valid with database

        //Submit valid parts to PartManager

        //Return responses
    }

}
