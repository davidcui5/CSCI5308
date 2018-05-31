package dal.csci5308;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="dealer")
@XmlType(propOrder={"dealerid","dealeraccesskey"})
public class Dealer {
    /*
    <!-- The dealer submitting the order -->
	<dealer>
		<!-- The dealer ID, identifies the dealer. Must validate that the dealer is a known dealer. -->
		<dealerid>XXX-1234-ABCD-1234</dealerid>
		<!-- The dealer access key, used by the security class to authenticate the dealer. -->
		<dealeraccesskey>kkklas8882kk23nllfjj88290</dealeraccesskey>
	</dealer>
	<!-- A list of items order by the dealer. -->
    */

    private String dealerid;
    private String dealeraccesskey;

    @XmlElement(name="dealerid")
    public void setDealerid(String dealerid) {
        this.dealerid = dealerid;
    }
    public String getDealerid(){
        return dealerid;
    }

    @XmlElement(name="dealeraccesskey")
    public void setDealeraccesskey(String dealeraccesskey){
        this.dealeraccesskey = dealeraccesskey;
    }
    public String getDealeraccesskey(){
        return dealeraccesskey;
    }

    //validate Dealer, i.e. check for empty string or null string, not the same as authenticating
    public boolean validate(){
        if(dealerid == null || dealerid.isEmpty())
            return false;
        if(dealeraccesskey == null || dealeraccesskey.isEmpty())
            return false;

        return true;
    }

    //authorize the dealer, or authenticate dealer, whatever the word is
    //call this method in other classes, by DealerObjectName.isDealerAuthorized(Security interface object);
    public boolean isDealerAuthorized(Security security){
        if(validate()){
            return security.IsDealerAuthorized(dealerid,dealeraccesskey);
        }
        else
            return false;
    }
}
