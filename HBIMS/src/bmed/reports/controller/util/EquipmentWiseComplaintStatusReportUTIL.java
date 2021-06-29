package bmed.reports.controller.util;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.reports.controller.data.EquipmentWiseComplaintStatusReportDATA;
import bmed.reports.controller.fb.EquipmentWiseComplaintStatusReportFB;
import bmed.transactions.controller.data.ItemWarrantyDtlsDATA;
import bmed.transactions.controller.fb.ItemWarrantyDtlsFB;
import bmed.vo.ComplaintRequestDtlVO;

/**
 * @author   Adil Wasi
 * Creation Date:- 30-Aug-2012 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) Equipment Wise Complaint Status Reports
 * 
 */
public class EquipmentWiseComplaintStatusReportUTIL 
{

	/**
	 * This method is used to initialize Main Page of Report
	 * @param listOfComplaintsFB_p the ListOfComplaintsFB
	 * @param request_p	the HttpServletRequest
	 */
	public static void setInitDtl(EquipmentWiseComplaintStatusReportFB downTimeFB_p,HttpServletRequest request_p) {
	
		String strCurrentDate="";
		String strDeptVal;
		HisUtil util = null;
		
		try 
		{
			                     util = new HisUtil("bmed","Equipment Wise Complaint Status");
			    
			    	
			    String strHospitalCode=request_p.getSession().getAttribute("HOSPITAL_CODE").toString();
			    String strSeatId = request_p.getSession().getAttribute("SEATID").toString();
			    strDeptVal= EquipmentWiseComplaintStatusReportDATA.setInitDtl(strHospitalCode,strSeatId);
										
				strCurrentDate = util.getASDate("dd-MMM-yyyy");
					 
				downTimeFB_p.setStrCurrentDate(strCurrentDate);
					
				  
				//	strItemVal = util.getOptionValue(complaintRequestDtlVO.getWrsData(), "0",	"0^Select Value", false);
				downTimeFB_p.setStrDeptCmb(strDeptVal);
								
			} 
	
		 catch (Exception e) 
		 {
			 	String strmsgText = e.getMessage();
			 	HisException eObj = new HisException("BMED", "EquipmentWiseComplaintStatusReportUTIL->setInitDtl()", strmsgText);
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
	public static void getItemNameCombo(EquipmentWiseComplaintStatusReportFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		formBean_p.setStrHospCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
		EquipmentWiseComplaintStatusReportDATA.getItemNameCombo(formBean_p, request_p, response_p);
		
	}

	/**
	 * This method is used to show Report using BIRT Template
	 * 
	 * @param EquipmentWiseComplaintStatusReportFB the EquipmentWiseComplaintStatusReportFB
	 * @param request	the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	
	public static void showReport(EquipmentWiseComplaintStatusReportFB equipmentWiseComplaintStatusReportFB_p,HttpServletRequest request_p, HttpServletResponse response_p) {
	
		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		String strPassId="";
	
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			String strHospitalCode = equipmentWiseComplaintStatusReportFB_p.getStrHospCode();
			String strReportId     = equipmentWiseComplaintStatusReportFB_p.getStrReportId();
			String strFromDate     = equipmentWiseComplaintStatusReportFB_p.getStrFromDate();
			String strToDate       = equipmentWiseComplaintStatusReportFB_p.getStrToDate();
			String strUserRemarks  = equipmentWiseComplaintStatusReportFB_p.getStrUserRemarks();
			String strDeptId	   = equipmentWiseComplaintStatusReportFB_p.getStrDeptId();
			String strItemId	   = equipmentWiseComplaintStatusReportFB_p.getStrItemId();
			
			if(strItemId.equals("%"))
				strItemId = "0";
			
	        params.put("item_id", strItemId);
	        params.put("dept_code", strDeptId);
			
			reportFormat = equipmentWiseComplaintStatusReportFB_p.getStrReportFormat();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (equipmentWiseComplaintStatusReportFB_p.getStrIsFooter().equals("1")) 
			{
				footerVisible = false;
				
			}
			
			String strReportName = "Equipment Wise Complaint Status";
	
			
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hospCode", strHospitalCode);
			params.put("strPassValue", strPassId);
			params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
			params.put("toDate", sdf.format(sdf.parse(strToDate)));
			
			
			String reportPath = "/bmed/reports/jsp/bmed_equipmentWiseComplaintStatusReport_report.rptdesign";
			ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("BMED", "EquipmentWiseComplaintStatusReportUTIL->setInitDtl()", strmsgText);
			equipmentWiseComplaintStatusReportFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
		
		
	}

}

