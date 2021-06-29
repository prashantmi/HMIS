/*
 * Add process validation.
 * Requirement: Engineering Item Type & Engineering Item Sub Type must be selected.
 */
function addNonItemMaintenanceMaster(mode) {
	var arrComboElements = document.getElementsByName("combo");

	/*
	 * There are three combo elements. Engineering Item Type, Engineering Item
	 * Sub Type, and Record Status. Record Status will not take part at the time
	 * of add process. Any new entry is by default 'active'.
	 */

	if (arrComboElements[0].value == '0') {
		alert("Please Select Engineering Item Type");
		arrComboElements[0].focus();
		return;
	}

	if (arrComboElements[1].value == '0') {
		alert("Please Select Engineering Item Sub Type");
		arrComboElements[1].focus();
		return;
	}

	document.forms[0].hmode.value = mode;
	document.forms[0].submit();

}

function LeftListTransfer() {
	shiftToRight("strLeftTaskId", "strRightTaskId", 1);
}

//function setBlockOptions() {
//
//	var url;
//	var mode = "getBlockCodeOptions";
//
//	url = "NonItemMaintenanceMstCNT.cnt?hmode=" + mode + "&strBuildingCode="
//			+ document.forms[0].strBuildingCode.value;
//
//	ajaxFunction(url, "1");
//
//}

//function setFloorOptions() {
//
//	var url;
//	var mode = "getFloorIdOptions";
//	url = "NonItemMaintenanceMstCNT.cnt?hmode=" + mode + "&strBuildingCode="
//			+ document.forms[0].strBuildingCode.value + "&strBlockCode="
//			+ document.forms[0].strBlockCode.value;
//	ajaxFunction(url, "2");
//
//}

//function setRoomOptions() {
//
//	var url;
//	var mode = "getRoomIdOptions";
//	url = "NonItemMaintenanceMstCNT.cnt?hmode=" + mode + "&strBuildingCode="
//			+ document.forms[0].strBuildingCode.value + "&strBlockCode="
//			+ document.forms[0].strBlockCode.value + "&strFloorId="
//			+ document.forms[0].strFloorId.value;
//	ajaxFunction(url, "3");
//
//}

function setTaskOptions() {

	var url;
	var mode = "getTaskOptions";
	
	if(document.forms[0].strMaintenanceId.value=="0") {
		var objLeftTaskDiv = document.getElementById("LeftTaskDivId");
		var objRightTaskDiv = document.getElementById("RightTaskDivId");

		objLeftTaskDiv.innerHTML = "<select name='strLeftTaskId' size='8' multiple style='width: 250px'></select>";
		objRightTaskDiv.innerHTML = "<select name='strRightTaskId' size='8' multiple style='width: 250px'></select>";
		
		
		return;
	}

	url = "NonItemMaintenanceMstCNT.cnt?hmode=" + mode
			+ "&strEngineeringItemTypeId="
			+ document.forms[0].strEngineeringItemTypeId.value
			+ "&strEngineeringItemSubTypeId="
			+ document.forms[0].strEngineeringItemSubTypeId.value
			+ "&strMaintenanceId=" + document.forms[0].strMaintenanceId.value
			+ "&strDeptId="
			+ document.forms[0].strDeptId.value
			+ "&strNonItemId="
			+ document.forms[0].strNonItemId.value;

	ajaxFunction(url, "4");

}

