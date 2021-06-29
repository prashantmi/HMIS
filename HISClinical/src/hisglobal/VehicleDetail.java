package hisglobal;

import hisglobal.exceptions.HisException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import transport.global.controller.VehicleDetailsHLP;


/**
 * Developer : Anshul Jindal
 * Create Date : 8-Sep-2009
 * Process Name : Vehicle Details TLD
 * Version : 1.0
 */

public class VehicleDetail implements Tag{
	
	PageContext pageContext;
	private String strVehicleNo = "0";
	private boolean withVehicleNo = false;
	private boolean withVehicleCategory = false;
	private boolean withVehicleValue = false;
	
	/**
	 * @return the strVehicleNo
	 */
	public String getStrVehicleNo() {
		return strVehicleNo;
	}
	/**
	 * @param strVehicleNo the strVehicleNo to set
	 */
	public void setStrVehicleNo(String strVehicleNo) {
		this.strVehicleNo = strVehicleNo;
	}
	/**
	 * @return the withVehicleNo
	 */
	public boolean isWithVehicleNo() {
		return withVehicleNo;
	}
	/**
	 * @param withVehicleNo the withVehicleNo to set
	 */
	public void setWithVehicleNo(boolean withVehicleNo) {
		this.withVehicleNo = withVehicleNo;
	}
	/**
	 * @return the withVehicleCategory
	 */
	public boolean isWithVehicleCategory() {
		return withVehicleCategory;
	}
	/**
	 * @param withVehicleCategory the withVehicleCategory to set
	 */
	public void setWithVehicleCategory(boolean withVehicleCategory) {
		this.withVehicleCategory = withVehicleCategory;
	}
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return EVAL_PAGE;
	}
	public int doStartTag() throws JspException {
		String strVehicleDetails = "";
		JspWriter jw = null;
		try {
			
			jw = pageContext.getOut();
			
			strVehicleDetails = VehicleDetailsHLP.getStrVehicleDetails(strVehicleNo, withVehicleNo, withVehicleCategory ,withVehicleValue, pageContext.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			
			jw.print(strVehicleDetails);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//voObj.setStrMsgString("Error while getting vehicle detail");
			new HisException("TRANSPORT","VehicleDetail Tag.doStartTag() -->",e.getMessage());
		}
		finally {
			try {
				//jw.print("<input type='hidden' name='strGlbErrMsgTLD' value='"+strVehicleDetails+"'>");
				
			}catch(Exception e) {}
			
			//voObj = null;
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
	/**
	 * @return the withVehicleValue
	 */
	public boolean isWithVehicleValue() {
		return withVehicleValue;
	}
	/**
	 * @param withVehicleValue the withVehicleValue to set
	 */
	public void setWithVehicleValue(boolean withVehicleValue) {
		this.withVehicleValue = withVehicleValue;
	}

}
