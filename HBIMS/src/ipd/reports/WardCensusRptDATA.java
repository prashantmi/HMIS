package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WardCensusRptDATA {

	public static void initReportPage(WardCensusRptFB formBean,HttpServletRequest request) {

		WardCensusRptBO bo = null;
		WardCensusRptVO voObj = null;

		HisUtil util = null;
		//String strDeptVal = "";
		String strHospNameValues = "";
	/*	String strWardVal = "";
		String strUnitVal = "";*/
		String strmsgText = null;
		try {
			bo = new WardCensusRptBO();
			voObj = new WardCensusRptVO();

			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrWardCode(formBean.getStrWardCode());
			voObj.setStrSeatId(formBean.getStrSeatId());

			bo.getHospitalName(voObj);
			//bo.getDepartmentDetails(voObj);
			/*bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("IPD Reports", "WardCensusRptDATA");
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0",
					"0^Select Value", false);	

			//strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					//"0^Select Value", false);
			/*strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);
			strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);*/
			formBean.setStrHospNameValues(strHospNameValues);
			//formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrWardValues(strWardVal);
			formBean.setStrUnitValues(strUnitVal);*/
						
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));

		} catch (Exception e) {
			strmsgText = "ipd.reports.WardCensusRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"WardCensusRpt->initReportPage()", strmsgText);
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
	
	public static void getdeptComboDetails(WardCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		WardCensusRptBO bo = null;
		WardCensusRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new WardCensusRptBO();
			voObj = new WardCensusRptVO();


			String strHospitalCode=request.getParameter("hospCode"); // Getting Form Bean's Hospital Code From Session
			
			voObj.setStrHospitalCode((strHospitalCode==null || strHospitalCode=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():strHospitalCode);

			bo.getdeptComboDetails(voObj);
			
			util = new HisUtil("IPD Reports", "WardCensusRptDATA");
			
			String temp1= "<option value='0'>Select Value</option>";
			
			if (voObj.getStrDeptWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrDeptWs(), "0", "0^Select Value",true);
			}
			else {
				temp1 ="<option value='1'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);
		}
		
		catch (Exception e) 
		{
			strmsgText = "ipd.reports.WardCensusRptDATA.getdeptComboDetails --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","WardCensusRptDATA->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	
	public static void getUnitWardDetails(WardCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		WardCensusRptBO bo = null;

		WardCensusRptVO voObj = null;
		String temp="";
		String temp1="";
		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new WardCensusRptBO();
			voObj = new WardCensusRptVO();

			String strDeptCode = request.getParameter("deptCode");

			if (strDeptCode == null)
				strDeptCode = "0";
           if(strDeptCode.equals("0"))
           {
        	   util = new HisUtil("IPD Reports", "WardCensusRptDATA");
     			 temp = "<option value='0'>All</option>";
     			 temp1= "<option value='0'>Select Value</option>"; 
           }
           else
           {
        	   voObj.setStrDeptCode(strDeptCode);
        	   voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

   			bo.getUnitWardDetails(voObj);

   			if (voObj.getStrMsgType().equals("1")) {
   				throw new Exception(voObj.getStrMsgString());

   			}
   	

   			util = new HisUtil("IPD Reports", "WardCensusRptDATA");
   			 temp = "<option value='0'>Select Value</option>";
   			 temp1= "<option value='0'>Select Value</option>";
   			if (voObj.getStrUnitWs().size() != 0) {

   				temp = util.getOptionValue(voObj.getStrUnitWs(), "0", "0^All",
   						true);

   			}else{
   				temp = "<option value='0'>All<option>";
   			}
   			if (voObj.getStrWardWs().size() != 0) {
   				temp1 = util.getOptionValue(voObj.getStrWardWs(), "0", "0^Select Value",
   			
   					true);
   			}
   			else {
   				temp1 ="<option value='0'>Select Value</option>";
   			}
 
           }
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp+"^"+temp1);

		} catch (Exception e) {
			strmsgText = "ipd.reports.WardCensusRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"WardCensusRpt->getUnitDetails()", strmsgText);
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
	public static void getWardDetailsCombo(WardCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		String strmsgText = null;
		try {

			

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";
			
			if(strDeptUnitCode.equals("0"))
			{
				
				WardCensusRptDATA.getWardDetailsForAll(formBean,request, response);
				}
				
			else
				{
				
				WardCensusRptDATA.getWardDetails(formBean,request, response);
				
				}
		}
		
		catch (Exception e) {
			strmsgText = "ipd.reports.WardCensusRptDATA.getWardDetailsCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"WardCensusRpt->getWardDetailsCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
			
		
	}
	
	public static void getWardDetailsForAll(WardCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		WardCensusRptBO bo = null;

		WardCensusRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new WardCensusRptBO();
			voObj = new WardCensusRptVO();

			String strDeptCode = request.getParameter("deptCode");
			
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			bo.getWardCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
	

			util = new HisUtil("IPD Reports", "WardCensusRptDATA");
			
			String temp1= "<option value='0'>Select Value</option>";
			
			if (voObj.getStrWardWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrWardWs(), "0", "0^Select Value",
			
					true);
			}
			else {
				temp1 ="<option value='1'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);

		} catch (Exception e) {
			strmsgText = "ipd.reports.WardCensusRptDATA.getWardDetailsForAll --> "
				+ e.getMessage();
		HisException eObj = new HisException("ipd",
				"WardCensusRpt->getWardDetailsForAll()", strmsgText);
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
	public static void getWardDetails(WardCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		WardCensusRptBO bo = null;

		WardCensusRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new WardCensusRptBO();
			voObj = new WardCensusRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "WardCensusRptDATA");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrWardWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrWardWs(), "0", "0^Select Value",
						true);

			}else {
				temp = "<option value='0'>Select Value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			strmsgText = "ipd.reports.WardCensusRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"WardCensusRpt->getWardDetails()", strmsgText);
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

	public static void getRoomDetails(WardCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		WardCensusRptBO bo = null;
		String strmsgText = null;
		WardCensusRptVO voObj = null;

		HisUtil util = null;

		try {

			bo = new WardCensusRptBO();
			voObj = new WardCensusRptVO();

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

			util = new HisUtil("IPD Reports", "WardCensusRptDATA");
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
			strmsgText = "ipd.reports.WardCensusRptDATA.getRoomDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"WardCensusRpt->getRoomDetails()", strmsgText);
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

	public static void showReport(WardCensusRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/wardCensus_ipdrptNew.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String strRoomCode =formBean.getStrRoomNo();
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();

			String strFromTime = formBean.getStrFromTime();
			String strToTime = formBean.getStrToTime();
			String currTime = formBean.getStrCurrentTime();
			String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strFromTime);
			String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?currTime:strToTime);
			String strCurrDate = formBean.getStrCurrentDate();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "Ward Census Report";
			String modeval="1";

			String strUserRemarks = formBean.getStrUserRemarks();
		
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			boolean rooterVisible = true;
			boolean IsGrandTotal = false;
			boolean IsDateWise = true;
			boolean IsConsolidated=true;
		
			if (formBean.getStrIsFooter().equals("1")) {

				rooterVisible = false;

			}
			if(formBean.getStrGrandTotalRequired().equals("0")){
				IsGrandTotal=true;
			}
			else
			{
				IsGrandTotal=false;
				
			}
			
			if(formBean.getStrCase().equals("1"))
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
			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", rooterVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("fromDate", sdf.format(sdf.parse(strEffectiveFrom)));
            params.put("toDate", sdf.format(sdf.parse(strEffectiveTo)));
			params.put("hosp_Code", strHospitalCode);
			params.put("IsGrandTotal",IsGrandTotal);
			params.put("modeval", modeval);
			params.put("IsConsolidated", IsConsolidated);
			params.put("IsDateWise", IsDateWise);
			params.put("IsDateWise", IsDateWise);
						
			if(strDeptCode.equals("0")){
				params.put("dept_Code", "0");
			}else{
				params.put("report_formattype", reportFormat);
			}
			
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

			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
