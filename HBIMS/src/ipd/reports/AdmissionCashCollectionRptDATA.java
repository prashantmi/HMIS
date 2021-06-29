package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdmissionCashCollectionRptDATA {
	
	
	public static void initReportPage(AdmissionCashCollectionRptFB formBean) {

		AdmissionCashCollectionRptBO bo = null;
		AdmissionCashCollectionRptVO voObj = null;

		HisUtil util = null;
		
		String strCatVal = "";
		String strmsgText = null;
		try {
			bo = new AdmissionCashCollectionRptBO();
			voObj = new AdmissionCashCollectionRptVO();
			formBean.setStrCategory("2");
			voObj.setStrCategory(formBean.getStrCategory());
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
					
			bo.getCategoryDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("IPD Reports", "AdmissionCashCollectionRptDATA");
			strCatVal = util.getOptionValue(voObj.getStrCategoryWs(), "0",
					"0^All", false);
			formBean.setStrCategoryValues(strCatVal);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "ipd.reports.AdmissionCashCollectionRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"AdmissionRegRpt->initReportPage()", strmsgText);
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


	public static void getCategoryDetails(AdmissionCashCollectionRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		AdmissionCashCollectionRptBO bo = null;

		AdmissionCashCollectionRptVO voObj = null;

		HisUtil util = null;
		String strmsgText =null;
		
		try {
			bo = new AdmissionCashCollectionRptBO();
			voObj = new AdmissionCashCollectionRptVO();

			String strCategory = request.getParameter("catType");

			if (strCategory == null)
				strCategory = "0";

			
			voObj.setStrCategory(strCategory);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			

			bo.getCategoryDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "AdmissionCashCollectionRptDATA");
			
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrCategoryWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrCategoryWs(), "0", "0^All",true);
			}
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
			
		} catch (Exception e) {
			strmsgText = "ipd.reports.AdmissionCashCollectionRptDATA.getCategoryDetails --> "
				+ e.getMessage();
			HisException eObj = new HisException("ipd",
				"AdmissionCashCollectionRpt->getCategoryDetails()", strmsgText);
				   formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
				  
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

	public static void showReport(AdmissionCashCollectionRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();
		String reportPath = "/ipd/reports/admCashCollection_ipdrpt.rptdesign";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strFromDate = formBean.getStrEffectiveFrom();
			String strToDate = formBean.getStrEffectiveTo();
			String strCategory = formBean.getStrCategory();
			String strReportName = "Admission Cash Collection";

     		String strUserRemarks = formBean.getStrUserRemarks();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	
			boolean footerVisible = true;
			boolean isPrimVisible = true;
			boolean isTreatVisible = true;

			if(formBean.getStrCase().equals("1")){
				isPrimVisible = false;
				params.put("report_Criteria", "Patient Category");
				if(!strCategory.equals("0")){
					params.put("prim_Cat", strCategory);
					params.put("treat_Cat", "null");
				}else{
					params.put("prim_Cat", "null");
					params.put("treat_Cat", "null");
				}
			}
			else if(formBean.getStrCase().equals("2")){
				isTreatVisible = false;
				params.put("report_Criteria", "Treatment Category");
				
				if(!strCategory.equals("0")){
					params.put("treat_Cat", strCategory);
					params.put("prim_Cat", "null");
				}else{
					params.put("prim_Cat", "null");
					params.put("treat_Cat", "null");
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
			params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
            params.put("to_Date", sdf.format(sdf.parse(strToDate)));
			params.put("hosp_Code", strHospitalCode);
			params.put("IsPrimCat", isPrimVisible);
			params.put("IsTreatCat", isTreatVisible);
						
	    	ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
