package hisglobal.tools.tag;

import java.io.IOException;
import hisglobal.vo.ResultEntryVO;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.w3c.dom.Document;

public class ResultEntryTemplateTag extends BodyTagSupport
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
		 else if((pageContext.findAttribute(this.getName()).getClass()).getName().equals("org.w3c.dom.Document"))
		 {
			 documentObject=(Document)pageContext.findAttribute(this.getName());
			 //this.transformDocument(out,documentObject,);
		 }
		 else
		 {
			 System.out.println("beanName ="+this.getName());
			 System.out.println("class "+pageContext.findAttribute(this.getName()).getClass());
			 ResultEntryVO beanObj=(ResultEntryVO)pageContext.findAttribute(this.getName());
			 
			 if(beanObj!=null)
			 {
				 if(beanObj.getTestDocument()!=null)
				 {
					 documentObject=beanObj.getTestDocument();
					 System.out.println("bean Object "+beanObj.getTestDocument().getClass());
					 this.transformDocument(out,documentObject,beanObj);
				 }
			 }
		 }
		  return EVAL_BODY_BUFFERED ;
		}

	private void transformDocument(JspWriter out, Document testDocument,ResultEntryVO resultEntryVO) {
		try {
			out.print(resultEntryVO.getTemplateDocumentString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
