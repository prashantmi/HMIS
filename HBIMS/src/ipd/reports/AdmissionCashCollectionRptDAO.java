package ipd.reports;

import ipd.IpdConfigUtil;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class AdmissionCashCollectionRptDAO {
	

	/**
	 * 
	 * @param voObj
	 */
	public static void getCategoryList(AdmissionCashCollectionRptVO voObj) {
		
		HisDAO daoObj = null;
		WebRowSet ws = null;
        String strSuperhospCode=IpdConfigUtil.SUPER_HOSPITAL_CODE;
		String strProcName = "{call Pkg_Ipd_Rpt.RPT_GBLT_PATIENT_CAT_MST(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("IPD Reports","AdmissionCashCollectionDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "hosp_code", strSuperhospCode);
			daoObj.setProcInValue(nProcIndex, "catType","0");
			daoObj.setProcOutValue(nProcIndex,"err", 1);
			daoObj.setProcOutValue(nProcIndex,"RESULTSET", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				voObj.setStrCategoryWs(ws);
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionCashCollectionDAO.getCategoryList() --> "
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
