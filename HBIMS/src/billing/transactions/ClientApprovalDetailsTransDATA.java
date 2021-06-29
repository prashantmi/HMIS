package billing.transactions;

// import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.utility.HisUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

public class ClientApprovalDetailsTransDATA {

	/**
	 * This Method is Used to get Client Approval Details Approval //used
	 * 
	 * @param form
	 */
	// used
	public static void initClientApprovalDetails(
			ClientApprovalDetailsTransFB formBean, HttpServletRequest request) {

		ClientApprovalDetailsTransBO bo = new ClientApprovalDetailsTransBO();
		ClientApprovalDetailsTransVO voObj = new ClientApprovalDetailsTransVO();
		try {
			voObj.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			bo.initClientApprovalDetails(voObj);

			if (formBean.getStrMsgType().equals("0")) {
				/*
				 * System.out.println("data initClientApprovalDetails
				 * formBean.getStrChk()"+formBean.getStrChk());
				 * 
				 * String chk2 = formBean.getStrChk(); String[] strTemp =
				 * chk2.split("\\@"); formBean.setStrChk(voObj.getStrChk());
				 * formBean.setStrClientPatNo(strTemp[0]);
				 * formBean.setStrMsgString(voObj.getStrMsgString());
				 * formBean.setStrMsgType(voObj.getStrMsgType());
				 * 
				 * initClientApprovalCombos(formBean, voObj);
				 */
			} else {
				throw new Exception(formBean.getStrMsgString());
			}

		} catch (Exception e) {

			String strmsgText = e.getMessage();
			HisException eObj = new HisException(
					"Billing",
					"ClientApprovalDetailsTransDATA->initClientApprovalDetails()",
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			bo = null;
			voObj = null;

		}

	}

	/*
	 * public static void initClientReApprovalDetails(
	 * ClientApprovalDetailsTransFB formBean) {
	 * 
	 * ClientApprovalDetailsTransBO bo = new ClientApprovalDetailsTransBO();
	 * ClientApprovalDetailsTransVO voObj = new ClientApprovalDetailsTransVO();
	 * try { voObj.setStrChk(formBean.getStrChk());
	 * bo.initClientApprovalDetails(voObj); if
	 * (formBean.getStrMsgType().equals("0")) {
	 * formBean.setStrChk(voObj.getStrChk());
	 * formBean.setStrMsgString(voObj.getStrMsgString());
	 * formBean.setStrMsgType(voObj.getStrMsgType()); } else { throw new
	 * Exception(formBean.getStrMsgString()); } } catch (Exception e) {
	 * 
	 * String strmsgText = e.getMessage(); HisException eObj = new HisException(
	 * "Billing",
	 * "ClientApprovalDetailsTransDATA->initClientReApprovalDetails()",
	 * strmsgText); formBean.setStrErrMsg("Application Error [ERROR ID : " +
	 * eObj.getErrorID() + "], Contact System Administrator! ");
	 * formBean.setStrMsgType("1"); eObj = null; } finally {
	 * 
	 * if (voObj != null) voObj = null; } }
	 */
	// used
	private static void initClientApprovalCombos(
			ClientApprovalDetailsTransFB formBean,
			ClientApprovalDetailsTransVO voObj) {
		HisUtil util = null;
		String temp1 = "";
		// String temp = "";
		util = new HisUtil("Admission Advice Trans", "AdmissionAdviceTransDATA");

		try {

			if (formBean.getStrMsgType().equals("0")) {
				if (voObj.getStrClientList() != null
						|| voObj.getStrClientList().size() > 0) {
					temp1 = util.getOptionValue(voObj.getStrClientList(), "0",
							"0^Select Value", false);
				} else {
					temp1 = "<option value='0'>N/A</option>";
				}

				formBean.setStrClientName(temp1);
			} else {
				throw new Exception(formBean.getStrMsgString());
			}
		} catch (Exception e) {

			String strmsgText = e.getMessage();
			HisException eObj = new HisException(
					"Billing",
					"ClientApprovalDetailsTransDATA->initClientApprovalCombos()",
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			util = null;
		}

	}

	/**
	 * *************************INSERT STATEMENT FOR
	 * APPROVAL*************************
	 */
	// used
	public static boolean InsertRecordApproval(HttpServletRequest request,
			HttpServletResponse response, ClientApprovalDetailsTransFB formBean) {

		String strmsgText = "";
		boolean bRetVal = true;
		String strMsg = "";
		ClientApprovalDetailsTransVO vo = null;
		ClientApprovalDetailsTransBO bo = null;

		// Set the Vale for Value Object
		try {
			vo = new ClientApprovalDetailsTransVO();
			bo = new ClientApprovalDetailsTransBO();

			vo.setStrClientPatientNo(formBean.getStrClientPatNo());
			vo.setStrClientPatientSrNo("1");
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrCltNo(formBean.getStrCltNo());
			/*
			 * System.out.println("DATA formBean.getStrCardNumber()" +
			 * formBean.getStrCardNumber());
			 * System.out.println("formBean.getStrCardExpiryDate()" +
			 * formBean.getStrCardExpiryDate()); System.out.println("DATA
			 * formBean.getStrCardHolderName()" +
			 * formBean.getStrCardHolderName());
			 */

			vo.setStrCardNumber(formBean.getStrCardNumber());
			vo.setStrCardExpiryDate(formBean.getStrCardExpiryDate());
			vo.setStrCardHolderName(formBean.getStrCardHolderName());
			vo
					.setStrAuthenticationNumber(formBean
							.getStrAuthenticationNumber());
			vo.setStrAuthenticationDate(formBean.getStrAuthenticationDate());
			vo.setStrSanctionAmount(formBean.getStrSanctionAmount());
			vo.setStrClientExpenseAmount("0");
			vo.setStrExpiryDate(formBean.getStrExpiryDate());
			vo.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			vo.setStrOneTimeService(formBean.getStrOneTimeService());

			/*
			 * System.out.println("formBean.getStrOneTimeService()-" +
			 * formBean.getStrOneTimeService());
			 */
			if (vo.getStrOneTimeService() == null
					|| vo.getStrOneTimeService().equals("")) {
				vo.setStrOneTimeService("0");
			}

			/*
			 * System.out.println("vo.getStrOneTimeService()-" +
			 * vo.getStrOneTimeService());
			 * System.out.println("formBean.getIPD()-" + formBean.getIPD());
			 * System.out.println("formBean.getOPD()-" + formBean.getOPD());
			 * System.out.println("formBean.getEME()-" + formBean.getEME());
			 */
			vo.setIPD(formBean.getIPD());

			vo.setOPD(formBean.getOPD());

			vo.setEME(formBean.getEME());

			// vo.setStrHospitalCode("108");
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			boolean fretValue = bo.insertForApproval(vo); // Get Return Value
			// from BO insert
			// method
			if (fretValue) {
				bRetVal = false;
				strMsg = "Record saved successfully!";
				formBean.setStrMsg(strMsg);
				formBean.setStrCrNo("");
			} else {
				bRetVal = false;// Check Error
				if (vo.getStrMsgType().equals("1")) {
					throw new Exception(vo.getStrMsgString());
				}
			}
		} catch (Exception e) {
			bRetVal = false;
			strmsgText = e.getMessage();

			HisException eObj = new HisException("Billing",
					"ClientApprovalDetailsTransDATA->InsertRecordApproval()",
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			bo = null;
		}
		return bRetVal;
	}

	/**
	 * *************************INSERT STATEMENT FOR
	 * APPROVAL*************************
	 */
	// used
	public static boolean InsertRecordReApproval(HttpServletRequest request,
			HttpServletResponse response, ClientApprovalDetailsTransFB formBean) {
		// Declare Variables
		// String strErrmsg = "";
		String strmsgText = "";
		boolean bRetVal = true;
		String strMsg = "";
		// String strWarning = "";
		// Create Object for BO & VO
		ClientApprovalDetailsTransVO vo = null;
		ClientApprovalDetailsTransBO bo = null;

		// Set the Vale for Value Object
		try {
			vo = new ClientApprovalDetailsTransVO();
			bo = new ClientApprovalDetailsTransBO();
			String strChkValue = formBean.getStrChk();
			// System.out.println("data reapp insert chk"+strChkValue);
			String temp[] = strChkValue.split("\\@");

			vo.setStrClientPatientNo(temp[0]);
			vo.setStrClientPatientSrNo(temp[1]);

			vo.setStrClientNo(formBean.getStrClientNo());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrCardNumber(formBean.getStrCardNumber());
			vo.setStrCardExpiryDate(formBean.getStrCardExpiryDate());
			vo.setStrCardHolderName(formBean.getStrCardHolderName());

			// vo.setStrClientPatientNo(formBean.getStrClientPatNo());
			vo.setStrAuthenticationNumber(formBean
					.getStrNewAuthenticationNumber());
			vo.setStrAuthenticationDate(formBean.getStrNewAuthenticationDate());
			vo.setStrSanctionAmount(formBean.getStrNewSanctionAmount());

			vo.setStrExpiryDate(formBean.getStrNewExpiryDate());

			vo.setIPD(formBean.getIPD());
			vo.setOPD(formBean.getOPD());
			vo.setEME(formBean.getEME());

			/*
			 * System.out.println("vo.getIPD()" + vo.getIPD());
			 * System.out.println("vo.getOPD()" + vo.getOPD());
			 * System.out.println("vo.getEME()" + vo.getEME());
			 */
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());

			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			boolean fretValue = bo.insertForReApproval(vo); // Get Return Value
			// from BO insert
			// method
			if (fretValue) {
				strMsg = "Record saved successfully!";
				formBean.setStrMsg(strMsg);
			} else {
				// Check Error
				if (vo.getStrMsgType().equals("1")) {
					throw new Exception(vo.getStrMsgString());
				}
			}
		} catch (Exception e) {
			bRetVal = false;
			strmsgText = e.getMessage();

			HisException eObj = new HisException("Billing",
					"ClientApprovalDetailsTransDATA->InsertRecordApproval()",
					strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			bo = null;
		}
		return bRetVal;
	}

	/**
	 * *************************INSERT STATEMENT FOR
	 * APPROVAL*************************
	 */
	// used
	public static boolean InsertForClose(HttpServletRequest request,
			HttpServletResponse response, ClientApprovalDetailsTransFB formBean) {
		// Declare Variables
		// String strErrmsg = "";
		String strmsgText = "";
		boolean bRetVal = true;
		String strMsg = "";
		// String strWarning = "";
		// Create Object for BO & VO
		ClientApprovalDetailsTransVO vo = null;
		ClientApprovalDetailsTransBO bo = null;

		// Set the Vale for Value Object
		try {
			vo = new ClientApprovalDetailsTransVO();
			bo = new ClientApprovalDetailsTransBO();
			String chk = request.getParameter("chk");
			// System.out.println("data close chk-"+chk);
			String[] strTemp = chk.split("\\@");
			vo.setStrClientPatNo(strTemp[0]);
			vo.setStrClientPatientSrNo(strTemp[1]);
			vo.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			boolean fretValue = bo.insertForClose(vo); // Get Return Value from
			// BO insert method
			if (fretValue) {
				strMsg = "Record Close successfully!";
				// System.out.println("strMsg-"+strMsg);
				formBean.setStrMsg(strMsg);
			} else {
				// Check Error
				if (vo.getStrMsgType().equals("1")) {
					throw new Exception(vo.getStrMsgString());
				}
			}
		} catch (Exception e) {
			bRetVal = false;
			strmsgText = e.getMessage();

			HisException eObj = new HisException("Billing",
					"ClientApprovalDetailsTransDATA->InsertForClose()",
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			bo = null;
		}
		return bRetVal;
	}

	// used
	public static boolean setPatientDetails(
			ClientApprovalDetailsTransFB formBean, HttpServletRequest request) {

		boolean bRetVal = true;
		String strPatView = "";
		ClientApprovalDetailsTransBO bo = null;
		ClientApprovalDetailsTransVO vo = null;
		// String temp[] = null;
		String strPatientStatus = "";

		try {
			bo = new ClientApprovalDetailsTransBO();
			vo = new ClientApprovalDetailsTransVO();

			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			bo.initClientApprovalDetails(vo);

			try {

				strPatView = PatientDtlHLP.patientWithAdmissionDtl(formBean
						.getStrCrNo(), formBean.getStrHospitalCode(), false);

			} catch (Exception e) {

				formBean.setStrErrMsg("Invalid CR No.");
				formBean.setStrCrNoStatus("0");

			}

			if (strPatView != null) {
				formBean.setStrPatientDetailsView(strPatView);
			} else {
				strPatView = " ";
				// Set Messages into form bean
				formBean.setStrPatientDetailsView(strPatView);
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}
			}
			if (strPatientStatus.equals("13")) {
				formBean.setStrErrMsg("Patient is Dead");
			}

			initClientApprovalCombos(formBean, vo);

			if (!vo.getStrApprovalStatus().equals("0")) {
				formBean.setStrWarning("Already Approved");
			}
			formBean.setStrApprovalStatus(vo.getStrApprovalStatus());
			formBean.setStrCrNo(vo.getStrCrNo());
			// System.out.println(""+formb);

		} catch (Exception e) {
			bRetVal = false;
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"ClientApprovalDetailsTransDATA->setPatientDetails()",
					msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			vo = null;

			bo = null;
		}

		return bRetVal;

	}

	// used

	public static void initViewDetails(HttpServletRequest request,
			ClientApprovalDetailsTransFB formBean) {

		String strChkValue = request.getParameter("chk");

		ClientApprovalDetailsTransVO vo = new ClientApprovalDetailsTransVO();
		ClientApprovalDetailsTransBO bo = new ClientApprovalDetailsTransBO();
		try {
			formBean.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			String temp[] = strChkValue.split("\\@");

			vo.setStrClientPatNo(temp[0]);
			vo.setStrClientPatientSrNo(temp[1]);
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			bo.initViewDetails(vo);
			WebRowSet ws = vo.getStrApprovalDetailsWs();
			int size = ws.size();
			String[] strCltPatSlNo = new String[size];
			String[] strAuthNumber = new String[size];
			String[] strAuthDate = new String[size];
			String[] strTotSancAmt = new String[size];
			String[] strExpAmt = new String[size];
			String[] strExpyDate = new String[size];
			String[] strClientAmt = new String[size];
			String[] strPrevSancAmt = new String[size];
			String[] strCurrSancAmt = new String[size];
			String Detail = new String();
			formBean.setStrCltPatSlNum(strCltPatSlNo);
			formBean.setStrAuthNumber(strAuthNumber);
			formBean.setStrAuthDate(strAuthDate);
			formBean.setStrTotSancAmt(strTotSancAmt);
			formBean.setStrExpAmt(strExpAmt);
			formBean.setStrExpyDate(strExpyDate);
			formBean.setStrClientAmt(strClientAmt);
			formBean.setStrPrevSancAmt(strPrevSancAmt);
			formBean.setStrCurrSancAmt(strCurrSancAmt);

			int i = 0;
			while (ws.next()) {
				formBean.setStrClientPatNo(ws.getString(1));
				formBean.setStrCrNo(ws.getString(3));
				formBean.setStrClientDetail(ws.getString(4));
				Detail = ws.getString(4);
				formBean.setStrCardNumber(ws.getString(5));
				formBean.setStrCardExpiryDate(ws.getString(6));
				formBean.setStrCardHolderName(ws.getString(7));
				strCltPatSlNo[i] = ws.getString(2);
				strAuthNumber[i] = ws.getString(8);
				strAuthDate[i] = ws.getString(9);
				strTotSancAmt[i] = ws.getString(10);
				strExpAmt[i] = ws.getString(11);

				strExpyDate[i] = ws.getString(12);
				// System.out.println("strExpyDate[i]"+strExpyDate[i]);
				if (strExpyDate[i] == null) {
					strExpyDate[i] = "";
				}
				strClientAmt[i] = ws.getString(14);
				strPrevSancAmt[i] = ws.getString(20);
				strCurrSancAmt[i] = ws.getString(21);
				i++;
			}

			String[] Data = Detail.split("\\^");
			formBean.setStrClientName(Data[0]);
			formBean.setStrClientType(Data[1]);
			formBean.setStrRegistrationNo(Data[2]);
			formBean.setStrContactPerson(Data[3]);
			formBean.setStrClientAddress(Data[4]);
			formBean.setStrContactNo(Data[5]);
			formBean.setStrEmailId(Data[6]);
			formBean.setEME(Data[11]);
			formBean.setIPD(Data[9]);
			formBean.setOPD(Data[10]);
			vo.setStrCardHolderName(formBean.getStrCardHolderName());
			vo.setStrCltPatSlNum(strCltPatSlNo);
			vo.setStrAuthNumber(strAuthNumber);
			vo.setStrAuthDate(strAuthDate);
			vo.setStrTotSancAmt(strTotSancAmt);
			vo.setStrExpAmt(strExpAmt);

			vo.setStrExpyDate(strExpyDate);
			vo.setStrClientAmt(strClientAmt);
			vo.setStrPrevSancAmt(strPrevSancAmt);
			vo.setStrCurrSancAmt(strCurrSancAmt);
			String strViewDtl = ClientApprovalDetailsTransHLP
					.getViewApprovalDtl(vo);
			formBean.setStrViewDtl(strViewDtl);
		} catch (Exception e) {
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"ClientApprovalDetailsTransDATA->initViewDetails()", msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			bo = null;
		}

	}

	// used
	public static boolean setPatientDetails2(
			ClientApprovalDetailsTransFB formBean, HttpServletRequest request) {

		boolean bRetVal = true;
		WebRowSet ws = null;
		String strPatView = "";
		ClientApprovalDetailsTransBO bo = null;
		ClientApprovalDetailsTransVO vo = null;
		String hiddCltDtl = "";

		try {
			bo = new ClientApprovalDetailsTransBO();
			vo = new ClientApprovalDetailsTransVO();
			formBean.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			// System.out.println("formBean.getStrChk()-"+formBean.getStrChk());
			String[] strTemp = formBean.getStrChk().split("\\@");
			String[] strTemp1 = strTemp[2].split("\\$");
			vo.setStrCrNo(strTemp1[0]);
			formBean.setStrCrNo(strTemp1[0]);
			vo.setStrClientPatientNo(strTemp[0]);
			vo.setStrClientPatientSrNo(strTemp[1]);
			vo.setStrHospitalCode(formBean.getStrHospitalCode());

			// by anshul // bo.initClientApprovalDetails(vo);

			bo.getReApproval(vo);

			strPatView = PatientDtlHLP.patientWithAdmissionDtl(formBean
					.getStrCrNo(), formBean.getStrHospitalCode(), false);
			if (strPatView != null) {
				formBean.setStrPatientDetailsView(strPatView);
			} else {
				strPatView = " ";
				// Set Messages into form bean
				formBean.setStrPatientDetailsView(strPatView);
				// Check error
				if (formBean.getStrMsgType().equals("1")) {
					throw new Exception(formBean.getStrMsgString());
				}
			}
			ws = vo.getStrCltReApprovalData();
			while (ws.next()) {

				formBean.setStrClientPatNo(ws.getString(1));

				formBean.setStrClientPatientSrNo(ws.getString(2));

				formBean.setStrCrNo(ws.getString(3));

				hiddCltDtl = ws.getString(4);

				formBean.setStrCardNumber(ws.getString(5));
				// System.out.println("Card Expry date-->" + ws.getString(6));
				formBean.setStrCardExpiryDate(ws.getString(6));

				// System.out.println("Card Holder Name -->"+ws.getString(7));
				formBean.setStrCardHolderName(ws.getString(7));
				// System.out.println("Auth No-->"+ws.getString(8));
				formBean.setStrAuthenticationNumber(ws.getString(8));
				// System.out.println("Auth Date-->"+ws.getString(9));
				formBean.setStrAuthenticationDate(ws.getString(9));
				// System.out.println("Sanc Amt-->"+ws.getString(10));
				formBean.setStrSanctionAmount(ws.getString(10));
				// System.out.println("Exp Amt-->"+ws.getString(11));
				formBean.setStrClientExpenseAmount(ws.getString(12));
				// System.out.println("Exp Date-->"+ws.getString(12));
				formBean.setStrExpiryDate(ws.getString(12));
				// System.out.println("Payment Status-->"+ws.getString(13));
				formBean.setStrPaymentMode(ws.getString(13));
				// System.out.println("Clt Amt-->"+ws.getString(14));
				formBean.setStrClientExpenseAmount(ws.getString(14));
				// System.out.println("OPD Approval-->" + ws.getString(15));
				formBean.setOPD(ws.getString(15));
				// System.out.println("IPD Approval-->" + ws.getString(16));
				formBean.setIPD(ws.getString(16));
				// System.out.println("EME Approval-->" + ws.getString(17));
				formBean.setEME(ws.getString(17));
				// System.out.println("One Time Service-->" + ws.getString(18));
				formBean.setStrOneTimeService(ws.getString(18));
				// System.out.println("Clt ID-->"+ws.getString(19));
				formBean.setStrClientNo(ws.getString(19));
				// System.out.println("Pre Sanc Amt-->"+ws.getString(20));
				formBean.setStrPreSancAmt(ws.getString(20));
				// System.out.println("Current Sanc Amt-->" + ws.getString(21));
				// formBean.setStrSanctionAmount(ws.getString(21));
			}
			String[] Data = hiddCltDtl.split("\\^");
			formBean.setStrClientName(Data[0]);
			formBean.setStrClientType(Data[1]);
			formBean.setStrRegistrationNo(Data[2]);
			formBean.setStrContactPerson(Data[3]);
			// System.out.println("Client Address ::"+Data[4]);
			formBean.setStrClientAddress(Data[4]);

			formBean.setStrContactNo(Data[5]);

			try {
				formBean.setStrEmailId(Data[6]);
			} catch (Exception e) {
				formBean.setStrEmailId(" ");
			}

			// strTariffDtl =
			// formBean.getStrClientAddress()+"^"+formBean.getStrRegistrationNo()+"^"+formBean.getStrClientType()+"^"+formBean.getStrContactPerson()+"^"+formBean.getStrContactNo()+"^"+formBean.getStrEmailId()+"^"+Data[2];

			formBean.setStrCrNo(vo.getStrCrNo());
			formBean.setStrChk(formBean.getStrChk());

		} catch (Exception e) {
			bRetVal = false;
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"ClientApprovalDetailsTransDATA->setPatientDetails()",
					msgStr);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			vo = null;

			bo = null;

		}
		// initClientApprovalCombos2(formBean, vo);
		// formBean.setStrApprovalStatus(vo.getStrApprovalStatus());

		return bRetVal;

	}

	/*
	 * private static void initClientApprovalCombos2(
	 * ClientApprovalDetailsTransFB formBean, ClientApprovalDetailsTransVO
	 * voObj) { HisUtil util = null; String temp1 = ""; // String temp = "";
	 * util = new HisUtil("Admission Advice Trans", "AdmissionAdviceTransDATA");
	 * 
	 * try { String cltno = formBean.getStrClientPatientNo();
	 * 
	 * if (formBean.getStrMsgType().equals("0")) { if (voObj.getStrClientList() !=
	 * null || voObj.getStrClientList().size() > 0) { temp1 =
	 * util.getOptionValue(voObj.getStrClientList(), cltno, "0^Select Value",
	 * true); } else { temp1 = "<option value='0'>N/A</option>"; }
	 * formBean.setStrClientName(temp1); } else { throw new
	 * Exception(formBean.getStrMsgString()); } } catch (Exception e) { String
	 * msgStr = e.getMessage(); HisException eObj = new HisException( "Billing",
	 * "ClientApprovalDetailsTransDATA->initClientApprovalCombos2()", msgStr);
	 * formBean.setStrErrMsg("Application Error [ERROR ID : " +
	 * eObj.getErrorID() + "], Contact System Administrator! ");
	 * formBean.setStrMsgType("1"); eObj = null; } finally {
	 * 
	 * if (voObj != null) voObj = null;
	 * 
	 * if (util != null) util = null; } }
	 */

	/*
	 * public static void initTariffDtls(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ClientApprovalDetailsTransVO voObj = new ClientApprovalDetailsTransVO(); //
	 * String strTariffDetails = request.getParameter("tariffDetails"); String
	 * strGName = request.getParameter("gName");
	 * 
	 * voObj.setStrGroupTempCode(strGName);
	 * 
	 * ClientApprovalDetailsTransBO bo = new ClientApprovalDetailsTransBO();
	 * bo.setTariffList(voObj); String tariffView = null; // String tariffView = //
	 * ClientApprovalDetailsTransHLP.getTarrifDetailsView( // voObj,
	 * strTariffDetails, 2);
	 * 
	 * try {
	 * 
	 * response.setHeader("Cache-Control", "no-cache");
	 * response.getWriter().print(tariffView); } catch (IOException e) {
	 * 
	 * new HisException("Ipd Master",
	 * "ClientApprovalDetailsTransDATA.initTariffDtls()", e .getMessage()); } }
	 */

	/**
	 * *************************AJAX FUNCTION for View CLIENT
	 * DETAILS****************************
	 */
	// used
	public static void UNITVAL22(HttpServletRequest request,
			HttpServletResponse response, ClientApprovalDetailsTransFB formBean) {
		// Declaring Variables
		String strCltNo = null;
		String strTariffDtl = "";
		// String strTariffDtl1 = "";
		String strmsgText = "";
		// String[] data = null;
		// String strRes = null;
		WebRowSet ws = null;

		// Creating Object for BO & VO.
		ClientApprovalDetailsTransVO vo = null;
		ClientApprovalDetailsTransBO bo = null;
		String[] Data = new String[7];
		try {
			vo = new ClientApprovalDetailsTransVO();
			bo = new ClientApprovalDetailsTransBO();
			// Here we Split Data
			strCltNo = request.getParameter("modName");

			vo.setStrClientPatientNo(strCltNo);
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());

			bo.UNITVAL12(vo);
			ws = vo.getStrClientDtlWs();
			if (ws != null) {
				while (ws.next()) {
					// System.out.println("ws.getString(1)" + ws.getString(1));
					Data = ws.getString(1).split("\\^");
					// System.out.println("Data[2]" + Data[2]);
					try {
						formBean.setStrClientAddress(Data[1]);
					} catch (Exception e) {
						formBean.setStrClientAddress(" ");
					}

					try {
						formBean.setStrClientType(Data[2]);
					} catch (Exception e) {
						formBean.setStrClientType(" ");
					}

					try {
						formBean.setStrRegistrationNo(Data[3]);
					} catch (Exception e) {
						formBean.setStrRegistrationNo(" ");
					}

					try {

						formBean.setStrContactPerson(Data[4]);
					} catch (Exception e) {
						formBean.setStrContactPerson(" ");
					}

					try {
						formBean.setStrContactNo(Data[5]);
					} catch (Exception e) {
						formBean.setStrContactNo(" ");
					}

					try {
						formBean.setStrEmailId(Data[6]);
					} catch (Exception e) {
						formBean.setStrEmailId(" ");
					}

					strTariffDtl = formBean.getStrClientAddress() + "^"
							+ formBean.getStrRegistrationNo() + "^"
							+ formBean.getStrClientType() + "^"
							+ formBean.getStrContactPerson() + "^"
							+ formBean.getStrContactNo() + "^"
							+ formBean.getStrEmailId();
				}
				// Set the Value in Response
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strTariffDtl);
			} else {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"IpdBillManagementTransDATA->UNITVAL12()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				// System.out.println("Inside IInd Else::::"+e1.getMessage());
			}
		} finally {

			vo = null;

		}
	}

