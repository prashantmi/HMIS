package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author: Vivek Aggarwal
 * @Date: 12-July-2011
 * @Module:	ADT
 */
public class CompleteWardCensusDetailRptDATA {

	/**
	 * To initialize Report Page
	 * 
	 * @param form	the	CompleteWardCensusDetailRptFB
	 * @param request	the javax.servlet.http.HttpServletRequest
	 */
	public static void initReportPage(CompleteWardCensusDetailRptFB formBean,HttpServletRequest request) {

		CompleteWardCensusDetailRptBO bo = null;
		CompleteWardCensusDetailRptVO voObj = null;
		HisUtil util = null;
		String strDeptVal = "";
		String strMsgText = null;
		String strWardVal="";

		try {
			bo = new CompleteWardCensusDetailRptBO();
			voObj = new CompleteWardCensusDetailRptVO();
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			voObj.setStrSeatId(formBean.getStrSeatId());
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrDeptCode(formBean.getStrDeptCode());
			voObj.setStrUnitCode(formBean.getStrUnitCode());
			bo.getWardAll(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
			util = new HisUtil("IPD Reports", "CompleteWardCensusDetailRptDATA");
			strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0","0^Select Value", false);

			formBean.setStrDeptValues(strDeptVal);
			formBean.setStrWardAllValues(strWardVal);	
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strMsgText = "ipd.reports.CompleteWardCensusDetailRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"CompleteWardCensusDetailRptDATA->initReportPage()", strMsgText);
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
	 * To Show Report
	 * 
	 * @param form	the	CompleteWardCensusDetailRptFB
	 * @param request	the javax.servlet.http.HttpServletRequest
	 * @param response  the the javax.servlet.http.HttpServletResponse
	 */
	public static void showReport(CompleteWardCensusDetailRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		

		ReportUtil ts = new ReportUtil();
		String reportPath = "/ipd/reports/completeWardCensusDetail_ipdrpt.rptdesign";
		String reportFormat = "html";
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			reportFormat = formBean.getStrReportFormat();
			String strWardCode = formBean.getStrWardCodeAll();
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strDate = formBean.getStrDate();
			String strReportName = "Ward Census Report";
			String strWardName = formBean.getStrWardName();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
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
			params.put("from_Date", sdf.format(sdf.parse(strDate)));
			params.put("to_Date", sdf.format(sdf.parse(strDate)));
			params.put("treatCat", "null");
			params.put("roomNo", "null");
			params.put("hosp_Code", strHospitalCode);
			params.put("wardName", strWardName);
			
			//Report 1
			if(formBean.getStrAdmittedDetailRequired()==null)
				formBean.setStrAdmittedDetailRequired("0");
			
			if(formBean.getStrAdmittedDetailRequired().equals("1"))
			{
				params.put("admittedPatientListingWise", true);
			}else{
				params.put("admittedPatientListingWise", false);
			}
			
			//Report 2
			if(formBean.getStrReceivedTransferFromOtherWard()==null)
				formBean.setStrReceivedTransferFromOtherWard("0");
			
			if(formBean.getStrReceivedTransferFromOtherWard().equals("1"))
			{
				params.put("receivedByTransferFromOtherWard", true);
			}else {
				params.put("receivedByTransferFromOtherWard", false);
			}
			
			//Report 3
			if(formBean.getStrDischargedByTransferToOtherWard()==null)
				formBean.setStrDischargedByTransferToOtherWard("0");
			if(formBean.getStrDischargedByTransferToOtherWard().equals("1"))
			{
				params.put("dischargedByTransferToOtherWard", true);
			}else {
				params.put("dischargedByTransferToOtherWard", false);
			}
			
			//Report 4
			if(formBean.getStrStillBirth()==null)
				formBean.setStrStillBirth("0");
			if(formBean.getStrStillBirth().equals("1"))
			{
				params.put("stillBirth", true);
			}else {
				params.put("stillBirth", false);
			}
			
			//Report 5
			if(formBean.getStrDischargeDetail()==null)
				formBean.setStrDischargeDetail("0");
			if(formBean.getStrDischargeDetail().equals("1"))
			{
				params.put("discharge", true);
			}else {
				params.put("discharge", false);
			}
			
			//Report 6
			if(formBean.getStrDiedDetail()==null)
				formBean.setStrDiedDetail("0");
			if(formBean.getStrDiedDetail().equals("1"))
			{
				params.put("died", true);
			}else {
				params.put("died", false);
			}
			
			//Report 7
			if(formBean.getStrSummary()==null)
				formBean.setStrSummary("0");
			if(formBean.getStrSummary().equals("1"))
			{
				params.put("summaryOfTheDay", true);
			}else {
				params.put("summaryOfTheDay", false);
			}
			//End of seven report checkboxes 
			if(strWardCode.equals("0"))
			{
				params.put("ward_Code", "null");
			}
			else
			{
				params.put("ward_Code", strWardCode);
			}
			ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}
