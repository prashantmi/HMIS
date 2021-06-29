package mrd.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EnclosureMasterFB extends ActionForm
{
	private String hmode;
	private String enclosure;
	private String maxPages;
	private String chk[];
	private String enclosureId;
	private String slNo;
	private String isValid;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		this.enclosure="";
		this.maxPages="";
		
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getEnclosure() {
		return enclosure;
	}
	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}
	public String getMaxPages() {
		return maxPages;
	}
	public void setMaxPages(String maxPages) {
		this.maxPages = maxPages;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getEnclosureId() {
		return enclosureId;
	}
	public void setEnclosureId(String enclosureId) {
		this.enclosureId = enclosureId;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	
}
