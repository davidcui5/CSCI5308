import java.io.PrintWriter;

public class PiggyBankTextSaver {
    //save PiggyBank to a file with an encoding.
    public static void save(PiggyBank bank, String fileName, String encoding)
    {
        try
        {
            PrintWriter writer = new PrintWriter(fileName, encoding);
            writer.println(Integer.toString(bank.getNumPennies()));
            writer.println(Integer.toString(bank.getNumDimes()));
            writer.println(Integer.toString(bank.getNumNickels()));
            writer.println(Integer.toString(bank.getNumQuarters()));
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("I am a bad programmer that hid an exception.");
        }
    }
}
