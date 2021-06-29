package inpatient.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HealthWorkerMasterFB extends ActionForm
{
	private String healthWorkerID;
	private String slNo;
	private String hospitalCode;
	private String healthWorker;
	
	private String shortName;
	
	private String description;
	
	private String chk[];
	private String hmode;
	private String isValid;
	private String tempMode;
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setHealthWorker("");
		this.setShortName("");
		this.setDescription("");
	}

	public String getHealthWorkerID() {
		return healthWorkerID;
	}

	public void setHealthWorkerID(String healthWorkerID) {
		this.healthWorkerID = healthWorkerID;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHealthWorker() {
		return healthWorker;
	}

	public void setHealthWorker(String healthWorker) {
		this.healthWorker = healthWorker;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getTempMode() {
		return tempMode;
	}

	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}
	
	
}
