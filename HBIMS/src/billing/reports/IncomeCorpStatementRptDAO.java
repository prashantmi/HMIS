package billing.reports;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class IncomeCorpStatementRptDAO {
	
	public static void getHospSerList(IncomeCorpStatementRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.RPTB_SBLT_CHARGETYPE_MST(?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","IncomeCorpStatementRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);
	
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
					.setStrMsgString("IncomeCorpStatementRptDAO.getHospSerList() --> "
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
