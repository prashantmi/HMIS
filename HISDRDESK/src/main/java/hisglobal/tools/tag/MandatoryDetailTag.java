package hisglobal.tools.tag;

import hisglobal.utility.Entry;
import hisglobal.vo.Test;
import hisglobal.vo.TemplateRepresentationClass;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class MandatoryDetailTag extends BodyTagSupport
{
  private String name;
  public int doStartTag() throws JspTagException
  {
	  JspWriter out=pageContext.getOut();
	  hisglobal.vo.Test testObject=null;
	  System.out.println("class Name  ="+(pageContext.findAttribute(this.getName())));
	  if(pageContext.findAttribute(this.getName())==null)
	  {
		  System.out.println("Could not Find the Bean In Any Scope");
		  
	  }
	  else
	  {
		  if(pageContext.findAttribute(this.getName()).getClass().getName().equals("hisglobal.vo.Test")||pageContext.findAttribute(this.getName()).getClass().getName().equals("hisglobal.vo.SlideBasedTest"))
		  {
			  testObject=(hisglobal.vo.Test)pageContext.findAttribute(this.getName());
			  
			  if(testObject.getMandatoryInfoDtl()!=null && testObject.getMandatoryInfoDtl().size()!=0)
			  {
				  this.transformDocument(out,testObject);
			  } else
				try {
					out.print("<TR><td width='100%'><div align='center'>No Mandatory Found for This Work order</div></td></TR>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	  }
	  
	  /* if(pageContext.findAttribute(this.getName())==null)
	  {
		  System.out.println("Could not Find the Bean In Any Scope");
		  
	  }
	  else
	  {
		  if(pageContext.findAttribute(this.getName()).getClass().getName().equals(""))
		  {
			  testObject=(hisglobal.vo.Test)pageContext.findAttribute(this.getName());
			  if(testObject.getIsMandatoryAssociated().equals("1"))
			  {
				  this.transformDocument(out,testObject);
			  }
			  else
				  System.out.println("tag typeCast Error");
		 }
	  }*/
	  return EVAL_BODY_BUFFERED;
  }
private void transformDocument(JspWriter out, hisglobal.vo.Test testObject) 
{
	try {
		out.print(this.getMandatoryTemplateHtmlString(testObject));
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
}
public int doEndTag() throws JspTagException
{
   	return EVAL_PAGE;
}

private String getMandatoryTemplateHtmlString(hisglobal.vo.Test testObject) {
	
	//value is the mandatory Name and name is the mandatory code
	int rowNo=0;
	String htmlString="";
	for(TemplateRepresentationClass templateRepresentationClass:testObject.getMandatoryInfoDtl())
	{
	    if(rowNo==0||rowNo==templateRepresentationClass.getRowNo())
			{
				if(rowNo==0)
					htmlString+="<tr>";
				else
					htmlString+="</tr><tr>";
			}
		if(testObject.getMandatoryInfoDtl().size()!=0)
			{
				htmlString+="";
				htmlString+="<td class='tdfont' width='50%'><div align='right'><b>"+templateRepresentationClass.getHtmlObjectName()+"</b></div></td>" ;
				htmlString+="<td class='tdfonthead' width='50%'><div align='left'>"+templateRepresentationClass.getHtmlObjectValue()+"</div></td>";
			}
		else
			{
			htmlString+="<td class='tdfont' width='50%'><b>No Mandatory Associated</b></td>";	
			}
		
			htmlString+="</tr>";
	}
	return htmlString;


}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
