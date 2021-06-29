package billing.reports;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billing.BillConfigUtil;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import billing.reports.FeeCollectionDetailsRptFB;

public class FeeCollectionDetailsRptDATA {

	public static void initReportPage(FeeCollectionDetailsRptFB formBean,HttpServletRequest request) {


		FeeCollectionDetailsRptBO bo = null;
		FeeCollectionDetailsRptVO voObj = null;

		HisUtil util = null;
		String strHospSerVal = "";
		String strmsgText = null;
        String strHospNameValues="";
		try {
		
			bo = new FeeCollectionDetailsRptBO();
			voObj = new FeeCollectionDetailsRptVO();
			
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )? request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			
			bo.getHospitalName(voObj);
			bo.getHospSerDetails(voObj);
			bo.getCounterDetails(voObj);
			bo.getClerkDetails(voObj);
			bo.getDeptDetails(voObj);

			//voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing","FeeCollectionDetailsRptDATA");
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), request.getSession().getAttribute("HOSPITAL_CODE").toString(),"0^All", false);	

			
			strHospSerVal = util.getOptionValue(voObj.getStrHospSerWs(), "0","0^All", false);
			formBean.setStrHospNameValues(strHospNameValues);
			formBean.setStrHospSerValues(strHospSerVal);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
            String temp1= "<option value='0'>All</option>";
			
			if (voObj.getStrCounterWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrCounterWs(), "0", "0^All",true);
			}
			else {
				temp1 ="<option value='0'>All</option>";
			}
			formBean.setStrCounterValues(temp1);
			
			
			  String temp2= "<option value='0'>All</option>";
				
				if (voObj.getStrClerkWs().size() != 0) {
					temp2 = util.getOptionValue(voObj.getStrClerkWs(), "0", "0^All",true);
				}
				else {
					temp2 ="<option value='0'>All</option>";
				}
				formBean.setStrFeeClerkValues(temp2);
				
				String temp3= "<option value='0'>All</option>";
				
				if (voObj.getStrDeptWs().size() != 0) {
					temp3 = util.getOptionValue(voObj.getStrDeptWs(), "0", "0^All",true);
				}
				else {
					temp3 ="<option value='0'>All</option>";
				}
				formBean.setStrDeptValues(temp3);

		} catch (Exception e) {
			strmsgText = "billing.reports.FeeCollectionDetailsRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"FeeCollectionDetailsRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator!");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}
	}
	
	
	
	
	
	public static void getHospitalServiceDetails(FeeCollectionDetailsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		FeeCollectionDetailsRptBO bo = null;

		FeeCollectionDetailsRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new FeeCollectionDetailsRptBO();
			voObj = new FeeCollectionDetailsRptVO();
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			//String strhospCode=request.getParameter("hospCode").toString();

			
		//	if (strhospCode == null)
				//strhospCode = "0";
		
		//	voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

		
			
			
			
			bo.getHospSerDetails(voObj);
			
			util = new HisUtil("Billing Reports", "FeeCollectionDetailsRptDATA");
			
			String temp1= "<option value='0'>All</option>";
			
			if (voObj.getStrHospSerWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrHospSerWs(), "0", "0^All",true);
			}
			else {
				temp1 ="<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);
		}
		
		catch (Exception e) 
		{
			strmsgText = "ipd.reports.FeeCollectionDetailsRptDATA.getWardDetailsCombo --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","FeeCollectionDetailsRpt->getdeptComboDetails()", 

strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	
	
	
	
	
	public static void getCounterDetails(FeeCollectionDetailsRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		FeeCollectionDetailsRptBO bo = null;

		FeeCollectionDetailsRptVO voObj = null;

		HisUtil util = null;
		String strCounterVal = "";
		String strmsgText = null;

		try {
		
			bo = new FeeCollectionDetailsRptBO();
			voObj = new FeeCollectionDetailsRptVO();
			
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
			
			bo.getCounterDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","FeeCollectionDetailsRptDATA");
			//strCounterVal = util.getOptionValue(voObj.getStrCounterWs(), "0",
					//"0^All", false);

			//formBean.setStrCounterValues(strCounterVal);
			
			
          String temp1= "<option value='0'>All</option>";
			
			if (voObj.getStrCounterWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrCounterWs(), "0", "0^All",true);
			}
			else {
				temp1 ="<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);
			
			//formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "billing.reports.FeeCollectionDetailsRptDATA.getCounterDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"FeeCollectionDetailsRptDATA->getCounterDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void getClerkDetails(FeeCollectionDetailsRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		FeeCollectionDetailsRptBO bo = null;

		FeeCollectionDetailsRptVO voObj = null;

		HisUtil util = null;
		String strClerkVal = "";
		String strmsgText = null;

		try {
			
			bo = new FeeCollectionDetailsRptBO();
			voObj = new FeeCollectionDetailsRptVO();
			
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());	
			bo.getClerkDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","FeeCollectionDetailsRptDATA");
		//	strClerkVal = util.getOptionValue(voObj.getStrClerkWs(), "0",
					//"0^All", false);

			//formBean.setStrFeeClerkValues(strClerkVal);
			  String temp1= "<option value='0'>All</option>";
				
				if (voObj.getStrClerkWs().size() != 0) {
					temp1 = util.getOptionValue(voObj.getStrClerkWs(), "0", "0^All",true);
				}
				else {
					temp1 ="<option value='0'>All</option>";
				}

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp1);
				
			
			

		} catch (Exception e) {
			strmsgText = "billing.reports.FeeCollectionDetailsRptDATA.getClerkDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"FeeCollectionDetailsRptDATA->getClerkDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void getGroupDetails(FeeCollectionDetailsRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		FeeCollectionDetailsRptBO bo = null;
		FeeCollectionDetailsRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		
		try {
			
			bo = new FeeCollectionDetailsRptBO();
			voObj = new FeeCollectionDetailsRptVO();

			String strChargeTypeId = request.getParameter("chargeTypeId");
			
			if (strChargeTypeId == null)
				strChargeTypeId = "0";
		    
			voObj.setStrHospSerName(strChargeTypeId);
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			bo.getGroupDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("BILLING Reports", "Fee Collection Details");
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
			strmsgText = "billing.reports.FeeCollectionDetailsRptDATA.getGroupDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"FeeCollectionDetailsRptDATA->getGroupDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
	public static void getTariffDetails(FeeCollectionDetailsRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		FeeCollectionDetailsRptBO bo = null;

		FeeCollectionDetailsRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;

		try {
			
			bo = new FeeCollectionDetailsRptBO();
			voObj = new FeeCollectionDetailsRptVO();

			String strGroupId = request.getParameter("groupId");
			if(strGroupId == null)
				strGroupId = "0";
			String tariffType = request.getParameter("tariffType");
			if(tariffType == null)
				tariffType = "0";

			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrTariffType(tariffType);
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrGroupName(strGroupId);
			

			bo.getTariffDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "Fee Collection Details");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrTariffWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrTariffWs(), "0", "0^All",
						true);

			}else {
				temp = "<option value='0'>All</option>";
			}
			
			formBean.setStrTariffValues(temp);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);


		} catch (Exception e) {
			strmsgText = "billing.reports.FeeCollectionDetailsRptDATA.getTariffDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"FeeCollectionDetailsRptDATA->getTariffDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
	public static void getTariffDetailsCombo(FeeCollectionDetailsRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		FeeCollectionDetailsRptBO bo = null;

		FeeCollectionDetailsRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;

		try {
			
			bo = new FeeCollectionDetailsRptBO();
			voObj = new FeeCollectionDetailsRptVO();

			String strGroupId = request.getParameter("groupId");
			if(strGroupId == null)
				strGroupId = "0";
			String tariffType = request.getParameter("tariffType");
			if(tariffType == null)
				tariffType = "0";
			
			voObj.setStrTariffType(tariffType);

		//	voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			voObj.setStrGroupName(strGroupId);
			

			bo.getTariffDetailsCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "Fee Collection Details");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrTariffWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrTariffWs(), "0", "0^All",
						true);

			}else {
				temp = "<option value='0'>All</option>";
			}
			
			formBean.setStrTariffValues(temp);
			
			/*response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);*/


		} catch (Exception e) {
			strmsgText = "billing.reports.FeeCollectionDetailsRptDATA.getTariffDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"FeeCollectionDetailsRptDATA->getTariffDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void getDeptDetails(FeeCollectionDetailsRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		FeeCollectionDetailsRptBO bo = null;

		FeeCollectionDetailsRptVO voObj = null;

		HisUtil util = null;
		String strDeptVal = "";
		String strmsgText = null;

		try {
			
			bo = new FeeCollectionDetailsRptBO();
			voObj = new FeeCollectionDetailsRptVO();
			
			String strGroupId = request.getParameter("groupId");
			if(strGroupId == null)
				strGroupId = "0";
			
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			bo.getDeptDetails(voObj);
			voObj.setStrGroupName(strGroupId);
			bo.getTariffDetailsCombo(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","FeeCollectionDetailsRptDATA");
			
			String temp1= "<option value='0'>All</option>";
			
			if (voObj.getStrDeptWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrDeptWs(), "0", "0^All",true);
			}
			else {
				temp1 ="<option value='0'>All</option>";
			}
			String temp2= "<option value='0'>All</option>";
			
			if (voObj.getStrTariffWs().size() != 0) {
				temp2 = util.getOptionValue(voObj.getStrTariffWs(), "0", "0^All",true);
			}
			else {
				temp2 ="<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1+"^"+temp2);
		//	strDeptVal = util.getOptionValue(voObj.getStrDeptWs(), "0",
					//"0^All", false);

			//formBean.setStrDeptValues(strDeptVal);
			
			

		} catch (Exception e) {
			strmsgText = "billing.reports.FeeCollectionDetailsRptDATA.getDeptDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"FeeCollectionDetailsRptDATA->getDeptDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	public static void showReport(FeeCollectionDetailsRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				
				Map<String, Object> params = new HashMap<String, Object>();
			try {
					
					String strReportName = "Fee Collection Report";		

					String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();

					//String strHospitalCode = formBean.getStrHospitalCode();
					String strReportId = formBean.getStrReportId();
					String strUserRemarks = formBean.getStrUserRemarks();
					String strCounterName = formBean.getStrCounterName();
					String strFeeClerkName = formBean.getStrFeeClerkName();
					//String strGroupName = formBean.getStrGroupName();
					//String strTariffName = formBean.getStrTariffName();
					String strDepartmentName = formBean.getStrDeptName();
					String strFromDate = formBean.getStrEffectiveFrom();
					String strToDate = formBean.getStrEffectiveTo();
					String strChargeTypeId = formBean.getStrHospSerName();
					String tariffType = formBean.getStrTariffType();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					reportFormat = formBean.getStrReportFormat();
					params.put("report_formattype", reportFormat);
					boolean footerVisible = true;
					String strHeaderHospCode="100";
					
					if(formBean.getStrAllHospCode().equalsIgnoreCase("testCode"))//multi hosp or single selected (ALL not selected)
					{
						strHospitalCode= formBean.getStrHospitalCode();
					
						if(formBean.getStrHospitalCode().contains(","))//multi hosp sel
						{
							//since multiple hospitals are selected
							strHeaderHospCode="100";
						}
						else
						{
							strHeaderHospCode=formBean.getStrHospitalCode();
						}
					}
					else //all selected
					{
						strHospitalCode=formBean.getStrAllHospCode();
						strHeaderHospCode="100";
					}
	
					
					
					
					//System.out.println("header hosp code::"+strHeaderHospCode+"::hosp code::"+formBean.getStrHospitalCode());
					
	
					if (formBean.getStrIsFooter().equals("1")) {
						footerVisible = false;
					}
					
					if(formBean.getStrCase().equals("1")){//COUNTER WISE
						
						String reportPath = "/billing/reports/feeCollectionDtl1_billrpt.rptdesign";

						if(strCounterName.equals("0")){
							params.put("counter_Id", "null");
						}else{
							params.put("counter_Id", strCounterName);
						}
						if(strChargeTypeId.equals("0")){
							params.put("chargeType_Name", "All");
						}else if(strChargeTypeId.equals("1")){
							params.put("chargeType_Name", "Opd Morning");
						}else if(strChargeTypeId.equals("2")){
							params.put("chargeType_Name", "Ipd");
						}else if(strChargeTypeId.equals("3")){
							params.put("chargeType_Name", "Emergency");
						}
						else if(strChargeTypeId.equals("4")){
							params.put("chargeType_Name", "Opd Special");
						}
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("clerk_Id", "null");
						params.put("group_Id", "null");
						params.put("tariff_Id", "null");
						params.put("dept_Code", "null");
						if(strChargeTypeId.equals("0")){
							params.put("charge_Type_Id", "null");
						}else{
							params.put("charge_Type_Id", strChargeTypeId);
						}
						
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strHeaderHospCode);
						
					}else if(formBean.getStrCase().equals("2")){
					
							String reportPath = "/billing/reports/feeCollectionDtl2New_billrpt.rptdesign";
							
							if(strFeeClerkName.equals("0")){
								params.put("clerk_Id", "null");
							}else{
								params.put("clerk_Id", strFeeClerkName);
							}
							if(strChargeTypeId.equals("0")){
								params.put("chargeType_Name", "All");
							}else if(strChargeTypeId.equals("1")){
								params.put("chargeType_Name", "Opd Morning");
							}else if(strChargeTypeId.equals("2")){
								params.put("chargeType_Name", "Ipd");
							}else if(strChargeTypeId.equals("3")){
								params.put("chargeType_Name", "Emergency");
							}
							else if(strChargeTypeId.equals("4")){
								params.put("chargeType_Name", "Opd Special");
							}
							params.put("hosp_Code", strHospitalCode);
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
							params.put("to_Date", sdf.format(sdf.parse(strToDate)));
							params.put("group_Id", "null");
							params.put("tariff_Id", "null");
							params.put("dept_Code", "null");
							params.put("counter_Id", "null");
							if(strChargeTypeId.equals("0")){
								params.put("charge_Type_Id", "null");
							}else{
								params.put("charge_Type_Id", strChargeTypeId);
							}
							
							ts.displayReport(request, response, reportPath, reportFormat,params,strHeaderHospCode);
							
						
					
//					}else if(formBean.getStrCase().equals("3")){
//						
//						String reportPath = "/billing/reports/feeCollectionDtl3_billrpt.rptdesign";
//					
//						if(strGroupName.equals("0")){
//							params.put("group_Id", "null");
//						}else{
//							params.put("group_Id", strGroupName);
//						}
//						if(strChargeTypeId.equals("0")){
//							params.put("chargeType_Name", "All");
//						}else if(strChargeTypeId.equals("1")){
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
//						params.put("tariff_Id", "null");
//						params.put("dept_Code", "null");
//						params.put("counter_Id", "null");
//						params.put("clerk_Id", "null");
//						if(strChargeTypeId.equals("0")){
//							params.put("charge_Type_Id", "null");
//						}else{
//							params.put("charge_Type_Id", strChargeTypeId);
//						}
//						
//						ts.displayReport(request, response, reportPath, reportFormat,
//								params);
//						
//					}else if(formBean.getStrCase().equals("4")){
//						
//						String reportPath = "/billing/reports/feeCollectionDtl4_billrpt.rptdesign";
//						
//						if(strGroupName.equals("0")){
//							params.put("group_Id", "null");
//						}else{
//							params.put("group_Id", strGroupName);
//						}
//						if(strTariffName.equals("0")){
//							params.put("tariff_Id", "null");
//						}else{
//							params.put("tariff_Id", strTariffName);
//						}
//						if(strChargeTypeId.equals("0")){
//							params.put("chargeType_Name", "All");
//						}else if(strChargeTypeId.equals("1")){
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
//						params.put("dept_Code", "null");
//						params.put("counter_Id", "null");
//						params.put("clerk_Id", "null");
//						if(strChargeTypeId.equals("0")){
//							params.put("charge_Type_Id", "null");
//						}else{
//							params.put("charge_Type_Id", strChargeTypeId);
//						}
//						
//						ts.displayReport(request, response, reportPath, reportFormat,
//								params);
					
					}else if(formBean.getStrCase().equals("3")){
						
						String reportPath = "/billing/reports/feeCollectionDtl5New_billrpt.rptdesign";
						
						if(strDepartmentName.equals("0")){
							params.put("dept_Code", "null");
						}else{
							params.put("dept_Code", strDepartmentName);
						}
						if(strChargeTypeId.equals("0")){
							params.put("chargeType_Name", "All");
						}else if(strChargeTypeId.equals("1")){
							params.put("chargeType_Name", "Opd Morning");
						}else if(strChargeTypeId.equals("2")){
							params.put("chargeType_Name", "Ipd");
						}else if(strChargeTypeId.equals("3")){
							params.put("chargeType_Name", "Emergency");
						}
						else if(strChargeTypeId.equals("4")){
							params.put("chargeType_Name", "Opd Special");
						}
						if(formBean.getStrTariffWise().equals("5"))
						{
							params.put("tariff_Id", formBean.getStrTariffName());
							reportPath = "/billing/reports/feeCollectionDtl7New_billrpt.rptdesign";
						}
						else
						{
							params.put("tariff_Id", "null");
						}
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("counter_Id", "null");
						params.put("clerk_Id", "null");
						params.put("group_Id", "null");
						if(strChargeTypeId.equals("0")){
							params.put("charge_Type_Id", "null");
						}else{
							params.put("charge_Type_Id", strChargeTypeId);
						}
						
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strHeaderHospCode);
						
						
					}else if(formBean.getStrCase().equals("4")){
						
						String reportPath = "/billing/reports/feeCollectionDtl6New_billrpt.rptdesign";
						if(strChargeTypeId.equals("0")){
							params.put("chargeType_Name", "All");
						}else if(strChargeTypeId.equals("1")){
							params.put("chargeType_Name", "Opd Morning");
						}else if(strChargeTypeId.equals("2")){
							params.put("chargeType_Name", "Ipd");
						}else if(strChargeTypeId.equals("3")){
							params.put("chargeType_Name", "Emergency");
						}
						else if(strChargeTypeId.equals("4")){
							params.put("chargeType_Name", "Opd Special");
						}
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("tariff_Id", "null");
						params.put("counter_Id", "null");
						params.put("clerk_Id", "null");
						params.put("group_Id", "null");
						params.put("dept_Code", "null");
						if(strChargeTypeId.equals("0")){
							params.put("charge_Type_Id", "null");
						}else{
							params.put("charge_Type_Id", strChargeTypeId);
						}
						
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strHeaderHospCode);
						
					}
                     else if(formBean.getStrCase().equals("9")){ // Tariff+Date 
						
						String reportPath = "/billing/reports/feeCollectionDtl11_billrpt.rptdesign";
						
	
						tariffType = formBean.getStrTariffTypeNew();
						
						if(strDepartmentName.equals("0")){
							params.put("dept_Code", "null");
						}else{
							params.put("dept_Code", strDepartmentName);
						}
						if(strChargeTypeId.equals("0")){
							params.put("chargeType_Name", "All");
						}else if(strChargeTypeId.equals("1")){
							params.put("chargeType_Name", "Opd Morning");
						}else if(strChargeTypeId.equals("2")){
							params.put("chargeType_Name", "Ipd");
						}else if(strChargeTypeId.equals("3")){
							params.put("chargeType_Name", "Emergency");
						}
						else if(strChargeTypeId.equals("4")){
							params.put("chargeType_Name", "Opd Special");
						}
						
						if(tariffType.equals("1")){
							params.put("tariff_type", "Only Hospital Cost");
						}else if(tariffType.equals("2")){
							params.put("tariff_type", "Only Package Cost");
						}
						else {
							params.put("tariff_type", "All");
						}
						if(formBean.getStrTariffSelection().equals("1"))
							params.put("tariff_Id", "0");
						else
						{
							//System.out.println("selected tariffs are::"+formBean.getStrSelectedTariffIds());
							params.put("tariff_Id", formBean.getStrSelectedTariffIds());
						}
						
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("counter_Id", "null");
						//params.put("clerk_Id", "null");
						if(strFeeClerkName.equals("0")){
							params.put("clerk_Id", "null");
						}else{
							params.put("clerk_Id", strFeeClerkName);
						}
						params.put("group_Id", "null");
						if(strChargeTypeId.equals("0")){
							params.put("charge_Type_Id", "null");
						}else{
							params.put("charge_Type_Id", strChargeTypeId);
						}
						
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strHeaderHospCode);
						
					}
				
			
				} catch (Exception e) {
					strmsgText = "billing.reports.FeeCollectionDetailsRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"FeeCollectionDetailsRptDATA->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;			
					}
			}
	
	public static void getTariffMulSelComboDetails(FeeCollectionDetailsRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		FeeCollectionDetailsRptBO bo = null;

		FeeCollectionDetailsRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;

		try {
			
			bo = new FeeCollectionDetailsRptBO();
			voObj = new FeeCollectionDetailsRptVO();

			String strGroupId = request.getParameter("groupId");
			if(strGroupId == null)
				strGroupId = "0";
			String tariffType = request.getParameter("tariffType");
			if(tariffType == null)
				tariffType = "0";
			
			voObj.setStrTariffType(tariffType);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrGroupName(strGroupId);
			

			bo.getTariffDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}

			util = new HisUtil("IPD Reports", "Fee Collection Details");
			String temp = "<option value='0'>Select Value</option>";

			if (voObj.getStrTariffWs().size() != 0) {

				temp = util.getOptionValue(voObj.getStrTariffWs(), "", "",
						true);

			}else {
				temp = "<option value='0'>All</option>";
			}
			
			formBean.setStrTariffValues(temp);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);


		} catch (Exception e) {
			strmsgText = "billing.reports.FeeCollectionDetailsRptDATA.getTariffDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"FeeCollectionDetailsRptDATA->getTariffDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
	}
