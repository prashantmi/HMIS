package hisglobal.exceptions.old;

import hisglobal.exceptions.HISException;

public class HisUpdateUnsuccesfullException extends HISException
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
