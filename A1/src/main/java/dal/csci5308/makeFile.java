package dal.csci5308;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class makeFile {

    public static void main(String[] args){

        DeliveryAddress addr = new DeliveryAddress();
        addr.setName("David Cui");
        addr.setPostalCode("abcdef");

        try
        {
            File file = new File("output.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(DeliveryAddress.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(addr, file);
            jaxbMarshaller.marshal(addr, System.out);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

    }
}
