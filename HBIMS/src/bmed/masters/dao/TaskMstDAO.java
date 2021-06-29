package bmed.masters.dao;

import hisglobal.hisconfig.Config;

import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.TaskMstVO;
import hisglobal.transactionmgnt.HisDAO;

/**
 * @author Vivek Aggarwal Creation Date:- 11-Jan-2011 Modifying Date:-
 *         20-Jan-2011 Used For:- Team Lead By:- Module:- BMED(HIS Project)
 * 
 */
public class TaskMstDAO {

	/**
	 * To Get Engineering Item Type Combo Values for Add Page.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void getEnggItemTypeCmbValues(TaskMstVO vo) {

		HisDAO dao = null;
		int nqryIndex;
		String strQuery;
		WebRowSet wb;

		try {
			dao = new HisDAO("bmed", "TaskMstDAO");

			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"taskMst.enggItemtype.combo.0");
			nqryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nqryIndex, 1, Config.SUPER_USER_HOSPITAL_CODE);

			wb = dao.executeQry(nqryIndex);

			vo.setStrEngineeringItemTypeWS(wb);

		} catch (Exception e) {
			vo.setStrMsgString("--> TaskMstDAO.getEngineeringItemTypeCmbValues()-->"
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
	 * To Get Engineering Item Sub Type Combo Values for Add Page.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void getEngineeringItemSubTypeCmbValues(TaskMstVO vo) {

		HisDAO dao = null;
		int nqryIndex;
		String strQuery;
		WebRowSet wb = null;

		try {
			dao = new HisDAO("bmed", "TaskMstDAO");

			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"taskMst.enggItemSubtype.combo.add.1");
			nqryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrEngineeringItemTypeId());

			wb = dao.executeQry(nqryIndex);

			vo.setStrEngineeringItemSubWS(wb);

		} catch (Exception e) {
			vo.setStrMsgString("--> TaskMstDAO.getEngineeringItemSubTypeCmbValues()-->"
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
	 * Check duplicate while inserting records
	 * 
	 * @param vo
	 *            the vo
	 * @param strInsertUpdate
	 *            the String variable used to catch the parameter to know
	 *            whether duplicacy is checked for inserting a record or
	 *            updating it
	 */
	public static void chkDuplicate(TaskMstVO vo, String strInsertUpdate) {
		HisDAO dao = null;
		int nqryIndex = 0;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = new String();

		try {
			dao = new HisDAO("bmed", "TaskMstDAO");
			if (strInsertUpdate.equals("insert")) {
				strquery = bmed.qryHandler_bmed.getQuery(1,
						"taskMst.insert.chkduplicate");
				nqryIndex = dao.setQuery(strquery);

				dao.setQryValue(nqryIndex, 1, vo.getStrEngineeringItemTypeId());
				dao.setQryValue(nqryIndex, 2,
						vo.getStrEngineeringItemSubTypeId());

				dao.setQryValue(nqryIndex, 3, vo.getStrTaskName());

				dao.setQryValue(nqryIndex, 4, vo.getStrHospitalCode());
			}

			else if (strInsertUpdate.equals("update")) {
				strquery = bmed.qryHandler_bmed.getQuery(1,
						"taskMst.update.chkduplicate");
				nqryIndex = dao.setQuery(strquery);

				dao.setQryValue(nqryIndex, 1, vo.getStrTaskId());
				dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());

				dao.setQryValue(nqryIndex, 3, vo.getStrEngineeringItemTypeId());
				dao.setQryValue(nqryIndex, 4,
						vo.getStrEngineeringItemSubTypeId());

				dao.setQryValue(nqryIndex, 5, vo.getStrTaskName());
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
			vo.setStrMsgString("TaskMstDAO.chkDuplicate() --> "
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
	public static void insertValues(TaskMstVO vo) {

		HisDAO dao = null;

		int nProcIndex_U;

		String strProcName_U = "";

		try {
			
			dao = new HisDAO("bmed", "TaskMstDAO");

			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_TASK_MST(?,?,?,?,?,?,?,?,?,?,?)}"; // Total
																							// 11
																							// Values
			nProcIndex_U = dao.setProcedure(strProcName_U);

			dao.setProcInValue(nProcIndex_U, "p_mode", "1");
			dao.setProcInValue(nProcIndex_U, "strEngineeringItemTypeId",
					(vo.getStrEngineeringItemTypeId() == null
							|| vo.getStrEngineeringItemTypeId().equals("") ? "0" : vo.getStrEngineeringItemTypeId()));
			dao.setProcInValue(nProcIndex_U, "strTaskId", "0");
			dao.setProcInValue(nProcIndex_U, "strHospitalCode",
					vo.getStrHospitalCode());
			dao.setProcInValue(nProcIndex_U, "strEngineeringItemSubTypeId",
					(vo.getStrEngineeringItemSubTypeId() == null
							|| vo.getStrEngineeringItemSubTypeId().equals("") ? "0" : vo.getStrEngineeringItemSubTypeId()));
			dao.setProcInValue(nProcIndex_U, "strTaskName", (vo.getStrTaskName() == null
					|| vo.getStrTaskName().equals("") ? "NA" : vo.getStrTaskName()));
			
			
			dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",
					vo.getStrEffectiveFrom());
			dao.setProcInValue(nProcIndex_U, "strRemarks", (vo.getStrRemarks() == null
					|| vo.getStrRemarks().equals("") ? "NA" : vo.getStrRemarks()));
			
			dao.setProcInValue(nProcIndex_U, "strSeatId", (vo.getStrSeatId() == null
					|| vo.getStrSeatId().equals("") ? "0" : vo.getStrSeatId()));

			/* Default Value */

			
			dao.setProcInValue(nProcIndex_U, "strIsValid", "1");
			dao.setProcOutValue(nProcIndex_U, "err", 1);

			dao.executeProcedure(nProcIndex_U);

			vo.setStrMsgType("0");

		} catch (Exception e) {
//			
			e.printStackTrace();
			vo.setStrMsgString("--> TaskMstDAO.insertValues()-->"
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
	public static void modifyRecord(TaskMstVO vo) {

		HisDAO dao = null;
		int nqryIndex = 0;

		String strQuery;

		WebRowSet web = null;

		try {

			dao = new HisDAO("bmed", "TaskMstDAO");

			// Getting the Engineering Item Type Combo on the modify page

			strQuery = bmed.qryHandler_bmed.getQuery(1, "taskMst.modify");
			nqryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nqryIndex, 1, Config.SUPER_USER_HOSPITAL_CODE);
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 3, vo.getStrTaskId());

			web = dao.executeQry(nqryIndex);
			if (web.next()) {
				vo.setStrEngineeringItemTypeName(web.getString(1));
				vo.setStrEngineeringItemSubTypeName(web.getString(2));
				vo.setStrTaskName(web.getString(3));
				vo.setStrEffectiveFrom(web.getString(4));
				vo.setStrRemarks(web.getString(5));
				vo.setStrIsValid(web.getString(6));
				vo.setStrEngineeringItemTypeId(web.getString(7));
				vo.setStrEngineeringItemSubTypeId(web.getString(8));
				
			}
			web.close();
		}

		catch (Exception e) {
			vo.setStrMsgString("--> TaskMstDAO.modifyRecord()-->"
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
	public static void updateQuery(TaskMstVO vo) {

		HisDAO dao = null;

		int nProcIndex_U;

		String strProcName_U = "";

		try {
			dao = new HisDAO("bmed", "TaskMstDAO");
			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_TASK_MST(?,?,?,?,?,?,?,?,?,?,?)}"; // Total
																							// 11
																							// Values
			nProcIndex_U = dao.setProcedure(strProcName_U);

			dao.setProcInValue(nProcIndex_U, "p_mode", "2");
			dao.setProcInValue(nProcIndex_U, "strEngineeringItemTypeId",
					(vo.getStrEngineeringItemTypeId() == null
							|| vo.getStrEngineeringItemTypeId().equals("") ? "0" : vo.getStrEngineeringItemTypeId()));
			dao.setProcInValue(nProcIndex_U, "strTaskId", vo.getStrTaskId());
			dao.setProcInValue(nProcIndex_U, "strHospitalCode",
					vo.getStrHospitalCode());
			dao.setProcInValue(nProcIndex_U, "strEngineeringItemSubTypeId",
					(vo.getStrEngineeringItemSubTypeId() == null
							|| vo.getStrEngineeringItemSubTypeId().equals("") ? "0" : vo.getStrEngineeringItemSubTypeId()));
			dao.setProcInValue(nProcIndex_U, "strTaskName", (vo.getStrTaskName() == null
					|| vo.getStrTaskName().equals("") ? "NA" : vo.getStrTaskName()));
			dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",
					vo.getStrEffectiveFrom());
			dao.setProcInValue(nProcIndex_U, "strRemarks", (vo.getStrRemarks() == null
					|| vo.getStrRemarks().equals("") ? "NA" : vo.getStrRemarks()));
			dao.setProcInValue(nProcIndex_U, "strSeatId", (vo.getStrSeatId() == null
					|| vo.getStrSeatId().equals("") ? "0" : vo.getStrSeatId()));
			dao.setProcInValue(nProcIndex_U, "strIsValid", (vo.getStrIsValid() == null
					|| vo.getStrIsValid().equals("") ? "0" : vo.getStrIsValid()));
			
	
			dao.setProcOutValue(nProcIndex_U, "err", 1); // default value

			dao.executeProcedure(nProcIndex_U);

			vo.setStrMsgType("0");

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("--> TaskMstDAO.updateQuery()-->"
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
