package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;

public class RegistrationPatientListingReportDATA extends ReportDATA
{
	public static Map getRegistrationPatientListingReportEssentials(UserVO _userVO)
	{
		return new MrdEssentialDelegate().getRegistrationPatientListingReportEssentials(_userVO);
	}
	
}
