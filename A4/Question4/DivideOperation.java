public class DivideOperation extends MathOperation
{
	public DivideOperation(int leftOperand, int rightOperand)
	{
		super(leftOperand, rightOperand);
	}

	@Override
	public int GetResult()
	{
		return GetLeftOperand() / GetRightOperand();
	}
}
