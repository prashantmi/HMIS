package hisglobal.exceptions.old;

import hisglobal.exceptions.HISException;

public class HisInvalidSessionException extends HISException {

	public HisInvalidSessionException()
	{
		super("HIS Invalid Session");
	}

	public HisInvalidSessionException(String _msg)
	{
		super(_msg);
	}

}
