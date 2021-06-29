package hisglobal.exceptions;

public class HisNotEligibleForMLCException extends HisException
{
	public HisNotEligibleForMLCException()
	{
		super("Not Eligible For MLC ");
	}

	public HisNotEligibleForMLCException(String _msg)
	{
		super(_msg);
	}

}
