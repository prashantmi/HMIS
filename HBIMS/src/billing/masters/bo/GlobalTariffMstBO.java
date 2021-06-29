package billing.masters.bo;
/* Global Tariff Master BO
 * author: Debashis Sardar
 * Created on : 14-Sep-2011
 */
import javax.sql.rowset.WebRowSet;
import billing.masters.dao.GlobalTariffMstDAO;
import billing.masters.vo.GlobalTariffMstVO;


public class GlobalTariffMstBO {


	/**
	 * retrieves insert Query and executes it.
	 * @param vo
	 * @return true - if Record Inserted Successfully. <br>
	 *         false - if Record Not Inserted Successfully.
	 */
	public static boolean insertData(GlobalTariffMstVO vo) throws Exception {
		boolean retValue = false;
		GlobalTariffMstDAO dao=new GlobalTariffMstDAO();
					
			
			retValue=dao.insertData(vo);
		 
			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("globalTariffMst.insertData() --> " + vo.getStrMsgString());
				
			}
			
		return retValue;
	}
	/*
	 *  to execute initial add query
	 * @param vo
	 * @return WebRowSet
	 */
	public static WebRowSet initAddQuery(GlobalTariffMstVO vo) throws Exception {

		
		
		WebRowSet wb = null;
		
		wb=GlobalTariffMstDAO.initAddQuery(vo);
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("globalTariffMst.initAddQuery() --> " + vo.getStrMsgString());
			
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
		
				
			chkValue =GlobalTariffMstDAO.chkAddUniqueData(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("globalTariffMst.chkAddUniqueData() --> " + vo.getStrMsgString());
				
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
		
		
			retValue = GlobalTariffMstDAO.getData(vo,chk);
			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("globalTariffMst.getData() --> " + vo.getStrMsgString());
				
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
		
		
	
		retVal = GlobalTariffMstDAO.updateData(vo);
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("globalTariffMst.updateData() --> " + vo.getStrMsgString());
			
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
		
		
			
			chkValue =GlobalTariffMstDAO.chkModifyUniqueData(vo);
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("globalTariffMst.chkModifyUniqueData() --> " + vo.getStrMsgString());
				
			}
		return chkValue;
}
	

}
