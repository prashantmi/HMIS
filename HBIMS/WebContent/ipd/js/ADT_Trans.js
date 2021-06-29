/** ***** JS Functions for Discharge , Transfer & Leave ********* */

function enter(e, frm) {
	if (e.keyCode == 13) {
		var hisValidator = new HISValidator(frmName);
		hisValidator.addValidation("strCrNo", "req",
				"Cr No. is a Mandatory Field");
		hisValidator.addValidation("strCrNo", "minlen="
				+ document.forms[0].strCrNo.maxLength, "Cr No. must be "
				+ document.forms[0].strCrNo.maxLength + " Digits");
		var retVal = hisValidator.validate();
		if (retVal) {
			document.forms[0].hmode.value = "GO";// go func
			document.forms[0].submit();
			adt_create_loading_msg();// loading_msg
		} else {
			return false;
		}
	}
}

function checkDblOcc() {
	try {
		if (document.forms[0].strPrevDblOcc.value == "1")
			document.forms[0].strIsBedVacant.checked = true;
	} catch (e) {
	}
}
function enterLeaveJoin(e, frm) {
	if (e.keyCode == 13) {
		var hisValidator = new HISValidator(frmName);
		+hisValidator.addValidation("strCrNo", "req",
				"Cr No. is a Mandatory Field");
		hisValidator.addValidation("strCrNo", "minlen="
				+ document.forms[0].strCrNo.maxLength, "CR No. must be "
				+ document.forms[0].strCrNo.maxLength + " Digits");
		var retVal = hisValidator.validate();
		if (retVal) {
			document.forms[0].hmode.value = "GO";// go func
			document.forms[0].submit();
			goFunc_recordEntry('patientLeaveJoinRecordTransBean');// loading_msg
		} else
			return false;
	}
}
function goFunc(frmName) // for CR No. field validation
{

	var hisValidator = new HISValidator(frmName);
	var typ = hisValidator.type;
	hisValidator.clearAllValidations();
	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field");
	hisValidator.addValidation("strCrNo", "minlen="
			+ document.forms[0].strCrNo.maxLength, "CR No. must be "
			+ document.forms[0].strCrNo.maxLength + " Digits");
	var retVal = hisValidator.validate();
	document.forms[0].strTempVal.value = document.forms[0].strCrNo.value;
	if (retVal) {
		document.forms[0].hmode.value = "GO";
		document.forms[0].submit();
		adt_create_loading_msg();// loading_msg
	} else {
		return false;
	}
}

function cancel() {
	document.forms[0].hmode.value = "CANCEL";
	document.forms[0].strCrNo.value = "";
	document.forms[0].submit();
}

function init_Main() {

	document.forms[0].hmode.value = "INITIALPAGE";
	document.forms[0].strCrNo.value = "";
	document.forms[0].submit();
}

function cancel_dtl_sub() {
	if (document.forms[0].strCancelRmk.value == "") {
		return false;
	} else {
		return true;
	}
}

