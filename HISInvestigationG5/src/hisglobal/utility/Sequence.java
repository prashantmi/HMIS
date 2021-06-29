package hisglobal.utility;

public class Sequence
{
	int i = 0;

	public Integer next()
	{
		return new Integer(++i);
	}
	
	public void reset() {
		this.i = 0;
	}
}
