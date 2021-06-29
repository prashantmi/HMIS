package hisglobal.exceptions;

public class HisDeptUnitIsClosed extends HisException
{
	public HisDeptUnitIsClosed()
	{
		super("HIS Dept Unit Is Closed");
	}

	public HisDeptUnitIsClosed(String _msg)
	{
		super(_msg);
	}

}
