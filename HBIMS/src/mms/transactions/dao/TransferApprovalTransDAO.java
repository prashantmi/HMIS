package mms.transactions.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import mms.transactions.vo.TransferApprovalTransVO;

public class TransferApprovalTransDAO {

	public static void getDWHList(TransferApprovalTransVO vo) {

		String err = "";
		int procIndex1 = 0;

		WebRowSet ws = null;

		HisDAO dao = null;

		try {

			dao = new HisDAO("mms", "TransferApprovalTransDAO.getDWHList()");

			String proc_name1 = "{call PKG_MMS_VIEW.proc_transfer_order_dtl(?,?,?,?)}";
			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modeval", "4");
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode());

			dao.setProcOutValue(procIndex1, "ERR", 1);
			dao.setProcOutValue(procIndex1, "resultset", 2);

			dao.executeProcedure(procIndex1);

			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			if (!err.equals(""))
				throw new Exception(err);

			ws = dao.getWebRowSet(procIndex1, "resultset");

			vo.setWsDwhList(ws);

		} catch (Exception e) {

			vo.setStrMsgString("TransferApprovalTransDAO.getDWHList() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	public static void getDemandRequestDetails(
			TransferApprovalTransVO vo) {

		String err = "";
		int procIndex1 = 0;

		WebRowSet ws = null;

		HisDAO dao = null;

		try {

			dao = new HisDAO("mms",
					"TransferApprovalTransDAO.getDemandRequestDetails()");

			String proc_name1 = "{call PKG_MMS_VIEW.proc_transfer_order_dtl(?,?,? ,?,?)}";
			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modeval", "5");
			dao.setProcInValue(procIndex1, "strId", vo.getStrStoreId());
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode());

			dao.setProcOutValue(procIndex1, "ERR", 1);
			dao.setProcOutValue(procIndex1, "resultset", 2);

			dao.executeProcedure(procIndex1);

			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			if (!err.equals(""))
				throw new Exception(err);

			ws = dao.getWebRowSet(procIndex1, "resultset");

			vo.setWsDemandRequestDetails(ws);

		} catch (Exception e) {

			vo.setStrMsgString("TransferApprovalTransDAO.getDemandRequestDetails() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	public static void getTransferingDetails(
			TransferApprovalTransVO vo) {

		String err = "";
		int procIndex1 = 0;

		WebRowSet ws = null;

		HisDAO dao = null;

		try {

			dao = new HisDAO("mms",
					"TransferApprovalTransDAO.getTransferingDetails()");

			String proc_name1 = "{call PKG_MMS_VIEW.proc_transfer_order_dtl(?,?,?,?,?)}";
			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modeval", "6");
			dao.setProcInValue(procIndex1, "reqNo", vo.getStrItemBrandId()); // in
																				// id
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode());

			dao.setProcOutValue(procIndex1, "ERR", 1);
			dao.setProcOutValue(procIndex1, "resultset", 2);

			dao.executeProcedure(procIndex1);

			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			if (!err.equals(""))
				throw new Exception(err);

			ws = dao.getWebRowSet(procIndex1, "resultset");

			vo.setWsTransferingDetails(ws);

		} catch (Exception e) {

			vo.setStrMsgString("TransferApprovalTransDAO.getTransferingDetails() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	public static void getOrderDetails(TransferApprovalTransVO vo) {

		String err = "";
		int procIndex1 = 0;

		WebRowSet ws = null;

		HisDAO dao = null;

		try {

			dao = new HisDAO("mms",
					"TransferApprovalTransDAO.getDemandRequestDetails()");

			String proc_name1 = "{call PKG_MMS_VIEW.proc_transfer_order_dtl(?,?,? ,?,?)}";
			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modeval", "3");
			dao.setProcInValue(procIndex1, "reqNo", vo.getStrOrderNo());
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode());

			dao.setProcOutValue(procIndex1, "ERR", 1);
			dao.setProcOutValue(procIndex1, "resultset", 2);

			dao.executeProcedure(procIndex1);

			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			if (!err.equals(""))
				throw new Exception(err);

			ws = dao.getWebRowSet(procIndex1, "resultset");

			if (ws != null && ws.size() > 0) {

				if (ws.next()) {

					// 1. order no.
					vo.setStrOrderDate(ws.getString(2));
					vo.setStrDemandingDDW(ws.getString(3));
					vo.setStrRequestNo(ws.getString(4));
					// 5. trans ddw name
					vo.setStrTransRequestNo(ws.getString(6));
					// 7. grp name
					vo.setStrDrugName(ws.getString(8));
					vo.setStrOrderQty(ws.getString(9));
					// 10. Trans qty.
					// 11. ack qty.
					// 12. order remarks
					// 13. sub group
					
					String[] strDetails = ws.getString(14).replace("^", "#").split("#");
					vo.setStrDemandDate(strDetails[0]);
					vo.setStrDemandQty(strDetails[1]+" "+strDetails[4]);
					vo.setStrOrderedQty(strDetails[2]+" "+strDetails[4]);
					vo.setStrBalanceQty(strDetails[3]+" "+strDetails[4]);
					vo.setStrBalanceQtyBaseValue(strDetails[3]);
					
					vo.setStrTransStoreId(ws.getString(15));
					vo.setStrItemBrandId(ws.getString(16));
					vo.setStrStoreId(ws.getString(17));

				}

			}

		} catch (Exception e) {

			vo.setStrMsgString("TransferApprovalTransDAO.getDemandRequestDetails() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	public synchronized static void insertOrderGenerate(
			TransferApprovalTransVO vo) {

		String err = "";
		int procIndex1 = 0;

		HisDAO dao = null;

		try {

			dao = new HisDAO("mms", "TransferApprovalTransDAO.getDWHList()");

			String proc_name1 = "{call PKG_MMS_DML.dml_transfer_order_dtls(?,?,?,?,?,?,?,?,?,?,?)}";

			for (int j = 0; j < vo.getStrTransferOrderQtys().length; j++) {

				if (vo.getStrTransferOrderQtys()[j] != null
						&& vo.getStrTransferOrderQtys()[j].trim().length() > 0
						&& !vo.getStrTransferOrderQtys()[j].trim().equals("")) {

					procIndex1 = dao.setProcedure(proc_name1);

					dao.setProcInValue(procIndex1, "modval", "1");
					dao.setProcInValue(procIndex1, "hospital_code",
							vo.getStrHospitalCode());

					dao.setProcInValue(procIndex1, "demandStrId",
							vo.getStrStoreId());
					dao.setProcInValue(procIndex1, "itemBrandId",
							vo.getStrItemBrandId());

					dao.setProcInValue(procIndex1, "demandNo",
							vo.getStrRequestNo());

					dao.setProcInValue(procIndex1, "transStrId",
							vo.getStrTransferStoreIds()[j]);
					dao.setProcInValue(procIndex1, "transNo",
							vo.getStrTransferRequestNos()[j]);

					dao.setProcInValue(procIndex1, "orderQty",
							vo.getStrTransferOrderQtys()[j]);

					dao.setProcInValue(procIndex1, "remarks",
							vo.getStrRemarks());

					dao.setProcInValue(procIndex1, "seat_id", vo.getStrSeatId());

					dao.setProcOutValue(procIndex1, "err", 1);

					dao.execute(procIndex1, 1);

				}

			}

			dao.fire();

			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";
			if (!err.equals(""))
				throw new Exception(err);

		} catch (Exception e) {

			vo.setStrMsgString("TransferApprovalTransDAO.insertOrderGenerate() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}
	}

	public static void getTransferingDetailsModify(
			TransferApprovalTransVO vo) {

		String err = "";
		int procIndex1 = 0;

		WebRowSet ws = null;

		HisDAO dao = null;

		String strItemBrandId = vo.getStrTransRequestNo() + "^"
				+ vo.getStrOrderNo();
		String strStoreId = vo.getStrTransStoreId();

		try {

			dao = new HisDAO("mms",
					"TransferApprovalTransDAO.getTransferingDetails()");

			String proc_name1 = "{call PKG_MMS_VIEW.proc_transfer_order_dtl(?,?,?,?,?,?)}";
			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modeval", "7");
			/* reqNo = transfer request no ^ item brand id */
			dao.setProcInValue(procIndex1, "reqNo", strItemBrandId);

			dao.setProcInValue(procIndex1, "strId", strStoreId);
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode());

			dao.setProcOutValue(procIndex1, "ERR", 1);
			dao.setProcOutValue(procIndex1, "resultset", 2);

			dao.executeProcedure(procIndex1);

			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			if (!err.equals(""))
				throw new Exception(err);

			ws = dao.getWebRowSet(procIndex1, "resultset");

			vo.setWsTransferingDetails(ws);

		} catch (Exception e) {
			e.printStackTrace();

			vo.setStrMsgString("TransferApprovalTransDAO.getTransferingDetails() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	
	public synchronized static void insertOrderModify(
			TransferApprovalTransVO vo) {

		String err = "";
		int procIndex1 = 0;
		int count = 0;

		HisDAO dao = null;

		try {

			dao = new HisDAO("mms", "TransferApprovalTransDAO.getDWHList()");

			String proc_name1 = "{call PKG_MMS_DML.dml_transfer_order_modify_dtls(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

			for (int j = 0; j < vo.getStrTransferOrderQtys().length; j++) {
				
				if (vo.getStrTransferOrderQtys()[j] != null
						&& vo.getStrTransferOrderQtys()[j].trim().length() > 0
						&& !vo.getStrTransferOrderQtys()[j].trim().equals("")) {

					count = count + 1;
					
					
					procIndex1 = dao.setProcedure(proc_name1);

					dao.setProcInValue(procIndex1, "modval", "1");
									
					dao.setProcInValue(procIndex1, "orderNo",
							vo.getStrOrderNo());
					
					dao.setProcInValue(procIndex1, "counter", String.valueOf(count));
					
					dao.setProcInValue(procIndex1, "hospital_code",
							vo.getStrHospitalCode());

					dao.setProcInValue(procIndex1, "demandStrId",
							vo.getStrStoreId());
					dao.setProcInValue(procIndex1, "itemBrandId",
							vo.getStrItemBrandId());

					dao.setProcInValue(procIndex1, "demandNo",
							vo.getStrDemandNo());

					dao.setProcInValue(procIndex1, "transStrId",
							vo.getStrTransferStoreIds()[j]);
					dao.setProcInValue(procIndex1, "transNo",
							vo.getStrTransferRequestNos()[j]);

					dao.setProcInValue(procIndex1, "orderQty",
							vo.getStrTransferOrderQtys()[j]);

					dao.setProcInValue(procIndex1, "remarks",
							vo.getStrRemarks());

					dao.setProcInValue(procIndex1, "seat_id", vo.getStrSeatId());

					dao.setProcOutValue(procIndex1, "err", 1);

					dao.execute(procIndex1, 1);

				}

			}

			dao.fire();

			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";
			if (!err.equals(""))
				throw new Exception(err);

		} catch (Exception e) {

			vo.setStrMsgString("TransferApprovalTransDAO.insertOrderModification() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}
	}

	
	public synchronized static void demandReject(TransferApprovalTransVO vo) {

		String err = "";
		int procIndex1 = 0;

		HisDAO dao = null;

		try {

			dao = new HisDAO("mms", "TransferApprovalTransDAO.demandReject()");

			String proc_name1 = "{call PKG_MMS_DML.dml_transfer_demand_dtls(?,?,?,?,?,?,?)}";
			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modval", "4");
			dao.setProcInValue(procIndex1, "seat_id", vo.getStrSeatId());
			dao.setProcInValue(procIndex1, "remarks", vo.getStrRemarks());
			dao.setProcInValue(procIndex1, "req_No", vo.getStrRequestNo());
			dao.setProcInValue(procIndex1, "store_id", vo.getStrStoreId());
			dao.setProcInValue(procIndex1, "hospital_code",
					vo.getStrHospitalCode());

			dao.setProcOutValue(procIndex1, "ERR", 1);

			dao.execute(procIndex1, 1);

			dao.fire();

			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			if (!err.equals(""))
				throw new Exception(err);

		} catch (Exception e) {

			vo.setStrMsgString("TransferApprovalTransDAO.demandReject() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	public synchronized static void transferReject(TransferApprovalTransVO vo) {

		String err = "";
		int procIndex1 = 0;

		HisDAO dao = null;

		try {

			dao = new HisDAO("mms", "TransferApprovalTransDAO.transferReject()");

			String proc_name1 = "{call PKG_MMS_DML.dml_transfer_transreq_dtls(?,?,?,?,?,?,?)}";
			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modval", "4");
			dao.setProcInValue(procIndex1, "seat_id", vo.getStrSeatId());
			dao.setProcInValue(procIndex1, "remarks", vo.getStrRemarks());
			dao.setProcInValue(procIndex1, "req_No", vo.getStrRequestNo());
			dao.setProcInValue(procIndex1, "store_id", vo.getStrStoreId());
			dao.setProcInValue(procIndex1, "hospital_code",
					vo.getStrHospitalCode());

			dao.setProcOutValue(procIndex1, "ERR", 1);

			dao.execute(procIndex1, 1);

			dao.fire();

			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			if (!err.equals(""))
				throw new Exception(err);

		} catch (Exception e) {

			vo.setStrMsgString("TransferApprovalTransDAO.transferReject() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	public synchronized static void transferForcefullyClose(
			TransferApprovalTransVO vo) {

		String err = "";
		int procIndex1 = 0;

		HisDAO dao = null;

		try {

			dao = new HisDAO("mms",
					"TransferApprovalTransDAO.transferForcefullyClose()");

			String proc_name1 = "{call PKG_MMS_DML.dml_transfer_transreq_dtls(?,?,?,?,?,?,?)}";
			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modval", "5");
			dao.setProcInValue(procIndex1, "seat_id", vo.getStrSeatId());
			dao.setProcInValue(procIndex1, "remarks", vo.getStrRemarks());
			dao.setProcInValue(procIndex1, "req_No", vo.getStrRequestNo());
			dao.setProcInValue(procIndex1, "store_id", vo.getStrStoreId());
			dao.setProcInValue(procIndex1, "hospital_code",
					vo.getStrHospitalCode());

			dao.setProcOutValue(procIndex1, "ERR", 1);

			dao.execute(procIndex1, 1);

			dao.fire();

			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			if (!err.equals(""))
				throw new Exception(err);

		} catch (Exception e) {

			vo.setStrMsgString("TransferApprovalTransDAO.transferForcefullyClose() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	

	public synchronized static void cancelOrder(
			TransferApprovalTransVO vo) {

		String err = "";
		int procIndex1 = 0;

		HisDAO dao = null;

		try {

			dao = new HisDAO("mms",
					"TransferApprovalTransDAO.cancelOrder()");

			String proc_name1 = "{call PKG_MMS_DML.dml_transfer_order_cancel_dtls(?,?,?,?,?,?)}";
			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modval", "1");
			dao.setProcInValue(procIndex1, "seat_id", vo.getStrSeatId());
			dao.setProcInValue(procIndex1, "remarks", vo.getStrRemarks());
			dao.setProcInValue(procIndex1, "orderNo", vo.getStrOrderNo());
			dao.setProcInValue(procIndex1, "hospital_code",
					vo.getStrHospitalCode());

			dao.setProcOutValue(procIndex1, "ERR", 1);

			dao.execute(procIndex1, 1);

			dao.fire();

			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			if (!err.equals(""))
				throw new Exception(err);

		} catch (Exception e) {

			vo.setStrMsgString("TransferApprovalTransDAO.cancelOrder() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}
	
}
