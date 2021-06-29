package billing.masters.dao;
/* Global Tariff Master DAO
 * author: Debashis Sardar
 * Created on : 14-Sep-2011
 */
import hisglobal.transactionmgnt.HisDAO;
import javax.sql.rowset.WebRowSet;
import billing.BillConfigUtil;
import billing.qryHandler_billing;
import billing.masters.vo.GlobalTariffMstVO;

public class GlobalTariffMstDAO {

	/**
	 * retrieves insert Query and executes it.
	 * @param vo
	 * @return true - if Record Inserted Successfully. <br>
	 *         false - if Record Not Inserted Successfully.
	 */

	public static boolean insertData(GlobalTariffMstVO vo) throws Exception {
		boolean retValue = false;
		HisDAO hisdao = new HisDAO("billing", "DAOTariffMst");
		int index, index1;
		try 
		{			
			String query = billing.qryHandler_billing.getQuery(1, "select.tariffMst.9");
			String query1 = qryHandler_billing.getQuery(1, "insert.global.tariffMst.0");
			
			
			index = hisdao.setQuery(query);
			hisdao.setQryValue(index, 1, vo.getStrgrpId());
			hisdao.setQryValue(index, 2, BillConfigUtil.SUPER_HOSPITAL_CODE);
				
			
			WebRowSet wb = hisdao.executeQry(index); // executes query to
			// retrieve primary key.
			if (wb.next()) 
			{
				vo.setStrtariffId(wb.getString(1));
			}
			try 
			{
				
				index1 = hisdao.setQuery(query1);
				
				hisdao.setQryValue(index1, 1, vo.getStrtariffId());
				hisdao.setQryValue(index1, 2, BillConfigUtil.SUPER_HOSPITAL_CODE);
				hisdao.setQryValue(index1, 3, BillConfigUtil.SUPER_HOSPITAL_CODE);
				hisdao.setQryValue(index1, 4, vo.getStrgrpId());
				hisdao.setQryValue(index1, 5, vo.getStrtariffId());
				hisdao.setQryValue(index1, 6, vo.getStrtrfPkgName());
				hisdao.setQryValue(index1, 7,vo.getStrseatId());
				hisdao.setQryValue(index1, 8,vo.getStrsubgroupName());
				
				hisdao.execute(index1, 0);
				
			} catch (Exception e) {
			 
			}
			
			
			synchronized (hisdao) 
			{
    			hisdao.fire();
				retValue = true;
			}
		} catch (Exception e) {
			retValue = false;
			vo.setStrMsgString("GlobalTariffMstDAO.insertData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
			
			
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return retValue;
	}
	/*
	 *  to execute initial add query
	 * @param vo
	 * @return WebRowSet
	 */
	
	public static WebRowSet initAddQuery(GlobalTariffMstVO vo) throws Exception {

		HisDAO dao = new HisDAO("billing", "GlobalTariffMstDAO");
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
			
			vo.setStrMsgString("GlobalTariffMstDAO.initAddQuery() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

			
		} finally {
			dao.free();
			dao = null;
		}

		return wb;
	}
	/*
	 * to check uniqueness of tariff name corresponding to group name in add
	 * page.
	 * @param vo
	 * @return boolean
	 */
	public static boolean chkAddUniqueData(GlobalTariffMstVO vo) throws Exception {
		boolean chkValue = false;
		HisDAO hisdao = new HisDAO("Billing", "DAOTariffMst.chkAddUniqueData");
		int index,index1;
		int count = 0,count1=0;
		WebRowSet wb = null;
		WebRowSet wb1 = null;
		String query = billing.qryHandler_billing.getQuery(1,"select.tariffMst.11");
		
		//String query1 = billing.qryHandler_billing.getQuery(1,"select.tariffMst.111");
		
		try 
		{
					
			index = hisdao.setQuery(query);
			hisdao.setQryValue(index, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			hisdao.setQryValue(index, 2, vo.getStrtrfPkgName()); 
			
			wb = hisdao.executeQry(index);
			
			while (wb.next()) 
			{
				count = Integer.parseInt(wb.getString(1));
			}
			// NO CHECK OF TARIFF CODE IN GLOBAL TARIFF BECOZ TARIFF CODE NOT PRESENT IN GLOBAL TARIFF
			/*index1 = hisdao.setQuery(query1);
			hisdao.setQryValue(index1, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			hisdao.setQryValue(index1, 2, vo.getStrtariffCode()); 
			
			wb1 = hisdao.executeQry(index1);
			
			
			while (wb1.next()) 
			{
				count1 = Integer.parseInt(wb1.getString(1));
			}
			
			if (count == 0 && count1 == 0)*/
			if (count == 0)
			{
				chkValue = true;
			} 
			else 
			{
				if(count!=0)
				{
					vo.setStrflag("1");
				}
				else
				{
					vo.setStrflag("2");
				}	
				chkValue = false;
			}
		} catch (Exception e) 
		{
	 
			chkValue = false;
			
			
			vo.setStrMsgString("GlobalTariffMstDAO.chkAddUniqueData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			hisdao.free();
			hisdao = null;
		}
	
		return chkValue;
	}

	/*
	 * to retrieve data from the database for modify page.
	 * @param vo
	 * @param chk
	 * @return boolean
	 */
	public static boolean getData(GlobalTariffMstVO vo, String chk) throws Exception {
		boolean retValue = false;
		HisDAO hisdao = new HisDAO("billing", "DAOGlobalTariffMst");
		int index1,index2;
		
		try {
			
		
			String query1 = qryHandler_billing.getQuery(1,
					"select.tariffMst.12");
			String query2 = qryHandler_billing.getQuery(1,
			"select.tariffMst.12.0");
			
			String[] temp = chk.replace('$', '#').split("#");
			String[] temp1 = temp[0].replace('@', '#').split("#"); 
			String[] temp2 = temp1[1].replace('|', '#').split("#");
			
			index1 = hisdao.setQuery(query1);
			hisdao.setQryValue(index1, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			hisdao.setQryValue(index1, 2, temp2[0]);
			
			WebRowSet wb = hisdao.executeQry(index1);
			
			
			
			while(wb.next()){
					
				vo.setStrgrpId(wb.getString(1));
				vo.setStrGroupId(wb.getString(1)); // used at the time of update
				vo.setStrtrfPkgName(wb.getString(2));
				vo.setStrtariffId(wb.getString(3));
				vo.setStrseatId(wb.getString(4));
				vo.setStrisValid(wb.getString(5));
				vo.setStrsubgroupName(wb.getString(6));
				
			}
			
			index2 = hisdao.setQuery(query2);
			hisdao.setQryValue(index2, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			hisdao.setQryValue(index2, 2, BillConfigUtil.SUPER_HOSPITAL_CODE);
			hisdao.setQryValue(index2, 3, temp2[0]);
			
			WebRowSet wb1 = hisdao.executeQry(index2);
			
			
			
			while(wb1.next()){
					
				vo.setStrgroupName(wb1.getString(1));
				
			}
			wb = null;
			wb1=null;
			retValue = true;
			
			}	
		catch (Exception e) {
			retValue = false;
			vo.setStrMsgString("GlobalTariffMstDAO.getData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return retValue;
	
	}
	
	/*
	 * to update data in modify page.
	 * @param vo
	 * @return boolean
	 */
	public static boolean updateData(GlobalTariffMstVO vo) throws Exception {
		boolean retVal = false;
		HisDAO hisdao = new HisDAO("billing", "GlobalTariffMstDAO");
		int index,index1;
		String temp[] = null;
		String temp2[] = null;
		String chk = "";
		String chkTrfId = "";
		
	try {
		
		chk = vo.getStrChK();
		temp = chk.replace("@","#").split("#");
		vo.setStrHospitalCode(temp[0]);
		temp2 = temp[1].replace("$","#").split("#");
		String[] temp3 =temp2[0].replace('|', '#').split("#");
		chkTrfId = temp3[0];
		
		String query  = qryHandler_billing.getQuery(1, "select.tariffMst.9");
		String query1 = qryHandler_billing.getQuery(1, "update.tariffMst.0");
		
		
		
		
		index = hisdao.setQuery(query);
		hisdao.setQryValue(index, 1, vo.getStrgrpId());
		hisdao.setQryValue(index, 2, BillConfigUtil.SUPER_HOSPITAL_CODE);
		
		
		WebRowSet wb = hisdao.executeQry(index);
		
			if (wb.next()) 
			{
				vo.setStrtariffId(wb.getString(1));
			}
		try{
			
			
			index1 = hisdao.setQuery(query1);
			
			hisdao.setQryValue(index1, 1, vo.getStrtrfPkgName());
			hisdao.setQryValue(index1, 2, vo.getStrsubgroupName());
			hisdao.setQryValue(index1, 3, vo.getStrisValid());
			hisdao.setQryValue(index1, 4, BillConfigUtil.SUPER_HOSPITAL_CODE);
			hisdao.setQryValue(index1, 5, chkTrfId);
			
			
			hisdao.execute(index1, 0);
			
		}
			catch (Exception e) {
			 
			}
			
		synchronized(hisdao){
			hisdao.fire();
			retVal = true;
			
	}}
	
	 catch (Exception e) {
			retVal = false;
		 
			vo.setStrMsgString("GlobalTariffMstDAO.updateData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return retVal;
	}
	
	/*
	 * to check uniqueness of tariff name corresponding to group name in add page.
	 * @param vo
	 * @return boolean
	 */
	public static boolean chkModifyUniqueData(GlobalTariffMstVO vo) throws Exception {
		
		boolean chkValue = false;
		HisDAO hisdao = new HisDAO("Billing",
				"DAOTariffMst.chkModifyUniqueData");
		int index ;
		int count = 0;
		int index1 ;
		int count1 = 0;
		
		
		
		WebRowSet wb = null;
		String[] temp=null;
		String chk="";
		String query = billing.qryHandler_billing.getQuery(1,
				"select.tariffMst.15");
		

		String query1 = billing.qryHandler_billing.getQuery(1,
		"select.tariffMst.115");
		
		try {
			
			
			 index = hisdao.setQuery(query);
			 chk = vo.getStrChK();
		     temp = chk.replace("@", "#").split("#");
		     vo.setStrHospitalCode(temp[0]);
		     String[] temp1=temp[1].replace("$", "#").split("#");
		     String[] temp2=temp1[0].replace("|", "#").split("#");
		     vo.setStrtariffId(temp2[0]);
		 
			hisdao.setQryValue(index, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			hisdao.setQryValue(index, 2, vo.getStrtrfPkgName());
			hisdao.setQryValue(index, 3, vo.getStrtariffId());
			
			
			wb = hisdao.executeQry(index);
			while (wb.next()) {
				count = Integer.parseInt(wb.getString(1));
			}
			
			
			 index1 = hisdao.setQuery(query1);
			
				hisdao.setQryValue(index1, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
				hisdao.setQryValue(index1, 2, vo.getStrtariffCode());
				hisdao.setQryValue(index1, 3, vo.getStrtariffId());
			 

				wb = hisdao.executeQry(index1);
				while (wb.next()) {
					count1 = Integer.parseInt(wb.getString(1));
				}
				
			
			if (count < 1 && count1 < 1) {
				chkValue = true;
			} else {
				chkValue = false;
			}
		}catch (Exception e) {
			chkValue = false;
	 
			vo.setStrMsgString("GlobalTariffMstDAO.chkModifyUniqueData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			hisdao.free();
			hisdao = null;
		}
		return chkValue;
}

	
	
}