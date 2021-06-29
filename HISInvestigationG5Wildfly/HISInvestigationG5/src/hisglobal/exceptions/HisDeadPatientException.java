package hisglobal.exceptions;

public class HisDeadPatientException extends HisException
{
	public HisDeadPatientException()
	{
		super("Patient is Dead exception");
	}

	public HisDeadPatientException(String _msg)
	{
		super(_msg);
	}
}
