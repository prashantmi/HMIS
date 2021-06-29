/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportTemplateMstBO.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.bo;

import dynamicreports.masters.dao.DynamicReportTemplateMstDAO;
import dynamicreports.masters.vo.DynamicReportTemplateMstVO;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportTemplateMstBO.
 */
public class DynamicReportTemplateMstBO {

	/**
	 * Gets the report header types.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report header types
	 */
	public void getReportHeaderTypes(DynamicReportTemplateMstVO vo) {

		DynamicReportTemplateMstDAO.getReportHeaderType(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportTemplateMstBO.getReportHeaderTypes() --> "
					+ strErr);
		}

	}

	/**
	 * to insert the data.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void insertQuery(DynamicReportTemplateMstVO vo) {
		DynamicReportTemplateMstDAO.chkDuplicate(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportTemplateMstBO.insertQuery() --> "
					+ strErr);
		}
		if (vo.isbExistStatus() == true) {
			DynamicReportTemplateMstDAO.insertQuery(vo);

			if (vo.getStrMsgType().equals("1")) {

				String strErr = vo.getStrMsgString();

				vo.setStrMsgString("DynamicReportTemplateMstBO.insertQuery() --> "
						+ strErr);
			}
		}
	}

	/**
	 * to get data for modify page.
	 * 
	 * @param vo
	 *            the vo
	 */

	public void modifyRecord(DynamicReportTemplateMstVO vo) {

		DynamicReportTemplateMstDAO.getReportHeaderType(vo);

		DynamicReportTemplateMstDAO.modifyQuery(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportTemplateMstBO.modifyRecord() --> "
					+ strErr);
		}

	}

	/**
	 * to check duplicate before update and update the record.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void updateRecord(DynamicReportTemplateMstVO vo) {

		DynamicReportTemplateMstDAO.initialUpdateQuery(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("DynamicReportTemplateMstBO.updateRecord() --> "
					+ vo.getStrMsgString());
		}
		if (vo.isbExistStatus() == true) {
			DynamicReportTemplateMstDAO.updateQuery(vo);
		}
		if (vo.getStrMsgType().equals("1")) {

			vo.setStrMsgString("DynamicReportTemplateMstBO.updateRecord() --> "
					+ vo.getStrMsgString());
		}

	}

}
