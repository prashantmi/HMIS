
package dutyroster.masters.controller.data;



import java.util.List;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.DutyRosterPrintPropertiesVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;


public class RosterPrintMstDATA  extends ControllerDATA 
{
		
	public static List getRosterCategory(UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getDutyRosterCategory(_userVO);
	}
	
	public static List getRosterTypeBasedOnRosterCategory(String rosterCategory,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getDutyRostersOnTheBasisOfRosterCategory(rosterCategory,_userVO);
	}
	
	
	public static DutyRosterPrintPropertiesVO[] getRosterPrintDetailsBasedOnRosterType(String rosterType,UserVO _userVO)
	{
		DutyRosterMasterDelegate delegateObj = new DutyRosterMasterDelegate();
		return delegateObj.getRosterPrintDetailsBasedOnRosterType(rosterType,_userVO);
	}
	
	//This Function saves new record only 
	public static void saveRosterPrintMstInfo(DutyRosterPrintPropertiesVO[] _rosterPrintVO, UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.saveRosterPrintMstInfo(_rosterPrintVO, _userVO);
	}

	//This Function saves new records and modifes old records by making them isvalid=0   as Deleted 
	
	public static void saveAndModifyRosterPrintMstInfo(DutyRosterPrintPropertiesVO[] _rosterPrintVO, UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.saveAndModifyRosterPrintMstInfo(_rosterPrintVO, _userVO);
	}
	
	
	//This Function Changes the display order of record only 
	public static void changeDisplayOrder(DutyRosterPrintPropertiesVO[] _rosterPrintVO, UserVO _userVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.changeDisplayOrder(_rosterPrintVO, _userVO);
	}
	
}
