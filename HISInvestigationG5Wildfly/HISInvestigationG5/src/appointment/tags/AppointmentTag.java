package appointment.tags;



import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import appointment.transactions.controller.util.AppointmentTagUTIL;

public class AppointmentTag extends TagSupport 
{
	private String crno;
	private String aptId;
	private String paraId1;
	private String paraId2;
	private String paraId3;
	private String paraId4;
	private String paraId5;
	private String paraId6;
	private String paraId7;
	private String tagId;
	private String cssClassName;
	private String tagView;
	private String aptDate;



	public String getTagId() {
		return tagId;
	}




	public void setTagId(String tagId) {
		this.tagId = tagId;
	}




	public String getCrno() {
		return crno;
	}




	public void setCrno(String crno) {
		this.crno = crno;
	}




	public String getAptId() {
		return aptId;
	}




	public void setAptId(String aptId) {
		this.aptId = aptId;
	}




	public String getParaId1() {
		return paraId1;
	}




	public void setParaId1(String paraId1) {
		this.paraId1 = paraId1;
	}




	public String getParaId2() {
		return paraId2;
	}




	public void setParaId2(String paraId2) {
		this.paraId2 = paraId2;
	}




	public String getParaId3() {
		return paraId3;
	}




	public void setParaId3(String paraId3) {
		this.paraId3 = paraId3;
	}




	public String getParaId4() {
		return paraId4;
	}




	public void setParaId4(String paraId4) {
		this.paraId4 = paraId4;
	}




	public String getParaId5() {
		return paraId5;
	}




	public void setParaId5(String paraId5) {
		this.paraId5 = paraId5;
	}




	public String getParaId6() {
		return paraId6;
	}




	public void setParaId6(String paraId6) {
		this.paraId6 = paraId6;
	}




	public String getParaId7() {
		return paraId7;
	}




	public void setParaId7(String paraId7) {
		this.paraId7 = paraId7;
	}



	
	
	
	public int doStartTag() throws JspException
	{
		
		try
		{
			/*HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			//ServletContext servletContext =StartAppointmentListner.getServletList();
			HttpSession session=request.getSession();			
			String tempCode=null; 
			//tempCode=AppointmentProcessUTIL.MakeAppointmentTag(servletContext,request,crno,aptId,paraId1,paraId2,paraId3,paraId4,paraId5,paraId6,paraId7,tagId,cssClassName);
			//System.out.println("tempCode" + tempCode);
			JspWriter out = this.pageContext.getOut();
			out.println(tempCode);*/
			
			
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

			ServletContext servletContext =StartAppointmentListner.getServletList();
			JspWriter out = this.pageContext.getOut();
			String tempCode=null; 
			tempCode=AppointmentTagUTIL.MakeAppointmentTag(request, response, crno, aptId, paraId1, paraId2, paraId3, paraId4, paraId5, paraId6, paraId7, tagId, cssClassName, tagView,aptDate);

			out.println(tempCode);
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}
		return TagSupport.EVAL_BODY_INCLUDE;
	}




	public String getCssClassName() {
		return cssClassName;
	}




	public void setCssClassName(String cssClassName) {
		this.cssClassName = cssClassName;
	}




	public String getTagView() {
		return tagView;
	}




	public void setTagView(String tagview) {
		this.tagView = tagview;
	}




	public String getAptDate() {
		return aptDate;
	}




	public void setAptDate(String aptDate) {
		this.aptDate = aptDate;
	}

}
