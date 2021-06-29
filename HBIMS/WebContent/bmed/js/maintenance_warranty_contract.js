function showTableRowGroup(id) {

	var tbodyElement = document.getElementById(id);
	tbodyElement.style.display = 'table-row-group';

}
function hideTableRowGroup(id) {

	var tbodyElement = document.getElementById(id);
	tbodyElement.style.display = 'none';

}

function showOrHideWarrantyDetails(mode, thisImg) {
	if (thisImg == null) {
		
	}
	if (mode == "SHOW") {
		// Change Image Attribute
		thisImg.src = "../../hisglobal/images/minus.gif";
		thisImg.title = "Hide";

		thisImg.removeAttribute("onclick");
		thisImg.setAttribute("onclick",
				"showOrHideWarrantyDetails('HIDE',this);");

		// Change body display
		showTableRowGroup('tbodyWarrantyDetailsHead');
		showTableRowGroup('tbodyWarrantyDetailsBody');

	} else if (mode == "HIDE") {
		// Change Image Attribute
		thisImg.src = "../../hisglobal/images/plus.gif";
		thisImg.title = "Show";

		thisImg.removeAttribute("onclick");
		thisImg.setAttribute("onclick",
				"showOrHideWarrantyDetails('SHOW',this);");

		// Change body display
		hideTableRowGroup('tbodyWarrantyDetailsHead');
		hideTableRowGroup('tbodyWarrantyDetailsBody');
	} else {
		//alert("showOrHideWarrantyDetails ::=> Unknown mode:" + mode);
	}
}
function showOrHideMaintenanceContractDetails(mode, thisImg) {
	if (thisImg == null) {
		alert("Cannot find this image object.");
	}
	if (mode == "SHOW") {
		// Change Image Attribute
		thisImg.src = "../../hisglobal/images/minus.gif";
		thisImg.title = "Hide";

		thisImg.removeAttribute("onclick");
		thisImg.setAttribute("onclick",
				"showOrHideMaintenanceContractDetails('HIDE',this);");

		// Change body display
		showTableRowGroup('tbodyMaintenanceContractDetailsHead');
		showTableRowGroup('tbodyMaintenanceContractDetailsBody');

	} else if (mode == "HIDE") {
		// Change Image Attribute
		thisImg.src = "../../hisglobal/images/plus.gif";
		thisImg.title = "Show";

		thisImg.removeAttribute("onclick");
		thisImg.setAttribute("onclick",
				"showOrHideMaintenanceContractDetails('SHOW',this);");

		// Change body display
		hideTableRowGroup('tbodyMaintenanceContractDetailsHead');
		hideTableRowGroup('tbodyMaintenanceContractDetailsBody');
	} else {
		alert("showOrHideMaintenanceContractDetails ::=> Unknown mode:" + mode);
	}
}
function ajaxCallGetData(strDisplayMode, strProcessMode, strItemId) {

	if (strDisplayMode == null) {

		alert("Display Mode unspecified.");
		return;

	}
	if (strProcessMode == null) {

		alert("Process Mode unspecified.");
		return;

	}
	if (strItemId == null) {

		alert("Item Id/ Non Item Id unspecified.");
		return;

	}
   // alert("ok");
	var url = "/HBIMS/bmed/global/MainteWarrantyContractCNT.cnt?hmode=getData&strDisplayMode="
			+ strDisplayMode
			+ "&strProcessMode="
			+ strProcessMode
			+ "&strItemId=" + strItemId;
	//alert(url);
	var xmlhttp;

	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari

		xmlhttp = new XMLHttpRequest();

	} else {// code for IE6, IE5

		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");

	}
	xmlhttp.onreadystatechange = function() {

		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var strResponseText = xmlhttp.responseText;
			writeResponse(strResponseText);
		}
	};
	xmlhttp.open("GET", url, true);
	xmlhttp.send();

}
function writeResponse(strResponseText) {
	var elementWarrantyDetailsBody = document
			.getElementById("tbodyWarrantyDetailsBody");
	var elementMaintenanceContractDetailsBody = document
			.getElementById("tbodyMaintenanceContractDetailsBody");

	var arrResString = strResponseText.split("####");

	if (elementWarrantyDetailsBody != null) {
		elementWarrantyDetailsBody.innerHTML = arrResString[0];
	} else {
		alert("tbodyWarrantyDetailsBody not found!");
	}
	if (elementMaintenanceContractDetailsBody != null) {
		elementMaintenanceContractDetailsBody.innerHTML = arrResString[1];
	} else {
		alert("tbodyMaintenanceContractDetailsBody not found!");
	}
}

function ajaxCallGetDataTrans(strDisplayMode, strProcessMode, strItemId) {

	if (strDisplayMode == null) {

		alert("Display Mode unspecified.");
		return;

	}
	if (strProcessMode == null) {

		alert("Process Mode unspecified.");
		return;

	}
	if (strItemId == null) {

		alert("Item Id/ Non Item Id unspecified.");
		return;

	}
   // alert("ok");
	var url = "/HBIMS/bmed/global/MainteWarrantyContractCNT.cnt?hmode=getDataTrans&strDisplayMode="
			+ strDisplayMode
			+ "&strProcessMode="
			+ strProcessMode
			+ "&strItemId=" + strItemId;
	//alert(url);
	var xmlhttp;

	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari

		xmlhttp = new XMLHttpRequest();

	} else {// code for IE6, IE5

		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");

	}
	xmlhttp.onreadystatechange = function() {

		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var strResponseText = xmlhttp.responseText;
			writeResponse(strResponseText);
		}
	};
	xmlhttp.open("GET", url, true);
	xmlhttp.send();

}