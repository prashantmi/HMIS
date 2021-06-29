package bmed.masters.dao;

import hisglobal.hisconfig.Config;

import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.NonItemMstVO;
import hisglobal.transactionmgnt.HisDAO;

/**
 * @author Vivek Aggarwal 
 * Creation Date:- 11-April-2011 
 * Modifying Date:- 13-April-2011 
 * Module:- BMED(HIS Project)
 * 
 */
public class NonItemMstDAO
{

	/**
	 * To Get Engineering Item Type Combo Values for Add Page.
	 * 
	 * @param vo_p	the NonItemMstVO
	 */
	public static void getEnggItemTypeCmbValues(NonItemMstVO vo_p) {

		HisDAO dao = null;
		int nqryIndex;
		String strQuery;
		WebRowSet wb;

		try {
			dao = new HisDAO("bmed", "NonItemMstDAO");

			strQuery = bmed.qryHandler_bmed.getQuery(1,	"nonItemMst.enggItemtype.combo.0");
			nqryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nqryIndex, 1, Config.SUPER_USER_HOSPITAL_CODE);

			wb = dao.executeQry(nqryIndex);

			vo_p.setStrEngineeringItemTypeWS(wb);

		} 
		catch (Exception e) 
		{
			vo_p.setStrMsgString("--> NonItemMstDAO.getEngineeringItemTypeCmbValues()-->"	+ e.getMessage());
			vo_p.setStrMsgType("1");
		}
		finally
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
			wb = null;
		}
	}

	/**
	 * To Get Engineering Item Sub Type Combo Values for Add Page.
	 * 
	 * @param vo_p	the NonItemMstVO
	 */
	public static void getEngineeringItemSubTypeCmbValues(NonItemMstVO vo_p) {

		HisDAO dao = null;
		int nqryIndex;
		String strQuery;
		WebRowSet wb = null;

		try {
			dao = new HisDAO("bmed", "NonItemMstDAO");

			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMst.enggItemSubtype.combo.add.1");
			nqryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nqryIndex, 1, vo_p.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo_p.getStrEngineeringItemTypeId());

			wb = dao.executeQry(nqryIndex);

			vo_p.setStrEngineeringItemSubWS(wb);

		}
		catch (Exception e) 
		{
			vo_p.setStrMsgString("--> NonItemMstDAO.getEngineeringItemSubTypeCmbValues()-->"
					+ e.getMessage());
			vo_p.setStrMsgType("1");
		} 
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
			wb = null;
		}
	}

	/**
	 * Check duplicate while inserting records
	 * 
	 * @param vo_p	the NonItemMstVO
	 * @param strInsertUpdate  the String variable used to catch the parameter to know
	 *     		whether duplicacy is checked for inserting a record or
	 *          updating it
	 */
	public static void chkDuplicate(NonItemMstVO vo_p, String strInsertUpdate) {
		HisDAO dao = null;
		int nqryIndex = 0;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = new String();

		try {
			dao = new HisDAO("bmed", "NonItemMstDAO");
			if (strInsertUpdate.equals("insert")) {
				strquery = bmed.qryHandler_bmed.getQuery(1,	"nonItemMst.insert.chkduplicate");
				nqryIndex = dao.setQuery(strquery);

				dao.setQryValue(nqryIndex, 1, vo_p.getStrEngineeringItemTypeId());
				dao.setQryValue(nqryIndex, 2, vo_p.getStrEngineeringItemSubTypeId());

				dao.setQryValue(nqryIndex, 3, vo_p.getStrNonItemName());

				dao.setQryValue(nqryIndex, 4, vo_p.getStrHospitalCode());
			}

			else if (strInsertUpdate.equals("update")) {
				strquery = bmed.qryHandler_bmed.getQuery(1,	"nonItemMst.update.chkduplicate");
				
				nqryIndex = dao.setQuery(strquery);

				dao.setQryValue(nqryIndex, 1, vo_p.getStrNonItemId());
				dao.setQryValue(nqryIndex, 2, vo_p.getStrHospitalCode());

				dao.setQryValue(nqryIndex, 3, vo_p.getStrEngineeringItemTypeId());
				dao.setQryValue(nqryIndex, 4, vo_p.getStrEngineeringItemSubTypeId());

				dao.setQryValue(nqryIndex, 5, vo_p.getStrNonItemName());
				dao.setQryValue(nqryIndex, 6, vo_p.getStrHospitalCode());
			}

			vo_p.setStrMsgType("0");

			wb = dao.executeQry(nqryIndex);

			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}

			if (ncount == 0) {

				vo_p.setBExistStatus(true); // ncount=0 => no duplicacy, hence new
											// record will be saved
			}
			else 
			{
				vo_p.setBExistStatus(false); // ncount!=0 => record already
											// exists, so new record will not be
											// added
			}
		} 
		catch (Exception e) 
		{
			vo_p.setStrMsgString("NonItemMstDAO.chkDuplicate() --> "	+ e.getMessage());
			vo_p.setStrMsgType("1");
		} 
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
			wb = null;
		}
	}

	/**
	 * to insert the data into the tables
	 * 
	 * @param vo_p	the NonItemMstVO
	 */
	public static void insertValues(NonItemMstVO vo_p) {

		HisDAO dao = null;

		int nProcIndex_U;

		String strProcName_U = "";

		try {
			dao = new HisDAO("bmed", "NonItemMstDAO");

			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_NONITEM_MST(?,?,?,?,?,?,?,?,?,?,?)}"; // Total
																							// 11
																							// Values
			nProcIndex_U = dao.setProcedure(strProcName_U);

			dao.setProcInValue(nProcIndex_U, "p_mode", "1");
			dao.setProcInValue(nProcIndex_U, "p_hemnum_engg_item_type_id",	(vo_p.getStrEngineeringItemTypeId() == null
					|| vo_p.getStrEngineeringItemTypeId().equals("") ? "0" : vo_p.getStrEngineeringItemTypeId()));
			dao.setProcInValue(nProcIndex_U, "p_hemnum_nonitem_id", "0");
			dao.setProcInValue(nProcIndex_U, "p_gnum_hospital_code", (vo_p.getStrHospitalCode() == null
					|| vo_p.getStrHospitalCode().equals("") ? "0" : vo_p.getStrHospitalCode()));
			dao.setProcInValue(nProcIndex_U, "p_hemnum_engg_item_sub_type_id", (vo_p.getStrEngineeringItemSubTypeId() == null
					|| vo_p.getStrEngineeringItemSubTypeId().equals("") ? "0" : vo_p.getStrEngineeringItemSubTypeId()));
			dao.setProcInValue(nProcIndex_U, "p_hemstr_nonitem_name", (vo_p.getStrNonItemName().trim() == null
					|| vo_p.getStrNonItemName().trim().equals("") ? "0" : vo_p.getStrNonItemName().trim()));
			
			dao.setProcInValue(nProcIndex_U, "p_gdt_effective_from", (vo_p.getStrEffectiveFrom() == null
					|| vo_p.getStrEffectiveFrom().equals("") ? "0" : vo_p.getStrEffectiveFrom()));
			dao.setProcInValue(nProcIndex_U, "p_gstr_remarks", (vo_p.getStrRemarks().trim() == null
					|| vo_p.getStrRemarks().trim().equals("") ? "NA" : vo_p.getStrRemarks().trim()));
			
			dao.setProcInValue(nProcIndex_U, "p_gnum_seatid", (vo_p.getStrSeatId() == null
					|| vo_p.getStrSeatId().equals("") ? "0" : vo_p.getStrSeatId()));
		
			dao.setProcInValue(nProcIndex_U, "p_gnum_isvalid", "1");
			dao.setProcOutValue(nProcIndex_U, "err", 1);

			dao.executeProcedure(nProcIndex_U);

			vo_p.setStrMsgType("0");

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			vo_p.setStrMsgString("--> NonItemMstDAO.insertValues()-->"
					+ e.getMessage());
			vo_p.setStrMsgType("1");
		} 
		finally 
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}
	}

	/**
	 * to get data for modify page.
	 * 
	 * @param vo_p	the NonItemMstVO
	 */
	public static void modifyRecord(NonItemMstVO vo_p) {

		HisDAO dao = null;
		int nqryIndex = 0;

		String strQuery;

		WebRowSet web = null;

		try {

			dao = new HisDAO("bmed", "NonItemMstDAO");

			// Getting the Engineering Item Type Combo on the modify page

			strQuery = bmed.qryHandler_bmed.getQuery(1, "nonItemMst.modify");
			nqryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nqryIndex, 1, vo_p.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo_p.getStrNonItemId());

			web = dao.executeQry(nqryIndex);
			if (web.next()) 
			{
				vo_p.setStrEngineeringItemTypeName(web.getString(1));
				vo_p.setStrEngineeringItemSubTypeName(web.getString(2));
				vo_p.setStrNonItemName(web.getString(3));
				vo_p.setStrEffectiveFrom(web.getString(4));
				vo_p.setStrRemarks(web.getString(5));
				vo_p.setStrIsValid(web.getString(6));
				vo_p.setStrEngineeringItemTypeId(web.getString(7));
				vo_p.setStrEngineeringItemSubTypeId(web.getString(8));
				
			}
			web.close();
		}

		catch (Exception e) {
			vo_p.setStrMsgString("--> NonItemMstDAO.modifyRecord()-->"	+ e.getMessage());
			vo_p.setStrMsgType("1");
		}
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
	}

	/**
	 * to update the record.
	 * 
	 * @param vo_p	the NonItemMstVO
	 * 
	 * @throws Exception
	 */
	public static void updateQuery(NonItemMstVO vo_p) {

		HisDAO dao = null;

		int nProcIndex_U;

		String strProcName_U = "";

		try {
			dao = new HisDAO("bmed", "NonItemMstDAO");
			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_NONITEM_MST(?,?,?,?,?,?,?,?,?,?,?)}"; // Total
																							// 11
																							// Values
			nProcIndex_U = dao.setProcedure(strProcName_U);

			dao.setProcInValue(nProcIndex_U, "p_mode", "2");
			dao.setProcInValue(nProcIndex_U, "p_hemnum_engg_item_type_id",	(vo_p.getStrEngineeringItemTypeId() == null
					|| vo_p.getStrEngineeringItemTypeId().equals("") ? "0" : vo_p.getStrEngineeringItemTypeId()));
			dao.setProcInValue(nProcIndex_U, "p_hemnum_nonitem_id",( vo_p.getStrNonItemId() == null
					||  vo_p.getStrNonItemId().equals("") ? "0" :  vo_p.getStrNonItemId()));
			dao.setProcInValue(nProcIndex_U, "p_gnum_hospital_code", (vo_p.getStrHospitalCode() == null
					|| vo_p.getStrHospitalCode().equals("") ? "0" : vo_p.getStrHospitalCode()));
			dao.setProcInValue(nProcIndex_U, "p_hemnum_engg_item_sub_type_id",	(vo_p.getStrEngineeringItemSubTypeId() == null
					|| vo_p.getStrEngineeringItemSubTypeId().equals("") ? "0" : vo_p.getStrEngineeringItemSubTypeId()));
			dao.setProcInValue(nProcIndex_U, "p_hemstr_nonitem_name", (vo_p.getStrNonItemName().trim() == null
					|| vo_p.getStrNonItemName().trim().equals("") ? "0" : vo_p.getStrNonItemName().trim()));
			dao.setProcInValue(nProcIndex_U, "p_gdt_effective_from", (vo_p.getStrEffectiveFrom() == null
					|| vo_p.getStrEffectiveFrom().equals("") ? "0" : vo_p.getStrEffectiveFrom()));
			dao.setProcInValue(nProcIndex_U, "p_gstr_remarks", (vo_p.getStrRemarks().trim() == null
					|| vo_p.getStrRemarks().trim().equals("") ? "NA" : vo_p.getStrRemarks().trim()));
			
			dao.setProcInValue(nProcIndex_U, "p_gnum_seatid", (vo_p.getStrSeatId() == null
					|| vo_p.getStrSeatId().equals("") ? "0" : vo_p.getStrSeatId()));
			dao.setProcInValue(nProcIndex_U, "p_gnum_isvalid", (vo_p.getStrIsValid() == null
					|| vo_p.getStrIsValid().equals("") ? "0" : vo_p.getStrIsValid()));
		
			dao.setProcOutValue(nProcIndex_U, "err", 1); // default value

			dao.executeProcedure(nProcIndex_U);

			vo_p.setStrMsgType("0");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			vo_p.setStrMsgString("--> NonItemMstDAO.updateQuery()-->"	+ e.getMessage());
			vo_p.setStrMsgType("1");
		} 
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
	}
}
