package hisglobal.tools.tag;
import investigation.controller.transaction.util.InvRequisitionFormUTIL;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class SampleRemarksTag extends BodyTagSupport {
	
	private String testCode;
	private String labCode;
	private String sampleCode;
	
	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getLabCode() {
		return labCode;
	}

	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}

	public String getSampleCode() {
		return sampleCode;
	}

	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}

	public int doStartTag() throws JspTagException
	{
		JspWriter out=pageContext.getOut();
	
	if(testCode!=null)
	{
		System.out.println("testCode---->inside sampleRemarkTag is---testCode "+testCode+"  labcode----"+labCode+" samplecode--->"+sampleCode);
		getSampleRemarks( out, testCode,labCode,sampleCode);
	}
	else
	{
		try {
			out.print("<table width='100%'><tr><td width 100%><div align='center'><font color='red'>Sample Remarks not Available</font></div></td></tr></table>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	return EVAL_BODY_BUFFERED ;
	}

	private void getSampleRemarks(JspWriter out,String testCode, String labCode, String sampleCode) {
		try {
			String sampleRemarkString= null; 
			sampleRemarkString=	InvRequisitionFormUTIL.getSampleRemarks(pageContext, testCode,labCode,sampleCode);
			
			String  templateString ="<table width='100%'><tr><td class = 'tdfonthead' width='23%' ><div align='left'><b>Sample Remarks: </b></div></td><td class='tdfont'><div align='left'>";
			templateString+=sampleRemarkString;
			templateString+="</div></td></tr></table>";
			System.out.println(templateString);
			out.print((sampleRemarkString!=null)?templateString:"<table width='100%'><tr><td width='33%' class='tdfonthead'><div align='left'><b>Sample Remarks: </b></div></td><td  class='tdfont'><div align='left'><font color='red'>Sample Remarks Not Available</font></div></td></tr></table>");
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

	public int doEndTag() throws JspTagException
		{
		   	return EVAL_PAGE;
		}

	
}
