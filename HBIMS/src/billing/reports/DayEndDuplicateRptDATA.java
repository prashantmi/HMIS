package billing.reports;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billing.BillConfigUtil;
import billing.PrintHLP;

public class DayEndDuplicateRptDATA {
	
	public static void initVal(DayEndDuplicateRptFB formBean, HttpServletRequest request) {
	
		HisUtil util = null;
		String strmsgText = null;
		String strHospNameValues="";
		BillConfigUtil bcu = null;
		DayEndDuplicateRptBO bo = null;
		DayEndDuplicateRptVO voObj = null;
		
		
		try {
		
			util = new HisUtil("Billing Reports","DayEndDuplicateRptDATA");
			bcu = new BillConfigUtil(formBean.getStrHospitalCode());
			bo= new DayEndDuplicateRptBO();
			voObj = new DayEndDuplicateRptVO();
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )? request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			bo.getHospitalName(voObj);
			if (voObj.getStrMsgType().equals("1")) {
	
				throw new Exception(voObj.getStrMsgString());
	
			}
			
			strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), request.getSession().getAttribute("HOSPITAL_CODE").toString(),"0^Select Value", false);
			formBean.setStrHospNameValues(strHospNameValues);
			
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			
			if(formBean.getStrBillModuleId().equals("") || formBean.getStrBillModuleId().equals("0") ){
			
				String strBillModuleId = request.getParameter("billModuleId");
				formBean.setStrBillModuleId(strBillModuleId);
				
			}
			
			
			if(formBean.getStrUserMode().equals("") || formBean.getStrUserMode().equals("0") ){
			
				String strUserMode = request.getParameter("userMode");
				formBean.setStrUserMode(strUserMode);
				
			}
									
			formBean.setStrPrintCount(bcu.getGeneralPrintMessageLimit());
			formBean.setStrIsDetailsRequired("1");
			formBean.setStrIsFooter("1");// TO PRINT HTML PAGE HEADER ON EACH PAGE AND PAGE NUMBER-AMIT ATERIA.FIRTHER MAPPING FOUND IN RPTDESIGN FILE			
		} catch (Exception e) {
	 
			strmsgText = "billing.reports.DayEndDuplicateRptDATA.initVal --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"DayEndDuplicateRptDATA->initVal()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator!");
	
			eObj = null;
		} finally {
	
			util = null;
			bcu = null;
		}
	
	}
		
	public static void getSummaryDetails(DayEndDuplicateRptFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
		DayEndDuplicateRptBO bo = null;
		DayEndDuplicateRptVO vo = null;
		HisUtil util = null;
	
		try 
		{
			vo = new DayEndDuplicateRptVO();
			bo = new DayEndDuplicateRptBO();
			util = new HisUtil("Billing","Day End Duplicate Report");
			
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			
			String strFromDate = request.getParameter("fromDate");
			formBean.setStrFromDate(strFromDate);
			vo.setStrFromDate(formBean.getStrFromDate());
						
			String strToDate = request.getParameter("toDate");
			formBean.setStrToDate(strToDate);
			vo.setStrToDate(formBean.getStrToDate());
			
			String strBillModuleId = request.getParameter("billModuleId");
			formBean.setStrBillModuleId(strBillModuleId);			
			vo.setStrBillModuleId(formBean.getStrBillModuleId());
		 
			//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrHospitalCode(request.getParameter("hospCode"));
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			
			if(formBean.getStrUserMode().equals("") )
					formBean.setStrUserMode("1");
			
			if(formBean.getStrUserMode().equals("1"))
			{				
				//String strUserId = request.getSession().getAttribute("USER_ID").toString();				
				String strUserId = request.getSession().getAttribute("SEATID").toString();
				vo.setStrUserId(strUserId);				
			} 			
			 
			bo.getSummaryDetails(vo);
	
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
	
			String temp = DayEndDuplicateRptHLP.getSummaryDetails(vo);			
			response.setHeader("Cache-Control", "no-cache");
			
			response.getWriter().print(temp);
		} 
		catch (Exception e) 
		{	 
			String  msgStr =  e.getMessage();
			HisException eObj = new HisException("Billing", "DayEndDuplicateRptDATA->getSummaryDetails()", msgStr);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} 
		finally 
		{
			vo = null;
			bo = null;
		}
	}
		
		
	/**
	 * Re-Print's the bill.
	 * @param request
	 * @param response
	 */
	public static void rePrint(HttpServletRequest request,
			HttpServletResponse response) {
	
		try {
	
			 
	
			PrintHLP.reprintFile(request);
	
		} catch (Exception e) {
	
			HisException eObj = new HisException("Billing",
					"DayEndDuplicateRptDATA->rePrint()", e
							.getMessage());
			String response1 = "ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator!";
	
			eObj = null;
	
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(response1);
			} catch (IOException e1) {
	
				new HisException("Billing",
						"DayEndDuplicateRptDATA->rePrint()", e1
								.getMessage());
			}
	
		}
	}
		
		
	public static void showReport(DayEndDuplicateRptFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{			 
			ReportUtil ts = new ReportUtil();
			String reportFormat = "html";				
			BillConfigUtil bcu =  new BillConfigUtil(formBean.getStrHospitalCode());				
			Map<String, Object> params = new HashMap<String, Object>();
			try 
			{
				if(formBean.getStrReportFormat().trim().toLowerCase().equals("text"))
				{					
				//	PrintHLP.isDuplicateBill = 1;
					PrintHLP.DAYEND_REPORT(formBean.getStrSummaryNo(), formBean.getStrHospitalCode(), formBean.getStrBillModuleId(), formBean.getStrIsDetailsRequired(), formBean.getStrIsPayModeRequired(), formBean.getStrIsPayDetailRequired(), request , 1);
				//	PrintHLP.isDuplicateBill = 0;						
					if(formBean.getStrBillModuleId().equals("") || formBean.getStrBillModuleId().equals("0") )
					{							
						String strBillModuleId = request.getParameter("billModuleId");
						formBean.setStrBillModuleId(strBillModuleId);							
					}						
					if(formBean.getStrUserMode().equals("") || formBean.getStrUserMode().equals("0") )
					{						
						String strUserMode = request.getParameter("userMode");
						formBean.setStrUserMode(strUserMode);							
					}						
					return;
				}				 
				String strReportName = "Scroll-Day End Report (Duplicate)";								
				String strHospitalCode = formBean.getStrHospitalCode();
				String strReportId = formBean.getStrReportId();
				String strUserRemarks = formBean.getStrUserRemarks();
				String strSummNo = formBean.getStrSummaryNo();
				reportFormat = formBean.getStrReportFormat();
				params.put("report_formattype", reportFormat);
				boolean footerVisible = true;
				boolean counterVisible = true;					
				boolean payModeVisible = true;
				boolean payDtlVisible = true;	
				boolean logoVisible = false;
				if (formBean.getStrIsFooter().equals("1")) 
				{
					footerVisible = false;
				}
				
				if(formBean.getStrBillModuleId().equals("1") || formBean.getStrBillModuleId().equals("3"))
				{						
					String reportPath = "/billing/reports/dayend_billrptBilling.rptdesign";						
					if(formBean.getStrIsDetailsRequired().equals("0"))
					{							 
						  reportPath = "/billing/reports/dayend_billrptBillingWithoutDtls.rptdesign";														
					}						
					if(formBean.getStrIsPayModeRequired().equals("1"))
					{							
						payModeVisible = false;
					}						
					if(formBean.getStrIsPayDetailRequired().equals("1"))
					{							
						payDtlVisible = false;
					}						
					if(bcu.getGeneralDayEndProcessType().equals("2"))
					{							
						counterVisible = false;							
					}
					if(bcu.getLogoReq().equals("1"))
					{							
						logoVisible = true;							
					}
					
					params.put("isCounterRequired", counterVisible);
					params.put("isPaymentModeReq", payModeVisible);
					params.put("isPaymentDtlReq", payDtlVisible);
					params.put("report_id", strReportId);
					params.put("report_Name", strReportName);
					params.put("report_Footer_Visible", footerVisible);
					params.put("report_user_Remarks", strUserRemarks);
					params.put("summ_No", strSummNo);
					params.put("hosp_Code", strHospitalCode);
					params.put("dayEndDate", formBean.getStrDayEndDate());
					params.put("seatId", request.getSession().getAttribute("SEATID").toString());
					params.put("isLogoRequired", logoVisible);
			
					ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
				}
				else
				{
					String reportPath = "/billing/reports/dayend_billrptOthers.rptdesign";						
					if(formBean.getStrIsDetailsRequired().equals("0"))
					{							
						 reportPath = "/billing/reports/dayend_billrptOthersWithoutDtls.rptdesign";							
					}										
					if(bcu.getGeneralDayEndNonBillingProcessType().equals("2"))
					{							
						counterVisible = false;							
					}
					if(bcu.getLogoReq().equals("1"))
					{							
						logoVisible = true;							
					}
					params.put("isCounterRequired", counterVisible);												
					params.put("report_id", strReportId);
					params.put("report_Name", strReportName);
					params.put("report_Footer_Visible", footerVisible);
					params.put("report_user_Remarks", strUserRemarks);
					params.put("summNo", strSummNo);
					params.put("hospCode", strHospitalCode);
			
					ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
				}					
			} 
			catch (Exception e) 
			{		 
				//e.printStackTrace();					
				String  msgStr =  e.getMessage();
				HisException eObj = new HisException("Billing", "DayEndDuplicateRptDATA->showReport()", msgStr);
				formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				eObj = null;
			}
	}

}
