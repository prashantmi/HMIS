package bmed.masters.dao;

import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.ExpertiseMstVO;
import hisglobal.transactionmgnt.HisDAO;

/**
 * @author Arun VR Creation Date:- 18-jan-2011 Modifying Date:- Used For:- Team
 *         Lead By:- Module:- BMED(HIS Project)
 * 
 */

public class ExpertiseMstDAO {

	/**
	 * Chk duplicate.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void chkDuplicate(ExpertiseMstVO vo) {
		HisDAO dao = null;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = new String();

		try {
			dao = new HisDAO("bmed", "ExpertiseMstDAO");
			strquery = bmed.qryHandler_bmed.getQuery(1,
					"expertiseMst.insert.chkduplicate");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrExpertiseName());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());

			vo.setStrMsgType("0");
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount == 0) {

				vo.setBExistStatus(false);
			} else {
				vo.setBExistStatus(true);
			}
		} catch (Exception e) {
			vo.setStrMsgString("ExpertiseMstDAO.chkDuplicate() --> "
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
	 * Chk duplicate.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void chkDuplicateUpdate(ExpertiseMstVO vo) {
		HisDAO dao = null;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = new String();

		try {
			dao = new HisDAO("bmed", "ExpertiseMstDAO");
			strquery = bmed.qryHandler_bmed.getQuery(1,
					"expertiseMst.update.chkduplicate");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrExpertiseName());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 3, vo.getStrSkillId());
			vo.setStrMsgType("0");
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount == 0) {

				vo.setBExistStatus(false);
			} else {
				vo.setBExistStatus(true);
			}
		} catch (Exception e) {
			vo.setStrMsgString("ExpertiseMstDAO.chkDuplicate() --> "
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
	 * to insert the data.
	 * 
	 * @param vo
	 *            the vo
	 */

	public static void insertQuery(ExpertiseMstVO vo) {
		HisDAO dao = null;
		int nProcIndex_U;
		String strProcName_U = "";
		try {
			dao = new HisDAO("bmed", "ExpertiseMstDAO");

			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_ENGG_SKILLS_MST(?,?,?,?,?,?,?,?,?)}"; // Total
																								// 9
																								// Values
			nProcIndex_U = dao.setProcedure(strProcName_U);
			dao.setProcInValue(nProcIndex_U, "p_mode", "1");
			dao.setProcInValue(
					nProcIndex_U,
					"strhospitalcode",
					(vo.getStrHospitalCode() == null
							|| vo.getStrHospitalCode().equals("") ? "0" : vo
							.getStrHospitalCode()));
			dao.setProcInValue(
					nProcIndex_U,
					"strexpertisename",
					(vo.getStrExpertiseName() == null
							|| vo.getStrExpertiseName().equals("") ? "NA" : vo
							.getStrExpertiseName()));
			dao.setProcInValue(
					nProcIndex_U,
					"streffectivefrom",
					(vo.getStrEffectiveFrom() == null
							|| vo.getStrEffectiveFrom().equals("") ? "0" : vo
							.getStrEffectiveFrom()));
			dao.setProcInValue(
					nProcIndex_U,
					"strremarks",
					(vo.getStrRemarks() == null
							|| vo.getStrRemarks().equals("") ? "NA" : vo
							.getStrRemarks()));
			dao.setProcInValue(
					nProcIndex_U,
					"strseatid",
					(vo.getStrSeatId() == null || vo.getStrSeatId().equals("") ? "0"
							: vo.getStrSeatId()));
			dao.setProcInValue(nProcIndex_U, "strIsValid", "1");
			dao.setProcInValue(nProcIndex_U, "strskillid", "0");
			dao.setProcOutValue(nProcIndex_U, "err", 1);
			dao.executeProcedure(nProcIndex_U);
			vo.setStrMsgType("0");

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("-->ExpertiseMstDAO.insert()-->"
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
	 * Modify.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void modify(ExpertiseMstVO vo) {
		HisDAO dao = null;
		String strQuery;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {

			dao = new HisDAO("bmed", "ExpertiseMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"expertiseMst.getExpertiseMstDeatils.1");
			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrSkillId());
			web = dao.executeQry(nQueryIndex);

			if (web.next()) {

				vo.setStrExpertiseName(web.getString(1));
				vo.setStrEffectiveFrom(web.getString(2));
				vo.setStrRemarks(web.getString(3));
				vo.setStrIsValid(web.getString(4));
			}

		} catch (Exception e) {
			// e.printStackTrace();
			vo.setStrMsgString("EngineeringItemSubMstDAO.modify() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;
		}
	}

	/**
	 * This function is used to update record during click of modify.
	 * 
	 * @param vo
	 *            the vo
	 */

	public static void updateRecord(ExpertiseMstVO vo)

	{
		HisDAO dao = null;

		int nProcIndex_U;

		String strProcName_U;

		try {

			dao = new HisDAO("bmed", "ExpertiseMstDAO");

			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_ENGG_SKILLS_MST(?,?,?,?,?,?,?,?,?)}"; // Total
																								// 9
																								// Values
			nProcIndex_U = dao.setProcedure(strProcName_U);

			dao.setProcInValue(nProcIndex_U, "p_mode", "2");
			dao.setProcInValue(
					nProcIndex_U,
					"strHospitalCode",
					(vo.getStrHospitalCode() == null
							|| vo.getStrHospitalCode().equals("") ? "0" : vo
							.getStrHospitalCode()));
			dao.setProcInValue(
					nProcIndex_U,
					"strexpertisename",
					(vo.getStrExpertiseName() == null
							|| vo.getStrExpertiseName().equals("") ? "NA" : vo
							.getStrExpertiseName()));
			dao.setProcInValue(
					nProcIndex_U,
					"strEffectiveFrom",
					(vo.getStrEffectiveFrom() == null
							|| vo.getStrEffectiveFrom().equals("") ? "0" : vo
							.getStrEffectiveFrom()));
			dao.setProcInValue(
					nProcIndex_U,
					"strRemarks",
					(vo.getStrRemarks() == null
							|| vo.getStrRemarks().equals("") ? "NA" : vo
							.getStrRemarks()));
			dao.setProcInValue(
					nProcIndex_U,
					"strSeatId",
					(vo.getStrSeatId() == null || vo.getStrSeatId().equals("") ? "0"
							: vo.getStrSeatId()));
			dao.setProcInValue(
					nProcIndex_U,
					"strIsValid",
					(vo.getStrIsValid() == null
							|| vo.getStrIsValid().equals("") ? "0" : vo
							.getStrIsValid()));
			dao.setProcInValue(
					nProcIndex_U,
					"strskillid",
					(vo.getStrSkillId() == null
							|| vo.getStrSkillId().equals("") ? "0" : vo
							.getStrSkillId()));
			dao.setProcOutValue(nProcIndex_U, "err", 1);
			dao.executeProcedure(nProcIndex_U);
			vo.setStrMsgType("0");

		} catch (Exception e) {
			// e.printStackTrace();
			vo.setStrMsgString("-->ExpertiseMstDAO.insert()-->"
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
