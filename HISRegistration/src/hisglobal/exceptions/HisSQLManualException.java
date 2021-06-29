package hisglobal.exceptions;

public class HisSQLManualException extends HisException
{
	public HisSQLManualException(String str)
	{
		super(str);
	}

	public HisSQLManualException()
	{
		super("His Manual-SQL Exception");
	}

}
