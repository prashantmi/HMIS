package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListMLCDeathRptDATA {

	public static void initReportPage(ListMLCDeathRptFB formBean,HttpServletRequest request) {

		ListMLCDeathRptBO bo = null;
		ListMLCDeathRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null ;
		//String strDeptVal = "";
		String strHospNameValues = "";
		/*String strWardVal = "";
		String strUnitVal = "";*/

		try {
			bo = new ListMLCDeathRptBO();
			voObj = new ListMLCDeathRptVO();

			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			//voObj.setStrSeatId(formBean.getStrSeatId());
			bo.getHospitalName(voObj);

			
			//bo.getDepartmentDetails(voObj);
		/*	bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
			util = new HisUtil("IPD Reports", "ListMLCDeathRptDATA");
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0",
					"0^Select Value", false);	
			//strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					//"0^Select Value", false);
		/*	strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);
			strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);*/

		//	formBean.setStrDeptValues(strDeptVal);
			formBean.setStrHospNameValues(strHospNameValues);
		/*	formBean.setStrWardValues(strWardVal);
			formBean.setStrUnitValues(strUnitVal);*/
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));


			
		} catch (Exception e) {
			strmsgText = "ipd.reports.ListMLCDeathRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ListMLCDeathRpt->initReportPage()", strmsgText);
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
	
	public static void getdeptComboDetails(ListMLCDeathRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		ListMLCDeathRptBO bo = null;
		ListMLCDeathRptVO voObj = null;
		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new ListMLCDeathRptBO();
			voObj = new ListMLCDeathRptVO();


			String strHospitalCode=request.getParameter("hospCode"); // Getting Form Bean's Hospital Code From Session
			
			voObj.setStrHospitalCode((strHospitalCode==null || strHospitalCode=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():strHospitalCode);

			bo.getdeptComboDetails(voObj);
			
			util = new HisUtil("IPD Reports", "ListMLCDeathRptDATA");
			
			String temp1= "<option value='0'>Select Value</option>";
			
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
			strmsgText = "ipd.reports.ListMLCDeathRptDATA.getdeptComboDetails --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","ListMLCDeathRptDATA->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	
	public static void getWardDetailsCombo(ListMLCDeathRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		String strmsgText = null;
		try {

			

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";
			
			if(strDeptUnitCode.equals("0"))
			{
				
				ListMLCDeathRptDATA.getWardDetailsForAll(formBean,request, response);
				}
				
			else
				{
				
				ListMLCDeathRptDATA.getWardDetails(formBean,request, response);
				
				}
		}
		
		catch (Exception e) {
			strmsgText = "ipd.reports.ListMLCDeathRptDATA.getWardDetailsCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ListMLCDeathRpt->getWardDetailsCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
			
		
	}
	
	public static void getWardDetailsForAll(ListMLCDeathRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		ListMLCDeathRptBO bo = null;

		ListMLCDeathRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new ListMLCDeathRptBO();
			voObj = new ListMLCDeathRptVO();

			String strDeptCode = request.getParameter("deptCode");
			
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			bo.getWardCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
	

			util = new HisUtil("IPD Reports", "ListMLCDeathRptDATA");
			
			String temp1= "<option value='0'>Select Value</option>";
			
			if (voObj.getStrWardWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrWardWs(), "0", "0^Select Value",
			
					true);
			}
			else {
				temp1 ="<option value='0'>Select Value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);

		} catch (Exception e) {
			strmsgText = "ipd.reports.ListMLCDeathRptDATA.getWardDetailsForAll --> "
				+ e.getMessage();
		HisException eObj = new HisException("ipd",
				"ListMLCDeathRpt->getWardDetailsForAll()", strmsgText);
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
	public static void getUnitWardDetails(ListMLCDeathRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ListMLCDeathRptBO bo = null;
		ListMLCDeathRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new ListMLCDeathRptBO();
			voObj = new ListMLCDeathRptVO();

			String strDeptCode = request.getParameter("deptCode");
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "ListMLCCaseRptDATA");
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
			strmsgText = "ipd.reports.ListMLCDeathRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ListMLCDeathRpt->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(ListMLCDeathRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ListMLCDeathRptBO bo = null;
		ListMLCDeathRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new ListMLCDeathRptBO();
			voObj = new ListMLCDeathRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");
			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "ListMLCCaseRptDATA");
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
			strmsgText = "ipd.reports.ListMLCDeathRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ListMLCDeathRpt->getWardDetails()", strmsgText);
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


	public static void showReport(ListMLCDeathRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/listMLCDeath_ipdrpt.rptdesign";
		String reportFormat = "html";
		HisUtil util = null;
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			util = new HisUtil("IPD Reports", "ListMLCDeathRptDATA");
			
			reportFormat = formBean.getStrReportFormat();
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			String strFromTime = formBean.getStrFromTime();
			String strToTime = formBean.getStrToTime();
			String currTime = formBean.getStrCurrentTime();
			String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strFromTime);
			String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?currTime:strToTime);
			String strCurrDate = formBean.getStrCurrentDate();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportName = "List Of MLC Deaths";
			String strReportId = formBean.getStrReportId();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			String strUserRemarks = formBean.getStrUserRemarks();
			
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