function getAjaxResponse(res, mode) {

	var objVal;

	var err = document.getElementById("errMsg");

	/* Response String format: responseStatus####responseValue */
	var arrStrResponse = res.split("####");

	var strResponseStatus = arrStrResponse[0];
	var strResponseValue = arrStrResponse[1];

	if (strResponseStatus == "ERROR") {

		err.innerHTML = strResponseValue;

	} else {

		if (mode == "1") {

			objVal = document.getElementById("BlockDivId");
			objVal.innerHTML = "<select name ='strBlockCode' class='comboNormal' onChange ='setFloorOptions();'>"
					+ strResponseValue + "</select>";
			
			objVal = document.getElementById("FloorDivId");
			objVal.innerHTML = "<select name ='strFloorId' class='comboNormal'><option value='0' selected='selected'>All</option></select>";
			objVal = document.getElementById("RoomDivId");
			objVal.innerHTML = "<select name ='strRoomId' class='comboNormal'><option value='0' selected='selected'>All</option></select>";
			
			setMaintenanceOptions();

		} else if (mode == "2") {

			objVal = document.getElementById("FloorDivId");
			objVal.innerHTML = "<select name ='strFloorId' class='comboNormal' onChange ='setRoomOptions();'>"
					+ strResponseValue + "</select>";
			
			
			objVal = document.getElementById("RoomDivId");
			objVal.innerHTML = "<select name ='strRoomId' class='comboNormal'><option value='0' selected='selected'>All</option></select>";
			
			setMaintenanceOptions();

		} else if (mode == "3") {

			objVal = document.getElementById("RoomDivId");
			objVal.innerHTML = "<select name ='strRoomId' class='comboNormal' onChange ='setMaintenanceOptions();'>"
					+ strResponseValue + "</select>";
			
			setMaintenanceOptions();

		} else if (mode == "4") {

			var arrStrResponseValue = strResponseValue.split("^");

			var strLeftTaskIdOptions = arrStrResponseValue[0];
			var strRightTaskIdOptions = arrStrResponseValue[1];

			var objLeftTaskDiv = document.getElementById("LeftTaskDivId");
			var objRightTaskDiv = document.getElementById("RightTaskDivId");

			objLeftTaskDiv.innerHTML = "<select name='strLeftTaskId' size='8' multiple style='width: 250px'>"
					+ strLeftTaskIdOptions + "</select>";
			objRightTaskDiv.innerHTML = "<select name='strRightTaskId' size='8' multiple style='width: 250px'>"
					+ strRightTaskIdOptions + "</select>";

		}else if (mode == "5") {

			objVal = document.getElementById("MaintenanceDivId");
			objVal.innerHTML = "<select name='strMaintenanceId'	class='comboNormal' onchange='setTaskOptions();'>"+ strResponseValue +"</select>";
			setTaskOptions();
		}

	}
}

function validate(mode) {

	var hisValidator = new HISValidator("nonItemMaintenanceMstFB");

	//hisValidator.addValidation("strNonItemId", "dontselect=0", "Select Non-Item Name.");	
	hisValidator.addValidation("strLandmarkDesc", "req", "Landmark Description is a mandatory field.");
	hisValidator.addValidation("strLandmarkDesc", "maxlen=100",	"Landmark Description cannot exceeds 250 characters.");
	//hisValidator.addValidation("strStoreId", "dontselect='0'",	"Please Select a Department/Store Name.");
	if(mode=="insert") {
		hisValidator.addValidation("strNonItemId", "dontselect=0", "Select Non-Item Name.");
		hisValidator.addValidation("strMaintenanceId", "dontselect='0'",
		"Please Select a Maintenance Name.");
	}
	hisValidator.addValidation("strPreferTimeFrom", "req", "Preferred Time From is a mandatory field.");
	hisValidator.addValidation("strPreferTimeTo", "req", "Preferred Time To is a mandatory field.");
	hisValidator.addValidation("strMaintenancePeriod", "req", "Maintenance Period is a mandatory field.");
	hisValidator.addValidation("strMaintenancePeriodUnitId", "dontselect='0'", "Please Select a Maintenance Period Unit.");
	hisValidator
			.addValidation("strAlertPeriod", "req",
					"Duration of alert before the Scheduled Days is a mandatory field.");

	hisValidator.addValidation("strEffectiveFrom", "date",
			"Effective Date is a mandatory field");
	hisValidator.addValidation("strRemarks", "maxlen=100",
			"Remarks cannot exceeds 100 characters.");
	
	if(mode=="update") {
		//alert('update');
		hisValidator.addValidation("strIsValid", "req",
		"Please Select a Record Status.");
	}

	var retVal = hisValidator.validate();

	hisValidator.clearAllValidations();

	if (retVal) {
		var count = selectListRecords("strRightTaskId");
		if (count == 0) {
			alert("Please populate at least one Task Name from Available Task in Scheduled Task.");
			return false;
		}

		if(!checkTimeFormat()) {
			
			return false;
		}
		
		document.forms[0].hmode.value = mode;
		document.forms[0].submit();
	}
}


