import java.time.Duration;
import java.util.ArrayList;

public interface IDVD extends ILibraryItem
{
    public Duration getPlayTime();
    public ArrayList<String> getCastList();
}
