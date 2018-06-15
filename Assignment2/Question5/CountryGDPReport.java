import java.util.ArrayList;

public class CountryGDPReport
{
	ArrayList<ICountry> countries;

	public CountryGDPReport()
	{
	    countries = new ArrayList<ICountry>();
	    countries.add(new Canada());
	    countries.add(new Mexico());
	}

	public void printCountryGDPReport()
	{
		System.out.println("GDP By Country:\n");
		for(int i=0; i<countries.size(); i++){
		    ICountry country = countries.get(i);
		    System.out.println(country.getGDPReport());
        }
	}
}