package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisMandatoryFieldEmptyException extends HisException
{

	public HisMandatoryFieldEmptyException()
	{
		super("HIS Mandatory Field Empty Exception");
	}

	public HisMandatoryFieldEmptyException(String _msg)
	{
		super(_msg);
	}

}
