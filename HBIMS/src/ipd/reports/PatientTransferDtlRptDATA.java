package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatientTransferDtlRptDATA {
	public static void initReportPage(PatientTransferDtlRptFB formBean,HttpServletRequest request) {
		PatientTransferDtlRptBO bo = null;
		PatientTransferDtlRptVO voObj = null;
		String strWardVal = "";
		HisUtil util = null;
		String strmsgText = null;
		formBean.setStrCase("2");
		formBean.setStrTransferCase("10");

		try {
			bo = new PatientTransferDtlRptBO();
			voObj = new PatientTransferDtlRptVO();
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			bo.getWardAll(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
			util = new HisUtil("IPD Reports", "PatientTransferDtlRptDATA");
			strWardVal=util.getOptionValue(voObj.getStrWardAllWs(), "0","0^All", false);
			
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));
			formBean.setStrWardAllValues(strWardVal);
		}
		catch (Exception e) {

			strmsgText = "ipd.reports.PatientTransferDtlRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"PatientTransferDtlRpt->initReportPage()", strmsgText);
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
	
	/**
	 * 
	 * @param formBean
	 * @param request
	 * @param response
	 */
	public static void showReport(PatientTransferDtlRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();
		boolean footerVisible=false;

		String reportPath = "";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			String strFromDate = formBean.getStrEffectiveFrom();
			String strToDate = formBean.getStrEffectiveTo();
			reportFormat = formBean.getStrReportFormat();
			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			@SuppressWarnings("unused")
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			String strUserRemarks = formBean.getStrUserRemarks();
			reportFormat = formBean.getStrReportFormat();
			params.put("report_formattype", reportFormat);
			
			if(formBean.getStrTransferType().equals("1"))//Trans In
			{
				reportPath = "/ipd/reports/patTransferDtlWardWise_ipdrpt.rptdesign";
				params.put("transfer_fromward", formBean.getStrTransferToWardCodeAll());//Transfer From-Second Row Combo
				params.put("transfer_toward", formBean.getStrWardCodeAll());//Transfer In First Row Combo
				params.put("report_Name", "Transfer In Details");
			}
			else//Trans Out
			{
				reportPath = "/ipd/reports/patTransferDtlWardWiseTransOut_ipdrpt.rptdesign";
				params.put("transfer_fromward", formBean.getStrWardCodeAll());
				params.put("transfer_toward", formBean.getStrTransferToWardCodeAll());
				params.put("report_Name", "Transfer Out  Details");
			}
				params.put("report_Criteria", "Ward Wise");
				params.put("from_Date", strFromDate);
				params.put("to_Date", strToDate);
				params.put("report_id", strReportId);
			    params.put("report_Footer_Visible", footerVisible);
			    params.put("report_user_Remarks", strUserRemarks);
			    params.put("hosp_Code", strHospitalCode);
			
			    ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);

		} catch (Exception e) {

              e.printStackTrace();

		}
	}
}
