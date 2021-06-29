package mortuary.masters.controller.hlp;

import java.util.List;
import java.util.Map;

import mortuary.masters.delegate.MortuaryEssentialDelegate;
import mortuary.masters.delegate.MortuaryMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.IncisionTypeMasterVO;
import hisglobal.vo.UserVO;

public class IncisionTypeMasterDATA extends ControllerDATA
{
	//Saving the Data
	public static void saveIncisionTypeMaster(IncisionTypeMasterVO incisionTypeMasterVO,UserVO userVO)
	{
		MortuaryMasterDelegate mstDelegate=new MortuaryMasterDelegate();
		mstDelegate.saveIncisionTypeMaster(incisionTypeMasterVO,userVO);
	}
	public static IncisionTypeMasterVO getDataForModify(IncisionTypeMasterVO incisionTypeMasterVO, UserVO _UserVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return(masterDelegate.getDataForModify(incisionTypeMasterVO, _UserVO));
	}

	public static boolean saveModIncisionTypeMaster(IncisionTypeMasterVO incisionTypeMasterVO, UserVO _UserVO)
	{
		boolean  flag=false;
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		flag= masterDelegate.saveModIncisionTypeMaster(incisionTypeMasterVO, _UserVO);
		return flag;
		
	}
}
