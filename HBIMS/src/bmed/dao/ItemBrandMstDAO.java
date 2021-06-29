package bmed.dao;

import javax.sql.rowset.WebRowSet;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import bmed.vo.ItemBrandMstVO;

public class ItemBrandMstDAO {

	public static void getItemBrandCombo(ItemBrandMstVO itemBrandMstVO_p, HisDAO hisDAO_p) throws Exception 
	{
		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_HSTT_ITEMBRAND_MST_CMB(?,?,?,?,?, ?,?,?)}";
		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try 
		{
			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
			HisUtil.replaceNullValueWithEmptyString(itemBrandMstVO_p);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", itemBrandMstVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_item_catg_code", itemBrandMstVO_p.getStrItemCatNo(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_store_id", itemBrandMstVO_p.getStrStoreId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_depart_id", itemBrandMstVO_p.getStrDepartmentId(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_item_id", itemBrandMstVO_p.getStrItemId().equals("") ? "0" : itemBrandMstVO_p.getStrItemId(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", itemBrandMstVO_p.getStrHospitalCode(),6);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8); // Cursor
			
			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			/* Sets The WebRowSet in ItemBrandMstVO */
			itemBrandMstVO_p.setWrsItemBrandComboOptions(webRowSet);

		} catch (Exception exception) {
			throw new Exception("ItemBrandMstDAO.getItemBrandCombo(ItemBrandMstVO)-->"
					+ exception.getMessage());
		}

	}

}