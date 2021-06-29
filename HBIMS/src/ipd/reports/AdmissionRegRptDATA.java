package ipd.reports;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdmissionRegRptDATA {

	public static void initReportPage(AdmissionRegRptFB formBean,HttpServletRequest request) {

		AdmissionRegRptBO bo = null;
		AdmissionRegRptVO voObj = null;

		HisUtil util = null;
		String strDeptVal = "";
		/*String strUnitVal = "";
		String strWardVal = "";*/
		String strCatVal = "";
		String strmsgText = null;
		String strHospName="";
		util = new HisUtil("IPD Reports", "AdmissionRegRptDATA");
		try {
			bo = new AdmissionRegRptBO();
			voObj = new AdmissionRegRptVO();
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			
			bo.getHospitalName(voObj);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			strHospName= util.getOptionValue(voObj.getStrHospitalWs(),request.getSession().getAttribute("HOSPITAL_CODE").toString(),"0^Select Value", false);
			
			//bo.getDepartmentDetails(voObj);
		/*	bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/
			bo.getCategoryDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			
			/*strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					"0^All", false);
			/*strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);
			strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);*/
			
			//strHospName= util.getOptionValue(voObj.getStrHospitalWs(),"0","0^Select Value", false);
			strCatVal = util.getOptionValue(voObj.getStrCategoryWs(), "0","0^All", false);
			
			
			
			
			
			formBean.setStrHospNameValues(strHospName);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));
			
			
			//formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrUnitValues(strUnitVal);
			formBean.setStrWardValues(strWardVal);*/
			formBean.setStrCategoryValues(strCatVal);
			
			//formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			//formBean.setStrCurrentTime(util.getASDate("HH:mm"));

		} catch (Exception e) {
			strmsgText = "ipd.reports.AdmissionRegRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"AdmissionRegRpt->initReportPage()", strmsgText);
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

	public static void getUnitWardDetails(AdmissionRegRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		AdmissionRegRptBO bo = null;
		AdmissionRegRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		try {

			bo = new AdmissionRegRptBO();
			voObj = new AdmissionRegRptVO();
			String strhospCode=request.getParameter("hospCode").toString();
			if (strhospCode == null)
				strhospCode = "0";
			
			voObj.setStrHospitalCode(strhospCode);
			bo.getCategoryDetails(voObj);
			
			String strDeptCode = request.getParameter("deptCode");
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("IPD Reports", "AdmissionRegRptDATA");
			String temp = "<option value='0'>Select Value</option>";
			String temp1= "<option value='0'>Select Value</option>";

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
			strmsgText = "ipd.reports.AdmissionRegRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"AdmissionRegRptDATA->initReportPage()", strmsgText);
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

	public static void getWardDetails(AdmissionRegRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		AdmissionRegRptBO bo = null;
		String strmsgText = null;
		AdmissionRegRptVO voObj = null;

		HisUtil util = null;

		try {

			bo = new AdmissionRegRptBO();
			voObj = new AdmissionRegRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			
		voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "AdmissionRegRptDATA");
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
			strmsgText = "ipd.reports.AdmissionRegRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"AdmissionRegRptDATA->getWardDetails()", strmsgText);
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

	 public static void showReport(AdmissionRegRptFB formBean,HttpServletRequest request,
			 					HttpServletResponse response) {
	
		 ReportUtil ts = new ReportUtil();
	
		 	String reportPath = "/ipd/reports/admissionRegistered_ipdrptNew.rptdesign";
	 		String reportFormat = "html";
	
	 		Map<String, Object> params = new HashMap<String, Object>();
	 try {
	
		 reportFormat = formBean.getStrReportFormat();
			
		 String strDeptCode = formBean.getStrDeptCode();
		 String strWardCode = formBean.getStrWardCode();
		 String strUnitCode = formBean.getStrUnitCode();
		 String strCategory = formBean.getStrCategory();
		 String strCatName = formBean.getStrTreatCatName();
		 String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();

		 String strFromTime = formBean.getStrFromTime();
			String strToTime = formBean.getStrToTime();
			String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strFromTime);
			String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strToTime);
			String strCurrDate = formBean.getStrCurrentDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		 String strReportId = formBean.getStrReportId();
		// String strEffectiveFrom = formBean.getStrEffectiveFrom();
		// String strEffectiveTo = formBean.getStrEffectiveTo();
		 String strReportName = "Category Wise Admission Listing";
	
		// SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
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
	
		 params.put("hosp_Code", strHospitalCode);
		 params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
		 params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
		 params.put("report_formattype", reportFormat);
	 
		 
		 if(strDeptCode.equals("0")){
			 params.put("dept_Code", "0");	
		 }else{
			 params.put("dept_Code", strDeptCode);	
		 }
	 
		 if(strUnitCode.equals("0")){
			 params.put("unit_Code", "0");
		 }else{
			 params.put("unit_Code", strUnitCode);
		 }
		 if(strWardCode.equals("0")){
			 params.put("ward_Code", "0");
		 }else{
			 params.put("ward_Code", strWardCode);
		 }
		 if(strCategory.equals("0")){
			 params.put("treat_Cat", "0");
		 }else{
			 params.put("treat_Cat", strCategory);
		 }
		 params.put("treat_Cat_Name", strCatName);

		 ts.displayReport(request, response, reportPath, reportFormat,
				 params,strHospitalCode);
	
	 } catch (Exception e) {
	
		 //e.printStackTrace();
				
	 	 }
	 }
}