	/*
	 * public static void initTariffList(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ClientApprovalDetailsTransVO voObj = new ClientApprovalDetailsTransVO();
	 * 
	 * voObj.setStrGroupTempCode(request.getParameter("gName"));
	 * 
	 * ClientApprovalDetailsTransBO bo = new ClientApprovalDetailsTransBO();
	 * 
	 * bo.setTariffList(voObj);
	 * 
	 * HisUtil util = null; String temp = "";
	 * 
	 * util = new HisUtil("Admission Advice Trans", "AdmissionAdviceTransDATA");
	 * 
	 * try {
	 * 
	 * if (voObj.getStrTariffWs() != null || voObj.getStrTariffWs().size() > 0) {
	 * temp = util.getOptionValue(voObj.getStrTariffWs(), voObj
	 * .getStrGroupTempCode(), "0^Select Value", false); } else { temp = "<option
	 * value='0'>N/A</option>"; } // temp =
	 * util.getOptionValue(voObj.getStrTariffWs(), voObj //
	 * .getStrGroupTempCode(), "0^Select Value", false);
	 * 
	 * response.setHeader("Cache-Control", "no-cache");
	 * response.getWriter().print(temp); } catch (IOException e) {
	 * 
	 * new HisException("Ipd Master",
	 * "ClientApprovalDetailsTransDATA.initTariffDtls()", e .getMessage()); }
	 * catch (Exception e) {
	 * 
	 * new HisException("Billing Transaction",
	 * "ClientApprovalDetailsTransDATA.initTariffDtls()", e .getMessage()); } }
	 */

