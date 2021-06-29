package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisEpisodeClosedException extends HisException
{
	public HisEpisodeClosedException()
	{
		super("HIS Episode Closed Exception");
	}

	public HisEpisodeClosedException(String _msg)
	{
		super(_msg);
	}
}
