package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.vo.TaskMstVO;

public class TaskMstDAO {
	

	public static void getData(TaskMstVO taskMstVO_p,
			HisDAO hisDAO_p) throws Exception {

		final String strProcName = "{CALL PKG_BMED_VIEW.PROC_HEMT_TASK_MST(?,?,?,?,?, ?)}";
		
		final int nProcedureIndex;
		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(taskMstVO_p);


			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					taskMstVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					taskMstVO_p.getStrHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_engg_item_type_id",
					taskMstVO_p.getStrEnggItemTypeId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_engg_item_sub_type_id",
					taskMstVO_p.getStrEnggItemSubTypeId(),4);

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

			/* Sets The WebRowSet in ItemTypeMstVO */
			taskMstVO_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"TaskMstDAO.getData(TaskMstVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}
}
