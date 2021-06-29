package opd.master.controller.data;

import java.util.Map;
import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ProfileGroupMasterVO;
import hisglobal.vo.UserVO;

public class ProfileGroupMasterDATA extends ControllerDATA 
{
	
	public static Map fetchProfileGroupEssentials(UserVO _userVO){			
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchProfileGroupEssentials(_userVO);		
	}
	
	public static void saveProfileGroupDetail(ProfileGroupMasterVO profileGroupMasterVO, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveProfileGroupDetail(profileGroupMasterVO,userVO);
	}
	
	public static ProfileGroupMasterVO fetchProfileGroupDetailModify(ProfileGroupMasterVO profileGroupMasterVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchProfileGroupDetailModify(profileGroupMasterVO,userVO);
	}
	
	public static void modifySave(ProfileGroupMasterVO profileGroupMasterVO, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.modifySaveProfileGroupMaster(profileGroupMasterVO,userVO);
	}

}
