 package ipd.reports;

import java.util.HashMap;
import java.util.Map;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WardRoomBedRptDATA {
	
	public static void initReportPage(WardRoomBedRptFB formBean) {

		WardRoomBedRptBO bo = null;

		WardRoomBedRptVO voObj = null;

		HisUtil util = null;
		String strDeptVal = "";
		String strWardTypeVal = "";
		String strmsgText = null;
		

		try {
			bo = new WardRoomBedRptBO();
			voObj = new WardRoomBedRptVO();

			voObj.setStrHospitalCode("101");
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());

			bo.getDepartmentDetails(voObj);
		
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("IPD Reports", "WardRoomBedRptDATA");
			strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					"0^All", false);
		
			formBean.setStrDeptValues(strDeptVal);
			
			strWardTypeVal = util.getOptionValue(voObj.getStrWardTypeWs(), "0",
					"0^All", false);
		
			formBean.setStrWardTypeValues(strWardTypeVal);
			

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "ipd.reports.WardRoomBedRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"WardRoomBedRpt->initReportPage()", strmsgText);
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

	public static void getUnitDetails(WardRoomBedRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		WardRoomBedRptBO bo = null;
		WardRoomBedRptVO voObj = null;
		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new WardRoomBedRptBO();
			voObj = new WardRoomBedRptVO();

			String strDeptCode = request.getParameter("deptCode");
			if (strDeptCode == null)
				strDeptCode = "0";

			voObj.setStrDeptCode(strDeptCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getUnitDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("IPD Reports", "WardRoomBedRptDATA");
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
			e.printStackTrace();
			strmsgText = "ipd.reports.WardRoomBedRptDATA.getUnitDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"WardRoomBedRpt->getUnitDetails()", strmsgText);
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

	public static void getWardDetails(WardRoomBedRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		WardRoomBedRptBO bo = null;
		WardRoomBedRptVO voObj = null;
		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new WardRoomBedRptBO();
			voObj = new WardRoomBedRptVO();

			String strDeptUnitCode = request.getParameter("deptunitCode");

			if (strDeptUnitCode == null)
				strDeptUnitCode = "0";

			voObj.setStrUnitCode(strDeptUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getWardDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			util = new HisUtil("IPD Reports", "WardRoomBedRptDATA");
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
			e.printStackTrace();
			strmsgText = "ipd.reports.WardRoomBedRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"WardRoomBedRpt->getWardDetails()", strmsgText);
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

	
	public static void showReport(WardRoomBedRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/wardroombed_ipdrpt.rptdesign";
		String reportFormat = "html";
		
		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();
			
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strReportName = "Ward Room Bed Report";
			String strUserRemarks = formBean.getStrUserRemarks();

			boolean footerVisible = true;
			
			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;
			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
		
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
