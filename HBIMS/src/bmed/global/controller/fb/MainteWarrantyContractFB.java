package bmed.global.controller.fb;

import org.apache.struts.action.ActionForm;

public class MainteWarrantyContractFB extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7693920372804709536L;
	private String strDisplayMode;
	private String strHospitalCode;
	private String strItemId;
	private String strProcessMode;
	private String strRadioInput;
	private String strtMainteContractData;
	private String strWarrantyData;

	/* Methods */
	public String getStrDisplayMode() {
		return strDisplayMode;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public String getStrItemId() {
		return strItemId;
	}

	public String getStrProcessMode() {
		return strProcessMode;
	}

	public String getStrRadioInput() {
		return strRadioInput;
	}

	public String getStrtMainteContractData() {
		return strtMainteContractData;
	}

	public String getStrWarrantyData() {
		return strWarrantyData;
	}

	public void setStrDisplayMode(String strDisplayMode) {
		this.strDisplayMode = strDisplayMode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}

	public void setStrProcessMode(String strProcessMode) {
		this.strProcessMode = strProcessMode;
	}

	public void setStrRadioInput(String strRadioInput) {
		this.strRadioInput = strRadioInput;
	}

	public void setStrtMainteContractData(String strtMainteContractData) {
		this.strtMainteContractData = strtMainteContractData;
	}

	public void setStrWarrantyData(String strWarrantyData) {
		this.strWarrantyData = strWarrantyData;
	}
}
