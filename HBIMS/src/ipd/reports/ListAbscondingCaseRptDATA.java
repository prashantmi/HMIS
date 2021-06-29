package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAbscondingCaseRptDATA {

	public static void initReportPage(ListAbscondingCaseRptFB formBean,HttpServletRequest request)  {

		ListAbscondingCaseRptBO bo = null;
		ListAbscondingCaseRptVO voObj = null;
		HisUtil util = null;
		String strDeptVal = "";
		/*String strWardVal = "";
		String strUnitVal = "";*/
		String strmsgText = null;
		String strHospName="";

		try {
			bo = new ListAbscondingCaseRptBO();
			voObj = new ListAbscondingCaseRptVO();

			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			bo.getHospitalName(voObj);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			//bo.getDepartmentDetails(voObj);
		/*	bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);
*/
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
			util =new HisUtil("IPD Reports", "ListAdscondingCaseRptDATA");
			strHospName= util.getOptionValue(voObj.getStrHospitalWs(),"0","0^Select Value", false);
		//	strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
			//		"0^Select Value", false);
			/*strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);
			strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);*/

			//formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrWardValues(strWardVal);
			formBean.setStrUnitValues(strUnitVal);*/
			
			//formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrHospNameValues(strHospName);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));
			
			
		} catch (Exception e) {

			strmsgText = "ipd.reports.ListAdscondingCaseRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ListAdscondingCaseRptDATA->initReportPage()", strmsgText);
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

	public static void getdeptComboDetails(ListAbscondingCaseRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		ListAbscondingCaseRptBO bo = null;

		ListAbscondingCaseRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new ListAbscondingCaseRptBO();
			voObj = new ListAbscondingCaseRptVO();

			String strhospCode=request.getParameter("hospCode").toString();

			
			if (strhospCode == null)
				strhospCode = "0";
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getDepartmentDetails(voObj);
			
			util = new HisUtil("IPD Reports", "ListAbscondingCaseRptDATA");
			
			String temp1= "<option value='0'>All</option>";
			
			if (voObj.getStrDeptWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrDeptWs(), "0", "0^Select Value",true);
			}
			else {
				temp1 ="<option value='0'>Select Value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);
		}
		
		catch (Exception e) 
		{
			strmsgText = "ipd.reports.ListAbscondingCaseRptDATA.getWardDetailsCombo --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","ListAbscondingCaseRpt->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	public static void getUnitWardDetails(ListAbscondingCaseRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ListAbscondingCaseRptBO bo = null;
		ListAbscondingCaseRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new ListAbscondingCaseRptBO();
			voObj = new ListAbscondingCaseRptVO();

			String strDeptCode = request.getParameter("deptCode");
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("IPD Reports", "ListAdscondingCaseRptDATA");
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
			
			strmsgText = "ipd.reports.ListAdscondingCaseRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ListAdscondingCaseRptDATA->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(ListAbscondingCaseRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ListAbscondingCaseRptBO bo = null;
		ListAbscondingCaseRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		try {

			bo = new ListAbscondingCaseRptBO();
			voObj = new ListAbscondingCaseRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");
			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "ListAdscondingCaseRptDATA");
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
			
			strmsgText = "ipd.reports.ListAdscondingCaseRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ListAdscondingCaseRptDATA->getWardDetails()", strmsgText);
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


	public static void showReport(ListAbscondingCaseRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/listAbscondingCase_ipdrpt.rptdesign";
		String reportFormat = "html";
		HisUtil util = null;
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			util = new HisUtil("IPD Reports", "ListAdscondingCaseRptDATA");
			
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			reportFormat = formBean.getStrReportFormat();
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			String strFromDate = formBean.getStrEffectiveFrom();
			//String strCurrDate = formBean.getStrCurrentDate();
			String strToDate = formBean.getStrEffectiveTo();
			//String strHospitalCode = formBean.getStrHospitalCode();
			 String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();

			//String strReportId = formBean.getStrReportId();
			 String strReportId = formBean.getStrReportId();
			//SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			
			
		
			 String strFromTime = formBean.getStrFromTime();
				String strToTime = formBean.getStrToTime();
				String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strFromTime);
				String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strToTime);
				String strCurrDate = formBean.getStrCurrentDate();
				String strUserRemarks = formBean.getStrUserRemarks();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			
			
			
			String strReportName = "List Of Absconding Cases";
			
			boolean footerVisible = true;
			boolean isWardWise = true;
			boolean isDateWise = true;

			if(formBean.getStrCase().equals("1")){
				
				isWardWise = false;
				params.put("report_Criteria", "Ward Wise");
				
				params.put("ward_Code", strWardCode);
				params.put("date_Code", "null");
				
			}else{
				isDateWise = false;
				params.put("report_Criteria", "Date Wise");
				
				params.put("ward_Code", "null");
				params.put("date_Code", strCurrDate);
			}
			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;
			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("IsDateWise", isDateWise);
			params.put("IsWardWise", isWardWise);
			//params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
			//params.put("to_Date", sdf.format(sdf.parse(strToDate)));
			
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
			params.put("report_user_Remarks", strUserRemarks);
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
			params.put("hosp_Code", strHospitalCode);

			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			//e.printStackTrace();

		}
	}
}
