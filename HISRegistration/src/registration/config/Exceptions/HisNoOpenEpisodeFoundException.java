package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisNoOpenEpisodeFoundException extends HisException
{
	public HisNoOpenEpisodeFoundException()
	{
		super("HIS No open episode found");
	}

	public HisNoOpenEpisodeFoundException(String _msg)
	{
		super(_msg);
	}
}
