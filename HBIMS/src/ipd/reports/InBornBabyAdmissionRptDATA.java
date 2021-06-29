package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InBornBabyAdmissionRptDATA {
	
	public static void initReportPage(InBornBabyAdmissionRptFB formBean,HttpServletRequest request) {

		HisUtil util = new HisUtil("IPD Reports", "InBornBabyAdmissionRpt");
		String strHospNameValues = "";
		InBornBabyAdmissionRptBO bo = null;
		InBornBabyAdmissionRptVO voObj = null;
		
		try {
			
			bo = new InBornBabyAdmissionRptBO();
			voObj = new InBornBabyAdmissionRptVO();
			
			voObj.setStrSeatId(formBean.getStrSeatId());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			
			bo.getHospitalName(voObj);
			
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0",
					"0^Select Value", false);	

			formBean.setStrHospNameValues(strHospNameValues);

			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrCurrentTime(util.getASDate("HH:mm"));
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		
	}

	public static void showReport(InBornBabyAdmissionRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();
		HisUtil util = null;
		String reportPath = "/ipd/reports/inbornBabyAdmissionListing_ipdrpt.rptdesign";
		// String moduleName = "IPD";
		// String fileName = "dateReport";
		String reportFormat = "html";

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			util = new HisUtil("IPD Reports", "InBornBabyAdmissionRptDATA");
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

			reportFormat = formBean.getStrReportFormat();
			

			String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strFromTime = formBean.getStrFromTime();
			String strToTime = formBean.getStrToTime();
			String currTime = formBean.getStrCurrentTime();
			String strEffectiveFrom = formBean.getStrEffectiveFrom()+" "+((strFromTime==null||strFromTime==""||formBean.getStrIsTimeRequired().equals("0"))?"00:00":strFromTime);
			String strEffectiveTo = formBean.getStrEffectiveTo()+" "+((strToTime==null||strToTime==""||formBean.getStrIsTimeRequired().equals("0"))?currTime:strToTime);
			String strCurrDate = formBean.getStrCurrentDate();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			String strReportName = "In Born Baby Admission Listing";
			String strUserRemarks = formBean.getStrUserRemarks();
			
			
			
			boolean footerVisible = true;
			boolean IsBetDate = true;
			
			if (formBean.getStrIsFooter().equals("1")) {

				footerVisible = false;

			}

			reportFormat = formBean.getStrReportFormat();

			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", strHospitalCode);
			
			if(formBean.getStrCase().equals("2")){
				IsBetDate = false;
				params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
				params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
			}else{
				params.put("from_Date", strCurrDate);
				params.put("to_Date", strCurrDate);
			}
			
			params.put("IsBetDate", IsBetDate);
		
			ts.displayReport(request, response, reportPath, reportFormat,
					params,strHospitalCode);

		} catch (Exception e) {
			//e.printStackTrace();
				
		}
	}
}
