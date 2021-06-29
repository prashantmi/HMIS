
package dutyroster.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import java.util.List;
import java.util.Map;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;
import dutyroster.transaction.delegate.DutyRosterDelegate;


public class DateWiseEmployeeDutyRosterDATA  extends ControllerDATA 
{
		
	public static List getDutyRosterCategory(UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpWiseRosterCategory(_userVO);
	}
	
	public static List getEmpTypeDateWiseDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpTypeDateWiseDutyRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
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
	
	public static Map getDateWiseEmployeesRoster(String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		return delegateObj.getDateWiseEmployeesRoster(_desigId,_areaTypeCode, _areaCode, _rosterId, _userVO);
	}
	
	public static Map getDateWiseEmployeesRosterModify(String _generatedRosterId,String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		return delegateObj.getDateWiseEmployeesRosterModify(_generatedRosterId,_desigId,_areaTypeCode, _areaCode, _rosterId, _userVO);
	}
	
	public static void saveEmpDutyRosterDateWise(RosterDtlVO[] _rosterDtlVO,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		delegateObj.saveEmpDutyRosterDateWise(_rosterDtlVO,_userVO);
	}
	
	
	public static void saveAndModifyEmpDutyRosterDateWise(RosterDtlVO[] _rosterDtlVO,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		delegateObj.saveAndModifyEmpDutyRosterDateWise(_rosterDtlVO,_userVO);
	}

	public static void generateEmpDutyRosterDateWise(RosterGenerationDtlVO _rosterGenerationDtlVO,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		delegateObj.generateEmpDutyRosterDateWise(_rosterGenerationDtlVO,_userVO);
	}
}
