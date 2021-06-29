package opd.master.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;
import hisglobal.vo.ProfileGroupDtlVO;

public class ProfileGroupDetailDATA extends ControllerDATA{

	public static Map fetchProfileGroupDetailEssentials(UserVO _userVO){			
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchProfileGroupDetailEssentials(_userVO);		
	}
	
	public static void saveProfileGroupAccessPrivDetail(List<ProfileGroupDtlVO> lstProfileAccesses, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveProfileGroupAccessPrivDetail(lstProfileAccesses,userVO);
	}
	
	public static List<ProfileGroupDtlVO> fetchProfileGroupDetailAccessModify(ProfileGroupDtlVO profileGroupDetailVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchProfileGroupDetailAccessModify(profileGroupDetailVO,userVO);
	}
	
	public static void modifySave(List<ProfileGroupDtlVO> lstProfileAccesses,ProfileGroupDtlVO profileGroupDtlVO, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.modifySaveProfileGroupDetail(lstProfileAccesses,profileGroupDtlVO,userVO);
	}
	
	public static List<UserVO> getSearchUsersForProfileAccessPrivil(String _mode, String _str, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getSearchUsersForProfileAccessPrivil(_mode, _str, _userVO);
	}
}
