/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package new_investigation.reportGenerator.DataProcessing;

import new_investigation.reportGenerator.DataHelper.Config;
import new_investigation.reportGenerator.DataHelper.PGDataHelper;
import new_investigation.reportGenerator.DataHelper.PropertiesHelper;
import new_investigation.reportGenerator.DataHelper.QueryConfig;
/*import new_investigation.reportGenerator.DataHelper.ServiceConfiguration;*/
import new_investigation.reportGenerator.Logging.ServiceLogger;
import new_investigation.reportGenerator.MongoHelper.MongoXmlHandler;
/*import new_investigation.reportGenerator.TemplateHelper.TriStringObject;*/
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVO;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
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

import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Document;

/*import MailSender.SendMessageToUser;*/

import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.sun.corba.se.impl.ior.OldPOAObjectKeyTemplate;


/**
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
@SuppressWarnings("unused")
public class PGPDFProcessing {

    public boolean processingData(ResultEntryVO resultEntryVO) {
    	
    	
        boolean jobprocessContinue = false;

        String processingData = null;
        String processinglaboratoryCode = null;
        String requisitionNo = null;
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

            hospitalCode=resultEntryVO.getHospitalCode();
            processingData=resultEntryVO.getPatCRNo();
    		processinglaboratoryCode=resultEntryVO.getLaboratoryCode();
    		requisitionNo=resultEntryVO.getRequisitionNo();
    		isDeptEntry=resultEntryVO.getIsDeptEntry();
            
            if (processingData != null) {
            	
           	//  synchronized (processingData) {
                  
                jobprocessContinue = true;

                Log(Level.INFO, "Calling count:::::::::::::::::::::::::::::::::" + processingData);
                
                processingToGenerateReport(conn, hospitalCode, processinglaboratoryCode, processingData, false,isDeptEntry, requisitionNo);
                /*cstmt = conn.prepareCall("{call inv_reportengine.removeSingleDataforjob(?,?,?,?)}");
                 cstmt.setString(1, processinglaboratoryCode);
                 cstmt.setString(2, InvestigationStaticConfigurator.servernumber);
                 cstmt.setString(3, processingData);
                 cstmt.setString(4, hospitalCode);
                 cstmt.execute();*/
         //  	  }
            	  } else {
                jobprocessContinue = false;
            }
            
            

        } catch (Exception e) {
            e.printStackTrace();
            jobprocessContinue = false;
        } finally {
            try {
            	/*
                if (cstmt != null) {
                    cstmt.close();
                }
				*/
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

    public void processingToGenerateReport(Connection conn, String hospitalCode, String labCode, String crOrRequisitionNo, boolean compulsory,String isDeptEntry, String reqnNumber) {
        List<ResultEntryVOGroupByValidatedBy> workOrderGroupListForresultEntry = new ArrayList<ResultEntryVOGroupByValidatedBy>();
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            List<ResultEntryVOGroupByValidatedBy> reportGroupByList = new ArrayList<ResultEntryVOGroupByValidatedBy>();
            
            //String isgroup="0";
            
           // if(!reqnNumber.equals("")) {
            
            	 CallableStatement cstmt1 = null;
                 ResultSet rs1 = null;
                	  
            	 cstmt1 = conn.prepareCall(QueryConfig.P_GET_PDF_WORKORDERLISTDATA_NEW_PM);
               
            	 cstmt1.setString(1, labCode);
                 cstmt1.setString(2, crOrRequisitionNo);
                 cstmt1.setString(3, hospitalCode);
                 cstmt1.registerOutParameter(4, Types.REF);
                 cstmt1.registerOutParameter(5, Types.VARCHAR);
                 cstmt1.registerOutParameter(6, Types.VARCHAR);
                 cstmt1.registerOutParameter(7, Types.VARCHAR);
                 cstmt1.setString(8, isDeptEntry);
                 cstmt1.setString(9, reqnNumber);
                 cstmt1.execute();
                 
                 Log(Level.INFO, "pprocessingToGenerateReport CR Nos::" + crOrRequisitionNo);
                 Log(Level.INFO, "processingToGenerateReport REPORTING ENGINGE QUERY-->INV_REPORTENGINE.getWorkOrderList::" + cstmt1.getString(5) + cstmt1.getString(6) + cstmt1.getString(7));
                 rs1 = (ResultSet) cstmt1.getObject(4);
                 
                 workOrderGroupListForresultEntry = new ArrayList<ResultEntryVOGroupByValidatedBy>();
                 
                 workOrderGroupListForresultEntry = resultsetProcessingForGroupingWorkOrder(rs1, workOrderGroupListForresultEntry);

                 
                 reportGroupByList = workOrderGroupListForresultEntry;
                 
            //}
            
            Log(Level.INFO, "processingToGenerateReport After reportGroupByList  size ::" + reportGroupByList.size());

           
            if (reportGroupByList != null && reportGroupByList.size() != 0) {
                Log(Level.INFO, "processingToGenerateReport reportGroupByList:::" + reportGroupByList.size());
               

                if (reportGroupByList != null && reportGroupByList.size() != 0) {
                	
                	Log(Level.INFO, "htmlcreate::::::::::::::" + reportGroupByList.size());
                	
                    PrintingHelper.createHTMLReportForListOfWorkOrders(reportGroupByList, compulsory);
                }

                Log(Level.INFO, "processingToGenerateReport reportGroupByList  size ::" + reportGroupByList);
                
                boolean flg=PropertiesHelper.getISFtporMongo();
                
            	if(flg)
            	printReportInPdfFormat(reportGroupByList, hospitalCode, conn);
            	else
            	printReportInPdfFormatFTP(reportGroupByList, hospitalCode, conn);
            
            }

        } catch (Exception e) {
            try {
            	e.printStackTrace();
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

    public boolean printReportInPdfFormatFTP(List<ResultEntryVOGroupByValidatedBy> reportGroupByList, String hospitalCode, Connection conn) throws SQLException {
        List<ResultEntryVOGroupByValidatedBy> registeredValidatedByList = null;
        List<ResultEntryVOGroupByValidatedBy> unRegisteredValidatedByList = null;

        
        boolean flag = false;
        CallableStatement cstmt = null;
        for (ResultEntryVOGroupByValidatedBy resBy : reportGroupByList) {
        	
            Log(Level.INFO, "counterrrrrrrrr::" + reportGroupByList.size());

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
            String dnoo="";
            for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj : registeredValidatedByList) {
            	
            	 if( resultEntryVOGroupByValidatedByObj.getHeaderheight()==0.0 && resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("1"))
                 {
            		 
            		 String dynamicTemplateID = PGDataHelper.getInstance().getDynamicTemplateStatus(resultEntryVOGroupByValidatedByObj.getTestCode(), resultEntryVOGroupByValidatedByObj.getHospitalCode(), false, resultEntryVOGroupByValidatedByObj.getLaboratoryCode());
         			
            		 if( dynamicTemplateID!=null && !dynamicTemplateID.isEmpty() && !dynamicTemplateID.equals("") && !dynamicTemplateID.equals("-1"))
                     {
            			 resultEntryVOGroupByValidatedByObj.setHeaderheight(205.0f);
                     }
            		 else
            		 {
            			 resultEntryVOGroupByValidatedByObj.setHeaderheight(195.0f);
            		 }
            		 
                 	
                 }
            	 else
            	 {
            		 
            		 
            			String dynamicTemplateID = PGDataHelper.getInstance().getDynamicTemplateStatus(resultEntryVOGroupByValidatedByObj.getTestCode(), resultEntryVOGroupByValidatedByObj.getHospitalCode(), false, resultEntryVOGroupByValidatedByObj.getLaboratoryCode());
            			
            		 if( dynamicTemplateID!=null && !dynamicTemplateID.isEmpty() && !dynamicTemplateID.equals("") && !dynamicTemplateID.equals("-1"))
                     {
            			 resultEntryVOGroupByValidatedByObj.setHeaderheight(190.0f);
                     }
            		 else
            		 {
            		 resultEntryVOGroupByValidatedByObj.setHeaderheight(180.0f);
            		 }
                 }
                 
            	 
            	if(resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo()!=null)
            	{
            		System.out.println("22");
            		if(dnoo.equals(""))
            		dnoo=resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo()==null?"":resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo();
            		else
            			dnoo+="#"+resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo()==null?"":"#"+resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo();	
            	}
            	
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
                    


                     InputStream oldFilePdf=null;
                    if(resultEntryVOGroupByValidatedByObj.getOldReportUrl()!=null && !resultEntryVOGroupByValidatedByObj.getOldReportUrl().equals("-"))
                    {
                    
                    	
                    
                    	boolean flg=PropertiesHelper.getISFtporMongo();
                        
                    	if(flg)
                    	{
                        	//oldFilePdf=MongoXmlHandler.getInstance().latestFetchFileByte(resultEntryVOGroupByValidatedByObj.getOldReportUrl());

                    	}
                    	else
                    	{
                    		
                    		String strPdfName=resultEntryVOGroupByValidatedByObj.getOldReportUrl();
                    		
                    		 String crNo=strPdfName.substring(0,15);
     						System.out.println(strPdfName.substring(5,7));
     						String year=    crNo.substring(5,7); //MergeAllPdfNewInv.getYear(crNo); 
     					   String insideyear=PGDataHelper.getInsideYear(crNo);
     				         String count=PGDataHelper.getcount(crNo);
     				         
     				     String ftpfilepath=PropertiesHelper.getFTPConnectionURI()+"/"+resultEntryVOGroupByValidatedByObj.getHospitalCode() +"/"+"20"+year+"/"+insideyear+"/"+count+"/"+resultEntryVOGroupByValidatedByObj.getPatCRNo()+"/"+resultEntryVOGroupByValidatedByObj.getOldReportUrl();
     				     	URL urlftp=null;
     						URLConnection urlc=null;
     						
     						try
     						{

     							System.out.println("ftpfilepath Invest common process :"+ftpfilepath);
     							  
     							   urlftp=new URL(ftpfilepath);
     							  urlc=urlftp.openConnection();
     								oldFilePdf=urlc.getInputStream();
     							
     							
     							
     						}
     						catch(Exception ex)
     						{
     							System.out.println("<!-- CreateRequisitionDirectory -->");
     							//ex.printStackTrace();
     						}
     					
     						
                    	}
                    	
                    	
                    
                    }
                    System.out.println("ashuchk-1");
                    
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
                    
                   /* if( resultEntryVOGroupByValidatedByObj.getHeaderheight()==0.0)
                    {
                    	resultEntryVOGroupByValidatedByObj.setHeaderheight(210.0f);
                    }*/
                    
                    if(resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0"))
                    {
                    	
                    	String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                    	                    	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                    }
                    else
                    {
                    if(resultEntryVOGroupByValidatedByObj.getIsdynamicGroupTemplate().equals("0") && resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate().equals("0"))
                    {
                    if (resultEntryVOGroupByValidatedByObj.getPrintingType() != null && resultEntryVOGroupByValidatedByObj.getPrintingType().equals("1")) {
                        //headerString.append(lineString);
                    	if(resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate()!=null && resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate().equals(1))
                    	{
                    		
                    		if(resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0"))
                            {
                            	
                            	
                    			String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                            	                            	
                            	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                            }
                    		else
                    		{
                            	String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                        	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));
                    		}
                    			
                    	}
                    	else
                    	{
                    		
                    		if(resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0"))
                            {
                            	
                            	
                    			String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                            	                            	                       	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                            }
                    		else
                    		{

                            	String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                      	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                    		}
                    	}	
                    	
                    	// headerString.append(standardRangesString);
                       // resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 25));
                    } else {
                    	if(resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0"))
                        {
                        	

                    		String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                        	                     	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                        }
                		else
                		{
                			

                        	String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                    	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                			
                		
                		}
                       // resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
                    }
                    }
                    else
                    {
                        if (resultEntryVOGroupByValidatedByObj.getPrintingType() != null && resultEntryVOGroupByValidatedByObj.getPrintingType().equals("1")) {
                           // headerString.append(lineString);
                        	if(resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0"))
                            {
                            	
                            	
                        		String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                            	                         	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                     		
                            }
                    		else
                    		{

                            	String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                            	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                    		}
                            //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
                        } else {
                        	if(resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0"))
                            {
                            	
                        		String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                            	                         	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));
	
                            }
                    		else
                    		{
                            	String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                             	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                    			
                    		}
                            //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
                        }
                    	
                    	
                    	
                    }
                    }
                    
                    String crNo=resultEntryVOGroupByValidatedByObj.getPatCRNo();
                    // System.out.println(crNo.substring(5,7));
 					String year=    crNo.substring(5,7);
                     
                     String insideyear=PGDataHelper.getInsideYear(crNo);
                     String count=  PGDataHelper.getcount(crNo);
                    String pdfFtpurl = PropertiesHelper.getFTPConnectionURI() +"/"+resultEntryVOGroupByValidatedByObj.getHospitalCode() +"/"+"20"+year+"/"+insideyear+"/"+count+"/"+resultEntryVOGroupByValidatedByObj.getPatCRNo();
                    String pdfFileurl= PropertiesHelper.getFTPConnectionURI() +"/"+resultEntryVOGroupByValidatedByObj.getHospitalCode() +"/"+"20"+ year+"/"+insideyear+"/"+count+"/"+resultEntryVOGroupByValidatedByObj.getPatCRNo();
                    System.out.println("pdfFileurl"+pdfFileurl);
                    
                    String saveStatus ="";
                    boolean flg=PropertiesHelper.getISFtporMongo();
                    
                	if(flg)
                	{
                		//saveStatus = SavePDF.saveFileToLocation(resultEntryVOGroupByValidatedByObj.getHeader(), resultEntryVOGroupByValidatedByObj.getFooter(), resultEntryVOGroupByValidatedByObj.getGroupTemplateString(), pdfFileName, Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterHeight()), resultEntryVOGroupByValidatedByObj.getHeaderheight(), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getHeaderWidth()), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterWidth()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : resultEntryVOGroupByValidatedByObj.getPageSize()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : Config.pagewidthprinting), oldFilePdf,resultEntryVOGroupByValidatedByObj.getFileuploaddata(),resultEntryVOGroupByValidatedByObj.getIsfileuploaddadd(),resultEntryVOGroupByValidatedByObj.getIsfirstpagereq());
                	}
                	else
                	{
                	
                		 saveStatus =  SavePDF.saveFileToFTPLocationnew(resultEntryVOGroupByValidatedByObj.getHeader(), resultEntryVOGroupByValidatedByObj.getFooter(), resultEntryVOGroupByValidatedByObj.getGroupTemplateString(), pdfFileName, Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterHeight()), resultEntryVOGroupByValidatedByObj.getHeaderheight(), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getHeaderWidth()), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterWidth()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : resultEntryVOGroupByValidatedByObj.getPageSize()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : Config.pagewidthprinting), oldFilePdf,resultEntryVOGroupByValidatedByObj.getFileuploaddata(),resultEntryVOGroupByValidatedByObj.getIsfileuploaddadd(),resultEntryVOGroupByValidatedByObj.getIsfirstpagereq(),pdfFtpurl,pdfFileurl,resultEntryVOGroupByValidatedByObj);
                	}
                    
                    
                    
                    //resultEntryVOGroupByValidatedByObj.setPdfFtpUrl(pdfFileurl + "/" + pdfFileName);
                    resultEntryVOGroupByValidatedByObj.setPdfFileName(pdfFileName);

                    
					  String strPdfPath = PropertiesHelper.getFTPConnectionURI()+"/"+pdfFileName.substring(0,5)+"/"+ "20"+year+"/"+insideyear+"/"+count+"/"+pdfFileName.substring(0,15)+"/"+pdfFileName;

						
						URL urlftp1 =new URL(strPdfPath);
						URLConnection urlc1=	urlftp1.openConnection();
			            
			            
			            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
						InputStream is = null;
						try {
						  is = urlftp1.openStream ();
						  byte[] byteChunk = new byte[4897]; // Or whatever size you want to read in at a time.
						  int n;

						  while ( (n = is.read(byteChunk)) > 0 ) {
							  baos1.write(byteChunk, 0, n);
						  }
						}
						catch (IOException e) {
						  System.err.printf ("Failed while reading bytes from %s: %s", urlftp1.toExternalForm(), e.getMessage());
						  e.printStackTrace ();
						  // Perform any other exception handling that's appropriate.
						}
						finally {
						  if (is != null) { is.close(); }
						}

				
						
			            byte[] pdfData1 = baos1.toByteArray();
			            
						
				    
                   // pdfnew = MongoXmlHandler.getInstance().latestFetchFileByte(pdfFileName);
                    String base64bytearraynew = org.apache.commons.codec.binary.Base64.encodeBase64String(pdfData1);
                    
                    
                    
                    List<String> patDetails = PGDataHelper.getInstance().getPatientDetails(resultEntryVOGroupByValidatedByObj.getPatCRNo());
                    //System.out.println("MNo : "+patDetails.get(0)+" : email : "+patDetails.get(1)+" : name : "+patDetails.get(3));
                    String patContactNo= patDetails.get(0);
                    String patName= patDetails.get(3);
                    
                    
                    if (saveStatus != null) {
                        String query = QueryConfig.Q_SAVE_PDF_WORKORDERS;
                        //chanks
                         cstmt = null;
                         ResultSet rs = null;
                        String queryforTest = QueryConfig.Q_GET_REQ_DTL_STATUS_HIVT_REQ_DTL;
                          
                        // conn = PGDataHelper.getInstance().getConnection();
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        PreparedStatement pstmt2 = conn.prepareStatement(QueryConfig.Q_UPDATE_REQ_STATUS);

                   //     PreparedStatement pstmt10 = conn.prepareStatement(QueryConfig.Q_UPDATE_REQ_STATUS_DAILY_TABLE_DELETE_DNO_DUPLICATE);
                    //    PreparedStatement pstmt11 = conn.prepareStatement(QueryConfig.Q_UPDATE_REQ_STATUS_DAILY_TABLE);
                        PreparedStatement pstmt10 = conn.prepareStatement(QueryConfig.Q_hivt_report_dtl_daily_TABLE);
                        PreparedStatement pstmt11 = conn.prepareStatement(QueryConfig.Q_UPDATE_hivt_report_dtl_hst_TABLE);
                        
                        PreparedStatement pstmt12 = conn.prepareStatement(QueryConfig.Q_UPDATE_hivt_report_dtl_daily_DELETE_DNO_DUPLICATE);
                        PreparedStatement pstmt13 = conn.prepareStatement(QueryConfig.Q_UPDATE_hivt_report_dtl_hst_DELETE_DNO_DUPLICATE);

                        PreparedStatement pstmt14 = conn.prepareStatement(QueryConfig.Q_UPDATE_hivt_report_dtl_daily_HISTBL);
                        PreparedStatement pstmt15 = conn.prepareStatement(QueryConfig.Q_UPDATE_hivt_report_dtl_hst_HISTBL);
                        
                       
                        //new
                        PreparedStatement pstmt33 =null;
                        PreparedStatement pstmt4 =null;
                        if(PropertiesHelper.is_data_insert_hprms())
                        {
                         pstmt33 = conn.prepareStatement(QueryConfig.Q_SAVE_INV_DTL_LOG);
                        pstmt4 = conn.prepareStatement(QueryConfig.Q_SAVE_INV_DTL_GROUP_LOG);
                      
                        }
                        
                        PreparedStatement pstmt5 = conn.prepareStatement(QueryConfig.Q_UPDATE_REQ_STATUS_ALL);

                        if(resultEntryVOGroupByValidatedByObj.getIsDeptEntry().equals("1"))
                        	pstmt2 = conn.prepareStatement(QueryConfig.Q_UPDATE_DEPT_REQ_STATUS);
                        

                    	
                    	Map<String, String> mp=new HashMap<String, String>();

                    	
                        for (ResultEntryVO resultEntryVOObj : resultEntryVOGroupByValidatedByObj.getResultEntryVOListValidatedBy()) {
                        	


                        	
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
                            
                            if(dnoo.contains(resultEntryVOObj.getRequisitionDNo()))
                        	{
                            pstmt2.setString(1, resultEntryVOGroupByValidatedByObj.getPdfFileName());
                            pstmt2.setString(2, resultEntryVOObj.getRequisitionDNo());
                             pstmt2.executeUpdate();
                          
                             
                             
                           
                            
                             
                     /*        pstmt10.setString(1, resultEntryVOObj.getRequisitionDNo());
                             pstmt10.executeUpdate();
                           
                       
                             pstmt11.setString(1, resultEntryVOObj.getRequisitionDNo());
                             pstmt11.executeUpdate();
                       */
                       
                             pstmt12.setString(1, resultEntryVOObj.getRequisitionDNo());
                             pstmt12.executeUpdate();
                             
                             
                             pstmt10.setString(1, resultEntryVOObj.getRequisitionDNo());
                             pstmt10.executeUpdate();
                           
                       
                             pstmt13.setString(1, resultEntryVOObj.getRequisitionDNo());
                             pstmt13.executeUpdate();
                       
                             pstmt11.setString(1, resultEntryVOObj.getRequisitionDNo());
                             pstmt11.executeUpdate();
                             
                             pstmt14.setString(1, resultEntryVOObj.getPatCRNo());
                             pstmt14.setString(2, resultEntryVOObj.getRequisitionDNo());
                             pstmt14.executeUpdate();
                       
                             
                             pstmt15.setString(1, resultEntryVOObj.getPatCRNo());
                             pstmt15.setString(2, resultEntryVOObj.getRequisitionDNo());
                             pstmt15.executeUpdate();
                       
                           
                             
                       
                             
                             System.out.println("Req DNo: " + resultEntryVOObj.getRequisitionDNo());
                            Log(Level.INFO, "testReq DNo status update in hivt_req_dtl:" + resultEntryVOObj.getRequisitionDNo()+"isDeptEntry"+resultEntryVOGroupByValidatedByObj.getIsDeptEntry());
                            Log(Level.INFO, "pdf file datails: " + resultEntryVOGroupByValidatedByObj.getPatCRNo() + " "
                                    + resultEntryVOObj.getRequisitionDNo() + " "
                                    + resultEntryVOGroupByValidatedByObj.getRequisitionNo() + " "
                                    + resultEntryVOGroupByValidatedByObj.getPdfFileName() + " "
                                    + resultEntryVOObj.getHospitalCode());
                        	
                            
                            
                            
                            try {
                            	//System.out.println("getPdfFileName : "+resultEntryVOGroupByValidatedByObj.getPdfFileName()+" pdfFileName : "+pdfFileName);
                            	//System.out.println("getGroupCode() : "+resultEntryVOGroupByValidatedByObj.getGroupCode());
                            	
                            	if(resultEntryVOObj.getGroupCode()==null)
                            	{
                            		
                            		if(PropertiesHelper.is_data_insert_hprms())
                                    {
                            	 pstmt33.setString(1, resultEntryVOObj.getPatCRNo());
                            	 pstmt33.setString(2, (InetAddress.getLocalHost().getHostAddress()).trim());
                            	 pstmt33.setString(3, resultEntryVOObj.getHospitalCode());
                            	 pstmt33.setString(4, resultEntryVOObj.getRequisitionNo());
                            	 pstmt33.setString(5, resultEntryVOObj.getRequisitionDNo());
                            	 pstmt33.setString(6, resultEntryVOObj.getPatVisitNo());
                            	 pstmt33.setString(7, resultEntryVOObj.getLaboratoryCode());
                            	 pstmt33.setString(8, resultEntryVOObj.getLaboratoryName());
                            	 pstmt33.setString(9, resultEntryVOObj.getTestCode());
                            	 pstmt33.setString(10, resultEntryVOObj.getTestName());
                            	 pstmt33.setString(11, base64bytearraynew);
                            	 pstmt33.setString(12, resultEntryVOObj.getAddendumRemark());
                            	 pstmt33.setString(13, patDetails.get(0));
                            	 pstmt33.setString(14, patDetails.get(1));
                            	 pstmt33.setString(15, patDetails.get(2));
                            	 pstmt33.setString(16, resultEntryVOObj.getCollDate());
                            	 pstmt33.setString(17, resultEntryVOObj.getRequisitionDate());
                                 	
                            	 pstmt33.executeUpdate();
                                    } 
                             
                            	}
                            } catch (Exception ex) {
                                //sqex.printStackTrace();
                                Log(Level.SEVERE, ex);
                            }
                            
                            try {
                            	
                            	if(resultEntryVOGroupByValidatedByObj.getGroupCode()!=null)
                            	{
                            		
                            		if(mp.get(resultEntryVOGroupByValidatedByObj.getRequisitionNo()+resultEntryVOGroupByValidatedByObj.getGroupCode())==null)
                            		{
                            		
                            			if(PropertiesHelper.is_data_insert_hprms())
                                        {		
                               pstmt4.setString(1, resultEntryVOGroupByValidatedByObj.getPatCRNo());
                                pstmt4.setString(2, (InetAddress.getLocalHost().getHostAddress()).trim());
                                pstmt4.setString(3, resultEntryVOGroupByValidatedByObj.getHospitalCode());
                                pstmt4.setString(4, resultEntryVOGroupByValidatedByObj.getRequisitionNo());
                                pstmt4.setString(5, resultEntryVOGroupByValidatedByObj.getRequisitionDNo());
                                pstmt4.setString(6, resultEntryVOGroupByValidatedByObj.getPatVisitNo());
                                pstmt4.setString(7, resultEntryVOGroupByValidatedByObj.getLaboratoryCode());
                                pstmt4.setString(8, resultEntryVOGroupByValidatedByObj.getLaboratoryName());
                                pstmt4.setString(9, resultEntryVOGroupByValidatedByObj.getGroupCode());
                                pstmt4.setString(10, resultEntryVOGroupByValidatedByObj.getGroupCode());
                                pstmt4.setString(11, resultEntryVOGroupByValidatedByObj.getHospitalCode());
                                pstmt4.setString(12, base64bytearraynew);
                                pstmt4.setString(13, resultEntryVOGroupByValidatedByObj.getAddendumRemark());
                                pstmt4.setString(14, patDetails.get(0));
                                pstmt4.setString(15, patDetails.get(1));
                                pstmt4.setString(16, patDetails.get(2));
                                pstmt4.setString(17, resultEntryVOGroupByValidatedByObj.getCollDate());
                                pstmt4.setString(18, resultEntryVOGroupByValidatedByObj.getRequisitionDate());
                                 	
                                pstmt4.executeUpdate();
                                        } 
                                
                            	
                    			mp.put(resultEntryVOGroupByValidatedByObj.getRequisitionNo()+resultEntryVOGroupByValidatedByObj.getGroupCode(), "1");
                    		}
                            		
                            	}
                            	
                            } catch (Exception ex) {
                                //sqex.printStackTrace();
                                Log(Level.SEVERE, ex);
                            }
                            
                            /*if (pstmt2 != null) {
                                pstmt2.close();
                            }*/
                            
                           /* List oldurlss=getPdfsrequisitiondtlrequisitionwisesamplewise(resultEntryVOGroupByValidatedByObj);
                            
                           
                            Date date1 = new Date();
                            SimpleDateFormat sdf1 = new SimpleDateFormat("MMddyyyyhhmmss.SSSa");
                            String formattedDate1 = sdf1.format(date1);
                            //System.out.println(formattedDate1); // 12/
                            String pdfFileName1 = resultEntryVOGroupByValidatedByObj.getPatCRNo() +".pdf";
                        
                             String pdfFileName1 = resultEntryVOGroupByValidatedByObj.getPatCRNo() + "_"
                                    + resultEntryVOGroupByValidatedByObj.getLaboratoryCode() + "_"
                                    + formattedDate1 + ".pdf";
                             
                             
                            if(oldurlss!=null && oldurlss.size()>0)
                            {

                            	 
                                List <byte[]> finalPdfArray= new ArrayList<byte[]>();
                                
                            	for(int i=0;i<oldurlss.size();i++)
                            	{
                            		String pdfname=oldurlss.get(i).toString();
                          byte[] oldFilePdf1=null;
                          oldFilePdf1=MongoXmlHandler.getInstance().latestFetchFileByte(pdfname);
                          if(oldFilePdf1!=null)
                          	finalPdfArray.add(oldFilePdf1);
                          
                            	}
                                String saveStatus1 = SavePDF.saveFileToLocation1(resultEntryVOGroupByValidatedByObj.getHeader(), resultEntryVOGroupByValidatedByObj.getFooter(), resultEntryVOGroupByValidatedByObj.getGroupTemplateString(), pdfFileName1, Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterHeight()), resultEntryVOGroupByValidatedByObj.getHeaderheight(), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getHeaderWidth()), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterWidth()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : resultEntryVOGroupByValidatedByObj.getPageSize()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : Config.pagewidthprinting), oldFilePdf,finalPdfArray);

                    	
                            }
                            
                            
                            pstmt5.setString(1, pdfFileName1);
                            pstmt5.setString(2, resultEntryVOObj.getRequisitionNo());
                            pstmt5.setString(3, resultEntryVOObj.getSampleNo());
                            pstmt5.executeUpdate();
                            System.out.println("Req DNo: " + resultEntryVOObj.getRequisitionDNo());
                            Log(Level.INFO, "testReq DNo status update in hivt_req_dtl:" + resultEntryVOObj.getRequisitionDNo()+"isDeptEntry"+resultEntryVOGroupByValidatedByObj.getIsDeptEntry());
                            Log(Level.INFO, "pdf file datails: " + resultEntryVOGroupByValidatedByObj.getPatCRNo() + " "
                                    + resultEntryVOObj.getRequisitionDNo() + " "
                                    + resultEntryVOGroupByValidatedByObj.getRequisitionNo() + " "
                                    + resultEntryVOGroupByValidatedByObj.getPdfFileName() + " "
                                    + resultEntryVOObj.getHospitalCode());*/
                        	
                            
                        	}
                            
                            
                        	}
                      
                        if(PropertiesHelper.is_data_insert_hprms())
                        {
                        if (pstmt33 != null) {
                            pstmt33.close();
                        }
                        if (pstmt4 != null) {
                            pstmt4.close();
                        }
                        
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
               int count=0;
        	String data=getreporttestforpagesepareation();
            while (rs.next()) {
                //if (rs.getString("isTestReady").equals("READY")) 
                {
                    if (resultEntryVO == null) {
                        Log(Level.INFO, " laboratoryCode::" + rs.getString("laboratorycode") + ",groupCode::" + rs.getString("groupcode") + ",testCode::" + rs.getString("testcode") + ",hospitalCode" + rs.getString("hospitalcode"));
                        System.out.println("samplecode"+rs.getString("samplecode"));
                        resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"),rs.getString("samplecode"));
                        DataHandler.populateVOfrmRS(resultEntryVO, rs);

                        resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                        DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                        if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                            resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                        }
                        System.out.println("reqdno list add for finding xml from mongo1 :"+ resultEntryVO.getPatCRNo()+":" +resultEntryVO.getRequisitionDNo());
                        Log(Level.INFO, "reqdno list add for finding xml from mongo1 :"+ resultEntryVO.getPatCRNo()+":" +resultEntryVO.getRequisitionDNo());
                        resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);
                        workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);

                        // if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                     if(data!=null && !data.equals("") && data.contains(resultEntryVO.getTestCode()))
                    	 checkMap.put(resultEntryVO.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode()+count, resultEntryVOGroupByValidatedBy);
                     else
                        checkMap.put(resultEntryVO.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode()+resultEntryVO.getSampleCode(), resultEntryVOGroupByValidatedBy);
                        //  }
                                    count++;
                    } else {
                        if (((resultEntryVO.getRequisitionNo()).equals(rs.getString("requisitionNo")) == true && resultEntryVO.getSampleCode().equals(rs.getString("samplecode")) == true)) {// && (resultEntryVO.getLaboratoryCode().equals(rs.getString("laboratoryCode")) == true) && (resultEntryVO.getTestCode().equals(rs.getString("testCode")) == true)) {                            
                        	System.out.println("samplecode-"+rs.getString("samplecode"));
                        	resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                            DataHandler.populateVOfrmRS(resultEntryVO, rs);
                        
                            if(data!=null && !data.equals("") && data.contains(resultEntryVO.getTestCode()))

                            {
                           
                            	if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode())) {
                            		   resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode());
                            	
                            		 /*  if(resultEntryVOGroupByValidatedBy==null)
                                       {
                                       	 resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                            System.out.println("reqdno list add for finding xml from mongo2 :"+ resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                            Log(Level.INFO, " new reqdno list add for finding xml from mongo2 :"+resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                            DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                            workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                            // if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                            checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode()+resultEntryVOGroupByValidatedBy.getSampleCode(), resultEntryVOGroupByValidatedBy);
                                            // }
                                       }*/
                            	}
                            	else
                            {
                            	
                            	resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                System.out.println("reqdno list add for finding xml from mongo2 :"+ resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                Log(Level.INFO, " new reqdno list add for finding xml from mongo2 :"+resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                // if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode()+count, resultEntryVOGroupByValidatedBy);
                                // }

                            }                            	
                            }
                            else
                            if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode()+resultEntryVO.getSampleCode())) {
                               
                            	
                            	
                            	resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode()+resultEntryVO.getSampleCode());
                            
                            	//chankswa 
                            	

                            	if(resultEntryVO.getIsfirstpagereq()!=null && !resultEntryVO.getIsfirstpagereq().equals("1"))
                            	{
                            		
                            	
                            	if(resultEntryVO.getIsfileuploaddadd()!=null && resultEntryVO.getIsfileuploaddadd().equals("1"))
                            	{
                            		resultEntryVOGroupByValidatedBy.setIsfileuploaddadd(resultEntryVO.getIsfileuploaddadd());
                            	}
                            	
                            	if(resultEntryVO.getFileuploaddata()!=null && !resultEntryVO.getFileuploaddata().equals(""))
                            	{
                            		resultEntryVOGroupByValidatedBy.setFileuploaddata(resultEntryVOGroupByValidatedBy.getFileuploaddata()+resultEntryVO.getFileuploaddata());
                            	}
                            	
                            	}
                            	
                                      /*  if(resultEntryVOGroupByValidatedBy==null)
                                        {
                                        	 resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                             System.out.println("reqdno list add for finding xml from mongo2 :"+ resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                             Log(Level.INFO, " new reqdno list add for finding xml from mongo2 :"+resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                             DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                             workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                             // if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                             checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode()+resultEntryVOGroupByValidatedBy.getSampleCode(), resultEntryVOGroupByValidatedBy);
                                             // }
                                        }*/
                            
                            } else {
                                resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                System.out.println("reqdno list add for finding xml from mongo2 :"+ resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                Log(Level.INFO, " new reqdno list add for finding xml from mongo2 :"+resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
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
                            System.out.println("reqdno list add for finding xml from mongo3 :"+ resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                            Log(Level.INFO, "reqdno list add for finding xml from mongo3 :"+ resultEntryVO.getPatCRNo()+":" +resultEntryVO.getRequisitionDNo());
                            resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);

                        } else {

                            //resultEntryVO=new ResultEntryVO();	CACHING IMPLEMENTATION
                        	System.out.println("samplecode--"+rs.getString("samplecode"));
                        	resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                            DataHandler.populateVOfrmRS(resultEntryVO, rs);
                           
                            if(data!=null && !data.equals("") && data.contains(resultEntryVO.getTestCode()))
                            {
                                
                            
                            if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode())) {
                                resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode()+count);
                         
                                /*if(resultEntryVOGroupByValidatedBy==null)
                                {
                                	 resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                     System.out.println("reqdno list add for finding xml from mongo2 :"+ resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                     Log(Level.INFO, " new reqdno list add for finding xml from mongo2 :"+resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                     DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                     workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                     // if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                     checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode()+resultEntryVOGroupByValidatedBy.getSampleCode(), resultEntryVOGroupByValidatedBy);
                                     // }
                                }*/
                                
                            } else {
                                resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                //  if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode()+count, resultEntryVOGroupByValidatedBy);
                                // }
                            }
                        }
                            else
                            if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode()+resultEntryVO.getSampleCode())) {
                                resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode()+resultEntryVO.getSampleCode());
                           
                            /*    if(resultEntryVOGroupByValidatedBy==null)
                                {
                                	 resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                     System.out.println("reqdno list add for finding xml from mongo2 :"+ resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                     Log(Level.INFO, " new reqdno list add for finding xml from mongo2 :"+resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
                                     DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                     workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                     // if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                     checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode()+resultEntryVOGroupByValidatedBy.getSampleCode(), resultEntryVOGroupByValidatedBy);
                                     // }
                                }*/
                            
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
	                            System.out.println("reqdno list add for finding xml from mongo4 :"+ resultEntryVO.getPatCRNo() +resultEntryVO.getRequisitionDNo());
	                            Log(Level.INFO, "reqdno list add for finding xml from mongo4 :"+ resultEntryVO.getPatCRNo()+":" +resultEntryVO.getRequisitionDNo()); 	
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

    
    
    public static List getPdfsrequisitiondtlrequisitionwisesamplewise(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		List finall=new ArrayList();
         List lsttest=new ArrayList();
         List lsttest1=new ArrayList();
		String query = QueryConfig.FETCH_PDFS_rquisitionno_samplecodewise;
		
		
		Connection conn = PGDataHelper.getInstance().getConnection();
		String filename = null;
		String crno = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null; 
		try
		{
			 pstmt = conn.prepareStatement(query);
			 pstmt.setString(1, resultEntryVOGroupByValidatedByObj.getRequisitionNo());
			 pstmt.setString(2, resultEntryVOGroupByValidatedByObj.getSampleNo());
			 int i=1;
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				
				filename = rs.getString(1);
				lsttest.add(filename);
				
				
				
				i++;
			}
			
			if(lsttest.size()>0)
			{
				finall.addAll(lsttest);
				
			}
			
			
			
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return finall;
	}

    
    
    public static String getreporttestforpagesepareation() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		List finall=new ArrayList();
         List lsttest=new ArrayList();
         List lsttest1=new ArrayList();
		String query = QueryConfig.SELECT_TEST_REPORT_SEPERATION;
		String data="";
		
		Connection conn = PGDataHelper.getInstance().getConnection();
		String filename = null;
		String crno = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null; 
		try
		{
			 pstmt = conn.prepareStatement(query);
			// pstmt.setString(1, resultEntryVOGroupByValidatedByObj.getRequisitionNo());
			// pstmt.setString(2, resultEntryVOGroupByValidatedByObj.getSampleNo());
			 int i=1;
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				
				filename = rs.getString(1);
				data=filename;
				
				
				
				i++;
			}
			
		
			
			
			
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}

    
    public static String getfinalheaderheight(String headerheight,int islabeldoubleprint,int headerheightt,String ipd,ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj ) {

    	String finalval="";
    
    	
    	headerheight=String.valueOf(headerheightt);
    	
    	finalval=headerheight;
    	
    	if(islabeldoubleprint==0)
    	{
    		
    		
    		 if( ((resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") ) ) ) //10010,10010
	        	{
    			 
	        	}
    		 else
    		 {
    		if(ipd!=null && ipd.equals("2"))
			{
    			Float ff=Float.parseFloat(headerheight);
        		
        		ff=ff+15f;
        			
        			finalval=ff.toString();
    			
			}
    		else
    		{
    		/*Float ff=Float.parseFloat(headerheight);
    		
    		ff=ff-5f;
    			
    			finalval=ff.toString();*/
    		}
    		 }
    		
    	}
    	else
    	{
    		Float ff=Float.parseFloat(headerheight);
    		for(int k=1;k<=islabeldoubleprint;k++)
    		{
    			
    			
    			
    			if(ipd!=null && ipd.equals("2"))
    			{
    				
    				
    				if( ((resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") ) ) ) //10010,10010
    	        	{
    					if(islabeldoubleprint==2)
        					ff+=6;
            			
            				if(islabeldoubleprint==4)
            				ff+=6;	
            				
            				if(islabeldoubleprint==6)
                			ff+=6;	
    	        	}
    				else
    				{
    				if(islabeldoubleprint==2)
    				ff+=14;
    			
    				if(islabeldoubleprint==4)
    				ff+=12;	
    				
    				if(islabeldoubleprint==6)
        			ff+=10;	
    				}
    				
    			}
    			else
    			{
    				
    				if( ((resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") ) ) ) //10010,10010
    	        	{
    					
    					if(islabeldoubleprint==2)
        					ff+=6;
            			
            				if(islabeldoubleprint==4)
            				ff+=6;	
            				
            				if(islabeldoubleprint==6)
                			ff+=6;	
            				
    	        	}
    				else
    				{
    				if(islabeldoubleprint==2)
    					ff+=10;
        			
        				if(islabeldoubleprint==4)
        				ff+=10;	
        				
        				if(islabeldoubleprint==6)
            			ff+=11;	
    				}
    				
    			}
    			
    			finalval=ff.toString();
    		}
    		
    		
    		if(ipd!=null && ipd.equals("2"))
			{
				

    			
    			 if( ((resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") ) ) ) //10010,10010
 	        	{
    				if(resultEntryVOGroupByValidatedByObj.getLabNo()!=null && resultEntryVOGroupByValidatedByObj.getLabNo()!=null && resultEntryVOGroupByValidatedByObj.getLabNo().length()>15)
    	            {
    					Float fvall=Float.parseFloat(finalval);

            			fvall=fvall+4.0f;
        				finalval=fvall.toString();
    					
    	            }
    				
                }
    			else
    			{
			if(resultEntryVOGroupByValidatedByObj.getLabNo()!=null && resultEntryVOGroupByValidatedByObj.getLabNo()!=null && resultEntryVOGroupByValidatedByObj.getLabNo().length()>16)
            {
    			Float fvall=Float.parseFloat(finalval);

    			fvall=fvall+10.0f;
				finalval=fvall.toString();
				
            }
    			}
			
			}
    		else
    		{
    			
    			
    			 if( ((resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") ) ) ) //10010,10010
 	        	{
    				if(resultEntryVOGroupByValidatedByObj.getLabNo()!=null && resultEntryVOGroupByValidatedByObj.getLabNo()!=null && resultEntryVOGroupByValidatedByObj.getLabNo().length()>15)
    	            {
    					Float fvall=Float.parseFloat(finalval);

            			fvall=fvall+4.0f;
        				finalval=fvall.toString();
        				
    					
    	            }
    				
                }
    			else
    			if(resultEntryVOGroupByValidatedByObj.getLabNo()!=null && resultEntryVOGroupByValidatedByObj.getLabNo()!=null && resultEntryVOGroupByValidatedByObj.getLabNo().length()>13)
                {
        			Float fvall=Float.parseFloat(finalval);

        			fvall=fvall+4.0f;
    				finalval=fvall.toString();
    				
                }
    			
    		}
    		
    		
    		

    		if(ipd!=null && ipd.equals("2")) // access no
			{
				

    			if(resultEntryVOGroupByValidatedByObj.getIsreportlablabelchanged()==null || resultEntryVOGroupByValidatedByObj.getIsreportlablabelchanged().equals("0"))
            
    			{}
    			else
    			{
    				
    				if( ((resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") ||  resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") ) ) ) //10010,10010
     	        	{
    			Float fvall=Float.parseFloat(finalval);

    			
    			if(islabeldoubleprint==4)
        			fvall=fvall-20.0f;
    				
    				if(islabeldoubleprint==6)
    	    			fvall=fvall-10.0f;
    				
    			
				finalval=fvall.toString();
     	        	}
    				
            }
			
			}
    		
    	}
    	
    	
    	if(PropertiesHelper.getReportXSL_latest())
		 {
   		Float ff=Float.parseFloat(finalval);
   		ff=ff+10.0f;
   		finalval=ff.toString();
		 }
   	
    	
    	
    	return finalval;
    }
    

    
    public boolean printReportInPdfFormat(List<ResultEntryVOGroupByValidatedBy> reportGroupByList, String hospitalCode, Connection conn) throws SQLException {
        List<ResultEntryVOGroupByValidatedBy> registeredValidatedByList = null;
        List<ResultEntryVOGroupByValidatedBy> unRegisteredValidatedByList = null;

        
        boolean flag = false;
        CallableStatement cstmt = null;
        for (ResultEntryVOGroupByValidatedBy resBy : reportGroupByList) {
        	
            Log(Level.INFO, "counterrrrrrrrr::" + reportGroupByList.size());

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
            String dnoo="";
            for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj : registeredValidatedByList) {
            	
            	
            	if(resultEntryVOGroupByValidatedByObj.getTestCode().equals("12815"))
            	{
            		
            		System.out.println("---------------------------------");
            	}
            	
            	 if( resultEntryVOGroupByValidatedByObj.getHeaderheight()==0.0 && resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("1"))
                 {
            		 
            		 String dynamicTemplateID = PGDataHelper.getInstance().getDynamicTemplateStatus(resultEntryVOGroupByValidatedByObj.getTestCode(), resultEntryVOGroupByValidatedByObj.getHospitalCode(), false, resultEntryVOGroupByValidatedByObj.getLaboratoryCode());
         			
            		 if( dynamicTemplateID!=null && !dynamicTemplateID.isEmpty() && !dynamicTemplateID.equals("") && !dynamicTemplateID.equals("-1"))
                     {
            			 resultEntryVOGroupByValidatedByObj.setHeaderheight(220.0f);
                     }
            		 else
            		 {
            			 resultEntryVOGroupByValidatedByObj.setHeaderheight(210.0f);
            		 }
            		 
                 	
                 }
            	 else
            	 {
            		 
            		 
            			String dynamicTemplateID = PGDataHelper.getInstance().getDynamicTemplateStatus(resultEntryVOGroupByValidatedByObj.getTestCode(), resultEntryVOGroupByValidatedByObj.getHospitalCode(), false, resultEntryVOGroupByValidatedByObj.getLaboratoryCode());
            			
            		 if( dynamicTemplateID!=null && !dynamicTemplateID.isEmpty() && !dynamicTemplateID.equals("") && !dynamicTemplateID.equals("-1"))
                     {
            			 resultEntryVOGroupByValidatedByObj.setHeaderheight(200.0f);
                     }
            		 else
            		 {
            		 resultEntryVOGroupByValidatedByObj.setHeaderheight(190.0f);
            		 }
                 }
            	 
            	
            	if(resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo()!=null)
            	{
            		System.out.println("22");
            		if(dnoo.equals(""))
            		dnoo=resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo()==null?"":resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo();
            		else
            			dnoo+="#"+resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo()==null?"":"#"+resultEntryVOGroupByValidatedByObj.getXmlgenereatedrequisitiondnoo();	
            	}
            	
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
                    
                    System.out.println("ashuchk-1");
                    
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
                    	

                    	String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                    	                    	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                    }
                    else
                    {
                    if(resultEntryVOGroupByValidatedByObj.getIsdynamicGroupTemplate().equals("0") && resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate().equals("0"))
                    {
                    if (resultEntryVOGroupByValidatedByObj.getPrintingType() != null && resultEntryVOGroupByValidatedByObj.getPrintingType().equals("1")) {
                        //headerString.append(lineString);
                    	if(resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate()!=null && resultEntryVOGroupByValidatedByObj.getIsdynamicTestTemplate().equals(1))
                    	{
                    		
                    		if(resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0"))
                            {
                            	
                    			String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                            	
                            	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                            }
                    		else
                    		{
                    			String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                            	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));
                        			}
                    			
                    	}
                    	else
                    	{
                    		
                    		if(resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0"))
                            {
                            	
                    			String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
    	                       	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                            }
                    		else
                    		{


                            	String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                      	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));


                    		}
                    	}	
                    	
                    	// headerString.append(standardRangesString);
                       // resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 25));
                    } else {
                    	if(resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0"))
                        {
                        	

                    		String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
	                     	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));
                        }
                		else
                		{
                			

                        	String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                    	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                			
                		
                		}
                       // resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
                    }
                    }
                    else
                    {
                        if (resultEntryVOGroupByValidatedByObj.getPrintingType() != null && resultEntryVOGroupByValidatedByObj.getPrintingType().equals("1")) {
                           // headerString.append(lineString);
                        	if(resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0"))
                            {
                            	
                            	
                        		String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
	                         	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                            }
                    		else
                    		{


                            	String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                            	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                    		}
                            //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) + 10));
                        } else {
                        	if(resultEntryVOGroupByValidatedByObj.getDisplayHeader().equals("0"))
                            {
                            	
                        		String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITH_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
	                         	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));
                            }
                    		else
                    		{
                    			String headerheight=getfinalheaderheight(Config.HEADER_HEIGHT_WITHOUT_HEADER,resultEntryVOGroupByValidatedByObj.getIslabeldoubleline(),(int)resultEntryVOGroupByValidatedByObj.getHeaderheight(),resultEntryVOGroupByValidatedByObj.getRequisitionTypeCode(),resultEntryVOGroupByValidatedByObj);
                         	resultEntryVOGroupByValidatedByObj.setHeaderheight(Float.parseFloat(headerheight));

                			
                    		}
                            //resultEntryVOGroupByValidatedBy.setHeaderHeight("" + (Float.parseFloat(resultEntryVOGroupByValidatedBy.getHeaderHeight()) - 2));
                        }
                    	
                    	
                    	
                    }
                    }
                    
                  
                    
                    String saveStatus = SavePDF.saveFileToLocation(resultEntryVOGroupByValidatedByObj.getHeader(), resultEntryVOGroupByValidatedByObj.getFooter(), resultEntryVOGroupByValidatedByObj.getGroupTemplateString(), pdfFileName, Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterHeight()), resultEntryVOGroupByValidatedByObj.getHeaderheight(), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getHeaderWidth()), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterWidth()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : resultEntryVOGroupByValidatedByObj.getPageSize()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : Config.pagewidthprinting), oldFilePdf,resultEntryVOGroupByValidatedByObj.getFileuploaddata(),resultEntryVOGroupByValidatedByObj.getIsfileuploaddadd(),resultEntryVOGroupByValidatedByObj.getIsfirstpagereq(),resultEntryVOGroupByValidatedByObj);
                    //resultEntryVOGroupByValidatedByObj.setPdfFtpUrl(pdfFileurl + "/" + pdfFileName);
                    resultEntryVOGroupByValidatedByObj.setPdfFileName(pdfFileName);

                    
                    byte[] pdfnew = null;
                    
                    pdfnew = MongoXmlHandler.getInstance().latestFetchFileByte(pdfFileName);
                    String base64bytearraynew = org.apache.commons.codec.binary.Base64.encodeBase64String(pdfnew);
                    
                    
                    
                    List<String> patDetails = PGDataHelper.getInstance().getPatientDetails(resultEntryVOGroupByValidatedByObj.getPatCRNo());
                    //System.out.println("MNo : "+patDetails.get(0)+" : email : "+patDetails.get(1)+" : name : "+patDetails.get(3));
                    String patContactNo= patDetails.get(0);
                    String patName= patDetails.get(3);
                    
                    
                    if (saveStatus != null) {
                        String query = QueryConfig.Q_SAVE_PDF_WORKORDERS;
                        //chanks
                         cstmt = null;
                         ResultSet rs = null;
                        String queryforTest = QueryConfig.Q_GET_REQ_DTL_STATUS_HIVT_REQ_DTL;
                          
                        // conn = PGDataHelper.getInstance().getConnection();
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        PreparedStatement pstmt2 = conn.prepareStatement(QueryConfig.Q_UPDATE_REQ_STATUS);
                       

                       // PreparedStatement pstmt10 = conn.prepareStatement(QueryConfig.Q_UPDATE_REQ_STATUS_DAILY_TABLE_DELETE_DNO_DUPLICATE);
                       // PreparedStatement pstmt11 = conn.prepareStatement(QueryConfig.Q_UPDATE_REQ_STATUS_DAILY_TABLE);
                        PreparedStatement pstmt10 = conn.prepareStatement(QueryConfig.Q_hivt_report_dtl_daily_TABLE);
                        PreparedStatement pstmt11 = conn.prepareStatement(QueryConfig.Q_UPDATE_hivt_report_dtl_hst_TABLE);
                        
                        PreparedStatement pstmt12 = conn.prepareStatement(QueryConfig.Q_UPDATE_hivt_report_dtl_daily_DELETE_DNO_DUPLICATE);
                        PreparedStatement pstmt13 = conn.prepareStatement(QueryConfig.Q_UPDATE_hivt_report_dtl_hst_DELETE_DNO_DUPLICATE);

                        
                        
                        //new
                    //    PreparedStatement pstmt33 = conn.prepareStatement(QueryConfig.Q_SAVE_INV_DTL_LOG);
                    //    PreparedStatement pstmt4 = conn.prepareStatement(QueryConfig.Q_SAVE_INV_DTL_GROUP_LOG);
                        
                        PreparedStatement pstmt33 =null;
                        PreparedStatement pstmt4 =null;
                        if(PropertiesHelper.is_data_insert_hprms())
                        {
                         pstmt33 = conn.prepareStatement(QueryConfig.Q_SAVE_INV_DTL_LOG);
                        pstmt4 = conn.prepareStatement(QueryConfig.Q_SAVE_INV_DTL_GROUP_LOG);
                      
                        }
                        
                        PreparedStatement pstmt14 = conn.prepareStatement(QueryConfig.Q_UPDATE_hivt_report_dtl_daily_HISTBL);
                        PreparedStatement pstmt15 = conn.prepareStatement(QueryConfig.Q_UPDATE_hivt_report_dtl_hst_HISTBL);
            
                        
                        PreparedStatement pstmt5 = conn.prepareStatement(QueryConfig.Q_UPDATE_REQ_STATUS_ALL);

                        if(resultEntryVOGroupByValidatedByObj.getIsDeptEntry().equals("1"))
                        	pstmt2 = conn.prepareStatement(QueryConfig.Q_UPDATE_DEPT_REQ_STATUS);
                        

                    	
                    	Map<String, String> mp=new HashMap<String, String>();

                    	
                        for (ResultEntryVO resultEntryVOObj : resultEntryVOGroupByValidatedByObj.getResultEntryVOListValidatedBy()) {
                        	


                        	
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
                            
                            if(dnoo.contains(resultEntryVOObj.getRequisitionDNo()))
                        	{
                            pstmt2.setString(1, resultEntryVOGroupByValidatedByObj.getPdfFileName());
                            pstmt2.setString(2, resultEntryVOObj.getRequisitionDNo());
                             pstmt2.executeUpdate();
                           
                            
                             
                            /* pstmt10.setString(1, resultEntryVOObj.getRequisitionDNo());
                             pstmt10.executeUpdate();
                           
                       
                             pstmt11.setString(1, resultEntryVOObj.getRequisitionDNo());
                             pstmt11.executeUpdate();*/
                             
                             pstmt12.setString(1, resultEntryVOObj.getRequisitionDNo());
                             pstmt12.executeUpdate();
                             
                             
                             pstmt10.setString(1, resultEntryVOObj.getRequisitionDNo());
                             pstmt10.executeUpdate();
                           
                       
                             pstmt13.setString(1, resultEntryVOObj.getRequisitionDNo());
                             pstmt13.executeUpdate();
                       
                             pstmt11.setString(1, resultEntryVOObj.getRequisitionDNo());
                             pstmt11.executeUpdate();
                             
                             pstmt14.setString(1, resultEntryVOObj.getPatCRNo());
                             pstmt14.setString(2, resultEntryVOObj.getRequisitionDNo());
                             pstmt14.executeUpdate();
                       
                             
                             pstmt15.setString(1, resultEntryVOObj.getPatCRNo());
                             pstmt15.setString(2, resultEntryVOObj.getRequisitionDNo());
                             pstmt15.executeUpdate();
                             
                              
                             System.out.println("Req DNo: " + resultEntryVOObj.getRequisitionDNo());
                            Log(Level.INFO, "testReq DNo status update in hivt_req_dtl:" + resultEntryVOObj.getRequisitionDNo()+"isDeptEntry"+resultEntryVOGroupByValidatedByObj.getIsDeptEntry());
                            Log(Level.INFO, "pdf file datails: " + resultEntryVOGroupByValidatedByObj.getPatCRNo() + " "
                                    + resultEntryVOObj.getRequisitionDNo() + " "
                                    + resultEntryVOGroupByValidatedByObj.getRequisitionNo() + " "
                                    + resultEntryVOGroupByValidatedByObj.getPdfFileName() + " "
                                    + resultEntryVOObj.getHospitalCode());
                        	
                            
                            
                            
                            try {
                            	//System.out.println("getPdfFileName : "+resultEntryVOGroupByValidatedByObj.getPdfFileName()+" pdfFileName : "+pdfFileName);
                            	//System.out.println("getGroupCode() : "+resultEntryVOGroupByValidatedByObj.getGroupCode());
                            	
                            	if(resultEntryVOObj.getGroupCode()==null)
                            	{
                            		
                            		 if(PropertiesHelper.is_data_insert_hprms())
                                     {
                            	 pstmt33.setString(1, resultEntryVOObj.getPatCRNo());
                            	 pstmt33.setString(2, (InetAddress.getLocalHost().getHostAddress()).trim());
                            	 pstmt33.setString(3, resultEntryVOObj.getHospitalCode());
                            	 pstmt33.setString(4, resultEntryVOObj.getRequisitionNo());
                            	 pstmt33.setString(5, resultEntryVOObj.getRequisitionDNo());
                            	 pstmt33.setString(6, resultEntryVOObj.getPatVisitNo());
                            	 pstmt33.setString(7, resultEntryVOObj.getLaboratoryCode());
                            	 pstmt33.setString(8, resultEntryVOObj.getLaboratoryName());
                            	 pstmt33.setString(9, resultEntryVOObj.getTestCode());
                            	 pstmt33.setString(10, resultEntryVOObj.getTestName());
                            	 pstmt33.setString(11, base64bytearraynew);
                            	 pstmt33.setString(12, resultEntryVOObj.getAddendumRemark());
                            	 pstmt33.setString(13, patDetails.get(0));
                            	 pstmt33.setString(14, patDetails.get(1));
                            	 pstmt33.setString(15, patDetails.get(2));
                            	 pstmt33.setString(16, resultEntryVOObj.getCollDate());
                            	 pstmt33.setString(17, resultEntryVOObj.getRequisitionDate());
                                 	
                            	 pstmt33.executeUpdate();
                                     }
                             
                            	}
                            } catch (Exception ex) {
                                //sqex.printStackTrace();
                                Log(Level.SEVERE, ex);
                            }
                            
                            try {
                            	
                            	if(resultEntryVOGroupByValidatedByObj.getGroupCode()!=null)
                            	{
                            		
                            		if(mp.get(resultEntryVOGroupByValidatedByObj.getRequisitionNo()+resultEntryVOGroupByValidatedByObj.getGroupCode())==null)
                            		{
                            		
                            	
                            			 if(PropertiesHelper.getisbilldatamovescheduleronorofff())
                                         {
                                pstmt4.setString(1, resultEntryVOGroupByValidatedByObj.getPatCRNo());
                                pstmt4.setString(2, (InetAddress.getLocalHost().getHostAddress()).trim());
                                pstmt4.setString(3, resultEntryVOGroupByValidatedByObj.getHospitalCode());
                                pstmt4.setString(4, resultEntryVOGroupByValidatedByObj.getRequisitionNo());
                                pstmt4.setString(5, resultEntryVOGroupByValidatedByObj.getRequisitionDNo());
                                pstmt4.setString(6, resultEntryVOGroupByValidatedByObj.getPatVisitNo());
                                pstmt4.setString(7, resultEntryVOGroupByValidatedByObj.getLaboratoryCode());
                                pstmt4.setString(8, resultEntryVOGroupByValidatedByObj.getLaboratoryName());
                                pstmt4.setString(9, resultEntryVOGroupByValidatedByObj.getGroupCode());
                                pstmt4.setString(10, resultEntryVOGroupByValidatedByObj.getGroupCode());
                                pstmt4.setString(11, resultEntryVOGroupByValidatedByObj.getHospitalCode());
                                pstmt4.setString(12, base64bytearraynew);
                                pstmt4.setString(13, resultEntryVOGroupByValidatedByObj.getAddendumRemark());
                                pstmt4.setString(14, patDetails.get(0));
                                pstmt4.setString(15, patDetails.get(1));
                                pstmt4.setString(16, patDetails.get(2));
                                pstmt4.setString(17, resultEntryVOGroupByValidatedByObj.getCollDate());
                                pstmt4.setString(18, resultEntryVOGroupByValidatedByObj.getRequisitionDate());
                                 	
                                pstmt4.executeUpdate();
                                
                                         }
                            	
                    			mp.put(resultEntryVOGroupByValidatedByObj.getRequisitionNo()+resultEntryVOGroupByValidatedByObj.getGroupCode(), "1");
                    		}
                            		
                            	}
                            	
                            } catch (Exception ex) {
                                //sqex.printStackTrace();
                                Log(Level.SEVERE, ex);
                            }
                            
                            /*if (pstmt2 != null) {
                                pstmt2.close();
                            }*/
                            
                           /* List oldurlss=getPdfsrequisitiondtlrequisitionwisesamplewise(resultEntryVOGroupByValidatedByObj);
                            
                           
                            Date date1 = new Date();
                            SimpleDateFormat sdf1 = new SimpleDateFormat("MMddyyyyhhmmss.SSSa");
                            String formattedDate1 = sdf1.format(date1);
                            //System.out.println(formattedDate1); // 12/
                            String pdfFileName1 = resultEntryVOGroupByValidatedByObj.getPatCRNo() +".pdf";
                        
                             String pdfFileName1 = resultEntryVOGroupByValidatedByObj.getPatCRNo() + "_"
                                    + resultEntryVOGroupByValidatedByObj.getLaboratoryCode() + "_"
                                    + formattedDate1 + ".pdf";
                             
                             
                            if(oldurlss!=null && oldurlss.size()>0)
                            {

                            	 
                                List <byte[]> finalPdfArray= new ArrayList<byte[]>();
                                
                            	for(int i=0;i<oldurlss.size();i++)
                            	{
                            		String pdfname=oldurlss.get(i).toString();
                          byte[] oldFilePdf1=null;
                          oldFilePdf1=MongoXmlHandler.getInstance().latestFetchFileByte(pdfname);
                          if(oldFilePdf1!=null)
                          	finalPdfArray.add(oldFilePdf1);
                          
                            	}
                                String saveStatus1 = SavePDF.saveFileToLocation1(resultEntryVOGroupByValidatedByObj.getHeader(), resultEntryVOGroupByValidatedByObj.getFooter(), resultEntryVOGroupByValidatedByObj.getGroupTemplateString(), pdfFileName1, Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterHeight()), resultEntryVOGroupByValidatedByObj.getHeaderheight(), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getHeaderWidth()), Float.parseFloat(resultEntryVOGroupByValidatedByObj.getFooterWidth()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : resultEntryVOGroupByValidatedByObj.getPageSize()), ((resultEntryVOGroupByValidatedByObj.getPageSize().startsWith("A4")) ? null : Config.pagewidthprinting), oldFilePdf,finalPdfArray);

                    	
                            }
                            
                            
                            pstmt5.setString(1, pdfFileName1);
                            pstmt5.setString(2, resultEntryVOObj.getRequisitionNo());
                            pstmt5.setString(3, resultEntryVOObj.getSampleNo());
                            pstmt5.executeUpdate();
                            System.out.println("Req DNo: " + resultEntryVOObj.getRequisitionDNo());
                            Log(Level.INFO, "testReq DNo status update in hivt_req_dtl:" + resultEntryVOObj.getRequisitionDNo()+"isDeptEntry"+resultEntryVOGroupByValidatedByObj.getIsDeptEntry());
                            Log(Level.INFO, "pdf file datails: " + resultEntryVOGroupByValidatedByObj.getPatCRNo() + " "
                                    + resultEntryVOObj.getRequisitionDNo() + " "
                                    + resultEntryVOGroupByValidatedByObj.getRequisitionNo() + " "
                                    + resultEntryVOGroupByValidatedByObj.getPdfFileName() + " "
                                    + resultEntryVOObj.getHospitalCode());*/
                        	
                            
                        	}
                            
                            
                        	}
                       
                        if(PropertiesHelper.is_data_insert_hprms())
                        {
                        if (pstmt33 != null) {
                            pstmt33.close();
                        }
                        if (pstmt4 != null) {
                            pstmt4.close();
                        }
                        
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
    
}
