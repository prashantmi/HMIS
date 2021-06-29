package opd.reports.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class ICDCodeReportDATA
{
	public static Map getEssentials(UserVO _userVO)
	{
		EssentialDelegate delegate = new EssentialDelegate();
		return delegate.getDepartmentWiseTotalPatReportEssentials(_userVO);
	}

	public static List getUnitBasedOnDepartment(String _deptCode,UserVO _userVO)
	{
		EssentialDelegate delegate = new EssentialDelegate();
		return delegate.getUnitBasedOnDept(_deptCode, _userVO);
	}
}
