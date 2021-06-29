package hisglobal.utility.print;

import hisglobal.exceptions.HisException;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;

import javax.print.PrintService;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;

/**
 * Converts the PDF content into printable format
 */
public class TestPrint {

	private PrinterJob pjob = null;
	
	/**
	 * Constructs the print job based on the input stream
	 * 
	 * @param inputStream
	 * @param jobName
	 * @throws IOException
	 * @throws PrinterException
	 */
	
	public TestPrint(String jobName) throws IOException, PrinterException {
		File f=null;
		try{
			f=new File("c:/print.pdf");
		}
		catch (Exception e) {
			System.out.println("File not found" + e);
		}
		if(f!=null){
			FileInputStream fis = new FileInputStream(f);
			byte[] pdfContent = new byte[fis.available()];
			fis.read(pdfContent, 0, fis.available());
			initialize(pdfContent, jobName);
		}	
	}
	public TestPrint(InputStream inputStream,String jobName) throws IOException, PrinterException {
		File f=null;
		try{
			f=new File("c:/print.pdf");
		}
		catch (Exception e) {
			System.out.println("File not found"+e);
		}
		//if(f!=null){
		FileInputStream fis = new FileInputStream(f);
		fis=(FileInputStream)inputStream;
		byte[] pdfContent = new byte[fis.available()];
		fis.read(pdfContent, 0, fis.available());
		initialize(pdfContent, jobName);
		
		//}	
	}
	public TestPrint(PDFFile pdfFile,String jobName) throws IOException, PrinterException {
		/*File f=null;
		try{
			f=new File("c:/print.pdf");
		}
		catch (Exception e) {
			System.out.println("File not found"+e);
		}*/
		initialize(pdfFile, jobName);
			
	}

	/**
	 * Constructs the print job based on the byte array content
	 * 
	 * @param content
	 * @param jobName
	 * @throws IOException
	 * @throws PrinterException
	 */
	public TestPrint(byte[] content, String jobName) throws IOException, PrinterException {
		initialize(content, jobName);
	}

	/**
	 * Initializes the job
	 * 
	 * @param pdfContent
	 * @param jobName
	 * @throws IOException
	 * @throws PrinterException
	 */
	private void initialize(byte[] pdfContent, String jobName)throws PrinterException{
		try{
			ByteBuffer bb = ByteBuffer.wrap(pdfContent);
			// Create PDF Print Page
			PDFFile pdfFile = new PDFFile(bb);
			PDFPrintPage pages = new PDFPrintPage(pdfFile);
	
			// Create Print Job
			pjob = PrinterJob.getPrinterJob();
			PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
			pjob.setJobName(jobName);
			Book book = new Book();
			book.append(pages, pf, pdfFile.getNumPages());
			pjob.setPageable(book);
	
			// to remove margins
			Paper paper = new Paper();
			paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
			pf.setPaper(paper);
		}
		catch (IOException e) {
			System.out.println("IOException :"+e.getMessage());
			e.printStackTrace();
		}
	}

	private void initialize(PDFFile pdfFile, String jobName) throws PrinterException {
		//ByteBuffer bb = ByteBuffer.wrap(pdfContent);
		// Create PDF Print Page
		//PDFFile pdfFile = new PDFFile(bb);
		try{
			PDFPrintPage pages = new PDFPrintPage(pdfFile);
			
			// Create Print Job
			pjob = PrinterJob.getPrinterJob();
			PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
			pjob.setJobName(jobName);
			Book book = new Book();
			book.append(pages, pf, pdfFile.getNumPages());
			pjob.setPageable(book);
			
			// to remove margins
			Paper paper = new Paper();
			paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
			pf.setPaper(paper);
		}
		catch (Exception e) {
			System.out.println("Exception :"+e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public void print(int copies,PrintService pservice) throws PrinterException {
		// Send print job to default printer
		try{
			pjob.setPrintService(pservice);
			pjob.setCopies(copies);
			pjob.print();
		}
		catch (Exception e) {
			System.out.println("Error in Print()" +e.getMessage());
		}
	}
}

/**
 * Class that actually converts the PDF file into Printable format
 */
class PDFPrintPage implements Printable {

	private PDFFile file;

	PDFPrintPage(PDFFile file) {
		this.file = file;
	}

	public int print(Graphics g, PageFormat format, int index) throws PrinterException {
		int pagenum = index + 1;
		if ((pagenum >= 1) && (pagenum <= file.getNumPages())) {
			Graphics2D g2 = (Graphics2D) g;
			PDFPage page = file.getPage(pagenum);

			// fit the PDFPage into the printing area
			Rectangle imageArea = new Rectangle((int) format.getImageableX(), (int) format.getImageableY(),
					(int) format.getImageableWidth(), (int) format.getImageableHeight());
			g2.translate(0, 0);
			PDFRenderer pgs = new PDFRenderer(page, g2, imageArea, null, null);
			try {
				page.waitForFinish();
				pgs.run();
			} catch (InterruptedException ie) {
				// nothing to do
			}
			return PAGE_EXISTS;
		} else {
			return NO_SUCH_PAGE;
		}
	}
}