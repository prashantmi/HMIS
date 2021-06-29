package mms.reports.controller.data;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.reports.controller.fb.ApplicationErrorLogDetailRptFB;


public class ApplicationErrorLogDetailRptDATA {
	
	public static void getInit(ApplicationErrorLogDetailRptFB formBean, HttpServletRequest request) {

		HisUtil util = null;
		
		String strmsgText = null;
		
		try {
			
			
			util = new HisUtil("MMS Transactions", "ApplicationErrorLogDetailRptDATA");
			
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			
		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.transactions.ApplicationErrorLogDetailRptDATA.getInit --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"ApplicationErrorLogDetailRptDATA->getInit()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (util != null)
				util = null;
		}
	}
	
	


	/**
	 * 
	 * @param formBean
	 * @param request
	 * @param response
	 */
	public static void showReport(ApplicationErrorLogDetailRptFB formBean,
			HttpServletRequest request, HttpServletResponse response)
	{

		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strFromDate = formBean.getStrFromDate();
			String strToDate = formBean.getStrToDate();
			String strPreferredFromTime = (formBean.getStrPreferredFromTime()==null || formBean.getStrPreferredFromTime().equals(""))?"00:00":formBean.getStrPreferredFromTime();
			String strPreferredToTime = (formBean.getStrPreferredToTime()==null || formBean.getStrPreferredToTime().equals(""))?"23:59":formBean.getStrPreferredToTime();
			String strErrorId = (formBean.getStrErrorId()==null || formBean.getStrErrorId().equals(""))?"0":formBean.getStrErrorId();
			
			String strWhetherConsolidated = formBean.getStrWhetherConsolidated();
			String strUserRemarks = formBean.getStrUserRemarks();
			reportFormat = formBean.getStrReportFormat();
			String reportPath = ""; 
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;
				
			}
			
			String strReportName = "Application Error Log Detail";
			
			if( strWhetherConsolidated==null || strWhetherConsolidated.equals("") )
				strWhetherConsolidated="0";
			
			if(strWhetherConsolidated.equals("1"))
			{
				 reportPath = "/mms/reports/dwh_applicationErrorLogDtl1_rpt.rptdesign"; // Consolidated
			}
			else
			{
				 reportPath = "/mms/reports/dwh_applicationErrorLogDtl_rpt.rptdesign"; // Detailed
			}
				
		
			
				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);
				params.put("report_Footer_Visible", footerVisible);
				params.put("report_user_Remarks", strUserRemarks);
				
				params.put("hospCode", strHospitalCode);
				System.out.println("hosp code:::   "+strHospitalCode);
				params.put("from_Date", sdf.format(sdf.parse(strFromDate))+" "+strPreferredFromTime);
				params.put("to_Date", sdf.format(sdf.parse(strToDate))+" "+strPreferredToTime);
				params.put("error_Id",strErrorId);
				
				
				ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
			
		      	
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
		}
	}
}