function getAjaxResponse(res, mode) {
	if (mode == "1") {
		var objVal = document.getElementById("unitId");
		if (typeof (document.forms[0].strWard) != "undefined") {
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strUnit' class='comboNormal' onChange='funward(this);'>"	+ res + "</select>";
			document.forms[0].strWard.selectedIndex = 0;
			if (typeof (document.forms[0].changeUnit) != "undefined") {
				var temp = document.forms[0].strCurrentDeptUnitRoom.value.split('^');
				// Removed Change Unit Option By Pragya on 02-May-2011
				/*
				 * if(document.forms[0].strDepartment.value==temp[0]) {
				 * document.getElementById("changeUnitId").style.display=""; var
				 * unit=temp[1]; document.forms[0].strUnit.value=unit;
				 * if(document.forms[0].changeUnit.checked)
				 * document.forms[0].strUnit.disabled=false; else
				 * document.forms[0].strUnit.disabled=true; funward(); } else {
				 */
				document.getElementById("changeUnitId").style.display = "none";
				document.forms[0].changeUnit.checked = false;
				document.getElementById("wardId").innerHTML = "<select style='cursor:pointer' name = 'strWard' class='comboNormal' onChange='funroom(this);' ><option value='0'>Select Value</option></select>";
				// }
			}
		} else
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strUnit' class='comboNormal' onChange='funconsultant();'>"+ res + "</select>";
		if (typeof (document.forms[0].strWardType) != "undefined")
			document.forms[0].strWardType.selectedIndex = 0;
		if (typeof (document.forms[0].strRoomType) != "undefined")
			document.forms[0].strRoomType.selectedIndex = 0;
		if (typeof (document.forms[0].strRoom) != "undefined")
			document.forms[0].strRoom.selectedIndex = 0;
		var l = document.forms[0].strUnit.length;
		/*
		 * if(l=="2") document.forms[0].strUnit.selectedIndex=l-1;
		 */
	}
	if (mode == "2") {
		var objVal = document.getElementById("wardId");
		if (document.forms[0].name == "patientLeaveBean")
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strWard' class='comboNormal' onChange='funroomLJ();' >"+ res + "</select>";
		else
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strWard' class='comboNormal' onChange='funroom(this);' >"+ res + "</select>";
		var l = document.forms[0].strWard.length;
		/*
		 * if(l=="2") document.forms[0].strWard.selectedIndex=l-1;
		 */
	}
	if (mode == "3") {
		// alert("res=="+res);
		var objVal = document.getElementById("roomId");
		if (typeof (document.forms[0].strBed) != "undefined")
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strRoom' class='comboNormal' onChange='sharableChkBox();'>"+ res + "</select>";
		else
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strRoom' class='comboNormal' >"+ res + "</select>";
		var l = document.forms[0].strRoom.length;
		funbed();
		/*
		 * if(l=="2") { document.forms[0].strRoom.selectedIndex=l-1;
		 * //alert(document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].value);
		 * if(document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].value=="0") {
		 * alert("MS Approval Required!!"); } }
		 */

		if (typeof (document.forms[0].strTransferUnit) != "undefined"
				&& document.forms[0].strTransferUnit[1].checked) {
			funbed();
		}
	}

	if (mode == "6") {
		// alert("res=="+res);
		var objVal = document.getElementById("roomId");
		if (typeof (document.forms[0].strBed) != "undefined")
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strRoom' class='comboNormal' onChange='sharableChkBox();'>"+ res + "</select>";
		else
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strRoom' class='comboNormal' >"
					+ res + "</select>";
		var l = document.forms[0].strRoom.length;
		funbedIPD();
		/*
		 * if(l=="2") { document.forms[0].strRoom.selectedIndex=l-1;
		 * //alert(document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].value);
		 * if(document.forms[0].strRoom[document.forms[0].strRoom.selectedIndex].value=="0") {
		 * alert("MS Approval Required!!"); } }
		 */

		if (typeof (document.forms[0].strTransferUnit) != "undefined"
				&& document.forms[0].strTransferUnit[1].checked) {
			funbedIPD();
		}
	}

	if (mode == "4") {
		// alert("res=="+res);
		var objVal = document.getElementById("bedId");
		objVal.innerHTML = "<select style='cursor:pointer' name = 'strBed' title='Select from list of vacant Beds' class='comboNormal' onChange=''>"+ res + "</select>";
		var l = document.forms[0].strBed.length;
		try {
			if (l > 0 && document.forms[0].name == "dischargecancelTransBean") {
				var bedno = document.getElementsByName("curWrdBedCode")[0].value.split('^')[1];
				for (var i = 0; i < l; i++)
					{
							if (document.getElementsByName("strBed")[0].options[i].value.split('^')[0] == bedno)
							{
								document.getElementsByName("strBed")[0].selectedIndex = i;
								break;
							}
					}

			}
		} catch (e) {
		}

		/*
		 * if(l==1 && document.forms[0].strRoom.value!="0" &&
		 * document.forms[0].strBedType.value!="0") //Updated By Pragya dated
		 * 01-Mar-2011 The Alert should be shown only in case of selection of
		 * both Room & Bed Type alert("No vacant beds available.Click on bed to
		 * view Bed status chart!!"); if(l=="2")
		 * document.forms[0].strBed.selectedIndex=l-1;
		 */
	}
	if (mode == "5") {
		// alert("res=="+res);
		res = "<option value='0'>Select Value</option>" + res;
		var objVal = document.getElementById("consultantId");
		objVal.innerHTML = "<select style='cursor:pointer' name='strConsultantCode' class='comboMax' dir='' title='' onChange=''>"+ res + "</select>";
		var l = document.forms[0].strConsultantCode.length;
		/*
		 * if(l=="2") document.forms[0].strConsultantCode.selectedIndex=l-1;
		 */
	}
	if (mode == "111") {
		var objVal = document.getElementById("id1");
		objVal.innerHTML = res;
//alert(res);
		try {
			checkDblOcc();
			// strTransferUnit is changed form combo to radio
			// if(document.forms[0].strTransferUnit[document.forms[0].strTransferUnit.selectedIndex].value=="1")//DeptUnit
			if (document.forms[0].strTransferUnit[0].checked)// DeptUnit 1
			{
				getAdvisedBy();
			}
			if (document.forms[0].strTransferUnit[2].checked)// Unit Change
			{

				getAdvisedBy();
			}
			if (document.forms[0].strTransferUnit[1].checked)// Room/Bed 2
			{
				funroom(document.forms[0].strRoomType);
			}
		} catch (e) {
		}

		try {
			funroom_disCancel();// Polpulating the Room By Default
		} catch (e) {
		}

		// document.getElementById("normalMsg").style.display="none";
	}

	/*
	 * IN Case Of IPD Only Merging Of nursing desk in to IPD Desk
	 * 
	 */
	if (mode == "7") {
		var objVal = document.getElementById("id1");
		objVal.innerHTML = res;

		try {
			checkDblOcc();
			// strTransferUnit is changed form combo to radio
			// if(document.forms[0].strTransferUnit[document.forms[0].strTransferUnit.selectedIndex].value=="1")//DeptUnit
			if (document.forms[0].strTransferUnit[0].checked)// DeptUnit 1
			{
				getAdvisedBy();
			}
			if (document.forms[0].strTransferUnit[2].checked)// Unit Change
			{

				getAdvisedBy();
			}
			if (document.forms[0].strTransferUnit[1].checked)// Room/Bed 2
			{
				funroomIPD(document.forms[0].strRoomType);
			}
		} catch (e) {
		}

		try {
			funroom_disCancel();// Polpulating the Room By Default
		} catch (e) {
		}

		// document.getElementById("normalMsg").style.display="none";
	}

	if (mode == '222') {
		var objEle = document.getElementById("menu1");
		objEle.innerHTML = res;
		objEle.style.display = "none";
		display_popup_menu(pWindow, "menu1", "365", "");
	}
	if (mode == '300') {
		var objEle1 = document.getElementById("VisitPassReprint");
		objEle1.innerHTML = res;
	}
	if (mode == '6') {
		var objVal = document.getElementById("unitId");
		objVal.innerHTML = "<select style='cursor:pointer' name = 'strUnit' class='comboNormal' onChange='funconsultant();'>"+ res + "</select>";

	}
	if (mode == '555') {
		var objVal = document.getElementById("roomId");
		objVal.innerHTML = "<select style='cursor:pointer' name = 'strRoom' class='comboNormal' onChange=''>"+ res + "</select>";

	}
	if (mode == '333') // Response for Advised By Consultants
	{
		document.getElementById("idDisBy").innerHTML = "<select class='comboMax' title='Doctors List' name='strRmk'>"+ res + "</select>";
		if (typeof (document.forms[0].strConsultantId) == "undefined")
			document.forms[0].strRmk.value = 0;
		else
			document.forms[0].strRmk.value = document.forms[0].strConsultantId.value;

	}

	if (mode == '444') {
		var serviceName = res.split("@")[0];

		var strBedVacantMode = res.split("@")[1]; // each time beds need to be
													// vacant to transfer
													// patient to OTHERS
		var objVal = document.getElementById("serviceId");
		if (serviceName == "" || serviceName == null)
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strServiceName' class='comboNormal' onchange='serviceValidate(this)'><option value='0'>Select Value</option></select>";
		else
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strServiceName' class='comboNormal' onchange='serviceValidate(this)'>"
					+ serviceName + "</select>";
		if (strBedVacantMode == 0) {
			document.forms[0].strWhetherVacantBed.checked = false;
		}
		if (strBedVacantMode == 1) {
			var strWardTypeCode = document.forms[0].strWardTypeCode.value;
			var strPvtWardCode = document.forms[0].strPvtWardCode.value;

			if (strWardTypeCode == strPvtWardCode) {
				document.forms[0].strWhetherVacantBed.checked = false;
				document.forms[0].strWhetherVacantBed.value = "0";
			} else {
				document.forms[0].strWhetherVacantBed.checked = true;
				document.forms[0].strWhetherVacantBed.value = "1";
			}
		}
		if (strBedVacantMode == 2) {
			document.forms[0].strWhetherVacantBed.checked = false;
			document.forms[0].strWhetherVacantBed.disabled = false;
		}
		document.forms[0].strWhetherVacantBed.disabled = true;

	}

	if (mode == "666") {
		if (res == 0)
			document.forms[0].strValidatedFlag.value = 0;
		else
			document.forms[0].strValidatedFlag.value = 1;
	}

	if (mode == '777') {
		var serviceName = res.split("@")[0];

		var strBedVacantMode = 0; // each time beds need to be vacant to
									// transfer patient to OTHERS
		var objVal = document.getElementById("serviceId");
		if (serviceName == "" || serviceName == null)
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strServiceName' class='comboNormal' onchange='serviceValidate(this)'><option value='0'>Select Value</option></select>";
		else
			objVal.innerHTML = "<select style='cursor:pointer' name = 'strServiceName' class='comboNormal' onchange='serviceValidate(this)'>"
					+ serviceName + "</select>";
		if (strBedVacantMode == 0) {
			document.forms[0].strWhetherVacantBed.checked = false;
		}
		if (strBedVacantMode == 1) {
			document.forms[0].strWhetherVacantBed.checked = true;
		}
		if (strBedVacantMode == 2) {
			document.forms[0].strWhetherVacantBed.checked = false;
			document.forms[0].strWhetherVacantBed.disabled = false;
		}
		document.forms[0].strWhetherVacantBed.disabled = true;
	}

}

