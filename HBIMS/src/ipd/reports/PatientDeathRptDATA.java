package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatientDeathRptDATA {

	public static void initReportPage(PatientDeathRptFB formBean,HttpServletRequest request) {

		PatientDeathRptBO bo = null;

		PatientDeathRptVO voObj = null;

		HisUtil util = new HisUtil("IPD Reports", "PatientDeathRptDATA");
		String strHospNameValues = "";
		//String strDeptVal = "";
		/*String strWardVal = "";
		String strUnitVal = "";*/
		String strmsgText = null;
		try {
			bo = new PatientDeathRptBO();
			voObj = new PatientDeathRptVO();

			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			
			bo.getHospitalName(voObj);
			//bo.getDepartmentDetails(voObj);
			/*bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/
		
			
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0",
					"0^Select Value", false);	
			//strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					//"0^Select Value", false);
			/*strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);
			strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);
			*/
			formBean.setStrHospNameValues(strHospNameValues);
			//formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrUnitValues(strUnitVal);
			formBean.setStrWardValues(strWardVal);*/
			
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));

		} catch (Exception e) {
			strmsgText = "ipd.reports.PatientDeathRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientDeathRpt->initReportPage()", strmsgText);
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
	
	public static void getdeptComboDetails(PatientDeathRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		PatientDeathRptBO bo = null;

		PatientDeathRptVO voObj = null;


		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new PatientDeathRptBO();
			voObj = new PatientDeathRptVO();

			String strHospitalCode=request.getParameter("hospCode"); // Getting Form Bean's Hospital Code From Session
			
			voObj.setStrHospitalCode((strHospitalCode==null || strHospitalCode=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():strHospitalCode);

			bo.getdeptComboDetails(voObj);
			
			util = new HisUtil("IPD Reports", "PatientDeathRptDATA");
			
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
			strmsgText = "ipd.reports.PatientDeathRptDATA.getdeptComboDetails --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","PatientDeathRptDATA->getdeptComboDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	
	public static void getWardDetailsCombo(PatientDeathRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		
		String strmsgText = null;
		try {

			

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";
			
			if(strDeptUnitCode.equals("0"))
			{
				
				PatientDeathRptDATA.getWardDetailsForAll(formBean,request, response);
				}
				
			else
				{
				
				PatientDeathRptDATA.getWardDetails(formBean,request, response);
				
				}
		}
		
		catch (Exception e) {
			strmsgText = "ipd.reports.PatientDeathRptDATA.getWardDetailsCombo --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientDeathRpt->getWardDetailsCombo()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
			
		
	}
	
	public static void getWardDetailsForAll(PatientDeathRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		PatientDeathRptBO bo = null;

		PatientDeathRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new PatientDeathRptBO();
			voObj = new PatientDeathRptVO();

			String strDeptCode = request.getParameter("deptCode");
			
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			bo.getWardCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
	

			util = new HisUtil("IPD Reports", "PatientDeathRptDATA");
			
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
			strmsgText = "ipd.reports.PatientDeathRptDATA.getWardDetailsForAll --> "
				+ e.getMessage();
		HisException eObj = new HisException("ipd",
				"PatientDeathRpt->getWardDetailsForAll()", strmsgText);
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
	public static void getUnitWardDetails(PatientDeathRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PatientDeathRptBO bo = null;

		PatientDeathRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new PatientDeathRptBO();
			voObj = new PatientDeathRptVO();

			String strDeptCode = request.getParameter("deptCode");

			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("IPD Reports", "PatientDeathRptDATA");
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
			strmsgText = "ipd.reports.PatientDeathRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientDeathRpt->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(PatientDeathRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PatientDeathRptBO bo = null;

		PatientDeathRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new PatientDeathRptBO();
			voObj = new PatientDeathRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "PatientDeathRptDATA");
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
			strmsgText = "ipd.reports.PatientDeathRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientDeathRpt->getWardDetails()", strmsgText);
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

	

	public static void showReport(PatientDeathRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		//String reportPath = "/ipd/reports/patientDeath_ipdrpt.rptdesign";
		String reportPath = "/ipd/reports/deathReport_ipdrpt.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String strWardCode = formBean.getStrWardCode();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "Patient Death Report";
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			String strCrNo = formBean.getStrCrNo();

			String strFromTime = formBean.getStrFromTime();
			String strToTime = formBean.getStrToTime();
			String currTime = formBean.getStrCurrentTime();
			String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strFromTime);
			String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?currTime:strToTime);
			String strCurrDate = formBean.getStrCurrentDate();
			
			String strGenderCode = formBean.getStrGenderCode();
			String strAgeFrom = formBean.getStrAgeFrom();
			String strAgeTo = formBean.getStrAgeTo();
			String strPatName = formBean.getStrPatName();
			String strUserRemarks = formBean.getStrUserRemarks();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			
			String strReportCriteria1="&nbsp; ";
			String strReportCriteria2="&nbsp; ";
			int Gender=Integer.parseInt(formBean.getStrGenderCode());
		    boolean IsCRNoWise = false;
	//		boolean IsPatWise = true;
	//		boolean IsAgeWise = true;
			boolean IsSexWise = true;
			boolean IsDeptUnitWise = true;
	//		boolean IsDateWise = true;
			boolean IsWardWise = true;
			
			boolean footerVisible = true;
			boolean bothConditionfalg=false;
			
			
			 System.out.println("Case"+formBean.getStrCase());
			// System.out.println("Gender Condition"+formBean.getStrGenderCode());
			//	params.put("mode_Val", "6");
	
			if(formBean.getStrAgeConditionReq().equals("1"))
			{
				
				strReportCriteria1="Age Range :" + formBean.getStrAgeFrom() + " - " + formBean.getStrAgeTo();
				params.put("mode_Val", "4");
				bothConditionfalg=true;
			}
			
			if(formBean.getStrGenderConditionReq().equals("1"))
			{
				
				
				String strGenderName=formBean.getStrGenderCode();
				
				
				switch(Gender)
				{
					case 1:
						strGenderName="Male";
						break;
					case 2:
						strGenderName="Female";
						break;
					case 3:
						strGenderName="Others";
						break;
						
				}
					
				strReportCriteria2="Gender : " + strGenderName;
				if(bothConditionfalg)
				params.put("mode_Val", "8");	
				else{
				params.put("mode_Val", "5");
				}
			}
			if(formBean.getStrAgeConditionReq().equals("0")&&formBean.getStrGenderConditionReq().equals("0")){
				params.put("mode_Val", "6");
			}
			
			
			
			if(formBean.getStrCase().equals("1")){
		//		IsDateWise = false;
				//params.put("mode_Val", "1");
				params.put("cr_No", "null");
				//params.put("from_Age", "null");
				//params.put("to_Age", "null");
				params.put("pat_Name", "null");
			
				//params.put("gender_Code", "null");
				//params.put("dept_Code", "null");
		    	//params.put("ward_Code", "null");
			    //params.put("unit_Code", "null");
				//params.put("from_Age", strAgeFrom);
				//params.put("to_Age", strAgeTo);
				//params.put("gender_Code", strGenderCode);
				
				if(strAgeFrom.equals("")){
					params.put("from_Age", "null");
							
				}
				else{
					params.put("from_Age", strAgeFrom);
					
				}
				if(strAgeTo.equals("")){
					params.put("to_Age", "null");
				}
				else{
					params.put("to_Age", strAgeTo);
				}
				if(strGenderCode.equals("0"))
				{
					params.put("gender_Code", "null");
				}	
				else{
					params.put("gender_Code", strGenderCode);
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
				if(strUnitCode.equals("0")){
					params.put("unit_Code", "null");
				}else{
					params.put("unit_Code", strUnitCode);
				}
				
			}
			
			
			
			/*else if(formBean.getStrCase().equals("2")){
				IsCRNoWise = true;
				params.put("mode_Val", "2");
				params.put("cr_No", strCrNo);
				params.put("from_Age", "null");
				params.put("to_Age", "null");
				params.put("pat_Name", "null");
				params.put("gender_Code", "null");
				params.put("dept_Code", "null");
				params.put("ward_Code", "null");
				params.put("unit_Code", "null");
				
			
			}else if (formBean.getStrCase().equals("3")) {
		//		IsPatWise = false;
				params.put("mode_Val", "3");
				params.put("pat_Name", strPatName);
				params.put("cr_No", "null");
				params.put("from_Age", "null");
				params.put("to_Age", "null");
				params.put("gender_Code", "null");
				params.put("dept_Code", "null");
				params.put("ward_Code", "null");
				params.put("unit_Code", "null");
				
							
			}else if (formBean.getStrCase().equals("4")) {
		//		IsAgeWise = false;
				params.put("mode_Val", "4");
				params.put("from_Age", strAgeFrom);
				params.put("to_Age", strAgeTo);
				params.put("cr_No", "null");
				params.put("pat_Name", "null");
				params.put("gender_Code", "null");
				params.put("dept_Code", "null");
				params.put("ward_Code", "null");
				params.put("unit_Code", "null");
				
							
			}else if (formBean.getStrCase().equals("5")) {
				IsSexWise = false;
				if(strGenderCode.equals("0")){
					params.put("gender_Code", "null");
				}else{
					params.put("gender_Code", strGenderCode);
				}
				params.put("mode_Val", "5");
				params.put("cr_No", "null");
				params.put("from_Age", "null");
				params.put("to_Age", "null");
				params.put("pat_Name", "null");
				params.put("dept_Code", "null");
				params.put("ward_Code", "null");
				params.put("unit_Code", "null");
				
						
			}else if (formBean.getStrCase().equals("6")) {
				IsDeptUnitWise = false;
				params.put("mode_Val", "6");
				params.put("cr_No", "null");
				params.put("from_Age", "null");
				params.put("to_Age", "null");
				params.put("pat_Name", "null");
				params.put("gender_Code", "null");
				params.put("unit_Code", "null");
				
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
						
			}
			
			else if (formBean.getStrCase().equals("7")) {
				IsWardWise = false;
				params.put("mode_Val", "7");
				params.put("cr_No", "null");
				params.put("from_Age", "null");
				params.put("to_Age", "null");
				params.put("pat_Name", "null");
				params.put("gender_Code", "null");
				
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
				
			}
			*/
			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;
			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Criteria1", strReportCriteria1);
			params.put("report_Criteria2", strReportCriteria2);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
			params.put("hosp_Code", strHospitalCode);
			params.put("IsCrNoWise", IsCRNoWise);
		//	params.put("IsPatWise", IsPatWise);
		//	params.put("IsAgeWise", IsAgeWise);
			params.put("IsSexWise", IsSexWise);
			params.put("IsDeptUnitWise", IsDeptUnitWise);
			params.put("IsWardWise", IsWardWise);
		//	params.put("IsDateWise", IsDateWise);
					
			
			
	
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}
