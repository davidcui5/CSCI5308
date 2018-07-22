public class Calculator
{
	public static int Divide(int left, int right)
	{
		MathOperation op = new DivideOperation(left, right);
		return op.GetResult();
	}

	public static int Multiply(int left, int right)
	{
		MathOperation op = new MultiplyOperation(left, right);
		return op.GetResult();
	}

	public static int Add(int left, int right)
	{
		MathOperation op = new AddOperation(left, right);
		return op.GetResult();
	}

	public static int Subtract(int left, int right)
	{
		MathOperation op = new SubtractOperation(left, right);
		return op.GetResult();
	}
}