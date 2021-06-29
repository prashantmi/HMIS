package mortuary.transaction.controller.data;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.UserVO;

public class DeceasedGeneralAppearanceDATA extends ControllerDATA
{
	public static DeceasedDetailVO getDeceasedGeneralAppearance(String deceasedNo,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getDeceasedGeneralAppearance(deceasedNo,userVO);
	}
	
	public static void saveDeceasedGeneralAppearance(DeceasedDetailVO deceasedDtlVO,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveDeceasedGeneralAppearance(deceasedDtlVO,userVO);
	}
	
	public static DeceasedDetailVO[] searchDeceasedNo(String fName,String mName,String lName,String deathDate,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.searchDeceasedNo(fName,mName,lName,deathDate,userVO);
	}
	
	public static MortuaryDeceasedImageDtlVO getDedeasedDefaultImage(String deceasedNo,UserVO userVO) 
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getDedeasedDefaultImage(deceasedNo,userVO);
	}
	
}
