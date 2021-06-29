package billing.reports;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

public class IncomeCorpStatementRptDATA {

	public static void initReportPage(IncomeCorpStatementRptFB formBean) {

		IncomeCorpStatementRptBO bo = null;

		IncomeCorpStatementRptVO voObj = null;

		HisUtil util = null;
		String strHospSerVal = "";
		String strmsgText = null;

		try {
			
			bo = new IncomeCorpStatementRptBO();
			voObj = new IncomeCorpStatementRptVO();
			
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			
			bo.getHospSerDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","IncomeCorpStatementRptDATA");
			strHospSerVal = util.getOptionValue(voObj.getStrHospSerWs(), "0",
					"0^All", false);
			formBean.setStrHospSerValues(strHospSerVal);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "billing.reports.IncomeCorpStatementRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"IncomeCorpStatementRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void showReport(IncomeCorpStatementRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				
				Map<String, Object> params = new HashMap<String, Object>();
			try {
					
					String strReportName = "Income Comparaive Statement";								
					String strHospitalCode = formBean.getStrHospitalCode();
					String strReportId = formBean.getStrReportId();
					String strUserRemarks = formBean.getStrUserRemarks();
					String strHospSerName = formBean.getStrHospSerName();
					String strFromYear = formBean.getStrFromYear();
					String strToYear = formBean.getStrToYear();
					String strFromMonthYear = formBean.getStrFromMonthYear();
					String strToMonthYear = formBean.getStrToMonthYear();
					String strFromMonth = formBean.getStrFromMonth();
					String strToMonth = formBean.getStrToMonth();
					
					reportFormat = formBean.getStrReportFormat();
					boolean footerVisible = true;
					boolean IsReportGenerate = true;
					boolean IsGraphGenerate = true;
	
					if (formBean.getStrIsFooter().equals("1")) {
						footerVisible = false;
					}
				
					if(formBean.getStrCase().equals("1")){
						String reportPath = "/billing/reports/incomeCorpStatement1_billrpt.rptdesign";
						
						if(formBean.getStrGraph().equals("1")){
							
							IsGraphGenerate = false;
							if(strHospSerName.equals("0")){
								params.put("chargeType_Name", "All");
							}else if(strHospSerName.equals("1")){
								params.put("chargeType_Name", "Opd");
							}else if(strHospSerName.equals("2")){
								params.put("chargeType_Name", "Ipd");
							}else if(strHospSerName.equals("3")){
								params.put("chargeType_Name", "Emergency");
							}
							params.put("IsGraphGenerate", IsGraphGenerate);
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							params.put("from_Year", strFromYear);
							params.put("to_Year", strToYear);
							params.put("hosp_Code", strHospitalCode);
							params.put("from_Mon", "null");
							params.put("to_Mon", "null");
							if(strHospSerName.equals("0")){
								params.put("charge_Type_Id", "null");
							}else{
								params.put("charge_Type_Id", strHospSerName);
							}
						}else {
						
							IsReportGenerate = false;
							if(strHospSerName.equals("0")){
								params.put("chargeType_Name", "All");
							}else if(strHospSerName.equals("1")){
								params.put("chargeType_Name", "Opd");
							}else if(strHospSerName.equals("2")){
								params.put("chargeType_Name", "Ipd");
							}else if(strHospSerName.equals("3")){
								params.put("chargeType_Name", "Emergency");
							}
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							params.put("hosp_Code", strHospitalCode);
							params.put("from_Year", strFromYear);
							params.put("to_Year", strToYear);
							params.put("IsReportGenerate", IsReportGenerate);
							params.put("from_Mon", "null");
							params.put("to_Mon", "null");
							if(strHospSerName.equals("0")){
								params.put("charge_Type_Id", "null");
							}else{
								params.put("charge_Type_Id", strHospSerName);
							}
							
						}	
													
							ts.displayReport(request, response, reportPath, reportFormat,
									params,strHospitalCode);
						
					}else if(formBean.getStrCase().equals("2")){
						String reportPath = "/billing/reports/incomeCorpStatement2_billrpt.rptdesign";
						
						if(formBean.getStrGraph().equals("1")){
							
							IsGraphGenerate = false;
							if(strHospSerName.equals("0")){
								params.put("chargeType_Name", "All");
							}else if(strHospSerName.equals("1")){
								params.put("chargeType_Name", "Opd");
							}else if(strHospSerName.equals("2")){
								params.put("chargeType_Name", "Ipd");
							}else if(strHospSerName.equals("3")){
								params.put("chargeType_Name", "Emergency");
							}
							params.put("IsGraphGenerate", IsGraphGenerate);
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							params.put("from_Year", strFromMonthYear);
							params.put("to_Year", strToMonthYear);
							params.put("from_Mon", strFromMonth);
							params.put("to_Mon", strToMonth);
							params.put("hosp_Code", strHospitalCode);
							if(strHospSerName.equals("0")){
								params.put("charge_Type_Id", "null");
							}else{
								params.put("charge_Type_Id", strHospSerName);
							}
						}else{
							
							IsReportGenerate = false;
							if(strHospSerName.equals("0")){
								params.put("chargeType_Name", "All");
							}else if(strHospSerName.equals("1")){
								params.put("chargeType_Name", "Opd");
							}else if(strHospSerName.equals("2")){
								params.put("chargeType_Name", "Ipd");
							}else if(strHospSerName.equals("3")){
								params.put("chargeType_Name", "Emergency");
							}
							params.put("hosp_Code", strHospitalCode);
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							params.put("IsReportGenerate", IsReportGenerate);
						params.put("from_Year", strFromMonthYear);
							params.put("to_Year", strToMonthYear);
							params.put("from_Mon", strFromMonth);
							params.put("to_Mon", strToMonth);
							if(strHospSerName.equals("0")){
								params.put("charge_Type_Id", "null");
							}else{
								params.put("charge_Type_Id", strHospSerName);
							}
							
						}	
							
							ts.displayReport(request, response, reportPath, reportFormat,
									params,strHospitalCode);
					
						}
					
				} catch (Exception e) {
					strmsgText = "billing.reports.IncomeCorpStatementRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"IncomeCorpStatementRptDATA->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;				
					}
			}
	}
