package hisglobal.tools.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.components.ActionComponent;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.*;
import org.apache.struts2.ServletActionContext;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ognl.OgnlValueStack;
import com.opensymphony.xwork2.util.ValueStack;

public class CheckBoxTag extends TagSupport
{
	private String name = "";
	private String property = "";
	private String value = "";
	private String onClick = "";
	private String readonly = "";
	private String disabled = "";
	private String id = "";

	public String getOnClick()
	{
		return onClick;
	}

	public void setOnClick(String onClick)
	{
		this.onClick = onClick;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getProperty()
	{
		return property;
	}

	public void setProperty(String property)
	{
		this.property = property;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public int doStartTag() throws JspTagException
	{

		try
		{
			JspWriter out = pageContext.getOut();
			String checkedString = "";
			OgnlValueStack valStack = (OgnlValueStack)TagUtils.getStack(pageContext);			
			Object bean=valStack.findValue(getName());
			String myCheck[]=new String[]{};
			//System.out.println("------"+getId()+"----");
			if(getId()!=""&&getId()!=null)
				myCheck= BeanUtils.getArrayProperty(bean, getId().substring(getId().indexOf(".")+1));
			else
				myCheck= BeanUtils.getArrayProperty(bean, getProperty().substring(getProperty().indexOf(".")+1));
				
			myCheck = ((myCheck == null) ? new String[]
			{} : myCheck);
			String value = getValue();
			// System.out.println("myCheck:::"+myCheck.length);
			String strCheck = "<input type='checkbox' name='"+getProperty()+"' id='"+getId()+"' value='"+getValue()+"'  tabindex='1'  onclick='"+getOnClick()+"' ";
			for (int i = 0; myCheck != null && i < myCheck.length; i++)
			{
				if (myCheck[i].equals(value)) 
					strCheck = strCheck + " checked ";
			}
			if (getDisabled().equalsIgnoreCase("true")) strCheck = strCheck + " disabled";

			out.write(strCheck + "/>");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}// end of method

	public String getReadonly()
	{
		return readonly;
	}

	public void setReadonly(String readonly)
	{
		this.readonly = readonly;
	}

	public String getDisabled()
	{
		return disabled;
	}

	public void setDisabled(String disabled)
	{
		this.disabled = disabled;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}//end of class
