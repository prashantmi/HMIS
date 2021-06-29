package hisglobal.exceptions;

public class HisPermanentUnfitDonorException extends HisException
{
	public HisPermanentUnfitDonorException()
	{
		super("Donor is Permanently Unfit ");
	}

	public HisPermanentUnfitDonorException(String _msg)
	{
		super(_msg);
	}
}
