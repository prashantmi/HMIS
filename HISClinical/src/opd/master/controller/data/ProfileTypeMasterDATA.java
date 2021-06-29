package opd.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ProfileRestrictedCategoryMasterVO;
import hisglobal.vo.ProfileTypeMstVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class ProfileTypeMasterDATA extends ControllerDATA  
{
	public static Map fetchRestrictedCategoryEssentials(UserVO _userVO){			
		OpdEssentialDelegate masterDelegate = new OpdEssentialDelegate();
		return masterDelegate.fetchRestrictedCategoryEssentials(_userVO);		
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

	public static void saveProfileType(ProfileTypeMstVO profileTypeMasterVO,
			UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveProfileType(profileTypeMasterVO,userVO);
		
	}
	
	public static ProfileTypeMstVO getModifyDetail(ProfileTypeMstVO profileTypeMstVO, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getModifyDetail(profileTypeMstVO, userVO);
	}
	
	public static void modifySave(ProfileTypeMstVO profileTypeMstVO, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.modifySave(profileTypeMstVO,userVO);
	}

}
