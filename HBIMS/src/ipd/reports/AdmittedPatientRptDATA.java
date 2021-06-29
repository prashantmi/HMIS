package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdmittedPatientRptDATA {

	public static void initReportPage(AdmittedPatientRptFB formBean,HttpServletRequest request)
	{

		AdmittedPatientRptBO bo = null;

		AdmittedPatientRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		String strDeptVal = "";
		String strHospName="";
	//	String strUnitVal = "";
	//	String strWardVal = "";
		

		try
		{
			bo = new AdmittedPatientRptBO();
			voObj = new AdmittedPatientRptVO();
			//bo.getHospitalName(voObj);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			//voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			//voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			//voObj.setStrUnitCode(formBean.getStrUnitCode());
			

			//bo.getDepartmentDetails(voObj);
		//	bo.getHospitalName(voObj);
		//	bo.getUnitDetails(voObj);
		//	bo.getWardDetails(voObj);
			bo.getHospitalName(voObj);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			
			if (voObj.getStrMsgType().equals("1"))
			{

				throw new Exception(voObj.getStrMsgString());

			}
			
			util = new HisUtil("IPD Reports", "AdmittedPatientRptDATA");
			strHospName= util.getOptionValue(voObj.getStrHospitalWs(),request.getSession().getAttribute("HOSPITAL_CODE").toString(),"0^Select Value", false);
			//String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();

			
			
			//strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0","0^Select Value", false);
		/*	strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);
			strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);*/
			
			//formBean.setStrDeptValues(strDeptVal);

			formBean.setStrHospNameValues(strHospName);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));


		//	formBean.setStrUnitValues(strUnitVal);
		//	formBean.setStrWardValues(strWardVal);
		    			
			formBean.setStrModeVal("2");
			//formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			//formBean.setStrCurrentTime(util.getASDate("HH:mm"));
		} catch (Exception e) {
			strmsgText = "ipd.reports.AdmittedPatientRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"AdmittedPatientRpt->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		finally
		{

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}

	}
	
	public static void getdeptComboDetails(AdmittedPatientRptFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
		AdmittedPatientRptBO bo = null;
		AdmittedPatientRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try 
		{
			bo = new AdmittedPatientRptBO();
			voObj = new AdmittedPatientRptVO();

			String strhospCode=request.getParameter("hospCode").toString();
			if (strhospCode == null)
				strhospCode = "0";
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getdeptComboDetails(voObj);
			
			util = new HisUtil("ADT", "AdmittedPatientRptDATA");
			
			String temp1= "<option value='0'>All</option>";
			
			if (voObj.getStrDeptWs().size() != 0) 
			{
				temp1 = util.getOptionValue(voObj.getStrDeptWs(), "0", "0^All",true);
			}
			else 
			{
				temp1 ="<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);
		}
		
		catch (Exception e) 
		{
			strmsgText = "ipd.reports.AdmittedPatientRptDATA.getWardDetailsCombo --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","AdmittedPatientRpt->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	public static void getWardDetailsCombo(AdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		String strmsgText = null;
		try {

			

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";
			
			if(strDeptUnitCode.equals("0"))
			{
				
				AdmittedPatientRptDATA.getWardDetailsForAll(formBean,request, response);
				}
				
			else
				{
				
				AdmittedPatientRptDATA.getWardDetails(formBean,request, response);
				
				}
		}
		
		catch (Exception e) {
			strmsgText = "ipd.reports.AdmittedPatientRptDATA.getWardDetailsCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"AdmittedPatientRpt->getWardDetailsCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
			
		
	}
	
	public static void getWardDetailsForAll(AdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		AdmittedPatientRptBO bo = null;

		AdmittedPatientRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new AdmittedPatientRptBO();
			voObj = new AdmittedPatientRptVO();

			String strDeptCode = request.getParameter("deptCode");
			
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			bo.getWardCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
	

			util = new HisUtil("IPD Reports", "AdmittedPatientRptDATA");
			
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
			strmsgText = "ipd.reports.AdmittedPatientRptDATA.getWardDetailsForAll --> "
				+ e.getMessage();
		HisException eObj = new HisException("ipd",
				"AdmittedPatientRpt->getWardDetailsForAll()", strmsgText);
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
	public static void getUnitWardDetails(AdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		AdmittedPatientRptBO bo = null;
		String strmsgText = null;
		AdmittedPatientRptVO voObj = null;

		HisUtil util = null;

		try {

			bo = new AdmittedPatientRptBO();
			voObj = new AdmittedPatientRptVO();

			String strDeptCode = request.getParameter("deptCode");

			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "Admitted Patient Report");
			String temp = "<option value='0'>Select Value</option>";
			String temp1= "<option value='0'>Select Value</option>";
			if (voObj.getStrUnitWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrUnitWs(), "0", "0^All",
						true);

			}else{
				temp = "<option value='0'>All<option>";
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
			strmsgText = "ipd.reports.AdmittedPatientRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"AdmittedPatientRptDATA->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(AdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		AdmittedPatientRptBO bo = null;
		String strmsgText = null;
		AdmittedPatientRptVO voObj = null;

		HisUtil util = null;

		try {

			bo = new AdmittedPatientRptBO();
			voObj = new AdmittedPatientRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "Admitted Patient Report");
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
			strmsgText = "ipd.reports.AdmittedPatientRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"AdmittedPatientRptDATA->getWardDetails()", strmsgText);
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

	public static void getRoomDetails(AdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		AdmittedPatientRptBO bo = null;
		String strmsgText = null;
		AdmittedPatientRptVO voObj = null;

		HisUtil util = null;

		try {

			bo = new AdmittedPatientRptBO();
			voObj = new AdmittedPatientRptVO();

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

			util = new HisUtil("IPD Reports", "Admitted Patient Report");
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
			strmsgText = "ipd.reports.AdmittedPatientRptDATA.getRoomDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"AdmittedPatientRptDATA->getRoomDetails()", strmsgText);
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

	public static void showReport(AdmittedPatientRptFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
		ReportUtil ts = new ReportUtil();
		String reportPath = "/ipd/reports/admittedPat_ipdrptNew.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try 
		{
			reportFormat = formBean.getStrReportFormat();
            String strRoomCode = formBean.getStrRoomNo();
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();

			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "New Admission Patient Listing";
			String strUnitCode = formBean.getStrUnitCode();
			String strUserRemarks = formBean.getStrUserRemarks();
			

			String strFromTime = formBean.getStrFromTime();
			String strToTime = formBean.getStrToTime();
			String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00:00":strFromTime);
			String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00:00":strToTime);
			String strCurrDate = formBean.getStrCurrentDate();
			//SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			
			
			//String strEffectiveFrom = formBean.getStrEffectiveFrom();
			//String strEffectiveTo = formBean.getStrEffectiveTo();
			//String strMode = formBean.getStrModeVal();
			String strMode = "2";//New Admission
			String strOnlyAcceptedPatient = formBean.getStrOnlyAcceptedPatient();
			//if(strMode.equals("1"))
				//strReportName = "New Admission Patient Listing";
			if(strOnlyAcceptedPatient.equals("1"))
			{
				strReportName = "New Admission Patient Listing(Accepted Patients)";
				reportPath = "/ipd/reports/admittedPatAccepted_ipdrptNew.rptdesign";
				strMode=formBean.getStrOnlyAcceptedPatient();
			}
			
			//SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

			boolean isWardWise = true;
			boolean isRoomWise = true;
			boolean footerVisible = true;

			 if (formBean.getStrIsRoomWise().equals("1")) 
			 {
				 isRoomWise = false;
				 reportPath = "/ipd/reports/admittedPatRoomWise_ipdrpt.rptdesign";
			 }
			 else
			 {
				 isWardWise = false;
			 }

			if (formBean.getStrIsFooter().equals("1")) 
			{
				footerVisible = false;
			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
			params.put("IsRoomWise", isRoomWise);
			params.put("IsWardWise", isWardWise);
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
			params.put("treat_Cat", "null");
			params.put("mode", strMode);
			params.put("report_formattype", reportFormat);
			
			
			
			if(strDeptCode.equals("0"))
			{
				params.put("dept_Code", "0");
			}
			else
			{
				params.put("dept_Code", strDeptCode);
			}
			if(strWardCode.equals("0"))
			{
				params.put("ward_Code", "0");
			}
			else
			{
				params.put("ward_Code", strWardCode);
			}
			if(strRoomCode.equals("0"))
			{
				params.put("room_No", "0");
			}
			else
			{
				params.put("room_No", strRoomCode);
			}
			if(strUnitCode.equals("0"))
			{
				params.put("unit_Code", "0");
			}
			else
			{
				params.put("unit_Code", strUnitCode);
			}
			ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
