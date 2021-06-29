package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MortalityStatisticsRptDATA {

	public static void initReportPage(MortalityStatisticsRptFB formBean,HttpServletRequest request) {

		MortalityStatisticsRptBO bo = null;
		MortalityStatisticsRptVO voObj = null;

		HisUtil util = null;
		//String strDeptVal = "";
		String strmsgText = null;

		String strHospNameValues = "";
		/*String strUnitVal = "";
		String strWardVal = "";*/

		try {
			bo = new MortalityStatisticsRptBO();
			voObj = new MortalityStatisticsRptVO();

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
			util = new HisUtil("IPD Reports", "MortalityStatisticsRptDATA");

			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0",
								"0^Select Value", false);	
			//strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					//"0^All", false);
			/*strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);
			strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);*/
			formBean.setStrHospNameValues(strHospNameValues);
			//formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrUnitValues(strUnitVal);
			formBean.setStrWardValues(strWardVal);*/
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));



		} catch (Exception e) {
			strmsgText = "ipd.reports.MortalityStatisticsRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"MortalityStatisticsRpt->initReportPage()", strmsgText);
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
	public static void getdeptComboDetails(MortalityStatisticsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		MortalityStatisticsRptBO bo = null;
		MortalityStatisticsRptVO voObj = null;


		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new MortalityStatisticsRptBO();
			voObj = new MortalityStatisticsRptVO();

			String strHospitalCode=request.getParameter("hospCode"); // Getting Form Bean's Hospital Code From Session
			
			voObj.setStrHospitalCode((strHospitalCode==null || strHospitalCode=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():strHospitalCode);

			bo.getdeptComboDetails(voObj);
			
			util = new HisUtil("IPD Reports", "MortalityStatisticsRptDATA");
			
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
			strmsgText = "ipd.reports.MortalityStatisticsRptDATA.getdeptComboDetails --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","MortalityStatisticsRptDATA->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}


	
	public static void getUnitDetails(MortalityStatisticsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		MortalityStatisticsRptBO bo = null;
		MortalityStatisticsRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		String temp="";
		String temp1="";
		try {

			bo = new MortalityStatisticsRptBO();
			voObj = new MortalityStatisticsRptVO();

			String strDeptCode = request.getParameter("deptCode");
			if (strDeptCode == null)
				strDeptCode = "0";
			if(strDeptCode.equals("0"))
	           {
	        	   util = new HisUtil("IPD Reports", "MortalityStatisticsRptDATA");
	     			 temp = "<option value='0'>All</option>";
	     			 temp1= "<option value='0'>All</option>"; 
	           }
			else
				
			{
				
			
			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getUnitDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("IPD Reports", "MortalityStatisticsRptDATA");
			 temp = "<option value='0'>All</option>";
			 temp1= "<option value='0'>All</option>";
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
			}
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp+"^"+temp1);

		} catch (Exception e) {
			strmsgText = "ipd.reports.MortalityStatisticsRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"MortalityStatisticsRpt->getUnitDetails()", strmsgText);
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
	public static void getWardDetailsCombo(MortalityStatisticsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		String strmsgText = null;
		try {

			

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";
			
			if(strDeptUnitCode.equals("0"))
			{
				
				MortalityStatisticsRptDATA.getWardDetailsForAll(formBean,request, response);
				}
				
			else
				{
				
				MortalityStatisticsRptDATA.getWardDetails(formBean,request, response);
				
				}
		}
		
		catch (Exception e) {
			strmsgText = "ipd.reports.MortalityStatisticsRptDATA.getWardDetailsCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"MortalityStatisticsRpt->getWardDetailsCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
			
		
	}
	
	public static void getWardDetailsForAll(MortalityStatisticsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		MortalityStatisticsRptBO bo = null;

		MortalityStatisticsRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new MortalityStatisticsRptBO();
			voObj = new MortalityStatisticsRptVO();

			String strDeptCode = request.getParameter("deptCode");
			
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			bo.getWardCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
	

			util = new HisUtil("IPD Reports", "MortalityStatisticsRptDATA");
			
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
			strmsgText = "ipd.reports.MortalityStatisticsRptDATA.getWardDetailsForAll --> "
				+ e.getMessage();
		HisException eObj = new HisException("ipd",
				"MortalityStatisticsRpt->getWardDetailsForAll()", strmsgText);
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
	public static void getWardDetails(MortalityStatisticsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		MortalityStatisticsRptBO bo = null;
		MortalityStatisticsRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new MortalityStatisticsRptBO();
			voObj = new MortalityStatisticsRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");
			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "MortalityStatisticsRptDATA");
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
			strmsgText = "ipd.reports.MortalityStatisticsRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"MortalityStatisticsRpt->getWardDetails()", strmsgText);
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

	public static void showReport(MortalityStatisticsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/mortalityStatistics_ipdrpt.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			String strReportName = "Mortality Statistics";
			String strReportId = formBean.getStrReportId();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strUserRemarks = formBean.getStrUserRemarks();
			String strFromTime = formBean.getStrFromTime();
			String strToTime = formBean.getStrToTime();
			String currTime = formBean.getStrCurrentTime();
			String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strFromTime);
			String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?currTime:strToTime);
			String strCurrDate = formBean.getStrCurrentDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");

			boolean visible = true;
			boolean rooterVisible = true;

			 if (formBean.getStrIsWardWise().equals("1")) {
			 visible = false;
			 params.put("mode_Val", "1");
			 }

			if (formBean.getStrIsFooter().equals("1")) {
				rooterVisible = false;
			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", rooterVisible);
			params.put("report_user_Remarks", strUserRemarks);

			if(strWardCode.equals("0")){
				params.put("ward_Code", "null");
			}else{
				params.put("ward_Code", strWardCode);
			}
			if(strDeptCode.equals("0")){
				params.put("dept_Code","null");
			}else{
				params.put("dept_Code",strDeptCode);
			}
			if(strUnitCode.equals("0")){
				params.put("unit_Code", "null");
			}else{
				params.put("unit_Code", strUnitCode);
			}
			params.put("IsWardWise", visible);
			params.put("hosp_Code", strHospitalCode);
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
			
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			//e.printStackTrace();

		}
	}
}
