package hisglobal.utility.print;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HTMLParsingUTIL;
import hisglobal.utility.HTMLToPDFUTIL;
import hisglobal.utility.HisHTMLParserUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDFServlet extends HttpServlet
{
	public CreatePDFServlet()
	{
		super();
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
	{
		doGet(req, resp);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..do post");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
	{
		ByteArrayOutputStream baosPDF = null;
		PdfWriter docWriter = null;
		Document doc = new Document();
		HttpSession session = WebUTIL.getSession(req);
		String htmlCode="";
		try
		{
			htmlCode=req.getParameter("htmlCode");
			//System.out.println("html code>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+htmlCode);
			htmlCode=HisHTMLParserUtil.freezeHTMLCodeElements(htmlCode);
			//WebUTIL.setAttributeInSession(arg2,ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getHtmlCode());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			//String strHtmData = (String) session.getAttribute(ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE);
			 htmlCode = HTMLParsingUTIL.makeHTMLPDFCompatible(htmlCode);
			
			HTMLToPDFUTIL converter = new HTMLToPDFUTIL(session, doc, htmlCode);
			//ConvertHTMLToPDF converter = new ConvertHTMLToPDF(doc, strHtmData);

			// Byte Array Output Stream to write to the Response 
			baosPDF = new ByteArrayOutputStream();
			
			// Creating Pdf Writer of iText to write to Pdf as well as to Byte Reader
			docWriter = PdfWriter.getInstance(doc, baosPDF);
			docWriter.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
			converter.convertToPDF();
			
			if (doc != null) doc.close();
			if (docWriter != null) docWriter.close();
			if (baosPDF.size() < 1) throw new DocumentException("document has " + baosPDF.size() + " bytes");

			System.out.println(doc.getPageSize());

			// Writing to Response
			/*ServletOutputStream sos;
			sos = resp.getOutputStream();
			baosPDF.writeTo(sos);
			sos.flush();*/
			System.out.println("Path>>>>>>>>>>>>>>>>>>>>>>>>>>>> : "+ new PrintApplet().getCodeBase());
			File f=new File("c:/print.pdf");
			FileOutputStream fos=new FileOutputStream(f);
			baosPDF.writeTo(fos);
			fos.close();
		}
		catch (DocumentException dex)
		{
			resp.setContentType("text/html");
			PrintWriter writer = resp.getWriter();
			writer.println(this.getClass().getName() + " caught an exception: " + dex.getClass().getName() + "<br>");
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
		
		RequestDispatcher rd=req.getRequestDispatcher("/registration/checkaction.cnt?hmode=NEW&modePrint=PRINT");
		if(rd!=null)
			rd.forward(req,resp);
	}
}
