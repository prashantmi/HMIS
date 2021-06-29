package bmed.masters.bo;

import bmed.masters.dao.NonItemMstDAO;
import bmed.masters.vo.NonItemMstVO;;

/**
 * @author	Vivek Aggarwal   
 * Creation Date:- 11-April-2011 
 * Modifying Date:- 12-April-2011
 *  Module:- BMED(HIS Project)
 * 
 */
public class NonItemMstBO
{
	
	/**
	 * to get Engineering Item Type Combo Value.
	 * 
	 * @param vo_p the NonItemMstVO
	 * 
	 */
	public void initializeAdd(NonItemMstVO vo_p) {

		NonItemMstDAO.getEnggItemTypeCmbValues(vo_p);
		NonItemMstDAO.getEngineeringItemSubTypeCmbValues(vo_p);

			if (vo_p.getStrMsgType().equals("1")) 
			{
				vo_p.setStrMsgString("NonItemMstBO.getEnggItemTypeCmbValues(vo_p) --> "
						+ vo_p.getStrMsgString());
			}
	}
	
	/**
	 * to get Engineering Item Sub Type Combo Value.
	 * 
	 * @param vo_p the NonItemMstVO
	 * 
	 */
	public void getStrEngineeringItemSubType(NonItemMstVO vo_p) {

		NonItemMstDAO.getEngineeringItemSubTypeCmbValues(vo_p);
			if (vo_p.getStrMsgType().equals("1")) 
			{
				vo_p.setStrMsgString("NonItemMstBO.getEngineeringItemSubTypeCmbValues(vo_p) --> "
						+ vo_p.getStrMsgString());
			}
	}
	
	/**
	 * to insert data.
	 * 
	 * @param vo_p the NonItemMstVO
	 * 
 	 */
	public void insertRecord(NonItemMstVO vo_p) {
		
		NonItemMstDAO.chkDuplicate(vo_p,"insert");
		
		if (vo_p.getBExistStatus() == true)	//no duplicacy , so new record is added
		{

			NonItemMstDAO.insertValues(vo_p);

			if (vo_p.getStrMsgType().equals("1")) 
			{
				vo_p.setStrMsgString("NonItemMstBO.insertValues(vo_p) ---->"+ vo_p.getStrMsgString());
			}
		}
		
			if (vo_p.getStrMsgType().equals("1")) 
			{
				vo_p.setStrMsgString("NonItemMstBO.insertRecord() --> "+ vo_p.getStrMsgString());
			}
	}
	
	/**
	 * to get data for modify page.
	 * 
	 * @param vo_p the NonItemMstVO
	 * 
	 */
	public void modifyRecord(NonItemMstVO vo_p)
	{
		NonItemMstDAO.modifyRecord(vo_p);
		if (vo_p.getStrMsgType().equals("1")) {
			vo_p.setStrMsgString("NonItemMstBO.modifyRecord(vo_p) --> "
					+ vo_p.getStrMsgString());
		}
	}
	
	/**
	 * to update the record.
	 * 
	 * @param vo_p the NonItemMstVO
	 */
	public void updateRecord(NonItemMstVO vo_p) 
	{
		NonItemMstDAO.chkDuplicate(vo_p,"update");
		
		if (vo_p.getBExistStatus() == true)	//no duplicacy , so new record is added
		{
			NonItemMstDAO.updateQuery(vo_p);
		}	
			
			if (vo_p.getStrMsgType().equals("1"))
			{
				vo_p.setStrMsgString("NonItemMstBO.updateRecord() --> "
				+ vo_p.getStrMsgString());
			}		
	}

}
