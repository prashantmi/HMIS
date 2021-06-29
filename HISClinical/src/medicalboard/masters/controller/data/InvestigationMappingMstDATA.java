package medicalboard.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import medicalboard.masters.delegate.MbMasterDelegate;
import medicalboard.masters.delegate.MbMasterEssentialDelegate;


public class InvestigationMappingMstDATA {

	public static Map getInvestigationMappingMstEssentials(String certificateTypeId,UserVO _UserVO)
	{
		MbMasterEssentialDelegate mEssentialDelegate=new MbMasterEssentialDelegate();
		Map mp=mEssentialDelegate.getInvestigationMappingMstEssentials(certificateTypeId,_UserVO);
		return mp;		
	}	
	
	public static Map getInvestigationModifyEssentials(String certificateTypeId,UserVO _UserVO)
	{
		MbMasterEssentialDelegate mEssentialDelegate=new MbMasterEssentialDelegate();
		Map mp=mEssentialDelegate.getInvestigationModifyEssentials(certificateTypeId,_UserVO);
		return mp;		
	}
	
	public static void saveInvestigationMappintDtl(List selInvastMapVOList,UserVO _UserVO)
	{
		MbMasterDelegate mbMasterDelegate=new MbMasterDelegate();
		mbMasterDelegate.saveInvestigationMappintDtl(selInvastMapVOList,_UserVO);
	}
	
	public static void updateInvestigationMappintDtl(List selInvastMapVOList,UserVO _UserVO)
	{
		MbMasterDelegate mbMasterDelegate=new MbMasterDelegate();
		mbMasterDelegate.updateInvestigationMappintDtl(selInvastMapVOList,_UserVO);
	}
	
	
	
	
}
