package hisglobal.exceptions.old;

import hisglobal.exceptions.HISException;

public class HisNoSuchObjectException extends HISException
{

	public HisNoSuchObjectException()
	{
		super("HIS No Such Object Exception");

	}

	public HisNoSuchObjectException(String _msg)
	{
		super(_msg);
	}

}
