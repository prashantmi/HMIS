package mortuary.masters.controller.hlp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;



public class PreservativeMasterFB extends ActionForm
{



	private String preservativeName;
	private String preservativeId;
	private String hospitalcode;
	private String serialNo;
	private String isActive;
	private String seatId;
	private String entryDate;
	private String chk;	
	private String hmode;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
		{
		this.preservativeId="";
		this.preservativeName="";
		this.hospitalcode = "";
		this.serialNo="";
		this.isActive = "-1";
		this.seatId="";
		this.chk="";
		}

	
	
	



	public String getPreservativeId() {
		return preservativeId;
	}
	public void setPreservativeId(String preservativeId) {
		this.preservativeId = preservativeId;
	}
	public String getPreservativeName() {
		return preservativeName;
	}
	public void setPreservativeName(String preservativeName) {
		this.preservativeName = preservativeName;
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
