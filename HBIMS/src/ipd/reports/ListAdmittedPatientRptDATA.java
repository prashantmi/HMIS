package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerDATA;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HisUtil;
import hisglobal.vo.HospitalMstVO;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAdmittedPatientRptDATA {

	public static void initReportPage(ListAdmittedPatientRptFB formBean,HttpServletRequest request) {

		ListAdmittedPatientRptBO bo = null;
		ListAdmittedPatientRptVO voObj = null;

		HisUtil util = null;
		String strDeptVal = "";
		String strHospNameValues = "";
		/*String strWardVal = "";
		String strUnitVal = "";*/
		String strmsgText = null;
		try {
			bo = new ListAdmittedPatientRptBO();
			voObj = new ListAdmittedPatientRptVO();
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			
			bo.getHospitalName(voObj); // Getting Hospital Combo List
			
			//bo.getDepartmentDetails(voObj);
			/*bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
			util = new HisUtil("IPD Reports", "ListAdmittedPatientRptDATA");

			//strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
				//	"0^Select Value", false);
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0",
					"0^Select Value", false);
		/*	strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);
			strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);
*/
			//formBean.setStrDeptValues(strDeptVal);
			formBean.setStrHospNameValues(strHospNameValues);
		/*	formBean.setStrWardValues(strWardVal);
			formBean.setStrUnitValues(strUnitVal);*/
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));
			
		} catch (Exception e) {
			strmsgText = "ipd.reports.ListAdmittedPatientRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ListAdmittedPatientRptDATA->initReportPage()", strmsgText);
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
	
	public static void getdeptComboDetails(ListAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		ListAdmittedPatientRptBO bo = null;

		ListAdmittedPatientRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new ListAdmittedPatientRptBO();
			voObj = new ListAdmittedPatientRptVO();
			String strHospitalCode=request.getParameter("hospCode"); // Getting Form Bean's Hospital Code From Session
			
			voObj.setStrHospitalCode((strHospitalCode==null || strHospitalCode=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():strHospitalCode);

			bo.getdeptComboDetails(voObj);
			
			util = new HisUtil("IPD Reports", "ListAdmittedPatientRptDATA");
			
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
			strmsgText = "ipd.reports.AdmittedPatientRptDATA.getdeptComboDetails --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","ListAdmittedPatientRptDATA->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}



	public static void getWardDetailsCombo(ListAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		String strmsgText = null;
		try {

			

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";
			
			if(strDeptUnitCode.equals("0"))
			{
				
				ListAdmittedPatientRptDATA.getWardDetailsForAll(formBean,request, response);
				}
				
			else
				{
				
				ListAdmittedPatientRptDATA.getWardDetails(formBean,request, response);
				
				}
		}
		
		catch (Exception e) {
			strmsgText = "ipd.reports.ListAdmittedPatientRptDATA.getWardDetailsCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ListAdmittedPatientRpt->getWardDetailsCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
			
		
	}
	
	public static void getWardDetailsForAll(ListAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		ListAdmittedPatientRptBO bo = null;

		ListAdmittedPatientRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new ListAdmittedPatientRptBO();
			voObj = new ListAdmittedPatientRptVO();

			String strDeptCode = request.getParameter("deptCode");
			
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			bo.getWardCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
	

			util = new HisUtil("IPD Reports", "ListAdmittedPatientRptDATA");
			
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
			strmsgText = "ipd.reports.ListAdmittedPatientRptDATA.getWardDetailsForAll --> "
				+ e.getMessage();
		HisException eObj = new HisException("ipd",
				"ListAdmittedPatientRpt->getWardDetailsForAll()", strmsgText);
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
	public static void getUnitWardDetails(ListAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ListAdmittedPatientRptBO bo = null;
		ListAdmittedPatientRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new ListAdmittedPatientRptBO();
			voObj = new ListAdmittedPatientRptVO();

			String strDeptCode = request.getParameter("deptCode");
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

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
			
			strmsgText = "ipd.reports.ListAdmittedPatientRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ListAdmittedPatientRptDATA->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(ListAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ListAdmittedPatientRptBO bo = null;
		ListAdmittedPatientRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {
			bo = new ListAdmittedPatientRptBO();
			voObj = new ListAdmittedPatientRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");
			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("IPD Reports", "ListAdmittedPatientRptDATA");
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
			strmsgText = "ipd.reports.ListAdmittedPatientRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ListAdmittedPatientRpt->getWardDetails()", strmsgText);
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

	public static void showReport(ListAdmittedPatientRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

			ReportUtil ts = new ReportUtil();

			String reportPath = "/ipd/reports/listAdmittedPatient_ipdrpt.rptdesign";
			String reportFormat = "html";
			HisUtil util = new HisUtil("IPD Reports", "ListAdmittedPatientRptDATA");
			Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			//String strCurrDate = formBean.getStrCurrentDate();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "List Of Admitted Patients";
			String strUserRemarks = formBean.getStrUserRemarks();
			String strFromTime = formBean.getStrFromTime();
			String strToTime = formBean.getStrToTime();
			String currTime = formBean.getStrCurrentTime();
			String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strFromTime);
			String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?currTime:strToTime);
			String strCurrDate = formBean.getStrCurrentDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			//String strHosCode = formBean.getStrHospitalName();
			
			boolean footerVisible = true;
			boolean IsWardWise = true;
			boolean IsDateWise = true;
		//	params.put("date_Code", strCurrDate);
			
				if(formBean.getStrCase().equals("1")){
				
				IsWardWise = false;
				params.put("report_Criteria", "Ward Wise");
				
				params.put("ward_Code", strWardCode);
				params.put("date_Code", "null");
				
			}else{
				IsDateWise = false;
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
			params.put("report_user_Remarks", strUserRemarks);
			params.put("IsDateWise", IsDateWise);
			params.put("IsWardWise", IsWardWise);
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
			params.put("hosp_Code", strHospitalCode);
			
			
			
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			//e.printStackTrace();

		}
	}

}
