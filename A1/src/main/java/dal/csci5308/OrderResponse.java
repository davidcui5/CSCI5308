package dal.csci5308;

import javax.xml.bind.annotation.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

//the OrderResponse class is the object for the response to the XML order
//this object is required as I use JAXB to convert object to XML output file as response
@XmlRootElement(name="order")
@XmlType(propOrder = {"result","error","errorMessage","orderItems"})
public class OrderResponse {

    private String result;
    private String error;
    private String errorMessage;
    private ArrayList<Item> orderItems;

    @XmlElement(name="result")
    public void setResult(String result){
        this.result = result;
    }
    public String getResult(){
        return result;
    }
    @XmlElement(name="error")
    public void setError(String error){
        this.error = error;
    }
    public String getError(){
        return error;
    }
    @XmlElement(name="errormessage")
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
    @XmlElementWrapper(name="orderitems")
    @XmlElement(name="item")
    public void setOrderItems(ArrayList<Item> orderItems){
        this.orderItems = orderItems;
    }
    public ArrayList<Item> getOrderItems(){
        return orderItems;
    }

}
