package bmed.masters.dao;



import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.EquipmentTestMstVO;
import bmed.masters.vo.EquipmentTestParameterMstVO;
import hisglobal.transactionmgnt.HisDAO;

/**
 * @author Arun VR Creation Date:- 7-Aug-2012 Modifying Date:- 7-Aug-2012 Used
 *         For:- Team Lead By:- Module:- BMED(HIS Project)
 * 
 */
public class EquipmentTestParameterMstDAO {

	/**
	 * To Get Engineering Item Type Combo Values for Add Page.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void getTestNameCmbValues(EquipmentTestParameterMstVO vo) {

		HisDAO dao = null;
		int nqryIndex;
		String strQuery;
		WebRowSet wb;

		try {
			dao = new HisDAO("bmed", "EquipmentTestParameterMstDAO");

			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"equipmentTestParameterMst.testName.combo.0");
			nqryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());

			wb = dao.executeQry(nqryIndex);

			vo.setStrTestNameWS(wb);

		} catch (Exception e) {
			vo.setStrMsgString("--> EquipmentTestParaMstDAO.getEngineeringItemTypeCmbValues()-->"
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

	public static void setWrsAvailableParametersOptions(
			EquipmentTestParameterMstVO vo) throws Exception {

		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "ServiceEngineerExpertiseMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"equipmentTestParameterMst.availableParameter.combo");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrTestId());

			web = dao.executeQry(nQueryIndex);
			vo.setWrsAvailableParameterOptions(web);

		} catch (Exception e) {

			throw new Exception(
					"ServiceEngineerExpertiseMstDAO.initializeModify(vo) --> "
							+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}

	public static void setWrsSelectedParametersOptions(
			EquipmentTestParameterMstVO vo) throws Exception {

		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "ServiceEngineerExpertiseMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"equipmentTestParameterMst.selectedParameter.combo");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrTestId());

			web = dao.executeQry(nQueryIndex);
			vo.setWrsSelectedParameterOptions(web);

		} catch (Exception e) {

			throw new Exception(
					"ServiceEngineerExpertiseMstDAO.initializeModify(vo) --> "
							+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}

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
	public static void chkDuplicate(EquipmentTestParameterMstVO vo,
			String strInsertUpdate) {
		HisDAO dao = null;
		int nqryIndex = 0;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = new String();

		try {
			dao = new HisDAO("bmed", "EquipmentTestParameterMstDAO");
			if (strInsertUpdate.equals("insert")) {
				strquery = bmed.qryHandler_bmed.getQuery(1,
						"equipmentTestMst.insert.chkduplicate");
				nqryIndex = dao.setQuery(strquery);

				dao.setQryValue(nqryIndex, 1, vo.getStrTestId());
				// dao.setQryValue(nqryIndex, 2,
				// vo.getStrEngineeringItemSubTypeId());
				//
				// dao.setQryValue(nqryIndex, 3, vo.getStrTestName());

				dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			}

			else if (strInsertUpdate.equals("update")) {
				strquery = bmed.qryHandler_bmed.getQuery(1,
						"equipmentTestMst.update.chkduplicate");
				nqryIndex = dao.setQuery(strquery);

				dao.setQryValue(nqryIndex, 1, vo.getStrTestId());
				dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());

				// dao.setQryValue(nqryIndex, 4,
				// vo.getStrEngineeringItemSubTypeId());
				//
				// dao.setQryValue(nqryIndex, 5, vo.getStrTestName());
				dao.setQryValue(nqryIndex, 6, vo.getStrHospitalCode());
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
	public static void insertValues(EquipmentTestParameterMstVO vo) {

		HisDAO dao = null;

		int nProcIndex_U;

		String strProcName_U = "";

		try {
			dao = new HisDAO("bmed", "EquipmentTestMstDAO");

			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_TEST_PARAMETER_MST(?,?,?,?,?,?,?,?,?)}"; // Total
			// 9
			// Values

			nProcIndex_U = dao.setProcedure(strProcName_U);

			dao.setProcInValue(nProcIndex_U, "p_mode", "1", 1);
			dao.setProcInValue(nProcIndex_U, "strtestid", vo.getStrTestId(), 2);
			dao.setProcInValue(nProcIndex_U, "strparameterid",
					vo.getStrParameterId(), 3);
			dao.setProcInValue(nProcIndex_U, "strhospitalcode",
					vo.getStrHospitalCode(), 4);
			dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",
					vo.getStrEffectiveFrom(), 5);
			dao.setProcInValue(
					nProcIndex_U,
					"strRemarks",
					vo.getStrRemarks() == null || vo.getStrRemarks().equals("") ? "-"
							: vo.getStrRemarks(), 6);
			dao.setProcInValue(nProcIndex_U, "strSeatId", vo.getStrSeatId(), 7);

			/* Default Value */

