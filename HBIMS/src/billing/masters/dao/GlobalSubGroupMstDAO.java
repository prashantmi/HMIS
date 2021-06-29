package billing.masters.dao;
/* Global Sub Group Master DAO
 * author: Debashis Sardar
 * Created on : 26-Aug-2011
 */
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.GetNetworkAddress;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.qryHandler_billing;
import billing.masters.vo.GlobalSubGroupMstVO;
import billing.masters.vo.GlobalTariffMstVO;

public class GlobalSubGroupMstDAO {
	/**
	 * retrieves insert Query and executes it.
	 * @param vo
	 * @return true - if Record Inserted Successfully. <br>
	 *         false - if Record Not Inserted Successfully.
	 */

	public static boolean insertQuery(GlobalSubGroupMstVO vo) throws Exception {
		
		HisDAO dao = null;
		int qryIndex;
		boolean retvalue = true;
		String query = new String();
		try {
			dao = new HisDAO("Billing", "GlobalSubGroupMstDAO");
			query = billing.qryHandler_billing.getQuery(1, "insert.subgroup.0");
			
			qryIndex = dao.setQuery(query);
			
				
			dao.setQryValue(qryIndex, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			dao.setQryValue(qryIndex, 2,  BillConfigUtil.SUPER_HOSPITAL_CODE);
			dao.setQryValue(qryIndex, 3, vo.getStrsubgroupName());
			dao.setQryValue(qryIndex, 4, vo.getStrseatId());
			dao.setQryValue(qryIndex, 5, vo.getStrsubgroupCode());
			dao.setQryValue(qryIndex, 6, vo.getStrgrpId());
			dao.setQryValue(qryIndex, 7, GetNetworkAddress.GetAddress("ip"));
			dao.setQryValue(qryIndex, 8, GetNetworkAddress.GetAddress("mac"));
			
			dao.execute(qryIndex, 0);

			synchronized (dao) {
				dao.fire();
				retvalue = true;

			}

		} catch (Exception e) {
			retvalue = false;
			e.printStackTrace();
			vo.setStrMsgString("billing.GlobalSubGroupMstDAO.insertQuery()"+ e.getMessage());
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
	public static void modifyQuery(String chk1, GlobalSubGroupMstVO vo) throws Exception {

		
		HisDAO dao = null;
		String strtemp[] = null;
		String strtemp2[] = null;
		
		int qryIndex,index2;
		String query = new String();
        strtemp = chk1.replace('@', '#').split("#");
		strtemp2 = strtemp[1].replace("|", "#").split("#");
		strtemp[1] = strtemp2[0];

		
		try {
			dao = new HisDAO("Billing", "GlobalSubGroupMstDAO");
			query = billing.qryHandler_billing.getQuery(1, "select.subgroup.1");
			String query2 = qryHandler_billing.getQuery(1,
					"select.subgroupMst.12.0");

			
			qryIndex = dao.setQuery(query);
			
			dao.setQryValue(qryIndex, 1, strtemp[0]);
			dao.setQryValue(qryIndex, 2, strtemp[1]);
			
			WebRowSet web = dao.executeQry(qryIndex);
			while (web.next()) {

				vo.setStrsubgroupName(web.getString(1));
				vo.setStrisPackage(web.getString(2));
				vo.setStrEffectiveFrom(web.getString(3));
				vo.setStrisValid(web.getString(4));
				vo.setStrremark(web.getString(5));
				vo.setStrisVisible(web.getString(6));
				vo.setStrseatId(web.getString(7));
				vo.setStrsubgroupCode(web.getString(8));
				vo.setStrgrpId(web.getString(9));
				vo.setStrGroupId(web.getString(9));
				

			}
			index2 = dao.setQuery(query2);
			dao.setQryValue(index2, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			dao.setQryValue(index2, 2, BillConfigUtil.SUPER_HOSPITAL_CODE);
			dao.setQryValue(index2, 3, strtemp[1]);
			
			WebRowSet wb1 = dao.executeQry(index2);
			
			
			
			while(wb1.next()){
					
				vo.setStrgroupName(wb1.getString(1));
				
			}
			web = null;
			wb1=null;
			
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("billing.GlobalSubGroupMstDAO.modifyQuery"
					+ e.getMessage());
			vo.setStrMsgType("1");
			

		} finally {
			dao.free();
			dao = null;
		}

	}
	public static WebRowSet initAddQuery(GlobalSubGroupMstVO vo) throws Exception {

		HisDAO dao = new HisDAO("billing", "GlobalSubGroupMstDAO");
		int nqryIndex;
		WebRowSet wb = null;
		String strquery = billing.qryHandler_billing.getQuery(1,
				"select.groupName.1");

		try {
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrgrpId());
			dao.setQryValue(nqryIndex, 2, BillConfigUtil.SUPER_HOSPITAL_CODE);
		
			wb = dao.executeQry(nqryIndex);

		} catch (Exception e) {
			
			vo.setStrMsgString("GlobalSubGroupMstDAO.initAddQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

			
		} finally {
			dao.free();
			dao = null;
		}

		return wb;
	}
	/**
	 * retrieves and executes update Query
	 * @param Chk
	 * @param vo
	 * @return true - if Record Updated Successfully. <br>
	 *         false - if Record Not Updated Successfully.
	 */

	public static boolean updateQuery(String chk, GlobalSubGroupMstVO vo)
			throws Exception {
		boolean retvalue = false;
		HisDAO dao = null;
		
		String strtemp[] = null;
		String strtemp2[] = null;
		int qryIndex;
		String query = new String();
		try {

			dao = new HisDAO("Billing", "GlobalSubGroupMstDAO");
			query = billing.qryHandler_billing.getQuery(1, "update.subgroup.1");
			qryIndex = dao.setQuery(query);
			strtemp = chk.replace('@', '#').split("#");
			strtemp2 = strtemp[1].replace("|", "#").split("#");
			strtemp[1] = strtemp2[0];
			
			dao.setQryValue(qryIndex, 1, vo.getStrsubgroupName());
			
			dao.setQryValue(qryIndex, 2, vo.getStrseatId());
			
			dao.setQryValue(qryIndex, 3, vo.getStrisValid());
			dao.setQryValue(qryIndex, 4, vo.getStrsubgroupCode());
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
			vo.setStrMsgString("billing.GlobalSubGroupMstDAO.updateQuery"
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

	public static boolean initialAddQuery(GlobalSubGroupMstVO vo) throws Exception {
		HisDAO dao = new HisDAO("Billing", "GlobalSubGroupMstDAO");
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		String query = billing.qryHandler_billing.getQuery(1, "select.subgroup.3");
		
		try {
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, vo.getStrsubgroupName());
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
			vo.setStrMsgString("billing.GlobalSubGroupMstDAO.initialAddQuery"
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

	public static boolean initialUpdateQuery(String chk, GlobalSubGroupMstVO vo) throws Exception 
	{
		HisDAO dao = new HisDAO("Billing", "GlobalSubGroupMstDAO");
		String strtemp[] = null;
		String strtemp2[] = null;
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		
		String query = billing.qryHandler_billing.getQuery(1, "select.subgroup.4");		 
		 
		try 
		{
			strtemp = chk.replace('@', '#').split("#");
			strtemp2 = strtemp[1].replace("|", "#").split("#");
			strtemp[1] = strtemp2[0];
			
			qryIndex = dao.setQuery(query);
			
			dao.setQryValue(qryIndex, 1, vo.getStrsubgroupName());
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
			vo.setStrMsgString("billing.GlobalSubGroupMstDAO.initialUpdateQuery"+ e.getMessage());
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