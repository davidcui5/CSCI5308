public class AddOperation extends MathOperation
{
	public AddOperation(int leftOperand, int rightOperand)
	{
		super(leftOperand, rightOperand);
	}

	@Override
	public int GetResult()
	{
		return GetLeftOperand() + GetRightOperand();
	}
}
