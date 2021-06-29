package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

//import mms.transactions.vo.WarrentyDetailsTransVO;

import bmed.reports.vo.EquipmentInspectionOrTestReportVO;

public class EquipmentInspectionOrTestReportDAO 
{

	/**
	 * To Get Data
	 * 
	 * @param complaintRequestDtlVO_p	the ComplaintRequestDtlVO
	 * @param hisDAO_p	the	HisDAO
	 * @throws Exception
	 */
	public static void getData(EquipmentInspectionOrTestReportVO equipmentInspectionOrTestReportVO_p,
			HisDAO hisDAO_p) throws Exception {

		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_COMPLAINT_REQUEST_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(equipmentInspectionOrTestReportVO_p);

			/* Setting and Registering In and Out Parameters */

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					equipmentInspectionOrTestReportVO_p.getStrMode());

			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					equipmentInspectionOrTestReportVO_p.getStrHospitalCode());
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dept_code",
					equipmentInspectionOrTestReportVO_p.getStrStoreId());
			hisDAO_p.setProcInValue(nProcedureIndex, "p_item_id",
					equipmentInspectionOrTestReportVO_p.getStrItemId());
			hisDAO_p.setProcInValue(nProcedureIndex, "p_test_id",
					equipmentInspectionOrTestReportVO_p.getStrTestId());
			hisDAO_p.setProcInValue(nProcedureIndex, "p_EquipmentTestSlNo_id",
					equipmentInspectionOrTestReportVO_p.getStrEquipmentTestSlNoId());
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_EquipmentTestChk_id",
					equipmentInspectionOrTestReportVO_p.getStrEquipmentTestChkDetail());
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id",equipmentInspectionOrTestReportVO_p.getStrSeatid());
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1); // 1 for
																	// varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2); // 2 for
																		// Cursor

			/* Executing Procedure */
			hisDAO_p.executeProcedure(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			equipmentInspectionOrTestReportVO_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"EquipmentInspectionOrTestReportDAO.getData(EquipmentInspectionOrTestReportVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}
	
	/** To get option value of Item Name combo 
	 * @param vo
	 */
	public static void getItemNameCombo(EquipmentInspectionOrTestReportVO vo) {

		HisDAO dao = null;
		
		WebRowSet wb = null;
		
		int nprocIndex;
		String proc_name = "";
		
		String strErr="";
			
			try {
				
				dao = new HisDAO("mms", "EquipmentInspectionOrTestReportDAO");

				proc_name = "{call pkg_bmed_view.proc_hstt_item_curstok_dtl_cmb(?,?,?,?,?)}";
				nprocIndex = dao.setProcedure(proc_name);
				
				
				dao.setProcInValue(nprocIndex, "p_mode", "1",1);
				
				dao.setProcInValue(nprocIndex, "p_hospital_code", vo.getStrHospitalCode(),2);
				dao.setProcInValue(nprocIndex, "p_store_id", vo.getStrStoreId(),3);
				
				dao.setProcOutValue(nprocIndex, "err", 1,4);
				dao.setProcOutValue(nprocIndex, "resultset", 2,5);
				
				dao.executeProcedureByPosition(nprocIndex);
				
				strErr=dao.getString(nprocIndex, "err");
				if (strErr == null)
					strErr = "";
				wb=dao.getWebRowSet(nprocIndex, "resultset");
				
				if (strErr.equals("")) {
					vo.setWrsItemName(wb);
					

				} else {
					throw new Exception(strErr);
				}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			/*vo.setStrMsgString(" --> WarrentyDetailsTransDAO.getItemNameCombo()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");*/
		} finally {
			if(dao!=null){
			dao.free();
			dao = null;}
			wb=null;
		}

	}
	
	
	/** To get option value of Equipment Test Sl. No. combo 
	 * @param vo
	 */
	public static void getEquipmentTestCombo(EquipmentInspectionOrTestReportVO vo) {

		HisDAO dao = null;
		
		WebRowSet wb = null;
		
		int nprocIndex;
		String proc_name = "";
		
		String strErr="";
			
			try {
				
				dao = new HisDAO("mms", "EquipmentInspectionOrTestReportDAO");

				proc_name = "{call pkg_bmed_view.proc_hemt_test_dtl_cmb(?,?,?,?,?)}";
				nprocIndex = dao.setProcedure(proc_name);
				
				
				dao.setProcInValue(nprocIndex, "p_mode", "1",1);
				dao.setProcInValue(nprocIndex, "p_hospital_code", vo.getStrHospitalCode(),2);
				dao.setProcInValue(nprocIndex, "p_item_id", (vo.getStrItemId()==null?"":vo.getStrItemId()),3);
				
				
				dao.setProcOutValue(nprocIndex, "err", 1,4);
				dao.setProcOutValue(nprocIndex, "resultset", 2,5);
				
				dao.executeProcedureByPosition(nprocIndex);
				
				strErr=dao.getString(nprocIndex, "err");
				if (strErr == null)
					strErr = "";
				wb=dao.getWebRowSet(nprocIndex, "resultset");
				
				if (strErr.equals("")) {
					vo.setWrsEquipmentSlNo(wb);
					

				} else {
					throw new Exception(strErr);
				}
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(dao!=null){
			dao.free();
			dao = null;}
			wb=null;
		}

	}
}
