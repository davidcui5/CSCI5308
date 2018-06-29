public abstract class ComponentDecorator extends BoardComponent
{

	protected BoardComponent decoratedComponent;

	public ComponentDecorator(BoardComponent decoratedComponent)
	{
		this.decoratedComponent = decoratedComponent;
	}

	@Override
	public void Operation()
	{
        decoratedComponent.Operation();
	}

	@Override
	public void Update()
	{
		decoratedComponent.Update();
	}

	@Override
	public void Add(BoardComponent child)
	{
        decoratedComponent.Add(child);
	}

	@Override
	public void Remove(BoardComponent child)
	{
        decoratedComponent.Remove(child);
	}
}
