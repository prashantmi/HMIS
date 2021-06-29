package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisPatientStillUnknownException extends HisException
{
	public HisPatientStillUnknownException()
	{
		super("HIS Patient StillUnknownException");
	}

	public HisPatientStillUnknownException(String _msg)
	{
		super(_msg);
	}
}
