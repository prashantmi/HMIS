package hisglobal.utility.generictemplate.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.codec.Encoder;
import java.nio.charset.StandardCharsets;
/*import org.apache.commons.codec.binary.Base64;*/

import org.apache.commons.codec.binary.Base64;

import hisglobal.utility.HisHTMLParserUtil;
import hisglobal.utility.generictemplate.controller.fb.PDFPrintingUtilityFB;
import hisglobal.utility.servlets.ServletsUtilityConfig;

import java.io.UnsupportedEncodingException;    

import javax.xml.bind.DatatypeConverter;

public class PDFPrintingUtilityACT extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
	{
		PDFPrintingUtilityFB fb = (PDFPrintingUtilityFB) form;
		HttpSession session = WebUTIL.getSession(request);			
		try
		{
			if(fb.getErrorMode().equals("NONE"))
			{
				String htmlCode = (String)session.getAttribute(ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE);
				if(htmlCode!=null && !htmlCode.equals(""))
				{
					fb.setErrorMode("OK");
					
					// Encrypt to Base64 -----------
  
					//Added by Vasu on 13.July.2018 for Base64 Encoding
				    String encodedString = new String(Base64.encodeBase64(htmlCode.getBytes(StandardCharsets.UTF_8)));
										
					fb.setHtmlCode(encodedString);//HisHTMLParserUtil.freezeHTMLCodeElements(htmlCode));
					
				}
				else
					fb.setErrorMode("TRY"); // now fetch data from opener's id Value 'pdfPrintingHTMLData'
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fb.setErrorMode("ERROR");
			fb.setErrorMessage("Error: No Data Available for Printing");
		}
		if(fb.getErrorMode().equals("OK"))
		{
			String base64Data = fb.getHtmlCode();
			//String decoded = new String(DatatypeConverter.parseBase64Binary(base64Data), StandardCharsets.UTF_8);
			String decoded = new String(Base64.decodeBase64(base64Data.getBytes(StandardCharsets.UTF_8))); //Modified by Vasu on 13.July.2018
			//fb.setHtmlCode(HisHTMLParserUtil.freezeHTMLCodeElements(fb.getHtmlCode()));
			  //fb.setHtmlCode(HisHTMLParserUtil.freezeHTMLCodeElements(decoded));
			fb.setHtmlCode(decoded);
			WebUTIL.setAttributeInSession(request,ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getHtmlCode());
			// Water Marking
			if(fb.getWaterMarkText()!=null && !fb.getWaterMarkText().equals(""))
				WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, fb.getWaterMarkText());
			if(fb.getModePrint().equals("PDF"))	fb.setModePrint("PDFGO");
		}
		return mapping.findForward("HTML");
	}
}
