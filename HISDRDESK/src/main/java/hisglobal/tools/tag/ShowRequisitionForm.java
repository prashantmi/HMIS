package hisglobal.tools.tag;

import hisglobal.vo.ResultEntryVO;
import investigation.controller.transaction.data.InvRequisitionFormDATA;
import investigation.controller.transaction.util.InvRequisitionFormUTIL;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;



public class ShowRequisitionForm extends BodyTagSupport {
	
	private String requisitionDNo;
	private String mode;
	

	public String getRequisitionDNo() {
		return requisitionDNo;
	}

	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int doStartTag() throws JspTagException
	{
		JspWriter out=pageContext.getOut();
	
	if(requisitionDNo!=null)
	{
		getRequisitionForm( out, requisitionDNo,mode);
	}
	else
	{
		try {
			out.print("<table width='100%'><tr><td width 100%><div align='center'><font color='red'>Requisition Form Not Available</font></div></td></tr></table>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	return EVAL_BODY_BUFFERED ;
	}

	private void getRequisitionForm(JspWriter out,String requisitionDNo,String mode) {
		try {
			String templateString = InvRequisitionFormUTIL.getRequisitionForm(pageContext, requisitionDNo, mode);
			System.out.println(templateString);
			out.print((templateString!=null)?templateString:"<table width='100%'><tr><td width 100%><div align='center'><font color='red'>Requisition Form Not Available</font></div></td></tr></table>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int doEndTag() throws JspTagException
		{
		   	return EVAL_PAGE;
		}

	
}
