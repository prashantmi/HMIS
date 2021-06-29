package bmed.masters.bo;

import bmed.masters.dao.ServiceEngineerMstDAO;
import bmed.masters.vo.ServiceEngineerMstVO;

/**
 * @author   Amit kr
 * Creation Date:- 17-jan-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class ServiceEngineerMstBO 
{
	/**
	 * Gets the component name.
	 * 
	 * @param vo the vo
	 * 
	 * @return the component name
	 */
	public void getAddPageComponent(ServiceEngineerMstVO vo) 
	{
		ServiceEngineerMstDAO.getAddPageComponent(vo);
		ServiceEngineerMstDAO.getServiceEnggNameCmb(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("ServiceEngineerMstBO.getAddPageComponent()---->"
					+ vo.getStrMsgString());
	}
	
	/**
	 * to insert the data and check duplicate before insert record.
	 * 
	 * @param vo the vo
	 */
	public void insertRecord(ServiceEngineerMstVO vo) 
	{
		    ServiceEngineerMstDAO.insertData(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("ServiceEngineerMstBO.insertRecord()--insertData()-->"+ vo.getStrMsgString());
			}
		


	}
	
	/**
	 * Modify.
	 * 
	 * @param vo the vo
	 */
	public void modify(ServiceEngineerMstVO vo)
	{
		ServiceEngineerMstDAO.getAddPageComponent(vo);
		ServiceEngineerMstDAO.getServiceEnggNameCmb(vo);
		ServiceEngineerMstDAO.getServiceEnggNameHlp(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("ServiceEngineerMstBO.modify()---->" + vo.getStrMsgString());
	}
	
	
	/**
	 * Update.
	 * 
	 * @param vo the vo
	 */
	public void updateRecord(ServiceEngineerMstVO vo) 
	{
			ServiceEngineerMstDAO.updateRecord(vo);
			if (vo.getStrMsgType().equals("1"))
				vo.setStrMsgString("ServiceEngineerMstBO.updateRecord()---->"
						+ vo.getStrMsgString());
		
	}

}