			dao.setProcInValue(nProcIndex_U, "strIsValid", "1", 8);
			dao.setProcOutValue(nProcIndex_U, "err", 1, 9);

			dao.executeProcedureByPosition(nProcIndex_U);

			vo.setStrMsgType("0");

		} catch (Exception e) {
			// e.printStackTrace();
			vo.setStrMsgString("--> EquipmentTestMstDAO.insertValues()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	public static void deleteValues(EquipmentTestParameterMstVO vo) {

		HisDAO dao = null;

		int nProcIndex_U;

		String strProcName_U = "";

		try {
			dao = new HisDAO("bmed", "EquipmentTestMstDAO");

			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_TEST_PARAMETER_MST(?,?,?,?,?,?,?,?,?)}"; // Total
			// 9
			// Values
			nProcIndex_U = dao.setProcedure(strProcName_U);

			dao.setProcInValue(nProcIndex_U, "p_mode", "2");
			dao.setProcInValue(nProcIndex_U, "strtestid", vo.getStrTestId());
			dao.setProcInValue(nProcIndex_U, "strparameterid",
					vo.getStrParameterId());
			dao.setProcInValue(nProcIndex_U, "strhospitalcode",
					vo.getStrHospitalCode());
			dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",
					vo.getStrEffectiveFrom());
			dao.setProcInValue(nProcIndex_U, "strRemarks", vo.getStrRemarks());
			dao.setProcInValue(nProcIndex_U, "strSeatId", vo.getStrSeatId());

			/* Default Value */

			dao.setProcInValue(nProcIndex_U, "strIsValid", "1");
			dao.setProcOutValue(nProcIndex_U, "err", 1);

			dao.executeProcedure(nProcIndex_U);

			vo.setStrMsgType("0");

		} catch (Exception e) {
			// e.printStackTrace();
			vo.setStrMsgString("--> EquipmentTestMstDAO.insertValues()-->"
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
	public static void modifyRecord(EquipmentTestParameterMstVO vo) {

		HisDAO dao = null;
		int nqryIndex = 0;

		String strQuery;

		WebRowSet web = null;

		try {

			dao = new HisDAO("bmed", "EquipmentTestMstDAO");

			// Getting the Engineering Item Type Combo on the modify page

			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"equipmentTestParameterMst.modify");
			nqryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrTestId());

			web = dao.executeQry(nqryIndex);
			int i = 0;

			System.out.println("Size" + web.size());
			String arr[] = new String[web.size()];

			if (web.next()) {
				vo.setStrTestId(web.getString(1));

				arr[i] = web.getString(2);

				vo.setStrEffectiveFrom(web.getString(3));
				vo.setStrRemarks(web.getString(4));
				vo.setStrIsValid(web.getString(5));
				// vo.setStrEngineeringItemTypeId(web.getString(7));
				// vo.setStrEngineeringItemSubTypeId(web.getString(8));
				i++;

			}

			vo.setArrStrSelectedTestId(arr);
			web.close();
		}

		catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("--> EquipmentTestMstDAO.modifyRecord()-->"
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
	public static void updateQuery(EquipmentTestMstVO vo) {

		HisDAO dao = null;

		int nProcIndex_U;

		String strProcName_U = "";

		try {
			dao = new HisDAO("bmed", "EquipmentTestMstDAO");
			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_TEST_MST(?,?,?,?,?,?,?,?,?,?,?)}"; // Total
																								// 11
																								// Values
			nProcIndex_U = dao.setProcedure(strProcName_U);

			dao.setProcInValue(nProcIndex_U, "p_mode", "2");
			dao.setProcInValue(nProcIndex_U, "strSeatId", vo.getStrSeatId());
			dao.setProcInValue(nProcIndex_U, "strIsValid", vo.getStrIsValid());
			dao.setProcInValue(nProcIndex_U, "strEngineeringItemTypeId",
					vo.getStrEngineeringItemTypeId());
			dao.setProcInValue(nProcIndex_U, "strEngineeringItemSubTypeId",
					vo.getStrEngineeringItemSubTypeId());

			dao.setProcInValue(nProcIndex_U, "strTestName", vo.getStrTestName());
			dao.setProcInValue(nProcIndex_U, "strRemarks", vo.getStrRemarks());
			dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",
					vo.getStrEffectiveFrom());

			dao.setProcInValue(nProcIndex_U, "strTestId", vo.getStrTestId());
			dao.setProcInValue(nProcIndex_U, "strHospitalCode",
					vo.getStrHospitalCode());

			dao.setProcOutValue(nProcIndex_U, "err", 1); // default value

			dao.executeProcedure(nProcIndex_U);

			vo.setStrMsgType("0");

		} catch (Exception e) {
			// e.printStackTrace();
			vo.setStrMsgString("--> EquipmentTestMstDAO.updateQuery()-->"
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
