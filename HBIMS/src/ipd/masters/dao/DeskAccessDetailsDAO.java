package ipd.masters.dao;

import ipd.masters.vo.DeskAccessDetailsVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;


/**
 * @author Adil Wasi
 *
 */
public class DeskAccessDetailsDAO {
	/**
	 * for getting option value of userName,DeskName combos and menu list 
	 * 
	 * @param deskAccessDetailsVO_p
	 * 
	 */
	public static void getInitialValues(DeskAccessDetailsVO deskAccessDetailsVO_p) {

		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		WebRowSet wb = null;
		
		try {
			dao = new HisDAO("transactionutil", "DeskAccessDetailsDAO");

			strquery = ipd.qryHandler_transactionutil.getQuery("Select.DeskAccessDtls.0");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, deskAccessDetailsVO_p.getStrHospitalCode());
			
			wb = dao.executeQry(nqryIndex);

			deskAccessDetailsVO_p.setUserNameComboWS(wb);
			
			strquery = ipd.qryHandler_transactionutil.getQuery("Select.DeskAccessDtls.1");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, deskAccessDetailsVO_p.getStrHospitalCode());
			
			wb = dao.executeQry(nqryIndex);

			deskAccessDetailsVO_p.setDeskNameComboWS(wb);
			
			
					

		} catch (Exception e) {
			deskAccessDetailsVO_p.setStrMsgString("-->DeskAccessDetailsDAO.getInitialValues()-->"
					+ e.getMessage());
			deskAccessDetailsVO_p.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	/**
	 * To get values of LEFT MENU LIST
	 * @param deskAccessDetailsVO_p
	 * 
	 */
	public static void getLeftListQuery(DeskAccessDetailsVO deskAccessDetailsVO_p) {

		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		WebRowSet wb = null;
		
		try {
			dao = new HisDAO("transactionutil", "DeskAccessDetailsDAO");

			strquery = ipd.qryHandler_transactionutil.getQuery("Select.DeskAccessDtls.2");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, deskAccessDetailsVO_p.getStrDeskId());
			dao.setQryValue(nqryIndex, 2, deskAccessDetailsVO_p.getStrUserId());
			dao.setQryValue(nqryIndex, 3, deskAccessDetailsVO_p.getStrDeskId());
			dao.setQryValue(nqryIndex, 4, deskAccessDetailsVO_p.getStrHospitalCode());
			
			wb = dao.executeQry(nqryIndex);

			deskAccessDetailsVO_p.setLeftMenuListWS(wb);
						

		} catch (Exception e) {
			deskAccessDetailsVO_p.setStrMsgString("-->DeskAccessDetailsDAO.getLeftListQuery()-->"
					+ e.getMessage());
			deskAccessDetailsVO_p.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	/**
	 * To get values of RIGHT MENU LIST
	 * @param deskAccessDetailsVO_p
	 * 
	 */
	public static void getRightListQuery(DeskAccessDetailsVO deskAccessDetailsVO_p) {

		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		WebRowSet wb = null;
		
		try {
			dao = new HisDAO("transactionutil", "DeskAccessDetailsDAO");

			strquery = ipd.qryHandler_transactionutil.getQuery("Select.DeskAccessDtls.3");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, deskAccessDetailsVO_p.getStrDeskId());
			dao.setQryValue(nqryIndex, 2, deskAccessDetailsVO_p.getStrUserId());
			dao.setQryValue(nqryIndex, 3, deskAccessDetailsVO_p.getStrDeskId());
			dao.setQryValue(nqryIndex, 4, deskAccessDetailsVO_p.getStrHospitalCode());
			
			wb = dao.executeQry(nqryIndex);

			deskAccessDetailsVO_p.setRightMenuListWS(wb);
			
		
					

		} catch (Exception e) {
			deskAccessDetailsVO_p.setStrMsgString("-->DeskAccessDetailsDAO.getRightListQuery()-->"
					+ e.getMessage());
			deskAccessDetailsVO_p.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	/**
	 * To delete the data before insert 
	 * @param deskAccessDetailsVO_p
	 * 
	 */
	public static void deleteQuery(DeskAccessDetailsVO deskAccessDetailsVO_p) {

		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		
		
		try {
			dao = new HisDAO("transactionutil", "DeskAccessDetailsDAO");

			strquery =ipd.qryHandler_transactionutil.getQuery("delete.DeskAccessDtls.0");
			nqryIndex = dao.setQuery(strquery);

			//System.out.println("deskAccessDetailsVO_p.getStrDeskId()"+deskAccessDetailsVO_p.getStrDeskId());
			//System.out.println("deskAccessDetailsVO_p.getStrMenuId()"+deskAccessDetailsVO_p.getStrMenuId());
			
			dao.setQryValue(nqryIndex, 1, deskAccessDetailsVO_p.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, deskAccessDetailsVO_p.getStrUserId());
			dao.setQryValue(nqryIndex, 3, deskAccessDetailsVO_p.getStrDeskId());
						
			dao.execute(nqryIndex,0);
			
			
			synchronized(dao)
			{
				dao.fire();
			}
						

		} catch (Exception e) {
			deskAccessDetailsVO_p.setStrMsgString("-->DeskAccessDetailsDAO.insertQuery()-->"
					+ e.getMessage());
			deskAccessDetailsVO_p.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	
	/**
	 * To insert the data
	 * @param deskAccessDetailsVO_p
	 * 
	 */
	public static void insertQuery(DeskAccessDetailsVO deskAccessDetailsVO_p) {

		HisDAO dao = null;
		int nqryIndex;
		String strquery = "";
		
		
		try {
			dao = new HisDAO("transactionutil", "DeskAccessDetailsDAO");

			
			
			strquery = ipd.qryHandler_transactionutil.getQuery("insert.DeskAccessDtls.0");
			nqryIndex = dao.setQuery(strquery);

			//System.out.println("deskAccessDetailsVO_p.getStrDeskId()"+deskAccessDetailsVO_p.getStrDeskId());
			//System.out.println("deskAccessDetailsVO_p.getStrMenuId()"+deskAccessDetailsVO_p.getStrMenuId());
			
			dao.setQryValue(nqryIndex, 1, deskAccessDetailsVO_p.getStrUserId());
			dao.setQryValue(nqryIndex, 2, deskAccessDetailsVO_p.getStrDeskId());
			dao.setQryValue(nqryIndex, 3, deskAccessDetailsVO_p.getStrMenuId());
			dao.setQryValue(nqryIndex, 4, deskAccessDetailsVO_p.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 5, deskAccessDetailsVO_p.getStrEffectiveFrom());
			dao.setQryValue(nqryIndex, 6, deskAccessDetailsVO_p.getStrSeatId());
			dao.setQryValue(nqryIndex, 7, deskAccessDetailsVO_p.getStrIsValid());
			dao.setQryValue(nqryIndex, 8, deskAccessDetailsVO_p.getStrMenuPosition());
						
			dao.execute(nqryIndex,0);

			synchronized(dao)
			{
				dao.fire();
			}
						

		} catch (Exception e) {
			e.printStackTrace();
			deskAccessDetailsVO_p.setStrMsgString("-->DeskAccessDetailsDAO.insertQuery()-->"
					+ e.getMessage());
			deskAccessDetailsVO_p.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
}
