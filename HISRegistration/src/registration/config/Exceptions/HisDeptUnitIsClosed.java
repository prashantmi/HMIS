package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

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
