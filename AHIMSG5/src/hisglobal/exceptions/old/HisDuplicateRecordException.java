package hisglobal.exceptions.old;

import hisglobal.exceptions.HISException;

import java.util.Map;

public class HisDuplicateRecordException extends HISException
{

	private Map essentialMap;

	public Map getEssentialMap()
	{
		return essentialMap;
	}

	public void setEssentialMap(Map essentialMap)
	{
		this.essentialMap = essentialMap;
	}

	public HisDuplicateRecordException(String str)
	{

		super(str);
	}

	public HisDuplicateRecordException(String str, Map _mp)
	{
		super(str);
		this.essentialMap = _mp;
	}

	public HisDuplicateRecordException()
	{
		super("Duplicate Record");
	}
}
