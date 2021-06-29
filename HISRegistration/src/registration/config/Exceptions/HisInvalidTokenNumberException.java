package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisInvalidTokenNumberException extends HisException
{

	public HisInvalidTokenNumberException()
	{
		super("HIS Invalid Token Number");
	}

	public HisInvalidTokenNumberException(String _msg)
	{
		super(_msg);
	}

}
