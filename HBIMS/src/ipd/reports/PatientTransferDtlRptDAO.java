package ipd.reports;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class PatientTransferDtlRptDAO {

	public static void getWardAll(PatientTransferDtlRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call Pkg_Ipd_Rpt.RPT_HIPT_WARD_MST(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {

			daoObj = new HisDAO("IPD Reports","PatientTransferDtlRptDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "2",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "deptunit_code","",3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setStrWardAllWs(ws);
				
			} else {
				throw new Exception(strErr);
			}
			} catch (Exception e) {
		
			voObj.setStrMsgString("PatientTransferDtlRptDAO.getWardAll() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
}
