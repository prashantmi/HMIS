package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import mrd.masters.delegate.MrdEssentialDelegate;

public class FormPReportDATA extends ReportDATA
{
	public static Map getFormPReportEssentials(UserVO _userVO)
	{
		return new MrdEssentialDelegate().getFormPReportEssentials(_userVO);
	}
}
