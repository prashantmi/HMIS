package mortuary.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;

import mortuary.masters.delegate.MortuaryEssentialDelegate;


public class LabAndTestWisePostMortemDATA extends ReportDATA
{
	public static List getLab(UserVO userVO)
	{
		MortuaryEssentialDelegate essentialDelegate= new MortuaryEssentialDelegate();
		List allLablist=essentialDelegate.getLab(userVO);
		return allLablist;
	}
	
	public static List getLabTest(UserVO userVO)
	{
		MortuaryEssentialDelegate essentialDelegate= new MortuaryEssentialDelegate();
		List labTestList=essentialDelegate.getLabTest(userVO);
		return labTestList;
	}

}
