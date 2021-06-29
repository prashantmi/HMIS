package ipd.masters.dao;

import ipd.masters.vo.BedStatusMstVO;
import ipd.masters.vo.BedTypeMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class BedStatusMstDAO {
	
	/* To Get Global Bed Status for Main Page.
	 * 
	 * @param vo the vo
	 */
	public static void getGlobalBedStatus(BedStatusMstVO vo) 
	throws Exception {

		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		WebRowSet wb = null;
		
		try {
			dao = new HisDAO("ipd", "BedStatusMstDAO");

			strquery = ipd.qryHandler_ipd.getQuery(1,"select.bedStatus.bedStatus.0");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			
			wb = dao.executeQry(nqryIndex);
			if(wb !=null && wb.size()>0){
				vo.setWrsGlobalBedStatus(wb);
			} 
				
			
		} catch (Exception e) {
			throw new Exception("ipd.BedStatusMstDAO.getGlobalBedStatus --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		
	}
	
	/***
	 * To insert new record
	 * @param vo 
	 * @return
	 * @throws Exception
	 */
	public static boolean insertQuery(BedStatusMstVO vo) throws Exception 
	{
		HisDAO dao = null;
		int nqryIndex;
		boolean fretvalue = true;
		String strquery = new String();
	
		try 
		{
			dao = new HisDAO("ADT", "BedStatusMstDAO");

			strquery = ipd.qryHandler_ipd.getQuery(1, "insert.bedStatus.0");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrGlobalBedStatus());
			dao.setQryValue(nqryIndex, 2, vo.getStrBedStatusName());
			dao.setQryValue(nqryIndex, 3, vo.getStrEffectiveDate());
			dao.setQryValue(nqryIndex, 4, vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 5, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 6, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 7, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 8, vo.getStrHospitalCode());
			dao.execute(nqryIndex, 0);

			synchronized (dao) 
			{
				dao.fire();
				fretvalue = true;
			}
		} 
		catch (Exception e) 
		{
			fretvalue = false;
			throw new Exception("BedStatusMstDAO.insertQuery() --> "+ e.getMessage());
		} 
		finally 
		{
			dao.free();
			dao = null;
		}
		return fretvalue;
	}
/**
 * Check for duplicate before inserting new record
 * @param vo
 * @return
 * @throws Exception
 */
	public static boolean initialAddQuery(BedStatusMstVO vo) throws Exception 
	{
		HisDAO dao = new HisDAO("ADT", "BedStatusMstDAO");
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.bedStatus.2");

		try 
		{
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrBedStatusName());
			wb = dao.executeQry(nqryIndex);
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
			throw new Exception("BedStatusMstDAO.initialAddQuery() --> "+ e.getMessage());
		} 
		finally 
		{
			dao.free();
			dao = null;
		}
	
		return freturnValue;
	}
/**
 * is used to bring data on modify page
 * @param chk1
 * @param vo
 * @throws Exception
 */
	public static void modifyQuery(String chk1, BedStatusMstVO vo)
			throws Exception {

		HisDAO dao = null;
		String strtemp[] = null;
		int nqryIndex;
		String strquery = new String();
		try {
			chk1=chk1.replace('@', '$');
			//System.out.println(""+chk1);
			strtemp = chk1.replace('$', '#').split("#");
			// System.out.println("strtemp[0]=="+strtemp[0]);
	         //System.out.println("strtemp[1]=="+strtemp[1]);
			dao = new HisDAO("ipd", "DAOBedStatusMst");
			strquery = ipd.qryHandler_ipd.getQuery(1, "select.bedStatus.3");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, strtemp[0]);//bedstatus_code
			dao.setQryValue(nqryIndex, 2, strtemp[1]);//hospital_code
			dao.setQryValue(nqryIndex, 3, strtemp[2]);//bed_status_code
			WebRowSet web = dao.executeQry(nqryIndex);
			while (web.next()) {
				vo.setStrBedStatusName(web.getString(1));
				vo.setStrEffectiveDate(web.getString(2));
				vo.setStrRemarks(web.getString(3));
				vo.setStrIsValid(web.getString(4));
			}
		} catch (Exception e) {
			throw new Exception("DAOBedStatusMst.modifyQuery() --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

	}
/**
 * To update existing record
 * @param chk
 * @param vo
 * @return
 * @throws Exception
 */
	public static boolean updateQuery(String chk, BedStatusMstVO vo) throws Exception 
	{
		boolean fretValue = true;
		HisDAO dao = null;
		String strtemp[] = null;
		int nqryIndex;
		String strquery = new String();
		
		try 
		{
			dao = new HisDAO("ADT", "BedStatusMstDAO");
			strquery = ipd.qryHandler_ipd.getQuery(1, "update.bedStatus.1");
			nqryIndex = dao.setQuery(strquery);
			chk=chk.replace('@', '$');
			strtemp = chk.replace('$', '#').split("#");
			dao.setQryValue(nqryIndex, 1, vo.getStrBedStatusName());
			dao.setQryValue(nqryIndex, 2, vo.getStrEffectiveDate());
			dao.setQryValue(nqryIndex, 3, vo.getStrLastModifiedSeatId()); 
			dao.setQryValue(nqryIndex, 4, vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 5, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 6, strtemp[0]);//bedstatus_code
			dao.setQryValue(nqryIndex, 7, strtemp[1]);//hospital_code
			dao.setQryValue(nqryIndex, 8, strtemp[2]);//bed_status_code
			dao.execute(nqryIndex, 0);

			synchronized (dao) 
			{
				dao.fire();
			}
		} 
		catch (Exception e) 
		{
			fretValue = false;
			throw new Exception("BedStatusMstDAO.updateQuery() --> "+ e.getMessage());
		} 
		finally 
		{
			dao.free();
			dao = null;
		}
		return fretValue;
	}
/**
 * check duplicate data before updating record
 * @param chk
 * @param vo
 * @return
 * @throws Exception
 */
	public static boolean initialUpdateQuery(String chk, BedStatusMstVO vo) throws Exception 
	{
		HisDAO dao = new HisDAO("ADT", "BedStatusMstDAO");
		String strtemp[] = null;
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.bedStatus.4");
	
		try 
		{
			chk=chk.replace('@', '$');
			strtemp = chk.replace('$', '#').split("#");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, strtemp[0]);
			dao.setQryValue(nqryIndex, 2, vo.getStrBedStatusName());
			dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) 
			{
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount < 1) 
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
			throw new Exception("BedStatusMstDAO.initialUpdateQuery() --> "	+ e.getMessage());
		} 
		finally 
		{
			dao.free();
			dao = null;
		}
		return freturnValue;
	}
}