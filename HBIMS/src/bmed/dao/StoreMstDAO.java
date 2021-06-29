package bmed.dao;

import javax.sql.rowset.WebRowSet;

import hisglobal.transactionmgnt.HisDAO;
import bmed.vo.StoreMstVO;

public class StoreMstDAO {

	public static void getStoreCombo(StoreMstVO storeMstVO_p, HisDAO hisDAO_p)
			throws Exception {
		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_HSTT_STORE_CMB(?,?,?,?,?, ?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", storeMstVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id",storeMstVO_p.getStrSeatid(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",storeMstVO_p.getStrHospitalCode(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_store_id", storeMstVO_p.getStrStoreId(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_store_type_id", storeMstVO_p.getStrStoretypeId(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_depart_id", storeMstVO_p.getStrDeptCode(),6);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8); // Cursor

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
			storeMstVO_p.setWrsStoreComboOptions(webRowSet);

		} catch (Exception exception) {
			throw new Exception("StoreMstDAO.getStoreCombo(StoreMstVO)-->"
					+ exception.getMessage());
		}

	}
	public static void getNatureOfServiceCombo(StoreMstVO storeMstVO_p, HisDAO hisDAO_p) throws Exception 
	{
		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_SEMT_NATURE_OF_SERVICE_MST(?,?,?,?,?, ?,?,?)}";
		final int nProcedureIndex;
		final String strDbErr;
		final WebRowSet webRowSet;
		try 
		{
			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id",storeMstVO_p.getStrSeatid(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code","100",3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_store_id", storeMstVO_p.getStrStoreId(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_store_type_id", storeMstVO_p.getStrStoretypeId(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_depart_id", storeMstVO_p.getStrDeptCode(),6);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8);

			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			if (strDbErr != null && !strDbErr.equals("")) 
			{
				throw new Exception("Data Base Error:" + strDbErr);
			}

			storeMstVO_p.setWrsNatureOfService(webRowSet);
		} 
		catch (Exception exception) 
		{
			throw new Exception("StoreMstDAO.getNatureOfServiceCombo(StoreMstVO)-->"+ exception.getMessage());
		}
	}
}
