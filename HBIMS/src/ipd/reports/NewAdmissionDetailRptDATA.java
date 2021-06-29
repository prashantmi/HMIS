
	package ipd.reports;
	/*
	 * author : Debashis sardar
	 * 
	 * New Admission Detail User Wise DATA
	 * 
	 * date: 30/12/2011
	 */
	import java.text.SimpleDateFormat;
	import java.util.HashMap;
	import java.util.Map;
	import hisglobal.ReportUtil;
	import hisglobal.exceptions.HisException;
	import hisglobal.utility.HisUtil;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	public class NewAdmissionDetailRptDATA {

		public static void initReportPage(NewAdmissionDetailRptFB formBean) {

			NewAdmissionDetailRptBO bo = null;
			NewAdmissionDetailRptVO voObj = null;

			HisUtil util = null;
			
			String strClerkName = "";
			String strmsgText = null;
			try {
				bo = new NewAdmissionDetailRptBO();
				voObj = new NewAdmissionDetailRptVO();
				voObj.setStrHospitalCode(formBean.getStrHospitalCode());
				
				voObj.setStrSeatId(formBean.getStrSeatId());
				
				bo.getClerkDetails(voObj);
				
				if (voObj.getStrMsgType().equals("1")) {

					throw new Exception(voObj.getStrMsgString());

				}
				util = new HisUtil("IPD Reports", "NewAdmissionDetailRptDATA");
				
				strClerkName = util.getOptionValue(voObj.getStrClerkWs(), "0",
						"0^Select Value", false);
				
			
				formBean.setStrClerkList(strClerkName);
				
				formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

			} catch (Exception e) {
				strmsgText = "ipd.reports.NewAdmissionDetailRptDATA.initReportPage --> "
						+ e.getMessage();
				HisException eObj = new HisException("ipd",
						"NewAdmissionDetailRpt->initReportPage()", strmsgText);
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

		

		 public static void showReport(NewAdmissionDetailRptFB formBean,HttpServletRequest request,
				 					HttpServletResponse response) {
		
			 ReportUtil ts = new ReportUtil();
		
			 	String reportPath = "/ipd/reports/NewAdmissionDetail_ipdrpt.rptdesign";
		 		String reportFormat = "html";
		
		 		Map<String, Object> params = new HashMap<String, Object>();
		 try {
		
			 reportFormat = formBean.getStrReportFormat();
				
		
			 String strUserId=formBean.getStrUserId();
			 String strUserName=formBean.getStrClerkSelected();
			 String strHospitalCode = formBean.getStrHospitalCode();
			 String strReportId = formBean.getStrReportId();
			 String strEffectiveFrom = formBean.getStrEffectiveFrom();
			 String strEffectiveTo = formBean.getStrEffectiveTo();
			 String strReportName = "Patient Admission By User :";
		
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
			 params.put("seat_Id", strUserId);
			 params.put("hosp_Code", strHospitalCode);
			 params.put("from_Date", sdf.format(sdf.parse(strEffectiveFrom)));
			 params.put("to_Date", sdf.format(sdf.parse(strEffectiveTo)));
			 params.put("user_name", strUserName);
			 
	

			 ts.displayReport(request, response, reportPath, reportFormat,
					 params,strHospitalCode);
		
		 } catch (Exception e) {
		
			 e.printStackTrace();
					
		 	 }
		 }
	}


