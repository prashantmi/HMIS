package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class externalLabMstFB extends ActionForm
{


	private String hmode;
	private String chk[];
	
	private String labName;
	private String labCode;
	private String labShortName;
	private String address1;
	private String address2;
	private String contactPerson;
	private String phone;
	private String fax;
	private String email;
	private String remarks;
	private String cityName;
	private String stateCode;
	private String labType;
	private String selectedChk;

	
	

	public String getSelectedChk() {
		return selectedChk;
	}



	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}



	public String getHmode() {
		return hmode;
	}



	public void setHmode(String hmode) {
		this.hmode = hmode;
	}



	public String[] getChk() {
		return chk;
	}



	public void setChk(String[] chk) {
		this.chk = chk;
	}



	public String getLabName() {
		return labName;
	}



	public void setLabName(String labName) {
		this.labName = labName;
	}



	public String getLabCode() {
		return labCode;
	}



	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}



	public String getLabShortName() {
		return labShortName;
	}



	public void setLabShortName(String labShortName) {
		this.labShortName = labShortName;
	}





	public String getContactPerson() {
		return contactPerson;
	}



	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getFax() {
		return fax;
	}



	public void setFax(String fax) {
		this.fax = fax;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public String getCityName() {
		return cityName;
	}



	public void setCityName(String cityName) {
		this.cityName = cityName;
	}



	public String getStateCode() {
		return stateCode;
	}



	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}



	public String getLabType() {
		return labType;
	}



	public void setLabType(String labType) {
		this.labType = labType;
	}



	public String getAddress1() {
		return address1;
	}



	public void setAddress1(String address1) {
		this.address1 = address1;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}

}
