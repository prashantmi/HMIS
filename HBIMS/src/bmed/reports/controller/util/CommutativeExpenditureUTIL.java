package bmed.reports.controller.util;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.reports.controller.data.CommutativeExpenditureDATA;
import bmed.reports.controller.data.ListOfComplaintsDATA;
import bmed.reports.controller.data.ListOfCompletedComplaintsDATA;
import bmed.reports.controller.fb.CommutativeExpenditureFB;
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
public class CommutativeExpenditureUTIL 
{

	/**
	 * This method is used to initialize Main Page of Report
	 * @param listOfComplaintsFB_p the ListOfComplaintsFB
	 * @param request_p	the HttpServletRequest
	 */
	public static void setInitDtl(CommutativeExpenditureFB commutativeExpenditureFB_p,HttpServletRequest request_p) {
	
		ComplaintRequestDtlVO complaintRequestDtlVO=null;
		String strCurrentDate="";
		String strDeptVal,strItemVal;
		HisUtil util = null;
		
		try 
		{
			    complaintRequestDtlVO = new ComplaintRequestDtlVO();
			                     util = new HisUtil("bmed","Commutative Expenditure");
			    
			   // complaintRequestDtlVO.setStrMode("6");
			    complaintRequestDtlVO.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
			    complaintRequestDtlVO.setStrSeatid(request_p.getSession().getAttribute("SEATID").toString());
	
			    String strHospitalCode=request_p.getSession().getAttribute("HOSPITAL_CODE").toString();
			  strItemVal= CommutativeExpenditureDATA.setInitDtl(complaintRequestDtlVO,strHospitalCode);
										
					  strCurrentDate = util.getASDate("dd-MMM-yyyy");
						 
					  commutativeExpenditureFB_p.setStrCurrentDate(strCurrentDate);
						
					  
					//	strItemVal = util.getOptionValue(complaintRequestDtlVO.getWrsData(), "0",	"0^Select Value", false);
						commutativeExpenditureFB_p.setStrItemCmb(strItemVal);
								
			} 
	
		 catch (Exception e) 
		 {
			 	String strmsgText = e.getMessage();
			 	HisException eObj = new HisException("BMED", "CommutativeExpenditureDetails->setInitDtl()", strmsgText);
			 	commutativeExpenditureFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
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
	
	public static void showReport(CommutativeExpenditureFB commutativeExpenditureFB_p,HttpServletRequest request_p, HttpServletResponse response_p) {
	
		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		String strPassId="";
	
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			String strHospitalCode = commutativeExpenditureFB_p.getStrHospCode();
			String strReportId     = commutativeExpenditureFB_p.getStrReportId();
			String strUniqId       = commutativeExpenditureFB_p.getStrUniqId();
			String strFromDate     = commutativeExpenditureFB_p.getStrFromDate();
			String strToDate       = commutativeExpenditureFB_p.getStrToDate();
			String strUserRemarks  = commutativeExpenditureFB_p.getStrUserRemarks();
			
			
			if(strUniqId.equals("1"))
			{	
				params.put("mode", "1");
				String strItemId=commutativeExpenditureFB_p.getStrItemId();
				 params.put("item_id", strItemId);
				      
				         
			}
			if(strUniqId.equals("2"))
			{	
				params.put("mode", "2");
				        // strPassId = commutativeExpenditureFB_p.getStrItemId();
				         params.put("item_id", "0");
				         params.put("dept_code", "0");
			}
			if(strUniqId.equals("3"))
			{	
				params.put("mode", "3");
				String strItemId=commutativeExpenditureFB_p.getStrComplaintId();
				params.put("item_id", strItemId);
			    params.put("dept_code", "0");
				         
			}
			
			          reportFormat = commutativeExpenditureFB_p.getStrReportFormat();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (commutativeExpenditureFB_p.getStrIsFooter().equals("1")) 
			{
				footerVisible = false;
				
			}
			
			String strReportName = "Commutative Expenditure Report";
	
			
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hospCode", strHospitalCode);
			//params.put("strPassValue", strPassId);
			params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
			params.put("toDate", sdf.format(sdf.parse(strToDate)));
			
			
			if(commutativeExpenditureFB_p.getStrUniqId().equals("1"))
			{
				
				String reportPath = "/bmed/reports/jsp/bmed_dept_CommutativeExpenditure_ItemWise_report.rptdesign";
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			}
			else if(commutativeExpenditureFB_p.getStrUniqId().equals("2"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_dept_CommutativeExpenditure_DateWise_report.rptdesign";
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			}
			else if(commutativeExpenditureFB_p.getStrUniqId().equals("3"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_dept_CommutativeExpenditure_Detailed_report.rptdesign";
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			}
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("BMED", "ListOfComplaintsDATA->setInitDtl()", strmsgText);
			commutativeExpenditureFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
		
		
	}

}

