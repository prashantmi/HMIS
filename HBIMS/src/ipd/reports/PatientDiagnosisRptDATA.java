package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatientDiagnosisRptDATA {

	public static void initReportPage(PatientDiagnosisRptFB formBean) {

		PatientDiagnosisRptBO  bo = null;

		PatientDiagnosisRptVO voObj= null;

		HisUtil util = new HisUtil("IPD Reports", "PatientDiagnosisRptDATA");
		String strDeptVal = "";
		String strmsgText = null;
		
		try {
			bo = new PatientDiagnosisRptBO();
			voObj = new PatientDiagnosisRptVO();

			voObj.setStrHospitalCode(formBean.getStrHospCode());
			// voObj.setStrDeptCode(formBean.getStrDeptCode());

			bo.getDepartmentDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}

			strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					"0^ALL", false);

			formBean.setStrDeptValues(strDeptVal);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "ipd.reports.PatientDiagnosisRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientDiagnosisRpt->initReportPage()", strmsgText);
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

	public static void getUnitDetails(PatientDiagnosisRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PatientDiagnosisRptBO bo = null;

		PatientDiagnosisRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new PatientDiagnosisRptBO();
			voObj = new PatientDiagnosisRptVO();

			String strDeptCode = request.getParameter("deptCode");

			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospCode());

			bo.getUnitDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "PatientDiagnosisRptDATA");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrUnitWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrUnitWs(), "0", "0^All",
						true);

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			strmsgText = "ipd.reports.PatientDiagnosisRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientDiagnosisRpt->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(PatientDiagnosisRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PatientDiagnosisRptBO bo = null;

		PatientDiagnosisRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new PatientDiagnosisRptBO();
			voObj = new PatientDiagnosisRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "PatientDiagnosisRptDATA");
			String temp = "<option value='0'>Select Value</option>";

			//System.out.println("Ward Ws size : " + voObj.getStrWardWs().size());

			if (voObj.getStrWardWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrWardWs(), "0", "0^All",
						true);

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			strmsgText = "ipd.reports.PatientDiagnosisRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientDiagnosisRpt->getWardDetails()", strmsgText);
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

	public static void getRoomDetails(PatientDiagnosisRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PatientDiagnosisRptBO bo = null;

		PatientDiagnosisRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try {

			bo = new PatientDiagnosisRptBO();
			voObj = new PatientDiagnosisRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");
			String strWardCode = request.getParameter("wardCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			if (strWardCode == null)
				strWardCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrWardCode(strWardCode);
			voObj.setStrHospitalCode(formBean.getStrHospCode());

			bo.getRoomDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "PatientDiagnosisRptDATA");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrRoomWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrRoomWs(), "0", "0^All",
						true);

			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			strmsgText = "ipd.reports.PatientDiagnosisRptDATA.getRoomDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientDiagnosisRpt->getRoomDetails()", strmsgText);
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

	public static void showReport(PatientDiagnosisRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/currentlyAdmittedPatient_rpt.rptdesign";
		// String moduleName = "IPD";
		// String fileName = "dateReport";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String strRoomCode = formBean.getStrRoomNo();
			String strWardCode = "109";
			String strHospitalCode = formBean.getStrHospCode();
			String strReportId = "100000";
			String strReportName = "<center>Ward Wise Report</center>";

			String strUserRemarks = formBean.getStrUserRemarks();

			boolean visible = true;
			boolean rooterVisible = true;

			if (formBean.getStrIsRoomWise().equals("1")) {

				visible = false;

			}

			if (formBean.getStrIsFooter().equals("1")) {

				rooterVisible = false;

			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", rooterVisible);
			params.put("report_user_Remarks", strUserRemarks);

			params.put("hospitalCode", strHospitalCode);
			params.put("roomNo", strRoomCode);
			params.put("wardCode", strWardCode);
			params.put("isRoomWise", visible);

			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			//e.printStackTrace();

		}
	}
}
