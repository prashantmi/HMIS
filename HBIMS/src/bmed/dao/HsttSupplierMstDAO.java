package bmed.dao;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;


import bmed.vo.HsttSupplierMstVO;


/**
 * @author Vivek Aggarwal  
 * Creation Date:- 11-May-2011 
 * Modifying Date:- 11-May-2011
 * Used For:- 
 * Team Lead By:-  
 *  Module:- BMED(HIS Project)
 * 
 */
public class HsttSupplierMstDAO 
{
	/*
	 * To get data
	 * 
	 * @param hsttSupplierMstVO_p		the HsttStoreCategoryMstVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void getData(HsttSupplierMstVO hsttSupplierMstVO_p, HisDAO hisDAO_p) throws Exception 
	{

		final String strProc_name = "{CALL PKG_BMED_VIEW.proc_hstt_supplier_mst_cmb(?,?,?,?,?)}";// 6 variables

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strProc_name);

			HisUtil.replaceNullValueWithEmptyString(hsttSupplierMstVO_p);

			/* Setting and Registering In and Out Parameters */
					 	  
			            
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", hsttSupplierMstVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hstnum_supplier_id", hsttSupplierMstVO_p.getStrSupplierId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code", hsttSupplierMstVO_p.getStrHospitalCode(),3);
	
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); // 1 
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
			
			hsttSupplierMstVO_p.setWrsData(webRowSet);
			
		} 
		catch (Exception exception)
		{
			throw new Exception("HsttItemMstDAO.getData(HsttSupplierMstVO,HisDAO)-->"+ exception.getMessage());
		}
	}
	
	public static HsttSupplierMstVO  getDetailsData(String StrSupplierId,String strHospitalCode, HisDAO hisDAO_p) throws Exception 
	{

		final String strProc_name = "{CALL PKG_BMED_VIEW.proc_hstt_supplier_mst_cmb(?,?,?,?,?)}";// 6 variables

		final int nProcedureIndex;
		HsttSupplierMstVO vo=null;
		final String strDbErr;
		 WebRowSet webRowSet=null;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strProc_name);


			/* Setting and Registering In and Out Parameters */
					 	  
			vo= new HsttSupplierMstVO ();            
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hstnum_supplier_id", StrSupplierId,2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code", Config.SUPER_USER_HOSPITAL_CODE,3);
	
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); // 1 
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
			
			
			if(webRowSet!=null && webRowSet.size()>0  ){
				
			if(webRowSet.next())
			{
				
				System.out.println(webRowSet.getString("SupplierName"));
				vo.setStrSupplierName(webRowSet.getString("SupplierName"));
				vo.setStrContractNo(webRowSet.getString("strContractNo"));
				vo.setStrAddress(webRowSet.getString("strAddress"));
			}
				}
			
		  
		
			return vo;
		} 
		catch (Exception exception)
		{
			
			
			exception.printStackTrace();
			throw new Exception("HsttItemMstDAO.getData(HsttSupplierMstVO,HisDAO)-->"+ exception.getMessage());
		}
	}
	
	
	
	
	/*
	 * To insert data
	 * 
	 * @param hsttSupplierMstVO_p		the HsttSupplierMstVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void insert(HsttSupplierMstVO hsttSupplierMstVO_p, HisDAO hisDAO_p)
	{
		
		
	}
	
	/*
	 * To modify data
	 * 
	 * @param hsttSupplierMstVO_p		the HsttSupplierMstVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void modify(HsttSupplierMstVO hsttSupplierMstVO_p, HisDAO hisDAO_p)
	{
		
		
	}
	
	/*
	 * To delete data
	 * 
	 * @param hsttSupplierMstVO_p		the HsttSupplierMstVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void delete(HsttSupplierMstVO hsttSupplierMstVO_p, HisDAO hisDAO_p)
	{
		
		
	}
	
}
