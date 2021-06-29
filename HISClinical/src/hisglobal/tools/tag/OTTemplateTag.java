package hisglobal.tools.tag;



import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import operationTheatre.masters.OTTemplate.TemplateDesignerMstUTIL;

public class OTTemplateTag extends TagSupport 
{
	private String templateId;
	private String mode;
	
	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}	
	
	public int doStartTag() throws JspException
	{
		
		try
		{
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			HttpSession session=request.getSession();
			System.out.println("mode------" + mode);
			String tempCode=TemplateDesignerMstUTIL.DesignTemplate(templateId,mode,request);
		
			JspWriter out = this.pageContext.getOut();
			out.println(tempCode);
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}
		return TagSupport.EVAL_BODY_INCLUDE;
	}


}
