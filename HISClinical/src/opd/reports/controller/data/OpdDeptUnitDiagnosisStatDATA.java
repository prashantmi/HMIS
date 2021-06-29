package opd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class OpdDeptUnitDiagnosisStatDATA extends ReportDATA
{
	public static Map getDepartment(UserVO _userVO)
	{
		return new EssentialDelegate().getDepartmentWiseTotalPatReportEssentials(_userVO);
	}
	
	public static List getUnitBasedOnDepartment(String _deptCode,UserVO _userVO)
	{
		return new EssentialDelegate().getUnitBasedOnDept(_deptCode,_userVO);
	}
	
	public static List getIcdCode(String _deptCode,UserVO _userVO)
	{
		return new EssentialDelegate().getIcdCodeBasedOnDept(_deptCode,_userVO);
	}
}
