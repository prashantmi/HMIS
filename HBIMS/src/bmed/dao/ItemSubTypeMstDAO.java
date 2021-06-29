package bmed.dao;

import javax.sql.rowset.WebRowSet;

import hisglobal.transactionmgnt.HisDAO;
import bmed.vo.ItemSubTypeMstVO;

public class ItemSubTypeMstDAO {

	public static void getItemSubTypeCombo(ItemSubTypeMstVO itemSubTypeMstVO_p,
			HisDAO hisDAO_p) throws Exception {
		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_HEMT_ENGITEM_SUBTYMST_CMB(?,?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_engg_item_type", itemSubTypeMstVO_p.getStrEnggItemTypeId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					itemSubTypeMstVO_p.getStrHospitalCode(),3);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); // 1 for varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5); // 2 for Cursor

			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			/* Sets The WebRowSet in ItemSubTypeMstVO */
			itemSubTypeMstVO_p.setWrsItemSubTypeComboOptions(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"ItemSubTypeMstDAO.getItemSubTypeCombo(ItemSubTypeMstVO)-->"
							+ exception.getMessage());
		}

	}

}
