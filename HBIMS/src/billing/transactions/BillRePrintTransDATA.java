package billing.transactions;

import hisglobal.exceptions.HisException;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billing.BillConfigUtil;
import billing.PrintHLP;

public class BillRePrintTransDATA {

	// from bala

	public static void getBillListingDtls(HttpServletRequest request,
			HttpServletResponse response, BillRePrintTransFB formBean) {
		BillRePrintTransVO voObj = null;
		BillRePrintTransBO bo = null;
		try {
			voObj = new BillRePrintTransVO();
			bo = new BillRePrintTransBO();
			
			String strCase = request.getParameter("CASE");
			String strSearchString = request.getParameter("searchString");
			String strSearchType  = request.getParameter("searchType");
			String strFromRow = request.getParameter("fromRow");
			String strRowPerPage = request.getParameter("rowPerPage");
			String strCtBlockSet = request.getParameter("ctBlockSet");
			String strFromDate = request.getParameter("fromDate");
			String strToDate = request.getParameter("toDate");

			if (strSearchString.contains("^")) {
				strSearchString = strSearchString.replace('^', '%');
			}

			voObj.setStrCase(strCase);
			voObj.setStrSearchString(strSearchString);
			voObj.setStrSearchType(strSearchType);
			voObj.setStrFromDate(strFromDate);
			voObj.setStrToDate(strToDate);
			voObj.setStrBillFromRow(strFromRow);
			voObj.setStrHosCode(formBean.getStrHosCode());

			int nToRow = Integer.parseInt(strFromRow)
					+ Integer.parseInt(strRowPerPage) * 10;

			voObj.setStrBillToRow(String.valueOf(nToRow));
			voObj.setStrBillRowPerPage(strRowPerPage);
			voObj.setStrBillCtBlockSet(strCtBlockSet);

			bo.getBillListingDtl(voObj);

			if (voObj.getStrMsgType().equals("0")) {
				String val = "";
				if (strCase.equals("1")) {
					 
					val = BillRePrintTransHLP.getBillListingView1(voObj);
					val = val.concat("@");
					val = val.concat("");

				}
				if (strCase.equals("2")) {
					val = BillRePrintTransHLP.getBillListingView2(voObj);
					val = val.concat("@");
					val = val.concat("");

				}
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(val);

			} else {
				throw new Exception(voObj.getStrMsgString());

			}

		} catch (Exception e) {
				e.printStackTrace();
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"BillRePrintTransDATA->billApprovalDtl()", msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			bo = null;

			voObj = null;
		}
	}

