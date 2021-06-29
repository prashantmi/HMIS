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


public class MonthlyEmpRosterReportDateWiseDATA  extends ControllerDATA 
{
		
	public static List getDutyRosterCategory(UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpWiseRosterCategory(_userVO);	
	}
	
	public static List getDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getDutyRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
	}
	
	public static Map getDutyAreaAndDesignationBasedOnRosterType(String _rosterId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getDutyAreaAndDesignationBasedOnRosterType(_rosterId,_userVO);
	}
	
	
	
	public static Map getEmployeesBasedOnDutyAreaAndDesignation(String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmployeesBasedOnDutyAreaAndDesignation(  _desigId,_areaTypeCode, _areaCode, _rosterId, _userVO);
	}
	
	public static Map getMonthlyEmpRosterReportDateWise(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String printFormat,String _fromDate,String _toDate)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		return delegateObj.getMonthlyEmpRosterReportDateWise(_desigId,_year,_month,_areaTypeCode, _areaCode, _rosterId, _userVO,printFormat,_fromDate,_toDate);
	}
	
	
		
}
