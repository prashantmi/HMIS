package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;
import javax.sql.rowset.WebRowSet;

import billing.dao.PrimaryKeyDAO;
import billing.dao.PrimaryKeyLogDAO;

/**
 * @author Anshul Jindal This is DAO File i.e; DATA ACCESS OBJECT FILE. This
 *         file is used to interact with the database by calling the
 *         procedures,functions or queries.
 */
public class OnlineRequestCancellationTransDAO {

	/**
	 * This method calls the procedure to find REQUEST DETAILS according to CR
	 * No.
	 * 
	 * @param vo
	 */
	public static void getOnlineRequestDetailsProc(
			OnlineRequestCancellationTransVO vo) {

		String strCrNo = null;
		WebRowSet wb = null;

		String PROC_NAME = "";
		PROC_NAME = "{call pkg_bill_view.proc_sblt_inbound_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		HisDAO dao = null;

		int procIndex = 0;

		String strErr = "";

		try {
			strCrNo = vo.getStrCrNo();
			dao = new HisDAO("billing",
					"OnlineRequestCancellationTransDAO.getOnlineRequestDetailsProc");
			procIndex = dao.setProcedure(PROC_NAME);

			dao.setProcInValue(procIndex, "mode_type", "11", 1);

			dao.setProcInValue(procIndex, "crno", strCrNo, 2);

			dao.setProcInValue(procIndex, "seatId", "0", 11);
			dao.setProcInValue(procIndex, "chargeTypeId", "", 3);
			dao.setProcInValue(procIndex, "patListType", "", 4);
			dao.setProcInValue(procIndex, "searchStr", "", 5);
			dao.setProcInValue(procIndex, "searchType", "", 6);
			dao.setProcInValue(procIndex, "frmRows", "", 7);
			dao.setProcInValue(procIndex, "toRows", "", 8);
			dao.setProcInValue(procIndex, "deptCode", "", 10);

			dao.setProcInValue(procIndex, "hosp_code", vo.getStrHospitalCode(),
					9); // New Value

			dao.setProcOutValue(procIndex, "err", 1, 12);

			dao.setProcOutValue(procIndex, "resultset", 2, 13);

			dao.executeProcedureByPosition(procIndex);

			strErr = dao.getString(procIndex, "err");

			if (strErr == null)
				strErr = "";
			wb = dao.getWebRowSet(procIndex, "resultset");

			if (strErr.equals("")) {
				vo.setStrOnlineReqDtlWs(wb);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo
					.setStrErrMsg("Request Details are not displaying due to some error, see Error Log");
			vo
					.setStrMsgString("OnlineRequestCancellationTransDAO.getOnlineRequestDetailsProc() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * This method calls a procedure to find Consultant codes and Names used to
	 * generate Canceled By combo. Set the WebRowSet in vo which can be access
	 * in BO to generate combo options
	 * 
	 * @param vo
	 * @return
	 */
	public static void getCancelledByCmbProc(OnlineRequestCancellationTransVO vo) {

		WebRowSet wb = null;

		String PROC_NAME = "";
		PROC_NAME = "{call pkg_simple_view.Proc_user_list(?,?,?,?,?)}";

		HisDAO dao = null;

		int procIndex = 0;

		String strErr = "";

		try {
			dao = new HisDAO("billing",
					"OnlineRequestCancellationTransDAO.getCancelledByCmbProc");
			procIndex = dao.setProcedure(PROC_NAME);
			dao.setProcInValue(procIndex, "hosp_code", vo.getStrHospitalCode(),
					2); // New Value

			dao.setProcInValue(procIndex, "processId", "4", 1);

			dao.setProcInValue(procIndex, "seatId", vo.getStrSeatId(), 3);
			dao.setProcOutValue(procIndex, "err", 1, 4);
			dao.setProcOutValue(procIndex, "resultset", 2, 5);
			// Execute Procedure
			dao.executeProcedureByPosition(procIndex);

			strErr = dao.getString(procIndex, "err");

			if (strErr == null) {
				strErr = "";
			}
			wb = dao.getWebRowSet(procIndex, "resultset");
			vo.setStrCancelledByWs(wb);
			if (strErr.equals("")) {
				vo.setStrCancelledByWs(wb);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			vo
					.setStrMsgString("OnlineRequestCancellationTransDAO.getCancelledByCmbProc() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}

		finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * This method calls a procedure to find Remarks used to generate Cancel
	 * Reason Combo. Set the WebRowSet in vo which can be access in BO to
	 * generate combo options
	 * 
	 * @param vo
	 * @return
	 */
	public static void getCancelReasonProc(OnlineRequestCancellationTransVO vo) {

		WebRowSet wb = null;

		String PROC_NAME = "";
		PROC_NAME = "{call pkg_simple_view.proc_hblt_remarks_mst(?,?,?,?,?)}";

		HisDAO dao = null;

		int procIndex = 0;

		String strErr = "";

		try {
			dao = new HisDAO("billing",
					"OnlineRequestCancellationTransDAO.getCancelReasonProc");
			procIndex = dao.setProcedure(PROC_NAME);

			dao.setProcInValue(procIndex, "rmkstype", "2", 1);
			dao.setProcInValue(procIndex, "modeVal", "1", 2);

			dao.setProcInValue(procIndex, "hosp_code", vo.getStrHospitalCode(),
					3); // New Value
			dao.setProcOutValue(procIndex, "err", 1, 4);
			dao.setProcOutValue(procIndex, "resultset", 2, 5);

			dao.executeProcedureByPosition(procIndex);

			strErr = dao.getString(procIndex, "err");

			if (strErr == null)
				strErr = "";
			wb = dao.getWebRowSet(procIndex, "resultset");
			vo.setStrCancelReasonWs(wb);

			if (strErr.equals("")) {
				vo.setStrCancelReasonWs(wb);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			vo
					.setStrMsgString("OnlineRequestCancellationTransDAO.getCancelReasonProc() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * This method calls when we click on a Request No. hyperlink to access the
	 * Tariff details used to generate a pop up in HLP
	 * 
	 * @param vo
	 */
	public static void getPopUpInfoProc(OnlineRequestCancellationTransVO vo) {

		WebRowSet wb = null;

		String PROC_NAME = "";
		PROC_NAME = "{call pkg_bill_view.proc_hblt_billreq_tariff_dtl(?,?,?,?,?,?,?)}";

		HisDAO dao = null;

		int procIndex = 0;

		String strErr = "";

		try {
			dao = new HisDAO("billing",
					"OnlineRequestCancellationTransDAO.getPopUpInfoProc");
			procIndex = dao.setProcedure(PROC_NAME);

			dao.setProcInValue(procIndex, "reqno", vo.getStrRequestNo(), 1);
			dao.setProcInValue(procIndex, "modeval", "5", 2);

			dao.setProcInValue(procIndex, "chargeTypeId", "", 4);
			dao.setProcInValue(procIndex, "deptCode", "", 5);

			dao.setProcInValue(procIndex, "hosp_code", vo.getStrHospitalCode(),
					3); // New Value
			dao.setProcOutValue(procIndex, "err", 1, 6);
			dao.setProcOutValue(procIndex, "resultset", 2, 7);

			dao.executeProcedureByPosition(procIndex);

			strErr = dao.getString(procIndex, "err");

			if (strErr == null) {
				strErr = "";
			}
			wb = dao.getWebRowSet(procIndex, "resultset");

			if (strErr.equals("")) {
				vo.setStrPopUpWs(wb);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			vo
					.setStrMsgString("OnlineRequestCancellationTransDAO.getPopUpInfoProc() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}

		finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * This method calls on SAVE button to cancel selected Request and insert
	 * the data into database. Trans No., Receipt No. and all other data to be
	 * inserted will come from check box values . To generate a CANCEL No., We
	 * call some methods of PrimaryKeyDAO class and to rollback generated CANCEL
	 * No.s (in case of any failure) we call some method of PrimaryKeyLogDAO
	 * class.This will write the error in log file.
	 * 
	 * @param vo
	 */
	public static void insertDataProc(OnlineRequestCancellationTransVO vo) {
		HisDAO dao = null;
		String PROC_NAME = "";
		int procIndex = 0;
		String strErr = "";
		String strCancelNo = "";
		PrimaryKeyDAO keyDAO = null;
		PrimaryKeyLogDAO keyLogDAO = null;

		String chk[] = new String[50];
		int noOfChk = 0;
		String temp[] = new String[23];

		String strTransNo = "";
		String strChargeTypeId = "";
		String strReceiptType = "";
		String strBServiceId = "";
		String strPatAccNo = "";
		String strReqNo = "";
		String strReceiptNo = "";

		try {
			dao = new HisDAO("billing",
					"OnlineRequestCancellationTransDAO.insertDataProc");
			keyDAO = new PrimaryKeyDAO();
			keyLogDAO = new PrimaryKeyLogDAO();

			PROC_NAME = "{call Pkg_Bill_Dml.DML_ONLINE_REQ_CANCELLATION (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			procIndex = dao.setProcedure(PROC_NAME);

			chk = vo.getStrChkValues().replace(',', '#').split("#");
			noOfChk = chk.length - 1;

			// System.out.println("no of chk--"+noOfChk);

			for (int i = 1; i <= noOfChk; i++) {
				// System.out.println("INside On-Line Requet Cancellation
				// DAO---->"+chk[i]);
				temp = chk[i].replace('^', '#').split("#");
				strTransNo = temp[7];
				strChargeTypeId = temp[3];
				strReceiptType = temp[13];
				strBServiceId = temp[4];
				strPatAccNo = temp[11];
				strReqNo = temp[0];
				strReceiptNo = temp[20];

				keyDAO.setStrKeyname("CANCELNO");
				keyDAO.setStrBlockkey("1");
				keyDAO.setStrHospCode(vo.getStrHospitalCode());
				keyDAO.select(dao);

				strCancelNo = keyDAO.getStrPrimrayKeyValue();
				// System.out.println("in dao
				// hoscode--"+vo.getStrHospitalCode());
				/*
				 * System.out.println("in dao
				 * hoscode--"+vo.getStrHospitalCode()); System.out.println("in
				 * dao cancel no--"+strCancelNo); System.out.println("in dao
				 * reqNo--"+strReqNo); System.out.println("in dao trans
				 * no--"+strTransNo); System.out.println("in dao receipt
				 * no--"+strReceiptNo); System.out.println("in dao
				 * chargeTypeId--"+strChargeTypeId); System.out.println("in dao
				 * bServiceId--"+strBServiceId); System.out.println("in dao
				 * receiptType--"+strReceiptType); System.out.println("in dao
				 * cancelled by--"+vo.getStrCancelledBy());
				 * System.out.println("in dao cancel
				 * reason--"+vo.getStrCancelReason()); System.out.println("in
				 * dao seat id--"+vo.getStrSeatId());
				 */

				dao.setProcInValue(procIndex, "hosp_code", vo
						.getStrHospitalCode(), 1);
				dao.setProcInValue(procIndex, "cancelNo", strCancelNo, 2);
				dao.setProcInValue(procIndex, "reqNo", strReqNo, 3);
				dao.setProcInValue(procIndex, "billNo", strTransNo, 4);
				dao.setProcInValue(procIndex, "receiptNo", strReceiptNo, 5);
				dao.setProcInValue(procIndex, "chargeTypeId", strChargeTypeId,
						6);
				dao.setProcInValue(procIndex, "bServiceId", strBServiceId, 7);
				dao.setProcInValue(procIndex, "receiptType", strReceiptType, 8);
				dao.setProcInValue(procIndex, "empNo", vo.getStrCancelledBy(),
						9);
				dao.setProcInValue(procIndex, "cancelReason", vo
						.getStrCancelReason(), 10);
				dao.setProcInValue(procIndex, "patAccNo", strPatAccNo, 11);
				dao.setProcInValue(procIndex, "seatId", vo.getStrSeatId(), 12);
				dao.setProcInValue(procIndex, "cancelmode", "", 13);
				dao.setProcInValue(procIndex, "tariffid", "", 14);
				dao.setProcInValue(procIndex, "ipAddr", vo.getStrIpAddress(),
						15);
				dao.setProcOutValue(procIndex, "err", 1, 16);

				dao.execute(procIndex, 1);
			}

			synchronized (dao) {
				dao.fire();
			}

			strErr = dao.getString(procIndex, "err");

			if (strErr == null) {
				strErr = "";
			}
			if (strErr.equals("")) {
				// System.out.println("data inserted successfully");
				vo
						.setStrMsg("Selected Request has been cancelled successfully");

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrPrimaryKeyMsg(e.getMessage());
			try {
				keyLogDAO.setStrBlockkey("1");
				keyLogDAO.setStrError(vo.getStrPrimaryKeyMsg());
				keyLogDAO.setStrKeyname("CANCELNO");
				keyLogDAO.setStrHospCode(vo.getStrHospitalCode());
				keyLogDAO.setStrStartkey(strCancelNo);
				keyLogDAO.setStrSeatid(vo.getStrSeatId());
				keyLogDAO.insert(dao);
			} catch (Exception e1) {
				vo
						.setStrMsgString("OnlineRequestCancellationTransDAO.insertDataProc() --> "
								+ e1.getMessage());
				vo.setStrMsgType("1");
			}
			vo
					.setStrMsgString("BillingCancellationTransDAO.insertDataProc() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			if (keyDAO != null) {
				// keyDAO.free();
				keyDAO = null;
			}
			if (keyLogDAO != null) {
				// keyLogDAO.free();
				keyLogDAO = null;
			}
		}

	}

}
