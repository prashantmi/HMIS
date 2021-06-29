/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportsTransBO.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.reports.bo;

import dynamicreports.reports.dao.DynamicReportsTransDAO;
import dynamicreports.reports.vo.DynamicReportsTransVO;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportsTransBO.
 */
public class DynamicReportsTransBO {

	/**
	 * Inits the.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void init(DynamicReportsTransVO vo) {

		DynamicReportsTransDAO.getReportTemplates(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportsTransBO.init() --> " + strErr);
		}

	}

	/*
	 * public void getReportList(DynamicReportsTransVO vo) {
	 * 
	 * DynamicReportsTransDAO.getReportTemplates(vo);
	 * 
	 * if (vo.getStrMsgType().equals("1")) {
	 * 
	 * String strErr = vo.getStrMsgString();
	 * 
	 * vo.setStrMsgString("DynamicReportsTransBO.getReportList() --> " +
	 * strErr); }
	 * 
	 * }
	 */
	/**
	 * Gets the proc and in out param dtls.
	 * 
	 * @param vo
	 *            the vo
	 * @return the proc and in out param dtls
	 */
	public void getProcAndInOutParamDtls(DynamicReportsTransVO vo) {

		DynamicReportsTransDAO.getReportProcDtls(vo);
		DynamicReportsTransDAO.getReportInParamDtls(vo);
		DynamicReportsTransDAO.getReportOutParamDtls(vo);

		if (vo.getStrReportTypeId().equals("2")) {

			DynamicReportsTransDAO.getReportColumnParamDtls(vo);

		}

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportsTransBO.getProcAndInParamDtls() --> "
					+ strErr);
		}

	}

	/**
	 * Gets the drill down proc dtls.
	 * 
	 * @param vo
	 *            the vo
	 * @return the drill down proc dtls
	 */
	public void getDrillDownProcDtls(DynamicReportsTransVO vo) {

		DynamicReportsTransDAO.getReportProcDtls(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportsTransBO.getProcAndInParamDtls() --> "
					+ strErr);
		}

	}

	/**
	 * Gets the proc next level param dtls.
	 * 
	 * @param vo
	 *            the vo
	 * @return the proc next level param dtls
	 */
	public void getProcNextLevelParamDtls(DynamicReportsTransVO vo) {

		DynamicReportsTransDAO.getReportInParamDtlsForNextLevel(vo);
		DynamicReportsTransDAO.getReportOutParamDtlsForNextLevel(vo);

		if (vo.getStrReportIsColBased().equals("1")) {

			DynamicReportsTransDAO.getReportColumnParamDtlsForNextLevel(vo);

		}

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportsTransBO.getProcAndInParamDtls() --> "
					+ strErr);
		}

	}

	/**
	 * Generate records.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void generateRecords(DynamicReportsTransVO vo) {

		DynamicReportsTransDAO.getReportHeaders(vo);

		DynamicReportsTransDAO.generateData(vo);

		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("DynamicReportsTransDAO.generateRecords()---->"
					+ vo.getStrMsgString());
	}

}
