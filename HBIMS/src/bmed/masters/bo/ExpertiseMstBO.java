package bmed.masters.bo;

import bmed.masters.dao.ExpertiseMstDAO;
import bmed.masters.vo.ExpertiseMstVO;

/**
 * @author Arun VR
 * Creation Date:- 20-jan-2011 
 * Modifying Date:- 
 * Used For:-Expertise Master
 * Team Lead By:- Amit Kumar
 *  Module:- BMED(HIS Project)
 * 
 */

public class ExpertiseMstBO {



	public void initAdd(ExpertiseMstVO vo) 
	{



		if (vo.getStrMsgType().equals("1"))
		{

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("ExpertiseMstBO.initAdd() --> " + strErr);
		}
	}

	/**
	 * to insert the data and check duplicate before insert record.
	 * 
	 * @param vo the vo
	 */
	public void insertRecord(ExpertiseMstVO vo) 
	{
		ExpertiseMstDAO.chkDuplicate(vo);

		if (vo.getBExistStatus() == false)
		{

			ExpertiseMstDAO.insertQuery(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ExpertiseMstBO.insert---->"+ vo.getStrMsgString());
			}
		}

		if (vo.getStrMsgType().equals("1")) 
		{
			vo.setStrMsgString("ExpertiseMstBO.insertRecord() --> "
					+ vo.getStrMsgString());
		}

	}
	/**
	 * Modify.
	 * 
	 * @param vo the vo
	 */
	public void modify(ExpertiseMstVO vo)
	{
		ExpertiseMstDAO.modify(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("ExpertiseMstBO.modify()---->" + vo.getStrMsgString());
	}


	/**
	 * Update.
	 * 
	 * @param vo the vo
	 */
	public void updateRecord(ExpertiseMstVO vo) 
	{
		
		ExpertiseMstDAO.chkDuplicateUpdate(vo);
		if (vo.getBExistStatus() == false) {
		ExpertiseMstDAO.updateRecord(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("ExpertiseMstBO.updateRecord()---->"
					+ vo.getStrMsgString());
		}
	}

}
