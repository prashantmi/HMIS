package hisglobal;

import java.util.ResourceBundle;

import hisglobal.exceptions.HisException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class CrNo implements Tag {

	PageContext pageContext;

	private String name = "strCrNo";
	private String id = "";
	private String value = "";
	private String js = "";
	private String className = "txtCrNoFormat";
	
	private static ResourceBundle hisProp = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties");
	private String maxLen = hisProp.getString("CR_FORMAT");
	
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return EVAL_PAGE;
	}

	public int doStartTag() throws JspException {

	
		JspWriter jw = pageContext.getOut();
		
			
		try {
			jw.write("<input type='text' align='middle' name='");
			jw.write(this.getName());
			jw.write("' ");
			jw.write("id='");
			jw.write(this.getId());
			jw.write("' ");
			jw.write("value='");
			jw.write(this.getValue());
			jw.write("' ");
			jw.write("class='");
			jw.write(this.getClassName());
			jw.write("' ");
			jw.write("maxlength='");
			jw.write(this.getMaxLen());
			jw.write("' ");
			jw.write(this.getJs());
			jw.write(">");
			
		} catch (Exception e) {
			
			new HisException("Global Tag Lib","CrNo.doStartTag()","-->"+e.getMessage());
		}
			
		return EVAL_BODY_INCLUDE;
	}

	public Tag getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	public void release() {
		// TODO Auto-generated method stub

	}

	public void setPageContext(PageContext pageContext) {

		this.pageContext = pageContext;

	}

	public void setParent(Tag tag) {
		// TODO Auto-generated method stub

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJs() {
		return js;
	}

	public void setJs(String js) {
		this.js = js;
	}

	public String getName() {
		return name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMaxLen() {
		return maxLen;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}

}
