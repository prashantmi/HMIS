package ipd.reports;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DailyWardSummaryRptDATA {

	public static void initReportPage(DailyWardSummaryRptFB formBean) {

		DailyWardSummaryRptBO bo = null;

		DailyWardSummaryRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		String strDeptVal = "";
	/*	String strUnitVal = "";
		String strWardVal = "";*/

		try {
			bo = new DailyWardSummaryRptBO();
			voObj = new DailyWardSummaryRptVO();

			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			bo.getDepartmentDetails(voObj);
		/*	bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
			
			util = new HisUtil("IPD Reports", "DailyWardSummaryRptDATA");
			strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					"0^Select Value", false);
		/*	strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);
			strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);*/

			formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrWardValues(strWardVal);
			formBean.setStrUnitValues(strUnitVal);*/
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "ipd.reports.DailyWardSummaryRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyWardSummaryRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}

	}
	public static void getWardDetailsCombo(DailyWardSummaryRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		String strmsgText = null;
		try {

			

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";
			
			if(strDeptUnitCode.equals("0"))
			{
				
				DailyWardSummaryRptDATA.getWardDetailsForAll(formBean,request, response);
				}
				
			else
				{
				
				DailyWardSummaryRptDATA.getWardDetails(formBean,request, response);
				
				}
		}
		
		catch (Exception e) {
			strmsgText = "ipd.reports.DailyWardSummaryRptDATA.getWardDetailsCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyWardSummaryRpt->getWardDetailsCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
			
		
	}
	
	public static void getWardDetailsForAll(DailyWardSummaryRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		DailyWardSummaryRptBO bo = null;

		DailyWardSummaryRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new DailyWardSummaryRptBO();
			voObj = new DailyWardSummaryRptVO();

			String strDeptCode = request.getParameter("deptCode");
			
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			bo.getWardCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
	

			util = new HisUtil("IPD Reports", "DailyWardSummaryRptDATA");
			
			String temp1= "<option value='0'>All</option>";
			
			if (voObj.getStrWardWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrWardWs(), "0", "0^All",
			
					true);
			}
			else {
				temp1 ="<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);

		} catch (Exception e) {
			strmsgText = "ipd.reports.DailyWardSummaryRptDATA.getWardDetailsForAll --> "
				+ e.getMessage();
		HisException eObj = new HisException("ipd",
				"DailyWardSummaryRpt->getWardDetailsForAll()", strmsgText);
		formBean.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");

		eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}
			
	}
	public static void getUnitWardDetails(DailyWardSummaryRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DailyWardSummaryRptBO bo = null;
		DailyWardSummaryRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new DailyWardSummaryRptBO();
			voObj = new DailyWardSummaryRptVO();

			String strDeptCode = request.getParameter("deptCode");
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("IPD Reports", "DailyWardSummaryRptDATA");
			String temp = "<option value='0'>Select Value</option>";

			String temp1 = "<option value='0'>Select Value</option>";

			if (voObj.getStrUnitWs().size() != 0) {
				temp = util.getOptionValue(voObj.getStrUnitWs(), "0", "0^All",
						true);
			}else{
				
				temp = "<option value='0'>All</option>";
			}
			if (voObj.getStrWardWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrWardWs(), "0", "0^All",
			
					true);
			}
			else {
				temp1 ="<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp+"^"+temp1);

		} catch (Exception e) {
			strmsgText = "ipd.reports.DailyWardSummaryRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyWardSummaryRptDATA->getUnitDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}

	}

	public static void getWardDetails(DailyWardSummaryRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DailyWardSummaryRptBO bo = null;
		DailyWardSummaryRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new DailyWardSummaryRptBO();
			voObj = new DailyWardSummaryRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "DailyWardSummaryRptDATA");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrWardWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrWardWs(), "0", "0^All",
						true);
			}else{
				
				temp = "<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			strmsgText = "ipd.reports.DailyWardSummaryRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyWardSummaryRptDATA->getWardDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}

	}

	public static void showReport(DailyWardSummaryRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/dailyWardSummary_ipdrpt.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			String strFromDate = formBean.getStrEffectiveFrom();
			String strToDate = formBean.getStrEffectiveTo();
			
			String strHospitalCode = formBean.getStrHospitalCode();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String strReportId = formBean.getStrReportId();
			String strReportName = "Daily Ward Summary";

			String strUserRemarks = formBean.getStrUserRemarks();

			String modeval="1";
			boolean rooterVisible = true;
			boolean IsGrandTotal = false;
			boolean IsDateWise = true;
			boolean IsConsolidated=true;

			
			if (formBean.getStrIsFooter().equals("1")) {
				rooterVisible = false;
			}
			if(formBean.getStrGrandTotalRequired().equals("0")){
				IsGrandTotal=true;
			}
			else
			{
				IsGrandTotal=false;
				
			}
			
			
			if(formBean.getStrCase().equals("1")){
				
				//strToDate=formBean.getStrCurrentDate();
				//strFromDate=formBean.getStrCurrentDate();
				modeval="1";
				IsDateWise=true;
				IsConsolidated=false;
			}
			else if(formBean.getStrCase().equals("2")){
				
				
				modeval="2";
				IsDateWise=false;
				IsConsolidated=true;
				
			}
			
			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", rooterVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
			params.put("toDate", sdf.format(sdf.parse(strToDate)));
			params.put("modeval", modeval);
			params.put("IsConsolidated", IsConsolidated);
			params.put("IsDateWise", IsDateWise);
			params.put("IsGrandTotal", IsGrandTotal);
					
			if(strDeptCode.equals("0")){
				params.put("dept_Code", "null");
			}else{
				params.put("dept_Code", strDeptCode);
			}
			if(strUnitCode.equals("0")){
				params.put("unit_Code", "null");		
			}else{
				params.put("unit_Code", strUnitCode);
			}
			if(strWardCode.equals("0")){
				params.put("ward_Code", "null");
			}else{
				params.put("ward_Code", strWardCode);
			}
			params.put("hosp_Code", strHospitalCode);
			
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

			
		
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
