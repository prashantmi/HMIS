package opd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class OpdCountOldPatNextVisitDATA extends ReportDATA
{
	public static Map getDeptAndCategory(UserVO _userVO)
	{
		return new EssentialDelegate().getAgeWiseReportEssentials(_userVO);
	}
	
	public static List getUnitBasedOnDepartment(String _deptCode,UserVO _userVO)
	{
		return new EssentialDelegate().getUnitBasedOnDept(_deptCode,_userVO);
	}
}
