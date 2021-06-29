package mrd.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RackShelfMstFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String controls[]=new String[]{""};
	
	private String rackName;
	private String rackId;
	private String rackShelfId;
	private String serialNo;
	private String shelfLabel;
	private String shelfStatus;
	private String shelfCapacity;
	private String isValid;
	private String mode;
	
	//private String shelfId[];
	//private String selectedShelfId[];
	//private String fetchedList;
	
	public void reset(ActionMapping mapping,HttpServletRequest _request){
		
		//this.rackId="-1";
		///this.fetchedList="";
		this.shelfLabel="";
		this.shelfStatus="";
		this.shelfCapacity="";
		
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getRackId() {
		return rackId;
	}
	public void setRackId(String rackId) {
		this.rackId = rackId;
	}
	
	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getRackShelfId() {
		return rackShelfId;
	}

	public void setRackShelfId(String rackShelfId) {
		this.rackShelfId = rackShelfId;
	}

	public String getShelfLabel() {
		return shelfLabel;
	}

	public void setShelfLabel(String shelfLabel) {
		this.shelfLabel = shelfLabel;
	}

	public String getShelfStatus() {
		return shelfStatus;
	}

	public void setShelfStatus(String shelfStatus) {
		this.shelfStatus = shelfStatus;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getShelfCapacity() {
		return shelfCapacity;
	}

	public void setShelfCapacity(String shelfCapacity) {
		this.shelfCapacity = shelfCapacity;
	}

	public String[] getControls() {
		return controls;
	}

	public void setControls(String[] controls) {
		this.controls = controls;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
		
	

}
