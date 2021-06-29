/**********************************************************
 Project:	   DWH_CMSS	
 File:         RequestWrapper.java
 Created:      Jul 3, 2014
 Last Changed: Jul 3, 2014
 Author:       cdac

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package application.filters;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.StrutsRequestWrapper;

/**
 * The Class RequestWrapper.
 */
public final class RequestWrapper extends StrutsRequestWrapper {

	/**
	 * Instantiates a new request wrapper.
	 * 
	 * @param servletRequest
	 *            the servlet request
	 */
	public RequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletRequestWrapper#getParameterValues(java.lang.String)
	 */
	public String[] getParameterValues(String parameter) {

		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		//System.out.println("Count-->>>" + count);
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			//System.out
					//.println("Parameter--->>>" + parameter + "<<" + values[i]);
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
	 */
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		//System.out.println("Parameter--->>>" + parameter + ">>" + value);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServletRequestWrapper#getHeader(java.lang.String)
	 */
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null)
			return null;
		return cleanXSS(value);

	}

	/**
	 * Clean xss.
	 * 
	 * @param value
	 *            the value
	 * @return the string
	 */
	private String cleanXSS(String value) {
		Pattern scriptPattern = null;
		Matcher m = null;
		//System.out.println("Two -->>" + value);
		// You'll need to remove the spaces from the html entities below
		  //value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		  /*value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		  value = value.replaceAll("'", "");
		  value = value.replaceAll("'", "& #39;");
		  value = value.replaceAll("eval\\((.*)\\)", "");
		  value =
		  value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']","\"\"");
		  value = value.replaceAll("script", "");*/
		value = value.replaceAll("confirm", "").replaceAll("prompt", "")
				.replaceAll("alert", "");

		// Avoid null characters
		value = value.replaceAll("", "");

		// //System.out.println("Value Befor Pattern Match-->>"+value);

		// Avoid anything between script tags
		scriptPattern = Pattern.compile("<script>(.*?)</script>",
				Pattern.CASE_INSENSITIVE);
		m = scriptPattern.matcher(value);
		// value = scriptPattern.matcher(value).replaceAll("");

		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-1->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		// Avoid anything in a src='...' type of expression
		// scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
		// Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		// value = scriptPattern.matcher(value).replaceAll("");

		// scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
		// Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		// value = scriptPattern.matcher(value).replaceAll("");

		// Remove any lonesome </script> tag
		scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
		// value = scriptPattern.matcher(value).replaceAll("");
		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-2->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}
		// Remove any lonesome <script ...> tag
		scriptPattern = Pattern.compile("<script(.*?)>",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		// value = scriptPattern.matcher(value).replaceAll("");
		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-2->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		// Avoid eval(...) expressions
		scriptPattern = Pattern.compile("eval\\((.*?)\\)",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		// value = scriptPattern.matcher(value).replaceAll("");
		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-3->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		// Avoid expression(...) expressions
		scriptPattern = Pattern.compile("expression\\((.*?)\\)",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		// value = scriptPattern.matcher(value).replaceAll("");
		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-4->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		// Avoid javascript:... expressions
		scriptPattern = Pattern
				.compile("javascript:", Pattern.CASE_INSENSITIVE);
		// value = scriptPattern.matcher(value).replaceAll("");
		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper5->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		// Avoid vbscript:... expressions
		scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
		// value = scriptPattern.matcher(value).replaceAll("");
		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-6->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		// Avoid onload= expressions
		scriptPattern = Pattern.compile("onload(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		// value = scriptPattern.matcher(value).replaceAll("");
		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-7->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}
		scriptPattern = Pattern.compile("onblur(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-8->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}
		scriptPattern = Pattern.compile("onClick(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-9->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		scriptPattern = Pattern.compile("onmouseover(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-9->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		scriptPattern = Pattern.compile("onmousedown(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-9->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		scriptPattern = Pattern.compile("onchange(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-9->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		scriptPattern = Pattern.compile("ondblclick(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-9->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		scriptPattern = Pattern.compile("onfocus(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-9->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		scriptPattern = Pattern.compile("onkeydown(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-9->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		scriptPattern = Pattern.compile("onkeyup(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-9->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		scriptPattern = Pattern.compile("onmouseout(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-9->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		scriptPattern = Pattern.compile("onmouseup(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-9->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		scriptPattern = Pattern.compile("onmousemove(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-9->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}

		scriptPattern = Pattern.compile("onselect(.*?)=",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

		m = scriptPattern.matcher(value);
		if (m.find()) {
			// System.out.println("Match Found--In Request Wrapper-9->");
			// value = scriptPattern.matcher(value).replaceAll("");
			throw new IllegalStateException("Illigal Activity Not Allowed !!");
		}
		
		scriptPattern = Pattern.compile("<(.*?)>",Pattern.CASE_INSENSITIVE);
		  m = scriptPattern.matcher(value);
		  if (m.find()) 
			{
				//System.out.println("Match Found--In Request Wrapper-1->");
				//value = scriptPattern.matcher(value).replaceAll("");	
				throw new IllegalStateException("Illigal Activity Not Allowed !!");
			}

		//System.out.println("Three Final Value ASASAS-->>" + value);
		return value;
	}

	public Map<String, String[]> getParameterMap()
	{  
		//System.out.println("In getParameterMap-->>");
		Map<String, String[]> paramMapIn = super.getParameterMap();  
        Map<String, String[]> paramMapOut = new HashMap<String, String[]>();  
        Iterator<String> keys = paramMapIn.keySet().iterator();  
        while (keys.hasNext()) {  
             String key = keys.next();  
             String[] values = paramMapIn.get(key);  
             if (values.length == 1)
             {  
     			//System.out.println("Parameter--->>>"+key+"<<"+values[0]);
    			String value = cleanXSS(values[0]);  
                paramMapOut.put(key, new String[] { value });  
             }
             else
             {  
         		for (int i = 0; i < values.length; i++)
         		{
        			//System.out.println("Parameter--->>>" + key + "<<" + values[i]);
        			values[i] = cleanXSS(values[i]);
        		}
				paramMapOut.put(key, values);  
             }  
        }
        return super.getParameterMap();  
   }
	
	/*
	 * public Map getParameterMap() { System.out.println("In getParameterMap");
	 * Map<String, String[]> originalMap = super.getParameterMap(); Map<String,
	 * String[]> filteredMap = new ConcurrentHashMap<String,
	 * String[]>(originalMap.size()); for (String name : originalMap.keySet()) {
	 * filteredMap.put(name, getParameterValues(name)); } return
	 * Collections.unmodifiableMap(filteredMap); }
	 */

	public Object getAttribute(String s) {
		Object attribute = super.getAttribute(s);
		 //System.out.println("In getAttribute-->>"+s);
		return attribute;
	}

}