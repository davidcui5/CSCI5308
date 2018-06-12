import java.time.Duration;
import java.util.ArrayList;

public class Book implements ILibraryItem
{
	public Duration GetPlayTime()
	{
		return Duration.ZERO;
	}

	public String GetAuthor()
	{
		return "Hemingway";
	}

	public String GetTitle()
	{
		return "For Whom The Bell Tolls";
	}

	public boolean IsDigitalOnly()
	{
		return false;
	}

	public ArrayList<String> GetCastList()
	{
		return null;
	}
}