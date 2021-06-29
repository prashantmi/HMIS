$("#aadhaarRegisteredFlagId")
.click(
		function() {
			var fromProcess = this.value;
			if(typeof fromProcess=='undefined')
				fromProcess=this.name.toString().split('#')[1];		
			/*if (this.checked == false) {
				opdregistration.setFormFieldsAfterSave();
				$("#hiddenCatOrRegstrdPopupFlagId").val("");
				$("#divNormalMsgId").html("");
				$("#divErrorMsgId").html("");
				$("#divPrintId").html("");
				if (("#isIdRequired").val() == "")
					$("#imgCatCardId").show("blind");
				return;
			}*/
			var patNationalId="";
			if(typeof $('[name="patNationalId"]')[0]!="undefined")
				patNationalId=$('[name="patNationalId"]')[0].value;
			
			var patPrimarCatId = $("#patPrimaryCatCodeId").val();
			var action = "/HISRegistration/registration/transactions/openPatAadhaarPopupAadhaarFeed.action?patPrimarCatId="
					+ patPrimarCatId
					+ "&adhaarRegisteredFlag=1"
					+ "&fromProcess="
					+ fromProcess
					+ "&aadhaarId="	+ patNationalId;
			openURLInPopupWithoutClose(action, '600', '200');
			// openURLInPopupWithCallbackFn(action,'600','200','closeModelFunction');
		});

function populatePatientDtl(patientJSONObject) {
	// alert("inside populatePatientDtl");

	for ( var patientKey in patientJSONObject) {
		if (patientJSONObject.hasOwnProperty(patientKey)) {
			var patientVal = patientJSONObject[patientKey];
			// alert("patientKey :"+patientKey+", patientVal :"+patientVal);
			if (document.getElementsByName(patientKey)
					&& document.getElementsByName(patientKey)[0] != undefined) {
				eval("var " + patientKey + "='" + patientVal + "'");
				document.getElementsByName(patientKey)[0].value = eval(patientKey);
				if (patientKey== "patPrimaryCatCode")
				{
					if(patientVal!=""){
					document.getElementsByName("strEmployeePatCatGroup")[0].value=document.getElementsByName("patPrimaryCatGrp")[0].value;
					opdregistration.onchange_patPrimaryCategory();}
					}
					
				// document.getElementsByName(patientKey)[0].disabled=true;
			}

		}
	}
	// setMandatoryRefdReadOnlyTrueFalse(false);
	//setMandatoryReadOnlyTrueFalse(false);

}