/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportTemplateMstDAO.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import dynamicreports.masters.vo.DynamicReportTemplateMstVO;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportTemplateMstDAO.
 */
public class DynamicReportTemplateMstDAO {

	/**
	 * Gets the report header type.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report header type
	 */
	public static void getReportHeaderType(DynamicReportTemplateMstVO vo) {
		HisDAO dao = null;
		int nqryIndex;
		WebRowSet wb = null;

		try {
			dao = new HisDAO("dynamicreports", "DynamicReportTemplateMstDAO");
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "select.headerType");
			nqryIndex = dao.setQuery(strquery);

			wb = dao.executeQry(nqryIndex);

			vo.setWsReportHeaderTypes(wb);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportTemplateMstDAO.getReportHeaderType() --> "
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
	 * Check the Record weather data is Duplicate or not.
	 * 
	 * @param vo
	 *            - FormBean Object
	 * @return - true -record cannot be saved ,already exist false - record will
	 *         save
	 */
	public static void chkDuplicate(DynamicReportTemplateMstVO vo) {
		HisDAO dao = null;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;

		try {
			dao = new HisDAO("dynamicreports", "DynamicReportTemplateMstDAO");
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "select.drptTemplateMst.1");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrReportName());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}

			if (ncount == 0) {
				vo.setbExistStatus(true);
			} else {
				vo.setbExistStatus(false);
			}
		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportTemplateMstDAO.chkDuplicate() --> "
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
	public static synchronized void insertQuery(DynamicReportTemplateMstVO vo) {
		HisDAO dao = null;
		int nqryIndex;

		String strquery = "";

		try {

			dao = new HisDAO("dynamicreports", "DynamicReportTemplateMstDAO");

			strquery = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptTemplateMst.0");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrReportTypeId());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 3, vo.getStrReportTypeId());
			dao.setQryValue(nqryIndex, 4, vo.getStrHospitalCode());

			dao.setQryValue(nqryIndex, 5, vo.getStrDisplayName());
			dao.setQryValue(nqryIndex, 6, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 7, vo.getStrReportWidth());
			dao.setQryValue(nqryIndex, 8, vo.getStrReportWidthUnit());
			dao.setQryValue(nqryIndex, 9, vo.getStrReportName());
			dao.setQryValue(nqryIndex, 10, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 11, vo.getStrIsBorder());
			dao.setQryValue(nqryIndex, 12, vo.getStrReportHeaderType());

			dao.execute(nqryIndex, 0);

			dao.fire();

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportTemplateMstDAO.insertQuery() --> "
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
	 * retrieves and executes modify Query.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void modifyQuery(DynamicReportTemplateMstVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportTemplateMstDAO");

		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "select.drptTemplateMst.2");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrReportTemplateId());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());

			WebRowSet web = dao.executeQry(nqryIndex);
			while (web.next()) {

				vo.setStrReportName(web.getString(1));
				vo.setStrDisplayName(web.getString(2));
				vo.setStrReportWidth(web.getString(3));
				vo.setStrReportWidthUnit(web.getString(4));
				vo.setStrIsBorder(web.getString(5));
				vo.setStrReportHeaderType(web.getString(6));

			}

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportTemplateMstDAO.modifyQuery() --> "
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
	 * to check duplicate before update.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void initialUpdateQuery(DynamicReportTemplateMstVO vo) {
		HisDAO dao = null;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = "";
		try {
			dao = new HisDAO("dynamicreports", "DynamicReportTemplateMstDAO");
			strquery = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"select.drptTemplateMst.3");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrReportName());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 3, vo.getStrReportTemplateId());
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount < 1) {
				vo.setbExistStatus(true);
			} else {
				vo.setbExistStatus(false);
			}

		} catch (Exception e) {
			vo.setStrMsgString("--> DynamicReportTemplateMstDAO.initialUpdateQuery()-->"
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
	 * retrieves and executes update Query.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static synchronized void updateQuery(DynamicReportTemplateMstVO vo) {

		HisDAO dao = null;
		int nqryIndex;

		try {

			dao = new HisDAO("dynamicreports", "DynamicReportTemplateMstDAO");

			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "update.drptTemplateMst.1");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrDisplayName());
			dao.setQryValue(nqryIndex, 2, vo.getStrReportName());
			dao.setQryValue(nqryIndex, 3, vo.getStrReportWidth());
			dao.setQryValue(nqryIndex, 4, vo.getStrReportWidthUnit());
			dao.setQryValue(nqryIndex, 5, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 6, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 7, vo.getStrIsBorder());
			dao.setQryValue(nqryIndex, 8, vo.getStrReportHeaderType());
			dao.setQryValue(nqryIndex, 9, vo.getStrReportTemplateId());
			dao.setQryValue(nqryIndex, 10, vo.getStrHospitalCode());

			dao.execute(nqryIndex, 0);

			dao.fire();

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportTemplateMstDAO.updateQuery() --> "
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
