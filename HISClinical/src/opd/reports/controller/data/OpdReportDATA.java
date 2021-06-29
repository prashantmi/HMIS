package opd.reports.controller.data;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;
import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

public class OpdReportDATA extends ReportDATA
{
	public static Map getAllDeparmentsEssentials(UserVO _userVO)
	{    	
		return new EssentialDelegate().getDeptWisePatCatReportsEssentials(_userVO);
    }
}
