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

	//enum types are replaced by subclasses that override this method with different operations
	public abstract int GetResult();
}