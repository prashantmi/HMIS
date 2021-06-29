package opd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class OpdMedicalCertificateIssuedDATA extends ReportDATA
{
	public static Map getDepartment(UserVO _userVO)
	{
		return new EssentialDelegate().getDepartmentWiseTotalPatReportEssentials(_userVO);
	}
	public static List getUnitBasedOnDept(String _deptCode,UserVO _userVO)
	{
		return new EssentialDelegate().getUnitBasedOnDept(_deptCode, _userVO);
	}
}
