/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import DataHelper.Config;
import DataHelper.PropertiesHelper;
import Logging.ServiceLogger;
import MongoHelper.MongoXmlHandler;
import TemplateHelper.EndPage;

import com.itextpdf.text.BadElementException;
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
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
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

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
	

/*    public synchronized static String saveFileToLocation(String headerString, String footerString, String htmlString, String pdfFileName, float footerHeight, float headerHeight, float headerwidth, float footerWidth, String pageHeight, String pageWidth, byte[] oldpdf) throws IOException, DocumentException {
*/        public synchronized static String saveFileToLocation(String headerString, String footerString, String htmlString, String pdfFileName, float footerHeight, float headerHeight, float headerwidth, float footerWidth, String pageHeight, String pageWidth) throws IOException, DocumentException {
        Document document = null;
       /* Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::pageHeight ::" + pageHeight);
        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::pagewidth ::" + pageWidth);
        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::headerHeight ::" + headerHeight);
        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::headerWidth ::" + headerwidth);
        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::footerHeight ::" + footerHeight);
        Log(Level.INFO, "DefaultPrintingTemplate::saveFileToLocation::footerWidth ::" + footerWidth);*/
       // headerHeight=Float.parseFloat( Config.HEADER_HEIGHT);
      //float  footerHeight1=Float.parseFloat( Config.FOOTER_HEIGHT);
        float  footerHeight1=Float.parseFloat("20");
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
        htmlString=htmlString.replace("\r\n","<br/>");
        System.out.println("the main html string is :  "+htmlString);
        
        List <byte[]> finalPdfArray= new ArrayList<byte[]>();
        //if(oldpdf!=null)
        	//finalPdfArray.add(oldpdf);
        
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

            
            document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight, document.bottomMargin() + footerHeight);
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
            
            finalPdfArray.add(pdfData);
            
          /*  if(oldpdf!=null)
            {
            addendumoutputstream.write( pdfData );
            newdata = addendumoutputstream.toByteArray( );
            }
            
            if(newdata!=null)
            	pdfData=newdata;*/
            
            /*if(oldpdf!=null)
            {
            output= mergeMyFiles(finalPdfArray, output);	
            pdfData=output.toByteArray();
            }*/
            
            
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
		System.out.println("Starting To Merge Files...");
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
				System.out.println("Reading File -");

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
					System.out.println("Bookmarks found and storing...");
				} else {
					System.out.println("No bookmarks in this file...");
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
				System.out.println("Checking for Acroforms");
				PRAcroForm form = reader.getAcroForm();
				if (form != null) {
					writer.addDocument(reader);
					System.out.println("Acroforms found and copied");
				} else
					System.out.println("Acroforms not found for this file");

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
				System.out.println("All bookmarks combined and added");

			} else {
				System.out.println("No bookmarks to add in the new file");

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

	
}
