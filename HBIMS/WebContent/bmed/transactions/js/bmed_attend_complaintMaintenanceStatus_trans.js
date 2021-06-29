

function tableShow(strTableId, imageElement) {
	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "table";
	var strOnclick = "tableHide('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/HBIMS/hisglobal/images/minus.gif");

}

function tableHide(strTableId, imageElement) {

	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "none";
	var strOnclick = "tableShow('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/HBIMS/hisglobal/images/plus.gif");

}




function hideOrShowGatePass(elementCheckBox) {
	if (elementCheckBox.checked) {
		showSpanOrDiv("getPassLebel");
		showSpanOrDiv("getPassControl");
	} else {
		hideSpanOrDiv("getPassLebel");
		hideSpanOrDiv("getPassControl");
	}
}
function hideSpanOrDiv(strIdValue) {
	var elementDivOrSpan = document.getElementById(strIdValue);
	elementDivOrSpan.style.display = "none";
}
function showSpanOrDiv(strIdValue) {
	var elementDivOrSpan = document.getElementById(strIdValue);
	elementDivOrSpan.style.display = "block";
}

function displayTaskPopup(elementCheckBox) {
	if (elementCheckBox.checked) {
		showTaskDiv(elementCheckBox);
	} else {
		//alert('Do Nothing!');
	}
}

function addSparePartsDetails(targetNode) {
	display_popup_menu(targetNode, 'divSparePartAddRepairOrReplace', '', '');
	moveSparePartAddRepairOrReplaceDisplay();
	// display_popup_menu(targetNode,'sparePartAddRepairOrReplace','','');
}

function moveSparePartAddRepairOrReplaceDisplay() {
	var elementSparePartAddRepairOrReplace = document
			.getElementById("sparePartAddRepairOrReplace");
	var elementDivSparePartAddRepairOrReplace = document
			.getElementById("divSparePartAddRepairOrReplace");

	if (elementSparePartAddRepairOrReplace.innerHTML != "") {
		elementDivSparePartAddRepairOrReplace.innerHTML = elementSparePartAddRepairOrReplace.innerHTML;
		elementSparePartAddRepairOrReplace.innerHTML = "";
	}

}

