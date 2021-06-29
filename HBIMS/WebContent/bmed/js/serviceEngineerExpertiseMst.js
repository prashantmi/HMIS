/*
 * Add process validation.
 * Requirement: Engineering Item Type & Engineering Item Sub Type must be selected.
 */
function addServiceEngineerExpertiseMst(mode) {
	var arrComboElements = document.getElementsByName("combo");

	/*
	 * There are three combo elements. Engineering Item Type, Engineering Item
	 * Sub Type, and Record Status. Record Status will not take part at the time
	 * of add process. Any new entry is by default 'active'.
	 */

	if (arrComboElements[0].value == '0') {
		alert("Please Select Service Engineer Name.");
		arrComboElements[0].focus();
		return;
	}

	var strSelectedServiceEngineerName = arrComboElements[0].options[arrComboElements[0].selectedIndex].text;
	
	var strServiceEngineerNameComboValue = arrComboElements[0].value+"^"+strSelectedServiceEngineerName;
	
	
	/* Changing the value of selected option.*/
	arrComboElements[0].options[arrComboElements[0].selectedIndex].value = strServiceEngineerNameComboValue;
	
	
	document.forms[0].hmode.value = mode;
	document.forms[0].submit();

}

function validate(mode) {

	var hisValidator = new HISValidator("serviceEngineerExpertiseMstFB");

	
	if(mode=="insert") {
		
		hisValidator.addValidation("strEffectiveFrom", "date",
		"Effective Date is a mandatory field");
		
	}
	
	
	hisValidator.addValidation("strRemarks", "maxlen=100",
			"Remarks cannot exceeds 100 characters.");
	
	if(mode=="update") {
		hisValidator.addValidation("strIsValid", "req",
		"Please Select a Record Status.");
	}
	
	

	var retVal = hisValidator.validate();

	hisValidator.clearAllValidations();
	
	

	if (retVal) {
		if(mode=="insert") {
			
			var count = selectListRecords("arrStrSelectedExpertiseId");
			if (count == 0) {
				alert("Please populate at least one Expertise Name\nfrom Available Expertise in Selected Expertise.");
				return false;
			}
			
		}
		
		document.forms[0].hmode.value = mode;
		document.forms[0].submit();
	}
}

function resetForm(mode) {
	
	document.forms[0].hmode.value = mode;
	document.forms[0].submit();
	
}

function cancelMasterPage() {
	/* Resetting Combo value for Service Engineer Name */
	var elementServiceEngineerCombo = document.getElementsByName("combo")[0];
	var strServiceEngineerId=elementServiceEngineerCombo.value.split('^')[0];
	elementServiceEngineerCombo.value=strServiceEngineerId;
	
	/* Calling Global Function*/
	cancel('LIST');
	
}
