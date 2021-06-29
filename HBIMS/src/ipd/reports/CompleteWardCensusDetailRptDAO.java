package ipd.reports;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

/**
 * 
 * @author: Vivek Aggarwal
 * @Date: 12-July-2011
 * @Module:	ADT
 */
public class CompleteWardCensusDetailRptDAO {

	/**
	 * To get Department Details
	 * 
	 * @param voObj	the CompleteWardCensusDetailRptVO
	 */
	/*public static void getDepartmentList(CompleteWardCensusDetailRptVO voObj) {

		//System.out.println("inside DAO");
		
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Ipd_Rpt.RPT_GBLT_DEPARTMENT_MST(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports","CompleteWardCensusDetailRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "modeVal", "2"); // mode=2 for dept combo on basis of login id
			daoObj.setProcInValue(nProcIndex, "seat_id", voObj.getStrSeatId()); // mode=2 for dept combo on basis of login id
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
		
				//System.out.println("ws : "+ws.size());
				
				voObj.setStrDeptWs(ws);
				
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("CompleteWardCensusDetailRptDAO.getDepartmentList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}*/
	
	
	
	public static void getWardAll(CompleteWardCensusDetailRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Ipd_Rpt.RPT_HIPT_WARD_MST(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports","CompleteWardCensusDetailRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "2",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "deptunit_code","",3);
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
		
			voObj
					.setStrMsgString("CompleteWardCensusDetailRptDAO.getWardAll() --> "
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
	 * To Get All Wards for All Units of a particular dept
	 * 
	 * @param voObj
	 */
	public static void getWardCombo(CompleteWardCensusDetailRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Ipd_Rpt.RPT_HIPT_WARD_MST(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports",
					"AdmittedPatRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "3");
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "deptunit_code",voObj.getStrDeptCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);

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
		
			voObj.setStrMsgString("CompleteWardCensusDetailRptDAO.getWardCombo() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	/**
	 * To get  all Unit for a particular dept
	 * 
	 * @param voObj	the CompleteWardCensusDetailRptVO
	 */
	public static void getUnitList(CompleteWardCensusDetailRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Ipd_Rpt.RPT_HGBT_UNIT_MST(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports",
					"CompleteWardCensusDetailRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "dept_code",voObj.getStrDeptCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);

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

			//e.printStackTrace();
			
			voObj.setStrMsgString("CompleteWardCensusDetailRptDAO.getUnitList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	/**
	 * To get All wards for a particular  unit of particular dept 
	 * 
	 * @param voObj	the CompleteWardCensusDetailRptVO
	 */
	public static void getWardList(CompleteWardCensusDetailRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Ipd_Rpt.RPT_HIPT_WARD_MST(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports",
					"CompleteWardCensusDetailRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "1");
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "deptunit_code",voObj.getStrUnitCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);

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

	//		e.printStackTrace();
			
			voObj
					.setStrMsgString("CompleteWardCensusDetailRptDAO.getWardList() --> "
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
