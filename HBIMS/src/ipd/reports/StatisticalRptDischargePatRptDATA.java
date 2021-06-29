package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatisticalRptDischargePatRptDATA {

	public static void initReportPage(StatisticalRptDischargePatRptFB formBean,HttpServletRequest request) {

		StatisticalRptDischargePatRptBO bo = null;

		StatisticalRptDischargePatRptVO voObj = null;

		HisUtil util = new HisUtil("IPD Reports",
				"StatisticalRptDischargePatRptDATA");
		String strHospNameValues = "";
		//String strDeptVal = "";
	/*	String strUnitVal = "";
		String strWardVal = "";*/
		String strCatVal = "";
		String strmsgText = null;
		try {
			bo = new StatisticalRptDischargePatRptBO();
			voObj = new StatisticalRptDischargePatRptVO();

			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrCategory(formBean.getStrCategory());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			bo.getHospitalName(voObj);
			//bo.getDepartmentDetails(voObj);
			/*bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/
			bo.getCategoryDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0",
					"0^Select Value", false);
			//strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					//"0^Select Value", false);
			/*strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);
			strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);*/
			strCatVal = util.getOptionValue(voObj.getStrCategoryWs(), "0",
					"0^All", false);
			
			formBean.setStrHospNameValues(strHospNameValues);

			//formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrWardValues(strWardVal);
			formBean.setStrUnitValues(strUnitVal);*/
			formBean.setStrCategoryValues(strCatVal);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));

		} catch (Exception e) {
			strmsgText = "ipd.reports.StatisticalRptDischargePatRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"StatisticalRptDischargePatRpt->initReportPage()",
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
	
	public static void getdeptComboDetails(StatisticalRptDischargePatRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		StatisticalRptDischargePatRptBO bo = null;

		StatisticalRptDischargePatRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new StatisticalRptDischargePatRptBO();
			voObj = new StatisticalRptDischargePatRptVO();

			String strHospitalCode=request.getParameter("hospCode"); // Getting Form Bean's Hospital Code From Session
			
			voObj.setStrHospitalCode((strHospitalCode==null || strHospitalCode=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():strHospitalCode);

			bo.getdeptComboDetails(voObj);
			
			util = new HisUtil("IPD Reports", "StatisticalRptDischargePatRptDATA");
			
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
			strmsgText = "ipd.reports.StatisticalRptDischargePatRptDATA.getdeptComboDetails --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","StatisticalRptDischargePatRptDATA->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}

	public static void getUnitWardDetails(StatisticalRptDischargePatRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		StatisticalRptDischargePatRptBO bo = null;

		StatisticalRptDischargePatRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new StatisticalRptDischargePatRptBO();
			voObj = new StatisticalRptDischargePatRptVO();

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
					"StatisticalRptDischargePatRptDATA");
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
			strmsgText = "ipd.reports.StatisticalRptDischargePatRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"StatisticalRptDischargePatRpt->getUnitDetails()",
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

	public static void getWardDetails(StatisticalRptDischargePatRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		StatisticalRptDischargePatRptBO bo = null;

		StatisticalRptDischargePatRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = new HisUtil("IPD Reports","StatisticalRptDischargePatRptDATA");
	
		try {
			bo = new StatisticalRptDischargePatRptBO();
			voObj = new StatisticalRptDischargePatRptVO();

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
					"StatisticalRptDischargePatRptDATA");
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
			strmsgText = "ipd.reports.StatisticalRptDischargePatRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException(
					"ipd",
					"StatisticalRptDischargePatRpt->gegetWardDetailstUnitDetails()",
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

//	public static void getCategoryDetails(
//			StatisticalRptDischargePatRptFB formBean,
//			HttpServletRequest request, HttpServletResponse response) {
//
//		StatisticalRptDischargePatRptBO bo = null;
//
//		StatisticalRptDischargePatRptVO voObj = null;
//		String strmsgText = null;
//		HisUtil util = new HisUtil("IPD Reports","StatisticalRptDischargePatRptDATA");
//		String strCategoryVal = "";
//		try {
//			bo = new StatisticalRptDischargePatRptBO();
//			voObj = new StatisticalRptDischargePatRptVO();
//			
//			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
//			voObj.setStrCategory(formBean.getStrCategory());
//			
//			bo.getCategoryDetails(voObj);
//			util = new HisUtil("IPD Reports", "AdmissionCashCollectionRptDATA");
// 
//			if (voObj.getStrMsgType().equals("1")) {
//				throw new Exception(voObj.getStrMsgString());
//			}
//						
//			strCategoryVal = util.getOptionValue(voObj.getStrCategoryWs(), "0",
//					"0^All", false);
//					
//
//			formBean.setStrCategoryValues(strCategoryVal);
//
//		} catch (Exception e) {
//			strmsgText = "ipd.reports.StatisticalRptDischargePatRptDATA.getCategoryDetails --> "
//					+ e.getMessage();
//			HisException eObj = new HisException("ipd",
//					"StatisticalRptDischargePatRpt->getCategoryDetails()",
//					strmsgText);
//			formBean.setStrErrMsg("Application Error [ERROR ID : "
//					+ eObj.getErrorID() + "],Contact System Administrator! ");
//
//			eObj = null;
//		} finally {
//
//			if (bo != null)
//				bo = null;
//			if (voObj != null)
//				voObj = null;
//			if (util != null)
//				util = null;
//		}
//
//	}

	public static void showReport(StatisticalRptDischargePatRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/statisticalReportDischargePat_ipdrpt.rptdesign";
		
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();
		
			String strWardCode = formBean.getStrWardCode();
			String strFromTime = formBean.getStrFromTime();
			String strToTime = formBean.getStrToTime();
			String currTime = formBean.getStrCurrentTime();
			String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strFromTime);
			String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?currTime:strToTime);
			String strCurrDate = formBean.getStrCurrentDate();
			String strUnitCode = formBean.getStrUnitCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strCategory = formBean.getStrCategory();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "Statistical Report For Discharge Patient";
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			String strUserRemarks = formBean.getStrUserRemarks();
			
			boolean IsWardWise = true;
			boolean IsPrimCatWise = true;
			boolean IsUnitWise = false;
			boolean footerVisible = true;
			boolean IsGrandTotal = false;

		if(formBean.getStrCase().equals("1")){
			IsWardWise = false;
			
			if(strUnitCode.equals("0")){
				params.put("unit_Code", "null");
			}else{
				params.put("unit_Code", strUnitCode);
			}
			
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
			
		}else if(formBean.getStrCase().equals("2")){
			IsPrimCatWise = false;
			IsWardWise = true;
			
			if(strUnitCode.equals("0")){
				params.put("unit_Code", "null");
			}else{
				params.put("unit_Code", strUnitCode);
			}
			
			if(strDeptCode.equals("0")){
				params.put("dept_Code", "null");
			}else{
				params.put("dept_Code", strDeptCode);
			}
			
			if(strCategory.equals("0")){
				params.put("prim_Cat", "null");
			}else{
				params.put("prim_Cat", strCategory);
			}
		}

			if (formBean.getStrIsFooter().equals("1")) {

				footerVisible = false;

			}

			if(formBean.getStrUnitNameDisplay()=="0")
			{
				IsUnitWise=false;
			}
			else
			{
				IsUnitWise=true;
			}
			
			if(formBean.getStrGrandTotalRequired()=="0"){
				IsGrandTotal=true;
			}
			else
			{
				IsGrandTotal=false;
				
			}
			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
			
			params.put("hosp_Code", strHospitalCode);
			params.put("IsWardWise", IsWardWise);
			params.put("IsPrimCatWise", IsPrimCatWise);
			params.put("IsUnitWise", IsUnitWise);
			params.put("IsGrandTotal",IsGrandTotal);
	
			

			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);
			
			
			
			

		} catch (Exception e) {

			//e.printStackTrace();

		}
	}

}
