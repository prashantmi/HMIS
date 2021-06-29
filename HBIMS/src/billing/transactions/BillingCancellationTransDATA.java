package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;

import billing.BillConfigUtil;
import billing.PrintHLP;

/**
 * @author Anshul Jindal
 * 
 */
public class BillingCancellationTransDATA {

	/**
	 * billDetails(formBean) -- > This Method is Used To get Bill Details
	 * 
	 * @param formBean
	 */
	public static void billDetails(BillingCancellationTransFB formBean) {
		String result = null;
		String strmsgText = null;
		BillingCancellationTransBO bo = null;
		BillingCancellationTransVO vo = null;

		try {
			bo = new BillingCancellationTransBO();
			vo = new BillingCancellationTransVO();

			vo.setStrCase(formBean.getStrCase());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			
			vo.setStrSeatId(formBean.getStrSeatId());	//added by vipul on 29.05.2021 to set login userid

			if (formBean.getStrCase().equals("1")) {
				vo.setStrCrNo(formBean.getStrCrNo());
				String strPatView = PatientDtlHLP.patientDtl(formBean
						.getStrCrNo(), true);
				if (strPatView.trim().equals("")) {
					formBean.setStrErrMsg("Invalid CR No.");
					// formBean.setStrCRNoSatus("0");
				} else {
					formBean.setStrPatientDetailsView(strPatView);
					formBean.setStrCRNoSatus("1");
					formBean.setStrMsgType("0");
				}
			}
			if (formBean.getStrCase().equals("2")) {
				vo.setStrGuarantorName(formBean.getStrGuarantorName());
			}

			bo.getBillDetails(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} else {
				result = BillingCancellationTransHLP.billDetails(vo);

				if (vo.getStrMsgType().equals("1")) {
					throw new Exception(vo.getStrMsgString());
				} else {
					vo.setStrBillDtl(result);
					formBean.setStrBillDtl(vo.getStrBillDtl());
				}

			}
		} catch (Exception e) {
			String msgStr = e.getMessage();
			if (msgStr.contains("CR.")) {

				formBean.setStrErrMsg("Invalid CR. No.");
				formBean.setStrCrNo("");
			} else{
			strmsgText = "billing.transactions.BillingCancellationTransDATA.billDetails(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"BillingCancellationTransaction->billDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
			}

		} finally {

			vo = null;
			bo = null;
		}

	}
	public static void CrbillDetails(BillingCancellationTransFB formBean) {
		String result = null;
		String strmsgText = null;
		BillingCancellationTransBO bo = null;
		BillingCancellationTransVO vo = null;

		try {
			bo = new BillingCancellationTransBO();
			vo = new BillingCancellationTransVO();

			vo.setStrCase(formBean.getStrCase());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			if (formBean.getStrCase().equals("1")) {
				vo.setStrCrNo(formBean.getStrCrNo());
				String strPatView = PatientDtlHLP.patientDtl(formBean
						.getStrCrNo(), true);
				if (strPatView.trim().equals("")) {
					formBean.setStrErrMsg("Invalid CR No.");
					// formBean.setStrCRNoSatus("0");
				} else {
					formBean.setStrPatientDetailsView(strPatView);
					formBean.setStrCRNoSatus("1");
					formBean.setStrMsgType("0");
				}
			}
			if (formBean.getStrCase().equals("2")) {
				vo.setStrGuarantorName(formBean.getStrGuarantorName());
			}

			bo.getCrBillDetails(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} else {
				result = BillingCancellationTransHLP.CrbillDetails(vo);

				if (vo.getStrMsgType().equals("1")) {
					throw new Exception(vo.getStrMsgString());
				} else {
					vo.setStrBillDtl(result);
					formBean.setStrBillDtl(vo.getStrBillDtl());
				}

			}
		} catch (Exception e) {
			String msgStr = e.getMessage();
			if (msgStr.contains("CR.")) {

				formBean.setStrErrMsg("Invalid CR. No.");
				formBean.setStrCrNo("");
			} else{
			strmsgText = "billing.transactions.BillingCancellationTransDATA.CrbillDetails(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"BillingCancellationTransaction->CrbillDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
			}

		} finally {

			vo = null;
			bo = null;
		}

	}

