package hisglobal.exceptions;

public class HisDeadDonorException extends HisException
{
	public HisDeadDonorException()
	{
		super("Donor is Dead ");
	}

	public HisDeadDonorException(String _msg)
	{
		super(_msg);
	}
}
