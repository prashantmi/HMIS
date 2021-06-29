function getAjaxComplaintRequestData() {

	var url;
	var mode = "getAjaxComplaintRequestData";
	var strDeptCode = document.getElementById("strDeptCode").value;

	var strIsAttached = 0;
	if (document.getElementById("strIsAttached").checked == true) 
	{
		strIsAttached = 1;
	}

	var elementActiveComplaintsTable = document.getElementById("activeComplaintsTable");
	var elementActivePreventiveMaintenanceBody = document.getElementById("activePreventiveMaintenanceTable");
	var elementErrMsgDiv = document.getElementById("strErrMsg");

	if (strDeptCode == "0") 
	{
		
		var strHTML = "<tr><td width='15%' class='LABEL_TD' style='text-align: center;'>Request Id / Date</td><td width='15%' class='LABEL_TD' style='text-align: center;'>Request Type</td><td width='15%' class='LABEL_TD' style='text-align: center;'>Equipment Name</td><td width='20%' class='LABEL_TD' style='text-align: center;'>Status</td><td width='15%' class='LABEL_TD' style='text-align: center;'>Actions</td><td width='15%' class='LABEL_TD' style='text-align: center;'>Reminders</td><td width='5%' class='LABEL_TD' style='text-align: center;'>&nbsp;</td></tr>";
		strHTML = strHTML +"<tr><td colspan='7' class='CONTROL_TD' style='text-align: center; color: red;'>No Data Found!</td></tr>";
		elementActiveComplaintsTable.innerHTML = strHTML;
		elementActivePreventiveMaintenanceBody.innerHTML = strHTML;
		elementErrMsgDiv.innerHTML = "";
		return;
	}

	url = "ComplaintMaintenanceStatusTransACTION.cnt?hmode=" + mode
			+ "&strDeptCode=" + strDeptCode + "&strIsAttached=" + strIsAttached;

	ajaxFunction(url, "1");

}

function getAjaxResponse(res, mode) 
{

	var objVal;

	var elementErrMsgDiv = document.getElementById("strErrMsg");

	/* Response String format: responseStatus####responseValue */
	
	var arrStrResponse = res.split("####");

	var strResponseStatus = arrStrResponse[0];
	var strResponseValue = arrStrResponse[1];

	if (strResponseStatus == "ERROR") {

		elementErrMsgDiv.innerHTML = strResponseValue;

	} 
	else 
	{

		if (mode == "1") 
		{
            
			var arrStrResponseValue = strResponseValue.split("^^^^");

			var strActiveComplaintsBody = arrStrResponseValue[0].split("^^")[0];
			if (strActiveComplaintsBody == null	|| strActiveComplaintsBody == "") 
			{
				strActiveComplaintsBody = "<tr><td colspan='7' class='CONTROL_TD' style='text-align: center; color: red;'>No Data Found!</td></tr>";
			}

			var strActiveComplaintsPagination = arrStrResponseValue[0].split("^^")[1];
			if (strActiveComplaintsPagination == null) 
			{
				strActiveComplaintsPagination = "";
			}

			var strActivePreventiveMaintenanceBody = arrStrResponseValue[1].split("^^")[0];
			if (strActivePreventiveMaintenanceBody == null|| strActivePreventiveMaintenanceBody == "") 
			{
				strActivePreventiveMaintenanceBody = "<tr><td colspan='7' class='CONTROL_TD' style='text-align: center; color: red;'>No Data Found!</td></tr>";
			}
			var strActivePreventiveMaintenancePagination = arrStrResponseValue[1].split("^^")[1];
			if (strActivePreventiveMaintenancePagination == null) 
			{
				strActivePreventiveMaintenancePagination = "";
			}

			var elementActiveComplaintsTable = document.getElementById("activeComplaintsTable");
			var elementActiveComplaintsPagination = document.getElementById("activeComplaintsTablePagination");
			var elementActivePreventiveMaintenanceTable = document.getElementById("activePreventiveMaintenanceTable");
			var elementActivePreventiveMaintenancePagination = document.getElementById("activePreventiveMaintenanceTablePagination");

			var strHTML = "<tr><td width='15%' class='LABEL_TD' style='text-align: center;'>Request Id / Date</td><td width='15%' class='LABEL_TD' style='text-align: center;'>Request Type</td><td width='15%' class='LABEL_TD' style='text-align: center;'>Equipment Name</td><td width='20%' class='LABEL_TD' style='text-align: center;'>Status</td><td width='15%' class='LABEL_TD' style='text-align: center;'>Actions</td><td width='15%' class='LABEL_TD' style='text-align: center;'>Reminders</td><td width='5%' class='LABEL_TD' style='text-align: center;'>&nbsp;</td></tr>";
            
			elementActiveComplaintsTable.innerHTML = strHTML+ strActiveComplaintsBody;
			elementActiveComplaintsPagination.innerHTML = strActiveComplaintsPagination;

           
			elementActivePreventiveMaintenanceTable.innerHTML = strHTML	+ strActivePreventiveMaintenanceBody;
			
			elementActivePreventiveMaintenancePagination.innerHTML = strActivePreventiveMaintenancePagination;
			
            
			// pagination('activeComplaintsTable', '1', '5', '10');
			// pagination('activePreventiveMaintenanceTable', '1', '5', '10');

			var elementFirstPageActiveComplaints = document.getElementById("activeComplaintsTable_1");
			/* Data may not exist, so null checking. */
			if (elementFirstPageActiveComplaints != null) 
			{
				var strFirstPageOnclickActiveComplaints = elementFirstPageActiveComplaints.getAttribute("onclick");
				eval(strFirstPageOnclickActiveComplaints);
			}

			var elementFirstPageActivePreventiveMaintenance = document.getElementById("activePreventiveMaintenanceTable_1");
			/* Data may not exist, so null checking. */
			if (elementFirstPageActivePreventiveMaintenance != null) 
			{
				var strFirstPageOnclickActivePreventiveMaintenance = elementFirstPageActivePreventiveMaintenance.getAttribute("onclick");
				eval(strFirstPageOnclickActivePreventiveMaintenance);
			}

		}

	}
}

