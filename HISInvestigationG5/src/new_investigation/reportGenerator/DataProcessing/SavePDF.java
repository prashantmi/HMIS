
package new_investigation.reportGenerator.DataProcessing;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
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
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.jscape.inet.ftp.FtpException;

import new_investigation.reportGenerator.DataHelper.JakartaFTPWrapper;
import new_investigation.reportGenerator.DataHelper.PGDataHelper;
import new_investigation.reportGenerator.DataHelper.PropertiesHelper;
import new_investigation.reportGenerator.DataHelper.QueryConfig;
import new_investigation.reportGenerator.Logging.ServiceLogger;
import new_investigation.reportGenerator.MongoHelper.MongoXmlHandler;
import new_investigation.reportGenerator.TemplateHelper.EndPage;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;


public class SavePDF {

	@SuppressWarnings("deprecation")
	public synchronized static String saveFileToLocation(String headerString, String footerString, String htmlString,
			String pdfFileName, float footerHeight, float headerHeight, float headerwidth, float footerWidth,
			String pageHeight, String pageWidth, byte[] oldpdf, String fileuploaddata, String isfileuploadprint,
			String isfirstpageprint, ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj)
					throws IOException, DocumentException {
		Document document = null;

		// float footerHeight1=Float.parseFloat( Config.FOOTER_HEIGHT);
		String returnStatus = null;
		if (htmlString == null) {
			Log(Level.SEVERE, "Empty HTML String");
			return null;
		}

		if (pageHeight == null) {
			document = new Document(PageSize.A4);
		} else {
			document = new Document(new Rectangle(Float.parseFloat(pageWidth), Float.parseFloat(pageHeight))); // specify
			// the
			// page
			// size
		}

		// for dynamic test template( canned file issue)

		htmlString = htmlString.replace("&amp;", "&");
		// for text area line breaks
		// htmlString=htmlString.replace("\r\n","<br/>");

		Log(Level.INFO, "Report HTML Body :  " + htmlString);

		Log(Level.INFO, "Report HTML Footer :  " + footerString);

		List<byte[]> finalPdfArray = new ArrayList<byte[]>();
		if (oldpdf != null) {
			if (isfirstpageprint != null && isfirstpageprint.equals("1")) // not attach oldpdf in case of file upload
			{

			} else {
				if (resultEntryVOGroupByValidatedByObj.getIsaddendumfirstreportprint() != null
						&& resultEntryVOGroupByValidatedByObj.getIsaddendumfirstreportprint().equals("1")) {
					if (resultEntryVOGroupByValidatedByObj.getOldReportUrl() != null
							&& !resultEntryVOGroupByValidatedByObj.getOldReportUrl().equals("")) {
						if (resultEntryVOGroupByValidatedByObj.getOldReportUrl() != null
								&& !resultEntryVOGroupByValidatedByObj.getOldReportUrl().equals(""))
							insertoldreports(resultEntryVOGroupByValidatedByObj.getRequisitionDNo(),
									resultEntryVOGroupByValidatedByObj.getOldReportUrl(),
									resultEntryVOGroupByValidatedByObj.getChangeCount(),
									resultEntryVOGroupByValidatedByObj.getPatCRNo());
					}
					oldpdf = null;
				} else {

					finalPdfArray.add(oldpdf);
				}

			}

		}

		// new addendum report output stream

		String htmlStart = "<div style=\"font-size:12.0pt; font-family:Times New Roman\">";
		
		String htmlEnd = "</div>";

		InputStream s = new ByteArrayInputStream(htmlString.getBytes());
		BufferedOutputStream bos = null;
		List<Element> footerElementList = null;
		List<Element> headerElementList = null;
		Log(Level.INFO, headerString);
		try {

			if (footerString != null && (footerString.equals("") == false)) {
				footerElementList = HTMLWorker.parseToList(
						new InputStreamReader(new ByteArrayInputStream(footerString.getBytes())), new StyleSheet());

			}

			if (headerString != null && (headerString.equals("") == false)) {
				headerElementList = HTMLWorker.parseToList(
						new InputStreamReader(new ByteArrayInputStream(headerString.getBytes())), new StyleSheet());
			}

			document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight,
					document.bottomMargin() + footerHeight);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			boolean isfirstpageprintornot = false;

			try {
				PdfWriter writer = PdfWriter.getInstance(document, baos);
				EndPage endPage = new EndPage();
				endPage.footerElementList = footerElementList;
				endPage.headerElementList = headerElementList;
				writer.setPageEvent(endPage);
				document.open();

				CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

				XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
				fontProvider.register(PropertiesHelper.getFontFile(), "Arial");

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
				p.parse(s);

			} catch (DocumentException e) {
				Log(Level.WARNING, e);
				e.printStackTrace();
			}

			document.close();
			byte[] pdfData = baos.toByteArray();

			if (isfirstpageprint != null && isfirstpageprint.equals("1")) {
				isfirstpageprintornot = true;
			} else {

				finalPdfArray.add(pdfData);
			}

			if (isfileuploadprint != null && !isfileuploadprint.equals("0")) {
				if (fileuploaddata != null && !fileuploaddata.equals("")) {

					if (fileuploaddata.contains("#@#")) {
						for (int k = 0; k < fileuploaddata.split("#@#").length; k++) {
							byte fileupload[] = null;
							fileupload = org.apache.commons.codec.binary.Base64
									.decodeBase64(fileuploaddata.split("#@#")[k]);

							if (fileupload != null)
								finalPdfArray.add(fileupload);

						}
					} else {
						byte fileupload[] = null;
						fileupload = org.apache.commons.codec.binary.Base64.decodeBase64(fileuploaddata);

						if (fileupload != null)
							finalPdfArray.add(fileupload);

					}

				}
			}

			if (finalPdfArray.size() > 0) {

				if (isfirstpageprintornot == true && oldpdf != null)
					output = mergeMyFiles(finalPdfArray, output);
				else
					output = mergeMyFiles(finalPdfArray, output);

				pdfData = output.toByteArray();
			}

			MongoXmlHandler.getInstance().savePDFFile(pdfFileName, pdfData);
			returnStatus = "success";

		} catch (IOException e) {
			Log(Level.SEVERE, e);

		} finally {

			try {
				if (document != null) {
					document.close();
				}
			} catch (Exception e) {
				Log(Level.SEVERE, e);
			}

			try {
				if (bos != null) {

					bos.close();
				}
			} catch (IOException e) {
				Log(Level.SEVERE, e);
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

	// to merge files
	private static ByteArrayOutputStream mergeMyFiles(List<byte[]> filesTobeMerges,
			ByteArrayOutputStream byteArrayOutputStream) {

		try {

			// Watermark watermark=new Watermark(img, 25, 725);
			int pageOffset = 0;
			ArrayList masterBookMarkList = new ArrayList();

			int fileIndex = 0;
			// String outFile = mergedFileLocation;
			Document document = null;
			PdfCopy writer = null;
			PdfReader reader = null;

			for (fileIndex = 0; fileIndex < filesTobeMerges.size(); fileIndex++) {

				reader = new PdfReader(filesTobeMerges.get(fileIndex), "Administrator".getBytes()); // filesToBeMerged[fileIndex]

				reader.consolidateNamedDestinations();

				int totalPages = reader.getNumberOfPages();

				//System.out.println("Checking for bookmarks...");
				List bookmarks = SimpleBookmark.getBookmark(reader);
				if (bookmarks != null) {
					if (pageOffset != 0)
						SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset, null);
					masterBookMarkList.addAll(bookmarks);
				} else {
				}
				pageOffset += totalPages;

				if (fileIndex == 0) {

					document = new Document(reader.getPageSizeWithRotation(1));

					writer = new PdfCopy(document, byteArrayOutputStream);

					document.open();

				}

				//System.out.println("Merging File: ");
				PdfImportedPage page;
				for (int currentPage = 1; currentPage <= totalPages; currentPage++) {
					page = writer.getImportedPage(reader, currentPage);
					writer.addPage(page);
				}

				// System.out.println("Checking for Acroforms");
				PRAcroForm form = reader.getAcroForm();
				if (form != null) {
					writer.addDocument(reader);
					// System.out.println("Acroforms found and copied");
				} else {
					// System.out.println("Acroforms not found for this file");
				}
			}

			if (!masterBookMarkList.isEmpty()) {
				writer.setOutlines(masterBookMarkList);
				// System.out.println("All bookmarks combined and added");

			} else {
				// System.out.println("No bookmarks to add in the new file");

			}

			if (document != null)
				document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream;
	}

	public synchronized static String saveFileToLocation1(String headerString, String footerString, String htmlString,
			String pdfFileName, float footerHeight, float headerHeight, float headerwidth, float footerWidth,
			String pageHeight, String pageWidth, byte[] oldpdf, List<byte[]> finalPdfArray1,
			ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj) throws IOException, DocumentException {
		Document document = null;

		// float footerHeight1=Float.parseFloat( Config.FOOTER_HEIGHT);
		String returnStatus = null;
		if (htmlString == null) {
			Log(Level.SEVERE, "Empty HTML String");
			return null;
		}

		if (pageHeight == null) {
			document = new Document(PageSize.A4);
		} else {
			document = new Document(new Rectangle(Float.parseFloat(pageWidth), Float.parseFloat(pageHeight))); // specify
			// the
			// page
			// size
		}

		// for dynamic test template( canned file issue)

		htmlString = htmlString.replace("&amp;", "&");
		// for text area line breaks
		Log(Level.INFO, "the main html string is :  " + htmlString);

		List<byte[]> finalPdfArray = new ArrayList<byte[]>();

		if (finalPdfArray1.size() > 0) {
			finalPdfArray = finalPdfArray1;
		}
		// new addendum report output stream

		String htmlStart = "<div style=\"font-size:12.0pt; font-family:Times New Roman\">";// "<html><head><meta
		// http-equiv='content-type'
		// content='application/xhtml+xml;
		// charset=UTF-8'/></head><body
		// style=\"font-family:Arial\">";
		String htmlEnd = "</div>";

		InputStream s = new ByteArrayInputStream(htmlString.getBytes());
		BufferedOutputStream bos = null;
		List<Element> footerElementList = null;
		List<Element> headerElementList = null;
		Log(Level.INFO, headerString);
		try {

			if (footerString != null && (footerString.equals("") == false)) {
				footerElementList = HTMLWorker.parseToList(
						new InputStreamReader(new ByteArrayInputStream(footerString.getBytes())), new StyleSheet());
			}

			if (headerString != null && (headerString.equals("") == false)) {
				headerElementList = HTMLWorker.parseToList(
						new InputStreamReader(new ByteArrayInputStream(headerString.getBytes())), new StyleSheet());
			}

			document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight,
					document.bottomMargin() + footerHeight);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			try {
				PdfWriter writer = PdfWriter.getInstance(document, baos);
				EndPage endPage = new EndPage();
				endPage.footerElementList = footerElementList;
				endPage.headerElementList = headerElementList;
				writer.setPageEvent(endPage);
				document.open();

				CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

				XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
				fontProvider.register(PropertiesHelper.getFontFile(), "Arial");
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
				p.parse(s);

			} catch (DocumentException e) {
				Log(Level.WARNING, e);
				e.printStackTrace();
			}

			document.close();
			byte[] pdfData = baos.toByteArray();

			finalPdfArray.add(pdfData);

			if (finalPdfArray.size() > 0) {
				output = mergeMyFiles(finalPdfArray, output);
				pdfData = output.toByteArray();
			}

			MongoXmlHandler.getInstance().savePDFFile(pdfFileName, pdfData);
			returnStatus = "success";

		} catch (IOException e) {
			Log(Level.SEVERE, e);

		} finally {

			try {
				if (document != null) {
					document.close();
				}
			} catch (Exception e) {

				Log(Level.SEVERE, e);
			}

			try {
				if (bos != null) {

					bos.close();
				}
			} catch (IOException e) {
				Log(Level.SEVERE, e);
			}

		}

		return returnStatus;
	}

	private static ByteArrayOutputStream mergeMyFilesfilesupload(List<byte[]> filesTobeMerges,
			ByteArrayOutputStream byteArrayOutputStream) {

		try {

			// Watermark watermark=new Watermark(img, 25, 725);
			int pageOffset = 0;
			ArrayList masterBookMarkList = new ArrayList();

			int fileIndex = 0;
			Document document = null;
			PdfCopy writer = null;
			PdfReader reader = null;
			OutputStream output = new ByteArrayOutputStream();

			for (fileIndex = 0; fileIndex < filesTobeMerges.size(); fileIndex++) {

				reader = new PdfReader(filesTobeMerges.get(fileIndex), "Administrator".getBytes()); // filesToBeMerged[fileIndex]

				reader.consolidateNamedDestinations();

				int totalPages = reader.getNumberOfPages();

				int n = reader.getNumberOfPages();
				PdfStamper stamp = new PdfStamper(reader, output);
				int i = 1;

				if (fileIndex == 0) {

					while (i <= n) {

						PdfGState gs = new PdfGState();
						gs.setFillOpacity(0.8f);

						PdfContentByte over = stamp.getUnderContent(i);
						BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);

						over.setGState(gs);
						over.beginText();
						over.setFontAndSize(bf, 100);
						over.setColorFill(new BaseColor(255, 200, 200));
						over.showTextAligned(Element.ALIGN_CENTER, "Cancelled", 300, 700, 0);
						over.endText();
						stamp.close();

						i++;

					}

				}

				reader = new PdfReader(((ByteArrayOutputStream) output).toByteArray());
				output.flush();

				// System.out.println("Checking for bookmarks...");
				List bookmarks = SimpleBookmark.getBookmark(reader);
				if (bookmarks != null) {
					if (pageOffset != 0)
						SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset, null);
					masterBookMarkList.addAll(bookmarks);
					// System.out.println("Bookmarks found and storing...");
				} else {
					// System.out.println("No bookmarks in this file...");
				}
				pageOffset += totalPages;

				if (fileIndex == 0) {

					document = new Document(reader.getPageSizeWithRotation(1));

					//System.out.println("Creating an empty PDF...");
					writer = new PdfCopy(document, byteArrayOutputStream);

					// writer.add(watermark);
					document.open();

				}

				PdfImportedPage page;
				for (int currentPage = 1; currentPage <= totalPages; currentPage++) {
					page = writer.getImportedPage(reader, currentPage);
					writer.addPage(page);
				}

				PRAcroForm form = reader.getAcroForm();
				if (form != null) {
					writer.addDocument(reader);
					// System.out.println("Acroforms found and copied");
				} else {
					// System.out.println("Acroforms not found for this file");
			}
			}

			if (!masterBookMarkList.isEmpty()) {
				writer.setOutlines(masterBookMarkList);
				// System.out.println("All bookmarks combined and added");

			} else {

				// System.out.println("No bookmarks to add in the new file");

			}

			if (document != null)
				document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream;
	}

