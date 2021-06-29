package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.dao.PaymentDAO;
import billing.dao.PrimaryKeyDAO;
import billing.dao.PrimaryKeyLogDAO;

public class CashCollectionWithoutCrTransDAO {

	 
 
     

	/**
	 * retrieves Payment Mode List
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void getPaymentModeList(CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_sblt_paymentmode_mst(?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();

		String strErr = "";

		try {

			daoObj = new HisDAO("Cash Collection Transaction",
					"CashCollectionTransDAO.getPaymentModeList");

			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				voObj.setStrPaymentModeList(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();
			voObj
					.setStrMsgString("CashCollectionTransDAO.getPaymentModeList() --> "
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
	 * retrieves Client Name List
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void getClientNameList(CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_hblt_client_mst(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();

		String strErr = "";

		try {

			daoObj = new HisDAO("Cash Collection Transaction",
					"CashCollectionTransDAO.getClientNameList");

			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode,2);
			daoObj.setProcInValue(nProcIndex, "client_no", "",3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setStrClientNameList(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
			voObj
					.setStrMsgString("CashCollectionTransDAO.getClientNameList() --> "
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
	 * retrieves Remarks List
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void getRemarksList(CashCollectionWithoutCrTransVO voObj) {

		String proc_name = "";

		proc_name = "{call PKG_SIMPLE_VIEW.PROC_HBLT_REMARKS_MST(?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";
		String strHospCode = voObj.getStrHospitalCode();

		WebRowSet ws = null;

		try {

			dao = new HisDAO("Cash Collection Transaction",

			"transactions.CashCollectionTransDAO.getRemarksList()");

			nprocIndex = dao.setProcedure(proc_name);

			// set value

			dao.setProcInValue(nprocIndex, "rmksType", "3",1);

			dao.setProcInValue(nprocIndex, "modeVal", "1",2);

			dao.setProcInValue(nprocIndex, "hosp_code", strHospCode,3);

			dao.setProcOutValue(nprocIndex, "ERR", 1,4); // 1 for string return
			// value

			dao.setProcOutValue(nprocIndex, "RESULTSET", 2,5); // 2 for object

			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {

				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

				if (ws.next()) {
					voObj.setStrOffLineRemarks(ws.getString(2));

					ws.beforeFirst();
				}

				voObj.setOfflineRemarksList(ws);

				voObj.setStrMsgType("0");

			} else {

				throw new Exception(strerr);
			}

		} catch (Exception e) {

			e.printStackTrace();
			
			voObj
					.setStrMsgString("CashCollectionTransDAO.getRemarksList() --> "

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
	 * retrieves Billing Service List
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void getBillingServiceList(CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_SBLT_BILLSERVICE_MST(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strChargeId = voObj.getStrOffLineHospitalService();
		String strRequestType = voObj.getStrOffLineRequestType();

		String strErr = "";

		try {

			daoObj = new HisDAO("Cash Collection Transaction",
					"CashCollectionTransDAO.getBillingServiceList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode,1);
			daoObj.setProcInValue(nProcIndex, "charge_id", strChargeId,2);
			daoObj.setProcInValue(nProcIndex, "req_type", strRequestType,3);
			daoObj.setProcInValue(nProcIndex, "modeval", "1",4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				if (ws.next()) {

					voObj.setStrOffLineBillingService(ws.getString(1));

					ws.beforeFirst();

				}

				voObj.setOfflineBillingServiceList(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			voObj
					.setStrMsgString("CashCollectionTransDAO.getBillingServiceList() --> "
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
	public static void getRaisingDepartmentList(CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.proc_GBLT_DEPARTMENT_MST(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strCrNo = voObj.getStrCrNo();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();

		String strErr = "";

		try {

			daoObj = new HisDAO("Cash Collection Transaction",
					"CashCollectionTransDAO.getRaisingDepartmentList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "4",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode,2);
			daoObj.setProcInValue(nProcIndex, "puk_no", strCrNo,4);
			daoObj.setProcInValue(nProcIndex, "charge_type_id",strChargeTypeId,5);
			

			daoObj.setProcInValue(nProcIndex, "deptcode", "",3);
			daoObj.setProcInValue(nProcIndex, "effect_from", "",6);
			daoObj.setProcInValue(nProcIndex, "effect_to", "",7);
			daoObj.setProcInValue(nProcIndex, "userId", "",8);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,9);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,10);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				if (ws.next()) {

					voObj.setStrOffLineRaisingDepartment(ws.getString(1));

					ws.beforeFirst();

				}

				voObj.setOfflineRaisingDepartmentList(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			voObj
					.setStrMsgString("CashCollectionTransDAO.getRaisingDepartmentList() --> "
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
	 * retrieves Tariff Details
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void getOnlineTariffDetails(CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_hblt_billreq_tariff_dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strReqNo = voObj.getStrRequestNo();
		String strHospCode = voObj.getStrHospitalCode();
		String strChargeTypeId = voObj.getStrChargeTypeId();

		String strErr = "";

		try {

			if (strReqNo != null) {

				daoObj = new HisDAO("Cash Collection Transaction",
						"CashCollectionTransDAO.getOnlineTariffDetails");

				nProcIndex = daoObj.setProcedure(strProcName);
				
				System.out.println("req no is::"+strReqNo+":: charge::"+strChargeTypeId);
								
				daoObj.setProcInValue(nProcIndex, "reqNo", strReqNo,1);
				daoObj.setProcInValue(nProcIndex, "modeVal", "1",2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", strHospCode,3);
				daoObj.setProcInValue(nProcIndex, "deptCode", "0",5);
				daoObj.setProcInValue(nProcIndex, "chargeTypeId",strChargeTypeId,4);

				daoObj.setProcOutValue(nProcIndex, "err", 1,6);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				if (strErr.equals("")) {
					ws = daoObj.getWebRowSet(nProcIndex, "resultset");

					voObj.setOnlineTariffDetails(ws);

				} else {
					throw new Exception(strErr);
				}
			} else {

				voObj.setOnlineTariffDetails(null);
			}

		} catch (Exception e) {

			e.printStackTrace();
			
			voObj
					.setStrMsgString("CashCollectionTransDAO.getOnlineTariffDetails() --> "
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
	 * retrieves Approver Employee List
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void getApprovedBy(CashCollectionWithoutCrTransVO voObj) {

		String proc_name = "";
		proc_name = "{call pkg_simple_view.Proc_user_list(?,?,?,?,?)}";

		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		String strHospCode = voObj.getStrHospitalCode();
		String strSeatId = voObj.getStrSeatId();
		WebRowSet ws = null;

		try {

			dao = new HisDAO("Cash Collection Transaction",

			"transactions.CashCollectionTransDAO.getApprovedBy()");

			nprocIndex = dao.setProcedure(proc_name);

			// set value
			dao.setProcInValue(nprocIndex, "processId", "1",1);
			dao.setProcInValue(nprocIndex, "seatId", strSeatId,3);
			dao.setProcInValue(nprocIndex, "hosp_code", strHospCode,2);
			//dao.setProcInValue(nprocIndex, "seatId", voObj.getStrSeatId());
			dao.setProcOutValue(nprocIndex, "ERR", 1,4); // 1 for string return

			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2,5); // 2 for object

			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "ERR");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) {

				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

				voObj.setOfflineApprovedByList(ws);

				voObj.setStrMsgType("0");

			} else {

				throw new Exception(strerr);

			}

		} catch (Exception e) {

			e.printStackTrace();
			
			voObj.setStrMsgString("CashCollectionTransDAO.getApprovedBy() --> "

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
	 * retrieves Off Line Group Combo
	 * 
	 * @param voObj -
	 *            ValueObject
	 */
	public static void getOffLineGroup(CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();
		String strIsPackageWise = voObj.getStrOffLineIsPackageGroup();
		String strProcName = "{call pkg_bill_view.proc_HBLT_HSERVICE_GROUP_MST(?,?,?,?,?,?)}";

		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("Cash Collection Transaction",
					"CashCollectionTransDAO.getOffLineGroup");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode,1);
			daoObj.setProcInValue(nProcIndex, "charge_id", strChargeTypeId,2);
			daoObj.setProcInValue(nProcIndex, "pkg_wise_group",	strIsPackageWise,3);
			daoObj.setProcInValue(nProcIndex, "modeval", "1",4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,6);

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
					.setStrMsgString("CashCollectionTransDAO.getOffLineGroup() --> "
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
	public static void getOffLineTariffList(CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strGroupId = voObj.getStrOffLineGroup();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();
		String strCategoryCode = voObj.getStrOffLineTreatmentCategory();
		String strWardCode = voObj.getStrOffLineWard();
		String strPackage = voObj.getStrOffLinePackageIndex();
		String strSearchLetter = voObj.getStrSearchLetter();
		

		if (strChargeTypeId == null )
			strChargeTypeId = "";
		if (strCategoryCode == null )
			strCategoryCode = "";
		
		if (strGroupId == null )
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
			daoObj = new HisDAO("Add Services", "AddServicesIPDTransDAO.getOffLineTariffList");

			nProcIndex = daoObj.setProcedure(strProcName);
			
				daoObj.setProcInValue(nProcIndex, "chargeTypeId", strChargeTypeId,1);
				daoObj.setProcInValue(nProcIndex, "catCode", strCategoryCode,2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode,7);
				daoObj.setProcInValue(nProcIndex, "pkg_flag", strPackage,8);
				daoObj.setProcInValue(nProcIndex, "wardCode", strWardCode,3);
				daoObj.setProcInValue(nProcIndex, "groupId", strGroupId,4);
				daoObj.setProcInValue(nProcIndex, "searchtrfname", strSearchLetter,5);
				daoObj.setProcInValue(nProcIndex, "searchMode", "1",6);
				daoObj.setProcInValue(nProcIndex, "advance_flag", "1",9);
				daoObj.setProcInValue(nProcIndex, "modeVal", mode,10);
				daoObj.setProcInValue(nProcIndex, "end_date", "",11);
				daoObj.setProcOutValue(nProcIndex, "err", 1,12);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,13);
 
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

			e.printStackTrace();
			
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
	 * retrieves Off Line Tariff List
	 * 
	 * @param voObj -
	 *            ValueObject
	 */
	public static void getOffLineTariffListByCode(CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strGroupId = voObj.getStrOffLineGroup();
		String strChargeTypeId = voObj.getStrOffLineHospitalService();
		String strCategoryCode = voObj.getStrOffLineTreatmentCategory();
		String strWardCode = voObj.getStrOffLineWard();
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


			
		strProcName = "{call PKG_BILL_VIEW.PROC_HBLT_CHARGE_MST(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		
		try {
			daoObj = new HisDAO("Add Services", "AddServicesIPDTransDAO.getOffLineTariffListByCode");

			nProcIndex = daoObj.setProcedure(strProcName);
			
				daoObj.setProcInValue(nProcIndex, "chargeTypeId", strChargeTypeId,1);
				daoObj.setProcInValue(nProcIndex, "catCode", strCategoryCode,2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode,7);
				daoObj.setProcInValue(nProcIndex, "pkg_flag", strPackage,8);
				daoObj.setProcInValue(nProcIndex, "wardCode", strWardCode,3);
				daoObj.setProcInValue(nProcIndex, "groupId", strGroupId,4);
				daoObj.setProcInValue(nProcIndex, "searchtrfname", strTariffCode,5);
				daoObj.setProcInValue(nProcIndex, "searchMode", "1",6);
				daoObj.setProcInValue(nProcIndex, "advance_flag", "1",9);
				daoObj.setProcInValue(nProcIndex, "modeVal", mode,10);
				daoObj.setProcInValue(nProcIndex, "end_date", "",11);
				daoObj.setProcOutValue(nProcIndex, "err", 1,12);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,13);

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

			e.printStackTrace();
			
			voObj
					.setStrMsgString("CashCollectionTransDAO.getOffLineTariffListByCode() --> "
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
			CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.proc_consultant_name(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospCode = voObj.getStrHospitalCode();

		String strErr = "";

		try {

			daoObj = new HisDAO("Cash Collection Transaction",
					"CashCollectionTransDAO.getOffLineDiscountApprovalList");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "deptunitCode", "");
			daoObj.setProcInValue(nProcIndex, "seatId", "");
			
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospCode);
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);

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
					.setStrMsgString("CashCollectionTransDAO.getOffLineDiscountApprovalList() --> "
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
	public static void getOffLineDiscountRemarksList(CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.proc_hblt_remarks_mst(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strHospCode = voObj.getStrHospitalCode();

		String strErr = "";

		try {

			daoObj = new HisDAO("Cash Collection Transaction",
					"CashCollectionTransDAO.getOffLineDiscountRemarksList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "rmkstype", "1");
			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospCode);
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);

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
					.setStrMsgString("CashCollectionTransDAO.getOffLineDiscountRemarksList() --> "
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
	public static void getOffLineTariffUnitList(CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_GBLT_UNIT_MST(?,?,?,?,?)}";
		int nProcIndex = 0;

		// String strHospitalCode = voObj.getStrHospitalCode();

		String strErr = "";

		try {

			daoObj = new HisDAO("Cash Collection Transaction",
					"CashCollectionTransDAO.getOffLineTariffUnitList");

			nProcIndex = daoObj.setProcedure(strProcName);
			
			
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),1);
			daoObj.setProcInValue(nProcIndex, "unit_id", voObj.getStrOffLineTariffUnitTempId(),2);
			daoObj.setProcInValue(nProcIndex, "modeval", "1",3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,5);

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

			e.printStackTrace();
			voObj
					.setStrMsgString("CashCollectionTransDAO.getOffLineTariffUnitList() --> "
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
	 * retrieves Without Cr. No. Bill Tariff Details
	 * 
	 * @param voObj
	 */
	public static void getWithoutCrNoBillTariffDetails(
			CashCollectionWithoutCrTransVO voObj) {

		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_BILL_SERVICE_DTL(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try {
			dao = new HisDAO("billing",
					"transactions.CashCollectionTransDAO.getWithoutCrNoBillTariffDetails()");
			nprocIndex = dao.setProcedure(proc_name);
			
			dao.setProcInValue(nprocIndex, "billNo ", voObj.getStrOffLineBillNumber(),1);
			dao.setProcInValue(nprocIndex, "modeval", "2",3);
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospitalCode(),4);
			
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "",2);
			dao.setProcInValue(nprocIndex, "recNo", "",5);
			dao.setProcInValue(nprocIndex, "recType", "",6);
			
			dao.setProcOutValue(nprocIndex, "ERR", 1,7); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2,8); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

				voObj.setOfflineBillTariffList(ws);
			} else {
				voObj.setOfflineBillTariffList(null);
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("CashCollectionTransDAO.getWithoutCrNoBillTariffDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	public static void getRequestNoList(CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_sblt_inbound_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {

			daoObj = new HisDAO("Cash Collection Transaction",
					"CashCollectionTransDAO.getRequestNoList");

			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "mode_type", "5",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),9);
			daoObj.setProcInValue(nProcIndex, "crno", "",2);
			daoObj.setProcInValue(nProcIndex, "chargeTypeId", "",3);
			daoObj.setProcInValue(nProcIndex, "patListType", "",4);
			daoObj.setProcInValue(nProcIndex, "searchStr", "",5);
			daoObj.setProcInValue(nProcIndex, "searchType", "",6);
			daoObj.setProcInValue(nProcIndex, "frmRows", "",7);
			daoObj.setProcInValue(nProcIndex, "toRows", "",8);
			daoObj.setProcInValue(nProcIndex, "deptCode", "",10);
			daoObj.setProcInValue(nProcIndex, "seatId", "0",11);
			daoObj.setProcOutValue(nProcIndex, "err", 1,12);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,13);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				voObj.setStrRequestNoList(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();
			voObj
					.setStrMsgString("CashCollectionTransDAO.getRequestNoList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	
	public static void getGenderList(CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_gender_dtl(?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {

			daoObj = new HisDAO("Cash Collection Transaction",
					"CashCollectionTransDAO.getGenderList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),1);
		
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,3);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				voObj.setWsGenderList(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();
			voObj
					.setStrMsgString("CashCollectionTransDAO.getGenderList() --> "
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
	 * retrieves Guarantor Details
	 * 
	 * @param voObj
	 */
	public static void getGuarantorDetails(CashCollectionWithoutCrTransVO voObj) {

		String strGuarantorDtls = "";
		String strFunc = "";
		strFunc = "{? = call BILL_MST.getGuarentorDtl(?,?)}";
		HisDAO dao = null;
		int nfuncIndex = 0;

		try {
			dao = new HisDAO("billing",
					"transactions.CashCollectionTransDAO.getGuarantorDetails()");

			nfuncIndex = dao.setFunction(strFunc);

			dao.setFuncInValue(nfuncIndex, 2, voObj.getStrOffLineBillNumber());
			dao.setFuncInValue(nfuncIndex, 3, voObj.getStrHospitalCode());
			dao.setFuncOutValue(nfuncIndex, 1);

			dao.executeFunction(nfuncIndex);

			strGuarantorDtls = dao.getFuncString(nfuncIndex);

			voObj.setStrGuarantorHiddenVal(strGuarantorDtls);

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("CashCollectionTransDAO.getGuarantorDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	public static void getWithoutCrNoTariffDetails(CashCollectionWithoutCrTransVO voObj) {
		WebRowSet ws = null;

		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_PATACCOUNT_SERVICE(?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try {
			dao = new HisDAO("billing",
					"transactions.CashCollectionTransDAO .getWithoutCrNoTariffDetails()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo ", voObj
					.getStrOffLineBillNumber());
			dao.setProcInValue(nprocIndex, "accNo ", voObj
					.getStrOffLineAccountNo());
			dao.setProcInValue(nprocIndex, "modeval", "1");
			dao.setProcInValue(nprocIndex, "hosp_code", voObj
					.getStrHospitalCode());
			
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "");
			dao.setProcInValue(nprocIndex, "groupId", "");
			dao.setProcInValue(nprocIndex, "recNo", "");
			dao.setProcInValue(nprocIndex, "recType", "");
			
			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

				voObj.setOfflineBillTariffList(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("CashCollectionTransDAO.getWithoutCrNoTariffDetails() --> "
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
	 * retrieves Bill Listing Details from Out Bound Table
	 * 
	 * @param voObj
	 */
	public static void getPreviousCrNoDetails(
			CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
	 

		String strErr = "";

		String strProcName = "{call pkg_bill_view.proc_prev_patient_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		try {
			daoObj = new HisDAO("Cash Collection Trans ",
					"CashCollectionTransDAO.getPreviousCrNoDetails");

			nProcIndex = daoObj.setProcedure(strProcName);
 
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospitalCode());

			daoObj.setProcInValue(nProcIndex, "prevCR", voObj.getStrPreviousCrNo());
			
			daoObj.setProcOutValue(nProcIndex, "ERR", 1);

			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "ERR");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				WebRowSet ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				
				if(ws != null && ws.next()){
					
					voObj.setStrPreviousCrNoPatientDtls(ws.getString(1));
					
				}else{
					voObj.setStrPreviousCrNoPatientDtls("");
				}
				

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("CashCollectionTransDAO.getPreviousCrNoDetails() --> "
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
	 * retrieves Bill Listing Details from Out Bound Table
	 * 
	 * @param voObj
	 */
	public static void getBillListingDtl_from_OutBound(
			CashCollectionWithoutCrTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strErr = "";

		String strSearchString = voObj.getStrBillSearchString();
		String strSearchType = voObj.getStrBillSearchType();
		
		String strFromRows = voObj.getStrBillFromRow();
		String strToRows = voObj.getStrBillToRow();
		String strModeType = "5";

		if (strSearchString == null || strSearchString.equals(""))
			strSearchString = "";

		String strProcName = "{call pkg_bill_view.Proc_Sblt_Outbound_Dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		try {
			daoObj = new HisDAO("Cash Collection Trans ",
					"CashCollectionTransDAO.getBillListingDtl_from_OutBound");

			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "crNo", "",1);
			
			daoObj.setProcInValue(nProcIndex, "chargeTypeId", "",3);
			
			daoObj.setProcInValue(nProcIndex, "to_be_refund_pkg", "",2);
			
			daoObj.setProcInValue(nProcIndex, "patListType", "",4);
			
			daoObj.setProcInValue(nProcIndex, "modeval", strModeType,9);

			daoObj.setProcInValue(nProcIndex, "searchStr", strSearchString,5);

			daoObj.setProcInValue(nProcIndex, "searchtype", strSearchType,6);	
			
			daoObj.setProcInValue(nProcIndex, "frmRows", strFromRows,7);

			daoObj.setProcInValue(nProcIndex, "toRows", strToRows,8);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),10);

			daoObj.setProcOutValue(nProcIndex, "ERR", 1,11);

			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,12);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "ERR");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setBillSearchList(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("CashCollectionTransDAO.setPatientListingDtl_from_OutBound() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	// Insert Methods

	/**
	 * Insert's Without Cr. No. Receipt Services.
	 * 
	 * @param voObj
	 */
	public static void insertWithoutCrNoReceiptServices(
			CashCollectionWithoutCrTransVO voObj) {

		String strBillNo = "";
		String strDiscAppId = "";

		int nBillFlag = 0;
		int nAppFlag = 0;
		int nInsertedIndex1 = 0;
		int nInsertedIndex2 = 0;

		HisDAO dao = null;
		PrimaryKeyDAO pKey = null;
		PaymentDAO paymentDao = null;
		PrimaryKeyLogDAO pKeyLogDao = null;

		try {

			dao = new HisDAO("Bill Transaction", "CashCollectionTransDAO.insertWithoutCrNoReceiptServices");

			paymentDao = new PaymentDAO();
			pKey = new PrimaryKeyDAO();
			pKeyLogDao = new PrimaryKeyLogDAO();

			pKey.setStrBlockkey("1");
			pKey.setStrKeyname("BILL_WITHOUT_PUK");
			pKey.setStrHospCode(voObj.getStrHospitalCode());

			pKey.select(dao);

			strBillNo = pKey.getStrPrimrayKeyValue();

			voObj.setStrBillNo(strBillNo);

			nBillFlag = 1;

			CashCollectionWithoutCrTransDAO.getCounterId(voObj);

			String strCounterId = voObj.getStrCounterId().replace("^", "#")
					.split("#")[0];

			nInsertedIndex1 = dao
					.setProcedure("{call pkg_bill_dml.dml_bill_without_crno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			
			dao.setProcInValue(nInsertedIndex1, "modval", "1",1);
			dao.setProcInValue(nInsertedIndex1, "billNo", strBillNo,2);
			dao.setProcInValue(nInsertedIndex1, "prevCrNo", voObj.getStrPreviousCrNo(),4);
			dao.setProcInValue(nInsertedIndex1, "hosp_code", voObj.getStrHospitalCode(),3);
			dao.setProcInValue(nInsertedIndex1, "patName", voObj.getStrGuarantorName(),5);
			dao.setProcInValue(nInsertedIndex1, "patAddress", voObj.getStrGuartorAddress(),6);
			dao.setProcInValue(nInsertedIndex1, "patContactNo", voObj.getStrGuarantorContactNo(),7);
			
			dao.setProcInValue(nInsertedIndex1, "age", voObj.getStrAge(),22);
			dao.setProcInValue(nInsertedIndex1, "ageUnit", voObj.getStrAgeUnit(),23);
			dao.setProcInValue(nInsertedIndex1, "refDoctor", voObj.getStrReferringPhysician(),24);
			dao.setProcInValue(nInsertedIndex1, "genderCode", voObj.getStrGender(),25);	
			
			dao.setProcInValue(nInsertedIndex1, "refDocContactNo", voObj.getStrReferringPhysicianContactNo(),26);
			
			dao.setProcInValue(nInsertedIndex1, "catCode", voObj.getStrOffLineTreatmentCategory(),8);
			dao.setProcInValue(nInsertedIndex1, "netAmt", voObj.getStrOfflineTotalRecAmount(),9);
			dao.setProcInValue(nInsertedIndex1, "actTrfCost", voObj.getStrOfflineTotalActualTariffAmount(),10);

			dao.setProcInValue(nInsertedIndex1, "disAmt", voObj.getStrOfflineTotalDiscountAmount(),11);
			dao.setProcInValue(nInsertedIndex1, "serTaxAmt", voObj.getStrOfflineTotalServiceTaxAmount(),12);
			dao.setProcInValue(nInsertedIndex1, "deptCode", voObj.getStrOffLineRaisingDepartment(),13);

			dao.setProcInValue(nInsertedIndex1, "chargeTypeId", voObj.getStrOffLineHospitalService(),14);
			dao.setProcInValue(nInsertedIndex1, "bServiceId", voObj.getStrOffLineBillingService(),15);
			dao.setProcInValue(nInsertedIndex1, "counter_id", strCounterId,17);
			dao.setProcInValue(nInsertedIndex1, "seatId", voObj.getStrSeatId(),16);
			dao.setProcInValue(nInsertedIndex1, "prevCrDtlFlag", voObj.getStrPreviousCrNoDtlFlag(),27);
			
			dao.setProcInValue(nInsertedIndex1, "reqNo", "",18);
			dao.setProcInValue(nInsertedIndex1, "reqDate", "",19);
			dao.setProcInValue(nInsertedIndex1, "gblReqNo", "",20);
			dao.setProcInValue(nInsertedIndex1, "serviceId","",21);
			
			dao.setProcOutValue(nInsertedIndex1, "err", 1,28);

			dao.execute(nInsertedIndex1, 1);

			if (voObj.getStrOfflineTariffDetailsHidden() != null)
				for (int i = 0, len = voObj.getStrOfflineTariffDetailsHidden().length; i < len; i++) {

					String[] strTariffDtlsVal = voObj
							.getStrOfflineTariffDetailsHidden()[i].replace('^',
							'#').split("#");

					String strTempTariffId = strTariffDtlsVal[0];
					String strTempGroupId = strTariffDtlsVal[1];
					// String strTempTariffRate = strTariffDtlsVal[3];
					String strTempRate = strTariffDtlsVal[4];
					String strUnit = strTariffDtlsVal[5];
					// String strTempBaseUnitVal = strTariffDtlsVal[6];
					// String strTempIsPackage = strTariffDtlsVal[7];
					// String strTempIsAdvance = strTariffDtlsVal[8];
					String strTempIsRefundable = strTariffDtlsVal[9];
					// String strTempDefaultRate = strTariffDtlsVal[10];
					String strTempTariffActualRate = strTariffDtlsVal[11];
					// String strTempMaxDiscount = strTariffDtlsVal[12];
					String strTempServiceTax = strTariffDtlsVal[13];
					String strTempServiceId = strTariffDtlsVal[15];
					String strTempGTariffId = strTariffDtlsVal[16];

					nInsertedIndex2 = dao
							.setProcedure("{call pkg_bill_dml.dml_bill_service_without_crno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

					
					dao.setProcInValue(nInsertedIndex2, "modval", "1",1);
					dao.setProcInValue(nInsertedIndex2, "billNo", strBillNo,3);
					
					dao.setProcInValue(nInsertedIndex2, "prevCrNo", voObj.getStrPreviousCrNo(),29);
					
					dao.setProcInValue(nInsertedIndex2, "hosp_code", voObj.getStrHospitalCode(),2);
					dao.setProcInValue(nInsertedIndex2, "catCode", voObj.getStrOffLineTreatmentCategory(),4);
					dao.setProcInValue(nInsertedIndex2, "deptCode", voObj.getStrOffLineRaisingDepartment(),5);
					dao.setProcInValue(nInsertedIndex2, "grpId",strTempGroupId,6);
					dao.setProcInValue(nInsertedIndex2, "trfId",strTempTariffId,7);
					dao.setProcInValue(nInsertedIndex2, "billQty", voObj.getStrOfflineTariffQty()[i],8);
					dao.setProcInValue(nInsertedIndex2, "billQtyUnitId", voObj.getStrOfflineTariffUnit()[i].replace("^", "#")
							.split("#")[0],9);
					dao.setProcInValue(nInsertedIndex2, "rate", strTempRate,10);
					dao.setProcInValue(nInsertedIndex2, "actRate",strTempTariffActualRate,11);
					dao.setProcInValue(nInsertedIndex2, "rateUnitId", strUnit,12);
					dao.setProcInValue(nInsertedIndex2, "serTax",strTempServiceTax,13);
					dao.setProcInValue(nInsertedIndex2, "serTaxAmt", voObj.getStrOfflineTariffServiceTaxAmtVal()[i],14);
					dao.setProcInValue(nInsertedIndex2, "trfNetAmt", voObj.getStrOfflineTariffNetAmount()[i],15);

					dao.setProcInValue(nInsertedIndex2, "disAmt", voObj.getStrOfflineTariffDiscountAmtVal()[i],18);

					if (voObj.getStrOfflineTariffDiscountConfigDetails() != null
							&& !voObj.getStrOfflineTariffDiscount()[i]
									.equals("0")) {

						String[] strDiscountDtlsValue = voObj
								.getStrOfflineTariffDiscountConfigDetails()[i]
								.replace(',', '#').split("#");

						String strTempDiscUnit = strDiscountDtlsValue[0];
						String strTempDiscType = strDiscountDtlsValue[1];
						String strTempDiscBy = strDiscountDtlsValue[2];
						String strTempDiscReason = strDiscountDtlsValue[4];
						String strTempDiscDate = strDiscountDtlsValue[5];

						if (!voObj.getStrOfflineTariffDiscount()[i].equals("0")) {
							pKey.setStrKeyname("APPROVAL");
							pKey.setStrBlockkey("1");
							pKey.setStrHospCode(voObj.getStrHospitalCode());
							pKey.select(dao);

							strDiscAppId = pKey.getStrPrimrayKeyValue();

							if (!strDiscAppId.equals("")
									|| strDiscAppId != null) {
								nAppFlag = 1;
							}

							dao.setProcInValue(nInsertedIndex2, "appId",strDiscAppId,16);
						} else {
							dao.setProcInValue(nInsertedIndex2, "appId", "",16);
						}
						dao.setProcInValue(nInsertedIndex2, "disUnit",strTempDiscUnit,17);
						dao.setProcInValue(nInsertedIndex2, "disType",strTempDiscType,19);
						dao.setProcInValue(nInsertedIndex2, "disBy",strTempDiscBy,20);
						dao.setProcInValue(nInsertedIndex2, "disReason",strTempDiscReason,21);
						dao.setProcInValue(nInsertedIndex2, "disDate",strTempDiscDate,22);

					} else {

						dao.setProcInValue(nInsertedIndex2, "appId", "0",16);
						dao.setProcInValue(nInsertedIndex2, "disUnit", "0",17);
						dao.setProcInValue(nInsertedIndex2, "disType", "",19);
						dao.setProcInValue(nInsertedIndex2, "disBy", "",20);
						dao.setProcInValue(nInsertedIndex2, "disReason", "",21);
						dao.setProcInValue(nInsertedIndex2, "disDate", "",22);
					}

					dao.setProcInValue(nInsertedIndex2, "chargeTypeId", voObj.getStrOffLineHospitalService(),23);
					dao.setProcInValue(nInsertedIndex2, "bServiceId", voObj.getStrOffLineBillingService(),24);
					dao.setProcInValue(nInsertedIndex2, "serviceId",strTempServiceId,25);
					dao.setProcInValue(nInsertedIndex2, "gblTrfId",	strTempGTariffId,26);
					dao.setProcInValue(nInsertedIndex2, "isRefund",	strTempIsRefundable,27);
					dao.setProcInValue(nInsertedIndex2, "seatId", voObj.getStrSeatId(),28);

					dao.setProcInValue(nInsertedIndex2, "reqNo", "",30);
					
					// output value
					dao.setProcOutValue(nInsertedIndex2, "err", 1,31);

					dao.execute(nInsertedIndex2, 1);
				}

			int nSlNo = 0;
			int nPaymentLenght = voObj.getStrOfflinePaymentMode().length;

			for (int j = 0; j < nPaymentLenght; j++) {

				nSlNo = nSlNo + 1;

				paymentDao.setStrBillNo(strBillNo);

				paymentDao.setStrPreviousPuk(voObj.getStrPreviousCrNo());
				
				paymentDao.setStrPaymentMode(voObj.getStrOfflinePaymentMode()[j]);
				paymentDao.setStrRecieptNo("1");
				paymentDao.setStrSerialNo(String.valueOf(nSlNo));
				paymentDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
				paymentDao.setStrBServiceId(voObj.getStrOffLineBillingService());

				String payDtl[] = { "", "" };

				if (voObj.getStrOfflinePaymentDtls() != null) {
					paymentDao.setStrPaymentDetails(voObj
							.getStrOfflinePaymentDtls()[j]);
					payDtl = voObj.getStrOfflinePaymentDtls()[j].replace(",",
							"#").split("#");
				} else {
					paymentDao.setStrPaymentDetails("");
				}

				if (voObj.getStrOfflinePaymentMode()[j].equals("2")
						|| voObj.getStrOfflinePaymentMode()[j].equals("3")) {
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[j].equals("4")
						|| voObj.getStrOfflinePaymentMode()[j].equals("5")) {
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[j].equals("6")) {

					paymentDao.setStrPaymentModeNo(payDtl[0]);
				} else {
					paymentDao.setStrPaymentModeNo("");
					paymentDao.setStrPaymentStatus("1");
				}

				paymentDao.setStrRecieptType("1");
				paymentDao.setStrRecieptAmount(voObj.getStrOfflineAmount()[j]);

				paymentDao.setStrPuk(voObj.getStrCrNo());
				paymentDao.setStrProcessedBy("0");
				paymentDao.setStrCounterId(strCounterId);
				paymentDao.setStrSeatId(voObj.getStrSeatId());
				paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
				paymentDao.setStrIsValid("1");
				paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());

				paymentDao.insert(dao);
			}

			synchronized (dao) {
				dao.fire();
			}

		} catch (Exception e) {

			e.printStackTrace();
			
			String err = "err:" + e.getMessage();

			voObj
					.setStrMsgString("CashCollectionTransDAO.insertWithoutCrNoReceiptServices()--> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

			if (nBillFlag == 1) {

				try {
					pKeyLogDao.setStrKeyname("BILL_WITHOUT_PUK");
					pKeyLogDao.setStrStartkey(strBillNo);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1) {

					voObj
							.setStrMsgString("CashCollectionTransDAO.insertWithoutCrNoReceiptServices()--> "
									+ e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

			if (nAppFlag == 1) {

				try {
					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrStartkey(strDiscAppId);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1) {

					voObj
							.setStrMsgString("CashCollectionTransDAO.insertWithoutCrNoReceiptServices()--> "
									+ e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

			pKey = null;
			pKeyLogDao = null;
			paymentDao = null;
		}

	}

	public static void insertWithoutCrNoRefundServices(
			CashCollectionWithoutCrTransVO voObj) {

		int nAppFlag = 0;

		String strAppId = "0";

		int nInsertedIndex1 = 0;
		int nInsertedIndex2 = 0;

		HisDAO dao = null;

		PrimaryKeyDAO pKey = null;
		PrimaryKeyLogDAO pKeyLogDao = null;
		PaymentDAO paymentDao = null;

		try {

			dao = new HisDAO("Bill Transaction", "CashCollectionTransDAO.insertWithoutCrNoRefundServices");
			pKey = new PrimaryKeyDAO();
			pKeyLogDao = new PrimaryKeyLogDAO();
			paymentDao = new PaymentDAO();

			String strPatName = voObj.getStrGuarantorHiddenVal().replace("^",
					"#").split("#")[0];

			CashCollectionWithoutCrTransDAO.getCounterId(voObj);

			String strCounterId = voObj.getStrCounterId().replace("^", "#")
					.split("#")[0];

			CashCollectionWithoutCrTransDAO.getRefundReceiptNo(voObj);

			String strRefundReceiptNo = voObj.getStrRefundReceiptNo();

			pKey.setStrKeyname("APPROVAL");
			pKey.setStrBlockkey("1");
			pKey.setStrHospCode(voObj.getStrHospitalCode());
			pKey.select(dao);

			strAppId = pKey.getStrPrimrayKeyValue();

			if (!strAppId.equals("") || strAppId != null) {
				nAppFlag = 1;
			}

			nInsertedIndex1 = dao
					.setProcedure("{call pkg_bill_dml.Dml_Refund_Without_Crno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			 
					
			dao.setProcInValue(nInsertedIndex1, "modval", "1",1);

			dao.setProcInValue(nInsertedIndex1, "billNo", voObj.getStrBillNo(),4);
			dao.setProcInValue(nInsertedIndex1, "hosp_code", voObj.getStrHospitalCode(),2);

			dao.setProcInValue(nInsertedIndex1, "patName", strPatName,3);

			dao.setProcInValue(nInsertedIndex1, "refRecNo", strRefundReceiptNo,5);
			dao.setProcInValue(nInsertedIndex1, "refAppId", strAppId,6);

			dao.setProcInValue(nInsertedIndex1, "refundDate", voObj.getStrOfflineRefundDate(),7);
			dao.setProcInValue(nInsertedIndex1, "refundBy", voObj.getStrOffLineRefundBy(),8);
			dao.setProcInValue(nInsertedIndex1, "refundReason", voObj.getStrOffLineRefundReasonText(),9);

			dao.setProcInValue(nInsertedIndex1, "catCode", voObj.getStrOffLineTreatmentCategory(),14);
			dao.setProcInValue(nInsertedIndex1, "netAmt", voObj.getStrOfflineTotalRecAmount(),15);

			dao.setProcInValue(nInsertedIndex1, "actTrfCost", voObj.getStrOfflineRefundTotalActualTariffAmount(),16);
			dao.setProcInValue(nInsertedIndex1, "disAmt", voObj.getStrOfflineRefundTotalDiscountAmount(),17);
			dao.setProcInValue(nInsertedIndex1, "serTaxAmt", voObj.getStrOfflineRefundTotalServiceTaxAmount(),18);
			dao.setProcInValue(nInsertedIndex1, "penAmt", voObj.getStrOfflineRefundTotalPenaltyAmount(),19);

			dao.setProcInValue(nInsertedIndex1, "deptCode", voObj.getStrOffLineRaisingDepartment(),20);
			dao.setProcInValue(nInsertedIndex1, "chargeTypeId", voObj.getStrOffLineHospitalService(),21);

			dao.setProcInValue(nInsertedIndex1, "bServiceId", voObj.getStrOffLineBillingService(),23);

			dao.setProcInValue(nInsertedIndex1, "seatId", voObj.getStrSeatId(),26);
			dao.setProcInValue(nInsertedIndex1, "moduleId", voObj.getStrModuleId(),27);
			
			dao.setProcInValue(nInsertedIndex1, "patAccNo", "",10);
			dao.setProcInValue(nInsertedIndex1, "puk", "",11);
			dao.setProcInValue(nInsertedIndex1, "admNo", "",12);
			dao.setProcInValue(nInsertedIndex1, "episodeCode", "",13);
			dao.setProcInValue(nInsertedIndex1, "wardId", "",22);
			dao.setProcInValue(nInsertedIndex1, "clientPatNo", "",24);
			dao.setProcInValue(nInsertedIndex1, "clientBalAmt", "",25);
			
			
			dao.setProcInValue(nInsertedIndex1, "ipAddr", voObj.getStrIpAddress(),28);

			dao.setProcInValue(nInsertedIndex1, "counter_id", strCounterId,29);
			// output value
			dao.setProcOutValue(nInsertedIndex1, "err", 1,30);

			// keep in batch
			dao.execute(nInsertedIndex1, 1);

			nInsertedIndex2 = dao
					.setProcedure("{call pkg_bill_dml.dml_refund_ser_without_crno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

						
			if (voObj.getStrBillTariffVal() != null)
				for (int i = 0, len = voObj.getStrBillTariffVal().length; i < len; i++) {

					String[] strTariffDtlsVal = voObj.getStrBillTariffVal()[i]
							.replace('^', '#').split("#");

					String strTempTariffId = strTariffDtlsVal[0];
					// String strTempRemQty = strTariffDtlsVal[1].replace("*",
					// "#").split("#")[0];
					// String strTempQtyUnitId = strTariffDtlsVal[2];
					String strTempTariffRate = strTariffDtlsVal[3];
					String strTempRateUnit = strTariffDtlsVal[4];
					String strTempGroupId = strTariffDtlsVal[5];
					String strTempActualRate = strTariffDtlsVal[6];
					String strTempServiceTax = strTariffDtlsVal[7];
					String strTempDiscUnit = strTariffDtlsVal[8];
					String strTempDiscType = strTariffDtlsVal[9];
					String strTempGTariffId = strTariffDtlsVal[10];
					String strTempAppId = strTariffDtlsVal[11];
					// String strTempUnitName = strTariffDtlsVal[12];
					// String strTempBaseUnitVal = strTariffDtlsVal[13];
					// String strTempIsPackage = strTariffDtlsVal[14];
					// String strTempNetAmt = strTariffDtlsVal[15];
					// String strTempPenlty = strTariffDtlsVal[16];
					// String strTempPenltyAmt = strTariffDtlsVal[17];
					String strTempReceiptNo = strTariffDtlsVal[18];
					String strTempSServiceId = strTariffDtlsVal[19];

					dao.setProcInValue(nInsertedIndex2, "modval", "1",1);
					dao.setProcInValue(nInsertedIndex2, "billNo", voObj.getStrBillNo(),4);
					dao.setProcInValue(nInsertedIndex2, "hosp_code", voObj.getStrHospitalCode(),2);

					dao.setProcInValue(nInsertedIndex2, "patName", strPatName,3);

					dao.setProcInValue(nInsertedIndex2, "trfRecNo",	strTempReceiptNo,5);

					dao.setProcInValue(nInsertedIndex2, "refRecNo",strRefundReceiptNo,6);

					dao.setProcInValue(nInsertedIndex2, "catCode", voObj.getStrOffLineTreatmentCategory(),7);
					dao.setProcInValue(nInsertedIndex2, "deptCode", voObj.getStrOffLineRaisingDepartment(),8);
					dao.setProcInValue(nInsertedIndex2, "grpId",strTempGroupId,9);
					dao.setProcInValue(nInsertedIndex2, "trfId",strTempTariffId,10);
					dao.setProcInValue(nInsertedIndex2, "refQty", voObj.getStrBillTariffRefund()[i],11);
					dao.setProcInValue(nInsertedIndex2, "refQtyUnitId", voObj
							.getStrBillTariffUnit()[i].replace("^", "#").split(
							"#")[0],12);
					dao.setProcInValue(nInsertedIndex2, "rate",	strTempTariffRate,13);
					dao.setProcInValue(nInsertedIndex2, "actRate",	strTempActualRate,14);
					dao.setProcInValue(nInsertedIndex2, "rateUnitId",strTempRateUnit,15);
					dao.setProcInValue(nInsertedIndex2, "serTax",strTempServiceTax,16);
					dao.setProcInValue(nInsertedIndex2, "serTaxAmt", voObj.getStrOfflineRefundServiceTaxAmount()[i],17);
					dao.setProcInValue(nInsertedIndex2, "trfNetAmt", voObj.getStrBillTariffRefundAmount()[i],18);

					dao.setProcInValue(nInsertedIndex2, "penelty", voObj.getStrBillTariffPenalty()[i],19);
					dao.setProcInValue(nInsertedIndex2, "penAmt", voObj.getStrOfflineRefundPenaltyAmount()[i],20);

					dao.setProcInValue(nInsertedIndex2, "disAmt", voObj.getStrOfflineRefundDiscountAmount()[i],23);

					dao.setProcInValue(nInsertedIndex2, "appId", strTempAppId,21);

					dao.setProcInValue(nInsertedIndex2, "disUnit",strTempDiscUnit,22);
					dao.setProcInValue(nInsertedIndex2, "disType",strTempDiscType,24);

					dao.setProcInValue(nInsertedIndex2, "chargeTypeId", voObj.getStrOffLineHospitalService(),25);

					dao.setProcInValue(nInsertedIndex2, "bServiceId", voObj.getStrOffLineBillingService(),26);
					dao.setProcInValue(nInsertedIndex2, "serviceId",strTempSServiceId,27);
					dao.setProcInValue(nInsertedIndex2, "gblTrfId",	strTempGTariffId,28);

					dao.setProcInValue(nInsertedIndex2, "seatId", voObj.getStrSeatId(),29);

					// output value
					dao.setProcOutValue(nInsertedIndex2, "err", 1,30);

					dao.execute(nInsertedIndex2, 1);

				}

			int nSlNo = 0;
			int nPaymentLenght = voObj.getStrOfflinePaymentMode().length;

			for (int j = 0; j < nPaymentLenght; j++) {

				nSlNo = nSlNo + 1;

				paymentDao.setStrBillNo(voObj.getStrBillNo());

				paymentDao.setStrPreviousPuk(voObj.getStrPreviousCrNo());
				
				paymentDao
						.setStrPaymentMode(voObj.getStrOfflinePaymentMode()[j]);
				paymentDao.setStrRecieptNo(strRefundReceiptNo);
				paymentDao.setStrSerialNo(String.valueOf(nSlNo));
				paymentDao.setStrChargeTypeId(voObj
						.getStrOffLineHospitalService());
				paymentDao
						.setStrBServiceId(voObj.getStrOffLineBillingService());

				String payDtl[] = { "", "" };

				if (voObj.getStrOfflinePaymentDtls() != null) {
					paymentDao.setStrPaymentDetails(voObj
							.getStrOfflinePaymentDtls()[j]);
					payDtl = voObj.getStrOfflinePaymentDtls()[j].replace(",",
							"#").split("#");
				} else {
					paymentDao.setStrPaymentDetails("");
				}

				if (voObj.getStrOfflinePaymentMode()[j].equals("2")
						|| voObj.getStrOfflinePaymentMode()[j].equals("3")) {
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[j].equals("4")
						|| voObj.getStrOfflinePaymentMode()[j].equals("5")) {
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");

				} else if (voObj.getStrOfflinePaymentMode()[j].equals("6")) {

					paymentDao.setStrPaymentModeNo(payDtl[0]);
				} else {
					paymentDao.setStrPaymentModeNo("");
					paymentDao.setStrPaymentStatus("1");
				}

				paymentDao.setStrRecieptType("2");
				paymentDao.setStrRecieptAmount(voObj.getStrOfflineAmount()[j]);

				paymentDao.setStrPuk(voObj.getStrCrNo());
				paymentDao.setStrProcessedBy("0");
				paymentDao.setStrCounterId(strCounterId);
				paymentDao.setStrSeatId(voObj.getStrSeatId());
				paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
				paymentDao.setStrIsValid("1");
				paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());

				paymentDao.insert(dao);
			}

			synchronized (dao) {
				dao.fire();
			}

		} catch (Exception e) {
			e.printStackTrace();
			String err = "err:" + e.getMessage();

			voObj
					.setStrMsgString("CashCollectionTransDAO.insertWithoutCrNoRefundServices()--> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

			if (nAppFlag == 1) {

				try {
					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrStartkey(strAppId);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} catch (Exception e1) {

					voObj
							.setStrMsgString("CashCollectionTransDAO.insertWithoutCrNoRefundServices()--> "
									+ e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");

				}

			}

		} finally {

			if (dao != null) {
				dao.free();
				dao = null;
			}

			pKey = null;
			pKeyLogDao = null;
			paymentDao = null;
		}

	}

	

	public static void insertOnlineWithoutCrNoReceiptServices(
			CashCollectionWithoutCrTransVO voObj) {

		String strBillNo = "";
		String strDiscAppId = "";

		int nBillFlag = 0;
		int nAppFlag = 0;
		int nInsertedIndex1 = 0;
		int nInsertedIndex2 = 0;

		HisDAO dao = null;
		PrimaryKeyDAO pKey = null;
		PaymentDAO paymentDao = null;
		PrimaryKeyLogDAO pKeyLogDao = null;

		try {

			dao = new HisDAO("Bill Transaction", "CashCollectionTransDAO.insertOnlineWithoutCrNoReceiptServices");

			paymentDao = new PaymentDAO();
			pKey = new PrimaryKeyDAO();
			pKeyLogDao = new PrimaryKeyLogDAO();

			pKey.setStrBlockkey("1");
			pKey.setStrKeyname("BILL_WITHOUT_PUK");
			pKey.setStrHospCode(voObj.getStrHospitalCode());

			pKey.select(dao);

			strBillNo = pKey.getStrPrimrayKeyValue();

			voObj.setStrBillNo(strBillNo);

			nBillFlag = 1;

			CashCollectionWithoutCrTransDAO.getCounterId(voObj);

			String strCounterId = voObj.getStrCounterId().replace("^", "#")
					.split("#")[0];

			nInsertedIndex1 = dao
					.setProcedure("{call pkg_bill_dml.dml_bill_without_crno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			dao.setProcInValue(nInsertedIndex1, "modval", "1",1);
			dao.setProcInValue(nInsertedIndex1, "billNo", strBillNo,2);
			dao.setProcInValue(nInsertedIndex1, "hosp_code", voObj.getStrHospitalCode(),3);	
			dao.setProcInValue(nInsertedIndex1, "prevCrNo", voObj.getStrPreviousCrNo(),4);
			dao.setProcInValue(nInsertedIndex1, "patName", voObj.getStrGuarantorName(),5);
			dao.setProcInValue(nInsertedIndex1, "patAddress", voObj.getStrGuartorAddress(),6);
			dao.setProcInValue(nInsertedIndex1, "patContactNo", voObj.getStrGuarantorContactNo(),7);			
			dao.setProcInValue(nInsertedIndex1, "catCode", voObj.getStrOffLineTreatmentCategory(),8);
			dao.setProcInValue(nInsertedIndex1, "netAmt", voObj.getStrOfflineTotalRecAmount(),9);
			dao.setProcInValue(nInsertedIndex1, "actTrfCost", voObj.getStrTotalTariffActualAmount(),10);
			dao.setProcInValue(nInsertedIndex1, "disAmt", voObj.getStrTotalTariffDiscountAmount(),11);
			dao.setProcInValue(nInsertedIndex1, "serTaxAmt", voObj.getStrTotalTariffServiceTaxAmount(),12);
			dao.setProcInValue(nInsertedIndex1, "deptCode", voObj.getStrOffLineRaisingDepartment(),13);
			dao.setProcInValue(nInsertedIndex1, "chargeTypeId", voObj.getStrOffLineHospitalService(),14);
			dao.setProcInValue(nInsertedIndex1, "bServiceId", voObj.getStrOffLineBillingService(),15);
			dao.setProcInValue(nInsertedIndex1, "seatId", voObj.getStrSeatId(),16);
			dao.setProcInValue(nInsertedIndex1, "counter_id", strCounterId,17);			
			dao.setProcInValue(nInsertedIndex1, "reqNo", voObj.getStrRequestNo(),18);
			dao.setProcInValue(nInsertedIndex1, "reqDate", voObj.getStrRequestDate(),19);
			dao.setProcInValue(nInsertedIndex1, "gblReqNo", voObj.getStrGlobalRequestNo(),20);
			dao.setProcInValue(nInsertedIndex1, "serviceId", voObj.getStrService(),21);
			dao.setProcInValue(nInsertedIndex1, "age", voObj.getStrAge(),22);
			dao.setProcInValue(nInsertedIndex1, "ageUnit", voObj.getStrAgeUnit(),23);
			dao.setProcInValue(nInsertedIndex1, "refDoctor", voObj.getStrReferringPhysician(),24);
			dao.setProcInValue(nInsertedIndex1, "genderCode", voObj.getStrGender(),25);
			dao.setProcInValue(nInsertedIndex1, "refDocContactNo", voObj.getStrReferringPhysicianContactNo(),26);			
			dao.setProcInValue(nInsertedIndex1, "prevCrDtlFlag", "",27);			
			dao.setProcOutValue(nInsertedIndex1, "err", 1,28);		

			dao.execute(nInsertedIndex1, 1);

			if (voObj.getStrTariffDetailsId() != null)
				for (int i = 0, len = voObj.getStrTariffDetailsId().length; i < len; i++) 
				{

					String[] strTariffDtlsVal = voObj.getStrTariffDetailsId()[i]
							.replace('^', '#').split("#");

					String strTempTariffId = strTariffDtlsVal[0];
					String strTempGroupId = strTariffDtlsVal[5];
					String strTempRate = strTariffDtlsVal[3];
					String strUnit = strTariffDtlsVal[4];
					String strTempIsRefundable = "0"; // strTariffDtlsVal[9];
					String strTempTariffActualRate = strTariffDtlsVal[6];
					String strTempServiceTax = strTariffDtlsVal[7];
					String strTempGTariffId = strTariffDtlsVal[10];
					String strTempServiceId = strTariffDtlsVal[strTariffDtlsVal.length - 1];

					nInsertedIndex2 = dao
							.setProcedure("{call pkg_bill_dml.dml_bill_service_without_crno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

					dao.setProcInValue(nInsertedIndex2, "modval", "1",1);
					dao.setProcInValue(nInsertedIndex2, "hosp_code", voObj.getStrHospitalCode(),2);
					dao.setProcInValue(nInsertedIndex2, "billNo", strBillNo,3);					
					dao.setProcInValue(nInsertedIndex2, "catCode", voObj.getStrOffLineTreatmentCategory(),4);
					dao.setProcInValue(nInsertedIndex2, "deptCode", voObj.getStrOffLineRaisingDepartment(),5);
					dao.setProcInValue(nInsertedIndex2, "grpId",strTempGroupId,6);
					dao.setProcInValue(nInsertedIndex2, "trfId",strTempTariffId,7);
					dao.setProcInValue(nInsertedIndex2, "billQty", voObj.getStrTariffBilledQty()[i],8);
					dao.setProcInValue(nInsertedIndex2, "billQtyUnitId", voObj.getStrBillTariffUnit()[i].replace("^", "#").split("#")[0],9);
					dao.setProcInValue(nInsertedIndex2, "rate", strTempRate,10);
					dao.setProcInValue(nInsertedIndex2, "actRate",strTempTariffActualRate,11);
					dao.setProcInValue(nInsertedIndex2, "rateUnitId", strUnit,12);
					dao.setProcInValue(nInsertedIndex2, "serTax",strTempServiceTax,13);
					dao.setProcInValue(nInsertedIndex2, "serTaxAmt", voObj.getStrTariffServiceTaxAmt()[i],14);
					dao.setProcInValue(nInsertedIndex2, "trfNetAmt", voObj.getStrTariffNetAmt()[i],15);
					

					if (voObj.getStrOfflineTariffDiscountConfigDetails() != null&& Double.parseDouble(voObj.getStrTariffDiscountAmt()[i]) != 0) 
					{
						String[] strDiscountDtlsValue = voObj.getStrOfflineTariffDiscountConfigDetails()[i].replace(",", "#").split("#");
						String strTempDiscUnit = strDiscountDtlsValue[0];
						String strTempDiscType = strDiscountDtlsValue[1];
						String strTempDiscBy = strDiscountDtlsValue[2];
						String strTempDiscReason = strDiscountDtlsValue[4];
						String strTempDiscDate = strDiscountDtlsValue[5];

						if (strDiscountDtlsValue.length > 1) 
						{
							pKey.setStrKeyname("APPROVAL");
							pKey.setStrBlockkey("1");
							pKey.setStrHospCode(voObj.getStrHospitalCode());
							pKey.select(dao);

							strDiscAppId = pKey.getStrPrimrayKeyValue();

							if (!strDiscAppId.equals("")|| strDiscAppId != null) 
							{
								nAppFlag = 1;
							}

							dao.setProcInValue(nInsertedIndex2, "appId",strDiscAppId,16);
						} 
						else 
						{
							dao.setProcInValue(nInsertedIndex2, "appId", "",16);
						}
						dao.setProcInValue(nInsertedIndex2, "disUnit",strTempDiscUnit,17);
						dao.setProcInValue(nInsertedIndex2, "disAmt", voObj.getStrTariffDiscountAmt()[i],18);						
						dao.setProcInValue(nInsertedIndex2, "disType",strTempDiscType,19);
						dao.setProcInValue(nInsertedIndex2, "disBy",strTempDiscBy,20);
						dao.setProcInValue(nInsertedIndex2, "disReason",strTempDiscReason,21);
						dao.setProcInValue(nInsertedIndex2, "disDate",strTempDiscDate,22);
					} 
					else 
					{
						dao.setProcInValue(nInsertedIndex2, "appId", "0",16);
						dao.setProcInValue(nInsertedIndex2, "disUnit", "0",17);
						dao.setProcInValue(nInsertedIndex2, "disAmt", voObj.getStrTariffDiscountAmt()[i],18);				
						dao.setProcInValue(nInsertedIndex2, "disType", "",19);
						dao.setProcInValue(nInsertedIndex2, "disBy", "",20);
						dao.setProcInValue(nInsertedIndex2, "disReason", "",21);
						dao.setProcInValue(nInsertedIndex2, "disDate", "",22);
					}				
					
					dao.setProcInValue(nInsertedIndex2, "chargeTypeId", voObj.getStrOffLineHospitalService(),23);
					dao.setProcInValue(nInsertedIndex2, "bServiceId", voObj.getStrOffLineBillingService(),24);
					dao.setProcInValue(nInsertedIndex2, "serviceId",strTempServiceId,25);
					dao.setProcInValue(nInsertedIndex2, "gblTrfId",strTempGTariffId,26);
					dao.setProcInValue(nInsertedIndex2, "isRefund",strTempIsRefundable,27);
					dao.setProcInValue(nInsertedIndex2, "seatId", voObj.getStrSeatId(),28);
					dao.setProcInValue(nInsertedIndex2, "prevCrNo", "",29);
					dao.setProcInValue(nInsertedIndex2, "reqNo", voObj.getStrRequestNo(),30);
					dao.setProcOutValue(nInsertedIndex2, "err", 1,31);

					dao.execute(nInsertedIndex2, 1);
				}

			int nSlNo = 0;
			int nPaymentLenght = voObj.getStrOfflinePaymentMode().length;

			for (int j = 0; j < nPaymentLenght; j++) 
			{
				nSlNo = nSlNo + 1;
				paymentDao.setStrBillNo(strBillNo);
				paymentDao.setStrPaymentMode(voObj.getStrOfflinePaymentMode()[j]);
				paymentDao.setStrRecieptNo("1");
				paymentDao.setStrSerialNo(String.valueOf(nSlNo));
				paymentDao.setStrChargeTypeId(voObj.getStrOffLineHospitalService());
				paymentDao.setStrBServiceId(voObj.getStrOffLineBillingService());

				String payDtl[] = { "", "" };

				if (voObj.getStrOfflinePaymentDtls() != null) 
				{
					paymentDao.setStrPaymentDetails(voObj.getStrOfflinePaymentDtls()[j]);
					payDtl = voObj.getStrOfflinePaymentDtls()[j].replace(",","#").split("#");
				} 
				else 
				{
					paymentDao.setStrPaymentDetails("");
				}

				if (voObj.getStrOfflinePaymentMode()[j].equals("2") || voObj.getStrOfflinePaymentMode()[j].equals("3")) 
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");
				} 
				else if (voObj.getStrOfflinePaymentMode()[j].equals("4") || voObj.getStrOfflinePaymentMode()[j].equals("5")) 
				{
					paymentDao.setStrPaymentModeNo(payDtl[1]);
					paymentDao.setStrPaymentStatus("0");
				} 
				else if (voObj.getStrOfflinePaymentMode()[j].equals("6")) 
				{
					paymentDao.setStrPaymentModeNo(payDtl[0]);
				} 
				else 
				{
					paymentDao.setStrPaymentModeNo("");
					paymentDao.setStrPaymentStatus("1");
				}

				paymentDao.setStrRecieptType("1");
				paymentDao.setStrRecieptAmount(voObj.getStrOfflineAmount()[j]);

				paymentDao.setStrPuk(voObj.getStrCrNo());
				paymentDao.setStrProcessedBy("0");
				paymentDao.setStrCounterId(strCounterId);
				paymentDao.setStrSeatId(voObj.getStrSeatId());
				paymentDao.setStrHospitalCode(voObj.getStrHospitalCode());
				paymentDao.setStrIsValid("1");
				paymentDao.setStrNetAmount(voObj.getStrOfflineTotalRecAmount());

				paymentDao.insert(dao);
			}

			synchronized (dao) 
			{
				dao.fire();
			}
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			String err = "err:" + e.getMessage();
			voObj.setStrMsgString("CashCollectionTransDAO.insertOnlineWithoutCrNoReceiptServices()--> "+ e.getMessage());
			voObj.setStrMsgType("1");

			if (nBillFlag == 1) 
			{
				try 
				{
					pKeyLogDao.setStrKeyname("BILL_WITHOUT_PUK");
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
					voObj.setStrMsgString("CashCollectionTransDAO.insertWithoutCrNoReceiptServices()--> "+ e1.getMessage() + "-->" + err);
					voObj.setStrMsgType("1");
				}
			}

			if (nAppFlag == 1) 
			{
				try 
				{
					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrStartkey(strDiscAppId);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrError(err);
					pKeyLogDao.setStrHospCode(voObj.getStrHospitalCode());
					pKeyLogDao.setStrSeatid(voObj.getStrSeatId());
					// Calling Insert Method of Primary Key Log
					pKeyLogDao.insert(dao);

				} 
				catch (Exception e1) 
				{
					voObj.setStrMsgString("CashCollectionTransDAO.insertOnlineWithoutCrNoReceiptServices()--> "+ e1.getMessage() + "-->" + err);
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
			pKey = null;
			pKeyLogDao = null;
			paymentDao = null;
		}
	}

	
	/**
	 * executes the BILL_MST.getCounterDtl() by passing Module Id, IP Address,
	 * Hospital Code and Mode 1. which generates the Counter and set to Value
	 * Object's counterId.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public static void getCounterId(CashCollectionWithoutCrTransVO voObj) {

		String strCounterId = "";
		String strMode = "1";

		String strFunc = "";
		strFunc = "{? = call BILL_MST.getCounterDtl(?,?,?,?)}";
		HisDAO dao = null;
		int nfuncIndex = 0;

		try {
			dao = new HisDAO("billing",
					"transactions.CashCollectionTransDAO .getCounterId()");

			nfuncIndex = dao.setFunction(strFunc);

			dao.setFuncInValue(nfuncIndex, 2, voObj.getStrModuleId());
			dao.setFuncInValue(nfuncIndex, 3, voObj.getStrIpAddress());
			dao.setFuncInValue(nfuncIndex, 4, voObj.getStrHospitalCode());
			dao.setFuncInValue(nfuncIndex, 5, strMode);
			dao.setFuncOutValue(nfuncIndex, 1);

			dao.executeFunction(nfuncIndex);

			strCounterId = dao.getFuncString(nfuncIndex);

			voObj.setStrCounterId(strCounterId);

		} catch (Exception e) {
			e.printStackTrace();
			voObj.setStrMsgString("CashCollectionTransDAO.getCounterId() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	public static void getRefundReceiptNo(CashCollectionWithoutCrTransVO voObj) {

		String strRefundReceiptNo = "";

		String strFunc = "";
		strFunc = "{? = call BILL_MST.get_refund_receiptno(?,?)}";
		HisDAO dao = null;
		int nfuncIndex = 0;

		try {
			dao = new HisDAO("billing",
					"transactions.CashCollectionTransDAO .getRefundReceiptNo()");

			nfuncIndex = dao.setFunction(strFunc);

			dao.setFuncInValue(nfuncIndex, 2, voObj.getStrHospitalCode());
			dao.setFuncInValue(nfuncIndex, 3, voObj.getStrBillNo());
			dao.setFuncOutValue(nfuncIndex, 3);

			dao.executeFuncForNumeric(nfuncIndex);

			strRefundReceiptNo = dao.getFuncNumeric(nfuncIndex);

			voObj.setStrRefundReceiptNo(strRefundReceiptNo);

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("CashCollectionTransDAO.getRefundReceiptNo() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

}
