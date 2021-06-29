package new_investigation.masters.controller.utl;

import hisglobal.presentation.ControllerUTIL;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import new_investigation.InvestigationConfig;
import new_investigation.Helper.EndPage;
import new_investigation.masters.controller.fb.PDFPrintingFB;
import new_investigation.transactions.dao.Helper.InvestigationTemplateDataHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFPrintingUTIL extends ControllerUTIL {

	public static void setResultPrintingEssential(HttpServletRequest request,
			PDFPrintingFB fb) {
		// TODO Auto-generated method stub

	}

	public static void getTestEssentialforResultPrinting(
			HttpServletRequest request, PDFPrintingFB fb) {
		// TODO Auto-generated method stub

	}

	public static void getworkOrderListForPdfPrinting(
			HttpServletRequest request, PDFPrintingFB fb) {
		// TODO Auto-generated method stub

	}

	public static void getPdfByteArray(HttpServletRequest request,
			String parameter, HttpServletResponse response,
			String isConfiString, String password) {
		// TODO Auto-generated method stub

	}

	public static void getPdfByteArrayForPrintingTemplate(
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "no-cache");
		Document templateDocument = (Document) request.getSession()
				.getAttribute(request.getParameter("sessionName"));
		String htmlString = "";
		String headerString = "";
		String footerString = "";
		String pageheight = InvestigationConfig.pagewidthheight;
		String pagewidth = InvestigationConfig.pagewidthprinting;
		String headerheight = "100";
		String headerwidth = InvestigationConfig.pagewidthprinting;
		String footerheight = "50";
		String footerwidth = InvestigationConfig.pagewidthprinting;
		// List<Node> imageNodes=null;
		try {
			if (templateDocument.getChildNodes().item(0).getAttributes()
					.getNamedItem("pagewidth") != null) {
				pagewidth = templateDocument.getChildNodes().item(0)
						.getAttributes().getNamedItem("pagewidth")
						.getNodeValue();

				if (pagewidth != null && pagewidth.equals("100%"))
					pagewidth = InvestigationConfig.pagewidthprinting;

			}

			if (templateDocument.getChildNodes().item(0).getAttributes()
					.getNamedItem("pageheight") != null) {
				pageheight = templateDocument.getChildNodes().item(0)
						.getAttributes().getNamedItem("pageheight")
						.getNodeValue();

				if (pageheight.equals("100%"))
					pageheight = InvestigationConfig.pagewidthheight;
			}

			if (templateDocument.getChildNodes().item(0).getAttributes()
					.getNamedItem("headerwidth") != null) {
				headerwidth = templateDocument.getChildNodes().item(0)
						.getAttributes().getNamedItem("headerwidth")
						.getNodeValue();
				if (headerwidth == null) {
					if (headerwidth != null && headerwidth.equals("100%"))
						headerwidth = InvestigationConfig.pagewidthprinting;
				}
			}

			if (templateDocument.getChildNodes().item(0).getAttributes()
					.getNamedItem("headerheight") != null) {
				headerheight = templateDocument.getChildNodes().item(0)
						.getAttributes().getNamedItem("headerheight")
						.getNodeValue();

				if (headerheight.equals("100%"))
					headerheight = InvestigationConfig.pagewidthheight;
			}

			if (templateDocument.getChildNodes().item(0).getAttributes()
					.getNamedItem("footerwidth") != null) {
				footerwidth = templateDocument.getChildNodes().item(0)
						.getAttributes().getNamedItem("footerwidth")
						.getNodeValue();
				if (footerwidth == null) {
					if (footerwidth != null && footerwidth.equals("100%"))
						footerwidth = InvestigationConfig.pagewidthprinting;
				}
			}

			if (templateDocument.getChildNodes().item(0).getAttributes()
					.getNamedItem("footerheight") != null) {
				footerheight = templateDocument.getChildNodes().item(0)
						.getAttributes().getNamedItem("footerheight")
						.getNodeValue();

				if (footerheight.equals("100%"))
					footerheight = InvestigationConfig.pagewidthheight;
			}

			InvestigationTemplateDataHelper configurationCacheManager = InvestigationTemplateDataHelper
					.getInstance();
			Transformer transformer = configurationCacheManager
					.getTransformerObject(InvestigationConfig.XSL_PRINTINGSTYLESHEET_BODY);
			Transformer transformer_header = configurationCacheManager
					.getTransformerObject(InvestigationConfig.XSL_PRINTINGSTYLESHEET_HEADER);
			Transformer transformer_footer = configurationCacheManager
					.getTransformerObject(InvestigationConfig.XSL_PRINTINGSTYLESHEET_FOOTER);

			// imageNodes=ITextImageDisplay.getImagesForPrintingTemplate(templateDocument);

			Source domSource = new DOMSource(templateDocument);
			java.io.CharArrayWriter baos = new java.io.CharArrayWriter();

			StreamResult streamResult = new StreamResult(baos);
			transformer.transform(domSource, streamResult);
			htmlString = baos.toString();
			if (transformer_header != null) {
				baos = new java.io.CharArrayWriter();
				streamResult = new StreamResult(baos);

				transformer_header.transform(domSource, streamResult);
				headerString = baos.toString();
			}

			if (transformer_footer != null) {
				baos = new java.io.CharArrayWriter();
				streamResult = new StreamResult(baos);
				transformer_footer.transform(domSource, streamResult);
				footerString = baos.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("htmlString::" + htmlString);
		BufferedOutputStream bos = null;
		com.itextpdf.text.Document document = null;

		try {
			InputStream s = new ByteArrayInputStream((htmlString).getBytes());

			document = new com.itextpdf.text.Document(new Rectangle(
					Float.parseFloat(pagewidth), Float.parseFloat(pageheight))); // specify
																					// the
																					// page
																					// size

			List<Element> footerElementList = null;
			List<Element> headerElementList = null;
			if (footerString != null && (footerString.equals("") == false))
				footerElementList = HTMLWorker.parseToList(
						new InputStreamReader(new ByteArrayInputStream(
								footerString.getBytes())), new StyleSheet());

			if (headerString != null && (headerString.equals("") == false))
				headerElementList = HTMLWorker.parseToList(
						new InputStreamReader(new ByteArrayInputStream(
								headerString.getBytes())), new StyleSheet());

			document.setMargins(document.leftMargin(), document.rightMargin(),
					document.topMargin() + Float.parseFloat(headerheight),
					document.bottomMargin() + Float.parseFloat(footerheight));
			bos = new BufferedOutputStream(response.getOutputStream());
			PdfWriter writer = PdfWriter.getInstance(document, bos);

			EndPage endPage = new EndPage();
			endPage.footerElementList = footerElementList;
			endPage.headerElementList = headerElementList;
			writer.setPageEvent(endPage);
			document.open();

			List<Element> elementlist = HTMLWorker.parseToList(
					new InputStreamReader(s), new StyleSheet());
			for (int i = 0; i < elementlist.size(); i++) {
				Element element = (Element) elementlist.get(i);
				if (elementlist.size() > 0) {
					document.add(element);
				}

			}

			// if(imageNodes!=null)
			// {
			// ITextImageDisplay.insertImageIntoPDF(document,imageNodes);
			// }

		} catch (DocumentException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
