
package new_investigation.reportGenerator.DataProcessing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import new_investigation.reportGenerator.DataHelper.Config;
import new_investigation.reportGenerator.DataHelper.PGDataHelper;
import new_investigation.reportGenerator.DataHelper.PropertiesHelper;
import new_investigation.reportGenerator.DataHelper.QueryConfig;
import new_investigation.reportGenerator.Logging.ServiceLogger;
import new_investigation.reportGenerator.MongoHelper.MongoXmlHandler;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVO;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;
import new_investigation.reportGenerator.vo.ReportGeneratorVO;


public class PDFProcessing {

	public JsonObject processingData(ReportGeneratorVO reportGeneratorVO) {

		JsonObject jsonResponse = new JsonObject();

		Connection conn = null;
		try {
			conn = PGDataHelper.getConnection();
			if (conn == null) {
				conn = PGDataHelper.createPostgresConnection();
			}
			reportGeneratorVO.getPatCRNo();
			jsonResponse = processingToGenerateReport(conn,false, reportGeneratorVO);
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.commit();
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return jsonResponse;
	}

	
	private JsonObject processingToGenerateReport(Connection conn, boolean compulsory, ReportGeneratorVO reportGeneratorVO) {
		
		List<ResultEntryVOGroupByValidatedBy> workOrderGroupListForresultEntry = new ArrayList<ResultEntryVOGroupByValidatedBy>();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		JsonObject jsonResponse = new JsonObject();
		JsonObject jsonReportPDFReturn = new JsonObject();
		String isSuccess="0";

		try {
			Log(Level.INFO, "Fetch Requisition for pdf printing Function:pprocessingToGenerateReport");
			List<ResultEntryVOGroupByValidatedBy> reportGroupByList = new ArrayList<ResultEntryVOGroupByValidatedBy>();
			CallableStatement cstmt1 = null;
			ResultSet rs1 = null;

			cstmt1 = conn.prepareCall(QueryConfig.P_GET_PDF_WORKORDERLISTDATA_NEW_PM);

			cstmt1.setString(1, reportGeneratorVO.getLaboratoryCode());
			cstmt1.setString(2, reportGeneratorVO.getPatCRNo());
			cstmt1.setString(3, reportGeneratorVO.getHospitalCode());
			cstmt1.registerOutParameter(4, Types.REF);
			cstmt1.registerOutParameter(5, Types.VARCHAR);
			cstmt1.registerOutParameter(6, Types.VARCHAR);
			cstmt1.registerOutParameter(7, Types.VARCHAR);
			cstmt1.setString(8, reportGeneratorVO.getIsDeptEntry());
			cstmt1.setString(9, reportGeneratorVO.getRequisitionNo());
			cstmt1.execute();
						
			Log(Level.INFO, "String P_GET_PDF_WORKORDERLISTDATA_NEW_PM=pkg_inv_report_generator.getworkorderlistforpdfjobnewpm(..)");
			Log(Level.INFO, cstmt1.getString(5) + cstmt1.getString(6) + cstmt1.getString(7));
			rs1 = (ResultSet) cstmt1.getObject(4);

			workOrderGroupListForresultEntry = new ArrayList<ResultEntryVOGroupByValidatedBy>();
			workOrderGroupListForresultEntry = resultsetProcessingForGroupingWorkOrder(rs1, workOrderGroupListForresultEntry);
			reportGroupByList = workOrderGroupListForresultEntry;

			Log(Level.INFO, "ListReportGroupBy  Size :: " + reportGroupByList.size());

			if (reportGroupByList != null && reportGroupByList.size() != 0) {
				PrintingHelper.createHTMLReportForListOfWorkOrders(reportGroupByList, compulsory, reportGeneratorVO);
			
				boolean flg = PropertiesHelper.getISFtporMongo();
				if (flg) {
					jsonReportPDFReturn = printReportInPdfFormat(reportGroupByList, reportGeneratorVO.getHospitalCode(), conn);
				} else {
					jsonReportPDFReturn = printReportInPdfFormatFTP(reportGroupByList, reportGeneratorVO.getHospitalCode(), conn);
				}
			}

			isSuccess="1";

		} catch (Exception e) {
			isSuccess="0";
			try {
				e.printStackTrace();
				if (conn != null) {
					conn.rollback();
					Log(Level.INFO, "Connection Rollback");
				}
			} catch (SQLException e1) {

				Log(Level.SEVERE, e1);
				e1.printStackTrace();
			}
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));

			jsonResponse.addProperty("errorMsg", sw.toString());
			Log(Level.SEVERE, e);
			// e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (cstmt != null) {
					cstmt.close();
				}
				if (conn != null) {
					conn.commit();
				}

			} catch (SQLException sqex) {
				StringWriter sw = new StringWriter();
				sqex.printStackTrace(new PrintWriter(sw));

				jsonResponse.addProperty("errorMsg", sw.toString());
				Log(Level.SEVERE, sqex);
			}

			jsonResponse.add("reportDtl", jsonReportPDFReturn);
			jsonResponse.addProperty("isSuccess", isSuccess);
		}
		return jsonResponse;
	}

	private JsonObject printReportInPdfFormatFTP(List<ResultEntryVOGroupByValidatedBy> reportGroupByList,
			String hospitalCode, Connection conn) throws SQLException {
		Log(Level.INFO, "Build PDF File Starts Function:printReportInPdfFormatFTP");
		List<ResultEntryVOGroupByValidatedBy> registeredValidatedByList = null;
		JsonArray pdfNameArr = new JsonArray();
		JsonObject jsonReportPDFReturn = new JsonObject();

		CallableStatement cstmt = null;
		for (ResultEntryVOGroupByValidatedBy resBy : reportGroupByList) {
			if (registeredValidatedByList == null) {
				registeredValidatedByList = new ArrayList<ResultEntryVOGroupByValidatedBy>();
			}
			registeredValidatedByList.add(resBy);
		}

		try {

			ResultSet rs1 = null;
			String dnoo = "";
			for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj : registeredValidatedByList) {

				if (resultEntryVOGroupByValidatedByObj.getHeaderheight() == 0.0
						&& resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("1")) {

					String dynamicTemplateID = PGDataHelper.getInstance().getDynamicTemplateStatus(
							resultEntryVOGroupByValidatedByObj.getTestCode(),
							resultEntryVOGroupByValidatedByObj.getHospitalCode(), false,
							resultEntryVOGroupByValidatedByObj.getLaboratoryCode());

					if (dynamicTemplateID != null && !dynamicTemplateID.isEmpty() && !dynamicTemplateID.equals("")
							&& !dynamicTemplateID.equals("-1")) {
						resultEntryVOGroupByValidatedByObj.setHeaderheight(205.0f);
					} else {
						resultEntryVOGroupByValidatedByObj.setHeaderheight(195.0f);
					}

				} else {

					String dynamicTemplateID = PGDataHelper.getInstance().getDynamicTemplateStatus(
							resultEntryVOGroupByValidatedByObj.getTestCode(),
							resultEntryVOGroupByValidatedByObj.getHospitalCode(), false,
							resultEntryVOGroupByValidatedByObj.getLaboratoryCode());

					if (dynamicTemplateID != null && !dynamicTemplateID.isEmpty() && !dynamicTemplateID.equals("")
							&& !dynamicTemplateID.equals("-1")) {
						resultEntryVOGroupByValidatedByObj.setHeaderheight(190.0f);
					} else {
						resultEntryVOGroupByValidatedByObj.setHeaderheight(180.0f);
					}
				}

				if (resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo() != null) {
					if (dnoo.equals(""))
						dnoo = resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo() == null ? ""
								: resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo();
					else
						dnoo += "#" + resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo() == null ? ""
								: "#" + resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo();
				}


				try {
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss.SSSa");
					String formattedDate = sdf.format(date);
					
					String pdfFileName = resultEntryVOGroupByValidatedByObj.getPatCRNo() + "_"
							+ resultEntryVOGroupByValidatedByObj.getLaboratoryCode() + "_" + formattedDate + ".pdf";

					

					InputStream oldFilePdf = null;
					if (resultEntryVOGroupByValidatedByObj.getOldReportUrl() != null
							&& !resultEntryVOGroupByValidatedByObj.getOldReportUrl().equals("-")) {

						boolean flg = PropertiesHelper.getISFtporMongo();

						if (flg) {
							// oldFilePdf=MongoXmlHandler.getInstance().latestFetchFileByte(resultEntryVOGroupByValidatedByObj.getOldReportUrl());

						} else {

							String strPdfName = resultEntryVOGroupByValidatedByObj.getOldReportUrl();

							String crNo = strPdfName.substring(0, 15);
							String year = crNo.substring(5, 7); 
							String insideyear = PGDataHelper.getInsideYear(crNo);
							String count = PGDataHelper.getcount(crNo);

							String ftpfilepath = PropertiesHelper.getFTPConnectionURI() + "/"
									+ resultEntryVOGroupByValidatedByObj.getHospitalCode() + "/" + "20" + year + "/"
									+ insideyear + "/" + count + "/" + resultEntryVOGroupByValidatedByObj.getPatCRNo()
									+ "/" + resultEntryVOGroupByValidatedByObj.getOldReportUrl();
							URL urlftp = null;
							URLConnection urlc = null;

							try {

								Log(Level.INFO, "ftpfilepath::" + ftpfilepath);

								urlftp = new URL(ftpfilepath);
								urlc = urlftp.openConnection();
								oldFilePdf = urlc.getInputStream();

							} catch (Exception ex) {
								Log(Level.INFO, "<!-- CreateRequisitionDirectory -->");
							}

						}

					}

					if (resultEntryVOGroupByValidatedByObj.getIsNablAccritedTest() != null
							&& resultEntryVOGroupByValidatedByObj.getIsNablAccritedTest().equals("1")) {
						// resultEntryVOGroupByValidatedByObj.setFooterHeight("27.0");
						// resultEntryVOGroupByValidatedByObj.setFooterHeight("58.0");
						resultEntryVOGroupByValidatedByObj.setFooterHeight("48.0");

					} else {
						// resultEntryVOGroupByValidatedByObj.setFooterHeight("58.0");
						resultEntryVOGroupByValidatedByObj.setFooterHeight("48.0");
						// resultEntryVOGroupByValidatedByObj.setFooterHeight("33.0");

					}

					 

					if (resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0")) {

						String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,
								resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
								(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
								resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
								resultEntryVOGroupByValidatedByObj);
						resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

					} else {
						if (resultEntryVOGroupByValidatedByObj.getIsdynamicGroupTemplate().equals("0")
								&& resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate().equals("0")) {
							if (resultEntryVOGroupByValidatedByObj.getPrintingType() != null
									&& resultEntryVOGroupByValidatedByObj.getPrintingType().equals("1")) {
								// headerString.append(lineString);
								if (resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate() != null
										&& resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate().equals(1)) {

									if (resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0")) {

										String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,
												resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
												(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
												resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
												resultEntryVOGroupByValidatedByObj);

										resultEntryVOGroupByValidatedByObj
										.setHeaderheight(Float.parseFloat(headerheight));

									} else {
										String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,
												resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
												(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
												resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
												resultEntryVOGroupByValidatedByObj);
										resultEntryVOGroupByValidatedByObj
										.setHeaderheight(Float.parseFloat(headerheight));
									}

								} else {

									if (resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0")) {

										String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,
												resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
												(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
												resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
												resultEntryVOGroupByValidatedByObj);
										resultEntryVOGroupByValidatedByObj
										.setHeaderheight(Float.parseFloat(headerheight));

									} else {

										String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,
												resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
												(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
												resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
												resultEntryVOGroupByValidatedByObj);
										resultEntryVOGroupByValidatedByObj
										.setHeaderheight(Float.parseFloat(headerheight));

									}
								}

							} else {
								if (resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0")) {

									String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,
											resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
											(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
											resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
											resultEntryVOGroupByValidatedByObj);
									resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

								} else {

									String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,
											resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
											(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
											resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
											resultEntryVOGroupByValidatedByObj);
									resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

								}
								
							}
						} else {
							if (resultEntryVOGroupByValidatedByObj.getPrintingType() != null
									&& resultEntryVOGroupByValidatedByObj.getPrintingType().equals("1")) {
								// headerString.append(lineString);
								if (resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0")) {

									String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,
											resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
											(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
											resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
											resultEntryVOGroupByValidatedByObj);
									resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

								} else {

									String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,
											resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
											(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
											resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
											resultEntryVOGroupByValidatedByObj);
									resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

								}
								
							} else {
								if (resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0")) {

									String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,
											resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
											(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
											resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
											resultEntryVOGroupByValidatedByObj);
									resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

								} else {
									String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,
											resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
											(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
											resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
											resultEntryVOGroupByValidatedByObj);
									resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

								}
								
							}

						}
					}

					String crNo = resultEntryVOGroupByValidatedByObj.getPatCRNo();
					String year = crNo.substring(5, 7);

					String insideyear = PGDataHelper.getInsideYear(crNo);
					String count = PGDataHelper.getcount(crNo);
					String pdfFtpurl = PropertiesHelper.getFTPConnectionURI() + "/"
							+ resultEntryVOGroupByValidatedByObj.getHospitalCode() + "/" + "20" + year + "/"
							+ insideyear + "/" + count + "/" + resultEntryVOGroupByValidatedByObj.getPatCRNo();
					String pdfFileurl = PropertiesHelper.getFTPConnectionURI() + "/"
							+ resultEntryVOGroupByValidatedByObj.getHospitalCode() + "/" + "20" + year + "/"
							+ insideyear + "/" + count + "/" + resultEntryVOGroupByValidatedByObj.getPatCRNo();
					Log(Level.INFO, "pdfFileurl::  " + pdfFileurl);

					boolean flg = PropertiesHelper.getISFtporMongo();

					if (flg) {
						// saveStatus =
						// SavePDF.saveFileToLocation(resultEntryVOGroupByValidatedByObj.getHeader(),
						// resultEntryVOGroupByValidatedByObj.getFooter(),
						// resultEntryVOGroupByValidatedByObj.getGroupTemplateString(), pdfFileName,
						// Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterHeight()),
						// resultEntryVOGroupByValidatedByObj.getHeaderheight(),
						// Float.parseFloat(resultEntryVOGroupByValidatedByObj.getHeaderWidth()),
						// Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterWidth()),
						// ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null :
						// resultEntryVOGroupByValidatedByObj.getPageSize()),
						// ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null :
						// Config.pagewidthprinting),
						// oldFilePdf,resultEntryVOGroupByValidatedByObj.getFileuploaddata(),resultEntryVOGroupByValidatedByObj.getIsfileuploaddadd(),resultEntryVOGroupByValidatedByObj.getIsfirstpagereq());
					} else {

						SavePDF.saveFileToFTPLocationnew(resultEntryVOGroupByValidatedByObj.getHeader(),
								resultEntryVOGroupByValidatedByObj.getFooter(),
								resultEntryVOGroupByValidatedByObj.getGroupTemplateString(), pdfFileName,
								Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterHeight()),
								resultEntryVOGroupByValidatedByObj.getHeaderheight(),
								Float.parseFloat(resultEntryVOGroupByValidatedByObj.getHeaderWidth()),
								Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterWidth()),
								((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null
										: resultEntryVOGroupByValidatedByObj.getPageSize()),
								((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null
										: Config.pagewidthprinting),
								oldFilePdf, resultEntryVOGroupByValidatedByObj.getFileuploaddata(),
								resultEntryVOGroupByValidatedByObj.getIsfileuploaddadd(),
								resultEntryVOGroupByValidatedByObj.getIsfirstpagereq(), pdfFtpurl, pdfFileurl,
								resultEntryVOGroupByValidatedByObj);
					}

				
					resultEntryVOGroupByValidatedByObj.setPdfFileName(pdfFileName);

					String strPdfPath = PropertiesHelper.getFTPConnectionURI() + "/" + pdfFileName.substring(0, 5) + "/"
							+ "20" + year + "/" + insideyear + "/" + count + "/" + pdfFileName.substring(0, 15) + "/"
							+ pdfFileName;

					URL urlftp1 = new URL(strPdfPath);
					urlftp1.openConnection();

					ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
					InputStream is = null;
					try {
						is = urlftp1.openStream();
						byte[] byteChunk = new byte[4897]; // Or whatever size you want to read in at a time.
						int n;

						while ((n = is.read(byteChunk)) > 0) {
							baos1.write(byteChunk, 0, n);
						}
					} catch (IOException e) {
						System.err.printf("Failed while reading bytes from %s: %s", urlftp1.toExternalForm(),
								e.getMessage());
						e.printStackTrace();
						// Perform any other exception handling that's appropriate.
					} finally {
						if (is != null) {
							is.close();
						}
					}

					byte[] pdfData1 = baos1.toByteArray();

					org.apache.commons.codec.binary.Base64.encodeBase64String(pdfData1);

					List<String> patDetails = PGDataHelper.getInstance()
							.getPatientDetails(resultEntryVOGroupByValidatedByObj.getPatCRNo());
					
					patDetails.get(0);
					patDetails.get(3);

					JsonPrimitive pdfFileNameElm = new JsonPrimitive(pdfFileName);
					pdfNameArr.add(pdfFileNameElm);

					Log(Level.INFO, "PDF File Saved " + pdfFileName);

				} catch (Exception ex) {
					Log(Level.SEVERE, ex);
					if (conn != null) {
						conn.rollback();
					}

				} finally {
					try {

						if (cstmt != null) {
							cstmt.close();
						}

						if (rs1 != null) {
							rs1.close();
						}

					} catch (SQLException sqex) {
						Log(Level.SEVERE, sqex);
					}
				}

			}

			jsonReportPDFReturn.add("pdfNameArr", pdfNameArr);
		} catch (Exception e) {
			Log(Level.SEVERE, e);
			if (conn != null)
				conn.rollback();
		} finally {
			if (conn != null) {
				conn.commit();
			}
		}

		return jsonReportPDFReturn;
	}

	private List<ResultEntryVOGroupByValidatedBy> resultsetProcessingForGroupingWorkOrder(ResultSet rs,
			List<ResultEntryVOGroupByValidatedBy> workOrderGroupListForresultEntry) {
		ResultEntryVO resultEntryVO = null;
		Map<String, ResultEntryVOGroupByValidatedBy> checkMap = new HashMap<String, ResultEntryVOGroupByValidatedBy>();
		ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy = null;
		try {
			int count = 0;
			String data = getreporttestforpagesepareation();
			while (rs.next()) {
				{
					if (resultEntryVO == null) {
						Log(Level.INFO,
						" laboratoryCode::" + rs.getString("laboratorycode") + ",groupCode::"+ rs.getString("groupcode") 
						+ ",testCode::" + rs.getString("testcode") + ",hospitalCode" + rs.getString("hospitalcode") 
						+ ",samplecode" + rs.getString("samplecode"));

						resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"),
								rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
						
						DataHandler.populateVOfrmRS(resultEntryVO, rs);

						resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
						DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
						if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
							resultEntryVOGroupByValidatedBy
							.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
						}
												
						resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);
						workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);

						if (data != null && !data.equals("") && data.contains(resultEntryVO.getTestCode()))
							checkMap.put(
									resultEntryVO.getRequisitionNo()
									+ resultEntryVOGroupByValidatedBy.getLaboratoryCode() + count,
									resultEntryVOGroupByValidatedBy);
						else
							checkMap.put(resultEntryVO.getRequisitionNo()
									+ resultEntryVOGroupByValidatedBy.getLaboratoryCode()
									+ resultEntryVO.getSampleCode(), resultEntryVOGroupByValidatedBy);
						count++;
					} else {
						if (((resultEntryVO.getRequisitionNo()).equals(rs.getString("requisitionNo")) == true
								&& resultEntryVO.getSampleCode().equals(rs.getString("samplecode")) == true)) {

							Log(Level.INFO,
									" laboratoryCode::" + rs.getString("laboratorycode") + ",groupCode::"+ rs.getString("groupcode") 
									+ ",testCode::" + rs.getString("testcode") + ",hospitalCode" + rs.getString("hospitalcode") 
									+ ",samplecode" + rs.getString("samplecode"));
							
							resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"),
									rs.getString("groupcode"), rs.getString("hospitalcode"),
									rs.getString("samplecode"));
							DataHandler.populateVOfrmRS(resultEntryVO, rs);

							if (data != null && !data.equals("") && data.contains(resultEntryVO.getTestCode()))

							{

								if (checkMap.containsKey(
										resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode())) {
									resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode());

								} else {

									resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
									DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
									workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
									checkMap.put(
											resultEntryVOGroupByValidatedBy.getRequisitionNo()
											+ resultEntryVOGroupByValidatedBy.getLaboratoryCode() + count,
											resultEntryVOGroupByValidatedBy);

								}
							} else if (checkMap.containsKey(resultEntryVO.getRequisitionNo()
									+ resultEntryVO.getLaboratoryCode() + resultEntryVO.getSampleCode())) {

								resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo()
										+ resultEntryVO.getLaboratoryCode() + resultEntryVO.getSampleCode());

								if (resultEntryVO.getIsfirstpagereq() != null
										&& !resultEntryVO.getIsfirstpagereq().equals("1")) {

									if (resultEntryVO.getIsfileuploaddadd() != null
											&& resultEntryVO.getIsfileuploaddadd().equals("1")) {
										resultEntryVOGroupByValidatedBy
										.setIsfileuploaddadd(resultEntryVO.getIsfileuploaddadd());
									}

									if (resultEntryVO.getFileuploaddata() != null
											&& !resultEntryVO.getFileuploaddata().equals("")) {
										resultEntryVOGroupByValidatedBy
										.setFileuploaddata(resultEntryVOGroupByValidatedBy.getFileuploaddata()
												+ resultEntryVO.getFileuploaddata());
									}

								}

							} else {
								resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
								
								DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
								workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
								checkMap.put(
										resultEntryVOGroupByValidatedBy.getRequisitionNo()
										+ resultEntryVOGroupByValidatedBy.getLaboratoryCode()
										+ resultEntryVOGroupByValidatedBy.getSampleCode(),
										resultEntryVOGroupByValidatedBy);
							}

							if (resultEntryVOGroupByValidatedBy != null)
								if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
									resultEntryVOGroupByValidatedBy
									.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
								}							
							resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);

						} else {
							Log(Level.INFO,
									" laboratoryCode::" + rs.getString("laboratorycode") + ",groupCode::"+ rs.getString("groupcode") 
									+ ",testCode::" + rs.getString("testcode") + ",hospitalCode" + rs.getString("hospitalcode") 
									+ ",samplecode" + rs.getString("samplecode"));
							
							
							resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"),
									rs.getString("groupcode"), rs.getString("hospitalcode"),
									rs.getString("samplecode"));
							DataHandler.populateVOfrmRS(resultEntryVO, rs);

							if (data != null && !data.equals("") && data.contains(resultEntryVO.getTestCode())) {

								if (checkMap.containsKey(
										resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode())) {
									resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo()
											+ resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode() + count);

								} else {
									resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
									DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
									workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
									checkMap.put(
											resultEntryVOGroupByValidatedBy.getRequisitionNo()
											+ resultEntryVOGroupByValidatedBy.getLaboratoryCode() + count,
											resultEntryVOGroupByValidatedBy);
								}
							} else if (checkMap.containsKey(resultEntryVO.getRequisitionNo()
									+ resultEntryVO.getLaboratoryCode() + resultEntryVO.getSampleCode())) {
								resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo()
										+ resultEntryVO.getLaboratoryCode() + resultEntryVO.getSampleCode());

							} else {
								resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
								DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
								workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
								checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo()
										+ resultEntryVOGroupByValidatedBy.getLaboratoryCode()
										+ resultEntryVO.getSampleCode(), resultEntryVOGroupByValidatedBy);
							}

							if (resultEntryVOGroupByValidatedBy != null)
								if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
									resultEntryVOGroupByValidatedBy
									.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
								}

							if (resultEntryVOGroupByValidatedBy != null) {					
								resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);
							}
						}

					}

				}
			}

		} catch (Exception e) {
			Log(Level.SEVERE, e);
		}
		return workOrderGroupListForresultEntry;
	}

	private void Log(Level level, String msg) {
		ServiceLogger.Log(PDFProcessing.class.getName(), level, msg);
	}

	private void Log(Level level, Exception e) {
		ServiceLogger.Log(PDFProcessing.class.getName(), level, e);
	}

	private static String getreporttestforpagesepareation() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		
		String query = QueryConfig.SELECT_TEST_REPORT_SEPERATION;
		String data = "";

		PGDataHelper.getInstance();
		Connection conn = PGDataHelper.getConnection();
		String filename = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				filename = rs.getString(1);
				data = filename;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	private static String getfinalheaderheight(String headerheight, int islabeldoubleprint, int headerheightt,
			String ipd, ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj) {

		String finalval = "";

		headerheight = String.valueOf(headerheightt);

		finalval = headerheight;

		if (islabeldoubleprint == 0) {

			if (((resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")
					|| resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")))) // 10010,10010
			{

			} else {
				if (ipd != null && ipd.equals("2")) {
					Float ff = Float.parseFloat(headerheight);

					ff = ff + 15f;

					finalval = ff.toString();

				} else {
					
				}
			}

		} else {
			Float ff = Float.parseFloat(headerheight);
			for (int k = 1; k <= islabeldoubleprint; k++) {

				if (ipd != null && ipd.equals("2")) {

					if (((resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")
							|| resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")))) // 10010,10010
					{
						if (islabeldoubleprint == 2)
							ff += 6;

						if (islabeldoubleprint == 4)
							ff += 6;

						if (islabeldoubleprint == 6)
							ff += 6;
					} else {
						if (islabeldoubleprint == 2)
							ff += 14;

						if (islabeldoubleprint == 4)
							ff += 12;

						if (islabeldoubleprint == 6)
							ff += 10;
					}

				} else {

					if (((resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")
							|| resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")))) // 10010,10010
					{

						if (islabeldoubleprint == 2)
							ff += 6;

						if (islabeldoubleprint == 4)
							ff += 6;

						if (islabeldoubleprint == 6)
							ff += 6;

					} else {
						if (islabeldoubleprint == 2)
							ff += 10;

						if (islabeldoubleprint == 4)
							ff += 10;

						if (islabeldoubleprint == 6)
							ff += 11;
					}

				}

				finalval = ff.toString();
			}

			if (ipd != null && ipd.equals("2")) {

				if (((resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")
						|| resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")))) // 10010,10010
				{
					if (resultEntryVOGroupByValidatedByObj.getLabNo() != null
							&& resultEntryVOGroupByValidatedByObj.getLabNo() != null
							&& resultEntryVOGroupByValidatedByObj.getLabNo().length() > 15) {
						Float fvall = Float.parseFloat(finalval);

						fvall = fvall + 4.0f;
						finalval = fvall.toString();

					}

				} else {
					if (resultEntryVOGroupByValidatedByObj.getLabNo() != null
							&& resultEntryVOGroupByValidatedByObj.getLabNo() != null
							&& resultEntryVOGroupByValidatedByObj.getLabNo().length() > 16) {
						Float fvall = Float.parseFloat(finalval);

						fvall = fvall + 10.0f;
						finalval = fvall.toString();

					}
				}

			} else {

				if (((resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")
						|| resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")))) // 10010,10010
				{
					if (resultEntryVOGroupByValidatedByObj.getLabNo() != null
							&& resultEntryVOGroupByValidatedByObj.getLabNo() != null
							&& resultEntryVOGroupByValidatedByObj.getLabNo().length() > 15) {
						Float fvall = Float.parseFloat(finalval);

						fvall = fvall + 4.0f;
						finalval = fvall.toString();

					}

				} else if (resultEntryVOGroupByValidatedByObj.getLabNo() != null
						&& resultEntryVOGroupByValidatedByObj.getLabNo() != null
						&& resultEntryVOGroupByValidatedByObj.getLabNo().length() > 13) {
					Float fvall = Float.parseFloat(finalval);

					fvall = fvall + 4.0f;
					finalval = fvall.toString();

				}

			}

			if (ipd != null && ipd.equals("2")) // access no
			{

				if (resultEntryVOGroupByValidatedByObj.getIsreportlablabelchanged() == null
						|| resultEntryVOGroupByValidatedByObj.getIsreportlablabelchanged().equals("0"))

				{
				} else {

					if (((resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")
							|| resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")))) // 10010,10010
					{
						Float fvall = Float.parseFloat(finalval);

						if (islabeldoubleprint == 4)
							fvall = fvall - 20.0f;

						if (islabeldoubleprint == 6)
							fvall = fvall - 10.0f;

						finalval = fvall.toString();
					}

				}

			}

		}

		if (PropertiesHelper.getReportXSL_latest()) {
			Float ff = Float.parseFloat(finalval);
			ff = ff + 10.0f;
			finalval = ff.toString();
		}

		return finalval;
	}

	private JsonObject printReportInPdfFormat(List<ResultEntryVOGroupByValidatedBy> reportGroupByList,
			String hospitalCode, Connection conn) throws SQLException {
		List<ResultEntryVOGroupByValidatedBy> registeredValidatedByList = null;
		JsonArray pdfNameArr = new JsonArray();
		JsonObject jsonReportPDFReturn = new JsonObject();

		CallableStatement cstmt = null;
		for (ResultEntryVOGroupByValidatedBy resBy : reportGroupByList) {

			Log(Level.INFO, "counterrrrrrrrr::" + reportGroupByList.size());

			if (registeredValidatedByList == null) {
				registeredValidatedByList = new ArrayList<ResultEntryVOGroupByValidatedBy>();
			}

			registeredValidatedByList.add(resBy);
			
		}

		try {

			ResultSet rs1 = null;
			String dnoo = "";
			for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj : registeredValidatedByList) {

				if (resultEntryVOGroupByValidatedByObj.getTestCode().equals("12815")) {

					Log(Level.INFO, "---------------------------------");
				}

				if (resultEntryVOGroupByValidatedByObj.getHeaderheight() == 0.0
						&& resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("1")) {

					String dynamicTemplateID = PGDataHelper.getInstance().getDynamicTemplateStatus(
							resultEntryVOGroupByValidatedByObj.getTestCode(),
							resultEntryVOGroupByValidatedByObj.getHospitalCode(), false,
							resultEntryVOGroupByValidatedByObj.getLaboratoryCode());

					if (dynamicTemplateID != null && !dynamicTemplateID.isEmpty() && !dynamicTemplateID.equals("")
							&& !dynamicTemplateID.equals("-1")) {
						resultEntryVOGroupByValidatedByObj.setHeaderheight(220.0f);
					} else {
						resultEntryVOGroupByValidatedByObj.setHeaderheight(210.0f);
					}

				} else {

					String dynamicTemplateID = PGDataHelper.getInstance().getDynamicTemplateStatus(
							resultEntryVOGroupByValidatedByObj.getTestCode(),
							resultEntryVOGroupByValidatedByObj.getHospitalCode(), false,
							resultEntryVOGroupByValidatedByObj.getLaboratoryCode());

					if (dynamicTemplateID != null && !dynamicTemplateID.isEmpty() && !dynamicTemplateID.equals("")
							&& !dynamicTemplateID.equals("-1")) {
						resultEntryVOGroupByValidatedByObj.setHeaderheight(200.0f);
					} else {
						resultEntryVOGroupByValidatedByObj.setHeaderheight(190.0f);
					}
				}

				if (resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo() != null) {
					Log(Level.INFO, "22");
					if (dnoo.equals(""))
						dnoo = resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo() == null ? ""
								: resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo();
					else
						dnoo += "#" + resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo() == null ? ""
								: "#" + resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo();
				}

				
				try {
					Log(Level.INFO, "resultEntryVOGroupByValidatedByObj.getPatCRNo()::"
							+ resultEntryVOGroupByValidatedByObj.getPatCRNo() + "::hospitalCode::" + hospitalCode);

					
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss.SSSa");
					String formattedDate = sdf.format(date);
					
					String pdfFileName = resultEntryVOGroupByValidatedByObj.getPatCRNo() + "_"
							+ resultEntryVOGroupByValidatedByObj.getLaboratoryCode() + "_" + formattedDate + ".pdf";

					

					byte[] oldFilePdf = null;
					if (resultEntryVOGroupByValidatedByObj.getOldReportUrl() != null
							&& !resultEntryVOGroupByValidatedByObj.getOldReportUrl().equals("-"))
						oldFilePdf = MongoXmlHandler.getInstance()
						.latestFetchFileByte(resultEntryVOGroupByValidatedByObj.getOldReportUrl());

					Log(Level.INFO, "ashuchk-1");

					if (resultEntryVOGroupByValidatedByObj.getIsNablAccritedTest() != null
							&& resultEntryVOGroupByValidatedByObj.getIsNablAccritedTest().equals("1")) {
						// resultEntryVOGroupByValidatedByObj.setFooterHeight("27.0");
						// resultEntryVOGroupByValidatedByObj.setFooterHeight("58.0");
						resultEntryVOGroupByValidatedByObj.setFooterHeight("48.0");

					} else {
						// Log(Level.INFO, "ashuchk-1");
						// resultEntryVOGroupByValidatedByObj.setFooterHeight("58.0");
						resultEntryVOGroupByValidatedByObj.setFooterHeight("48.0");
						// resultEntryVOGroupByValidatedByObj.setFooterHeight("33.0");

					}

					if (resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0")) {

						String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,
								resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
								(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
								resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
								resultEntryVOGroupByValidatedByObj);
						resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

					} else {
						if (resultEntryVOGroupByValidatedByObj.getIsdynamicGroupTemplate().equals("0")
								&& resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate().equals("0")) {
							if (resultEntryVOGroupByValidatedByObj.getPrintingType() != null
									&& resultEntryVOGroupByValidatedByObj.getPrintingType().equals("1")) {
								// headerString.append(lineString);
								if (resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate() != null
										&& resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate().equals(1)) {

									if (resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0")) {

										String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,
												resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
												(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
												resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
												resultEntryVOGroupByValidatedByObj);

										resultEntryVOGroupByValidatedByObj
										.setHeaderheight(Float.parseFloat(headerheight));

									} else {
										String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,
												resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
												(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
												resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
												resultEntryVOGroupByValidatedByObj);
										resultEntryVOGroupByValidatedByObj
										.setHeaderheight(Float.parseFloat(headerheight));
									}

								} else {

									if (resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0")) {

										String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,
												resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
												(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
												resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
												resultEntryVOGroupByValidatedByObj);
										resultEntryVOGroupByValidatedByObj
										.setHeaderheight(Float.parseFloat(headerheight));

									} else {

										String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,
												resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
												(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
												resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
												resultEntryVOGroupByValidatedByObj);
										resultEntryVOGroupByValidatedByObj
										.setHeaderheight(Float.parseFloat(headerheight));

									}
								}

								
							} else {
								if (resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0")) {

									String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,
											resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
											(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
											resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
											resultEntryVOGroupByValidatedByObj);
									resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));
								} else {

									String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,
											resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
											(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
											resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
											resultEntryVOGroupByValidatedByObj);
									resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

								}
							}
						} else {
							if (resultEntryVOGroupByValidatedByObj.getPrintingType() != null
									&& resultEntryVOGroupByValidatedByObj.getPrintingType().equals("1")) {
								if (resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0")) {

									String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,
											resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
											(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
											resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
											resultEntryVOGroupByValidatedByObj);
									resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

								} else {

									String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,
											resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
											(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
											resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
											resultEntryVOGroupByValidatedByObj);
									resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

								}
								
							} else {
								if (resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0")) {

									String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,
											resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
											(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
											resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
											resultEntryVOGroupByValidatedByObj);
									resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));
								} else {
									String headerheight = getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,
											resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),
											(int) resultEntryVOGroupByValidatedByObj.getHeaderheight(),
											resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),
											resultEntryVOGroupByValidatedByObj);
									resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

								}
								
							}

						}
					}

					SavePDF.saveFileToLocation(resultEntryVOGroupByValidatedByObj.getHeader(),
							resultEntryVOGroupByValidatedByObj.getFooter(),
							resultEntryVOGroupByValidatedByObj.getGroupTemplateString(), pdfFileName,
							Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterHeight()),
							resultEntryVOGroupByValidatedByObj.getHeaderheight(),
							Float.parseFloat(resultEntryVOGroupByValidatedByObj.getHeaderWidth()),
							Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterWidth()),
							((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null
									: resultEntryVOGroupByValidatedByObj.getPageSize()),
							((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null
									: Config.pagewidthprinting),
							oldFilePdf, resultEntryVOGroupByValidatedByObj.getFileuploaddata(),
							resultEntryVOGroupByValidatedByObj.getIsfileuploaddadd(),
							resultEntryVOGroupByValidatedByObj.getIsfirstpagereq(), resultEntryVOGroupByValidatedByObj);
					
					resultEntryVOGroupByValidatedByObj.setPdfFileName(pdfFileName);

					byte[] pdfnew = null;

					pdfnew = MongoXmlHandler.getInstance().latestFetchFileByte(pdfFileName);
					org.apache.commons.codec.binary.Base64.encodeBase64String(pdfnew);

					List<String> patDetails = PGDataHelper.getInstance()
							.getPatientDetails(resultEntryVOGroupByValidatedByObj.getPatCRNo());
					
					patDetails.get(0);
					patDetails.get(3);

					JsonPrimitive pdfFileNameElm = new JsonPrimitive(pdfFileName);
					pdfNameArr.add(pdfFileNameElm);

					Log(Level.INFO, "PDF File Saved " + pdfFileName);

					// encryptedPdfFile(pdfFileurl, pdfFileName, "administrator");
				} catch (Exception ex) {
					// ex.printStackTrace();
					Log(Level.SEVERE, ex);
					if (conn != null) {
						conn.rollback();
					}

					// ex.printStackTrace();
				} finally {
					try {

						if (cstmt != null) {
							cstmt.close();
						}

						if (rs1 != null) {
							rs1.close();
						}

					} catch (SQLException sqex) {
						// sqex.printStackTrace();
						Log(Level.SEVERE, sqex);
					}
				}

			}

			
			jsonReportPDFReturn.add("pdfNameArr", pdfNameArr);

		} catch (Exception e) {
			// e.printStackTrace();
			Log(Level.SEVERE, e);
			if (conn != null)
				conn.rollback();
		} finally {
			if (conn != null) {
				conn.commit();
				// conn.close();
			}
		}

		return jsonReportPDFReturn;
	}

}
