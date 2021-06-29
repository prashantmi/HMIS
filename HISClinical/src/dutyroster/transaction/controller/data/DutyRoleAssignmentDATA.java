package dutyroster.transaction.controller.data;

import java.util.List;
import java.util.Map;


import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.transaction.delegate.DutyRosterDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DutyRoleDetailVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;

public class DutyRoleAssignmentDATA extends ControllerDATA {

	public static List getRosterForRoleAssignment(UserVO _userVO) {
		DutyRosterEssentialDelegate delegate=new DutyRosterEssentialDelegate();
		return delegate.getRosterForRoleAssignment(_userVO);
	}

	public static Map getShiftAndAreaForRoster(String _rosterTypeID, UserVO _userVO) {
		DutyRosterEssentialDelegate delegate=new DutyRosterEssentialDelegate();
		return delegate.getShiftAndAreaForRoster(_rosterTypeID,_userVO);
	}

	public static Map getEmployeeMapAndRole(RosterDtlVO _rosterDtlVO, UserVO _userVO) {
		DutyRosterEssentialDelegate delegate=new DutyRosterEssentialDelegate();
		return delegate.getEmployeeMapAndRole(_rosterDtlVO,_userVO);
	}

	public static void saveEmployeeRoleDetail(DutyRoleDetailVO[] _dutyRoleDetailVO,RosterDtlVO[] _insertRosterDtlVO,RosterDtlVO[] _updateRosterDtlVO, UserVO _userVO,String mode) {
		DutyRosterDelegate delegate=new DutyRosterDelegate();
		delegate.saveEmployeeRoleDetail(_dutyRoleDetailVO,_insertRosterDtlVO,_updateRosterDtlVO,_userVO,mode);
		
	}

}
