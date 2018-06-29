import java.util.ArrayList;

public class SpawnShieldCommand extends Command
{
	public SpawnShieldCommand(Object receiver, String[] args)
	{
		super(receiver, args);
	}

	@Override
	public void Execute()
	{
		//receiver is board
		GameBoard board = (GameBoard)receiver;
		System.out.println("Spawning shield at (" + args[0] + "," + args[1] + ")");

		//get square we want to decorate
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        BoardComponent square = board.GetBoard().get(y).get(x);

        //decorate it
        BoardComponent shieldedSquare = new Shield(square);
        //replace square with shield inside board
        ArrayList<BoardComponent> row = board.GetBoard().get(y);
        row.set(x,shieldedSquare);

        square.SetParent(shieldedSquare);
	}
}