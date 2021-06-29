package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;

import mrd.reports.bo.MrdReportBO;
import mrd.vo.MrdReportDataVO;
import mrd.vo.MrdReportVO;

public class PediatricsImmunizationsReportDATA extends ReportDATA
{
	// Getting Pediatric Immunization report Data
	public static List<MrdReportDataVO> getReportData(MrdReportVO voReport_p, UserVO voUser_p)
	{
		MrdReportBO boReports = new MrdReportBO();
		return boReports.getPediatricImmunReportData(voReport_p, voUser_p);
	}
	
}
