package mortuary.masters.controller.hlp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;



public class ChamberRackMasterFB extends ActionForm
{



	private String chamberId;
	private String hospitalcode;
	private String serialNo;
	private String chamberName;
	private String rackNumbers;
	private String[] rackName;
	private String[] rackStatus;
	private String[] rackCapacity;
	private String isActive;
	private String seatId;
	private String chk;
	private String[] controls;
	private String hmode;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
		{
		this.chamberId = "";
		this.hospitalcode = "";
		this.serialNo="";
		this.chamberName="";
		this.rackName=null;
		this.rackStatus=null;	
		this.rackCapacity=null;
		this.rackNumbers="";
		this.isActive = "-1";
		this.seatId="";
		this.chk="";
		this.controls=new String[2];
		}


	public String getChamberId() {
		return chamberId;
	}


	public void setChamberId(String chamberId) {
		this.chamberId = chamberId;
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


	public String getChamberName() {
		return chamberName;
	}


	public void setChamberName(String chamberName) {
		this.chamberName = chamberName;
	}


	public String getRackNumbers() {
		return rackNumbers;
	}


	public void setRackNumbers(String rackNumbers) {
		this.rackNumbers = rackNumbers;
	}


	public String[] getRackName() {
		return rackName;
	}


	public void setRackName(String[] rackName) {
		this.rackName = rackName;
	}


	public String[] getRackStatus() {
		return rackStatus;
	}


	public void setRackStatus(String[] rackStatus) {
		this.rackStatus = rackStatus;
	}


	public String[] getRackCapacity() {
		return rackCapacity;
	}


	public void setRackCapacity(String[] rackCapacity) {
		this.rackCapacity = rackCapacity;
	}
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String getSeatId() {
		return seatId;
	}


	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}


	public String getChk() {
		return chk;
	}


	public void setChk(String chk) {
		this.chk = chk;
	}


	public String[] getControls() {
		return controls;
	}


	public void setControls(String[] controls) {
		this.controls = controls;
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	
	
	









}
