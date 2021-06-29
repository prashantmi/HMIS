package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisNotAnEmergencyCaseException extends HisException
{

	public HisNotAnEmergencyCaseException()
	{
		super("HIS Not an emergency case exception");
	}

	public HisNotAnEmergencyCaseException(String _msg)
	{
		super(_msg);
	}

}