	public synchronized static String saveFileToFTPLocation(String headerString, String footerString, String htmlString,
			String pdfFileurl, String pdfFtpurl, String pdfFileName, float footerHeight, float headerHeight,
			ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj) throws IOException, FtpException {
		String returnStatus = null;
		String pagewidthprinting = null;
		String pagewidthheight = null;

		Document document = null;
		// float footerHeight1=Float.parseFloat( Config.FOOTER_HEIGHT);

		if (htmlString == null) {
			Log(Level.SEVERE, "Empty HTML String");
			return null;
		}

		if (pagewidthheight == null) {
			document = new Document(PageSize.A4);
		} else {
			document = new Document(
					new Rectangle(Float.parseFloat(pagewidthprinting), Float.parseFloat(pagewidthheight))); // specify
			// the page
			// size
		}

		htmlString = htmlString.replace("&amp;", "&");
		// for text area line breaks
		htmlString = htmlString.replace("\r\n", "<br/>");

		// For 2D ECHO Test
		htmlString = htmlString.replace("less than", "&lt;");
		htmlString = htmlString.replace("greater than", "&gt;");

		Log(Level.INFO, "the main html string is :  " + htmlString);

		List<byte[]> finalPdfArray = new ArrayList<byte[]>();

		String htmlStart = "<div style=\"font-size:12.0pt; font-family:Times New Roman\">";// "<html><head><meta
		// http-equiv='content-type'
		// content='application/xhtml+xml;
		// charset=UTF-8'/></head><body
		// style=\"font-family:Arial\">";
		String htmlEnd = "</div>";
		// specify the page size
		InputStream s = new ByteArrayInputStream((htmlString).getBytes());
		BufferedOutputStream bos = null;
		List<Element> footerElementList = null;
		List<Element> headerElementList = null;

		try {

			if (footerString != null && (footerString.equals("") == false))
				footerElementList = HTMLWorker.parseToList(
						new InputStreamReader(new ByteArrayInputStream(footerString.getBytes())), new StyleSheet());

			if (headerString != null && (headerString.equals("") == false))
				headerElementList = HTMLWorker.parseToList(
						new InputStreamReader(new ByteArrayInputStream(headerString.getBytes())), new StyleSheet());

			document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight,
					document.bottomMargin() + footerHeight);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			URL urlftp = new URL(pdfFtpurl + "/" + pdfFileName);
			URLConnection urlc = urlftp.openConnection();

			try {
				bos = new BufferedOutputStream(urlc.getOutputStream());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (bos == null) {

				String[] folder = pdfFileurl.replace("/", "#").split("#");

				if (folder[2] != null && folder[2].replace("@", "#").split("#").length > 1)
					createDirectoryStructure(folder[2].replace("@", "#").split("#")[1], folder);
				else
					createDirectoryStructure(folder[2], folder);

				bos = new BufferedOutputStream(urlc.getOutputStream());
			}

			try {

				PdfWriter writer = PdfWriter.getInstance(document, bos);
				EndPage endPage = new EndPage();
				endPage.footerElementList = footerElementList;
				endPage.headerElementList = headerElementList;
				writer.setPageEvent(endPage);
				document.open();

				CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

				XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
				fontProvider.register(PropertiesHelper.getFontFile(), "Arial");

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
				if (p == null)
					System.out.println("p is null");
				if (s == null) {
					System.out.println("nullll");
				}
				p.parse(s);

			}

			catch (DocumentException e) {
				e.printStackTrace();
			}

			document.close();
			returnStatus = "success";
			Log(Level.INFO, "end of function saveFileToFTPLocation : PDF File Saved on FTP server.");

		}

		finally {

			try {
				if (document != null)
					document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (bos != null)
					bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return returnStatus;
	}

	public synchronized static String saveFileToFTPLocationnew(String headerString, String footerString,
			String htmlString, String pdfFileName, float footerHeight, float headerHeight, float headerwidth,
			float footerWidth, String pageHeight, String pageWidth, InputStream oldpdf, String fileuploaddata,
			String isfileuploadprint, String isfirstpageprint, String pdfFtpurl, String pdfFileurl,
			ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedByObj) throws IOException, DocumentException {
		Document document = null;

		// float footerHeight1=Float.parseFloat( Config.FOOTER_HEIGHT);
		String returnStatus = null;
		if (htmlString == null) {
			Log(Level.SEVERE, "Empty HTML String");
			return null;
		}

		if (pageHeight == null) {
			document = new Document(PageSize.A4);
		} else {
			document = new Document(new Rectangle(Float.parseFloat(pageWidth), Float.parseFloat(pageHeight))); // specify
			// the
			// page
			// size
		}

		// for dynamic test template( canned file issue)

		htmlString = htmlString.replace("&amp;", "&");
		// for text area line breaks

		Log(Level.INFO, "Report HTML Body :  " + htmlString);

		Log(Level.INFO, "Report HTML Footer :  " + footerString);

		List<InputStream> finalPdfArray = new ArrayList<InputStream>();
		if (oldpdf != null) {
			if (isfirstpageprint != null && isfirstpageprint.equals("1")) // not attach oldpdf in case of file upload
			{

			} else {
				if (resultEntryVOGroupByValidatedByObj.getIsaddendumfirstreportprint() != null
						&& resultEntryVOGroupByValidatedByObj.getIsaddendumfirstreportprint().equals("1")) {
					if (resultEntryVOGroupByValidatedByObj.getOldReportUrl() != null
							&& !resultEntryVOGroupByValidatedByObj.getOldReportUrl().equals("")) {
						if (resultEntryVOGroupByValidatedByObj.getOldReportUrl() != null
								&& !resultEntryVOGroupByValidatedByObj.getOldReportUrl().equals(""))
							insertoldreports(resultEntryVOGroupByValidatedByObj.getRequisitionDNo(),
									resultEntryVOGroupByValidatedByObj.getOldReportUrl(),
									resultEntryVOGroupByValidatedByObj.getChangeCount(),
									resultEntryVOGroupByValidatedByObj.getPatCRNo());
					}
					oldpdf = null;
				} else {
					finalPdfArray.add(oldpdf);
				}

			}

		}

		// new addendum report output stream
		String htmlStart = "<div style=\"font-size:12.0pt; font-family:Times New Roman\">";// "<html><head><meta
		// http-equiv='content-type'
		// content='application/xhtml+xml;
		// charset=UTF-8'/></head><body
		// style=\"font-family:Arial\">";
		String htmlEnd = "</div>";

		InputStream s = new ByteArrayInputStream(htmlString.getBytes());
		BufferedOutputStream bos = null;
		List<Element> footerElementList = null;
		List<Element> headerElementList = null;
		Log(Level.INFO, "Report HTML Header :: " + headerString);
		try {

			if (footerString != null && (footerString.equals("") == false)) {
				footerElementList = HTMLWorker.parseToList(
						new InputStreamReader(new ByteArrayInputStream(footerString.getBytes())), new StyleSheet());

			}

			if (headerString != null && (headerString.equals("") == false)) {
				headerElementList = HTMLWorker.parseToList(
						new InputStreamReader(new ByteArrayInputStream(headerString.getBytes())), new StyleSheet());
			}

			String addfooter = resultEntryVOGroupByValidatedByObj.getIsextrafooterreq();
			String addfooternote = resultEntryVOGroupByValidatedByObj.getIsextrafooterreqval();

			if (addfooter != null && addfooter.equals("1")) {
				if (addfooternote != null && !addfooternote.equals("")) {
					footerHeight = 80;
				}

			}

			if (resultEntryVOGroupByValidatedByObj.getLaboratoryCode() != null
					&& !resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("")
					&& (resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("10010")
							|| resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("10042")
							|| resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("10039")
							|| resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("10004")
							|| resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("10007")
							|| resultEntryVOGroupByValidatedByObj.getLaboratoryCode().equals("10009"))
					&& (resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914")
							|| resultEntryVOGroupByValidatedByObj.getHospitalCode().equals("22914"))) {
				footerHeight = 73;
			}

			document.setMargins(document.leftMargin(), document.rightMargin(), document.topMargin() + headerHeight,
					document.bottomMargin() + footerHeight);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			boolean isfirstpageprintornot = false;

			String finalUrlStr = pdfFtpurl + "/" + pdfFileName;
			URL urlftp = new URL(finalUrlStr.toString());
			URLConnection urlc = urlftp.openConnection();

			try {
				bos = new BufferedOutputStream(urlc.getOutputStream());

			} catch (Exception ex) {
				// System.out.println("catch");
				ex.printStackTrace();
			}

			if (bos == null) {

				String[] folder = pdfFileurl.replace("/", "#").split("#");

				if (folder[2] != null && folder[2].replace("@", "#").split("#").length > 1)
					createDirectoryStructure(folder[2].replace("@", "#").split("#")[1], folder);
				else
					createDirectoryStructure(folder[2], folder);

				bos = new BufferedOutputStream(urlc.getOutputStream());
			}

			try {

				PdfWriter writer = PdfWriter.getInstance(document, bos);
				EndPage endPage = new EndPage();
				endPage.footerElementList = footerElementList;
				endPage.headerElementList = headerElementList;
				writer.setPageEvent(endPage);
				document.open();

				CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

				XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
				fontProvider.register(PropertiesHelper.getFontFile(), "Arial");
				// fontProvider.register("resources/fonts/PT_Serif-Web-Regular.ttf", "Serif");
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
				if (p == null)
					// System.out.println("p is null");
					if (s == null) {
						// System.out.println("nullll");
					}
				p.parse(s);

			} catch (DocumentException e) {
				Log(Level.WARNING, e);
				e.printStackTrace();
			}

			document.close();

			String finalUrlStr2 = pdfFtpurl + "/" + pdfFileName;
			URL urlftp1 = new URL(finalUrlStr2.toString());
			URLConnection urlc1 = urlftp1.openConnection();
			String userpass1 = "prashantmindia@gmail.com" + ":" + "0.Wiillgags";

			ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
			InputStream is = null;
			try {
				is = urlftp1.openStream();
				byte[] byteChunk = new byte[4897]; // Or whatever size you want to read in at a time.
				int n;

				while ((n = is.read(byteChunk)) > 0) {
					baos1.write(byteChunk, 0, n);
				}
			} catch (IOException e) {
				System.err.printf("Failed while reading bytes from %s: %s", urlftp1.toExternalForm(), e.getMessage());
				e.printStackTrace();
				// Perform any other exception handling that's appropriate.
			} finally {
				if (is != null) {
					is.close();
				}
			}

			byte[] pdfData1 = baos1.toByteArray();

			if (isfirstpageprint != null && isfirstpageprint.equals("1")) {
				isfirstpageprintornot = true;
			} else {
				InputStream is1 = null;
				is1 = new ByteArrayInputStream(pdfData1);
				finalPdfArray.add(is1);
			}

			if (isfileuploadprint != null && !isfileuploadprint.equals("0")) {
				if (fileuploaddata != null && !fileuploaddata.equals("")) {

					if (fileuploaddata.contains("#@#")) {
						for (int k = 0; k < fileuploaddata.split("#@#").length; k++) {
							byte fileupload[] = null;
							fileupload = org.apache.commons.codec.binary.Base64
									.decodeBase64(fileuploaddata.split("#@#")[k]);

							if (fileupload != null) {
								InputStream is1 = null;
								is1 = new ByteArrayInputStream(fileupload);
								finalPdfArray.add(is1);

							}

						}
					} else {
						byte fileupload[] = null;
						fileupload = org.apache.commons.codec.binary.Base64.decodeBase64(fileuploaddata);

						if (fileupload != null) {
							InputStream is1 = null;
							is1 = new ByteArrayInputStream(fileupload);
							finalPdfArray.add(is1);
						}

					}

				}
			}

			if (finalPdfArray.size() > 0) {

				if (isfirstpageprintornot == true && oldpdf != null)
					output = mergeMyFilesFTP(finalPdfArray, output);
				else
					output = mergeMyFilesFTP(finalPdfArray, output);

				pdfFtpurl = "ftp://10.226.24.194/ftpserver/96101/2019/100000/10000/961011900018142";
				URL urlftp11 = new URL(pdfFtpurl + "/" + pdfFileName);
				URLConnection urlc11 = urlftp11.openConnection();
				BufferedOutputStream bos11 = null;

				try {
					bos11 = new BufferedOutputStream(urlc11.getOutputStream());

				} catch (Exception ex) {
					System.out.println("catch");
					ex.printStackTrace();
				}
				bos11.write(output.toByteArray());

				if (bos11 != null) {

					bos11.close();
				}

			}

			returnStatus = "success";

		} catch (IOException e) {
			Log(Level.SEVERE, e);

		} finally {

			try {
				if (document != null) {
					document.close();
				}
			} catch (Exception e) {

				Log(Level.SEVERE, e);
			}

			try {
				if (bos != null) {

					bos.close();
				}
			} catch (IOException e) {
				Log(Level.SEVERE, e);
			}

		}

		return returnStatus;
	}

	private synchronized static void createDirectoryStructure(String ftpserver, String[] folders) {
		JakartaFTPWrapper ftp = null;
		try {
			ftp = new JakartaFTPWrapper();

			String ftpUserName = PropertiesHelper.getFTPConnectionUsername();
			String ftpUserPassword = PropertiesHelper.getFTPConnectionPassword();

			System.out.println("Connecting to " + ftpserver + "ftpUserName :" + ftpUserName + " ftpUserPassword :"
					+ ftpUserPassword);
			if (ftp.connectAndLogin(ftpserver, ftpUserName, ftpUserPassword)) {
				System.out.println("Connected to " + ftpserver);
				ftp.setPassiveMode(true);
				ftp.changeWorkingDirectory("ftpserver");
				System.out.println("Present Working Directory :" + ftp.pwd());
				for (int i = 4; i < folders.length; i++) {
					ftp.mkd(folders[i]);
					ftp.changeWorkingDirectory(folders[i]);
				}
			} else {
				System.out.println("Unable to connect to" + ftpserver);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (ftp != null) {
					ftp.logout();
					ftp.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static ByteArrayOutputStream mergeMyFilesFTP(List<InputStream> filesTobeMerges,
			ByteArrayOutputStream byteArrayOutputStream) {

		try {

			// Watermark watermark=new Watermark(img, 25, 725);
			int pageOffset = 0;
			ArrayList masterBookMarkList = new ArrayList();

			int fileIndex = 0;
			Document document = null;
			PdfCopy writer = null;
			PdfReader reader = null;

			for (fileIndex = 0; fileIndex < filesTobeMerges.size(); fileIndex++) {

				reader = new PdfReader(filesTobeMerges.get(fileIndex), "Administrator".getBytes()); // filesToBeMerged[fileIndex]

				reader.consolidateNamedDestinations();

				int totalPages = reader.getNumberOfPages();

				// System.out.println("Checking for bookmarks...");
				List bookmarks = SimpleBookmark.getBookmark(reader);
				if (bookmarks != null) {
					if (pageOffset != 0)
						SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset, null);
					masterBookMarkList.addAll(bookmarks);
					// System.out.println("Bookmarks found and storing...");
				} else {
					// System.out.println("No bookmarks in this file...");
				}
				pageOffset += totalPages;

				if (fileIndex == 0) {

					document = new Document(reader.getPageSizeWithRotation(1));

					// System.out.println("Creating an empty PDF...");
					writer = new PdfCopy(document, byteArrayOutputStream);

					// writer.add(watermark);
					document.open();
					// document.add(img);
					// document.add(watermark);
				}

				// System.out.println("Merging File: ");
				PdfImportedPage page;
				for (int currentPage = 1; currentPage <= totalPages; currentPage++) {
					page = writer.getImportedPage(reader, currentPage);
					writer.addPage(page);
				}

				// System.out.println("Checking for Acroforms");
				PRAcroForm form = reader.getAcroForm();
				if (form != null) {
					writer.addDocument(reader);
					// System.out.println("Acroforms found and copied");
				} else {
					// System.out.println("Acroforms not found for this file");
				}
			}

			if (!masterBookMarkList.isEmpty()) {
				writer.setOutlines(masterBookMarkList);
				// System.out.println("All bookmarks combined and added");

			} else {
				// System.out.println("No bookmarks to add in the new file");

			}

			if (document != null)
				document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream;
	}

	public static byte[] getReport(String filename) throws IOException {
		byte[] byteArray = null;

		byteArray = MongoXmlHandler.getInstance().latestFetchFileByte(filename);

		return byteArray;
	}

	// check pdf save -- convert string to bytearray thn to pdf
	public static void byteArrayToFile(String name) {
		try {

			byte[] bytearray = org.apache.commons.codec.binary.Base64.decodeBase64(name);
			OutputStream out = new FileOutputStream(PropertiesHelper.getFTPSaveTempPDFFilePath());
			out.write(bytearray);
			out.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void byteArrayToFileXML(String name) {
		try {

			byte[] bytearray = org.apache.commons.codec.binary.Base64.decodeBase64(name);
			OutputStream out = new FileOutputStream(PropertiesHelper.getFTPSaveTempXMLFilePath());
			out.write(bytearray);
			out.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void insertoldreports(String dno, String reporturl, String reportchnageflag, String crno) {
		Connection conn = null;
		try {

			PGDataHelper.getInstance();
			conn = PGDataHelper.getConnection();

			if (conn == null) {
				PGDataHelper.getInstance();
				conn = PGDataHelper.createPostgresConnection();
			}

			PreparedStatement pstmt13 = conn.prepareStatement(QueryConfig.Q_hivt_ADDENDUM_HST_TABLE);

			pstmt13.setString(1, dno);
			pstmt13.setString(2, reporturl);
			pstmt13.setString(3, reportchnageflag);
			pstmt13.setString(4, crno);
			pstmt13.executeUpdate();
		} catch (Exception e) {
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