function changeSparePartAddPopup(elementSparePartStatusCombo) {
	var strSparePartStatusId = elementSparePartStatusCombo.value;

	var divElementSparePartStockDetails = document
			.getElementById("sparePartStockDetails");
	var divAddSparePartDetails = document.getElementById("addSparePartDetails");
	var tableSparePartAddRepairOrReplaceButton = document
			.getElementById("sparePartAddRepairOrReplaceButton");
	var elementSparePartNameDiv = document.getElementById("sparePartNameDiv");
	var elementSparePartIdCombo = document
			.getElementById("strSparePartIdCombo");
	
	clearSparePartRadioButton();

	if (strSparePartStatusId == 0) {
		// Show Nothing
		divElementSparePartStockDetails.style.display = 'none';
		divAddSparePartDetails.style.display = 'none';
		tableSparePartAddRepairOrReplaceButton.style.display = 'none';

	} else if (strSparePartStatusId == 1) {
		// New Spare Part Addition
		divElementSparePartStockDetails.style.display = 'none';
		divAddSparePartDetails.style.display = 'block';
		elementSparePartNameDiv.style.display = 'none';
		elementSparePartIdCombo.style.display = 'block';
		tableSparePartAddRepairOrReplaceButton.style.display = 'table';

	} else if (strSparePartStatusId == 2) {
		// Replace Spare Part
		divElementSparePartStockDetails.style.display = 'block';
		divAddSparePartDetails.style.display = 'none';
		elementSparePartNameDiv.style.display = 'block';
		elementSparePartIdCombo.style.display = 'none';
		tableSparePartAddRepairOrReplaceButton.style.display = 'table';

	} else if (strSparePartStatusId == 3) {
		// Repair Spare Part
		divElementSparePartStockDetails.style.display = 'block';
		divAddSparePartDetails.style.display = 'none';
		tableSparePartAddRepairOrReplaceButton.style.display = 'table';

	}

}
function showAddSparePartDetailsInReplaceMode(strSparePartName) {

	var divAddSparePartDetails = document.getElementById("addSparePartDetails");
	var elementSparePartNameDiv = document.getElementById("sparePartNameDiv");

	var elementSparePartStatusCombo = document
			.getElementById("strSparePartStatusId");
	var strSparePartStatusId = elementSparePartStatusCombo.value;
	elementSparePartNameDiv.innerHTML = strSparePartName;
	if (strSparePartStatusId == 2) {
		divAddSparePartDetails.style.display = 'block';
	} else {
		divAddSparePartDetails.style.display = 'none';
	}

}
function validateSparePartAdd() {
	var elementSparePartStatusCombo = document
			.getElementById("strSparePartStatusId");
	var strSparePartStatusId = elementSparePartStatusCombo.value;

	var hisValidator = new HISValidator("complaintMaintenanceStatusBean");

	if (strSparePartStatusId != 3) {

		if (strSparePartStatusId == 1) {

			hisValidator.addValidation("strSparePartId", "dontselect='0'",
					"Please Select a Spare Part Name.");
		}
		hisValidator.addValidation("strSpareItemSerialNo", "req",
				"Item Serial No. is a mandatory field");
		hisValidator.addValidation("strManufacturerId", "dontselect='0'",
				"Please Select a Manufacture Name.");
		hisValidator.addValidation("strWarrantyFromDateDD", "req",
				"Warranty From Date Day is a mandatory field");
		hisValidator.addValidation("strWarrantyFromDateYYYY", "req",
				"Warranty From Date Year is a mandatory field");
		hisValidator.addValidation("strWarrantyUpto", "req",
				"Warranty Upto is a mandatory field");
		hisValidator.addValidation("strWarrantyUptoUnitId", "dontselect='0'",
				"Please Select a Warranty Upto Unit.");
		hisValidator.addValidation("strSpecification", "req",
				"Specification is a mandatory field");
		hisValidator.addValidation("strSpecification", "maxlen=4000",
				"Specification cannot exceeds 4000 characters.");
		hisValidator.addValidation("strPerformedDateDD", "req",
				"Performed Date Day is a mandatory field");
		hisValidator.addValidation("strPerformedDateYYYY", "req",
				"Performed Date Year is a mandatory field");

	}

	var retVal = hisValidator.validate();

	hisValidator.clearAllValidations();

	if (retVal) {
		
		if (strSparePartStatusId != 3) {
			if (!(checkDate("strWarrantyFromDate") && checkDate("strPerformedDate"))) {
				return false;
			}
		}
		if (strSparePartStatusId != 1) {
			var arrElementSparePartStockDetailsRadio = document
					.getElementsByName("strSparePartStockDetailsRadio");
			if (arrElementSparePartStockDetailsRadio == null) {
				alert("There is no stock to replace or repair!");
				return false;
			} else {
				var i = 0;
				var fChecked = false;
				for (i = 0; i < arrElementSparePartStockDetailsRadio.length; ++i) {
					if (arrElementSparePartStockDetailsRadio[i].checked) {
						fChecked = true;
						break;
					}
				}
				if (!fChecked) {
					alert("Please select an existing stock to replace or repair!");
					return false;
				}
			}

		}
		//alert("Saved!");
		hide_popup_menu('divSparePartAddRepairOrReplace');
	}
	return true;

}
function checkDate(strDateName) {

	var elementDD_Date = document.getElementById(strDateName + "DD");
	var elementMM_Date = document.getElementById(strDateName + "MM");
	var elementYYYY_Date = document.getElementById(strDateName + "YYYY");
	var elementDate = document.getElementById(strDateName);

	var strDD_Date = elementDD_Date.value;
	var strMM_Date = elementMM_Date.value;
	var strYYYY_Date = elementYYYY_Date.value;

	var nDD_Date = parseInt(strDD_Date);
	var nMM_Date = parseInt(strMM_Date);
	var nYYYY_Date = parseInt(strYYYY_Date);

	if (strYYYY_Date < 1000) {
		alert("Year is in YYYY format.");
		elementYYYY_Date.focus();
		return false;
	}

	var validDD = true;
	switch (nMM_Date) {
	case 2:
		if (isleap(nYYYY_Date)) {
			if (nDD_Date > 29) {
				alert("Date should be less than or equal to 29");
				validDD = false;
			}
		} else {
			if (nDD_Date > 28) {
				alert("Date should be less than or equal to 28");
				validDD = false;
			}

		}
		break;
	case 1:
	case 3:
	case 5:
	case 7:
	case 8:
	case 10:
	case 12:
		if (nDD_Date > 31) {
			alert("Date should be less than or equal to 31");
			validDD = false;
		}
		break;
	//case 2:
	case 4:
	case 6:
	case 9:
	case 11:
		if (nDD_Date > 30) {
			alert("Date should be less than or equal to 30");
			validDD = false;
		}
		break;

	}
	if (validDD == false) {
		elementDD_Date.focus();
		return false;
	}
	elementDate.value = getDateString(strDD_Date,strMM_Date,strYYYY_Date);
	return true;
}
function isleap(yr) {

	if ((parseInt(yr) % 4) == 0) {
		if (parseInt(yr) % 100 == 0) {
			if (parseInt(yr) % 400 != 0) {
				// alert("Not Leap");
				return false;
			}
			if (parseInt(yr) % 400 == 0) {
				// alert("Leap");
				return true;
			}
		}
		if (parseInt(yr) % 100 != 0) {
			// alert("Leap");
			return true;
		}
	}
	if ((parseInt(yr) % 4) != 0) {
		// alert("Not Leap");
		return false;
	}
}
function resetSparePartAddPopup() {

	/*
	 * Display Component
	 */
	var divElementSparePartStockDetails = document
			.getElementById("sparePartStockDetails");
	var divAddSparePartDetails = document.getElementById("addSparePartDetails");
	var tableSparePartAddRepairOrReplaceButton = document
			.getElementById("sparePartAddRepairOrReplaceButton");

	/*
	 * Input Component
	 */
	var elementSparePartStatusCombo = document
			.getElementById("strSparePartStatusId");
	var arrElementSparePartStockDetailsRadio = document
			.getElementsByName("strSparePartStockDetailsRadio");
	var elementSparePartCombo = document.getElementById("strSparePartId");
	var elementSpareItemSerialNoText = document
			.getElementById("strSpareItemSerialNo");
	var elementManufacturerCombo = document.getElementById("strSpareManufacturerId");
	var elementManufactureSerialNoText = document
			.getElementById("strSpareManufactureSerialNo");
	var elementWarrantyFromDateDDText = document
			.getElementById("strWarrantyFromDateDD");
	var elementWarrantyFromDateMMCombo = document
			.getElementById("strWarrantyFromDateMM");
	var elementWarrantyFromDateYYYYText = document
			.getElementById("strWarrantyFromDateYYYY");
	var elementWarrantyUptoText = document.getElementById("strWarrantyUpto");
	var elementWarrantyUptoUnitCombo = document
			.getElementById("strWarrantyUptoUnitId");
	var elementSpecificationText = document.getElementById("strSpecification");
	var elementPerformedDateDDText = document
			.getElementById("strPerformedDateDD");
	var elementPerformedDateMMCombo = document
			.getElementById("strPerformedDateMM");
	var elementPerformedDateYYYYText = document
			.getElementById("strPerformedDateYYYY");

	elementSparePartStatusCombo.value = "0";
	/*
	for (i = 0; i < arrElementSparePartStockDetailsRadio.length; ++i) {
		arrElementSparePartStockDetailsRadio[i].checked = false;

	}
	*/
	
	clearSparePartRadioButton();
	
	elementSparePartCombo.value = "0";
	elementSpareItemSerialNoText.value = "";
	elementManufacturerCombo.value = "0";
	elementManufactureSerialNoText.value = "";
	elementWarrantyFromDateDDText.value = "";
	elementWarrantyFromDateMMCombo.value = "01";
	elementWarrantyFromDateYYYYText.value = "";
	elementWarrantyUptoText.value = "";
	elementWarrantyUptoUnitCombo.value = "0";
	elementSpecificationText.value = "";
	elementPerformedDateDDText.value = "";
	elementPerformedDateMMCombo.value = "01";
	elementPerformedDateYYYYText.value = "";

	divElementSparePartStockDetails.style.display = 'none';
	divAddSparePartDetails.style.display = 'none';
	tableSparePartAddRepairOrReplaceButton.style.display = 'none';

}
function cancelSparePartAddPopup() {
	resetSparePartAddPopup();
	hide_popup_menu('divSparePartAddRepairOrReplace');
}

