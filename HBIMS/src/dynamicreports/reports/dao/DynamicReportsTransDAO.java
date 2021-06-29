/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportsTransDAO.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.reports.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import dynamicreports.reports.vo.DynamicReportsTransVO;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportsTransDAO.
 */
public class DynamicReportsTransDAO {

	/*
	 * public static void getReportType(DynamicReportsTransVO vo) {
	 * 
	 * HisDAO dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
	 * 
	 * WebRowSet web = null; int nqryIndex;
	 * 
	 * try { String strquery = dynamicreports.qryHandler_dynamicreports
	 * .getQuery(2, "select.drptType"); nqryIndex = dao.setQuery(strquery);
	 * 
	 * dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
	 * 
	 * web = dao.executeQry(nqryIndex);
	 * 
	 * vo.setWsReportTypeValues(web);
	 * 
	 * } catch (Exception e) {
	 * vo.setStrMsgString("DynamicReportsTransDAO.getReportType() --> " +
	 * e.getMessage()); vo.setStrMsgType("1");
	 * 
	 * } finally { if (dao != null) { dao.free(); dao = null; } }
	 * 
	 * }
	 */

	/**
	 * Gets the report templates.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report templates
	 */
	public static void getReportTemplates(DynamicReportsTransVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
	//	//dao.setConnType("2");
		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(2, "select.drptTemplateList");
			nqryIndex = dao.setQuery(strquery);

			// dao.setQryValue(nqryIndex, 1, vo.getStrReportTypeId());
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());

			web = dao.executeQry(nqryIndex);

