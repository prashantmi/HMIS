package mms.masters.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import mms.dao.CircularDAO;
import mms.masters.vo.CircularMstVO;


// TODO: Auto-generated Javadoc
/**
 * The Class InvoiceTypeMstDAO.
 */
public class CircularMstDAO {

	/**
	 * to check duplicate before insert record.
	 * 
	 * @param vo the vo
	 */
	public static void chkDuplicate(CircularMstVO vo) {
		HisDAO dao = null;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = new String();

		try {
			dao = new HisDAO("mms", "CircularMstDAO");
			strquery = mms.qryHandler_mms
					.getQuery(1, "select.circularMst.2");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrCircularName());
			dao.setQryValue(nqryIndex, 2, "100");
			dao.setQryValue(nqryIndex, 3, vo.getStrCircular());
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount == 0) {
				vo.setBExistStatus(true);
			} else {
				vo.setBExistStatus(false);
			}
		} catch (Exception e) {
			vo.setStrMsgString("CircularMstDAO.chkDuplicate() --> "
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
	 * @param vo the vo
	 */

	public static void insertQuery(CircularMstVO vo) {
		HisDAO dao = null;
		CircularDAO circularDao = null;

		try 
		{
			circularDao = new CircularDAO();
			dao = new HisDAO("mms", "CircularMstDAO");

			circularDao.setStrHospitalCode("100");
			circularDao.setStrCircularId(vo.getStrCircularId());
			circularDao.setStrRemarks(vo.getStrRemarks());
			circularDao.setStrCircularName(vo.getStrCircularName());
			
			circularDao.setStrCircularDate(vo.getStrCircularDate());
			circularDao.setStrCircularLink(vo.getStrCircularLink());
			circularDao.setStrCircularSubject(vo.getStrCircularSubject());
			circularDao.setStrCircularFileUpload(vo.getStrCircularFileUpload());
			
			circularDao.setStrSeatId(vo.getStrSeatId());
			circularDao.setStrIsValid(vo.getStrIsValid());
			circularDao.setStrCircular(vo.getStrCircular());
			
			circularDao.insert(dao);
			synchronized (dao)
			{
				dao.fire();
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("CircularMstDAO.insertQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}

			circularDao = null;

		}

	}

	/**
	 * to get data for modify page.
	 * 
	 * @param vo the vo
	 */

	public static void modifyQuery(CircularMstVO vo) {

		HisDAO dao = new HisDAO("mms", "CircularMstDAO");

		int nqryIndex;
		String strquery = new String();

		try {

			strquery = mms.qryHandler_mms
					.getQuery(1, "select.circularMst.3");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrCircularId());
			//dao.setQryValue(nqryIndex, 2, vo.getStrCircularId());

			WebRowSet web = dao.executeQry(nqryIndex);
			System.out.println(web.size());
			while (web.next()) {
				System.out.println("web.getString(1)"+web.getString(1));
				vo.setStrCircularId(web.getString(1));
				vo.setStrCircularName(web.getString(2));
				vo.setStrCircularSubject(web.getString(3));
				vo.setStrCircularLink(web.getString(4));
				vo.setStrCircularDate(web.getString(5));
			
			}
		} catch (Exception e) {

			vo.setStrMsgString("CircularMstDAO.modifyQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	public static String changeFileUpload(CircularMstVO vo) throws Exception {

		HisDAO dao = new HisDAO("mms", "CircularMstDAO");
       String FileName=null;
		int nqryIndex;
		String strquery = new String();

		try {

			strquery = mms.qryHandler_mms
					.getQuery(1, "select.circularMst.file.0");
			nqryIndex = dao.setQuery(strquery);

			
			//dao.setQryValue(nqryIndex, 2, vo.getStrCircularId());

			WebRowSet web = dao.executeQry(nqryIndex);
			System.out.println(web.size());
			while (web.next()) {
				
				FileName= web.getString(1);
			}
			
		} catch (Exception e) {

			vo.setStrMsgString("CircularMstDAO.modifyQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return FileName;
	}

	/**
	 * to check duplicate before update.
	 * 
	 * @param vo the vo
	 */

/*	public static void checkDuplicateRecord(CircularMstVO vo) {
		HisDAO dao = null;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = "";
		try {
			dao = new HisDAO("mms", "CircularMstDAO");
			strquery = mms.qryHandler_mms
					.getQuery(1, "select.circularMst.4");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrCircularName());
			dao.setQryValue(nqryIndex, 2, "100");
			dao.setQryValue(nqryIndex, 3, vo.getStrCircularId());
			dao.setQryValue(nqryIndex, 4, vo.getStrCircular());
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount < 1) {
				vo.setBExistStatus(true);
			} else {
				vo.setBExistStatus(false);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("--> CircularMstDAO.checkDuplicateRecord()-->"
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}

		}
	}
**/
	/**
	 * to update the record.
	 * 
	 * @param vo the vo
	 * 
	 * @throws Exception 	 */

	public static void updateQuery(CircularMstVO vo) {

		CircularDAO circularDAO = null;
		HisDAO dao = null;

		try {
			circularDAO = new CircularDAO();
			dao = new HisDAO("mms", "CircularMstDAO");

			circularDAO.setStrHospitalCode(vo.getStrHospitalCode());
	        circularDAO.setStrCircularId(vo.getStrCircularId());
			circularDAO.setStrCircularName(vo.getStrCircularName());
			circularDAO.setStrCircularSubject(vo.getStrCircularSubject());
			circularDAO.setStrCircularLink(vo.getStrCircularLink());
			circularDAO.setStrCircularDate(vo.getStrCircularDate());
			circularDAO.update(dao);

			synchronized (dao) {
				dao.fire();
			}
		} catch (Exception e) {

			e.printStackTrace();
			vo.setStrMsgString("--> CircularMstDAO.updateQuery()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			circularDAO = null;

		}

	}

}
