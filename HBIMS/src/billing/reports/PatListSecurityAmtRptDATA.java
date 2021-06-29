package billing.reports;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billing.BillConfigUtil;

public class PatListSecurityAmtRptDATA {

	public static void initReportPage(PatListSecurityAmtRptFB formBean) {

		PatListSecurityAmtRptBO bo = null;

		PatListSecurityAmtRptVO voObj = null;

		HisUtil util = null;
		String strDeptVal = "";
		String strmsgText = null;
		try {
			bo = new PatListSecurityAmtRptBO();
			voObj = new PatListSecurityAmtRptVO();
			
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			
			bo.getDepartmentDetails(voObj);
		
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("IPD Reports","PatListSecurityAmtRptDATA");
			strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					"0^Select Value", false);
			
			formBean.setStrDeptValues(strDeptVal);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "billing.reports.PatListSecurityAmtRptDATA.initReportPage --> "
				+ e.getMessage();
			HisException eObj = new HisException("billing",
				"PatListSecurityAmtRpt->initReportPage()", strmsgText);
				   formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
				  
				   eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void getUnitDetails(PatListSecurityAmtRptFB formBean, HttpServletRequest request,
			HttpServletResponse response) {

		PatListSecurityAmtRptBO bo = null;

		PatListSecurityAmtRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new PatListSecurityAmtRptBO();
			voObj = new PatListSecurityAmtRptVO();

			String strDeptCode = request.getParameter("deptCode");

			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("Billing Reports",
						"PatListSecurityAmtRptDATA");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrUnitWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrUnitWs(), "0", "0^Select Value",
						true);

			}else {
				temp = "<option value='0'>Select Value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			strmsgText = "billing.reports.PatListSecurityAmtRptDATA.getUnitDetails --> "
				+ e.getMessage();
			HisException eObj = new HisException("billing",
				"PatListSecurityAmtRptDATA->getUnitDetails()", strmsgText);
				   formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
				  
				   eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void getWardDetails(PatListSecurityAmtRptFB formBean, HttpServletRequest request,
			HttpServletResponse response) {

		PatListSecurityAmtRptBO bo = null;
		String strmsgText = null;
		PatListSecurityAmtRptVO voObj = null;

		HisUtil util = null;

		try {

			bo = new PatListSecurityAmtRptBO();
			voObj = new PatListSecurityAmtRptVO();

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
						"PatListSecurityAmtRptDATA");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrWardWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrWardWs(), "0", "0^Select Value",
						true);

			}else {
				temp = "<option value='0'>Select Value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			strmsgText = "billing.reports.PatListSecurityAmtRptDATA.getWardDetails --> "
				+ e.getMessage();
			HisException eObj = new HisException("billing",
				"PatListSecurityAmtRptDATA->getWardDetails()", strmsgText);
				   formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
				  
				   eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void showReport(PatListSecurityAmtRptFB  formBean,
			HttpServletRequest request, HttpServletResponse response) 
	  {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/billing/reports/patListSecurityAmt_billrpt.rptdesign";
		String reportFormat = "html";
		String strmsgText = "";
		
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			BillConfigUtil billConfIg = new BillConfigUtil(formBean.getStrHospitalCode());
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			
			
			reportFormat = formBean.getStrReportFormat();	
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "Patient List Whose Security Amount Is Due";
			String strUnitCode = formBean.getStrUnitCode();
			String strUserRemarks = formBean.getStrUserRemarks();
			String strCurrentDate = formBean.getStrCurrentDate();
			String strDuration = billConfIg.getIpdPartPaymentDuration();
			
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
			params.put("duration", strDuration);
			params.put("date_Val" , sdf.format(sdf.parse(strCurrentDate)));
			
			
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

			strmsgText = "billing.reports.PatListSecurityAmtRptDATA.showReport --> "
				+ e.getMessage();
			HisException eObj = new HisException("billing",
				"PatListSecurityAmtRptDATA->showReport()", strmsgText);
				   formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
				  
				   eObj = null;
		}
	}
}
