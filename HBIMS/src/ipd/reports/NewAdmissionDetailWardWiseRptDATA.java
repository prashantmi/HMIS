
	package ipd.reports;
	/*
	 * author : Debashis sardar
	 * 
	 * New Admission Detail Ward Wise DATA
	 * 
	 * date: 02/01/2012
	 */
	import java.text.SimpleDateFormat;
	import java.util.HashMap;
	import java.util.Map;
	import hisglobal.ReportUtil;
	import hisglobal.exceptions.HisException;
	import hisglobal.utility.HisUtil;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	public class NewAdmissionDetailWardWiseRptDATA {

		public static void initReportPage(NewAdmissionDetailWardWiseRptFB formBean) {

			NewAdmissionDetailWardWiseRptBO bo = null;
			NewAdmissionDetailWardWiseRptVO voObj = null;

			HisUtil util = null;
			
			String strWard = "";
			String strmsgText = null;
			try {
				bo = new NewAdmissionDetailWardWiseRptBO();
				voObj = new NewAdmissionDetailWardWiseRptVO();
				voObj.setStrHospitalCode(formBean.getStrHospitalCode());
				
				voObj.setStrSeatId(formBean.getStrSeatId());
				
				bo.getWardDetails(voObj);
				
				if (voObj.getStrMsgType().equals("1")) {

					throw new Exception(voObj.getStrMsgString());

				}
				util = new HisUtil("IPD Reports", "NewAdmissionDetailWardWiseRptDATA");
				
				strWard = util.getOptionValue(voObj.getStrWardWs(), "0",
						"0^Select Value", false);
				
			
				formBean.setStrWardList(strWard);
				
				formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

			} catch (Exception e) {
				strmsgText = "ipd.reports.NewAdmissionDetailWardWiseRptDATA.initReportPage --> "
						+ e.getMessage();
				HisException eObj = new HisException("ipd",
						"NewAdmissionDetailWardWiseRpt->initReportPage()", strmsgText);
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

		

		 public static void showReport(NewAdmissionDetailWardWiseRptFB formBean,HttpServletRequest request,
				 					HttpServletResponse response) {
		
			 ReportUtil ts = new ReportUtil();
		
			 	String reportPath = "/ipd/reports/NewAdmissionDetailWardWise_ipdrpt.rptdesign";
		 		String reportFormat = "html";
		
		 		Map<String, Object> params = new HashMap<String, Object>();
		 try {
		
			 reportFormat = formBean.getStrReportFormat();
				
		
			 String strWardCode=formBean.getStrWardCode();
			 String strWardName=formBean.getStrWardSelected();
			 String strHospitalCode = formBean.getStrHospitalCode();
			 String strReportId = formBean.getStrReportId();
			 String strEffectiveFrom = formBean.getStrEffectiveFrom();
			 String strEffectiveTo = formBean.getStrEffectiveTo();
			 String strReportName = "Admitted Patient in the Ward :";
		
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
			
			 params.put("ward_code", strWardCode);
			 params.put("hosp_Code", strHospitalCode);
			 params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			 params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
			 
			 params.put("ward_name", strWardName);
			 
	

			 ts.displayReport(request, response, reportPath, reportFormat,
					 params,strHospitalCode);
		
		 } catch (Exception e) {
		
			 e.printStackTrace();
					
		 	 }
		 }
	}




