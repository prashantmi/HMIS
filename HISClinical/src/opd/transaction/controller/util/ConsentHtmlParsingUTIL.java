package opd.transaction.controller.util;

/**
 * @author  CDAC
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.fb.ConsentHtmlParsingFB;

import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.NodeVisitor;

public class ConsentHtmlParsingUTIL extends ControllerUTIL
{
	// Parsing HTML
	public static void parseHtml(ConsentHtmlParsingFB _fb, HttpServletRequest _rq)
	{
		HttpSession session = WebUTIL.getSession(_rq);
		Status objStatus = new Status();
		Map mp;
		try
		{
			if (session.getAttribute(OpdConfig.OPD_DESK_TEMPLATES_ACTIVE_HTML_DATA_MAP) == null)
			{
				objStatus.add(Status.TRANSINPROCESS, "HTML Data Not Found", "");
			}
			else
			{
				mp = (Map) session.getAttribute(OpdConfig.OPD_DESK_TEMPLATES_ACTIVE_HTML_DATA_MAP);
				String str = (String) mp.get(_fb.getTemplateId());
				_fb.setConsentHtmlToPrint(str);

				parseConsent(_fb, _rq);

				WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_TEMPLATE_CONSENT_HTML_DATA, _fb.getConsentHtmlToPrint());
			}
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Parsing Consent
	private static void parseConsent(ConsentHtmlParsingFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		try
		{
			String strHtmData = _fb.getConsentHtmlToPrint();
			strHtmData = "<html><body>" + strHtmData + "</body></html>";
			strHtmData = strHtmData.replace("\n", "");
			strHtmData = strHtmData.replace("\t", "");

			// Parsing Data
			Parser parser = new Parser(strHtmData);
			NodeList list = parser.parse(null);

			// For Searching Print Button Table Row
			// Creating a Visitor
			class PrintTrVisitor extends NodeVisitor
			{
				List paraTagList;

				public PrintTrVisitor()
				{
					this.paraTagList = new ArrayList();
				}

				public void visitTag(Tag tag)
				{
					if (tag.getAttribute("id") != null && tag.getAttribute("id").equals("tdPrintConsent"))
					{
						this.paraTagList.add(tag);
						System.out.println("\n" + tag.getTagName() + " " + tag.getAttribute("id"));
					}
				}

				public List getParaTagList()
				{
					return paraTagList;
				}

				public void visitStringNode(Text string)
				{
				}
			}
			PrintTrVisitor ptv = new PrintTrVisitor();
			list.visitAllNodesWith(ptv);
			List allParamTags = ptv.getParaTagList();
			// Removing Print Button Row
			for (int i = 0; i < allParamTags.size(); i++)
			{
				Tag tag = (Tag) allParamTags.get(i);
				tag.setChildren(new NodeList());
			}

			// Document doc=parseHtmlToDoc(list.toHtml(),_rq);

			// Deleting Width Attribute
			/*
			 * class WidthDeleteVisitor extends NodeVisitor { public void visitTag(Tag tag) { if (tag.getAttribute("width") !=
			 * null) { //tag.removeAttribute("width"); //System.out.println ("\n" + tag.getTagName () + "
			 * "+tag.getAttribute("id")); } }
			 * 
			 * public void visitStringNode(Text string) { } }
			 */
			// WidthDeleteVisitor wdv = new WidthDeleteVisitor();
			// list.visitAllNodesWith(wdv);
			/*
			 * ParamterNodeVisitor myVisitor=new ParamterNodeVisitor(); list.visitAllNodesWith(myVisitor); allParamTags =
			 * myVisitor.getParaTagList(); for(int i=0;i<allParamTags.size();i++) { Tag tag =(Tag)allParamTags.get(i);
			 * String value=new String(); if(tag instanceof InputTag) if(tag.getAttribute("type").equals("text"))
			 * value=tag.getAttribute("value"); else if (tag.getAttribute("type").equals("radio")||
			 * tag.getAttribute("type").equals("checkbox")) if(tag.getAttribute("checked").equals("true"))
			 * value=tag.getAttribute("value"); else if(tag instanceof TextareaTag) value=tag.getAttribute("value"); else
			 * if(tag instanceof SelectTag) { OptionTag[] opts=((SelectTag)tag).getOptionTags(); for(int o=0;o<opts.length;o++)
			 * if(opts[o].getAttribute("selected").equals("true")) { value=opts[o].getAttribute("value"); break; } } tag
			 * =(Tag)tag.getParent(); NodeList nl=new NodeList(new TextNode(value)); tag.setChildren(nl); }
			 */

			strHtmData = list.toHtml();
			// strHtmData=list.toNodeArray()[0].toPlainTextString();

			strHtmData = strHtmData.replace("&", "");
			System.out.println(strHtmData);
			_fb.setConsentHtmlToPrint(strHtmData);
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	/*
	// Parsing HTML to Document
	private static Document parseHtmlToDoc(String str, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		Document doc = new Document();
		try
		{
			doc.addTitle("PRAGYA SHARMA RnD");
			doc.addKeywords("pdf, itext, Java, open source, http");
			doc.setPageSize(PageSize.A4);
			HeaderFooter header = new HeaderFooter(new Phrase("This is a Header"), false);
			HeaderFooter footer = new HeaderFooter(new Phrase("This is a footer."), false);
			doc.setHeader(header);
			doc.setFooter(footer);

			// Parsing Data
			Parser parser = new Parser(str);
			NodeList list = parser.parse(null);

			class HTMLToDocumentVisitor extends NodeVisitor
			{
				Document doc;

				public Document getDoc()
				{
					return this.doc;
				}

				public HTMLToDocumentVisitor(Document d)
				{
					this.doc = d;
				}

				public void visitTag(Tag tag)
				{

				}

				public void visitStringNode(Text string)
				{
				}
			}

		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return doc;

	}*/
}
