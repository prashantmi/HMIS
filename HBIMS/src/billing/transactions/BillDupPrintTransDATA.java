package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billing.BillConfigUtil;
import billing.PrintHLP;
import billing.HLPTariffDtl;

public class BillDupPrintTransDATA 
{
	public static void getBillListingDtls(HttpServletRequest request,
			HttpServletResponse response, BillDupPrintTransFB formBean) 
	{
		BillDupPrintTransVO voObj = null;
		BillDupPrintTransBO bo = null;
		
		BillConfigUtil billConfigUtil = null;
		
		try 
		{
			voObj = new BillDupPrintTransVO();
			bo = new BillDupPrintTransBO();
			voObj.setStrHosCode(formBean.getStrHosCode());
			billConfigUtil = new BillConfigUtil(voObj.getStrHosCode());
			 
			String strCase = request.getParameter("CASE");
			String strSearchString = request.getParameter("searchString");
			String strSearchType  = request.getParameter("searchType");
			String strFromRow = request.getParameter("fromRow");
			String strRowPerPage = request.getParameter("rowPerPage");
			String strCtBlockSet = request.getParameter("ctBlockSet");
			String strFromDate = request.getParameter("fromDate");
			String strToDate = request.getParameter("toDate");

			if (strSearchString.contains("^")) 
			{
				strSearchString = strSearchString.replace('^', '%');
			}
			
			voObj.setStrDuplicateMode(billConfigUtil.getGeneralDuplicatePrint());
			voObj.setStrCase(strCase);
			voObj.setStrSearchString(strSearchString);
			voObj.setStrSearchType(strSearchType);
			voObj.setStrFromDate(strFromDate);
			voObj.setStrToDate(strToDate);
			voObj.setStrBillFromRow(strFromRow);
			voObj.setStrHosCode(formBean.getStrHosCode());

			int nToRow = Integer.parseInt(strFromRow)+ Integer.parseInt(strRowPerPage) * 10;

			voObj.setStrBillToRow(String.valueOf(nToRow));
			voObj.setStrBillRowPerPage(strRowPerPage);
			voObj.setStrBillCtBlockSet(strCtBlockSet);

			bo.getBillListingDtl(voObj);

			if (voObj.getStrMsgType().equals("0"))
			{
				String val = "";
				if (strCase.equals("1")) 
				{
					val = BillDupPrintTransHLP.getBillListingView1(voObj);
					val = val.concat("@");
					val = val.concat("");

				}
				if (strCase.equals("2"))
				{
					val = BillDupPrintTransHLP.getBillListingView2(voObj);
					val = val.concat("@");
					val = val.concat("");

				}
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(val);

			} else 
			{
				throw new Exception(voObj.getStrMsgString());

			}

		} catch (Exception e) 
		{
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"BillDupPrintTransDATA->getBillListingDtls()", msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally 
		{
			bo = null;
			voObj = null;
		}
	}

	/**
	 * GO(request,response,formBean) -- > This Method generate the Ajax Response
	 * for Bill Details
	 * 
	 * @param request
	 * @param response
	 * @param form
	 */
	public static void GO(HttpServletRequest request,HttpServletResponse response, BillDupPrintTransFB formBean) 
	{
		String strData = null;
		String strRecNo = null;
		// String strTariffDtl = "";
		// String strTariffDtl1 = "";
		String strmsgText = "";
		// String[] data = null;
		String hosp_code = null;
		String strRes = null;
		String strResReg = null;
		String strBillDtl = null;
		String strBillDtlReg = null;
        String strRecType = null;
		BillDupPrintTransVO vo = null;
		BillDupPrintTransBO bo = null;
		BillConfigUtil billConfigUtil = null;
		HisUtil hisutil = null;
		
		try 
		{
			vo = new BillDupPrintTransVO();
			bo = new BillDupPrintTransBO();
			hosp_code=request.getSession().getAttribute("HOSPITAL_CODE").toString();
		    vo.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			billConfigUtil = new BillConfigUtil(vo.getStrHosCode());
			
			hisutil = new HisUtil("transaction", "BillDupPrintTransData");
			
			strData = request.getParameter("modName");
			strRecNo = request.getParameter("recNo"); 
			formBean.setStrBillNo(strData.replace("^", "#").split("#")[0]);
			formBean.setStrRcptNo(strRecNo);
			
			vo.setStrBillNo(formBean.getStrBillNo());
			vo.setStrRcptNo(strRecNo);
			
			vo.setStrDuplicateMode(billConfigUtil.getGeneralDuplicatePrint().trim());
		
			if(billConfigUtil.getGeneralDuplicatePrint().trim().equals("1"))
			{
				vo.setStrRcptNo("0");
				formBean.setStrRcptNo("0");
			}
			if(!strData.contains(hosp_code))
			{
				vo.setStrMsgType("3");
				throw new Exception(vo.getStrMsgString());
			}
			strRecType=strData.replace(hosp_code, "#").split("#")[1];
		
			//System.out.println("strRecType"+strRecType);
			if(strRecType.startsWith("1")==true)// Registration Bill
			{
				bo.getBillDetailsReg(vo);
			}
			else // Billing Bill
			{
				bo.getBillDetails(vo);
			}
			
			vo.setStrReceiptTypevalues(hisutil.getOptionValue(vo.getReceiptTypeWb(), "0", "0^Select Value", true));
			
			if (vo.getBillDetails()!= null && vo.getBillDetails().size() > 0) 
			{
				if (vo.getStrMsgType().equals("0")) 
				{					
					if(billConfigUtil.getGeneralDuplicatePrint().trim().equals("1"))
					{						
						strBillDtl = BillDupPrintTransHLP.patientDtlBillDetail(vo.getBillDetails(), vo.getStrBillNo(),vo.getStrHosCode());
					}
					else
					{
						strBillDtl = BillDupPrintTransHLP.patientDtlBillDetail(vo.getBillDetails(), vo.getStrBillNo(), vo.getStrRcptNo(),vo.getStrHosCode());
					}				
					
					String strTariffDtl = BillDupPrintTransHLP.getTariffDetails(vo.getTariffDetails());
					
					String[] TestData = strBillDtl.split("\\####");
					response.setHeader("Cache-Control", "no-cache");
					formBean.setStrBillDetl(TestData[0]);
					strRes = TestData[0];
					response.getWriter().print(strRes+""+strTariffDtl);
					
				} 
				else 
				{
					throw new Exception(vo.getStrMsgString());
				}
			} 
			else 
			{
				vo.setStrMsgType("3");
				throw new Exception(vo.getStrMsgString());
			}
			
		} 
	catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				String response1 = "";
				HisException eObj = new HisException("Billing",
						"BillDupPrintTransDATA->GO()", strmsgText);
				if (vo.getStrMsgType().equals("3"))
					response1 = "ERROR####Invalid Bill No.";
				else
					response1 = "ERROR####Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing", "BillDupPrintTransDATA->GO()", e1
						.getMessage()
						+ "-->" + strmsgText);
			}

		} finally
		{

			vo = null;
			bo = null;

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
	public static boolean getInsertBillDtls(BillDupPrintTransFB formBean , HttpServletRequest request) 
	{
		boolean fretValue = true;
		String strmsgText = "";
		BillDupPrintTransVO vo = null;
		BillDupPrintTransBO bo = null;
		BillConfigUtil billConfigUtil = null;
		
		try 
		{
			vo = new BillDupPrintTransVO();
			bo = new BillDupPrintTransBO();
			vo.setStrHosCode(formBean.getStrHosCode());
			billConfigUtil = new BillConfigUtil(vo.getStrHosCode());
			 
			
			// for the without CR No Process if bill no starts with 5 then previous CR No will be inserted in the tables 
			// else CR No. will be inserted
			
			if(formBean.getStrBillNo().startsWith("5"))
			{
				vo.setStrPreviousPukNo(formBean.getStrPukNo());
				vo.setStrPukNo("0");
			}
			else
			{
				vo.setStrPreviousPukNo("0");
				vo.setStrPukNo(formBean.getStrPukNo());
			}			
			
			vo.setStrBillNo(formBean.getStrBillNo());
			vo.setStrRcptNo(formBean.getStrRcptNo());
			vo.setStrRcptType(formBean.getStrRcptType());
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
			fretValue = bo.insertBillDtls(vo);
			// System.out.println("vo.getStrBillNo()"+vo.getStrBillNo());

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} 
			else 
			{
				//since in setup file there is only one option of 'replica of original(value 2)' for billConfigUtil.getGeneralDuplicatePrint()..
				//thus vl always go in els..
				
				if(billConfigUtil.getGeneralDuplicatePrint().trim().equals("1"))
				{
					PrintHLP.findPrintType(vo.getStrBillNo(),vo.getStrBServiceId(), vo.getStrPatActNo(), vo.getStrHosCode(), vo.getStrBillTypeCombo() , request ,0); 
				}
				else
				{
					vo.setStrRcptNo(formBean.getStrRcptNo());
					
					if(formBean.getTemp_wallet().equals("-1"))
									PrintHLP.findPrintType1(vo.getStrBillNo(), vo.getStrBServiceId(), vo.getStrPatActNo(),
							                vo.getStrHosCode(), vo.getStrBillTypeCombo(), vo.getStrRcptNo(), vo.getStrRcptType() , 
							                request , null , "0" , 1,formBean.getStrReceiptTypeLabel());		
					else
						PrintHLP.findPrintType1(vo.getStrBillNo(), "100", vo.getStrPatActNo(),
				                vo.getStrHosCode(), vo.getStrBillTypeCombo(), vo.getStrRcptNo(), vo.getStrRcptType() , 
				                request , null , "0" , 1,formBean.getStrReceiptTypeLabel());	
						
				}
				
				formBean.setStrMsg("Bill No." + vo.getStrBillNo() + "/"	+ vo.getStrRcptNo() + " is Saved/Printed ");
				formBean.setStrTempBillNo(vo.getStrBillNo());
				formBean.setStrTempRcptNo(vo.getStrRcptNo());
				formBean.setStrMsgType(vo.getStrMsgType());
				
				String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH")+ "/"+ "Billing"+ request.getSession().getAttribute("SEATID").toString() + ".dat";
				formBean.setFilePath(fileName);
				request.setAttribute("filePath", fileName);
				formBean.setIsOpenPopUp("1");
			}

		} catch (Exception e) 
		{
			fretValue = false;
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"BillDupPrintTransDATA->getInsertBillDtls()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally 
		{

			vo = null;

			bo = null;
		}
		return fretValue;
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
	public static boolean getUpdateBillDtls(BillDupPrintTransFB formBean,
			HttpServletRequest request) {

		// Declaring Variables
		boolean fretValue = true;
		// String strErrmsg = "";
		String strmsgText = "";
		// String strMsg = "";
		// String strWarning = "";
		// Creating VO & BO Object
		BillDupPrintTransVO vo = null;
	//	BillDupPrintTransBO bo = null;
		String strBillNo = "";
		String strRecieptNo = "";
		String strHospitalCode = "";

		try {
			vo = new BillDupPrintTransVO();
		
			// Set value into VO
			strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			strBillNo = request.getParameter("billNo");
			strRecieptNo = request.getParameter("recieptNo");
			// /System.out.println("data strBillNo"+strBillNo);
			// System.out.println("data strRecieptNo"+strRecieptNo);

			PrintHLP.updatePrintStatus(strBillNo, strRecieptNo,
					strHospitalCode, "0");

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			} 
			else 
			{

				formBean.setStrMsg("Bill No." + strBillNo + "/" + strRecieptNo + "is Printed ");
				formBean.setStrTempBillNo(vo.getStrBillNo());
				formBean.setStrTempRcptNo(vo.getStrRcptNo());
				formBean.setStrMsgType(vo.getStrMsgType());
			}

		} catch (Exception e) 
		{
			fretValue = false;
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"BillDupPrintTransDATA->updateBillDtls()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

		}
		return fretValue;
	}


	/**
	 * Re-Print's the bill.
	 * @param request
	 * @param response
	 */
		public static void rePrint(HttpServletRequest request,HttpServletResponse response) 
		{

			try 
			{

				PrintHLP.reprintFile(request);

			} catch (Exception e) 
			{

				HisException eObj = new HisException("Billing",
						"BillDupPrintTransDATA->rePrint()", e
								.getMessage());
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID() + "], Contact System Administrator!";

				eObj = null;

				try {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(response1);
				} catch (IOException e1) {

					new HisException("Billing",
							"BillDupPrintTransDATA->rePrint()", e1
									.getMessage());
				}

			}
		}
		
		public static void GET_INITIAL_DATA(HttpServletRequest request,
				HttpServletResponse response, BillDupPrintTransFB formBean) 
		{
			// Declaring Variables
			String strData = null;
			String strRecNo = null;
			// String strTariffDtl = "";
			// String strTariffDtl1 = "";
			String strmsgText = "";
			// String[] data = null;
			String strRes = null;
			String strBillDtl = null;

			// Creating Object for BO & VO.
			BillDupPrintTransVO vo = null;
			BillDupPrintTransBO bo = null;
			BillConfigUtil billConfigUtil = null;
			HisUtil hisutil = null;
			
			try 
			{
				vo = new BillDupPrintTransVO();
				bo = new BillDupPrintTransBO();
				vo.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				billConfigUtil = new BillConfigUtil(vo.getStrHosCode());
				
				hisutil = new HisUtil("transaction", "BillDupPrintTransData");
	
				//get drop down values of receipt type combo..
				bo.getReceiptType(vo);
				
				vo.setStrReceiptTypevalues(hisutil.getOptionValue(vo.getReceiptTypeWb(), "1", "", true));
				
				formBean.setStrReceiptTypevalues(vo.getStrReceiptTypevalues());

			} catch (Exception e) 
			{
				e.printStackTrace();
				strmsgText = e.getMessage();
				
				String response1 = "";
				HisException eObj = new HisException("Billing","BillDupPrintTransDATA->GET_INITIAL_DATA()", strmsgText);
					
				vo.setStrMsgString(strmsgText);
			} finally
			{
				vo = null;
				bo = null;

			}
		}
		
	
}



