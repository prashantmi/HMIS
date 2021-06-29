package dutyroster.masters.controller.data;

import hisglobal.vo.DutyBlockMstVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;


public class DutyBlockMstDATA  
{
	public static boolean saveDutyBlock(DutyBlockMstVO dutyBlockMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.saveDutyBlock(dutyBlockMstVO, _UserVO);
	}
	
	public static boolean updateDutyBlock(DutyBlockMstVO dutyBlockMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.updateDutyBlock(dutyBlockMstVO, _UserVO);
	}
	
	public static DutyBlockMstVO getDutyBlock(DutyBlockMstVO dutyBlockMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getDutyBlock(dutyBlockMstVO, _UserVO);
	}

		
}
