package dutyroster.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RosterShiftMstFB extends ActionForm
{
	private String hmode;
	private String chk;
	private String isActive;
	private String hospitalcode;
	private String serialNo;
	
	private String rosterTypeId;
	private String shiftId[];
	private String selectedShiftId[];
	private String fetchedList;
	private String availableList;
	private String rosterTypeName;
	private String rosterType;
	
	
		
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		//this.selectedShiftId=null;
		//this.shiftId=null;
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getHospitalcode() {
		return hospitalcode;
	}

	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
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

	public String[] getShiftId() {
		return shiftId;
	}

	public void setShiftId(String[] shiftId) {
		this.shiftId = shiftId;
	}

	public String[] getSelectedShiftId() {
		return selectedShiftId;
	}

	public void setSelectedShiftId(String [] selectedShiftId) {
		this.selectedShiftId = selectedShiftId;
	}

	public String getFetchedList() {
		return fetchedList;
	}

	public void setFetchedList(String fetchedList) {
		this.fetchedList = fetchedList;
	}

	public String getAvailableList() {
		return availableList;
	}

	public void setAvailableList(String availableList) {
		this.availableList = availableList;
	}

	public String getRosterTypeName() {
		return rosterTypeName;
	}

	public void setRosterTypeName(String rosterTypeName) {
		this.rosterTypeName = rosterTypeName;
	}

	public String getRosterType() {
		return rosterType;
	}

	public void setRosterType(String rosterType) {
		this.rosterType = rosterType;
	}


	
}
