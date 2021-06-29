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

function validateSchedule(mode) {
	var hisValidator = new HISValidator("complaintMaintenanceStatusBean");
	
	/*
	 * Default Value is true.
	 */
	var boolAllocateToExternalServiceProvider=true;
	if(document.getElementsByName("strAllocateToExternalServiceProvider")[0]!=null) {
		boolAllocateToExternalServiceProvider=document.getElementsByName("strAllocateToExternalServiceProvider")[0].checked;
	}
	if(boolAllocateToExternalServiceProvider) {
		hisValidator.addValidation("strIntimationDate", "req",
		"Intimation Date is mandatory.");
		hisValidator.addValidation("strIntimationTime", "req",
		"Intimation Time is mandatory.");
		hisValidator.addValidation("strContactPerson", "req",
		"Contact Person is mandatory.");
		hisValidator.addValidation("strContactNo", "req",
		"Contact No is mandatory.");
		hisValidator.addValidation("strProblemDescription", "req",
		"Problem Description is mandatory.");
		hisValidator.addValidation("strProblemDescription", "maxlen=500",
		"Problem Description cannot exceeds 500 characters.");
		hisValidator.addValidation("strCommunicationId", "req",
		"Communication Id is mandatory.");
		hisValidator.addValidation("strExpectedVisit", "req",
		"Expected Visit is mandatory.");
		hisValidator.addValidation("strExpectedVisitUnitId", "dontselect='0'",
		"Please Select a Expected Visit Unit.");
		hisValidator.addValidation("strSolutionProvided", "req",
		"Solution Provided is mandatory.");
		hisValidator.addValidation("strSolutionProvided", "maxlen=250",
		"Solution Provided cannot exceeds 250 characters.");
		hisValidator.addValidation("strServiceProviderRemarks", "maxlen=250",
		"Service Provider Remarks cannot exceeds 250 characters.");
	} else {
		hisValidator.addValidation("strExpectedDateToAttend", "req",
		"Expected Date To Attend is mandatory.");
		hisValidator.addValidation("strExpectedTimeToAttend", "req",
		"Expected Time To Attend.");
		hisValidator.addValidation("strServiceEngnieerRemarks", "req",
		"Service Engnieer Remarks is mandatory.");
		hisValidator.addValidation("strServiceEngnieerRemarks", "maxlen=250",
		"Service Engnieer Remarks cannot exceeds 250 characters.");
	}

	

	var retVal = hisValidator.validate();

	hisValidator.clearAllValidations();

	if (retVal) {
		
		if(!checkTimeFormat()) {
			
			return false;
		}
		
		if(!boolAllocateToExternalServiceProvider) {
			var arr_strServiceEnggId_radio=document.getElementsByName("strServiceEnggId");
			var i=0;
			var strServiceEnggIdIsChecked=false;
			for(i=0;i<arr_strServiceEnggId_radio.length;++i){
				if(arr_strServiceEnggId_radio[i].checked) {
					strServiceEnggIdIsChecked=true;
					break;
				}
			}
			if(!strServiceEnggIdIsChecked) {
				alert("Select Service Engineer");
				return false;
			}
		}
		
		document.forms[0].hmode.value = mode;
		document.forms[0].submit();
	}
}


function checkTimeFormat() {
	
	var eleIntimationTime = null;
	
	/*
	 * Default Value is true.
	 */
	var boolAllocateToExternalServiceProvider=true;
	if(document.getElementsByName("strAllocateToExternalServiceProvider")[0]!=null) {
		boolAllocateToExternalServiceProvider=document.getElementsByName("strAllocateToExternalServiceProvider")[0].checked;
	}
	
	if(boolAllocateToExternalServiceProvider) {
		eleIntimationTime = document.getElementsByName("strIntimationTime")[0];
	} else {
		eleIntimationTime = document.getElementsByName("strExpectedTimeToAttend")[0];
	}
	

	var strIntimationTime = eleIntimationTime.value;
	

	var arrIntimationTime = strIntimationTime.split(":");
	

	if (arrIntimationTime.length != 2) {
		alert("Please enter the Time in HH:MM format only!");
		eleIntimationTime.focus();
		return false;
	}

	

	var strIntimationTimeHH = arrIntimationTime[0];
	var strIntimationTimeMM = arrIntimationTime[1];

	
	
	var numIntimationTimeHH = parseInt(strIntimationTimeHH);
	var numIntimationTimeMM = parseInt(strIntimationTimeMM);

	

	if (isNaN(numIntimationTimeHH) || isNaN(numIntimationTimeMM)) {
		alert("Please enter the Intimation Time in HH:MM 24hr format only!");
		eleIntimationTime.focus();
		return false;
	} else {
		if(numIntimationTimeHH>=24) {
			alert("Hour should be less than 24.");
			eleIntimationTime.focus();
			return false;
		}
		if(numIntimationTimeMM>=60) {
			alert(numIntimationTimeMM);
			alert("Minute should be less than 60.");
			eleIntimationTime.focus();
			return false;
		}
	}

	return true;
	
}

