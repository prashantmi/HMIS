package dutyroster.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;
import dutyroster.transaction.delegate.DutyRosterDelegate;


public class EmpRosterGenerationDATA  extends ControllerDATA 
{
		
	public static List getRosterCategoryList(UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getRosterCategoryList(_userVO);
	}
	
	public static List getEmpTypeMonthWiseDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpTypeMonthWiseDutyRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
	}
	
	public static Map getDutyAreaWithCapacityAndDesignationBasedOnRosterType(String _rosterId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getDutyAreaWithCapacityAndDesignationBasedOnRosterType(_rosterId,_userVO);
	}
	
	
	
	public static Map getEmployeesBasedOnDutyAreaAndDesignation(String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmployeesBasedOnDutyAreaAndDesignation(  _desigId,_areaTypeCode, _areaCode, _rosterId, _userVO);
	}
	
	public static Map getEmployeesWithRoster(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId, String _areaId, UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		return delegateObj.getEmployeesWithRoster(_desigId,_year,_month,_areaTypeCode, _areaCode, _rosterId, _areaId, _userVO);
	}
	
	
	public static void saveEmpDutyRoster(RosterDtlVO[] _rosterDtlVO,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		delegateObj.saveEmpDutyRoster(_rosterDtlVO,_userVO);
	}
	
	public static void saveAndModifyEmpDutyRoster(RosterDtlVO[] _rosterDtlVO,UserVO _userVO,String empListToBeUpdated,String daysListToBeUpdated,String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		delegateObj.saveAndModifyEmpDutyRoster(_rosterDtlVO,_userVO,empListToBeUpdated, daysListToBeUpdated,_year,_month,_rosterId,_areaTypeCode,_areaCode);
	}
	
	public static void generateEmpDutyRoster(RosterGenerationDtlVO _rosterGenerationDtlVO,UserVO _userVO,RosterWiseReliversDtlVO[] _reliverRosterVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		delegateObj.generateEmpDutyRoster(_rosterGenerationDtlVO,_userVO,_reliverRosterVO);
	}
	
	public static void dropEmpDutyRoster(String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode,UserVO _userVO,String empListToBeUpdated,String daysListToBeUpdated,String generatedRosterId)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		delegateObj.dropEmpDutyRoster(_year,_month,_rosterId,_areaTypeCode,_areaCode,_userVO,empListToBeUpdated,daysListToBeUpdated,generatedRosterId);
	}
	
	public static void saveEmpReliverDtl(UserVO _userVO,RosterWiseReliversDtlVO[] _reliverRosterVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		delegateObj.saveEmpReliverDtl(_userVO, _reliverRosterVO);
	}
}
