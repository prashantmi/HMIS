package hisglobal.exceptions.old;

import hisglobal.exceptions.HISException;

public class HisMandatoryFieldEmptyException extends HISException
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
