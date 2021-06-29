package mrd.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MrdCheckListMasterFB extends ActionForm
{
	private String hmode;
	private String checkList;
	//private String controls[];
	private String chk[];
	private String checkListId;
	private String slNo;
	private String isValid;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		this.checkList="";
		
	}

	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getCheckList() {
		return checkList;
	}
	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}
	
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getCheckListId() {
		return checkListId;
	}
	public void setCheckListId(String checkListId) {
		this.checkListId = checkListId;
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
