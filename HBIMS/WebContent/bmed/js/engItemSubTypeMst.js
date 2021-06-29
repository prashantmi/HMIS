// method for page UPDATE
function validateUpdate() {
	var hisValidator = new HISValidator("engineeringItemSubTypeBean");

	hisValidator.addValidation("strEngItemSubTypeName", "req",
			"Engineering Item Sub Type Name is a Mandatory Field");
	hisValidator
			.addValidation("strEngItemSubTypeName", "maxlen=100",
					"Engineering Item Sub Type Name should not contain more than 100 characters");
	hisValidator.addValidation("strEffectiveFrom", "req",
			"Effective From Date is a Mandatory Field");
	hisValidator.addValidation("strEffectiveFrom", "date", "Should be a Date");
	hisValidator.addValidation("strRemarks", "maxlen=100",
			"Remarks should not contain more than 100 characters");
	var retVal = hisValidator.validate();
	if (retVal) {

		document.forms[0].hmode.value = "UPDATE";

		document.forms[0].submit();

	}

}

// method for page SAVE records
function validate1() {

	var hisValidator = new HISValidator("engineeringItemSubTypeBean");

	hisValidator.addValidation("strEnggItemTypeId", "dontselect='0'",
			"Please Select a Engineering Item Type.");

	hisValidator.addValidation("strEngItemSubTypeName", "req",
			"Engineering Item Sub Type Name is a Mandatory Field");
	hisValidator
			.addValidation("strEngItemSubTypeName", "maxlen=100",
					"Engineering Item Sub Type Name should not contain more than 100 characters");
	hisValidator.addValidation("strEffectiveFrom", "req",
			"Effective From Date is a Mandatory Field");
	hisValidator.addValidation("strEffectiveFrom", "date", "Should be a Date");
	hisValidator.addValidation("strRemarks", "maxlen=100",
			"Remarks should not contain more than 100 characters");

	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	if (retVal) {
		document.forms[0].hmode.value = "SAVE";
		document.forms[0].submit();
	} else {

		return false;
	}
}

// method for page ADD
function callComboAdd(form1, mode) {

	with (form1) {

		add("ADD");

	}

}
// method for page MODIFY

function callComboModify(form1, mode) {

	with (form1) {

		edit("MODIFY");
	}
}

function clearMsg(mode) {

	document.forms[0].hmode.value = mode;
	document.forms[0].submit();

}
