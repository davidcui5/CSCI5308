package dal.csci5308;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//This Item class, contains partnumber, quantity, result, and errorMessage.
//When deserializing XML file, partnumber, quantity will be set the the values in XML file and
// result and errorMessage will be Null initially.
//Then, after Item is validated, validated with DatabaseInterface, checked with PARTMANAGER,
// results and errorMessage will be set accordingly.
//Then, Item can be used for serializing into XML response files.

@XmlRootElement(name="item")
@XmlType(propOrder = {"partnumber","quantity","result","errorMessage"})
public class Item {
    /*
    <item>
		<!-- The part number to submit to PARTMANAGER. -->
		<partnumber>1234</partnumber>
		<!-- The quantity ordered to submit to PARTMANAGER. -->
		<quantity>2</quantity>
	</item>
	*/

    private String partnumber;
    private String quantity;
    private String result;
    private String errorMessage;

    @XmlElement(name="partnumber")
    public void setPartnumber(String partnumber){
        this.partnumber = partnumber;
    }
    public String getPartnumber(){
        return partnumber;
    }

    @XmlElement(name="quantity")
    public void setQuantity(String quantity){
        this.quantity = quantity;
    }
    public String getQuantity(){
        return quantity;
    }

    @XmlElement(name="result")
    public void setResult(String result){
        this.result = result;
    }
    public String getResult(){
        return result;
    }

    @XmlElement(name="errormessage")
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    //Validate item entry, I used Robert Hawkey's Item Class' Validate method as reference, but I wrote this myself.
    //If any item entry miss the partnumber or quantity element (is null),
    // also if partnumber/quantity is not positive int, then it's invalid.
    public boolean validate(){
        if(partnumber == null || quantity == null)
            return false;
        if(partnumber.isEmpty() || quantity.isEmpty())
            return false;
        try{
            int number = Integer.parseInt(partnumber);
            if(number <= 0)
                return false;
            number = Integer.parseInt(quantity);
            if(number <= 0)
                return false;
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    //use database interface to validate partnumber of this item
    //not same as validate()
    public boolean validatePartnumber(DatabaseInterface db){
        if(validate())
            return db.isPartnumberValid(partnumber);
        else
            return false;
    }

}
