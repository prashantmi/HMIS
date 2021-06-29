package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatientDischargeRptDATA {

	public static void initReportPage(PatientDischargeRptFB formBean,HttpServletRequest request) {

		PatientDischargeRptBO bo = null;

		PatientDischargeRptVO voObj = null;

		HisUtil util = new HisUtil("IPD Reports", "PatientDischargeRptDATA");
		String strDeptVal = "";
		String strHospNameValues = "";
		/*String strWardVal = "";
		String strUnitVal = "";*/
		String strmsgText = null;
		try {
			bo = new PatientDischargeRptBO();
			voObj = new PatientDischargeRptVO();

			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			bo.getHospitalName(voObj);
			bo.getDepartmentDetails(voObj);
		/*	bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/
		
			
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0",
					"0^Select Value", false);
			strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					"0^All", false);
			/*strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);
			strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);*/
			
		
			formBean.setStrHospNameValues(strHospNameValues);
			formBean.setStrDeptValues(strDeptVal);
		/*	formBean.setStrUnitValues(strUnitVal);
			formBean.setStrWardValues(strWardVal);*/
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));
		} catch (Exception e) {
			strmsgText = "ipd.reports.PatientDischargeRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientDischargeRpt->initReportPage()", strmsgText);
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

	public static void getUnitDetails(PatientDischargeRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PatientDischargeRptBO bo = null;

		PatientDischargeRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new PatientDischargeRptBO();
			voObj = new PatientDischargeRptVO();

			String strDeptCode = request.getParameter("deptCode");

			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getUnitDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "PatientDischargeRptDATA");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrUnitWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrUnitWs(), "0", "0^All",
						true);

			}else{
				temp = "<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			strmsgText = "ipd.reports.PatientDischargeRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientDischargeRpt->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(PatientDischargeRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PatientDischargeRptBO bo = null;

		PatientDischargeRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new PatientDischargeRptBO();
			voObj = new PatientDischargeRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "PatientDischargeRptDATA");
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
			strmsgText = "ipd.reports.PatientDischargeRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientDischargeRpt->getWardDetails()", strmsgText);
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

	public static void getConsultDetails(PatientDischargeRptFB formBean,HttpServletRequest request,HttpServletResponse response) {

		PatientDischargeRptBO bo = null;

		PatientDischargeRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = new HisUtil("IPD Reports", "PatientDischargeRptDATA");
		String strConsultVal ="<option value='0'>Select Value</option>";
		try {
			bo = new PatientDischargeRptBO();
			voObj = new PatientDischargeRptVO();
			
			String strHospitalCode=request.getParameter("hospCode"); // Getting Form Bean's Hospital Code From Session
			
			voObj.setStrHospitalCode((strHospitalCode==null || strHospitalCode=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():strHospitalCode);
			
			bo.getConsultDetails(voObj);
			
//			if (voObj.getStrMsgType().equals("1")) {
//				throw new Exception(voObj.getStrMsgString());
//			}
			
			if(voObj.getStrConsultWs() != null && voObj.getStrConsultWs().size() != 0){
			strConsultVal = util.getOptionValue(voObj.getStrConsultWs(), "0",
					"0^All", false);
			}else{
				
				strConsultVal = "<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strConsultVal);
			
			
			//formBean.setStrConsultantValues(strConsultVal);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			
			
		} catch (Exception e) {
			strmsgText = "ipd.reports.PatientDischargeRptDATA.getConsultDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientDischargeRpt->getConsultDetails()", strmsgText);
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

	public static void showReport(PatientDischargeRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/patientAdmDis_ipdrpt.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			//String strWardCode = formBean.getStrWardCode();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "Patient Admission Discharge Report";
			//String strDeptCode = formBean.getStrDeptCode();
			//String strUnitCode = formBean.getStrUnitCode();
			String strConsultantCode = formBean.getStrConsultantName();
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
			
		//	boolean IsCRNoWise = true;
		//	boolean IsPatWise = true;
		//	boolean IsAgeWise = true;
			boolean IsSexWise = true;
		//	boolean IsDeptUnitWise = true;
			boolean IsConsultantWise = true;
		//	boolean IsWardWise = true;
			
			boolean footerVisible = true;
			
			
			
			if(formBean.getStrCase().equals("1")){
			//	IsCRNoWise = false;
				params.put("mode_Val", "1");
				params.put("cr_No", strCrNo);
				params.put("from_Age", "null");
				params.put("to_Age", "null");
				params.put("pat_Name", "null");
				params.put("consultant_Code", "null");
				params.put("gender_Code", "null");
			
			}else if (formBean.getStrCase().equals("2")) {
			//	IsPatWise = false;
				params.put("mode_Val", "2");
				params.put("pat_Name", strPatName);
				params.put("cr_No", "null");
				params.put("from_Age", "null");
				params.put("to_Age", "null");
				params.put("consultant_Code", "null");
				params.put("gender_Code", "null");
							
			}else if (formBean.getStrCase().equals("3")) {
			//	IsAgeWise = false;
				params.put("mode_Val", "3");
				params.put("from_Age", strAgeFrom);
				params.put("to_Age", strAgeTo);
				params.put("cr_No", "null");
				params.put("pat_Name", "null");
				params.put("consultant_Code", "null");
				params.put("gender_Code", "null");
							
			}else if (formBean.getStrCase().equals("4")) {
				IsSexWise = false;
				
				//System.out.println("inside age / sex ");
				
				params.put("mode_Val", "4");
				params.put("cr_No", "null");
				params.put("from_Age", "null");
				params.put("to_Age", "null");
				params.put("pat_Name", "null");
				params.put("consultant_Code", "null");
				if(strGenderCode.equals("0")){
					params.put("gender_Code", "null");
				}else{
					params.put("gender_Code", strGenderCode);
				}
						
			}else if (formBean.getStrCase().equals("5")) {
				IsConsultantWise = false;
				System.out.println("strConsultantCode--->"+strConsultantCode);
				params.put("mode_Val", "6");
				params.put("cr_No", "null");
				params.put("from_Age", "null");
				params.put("to_Age", "null");
				params.put("pat_Name", "null");
				params.put("gender_Code", "null");
				if(strConsultantCode.equals("0")){
					params.put("consultant_Code", "null");
				}else{
					params.put("consultant_Code", strConsultantCode);
				}
				
			}			

			
			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;
			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
			params.put("hosp_Code", strHospitalCode);
			params.put("IsSexWise", IsSexWise);
			params.put("IsConsultantWise", IsConsultantWise);
					

			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
