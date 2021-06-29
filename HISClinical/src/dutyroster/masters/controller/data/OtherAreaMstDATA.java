package dutyroster.masters.controller.data;



import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DutyRosterOtherAreaMstVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterMasterDelegate; 


public class OtherAreaMstDATA  extends ControllerDATA
{
	public static void saveOtherAreaInfo(DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.saveOtherAreaInfo(_otherAreaMstVO, _UserVO);
	}

	public static DutyRosterOtherAreaMstVO fetchRosterCategoryInfo(DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		return masterDelegate.fetchOtherAreaInfo(_otherAreaMstVO, _UserVO);
	}

	public static void updateRosterCategoryInfo(String sRosterId,String sRosterSlno,DutyRosterOtherAreaMstVO _otherAreaMstVO, UserVO _UserVO)
	{
		DutyRosterMasterDelegate masterDelegate = new DutyRosterMasterDelegate();
		masterDelegate.updateOtherAreaInfo(sRosterId,sRosterSlno,_otherAreaMstVO, _UserVO);
	}

	
}