	/**
	 * cancelledByCmb(formBean) -- > This Method is Used To get Cancel By Combo
	 * 
	 * @param formBean
	 */
	public static void cancelledByCmb(BillingCancellationTransFB formBean) {

		String strmsgText = null;
		BillingCancellationTransBO bo = null;
		BillingCancellationTransVO vo = null;

		try {
			bo = new BillingCancellationTransBO();
			vo = new BillingCancellationTransVO();

			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			bo.getCancelledByCmb(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} else {
				formBean.setStrCancelledBy(vo.getStrCancelledBy());
			}

		} catch (Exception e) {
			strmsgText = "billing.transactions.BillingCancellationTransDATA.cancelledByCmb(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"BillingCancellationTransaction->cancelledByCmb()",
					strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			vo = null;

			bo = null;

		}

	}

	/**
	 * cancelReasonCmb(formBean) -- > This Method is Used To get Cancel Reason
	 * By Combo
	 * 
	 * @param formBean
	 */
	public static void cancelReasonCmb(BillingCancellationTransFB formBean) {

		BillingCancellationTransBO bo = null;
		BillingCancellationTransVO vo = null;

		String strmsgText = null;
		try {
			bo = new BillingCancellationTransBO();
			vo = new BillingCancellationTransVO();

			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			bo.getCancelReasonCmb(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} else {
				formBean.setStrCancelReason(vo.getStrCancelReason());
			}

		} catch (Exception e) {
			strmsgText = "billing.transactions.BillingCancellationTransDATA.cancelReasonCmb(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"BillingCancellationTransaction->cancelReasonCmb()",
					strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			vo = null;
			bo = null;
		}

	}

	/**
	 * This method will get the POPUP info according to the selected Bill No. to
	 * generate a PopUp in HLP
	 * 
	 * @param request
	 * @param response
	 */
	public static void getPopUp(HttpServletRequest request,
			HttpServletResponse response, BillingCancellationTransFB formBean) {
		String strPopUpDtls = null;
		String index = "";
		String strmsgText = null;

		BillingCancellationTransBO bo = null;
		BillingCancellationTransVO vo = null;

		String temp[] = null;

		try {
			bo = new BillingCancellationTransBO();
			vo = new BillingCancellationTransVO();

			vo.setStrBillNo((String) request.getParameter("billNo"));
			vo.setStrPatAccNo((String) request.getParameter("accNo"));
			String chk = (String) request.getParameter("CHK");
			// System.out.println("chk"+chk);
			temp = chk.replace("^", "#").split("#");
			vo.setStrReceiptNo(temp[8]);
			vo.setStrReceiptType(temp[2]);
			// System.out.println("vo.getStrReceiptNo()-"+vo.getStrReceiptNo());
			// System.out.println("vo.getStrReceiptType()-"+vo.getStrReceiptType());

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			index = (String) request.getParameter("index");
			bo.getPopUpInfo(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} else {
				strPopUpDtls = BillingCancellationTransHLP.getPopUpInfo1(vo,
						index);

				if (vo.getStrMsgType().equals("1")) {

					HisException eObj = new HisException("Billing",
							"BillingCancellationTransaction->getPopUp()",
							strmsgText);
					strmsgText = "ERROR####Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ";
					eObj = null;
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(strmsgText);
					throw new Exception(vo.getStrMsgString());
				} else {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(strPopUpDtls);

				}
			}

		} catch (Exception e) {
			strmsgText = "billing.transactions.BillingCancellationTransDATA.getPopUp(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"BillingCancellationTransaction->getPopUp()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			vo = null;

			bo = null;
		}

	}

