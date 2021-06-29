package hisglobal.exceptions;

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
