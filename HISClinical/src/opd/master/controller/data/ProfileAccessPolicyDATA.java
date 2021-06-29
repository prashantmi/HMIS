package opd.master.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.UserVO;

public class ProfileAccessPolicyDATA extends ControllerDATA 
{

	public static Map fetchProfileAccessPolicyEssentials(UserVO _userVO){			
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchProfileAccessPolicyEssentials(_userVO);		
	}
	
	public static void saveProfileAccessPolicy(List<ProfileAccessPolicyVO> profileAccessPolicyVOList, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveProfileAccessPolicy(profileAccessPolicyVOList,userVO);
	}
	
	public static ProfileAccessPolicyVO fetchProfileAccessPolicyModify(ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchProfileAccessPolicyModify(profileAccessPolicyVO,userVO);
	}
	
	public static void modifySave(ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.modifySaveProfileAccessPolicy(profileAccessPolicyVO,userVO);
	}

	public static List<Entry> getDeptUnitMappedWithProfileAccess(
			ProfileAccessPolicyVO profileAccessPolicyVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getDeptUnitMappedWithProfileAccess(profileAccessPolicyVO,userVO);
	}
}