function openPopup(mode) {
	var s = document.forms[0].curWrdBedCode.value;
	var d = document.forms[0].curDept_Unt_RomCode.value;
	var myArray = new Array();
	var myArray1 = new Array();
	myArray = s.split("^");
	myArray1 = d.split("^");
	var temp = myArray[0] + "^" + document.forms[0].strRoom.value + "^"
			+ document.forms[0].strBedType.value + "^" + myArray1[1] + "^"
			+ document.forms[0].strCrNo.value;

	if (document.forms[0].strRoom.value == "0") {
		if (typeof (document.forms[0].strTransferUnit) != "undefined"
				&& document.forms[0].strTransferUnit[1].checked)// Room/Bed
		{
			bedStatusChart(temp);
			return;
		} else {
			bedStatusChart(temp);
			// alert("Room is Mandatory");
		}
	}
	// else if(document.forms[0].strBedType.value=="0")
	// alert("Bed Type is Mandetory");
	else
		bedStatusChart(temp);
}


/*
 *  Brings Pop up Of Bed
 *  Works in Case of IPD Nursing Desk
 */
function openPopupIPD(mode) {
	var s = document.forms[0].curWrdBedCode.value;
	var d = document.forms[0].curDept_Unt_RomCode.value;
	var myArray = new Array();
	var myArray1 = new Array();
	myArray = s.split("^");
	myArray1 = d.split("^");
	var temp = myArray[0] + "^" + document.forms[0].strRoom.value + "^"
			+ document.forms[0].strBedType.value + "^" + myArray1[1] + "^"
			+ document.forms[0].strCrNo.value;

	if (document.forms[0].strRoom.value == "0") {
		if (typeof (document.forms[0].strTransferUnit) != "undefined"
				&& document.forms[0].strTransferUnit[1].checked)// Room/Bed
		{
			bedStatusChartIPD(temp);
			return;
		} else {
			bedStatusChartIPD(temp);
			// alert("Room is Mandatory");
		}
	}
	// else if(document.forms[0].strBedType.value=="0")
	// alert("Bed Type is Mandetory");
	else
		bedStatusChartIPD(temp);
}

function bedStatusChart(WrdRoomBedtypUnt_code) {
	/*var mode = "BEDSTATUS";
	var url = "IpdCNT.cnt?hmode=" + mode + "&modPopUp=" + WrdRoomBedtypUnt_code;
	openPopUp(createFHashAjaxQuery(url), "300", "290", "1");*/
	
    $('.modal-container').css('display','block');
	
	var mode="BEDSTATUS1";
	//alert('1');
	var url="/HBIMS/ipd/transactions/IpdCNT.cnt?hmode="+mode+"&modPopUp="+WrdRoomBedtypUnt_code;
	$('#modalSpaceId').load(createFHashAjaxQuery(url));
}

/*
 *  Calls The pop up URL 
 *  Works in Case of IPD Nursing DESK
 */
function bedStatusChartIPD(WrdRoomBedtypUnt_code) {
	/*var mode = "BEDSTATUS";
	var url = "/HBIMS/ipd/transactions/IpdCNT.cnt?hmode=" + mode + "&modPopUp=" + WrdRoomBedtypUnt_code;
	openPopUp(createFHashAjaxQuery(url), "300", "290", "1");*/
	
	
    $('.modal-container').css('display','block');
	
	var mode="BEDSTATUS1";
	//alert('1');
	var url="/HBIMS/ipd/transactions/IpdCNT.cnt?hmode="+mode+"&modPopUp="+WrdRoomBedtypUnt_code;
	$('#modalSpaceId').load(createFHashAjaxQuery(url));
}


function fununit() {
	if (document.getElementById("unitId") != null) {
		var mode = "UNIT";
		var url = document.forms[0].cnt.value + "?hmode=" + mode + "&modDept="+ document.forms[0].strDepartment.value;
		ajaxFunction(url, "1");
	} else
		funward();
}

/*
 * 
 * For IPD Desk only
 * 
 */
function fununitIPD() {

	if (document.getElementById("unitId") != null) {
		var mode = "UNIT";
		var url = "/HBIMS/ipd/transactions/PatientTransferTransCNT.cnt?hmode="+ mode + "&modDept=" + document.forms[0].strDepartment.value;
		//alert(url);
		ajaxFunction(url, "1");
	} else {
		funwardIPD();
	}

}

function funUnit() {
	var mode = "UNIT";
	var url = document.forms[0].cnt.value + "?hmode=" + mode + "&modDept="+ document.forms[0].strDepartment.value;
	ajaxFunction(url, "6");
}

