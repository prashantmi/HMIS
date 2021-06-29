package billing.reports;

import ipd.reports.CurrentlyAdmittedPatientRptBO;
import ipd.reports.CurrentlyAdmittedPatientRptFB;
import ipd.reports.CurrentlyAdmittedPatientRptVO;

import java.util.HashMap;
import java.util.Map;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DueDetailsRptDATA {
	
	public static void initReportPage(DueDetailsRptFB formBean, HttpServletRequest req) {

		DueDetailsRptBO bo =  null;

		DueDetailsRptVO voObj = null;

		HisUtil util = new HisUtil("Billing Reports", "DueDetailsRptDATA");
		String strmsgText = null;
		String strHospNameValues="";
		try {
			bo = new DueDetailsRptBO();
			voObj =  new DueDetailsRptVO();
			 
			//voObj.setStrHospitalCode(req.getSession().getAttribute("HOSPITAL_CODE").toString());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?req.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(req.getSession().getAttribute("SEATID").toString());
			bo.getHospitalName(voObj);
			
			bo.getDeptDetails(voObj);
			
		if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
		voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?req.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());


		strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0",
							"0^Select Value", false);	

		formBean.setStrHospNameValues(strHospNameValues);

	    	//String	strDeptValues = util.getOptionValue(voObj.getStrDeptWs(),
					//"0", "0^Select Value", false);
		
			//formBean.setStrDeptValues(strDeptValues);
					
		} catch (Exception e) {
			strmsgText = "billing.reports.DueDetailsRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DueDetailsRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}

	public static void getdeptComboDetails(DueDetailsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		DueDetailsRptBO bo = null;

		DueDetailsRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new DueDetailsRptBO();
			voObj = new DueDetailsRptVO();
			String strhospCode=request.getParameter("hospCode").toString();

			
			if (strhospCode == null)
				strhospCode = "0";
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
           
			bo.getDeptDetails(voObj);
		//	bo.getDepartmentDetails(voObj);
			
			util = new HisUtil("IPD Reports", "DueDetailsRptDATA");
			
			String temp1= "<option value='0'>Select Value</option>";
			
			if (voObj.getStrDeptWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrDeptWs(), "0", "0^Select Value",true);
			}
			else {
				temp1 ="<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);
		}
		
		catch (Exception e) 
		{
			strmsgText = "ipd.reports.DueDetailsRptDATA.getWardDetailsCombo --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","DueDetailsRptDATA->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	
	public static void getUNITCMB(DueDetailsRptFB formBean, HttpServletRequest req ,HttpServletResponse res) 
	   {
	
		DueDetailsRptBO bo =  null;
	
		DueDetailsRptVO voObj = null;
		HisUtil util = new HisUtil("Billing Reports", "DueDetailsRptDATA");
		
		String strmsgText = null;
		try {
			bo = new DueDetailsRptBO();
			voObj = new DueDetailsRptVO();
	
			String strDeptCode = req.getParameter("deptCode");
			
			
	
			if (strDeptCode == null)
				strDeptCode = "0";
		    
			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
	
			bo.getUNITCMB(voObj);
	
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
	
			}
	
			util = new HisUtil("Billing Reports", " Patient Report");
			String temp = "<option value='0'>Select Value</option>";
	
			if (voObj.getStrUnitWs().size() != 0) 
			  {
	
				temp = util.getOptionValue(voObj.getStrUnitWs(), "0", "0^Select Value",
						true);
	
			}else {
				temp = "<option value='0'>Select Value</option>";
			}
		
			res.setHeader("Cache-Control", "no-cache");
			res.getWriter().print(temp);
	
			} 
		  catch (Exception e) {
			strmsgText = "billing.reports.DueDetailsRptDATA.getUNITCMB --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DueDetailsRptDATA->getUNITCMB", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");
	
			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}
	
	}
	public static void getWARDCMB(DueDetailsRptFB formBean, HttpServletRequest req ,HttpServletResponse res) 
	{
	
		DueDetailsRptBO bo =  null;
	
		DueDetailsRptVO voObj = null;
		HisUtil util = new HisUtil("Billing Reports", "DueDetailsRptDATA");
		
		String strmsgText = null;
		try {
			bo = new DueDetailsRptBO();
			voObj = new DueDetailsRptVO();
	
			String strUnitCode = req.getParameter("unitCode");
			if (strUnitCode == null)
				strUnitCode = "0";
		    
			voObj.setStrUnitCode(strUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
	
			bo.getWARDCMB(voObj);
	
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
	
			}
	
			util = new HisUtil("Billing Reports", " Patient Report");
			String temp = "<option value='0'>Select Value</option>";
	
			if (voObj.getStrWardWs().size() != 0) 
			  {
	
				temp = util.getOptionValue(voObj.getStrWardWs(), "0", "0^Select Value",
						true);
	
			}else {
				temp = "<option value='0'>Select Value</option>";
			}
		
			res.setHeader("Cache-Control", "no-cache");
			res.getWriter().print(temp);
			
				} 
		  catch (Exception e) {
			strmsgText = "billing.reports.DueDetailsRptDATA.getUNITCMB --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DueDetailsRptDATA->getUNITCMB", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");
	
			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}
	
	}
	public static void showReport(DueDetailsRptFB  formBean,
		HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();
		String reportPath = "/billing/reports/dueDtl_billrpt.rptdesign";
		String reportFormat = "html";
		String strmsgText = "";
		
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			reportFormat = formBean.getStrReportFormat();	
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "Due Detail Report";
			String strUnitCode = formBean.getStrUnitCode();
			String strUserRemarks = formBean.getStrUserRemarks();
		
			boolean footerVisible = true;	
			
			
			
			if (formBean.getStrIsFooter().equals("1")) 
			
			{
	
				footerVisible = false;
	
			}

			reportFormat = formBean.getStrReportFormat();
	        params.put("report_Id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
			
		
			if(strDeptCode.equals("0")){
				params.put("dept_Code", "null");
			}else{
				params.put("dept_Code", strDeptCode);
			}
			if(strWardCode.equals("0")){
				params.put("ward_Code", "null");
			}else{
				params.put("ward_Code", strWardCode);
			}
			
			if(strUnitCode.equals("0")){
				params.put("unit_Code", "null");
			}else{
				params.put("unit_Code", strUnitCode);
			}
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);
	
		} catch (Exception e) {
	
			strmsgText = "billing.reports.DueDetailsRptDATA.showReport --> "
				+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DueDetailsRpt->showReport()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");
	
			eObj = null;	
		}
	}
}


