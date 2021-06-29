package ipd.masters.dao;

import ipd.masters.vo.BedTypeMstVO;
import ipd.masters.vo.WardTypeMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class BedTypeMstDAO {
	
	/* To Get Global Bed Type for Main Page.
	 * 
	 * @param vo the vo
	 */
	public static void getGlobalBedType(BedTypeMstVO vo) 
	throws Exception {

		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		WebRowSet wb = null;
		
		try {
			dao = new HisDAO("ipd", "BedTypeMstDAO");

			strquery = ipd.qryHandler_ipd.getQuery(1,"select.bedtype.globalBedType.0");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrHospital_id());
			
			wb = dao.executeQry(nqryIndex);
			if(wb !=null && wb.size()>0){
				vo.setWrsGlobalBedType(wb);
			} 
				
			
		} catch (Exception e) {
			throw new Exception("ipd.BedTypeMstDAO.getGlobalBedType --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		
	}
	
	
	public static boolean insertQuery(BedTypeMstVO vo) throws Exception {
		// System.out.println("Inside Insert query");
		HisDAO dao = null;
		int nqryIndex;
		boolean fretValue = true;
		String strquery = new String();
		dao = new HisDAO("ipd", "BedTypeMstDAO");

		strquery = ipd.qryHandler_ipd.getQuery(1, "insert.bedType.0");
		nqryIndex = dao.setQuery(strquery);
		try {
			dao.setQryValue(nqryIndex, 1,vo.getStrGlobalBedType());
			dao.setQryValue(nqryIndex, 2, vo.getStrBedType());
			dao.setQryValue(nqryIndex, 3, vo.getStrEffectiveDate());
			dao.setQryValue(nqryIndex, 4, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 5, vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 6, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 7, "1");
			dao.setQryValue(nqryIndex, 8,vo.getStrHospital_id());
			dao.setQryValue(nqryIndex, 9,vo.getStrHospital_id());
			dao.execute(nqryIndex, 0);

			synchronized (dao) {
				dao.fire();
				fretValue = true;
			}
		} catch (Exception e) {
			fretValue = false;
			throw new Exception("BedTypeMstDAO.insertQuery(vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return fretValue;

	}

	public static boolean initialAddQuery(BedTypeMstVO vo) throws Exception {
		HisDAO dao = new HisDAO("ipd", "BedTypeMstDAO");
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.bedType.2");

		try {
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrBedType());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospital_id());
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount == 0) {
				freturnValue = true;
			} else {
				freturnValue = false;
			}
		} catch (Exception e) {
			freturnValue = false;
			throw new Exception("BedTypeMstDAO.initialAddQuery(vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return freturnValue;
	}

	public static void modifyQuery(String chk1, BedTypeMstVO vo)
			throws Exception {

		HisDAO dao = null;
		
		int nqryIndex;
		String strquery = new String();
				//strtemp = chk1.replace('$', '#').split("#");
		//strTemp1 = strtemp[0].replace('@', '#').split("#");
		
		try {
		//	String strtemp[] = null;
			String strTemp1[]=null;
			dao = new HisDAO("ipd", "BedTypeMstDAO");
			strquery = ipd.qryHandler_ipd.getQuery(1, "select.bedType.3");
			String strTemp[] = chk1.replace('$', '#').split("#");
			
			nqryIndex = dao.setQuery(strquery);
			//dao.setQryValue(nqryIndex, 1, strtemp[0]);
			
			strTemp1 = strTemp[0].replace('@', '#').split("#");
			for (int i = 0; i < strTemp1.length; i++) {
				dao.setQryValue(nqryIndex, (i + 1), strTemp1[i]);
			}
			
			WebRowSet web = dao.executeQry(nqryIndex);
			while (web.next()) {
				vo.setStrBedType(web.getString(1));
				vo.setStrEffectiveDate(web.getString(2));
				vo.setStrRemarks(web.getString(3));
				vo.setStrIsValid(web.getString(4));
			
			}
		} catch (Exception e) {
			throw new Exception("BedTypeMstDAO.modifyQuery(chk,vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
	}

	public static boolean updateQuery(String chk, BedTypeMstVO vo)
			throws Exception {
		
		boolean fretValue = true;
		HisDAO dao = null;
		String strtemp[] = null;
		int nqryIndex;
		String strquery = new String();
		try {
			dao = new HisDAO("IPD", "BedTypeMstDAO");
			strquery = ipd.qryHandler_ipd.getQuery(1, "update.bedType.1");
			nqryIndex = dao.setQuery(strquery);
			String strTemp1[]=null;
			// System.out.println("chk"+chk);
			strtemp = chk.replace('$', '#').split("#");
			strTemp1 = strtemp[0].replace('@', '#').split("#");
			dao.setQryValue(nqryIndex, 1, vo.getStrBedType());
			dao.setQryValue(nqryIndex, 2, vo.getStrEffectiveDate());
			dao.setQryValue(nqryIndex, 3, vo.getStrLastModifiedSeatId()); 
			dao.setQryValue(nqryIndex, 4, vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 5, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 6,strTemp1[0]);
			dao.setQryValue(nqryIndex, 7, vo.getStrHospital_id());
			dao.setQryValue(nqryIndex, 8,strTemp1[2]);
			dao.execute(nqryIndex, 0);

			synchronized (dao) {
				dao.fire();
			}
		} catch (Exception e) {
			fretValue = false;
			throw new Exception("BedTypeMstDAO.updateQuery(chk,vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		 //System.out.println("Successfully Executed=>"+fretValue);
		return fretValue;
	}

	public static boolean initialUpdateQuery(String chk, BedTypeMstVO vo)
			throws Exception {
	
		
		HisDAO dao = new HisDAO("ipd", "BedTypeMstDAO");
		String strtemp[] = null;
		String strtemp1[] = null;
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.bedType.4");
		try {
			strtemp = chk.replace('$', '#').split("#");
			strtemp1 = strtemp[0].replace('@', '#').split("#");
			//System.out.println("bed type masrer code=="+strtemp1[0]);
			//System.out.println("bed type masrer bedname=="+ vo.getStrBedType());
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, strtemp1[0]);
			dao.setQryValue(nqryIndex, 2, vo.getStrBedType());
			dao.setQryValue(nqryIndex, 3, strtemp1[1]);
			dao.setQryValue(nqryIndex, 4, strtemp1[2]);
			
			wb = dao.executeQry(nqryIndex);
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
			throw new Exception("BedTypeMstDAO.initialUpdateQuery(chk,vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		
	return freturnValue;
	}

}