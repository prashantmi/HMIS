package billing.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billing.BillConfigUtil;


public class ConsolidatedDiscountDtlRptDATA {
	
	public static void initReportPage(ConsolidatedDiscountDtlRptFB formBean,HttpServletRequest request){

		ConsolidatedDiscountDtlRptBO bo = null;
		ConsolidatedDiscountDtlRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
        String strHospNameValues="";
     
		try {
		
			bo = new ConsolidatedDiscountDtlRptBO();
			voObj = new ConsolidatedDiscountDtlRptVO();
			
			 voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			//voObj.setStrHospitalCode1((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			//voObj.setStrHospitalCode();
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			bo.getHospitalName(voObj);			
			bo.getHospSerDetails(voObj);
			bo.getTreatCatDetails(voObj);	
			bo.getGroupName(voObj);

					
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","ConsolidatedDiscountDtlRptDATA");
			
			
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0","0^All", false);	

			formBean.setStrHospNameValues(strHospNameValues);
			
			
			
			String strHospSerVal = util.getOptionValue(voObj.getStrHospSerWs(), "0","0^All", false);
		
			formBean.setStrHospSerValues(strHospSerVal);
			
			String strTreatCatVal = util.getOptionValue(voObj.getStrTreatCatWs(), "0","0^All", false);
			
			formBean.setStrTreatCatValues(strTreatCatVal);
			
			String strGroupVal = util.getOptionValue(voObj.getGrpNameWS(), "0","0^All", false);

			formBean.setStrGroupNameVal(strGroupVal);
			formBean.setStrGroupTariffVal(strGroupVal);
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTime( new SimpleDateFormat("dd-MMM-yyyy").parse(util.getASDate("dd-MMM-yyyy")) );
			
			cal.add(Calendar.DATE, -1);
						
			formBean.setStrCurrentDate(  new SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime()) );

		} catch (Exception e) {
			strmsgText = "billing.reports.ConsolidatedDiscountDtlRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"ConsolidatedDiscountDtlRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator!");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	 
	
	public static void showReport(ConsolidatedDiscountDtlRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		
		

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				String strHosp="";

				Map<String, Object> params = new HashMap<String, Object>();
			try {
					
					String strReportName = "Consolidated Discount Detail Report";	
					String strMultiHospitalCode="";
				
					
					
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
					//String strHospitalCode = formBean.getStrHospitalCode();
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
						
						String reportPath = "/billing/reports/consolidatedDiscountDtl2New_billrpt.rptdesign";
						
						if(strTreatCat.equals("0")){
							params.put("treat_Cat", "0");
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
					
							String reportPath = "/billing/reports/consolidatedDiscountDtl1New_billrpt.rptdesign";
							
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
					}
					else if(formBean.getStrCase().equals("3")){
						
						String reportPath = "/billing/reports/consolidatedDiscountDtl3New_billrpt.rptdesign";
						
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
					    else if(formBean.getStrCase().equals("4")){
						
						String reportPath = "/billing/reports/consolidatedDiscountDtl4New_billrpt.rptdesign";
						
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
						params.put("hosp_Code", strHospitalCode);
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("groupId", strGroupName);
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
				
					strmsgText = "billing.reports.ConsolidatedDiscountDtlRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"ConsolidatedDiscountDtlRptDATA->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;		
					}
			}
	
 
	 
	
	public static void getTariffName(ConsolidatedDiscountDtlRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		ConsolidatedDiscountDtlRptBO bo = null;
		ConsolidatedDiscountDtlRptVO vo = null;

		HisUtil util = null;
		String strmsgText = null;
		String strGroupId=null;
		String strTariffName = "";
		
		try {
			
			bo = new ConsolidatedDiscountDtlRptBO();
			vo = new ConsolidatedDiscountDtlRptVO();
			
			vo.setStrHospitalCode(formBean.getStrHospitalCodes());
			
			strGroupId = request.getParameter("groupId");
			
			vo.setStrGroupTariffId(strGroupId);
			
			bo.getTariffName(vo);
			
			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","ConsolidatedDiscountDtlRptDATA");
			strTariffName = util.getOptionValue(vo.getTariffNameWS(), "0",
					"0^All", false);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strTariffName);
			
			//System.out.println("strTariffName-->"+strTariffName);
			

		} catch (Exception e) {
			strmsgText = "billing.reports.ConsolidatedDiscountDtlRptDATA.getTariffName --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"ConsolidatedDiscountDtlRpt->getTariffName()", strmsgText);
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