function cancelProcess()
{
  if(document.forms[0].strIsHemDesk.value=='0')
  {	
	document.forms[0].hmode.value = "unspecified";
	document.forms[0].submit();
  }
  else
  {
  	
  	document.forms[0].hmode.value = "CANCELTODESK";
	document.forms[0].submit();
  	
  }	
}

function activateAllocateToExternalServiceProviderMode(elementCheckBox){
	
	var elementWarrantyAndMaintenanceContractDiv = document.getElementById("warrantyAndMaintenanceContractDiv") ; 
	var elementExternalScheduleDetailsDiv = document.getElementById("externalScheduleDetailsDiv") ; 
	var elementServiceEngineerDataDiv = document.getElementById("serviceEngineerDataDiv") ; 
	
	
	if(elementCheckBox.checked) {
		elementWarrantyAndMaintenanceContractDiv.style.display="block";
		elementExternalScheduleDetailsDiv.style.display="block";
		
		elementServiceEngineerDataDiv.style.display="none";
	} else {
		elementWarrantyAndMaintenanceContractDiv.style.display="none";
		elementExternalScheduleDetailsDiv.style.display="none";
		
		elementServiceEngineerDataDiv.style.display="block";
	}
	
	
}


function populateFilterValue(filterTypeCombo) {
	
	var strfilterType=filterTypeCombo.value;
	var url;
	var mode;
	
	
	if(strfilterType==0) {
		
		var elementFilterValueCombo = document.getElementById("strFilterValue");
		elementFilterValueCombo.innerHTML = "<option value='0'>Select Value</option>";
		
		var strHtml="<tr>";
		strHtml=strHtml+"<td width=\"5%\" class=\"LABEL_TD\" style=\"text-align: center;\">&nbsp;</td>";
		strHtml=strHtml+"<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Service Engineer Name</td>";
		strHtml=strHtml+"<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Skills</td>";
		strHtml=strHtml+"<td width=\"35%\" class=\"LABEL_TD\" style=\"text-align: center;\">Number of Jobs</td>";
		strHtml=strHtml+"</tr>";
		strHtml=strHtml+"<tr>";
		strHtml=strHtml+"<td colspan=\"4\" class=\"CONTROL_TD\" style=\"text-align: center;color:red\">No Data Found!</td>";
		strHtml=strHtml+"</tr>";
		var elementOtherServiceEngineerDetailsTable = document.getElementById("strOtherServiceEngineerDetailsTable");
		elementOtherServiceEngineerDetailsTable.innerHTML = strHtml;
		
	} else {
		
		
		
		var strEngineeringItemTypeId=document.getElementById("strEngineeringItemTypeId").value;

		url = "ComplaintMaintenanceStatusTransACTION.cnt?hmode=getFilterValue&strEngineeringItemTypeId="+strEngineeringItemTypeId+"&strFilterType="+strfilterType;

		
		ajaxFunction(url, "1");
	}
	
}

