package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.ItemCurrStockDtlMstVO;

public class ItemCurrStockDtlMstDAO 
{
		public static void getStockDtl(ItemCurrStockDtlMstVO ItemCurrStockDtlMstVO_p, HisDAO hisDAO_p)
		throws Exception 
		{
		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_hstt_item_currstock_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?)}";
		
		final int nProcedureIndex;
		
		final String strDbErr;
		final WebRowSet webRowSet;
	try 
	{

		nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
	
		/* Setting and Registering In and Out Parameters */
		hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", ItemCurrStockDtlMstVO_p.getStrMode(),1);//MOD-2
		hisDAO_p.setProcInValue(nProcedureIndex, "p_itemBrand_id", ItemCurrStockDtlMstVO_p.getStrItemBrandId(),2);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_item_id", ItemCurrStockDtlMstVO_p.getStrItemId(),3);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_batch_No", ItemCurrStockDtlMstVO_p.getStrBatchNo(),4);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_depart_id", ItemCurrStockDtlMstVO_p.getStrDeptId(),5);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_store_id", ItemCurrStockDtlMstVO_p.getStrStoreId(),6);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_itemSl_No", ItemCurrStockDtlMstVO_p.getStrItemSlNo(),7);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_item_cat_No", ItemCurrStockDtlMstVO_p.getStrItemCatgNo(),8);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_stock_status_code", ItemCurrStockDtlMstVO_p.getStrStockStatusCode(),9);
		hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", ItemCurrStockDtlMstVO_p.getStrHospCode(),10);
		hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,11); // varchar
		hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,12); // Cursor
			
		/* Executing Procedure */
		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
	
		/* Getting out parameters */
		 strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
		webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");
	
		/* If Database Error Occurs, No farther processing is required. */
		if (strDbErr != null && !strDbErr.equals("")) {
			throw new Exception("Data Base Error:" + strDbErr);
		}
	
		/* Sets The WebRowSet in ItemCurrStockDtlMstVO */
		ItemCurrStockDtlMstVO_p.setWrsStockDtl(webRowSet);
	
	} 
	catch (Exception exception) 
	{
	   throw new Exception("ItemCurrStockDtlMstDAO.getItemBrandCombo(ItemCurrStockDtlMstVO)-->"
			+ exception.getMessage());
	}
	
  }
	
}



