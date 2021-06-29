package hisglobal.exceptions.old;

import hisglobal.exceptions.HISException;

public class HisNotEligibleForEmgOldPatientVisitException extends HISException
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
