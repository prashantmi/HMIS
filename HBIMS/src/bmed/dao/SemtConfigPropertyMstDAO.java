package bmed.dao;

import javax.sql.rowset.WebRowSet;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import bmed.vo.SemtConfigPropertyMstVO;

public class SemtConfigPropertyMstDAO 
{
	/*
	 * To get data
	 * 
	 * @param semtConfigPropertyMstVO_p		the SemtConfigPropertyMstVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void getData(SemtConfigPropertyMstVO semtConfigPropertyMstVO_p, HisDAO hisDAO_p)throws Exception {

		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_SEMT_CONFIG_PROPERTY_MST(?,?,?,?,?, ?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			/* Setting and Registering In and Out Parameters */
			  
		
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", semtConfigPropertyMstVO_p.getStrMode(),1);
			 
			hisDAO_p.setProcInValue(nProcedureIndex, "p_num_config_property_id", semtConfigPropertyMstVO_p.getStrConfigPropertyId(),2);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",	Config.SUPER_USER_HOSPITAL_CODE,3);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_num_isvalid", Config.IS_VALID_ACTIVE,4);

			
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // 1 for
			// varchar
            hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // 2 for
				// Cursor
						

			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}
			
			semtConfigPropertyMstVO_p.setWrsData(webRowSet);
			while(webRowSet.next())
			{	
             
             semtConfigPropertyMstVO_p.setStrPropertyValue(webRowSet.getString(1));
			} 
		} 
		catch (Exception exception)
		{
			throw new Exception("SemtConfigPropertyMstDAO.getData(SemtConfigPropertyMstVO,HisDAO)-->"+ exception.getMessage());
		}
		

	}
	
	/*
	 * To insert data
	 * 
	 * @param semtConfigPropertyMstVO_p		the SemtConfigPropertyMstVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void insert(SemtConfigPropertyMstVO semtConfigPropertyMstVO_p, HisDAO hisDAO_p)
	{
		
		
	}
	
	/*
	 * To modify data
	 * 
	 * @param semtConfigPropertyMstVO_p		the SemtConfigPropertyMstVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void modify(SemtConfigPropertyMstVO semtConfigPropertyMstVO_p, HisDAO hisDAO_p)
	{
		
		
	}
	
	/*
	 * To delete data
	 * 
	 * @param semtConfigPropertyMstVO_p		the SemtConfigPropertyMstVO
	 * @param hisDAO_p		the HisDAO
	 */
	public static void delete(SemtConfigPropertyMstVO semtConfigPropertyMstVO_p, HisDAO hisDAO_p)
	{
		
		
	}
	
}
