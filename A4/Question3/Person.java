public class Person
{
	public String name;

	public Person()
	{
		name = "Rob";
	}

	public boolean IsPersonRob()
	{
		return name.equals("Rob") && IsRobsAddress("Rob street", "Rob city", "Rob province", "Rob postalcode");
	}

	private boolean IsRobsAddress(String street, String city, String province, String postalCode)
	{
		return street.equals("Rob street") &&
			city.equals("Rob city") &&
			province.equals("Rob province") &&
			postalCode.equals("Rob postalcode");
	}
}