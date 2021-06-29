
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


public class EmpWiseSelfEmpRosterReportDATA  extends ControllerDATA 
{
	
	
	public static Map getEmpWiseRosterReport(String _year,String _month,String empId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpWiseRosterReport(_year,_month,empId,_userVO);
	}
	
	public static Map getAllHospitalEssentials(UserVO _userVO)
	{    	
		return new DutyRosterEssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	
		
}