	/**
	 * This method calls when we again click on a hyperlink In this method first
	 * we set the parameter value on VO and then use in HLP to show again same
	 * pop up. This parameter(popupData) is already set by the value of flag i
	 * in JS file(AJAX Function) and flag i is already set by the link info when
	 * a link is clicked first time
	 * 
	 * @param request
	 * @param response
	 */
	public static void getPopUpData(HttpServletRequest request,
			HttpServletResponse response, BillingCancellationTransFB formBean) {

		String strPopUpDtls = null;
		String strmsgText = null;

		BillingCancellationTransVO vo = null;

		try {

			vo = new BillingCancellationTransVO();

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrPopUpData((String) request.getParameter("popupData"));

			strPopUpDtls = BillingCancellationTransHLP.getPopUpData(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} else {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(strPopUpDtls);

			}

		} catch (Exception e) {
			strmsgText = "billing.transactions.BillingCancellationTransDATA.getPopUpData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"BillingCancellationTransaction->getPopUpData()",
					strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			vo = null;

		}

	}

	/**
	 * This method is used to call the DAO insert method through BO insert
	 * method. To insert the data into database the Procedure needs some
	 * parameters. This method sets the parameters in VO HOSPITAL CODE & SEAT ID
	 * --from session CANCELLED BY & CANCELLED REASON -- from formBean CRITERIA
	 * (1 or 2 )-- from formBean In criteria 1 : TRANS NO. & RECEIPT NO. comes
	 * from chk values so it sets CHKVALUES -- from request parameter (set in
	 * js) In criteria 2 : TRANS NO. & RECEIPT NO. comes directly from formBean
	 * so it sets TRANS NO. & RECEIPT NO. -- from formBean
	 * 
	 * @param form
	 * @param request
	 * @param response
	 */
	public static void insertData(ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// String msgStr = "";

		String strmsgText = null;
		String strHospitalCode = request.getSession().getAttribute(
				"HOSPITAL_CODE").toString();
		String strSeatId = request.getSession().getAttribute("SEATID")
				.toString();

		String strIpAddress = request.getSession().getAttribute("IP_ADDR")
				.toString();
	
		BillingCancellationTransBO bo = null;
		BillingCancellationTransVO vo = null;
		BillingCancellationTransFB formBean = null;

		try {
			bo = new BillingCancellationTransBO();
			vo = new BillingCancellationTransVO();
			formBean = (BillingCancellationTransFB) form;

			vo.setStrCriteria(formBean.getStrCriteria());
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrIpAddress(strIpAddress);
			vo.setStrCancelledBy(formBean.getStrCancelledBy());
			vo.setStrCancelReason(formBean.getStrOtherReason());
			
			vo.setStrService(formBean.getStrService());

			if (formBean.getStrCriteria().equals("1")) {
			 
				vo.setChk(formBean.getChk());
				vo.setCrchk(formBean.getCrchk());
				vo.setCehk(formBean.getCehk());
				vo.setBillcancelflag(formBean.getBillcancelflag());
				vo.setClNo(formBean.getClNo());
				vo.setClDate(formBean.getClDate());
				vo.setStrCrNo(formBean.getStrCrNo());
			}
			if (formBean.getStrCriteria().equals("2")) {
				vo.setStrTransNo(formBean.getStrTransNo());
				vo.setStrReceiptNo(formBean.getStrRcptNo());
			}
			/*System.out.println("vo.setChk"+vo.getChk());
			System.out.println("vo.setCrchk"+vo.getCrchk());
			System.out.println("vo.setCehk"+vo.getCehk());*/

			bo.insertData(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} else if (vo.getStrMsgType().equals("3")) { // for invalid Bill
				// No.
				formBean.setStrCrNo("");
				formBean.setStrGuarantorName("");
				formBean.setStrTransNo("");
				formBean.setStrRcptNo("");

				formBean.setStrErrMsg(vo.getStrMsgString());
			} else {
				formBean.setStrCrNo("");
				formBean.setStrGuarantorName("");
				formBean.setStrMsg(vo.getStrMsg());
				if(!vo.getStrCriteria().equals("2"))
				{
					if(vo.getChk()==null)
					{
						String temp[] = new String[9];
						//String temp1[] = new String[20];
						String strTransNo = "";
						String strReceiptNo = "";
						//String trfid="";
						
						//System.out.println("vo.getCrchk().length"+vo.getCrchk().length);
						for (int j = 0; j < vo.getCrchk().length; j++) 
						    {
									temp = vo.getCrchk()[j].replace("^", "#").split("#");
									strTransNo = temp[0];
									strReceiptNo = temp[8];
						    }
						/*for (int i = 0; i < vo.getCehk().length; i++) 
						  {
							temp1 = vo.getCehk()[i].replace("^", "#").split("#");
						    trfid=temp1[0];
						  }*/
						/*System.out.println("temp"+temp);
						System.out.println("strTransNo"+strTransNo);
						System.out.println("strReceiptNo"+strReceiptNo);*/
					
						PrintHLP.CREDIT_NOTE(strTransNo, "10",formBean.getStrHospitalCode(),  strReceiptNo,request,null);
						
						String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH")+ "/"+ "Billing"+ request.getSession().getAttribute("SEATID").toString() + ".dat";
						formBean.setFilePath(fileName);
						request.setAttribute("filePath", fileName);
						formBean.setIsOpenPopUp("1");
					}
				}
			}

			formBean.setStrCase("1");
			
		} catch (Exception e) {
			strmsgText = "billing.transactions.BillingCancellationTransDATA.insertData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"BillingCancellationTransaction->insertData()", strmsgText);
			if(strmsgText.contains("ERROR: query returned no rows"))
			{formBean.setStrErrMsg("Bill can't able to cancel as it is raised by other user! ");}
			else{formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");}
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			bo = null;

			vo = null;

		}

	}
	public static String TariffDtl(BillingCancellationTransFB formBean,HttpServletRequest request) 
	{
		String strmsgText = "";
		String tmpBsId[] = null;
		String tariffDetails = null;
		String strPenalty = "";
		BillingCancellationTransVO vo = null;
		BillingCancellationTransBO bo = null;
		BillConfigUtil config = null;

		try 
		{
			vo = new BillingCancellationTransVO();
			bo = new BillingCancellationTransBO();
			config = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			vo.setStrHospitalCode(hosCode);
			String strValmode = (String) request.getParameter("modName");
			vo.setStrChkValues(strValmode);
			tmpBsId = strValmode.replace("^", "#").split("#");
			//vo.setStrHospServ(tmpBsId[1]);

			/*if (vo.getStrHospServ().equals("1")) 
			{
				strPenalty = config.getOpdRefundPenalty();
				vo.setStrPenalty(strPenalty);
			} 
			else if (vo.getStrHospServ().equals("2")) 
			{
				strPenalty = config.getIpdRefundPenalty();
				vo.setStrPenalty(strPenalty);
			} 
			else 
			{
				strPenalty = config.getEmergencyRefundPenalty();
				vo.setStrPenalty(strPenalty);
			}*/
			bo.setTariffValues(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			} 
			else 
			{
				tariffDetails = BillingCancellationTransHLP.getTariffDetails(vo);
				// tariffDetails = null;
				if (vo.getStrMsgType().equals("1") || tariffDetails == null) 
				{
					throw new Exception(vo.getStrMsgString());
				}
			}
		} 
		catch (Exception e) 
		{
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing","BillingCancellationTransDATA->TariffDtl()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{		
			vo = null;		
			bo = null;
		}
		if (strmsgText.equals("") || tariffDetails != null) 
		{
			return tariffDetails;
		} 
		else 
		{
			return formBean.getStrErrMsg();
		}
	}

}
