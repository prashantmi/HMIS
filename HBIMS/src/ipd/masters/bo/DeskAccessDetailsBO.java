package ipd.masters.bo;

import ipd.masters.dao.DeskAccessDetailsDAO;
import ipd.masters.vo.DeskAccessDetailsVO;


/**
 * @author Adil Wasi
 *
 */
public class DeskAccessDetailsBO {

	
	/**
	 * for getting option value of userName,DeskName combos and menu list 
	 * 
	 * @param deskAccessDetailsVO_p
	 * 
	 */

	public void getInitialValues(DeskAccessDetailsVO deskAccessDetailsVO_p) {

		DeskAccessDetailsDAO.getInitialValues(deskAccessDetailsVO_p);

		if (deskAccessDetailsVO_p.getStrMsgType().equals("1")) {
			deskAccessDetailsVO_p.setStrMsgString("DeskAccessDetailsBO.getInitialValues() --> "
					+ deskAccessDetailsVO_p.getStrMsgString());
		}
	}
	
	/**
	 * To get values of LEFT MENU LIST
	 * 
	 * @param deskAccessDetailsVO_p
	 * @throws Exception
	 */
	public void getLeftList(DeskAccessDetailsVO deskAccessDetailsVO_p) {

		DeskAccessDetailsDAO.getLeftListQuery(deskAccessDetailsVO_p);
		if (deskAccessDetailsVO_p.getStrMsgType().equals("1")) {
			deskAccessDetailsVO_p.setStrMsgString("DeskAccessDetailsBO.getLeftList() --> "
					+ deskAccessDetailsVO_p.getStrMsgString());
		}

	}
	/**
	 * To get values of RIGHT MENU LIST
	 * 
	 * @param deskAccessDetailsVO_p
	 * @throws Exception
	 */
	public void getRightList(DeskAccessDetailsVO deskAccessDetailsVO_p) {

		DeskAccessDetailsDAO.getRightListQuery(deskAccessDetailsVO_p);
		if (deskAccessDetailsVO_p.getStrMsgType().equals("1")) {
			deskAccessDetailsVO_p.setStrMsgString("DeskAccessDetailsBO.getRightList() --> "
					+ deskAccessDetailsVO_p.getStrMsgString());
		}

	}
	
	/**
	 * to delete the data before insert 
	 *  
	 * @param deskAccessDetailsVO_p
	 */
	public void deleteRecords(DeskAccessDetailsVO deskAccessDetailsVO_p) {

		
		DeskAccessDetailsDAO.deleteQuery(deskAccessDetailsVO_p);

		if (deskAccessDetailsVO_p.getStrMsgType().equals("1")) {
			deskAccessDetailsVO_p.setStrMsgString("DeskAccessDetailsBO.insertRecord() --> "
					+ deskAccessDetailsVO_p.getStrMsgString());
		}
		

	}
	/**
	 * to insert the data 
	 *  
	 * @param deskAccessDetailsVO_p
	 */
	public void insertRecord(DeskAccessDetailsVO deskAccessDetailsVO_p) {

		
		DeskAccessDetailsDAO.insertQuery(deskAccessDetailsVO_p);

		if (deskAccessDetailsVO_p.getStrMsgType().equals("1")) {
			deskAccessDetailsVO_p.setStrMsgString("DeskAccessDetailsBO.insertRecord() --> "
					+ deskAccessDetailsVO_p.getStrMsgString());
		}
		

	}

}
