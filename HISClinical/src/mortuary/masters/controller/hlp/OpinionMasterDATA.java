package mortuary.masters.controller.hlp;

import java.util.List;
import java.util.Map;

import mortuary.masters.delegate.MortuaryEssentialDelegate;
import mortuary.masters.delegate.MortuaryMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.OpinionMasterVO;
import hisglobal.vo.UserVO;

public class OpinionMasterDATA extends ControllerDATA
{
	
	//Saving the Data
	public static void saveOpinionMaster(OpinionMasterVO opinionMasterVO,UserVO userVO)
	{
		MortuaryMasterDelegate mstDelegate=new MortuaryMasterDelegate();
		mstDelegate.saveOpinionMaster(opinionMasterVO,userVO);
	}
	public static OpinionMasterVO getDataForModify(OpinionMasterVO _OpinionMasterVO, UserVO _UserVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return(masterDelegate.getDataForOpinionModify(_OpinionMasterVO, _UserVO));
	}

	public static boolean saveModOpinionMaster(OpinionMasterVO _OpinionMasterVO, UserVO _UserVO)
	{
		boolean  flag=false;
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		flag= masterDelegate.saveModOpinionMaster(_OpinionMasterVO, _UserVO);
		return flag;
		
	}
}
