package billing.reports;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAbscondedPatRptDATA {

	public static void initReportPage(ListAbscondedPatRptFB formBean) {

		ListAbscondedPatRptBO bo = null;

		ListAbscondedPatRptVO voObj = null;

		HisUtil util = null;
		String strDeptVal = "";
    	String strmsgText = null;
		try {
			bo = new ListAbscondedPatRptBO();
			voObj = new ListAbscondedPatRptVO();
			
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			
			bo.getDepartmentDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","ListAbscondedPatRptDATA");
			strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					"0^Select Value", false);
			
			formBean.setStrDeptValues(strDeptVal);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "billing.reports.ListAbscondedPatRptDATA.initReportPage --> "
				+ e.getMessage();
			HisException eObj = new HisException("billing",
				"ListAbscondedPatRptDATA->initReportPage()", strmsgText);
				   formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
				  
				   eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void getUnitDetails(ListAbscondedPatRptFB formBean, HttpServletRequest request,
			HttpServletResponse response) {

		ListAbscondedPatRptBO bo = null;

		ListAbscondedPatRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new ListAbscondedPatRptBO();
			voObj = new ListAbscondedPatRptVO();

			String strDeptCode = request.getParameter("deptCode");

			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports",
						"ListAbscondedPatRptDATA");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrUnitWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrUnitWs(), "0", "0^Select Value",
						true);

			}else {
				temp = "<option value='0'>Select Value<option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			strmsgText = "billing.reports.ListAbscondedPatRptDATA.getUnitDetails --> "
				+ e.getMessage();
			HisException eObj = new HisException("billing",
				"ListAbscondedPatRptDATA->getUnitDetails()", strmsgText);
				   formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
				  
				   eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void getWardDetails(ListAbscondedPatRptFB formBean, HttpServletRequest request,
			HttpServletResponse response) {

		ListAbscondedPatRptBO bo = null;
		String strmsgText = null;
		ListAbscondedPatRptVO voObj = null;

		HisUtil util = null;

		try {

			bo = new ListAbscondedPatRptBO();
			voObj = new ListAbscondedPatRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");
		
			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports",
						"ListAbscondedPatRptDATA");
			String temp = "<option value='0'>Select Value</option>";

					
			if (voObj.getStrWardWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrWardWs(), "0", "0^Select Value",
						true);

			}else {
				temp = "<option value='0'>Select Value<option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			strmsgText = "billing.reports.ListAbscondedPatRptDATA.getWardDetails --> "
				+ e.getMessage();
			HisException eObj = new HisException("billing",
				"ListAbscondedPatRptDATA->getWardDetails()", strmsgText);
				   formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
				  
			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	

	public static void showReport(ListAbscondedPatRptFB  formBean,
			HttpServletRequest request, HttpServletResponse response) 
	  {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/billing/reports/listAbscondedPat_billrpt.rptdesign";
		String reportFormat = "html";
		Map<String, Object> params = new HashMap<String, Object>();
		String strmsgText = "";
		
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			reportFormat = formBean.getStrReportFormat();	
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "List Of Absconded Patients";
			String strUnitCode = formBean.getStrUnitCode();
			String strUserRemarks = formBean.getStrUserRemarks();
			String strFromDate = formBean.getStrEffectiveFrom();
			String strToDate = formBean.getStrEffectiveTo();
			
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
			params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
			params.put("to_Date", sdf.format(sdf.parse(strToDate)));
			
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
		} 
		catch (Exception e) {

			strmsgText = "billing.reports.ListAbscondedPatRptDATA.showReport --> "
				+ e.getMessage();
			HisException eObj = new HisException("billing",
				"ListAbscondedPatRptDATA->showReport()", strmsgText);
				   formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;	
			}
	  }
}
