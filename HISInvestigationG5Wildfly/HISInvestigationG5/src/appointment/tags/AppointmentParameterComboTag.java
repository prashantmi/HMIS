package appointment.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import appointment.transactions.controller.util.AppointmentTagUTIL;

public class AppointmentParameterComboTag extends TagSupport
{
	//MASTER OR TRANSACTION
	String tagView;
	String supportClassName;
	String controllerName;
	String  scriptCallBackFunctionName;
	String tagname="AppointmentParameterComboTag";
	String aptId;
	
	
	
	
	
	public String getAptId() {
		return aptId;
	}

	public void setAptId(String aptId) {
		this.aptId = aptId;
	}

	public String getScriptCallBackFunctionName() {
		return scriptCallBackFunctionName;
	}

	public void setScriptCallBackFunctionName(String scriptCallBackFunctionName) {
		this.scriptCallBackFunctionName = scriptCallBackFunctionName;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getSupportClassName() {
		return supportClassName;
	}

	public void setSupportClassName(String supportClassName) {
		this.supportClassName = supportClassName;
	}

	public String getTagView() {
		return tagView;
	}

	public void setTagView(String tagView) {
		this.tagView = tagView;
	}

	public int doStartTag() throws JspException
	{
		try
		{
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			JspWriter out = this.pageContext.getOut();
			String strReturn=AppointmentTagUTIL.getAppointmentEssentials(request,tagView , supportClassName,controllerName,scriptCallBackFunctionName,tagname,aptId);
			out.println(strReturn);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return TagSupport.EVAL_BODY_INCLUDE;
	}


		

	String name;
	Object bean;

	public Object getBean()
	{
		return bean;
	}

	public void setBean() throws JspException
	{
		
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name) throws JspException
	{
		this.name = name;
		this.setBean();
	}

	

}
