package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisMlcAlreadyExistException extends HisException
{
	public HisMlcAlreadyExistException()
	{
		super("HisMlcAlreadyExistException");
	}

	public HisMlcAlreadyExistException(String _msg)
	{
		super(_msg);
	}

}
