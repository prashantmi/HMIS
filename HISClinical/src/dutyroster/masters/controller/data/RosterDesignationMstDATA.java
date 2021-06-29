package dutyroster.masters.controller.data;

import hisglobal.vo.RosterDesignationMstVO;
import hisglobal.vo.UserVO;
import java.util.List;
import java.util.Map;

import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;

public class RosterDesignationMstDATA  
{
	public static List getNotAssignedDesignation(String rosterTypeId,UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getNotAssignedDesignation(rosterTypeId, _userVO);
	}
	public static List getAssignedDesignation(String rosterTypeId,UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getAssignedDesignation(rosterTypeId, _userVO);
	}

	public static List getRosterType(UserVO _userVO) {
		DutyRosterEssentialDelegate masterDelegate = new DutyRosterEssentialDelegate();
		return masterDelegate.getRosterType(_userVO);
	}

	public static void saveRosterDesignation(RosterDesignationMstVO[] insertRosterRoleVO,
			RosterDesignationMstVO[] updateRosterRoleVO, UserVO _userVO) {
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.saveRosterDesignation(insertRosterRoleVO,updateRosterRoleVO,_userVO);
		
	}
				
}
