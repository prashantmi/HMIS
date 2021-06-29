package bmed.reports.controller.util;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.reports.controller.data.ServiceEngineerWiseJobStatusReportDATA;
import bmed.reports.controller.fb.ServiceEngineerWiseJobStatusReportFB;
import bmed.vo.ServiceEngineerWiseJobStatusReportVO;

/**
 * @author   Adil Wasi
 * Creation Date:- 29-Aug-2012 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) Service Engineer Wise Job Status Reports
 * 
 */
public class ServiceEngineerWiseJobStatusReportUTIL 
{

	/**
	 * This method is used to initialize Main Page of Report
	 * @param serviceEngineerWiseJobStatusReportFB_p the ServiceEngineerWiseJobStatusReportFB
	 * @param request_p	the HttpServletRequest
	 */
	public static void setInitDtl(ServiceEngineerWiseJobStatusReportFB serviceEngineerWiseJobStatusReportFB_p,HttpServletRequest request_p) {
	
		ServiceEngineerWiseJobStatusReportVO serviceEngineerWiseJobStatusReportVO=null;
		String strCurrentDate="";
		String strDeptVal,strServiceEngineerVal;
		HisUtil util = null;
		
		try 
		{
			serviceEngineerWiseJobStatusReportVO = new ServiceEngineerWiseJobStatusReportVO();
			                     util = new HisUtil("bmed","ServiceEngineerWiseJobStatusReportUTIL");
			    
		     serviceEngineerWiseJobStatusReportVO.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
             serviceEngineerWiseJobStatusReportVO.setStrSeatId(request_p.getSession().getAttribute("SEATID").toString());
	
             String strHospitalCode=request_p.getSession().getAttribute("HOSPITAL_CODE").toString();
             strServiceEngineerVal= ServiceEngineerWiseJobStatusReportDATA.setInitDtl(strHospitalCode);
										
             strCurrentDate = util.getASDate("dd-MMM-yyyy");
					 
             serviceEngineerWiseJobStatusReportFB_p.setStrCurrentDate(strCurrentDate);
					
				  
             //	strServiceEngineerVal = util.getOptionValue(complaintRequestDtlVO.getWrsData(), "0",	"0^Select Value", false);
             serviceEngineerWiseJobStatusReportFB_p.setStrServiceEngineerCmb(strServiceEngineerVal);
								
			} 
	
		 catch (Exception e) 
		 {
			 	String strmsgText = e.getMessage();
			 	HisException eObj = new HisException("BMED", "ServiceEngineerWiseJobStatusReportUTIL->setInitDtl()", strmsgText);
			 	serviceEngineerWiseJobStatusReportFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			 	eObj = null;
		} 
		finally 
		{
		}
	}


	/**
	 * This method is used to show Report using BIRT Template
	 * 
	 * @param serviceEngineerWiseJobStatusReportFB_p the ServiceEngineerWiseJobStatusReportFB
	 * @param request	the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	
	public static void showReport(ServiceEngineerWiseJobStatusReportFB serviceEngineerWiseJobStatusReportFB_p,HttpServletRequest request_p, HttpServletResponse response_p) {
	
		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		String strPassId="";
	
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			String strHospitalCode 		= serviceEngineerWiseJobStatusReportFB_p.getStrHospitalCode();
			String strReportId     		= serviceEngineerWiseJobStatusReportFB_p.getStrReportId();
			String strFromDate     		= serviceEngineerWiseJobStatusReportFB_p.getStrFromDate();
			String strToDate       		= serviceEngineerWiseJobStatusReportFB_p.getStrToDate();
			String strUserRemarks  		= serviceEngineerWiseJobStatusReportFB_p.getStrUserRemarks();
			String strServiceEngineerId = serviceEngineerWiseJobStatusReportFB_p.getStrServiceEngineerId();
			
			
			reportFormat = serviceEngineerWiseJobStatusReportFB_p.getStrReportFormat();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (serviceEngineerWiseJobStatusReportFB_p.getStrIsFooter().equals("1")) 
			{
				footerVisible = false;
				
			}
			
			String strReportName = "Service Engineer Wise Job Status";
	
			params.put("mode", "1");
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hospCode", strHospitalCode);
			params.put("serviceEngineerId", strServiceEngineerId);
			params.put("strPassValue", strPassId);
			params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
			params.put("toDate", sdf.format(sdf.parse(strToDate)));
			
			
			String reportPath = "/bmed/reports/jsp/bmed_serviceEngineerWiseJobStatus_DateWise_report.rptdesign";
			ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("BMED", "ServiceEngineerWiseJobStatusReportDATA->setInitDtl()", strmsgText);
			serviceEngineerWiseJobStatusReportFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
		
		
	}

}

