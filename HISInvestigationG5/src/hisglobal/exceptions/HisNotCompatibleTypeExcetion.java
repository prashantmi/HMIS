package hisglobal.exceptions;

public class HisNotCompatibleTypeExcetion extends HisException
{
	public HisNotCompatibleTypeExcetion()
	{
		super("Hospital Code and Icd Code cannot map to eah other");
	}

	public HisNotCompatibleTypeExcetion(String _msg)
	{
		super(_msg);
	}

}
