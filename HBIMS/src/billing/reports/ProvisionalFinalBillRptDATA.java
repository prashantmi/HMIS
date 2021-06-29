package billing.reports;

import java.util.HashMap;
import java.util.Map;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProvisionalFinalBillRptDATA {
	
	
	public static void initReportPage(ProvisionalFinalBillRptFB formBean, HttpServletRequest req) {

		ProvisionalFinalBillRptBO bo =  null;

		ProvisionalFinalBillRptVO voObj = null;

		HisUtil util = new HisUtil("Billing Reports", "DueDetailsRptDATA");
		String strmsgText = null;
		try {
			bo = new ProvisionalFinalBillRptBO();
			voObj =  new ProvisionalFinalBillRptVO();
			 
			voObj.setStrHospitalCode("108");

			bo.getDeptDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}

		String	strDeptValues = util.getOptionValue(voObj.getStrDeptWs(),
					"0", "0^Select Value", false);
		
		formBean.setStrDeptValues(strDeptValues);
		
						
		} catch (Exception e) {
			strmsgText = "billing.reports.DueDetailsRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DueDetailsRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}

	public static void getWARDCMB(ProvisionalFinalBillRptFB formBean, HttpServletRequest req ,HttpServletResponse res) 
	{

		ProvisionalFinalBillRptBO bo =  null;

		ProvisionalFinalBillRptVO voObj = null;
		HisUtil util = new HisUtil("Billing Reports", "DueDetailsRptDATA");
		
		String strmsgText = null;
		try {
			bo = new ProvisionalFinalBillRptBO();
			voObj =  new ProvisionalFinalBillRptVO();

		
			voObj.setStrHospitalCode("108");


			bo.getWARDCMB(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}

		String	strWardValues = util.getOptionValue(voObj.getStrWardWs(),
					"0", "0^Select Value", false);
		
		formBean.setStrWardValues(strWardValues);
		
				} 
		  catch (Exception e) {
		 
			strmsgText = "billing.reports.DueDetailsRptDATA.getUNITCMB --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DueDetailsRptDATA->getUNITCMB", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	
	 public static void showReport(ProvisionalFinalBillRptFB formBean,
				HttpServletRequest request, HttpServletResponse response) {

					ReportUtil ts = new ReportUtil();
					String reportFormat = "html";
					String strmsgText = "";
					Map<String, Object> params = new HashMap<String, Object>();
				try {
						
						String strReportName = "Provisional Final Adjustment Bill";								
						String strHospitalCode = "108";
						String strReportId = formBean.getStrReportId();
						String strAccountNo = formBean.getStrPatAccNo();
						String strDeptCode = formBean.getStrDeptCode();
						String strWardCode = formBean.getStrWardCode();
						reportFormat = formBean.getStrReportFormat();
				
						if(formBean.getStrBillTypeCombo().equals("1")){
							
							String reportPath = "/billing/reports/provisionalFinalAdjustmentBill1_billrpt.rptdesign";
							
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("hosp_Code", strHospitalCode);
							
							params.put("acc_No", strAccountNo);
							params.put("bill_No", "null");
							params.put("dept_Code", strDeptCode);
							params.put("ward_Code", strWardCode);
						
											
							ts.displayReport(request, response, reportPath, reportFormat,
									params,strHospitalCode);
							
						}else if(formBean.getStrBillTypeCombo().equals("2")){
						
								String reportPath = "/billing/reports/provisionalFinalAdjustmentBill2_billrpt.rptdesign";
								
								params.put("hosp_Code", strHospitalCode);
								params.put("report_id", strReportId);
								params.put("report_Name", strReportName);
								
								params.put("acc_No", strAccountNo);
								params.put("bill_No", "null");
								params.put("dept_Code", strDeptCode);
								params.put("ward_Code", strWardCode);
								
								ts.displayReport(request, response, reportPath, reportFormat,
									params,strHospitalCode);
						}
						
					} catch (Exception e) {
						strmsgText = e.getMessage();
						HisException eObj = new HisException("Billing", 
									"IpdBillManagementTransDATA->showReport()", strmsgText);
						formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "]," +
											"Contact System Administrator! ");
						formBean.setStrMsgType("1");
						eObj = null;
					}
				}
	   
	   

}
