package bmed.global.dao;

import javax.sql.rowset.WebRowSet;

import bmed.global.vo.MainteWarrantyContractVO;
import hisglobal.transactionmgnt.HisDAO;

public class MainteWarrantyContractDAO {

	public static void getWarrantyData(
			MainteWarrantyContractVO mainteWarrantyContractVO_p)
			throws Exception {

		String strProcName = "{call pkg_bmed_view.proc_hstt_warranty_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		WebRowSet ws = null;
		HisDAO daoObj = null;
		try {

			daoObj = new HisDAO("MMSModule", "BreakageItemDtlTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "p_mode", "1");
			daoObj.setProcInValue(nProcIndex, "p_item_id",
					mainteWarrantyContractVO_p.getStrItemId());
			daoObj.setProcInValue(nProcIndex, "p_hospital_code",
					mainteWarrantyContractVO_p.getStrHospitalCode());

			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null) {
				strErr = "";
			}
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				mainteWarrantyContractVO_p.setWebRowSetWarranty(ws);
			}
		} catch (Exception exception) {
			throw new Exception("MainteWarrantyContractDAO.getWarrantyData-->"
					+ exception.getMessage());
		}

	}

	public static void getContractData(
			MainteWarrantyContractVO mainteWarrantyContractVO_p)
			throws Exception {

		String strProcName = "{call pkg_bmed_view.proc_hemt_item_mc_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		WebRowSet ws = null;
		HisDAO daoObj = null;
		try {

			daoObj = new HisDAO("MMSModule", "BreakageItemDtlTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "p_mode", "1");
			daoObj.setProcInValue(nProcIndex, "p_item_id",
					mainteWarrantyContractVO_p.getStrItemId());
			daoObj.setProcInValue(nProcIndex, "p_hospital_code",
					mainteWarrantyContractVO_p.getStrHospitalCode());

			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null) {
				strErr = "";
			}
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				mainteWarrantyContractVO_p.setWebRowSetMaintenance(ws);
			}
		} catch (Exception exception) {
			throw new Exception("MainteWarrantyContractDAO.getWarrantyData-->"
					+ exception.getMessage());
		}

	}
}