function populateOtherServiceEngineer() {
	
	var strFilterBy=document.getElementById("strFilterBy").value;
	var strFilterValue=document.getElementById("strFilterValue").value;
	var strEngineeringItemTypeId=document.getElementById("strEngineeringItemTypeId").value;
	
	var url="";
	var mode="";
	
	
	if(strFilterBy==0) {
		return;
	} 
	if(strFilterValue==0) {
		var strHtml="<tr>";
		strHtml=strHtml+"<td width=\"5%\" class=\"LABEL_TD\" style=\"text-align: center;\">&nbsp;</td>";
		strHtml=strHtml+"<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Service Engineer Name</td>";
		strHtml=strHtml+"<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Skills</td>";
		strHtml=strHtml+"<td width=\"35%\" class=\"LABEL_TD\" style=\"text-align: center;\">Number of Jobs</td>";
		strHtml=strHtml+"</tr>";
		strHtml=strHtml+"<tr>";
		strHtml=strHtml+"<td colspan=\"4\" class=\"CONTROL_TD\" style=\"text-align: center;color:red\">No Data Found!</td>";
		strHtml=strHtml+"</tr>";
		var elementOtherServiceEngineerDetailsTable = document.getElementById("strOtherServiceEngineerDetailsTable");
		elementOtherServiceEngineerDetailsTable.innerHTML = strHtml;
	} 	
		
	url = "ComplaintMaintenanceStatusTransACTION.cnt?hmode=getOtherServiceEngineerDetailsTable&strEngineeringItemTypeId="
	+ strEngineeringItemTypeId + "&strFilterBy=" + strFilterBy + "&strFilterValue=" + strFilterValue;

	
	
	ajaxFunction(url, "2");
	
	
}





function getAjaxResponse(res, mode) {

	var objVal;

	var err = document.getElementById("strErrMsg");

	/* Response String format: responseStatus####responseValue */
	var arrStrResponse = res.split("####");

	var strResponseStatus = arrStrResponse[0];
	var strResponseValue = arrStrResponse[1];

	if (strResponseStatus == "ERROR") {

		err.innerHTML = strResponseValue;

	} else {

		/* Mode 1 is for filter value population */
		if (mode == "1") {

			var elementFilterValueCombo = document.getElementById("strFilterValue");
			elementFilterValueCombo.innerHTML = strResponseValue;
			
			var strHtml="<tr>";
			strHtml=strHtml+"<td width=\"5%\" class=\"LABEL_TD\" style=\"text-align: center;\">&nbsp;</td>";
			strHtml=strHtml+"<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Service Engineer Name</td>";
			strHtml=strHtml+"<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Skills</td>";
			strHtml=strHtml+"<td width=\"35%\" class=\"LABEL_TD\" style=\"text-align: center;\">Number of Jobs</td>";
			strHtml=strHtml+"</tr>";
			strHtml=strHtml+"<tr>";
			strHtml=strHtml+"<td colspan=\"4\" class=\"CONTROL_TD\" style=\"text-align: center;color:red\">No Data Found!</td>";
			strHtml=strHtml+"</tr>";
			
			var elementOtherServiceEngineerDetailsTable = document.getElementById("strOtherServiceEngineerDetailsTable");
			elementOtherServiceEngineerDetailsTable.innerHTML = strHtml;
			
			

		} else if (mode == "2") {


			var elementOtherServiceEngineerDetailsTable = document.getElementById("strOtherServiceEngineerDetailsTable");
			elementOtherServiceEngineerDetailsTable.innerHTML = strResponseValue;
			
			

		}  
		

	}
}

function clearSchedule() {
	
	document.forms[0].reset();
	var elementFilterValueCombo = document.getElementById("strFilterValue");
		elementFilterValueCombo.innerHTML = "<option value='0'>Select Value</option>";
		
		var strHtml="<tr>";
		strHtml=strHtml+"<td width=\"5%\" class=\"LABEL_TD\" style=\"text-align: center;\">&nbsp;</td>";
		strHtml=strHtml+"<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Service Engineer Name</td>";
		strHtml=strHtml+"<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Skills</td>";
		strHtml=strHtml+"<td width=\"35%\" class=\"LABEL_TD\" style=\"text-align: center;\">Number of Jobs</td>";
		strHtml=strHtml+"</tr>";
		strHtml=strHtml+"<tr>";
		strHtml=strHtml+"<td colspan=\"4\" class=\"CONTROL_TD\" style=\"text-align: center;color:red\">No Data Found!</td>";
		strHtml=strHtml+"</tr>";
		var elementOtherServiceEngineerDetailsTable = document.getElementById("strOtherServiceEngineerDetailsTable");
		elementOtherServiceEngineerDetailsTable.innerHTML = strHtml;
	
}

function cancelPage()
{
	document.forms[0].hmode.value = "unspecified";
	document.forms[0].submit();
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