package billing.transactions;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.controller.HisComboOptions;
import hisglobal.utility.HisUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.PrintHLP;

public class DayEndTransDATA {

	public static void dayEndProcess(DayEndTransFB formBean,HttpServletRequest request) 
	{
		String strUserVal = "";
		String strCounterVal = "";
		LinkedHashMap<String, String> _mapProcedureParam2 = null;
		LinkedHashMap<String, String> _mapProcedureParam3 = null;
		
		DayEndTransVO vo = null;
		DayEndTransBO bo = null;
		
		BillConfigUtil bcu = null;	
		
		HisUtil hisUtil = null;
		
		try 
		{
			 vo = new DayEndTransVO();
			 bo = new DayEndTransBO();
			
			 bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());	
			 hisUtil = new HisUtil("Billing" , "DayEndTransDATA.dayEndProcess()");
			
			 //StrUserMode 1 Clerk,2 Supervisor
			 //billModuleId 1 Billing Day End, 2 Registration Day End
			 if(request.getParameter("userMode") != null)
			 {
				 formBean.setStrUserMode(request.getParameter("userMode"));				
			 }
			 else
			 {
				formBean.setStrUserMode("1");
			 }
			
			if(request.getParameter("billModuleId") != null)
			{
				
				formBean.setStrBillModuleId(request.getParameter("billModuleId"));				
			}
			else
			{
				formBean.setStrBillModuleId("1");
			}			
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
			
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
	    	vo.setStrIpAddress(formBean.getStrIpAddress());
			vo.setStrSeatId(formBean.getStrSeatId());
	    	vo.setStrBillModuleId(formBean.getStrBillModuleId());
	    	vo.setStrUserMode(formBean.getStrUserMode());
	    	
	    	 _mapProcedureParam2 = new LinkedHashMap<String, String>();
			 _mapProcedureParam2.put("hosp_code", vo.getStrHospitalCode());
			 _mapProcedureParam2.put("userid","0");
			 _mapProcedureParam2.put("groupid", "0");
			 _mapProcedureParam2.put("err", "#1");
			 _mapProcedureParam2.put("resultset", "#2");

			 strUserVal = HisComboOptions.getOptionsFromProc("pkg_bill_view.PROC_GBLT_USER_MST", _mapProcedureParam2, "0", "0^Select Value", true);
			 formBean.setStrUserVal(strUserVal);
			
			 _mapProcedureParam3 = new LinkedHashMap<String, String>();
			 _mapProcedureParam3.put("hosp_code", vo.getStrHospitalCode());
			 _mapProcedureParam3.put("err", "#1");
			 _mapProcedureParam3.put("resultset", "#2");

			 strCounterVal = HisComboOptions.getOptionsFromProc("pkg_bill_view.PROC_GBLT_COUNTER_MST", _mapProcedureParam3, "0", "0^Select Value", true);
			 formBean.setStrCounterVal(strCounterVal);

			 formBean.setStrDayEndMode(bcu.getGeneralDayEndProcessType());
			 
			 bo.getDayEndProcess(vo);			 

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}			
			
			formBean.setStrCounterId(vo.getStrCounterId());
			formBean.setStrCounterName(vo.getStrCounterName());
			formBean.setStrUserName(vo.getStrUserName());
			formBean.setStrDayEndAmount(vo.getStrDayEndAmount());
			formBean.setStrDayEndCreditCol(vo.getStrDayEndCreditCol());
			formBean.setStrDayEndInstAmt(vo.getStrDayEndInstAmt());
			formBean.setStrDayEndTimeBoundFlag(vo.getStrDayEndTimeBoundFlag());
			formBean.setStrDayEndAllowedFlag(vo.getStrDayEndAllowedFlag());
			formBean.setStrDayEndAllowedTime(vo.getStrDayEndAllowedTime());
			formBean.setStrCurrentTime(vo.getStrCurrentTime());
			
			if(formBean.getStrBillModuleId().equals("1"))
			{
				formBean.setStrDateType(bcu.getGeneralDayEndDateType());
				formBean.setStrProcessType(bcu.getGeneralDayEndProcessType());
			}
			else
			{
				formBean.setStrDateType(bcu.getGeneralDayEndNonBillingDateType());
				formBean.setStrProcessType(bcu.getGeneralDayEndNonBillingProcessType());
			}			

