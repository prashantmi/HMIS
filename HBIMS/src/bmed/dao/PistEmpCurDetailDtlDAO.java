package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.vo.PistEmpCurDetailDtlVO;

public class PistEmpCurDetailDtlDAO {

	public static void getEmployeeCombo(
			PistEmpCurDetailDtlVO pistEmpCurDetailDtlVO_p, HisDAO hisDAO_p)
			throws Exception {

		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_EMP_CUR_DETAIL_DTL_CMB(?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					pistEmpCurDetailDtlVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					pistEmpCurDetailDtlVO_p.getStrHospitalCode(),2);

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

			/* Sets The WebRowSet in PistEmpCurDetailDtlVO */
			pistEmpCurDetailDtlVO_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"PistEmpCurDetailDtlDAO.getEmployeeCombo(PistEmpCurDetailDtlVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}

	
	public static void getEmployeeDeptBasedCombo(
			PistEmpCurDetailDtlVO pistEmpCurDetailDtlVO_p, HisDAO hisDAO_p)
			throws Exception {

		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_EMP_CUR_DTL_DEPTBASED_CMB(?,?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					pistEmpCurDetailDtlVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					pistEmpCurDetailDtlVO_p.getStrHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dept_code",
					pistEmpCurDetailDtlVO_p.getStrDeptId(),3);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); // 1 for
																	// varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5); // 2 for
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

			/* Sets The WebRowSet in PistEmpCurDetailDtlVO */
			pistEmpCurDetailDtlVO_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"PistEmpCurDetailDtlDAO.getEmployeeCombo(PistEmpCurDetailDtlVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}
	
}