package bmed.reports.controller.util;


import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bmed.reports.controller.data.logRegisterReportDATA;

import bmed.reports.controller.fb.logRegisterReportFB;

public class logRegisterReportUTIL {
	
	/**
	 * This method is used to initialize Main Page of Report
	 * @param listOfComplaintsFB_p the ListOfComplaintsFB
	 * @param request_p	the HttpServletRequest
	 */
	public static void setInitDtl(logRegisterReportFB downTimeFB_p,HttpServletRequest request_p) {
	
		String strCurrentDate="";
		String strDeptVal;
		HisUtil util = null;
		
		try 
		{
			                     util = new HisUtil("bmed","Equipment Wise Complaint Status");
			    
			    	
			    String strHospitalCode=request_p.getSession().getAttribute("HOSPITAL_CODE").toString();
			    String strSeatId = request_p.getSession().getAttribute("SEATID").toString();
			    strDeptVal= logRegisterReportDATA.setInitDtl(strHospitalCode,strSeatId);
										
				strCurrentDate = util.getASDate("dd-MMM-yyyy");
					 
				downTimeFB_p.setStrCurrentDate(strCurrentDate);
					
				  
				//	strItemVal = util.getOptionValue(complaintRequestDtlVO.getWrsData(), "0",	"0^Select Value", false);
				downTimeFB_p.setStrDeptCmb(strDeptVal);
								
			} 
	
		 catch (Exception e) 
		 {
			 	String strmsgText = e.getMessage();
			 	HisException eObj = new HisException("BMED", "logRegisterReportUTIL->setInitDtl()", strmsgText);
			 	downTimeFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			 	eObj = null;
		} 
		finally 
		{
		}
	}
	/**
	 * This method is used to get Item Name Combo
	 * 
	 * @param EquipmentWiseComplaintStatusReportFB the EquipmentWiseComplaintStatusReportFB
	 * @param request	the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	public static void getStoreNameCombo(logRegisterReportFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		formBean_p.setStrHospCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean_p.setStrSeatId(request_p.getSession().getAttribute("SEATID").toString());
		formBean_p.setStrDeptId(request_p.getParameter("strDeptId"));
		logRegisterReportDATA.getStoreNameCombo(formBean_p, request_p, response_p);
		
	}

	/**
	 * This method is used to show Report using BIRT Template
	 * 
	 * @param logRegisterReportFB the logRegisterReportFB
	 * @param request	the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	
	public static void showReport(logRegisterReportFB logRegisterReportFB_p,HttpServletRequest request_p, HttpServletResponse response_p) {
	
		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		String strPassId="";
	
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			String strHospitalCode = logRegisterReportFB_p.getStrHospCode();
			String strReportId     = logRegisterReportFB_p.getStrReportId();
			String strFromDate     = logRegisterReportFB_p.getStrFromDate();
			String strToDate       = logRegisterReportFB_p.getStrToDate();
			String strUserRemarks  = logRegisterReportFB_p.getStrUserRemarks();
			String strDeptId	   = logRegisterReportFB_p.getStrDeptId();
			String strStoreId	   = logRegisterReportFB_p.getStrStoreId();
			
			
			
	        params.put("Store_id", strStoreId);
	        params.put("dept_code", strDeptId);
			
			reportFormat = logRegisterReportFB_p.getStrReportFormat();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (logRegisterReportFB_p.getStrIsFooter().equals("1")) 
			{
				footerVisible = false;
				
			}
			String strReportName = "Log Register Report";
	
			
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hospCode", strHospitalCode);
			params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
			params.put("toDate", sdf.format(sdf.parse(strToDate)));
			
			String reportPath = "/bmed/reports/jsp/bmed_logRegisterReport_report.rptdesign";
			ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("BMED", "logRegisterReportUTIL->setInitDtl()", strmsgText);
			logRegisterReportFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
		
		
	}

}
