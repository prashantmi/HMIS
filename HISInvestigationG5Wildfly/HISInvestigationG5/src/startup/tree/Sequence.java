package startup.tree;

public class Sequence 
{
	private int  i = 00;
	public int next()
	{
		return ++i;
	}
	public int getTotSequence()
	{
		return this.i;
	}
}
