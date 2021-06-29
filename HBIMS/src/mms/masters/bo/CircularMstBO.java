package mms.masters.bo;

import mms.masters.dao.CircularMstDAO;
import mms.masters.vo.CircularMstVO;


// TODO: Auto-generated Javadoc
/**
 * The Class CircularMstBO.
 * 
 * @author manas meher
 */

public class CircularMstBO {

	/**
	 * to insert the data and check duplicate.
	 * 
	 * @param vo the vo
	 */

	public void insertQuery(CircularMstVO vo) {

		
		if (true) {
			CircularMstDAO.insertQuery(vo);

			if (vo.getStrMsgType().equals("1")) {

				String strErr = vo.getStrMsgString();

				vo.setStrMsgString("CircularMstBO.insertRecord() --> "
						+ strErr);
			}
		}
	}

	
public String getFileUploadName(CircularMstVO vo) throws Exception {

		String FileName=null;
		if (true) {
			FileName=CircularMstDAO.changeFileUpload(vo);
			System.out.println("FileName  " +FileName );

			if (vo.getStrMsgType().equals("1")) {

				String strErr = vo.getStrMsgString();

				vo.setStrMsgString("CircularMstBO.insertRecord() --> "
						+ strErr);
			}
		}
		return FileName;
	}

	/**
	 * to get data for modify page.
	 * 
	 * @param vo the vo
	 * 
	 * @throws Exception 	 */

	public void modifyQuery(CircularMstVO vo) {
		CircularMstDAO.modifyQuery(vo);
		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("CircularMstBO.modifyQuery() --> " + strErr);

		}
	}

	/**
	 * to check duplicate before update and update the record.
	 * 
	 * @param vo the vo
	 */

	public void updateRecord(CircularMstVO vo) {
		CircularMstDAO.updateQuery(vo);
	}

	/**
	 * Inits the add.
	 * 
	 * @param vo the vo
	 */
	public void initAdd(CircularMstVO vo) {

		// ComponentMstDAO.comboQuery(vo);

		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("ComponentMstBO.initAdd() --> "
					+ vo.getStrMsgString());
		}
	}
}
