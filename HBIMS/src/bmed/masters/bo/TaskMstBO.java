package bmed.masters.bo;

import bmed.masters.dao.TaskMstDAO;
import bmed.masters.vo.TaskMstVO;;

/**
 * @author	Vivek Aggarwal   
 * Creation Date:- 11-Jan-2011 
 * Modifying Date:- 25-Jan-2011
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class TaskMstBO {
	
	/**
	 * to get Engineering Item Type Combo Value.
	 * 
	 * @param vo the vo
	 * 
	 */
	public void initializeAdd(TaskMstVO vo) {

		TaskMstDAO.getEnggItemTypeCmbValues(vo);
		TaskMstDAO.getEngineeringItemSubTypeCmbValues(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("TaskMstBO.getEnggItemTypeCmbValues(vo) --> "
						+ vo.getStrMsgString());
			}
	}
	
	/**
	 * to get Engineering Item Sub Type Combo Value.
	 * 
	 * @param vo the vo
	 * 
	 */
	public void getStrEngineeringItemSubType(TaskMstVO vo) {

		TaskMstDAO.getEngineeringItemSubTypeCmbValues(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("TaskMstBO.getEngineeringItemSubTypeCmbValues(vo) --> "
						+ vo.getStrMsgString());
			}
	}
	
	/**
	 * to insert data.
	 * 
	 * @param vo the vo
	 * 
 	 */
	public void insertRecord(TaskMstVO vo) {
		
		TaskMstDAO.chkDuplicate(vo,"insert");
		
		if (vo.getBExistStatus() == true)	//no duplicacy , so new record is added
		{

			TaskMstDAO.insertValues(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("TaskMstBO.insertValues(vo) ---->"+ vo.getStrMsgString());
			}
		}
		
			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("TaskMstBO.insertRecord() --> "+ vo.getStrMsgString());
			}
	}
	
	/**
	 * to get data for modify page.
	 * 
	 * @param vo the vo
	 * 
	 */
	public void modifyRecord(TaskMstVO vo)
	{
		TaskMstDAO.modifyRecord(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TaskMstBO.modifyRecord(vo) --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * to update the record.
	 * 
	 * @param vo the vo
	 */
	public void updateRecord(TaskMstVO vo) 
	{
		TaskMstDAO.chkDuplicate(vo,"update");
		
		if (vo.getBExistStatus() == true)	//no duplicacy , so new record is added
		{
			TaskMstDAO.updateQuery(vo);
		}	
			
			if (vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("TaskMstBO.updateRecord() --> "
				+ vo.getStrMsgString());
			}		
	}

}
