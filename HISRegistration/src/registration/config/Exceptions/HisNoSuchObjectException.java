package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisNoSuchObjectException extends HisException
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
