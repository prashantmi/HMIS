package hisglobal.tools.tag;

import hisglobal.vo.ResultEntryVO;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.w3c.dom.Document;

public class ResultPrintingTemplateTag extends BodyTagSupport
{
	
	private String name;
	private String property;
	

	public int doStartTag() throws JspTagException
		{
		JspWriter out=pageContext.getOut();
		Document documentObject=null;
		System.out.println("class Name  ="+(pageContext.findAttribute(this.getName())));
		if(pageContext.findAttribute(this.getName())==null)
		 {
			 System.out.println("Could not Find the Bean In Any Scope");
		 }
		 else
		 {
			 System.out.println("beanName ="+this.getName());
			 System.out.println("class "+pageContext.findAttribute(this.getName()).getClass());
			 ResultEntryVO beanObj=(ResultEntryVO)pageContext.findAttribute(this.getName());
			 if(beanObj!=null)
			 {
				 try {
				 if(beanObj.getTemplateDocumentString()!=null)
				 {
					 out.print(beanObj.getTemplateDocumentString());
					 System.out.println(beanObj.getTemplateDocumentString());
				 }
				 else
				 {
					 out.print("<table align='center' width='100%'><tr><td width='100%'><div align='center'>System unable to Find out the Investigation for this test </div></td></tr></table>"); 
				 }
				 } catch (IOException e) {
						e.printStackTrace();
					}
				 
			 }
		 }
		  return EVAL_BODY_BUFFERED ;
		}

	

	public int doEndTag() throws JspTagException
		{
		   	return EVAL_PAGE;
		}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
