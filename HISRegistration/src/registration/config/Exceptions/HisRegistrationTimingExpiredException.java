package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisRegistrationTimingExpiredException extends HisException
{
	public HisRegistrationTimingExpiredException()
	{
		super("HIS Registration Timing Over");
	}

	public HisRegistrationTimingExpiredException(String _msg)
	{
		super(_msg);
	}
}
