package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.SemtEscalationLevelTypeMstVO;

public class SemtEscalationLevelTypeMstDAO 
{
		public static void getSemtCancelTypeMstCombo(SemtEscalationLevelTypeMstVO semtEscalationLevelTypeMstVO_p, HisDAO hisDAO_p)	throws Exception 
		{
				final String strproc_name = "{CALL PKG_BMED_VIEW.proc_SEMT_ESC_LEVTYPE_MST_cmb(?,?,?,?,?)}";
				final int nProcedureIndex;
				final String strDbErr;
				final WebRowSet webRowSet;
				try {
		
						nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
		
						/* Setting and Registering In and Out Parameters */
						hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", semtEscalationLevelTypeMstVO_p.getStrMode(),1);
						hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_LEVELTYPE_ID",semtEscalationLevelTypeMstVO_p.getStrLevelTypeId(),2);
						hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",semtEscalationLevelTypeMstVO_p.getStrHospCode(),3);
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
						semtEscalationLevelTypeMstVO_p.setWrsData(webRowSet);
		
					} catch (Exception exception) {
						throw new Exception(
								"UnitMstDAO.getSemtCancelTypeMstCombo(SemtMaintTypeMstVO)-->"
										+ exception.getMessage());
					}

	      }


	}


