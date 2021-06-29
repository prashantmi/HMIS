package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MandatoryComboMstFB extends ActionForm
{


	private String hmode;
	private String chk[];
	private String mandatoryName;
	private String mandatoryCode;
	private String mandatorycomboValues;
	private String mandatorySeq;
	private String mandatoryValue[];
	private String storeValue;
	private String isActive[];
	private String numberOfRow;
	private String selectedChk;
	
	
	public String getMandatorySeq() {
		return mandatorySeq;
	}

	public void setMandatorySeq(String mandatorySeq) {
		this.mandatorySeq = mandatorySeq;
	}



	public String getMandatoryName() {
		return mandatoryName;
	}



	public void setMandatoryName(String mandatoryName) {
		this.mandatoryName = mandatoryName;
	}



	public String getMandatorycomboValues() {
		return mandatorycomboValues;
	}



	public void setMandatorycomboValues(String mandatorycomboValues) {
		this.mandatorycomboValues = mandatorycomboValues;
	}
	private String valid_invalid;




	public String getValid_invalid() {
		return valid_invalid;
	}



	public void setValid_invalid(String valid_invalid) {
		this.valid_invalid = valid_invalid;
	}


	public String getMandatoryCode() {
		return mandatoryCode;
	}



	public void setMandatoryCode(String sampleCode) {
		this.mandatoryCode = sampleCode;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.mandatoryCode="-1";
		this.mandatoryValue=null;
	}


	public String getHmode()
	{
		return hmode;
	}
	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}
	public String[] getChk()
	{
		return chk;
	}
	public void setChk(String[] chk)
	{
		this.chk = chk;
	}


	public String getNumberOfRow() {
		return numberOfRow;
	}



	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}




	public String[] getMandatoryValue() {
		return mandatoryValue;
	}

	public void setMandatoryValue(String[] mandatoryValue) {
		this.mandatoryValue = mandatoryValue;
	}

	public String[] getIsActive() {
		return isActive;
	}

	public void setIsActive(String[] isActive) {
		this.isActive = isActive;
	}

	public String getSelectedChk() {
		return selectedChk;
	}

	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}

	public String getStoreValue() {
		return storeValue;
	}

	public void setStoreValue(String storeValue) {
		this.storeValue = storeValue;
	}

}