function getRoomCombo(obj) {
	var mode = "ROOMCOMBO";
	var unitCode = obj.value;
	var deptCode = document.forms[0].strCurrentDeptUnitRoom.value.split('^')[0];
	var wardCode = document.forms[0].curWrdBedCode.value.split('^')[0];
	var age = document.forms[0].strAge.value;
	var ageUnit = document.forms[0].strAgeUnit.value;
	var gender = document.forms[0].strSexCode.value;
	var category = document.forms[0].strCategoryCode.value;
	var url = document.forms[0].cnt.value + "?hmode=" + mode + "&deptCode="
			+ deptCode + "&wardCode=" + wardCode + "&unitCode=" + unitCode
			+ "&age=" + age + "&ageUnit=" + ageUnit + "&gender=" + gender
			+ "&category=" + category;
	ajaxFunction(url, "555");
}

/*
 * This function set Age, Age Unit Code and Sex Code
 */
function getAgeSex() {
	var temp1 = document.forms[0].strHiddenPatDtl.value.split("^");
	var strUnitCode = "1";
	var sexCode = "3";
	var strTemp = temp1[3];
	var strAgeSign = "";
	var temp2 = strTemp.split("/");
	/**
	 * This loop is for generating age Code
	 */
	for (var i = 0; i < strTemp.length; i++) {
		if (strTemp.charAt(i) == 'Y' || strTemp.charAt(i) == 'y') {
			strAgeSign = strTemp.charAt(i);
			strUnitCode = "3";
			break;
		} else if (strTemp.charAt(i) == 'M' || strTemp.charAt(i) == 'm') {
			strAgeSign = strTemp.charAt(i);
			strUnitCode = "2";
			break;
		} else if (strTemp.charAt(i) == 'D' || strTemp.charAt(i) == 'd') {
			strAgeSign = strTemp.charAt(i);
			strUnitCode = "1";
			break;
		} else if (strTemp.charAt(i) == 'W' || strTemp.charAt(i) == 'w') {
			strAgeSign = strTemp.charAt(i);
			strUnitCode = "4";
			break;
		} else if (strTemp.charAt(i) == 'H' || strTemp.charAt(i) == 'h') {
			strAgeSign = strTemp.charAt(i);
			strUnitCode = "5";
			break;
		} else {
			strUnitCode = "3";
			// break;
		}

	}
	/**
	 * This loop is for generating Sex Code
	 */
	for (var i = 0; i < temp2[1].length; i++) {
		if (temp2[1].charAt(i) == 'M' || temp2[1].charAt(i) == 'm') {
			sexCode = "1";
			break;
		}
		if (temp2[1].charAt(i) == 'F' || temp2[1].charAt(i) == 'f') {
			sexCode = "2";
			break;
		}

	}

	var temp3 = temp2[0].split(strAgeSign);
	document.forms[0].strAge.value = temp3[0];
	if (document.forms[0].strAge.value <= 0)
		document.forms[0].strAge.value = 1;
	document.forms[0].strAgeUnit.value = strUnitCode;
	document.forms[0].strSexCode.value = sexCode;

}

/*
 * This function set Age, Age Unit Code and Sex Code For IPD Only
 */
function getAgeSexIPD() {
	var temp1 = document.forms[0].strHiddenPatDtl.value.split("^");
	var strUnitCode = "1";
	var sexCode = "3";
	var strTemp = temp1[3];
	var strAgeSign = "";
	var temp2 = strTemp.split("/");
	/**
	 * This loop is for generating age Code
	 */
	for (var i = 0; i < strTemp.length; i++) {
		if (strTemp.charAt(i) == 'Y' || strTemp.charAt(i) == 'y') {
			strAgeSign = strTemp.charAt(i);
			strUnitCode = "3";
			break;
		} else if (strTemp.charAt(i) == 'M' || strTemp.charAt(i) == 'm') {
			strAgeSign = strTemp.charAt(i);
			strUnitCode = "2";
			break;
		} else if (strTemp.charAt(i) == 'D' || strTemp.charAt(i) == 'd') {
			strAgeSign = strTemp.charAt(i);
			strUnitCode = "1";
			break;
		} else if (strTemp.charAt(i) == 'W' || strTemp.charAt(i) == 'w') {
			strAgeSign = strTemp.charAt(i);
			strUnitCode = "4";
			break;
		} else if (strTemp.charAt(i) == 'H' || strTemp.charAt(i) == 'h') {
			strAgeSign = strTemp.charAt(i);
			strUnitCode = "5";
			break;
		} else {
			strUnitCode = "3";
			// break;
		}

	}
	/**
	 * This loop is for generating Sex Code
	 */
	for (var i = 0; i < temp2[1].length; i++) {
		if (temp2[1].charAt(i) == 'M' || temp2[1].charAt(i) == 'm') {
			sexCode = "1";
			break;
		}
		if (temp2[1].charAt(i) == 'F' || temp2[1].charAt(i) == 'f') {
			sexCode = "2";
			break;
		}

	}

	var temp3 = temp2[0].split(strAgeSign);
	document.forms[0].strAge.value = temp3[0];
	if (document.forms[0].strAge.value <= 0)
		document.forms[0].strAge.value = 1;
	document.forms[0].strAgeUnit.value = strUnitCode;
	document.forms[0].strSexCode.value = sexCode;

}

function funward(these) {

	// alert("Inside funward()");
	getAgeSex();
	var wardType;

	/*
	 * if(typeof(document.forms[0].strWardType)=="undefined") wardType=0; else
	 * wardType=document.forms[0].strWardType.value;
	 */

	if ((document.forms[0].strIsBedVacant.checked == false || (document.forms[0].strIsBedVacant.checked == true))
			|| document.forms[0].strPrevDblOcc.value == "1") {
		var mode = "WARD";
		var myArray1 = new Array();
		myArray1 = (document.forms[0].curDept_Unt_RomCode.value).split("^");
		if (typeof (document.forms[0].strDepartment) == "undefined")
			var temp = myArray1[0] + "^" + myArray1[1] + "^" + wardType;
		else
			var temp = document.forms[0].strDepartment.value + "^"
					+ document.forms[0].strUnit.value + "^" + wardType;
		var url = document.forms[0].cnt.value + "?hmode=" + mode
				+ "&modWardTpe=" + temp + '&ageCode='
				+ document.forms[0].strAgeUnit.value + '&sexCode='
				+ document.forms[0].strSexCode.value + '&age='
				+ document.forms[0].strAge.value + '&treatmentCategCode='
				+ document.forms[0].strCategoryCode.value + '&crNo='
				+ document.forms[0].strCrNo.value;
		// +'&deptCode='+document.forms[0].strDeptCode.value+'&crNo='+document.forms[0].strCrNo.value+'&deptUnitCode='+document.forms[0].strDeptUnitCode.value+'&msApprovalStatus='+document.forms[0].strMsApprovalStatus.value+'&msApprovalFlag='+document.forms[0].strMsApprovalFlag.value+'&wardName='+document.forms[0].strWardName.value+'&roomCode='+document.forms[0].strRoomCode.value+'&deptName='+deptName+'&treatmentCategCode='+document.forms[0].strTreatmentCategoryCode.value+'&ageCode='+document.forms[0].strAgeUnit.value+'&sexCode='+
		// document.forms[0].strSexCode.value+'&age='+document.forms[0].strAge.value;
		ajaxFunction(url, "2");
	} else {
		// document.forms[0].strWardType.value="0";
		try {
			if (these.name == "strWardType")
				alert("Must Select ICU Ward for Double Occupancy");
		} catch (e) {
		}
	}
}

