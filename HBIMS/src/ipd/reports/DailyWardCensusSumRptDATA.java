package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DailyWardCensusSumRptDATA {

	public static void initReportPage(DailyWardCensusSumRptFB formBean) 
	{
		DailyWardCensusSumRptBO  bo = null;
		DailyWardCensusSumRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		String strDeptVal = "";
		String strWardVal = "";

		try 
		{
			bo = new DailyWardCensusSumRptBO();
			voObj = new DailyWardCensusSumRptVO();
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(formBean.getStrSeatId());

			bo.getDepartmentDetails(voObj);
			bo.getWardAll(voObj);
			bo.getWardType(voObj);
			bo.getYearName(voObj);

			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
			util = new HisUtil("ADT", "DailyWardCensusSumRptDATA");
			
			strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0", "0^All", false);
			strWardVal = util.getOptionValue(voObj.getStrWardAllWs(), "0","0^All", false);
			String strWardTypeVal=util.getOptionValue(voObj.getStrWardTypeWs(), "0","0^All", false);
			String strYearValues=util.getOptionValue(voObj.getStrYearWs(), "0",	"", false);
			
			formBean.setStrDeptValues(strDeptVal);
			formBean.setStrWardAllValues(strWardVal);
			formBean.setStrWardTypeVal(strWardTypeVal);
			formBean.setStrYearList(strYearValues);			
			
			Calendar cal = Calendar.getInstance();			
			cal.setTime( new SimpleDateFormat("dd-MMM-yyyy").parse(util.getASDate("dd-MMM-yyyy")) );			
			cal.add(Calendar.DATE, -1);						
			formBean.setStrCurrentDate(  new SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime()) );
			//formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
		} 
		catch (Exception e) 
		{
			strmsgText = "ipd.reports.DailyWardCensusSumRptDATA.initReportPage --> "+ e.getMessage();
			HisException eObj = new HisException("ADT","DailyWardCensusSumRpt->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
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
	public static void getWardDetailsCombo(DailyWardCensusSumRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		String strmsgText = null;
		try {

			

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";
			
			if(strDeptUnitCode.equals("0"))
			{
				
				DailyWardCensusSumRptDATA.getWardDetailsForAll(formBean,request, response);
				}
				
			else
				{
				
				DailyWardCensusSumRptDATA.getWardDetails(formBean,request, response);
				
				}
		}
		
		catch (Exception e) {
			strmsgText = "ipd.reports.DailyWardCensusSumRptDATA.getWardDetailsCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyWardCensusSumRpt->getWardDetailsCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
			
		
	}
	
	public static void getWardDetailsForAll(DailyWardCensusSumRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		DailyWardCensusSumRptBO bo = null;

		DailyWardCensusSumRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new DailyWardCensusSumRptBO();
			voObj = new DailyWardCensusSumRptVO();

			String strDeptCode = request.getParameter("deptCode");
			
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			bo.getWardCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
	

			util = new HisUtil("IPD Reports", "DailyWardCensusSumRptDATA");
			
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
			strmsgText = "ipd.reports.DailyWardCensusSumRptDATA.getWardDetailsForAll --> "
				+ e.getMessage();
		HisException eObj = new HisException("ipd",
				"DailyWardCensusSumRpt->getWardDetailsForAll()", strmsgText);
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
	public static void getUnitWardDetails(DailyWardCensusSumRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DailyWardCensusSumRptBO bo = null;
		DailyWardCensusSumRptVO voObj = null;
		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new DailyWardCensusSumRptBO();
			voObj = new DailyWardCensusSumRptVO();

			String strDeptCode = request.getParameter("deptCode");
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("IPD Reports", "DailyWardCensusSumRptDATA");
			String temp = "<option value='0'>Select Value</option>";
			String temp1= "<option value='0'>Select Value</option>";

			if (voObj.getStrUnitWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrUnitWs(), "0", "0^All",
						true);
				
			}else {
				temp ="<option value='0'>All</option>";
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
			strmsgText = "ipd.reports.DailyWardCensusSumRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyWardCensusSumRpt->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(DailyWardCensusSumRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DailyWardCensusSumRptBO bo = null;
		DailyWardCensusSumRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new DailyWardCensusSumRptBO();
			voObj = new DailyWardCensusSumRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("IPD Reports", "DailyWardCensusSumRptDATA");
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
			strmsgText = "ipd.reports.DailyWardCensusSumRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyWardCensusSumRptDATA->getWardDetails()", strmsgText);
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

	public static void showReport(DailyWardCensusSumRptFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
		ReportUtil ts = new ReportUtil();
		String reportPath = "/ipd/reports/dailyWardCensusSum_ipdrpt.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try 
		{
			reportFormat = formBean.getStrReportFormat();
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportDate = formBean.getStrReportDate();
			String strReportName = "Daily Ward Census (Summary) Report";
			String strFromDate = formBean.getStrEffectiveFrom();
			String strToDate =formBean.getStrEffectiveTo();
			String strWardCodeAll = formBean.getStrWardCodeAll();
			String strUserRemarks = formBean.getStrUserRemarks();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String modeval="2";
			String deptCheck="1";
			
			boolean rooterVisible = true;
			boolean IsGrandTotal = false;
			boolean IsDateWise = false;
			boolean IsConsolidated=true;
			
			if (formBean.getStrIsFooter().equals("1")) 
			{
				rooterVisible = false;
			}
			if (formBean.getStrNewBornOnly().equals("1")) //New Born Checked
			{
				reportPath = "/ipd/reports/dailyWardCensusSumNewBornOnly_ipdrpt.rptdesign";
				
			}
						
			/*if(formBean.getStrGrandTotalRequired().equals("0")){
				IsGrandTotal=true;
			}
			else
			{
				IsGrandTotal=false;
				
			}*/
			
			/*if(formBean.getStrCase().equals("1"))
			{
				modeval="1";
				IsDateWise=true;
				IsConsolidated=false;
			}
			
			
			if(formBean.getStrCase().equals("2"))
			{
				modeval="2";
				IsDateWise=false;
				IsConsolidated=true;
			}*/
			/*if(formBean.getStrDeptCheckRequired()!=null &&  formBean.getStrDeptCheckRequired().equals("1"))
			{
				deptCheck="1";
			}*/
			
			
			reportFormat = formBean.getStrReportFormat();			
			params.put("report_id", strReportId);			
			params.put("report_Footer_Visible", rooterVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
			params.put("report_Date", sdf.format(sdf.parse(strReportDate)));
			params.put("IsGrandTotal",IsGrandTotal);
			params.put("IsConsolidated", IsConsolidated);
			params.put("IsDateWise", IsDateWise);			
			if(formBean.getStrDateMonthWiseCase().equals("2"))//Month Wise
			{
				params.put("monthWise", "1");
				if (formBean.getStrCase().equals("1")||formBean.getStrCase().equals("2"))//Department Wise or Ward Wise
				{
					if (formBean.getStrNewBornOnly().equals("1")) //New Born Checked
					{						
						params.put("toDate", formBean.getStrMonthValue()+"-"+formBean.getStrYear());
						params.put("fromDate",formBean.getStrMonthValue()+"-"+formBean.getStrYear());
						modeval="8";
						strReportName = "Daily Ward Census (Summary) Report-Month Wise New Born Only";
					}
					else
					{
						params.put("toDate", formBean.getStrMonthValue()+"-"+formBean.getStrYear());
						params.put("fromDate",formBean.getStrMonthValue()+"-"+formBean.getStrYear());
						modeval="7";
						strReportName = "Daily Ward Census (Summary) Report-Month Wise";
						/*
						reportPath = "/ipd/reports/dailyWardCensusSumMonthWise_ipdrpt.rptdesign";//------------------
*/					}
				}
				else//Ward Type Wise
				{
					params.put("toDate", formBean.getStrMonthValue()+"-"+formBean.getStrYear());
					params.put("fromDate",formBean.getStrMonthValue()+"-"+formBean.getStrYear());
					modeval="9";
					strReportName = "Daily Ward Census (Summary) Report-Ward Type & Month Wise";
					/*
					reportPath="/ipd/reports/dailyWardTypeCensusSumMonthWise_ipdrpt.rptdesign";//-------------
*/				}				
			}
			else//Date Wise
			{
				params.put("monthWise", "0");
				if (formBean.getStrCase().equals("1")||formBean.getStrCase().equals("2"))//Department Wise or Ward Wise
				{
					if (formBean.getStrNewBornOnly().equals("1")) //New Born Checked
					{					
						params.put("fromDate",strFromDate);
						params.put("toDate", strToDate);
						modeval="6";
						strReportName = "Daily Ward Census (Summary) Report-Date Wise New Born Only";
					}
					else
					{
						params.put("fromDate",strFromDate);
						params.put("toDate", strToDate);
						modeval="2";
						strReportName = "Daily Ward Census (Summary) Report-Date Wise";
					}
				}
				else//Ward Type Wise
				{
					params.put("fromDate",strFromDate);
					params.put("toDate", strToDate);
					modeval="5";
					strReportName = "Daily Ward Census (Summary) Report-Date Wise & Ward Type Wise";
				}
			}				
				
			if(formBean.getStrCase().equals("1"))//Department
			{
				if(strDeptCode.equals("0"))
				{
					params.put("dept_Code" , "null");
				}
				else
				{
					params.put("dept_Code" , strDeptCode);
				}
				if(strUnitCode.equals("0"))
				{
					params.put("unit_Code" , "null");
				}
				else
				{
					params.put("unit_Code" , strUnitCode);
				}
				if(strWardCode.equals("0"))
				{
					params.put("ward_Code" , "null");
				}
				else
				{
					params.put("ward_Code" , strWardCode);
				}
				deptCheck="1";
				//modeval="2";
			}
			else//Ward Wise & Ward Type Wise
			{
				params.put("dept_Code" , "null");				
				params.put("unit_Code" , "null");				
				
				if(!formBean.getStrCase().equals("3"))//Ward Wise
				{
					if(strWardCodeAll.equals("0"))//All Ward Checked
					{
						params.put("ward_Code" , "null");
					}
					else
					{
						params.put("ward_Code" , strWardCodeAll);
					}
				}
				else//Ward Type Wise
				{
					//strReportName = "Daily Ward Census (Summary) Report-Ward Type Wise";
					if(formBean.getStrWardTypeVal().equals("0"))
					{
						params.put("ward_Code" , "null");
					}
					else
					{
						params.put("ward_Code" , formBean.getStrWardTypeVal());
					}	
				}				
				deptCheck="0";
			}
			params.put("deptCheck", deptCheck);			
			params.put("report_Name", strReportName);
			params.put("modeval", modeval);
			
			
			if(formBean.getStrCase().equals("1") || formBean.getStrCase().equals("2"))
			{
				ts.displayReport(request, response, reportPath, reportFormat,
						params,strHospitalCode);
			}				
			else
			{
				reportPath="/ipd/reports/dailyWardTypeCensusSum_ipdrpt.rptdesign";
				ts.displayReport(request, response, reportPath, reportFormat,
						params,strHospitalCode);
			}
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
		}
	}
}
