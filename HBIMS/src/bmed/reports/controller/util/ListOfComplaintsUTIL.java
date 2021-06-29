package bmed.reports.controller.util;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.reports.controller.data.ListOfComplaintsDATA;
import bmed.reports.controller.data.ListOfCompletedComplaintsDATA;
import bmed.reports.controller.fb.ListOfComplaintsFB;
import bmed.vo.ComplaintRequestDtlVO;

/**
 * @author   Amit kr
 * Creation Date:- 21-June-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) List of Compeleted Complaints Request Reports
 * 
 */
public class ListOfComplaintsUTIL 
{

	/**
	 * This method is used to initialize Main Page of Report
	 * @param listOfComplaintsFB_p the ListOfComplaintsFB
	 * @param request_p	the HttpServletRequest
	 */
	public static void setInitDtl(ListOfComplaintsFB listOfComplaintsFB_p,HttpServletRequest request_p) {
	
		ComplaintRequestDtlVO complaintRequestDtlVO=null;
		String strCurrentDate="";
		String strDeptVal,strItemVal;
		HisUtil util = null;
		
		try 
		{
			    complaintRequestDtlVO = new ComplaintRequestDtlVO();
			                     util = new HisUtil("bmed","List of Complaint Details");
			    
			    complaintRequestDtlVO.setStrMode("7");
			    complaintRequestDtlVO.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
			    complaintRequestDtlVO.setStrSeatid(request_p.getSession().getAttribute("SEATID").toString());
	
			    
			    ListOfCompletedComplaintsDATA.setInitDtl(complaintRequestDtlVO);
										
					  strCurrentDate = util.getASDate("dd-MMM-yyyy");
						  strDeptVal = util.getOptionValue(complaintRequestDtlVO.getWrsData(), "0",	"0^Select Value", false);
						listOfComplaintsFB_p.setStrDeptCmb(strDeptVal);
						listOfComplaintsFB_p.setStrCurrentDate(strCurrentDate);
						
				complaintRequestDtlVO.setStrMode("5");
				complaintRequestDtlVO.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
				complaintRequestDtlVO.setStrSeatid(request_p.getSession().getAttribute("SEATID").toString());
				
			    ListOfComplaintsDATA.setInitDtl(complaintRequestDtlVO);
	
				strItemVal = util.getOptionValue(complaintRequestDtlVO.getWrsData(), "0",	"0^Select Value", false);
				listOfComplaintsFB_p.setStrItemCmb(strItemVal);
								
			} 
	
		 catch (Exception e) 
		 {
			 	String strmsgText = e.getMessage();
			 	HisException eObj = new HisException("BMED", "ListOfComplaintsDATA->setInitDtl()", strmsgText);
			 	listOfComplaintsFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			 	eObj = null;
		} 
		finally 
		{
		}
	}


	/**
	 * This method is used to show Report using BIRT Template
	 * 
	 * @param ListOfComplaintsFB the ListOfComplaintsFB
	 * @param request	the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	
	public static void showReport(ListOfComplaintsFB listOfComplaintsFB_p,HttpServletRequest request_p, HttpServletResponse response_p) {
	
		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		String strPassId="";
	
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			String strHospitalCode = listOfComplaintsFB_p.getStrHospCode();
			String strReportId     = listOfComplaintsFB_p.getStrReportId();
			String strUniqId       = listOfComplaintsFB_p.getStrUniqId();
			String strFromDate     = listOfComplaintsFB_p.getStrFromDate();
			String strToDate       = listOfComplaintsFB_p.getStrToDate();
			String strUserRemarks  = listOfComplaintsFB_p.getStrUserRemarks();
			if(strUniqId.equals("1"))
			{	
				         strPassId = listOfComplaintsFB_p.getStrDeptId();
				         params.put("dept_code", listOfComplaintsFB_p.getStrDeptId());
				         params.put("item_id", "0");
				         
			}
			else if(strUniqId.equals("2"))
			{	
				         strPassId = listOfComplaintsFB_p.getStrItemId();
				         params.put("item_id", listOfComplaintsFB_p.getStrItemId());
				         params.put("dept_code", "0");
			}
			else 
			{	
				         params.put("item_id", "0");
				         params.put("dept_code", "0");
			}
			
			          reportFormat = listOfComplaintsFB_p.getStrReportFormat();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (listOfComplaintsFB_p.getStrIsFooter().equals("1")) 
			{
				footerVisible = false;
				
			}
			
			String strReportName = "List Of Complaints";
	
			params.put("mode", "2");
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hospCode", strHospitalCode);
			params.put("strPassValue", strPassId);
			params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
			params.put("toDate", sdf.format(sdf.parse(strToDate)));
			
			
			if(listOfComplaintsFB_p.getStrUniqId().equals("1"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_dept_ListOfComplaint_report.rptdesign";
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			}
			else if(listOfComplaintsFB_p.getStrUniqId().equals("2"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_dept_ListOfComplaint_report.rptdesign";
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			}
			else if(listOfComplaintsFB_p.getStrUniqId().equals("3"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_dept_ListOfComplaint_report.rptdesign";
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("BMED", "ListOfComplaintsDATA->setInitDtl()", strmsgText);
			listOfComplaintsFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
		
		
	}

}

