package opd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;

import registration.bo.delegate.EssentialDelegate;


public class OpdPatientAttendedDATA extends ReportDATA
{
	public static List getAllDepartment(UserVO _userVO)
	{
		return new EssentialDelegate().getAllDepartments(_userVO);
	}
	
	public static List getUser(UserVO _userVO)
	{
		return new OpdEssentialDelegate().getUsersOpd(_userVO); 
	}
	
}
