package registration.config.Exceptions;

import hisglobal.exceptions.HisException;

public class HisDeptUnitNotOnDuty extends HisException
{
	public HisDeptUnitNotOnDuty()
	{
		super("HIS dept Unit Not On Duty");
	}

	public HisDeptUnitNotOnDuty(String _msg)
	{
		super(_msg);
	}

}
