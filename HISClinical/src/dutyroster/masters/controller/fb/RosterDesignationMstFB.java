package dutyroster.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RosterDesignationMstFB extends ActionForm
{
	private String hmode;
	private String chk;
	private String serialNo;
	
	private String rosterTypeId;
	private String designationId[];
	private String selectedDesignationId[];
	private String fetchedList;
	
	
		
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.rosterTypeId="";
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}


	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getRosterTypeId() {
		return rosterTypeId;
	}

	public void setRosterTypeId(String rosterTypeId) {
		this.rosterTypeId = rosterTypeId;
	}

	
	public String getFetchedList() {
		return fetchedList;
	}

	public void setFetchedList(String fetchedList) {
		this.fetchedList = fetchedList;
	}

	public String[] getDesignationId() {
		return designationId;
	}

	public void setDesignationId(String[] designationId) {
		this.designationId = designationId;
	}

	public String[] getSelectedDesignationId() {
		return selectedDesignationId;
	}

	public void setSelectedDesignationId(String[] selectedDesignationId) {
		this.selectedDesignationId = selectedDesignationId;
	}

}
