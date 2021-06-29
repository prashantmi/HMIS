package medicalboard.masters.controller.data;

import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.UserVO;
import medicalboard.masters.delegate.MbMasterDelegate;

public class OrganizationMstDATA {

	
	public static void saveOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO, UserVO userVO)
	{
		MbMasterDelegate mstDelegate=new MbMasterDelegate();
		mstDelegate.saveOrganizationDtl(mbOrganizationMstVO,userVO);
	}
	
	public static MbOrganizationMstVO getOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO, UserVO _UserVO)
	{
		MbMasterDelegate mstDelegate=new MbMasterDelegate();
		return(mstDelegate.getOrganizationDtl(mbOrganizationMstVO, _UserVO));
	}

	public static boolean saveModOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO,UserVO _UserVO)
	{
		boolean  flag=false;
		MbMasterDelegate mstDelegate=new MbMasterDelegate();
		flag= mstDelegate.saveModOrganizationDtl(mbOrganizationMstVO, _UserVO);
		return flag;
		
	}
	
}
