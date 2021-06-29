
package opd.transaction.controller.action;

/**
 * @author  CDAC
 */

import hisglobal.presentation.WebUTIL;
//import hisglobal.utility.WatermarkUtility;

import javax.servlet.*;
import javax.servlet.http.*;

import opd.OpdConfig;
import opd.transaction.controller.fb.ConsentHtmlParsingFB;
import opd.transaction.controller.util.ConsentHtmlParsingUTIL;
import opd.transaction.controller.util.ConvertHTMLToPDF;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

// import the iText packages
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/** 
 * 
 *  a servlet that will generate a PDF document
 *  and send the document to the client via the 
 *  ServletOutputStream
 * 
 *  @author Sean C. Sullivan
 * 
 */
public class HtmlConsentToPdfServlet extends HttpServlet
{
	public HtmlConsentToPdfServlet()
	{
		super();
	}

	/**
	 *  
	 * 
	 * we implement doGet so that this servlet will process all 
	 * HTTP GET requests
	 * 
	 * @param req HTTP request object 
	 * @param resp HTTP response object
	 * 
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws javax.servlet.ServletException, java.io.IOException
	{
		ByteArrayOutputStream baosPDF = null;
		PdfWriter docWriter = null;
		Document doc= new Document();
		HttpSession session=WebUTIL.getSession(req);
		try
		{
			/*** Html File Conversion to Pdf ***/
			/***  Note Points
			 * 1. must contain <html> and <body> Tags
			 * 2. dont support class Attribute of Tags
			 * 3. dont support <div>, <br> tags
			 * 4. dont support &nbsp; values 
			 */
			ConsentHtmlParsingFB newFB=new ConsentHtmlParsingFB();
			newFB.setTemplateId((String)req.getParameter("templateId"));
			ConsentHtmlParsingUTIL.parseHtml(newFB,req);
			String strHtmData=(String)session.getAttribute(OpdConfig.OPD_TEMPLATE_CONSENT_HTML_DATA);
			
			ConvertHTMLToPDF converter= new ConvertHTMLToPDF(doc,strHtmData);
			
			
			// Byte Array Output Stream to write to the Response 
			baosPDF = new ByteArrayOutputStream();
			// Creating Pdf Writer of iText to write to Pdf as well as to Byte Reader
			docWriter = PdfWriter.getInstance(doc, baosPDF);
			docWriter.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
			
			// Setting PDF Document
			//doc.addTitle("PRAGYA SHARMA RnD");
			//doc.addKeywords("pdf, itext, Java, open source, http");
			//doc.setPageSize(PageSize.A4);
			//HeaderFooter header = new HeaderFooter(	new Phrase("This is a Header"),false);
			//HeaderFooter footer = new HeaderFooter(	new Phrase("This is a footer."),false);
			//doc.setHeader(header);
			//doc.setFooter(footer);
			
			converter.convertToPDF();
			
			if (doc != null)
				doc.close();
			if (docWriter != null)
				docWriter.close();
			if (baosPDF.size() < 1)
				throw new DocumentException("document has "	+ baosPDF.size()+ " bytes");		

			System.out.println(doc.getPageSize());
			
			//WatermarkUtility.addWaterMarkToPDF(doc, baosPDF);

			// Writing to Response
			ServletOutputStream sos;
			sos = resp.getOutputStream();
			baosPDF.writeTo(sos);
			sos.flush();
			
		}
		catch (DocumentException dex)
		{
			resp.setContentType("text/html");
			PrintWriter writer = resp.getWriter();
			writer.println(
					this.getClass().getName() 
					+ " caught an exception: " 
					+ dex.getClass().getName()
					+ "<br>");
			writer.println("<pre>");
			dex.printStackTrace(writer);
			writer.println("</pre>");
		}
		catch (Exception dex)
		{
			dex.printStackTrace();
		}
		finally
		{
			if (baosPDF != null)
			{
				baosPDF.reset();
			}
		}
	}
}
