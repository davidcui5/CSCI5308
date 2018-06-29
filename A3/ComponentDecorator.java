//This is the abstract class for Decorator pattern for BoardComponent
// This provides default implementations for concrete decorators

public abstract class ComponentDecorator extends BoardComponent
{

	//the component being decorated
	protected BoardComponent decoratedComponent;

	//constructor
	public ComponentDecorator(BoardComponent decoratedComponent)
	{
		super(decoratedComponent.GetX(),decoratedComponent.GetY());
		this.decoratedComponent = decoratedComponent;
	}

	//default: calls BoardComponent's Operation
	@Override
	public void Operation()
	{
        decoratedComponent.Operation();
	}

	//default: calls BoardComponent's Update
	@Override
	public void Update(int x, int y)
	{
		decoratedComponent.Update(x, y);
	}

	//default: calls BoardComponent's Add
	@Override
	public void Add(BoardComponent child)
	{
        decoratedComponent.Add(child);
	}

	//default: calls BoardComponent's Remove
	@Override
	public void Remove(BoardComponent child)
	{
        decoratedComponent.Remove(child);
	}

	@Override
	public void Attach()
	{
		decoratedComponent.Attach();
	}

	@Override
	public void Detach()
	{
		decoratedComponent.Detach();
	}
}
