
package dutyroster.masters.controller.data;



import java.util.List;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DutyRosterShiftMasterVO;
import hisglobal.vo.DutyRosterShiftTimingsMasterVO;
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.ShiftMasterVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;


public class ShiftTypeMstDATA  extends ControllerDATA
{
		
	public static List getShiftTypes(UserVO _UserVO)
	{
		DutyRosterEssentialDelegate masterDelegate = new DutyRosterEssentialDelegate();
		return masterDelegate.getShiftTypes(_UserVO);
	}
	
	
	public static void saveShiftTypeInfo(DutyRosterShiftMasterVO _shiftMstVO, UserVO _UserVO,DutyRosterShiftTimingsMasterVO[] _shiftTimingsMstVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.saveShiftTypeInfo(_shiftMstVO, _UserVO, _shiftTimingsMstVO);
	}

	public static Map fetchShiftTypeInfo(DutyRosterShiftMasterVO _shiftMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.fetchShiftTypeInfo(_shiftMstVO, _UserVO);
	}

	public static void updateShiftTypeInfo(String shiftCode,String serialNo,DutyRosterShiftMasterVO _shiftMstVO, UserVO _UserVO,DutyRosterShiftTimingsMasterVO[] _shiftTimingsMstVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.updateShiftTypeInfo(shiftCode,serialNo,_shiftMstVO, _UserVO,_shiftTimingsMstVO);
	}

	
}
