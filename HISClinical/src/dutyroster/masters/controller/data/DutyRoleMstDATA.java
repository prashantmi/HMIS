package dutyroster.masters.controller.data;

import hisglobal.vo.DutyRoleMstVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;


public class DutyRoleMstDATA  
{
	public static boolean saveDutyRole(DutyRoleMstVO dutyRoleMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.saveDutyRole(dutyRoleMstVO, _UserVO);
	}
	
	public static boolean updateDutyRole(DutyRoleMstVO dutyRoleMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.updateDutyRole(dutyRoleMstVO, _UserVO);
	}
	
	public static DutyRoleMstVO getDutyRole(DutyRoleMstVO dutyRoleMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getDutyRole(dutyRoleMstVO, _UserVO);
	}

		
}
