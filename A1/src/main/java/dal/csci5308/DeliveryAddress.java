package dal.csci5308;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="deliveryaddress")
@XmlType(propOrder={"name","street","city","province","postalCode"})
public class DeliveryAddress {
    //this is the DeliveryAddress in PARTMANAGER, I separated it to here for better structure,
    //also changed public fields to private
    /*
    <!-- The delivery address to send the parts to. -->
	<deliveryaddress>
		<!-- The name field can be blank. -->
        <name>Mrs. Jane Smith</name>
		<!-- The street is required. -->
		<street>35 Streetname</street>
		<!-- The city is required. -->
		<city>Halifax</city>
		<!-- The province is required. -->
		<province>NS</province>
		<!-- The postal code is required. -->
		<postalcode>B2T1A4</postalcode>
	</deliveryaddress>
	*/

    private String name;
    private String street;
    private String city;
    private String province;
    private String postalCode;

    @XmlElement(name="name")
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    @XmlElement(name="street")
    public void setStreet(String street){
        this.street = street;
    }
    public String getStreet(){
        return street;
    }

    @XmlElement(name="city")
    public void setCity(String city){
        this.city = city;
    }
    public String  getCity(){
        return city;
    }

    @XmlElement(name="province")
    public void setProvince(String province){
        this.province = province;
    }
    public String getProvince(){
        return province;
    }

    @XmlElement(name="postalcode")
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    public String getPostalCode(){
        return postalCode;
    }

    //Validate delivery address:
    //If any of name, street, city, province, postal code is null (element not there), it's invalid.
    //If street, city, province, postal code is empty string, it's invalid.
    //name can be empty.
    public boolean validate(){
        if(name == null || street == null || city == null || province == null || postalCode == null)
            return false;
        if(street.isEmpty() || city.isEmpty() || province.isEmpty() || postalCode.isEmpty())
            return false;
        return true;
    }

}

