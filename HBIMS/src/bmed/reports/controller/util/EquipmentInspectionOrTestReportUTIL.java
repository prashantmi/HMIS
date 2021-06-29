package bmed.reports.controller.util;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.reports.controller.data.EquipmentInspectionOrTestReportDATA;
import bmed.reports.controller.fb.EquipmentInspectionOrTestReportFB;
import bmed.reports.vo.EquipmentInspectionOrTestReportVO;

/**
 * @author   Adil Wasi
 * Creation Date:- 03-Sept-2012
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) Equipment Inspection/Test Report
 * 
 */
public class EquipmentInspectionOrTestReportUTIL 
{

	/**
	 * This method is used to initialize Main Page of Report
	 * @param listOfComplaintsFB_p the EquipmentInspectionOrTestReportFB
	 * @param request_p	the HttpServletRequest
	 */
	public static void setInitDtl(EquipmentInspectionOrTestReportFB equipmentInspectionOrTestReportFB_p,HttpServletRequest request_p) {
	
		EquipmentInspectionOrTestReportVO equipmentInspectionOrTestReportVO=null;
		String strCurrentDate="";
		String strStoreVal,strItemVal;
		HisUtil util = null;
		
		try 
		{
			    equipmentInspectionOrTestReportVO = new EquipmentInspectionOrTestReportVO();
			                     util = new HisUtil("bmed","Equipment Inspection/Test Report");
			    
			    //equipmentInspectionOrTestReportVO.setStrMode("1");
			    equipmentInspectionOrTestReportVO.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
			    equipmentInspectionOrTestReportVO.setStrSeatid(request_p.getSession().getAttribute("SEATID").toString());
	
			    
			    
										
					  strCurrentDate = util.getASDate("dd-MMM-yyyy");
					  strStoreVal = EquipmentInspectionOrTestReportDATA.setStoreDtl(equipmentInspectionOrTestReportVO);
						equipmentInspectionOrTestReportFB_p.setStrStoreCmb(strStoreVal);
						equipmentInspectionOrTestReportFB_p.setStrCurrentDate(strCurrentDate);
						
				//equipmentInspectionOrTestReportVO.setStrMode("2");
				equipmentInspectionOrTestReportVO.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
				
			    
				equipmentInspectionOrTestReportFB_p.setStrTestCmb(EquipmentInspectionOrTestReportDATA.setTestDtl(equipmentInspectionOrTestReportVO));
				equipmentInspectionOrTestReportFB_p.setStrEquipmentTestChkDetail("1");
								
			} 
	
		 catch (Exception e) 
		 {
			 	String strmsgText = e.getMessage();
			 	HisException eObj = new HisException("BMED", "EquipmentInspectionOrTestReportUTIL->setInitDtl()", strmsgText);
			 	equipmentInspectionOrTestReportFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			 	eObj = null;
		} 
		finally 
		{
		}
	}

	/**
	 * This method is used to get Item Name for Equipment Name Combo
	 * 
	 * @param formBean_p the EquipmentInspectionOrTestReportFB
	 * @param request	the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	public static void getItemNameCombo(EquipmentInspectionOrTestReportFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		formBean_p.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
		EquipmentInspectionOrTestReportDATA.getItemNameCombo(formBean_p, request_p, response_p);
		
	}
	
	/**
	 * This method is used to get Equipment Test Combo
	 * 
	 * @param formBean_p the EquipmentInspectionOrTestReportFB
	 * @param request	the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	public static void getEquipmentTestCombo(EquipmentInspectionOrTestReportFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		formBean_p.setStrHospitalCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
		EquipmentInspectionOrTestReportDATA.getEquipmentTestCombo(formBean_p, request_p, response_p);
		
	}

	/**
	 * This method is used to show Report using BIRT Template
	 * 
	 * @param EquipmentInspectionOrTestReportFB the EquipmentInspectionOrTestReportFB
	 * @param request	the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	
	public static void showReport(EquipmentInspectionOrTestReportFB equipmentInspectionOrTestReportFB_p,HttpServletRequest request_p, HttpServletResponse response_p) {
	
		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		String strPassId="";
	
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			String strHospitalCode = equipmentInspectionOrTestReportFB_p.getStrHospitalCode();
			String strReportId     = equipmentInspectionOrTestReportFB_p.getStrReportId();
			String strUniqId       = equipmentInspectionOrTestReportFB_p.getStrUniqId();
			String strFromDate     = equipmentInspectionOrTestReportFB_p.getStrFromDate();
			String strToDate       = equipmentInspectionOrTestReportFB_p.getStrToDate();
			String strUserRemarks  = equipmentInspectionOrTestReportFB_p.getStrUserRemarks();
			
			params.put("store_id", equipmentInspectionOrTestReportFB_p.getStrStoreId());
			if(strUniqId.equals("1"))
			{	
				params.put("mode", "1");
				params.put("item_id", equipmentInspectionOrTestReportFB_p.getStrItemId());
				params.put("test_id", "0");
				params.put("equipmentTest_SlNo","0");
			}
			else if(strUniqId.equals("2"))
			{	
				 params.put("mode", "2");
		         params.put("test_id", equipmentInspectionOrTestReportFB_p.getStrTestId());
		         params.put("item_id", "0");
		         params.put("equipmentTest_SlNo","0");
			}
			else 
			{	
				params.put("mode", "3");
				params.put("item_id", equipmentInspectionOrTestReportFB_p.getStrItemId());
				params.put("equipmentTest_SlNo",equipmentInspectionOrTestReportFB_p.getStrEquipmentTestSlNoId());
				params.put("test_id", "0");
			}
			
			          reportFormat = equipmentInspectionOrTestReportFB_p.getStrReportFormat();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (equipmentInspectionOrTestReportFB_p.getStrIsFooter().equals("1")) 
			{
				footerVisible = false;
				
			}
			
			String strReportName = "Equipment Inspection/Test Report";
	
			
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hospCode", strHospitalCode);
			params.put("strPassValue", strPassId);
			params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
			params.put("toDate", sdf.format(sdf.parse(strToDate)));
			
			
			if(equipmentInspectionOrTestReportFB_p.getStrUniqId().equals("1"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_equipment_inspection_equipment_wise_report.rptdesign";
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			}
			else if(equipmentInspectionOrTestReportFB_p.getStrUniqId().equals("2"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_equipment_inspection_test_wise_report.rptdesign";
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			}
			else if(equipmentInspectionOrTestReportFB_p.getStrUniqId().equals("3"))
			{
				String reportPath = "/bmed/reports/jsp/bmed_equipment_inspection_equipmentTestSlNo_wise_rpt.rptdesign";
				ts.displayReport(request_p, response_p, reportPath, reportFormat,params,strHospitalCode);
			}
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("BMED", "EquipmentInspectionOrTestReportDATA->showReport()", strmsgText);
			equipmentInspectionOrTestReportFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
		
		
	}

}

