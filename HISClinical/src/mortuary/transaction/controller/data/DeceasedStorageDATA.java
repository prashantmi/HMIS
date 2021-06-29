package mortuary.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.DeceasedStorageDtlVO;
import hisglobal.vo.UserVO;

public class DeceasedStorageDATA extends ControllerDATA
{
	public static DeceasedDetailVO[] getDeceasedListForStorage(UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.getDeceasedListForStorage(userVO);
	}
	
	public static Map getDeceasedEssentialStorageDetail(UserVO userVO)
	{
		MortuaryEssDelegate	essDelegate=new MortuaryEssDelegate();
		return essDelegate.getDeceasedEssentialStorageDetail(userVO);
	}
	
	public static void saveDeceasedStorageDetail(DeceasedRelativeDtlVO deceasedRelativeVO, DeceasedStorageDtlVO deceasedStorageVO,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveDeceasedStorageDetail(deceasedRelativeVO, deceasedStorageVO,userVO);
	}
	public static List getChamberBasedOnMortuaryArea(String mortuaryArea,UserVO userVO)
	{
		MortuaryEssDelegate	essDelegate=new MortuaryEssDelegate();
		return essDelegate.getChamberBasedOnMortuaryArea(mortuaryArea, userVO);
	}
	
	public static List getAreaBasedOnMortuary(String mortuary,UserVO userVO)
	{
		MortuaryEssDelegate	essDelegate=new MortuaryEssDelegate();
		return essDelegate.getAreaBasedOnMortuary(mortuary, userVO);
	}
	
}
