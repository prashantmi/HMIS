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
import billing.reports.DayEndCashHandoverRptFB;

public class DayEndCashHandoverRptDATA {

	public static void initReportPage(DayEndCashHandoverRptFB formBean,HttpServletRequest request) {


		DayEndCashHandoverRptBO bo = null;
		DayEndCashHandoverRptVO voObj = null;

		HisUtil util = null;
		String strHospSerVal = "";
		String strmsgText = null;
        String strHospNameValues="";
		try {
		
			bo = new DayEndCashHandoverRptBO();
			voObj = new DayEndCashHandoverRptVO();
			
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )? request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			
			bo.getHospitalName(voObj);
			bo.getHospSerDetails(voObj);
			bo.getCounterDetails(voObj);
			bo.getClerkDetails(voObj);
			bo.getDeptDetails(voObj);
			bo.getCostTypeDetails(voObj);
			

			//voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing","DayEndCashHandoverRptDATA");
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), request.getSession().getAttribute("HOSPITAL_CODE").toString(),"0^All Hospitals", false);	
			
			
			
			strHospSerVal = util.getOptionValue(voObj.getStrHospSerWs(), "0","0^All", false);
			formBean.setStrHospNameValues(strHospNameValues);
			//formBean.setStrHospSerValues(strHospSerVal);
			formBean.setStrCostTypeValue(util.getOptionValue(voObj.getStrCostTypeWS(),"","0^All", false)	);
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
			strmsgText = "billing.reports.DayEndCashHandoverRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DayEndCashHandoverRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator!");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}
	}
	
	
	
	
	
	public static void getHospitalServiceDetails(DayEndCashHandoverRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		DayEndCashHandoverRptBO bo = null;

		DayEndCashHandoverRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new DayEndCashHandoverRptBO();
			voObj = new DayEndCashHandoverRptVO();
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			//String strhospCode=request.getParameter("hospCode").toString();

			
		//	if (strhospCode == null)
				//strhospCode = "0";
		
		//	voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

		
			
			
			
			bo.getHospSerDetails(voObj);
			
			util = new HisUtil("Billing Reports", "DayEndCashHandoverRptDATA");
			
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
			strmsgText = "ipd.reports.DayEndCashHandoverRptDATA.getWardDetailsCombo --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","FeeCollectionDetailsRpt->getdeptComboDetails()", 

strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	
	
	
	
	
	public static void getCounterDetails(DayEndCashHandoverRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		DayEndCashHandoverRptBO bo = null;

		DayEndCashHandoverRptVO voObj = null;

		HisUtil util = null;
		String strCounterVal = "";
		String strmsgText = null;

		try {
		
			bo = new DayEndCashHandoverRptBO();
			voObj = new DayEndCashHandoverRptVO();
			
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
			
			bo.getCounterDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","DayEndCashHandoverRptDATA");
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
			strmsgText = "billing.reports.DayEndCashHandoverRptDATA.getCounterDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DayEndCashHandoverRptDATA->getCounterDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void getClerkDetails(DayEndCashHandoverRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		DayEndCashHandoverRptBO bo = null;

		DayEndCashHandoverRptVO voObj = null;

		HisUtil util = null;
		String strClerkVal = "";
		String strmsgText = null;

		try {
			
			bo = new DayEndCashHandoverRptBO();
			voObj = new DayEndCashHandoverRptVO();
			
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());	
			bo.getClerkDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","DayEndCashHandoverRptDATA");
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
			strmsgText = "billing.reports.DayEndCashHandoverRptDATA.getClerkDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DayEndCashHandoverRptDATA->getClerkDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void getGroupDetails(DayEndCashHandoverRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		DayEndCashHandoverRptBO bo = null;
		DayEndCashHandoverRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		
		try {
			
			bo = new DayEndCashHandoverRptBO();
			voObj = new DayEndCashHandoverRptVO();

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
			strmsgText = "billing.reports.DayEndCashHandoverRptDATA.getGroupDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DayEndCashHandoverRptDATA->getGroupDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
	public static void getTariffDetails(DayEndCashHandoverRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		DayEndCashHandoverRptBO bo = null;

		DayEndCashHandoverRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;

		try {
			
			bo = new DayEndCashHandoverRptBO();
			voObj = new DayEndCashHandoverRptVO();

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
			strmsgText = "billing.reports.DayEndCashHandoverRptDATA.getTariffDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DayEndCashHandoverRptDATA->getTariffDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
	public static void getTariffDetailsCombo(DayEndCashHandoverRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		DayEndCashHandoverRptBO bo = null;

		DayEndCashHandoverRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;

		try {
			
			bo = new DayEndCashHandoverRptBO();
			voObj = new DayEndCashHandoverRptVO();

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
			strmsgText = "billing.reports.DayEndCashHandoverRptDATA.getTariffDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DayEndCashHandoverRptDATA->getTariffDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	public static void getDeptDetails(DayEndCashHandoverRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		DayEndCashHandoverRptBO bo = null;

		DayEndCashHandoverRptVO voObj = null;

		HisUtil util = null;
		String strDeptVal = "";
		String strmsgText = null;

		try {
			
			bo = new DayEndCashHandoverRptBO();
			voObj = new DayEndCashHandoverRptVO();
			
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
			util = new HisUtil("Billing Reports","DayEndCashHandoverRptDATA");
			
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
			strmsgText = "billing.reports.DayEndCashHandoverRptDATA.getDeptDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DayEndCashHandoverRptDATA->getDeptDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	public static void showReport(DayEndCashHandoverRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				
				Map<String, Object> params = new HashMap<String, Object>();
			try {
					
					String strReportName = "Day End Cash Habdover Report";		

					String strHospitalCode = (formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="")?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode();

					//String strHospitalCode = formBean.getStrHospitalCode();
					String strReportId = formBean.getStrReportId();
					String strUserRemarks = formBean.getStrUserRemarks();
					String strCounterName = formBean.getStrCounterName();
					String cost_type=formBean.getStrCostType();
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
	
					
					
					
					
	
					if (formBean.getStrIsFooter().equals("1")) {
						footerVisible = false;
					}
					
					String reportPath = "";

					if(formBean.getStrCase().equals("1")){//scroll no wise
						 reportPath = "/billing/reports/cashHandoverRpt.rptdesign";
					}
					
                      if(formBean.getStrCase().equals("2")){
						
                    	  reportPath = "/billing/reports/cashHandoverRpt2.rptdesign";
					}
					  params.put("cost_type", cost_type);
					  params.put("report_id", strReportId);
					  params.put("report_Name", strReportName);
					  params.put("report_user_Remarks", strUserRemarks);
					  params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
					  params.put("to_Date", sdf.format(sdf.parse(strToDate)));
                      params.put("hosp_Code", strHospitalCode);
                      params.put("report_Footer_Visible", footerVisible);
                      params.put("report_formattype", reportFormat);
                      
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strHeaderHospCode);
						
					
				
			
				} catch (Exception e) {
					strmsgText = "billing.reports.DayEndCashHandoverRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"DayEndCashHandoverRptDATA->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;			
					}
			}
	
	public static void getTariffMulSelComboDetails(DayEndCashHandoverRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		DayEndCashHandoverRptBO bo = null;

		DayEndCashHandoverRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;

		try {
			
			bo = new DayEndCashHandoverRptBO();
			voObj = new DayEndCashHandoverRptVO();

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
			strmsgText = "billing.reports.DayEndCashHandoverRptDATA.getTariffDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DayEndCashHandoverRptDATA->getTariffDetails()", strmsgText);
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
