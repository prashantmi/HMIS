package ipd.masters.dao;

import ipd.masters.vo.GlobalBedStatusMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class GlobalBedStatusMstDAO {
	/***
	 * To insert new record
	 * @param globalBedStatusMstVO_p 
	 * @return
	 * @throws Exception
	 */
	public static boolean insertQuery(GlobalBedStatusMstVO globalBedStatusMstVO_p) throws Exception {
		// System.out.println("Inside Insert query");
		HisDAO hisDaoObj = null;
		int nqryIndex;
		boolean fretvalue = true;
		String strquery = new String();
		try {
			hisDaoObj = new HisDAO("ADT", "GlobalBedStatusMstDAO");

			strquery = ipd.qryHandler_ipd.getQuery(1, "insert.globalBedStatus.0");
			nqryIndex = hisDaoObj.setQuery(strquery);
			
			hisDaoObj.setQryValue(nqryIndex, 1, globalBedStatusMstVO_p.getStrHospitalCode());
			hisDaoObj.setQryValue(nqryIndex, 2, globalBedStatusMstVO_p.getStrBedStatusName());
			hisDaoObj.setQryValue(nqryIndex, 3, globalBedStatusMstVO_p.getStrEffectiveDate());
			hisDaoObj.setQryValue(nqryIndex, 4, globalBedStatusMstVO_p.getStrSeatId());
			hisDaoObj.setQryValue(nqryIndex, 5, globalBedStatusMstVO_p.getStrRemarks());
			hisDaoObj.setQryValue(nqryIndex, 6, globalBedStatusMstVO_p.getStrSeatId());
			hisDaoObj.setQryValue(nqryIndex, 7, globalBedStatusMstVO_p.getStrIsValid());
			hisDaoObj.setQryValue(nqryIndex, 8, globalBedStatusMstVO_p.getStrHospitalCode());
			hisDaoObj.execute(nqryIndex, 0);

			synchronized (hisDaoObj) {
				hisDaoObj.fire();
				fretvalue = true;
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			fretvalue = false;
			throw new Exception("GlobalBedStatusMstDAO.insertQuery() --> "
					+ e.getMessage());
		} finally {
			hisDaoObj.free();
			hisDaoObj = null;
		}
		return fretvalue;

	}
/**
 * Check for duplicate before inserting new record
 * @param globalBedStatusMstVO_p
 * @return
 * @throws Exception
 */
	public static boolean initialAddQuery(GlobalBedStatusMstVO globalBedStatusMstVO_p) throws Exception 
	{
		HisDAO hisDaoObj = new HisDAO("ADT", "GlobalBedStatusMstDAO");
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.globalBedStatus.2");

		try 
		{
			nqryIndex = hisDaoObj.setQuery(strquery);
			hisDaoObj.setQryValue(nqryIndex, 1, globalBedStatusMstVO_p.getStrBedStatusName());
			hisDaoObj.setQryValue(nqryIndex, 2, globalBedStatusMstVO_p.getStrHospitalCode());
			wb = hisDaoObj.executeQry(nqryIndex);
			while (wb.next()) 
			{
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount == 0) 
			{
				freturnValue = true;
			} 
			else 
			{
				freturnValue = false;
			}
		} 
		catch (Exception e) 
		{
			freturnValue = false;
			throw new Exception("GlobalBedStatusMstDAO.initialAddQuery() --> "+ e.getMessage());
		} 
		finally 
		{
			hisDaoObj.free();
			hisDaoObj = null;
		}	
		return freturnValue;
	}
/**
 * is used to bring data on modify page
 * @param chk1
 * @param vo
 * @throws Exception
 */
	public static void modifyQuery(String chk1, GlobalBedStatusMstVO globalBedStatusMstVO_p)
			throws Exception {

		HisDAO hisDaoObj = null;
		String strtemp[] = null;
		int nqryIndex;
		String strquery = new String();
		try {
			chk1=chk1.replace('@', '$');
			//System.out.println(""+chk1);
			strtemp = chk1.replace('$', '#').split("#");
			// System.out.println("strtemp[0]=="+strtemp[0]);
	         //System.out.println("strtemp[1]=="+strtemp[1]);
			hisDaoObj = new HisDAO("ipd", "GlobalBedStatusMstDAO");
			strquery = ipd.qryHandler_ipd.getQuery(1, "select.globalBedStatus.3");
			nqryIndex = hisDaoObj.setQuery(strquery);
			hisDaoObj.setQryValue(nqryIndex, 1, strtemp[0]);//bedstatus_code
			hisDaoObj.setQryValue(nqryIndex, 2, strtemp[1]);//hospital_code
			hisDaoObj.setQryValue(nqryIndex, 3, strtemp[2]);//serial no.
			WebRowSet web = hisDaoObj.executeQry(nqryIndex);
			while (web.next()) {
				globalBedStatusMstVO_p.setStrBedStatusName(web.getString(1));
				globalBedStatusMstVO_p.setStrEffectiveDate(web.getString(2));
				globalBedStatusMstVO_p.setStrRemarks(web.getString(3));
				globalBedStatusMstVO_p.setStrIsValid(web.getString(4));
			}
		} catch (Exception e) {
			throw new Exception("GlobalBedStatusMstDAO.modifyQuery() --> "
					+ e.getMessage());
		} finally {
			hisDaoObj.free();
			hisDaoObj = null;
		}

	}
/**
 * To update existing record
 * @param chk
 * @param globalBedStatusMstVO_p
 * @return
 * @throws Exception
 */
	public static boolean updateQuery(String chk, GlobalBedStatusMstVO globalBedStatusMstVO_p)
			throws Exception {
		boolean fretValue = true;
		HisDAO hisDaoObj = null;
		String strtemp[] = null;
		int nqryIndex;
		String strquery = new String();
		try {
			hisDaoObj = new HisDAO("IPD", "DAOBedStatus");
			strquery = ipd.qryHandler_ipd.getQuery(1, "update.globalBedStatus.1");
			nqryIndex = hisDaoObj.setQuery(strquery);
			chk=chk.replace('@', '$');
			// System.out.println("chk"+chk);
			strtemp = chk.replace('$', '#').split("#");
			hisDaoObj.setQryValue(nqryIndex, 1, globalBedStatusMstVO_p.getStrBedStatusName());
			hisDaoObj.setQryValue(nqryIndex, 2, globalBedStatusMstVO_p.getStrEffectiveDate());
			hisDaoObj.setQryValue(nqryIndex, 3, globalBedStatusMstVO_p.getStrLastModifiedSeatId()); 
			hisDaoObj.setQryValue(nqryIndex, 4, globalBedStatusMstVO_p.getStrRemarks());
			hisDaoObj.setQryValue(nqryIndex, 5, globalBedStatusMstVO_p.getStrIsValid());
			hisDaoObj.setQryValue(nqryIndex, 6, strtemp[0]);//bedstatus_code
			hisDaoObj.setQryValue(nqryIndex, 7, strtemp[1]);//hospital_code
			hisDaoObj.setQryValue(nqryIndex, 8, strtemp[2]);//serial no.
			hisDaoObj.execute(nqryIndex, 0);

			synchronized (hisDaoObj) {
				hisDaoObj.fire();
			}
		} catch (Exception e) {
			fretValue = false;
			throw new Exception("GlobalBedStatusMstDAO.updateQuery() --> "
					+ e.getMessage());
		} finally {
			hisDaoObj.free();
			hisDaoObj = null;
		}
		// System.out.println("Successfully Executed=>"+retvalue);
		return fretValue;
	}
/**
 * check duplicate data before updating record
 * @param chk
 * @param globalBedStatusMstVO_p
 * @return
 * @throws Exception
 */
	public static boolean initialUpdateQuery(String chk, GlobalBedStatusMstVO globalBedStatusMstVO_p)
			throws Exception {
		HisDAO hisDaoObj = new HisDAO("ipd", "GlobalBedStatusMstDAO");
		String strtemp[] = null;
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.globalBedStatus.4");
		try {
			chk=chk.replace('@', '$');
			strtemp = chk.replace('$', '#').split("#");
			nqryIndex = hisDaoObj.setQuery(strquery);
			hisDaoObj.setQryValue(nqryIndex, 1, strtemp[0]);
			hisDaoObj.setQryValue(nqryIndex, 2, globalBedStatusMstVO_p.getStrBedStatusName());
			hisDaoObj.setQryValue(nqryIndex, 3, strtemp[1]);//Hospital_code.
			hisDaoObj.setQryValue(nqryIndex, 4, strtemp[2]);//serial no.
			wb = hisDaoObj.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount < 1) {
				freturnValue = true;
			} else {
				freturnValue = false;
			}

		} catch (Exception e) {
			freturnValue = false;
			throw new Exception("GlobalBedStatusMstDAO.initialUpdateQuery() --> "
					+ e.getMessage());
		} finally {
			hisDaoObj.free();
			hisDaoObj = null;
		}
		return freturnValue;
	}
}