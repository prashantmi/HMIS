package hisglobal.backutil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.*;

public class PathTag extends SimpleTagSupport {
	private String src ="";
	
	public void doTag() throws JspException,IOException{
		PageContext pc = (PageContext) this.getJspContext();
		HttpServletRequest request = (HttpServletRequest)pc.getRequest();
		
		String strContextPath = request.getContextPath();
		String path = strContextPath+(this.src.startsWith("/")?"":"/")+this.src;
		
		this.getJspContext().getOut().write(path);		
	}
	
	
	
	public void setSrc(String _src)	
	{
		this.src=_src;		
	}	
}
