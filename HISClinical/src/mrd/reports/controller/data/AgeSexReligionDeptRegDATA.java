package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class AgeSexReligionDeptRegDATA extends ReportDATA
{
	public static Map getAgeSexReligionDeptRegEssentials(UserVO _userVO)
	{
		return new EssentialDelegate().getAgeSexReligionDeptRegEssentials(_userVO);
	}
	
}
