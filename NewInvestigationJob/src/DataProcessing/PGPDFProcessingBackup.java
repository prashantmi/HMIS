/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import DataHelper.Config;
import DataHelper.PGDataHelper;
import DataHelper.QueryConfig;
import Logging.ServiceLogger;
import MongoHelper.MongoXmlHandler;
import TemplateHelper.TriStringObject;
import TemplateHelper.vo.ResultEntryVO;
import TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;

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

import org.w3c.dom.Document;

/**
 * *************************Start of program***************************** ##
 * Copyright Information	: C-DAC, Noida ## Project Name	: CCD SDK ## Name of
 * Developer	: Siddharth Srivastava ## Module Name	: Health Standards ##
 * Process/Database Object Name	: ## Purpose : ## Date of Creation	: ##
 * Modification Log	: ##	Modify Date	: ##	Reason	(CR/PRS)	: ##	Modify By	:
 */
/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class PGPDFProcessingBackup {

    public boolean processingData() {
        boolean jobprocessContinue = false;

        String processingData = null;
        String processinglaboratoryCode = null;
        String hospitalCode = null;
        Connection conn = null;
        Log(Level.INFO, "Beginning PDF Generation");
        CallableStatement cstmt = null;
        try {
            conn = PGDataHelper.getInstance().getConnection();
            if (conn == null) {
                conn = PGDataHelper.getInstance().createPostgresConnection();//("jdbc:edb://10.0.2.103:5444/phdm", "phdm", "phdm");  // DriverManager.getConnection(Conn.getDatabaseConnection(), InvestigationStaticConfigurator.databaseusername,InvestigationStaticConfigurator.databasepassword);
            }
            String query = QueryConfig.P_GET_PDF_WORKORDERLIST;
            // conn.setAutoCommit(false);
            cstmt = conn.prepareCall(query);

            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.registerOutParameter(3, Types.VARCHAR);
          
            if(conn==null || conn.isClosed())
            {
          	  
          	
            }
            else
            {
            cstmt.execute();
            processingData = cstmt.getString(1);
            processinglaboratoryCode = cstmt.getString(2);
            hospitalCode = cstmt.getString(3);

            if (cstmt != null) {
                cstmt.close();
                //conn.commit();
                //  conn.setAutoCommit(true);
            }

            if (processingData != null) {
                jobprocessContinue = true;

                processingToGenerateReport(conn, hospitalCode, processinglaboratoryCode, processingData, false);
                /*cstmt = conn.prepareCall("{call inv_reportengine.removeSingleDataforjob(?,?,?,?)}");
                 cstmt.setString(1, processinglaboratoryCode);
                 cstmt.setString(2, InvestigationStaticConfigurator.servernumber);
                 cstmt.setString(3, processingData);
                 cstmt.setString(4, hospitalCode);
                 cstmt.execute();*/
            } else {
                jobprocessContinue = false;
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jobprocessContinue = false;
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.commit();
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        Log(Level.INFO, "Finishing PDF Generation");
        return jobprocessContinue;
    }

    public void processingToGenerateReport(Connection conn, String hospitalCode, String labCode, String crOrRequisitionNo, boolean compulsory) {
        List<ResultEntryVOGroupByValidatedBy> workOrderGroupListForresultEntry = new ArrayList<ResultEntryVOGroupByValidatedBy>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            //conn.setAutoCommit(false);
            cstmt = conn.prepareCall(QueryConfig.P_GET_PDF_WORKORDERLISTDATA);
            cstmt.setString(1, labCode);
            cstmt.setString(2, crOrRequisitionNo);
            cstmt.setString(3, hospitalCode);
            cstmt.registerOutParameter(4, Types.REF);
            cstmt.registerOutParameter(5, Types.VARCHAR);
            cstmt.registerOutParameter(6, Types.VARCHAR);
            cstmt.registerOutParameter(7, Types.VARCHAR);
            cstmt.execute();
            Log(Level.INFO, "processingToGenerateReport CR Nos::" + crOrRequisitionNo);
            Log(Level.INFO, "processingToGenerateReport REPORTING ENGINGE QUERY-->INV_REPORTENGINE.getWorkOrderList::" + cstmt.getString(5) + cstmt.getString(6) + cstmt.getString(7));
            rs = (ResultSet) cstmt.getObject(4);
            workOrderGroupListForresultEntry = resultsetProcessingForGroupingWorkOrder(rs, workOrderGroupListForresultEntry);

            Log(Level.INFO, "processingToGenerateReport workOrderListForresultEntry  size ::" + workOrderGroupListForresultEntry.size());
            List<ResultEntryVOGroupByValidatedBy> reportGroupByList = processTheWorkOrdersToGroups(workOrderGroupListForresultEntry);
            Log(Level.INFO, "processingToGenerateReport After reportGroupByList  size ::" + reportGroupByList.size());

            if (reportGroupByList != null && reportGroupByList.size() > 0) {
                for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy2 : reportGroupByList) {
                    Map<String, String> sampleNameMap = new HashMap<String, String>();

                    if (resultEntryVOGroupByValidatedBy2.getResultEntryVOListValidatedBy() != null) {
                        for (ResultEntryVO resultEntryVO2 : resultEntryVOGroupByValidatedBy2.getResultEntryVOListValidatedBy()) {

                            if (resultEntryVO2.getSampleName() != null && sampleNameMap.containsKey(resultEntryVO2.getSampleName()) == false) {
                                if (resultEntryVOGroupByValidatedBy2.getSampleNameList() == null) {
                                    resultEntryVOGroupByValidatedBy2.setSampleNameList(new ArrayList<String>());
                                }

                                resultEntryVOGroupByValidatedBy2.getSampleNameList().add(resultEntryVO2.getSampleName());

                                sampleNameMap.put(resultEntryVO2.getSampleName(), resultEntryVO2.getSampleName());

                            }
                        }
                    }
                }

                for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy2 : reportGroupByList) {
                    Map<String, String> labNoMap = new HashMap<String, String>();

                    if (resultEntryVOGroupByValidatedBy2.getResultEntryVOListValidatedBy() != null) {
                        for (ResultEntryVO resultEntryVO2 : resultEntryVOGroupByValidatedBy2.getResultEntryVOListValidatedBy()) {

                            if (resultEntryVO2.getLabNo() != null && labNoMap.containsKey(resultEntryVO2.getLabNo()) == false) {
                                if (resultEntryVOGroupByValidatedBy2.getLabNoList() == null) {
                                    resultEntryVOGroupByValidatedBy2.setLabNoList(new ArrayList<String>());
                                }

                                resultEntryVOGroupByValidatedBy2.getLabNoList().add(resultEntryVO2.getLabNo());

                                labNoMap.put(resultEntryVO2.getLabNo(), resultEntryVO2.getLabNo());

                            }
                        }
                    }
                }

            }

            if (reportGroupByList != null && reportGroupByList.size() != 0) {
                Log(Level.INFO, "processingToGenerateReport reportGroupByList:::" + reportGroupByList.size());
                for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy : reportGroupByList) {
                    if (resultEntryVOGroupByValidatedBy.getPrintedWithTemplateID() != null && resultEntryVOGroupByValidatedBy.getPrintedWithTemplateID().equals("0") == false) {
                        Log(Level.INFO, "<!----------------------------PrintingTemplate Id Found---------------------->" + resultEntryVOGroupByValidatedBy.getPrintedWithTemplateID());
                        Log(Level.INFO, "ReporintEngineDAO::processingToGenerateReport " + resultEntryVOGroupByValidatedBy.getGroupMap());
                        PrintingHelper.populatePrintingDocumentWithWorkOrderDocument(resultEntryVOGroupByValidatedBy);

                    }
                }

                if (reportGroupByList != null && reportGroupByList.size() != 0) {
                    PrintingHelper.createHTMLReportForListOfWorkOrders(reportGroupByList, compulsory);
                }

                Log(Level.INFO, "processingToGenerateReport reportGroupByList  size ::" + reportGroupByList);
                printReportInPdfFormat(reportGroupByList, hospitalCode, conn);
            }

        } catch (Exception e) {
            try {
                if (cstmt != null) {
                    Log(Level.INFO, cstmt.getString(5) + cstmt.getString(6) + cstmt.getString(7));
                }
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (cstmt != null) {
                    cstmt.close();
                }

            } catch (SQLException sqex) {
                sqex.printStackTrace();
            }

        }

    }

    public boolean printReportInPdfFormat(List<ResultEntryVOGroupByValidatedBy> reportGroupByList, String hospitalCode, Connection conn) throws SQLException {
        List<ResultEntryVOGroupByValidatedBy> registeredValidatedByList = null;
        List<ResultEntryVOGroupByValidatedBy> unRegisteredValidatedByList = null;

        boolean flag = false;
        CallableStatement cstmt = null;
        for (ResultEntryVOGroupByValidatedBy resBy : reportGroupByList) {
            /*   if (resBy.getRequisitionTypeCode().equals("3") || resBy.getRequisitionTypeCode().equals("4")) {
             if (unRegisteredValidatedByList == null) {
             unRegisteredValidatedByList = new ArrayList<ResultEntryVOGroupByValidatedBy>();
             }

             unRegisteredValidatedByList.add(resBy);
             } else {*/
            if (registeredValidatedByList == null) {
                registeredValidatedByList = new ArrayList<ResultEntryVOGroupByValidatedBy>();
            }

            registeredValidatedByList.add(resBy);
            //}
        }

        try {

            // if ((registeredValidatedByList != null) && (registeredValidatedByList.size() > 0)) {
            Object[] workOrderCollectionAttributes = null;

            for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj : registeredValidatedByList) {
                //   StructDescriptor workOrderSD = StructDescriptor.createDescriptor("INVPDFWORKORDEROBJ", conn);
                //   ArrayDescriptor workOrderCollectionAD = ArrayDescriptor.createDescriptor("INVPDFWORKORDERCOLLECTION", conn);
                //    ArrayDescriptor workOrderAD = ArrayDescriptor.createDescriptor("ARRAY_STRING", conn);
                try {
                    workOrderCollectionAttributes = new Object[1];
                    int workOrderCollectionADIndex = 0;
                    /*cstmt = conn.prepareCall(QueryConfig.P_GET_PDF_UNIQUE_CODE);
                     conn.setAutoCommit(false);
                     cstmt.setString(1, resultEntryVOGroupByValidatedByObj.getPatCRNo());
                     cstmt.setString(2, null);
                     cstmt.setString(3, hospitalCode);
                     cstmt.registerOutParameter(4, Types.VARCHAR);
                     cstmt.registerOutParameter(5, Types.VARCHAR);
                     cstmt.registerOutParameter(6, Types.VARCHAR);
                     //cstmt.setString(7, "REGISTER");
                     cstmt.execute();
                     String pdfFileurl = null;
                     String pdfFtpurl = null;
                     String maxtransactionno = cstmt.getString(4);*/
                    Log(Level.INFO, "resultEntryVOGroupByValidatedByObj.getPatCRNo()::" + resultEntryVOGroupByValidatedByObj.getPatCRNo() + "::hospitalCode::" + hospitalCode);

                    /* if (hisAppletConfigurator.getBackupsystemenabled() != null && hisAppletConfigurator.getBackupsystemenabled().equals("enabled")) {
                     //
                     if (InvestigationStaticConfigurator.backupsystemtype.equals("linux")) {
                     pdfFileurl = "file://" + hisAppletConfigurator.getBackupsystempdffolderpath() + "/" + resultEntryVOGroupByValidatedByObj.getPatCRNo();
                     pdfFtpurl = "file://" + hisAppletConfigurator.getBackupsystempdffolderpath() + "/" + resultEntryVOGroupByValidatedByObj.getPatCRNo();
                     } else {
                     pdfFileurl = "file:/" + hisAppletConfigurator.getBackupsystempdffolderpath() + "/" + resultEntryVOGroupByValidatedByObj.getPatCRNo();
                     pdfFtpurl = "file:/" + hisAppletConfigurator.getBackupsystempdffolderpath() + "/" + resultEntryVOGroupByValidatedByObj.getPatCRNo();
                     }
                     } else {

                     String logicalName = hisAppletConfigurator.getResultprintingFTPAddress().replace('/', '#').split("#")[1];
                     Log(Level.INFO, "logical Name is -->" + logicalName);

                     pdfFileurl = "ftp://" + hisAppletConfigurator.getResultprintingFTPAddress() + "/" + logicalName + "/" + resultEntryVOGroupByValidatedByObj.getPatCRNo();
                     if (hisAppletConfigurator.getFtpservermode().equals("1")) {
                     pdfFileurl = "ftp://" + hisAppletConfigurator.getResultprintingFTPAddress() + "/" + resultEntryVOGroupByValidatedByObj.getPatCRNo();
                     }

                     pdfFtpurl = "ftp://" + hisAppletConfigurator.getResultprintingFTPAddress() + "/" + resultEntryVOGroupByValidatedByObj.getPatCRNo();
                     }*/
                    // pdfFileurl = pdfFtpurl = resultEntryVOGroupByValidatedByObj.getPatCRNo();
                    //(Level.INFO, "pdfFileurl-1::" + pdfFileurl);
                    //Log(Level.INFO, "pdfFileurl-2:" + pdfFtpurl);
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss.SSSa");
                    String formattedDate = sdf.format(date);
                    System.out.println(formattedDate); // 12/
                    String pdfFileName = resultEntryVOGroupByValidatedByObj.getPatCRNo() + "_"
                            + resultEntryVOGroupByValidatedByObj.getLaboratoryCode() + "_"
                            + formattedDate + ".pdf";

                    /*  Object[] workOrderSDAttributes = new Object[7];
                     workOrderSDAttributes[0] = resultEntryVOGroupByValidatedByObj.getPatCRNo(); //Unregister CR NO
                     workOrderSDAttributes[1] = resultEntryVOGroupByValidatedByObj.resultEntryVO.getRequisitionNo();
                     workOrderSDAttributes[2] = maxtransactionno;
                     workOrderSDAttributes[3] = pdfFileurl + "/" + pdfFileName;
                     workOrderSDAttributes[4] = resultEntryVOGroupByValidatedByObj.getClinicianName().toString().trim();
                     Log(Level.INFO, "workOrderSDAttributes[4]----------->" + workOrderSDAttributes[4]);

                     Object[] workOrderArray = new Object[resultEntryVOGroupByValidatedByObj.getResultEntryVOListValidatedBy().size()];
                     Object[] sessionArray = new Object[resultEntryVOGroupByValidatedByObj.getResultEntryVOListValidatedBy().size()];
                     int workorderArrIndex = 0;
                     for (ResultEntryVO resultEntryVOObj : resultEntryVOGroupByValidatedByObj.getResultEntryVOListValidatedBy()) {
                     workOrderArray[workorderArrIndex] = resultEntryVOObj.getRequisitionDNo();

                     if (resultEntryVOObj.getIsTestMultiSession().equals(1)) {
                     sessionArray[workorderArrIndex] = resultEntryVOObj.getSessionId();
                     } else {
                     sessionArray[workorderArrIndex] = "0";
                     }

                     workorderArrIndex++;
                     }

                     workOrderSDAttributes[5] = new oracle.sql.ARRAY(workOrderAD, conn, workOrderArray);//allWorkOrderNo;
                     workOrderSDAttributes[6] = new oracle.sql.ARRAY(workOrderAD, conn, sessionArray);//allSessionNo;

                     workOrderCollectionAttributes[workOrderCollectionADIndex++] = new oracle.sql.STRUCT(workOrderSD, conn, workOrderSDAttributes);
                     Log(Level.INFO, "pdfFileurl---->" + pdfFileurl + "pdfFtpurl-------->" + pdfFtpurl + "pdfFileName-------->" + pdfFileName);

                     cstmt = null;
                     cstmt = conn.prepareCall("{call Inv_Pdf_Print.savecrnowisepdfdata(?,?,?,?,?,?)}");

                     cstmt.setString(1, null);
                     cstmt.setString(2, hospitalCode);
                     cstmt.setObject(3, new oracle.sql.ARRAY(workOrderCollectionAD, conn, workOrderCollectionAttributes));
                     cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
                     cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
                     cstmt.setString(6, "REGISTER");
                     cstmt.execute();
                     Log(Level.INFO, "query is equal to ErrorMsg==" + cstmt.getString(4));
                     Log(Level.INFO, "query is equal to ErrorCode==" + cstmt.getString(5));*/
                    
                	byte[] oldFilePdf=MongoXmlHandler.getInstance().latestFetchFileByte(resultEntryVOGroupByValidatedByObj.getOldReportUrl());

                	
                	
                    SavePDF.saveFileToLocation(resultEntryVOGroupByValidatedByObj.getHeader(), resultEntryVOGroupByValidatedByObj.getFooter(), resultEntryVOGroupByValidatedByObj.getGroupTemplateString(), pdfFileName, Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterHeight()), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getHeaderHeight()), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getHeaderWidth()), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterWidth()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : resultEntryVOGroupByValidatedByObj.getPageSize()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : Config.pagewidthprinting),oldFilePdf,resultEntryVOGroupByValidatedByObj.getAccessionNo(),resultEntryVOGroupByValidatedByObj.getIsfileuploadprint(),resultEntryVOGroupByValidatedByObj.getIsfirstpagereq(), resultEntryVOGroupByValidatedByObj);
                    //resultEntryVOGroupByValidatedByObj.setPdfFtpUrl(pdfFileurl + "/" + pdfFileName);
                    resultEntryVOGroupByValidatedByObj.setPdfFileName(pdfFileName);
                    String query = QueryConfig.Q_SAVE_PDF_WORKORDERS;
                    // conn = PGDataHelper.getInstance().getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    for (ResultEntryVO resultEntryVOObj : resultEntryVOGroupByValidatedByObj.getResultEntryVOListValidatedBy()) {
                        pstmt.setString(1, resultEntryVOGroupByValidatedByObj.getPatCRNo());
                        pstmt.setString(2, resultEntryVOObj.getRequisitionDNo());
                        pstmt.setString(3, resultEntryVOGroupByValidatedByObj.getPdfFileName());
                        pstmt.setString(4, resultEntryVOObj.getHospitalCode());
                        pstmt.executeUpdate();

                        System.out.println(resultEntryVOGroupByValidatedByObj.getPatCRNo() + " "
                                + resultEntryVOObj.getRequisitionDNo() + " "
                                + resultEntryVOGroupByValidatedByObj.getRequisitionNo() + " "
                                + resultEntryVOGroupByValidatedByObj.getPdfFileName() + " "
                                + resultEntryVOObj.getHospitalCode());
                    }
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    conn.commit();

                    Log(Level.INFO, "PDF File Saved " + pdfFileName);
                    //  encryptedPdfFile(pdfFileurl, pdfFileName, "administrator");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    if (conn != null) {
                        conn.rollback();
                    }

                    ex.printStackTrace();
                } finally {
                    try {

                        if (cstmt != null) {
                            cstmt.close();
                        }

                    } catch (SQLException sqex) {
                        sqex.printStackTrace();
                    }
                }

            }

            //   }
            /* else if (unRegisteredValidatedByList != null && unRegisteredValidatedByList.size() > 0) {

             Object[] workOrderCollectionAttributes = null;

             workOrderCollectionAttributes = new Object[unRegisteredValidatedByList.size()];
             int workOrderCollectionADIndex = 0;
             for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByUnregObj : unRegisteredValidatedByList) {

             StructDescriptor workOrderSD = StructDescriptor.createDescriptor("INVPDFWORKORDEROBJ", conn);
             ArrayDescriptor workOrderCollectionAD = ArrayDescriptor.createDescriptor("INVPDFWORKORDERCOLLECTION", conn);

             try {
             Log(Level.INFO, "inside DAO func---printReportInPdfFormat-->ResultEntryVOGroupByValidatedBy");
             cstmt = conn.prepareCall("{call Inv_Pdf_Print.getmaxtransactionnoforpdf(?,?,?,?,?,?,?)}");
             cstmt.setString(1, resultEntryVOGroupByValidatedByUnregObj.resultEntryVO.getRequisitionNo());
             cstmt.setString(2, "10000");
             cstmt.setString(3, hospitalCode);
             cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
             cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
             cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
             cstmt.setString(7, "UNREGISTER");
             cstmt.execute();

             Log(Level.INFO, "query is equal to MaxPdfTransactionId ==" + cstmt.getString(4));
             Log(Level.INFO, "query is equal to ErrorMsg==" + cstmt.getString(5));
             Log(Level.INFO, "query is equal to ErrorCode==" + cstmt.getString(6));
             String maxtransactionno = cstmt.getString(4);

             String logicalName = hisAppletConfigurator.getResultprintingFTPAddress().replace('/', '#').split("#")[1];

             Log(Level.INFO, "resultEntryVOGroupByValidatedByUnregObj.resultEntryVO.getRequisitionNo()----------->" + resultEntryVOGroupByValidatedByUnregObj.resultEntryVO.getRequisitionNo());
             //String pdfFileurl="ftp://10.0.5.152/ftpserver/ftpserver"+"/"+resultEntryVOGroupByValidatedByUnregObj.resultEntryVO.getRequisitionNo();
             String pdfFileurl = "ftp://" + hisAppletConfigurator.getResultprintingFTPAddress() + "/" + logicalName + "/" + resultEntryVOGroupByValidatedByUnregObj.resultEntryVO.getRequisitionNo();

             //String pdfFtpurl="ftp://10.0.5.152/ftpserver"+"/"+resultEntryVOGroupByValidatedByUnregObj.resultEntryVO.getRequisitionNo();
             String pdfFtpurl = "ftp://" + hisAppletConfigurator.getResultprintingFTPAddress() + "/" + resultEntryVOGroupByValidatedByUnregObj.resultEntryVO.getRequisitionNo();

             String pdfFileName = resultEntryVOGroupByValidatedByUnregObj.resultEntryVO.getRequisitionNo() + "_" + maxtransactionno + ".pdf";

             Object[] workOrderSDAttributes = new Object[7];
             workOrderSDAttributes[0] = resultEntryVOGroupByValidatedByUnregObj.resultEntryVO.getRequisitionNo();
             workOrderSDAttributes[1] = resultEntryVOGroupByValidatedByUnregObj.resultEntryVO.getRequisitionNo();
             workOrderSDAttributes[2] = maxtransactionno;
             workOrderSDAttributes[3] = pdfFileurl + "/" + pdfFileName;
             workOrderSDAttributes[4] = resultEntryVOGroupByValidatedByUnregObj.getClinicianName().toString().trim();
             String allWorkOrderNo = "";
             String allSessionNo = "";
             for (ResultEntryVO resultEntryVOObj : resultEntryVOGroupByValidatedByUnregObj.getResultEntryVOListValidatedBy()) {

             allWorkOrderNo += resultEntryVOObj.getRequisitionDNo() + "#";

             if (resultEntryVOObj.getIsTestMultiSession().equals(1)) {
             allSessionNo += resultEntryVOObj.getSessionId() + "#";
             } else {
             allSessionNo += "0" + "#";
             }
             }
             workOrderSDAttributes[5] = allWorkOrderNo;
             workOrderSDAttributes[6] = allSessionNo;

             workOrderCollectionAttributes[workOrderCollectionADIndex++] = new oracle.sql.STRUCT(workOrderSD, conn, workOrderSDAttributes);
             Log(Level.INFO, "pdfFileurl---->" + pdfFileurl + "pdfFtpurl-------->" + pdfFtpurl + "pdfFileName-------->" + pdfFileName);

             cstmt = null;
             cstmt = conn.prepareCall("{call Inv_Pdf_Print.savecrnowisepdfdata(?,?,?,?,?,?)}");
             cstmt.setString(1, "10000");
             cstmt.setString(2, hospitalCode);
             cstmt.setObject(3, new oracle.sql.ARRAY(workOrderCollectionAD, conn, workOrderCollectionAttributes));
             cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
             cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
             cstmt.setString(6, "UNREGISTER");
             cstmt.execute();
             DefaultPrintingTemplateWithStandardRanges.saveFileToLocation(resultEntryVOGroupByValidatedByUnregObj.getHeader(), resultEntryVOGroupByValidatedByUnregObj.getFooter(), resultEntryVOGroupByValidatedByUnregObj.getGroupTemplateString(), pdfFileurl, pdfFtpurl, pdfFileName, Float.parseFloat(resultEntryVOGroupByValidatedByUnregObj.getFooterHeight()), Float.parseFloat(resultEntryVOGroupByValidatedByUnregObj.getHeaderHeight()), Float.parseFloat(resultEntryVOGroupByValidatedByUnregObj.getHeaderWidth()), Float.parseFloat(resultEntryVOGroupByValidatedByUnregObj.getFooterWidth()), ((resultEntryVOGroupByValidatedByUnregObj.getPageSize().startsWith("A4")) ? null : resultEntryVOGroupByValidatedByUnregObj.getPageSize()), ((resultEntryVOGroupByValidatedByUnregObj.getPageSize().startsWith("A4")) ? null : InvestigationStaticConfigurator.pagewidthprinting), hisAppletConfigurator.getBackupsystemenabled(), hisAppletConfigurator.getBackupsystempdffolderpath());
             resultEntryVOGroupByValidatedByUnregObj.setPdfFtpUrl(pdfFileurl + "/" + pdfFileName);
             Log(Level.INFO, pdfFileurl + "/" + pdfFileName);
             encryptedPdfFile(pdfFileurl, pdfFileName, "administrator");
             } catch (Exception ex) {
             if (conn != null) {
             conn.rollback();
             }

             ex.printStackTrace();
             } finally {
             try {

             if (cstmt != null) {
             cstmt.close();
             }

             } catch (Exception ex) {
             ex.printStackTrace();
             }
             }
             }
             }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.commit();
                //  conn.close();
            }
        }

        return flag;
    }

    private List<ResultEntryVOGroupByValidatedBy> resultsetProcessingForGroupingWorkOrder(ResultSet rs, List<ResultEntryVOGroupByValidatedBy> workOrderGroupListForresultEntry) {
        ResultEntryVO resultEntryVO = null;
        Map<String, ResultEntryVOGroupByValidatedBy> checkMap = new HashMap<String, ResultEntryVOGroupByValidatedBy>();
        ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy = null;
        try {

            while (rs.next()) {
                //if (rs.getString("isTestReady").equals("READY")) 
                {
                    if (resultEntryVO == null) {
                        Log(Level.INFO, "laboratoryCode::" + rs.getString("laboratorycode") + ",groupCode::" + rs.getString("groupcode") + ",testCode::" + rs.getString("testcode") + ",hospitalCode" + rs.getString("hospitalcode"));
                        resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                        DataHandler.populateVOfrmRS(resultEntryVO, rs);

                        resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                        DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                        if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                            resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                        }

                        resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);
                        workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);

                        if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                            checkMap.put(resultEntryVO.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode() + resultEntryVOGroupByValidatedBy.getGroupCode(), resultEntryVOGroupByValidatedBy);
                        }

                    } else {
                        if (((resultEntryVO.getRequisitionNo()).equals(rs.getString("requisitionNo")) == true) && (resultEntryVO.getLaboratoryCode().equals(rs.getString("laboratoryCode")) == true) && (resultEntryVO.getTestCode().equals(rs.getString("testCode")) == true)) {
                            if (resultEntryVO.getIsTestMultiSession().equals("1"))// if test is Multi session
                            {
                                //if test is sample re grouped
                                if ((resultEntryVO.getSampleGroupCode() != null) && (resultEntryVO.getSampleGroupCode().equals(rs.getString("sampleGroupCode")) == true)) {
                                    if (resultEntryVO.getIsMultiSessionRegrouped().equals("0"))//grouping is not achieved
                                    {
                                        if (resultEntryVO.getSessionId().equals(rs.getString("sessionId"))) {
                                            if (resultEntryVO.getAssociatedWorkOrders() == null) {
                                                resultEntryVO.setAssociatedWorkOrders(new ArrayList<TriStringObject>());
                                            }

                                            resultEntryVO.getAssociatedWorkOrders().add(new TriStringObject(rs.getString("requisitionDNo"), rs.getString("sampleNo"), rs.getString("sessionId")));

                                        } else {

                                            //resultEntryVO=new ResultEntryVO();	CACHING IMPLEMENTATION
                                            resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                                            DataHandler.populateVOfrmRS(resultEntryVO, rs);

                                            if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode())) {
                                                resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode());
                                            } else {
                                                resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                                DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                                workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                                if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                                    checkMap.put(resultEntryVO.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode() + resultEntryVOGroupByValidatedBy.getGroupCode(), resultEntryVOGroupByValidatedBy);
                                                }
                                            }

                                            if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                                                resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                                            }

                                            resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);

                                        }
                                    } else // grouping to be achieved
                                    {
                                        if (resultEntryVO.getAssociatedWorkOrders() == null) {
                                            resultEntryVO.setAssociatedWorkOrders(new ArrayList<TriStringObject>());
                                        }

                                        resultEntryVO.getAssociatedWorkOrders().add(new TriStringObject(rs.getString("requisitionDNo"), rs.getString("sampleNo"), rs.getString("sessionId")));

                                    }

                                } else// test not raised with sample groupCode
                                {

                                    if (resultEntryVO.getIsMultiSessionRegrouped().equals("0"))// grouping is not achieved
                                    {

                                        //resultEntryVO=new ResultEntryVO();	CACHING IMPLEMENTATION
                                        resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                                        DataHandler.populateVOfrmRS(resultEntryVO, rs);

                                        if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode())) {
                                            resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode());
                                        } else {
                                            resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                            DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                            workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                            if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                                checkMap.put(resultEntryVO.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode() + resultEntryVOGroupByValidatedBy.getGroupCode(), resultEntryVOGroupByValidatedBy);
                                            }
                                        }

                                        if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                                            resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                                        }

                                        resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);

                                    } else // grouping to be achieved
                                    {
                                        if (resultEntryVO.getAssociatedWorkOrders() == null) {
                                            resultEntryVO.setAssociatedWorkOrders(new ArrayList<TriStringObject>());
                                        }

                                        resultEntryVO.getAssociatedWorkOrders().add(new TriStringObject(rs.getString("requisitionDNo"), rs.getString("sampleNo"), rs.getString("sessionId")));
                                    }

                                }

                            } else //Test is not multisessioned
                            {
                                if ((resultEntryVO.getSampleGroupCode() != null) && (resultEntryVO.getSampleGroupCode().equals(rs.getString("sampleGroupCode")) == true)) {
                                    if (resultEntryVO.getAssociatedWorkOrders() == null) {
                                        resultEntryVO.setAssociatedWorkOrders(new ArrayList<TriStringObject>());
                                    }

                                    resultEntryVO.getAssociatedWorkOrders().add(new TriStringObject(rs.getString("requisitionDNo"), rs.getString("sampleNo"), rs.getString("sessionId")));
                                } else {

                                    //resultEntryVO=new ResultEntryVO();	CACHING IMPLEMENTATION
                                    resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                                    DataHandler.populateVOfrmRS(resultEntryVO, rs);
                                    if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode())) {
                                        resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode());
                                    } else {
                                        resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                        DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                        workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                        if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                            checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode() + resultEntryVOGroupByValidatedBy.getGroupCode(), resultEntryVOGroupByValidatedBy);
                                        }
                                    }

                                    if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                                        resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                                    }

                                    resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);

                                }
                            }

                        } else {

                            //resultEntryVO=new ResultEntryVO();	CACHING IMPLEMENTATION
                            resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                            DataHandler.populateVOfrmRS(resultEntryVO, rs);
                            if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode())) {
                                resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode());
                            } else {
                                resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                    checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode() + resultEntryVOGroupByValidatedBy.getGroupCode(), resultEntryVOGroupByValidatedBy);
                                }
                            }

                            if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                                resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                            }

                            resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);

                        }

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log(Level.INFO, "END" + workOrderGroupListForresultEntry.size());

        return workOrderGroupListForresultEntry;
    }

    private List<ResultEntryVOGroupByValidatedBy> processTheWorkOrdersToGroups(List<ResultEntryVOGroupByValidatedBy> workOrderGroupListForresultEntry) {

        List<ResultEntryVOGroupByValidatedBy> resultEntryVOGroupByValidatedByList = new ArrayList<ResultEntryVOGroupByValidatedBy>();
        Map<String, Integer> fileGroupMap = new HashMap<String, Integer>();

        for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy : workOrderGroupListForresultEntry) {
            //if group belongs to a group code

            if (resultEntryVOGroupByValidatedBy.getGroupCode() != null && resultEntryVOGroupByValidatedBy.getGroupCode().equals("") == false) {
                Log(Level.INFO, "processTheWorkOrdersToGroups::1::group belongs to a group code ::+" + resultEntryVOGroupByValidatedBy.getGroupCode());
                if (resultEntryVOGroupByValidatedBy.getGroupMap() == null) {
                    resultEntryVOGroupByValidatedBy.setGroupMap(new HashMap<String, String>());
                }

                resultEntryVOGroupByValidatedBy.getGroupMap().put(resultEntryVOGroupByValidatedBy.getGroupCode(), resultEntryVOGroupByValidatedBy.getGroupCode());

                if (resultEntryVOGroupByValidatedBy.getPrintedWithTemplateID() != null && (!resultEntryVOGroupByValidatedBy.getPrintedWithTemplateID().equals(""))) {
                    Log(Level.INFO, "processTheWorkOrdersToGroups::1::printing template id exists for group ::+" + resultEntryVOGroupByValidatedBy.getPrintedWithTemplateID());
                    /* printing template id exists for group */
                    /* find if printing file exists for any of the groups group */
                    boolean printingFileExists = false;
                    //this printing template does exists for this group
                    printingFileExistsloop:
                    for (ResultEntryVO entryVO : resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {
                        if (entryVO.getPdfFtpUrl() != null && (entryVO.getPdfFtpUrl().equals("") == false)) {
                            printingFileExists = true;
                            resultEntryVOGroupByValidatedBy.setPdfFtpUrl(entryVO.getPdfFtpUrl());
                            break printingFileExistsloop;
                        }

                    }

                    //printing file exists for this GroupVO
                    if (printingFileExists) {
                        Log(Level.INFO, "processTheWorkOrdersToGroups::4::printing file exists for this GroupVO");
                        resultEntryVOGroupByValidatedBy = populateTheGroupingVOWithWorkOrdersPrintedWith(resultEntryVOGroupByValidatedBy);
                        //find whether any group exists with this map
                        Integer indexFound = fileGroupMap.get(resultEntryVOGroupByValidatedBy.getPdfFtpUrl());

                        if (indexFound == null) {
                            Log(Level.INFO, "processTheWorkOrdersToGroups::5::printing file not present in current processing");
                            //printing file not present in current processing 
                            resultEntryVOGroupByValidatedByList.add(resultEntryVOGroupByValidatedBy);
                            fileGroupMap.put(resultEntryVOGroupByValidatedBy.getPdfFtpUrl(), resultEntryVOGroupByValidatedByList.size() - 1);
                        } else {
                            Log(Level.INFO, "processTheWorkOrdersToGroups::6::printing file exists for group work order and the file key found ie group found");
                            //if printing file exists for group work order and the file key found ie group found
                            // adding all work orders which are not present in the existing group
                            ResultEntryVOGroupByValidatedBy existingGroupVO = resultEntryVOGroupByValidatedByList.get(indexFound);
                            for (ResultEntryVO resVO : resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {
                                boolean workorderAlreadyExists = false;
                                for (ResultEntryVO rVO : existingGroupVO.getResultEntryVOListValidatedBy()) {
                                    if (resVO.getRequisitionDNo().equals(rVO.getRequisitionDNo()) && resVO.getSessionId().equals(rVO.getSessionId())) {
                                        workorderAlreadyExists = true;
                                    }

                                }

                                if (workorderAlreadyExists == false) {
                                    existingGroupVO.getResultEntryVOListValidatedBy().add(resVO);
                                }

                            }

                        }
                    } else {
                        Log(Level.INFO, "processTheWorkOrdersToGroups::7::printing template id exists and No work order was printed to a file");
                        /* If printing template id exists and No work order was printed to a file */
                        /* Find the printing template id present in the processing  */

                        for (ResultEntryVO resultEntryVO : resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {
                            // find the group which has printing template associated with it
                            findThePrintingTemplateId(fileGroupMap, resultEntryVO, 1, resultEntryVOGroupByValidatedByList);
                        }
                    }
                } else {
                    Log(Level.INFO, "processTheWorkOrdersToGroups::8::printing template id does not exists for group");
                    /*printing template id does not exists for group*/
                    if (resultEntryVOGroupByValidatedBy.getGroupMap() == null) {
                        resultEntryVOGroupByValidatedBy.setGroupMap(new HashMap<String, String>());
                    }

                    resultEntryVOGroupByValidatedBy.getGroupMap().put(resultEntryVOGroupByValidatedBy.getGroupCode(), resultEntryVOGroupByValidatedBy.getGroupCode());

                    processingTheWorkOrdersWhichbelongstogrouporNot(fileGroupMap, resultEntryVOGroupByValidatedByList, resultEntryVOGroupByValidatedBy);
                }

            } else {
                Log(Level.INFO, "processTheWorkOrdersToGroups::9::work order group formed is not associated to a group");
                //if work order group formed is not associated to a group
                if (resultEntryVOGroupByValidatedBy.getGroupMap() == null) {
                    resultEntryVOGroupByValidatedBy.setGroupMap(new HashMap<String, String>());
                }

                processingTheWorkOrdersWhichbelongstogrouporNot(fileGroupMap, resultEntryVOGroupByValidatedByList, resultEntryVOGroupByValidatedBy);
            }

        }
        return resultEntryVOGroupByValidatedByList;
    }

    public ResultEntryVOGroupByValidatedBy processingTheWorkOrdersWhichbelongstogrouporNot(Map<String, Integer> fileGroupMap, List<ResultEntryVOGroupByValidatedBy> resultEntryVOGroupByValidatedByList, ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy) {
        Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot");
        for (ResultEntryVO resultEntryVO : resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {
            Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::1");
            // if work order is already printed in a file
            if (resultEntryVO.getPdfFtpUrl() != null && resultEntryVO.getPdfFtpUrl().equals("") == false) {
                Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::2");
                //find if the a group exists with this fileName
                ResultEntryVOGroupByValidatedBy groupingVO = resultEntryVOGroupByValidatedByList.get(fileGroupMap.get(resultEntryVO.getPdfFtpUrl()));
                if (groupingVO == null)// group not present
                {
                    Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::3");
                    groupingVO = new ResultEntryVOGroupByValidatedBy();
                    DataHandler.populate(groupingVO, resultEntryVO);
                    groupingVO = populateTheGroupingVOWithWorkOrdersPrintedWith(groupingVO);
                    fileGroupMap.put(groupingVO.getPdfFtpUrl(), resultEntryVOGroupByValidatedByList.size() - 1);
                    resultEntryVOGroupByValidatedByList.add(groupingVO);
                    if (groupingVO.getGroupMap() == null) {
                        groupingVO.setGroupMap(new HashMap<String, String>());
                    }

                }

                //add the work order to the groupVO
                boolean isContainable = true;

                loop1:
                for (ResultEntryVO tempResVO : groupingVO.getResultEntryVOListValidatedBy()) {
                    if (tempResVO.getRequisitionDNo().equals(resultEntryVO.getRequisitionDNo()) && tempResVO.getSessionId().equals(resultEntryVO.getSessionId())) {
                        isContainable = false;
                        break loop1;
                    }
                }

                if (isContainable) {
                    groupingVO.getResultEntryVOListValidatedBy().add(resultEntryVO);
                }

            } else// if work order is very new to the printing
            {
                Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::4");
                // work order has a printing template associated with it
                if (resultEntryVO.getPrintedWithTemplateID() != null
                        && resultEntryVO.getPrintedWithTemplateID().equals("") == false) {
                    Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::5");
                    // find the group which has printing template associated with it
                    findThePrintingTemplateId(fileGroupMap, resultEntryVO, 1, resultEntryVOGroupByValidatedByList);

                } else {
                    Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::6");
                    //populating for the printing templateId to the normal and non normal ranges
                    findThePrintingTemplateId(fileGroupMap, resultEntryVO, 2, resultEntryVOGroupByValidatedByList);
                }

            }

        }

        return resultEntryVOGroupByValidatedBy;
    }

    private ResultEntryVOGroupByValidatedBy findThePrintingTemplateId(Map<String, Integer> fileGroupMap, ResultEntryVO resultEntryVO2, int i, List<ResultEntryVOGroupByValidatedBy> resultEntryVOGroupByValidatedByList) {
        ResultEntryVOGroupByValidatedBy groupByValidatedBy = null;
        //finding the resultEntryVO : work order in the file group map
        // if work order is containable in one of group in the filegroupMap
        upper:
        for (String key : fileGroupMap.keySet()) {
            ResultEntryVOGroupByValidatedBy groupingVO = resultEntryVOGroupByValidatedByList.get(fileGroupMap.get(key));
            // if (resultEntryVO2.getRequisitionTypeCode().equals("3") || (resultEntryVO2.getRequisitionTypeCode().equals("4"))) {
            //     groupByValidatedBy = groupingTheGivenWorkOrderToTheUnRegiseredPatientGroup(groupingVO, resultEntryVO2, resultEntryVOGroupByValidatedByList, i);
            // } else {
            groupByValidatedBy = groupingTheGivenWorkOrderToTheRegiseredPatientGroup(groupingVO, resultEntryVO2, resultEntryVOGroupByValidatedByList, i);
            //  }

            if (groupByValidatedBy != null) {
                break upper;
            }

        }

        if (groupByValidatedBy == null) {
            ResultEntryVOGroupByValidatedBy groupingVO = new ResultEntryVOGroupByValidatedBy();
            DataHandler.populate(groupingVO, resultEntryVO2);
            resultEntryVOGroupByValidatedByList.add(groupingVO);
            groupByValidatedBy = groupingVO;
            if (groupingVO.getGroupMap() == null) {
                groupingVO.setGroupMap(new HashMap<String, String>());
            }

            if (groupingVO.getGroupCode() != null) {
                groupingVO.getGroupMap().put(groupingVO.getGroupCode(), groupingVO.getGroupCode());
            }

            groupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
            if (i == 1) {
                /* if (groupingVO.getRequisitionTypeCode().equals("3") || groupingVO.getRequisitionTypeCode().equals("4")) {
                 fileGroupMap.put(groupingVO.getResultValidatedBy() + groupingVO.getRequisitionNo(), resultEntryVOGroupByValidatedByList.size() - 1);
                 } else {*/
                fileGroupMap.put((groupingVO.getPatAdvisedBy() + groupingVO.getResultValidatedBy() + groupingVO.getPatCRNo() + groupingVO.getPatEpisodeCode() + groupingVO.getPatVisitNo() + "#" + groupingVO.getPrintedWithTemplateID() + "#" + groupingVO.getPrintingType()), resultEntryVOGroupByValidatedByList.size() - 1);
                //}
            } else {
                /* if (groupingVO.getRequisitionTypeCode().equals("3") || groupingVO.getRequisitionTypeCode().equals("4")) {
                 fileGroupMap.put(groupingVO.getResultValidatedBy() + groupingVO.getRequisitionNo(), resultEntryVOGroupByValidatedByList.size() - 1);
                 } else {*/
                fileGroupMap.put((groupingVO.getPatAdvisedBy() + groupingVO.getResultValidatedBy() + groupingVO.getPatCRNo() + groupingVO.getPatEpisodeCode() + groupingVO.getPatVisitNo() + "#" + groupingVO.getPrintedWithTemplateID() + "#" + groupingVO.getPrintingType()), resultEntryVOGroupByValidatedByList.size() - 1);
                //}
            }
        }

        // adding the workorder to the final object constructed
        groupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO2);

        return groupByValidatedBy;
    }

    private ResultEntryVOGroupByValidatedBy groupingTheGivenWorkOrderToTheRegiseredPatientGroup(ResultEntryVOGroupByValidatedBy groupingVO, ResultEntryVO resultEntryVO2, List<ResultEntryVOGroupByValidatedBy> resultEntryVOGroupByValidatedByList, int i) {
        Log(Level.INFO, "groupingTheGivenWorkOrderToTheRegiseredPatientGroup");
        ResultEntryVOGroupByValidatedBy groupByValidatedBy = null;
        if (i == 1) {
            Log(Level.INFO, "groupingTheGivenWorkOrderToTheRegiseredPatientGroup i=" + i);
            if ((groupingVO.getPatAdvisedBy() + groupingVO.getResultValidatedBy() + groupingVO.getPatCRNo()
                    + groupingVO.getPatEpisodeCode() + groupingVO.getPatVisitNo() + "#"
                    + groupingVO.getPrintedWithTemplateID() + "#"
                    + groupingVO.getPrintingType()).equals(resultEntryVO2.getPatAdvisedBy()
                            + resultEntryVO2.getResultValidatedBy() + resultEntryVO2.getPatCRNo()
                            + resultEntryVO2.getPatEpisodeCode() + resultEntryVO2.getPatVisitNo() + "#"
                            + resultEntryVO2.getPrintedWithTemplateID() + "#"
                            + resultEntryVO2.getPrintingType())) {
                boolean isContainable = true;
                if (groupingVO.getResultEntryVOListValidatedBy() != null) {
                    for (ResultEntryVO resultEntryVO : groupingVO.getResultEntryVOListValidatedBy()) {
                        if ((resultEntryVO.getLaboratoryCode() + resultEntryVO.getTestCode() + resultEntryVO.getSessionId()).equals((resultEntryVO2.getLaboratoryCode() + resultEntryVO2.getTestCode() + resultEntryVO2.getSessionId()))) {
                            isContainable = false;
                        }

                    }
                }

                if (isContainable == true) {
                    Log(Level.INFO, "groupingTheGivenWorkOrderToTheRegiseredPatientGroup i=" + i
                            + " isContainable::" + isContainable);
                    groupByValidatedBy = groupingVO;
                } else // if this template is not containable in the mapping group find the other group which represents same printing template id 
                {
                    Log(Level.INFO, "groupingTheGivenWorkOrderToTheRegiseredPatientGroup i=" + i
                            + " isContainable::" + isContainable);
                    Log(Level.INFO, "this template is not containable in the mapping group find the other group "
                            + "which represents same printing template id");

                    searchingloop:
                    for (ResultEntryVOGroupByValidatedBy newGroup : resultEntryVOGroupByValidatedByList) {
                        boolean isNewContainable = true;
                        if ((newGroup.getPatAdvisedBy() + newGroup.getResultValidatedBy() + newGroup.getPatCRNo()
                                + newGroup.getPatEpisodeCode() + newGroup.getPatVisitNo() + "#"
                                + newGroup.getPrintedWithTemplateID() + "#"
                                + newGroup.getPrintingType()).equals(resultEntryVO2.getPatAdvisedBy()
                                        + resultEntryVO2.getResultValidatedBy()
                                        + resultEntryVO2.getPatCRNo()
                                        + resultEntryVO2.getPatEpisodeCode()
                                        + resultEntryVO2.getPatVisitNo() + "#"
                                        + resultEntryVO2.getPrintedWithTemplateID() + "#"
                                        + resultEntryVO2.getPrintingType())) {
                            if (groupingVO.getResultEntryVOListValidatedBy() != null) {
                                for (ResultEntryVO resultEntryVO : newGroup.getResultEntryVOListValidatedBy()) {
                                    if ((resultEntryVO.getLaboratoryCode() + resultEntryVO.getTestCode()
                                            + resultEntryVO.getSessionId()).equals((resultEntryVO2.getLaboratoryCode()
                                                    + resultEntryVO2.getTestCode() + resultEntryVO2.getSessionId()))) {
                                        isNewContainable = false;
                                    }
                                }
                            }

                            if (isNewContainable == true) {
                                groupByValidatedBy = newGroup;
                                break searchingloop;
                            }

                        }
                    }

                }

                if (groupByValidatedBy == null)// if no containable group can be found
                {
                    Log(Level.INFO, "no containable group can be found");
                    groupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                    Log(Level.INFO, "new.... groupByValidatedBy created....");
                    DataHandler.populate(groupByValidatedBy, resultEntryVO2);
                    //check this part::XXXXXXXXXXXXXXXXXXXXXX
                    resultEntryVOGroupByValidatedByList.add(groupByValidatedBy);
                    Log(Level.INFO, "new.... groupByValidatedBy added....");
                    // donot add the group to file group map
                    //groupByValidatedBy=groupingVO;
                    groupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                    if (groupingVO.getGroupMap() == null) {
                        groupingVO.setGroupMap(new HashMap<String, String>());
                    }

                    if (groupingVO.getGroupCode() != null) {
                        groupingVO.getGroupMap().put(groupingVO.getGroupCode(), groupingVO.getGroupCode());
                    }
                }

            }

        } else {
            if ((groupingVO.getPatAdvisedBy() + groupingVO.getResultValidatedBy()
                    + groupingVO.getPatCRNo() + groupingVO.getPatEpisodeCode()
                    + groupingVO.getPatVisitNo() + "#" + groupingVO.getPrintedWithTemplateID() + "#"
                    + groupingVO.getPrintingType()).equals(resultEntryVO2.getPatAdvisedBy()
                            + resultEntryVO2.getResultValidatedBy() + resultEntryVO2.getPatCRNo()
                            + resultEntryVO2.getPatEpisodeCode() + resultEntryVO2.getPatVisitNo() + "#"
                            + resultEntryVO2.getPrintedWithTemplateID() + "#" + resultEntryVO2.getPrintingType())) {

                boolean isContainable = true;
                for (ResultEntryVO resultEntryVO : groupingVO.getResultEntryVOListValidatedBy()) {
                    if ((resultEntryVO.getLaboratoryCode() + resultEntryVO.getTestCode()
                            + resultEntryVO.getSessionId()).equals((resultEntryVO2.getLaboratoryCode()
                                    + resultEntryVO2.getTestCode() + resultEntryVO2.getSessionId()))) {
                        isContainable = false;
                    }

                }

                if (isContainable == true) {
                    groupByValidatedBy = groupingVO;

                } else {
                    searchingloop1:
                    for (ResultEntryVOGroupByValidatedBy newGroup : resultEntryVOGroupByValidatedByList) {
                        boolean isNewContainable = true;
                        if ((newGroup.getPatAdvisedBy() + newGroup.getResultValidatedBy() + newGroup.getPatCRNo()
                                + newGroup.getPatEpisodeCode() + newGroup.getPatVisitNo() + "#"
                                + newGroup.getPrintedWithTemplateID() + "#"
                                + newGroup.getPrintingType()).equals(resultEntryVO2.getPatAdvisedBy()
                                        + resultEntryVO2.getResultValidatedBy()
                                        + resultEntryVO2.getPatCRNo()
                                        + resultEntryVO2.getPatEpisodeCode()
                                        + resultEntryVO2.getPatVisitNo() + "#"
                                        + resultEntryVO2.getPrintedWithTemplateID() + "#"
                                        + resultEntryVO2.getPrintingType())) {

                            for (ResultEntryVO resultEntryVO : newGroup.getResultEntryVOListValidatedBy()) {
                                if ((resultEntryVO.getLaboratoryCode() + resultEntryVO.getTestCode() + resultEntryVO.getSessionId()).equals((resultEntryVO2.getLaboratoryCode() + resultEntryVO2.getTestCode() + resultEntryVO2.getSessionId()))) {
                                    isNewContainable = false;
                                }
                            }

                            if (isNewContainable == true) {
                                groupByValidatedBy = newGroup;
                                break searchingloop1;
                            }

                        }
                    }
                }

                if (groupByValidatedBy == null)// if no containable group can be found
                {
                    groupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                    DataHandler.populate(groupByValidatedBy, resultEntryVO2);
                    // donot add the group to file group
                    //commented as groupValidated By VO are not equal 
                    //groupByValidatedBy=groupingVO;
                    //check this part::XXXXXXXXXXXXXXXXXXXXXX
                    resultEntryVOGroupByValidatedByList.add(groupByValidatedBy);

                    groupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());

                }

            }
        }

        return groupByValidatedBy;
    }

    private ResultEntryVOGroupByValidatedBy populateTheGroupingVOWithWorkOrdersPrintedWith(ResultEntryVOGroupByValidatedBy groupingVO) {
        Connection conn = null;
        try {
            conn = PGDataHelper.getInstance().getConnection();
            // conn.setAutoCommit(false);
            CallableStatement cstmt = conn.prepareCall(QueryConfig.P_GET_PDF_FILE_WORKORDERLISTDATA);
            cstmt.setString(1, groupingVO.getRequisitionTypeCode());
            cstmt.setString(2, groupingVO.getPdfFtpUrl());
            cstmt.registerOutParameter(3, Types.REF);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.registerOutParameter(5, Types.VARCHAR);
         
            if(conn==null || conn.isClosed())
            {
          	  
          	
            }
            else
            {
            cstmt.execute();
            ResultEntryVO resultEntryVO = null;
            ResultSet rs = (ResultSet) cstmt.getObject(4);
            while (rs.next()) {
                boolean found = false;
                for (ResultEntryVO resultEntryVO2 : groupingVO.getResultEntryVOListValidatedBy()) {
                    if (rs.getString(1).equals(resultEntryVO2.getRequisitionDNo()) && rs.getString(2).equals(resultEntryVO2.getSessionId())) {
                        found = true;
                    }
                }
                if (found == false) {
                    //resultEntryVO=new ResultEntryVO();	CACHING IMPLEMENTATION
                    resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                    resultEntryVO.setRequisitionDNo(rs.getString(1));
                    resultEntryVO.setSessionId(rs.getString(2));
                    resultEntryVO.setLaboratoryCode(rs.getString(3));
                    resultEntryVO.setTestCode(rs.getString(4));
                    if (groupingVO.getResultEntryVOListValidatedBy() == null) {
                        groupingVO.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                    }

                    // add only if workorder is not present in the 
                    groupingVO.getResultEntryVOListValidatedBy().add(resultEntryVO);
                }

            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            	if(conn!=null && !conn.isClosed())
            	{
                conn.commit();
                conn.close();
            	}
            	
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return groupingVO;
    }

    private void Log(Level level, String msg) {
        ServiceLogger.Log(PGPDFProcessing.class.getName(), level, msg);
    }

    private void Log(Level level, Exception e) {
        ServiceLogger.Log(PGPDFProcessing.class.getName(), level, e);
    }

}
