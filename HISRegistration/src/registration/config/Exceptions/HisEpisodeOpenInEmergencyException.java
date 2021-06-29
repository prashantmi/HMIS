package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisEpisodeOpenInEmergencyException extends HisException
{
	public HisEpisodeOpenInEmergencyException()
	{
		super("HIS Open Episode found in Emergency");
	}

	public HisEpisodeOpenInEmergencyException(String _msg)
	{
		super(_msg);
	}
}