function getDateString(dd, mm, yyyy) {
	var arrMonthNames = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
			"Aug", "Sep", "Oct", "Nov", "Dec");
	return dd+"-"+arrMonthNames[parseInt(mm, 10)-1]+"-"+yyyy;
}


function cancelAttended() {
	document.forms[0].hmode.value = "unspecified";
	document.forms[0].submit();
	
}


function clearAttended() {
	resetSparePartAddPopup();
	document.forms[0].reset();
}

function checkTimeFormat(eleTime) {
	var strTime=eleTime.value;
	
	var arrTime = strTime.split(":");
	

	if (arrTime.length != 2) {
		alert("Please enter the  Time in HH:MM 24hr format only!");
		eleTime.focus();
		return false;
	}

	

	var strTimeHH = arrTime[0];
	var strTimeMM = arrTime[1];

	
	
	var numTimeHH = parseInt(strTimeHH);
	var numTimeMM = parseInt(strTimeMM);

	

	if (isNaN(numTimeHH) || isNaN(numTimeMM)) {
		alert("Please enter the  Time in HH:MM 24hr format only!");
		eleTime.focus();
		return false;
	} else {
		if(numTimeHH>=24) {
			alert("Hour should be less than 24.");
			eleTime.focus();
			return false;
		}
		if(numTimeMM>=60) {
			
			alert("Minute should be less than 60.");
			eleTime.focus();
			return false;
		}
	}

	return true;
	
}