	/*
	 * public static void initReApprovalDetails(HttpServletRequest request,
	 * ClientApprovalDetailsTransFB formBean) {
	 * 
	 * String strChkValue = request.getParameter("chkValue");
	 * 
	 * ClientApprovalDetailsTransVO voObj = new ClientApprovalDetailsTransVO();
	 * 
	 * String temp[] = strChkValue.replace("@", "#").split("#");
	 * 
	 * voObj.setStrClientPatientNo(temp[0]);
	 * voObj.setStrClientPatientSrNo(temp[1]);
	 * 
	 * ClientApprovalDetailsTransBO bo = new ClientApprovalDetailsTransBO();
	 * 
	 * bo.initReApprovalDetails(voObj);
	 * 
	 * formBean.setStrCrNo(voObj.getStrCrNo());
	 * 
	 * TransferObjectFactory.populateData(formBean, voObj);
	 * 
	 * formBean.setStrMsgString(voObj.getStrMsgString());
	 * formBean.setStrMsgType(voObj.getStrMsgType());
	 * 
	 * HisUtil util = null; String temp1 = "";
	 * 
	 * util = new HisUtil("Admission Advice Trans", "AdmissionAdviceTransDATA");
	 * 
	 * try {
	 * 
	 * if (formBean.getStrMsgType().equals("0")) {
	 * 
	 * if (voObj.getStrGroupNameWs() != null || voObj.getStrGroupNameWs().size() >
	 * 0) { temp1 = util.getOptionValue(voObj.getStrGroupNameWs(), "0",
	 * "0^Select Value", false); } else { temp1 = "<option value='0'>N/A</option>"; }
	 * 
	 * formBean.setStrGroupNameValues(temp1); } else {
	 * 
	 * throw new Exception(formBean.getStrMsgString()); } } catch (Exception e) {
	 * 
	 * new HisException("Client Approval Details Transaction",
	 * "ClientApprovalDetailsTransDATA",
	 * "ClientApprovalDetailsTransDATA.initReApprovalDetails()-->" +
	 * e.getMessage()); } finally {
	 * 
	 * if (voObj != null) voObj = null; if (formBean != null) formBean = null;
	 * if (util != null) util = null; } }
	 */
	/*
	 * public static void initReFundDetails(HttpServletRequest request,
	 * ClientApprovalDetailsTransFB formBean) {
	 * 
	 * String strChkValue = request.getParameter("chkValue");
	 * 
	 * ClientApprovalDetailsTransVO voObj = new ClientApprovalDetailsTransVO();
	 * 
	 * String temp[] = strChkValue.replace("@", "#").split("#");
	 * 
	 * voObj.setStrClientPatientNo(temp[0]);
	 * voObj.setStrClientPatientSrNo(temp[1]);
	 * voObj.setStrHospitalCode(formBean.getStrHospitalCode());
	 * ClientApprovalDetailsTransBO bo = new ClientApprovalDetailsTransBO();
	 * 
	 * bo.initReFundDetails(voObj);
	 * 
	 * formBean.setStrCrNo(voObj.getStrCrNo());
	 * 
	 * TransferObjectFactory.populateData(formBean, voObj);
	 * 
	 * formBean.setStrMsgString(voObj.getStrMsgString());
	 * formBean.setStrMsgType(voObj.getStrMsgType()); }
	 */
	/*
	 * public static void paymentMode(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * String strPayMode = request.getParameter("payMode");
	 * 
	 * String strPayDtl = null;
	 * 
	 * if (strPayMode.equals("2")) {
	 * 
	 * strPayDtl = PaymentModeHLP1.getChequeDetails(); } else if
	 * (strPayMode.equals("3") || strPayMode.equals("4")) {
	 * 
	 * strPayDtl = PaymentModeHLP1.getCardDetails(); } else if
	 * (strPayMode.equals("5")) { strPayDtl = PaymentModeHLP1.getDDDetails(); }
	 * else { strPayDtl = ""; }
	 * 
	 * try {
	 * 
	 * response.setHeader("Cache-Control", "no-cache");
	 * response.getWriter().print(strPayDtl); } catch (IOException e) {
	 * 
	 * new HisException("Ipd Master",
	 * "ClientApprovalDetailsTransDATA.initReFundDetails()", e .getMessage()); } }
	 */
	/*
	 * public static void initCloseDetails(HttpServletRequest request,
	 * ClientApprovalDetailsTransFB formBean) {
	 * 
	 * String strChkValue = request.getParameter("chkValue");
	 * 
	 * ClientApprovalDetailsTransVO voObj = new ClientApprovalDetailsTransVO();
	 * 
	 * String temp[] = strChkValue.replace("@", "#").split("#");
	 * 
	 * voObj.setStrClientPatientNo(temp[0]);
	 * voObj.setStrClientPatientSrNo(temp[1]);
	 * 
	 * ClientApprovalDetailsTransBO bo = new ClientApprovalDetailsTransBO();
	 * 
	 * bo.initCloseDetails(voObj);
	 * 
	 * formBean.setStrCrNo(voObj.getStrCrNo());
	 * 
	 * TransferObjectFactory.populateData(formBean, voObj);
	 * 
	 * formBean.setStrMsgString(voObj.getStrMsgString());
	 * formBean.setStrMsgType(voObj.getStrMsgType()); }
	 */

}
