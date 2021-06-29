/**
 * Developer : Anurudra Goel
 * Version : 1.0
 * Date : 17/July/2009
 *  
*/
package mms.reports.controller.data;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.reports.bo.IssueToPatientDetailRptBO;
import mms.reports.controller.fb.IssueToPatientDetailRptFB;
import mms.reports.vo.IssueToPatientDetailRptVO;

public class IssueToPatientDetailRptData {
	
	
	public static void setInitDtl(IssueToPatientDetailRptFB formBean,HttpServletRequest request) {

		IssueToPatientDetailRptVO vo=null;
		IssueToPatientDetailRptBO bo=null;
		String strCurrentDate="";
		String strStoreVal="",strItemVal;
		HisUtil util = null;
		String strStoreId="0";
		try {
				bo=new IssueToPatientDetailRptBO();
				vo=new IssueToPatientDetailRptVO();
				
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				bo.getInitDtl(vo);
				if(vo.getStrMsgType().equals("1")){
					
					throw new Exception(vo.getStrMsgString());
				}else{
						util = new HisUtil("Issue Detail Report","IssueToPatientDetailRptData");
						strCurrentDate=util.getASDate("dd-MMM-yyyy");
						
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					    Calendar c1 = Calendar.getInstance();	    
					    c1.add(Calendar.DATE,0);   // or  Calendar.DAY_OF_MONTH which is a synonym
					    //System.out.println("Date + 1 days is : " + sdf.format(c1.getTime()));
						
//					    formBean.setStrCurrentDate(strCurrentDate);
						formBean.setStrCurrentDate(sdf.format(c1.getTime()));
						
						/*if(vo.getStrWS()!=null && vo.getStrWS().size()>0)
						{
							if(vo.getStrWS().next())
							{
								strStoreId = vo.getStrWS().getString(1);
							}
						}*/
						//vo.getStrWS().beforeFirst();
						strStoreVal=util.getOptionValue(vo.getStrWS(), "0",	"0^Select Value", false);
						formBean.setStrStoreVal(strStoreVal);
						
						vo.setStrStoreId(strStoreId);
						bo.getItemCateg(vo);
						
						if(vo.getStrMsgType().equals("1")){
							throw new Exception(vo.getStrMsgString());
						}else{
						
							util = new HisUtil("Issue Detail Report","IssueToPatientDetailRptData");
							strItemVal=util.getOptionValue(vo.getItemCategWS(), "0","0^Select Value", false);
							formBean.setStrItemVal(strItemVal);
						
					    	
						}
				}
			} 

		 catch (Exception e) {

				String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "IssueToPatientDetailRptData->setInitDtl()", strmsgText);
			   formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		} finally {

		}

	}
	
	public static void setItemCategComboDtl(IssueToPatientDetailRptFB formBean,HttpServletRequest request,HttpServletResponse response) {

		IssueToPatientDetailRptVO vo=null;
		IssueToPatientDetailRptBO bo=null;
		String strmsgText="";
		HisUtil util = null;
		String strItemVal="";
		
		try {
				bo=new IssueToPatientDetailRptBO();
				vo=new IssueToPatientDetailRptVO();
				
				vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				
				vo.setStrStoreId(request.getParameter("strId"));
				bo.getItemCateg(vo);
				if(vo.getStrMsgType().equals("1")){
					throw new Exception(vo.getStrMsgString());
				}else{
				
					util = new HisUtil("Issue Detail Report","IssueToPatientDetailRptData");
					strItemVal=util.getOptionValue(vo.getItemCategWS(), "0","0^Select Value", false);
					response.getWriter().print(strItemVal);
			    	
				}
	
			} 

		 catch (Exception e) {

				strmsgText = "IssueToPatientDetailRptData.setItemCategComboDtl() --> "
					+ e.getMessage();
				HisException eObj = new HisException("IPD", "IssueToPatientDetailRptData->setItemCategComboDtl()", strmsgText);
			    String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			    try
			    {
			    	response.getWriter().print(response1);
			    	eObj=null;
			    }
			    catch(Exception e1)
			    {
			    	
			    }
		 }
	}
	
	public static void showReport(IssueToPatientDetailRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		String strReportCriteriaFlag="1";
		String reportPath="";
		Map<String, Object> params = new HashMap<String, Object>();
		HisUtil util = null;
		try {
			util = new HisUtil("Issue Detail Report","IssueToPatientDetailRptData");
			
			String strHospitalCode = formBean.getStrHospCode();
			String strReportId = formBean.getStrReportId();
			String strCatCode = formBean.getStrItemCategNo();
			String strFromDate = formBean.getStrFromDate();
			String strToDate = formBean.getStrToDate();
			String strUserRemarks = formBean.getStrUserRemarks();
			String strStoreId = formBean.getStrStoreId();
			reportFormat = formBean.getStrReportFormat();
			String strReportName = "Issue Details",strCurrentDate;
			String strByCurrentAndDate = (formBean.getStrByCurrentAndDate()==null||formBean.getStrByCurrentAndDate().equals(""))?"1":formBean.getStrByCurrentAndDate();
			String dossierflag = formBean.getIsdossier();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			util = new HisUtil("Issue Detail Report","IssueToPatientDetailRptData");
			strCurrentDate=util.getASDate("dd-MMM-yyyy");
			
//			System.out.println("strByCurrentAndDate"+strByCurrentAndDate);
			
			
			
			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;
				
			}
			
			if(formBean.getStrReportType()==null )
			{
				formBean.setStrReportType("1");
			}
			
			if(formBean.getStrWhetherBatchRequiredCheckBox()==null)
			{
				formBean.setStrWhetherBatchRequiredCheckBox("0");
			}
			if(formBean.getStrOnlyIssueDetailRequiredCheckBox()==null)
			{
				formBean.setStrOnlyIssueDetailRequiredCheckBox("0");
			}
			
			
		/*	if(formBean.getStrReportType().equals("1") ) // For Dept/Store
			{
				params.put("mode", "1");				
			
				if(formBean.getStrConsolidatedOrDetailed().equals("1"))	// consolidated
				{
				 strReportName = "Issue Summary Detail (Department/Store)";
					 if(formBean.getStrWhetherBatchRequiredCheckBox().equals("1"))
						{
							params.put("batchNoFlag", true);
						}
					 else
					 {
						params.put("batchNoFlag", false);
					 }
					 strReportCriteriaFlag = "1";
				 
				  reportPath = "/mms/reports/issueDetail_mmsrpt.rptdesign"; // FIRST REPORT 
				 
				}
				else if(formBean.getStrConsolidatedOrDetailed().equals("2"))	// Detailed
				{
					 strReportName = "Issue Detail (Department/Store)";
					 
					 if(formBean.getStrOnlyIssueDetailRequiredCheckBox().equals("1"))
					 {
						 reportPath = "/mms/reports/issueDetail_mmsrpt1.rptdesign";// SECOND REPORT 
						 strReportCriteriaFlag = "2";
						
					 }
					 else					//•	Report Type = “Department/Store” Detail Option is checked ,
						 					//  Only Issue Detail (Without Drug) is not checked
	 
					 {
						 reportPath = "/mms/reports/issueDetail_mmsrpt2.rptdesign";// THIRD REPORT PENDING
						 strReportCriteriaFlag = "3";
					 }
				}
			}
			
			else if(formBean.getStrReportType().equals("2") )	//For Employee
			{
				params.put("mode", "3");	
				 
				if( formBean.getStrConsolidatedOrDetailed().equals("1"))	
				{
					strReportName = "Issue Summary Detail (Employee)";
					
					 if(formBean.getStrWhetherBatchRequiredCheckBox().equals("1"))
						{
							params.put("batchNoFlag", true);
						}
					 else
					 {
						params.put("batchNoFlag", false);
					 }
					 strReportCriteriaFlag = "1";
				 
				  reportPath = "/mms/reports/issueDetail_mmsrpt4.rptdesign"; // FOURTH REPORT 
					
				}
				else if(formBean.getStrConsolidatedOrDetailed().equals("2"))
				{
					 strReportName = "Issue Detail (Employee)";
					 
					 if(formBean.getStrOnlyIssueDetailRequiredCheckBox().equals("1"))
					 {
					  strReportCriteriaFlag = "2";
					 
					  reportPath = "/mms/reports/issueDetail_mmsrpt5.rptdesign"; // FIFTH REPORT 
					 }
					 else
					 {
						 strReportCriteriaFlag = "3";
						 
						  reportPath = "/mms/reports/issueDetail_mmsrpt6.rptdesign"; // SIXTH REPORT PENDING
					 }	 
				}	
				 
			}	
			
			else if(formBean.getStrReportType().equals("3") )	// For Patient
			{
				params.put("mode", "2");	
				 
				
				if(formBean.getStrConsolidatedOrDetailed().equals("1"))
				{
					 strReportName = "Issue Summary Detail (Patient)";	
					 
					 if(formBean.getStrWhetherBatchRequiredCheckBox().equals("1"))
						{
							params.put("batchNoFlag", true);
							strReportCriteriaFlag = "4";
						}
					 else
					 {
						params.put("batchNoFlag", false);
						strReportCriteriaFlag = "1";
					 }
					
				 
					 reportPath = "/mms/reports/issueToPatientDetail_mmsrpt7.rptdesign"; // SEVENTH REPORT 
					 
				}
				else if(formBean.getStrConsolidatedOrDetailed().equals("2"))
				{
					 strReportName = "Issue Detail (Patient)";
					 
					 if(formBean.getStrOnlyIssueDetailRequiredCheckBox().equals("1"))
					 {
					  strReportCriteriaFlag = "2";
					 
					  reportPath = "/mms/reports/issueToPatientDetail_mmsrpt8.rptdesign"; // EIGHTH REPORT 
					 }
					 else
					 {
						 strReportCriteriaFlag = "3";
						 
						  reportPath = "/mms/reports/issueToPatientDetail_mmsrpt9.rptdesign"; // NINTH REPORT PENDING
					 }	 
				}				 
			}*//*			
			else
			{
				strReportName = "Issue Details";	
			}*/
			 
//			System.out.println("strFromDate"+strFromDate);
//			System.out.println("strToDate"+strToDate);
			if(strByCurrentAndDate.equals("1"))
			{
				strFromDate	=	strCurrentDate;
				strToDate	=	strCurrentDate;
			}
			if(formBean.getStrConsolidatedOrDetailed().equalsIgnoreCase("1"))
				{
				if(!dossierflag.equals("1"))
					strReportName = "Issue To Patient ";
				else
					strReportName = "Issue To Patient(only Dossier) ";
				reportPath = "/mms/reports/issueto_patient.rptdesign";
				}
			else if(formBean.getStrConsolidatedOrDetailed().equalsIgnoreCase("2"))
				{
					if(!dossierflag.equals("1"))	
						strReportName = "Return From Patient ";
					else
						strReportName = "Return from Patient(only Dossier) ";
					reportPath = "/mms/reports/returnfrom_patient.rptdesign";
				}
			else if(formBean.getStrConsolidatedOrDetailed().equalsIgnoreCase("3"))
			{
				 strReportName = "Date waise Sales & Return  From Patient ";
				reportPath = "/mms/reports/netsale_patient.rptdesign";
			}
			System.out.println("reportPath***********************************"+reportPath);
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);	
			params.put("modeval", "4");
			params.put("hospCode", strHospitalCode);
			params.put("storeId", strStoreId);
			params.put("catCode", strCatCode);
			params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
			params.put("toDate", sdf.format(sdf.parse(strToDate)));
			params.put("dossierflag", dossierflag);
			ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
			
		
			
			
			
		      	
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}


