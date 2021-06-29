package mrd.reports.controller.data;

import java.util.List;
import java.util.Map;

import mrd.reports.bo.MrdReportBO;
import mrd.vo.MrdReportDataVO;
import mrd.vo.MrdReportVO;
import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

public class DeliveriesAndCUTInsertionReportDATA extends ReportDATA
{
	// Getting Deliveries and Cu-T Insertion Report Data
	public static Map<String, List<MrdReportDataVO>> getReportData(MrdReportVO voReport_p, UserVO voUser_p,String strServiceType)
	{
		MrdReportBO boReports = new MrdReportBO();
		return boReports.getDelivCuTInsertionReportData(voReport_p, voUser_p,strServiceType);
	}
	
}
