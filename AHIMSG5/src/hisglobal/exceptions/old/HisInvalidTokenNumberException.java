package hisglobal.exceptions.old;

import hisglobal.exceptions.HISException;

public class HisInvalidTokenNumberException extends HISException
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
