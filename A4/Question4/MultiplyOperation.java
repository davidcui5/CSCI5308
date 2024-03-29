public class MultiplyOperation extends MathOperation
{
	public MultiplyOperation(int leftOperand, int rightOperand)
	{
		super(leftOperand, rightOperand);
	}

	@Override
	public int GetResult()
	{
		return GetLeftOperand() * GetRightOperand();
	}
}
