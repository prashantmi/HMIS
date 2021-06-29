package bmed.masters.bo;

import bmed.masters.dao.EquipmentParameterMstDAO;
import bmed.masters.dao.EquipmentTestMstDAO;
import bmed.masters.vo.EquipmentParameterMstVO;
import bmed.masters.vo.EquipmentTestMstVO;

;

/**
 * @author Arun VR Creation Date:- 06-Aug-2012 Modifying Date:- Used For:- Team
 *         Lead By:- Module:- BMED(HIS Project)
 * 
 */
public class EquipmentParameterMstBO {

	/**
	 * to insert data.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 */
	public void insertRecord(EquipmentParameterMstVO vo) {

		EquipmentParameterMstDAO.chkDuplicate(vo, "insert");

		if (vo.getBExistStatus() == true) // no duplicacy , so new record is
											// added
		{

			EquipmentParameterMstDAO.insertValues(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("EquipmentTestMstBO.insertValues(vo) ---->"
						+ vo.getStrMsgString());
			}
		}

		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("EquipmentTestMstBO.insertRecord() --> "
					+ vo.getStrMsgString());
		}
	}

	/**
	 * to get data for modify page.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 */
	public void modifyRecord(EquipmentParameterMstVO vo) {
		EquipmentParameterMstDAO.modifyRecord(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("EquipmentTestMstBO.modifyRecord(vo) --> "
					+ vo.getStrMsgString());
		}
	}

	/**
	 * to update the record.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void updateRecord(EquipmentParameterMstVO vo) {
		EquipmentParameterMstDAO.chkDuplicate(vo, "update");

		if (vo.getBExistStatus() == true) // no duplicacy , so new record is
											// added
		{
			EquipmentParameterMstDAO.updateQuery(vo);
		}

		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("EquipmentParameterMstBO.updateRecord() --> "
					+ vo.getStrMsgString());
		}
	}

}
