package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisNotEligibleForEmgOldPatientVisitException extends HisException
{
	public HisNotEligibleForEmgOldPatientVisitException()
	{
		super("HIS NotEligible for emergency visit Exception");
	}

	public HisNotEligibleForEmgOldPatientVisitException(String _msg)
	{
		super(_msg);
	}

}
