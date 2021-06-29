package billing.transactions;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.dao.OutBoundDAO;
import billing.dao.PatientAccountDAO;
import billing.dao.PatientAccountServiceDAO;
import billing.dao.PrimaryKeyDAO;
import billing.dao.PrimaryKeyLogDAO;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

public class AddServicesIPDTransDAO {

	public static void getPatientCateogry(AddServicesIPDTransVO vo) {
		String proc_name = "";

		proc_name = "{call PKG_SIMPLE_VIEW.PROC_GBLT_PATIENT_CAT_MST(?,?,?,?,?,?,?,?)}";

		HisDAO dao = null;

		int nprocIndex = 0;

		String strerr = "";

		try {

			dao = new HisDAO("AddServicesIPDTrans",
					"transactions.AddServicesIPDTransDAO.getPatientCateogry(vo)");

			nprocIndex = dao.setProcedure(proc_name);

			// set value
 
			
			dao.setProcInValue(nprocIndex, "modeVal", "1");
			dao
					.setProcInValue(nprocIndex, "hosp_code", BillConfigUtil.SUPER_HOSPITAL_CODE);
			
			dao.setProcInValue(nprocIndex, "puk_no", "");
			dao.setProcInValue(nprocIndex, "charge_type_id", "");
			dao.setProcInValue(nprocIndex, "effect_from", "");
			dao.setProcInValue(nprocIndex, "effect_TO", "");
			
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

				vo.setWsPateintCategory(dao.getWebRowSet(nprocIndex,
						"RESULTSET"));

			} else {
				throw new Exception(strerr);
			}

		}