function validateAttendDetailSave(mode) 
{
	var hisValidator = new HISValidator("complaintMaintenanceStatusBean");

	hisValidator.addValidation("strServiceEngineerName", "req",	"Service Engineer Name is a mandatory field.");
	hisValidator.addValidation("strContactNo", "req", "Contact No is a mandatory field.");
	hisValidator.addValidation("strAttendDate", "req", "Attend Date is a mandatory field.");
	hisValidator.addValidation("strAttendTime", "req", "Attend Time is a mandatory field.");
	hisValidator.addValidation("strProblemDescription", "req", "Problem Description is a mandatory field.");
	hisValidator.addValidation("strProblemDescription", "maxlen=500", "Problem Description cannot exceeds 500 characters.");
	
	/*
	if(document.getElementsByName("strItemOrSparePartMovedOut")[0].checked) {
		hisValidator.addValidation("strGatePassNo", "req",
		"Gate Pass No is a mandatory field.");
	}
	*/
	hisValidator.addValidation("strSolutionProvided", "req", "Solution Provided is a mandatory field.");
	hisValidator.addValidation("strSolutionProvided", "maxlen=250", "Solution Provided cannot exceeds 250 characters.");
	

	hisValidator.addValidation("strFromDate", "req", "From Date is a mandatory field.");
	hisValidator.addValidation("strFromTime", "req", "From Time is a mandatory field.");
	
	hisValidator.addValidation("strToDate", "req", "To Date is a mandatory field.");
	hisValidator.addValidation("strToTime", "req", "To Time is a mandatory field.");
	
	hisValidator.addValidation("strFromDate", "dtltet="+document.forms[0].strToDate.value, "From Date should be less than equal To Date.");
	

	hisValidator.addValidation("strRemarks", "req", "Attendent Remarks is a mandatory field.");
	hisValidator.addValidation("strRemarks", "maxlen=100","Remarks cannot exceeds 100 characters.");


	if(document.getElementsByName("strIsCostInvolved")[0].checked==true)
	{
		hisValidator.addValidation("strCost", "req", "Cost is a mandatory field.");
	}

	var retVal = hisValidator.validate();

	hisValidator.clearAllValidations();

	if (retVal) {
		
		if(checkTimeFormat(document.getElementsByName("strAttendTime")[0])==false) {
			return false;
		}
		if(checkTimeFormat(document.getElementsByName("strFromTime")[0])==false) {
			return false;
		}
		if(checkTimeFormat(document.getElementsByName("strToTime")[0])==false) {
			return false;
		}
		var fWorkingConditionChecked=false;
		var i=0;
		var arrRadioWorkingCondition=document.getElementsByName("strWorkingCondition");
		for(i=0;i<arrRadioWorkingCondition.length;++i ) {
			if(arrRadioWorkingCondition[i].checked) {
				fWorkingConditionChecked=true;
				break;
			}
		}
		if(fWorkingConditionChecked==false) {
			alert("Please Select Working Condition.");
			return;
		}
		
		var fWorkStatusChecked=false;
		i=0;
		var arrRadioWorkStatus=document.getElementsByName("strWorkStatus");
		for(i=0;i<arrRadioWorkStatus.length;++i ) {
			if(arrRadioWorkStatus[i].checked) {
				fWorkStatusChecked=true;
				break;
			}
		}
		if(fWorkStatusChecked==false) {
			alert("Please Select Visit.");
			return;
		}
		
		document.forms[0].hmode.value = mode;
		document.forms[0].submit();
	}
}

