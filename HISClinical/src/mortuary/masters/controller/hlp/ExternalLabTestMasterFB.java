package mortuary.masters.controller.hlp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;



public class ExternalLabTestMasterFB extends ActionForm
{



	private String externalLabTestId;
	private String hospitalcode;
	private String serialNo;
	private String externalLabTestName;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String chk;	
	private String hmode;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
		{
		this.externalLabTestId="";
		this.externalLabTestName="";
		this.hospitalcode = "";
		this.serialNo="";
		this.isValid = "-1";
		this.seatId="";
		this.chk="";
		}

	
	
	

	public String getExternalLabTestId() {
		return externalLabTestId;
	}


	public void setExternalLabTestId(String externalLabTestId) {
		this.externalLabTestId = externalLabTestId;
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


	public String getExternalLabTestName() {
		return externalLabTestName;
	}


	public void setExternalLabTestName(String externalLabTestName) {
		this.externalLabTestName = externalLabTestName;
	}


	public String getIsValid() {
		return isValid;
	}


	public void setIsValid(String isValid) {
		this.isValid = isValid;
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
