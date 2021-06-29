package opd.master.controller.data;

import java.util.Map;
import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ProfileRestrictedCategoryMasterVO;
import hisglobal.vo.UserVO;

public class ProfileRestrictedCategoryMasterDATA extends ControllerDATA  
{
	public static Map fetchRestrictedCategoryEssentials(UserVO _userVO){			
		OpdEssentialDelegate masterDelegate = new OpdEssentialDelegate();
		return masterDelegate.fetchRestrictedCategoryEssentials(_userVO);		
	}
	
	public static Map getPatientCategoryForProfileType(String profileType,UserVO _userVO){			
		OpdEssentialDelegate masterDelegate = new OpdEssentialDelegate();
		return masterDelegate.getPatientCategoryForProfileType(profileType,_userVO);		
	}
	
	public static void saveProfileRestrictedCategory(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveProfileRestrictedCategory(profileRestrictedCategoryMasterVO,userVO);
	}
	
	public static ProfileRestrictedCategoryMasterVO fetchPatientCatModify(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchPatientCatModify(profileRestrictedCategoryMasterVO,userVO);
	}
	
	public static void modifySave(ProfileRestrictedCategoryMasterVO profileRestrictedCategoryMasterVO, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.modifySave(profileRestrictedCategoryMasterVO,userVO);
	}

	public static void saveProfileRestrictedCategory(
			ProfileRestrictedCategoryMasterVO[] insertProfileRestrictedCatMstVO,
			ProfileRestrictedCategoryMasterVO[] updateProfileRestrictedCatMstVO,
			UserVO userVO) {
	
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveProfileRestrictedCategory(insertProfileRestrictedCatMstVO,updateProfileRestrictedCatMstVO,userVO);
	}

}
