package ipd.reports;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DailyMasterCensusRptDATA {

	public static void initReportPage(DailyMasterCensusRptFB formBean,HttpServletRequest request){

		DailyMasterCensusRptBO bo = null;

		DailyMasterCensusRptVO  voObj = null;
		String strmsgText = null;

		String strHospNameValues = "";

		
		HisUtil util = null;
		String strDeptVal = "";
	/*	String strWardVal = "";
		String strUnitVal = "";*/

		try {
			
						
			bo = new DailyMasterCensusRptBO();
			
			voObj = new DailyMasterCensusRptVO();
			
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
	
			bo.getHospitalName(voObj);
		//	bo.getDepartmentDetails(voObj);
		/*	bo.getWardDetails(voObj);
			bo.getUnitDetails(voObj);*/

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
			util = new HisUtil("IPD Reports", "DailyMasterCensusRptDATA");
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0","0^Select Value", false);	

           
			//strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",	"0^Select Value", false);
		/*	strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",	"0^All", false);
			strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",	"0^All", false);
*/
			 formBean.setStrHospNameValues(strHospNameValues);
			//formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrWardValues(strWardVal);
			formBean.setStrUnitValues(strUnitVal);*/
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
		} catch (Exception e) {
			strmsgText = "ipd.reports.DailyMasterCensusRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyMasterCensusRpt->initReportPage()", strmsgText);
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
	
	public static void getdeptComboDetails(DailyMasterCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		DailyMasterCensusRptBO bo = null;

		DailyMasterCensusRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new DailyMasterCensusRptBO();
			voObj = new DailyMasterCensusRptVO();

			String strhospCode=request.getParameter("hospCode").toString();

			
			if (strhospCode == null)
				strhospCode = "0";
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getdeptComboDetails(voObj);
			
			util = new HisUtil("IPD Reports", "DailyMasterCensusRptDATA");
			
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
			strmsgText = "ipd.reports.DailyMasterCensusRptDATA.getWardDetailsCombo --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","DailyMasterCensus->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	
	public static void getWardDetailsCombo(DailyMasterCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		String strmsgText = null;
		try {

			

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";
			
			if(strDeptUnitCode.equals("0"))
			{
				
				DailyMasterCensusRptDATA.getWardDetailsForAll(formBean,request, response);
				}
				
			else
				{
				
				DailyMasterCensusRptDATA.getWardDetails(formBean,request, response);
				
				}
		}
		
		catch (Exception e) {
			strmsgText = "ipd.reports.DailyMasterCensusRptDATA.getWardDetailsCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyMasterCensusRpt->getWardDetailsCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
			
		
	}
	
	public static void getWardDetailsForAll(DailyMasterCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		DailyMasterCensusRptBO bo = null;

		DailyMasterCensusRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new DailyMasterCensusRptBO();
			voObj = new DailyMasterCensusRptVO();

			String strDeptCode = request.getParameter("deptCode");
			
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			bo.getWardCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
	

			util = new HisUtil("IPD Reports", "DailyMasterCensusRptDATA");
			
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
			strmsgText = "ipd.reports.DailyMasterCensusRptDATA.getWardDetailsForAll --> "
				+ e.getMessage();
		HisException eObj = new HisException("ipd",
				"DailyMasterCensusRpt->getWardDetailsForAll()", strmsgText);
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
	public static void getUnitWardDetails(DailyMasterCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DailyMasterCensusRptBO bo = null;
		DailyMasterCensusRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new DailyMasterCensusRptBO();
			voObj = new DailyMasterCensusRptVO();

			String strDeptCode = request.getParameter("deptCode");

			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "DailyMasterCensusRptDATA");
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
			
			strmsgText = "ipd.reports.DailyMasterCensusRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyMasterCensusRpt->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(DailyMasterCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DailyMasterCensusRptBO bo = null;
		DailyMasterCensusRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new DailyMasterCensusRptBO();
			voObj = new DailyMasterCensusRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "DailyMasterCensusRptDATA");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrWardWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrWardWs(), "0", "0^All",true);

			}else {
				temp = "<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			
			strmsgText = "ipd.reports.DailyMasterCensusRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyMasterCensusRpt->getWardDetails()", strmsgText);
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

	public static void showReport(DailyMasterCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/dailyMasterCensus_ipdrpt.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			String strCurrDate = formBean.getStrCurrentDate();
			String strEffectiveFrom = formBean.getStrEffectiveFrom();
			String strEffectiveTo = formBean.getStrEffectiveTo();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String strReportId = formBean.getStrReportId();
			String strReportName = "Master Census";

			String strUserRemarks = formBean.getStrUserRemarks();

			boolean footerVisible = true;
			boolean IsGrandTotal = true;
			boolean IsConsolidated =true;
			boolean IsDateWise =true;
			
			String modeval="1";
			if(formBean.getStrCase().equals("1")){
				
				IsConsolidated = false;
				params.put("report_Criteria", "Consolidated");
				
				params.put("ward_Code", strWardCode);
				params.put("date_Code", "null");
				
			}else{
				IsDateWise = false;
				params.put("report_Criteria", "Date Wise");
				
				params.put("ward_Code", "null");
				params.put("date_Code", strCurrDate);
			}
			
			
			if(formBean.getStrGrandTotalRequired()=="0"){
				IsGrandTotal=true;
			}
			else
			{
				IsGrandTotal=false;
				
			}
			
			if(formBean.getStrCase()=="1")
			{
				modeval="1";
				IsDateWise=true;
				IsConsolidated=false;
			}
			if(formBean.getStrCase()=="2")
			{
				modeval="2";
				IsDateWise=false;
				IsConsolidated=true;
			}
			
			
			
			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;
			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("IsGrandTotal",IsGrandTotal);
			params.put("IsDateWise", IsDateWise);
			params.put("IsConsolidated", IsConsolidated);
			params.put("hosp_Code", strHospitalCode);
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
				
			params.put("modeval", modeval);
			if(strDeptCode.equals("0")){
				params.put("dept_Code" , "null");
			}else{
				params.put("dept_Code" , strDeptCode);
			}
			if(strUnitCode.equals("0")){
				params.put("unit_Code" , "null");
			}else{
				params.put("unit_Code" , strUnitCode);
			}
			if(strWardCode.equals("0")){
				params.put("ward_Code" , "null");
			}else{
				params.put("ward_Code" , strWardCode);
			}
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			//e.printStackTrace();

		}
	}

}
