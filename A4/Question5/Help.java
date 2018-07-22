public class Help
{
	public String GetHelp(String command)
	{
		if (command != null && command.length() != 0)
		{
			switch (command)
			{
				case "print":
				{
					return "print -f <path> [-colour=0/1] [-two-sided=0/1]";
				}
				case "open":
				{
					return "open -f <path> [-create=0/1]";
				}
				case "close":
				{
					return "close -f <path>";
				}
			}
		}
		return ListAllCommands();
	}

	public String ListAllCommands()
	{
		return "Commands: print, open, close";
	}
}