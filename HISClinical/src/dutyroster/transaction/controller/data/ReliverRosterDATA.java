package dutyroster.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterExChangeDetailVO;
import hisglobal.vo.RosterReliverDtlVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;
import dutyroster.transaction.delegate.DutyRosterDelegate;


public class ReliverRosterDATA  extends ControllerDATA 
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
	
	public static List getAreaListBasedOnRosterCategory(String _rosterCatgId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getAreaListBasedOnRosterCategory(_rosterCatgId,_userVO);
	}
	

	public static Map getEmpAndShiftListBasedOnRosterCategory(String _year,String _month,String _rosterCatgId,String _areaCode,String _areaTypeCode,String _reason,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpAndShiftListBasedOnRosterCategory(_year, _month, _rosterCatgId, _areaCode,_areaTypeCode,_reason,_userVO);
	}
	
	public static RosterDtlVO[] getEmpDutyListForChange(String _year,String _month,String day,String _rosterCatgId,String empId,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpDutyListForChange(_year, _month,day,_rosterCatgId, empId, _userVO);
	}
	
	public static void saveReliverOfDuty(RosterDtlVO[] _reliverEmpInsertNewVO,RosterDtlVO[] _reliverEmpCancelOldVO,RosterDtlVO[] _requestedEmpCancelOldVO,RosterDtlVO _reliverEmpModifyDayOffOldVO,RosterReliverDtlVO[] _rosterReliverDtlVO,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		 delegateObj.saveReliverOfDuty(_reliverEmpInsertNewVO,_reliverEmpCancelOldVO,_requestedEmpCancelOldVO,_reliverEmpModifyDayOffOldVO,_rosterReliverDtlVO,_userVO);
	
	}
	
	public static void saveChangeofDuty(RosterExChangeDetailVO _exchangeDtlVO,RosterDtlVO _requestedEmpCancelOldVO,RosterDtlVO _changeEmpInsertNewVO,UserVO _userVO)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		 delegateObj.saveChangeofDuty(_exchangeDtlVO,_requestedEmpCancelOldVO,_changeEmpInsertNewVO,_userVO);
	}
	

}
