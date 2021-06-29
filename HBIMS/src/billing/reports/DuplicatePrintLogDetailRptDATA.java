/**
 * 
 */
package billing.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public class DuplicatePrintLogDetailRptDATA {
	
	public static void initReportPage(DuplicatePrintLogDetailRptFB formBean,HttpServletRequest request) {

		HisUtil util = null;
		DuplicatePrintLogDetailRptBO bo = null;
		DuplicatePrintLogDetailRptVO voObj = null;

		String strmsgText = null;
		String strHospNameValues="";

		try {
		
			bo= new DuplicatePrintLogDetailRptBO();
			voObj = new DuplicatePrintLogDetailRptVO();
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )? request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			bo.getHospitalName(voObj);
			
			
			
			//formBean.setStrHospitalCode(formBean.getStrHospitalCode());
						
			util = new HisUtil("Billing Reports","DuplicatePrintLogDetailRptDATA");
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0","0^Select Value", false);
			formBean.setStrHospNameValues(strHospNameValues);	
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "billing.reports.DuplicatePrintLogDetailRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DuplicatePrintLogDetailRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator!");

			eObj = null;
		} finally {

			util = null;
		}

	}
	
	public static void showReport(DuplicatePrintLogDetailRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				
				Map<String, Object> params = new HashMap<String, Object>();
			try {
					
					String strReportName = "Duplicate Print Log Detail";								
					String strHospitalCode = formBean.getStrHospitalCode();
					String strReportId = formBean.getStrReportId();
					String strUserRemarks = formBean.getStrUserRemarks();
					String strFromDate = formBean.getStrEffectiveFrom();
					String strToDate = formBean.getStrEffectiveTo();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					reportFormat = formBean.getStrReportFormat();
					
					boolean footerVisible = true;
	
					if (formBean.getStrIsFooter().equals("1")) {
						footerVisible = false;
					}
					
					if(formBean.getStrCase().equals("1")){
						
						String reportPath = "/billing/reports/duplicatePrintLodDetail1New_billrpt.rptdesign";
						
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("report_Criteria", "Detailed Duplicate Print");
						
												
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strHospitalCode);
						
					}
					else if(formBean.getStrCase().equals("2")){
						
						String reportPath = "/billing/reports/duplicatePrintLodDetail2New_billrpt.rptdesign";
						
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("report_Criteria", "Consolidated Duplicate Print");
						
												
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strHospitalCode);
						
					}	
					
					
				} catch (Exception e) {
				
					strmsgText = "billing.reports.DuplicatePrintLogDetailRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"DuplicatePrintLogDetailRptDATA->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;		
					}
			}

}
