package mortuary.transaction.controller.data;

import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PostmortemDetailVO;
import hisglobal.vo.UserVO;

public class DeceasedInjuriesDATA extends ControllerDATA
{
	public static Map getEssentialForInjuries(UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getEssentialForInjuries(userVO);
	}
	
	public static PatientDeathDetailVO getExistingInjuryDetail(String crNo,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getExistingInjuryDetail(crNo,userVO);
	}
	
	public static void saveInjuriesDetail(PostmortemDetailVO postmortemInjuryVO, UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveInjuriesDetail(postmortemInjuryVO,userVO); 
	}
	
	public static boolean getGeneralAppearanceExsistance(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getGeneralAppearanceExsistance(postmortemId,userVO);
	}
	
	public static PostmortemDetailVO getAddedInjuryDetailToDisplay(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getAddedInjuryDetailToDisplay(postmortemId,userVO);
	}
}
