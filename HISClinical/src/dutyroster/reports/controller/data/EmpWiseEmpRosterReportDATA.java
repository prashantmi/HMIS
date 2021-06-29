
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


public class EmpWiseEmpRosterReportDATA  extends ControllerDATA 
{
		
	public static List getDesignationList(UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getDesignationList(_userVO);
	}
	
	public static List getEmpListBasedOnDesignation(String desigId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpListBasedOnDesignation(desigId,_userVO);
	}
	
	public static List getDutyAreaBasedOnEmployee(String _empId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getDutyAreaBasedOnEmployee(_empId,_userVO);
	}
	
	public static List getRosterListBasedOnAreaEmployee(String _empId,String _areaCode,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getRosterListBasedOnAreaEmployee(_empId,_areaCode,_userVO);
	}
	
	
	
	public static Map getEmpWiseRosterReport(String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode,String _empId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpRosterWiseEmpReport(_year,_month,_rosterId,_areaTypeCode,_areaCode,_empId, _userVO);
	}
	
	public static Map getAllHospitalEssentials(UserVO _userVO)
	{    	
		return new DutyRosterEssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	public static List getDesignationBasedOnHospital(String hospitalCode,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getDesignationBasedOnHospital(hospitalCode,_userVO);
	}
		
}
