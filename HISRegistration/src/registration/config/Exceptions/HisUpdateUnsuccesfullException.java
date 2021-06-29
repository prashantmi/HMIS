package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisUpdateUnsuccesfullException extends HisException
{
	public HisUpdateUnsuccesfullException(String str)
	{
		super(str);
	}

	public HisUpdateUnsuccesfullException()
	{
		super("His Update Unsuccesfull");
	}

}
