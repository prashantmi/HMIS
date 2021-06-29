package bmed.masters.bo;

import bmed.masters.dao.EquipmentTestMstDAO;
import bmed.masters.vo.EquipmentTestMstVO;;

/**
 * @author	Vivek Aggarwal   
 * Creation Date:- 27-Jul-2012
 * Modifying Date:- 27-Jul-2012
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class EquipmentTestMstBO {
	
	/**
	 * to get Engineering Item Type Combo Value.
	 * 
	 * @param vo the vo
	 * 
	 */
	public void initializeAdd(EquipmentTestMstVO vo) {

		EquipmentTestMstDAO.getEnggItemTypeCmbValues(vo);
		EquipmentTestMstDAO.getEngineeringItemSubTypeCmbValues(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("EquipmentTestMstBO.getEnggItemTypeCmbValues(vo) --> "
						+ vo.getStrMsgString());
			}
	}
	
	/**
	 * to get Engineering Item Sub Type Combo Value.
	 * 
	 * @param vo the vo
	 * 
	 */
	public void getStrEngineeringItemSubType(EquipmentTestMstVO vo) {

		EquipmentTestMstDAO.getEngineeringItemSubTypeCmbValues(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("EquipmentTestMstBO.getEngineeringItemSubTypeCmbValues(vo) --> "
						+ vo.getStrMsgString());
			}
	}
	
	/**
	 * to insert data.
	 * 
	 * @param vo the vo
	 * 
 	 */
	public void insertRecord(EquipmentTestMstVO vo) {
		
		EquipmentTestMstDAO.chkDuplicate(vo,"insert");
		
		if (vo.getBExistStatus() == true)	//no duplicacy , so new record is added
		{

			EquipmentTestMstDAO.insertValues(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("EquipmentTestMstBO.insertValues(vo) ---->"+ vo.getStrMsgString());
			}
		}
		
			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("EquipmentTestMstBO.insertRecord() --> "+ vo.getStrMsgString());
			}
	}
	
	/**
	 * to get data for modify page.
	 * 
	 * @param vo the vo
	 * 
	 */
	public void modifyRecord(EquipmentTestMstVO vo)
	{
		EquipmentTestMstDAO.modifyRecord(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("EquipmentTestMstBO.modifyRecord(vo) --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * to update the record.
	 * 
	 * @param vo the vo
	 */
	public void updateRecord(EquipmentTestMstVO vo) 
	{
		EquipmentTestMstDAO.chkDuplicate(vo,"update");
		
		if (vo.getBExistStatus() == true)	//no duplicacy , so new record is added
		{
			EquipmentTestMstDAO.updateQuery(vo);
		}	
			
			if (vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("EquipmentTestMstBO.updateRecord() --> "
				+ vo.getStrMsgString());
			}		
	}

}
