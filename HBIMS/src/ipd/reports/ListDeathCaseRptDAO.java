package ipd.reports;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class ListDeathCaseRptDAO {

	public static void getDepartmentList(ListDeathCaseRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call Pkg_Ipd_Rpt.RPT_GBLT_DEPARTMENT_MST(?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {

			daoObj = new HisDAO("IPD Reports","ListDeathCaseRptDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setStrDeptWs(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
	
			voObj.setStrMsgString("ListDeathCaseRptDAO.getDepartmentList() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	public static void getWardCombo(ListDeathCaseRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call Pkg_Ipd_Rpt.RPT_HIPT_WARD_MST(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports","AdmittedPatRptDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "3",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "deptunit_code",voObj.getStrDeptCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setStrWardWs(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
		
			voObj.setStrMsgString("DailyMasterCensusRptVO.getWardCombo() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
		
	public static void getUnitList(ListDeathCaseRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call Pkg_Ipd_Rpt.RPT_HGBT_UNIT_MST(?,?::character varying,?::character varying,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports","ListDeathCaseRptDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "dept_code",voObj.getStrDeptCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setStrUnitWs(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
		
			voObj.setStrMsgString("ListDeathCaseRptDAO.getUnitList() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	
	public static void getWardList(ListDeathCaseRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call Pkg_Ipd_Rpt.RPT_HIPT_WARD_MST(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports","ListDeathCaseRptDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval","1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "deptunit_code",voObj.getStrUnitCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setStrWardWs(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			voObj.setStrMsgString("ListDeathCaseRptDAO.getWardList() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getHospitalName(ListDeathCaseRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_bill_rpt.RPT_GET_HOSPITAL_LIST(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports","AdmittedPatientRptDAO");
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
			voObj.setStrMsgString("AdmittedPatientRptDAO.getHospitalName() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getdeptComboDetails(ListDeathCaseRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Ipd_Rpt.RPT_GBLT_DEPARTMENT_MST(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {

			daoObj = new HisDAO("IPD Reports","ListDeathCaseRptDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "seat_id", voObj.getStrSeatId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setStrDeptWs(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj.setStrMsgString("ListDeathCaseRptDAO.getDepartmentList() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

}
