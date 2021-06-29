package bmed.masters.bo;

import bmed.masters.dao.EngineeringItemSubTypeMstDAO;
import bmed.masters.vo.EngineeringItemSubMstVO;

/**
 * @author Creation Date:- 11-jan-2011 Modifying Date:- Used For:- Team Lead
 *         By:- Module:- BMED(HIS Project)
 * 
 */
public class EngineeringItemSubTypeMstBO {

	/**
	 * for getting option value of engineering item type combo on add page.
	 * 
	 * @param vo
	 *            the vo
	 */

	public void initAdd(EngineeringItemSubMstVO vo) {

		EngineeringItemSubTypeMstDAO.getEnggItemTypeCmb(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("EngineeringItemSubMstBO.initAdd() --> "
					+ strErr);
		}
	}

	/**
	 * to insert the data and check duplicate before insert record.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void insertRecord(EngineeringItemSubMstVO vo) {
		EngineeringItemSubTypeMstDAO.chkDuplicate(vo);

		if (vo.getBExistStatus() == false) {

			EngineeringItemSubTypeMstDAO.insertQuery(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("EngineeringItemSubTypeMstBO.insert---->"
						+ vo.getStrMsgString());
			}
		}

		if (vo.getStrMsgType().equals("1")) {
			vo
					.setStrMsgString("EngineeringItemSubTypeMstBO.insertRecord() --> "
							+ vo.getStrMsgString());
		}

	}

	/**
	 * Modify.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void modify(EngineeringItemSubMstVO vo) {
		EngineeringItemSubTypeMstDAO.modify(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("EngineeringItemSubTypeMstBO.modify()---->"
					+ vo.getStrMsgString());
	}

	/**
	 * Update.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void updateRecord(EngineeringItemSubMstVO vo) {

		EngineeringItemSubTypeMstDAO.chkDuplicateUpdate(vo);

		if (vo.getBExistStatus() == false) {
			EngineeringItemSubTypeMstDAO.updateRecord(vo);

			if (vo.getStrMsgType().equals("1"))
				vo.setStrMsgString("EngineeringItemSubTypeMstBO.updateRecord()---->"
								+ vo.getStrMsgString());

		}
	}

}
