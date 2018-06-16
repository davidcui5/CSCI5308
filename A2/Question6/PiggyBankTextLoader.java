import java.io.FileReader;
import java.util.Scanner;

public class PiggyBankTextLoader
{
    //load PiggyBank from file, returns it, returns null if something is wrong.
    public static PiggyBank load(String fileName)
    {
        try
        {
            PiggyBank bank = new PiggyBank();
            Scanner in = new Scanner(new FileReader(fileName));
            bank.setNumPennies(Integer.parseInt(in.next()));
            bank.setNumDimes(Integer.parseInt(in.next()));
            bank.setNumNickels(Integer.parseInt(in.next()));
            bank.setNumQuarters(Integer.parseInt(in.next()));
            return bank;
        }
        catch (Exception e)
        {
            System.out.println("I am a bad programmer that hid an exception.");
            return null;
        }
    }
}
