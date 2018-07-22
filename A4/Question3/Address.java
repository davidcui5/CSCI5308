public class Address {
	private String street;
	private String city;
	private String province;
	private String postalCode;

	public Address(String street, String city, String province, String postalCode){
		this.street = street;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getProvince() {
		return province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public boolean equals(Address address) {
		return this.street.equals(address.getStreet()) && this.city.equals(address.getCity()) &&
				this.province.equals(address.getProvince()) && this.postalCode.equals(address.getPostalCode());
	}
}
