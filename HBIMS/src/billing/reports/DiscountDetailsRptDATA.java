package billing.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DiscountDetailsRptDATA {

	public static void initReportPage(DiscountDetailsRptFB formBean,HttpServletRequest request) {

		DiscountDetailsRptBO bo =  null;

		DiscountDetailsRptVO voObj = null;

		HisUtil util = new HisUtil("Billing", "DiscountDetailsRptDATA");
		String strHospSerVal = "";
		String strHospNameValues="";
		String strmsgText = null;
		try {
			bo = new DiscountDetailsRptBO();
			voObj =  new DiscountDetailsRptVO();
			 
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			  voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )? request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			  voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				
				bo.getHospitalName(voObj);
				bo.getHospSerDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}

			//strHospSerVal = util.getOptionValue(voObj.getStrHospServiceWs(),
					//"1", "", false);
			

			System.out.println("hospcode"+request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			  strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), request.getSession().getAttribute("HOSPITAL_CODE").toString(),"0^All", false);
			  

				
				strHospSerVal = util.getOptionValue(voObj.getStrHospServiceWs(), "0","0^ALL", false);
				formBean.setStrHospNameValues(strHospNameValues);
				formBean.setStrHospSerValues(strHospSerVal);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "billing.reports.DiscountDetailsRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DiscountDetailsRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	public static void getHospitalServiceDetails(DiscountDetailsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		DiscountDetailsRptBO bo = null;

		DiscountDetailsRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new DiscountDetailsRptBO();
			voObj = new DiscountDetailsRptVO();
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			//String strhospCode=request.getParameter("hospCode").toString();

			
		//	if (strhospCode == null)
				//strhospCode = "0";
		
		//	voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

		
			
			
			
			bo.getHospSerDetails(voObj);
			
			util = new HisUtil("Billing Reports", "ChargeDetailRptDATA");
			
			String temp1= "<option value='0'>Select Value</option>";
			
			if (voObj.getStrHospServiceWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrHospServiceWs(), "0", "0^Select Value",true);
			}
			else {
				temp1 ="<option value='0'>Select Value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);
		}
		
		catch (Exception e) 
		{
			strmsgText = "ipd.reports.DiscountDetailsRptDATA.getHospitalServiceDetails --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","DiscountDetails->getHospitalServiceDetails()", 

strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	
	public static void getGroupDetails(DiscountDetailsRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		DiscountDetailsRptBO bo = null;
		DiscountDetailsRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		
		try {
			
			bo = new DiscountDetailsRptBO();
			voObj = new DiscountDetailsRptVO();
			
			String strChargeTypeId = request.getParameter("chargeTypeId");
			if(strChargeTypeId == null)
				strChargeTypeId = "0";
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospService(strChargeTypeId);
			bo.getGroupDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("Billing Reports", "Discount Detail Report");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrGroupWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrGroupWs(), "0", "0^All",
						true);

			}else {
				temp = "<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
			
			
		} catch (Exception e) {
			strmsgText = "billing.reports.DiscountDetailsRptDATA.getGroupDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DiscountDetailsRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}

	public static void getTariffDetails(DiscountDetailsRptFB formBean,HttpServletRequest request,
								HttpServletResponse response) {

		DiscountDetailsRptBO bo = null;

		DiscountDetailsRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		try {
			bo = new DiscountDetailsRptBO();
			voObj = new DiscountDetailsRptVO();
			
			String strGroupId = request.getParameter("groupId");
			if(strGroupId == null)
				strGroupId = "0";

			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrGroupCode(strGroupId);
			
			bo.getTariffDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("Billing Reports", "Discount Detail Report");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrTariffWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrTariffWs(), "0", "0^All",
						true);

			}else {
				temp = "<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
			

		} catch (Exception e) {
			strmsgText = "billing.reports.DiscountDetailsRptDATA.getTariffDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"DiscountDetailsRptDATA->getGroupDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}

	public static void getCategoryDetails(DiscountDetailsRptFB formBean,HttpServletRequest request,
										HttpServletResponse response) {
		
		DiscountDetailsRptBO bo = null;

		DiscountDetailsRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		String strCategoryVal = "";

		try {

			bo = new DiscountDetailsRptBO();
			voObj = new DiscountDetailsRptVO();

			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
		
			bo.getCategoryDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports", "DiscountDetailsRptDATA");

			strCategoryVal = util.getOptionValue(voObj.getStrCategoryWs(), "0",
					"0^All", false);

			formBean.setStrCategoryValues(strCategoryVal);

		} catch (Exception e) {
			strmsgText = "billing.reports.DiscountDetailsRptDATA.getCategoryDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"DiscountDetailsRptDATA->getCategoryDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}

	public static void getConsultDetails(DiscountDetailsRptFB formBean,HttpServletRequest request,HttpServletResponse response) 
	{
		DiscountDetailsRptBO bo = null;
		DiscountDetailsRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;

		try 
		{
			bo = new DiscountDetailsRptBO();
			voObj = new DiscountDetailsRptVO();

			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			bo.getConsultDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
			util = new HisUtil("Billing", "DiscountDetailsRptDATA");
			//strConsultVal = util.getOptionValue(voObj.getStrConsultWs(), "0","0^All", false);
		//	formBean.setStrConsultValues(strConsultVal);
			
			String temp1= "<option value='0'>Select Value</option>";
			
			if (voObj.getStrConsultWs().size() != 0) 
			{
				temp1 = util.getOptionValue(voObj.getStrConsultWs(), "0", "0^Select Value",true);
			}
			else 
			{
				temp1 ="<option value='0'>Select Value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);			
		} 
		catch (Exception e) 
		{
			strmsgText = "billing.reports.DiscountDetailsRptDATA.getConsultDetails --> "+ e.getMessage();
			HisException eObj = new HisException("billing","DiscountDetailsRptDATA->getConsultDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 
		finally 
		{
			bo = null;
			voObj = null;
			util = null;
		}
	}

	public static void getReasonDetails(DiscountDetailsRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		DiscountDetailsRptBO bo = null;

		DiscountDetailsRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		String strReasonVal = "";
		try {

			bo = new DiscountDetailsRptBO();
			voObj = new DiscountDetailsRptVO();
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrReason(formBean.getStrReason());

			bo.getReasonDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports", "DiscountDetailsRptDATA");


			strReasonVal = util.getOptionValue(voObj.getStrReasonWs(), "0",
					"0^All", false);

			formBean.setStrReasonValues(strReasonVal);

		} catch (Exception e) {
			strmsgText = "billing.reports.DiscountDetailsRptDATA.getReasonDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DiscountDetailsRptDATA->getReasonDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	
	public static void showReport(DiscountDetailsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				
				Map<String, Object> params = new HashMap<String, Object>();
			try {
					
					String strReportName = "Discount Detail Report";	
					String strChargeTypeId = formBean.getStrHospService();
					String strHospitalCode = formBean.getStrHospitalCode();
					String strReportId = formBean.getStrReportId();
					String strUserRemarks = formBean.getStrUserRemarks();
				//	String strGroupName = formBean.getStrGroupCode();
				//	String strTariffName = formBean.getStrTariffCode();
				//	String strTreatCatName = formBean.getStrCategory();
					String strConsultantName = formBean.getStrConsult();
					String strReason = formBean.getStrReason();
					String strFromDate = formBean.getStrEffectiveFrom();
					String strToDate = formBean.getStrEffectiveTo();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					reportFormat = formBean.getStrReportFormat();
					params.put("report_formattype", reportFormat);
					boolean footerVisible = true;
	
					if (formBean.getStrIsFooter().equals("1")) {
						footerVisible = false;
					}
				

//					if(formBean.getStrCase().equals("1")){
//						
//						String reportPath = "/billing/reports/discountDtl1_billrpt.rptdesign";
//
//						if(strGroupName.equals("0")){
//							params.put("group_Id", "null");
//						}else{
//							params.put("group_Id", strGroupName);
//						}
//						
//						params.put("report_id", strReportId);
//						params.put("report_Name", strReportName);
//						params.put("report_Footer_Visible", footerVisible);
//						params.put("report_user_Remarks", strUserRemarks);
//						
//						params.put("hosp_Code", strHospitalCode);
//						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
//						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
//						params.put("tariff_Id", "null");
//						params.put("reason_Id", "null");
//						params.put("consult_Id", "null");
//						params.put("treat_Cat", "null");
//						params.put("charge_Type_Id", strChargeTypeId);
//					
//						ts.displayReport(request, response, reportPath, reportFormat,
//								params);
//						
//					}else if(formBean.getStrCase().equals("2")){
//					
//							String reportPath = "/billing/reports/discountDtl2_billrpt.rptdesign";
//							
//							if(strTariffName.equals("0")){
//								params.put("tariff_Id", "null");
//							}else{
//								params.put("tariff_Id", strTariffName);
//							}
//							
//							params.put("hosp_Code", strHospitalCode);
//							params.put("report_id", strReportId);
//							params.put("report_Name", strReportName);
//							params.put("report_Footer_Visible", footerVisible);
//							params.put("report_user_Remarks", strUserRemarks);
//							params.put("tariff_Id", strTariffName);
//							params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
//							params.put("to_Date", sdf.format(sdf.parse(strToDate)));
//							params.put("reason_Id", "null");
//							params.put("consult_Id", "null");
//							params.put("treat_Cat", "null");
//							if(strGroupName.equals("0")){
//								params.put("group_Id", "null");
//							}else{
//								params.put("group_Id", strGroupName);
//							}
//							
//							params.put("charge_Type_Id", strChargeTypeId);
//							
//							ts.displayReport(request, response, reportPath, reportFormat,
//									params);
//						
					
//			   }else if(formBean.getStrCase().equals("1")){
//						
//						String reportPath = "/billing/reports/discountDtl3_billrpt.rptdesign";
//					
//						if(strTreatCatName.equals("0")){
//							params.put("treat_Cat", "null");
//						}else{
//							params.put("treat_Cat", strTreatCatName);
//						}
//						
//						if(strChargeTypeId.equals("1")){
//							params.put("chargeType_Name", "Opd");
//						}else if(strChargeTypeId.equals("2")){
//							params.put("chargeType_Name", "Ipd");
//						}else if(strChargeTypeId.equals("3")){
//							params.put("chargeType_Name", "Emergency");
//						}
//						params.put("report_id", strReportId);
//						params.put("report_Name", strReportName);
//						params.put("report_Footer_Visible", footerVisible);
//						params.put("report_user_Remarks", strUserRemarks);
//						params.put("hosp_Code", strHospitalCode);
//						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
//						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
//						params.put("reason_Id", "null");
//						params.put("consult_Id", "null");
//						params.put("tariff_Id", "null");
//						params.put("group_Id", "null");
//						params.put("charge_Type_Id", strChargeTypeId);
//						
//						ts.displayReport(request, response, reportPath, reportFormat,
//								params);
						
					if(formBean.getStrCase().equals("1")){
						
						String reportPath = "/billing/reports/discountDtl4New_billrpt.rptdesign";
						
						/*if(strConsultantName.equals("0")){
							params.put("consult_Id", "null");
						}else{*/
							params.put("consult_Id", strConsultantName);
						//}
						//System.out.println("strConsultantName"+strConsultantName);
						if(strChargeTypeId.equals("1")){
							params.put("chargeType_Name", "Opd Morning");
						}else if(strChargeTypeId.equals("2")){
							params.put("chargeType_Name", "Ipd");
						}else if(strChargeTypeId.equals("3")){
							params.put("chargeType_Name", "Emergency");
						}
						else if(strChargeTypeId.equals("4")){
							params.put("chargeType_Name", "Opd Special");
						}
						else if(strChargeTypeId.equals("0")){
							params.put("chargeType_Name", "All");
						}
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("reason_Id", "null");
						params.put("treat_Cat", "null");
						params.put("tariff_Id", "null");
						params.put("group_Id", "null");
						params.put("charge_Type_Id", strChargeTypeId);
					
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strHospitalCode);
						
					}else if(formBean.getStrCase().equals("2")){
						
						String reportPath = "/billing/reports/discountDtl5New_billrpt.rptdesign";
					
						if(strReason.equals("0")){
							params.put("reason_Id", "null");
						}else{
							params.put("reason_Id", strReason);
						}
						if(strChargeTypeId.equals("1")){
							params.put("chargeType_Name", "Opd");
						}else if(strChargeTypeId.equals("2")){
							params.put("chargeType_Name", "Ipd");
						}else if(strChargeTypeId.equals("3")){
							params.put("chargeType_Name", "Emergency");
						}
						else if(strChargeTypeId.equals("4")){
							params.put("chargeType_Name", "Opd Special");
						}
						else if(strChargeTypeId.equals("0")){
							params.put("chargeType_Name", "All");
						}
						params.put("reason_Id", strReason);
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("consult_Id", "null");
						params.put("treat_Cat", "null");
						params.put("tariff_Id", "null");
						params.put("group_Id", "null");
						params.put("charge_Type_Id", strChargeTypeId);
	
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strHospitalCode);
						
					}else if(formBean.getStrCase().equals("3")){
						
						String reportPath = "/billing/reports/discountDtl6New_billrpt.rptdesign";
						if(strChargeTypeId.equals("1")){
							params.put("chargeType_Name", "Opd");
						}else if(strChargeTypeId.equals("2")){
							params.put("chargeType_Name", "Ipd");
						}else if(strChargeTypeId.equals("3")){
							params.put("chargeType_Name", "Emergency");
						}
						else if(strChargeTypeId.equals("4")){
							params.put("chargeType_Name", "Opd Special");
						}
						else if(strChargeTypeId.equals("0")){
							params.put("chargeType_Name", "All");
						}
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("consult_Id", "null");
						params.put("treat_Cat", "null");
						params.put("tariff_Id", "null");
						params.put("group_Id", "null");
						params.put("reason_Id", "null");
						params.put("charge_Type_Id", strChargeTypeId);
						
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strHospitalCode);
					}
				

				} catch (Exception e) {
					strmsgText = "billing.reports.DiscountDetailsRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"DiscountDetailsRptDATA->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;		
					}
			}
	}