			vo.setWsReportTemplateValues(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportsTransDAO.getReportTemplates() --> "
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
	 * Gets the report proc dtls.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report proc dtls
	 */
	public static void getReportProcDtls(DynamicReportsTransVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
//		//dao.setConnType("2");
		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(2, "select.drptTemplateProc");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrReportTemplateId());

			web = dao.executeQry(nqryIndex);

			vo.setWsReportProcValues(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportsTransDAO.getReportProcDtls() --> "
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
	 * Gets the report in param dtls.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report in param dtls
	 */
	public static void getReportInParamDtls(DynamicReportsTransVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
		//dao.setConnType("2");
		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(2, "select.drptInParamDtl");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrReportTemplateId());

			web = dao.executeQry(nqryIndex);

			vo.setWsReportInParamValues(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportsTransDAO.getReportInParamDtls() --> "
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
	 * Gets the report in param dtls for next level.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report in param dtls for next level
	 */
	public static void getReportInParamDtlsForNextLevel(DynamicReportsTransVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
		//dao.setConnType("2");
		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(2, "select.drptInParamDtl2");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrProcId());
			dao.setQryValue(nqryIndex, 3, vo.getStrReportTemplateId());

			web = dao.executeQry(nqryIndex);

			vo.setWsReportInParamValues(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportsTransDAO.getReportInParamDtlsForNextLevel() --> "
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
	 * Gets the report out param dtls.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report out param dtls
	 */
	public static void getReportOutParamDtls(DynamicReportsTransVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
		//dao.setConnType("2");
		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(2, "select.drptOutParamDtl");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrReportTemplateId());

			web = dao.executeQry(nqryIndex);

			vo.setWsReportOutParamValues(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportsTransDAO.getReportOutParamDtls() --> "
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
	 * Gets the report out param dtls for next level.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report out param dtls for next level
	 */
	public static void getReportOutParamDtlsForNextLevel(
			DynamicReportsTransVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
		//dao.setConnType("2");
		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(2, "select.drptOutParamDtl2");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrProcId());
			dao.setQryValue(nqryIndex, 3, vo.getStrReportTemplateId());

			web = dao.executeQry(nqryIndex);

			vo.setWsReportOutParamValues(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportsTransDAO.getReportOutParamDtlsForNextLevel() --> "
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
	 * Gets the report column param dtls.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report column param dtls
	 */
	public static void getReportColumnParamDtls(DynamicReportsTransVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
		//dao.setConnType("2");
		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(2, "select.drptColParamDtl");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrReportTemplateId());

			web = dao.executeQry(nqryIndex);

			vo.setWsReportColParamValues(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportsTransDAO.getReportColumnParamDtls() --> "
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
	 * Gets the report column param dtls for next level.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report column param dtls for next level
	 */
	public static void getReportColumnParamDtlsForNextLevel(
			DynamicReportsTransVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
		//dao.setConnType("2");
		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(2, "select.drptColParamDtl2");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrProcId());
			dao.setQryValue(nqryIndex, 3, vo.getStrReportTemplateId());

			web = dao.executeQry(nqryIndex);

			vo.setWsReportColParamValues(web);

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportsTransDAO.getReportColumnParamDtlsForNextLevel() --> "
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
	 * Gets the combo query.
	 * 
	 * @param strTemplateId
	 *            the str template id
	 * @param strProcId
	 *            the str proc id
	 * @param strParamId
	 *            the str param id
	 * @param strHospCode
	 *            the str hosp code
	 * @return the combo query
	 */
	public static String getComboQuery(String strTemplateId, String strProcId,
			String strParamId, String strHospCode) {
		HisDAO dao = null;
		String strQuery = "";
		String strComboQuery = "";
		int nQueryIndex = 0;
		WebRowSet ws = null;
		try {
			dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
			//dao.setConnType("2");
			strQuery = dynamicreports.qryHandler_dynamicreports.getQuery(2,
					"select.comboQuery");
			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, strHospCode);
			dao.setQryValue(nQueryIndex, 2, strParamId);
			dao.setQryValue(nQueryIndex, 3, strProcId);
			dao.setQryValue(nQueryIndex, 4, strTemplateId);

			ws = dao.executeQry(nQueryIndex);

			if (ws != null && ws.next()) {
				strComboQuery = ws.getString(1);
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			dao = null;
			ws = null;
		}

		return strComboQuery;
	}

	/**
	 * Gets the report headers.
	 * 
	 * @param vo
	 *            the vo
	 * @return the report headers
	 */
	public static void getReportHeaders(DynamicReportsTransVO vo) {

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
		//dao.setConnType("2");
		WebRowSet web = null;

		String strHeaderQuery = "";
		String strHeaderHtmlContent = "";

		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(2, "select.drptHeaders");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrReportHeaderTypeId());

			web = dao.executeQry(nqryIndex);

			if (web != null && web.size() > 0 && web.next()) {

				strHeaderQuery = web.getString(1);
				strHeaderHtmlContent = web.getString(2);

			}

			vo.setStrReportHeaderHtmlContent(strHeaderHtmlContent);

			web = null;
			if (!strHeaderQuery.trim().equals("")) {

				if (strHeaderQuery.contains("?")) {

					nqryIndex = dao.setQuery(strHeaderQuery);

					dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());

					if (vo.getStrReportHeaderParamReq().equals("1")) {

						dao.setQryValue(nqryIndex, 2,
								vo.getStrReportHeaderParamValue());

					}

					web = dao.executeQry(nqryIndex);

				} else {

					web = dao.getQryResult(strHeaderQuery);

				}

				if (web != null && web.size() > 0 && web.next()) {

					int colCount = web.getMetaData().getColumnCount();

					String[] strHeader = new String[colCount];

					for (int i = 0; i < colCount; i++) {

						strHeader[i] = web.getString((i + 1));

					}

					vo.setStrHeader(strHeader);

				}

			}

		} catch (Exception e) {
			vo.setStrMsgString("DynamicReportsTransDAO.getReportHeaders() --> "
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
	 * Gets the report colors.
	 * 
	 * @param strHospCode
	 *            the str hosp code
	 * @return the report colors
	 * @throws Exception
	 *             the exception
	 */
	public static String getReportColors(String strHospCode) throws Exception {
		HisDAO dao = null;
		String strQuery = "";
		String strColorCodes = "";
		int nQueryIndex = 0;
		WebRowSet ws = null;
		try {
			dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
			//dao.setConnType("2");
			strQuery = dynamicreports.qryHandler_dynamicreports.getQuery(2,
					"select.drptColorCodes").replace("?", strHospCode);
			nQueryIndex = dao.setQuery(strQuery);

			ws = dao.executeQry(nQueryIndex);

			if (ws != null && ws.next()) {
				strColorCodes = ws.getString(1);
			}

		} catch (Exception e) {

			throw e;

		} finally {
			dao = null;
			ws = null;
		}

		return strColorCodes;
	}

	/**
	 * Gets the result set for query.
	 * 
	 * @param strQuery
	 *            the str query
	 * @param strHospitalCode
	 *            the str hospital code
	 * @return the result set for query
	 * @throws Exception
	 *             the exception
	 */
	public static WebRowSet getResultSetForQuery(String strQuery,
			String strHospitalCode) throws Exception {
		HisDAO dao = null;
		int nQueryIndex = 0;
		WebRowSet ws = null;
		try {
			dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
			//dao.setConnType("2");
			strQuery = strQuery.replace("?", strHospitalCode);

			nQueryIndex = dao.setQuery(strQuery);

			ws = dao.executeQry(nQueryIndex);

		} catch (Exception e) {

			throw e;
		} finally {
			dao = null;

		}

		return ws;

	}

	/**
	 * Exec formula.
	 * 
	 * @param strFormula
	 *            the str formula
	 * @return the string
	 */
	public static String execFormula(String strFormula) {

		String result = "";

		HisDAO dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
		//dao.setConnType("2");
		WebRowSet web = null;
		int nqryIndex;

		try {
			String strquery = dynamicreports.qryHandler_dynamicreports
					.getQuery(2, "select.execFormula");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, strFormula);

			web = dao.executeQry(nqryIndex);

			if (web != null && web.next()) {

				result = web.getString(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

		return result;

	}

	/**
	 * Generate data.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void generateData(DynamicReportsTransVO vo) {
		String strProcName = "";
		HisDAO dao = null;

		int nProcIndex = 0;

		// String procString = "";

		WebRowSet ws = null;

		try {

			strProcName = vo.getStrCallProcedure();

			dao = new HisDAO("dynamicreports", "DynamicReportsTransDAO");
			//dao.setConnType("2");
			nProcIndex = dao.setProcedure(strProcName);

			if (vo.getStrInParamName() != null
					&& vo.getStrInParamName().length > 0) {
				for (int j = 0; j <= vo.getStrInParamName().length + 1; j++) {

					if (j == vo.getStrInParamName().length) {

						dao.setProcOutValue(nProcIndex, "ERR", 1);

						// procString = procString+" ERR=> ";

					} else if (j == (vo.getStrInParamName().length + 1)) {

						dao.setProcOutValue(nProcIndex, "resultset", 2);

						// procString = procString+" RESULTSET=> ";

					} else {

						if (!vo.getStrInConstantValue()[j].equals("0")
								|| vo.getStrInConstantValue()[j].length() != 0) {

							if (vo.getStrInParamName()[j].trim()
									.equalsIgnoreCase("GROUPORDERBY")) {

								// procString =
								// procString+" GROUPORDERBY=>"+vo.getStrOrderString()+" ";

								dao.setProcInValue(nProcIndex,
										vo.getStrInParamName()[j],
										vo.getStrOrderString());

							} else if (vo.getStrInParamName()[j].trim()
									.equalsIgnoreCase("HOSP_CODE")) {

								dao.setProcInValue(nProcIndex,
										vo.getStrInParamName()[j],
										vo.getStrHospitalCode());

								// procString =
								// procString+" HOSP_CODE=>"+vo.getStrHospitalCode()+" ";

							}
							else if (vo.getStrInParamName()[j].trim()
									.equalsIgnoreCase("SEAT_ID")) {

								dao.setProcInValue(nProcIndex,
										vo.getStrInParamName()[j],
										vo.getStrSeatId());

								// procString =
								// procString+" HOSP_CODE=>"+vo.getStrHospitalCode()+" ";

							} else {

								dao.setProcInValue(nProcIndex,
										vo.getStrInParamName()[j],
										vo.getStrInConstantValue()[j]);

								// procString =
								// procString+" "+vo.getStrInParamName()[j]+"=>"+vo.getStrInConstantValue()[j]+" ";

							}

						}

					}

				}

				
				dao.executeProcedure(nProcIndex);

				String err = dao.getString(nProcIndex, "ERR");

				if (err == null)
					err = "";

				if (err.equals("")) {

					ws = dao.getWebRowSet(nProcIndex, "resultset");

				} else {

					throw new Exception(err);

				}

				vo.setWsGeneratedDatas(ws);

			} else {

				throw new Exception("No Parameter Exists");

			}

		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrMsgString("DynamicReportsTransDAO.generateData() --> "
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