		catch (Exception e) {

			vo.setStrMsgString("IpdBillManagementTransDAO.PATIENTCATEOGRYCOMBO()"
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
	 * retrieves Off Line Group Combo
	 * 
	 * @param voObj -
	 *            ValueObject
	 */
	public static void getOffLineGroup(AddServicesIPDTransVO voObj) {

		HisDAO daoObj = null;
		 
		String strHospitalCode = voObj.getStrHospitalCode();
		String strChargeTypeId = "2"; // ipd
		String strIsPackageWise = "2";
		
		String strProcName = "{call pkg_bill_view.proc_HBLT_HSERVICE_GROUP_MST(?,?,?,?,?,?)}";

		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("Add Services IPD Transaction",
					"AddServicesIPDTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode);
			daoObj.setProcInValue(nProcIndex, "charge_id", strChargeTypeId);
			daoObj.setProcInValue(nProcIndex, "pkg_wise_group",
					strIsPackageWise);
			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				 				
				voObj.setWsOfflineGroupDetails(daoObj.getWebRowSet(nProcIndex, "RESULTSET"));
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			 
			voObj
					.setStrMsgString("AddServicesIPDTransDAO.getOffLineGroup() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	
	 
	public static void getPreviousAddServices(AddServicesIPDTransVO voObj) {

		HisDAO daoObj = null;
		 
		 
		String strProcName = "{call pkg_bill_view.PROC_PREV_ADDSERVICE_DTLS(?,?,?,?)}";

		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("Add Services IPD Transaction",
					"AddServicesIPDTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "accNo", voObj.getStrAccountNo());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				 				
				voObj.setWsPreviousAddServiceDetails(daoObj.getWebRowSet(nProcIndex, "RESULTSET"));
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			 
			voObj
					.setStrMsgString("AddServicesIPDTransDAO.getPreviousAddServices() --> "
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
	public static void getOffLineTariffList(AddServicesIPDTransVO voObj) {
		HisDAO daoObj = null;
	 
		String strProcName = "";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strGroupId = voObj.getStrGroupId();
		String strChargeTypeId = voObj.getStrChargeTypeId();
		String strCategoryCode = voObj.getStrPatientCategory();
		String strWardCode = voObj.getStrWardCode();
		 
		String strPackage = "2";
 
		String strErr = "";
		String mode = "1";

		try {
			daoObj = new HisDAO("Ipd Bill Management Transaction",
					"IpdBillManagementTransDAO");

		 
					strProcName = "{call PKG_BILL_VIEW.PROC_HBLT_CHARGE_MST(?,?,?,?,?,?,?,?,?,?,?,?)}";
		 
			nProcIndex = daoObj.setProcedure(strProcName);

			// System.out.println(" procedure Name :"+strProcName);

			if (strChargeTypeId != null && strCategoryCode != null) {

				daoObj.setProcInValue(nProcIndex, "chargetypeid",
						strChargeTypeId);
				daoObj.setProcInValue(nProcIndex, "catCode", strCategoryCode);
				daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode);
				daoObj.setProcInValue(nProcIndex, "pkg_flag", strPackage);
				daoObj.setProcInValue(nProcIndex, "advance_flag","");
				daoObj.setProcInValue(nProcIndex, "searchtrfname","");
				daoObj.setProcInValue(nProcIndex, "searchMode","");
 
					daoObj.setProcInValue(nProcIndex, "wardCode", strWardCode);
  
					daoObj.setProcInValue(nProcIndex, "groupId", strGroupId);
 
				daoObj.setProcInValue(nProcIndex, "modeVal", mode);

				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);

				daoObj.executeProcedure(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";
 
				if (strErr.equals("")) {
					voObj.setWsTariffList(daoObj.getWebRowSet(nProcIndex, "resultset"));
 	
				}

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();
			
			voObj
					.setStrMsgString("AddServicesIPDTransDAO.getOffLineTariffList() --> "
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
	 * retrieves Tariff List
	 * 
	 * @param voObj -
	 *            ValueObject
	 */
	public static void getTariffListByCode(AddServicesIPDTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strGroupId = voObj.getStrGroupId();
		String strChargeTypeId = voObj.getStrChargeTypeId();
		String strCategoryCode = voObj.getStrPatientCategory();
		String strWardCode = voObj.getStrWardCode();
	
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
		
		String strPackage = "2";

			
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
				daoObj.setProcInValue(nProcIndex, "searchMode", "");
				daoObj.setProcInValue(nProcIndex, "advance_flag", "");
				daoObj.setProcInValue(nProcIndex, "modeVal", mode);
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);

				daoObj.executeProcedure(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (ws != null) {

					// System.out.println("ws size : "+ws.size());
					voObj.setWsTariffList(ws);
				}
 
		} catch (Exception e) {

			e.printStackTrace();
			
			voObj
					.setStrMsgString("AddServicesIPDTransDAO.getTariffListByCode() --> "
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
	 * retrieves Tariff List
	 * 
	 * @param voObj -
	 *            ValueObject
	 */
	public static void getDefaultTariffList(AddServicesIPDTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();
		String strGroupId = voObj.getStrGroupId();
		String strChargeTypeId = voObj.getStrChargeTypeId();
		String strCategoryCode = voObj.getStrPatientCategory();
		String strWardCode = voObj.getStrWardCode();
			

		if (strChargeTypeId == null )
			strChargeTypeId = "";
		if (strCategoryCode == null )
			strCategoryCode = "";
		
		if (strGroupId == null )
			strGroupId = "";
		if (strWardCode == null)
			strWardCode = "";

		 

		String strErr = "";
		String mode = "4";
		
		String strPackage = "2";

			
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
				daoObj.setProcInValue(nProcIndex, "searchtrfname", "");
				daoObj.setProcInValue(nProcIndex, "searchMode", "");
				daoObj.setProcInValue(nProcIndex, "advance_flag", "");
				daoObj.setProcInValue(nProcIndex, "modeVal", mode);
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);

				daoObj.executeProcedure(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (ws != null) {

					// System.out.println("ws size : "+ws.size());
					voObj.setWsTariffList(ws);
				}
 

		} catch (Exception e) {

			voObj
					.setStrMsgString("AddServicesIPDTransDAO.getDefaultTariffList() --> "
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
	public static void getTariffUnitList(AddServicesIPDTransVO voObj) {

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
					.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "unit_id", voObj.getStrTariffUnitTempId());
			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setWsTariffUnit(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("AddServicesIPDTransDAO.getTariffUnitList() --> "
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
	 * calculates the quantity based on the dates provided
	 * 
	 * @param voObj
	 */
	public static void getCalculatedQuantity(AddServicesIPDTransVO voObj) {

		HisDAO daoObj = null;
	 
		String strFuncName =  "{? = call BILL_MST.calcDefaultQty(?,?,?)}";
		int strFuncIndex = 0;
		 
		try {

			daoObj = new HisDAO("Ipd BillManagement Transaction",
					"IpdBillManagementTransDAO");

			strFuncIndex = daoObj.setFunction(strFuncName);
			
			daoObj.setFuncInValue(strFuncIndex, 2, voObj.getStrDateString() );
			daoObj.setFuncInValue(strFuncIndex, 3, voObj.getStrWardCode());
			daoObj.setFuncInValue(strFuncIndex, 4, voObj.getStrHospitalCode());
			daoObj.setFuncOutValue(strFuncIndex, 3);

			daoObj.executeFuncForNumeric(strFuncIndex);

			String strQty = daoObj.getFuncNumeric(strFuncIndex); 
			
			voObj.setStrQuantity(strQty);
			
		} catch (Exception e) {

			voObj
					.setStrMsgString("AddServicesIPDTransDAO.getCalculatedQuantity() --> "
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
	 * InsertAddServiceTrans(vo) -- > This Method is Used to Insert Data for Add
	 * Service's Transaction Here we use five Table SBLT_PRIMARYKEY_DTL ,
	 * SBLT_PRIMARYKEY_LOG_DTL HBLT_PATACCOUNT_DTL , HBLT_PATACCOUNT_SERVICE_DTL ,
	 * HBLT_PATACCOUNT_PACKAGE_DTL
	 */

	public static boolean InsertAddServiceTrans(AddServicesIPDTransVO vo) {
		// Decleration
		HisDAO dao = null;
		boolean retVal = false;
	  
		
		int compChargeLen = vo.getStrCompChargeCheck().length;
		int length = vo.getStrOfflineTariffName().length;

		// Createing Object for Table Specific DAO
		PrimaryKeyDAO pKeyDao = new PrimaryKeyDAO();
		PrimaryKeyLogDAO pKeyLogDao = new PrimaryKeyLogDAO();
		PatientAccountDAO pActDao = new PatientAccountDAO();
		PatientAccountServiceDAO pActServDao = new PatientAccountServiceDAO();
//		PatientAccountPackageDAO pActPckDao = new PatientAccountPackageDAO();
		OutBoundDAO outBoundDAO = new OutBoundDAO();

		try {
			dao = new HisDAO("billing",
					"transactions.DAObilling.InsertAddServiceTrans()");

			if (length > 0) {
				/** **Here We Generate Primary Key(REQNO)*** */
				// Here we Set Value For DAO
				pKeyDao.setStrKeyname("REQNO");
				pKeyDao.setStrBlockkey("1");
				pKeyDao.setStrHospCode(vo.getStrHospitalCode());
				// Procedure Execute
				pKeyDao.select(dao);
				vo.setStrReqNo(pKeyDao.getStrPrimrayKeyValue());

				// destroy Object
				pKeyDao = null;
			}
			if (vo.getStrReqNo() != null && !vo.getStrReqNo().equals("")) {

				
				for(int j=0 ; j< compChargeLen ; j++){
					
					String slNo = ""+(j+1);
					
		//			DmlAccountAddServiceProcedure(vo, dao, j , slNo);
					
				}
				
				
				for (int i = compChargeLen; i < length; i++) {

					/** *************New Logic Add Here******************** */

		//			DmlAccountAddServiceProcedure(vo, dao, i , "1");

					/** ********************************************* */

				}
				 
				
				outBoundDAO.setStrTransNo(vo.getStrReqNo());
				outBoundDAO.setStrReceiptNo("1");
				outBoundDAO.setStrPatAccNo(vo.getStrAccountNo());
				outBoundDAO.setStrPuk(vo.getStrCrNo());
				outBoundDAO.setStrAdmNo("0");
				outBoundDAO.setStrEpisodeCode("");
				outBoundDAO.setStrPatientCatCode(vo.getStrPatientCategory());
				outBoundDAO.setStrTransAmt("");
				outBoundDAO.setStrDeptCode(vo.getStrChargeTypeId());
				outBoundDAO.setStrChargeTypeId("2");
				outBoundDAO.setStrIpdChargeTypeId("");
				outBoundDAO.setStrBServiceId("11");
				outBoundDAO.setStrSeatId(vo.getStrSeatId());
				outBoundDAO.setStrTransType("1");
				outBoundDAO.setStrWardCode(vo.getStrWardCode());
				outBoundDAO.setStrIsBill("0");
				outBoundDAO.setStrIsOnline("0");
				outBoundDAO.setStrHospitalCode(vo.getStrHospitalCode());
				
				outBoundDAO.insert(dao);
				
				// end --> out bount entry code added by Balasubramaniam M ( 10-Nov-2009 )
				
				
				/** **********( STEP - IV )*********** */

				pActDao.setStrPatAccNo(vo.getStrAccountNo());
				 
				pActDao.setStrNetActualAmt("");
			 
				pActDao.setStrSerTaxNetAmt("");
			 
				pActDao.setStrTariffNetAmt("");
				 

				pActDao.setStrHospitalCode(vo.getStrHospitalCode());
 
				pActDao.update3(dao);

			}
			synchronized (dao) {
				dao.fire(); // Here we Execute in Batch
				retVal = true; // O.Value

			}

		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrErrPrimKeyLog(e.getMessage());
			vo
					.setStrMsgString("IpdBillManagementTransDAO.InsertAddServiceTrans()--> "
							+ e.getMessage());
			vo.setStrMsgType("1");
			retVal = false;

			/**
			 * Here We Call The PrimaryKeyLogProc Procedure To RollBack The
			 * Primary Key Table*
			 */
			try {
				pKeyLogDao.setStrKeyname("REQNO");
				pKeyLogDao.setStrStartkey(vo.getStrReqNo());
				pKeyLogDao.setStrBlockkey("1");
				pKeyLogDao.setStrHospCode(vo.getStrHospitalCode());
				pKeyLogDao.setStrError(vo.getStrErrPrimKeyLog());
				pKeyLogDao.setStrSeatid(vo.getStrSeatId());
				// Calling Insert Method of Primary Key Log
				pKeyLogDao.insert(dao);
			} catch (Exception e1) {

				e1.printStackTrace();

				vo
						.setStrMsgString("IpdBillManagementTransDAO.InsertAddServiceTrans()--> "
								+ e1.getMessage());
				vo.setStrMsgType("1");
				new HisException("billing", "IpdBillMangementTrans",
						"IpdBillManagementTransDAO.InsertAddServiceTrans() --> "
								+ e1.getMessage());
				retVal = false;
			}

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			if (pActDao != null || pKeyLogDao != null /*|| pActPckDao != null*/
					|| pActServDao != null) {
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
	/*public static void DmlAccountAddServiceProcedure(
			AddServicesIPDTransVO vo, HisDAO dao, int i , String slNo) {
		String proc_name = "";
		proc_name = "{call pkg_bill_dml.dml_account_add_service(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nprocIndex = 0;
		try {
			double rate = Double.parseDouble(vo.getStrTariffRate()[i]);
			double baseValue = Double.parseDouble(vo.getStrUnitBaseValue()[i]);
			double qty = Double.parseDouble(vo.getStrBillQty()[i]);
			double serViceTax = Double.parseDouble(vo.getStrServTax()[i]);
			double RateBaseValue = Double
					.parseDouble(vo.getStrRateBaseValue()[i]);
			double tariffActualCost = Double.parseDouble(vo
					.getStrTariffActualCost()[i]);
			double serviceTaxAmt = 0.0;
			double total = 0.0;

			total = (rate / RateBaseValue) * (qty * baseValue);

			serviceTaxAmt = total * (serViceTax / 100);
			String service_tax = Double.toString(serviceTaxAmt);

			double dtrf_amt = Double.parseDouble(vo.getStrNetTariffAmt());
			double dactual_amt = Double.parseDouble(vo
					.getStrNetActualTariffAmt());
			double dser_tax_amt = Double.parseDouble(vo
					.getStrNetServiceTaxAmt());

			dtrf_amt = dtrf_amt + ((qty * baseValue) * (rate / RateBaseValue))
					+ serviceTaxAmt;
			dactual_amt = dactual_amt
					+ ((qty * baseValue) * (tariffActualCost / RateBaseValue));
			dser_tax_amt = dser_tax_amt + serviceTaxAmt;

			vo.setStrNetTariffAmt("" + dtrf_amt);
			vo.setStrNetActualTariffAmt("" + dactual_amt);
			vo.setStrNetServiceTaxAmt("" + dser_tax_amt);

			nprocIndex = dao.setProcedure(proc_name);

			dao.setProcInValue(nprocIndex, "modval", "1"); // 1

			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode()); // 2

			dao.setProcInValue(nprocIndex, "reqNo", vo.getStrReqNo()); // 3

			dao.setProcInValue(nprocIndex, "patAccNo", vo.getStrPatAcctNo()); // 4

			dao.setProcInValue(nprocIndex, "puk", vo.getStrPukNo()); // 5

			dao
					.setProcInValue(nprocIndex, "catCode", vo
							.getStrPatientCatCode()); // 6

			dao.setProcInValue(nprocIndex, "deptCode", vo.getStrDeptId()); // 7

			dao.setProcInValue(nprocIndex, "grpId", vo.getStrGrpId()[i]); // 8

			dao.setProcInValue(nprocIndex, "trfId", vo.getStrTariffId()[i]); // 9

			dao.setProcInValue(nprocIndex, "reqQty", vo.getStrBillQty()[i]); // 10

			dao.setProcInValue(nprocIndex, "reqQtyUnitId",
					vo.getStrUnitIDVal()[i]); // 11

			dao.setProcInValue(nprocIndex, "rate", vo.getStrTariffRate()[i]); // 12

			dao.setProcInValue(nprocIndex, "actRate", vo
					.getStrTariffActualCost()[i]); // Check //13

			dao.setProcInValue(nprocIndex, "rateUnitId",
					vo.getStrUnitIDVal()[i]); // 14

			dao.setProcInValue(nprocIndex, "serTax", vo.getStrServTax()[i]); // 15

			dao.setProcInValue(nprocIndex, "serTaxAmt", service_tax); // 16

			dao.setProcInValue(nprocIndex, "trfNetAmt",
					vo.getStrTariffNetAmt()[i]); // 17

			dao.setProcInValue(nprocIndex, "chargeTypeId", vo
					.getStrChargeTypeID()); // 18

			dao.setProcInValue(nprocIndex, "wardId", vo.getStrWardCode()); // 19

			dao.setProcInValue(nprocIndex, "bServiceId", "11"); // Hard Coded
			// Plz Check It
			// //20

			dao
					.setProcInValue(nprocIndex, "serviceId", vo
							.getStrServiceId()[i]); // 21

			dao.setProcInValue(nprocIndex, "gblTrfId",
					vo.getStrGlblTariffId()[i]); // Hard Coded Plz Check //22

			dao.setProcInValue(nprocIndex, "isRefund",
					vo.getStrIsRefundable()[i]); // 23

			dao.setProcInValue(nprocIndex, "isPackage", vo.getStrIsPackg()[i]); // 24

			dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId()); // 25

			dao.setProcInValue(nprocIndex, "slNo", slNo);
			
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for return string
			// //26

			// execute procedure

			dao.execute(nprocIndex, 1);

		} catch (Exception e) {
			 e.printStackTrace();
			vo
					.setStrMsgString("RefundApprovalTransDAO.DmlAccountAddServiceProcedure() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}

	}

	*/
	
}
