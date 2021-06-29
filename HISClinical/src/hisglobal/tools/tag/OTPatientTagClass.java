package hisglobal.tools.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import operationTheatre.vo.OTPatientVO;

public class OTPatientTagClass extends BodyTagSupport
{
	String name;
	String property;
	public OTPatientVO patientVO;
	public int doStartTag( ) throws JspException
	{
		JspWriter out=pageContext.getOut();
		 if(pageContext.findAttribute(this.getName())==null)
		 {
			 System.out.println("Could not Find the Bean In Any Scope");
		 }
		 else if((pageContext.findAttribute(this.getName()).getClass()).getName().equals("operationTheatre.vo.OTPatientVO"))
		 {
			patientVO=(OTPatientVO)pageContext.findAttribute(this.getName());
			try 
			{
				if(patientVO!=null)
				{
				out.write(createPatientDetailHTML(patientVO));
				}
			} catch (IOException e) {
				System.out.println("Exception in writing the tag");
				e.printStackTrace();
			}  
		 }
		 else
		 {
			 System.out.println("can not cast required:OTPatientVO  implied type:"+pageContext.findAttribute(this.getName()).getClass().getName());
		 }
		  return EVAL_BODY_BUFFERED;
	    }

	public int doEndTag( ) throws JspException {
		
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
	
	public String createPatientDetailHTML(OTPatientVO patient)
	{
		String html="";
		html+="<tr><td class='tdfonthead' width='25%'><b>CR No.</b></td><td class='tdfont' width='25%' colspan='1'><font face='Verdana, Arial, Helvetica, sans-serif'>"+((patient.getPatCrNo()==null)?"":patient.getPatCrNo()) +"</font></td><td class='tdfonthead' width='25%'><b>Patient Name</b></td><td class='tdfont' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif'>"+patient.getPatFirstName()+" " +((patient.getPatMiddleName()==null)?"":patient.getPatMiddleName())+" "+((patient.getPatLastName()==null)?"":patient.getPatLastName())+"</font></td></tr>";
		html+="<tr><td class='tdfonthead' width='25%'><b>Age/ Gender</b></td><td class='tdfont' width='25%'> <font face='Verdana, Arial, Helvetica, sans-serif'>"+((patient.getPatAge()==null)?"":patient.getPatAge())+"/ "+((patient.getPatGender()==null)?"":patient.getPatGender())+"</font></td>";
		html+="<td class='tdfonthead' width='25%'><b>Registration Category</b></td><td class='tdfont' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif'>"+((patient.getPatCategory()==null)?"":patient.getPatCategory())+"</font></td></tr>";
		html+="<tr><td class='tdfonthead' width='25%'><b>Father / "+((patient.getPatGenderCode().equals("1"))?"Spouse":"Husband")+" Name</b></td><td class='tdfont' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif'>"+((patient.getPatGuardianName()==null)?"":patient.getPatGuardianName())+""+((patient.getPatHusbandName()==null)?"":" / "+patient.getPatHusbandName())+"</font></td><td class='tdfonthead' width='25%'><b>Patient Status</b></td><td class='tdfont' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif'>"+((patient.getPatStatus()==null)?"":patient.getPatStatus())+"</font></td></tr>";
		
		return html;
	}

}
