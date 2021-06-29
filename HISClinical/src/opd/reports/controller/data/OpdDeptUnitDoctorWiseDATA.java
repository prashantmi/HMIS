package opd.reports.controller.data;

import java.util.List;
import java.util.Map;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;
import registration.bo.delegate.EssentialDelegate;

public class OpdDeptUnitDoctorWiseDATA extends ReportDATA
{
	public static Map getDepartment(UserVO _userVO)
	{
		return new EssentialDelegate().getDepartmentWiseTotalPatReportEssentials(_userVO);
	}
	
	public static List getUnitBasedOnDepartment(String _deptCode,UserVO _userVO)
	{
		return new EssentialDelegate().getUnitBasedOnDept(_deptCode,_userVO);
	}
	public static List getDoctorNameBasedOnUnit(String _unitCode,UserVO _userVO)
	{
		return new EssentialDelegate().getDocttorNameBasedOnUnit(_unitCode, _userVO);
	}
}
