package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BedOccupancyRptDATA {

	public static void initReportPage(BedOccupancyRptFB formBean) {

		BedOccupancyRptBO bo = null;
		String strmsgText = null;
		BedOccupancyRptVO voObj = null; 

		HisUtil util = new HisUtil("IPD Reports", "BedOccupancyRptDATA");
		String strDeptVal = "";
		/*String strWardVal = "";
		String strUnitVal = "";*/

		try {
			bo = new BedOccupancyRptBO();
			voObj= new BedOccupancyRptVO();
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			bo.getDepartmentDetails(voObj);
		/*	bo.getUnitDetails(voObj);
			bo.getWardDetails(voObj);*/

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}

			strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					"0^Select Value", false);
		/*	strWardVal = util.getOptionValue(voObj.getStrWardWs(), "0",
					"0^All", false);
			strUnitVal = util.getOptionValue(voObj.getStrUnitWs(), "0",
					"0^All", false);*/

			formBean.setStrDeptValues(strDeptVal);
		/*	formBean.setStrUnitValues(strUnitVal);
			formBean.setStrWardValues(strWardVal);
			*/
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "ipd.reports.BedOccupancyRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"BedOccupancyRpt->initReportPage()", strmsgText);
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

	
	public static void getUnitWardDetails(BedOccupancyRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		    BedOccupancyRptBO bo = null;
			String strmsgText = null;
			BedOccupancyRptVO voObj = null;
			
			HisUtil util = null;
			
			try {
			
				bo = new BedOccupancyRptBO();
				voObj = new BedOccupancyRptVO();
				
				String strDeptCode = request.getParameter("deptCode");
				if (strDeptCode == null)
					strDeptCode = "0";
				
				voObj.setStrDeptCode(strDeptCode);
				voObj.setStrHospitalCode(formBean.getStrHospitalCode());
				
				bo.getUnitWardDetails(voObj);
				
				if (voObj.getStrMsgType().equals("1")) {
					throw new Exception(voObj.getStrMsgString());
				
				}
				util = new HisUtil("IPD Reports", "BedOccupancyRptDATA");
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

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp+"^"+temp1);
			
			} catch (Exception e) {
				strmsgText = "ipd.reports.BedOccupancyRptDATA.getUnitDetails --> "
						+ e.getMessage();
				HisException eObj = new HisException("ipd",
						"BedOccupancyRptDATA->getUnitDetails()", strmsgText);
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

	
	public static void getWardDetails(BedOccupancyRptFB formBean,HttpServletRequest request,
							HttpServletResponse response) {

		BedOccupancyRptBO bo = null;
		String strmsgText = null;
		BedOccupancyRptVO voObj = null;

		HisUtil util = new HisUtil("IPD Reports", "BedOccupancyRptDATA");
		try {
			bo = new BedOccupancyRptBO();
			voObj = new BedOccupancyRptVO();
			
			String strUnitCode = request.getParameter("deptunitCode");
			if (strUnitCode == null)
				strUnitCode = "0";
			
			voObj.setStrUnitCode(strUnitCode);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			
			bo.getWardDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			
			}
			util = new HisUtil("IPD Reports", "BedOccupancyRptDATA");
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
			strmsgText = "ipd.reports.BedOccupancyRptDATA.getWardDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"BedOccupancyRpt->getWardDetails()", strmsgText);
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

	public static void showReport(BedOccupancyRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportPath = "/ipd/reports/bedOccupancy_ipdrpt.rptdesign";
		// String moduleName = "IPD";
		// String fileName = "dateReport";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {

			reportFormat = formBean.getStrReportFormat();

			String strUnitCode = formBean.getStrUnitCode();
			String strWardCode = formBean.getStrWardCode();
			String strDeptCode = formBean.getStrDeptCode();
			String strHospitalCode = formBean.getStrHospitalCode();
			String strFromDate = formBean.getStrEffectiveFrom();
			String strToDate = formBean.getStrEffectiveTo();
			String strReportId = formBean.getStrReportId();
			String strReportName = "Bed Occupancy Report For Department";
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String strUserRemarks = formBean.getStrUserRemarks();

			boolean IsWardWise = true;
			boolean IsDeptWise = true;
			boolean rooterVisible = true;
			boolean isUnitReq=true;
			if(formBean.getStrCase().equals("1")){
				
				IsDeptWise = false;
				params.put("mode_Val", "1");
				params.put("ward_Code", "null");
				params.put("unit_Code", "null");
				
				if(strDeptCode.equals("0")){
					params.put("dept_Code", "null");
				}else{
					params.put("dept_Code", strDeptCode);
				}
				
			}else if(formBean.getStrCase().equals("2")){
				
				IsWardWise = false;
				params.put("mode_Val", "2");
				
				if(strWardCode.equals("0")){
					params.put("ward_Code", "null");
				}else{
					params.put("ward_Code", strWardCode);
				}
				if(strDeptCode.equals("0")){
					params.put("dept_Code", "null");
				}else{
					params.put("dept_Code", strDeptCode);
				}
				if(strDeptCode.equals("0")){
					params.put("unit_Code", "null");
				}else{
					params.put("unit_Code", strUnitCode);
				}
			}
			
			


			if(formBean.getStrUnitReq().equals("1"))
						{
							isUnitReq=false;
						}




			if (formBean.getStrIsFooter().equals("1")) {

				rooterVisible = false;

			}

			reportFormat = formBean.getStrReportFormat();
			
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", rooterVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("IsWardWise", IsWardWise);
			params.put("IsDeptWise", IsDeptWise);
			params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
            params.put("to_Date", sdf.format(sdf.parse(strToDate)));
			params.put("hosp_Code", strHospitalCode);
			params.put("IsUnitReq", isUnitReq);			
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			//e.printStackTrace();

		}
	}

}
