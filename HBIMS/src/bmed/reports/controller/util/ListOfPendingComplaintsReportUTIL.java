package bmed.reports.controller.util;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.reports.controller.data.ListOfPendingComplaintsReportDATA;
import bmed.reports.controller.fb.ListOfPendingComplaintsReportFB;
import bmed.vo.ComplaintRequestDtlVO;

public class ListOfPendingComplaintsReportUTIL 
{
	/**
	 * This function is used forward control to main  page
	 * 
	 * @param listOfPendingComplaintsReportFB_p	the ListOfPendingComplaintsReportFB
	 * @param request_p	the	HttpServletRequest
	 */
	public static void setInitDtl(ListOfPendingComplaintsReportFB listOfPendingComplaintsReportFB_p,HttpServletRequest request_p)
	{
		
		ComplaintRequestDtlVO complaintRequestDtlVO=null;
		String strCurrentDate;
		String strUniqValCmb;
		HisUtil util = null;
		try 
		{
				
			complaintRequestDtlVO=new ComplaintRequestDtlVO();
		    complaintRequestDtlVO.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
		    complaintRequestDtlVO.setStrSeatid(request_p.getSession().getAttribute("SEATID").toString());
			
			util = new HisUtil("bmed", "List of Pending Complaints");
			strCurrentDate=util.getASDate("dd-MMM-yyyy");
			listOfPendingComplaintsReportFB_p.setStrCurrentDate(strCurrentDate);
					
			  
				if(listOfPendingComplaintsReportFB_p.getStrIssueChkDetail().equals("1"))
			    {
			    	complaintRequestDtlVO.setStrMode("7"); // For Dept Combo
			    }
			    else if(listOfPendingComplaintsReportFB_p.getStrIssueChkDetail().equals("2"))
			    {
			    	complaintRequestDtlVO.setStrMode("5");	// For Item Combo
			    }			   
			    else
			    	complaintRequestDtlVO.setStrMode("6");
			    
			    ListOfPendingComplaintsReportDATA.setInitDtl(complaintRequestDtlVO);
			    
				
				
				strUniqValCmb=util.getOptionValue(complaintRequestDtlVO.getWrsData(), "0",	"0^Select Value", false);
				
				
				listOfPendingComplaintsReportFB_p.setStrUniqValCmb(strUniqValCmb);
			} 

		 catch (Exception e) {

				String strMsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "ListOfPendingComplaintsReportUTIL->setInitDtl()", strMsgText);
			   listOfPendingComplaintsReportFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		} finally {

		}
	}	
	
	/**
	 * To show Report
	 * 
	 * @param listOfPendingComplaintsReportFB_p	the ListOfPendingComplaintsReportFB
	 * @param request_p	the	HttpServletRequest
	 * @param response	the HttpServletResponse
	 * 
	 */
	public static void showReport(ListOfPendingComplaintsReportFB listOfPendingComplaintsReportFB_p,	HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			String strHospitalCode = listOfPendingComplaintsReportFB_p.getStrHospCode();
			String strReportId = listOfPendingComplaintsReportFB_p.getStrReportId();
			String strFromDate = listOfPendingComplaintsReportFB_p.getStrFromDate();
			String strToDate = listOfPendingComplaintsReportFB_p.getStrToDate();
			String strUserRemarks = listOfPendingComplaintsReportFB_p.getStrUserRemarks();
			
			
			if(listOfPendingComplaintsReportFB_p.getStrIssueChkDetail().equals("1"))
			{	
				         params.put("dept_code", listOfPendingComplaintsReportFB_p.getStrUniqId());
				         params.put("itemId", "0");
			}
			else if(listOfPendingComplaintsReportFB_p.getStrIssueChkDetail().equals("2"))
			{	
				         params.put("itemId", listOfPendingComplaintsReportFB_p.getStrUniqId());
				         params.put("dept_code", "0");
			}
			else 
			{	
				         params.put("itemId", "0");
				         params.put("dept_code", "0");
			}
			
			reportFormat = listOfPendingComplaintsReportFB_p.getStrReportFormat();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (listOfPendingComplaintsReportFB_p.getStrIsFooter().equals("1")) {
				footerVisible = false;
				
			}
			
			String strReportName = "List of Pending Complaints";

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			
			params.put("hospCode", strHospitalCode);
			params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
			params.put("toDate", sdf.format(sdf.parse(strToDate)));
	         
			params.put("mode", "3");
			
			if(listOfPendingComplaintsReportFB_p.getStrIssueChkDetail().equals("1"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_ListofPendingComplaints_report.rptdesign";
				ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
			}
			else if(listOfPendingComplaintsReportFB_p.getStrIssueChkDetail().equals("2"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_ListofPendingComplaints_report.rptdesign";
				ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
			}
			else if(listOfPendingComplaintsReportFB_p.getStrIssueChkDetail().equals("3"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_ListofPendingComplaints_report.rptdesign";
				ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
			}
			else 
			{
				String reportPath = "/bmed/reports/jsp/bmed_ListofPendingComplaints_report.rptdesign";
				ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
			}
		} 
		catch (Exception e)
		{
		   String strMsgText = e.getMessage();
		   HisException eObj = new HisException("IPD", "ListOfEquipmentUnderAmcReportUTIL->setInitDtl()", strMsgText);
		   listOfPendingComplaintsReportFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		     eObj = null;
		}
		finally
		{
	
		}
	}
}

