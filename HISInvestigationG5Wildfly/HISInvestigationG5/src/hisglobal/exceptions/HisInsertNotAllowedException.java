package hisglobal.exceptions;

public class HisInsertNotAllowedException extends HisException{

	public HisInsertNotAllowedException(String str)
	{

		super(str);
	}
	
	public HisInsertNotAllowedException()
	{
		super("Insertion Not Allowed");
	}

}
