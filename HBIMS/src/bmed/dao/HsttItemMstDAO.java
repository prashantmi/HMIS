package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.vo.HsttItemMstVO;

	/**
	 * @author Vivek Aggarwal  
	 * Creation Date:- 11-May-2011 
	 * Modifying Date:- 11-May-2011
	 * Used For:- 
	 * Team Lead By:-  
	 *  Module:- BMED(HIS Project)
	 * 
	 */
	public class HsttItemMstDAO 
	{
		/*
		 * To get data
		 * 
		 * @param hsttItemMstVO_p		the HsttStoreCategoryMstVO
		 * @param hisDAO_p		the HisDAO
		 */
		public static void getData(HsttItemMstVO hsttItemMstVO_p, HisDAO hisDAO_p) throws Exception 
		{

			final String strProc_name = "{CALL PKG_BMED_VIEW.proc_hstt_item_mst(?,?,?,?,?, ?)}";// 6 variables

			final int nProcedureIndex;

			final String strDbErr;
			final WebRowSet webRowSet;
			try {

				nProcedureIndex = hisDAO_p.setProcedure(strProc_name);

				HisUtil.replaceNullValueWithEmptyString(hsttItemMstVO_p);

				/* Setting and Registering In and Out Parameters */
				  
				            
				hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", hsttItemMstVO_p.getStrMode(),1);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_hstnum_item_id", hsttItemMstVO_p.getStrItemId(),2);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code", hsttItemMstVO_p.getStrHospitalCode(),3);
				hisDAO_p.setProcInValue(nProcedureIndex, "p_sstnum_item_cat_no", hsttItemMstVO_p.getStrItemCatNo(),4);
		
				hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // 1 
	            hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // 2 for Cursor
							

				/* Executing Procedure */
				hisDAO_p.executeProcedureByPosition(nProcedureIndex);

				/* Getting out parameters */
				strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
				webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

				/* If Database Error Occurs, No farther processing is required. */
				if (strDbErr != null && !strDbErr.equals("")) {
					throw new Exception("Data Base Error:" + strDbErr);
				}
				
				hsttItemMstVO_p.setWrsData(webRowSet);
				
			} 
			catch (Exception exception)
			{
				throw new Exception("HsttItemMstDAO.getData(HsttItemMstVO,HisDAO)-->"+ exception.getMessage());
			}
		}
		
		/*
		 * To insert data
		 * 
		 * @param hsttItemMstVO_p		the HsttItemMstVO
		 * @param hisDAO_p		the HisDAO
		 */
		public static void insert(HsttItemMstVO hsttItemMstVO_p, HisDAO hisDAO_p)
		{
			
			
		}
		
		/*
		 * To modify data
		 * 
		 * @param hsttItemMstVO_p		the HsttItemMstVO
		 * @param hisDAO_p		the HisDAO
		 */
		public static void modify(HsttItemMstVO hsttItemMstVO_p, HisDAO hisDAO_p)
		{
			
			
		}
		
		/*
		 * To delete data
		 * 
		 * @param hsttItemMstVO_p		the HsttItemMstVO
		 * @param hisDAO_p		the HisDAO
		 */
		public static void delete(HsttItemMstVO hsttItemMstVO_p, HisDAO hisDAO_p)
		{
			
			
		}
		
}
