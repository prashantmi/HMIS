package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisOPDPatientEligibilityException extends HisException
{
	public HisOPDPatientEligibilityException()
	{
		super("Patient NOT Eligible for OPD");
	}

	public HisOPDPatientEligibilityException(String msg)
	{
		super(msg);
	}
}
