package dutyroster.reports.controller.data;

import hisglobal.presentation.ControllerDATA;
import java.util.List;
import java.util.Map;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;
import dutyroster.transaction.delegate.DutyRosterDelegate;


public class EmpDailyWorkReportDATA  extends ControllerDATA 
{
	
	public static List getListOfRoleBasedRosterMainCategory(UserVO _userVO)
	{	
		DutyRosterEssentialDelegate essentialDelegate = new DutyRosterEssentialDelegate();
		return essentialDelegate.getListOfRoleBasedRosterMainCategory(_userVO);
	}
	
	
	public static List getListOfRoleBasedRosterCategoryBasedOnRosterMainCategory(String _rosterMainCategory,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getListOfRoleBasedRosterCategoryBasedOnRosterMainCategory(_rosterMainCategory,_userVO);
	}
	
	public static List getListOfRoleBasedRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getListOfRoleBasedRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
	}
	
	public static List getDutyAreasBasedOnRosterType(String _rosterId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getDutyAreasBasedOnRosterType(_rosterId,_userVO);
	}
	
	public static List getListOfAllMappedEmployeesHavingUserId(String _rosterId,String _areaCode,String _areaTypeCode,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getListOfAllMappedEmployeesHavingUserId(_rosterId, _areaCode, _areaTypeCode, _userVO);
	}
	
		
	public static Map getEmpDailyWorkReport(String _rosterId,String _areaTypeCode,String _areaCode,String _empId,String _workingDate,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		return delegateObj.getEmpDailyWorkReport(_rosterId,_areaTypeCode, _areaCode,_empId,_workingDate, _userVO);
	}
	
	
		
}
