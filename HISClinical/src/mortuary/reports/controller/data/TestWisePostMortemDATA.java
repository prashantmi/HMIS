package mortuary.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;

import mortuary.masters.delegate.MortuaryEssentialDelegate;


public class TestWisePostMortemDATA extends ReportDATA
{
	public static List getLabTest(UserVO userVO)
	{
		MortuaryEssentialDelegate essentialDelegate= new MortuaryEssentialDelegate();
		List labTestList=essentialDelegate.getLabTest(userVO);
		return labTestList;
	}

}
