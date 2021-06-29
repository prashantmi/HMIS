package billing.transactions;


import ipd.IpdConfigUtil;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.dao.ApprovalDAO;
import billing.dao.PatientAccountDAO;
import billing.dao.PatientAccountServiceDAO;
import billing.dao.PatientAccountStatusDAO;
import billing.dao.PrimaryKeyDAO;
import billing.dao.PrimaryKeyLogDAO;
import hisglobal.utility.GetNetworkAddress;

public class IpdBillManagementTransDAO {
	/**
	 * This class will be used to inetract with the database server. Only this
	 * class can access HisDAO class. Values can be set into Value Object. This
	 * class can be called only from BO class.
	 */
	WebRowSet ws = null;

	/**
	 * DepUnitId(vo) -- > This Method is Used to get DepUnitId
	 */
	public static String DepUnitId(IpdBillManagementTransVO voObj) {

		String DeptUnitId = "";
		// String proc_name2 = "";
		HisDAO dao = null;
		// int nprocIndex2 = 0;
		// String strerr = "";
		// WebRowSet ws2 = null;

		String s1[] = voObj.getStrComboValue();
		int k = Integer.parseInt(s1[0]); // Account Status

		voObj.setStrDeptId(s1[1]);

		try {
			if (k == 1) {
				voObj.setStrPresentAcctStatus("InActive");
			}
			if (k == 2) {
				voObj.setStrPresentAcctStatus("Active");
			}
			if (k == 3) {
				voObj.setStrPresentAcctStatus("On-Hold");
			}
			if (k == 4) {
				voObj.setStrPresentAcctStatus("Dormant");
			}
			if (k == 5) {
				voObj.setStrPresentAcctStatus("Request for Close");
			}

		} catch (Exception e) {
			voObj.setStrMsgString("IpdBillManagementTransDAO.DepUnitId() -->"
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return DeptUnitId;
	}

	/**
	 * APPROVEDBY(vo) -- > This Method is Used to get WebRowSet for Approved By
	 * Combo
	 */

	public static WebRowSet APPROVEDBY(IpdBillManagementTransVO VO) {

		String proc_name = "";

		proc_name = "{call pkg_simple_view.Proc_user_list(?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("IpdBillManagementTrans","transactions.IpdBillManagementTransDAO.APPROVEDBY()");
			nprocIndex = dao.setProcedure(proc_name);
			// set value
			dao.setProcInValue(nprocIndex, "processId", "3", 1);
			dao.setProcInValue(nprocIndex, "hosp_code",	VO.getStrHospitalCode(), 2);
			dao.setProcInValue(nprocIndex, "seatId", VO.getStrSeatId(), 3);
			
			dao.setProcOutValue(nprocIndex, "ERR", 1, 4); // 1 for string
														// return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 5); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "ERR");
			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		}

		catch (Exception e) {

			// e.printStackTrace();

			VO.setStrMsgString("IpdBillManagementTransDAO.APPROVEDBY() --> "
					+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

		return ws;
	}

	public static WebRowSet ACCOUNTSTATUS(IpdBillManagementTransVO VO) {

		String proc_name = "";

		proc_name = "{call pkg_simple_view.proc_accstatus_list(?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.ACCOUNTSTATUS()");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "hosp_code",
					VO.getStrHospitalCode(), 1);
			dao.setProcOutValue(nprocIndex, "ERR", 1, 2); // 1 for string
															// return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 3); // 2 for object
			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		}

		catch (Exception e) {

			// e.printStackTrace();

			VO.setStrMsgString("IpdBillManagementTransDAO.ACCOUNTSTATUS() --> "
					+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

		return ws;
	}

	/**
	 * REMARKSCOMBO(vo) -- > This Method is Used to get WebRowSet for Remarks
	 * Combo from Table HBLT_REMARKS_MST
	 */
	public static WebRowSet REMARKSCOMBO(IpdBillManagementTransVO VO) {

		String proc_name = "";

		proc_name = "{call PKG_SIMPLE_VIEW.PROC_HBLT_REMARKS_MST(?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.REMARKSCOMBO()");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "rmksType", "4", 1);

			dao.setProcInValue(nprocIndex, "modeVal", "1", 2);

			dao.setProcInValue(nprocIndex, "hosp_code",
					VO.getStrHospitalCode(), 3);

			dao.setProcOutValue(nprocIndex, "ERR", 1, 4); // 1 for string
															// return
			// value

			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 5); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		}

		catch (Exception e) {

			// e.printStackTrace();

			VO.setStrMsgString("IpdBillManagementTransDAO.REMARKSCOMBO() --> "
					+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

		return ws;
	}

	/**
	 * GROUPNAMECOMBO(vo) -- > This Method is Used to get WebRowSet for Group
	 * Name Combo from Table HBLT_HSERVICE_GROUP_MST
	 */
	public static WebRowSet GROUPNAMECOMBO(IpdBillManagementTransVO VO) {

		String proc_name = "";

		proc_name = "{call PKG_BILL_VIEW.proc_HBLT_HSERVICE_GROUP_MST(?,?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.GROUPNAMECOMBO()");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "hosp_code",
					VO.getStrHospitalCode(), 1);

			dao.setProcInValue(nprocIndex, "charge_id",
					VO.getStrChargeTypeID(), 2);

			dao.setProcInValue(nprocIndex, "pkg_wise_group", "0", 3);

			dao.setProcInValue(nprocIndex, "modeVal", "1", 4);

			dao.setProcOutValue(nprocIndex, "err", 1, 5); // 1 for string
															// return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2, 6); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		}

		catch (Exception e) {

			// e.printStackTrace();

			VO
					.setStrMsgString("IpdBillManagementTransDAO.GROUPNAMECOMBO() --> "
							+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

		return ws;
	}

	/**
	 * TARIFFCOMBO(vo) -- > This Method is Used to get WebRowSet for Tariff
	 * Combo from Table HBLT_TARIFF_MST
	 */
	public static WebRowSet TARIFFCOMBO(IpdBillManagementTransVO VO) {
		// String str = VO.getStrGroupId();

		String proc_name = "";

		proc_name = "{call PKG_BILL_VIEW.proc_HBLT_TARIFF_MST(?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.TARIFFCOMBO(VO)");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "hosp_code",
					VO.getStrHospitalCode(), 1);
			dao.setProcInValue(nprocIndex, "group_id", VO.getStrGroupId(), 2);
			dao.setProcInValue(nprocIndex, "modeval", "1", 3);
			dao.setProcOutValue(nprocIndex, "err", 1, 4); // 1 for string
															// return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2, 5); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		}

		catch (Exception e) {

			// e.printStackTrace();

			VO.setStrMsgString("IPDBillManagementTransDAO.TARIFFCOMBO() --> "
					+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

		return ws;
	}

	/**
	 * PARTPAYMENTCOMBO(vo) -- > This Method is Used to get WebRowSet for
	 * PartPayment Combo from Table HBLT_ADVANCE_MST
	 */
	public static WebRowSet PARTPAYMENTCOMBO(IpdBillManagementTransVO VO) {
		String proc_name = "";

		proc_name = "{call PKG_BILL_VIEW.proc_hblt_advance_mst(?,?,?,?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.PARTPAYMENTCOMBO(VO)");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "wardid", VO.getStrWardCode(), 1);

			dao.setProcInValue(nprocIndex, "catcode", VO.getStrPtCatCode(), 2);

			dao.setProcInValue(nprocIndex, "modeval", "1", 6);

			dao.setProcInValue(nprocIndex, "chargeId", "2", 3);
			dao.setProcInValue(nprocIndex, "splwardid", "", 5);

			dao.setProcInValue(nprocIndex, "hosp_code",
					VO.getStrHospitalCode(), 4);

			dao.setProcOutValue(nprocIndex, "ERR", 1, 7); // 1 for string
															// return
			// value

			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 8); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {

				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		}

		catch (Exception e) {

			// e.printStackTrace();

			VO
					.setStrMsgString("IPDBillManagementTransDAO.PARTPAYMENTCOMBO() --> "
							+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

		return ws;
	}

	/**
	 * TARIFFNAMECOMBO(vo) -- > This Method is Used to get WebRowSet for
	 * TariffName Combo from Table HBLT_TARIFF_MST
	 */
	public static WebRowSet TARIFFNAMECOMBO(IpdBillManagementTransVO VO) {

		// String str = VO.getStrGroupId();

		String proc_name = "";

		proc_name = "{call PKG_BILL_VIEW.proc_HBLT_TARIFF_MST(?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		String[] groupid = VO.getStrGroupId().replace("^", "#").split("#");

		String strIsPack = "0";

		if (groupid.length > 1 && groupid[1].equals("1")) {
			VO.setStrIsPackaged(groupid[1]);
			strIsPack = groupid[1];
		}
		try {

			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.TARIFFNAMECOMBO(VO)");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "hosp_code",
					VO.getStrHospitalCode(), 1);

			dao.setProcInValue(nprocIndex, "group_id", groupid[0], 2);

			dao.setProcInValue(nprocIndex, "modeval", "1", 3);

			dao.setProcOutValue(nprocIndex, "err", 1, 4); // 1 for string
															// return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2, 5); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				VO.setStrIsPackaged(strIsPack);
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}
		}

		catch (Exception e) {

			VO
					.setStrMsgString("IpdBillManagementTransDAO.TARIFFNAMECOMBO() --> "
							+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

		return ws;
	}

	/**
	 * HOSPITALSERVICECOMBO(vo) -- > This Method is Used to get WebRowSet for
	 * HospitalService Combo from Table SBLT_CHARGETYPE_MST
	 */
	public static WebRowSet HOSPITALSERVICECOMBO(IpdBillManagementTransVO VO) {
		String proc_name = "";

		proc_name = "{call PKG_BILL_VIEW.proc_SBLT_CHARGETYPE_MST(?,?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.HOSPITALSERVICECOMBO(VO)");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "hosp_code",
					VO.getStrHospitalCode(), 1);

			dao.setProcInValue(nprocIndex, "modeval", "1", 2);

			dao.setProcInValue(nprocIndex, "crNo", "", 4);
			dao.setProcInValue(nprocIndex, "seatId", "", 3);

			dao.setProcOutValue(nprocIndex, "ERR", 1, 5); // 1 for string
															// return
			// value

			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 6); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		}

		catch (Exception e) {

			// e.printStackTrace();

			VO
					.setStrMsgString("IpdBillManagementTransDAO.HOSPITALSERVICECOMBO()"
							+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

		return ws;
	}

	/**
	 * retrieves Treatment Category List
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void getTreatmentCategoryList(IpdBillManagementTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.PROC_GBLT_PATIENT_CAT_MST(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();

		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Bill Management",
					"IpdBillManagementTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);

			daoObj.setProcInValue(nProcIndex, "puk_no", "", 3);
			daoObj.setProcInValue(nProcIndex, "charge_type_id", "0", 4);
			daoObj.setProcInValue(nProcIndex, "effect_from", "", 5);
			daoObj.setProcInValue(nProcIndex, "effect_TO", "", 6);

			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospitalCode(), 2);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 7);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 8);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				if (ws.next()) {

					voObj.setStrOffLineTreatmentCategory(ws.getString(1));

					ws.beforeFirst();

				}
				voObj.setOfflineTreatmentCategoryList(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("IpdBillManagementTransDAO.getTreatmentCategoryList() --> "
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
	 * retrieves Ward List
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void getWardTypes(IpdBillManagementTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_ipd_chargetype_list(?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();

		String strErr = "";

		try {

			daoObj = new HisDAO("billing",
					"IpdBillManagementTransDAO.getWardList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "mode_val", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 2);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				if (ws.next()) {

					voObj.setStrOffLineWard(ws.getString(1));

					ws.beforeFirst();

				}

				voObj.setWardTypeList(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("IpdBillManagementTransDAO.getWardList() --> "
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
	 * retrieves Raising Department List
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void getDepartmentList(IpdBillManagementTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.proc_GBLT_DEPARTMENT_MST(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strCrNo = voObj.getStrCrNo();
		String strChargeTypeId = voObj.getStrChargeTypeID();

		String strErr = "";

		try {

			daoObj = new HisDAO("billing",
					"IpdBillManagementTransDAO.getDepartmentList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "4", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 2);
			daoObj.setProcInValue(nProcIndex, "puk_no", strCrNo, 4);
			
			daoObj.setProcInValue(nProcIndex, "charge_type_id",	strChargeTypeId, 5);
		
			daoObj.setProcInValue(nProcIndex, "deptcode", "", 3);
			
			daoObj.setProcInValue(nProcIndex, "effect_from", "", 6);
			daoObj.setProcInValue(nProcIndex, "effect_to", "", 7);
			daoObj.setProcInValue(nProcIndex, "userId", "", 8);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 9);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 10);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				voObj.setStrDepartmentList(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("IpdBillManagementTransDAO.getDepartmentList() --> "
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
	 * retrieves Previous Service Date List
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void getPreviousDatesList(IpdBillManagementTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_service_date_list(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strAccNo = voObj.getStrAccountNo();

		String strErr = "";

		try {

			daoObj = new HisDAO("billing",
					"IpdBillManagementTransDAO.getPreviousDatesList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 3);
			daoObj.setProcInValue(nProcIndex, "accNo", strAccNo, 2);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				voObj.setStrPreviousDatesList(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("IpdBillManagementTransDAO.getPreviousDatesList() --> "
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
	 * retrieves Previous Details
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void getPreviousDetails(IpdBillManagementTransVO voObj) 
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_acc_service_dtl(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strAccNo = voObj.getStrAccountNo();
		String strDateRang = voObj.getStrPreviousDates();

		String strErr = "";

		try 
		{
			daoObj = new HisDAO("billing","IpdBillManagementTransDAO.getPreviousDetails");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 3);
			daoObj.setProcInValue(nProcIndex, "accNo", strAccNo, 2);
			daoObj.setProcInValue(nProcIndex, "dtRange", strDateRang, 4);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setStrPreviousDtls(ws);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("IpdBillManagementTransDAO.getPreviousDetails() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * delete Previous Details
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void deletPreviousDetails(IpdBillManagementTransVO voObj) {

		HisDAO daoObj = null;

		String strProcName = "{call pkg_bill_dml.dml_delete_account_service_dtl(?,?,?,?,?,?,?,?,?)}";
		String strProcName1 = "{call pkg_bill_dml.dml_modify_account_service_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		int nProcIndex1 = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strSeatId = voObj.getStrSeatId();
		String[] previousDtl = voObj.getStrPreviousCheck();

		String strErr = "";

		try {

			daoObj = new HisDAO("billing","IpdBillManagementTransDAO.deletPreviousDetails");

			for (int i = 0; i < voObj.getStrPreviousCheck().length; i++) {

				String[] strDtls = previousDtl[i].replace("^", "#").split("#");

				nProcIndex = daoObj.setProcedure(strProcName);

				daoObj.setProcInValue(nProcIndex, "modval", "1", 1);
				daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode,2);
				daoObj.setProcInValue(nProcIndex, "seatId", strSeatId, 8);
				daoObj.setProcInValue(nProcIndex, "accNo", strDtls[0], 3);
				daoObj.setProcInValue(nProcIndex, "reqNo", strDtls[1], 4);
				daoObj.setProcInValue(nProcIndex, "trfId", strDtls[2], 5);
				daoObj.setProcInValue(nProcIndex, "recNo", strDtls[3], 6);
				daoObj.setProcInValue(nProcIndex, "slNo", strDtls[4], 7);
				daoObj.setProcOutValue(nProcIndex, "err", 1, 9);

				daoObj.execute(nProcIndex, 1);
				
				
				nProcIndex1 = daoObj.setProcedure(strProcName1);

				daoObj.setProcInValue(nProcIndex1, "modval", "1", 1);
				daoObj.setProcInValue(nProcIndex1, "hosp_code", strHospitalCode,2);
				daoObj.setProcInValue(nProcIndex1, "seatId", strSeatId, 8);
				daoObj.setProcInValue(nProcIndex1, "accNo", strDtls[0], 3);
				daoObj.setProcInValue(nProcIndex1, "reqNo", strDtls[1], 4);
				daoObj.setProcInValue(nProcIndex1, "trfId", strDtls[2], 5);
				daoObj.setProcInValue(nProcIndex1, "recNo", strDtls[3], 6);
				daoObj.setProcInValue(nProcIndex1, "slNo", strDtls[4], 7);
				daoObj.setProcInValue(nProcIndex1, "hbldt_req_date", strDtls[5], 9);
				daoObj.setProcInValue(nProcIndex1, "rate", strDtls[6], 10);
				daoObj.setProcInValue(nProcIndex1, "amt", String.valueOf(Double.parseDouble(strDtls[6])* Double.parseDouble(strDtls[7])) , 11);
				daoObj.setProcInValue(nProcIndex1, "qty",strDtls[7] , 12);
				daoObj.setProcOutValue(nProcIndex1, "err", 1, 13);

				daoObj.execute(nProcIndex1, 1);

			}

			synchronized (daoObj) {
				daoObj.fire();

			}

			if (strErr == null)
				strErr = "";

			/*if (strErr.equals("")) {
				throw new Exception(strErr);
			}*/

		} catch (Exception e) {
			
			voObj.setStrMsgString("IpdBillManagementTransDAO.deletPreviousDetails() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/*
	 *//**
		 * This function is used to set initial details for ward type combo that
		 * would be displayed on ward type combo.
		 * 
		 * @param voObj
		 */
	/*
	 * public static void getWardTypes(IpdBillManagementTransVO voObj) {
	 * 
	 * HisDAO daoObj = null; WebRowSet ws = null;
	 * 
	 * String strProcName = "{call
	 * pkg_bill_view.proc_ward_list(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"; int
	 * nProcIndex = 0; String strErr = ""; // WebRowSet web=null;
	 * 
	 * try { daoObj = new HisDAO("IPD Bill Management",
	 * "IpdBillManagementTransDAO");
	 * 
	 * nProcIndex = daoObj.setProcedure(strProcName);
	 * 
	 * daoObj.setProcInValue(nProcIndex, "modeval", "10");
	 * 
	 * daoObj.setProcInValue(nProcIndex, "wardtypcode", "0");
	 * daoObj.setProcInValue(nProcIndex, "deptcode", "0");
	 * daoObj.setProcInValue(nProcIndex, "deptunitcode", "0");
	 * daoObj.setProcInValue(nProcIndex, "unitcode", "0");
	 * daoObj.setProcInValue(nProcIndex, "age", "0");
	 * daoObj.setProcInValue(nProcIndex, "gender", "0");
	 * daoObj.setProcInValue(nProcIndex, "treatment_cat", "0");
	 * daoObj.setProcInValue(nProcIndex, "effect_from", "");
	 * daoObj.setProcInValue(nProcIndex, "effect_to", "");
	 * daoObj.setProcInValue(nProcIndex, "diseasetypcode", "0");
	 * daoObj.setProcInValue(nProcIndex, "wardcode", "0");
	 * 
	 * daoObj.setProcInValue(nProcIndex, "hosp_code",
	 * voObj.getStrHospitalCode()); daoObj.setProcInValue(nProcIndex, "puk_no",
	 * voObj.getStrCrNo()); daoObj .setProcInValue(nProcIndex, "charge_type_id",
	 * voObj.getStrChargeTypeID()); daoObj.setProcOutValue(nProcIndex, "err",
	 * 1); daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);
	 * 
	 * daoObj.executeProcedure(nProcIndex); strErr =
	 * daoObj.getString(nProcIndex, "err"); if (strErr == null) strErr = ""; ws =
	 * daoObj.getWebRowSet(nProcIndex, "RESULTSET");
	 * 
	 * if (strErr.equals("")) {
	 * 
	 * voObj.setWardTypeList(ws);
	 *  } else { throw new Exception(strErr); }
	 *  } catch (Exception e) {
	 * 
	 * voObj.setStrMsgString("IpdBillManagementTransDAO.getWardTypes() --> " +
	 * e.getMessage()); voObj.setStrMsgType("1"); } finally { if (daoObj !=
	 * null) { daoObj.free(); daoObj = null; } }
	 *  }
	 */

	public static void getChargeTypeName(IpdBillManagementTransVO voObj) {

		String strWardType = "";
		String strMode = "2";

		String strFunc = "";
		strFunc = "{? = call BILL_MST.get_online_ipdChargeTypeId(?,?,?,?,?)}";
		HisDAO dao = null;
		int nfuncIndex = 0;

		try {
			dao = new HisDAO("billing",
					"transactions.IpdBillManagementTransDAO .getChargeTypeName()");

			nfuncIndex = dao.setFunction(strFunc);

			dao.setFuncInValue(nfuncIndex, 2, strMode);
			dao.setFuncInValue(nfuncIndex, 3, voObj.getStrWardCode());
			dao.setFuncInValue(nfuncIndex, 4, "0");
			dao.setFuncInValue(nfuncIndex, 5, voObj.getStrCrNo());
			dao.setFuncInValue(nfuncIndex, 6, voObj.getStrHospitalCode());
			dao.setFuncOutValue(nfuncIndex, 3);

			dao.executeFuncForNumeric(nfuncIndex);

			strWardType = dao.getFuncNumeric(nfuncIndex);

			voObj.setStrWardType(strWardType);

		} catch (Exception e) {
			voObj
					.setStrMsgString("IpdBillManagementTransDAO.getChargeTypeName() --> "
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
	 * retrieves Tariff List
	 * 
	 * @param voObj -
	 *            ValueObject
	 */
	public static void getTariffListByCode(IpdBillManagementTransVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;
		String mode;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strGroupId = voObj.getStrGroupId();
		String strChargeTypeId = voObj.getStrChargeTypeID();
		String strCategoryCode = voObj.getStrNewTreatmentCategory();
		String strWardCode = voObj.getStrOffLineWard();

		String strTariffCode = voObj.getStrTariffCode();

		if (strChargeTypeId == null)
			strChargeTypeId = "";
		if (strCategoryCode == null)
			strCategoryCode = "";

		if (strGroupId == null)
			strGroupId = "";
		if (strWardCode == null)
			strWardCode = "";

		String strErr = "";
		if(strGroupId.equals("107"))
			mode="6";
		else
			mode = "3";

		String strPackage = "2";

		strProcName = "{call PKG_BILL_VIEW.PROC_HBLT_CHARGE_MST(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		try 
		{
			daoObj = new HisDAO("Billing", "AddServicesIPDTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "chargeTypeId", strChargeTypeId,1);
			daoObj.setProcInValue(nProcIndex, "catCode", strCategoryCode, 2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 7);
			daoObj.setProcInValue(nProcIndex, "pkg_flag", strPackage, 8);
			daoObj.setProcInValue(nProcIndex, "wardCode", strWardCode, 3);
			daoObj.setProcInValue(nProcIndex, "groupId", strGroupId, 4);
			daoObj.setProcInValue(nProcIndex, "searchtrfname", strTariffCode,5);
			daoObj.setProcInValue(nProcIndex, "searchMode", "1", 6);
			daoObj.setProcInValue(nProcIndex, "advance_flag", "1", 9);
			daoObj.setProcInValue(nProcIndex, "modeVal", mode, 10);
			daoObj.setProcInValue(nProcIndex, "end_date", "", 11);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 12);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 13);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (ws != null) 
			{
				voObj.setOfflineTariffList(ws);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("CashCollectionTransDAO.getTariffListByCode() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getTariffListByCodeD(IpdBillManagementTransVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strGroupId = voObj.getStrGroupId();
		String strChargeTypeId = voObj.getStrChargeTypeID();
		String strCategoryCode = voObj.getStrNewTreatmentCategory();
		String strWardCode = voObj.getStrOffLineWard();

		String strTariffCode = voObj.getStrTariffCode();

		if (strChargeTypeId == null)
			strChargeTypeId = "";
		if (strCategoryCode == null)
			strCategoryCode = "";

		if (strGroupId == null)
			strGroupId = "";
		if (strWardCode == null)
			strWardCode = "";

		String strErr = "";
		String mode = "6";

		String strPackage = "2";

		strProcName = "{call PKG_BILL_VIEW.PROC_HBLT_CHARGE_MST(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		try 
		{
			daoObj = new HisDAO("Billing", "AddServicesIPDTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "chargeTypeId", strChargeTypeId,1);
			daoObj.setProcInValue(nProcIndex, "catCode", strCategoryCode, 2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 7);
			daoObj.setProcInValue(nProcIndex, "pkg_flag", strPackage, 8);
			daoObj.setProcInValue(nProcIndex, "wardCode", strWardCode, 3);
			daoObj.setProcInValue(nProcIndex, "groupId", strGroupId, 4);
			daoObj.setProcInValue(nProcIndex, "searchtrfname", strTariffCode,5);
			daoObj.setProcInValue(nProcIndex, "searchMode", "1", 6);
			daoObj.setProcInValue(nProcIndex, "advance_flag", "1", 9);
			daoObj.setProcInValue(nProcIndex, "modeVal", mode, 10);
			daoObj.setProcInValue(nProcIndex, "end_date", "", 11);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 12);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 13);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (ws != null) 
			{
				voObj.setOfflineTariffList(ws);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("CashCollectionTransDAO.getTariffListByCode() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * retrieves Tariff List
	 * 
	 * @param voObj -
	 *            ValueObject
	 */
	public static void getDefaultTariffList(IpdBillManagementTransVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strIpdChargeTypeId = voObj.getStrIpdChgTypeId();
		String strChargeTypeId = voObj.getStrChargeTypeID();
		String strCategoryCode = voObj.getStrNewTreatmentCategory();
		String strWardCode = voObj.getStrOffLineWard();

		if (strChargeTypeId == null)
			strChargeTypeId = "";
		if (strCategoryCode == null)
			strCategoryCode = "";

		if (strWardCode == null)
			strWardCode = "";

		String strErr = "";
		String mode = "1";

		strProcName = "{call PKG_BILL_VIEW.proc_compulsory_charge_list(?,?,?,?,?,?,?,?)}";

		try 
		{
			daoObj = new HisDAO("Add Services", "AddServicesIPDTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", mode, 1);
			daoObj.setProcInValue(nProcIndex, "chargetypeid", strChargeTypeId,2);
			daoObj.setProcInValue(nProcIndex, "catcode", strCategoryCode, 3);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 5);
			daoObj.setProcInValue(nProcIndex, "wardcode", strWardCode, 4);
			daoObj.setProcInValue(nProcIndex, "ipdChargeTypeId",strIpdChargeTypeId, 6);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 8);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (ws != null) 
			{
				voObj.setOfflineTariffList(ws);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("CashCollectionTransDAO.getDefaultTariffList() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * retrieves Tariff List
	 * 
	 * @param voObj -
	 *            ValueObject
	 */
	public static void getSpecialTariffList(IpdBillManagementTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strIpdChargeTypeId = voObj.getStrIpdChgTypeId();
		String strChargeTypeId = voObj.getStrChargeTypeID();
		String strCategoryCode = voObj.getStrNewTreatmentCategory();
		String strWardCode = voObj.getStrOffLineWard();

		if (strChargeTypeId == null)
			strChargeTypeId = "";
		if (strCategoryCode == null)
			strCategoryCode = "";

		if (strWardCode == null)
			strWardCode = "";

		String strErr = "";
		String mode = "1";

		strProcName = "{call PKG_BILL_VIEW.proc_special_charge_list(?,?,?,?,?,?,?,?)}";

		try {
			daoObj = new HisDAO("Add Services", "AddServicesIPDTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", mode, 1);
			daoObj.setProcInValue(nProcIndex, "chargetypeid", strChargeTypeId,
					2);
			daoObj.setProcInValue(nProcIndex, "catcode", strCategoryCode, 3);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 5);
			daoObj.setProcInValue(nProcIndex, "wardcode", strWardCode, 4);
			daoObj.setProcInValue(nProcIndex, "ipdChargeTypeId",
					strIpdChargeTypeId, 6);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 8);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (ws != null) {

				// System.out.println("ws size : "+ws.size());
				voObj.setOfflineTariffList(ws);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("CashCollectionTransDAO.getDefaultTariffList() --> "
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
	 * PATIENTCATEOGRYCOMBO(vo) -- > This Method is Used to get WebRowSet for
	 * PatientCategory Combo from Table GBLT_PATIENT_CAT_MST
	 */
	public static WebRowSet PATIENTCATEOGRYCOMBO(IpdBillManagementTransVO VO) {
		String proc_name = "";

		proc_name = "{call PKG_SIMPLE_VIEW.PROC_GBLT_PATIENT_CAT_MST(?,?,?,?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.PATIENTCATEOGRYCOMBO(VO)");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "modeVal", "1", 1);

			dao.setProcInValue(nprocIndex, "puk_no", "", 3);
			dao.setProcInValue(nprocIndex, "charge_type_id", "0", 4);
			dao.setProcInValue(nprocIndex, "effect_from", "", 5);
			dao.setProcInValue(nprocIndex, "effect_TO", "", 6);

			dao.setProcInValue(nprocIndex, "hosp_code",
					BillConfigUtil.SUPER_HOSPITAL_CODE, 2);

			dao.setProcOutValue(nprocIndex, "ERR", 1, 7); // 1 for string
															// return
			// value

			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 8); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		}

		catch (Exception e) {

			// e.printStackTrace();

			VO
					.setStrMsgString("IpdBillManagementTransDAO.PATIENTCATEOGRYCOMBO()"
							+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

		return ws;
	}

	/**
	 * WARDNAMECOMBO(vo) -- > This Method is Used to get WebRowSet for WardName
	 * Combo from Table HIPT_WARD_MST
	 */
	public static WebRowSet WARDNAMECOMBO(IpdBillManagementTransVO VO) {
		String proc_name = "";
		proc_name = "{call PKG_IPD_VIEW.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		// String s1[] = VO.getStrComboValue();
		// System.out.println("s1[0]->"+s1[0]);
		// System.out.println("s1[1]->"+s1[1]);
		// System.out.println("Inside Ward Combo->"+s1[2]);

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.WARDNAMECOMBO()");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "modeVal", "2", 1);

			dao.setProcInValue(nprocIndex, "wardtypcode", "0", 2);
			dao.setProcInValue(nprocIndex, "deptcode", "0", 3);
			dao.setProcInValue(nprocIndex, "deptunitcode", "0", 4);
			dao.setProcInValue(nprocIndex, "unitcode", "0", 5);
			dao.setProcInValue(nprocIndex, "age", "0", 6);
			dao.setProcInValue(nprocIndex, "gender", "0", 7);
			dao.setProcInValue(nprocIndex, "treatment_cat", "0", 8);
			dao.setProcInValue(nprocIndex, "effect_from", "", 10);
			dao.setProcInValue(nprocIndex, "effect_to", "", 11);
			dao.setProcInValue(nprocIndex, "diseasetypcode", "0", 12);
			dao.setProcInValue(nprocIndex, "wardcode", "0", 13);
			dao.setProcInValue(nprocIndex, "puk_no", "0", 14);
			dao.setProcInValue(nprocIndex, "charge_type_id", "0", 15);

			dao.setProcInValue(nprocIndex, "hosp_code",VO.getStrHospitalCode(), 9);

			dao.setProcOutValue(nprocIndex, "ERR", 1, 16); // 1 for string
															// return
			// value

			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 17); // 2 for
																	// object

			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		}

		catch (Exception e) {

			// e.printStackTrace();

			VO.setStrMsgString("IpdBillManagementTransDAO.WARDNAMECOMBO()"
					+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}
		return ws;
	}

	/* ---------- Here We Get Account Detail By Passing Account No -------- */
	/**
	 * getAccountDtlWithAcctNo(vo) -- > This Method is Used to get Account
	 * Detail from Table HBLT_PATACCOUNT_DTL
	 */

	public static void getAccountDtlWithAcctNo(IpdBillManagementTransVO VO) {
		String str1 = VO.getStrAccountNo();

		String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_VIEW.PROC_HBLT_PATACCOUNT_DTL(?,?,?,?,?,?,?,?)}";

		HisDAO dao = null;

		int procIndex1 = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("billing",
					"transactions.DAObilling.getAccountDtlWithAcctNo()");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "accNo", str1, 1);
			dao.setProcInValue(procIndex1, "modeVal", "1", 5);
			dao.setProcInValue(procIndex1, "puk", "", 2);
			dao.setProcInValue(procIndex1, "chargeTypeId", "", 3);
			dao.setProcInValue(procIndex1, "activeAccount", "1", 4);
			dao.setProcInValue(procIndex1, "hosp_code",
					VO.getStrHospitalCode(), 6);
			dao.setProcOutValue(procIndex1, "ERR", 1, 7); // 1 for string
															// return
			// value
			dao.setProcOutValue(procIndex1, "RESULTSET", 2, 8); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(procIndex1);
			// get value
			strerr = dao.getString(procIndex1, "ERR");
			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(procIndex1, "RESULTSET");
				VO.setStrDtlWs(ws);
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		} catch (Exception e) {

			VO
					.setStrMsgString("IpdBillManagementTransDAO.getAccountDtlWithAcctNo() --> "
							+ e.getMessage());
			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

	}

	public static void getAccountDtl(IpdBillManagementTransVO VO) 
	{
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.PROC_HBLT_PATACCOUNT_DTL(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int procIndex1 = 0;
		String strerr = "";
		WebRowSet ws = null;
		try 
		{
			dao = new HisDAO("billing","transactions.DAObilling.getAccountDtlWithAcctNo()");
			procIndex1 = dao.setProcedure(proc_name1);
			dao.setProcInValue(procIndex1, "accNo", VO.getStrAccountNo(), 1);
			dao.setProcInValue(procIndex1, "modeVal", "5", 5);
			dao.setProcInValue(procIndex1, "puk", "", 2);
			dao.setProcInValue(procIndex1, "chargeTypeId", "", 3);
			dao.setProcInValue(procIndex1, "activeAccount", "", 4);
			dao.setProcInValue(procIndex1, "hosp_code",VO.getStrHospitalCode(), 6);
			dao.setProcOutValue(procIndex1, "ERR", 1, 7);
			dao.setProcOutValue(procIndex1, "RESULTSET", 2, 8);
			dao.executeProcedureByPosition(procIndex1);
			strerr = dao.getString(procIndex1, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "RESULTSET");
				if (ws != null && ws.next()) 
				{
					VO.setStrDepartment(ws.getString(1));
					VO.setStrTreatmentCategory(ws.getString(2));
					VO.setStrWardType(ws.getString(3));//Ward Code
					VO.setStrSpecialWardType(ws.getString(4));//Charge Type
					VO.setStrStartDate(ws.getString(5));
					VO.setStrEndDate(ws.getString(6));
					VO.setStrAdvanceamt(ws.getString(7));
					VO.setStrDeptUnitId(ws.getString(8));
					VO.setStrAccStatus(ws.getString(9));
					VO.setGrpid(ws.getString(10));
					VO.setAccType(ws.getString(11));
					VO.setFinalBillFlag(ws.getString(12));
					VO.setServiceFlag(ws.getString(13));
				}
				
				VO.setStrMsgType("0");
			} 
			else 
			{
				throw new Exception(strerr);
			}
		} 
		catch (Exception e) 
		{
			VO.setStrMsgString("IpdBillManagementTransDAO.getAccountDtlWithAcctNo() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		}
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
	}

	/* ---------------Calling of Function for View Charges--------------------- */
	/**
	 * callingFunctionView(vo) -- > This Method is Call the Function for View
	 * Charges
	 */
	public static void callingFunctionView(IpdBillManagementTransVO VO) {

		HisDAO dao = null;
		// WebRowSet ws = null;
		// Declearing Variable
		String retVal = null;
		int funcIndex = 0;
		// Split the Value
		String[] id = VO.getStrGroupId().split("\\^");

		if (id[1].equals("1") || id[1].equals("3")) {
			id[0] = "0";
		}
		try {
			dao = new HisDAO("BillingModule", "IpdBillManagementTrans");
			if (id[6].equals("0")) {
				funcIndex = dao
						.setFunction("{? = call BILL_MST.getChargeDetails(?,?,?,?,?,?)}");
				// Set Value
				dao.setFuncInValue(funcIndex, 2, "1");
				// dao.setFuncInValue(funcIndex,3,"hospCode");
				dao.setFuncInValue(funcIndex, 3, VO.getStrHospitalCode());
				// dao.setFuncInValue(funcIndex,4,"wardCode");
				dao.setFuncInValue(funcIndex, 4, id[0]);
				// dao.setFuncInValue(funcIndex,5,"catcode");
				dao.setFuncInValue(funcIndex, 5, id[2]);
				// dao.setFuncInValue(funcIndex,6,"chargetype");
				dao.setFuncInValue(funcIndex, 6, id[1]);
				// dao.setFuncInValue(funcIndex,7,"trfid");
				dao.setFuncInValue(funcIndex, 7, id[3]);
				dao.setFuncOutValue(funcIndex, 1);
				// Execute Function

				dao.executeFunction(funcIndex);
				retVal = dao.getFuncString(funcIndex);

				VO.setStrRetValue(retVal);
				// System.out.println("Ret Value is--I->>"+retVal);
			} else {
				funcIndex = dao
						.setFunction("{? = call BILL_MST.getChargeDetails(?,?,?,?,?,?,?)}");
				// Set Value of Function
				dao.setFuncInValue(funcIndex, 2, "1");
				// dao.setFuncInValue(funcIndex,3,"hospCode");
				dao.setFuncInValue(funcIndex, 3, VO.getStrHospitalCode());
				// dao.setFuncInValue(funcIndex,4,"wardCode");
				dao.setFuncInValue(funcIndex, 4, id[0]);
				// dao.setFuncInValue(funcIndex,5,"catcode");
				dao.setFuncInValue(funcIndex, 5, id[2]);
				// dao.setFuncInValue(funcIndex,6,"chargetype");
				dao.setFuncInValue(funcIndex, 6, id[1]);
				// dao.setFuncInValue(funcIndex,7,"trfid");
				dao.setFuncInValue(funcIndex, 7, id[3]);
				// dao.setFuncInValue(funcIndex,8,"effectDate");
				dao.setFuncInValue(funcIndex, 8, id[6]);

				dao.setFuncOutValue(funcIndex, 1);
				// Execute Function

				dao.executeFunction(funcIndex);
				// Getting Return value
				retVal = dao.getFuncString(funcIndex);
				// System.out.println("Ret Value is--II->>"+retVal);

				VO.setStrRetValue(retVal);

			}

			if (id[5].equals("1")) {
				CallingHbltPackageChgMst(VO);
				if (!VO.getStrMsgType().equals("0")) {
					throw new Exception(VO.getStrMsgString());
				}
			} else {
				VO.setChkgValue("1");
			}

		} catch (Exception e) {
			VO
					.setStrMsgString("IpdBillManagementTransDAO.callingFunctionView() --> "
							+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	/**
	 * CallingHbltPackageChgMst(vo) -- > This Method is get Data for Package
	 * Charge Master from Table HBLT_PACKAGE_CHARGE_MST
	 */

	public static void CallingHbltPackageChgMst(IpdBillManagementTransVO VO) {

		// String str = VO.getStrGroupId();

		String proc_name = "";

		proc_name = "{call PKG_BILL_VIEW.proc_HBLT_PACKAGE_CHARGE_MST(?,?,?,?,?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		String[] id = VO.getStrGroupId().split("\\^");
		// System.out.println("Calling id[0]ward->"+id[0]);
		// System.out.println("Calling id[1]hospital->"+id[1]);
		// System.out.println("Calling id[2]patient->"+id[2]);
		// System.out.println("Calling id[3]tariff->"+id[3]);
		// System.out.println("Calling id[4]Gruop->"+id[4]);
		// System.out.println("Calling Effective Date
		// isPackaged[5]Gruop->"+id[5]);
		// System.out.println("Calling isPackaged[5]Gruop->"+id[6]);

		try {
			dao = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.CallingHbltPackageChgMst(VO)");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "hosp_code",
					VO.getStrHospitalCode(), 1);
			// System.out.println("hosp_code::"+VO.getStrHospitalCode());
			dao.setProcInValue(nprocIndex, "charge_id", id[1], 2);
			// System.out.println("charge_id::"+id[1]);
			dao.setProcInValue(nprocIndex, "cat_code", id[2], 3);
			// System.out.println("cat_code::"+id[2]);
			dao.setProcInValue(nprocIndex, "ward_id", id[0], 4);
			// System.out.println("Ward_id"+id[0]);
			dao.setProcInValue(nprocIndex, "pkg_id", id[3], 5);
			// System.out.println("pkg_id::"+id[3]);
			dao.setProcInValue(nprocIndex, "effect_date", id[6], 6);
			// System.out.println("effect_date::"+id[6]);
			dao.setProcInValue(nprocIndex, "modeval", "1", 7);

			dao.setProcOutValue(nprocIndex, "err", 1, 8); // 1 for string
															// return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2, 9); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				VO.setStrPkgBrkUpWs(ws);
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			VO
					.setStrMsgString("IpdBillManagementTransDAO.CallingHbltPackageChgMst() --> "
							+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	/**
	 * hbltPackageChgMstForAddService(vo,i) -- > This Method is get Data for
	 * Package Charge Master for Add Service form Table HBLT_PACKAGE_CHARGE_MST
	 */

	public static void hbltPackageChgMstForAddService(
			IpdBillManagementTransVO VO, int i) {
		String proc_name = "";

		proc_name = "{call PKG_BILL_VIEW.proc_HBLT_PACKAGE_CHARGE_MST(?,?,?,?,?,?,?,?,?)}";

		HisDAO dao1 = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {
			dao1 = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.HbltPackageChgMstForAddService(VO)");

			nprocIndex = dao1.setProcedure(proc_name);

			// set value

			dao1.setProcInValue(nprocIndex, "hosp_code", VO
					.getStrHospitalCode(), 1);

			dao1.setProcInValue(nprocIndex, "charge_id", VO
					.getStrChargeTypeID(), 2);

			dao1.setProcInValue(nprocIndex, "cat_code", VO
					.getStrPatientCatCode(), 3);

			dao1.setProcInValue(nprocIndex, "ward_id", VO.getStrWardCode(), 4);

			dao1
					.setProcInValue(nprocIndex, "pkg_id",
							VO.getStrTariffId()[i], 5);

			dao1.setProcInValue(nprocIndex, "modeval", "1", 7);
			dao1.setProcInValue(nprocIndex, "effect_date", "", 6);

			dao1.setProcOutValue(nprocIndex, "err", 1, 8); // 1 for string
															// return
			// value
			dao1.setProcOutValue(nprocIndex, "resultset", 2, 9); // 2 for
																	// object

			// execute procedure

			dao1.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao1.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao1.getWebRowSet(nprocIndex, "resultset");
				VO.setNSize(ws.size());
				String[] strHiddenVal1 = new String[ws.size()];
				int k = 0;

				while (ws.next()) {
					strHiddenVal1[k] = ws.getString(6);
					VO.setStrTempVal(strHiddenVal1);
					k = k + 1;
					if (k == ws.size())
						break;

				}
				// VO.setStrPkgBrkUpWs(ws);
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}
		}

		catch (Exception e) {
			VO
					.setStrMsgString("IpdBillManagementTransDAO.HbltPackageChgMstForAddService() --> "
							+ e.getMessage());
			VO.setStrMsgType("1");
		} finally {
			if (dao1 != null) {
				dao1.free();
				dao1 = null;
			}
		}
	}

	/**
	 * hbltPackageChgMstForBillApproval(vo,i) -- > This Method is get Data for
	 * Package Charge Master for Add Service from Table HBLT_PACKAGE_CHARGE_MST
	 */

	public static void hbltPackageChgMstForBillApproval(
			IpdBillManagementTransVO VO, int i) {
		String proc_name = "";

		proc_name = "{call PKG_BILL_VIEW.proc_HBLT_PACKAGE_CHARGE_MST(?,?,?,?,?,?,?,?,?)}";

		HisDAO dao1 = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao1 = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.HbltPackageChgMstForAddService(VO)");

			nprocIndex = dao1.setProcedure(proc_name);

			// set value

			dao1.setProcInValue(nprocIndex, "hosp_code", VO
					.getStrHospitalCode(), 1);
			dao1.setProcInValue(nprocIndex, "charge_id", VO
					.getStrChargeTypeID(), 2);
			dao1.setProcInValue(nprocIndex, "cat_code", VO
					.getStrPatientCatCode(), 3);
			dao1.setProcInValue(nprocIndex, "ward_id", VO.getStrWardCode(), 4);
			dao1
					.setProcInValue(nprocIndex, "pkg_id",
							VO.getStrTariffId()[i], 5);
			dao1.setProcInValue(nprocIndex, "modeval", "1", 7);
			dao1.setProcInValue(nprocIndex, "effect_date", "", 6);
			dao1.setProcOutValue(nprocIndex, "err", 1, 8); // 1 for string
															// return
			// value
			dao1.setProcOutValue(nprocIndex, "resultset", 2, 9); // 2 for
																	// object
			// execute procedure
			dao1.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao1.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao1.getWebRowSet(nprocIndex, "resultset");
				VO.setNSize(ws.size());
				String[] strHiddenVal1 = new String[ws.size()];
				int k = 0;
				while (ws.next()) {
					strHiddenVal1[k] = ws.getString(6);
					VO.setStrTempVal(strHiddenVal1);
					k = k + 1;
					if (k == ws.size())
						break;

				}
				// VO.setStrPkgBrkUpWs(ws);
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			VO
					.setStrMsgString("IpdBillManagementTransDAO.HbltPackageChgMstForAddService() --> "
							+ e.getMessage());
			VO.setStrMsgType("1");
		}

		finally {
			if (dao1 != null) {
				dao1.free();
				dao1 = null;
			}

		}

	}

	/**
	 * hbltPackageChgMstForBillApproval(vo,i) -- > This Method is get Data for
	 * Package Charge Master for Bill Approval Transaction from Table
	 * HBLT_PACKAGE_CHARGE_MST
	 */

	public static void hbltPackageChgMstForBillApproval1(
			IpdBillManagementTransVO VO, int i) {

		String proc_name = "";

		proc_name = "{call PKG_BILL_VIEW.proc_HBLT_PACKAGE_CHARGE_MST(?,?,?,?,?,?,?,?,?)}";

		HisDAO dao1 = null;

		int nprocIndex = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao1 = new HisDAO("IpdBillManagementTrans",
					"transactions.IpdBillManagementTransDAO.HbltPackageChgMstForAddService(VO)");

			nprocIndex = dao1.setProcedure(proc_name);

			// set value

			dao1.setProcInValue(nprocIndex, "hosp_code", VO
					.getStrHospitalCode(), 1);
			dao1.setProcInValue(nprocIndex, "charge_id", VO
					.getStrChargeTypeID(), 2);
			dao1.setProcInValue(nprocIndex, "cat_code", VO
					.getStrPatientCatCode(), 3);
			dao1.setProcInValue(nprocIndex, "ward_id", VO.getStrWardCode(), 4);
			dao1
					.setProcInValue(nprocIndex, "pkg_id",
							VO.getStrTariffId()[i], 5);
			dao1.setProcInValue(nprocIndex, "modeval", "1", 7);

			dao1.setProcInValue(nprocIndex, "effect_date", "", 6);

			dao1.setProcOutValue(nprocIndex, "err", 1, 8); // 1 for string
															// return
			// value
			dao1.setProcOutValue(nprocIndex, "resultset", 2, 9); // 2 for
																	// object

			// execute procedure

			dao1.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao1.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao1.getWebRowSet(nprocIndex, "resultset");
				VO.setNSize(ws.size());
				String[] strHiddenVal1 = new String[ws.size()];
				int k = 0;

				while (ws.next()) {
					strHiddenVal1[k] = ws.getString(6);
					VO.setStrTempVal(strHiddenVal1);
					k = k + 1;
					if (k == ws.size())
						break;

				}

				// VO.setStrPkgBrkUpWs(ws);
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}
		}

		catch (Exception e) {
			VO
					.setStrMsgString("IpdBillManagementTransDAO.HbltPackageChgMstForAddService() --> "
							+ e.getMessage());

			VO.setStrMsgType("1");

		}

		finally {

			if (dao1 != null) {

				dao1.free();

				dao1 = null;

			}

		}

	}

	/* Insert Method for Add Service */

	/**
	 * InsertAddServiceTrans(vo) -- > This Method is Used to Insert Data for Add
	 * Service's Transaction Here we use five Table SBLT_PRIMARYKEY_DTL ,
	 * SBLT_PRIMARYKEY_LOG_DTL HBLT_PATACCOUNT_DTL , HBLT_PATACCOUNT_SERVICE_DTL ,
	 * HBLT_PATACCOUNT_PACKAGE_DTL
	 */

	public static boolean InsertAddServiceTrans(IpdBillManagementTransVO vo) 
	{
		HisDAO dao = null;
		boolean retVal = false;
		int blockLen = 0;
		long compChargeReqNo = 0;
		long specialReqNo = 0;
		long tariffReqNo = 0;

		int compChargeLen = 0;
		int splChargeLen = 0;
		int length = 0;

		if (vo.getStrCompChargeCheck() != null) 
		{
			compChargeLen = vo.getStrCompChargeCheck().length;
		}
		if (vo.getStrSpecialChargeCheck() != null) 
		{
			splChargeLen = vo.getStrSpecialChargeCheck().length;
		}
		if (vo.getStrTariffId() != null) 
		{
			length = vo.getStrTariffId().length;
		}
		if (compChargeLen > 0) 
		{
			blockLen = blockLen + 1;
		}
		if (splChargeLen > 0) 
		{
			blockLen = blockLen + 1;
		}
		if (length > 0) 
		{
			blockLen = blockLen + 1;
		}

		// Createing Object for Table Specific DAO
		PrimaryKeyDAO pKeyDao = new PrimaryKeyDAO();
		PrimaryKeyLogDAO pKeyLogDao = new PrimaryKeyLogDAO();
		PatientAccountDAO pActDao = new PatientAccountDAO();
		PatientAccountServiceDAO pActServDao = new PatientAccountServiceDAO();
		// PatientAccountPackageDAO pActPckDao = new PatientAccountPackageDAO();
		// OutBoundDAO outBoundDAO = new OutBoundDAO();

		try 
		{
			dao = new HisDAO("billing","transactions.DAObilling.InsertAddServiceTrans()");
			//System.out.println("length"+length);
			if (length > 0) 
			{
				pKeyDao.setStrKeyname("BILL_IPD");
				pKeyDao.setStrBlockkey(blockLen + "");
				pKeyDao.setStrHospCode(vo.getStrHospitalCode());
				pKeyDao.select(dao);
				vo.setStrReqNo(pKeyDao.getStrPrimrayKeyValue());
				pKeyDao = null;
			}

			if (compChargeLen > 0) 
			{
				compChargeReqNo = Long.parseLong(vo.getStrReqNo());
			}
			if (compChargeReqNo > 0 && splChargeLen > 0) 
			{
				specialReqNo = compChargeReqNo + 1;
			} 
			else if(splChargeLen > 0) 
			{
				specialReqNo = Long.parseLong(vo.getStrReqNo());
			}
			if (specialReqNo > 0 && length > 0) 
			{
				tariffReqNo = specialReqNo + 1;
			} 
			else if (specialReqNo <=0 && compChargeReqNo > 0 && length > 0) 
			{
				tariffReqNo = compChargeReqNo + 1;
			} 
			else if (specialReqNo <=0  && compChargeReqNo <=0  && length > 0) 
			{
				tariffReqNo = Long.parseLong(vo.getStrReqNo());
			}
			if (vo.getStrReqNo() != null && !vo.getStrReqNo().equals("")) 
			{
				for (int k = 0; k < length; k++) 
				{
					if (!vo.getDeleteFlag()[k].equals("0")) //When Any One Row Added,Deleted or Modified
					{
						if (vo.getStrServiceType()[k].equals("1")) //Compulsory Charges
						{
							DmlAccountAddServiceProcedure(vo, dao, k,compChargeReqNo);
						} 
						else if (vo.getStrServiceType()[k].equals("2")) //Special Charges
						{
							DmlAccountAddServiceProcedure(vo, dao, k, specialReqNo);
						} 
						else //Tariffs Add Service
						{
							DmlAccountAddServiceProcedure(vo, dao, k, tariffReqNo);
						}
					}
				}
			}
			
			//System.out.println("vo.getStrNetTariffAmt()"+vo.getStrNetTariffAmt());
			// comment due to already update all amt by DmlAccountAddServiceProcedure  dml_account_add_service
			/*pActDao.setStrPuk(vo.getStrPukNo());
			pActDao.setStrPatAccNo(vo.getStrPatAcctNo());
			pActDao.setStrNetActualAmt(vo.getStrNetActualTariffAmt());
			pActDao.setStrSerTaxNetAmt(vo.getStrNetServiceTaxAmt());
			pActDao.setStrTariffNetAmt(vo.getStrNetTariffAmt());
			pActDao.setStrHospitalCode(vo.getStrHospitalCode());
			pActDao.setIsBillFinal(vo.getIsBillFinal());
			pActDao.setStrSeatId(vo.getStrSeatId());
            pActDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			pActDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
			pActDao.update3(dao);*/
			
			if (new BillConfigUtil(vo.getStrHospitalCode()).getIpdGenAdtProcessType().equals("2")) 
			{
				String proc_name1 = "";
				proc_name1 = "{call PKG_BILL_DML.dml_update_account_dtl(?,?,?,?,?,?,?,?)}";

				int procIndex1 = 0;

				procIndex1 = dao.setProcedure(proc_name1);

				dao.setProcInValue(procIndex1, "modval", "1", 1);

				dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode(), 2);
				dao.setProcInValue(procIndex1, "accNo", vo.getStrPatAcctNo(), 3);
				dao.setProcInValue(procIndex1, "wardId", vo.getStrSpecialWardType(), 4);
				dao.setProcInValue(procIndex1, "ipdChargeTypeId", vo.getStrWardType(), 5);
				dao.setProcInValue(procIndex1, "patCat", vo.getStrPatientCatCode(), 6);
				dao.setProcInValue(procIndex1, "deptId", vo.getStrDepartment(),7);
				dao.setProcOutValue(procIndex1, "ERR", 1, 8);
				dao.execute(procIndex1, 1);
			}
			synchronized (dao) 
			{
				dao.fire();
				retVal = true; 
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrErrPrimKeyLog(e.getMessage());
			vo.setStrMsgString("IpdBillManagementTransDAO.InsertAddServiceTrans()--> "+ e.getMessage());
			vo.setStrMsgType("1");
			retVal = false;

			try 
			{
				pKeyLogDao.setStrKeyname("REQNO");
				pKeyLogDao.setStrStartkey(vo.getStrReqNo());
				pKeyLogDao.setStrBlockkey("1");
				pKeyLogDao.setStrHospCode(vo.getStrHospitalCode());
				pKeyLogDao.setStrError(vo.getStrErrPrimKeyLog());
				pKeyLogDao.setStrSeatid(vo.getStrSeatId());
				pKeyLogDao.insert(dao);
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
				vo.setStrMsgString("IpdBillManagementTransDAO.InsertAddServiceTrans()--> "+ e1.getMessage());
				vo.setStrMsgType("1");
				new HisException("billing", "IpdBillMangementTrans","IpdBillManagementTransDAO.InsertAddServiceTrans() --> "+ e1.getMessage());
				retVal = false;
			}
		} 
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
			if (pActDao != null || pKeyLogDao != null /* || pActPckDao != null */ || pActServDao != null) 
			{
				pActDao = null;
				pKeyLogDao = null;
			}
		}
		return retVal;

	}

	/**
	 * *****************This Method is Used for Insert Logic in Add
	 * Services**************
	 */
	public static void DmlAccountAddServiceProcedure(IpdBillManagementTransVO vo, HisDAO dao, int i, long requestNo) 
	{
		String proc_name = "";
		proc_name = "{call pkg_bill_dml.dml_account_add_service(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nprocIndex = 0;
		try 
		{
			double rate = Double.parseDouble(vo.getStrTariffRate()[i]);
			double baseValue = Double.parseDouble(vo.getStrUnitBaseValue()[i]);
			double qty = Double.parseDouble(vo.getStrBillQty()[i]);
			double serViceTax = Double.parseDouble(vo.getStrServTax()[i]);
			double RateBaseValue = Double.parseDouble(vo.getStrRateBaseValue()[i]);
			double tariffActualCost = Double.parseDouble(vo.getStrTariffActualCost()[i]);
			double serviceTaxAmt = 0.0;
			double total = 0.0;

			total = (rate / RateBaseValue) * (qty * baseValue);

			serviceTaxAmt = total * (serViceTax / 100);
			String service_tax = Double.toString(serviceTaxAmt);

			double dtrf_amt = Double.parseDouble(vo.getStrNetTariffAmt());
			double dactual_amt = Double.parseDouble(vo.getStrNetActualTariffAmt());
			double dser_tax_amt = Double.parseDouble(vo.getStrNetServiceTaxAmt());

			
			dtrf_amt = dtrf_amt + ((qty * baseValue) * (rate / RateBaseValue))+ serviceTaxAmt;
			dactual_amt = dactual_amt+ ((qty * baseValue) * (tariffActualCost / RateBaseValue));
			dser_tax_amt = dser_tax_amt + serviceTaxAmt;

			//--strAddRemUpdFlag=1 -Delete,2-Add,3-Update
			//Add Amount Only When Prev Req No Present
			if(vo.getDeleteFlag()[i]!=null && (vo.getDeleteFlag()[i].equals("1") || vo.getDeleteFlag()[i].equals("3")))
			{
				if(vo.getStrPrevReqNo()[i]!=null && !vo.getStrPrevReqNo()[i].equals("") && !vo.getStrPrevReqNo()[i].equals("0"))
				{
					vo.setStrNetTariffAmt("" + dtrf_amt);
					vo.setStrNetActualTariffAmt("" + dactual_amt);
					vo.setStrNetServiceTaxAmt("" + dser_tax_amt);
				}
				else
				{
					vo.setStrNetTariffAmt("0");
					vo.setStrNetActualTariffAmt("0");
					vo.setStrNetServiceTaxAmt("0");
				}
			}
			else
			{
				vo.setStrNetTariffAmt("" + dtrf_amt);
				vo.setStrNetActualTariffAmt("" + dactual_amt);
				vo.setStrNetServiceTaxAmt("" + dser_tax_amt);
			}

			nprocIndex = dao.setProcedure(proc_name);
			//System.out.println("strDisActAmt"+vo.getStrDisActAmt()[i]);
			dao.setProcInValue(nprocIndex, "modval", "1", 1);
			dao.setProcInValue(nprocIndex, "hosp_code",vo.getStrHospitalCode(), 2);
			dao.setProcInValue(nprocIndex, "reqNo", String.valueOf(requestNo),3);
			dao.setProcInValue(nprocIndex, "patAccNo", vo.getStrPatAcctNo(), 4);
			dao.setProcInValue(nprocIndex, "puk", vo.getStrPukNo(), 5);
			dao.setProcInValue(nprocIndex, "catCode",vo.getStrPatientCatCode(), 6);
			dao.setProcInValue(nprocIndex, "deptCode", vo.getStrDepartment(), 7);
			dao.setProcInValue(nprocIndex, "grpId", vo.getStrGrpId()[i], 8);
			dao.setProcInValue(nprocIndex, "trfId", vo.getStrTariffId()[i], 9);
			dao.setProcInValue(nprocIndex, "reqQty", vo.getStrBillQty()[i], 10);
			dao.setProcInValue(nprocIndex, "reqQtyUnitId",vo.getStrUnitIDVal()[i], 11);
			dao.setProcInValue(nprocIndex, "rate",vo.getStrTariffRate()[i], 12);
			dao.setProcInValue(nprocIndex, "actRate", vo.getStrTariffActualCost()[i], 13);
			dao.setProcInValue(nprocIndex, "rateUnitId",vo.getStrUnitIDVal()[i], 14);
			dao.setProcInValue(nprocIndex, "serTax", vo.getStrServTax()[i], 15);
			dao.setProcInValue(nprocIndex, "serTaxAmt", service_tax, 16);
			dao.setProcInValue(nprocIndex, "trfNetAmt",vo.getStrTariffNetAmt()[i], 17);
			dao.setProcInValue(nprocIndex, "chargeTypeId", "2", 18);
			dao.setProcInValue(nprocIndex, "wardId",vo.getStrSpecialWardType(), 19);
			dao.setProcInValue(nprocIndex, "bServiceId", "11", 20);
			dao.setProcInValue(nprocIndex, "serviceId",vo.getStrServiceId()[i], 21);
			dao.setProcInValue(nprocIndex, "gblTrfId",vo.getStrGlblTariffId()[i], 22);
			dao.setProcInValue(nprocIndex, "isRefund",vo.getStrIsRefundable()[i], 23);
			dao.setProcInValue(nprocIndex, "isPackage", vo.getStrIsPackg()[i],24);
			dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId(), 25);
			dao.setProcInValue(nprocIndex, "slNo", vo.getsNo()[i], 26);
			dao.setProcInValue(nprocIndex, "ipdChargeTypeId", vo.getStrWardType(), 27);
			//System.out.println("vo.getStrOfflineTariffDate()"+vo.getStrOfflineTariffDate()[i]);
			dao.setProcInValue(nprocIndex, "reqDate", vo.getStrOfflineTariffDate()[i], 28);
			dao.setProcInValue(nprocIndex, "endDate", vo.getStrEndDate(), 29);
			dao.setProcInValue(nprocIndex, "serviceType", vo.getStrServiceType()[i], 30);			
			dao.setProcInValue(nprocIndex, "tariffDate", vo.getStrOfflineTariffDate()[i], 31);			
			dao.setProcInValue(nprocIndex, "strPriority", vo.getStrPriority()[i], 32);			
			dao.setProcInValue(nprocIndex, "strDiscount", vo.getStrDiscount()[i], 33);			
			dao.setProcInValue(nprocIndex, "strDiscountType", vo.getStrDiscountType()[i], 34);			
			dao.setProcInValue(nprocIndex, "strDiscountAmt", vo.getStrDiscountAmt()[i], 35);			
			dao.setProcInValue(nprocIndex, "strTrfName", vo.getStrOfflineTariffName()[i], 36);			
			dao.setProcInValue(nprocIndex, "strAddRemUpdFlag", vo.getDeleteFlag()[i], 37);
			dao.setProcInValue(nprocIndex, "strPrevReqNo", vo.getStrPrevReqNo()[i], 38);
			dao.setProcInValue(nprocIndex, "dblOccFlag", "0", 39);//dblOccFlag=0/1
			dao.setProcInValue(nprocIndex, "strActualOutDate", "0", 40);//If dblOccFlag=1 THEN ACTUAL OPUT DATE WILL BE UPTO LAST DATE OF ICU STAY 
			dao.setProcInValue(nprocIndex, "ip", GetNetworkAddress.GetAddress("ip"), 41);
			dao.setProcInValue(nprocIndex, "mac", GetNetworkAddress.GetAddress("mac"), 42);
			dao.setProcInValue(nprocIndex, "strDisActAmt", vo.getStrDisActAmt()[i], 43);
			dao.setProcInValue(nprocIndex, "adtOnlineFlag","0", 44);
			dao.setProcOutValue(nprocIndex, "err", 1, 45);
			dao.execute(nprocIndex, 1);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("RefundApprovalTransDAO.DmlAccountAddServiceProcedure() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	/*------------------------Insert Method for Bill Approval--------------------------*/
	/**
	 * InsertBillApproval(vo) -- > This Method is Used to Insert Data for Bill
	 * Approval Transaction Here we use five Table SBLT_PRIMARYKEY_DTL ,
	 * SBLT_PRIMARYKEY_LOG_DTL HBLT_PATACCOUNT_DTL , HBLT_PATACCOUNT_SERVICE_DTL
	 * ,SBLT_INBOUND_DTL , HBLT_BILLREQ_DTL , HBLT_BILLREQ_TARIFF_DTL ,
	 * HBLT_APPROVAL_DTL
	 */

	public static void InsertBillApproval(IpdBillManagementTransVO vo) {
		// Decleration
		HisDAO dao = null;

		int appFlag1 = 0, appFlag2 = 0, reqFlag = 0, reqFlag2 = 0;

		// double dactual_amt = 0.0, dser_tax_amt = 0.0, dtrf_amt = 0.0,
		// dtrf_amt_tot = 0.0, dis_amt = 0.0;

		double dis_amt = 0.0;

		double dblNetDisAmt = 0;
		double dblNetSTaxAmt = 0;
		double dblNetActualAmt = 0;
		double dblNetTrfAmt = 0;

		// double billQty = 0.0, qtyBaseValue = 0.0, tariffActualCost = 0.0,
		// serTaxAmt = 0.0;
		// double triffRate = 0.0, RateBaseValue = 0.0;
		// String trf_amt_tot = null;

		String app_id_discount = null;
		String app_id_bill = null;

		int length = vo.getStrTariffId().length;

		// Createing Object for Table Specific DAO
		PrimaryKeyDAO pKeyDao = new PrimaryKeyDAO();
		PrimaryKeyLogDAO pKeyLogDao = new PrimaryKeyLogDAO();
		PatientAccountDAO pActDao = new PatientAccountDAO();
		// OutBoundDAO outBoundDAO = new OutBoundDAO();

		ApprovalDAO pApprovalDao = new ApprovalDAO();

		try {
			dao = new HisDAO("billing",
					"transactions.DAObilling.InsertBillApproval()");

			if (vo.getStrTotalDiscAmt().trim().length() == 0) {

				vo.setStrTotalDiscAmt("0");
			}

			dis_amt = Double.parseDouble(vo.getStrTotalDiscAmt());

			if (dis_amt < 0) {
				/** ***** Here We Generate Primary Key(REQNO) ***** */
				// Here we Set Value For DAO
				pKeyDao.setStrKeyname("APPROVAL");
				pKeyDao.setStrBlockkey("2");
				pKeyDao.setStrHospCode(vo.getStrHospitalCode());
				// Procedure Execute
				try {
					pKeyDao.select(dao);
					vo.setStrApprovalId(pKeyDao.getStrPrimrayKeyValue());
					app_id_discount = pKeyDao.getStrPrimrayKeyValue();

					vo.setStrTotalDiscountApprovalId(app_id_discount);

					long napp_id_bill = (Long.parseLong(app_id_discount)) + 1;
					app_id_bill = Long.toString(napp_id_bill);

					appFlag1 = 1;
					appFlag2 = 1;
				} catch (Exception e) {

					e.printStackTrace();

					throw new Exception("pKeyDao.select(dis_amt>0) --> "
							+ e.getMessage());
				}

				if (app_id_discount != null && !app_id_discount.equals("")) {
					/** ********( STEP - II )******* */
					try {
						pApprovalDao.setStrApprovalId(app_id_discount);
						pApprovalDao.setStrApprovalSlNo("1");
						pApprovalDao.setStrReceiptType("1");
						pApprovalDao.setStrBServiceId("21");
						pApprovalDao.setStrChargeTypeId(vo.getStrChargeTypeID());
						pApprovalDao.setStrEmpNo(vo.getStrApprovalBy());
						pApprovalDao.setStrUserLevel(vo.getStrUserLevel());
						pApprovalDao.setStrDiscountUnit(vo.getStrOffLineTariffDiscountUnit());
						pApprovalDao.setStrRemarks(vo.getStrOffLineTariffDiscountReasonText());
						pApprovalDao.setStrDiscountType(vo.getStrOffLineTariffDiscountType());
						pApprovalDao.setStrApprovalType("2");
						pApprovalDao.setStrStatus("1");
						pApprovalDao.setStrSeatId(vo.getStrSeatId());
						pApprovalDao.setStrIsValid("1");
						pApprovalDao.setStrHospitalCode(vo.getStrHospitalCode());
						// Added into Batch
						pApprovalDao.insert(dao); // Pass Data Insert
						// Succesfully
						
					} catch (Exception e) {

						e.printStackTrace();

						throw new Exception(
								"pApprovalDao.insert(dao if dis_amt > 0.0)--> "
										+ e.getMessage());
					}
				}
			} else {

				/** ***** Here We Generate Primary Key(APPROVAL)********** */
				// Here we Set Value For DAO
				pKeyDao.setStrKeyname("APPROVAL");
				pKeyDao.setStrBlockkey("1");
				pKeyDao.setStrHospCode(vo.getStrHospitalCode());
				// Procedure Execute
				try {
					pKeyDao.select(dao);

					vo.setStrApprovalId(pKeyDao.getStrPrimrayKeyValue());
					app_id_bill = pKeyDao.getStrPrimrayKeyValue();
					appFlag2 = 1;

				} catch (Exception e) {

					e.printStackTrace();

					throw new Exception("pKeyDao.select(APPROVAL) --> "
							+ e.getMessage());

				}
			}

			vo.setStrTotalBillApprovalId(app_id_bill);

			/** ********( STEP - III )******* */
			try {
				pApprovalDao.setStrApprovalId(app_id_bill);

				pApprovalDao.setStrApprovalSlNo("1");
				pApprovalDao.setStrReceiptType("1");
				pApprovalDao.setStrBServiceId("21");
				pApprovalDao.setStrChargeTypeId(vo.getStrChargeTypeID());
				pApprovalDao.setStrEmpNo(vo.getStrApprovalBy());
				pApprovalDao.setStrUserLevel(vo.getStrUserLevel());
				pApprovalDao.setStrRemarks(vo.getStrRemarks());
				pApprovalDao.setStrApprovalType("1");
				pApprovalDao.setStrStatus("1");
				pApprovalDao.setStrSeatId(vo.getStrSeatId());
				pApprovalDao.setStrIsValid("1");
				pApprovalDao.setStrHospitalCode(vo.getStrHospitalCode());

				// Added into Batch
				pApprovalDao.insert(dao); // Insert Sussecfully
				
				
			} 
			
			catch (Exception e) {

				e.printStackTrace();

				throw new Exception("pApprovalDao.insert(dao)--> "
						+ e.getMessage());
			}

			if (length > 0) {
				/** **********Here We Generate Primary Key(REQNO)********** */
				pKeyDao.setStrKeyname("REQNO");
				pKeyDao.setStrBlockkey("1");
				pKeyDao.setStrHospCode(vo.getStrHospitalCode());
				// Procedure Execute
				try {
					pKeyDao.select(dao);

					vo.setStrReqNo(pKeyDao.getStrPrimrayKeyValue());
					reqFlag = 1;
				} catch (Exception e) {
					throw new Exception("pKeyDao.select(REQNO - 1) --> "
							+ e.getMessage());
				}

			}

			if (vo.getStrReqNo() != null && !vo.getStrReqNo().equals("")) {

				// New Added Services
				for (int i = 0; i < length; i++) {

					vo.setActual_amt(vo.getStrOfflineActualTariffAmtVal()[i]);
					vo.setTrf_amt_tot(vo.getStrOfflineTariffNetAmount()[i]);

					dblNetTrfAmt = dblNetTrfAmt
							+ Double.parseDouble(vo
									.getStrOfflineTariffNetAmount()[i]);

					dblNetActualAmt = dblNetActualAmt
							+ Double.parseDouble(vo
									.getStrOfflineActualTariffAmtVal()[i]);

					dblNetSTaxAmt = dblNetSTaxAmt
							+ Double.parseDouble(vo
									.getStrOfflineTariffServiceTaxAmtVal()[i]);

					DmlBillApprovalService(vo, dao, i);

				}

				// start --> out bount entry code added by Balasubramaniam M (
				// 10-Nov-2009 )

				/*
				 * outBoundDAO.setStrTransNo(vo.getStrReqNo());
				 * outBoundDAO.setStrReceiptNo("1");
				 * outBoundDAO.setStrPatAccNo(vo.getStrPatAcctNo());
				 * outBoundDAO.setStrPuk(vo.getStrPukNo());
				 * outBoundDAO.setStrAdmNo(vo.getStrAddmissionNo());
				 * outBoundDAO.setStrEpisodeCode(vo.getStrEpisodeNo());
				 * outBoundDAO.setStrPatientCatCode(vo.getStrPatientCatCode());
				 * outBoundDAO.setStrTransAmt(Double.toString(dblNetTrfAmt));
				 * outBoundDAO.setStrDeptCode(vo.getStrDeptId());
				 * outBoundDAO.setStrChargeTypeId("2");
				 * outBoundDAO.setStrIpdChargeTypeId(vo.getStrIpdChgTypeId());
				 * outBoundDAO.setStrBServiceId("11");
				 * outBoundDAO.setStrSeatId(vo.getStrSeatId());
				 * outBoundDAO.setStrTransType("1");
				 * outBoundDAO.setStrWardCode(vo.getStrWardCode());
				 * outBoundDAO.setStrIsBill("0");
				 * outBoundDAO.setStrIsOnline("0");
				 * outBoundDAO.setStrHospitalCode(vo.getStrHospitalCode());
				 * 
				 * outBoundDAO.insert(dao);
				 */
				// end --> out bount entry code added by Balasubramaniam M (
				// 10-Nov-2009 )
			}

			// Refund Un Processed Services

			if (vo.getNChkSize() > 0) {

				for (int j = 0; j < vo.getNChkSize(); j++) {

					if (vo.getChkBox1()[j].equals("1")) {

						vo.setDis_amt(vo.getStrDiscAmt()[j]);
						vo.setSer_tax_amt(vo.getStrUnProcessServiceTaxAmt()[j]);

						vo.setActual_amt(vo.getStrActualTariffNetAmt()[j]);
						vo.setTrf_amt(vo.getStrUnProcNetAmt()[j]);

						dblNetActualAmt = dblNetActualAmt
								+ (Double.parseDouble(vo
										.getStrActualTariffNetAmt()[j]));
						dblNetSTaxAmt = dblNetSTaxAmt
								+ (Double.parseDouble(vo
										.getStrUnProcessServiceTaxAmt()[j]));
						dblNetDisAmt = dblNetDisAmt
								+ (Double.parseDouble(vo.getStrDiscAmt()[j]));
						dblNetTrfAmt = dblNetTrfAmt
								+ (Double
										.parseDouble(vo.getStrUnProcNetAmt()[j]));

						DmlBillApprovalService2(vo, dao, j);

					}
				}
			}

			pActDao.setStrPatAccNo(vo.getStrPatAcctNo());

			pActDao.setStrNetActualAmt(Double.toString(dblNetActualAmt));
			pActDao.setStrSerTaxNetAmt(Double.toString(dblNetSTaxAmt));
			pActDao.setStrTariffNetAmt(Double.toString(dblNetTrfAmt));
			pActDao.setStrDiscountNetAmt(Double.toString(dblNetDisAmt)); // Check
			// It
			pActDao.setStrAppBy(vo.getStrApprovalBy()); // Check It
			pActDao.setStrHospitalCode(vo.getStrHospitalCode());
			pActDao.setStrAppId(vo.getStrApprovalId());
			pActDao.setIsBillFinal("91");
            pActDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			pActDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
			pActDao.update(dao); // Successfully Updated

			pKeyDao.setStrKeyname("REQNO");
			pKeyDao.setStrBlockkey("1");
			pKeyDao.setStrHospCode(vo.getStrHospitalCode());
			// Procedure Execute
			try {
				pKeyDao.select(dao);

				vo.setStrReqNo(pKeyDao.getStrPrimrayKeyValue());
				reqFlag2 = 1;
			} catch (Exception e) {
				throw new Exception("pKeyDao.select(REQNO - 1) --> "
						+ e.getMessage());
			}

			DmlBillApprovalOnLineReq(vo, dao);

			// ///for poor free ///////
			if (vo.getStrPrimaryCategory().equals(
					new BillConfigUtil(vo.getStrHospitalCode())
							.getIpdFreeCategory())) {
				pKeyDao.setStrKeyname("REQNO");
				pKeyDao.setStrBlockkey("1");
				pKeyDao.setStrHospCode(vo.getStrHospitalCode());
				// Procedure Execute
				try {
					pKeyDao.select(dao);

					vo.setStrReqNo(pKeyDao.getStrPrimrayKeyValue());

				} catch (Exception e) {
					throw new Exception("pKeyDao.select(REQNO - 1) --> "
							+ e.getMessage());
				}

				DmlAccountPoorFree(vo, dao);
			}

			// ////////////////////////////////

			synchronized (dao) {

				dao.fire(); // Here we Execute in Batch

			}

		} catch (Exception e) {
			e.printStackTrace();

			vo.setStrErrPrimKeyLog(e.getMessage());

			/**
			 * ************* Here We Call The PrimaryKeyLogProc Procedure To
			 * RollBack The Primary Key Table ********
			 */
			if (appFlag1 == 1 && appFlag2 == 1) {
				try {
					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrStartkey(vo.getStrApprovalId());
					pKeyLogDao.setStrBlockkey("2");
					pKeyLogDao.setStrHospCode(vo.getStrHospitalCode());
					pKeyLogDao.setStrError(vo.getStrErrPrimKeyLog());
					pKeyLogDao.setStrSeatid(vo.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);
				} catch (Exception e1) {

					e1.printStackTrace();

					vo
							.setStrMsgString("IpdBillManagementTransDAO.InsertBillApproval().pKeyLogDao(APPROVAL with AppFlag1 & AppFlag2)--> "
									+ e1.getMessage());
					vo.setStrMsgType("1");

				}
			}
			if (appFlag2 == 1) {
				try {
					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrHospCode(vo.getStrHospitalCode());
					pKeyLogDao.setStrStartkey(vo.getStrApprovalId());
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(vo.getStrErrPrimKeyLog());
					pKeyLogDao.setStrSeatid(vo.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);
				} catch (Exception e2) {

					e2.printStackTrace();

					vo
							.setStrMsgString("IpdBillManagementTransDAO.InsertBillApproval().pKeyLogDao(APPROVAL with AppFlag2)--> "
									+ e2.getMessage());
					vo.setStrMsgType("1");

				}
			}
			if (reqFlag == 1) {
				try {
					pKeyLogDao.setStrKeyname("REQNO");
					pKeyLogDao.setStrStartkey(vo.getStrReqNo());
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrHospCode(vo.getStrHospitalCode());
					pKeyLogDao.setStrError(vo.getStrErrPrimKeyLog());
					pKeyLogDao.setStrSeatid(vo.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);
				} catch (Exception e3) {

					e3.printStackTrace();

					vo
							.setStrMsgString("IpdBillManagementTransDAO.InsertBillApproval().pKeyLogDao(REQNO)--> "
									+ e3.getMessage());
					vo.setStrMsgType("1");

				}
			}

			if (reqFlag2 == 1) {
				try {
					pKeyLogDao.setStrKeyname("REQNO");
					pKeyLogDao.setStrStartkey(vo.getStrReqNo());
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrHospCode(vo.getStrHospitalCode());
					pKeyLogDao.setStrError(vo.getStrErrPrimKeyLog());
					pKeyLogDao.setStrSeatid(vo.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);
				} catch (Exception e3) {

					e3.printStackTrace();

					vo
							.setStrMsgString("IpdBillManagementTransDAO.InsertBillApproval().pKeyLogDao(REQNO)--> "
									+ e3.getMessage());
					vo.setStrMsgType("1");

				}
			}

			new HisException("billing", "IpdBillMangementTrans",
					"IpdBillManagementTransDAO.InsertBillApproval() --> "
							+ e.getMessage());

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			if (pActDao != null || pKeyLogDao != null) {
				pKeyDao = null;
				pKeyLogDao = null;
				pActDao = null;
				// pActServDao = null;
				// pActPckDao = null;
				// pInBoundDao = null;
				// pBillReqDao = null;
				// pBillReqTarrfDao = null;
				pApprovalDao = null;
			}
		}

	}

	private static void DmlAccountPoorFree(IpdBillManagementTransVO vo,
			HisDAO dao) {
		String proc_name = "";
		proc_name = "{call pkg_bill_dml.dml_account_poor_service(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nprocIndex = 0;
		try {

			
			
			nprocIndex = dao.setProcedure(proc_name);

			dao.setProcInValue(nprocIndex, "modval", "1", 1); // 1

			dao.setProcInValue(nprocIndex, "hosp_code",
					vo.getStrHospitalCode(), 2); // 2

			dao.setProcInValue(nprocIndex, "reqNo", vo.getStrReqNo(), 3); // 2

			dao.setProcInValue(nprocIndex, "patAccNo", vo.getStrAccountNo(), 4); // 2

			dao.setProcInValue(nprocIndex, "puk", vo.getStrCrNo(), 5); // 3

			dao.setProcInValue(nprocIndex, "catCode", vo
					.getStrPrimaryCategory(),6); // 4

			dao.setProcInValue(nprocIndex, "deptCode", "1", 7); // 5

			dao.setProcInValue(nprocIndex, "chargeTypeId", "2", 8); // 6

			dao.setProcInValue(nprocIndex, "wardId", "1", 9); // 7

			dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId(), 10); // 8

			dao.setProcInValue(nprocIndex, "ipdChargeTypeId", "1", 11); // 9

            dao.setProcInValue(nprocIndex, "ip", GetNetworkAddress.GetAddress("ip"), 12);
			
			dao.setProcInValue(nprocIndex, "mac", GetNetworkAddress.GetAddress("mac"), 13);

			dao.setProcOutValue(nprocIndex, "err", 1, 14); // 1 for return
															// string
			// //19

			// execute procedure

			dao.execute(nprocIndex, 1);

		} catch (Exception e) {
			e.printStackTrace();

			vo
					.setStrMsgString("IpdBillManagementTransDao.DmlAccountPoorFree() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}

	}

	/*------------------------Insert Method for Bill Approval Rejection--------------------------*/
	/**
	 * InsertBillReject(vo) -- > This Method is Used in case of rejection of
	 * Bill Approval Transaction Here we use two Table HBLT_PATACCOUNT_DTL
	 * ,HBLT_PATACCOUNT_STATUS_LOG_DTL
	 * 
	 * 
	 */

	public static void InsertBillReject(IpdBillManagementTransVO vo) {
		// Decleration
		HisDAO dao = null;

		// int appFlag1 = 0, appFlag2 = 0, reqFlag = 0, reqFlag2 = 0;

		// Createing Object for Table Specific DAO

		PatientAccountDAO pActDao = new PatientAccountDAO();
		PatientAccountStatusDAO pActStatusDAO = new PatientAccountStatusDAO();

		try {
			dao = new HisDAO("billing",
					"transactions.DAObilling.InsertBillReject()");

			pActDao.setStrPatAccNo(vo.getStrPatAcctNo());
			// pActDao.setStrAppBy(vo.getStrApprovalBy()); // Check It
			pActDao.setStrHospitalCode(vo.getStrHospitalCode());
			// pActDao.setStrAppId(vo.getStrApprovalId());
			pActDao.setIsBillFinal("90");
            pActDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
			pActDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
			pActDao.update5(dao); // Successfully Updated

			pActStatusDAO.setStrAppBy(vo.getStrApprovalBy());
			pActStatusDAO.setStrPatAccountNo(vo.getStrPatAcctNo());
			pActStatusDAO.setStrOldStatus("91");
			pActStatusDAO.setStrNewStatus("90");
			pActStatusDAO.setStrRemarks(vo.getStrRemarks());
			pActStatusDAO.setStrHospitalCode(vo.getStrHospitalCode());
			pActStatusDAO.setStrSeatId(vo.getStrSeatId());
			pActStatusDAO.insert(dao);
			synchronized (dao) {

				dao.fire(); // Here we Execute in Batch

			}

		} catch (Exception e) {
			e.printStackTrace();

			vo.setStrErrPrimKeyLog(e.getMessage());

			/**
			 * ************* Here We Call The PrimaryKeyLogProc Procedure To
			 * RollBack The Primary Key Table ********
			 */

			new HisException("billing", "IpdBillMangementTrans",
					"IpdBillManagementTransDAO.InsertBillReject() --> "
							+ e.getMessage());

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			if (pActDao != null) {

				pActDao = null;

			}
		}

	}

	// ///////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * ************** This Method is Used for Insert Logic in Bill Approval-II
	 * **************
	 */
	public static void DmlBillApprovalService(IpdBillManagementTransVO vo,
			HisDAO dao, int i) {
		String proc_name = "";
		proc_name = "{call pkg_bill_dml.dml_billapproval_service(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nprocIndex = 0;
		try {

			nprocIndex = dao.setProcedure(proc_name);

			dao.setProcInValue(nprocIndex, "modval", "1", 1); // 1
			dao.setProcInValue(nprocIndex, "hosp_code",
					vo.getStrHospitalCode(), 2); // 2
			dao.setProcInValue(nprocIndex, "reqNo", vo.getStrReqNo(), 3); // 3
			// System.out.println("reqNO-->"+vo.getStrReqNo());
			dao.setProcInValue(nprocIndex, "trfRecNo", "1", 4); // 4
			// HBLNUM_REF_RECIEPT_NO
			// System.out.println("In
			// DmlBillApprovalService-trfRecNo---1->>"+vo.getStrTariffId()[i]);
			dao.setProcInValue(nprocIndex, "patAccNo", vo.getStrPatAcctNo(), 5); // 5
			// System.out.println("patAccNo-->>>"+vo.getStrPatAcctNo());
			dao.setProcInValue(nprocIndex, "puk", vo.getStrPukNo(), 6); // 6
			// System.out.println("puk--->>>"+vo.getStrPukNo());
			dao.setProcInValue(nprocIndex, "catCode",
					vo.getStrPatientCatCode(), 7); // 7
			// System.out.println("catCode-->>>"+vo.getStrPatientCatCode());
			dao.setProcInValue(nprocIndex, "deptCode", vo.getStrDeptId(), 8); // 8
			// System.out.println("deptCode-->>>"+vo.getStrDeptId());
			dao.setProcInValue(nprocIndex, "grpId", vo.getStrGrpId()[i], 9); // 9
			// System.out.println("grpId--->>>"+vo.getStrGrpId()[i]);
			dao.setProcInValue(nprocIndex, "trfId", vo.getStrTariffId()[i], 10); // 10
			// System.out.println("trfId--1-->>>"+vo.getStrTariffId()[i]);
			dao.setProcInValue(nprocIndex, "reqQty", vo.getStrBillQty()[i], 11); // 11
			// System.out.println("In
			// DmlBillApprovalService-reqQty--1-->>"+vo.getStrBillQty()[i]);

			dao.setProcInValue(nprocIndex, "reqQtyUnitId",
					vo.getStrUnitIDVal()[i], 12); // 12

			// System.out.println("reqQtyUnitId--1->>>>"+vo.getStrUnitIDVal()[i]);

			dao
					.setProcInValue(nprocIndex, "rate",
							vo.getStrTariffRate()[i], 13); // 13

			// System.out.println("rate--1--->>>"+vo.getStrTariffRate()[i]);

			dao.setProcInValue(nprocIndex, "actRate", vo
					.getStrTariffActualCost()[i], 14); // Check
			// //14

			// System.out.println("actRate--1--->>>>"+vo.getStrTariffRate()[i]);

			dao.setProcInValue(nprocIndex, "rateUnitId",
					vo.getStrUnitIDVal()[i], 15); // 15

			// System.out.println("rateUnitId---1-->>>"+vo.getStrUnitIDVal()[i]);

			dao.setProcInValue(nprocIndex, "serTax", vo.getStrServTax()[i], 16); // 16

			// System.out.println("serTax---1--->>>>"+vo.getStrServTax()[i]);

			dao.setProcInValue(nprocIndex, "serTaxAmt", vo
					.getStrOfflineTariffServiceTaxAmtVal()[i], 17); // 17 Hard
																	// Coded

			dao.setProcInValue(nprocIndex, "trfNetAmt",
					vo.getStrTariffNetAmt()[i], 18); // 18

			// System.out.println("trfNetAmt--1-->>>>"+vo.getStrTariffNetAmt()[i]);

			dao.setProcInValue(nprocIndex, "appId", vo.getStrApprovalId(), 19); // 19

			// System.out.println("appId--1-->>>"+vo.getStrApprovalId());

			dao.setProcInValue(nprocIndex, "disUnit", vo
					.getStrOffLineTariffDiscountUnit(), 20); // 20

			// System.out.println("disUnit--1-->>>"+vo.getStrOffLineTariffDiscountUnit());

			dao.setProcInValue(nprocIndex, "disAmt", vo
					.getStrTariffDiscountAmtConfgDtlBillApproval(), 21); // 21
																			// //
			// Check
			// It

			// System.out.println("disAmt---1->>>"+vo.getStrTariffDiscountAmtConfgDtlBillApproval());

			dao.setProcInValue(nprocIndex, "disType", vo
					.getStrOffLineTariffDiscountType(), 22); // 22

			// System.out.println("disType--1->>>>"+vo.getStrOffLineTariffDiscountType());

			dao.setProcInValue(nprocIndex, "chargeTypeId", vo
					.getStrChargeTypeID(), 23); // 23

			// System.out.println("chargeTypeId--1->>>>"+vo.getStrChargeTypeID());

			dao.setProcInValue(nprocIndex, "wardId", vo.getStrWardCode(), 24); // 24

			// System.out.println("wardId--1->>>>"+ vo.getStrWardCode());

			dao
					.setProcInValue(nprocIndex, "serviceId", vo
							.getStrServiceId()[i]); // 25

			dao.setProcInValue(nprocIndex, "gblTrfId",
					vo.getStrGlblTariffId()[i], 26); // 26

			dao.setProcInValue(nprocIndex, "isRefund",
					vo.getStrIsRefundable()[i], 27); // 27

			// System.out.println("isRefund--1->>>>"+
			// vo.getStrIsRefundable()[i]);

			dao.setProcInValue(nprocIndex, "isPackage", vo.getStrIsPackg()[i],
					28); // 28

			// System.out.println("isPackage--1->>>>"+ vo.getStrIsPackg()[i]);

			dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId(), 30); // 30

			dao.setProcInValue(nprocIndex, "admNo", "", 29); // 29

			// System.out.println("Seat ID-1->>>>>"+vo.getStrSeatId());

            dao.setProcInValue(nprocIndex, "ip", GetNetworkAddress.GetAddress("ip"), 31);
			
			dao.setProcInValue(nprocIndex, "mac", GetNetworkAddress.GetAddress("mac"), 32);

			dao.setProcOutValue(nprocIndex, "err", 1, 33); // 1 for return
															// string
			// //30

			// execute procedure

			dao.execute(nprocIndex, 1);

		} catch (Exception e) {

			e.printStackTrace();

			// System.out.println("Error Message is
			// DmlBillApprovalService()-->>>>"+e.getMessage());
			vo
					.setStrMsgString("RefundApprovalTransDAO.DmlBillApprovalService(Mode 1) --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}

	}

	/**
	 * *****************This Method is Used for Insert Logic in Bill
	 * Approval**************
	 */
	public static void DmlBillApprovalOnLineReq(IpdBillManagementTransVO vo,HisDAO dao) 
	{
		String proc_name = "";
		proc_name = "{call pkg_bill_dml.dml_billapproval_online_req(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nprocIndex = 0;
		try 
		{
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "modval", "1", 1);
			dao.setProcInValue(nprocIndex, "hosp_code",vo.getStrHospitalCode(), 2);
			dao.setProcInValue(nprocIndex, "defUnit", vo.getStrDefaultUnit(), 3);
			dao.setProcInValue(nprocIndex, "deptCode", vo.getStrDeptId(), 4);
			dao.setProcInValue(nprocIndex, "chargeTypeId", vo.getStrChargeTypeID(), 5);
			dao.setProcInValue(nprocIndex, "catCode",vo.getStrPatientCatCode(), 6);
			dao.setProcInValue(nprocIndex, "episodeCode", vo.getStrEpisodeNo(),7);
			dao.setProcInValue(nprocIndex, "patAccNo", vo.getStrPatAcctNo(), 8);
			dao.setProcInValue(nprocIndex, "puk", vo.getStrPukNo(), 9);
			dao.setProcInValue(nprocIndex, "admNo", vo.getStrAddmissionNo(), 10);
			dao.setProcInValue(nprocIndex, "appId", vo.getStrApprovalId(), 11);
			dao.setProcInValue(nprocIndex, "appBy", vo.getStrApprovalBy(), 12);
			dao.setProcInValue(nprocIndex, "serviceTax", vo.getStrServiceTax(),13);
			dao.setProcInValue(nprocIndex, "netAmt", vo.getTotalRecAmtDivId1(),14);
			dao.setProcInValue(nprocIndex, "appId_discount", vo.getStrTotalDiscountApprovalId(), 15);
			dao.setProcInValue(nprocIndex, "disType", vo.getStrOffLineTariffDiscountType(), 16);
			dao.setProcInValue(nprocIndex, "disUnit", vo.getStrOffLineTariffDiscountUnit(), 17);
			dao.setProcInValue(nprocIndex, "wardId", vo.getStrWardCode(), 18);
			dao.setProcInValue(nprocIndex, "grpId", vo.getStrGroupId(), 19);
			dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId(), 20);
			dao.setProcInValue(nprocIndex, "v_req_no", vo.getStrReqNo(), 21);			
			dao.setProcOutValue(nprocIndex, "err", 1, 22);
			dao.execute(nprocIndex, 1);

		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			vo.setStrMsgString("RefundApprovalTransDAO.DmlBillApprovalService() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}

	}

	/** **** (In Case of Reverse) ******** */
	public static void DmlBillApprovalService2(IpdBillManagementTransVO vo,
			HisDAO dao, int i) {
		String proc_name = "";
		proc_name = "{call pkg_bill_dml.dml_billapproval_service(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nprocIndex = 0;
		try {

			nprocIndex = dao.setProcedure(proc_name);

			dao.setProcInValue(nprocIndex, "modval", "2", 1); // 1

			dao.setProcInValue(nprocIndex, "hosp_code",
					vo.getStrHospitalCode(), 2); // 2

			dao
					.setProcInValue(nprocIndex, "reqNo",
							vo.getStrUnProReqNo()[i], 3); // 3

			// System.out.println("In
			// DmlBillApprovalService-reqNo---2->>"+vo.getStrReqNo());

			dao.setProcInValue(nprocIndex, "trfRecNo", vo
					.getStrUnProHbReciptNo()[i], 4); // 4 ***

			// System.out.println("In
			// DmlBillApprovalService-trfRecNo---2->>"+vo.getStrTariffId()[i]);

			dao.setProcInValue(nprocIndex, "patAccNo", vo.getStrPatAcctNo(), 5); // 5

			dao.setProcInValue(nprocIndex, "puk", vo.getStrPukNo(), 6); // 6

			dao.setProcInValue(nprocIndex, "catCode",
					vo.getStrPatientCatCode(), 7); // 7

			dao.setProcInValue(nprocIndex, "deptCode",
					vo.getStrUnProDeptCode()[i], 8); // 8

			dao.setProcInValue(nprocIndex, "grpId",
					vo.getStrUnProcGroupId()[i], 9); // 9

			dao.setProcInValue(nprocIndex, "trfId",
					vo.getStrUnProTariffId()[i], 10); // 10

			dao.setProcInValue(nprocIndex, "reqQty",
					vo.getStrUnProcRefQty()[i], 11); // 11
			// ***Check
			// It

			// System.out.println("In
			// DmlBillApprovalService-reqQty--2-->>"+vo.getStrBillQty()[i]);

			dao.setProcInValue(nprocIndex, "reqQtyUnitId", vo
					.getStrUnProQtyUnitId()[i], 12); // 12

			dao.setProcInValue(nprocIndex, "rate",
					vo.getStrUnProTariffRate()[i], 13); // 13

			dao.setProcInValue(nprocIndex, "actRate", vo
					.getStrUnProcTariffActualRate()[i], 14); // Check
			// //14

			dao.setProcInValue(nprocIndex, "rateUnitId", vo
					.getStrUnProRateUnitCode()[i], 15); // 15

			dao.setProcInValue(nprocIndex, "serTax",
					vo.getStrUnProcSercTax()[i], 16); // 16

			System.out.println(" getStrUnProcessServiceTaxAmt "
					+ vo.getStrUnProcessServiceTaxAmt()[i]);

			dao.setProcInValue(nprocIndex, "serTaxAmt", vo
					.getStrUnProcessServiceTaxAmt()[i], 17); // 17 Hard Coded

			dao.setProcInValue(nprocIndex, "trfNetAmt",
					vo.getStrUnProcNetAmt()[i], 18); // 18

			dao.setProcInValue(nprocIndex, "appId",
					vo.getStrUnProcHbAppId()[i], 19); // 19

			dao.setProcInValue(nprocIndex, "disUnit", vo
					.getStrUnProHblDisUnit()[i], 20); // 20

			dao.setProcInValue(nprocIndex, "disAmt", vo.getStrDiscAmt()[i], 21); // 21
			// //
			// Check
			// It

			dao.setProcInValue(nprocIndex, "disType", vo
					.getStrUnProHblDisType()[i], 22); // 22

			dao.setProcInValue(nprocIndex, "chargeTypeId", vo
					.getStrChargeTypeID(), 23); // 23

			dao.setProcInValue(nprocIndex, "wardId",
					vo.getStrUnProWardCode()[i], 24); // 24

			dao.setProcInValue(nprocIndex, "serviceId", vo
					.getStrUnProServiceId()[i], 25); // 25

			dao.setProcInValue(nprocIndex, "gblTrfId", vo
					.getStrUnProGstrTariffId()[i], 26); // 26

			dao.setProcInValue(nprocIndex, "isRefund", "1", 27); // 27

			dao.setProcInValue(nprocIndex, "isPackage", vo
					.getStrUnProcHbIsPkg()[i], 28); // 28

			dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId(), 30); // 29

			dao
					.setProcInValue(nprocIndex, "admNo", vo
							.getStrAddmissionNo(), 29); // 29

			// System.out.println("Seat ID-2->>>>>"+vo.getStrSeatId());

            dao.setProcInValue(nprocIndex, "ip", GetNetworkAddress.GetAddress("ip"), 31);
			
			dao.setProcInValue(nprocIndex, "mac", GetNetworkAddress.GetAddress("mac"), 32);

			dao.setProcOutValue(nprocIndex, "err", 1, 33); // 1 for return
															// string
			// //30

			// execute procedure

			dao.execute(nprocIndex, 1);

		} catch (Exception e) {

			e.printStackTrace();

			System.out
					.println("Error Message is DmlBillApprovalService(With Mode 2)-->>>>"
							+ e.getMessage());
			vo
					.setStrMsgString("RefundApprovalTransDAO.DmlBillApprovalService() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}

	}

	// ///////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Insert Method for PartPayment When Update CheckBox is Unchecked Here We
	 * First Get The Primary Key By Procedure And After That Performing the
	 * Insertion By Executing Three Procedure into Batch Table in use
	 * SBLT_PRIMARYKEY_DTL , SBLT_INBOUND_DTL HBLT_BILLREQ_DTL
	 * HBLT_BILLREQ_TARIFF_DTL , SBLT_PRIMARYKEY_LOG_DTL
	 */
	public static boolean InsertDataPartPayment(IpdBillManagementTransVO vo) {
		HisDAO dao = null;
		boolean retVal = false;
		boolean retVal0 = false;
		boolean retVal1 = false;
		boolean retVal2 = false;
		try {
			dao = new HisDAO("billing",
					"transactions.DAObilling.InsertDataPartPayment()");

			retVal0 = getPrimaryKeyProc(vo, dao);

			if (retVal0) {
				retVal1 = insertSbltInbound(vo, dao);

				if (retVal1) {
					retVal1 = insertHbltBillReq(vo, dao);
					if (retVal1) {
						retVal1 = insertHbltBillReqTrffDtl(vo, dao);
					}
				}
			}
			if (retVal1) {
				synchronized (dao) {
					dao.fire(); // Here we Execute in Batch
					retVal = true;
				}
			}

		} catch (Exception e) {
			vo.setStrErrPrimKeyLog(e.getMessage());
			/*
			 * Here We Call The PrimaryKeyLogProc Procedure To RollBack The
			 * Primary Key Table
			 */
			retVal2 = PrimaryKeyLogProc(vo.getStrReqNo(), "REQNO", vo, dao);
			if (retVal2) {
				vo
						.setStrMsgString("IpdBillManagementTransDAO.InsertDataPartPayment() --> "
								+ e.getMessage());
				vo.setStrMsgType("1");
			}
			new HisException("billing", "IpdBillMangementTrans",
					"IpdBillManagementTransDAO.InsertDataPartPayment() --> "
							+ e.getMessage());
			retVal = false;
		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}
		return retVal;

	}

	/**
	 * Insert Method for PartPayment When Update CheckBox is Checked Table in
	 * use SBLT_PRIMARYKEY_DTL , SBLT_INBOUND_DTL HBLT_BILLREQ_DTL
	 * HBLT_BILLREQ_TARIFF_DTL , SBLT_PRIMARYKEY_LOG_DTL , HBLT_APPROVAL_DTL
	 */
	public static boolean InsertDataPartPaymentWithApproval(
			IpdBillManagementTransVO vo) {
		HisDAO dao = null;
		boolean retVal = false;
		boolean retVal0 = false;
		boolean retVal1 = false;
		boolean retVal2 = false;
		// boolean retVal3 = false;
		// boolean retVal4 = false;
		PrimaryKeyLogDAO pKeyLogDao = new PrimaryKeyLogDAO();

		try {
			dao = new HisDAO("billing",
					"transactions.DAObilling.InsertDataPartPaymentWithApproval()");

			retVal0 = getPrimaryKeyProcApprovalDtl(vo, dao);
			retVal1 = getPrimaryKeyProc(vo, dao);
			if (retVal0 && retVal1) {

				retVal2 = insertSbltInbound(vo, dao);
				if (retVal2) {
					retVal2 = insertHbltBillReq(vo, dao);
					if (retVal2) {
						retVal2 = insertHbltBillReqTrffDtl(vo, dao);
						if (retVal2) {
							retVal2 = insertHbltApprovalDtl(vo, dao);
						}
					}
				}
			}
			if (retVal2) {
				synchronized (dao) {
					dao.fire();
					retVal = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrErrPrimKeyLog(e.getMessage());
			try {

				pKeyLogDao.setStrKeyname("APPROVAL");
				pKeyLogDao.setStrStartkey(vo.getStrApprovalId());
				pKeyLogDao.setStrBlockkey("1");
				pKeyLogDao.setStrHospCode(vo.getStrHospitalCode());
				pKeyLogDao.setStrError(vo.getStrErrPrimKeyLog());
				pKeyLogDao.setStrSeatid(vo.getStrSeatId());
				// Calling Insert Method of Primary Key Log
				pKeyLogDao.insert(dao);
			} catch (Exception e2) {
				vo
						.setStrMsgString("IpdBillManagementTransDAO.InsertDataPartPaymentWithApproval()--> "
								+ e2.getMessage());
				vo.setStrMsgType("1");
			}
			try {

				pKeyLogDao.setStrKeyname("REQNO");
				pKeyLogDao.setStrStartkey(vo.getStrReqNo());
				pKeyLogDao.setStrBlockkey("1");
				pKeyLogDao.setStrHospCode(vo.getStrHospitalCode());
				pKeyLogDao.setStrError(vo.getStrErrPrimKeyLog());
				pKeyLogDao.setStrSeatid(vo.getStrSeatId());
				// Calling Insert Method of Primary Key Log
				pKeyLogDao.insert(dao);
			} catch (Exception e3) {
				vo
						.setStrMsgString("IpdBillManagementTransDAO.InsertDataPartPaymentWithApproval()--> "
								+ e3.getMessage());
				vo.setStrMsgType("1");

			}

			/*
			 * retVal3 =
			 * PrimaryKeyLogProc(vo.getStrApprovalId(),"APPROVAL",vo,dao);
			 * retVal4 = PrimaryKeyLogProc(vo.getStrReqNo(),"REQNO",vo,dao);
			 * if(retVal3) { if(retVal4) { retVal = false;
			 * vo.setStrMsgString("IpdBillManagementTransDAO.InsertDataPartPaymentWithApproval()--> "+
			 * e.getMessage()); vo.setStrMsgType("1"); } } else { retVal =
			 * false;
			 * vo.setStrMsgString("IpdBillManagementTransDAO.InsertDataPartPaymentWithApproval()-->"+e.getMessage());
			 * vo.setStrMsgType("1"); }
			 */

		}

		finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return retVal;
	}

	/**
	 * This Method is Used to perform Insert Oepration for Update Account Status
	 * Table in use HBLT_PATACCOUNT_DTL , HBLT_PATACCOUNT_STAT_LOG
	 * 
	 */
	public static boolean InsertUpdateAcctStatus(IpdBillManagementTransVO vo) {
		HisDAO dao = null;
		boolean retVal = false;
		boolean retVal1 = false;

		try {
			dao = new HisDAO("billing",
					"transactions.DAObilling.InsertUpdateAcctStatus()");
			if (vo.getStrChk() != null) {
				retVal1 = true;
				retVal1 = UpdateHbltPatAcctDtl(vo, dao); /*
															 * Here We Call The
															 * Table Specific
															 * Insert Method By
															 * Passing Vo & dao
															 * Parameter
															 */

				if (retVal1) {
					retVal1 = insertHbltPatAcctStatLogDtl(vo, dao);

				}
			}
			if (retVal1) {
				synchronized (dao) {
					dao.fire();
					retVal = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo
					.setStrMsgString("IpdBillManagementTransDAO.InsertUpdateAcctStatus()-->"
							+ e.getMessage());
			vo.setStrMsgType("1");
		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}
		return retVal;

	}

	/**
	 * Here We Get The Primary For Approval Dtl.
	 */
	public static boolean getPrimaryKeyProcApprovalDtl(
			IpdBillManagementTransVO vo, HisDAO dao) {

		String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_VIEW.proc_sblt_primarykey_dtl(?,?,?,?,?,?)}";

		boolean retVal = false;

		int procIndex1 = 0;
		// HisDAO dao = null;

		String strerr = "";

		WebRowSet ws = null;
		try {

			// dao = new HisDAO("billing",
			// "transactions.DAObilling.PrimaryKeyProc()");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "keyname", "APPROVAL", 1);

			dao.setProcInValue(procIndex1, "blockkey", "1", 2);

			dao.setProcInValue(procIndex1, "commitFlag", "1", 3);

			dao.setProcInValue(procIndex1, "hosp_code",
					vo.getStrHospitalCode(), 4);

			dao.setProcOutValue(procIndex1, "ERR", 1, 6); // 1 for string
															// return

			dao.setProcOutValue(procIndex1, "RESULTSET", 2, 5); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(procIndex1);

			// get value

			strerr = dao.getString(procIndex1, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(procIndex1, "RESULTSET");
				while (ws.next()) {
					vo.setStrApprovalId(ws.getString(1));

				}
				retVal = true;
				vo.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		} catch (Exception e) {
			new HisException("billing", "IpdBillManagementTransDAO",
					"IpdBillManagementTransDAO.getPrimaryKeyProcApprovalDtl() --> "
							+ e.getMessage());
			vo
					.setStrMsgString("IpdBillManagementTransDAO.getPrimaryKeyProcApprovalDtl()--> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}
		return retVal;

	}

	/**
	 * Here We Get The Primary Key By Passing The Key Name
	 */
	public static boolean getPrimaryKeyProc(IpdBillManagementTransVO vo,
			HisDAO dao) {

		String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_VIEW.proc_sblt_primarykey_dtl(?,?,?,?,?,?)}";

		// HisDAO dao = null;

		int procIndex1 = 0;

		String strerr = "";

		WebRowSet ws = null;
		boolean retVal = false;

		try {

			// dao = new
			// HisDAO("billing","transactions.DAObilling.PrimaryKeyProc()");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "keyname", "REQNO", 1);

			dao.setProcInValue(procIndex1, "blockkey", "1", 2);

			dao.setProcInValue(procIndex1, "commitFlag", "1", 3);

			dao.setProcInValue(procIndex1, "hosp_code",
					vo.getStrHospitalCode(), 4);

			dao.setProcOutValue(procIndex1, "ERR", 1, 6); // 1 for string
															// return

			dao.setProcOutValue(procIndex1, "RESULTSET", 2, 5); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(procIndex1);

			// get value

			strerr = dao.getString(procIndex1, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(procIndex1, "RESULTSET");
				while (ws.next()) {
					vo.setStrReqNo(ws.getString(1));

				}
				retVal = true;
				vo.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		} catch (Exception e) {
			new HisException("billing", "IpdBillManagementTransDAO",
					"getPrimaryKeyProc() --> " + e.getMessage());
			vo
					.setStrMsgString("IpdBillManagementTransDAO.getPrimaryKeyProc() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}
		return retVal;
	}

	/**
	 * This Procedure Basically Used When Transaction Failled In That Situation
	 * This Procedure Roll Back The Primary Key Here We Passing Two Parameter
	 * One is KeyName eg. "REQNO" StartKey eg. 100078000001
	 */
	public static boolean PrimaryKeyLogProc(String strStratKey,
			String strKeyName, IpdBillManagementTransVO vo, HisDAO dao) {

		String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_DML.dml_sblt_primarykey_log_dtl(?,?,?,?,?,?,?,?)}";

		// HisDAO dao = null;

		int procIndex1 = 0;

		String strerr = "";

		// WebRowSet ws = null;
		boolean retVal = false;

		try {

			// dao = new
			// HisDAO("billing","transactions.DAObilling.PrimaryKeyLogProc()");

			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "keyname", strKeyName, 1);

			dao.setProcInValue(procIndex1, "startkey", strStratKey, 2);

			dao.setProcInValue(procIndex1, "blockkey", "1", 3);

			dao.setProcInValue(procIndex1, "hosp_code",
					vo.getStrHospitalCode(), 7);

			dao.setProcInValue(procIndex1, "procedureName", "", 6);
			dao
					.setProcInValue(procIndex1, "error", vo
							.getStrErrPrimKeyLog(), 4); // 

			dao.setProcInValue(procIndex1, "seatid", vo.getStrSeatId(), 5);

			dao.setProcOutValue(procIndex1, "ERR", 1, 8); // 1 for string
															// return
			// value

			// execute procedure

			dao.executeProcedureByPosition(procIndex1);

			// get value

			strerr = dao.getString(procIndex1, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				retVal = true;
				vo.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		} catch (Exception e) {
			new HisException("", "",
					"IpdBillManagementTransDAO.PrimaryKeyLogProc() --> "
							+ e.getMessage());
			vo
					.setStrMsgString("IpdBillManagementTransDAO.PrimaryKeyLogProc() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
			retVal = false;
		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}
		return retVal;
	}

	/**
	 * Insert Method for Update Account Status Here we insert Value in
	 * HBLT_PAT_ACCT_LOG DTL by passing two parameter vo & dao these method
	 * execute in Batch
	 */
	public static boolean insertHbltPatAcctStatLogDtl(
			IpdBillManagementTransVO vo, HisDAO dao) {
		boolean retVal = false;
		String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_DML.dml_hblt_pataccount_stat_log(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		int procIndex1 = 0;

		// String strerr = "";

		// WebRowSet ws = null;

		try {

			String strTemp[] = vo.getStrChk().replace('@', '#').split("#");

			procIndex1 = dao.setProcedure(proc_name1);

			// System.out.println("No of Insert-->"+hpalvo.getNRowInserted());
			/*
			 * System.out.println("Pat Acct No->"+strTemp[0]);
			 * System.out.println("Mode Val"+vo.getStrmodval());
			 * System.out.println("Old Status"+vo.getStrOldStatus());
			 * System.out.println("New "+vo.getStrNewStatus());
			 * System.out.println("App By-->"+vo.getStrApprBy());
			 * System.out.println("Appp Date-->"+vo.getStrApprDate());
			 * System.out.println("Remarks-->"+vo.getStrRemarks());
			 * System.out.println("Seat ID-->"+vo.getStrSeatId());
			 * System.out.println("Entry date-->"+vo.getStrEntryDate());
			 */
			// set value
			dao.setProcInValue(procIndex1, "modval", "1", 1);

			dao.setProcInValue(procIndex1, "hblnum_pataccount_no", strTemp[0],
					2);

			dao.setProcInValue(procIndex1, "hblnum_status_slno", "0", 3);

			dao.setProcInValue(procIndex1, "hblnum_old_status", vo
					.getStrPAcctStatus(), 4);

			dao.setProcInValue(procIndex1, "hblnum_new_status", vo
					.getStrNewAcctStatus(), 5);

			dao.setProcInValue(procIndex1, "hblstr_approved_by", vo
					.getStrApprovalBy(), 6);
			System.out.println("hblt_pataccount_stat_log(Approved By):::"
					+ vo.getStrApprovalBy());

			dao.setProcInValue(procIndex1, "hbldt_approved_date", vo
					.getStrCtDate(), 7);

			dao.setProcInValue(procIndex1, "gstr_remarks", vo.getStrRemarks(),
					8);

			dao.setProcInValue(procIndex1, "gdt_entry_date", vo.getStrCtDate(),
					9);

			dao
					.setProcInValue(procIndex1, "gnum_seatid", vo
							.getStrSeatId(), 10);

			dao.setProcInValue(procIndex1, "gnum_isvalid", "1", 11);

			dao.setProcInValue(procIndex1, "hosp_code",
					vo.getStrHospitalCode(), 12);

			dao.setProcOutValue(procIndex1, "err", 1, 13); // 1 for string
															// return

			// execute procedure

			dao.execute(procIndex1, 1);
			retVal = true;
		} catch (Exception e) {

			new HisException("", "",
					"IpdBillManagementTransDAO.insertHbltPatAcctStatLogDtl()--> "
							+ e.getMessage());

			vo
					.setStrMsgString("HbltBillReqDtlDAO.insertHbltPatAcctStatLogDtl()--> "
							+ e.getMessage());
			vo.setStrMsgType("1");
			retVal = false;

		}

		return retVal;

	}

	/**
	 * This method use in Update Acct Status Update method for HBLT_PATACCT_DTL
	 */

	public static boolean UpdateHbltPatAcctDtl(IpdBillManagementTransVO vo,
			HisDAO dao) {
		boolean retVal = false;
		String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_DML.dml_hblt_pataccount_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		int procIndex1 = 0;

		// String strerr = "";

		try {

			String strTemp[] = vo.getStrChk().replace('@', '#').split("#");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "modval", "3", 1);
			dao.setProcInValue(procIndex1, "hblnum_pataccount_no", strTemp[0],
					2);
			dao.setProcInValue(procIndex1, "hblnum_pataccount_status", vo
					.getStrNewAcctStatus(), 21);
			dao.setProcInValue(procIndex1, "sblnum_chargetype_id", "0", 3);
			dao.setProcInValue(procIndex1, "hbldt_pataccount_opdate", "0", 4);
			dao.setProcInValue(procIndex1, "gnum_dept_code", "0", 5);
			dao.setProcInValue(procIndex1, "hrgnum_puk", "0", 6);
			dao.setProcInValue(procIndex1, "hblnum_client_patient_no", "0", 7);
			dao.setProcInValue(procIndex1, "hastr_adm_no", "0", 8);
			dao.setProcInValue(procIndex1, "hrgnum_episode_code", "0", 9);
			dao.setProcInValue(procIndex1, "gnum_patient_cat_code", "0", 10);
			dao.setProcInValue(procIndex1, "hblnum_sanc_amt", "0", 11);
			dao.setProcInValue(procIndex1, "hblnum_patient_recd_amt", "0", 12);
			dao.setProcInValue(procIndex1, "hblnum_client_amt", "0", 13);
			dao
					.setProcInValue(procIndex1, "hblnum_patient_refund_amt",
							"0", 14);
			dao.setProcInValue(procIndex1, "hblnum_discount_net_amt", "0", 15);
			dao.setProcInValue(procIndex1, "hblnum_reconcile_net_amt", "0", 16);
			dao.setProcInValue(procIndex1, "hblnum_net_actual_amt", "0", 17);
			dao.setProcInValue(procIndex1, "hblstr_approved_by", "0", 18);
			dao.setProcInValue(procIndex1, "hblnum_client_balance", "0", 19);
			dao.setProcInValue(procIndex1, "hbldt_pataccount_closing_date", "",
					20);
			dao.setProcInValue(procIndex1, "gdt_entry_date", "", 23);
			dao.setProcInValue(procIndex1, "gnum_seatid", "0", 24);
			dao.setProcInValue(procIndex1, "gnum_isvalid", "1", 25);
			dao.setProcInValue(procIndex1, "hblnum_approval_id", "0", 26);
			dao.setProcInValue(procIndex1, "hblnum_clientpat_bound_date", "",
					27);
			dao.setProcInValue(procIndex1, "hblnum_sertax_net_amt", "0", 28);
			dao.setProcInValue(procIndex1, "hblnum_tariff_net_amt", "0", 29);
			dao.setProcInValue(procIndex1, "hblnum_penelty_net_amt", "0", 30);
			dao.setProcInValue(procIndex1, "hbldt_approved_date", "", 22);
			dao.setProcInValue(procIndex1, "hosp_code",
					vo.getStrHospitalCode(), 31);
			dao.setProcInValue(procIndex1, "is_bill_final_flag", "", 32);
			dao.setProcInValue(procIndex1, "hblnum_againstsec_flag", "", 33);
			dao.setProcInValue(procIndex1, "hblnum_mob_no", "", 34);
            dao.setProcInValue(procIndex1,"hblnum_room_limit","0",35);
			dao.setProcInValue(procIndex1, "ip",GetNetworkAddress.GetAddress("ip"),36);
			dao.setProcInValue(procIndex1, "mac",GetNetworkAddress.GetAddress("mac"),37);
			dao.setProcInValue(procIndex1, "acctype","",38);
			dao.setProcOutValue(procIndex1, "err", 1, 39); // 1 for string
															// return

			dao.execute(procIndex1, 1);

			retVal = true;
		} catch (Exception e) {
			new HisException("", "",
					"IpdBillManagementTransDAO.HbltPatAcctDtl.UpdateHbltPatAcctDtl() --> "
							+ e.getMessage());

			vo.setStrMsgString("HbltBillReqDtlDAO.UpdateHbltPatAcctDtl() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
			retVal = false;

		}

		return retVal;
	}

	/**
	 * This method is Used in Part Payment Request INSERT INTO TABLE
	 * HBLT_APPROVAL_DTL *
	 */
	public static boolean insertHbltApprovalDtl(IpdBillManagementTransVO vo,
			HisDAO dao) {
		boolean retVal;
		String strTemp[] = vo.getStrChk().replace('@', '#').split("#");
		// System.out.println("When Checked :");
		// System.out.println(" SeatID ->"+vo.getStrSeatId());
		// System.out.println(" ApprovalID ->"+vo.getStrApprovalId());
		// System.out.println(" Charge Type ID ->"+strTemp[4]);
		// System.out.println(" Emp ID ID ->"+vo.getStrApprovalBy());
		// System.out.println(" Remarks ->"+vo.getStrRemarks());

		String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_DML.dml_hblt_approval_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int procIndex1 = 0;

		// String strerr = "";

		// WebRowSet ws = null;

		try {

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "modval", "1", 1);
			dao.setProcInValue(procIndex1, "hbldt_approval_date", "", 3);
			dao.setProcInValue(procIndex1, "hblnum_user_level", "1", 9);
			dao.setProcInValue(procIndex1, "hblnum_discount_unit", "0", 10);
			dao.setProcInValue(procIndex1, "hblnum_discount_type", "0", 12);
			dao
					.setProcInValue(procIndex1, "hblnum_prev_discount_unit",
							"0", 13);
			dao
					.setProcInValue(procIndex1, "hblnum_prev_discount_type",
							"0", 16);
			dao.setProcInValue(procIndex1, "gdt_entry_date", "", 18);
			dao.setProcInValue(procIndex1, "gnum_isvalid", "1", 19);
			dao.setProcInValue(procIndex1, "hblnum_approval_id", vo
					.getStrApprovalId(), 2);
			dao.setProcInValue(procIndex1, "hblnum_approval_slno", "1", 4);
			dao.setProcInValue(procIndex1, "hblnum_reciept_type", "1", 5);
			dao.setProcInValue(procIndex1, "sblnum_bservice_id", "20", 6);
			dao.setProcInValue(procIndex1, "sblnum_chargetype_id", strTemp[4],
					7);
			dao.setProcInValue(procIndex1, "pstr_employee_number", vo
					.getStrApprovalBy(), 8);
			dao.setProcInValue(procIndex1, "hblstr_remarks",
					vo.getStrRemarks(), 11);
			dao.setProcInValue(procIndex1, "hblnum_approval_type", "4", 14);
			dao
					.setProcInValue(procIndex1, "gnum_seatid", vo
							.getStrSeatId(), 17);
			dao.setProcInValue(procIndex1, "hblnum_status", "1", 15);
			dao.setProcInValue(procIndex1, "hosp_code",
					vo.getStrHospitalCode(), 20);
			dao.setProcOutValue(procIndex1, "err", 1, 21); // 1 for string
															// return

			// execute procedure

			dao.execute(procIndex1, 1);

			retVal = true;

		} catch (Exception e) {
			new HisException("", "",
					"IpdBillManagementTransDAO.insertHbltApprovalDtl() --> "
							+ e.getMessage());
			retVal = false;
			vo
					.setStrMsgString("IpdBillManagementTransDAO.insertHbltApprovalDtl() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}

		return retVal;
	}

	/**
	 * This method is Used TO INSERT INTO TABLE HBLT_BILLREQ_TARIFF_DTL
	 * 
	 */

	public static boolean insertHbltBillReqTrffDtl(IpdBillManagementTransVO vo,
			HisDAO dao) {

		boolean retVal;

		String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_DML.DML_HBLT_BILLREQ_TARIFF_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		int procIndex1 = 0;

		// String strerr = "";

		try {

			String strTemp[] = vo.getStrChk().replace('@', '#').split("#");
			// String[] wardid = vo.getStrWardCode().split("\\^");
			procIndex1 = dao.setProcedure(proc_name1);
			dao.setProcInValue(procIndex1, "modval", "1", 1);
			dao
					.setProcInValue(procIndex1, "hblnum_req_no", vo
							.getStrReqNo(), 2);
			dao.setProcInValue(procIndex1, "hblnum_tariff_id", "0", 3);
			dao.setProcInValue(procIndex1, "hbldt_req_date", "", 4);
			dao.setProcInValue(procIndex1, "sblnum_bservice_id", "20", 5);
			dao.setProcInValue(procIndex1, "hblnum_group_id", vo
					.getStrGroupId(), 6);
			dao.setProcInValue(procIndex1, "gstr_tariff_id", "0", 7);
			dao.setProcInValue(procIndex1, "gnum_rate_unit_id", vo
					.getStrUnitId(), 8);
			dao.setProcInValue(procIndex1, "gnum_qty_unit_id", vo
					.getStrUnitId(), 9);
			dao.setProcInValue(procIndex1, "hblnum_req_qty", "1", 10);
			dao.setProcInValue(procIndex1, "hblnum_bill_qty", "0", 11);
			dao.setProcInValue(procIndex1, "hblnum_tariff_rate", vo
					.getStrPartPaymentAmt(), 12);
			dao.setProcInValue(procIndex1, "hblnum_approval_id", "0", 13);
			dao.setProcInValue(procIndex1, "hblnum_discount_type", "0", 14);
			dao.setProcInValue(procIndex1, "hblnum_status", "1", 15);
			dao.setProcInValue(procIndex1, "gnum_isvalid", "1", 16);
			dao.setProcInValue(procIndex1, "hblnum_discount_unit", "0", 17);
			dao.setProcInValue(procIndex1, "hrgnum_puk", strTemp[1], 18);
			dao.setProcInValue(procIndex1, "hblnum_is_package", "0", 19);
			dao.setProcInValue(procIndex1, "hblnum_service_tax", "0", 20);
			dao.setProcInValue(procIndex1, "hblnum_penelty", "0", 21);
			dao.setProcInValue(procIndex1, "hblnum_qty_type", "0", 22);
			dao.setProcInValue(procIndex1, "sblnum_service_id", "0", 23);
			dao.setProcInValue(procIndex1, "hblnum_reciept_no", "0", 24);
			dao.setProcInValue(procIndex1, "hosp_code",
					vo.getStrHospitalCode(), 25);
			dao.setProcInValue(procIndex1, "deptCode", "0", 26);
			dao.setProcOutValue(procIndex1, "ERR", 1, 27); // 1 for string
															// return
			// System.out.println("(BILLREQ_TARIFF_DTL)REQNO:::"+vo.getStrReqNo());
			// dao.setProcInValue(procIndex1,
			// "sblnum_ipd_chargetype_id",wardid[1]); // Value Remove From
			// Procedure
			// execute procedure
			dao.execute(procIndex1, 1);

			// get value
			retVal = true;

		} catch (Exception e) {
			// new
			// HisException("","","IpdBillManagementTransDAO.PROC_HBLT_BILLREQ_TARIFF_DTL()
			// --> "
			// + e.getMessage());
			vo
					.setStrMsgString("IpdBillManagementTransDAO.insertHbltBillReqTrffDtl() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
			retVal = false;
		}
		return retVal;
	}

	/**
	 * This method is Used TO INSERT DATA INTO TABLE HBLT_BILLREQ_TARIFF_DTL
	 * 
	 */

	public static boolean insertHbltBillReq(IpdBillManagementTransVO vo,
			HisDAO dao) {

		boolean retVal;

		String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_DML.dml_HBLT_BILLREQ_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		int procIndex1 = 0;

		// String strerr = "";

		// WebRowSet ws = null;

		try {

			String strTemp[] = vo.getStrChk().replace('@', '#').split("#");
			if (vo.getStrApprovedDate().equals("")) {
				vo.setStrApprovedDate("");

			} else {
				vo.setStrApprovedDate(vo.getStrCtDate());

			}

			String[] wardid = vo.getStrWardCode().replace("^", "#").split("#");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "modval", "1", 1);
			dao.setProcInValue(procIndex1, "hbldt_req_date", "", 3);
			dao.setProcInValue(procIndex1, "sblnum_service_id", "0", 7);
			dao.setProcInValue(procIndex1, "gstr_req_no", "", 8);
			dao.setProcInValue(procIndex1, "hrgnum_episode_code", "0", 10);
			dao.setProcInValue(procIndex1, "hblnum_bill_no", "0", 15);
			dao.setProcInValue(procIndex1, "gdt_entry_date", "", 18);
			dao.setProcInValue(procIndex1, "hblnum_cancel_no", "0", 21);
			dao.setProcInValue(procIndex1, "hbldt_cancel_date", "", 22);
			dao.setProcInValue(procIndex1, "hblnum_reciept_no", "0", 28);
			dao
					.setProcInValue(procIndex1, "hblnum_req_no", vo
							.getStrReqNo(), 2);
			// System.out.println("HBLT_BILLREQ_DTL(REQ_NO)::"+vo.getStrReqNo());
			dao.setProcInValue(procIndex1, "gnum_dept_code ", vo
					.getStrDeptCode(), 4);
			dao.setProcInValue(procIndex1, "sblnum_chargetype_id", strTemp[4],
					5);
			dao.setProcInValue(procIndex1, "sblnum_bservice_id", "20", 6);
			dao.setProcInValue(procIndex1, "gnum_patient_cat_code", strTemp[3],
					9);
			dao.setProcInValue(procIndex1, "hrgnum_puk", strTemp[1], 11);
			dao.setProcInValue(procIndex1, "hastr_adm_no", strTemp[5], 12);
			dao.setProcInValue(procIndex1, "hblnum_pataccount_no ", strTemp[0],
					13);
			dao.setProcInValue(procIndex1, "hblnum_approval_id", vo
					.getStrApprovalId(), 14);
			// System.out.println("HBLT_BILLREQ_DTL(APP_ID)::"+vo.getStrApprovalId());
			dao.setProcInValue(procIndex1, "hblstr_remarks",
					vo.getStrRemarks(), 16);
			dao.setProcInValue(procIndex1, "hblnum_status", "1", 17);
			dao
					.setProcInValue(procIndex1, "gnum_seatid", vo
							.getStrSeatId(), 19);
			dao.setProcInValue(procIndex1, "gnum_isvalid", "1", 20);
			dao.setProcInValue(procIndex1, "hblstr_approved_by ", vo
					.getStrApprovalBy(), 23);
			dao.setProcInValue(procIndex1, "hbldt_approved_date", vo
					.getStrApprovedDate(), 24);
			dao.setProcInValue(procIndex1, "hblnum_req_type", "1", 25);
			dao.setProcInValue(procIndex1, "sblnum_ipd_chargetype_id",
					wardid[1], 26);
			dao.setProcInValue(procIndex1, "hipnum_ward_code", wardid[0], 27);
			dao.setProcInValue(procIndex1, "hosp_code",
					vo.getStrHospitalCode(), 29);
			dao.setProcOutValue(procIndex1, "ERR", 1, 30); // 1 for string
															// return
			dao.execute(procIndex1, 1);
			retVal = true;
		} catch (Exception e) {
			new HisException("", "",
					"IpdBillManagementTransDAO.insertHbltBillReqDtl() --> "
							+ e.getMessage());

			vo
					.setStrMsgString("IpdBillManagementTransDAO.insertHbltBillReqDtl() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
			retVal = false;

		}

		return retVal;
	}

	/**
	 * This method is Used TO INSERT DATA INTO TABLE SBLT_INBOUND_DTL
	 * 
	 */

	public static boolean insertSbltInbound(IpdBillManagementTransVO vo,
			HisDAO dao) {
		boolean retVal;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_DML.dml_SBLT_INBOUND_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int procIndex1 = 0;

		// String strerr = "";

		// WebRowSet ws = null;
		try {
			// Here we Split CheckBox Value
			String strTemp[] = vo.getStrChk().replace('@', '#').split("#");
			// Here we Get Ward Code wardid[0] & Ipd_ChargeType_ID wardid[1]
			String[] wardid = vo.getStrWardCode().replace("^", "#").split("#");

			if (vo.getStrApprovedDate().equals("")) {
				vo.setStrApprovedDate("");
				// System.out.println("Without Check Box");
			} else {
				vo.setStrApprovedDate(vo.getStrCtDate());
				// System.out.println("With Check Box");
			}
			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "modval", "1", 1);
			dao
					.setProcInValue(procIndex1, "hblnum_req_no", vo
							.getStrReqNo(), 2);
			dao.setProcInValue(procIndex1, "gnum_dept_code ", vo
					.getStrDeptCode(), 4);
			dao.setProcInValue(procIndex1, "hbldt_req_date", "", 3);
			dao.setProcInValue(procIndex1, "sblnum_service_id", "0", 7);
			dao.setProcInValue(procIndex1, "gstr_req_no", "", 8);
			dao.setProcInValue(procIndex1, "hblnum_bill_no", "0", 9);
			dao.setProcInValue(procIndex1, "gdt_entry_date", "", 17);
			dao.setProcInValue(procIndex1, "gnum_isvalid", "1", 19);
			dao.setProcInValue(procIndex1, "hblnum_reciept_no", "1", 25);
			dao.setProcInValue(procIndex1, "sblnum_chargetype_id", strTemp[4],
					5);
			dao.setProcInValue(procIndex1, "sblnum_bservice_id", "20", 6);
			dao.setProcInValue(procIndex1, "gnum_patient_cat_code", vo
					.getStrTreatmentCategory(), 10);
			dao.setProcInValue(procIndex1, "hrgnum_puk", strTemp[1], 12);
			dao.setProcInValue(procIndex1, "hastr_adm_no", strTemp[5], 13);
			dao.setProcInValue(procIndex1, "hblnum_pataccount_no ", strTemp[0],
					14);
			dao.setProcInValue(procIndex1, "hblnum_approval_id", vo
					.getStrApprovalId(), 15);
			// System.out.println("SBLT-INBOUD-DTL(Approval
			// ID):::"+vo.getStrApprovalId());
			dao.setProcInValue(procIndex1, "hrgnum_episode_code", strTemp[8]
					.replace("$", "#").split("#")[0], 11);
			dao.setProcInValue(procIndex1, "hblnum_status", "1", 16);
			dao
					.setProcInValue(procIndex1, "gnum_seatid", vo
							.getStrSeatId(), 18);
			dao.setProcInValue(procIndex1, "hblstr_approved_by ", vo
					.getStrApprovalBy(), 20);
			dao.setProcInValue(procIndex1, "hbldt_approved_date", vo
					.getStrApprovedDate(), 21);
			dao.setProcInValue(procIndex1, "hblnum_req_type", "1", 22);
			dao.setProcInValue(procIndex1, "sblnum_ipd_chargetype_id",
					wardid[1], 23);
			dao.setProcInValue(procIndex1, "hipnum_ward_code", wardid[0], 24);
			dao.setProcInValue(procIndex1, "hosp_code",
					vo.getStrHospitalCode(), 26);
			dao.setProcInValue(procIndex1, "hblstr_remarks", "", 27);
			dao.setProcOutValue(procIndex1, "err", 1, 28); // 1 for string
															// return
			// value
			// execute procedure

			dao.execute(procIndex1, 1);

			retVal = true;
		} catch (Exception e) {

			e.printStackTrace();

			new HisException("", "",
					"IpdBillManagementTransDAO.insertSbltInbound() --> "
							+ e.getMessage());
			retVal = false;

		}

		return retVal;
	}

	// nitin here
	/**
	 * Insert Account Dtl Method INSERT DATA INTO HBLT_PATACCOUNT_DTL
	 */
	public static boolean InsertAccountDtl(IpdBillManagementTransVO vo) {

		String strTemp[] = vo.getStrClientPatNo().replace('^', '#').split("#");
		String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_DML.DML_HBLT_PATACCOUNT_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int procIndex1 = 0;
		String strerr = "";
		// WebRowSet ws = null;
		boolean retVal = false;
		try {

			dao = new HisDAO("billing",
					"transactions.DAObilling.getAccountDtlWithAcctNo()");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "hblnum_pataccount_no", vo
					.getStrAccountNo(), 2);
			dao.setProcInValue(procIndex1, "hblnum_client_patient_no",
					strTemp[0], 7);
			dao.setProcInValue(procIndex1, "hblnum_client_balance", vo
					.getStrCltApprBalanceAmt(), 19);
			dao.setProcInValue(procIndex1, "hblnum_sanc_amt", vo
					.getStrCltAppSancAmt(), 11);
			dao.setProcInValue(procIndex1, "modval", "2", 1);
			dao.setProcInValue(procIndex1, "sblnum_chargetype_id", "0", 3);
			dao.setProcInValue(procIndex1, "hbldt_pataccount_opdate", "", 4);
			dao.setProcInValue(procIndex1, "gnum_dept_code", "0", 5);
			dao.setProcInValue(procIndex1, "hrgnum_puk", "0", 6);
			dao.setProcInValue(procIndex1, "hastr_adm_no", "0", 8);
			dao.setProcInValue(procIndex1, "hrgnum_episode_code", "0", 9);
			dao.setProcInValue(procIndex1, "gnum_patient_cat_code", "0", 10);
			dao.setProcInValue(procIndex1, "hblnum_patient_recd_amt", "0", 12);
			dao.setProcInValue(procIndex1, "hblnum_client_amt", "0", 13);
			dao
					.setProcInValue(procIndex1, "hblnum_patient_refund_amt",
							"0", 14);
			dao.setProcInValue(procIndex1, "hblnum_discount_net_amt", "0", 15);
			dao.setProcInValue(procIndex1, "hblnum_reconcile_net_amt", "0", 16);
			dao.setProcInValue(procIndex1, "hblnum_net_actual_amt", "0", 17);
			dao.setProcInValue(procIndex1, "hblstr_approved_by", "", 18);
			dao.setProcInValue(procIndex1, "hbldt_pataccount_closing_date", "",
					20);
			dao.setProcInValue(procIndex1, "hblnum_pataccount_status", "1", 21);
			dao.setProcInValue(procIndex1, "hbldt_approved_date", "", 22);
			dao.setProcInValue(procIndex1, "gdt_entry_date", "", 23);
			dao.setProcInValue(procIndex1, "gnum_seatid", "1", 24);
			dao.setProcInValue(procIndex1, "gnum_isvalid", "1", 25);
			dao.setProcInValue(procIndex1, "hblnum_approval_id", "0", 26);
			dao.setProcInValue(procIndex1, "hblnum_clientpat_bound_date", "",
					27);
			dao.setProcInValue(procIndex1, "hblnum_sertax_net_amt", "0", 28);
			dao.setProcInValue(procIndex1, "hblnum_tariff_net_amt", "0", 29);
			dao.setProcInValue(procIndex1, "hblnum_penelty_net_amt", "0", 30);
			dao.setProcInValue(procIndex1, "hosp_code",
					vo.getStrHospitalCode(), 31);
			dao.setProcInValue(procIndex1, "is_bill_final_flag", "", 32);
			dao.setProcInValue(procIndex1, "hblnum_againstsec_flag", "", 33);
			dao.setProcInValue(procIndex1, "hblnum_mob_no", "", 34);
            dao.setProcInValue(procIndex1,"hblnum_room_limit","0",35);
			dao.setProcInValue(procIndex1, "ip",GetNetworkAddress.GetAddress("ip"),36);
			dao.setProcInValue(procIndex1, "mac",GetNetworkAddress.GetAddress("mac"),37);
			dao.setProcInValue(procIndex1, "acctype","",38);
			dao.setProcOutValue(procIndex1, "ERR", 1, 38); // 1 for string
															// return
			// value
			// execute procedure
			dao.executeProcedureByPosition(procIndex1);
			// get value

			strerr = dao.getString(procIndex1, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				retVal = true;
				vo.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("IpdBillManagementTransDAO.InsertAccountDtl() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}
		return retVal;

	}

	// ////////////////////DROP DOWN DATA ////////////////////////

	/**
	 * retrieves Off Line Group Combo
	 * 
	 * @param voObj -
	 *            ValueObject
	 */
	public static void getOffLineGroup(IpdBillManagementTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strChargeTypeId = voObj.getStrChargeTypeID();
		String strIsPackageWise = "2";
		// System.out.println("getOffLineGroup(strHospitalCode)->"+strHospitalCode);
		// System.out.println("getOffLineGroup(strChargeTypeId)->"+strChargeTypeId);

		String strProcName = "{call pkg_bill_view.proc_HBLT_HSERVICE_GROUP_MST(?,?,?,?,?,?)}";

		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("Ipd Bill Management Transaction",
					"IpdBillManagementTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 1);
			daoObj.setProcInValue(nProcIndex, "charge_id", strChargeTypeId, 2);
			daoObj.setProcInValue(nProcIndex, "pkg_wise_group",
					strIsPackageWise, 3);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 4);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				if (ws != null) {

					if (strIsPackageWise.equals("0")) {
						voObj.setOfflineGroupList(ws);
					} else {
						voObj.setOfflinePackageGroup(ws);
					}
				}
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();

			voObj
					.setStrMsgString("IpdBillManagementTransDAO.getOffLineGroup() --> "
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
	public static void getOffLineTariffList(IpdBillManagementTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strGroupId = voObj.getStrGroupId();
		String strChargeTypeId = voObj.getStrChargeTypeID();
		String strCategoryCode = voObj.getStrNewTreatmentCategory();
		String strWardCode = voObj.getStrOffLineWard();
		String strPackage = "2";
		String strSearchLetter = voObj.getStrSearchLetter();

		if (strChargeTypeId == null)
			strChargeTypeId = "";
		if (strCategoryCode == null)
			strCategoryCode = "";

		if (strGroupId == null)
			strGroupId = "";
		if (strWardCode == null)
			strWardCode = "";

		if (strPackage == null || strPackage.equals(""))
			strPackage = "0";

		String strErr = "";
		String mode = "1";

		strPackage = "2";

		strProcName = "{call PKG_BILL_VIEW.PROC_HBLT_CHARGE_MST(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		try {
			daoObj = new HisDAO("Add Services",
					"AddServicesIPDTransDAO.getOffLineTariffList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "chargeTypeId", strChargeTypeId,
					1);
			daoObj.setProcInValue(nProcIndex, "catCode", strCategoryCode, 2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 7);
			daoObj.setProcInValue(nProcIndex, "pkg_flag", strPackage, 8);
			daoObj.setProcInValue(nProcIndex, "wardCode", strWardCode, 3);
			daoObj.setProcInValue(nProcIndex, "groupId", strGroupId, 4);
			daoObj.setProcInValue(nProcIndex, "searchtrfname", strSearchLetter,
					5);
			daoObj.setProcInValue(nProcIndex, "searchMode", "1", 6);
			daoObj.setProcInValue(nProcIndex, "advance_flag", "1", 9);
			daoObj.setProcInValue(nProcIndex, "modeVal", mode, 10);
			daoObj.setProcInValue(nProcIndex, "end_date", "", 11);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 12);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 13);

			daoObj.executeProcedureByPosition(nProcIndex);

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
				voObj.setOfflineTariffList(ws);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("CashCollectionTransDAO.getOffLineTariffList() --> "
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
	 * retrieves discount approval list
	 * 
	 * @param voObj
	 */
	public static void getOffLineDiscountApprovalList(
			IpdBillManagementTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.proc_consultant_name(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {

			daoObj = new HisDAO("Ipd BillManagement Transaction",
					"IpdBillManagementTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "deptunitCode", "0", 1);
			daoObj.setProcInValue(nProcIndex, "seatId", "", 3);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospitalCode(), 2);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				if (ws.next()) {
					voObj.setStrOffLineWard(ws.getString(1));
					ws.beforeFirst();
				}

				voObj.setOfflineDiscountApprovedBy(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();

			voObj
					.setStrMsgString("IpdBillManagementTransDAO.getOffLineDiscountApprovalList() --> "
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
	 * retrieves discount remarks list
	 * 
	 * @param voObj
	 */
	public static void getOffLineDiscountRemarksList(
			IpdBillManagementTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.proc_hblt_remarks_mst(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("Ipd BillManagement Transaction",
					"IpdBillManagementTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "rmkstype", "1", 1);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospitalCode(), 3);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				if (ws.next()) {

					voObj.setStrOffLineWard(ws.getString(1));

					ws.beforeFirst();

				}

				voObj.setOfflineDiscountRemarks(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();

			voObj
					.setStrMsgString("IpdBillManagementTransDAO.getOffLineDiscountRemarksList() --> "
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
	 * retrieves unit list based on unit id and hospital code
	 * 
	 * @param voObj
	 */
	public static void getOffLineTariffUnitList(IpdBillManagementTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_GBLT_UNIT_MST(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {

			daoObj = new HisDAO("Ipd BillManagement Transaction",
					"IpdBillManagementTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospitalCode(), 1);
			daoObj.setProcInValue(nProcIndex, "unit_id", voObj
					.getStrOffLineTariffUnitTempId(), 2);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 3);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setOfflineTariffUnit(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("IpdBillManagementTransDAO.getOffLineTariffUnitList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	// ///////////////////DROP DOWN - DATA END////////////////////////////////

	public static void getParticularDtlsList(IpdBillManagementTransVO voObj) 
	{
		// HisDAO daoObj = null;
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws1 = null;
		String strAcctNo = voObj.getStrAccountNo();
		String strHospCode = voObj.getStrHospitalCode();

		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW. Proc_Hblt_Pataccount_Service(?,?,?,?,?,?,?,?,?,?)}";
		try 
		{
			dao = new HisDAO("billing","transactions.DAObilling.getAllAmtForViewBill(BillingVO voHdrObj)");
			procIndex1 = dao.setProcedure(proc_name1);
			dao.setProcInValue(procIndex1, "billNo", "0", 1);
			dao.setProcInValue(procIndex1, "accNo", strAcctNo, 2);
			dao.setProcInValue(procIndex1, "to_be_refund_pkg", "0", 3);
			dao.setProcInValue(procIndex1, "groupId", "", 4);			
			dao.setProcInValue(procIndex1, "modeval", "2", 5);
			dao.setProcInValue(procIndex1, "hosp_code", strHospCode, 6);			
			dao.setProcInValue(procIndex1, "recNo", "0", 7);
			dao.setProcInValue(procIndex1, "recType", "0", 8);
			dao.setProcOutValue(procIndex1, "ERR", 1, 9);
			dao.setProcOutValue(procIndex1, "RESULTSET", 2, 10);
			dao.executeProcedureByPosition(procIndex1);

			ws1 = dao.getWebRowSet(procIndex1, "RESULTSET");
			voObj.setStrParticularDtlsListWs(ws1);
			err = dao.getString(procIndex1, "ERR");

			if (err == null)
				err = "";

			if (!err.equals("")) 
			{
				throw new Exception(err);
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			voObj.setStrMsgString("IpdBillManagementTransDAO.getParticularDtlsList() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
	}

	public static String getBillReopenFlag(IpdBillManagementTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_get_bill_reopen_flag(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		String strFlag = "";
		try {

			daoObj = new HisDAO("Ipd BillManagement Transaction",
					"IpdBillManagementTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "patAccNo", vo.getStrAccountNo(),
					1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo
					.getStrHospitalCode(), 2);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 4);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				while (ws.next()) {
					strFlag = ws.getString(1);
				}
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			vo
					.setStrMsgString("IpdBillManagementTransDAO.getBillReopenFlag() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

		return strFlag;
	}
	
	//to get the account details for Ipd Desk
	public static void getAccDetailsIpd(IpdBillManagementTransVO VO) {
		
		String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_VIEW.proc_acc_details_ipd(?,?,?,?,?)}";

		HisDAO dao = null;

		int procIndex1 = 0;

		String strerr = "";

		WebRowSet ws = null;

		try {

			dao = new HisDAO("billing",
					"transactions.DAObilling.getAccountDtlWithAcctNo()");

			procIndex1 = dao.setProcedure(proc_name1);

			// set value

			dao.setProcInValue(procIndex1, "puk", VO.getStrCrNo(), 1);
			dao.setProcInValue(procIndex1, "admno", VO.getStrAddmissionNo(), 2);
			dao.setProcInValue(procIndex1, "hosp_code", VO.getStrHospitalCode(), 3);
			dao.setProcOutValue(procIndex1, "ERR", 1, 4); // 1 for string
															// return
			// value
			dao.setProcOutValue(procIndex1, "RESULTSET", 2, 5); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(procIndex1);
			// get value
			strerr = dao.getString(procIndex1, "ERR");
			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {
				ws = dao.getWebRowSet(procIndex1, "RESULTSET");
				VO.setStrAccDtls(ws);
				VO.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}

		} catch (Exception e) {

			VO
					.setStrMsgString("IpdBillManagementTransDAO.getAccountDtlWithAcctNo() --> "
							+ e.getMessage());
			VO.setStrMsgType("1");

		}

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}

	}
	
public static void getAllTrfList(IpdBillManagementTransVO voObj) 
{
	// HisDAO daoObj = null;
	HisDAO dao = null;
	int procIndex1 = 0;
	String err = "";
	WebRowSet ws1 = null;
	String strAcctNo = voObj.getStrAccountNo();
	String strHospCode = voObj.getStrHospitalCode();

	String proc_name1 = "";
	proc_name1 = "{call PKG_BILL_VIEW.PROC_HBLT_PATACCOUNT_SERVICE(?,?,?,?,?,?,?,?,?,?)}";
	try 
	{
		dao = new HisDAO("billing","transactions.IPDBillMgmtDAO.getAllTrfList()");
		procIndex1 = dao.setProcedure(proc_name1);
		dao.setProcInValue(procIndex1, "billNo", "0", 1);
		dao.setProcInValue(procIndex1, "accNo", strAcctNo, 2);
		dao.setProcInValue(procIndex1, "to_be_refund_pkg", "0", 3);
		dao.setProcInValue(procIndex1, "groupId", "", 4);			
		dao.setProcInValue(procIndex1, "modeval", "9", 5);
		dao.setProcInValue(procIndex1, "hosp_code", strHospCode, 6);			
		dao.setProcInValue(procIndex1, "recNo", "0", 7);
		dao.setProcInValue(procIndex1, "recType", "0", 8);
		dao.setProcOutValue(procIndex1, "ERR", 1, 9);
		dao.setProcOutValue(procIndex1, "RESULTSET", 2, 10);
		dao.executeProcedureByPosition(procIndex1);

		ws1 = dao.getWebRowSet(procIndex1, "RESULTSET");
		voObj.setAllTrfListWS(ws1);
		err = dao.getString(procIndex1, "ERR");

		if (err == null)
			err = "";

		if (!err.equals("")) 
		{
			throw new Exception(err);
		}
	} 
	catch (Exception e) 
	{
		// e.printStackTrace();
		voObj.setStrMsgString("IpdBillManagementTransDAO.getAllTrfList() --> "+ e.getMessage());
		voObj.setStrMsgType("1");
	}
	finally 
	{
		if (dao != null) 
		{
			dao.free();
			dao = null;
		}
	}
}

public static void getPrevBedTransfers(IpdBillManagementTransVO voObj) 
{
	// HisDAO daoObj = null;
	HisDAO dao = null;
	int procIndex1 = 0;
	String err = "";
	WebRowSet ws1 = null;
	String strAcctNo = voObj.getStrAccountNo();
	String strHospCode = voObj.getStrHospitalCode();

	String proc_name1 = "";
	proc_name1 = "{call PKG_BILL_VIEW.PROC_HBLT_PATACCOUNT_SERVICE(?,?,?,?,?,?,?,?,?,?)}";
	try 
	{
		dao = new HisDAO("billing","transactions.IPDBillMgmtDAO.getAllTrfList()");
		procIndex1 = dao.setProcedure(proc_name1);
		dao.setProcInValue(procIndex1, "billNo", "0", 1);
		dao.setProcInValue(procIndex1, "accNo", strAcctNo, 2);
		dao.setProcInValue(procIndex1, "to_be_refund_pkg", "0", 3);
		dao.setProcInValue(procIndex1, "groupId", "", 4);			
		dao.setProcInValue(procIndex1, "modeval", "11", 5);
		dao.setProcInValue(procIndex1, "hosp_code", strHospCode, 6);			
		dao.setProcInValue(procIndex1, "recNo", "0", 7);
		dao.setProcInValue(procIndex1, "recType", "0", 8);
		dao.setProcOutValue(procIndex1, "ERR", 1, 9);
		dao.setProcOutValue(procIndex1, "RESULTSET", 2, 10);
		dao.executeProcedureByPosition(procIndex1);

		ws1 = dao.getWebRowSet(procIndex1, "RESULTSET");
		voObj.setPrevBedTrf(ws1);
		err = dao.getString(procIndex1, "ERR");

		if (err == null)
			err = "";

		if (!err.equals("")) 
		{
			throw new Exception(err);
		}
	} 
	catch (Exception e) 
	{
		// e.printStackTrace();
		voObj.setStrMsgString("IpdBillManagementTransDAO.getAllTrfList() --> "+ e.getMessage());
		voObj.setStrMsgType("1");
	}
	finally 
	{
		if (dao != null) 
		{
			dao.free();
			dao = null;
		}
	}
}


public static boolean InsertBedTransfer(IpdBillManagementTransVO vo) 
{
	HisDAO dao = null;
	HisDAO daoCommit = null;
	boolean retVal = false;
	int blockLen = 1;//To UPDATE SEQ No GENERATED
	long compChargeReqNo = 0;
	long specialReqNo = 0;
	long tariffReqNo = 0;

	int length = vo.getStrTransferDeptCode().length;
	//int length = 1;

	
	PrimaryKeyLogDAO pKeyLogDao = new PrimaryKeyLogDAO();
	PatientAccountDAO pActDao = new PatientAccountDAO();
	PatientAccountServiceDAO pActServDao = new PatientAccountServiceDAO();
	// PatientAccountPackageDAO pActPckDao = new PatientAccountPackageDAO();
	// OutBoundDAO outBoundDAO = new OutBoundDAO();

	try 
	{
		dao = new HisDAO("billing","transactions.DAObilling.InsertBedTransfer()");
		
		
		
		
		//if(IpdConfigUtil.AUTO_BED_CHARGES_JOB.equals("OFF"))
		//{			
			for (int k = 0; k < length; k++) // BED CHARGES JOBS LOGIC REMOVED. ALL BED CHARGES NEEDS TO BE TAKEN CARE FROM BED TRANSFER SCREEN EITHER ADT TRANSFER ONLINE OR NOT
			{
					if (length > 0) 
					{
						daoCommit = new HisDAO("billing","transactions.DAObilling.InsertBedTransfer()");
						PrimaryKeyDAO pKeyDao = new PrimaryKeyDAO();
						pKeyDao.setStrKeyname("REQNO");
						pKeyDao.setStrBlockkey(blockLen + "");
						pKeyDao.setStrHospCode(vo.getStrHospitalCode());
						pKeyDao.selectWithCommit(daoCommit);
						vo.setStrReqNo(pKeyDao.getStrPrimrayKeyValue());
						tariffReqNo=Long.parseLong(vo.getStrReqNo());
						pKeyDao=null;
						if (vo.getStrReqNo() != null && !vo.getStrReqNo().equals("")) 
						{
							if (vo.getStrSaveFlag()[k] != null && vo.getStrSaveFlag()[k].equals("1") && vo.getStrNewBabyBed()[k].equals("0")) 
								BedTransferProcedure(vo, dao, k, tariffReqNo);
						}						
					}
			}
		/*}
		else//IF BED CHARGES AUTOMATIC THROUGH JOB THEN TAKE ONLY DISCHARGE DATE BED CHARGES I.E. LAST DATE ONE DAY BED CHARGES
		{
			if (length > 0) 
			{
				PrimaryKeyDAO pKeyDao = new PrimaryKeyDAO();
				pKeyDao.setStrKeyname("REQNO");
				pKeyDao.setStrBlockkey(blockLen + "");
				pKeyDao.setStrHospCode(vo.getStrHospitalCode());
				pKeyDao.select(dao);
				vo.setStrReqNo(pKeyDao.getStrPrimrayKeyValue());
				tariffReqNo=Long.parseLong(vo.getStrReqNo());
				pKeyDao = null;
			}
			BedTransferProcedure(vo, dao, length-1, tariffReqNo);
		}*/
		
		pActDao.setStrPuk(vo.getStrPukNo());
		pActDao.setStrPatAccNo(vo.getStrPatAcctNo());
		pActDao.setStrNetActualAmt(vo.getStrNetActualTariffAmt());
		pActDao.setStrSerTaxNetAmt(vo.getStrNetServiceTaxAmt());
		pActDao.setStrTariffNetAmt(vo.getStrNetTariffAmt());
		pActDao.setStrHospitalCode(vo.getStrHospitalCode());
		pActDao.setIsBillFinal(vo.getIsBillFinal());
		pActDao.setStrSeatId(vo.getStrSeatId());
        pActDao.setStripadd(GetNetworkAddress.GetAddress("ip"));
		pActDao.setStrmacadd(GetNetworkAddress.GetAddress("mac"));
		pActDao.update3(dao);

		if (new BillConfigUtil(vo.getStrHospitalCode()).getIpdGenAdtProcessType().equals("2")) 
		{
			String proc_name1 = "";
			proc_name1 = "{call PKG_BILL_DML.dml_update_account_dtl(?,?,?,?,?,?,?,?)}";

			int procIndex1 = 0;

			procIndex1 = dao.setProcedure(proc_name1);

			dao.setProcInValue(procIndex1, "modval", "1", 1);

			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHospitalCode(), 2);
			dao.setProcInValue(procIndex1, "accNo", vo.getStrPatAcctNo(), 3);
			dao.setProcInValue(procIndex1, "wardId", vo.getStrSpecialWardType(), 4);
			dao.setProcInValue(procIndex1, "ipdChargeTypeId", vo.getStrWardType(), 5);
			dao.setProcInValue(procIndex1, "patCat", vo.getStrPatientCatCode(), 6);
			dao.setProcInValue(procIndex1, "deptId", vo.getStrDepartment(),7);
			dao.setProcOutValue(procIndex1, "ERR", 1, 8);
			dao.execute(procIndex1, 1);
		}
		
		// Compulsory Auto Charges by Consultant  // added by manisha
		if(vo.getIsBillFinal().equalsIgnoreCase("91"))   // bed transfer flag is checked  // to avoid insertion everytime
		{
		String proc_name2 = "";
		proc_name2 = "{call pkg_bill_auto_charge.proc_bill_auto_charge_scheduler(?,?,?)}";

		int procIndex2 = 0;

		procIndex2 = dao.setProcedure(proc_name2);

		dao.setProcInValue(procIndex2, "modval", "2", 1);  // FOR PROCESS=2, SCHEDULER=1
		dao.setProcInValue(procIndex2, "puk", vo.getStrPukNo(), 2);
		dao.setProcInValue(procIndex2, "hosp_code", vo.getStrHospitalCode(), 3);
		//dao.setProcOutValue(procIndex2, "ERR", 1, 4);
		dao.execute(procIndex2, 1);
		
		}
		
		
		synchronized (dao) 
		{
			dao.fire();
			retVal = true; 
		}

	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		vo.setStrErrPrimKeyLog(e.getMessage());
		vo.setStrMsgString("IpdBillManagementTransDAO.InsertBedTransfer()--> "+ e.getMessage());
		vo.setStrMsgType("1");
		retVal = false;

		try 
		{
			pKeyLogDao.setStrKeyname("REQNO");
			pKeyLogDao.setStrStartkey(vo.getStrReqNo());
			pKeyLogDao.setStrBlockkey("1");
			pKeyLogDao.setStrHospCode(vo.getStrHospitalCode());
			pKeyLogDao.setStrError(vo.getStrErrPrimKeyLog());
			pKeyLogDao.setStrSeatid(vo.getStrSeatId());
			pKeyLogDao.insert(dao);
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
			vo.setStrMsgString("IpdBillManagementTransDAO.InsertBedTransfer()--> "+ e1.getMessage());
			vo.setStrMsgType("1");
			new HisException("billing", "IpdBillMangementTrans","IpdBillManagementTransDAO.InsertBedTransfer() --> "+ e1.getMessage());
			retVal = false;
		}
	} 
	finally 
	{
		if (dao != null) 
		{
			dao.free();
			dao = null;
		}
		if (pActDao != null || pKeyLogDao != null /* || pActPckDao != null */ || pActServDao != null) 
		{
			pActDao = null;
			pKeyLogDao = null;
		}
	}
	return retVal;

}

public static void BedTransferProcedure(IpdBillManagementTransVO vo, HisDAO dao, int i, long requestNo) 
{
	String proc_name = "";
	proc_name = "{call pkg_bill_dml.dml_account_add_service(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	int nprocIndex = 0;
	try 
	{
		nprocIndex = dao.setProcedure(proc_name);

		dao.setProcInValue(nprocIndex, "modval", "2", 1);
		dao.setProcInValue(nprocIndex, "hosp_code",vo.getStrHospitalCode(), 2);
		dao.setProcInValue(nprocIndex, "reqNo", String.valueOf(requestNo),3);
		dao.setProcInValue(nprocIndex, "patAccNo", vo.getStrPatAcctNo(), 4);
		dao.setProcInValue(nprocIndex, "puk", vo.getStrPukNo(), 5);
		dao.setProcInValue(nprocIndex, "catCode",vo.getStrPatientCatCode(), 6);
		dao.setProcInValue(nprocIndex, "deptCode", vo.getStrTransferDeptCode()[i], 7);//vo.getStrTransferDeptCode()[i]
		dao.setProcInValue(nprocIndex, "grpId", vo.getStrUnitCode()[i], 8); // here grpId Pass as DeptUnitCode
		dao.setProcInValue(nprocIndex, "trfId", vo.getStrConsultant()[i], 9);// here trfId Pass as consultant ID
		dao.setProcInValue(nprocIndex, "reqQty", "0", 10);
		dao.setProcInValue(nprocIndex, "reqQtyUnitId","0", 11);
		dao.setProcInValue(nprocIndex, "rate","0", 12);
		dao.setProcInValue(nprocIndex, "actRate","0", 13);
		dao.setProcInValue(nprocIndex, "rateUnitId","0", 14);
		dao.setProcInValue(nprocIndex, "serTax", "0", 15);
		dao.setProcInValue(nprocIndex, "serTaxAmt", "0", 16);
		dao.setProcInValue(nprocIndex, "trfNetAmt","0", 17);
		dao.setProcInValue(nprocIndex, "chargeTypeId", "2", 18);
		dao.setProcInValue(nprocIndex, "wardId",vo.getStrTransferWardCode()[i].replace("^","#").split("#")[0], 19);
		dao.setProcInValue(nprocIndex, "bServiceId", "11", 20);
		dao.setProcInValue(nprocIndex, "serviceId","0", 21);
		dao.setProcInValue(nprocIndex, "gblTrfId","0", 22);
		dao.setProcInValue(nprocIndex, "isRefund","1", 23);
		dao.setProcInValue(nprocIndex, "isPackage", "0",24);
		dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId(), 25);
		dao.setProcInValue(nprocIndex, "slNo", vo.getStrMovNo()[i], 26);
		dao.setProcInValue(nprocIndex, "ipdChargeTypeId", vo.getStrTransferChargeType()[i].replace("^","#").split("#")[0], 27);
		//System.out.println("vo.getStrInDate()[i]"+vo.getStrInDate()[i]);
		//System.out.println("vo.getStrOutDate()[i]"+vo.getStrOutDate()[i]);
		dao.setProcInValue(nprocIndex, "reqDate", vo.getStrInDate()[i], 28);
		dao.setProcInValue(nprocIndex, "endDate", vo.getStrOutDate()[i], 29);
		dao.setProcInValue(nprocIndex, "serviceType", "1", 30);
		dao.setProcInValue(nprocIndex, "tariffDate", vo.getStrOutDate()[i], 31);
		dao.setProcInValue(nprocIndex, "strPriority", "", 32);			
		dao.setProcInValue(nprocIndex, "strDiscount", "0", 33);			
		dao.setProcInValue(nprocIndex, "strDiscountType", "0", 34);			
		dao.setProcInValue(nprocIndex, "strDiscountAmt", "0", 35);			
		dao.setProcInValue(nprocIndex, "strTrfName", "0", 36);			
		dao.setProcInValue(nprocIndex, "strAddRemUpdFlag", "0", 37);
		dao.setProcInValue(nprocIndex, "strPrevReqNo", "0", 38);	
		dao.setProcInValue(nprocIndex, "dblOccFlag", vo.getStrIsDoubleOc()[i], 39);//dblOccFlag=0/1
		dao.setProcInValue(nprocIndex, "strActualOutDate", vo.getStrDblOccDate()[i], 40);//If dblOccFlag=1 THEN ACTUAL OPUT DATE WILL BE UPTO LAST DATE OF ICU STAY 
		dao.setProcInValue(nprocIndex, "ip", GetNetworkAddress.GetAddress("ip"), 41);
		dao.setProcInValue(nprocIndex, "mac", GetNetworkAddress.GetAddress("mac"), 42);
		dao.setProcInValue(nprocIndex, "strDisActAmt", "", 43);
		dao.setProcInValue(nprocIndex, "adtOnlineFlag",vo.getAdtOnlineFlag()[i], 44);
		dao.setProcOutValue(nprocIndex, "err", 1, 45);
		dao.execute(nprocIndex, 1);
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		vo.setStrMsgString("IPDBillMgmtTransDAO.BedTransferProcedure() --> "+ e.getMessage());
		vo.setStrMsgType("1");
	}
}

public static void admissionList(IpdBillManagementTransVO vo)
{
	String strProcName = "{call pkg_bill_view.Proc_Pat_Admstatus_Code_Dtl(?,?,?,?,?)}";
	int nProcIndex = 0;

	String strErr = "";

	HisDAO daoObj = null;
	WebRowSet ws = null;

	try 
	{
			daoObj = new HisDAO("billing","transactions.IPDBillMgmtDAO.admissionList()");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval","1",1);
			daoObj.setProcInValue(nProcIndex, "hrgnum_puk",vo.getStrCrNo(),2);
			daoObj.setProcInValue(nProcIndex, "gnum_hospital_code",vo.getStrHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
            
			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				vo.setAdmissionListWS(ws);
			} 
			else 
			{
				throw new Exception(strErr);
			}
	} 
	catch (Exception e) 
	{
		vo.setStrMsgString("IpdBillManagementTransDAO.admissionList() --> "+ e.getMessage());
		vo.setStrMsgType("1");
	} 
	finally 
	{
		if (daoObj != null) 
		{
			daoObj.free();
			daoObj = null;
		}
	}
}	

public static void InsertNoDuesPrint(IpdBillManagementTransVO voObj)
{
	int nBillFlag = 0;
	int nInsertedIndex1 = 0;
	String strBillNo = "0";
	HisDAO dao = null;
	PrimaryKeyDAO pKey = null;
	PrimaryKeyLogDAO pKeyLogDao = null;
	try 
	{

		dao = new HisDAO("Billing", "IpdBillManagementTransDAO");
		pKey = new PrimaryKeyDAO();
		pKeyLogDao = new PrimaryKeyLogDAO();
		if(voObj.getStrBillNo().equals("0"))
		{
	    // Bill Generation
				pKey.setStrBlockkey("1");
				pKey.setStrKeyname("NODUES_NO");
				pKey.setStrHospCode(voObj.getStrHospitalCode());
				pKey.setStrAppendHospcodeFlag("1");

				pKey.select(dao);

				strBillNo = pKey.getStrPrimrayKeyValue();

				voObj.setStrBillNo(strBillNo);

				nBillFlag = 1;
		}
		else
			strBillNo=voObj.getStrBillNo();

				nInsertedIndex1 = dao.setProcedure("{call pkg_bill_dml.Dml_NoDues_Print(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,?,?)}");

				dao.setProcInValue(nInsertedIndex1, "modval", "1",1);
				dao.setProcInValue(nInsertedIndex1, "hosp_code", voObj.getStrHospitalCode(),2);
				//dao.setProcInValue(nInsertedIndex1, "reqNo", voObj.getStrRequestNo(),3);
				dao.setProcInValue(nInsertedIndex1, "reqDate", voObj.getStrCtDate(),3);
				//dao.setProcInValue(nInsertedIndex1, "gblReqNo", voObj.getStrGlobalRequestNo(),5);
				//dao.setProcInValue(nInsertedIndex1, "serviceId", voObj.getStrService(),6);			
				dao.setProcInValue(nInsertedIndex1, "billNo", strBillNo,4);	
				dao.setProcInValue(nInsertedIndex1, "patAccNo", voObj.getStrAccountNo(),5);
				dao.setProcInValue(nInsertedIndex1, "puk", voObj.getStrCrNo(),6);
				dao.setProcInValue(nInsertedIndex1, "admNo", voObj.getStrAddmissionNo(),7);
				//dao.setProcInValue(nInsertedIndex1, "episodeCode", voObj.getStrEpisode(),11);
				dao.setProcInValue(nInsertedIndex1, "catCode", voObj.getStrTreatmentCategory(),8);
				/*dao.setProcInValue(nInsertedIndex1, "netAmt", voObj.getStrOnlineTotalRecAmount(),13);
				dao.setProcInValue(nInsertedIndex1, "clientPaidAmt", voObj.getStrOnlineMaxClientBenefitAmount(),14);
				dao.setProcInValue(nInsertedIndex1, "patPaidAmt", voObj.getStrOnlinePatNetPayAmount(),15);
				dao.setProcInValue(nInsertedIndex1, "actTrfCost", voObj.getStrTotalTariffActualAmount(),16);
				dao.setProcInValue(nInsertedIndex1, "disAmt", voObj.getStrTotalTariffDiscountAmount(),17);
				dao.setProcInValue(nInsertedIndex1, "serTaxAmt", voObj.getStrTotalTariffServiceTaxAmount(),18);*/
				dao.setProcInValue(nInsertedIndex1, "deptCode", voObj.getStrDeptId(),9);
				//dao.setProcInValue(nInsertedIndex1, "chargeTypeId", voObj.getStrChargeTypeID(),10);
				dao.setProcInValue(nInsertedIndex1, "wardId", voObj.getStrWardCode(),10);
				//dao.setProcInValue(nInsertedIndex1, "bServiceId", voObj.getStrBillingService(),22);
				//dao.setProcInValue(nInsertedIndex1, "clientPatNo", voObj.getStrClientPatientNo(),23);
				//dao.setProcInValue(nInsertedIndex1, "counterId", strCounterId,24);
				//dao.setProcInValue(nInsertedIndex1, "depGrpId", voObj.getStrGroupId(),12);
				//dao.setProcInValue(nInsertedIndex1, "defUnitId", voObj.getStrQtyUnitId(),26);
				dao.setProcInValue(nInsertedIndex1, "seatId", voObj.getStrSeatId(),11);
				
				dao.setProcInValue(nInsertedIndex1, "admDate", voObj.getStrAdmissionDate(),12);
				dao.setProcInValue(nInsertedIndex1, "disDate", voObj.getStrDischargeDate(),13);
				dao.setProcInValue(nInsertedIndex1, "ip", GetNetworkAddress.GetAddress("ip"), 14);
				dao.setProcInValue(nInsertedIndex1, "mac", GetNetworkAddress.GetAddress("mac"), 15);		
				dao.setProcOutValue(nInsertedIndex1, "err", 1,16);
				
				dao.execute(nInsertedIndex1, 1);
				
				synchronized (dao) 
				{
					dao.fire();
				}
	} 
	catch (Exception e) 
	{
		String err = "err:" + e.getMessage();
		voObj.setStrMsgString("IpdBillManagementTransDAO.InsertNoDuesPrint()--> "+ err);
		voObj.setStrMsgType("1");
		if (nBillFlag == 1) 
		{
			try 
				{
						pKeyLogDao.setStrKeyname("BILL_IPD");
						pKeyLogDao.setStrStartkey(strBillNo);
						pKeyLogDao.setStrBlockkey("1");
						pKeyLogDao.setStrError(err);
						pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode()); 
						pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
						// Calling Insert Method of Primary Key Log
						pKeyLogDao.insert(dao);

				} 
				catch (Exception e1) 
				{
						voObj.setStrMsgString("IpdBillManagementTransDAO.InsertNoDuesPrint()--> "+ e1.getMessage() + "-->" + err);
						voObj.setStrMsgType("1");
				}
				}
			} 
			finally 
			{
				if (dao != null) 
				{
					dao.free();
					dao = null;
				}

				if (pKey != null) 			pKey 			= null;
				if (pKeyLogDao != null)		pKeyLogDao 		= null;
				

	
    }
}	
}

