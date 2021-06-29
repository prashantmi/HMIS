package dutyroster.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterExChangeDetailVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;
import dutyroster.transaction.delegate.DutyRosterDelegate;


public class ExChangeOfDutyDATA  extends ControllerDATA 
{
		
	public static List getRosterMainCategory(UserVO _userVO)
	{
		DutyRosterEssentialDelegate essentialDelegate = new DutyRosterEssentialDelegate();
		return essentialDelegate.getListOfRosterMainCategory(_userVO);
	}
	
	public static List getRosterCategoryBasedOnRosterMainCategory(String _rosterMainCatg,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getRosterCategoryBasedOnRosterMainCategory(_rosterMainCatg,_userVO);
	}
	
	public static List getEmpList(String _rosterCatgId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpListToBeExchanged(_rosterCatgId,_userVO);
	}
	

	public static RosterDtlVO[] getEmpDutyListForExchange(String _year,String _month,String _rosterCatgId,String empId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpDutyListForExchange(_year, _month, _rosterCatgId, empId, _userVO);
	}
	
	public static RosterDtlVO[] getEmpDutyListForChange(String _year,String _month,String day,String _rosterCatgId,String empId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpDutyListForChange(_year, _month,day,_rosterCatgId, empId, _userVO);
	}
	
	public static void saveExchangeofDuty(RosterExChangeDetailVO _exchangeDtlVO,RosterDtlVO _requestedEmpCancelOldVO,RosterDtlVO _exchangeEmpCancelOldVO,RosterDtlVO _reliverEmpInsertNewVO,RosterDtlVO _exchangeEmpInsertNewVO,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		 delegateObj.saveExchangeofDuty(_exchangeDtlVO,_requestedEmpCancelOldVO,_exchangeEmpCancelOldVO,_reliverEmpInsertNewVO,_exchangeEmpInsertNewVO,_userVO);
	}
	
	public static void saveChangeofDuty(RosterExChangeDetailVO _exchangeDtlVO,RosterDtlVO _requestedEmpCancelOldVO,RosterDtlVO _changeEmpInsertNewVO,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		 delegateObj.saveChangeofDuty(_exchangeDtlVO,_requestedEmpCancelOldVO,_changeEmpInsertNewVO,_userVO);
	}
	
}
