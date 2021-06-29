package bmed.masters.bo;

import bmed.masters.dao.EquipmentTestMstDAO;
import bmed.masters.dao.EquipmentTestParameterMstDAO;
import bmed.masters.vo.EquipmentTestMstVO;
import bmed.masters.vo.EquipmentTestParameterMstVO;

/**
 * @author	Arun  
 * Creation Date:- 7-Aug-2012
 * Modifying Date:- 7-Aug-2012
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class EquipmentTestParameterMstBO {
	
	/**
	 * to get Engineering Item Type Combo Value.
	 * 
	 * @param vo the vo
	 * 
	 */
	public void initializeAdd(EquipmentTestParameterMstVO vo) {

		
		try {
			
			EquipmentTestParameterMstDAO.getTestNameCmbValues(vo);
			EquipmentTestParameterMstDAO.setWrsAvailableParametersOptions(vo);
			

			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("EquipmentTestMstBO.getEnggItemTypeCmbValues(vo) --> "
						+ vo.getStrMsgString());
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
public void initializeModify(EquipmentTestParameterMstVO vo) {

		
		try {
			
			EquipmentTestParameterMstDAO.getTestNameCmbValues(vo);
			//EquipmentTestParameterMstDAO.setWrsAvailableParametersOptions(vo);
			

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/**
	 * to get Engineering Item Sub Type Combo Value.
	 * 
	 * @param vo the vo
	 * 
	 */
	public void getStrParameterCmb(EquipmentTestParameterMstVO vo) {

		try {
				
			
			EquipmentTestParameterMstDAO.setWrsAvailableParametersOptions(vo);
			
			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("EquipmentTestMstBO.getEngineeringItemSubTypeCmbValues(vo) --> "
						+ vo.getStrMsgString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	public void getStrSeletecParameterCmb(EquipmentTestParameterMstVO vo) {

		try {
				
			
			EquipmentTestParameterMstDAO.setWrsSelectedParametersOptions(vo);
			
			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("EquipmentTestMstBO.getEngineeringItemSubTypeCmbValues(vo) --> "
						+ vo.getStrMsgString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	/**
	 * to insert data.
	 * 
	 * @param vo the vo
	 * 
 	 */
	public void insertRecord(EquipmentTestParameterMstVO vo) {
		
	//	EquipmentTestParameterMstDAO.deleteValues(vo);
		
		//if (vo.getBExistStatus() == true)	//no duplicacy , so new record is added
		//{

		    if(vo.getArrStrSelectedTestId().length > 0 || vo.getArrStrSelectedTestId() != null)
		    {
		    	for(int i=0;i<vo.getArrStrSelectedTestId().length;i++){
		    		vo.setStrParameterId(vo.getArrStrSelectedTestId()[i]);
		     	EquipmentTestParameterMstDAO.insertValues(vo);
		     	vo.setBExistStatus(true);
		    	}

		    }
			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("EquipmentTestMstBO.insertValues(vo) ---->"+ vo.getStrMsgString());
			}
		//}
		
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
	public void modifyRecord(EquipmentTestParameterMstVO vo)
	{
		EquipmentTestParameterMstDAO.modifyRecord(vo);
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
	public void updateRecord(EquipmentTestParameterMstVO vo) 
	{
		//EquipmentTestMstDAO.chkDuplicate(vo,"update");
		
		EquipmentTestParameterMstDAO.deleteValues(vo);
		
		//if (vo.getBExistStatus() == true)	//no duplicacy , so new record is added
		//{
		if(vo.getArrStrSelectedTestId().length > 0 || vo.getArrStrSelectedTestId() != null)
	    {
	    	for(int i=0;i<vo.getArrStrSelectedTestId().length;i++){
	    		vo.setStrParameterId(vo.getArrStrSelectedTestId()[i]);
	     	EquipmentTestParameterMstDAO.insertValues(vo);
	     	vo.setBExistStatus(true);
	    	}

	    }
		//}	
			
			if (vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("EquipmentTestMstBO.updateRecord() --> "
				+ vo.getStrMsgString());
			}		
	}

}
