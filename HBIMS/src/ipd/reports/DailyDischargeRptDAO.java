package ipd.reports;

import ipd.IpdConfigUtil;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class DailyDischargeRptDAO {

		
	public static void getDepartmentList(DailyDischargeRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Ipd_Rpt.RPT_GBLT_DEPARTMENT_MST(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports",
					"ListIndoorPatientRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "seat_id", voObj.getStrSeatId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

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

			//e.printStackTrace();
			
			voObj
					.setStrMsgString("DailyDischargeRptDAO.getDepartmentList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getHospitalName(DailyDischargeRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_rpt.RPT_GET_HOSPITAL_LIST(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports","DailyDischargeRptDAO");

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
					.setStrMsgString("DailyDischargeRptDAO.getHospitalName() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	
	public static void getDischargeTypeName(DailyDischargeRptVO voObj)
	{
		
		
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_rpt.RPT_GET_HOSPITAL_LIST(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports","DailyDischargeRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue( nProcIndex, "modeval","2",1);
			daoObj.setProcInValue( nProcIndex, "hosp_code",IpdConfigUtil.SUPER_HOSPITAL_CODE,2);
			daoObj.setProcInValue( nProcIndex, "seatId",voObj.getStrSeatId(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrDiscargeTypeWs(ws);
				
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj.setStrMsgString("DailyDischargeRptDAO.getDischargeTypeName() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
		
		public static void getUnitList(DailyDischargeRptVO voObj) {

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strProcName = "{call Pkg_Ipd_Rpt.RPT_HGBT_UNIT_MST(?,?,?,?,?)}";
			int nProcIndex = 0;

			String strErr = "";

			try {

				daoObj = new HisDAO("IPD Reports",
						"DailyDischargeRptDAO");

				nProcIndex = daoObj.setProcedure(strProcName);

				daoObj.setProcInValue( nProcIndex, "modeval","1",1);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "dept_code",voObj.getStrDeptCode(),3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				if (strErr.equals("")) {

					ws = daoObj.getWebRowSet(nProcIndex, "resultset");

					voObj.setStrUnitWs(ws);
					
					
				} else {
					throw new Exception(strErr);
				}

			} catch (Exception e) {
			
				voObj
						.setStrMsgString("DailyDischargeRptDAO.getUnitList() --> "
								+ e.getMessage());
				voObj.setStrMsgType("1");

			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}

		}
		
		
		public static void getWardList(DailyDischargeRptVO voObj) {

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strProcName = "{call Pkg_Ipd_Rpt.RPT_HIPT_WARD_MST(?,?,?,?,?)}";
			int nProcIndex = 0;

			String strErr = "";

			try {

				daoObj = new HisDAO("IPD Reports",
						"DailyDischargeRptDAO");

				nProcIndex = daoObj.setProcedure(strProcName);

				daoObj.setProcInValue( nProcIndex, "modeval","1",1);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "deptunit_code",voObj.getStrUnitCode(),3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				if (strErr.equals("")) {

					ws = daoObj.getWebRowSet(nProcIndex, "resultset");

					voObj.setStrWardWs(ws);
					
					
				} else {
					throw new Exception(strErr);
				}

			} catch (Exception e) {
			
				voObj
						.setStrMsgString("DailyDischargeRptDAO.getWardList() --> "
								+ e.getMessage());
				voObj.setStrMsgType("1");

			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}

		}

		public static void getWardCombo(DailyDischargeRptVO voObj) {

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strProcName = "{call Pkg_Ipd_Rpt.RPT_HIPT_WARD_MST(?,?,?,?,?)}";
			int nProcIndex = 0;

			String strErr = "";

			try {

				daoObj = new HisDAO("IPD Reports",
						"AdmittedPatRptDAO");

				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval", "3",1);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "deptunit_code",voObj.getStrDeptCode(),3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				if (strErr.equals("")) {

					ws = daoObj.getWebRowSet(nProcIndex, "resultset");

					voObj.setStrWardWs(ws);
					
					
				} else {
					throw new Exception(strErr);
				}

			} catch (Exception e) {
			
				voObj
						.setStrMsgString("DailyDischargeRptDAO.getWardList() --> "
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
