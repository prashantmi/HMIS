package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CurrentlyAdmittedPatientRptDATA {

	public static void initReportPage(CurrentlyAdmittedPatientRptFB formBean,HttpServletRequest request)
	{

		CurrentlyAdmittedPatientRptBO bo = null;
		String strmsgText = null;
		CurrentlyAdmittedPatientRptVO  voObj = null;

		HisUtil util = null;
		String strDeptVal = "";
		/*String strUnitVal = "";
		String strWardVal = "";*/
		

		try {
			bo = new CurrentlyAdmittedPatientRptBO();
			voObj = new CurrentlyAdmittedPatientRptVO();
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			
			String strHospNameValues = "";
		
			/*bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/
			
			bo.getHospitalName(voObj);
			
				voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			
				bo.getDepartmentDetails(voObj);
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());
			}
			
			util =new HisUtil("IPD Reports","CurrentlyAdmittedPatientRptDATA");
			
			//strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0", "0^Select Value", false);
		/*	strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",	"0^All", false);
			strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0", "0^All", false);*/
	
			
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());


			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0",
								"0^Select Value", false);	

			formBean.setStrHospNameValues(strHospNameValues);
			//formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrUnitValues(strUnitVal);
			formBean.setStrWardValues(strWardVal);*/
			

		} catch (Exception e) {

			strmsgText = "ipd.reports.CurrentlyAdmittedPatientRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"CurrentlyAdmittedPatientRpt->initReportPage()", strmsgText);
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
	
	public static void getdeptComboDetails(CurrentlyAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		CurrentlyAdmittedPatientRptBO bo = null;

		CurrentlyAdmittedPatientRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new CurrentlyAdmittedPatientRptBO();
			voObj = new CurrentlyAdmittedPatientRptVO();
			String strhospCode=request.getParameter("hospCode").toString();

			
			if (strhospCode == null)
				strhospCode = "0";
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getDepartmentDetails(voObj);
			
			util = new HisUtil("IPD Reports", "CurrentlyAdmittedPatientRptDATA");
			
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
			strmsgText = "ipd.reports.CurrentlyAdmittedPatientRptDATA.getWardDetailsCombo --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","CurrentlyAdmittedPatientRpt->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	
	public static void getWardDetailsCombo(CurrentlyAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		String strmsgText = null;
		try {

			

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";
			
			if(strDeptUnitCode.equals("0"))
			{
				
				CurrentlyAdmittedPatientRptDATA.getWardDetailsForAll(formBean,request, response);
				}
				
			else
				{
				
				CurrentlyAdmittedPatientRptDATA.getWardDetails(formBean,request, response);
				
				}
		}
		
		catch (Exception e) {
			strmsgText = "ipd.reports.CurrentlyAdmittedPatientRptDATA.getWardDetailsCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"CurrentlyAdmittedPatientRpt->getWardDetailsCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
			
		
	}
	
	public static void getWardDetailsForAll(CurrentlyAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		CurrentlyAdmittedPatientRptBO bo = null;

		CurrentlyAdmittedPatientRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new CurrentlyAdmittedPatientRptBO();
			voObj = new CurrentlyAdmittedPatientRptVO();

			String strDeptCode = request.getParameter("deptCode");
			
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			bo.getWardCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
	

			util = new HisUtil("IPD Reports", "CurrentlyAdmittedPatientRptDATA");
			
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
			strmsgText = "ipd.reports.CurrentlyAdmittedPatientRptDATA.getWardDetailsForAll --> "
				+ e.getMessage();
		HisException eObj = new HisException("ipd",
				"CurrentlyAdmittedPatientRpt->getWardDetailsForAll()", strmsgText);
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
	public static void getUnitWardDetails(CurrentlyAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		CurrentlyAdmittedPatientRptBO bo = null;

		CurrentlyAdmittedPatientRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new CurrentlyAdmittedPatientRptBO();
			voObj = new CurrentlyAdmittedPatientRptVO();

			String strDeptCode = request.getParameter("deptCode");

			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "CurrentlyAdmittedPatientRptDATA");
			String temp = "<option value='0'>Select Value</option>";
			String temp1= "<option value='0'>Select Value</option>";
			if (voObj.getStrUnitWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrUnitWs(), "0", "0^All",
						true);
				

			}else {
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
			strmsgText = "ipd.reports.CurrentlyAdmittedPatientRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"CurrentlyAdmittedPatientRpt->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(CurrentlyAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		CurrentlyAdmittedPatientRptBO bo = null;

		CurrentlyAdmittedPatientRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		
		try {

			bo = new CurrentlyAdmittedPatientRptBO();
			voObj = new CurrentlyAdmittedPatientRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);
			

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "CurrentlyAdmittedPatientRptDATA");
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
			strmsgText = "ipd.reports.CurrentlyAdmittedPatientRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"CurrentlyAdmittedPatientRpt->getWardDetails()", strmsgText);
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

	public static void getRoomDetails(CurrentlyAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		CurrentlyAdmittedPatientRptBO bo = null;

		CurrentlyAdmittedPatientRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new CurrentlyAdmittedPatientRptBO();
			voObj = new CurrentlyAdmittedPatientRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");
			String strWardCode = request.getParameter("wardCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			if (strWardCode == null)
				strWardCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrWardCode(strWardCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getRoomDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "CurrentlyAdmittedPatientRpt");
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
			strmsgText = "ipd.reports.CurrentlyAdmittedPatientRptDATA.getRoomDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"CurrentlyAdmittedPatientRpt->getRoomDetails()", strmsgText);
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
	public static void showReport(CurrentlyAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/currentlyAdmittedPatient_ipdrptNew.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String strRoomCode = formBean.getStrRoomNo();
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "Currently Admitted Patient List";
		
			String strUserRemarks = formBean.getStrUserRemarks();
			
			boolean isWardWise = true;
			boolean isRoomWise = true;
			boolean rooterVisible = true;

			 if (formBean.getStrIsRoomWise().equals("1")) {
				 isRoomWise = false;
				 reportPath = "/ipd/reports/currentlyAdmittedPatientRoomWise_ipdrptNew.rptdesign";
			 }
			 else{
				 isWardWise = false;
			 }

			if (formBean.getStrIsFooter().equals("1")) {
				rooterVisible = false;
			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", rooterVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
			if(strRoomCode.equals("0")){
				params.put("room_No", "0");
			}else{
				params.put("room_No", strRoomCode);
			}
			if(strWardCode.equals("0")){
				params.put("ward_Code", "0");
			}else{
				params.put("ward_Code", strWardCode);
			}
			if(strDeptCode.equals("0")){
				params.put("dept_Code","0");
			}else{
				params.put("dept_Code",strDeptCode);
			}
			if(strUnitCode.equals("0")){
				params.put("unit_Code", "0");
			}else{
				params.put("unit_Code", strUnitCode);
			}
			params.put("IsRoomWise", isRoomWise);
			params.put("IsWardWise", isWardWise);
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			//e.printStackTrace();

		}
	}

}
