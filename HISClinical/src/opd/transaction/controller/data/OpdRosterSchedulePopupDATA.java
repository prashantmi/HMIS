package opd.transaction.controller.data;

import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

public class OpdRosterSchedulePopupDATA extends ControllerDATA
{
	// Getting Schedule Dates Unit User Date Wise
	public static List<Entry> getOpdRosterSchedule(String _deptUnitCode, String _userId, String _date, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getOpdRosterSchedule(_deptUnitCode, _userId, _date, _userVO);
	}
}
