public class Calculator
{
	public static int Divide(int left, int right)
	{
		MathOperation op = new MathOperation(MathOperation.OperationType.DIVIDE, left, right);
		return op.GetResult();
	}

	public static int Multiply(int left, int right)
	{
		MathOperation op = new MathOperation(MathOperation.OperationType.MULTIPLY, left, right);
		return op.GetResult();
	}

	public static int Add(int left, int right)
	{
		MathOperation op = new MathOperation(MathOperation.OperationType.ADD, left, right);
		return op.GetResult();
	}

	public static int Subtract(int left, int right)
	{
		MathOperation op = new MathOperation(MathOperation.OperationType.SUBTRACT, left, right);
		return op.GetResult();
	}
}