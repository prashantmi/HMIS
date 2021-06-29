
package dutyroster.masters.controller.data;



import java.util.List;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;


public class DutyAreaEmployeeMstDATA  extends ControllerDATA 
{
		
	public static List getEmployeeAreaEssentials(UserVO _userVO)
	{
		DutyRosterEssentialDelegate masterDelegate = new DutyRosterEssentialDelegate();
		return masterDelegate.getAllDutyAreaTypeList(_userVO);
	}
	
	public static List getDutyAreaBasedOnDutyAreaType(String areaTypeCode,UserVO _userVO)
	{
		DutyRosterEssentialDelegate masterDelegate = new DutyRosterEssentialDelegate();
		return masterDelegate.getDutyAreaBasedOnDutyAreaType(areaTypeCode,_userVO);
	}
	
	public static Map getRoleAndDesignation(UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getRoleAndDesignation(_userVO);
	}
	
	public static List getLeftEmployeesBasedOnDesignationAndArea(String empDesg,DutyRosterAreaEmployeeVO _dutyAreaEmpVO,UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getLeftEmployeesBasedOnDesignationAndArea(empDesg,_dutyAreaEmpVO,_userVO);
	}
	public static List getRightEmployeesBasedOnDesignationAndArea(String empDesg,DutyRosterAreaEmployeeVO _dutyAreaEmpVO,UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.getRightEmployeesBasedOnDesignationAndArea(empDesg,_dutyAreaEmpVO,_userVO);
	}
	public static void saveAndModifyDutyAreaEmpInfo(DutyRosterAreaEmployeeVO[] _addDutyAreaEmpVO,DutyRosterAreaEmployeeVO[] _deleteDutyAreaEmpVO, UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.saveAndModifyDutyAreaEmpInfo(_addDutyAreaEmpVO,_deleteDutyAreaEmpVO, _userVO);
	}



	
}
