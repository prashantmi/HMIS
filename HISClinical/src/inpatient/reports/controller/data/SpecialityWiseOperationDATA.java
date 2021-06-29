package inpatient.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class SpecialityWiseOperationDATA extends ReportDATA
{
	public static Map getAllHospitalEssentials(UserVO _userVO){    	
		return new EssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	
	public static Map getSpecialityWiseOperationReportEssentials(UserVO _userVO)
	{
		return new EssentialDelegate().getSpecialityWiseOperationReportEssentials(_userVO);
	}
	
	public static List getSpecialityWiseOperationUnit(UserVO _userVO)
	{
		return new EssentialDelegate().getSpecialityWiseOperationUnit(_userVO);
	}
	
}
