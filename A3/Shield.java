import java.util.ArrayList;

//This is the concrete implementation of ComponentDecorator
//This is a Shield decorator which decorates Square
public class Shield extends ComponentDecorator
{
	//Shield has a health
	private int shieldHealth;

	public Shield(BoardComponent decoratedSquare)
	{
		super(decoratedSquare);
		shieldHealth = 2;
	}

	//Shield overrides Update() method
	//Update has parameter x, y, which are grid positions, which allows the Shield to un-decorate itself
	// and replace itself with the Square it decorates in grid position
	//Update(x, y) is called when Asteroid impact happens
	//Update in Shield protects decoratedSquare and its children from being Updated
	//If Asteroid impact in same x,y as Shield, health--, and if health is 0,
	// shield is un-decorated, so shieldedSquare is replaced by square
	// shield is then detached from Subject, and square and its children are attached
	@Override
	public void Update(int x, int y)
	{
		if(GetX()==x && GetY()==y)
		{
			shieldHealth--;
			if (shieldHealth == 0)
			{
				ArrayList<BoardComponent> row = GameBoard.Instance().GetBoard().get(y);
				row.set(x, decoratedComponent);
				Detach();
			}
		}
	}

	//Attaching a ShieldedSquare to Subject means the Shield is attached, and the square and its children are detached
	@Override
	public void Attach()
	{
		GameBoard.Instance().GetAsteroidImpact().Attach(this);
		decoratedComponent.Detach();
	}

	//Detaching a ShieldedSquare means the shield is detached and the square and its children are re-attached
	@Override
	public void Detach()
	{
		GameBoard.Instance().GetAsteroidImpact().Detach(this);
		decoratedComponent.Attach();
	}

}
