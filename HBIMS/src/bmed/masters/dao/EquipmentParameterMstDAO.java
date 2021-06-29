package bmed.masters.dao;



import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.EquipmentParameterMstVO;
import hisglobal.transactionmgnt.HisDAO;

/**
 * @author Arun VR Creation Date:- 06-August-2012 Modifying Date:- Used For:-
 *         Team Lead By:- Module:- BMED(HIS Project)
 * 
 */
public class EquipmentParameterMstDAO {

	/**
	 * Check duplicate while inserting records
	 * 
	 * @param vo
	 *            the vo
	 * @param strInsertUpdate
	 *            the String variable used to catch the parameter to know
	 *            whether duplicacy is checked for inserting a record or
	 *            updating it
	 */
	public static void chkDuplicate(EquipmentParameterMstVO vo,
			String strInsertUpdate) {
		HisDAO dao = null;
		int nqryIndex = 0;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = new String();

		try {
			dao = new HisDAO("bmed", "EquipmentParameterMstDAO");
			if (strInsertUpdate.equals("insert")) {
				strquery = bmed.qryHandler_bmed.getQuery(1,
						"equipmentParameterMst.insert.chkduplicate");
				nqryIndex = dao.setQuery(strquery);
				dao.setQryValue(nqryIndex, 1, vo.getStrParameterName());

				dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			}

			else if (strInsertUpdate.equals("update")) {
				strquery = bmed.qryHandler_bmed.getQuery(1,
						"equipmentParameterMst.update.chkduplicate");
				nqryIndex = dao.setQuery(strquery);

				dao.setQryValue(nqryIndex, 1, vo.getStrParameterId());
				dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());

				dao.setQryValue(nqryIndex, 3, vo.getStrParameterName());
				dao.setQryValue(nqryIndex, 4, vo.getStrHospitalCode());
			}

			vo.setStrMsgType("0");

			wb = dao.executeQry(nqryIndex);

			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}

			if (ncount == 0) {

				vo.setBExistStatus(true); // ncount=0 => no duplicacy, hence new
											// record will be saved
			} else {
				vo.setBExistStatus(false); // ncount!=0 => record already
											// exists, so new record will not be
											// added
			}
		} catch (Exception e) {
			vo.setStrMsgString("EquipmentTestMstDAO.chkDuplicate() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			wb = null;
		}
	}

	/**
	 * to insert the data into the tables
	 * 
	 * @param vo
	 */
	public static void insertValues(EquipmentParameterMstVO vo) {

		HisDAO dao = null;

		int nProcIndex_U;

		String strProcName_U = "";

		try {
			dao = new HisDAO("bmed", "EquipmentTestMstDAO");

			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_PARAMETER_MST(?,?,?,?,?,?,?,?,?)}"; // Total
																								// 9
			// Values
			nProcIndex_U = dao.setProcedure(strProcName_U);

			dao.setProcInValue(nProcIndex_U, "p_mode", "1", 1);
			dao.setProcInValue(nProcIndex_U, "strparameterid",
					vo.getStrParameterId(), 2);
			dao.setProcInValue(nProcIndex_U, "strHospitalCode",
					vo.getStrHospitalCode(), 3);
			dao.setProcInValue(nProcIndex_U, "strparametername",
					vo.getStrParameterName(), 4);
			dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",
					vo.getStrEffectiveFrom(), 5);
			dao.setProcInValue(nProcIndex_U, "strRemarks", vo.getStrRemarks(),
					6);
			dao.setProcInValue(nProcIndex_U, "strSeatId", vo.getStrSeatId(), 7);

			/* Default Value */

			dao.setProcInValue(nProcIndex_U, "strIsValid", "1", 8);
			dao.setProcOutValue(nProcIndex_U, "err", 1, 9);

			dao.executeProcedureByPosition(nProcIndex_U);

			vo.setStrMsgType("0");

		} catch (Exception e) {
			// e.printStackTrace();
			vo.setStrMsgString("--> EquipmentParameterMstDAO.insertValues()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	/**
	 * to get data for modify page.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void modifyRecord(EquipmentParameterMstVO vo) {

		HisDAO dao = null;
		int nqryIndex = 0;

		String strQuery;

		WebRowSet web = null;

		try {

			dao = new HisDAO("bmed", "EquipmentTestMstDAO");

			// Getting the Engineering Item Type Combo on the modify page

			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"equipmentParameterMst.modify");
			nqryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrParameterId());

			web = dao.executeQry(nqryIndex);
			if (web.next()) {
				vo.setStrParameterName(web.getString(1));
				vo.setStrEffectiveFrom(web.getString(2));
				vo.setStrRemarks(web.getString(3));
				vo.setStrIsValid(web.getString(4));

			}
			web.close();
		}

		catch (Exception e) {
			vo.setStrMsgString("--> EquipmentParameterMstDAO.modifyRecord()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}

		}
	}

	/**
	 * to update the record.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @throws Exception
	 */
	public static void updateQuery(EquipmentParameterMstVO vo) {

		HisDAO dao = null;

		int nProcIndex_U;

		String strProcName_U = "";

		try {
			dao = new HisDAO("bmed", "EquipmentTestMstDAO");
			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_PARAMETER_MST(?,?,?,?,?,?,?,?,?)}"; // Total
																								// 9
																								// Values
			nProcIndex_U = dao.setProcedure(strProcName_U);

			dao.setProcInValue(nProcIndex_U, "p_mode", "2", 1);
			dao.setProcInValue(nProcIndex_U, "strparameterid",
					vo.getStrParameterId(), 2);
			dao.setProcInValue(nProcIndex_U, "strHospitalCode",
					vo.getStrHospitalCode(), 3);
			dao.setProcInValue(nProcIndex_U, "strparametername",
					vo.getStrParameterName(), 4);
			dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",
					vo.getStrEffectiveFrom(), 5);
			dao.setProcInValue(nProcIndex_U, "strRemarks", vo.getStrRemarks(),
					6);
			dao.setProcInValue(nProcIndex_U, "strSeatId", vo.getStrSeatId(), 7);

			/* Default Value */

			dao.setProcInValue(nProcIndex_U, "strIsValid", vo.getStrIsValid(),
					8);
			dao.setProcOutValue(nProcIndex_U, "err", 1, 9);

			dao.executeProcedureByPosition(nProcIndex_U);

			vo.setStrMsgType("0");

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("--> EquipmentParameterMstDAO.updateQuery()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
}
