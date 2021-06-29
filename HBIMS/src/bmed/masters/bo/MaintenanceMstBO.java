package bmed.masters.bo;

import bmed.masters.dao.MaintenanceMstDAO;
import bmed.masters.vo.MaintenanceMstVO;

/**
 * @author   Vivek Aggarwal
 * Creation Date:- 19-Jan-2011 
 * Modifying Date:- 21-Jan-2011
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class MaintenanceMstBO
{
	
	/**
	 * to get Engineering Item Type Combo Value.
	 * 
	 * @param vo the vo	 
	 */
	public void initializeAdd(MaintenanceMstVO vo) throws Exception
	{
		
		MaintenanceMstDAO.getEnggItemTypeCmbValues(vo);
		MaintenanceMstDAO.getEngineeringItemSubTypeCmbValues(vo);
		if (vo.getStrMsgType().equals("1")) 
		{
			vo.setStrMsgString("MaintenanceMstBO.getEnggItemTypeCmbValues() --> "
					+ vo.getStrMsgString());
		}		
	}
	
	/**
	 * to get Engineering Item Sub Type Combo Value.
	 * 
	 * @param vo the vo
	 */
	public void getEnggItemSubTypeCmbValues(MaintenanceMstVO vo)
	{
		MaintenanceMstDAO.getEngineeringItemSubTypeCmbValues(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("MaintenanceMstBO.getStrEngineeringItemType() --> "
					+ vo.getStrMsgString());
		}
		
	}
	
	
	/**
	 * to insert data.
	 * 
	 * @param vo the vo
	 * 
 	 */
	public void insertRecord(MaintenanceMstVO vo)
	{
		
		MaintenanceMstDAO.chkDuplicate(vo,"insert");
		
		if (vo.isBExistStatus() == true)
		{

			MaintenanceMstDAO.insertValues(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("MaintenanceMstBO.insert---->"+ vo.getStrMsgString());
			}
		}
		
		if (vo.getStrMsgType().equals("1")) 
		{
			vo.setStrMsgString("MaintenanceMstBO.insertRecord() --> "
					+ vo.getStrMsgString());
		}
	}
	
	
	/**
	 * to get data for modify page.
	 * 
	 * @param vo the vo
	 * 
	 */
	public void modifyRecord(MaintenanceMstVO vo) {

		MaintenanceMstDAO.modifyRecord(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("MaintenanceMstBO.modifyRecord() --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * to update the record.
	 * 
	 * @param vo the vo
	 */
	public void updateRecord(MaintenanceMstVO vo) 
	{
		MaintenanceMstDAO.chkDuplicate(vo,"update");
		
		if (vo.isBExistStatus() == true)	//no duplicacy , so new record is added
		{
			MaintenanceMstDAO.updateQuery(vo);
		}
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("MaintenanceMstBO.updateRecord() --> "
			+ vo.getStrMsgString());
		}

	}
	
}