import java.util.HashMap;
import java.util.Map;

public class Help
{
	private Map<String, Command> map;

	public Help()
	{
		map = new HashMap<String, Command>();
		map.put("print", new PrintCommand());
		map.put("open", new OpenCommand());
		map.put("close", new CloseCommand());
	}

	//replaced the switch statement with polymorphism
	public String GetHelp(String command)
	{
		if(map.containsKey(command))
		{
			return map.get(command).GetHelp();
		}
		return ListAllCommands();
	}

	public String ListAllCommands()
	{
		return "Commands: print, open, close";
	}
}