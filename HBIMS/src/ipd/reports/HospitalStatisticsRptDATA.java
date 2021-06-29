package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HospitalStatisticsRptDATA {

	public static void initReportPage(HospitalStatisticsRptFB formBean,HttpServletRequest request) {

		HospitalStatisticsRptBO bo = null;

		HospitalStatisticsRptVO voObj = null;

		HisUtil util = null;
		String strDeptVal = "";
		String strmsgText = null;
		String strHospName="";
		/*String strUnitVal = "";
		String strWardVal = "";
*/

		try {
			bo = new HospitalStatisticsRptBO();
			voObj = new HospitalStatisticsRptVO();
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			bo.getHospitalName(voObj);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			
			//bo.getDepartmentDetails(voObj);
		/*	bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
			util = new HisUtil("IPD Reports", "HospitalStatisticsRptDATA");
		
			strHospName= util.getOptionValue(voObj.getStrHospitalWs(),"0","0^Select Value", false);
			//strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					//"0^Select Value", false);
			/*strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);
			strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);*/

			//formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrWardValues(strWardVal);
			formBean.setStrUnitValues(strUnitVal);*/
			formBean.setStrHospNameValues(strHospName);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "ipd.reports.HospitalStatisticsRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"HospitalStatisticsRptDATA->initReportPage()", strmsgText);
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
	
	public static void getdeptComboDetails(HospitalStatisticsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		HospitalStatisticsRptBO bo = null;

		HospitalStatisticsRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new HospitalStatisticsRptBO();
			voObj = new HospitalStatisticsRptVO();

			String strhospCode=request.getParameter("hospCode").toString();

			
			if (strhospCode == null)
				strhospCode = "0";
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getdeptComboDetails(voObj);
			
			util = new HisUtil("IPD Reports", "HospitalStatisticsRptDATA");
			
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
			strmsgText = "ipd.reports.HospitalStatisticsRptDATA.getWardDetailsCombo --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","HospitalStatisticsRpt->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	
	public static void getWardDetailsCombo(HospitalStatisticsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		String strmsgText = null;
		try {

			

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";
			
			if(strDeptUnitCode.equals("0"))
			{
				
				HospitalStatisticsRptDATA.getWardDetailsForAll(formBean,request, response);
				}
				
			else
				{
				
				HospitalStatisticsRptDATA.getWardDetails(formBean,request, response);
				
				}
		}
		
		catch (Exception e) {
			strmsgText = "ipd.reports.HospitalStatisticsRptDATA.getWardDetailsCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"HospitalStatisticsRpt->getWardDetailsCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
			
		
	}
	
	public static void getWardDetailsForAll(HospitalStatisticsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		HospitalStatisticsRptBO bo = null;

		HospitalStatisticsRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new HospitalStatisticsRptBO();
			voObj = new HospitalStatisticsRptVO();

			String strDeptCode = request.getParameter("deptCode");
			
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			bo.getWardCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
	

			util = new HisUtil("IPD Reports", "HospitalStatisticsRptDATA");
			
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
			strmsgText = "ipd.reports.HospitalStatisticsRptDATA.getWardDetailsForAll --> "
				+ e.getMessage();
		HisException eObj = new HisException("ipd",
				"HospitalStatisticsRpt->getWardDetailsForAll()", strmsgText);
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
	public static void getUnitWardDetails(HospitalStatisticsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		HospitalStatisticsRptBO bo = null;
		HospitalStatisticsRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new HospitalStatisticsRptBO();
			voObj = new HospitalStatisticsRptVO();

			String strDeptCode = request.getParameter("deptCode");
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "HospitalStatisticsRptDATA");
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
			strmsgText = "ipd.reports.HospitalStatisticsRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"HospitalStatisticsRptDATA->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(HospitalStatisticsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		HospitalStatisticsRptBO bo = null;

		HospitalStatisticsRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new HospitalStatisticsRptBO();
			voObj = new HospitalStatisticsRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("IPD Reports", "HospitalStatisticsRptDATA");
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
			strmsgText = "ipd.reports.HospitalStatisticsRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"HospitalStatisticsRptDATA->getWardDetails()", strmsgText);
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

	public static void showReport(HospitalStatisticsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/hospStatistics_ipdrpt.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strFromDate = formBean.getStrEffectiveFrom();
			String strToDate = formBean.getStrEffectiveTo();
			String strReportName = "Hospital Statistics";
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

			String strUserRemarks = formBean.getStrUserRemarks();
		
			boolean rooterVisible = true;

			if (formBean.getStrIsFooter().equals("1")) {
				rooterVisible = false;
			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", rooterVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
			params.put("to_Date", sdf.format(sdf.parse(strToDate)));
			params.put("hosp_Code", strHospitalCode);
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
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);


		} catch (Exception e) {

			//e.printStackTrace();

		}
	}
}
