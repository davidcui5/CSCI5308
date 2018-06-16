import java.util.ArrayList;

//Changed EmailSender class, so its sendEmail method now takes in ProfitReport, formats reportData, and send it as email.
public class EmailSender
{
    public void sendEmail(String emailAddress, String subject, ProfitReport report)
    {
        try
        {
            ArrayList<String> reportData = report.getReportData();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < reportData.size(); i++)
            {
                builder.append(reportData.get(i) + "\n");
            }
            // I'm pretending to send an email!
            System.out.println("To: " + emailAddress);
            System.out.println("Subject: " + subject);
            System.out.println("Message: \n\n" + builder.toString());
        }
        catch (Exception e)
        {
            System.out.println("Yipes internet must be down!");
        }
    }
}