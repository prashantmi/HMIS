package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NonAcceptedPatientListingRptDATA {

	public static void initReportPage(NonAcceptedPatientListingRptFB formBean,HttpServletRequest request) {

		NonAcceptedPatientListingRptBO bo = null;
		NonAcceptedPatientListingRptVO voObj = null;

		HisUtil util = null;
		String strDeptVal = "";
		String strHospNameValues = "";
		/*String strUnitVal = "";
		String strWardVal = "";*/
		String strmsgText = null;
		try {
			bo = new NonAcceptedPatientListingRptBO();
			voObj = new NonAcceptedPatientListingRptVO();

			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

			bo.getHospitalName(voObj);
			
			//bo.getDepartmentDetails(voObj);
		/*	bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());
			}
			util = new HisUtil("IPD Reports",
			"NonAcceptedPatientListingRptDATA");
			//strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
				//	"0^All", false);
			strHospNameValues= util.getOptionValue(voObj.getStrHospitalWs(),request.getSession().getAttribute("HOSPITAL_CODE").toString(),"0^Select Value", false);
		/*	strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);
			strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);*/

			formBean.setStrHospNameValues(strHospNameValues);
			//formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrUnitValues(strUnitVal);
			formBean.setStrWardValues(strWardVal);*/
			/*bo.getdeptComboDetails(voObj);
			strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					"0^Select Value", true);
			formBean.setStrDeptValues(strDeptVal);*/
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));
			

		} catch (Exception e) {
			strmsgText = "ipd.reports.NonAcceptedPatientListingRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"NonAcceptedPatientListingRpt->initReportPage()",
					strmsgText);
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
	public static void getdeptComboDetails(NonAcceptedPatientListingRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		NonAcceptedPatientListingRptBO bo = null;
		NonAcceptedPatientListingRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new NonAcceptedPatientListingRptBO();
			voObj = new NonAcceptedPatientListingRptVO();

			String strHospitalCode=request.getParameter("hospCode").toString();; // Getting Form Bean's Hospital Code From Session
	
			if (strHospitalCode == null)
				strHospitalCode = "0";
			
			voObj.setStrHospitalCode((strHospitalCode==null || strHospitalCode=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():strHospitalCode);

			bo.getdeptComboDetails(voObj);
			
			util = new HisUtil("IPD Reports", "NonAcceptedPatientListingRptDATA");
			
			String temp1= "<option value='0'>All</option>";
			
			if (voObj.getStrDeptWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrDeptWs(), "0", "0^All",true);
			}
			else {
				temp1 ="<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);
		}
		
		catch (Exception e) 
		{
			strmsgText = "ipd.reports.NonAcceptedPatientListingRptDATA.getdeptComboDetails --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","NonAcceptedPatientListingRptDATA->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	public static void getUnitWardDetails(NonAcceptedPatientListingRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		NonAcceptedPatientListingRptBO bo = null;

		NonAcceptedPatientListingRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new NonAcceptedPatientListingRptBO();
			voObj = new NonAcceptedPatientListingRptVO();

			String strDeptCode = request.getParameter("deptCode");

			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports",
					"NonAcceptedPatientListingRptDATA");
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
			strmsgText = "ipd.reports.NonAcceptedPatientListingRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"NonAcceptedPatientListingRpt->getUnitDetails()",
					strmsgText);
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

	public static void getWardDetails(NonAcceptedPatientListingRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		NonAcceptedPatientListingRptBO bo = null;

		NonAcceptedPatientListingRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new NonAcceptedPatientListingRptBO();
			voObj = new NonAcceptedPatientListingRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports",
					"NonAcceptedPatientListingRptDATA");
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
			strmsgText = "ipd.reports.NonAcceptedPatientListingRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"NonAcceptedPatientListingRpt->getWardDetails()",
					strmsgText);
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

	public static void showReport(NonAcceptedPatientListingRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/nonAcceptedPatientListing_ipdrpt.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String NotReported = formBean.getIsNotReported();
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			String strFromTime = formBean.getStrFromTime();
			String strToTime = formBean.getStrToTime();
			String currTime = formBean.getStrCurrentTime();
			String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strFromTime);
			String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?currTime:strToTime);
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "Cancelled / Not Reported Patient Listing";
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			String strUserRemarks = formBean.getStrUserRemarks();
	
			boolean footerVisible = true;

			if (formBean.getStrIsFooter().equals("1")) {

				footerVisible = false;

			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
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
			if(NotReported.equals("0")){
				params.put("status", "0");
			}
			else{
				params.put("status",NotReported);
			}
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
			params.put("hosp_Code", strHospitalCode);
			params.put("report_formattype", reportFormat);
	
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			//e.printStackTrace();

		}
	}

}
