package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.HsttSupplierItemMstVO;

public class HsttSupplierItemMsDAO 
{
	
	
			public static void getSupplierCmb(HsttSupplierItemMstVO hsttSupplierItemMstVO_p, HisDAO hisDAO_p)
			throws Exception 
			{
			final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_HSTT_SUPPLIERITEM_MST_CMB(?,?,?,?,?, ?,?,?)}";

			final int nProcedureIndex;
			
			final String strDbErr;
			final WebRowSet webRowSet;
		try 
		{			
		
			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
		
			/* Setting and Registering In and Out Parameters */
		
			System.out.println("P_mode:::"+hsttSupplierItemMstVO_p.getStrMode());
			System.out.println("p_supplier_id:::"+hsttSupplierItemMstVO_p.getStrSupplierId());
			System.out.println("p_itemBrand_id:::"+hsttSupplierItemMstVO_p.getStrItemBrandId());
			System.out.println("p_item_id:::"+hsttSupplierItemMstVO_p.getStrItemId());
			System.out.println("p_hospital_code:::"+hsttSupplierItemMstVO_p.getStrHospCode());
			System.out.println("p_supplierItemSl_No:::"+hsttSupplierItemMstVO_p.getStrSupplItemSlNo());
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", hsttSupplierItemMstVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_supplier_id", hsttSupplierItemMstVO_p.getStrSupplierId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_itemBrand_id", hsttSupplierItemMstVO_p.getStrItemBrandId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_item_id", hsttSupplierItemMstVO_p.getStrItemId(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", hsttSupplierItemMstVO_p.getStrHospCode(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_supplierItemSl_No", hsttSupplierItemMstVO_p.getStrSupplItemSlNo(),6);
			
			
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
		    
			/* Sets The WebRowSet in HsttSupplierItemMstVO */
			hsttSupplierItemMstVO_p.setWrsSupplierDetails(webRowSet);
		
		} 
		catch (Exception exception) 
		{
			//exception.printStackTrace();
		   throw new Exception("HemtItemMcDtlDAO.getItemBrandCombo(HsttSupplierItemMstVO)-->"
				+ exception.getMessage());
		}
		
		}


}
