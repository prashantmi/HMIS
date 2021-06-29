package billing.masters.bo;
/* Unit Value Master BO
 * author: Debashis Sardar
 * Created on : 16-Sep-2011
 */

import billing.masters.dao.UnitValueMstDAO;
import billing.masters.vo.UnitValueMstVO;



public class UnitValueMstBO {
	/**
	 * retrieves insert Query and executes it.
	 * @param vo
	 * @return true - if Record Inserted Successfully. <br>
	 *         false - if Record Not Inserted Successfully.
	 */
	public static boolean insertQuery(UnitValueMstVO vo) throws Exception {

		

		boolean retvalue = true;
		
			retvalue=UnitValueMstDAO.insertQuery(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("UnitValueMst.insertQuery() --> " + vo.getStrMsgString());
				
			}
			
		
		
		return retvalue;
	}

	/**
	 * retrieves and executes Modify Query
	 * 
	 * @param vo
	 * @param Chk1
	 */

	public static void modifyQuery(String chk1, UnitValueMstVO vo)
			throws Exception {

		
		
			UnitValueMstDAO.modifyQuery(chk1,vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("UnitValueMst.modifyQuery() --> " + vo.getStrMsgString());
				
			}
			
		

	}

	/**
	 * retrieves and executes update Query
	 * @param Chk
	 * @param vo
	 * @return true - if Record Updated Successfully. 
	 *         false - if Record Not Updated Successfully.
	 */
	public static boolean updateQuery(String chk, UnitValueMstVO vo)
			throws Exception {
		boolean retvalue = true;
		

		
			retvalue=UnitValueMstDAO.updateQuery(chk,vo);
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("UnitValueMst.updateQuery() --> " + vo.getStrMsgString());
				
			}
			

		
		return retvalue;

	}

	/**
	 * initial update query
	 * @param vo
	 * @param chk
	 * @return boolean
	 */

	public static boolean initialUpdateQuery(String chk, UnitValueMstVO vo)
			throws Exception {
		
		boolean returnValue = false;
		
		
			returnValue=UnitValueMstDAO.initialUpdateQuery(chk,vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("UnitValueMst.initialUpdateQuery() --> " + vo.getStrMsgString());
				
			}
			

		return returnValue;
	}
	/**
	 * initial add query
	 * @param vo
	 * @param chk
	 * @return boolean
	 */
	public static boolean initialAddQuery(UnitValueMstVO vo) throws Exception {
		
		boolean returnValue = false;
		
	
			returnValue=UnitValueMstDAO.initialAddQuery(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("UnitValueMst.initialAddQuery() --> " + vo.getStrMsgString());
				
			}
			
		
		return returnValue;
	}

}