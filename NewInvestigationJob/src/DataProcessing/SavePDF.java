/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import DataHelper.Config;
import DataHelper.JakartaFTPWrapper;
import DataHelper.PGDataHelper;
import DataHelper.PropertiesHelper;
import DataHelper.QueryConfig;
import Logging.ServiceLogger;
import MongoHelper.MongoXmlHandler;
import TemplateHelper.EndPage;
import TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PRAcroForm;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.SimpleBookmark;
import com.itextpdf.tool.xml.ElementHandler;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.Writable;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.WritableElement;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.text.pdf.codec.Base64;
import com.jscape.inet.ftp.FtpException;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.net.ftp.FTPClient;

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
public class SavePDF {
	

    public synchronized static String saveFileToLocation(String headerString, String footerString, String htmlString, String pdfFileName, float footerHeight, float headerHeight, float headerwidth, float footerWidth, String pageHeight, String pageWidth, byte[] oldpdf,String fileuploaddata,String isfileuploadprint,String isfirstpageprint,ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj) throws IOException, DocumentException {
        Document document = null;
       /* Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::pageHeight ::" + pageHeight);
        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::pagewidth ::" + pageWidth);
        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::headerHeight ::" + headerHeight);
        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::headerWidth ::" + headerwidth);
        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::footerHeight ::" + footerHeight);
        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::footerWidth ::" + footerWidth);*/
       // headerHeight=Float.parseFloat( Config.HEADER_HEIGHT);
        
       
        
      float  footerHeight1=Float.parseFloat( Config.FOOTER_HEIGHT);
        String returnStatus = null;
        if(htmlString == null )
        {
            Log(Level.SEVERE, "Empty HTML String");
            return null;
        }
        
        if (pageHeight == null) {
            document = new Document(PageSize.A4);
        } else {
            document = new Document(new Rectangle(Float.parseFloat(pageWidth), Float.parseFloat(pageHeight))); // specify the page size
        }
        
        
        // for dynamic test template( canned file issue)
     
     /*   htmlString=htmlString.replace("<","&lt;");
        htmlString=htmlString.replace(">","&gt;");
     */
        
      /*  htmlString=htmlString.replace("&lt;","<");
        htmlString=htmlString.replace("&gt;",">");
        htmlString=htmlString.replace("&amp;","&");
      */ 
        
        
        htmlString=htmlString.replace("&amp;","&");
      //for text area line breaks 
       // htmlString=htmlString.replace("\r\n","<br/>");
        
        
       // String divv="<div style='height:7px;'></div>";
        //htmlString=htmlString.replace("\r\n",divv);
        System.out.println("the main html string is :  "+htmlString);
        
        System.out.println(" footer :  "+footerString);

        
        List <byte[]> finalPdfArray= new ArrayList<byte[]>();
        if(oldpdf!=null)
        {
        	if(isfirstpageprint!=null && isfirstpageprint.equals("1")) // not attach oldpdf in case of file upload
            {
        
            }
        	else
        	{
        		if(resultEntryVOGroupByValidatedByObj.getIsaddendumfirstreportprint()!=null && resultEntryVOGroupByValidatedByObj.getIsaddendumfirstreportprint().equals("1"))
        		{
        			if(resultEntryVOGroupByValidatedByObj.getOldReportUrl()!=null && !resultEntryVOGroupByValidatedByObj.getOldReportUrl().equals(""))
        			{
        				if(resultEntryVOGroupByValidatedByObj.getOldReportUrl()!=null && !resultEntryVOGroupByValidatedByObj.getOldReportUrl().equals(""))
        			insertoldreports(resultEntryVOGroupByValidatedByObj.getRequisitionDNo(),resultEntryVOGroupByValidatedByObj.getOldReportUrl(),resultEntryVOGroupByValidatedByObj.getChangeCount(),resultEntryVOGroupByValidatedByObj.getPatCRNo());
        			}
        			oldpdf=null;
        		}
        		else
        		{
        			
        		finalPdfArray.add(oldpdf);
        		}
        		
          }
        	
         }

        //new addendum report output stream
     /*   ByteArrayOutputStream addendumoutputstream = new ByteArrayOutputStream( );
        byte newdata[] = null;
        if(oldpdf!=null && !oldpdf.equals("null"));
        addendumoutputstream.write( oldpdf );*/
       
        
        
    //    BaseFont bf = BaseFont.createFont("/DataProcessing/FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
   //     Font f = new Font(bf, 12);
    //    String htmlStart="<html><head><meta http-equiv='content-type' content='application/xhtml+xml; charset=UTF-8'/></head><body style=\"font-size:12.0pt; font-family:Arial\"><br /><br /><br /><br />";
        String htmlStart="<div style=\"font-size:12.0pt; font-family:Times New Roman\">";//"<html><head><meta http-equiv='content-type' content='application/xhtml+xml; charset=UTF-8'/></head><body style=\"font-family:Arial\">";
        String htmlEnd="</div>";
       // String htmlEnd="</html>";
    //	Paragraph p = new Paragraph(htmlStart+htmlString+htmlEnd, f);
//         String FONT = "C:\\Users\\P.S.KHURANA\\Downloads\\FreeSans.ttf"; // add arial
//        BaseFont bf = BaseFont.createFont(FONT, BaseFont..IDENTITY_H, BaseFont.EMBEDDED);
//        Font f = new Font(bf, 12);
//        Paragraph p = new Paragraph(htmlString, f);
        InputStream s = new ByteArrayInputStream(htmlString.getBytes());
        BufferedOutputStream bos = null;
        List<Element> footerElementList = null;
        List<Element> headerElementList = null;
        System.out.println(headerString);
        try {

            if (footerString != null && (footerString.equals("") == false)) {
                footerElementList = HTMLWorker.parseToList(new InputStreamReader(new ByteArrayInputStream(footerString.getBytes())), new StyleSheet());
                
            }

            if (headerString != null && (headerString.equals("") == false)) {
                headerElementList = HTMLWorker.parseToList(new InputStreamReader(new ByteArrayInputStream(headerString.getBytes())), new StyleSheet());
            }

            /*float headerheightt=document.topMargin();
            
            if(document.topMargin()==310.0)
            {
            	headerHeight=headerHeight+10f ;
            }*/
            
            
            document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight, document.bottomMargin() + footerHeight);
            
           /* if(document.topMargin()==310.0)
            {
            	            	headerHeight=headerHeight+10f ;
            }
            
            if(document.topMargin()==246.0)
            {
            	            	headerHeight=headerHeight+15f ;
            }
            
           
            
            if (pageHeight == null) {
	            document = new Document(PageSize.A4);
	        } else {
	            document = new Document(new Rectangle(Float.parseFloat(pageWidth), Float.parseFloat(pageHeight))); // specify the page size
	        }
            
            document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight, document.bottomMargin() + footerHeight);
            */
            
            System.out.println("Header heights" + document.topMargin() + " " + headerHeight);
            System.out.println("footerr heights" + document.bottomMargin()  + " " + footerHeight);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            boolean isfirstpageprintornot=false;
            
            try {
               // System.out.println(htmlString);
                PdfWriter writer = PdfWriter.getInstance(document, baos);
                EndPage endPage = new EndPage();
                endPage.footerElementList = footerElementList; 
                endPage.headerElementList = headerElementList;
                writer.setPageEvent(endPage);
                document.open();
/*                endPage.onCloseDocument(writer, document);
                */
              // document.addHeader("", arg1)
                
               // latest change to print DELTA- 2 lines by adding htm head info
//                XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
//               // worker.parseXHtml(writer, document, s);
//                worker.parseXHtml(writer, document, s,Charset.forName("cp1252"));

                
                ///DataProcessing/
                
                /*this change didn't work
                 * 
                 *    ITextRenderer renderer = new ITextRenderer();
                     renderer.getFontResolver().addFont("c:/work/fonts/TIMES.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
               */
                
                
                
              /* this change didn't work
               * 
               *  XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
                InputStream is = new ByteArrayInputStream(htmlString.getBytes("UTF-8"));
                worker.parseXHtml(writer, document, is, Charset.forName("UTF-8"));*/
                
              /*  this change didn't work
               *  XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
                fontImp.register("/DataProcessing/FreeSans.ttf");
                XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                        new FileInputStream(htmlString), null, Charset.forName("UTF-8"), fontImp);*/
                
                
             
                
                
                
                
               /* this change didn't work*/
                 // document.add(p);
                
                
//               HTMLWorker htmlWorker = new HTMLWorker(document);
//                htmlWorker.parse(new StringReader(p.toString()));
//               
                
                
                	
//              String FONT = "C:\\Users\\P.S.KHURANA\\Downloads\\arial.ttf"; // add arial
//             BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//             Font f = new Font(bf, 12);
//             Paragraph p1 = new Paragraph("",f);
//                
//                ElementList list = XMLWorkerHelper.parseToElementList(htmlString, null);
//                for (Element element : list) {
//                    p1.add(element);
//                }
//                
//              // Paragraph para = new Paragraph(p1.toString(), f);
//               document.add(p1);
        /*        original parsing  process      */
              
//         List<Element> elementlist =  HTMLWorker.parseToList(new InputStreamReader(s), new StyleSheet());
//                for (int i = 0; i < elementlist.size(); i++) {
//                    Element element = (Element) elementlist.get(i);
//                    if (elementlist.size() > 0) {
//                        document.add(element);
//                    }
//                }
                CSSResolver cssResolver =
                        XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
         
                
                XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
                fontProvider.register(PropertiesHelper.getFontFile(), "Arial");
                //fontProvider.register("resources/fonts/PT_Serif-Web-Regular.ttf", "Serif");
                CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
                
                // HTML
                HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
                htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
                htmlContext.setImageProvider(new Base64ImageProvider());
         
                // Pipelines
                PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
                HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
                CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
                
                // XML Worker
                XMLWorker worker = new XMLWorker(css, true);
                XMLParser p = new XMLParser(worker, Charset.forName("UTF-8")); 
                if(p==null)
                	System.out.println("p is null");
                if(s== null)
                {
                	System.out.println("nullll");
                }
                p.parse(s);
                
            } catch (DocumentException e) {
                //returnStatus = null;
                Log(Level.WARNING, e);
                e.printStackTrace();
            }

            document.close();
            byte[] pdfData = baos.toByteArray();
           
            if(isfirstpageprint!=null && isfirstpageprint.equals("1"))
            {
            	isfirstpageprintornot=true;
            }
            else
            {
            	
            	finalPdfArray.add(pdfData);
            }
            
            
            
        /*    if(oldpdf!=null )
            {
            	
            	 if(isfirstpageprint!=null && isfileuploadprint.equals("1"))
                   {
                 		 output= mergeMyFilesfilesupload(finalPdfArray, output);	
                         pdfData=output.toByteArray();
             
                    }
            	 else
            	 {
                output= mergeMyFiles(finalPdfArray, output);	
                pdfData=output.toByteArray();
                }
            	 
            }*/
            
            
            if(isfileuploadprint!=null && !isfileuploadprint.equals("0"))
            {
            	if(fileuploaddata!=null && !fileuploaddata.equals(""))
            {
            	
            	if(fileuploaddata.contains("#@#"))
            	{
            		for(int k=0;k<fileuploaddata.split("#@#").length;k++)
            		{
            			 byte fileupload[]=null;
                         fileupload = org.apache.commons.codec.binary.Base64.decodeBase64(fileuploaddata.split("#@#")[k]);
                          
                         if(fileupload!=null)
                         	finalPdfArray.add(fileupload);
                         
                       
            		}
            	}
            	else
            	{
            		 byte fileupload[]=null;
                    fileupload = org.apache.commons.codec.binary.Base64.decodeBase64(fileuploaddata);
                     
                    if(fileupload!=null)
                    	finalPdfArray.add(fileupload);
                    

            	}
            	
            }
            }
            

            
          /*  if(oldpdf!=null)
            {
            addendumoutputstream.write( pdfData );
            newdata = addendumoutputstream.toByteArray( );
            }
            
            if(newdata!=null)
            	pdfData=newdata;*/
            
           
            
          
            if(finalPdfArray.size()>0)
            {
            	
            	if(isfirstpageprintornot==true && oldpdf!=null)
            output= mergeMyFiles(finalPdfArray, output);	
            	else
            output= mergeMyFiles(finalPdfArray, output);
            	
            pdfData=output.toByteArray();
            }
            
            MongoXmlHandler.getInstance().savePDFFile(pdfFileName, pdfData);
            returnStatus = "success";

        } catch (IOException e) {
            //  returnStatus = null;
            Log(Level.SEVERE, e);
            //e.printStackTrace();

        } finally {

            try {
                if (document != null) {
                    document.close();
                }
            } catch (Exception e) {
                //  returnStatus = null;
                //e.printStackTrace();
                Log(Level.SEVERE, e);
            }

            try {
                if (bos != null) {
                    
                    bos.close();
                }
            } catch (IOException e) {
                //  returnStatus = null;
                Log(Level.SEVERE, e);
               // e.printStackTrace();
            }
            
        }

