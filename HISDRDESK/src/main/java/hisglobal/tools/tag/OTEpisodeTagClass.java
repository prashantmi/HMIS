package hisglobal.tools.tag;
import operationTheatre.vo.OTEpisodeVO;
import operationTheatre.vo.OTPatientVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
public class OTEpisodeTagClass extends BodyTagSupport{
	
	String name;
	String property;
	public OTEpisodeVO patientEpisode=null;
	public int doStartTag( ) throws JspException{
		try
		{
		 if(pageContext.findAttribute(this.getName())==null)
		 {
			 System.out.println("Could not Find the Bean In Any Scope");
		 }
		/* else if((pageContext.findAttribute(this.getName()).getClass()).getName().equals("OTEpisodeVO"))
		 {
			patientEpisode=(OTEpisodeVO)pageContext.findAttribute(this.getName());
			createPatientDetailHTML(patientEpisode);  
		 }*/
		 else
		 {
			 JspWriter out=pageContext.getOut();
			 try
			 {
			 patientEpisode=(OTEpisodeVO)pageContext.findAttribute(this.getName());
			 out.write(createPatientDetailHTML(patientEpisode));
			 }
			 catch(Exception ex)
			 {
				Class cls=pageContext.findAttribute(this.getProperty()).getClass();
				Method[] methods= cls.getMethods();
				try
					{
					Field field=cls.getField(this.getProperty());
					boolean checkproperty=false;
					for(Method methodname:methods)
					{
						if(methodname.getName().equalsIgnoreCase("get"+this.getProperty()))
						{
							checkproperty=true;
							OTEpisodeVO patientEpisode=(OTEpisodeVO)pageContext.findAttribute(this.getName());
						}
						
					}
					if(checkproperty)
						out.write(createPatientDetailHTML(patientEpisode));
			 		 }
						catch(Exception e)
						{
							System.out.println("Field Not Found : "+this.getProperty()+" Or Either gettter not available");
							ex.printStackTrace();
						}
			 }
		 }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
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
	
	public String createPatientDetailHTML(OTEpisodeVO patientEpisode)
	{
		String html="";
		//html+="<tr><td class='tdfonthead' width='25%'><b>Episode Number</b></td><td class='tdfont' width='25%'>"+patientEpisode.getPatEpisodeCode()+"</td><td class='tdfonthead' width='25%'><b>Department / Unit</b></td><td class='tdfont' width='25%'> "+patientEpisode.getPatDeptUnit()+"</td></tr>";
	//	html+="<tr><td class='tdfonthead'><b>Visit Date</b></td><td class='tdfont'>"+patientEpisode.getPatVisitDate()+"</td><td class='tdfonthead'><b>Visit No.</b></td><td class='tdfont'> "+patientEpisode.getPatVisitNo()+"</td></tr>";
		
		html+="<tr><td class='tdfonthead' width='25%'><b>Department / Unit</b></td><td class='tdfont' width='25%'> <font face='Verdana, Arial, Helvetica, sans-serif'>"+patientEpisode.getPatDeptUnit()+"</font></td>";
		html+="<td class='tdfonthead' width='25%'><b>Visit Date</b></td><td class='tdfont' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif'>"+patientEpisode.getPatVisitDate()+"</font></td></tr>" ;
				
		if(patientEpisode.getPatVisitTypeCode()!=null && patientEpisode.getPatVisitTypeCode().equals("0"))
		{
		//html+="<tr><td class='HEADER' colspan='4'>IPD Details</td></tr>";
		html+="<tr><td class='tdfonthead' width='25%'><b>Admission No. </b></td><td class='tdfont' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif'>"+((patientEpisode.getPatAdmissionNo()==null)?"":patientEpisode.getPatAdmissionNo())+"</font></td><td class='tdfonthead' width='25%'><b>Ward</b> </td><td class='tdfont' width='25%'>"+((patientEpisode.getPatWardName()==null)?"":patientEpisode.getPatWardName())+"</td></tr>";
		html+="<tr><td class='tdfonthead' width='25%'><b>Room </b></td><td class='tdfont' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif'>"+((patientEpisode.getPatRoomName()==null)?"":patientEpisode.getPatRoomName())+"</font></td><td class='tdfonthead' width='25%'><b>Bed </b></td><td class='tdfont' width='25%'><font face='Verdana, Arial, Helvetica, sans-serif'> "+((patientEpisode.getBedName()==null)?"":patientEpisode.getBedName())+"</font></td></tr>";
		}
		
		if(patientEpisode.getPatEpisodeTypeCode()!=null && patientEpisode.getPatEpisodeTypeCode().equals("3"))
			html+="<tr><td class='tdfonthead' width='25%'><b>Is MLC</b></td><td class='tdfont'><font face='Verdana, Arial, Helvetica, sans-serif'>"+patientEpisode.getPatMLC()+"</font></td><td class='tdfonthead'><b>MLC No.</b></td><td class='tdfont'><font face='Verdana, Arial, Helvetica, sans-serif'> "+patientEpisode.getPatMLCNo()+"</font></td></tr>";
		
		
		html+="<tr><td class='tdfonthead' width='25%'><b>Diagnosis</b></td><td class='tdfont' width='75%' colspan='3'><font face='Verdana, Arial, Helvetica, sans-serif'><div align='left'> "+((patientEpisode.getDiagnosis()!=null)?(patientEpisode.getDiagnosis()+"<input type='hidden' name='diagnosisFound' value='found'"):(" <font color='red'>Not Available </font>"+"<input type='hidden' name='diagnosisFound' value='notfound'"))+"</div></font></td></tr>";
		
		return html;
	}

}
