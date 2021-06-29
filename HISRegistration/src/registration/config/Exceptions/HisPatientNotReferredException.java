package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisPatientNotReferredException extends HisException
{
	public HisPatientNotReferredException()
	{
		super("HIS Patient not referred exception");
	}

	public HisPatientNotReferredException(String _msg)
	{
		super(_msg);
	}
}
