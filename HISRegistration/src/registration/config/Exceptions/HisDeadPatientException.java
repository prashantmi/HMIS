package registration.config.Exceptions;
import hisglobal.exceptions.HisException;

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
