package hisglobal.exceptions;

public class HisNotAnIPDcaseException extends HisException
{
	public HisNotAnIPDcaseException()
	{
		super("HIS Not an IPD case exception");
	}

	public HisNotAnIPDcaseException(String _msg)
	{
		super(_msg);
	}
}
