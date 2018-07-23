public abstract class MathOperation
{
	private int leftOperand;
	private int rightOperand;

	public MathOperation(int leftOperand, int rightOperand)
	{
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public int GetLeftOperand()
	{
		return leftOperand;
	}

	public int GetRightOperand()
	{
		return rightOperand;
	}

	//operation enum types are replaced by subclasses doing different operations
	//subclasses override this for different operations
	public abstract int GetResult();
}