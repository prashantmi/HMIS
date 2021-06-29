package hisglobal.exceptions;

public class HisRenewalRequiredException extends HisException
{

	public HisRenewalRequiredException()
	{
		super("HIS Renewal Required Exception");
	}

	public HisRenewalRequiredException(String _msg)
	{
		super(_msg);
	}

}