/*
 * For IPD Desk Only
 * 
 */
function funwardIPD(these) {

	// alert("Inside funward()");
	getAgeSex();
	var wardType;

	/*
	 * if(typeof(document.forms[0].strWardType)=="undefined") wardType=0; else
	 * wardType=document.forms[0].strWardType.value;
	 */

	if ((document.forms[0].strIsBedVacant.checked == false || (document.forms[0].strIsBedVacant.checked == true))
			|| document.forms[0].strPrevDblOcc.value == "1") {
		var mode = "WARD";
		var myArray1 = new Array();
		myArray1 = (document.forms[0].curDept_Unt_RomCode.value).split("^");
		if (typeof (document.forms[0].strDepartment) == "undefined")
			var temp = myArray1[0] + "^" + myArray1[1] + "^" + wardType;
		else
			var temp = document.forms[0].strDepartment.value + "^"
					+ document.forms[0].strUnit.value + "^" + wardType;
		var url = "/HBIMS/ipd/transactions/PatientTransferTransCNT.cnt?hmode="
				+ mode + "&modWardTpe=" + temp + '&ageCode='
				+ document.forms[0].strAgeUnit.value + '&sexCode='
				+ document.forms[0].strSexCode.value + '&age='
				+ document.forms[0].strAge.value + '&treatmentCategCode='
				+ document.forms[0].strCategoryCode.value + '&crNo='
				+ document.forms[0].strCrNo.value;
		// +'&deptCode='+document.forms[0].strDeptCode.value+'&crNo='+document.forms[0].strCrNo.value+'&deptUnitCode='+document.forms[0].strDeptUnitCode.value+'&msApprovalStatus='+document.forms[0].strMsApprovalStatus.value+'&msApprovalFlag='+document.forms[0].strMsApprovalFlag.value+'&wardName='+document.forms[0].strWardName.value+'&roomCode='+document.forms[0].strRoomCode.value+'&deptName='+deptName+'&treatmentCategCode='+document.forms[0].strTreatmentCategoryCode.value+'&ageCode='+document.forms[0].strAgeUnit.value+'&sexCode='+
		// document.forms[0].strSexCode.value+'&age='+document.forms[0].strAge.value;
		ajaxFunction(url, "2");
	} else {
		// document.forms[0].strWardType.value="0";
		try {
			if (these.name == "strWardType")
				alert("Must Select ICU Ward for Double Occupancy");
		} catch (e) {
		}
	}
}

function funroom(these) {
	// Changed by Pragya on 02 May 2011
	if (document.getElementById("roomId") == null)
		return;

	getAgeSex();
	var mode = "ROOM";

	var myArray = new Array();

	// Selected Radio of strTransferUnit
	// if(document.forms[0].strTransferUnit[document.forms[0].strTransferUnit.selectedIndex].value=="2")
	if (document.forms[0].strTransferUnit[1].checked) {
		var s = document.forms[0].curWrdBedCode.value;
		myArray = s.split("^");
		var temp = myArray[0] + "^" + document.forms[0].strRoomType.value;
	} else {
		var objWard = document.getElementsByName("strWard");
		myArray = (objWard[0].value).split("^");
		var cr = document.forms[0].strCrNo.value;
		// alert(document.forms[0].curAdmAdvNo);
		if (document.forms[0].curAdmAdvNo == undefined)
			var admAdv = "";
		else
			var admAdv = document.forms[0].curAdmAdvNo.value;
		try {
			if (myArray[1] == "1")
				if (these.name == "strWard")
					alert("MS Approval is Required for this ward!!");
		} catch (e) {
		}
		var wrdTyp;
		if (typeof (document.forms[0].strWardType) == "undefined")
			wrdTyp = 0;
		else {
			wrdTyp = document.forms[0].strWardType[document.forms[0].strWardType.selectedIndex].value;
		}
		var roomType;
		if (typeof (document.forms[0].strRoomType) == "undefined")
			roomType = 0;
		else {
			roomType = document.forms[0].strRoomType[document.forms[0].strRoomType.selectedIndex].value;
		}
		var temp = myArray[0] + "^" + roomType + "^" + myArray[1] + "^"
				+ wrdTyp + "^" + cr + "^" + admAdv;
	}
	// alert(document.forms[0].curDept_Unt_RomCode.value.split("^")[1]);
	var url = document.forms[0].cnt.value + "?hmode=" + mode + "&modRoomType="
			+ temp + '&ageCode=' + document.forms[0].strAgeUnit.value
			+ '&sexCode=' + document.forms[0].strSexCode.value + '&age='
			+ document.forms[0].strAge.value + '&treatmentCategCode='
			+ document.forms[0].strCategoryCode.value + '&crNo='
			+ document.forms[0].strCrNo.value + "&deptUnitCode="
			+ document.forms[0].curDept_Unt_RomCode.value.split("^")[1];
	if (document.getElementsByName("strDepartment")[0])
		url += "&strDeptCode="
				+ document.getElementsByName("strDepartment")[0].value; // Added
																		// by
																		// Pragya
																		// on
																		// 03-May-2011
	var strDeptUnitCode;
	try {
		strDeptUnitCode = "&strDeptUnitCode="
				+ document.getElementsByName("strUnit")[0].value;
	} catch (_Err) {
		strDeptUnitCode = "&strDeptUnitCode="
				+ document.forms[0].curDept_Unt_RomCode.value.split("^")[1]
	}
	url = url + strDeptUnitCode;
	ajaxFunction(url, "3");
}

