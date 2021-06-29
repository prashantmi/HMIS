package bmed.dao;

import javax.sql.rowset.WebRowSet;

import hisglobal.transactionmgnt.HisDAO;
import bmed.vo.ItemTypeMstVO;

public class ItemTypeMstDAO {

	public static void getItemTypeCombo(ItemTypeMstVO itemTypeMstVO_p,
			HisDAO hisDAO_p) throws Exception {
		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_SEMT_ENG_ITEM_TYPMST_CMB(?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					itemTypeMstVO_p.getStrHospitalCode(),2);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,3); // 1 for varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,4); // 2 for Cursor

			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			/* Sets The WebRowSet in ItemTypeMstVO */
			itemTypeMstVO_p.setWrsItemTypeComboOptions(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"ItemTypeMstDAO.getItemTypeCombo(ItemTypeMstVO)-->"
							+ exception.getMessage());
		}

	}

}
