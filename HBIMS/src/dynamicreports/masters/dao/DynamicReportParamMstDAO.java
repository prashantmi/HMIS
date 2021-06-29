/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportParamMstDAO.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import dynamicreports.masters.vo.DynamicReportParamMstVO;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportParamMstDAO.
 */
public class DynamicReportParamMstDAO {

	/**
	 * Gets the report type.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report type
	 */
	public static void getReportType(DynamicReportParamMstVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportParamMstDAO");

		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "select.drptType.1");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());

			web = dao.executeQry(nqryIndex);

			vo.setWsReportTypeValues(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportParamMstDAO.getReportType() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * Gets the report templates.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report templates
	 */
	public static void getReportTemplates(DynamicReportParamMstVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportParamMstDAO");

		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "select.drptTemplateList");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrReportTypeId());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());

			web = dao.executeQry(nqryIndex);

			vo.setWsReportValues(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportParamMstDAO.getReportTemplates() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * Gets the procedures.
	 * 
	 * @param vo
	 *            the vo
	 * @return the procedures
	 */
	public static void getProcedures(DynamicReportParamMstVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportParamMstDAO");

		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "select.drptProcedureList");
			nqryIndex = dao.setQuery(strquery);

			web = dao.executeQry(nqryIndex);

			vo.setWsProcedureValues(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportParamMstDAO.getProcedures() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * Gets the proc in paramters.
	 * 
	 * @param vo
	 *            the vo
	 * @return the proc in paramters
	 */
	public static void getProcInParamters(DynamicReportParamMstVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportParamMstDAO");

		String[] procDetails = vo.getStrProcedureName().replace("^", "#")
				.split("#");

		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "select.drptProcColList");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, procDetails[0]);
			dao.setQryValue(nqryIndex, 2, procDetails[1]);

			web = dao.executeQry(nqryIndex);

			vo.setWsProcInParam(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportParamMstDAO.getProcInParamters() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * Gets the proc pre in paramters.
	 * 
	 * @param vo
	 *            the vo
	 * @return the proc pre in paramters
	 */
	public static void getProcPreInParamters(DynamicReportParamMstVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportParamMstDAO");

		String strRptTemplateId = vo.getStrReportTemplateId();

		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "select.drptPreInParams");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, strRptTemplateId);

			web = dao.executeQry(nqryIndex);

			vo.setWsPreInParams(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportParamMstDAO.getProcPreInParamters() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * Gets the proc pre out paramters.
	 * 
	 * @param vo
	 *            the vo
	 * @return the proc pre out paramters
	 */
	public static void getProcPreOutParamters(DynamicReportParamMstVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportParamMstDAO");

		String strRptTemplateId = vo.getStrReportTemplateId();

		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "select.drptPreOutParams");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, strRptTemplateId);

			web = dao.executeQry(nqryIndex);

			vo.setWsPreInParams(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportParamMstDAO.getProcPreOutParamters() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * Gets the proc pre out paramters for header mapping combo.
	 * 
	 * @param vo
	 *            the vo
	 * @return the proc pre out paramters for header mapping combo
	 */
	public static void getProcPreOutParamtersForHeaderMappingCombo(
			DynamicReportParamMstVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportParamMstDAO");

		String strRptTemplateId = vo.getStrReportTemplateId();

		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "select.drptPreOutParams.header");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, strRptTemplateId);

			web = dao.executeQry(nqryIndex);

			vo.setWsHeaderMappingParamValues(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportParamMstDAO.getProcPreOutParamtersForHeaderMappingCombo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * Gets the pre rpt dtls.
	 * 
	 * @param vo
	 *            the vo
	 * @return the pre rpt dtls
	 */
	public static void getPreRptDtls(DynamicReportParamMstVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportParamMstDAO");

		String strReportTemplateId = vo.getStrReportTemplateId();

		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "select.drptPreRptDtls");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, strReportTemplateId);
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());

			web = dao.executeQry(nqryIndex);

			vo.setWsPreRptDtls(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportParamMstDAO.getPreRptDtls() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * Gets the rpt in param view dtls.
	 * 
	 * @param vo
	 *            the vo
	 * @return the rpt in param view dtls
	 */
	public static void getRptInParamViewDtls(DynamicReportParamMstVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportParamMstDAO");

		String strReportTemplateId = vo.getStrReportTemplateId();

		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "view.inparamDtls");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrReportProcId());
			dao.setQryValue(nqryIndex, 3, strReportTemplateId);

			web = dao.executeQry(nqryIndex);

			vo.setWsInParamView(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportParamMstDAO.getRptInParamViewDtls() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * Gets the rpt row base out param view dtls.
	 * 
	 * @param vo
	 *            the vo
	 * @return the rpt row base out param view dtls
	 */
	public static void getRptRowBaseOutParamViewDtls(DynamicReportParamMstVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportParamMstDAO");

		String strReportTemplateId = vo.getStrReportTemplateId();

		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "view.rowbasedOutParamDtls");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrReportProcId());
			dao.setQryValue(nqryIndex, 3, strReportTemplateId);

			web = dao.executeQry(nqryIndex);

			vo.setWsOutParamView(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportParamMstDAO.getRptRowBaseOutParamViewDtls() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * Gets the rpt col base out param view dtls.
	 * 
	 * @param vo
	 *            the vo
	 * @return the rpt col base out param view dtls
	 */
	public static void getRptColBaseOutParamViewDtls(DynamicReportParamMstVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportParamMstDAO");

		String strReportTemplateId = vo.getStrReportTemplateId();

		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(1, "view.colbasedOutParamDtls");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrReportProcId());
			dao.setQryValue(nqryIndex, 3, strReportTemplateId);

			web = dao.executeQry(nqryIndex);

			vo.setWsColParamView(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportParamMstDAO.getRptColBaseOutParamViewDtls() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * Gets the proc result set.
	 * 
	 * @param vo
	 *            the vo
	 * @return the proc result set
	 */
	public static void getProcResultSet(DynamicReportParamMstVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {

			daoObj = new HisDAO("dynamicreports", "DynamicReportParamMstDAO");

			String strProcName = vo.getStrProcedureName();

			String[] strInParamName = vo.getStrProcInColumnDtls();
			String[] strDefaultValue = vo.getStrInConstantValue();

			String strProcStart = "{ call " + strProcName + "( ";

			String strProcMid = "";

			for (int i = 0; i < strInParamName.length; i++) {

				if (i != strInParamName.length - 1) {
					strProcMid = strProcMid + "?, ";
				} else {
					strProcMid = strProcMid + "? ";
				}

			}

			strProcMid = strProcMid + ", ?, ?";

			String strProcEnd = ") }";

			String strProcCall = strProcStart + strProcMid + strProcEnd;

			int procIndex = daoObj.setProcedure(strProcCall.trim());

			int count = 0;

			int index = 0;

			for (int j = 0; j < strInParamName.length; j++) {

				index = index + 1;

				String columnName = strInParamName[j];
				// String dataType = strDefaultValue[j];

				if (columnName.equals("HOSP_CODE")) {

					daoObj.setProcInValue(procIndex, columnName,
							vo.getStrHospitalCode());
				} else if (columnName.equals("SEATID")) {

					daoObj.setProcInValue(procIndex, columnName,
							vo.getStrSeatId());
				} else {

					if (strDefaultValue[count] != null
							&& strDefaultValue[count].length() > 0) {

						if (strDefaultValue[count].equals("0")) {

							daoObj.setProcInValue(procIndex, columnName, "1");

						} else {

							daoObj.setProcInValue(procIndex, columnName,
									strDefaultValue[count]);
						}

					} else {

						daoObj.setProcInValue(procIndex, columnName, "1");

					}

					count = count + 1;

				}
			}

			daoObj.setProcOutValue(procIndex, "ERR", 1);
			daoObj.setProcOutValue(procIndex, "RESULTSET", 2);

			daoObj.executeProcedure(procIndex);
			String err = daoObj.getString(procIndex, "ERR");
			if (err == null)
				err = "";

			if (!err.equals(""))
				throw new Exception(err);

			ws = daoObj.getWebRowSet(procIndex, "RESULTSET");

			vo.setWsProcOutParam(ws);

		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrMsgString("DynamicReportParamMstDAO.getProcInParamters() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}

		}

	}

	/**
	 * Save row based rpt data.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static synchronized void saveRowBasedRptData(
			DynamicReportParamMstVO vo) {

		HisDAO dao = null;
		int nqryIndex;
		int nqryIndex2;
		int nqryIndex3;
		int nFuncIndex;
		String strProcTempId = "0";
		String strquery = "";
		String strQuery2 = "";
		String strQuery3 = "";
		String strFuncName = "";

		try {
			dao = new HisDAO("dynamicreport", "DynamicReportParamMstDAO");

			strFuncName = "{? = call DRPT_MST.GETTEMPLATEPROCID(? , ? )}";
			nFuncIndex = dao.setFunction(strFuncName);

			dao.setFuncInValue(nFuncIndex, 2, vo.getStrReportTemplateId());
			dao.setFuncInValue(nFuncIndex, 3, vo.getStrHospitalCode());
			dao.setFuncOutValue(nFuncIndex, 1);
			dao.executeFunction(nFuncIndex);
			strProcTempId = dao.getFuncString(nFuncIndex);

			strquery = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptParamMapping.1");
			nqryIndex = dao.setQuery(strquery);

			
			

			dao.setQryValue(nqryIndex, 1, vo.getStrReportTemplateId());
			dao.setQryValue(nqryIndex, 2, strProcTempId);
			dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 4, vo.getStrReportName());
			dao.setQryValue(nqryIndex, 5, vo.getStrIsCombo());
			dao.setQryValue(nqryIndex, 6, vo.getStrProcedureName());
			dao.setQryValue(nqryIndex, 7, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 8, "1");
			dao.setQryValue(nqryIndex, 9, "0");
			dao.setQryValue(nqryIndex, 10, vo.getStrReportHeaderTypeId());
			dao.setQryValue(nqryIndex, 11, vo.getStrReportHeaderParamReq());
			dao.setQryValue(nqryIndex, 12, vo.getStrReportHeaderParamType());
			dao.setQryValue(nqryIndex, 13, vo.getStrReportHeaderParamId());
			dao.execute(nqryIndex, 0);

			strQuery2 = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptParamMapping.2");

			if (vo.getStrProcInColumnDtls() != null)
				for (int i = 0, stopI = vo.getStrProcInColumnDtls().length; i < stopI; i++) {

					nqryIndex2 = dao.setQuery(strQuery2);
					

					dao.setQryValue(nqryIndex2, 1, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex2, 2, strProcTempId);
					dao.setQryValue(nqryIndex2, 3, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex2, 4, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex2, 5, strProcTempId);
					dao.setQryValue(nqryIndex2, 6, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex2, 7, vo.getStrInDisplayName()[i]);
					dao.setQryValue(nqryIndex2, 8,vo.getStrInConstantValue()[i]);
					dao.setQryValue(nqryIndex2, 9, vo.getStrParamCompType()[i]);
					dao.setQryValue(nqryIndex2, 10,vo.getStrProcInColumnDtls()[i]);
					dao.setQryValue(nqryIndex2, 11, vo.getStrComboQuery()[i]);
					dao.setQryValue(nqryIndex2, 12, "0");
					dao.setQryValue(nqryIndex2, 13, "0");

					dao.execute(nqryIndex2, 0);

				}

			strQuery3 = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptParamMapping.3");

			if (vo.getStrOutColumnName() != null)
				for (int i = 0, stopI = vo.getStrOutColumnName().length; i < stopI; i++) {

					nqryIndex3 = dao.setQuery(strQuery3);

					

					dao.setQryValue(nqryIndex3, 1, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex3, 2, strProcTempId);
					dao.setQryValue(nqryIndex3, 3, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex3, 4, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex3, 5, strProcTempId);
					dao.setQryValue(nqryIndex3, 6, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex3, 7,
							vo.getStrOutColumnDisplayName()[i]);
					dao.setQryValue(nqryIndex3, 8, vo.getStrOutColumnWidth()[i]);
					dao.setQryValue(nqryIndex3, 9,
							vo.getStrGroupByRequiredValue()[i]);
					dao.setQryValue(nqryIndex3, 10, vo.getStrOutColumnName()[i]);
					dao.setQryValue(nqryIndex3, 11,
							vo.getStrOrderByRequiredValue()[i]);
					dao.setQryValue(nqryIndex3, 12,
							vo.getStrTotalRequiredValue()[i]);
					dao.setQryValue(nqryIndex3, 13,
							vo.getStrOutColumnIndex()[i]);
					dao.setQryValue(nqryIndex3, 14,
							vo.getStrOutColumnActualIndex()[i]);
					dao.setQryValue(nqryIndex3, 15, "0");
					dao.setQryValue(nqryIndex3, 16, "0");
					dao.setQryValue(nqryIndex3, 17,
							vo.getStrOutColumnAlign()[i]);
					dao.execute(nqryIndex3, 0);

				}

			dao.fire();

		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrMsgString("DynamicReportParamMstDAO.saveRowBasedRptData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	/**
	 * Save col based rpt data.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static synchronized void saveColBasedRptData(
			DynamicReportParamMstVO vo) {

		HisDAO dao = null;
		int nqryIndex;
		int nqryIndex2;
		int nqryIndex3;
		int nqryIndex4;
		int nFuncIndex;
		String strProcTempId = "0";
		String strquery = "";
		String strQuery2 = "";
		String strQuery3 = "";
		String strQuery4 = "";
		String strFuncName = "";

		try {
			dao = new HisDAO("dynamicreport", "DynamicReportParamMstDAO");

			strFuncName = "{? = call DRPT_MST.GETTEMPLATEPROCID(? , ? )}";
			nFuncIndex = dao.setFunction(strFuncName);

			dao.setFuncInValue(nFuncIndex, 2, vo.getStrReportTemplateId());
			dao.setFuncInValue(nFuncIndex, 3, vo.getStrHospitalCode());
			dao.setFuncOutValue(nFuncIndex, 1);
			dao.executeFunction(nFuncIndex);
			strProcTempId = dao.getFuncString(nFuncIndex);

			strquery = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptParamMapping.1");
			nqryIndex = dao.setQuery(strquery);

			
			dao.setQryValue(nqryIndex, 1, vo.getStrReportTemplateId());
			dao.setQryValue(nqryIndex, 2, strProcTempId);
			dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 4, vo.getStrReportName());
			dao.setQryValue(nqryIndex, 5, vo.getStrIsCombo());
			dao.setQryValue(nqryIndex, 6, vo.getStrProcedureName());
			dao.setQryValue(nqryIndex, 7, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 8, "1");
			dao.setQryValue(nqryIndex, 9, "1");
			dao.setQryValue(nqryIndex, 10, vo.getStrReportHeaderTypeId());
			dao.setQryValue(nqryIndex, 11, vo.getStrReportHeaderParamReq());
			dao.setQryValue(nqryIndex, 12, vo.getStrReportHeaderParamType());
			dao.setQryValue(nqryIndex, 13, vo.getStrReportHeaderParamId());
			dao.execute(nqryIndex, 0);

			strQuery2 = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptParamMapping.2");

			if (vo.getStrProcInColumnDtls() != null)
				for (int i = 0, stopI = vo.getStrProcInColumnDtls().length; i < stopI; i++) {

					nqryIndex2 = dao.setQuery(strQuery2);

					dao.setQryValue(nqryIndex2, 1, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex2, 2, strProcTempId);
					dao.setQryValue(nqryIndex2, 3, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex2, 4, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex2, 5, strProcTempId);
					dao.setQryValue(nqryIndex2, 6, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex2, 7, vo.getStrInDisplayName()[i]);
					dao.setQryValue(nqryIndex2, 8,
							vo.getStrInConstantValue()[i]);
					dao.setQryValue(nqryIndex2, 9, vo.getStrParamCompType()[i]);
					dao.setQryValue(nqryIndex2, 10,
							vo.getStrProcInColumnDtls()[i]);
					dao.setQryValue(nqryIndex2, 11, vo.getStrComboQuery()[i]);
					dao.setQryValue(nqryIndex2, 12, "0");
					dao.setQryValue(nqryIndex2, 13, "0");

					dao.execute(nqryIndex2, 0);

				}

			strQuery3 = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptParamMapping.3");

			if (vo.getStrOutColumnName() != null)
				for (int i = 0, stopI = vo.getStrOutColumnName().length; i < stopI; i++) {

					nqryIndex3 = dao.setQuery(strQuery3);

					dao.setQryValue(nqryIndex3, 1, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex3, 2, strProcTempId);
					dao.setQryValue(nqryIndex3, 3, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex3, 4, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex3, 5, strProcTempId);
					dao.setQryValue(nqryIndex3, 6, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex3, 7,
							vo.getStrOutColumnDisplayName()[i]);
					dao.setQryValue(nqryIndex3, 8, "");
					dao.setQryValue(nqryIndex3, 9, "0");
					dao.setQryValue(nqryIndex3, 10, vo.getStrOutColumnName()[i]);
					dao.setQryValue(nqryIndex3, 11, "0");
					dao.setQryValue(nqryIndex3, 12, "0");
					dao.setQryValue(nqryIndex3, 13,
							vo.getStrOutColumnIndex()[i]);
					dao.setQryValue(nqryIndex3, 14,
							vo.getStrOutColumnActualIndex()[i]);
					dao.setQryValue(nqryIndex3, 15, "0");
					dao.setQryValue(nqryIndex3, 16, "0");
					dao.setQryValue(nqryIndex3, 17, "0");
					dao.execute(nqryIndex3, 0);

				}

			strQuery4 = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptParamMapping.4");

			if (vo.getStrColumnDisplayName() != null)
				for (int i = 0, stopI = vo.getStrColumnDisplayName().length; i < stopI; i++) {

					nqryIndex4 = dao.setQuery(strQuery4);

					dao.setQryValue(nqryIndex4, 1, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex4, 2, strProcTempId);
					dao.setQryValue(nqryIndex4, 3, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex4, 4, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex4, 5, strProcTempId);
					dao.setQryValue(nqryIndex4, 6, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex4, 7,
							vo.getStrColumnDisplayName()[i]);
					dao.setQryValue(nqryIndex4, 8, vo.getStrColumnPrefix()[i]);
					dao.setQryValue(nqryIndex4, 9, vo.getStrColumnFormula()[i]);
					dao.setQryValue(nqryIndex4, 10, vo.getStrColumnSuffix()[i]);
					dao.setQryValue(nqryIndex4, 11,
							vo.getStrOutColumnAlign()[i]);

					dao.execute(nqryIndex4, 0);

				}

			dao.fire();

		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrMsgString("DynamicReportParamMstDAO.saveColBasedRptData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	/**
	 * Save drill down rpt data.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static synchronized void saveDrillDownRptData(
			DynamicReportParamMstVO vo) {

		HisDAO dao = null;
		int nqryIndex;
		int nqryIndex2;
		int nqryIndex3;
		int nFuncIndex;
		String strProcTempId = "0";
		String strquery = "";
		String strQuery2 = "";
		String strQuery3 = "";
		String strFuncName = "";

		try {
			dao = new HisDAO("dynamicreport", "DynamicReportParamMstDAO");

			
			strFuncName = "{? = call DRPT_MST.GETTEMPLATEPROCID(? , ? )}";
			nFuncIndex = dao.setFunction(strFuncName);

			dao.setFuncInValue(nFuncIndex, 2, vo.getStrReportTemplateId());
			dao.setFuncInValue(nFuncIndex, 3, vo.getStrHospitalCode());
			dao.setFuncOutValue(nFuncIndex, 1);
			dao.executeFunction(nFuncIndex);
			strProcTempId = dao.getFuncString(nFuncIndex);

			strquery = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptParamMapping.1");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrReportTemplateId());
			dao.setQryValue(nqryIndex, 2, strProcTempId);
			dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 4, vo.getStrReportName());
			dao.setQryValue(nqryIndex, 5, vo.getStrIsCombo());
			dao.setQryValue(nqryIndex, 6, vo.getStrProcedureName());
			dao.setQryValue(nqryIndex, 7, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 8, vo.getStrIsLast());
			dao.setQryValue(nqryIndex, 9, vo.getStrIsColumnBaseRpt());
			dao.setQryValue(nqryIndex, 10, vo.getStrReportHeaderTypeId());
			dao.setQryValue(nqryIndex, 11, vo.getStrReportHeaderParamReq());
			dao.setQryValue(nqryIndex, 12, vo.getStrReportHeaderParamType());
			dao.setQryValue(nqryIndex, 13, vo.getStrReportHeaderParamId());
			dao.execute(nqryIndex, 0);

			strQuery2 = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptParamMapping.2");

			if (vo.getStrProcInColumnDtls() != null)
				for (int i = 0, stopI = vo.getStrProcInColumnDtls().length; i < stopI; i++) {

					nqryIndex2 = dao.setQuery(strQuery2);
						
					dao.setQryValue(nqryIndex2, 1, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex2, 2, strProcTempId);
					dao.setQryValue(nqryIndex2, 3, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex2, 4, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex2, 5, strProcTempId);
					dao.setQryValue(nqryIndex2, 6, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex2, 7, vo.getStrInDisplayName()[i]);
					dao.setQryValue(nqryIndex2, 8,vo.getStrInConstantValue()[i]);
					dao.setQryValue(nqryIndex2, 9, vo.getStrParamCompType()[i]);
					dao.setQryValue(nqryIndex2, 10,vo.getStrProcInColumnDtls()[i]);
					dao.setQryValue(nqryIndex2, 11, (vo.getStrComboQuery()[i].equals(null) || vo.getStrComboQuery()[i].equals(""))?"0":vo.getStrComboQuery()[i]);
					if (vo.getStrPreInParamValues() != null && vo.getStrPreInParamValues()[i] != null && !vo.getStrPreInParamValues()[i].equals("0")) {

						dao.setQryValue(nqryIndex2, 12, vo.getStrPreInParamValues()[i].replace("^", "#").split("#")[1]);
					} else
					{
						dao.setQryValue(nqryIndex2, 12, "0");
					}

					if (vo.getStrInParamType() != null
							&& vo.getStrInParamType()[i] != null
							&& !vo.getStrInParamType()[i].equals("0")) {

						dao.setQryValue(nqryIndex2, 13,
								vo.getStrInParamType()[i]);
					} else {
						dao.setQryValue(nqryIndex2, 13, "0");
					}

					dao.execute(nqryIndex2, 0);

				}

			strQuery3 = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptParamMapping.3");

			if (vo.getStrOutColumnName() != null)
				for (int i = 0, stopI = vo.getStrOutColumnName().length; i < stopI; i++) {

					nqryIndex3 = dao.setQuery(strQuery3);

					dao.setQryValue(nqryIndex3, 1, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex3, 2, strProcTempId);
					dao.setQryValue(nqryIndex3, 3, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex3, 4, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex3, 5, strProcTempId);
					dao.setQryValue(nqryIndex3, 6, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex3, 7,
							vo.getStrOutColumnDisplayName()[i]);
					dao.setQryValue(nqryIndex3, 8, vo.getStrOutColumnWidth()[i]);
					dao.setQryValue(nqryIndex3, 9,
							vo.getStrGroupByRequiredValue()[i]);
					dao.setQryValue(nqryIndex3, 10, vo.getStrOutColumnName()[i]);
					dao.setQryValue(nqryIndex3, 11,
							vo.getStrOrderByRequiredValue()[i]);
					dao.setQryValue(nqryIndex3, 12,
							vo.getStrTotalRequiredValue()[i]);
					dao.setQryValue(nqryIndex3, 13,
							vo.getStrOutColumnIndex()[i]);
					dao.setQryValue(nqryIndex3, 14,
							vo.getStrOutColumnActualIndex()[i]);
					dao.setQryValue(nqryIndex3, 15,
							vo.getStrHyperlinkRequiredValue()[i]);

					if (vo.getStrHyperlinkMappingValue()[i] != null
							&& !vo.getStrHyperlinkMappingValue()[i].trim()
									.equals("0")) {

						dao.setQryValue(nqryIndex3, 16,
								vo.getStrHyperlinkMappingValue()[i]);

					} else {
						dao.setQryValue(nqryIndex3, 16, "0");
					}

					dao.setQryValue(nqryIndex3, 17,
							vo.getStrOutColumnAlign()[i]);

					dao.execute(nqryIndex3, 0);

				}

			dao.fire();

		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrMsgString("DynamicReportParamMstDAO.saveDrillDownRptData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	/**
	 * Save merge rpt data.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static synchronized void saveMergeRptData(DynamicReportParamMstVO vo) {

		HisDAO dao = null;
		int nqryIndex;
		int nqryIndex2;
		int nqryIndex3;
		int nqryIndex4;
		int nFuncIndex;
		String strProcTempId = "0";
		String strquery = "";
		String strQuery2 = "";
		String strQuery3 = "";
		String strQuery4 = "";
		String strFuncName = "";

		try {
			dao = new HisDAO("dynamicreport", "DynamicReportParamMstDAO");

			strFuncName = "{? = call DRPT_MST.GETTEMPLATEPROCID(? , ? )}";
			nFuncIndex = dao.setFunction(strFuncName);

			
			dao.setFuncInValue(nFuncIndex, 2, vo.getStrReportTemplateId());
			dao.setFuncInValue(nFuncIndex, 3, vo.getStrHospitalCode());
			dao.setFuncOutValue(nFuncIndex, 1);
			dao.executeFunction(nFuncIndex);
			strProcTempId = dao.getFuncString(nFuncIndex);

			strquery = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptParamMapping.1");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrReportTemplateId());
			dao.setQryValue(nqryIndex, 2, strProcTempId);
			dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 4, vo.getStrReportLevelDisplayName());
			dao.setQryValue(nqryIndex, 5, vo.getStrIsCombo());
			dao.setQryValue(nqryIndex, 6, vo.getStrProcedureName());
			dao.setQryValue(nqryIndex, 7, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 8, vo.getStrIsLast());
			dao.setQryValue(nqryIndex, 9, vo.getStrIsColumnBaseRpt());
			dao.setQryValue(nqryIndex, 10, vo.getStrReportHeaderTypeId());
			dao.setQryValue(nqryIndex, 11, vo.getStrReportHeaderParamReq());
			dao.setQryValue(nqryIndex, 12, vo.getStrReportHeaderParamType());
			dao.setQryValue(nqryIndex, 13, vo.getStrReportHeaderParamId());
			dao.execute(nqryIndex, 0);

			strQuery2 = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"insert.drptParamMapping.2");

			if (vo.getStrProcInColumnDtls() != null)
				for (int i = 0, stopI = vo.getStrProcInColumnDtls().length; i < stopI; i++) {

					nqryIndex2 = dao.setQuery(strQuery2);

					dao.setQryValue(nqryIndex2, 1, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex2, 2, strProcTempId);
					dao.setQryValue(nqryIndex2, 3, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex2, 4, vo.getStrReportTemplateId());
					dao.setQryValue(nqryIndex2, 5, strProcTempId);
					dao.setQryValue(nqryIndex2, 6, vo.getStrHospitalCode());
					dao.setQryValue(nqryIndex2, 7, vo.getStrInDisplayName()[i]);
					dao.setQryValue(nqryIndex2, 8,
							vo.getStrInConstantValue()[i]);
					dao.setQryValue(nqryIndex2, 9, vo.getStrParamCompType()[i]);
					dao.setQryValue(nqryIndex2, 10,
							vo.getStrProcInColumnDtls()[i]);
					dao.setQryValue(nqryIndex2, 11, vo.getStrComboQuery()[i]);
					if (vo.getStrPreInParamValues() != null
							&& vo.getStrPreInParamValues()[i] != null
							&& !vo.getStrPreInParamValues()[i].equals("0")) {

						dao.setQryValue(nqryIndex2, 12, vo
								.getStrPreInParamValues()[i].replace("^", "#")
								.split("#")[1]);
					} else {
						dao.setQryValue(nqryIndex2, 12, "");
					}
					dao.setQryValue(nqryIndex2, 13, "");

					dao.execute(nqryIndex2, 0);

				}

			if (vo.getStrIsColumnBaseRpt().equals("0")) {

				strQuery3 = dynamicreports.qryHandler_dynamicreports.getQuery(
						1, "insert.drptParamMapping.3");

				if (vo.getStrOutColumnName() != null)
					for (int i = 0, stopI = vo.getStrOutColumnName().length; i < stopI; i++) {

						nqryIndex3 = dao.setQuery(strQuery3);

						dao.setQryValue(nqryIndex3, 1,
								vo.getStrReportTemplateId());
						dao.setQryValue(nqryIndex3, 2, strProcTempId);
						dao.setQryValue(nqryIndex3, 3, vo.getStrHospitalCode());
						dao.setQryValue(nqryIndex3, 4,
								vo.getStrReportTemplateId());
						dao.setQryValue(nqryIndex3, 5, strProcTempId);
						dao.setQryValue(nqryIndex3, 6, vo.getStrHospitalCode());
						dao.setQryValue(nqryIndex3, 7,
								vo.getStrOutColumnDisplayName()[i]);
						dao.setQryValue(nqryIndex3, 8,
								vo.getStrOutColumnWidth()[i]);
						dao.setQryValue(nqryIndex3, 9,
								vo.getStrGroupByRequiredValue()[i]);
						dao.setQryValue(nqryIndex3, 10,
								vo.getStrOutColumnName()[i]);
						dao.setQryValue(nqryIndex3, 11,
								vo.getStrOrderByRequiredValue()[i]);
						dao.setQryValue(nqryIndex3, 12,
								vo.getStrTotalRequiredValue()[i]);
						dao.setQryValue(nqryIndex3, 13,
								vo.getStrOutColumnIndex()[i]);
						dao.setQryValue(nqryIndex3, 14,
								vo.getStrOutColumnActualIndex()[i]);
						dao.setQryValue(nqryIndex3, 15, "0");
						dao.setQryValue(nqryIndex3, 16, "0");
						dao.setQryValue(nqryIndex3, 17,
								vo.getStrOutColumnAlign()[i]);

						dao.execute(nqryIndex3, 0);

					}

			} else {

				strQuery3 = dynamicreports.qryHandler_dynamicreports.getQuery(
						1, "insert.drptParamMapping.3");

				if (vo.getStrOutColumnName() != null)
					for (int i = 0, stopI = vo.getStrOutColumnName().length; i < stopI; i++) {

						nqryIndex3 = dao.setQuery(strQuery3);

						dao.setQryValue(nqryIndex3, 1,
								vo.getStrReportTemplateId());
						dao.setQryValue(nqryIndex3, 2, strProcTempId);
						dao.setQryValue(nqryIndex3, 3, vo.getStrHospitalCode());
						dao.setQryValue(nqryIndex3, 4,
								vo.getStrReportTemplateId());
						dao.setQryValue(nqryIndex3, 5, strProcTempId);
						dao.setQryValue(nqryIndex3, 6, vo.getStrHospitalCode());
						dao.setQryValue(nqryIndex3, 7,
								vo.getStrOutColumnDisplayName()[i]);
						dao.setQryValue(nqryIndex3, 8, "0");
						dao.setQryValue(nqryIndex3, 9, "0");
						dao.setQryValue(nqryIndex3, 10,
								vo.getStrOutColumnName()[i]);
						dao.setQryValue(nqryIndex3, 11, "0");
						dao.setQryValue(nqryIndex3, 12, "0");
						dao.setQryValue(nqryIndex3, 13,
								vo.getStrOutColumnIndex()[i]);
						dao.setQryValue(nqryIndex3, 14,
								vo.getStrOutColumnActualIndex()[i]);
						dao.setQryValue(nqryIndex3, 15, "0");
						dao.setQryValue(nqryIndex3, 16, "0");
						dao.setQryValue(nqryIndex3, 17, "0");
						dao.execute(nqryIndex3, 0);

					}

				strQuery4 = dynamicreports.qryHandler_dynamicreports.getQuery(
						1, "insert.drptParamMapping.4");

				if (vo.getStrColumnDisplayName() != null)
					for (int i = 0, stopI = vo.getStrColumnDisplayName().length; i < stopI; i++) {

						nqryIndex4 = dao.setQuery(strQuery4);

						dao.setQryValue(nqryIndex4, 1,
								vo.getStrReportTemplateId());
						dao.setQryValue(nqryIndex4, 2, strProcTempId);
						dao.setQryValue(nqryIndex4, 3, vo.getStrHospitalCode());
						dao.setQryValue(nqryIndex4, 4,
								vo.getStrReportTemplateId());
						dao.setQryValue(nqryIndex4, 5, strProcTempId);
						dao.setQryValue(nqryIndex4, 6, vo.getStrHospitalCode());
						dao.setQryValue(nqryIndex4, 7,
								vo.getStrColumnDisplayName()[i]);
						dao.setQryValue(nqryIndex4, 8,
								vo.getStrColumnPrefix()[i]);
						dao.setQryValue(nqryIndex4, 9,
								vo.getStrColumnFormula()[i]);
						dao.setQryValue(nqryIndex4, 10,
								vo.getStrColumnSuffix()[i]);
						dao.setQryValue(nqryIndex4, 11,
								vo.getStrOutColumnAlign()[i]);

						dao.execute(nqryIndex4, 0);

					}

			}

			dao.fire();

		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrMsgString("DynamicReportParamMstDAO.saveMergeRptData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

	/**
	 * Cancel rpt data.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static synchronized void cancelRptData(DynamicReportParamMstVO vo) {

		HisDAO dao = null;
		int nqryIndex;
		int nqryIndex2;
		int nqryIndex3;
		int nqryIndex4;

		String strquery = "";
		String strQuery2 = "";
		String strQuery3 = "";
		String strQuery4 = "";

		try {
			dao = new HisDAO("dynamicreport", "DynamicReportParamMstDAO");

			strquery = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"update.drptProcDtls");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 3, vo.getStrReportProcId());
			dao.setQryValue(nqryIndex, 4, vo.getStrReportTemplateId());

			dao.execute(nqryIndex, 0);

			strQuery2 = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"delete.drptInParams");

			nqryIndex2 = dao.setQuery(strQuery2);

			dao.setQryValue(nqryIndex2, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex2, 2, vo.getStrReportProcId());
			dao.setQryValue(nqryIndex2, 3, vo.getStrReportTemplateId());

			dao.execute(nqryIndex2, 0);

			strQuery3 = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"delete.drptOutParams");

			nqryIndex3 = dao.setQuery(strQuery3);

			dao.setQryValue(nqryIndex3, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex3, 2, vo.getStrReportProcId());
			dao.setQryValue(nqryIndex3, 3, vo.getStrReportTemplateId());

			dao.execute(nqryIndex3, 0);

			strQuery4 = dynamicreports.qryHandler_dynamicreports.getQuery(1,
					"delete.drptColumnParams");

			nqryIndex4 = dao.setQuery(strQuery4);

			dao.setQryValue(nqryIndex4, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex4, 2, vo.getStrReportProcId());
			dao.setQryValue(nqryIndex4, 3, vo.getStrReportTemplateId());

			dao.execute(nqryIndex4, 0);

			dao.fire();

		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrMsgString("DynamicReportParamMstDAO.cancelRptData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}

		}

	}

}
