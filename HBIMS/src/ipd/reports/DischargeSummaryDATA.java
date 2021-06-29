package ipd.reports;

import hisglobal.ReportUtil;
import hisglobal.utility.HisUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DischargeSummaryDATA {

	private static final String pathFileName = "hisglobal.hisconfig.hisReport";

	public static void showReport(DischargeSummaryFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ResourceBundle rsBundle = ResourceBundle.getBundle(pathFileName);
		String path = HisUtil.getParameterFromHisPathXML("IPD_PATH");

		ReportUtil report = null;

		try {

			report = new ReportUtil();
			
			String strAdmNo = formBean.getStrAdmNo();
			String strHospCode = formBean.getStrHospCode();

			String strFileName = path + File.separator + strAdmNo + ".pdf";

			File f = new File(strFileName);

			if (f.exists()) {

				report.displayReportFromFile(response, "IPD", strAdmNo, "pdf");

			} else {

		
				
				
				
				String strDischargeFooter = rsBundle.getString("DISCHARGE_FOOTER");
				String strReportAdvice = rsBundle.getString("REPORT_ADVICE");
				String reportPath = "/ipd/reports/dischargeSummary.rptdesign";
				String strDischargeImage = HisUtil.getParameterFromHisPathXML("DISCHARGE_IMAGE");

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("adm_No", strAdmNo);
				params.put("hosp_Code", strHospCode);
				params.put("report_Name", "Discharge Summary");
				params.put("report_Advice", strReportAdvice);
				params.put("discharge_Footer", strDischargeFooter);
				params.put("discharge_Image", strDischargeImage);
				
				report.saveReport(request, response, reportPath, "IPD",
						strAdmNo, "pdf", params, true);

			}
		} catch (Exception e) {

			//e.printStackTrace();
		}

	}

}
