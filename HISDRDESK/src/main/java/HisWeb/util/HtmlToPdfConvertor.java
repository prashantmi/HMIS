package HisWeb.util;


import hisglobal.exceptions.HisException;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;






//import org.docx4j.org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.StringReader;
//import org.xhtmlrenderer.pdf.ITextRenderer;


import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;





import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.HtmlEncoder;

// TODO: Auto-generated Javadoc
/**
 * The Class HtmlToPdfConvertor.
 */
@SuppressWarnings("deprecation")
public class HtmlToPdfConvertor {
	// *****************************************************************************************
	// Convert to PDF Document Directly
	// *****************************************************************************************
	/**
	 * Convert html to pdf direct.
	 * 
	 * @param _response
	 *            the _response
	 * @param _htmlCode
	 *            the _html code
	 */
	public static void convertHtmlToPDFDirect(HttpServletResponse _response,
			String _htmlCode) {

		OutputStream os = null;

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder
					.parse(new StringBufferInputStream(_htmlCode));

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);
			renderer.layout();
			os = _response.getOutputStream();
			renderer.createPDF(os);

		} catch (Exception ex) {

			new HisException("Dynamic Reports",
					"HtmlToPdfConvertor.convertHtmlToPDFDirect()",
					ex.getMessage());

		}

	}

	// by ajay to track the error
	/**
	 * Convert html to pdf direct.
	 * 
	 * @param _response
	 *            the _response
	 * @param _htmlCode
	 *            the _html code
	 * @param reportName
	 *            the report name
	 * @param procName
	 *            the proc name
	 */
	public static String convertHtmlToPDFDirect(HttpServletResponse _response,
			String _htmlCode, String reportName, String procName) {

		OutputStream os = null;

		try {
			/*DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			String html="<html><head></head><body>...Your content here...</body>/html>";
			Document doc = builder
					.parse(new StringBufferInputStream(html));
			
			
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);
			renderer.layout();
			os = _response.getOutputStream();
			renderer.createPDF(os);*/
			
			com.itextpdf.text.Document document =new com.itextpdf.text.Document( com.itextpdf.text.PageSize.A4.rotate() , 0f, 0f, 0f, 0f ); // left, right, top, bottom
			    float fntSize, lineSpacing;
			    fntSize = 0.1f;
			    lineSpacing = 0f;
			    Paragraph  p = new Paragraph(new Phrase(lineSpacing,"",FontFactory.getFont(FontFactory.HELVETICA, fntSize)));
			    
			//document.setMargins(0, 0, 0, 0);//document.setMargins(left, right, 0, bottom);
			os = _response.getOutputStream();
			com.itextpdf.text.pdf.PdfWriter pdfWriter =com.itextpdf.text.pdf.PdfWriter.getInstance( document, os );
			document.open();
			document.add(p);
			com.itextpdf.text.html.simpleparser.HTMLWorker htmlWorker =new com.itextpdf.text.html.simpleparser.HTMLWorker( document );
			htmlWorker.parse( new StringReader(_htmlCode) );
			//htmlWorker.parse( new StringReader("<html><head></head><body><h1>hello</h1></body></html>") );
			//document.
			//document.close();
			
			os.close();
			System.out.println("document.toString()"+document.toString());
			return document.toString();


		} catch (Exception ex) {
			ex.printStackTrace();
			new HisException("Dynamic Reports",
					"HtmlToPdfConvertor.convertHtmlToPDFDirect() =>Report Name = "
							+ reportName, ex.getMessage() + ", Proc Name :: "
							+ procName);

		}
		return "";

	}
	public static void convertHtmlToPDFDirectNEW(HttpServletResponse _response,
			String _htmlCode) {

		OutputStream os = null;

		try {
			
			 Document document = new Document();
		        // step 2
		        PdfWriter writer = PdfWriter.getInstance(document, _response.getOutputStream());
		        // step 3
		        document.open();
		        // step 4
		        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
		        		new StringBufferInputStream(_htmlCode)); 
		        //step 5
		        System.out.println("document"+document);
		         document.close();
		 
			
			 

		} catch (Exception ex) {
			ex.printStackTrace();
			new HisException("Dynamic Reports",
					"HtmlToPdfConvertor.convertHtmlToPDFDirect()",
					ex.getMessage());

		}

	}
	
}