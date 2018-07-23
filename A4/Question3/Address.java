public class Address
{
	private String street;
	private String city;
	private String province;
	private String postalCode;

	public Address(String street, String city, String province, String postalCode)
	{
		this.street = street;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
	}

	public String GetStreet()
	{
		return street;
	}

	public String GetCity()
	{
		return city;
	}

	public String GetProvince()
	{
		return province;
	}

	public String GetPostalCode()
	{
		return postalCode;
	}

	public boolean Equals(Address address)
	{
		return street.equals(address.GetStreet()) && city.equals(address.GetCity()) &&
				province.equals(address.GetProvince()) && postalCode.equals(address.GetPostalCode());
	}
}
