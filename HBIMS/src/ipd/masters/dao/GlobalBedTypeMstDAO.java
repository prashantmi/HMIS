package ipd.masters.dao;

import ipd.masters.vo.GlobalBedTypeMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class GlobalBedTypeMstDAO {
	public static boolean insertQuery(GlobalBedTypeMstVO globalBedTypeMstVO_p) throws Exception {
		// System.out.println("Inside Insert query");
		HisDAO dao = null;
		int nqryIndex;
		boolean fretValue = true;
		String strquery = new String();
		dao = new HisDAO("ipd", "GlobalBedTypeMstDAO");

		strquery = ipd.qryHandler_ipd.getQuery(1, "insert.globalBedType.0");
		nqryIndex = dao.setQuery(strquery);
		try {
			dao.setQryValue(nqryIndex, 1,globalBedTypeMstVO_p.getStrHospital_id());
			dao.setQryValue(nqryIndex, 2, globalBedTypeMstVO_p.getStrBedType());
			dao.setQryValue(nqryIndex, 3, globalBedTypeMstVO_p.getStrEffectiveDate());
			dao.setQryValue(nqryIndex, 4, globalBedTypeMstVO_p.getStrSeatId());
			dao.setQryValue(nqryIndex, 5, globalBedTypeMstVO_p.getStrRemarks());
			dao.setQryValue(nqryIndex, 6, globalBedTypeMstVO_p.getStrSeatId());
			dao.setQryValue(nqryIndex, 7, "1");
			dao.setQryValue(nqryIndex, 8,globalBedTypeMstVO_p.getStrHospital_id());
			dao.execute(nqryIndex, 0);

			synchronized (dao) {
				dao.fire();
				fretValue = true;
			}
		} catch (Exception e) {
			fretValue = false;
			throw new Exception("GlobalBedTypeMstDAO.insertQuery(globalBedTypeMstVO_p) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return fretValue;

	}

	public static boolean initialAddQuery(GlobalBedTypeMstVO globalBedTypeMstVO_p) throws Exception {
		HisDAO dao = new HisDAO("ipd", "GlobalBedTypeMstDAO");
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.globalBedType.2");

		try {
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, globalBedTypeMstVO_p.getStrBedType());
			dao.setQryValue(nqryIndex, 2, globalBedTypeMstVO_p.getStrHospital_id());
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
			throw new Exception("GlobalBedTypeMstDAO.initialAddQuery(globalBedTypeMstVO_p) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return freturnValue;
	}

	public static void modifyQuery(String chk1, GlobalBedTypeMstVO globalBedTypeMstVO_p)
			throws Exception {

		HisDAO dao = null;
		
		int nqryIndex;
		String strquery = new String();
				//strtemp = chk1.replace('$', '#').split("#");
		//strTemp1 = strtemp[0].replace('@', '#').split("#");
		
		try {
		//	String strtemp[] = null;
			String strTemp1[]=null;
			dao = new HisDAO("ipd", "GlobalBedTypeMstDAO");
			strquery = ipd.qryHandler_ipd.getQuery(1, "select.globalBedType.3");
			String strTemp[] = chk1.replace('$', '#').split("#");
			
			nqryIndex = dao.setQuery(strquery);
			//dao.setQryValue(nqryIndex, 1, strtemp[0]);
			
			strTemp1 = strTemp[0].replace('@', '#').split("#");
		
			dao.setQryValue(nqryIndex, 1, strTemp1[0]);
			dao.setQryValue(nqryIndex, 2, strTemp1[1]);
			dao.setQryValue(nqryIndex, 3, strTemp1[2].replace("|", "@").split("@")[0]);
			
			WebRowSet web = dao.executeQry(nqryIndex);
			while (web.next()) {
				globalBedTypeMstVO_p.setStrBedType(web.getString(1));
				globalBedTypeMstVO_p.setStrEffectiveDate(web.getString(2));
				globalBedTypeMstVO_p.setStrRemarks(web.getString(3));
				globalBedTypeMstVO_p.setStrIsValid(web.getString(4));
			
			}
		} catch (Exception e) {
			throw new Exception("GlobalBedTypeMstDAO.modifyQuery(chk,globalBedTypeMstVO_p) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
	}

	public static boolean updateQuery(String strChk_p, GlobalBedTypeMstVO globalBedTypeMstVO_p)
			throws Exception {
		
		boolean fretValue = true;
		HisDAO dao = null;
		String strtemp[] = null;
		int nqryIndex;
		String strquery = new String();
		try {
			dao = new HisDAO("IPD", "GlobalBedTypeMstDAO");
			strquery = ipd.qryHandler_ipd.getQuery(1, "update.globalBedType.1");
			nqryIndex = dao.setQuery(strquery);
			String strTemp1[]=null;
			// System.out.println("strChk_p"+strChk_p);
			strtemp = strChk_p.replace('$', '#').split("#");
			strTemp1 = strtemp[0].replace('@', '#').split("#");
			dao.setQryValue(nqryIndex, 1, globalBedTypeMstVO_p.getStrBedType());
			dao.setQryValue(nqryIndex, 2, globalBedTypeMstVO_p.getStrEffectiveDate());
			dao.setQryValue(nqryIndex, 3, globalBedTypeMstVO_p.getStrLastModifiedSeatId()); 
			dao.setQryValue(nqryIndex, 4, globalBedTypeMstVO_p.getStrRemarks());
			dao.setQryValue(nqryIndex, 5, globalBedTypeMstVO_p.getStrIsValid());
			dao.setQryValue(nqryIndex, 6,strTemp1[0]);//Global BedType Code
			dao.setQryValue(nqryIndex, 7, globalBedTypeMstVO_p.getStrHospital_id());
			dao.setQryValue(nqryIndex, 8,strTemp1[2].replace("|", "@").split("@")[0]);//Serial No
			dao.execute(nqryIndex, 0);

			synchronized (dao) {
				dao.fire();
			}
		} catch (Exception e) {
			fretValue = false;
			throw new Exception("GlobalBedTypeMstDAO.updateQuery(strChk_p,globalBedTypeMstVO_p) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		 //System.out.println("Successfully Executed=>"+fretValue);
		return fretValue;
	}

	public static boolean initialUpdateQuery(String strChk_p, GlobalBedTypeMstVO globalBedTypeMstVO_p)
			throws Exception {
	
		
		HisDAO dao = new HisDAO("ipd", "GlobalBedTypeMstDAO");
		String strtemp[] = null;
		String strtemp1[] = null;
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.globalBedType.4");
		try {
			strtemp = strChk_p.replace('$', '#').split("#");
			strtemp1 = strtemp[0].replace('@', '#').split("#");
			//System.out.println("bed type masrer code=="+strtemp1[0]);
			//System.out.println("bed type masrer bedname=="+ globalBedTypeMstVO_p.getStrBedType());
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, strtemp1[0]);//Globa Bed Type Code
			dao.setQryValue(nqryIndex, 2, globalBedTypeMstVO_p.getStrBedType());
			dao.setQryValue(nqryIndex, 3, strtemp1[1]);//Hospital Code
			dao.setQryValue(nqryIndex, 4, strtemp1[2].replace("|", "@").split("@")[0]);//Serial No
			
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
			e.printStackTrace();
			freturnValue = false;
			throw new Exception("GlobalBedTypeMstDAO.initialUpdateQuery(strChk_p,globalBedTypeMstVO_p) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		
	return freturnValue;
	}

}