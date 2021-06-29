package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.SemtMaintTypeMstVO;

public class SemtMaintTypeMstDAO 
{
	
	
	public static void getSemtMaintTypeMstCombo(SemtMaintTypeMstVO semtMaintTypeMstVO_p, HisDAO hisDAO_p)
	throws Exception 
	{
	final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_SEMT_MAINTE_TYPE_MST_CMB(?,?,?,?,?)}";
	
	final int nProcedureIndex;
	
	final String strDbErr;
	final WebRowSet webRowSet;
	try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", semtMaintTypeMstVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_maintenancetype_id","0",2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",semtMaintTypeMstVO_p.getStrHospCode(),3);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5); // Cursor

			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			/* Sets The WebRowSet in SemtMaintTypeMstVO */
			semtMaintTypeMstVO_p.setWrsMaintTypeComboOptions(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"SemtMaintTypeMstDAO.getSemtMaintTypeMstCombo(SemtMaintTypeMstVO)-->"
							+ exception.getMessage());
		}

}


}
