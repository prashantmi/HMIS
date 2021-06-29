package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;

public class RegistrationCensusReportDATA extends ReportDATA
{
	public static Map getRegistrationCensusReportEssentials(UserVO _userVO)
	{
		return new MrdEssentialDelegate().getRegistrationCensusReportEssentials(_userVO);
	}
	
}