/* This Function Will Show Row Group */
function showRow(strRowId) {
	var elementRow = document.getElementById(strRowId);

	if (elementRow != null) {
		elementRow.style.display = "table-row";
	}

}

/* This Function Will Hide Row Group */
function hideRow(strRowId) {
	var elementRow = document.getElementById(strRowId);

	if (elementRow != null) {

		elementRow.style.display = "none";
	}

}

/*
 * rowGroupPattern: e.g. currentPageIndex: 1 to numberOfPages numberOfPages
 */
function pagination(strTableId, strPageIndex, strNumberOfRowsPerPage,
		strNumberOfPages) {
	var npageCounter = 0;
	var nRowCounter = 0;
	var nPageIndex = parseInt(strPageIndex);
	var nNumberOfRowsPerPage = parseInt(strNumberOfRowsPerPage);
	var nNumberOfPages = parseInt(strNumberOfPages);

	for (npageCounter = 1; npageCounter <= nNumberOfPages; ++npageCounter) {

		if (npageCounter == nPageIndex) {
			for (nRowCounter = 1; nRowCounter <= nNumberOfRowsPerPage; ++nRowCounter) {
				var strRowId = strTableId + "_" + npageCounter + "_"
						+ nRowCounter;
				showRow(strRowId);
			}

		} else {
			for (nRowCounter = 1; nRowCounter <= nNumberOfRowsPerPage; ++nRowCounter) {
				var strRowId = strTableId + "_" + npageCounter + "_"
						+ nRowCounter;
				hideRow(strRowId);
			}

		}
	}

}

function goAction(strActionElementId, strHiddenComplaintId) {
	var elementActionId = document.getElementById(strActionElementId);
	var nActionId = parseInt(elementActionId.value);
	var strHmode = null;
	if (nActionId == 0) {
		alert("Select Action");
		elementActionId.focus();
		return;
	} else {
		switch (nActionId) {
		case 1:
			strHmode = "initializeCancel";
			break;
		case 2:
			strHmode = "initializeSchedule";
			break;
		case 3:
			strHmode = "initializeAttended";
			break;
		case 4:
			strHmode = "initializeReminder";
			break;
		case 5:
			strHmode = "initializeGrievances";
			break;
		case 6:
			strHmode = "initializeClose";
			break;

		}
	}

	if (strHmode != null && strHmode != "") {
		document.forms[0].hmode.value = strHmode;
		document.forms[0].strHiddenComplaintId.value = strHiddenComplaintId;
		document.forms[0].submit();
	} else {
		alert("mode is empty!");
	}

}

function validateNewComplaint(mode) {

	var hisValidator = new HISValidator("complaintMaintenanceStatusBean");

	hisValidator.addValidation("strDeptCode", "dontselect='0'",	"Please Select a Hospital.");

	var retVal = hisValidator.validate();

	hisValidator.clearAllValidations();

	if (retVal) {

		document.forms[0].hmode.value = mode;
		document.forms[0].submit();
	}

}

function cancelComplaintMaintenanceStatus(mode) {

	document.forms[0].hmode.value = mode;
	document.forms[0].submit();
}

function clearComplaintMaintenanceStatus() {

	document.forms[0].hmode.value = "unspecified";
	document.forms[0].submit();
}

function showView(strComplaintId) {
	
	//http://localhost:8080/HEMMS_ODISHA/bmed/transactions/ComplaintMaintenanceStatusTransACTION.cnt?hmode=viewDetails&strComplaintId=1110510003
		document.forms[0].hmode.value = "viewDetails";
		document.forms[0].strComplaintId.value = strComplaintId;
		document.forms[0].submit();
	
}