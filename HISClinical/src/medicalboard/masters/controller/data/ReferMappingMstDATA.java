package medicalboard.masters.controller.data;

import hisglobal.vo.MbReferMappingMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import medicalboard.masters.delegate.MbMasterDelegate;
import medicalboard.masters.delegate.MbMasterEssentialDelegate;

public class ReferMappingMstDATA {

	
	public static Map getReferEssentials(UserVO _UserVO)
	{
		MbMasterEssentialDelegate mEssentialDelegate=new MbMasterEssentialDelegate();
		Map mp=mEssentialDelegate.getReferEssentials(_UserVO);
		return mp;		
	}	
	
	public static void saveReferMappingData(List selectedDeptOrUnit,UserVO _UserVO)
	{
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		masterDelegate.saveReferMappingData(selectedDeptOrUnit,_UserVO);
	}
	
	public static Map getReferMappingDetail(MbReferMappingMstVO mappingMstVO, UserVO _UserVO)
	{
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		return (masterDelegate.getReferMappingDetail(mappingMstVO, _UserVO));
	}
	
	
	public static void saveModReferMappingData(List selectedDeptOrUnit,UserVO _UserVO)
	{
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		masterDelegate.saveModReferMappingData(selectedDeptOrUnit,_UserVO);
	}
	
}
