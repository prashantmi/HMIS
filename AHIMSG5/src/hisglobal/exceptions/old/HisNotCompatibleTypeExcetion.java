package hisglobal.exceptions.old;

import hisglobal.exceptions.HISException;

public class HisNotCompatibleTypeExcetion extends HISException
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
