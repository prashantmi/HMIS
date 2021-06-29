package dutyroster.masters.controller.data;



import java.util.List;
import java.util.Map;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;


public class RosterCategoryMstDATA  extends ControllerDATA
{
	public static List getListOfRosterMainCategory(UserVO _UserVO)
	{
		DutyRosterEssentialDelegate essentialDelegate = new DutyRosterEssentialDelegate();
		return essentialDelegate.getListOfRosterMainCategory( _UserVO);
	}
	
	public static void saveRosterCategoryInfo(RosterCategoryMstVO _rosterCatMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.saveRosterCategoryInfo(_rosterCatMstVO, _UserVO);
	}

	public static Map fetchRosterCategoryInfo(RosterCategoryMstVO _rosterCatMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.fetchRosterCategoryInfo(_rosterCatMstVO, _UserVO);
	}

	public static void updateRosterCategoryInfo(String sRosterId,String sRosterSlno,RosterCategoryMstVO _rosterCatMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.updateRosterCategoryInfo(sRosterId,sRosterSlno,_rosterCatMstVO, _UserVO);
	}

	
}
