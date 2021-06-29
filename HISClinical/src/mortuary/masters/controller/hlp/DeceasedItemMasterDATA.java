package mortuary.masters.controller.hlp;

import java.util.List;
import java.util.Map;

import mortuary.masters.delegate.MortuaryEssentialDelegate;
import mortuary.masters.delegate.MortuaryMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedItemMasterVO;
import hisglobal.vo.UserVO;

public class DeceasedItemMasterDATA extends ControllerDATA
{
	
	//Saving the Data
	public static void saveDeceasedItemMaster(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO)
	{
		MortuaryMasterDelegate mstDelegate=new MortuaryMasterDelegate();
		mstDelegate.saveDeceasedItemMaster(deceasedItemMasterVO,userVO);
	}
	public static DeceasedItemMasterVO getDataForModify(DeceasedItemMasterVO deceasedItemMasterVO, UserVO _UserVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return(masterDelegate.getDataForDeceasedItemModify(deceasedItemMasterVO, _UserVO));
	}

	public static boolean saveModDeceasedItemMaster(DeceasedItemMasterVO deceasedItemMasterVO, UserVO _UserVO)
	{
		boolean  flag=false;
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		flag= masterDelegate.saveModDeceasedItemMaster(deceasedItemMasterVO, _UserVO);
		return flag;
		
	}
}
