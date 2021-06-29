package bmed.reports.controller.util;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.reports.controller.data.ListOfEquipmentUnderAmcReportDATA;
import bmed.reports.controller.fb.ListOfEquipmentUnderAmcReportFB;
import bmed.vo.HemtItemMcDtlVO;


/**
 * Developer : Vivek Aggarwal
 * Version : 1.0
 * Date : 21-June-	2011
 *
 */
public class ListOfEquipmentUnderAmcReportUTIL 
{
	/**
	 * This function is used forward control to main  page
	 * 
	 * @param listOfEquipmentUnderAmcReportFB_p	the ListOfEquipmentUnderAmcReportFB
	 * @param request_p	the	HttpServletRequest
	 */
	public static void setInitDtl(ListOfEquipmentUnderAmcReportFB listOfEquipmentUnderAmcReportFB_p,HttpServletRequest request_p)
	{
		
		HemtItemMcDtlVO	hemtItemMcDtlVO = null;
		String strCurrentDate;
		String strUniqValCmb;
		HisUtil util = null;
		
		try {
				
			    hemtItemMcDtlVO=new HemtItemMcDtlVO();
			    hemtItemMcDtlVO.setStrHospCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
			    
			    if(listOfEquipmentUnderAmcReportFB_p.getStrEquipOrVendorOrAmcType().equals("1"))
			    {
			    	hemtItemMcDtlVO.setStrMode("6");
			    }
			    else if(listOfEquipmentUnderAmcReportFB_p.getStrEquipOrVendorOrAmcType().equals("2"))
			    {
			    	hemtItemMcDtlVO.setStrMode("7");
			    }
			    else if(listOfEquipmentUnderAmcReportFB_p.getStrEquipOrVendorOrAmcType().equals("3"))
			    {
			    	hemtItemMcDtlVO.setStrMode("8");
			    }
			    else{
			    	hemtItemMcDtlVO.setStrMode("6");
			    }
			    hemtItemMcDtlVO.setStrSeatId(request_p.getSession().getAttribute("SEATID").toString());
			    
			    ListOfEquipmentUnderAmcReportDATA.setInitDtl(hemtItemMcDtlVO);
			    
				
				
				util = new HisUtil("bmed",	"List of Equipment Under Amc");
				strCurrentDate=util.getASDate("dd-MMM-yyyy");
				listOfEquipmentUnderAmcReportFB_p.setStrCurrentDate(strCurrentDate);
				
				strUniqValCmb=util.getOptionValue(hemtItemMcDtlVO.getWrsMCDetails(), "0",	"0^Select Value", false);
				
				
				listOfEquipmentUnderAmcReportFB_p.setStrUniqValCmb(strUniqValCmb);
				
			} 

		 catch (Exception e) {

				String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "ListOfEquipmentUnderAmcReportUTIL->setInitDtl()", strmsgText);
			   listOfEquipmentUnderAmcReportFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		} finally {

		}

	}	
	
	/**
	 * To show Report
	 * 
	 * @param listOfEquipmentUnderAmcReportFB_p	the ListOfEquipmentUnderAmcReportFB
	 * @param request_p	the	HttpServletRequest
	 * @param response	the HttpServletResponse
	 */
	public static void showReport(ListOfEquipmentUnderAmcReportFB listOfEquipmentUnderAmcReportFB_p,HttpServletRequest request_p, HttpServletResponse response_p) {

		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		String reportPath="";
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			String strHospitalCode = listOfEquipmentUnderAmcReportFB_p.getStrHospCode();
			String strReportId = listOfEquipmentUnderAmcReportFB_p.getStrReportId();
			String strFromDate = listOfEquipmentUnderAmcReportFB_p.getStrFromDate();
			String strToDate = listOfEquipmentUnderAmcReportFB_p.getStrToDate();
			String strUserRemarks = listOfEquipmentUnderAmcReportFB_p.getStrUserRemarks();
			
			
			if(listOfEquipmentUnderAmcReportFB_p.getStrEquipOrVendorOrAmcType().equals("1"))
			{	
				         params.put("item_id", listOfEquipmentUnderAmcReportFB_p.getStrUniqId());
				         params.put("manuf_id", "0");
				         params.put("mc_type", "0");
				         
			}
			else if(listOfEquipmentUnderAmcReportFB_p.getStrEquipOrVendorOrAmcType().equals("2"))
			{	
				 params.put("item_id", "0");
		         params.put("manuf_id", listOfEquipmentUnderAmcReportFB_p.getStrUniqId());
		         params.put("mc_type", "0");
			}
			else if(listOfEquipmentUnderAmcReportFB_p.getStrEquipOrVendorOrAmcType().equals("3"))
			{
				 params.put("item_id", "0");
		         params.put("manuf_id", "0");
		         params.put("mc_type", listOfEquipmentUnderAmcReportFB_p.getStrUniqId());
			}
			else
			{
				 params.put("item_id", "0");
		         params.put("manuf_id", "0");
		         params.put("mc_type", "0");
			}
			
			reportFormat = listOfEquipmentUnderAmcReportFB_p.getStrReportFormat();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (listOfEquipmentUnderAmcReportFB_p.getStrIsFooter().equals("1")) {
				footerVisible = false;
				
			}
			
			String strReportName = "List Of Equipment Under Amc";
			
			params.put("mode", "1");
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			
			params.put("hospCode", strHospitalCode);
			params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
			params.put("toDate", sdf.format(sdf.parse(strToDate)));
			
			/*if(listOfEquipmentUnderAmcReportFB_p.getStrEquipOrVendorOrAmcType().equals("1"))
			{
				reportPath = "/bmed/reports/jsp/bmed_ListOfEquipmentUnderAmc_report.rptdesign";
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params);
			}
			else if(listOfEquipmentUnderAmcReportFB_p.getStrEquipOrVendorOrAmcType().equals("2"))
			{
				reportPath = "/bmed/reports/jsp/bmed_ListOfEquipmentUnderAmc_report.rptdesign";
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params);
			}
			else if(listOfEquipmentUnderAmcReportFB_p.getStrEquipOrVendorOrAmcType().equals("3"))
			{
				reportPath = "/bmed/reports/jsp/bmed_ListOfEquipmentUnderAmc_report.rptdesign";
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params);
			}
			
			else 
			{*/
				
			
			reportPath = "/bmed/reports/jsp/bmed_listOfEquipmentUnderAmc_New_report.rptdesign"; // FIRST REPORT

			//	String reportPath = "/bmed/reports/jsp/bmed_listOfEquipmentUnderAmc_rpt.rptdesign";
				
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			/*}*/
		}
		catch (Exception e) 
		{
		   String strmsgText = e.getMessage();
		   HisException eObj = new HisException("ggsh", "ListOfEquipmentUnderAmcReportUTIL->setInitDtl()", strmsgText);
		   listOfEquipmentUnderAmcReportFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		   eObj = null;
		}
		finally 
		{
			
		}
	}
}