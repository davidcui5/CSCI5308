public class Person
{
	//introduced parameter object: Address
	private String name;
	private Address address;

	public Person()
	{
		name = "Rob";
		address = new Address("Rob street","Rob city","Rob province","Rob postalcode");
	}

	public boolean IsPersonRob()
	{
		return name.equals("Rob") && IsRobsAddress(this.address);
	}

	private boolean IsRobsAddress(Address personsAddress)
	{
		Address robsAddress = new Address("Rob street","Rob city","Rob province","Rob postalcode");
		return personsAddress.Equals(robsAddress);
	}
}