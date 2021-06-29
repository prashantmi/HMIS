package opd.master.controller.data;

import hisglobal.vo.DoctorDesigMappingVO;
import hisglobal.vo.ProfileTypeTabMappingVO;
import hisglobal.vo.UserVO;
import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdMasterDelegate;

import mrd.masters.delegate.MrdMasterDelegate;


public class ProfileTypeTabMappingDATA  
{
	
	public static List getProfileType(UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getProfileType(userVO);
	}

	public static void saveProcessWiseDesig(
			DoctorDesigMappingVO[] insertDoctorDesigMappingVO,
			DoctorDesigMappingVO[] updateDoctorDesigMappingVO, UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		masterDelegate.saveProcessWiseDesig(insertDoctorDesigMappingVO,updateDoctorDesigMappingVO,userVO);
	}
	public static Map getDeskMenuForProfileMapping(String profileType,
			UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getDeskMenuForProfileMapping(profileType,userVO);
	}

	public static void saveProfileTypeTabMapping(
			ProfileTypeTabMappingVO[] insertProfileTypeTabMappingVO,
			ProfileTypeTabMappingVO[] updateProfileTypeTabMappingVO,
			UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveProfileTypeTabMapping(insertProfileTypeTabMappingVO,updateProfileTypeTabMappingVO,userVO);
		
	}
	
}
