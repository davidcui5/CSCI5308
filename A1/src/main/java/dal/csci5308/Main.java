package dal.csci5308;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class Main {

    public static void main (String[] args) throws Exception {

        Order order;
        try{
            LowercaseXMLElementStreamReader reader = new LowercaseXMLElementStreamReader("C:\\Users\\david2\\CSCI5308\\A1\\order.xml", Order.class);
            order = (Order) reader.DeserializeXMLIntoObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        OrderResponse response = order.processOrder();
        try
        {
            File file = new File("output.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(OrderResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(response, file);
            jaxbMarshaller.marshal(response, System.out);
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
