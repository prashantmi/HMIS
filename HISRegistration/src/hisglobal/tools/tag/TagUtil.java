package hisglobal.tools.tag;

import hisglobal.presentation.Status;


import java.lang.reflect.Method;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.views.jsp.TagUtils;

public class TagUtil
{
	public static Object invokeGetterFor(Object obj, String strPropertyName)
	{
		try
		{
			Method meth = obj.getClass().getMethod("get" + strPropertyName, new Class[0]);
			return meth.invoke(obj, null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("No Getter for Property: " + strPropertyName);
		}
	}

	public static void invokeSetter(Object obj, String strPropertyName, String arg)
	{
		try
		{

			Method meth = obj.getClass().getMethod(strPropertyName, new Class[]
			{ String.class });
			meth.invoke(obj, new Object[]
			{ arg });
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("No Setter for Property: " + strPropertyName);
		}
	}

	public static boolean isStatusNew(Status _status)
	{
		if (_status == null) return false;
		return _status.contains(Status.NEW);
	}

	public static Object getAttribute(PageContext pageContext, String _name) throws JspException
	{
		
		Object obj= new Object();
		return obj;
	}

	public void getDummy()
	{
		System.out.println("Dummy Method....");
	}
}
