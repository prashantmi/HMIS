package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class DeptWiseMonthlyAbstractOfPatientDATA extends ReportDATA
{
	public static Map getDeptWiseMonthlyAbstractOfPatientReportEssentials(UserVO _userVO)
	{
		return new EssentialDelegate().getDepartments(_userVO);
	}
	/*public static List getUnitBasedOnSpeciality(String speciality,UserVO _userVO)
	{
		return new EssentialDelegate().getUnitBasedOnSpeciality(speciality,_userVO);
	}*/
}
 
