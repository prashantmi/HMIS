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
public class BillCancellationLogDetailRptDATA {
	
	public static void initReportPage(BillCancellationLogDetailRptFB formBean,HttpServletRequest request) {

		BillCancellationLogDetailRptBO bo = null;
		BillCancellationLogDetailRptVO voObj = null;

		HisUtil util = null;
		
		String strmsgText = null;
        String strHospNameValues="";
		try {
		
			bo= new BillCancellationLogDetailRptBO();
			voObj = new BillCancellationLogDetailRptVO();
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )? request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			bo.getHospitalName(voObj);
			bo.getFeeClerkName(voObj);
		
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
						
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","BillCancellationLogDetailRptDATA");
			
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), request.getSession().getAttribute("HOSPITAL_CODE").toString(),"0^Select Value", false);
			formBean.setStrHospNameValues(strHospNameValues);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy") );
			
			
			  String feeClerk= "<option value='0'>All</option>";
				
				if (voObj.getStrClerkWs().size() != 0) {
					feeClerk = util.getOptionValue(voObj.getStrClerkWs(), "0", "0^All",true);
				}
				else {
					feeClerk ="<option value='0'>All</option>";
				}
				formBean.setStrFeeClekNameValues(feeClerk);

		} catch (Exception e) {
			strmsgText = "billing.reports.BillCancellationLogDetailRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"BillCancellationLogDetailRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator!");

			eObj = null;
		} finally {

			voObj = null;
			util = null;
		}

	}
	
	public static void showReport(BillCancellationLogDetailRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				String linkadd="";
				
				Map<String, Object> params = new HashMap<String, Object>();
			try {
				
				
				linkadd=request.getSession().getServletContext().getRealPath("/build/clases/billing/reports/BillCancellationLogDetailRptDATA.class");  
					
					String strReportName = "Bill Cancellation Log Detail Report";								
					String strHospitalCode = formBean.getStrHospitalCode();
					String strReportId = formBean.getStrReportId();
					String strUserRemarks = formBean.getStrUserRemarks();
					String strFromDate = formBean.getStrEffectiveFrom();
					String strToDate = formBean.getStrEffectiveTo();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					reportFormat = formBean.getStrReportFormat();
					params.put("report_formattype", reportFormat);
					String reportPath="";
					
					boolean footerVisible = true;
	
					if (formBean.getStrIsFooter().equals("1")) {
						footerVisible = false;
					}
					
					
					if(formBean.getStrCase().equals("1")){
						
						 reportPath = "/billing/reports/billCancellationLogDtlNew_billrpt.rptdesign";
							
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							params.put("hosp_Code", strHospitalCode);
							params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
							params.put("to_Date", sdf.format(sdf.parse(strToDate)));
							params.put("report_Criteria", "Date Wise");
							params.put("linkadd", linkadd);
							
						
					}else{
						
					
					
					 reportPath = "/billing/reports/billCancellationLogDtlNew_billrpt2.rptdesign";
						
					params.put("report_id", strReportId);
					params.put("report_Name", strReportName);
					params.put("report_Footer_Visible", footerVisible);
					params.put("report_user_Remarks", strUserRemarks);
					params.put("hosp_Code", strHospitalCode);
					params.put("seat_id", formBean.getStrFeeClerkName());
					params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
					params.put("to_Date", sdf.format(sdf.parse(strToDate)));
					params.put("report_Criteria", "User Wise");
					
					
					
					}
											
					ts.displayReport(request, response, reportPath, reportFormat,
								params,strHospitalCode);
						
					
					
				} catch (Exception e) {
				
					strmsgText = "billing.reports.BillCancellationLogDetailRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"BillCancellationLogDetailRptDATA->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;		
					}
			}
	public static void LinkedReport(BillCancellationLogDetailRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				
				Map<String, Object> params = new HashMap<String, Object>();
			try {
					
					String strReportName = "Reciept Detail Report";								
					params.put("report_formattype", reportFormat);
					String reportPath="";
					String hosp_Code=request.getParameter("hosp_Code");
					String rec_no=request.getParameter("rec_no").split("/")[0];
					boolean footerVisible = true;
					reportPath = "/billing/reports/billCancellationLogDtlRecieptLink_billrpt.rptdesign";
							
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("hosp_Code", hosp_Code);
							params.put("rec_no", rec_no);
							params.put("report_Criteria", "BILL NO:- "+rec_no);
							
					ts.displayReport(request, response, reportPath, reportFormat,
								params,hosp_Code);

				} catch (Exception e) {
				
					strmsgText = "billing.reports.BillCancellationLogDetailRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"BillCancellationLogDetailRptDATA->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;		
					}
			}

}
