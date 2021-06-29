

package dutyroster.masters.controller.data;



import java.util.List;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.RosterAreaCapacityMstVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;


public class RosterAreaCapacityMstDATA  extends ControllerDATA 
{
		
	public static List getRosterIdList(UserVO _userVO)
	{
		DutyRosterEssentialDelegate masterDelegate = new DutyRosterEssentialDelegate();
		return masterDelegate.getRosterIdList(_userVO);
	}
	
	public static Map getDutyAreaAndShiftsBasedOnRosterType(String _rosterId,String areaTypeCode,UserVO _userVO)
	{
		DutyRosterEssentialDelegate masterDelegate = new DutyRosterEssentialDelegate();
		return masterDelegate.getDutyAreaAndShiftsBasedOnRosterType(_rosterId,areaTypeCode,_userVO);
	}
	
	
	public static void saveRosterAreaCapacityInfo(RosterAreaCapacityMstVO _rosterAreaCapaMstVO, UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.saveRosterAreaCapacityInfo(_rosterAreaCapaMstVO, _userVO);
	}


	public static Map fetchRosterAreaCapacityInfo(RosterAreaCapacityMstVO _rosterAreaCapaMstVO,UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.fetchRosterAreaCapacityInfo(_rosterAreaCapaMstVO, _userVO);
	}
	
	public static void updateRosterAreaCapacityInfo(String areaCode,RosterAreaCapacityMstVO _rosterAreaCapaMstVO, UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.updateRosterAreaCapacityInfo(areaCode,_rosterAreaCapaMstVO, _userVO);
	}
	

	
}
