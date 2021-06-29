package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.HemtNonItemMstVO;

public class HemtNonItemMstDAO 
{
	
	public static void getNonItemCmb(HemtNonItemMstVO HemtNonItemMstVO_p, HisDAO hisDAO_p)throws Exception 
	{
			final String strproc_name = "{CALL PKG_BMED_VIEW.proc_HEMT_NONITEM_MST_cmb(?,?,?,?,?, ?)}";
			final int nProcedureIndex;
			final String strDbErr;
			final WebRowSet webRowSet;
			try 
			{			
		
					nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
				
					/* Setting and Registering In and Out Parameters */
										
					hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", HemtNonItemMstVO_p.getStrMode(),1);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_enggItemType_id", HemtNonItemMstVO_p.getStrEnggItemTypeId(),2);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_enggItemSubType_id", HemtNonItemMstVO_p.getStrEnggItemSubTypeId(),3);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", HemtNonItemMstVO_p.getStrHospCode(),4);
					hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
					hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor
								
					/* Executing Procedure */
					hisDAO_p.executeProcedureByPosition(nProcedureIndex);
				
					/* Getting out parameters */
					 strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
					webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");
					//System.out.println("Size..."+webRowSet.size());
				
					/* If Database Error Occurs, No farther processing is required. */
					if (strDbErr != null && !strDbErr.equals("")) {
						throw new Exception("Data Base Error:" + strDbErr);
					}
				
					/* Sets The WebRowSet in HemtNonItemMstVO */
					HemtNonItemMstVO_p.setWrsNonItemDetails(webRowSet);
				
		} 
		catch (Exception exception) 
		{
			//exception.printStackTrace();
		   throw new Exception("HemtNonItemMstDAO.getNonItemCmb(HemtNonItemMstVO)-->"
				+ exception.getMessage());
		}
		
	}

}
