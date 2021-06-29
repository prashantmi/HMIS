package investigationDesk.transactions.controller.utl;


import hisglobal.utility.Entry;


 
 


import hisglobal.utility.servlets.ServletsUtilityConfig;
import investigationDesk.InvestigationConfig;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import investigationDesk.vo.ResultEntryVOGroupByValidatedBy;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Watermark;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.SimpleBookmark;

/**
 * Servlet implementation class MergeAllPdf
 */
public class MergeAllPdfNewInv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MergeAllPdfNewInv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	System.out.println("Merge All PDF doPost");	
	
	try
	{
	List<ResultEntryVOGroupByValidatedBy> registeredList=(List<ResultEntryVOGroupByValidatedBy>)request.getSession().getAttribute("SESSIONREGISTEREDWORKORDERS");
	List<ResultEntryVOGroupByValidatedBy> unRegisteredList=(List<ResultEntryVOGroupByValidatedBy>)request.getSession().getAttribute("SESSIONUNREGISTEREDWORKORDERS");
	List<InputStream> pdfs = new ArrayList<InputStream>();
	List<Entry> strIsConfidential = new ArrayList<Entry>();
	
	for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj: registeredList)
	{
		URL urlftp=new URL(resultEntryVOGroupByValidatedByObj.getPdfFtpUrl());
		URLConnection urlcon=urlftp.openConnection();
		pdfs.add(urlcon.getInputStream());
		strIsConfidential.add(new Entry(resultEntryVOGroupByValidatedByObj.getIsconfidential(),resultEntryVOGroupByValidatedByObj.getClinicianName()));
		
	}
	for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByUnObj: unRegisteredList)
	{
		URL urlftp=new URL(resultEntryVOGroupByValidatedByUnObj.getPdfFtpUrl());
		URLConnection urlcon=urlftp.openConnection();
		pdfs.add(urlcon.getInputStream());
		strIsConfidential.add(new Entry(resultEntryVOGroupByValidatedByUnObj.getIsconfidential(),resultEntryVOGroupByValidatedByUnObj.getClinicianName()));
	}
	OutputStream output = response.getOutputStream();
    concatPDFs(pdfs, output, false,strIsConfidential,null);
    System.out.println("Merge All PDF concatPDFs");	
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
	public static void concatPDFs(List<InputStream> streamOfPDFFiles, OutputStream outputStream, boolean paginate,List<Entry> strIsConfidential, String strMode) {

	    Document document = new Document();
	   
	    Watermark watermark=null;
	    try {
	   
	    
	      List<InputStream> pdfs = streamOfPDFFiles;
	      List<PdfReader> readers = new ArrayList<PdfReader>();
	      int totalPages = 0;
	      Iterator<InputStream> iteratorPDFs = pdfs.iterator();

	      int count=0;
	      // Create Readers for the pdfs.
	      while (iteratorPDFs.hasNext()) { 	  
	    	  
	    	
	        InputStream pdf = iteratorPDFs.next();
	        PdfReader pdfReader =null;	        
	        //if password protected pdf file is generated 
	        pdfReader=new PdfReader(pdf,"Administrator".getBytes());
	        readers.add(pdfReader);
	        totalPages += pdfReader.getNumberOfPages();
	        
	       count++; 
	      }
	      // Create a writer for the outputstream
	      PdfWriter writer = PdfWriter.getInstance(document, outputStream);	     
	      document.open();	      
	     // document.add(watermark);	      
	      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	      PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
	      PdfContentByte cb1 = writer.getDirectContentUnder();
	     // Image img = Image.getInstance(InvestigationConfig.fetchfilepath+"DuplicateCopyStamp.jpg");
	     // img.setAbsolutePosition(25, 725);
	      cb1.beginText();
	    //  cb1.addImage(img);
	      cb1.endText();
	      // data
	      PdfImportedPage page;
	      int currentPageNumber = 0;
	      int pageOfCurrentReaderPDF = 0;
	      Iterator<PdfReader> iteratorPDFReader = readers.iterator();

	      // Loop through the PDF files and add to the output.
	      while (iteratorPDFReader.hasNext()) {
	        PdfReader pdfReader = iteratorPDFReader.next();	   
	        
	        // Create a new page in the target for each source page.
	        while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) 
	        {
	           	
	         document.newPage();
	               
	          pageOfCurrentReaderPDF++;
	          currentPageNumber++;
	          
	          page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);       
	          cb.addTemplate(page, 0, 0);	          
	          // Code for pagination.
	          if (paginate) {	        	  
	            cb.beginText();	            
	            cb.add(cb1);
	            cb.setFontAndSize(bf, 9);
	            cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
	            cb.endText();
	          }
	          
	          
	        }
	        pageOfCurrentReaderPDF = 0;
	      }
	      
	     
	      
	   
	      outputStream.flush();
	      document.close();	      
	      outputStream.close();	    
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (document.isOpen())
	        document.close();
	      try {
	        if (outputStream != null)
	          outputStream.close();
	      } catch (IOException ioe) {
	        ioe.printStackTrace();
	      }
	    }
	  }

	public static void concatallPDFs(List<byte[]> pdfs, OutputStream output, String strMode,HttpServletRequest request)
	{
		PdfReader reader=null;
		PdfStamper stamp =null;
		 
		HttpSession session=request.getSession();
	try
	{
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		ByteArrayOutputStream byteArrayOutputStreamMerge=new ByteArrayOutputStream();
		//mergeMyFiles(filesTobeMerges, mergedFileLocation);/root/investigationDetails/watermark/duplicateStamp.jpg
		System.out.println("start to merge");
		byteArrayOutputStreamMerge=mergeMyFiles(pdfs, byteArrayOutputStream);
		System.out.println("end to merge");
		if(strMode!=null && strMode.equals("DUPLICATEDPRINTING"))
		{
			reader=new PdfReader(byteArrayOutputStream.toByteArray());
			 int n = reader.getNumberOfPages();
			stamp = new PdfStamper(reader, 
		    		  output);
		      int i = 1;
		      PdfContentByte under;
		      PdfContentByte over;
		       
		     // String imageUrl = "/HISInvestigationG5/WebContent/investigationDesk/images/" +
			          //   "duplicateStamp.jpg";  <img src='/HISInvestigationG5/hisglobal/css/close.png' '>

				 
			         //  Image img = Image.getInstance(new URL(imageUrl));
		    
		      //Modify for Image Getting From Config By Pathan Basha on 10-06-2015
		      Image img = Image.getInstance(session.getServletContext().getRealPath(InvestigationConfig.WATERMARK_IMAGE_FOR_DUPLICATE_REPORT_PRINTING));
		     
		     img.setBackgroundColor(Color.white);		      
		      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI, BaseFont.EMBEDDED);
		
		      
		     img.setAbsolutePosition(25, 700);
		     
		
		      while (i <= n) 
		      {
		        // Watermark under the existing page
		        under = stamp.getUnderContent(i);        
		        under.addImage(img);  
		        under.setColorFill(new Color(0xC0, 0xC0, 0xC0));
		        under.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, false), 54);   
		        under.showTextAligned(Element.ALIGN_CENTER, "Duplicate Report", 295f, 552f, 40f);
		        
		        // Text over the existing page under.showTextAlignedKerned(alignment, text, x, y, rotation);
		        over = stamp.getOverContent(i);
		        over.beginText();       
		        over.setFontAndSize(bf, 18);        
		        //over.showText("This Is Duplicate File");over.showTextAlignedKerned(0,"This Is Duplicate File", 20, 800, 0); over.showText("page " + i);
		        over.endText();    
		        i++;
		      }
		    
		      stamp.close();
		}
		else
		{
		   System.out.println(" output write:"+byteArrayOutputStreamMerge.toByteArray().length);
			output.write(byteArrayOutputStreamMerge.toByteArray());
		}
			}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		
	}
	finally
	{
		
			if(reader!=null){
				reader.close();
			}
			if(stamp!=null){
				try {
					stamp.close();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
	}
	
	private static ByteArrayOutputStream mergeMyFiles(List<byte[]> filesTobeMerges,
			ByteArrayOutputStream byteArrayOutputStream) {
		// TODO Auto-generated method stub
		System.out.println("Starting To Merge Files...");
		//System.out.println("Total Number Of Files To Be Merged..."+ filesToBeMerged.length + "\n");D:\root\investigationDetails\watermark\duplicateStamp.jpg D:\investigationDesk_21_05-2015\AHIMS\WebContent\investigationDesk\images\duplicateStamp.jpg
		
		
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
				System.out.println("Start to read fies -");
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
				System.out.println("total pages"+totalPages);
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
					System.out.println("Adding Page to PDF: " + currentPage);
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
					writer.copyAcroForm(reader);
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

	

	public static void concatallPDFs1(List<byte[]> pdfs, OutputStream output, String strMode,HttpServletRequest request)
	{
		PdfReader reader=null;
		PdfStamper stamp =null;
		 
		HttpSession session=request.getSession();
	try
	{
		
		
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		ByteArrayOutputStream byteArrayOutputStreamMerge=new ByteArrayOutputStream();
		//mergeMyFiles(filesTobeMerges, mergedFileLocation);/root/investigationDetails/watermark/duplicateStamp.jpg
		
		byteArrayOutputStreamMerge=mergeMyFiles(pdfs, byteArrayOutputStream);
		
		if(strMode!=null && strMode.equals("DUPLICATEDPRINTING"))
		{
			
                   System.out.println("DUPLICATEDPRINTINGDUPLICATEDPRINTING chandann");
			reader=new PdfReader(byteArrayOutputStream.toByteArray());
			 int n = reader.getNumberOfPages();
			stamp = new PdfStamper(reader, 
		    		  output);
		      int i = 1;
		      PdfContentByte under;
		      PdfContentByte over;
		       
		     // String imageUrl = "/HISInvestigationG5/WebContent/new_investigation/images/" +
			          //   "duplicateStamp.jpg";  <img src='/HISInvestigationG5/hisglobal/css/close.png' '>

				 
			         //  Image img = Image.getInstance(new URL(imageUrl));
		    
		      //Modify for Image Getting From Config By Pathan Basha on 10-06-2015
		    //c  Image img = Image.getInstance(session.getServletContext().getRealPath(InvestigationConfig.WATERMARK_IMAGE_FOR_DUPLICATE_REPORT_PRINTING));
		     
		  //c   img.setBackgroundColor(Color.white);		      
		      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI, BaseFont.EMBEDDED);
		      
		     //img.setAbsolutePosition(60, 555);
		//c      img.setAbsolutePosition(405,710);
		      
		     //change by ashu
		     
		     //Image img1 = Image.getInstance(session.getServletContext().getRealPath(InvestigationConfig.IMAGE_FOR_HEADER));
		     
		  //c   img.setBackgroundColor(Color.white);		      
		      BaseFont bf1 = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI, BaseFont.EMBEDDED);
		
		      
		   //  img1.setAbsolutePosition(20, 735);
		     
		
		      while (i <= n) 
		      {
		    	  
		    	  over = stamp.getOverContent(i);
		    	 // stamp.get
		          over.beginText();
		          over.setFontAndSize(bf, 11);
		          over.setTextMatrix(30, 30);
		         // over.showText("page " + i +" of "+ n);
		         // String ove="Page " + i +" of "+ n;
		          String ove="";
		          // String endOfReport = "********** END OF THE REPORT **********";
		          //over.setFontAndSize(bf, 25);
		       //   over.showTextAligned(Element.ALIGN_RIGHT, ove, 230, 430, 45);
		          //over.showTextAligned(Element.ALIGN_BOTTOM, ove, 250f, 50f, 0f);
		          over.showTextAligned(Element.ALIGN_BOTTOM, ove, 250f, 30f, 0f);
		          /*if(i == n)
		        	  over.showTextAligned(Element.ALIGN_BOTTOM, endOfReport, 170f, 100f, 0f);*/
		          //over.showTextAligned(Element.ALIGN_CENTER, "Nizam's Institute of Medical Sciences", 350f, 500f, 0f);
		          //over.showTextAligned(Element.ALIGN_CENTER, "Nizam's Institute of Medical Sciences", 350f,800f, 0f);
		         // over.showTextAligned(arg0, arg1, arg2, arg3, arg4);
		          over.endText();
		       // over.endMarkedContentSequence();
		        // Watermark under the existing page
		        under = stamp.getUnderContent(i);        
		     //c   under.addImage(img);  
		        under.setColorFill(new Color(0xC0, 0xC0, 0xC0));
		        under.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, false), 54); 
		        
		        /*under.addImage(img1);  
		        under.setColorFill(new Color(0xC0, 0xC0, 0xC0));
		        under.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, false), 54);*/
		        
		        
		        under.showTextAligned(Element.ALIGN_CENTER, "Duplicate Report", 295f, 552f, 40f);
		        
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
		}
		else
		{
			
			System.out.println("not DUPLICATEDPRINTINGDUPLICATEDPRINTING chandann");
			
			reader=new PdfReader(byteArrayOutputStream.toByteArray());
			 int n = reader.getNumberOfPages();
			stamp = new PdfStamper(reader, 
		    		  output);
		      int i = 1;
		      PdfContentByte under;
		      PdfContentByte over;
		       
		     // String imageUrl = "/HISInvestigationG5/WebContent/new_investigation/images/" +
			          //   "duplicateStamp.jpg";  <img src='/HISInvestigationG5/hisglobal/css/close.png' '>

				 
			         //  Image img = Image.getInstance(new URL(imageUrl));
		    
		      //Modify for Image Getting From Config By Pathan Basha on 10-06-2015
		    //  Image img = Image.getInstance(session.getServletContext().getRealPath(InvestigationConfig.WATERMARK_IMAGE_FOR_DUPLICATE_REPORT_PRINTING));
		     
		   //  img.setBackgroundColor(Color.white);		      
		      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.WINANSI, BaseFont.EMBEDDED);
		
		      
		    // img.setAbsolutePosition(25, 700);
		     
		     System.out.println("chandann no of pages:"+n +" iii value"+i);
		
		      while (i <= n) 
		      {
		    	  
		    	  over = stamp.getOverContent(i);
		    	 // stamp.get
		          over.beginText();
		          over.setFontAndSize(bf, 11);
		          over.setTextMatrix(30, 30);
		         // over.showText("page " + i +" of "+ n);
		          
				     System.out.println("chandann page no"+n +" iii value"+i);

				     
		          //String ove="Page " + i +" of "+ n;
				     String ove="";
		          
		          //String endOfReport = "********** END OF THE REPORT **********";
		          //over.setFontAndSize(bf, 25);
		       //   over.showTextAligned(Element.ALIGN_RIGHT, ove, 230, 430, 45);
		        //  System.out.println("ASHU");
		          //over.showTextAligned(Element.ALIGN_BOTTOM, ove, 250f, 55f, 0f);
		          over.showTextAligned(Element.ALIGN_BOTTOM, ove, 250f, 30f, 0f);
		          
		         /* if(i == n)
		        	  over.showTextAligned(Element.ALIGN_BOTTOM, endOfReport, 170f, 100f, 0f);*/
		         // over.showTextAligned(arg0, arg1, arg2, arg3, arg4);
		          over.endText();
		       // over.endMarkedContentSequence();
		        // Watermark under the existing page
		        under = stamp.getUnderContent(i);        
		     /*   under.addImage(img);  
		        under.setColorFill(new Color(0xC0, 0xC0, 0xC0));
		        under.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, false), 54);   
		        under.showTextAligned(Element.ALIGN_CENTER, "Duplicate Report", 295f, 552f, 40f);
		        */
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
			//output.write(byteArrayOutputStreamMerge.toByteArray());
		}
			}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		
	}
	finally
	{
		
			if(reader!=null){
				reader.close();
			}
			if(stamp!=null){
				try {
					stamp.close();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
	}

}

