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

public class ConsolidatedFeeCollRptDATA {
	
	public static void initReportPage(ConsolidatedFeeCollRptFB formBean,HttpServletRequest request){

		ConsolidatedFeeCollRptBO bo = null;
		ConsolidatedFeeCollRptVO voObj = null;

		HisUtil util = null;
		String strHospSerVal = "";
		String strmsgText = null;
		 String strHospNameValues="";
		try {
		
			bo = new ConsolidatedFeeCollRptBO();
			voObj = new ConsolidatedFeeCollRptVO();
			
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			 voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			 voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			 bo.getHospitalName(voObj);			
			bo.getHospSerDetails(voObj);
			bo.getCounterDetails(voObj);
			bo.getClerkDetails(voObj);
			bo.getTreatCatDetails(voObj);
			bo.getGroupName(voObj);
			bo.getDeptName(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","ConsolidatedFeeCollRptDATA");
			
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0","0^All", false);	

			formBean.setStrHospNameValues(strHospNameValues);
			strHospSerVal = util.getOptionValue(voObj.getStrHospSerWs(), "0",
					"0^All", false);
		
			formBean.setStrHospSerValues(strHospSerVal);

			
			String strCounterVal = util.getOptionValue(voObj.getStrCounterWs(), "0",
					"0^All", false);
			formBean.setStrCounterValues(strCounterVal);
			
			
			String strGroupVal = util.getOptionValue(voObj.getGrpNameWS(), "0",
				"0^All", false);

			formBean.setStrGroupNameVal(strGroupVal);
			formBean.setStrGroupTariffVal(strGroupVal);
			
			String strTreatCatVal = util.getOptionValue(voObj.getStrTreatCatWs(), "0",
					"0^All", false);
			
			formBean.setStrTreatCatValues(strTreatCatVal);
			 
			String strCleark = util.getOptionValue(voObj.getStrClerkWs(), "0",
					"0^All", false);
			
			formBean.setStrFeeClerkValues(strCleark);
			
			String strDept = util.getOptionValue(voObj.getStrDeptWs(), "0",
					"0^All", false);
			
			formBean.setStrDeptValues(strDept);
			
				
			Calendar cal = Calendar.getInstance();
			
			cal.setTime( new SimpleDateFormat("dd-MMM-yyyy").parse(util.getASDate("dd-MMM-yyyy")) );
			
			cal.add(Calendar.DATE, -1);
						
			formBean.setStrCurrentDate(  new SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime()) );



		} catch (Exception e) {
			strmsgText = "billing.reports.ConsolidatedFeeCollRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"ConsolidatedFeeCollRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator!");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
	 
	
	public static void getTariffName(ConsolidatedFeeCollRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		ConsolidatedFeeCollRptBO bo = null;
		ConsolidatedFeeCollRptVO vo = null;

		HisUtil util = null;
		String strmsgText = null;
		String strGroupId=null;
		String strTariffName = "";
		
		try {
			
			bo = new ConsolidatedFeeCollRptBO();
			vo = new ConsolidatedFeeCollRptVO();
			
			//vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			strGroupId = request.getParameter("groupId");
			
			vo.setStrGroupTariffId(strGroupId);
			
			bo.getTariffName(vo);
			
			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","ConsolidatedFeeCollRptDATA");
			strTariffName = util.getOptionValue(vo.getTariffNameWS(), "0",
					"0^All", false);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strTariffName);
			
			//System.out.println("strTariffName-->"+strTariffName);
			

		} catch (Exception e) {
			strmsgText = "billing.reports.ConsolidatedFeeCollRptDATA.getTariffName --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"ConsolidatedFeeCollRpt->getTariffName()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
			util = null;
		}

	}
	
	public static void showReport(ConsolidatedFeeCollRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				String strMultiHospitalCode="";
				Map<String, Object> params = new HashMap<String, Object>();
			try {
					
					String strReportName = "Consolidated Fee Collection Report";								
					//String strHospitalCode = formBean.getStrHospitalCode();
					String strHospitalCode=formBean.getStrHospitalCodes();
					if(strHospitalCode.contains(","))
					{
						strMultiHospitalCode="100";
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
					String strGroupName = formBean.getStrGroupId();
					String strTariffName = formBean.getStrTariffId();
					String strDeptId = formBean.getStrDeptName();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					reportFormat = formBean.getStrReportFormat();
					boolean footerVisible = true;
	
					if (formBean.getStrIsFooter().equals("1")) {
						footerVisible = false;
					}
					
					if(formBean.getStrCase().equals("1")){
						
						String reportPath = "/billing/reports/consolidatedFeeCollection1New_billrpt.rptdesign";
						
						if(strCounterName.equals("0")){
							params.put("counter_Id", "0");
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
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("clerk_Id", "0");
						params.put("treat_Cat", "0");
						params.put("deptId", "0");
						if(strChargeTypeId.equals("0")){
							params.put("charge_Type_Id", "0");
						}else{
							params.put("charge_Type_Id", strChargeTypeId);
						}
												
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strMultiHospitalCode);
						
					}else if(formBean.getStrCase().equals("2")){
					
							String reportPath = "/billing/reports/consolidatedFeeCollection2_billrpt.rptdesign";
							
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
							params.put("hosp_Code", strHospitalCode);
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							
							params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
							params.put("to_Date", sdf.format(sdf.parse(strToDate)));
							params.put("treat_Cat", "0");
							params.put("counter_Id", "0");
							params.put("deptId", "0");
							if(strChargeTypeId.equals("0")){
								params.put("charge_Type_Id", "0");
							}else{
								params.put("charge_Type_Id", strChargeTypeId);
							}
							
							ts.displayReport(request, response, reportPath, reportFormat,
									params,strMultiHospitalCode);
						
					
					}else if(formBean.getStrCase().equals("3")){
						
						String reportPath = "/billing/reports/consolidatedFeeCollection3New_billrpt.rptdesign";
					
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
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("counter_Id", "0");
						params.put("clerk_Id", "0");
						params.put("deptId", "0");
						if(strChargeTypeId.equals("0")){
							params.put("charge_Type_Id", "0");
						}else{
							params.put("charge_Type_Id", strChargeTypeId);
						}
						
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strMultiHospitalCode);
						
					}else if(formBean.getStrCase().equals("4")){
						
						String reportPath = "/billing/reports/consolidatedFeeCollection4New_billrpt.rptdesign";
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
						params.put("treat_Cat", "0");
						params.put("counter_Id", "0");
						params.put("clerk_Id", "0");
						params.put("deptId", "0");
						if(strChargeTypeId.equals("0")){
							params.put("charge_Type_Id", "0");
						}else{
							params.put("charge_Type_Id", strChargeTypeId);
						}
						
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strMultiHospitalCode);
					}
					//group wise
                        else if(formBean.getStrCase().equals("5")){
						
						String reportPath = "/billing/reports/consolidatedFeeCollection5New_billrpt.rptdesign";
						
						 
							params.put("groupid", strGroupName);
			 
						
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
						params.put("treat_Cat", "0");
						params.put("counter_Id", "0");
						params.put("clerk_Id", "0");
						params.put("deptId", "0");
						if(strChargeTypeId.equals("0")){
							params.put("charge_Type_Id", "0");
						}else{
							params.put("charge_Type_Id", strChargeTypeId);
						}
						
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strMultiHospitalCode);
					}
			
                        else if(formBean.getStrCase().equals("6")){
    						
    						String reportPath = "/billing/reports/consolidatedFeeCollection6New_billrpt.rptdesign";
    						
    					 
    						strGroupName = formBean.getStrGroupTariffId();
    						
    							params.put("tariffId", strTariffName);
    					 
    						
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
    						params.put("groupid", strGroupName);
    						params.put("treat_Cat", "0");
    						params.put("counter_Id", "0");
    						params.put("clerk_Id", "0");
    						params.put("deptId", "0");
    						if(strChargeTypeId.equals("0")){
    							params.put("charge_Type_Id", "0");
    						}else{
    							params.put("charge_Type_Id", strChargeTypeId);
    						}
    						
    						ts.displayReport(request, response, reportPath, reportFormat,
    								params,strMultiHospitalCode);
    					}
					
                        else if(formBean.getStrCase().equals("7")){
    						
    						String reportPath = "/billing/reports/consolidatedFeeCollection7_billrpt.rptdesign";
    						
    					 
    						strGroupName = formBean.getStrGroupTariffId();
    						
    							params.put("tariffId", strTariffName);
    							params.put("deptId", strDeptId);
    					 
    							
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
    						params.put("groupid", strGroupName);
    						params.put("treat_Cat", "0");
    						params.put("counter_Id", "0");
    						params.put("clerk_Id", "0");
    						if(strChargeTypeId.equals("0")){
    							params.put("charge_Type_Id", "0");
    						}else{
    							params.put("charge_Type_Id", strChargeTypeId);
    						}
    						
    						ts.displayReport(request, response, reportPath, reportFormat,
    								params,strMultiHospitalCode);
    					}
					
				} catch (Exception e) {
					strmsgText = "billing.reports.ConsolidatedFeeCollRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"ConsolidatedFeeCollRpt->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;		
					}
			}
	}
