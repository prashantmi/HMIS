package dutyroster.masters.controller.data;

import hisglobal.vo.RosterShiftMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import dutyroster.masters.delegate.DutyRosterMasterDelegate;

public class RosterShiftMstDATA  
{
	public static List getRosterShiftEssentials(UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getRosterShiftEssentials(_UserVO);
	}
	
	public static List getShiftsBasedOnRoster(String shiftId,String shiftType,UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getShiftsBasedOnRoster(shiftId,shiftType,_UserVO);
	}
	
	public static void saveRosterShift(RosterShiftMstVO rosterShiftVO[],UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.saveRosterShift(rosterShiftVO,_UserVO);
	}

	public static Map getRosterShift(RosterShiftMstVO rosterShiftVO,UserVO userVO) {
		
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getRosterShift(rosterShiftVO,userVO);
	}

	public static void modifyRosterShift(RosterShiftMstVO[] insertRosterShiftVO,RosterShiftMstVO[] updateRosterShiftVO, UserVO userVO) {

		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.modifyRosterShift(insertRosterShiftVO,updateRosterShiftVO,userVO);
		
	}

	
	
		
}
