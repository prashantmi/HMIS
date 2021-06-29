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

public class DayEndCashHandoverTransDATA {

	public static void dayEndProcess(DayEndCashHandoverTransFB formBean,HttpServletRequest request) 
	{
		String strUserVal = "";
		String strCounterVal = "";
		LinkedHashMap<String, String> _mapProcedureParam2 = null;
		LinkedHashMap<String, String> _mapProcedureParam3 = null;
		
		DayEndCashHandoverTransVO vo = null;
		DayEndCashHandoverTransBO bo = null;
		
		BillConfigUtil bcu = null;	
		
		HisUtil hisUtil = null;
		
		try 
		{
			 vo = new DayEndCashHandoverTransVO();
			 bo = new DayEndCashHandoverTransBO();
			
			 bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());	
			 hisUtil = new HisUtil("Billing" , "DayEndCashHandoverTransDATA.dayEndProcess()");
			
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
			formBean.setStrCashHandoverToValues(hisUtil.getOptionValue(vo.getStrCashHandoverToWS(), "","0^Select Value",false));
			
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
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String msgStr =  e.getMessage();
			HisException eObj = new HisException("Billing", "DayEndCashHandoverTransDATA->dayEndProcess()", msgStr);
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

	public static boolean addData(DayEndCashHandoverTransFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
		String msgStr = "";
		boolean bretVal = true;
		   
		DayEndCashHandoverTransBO bo = null;
		DayEndCashHandoverTransVO vo = null;

		BillConfigUtil bcu = null;
	
		try 
		{
			 bo = new DayEndCashHandoverTransBO();
			 vo = new DayEndCashHandoverTransVO();
			
			 bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
  
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrCashHandOverAmount(formBean.getStrCashHandOverAmount());
			vo.setStrCashHandoverTo(formBean.getStrCashHandoverTo());
			vo.setStrRefNo(formBean.getStrRefNo());
			vo.setStrHandOverToName(formBean.getStrHandOverToName());
			vo.setStrChk(formBean.getStrChk());
			
			bo.insertAddData(vo);
			
			// Check Error
			if (vo.getStrMsgType().equals("1")) 
			{
				formBean.setStrErrMsg(vo.getStrErrMsg());
				throw new Exception(vo.getStrMsgString());
			} 
			else
			{
				formBean.setStrMsg("Day End Amount Handed Over Successfully");
			}
			/*else 
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
			}*/
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			 bretVal = false;
			 msgStr =  e.getMessage();
			 HisException eObj = new HisException("Billing", "DayEndCashHandoverTransDATA->addData()", msgStr);
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
	
	public static void showReport(DayEndCashHandoverTransFB formBean,HttpServletRequest request, HttpServletResponse response) 
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
						HisException eObj = new HisException("Billing", "DayEndCashHandoverTransDATA->showReport()", msgStr);
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
						"DayEndCashHandoverTransDATA->rePrint()", e
								.getMessage());
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID() + "], Contact System Administrator!";

				eObj = null;

				try {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(response1);
				} catch (IOException e1) {

					new HisException("Billing",
							"DayEndCashHandoverTransDATA->rePrint()", e1
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
		private static String getPendingDayEndDetailsTable(WebRowSet wrsData_p)	throws SQLException,Exception
		{
			StringBuffer sbHeader = new StringBuffer(100);
			StringBuffer sbBody = new StringBuffer(100);
			int index=0;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			
			
			/*
			 * Header Row:
			 */
			sbHeader.append("<thead>");
			sbHeader.append("<tr>");
/*			sbHeader.append("<th><input type='checkbox' name='chkmain' onclick='checkall(this);' onkeypress='checkallevent(event,this)'></th>");	
*/		    sbHeader.append("<th>#</th>");	
			sbHeader.append("<th>User Name </th>");
			sbHeader.append("<th>Scroll No.</th>");
			sbHeader.append("<th>Date</th>");
			sbHeader.append("<th>Cost Type</th>");
			sbHeader.append("<th>Payment Mode</th>");
			sbHeader.append("<th>Amount(Rs.)</th>");
			sbHeader.append("</tr>");
			sbHeader.append("</thead>");
			sbHeader.append("</body>");
			if (wrsData_p != null && wrsData_p.size() > 0) {		
				/* Result Index */
				// DAYEND_DATE: 1
				// DAYEND_AMOUNT: 2
				
				while (wrsData_p.next()) {

					String strUserName = wrsData_p.getString("USERNAME");
					String strScrollNo = wrsData_p.getString("SCROLL_NO");
					String strDayEndDate = wrsData_p.getString("SM_DATE_DATE");
					String strDayEndAmount = wrsData_p.getString("DAYEND_AMOUNT");
					String strCostTypeName = wrsData_p.getString("COSTTYPENAME");
					String strPayModeName = wrsData_p.getString("PAYMODENAME");
					
					
					
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
					sbBody.append("<td><input type='checkbox' name='strChk' value='"+strScrollNo+"' id='strChk"+index+"' class='chkclass' /></td>");
					sbBody.append("<td>" + strUserName + "</td>");
					sbBody.append("<td>" + strScrollNo.replace("^", "#").split("#")[0] + "</td>");
					sbBody.append("<td>" + strDayEndDate + "</td>");
					sbBody.append("<td>" + strCostTypeName + "</td>");
					sbBody.append("<td>" + strPayModeName+ "</td>");					
					sbBody.append("<td class='sum wagein'><a class='btn btn-info viewbill'>" + strDayEndAmount + "</a></td>");
					
					index++;
					sbBody.append("</tr>"); 
					
				}
			} 
			else 
			{
				
			//	sbBody.append("<div class='alert alert-danger'>No Data Found!</div>");
				
			}
			sbHeader.append("</tbody>");
			
			return sbHeader.toString() + sbBody.toString();
		}


		/**
		 * 
		 * @param formBean	the DayEndCashHandoverTransFB
		 * @param request	the HttpServletRequest
		 */
		public static void pendingDayEndDetails(DayEndCashHandoverTransFB formBean,	HttpServletRequest request,HttpServletResponse response) 
		{
			// Creating Object for BO & VO.
			DayEndCashHandoverTransVO vo = null;
			DayEndCashHandoverTransBO bo = null;
			BillConfigUtil bcu = null;
			String strPendingDayEndDetailsTable="";
			
			
			try{
				 vo = new DayEndCashHandoverTransVO();
				 bo = new DayEndCashHandoverTransBO();
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
						vo.setStrMode("4");// modval=1
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
							strPendingDayEndDetailsTable	=	getPendingDayEndDetailsTable(vo.getWrsData());
						}
						else if(formBean.getStrDateType().equals("2"))	// Back Date Allowed
						{
							strPendingDayEndDetailsTable	=	getPendingDayEndDetailsTable(vo.getWrsData());
						}
					}
					else if(formBean.getStrUserMode().equals("2"))	// userMode=2 =>Supervisor
					{
						if(formBean.getStrDateType().equals("1"))	// Current Date
						{
							strPendingDayEndDetailsTable	=	getPendingDayEndDetailsTable(vo.getWrsData());
						}
						else if(formBean.getStrDateType().equals("2"))	// Back Date Allowed
						{
							strPendingDayEndDetailsTable	=	getPendingDayEndDetailsTable(vo.getWrsData());
						}
					}
				
					//formBean.setStrPendingDayEndDetailsTable(strPendingDayEndDetailsTable);	
					
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strPendingDayEndDetailsTable);
			}
			catch (Exception e) {

				e.printStackTrace();
				String msgStr =  e.getMessage();
				HisException eObj = new HisException("Billing", "DayEndCashHandoverTransDATA->dayEndProcess()", msgStr);
				formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				formBean.setStrMsgType("1");
				eObj = null;

			} finally {
					vo = null;
					bo = null;
					bcu = null;
				}		
		}
		
		public static void getDayEndAmountAjax(DayEndCashHandoverTransFB formBean,HttpServletRequest request,HttpServletResponse response) 
		{
			DayEndCashHandoverTransVO vo = null;
			DayEndCashHandoverTransBO bo = null;
			BillConfigUtil bcu = null;	
			
			try 
			{
				 vo = new DayEndCashHandoverTransVO();
				 bo = new DayEndCashHandoverTransBO();
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
				HisException eObj = new HisException("Billing", "DayEndCashHandoverTransDATA->getDayEndAmountAjax()", msgStr);
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
		
		public static void getDayEndAmountAjaxUserNCounterWise(DayEndCashHandoverTransFB formBean,
				HttpServletRequest request,HttpServletResponse response) 
		{
			// Creating Object for BO & VO.
			DayEndCashHandoverTransVO vo = null;
			DayEndCashHandoverTransBO bo = null;
			
			BillConfigUtil bcu = null;	
			
			HisUtil hisUtil = null;
			
			try {
				
				 vo = new DayEndCashHandoverTransVO();
				 bo = new DayEndCashHandoverTransBO();
				
				 bcu = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());	
				 hisUtil = new HisUtil("Billing" , "DayEndCashHandoverTransDATA.dayEndProcess()");
				
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
				HisException eObj = new HisException("Billing", "DayEndCashHandoverTransDATA->getDayEndAmountAjaxUserNCounterWise()", msgStr);
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
		public static void getDayEndCreditColAjax(DayEndCashHandoverTransFB formBean,HttpServletRequest request,HttpServletResponse response) 
		{
			DayEndCashHandoverTransVO vo = null;
			DayEndCashHandoverTransBO bo = null;
			BillConfigUtil bcu = null;	
			
			try 
			{
				 vo = new DayEndCashHandoverTransVO();
				 bo = new DayEndCashHandoverTransBO();
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
				HisException eObj = new HisException("Billing", "DayEndCashHandoverTransDATA->getDayEndCreditColAjax()", msgStr);
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
		public static void getDayEndInstAmtAjax(DayEndCashHandoverTransFB formBean,HttpServletRequest request,HttpServletResponse response) 
		{
			DayEndCashHandoverTransVO vo = null;
			DayEndCashHandoverTransBO bo = null;
			BillConfigUtil bcu = null;	
			
			try 
			{
				 vo = new DayEndCashHandoverTransVO();
				 bo = new DayEndCashHandoverTransBO();
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
				HisException eObj = new HisException("Billing", "DayEndCashHandoverTransDATA->getDayEndInstAmtAjax()", msgStr);
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

		/*public static void OfflineMode(DayEndCashHandoverTransFB formBean, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			response.getWriter().print("herllo");
			
			
			
		}
		*/
}


