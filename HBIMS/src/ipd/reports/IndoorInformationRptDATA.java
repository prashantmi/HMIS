package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndoorInformationRptDATA {


	public static void initReportPage(IndoorInformationRptFB formBean,HttpServletRequest request) {

		IndoorInformationRptBO bo =null;
		IndoorInformationRptVO voObj = null;
		HisUtil util = null;
		String strDeptVal = "";
	/*	String strUnitVal = "";
		String strWardVal = "";*/
		
		String strmsgText = null;
		
		try {
			 bo = new IndoorInformationRptBO();
			 voObj = new IndoorInformationRptVO();

			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

			bo.getDepartmentDetails(voObj);
			/*bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/
			
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("IPD Reports", "IndoorInformationRptDATA");
			strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					"0^All", false);
		/*	strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);
			strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);*/

			formBean.setStrDeptValues(strDeptVal);
			/*formBean.setStrWardValues(strWardVal);
			formBean.setStrUnitValues(strUnitVal);*/
			
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "ipd.reports.IndoorInformationRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"IndoorInformationRpt->initReportPage()", strmsgText);
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

	public static void getUnitWardDetails(IndoorInformationRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		IndoorInformationRptBO bo = null;
		IndoorInformationRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new IndoorInformationRptBO();
			voObj = new IndoorInformationRptVO();

			String strDeptCode = request.getParameter("deptCode");
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("IPD Reports", "IndoorInformationRptDATA");
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
			System.out.println(temp+"^"+temp1);

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp+"^"+temp1);
			

		} catch (Exception e) {
			strmsgText = "ipd.reports.IndoorInformationRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"IndoorInformationRpt->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(IndoorInformationRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		IndoorInformationRptBO bo = null;
		IndoorInformationRptVO voObj = null;
		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new IndoorInformationRptBO();
			voObj = new IndoorInformationRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");
			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("IPD Reports", "IndoorInformationRptDATA");
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
			strmsgText = "ipd.reports.IndoorInformationRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"IndoorInformationRptDATA->getWardDetails()", strmsgText);
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
	public static void getdeptComboDetails(IndoorInformationRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		IndoorInformationRptBO bo = null;

		IndoorInformationRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new IndoorInformationRptBO();
			voObj = new IndoorInformationRptVO();

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
	public static void showReport(IndoorInformationRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/indoorInformation_ipdrpt.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strUnitCode = formBean.getStrUnitCode();
			String strFromDate = formBean.getStrEffectiveFrom();
			String strToDate = formBean.getStrEffectiveTo();
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "Indoor Admission,Deaths & Discharge Information";
			String strUserRemarks = formBean.getStrUserRemarks();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

			boolean rooterVisible = true;

			if (formBean.getStrIsFooter().equals("1")) {
				rooterVisible = false;
			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", rooterVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
			params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
			params.put("to_Date", sdf.format(sdf.parse(strToDate)));
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
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			//e.printStackTrace();

		}
	}
}
