package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.dao.PrimaryKeyDAO;
import billing.dao.PrimaryKeyLogDAO;

public class BillReconcileTransDAO {

	public static void getBillDetails(BillReconcileTransVO vo) {
		String proc_name = "";
		proc_name = "{call pkg_bill_view.proc_SBLT_OUTBOUND_DTL(?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		// WebRowSet ws = null;

		try {
			dao = new HisDAO("billing",
					"transactions.BillReconcileTransDAO .getBillDetails()");
			nprocIndex = dao.setProcedure(proc_name);

			dao.setProcInValue(nprocIndex, "crNo", vo.getStrCrNo());
			dao.setProcInValue(nprocIndex, "modeval", "2");
			dao.setProcInValue(nprocIndex, "chargeTypeId", "4");
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1");
			dao.setProcInValue(nprocIndex, "searchStr", "");
			dao.setProcInValue(nprocIndex, "patListType", "0");
			dao.setProcInValue(nprocIndex, "searchType", "1");
			dao.setProcInValue(nprocIndex, "frmRows", "0");
			dao.setProcInValue(nprocIndex, "toRows", "0");
			
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode()); // New Value
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return

			dao.setProcOutValue(nprocIndex, "RESULTSET", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";

			vo.setBillDtlList(dao.getWebRowSet(nprocIndex, "RESULTSET"));
		} catch (Exception e) {

			vo.setStrMsgString("BillReconcileTransDAO.getBillDetails() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		// return ws;
	}

	/**
	 * Get bill tariff details from bill service detail table [account does not
	 * exist]
	 * 
	 * @param vo
	 */
	public static void getTariffDetails_NoAcc(BillReconcileTransVO vo) {
		WebRowSet ws = null;
		String strTemp[] = (vo.getStrValmode()).replace('^', '#').split("#");
		String proc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";

		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_BILL_SERVICE_DTL(?,?,?,?,?,?,?,?)}";

		try {
			dao = new HisDAO("billing",
					"transactions.BillReconcileTransDAO.getTariffDetails_NoAcc()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo ", strTemp[0]);
			dao.setProcInValue(nprocIndex, "modeval", "1");
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1");
			dao.setProcInValue(nprocIndex, "recNo", "0");
			dao.setProcInValue(nprocIndex, "recType", "1");
	
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode()); // New Value
			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";

			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

			vo.setBServiceDtls(ws);

		} catch (Exception e) {

			vo
					.setStrMsgString("BillReconcileTransDAO.getTariffDetails_NoAcc() --> "
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
	 * Get bill tariff details from bill service detail table [account exists]
	 * 
	 * @param vo
	 */
	public static void getTariffDetails_Acc(BillReconcileTransVO vo) {
		WebRowSet ws = null;
		String strTemp[] = (vo.getStrValmode()).replace('^', '#').split("#");
		String proc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";

		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_PATACCOUNT_SERVICE(?,?,?,?,?,?,?,?,?,?)}";

		try {
			dao = new HisDAO("billing",
					"transactions.BillReconcileTransDAO.getTariffDetails_Acc()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo ", strTemp[0]);
			dao.setProcInValue(nprocIndex, "accNo ", strTemp[4]);
			dao.setProcInValue(nprocIndex, "modeval", "1");
	
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1");
			dao.setProcInValue(nprocIndex, "groupId", "");
			dao.setProcInValue(nprocIndex, "recNo", "0");
			dao.setProcInValue(nprocIndex, "recType", "1");
			
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode()); // New Value
			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";

			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			if (ws != null)
				vo.setBServiceDtls(ws);

		} catch (Exception e) {

			vo
					.setStrMsgString("BillReconcileTransDAO.getTariffDetails_Acc() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	public static void getPkgLst_NoAcc(BillReconcileTransVO vo) {
		String strTemp[] = (vo.getStrValmode()).replace('^', '#').split("#");
		String proc_name = "";
		proc_name = "{call pkg_bill_view.PROC_HBLT_BILL_SERVICE_DTL(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("billing",
					"transactions.RefundApprovalTransDAO.getPkgLst_NoAcc()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo", strTemp[0]);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "0");
			dao.setProcInValue(nprocIndex, "modeval", "1");
		
			dao.setProcInValue(nprocIndex, "recNo", "0");
			dao.setProcInValue(nprocIndex, "recType", "1");
			
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode()); // New Value
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");

			if (ws != null) {

				vo.setBServiceDtls(ws);
			}

		} catch (Exception e) {
			vo.setStrMsgString("BillRecncileTransDAO.getPkgLst_NoAcc--> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	public static void getPkgLst_Acc(BillReconcileTransVO vo) {
		String strTemp[] = (vo.getStrValmode()).replace('^', '#').split("#");
		String proc_name = "";
		proc_name = "{call pkg_bill_view.PROC_HBLT_PATACCOUNT_SERVICE(?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("billing",
					"transactions.RefundApprovalTransDAO .getPkgLst_Acc()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo", strTemp[0]);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "0");
			dao.setProcInValue(nprocIndex, "accNo", strTemp[4]);
			dao.setProcInValue(nprocIndex, "modeval", "1");
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode()); // New Value
			
			dao.setProcInValue(nprocIndex, "groupId", "");
			dao.setProcInValue(nprocIndex, "recNo", "0");
			dao.setProcInValue(nprocIndex, "recType", "1");
			
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");

			if (ws != null) {

				vo.setBServiceDtls(ws);

			}

		} catch (Exception e) {
			vo.setStrMsgString("BillRecncileTransDAO.getPkgLst_Acc--> "
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
	 * gets group wise details [if account exists and final settlement]
	 * 
	 * @param vo
	 */
	public static void getFSetLst(BillReconcileTransVO vo) {
		String strTemp[] = (vo.getStrValmode()).replace('^', '#').split("#");
		String proc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;

		proc_name = "{call pkg_bill_view.PROC_HBLT_PATACCOUNT_SERVICE(?,?,?,?,?,?,?,?,?,?)}";

		try {
			dao = new HisDAO("billing",
					"transactions.BillReconcileTransDAO.getFSetLst()");
			nprocIndex = dao.setProcedure(proc_name);

			dao.setProcInValue(nprocIndex, "billNo ", "0");
			dao.setProcInValue(nprocIndex, "accNo", strTemp[4]);
			dao.setProcInValue(nprocIndex, "modeval", "2");
			
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1");
			dao.setProcInValue(nprocIndex, "groupId", "");
			dao.setProcInValue(nprocIndex, "recNo", "0");
			dao.setProcInValue(nprocIndex, "recType", "1");
			
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode()); // New Value
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");

			if (ws != null)
				vo.setBServiceDtls(ws);

		} catch (Exception e) {

			vo.setStrMsgString("BillRecncileTransDAO.getFSetLst--> "
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
	 * To get group details in Final Settlement (consolidated) section
	 * 
	 * @param vo
	 */
	public static void getFinalSettlementDtls(BillReconcileTransVO vo) {

		String proc_name = "";

		// System.out.println("vo.getStrValmode() = " + vo.getStrValmode());
		// String strTemp[] = (vo.getStrValmode()).replace('^', '#').split("#");
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		String strTemp[] = (vo.getStrValmode()).replace('^', '#').split("#");
		proc_name = "{call pkg_bill_view.PROC_HBLT_PATACCOUNT_SERVICE(?,?,?,?,?,?,?,?,?,?)}";

		try {
			dao = new HisDAO("billing",
					"transactions.BillReconcileTransDAO.getFSetDtls()");

			// System.out.println("acc = " + strTemp[4]);
			// System.out.println("groupId = " + vo.getStrGroup());

			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "accNo", strTemp[4]); // Hard
			
			dao.setProcInValue(nprocIndex, "groupId", vo.getStrGroup());
			dao.setProcInValue(nprocIndex, "modeval", "4");
			dao.setProcInValue(nprocIndex, "billNo ", "0");
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1");
			dao.setProcInValue(nprocIndex, "recNo", "0");
			dao.setProcInValue(nprocIndex, "recType", "1");
			
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode()); // New Value
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				if (ws != null)
					vo.setFServiceDtls(ws);
				// System.out.println("size = " + ws.size());
			} else
				throw new Exception("No Group Details Exist !!");

		} catch (Exception e) {

			vo
					.setStrMsgString("BillRecncileTransDAO.getFinalSettlementDtls--> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/*
	 * public WebRowSet getRefundRsn() { WebRowSet ws = null; HisDAO dao = null;
	 * int nQryIndex; try { dao = new HisDAO("billing",
	 * "transactions.BillReconcileTransDAO .getRefundRsn()"); nQryIndex = dao
	 * .setQuery("select HBLSTR_REMARKS from HBLT_REMARKS_MST where
	 * GNUM_ISVALID=" + 1); ws = dao.executeQry(nQryIndex); } catch (Exception
	 * e) { } finally { dao.free(); dao = null; } return ws; }
	 */

	public WebRowSet getRefundBy() {
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call PKG_SIMPLE_VIEW.PROC_CONSULTANT_NAME(?,?,?,?,?)}";
		HisDAO dao = null;
		BillReconcileTransVO vo = null;
		int nprocIndex = 0;
		String strErr = "";
		try {

			vo = new BillReconcileTransVO();
			dao = new HisDAO("billing",
					"transactions.BillReconcileTransDAO .getRefundBy()");
			nprocIndex = dao.setProcedure(proc_name);
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode()); // New Value
			
			dao
			.setProcInValue(nprocIndex, "deptunitCode", "0");
			
			dao
			.setProcInValue(nprocIndex, "seatId", vo
					.getStrSeatId());
			
			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
		} catch (Exception e) {

			vo.setStrMsgString("BillRecncileTransDAO.getRefundBy--> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			dao.free();
			dao = null;
		}

		return ws;
	}

	/*
	 * public void getRsnRmk(BillReconcileTransVO vo) { HisUtil hisutil = new
	 * HisUtil("transaction", "BillReconcileTransHLP"); WebRowSet w; WebRowSet
	 * w1; try { w = getRefundRsn(); w1 = getRefundBy();
	 * 
	 * vo.setStrDisRsn(w); vo.setStrDisBy(w1);
	 * 
	 * String str2 = hisutil.getOptionValue(vo.getStrDisRsn(), "-1", "0^Select
	 * value", true); String str1 = hisutil.getOptionValue(vo.getStrDisBy(),
	 * "-1", "0^Select value", true); vo.setStrRsn(str2); vo.setStrRmk(str1); }
	 * catch (Exception e) { } w = w1 = null; }
	 */

	/**
	 * To get unit for reconciliation tariff
	 * 
	 * @param vo
	 */
	public static void unit(BillReconcileTransVO vo) {
		String proc_name = "";
		// HisUtil hisutil = new HisUtil("transaction",
		// "BillReconcileTransHLP");
		proc_name = "{call pkg_bill_view.PROC_GBLT_UNIT_MST(?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;

		try {
			dao = new HisDAO("billing",
					"transactions.BillReconcileTransDAO.getFSetLst()");
			nprocIndex = dao.setProcedure(proc_name);
			// dao.setProcInValue(nprocIndex, "hosp_code", "108");
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode()); // New Value
			dao.setProcInValue(nprocIndex, "unit_id", vo
					.getStrTariffUnitTempId());
			dao.setProcInValue(nprocIndex, "modeval", "1");
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");

			// System.out.println("Unit Ws Size "+ws.size());

			vo.setTariffUnitList(ws);
		} catch (Exception e) {

			vo.setStrMsgString("BillReconcileTransDAO.unit() -->"
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
	 * retrieves Off Line Group Combo
	 * 
	 * @param voObj -
	 *            ValueObject
	 */
	public static void getOffLineGroup(BillReconcileTransVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_bill_view.proc_HBLT_HSERVICE_GROUP_MST(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		String strHospitalCode = voObj.getStrHospitalCode();
		String strChargeTypeId = voObj.getStrHospitalServices();
		// String strIsPackageWise = voObj.getStrIsPackageWise();

		try {
			daoObj = new HisDAO("Cash Collection Transaction",
					"CashCollectionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode);
			daoObj.setProcInValue(nProcIndex, "charge_id", strChargeTypeId);
			 daoObj.setProcInValue(nProcIndex, "pkg_wise_group", "0");
			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);
			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				if (ws != null) {
					voObj.setGroupList(ws);
				}
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			voObj.setStrMsgString("BillReconcileTransDAO.getOffLineGroup() -->"
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * retrieves Off Line Tariff List
	 * 
	 * @param voObj -
	 *            ValueObject
	 */
	public static void getOffLineTariffList(BillReconcileTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "";
		String strErr = "";
		String mode = "1";

		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strGroupId = voObj.getStrGroup();
		String strChargeTypeId = voObj.getStrHospitalServices();
		String strCategoryCode = voObj.getStrCategoryCode();
		String strWardCode = voObj.getStrWardCode();
		// String strPackage = voObj.getStrOffLinePackageIndex();

		if (strChargeTypeId == null)
			strChargeTypeId = "";
		if (strCategoryCode == null)
			strCategoryCode = "";

		if (strGroupId == null )
			strGroupId = "";
		if (strWardCode == null)
			strWardCode = "";

		 

		strProcName = "{call PKG_BILL_VIEW.PROC_HBLT_CHARGE_MST(?,?,?,?,?,?,?,?,?,?,?,?)}";

		try {
			if (strChargeTypeId.equals("") || strCategoryCode.equals(""))
				throw new Exception(
						"Patient category & ChargeTypeId is mandatory !!");

			daoObj = new HisDAO("Bill Reconciliation", "getOffLineTariffList()");

			nProcIndex = daoObj.setProcedure(strProcName);

			// System.out.println(" procedure Name :"+strProcName);

			daoObj.setProcInValue(nProcIndex, "chargeTypeId", strChargeTypeId);
			daoObj.setProcInValue(nProcIndex, "catCode", strCategoryCode);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode);
			daoObj.setProcInValue(nProcIndex, "pkg_flag", "0");
			daoObj.setProcInValue(nProcIndex, "wardCode", strWardCode);
			daoObj.setProcInValue(nProcIndex, "groupId", strGroupId);
			daoObj.setProcInValue(nProcIndex, "searchtrfname", "");
			daoObj.setProcInValue(nProcIndex, "searchMode", "1");
			daoObj.setProcInValue(nProcIndex, "advance_flag", "1");
			daoObj.setProcInValue(nProcIndex, "modeVal", mode);
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (ws != null) {
				// while(ws.next()){
				// System.out.println("value :"+ws.getString(2));
				// }
				// ws.beforeFirst();
				voObj.setTariffList(ws);
			} else {
				// System.out.println("else ws");
				throw new Exception(strErr);
			}
		} catch (Exception e) {

			// System.out.println("errr :: " + e.getMessage());
			voObj
					.setStrMsgString("BillReconcileTransDAO.getOffLineTariffList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves approval list either for discount or reconciliation
	 * 
	 * type = 1 for reconciliation = 2 for discount
	 * 
	 * @param voObj
	 */
	public static void getApprovalList(BillReconcileTransVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		// BillReconcileTransVO voObj = null;
		String strProcName = "{call pkg_simple_view.proc_consultant_name(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {

			// voObj = new BillReconcileTransVO();
			daoObj = new HisDAO("Billing Reconciliation Transaction",
					"BillReconcileTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospitalCode()); 
			daoObj.setProcInValue(nProcIndex, "deptunitCode", "0");
			daoObj
			.setProcInValue(nProcIndex, "seatId", voObj
					.getStrSeatId());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				// System.out.println("setStrReconlByRmkList : "+ws.size());

				voObj.setStrDiscAppByList(ws);

				voObj.setStrReconlByList(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("BillReconcileTransDAO.getApprovalList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * retrieves reason list either for reconciliation or discount
	 * 
	 * reasonFor = 1 if reason for reconciliation = 2 if reason for discount
	 * 
	 * @param voObj
	 */
	public static void getRemarksList(BillReconcileTransVO voObj) {
		String proc_name = "";

		proc_name = "{call PKG_SIMPLE_VIEW.PROC_HBLT_REMARKS_MST(?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.REMARKSCOMBO()");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "rmksType", "4");

			dao.setProcInValue(nprocIndex, "modeVal", "1");

			dao.setProcInValue(nprocIndex, "hosp_code", voObj
					.getStrHospitalCode());

			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return
			// value

			dao.setProcOutValue(nprocIndex, "RESULTSET", 2); // 2 for object

			// execute procedure

			dao.executeProcedure(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

				// System.out.println("setStrReconlByList : "+ws.size());

				voObj.setStrDisByRmkList(ws);

				voObj.setStrReconlByRmkList(ws);

			} else {
				throw new Exception(strerr);
			}

		} catch (Exception e) {

			// e.printStackTrace();

			voObj
					.setStrMsgString("IpdBillManagementTransDAO.REMARKSCOMBO() --> "
							+ e.getMessage());

			voObj.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * retrieves unit list based on unit id and hospital code
	 * 
	 * @param voObj
	 */
	public static void getOffLineTariffUnitList(BillReconcileTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		// System.out.println("DAO : getOffLineTariffUnitList");

		String strProcName = "{call pkg_bill_view.proc_GBLT_UNIT_MST(?,?,?,?,?)}";
		int nProcIndex = 0;

		// String strHospitalCode = voObj.getStrHospitalCode();

		String strErr = "";

		try {

			daoObj = new HisDAO("BillReconcileTransDAO",
					"getOffLineTariffUnitList()");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "unit_id", voObj
					.getStrTariffUnitTempId());
			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setTariffUnitList(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("BillReconcileTransDAO.getOffLineTariffUnitList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

/*	public static void hbltPackageChgMstForBillService(BillReconcileTransVO vo,
			int i) {

		String strProcName = "{call pkg_bill_view. proc_HBLT_PACKAGE_CHARGE_MST(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		String[] strHiddenVal = null;
		String strErr = "";
		try {
			dao = new HisDAO("Bill Reconcile Trans ", "BillReconcileTransDAO");

			nProcIndex = dao.setProcedure(strProcName);

			dao
					.setProcInValue(nProcIndex, "hosp_code", vo
							.getStrHospitalCode());
			dao
					.setProcInValue(nProcIndex, "charge_id", vo
							.getStrChargeTypeId());
			dao.setProcInValue(nProcIndex, "cat_code", vo.getStrCategoryCode());

			if (vo.getStrWardCode() != null || vo.getStrWardCode() != "") {
				dao.setProcInValue(nProcIndex, "ward_id", vo.getStrWardCode());
			} else {
				dao.setProcInValue(nProcIndex, "ward_id", "0");
			}

			dao.setProcInValue(nProcIndex, "pkg_id", vo.getStrTariffTempId());
			dao.setProcInValue(nProcIndex, "modeval", "1");

			dao.setProcOutValue(nProcIndex, "err", 1);
			dao.setProcOutValue(nProcIndex, "resultset", 2);

			dao.executeProcedure(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = dao.getWebRowSet(nProcIndex, "resultset");
				vo.setStrBillWs(ws);
				strHiddenVal = new String[ws.size()];
				int m = 0;

				while (ws.next()) {
					strHiddenVal[m] = ws.getString(6);
					vo.setStrTempVal(strHiddenVal);
					m = m + 1;
					if (m == ws.size())
						break;
				}

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("BillReconcileTransDAO.hbltPackageChgMstForBillService() --> "
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

	public static void hbltPackageChgMstForBillReconcileService(
			BillReconcileTransVO vo) {

		String strProcName = "{call pkg_bill_view. proc_HBLT_PACKAGE_CHARGE_MST(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		HisDAO dao = null;
		WebRowSet ws = null;
		String strErr = "";
		try {
			dao = new HisDAO("Bill Reconcile Trans ", "BillReconcileTransDAO");

			nProcIndex = dao.setProcedure(strProcName);

			dao
					.setProcInValue(nProcIndex, "hosp_code", vo
							.getStrHospitalCode());
			dao.setProcInValue(nProcIndex, "charge_id", vo
					.getStrHospitalServices());
			dao.setProcInValue(nProcIndex, "cat_code", vo.getStrCategoryCode());

			if (vo.getStrWardCode() != null || vo.getStrWardCode() != "") {
				dao.setProcInValue(nProcIndex, "ward_id", vo.getStrWardCode());
			} else {
				dao.setProcInValue(nProcIndex, "ward_id", "0");
			}

			dao.setProcInValue(nProcIndex, "pkg_id", vo.getStrTariffTempId());
			dao.setProcInValue(nProcIndex, "modeval", "1");

			dao.setProcOutValue(nProcIndex, "err", 1);
			dao.setProcOutValue(nProcIndex, "resultset", 2);

			dao.executeProcedure(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = dao.getWebRowSet(nProcIndex, "resultset");
				
				vo.setStrBillRecWs(ws);
			

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("BillReconcileTransDAO.hbltPackageChgMstForBillReconcileService() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}

		finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}*/

	public static void insertDataProc(BillReconcileTransVO vo)

	{
		// System.out.println("calling insert data proc");
	
		HisDAO dao = null;
		PrimaryKeyDAO keyDAO = null;
		PrimaryKeyLogDAO keyLogDAO = null;

		//ApprovalDAO appDAO = null;
		//InBoundDAO inboundDAO = null;
		//BillRequisitionDAO billreqDAO = null;
		/*BillRequisitionTariffDAO billreqtrfDAO = null;
		BillReqPackageDAO billreqpackageDAO = null;
		PatientAccountServiceDAO pataccserDAO = null;
		BillServiceDAO billserviceDAO = null;
		PatientAccountPackageDAO pataccpackDAO = null;
		BillPackageDAO billpackageDAO = null;*/

		int appFlag = 0, appFlag1 = 0, reqFlag = 0;

		int nInsertedIndex1 = 0;
		int nInsertedIndex2 = 0;
		int nInsertedIndex3 = 0;
		
		String strAppId = "";
		String strReqNo = "";
		String strAppId_Dis = "";
	
		//String[] strHiddenVal = null;

		try {
			dao = new HisDAO("billing", "BillReconcileTransDAO.insertDataProc");

			keyDAO = new PrimaryKeyDAO();
			keyLogDAO = new PrimaryKeyLogDAO();

		/*  appDAO = new ApprovalDAO();
			inboundDAO = new InBoundDAO();
			billreqDAO = new BillRequisitionDAO();
			billreqtrfDAO = new BillRequisitionTariffDAO();
			billreqpackageDAO = new BillReqPackageDAO();
			pataccserDAO = new PatientAccountServiceDAO();
			billserviceDAO = new BillServiceDAO();
			pataccpackDAO = new PatientAccountPackageDAO();
			billpackageDAO = new BillPackageDAO();*/

			keyDAO.setStrKeyname("APPROVAL");
			keyDAO.setStrBlockkey("1");
			keyDAO.setStrHospCode(vo.getStrHospitalCode());
			keyDAO.select(dao);

			strAppId = keyDAO.getStrPrimrayKeyValue();

			if (!strAppId.equals("") || strAppId != null) {
				appFlag = 1;
			}

			
			// -----generate online request------

			keyDAO.setStrKeyname("REQNO");
			keyDAO.setStrBlockkey("1");
			keyDAO.setStrHospCode(vo.getStrHospitalCode());
			keyDAO.select(dao);

			strReqNo = keyDAO.getStrPrimrayKeyValue();
			if (strReqNo != "" || strReqNo != null) {
				reqFlag = 1;
			}

			
			nInsertedIndex1 = dao
			.setProcedure("{call pkg_bill_dml.Dml_reconcile_approval(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

				
			dao.setProcInValue(nInsertedIndex1, "modval", "1");
			dao.setProcInValue(nInsertedIndex1, "reqNo", strReqNo);
			dao.setProcInValue(nInsertedIndex1, "appId", strAppId);
			dao.setProcInValue(nInsertedIndex1, "hosp_code", vo.getStrHospitalCode());
			dao.setProcInValue(nInsertedIndex1, "deptCode", vo.getStrDepartmentCode());
			dao.setProcInValue(nInsertedIndex1, "chargeTypeId", vo.getStrChargeTypeId());
			dao.setProcInValue(nInsertedIndex1, "catCode", vo.getStrCatCode());
			dao.setProcInValue(nInsertedIndex1, "episodeCode", vo.getStrEpisodeCode());
			dao.setProcInValue(nInsertedIndex1, "patAccNo", vo.getStrPatAccountNo());
			dao.setProcInValue(nInsertedIndex1, "puk", vo.getStrCrNo());
			dao.setProcInValue(nInsertedIndex1, "admNo", vo.getStrAdmissionNo());
			dao.setProcInValue(nInsertedIndex1, "bServiceId", vo.getStrBserviceId());
			dao.setProcInValue(nInsertedIndex1, "serviceId", vo.getStrServiceId());
			dao.setProcInValue(nInsertedIndex1, "gblReqNo", vo.getStrGblRequestNo());
			dao.setProcInValue(nInsertedIndex1, "recBy", vo.getStrReconcilationBy());
			dao.setProcInValue(nInsertedIndex1, "recReason", vo.getStrReconcilationReasonText());
			dao.setProcInValue(nInsertedIndex1, "wardId", vo.getStrWardCode());
			
			dao.setProcInValue(nInsertedIndex1, "billNo",vo.getStrBillNo());
			dao.setProcInValue(nInsertedIndex1, "recNo", vo.getStrReceiptNo());
			
			dao.setProcInValue(nInsertedIndex1, "seatId", vo.getStrSeatId());
			
			// output value
			dao.setProcOutValue(nInsertedIndex1, "err", 1);
		
			// keep in batch
			dao.execute(nInsertedIndex1, 1);
			
			
			
			// ------put reconciliation details----------
/*
			appDAO.setStrApprovalId(strAppId);
			appDAO.setStrApprovalSlNo("1");
			appDAO.setStrApprovalType("3");
			appDAO.setStrBServiceId(vo.getStrBserviceId());
			appDAO.setStrChargeTypeId(vo.getStrChargeTypeId());
			appDAO.setStrEmpNo(vo.getStrReconcilationBy());
			appDAO.setStrUserLevel(vo.getStrUserLevel());
			appDAO.setStrRemarks(vo.getStrReconcilationReasonText());
			appDAO.setStrApprovalType("6");
			appDAO.setStrStatus("1");
			appDAO.setStrSeatId(vo.getStrSeatId());
			appDAO.setStrIsValid("1");
			appDAO.setStrHospitalCode(vo.getStrHospitalCode());

			// Execute in batch
			appDAO.insert(dao);

			// -----generate online request------

			keyDAO.setStrKeyname("REQNO");
			keyDAO.setStrBlockkey("1");
			keyDAO.select(dao);

			strReqNo = keyDAO.getStrPrimrayKeyValue();
			if (strReqNo != "" || strReqNo != null) {
				reqFlag = 1;
			}

			// -----to insert the details into inbound detail-----

			inboundDAO.setStrReqNo(strReqNo);
			inboundDAO.setStrDeptCode(vo.getStrDepartmentCode());
			inboundDAO.setStrChargeTypeId(vo.getStrChargeTypeId());
			inboundDAO.setStrBServiceId(vo.getStrBserviceId());
			inboundDAO.setStrBillNo(vo.getStrBillNo());
			inboundDAO.setStrCatCode(vo.getStrCatCode());
			inboundDAO.setStrEpisodeCode(vo.getStrEpisodeCode());
			inboundDAO.setStrPuk(vo.getStrCrNo());
			inboundDAO.setStrAdmNo(vo.getStrAdmissionNo());
			inboundDAO.setStrPatAccNo(vo.getStrPatAccountNo());
			inboundDAO.setStrAppId(strAppId);
			inboundDAO.setStrStatus("1");
			inboundDAO.setStrSeatId(vo.getStrSeatId());
			inboundDAO.setStrIsValid("1");
			inboundDAO.setStrAppBy(vo.getStrReconcilationBy());
			inboundDAO.setStrReqType("3");
			inboundDAO.setStrIpdChargeTypeId(vo.getStrIpdChargeTypeId());
			inboundDAO.setStrWardCode(vo.getStrWardCode());
			inboundDAO.setStrReceiptNo(vo.getStrReceiptNo());
			inboundDAO.setStrHospitalCode(vo.getStrHospitalCode());
			inboundDAO.setStrGblReqNo(vo.getStrGblRequestNo());
			inboundDAO.setStrServiceId(vo.getStrServiceId());
			
			// Execute in batch
			inboundDAO.insert(dao);

			// -----to insert the details into requisition detail-----

			billreqDAO.setStrReqNo(strReqNo);
			billreqDAO.setStrDeptCode(vo.getStrDepartmentCode());
			billreqDAO.setStrChargeTypeId(vo.getStrChargeTypeId());
			billreqDAO.setStrBServiceId(vo.getStrBserviceId());
			billreqDAO.setStrCatCode(vo.getStrCatCode());
			billreqDAO.setStrEpisodeCode(vo.getStrEpisodeCode());
			billreqDAO.setStrPuk(vo.getStrCrNo());
			billreqDAO.setStrAdmNo(vo.getStrAdmissionNo());
			billreqDAO.setStrPatAccNo(vo.getStrPatAccountNo());
			billreqDAO.setStrAppId(strAppId);
			billreqDAO.setStrBillNo(vo.getStrBillNo());
			billreqDAO.setStrRemarks(vo.getStrReconcilationReasonText());
			billreqDAO.setStrStatus("1");
			billreqDAO.setStrSeatId(vo.getStrSeatId());
			billreqDAO.setStrIsValid("1");
			billreqDAO.setStrAppBy(vo.getStrReconcilationBy());
			billreqDAO.setStrReqType("3");
			billreqDAO.setStrIpdChargeTypeId(vo.getStrIpdChargeTypeId());
			billreqDAO.setStrWardCode(vo.getStrWardCode());
			billreqDAO.setStrReceiptNo(vo.getStrReceiptNo());
			billreqDAO.setStrHospitalCode(vo.getStrHospitalCode());
			billreqDAO.setStrServiceId(vo.getStrServiceId());
			billreqDAO.setStrGblReqNo(vo.getStrGblRequestNo());
			
			// Execute in batch
			billreqDAO.insert(dao);
*/
			// ---------LOOP UNTIL ALL NEW SERVICES ARE HANDLED----------

			int nLen = 0;

			if (vo.getStrOfflineTariffName() != null)
				nLen = vo.getStrOfflineTariffName().length;

			String[] strDiscountDtls = vo
					.getStrOfflineTariffDiscountConfigDetails();

			
			for (int j = 0; j < nLen ; j++) {

			//	System.out.println(" val :"+vo.getStrOfflineTariffDetailsHidden());
				
				strAppId_Dis = "0";
				
				String[] selMul = vo.getStrOfflineTariffDetailsHidden();
				String[] newTrfMulVal = selMul[j].replace("^", "#").split("#");
				
				String[] strDiscountDtlsValue = null;
				
				String strDiscountUnitVal = "0";
				String strDiscountTypeVal = "0";
				String strApprovalBy = "";
				String strDiscountReason = "";
				//String strApprovalDate = "";
				
				
				if(strDiscountDtls[j].length() > 1)
					 	strDiscountDtlsValue = strDiscountDtls[j].replace(",","#").split("#");	
			
			if (strDiscountDtlsValue != null && strDiscountDtlsValue.length > 2) {
				
				strDiscountUnitVal = strDiscountDtlsValue[0];
				strDiscountTypeVal = strDiscountDtlsValue[1];
				strApprovalBy = strDiscountDtlsValue[2];
				strDiscountReason = strDiscountDtlsValue[4];
			//	strApprovalDate = strDiscountDtlsValue[5];
			}

			if (vo.getStrOfflineTariffDiscount()[j] != null && vo.getStrOfflineTariffDiscount()[j].trim().length() > 1) {

				keyDAO.setStrKeyname("APPROVAL");
				keyDAO.setStrBlockkey("1");
				keyDAO.setStrHospCode(vo.getStrHospitalCode());
				keyDAO.select(dao);

				strAppId_Dis = keyDAO.getStrPrimrayKeyValue();
				
					
				}
						
			
			nInsertedIndex2 = dao
			.setProcedure("{call pkg_bill_dml.Dml_reconcile_service_approval(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

						
			dao.setProcInValue(nInsertedIndex2, "modval", "1");
			dao.setProcInValue(nInsertedIndex2, "reqNo", strReqNo);
			dao.setProcInValue(nInsertedIndex2, "hosp_code", vo.getStrHospitalCode());
		
			dao.setProcInValue(nInsertedIndex2, "grpId", newTrfMulVal[1]);
			dao.setProcInValue(nInsertedIndex2, "trfId", newTrfMulVal[0]);
			
			dao.setProcInValue(nInsertedIndex2, "chargeTypeId", vo.getStrChargeTypeId());
			dao.setProcInValue(nInsertedIndex2, "catCode", vo.getStrCatCode());
			dao.setProcInValue(nInsertedIndex2, "puk", vo.getStrCrNo());
			dao.setProcInValue(nInsertedIndex2, "bServiceId", vo.getStrBserviceId());
			
			dao.setProcInValue(nInsertedIndex2, "serviceId", newTrfMulVal[15]);
			
			dao.setProcInValue(nInsertedIndex2, "gblTrfId", newTrfMulVal[16]);
			
			dao.setProcInValue(nInsertedIndex2, "disAppId", strAppId_Dis);
			dao.setProcInValue(nInsertedIndex2, "disBy", strApprovalBy);
			dao.setProcInValue(nInsertedIndex2, "disReason", strDiscountReason);
			dao.setProcInValue(nInsertedIndex2, "disUnit", strDiscountUnitVal);
			dao.setProcInValue(nInsertedIndex2, "disType", strDiscountTypeVal);
			dao.setProcInValue(nInsertedIndex2, "rate", newTrfMulVal[3]);
			dao.setProcInValue(nInsertedIndex2, "reqQty", vo.getStrOfflineTariffQty()[j]);
			dao.setProcInValue(nInsertedIndex2, "isPackage", newTrfMulVal[7]);
			dao.setProcInValue(nInsertedIndex2, "tax", newTrfMulVal[13]);
			dao.setProcInValue(nInsertedIndex2, "qtyUnitId",vo.getStrOfflineTariffUnit()[j].replace("^", "#").split("#")[0]);
			dao.setProcInValue(nInsertedIndex2, "rateUnitId", newTrfMulVal[5]);
					
	        dao.setProcInValue(nInsertedIndex2, "patAccNo", "0");
	    	 dao.setProcInValue(nInsertedIndex2, "billNo", "0");
			 dao.setProcInValue(nInsertedIndex2, "trfRecNo", "0");
			 dao.setProcInValue(nInsertedIndex2, "accReqNo", "0");
		        
			dao.setProcInValue(nInsertedIndex2, "wardId", vo.getStrWardCode());
			dao.setProcInValue(nInsertedIndex2, "seatId", vo.getStrSeatId());
			
			// output value
			dao.setProcOutValue(nInsertedIndex2, "err", 1);
		
			// keep in batch
			dao.execute(nInsertedIndex2, 1);
			
			
			
			
			
				/*	if (!strAppId_Dis.equals("") || strAppId_Dis != null) {
						appFlag1 = 1;
					

					// ------put approval details----------

					appDAO.setStrApprovalId(strAppId_Dis);
					appDAO.setStrApprovalSlNo("1");
					appDAO.setStrReceiptType("3");
					appDAO.setStrBServiceId(vo.getStrBserviceId());
					appDAO.setStrChargeTypeId(vo.getStrChargeTypeId());
					appDAO.setStrEmpNo(strApprovalBy);
					appDAO.setStrUserLevel(vo.getStrUserLevel());
					appDAO.setStrDiscountUnit(strDiscountUnitVal); // Chek
					// appDAO.setStrDiscountUnit("1");
					appDAO.setStrApprovalDt(strApprovalDate);
					appDAO.setStrRemarks(strDiscountReason);
					// appDAO.setStrDiscountType("1");
					appDAO.setStrDiscountType(strDiscountTypeVal); // Check
					appDAO.setStrApprovalType("2");
					appDAO.setStrStatus("1");
					appDAO.setStrSeatId(vo.getStrSeatId());
					appDAO.setStrIsValid("1");
					appDAO.setStrHospitalCode(vo.getStrHospitalCode());

					// Execute in batch
					appDAO.insert(dao);
					
					}	
					
				}
				// ------to insert tariff details into requisition tariff
				// details----------

				billreqtrfDAO.setStrReqNo(strReqNo);
				billreqtrfDAO.setStrTariffId(newTrfMulVal[0]);
				billreqtrfDAO.setStrBServiceId(vo.getStrBserviceId());
				billreqtrfDAO.setStrGroupId(newTrfMulVal[1]);
				billreqtrfDAO.setStrRate(newTrfMulVal[4]);
				billreqtrfDAO.setStrQty(vo.getStrOfflineTariffUnit()[j].replace("^", "#").split("#")[0]);
				billreqtrfDAO.setStrReqQty(vo.getStrOfflineTariffQty()[j]);
				billreqtrfDAO.setStrBillQty("0");
				billreqtrfDAO.setStrTariffRate(newTrfMulVal[3]);
				billreqtrfDAO.setStrAppId(strAppId_Dis);
				billreqtrfDAO.setStrDiscountType(strDiscountTypeVal);
				billreqtrfDAO.setStrStatus("1");
				billreqtrfDAO.setStrIsValid("1");
				billreqtrfDAO.setStrDiscountUnit(strDiscountUnitVal);
				billreqtrfDAO.setStrPuk(vo.getStrCrNo());
				billreqtrfDAO.setStrIsPackage(newTrfMulVal[7]);
				billreqtrfDAO.setStrServiceTax(newTrfMulVal[13]);
				billreqtrfDAO.setStrTariffReceiptNo("0");
				billreqtrfDAO.setStrHospitalCode(vo.getStrHospitalCode());
				billreqtrfDAO.setStrGblTariffId(newTrfMulVal[16]);
				billreqtrfDAO.setStrServiceId(newTrfMulVal[15]);

				// Execute in batch
				billreqtrfDAO.insert(dao);

				// ------to insert package details into bill requisition package
				// details----------

				if (newTrfMulVal[7].equals("1")) {

					vo.setStrTariffTempId(newTrfMulVal[0]);

					hbltPackageChgMstForBillService(vo, j);
					int nCount = 0;
					if(vo.getStrBillWs() != null)
					while (vo.getStrBillWs().next()) {

						strHiddenVal = vo.getStrBillWs().getString(6).replace(
								"^", "#").split("#");
						int billQty = Integer.parseInt(vo
								.getStrOfflineTariffQty()[j]);
						int HidtariifQty = Integer.parseInt(strHiddenVal[8]);
						String fbillQty = Integer.toString(billQty
								* HidtariifQty);

						billreqpackageDAO.setStrRequestNo(strReqNo);
						billreqpackageDAO.setStrPackageId(vo.getStrTariffId()[j]);
						billreqpackageDAO.setStrGroupId(strHiddenVal[2]);
						billreqpackageDAO.setStrPuk(vo.getStrCrNo());
						billreqpackageDAO.setStrRateUnitId(strHiddenVal[1]);
						billreqpackageDAO.setStrTariffRate(strHiddenVal[5]);
						billreqpackageDAO.setStrRequestQty(fbillQty);
						billreqpackageDAO.setStrBillQty("0");
						billreqpackageDAO.setStrStatus("1");
						billreqpackageDAO.setStrSeatId(vo.getStrSeatId());
						billreqpackageDAO.setStrIsValid("1");
						billreqpackageDAO.setStrQtyUnitId(strHiddenVal[3]);
						billreqpackageDAO.setStrTariffId(strHiddenVal[0]);
						billreqpackageDAO.setStrPackageQty(strHiddenVal[8]);
						billreqpackageDAO.setStrHospitalCode(vo
								.getStrHospitalCode());

						// Added into Batch
						billreqpackageDAO.insert(dao);

						nCount = nCount + 1;

					} */
				
			}
			// ---------LOOP UNTIL ALL RECONCILE SERVICES ARE HANDLED----------

			// ------------to insert tariffs details into requisition tariff
			// details-----------

			
		//	System.out.println("Tariff Len : "+vo.getStrTariffId().length);
			
			if(vo.getStrTariffId() != null)
			for (int i = 0; i < vo.getStrTariffId().length; i++) {
	
				
				
				

				nInsertedIndex3 = dao
				.setProcedure("{call pkg_bill_dml.Dml_reconcile_service_approval(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

							
				dao.setProcInValue(nInsertedIndex3, "modval", "2");
				dao.setProcInValue(nInsertedIndex3, "reqNo", strReqNo);
				dao.setProcInValue(nInsertedIndex3, "hosp_code", vo.getStrHospitalCode());
				dao.setProcInValue(nInsertedIndex3, "grpId", vo.getStrGroupId()[i]);
				dao.setProcInValue(nInsertedIndex3, "trfId", vo.getStrTariffId()[i]);
				dao.setProcInValue(nInsertedIndex3, "chargeTypeId", vo.getStrChargeTypeId());
				dao.setProcInValue(nInsertedIndex3, "catCode", vo.getStrCatCode());
				dao.setProcInValue(nInsertedIndex3, "puk", vo.getStrCrNo());
				dao.setProcInValue(nInsertedIndex3, "bServiceId", vo.getStrBserviceId());
				dao.setProcInValue(nInsertedIndex3, "serviceId", vo.getStrSServiceId()[i]);
				dao.setProcInValue(nInsertedIndex3, "gblTrfId", vo.getStrGTariffId()[i]);
				dao.setProcInValue(nInsertedIndex3, "disAppId", vo.getStrDiscountApprovalId()[i]);
				dao.setProcInValue(nInsertedIndex3, "disUnit", vo.getStrDiscountUnit()[i]);
				dao.setProcInValue(nInsertedIndex3, "disType", vo.getStrDiscountType()[i]);
				dao.setProcInValue(nInsertedIndex3, "rate", vo.getStrTariffRate()[i]);
				dao.setProcInValue(nInsertedIndex3, "reqQty", vo.getStrBillTariffRefund()[i]);
				dao.setProcInValue(nInsertedIndex3, "isPackage", vo.getStrIsPackage()[i]);
				dao.setProcInValue(nInsertedIndex3, "tax", vo.getStrServiceTax()[i]);
				dao.setProcInValue(nInsertedIndex3, "qtyUnitId",vo.getStrQtyUnitId()[i].replace("^", "#").split("#")[0]);
				dao.setProcInValue(nInsertedIndex3, "rateUnitId", vo.getStrRateUnit()[i]);
				dao.setProcInValue(nInsertedIndex3, "patAccNo", vo.getStrPatAccountNo());
				dao.setProcInValue(nInsertedIndex3, "billNo", vo.getStrBillNo());
				dao.setProcInValue(nInsertedIndex3, "trfRecNo", vo.getStrTariffReceiptNo()[i]);
				dao.setProcInValue(nInsertedIndex3, "wardId", vo.getStrWardCode());
				dao.setProcInValue(nInsertedIndex3, "seatId", vo.getStrSeatId());
				dao.setProcInValue(nInsertedIndex3, "accReqNo", vo.getStrAccReqNo()[i]);
				
				// output value
				dao.setProcOutValue(nInsertedIndex3, "err", 1);
			
				// keep in batch
				dao.execute(nInsertedIndex3, 1);
				
				
				
				
				
				
		/*		billreqtrfDAO.setStrReqNo(strReqNo);
				billreqtrfDAO.setStrTariffId(vo.getStrTariffId()[i]);
				billreqtrfDAO.setStrBServiceId(vo.getStrBserviceId());
				billreqtrfDAO.setStrGroupId(vo.getStrGroupId()[i]);
				billreqtrfDAO.setStrRate(vo.getStrRateUnit()[i]);
				billreqtrfDAO.setStrQty(vo.getStrQtyUnitId()[i].replace("^", "#").split("#")[0]);
				billreqtrfDAO.setStrReqQty(vo.getStrBillTariffRefund()[i]);
				billreqtrfDAO.setStrBillQty("0");
				billreqtrfDAO.setStrTariffRate(vo.getStrTariffRate()[i]);
				billreqtrfDAO.setStrAppId(strAppId_Dis);
				billreqtrfDAO.setStrDiscountType(vo.getStrDiscountType()[i]);
				billreqtrfDAO.setStrStatus("1");
				billreqtrfDAO.setStrIsValid("1");
				billreqtrfDAO.setStrDiscountUnit(vo.getStrDiscountUnit()[i]);
				billreqtrfDAO.setStrPuk(vo.getStrCrNo());
				if (vo.getStrIsPackage()[i].equals("1")) {
					billreqtrfDAO.setStrIsPackage("1");
				}
				billreqtrfDAO.setStrServiceTax(vo.getStrServiceTax()[i]);
				billreqtrfDAO.setStrTariffReceiptNo(vo.getStrTariffReceiptNo()[i]);
				billreqtrfDAO.setStrPenelty(vo.getStrPenalty()[i]);
				billreqtrfDAO.setStrQtyType("0");
				billreqtrfDAO.setStrHospitalCode(vo.getStrHospitalCode());
				billreqtrfDAO.setStrGblTariffId(vo.getStrGTariffId()[i]);
				billreqtrfDAO.setStrTariffReceiptNo(vo.getStrReceiptNo());
				billreqtrfDAO.setStrServiceId(vo.getStrSServiceId()[i]);
				
				// Added into Batch
				billreqtrfDAO.insert(dao);

				// ------------update the remained quantity in bill service or
				// account service-----------

			
				// System.out.println("strPatAccNo : "+strPatAccNo);

				if (!vo.getStrPatAccountNo().equals("0")) {

					pataccserDAO.setStrRemainedQty(vo.getStrBalanceQty()[i]);
					pataccserDAO.setStrPatAccountNo(vo.getStrPatAccountNo());
					pataccserDAO.setStrReceiptNo(vo.getStrReceiptNo());
					pataccserDAO.setStrTariffId(vo.getStrTariffId()[i]);
					pataccserDAO.setStrReqNo(strReqNo);
					pataccserDAO.setStrHospitalCode(vo.getStrHospitalCode());

					// Added into Batch
					pataccserDAO.update(dao);
				} else {
					billserviceDAO.setStrRemainedQty(vo.getStrBalanceQty()[i]);
					billserviceDAO.setStrReceiptNo(vo.getStrReceiptNo());
					billserviceDAO.setStrTariffId(vo.getStrTariffId()[i]);
					billserviceDAO.setStrBillNo(vo.getStrBillNo());
					billserviceDAO.setStrHospitalCode(vo.getStrHospitalCode());

					// Added into Batch
					billserviceDAO.update(dao);
				}

				if (vo.getStrIsPackage()[i].equals("1")) {

					vo.setStrTariffTempId(vo.getStrTariffId()[i]);
					vo.setStrHospitalServices(vo.getStrChargeTypeId());
					vo.setStrWardCode(vo.getStrWardCode());
					vo.setStrCategoryCode(vo.getStrCatCode());

					hbltPackageChgMstForBillReconcileService(vo);

					int nCount1 = 0;

					if(vo.getStrBillRecWs() != null)
					while (vo.getStrBillRecWs().next()) {

						int billQty = Integer.parseInt(vo
								.getStrBillTariffRefund()[i]);

						strHiddenVal = vo.getStrBillRecWs().getString(6)
								.replace("^", "#").split("#");

						int HidtariifQty = Integer.parseInt(strHiddenVal[8]);
						String fbillQty = Integer.toString(billQty
								* HidtariifQty);

						billreqpackageDAO.setStrRequestNo(strReqNo);
						billreqpackageDAO
								.setStrPackageId(vo.getStrTariffId()[i]);
						billreqpackageDAO.setStrGroupId(strHiddenVal[2]);
						billreqpackageDAO.setStrPuk(vo.getStrCrNo());
						billreqpackageDAO.setStrRateUnitId(strHiddenVal[1]);
						billreqpackageDAO.setStrTariffRate(strHiddenVal[5]);
						billreqpackageDAO.setStrRequestQty(fbillQty);
						billreqpackageDAO.setStrBillQty("0");
						billreqpackageDAO.setStrStatus("1");
						billreqpackageDAO.setStrSeatId(vo.getStrSeatId());
						billreqpackageDAO.setStrIsValid("1");
						billreqpackageDAO.setStrQtyUnitId(strHiddenVal[3]);
						billreqpackageDAO.setStrTariffId(strHiddenVal[0]);
						billreqpackageDAO.setStrPackageQty(strHiddenVal[8]);
						billreqpackageDAO.setStrHospitalCode(vo
								.getStrHospitalCode());

						// Added into Batch
						billreqpackageDAO.insert(dao);

						nCount1 = nCount1 + 1;

						// -------update the remained quantity in Pat Account
						// Package or Bill Package Detail------

						if (!vo.getStrPatAccountNo().equals("")
								|| !vo.getStrPatAccountNo().equals("0")) {

							pataccpackDAO.setStrRemainedQty(fbillQty);
							pataccpackDAO.setStrPatAccountNo(vo
									.getStrPatAccountNo());
							pataccpackDAO.setStrReceiptNo(vo.getStrReceiptNo());
							pataccpackDAO
									.setStrTariffId(strHiddenVal[0]);
							pataccpackDAO.setStrReqNo(vo.getStrRequestNo());
							pataccpackDAO
									.setStrPackageId(vo.getStrTariffId()[i]);
							pataccpackDAO.setStrHospitalCode(vo
									.getStrHospitalCode());

							// Added into Batch
							pataccpackDAO.update(dao);

						} else {

							billpackageDAO.setStrRemainedQty(vo.getStrBalanceQty()[i]);
							pataccpackDAO.setStrReceiptNo(vo.getStrReceiptNo());
							pataccpackDAO
									.setStrTariffId(strHiddenVal[0]);
							pataccpackDAO.setStrReqNo(vo.getStrRequestNo());
							pataccpackDAO
									.setStrPackageId(vo.getStrTariffId()[i]);
							pataccpackDAO.setStrHospitalCode(vo
									.getStrHospitalCode());

							// Added into Batch
							billpackageDAO.update(dao);
						}
					}
				}
				*/
			}

			synchronized (dao) {
				dao.fire(); // Here we Execute in Batch
			
			}

			vo.setStrMsgString("Data Saved Successfully");
			vo.setStrMsgType("0");
			// System.out.println("DAO Success: getOffLineTariffUnitList");

		} catch (Exception e) {

			e.printStackTrace();
			
			String err = "err:"+e.getMessage();
			
			vo.setStrErrPrimaryKeyLog(err);
			// System.out.println("Err is -->"+e.getMessage());
			vo.setStrMsgString("BillReconcileTransDAO.insertDataProc()--> "
					+ err);
			vo.setStrMsgType("1");
			

			//
			// Here We Call The PrimaryKeyLogProc Procedure To RollBack The
			// Primary Key Table
			//

			try {
				if (appFlag == 1) {

					keyLogDAO.setStrKeyname("APPROVAL");
					keyLogDAO.setStrStartkey(strAppId);
					keyLogDAO.setStrBlockkey("1");
					keyLogDAO.setStrHospCode(vo.getStrHospitalCode());
					keyLogDAO.setStrError(vo.getStrErrPrimaryKeyLog());
					keyLogDAO.setStrSeatid(vo.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					keyLogDAO.insert(dao);
				}
			} catch (Exception e1) {

				vo.setStrMsgString("BillReconcileTransDAO.insertDataProc()--> appFlag == 1 --> "
						+ e1.getMessage()+"-->"+err);
				vo.setStrMsgType("1");

				
			}

			try {
				if (appFlag1 == 1) {

					keyLogDAO.setStrKeyname("APPROVAL");
					keyLogDAO.setStrStartkey(strAppId_Dis);
					keyLogDAO.setStrBlockkey("1");
					keyLogDAO.setStrError(vo.getStrErrPrimaryKeyLog());
					keyLogDAO.setStrHospCode(vo.getStrHospitalCode());
					keyLogDAO.setStrSeatid(vo.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					keyLogDAO.insert(dao);
				}
			} catch (Exception e2) {

				vo.setStrMsgString("BillReconcileTransDAO.insertDataProc()--> appFlag1 == 1 --> "
						+ e2.getMessage()+"-->"+err);
				vo.setStrMsgType("1");

				
			}

			try {
				if (reqFlag == 1) {
					
					keyLogDAO.setStrKeyname("REQNO");
					keyLogDAO.setStrStartkey(strReqNo);
					keyLogDAO.setStrBlockkey("1");
					keyLogDAO.setStrError(vo.getStrErrPrimaryKeyLog());
					keyLogDAO.setStrHospCode(vo.getStrHospitalCode());
					keyLogDAO.setStrSeatid(vo.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					keyLogDAO.insert(dao);
				}
			} catch (Exception e3) {

				vo.setStrMsgString("BillReconcileTransDAO.insertDataProc()--> reqFlag == 1 -->"
						+ e3.getMessage()+"-->"+err);
				vo.setStrMsgType("1");

				
			}
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}

			keyDAO = null;
			keyLogDAO = null;
			/*appDAO = null;
			inboundDAO = null;
			billreqDAO = null;
			billreqtrfDAO = null;
			billreqpackageDAO = null;
			pataccserDAO = null;
			billserviceDAO = null;
			pataccpackDAO = null;
			billpackageDAO = null;*/
		}
		
	}

	/**
	 * retrieves Off Line Tariff List
	 * 
	 * @param voObj -
	 *            ValueObject
	 */
	public static void getOffLineTariffListByCode(BillReconcileTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strGroupId = voObj.getStrGroup();
		String strChargeTypeId = voObj.getStrHospitalServices();
		String strCategoryCode = voObj.getStrCategoryCode();
		String strWardCode = voObj.getStrWardCode();
		String strPackage = voObj.getStrOffLinePackageIndex();
		String strTariffCode = voObj.getStrTariffCode();
		
		if (strChargeTypeId == null )
			strChargeTypeId = "";
		if (strCategoryCode == null )
			strCategoryCode = "";
		
		if (strGroupId == null )
			strGroupId = "";
		if (strWardCode == null)
			strWardCode = "";

		 

		String strErr = "";
		String mode = "3";
		
		 strPackage = "2";

			
		strProcName = "{call PKG_BILL_VIEW.PROC_HBLT_CHARGE_MST(?,?,?,?,?,?,?,?,?,?,?,?)}";
		
		try {
			daoObj = new HisDAO("Add Services", "AddServicesIPDTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
 
				daoObj.setProcInValue(nProcIndex, "chargeTypeId", strChargeTypeId);
				daoObj.setProcInValue(nProcIndex, "catCode", strCategoryCode);
				daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode);
				daoObj.setProcInValue(nProcIndex, "pkg_flag", strPackage);
				daoObj.setProcInValue(nProcIndex, "wardCode", strWardCode);
				daoObj.setProcInValue(nProcIndex, "groupId", strGroupId);
				daoObj.setProcInValue(nProcIndex, "searchtrfname", strTariffCode);
				daoObj.setProcInValue(nProcIndex, "searchMode", "1");
				daoObj.setProcInValue(nProcIndex, "advance_flag", "1");
				daoObj.setProcInValue(nProcIndex, "modeVal", mode);
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);

				daoObj.executeProcedure(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				/*
				 * System.out.println("charge Type Id "+strChargeTypeId);
				 * System.out.println("categoryCode "+strCategoryCode);
				 * System.out.println("group code "+strGroupId);
				 * System.out.println("ward code "+strWardCode);
				 * System.out.println("hospitalCode "+strHospitalCode);
				 * System.out.println("pkgFlag "+strPackage);
				 */

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (ws != null) {

					// System.out.println("ws size : "+ws.size());
					voObj.setTariffList(ws);
				}

			
		} catch (Exception e) {

			voObj
					.setStrMsgString("BillReconcileTransDAO.getOffLineTariffListByCode() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
}
