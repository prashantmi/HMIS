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

public class ConsolidatedExpDtlRptDATA {
	
	public static void initReportPage(ConsolidatedExpDtlRptFB formBean,HttpServletRequest request){

		ConsolidatedExpDtlRptBO bo = null;
		ConsolidatedExpDtlRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		String strHospNameValues="";

		try {
		
			bo = new ConsolidatedExpDtlRptBO();
			voObj = new ConsolidatedExpDtlRptVO();
			
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			 voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			 voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			bo.getHospitalName(voObj);	
			bo.getHospSerDetails(voObj);
			bo.getTreatCatDetails(voObj);
			bo.getGroupName(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","ConsolidatedExpDtlRptDATA");
			
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0","0^All", false);	

			formBean.setStrHospNameValues(strHospNameValues);
			
			
			String strHospSerVal = util.getOptionValue(voObj.getStrHospSerWs(), "0",
					"0^All", false);
		
			formBean.setStrHospSerValues(strHospSerVal);

			String strTreatCatVal = util.getOptionValue(voObj.getStrTreatCatWs(), "0",
					"0^All", false);
			
			formBean.setStrTreatCatValues(strTreatCatVal);
			
			
			String strGroupVal = util.getOptionValue(voObj.getGrpNameWS(), "0",	"0^All", false);

			formBean.setStrGroupNameVal(strGroupVal);
			
			formBean.setStrGroupTariffVal(strGroupVal);

			Calendar cal = Calendar.getInstance();
			
			cal.setTime( new SimpleDateFormat("dd-MMM-yyyy").parse(util.getASDate("dd-MMM-yyyy")) );
			
			cal.add(Calendar.DATE, -1);
						
			formBean.setStrCurrentDate(  new SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime()) );

			

		} catch (Exception e) {
			strmsgText = "billing.reports.ConsolidatedExpDtlRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"ConsolidatedExpDtlRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator!");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
		 
	public static void showReport(ConsolidatedExpDtlRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				String strMultiHospitalCode="";
				Map<String, Object> params = new HashMap<String, Object>();
			try {
					
					String strReportName = "Consolidated Expenditure Detail Report";								
					//String strHospitalCode = formBean.getStrHospitalCode();
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
					String strTreatCat = formBean.getStrTreatCatName();
					String strFromDate = formBean.getStrEffectiveFrom();
					String strToDate = formBean.getStrEffectiveTo();
					String strChargeTypeId = formBean.getStrHospSerName();
					String strGroupName = formBean.getStrGroupId();
					String strTariffName = formBean.getStrTariffId();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					reportFormat = formBean.getStrReportFormat();
					
					boolean footerVisible = true;
	
					if (formBean.getStrIsFooter().equals("1")) {
						footerVisible = false;
					}
					
					if(formBean.getStrCase().equals("1")){
						
						String reportPath = "/billing/reports/consolidatedExpenditureDtl1New_billrpt.rptdesign";
						
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
						params.put("report_Criteria", "Treatment Category Wise");
						if(strChargeTypeId.equals("0")){
							params.put("chargeType_Id", "0");
						}else{
							params.put("chargeType_Id", strChargeTypeId);
						}
												
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strMultiHospitalCode);
						
					}else if(formBean.getStrCase().equals("2")){
					
							String reportPath = "/billing/reports/consolidatedExpenditureDtl2New_billrpt.rptdesign";
							
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
							params.put("report_Criteria", "Date Wise");
							if(strChargeTypeId.equals("0")){
								params.put("chargeType_Id", "0");
							}else{
								params.put("chargeType_Id", strChargeTypeId);
							}
							ts.displayReport(request, response, reportPath, reportFormat,
									params,strMultiHospitalCode);
							
					}else if(formBean.getStrCase().equals("3")){
						
						String reportPath = "/billing/reports/consolidatedExpenditureDtl3_billrpt.rptdesign";
						
						
						params.put("hospCode", strHospitalCode);
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
						params.put("toDate", sdf.format(sdf.parse(strToDate)));
						params.put("chargeType", "0");
						params.put("treatCat", "0");
						
						params.put("report_Criteria", "Expenditure Details CR No. Wise");
					
						
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strMultiHospitalCode);
				}else if(formBean.getStrCase().equals("4")){
					
					String reportPath = "/billing/reports/consolidatedExpenditureDtl4New_billrpt.rptdesign";
					
					params.put("groupId", strGroupName);
					
					params.put("tariffid", "0");
					
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
					params.put("report_Criteria", "Group Wise");
					if(strChargeTypeId.equals("0")){
						params.put("chargeType_Id", "0");
					}else{
						params.put("chargeType_Id", strChargeTypeId);
					}
					ts.displayReport(request, response, reportPath, reportFormat,
							params,strMultiHospitalCode);
				}
					else if(formBean.getStrCase().equals("5")){
					
					String reportPath = "/billing/reports/consolidatedExpenditureDtl5New_billrpt.rptdesign";
					
					strGroupName = formBean.getStrGroupTariffId();
					
					params.put("groupId", strGroupName);
					
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
					params.put("hosp_Code", strHospitalCode);
					params.put("report_id", strReportId);
					params.put("report_Name", strReportName);
					params.put("report_Footer_Visible", footerVisible);
					params.put("report_user_Remarks", strUserRemarks);
					params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
					params.put("to_Date", sdf.format(sdf.parse(strToDate)));
					params.put("treat_Cat", "0");
					params.put("report_Criteria", "Tariff Wise");
					if(strChargeTypeId.equals("0")){
						params.put("chargeType_Id", "0");
					}else{
						params.put("chargeType_Id", strChargeTypeId);
					}
					ts.displayReport(request, response, reportPath, reportFormat,
							params,strMultiHospitalCode);
				}
					
				} catch (Exception e) {
				
					strmsgText = "billing.reports.ConsolidatedExpDtlRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"ConsolidatedExpDtlRptDATA->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;		
					}
			}
	
	 
	
	public static void getTariffName(ConsolidatedExpDtlRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		ConsolidatedExpDtlRptBO bo = null;
		ConsolidatedExpDtlRptVO vo = null;

		HisUtil util = null;
		String strmsgText = null;
		String strGroupId=null;
		String strTariffName = "";
		
		try {
			
			bo = new ConsolidatedExpDtlRptBO();
			vo = new ConsolidatedExpDtlRptVO();
			
			//vo.setStrHospitalCode(formBean.getStrHospitalCodes());
		vo.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			strGroupId = request.getParameter("groupId");
			
			vo.setStrGroupTariffId(strGroupId);
			
			bo.getTariffName(vo);
			
			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","ConsolidatedExpDtlRptDATA");
			strTariffName = util.getOptionValue(vo.getTariffNameWS(), "0",
					"0^All", false);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strTariffName);
			
			//System.out.println("strTariffName-->"+strTariffName);
			

		} catch (Exception e) {
			strmsgText = "billing.reports.ConsolidatedExpDtlRptDATA.getTariffName --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"ConsolidatedExpDtlRpt->getTariffName()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
			util = null;
		}

	}


}
