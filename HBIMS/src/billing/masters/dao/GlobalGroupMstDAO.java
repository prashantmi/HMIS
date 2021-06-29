package billing.masters.dao;
/* Global Group Master DAO
 * author: Debashis Sardar
 * Created on : 26-Aug-2011
 */
import hisglobal.transactionmgnt.HisDAO;
import javax.sql.rowset.WebRowSet;
import billing.BillConfigUtil;
import billing.masters.vo.GlobalGroupMstVO;
import hisglobal.utility.GetNetworkAddress;

public class GlobalGroupMstDAO {
	/**
	 * retrieves insert Query and executes it.
	 * @param vo
	 * @return true - if Record Inserted Successfully. <br>
	 *         false - if Record Not Inserted Successfully.
	 */

	public static boolean insertQuery(GlobalGroupMstVO vo) throws Exception {
		
		HisDAO dao = null;
		int qryIndex;
		boolean retvalue = true;
		String query = new String();
		try {
			dao = new HisDAO("Billing", "GlobalGroupMstDAO");
			query = billing.qryHandler_billing.getQuery(1, "insert.group.0");
			
			qryIndex = dao.setQuery(query);
			
				
			dao.setQryValue(qryIndex, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			dao.setQryValue(qryIndex, 2,  BillConfigUtil.SUPER_HOSPITAL_CODE);
			dao.setQryValue(qryIndex, 3, vo.getStrgroupName());
			dao.setQryValue(qryIndex, 4, vo.getStrseatId());
			dao.setQryValue(qryIndex, 5, vo.getStrgroupCode());
			dao.setQryValue(qryIndex, 6, GetNetworkAddress.GetAddress("ip"));
			dao.setQryValue(qryIndex, 7, GetNetworkAddress.GetAddress("mac"));
			
			dao.execute(qryIndex, 0);

			synchronized (dao) {
				dao.fire();
				retvalue = true;

			}

		} catch (Exception e) {
			retvalue = false;
			e.printStackTrace();
			vo.setStrMsgString("billing.GlobalGroupMstDAO.insertQuery()"+ e.getMessage());
			vo.setStrMsgType("1");
			

		} finally {
			dao.free();
			dao = null;
		}
		return retvalue;

	}
	/**
	 * retrieves and executes Modify Query
	 * 
	 * @param vo
	 * @param Chk1
	 */
	public static void modifyQuery(String chk1, GlobalGroupMstVO vo) throws Exception {

		
		HisDAO dao = null;
		String strtemp[] = null;
		String strtemp2[] = null;
		
		int qryIndex;
		String query = new String();
        strtemp = chk1.replace('@', '#').split("#");
		strtemp2 = strtemp[1].replace("|", "#").split("#");
		strtemp[1] = strtemp2[0];

		
		try {
			dao = new HisDAO("Billing", "GlobalGroupMstDAO");
			query = billing.qryHandler_billing.getQuery(1, "select.group.1");

			
			qryIndex = dao.setQuery(query);
			
			dao.setQryValue(qryIndex, 1, strtemp[0]);
			dao.setQryValue(qryIndex, 2, strtemp[1]);
			
			WebRowSet web = dao.executeQry(qryIndex);
			while (web.next()) {

				vo.setStrgroupName(web.getString(1));
				vo.setStrisPackage(web.getString(2));
				vo.setStrEffectiveFrom(web.getString(3));
				vo.setStrisValid(web.getString(4));
				vo.setStrremark(web.getString(5));
				vo.setStrisVisible(web.getString(6));
				vo.setStrseatId(web.getString(7));
				vo.setStrgroupCode(web.getString(8));
				

			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("billing.GlobalGroupMstDAO.modifyQuery"
					+ e.getMessage());
			vo.setStrMsgType("1");
			

		} finally {
			dao.free();
			dao = null;
		}

	}
	/**
	 * retrieves and executes update Query
	 * @param Chk
	 * @param vo
	 * @return true - if Record Updated Successfully. <br>
	 *         false - if Record Not Updated Successfully.
	 */

	public static boolean updateQuery(String chk, GlobalGroupMstVO vo)
			throws Exception {
		boolean retvalue = false;
		HisDAO dao = null;
		
		String strtemp[] = null;
		String strtemp2[] = null;
		int qryIndex;
		String query = new String();
		try {

			dao = new HisDAO("Billing", "GlobalGroupMstDAO");
			query = billing.qryHandler_billing.getQuery(1, "update.group.1");
			qryIndex = dao.setQuery(query);
			strtemp = chk.replace('@', '#').split("#");
			strtemp2 = strtemp[1].replace("|", "#").split("#");
			strtemp[1] = strtemp2[0];
			
			dao.setQryValue(qryIndex, 1, vo.getStrgroupName());
			
			dao.setQryValue(qryIndex, 2, vo.getStrseatId());
			
			dao.setQryValue(qryIndex, 3, vo.getStrisValid());
			dao.setQryValue(qryIndex, 4, vo.getStrgroupCode());
			dao.setQryValue(qryIndex, 5, GetNetworkAddress.GetAddress("ip"));
			dao.setQryValue(qryIndex, 6, GetNetworkAddress.GetAddress("mac"));
			dao.setQryValue(qryIndex, 7, BillConfigUtil.SUPER_HOSPITAL_CODE);
			dao.setQryValue(qryIndex, 8, strtemp[1]);
			dao.execute(qryIndex,0);

			synchronized (dao) {
				dao.fire();
				retvalue = true;
			}
		} catch (Exception e) {
			retvalue = false;
			e.printStackTrace();
			vo.setStrMsgString("billing.GlobalGroupMstDAO.updateQuery"
					+ e.getMessage());
			vo.setStrMsgType("1");
			

		} finally {
			dao.free();
			dao = null;
		}

		return retvalue;

	}
	/**
	 * initial add query
	 * @param vo
	 * @return boolean
	 */

	public static boolean initialAddQuery(GlobalGroupMstVO vo) throws Exception {
		HisDAO dao = new HisDAO("Billing", "GlobalGroupMstDAO");
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		String query = billing.qryHandler_billing.getQuery(1, "select.group.3");
		
		try {
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, vo.getStrgroupName());
			dao.setQryValue(qryIndex, 2, BillConfigUtil.SUPER_HOSPITAL_CODE);

			wb = dao.executeQry(qryIndex);
			while (wb.next()) {
				count = Integer.parseInt(wb.getString(1));
				
			}
			if (count == 0) {
				returnValue = true;
			} else {
				returnValue = false;
			}

		} catch (Exception e) {
			returnValue = false;
			e.printStackTrace();
			vo.setStrMsgString("billing.GlobalGroupMstDAO.initialAddQuery"
					+ e.getMessage());
			vo.setStrMsgType("1");
			

		}

		finally {

			dao.free();
			dao = null;
		}
		
		return returnValue;
	}
	/**
	 * initial update query
	 * @param vo
	 * @param chk
	 * @return boolean
	 */

	public static boolean initialUpdateQuery(String chk, GlobalGroupMstVO vo) throws Exception 
	{
		HisDAO dao = new HisDAO("Billing", "GlobalGroupMstDAO");
		String strtemp[] = null;
		String strtemp2[] = null;
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		
		String query = billing.qryHandler_billing.getQuery(1, "select.group.4");		 
		 
		try 
		{
			strtemp = chk.replace('@', '#').split("#");
			strtemp2 = strtemp[1].replace("|", "#").split("#");
			strtemp[1] = strtemp2[0];
			
			qryIndex = dao.setQuery(query);
			
			dao.setQryValue(qryIndex, 1, vo.getStrgroupName());
			dao.setQryValue(qryIndex, 2, strtemp[0]);
			dao.setQryValue(qryIndex, 3, strtemp[1]);
			
			wb = dao.executeQry(qryIndex);
			while (wb.next()) 
			{
				count = Integer.parseInt(wb.getString(1));
			}
			if (count == 0) 
			{
				returnValue = true;
			} 
			else 
			{
				returnValue = false;
			}           
		} 
		catch (Exception e) 
		{
			returnValue = false;
			vo.setStrMsgString("billing.GlobalGroupMstDAO.initialUpdateQuery"+ e.getMessage());
			vo.setStrMsgType("1");			
		}
		finally 
		{
			dao.free();
			dao = null;
		}
		return returnValue;
	}
}