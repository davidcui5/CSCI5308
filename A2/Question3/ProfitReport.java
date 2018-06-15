import java.util.ArrayList;

//Removed SendToEmail and SendToPrinter in ProfitReport class.
//Added a getReportData method in ProfitReport to allow other classes to access reportData.
public class ProfitReport
{
    private ArrayList<String> reportData;

    public ProfitReport()
    {
        reportData = new ArrayList<String>();
    }

    public void createReport()
    {
        reportData.add("Canada made $100000");
        reportData.add("Mexico made $1007700");
        reportData.add("Russia made $10009940");
        reportData.add("India made $10004500");
        reportData.add("China made $1045460000");
        reportData.add("Iran made $100466000");
        reportData.add("England made $1006000");
        reportData.add("Germany made $133300000");
        reportData.add("Chile made $1000400");
    }

    public ArrayList<String> getReportData()
	{
    	return reportData;
	}
}