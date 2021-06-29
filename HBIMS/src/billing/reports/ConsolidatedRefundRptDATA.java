package billing.reports;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billing.BillConfigUtil;

public class ConsolidatedRefundRptDATA {
	
	public static void initReportPage(ConsolidatedRefundRptFB formBean,HttpServletRequest request){

		ConsolidatedRefundRptBO bo = null;
		ConsolidatedRefundRptVO voObj = null;

		HisUtil util = null;
		String strHospSerVal = "";
		String strmsgText = null;
		String strHospNameValues="";

		try {
		
			bo = new ConsolidatedRefundRptBO();
			voObj = new ConsolidatedRefundRptVO();
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			 bo.getHospitalName(voObj);					
			bo.getHospSerDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","ConsolidatedRefundRptDATA");
			
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0","0^All", false);	

			formBean.setStrHospNameValues(strHospNameValues);
			
			strHospSerVal = util.getOptionValue(voObj.getStrHospSerWs(), "0",
					"0^All", false);

			formBean.setStrHospSerValues(strHospSerVal);
		 
			Calendar cal = Calendar.getInstance();
			
			cal.setTime( new SimpleDateFormat("dd-MMM-yyyy").parse(util.getASDate("dd-MMM-yyyy")) );
			
			cal.add(Calendar.DATE, -1);
						
			formBean.setStrCurrentDate(  new SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime()) );

			
		} catch (Exception e) {
			strmsgText = "billing.reports.ConsolidatedRefundRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"ConsolidatedRefundRptDA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	public static void geCounterDetails(ConsolidatedRefundRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		ConsolidatedRefundRptBO bo = null;

		ConsolidatedRefundRptVO voObj = null;

		HisUtil util = null;
		String strCounterVal = "";
		String strmsgText = null;

		try {
		
			bo = new ConsolidatedRefundRptBO();
			voObj = new ConsolidatedRefundRptVO();
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrModuleId(formBean.getStrModuleId());
			
			bo.getCounterDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","ConsolidatedRefundRptDATA");
			strCounterVal = util.getOptionValue(voObj.getStrCounterWs(), "0",
					"0^All", false);

			formBean.setStrCounterValues(strCounterVal);
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTime( new SimpleDateFormat("dd-MMM-yyyy").parse(util.getASDate("dd-MMM-yyyy")) );
			
			cal.add(Calendar.DATE, -1);
						
			formBean.setStrCurrentDate(  new SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime()) );


		} catch (Exception e) {
			strmsgText = "billing.reports.ConsolidatedRefundRptDATA.geCounterDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"ConsolidatedRefundRpt->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	
	public static void getClerkDetails(ConsolidatedRefundRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		ConsolidatedRefundRptBO bo = null;

		ConsolidatedRefundRptVO voObj = null;

		HisUtil util = null;
		String strClerkVal = "";
		String strmsgText = null;

		try {
			
			bo = new ConsolidatedRefundRptBO();
			voObj = new ConsolidatedRefundRptVO();
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
						
			bo.getClerkDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","ConsolidatedRefundRptDATA");
			strClerkVal = util.getOptionValue(voObj.getStrClerkWs(), "0",
					"0^All", false);

			formBean.setStrFeeClerkValues(strClerkVal);
	
		} catch (Exception e) {
			strmsgText = "billing.reports.ConsolidatedRefundRptDATA.getClerkDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"ConsolidatedRefundRpt->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void getTreatCatDetails(ConsolidatedRefundRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		ConsolidatedRefundRptBO bo = null;
		ConsolidatedRefundRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		String strTreatCatVal = "";
		String strHospNameValues="";
		try {
			
			bo = new ConsolidatedRefundRptBO();
			voObj = new ConsolidatedRefundRptVO();
			
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrCatType(formBean.getStrCatType());
			
			bo.getTreatCatDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","ConsolidatedRefundRptDATA");
			
			
			
			strTreatCatVal = util.getOptionValue(voObj.getStrTreatCatWs(), "0",
					"0^All", false);
			
			formBean.setStrTreatCatValues(strTreatCatVal);
			

		} catch (Exception e) {
			strmsgText = "billing.reports.ConsolidatedRefundRptDATA.getGroupDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"ConsolidatedRefundRpt->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	public static void showReport(ConsolidatedRefundRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				String strMultiHospitalCode="";
				Map<String, Object> params = new HashMap<String, Object>();
			try {
					
					String strReportName = "Consolidated Refund Report";								
					//String strHospitalCode = formBean.getStrHospitalCode();
					//String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();
					String strHospitalCode=formBean.getStrHospitalCodes();
					if(strHospitalCode.contains(","))
					{
						strMultiHospitalCode=BillConfigUtil.SUPER_HOSPITAL_CODE;
					}
					else
					{
						strMultiHospitalCode=strHospitalCode;
					}
					
					
					String strReportId = formBean.getStrReportId();
					String strUserRemarks = formBean.getStrUserRemarks();
					String strCounterName = formBean.getStrCounterName();
					String strTreatCat = formBean.getStrTreatCatName();
					String strFeeClerkName = formBean.getStrFeeClerkName();
					String strFromDate = formBean.getStrEffectiveFrom();
					String strToDate = formBean.getStrEffectiveTo();
					String strChargeTypeId = formBean.getStrHospSerName();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					reportFormat = formBean.getStrReportFormat();
					boolean footerVisible = true;
	
					if (formBean.getStrIsFooter().equals("1")) {
						footerVisible = false;
						
					}
					
					if(formBean.getStrCase().equals("1")){
						
						String reportPath = "/billing/reports/consolidatedRefundRpt1_billrpt.jsp.rptdesign";
						
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						if(strCounterName.equals("0")){
							params.put("counter_Id", "null");
						}else{
							params.put("counter_Id", strCounterName);
						}
						if(strChargeTypeId.equals("0")){
							params.put("chargeType_Name", "All");
						}else if(strChargeTypeId.equals("1")){
							params.put("chargeType_Name", "Opd");
						}else if(strChargeTypeId.equals("2")){
							params.put("chargeType_Name", "Ipd");
						}else if(strChargeTypeId.equals("3")){
							params.put("chargeType_Name", "Emergency");
						}
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("clerk_Id", "null");
						params.put("treat_Cat", "null");
						if(strChargeTypeId.equals("0")){
							params.put("charge_Type_Id", "null");
						}else{
							params.put("charge_Type_Id", strChargeTypeId);
						}
												
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strMultiHospitalCode);
						
					}else if(formBean.getStrCase().equals("2")){
					
							String reportPath = "/billing/reports/consolidatedRefundRpt2_billrpt.jsp.rptdesign";
							
							params.put("hosp_Code", strHospitalCode);
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							if(strFeeClerkName.equals("0")){
								params.put("clerk_Id", "null");
							}else{
								params.put("clerk_Id", strFeeClerkName);
							}
							if(strChargeTypeId.equals("0")){
								params.put("chargeType_Name", "All");
							}else if(strChargeTypeId.equals("1")){
								params.put("chargeType_Name", "Opd");
							}else if(strChargeTypeId.equals("2")){
								params.put("chargeType_Name", "Ipd");
							}else if(strChargeTypeId.equals("3")){
								params.put("chargeType_Name", "Emergency");
							}
							params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
							params.put("to_Date", sdf.format(sdf.parse(strToDate)));
							params.put("treat_Cat", "null");
							params.put("counter_Id", "null");
							if(strChargeTypeId.equals("0")){
								params.put("charge_Type_Id", "null");
							}else{
								params.put("charge_Type_Id", strChargeTypeId);
							}
							
							ts.displayReport(request, response, reportPath, reportFormat,
									params,strMultiHospitalCode);
						
					
					}else if(formBean.getStrCase().equals("3")){
						
						String reportPath = "/billing/reports/consolidatedRefundRpt3_billrpt.jsp.rptdesign";
					
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						if(strTreatCat.equals("0")){
							params.put("treat_Cat", "null");
						}else{
							params.put("treat_Cat", strTreatCat);
						}
						if(strChargeTypeId.equals("0")){
							params.put("chargeType_Name", "All");
						}else if(strChargeTypeId.equals("1")){
							params.put("chargeType_Name", "Opd");
						}else if(strChargeTypeId.equals("2")){
							params.put("chargeType_Name", "Ipd");
						}else if(strChargeTypeId.equals("3")){
							params.put("chargeType_Name", "Emergency");
						}
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("counter_Id", "0");
						params.put("clerk_Id", "0");
						if(strChargeTypeId.equals("0")){
							params.put("charge_Type_Id", "0");
						}else{
							params.put("charge_Type_Id", strChargeTypeId);
						}
						
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strMultiHospitalCode);
						
					}else if(formBean.getStrCase().equals("4")){
						
						String reportPath = "/billing/reports/consolidatedRefundRpt4New_billrpt.jsp.rptdesign";
						if(strChargeTypeId.equals("0")){
							params.put("chargeType_Name", "All");
						}else if(strChargeTypeId.equals("1")){
							params.put("chargeType_Name", "Opd");
						}else if(strChargeTypeId.equals("2")){
							params.put("chargeType_Name", "Ipd");
						}else if(strChargeTypeId.equals("3")){
							params.put("chargeType_Name", "Emergency");
						}
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("treat_Cat", "null");
						params.put("counter_Id", "null");
						params.put("clerk_Id", "null");
						if(strChargeTypeId.equals("0")){
							params.put("charge_Type_Id", "null");
						}else{
							params.put("charge_Type_Id", strChargeTypeId);
						}
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strMultiHospitalCode);
					}
					
				} catch (Exception e) {
					strmsgText = "billing.reports.ConsolidatedRefundRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"ConsolidatedRefundRpt->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;					
					}
			}
	}
