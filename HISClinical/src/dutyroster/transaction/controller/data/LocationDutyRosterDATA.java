
package dutyroster.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import java.util.List;
import java.util.Map;

import hisglobal.vo.BlockwiseRosterDtlVO;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;
import dutyroster.transaction.delegate.DutyRosterDelegate;


public class LocationDutyRosterDATA  extends ControllerDATA 
{
		
	public static List getRosterAndAreaTypeListHavingRosterModeLocation(UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getRosterAndAreaTypeListHavingRosterModeLocation(_userVO);
	}
	
	public static Map getDutyAreaWithCapacityAndDesignationBasedOnRosterType(String _rosterId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getDutyAreaWithCapacityAndDesignationBasedOnRosterType(_rosterId,_userVO);
	}
	
	
	
	public static Map getLocationWiseRosterEssentials(String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getLocationWiseRosterEssentials( _areaTypeCode, _areaCode, _rosterId, _userVO);
	}
	
	public static Map fetchLocationRosterAreaWise(String _startDate,String _endDate,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		return delegateObj.fetchLocationRosterAreaWise( _startDate,_endDate,_areaTypeCode, _areaCode, _rosterId, _userVO);
	}
	
	
	public static void saveLocationDutyRoster(BlockwiseRosterDtlVO[] _blockWiseRosterVO,UserVO _userVO,String modifyStatus,String startDateTimeOld,String endDateTimeOld,String fromDateCheck,String toDateCheck)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		delegateObj.saveLocationDutyRoster(_blockWiseRosterVO,_userVO,modifyStatus, startDateTimeOld, endDateTimeOld, fromDateCheck, toDateCheck);
	}
	
	
	public static void generateLocationWiseRoster(String _startDate,String _endDate,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		 delegateObj.generateLocationWiseRoster( _startDate,_endDate,_areaTypeCode, _areaCode, _rosterId, _userVO);
	}

	
}
