
import java.util.ArrayList;

// Implementation of the Abstract Factory pattern's interface
public class AsteroidGameFactory implements IAsteroidGameFactory
{
	//MakeSquare now requires x, y grid position
	@Override
	public BoardComponent MakeSquare(int x, int y)
	{
		return new Square(x, y);
	}

	//Added
	@Override
	public BoardComponent MakeShield(BoardComponent squareToDecorate)
	{
		return new Shield(squareToDecorate);
	}

	//now requires x, y grid position
	@Override
	public BoardComponent MakeBuilding(int x, int y)
	{
		Building building = new Building(x, y);
		return building;
	}

	//now requires x, y grid position
	@Override
	public Asteroid MakeAsteroid(int x, int y, int height)
	{
		Asteroid asteroid = new Asteroid(x, y, height);
		return asteroid;
	}

	//Changed that x, y should be given to MakeSquare as arguments.
	@Override
	public ArrayList<ArrayList<BoardComponent>> MakeBoard(int height, int width)
	{
		ArrayList<ArrayList<BoardComponent>> board = new ArrayList<>();
		for (int i = 0; i < height; i++)
		{
			// Make an array for each row.
			ArrayList<BoardComponent> row = new ArrayList<>();
			// Add squares equal to the width to the row.
			for (int j = 0; j < width; j++)
			{
				//column (x) is j, row (y) is i
				BoardComponent square = MakeSquare(j,i);
				row.add(square);
				//also attach square to Subject as an Observer
				square.Attach();
			}
			board.add(row);
		}
		return board;
	}
	
	@Override
	public Command MakeCommand(String commandLine)
	{
		String commandToMake;
		String[] args = null;
		String commandLineArgs = "";
		if (commandLine.contains(" "))
		{
			commandToMake = commandLine.split(" ")[0];
			commandLineArgs = commandLine.substring(commandToMake.length() + 1);
			args = commandLineArgs.split(" ");
		}
		else
		{
			commandToMake = commandLine;
		}
		switch (commandToMake.toUpperCase())
		{
			case "INIT":
			{
				return new InitializeBoardCommand(GameBoard.Instance(), args);
			}
			case "SPAWN_ASTEROID":
			{
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				BoardComponent square = GameBoard.Instance().GetBoard().get(y).get(x);
				return new SpawnAsteroidCommand(square, args);
			}
			case "TICK":
			{
				return new TickCommand(GameBoard.Instance(), args);
			}
			case "START_GAME":
			{
				return new StartGameCommand(GameBoard.Instance(), args);
			}
			case "SPAWN_BUILDING":
			{
				// DONE:  Implement a command to spawn a building.  It should be similar
				//        to SPAWN_ASTEROID above.  The command must increment the building count!
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				BoardComponent square = GameBoard.Instance().GetBoard().get(y).get(x);
				return new SpawnBuildingCommand(square, args);
			}
			case "SPAWN_SHIELD":
			{
				// DONE:  Implement a command that uses the Decorator pattern to decorate
				//        a Square object with a shield.  The shield will have health,
				//        like a building, hard coded to 2 health in the decorator object.
				//			 While the shield is alive buildings in the square do not take damage from
				//			 asteroid impacts.  When the shield health hits 0 it is destroyed and
				//			 removed from decorating the Square.
				return new SpawnShieldCommand(GameBoard.Instance(), args);
			}
		}
		return null;
	}
}