			formBean.setStrCtDate(hisUtil.getASDate("dd-MMM-yyyy"));
			formBean.setStrPrintCount(bcu.getGeneralPrintMessageLimit());
			formBean.setStrIsDetailsRequired("1");
			formBean.setStrIsFooter("1");// TO PRINT HTML PAGE HEADER ON EACH PAGE AND PAGE NUMBER-AMIT ATERIA.FIRTHER MAPPING FOUND IN RPTDESIGN FILE
			formBean.setStrNegativeDayEndAllowed(bcu.getStrNegativeDayEndAllowed());
			formBean.setStrDenominationAllowed(bcu.getStrDenominationAllowed());
			formBean.setStrIsPayDetailRequired("1");
		} 
		catch (Exception e) 
		{
			String msgStr =  e.getMessage();
			HisException eObj = new HisException("Billing", "DayEndTransDATA->dayEndProcess()", msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{
			vo = null;
			bo = null;
			bcu = null;
			hisUtil = null;
		}
	}

	public static boolean addData(DayEndTransFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
		String msgStr = "";
		boolean bretVal = true;
		   
		DayEndTransBO bo = null;
		DayEndTransVO vo = null;

		BillConfigUtil bcu = null;
	
		try 
		{
			 bo = new DayEndTransBO();
			 vo = new DayEndTransVO();
			
			 bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
  
			if(request.getParameter("userMode") != null)
			{
				formBean.setStrUserMode(request.getParameter("userMode"));
			}
			else
			{
				formBean.setStrUserMode("1");
			}			
			if(request.getParameter("billModuleId") != null)
			{
				formBean.setStrBillModuleId(request.getParameter("billModuleId"));
			}
			else
			{
				formBean.setStrBillModuleId("1");
			}
			
			
			vo.setStrRemarks(formBean.getStrRemarks());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());			
			vo.setStrCounterId(formBean.getStrCounterId());
			vo.setStrCountId(formBean.getStrCountId());			
			vo.setStrUserMode(formBean.getStrUserMode());
			vo.setStrBillModuleId(formBean.getStrBillModuleId());
			vo.setStrUsrId(formBean.getStrUsrId());
			vo.setStrDayEndDate(formBean.getStrDayEndDate());
			
			formBean.setStrDayEndUserName(formBean.getStrUserName());
			formBean.setStrDayEndCounterName(formBean.getStrCounterName());
			
			vo.setStrDayEndAmount(formBean.getStrDayEndAmount());
			vo.setStrDayEndCounterName(formBean.getStrDayEndCounterName());
			vo.setStrDayEndUserName(formBean.getStrDayEndUserName());
			vo.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
			vo.setStrDayEndMode(bcu.getGeneralDayEndProcessType());
			vo.setStrTwoThousandNotes(formBean.getStrTwoThousandNotes());
			vo.setStrThousandNotes(formBean.getStrThousandNotes());
			vo.setStrFiveHundNotes(formBean.getStrFiveHundNotes());
			vo.setStrTwoHundNotes(formBean.getStrTwoHundNotes());
			vo.setStrHundNotes(formBean.getStrHundNotes());
			vo.setStrFiftyNotes(formBean.getStrFiftyNotes());
			vo.setStrTenNotes(formBean.getStrTenNotes());
			vo.setStrFiveNotes(formBean.getStrFiveNotes());
			vo.setStrTwoNotes(formBean.getStrTwoNotes());
			vo.setStrOneNotes(formBean.getStrOneNotes());
			vo.setStrtenCoins(formBean.getStrtenCoins());
			vo.setStrFiveCoins(formBean.getStrFiveCoins());
			vo.setStrTwoCoins(formBean.getStrTwoCoins());
			vo.setStrOneCoins(formBean.getStrOneCoins());			
			vo.setStrDayEndCreditCol(formBean.getStrDayEndCreditCol());
			vo.setStrDayEndInstAmt(formBean.getStrDayEndInstAmt());
			
			// bill module id  1: Billing, 2: Other Modules 
			// General Day End Process Type 1: User wise, 2: User + Counter Wise
			if( formBean.getStrBillModuleId().equals("1") && bcu.getGeneralDayEndProcessType().equals("1") )
			{				
				vo.setStrCountId("0");
				vo.setStrCounterId("0");
			}			 
			// General Day End Non Billing Process Type 1: User wise, 2: User + Counter Wise
			if( formBean.getStrBillModuleId().equals("2") && bcu.getGeneralDayEndNonBillingProcessType().equals("1") )
			{				
				vo.setStrCountId("0");
				vo.setStrCounterId("0");				
			}	
			// General Day End Combined Process Type 1: User wise, 2: User + Counter Wise
			if( formBean.getStrBillModuleId().equals("3") && bcu.getGeneralDayEndNonBillingProcessType().equals("1") )
			{				
				vo.setStrCountId("0");
				vo.setStrCounterId("0");				
			}
			
			bo.insertAddData(vo);
			
			formBean.setStrCounterName(vo.getStrCounterName());
			formBean.setStrSummNo(vo.getStrSummNo());
			// Check Error
			if (vo.getStrMsgType().equals("1")) 
			{
				formBean.setStrErrMsg(vo.getStrErrMsg());
				throw new Exception(vo.getStrMsgString());
			} 
			else 
			{
				if(formBean.getStrReportFormat().trim().toLowerCase().equals("text"))
				{
					PrintHLP.DAYEND_REPORT(formBean.getStrSummNo(), vo.getStrHospitalCode(), formBean.getStrBillModuleId(), formBean.getStrIsDetailsRequired(), formBean.getStrIsPayModeRequired(), formBean.getStrIsPayDetailRequired(), request , 0);
				}
				else
				{
					showReport(formBean, request, response);
				}				
				formBean.setStrCounterName("");
				formBean.setStrCounterId("");
			 	formBean.setStrMsgString(vo.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			 bretVal = false;
			 msgStr =  e.getMessage();
			 HisException eObj = new HisException("Billing", "DayEndTransDATA->addData()", msgStr);
			 formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			 formBean.setStrMsgType("1");
			 eObj = null;			
		} 
		finally 
		{
				vo = null;
				bo = null;
		}
		  return bretVal;
	}
	
	public static void showReport(DayEndTransFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				BillConfigUtil bcu =  new BillConfigUtil(formBean.getStrHospitalCode());
				Map<String, Object> params = new HashMap<String, Object>();
			
				try 
				{
					String strReportName = "Scroll-Day End Report";
					String strHospitalCode = formBean.getStrHospitalCode();
					String strReportId = formBean.getStrReportId();
					String strUserRemarks = formBean.getStrUserRemarks();
					String strSummNo = formBean.getStrSummNo();
					
					String strBillModuleId = request.getParameter("billModuleId");
					formBean.setStrBillModuleId(strBillModuleId);
					reportFormat = formBean.getStrReportFormat();
					boolean footerVisible = true;
					boolean counterVisible = true;
					
					boolean payModeVisible = true;
					boolean payDtlVisible = true;
					boolean logoVisible = true;
					
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
							logoVisible = false;							
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
						params.put("seatId", formBean.getStrSeatId());
						params.put("isLogoRequired", logoVisible);
				
						ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
					}
					else
					{
						//reg day end report
						String reportPath = "/billing/reports/dayend_billrptOthers.rptdesign";
						if(formBean.getStrIsDetailsRequired().equals("0"))
						{
							reportPath = "/billing/reports/dayend_billrptOthersWithutDtls.rptdesign";
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
						String  msgStr =  e.getMessage();
						HisException eObj = new HisException("Billing", "DayEndTransDATA->showReport()", msgStr);
						formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
						formBean.setStrMsgType("1");
						eObj = null;
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
						"DayEndTransDATA->rePrint()", e
								.getMessage());
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID() + "], Contact System Administrator!";

				eObj = null;

				try {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(response1);
				} catch (IOException e1) {

					new HisException("Billing",
							"DayEndTransDATA->rePrint()", e1
									.getMessage());
				}

			}
		}
	
	
		/**
		 * To get Pending Day End Details HLP
		 * 
		 * @param wrsData_p
		 * @param strWithOrWithoutRadioButton
		 * @return
		 * @throws SQLException
		 * @throws Exception
		 */
		private static String getPendingDayEndDetailsTable(WebRowSet wrsData_p,String strWithOrWithoutRadioButton)	throws SQLException,Exception
		{
			StringBuffer sbHeader = new StringBuffer(100);
			StringBuffer sbBody = new StringBuffer(100);
			int nWidth=40;
			int nColspan=2;
			int index=0;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			if(strWithOrWithoutRadioButton.equals("WITH_RADIO_BUTTON"))
			{
				nWidth = 33;
				nColspan = 3;
			}
			else if(strWithOrWithoutRadioButton.equals("WITHOUT_RADIO_BUTTON"))
			{
				nWidth = 40;
				nColspan = 2;
			}
				
			
			/*
			 * Header Row:
			 */
			sbHeader.append("<thead>");
			sbHeader.append("<tr>");
			if(strWithOrWithoutRadioButton.equals("WITH_RADIO_BUTTON")){
				sbHeader.append("<th class='LABEL' style='text-align: center;' width='23%'>Select</th>");	
			}
			
			sbHeader.append("<th class='LABEL' style='text-align: center;' width='33%'>Date </th>");
			sbHeader.append("<th class='LABEL thright' style='text-align: right;' width='43%'>Transaction Amount(<i class='fas fa-rupee-sign'></i>)</th>");
			sbHeader.append("</tr>");
			sbHeader.append("</thead>");
			sbBody.append("<tbody>");
			if (wrsData_p != null && wrsData_p.size() > 0) {		
				/* Result Index */
				// DAYEND_DATE: 1
				// DAYEND_AMOUNT: 2
				
				while (wrsData_p.next()) {

					String strDayEndDate = wrsData_p.getString("DAYEND_DATE");
					String strDayEndAmount = wrsData_p.getString("DAYEND_AMOUNT");
					
					
					if (strDayEndDate == null) {
						strDayEndDate = "---";
					}
					if (strDayEndAmount == null) {
						strDayEndAmount = "---";
					}else{
						strDayEndAmount = HisUtil.getAmountWithDecimal(strDayEndAmount, 2);
					}
					
					/*
					 * Table Body
					 */
					
					sbBody.append("<tr>");
					
					if(strWithOrWithoutRadioButton.equals("WITH_RADIO_BUTTON")){
						
						sbBody.append("<td class='CONTROL' style='text-align: center;' width='23%'>"
								+"<input type=\"radio\" name=\"strRadioButton\" value=\""+strDayEndDate+"\" data-dismiss='modal'  onclick=\"setDayEndDate('"+index+"');\">" + "</td>");
						
					}
					
					sbBody.append("<td class='CONTROL' style='text-align: center;' width='33%'>" + strDayEndDate + "</td>");
					sbBody.append("<td  class='CONTROL tdright' style='text-align: right;' width='43%'>" + strDayEndAmount + "</td>");
					
					index++;
					
					sbBody.append("</tr>"); 
					
				}

			} 
			else 
			{
				sbBody.append("<tr>");
				sbBody.append("<td colspan=\""	+ nColspan	+ "\" class=\"CONTROL\" style=\"text-align: center; color: red;\">No Data Found!</td>");
				sbBody.append("</tr>");
			}
			
			sbBody.append("</tbody>");
			return sbHeader.toString() + sbBody.toString();
		}


		/**
		 * 
		 * @param formBean	the DayEndTransFB
		 * @param request	the HttpServletRequest
		 */
		public static void pendingDayEndDetails(DayEndTransFB formBean,	HttpServletRequest request,HttpServletResponse response) 
		{
			// Creating Object for BO & VO.
			DayEndTransVO vo = null;
			DayEndTransBO bo = null;
			BillConfigUtil bcu = null;
			String strPendingDayEndDetailsTable="";
			
			
			try{
				 vo = new DayEndTransVO();
				 bo = new DayEndTransBO();
				 bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				 
				 String  strCounterId= request.getParameter("counterId");
				 
				  String strUserId = request.getParameter("userId");
				   
				 if(request.getParameter("userMode") != null)
				 {
						formBean.setStrUserMode(request.getParameter("userMode"));
				 }
				 else
				 {
						formBean.setStrUserMode("1");
				 }
				if(request.getParameter("billModuleId") != null)
				{
					formBean.setStrBillModuleId(request.getParameter("billModuleId"));
				}
				else
				{
					formBean.setStrBillModuleId("1");
				}
									
					formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
					formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
					//formBean.setStrUserName(request.getSession().getAttribute("userName").toString());
					formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
					
					if(formBean.getStrBillModuleId().equals("1")){
						
						formBean.setStrDateType(bcu.getGeneralDayEndDateType());
						formBean.setStrProcessType(bcu.getGeneralDayEndProcessType());
						
						
					}else{
						
						formBean.setStrDateType(bcu.getGeneralDayEndNonBillingDateType());
						formBean.setStrProcessType(bcu.getGeneralDayEndNonBillingProcessType());
					
						
					}
					
					vo.setStrHospitalCode(formBean.getStrHospitalCode());
					vo.setStrCounterId(strCounterId);
					vo.setStrSeatId( ( strUserId==null || strUserId.equals("") ) ? formBean.getStrSeatId():strUserId);
					vo.setStrDayEndProcess(formBean.getStrProcessType()); //User Wise OR User + Counter Wise
					
					
					if(formBean.getStrBillModuleId().equals("1"))	// billModuleId=1 =>Billing
					{
						vo.setStrMode("1");// modval=1
						bo.getPendingDayEndProcessBilling(vo);
						
					}
					else if(formBean.getStrBillModuleId().equals("2"))	// billModuleId=2 =>Non-Billing
					{
						vo.setStrMode("2"); // modval=2
						bo.getPendingDayEndProcessBilling(vo);
					}	
					
					else if(formBean.getStrBillModuleId().equals("3"))	// billModuleId=3 =>Combined Billing and Registration
					{
						vo.setStrMode("3"); // modval=3
						bo.getPendingDayEndProcessBilling(vo);
					}	
					
					if(formBean.getStrUserMode().equals("1"))	// userMode=1 =>Clerk
					{
						if(formBean.getStrDateType().equals("1"))	// Current Date
						{
							strPendingDayEndDetailsTable	=	getPendingDayEndDetailsTable(vo.getWrsData(),"WITHOUT_RADIO_BUTTON");
						}
						else if(formBean.getStrDateType().equals("2"))	// Back Date Allowed
						{
							strPendingDayEndDetailsTable	=	getPendingDayEndDetailsTable(vo.getWrsData(),"WITH_RADIO_BUTTON");
						}
					}
					else if(formBean.getStrUserMode().equals("2"))	// userMode=2 =>Supervisor
					{
						if(formBean.getStrDateType().equals("1"))	// Current Date
						{
							strPendingDayEndDetailsTable	=	getPendingDayEndDetailsTable(vo.getWrsData(),"WITHOUT_RADIO_BUTTON");
						}
						else if(formBean.getStrDateType().equals("2"))	// Back Date Allowed
						{
							strPendingDayEndDetailsTable	=	getPendingDayEndDetailsTable(vo.getWrsData(),"WITH_RADIO_BUTTON");
						}
					}
				
					//formBean.setStrPendingDayEndDetailsTable(strPendingDayEndDetailsTable);	
					
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strPendingDayEndDetailsTable);
			}
			catch (Exception e) {

				//e.printStackTrace();
				String msgStr =  e.getMessage();
				HisException eObj = new HisException("Billing", "DayEndTransDATA->dayEndProcess()", msgStr);
				formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				formBean.setStrMsgType("1");
				eObj = null;

			} finally {
					vo = null;
					bo = null;
					bcu = null;
				}		
		}
		
		public static void getDayEndAmountAjax(DayEndTransFB formBean,HttpServletRequest request,HttpServletResponse response) 
		{
			DayEndTransVO vo = null;
			DayEndTransBO bo = null;
			BillConfigUtil bcu = null;	
			
			try 
			{
				 vo = new DayEndTransVO();
				 bo = new DayEndTransBO();
				 bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());	
				
				String  strCounterId= request.getParameter("counterId");
				String  strDayEnddate= request.getParameter("dayEndDate");
				 
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		    	vo.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		    	vo.setStrDayEndMode(bcu.getGeneralDayEndProcessType());
		    	vo.setStrCounterId(strCounterId);
		    	vo.setStrDayEndDate(strDayEnddate);
		    
		    	bo.getDayEndAmountAjax(vo);
                formBean.setStrDayEndAmount(vo.getStrDayEndAmount());
				// Check Error
				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				}
				
				//write response..
				response.setHeader("Cache-Control", "no-cache");
				//response.getWriter().print(vo.getStrDayEndAmount());
			
				
				
				
				
			} catch (Exception e) {

				e.printStackTrace();
				String msgStr =  e.getMessage();
				HisException eObj = new HisException("Billing", "DayEndTransDATA->getDayEndAmountAjax()", msgStr);
				formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				formBean.setStrMsgType("1");
				eObj = null;

			} 
			finally 
			{
					vo = null;
					bo = null;
					bcu = null;
			}
		}
		
		public static void getDayEndAmountAjaxUserNCounterWise(DayEndTransFB formBean,
				HttpServletRequest request,HttpServletResponse response) 
		{
			// Creating Object for BO & VO.
			DayEndTransVO vo = null;
			DayEndTransBO bo = null;
			
			BillConfigUtil bcu = null;	
			
			HisUtil hisUtil = null;
			
			try {
				
				 vo = new DayEndTransVO();
				 bo = new DayEndTransBO();
				
				 bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());	
				 hisUtil = new HisUtil("Billing" , "DayEndTransDATA.dayEndProcess()");
				
				String  strCounterId= request.getParameter("counterId");
				String  strDayEnddate= request.getParameter("dayEndDate");
				 
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		    	vo.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
				vo.setStrSeatId(request.getParameter("userIdd"));
		    	vo.setStrDayEndMode(bcu.getGeneralDayEndProcessType());
		    	vo.setStrCounterId(strCounterId);
		    	vo.setStrDayEndDate(strDayEnddate);
		    	
		    	bo.getDayEndAmountAjax(vo);

				// Check Error
				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				}
				
				//write response..
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(vo.getStrDayEndAmount());
				
				
				
				
			} catch (Exception e) {

				e.printStackTrace();
				String msgStr =  e.getMessage();
				HisException eObj = new HisException("Billing", "DayEndTransDATA->getDayEndAmountAjaxUserNCounterWise()", msgStr);
				formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				formBean.setStrMsgType("1");
				eObj = null;

			} finally {
					vo = null;
					bo = null;
					bcu = null;
					hisUtil = null;
				}
		}
		public static void getDayEndCreditColAjax(DayEndTransFB formBean,HttpServletRequest request,HttpServletResponse response) 
		{
			DayEndTransVO vo = null;
			DayEndTransBO bo = null;
			BillConfigUtil bcu = null;	
			
			try 
			{
				 vo = new DayEndTransVO();
				 bo = new DayEndTransBO();
				 bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());	
				
				String  strCounterId= request.getParameter("counterId");
				String  strDayEnddate= request.getParameter("dayEndDate");
				 
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		    	vo.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		    	vo.setStrDayEndMode(bcu.getGeneralDayEndProcessType());
		    	vo.setStrCounterId(strCounterId);
		    	vo.setStrDayEndDate(strDayEnddate);
		    
		    	bo.getDayEndCreditColAjax(vo);
		    	formBean.setStrDayEndCreditCol(vo.getStrDayEndCreditCol());

				// Check Error
				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				}
				
				//write response..
				response.setHeader("Cache-Control", "no-cache");
				//response.getWriter().print(vo.getStrDayEndCreditCol());
			
				
				
				
				
			} catch (Exception e) {

				e.printStackTrace();
				String msgStr =  e.getMessage();
				HisException eObj = new HisException("Billing", "DayEndTransDATA->getDayEndCreditColAjax()", msgStr);
				formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				formBean.setStrMsgType("1");
				eObj = null;

			} 
			finally 
			{
					vo = null;
					bo = null;
					bcu = null;
			}
		}
		public static void getDayEndInstAmtAjax(DayEndTransFB formBean,HttpServletRequest request,HttpServletResponse response) 
		{
			DayEndTransVO vo = null;
			DayEndTransBO bo = null;
			BillConfigUtil bcu = null;	
			
			try 
			{
				 vo = new DayEndTransVO();
				 bo = new DayEndTransBO();
				 bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());	
				
				String  strCounterId= request.getParameter("counterId");
				String  strDayEnddate= request.getParameter("dayEndDate");
				 
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		    	vo.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		    	vo.setStrDayEndMode(bcu.getGeneralDayEndProcessType());
		    	vo.setStrCounterId(strCounterId);
		    	vo.setStrDayEndDate(strDayEnddate);
		    
		    	bo.getDayEndInstAmtAjax(vo);
                formBean.setStrDayEndInstAmt(vo.getStrDayEndInstAmt());
				// Check Error
				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				}
				
				//write response..
				response.setHeader("Cache-Control", "no-cache");
				//response.getWriter().print(vo.getStrDayEndAmount());
			
				
				
				
				
			} catch (Exception e) {

				e.printStackTrace();
				String msgStr =  e.getMessage();
				HisException eObj = new HisException("Billing", "DayEndTransDATA->getDayEndInstAmtAjax()", msgStr);
				formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				formBean.setStrMsgType("1");
				eObj = null;

			} 
			finally 
			{
					vo = null;
					bo = null;
					bcu = null;
			}
		}
		
}


