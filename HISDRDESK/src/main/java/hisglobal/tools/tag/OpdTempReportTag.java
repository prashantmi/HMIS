package hisglobal.tools.tag;

/**
 * @author  CDAC
 */


import hisglobal.hisconfig.Config;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.generictemplate.controller.fb.TemplateParameterMasterFB;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.http.HttpServletRequest;


public class OpdTempReportTag  extends TagSupport 
{
	private TemplateParameterMasterFB templateBean;
	
	public int doStartTag() throws JspException 
	{
		try 
		{
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			
			this.templateBean =(TemplateParameterMasterFB)this.getBean();
			HisFileControlUtil hisFile =new HisFileControlUtil();
			hisFile.setWindowsFilePath(Config.GENERIC_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_PATH_WINDOWS);
			hisFile.setLinuxFilePath(Config.GENERIC_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_PATH_LINUX);

			//hisFile.setFilePath(Config.OPD_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_PATH);
			hisFile.setFileName(Config.OPD_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_NAME+this.templateBean.getTemplateId()+".html");
			if( hisFile.readFile() )
				fillParameterValuesIntoFile(hisFile);
			else
			{
				TemplateUtilityUTIL.getTemplateParametersData(this.templateBean, request);
				this.templateBean.generateTemplate();
				hisFile.setFileContent(this.templateBean.getHtmlTemplate().getBytes());
				fillParameterValuesIntoFile(hisFile);
			}
			JspWriter out = this.pageContext.getOut(); 
			out.println(hisFile.getFileContent());
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}
		return TagSupport.EVAL_BODY_INCLUDE;
	}
	private void fillParameterValuesIntoFile(HisFileControlUtil hisFile)
	{
		ConvertHTMLToPDF myParser = new ConvertHTMLToPDF(hisFile.getFileContentInString());
		myParser.convertToReportHtml(this.templateBean.getActualParameterValues());
		hisFile.setFileContent(myParser.getTargetContent().getBytes());
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
