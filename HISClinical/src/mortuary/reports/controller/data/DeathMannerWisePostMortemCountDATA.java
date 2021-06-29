package mortuary.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;

import mortuary.masters.delegate.MortuaryEssentialDelegate;


public class DeathMannerWisePostMortemCountDATA extends ReportDATA
{
	
	public static List getDeathCause(UserVO userVO)
	{
		MortuaryEssentialDelegate essentialDelegate= new MortuaryEssentialDelegate();
		List deathCauseList=essentialDelegate.getDeathCause(userVO);
		return deathCauseList;
	}

}
