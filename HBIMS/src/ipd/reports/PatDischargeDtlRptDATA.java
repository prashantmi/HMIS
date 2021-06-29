package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatDischargeDtlRptDATA {

	public static void initReportPage(PatDischargeDtlRptFB formBean,HttpServletRequest request) {

		PatDischargeDtlRptBO bo = null;
		PatDischargeDtlRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
	//	String strDeptVal = "";
		String strHospNameValues = "";

		/*String strUnitVal = "";
		String strWardVal = "";*/
		

		try {
			bo = new PatDischargeDtlRptBO();
			voObj = new PatDischargeDtlRptVO();

			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			bo.getHospitalName(voObj);
			//bo.getDepartmentDetails(voObj);
			/*bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/
			
			
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			
			util = new HisUtil("IPD Reports", "PatDischargeDtlDATA");
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0",
					"0^Select Value", false);	

		//	strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
				//	"0^Select Value", false);
			/*strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);
			strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);
			*/
			
			formBean.setStrHospNameValues(strHospNameValues);
			
		//	formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrUnitValues(strUnitVal);
			formBean.setStrWardValues(strWardVal);*/
		
			
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));

		} catch (Exception e) {
			strmsgText = "ipd.reports.PatDischargeDtlDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatDischargeDtl->initReportPage()", strmsgText);
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
	public static void getdeptComboDetails(PatDischargeDtlRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		PatDischargeDtlRptBO bo = null;
		PatDischargeDtlRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new PatDischargeDtlRptBO();
			voObj = new PatDischargeDtlRptVO();

			String strHospitalCode=request.getParameter("hospCode"); // Getting Form Bean's Hospital Code From Session
			
			voObj.setStrHospitalCode((strHospitalCode==null || strHospitalCode=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():strHospitalCode);

			bo.getdeptComboDetails(voObj);
			
			util = new HisUtil("IPD Reports", "PatDischargeDtlRptDATA");
			
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
			strmsgText = "ipd.reports.PatDischargeDtlRptDATA.getdeptComboDetails --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","PatDischargeDtlRptDATA->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}

	public static void getWardDetailsCombo(PatDischargeDtlRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		String strmsgText = null;
		try {

			

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";
			
			if(strDeptUnitCode.equals("0"))
			{
				
				PatDischargeDtlRptDATA.getWardDetailsForAll(formBean,request, response);
				}
				
			else
				{
				
				PatDischargeDtlRptDATA.getWardDetails(formBean,request, response);
				
				}
		}
		
		catch (Exception e) {
			strmsgText = "ipd.reports.PatDischargeDtlRptDATA.getWardDetailsCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatDischargeDtlRpt->getWardDetailsCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
			
		
	}
	
	public static void getWardDetailsForAll(PatDischargeDtlRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		PatDischargeDtlRptBO bo = null;

		PatDischargeDtlRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new PatDischargeDtlRptBO();
			voObj = new PatDischargeDtlRptVO();

			String strDeptCode = request.getParameter("deptCode");
			
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			bo.getWardCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
	

			util = new HisUtil("IPD Reports", "PatDischargeDtlRptDATA");
			
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
			strmsgText = "ipd.reports.PatDischargeDtlRptDATA.getWardDetailsForAll --> "
				+ e.getMessage();
		HisException eObj = new HisException("ipd",
				"PatDischargeDtlRpt->getWardDetailsForAll()", strmsgText);
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
	public static void getUnitWardDetails(PatDischargeDtlRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PatDischargeDtlRptBO bo = null;
		String strmsgText = null;
		PatDischargeDtlRptVO voObj = null;

		HisUtil util = null;

		try {

			bo = new PatDischargeDtlRptBO();
			voObj = new PatDischargeDtlRptVO();

			String strDeptCode = request.getParameter("deptCode");

			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "Patient Discharge Detail Report");
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
			strmsgText = "ipd.reports.PatDischargeDtlDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatDischargeDtl->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(PatDischargeDtlRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PatDischargeDtlRptBO bo = null;
		String strmsgText = null;
		PatDischargeDtlRptVO voObj = null;

		HisUtil util = null;

		try {

			bo = new PatDischargeDtlRptBO();
			voObj = new PatDischargeDtlRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "Patient Discharge Detail Report");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrWardWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrWardWs(), "0", "0^All",
						true);

			}else {
				temp = "<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			strmsgText = "ipd.reports.PatDischargeDtlDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatDischargeDtl->getWardDetails()", strmsgText);
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

	public static void getRoomDetails(PatDischargeDtlRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PatDischargeDtlRptBO bo = null;
		String strmsgText = null;
		PatDischargeDtlRptVO voObj = null;

		HisUtil util = null;

		try {

			bo = new PatDischargeDtlRptBO();
			voObj = new PatDischargeDtlRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");
			String strWardCode = request.getParameter("wardCode");
			

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			if (strWardCode == null)
				strWardCode = "0";

			
			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrWardCode(strWardCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getRoomDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "Patient Discharge Detail Report");
			String temp = "<option value='0'>Select Value</option>";
			if (voObj.getStrRoomWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrRoomWs(), "0", "0^All",
						true);

			}else {
				temp = "<option value='0'>All</option>";
			}
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			//e.printStackTrace();
			strmsgText = "ipd.reports.PatDischargeDtlDATA.getRoomDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatDischargeDtl->getRoomDetails()", strmsgText);
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
	
	public static void getBedDetails(PatDischargeDtlRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PatDischargeDtlRptBO bo = null;
		String strmsgText = null;
		PatDischargeDtlRptVO voObj = null;

		HisUtil util = null;

		try {

			bo = new PatDischargeDtlRptBO();
			voObj = new PatDischargeDtlRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");
			String strWardCode = request.getParameter("wardCode");
			String strRoomCode = request.getParameter("roomCode");
			
			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			if (strWardCode == null)
				strWardCode = "0";
			
			voObj.setStrRoomNo(strRoomCode);
			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrWardCode(strWardCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getBedDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "Patient Discharge Detail Report");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrBedWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrBedWs(), "0", "0^All",
						true);

			}else {
				temp = "<option value='0'>All</option>";
			}
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			//e.printStackTrace();
			strmsgText = "ipd.reports.PatDischargeDtlDATA.getBedDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatDischargeDtl->getBedDetails()", strmsgText);
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

	public static void showReport(PatDischargeDtlRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

	//	String reportPath = "/ipd/reports/patDischargeDtl_ipdrpt.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String strRoomCode = formBean.getStrRoomNo();
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strBedCode = formBean.getStrBedCode();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "Patient Discharge Detail Report";
			String strUnitCode = formBean.getStrUnitCode();
			String strUserRemarks = formBean.getStrUserRemarks();
			String strFromTime = formBean.getStrFromTime();
			String strToTime = formBean.getStrToTime();
			String currTime = formBean.getStrCurrentTime();
			String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strFromTime);
			String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?currTime:strToTime);
			String strCurrDate = formBean.getStrCurrentDate();
			String strCrNo = formBean.getStrCrNo();
			reportFormat = formBean.getStrReportFormat();	
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
	
			boolean footerVisible = true;
			
			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;
				
			}

	if(formBean.getStrCase().equals("1")){
		
		String reportPath = "/ipd/reports/patDischargeDtl1_ipdrpt.rptdesign";
			
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
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
			params.put("cr_No", "null");
			params.put("room_Code", "null");
			params.put("bed_Code", "null");
			
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);
						
		}else if(formBean.getStrCase().equals("2")){
			
			String reportPath = "/ipd/reports/patDischargeDtl2_ipdrpt.rptdesign";
			
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
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
			if(strRoomCode.equals("0")){
				params.put("room_Code", "null");
			}else{
				params.put("room_Code", strRoomCode);
			}
			if(strUnitCode.equals("0")){
				params.put("unit_Code", "null");
			}else{
				params.put("unit_Code", strUnitCode);
			}
			params.put("bed_Code", "null");
			params.put("cr_No", "null");
						
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);
		
		}else if (formBean.getStrCase().equals("3")) {
			
			String reportPath = "/ipd/reports/patDischargeDtl3_ipdrpt.rptdesign";
			
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
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
			if(strRoomCode.equals("0")){
				params.put("room_Code", "null");
			}else{
				params.put("room_Code", strRoomCode);
			}
			if(strUnitCode.equals("0")){
				params.put("unit_Code", "null");
			}else{
				params.put("unit_Code", strUnitCode);
			}
			if(strBedCode.equals("0")){
				params.put("bed_Code", "null");
			}else{
				params.put("bed_Code", strBedCode);
			}
			params.put("cr_No", "null");
			
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);
			
		}else if (formBean.getStrCase().equals("4")) {
		
			String reportPath = "/ipd/reports/patDischargeDtl4_ipdrpt.rptdesign";
			
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
			params.put("from_Date", "null");
			params.put("to_Date", "null");
			params.put("dept_Code", "null");
			params.put("ward_Code", "null");
			params.put("room_Code", "null");
			params.put("unit_Code", "null");
			params.put("bed_Code", "null");
			params.put("cr_No", strCrNo);
						
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);
			
		}else if (formBean.getStrCase().equals("5")) {
			
			String reportPath = "/ipd/reports/patDischargeDtl5_ipdrpt.rptdesign";
			
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
			params.put("dept_Code", "null");
			params.put("ward_Code", "null");
			params.put("room_Code", "null");
			params.put("unit_Code", "null");
			params.put("bed_Code", "null");
			params.put("cr_No", "null");
			
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);
		
			}				
		} catch (Exception e) {

			//e.printStackTrace();

		}
	}
	
}
