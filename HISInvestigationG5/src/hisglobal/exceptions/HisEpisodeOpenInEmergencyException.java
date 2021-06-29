package hisglobal.exceptions;

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