/*
 * For Nursing Desk Merging in IPD Desk Works Only For IPD Desk
 */

function funroomIPD(these) {
	// Changed by Pragya on 02 May 2011
	if (document.getElementById("roomId") == null)
		return;

	getAgeSex();
	var mode = "ROOM";

	var myArray = new Array();

	// Selected Radio of strTransferUnit
	// if(document.forms[0].strTransferUnit[document.forms[0].strTransferUnit.selectedIndex].value=="2")
	if (document.forms[0].strTransferUnit[1].checked) {
		var s = document.forms[0].curWrdBedCode.value;
		myArray = s.split("^");
		var temp = myArray[0] + "^" + document.forms[0].strRoomType.value;
	} else {
		var objWard = document.getElementsByName("strWard");
		myArray = (objWard[0].value).split("^");
		var cr = document.forms[0].strCrNo.value;
		// alert(document.forms[0].curAdmAdvNo);
		if (document.forms[0].curAdmAdvNo == undefined)
			var admAdv = "";
		else
			var admAdv = document.forms[0].curAdmAdvNo.value;
		try {
			if (myArray[1] == "1")
				if (these.name == "strWard")
					alert("MS Approval is Required for this ward!!");
		} catch (e) {
		}
		var wrdTyp;
		if (typeof (document.forms[0].strWardType) == "undefined")
			wrdTyp = 0;
		else {
			wrdTyp = document.forms[0].strWardType[document.forms[0].strWardType.selectedIndex].value;
		}
		var roomType;
		if (typeof (document.forms[0].strRoomType) == "undefined")
			roomType = 0;
		else {
			roomType = document.forms[0].strRoomType[document.forms[0].strRoomType.selectedIndex].value;
		}
		var temp = myArray[0] + "^" + roomType + "^" + myArray[1] + "^"
				+ wrdTyp + "^" + cr + "^" + admAdv;
	}
	// alert(document.forms[0].curDept_Unt_RomCode.value.split("^")[1]);
	var url = "/HBIMS/ipd/transactions/PatientTransferTransCNT.cnt?hmode="
			+ mode + "&modRoomType=" + temp + '&ageCode='
			+ document.forms[0].strAgeUnit.value + '&sexCode='
			+ document.forms[0].strSexCode.value + '&age='
			+ document.forms[0].strAge.value + '&treatmentCategCode='
			+ document.forms[0].strCategoryCode.value + '&crNo='
			+ document.forms[0].strCrNo.value + "&deptUnitCode="
			+ document.forms[0].curDept_Unt_RomCode.value.split("^")[1];
	if (document.getElementsByName("strDepartment")[0])
		url += "&strDeptCode="
				+ document.getElementsByName("strDepartment")[0].value; // Added
																		// by
																		// Pragya
																		// on
																		// 03-May-2011
	var strDeptUnitCode;
	try {
		strDeptUnitCode = "&strDeptUnitCode="
				+ document.getElementsByName("strUnit")[0].value;
	} catch (_Err) {
		strDeptUnitCode = "&strDeptUnitCode="
				+ document.forms[0].curDept_Unt_RomCode.value.split("^")[1]
	}
	url = url + strDeptUnitCode;
	ajaxFunction(url, "6");
}

function funbed() {
	
	
	var mode = "BED";
	var s = document.forms[0].curWrdBedCode.value;
	var d = document.forms[0].curDept_Unt_RomCode.value;
	var shr_chk=document.getElementById("sharableChkid").value;
	var myArray = new Array();
	var myArray1 = new Array();
	myArray = s.split("^");
	myArray1 = d.split("^");
	var strRoomType = document.forms[0].strRoomType.value;
	var temp = myArray[0] + "^" + document.forms[0].strRoom.value + "^"
			+ document.forms[0].strBedType.value + "^" + myArray1[1] + "^"
			+ strRoomType;
	var url = document.forms[0].cnt.value + "?hmode=" + mode + "&modBedType="
			+ temp + "&shr_chk="+shr_chk;
	ajaxFunction(url, "4");
}

/*
 * For Nursing desk Merging In IPD Desk Works in case OF OPD Desk Only
 * 
 */
function funbedIPD() {
	var mode = "BED";
	var s = document.forms[0].curWrdBedCode.value;
	var d = document.forms[0].curDept_Unt_RomCode.value;
	var shr_chk=document.getElementById("sharableChkid").value;
	var myArray = new Array();
	var myArray1 = new Array();
	myArray = s.split("^");
	myArray1 = d.split("^");
	var strRoomType = document.forms[0].strRoomType.value;
	var temp = myArray[0] + "^" + document.forms[0].strRoom.value + "^"
			+ document.forms[0].strBedType.value + "^" + myArray1[1] + "^"
			+ strRoomType;
	var url = "/HBIMS/ipd/transactions/PatientTransferTransCNT.cnt?hmode="
			+ mode + "&modBedType=" + temp+ "&shr_chk="+shr_chk;
	ajaxFunction(url, "4");
}

function funServRoom() {
	var mode = "ServRoom";
	var temp = document.forms[0].strServArea.value;
	var url = document.forms[0].cnt.value + "?hmode=" + mode + "&modServCode="
			+ temp;
	ajaxFunction(url, "3");
}

function funconsultant() {
	var mode = "ConsltntID";
	var temp = document.forms[0].strCrNo.value + "^"
			+ document.forms[0].strDepartment.value + "^"
			+ document.forms[0].strUnit.value;
	var url = document.forms[0].cnt.value + "?hmode=" + mode
			+ "&modConsltntID=" + temp;
	ajaxFunction(url, "5");
}

function groupCombo() {
	var l = document.forms[0].strDisRsn.length - 1;
	if (document.forms[0].strDisRsn.selectedIndex == l) {
		document.forms[0].strDrt.value = "";
		document.forms[0].strDrt.disabled = false;
	} else {
		document.forms[0].strDrt.value = document.forms[0].strDisRsn[document.forms[0].strDisRsn.selectedIndex].text;
		document.forms[0].strDrt.disabled = true;
	}
}

