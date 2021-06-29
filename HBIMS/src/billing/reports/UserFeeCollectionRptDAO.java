package billing.reports;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class UserFeeCollectionRptDAO {
		
	public static void getHospSerList(UserFeeCollectionRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.RPTB_SBLT_CHARGETYPE_MST(?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","UserFeeCollectionRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				voObj.setStrHospSerWs(ws);
				
								
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			voObj
					.setStrMsgString("UserFeeCollectionRptDAO.getHospSerList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getHospitalName(UserFeeCollectionRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.RPT_GET_HOSPITAL_LIST(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","UserFeeCollectionRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue( nProcIndex, "modeval","1",1);
			daoObj.setProcInValue( nProcIndex, "hosp_code",voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue( nProcIndex, "seatId",voObj.getStrSeatId(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		

				voObj.setStrHospitalWs(ws);
				
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("UserFeeCollectionRptDAO.getHospitalName() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getCounterList(UserFeeCollectionRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.RPTB_GBLT_COUNTER_MST(?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","UserFeeCollectionRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),1);
			daoObj.setProcInValue(nProcIndex, "moduleId", voObj.getStrModuleId(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				
				voObj.setStrCounterWs(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
	 

			voObj
					.setStrMsgString("UserFeeCollectionRptDAO.getCounterList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	/******pending because there is no procedure for clerk list******/
	public static void getClerkList(UserFeeCollectionRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName =  "{call Pkg_Bill_Rpt.RPTB_user_list(?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","UserFeeCollectionRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrClerkWs(ws);
								
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			voObj
					.setStrMsgString("UserFeeCollectionRptDAO.getClerkList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	public static void getGroupList(UserFeeCollectionRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.RPTB_HBLT_GROUP_MST(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","UserFeeCollectionRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "charge_id", voObj.getStrHospSerName(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				voObj.setStrGroupWs(ws);
								
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("UserFeeCollectionRptDAO.getGroupList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	/******pending because there is no procedure for Tariff list******/
	public static void getTariffList(UserFeeCollectionRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.rptb_hblt_tariff_mst(?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","UserFeeCollectionRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),1);
			daoObj.setProcInValue(nProcIndex, "grpId", voObj.getStrGroupName(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrTariffWs(ws);
								
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			voObj
					.setStrMsgString("UserFeeCollectionRptDAO.getTariffList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getDeptList(UserFeeCollectionRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.RPTB_GBLT_DEPARTMENT_MST(?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","UserFeeCollectionRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrDeptWs(ws);
								
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
	 

			voObj
					.setStrMsgString("UserFeeCollectionRptDAO.getDeptList() --> "
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
