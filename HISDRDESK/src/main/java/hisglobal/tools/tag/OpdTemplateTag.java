package hisglobal.tools.tag;

/**
 * @author  CDAC
 */

import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HisFileControlUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.OptionTag;
import org.htmlparser.tags.SelectTag;
import org.htmlparser.tags.TextareaTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.NodeVisitor;

import opd.OpdConfig;
import opd.master.controller.fb.TemplateParameterMasterFB;
import opd.master.controller.util.TemplateUtilityUTIL;

public class OpdTemplateTag  extends TagSupport 
{
	private TemplateParameterMasterFB templateBean;
	
	public int doStartTag() throws JspException 
	{
		try 
		{
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			HttpSession session=WebUTIL.getSession(request);
			
			this.templateBean =(TemplateParameterMasterFB)this.getBean();
			HisFileControlUtil hisFile =new HisFileControlUtil();
			hisFile.setWindowsFilePath(Config.GENERIC_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_PATH_WINDOWS);
			hisFile.setLinuxFilePath(Config.GENERIC_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_PATH_LINUX);
			hisFile.setFileName(Config.OPD_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_NAME+this.templateBean.getTemplateId()+".html");
			if( hisFile.readFile() )
				fillParameterValuesIntoFile(hisFile);
			else
			{
				TemplateUtilityUTIL.getTemplateParametersData(this.templateBean, request);
				this.templateBean.generateTemplate();
				hisFile.setFileContent(this.templateBean.getHtmlTemplate().getBytes());
			}
			JspWriter out = this.pageContext.getOut(); 
			out.println(hisFile.getFileContent());
			
			Map mp;
			if(session.getAttribute(OpdConfig.OPD_DESK_TEMPLATES_ACTIVE_HTML_DATA_MAP)==null)
				mp=new HashMap();
			else
				mp=(Map)session.getAttribute(OpdConfig.OPD_DESK_TEMPLATES_ACTIVE_HTML_DATA_MAP);
				
			List lst=new ArrayList();
			lst.add(hisFile.getFileContent());
			mp.put(this.templateBean.getTemplateId(),hisFile.getFileContent());
			WebUTIL.setAttributeInSession(request,OpdConfig.OPD_DESK_TEMPLATES_ACTIVE_HTML_DATA_MAP,mp);
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}
		return TagSupport.EVAL_BODY_INCLUDE;
	}
	private void fillParameterValuesIntoFile(HisFileControlUtil hisFile)
	{
		if(this.templateBean.getActualParameterValues()!=null)
		{
			try
			{
				Map map=this.templateBean.getActualParameterValues();
				
				Parser parser = new Parser(hisFile.getFileContentInString());
				NodeList list = parser.parse (null);
				
				class ParamterNodeVisitor extends NodeVisitor
				{
					List paraTagList; 
					public ParamterNodeVisitor() {	this.paraTagList= new ArrayList();	}
				    public void visitTag (Tag tag)
				    {
				    	if(tag.getAttribute("name")!=null && tag.getAttribute("name").startsWith("PARAMETER"))
				    	{
				    		this.paraTagList.add(tag);
				            System.out.println ("\n" + tag.getTagName () + tag.getAttribute("name"));
				    	}
				    }
				    public List getParaTagList() {	return paraTagList;	}
					public void visitStringNode (Text string){}
				}
				
				ParamterNodeVisitor myVisitor=new ParamterNodeVisitor();
				list.visitAllNodesWith(myVisitor);
				List allParamTags = myVisitor.getParaTagList();
				for(int i=0;i<allParamTags.size();i++)
				{
					Tag tag =(Tag)allParamTags.get(i);
					String name=tag.getAttribute("name");
					name=name.split("@")[1];
					String id= name.split("&")[0];
					String value=(String)map.get(id);
					if(value != null)setValueToTag(tag,value);
				}
				hisFile.setFileContent(list.toHtml().getBytes());
			}
			catch (Exception dex)
			{
				dex.printStackTrace();
			}
		}
	}
	private void setValueToTag(Tag tag,String value)
	{
		if(tag instanceof InputTag)
		{
			if(tag.getAttribute("type").equals("text"))
				tag.setAttribute("value",value);
			else if (tag.getAttribute("type").equals("radio"))
			{				
				if(tag.getAttribute("value").equals(value))
					tag.setAttribute("checked","true");
				else
					tag.removeAttribute("checked");
			}
			else if (tag.getAttribute("type").equals("checkbox"))
			{
				if(tag.getAttribute("value").equals(value))
					tag.setAttribute("checked","true");
				else
					tag.removeAttribute("checked");
			}
		}
		else if(tag instanceof TextareaTag)
		{
			NodeList nl = new NodeList();
			nl.add(new TextNode(value));			
			tag.setChildren(nl);		
		}
		else if(tag instanceof SelectTag)
		{
			OptionTag[] opts=((SelectTag)tag).getOptionTags();
			for(int o=0;o<opts.length;o++)
				if(opts[o].getAttribute("value").equals(value))
				{
					opts[o].setAttribute("selected","true");
					break;
				}
		}
	}
	String name;	
	Object bean;
	
	public Object getBean() 
	{
		return bean;
	}
	
	public void setBean() throws JspException
	{
		this.bean = TagUtil.getAttribute(this.pageContext, this.getName());
		if(this.bean == null)
			throw new RuntimeException("No bean with name: "+this.getName());
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name)  throws JspException
	{
		this.name = name;
		this.setBean();
	}
}
