package dal.csci5308;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

//the entry point to the logic
// the App class only calls other classes, and the other classes are tested, so the App doesn't need tests.
// Except for the serialize into XML block of code, but that's provided by Robert Hawkey, so it is like a library,
// there, it is not tested.
public class App {

    public static void main (String[] args){
        if(args.length != 2){
            System.out.println("Need 2 command line arguments: inputFilename outputFilename");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        //Declare Order order
        Order order;

        //deserialize input XML to order
        try{
            LowercaseXMLElementStreamReader reader = new LowercaseXMLElementStreamReader(inputFileName, Order.class);
            order = (Order) reader.DeserializeXMLIntoObject();
        }
        catch (Exception e)
        {
            System.out.println("Input XML file is invalid, cannot parse into object.");
            return;
        }

        //Declare orderResponse response, process order to get response
        OrderResponse response = order.processOrder();

        //serialize response to XML file with name: outputFileName
        //This block of code is provided by Robert Hawkey in his class examples.
        try
        {
            File file = new File(outputFileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(OrderResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(response, file);
        }
        catch (JAXBException e)
        {
            System.out.println("Error when serializing response, this shouldn't occur.");
        }
    }
}
