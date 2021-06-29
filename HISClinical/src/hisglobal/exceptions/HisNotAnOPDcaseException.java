package hisglobal.exceptions;

public class HisNotAnOPDcaseException extends HisException
{
	public HisNotAnOPDcaseException()
	{
		super("HIS Not an opd case exception");
	}

	public HisNotAnOPDcaseException(String _msg)
	{
		super(_msg);
	}

}
