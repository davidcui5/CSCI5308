package dal.csci5308;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="item")
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

    //Validate item entry, I used Robert's Item Class' Validate method as reference, but I still wrote this myself.
    //If any item entry lack the partnumber or quantity element, also if partnumber/quantity is not positive int,
    //then it's invalid.
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
