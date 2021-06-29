package opd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class OpdCaseSheetDATA extends ReportDATA
{
	public static Map getDepartment(UserVO _userVO)
	{
		return new EssentialDelegate().getDepartmentWiseTotalPatReportEssentials(_userVO);
	}
}