function checkTimeFormat() {
	
	var elePreferTimeFrom = document.getElementsByName("strPreferTimeFrom")[0];
	var elePreferTimeTo = document.getElementsByName("strPreferTimeTo")[0];

	var strPreferTimeFrom = elePreferTimeFrom.value;
	var strPreferTimeTo = elePreferTimeTo.value;

	var arrPreferTimeFrom = strPreferTimeFrom.split(":");
	var arrPreferTimeTo = strPreferTimeTo.split(":");

	if (arrPreferTimeFrom.length != 2) {
		alert("Please enter the Preferred Time From in HH:MM format only!");
		elePreferTimeFrom.focus();
		return false;
	}

	if (arrPreferTimeTo.length != 2) {
		alert("Please enter the Preferred Time To in HH:MM format only!");
		elePreferTimeTo.focus();
		return false;
	}

	var strPreferTimeFromHH = arrPreferTimeFrom[0];
	var strPreferTimeFromMM = arrPreferTimeFrom[1];

	var strPreferTimeToHH = arrPreferTimeTo[0];
	var strPreferTimeToMM = arrPreferTimeTo[1];
	
	var numPreferTimeFromHH = parseInt(strPreferTimeFromHH);
	var numPreferTimeFromMM = parseInt(strPreferTimeFromMM);

	var numPreferTimeToHH = parseInt(strPreferTimeToHH);
	var numPreferTimeToMM =parseInt(strPreferTimeToMM);

	if (isNaN(numPreferTimeFromHH) || isNaN(numPreferTimeFromMM)) {
		alert("Please enter the Preferred Time From in HH:MM 24hr format only!");
		elePreferTimeFrom.focus();
		return false;
	} else {
		if(numPreferTimeFromHH>=24) {
			alert("Hour should be less than 24.");
			elePreferTimeFrom.focus();
			return false;
		}
		if(numPreferTimeFromMM>=60) {
			alert(numPreferTimeFromMM);
			alert("Minute should be less than 59.");
			elePreferTimeFrom.focus();
			return false;
		}
	}

	if (isNaN(numPreferTimeToHH) || isNaN(numPreferTimeToMM)) {
		alert("Please enter the Preferred Time To in HH:MM 24hr format only!");
		elePreferTimeTo.focus();
		return false;
	} else {
		if(numPreferTimeToHH>=24) {
			alert("Hour should be less than 24.");
			elePreferTimeTo.focus();
			return false;
		}
		if(numPreferTimeToMM>=60) {
			alert("Minute should be less than 59.");
			elePreferTimeTo.focus();
			return false;
		}
	}

	

	return true;
	
}

function setMaintenanceOptions() {
	
	var url;
	var mode = "getMaintenanceOptions";
	
	if(document.forms[0].strNonItemId.value=='0') {
		alert('Select Non Item Name.');
		document.forms[0].strNonItemId.focus();
		return;
	}
	
	if(document.forms[0].strDeptId.value=='0') {
		alert('Select Department Name.');
		document.forms[0].strDeptId.focus();
		return;
	}
	
	url = "NonItemMaintenanceMstCNT.cnt?hmode=" + mode
			+ "&strDeptId="
			+ document.forms[0].strDeptId.value
			+ "&strNonItemId="
			+ document.forms[0].strNonItemId.value
			+ "&strEngineeringItemTypeId=" + document.forms[0].strEngineeringItemTypeId.value
			+ "&strEngineeringItemSubTypeId=" + document.forms[0].strEngineeringItemSubTypeId.value;

	ajaxFunction(url, "5");
	
}


function viewNonItemMaintenanceMaster(mode){
	

	document.getElementById('message').style.display = 'none';
	
	if (!checkForView()) {
		return;
	}
	
	
	var strPrimaryKeySet	=	null;
	
	var arrEleCheckBoxes	=	document.getElementsByName("chk");
	
	var nNoOfCheckBoxes	=	0;
	
	var strCheckValue 		= 	null;
	
	
	
	
	nNoOfCheckBoxes = arrEleCheckBoxes.length;
	
	
	var nCheckedIndex=-1;
	var nNoOfCheckedValue=0;

	var chk = "";
	
	for ( i = 0; i < nNoOfCheckBoxes; i++) {
		
		if (arrEleCheckBoxes[i].checked == true) {
			nCheckedIndex=i;
			nNoOfCheckedValue++;
		}
				
	}
	
	
	if(nNoOfCheckedValue<=0) {
		alert("Please select a record to view.");
		return;
	} else if(nNoOfCheckedValue>1){
		alert("Please select only one record to view.");
		return;
	}
	
	strPrimaryKeySet = arrEleCheckBoxes[ nCheckedIndex ].value;

	var strPopupDimention = "width=700,height=500,left=200,top=200";
	
	
	var myPopup = window.open(createFHashAjaxQuery("NonItemMaintenanceMstCNT.cnt?hmode=" + mode + "&chk=" + strPrimaryKeySet, 'NonItemMaintenanceMaster'), strPopupDimention);


	
}


function resetForm(mode) {
	
	document.forms[0].hmode.value = mode;
	document.forms[0].submit();
	
}

/**
 * Function for File Up-Loading
 */

function onUploadedFileName(obj,index,fileId)
{
	
		if(fileId!='0')
		{
			document.forms[0].hmode.value="GETUPLOADEDFILE"; 
			document.forms[0].strUploadFileId.value=fileId;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
		}
		else
		{
			alert("No File to Up-Load!!");			
		}
	
}


