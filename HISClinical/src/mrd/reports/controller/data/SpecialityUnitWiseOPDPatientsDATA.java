package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class SpecialityUnitWiseOPDPatientsDATA extends ReportDATA
{
	public static Map getSpecialityUnitWiseOPDPatientReportEssentials(UserVO _userVO)
	{
		return new EssentialDelegate().getSpecialityUnitWiseOPDPatientReportEssentials(_userVO);
	}
	
}
