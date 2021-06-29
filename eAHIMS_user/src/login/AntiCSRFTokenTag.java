package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import login.CSRFTokenUtil;

public class AntiCSRFTokenTag extends BodyTagSupport {

	private static final long serialVersionUID = 6618685514433195079L;

	/** 
	 * Get Current CSRF Token associated session. 
	 * Create one if one doesn't exist
	 * @return CSRF token for this session
	 * @throws JspTagException
	 */
	public String getToken () throws JspTagException {
		try {
			HttpSession session = ((HttpServletRequest)this.pageContext.
				getRequest()).getSession(false);
			//System.out.println("-----Page Context :------"+this.pageContext.getRequest()+"--------");
			//System.out.println("-----Session Attributes :------"+session.getAttributeNames()+"--------");
			String token = CSRFTokenUtil.getToken(session);
			System.out.println("-----Token :------"+token+"--------");
			return token;
		} catch (Exception e) {
			//throw new JspTagException(e);
			throw new JspTagException();
		}
	}
	
	 /**
     * After tag body parsing handler.
     * @return {@link javax.servlet.jsp.tagext.Tag#SKIP_BODY}
     * @throws JspTagException if writing to the bodyContent's
     * enclosing writer throws an IOException or there's an
     * exception generating a session.
     */
    public int doStartTag() throws JspTagException
    {
            JspWriter out;

            out = this.pageContext.getOut();
            try
            {
                    out.print("<input type='hidden' name='" 
                    		+ CSRFTokenUtil.SESSION_ATTR_KEY +
                    		"' value='" + getToken() + "' />");
            }
            catch (Exception e)
            {
                    //throw new JspTagException("Error writing to body's enclosing JspWriter",e);
            	throw new JspTagException();
            }

            return SKIP_BODY;
    }

	
}
