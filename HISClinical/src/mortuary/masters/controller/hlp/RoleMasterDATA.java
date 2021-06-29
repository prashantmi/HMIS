package mortuary.masters.controller.hlp;

import java.util.List;
import java.util.Map;

import mortuary.masters.delegate.MortuaryEssentialDelegate;
import mortuary.masters.delegate.MortuaryMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MortuaryRoleMasterVO;
import hisglobal.vo.UserVO;

public class RoleMasterDATA extends ControllerDATA
{
	
	//Saving the Data
	public static void saveRoleMaster(MortuaryRoleMasterVO mortuaryRoleMasterVO,UserVO userVO)
	{
		MortuaryMasterDelegate mstDelegate=new MortuaryMasterDelegate();
		mstDelegate.saveRoleMaster(mortuaryRoleMasterVO,userVO);
	}
	public static MortuaryRoleMasterVO getDataForModify(MortuaryRoleMasterVO _MortuaryRoleMasterVO, UserVO _UserVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return(masterDelegate.getDataForRoleModify(_MortuaryRoleMasterVO, _UserVO));
	}

	public static boolean saveModRoleMaster(MortuaryRoleMasterVO _MortuaryRoleMasterVO, UserVO _UserVO)
	{
		boolean  flag=false;
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		flag= masterDelegate.saveModRoleMaster(_MortuaryRoleMasterVO, _UserVO);
		return flag;
		
	}
}
