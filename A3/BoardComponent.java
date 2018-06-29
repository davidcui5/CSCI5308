// This is the base Component abstract for the game's Composite pattern.
public abstract class BoardComponent
{
	protected BoardComponent parent;
	//Changed so BoardComponent keep their grid position x, y
	private int x;
	private int y;

	public BoardComponent(int x, int y)
	{
		parent = null;
		this.x = x;
		this.y = y;
	}
	
	public abstract void Operation();
	//Added Update() which is required for Observer pattern
	public abstract void Update(int x, int y);
	public abstract void Add(BoardComponent child);
	public abstract void Remove(BoardComponent child);
	//Attach and Detach methods for BoardComponent to Attach and Detach to Subject
	public abstract void Attach();
	public abstract void Detach();

	public int GetX()
	{
		return x;
	}

	public int GetY()
	{
		return y;
	}
	
	public void SetParent(BoardComponent parent)
	{
		this.parent = parent;
	}
}