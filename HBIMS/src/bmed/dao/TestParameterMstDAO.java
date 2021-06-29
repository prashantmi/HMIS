package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.TestMstVO;
import bmed.vo.TestParameterMstVO;

public class TestParameterMstDAO {

	public static void getTestParameterCombo(TestParameterMstVO testPara_p, HisDAO hisDAO_p)
			throws Exception {
		final String strproc_name = "{CALL PKG_BMED_VIEW.proc_hemt_test_para_mst_cmb(?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", testPara_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					testPara_p.getStrHospitalCode(),2);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,3); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,4); // Cursor

			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			/* Sets The WebRowSet in StoreMstVO */
			testPara_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			throw new Exception("StoreMstDAO.getStoreCombo(StoreMstVO)-->"
					+ exception.getMessage());
		}

	}

}
