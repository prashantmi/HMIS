package mrd.reports.controller.data;

import java.util.List;
import java.util.Map;

import mrd.reports.bo.MrdReportBO;
import mrd.vo.MrdReportDataVO;
import mrd.vo.MrdReportVO;
import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

public class HospitalPerformanceReportDATA extends ReportDATA
{
	// Getting Hospital Performance Report Data
	public static Map<String, List<MrdReportDataVO>> getReportData(MrdReportVO voReport_p, UserVO voUser_p)
	{
		MrdReportBO boReports = new MrdReportBO();
		return boReports.getHospitalPerformanceReportData(voReport_p, voUser_p);
	}

}
