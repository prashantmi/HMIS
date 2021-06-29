package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class GenderWiseOutdoorPatientsDATA extends ReportDATA
{
	public static Map getGenderWiseOutdoorPatientReportEssentials(UserVO _userVO)
	{
		return new EssentialDelegate().getGenderWiseOutdoorPatientReportEssentials(_userVO);
	}
	
}
