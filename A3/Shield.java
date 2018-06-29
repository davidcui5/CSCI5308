import java.util.ArrayList;

public class Shield extends ComponentDecorator
{

	private int shieldHealth;

	public Shield(BoardComponent decoratedSquare)
	{
		super(decoratedSquare);
		shieldHealth = 2;
	}

	@Override
	public void Update()
	{
		shieldHealth--;
		if(shieldHealth==0)
		{
		    int x=0, y=0;
			ArrayList<ArrayList<BoardComponent>> board = GameBoard.Instance().GetBoard();
			for (int i=0; i<board.size(); i++)
            {
                ArrayList<BoardComponent> row = board.get(i);
                int index = row.indexOf(this);
                if(-1 != index)
                {
                    x = index;
                    y = i;
                    break;
                }
            }
            ArrayList<BoardComponent> row = board.get(y);
			row.set(x, decoratedComponent);
		}
	}
}