	/**
	 * updatePrintStatus(request,response) -- > This Method generate the Ajax
	 * Response to Get Print Status
	 * 
	 * @param request
	 * @param response
	 */
	public static void updatePrintStatus(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String strData = request.getParameter("modName");
			String[] id = strData.split("\\^");

			String strBillNo = id[0];
			String strReceiptNo = id[1];
			String strPrintFlag = id[2];

			if (strBillNo == null)
				strBillNo = "0";
			if (strReceiptNo == null)
				strReceiptNo = "0";
			if (strPrintFlag == null)
				strPrintFlag = "0";
			PrintHLP.updatePrintStatus(strBillNo, strReceiptNo, request
					.getSession().getAttribute("HOSPITAL_CODE").toString(),
					strPrintFlag);

		} catch (Exception e) {
			HisException eObj = new HisException("Billing",
					"BillRe-PrintTransDATA->updatePrintStatus()", e
							.getMessage());
			String response1 = "ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator!";

			eObj = null;

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(response1);
			} catch (IOException e1) {
				new HisException("Billing",
						"BillRePrintTransDATA->updatePrintStatus()", e1
								.getMessage());
			}

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
						"BillRePrintTransDATA->rePrint()", e
								.getMessage());
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID() + "], Contact System Administrator!";

				eObj = null;

				try {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(response1);
				} catch (IOException e1) {

					new HisException("Billing",
							"BillRePrintTransDATA->rePrint()", e1
									.getMessage());
				}

			}
		}
		
	
	
	/**
	 * GO(request,response,formBean) -- > This Method generate the Ajax Response
	 * for Bill Details For Bill-Re Print Transaction
	 * 
	 * @param request
	 * @param response
	 * @param formBean
	 */
	public static void GO(HttpServletRequest request,
			HttpServletResponse response, BillRePrintTransFB formBean) {
		// Declaring Variables
		String strData = null;
		String strBillNo = null;
		String strRcptNo = null;
		// String strTariffDtl = "";
		// String strTariffDtl1 = "";
		String strmsgText = "";
		// String[] data = null;
		String strRes = null;
		String strBillDtl = null;

		// Creating Object for BO & VO.
		BillRePrintTransVO vo = null;
		BillRePrintTransBO bo = null;
		try {

			vo = new BillRePrintTransVO();
			bo = new BillRePrintTransBO();
			vo.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE")
					.toString());
			// Here we Split Data
			strData = request.getParameter("modName");

			String[] id = strData.split("\\^");

			strBillNo = id[0];
			strRcptNo = id[1];
			// System.out.println("Bill No::-->>"+strBillNo);
			// System.out.println("Rcpt No:::-->>>"+strRcptNo);
			formBean.setStrBillNo(strBillNo);
			vo.setStrBillNo(strBillNo);

			vo.setStrRcptNo(strRcptNo);

			// Calling TariffDtls
			bo.getBillDetails(vo);
			if (vo.getStrMsgType().equals("0")) {
				if (vo.getBillDetails() != null
						&& vo.getBillDetails().size() > 0) {
					strBillDtl = BillRePrintTransHLP.patientDtlBillDetail(vo
							.getBillDetails(), vo.getStrBillNo(), vo
							.getStrRcptNo());
					String[] TestData = strBillDtl.split("\\####");
					response.setHeader("Cache-Control", "no-cache");
					formBean.setStrBillDetl(TestData[0]);
					strRes = TestData[0];
				} else {
					strRes = "ERROR####Invalid Bill No!!!";
				}

				response.getWriter().print(strRes);
			} else {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"BillRePrintTransDATA->GO()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing", "BillRePrintTransDATA->GO()",
						strmsgText + "-->" + e1.getMessage());
			}

		} finally {

			vo = null;

		}
	}

	/**
	 * getInsertBillDtls(formBean) -- > Insert Logic for Client Approval Page
	 * 
	 * @param response
	 * @param request
	 * @param request
	 * @param response
	 * @param form
	 */
	public static boolean getInsertBillDtls(BillRePrintTransFB formBean , HttpServletRequest request) {

		// Declaring Variables
		boolean fretValue = true;
		// String strErrmsg = "";
		String strmsgText = "";
		// String strMsg = "";
		// String strWarning = "";
		// Creating VO & BO Object
		BillRePrintTransVO vo = null;
	//	BillRePrintTransBO bo = null;

		try {
			vo = new BillRePrintTransVO();
			//bo = new BillRePrintTransBO();
			// Set value into VO
			vo.setStrRcptNo(formBean.getStrRcptNo());
			vo.setStrRcptType(formBean.getStrRcptType());
			
			if(formBean.getStrBillNo().startsWith("5")){
				
				vo.setStrPreviousPukNo(formBean.getStrPukNo());
				vo.setStrPukNo("0");
				
			}else{
				vo.setStrPreviousPukNo("0");
				vo.setStrPukNo(formBean.getStrPukNo());
			}
			
			
			vo.setStrBillNo(formBean.getStrBillNo());
			vo.setStrPatientName(formBean.getStrPatientName());
			vo.setStrCategoryName(formBean.getStrCategoryName());
			vo.setStrBServiceId(formBean.getStrBServiceId());
			vo.setStrRePrintChg(formBean.getStrRePrintChg());
			vo.setStrBillService(BillConfigUtil.BILL_MODULE_ID);
			vo.setStrChargeTypeID(formBean.getStrChargeTypeID());
			vo.setStrPatientCatCode(formBean.getStrPatientCatCode());
			vo.setStrIpAddr(formBean.getStrIpAddr());
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrHosCode(formBean.getStrHosCode());
			vo.setStrPatActNo(formBean.getStrPatActNo());
			vo.setStrAdmnNo(formBean.getStrAdmnNo());
			vo.setStrDeptCode(formBean.getStrDeptCode());
			vo.setStrWardCode(formBean.getStrWardCode());
			vo.setStrBillTypeCombo(formBean.getStrBillTypeCombo());
			// Calling BO insert method
			/*
			 * fretValue = bo.insertBillDtls(vo);
			 * if(vo.getStrMsgType().equals("1")) { throw new
			 * Exception(vo.getStrMsgString()); } else {
			 */
			PrintHLP.findPrintType1(vo.getStrBillNo(), vo.getStrBServiceId(),
					vo.getStrPatActNo(), vo.getStrHosCode(), vo
							.getStrBillTypeCombo(), vo.getStrRcptNo(), vo
							.getStrRcptType() , request , null , "0" , 0,"");
			formBean.setStrTempBillNo(vo.getStrBillNo());
			formBean.setStrTempReceiptNo(vo.getStrRcptNo());
			formBean.setStrPrintFlag("1");
			formBean.setStrMsg("Bill Successfully Printed!!!!");
			formBean.setStrMsgType(vo.getStrMsgType());
		} catch (Exception e) {
			fretValue = false;
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"IpdBillManagementTransDATA->InserRecord()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			
		}
		return fretValue;
	}

}