function showOrHideSparePartReqRow(radioElementWorkStatus) {
	
	if(radioElementWorkStatus.value==1) { //this element is Work Complete
		
		if(radioElementWorkStatus.checked) {
			hideSparePartReqRow();
		} else {
			showSparePartReqRow();
		}
		
	} else if(radioElementWorkStatus.value==2) { //this element is Work In Complete
		if(radioElementWorkStatus.checked) {
			showSparePartReqRow();
			
		} else {
			hideSparePartReqRow();
		}
	}
	
}

function showSparePartReqRow() {
	document.getElementById("sparePartReqRowId").style.display="table-row";
	
}
function hideSparePartReqRow() {
	document.getElementById("sparePartReqRowId").style.display="none";
}
function clearSparePartRadioButton() {
	var arrElementSparePartStockDetailsRadio=document.getElementsByName("strSparePartStockDetailsRadio");
	if(arrElementSparePartStockDetailsRadio!=null) {
		for (i = 0; i < arrElementSparePartStockDetailsRadio.length; ++i) {
			arrElementSparePartStockDetailsRadio[i].checked = false;

		}
	}
	
}



function showTaskDiv(elementCheckBox) {
	moveTaskDisplay();
	display_popup_menu(elementCheckBox.parentNode, 'taskDivMain', '', '');
}

function moveTaskDisplay() {
	var elementTaskDivMain = document.getElementById("taskDivMain");
	var elementTaskDivBottom = document.getElementById("taskDivBottom");

	if (elementTaskDivBottom.innerHTML != "") 
	{
		elementTaskDivMain.innerHTML = elementTaskDivBottom.innerHTML;
		elementTaskDivBottom.innerHTML = "";
	}

}

function isCostInvolved(obj)
{
		if(document.getElementsByName("strIsCostInvolved")[0].checked==true)
		{
			document.getElementById("costInvolvedDivId1").style.display='block';
			document.getElementById("costInvolvedDivId2").style.display='block';
			document.getElementById("costInvolvedDivId3").style.display='block';
			document.getElementById("costInvolvedDivId4").style.display='block';
			document.getElementById("costInvolvedDivId5").style.display='block';
			document.getElementById("costInvolvedDivId6").style.display='block';
		}
		else
		{
			document.getElementById("costInvolvedDivId1").style.display='none';
			document.getElementById("costInvolvedDivId2").style.display='none';
			document.getElementById("costInvolvedDivId3").style.display='none';
			document.getElementById("costInvolvedDivId4").style.display='none';
			document.getElementById("costInvolvedDivId5").style.display='none';
			document.getElementById("costInvolvedDivId6").style.display='none';
		}
}	
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