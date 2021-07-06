package hisglobal.exceptions;

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
