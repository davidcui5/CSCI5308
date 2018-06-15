public class Mexico implements ICountry
{
	public String getAgriculture()
	{
		return "$50000000 MXN";
	}

	public String getTourism()
	{
		return "$100000 MXN";
	}

	public String getGDPReport(){
		return "- Mexico:\n" +
				"   - Agriculture: " + getAgriculture() +
				"   - Tourism: " + getTourism();
	}
}