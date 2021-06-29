package hisglobal.exceptions.old;

import hisglobal.exceptions.HISException;

public class HisInsertNotAllowedException extends HISException{

	public HisInsertNotAllowedException(String str)
	{

		super(str);
	}
	
	public HisInsertNotAllowedException()
	{
		super("Insertion Not Allowed");
	}

}