function butdis() {
	if (document.forms[0].strCrNo.value != "") {
		document.forms[0].strCrNo.readOnly = true;
	}
	if ((document.forms[0].name == "patientMoveTransBean")
			|| (document.forms[0].name == "patientDischargeBean"))
		document.forms[0].strTransferUnit[0].checked = true;
	// document.forms[0].strTransferUnit.selectedIndex=0;
	document.forms[0].strErrMsgString.value = "";
	document.forms[0].strNormalMsgString.value = "";
}

function CNTIni() {
	var frmName = document.forms[0].name;
	var cntVal;
	if (frmName == "patientDischargeBean")
		cntVal = "PatientFinalDischargeCNT.cnt";
	if (frmName == "patientLeaveBean")
		cntVal = "PatientLeaveCNT.cnt";
	if (frmName == "patientMoveTransBean")
		cntVal = "PatientTransferTransCNT.cnt";
	if (frmName == "patientLeaveApprovalTransBean")
		cntVal = "PatientLeaveApprovalTransCNT.cnt";
	if (frmName == "patientLeaveRequestTransBean")
		cntVal = "PatientLeaveRequestTransCNT.cnt";
	if (frmName == "patientLeaveJoinRecordTransBean")
		cntVal = "PatientLeaveJoinRecordTransCNT.cnt";
	if (frmName == "dischargecancelTransBean")
		cntVal = "DischargeCancelTransCNT.cnt";
	document.forms[0].cnt.value = cntVal;
}

function hlpOnLoad() {

	var cnt = document.forms[0].cnt.value;
	var frmName = document.forms[0].name;
	if (document.forms[0].strCrNo.value > 0) {
		var o1 = document.getElementById("admDtlTld");
		if ((o1.innerHTML).length > 9) {
			document.getElementById("disBnR").style.display = "block";
			document.getElementById("patTldglbdiv").style.display = "block";
			document.getElementById("admDtlTldglbdiv").style.display = "block";
			document.getElementById("patDtlTld").style.display = "";
			document.getElementById("admDtlTld").style.display = "block";
			document.getElementById("id1").style.display = "block";
			document.getElementById("transChng").style.display = "block";
			var currDtUtWrRmBd = document.forms[0].curDtUtWrRmBd.value;
			var mode = "transOf";
			var url = cnt + "?hmode=" + mode + "&modName=" + 1 + "&currDtl="
					+ currDtUtWrRmBd;
			ajaxFunction(url, "111");
		} else {
			if(document.getElementsByName("strErrMsgString")[0].value=="")
				document.forms[0].strErrMsgString.value = document.forms[0].strGlbErrMsgTLD.value;
			document.getElementById("patTldglbdiv").style.display = "none";
			document.getElementById("admDtlTldglbdiv").style.display = "none";
			document.getElementById("patDtlTld").style.display = "none";
			document.getElementById("id1").style.display = "none";
			document.getElementById("transChng").style.display = "none";
			document.getElementById("admDtlTld").style.display = "none";
			document.getElementById("disBnR").style.display = "none";
			if(document.getElementsByName("strErrMsgString")[0].value=="")
				document.getElementsByName("strErrMsgString")[0].value = "Invalid CR No/Data Not Found";
			document.getElementById("errMsg").style.display = "block";
			document.forms[0].hmode.value = "CANCEL";
			document.forms[0].strCrNo.value = "";
			//document.forms[0].submit();
		}
	}
}

function hlpOnLoadIPD() {

	// combo Persistence is not working so alternative of that is applied here
	// (in case of ipd desk only...)

	document.getElementsByName("combo")[0].value = document
			.getElementsByName("curDept_Unt_RomCode")[0].value.split("^")[0];
	document.getElementsByName("combo")[1].value = document
			.getElementsByName("curDept_Unt_RomCode")[0].value.split("^")[1];
	document.getElementsByName("combo")[3].value = document
			.getElementsByName("curDept_Unt_RomCode")[0].value.split("^")[2];
	document.getElementsByName("combo")[2].value = document
			.getElementsByName("curWrdBedCode")[0].value.split("^")[0];

	var cnt = document.forms[0].cnt.value;
	var frmName = document.forms[0].name;
	if (document.forms[0].strCrNo.value > 0) {
		var o1 = document.getElementById("admDtlTld");
		if ((o1.innerHTML).length > 9) {
			document.getElementById("disBnR").style.display = "block";
			document.getElementById("patTldglbdiv").style.display = "block";
			document.getElementById("admDtlTldglbdiv").style.display = "block";
			document.getElementById("patDtlTld").style.display = "";
			document.getElementById("admDtlTld").style.display = "block";
			document.getElementById("id1").style.display = "block";
			document.getElementById("transChng").style.display = "block";
			var currDtUtWrRmBd = document.forms[0].curDtUtWrRmBd.value;
			var mode = "transOfIPD";
			var url = "/HBIMS/ipd/transactions/PatientTransferTransCNT.cnt?hmode="
					+ mode + "&modName=" + 1 + "&currDtl=" + currDtUtWrRmBd;
			ajaxFunction(url, "111");
		} else {
			if(document.getElementsByName("strErrMsgString")[0].value=="")
				document.forms[0].strErrMsgString.value = document.forms[0].strGlbErrMsgTLD.value;
			document.getElementById("patTldglbdiv").style.display = "none";
			document.getElementById("admDtlTldglbdiv").style.display = "none";
			document.getElementById("patDtlTld").style.display = "none";
			document.getElementById("id1").style.display = "none";
			document.getElementById("transChng").style.display = "none";
			document.getElementById("admDtlTld").style.display = "none";
			document.getElementById("disBnR").style.display = "none";
			if(document.getElementsByName("strErrMsgString")[0].value=="")
				document.getElementsByName("strErrMsgString")[0].value = "Invalid CR No/Data Not Found";
			document.getElementById("errMsg").style.display = "block";
			document.forms[0].hmode.value = "CANCEL";
			document.forms[0].strCrNo.value = "";
			//document.forms[0].submit();
		}
	}
}

