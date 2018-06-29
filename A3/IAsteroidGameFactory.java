import java.util.ArrayList;

// This is the abstract factory requirement.
// It needs to create their squares, buildings, shields and asteroids.
public interface IAsteroidGameFactory
{
	//MakeSquare now requires x, y grid position
	public BoardComponent MakeSquare(int x, int y);
	//Added MakeShield to Factory, because we use AbstractFactory pattern, so Factory should do object creation
	public BoardComponent MakeShield(BoardComponent squareToDecorate);
	//now requires x, y grid position
	public BoardComponent MakeBuilding(int x, int y);
	//now requires x, y grid position
	public Asteroid MakeAsteroid(int x, int y, int height);

	public ArrayList<ArrayList<BoardComponent>> MakeBoard(int height, int width);
	public Command MakeCommand(String commandLine);
}