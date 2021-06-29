package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.vo.HsttStoreCategoryMstVO;

/**
 * @author Vivek Aggarwal  
 * Creation Date:- 11-May-2011 
 * Modifying Date:- 11-May-2011
 * Used For:- 
 * Team Lead By:-  
 *  Module:- BMED(HIS Project)
 * 
 */
public class HsttStoreCategoryMstDAO 
{
	/*
	 * To get data
	 * 
	 * @param hsttStoreCategoryMstVO_p		the HsttStoreCategoryMstVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void getData(HsttStoreCategoryMstVO hsttStoreCategoryMstVO_p, HisDAO hisDAO_p) throws Exception 
	{

		final String strProc_name = "{CALL PKG_BMED_VIEW.proc_hstt_store_category_mst(?,?,?,?,?, ?,?)}";// 7 variables

		final int nProcedureIndex;
		
		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strProc_name);

			HisUtil.replaceNullValueWithEmptyString(hsttStoreCategoryMstVO_p);

			/* Setting and Registering In and Out Parameters */
			  
		
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", hsttStoreCategoryMstVO_p.getStrMode(),1);
			 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hstnum_store_id", hsttStoreCategoryMstVO_p.getStrStoreId(),2);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_sstnum_item_cat_no", hsttStoreCategoryMstVO_p.getStrItemCatNo(),3);
			
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",hsttStoreCategoryMstVO_p.getStrHospitalCode(),4);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_hstnum_cat_slno", hsttStoreCategoryMstVO_p.getStrCatSlno(),5);
			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); // 1 
            hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7); // 2 for Cursor
						

			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}
			
			hsttStoreCategoryMstVO_p.setWrsData(webRowSet);
			
		} 
		catch (Exception exception)
		{
			throw new Exception("HsttStoreCategoryMstDAO.getData(HsttStoreCategoryMstVO,HisDAO)-->"+ exception.getMessage());
		}
	}
	
	/*
	 * To insert data
	 * 
	 * @param hsttStoreCategoryMstVO_p		the HsttStoreCategoryMstVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void insert(HsttStoreCategoryMstVO hsttStoreCategoryMstVO_p, HisDAO hisDAO_p)
	{
		
		
	}
	
	/*
	 * To modify data
	 * 
	 * @param hsttStoreCategoryMstVO_p		the HsttStoreCategoryMstVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void modify(HsttStoreCategoryMstVO hsttStoreCategoryMstVO_p, HisDAO hisDAO_p)
	{
		
		
	}
	
	/*
	 * To delete data
	 * 
	 * @param hsttStoreCategoryMstVO_p		the HsttStoreCategoryMstVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void delete(HsttStoreCategoryMstVO hsttStoreCategoryMstVO_p, HisDAO hisDAO_p)
	{
		
		
	}
	

}
