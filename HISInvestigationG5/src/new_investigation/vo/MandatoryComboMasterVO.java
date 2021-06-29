package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class MandatoryComboMasterVO extends ValueObject 
{

	private String mandatoryName;
	private String mandatoryCode;
	private String mandatorycomboValues;
	private String mandatorySeq;
	private String mandatoryValue;
	private String isActive;
	private String numberOfRow;
	private String storeValue;



	//	private String isActive;


	public String getMandatorySeq() {
		return mandatorySeq;
	}



	public void setMandatorySeq(String mandatorySeq) {
		this.mandatorySeq = mandatorySeq;
	}



	public String getMandatoryValue() {
		return mandatoryValue;
	}



	public void setMandatoryValue(String mandatoryValue) {
		this.mandatoryValue = mandatoryValue;
	}




	public String getMandatoryName() {
		return mandatoryName;
	}
	public void setMandatoryName(String mandatoryName) {
		this.mandatoryName = mandatoryName;
	}
	public String getMandatoryCode() {
		return mandatoryCode;
	}
	public void setMandatoryCode(String mandatoryCode) {
		this.mandatoryCode = mandatoryCode;
	}
	public String getMandatorycomboValues() {
		return mandatorycomboValues;
	}
	public void setMandatorycomboValues(String mandatorycomboValues) {
		this.mandatorycomboValues = mandatorycomboValues;
	}


	public String getNumberOfRow() {
		return numberOfRow;
	}



	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}



	public String getIsActive() {
		return isActive;
	}



	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}



	public String getStoreValue() {
		return storeValue;
	}



	public void setStoreValue(String storeValue) {
		this.storeValue = storeValue;
	}



}
