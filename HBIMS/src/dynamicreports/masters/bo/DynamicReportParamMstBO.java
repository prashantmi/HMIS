/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportParamMstBO.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.bo;

import dynamicreports.masters.dao.DynamicReportParamMstDAO;
import dynamicreports.masters.vo.DynamicReportParamMstVO;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportParamMstBO.
 */
public class DynamicReportParamMstBO {

	/**
	 * Inits the.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void init(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.getReportType(vo);
		DynamicReportParamMstDAO.getProcedures(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.init() --> " + strErr);
		}

	}

	/**
	 * Gets the report list.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report list
	 */
	public void getReportList(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.getReportTemplates(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.getReportList() --> "
					+ strErr);
		}

	}

	/**
	 * Gets the proc in param list.
	 * 
	 * @param vo
	 *            the vo
	 * @return the proc in param list
	 */
	public void getProcInParamList(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.getProcInParamters(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.getReportList() --> "
					+ strErr);
		}

	}

	/**
	 * Gets the proc in out param list.
	 * 
	 * @param vo
	 *            the vo
	 * @return the proc in out param list
	 */
	public void getProcInOutParamList(DynamicReportParamMstVO vo) {

		if (vo.getStrParamTypeId().equals("1")) {

			DynamicReportParamMstDAO.getProcPreInParamters(vo);

		} else {

			DynamicReportParamMstDAO.getProcPreOutParamters(vo);

		}

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.getReportList() --> "
					+ strErr);
		}

	}

	/**
	 * Gets the drill down proc in param list.
	 * 
	 * @param vo
	 *            the vo
	 * @return the drill down proc in param list
	 */
	public void getDrillDownProcInParamList(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.getProcInParamters(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.getDrillDownProcInParamList() --> "
					+ strErr);
		}

	}

	/**
	 * Gets the merge proc in param list.
	 * 
	 * @param vo
	 *            the vo
	 * @return the merge proc in param list
	 */
	public void getMergeProcInParamList(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.getProcPreInParamters(vo);
		DynamicReportParamMstDAO.getProcInParamters(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.getMergeProcInParamList() --> "
					+ strErr);
		}

	}

	/**
	 * Gets the pre rpt dtls.
	 * 
	 * @param vo
	 *            the vo
	 * @return the pre rpt dtls
	 */
	public void getPreRptDtls(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.getPreRptDtls(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.getPreRptDtls() --> "
					+ strErr);
		}

	}

	/**
	 * Gets the proc out param list.
	 * 
	 * @param vo
	 *            the vo
	 * @return the proc out param list
	 */
	public void getProcOutParamList(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.getProcPreInParamters(vo);
		DynamicReportParamMstDAO
				.getProcPreOutParamtersForHeaderMappingCombo(vo);
		DynamicReportParamMstDAO.getProcResultSet(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.getReportList() --> "
					+ strErr);
		}

	}

	/**
	 * Save row based rpt data.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void saveRowBasedRptData(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.saveRowBasedRptData(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.saveRowBasedRptData() --> "
					+ strErr);
		}

	}

	/**
	 * Save col based rpt data.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void saveColBasedRptData(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.saveColBasedRptData(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.saveColBasedRptData() --> "
					+ strErr);
		}

	}

	/**
	 * Save drill down rpt data.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void saveDrillDownRptData(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.saveDrillDownRptData(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.saveDrillDownRptData() --> "
					+ strErr);
		}

	}

	/**
	 * Save merge rpt data.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void saveMergeRptData(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.saveMergeRptData(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.saveMergeRptData() --> "
					+ strErr);
		}

	}

	/**
	 * Cancel report.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void cancelReport(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.cancelRptData(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.cancelReport() --> "
					+ strErr);
		}

	}

	/**
	 * Row based view.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void rowBasedView(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.getRptInParamViewDtls(vo);
		DynamicReportParamMstDAO.getRptRowBaseOutParamViewDtls(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.rowBasedView() --> "
					+ strErr);
		}

	}

	/**
	 * Col based view.
	 * 
	 * @param vo
	 *            the vo
	 */
	public void colBasedView(DynamicReportParamMstVO vo) {

		DynamicReportParamMstDAO.getRptInParamViewDtls(vo);

		DynamicReportParamMstDAO.getRptColBaseOutParamViewDtls(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("DynamicReportParamMstBO.colBasedView() --> "
					+ strErr);
		}

	}

}
