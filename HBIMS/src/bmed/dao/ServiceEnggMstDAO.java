package bmed.dao;

import javax.sql.rowset.WebRowSet;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import bmed.vo.ServiceEnggMstVO;

public class ServiceEnggMstDAO {

	public static void getData(ServiceEnggMstVO serviceEnggMstVO_p,
			HisDAO hisDAO_p) throws Exception {
		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_HEMT_SERVICE_ENGG_MST(?,?,?,?,?, ?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(serviceEnggMstVO_p);
			//HisUtil.printStringFieldsOfVO(serviceEnggMstVO_p);
			
			/* Setting and Registering In and Out Parameters */
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", serviceEnggMstVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_engg_item_type_id", serviceEnggMstVO_p.getStrEnggItemTypeId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_engg_item_sub_type_id", serviceEnggMstVO_p.getStrEnggItemSubTypeId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_id", serviceEnggMstVO_p.getStrEmpId(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", serviceEnggMstVO_p.getStrHospitalCode(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_skill_id", serviceEnggMstVO_p.getStrSkillId(),6);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); // 1 for varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8); // 2 for Cursor

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
			serviceEnggMstVO_p.setWrsData(webRowSet);
			
		} catch (Exception exception) {
			throw new Exception(
					"ServiceEnggMstDAO.getData(ServiceEnggMstVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}

}
