//The Command pattern for Spawning Buildings, MakeCommand is implemented in AsteroidGameFactory.java

public class SpawnBuildingCommand extends Command
{
	public SpawnBuildingCommand(Object receiver, String[] args)
	{
		super(receiver, args);
	}

	@Override
	public void Execute()
	{
		//receiver is the square to spawn Building
		Square square = (Square) receiver;

		//singleton factory, spawn building, add to square, and attach to Subject
		IAsteroidGameFactory factory = GameBoard.Instance().GetFactory();
		System.out.println("Spawning building at (" + args[0] + "," + args[1] + ")");
		BoardComponent building = factory.MakeBuilding(square.GetX(), square.GetY());
		square.Add(building);
		building.Attach();

		//increment building count
		GameBoard.Instance().IncrementBuildingCount();
	}
}
