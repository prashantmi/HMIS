package hisglobal.tools.tag;

import hisglobal.utility.Entry;
import hisglobal.vo.Test;
import hisglobal.vo.TemplateRepresentationClass;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class MandatoryTemplate extends BodyTagSupport
	{
		private String name;
		private String associatedDivId;
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
				 if((pageContext.findAttribute(this.getName()).getClass()).getName().equals("hisglobal.vo.SampleBasedTest")||(pageContext.findAttribute(this.getName()).getClass()).getName().equals("hisglobal.vo.SlideBasedTest")||(pageContext.findAttribute(this.getName()).getClass()).getName().equals("hisglobal.vo.PatientBasedTest"))
				 {
					 testObject=(hisglobal.vo.Test)pageContext.findAttribute(this.getName());
					 if(testObject.getIsMandatoryAssociated().equals("1"))
						 		this.transformDocument(out, testObject);
				 }
				 else
				 {
					 System.out.println("tag typeCast Error");
				 }
			 }
			 
			  return EVAL_BODY_BUFFERED ;
			}

		private void transformDocument(JspWriter out, hisglobal.vo.Test testObj) {
			try {
				out.print(this.getMandatoryTemplateHtmlString(testObj));
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

	private  String getMandatoryTemplateHtmlString(Test testObj) {
			//value is the mandatory Name and name is the mandatory code
			int rowNo=0;
			String htmlString="";
			for(TemplateRepresentationClass templateRepresentationClass:testObj.getMandatoryInfoDtl())
			{
				if(rowNo==0||rowNo==templateRepresentationClass.getRowNo())
				{
					if(rowNo==0)
						htmlString+="<tr>";
					else
						htmlString+="</tr><tr>";
				}
				
				if(templateRepresentationClass.getHtmlObjectType().equals("textBox"))
					htmlString+="<td class='tdfont' width='50%'><div align='right'><b>"+templateRepresentationClass.getValue()+"</b></div></td><td class='tdfonthead' width='50%'><div align='left'><input type='' name='mandatoryInfo' value='"+((templateRepresentationClass.getHtmlObjectValue()==null)?"":templateRepresentationClass.getHtmlObjectValue())+"' title='"+this.associatedDivId+"'></div></td>";
				else
					{
					htmlString+="<td class='tdfont' width='50%'><div align='right'><b>"+templateRepresentationClass.getValue()+"</b></div></td><td class='tdfonthead' width='50%'><div align='left'><select name='mandatoryInfo' title='"+this.associatedDivId+"'><option value='-1' selected>select</option>";
					for(Entry entryObj:templateRepresentationClass.getOptions())
					{
						if(entryObj.getValue().equals(templateRepresentationClass.getHtmlObjectValue()))
							htmlString+="<option value='"+entryObj.getValue()+"' selected>"+entryObj.getLabel()+"</option>";
						else
							htmlString+="<option value='"+entryObj.getValue()+"'>"+entryObj.getLabel()+"</option>";
					}
					htmlString+="</select></div></td>";
					}
			}
			htmlString+="</tr>";
			return htmlString;
		}

	public String getAssociatedDivId() {
		return associatedDivId;
	}

	public void setAssociatedDivId(String associatedDivId) {
		this.associatedDivId = associatedDivId;
	}


}
