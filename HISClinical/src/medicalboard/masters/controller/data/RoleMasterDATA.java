package medicalboard.masters.controller.data;

import hisglobal.vo.RoleMasterVO;
import hisglobal.vo.UserVO;
import medicalboard.masters.delegate.MbMasterDelegate;

public class RoleMasterDATA {

	public static void saveRoleDtl(RoleMasterVO  roleMasterVO, UserVO userVO)
	{
		MbMasterDelegate mstDelegate=new MbMasterDelegate();
		mstDelegate.saveRoleDtl(roleMasterVO,userVO);
	}
	
	public static RoleMasterVO getRoleDtl(RoleMasterVO  roleMasterVO, UserVO _UserVO)
	{
		MbMasterDelegate mstDelegate=new MbMasterDelegate();
		return(mstDelegate.getRoleDtl(roleMasterVO, _UserVO));
	}

	public static boolean saveModRoleDtl(RoleMasterVO  roleMasterVO,UserVO _UserVO)
	{
		MbMasterDelegate mstDelegate=new MbMasterDelegate();
		return( mstDelegate.saveModRoleDtl(roleMasterVO, _UserVO));
		
	}
	
}
