package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class OpdStaticReportDATA extends ReportDATA
{
	public static Map getAllHospitalEssentials(UserVO _userVO)
	{    	
		return new EssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	public static Map getDepartment(UserVO _userVO)
	{
		return new EssentialDelegate().getDepartmentWiseTotalPatReportEssentials(_userVO);
	}
	
	public static List getUnitBasedOnDepartment(String _deptCode,UserVO _userVO)
	{
		return new EssentialDelegate().getUnitBasedOnDept(_deptCode,_userVO);
	}
	
	public static Map getRegAndPatCategory(UserVO _userVO)
	{
		return new EssentialDelegate().getEmergencyRegPatReportEssential(_userVO);
	}
	
}
