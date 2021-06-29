package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.EnggSkillsMstVO;

public class EnggSkillsMstDAO {

	public static void getSkillCombo(
			EnggSkillsMstVO enggSkillsMstVO_p, HisDAO hisDAO_p)
			throws Exception {

		final String strProcName = "{CALL PKG_BMED_VIEW.PROC_HEMT_ENGG_SKILLS_MST_COMB(?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", enggSkillsMstVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					enggSkillsMstVO_p.getStrHospitalCode(),2);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,3); // 1 for
																	// varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,4); // 2 for
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

			/* Sets The WebRowSet in EnggSkillsMstVO */
			enggSkillsMstVO_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"EnggSkillsMstDAO.getSkillCombo(EnggSkillsMstVO)-->"
							+ exception.getMessage());
		}

	}

}
