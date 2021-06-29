package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisReferredRecordNotFoundException extends HisException
{
	public HisReferredRecordNotFoundException()
	{
		super("HIS Patient not referred exception");
	}

	public HisReferredRecordNotFoundException(String _msg)
	{
		super(_msg);
	}
}
