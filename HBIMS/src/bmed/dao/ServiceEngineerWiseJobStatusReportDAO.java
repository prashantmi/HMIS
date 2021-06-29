package bmed.dao;

import javax.sql.rowset.WebRowSet;

import bmed.vo.ServiceEngineerWiseJobStatusReportVO;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

public class ServiceEngineerWiseJobStatusReportDAO {

	public static void getServiceEngineerCombo(ServiceEngineerWiseJobStatusReportVO serviceEngineerWiseJobStatusReportVO_p,HisDAO hisDAO_p)
			throws Exception {
		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_HEMT_SERVICE_ENGG_MST_CMB(?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(serviceEngineerWiseJobStatusReportVO_p);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", serviceEngineerWiseJobStatusReportVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", serviceEngineerWiseJobStatusReportVO_p.getStrHospitalCode(),2);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,3); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,4); // Cursor

			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			/* Sets The WebRowSet in ItemBrandMstVO */
			serviceEngineerWiseJobStatusReportVO_p.setWrsServiceEngineerComboOptions(webRowSet);

		} catch (Exception exception) {
			throw new Exception("ServiceEngineerWiseJobStatusReportDAO.getServiceEngineerCombo(ServiceEngineerWiseJobStatusReportVO)-->"
					+ exception.getMessage());
		}

	}

}