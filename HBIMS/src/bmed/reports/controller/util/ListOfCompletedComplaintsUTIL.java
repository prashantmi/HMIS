package bmed.reports.controller.util;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.reports.controller.data.ListOfCompletedComplaintsDATA;
import bmed.reports.controller.fb.ListOfCompletedComplaintsFB;
import bmed.transactions.bo.BmedTransBO;
import bmed.vo.ComplaintRequestDtlVO;

/**
 * @author   Amit kr
 * Creation Date:- 21-June-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) List of Completed Complaints Request Reports
 * 
 */
public class ListOfCompletedComplaintsUTIL 
{

	/**
	 * This method is used to initialize Main Page of Report
	 * 
	 * @param listOfCompletedComplaintsFB_p	the	ListOfCompletedComplaintsFB
	 * @param request_p the HttpServletRequest
	 */
	
	public static void setInitDtl(ListOfCompletedComplaintsFB listOfCompletedComplaintsFB_p,HttpServletRequest request_p) {

		ComplaintRequestDtlVO complaintRequestDtlVO=null;
		String strCurrentDate="";
		String strDeptVal,strItemVal;
		HisUtil util = null;
		
		try 
		{
			    complaintRequestDtlVO = new ComplaintRequestDtlVO();
			                     util = new HisUtil("bmed","List of Compelted Complaint Details");
			    
			    complaintRequestDtlVO.setStrMode("7");
			    complaintRequestDtlVO.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
			    complaintRequestDtlVO.setStrSeatid(request_p.getSession().getAttribute("SEATID").toString());

			    
			    ListOfCompletedComplaintsDATA.setInitDtl(complaintRequestDtlVO);
										
					  strCurrentDate = util.getASDate("dd-MMM-yyyy");
						  strDeptVal = util.getOptionValue(complaintRequestDtlVO.getWrsData(), "0",	"0^Select Value", false);
						listOfCompletedComplaintsFB_p.setStrDeptCmb(strDeptVal);
						listOfCompletedComplaintsFB_p.setStrCurrentDate(strCurrentDate);
						
				complaintRequestDtlVO.setStrMode("5");
				complaintRequestDtlVO.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
				complaintRequestDtlVO.setStrSeatid(request_p.getSession().getAttribute("SEATID").toString());
				
			    ListOfCompletedComplaintsDATA.setInitDtl(complaintRequestDtlVO);

				strItemVal = util.getOptionValue(complaintRequestDtlVO.getWrsData(), "0",	"0^Select Value", false);
				listOfCompletedComplaintsFB_p.setStrItemCmb(strItemVal);
								
			} 

		 catch (Exception e) 
		 {

				String strmsgText = e.getMessage();
			   HisException eObj = new HisException("BMED", "ListOfCompletedComplaintsDATA->setInitDtl()", strmsgText);
			   listOfCompletedComplaintsFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		} 
		finally 
		{
		}

	}	
	/**
	 * This method is used to show Report using BIRT Template
	 * 
	 * @param listOfCompletedComplaintsFB_p	the	ListOfCompletedComplaintsFB
	 * @param request_p the HttpServletRequest
	 */
	
	public static void showReport(ListOfCompletedComplaintsFB listOfCompletedComplaintsFB_p,
			HttpServletRequest request_p, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		String strPassId="";

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			String strHospitalCode = listOfCompletedComplaintsFB_p.getStrHospCode();
			String strReportId     = listOfCompletedComplaintsFB_p.getStrReportId();
			String strUniqId       = listOfCompletedComplaintsFB_p.getStrUniqId();
			String strFromDate     = listOfCompletedComplaintsFB_p.getStrFromDate();
			String strToDate       = listOfCompletedComplaintsFB_p.getStrToDate();
			String strUserRemarks  = listOfCompletedComplaintsFB_p.getStrUserRemarks();
			if(strUniqId.equals("1"))
			{	
				         strPassId = listOfCompletedComplaintsFB_p.getStrDeptId();
				         params.put("dept_code", strPassId);
				         params.put("item_id", "0");
			}
			else if(strUniqId.equals("2"))
			{	
				         strPassId = listOfCompletedComplaintsFB_p.getStrItemId();
				         params.put("item_id", strPassId);
				         params.put("dept_code", "0");
			}
			
			else 
			{	
				         params.put("item_id", "0");
				         params.put("dept_code", "0");
			}
			
			          reportFormat = listOfCompletedComplaintsFB_p.getStrReportFormat();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (listOfCompletedComplaintsFB_p.getStrIsFooter().equals("1")) 
			{
				footerVisible = false;
				
			}
			
			String strReportName = "List Of Completed Complaints";

			params.put("mode", "1");
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hospCode", strHospitalCode);
			
			params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
			params.put("toDate", sdf.format(sdf.parse(strToDate)));
			
//			params.put("p_store_id", "0");
			
			if(listOfCompletedComplaintsFB_p.getStrUniqId().equals("1"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_dept_ListofCompleteComplaint_report.rptdesign";
				ts.displayReport(request_p, response, reportPath, reportFormat,params,strHospitalCode);
			}
			else if(listOfCompletedComplaintsFB_p.getStrUniqId().equals("2"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_dept_ListofCompleteComplaint_report.rptdesign";
				ts.displayReport(request_p, response, reportPath, reportFormat,params,strHospitalCode);
			}
			else if(listOfCompletedComplaintsFB_p.getStrUniqId().equals("3"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_dept_ListofCompleteComplaint_report.rptdesign";
				ts.displayReport(request_p, response, reportPath, reportFormat,params,strHospitalCode);
			}
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("BMED", "ListOfCompletedComplaintsDATA->setInitDtl()", strmsgText);
			listOfCompletedComplaintsFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}
}