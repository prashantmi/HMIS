package dutyroster.transaction.controller.data;

import java.util.List;
import java.util.Map;

import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.transaction.delegate.DutyRosterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.RosterExChangeDetailVO;
import hisglobal.vo.UserVO;

public class NurseRoleDetailDATA extends ControllerDATA
{
	public static Map getNurseRoleDetail(UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getNurseRoleDetail(_userVO);
	}
	
	public static List getRosterDetail(String rosterCatId,String dutyAreaCode,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		return delegateObj.getRosterDetail(rosterCatId,dutyAreaCode,_userVO);
	}
	
	
}