        return returnStatus;
    }

    private static void Log(Level level, String msg) {
        ServiceLogger.Log(SavePDF.class.getName(), level, msg);
    }

    private static void Log(Level level, Exception e) {
        ServiceLogger.Log(SavePDF.class.getName(), level, e);
    }

    //to merge files
	private static ByteArrayOutputStream mergeMyFiles(List<byte[]> filesTobeMerges,
			ByteArrayOutputStream byteArrayOutputStream) {
		// TODO Auto-generated method stub
	//	System.out.println("Starting To Merge Files...");
		//System.out.println("Total Number Of Files To Be Merged..."+ filesToBeMerged.length + "\n");D:\root\investigationDetails\watermark\duplicateStamp.jpg D:\NEW_INVESTIGATION_21_05-2015\AHIMS\WebContent\new_investigation\images\duplicateStamp.jpg
		
		
		try {
			//Image img = Image.getInstance(InvestigationConfig.fetchfilepath+"duplicateStamp.jpg");	      
		   //  img.setAbsolutePosition(23,725);
		     
		    // Watermark watermark=new Watermark(img, 25, 725);
			int pageOffset = 0;
			ArrayList masterBookMarkList = new ArrayList();

			int fileIndex = 0;
			//String outFile = mergedFileLocation;
			Document document = null;
			PdfCopy writer = null;
			PdfReader reader = null;

			for (fileIndex = 0; fileIndex < filesTobeMerges.size(); fileIndex++) {

				
				/**
				 * Create a reader for the file that we are reading
				 */
				reader = new PdfReader(filesTobeMerges.get(fileIndex),"Administrator".getBytes()); //filesToBeMerged[fileIndex]
			//	System.out.println("Reading File -");

				/**
				 * Replace all the local named links with the actual
				 * destinations.
				 */
				reader.consolidateNamedDestinations();

				/**
				 * Retrieve the total number of pages for this document
				 */
				int totalPages = reader.getNumberOfPages();

			
				
				
				
				/**
				 * Get the list of bookmarks for the current document If the
				 * bookmarks are not empty, store the bookmarks into a master
				 * list
				 */
				
				System.out.println("Checking for bookmarks...");				
				List bookmarks = SimpleBookmark.getBookmark(reader);
				if (bookmarks != null) {
					if (pageOffset != 0)
						SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset,
								null);
					masterBookMarkList.addAll(bookmarks);
			//		System.out.println("Bookmarks found and storing...");
				} else {
			//		System.out.println("No bookmarks in this file...");
				}
				pageOffset += totalPages;

				/**
				 * Merging the files to the first file. If we are passing file1,
				 * file2 and file3, we will merge file2 and file3 to file1.
				 */
				if (fileIndex == 0) {
					/**
					 * Create the document object from the reader
					 */
					document = new Document(reader.getPageSizeWithRotation(1));

					/**
					 * Create a pdf write that listens to this document. Any
					 * changes to this document will be written the file
					 * 
					 * outFile is a location where the final merged document
					 * will be written to.
					 */
//
			//		System.out.println("Creating an empty PDF...");
					writer = new PdfCopy(document,
							byteArrayOutputStream);
					/**
					 * Open this document
					 */
					
					//writer.add(watermark);
					document.open();
					//document.add(img);
					//document.add(watermark);
				}
				/**
				 * Add the conent of the file into this document (writer). Loop
				 * through multiple Pages
				 */
				System.out.println("Merging File: ");
				PdfImportedPage page;
				for (int currentPage = 1; currentPage <= totalPages; currentPage++) {
					page = writer.getImportedPage(reader, currentPage);					
					writer.addPage(page);					
				}

				/**
				 * This will get the documents acroform. This will return null
				 * if no acroform is part of the document.
				 * 
				 * Acroforms are PDFs that have been turned into fillable forms.
				 */
		//		System.out.println("Checking for Acroforms");
				PRAcroForm form = reader.getAcroForm();
				if (form != null) {
					writer.addDocument(reader);
			//		System.out.println("Acroforms found and copied");
				} else
		//			System.out.println("Acroforms not found for this file");

				System.out.println();
			}
			/**
			 * After looping through all the files, add the master bookmarklist.
			 * If individual PDF documents had separate bookmarks, master
			 * bookmark list will contain a combination of all those bookmarks
			 * in the merged document.
			 */
			if (!masterBookMarkList.isEmpty()) {
				writer.setOutlines(masterBookMarkList);
		//		System.out.println("All bookmarks combined and added");

			} else {
	//			System.out.println("No bookmarks to add in the new file");

			}

			/**
			 * Finally Close the main document, which will trigger the pdfcopy
			 * to write back to the filesystem.
			 */
			if(document!=null)
			document.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream;
	}


	
	 public synchronized static String saveFileToLocation1(String headerString, String footerString, String htmlString, String pdfFileName, float footerHeight, float headerHeight, float headerwidth, float footerWidth, String pageHeight, String pageWidth, byte[] oldpdf,List <byte[]> finalPdfArray1,ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj) throws IOException, DocumentException {
	        Document document = null;
	       /* Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::pageHeight ::" + pageHeight);
	        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::pagewidth ::" + pageWidth);
	        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::headerHeight ::" + headerHeight);
	        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::headerWidth ::" + headerwidth);
	        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::footerHeight ::" + footerHeight);
	        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::footerWidth ::" + footerWidth);*/
	       // headerHeight=Float.parseFloat( Config.HEADER_HEIGHT);
	      float  footerHeight1=Float.parseFloat( Config.FOOTER_HEIGHT);
	        String returnStatus = null;
	        if(htmlString == null )
	        {
	            Log(Level.SEVERE, "Empty HTML String");
	            return null;
	        }
	        
	        if (pageHeight == null) {
	            document = new Document(PageSize.A4);
	        } else {
	            document = new Document(new Rectangle(Float.parseFloat(pageWidth), Float.parseFloat(pageHeight))); // specify the page size
	        }
	        
	        
	        // for dynamic test template( canned file issue)
	     
	     /*   htmlString=htmlString.replace("<","&lt;");
	        htmlString=htmlString.replace(">","&gt;");
	     */
	        
	      /*  htmlString=htmlString.replace("&lt;","<");
	        htmlString=htmlString.replace("&gt;",">");
	        htmlString=htmlString.replace("&amp;","&");
	      */ 
	        
	        
	        htmlString=htmlString.replace("&amp;","&");
	      //for text area line breaks 
	       // htmlString=htmlString.replace("\r\n","<br/>");
	        
	        
	       // String divv="<div style='height:7px;'></div>";
	        //htmlString=htmlString.replace("\r\n",divv);
	        System.out.println("the main html string is :  "+htmlString);
	        
	        List <byte[]> finalPdfArray= new ArrayList<byte[]>();
	        /*if(oldpdf!=null)
	        	finalPdfArray.add(oldpdf);
	        */
	        if(finalPdfArray1.size()>0)
	        {
	        	finalPdfArray=finalPdfArray1;
	        }
	        //new addendum report output stream
	     /*   ByteArrayOutputStream addendumoutputstream = new ByteArrayOutputStream( );
	        byte newdata[] = null;
	        if(oldpdf!=null && !oldpdf.equals("null"));
	        addendumoutputstream.write( oldpdf );*/
	       
	        
	        
	    //    BaseFont bf = BaseFont.createFont("/DataProcessing/FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	   //     Font f = new Font(bf, 12);
	    //    String htmlStart="<html><head><meta http-equiv='content-type' content='application/xhtml+xml; charset=UTF-8'/></head><body style=\"font-size:12.0pt; font-family:Arial\"><br /><br /><br /><br />";
	        String htmlStart="<div style=\"font-size:12.0pt; font-family:Times New Roman\">";//"<html><head><meta http-equiv='content-type' content='application/xhtml+xml; charset=UTF-8'/></head><body style=\"font-family:Arial\">";
	        String htmlEnd="</div>";
	       // String htmlEnd="</html>";
	    //	Paragraph p = new Paragraph(htmlStart+htmlString+htmlEnd, f);
//	         String FONT = "C:\\Users\\P.S.KHURANA\\Downloads\\FreeSans.ttf"; // add arial
//	        BaseFont bf = BaseFont.createFont(FONT, BaseFont..IDENTITY_H, BaseFont.EMBEDDED);
//	        Font f = new Font(bf, 12);
//	        Paragraph p = new Paragraph(htmlString, f);
	        InputStream s = new ByteArrayInputStream(htmlString.getBytes());
	        BufferedOutputStream bos = null;
	        List<Element> footerElementList = null;
	        List<Element> headerElementList = null;
	        System.out.println(headerString);
	        try {

	            if (footerString != null && (footerString.equals("") == false)) {
	                footerElementList = HTMLWorker.parseToList(new InputStreamReader(new ByteArrayInputStream(footerString.getBytes())), new StyleSheet());
	            }

	            if (headerString != null && (headerString.equals("") == false)) {
	                headerElementList = HTMLWorker.parseToList(new InputStreamReader(new ByteArrayInputStream(headerString.getBytes())), new StyleSheet());
	            }

	            /*float headerheightt=document.topMargin();
	            
	            if(document.topMargin()==310.0)
	            {
	            	            	headerHeight=headerHeight+10f ;
	            }*/
	            
	            document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight, document.bottomMargin() + footerHeight);
	        
	           /* if(document.topMargin()==310.0)
	            {
	            	            	headerHeight=headerHeight+10f ;
	            }
	            
	            if(document.topMargin()==246.0)
	            {
	            	            	headerHeight=headerHeight+15f ;
	            }
	            
	          
	            
	            if (pageHeight == null) {
		            document = new Document(PageSize.A4);
		        } else {
		            document = new Document(new Rectangle(Float.parseFloat(pageWidth), Float.parseFloat(pageHeight))); // specify the page size
		        }
	            
	            document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight, document.bottomMargin() + footerHeight);
	            */
	            
	            System.out.println("Header heights" + document.topMargin() + " " + headerHeight);
	            System.out.println("footerr heights" + document.bottomMargin()  + " " + footerHeight);
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ByteArrayOutputStream output = new ByteArrayOutputStream();
	            
	                    
	            try {
	               // System.out.println(htmlString);
	                PdfWriter writer = PdfWriter.getInstance(document, baos);
	                EndPage endPage = new EndPage();
	                endPage.footerElementList = footerElementList; 
	                endPage.headerElementList = headerElementList;
	                writer.setPageEvent(endPage);
	                document.open();
	              // document.addHeader("", arg1)
	                
	               // latest change to print DELTA- 2 lines by adding htm head info
//	                XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
//	               // worker.parseXHtml(writer, document, s);
//	                worker.parseXHtml(writer, document, s,Charset.forName("cp1252"));

	                
	                ///DataProcessing/
	                
	                /*this change didn't work
	                 * 
	                 *    ITextRenderer renderer = new ITextRenderer();
	                     renderer.getFontResolver().addFont("c:/work/fonts/TIMES.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	               */
	                
	                
	                
	              /* this change didn't work
	               * 
	               *  XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
	                InputStream is = new ByteArrayInputStream(htmlString.getBytes("UTF-8"));
	                worker.parseXHtml(writer, document, is, Charset.forName("UTF-8"));*/
	                
	              /*  this change didn't work
	               *  XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	                fontImp.register("/DataProcessing/FreeSans.ttf");
	                XMLWorkerHelper.getInstance().parseXHtml(writer, document,
	                        new FileInputStream(htmlString), null, Charset.forName("UTF-8"), fontImp);*/
	                
	                
	             
	                
	                
	                
	                
	               /* this change didn't work*/
	                 // document.add(p);
	                
	                
//	               HTMLWorker htmlWorker = new HTMLWorker(document);
//	                htmlWorker.parse(new StringReader(p.toString()));
//	               
	                
	                
	                	
//	              String FONT = "C:\\Users\\P.S.KHURANA\\Downloads\\arial.ttf"; // add arial
//	             BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//	             Font f = new Font(bf, 12);
//	             Paragraph p1 = new Paragraph("",f);
//	                
//	                ElementList list = XMLWorkerHelper.parseToElementList(htmlString, null);
//	                for (Element element : list) {
//	                    p1.add(element);
//	                }
//	                
//	              // Paragraph para = new Paragraph(p1.toString(), f);
//	               document.add(p1);
	        /*        original parsing  process      */
	              
//	         List<Element> elementlist =  HTMLWorker.parseToList(new InputStreamReader(s), new StyleSheet());
//	                for (int i = 0; i < elementlist.size(); i++) {
//	                    Element element = (Element) elementlist.get(i);
//	                    if (elementlist.size() > 0) {
//	                        document.add(element);
//	                    }
//	                }
	                CSSResolver cssResolver =
	                        XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
	         
	                
	                XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	                fontProvider.register(PropertiesHelper.getFontFile(), "Arial");
	                //fontProvider.register("resources/fonts/PT_Serif-Web-Regular.ttf", "Serif");
	                CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
	                
	                // HTML
	                HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
	                htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
	                htmlContext.setImageProvider(new Base64ImageProvider());
	         
	                // Pipelines
	                PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
	                HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
	                CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
	                
	                // XML Worker
	                XMLWorker worker = new XMLWorker(css, true);
	                XMLParser p = new XMLParser(worker, Charset.forName("UTF-8")); 
	                if(p==null)
	                	System.out.println("p is null");
	                if(s== null)
	                {
	                	System.out.println("nullll");
	                }
	                p.parse(s);
	                
	            } catch (DocumentException e) {
	                //returnStatus = null;
	                Log(Level.WARNING, e);
	                e.printStackTrace();
	            }

	            document.close();
	            byte[] pdfData = baos.toByteArray();
	            
	            finalPdfArray.add(pdfData);
	            
	          /*  if(oldpdf!=null)
	            {
	            addendumoutputstream.write( pdfData );
	            newdata = addendumoutputstream.toByteArray( );
	            }
	            
	            if(newdata!=null)
	            	pdfData=newdata;*/
	            
	            if(finalPdfArray.size()>0)
	            {
	            output= mergeMyFiles(finalPdfArray, output);	
	            pdfData=output.toByteArray();
	            }
	            
	            
	            MongoXmlHandler.getInstance().savePDFFile(pdfFileName, pdfData);
	            returnStatus = "success";

	        } catch (IOException e) {
	            //  returnStatus = null;
	            Log(Level.SEVERE, e);
	            //e.printStackTrace();

	        } finally {

	            try {
	                if (document != null) {
	                    document.close();
	                }
	            } catch (Exception e) {
	                //  returnStatus = null;
	                //e.printStackTrace();
	                Log(Level.SEVERE, e);
	            }

	            try {
	                if (bos != null) {
	                    
	                    bos.close();
	                }
	            } catch (IOException e) {
	                //  returnStatus = null;
	                Log(Level.SEVERE, e);
	               // e.printStackTrace();
	            }
	            
	        }

	        return returnStatus;
	    }



	 
	 private static ByteArrayOutputStream mergeMyFilesfilesupload(List<byte[]> filesTobeMerges,
				ByteArrayOutputStream byteArrayOutputStream) {
			// TODO Auto-generated method stub
			//System.out.println("Starting To Merge Files...");
			//System.out.println("Total Number Of Files To Be Merged..."+ filesToBeMerged.length + "\n");D:\root\investigationDetails\watermark\duplicateStamp.jpg D:\NEW_INVESTIGATION_21_05-2015\AHIMS\WebContent\new_investigation\images\duplicateStamp.jpg
			
			
			try {
				//Image img = Image.getInstance(InvestigationConfig.fetchfilepath+"duplicateStamp.jpg");	      
			   //  img.setAbsolutePosition(23,725);
			     
			    // Watermark watermark=new Watermark(img, 25, 725);
				int pageOffset = 0;
				ArrayList masterBookMarkList = new ArrayList();

				int fileIndex = 0;
				//String outFile = mergedFileLocation;
				Document document = null;
				PdfCopy writer = null;
				PdfReader reader = null;
				OutputStream output = new ByteArrayOutputStream(); 

				for (fileIndex = 0; fileIndex < filesTobeMerges.size(); fileIndex++) {

					
					/**
					 * Create a reader for the file that we are reading
					 */
					reader = new PdfReader(filesTobeMerges.get(fileIndex),"Administrator".getBytes()); //filesToBeMerged[fileIndex]
			//		System.out.println("Reading File -");

					/**
					 * Replace all the local named links with the actual
					 * destinations.
					 */
					reader.consolidateNamedDestinations();

					/**
					 * Retrieve the total number of pages for this document
					 */
					int totalPages = reader.getNumberOfPages();


					 int n = reader.getNumberOfPages();
					 PdfStamper stamp = new PdfStamper(reader,output);
					  int i = 1;
				      
				      
					if(fileIndex==0)
					{

					      while (i <= n) 
					      {
					    	  
					    	  PdfGState gs = new PdfGState();
						        gs.setFillOpacity(0.8f);
						        
						        PdfContentByte  over = stamp.getUnderContent(i);
						        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
							      
					    	  over.setGState(gs);
					    	  over.beginText();
					    	  over.setFontAndSize(bf, 100);
					    	  over.setColorFill(new BaseColor(255,200,200));	
					    	  over.showTextAligned(Element.ALIGN_CENTER, "Cancelled", 300, 700, 0);
					    	  over.endText();
					    	  stamp.close();
						        
					    	  i++;

					    	   
					      }
						 
				 	
		            
			    //    stamp.close();
			        
					}
					
			        reader = new PdfReader(((ByteArrayOutputStream)output).toByteArray()); 
			        output.flush();
			        
				     
					
					/**
					 * Get the list of bookmarks for the current document If the
					 * bookmarks are not empty, store the bookmarks into a master
					 * list
					 */
					
			//		System.out.println("Checking for bookmarks...");				
					List bookmarks = SimpleBookmark.getBookmark(reader);
					if (bookmarks != null) {
						if (pageOffset != 0)
							SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset,
									null);
						masterBookMarkList.addAll(bookmarks);
			//			System.out.println("Bookmarks found and storing...");
					} else {
			//			System.out.println("No bookmarks in this file...");
					}
					pageOffset += totalPages;

					/**
					 * Merging the files to the first file. If we are passing file1,
					 * file2 and file3, we will merge file2 and file3 to file1.
					 */
					if (fileIndex == 0) {
						/**
						 * Create the document object from the reader
						 */
						document = new Document(reader.getPageSizeWithRotation(1));

						/**
						 * Create a pdf write that listens to this document. Any
						 * changes to this document will be written the file
						 * 
						 * outFile is a location where the final merged document
						 * will be written to.
						 */

						System.out.println("Creating an empty PDF...");
						writer = new PdfCopy(document,
								byteArrayOutputStream);
						/**
						 * Open this document
						 */
						
						//writer.add(watermark);
						document.open();
						//document.add(img);
						//document.add(watermark);
					}
					/**
					 * Add the conent of the file into this document (writer). Loop
					 * through multiple Pages
					 */
			//		System.out.println("Merging File: ");
					PdfImportedPage page;
					for (int currentPage = 1; currentPage <= totalPages; currentPage++) {
						page = writer.getImportedPage(reader, currentPage);					
						writer.addPage(page);					
					}

					/**
					 * This will get the documents acroform. This will return null
					 * if no acroform is part of the document.
					 * 
					 * Acroforms are PDFs that have been turned into fillable forms.
					 */
				//	System.out.println("Checking for Acroforms");
					PRAcroForm form = reader.getAcroForm();
					if (form != null) {
						writer.addDocument(reader);
			//			System.out.println("Acroforms found and copied");
					} else
			//			System.out.println("Acroforms not found for this file");

					System.out.println();
				}
				/**
				 * After looping through all the files, add the master bookmarklist.
				 * If individual PDF documents had separate bookmarks, master
				 * bookmark list will contain a combination of all those bookmarks
				 * in the merged document.
				 */
				if (!masterBookMarkList.isEmpty()) {
					writer.setOutlines(masterBookMarkList);
				//	System.out.println("All bookmarks combined and added");

				} else {
				
					
					//System.out.println("No bookmarks to add in the new file");

				}

				/**
				 * Finally Close the main document, which will trigger the pdfcopy
				 * to write back to the filesystem.
				 */
				if(document!=null)
				document.close();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return byteArrayOutputStream;
		}


	 public synchronized static String saveFileToFTPLocation(String headerString,String footerString,String htmlString,String pdfFileurl, String pdfFtpurl, String pdfFileName, float footerHeight,float headerHeight,ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj) throws IOException, FtpException
		{
			String returnStatus = null;
			String pagewidthprinting=null;
	        String pagewidthheight=null;
			
			//Document document = new Document(new Rectangle(Float.parseFloat(pagewidthprinting), Float.parseFloat(pagewidthheight))); 
	        Document document = null;
	      float  footerHeight1=Float.parseFloat( Config.FOOTER_HEIGHT);
			
			   if(htmlString == null )
		        {
		            Log(Level.SEVERE, "Empty HTML String");
		            return null;
		        }
		        
		        if (pagewidthheight == null) {
		            document = new Document(PageSize.A4);
		        } else {
		            document = new Document(new Rectangle(Float.parseFloat(pagewidthprinting), Float.parseFloat(pagewidthheight))); // specify the page size
		        }
		        
		  
		        
		        htmlString=htmlString.replace("&amp;","&");
		      //for text area line breaks 
		        htmlString=htmlString.replace("\r\n","<br/>");
		        
		        // For 2D ECHO Test
		        htmlString=htmlString.replace("less than","&lt;");
		        htmlString=htmlString.replace("greater than","&gt;");
		        
		        
		        System.out.println("the main html string is :  "+htmlString);
		        
		        List <byte[]> finalPdfArray= new ArrayList<byte[]>();
		      
		    //    String htmlStart="<html><head><meta http-equiv='content-type' content='application/xhtml+xml; charset=UTF-8'/></head><body style=\"font-size:12.0pt; font-family:Arial\"><br /><br /><br /><br />";
		        String htmlStart="<div style=\"font-size:12.0pt; font-family:Times New Roman\">";//"<html><head><meta http-equiv='content-type' content='application/xhtml+xml; charset=UTF-8'/></head><body style=\"font-family:Arial\">";
		        String htmlEnd="</div>";
			// specify the page size
			InputStream s = new ByteArrayInputStream((htmlString).getBytes()); 
			BufferedOutputStream bos = null ;
			List<Element>footerElementList=null;
			List<Element>headerElementList=null;
			//System.out.println("height  "+document.getPageSize().height()+" width :: "+document.getPageSize().width());
			
			try {
				
				if(footerString!=null && (footerString.equals("")==false))
					footerElementList=HTMLWorker.parseToList(new InputStreamReader(new ByteArrayInputStream(footerString.getBytes())), new StyleSheet());
				
				if(headerString != null && (headerString.equals("")==false))
					headerElementList=HTMLWorker.parseToList(new InputStreamReader(new ByteArrayInputStream(headerString.getBytes())), new StyleSheet());
				
				//document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin()+headerHeight, document.bottomMargin()+footerHeight);
				
				/*float headerheightt=document.topMargin();
	            
	            if(document.topMargin()==310.0)
	            {
	            	            	headerHeight=headerHeight+10f ;
	            }
	            */
	         
	            
				document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight, document.bottomMargin() + footerHeight);
	            
				  /* if(document.topMargin()==310.0)
		            {
		            	            	headerHeight=headerHeight+10f ;
		            }
				  
				   if(document.topMargin()==246.0)
		            {
		            	            	headerHeight=headerHeight+15f ;
		            }
				  
				   
				   if (pagewidthheight == null) {
			            document = new Document(PageSize.A4);
			        } else {
			        	
			            document = new Document(new Rectangle(Float.parseFloat(pagewidthheight), Float.parseFloat(pagewidthheight))); // specify the page size
			        }
				   
		            document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight, document.bottomMargin() + footerHeight);
		            */
		            
				System.out.println("Header heights" + document.topMargin() + " " + headerHeight);
	            System.out.println("footerr heights" + document.bottomMargin()  + " " + footerHeight);
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ByteArrayOutputStream output = new ByteArrayOutputStream();
	            
				
				URL urlftp =new URL(pdfFtpurl+"/"+pdfFileName);
				URLConnection urlc=	urlftp.openConnection();
				
			
				
				try
				{
					bos=new BufferedOutputStream(urlc.getOutputStream());
					
				}
				catch(Exception ex)
				{
					System.out.println("catch");
					ex.printStackTrace();
				}
				
				if(bos==null)
				{
					/*String[] folder=pdfFileurl.replace("/", "#").split("#");
					createDirectoryStructure(folder[2],folder);
					*/
					String[] folder=pdfFileurl.replace("/", "#").split("#");
					
					if(folder[2]!=null && folder[2].replace("@", "#").split("#").length>1)
					createDirectoryStructure(folder[2].replace("@", "#").split("#")[1],folder);
					else
					createDirectoryStructure(folder[2],folder);	
					
					bos=new BufferedOutputStream(urlc.getOutputStream());
				}
				
				
				try {
					
					PdfWriter writer =PdfWriter.getInstance(document,	bos);
					EndPage endPage=new  EndPage();
					endPage.footerElementList=footerElementList;
					endPage.headerElementList=headerElementList;
					writer.setPageEvent(endPage);	
					document.open();
					
					
					

			        
			        CSSResolver cssResolver =
	                        XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
	         
	                
	                XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	                fontProvider.register(PropertiesHelper.getFontFile(), "Arial");
	                //fontProvider.register("resources/fonts/PT_Serif-Web-Regular.ttf", "Serif");
	                CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
	                
	                // HTML
	                HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
	                htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
	                htmlContext.setImageProvider(new Base64ImageProvider());
	         
	                // Pipelines
	                PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
	                HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
	                CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
	                
	                // XML Worker
	                XMLWorker worker = new XMLWorker(css, true);
	                XMLParser p = new XMLParser(worker, Charset.forName("UTF-8"));
	                if(p==null)
	                	System.out.println("p is null");
	                if(s== null)
	                {
	                	System.out.println("nullll");
	                }
	                p.parse(s);
	                
	            } 
					
				
					/*List<Element> elementlist = HTMLWorker.parseToList(new InputStreamReader(s), new StyleSheet()); 
				        for (int i = 0; i < elementlist.size(); i++) 
				        {
							Element element = (Element)elementlist.get(i); 
					        if(elementlist.size()>0) 
					        { 
					                document.add(element);  
					        } 
				    	
						}
				        
				         */
		             
				catch (DocumentException e) 
				{
					e.printStackTrace();
				}
				
				document.close();
				returnStatus = "success";
				Log(Level.INFO, "end of function saveFileToFTPLocation : PDF File Saved on FTP server.");
				
			} 
			
			finally
			{

				try
				{
				if(document!=null)
					document.close();
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				try
				{
				if(bos!=null)
					bos.close();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			
			return returnStatus;
		}

	 
	 
	 public synchronized static String saveFileToFTPLocationnew(String headerString, String footerString, String htmlString, String pdfFileName, float footerHeight, float headerHeight, float headerwidth, float footerWidth, String pageHeight, String pageWidth, InputStream oldpdf,String fileuploaddata,String isfileuploadprint,String isfirstpageprint,String pdfFtpurl,String pdfFileurl,ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj) throws IOException, DocumentException {
	        Document document = null;
	       /* Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::pageHeight ::" + pageHeight);
	        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::pagewidth ::" + pageWidth);
	        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::headerHeight ::" + headerHeight);
	        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::headerWidth ::" + headerwidth);
	        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::footerHeight ::" + footerHeight);
	        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::footerWidth ::" + footerWidth);*/
	       // headerHeight=Float.parseFloat( Config.HEADER_HEIGHT);
	        
	       
	        
	      float  footerHeight1=Float.parseFloat( Config.FOOTER_HEIGHT);
	        String returnStatus = null;
	        if(htmlString == null )
	        {
	            Log(Level.SEVERE, "Empty HTML String");
	            return null;
	        }
	        
	        if (pageHeight == null) {
	            document = new Document(PageSize.A4);
	        } else {
	            document = new Document(new Rectangle(Float.parseFloat(pageWidth), Float.parseFloat(pageHeight))); // specify the page size
	        }
	        
	        
	        // for dynamic test template( canned file issue)
	     
	     /*   htmlString=htmlString.replace("<","&lt;");
	        htmlString=htmlString.replace(">","&gt;");
	     */
	        
	      /*  htmlString=htmlString.replace("&lt;","<");
	        htmlString=htmlString.replace("&gt;",">");
	        htmlString=htmlString.replace("&amp;","&");
	      */ 
	        
	        
	        htmlString=htmlString.replace("&amp;","&");
	      //for text area line breaks 
	       // htmlString=htmlString.replace("\r\n","<br/>");
	        
	        
	       // String divv="<div style='height:7px;'></div>";
	        //htmlString=htmlString.replace("\r\n",divv);
	        System.out.println("the main html string is :  "+htmlString);
	        
	        System.out.println(" footer :  "+footerString);

	        
	        List <InputStream> finalPdfArray= new ArrayList<InputStream>();
	        if(oldpdf!=null)
	        {
	        	if(isfirstpageprint!=null && isfirstpageprint.equals("1")) // not attach oldpdf in case of file upload
	            {
	        
	            }
	        	else
	        	{
	        		if(resultEntryVOGroupByValidatedByObj.getIsaddendumfirstreportprint()!=null && resultEntryVOGroupByValidatedByObj.getIsaddendumfirstreportprint().equals("1"))
	        		{
	        			if(resultEntryVOGroupByValidatedByObj.getOldReportUrl()!=null && !resultEntryVOGroupByValidatedByObj.getOldReportUrl().equals(""))
	        			{
	        				if(resultEntryVOGroupByValidatedByObj.getOldReportUrl()!=null && !resultEntryVOGroupByValidatedByObj.getOldReportUrl().equals(""))
	        			insertoldreports(resultEntryVOGroupByValidatedByObj.getRequisitionDNo(),resultEntryVOGroupByValidatedByObj.getOldReportUrl(),resultEntryVOGroupByValidatedByObj.getChangeCount(),resultEntryVOGroupByValidatedByObj.getPatCRNo());
	        			}
	        			oldpdf=null;
	        		}
	        		else
	        		{
	        		finalPdfArray.add(oldpdf);
	        		}
	        		
	        		
	        	}
	        	
	         }

	        //new addendum report output stream
	     /*   ByteArrayOutputStream addendumoutputstream = new ByteArrayOutputStream( );
	        byte newdata[] = null;
	        if(oldpdf!=null && !oldpdf.equals("null"));
	        addendumoutputstream.write( oldpdf );*/
	       
	        
	        
	    //    BaseFont bf = BaseFont.createFont("/DataProcessing/FreeSans.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	   //     Font f = new Font(bf, 12);
	    //    String htmlStart="<html><head><meta http-equiv='content-type' content='application/xhtml+xml; charset=UTF-8'/></head><body style=\"font-size:12.0pt; font-family:Arial\"><br /><br /><br /><br />";
	        String htmlStart="<div style=\"font-size:12.0pt; font-family:Times New Roman\">";//"<html><head><meta http-equiv='content-type' content='application/xhtml+xml; charset=UTF-8'/></head><body style=\"font-family:Arial\">";
	        String htmlEnd="</div>";
	       // String htmlEnd="</html>";
	    //	Paragraph p = new Paragraph(htmlStart+htmlString+htmlEnd, f);
//	         String FONT = "C:\\Users\\P.S.KHURANA\\Downloads\\FreeSans.ttf"; // add arial
//	        BaseFont bf = BaseFont.createFont(FONT, BaseFont..IDENTITY_H, BaseFont.EMBEDDED);
//	        Font f = new Font(bf, 12);
//	        Paragraph p = new Paragraph(htmlString, f);
	        InputStream s = new ByteArrayInputStream(htmlString.getBytes());
	        BufferedOutputStream bos = null;
	        List<Element> footerElementList = null;
	        List<Element> headerElementList = null;
	        System.out.println(headerString);
	        try {

	            if (footerString != null && (footerString.equals("") == false)) {
	                footerElementList = HTMLWorker.parseToList(new InputStreamReader(new ByteArrayInputStream(footerString.getBytes())), new StyleSheet());
	                
	            }

	            if (headerString != null && (headerString.equals("") == false)) {
	                headerElementList = HTMLWorker.parseToList(new InputStreamReader(new ByteArrayInputStream(headerString.getBytes())), new StyleSheet());
	            }

	           /* float headerheightt=document.topMargin();
	            
	            if(document.topMargin()==310.0)
	            {
	            	            	headerHeight=headerHeight+10f ;
	            }*/
	            
	            
	            
	            String addfooter=resultEntryVOGroupByValidatedByObj.getIsextrafooterreq() ;
	            String addfooternote=resultEntryVOGroupByValidatedByObj.getIsextrafooterreqval();
	            
	            if(addfooter!=null && addfooter.equals("1"))
	        	{
	        		if(addfooternote!=null && !addfooternote.equals(""))
	        		{
	        			footerHeight=80;
	        		}
	        	
	        	}
	            
	            if(resultEntryVOGroupByValidatedByObj.getLaboratoryCode()!=null && !resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("") && (resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("10010")|| resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("10042") || resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("10039") || resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("10004")|| resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("10007") || resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("10009")) && (resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914") || resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")))
	            {
	            	footerHeight=73;
	            }
	           
	            
	           
	            
	            
	            document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight, document.bottomMargin() + footerHeight);
	            
	            /*if(document.topMargin()==310.0)
	            {
	            	            	headerHeight=headerHeight+10f ;
	            }
	           
	            if(document.topMargin()==246.0)
	            {
	            	            	headerHeight=headerHeight+15f ;
	            }
	            
	           
	            if (pageHeight == null) {
		            document = new Document(PageSize.A4);
		        } else {
		            document = new Document(new Rectangle(Float.parseFloat(pageWidth), Float.parseFloat(pageHeight))); // specify the page size
		        }
	            
	            document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight, document.bottomMargin() + footerHeight);
	            */
	            System.out.println("Header heights" + document.topMargin() + " " + headerHeight);
	            System.out.println("footerr heights" + document.bottomMargin()  + " " + footerHeight);
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ByteArrayOutputStream output = new ByteArrayOutputStream();
	            boolean isfirstpageprintornot=false;
	            
				
					URL urlftp =new URL(pdfFtpurl+"/"+pdfFileName);
					URLConnection urlc=	urlftp.openConnection();
					
						try
					{
						bos=new BufferedOutputStream(urlc.getOutputStream());
						
					}
					catch(Exception ex)
					{
						System.out.println("catch");
						ex.printStackTrace();
					}
					
						if(bos==null)
					{
						/*String[] folder=pdfFileurl.replace("/", "#").split("#");
						createDirectoryStructure(folder[2],folder);
						*/
						String[] folder=pdfFileurl.replace("/", "#").split("#");
						
						if(folder[2]!=null && folder[2].replace("@", "#").split("#").length>1)
						createDirectoryStructure(folder[2].replace("@", "#").split("#")[1],folder);
						else
						createDirectoryStructure(folder[2],folder);	
						
						bos=new BufferedOutputStream(urlc.getOutputStream());
					}
					
	            try {
	               // System.out.println(htmlString);
	            	
	                //PdfWriter writer = PdfWriter.getInstance(document, baos); chnaged for ftp
	            	PdfWriter writer = PdfWriter.getInstance(document, bos);
	                EndPage endPage = new EndPage();
	                endPage.footerElementList = footerElementList; 
	                endPage.headerElementList = headerElementList;
	                writer.setPageEvent(endPage);
	                document.open();
	/*                endPage.onCloseDocument(writer, document);
	                */
	              // document.addHeader("", arg1)
	                
	               // latest change to print DELTA- 2 lines by adding htm head info
//	                XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
//	               // worker.parseXHtml(writer, document, s);
//	                worker.parseXHtml(writer, document, s,Charset.forName("cp1252"));

	                
	                ///DataProcessing/
	                
	                /*this change didn't work
	                 * 
	                 *    ITextRenderer renderer = new ITextRenderer();
	                     renderer.getFontResolver().addFont("c:/work/fonts/TIMES.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	               */
	                
	                
	                
	              /* this change didn't work
	               * 
	               *  XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
	                InputStream is = new ByteArrayInputStream(htmlString.getBytes("UTF-8"));
	                worker.parseXHtml(writer, document, is, Charset.forName("UTF-8"));*/
	                
	              /*  this change didn't work
	               *  XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	                fontImp.register("/DataProcessing/FreeSans.ttf");
	                XMLWorkerHelper.getInstance().parseXHtml(writer, document,
	                        new FileInputStream(htmlString), null, Charset.forName("UTF-8"), fontImp);*/
	                
	                
	             
	                
	                
	                
	                
	               /* this change didn't work*/
	                 // document.add(p);
	                
	                
//	               HTMLWorker htmlWorker = new HTMLWorker(document);
//	                htmlWorker.parse(new StringReader(p.toString()));
//	               
	                
	                
	                	
//	              String FONT = "C:\\Users\\P.S.KHURANA\\Downloads\\arial.ttf"; // add arial
//	             BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//	             Font f = new Font(bf, 12);
//	             Paragraph p1 = new Paragraph("",f);
//	                
//	                ElementList list = XMLWorkerHelper.parseToElementList(htmlString, null);
//	                for (Element element : list) {
//	                    p1.add(element);
//	                }
//	                
//	              // Paragraph para = new Paragraph(p1.toString(), f);
//	               document.add(p1);
	        /*        original parsing  process      */
	              
//	         List<Element> elementlist =  HTMLWorker.parseToList(new InputStreamReader(s), new StyleSheet());
//	                for (int i = 0; i < elementlist.size(); i++) {
//	                    Element element = (Element) elementlist.get(i);
//	                    if (elementlist.size() > 0) {
//	                        document.add(element);
//	                    }
//	                }
	                CSSResolver cssResolver =
	                        XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
	         
	                
	                XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
	                fontProvider.register(PropertiesHelper.getFontFile(), "Arial");
	                //fontProvider.register("resources/fonts/PT_Serif-Web-Regular.ttf", "Serif");
	                CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
	                
	                // HTML
	                HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
	                htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
	                htmlContext.setImageProvider(new Base64ImageProvider());
	         
	                // Pipelines
	                PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
	                HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
	                CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
	                
	                // XML Worker
	                XMLWorker worker = new XMLWorker(css, true);
	                XMLParser p = new XMLParser(worker, Charset.forName("UTF-8")); 
	                if(p==null)
	                	System.out.println("p is null");
	                if(s== null)
	                {
	                	System.out.println("nullll");
	                }
	                p.parse(s);
	                
	            } catch (DocumentException e) {
	                //returnStatus = null;
	                Log(Level.WARNING, e);
	                e.printStackTrace();
	            }

	            document.close();
	          //  byte[] pdfData = baos.toByteArray();
	            
	         // OutputStream outputt = new BufferedOutputStream(bos);
	          //  ByteArrayOutputStream bos1 = (ByteArrayOutputStream)outputt;
	           
	          //  ByteArrayInputStream is=new ByteArrayInputStream();
	            

	            
	            
				URL urlftp1 =new URL(pdfFtpurl+"/"+pdfFileName);
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

	         /*   
	            try { 
	    	    	//name = "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";
	    	        // Create file  
	    	    	String path="E:/";
	    	    	//String path1=path+name;
	    	    	//String finalpath=path1+".pdf";
	    	    	
	    	    	//byte[] bytearray=org.apache.commons.codec.binary.Base64.decodeBase64(name);
	    	        OutputStream out = new FileOutputStream("E:/out.pdf");
	    	        out.write(pdfData1);
	    	        out.close();  
	    	    } catch (Exception e) {  
	    	        System.err.println("Error: " + e.getMessage());  
	    	    }  */
	            
	            
	            
	            
	            
	        //    byte[] pdfData = baos.toByteArray();

	            
	            if(isfirstpageprint!=null && isfirstpageprint.equals("1"))
	            {
	            	isfirstpageprintornot=true;
	            }
	            else
	            {
	            	InputStream is1 = null;
	                is1 = new ByteArrayInputStream(pdfData1);
	            	finalPdfArray.add(is1);
	            }
	            
	            
	            
	        /*    if(oldpdf!=null )
	            {
	            	
	            	 if(isfirstpageprint!=null && isfileuploadprint.equals("1"))
	                   {
	                 		 output= mergeMyFilesfilesupload(finalPdfArray, output);	
	                         pdfData=output.toByteArray();
	             
	                    }
	            	 else
	            	 {
	                output= mergeMyFiles(finalPdfArray, output);	
	                pdfData=output.toByteArray();
	                }
	            	 
	            }*/
	            
	            
	            if(isfileuploadprint!=null && !isfileuploadprint.equals("0"))
	            {
	            	if(fileuploaddata!=null && !fileuploaddata.equals(""))
	            {
	            	
	            	if(fileuploaddata.contains("#@#"))
	            	{
	            		for(int k=0;k<fileuploaddata.split("#@#").length;k++)
	            		{
	            			 byte fileupload[]=null;
	                         fileupload = org.apache.commons.codec.binary.Base64.decodeBase64(fileuploaddata.split("#@#")[k]);
	                          
	                         if(fileupload!=null)
	                         {
	                        	  	InputStream is1 = null;
	            	                is1 = new ByteArrayInputStream(fileupload);
	            	            	finalPdfArray.add(is1);
	            	            
	                         }
	                       
	            		}
	            	}
	            	else
	            	{
	            		 byte fileupload[]=null;
	                    fileupload = org.apache.commons.codec.binary.Base64.decodeBase64(fileuploaddata);
	                     
	                    if(fileupload!=null)
	                    {
	                     	InputStream is1 = null;
     	                is1 = new ByteArrayInputStream(fileupload);
     	            	finalPdfArray.add(is1);
	                    }

	            	}
	            	
	            }
	            }
	            

	            
	          /*  if(oldpdf!=null)
	            {
	            addendumoutputstream.write( pdfData );
	            newdata = addendumoutputstream.toByteArray( );
	            }
	            
	            if(newdata!=null)
	            	pdfData=newdata;*/
	            
	           
	            
	          
	            if(finalPdfArray.size()>0)
	            {
	            	
	            	if(isfirstpageprintornot==true && oldpdf!=null)
	            output= mergeMyFilesFTP(finalPdfArray, output);	
	            	else
	            output= mergeMyFilesFTP(finalPdfArray, output);
	         
	            	/*
	            	   
		            try { 
		    	    	//name = "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";
		    	        // Create file  
		    	    	String path="E:/";
		    	    	//String path1=path+name;
		    	    	//String finalpath=path1+".pdf";
		    	    	
		    	    	//byte[] bytearray=org.apache.commons.codec.binary.Base64.decodeBase64(name);
		    	        OutputStream out = new FileOutputStream("E:/out111.pdf");
		    	        out.write(output.toByteArray());
		    	        out.close();  
		    	    } catch (Exception e) {  
		    	        System.err.println("Error: " + e.getMessage());  
		    	    }*/
		            
	         //   pdfData=output.toByteArray();
	            
	        	URL urlftp11 =new URL(pdfFtpurl+"/"+pdfFileName);
				URLConnection urlc11=	urlftp11.openConnection();
		        BufferedOutputStream bos11 = null;

					try
				{
					bos11=new BufferedOutputStream(urlc11.getOutputStream());
					
				}
				catch(Exception ex)
				{
					System.out.println("catch");
					ex.printStackTrace();
				}
					bos11.write(output.toByteArray());
					
             if (bos11 != null) {
	                    
             	bos11.close();
	                }
					
	            }
	            
	            

			
	            
	        //    MongoXmlHandler.getInstance().savePDFFile(pdfFileName, pdfData);
	            returnStatus = "success";

	        } catch (IOException e) {
	            //  returnStatus = null;
	            Log(Level.SEVERE, e);
	            //e.printStackTrace();

	        } finally {

	            try {
	                if (document != null) {
	                    document.close();
	                }
	            } catch (Exception e) {
	                //  returnStatus = null;
	                //e.printStackTrace();
	                Log(Level.SEVERE, e);
	            }

	            try {
	                if (bos != null) {
	                    
	                    bos.close();
	                }
	            } catch (IOException e) {
	                //  returnStatus = null;
	                Log(Level.SEVERE, e);
	               // e.printStackTrace();
	            }
	            
	        }

	        return returnStatus;
	    }

	 
	 private synchronized static void createDirectoryStructure(String ftpserver, String[] folders) {
			JakartaFTPWrapper ftp = null;
			try {
				 ftp = new JakartaFTPWrapper();
				
			//	HisAppletConfigurator hisAppletConfigurator=new HisAppletConfigurator();
				//new TemplateProcessingClass(1,"101").readingAppletConfiguratorXML(hisAppletConfigurator);
				
				
				/*String ftpUserName=hisAppletConfigurator.getResultprintingusername();
				String ftpUserPassword=hisAppletConfigurator.getResultprintinguserpassword();*/
				
				String ftpUserName= PropertiesHelper.getFTPConnectionUsername();
				String ftpUserPassword=PropertiesHelper.getFTPConnectionPassword();
				
						
				System.out.println("Connecting to " + ftpserver + "ftpUserName :"+ftpUserName+" ftpUserPassword :"+ftpUserPassword);
				if (ftp.connectAndLogin(ftpserver, ftpUserName, ftpUserPassword))
				{
					System.out.println("Connected to " + ftpserver);
					ftp.setPassiveMode(true);
					ftp.changeWorkingDirectory("ftpserver");
					System.out.println("Present Working Directory :"+ftp.pwd());
					for(int i=4;i<folders.length;i++)
					{
							//System.out.println("directory "+folders[i]+" created");
					ftp.mkd(folders[i]);
					ftp.changeWorkingDirectory(folders[i]);
					}
				}
				else 
				{
					System.out.println("Unable to connect to" + ftpserver);
				}
				
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		
		finally {
			try
			{
				if(ftp!=null)
				{
				ftp.logout();
				ftp.disconnect();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		}

	 
	 private static ByteArrayOutputStream mergeMyFilesFTP(List<InputStream> filesTobeMerges,
				ByteArrayOutputStream byteArrayOutputStream) {
			// TODO Auto-generated method stub
		//	System.out.println("Starting To Merge Files...");
			//System.out.println("Total Number Of Files To Be Merged..."+ filesToBeMerged.length + "\n");D:\root\investigationDetails\watermark\duplicateStamp.jpg D:\NEW_INVESTIGATION_21_05-2015\AHIMS\WebContent\new_investigation\images\duplicateStamp.jpg
			
			
			try {
				//Image img = Image.getInstance(InvestigationConfig.fetchfilepath+"duplicateStamp.jpg");	      
			   //  img.setAbsolutePosition(23,725);
			     
			    // Watermark watermark=new Watermark(img, 25, 725);
				int pageOffset = 0;
				ArrayList masterBookMarkList = new ArrayList();

				int fileIndex = 0;
				//String outFile = mergedFileLocation;
				Document document = null;
				PdfCopy writer = null;
				PdfReader reader = null;

				for (fileIndex = 0; fileIndex < filesTobeMerges.size(); fileIndex++) {

					
					/**
					 * Create a reader for the file that we are reading
					 */
					reader = new PdfReader(filesTobeMerges.get(fileIndex),"Administrator".getBytes()); //filesToBeMerged[fileIndex]
			//		System.out.println("Reading File -");

					/**
					 * Replace all the local named links with the actual
					 * destinations.
					 */
					reader.consolidateNamedDestinations();

					/**
					 * Retrieve the total number of pages for this document
					 */
					int totalPages = reader.getNumberOfPages();

				
					
					
					
					/**
					 * Get the list of bookmarks for the current document If the
					 * bookmarks are not empty, store the bookmarks into a master
					 * list
					 */
					
					System.out.println("Checking for bookmarks...");				
					List bookmarks = SimpleBookmark.getBookmark(reader);
					if (bookmarks != null) {
						if (pageOffset != 0)
							SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset,
									null);
						masterBookMarkList.addAll(bookmarks);
			//			System.out.println("Bookmarks found and storing...");
					} else {
			//			System.out.println("No bookmarks in this file...");
					}
					pageOffset += totalPages;

					/**
					 * Merging the files to the first file. If we are passing file1,
					 * file2 and file3, we will merge file2 and file3 to file1.
					 */
					if (fileIndex == 0) {
						/**
						 * Create the document object from the reader
						 */
						document = new Document(reader.getPageSizeWithRotation(1));

						/**
						 * Create a pdf write that listens to this document. Any
						 * changes to this document will be written the file
						 * 
						 * outFile is a location where the final merged document
						 * will be written to.
						 */

				//		System.out.println("Creating an empty PDF...");
						writer = new PdfCopy(document,
								byteArrayOutputStream);
						/**
						 * Open this document
						 */
						
						//writer.add(watermark);
						document.open();
						//document.add(img);
						//document.add(watermark);
					}
					/**
					 * Add the conent of the file into this document (writer). Loop
					 * through multiple Pages
					 */
				//	System.out.println("Merging File: ");
					PdfImportedPage page;
					for (int currentPage = 1; currentPage <= totalPages; currentPage++) {
						page = writer.getImportedPage(reader, currentPage);					
						writer.addPage(page);					
					}

					/**
					 * This will get the documents acroform. This will return null
					 * if no acroform is part of the document.
					 * 
					 * Acroforms are PDFs that have been turned into fillable forms.
					 */
				//	System.out.println("Checking for Acroforms");
					PRAcroForm form = reader.getAcroForm();
					if (form != null) {
						writer.addDocument(reader);
						System.out.println("Acroforms found and copied");
					} else
				//		System.out.println("Acroforms not found for this file");

					System.out.println();
				}
				/**
				 * After looping through all the files, add the master bookmarklist.
				 * If individual PDF documents had separate bookmarks, master
				 * bookmark list will contain a combination of all those bookmarks
				 * in the merged document.
				 */
				if (!masterBookMarkList.isEmpty()) {
					writer.setOutlines(masterBookMarkList);
				//	System.out.println("All bookmarks combined and added");

				} else {
			//		System.out.println("No bookmarks to add in the new file");

				}

				/**
				 * Finally Close the main document, which will trigger the pdfcopy
				 * to write back to the filesystem.
				 */
				if(document!=null)
				document.close();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return byteArrayOutputStream;
		}

	 
	 public static byte[] getReport(String filename) throws IOException
		{
			byte[] byteArray=null;
			         
			byteArray = MongoXmlHandler.getInstance().latestFetchFileByte(filename);
			    	
		    return byteArray;
		}

	 
	//check pdf save -- convert string to bytearray thn to pdf
			public static void byteArrayToFile(String name) {  
			    try { 
			    	  
			    	byte[] bytearray=org.apache.commons.codec.binary.Base64.decodeBase64(name);
			        OutputStream out = new FileOutputStream(PropertiesHelper.getFTPSaveTempPDFFilePath());
			        out.write(bytearray);
			        out.close();  
			    } catch (Exception e) {  
			        System.err.println("Error: " + e.getMessage());  
			    }  
			}
			
			public static void byteArrayToFileXML(String name) {  
			    try { 
			    	  
			    	byte[] bytearray=org.apache.commons.codec.binary.Base64.decodeBase64(name);
			        OutputStream out = new FileOutputStream(PropertiesHelper.getFTPSaveTempXMLFilePath());
			        out.write(bytearray);
			        out.close();  
			    } catch (Exception e) {  
			        System.err.println("Error: " + e.getMessage());  
			    }  
			}
			

			
			public static void insertoldreports(String dno,String reporturl,String reportchnageflag,String crno)
			{
				Connection conn = null;
                    try {
					   
					   conn = PGDataHelper.getInstance().getConnection();

					   if (conn == null) {
			               // conn = PGDataHelper.getInstance().createPostgresConnection(PropertiesHelper.getMongoConnectionURI(), ServiceConfiguration.dbUsername, ServiceConfiguration.dbPassword);  
			                conn = PGDataHelper.getInstance().createPostgresConnection();
			                //conn = PGDataHelper.getInstance().createPostgresConnection("jdbc:edb://10.0.2.103:5444/nimsnew", "nimsnew", "nimsnew");  
			            }
			            
					   PreparedStatement pstmt13 = conn.prepareStatement(QueryConfig.Q_hivt_ADDENDUM_HST_TABLE);

					   
					pstmt13.setString(1, dno);
					 pstmt13.setString(2, reporturl);
					 pstmt13.setString(3, reportchnageflag);
					 pstmt13.setString(4, crno);
					    pstmt13.executeUpdate();
				}catch (Exception e) {
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
			}
			
}