function transferOf(obj) {
	try {
		// if(document.getElementsByName("strTransferUnit")[0].value==1)
		if (document.getElementsByName("strTransferUnit")[0].checked)
			document.getElementById("divTrasTypeDtl").style.display = "block";
		else
			document.getElementById("divTrasTypeDtl").style.display = "none";
	} catch (_Err) {
	}
	try {
		if (document.forms[0].strCrNo.value > 0) {
			if (document.getElementById("id1").style.display == "block") {
				if (confirm("Proceed Without Saving Current Data?")) {
					var currDtUtWrRmBd = document.forms[0].curDtUtWrRmBd.value;
					var mode = "transOf";
					// strTransferUnit is changed from combo to radio on
					// 25-Apr-2011 by pragya

					// var
					// url=document.forms[0].cnt.value+"?hmode="+mode+"&modName="+obj[obj.selectedIndex].value+"&currDtl="+currDtUtWrRmBd;
					var deptCode = document.forms[0].strCurrentDeptUnitRoom.value
							.split('^')[0];
					var wardCode = document.forms[0].curWrdBedCode.value
							.split('^')[0];
					var unitCode = document.forms[0].strCurrentDeptUnitRoom.value
							.split('^')[1];
					var url = document.forms[0].cnt.value + "?hmode=" + mode
							+ "&modName=" + obj.value + "&currDtl="
							+ currDtUtWrRmBd + "&deptCode=" + deptCode
							+ "&wardCode=" + wardCode + "&unitCode=" + unitCode;
					ajaxFunction(url, "111");
					return true;
				} else
					return false;
			} else {
				alert("Press GO to submit!!!");
				// document.forms[0].strTransferUnit.value="1";
				document.forms[0].strTransferUnit[0].checked = true;
			}
		} else {
			alert("Enter CR No. first!!!");
			// document.forms[0].strTransferUnit.value="1";
			document.forms[0].strTransferUnit[0].checked = true;
			return false;
		}
	} catch (e) {
		alert("Error :" + e);
	}
}

/*
 * In Case Of IPD is Called Works for IPD Desk Only
 */
function transferOfIPD(obj) {
	try {
		// if(document.getElementsByName("strTransferUnit")[0].value==1)
		if (document.getElementsByName("strTransferUnit")[0].checked)
			document.getElementById("divTrasTypeDtl").style.display = "block";
		else
			document.getElementById("divTrasTypeDtl").style.display = "none";
	} catch (_Err) {
	}
	try {
		if (document.forms[0].strCrNo.value > 0) {
			if (document.getElementById("id1").style.display == "block") {
				if (confirm("Proceed Without Saving Current Data?")) {
					var currDtUtWrRmBd = document.forms[0].curDtUtWrRmBd.value;
					var mode = "transOfIPD";
					// strTransferUnit is changed from combo to radio on
					// 25-Apr-2011 by pragya

					// var
					// url=document.forms[0].cnt.value+"?hmode="+mode+"&modName="+obj[obj.selectedIndex].value+"&currDtl="+currDtUtWrRmBd;
					var deptCode = document.forms[0].strCurrentDeptUnitRoom.value
							.split('^')[0];
					var wardCode = document.forms[0].curWrdBedCode.value
							.split('^')[0];
					var unitCode = document.forms[0].strCurrentDeptUnitRoom.value
							.split('^')[1];
					var url = "/HBIMS/ipd/transactions/PatientTransferTransCNT.cnt?hmode="
							+ mode
							+ "&modName="
							+ obj.value
							+ "&currDtl="
							+ currDtUtWrRmBd
							+ "&deptCode="
							+ deptCode
							+ "&wardCode=" + wardCode + "&unitCode=" + unitCode;
					ajaxFunction(url, "7");
					return true;
				} else
					return false;
			} else {
				alert("Press GO to submit!!!");
				// document.forms[0].strTransferUnit.value="1";
				document.forms[0].strTransferUnit[0].checked = true;
			}
		} else {
			alert("Enter CR No. first!!!");
			// document.forms[0].strTransferUnit.value="1";
			document.forms[0].strTransferUnit[0].checked = true;
			return false;
		}
	} catch (e) {
		alert("Error :" + e);
	}
}

function hideDetails() {
	document.getElementById("minusonLineId").style.display = "none";
	document.getElementById("plusonLineId").style.display = "block";
	document.getElementById("patDtlTld").style.display = "none";
}
function showDetails() {
	document.getElementById("plusonLineId").style.display = "none";
	document.getElementById("minusonLineId").style.display = "block";
	document.getElementById("patDtlTld").style.display = "block";
}
function hideDetails1() {
	document.getElementById("minusonLineId1").style.display = "none";
	document.getElementById("plusonLineId1").style.display = "block";
	document.getElementById("admDtlTld").style.display = "none";
}
function showDetails1() {
	document.getElementById("plusonLineId1").style.display = "none";
	document.getElementById("minusonLineId1").style.display = "block";
	document.getElementById("admDtlTld").style.display = "block";
}
function hideDetails2() {
	document.getElementById("minusonLineId2").style.display = "none";
	document.getElementById("plusonLineId2").style.display = "block";
	document.getElementById("LeaveId").style.display = "none";
	document.getElementById("disBnR").style.display = "none";
}
function showDetails2() {
	document.getElementById("plusonLineId2").style.display = "none";
	document.getElementById("minusonLineId2").style.display = "block";
	document.getElementById("LeaveId").style.display = "block";
	document.getElementById("disBnR").style.display = "block";
}
function sharableChkBox()
{	
var shr_chk=document.getElementById("sharableChkid");
	if(shr_chk.checked ==true){
		shr_chk.value="1";
	}
	else{
		shr_chk.value="0";
	}
		funbed();

}

/*******************************************************************************
 * loading please wait* function
 * adt_create_loading_msg(){if(document.forms[0].strCrNo.value>0){var qh=80;var
 * qw=300;var dh=0;var
 * dw=0;if(window.innerHeight){dh=window.innerHeight;dw=window.innerWidth;}else
 * {dh=document.documentElement.clientHeight;dw=document.documentElement.clientWidth;}var
 * tpos=parseInt((dh-qh)/2);var lpos=parseInt((dw-qw)/2);var wt='<div
 * id="qmvi_loading_div"
 * style="top:'+tpos+'px;left:'+lpos+'px;height:'+qh+'px;width:'+qw+'px;position:absolute;text-align:center;font-family:Arial;text-decoration:none;font-weight:normal;font-size:13px;color:#00224A;background-color:#ffffff;border-width:1px;border-color:#828EA2;border-style:solid;">';wt+='<div
 * style="padding:20px;"><img src="../../hisglobal/images/loading.gif" width=16
 * height=16><br>
 * <br>
 * Fetching Details.Please Wait!!</div>';wt+='</div>';document.getElementById("normalMsg").innerHTML=wt;}}
 ******************************************************************************/
