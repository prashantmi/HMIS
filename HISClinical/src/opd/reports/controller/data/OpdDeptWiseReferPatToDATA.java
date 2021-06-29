package opd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;

import registration.bo.delegate.EssentialDelegate;

public class OpdDeptWiseReferPatToDATA extends ReportDATA
{
	public static List getAllDepartment(UserVO _userVO)
	{
		return new EssentialDelegate().getAllDepartments(_userVO);
	}
}
