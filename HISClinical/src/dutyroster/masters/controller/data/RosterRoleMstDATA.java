package dutyroster.masters.controller.data;

import hisglobal.vo.RosterRoleMstVO;
import hisglobal.vo.UserVO;
import java.util.List;
import java.util.Map;

import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;

public class RosterRoleMstDATA  
{
	public static List getDutyRoleNotIn(String rosterTypeId,UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getDutyRoleNotIn(rosterTypeId, _userVO);
	}
	public static List getDutyRole(String rosterTypeId,UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getDutyRole(rosterTypeId, _userVO);
	}

	public static List getRosterType(UserVO _userVO) {
		DutyRosterEssentialDelegate masterDelegate = new DutyRosterEssentialDelegate();
		return masterDelegate.getRosterType(_userVO);
	}

	public static void saveRosterRole(RosterRoleMstVO[] insertRosterRoleVO,
			RosterRoleMstVO[] updateRosterRoleVO, UserVO _userVO) {
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.saveRosterRole(insertRosterRoleVO,updateRosterRoleVO,_userVO);
		
	}
				
}
