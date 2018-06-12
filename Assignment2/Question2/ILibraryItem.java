
import java.time.Duration;
import java.util.ArrayList;

public interface ILibraryItem
{
	public Duration GetPlayTime();
	public String GetAuthor();
	public String GetTitle();
	public boolean IsDigitalOnly();
	public ArrayList<String> GetCastList();
}