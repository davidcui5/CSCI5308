package dal.csci5308;

import com.sun.deploy.util.OrderedHashSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class makeFile {

    public static void main(String[] args){

        OrderResponse or = new OrderResponse();

        try
        {
            File file = new File("makefile.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(OrderResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(or, file);
            jaxbMarshaller.marshal(or, System.out);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

    }
}
