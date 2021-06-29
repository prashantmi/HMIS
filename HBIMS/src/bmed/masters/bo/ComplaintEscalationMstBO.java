package bmed.masters.bo;

import bmed.masters.dao.ComplaintEscalationMstDAO;
import bmed.masters.vo.ComplaintEscalationMstVO;

/**
 * @author   Amit kr
 * Creation Date:- 17-jan-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */

public class ComplaintEscalationMstBO 
{
	/**
	 * Gets the Add page component.
	 * 
	 * @param vo the vo
	 * 
	 * @return the component name
	 */
	public void getAddPageComponent(ComplaintEscalationMstVO vo) 
	{		
		ComplaintEscalationMstDAO.getUnitCmb(vo);
		ComplaintEscalationMstDAO.getLevelTypeCmb(vo);
		ComplaintEscalationMstDAO.getAddPageComponent(vo);
		ComplaintEscalationMstDAO.getServiceEmpNameCmb(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("ComplaintEscalationMstBO.getAddPageComponent()---->"
					+ vo.getStrMsgString());
	}

	/**
	 * Gets the Employee Information.
	 * 
	 * @param vo the vo
	 * 
	 * @return the component name
	 */
	public void getEmpInfo(ComplaintEscalationMstVO vo) 
	{		
		ComplaintEscalationMstDAO.getEmpInfo(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("ComplaintEscalationMstBO.getEmpInfo()---->"
					+ vo.getStrMsgString());
	}
	
	/**
	 * to insert the data and check duplicate before insert record.
	 * 
	 * @param vo the vo
	 */
	public void insertRecord(ComplaintEscalationMstVO vo) 
	{
		ComplaintEscalationMstDAO.chkDuplicate(vo);
		
		if (vo.getBExistStatus() == true)
		{

			ComplaintEscalationMstDAO.insertData(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ComplaintEscalationMstBO.insertRecord()---->"+ vo.getStrMsgString());
			}
		}
		
		if (vo.getStrMsgType().equals("1")) 
		{
			vo.setStrMsgString("ComplaintEscalationMstBO.insertRecord() --> "
					+ vo.getStrMsgString());
		}

	}
	/**
	 * Gets the component value for Modify page.
	 * 
	 * @param vo the vo
	 * 
	 * @return the component name
	 */
	public void modify(ComplaintEscalationMstVO vo) 
	{		
		ComplaintEscalationMstDAO.getUnitCmb(vo);
		ComplaintEscalationMstDAO.getAddPageComponent(vo);
		ComplaintEscalationMstDAO.getModifyPageData(vo);
		ComplaintEscalationMstDAO.getServiceEmpNameCmb(vo);
		ComplaintEscalationMstDAO.getEmpInfo(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("ComplaintEscalationMstBO.getAddPageComponent()---->"
					+ vo.getStrMsgString());
	}

	
	
	/**
	 * method is used to Update database .
	 * 
	 * @param vo the vo
	 */
	public void updateRecord(ComplaintEscalationMstVO vo) 
	{
			ComplaintEscalationMstDAO.updateRecord(vo);
			if (vo.getStrMsgType().equals("1"))
				vo.setStrMsgString("ComplaintEscalationMstBO.updateRecord()---->"
						+ vo.getStrMsgString());
		
	}
	
	
}
