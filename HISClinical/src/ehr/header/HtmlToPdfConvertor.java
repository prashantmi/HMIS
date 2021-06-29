package ehr.header;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.docx4j.org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;



@SuppressWarnings("deprecation")
public class HtmlToPdfConvertor
{
	// *****************************************************************************************
	// Convert to PDF Document Directly
	// *****************************************************************************************
	public static boolean checkForEncode(String string) {
		String pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(string);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}
	public static void convertHtmlToPDFDirect(HttpServletResponse _response,
			String _htmlCode) {

		OutputStream os = null;

		try {

			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			String result = _htmlCode.substring(_htmlCode.indexOf("<body>") + 6,_htmlCode.lastIndexOf("</body>"));
			
			if (checkForEncode(result)) {
				String decoded = new String(DatatypeConverter.parseBase64Binary(result));
				_htmlCode = _htmlCode.replace(result, decoded);
			}
			
			_htmlCode = _htmlCode.replace("&", "and");
			_htmlCode = _htmlCode.replace("DWH_MP", "HIS");
			_htmlCode = _htmlCode.replace("'", "\"");
			 Pattern p = Pattern.compile("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\u10000-\\u10FFF]+"); 
			_htmlCode = p.matcher(_htmlCode).replaceAll("");
			
			Document doc=null;
			try {
				
				  InputStream stream = new ByteArrayInputStream(_htmlCode.getBytes(StandardCharsets.UTF_8));
				  doc = builder.parse(new InputSource(stream));
			} catch (Exception e) {
				 InputStream in = IOUtils.toInputStream(_htmlCode, "UTF-8");
				 doc = builder.parse(new InputSource(in));	
			}	
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);
			renderer.layout();
			os = _response.getOutputStream();
			renderer.createPDF(os);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	public static void convertHtmlToPDFAndSave(String _htmlCode,String fileName,String strFilePath)
	{
		
		 OutputStream os = null;				
		  try 
		  {
			                
			    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			    
			   /* System.out.println("<------2----->");			    
			    _htmlCode = _htmlCode.replaceAll("& lt;", "<").replaceAll("& gt;", ">");
	            _htmlCode = _htmlCode.replaceAll("& #40;", "\\(").replaceAll("& #41;", "\\)");*/
	            
	            Document doc = builder.parse(new StringBufferInputStream(_htmlCode)); 
	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocument(doc, null);
	            renderer.layout();
	            final FileOutputStream fileOutputStream =new FileOutputStream(new File(strFilePath+"/"+fileName+".pdf"));
	            renderer.createPDF(fileOutputStream);
	            fileOutputStream.close();
	            
	        } 
		    catch (Exception ex) 
		    {
	            ex.printStackTrace();
	        }
	        finally 
	        {
				// close the streams using close method
				try 
				{
					if (os != null) 
					{
						os.close();
					}
				}
				catch (Exception ioe) 
				{
					System.out.println("Error while closing stream: " + ioe);
				}

			}
		
		 
	}		
	
}
