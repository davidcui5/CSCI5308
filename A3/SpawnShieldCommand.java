import java.util.ArrayList;

//This is the Command for spawning shields
// It decorates a Square with Shield
// and replaces the Square with the ShieldedSquare in the grid position
public class SpawnShieldCommand extends Command
{
	public SpawnShieldCommand(Object receiver, String[] args)
	{
		super(receiver, args);
	}

	@Override
	public void Execute()
	{
		//receiver is the singleton GameBoard
		GameBoard board = (GameBoard)receiver;
		System.out.println("Spawning shield at (" + args[0] + "," + args[1] + ")");

		//get the Square we want to decorate using its grid position x,y
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        BoardComponent square = board.GetBoard().get(y).get(x);

		//singleton factory create shieldedSquare
		IAsteroidGameFactory factory = GameBoard.Instance().GetFactory();
        BoardComponent shieldedSquare = factory.MakeShield(square);

        //replace square with shieldedSquare inside board
        ArrayList<BoardComponent> row = board.GetBoard().get(y);
        row.set(x,shieldedSquare);

        //Attach shield to Subject (note this also detaches the square and its children)
		shieldedSquare.Attach();
	}
}