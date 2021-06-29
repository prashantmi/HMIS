package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

public class DailyDischargeRptDATA {

	public static void initReportPage(DailyDischargeRptFB formBean,HttpServletRequest request) {

		DailyDischargeRptBO bo = null;

		DailyDischargeRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = new HisUtil("IPD Reports", "DailyDischargeRptDATA");
		String strHospNameValues = "";
		String strDeptVal = "";
		/*String strUnitVal = "";
		String strWardVal = "";*/

		try {
			bo = new DailyDischargeRptBO();
			 voObj = new DailyDischargeRptVO();
			 

			
		//	voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			 voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			 voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			 bo.getHospitalName(voObj);
			 bo.getDischargeTypeName(voObj);
			//bo.getDepartmentDetails(voObj);
			//bo.getUnitWardDetails(voObj);
			//bo.getWardDetails(voObj);
			 
			 System.out.println(voObj.getStrDiscargeTypeWs().size());
			 
			 String sb=createHlpForRadioButtons(voObj.getStrDiscargeTypeWs());

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			/*strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0",
					"0^Select Value", false);*/
			strHospNameValues= util.getOptionValue(voObj.getStrHospitalWs(),request.getSession().getAttribute("HOSPITAL_CODE").toString(),"0^Select Value", false);

			formBean.setStrHospNameValues(strHospNameValues);
			
			//formBean.setStrDischargeType(util.getOptionValue(voObj.getStrDiscargeTypeWs(), "8",	"", false));
			
			formBean.setStrRadioBean(sb);
			formBean.setStrRadioBeanSize(voObj.getStrDiscargeTypeWs().size());
			

			
		//	strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
				//	"0^Select Value", false);
			/*strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);
			strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);*/

			//formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrWardValues(strWardVal);
			formBean.setStrUnitValues(strUnitVal);*/
			
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));
			

		} catch (Exception e) {
			strmsgText = "ipd.reports.DailyDischargeRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyDischargeRpt->initReportPage()", strmsgText);
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
	public static void getdeptComboDetails(DailyDischargeRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		DailyDischargeRptBO bo = null;

		DailyDischargeRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new DailyDischargeRptBO();
			voObj = new DailyDischargeRptVO();

			String strhospCode=request.getParameter("hospCode").toString();

			
			if (strhospCode == null)
				strhospCode = "0";
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getdeptComboDetails(voObj);
			
			util = new HisUtil("IPD Reports", "AdmittedPatientRptDATA");
			
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
			strmsgText = "ipd.reports.AdmittedPatientRptDATA.getWardDetailsCombo --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","AdmittedPatientRpt->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}

	public static void getUnitWardDetails(DailyDischargeRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DailyDischargeRptBO bo = null;

		DailyDischargeRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new DailyDischargeRptBO();
			voObj = new DailyDischargeRptVO();

			String strDeptCode = request.getParameter("deptCode");

			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "DailyDischargeRptDATA");
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
			strmsgText = "ipd.reports.DailyDischargeRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyDischargeRpt->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(DailyDischargeRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DailyDischargeRptBO bo = null;

		DailyDischargeRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new DailyDischargeRptBO();
			voObj = new DailyDischargeRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "DailyDischargeRptDATA");
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
			strmsgText = "ipd.reports.DailyDischargeRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DailyDischargeRpt->getWardDetails()", strmsgText);
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

	public static void showReport(DailyDischargeRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/dailyDischarge_ipdrptNew.rptdesign";
		String reportPath1="/ipd/reports/deathReport_ipdrpt.rptdesign";
		String reportPath2="/ipd/reports/listAbscondingCase_ipdrpt.rptdesign";
		// String moduleName = "IPD";
		// String fileName = "dateReport";
		String reportFormat = "html";
		

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String strDischargeType=formBean.getStrRadioCheckValue();
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			
			String strFromTime = formBean.getStrFromTime();
			String strToTime = formBean.getStrToTime();
			String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strFromTime);
			String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strToTime);
			String strCurrDate = formBean.getStrCurrentDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

			
			
			//String strEffectiveFrom = formBean.getStrEffectiveFrom();
			//String strEffectiveTo = formBean.getStrEffectiveTo();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();

			IpdConfigUtil ipdC=new IpdConfigUtil(strHospitalCode);
			String strReportId = formBean.getStrReportId();
			String strReportName = "Discharge Report";
			//SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String strUserRemarks = formBean.getStrUserRemarks();
			
			boolean footerVisible = true;

			
			if (formBean.getStrIsFooter().equals("1")) {

				footerVisible = false;

			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_formattype", reportFormat);
			
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
	
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
		
			
			if(strDeptCode.equals("0")){
				params.put("dept_Code", "0");
			}else{
				params.put("dept_Code", strDeptCode);
			}
			if(strWardCode.equals("0")){
				params.put("ward_Code", "0");
			}else{
				params.put("ward_Code", strWardCode);
			}
			
			
			if(strUnitCode.equals("0")){
				params.put("unit_Code", "0");
			}else{
				params.put("unit_Code", strUnitCode);
			}
			
			
			
		    if(strDischargeType.equals(ipdC.getStrDischargeTypeLAMA()) || strDischargeType.equals(ipdC.getStrNormlaDischargeType()) || strDischargeType.equals(ipdC.getStrDischargeTypeTransfer()) || strDischargeType.equals(ipdC.getStrDischargeTypeReferral()) || strDischargeType.equals("0"))
		    {
		    
		    	if(strDischargeType.equals(ipdC.getStrNormlaDischargeType()))
		    	params.put("report_Name", "Discharge Report (Normal discharge)");
		    	else if (strDischargeType.equals(ipdC.getStrDischargeTypeLAMA()))
		    	params.put("report_Name", "Discharge Report (LAMA)");
		    	else if (strDischargeType.equals(ipdC.getStrDischargeTypeReferral()))
			    params.put("report_Name", "Discharge Report (Referral)");
		    	else if (strDischargeType.equals(ipdC.getStrDischargeTypeTransfer()))
			    params.put("report_Name", "Discharge Report (Outside Transfer)");
		    	else if(strDischargeType.equals("0"))
		    	params.put("report_Name", "All Discharge Report");		    		
		    	params.put("dis_type", strDischargeType);
		    	ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);
		    }
		    else if (strDischargeType.equals(ipdC.getStrDischargeTypeDeath())){
				
		    	params.put("report_Name", "Patient Death Report");
		    	params.put("dis_type", strDischargeType);
				ts.displayReport(request, response, reportPath1, reportFormat,
						params,strHospitalCode);
			}
		    else  {
		    	params.put("report_Name", "Patient Absconded Report");
		    	params.put("dis_type", strDischargeType);
				ts.displayReport(request, response, reportPath2, reportFormat,
						params,strHospitalCode);
			}
		} catch (Exception e) {

			//e.printStackTrace();

		}
	}
	
	public static String createHlpForRadioButtons(WebRowSet ws) throws SQLException
	{
		
		int i=ws.size();
		StringBuffer sb=new StringBuffer();
		sb.append("<input type='radio' value='0' name='dischargeTypeRadio' checked='true'>"+"All" );
		while(ws.next())
		{
			sb.append("<input type='radio' value='"+ws.getString(1)+"' name='dischargeTypeRadio'>"+ws.getString(2) );	
		}
		System.out.println(sb.toString());
		System.out.println("webrow set size is "+i);
		return sb.toString();
		
	}
	

}
