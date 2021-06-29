package hisglobal.exceptions;

public class HisRenewalCardRequired extends HisException
{

	public HisRenewalCardRequired()
	{
		super("Renewal Of Registration Card Required");
	}

	public HisRenewalCardRequired(String _msg)
	{
		super(_msg);
	}

}
