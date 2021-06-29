package mortuary.masters.controller.hlp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;



public class ExternalLabMasterFB extends ActionForm
{



	private String externalLabId;
	private String hospitalcode;
	private String serialNo;
	private String externalLabName;
	private String address;
	private String contactNo;
	private String isActive;
	private String seatId;
	private String entryDate;
	private String chk;	
	private String hmode;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
		{
		this.externalLabId="";
		this.externalLabName="";
		this.hospitalcode = "";
		this.serialNo="";
		this.isActive = "-1";
		this.seatId="";
		this.chk="";
		this.contactNo="";
		this.address="";
		}

	


	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getExternalLabId() {
		return externalLabId;
	}

	public void setExternalLabId(String externalLabId) {
		this.externalLabId = externalLabId;
	}
	public String getExternalLabName() {
		return externalLabName;
	}
	public void setExternalLabName(String externalLabName) {
		this.externalLabName = externalLabName;
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


	public String getEntryDate() {
		return entryDate;
	}


	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}


	public String getChk() {
		return chk;
	}


	public void setChk(String chk) {
		this.chk = chk;
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	
	
	
	









}
