package bmed.masters.bo;

import bmed.masters.dao.HemConfigMstDAO;
import bmed.masters.vo.HemConfigMstVO;

public class HemConfigMstBO
{

	/**
	 * to get data for Hem Config page.
	 * 
	 * @param vo_p the HemConfigMstVO
	 * 
	 */
	public void getRecord(HemConfigMstVO vo_p)
	{
		HemConfigMstDAO.getRecord(vo_p);
		if (vo_p.getStrMsgType().equals("1")) {
			vo_p.setStrMsgString("HemConfigMstBO.getRecord(vo_p) --> " + vo_p.getStrMsgString());
		}
	}
	
	/**
	 * to save the record.
	 * 
	 * @param vo_p the HemConfigMstVO
	 */
	public void saveRecord(HemConfigMstVO vo_p) 
	{

			HemConfigMstDAO.saveRecord(vo_p);
	
			
			if (vo_p.getStrMsgType().equals("1"))
			{
				vo_p.setStrMsgString("HemConfigMstBO.saveRecord() --> "	+ vo_p.getStrMsgString());
			}		
	}
}
