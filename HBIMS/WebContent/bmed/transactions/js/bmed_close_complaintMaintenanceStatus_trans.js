
function tableShow(strTableId, imageElement) {
	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "table";
	var strOnclick = "tableHide('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/HBIMS/hisglobal/images/minus.gif");

}
function showDoc(fileId)
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
function tableHide(strTableId, imageElement) {

	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "none";
	var strOnclick = "tableShow('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/HBIMS/hisglobal/images/plus.gif");

}

function validateSave(mode) {
	var hisValidator = new HISValidator("complaintMaintenanceStatusBean");
	var radioArrWorkingCondition = document.getElementsByName("strWorkingCondition");
	var loopCounter=0;
	var checkedFlag=false;
	var checkedValue=null;
	
	
	if(document.getElementsByName("strRepaiedByVendor")[0].checked) {
		hisValidator.addValidation("strVendorId", "dontselect='0'",
			"Please Select a Vendor.");
		
	}
	
	hisValidator.addValidation("strClosingDate", "req",
	"Closing Date is mandatory.");
	hisValidator.addValidation("strClosingTime", "req",
	"Closing Time is mandatory.");


	hisValidator.addValidation("strTotalCostInvolved", "req",
	"Total Cost Involved is mandatory.");
	
	
	hisValidator.addValidation("strReasonForClosing", "req",
	"Reason For Closing is mandatory.");
	hisValidator.addValidation("strReasonForClosing", "maxlen=100",
	"Reason For Closing cannot exceed 100 character.");

	
	
	
		
	hisValidator.addValidation("strVerifiedBy", "dontselect='0'",
	"Please Select a Verified By.");	
	
	
	hisValidator.addValidation("strRemarks", "maxlen=100",
	"Remarks cannot exceed 100 character.");

// hisValidator.addValidation("strCancelTypeId", "dontselect='0'",
// "Please Select a Cancel Type.");
// hisValidator.addValidation("strCancelRemarks", "req",
// "Cancel Remarks is a mandatory field.");
// hisValidator.addValidation("strCancelRemarks", "maxlen=100",
// "Cancel Remarks cannot exceeds 100 characters.");

	var retVal = hisValidator.validate();

	hisValidator.clearAllValidations();

	if (retVal) {
		
		for(loopCounter=0;loopCounter<radioArrWorkingCondition.length;++loopCounter) {
			if(radioArrWorkingCondition[loopCounter].checked) {
				checkedFlag=true;
				checkedValue=radioArrWorkingCondition[loopCounter].value;
				break;
			}
		}
		if(checkedFlag) {
			if(checkedValue=="0") {
				hisValidator.addValidation("strTotalDownTime", "req","Total Down Time is mandatory.");	
			}
		} else {
			alert("Please Select Working Condition");
			hisValidator.clearAllValidations();
			return;
		}
		
		if(!checkTimeFormat(document.getElementsByName("strClosingTime")[0].value)) {
			return;
		}
		if(checkedFlag==true && checkedValue=="0") {
// if(!checkTimeFormatBeyond24Hr(document.getElementsByName("strTotalDownTime")[0].value))
// {
// return;
// }
	//		if(!checkTimeFormat(document.getElementsByName("strTotalDownTime")[0].value)) 
//			{
//				return;
//			}
		}
		
		document.forms[0].hmode.value = mode;
		document.forms[0].submit();
	}
}

function cancelClose() {

	document.forms[0].hmode.value = "unspecified";
	document.forms[0].submit();

}

function showOrHideVendorDetails(checkBoxElement) {
	var elementVendorDetailTable = document
			.getElementById("repaiedByVendorDetailTable");
	if (checkBoxElement.checked) {
		elementVendorDetailTable.style.display = "table";
	} else {
		elementVendorDetailTable.style.display = "none";
	}

}
function showOrHideTotalDownTime(radioElement) {

/*	
	var elementLebel = document.getElementById("strTotalDownTimeLebel");
	var elementControl = document.getElementById("strTotalDownTimeControl");
	if (radioElement.value == 1) {
		// Item Is Working
		if (radioElement.checked) {
			elementLebel.style.display = "none";
			elementControl.style.display = "none";
		} else {
			elementLebel.style.display = "block";
			elementControl.style.display = "block";
		}

	} else if (radioElement.value == 0) {
		// Item Not Working
		if (radioElement.checked) {
			elementLebel.style.display = "block";
			elementControl.style.display = "block";
		} else {

			elementLebel.style.display = "none";
			elementControl.style.display = "none";
		}

	}

*/
 
}

function checkTimeFormat(strTime) {
	
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

function checkTimeFormatBeyond24Hr(strTime) {
	
	var arrTime = strTime.split(":");
	

	if (arrTime.length != 2) {
		alert("Please enter the  Time in HH:MM format only!");
		eleTime.focus();
		return false;
	}

	

	var strTimeHH = arrTime[0];
	var strTimeMM = arrTime[1];

	
	
	var numTimeHH = parseInt(strTimeHH);
	var numTimeMM = parseInt(strTimeMM);

	

	if (isNaN(numTimeHH) || isNaN(numTimeMM)) {
		alert("Please enter the  Time in HH:MM format only!");
		eleTime.focus();
		return false;
	} else {
		if(numTimeMM>=60) {
			
			alert("Minute should be less than 60.");
			eleTime.focus();
			return false;
		}
	}

	return true;
	
}


function clearClose()
{
	document.forms[0].reset();
	var strComplaintId = document.forms[0].strComplaintId.value;
	document.forms[0].hmode.value="initializeClose";
	document.forms[0].strComplaintId.value=strComplaintId;
	document.forms[0].strHiddenComplaintId.value=strComplaintId;
	document.forms[0].submit();	
}
	
	
function setInitValues()
{
	if(document.forms[0].strTempCost)
	{
		document.forms[0].strTotalCostInvolved.value = document.forms[0].strTempCost.value;	
	}
	
	if(document.forms[0].strTempTotalDownTime)
	{
		document.forms[0].strTotalDownTime.value = document.forms[0].strTempTotalDownTime.value;	
	}
	
	
}
	
function onUploadedFileName(obj,index,fileId)
{
	//alert("fileId="+fileId);
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