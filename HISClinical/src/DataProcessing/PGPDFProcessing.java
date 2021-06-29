/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import DataHelper.Config;
import DataHelper.PGDataHelper;
import DataHelper.PropertiesHelper;
import DataHelper.QueryConfig;
import DataHelper.ServiceConfiguration;
import Logging.ServiceLogger;
import MongoHelper.MongoXmlHandler;
import TemplateHelper.TriStringObject;
import TemplateHelper.vo.ResultEntryVO;
import TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
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

import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Document;

import MailSender.SendMessageToUser;

import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.sun.corba.se.impl.ior.OldPOAObjectKeyTemplate;

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
public class PGPDFProcessing {

    public boolean processingData() {
        boolean jobprocessContinue = false;

        String processingData = null;
        String processinglaboratoryCode = null;
        String hospitalCode = null;
        String isDeptEntry = null;
        Connection conn = null;
      //  Log(Level.INFO, "Beginning PDF Generation");
        CallableStatement cstmt = null;
        try {
            conn = PGDataHelper.getInstance().getConnection();
            if (conn == null) {
               // conn = PGDataHelper.getInstance().createPostgresConnection(PropertiesHelper.getMongoConnectionURI(), ServiceConfiguration.dbUsername, ServiceConfiguration.dbPassword);  
                conn = PGDataHelper.getInstance().createPostgresConnection();
                //conn = PGDataHelper.getInstance().createPostgresConnection("jdbc:edb://10.0.2.103:5444/nimsnew", "nimsnew", "nimsnew");  
            }
            String query = QueryConfig.P_GET_PDF_WORKORDERLIST;
            // conn.setAutoCommit(false);
            cstmt = conn.prepareCall(query);

            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.execute();
            processingData = cstmt.getString(1);
            processinglaboratoryCode = cstmt.getString(2);
            hospitalCode = cstmt.getString(3);
            isDeptEntry = cstmt.getString(4);

            if (cstmt != null) {
                cstmt.close();
                //conn.commit();
                //  conn.setAutoCommit(true);
            }

            if (processingData != null) {
                jobprocessContinue = true;

                processingToGenerateReport(conn, hospitalCode, processinglaboratoryCode, processingData, false,isDeptEntry);
                /*cstmt = conn.prepareCall("{call inv_reportengine.removeSingleDataforjob(?,?,?,?)}");
                 cstmt.setString(1, processinglaboratoryCode);
                 cstmt.setString(2, InvestigationStaticConfigurator.servernumber);
                 cstmt.setString(3, processingData);
                 cstmt.setString(4, hospitalCode);
                 cstmt.execute();*/
            } else {
                jobprocessContinue = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            jobprocessContinue = false;
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }

                if (conn != null) {
                    conn.commit();
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
       // Log(Level.INFO, "Finishing PDF Generation");
        return jobprocessContinue;
    }

    public void processingToGenerateReport(Connection conn, String hospitalCode, String labCode, String crOrRequisitionNo, boolean compulsory,String isDeptEntry) {
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
            cstmt.setString(8, isDeptEntry);
          //  System.out.println(cstmt);
          //  System.out.println(cstmt.execute());
            cstmt.execute();
            
            Log(Level.INFO, "processingToGenerateReport CR Nos::" + crOrRequisitionNo);
            Log(Level.INFO, "processingToGenerateReport REPORTING ENGINGE QUERY-->INV_REPORTENGINE.getWorkOrderList::" + cstmt.getString(5) + cstmt.getString(6) + cstmt.getString(7));
            rs = (ResultSet) cstmt.getObject(4);
            workOrderGroupListForresultEntry = resultsetProcessingForGroupingWorkOrder(rs, workOrderGroupListForresultEntry);

            
            Log(Level.INFO, "processingToGenerateReport workOrderListForresultEntry  size ::" + workOrderGroupListForresultEntry.size());
            // List<ResultEntryVOGroupByValidatedBy> reportGroupByList = processTheWorkOrdersToGroups(workOrderGroupListForresultEntry);
            List<ResultEntryVOGroupByValidatedBy> reportGroupByList = workOrderGroupListForresultEntry;
            Log(Level.INFO, "processingToGenerateReport After reportGroupByList  size ::" + reportGroupByList.size());

            /* if (reportGroupByList != null && reportGroupByList.size() > 0) {
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

             }*/
            if (reportGroupByList != null && reportGroupByList.size() != 0) {
                Log(Level.INFO, "processingToGenerateReport reportGroupByList:::" + reportGroupByList.size());
                /* for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy : reportGroupByList) {
                 if (resultEntryVOGroupByValidatedBy.getPrintedWithTemplateID() != null && resultEntryVOGroupByValidatedBy.getPrintedWithTemplateID().equals("0") == false) {
                 Log(Level.INFO, "<!----------------------------PrintingTemplate Id Found---------------------->" + resultEntryVOGroupByValidatedBy.getPrintedWithTemplateID());
                 Log(Level.INFO, "ReporintEngineDAO::processingToGenerateReport " + resultEntryVOGroupByValidatedBy.getGroupMap());
                 PrintingHelper.populatePrintingDocumentWithWorkOrderDocument(resultEntryVOGroupByValidatedBy);

                 }
                 }*/

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
               
                if(conn != null) {
                    conn.rollback();
                    Log(Level.INFO, "Connection Rollback");
                }
            } catch (SQLException e1) {

                Log(Level.SEVERE, e1);
                e1.printStackTrace();
            }
            Log(Level.SEVERE, e);
          //  e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (cstmt != null) {
                    cstmt.close();
                }
                if( conn != null) {
                    conn.commit();
                  //  conn.close();
                }

            } catch (SQLException sqex) {
                Log(Level.SEVERE, sqex);
                //sqex.printStackTrace();
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
            ResultSet rs1 = null;
            
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
                    //System.out.println(formattedDate); // 12/
                /*    String pdfFileName = resultEntryVOGroupByValidatedByObj.getPatCRNo() +".pdf";
                */
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
                    
                    byte[] oldFilePdf=null;
                    if(resultEntryVOGroupByValidatedByObj.getOldReportUrl()!=null && !resultEntryVOGroupByValidatedByObj.getOldReportUrl().equals("-"))
                    	oldFilePdf=MongoXmlHandler.getInstance().latestFetchFileByte(resultEntryVOGroupByValidatedByObj.getOldReportUrl());
                    
                    if(resultEntryVOGroupByValidatedByObj.getIsNablAccritedTest()!=null && resultEntryVOGroupByValidatedByObj.getIsNablAccritedTest().equals("1"))
                    {
                    	//resultEntryVOGroupByValidatedByObj.setFooterHeight("27.0");
                    	//resultEntryVOGroupByValidatedByObj.setFooterHeight("58.0");
                    	resultEntryVOGroupByValidatedByObj.setFooterHeight("48.0");
                    	
                    }
                    else
                    {
                    	//System.out.println("ashuchk-1");
                    	//resultEntryVOGroupByValidatedByObj.setFooterHeight("58.0");
                    	resultEntryVOGroupByValidatedByObj.setFooterHeight("48.0");
                    	//resultEntryVOGroupByValidatedByObj.setFooterHeight("33.0");
                    	
                    }
                    
                    
                    
                    if(resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0"))
                    {
                    	
                    	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat( Config.HEADER_HEIGHT_WITHOUT_HEADER));
                    	
                    }
                    else
                    {
                    if(resultEntryVOGroupByValidatedByObj.getIsdynamicGroupTemplate().equals("0") && resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate().equals("0"))
                    {
                    if (resultEntryVOGroupByValidatedByObj.getPrintingType() != null && resultEntryVOGroupByValidatedByObj.getPrintingType().equals("1")) {
                        //headerString.append(lineString);
                    	if(resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate()!=null && resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate().equals(1))
                    		resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat( Config.HEADER_HEIGHT_WITHOUT_HEADER));
                    	else
                    	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat( Config.HEADER_HEIGHT));
                    	// headerString.append(standardRangesString);
                       // resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 25));
                    } else {
                    	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat( Config.HEADER_HEIGHT_WITHOUT_HEADER));
                       // resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
                    }
                    }
                    else
                    {
                        if (resultEntryVOGroupByValidatedByObj.getPrintingType() != null && resultEntryVOGroupByValidatedByObj.getPrintingType().equals("1")) {
                           // headerString.append(lineString);
                        	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat( Config.HEADER_HEIGHT_WITHOUT_HEADER));
                            //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
                        } else {
                        	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat( Config.HEADER_HEIGHT_WITHOUT_HEADER));
                            //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
                        }
                    	
                    	
                    	
                    }
                    }
                    
                    
                    String saveStatus = SavePDF.saveFileToLocation(resultEntryVOGroupByValidatedByObj.getHeader(), resultEntryVOGroupByValidatedByObj.getFooter(), resultEntryVOGroupByValidatedByObj.getGroupTemplateString(), pdfFileName, Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterHeight()), resultEntryVOGroupByValidatedByObj.getHeaderheight(), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getHeaderWidth()), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterWidth()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : resultEntryVOGroupByValidatedByObj.getPageSize()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : Config.pagewidthprinting), oldFilePdf);
                    //resultEntryVOGroupByValidatedByObj.setPdfFtpUrl(pdfFileurl + "/" + pdfFileName);
                    resultEntryVOGroupByValidatedByObj.setPdfFileName(pdfFileName);

                    if (saveStatus != null) {
                        String query = QueryConfig.Q_SAVE_PDF_WORKORDERS;
                        //chanks
                         cstmt = null;
                         ResultSet rs = null;
                        String queryforTest = QueryConfig.Q_GET_REQ_DTL_STATUS_HIVT_REQ_DTL;
                          
                        // conn = PGDataHelper.getInstance().getConnection();
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        PreparedStatement pstmt2 = conn.prepareStatement(QueryConfig.Q_UPDATE_REQ_STATUS);
                        
                        if(resultEntryVOGroupByValidatedByObj.getIsDeptEntry().equals("1"))
                        	pstmt2 = conn.prepareStatement(QueryConfig.Q_UPDATE_DEPT_REQ_STATUS);
                        
                        
                        for (ResultEntryVO resultEntryVOObj : resultEntryVOGroupByValidatedByObj.getResultEntryVOListValidatedBy()) {
                        	
                        	//added by chanks
                        	PreparedStatement preparedStatement = null;
                        	String reqDtl_status="";
                        	preparedStatement = conn.prepareStatement(queryforTest);
                        	preparedStatement.setString(1, resultEntryVOObj.getRequisitionDNo());
                        	ResultSet rs2 = preparedStatement.executeQuery();
                        	while (rs2.next()) {

                				 reqDtl_status = rs2.getString("hivnum_reqdtl_status");
                				
                               System.out.println("old status for dno: " +resultEntryVOObj.getRequisitionDNo() +" status old: "+ reqDtl_status);
                				 Log(Level.INFO, "old status for dno: " +resultEntryVOObj.getRequisitionDNo() +" status old: "+ reqDtl_status + " "	);

                			}
                        	
                          
                            
                            
                            pstmt.setString(1, resultEntryVOGroupByValidatedByObj.getPatCRNo());
                            pstmt.setString(2, resultEntryVOObj.getRequisitionDNo());
                            pstmt.setString(3, resultEntryVOGroupByValidatedByObj.getPdfFileName());
                            pstmt.setString(4, resultEntryVOObj.getHospitalCode());  
                            pstmt.setString(5, resultEntryVOGroupByValidatedByObj.getIsDeptEntry()); 
                            pstmt.executeUpdate();
                            
                            pstmt2.setString(1, resultEntryVOGroupByValidatedByObj.getPdfFileName());
                            pstmt2.setString(2, resultEntryVOObj.getRequisitionDNo());
                            pstmt2.executeUpdate();
                            System.out.println("Req DNo: " + resultEntryVOObj.getRequisitionDNo());
                            Log(Level.INFO, "testReq DNo status update in hivt_req_dtl:" + resultEntryVOObj.getRequisitionDNo()+"isDeptEntry"+resultEntryVOGroupByValidatedByObj.getIsDeptEntry());
                            Log(Level.INFO, "pdf file datails: " + resultEntryVOGroupByValidatedByObj.getPatCRNo() + " "
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
                        
                        //changed by ashu for sending email.
                        
                        String email = null;
                        String fname = null;
                        String mname = null;
                        String lname = null;
                        String message = null;
                        
                        //message = "DISCLAIMER: PLEASE DO NOT REPLY TO THIS MAIL. THIS IS AN AUTO GENERATED MAIL AND REPLIES TO THIS EMAIL ID ARE NOT ATTENDED TO. FOR ANY QUERIES OR CLARIFICATIONS PLEASE CALL US AT THE CONTACT NUMBERS PROVIDED AT OUR WEBSITE nims.edu.in OR VISIT NIMS.";
                        
                        query = QueryConfig.FETCH_DATA;
                        PreparedStatement pstmt3 = conn.prepareStatement(query);
                        pstmt3.setString(1, resultEntryVOGroupByValidatedByObj.getPatCRNo());
                        rs1 = pstmt3.executeQuery();
                        
                        if(rs1.next()){
                        	email = rs1.getString("hrgstr_email_id");
                        	fname = rs1.getString("hrgstr_fname");
                        	mname = rs1.getString("hrgstr_mname");
                        	lname = rs1.getString("hrgstr_lname");
                        }
                        	
                        
                        if (pstmt3 != null) {
                            pstmt.close();
                        }
                        
                        
                        if(email != null){
                        	
                        	System.out.println("Send Mail to "+email+ ".");
                        	
                        	if(fname != null){
                        		if(mname != null){
                            		if(lname != null){
                            			message = "Dear "+fname+ " "+mname+" "+lname+",\r\n\r\n"
                                				+"The report as requested by you is attached herewith. Thank You for providing us an opportunity to serve you. " 
                                				+"\r\n\r\nRegards"+
                                				"\r\nNizam's Institute of Medical Sciences \r\n"
                                				+ "Telephone No.: 040-23489000   Fax No.: 040-23310076";
                            		}else{
                            			message = "Dear "+fname+ " "+mname+",\r\n\r\n"
                                				+"The report as requested by you is attached herewith. Thank You for providing us an opportunity to serve you. " 
                                				+"\r\n\r\nRegards"+
                                				"\r\nNizam's Institute of Medical Sciences \r\n"
                                				+ "Telephone No.: 040-23489000   Fax No.: 040-23310076";
                            		}
                            	}else{
                            		if(lname != null){
                            			message = "Dear "+fname+ " "+lname+",\r\n\r\n"
                                				+"The report as requested by you is attached herewith. Thank You for providing us an opportunity to serve you. " 
                                				+"\r\n\r\nRegards"+
                                				"\r\nNizam's Institute of Medical Sciences \r\n"
                                				+ "Telephone No.: 040-23489000   Fax No.: 040-23310076";
                            		}else{
                            			message = "Dear "+fname+",\r\n\r\n"
                                				+"The report as requested by you is attached herewith. Thank You for providing us an opportunity to serve you. " 
                                				+"\r\n\r\nRegards"+
                                				"\r\nNizam's Institute of Medical Sciences \r\n"
                                				+ "Telephone No.: 040-23489000   Fax No.: 040-23310076";
                            		}
                            	}
                        		
                        	}else{
                        		message = "The report as requested by you is attached herewith. Thank You for providing us an opportunity to serve you. " 
                        				+"\r\n\r\nRegards"+
                        				"\r\n-Nizam's Institute of Medical Sciences \r\n"
                        				+ "Telephone No.: 040-23489000   Fax No.: 040-23310076";
                        	}
                        	
                        	
                        	message = message + "\r\n\r\nDISCLAIMER: PLEASE DO NOT REPLY TO THIS MAIL. THIS IS AN AUTO GENERATED MAIL AND REPLIES TO THIS EMAIL ID ARE NOT ATTENDED TO. FOR ANY QUERIES OR CLARIFICATIONS PLEASE CALL US AT THE CONTACT NUMBERS PROVIDED AT OUR WEBSITE nims.edu.in OR VISIT NIMS.";
                        	
                        	byte[] pdf = null;
                        	//OutputStream output = null;
                        	ByteArrayOutputStream output = new ByteArrayOutputStream();
                            
                            pdf = MongoXmlHandler.getInstance().latestFetchFileByte(pdfFileName);
                            
                            PdfReader reader=null;
                    		PdfStamper stamp =null;
                    		
                    		reader=new PdfReader(pdf);
               			 	int n = reader.getNumberOfPages();
               			 	stamp = new PdfStamper(reader, output);
               			 	int i = 1;
               			 	PdfContentByte under ;
               			 	PdfContentByte over;
               			 	
               			 	Image img = Image.getInstance(PropertiesHelper.getReportHeaderPath());
            		     
            		     //img.setBackgroundColor(Color.white);		      
               			 	//BaseFont bf1 = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI, BaseFont.EMBEDDED);
               			 	//img.setAbsolutePosition(20, 735);
               			 	img.setAbsolutePosition(20, 747);
               			 	
               			 	BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI, BaseFont.EMBEDDED);
               			 	
               			 while (i <= n) 
               			 {
           		    	  
               				 over = stamp.getOverContent(i);
               				 over.beginText();
               				 over.setFontAndSize(bf, 11);
               				 over.setTextMatrix(30, 30);
               				 //over.showText("page " + i +" of "+ n);
           		          
               				 String ove="Page " + i +" of "+ n;
               				 //over.setFontAndSize(bf, 25);
               				 
               				 over.showTextAligned(Element.ALIGN_BOTTOM, ove, 250f, 30f, 0f);
	           		          
               				 over.addImage(img);  
               				 over.setColorFill(new Color(0xC0, 0xC0, 0xC0));
               				 over.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, false), 54);
               				 
               				 over.endText();
           		       
               				 // over.endMarkedContentSequence();
               				 // Watermark under the existing page
           		        
               				 /*under = stamp.getUnderContent(i);
               				 under.addImage(img);  
               				 under.setColorFill(new Color(0xC0, 0xC0, 0xC0));
               				 under.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, false), 54);*/
           		        
           		        
           		        //under.showTextAligned(Element.ALIGN_CENTER, "Duplicate Report", 295f, 552f, 40f);
           		        
           		        // Text over the existing page under.showTextAlignedKerned(alignment, text, x, y, rotation);
	           		        over = stamp.getOverContent(i);
	           		        over.beginText();       
	           		        over.setFontAndSize(bf, 18);        
	           		        //over.showText("This Is Duplicate File");over.showTextAlignedKerned(0,"This Is Duplicate File", 20, 800, 0); over.showText("page " + i);
	           		        over.endText();    
	           		        i++;
               			 }
           		    
               			 stamp.setFormFlattening(true);
               			 stamp.close();
            		    
                            
                          System.out.println("Starting to send mail..");
                          HttpServletRequest request = null ;
                          pdf = output.toByteArray();
                          String base64bytearray = org.apache.commons.codec.binary.Base64.encodeBase64String(pdf);
                         
                          //commented by ashu -- mail sending process.
                          
                          // SendMessageToUser.SendEmail("Patient Report: NIMS", message,email,base64bytearray, request);
                          
                          
                       }
                        
                        
                    }
                    //  encryptedPdfFile(pdfFileurl, pdfFileName, "administrator");
                } catch (Exception ex) {
                   // ex.printStackTrace();
                    Log(Level.SEVERE, ex);
                    if (conn != null) {
                        conn.rollback();
                    }
                    
                    //ex.printStackTrace();
                } finally {
                    try {

                        if (cstmt != null) {
                            cstmt.close();
                        }
                        
                        if (rs1 != null) {
                        	rs1.close();
                        }

                    } catch (SQLException sqex) {
                        //sqex.printStackTrace();
                        Log(Level.SEVERE, sqex);
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
            //e.printStackTrace();
            Log(Level.SEVERE, e);
            if(conn != null)
                conn.rollback();
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
                        System.out.println("samplecode"+rs.getString("samplecode"));
                        resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"),rs.getString("samplecode"));
                        DataHandler.populateVOfrmRS(resultEntryVO, rs);

                        resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                        DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                        if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                            resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                        }
                        System.out.println("reqdno list add for finding xml from mongo :"+ resultEntryVO.getPatCRNo()+":" +resultEntryVO.getRequisitionDNo());
                        Log(Level.INFO, "reqdno list add for finding xml from mongo :"+ resultEntryVO.getPatCRNo()+":" +resultEntryVO.getRequisitionDNo());
                        resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);
                        workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);

                        // if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                     
                        checkMap.put(resultEntryVO.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode()+resultEntryVO.getSampleCode(), resultEntryVOGroupByValidatedBy);
                        //  }

                    } else {
                        if (((resultEntryVO.getRequisitionNo()).equals(rs.getString("requisitionNo")) == true && resultEntryVO.getSampleCode().equals(rs.getString("samplecode")) == true)) {// && (resultEntryVO.getLaboratoryCode().equals(rs.getString("laboratoryCode")) == true) && (resultEntryVO.getTestCode().equals(rs.getString("testCode")) == true)) {                            
                        	System.out.println("samplecode-"+rs.getString("samplecode"));
                        	resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                            DataHandler.populateVOfrmRS(resultEntryVO, rs);
                            if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode()+resultEntryVO.getSampleCode())) {
                                resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode()+resultEntryVO.getSampleCode());
                            } else {
                                resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                System.out.println("reqdno list add for finding xml from mongo :"+ resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                Log(Level.INFO, " new reqdno list add for finding xml from mongo :"+resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                // if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode()+resultEntryVOGroupByValidatedBy.getSampleCode(), resultEntryVOGroupByValidatedBy);
                                // }
                            }

                            if(resultEntryVOGroupByValidatedBy!=null)
                            if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                                resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                            }
                            System.out.println("reqdno list add for finding xml from mongo :"+ resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                            Log(Level.INFO, "reqdno list add for finding xml from mongo :"+ resultEntryVO.getPatCRNo()+":" +resultEntryVO.getRequisitionDNo());
                            resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);

                        } else {

                            //resultEntryVO=new ResultEntryVO();	CACHING IMPLEMENTATION
                        	System.out.println("samplecode--"+rs.getString("samplecode"));
                        	resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                            DataHandler.populateVOfrmRS(resultEntryVO, rs);
                            if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode()+resultEntryVO.getSampleCode())) {
                                resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode()+resultEntryVO.getSampleCode());
                            } else {
                                resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                //  if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode()+resultEntryVO.getSampleCode(), resultEntryVOGroupByValidatedBy);
                                // }
                            }

                            if(resultEntryVOGroupByValidatedBy!=null)
                            if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                                resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                            }

                            if(resultEntryVOGroupByValidatedBy!=null)
                            {
                            	 System.out.println("reqdno list add for finding xml from mongo :"+ resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                            Log(Level.INFO, "reqdno list add for finding xml from mongo :"+ resultEntryVO.getPatCRNo()+":" +resultEntryVO.getRequisitionDNo()); 	
                            resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);
                            }
                        }

                    }

                }
            }

        } catch (Exception e) {
            Log(Level.SEVERE, e);
            //e.printStackTrace();
        }

        Log(Level.INFO, "END" + workOrderGroupListForresultEntry.size());

        return workOrderGroupListForresultEntry;
    }

    private List<ResultEntryVOGroupByValidatedBy> processTheWorkOrdersToGroups(List<ResultEntryVOGroupByValidatedBy> workOrderGroupListForresultEntry) {

        List<ResultEntryVOGroupByValidatedBy> resultEntryVOGroupByValidatedByList = new ArrayList<ResultEntryVOGroupByValidatedBy>();
        Map<String, Integer> fileGroupMap = new HashMap<String, Integer>();

        for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy : workOrderGroupListForresultEntry) {
            //if group belongs to a group code

            {
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
         //   Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::1");
            // if work order is already printed in a file
            // if work order is very new to the printing
            {
               // Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::4");
                // work order has a printing template associated with it
                if (resultEntryVO.getPrintedWithTemplateID() != null
                        && resultEntryVO.getPrintedWithTemplateID().equals("") == false) {
                 //   Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::5");
                    // find the group which has printing template associated with it
                    findThePrintingTemplateId(fileGroupMap, resultEntryVO, 1, resultEntryVOGroupByValidatedByList);

                } else {
                 //   Log(Level.INFO, "processingTheWorkOrdersWhichbelongstogrouporNot::6");
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
           // Log(Level.INFO, "groupingTheGivenWorkOrderToTheRegiseredPatientGroup i=" + i);
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
                   /* Log(Level.INFO, "groupingTheGivenWorkOrderToTheRegiseredPatientGroup i=" + i
                            + " isContainable::" + isContainable);*/
                    groupByValidatedBy = groupingVO;
                } else // if this template is not containable in the mapping group find the other group which represents same printing template id 
                {
                    /*Log(Level.INFO, "groupingTheGivenWorkOrderToTheRegiseredPatientGroup i=" + i
                            + " isContainable::" + isContainable);
                    Log(Level.INFO, "this template is not containable in the mapping group find the other group "
                            + "which represents same printing template id");
*/
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
                   // Log(Level.INFO, "no containable group can be found");
                    groupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                 //   Log(Level.INFO, "new.... groupByValidatedBy created....");
                    DataHandler.populate(groupByValidatedBy, resultEntryVO2);
                    //check this part::XXXXXXXXXXXXXXXXXXXXXX
                    resultEntryVOGroupByValidatedByList.add(groupByValidatedBy);
                   //
                    //Log(Level.INFO, "new.... groupByValidatedBy added....");
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

        } catch (Exception e) {
            //e.printStackTrace();
            Log(Level.SEVERE, e);
        } finally {
            try {
                conn.commit();
                conn.close();
            } catch (Exception e) {
                Log(Level.SEVERE, e);
                //e.printStackTrace();
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
