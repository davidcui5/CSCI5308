public class Canada implements ICountry
{
	public String getAgriculture()
	{
		return "$50000000 CAD";
	}

	public String getManufacturing()
	{
		return "$100000 CAD";
	}

	public String getGDPReport(){
		return "- Canada:\n" +
				"   - Agriculture: " + getAgriculture() +
				"   - Manufacturing: " + getManufacturing();
	}
}