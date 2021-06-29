var child = null;
var popIndex = 0;
var gblCntrlObj = null;
/*
 * This function is used perform client side validation before insertion
 */
function validate1NA() {

	// if(!confirm("Do You Want to Save the Data."))
	// /return false;
	var flag = false;
	var cboCountry = document.getElementsByName("strCountry")[0];
	document.getElementById("savebutton").setAttribute("data-target", "#validateModal"); 
	var hisValidator = new HISValidator("paientAdmissionTransBean");
	hisValidator.addValidation("strCrNo", "req", "CR No.  is a Mandatory Field");
	// alert("inside patient admission");
	/*
	 * if((document.forms[0].strFatherName.value=="")
	 * ||(document.forms[0].strFatherName.value==null))
	 * if((document.forms[0].strSpouseName.value=="")
	 * ||(document.forms[0].strSpouseName.value==null))
	 * hisValidator.addValidation("strFatherName", "req", "Father/Spouse Name is
	 * a Mandatory Field" );
	 */

	// hisValidator.addValidation("strReligion","dontselect=0","Please select
	// Religion.");
	// hisValidator.addValidation("strPatientCaste", "dontselect=0", "Patient
	// Caste is a Mandatory Field" );
	// alert(document.forms[0].strPatIsUnknown.value);
	if (document.forms[0].strPatIsUnknown.value == "0"
			|| document.forms[0].strPatIsUnknown.value == ""
			|| document.forms[0].strPatIsUnknown.value == " "
			|| document.forms[0].strPatIsUnknown.value == null) {
		hisValidator.addValidation("strStreet", "req",
				"Street/Mohallah Name  is a Mandatory Field");
		hisValidator.addValidation("strCountry", "dontselect=0",
				"Country is a Mandatory Field");
		// hisValidator.addValidation("strCity", "req", "City is a Mandatory
		// Field" );
		// hisValidator.addValidation("strMaritalStatus", "dontselect=0",
		// "Marital Status is a Mandatory Field" );
		if (cboCountry.value == "IND") {
			hisValidator.addValidation("strState", "dontselect=0",
					"State is a Mandatory Field");
			hisValidator.addValidation("strDistrictCode", "dontselect=0",
			"District is a Mandatory Field");
		}
		if (cboCountry.value != "IND") {
			hisValidator.addValidation("strStateName", "req",
					"State is a Mandatory Field");
			hisValidator.addValidation("strDistrictName", "req",
			"District is a Mandatory Field");
			
			
		}

		// hisValidator.addValidation("strDistrict", "req", "District is a
		// Mandatory Field" );
		hisValidator.addValidation("strOccOffName", "maxlen=70",
				"Office Name Cannot be More than 70 Characters");
		hisValidator.addValidation("strSpouseName", "maxlen=30",
				"Spouse Name Cannot be More than 30 Characters");
		// hisValidator.addValidation("strPinCode", "maxlen=6","Pin Code Cannot
		// be More than 6 digits");
		hisValidator.addValidation("strMobileNo", "maxlen=10",
				"Mobile No Cannot be More than 10 digits");
		hisValidator.addValidation("strMobileNo", "req",
				"Mobile No is a Mandatory Field");

		// hisValidator.addValidation("strCityLocation","req","Location is a
		// Mandatory Field");
		hisValidator.addValidation("strCity", "req",
				"City is a Mandatory Field");
		hisValidator.addValidation("strPhoneNo", "maxlen=13",
				"Phone No Cannot be More than 13 digits");
		if (document.getElementsByName("strPhoneNo")[0].value != "")
			hisValidator.addValidation("strPhoneNo", "minlen=8",
					"Phone No Cannot be Less than 8 digits");
		hisValidator.addValidation("strReliefFund", "dontselect=0",
				"Relief Fund is a Mandatory Field");
		if (document.getElementsByName("strMobileNo")[0].value != "") {
			if (document.getElementsByName("strCountry")[0].value == "IND"
					&& !(document.getElementsByName("strMobileNo")[0].value
							.startsWith("6")
							|| document.getElementsByName("strMobileNo")[0].value
									.startsWith("7")
							|| document.getElementsByName("strMobileNo")[0].value
									.startsWith("8") || document
							.getElementsByName("strMobileNo")[0].value
							.startsWith("9"))) {
				alert("Mobile Number must start with 6,7,8 or 9");
				return false;
			}
		}

		if (document.getElementsByName("strFirstPersonName")[0].value == ""
				&& document.getElementsByName("strSecondPersonName")[0].value != "") {
			alert("You cannot enter Second person details without entering first person details!!");
			return false;
		}

		if (document.getElementsByName("strFirstPersonName")[0].value != "") {
			hisValidator.addValidation("strFirstPersonRelationCode",
					"dontselect=0", "First Person Relation Is Mandatory.");
			//hisValidator.addValidation("strEmgAddress1", "req","First Person Address Is Mandatory");
			hisValidator.addValidation("strFirstpersonphone", "req",
					"First Person Phone Is Mandatory");
			hisValidator.addValidation("strFirstpersonphone", "maxlen=13",
					"First Person Phone No Cannot be More than 13 digits");
			hisValidator.addValidation("strFirstpersonphone", "minlen=8",
					"First Person Phone No Cannot be Less than 8 digits");
		}
		if (document.getElementsByName("strSecondPersonName")[0].value != "") {
			hisValidator.addValidation("strSecondPersonRelationCode",
					"dontselect=0", "Second Person Relation Is Mandatory.");
			//hisValidator.addValidation("strEmgAddress2", "req","Second Person Address Is Mandatory");
			hisValidator.addValidation("strSecondpersonphone", "req",
					"Second person Phone Is Madatory");
			hisValidator.addValidation("strSecondpersonphone", "maxlen=13",
					"Second person Phone No Cannot be More than 13 digits");
			hisValidator.addValidation("strSecondpersonphone", "minlen=8",
					"Second person Phone No Cannot be Less than 8 digits");
		}
	}

	/*
	 * if(document.forms[0].strFatherNameMandatoryFlag.value==1){
	 * //hisValidator.addValidation("strFatherName", "req", "Father Name/Spouse
	 * Name is a Mandatory Field" );
	 * if((document.forms[0].strFatherName.value=="" &&
	 * !document.forms[0].strSpouseName.value=="") ||
	 * (!document.forms[0].strFatherName.value=="" &&
	 * document.forms[0].strSpouseName.value=="") ||
	 * (!document.forms[0].strFatherName.value=="" &&
	 * !document.forms[0].strSpouseName.value=="")) { //alert("Father/Spouse
	 * Name is Mandatory Field"); } else { alert("Father/Spouse Name is
	 * Mandatory Field"); document.forms[0].strFatherName.focus(); return; } }
	 */
	if (document.getElementsByName("strOccIsDept")[0] != undefined) {
		if (document.getElementsByName("strOccIsDept")[0].checked) {
			// hisValidator.addValidation("strOccRelation","dontselect=0","Please
			// Select Relation in Patient Occupation Detail");
			// hisValidator.addValidation("strOccEmpName", "req", "Dependent On
			// is a Mandatory Field in Patient Occupation Detail" );
		}
	}

	hisValidator.addValidation("strTreatmentCategoryCode", "req",
			"Please Enter Patient Category");
	// hisValidator.addValidation("strOccBasic", "req", "Please Enter Basic
	// Salary in Patient Occupation Detail" );
	// hisValidator.addValidation("strOccCity", "req", "Please Enter City in
	// Patient Occupation Detail" );
	// hisValidator.addValidation("strOccState","dontselect=0","Please select
	// State in Patient Occupation Detail");
	hisValidator.addValidation("strRemarks", "maxlen=200",
			"Remarks Cannot be More than 200 Characters");
	// hisValidator.addValidation("strAdmissionType", "dontselect=0", "Admission
	// Type is a Mandatory Field" );
	// hisValidator.addValidation("strReliefFund", "dontselect=0", "Relief Fund
	// is a Mandatory Field" );
	if (document.forms[0].strIsAdmissionOnline.value == "2") {
		hisValidator.addValidation("strDeptCode", "dontselect=0",
				"Please Select Department");
		if (document.getElementsByName("strHiddenUnit")[0].value == "1") {
			hisValidator.addValidation("strDeptUnitCode", "dontselect=0",
					"Please Select Unit");
		}
		hisValidator.addValidation("strConsultantCode", "dontselect=0",
				"Please Select Consultant");
		hisValidator.addValidation("strWardCode", "dontselect=0",
				"Please Select Ward");
		/*
		 * if(document.getElementsByName("strHiddenRoom")[0].value=="1"){
		 * hisValidator.addValidation("strRoomCode","dontselect=0","Please
		 * Select Room"); document.forms[0].strRoom.value =
		 * document.forms[0].strRoomCode[document.forms[0].strRoomCode.selectedIndex].text; }
		 */
		document.forms[0].strDepartmentName.value = document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].text;
		var consultant = document.forms[0].strConsultantCode[document.forms[0].strConsultantCode.selectedIndex].text;

		document.forms[0].strConsultantName.value = consultant.split('-')[0];
		if (document.getElementsByName("strHiddenUnit")[0].value == "1")
			document.forms[0].strDeptUnitName.value = document.forms[0].strDeptUnitCode[document.forms[0].strDeptUnitCode.selectedIndex].text;
		document.forms[0].strWardName.value = document.forms[0].strWardCode[document.forms[0].strWardCode.selectedIndex].text;
		document.forms[0].strWard.value = document.forms[0].strWardCode[document.forms[0].strWardCode.selectedIndex].text;
		if (document.getElementsByName("strRoomCode")[0].value == "1")
			document.forms[0].strDeptUnitName.value = document.forms[0].strDeptUnitCode[document.forms[0].strDeptUnitCode.selectedIndex].text;

	}
	// alert(document.forms[0].strWardCode.value.split('$')[2]);
	if (document.forms[0].strWardCode.value.split('$')[2] == "1"
			&& document.forms[0].strMsAppStatus.value != "2") {
		alert("Private Ward Patients cannot be admitted without Ms Approval!!");
		return false;
	}
	/*
	 * if((document.forms[0].strWardCode.value.split('$')[3]==document.forms[0].strIcuWardType.value ||
	 * document.forms[0].strWardCode.value.split('$')[3]==document.forms[0].strPvtWardType.value) &&
	 * document.forms[0].billcount.value==0) { alert("ICU and Private Ward Type
	 * Patients cannot be admitted without Billing!!"); return false; }
	 */
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	
	if (retVal) {
		preview();	
	}
		
}

function submit(){
	var hisValidator = new HISValidator("paientAdmissionTransBean");
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	//var retVal=true;

	if (retVal) {

			document.forms[0].hmode.value = "INSERT";
			//document.getElementById("saveid").style.display = "none";
			document.forms[0].submit();
		}

	 else {

		return false;

	}

}
/*
 * 
 */
function goRetFuncNA(obj) {
	var flag = validateData(obj, 5);
	if (flag) {
		if (obj.keyCode == 13) {

			var hisValidator = new HISValidator("paientAdmissionTransBean");
			hisValidator.addValidation("strCrNo", "req",
					"CR No. is a Mandatory Field");
			hisValidator.addValidation("strCrNo", "minlen="
					+ document.forms[0].strCrNo.maxLength, "CR No. must be "
					+ document.forms[0].strCrNo.maxLength + " Digits");
			var retVal = hisValidator.validate();
			if (retVal) {
				document.forms[0].hmode.value = "GOPatient";
				document.forms[0].submit();
			} else {

				return false;
			}
		}

	} else {
		return false;
	}
}
function goFuncNA() {
	/*
	 * var validateModal=document.getElementById("validateModal"); var
	 * crvalidate=document.getElementById("crvalidate")
	 * validateModal.classList.remove("modal"); crvalidate.style.display="none";
	 * 
	 * var hisValidator = new HISValidator("paientAdmissionTransBean");
	 * hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory
	 * Field" );
	 * hisValidator.addValidation("strCrNo","minlen="+document.forms[0].strCrNo.maxLength,"CR
	 * No. must be "+document.forms[0].strCrNo.maxLength+" Digits" ); var retVal =
	 * hisValidator.validate();
	 * 
	 * 
	 * if(document.forms[0].strCrNo.value.length<15) retVal=false; else
	 * retVal=true;
	 * 
	 * document.forms[0].strSaveFlag.value="";
	 * document.forms[0].strPatientCrNo.value=""; if(retVal) {
	 * document.forms[0].hmode.value="GOPatient"; document.forms[0].submit(); }
	 * else { crvalidate.style.display=""; validateModal.classList.add("modal");
	 * //document.getElementById("warn").innerHTML="<font color='red'>CR No. is
	 * a Mandatory Field</font>"; document.getElementById("warn").innerHTML="CR
	 * No. is a Mandatory Field";
	 * document.getElementById("len").innerHTML="[Current
	 * length="+document.forms[0].strCrNo.value.length+"]"; return false; }
	 */

	var retVal = validateModalAlert(document.forms[0].strCrNo, "15",
			"CR NO. is Mandatory Field", "1");

	if (retVal) {
		document.forms[0].hmode.value = "GOPatient";
		document.forms[0].submit();
	} else
		return false;
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
	// if(temp3[1]=="Y" ||temp3[1]=="y" ||temp3[1]=="M" ||temp3[1]=="m"
	// ||temp3[1]=="D" ||temp3[1]=="d"||temp3[1]=="W" ||temp3[1]=="w"
	// ||temp3[1]=="H"||temp3[1]=="h")
	document.forms[0].strAge.value = temp3[0];
	/*
	 * else document.forms[0].strAge.value=temp3[0]+temp3[1];
	 */
	if (document.forms[0].strAge.value <= 0)
		document.forms[0].strAge.value = 1;
	document.forms[0].strAgeUnit.value = strUnitCode;
	document.forms[0].strSexCode.value = sexCode;

}
function bedDetails() {
	
	if(document.getElementsByName("strWardCode")[0].value == "0") 
	{
		swal({ 
			  title: "",
			   text: "Ward Not Selected!",
			    type: "error" 
			  },
			  function(){
				 $('#myModal').modal('hide');
				  //$("#myModal .close").click();
				  //$('body').removeClass('modal-open');
				  $('.modal-backdrop').remove();
				  //$('.modal-backdrop').hide();
			});
		
		/*Swal.fire({
			  type: 'error',
			  title: '',
			  text: 'Ward Not Selected!',
			  footer: ''
			},
			function(){   
				//document.getElementById("modal-container").style.display="none";
				alert('here');
				$('#myModal').modal('hide');
				});*/
		 return false;
	}else{
		// alert("in");

		/*
		 * if(document.getElementsByName("strWardCode")[0].value == "0") {
		 * //alert("Please Select Ward!!");
		 * $('.modal-backdrop').css('display','none');
		 * //$('.modal-container').css('display','none');
		 * //$('#modalSpaceId').unload(); return false; } else{
		 */
		// $('.modal-backdrop').show();
		 //$("#myModal").attr("data-toggle").modal();
		$('.modal-container').css('display', 'block');

		// var mode="BEDSTATUS1";
		var mode = "BEDSTATUSPATADM";
		// WrdRoomBedtypUnt_code=window.opener.document.forms[0].strWardCode.value+"^"+document.forms[0].strRoom.value+"^0^"+document.forms[0].strUnit.value+"^"+document.forms[0].strchk.value;
		var WrdRoomBedtypUnt_code = document.forms[0].strWardCode.value.split('$')[0]
				+ ",0,0,0,0";

		// alert(WrdRoomBedtypUnt_code);
		// alert(WrdRoomBedtypUnt_code);
		url = "IpdCNT.cnt?hmode=" + mode + "&modPopUp=" + WrdRoomBedtypUnt_code;
		
		$('#modalSpaceId').load(url);

		//$('#modalSpaceId').load(createFHashAjaxQuery(url));

		// $('#modalSpaceId').load("http://vnexpress.net");
		// alert(WrdRoomBedtypUnt_code);
		// location.reload();

		/*
		 * $('.modal-container').load(createFHashAjaxQuery(url),function(result) {
		 * $('#myModal').modal({show:true}); });
		 */

		// window.open('http://kanishkkunal.in','popup','width=600,height=600');
		// openPopUp(url,"400","200","1");
		// openModalPopUp(url,"600","400","1");
	}
		 
	
}
/*
 * This function is used to forward control to bed popup window
 */
function bedDetails_Old() {

	getAgeSex();
	var hmode = "BEDSTATUS";
	var deptName = "";
	document.forms[0].strAge.value = document.forms[0].strAge.value.replace(
			/[^0-9]/g, "");
	// alert("Ms approval flag"+document.forms[0].strMsApprovalFlag.value);
	// alert("Ms status"+document.forms[0].strMsApprovalStatus.value);
	if (document.forms[0].strIsAdmissionOnline.value == "2") {
		var hisValidator = new HISValidator("paientAdmissionTransBean");
		hisValidator.addValidation("strDeptCode", "dontselect=0",
				"Please Select Department");
		if (document.getElementsByName("strHiddenUnit")[0].value == "1") {
			hisValidator.addValidation("strDeptUnitCode", "dontselect=0",
					"Please Select Unit");
		}
		hisValidator.addValidation("strWardCode", "dontselect=0",
				"Please Select Ward");
		if (document.forms[0].strDeptUnitCode.value == "")
			document.forms[0].strDeptUnitCode.value = "0";
		var retVal = hisValidator.validate();
		hisValidator.clearAllValidations();
		if (retVal) {
			deptName = document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].text;
			var url = 'PatientAdmissionTransBSCNT.cnt?hmode=' + hmode
					+ '&wardTypeCode='
					+ document.forms[0].strWardTypeCode.value
					+ '&roomTypeCode='
					+ document.forms[0].strRoomTypeCode.value + '&strWardCode='
					+ document.forms[0].strWardCode.value.split('$')[0]
					+ '&bedTypeCode=' + document.forms[0].strBedTypeCode.value
					+ '&deptCode=' + document.forms[0].strDeptCode.value
					+ '&strCrNo=' + document.forms[0].strCrNo.value
					+ '&deptUnitCode='
					+ document.forms[0].strDeptUnitCode.value
					+ '&msApprovalStatus='
					+ document.forms[0].strMsApprovalStatus.value
					+ '&msApprovalFlag='
					+ document.forms[0].strMsApprovalFlag.value + '&wardName='
					+ document.forms[0].strWardName.value + '&roomCode='
					+ document.forms[0].strRoomCode.value + '&deptName='
					+ deptName + '&treatmentCategCode='
					+ document.forms[0].strTreatmentCategoryCode.value
					+ '&ageCode=' + document.forms[0].strAgeUnit.value
					+ '&sexCode=' + document.forms[0].strSexCode.value
					+ '&age=' + document.forms[0].strAge.value;
			var featuresList = "width=380,height=390,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
			// myWindow = window.open(url,'popupWindow',featuresList);

			/*openModalPopUp(createFHashAjaxQuery(url), "600", "350", "1");*/
				openModalPopUp(url, "600", "350", "1");

			// myWindow.focus();
		}
	} else {
		// alert(document.forms[0].strPrimaryCategoryCode.value);
		deptName = document.forms[0].strDepartmentName.value;
		var url = 'PatientAdmissionTransBSCNT.cnt?hmode=' + hmode
				+ '&wardTypeCode=' + document.forms[0].strWardTypeCode.value
				+ '&roomTypeCode=' + document.forms[0].strRoomTypeCode.value
				+ '&strWardCode='
				+ document.forms[0].strWardCode.value.split('$')[0]
				+ '&bedTypeCode=' + document.forms[0].strBedTypeCode.value
				+ '&deptCode=' + document.forms[0].strDeptCode.value
				+ '&strCrNo=' + document.forms[0].strCrNo.value
				+ '&deptUnitCode=' + document.forms[0].strDeptUnitCode.value
				+ '&msApprovalStatus='
				+ document.forms[0].strMsApprovalStatus.value
				+ '&msApprovalFlag='
				+ document.forms[0].strMsApprovalFlag.value + '&wardName='
				+ document.forms[0].strWardName.value + '&roomCode='
				+ document.forms[0].strRoomCode.value + '&deptName=' + deptName
				+ '&treatmentCategCode='
				+ document.forms[0].strPrimaryCategoryCode.value + '&ageCode='
				+ document.forms[0].strAgeUnit.value + '&sexCode='
				+ document.forms[0].strSexCode.value + '&age='
				+ document.forms[0].strAge.value;
		var featuresList = "width=380,height=390,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
		// myWindow = window.open(url,'popupWindow',featuresList);
		openModalPopUp(url, "400", "350", "1");
		// myWindow.focus();
	}
}
function unitDetails() {

	if (document.forms[0].strDeptCode.value == '0') 
	{
		document.getElementById("UnitId").innerHTML = "<select name='strDeptUnitCode' class='comboNormal'><option value='0'>Select Value</option></select>";
		document.getElementById("consId").innerHTML = "<select name='strConsultantCode' class='comboNormal' onChange='wardonUnit(this);'><option value='0'>Select Value</option></select>";
		document.getElementById("WardId").innerHTML = "<select name='strWardCode' class='comboNormal' onChange='getAdmissionCharges(this)'><option value='0'>Select Value</option></select>";
		if (document.forms[0].strHiddenRoom.value == '1') 
		{
			document.getElementById("RoomId").innerHTML = "<select name='strRoomCode' class='comboNormal'><option value='0'>Select Value</option></select>";
		}
		return;
	}
	// document.getElementById("wardNameId").innerHTML="";
	// document.getElementById("roomBedId").innerHTML="";
	// document.forms[0].strWardCode.value="0";
	// document.forms[0].strRoomCode.value="0";
	getAgeSex();
	var hmode = "GETUNIT";
	var url = 'PatientAdmissionTransBSCNT.cnt?hmode=' + hmode + '&deptCode='
			+ document.forms[0].strDeptCode.value + "&strCrNo="
			+ document.forms[0].strCrNo.value + "&strDeptUnitCode="
			+ document.forms[0].strDeptUnitCode.value + '&wardTypeCode='
			+ document.forms[0].strWardTypeCode.value + '&ageCode='
			+ document.forms[0].strAgeUnit.value + '&sexCode='
			+ document.forms[0].strSexCode.value + '&age='
			+ document.forms[0].strAge.value + '&treatmentCategCode='
			+ document.forms[0].strTreatmentCategoryCode.value;
	ajaxFunction(url, "1");
}
function viewA() 
{
	/*var frameEl = window.frameElement;
	// If we're embedded, change the containing element's URL to 'http://mozilla.org/'
	if (frameEl) {
	  alert(frameEl.id)
	}*/
	
	   /* var dt = $('#Patient Admission New_iframe').contents().find('#dateTdId').text();
console.log("date:::::::::::::::::::::::::::::::::::::::::"+dt);*/

	/*alert(document.getElementById("dateTdId").value);
	document.forms[0].strAuthDate.value=document.getElementById("dateTdId").value;*/
	
	if(document.forms[0].isSingleMenu.value=="1");
			setSingleWindow();
		
			if(document.getElementById("normalMsg").innerHTML=="")
				document.getElementById("printbutton").style.display="none";	
	
	//checkMsg();
	if (document.forms[0].strCrNo.value != "") 
	{
		if (checkCrdef(document.getElementById("strCrNoId")) == false)// Complete CR No Entered 
		{
			if (document.getElementById("errID").innerHTML != "") 
			{
				document.getElementById("errID").style.display = "";
			}

			// document.forms[0].strFatherName.focus();
			// var o=document.getElementById("id4");
			// o.style.display="block";
			// var o=document.getElementById("patientDtlID");
			// o.style.display="block";
			var o1 = document.getElementById("PatientOccId");
			o1.style.display = "block";
			var obj = document.getElementById("wardDivId");
			obj.style.display = "block";
			var object = document.getElementById("emrgencyDivId");
			object.style.display = "block";
			o = document.getElementById("PatientOccDtl");
			o.style.display = "block";
			document.getElementById("csno").style.display = "block";
			// var wardUnit=document.getElementById("WardUnitDtl");
			// wardUnit.style.display="block";
			// document.getElementsByName("strOccIsDept")[1].checked="checked";
			// document.getElementById("IsDependendID").style.display="none";
			temp = document.getElementsByName("strHiddenPatDtl")[0].value
					.split('^');
			// document.getElementsByName("strOccEmpName")[0].value=temp[0];
			// document.getElementsByName("strOccRelation")[0].value="10";
			/*
			 * if(!document.getElementsByName("strOccRelation")[0].disabled)
			 * document.getElementsByName("strOccRelation")[0].disabled=true;
			 */
			// document.getElementById("des").style.display="none";
			// document.getElementById("ofc").style.display="none";
			// document.getElementById("ofcadd").style.display="none";
			// document.getElementById("ofcph").style.display="none";
			o = document.getElementById("newModificationId");
			o.style.display = "block";
			// document.getElementById("mandCRId").style.display="none";
			document.getElementById("patDtlID").style.display = "block";
			document.getElementById("patName1").innerHTML = document.forms[0].strPatName.value;
			document.getElementById("patName2").innerHTML = document.forms[0].strPatName.value;
			document.getElementById("savebutton").style.display = "block";
			document.getElementById("printbutton").style.display = "none";
			$('#nonPrintable').addClass('vpinside');

			if (document.forms[0].strNewBorn.value == "1") {
				var obj1 = document.getElementById("id5");
				obj1.style.display = "block";
				var obj2 = document.getElementById("newAddressModiId");
				obj2.style.display = "none";
			} else {
				// var obj1=document.getElementById("plusAddModiId");
				// obj1.style.display="none";
				// var obj2=document.getElementById("minusAddModiId");
				// obj2.style.display="block";
			}
			var o3 = document.getElementById("patientCrEdId");
			// var o4=document.getElementById("patientCrId1");
			// o3.style.display="none";
			// o4.innerHTML=document.forms[0].strCrNo.value;
			// o4.style.display="";
			var o5 = document.getElementById("patientCrId2");
			o5.innerHTML = document.forms[0].strCrNo.value;
			// o5.style.display="none";
			document.getElementById("goBox").style.display = 'none';
			// document.getElementById("crNoId").style.display="";
			document.forms[0].strStreet.focus();
			// document.getElementById("save").style.display="none";
			//document.getElementById("saveid").style.display = "block";
			//document.getElementById("saveid1").style.display = "none";
			document.getElementById("newBabyLink").style.display = "none";
			if (document.forms[0].strIsAdmissionOnline.value == "1") 
			{
				document.getElementById("strDeptWardChangeChkId").style.display = "block";
			}
		} 
		else// only predefined Digits in CR Field
		{
			// SetCursorToTextEnd('strCrNoId');
			SetSelectedCrNo();
			// document.forms[0].strCrNo.focus();
			var o = document.getElementById("id4");
			// o.style.display="none";
			// var o=document.getElementById("patientDtlID");
			// o.style.display="none";
			o = document.getElementById("PatientOccId");
			o.style.display = "none";
			var obj = document.getElementById("wardDivId");
			obj.style.display = "none";
			var o3 = document.getElementById("patientCrEdId");
			// var o4=document.getElementById("patientCrId");
			// o3.style.display="block";
			// o4.style.display="none";
			o4 = document.getElementById("id5");
			o4.style.display = "none";
			document.getElementById("csno").style.display = "none";
			// o4=document.getElementById("minusAddModiId");
			// o4.style.display="none";
			o4 = document.getElementById("newModificationId");
			o4.style.display = "none";
			o4 = document.getElementById("newAddressModiId");
			o4.style.display = "none";
			// document.getElementById("mandCRId").style.display="";
			document.getElementById("PatientOccDtl").style.display = "none";
			// document.getElementById("WardUnitDtl").style.display="none";
			document.getElementById("patDtlID").style.display = "none";
			// document.getElementById("crNoId").style.display="none";
			o5 = document.getElementById("DeptUnitDivId");
			o5.style.display = "none";
			document.getElementById("newBabyLink").style.display = "";
		}
	} 
	else 
	{

		// document.forms[0].strCrNo.focus();
		var o = document.getElementById("id4");
		o.style.display = "none";
		// var o=document.getElementById("patientDtlID");
		// o.style.display="none";
		o = document.getElementById("PatientOccId");
		o.style.display = "none";
		var obj = document.getElementById("wardDivId");
		obj.style.display = "none";
		var o3 = document.getElementById("patientCrEdId");
		// var o4=document.getElementById("patientCrId");
		o3.style.display = "block";
		// o4.style.display="none";
		o4 = document.getElementById("id5");
		o4.style.display = "none";
		document.getElementById("csno").style.display = "none";
		// o4=document.getElementById("minusAddModiId");
		// o4.style.display="none";
		o4 = document.getElementById("newModificationId");
		o4.style.display = "none";
		o4 = document.getElementById("newAddressModiId");
		o4.style.display = "none";
		document.getElementById("mandCRId").style.display = "";
		document.getElementById("PatientOccDtl").style.display = "none";
		// document.getElementById("WardUnitDtl").style.display="none";
		document.getElementById("patDtlID").style.display = "none";
		// document.getElementById("crNoId").style.display="none";
		o5 = document.getElementById("DeptUnitDivId");
		o5.style.display = "none";
	}
	if (document.forms[0].strPatStatusCode.value == 13) {
		// var o4=document.getElementById("plusAddModiId");
		// o4.style.display="block";
		// o4=document.getElementById("patientDtlID");
		// o4.style.display="none";
		o4 = document.getElementById("newModificationId");
		o4.style.display = "none";
		o4 = document.getElementById("newAddressModiId");
		o4.style.display = "none";
		o4 = document.getElementById("PatientOccId");
		o4.style.display = "none";
		o4 = document.getElementById("wardDivId");
		o4.style.display = "none";
		document.getElementById("DeptUnitDivId").style.display = "none";
		document.getElementById("csno").style.display = "none";

	}

/*	if (document.forms[0].strIsAdvanceAmountAtAdmissionTaken.value == "0") {
		// var o4=document.getElementById("plusAddModiId");
		// o4.style.display="block";
		// o4=document.getElementById("patientDtlID");
		// o4.style.display="none";
		o4 = document.getElementById("newModificationId");
		o4.style.display = "none";
		o4 = document.getElementById("newAddressModiId");
		o4.style.display = "none"; 
		o4 = document.getElementById("PatientOccId");
		o4.style.display = "none";
		o4 = document.getElementById("wardDivId");
		o4.style.display = "none";
		document.getElementById("csno").style.display = "none";

		document.getElementById("DeptUnitDivId").style.display = "none";
	}*/
	if (document.forms[0].strPatStatusCode.value == 11) {
		// var o4=document.getElementById("plusAddModiId");
		// o4.style.display="block";
		// o4=document.getElementById("patientDtlID");
		// o4.style.display="none";
		o4 = document.getElementById("newModificationId");
		o4.style.display = "none";
		o4 = document.getElementById("newAddressModiId");
		o4.style.display = "none";
		o4 = document.getElementById("PatientOccId");
		o4.style.display = "none";
		o4 = document.getElementById("wardDivId");
		o4.style.display = "none";
		document.getElementById("csno").style.display = "none";

		document.getElementById("DeptUnitDivId").style.display = "none";
	}
	if (document.forms[0].strAdviceStatus.value == 0
			&& document.forms[0].strIsAdmissionOnline.value == 1) {
		// var o4=document.getElementById("plusAddModiId");
		// o4.style.display="block";
		// o4=document.getElementById("patientDtlID");
		// o4.style.display="none";
		o4 = document.getElementById("newModificationId");
		o4.style.display = "none";
		o4 = document.getElementById("newAddressModiId");
		o4.style.display = "none";
		o4 = document.getElementById("PatientOccId");
		o4.style.display = "none";
		o4 = document.getElementById("wardDivId");
		o4.style.display = "none";
		document.getElementById("csno").style.display = "none";

		document.getElementById("DeptUnitDivId").style.display = "none";
		document.forms[0].strDeptWardChangeChk.value="0";
	}
}
function enableDeptWard(obj)
{
	//if(document.forms[0].strDeptWardChangeChk.checked
	//strDeptWardChangeChk
	document.forms[0].strDeptWardChangeChk.value="1";
	document.forms[0].hmode.value = "GOPatient";
	document.forms[0].submit();
}
function checkMsg() {
	var err=document.getElementById("errID");
	var nor=document.getElementById("normalMsg");
	var warn=document.getElementById("wrnID");
	if (err.innerHTML != "") {
		
		err.innerHTML="<i class='fas fa-exclamation-triangle'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Error!</strong>&nbsp;&nbsp;"+err.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
		err.style.display = "";
		
	}
	if (nor.innerHTML != "") {
		nor.innerHTML="<i class='far fa-check-circle'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Success!</strong>&nbsp;&nbsp;"+nor.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
		nor.style.display = "";
	}
	if (warn.innerHTML != "") {
		warn.innerHTML="<i class='fas fa-bell'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Warning!</strong>&nbsp;&nbsp;"+warn.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
		warn.style.display = "";
	}

}

function view1() {

	var o1 = document.getElementById("id1");
	var o2 = document.getElementById("id2");
	var o3 = document.getElementById("PatientOccDtl");
	// var o4=document.getElementById("WardUnitDtl");
	o1.style.display = "none";
	o2.style.display = "block";
	o3.style.display = "block";

}
function view2() {

	var o1 = document.getElementById("id1");
	var o2 = document.getElementById("id2");
	var o3 = document.getElementById("PatientOccDtl");
	o1.style.display = "block";
	o2.style.display = "none";
	o3.style.display = "none";
	var obj = document.getElementById("wardDivId");
	obj.style.display = "block";
	// var o4=document.getElementById("WardUnitDtl");
	// o4.style.display="block";
}
function view3() {

	// var o1=document.getElementById("plusAddModiId");
	// var o2=document.getElementById("minusAddModiId");
	var o3 = document.getElementById("newAddressModiId");
	// o1.style.display="none";
	o2.style.display = "block";
	// o3.style.display="block";
	var o4 = document.getElementById("DeptUnitDivId");
	o4.style.display = "block";

}
function view4() {

	// var o1=document.getElementById("plusAddModiId");
	// var o2=document.getElementById("minusAddModiId");
	var o3 = document.getElementById("newAddressModiId");
	// o1.style.display="block";
	// o2.style.display="none";
	o3.style.display = "none";
	var obj = document.getElementById("wardDivId");
	obj.style.display = "block";
	var o4 = document.getElementById("DeptUnitDivId");
	o4.style.display = "block";

}
function viewX() {
	document.getElementById("plusPatDtl").style.display = "none";
	document.getElementById("minusPatDtl").style.display = "none";
	document.getElementById("id4").style.display = "";
	document.getElementById("patientCrId1").style.display = "none";
	document.getElementById("patientCrId2").style.display = "";
}
function viewY() {
	document.getElementById("plusPatDtl").style.display = "none";
	document.getElementById("minusPatDtl").style.display = "none";
	document.getElementById("id4").style.display = "none";
	document.getElementById("patientCrId1").style.display = "";
	document.getElementById("patientCrId2").style.display = "none";
}
function clearRecord() {
	/*
	 * document.forms[0].strCrNo.value="";
	 * document.getElementById("errID").innerHTML="";
	 * document.getElementById("normalMsg").innerHTML="";
	 * document.getElementById("wrnID").innerHTML=""; viewA();
	 */
	document.forms[0].hmode.value = "CLEAR";
	document.forms[0].strSaveFlag.value = "";
	document.forms[0].strPatientCrNo.value = "";
	document.forms[0].strCaseSheetNo.value = "";
	document.forms[0].submit();
}
function cancelPage() {
	document.forms[0].hmode.value = "INITIALPAGE";
	document.forms[0].submit();
}
function getAjaxResponse(res, mode) 
{
	if (mode == '1') 
	{
		var objEle = document.getElementById("UnitId");
		objEle.innerHTML = "<select name='strDeptUnitCode' class='browser-default custom-select' onChange='changeUnit();'>"+ res.split('^')[0] + "</select>";
		document.getElementById("WardId").innerHTML = "<select name='strWardCode' class='browser-default custom-select' onChange='getBedFrmNursingDesk();'>"+ res.split('^')[1] + "</select>";
		if (document.forms[0].strHiddenRoom.value == '1') 
		{
			document.getElementById("RoomId").innerHTML = "<select name='strRoomCode' class='browser-default custom-select'><option value='0'>Select Value</option></select>";
		}
	}
	if (mode == '2') 
	{
		var objEle = document.getElementById("treatCat");
		objEle.innerHTML = "<select name='strTreatmentCategoryCode' class='browser-default custom-select' onChange='wardDetails(this)'>"+ res.split('^')[0] + "</select>";
		document.getElementById("consId").innerHTML = "<select name='strConsultantCode' class='browser-default custom-select' onChange='wardonUnit(this);'>"
				+ res.split('^')[1] + "</select>";
		document.getElementById("WardId").innerHTML = "<select name='strWardCode' class='browser-default custom-select' onChange='getBedFrmNursingDesk();'>"+ res.split('^')[2] + "</select>";
		if (document.forms[0].strHiddenRoom.value == '1') 
		{
			document.getElementById("RoomId").innerHTML = "<select name='strRoomCode' class='browser-default custom-select'><option value='0'>Select Value</option></select>";
		}
	}
	if (mode == '3') 
	{
		var objEle = document.getElementById("WardId");
		objEle.innerHTML = "<select name='strWardCode' class='browser-default custom-select' onChange='getBedFrmNursingDesk()'>"+ res + "</select>";
	}
	if (mode == '4') 
	{
		var objEle = document.getElementById("RoomId");
		objEle.innerHTML = "<select name='strRoomCode' class='comboNormal' >"
				+ res + "</select>";
	}
	if (mode == '5') 
	{
		document.getElementById("consId").innerHTML = "<select name='strConsultantCode' class='browser-default custom-select' onChange='wardonUnit(this);'>"
				+ res.split('^')[0] + "</select>";
		document.getElementById("WardId").innerHTML = "<select name='strWardCode' class='browser-default custom-select' onChange='getBedFrmNursingDesk();'>"+ res.split('^')[1] + "</select>";

	}

	if (mode == '6') 
	{
		//document.getElementsByName("strAdmcharge")[0].value = res;
		//alert(res)
		document.getElementsByName("strAdmissionChargeValue")[0].value = res.split('$')[0];
		if (document.getElementById("admCharges") != undefined) 
		{
			document.getElementById("admCharges").innerHTML= res.split('$')[0];	
		}
			
		document.getElementsByName("strAdvanceAmountVal")[0].value = res.split('$')[1];
		if (document.getElementById("advanceAmount") != undefined) 
		{
			document.getElementById("advanceAmount").innerHTML= res.split('$')[1];
		}		
	}
	if (mode == '7') 
	{
		var objEle = document.getElementById("strDistrictCode");
		objEle.innerHTML = "<select name='strDistrictCode' class='browser-default custom-select' onclick='onchangeDistrict();' >"
				+ res + "</select>";
	}	
	if (mode == '8') 
	{
		var objEle = document.getElementById("WardId");
		// alert(res);
		// objEle.innerHTML ="<select name='strWardCode' class='comboNormal'
		// onChange='roomDetails(this);getAdmissionCharges();'>"+res+"</select>";
		//objEle.innerHTML = "<select name='strWardCode' class='browser-default custom-select' onChange='roomDetails(this);'>"+ res + "</select>";
		objEle.innerHTML = "<select name='strWardCode' class='browser-default custom-select' onChange='getBedFrmNursingDesk();'>"+ res + "</select>";
	}
	if (mode == '9') 
	{
		var objEle = document.forms[0].strPayMode;
		objEle.innerHTML = '<select class="browser-default custom-select" name="strPayMode">'+res+'</select>';
		
		getAdmissionCharges();
	}
	
	if (mode == '10') 
	{
		var objEle = document.forms[0].strBed;
		objEle.innerHTML = '<select class="browser-default custom-select" name="strBed">'+res+'</select>';
		
		getAdmissionCharges();
	}
	
}
function changeUnit() {
	// document.getElementById("wardNameId").innerHTML="";
	// document.getElementById("roomBedId").innerHTML="";
	// document.forms[0].strWardCode.value="0";
	// document.forms[0].strRoomCode.value="0";
	// document.getElementById("wardNameId").innerHTML="";
	// document.getElementById("roomBedId").innerHTML="";
	// document.forms[0].strWardCode.value="0";
	// document.forms[0].strRoomCode.value="0";
	if (document.forms[0].strDeptUnitCode.value == '0') {
		document.getElementById("consId").innerHTML = "<select name='strConsultantCode' class='browser-default custom-select' onChange='wardonUnit(this);'><option value='0'>Select Value</option></select>";
		document.getElementById("WardId").innerHTML = "<select name='strWardCode' class='browser-default custom-select' onChange='getBedFrmNursingDesk()'><option value='0'>Select Value</option></select>";
		if (document.forms[0].strHiddenRoom.value == '1') {
			document.getElementById("RoomId").innerHTML = "<select name='strRoomCode' class='browser-default custom-select'><option value='0'>Select Value</option></select>";
		}
		return;
	}
	getAgeSex();
	var hmode = "GETTREATMENTCAT";
	var url = 'PatientAdmissionTransBSCNT.cnt?hmode=' + hmode + '&deptCode='
			+ document.forms[0].strDeptCode.value + "&strCrNo="
			+ document.forms[0].strCrNo.value + "&strDeptUnitCode="
			+ document.forms[0].strDeptUnitCode.value + '&wardTypeCode='
			+ document.forms[0].strWardTypeCode.value + '&ageCode='
			+ document.forms[0].strAgeUnit.value + '&sexCode='
			+ document.forms[0].strSexCode.value + '&age='
			+ document.forms[0].strAge.value + '&treatmentCategCode='
			+ document.forms[0].strTreatmentCategoryCode.value;
	ajaxFunction(url, "2");
}
function wardDetails(obj) {
	getAgeSex();
	var hmode = "GETWARDNAME";
	var url = 'PatientAdmissionTransBSCNT.cnt?hmode=' + hmode + '&deptCode='
			+ document.forms[0].strDeptCode.value + "&strCrNo="
			+ document.forms[0].strCrNo.value + "&strDeptUnitCode="
			+ document.forms[0].strDeptUnitCode.value + '&wardTypeCode='
			+ document.forms[0].strWardTypeCode.value + '&ageCode='
			+ document.forms[0].strAgeUnit.value + '&sexCode='
			+ document.forms[0].strSexCode.value + '&age='
			+ document.forms[0].strAge.value + '&treatmentCategCode='
			+ obj.value;
	ajaxFunction(url, "3");
}

function wardonUnit(obj) {
	getAgeSex();
	var hmode = "GETWARDONUNIT";
	var unit = 0;
	if (document.forms[0].strConsultantCode.value != 0)
		unit = document.forms[0].strConsultantCode.value.split("$")[1];
	var url = 'PatientAdmissionTransBSCNT.cnt?hmode=' + hmode + '&deptCode='
			+ document.forms[0].strDeptCode.value + "&strCrNo="
			+ document.forms[0].strCrNo.value + "&strDeptUnitCode=" + unit
			+ '&wardTypeCode=' + document.forms[0].strWardTypeCode.value
			+ '&ageCode=' + document.forms[0].strAgeUnit.value + '&sexCode='
			+ document.forms[0].strSexCode.value + '&age='
			+ document.forms[0].strAge.value + '&treatmentCategCode='
			+ obj.value;
	// alert(url);
	ajaxFunction(url, "8");
}
function roomDetails(obj) {

	var e = document.getElementsByName("strWardCode")[0];
	document.getElementsByName("strWardName")[0].value = e.options[e.selectedIndex].value;
	if (parseInt(obj.value.split('$')[1]) <= 0) {
		document.getElementById("bedStatusDiv").innerHTML = "<font color='red'>Bed Status(No Vacant Beds)</font>";
		document.getElementById("bedStatusTD").style.width = '25%';
	} else {
		document.getElementById("bedStatusDiv").innerHTML = "<font color='#008000'>Bed Status</font>";
		document.getElementById("bedStatusTD").style.width = '10%';
	}

	if (obj.value.split('$')[0] == "0")
		document.getElementById("bedStatusDiv").innerHTML = "Bed Status";

	if (obj.value == '0' && document.forms[0].strHiddenRoom.value == '1') {
		document.getElementById("RoomId").innerHTML = "<select name='strRoomCode' class='browser-default custom-select'><option value='0'>Select Value</option></select>";
		return;
	}
	if (document.forms[0].strHiddenRoom.value == '1') {
		getAgeSex();
		var hmode = "GETROOM";
		var wardCode = obj.value.split('$')[0];
		var url = 'PatientAdmissionTransBSCNT.cnt?hmode=' + hmode
				+ '&deptCode=' + document.forms[0].strDeptCode.value
				+ "&strCrNo=" + document.forms[0].strCrNo.value
				+ "&strWardCode=" + wardCode + '&roomTypeCode='
				+ document.forms[0].strRoomTypeCode.value + '&wardTypeCode='
				+ document.forms[0].strWardTypeCode.value + '&ageCode='
				+ document.forms[0].strAgeUnit.value + '&sexCode='
				+ document.forms[0].strSexCode.value + '&age='
				+ document.forms[0].strAge.value + '&treatmentCategCode='
				+ document.forms[0].strTreatmentCategoryCode.value;
		ajaxFunction(url, "4");
	}

}
function checkAddress() {
	if (document.forms[0].strSameAsAdd.checked) {
		document.forms[0].strOccCity.value = document.forms[0].strCity.value;
		for (var i = 0; i < document.forms[0].strOccState.options.length; i++) {
			if (document.forms[0].strOccState.options[i].value == document.forms[0].strState.value) {
				document.forms[0].strOccState.options[i].selected = true;
			}
		}
	}
}
function ConsultantDetails() {
	//document.getElementById("bedStatusDiv").innerHTML = "Bed Status";
	if (document.forms[0].strDeptCode.value == '0') 
	{
		document.getElementById("consId").innerHTML = "<select name='strConsultantCode' class='browser-default custom-select' onChange='wardonUnit(this);'><option value='0'>Select Value</option></select>";
		document.getElementById("WardId").innerHTML = "<select name='strWardCode' class='browser-default custom-select' onChange='getAdmissionCharges(this)'><option value='0'>Select Value</option></select>";
		if (document.forms[0].strHiddenRoom.value == '1') 
		{
			document.getElementById("RoomId").innerHTML = "<select name='strRoomCode' class='browser-default custom-select'><option value='0'>Select Value</option></select>";
		}
		return;
	}
	getAgeSex();
	var hmode = "GETCONSWARD";
	var url = 'PatientAdmissionTransBSCNT.cnt?hmode=' + hmode + '&deptCode='
			+ document.forms[0].strDeptCode.value + "&strCrNo="
			+ document.forms[0].strCrNo.value + '&wardTypeCode='
			+ document.forms[0].strWardTypeCode.value + '&ageCode='
			+ document.forms[0].strAgeUnit.value + '&sexCode='
			+ document.forms[0].strSexCode.value + '&age='
			+ document.forms[0].strAge.value + '&treatmentCategCode='
			+ document.forms[0].strTreatmentCategoryCode.value;
	ajaxFunction(url, "5");
}
function openSlipPopup() {
	if (document.forms[0].strSaveFlag.value == '1'
			&& document.forms[0].strPatientCrNo.value != '') {
		// var
		// url='PatientAdmissionTransBSCNT.cnt?hmode=PRINTSLIP&strCrNo='+document.forms[0].strPatientCrNo.value+'&strAdmNo='+document.forms[0].strAdmNo.value+'&duplicateMode=0';
		var url = 'PatientAdmissionTransBSCNT.cnt?hmode=PRINTSLIP&strCrNo='
				+ document.forms[0].strPatientCrNo.value + '&strAdmNo='
				+ document.forms[0].strAdmNo.value + '&duplicateMode=0';
		// var
		// url='PatientAdmissionTransBSCNT.cnt?hmode=PRINTSLIP&strCrNo=1011100004939';
		child = window
				.open(url, 'popupWindow',
						'status=yes,scrollbars=yes,height=600,width=500,left=400,top=100');
		child.moveTo(900, 250);
		child.focus();
	}
}
function printSlip() {
	
	get_object("divBarCodeControlCrNo").innerHTML = DrawCode39Barcode(get_object("divBarCodeControlCrNo").innerHTML, 0);
	get_object("divBarCodeControlAdmNo").innerHTML = DrawCode39Barcode(get_object("divBarCodeControlAdmNo").innerHTML, 0);
	// window.print();
	// var t=setTimeout("printSlip1()",2000);
}
function printSlip1() {
	if (confirm("Whether Printed Successfully?")) {
		if (confirm("Do you want to print next page?")) {
			window.close();
			self.close();
			openSecondSlipPopup();
		} else {
			window.close();
			self.close();
		}
	} else {
		printSlip();
	}
}
function openSecondSlipPopup() {

	var url = 'PatientAdmissionTransBSCNT.cnt?hmode=PRINTSECONDSLIP';
	child = window.open(url, 'popupWindow',
			'status=yes,scrollbars=yes,height=600,width=500,left=400,top=100');
	child.moveTo(900, 250);
	child.focus();

}
function printSecondSlip() {
	window.print();
	var t = setTimeout("printSecondSlip1()", 2000);
}

function printSecondSlip1() {
	if (confirm("Whether Printed Successfully?")) {

		if (document.getElementsByName("strIsAdvanceAmountAtAdmission")[0].value == 1) {
			openThirdSlipPopup();
		} else {
			window.close();
			self.close();
		}
	} else {

		printSecondSlip();
		// openThirdSlipPopup();
	}

}
function openThirdSlipPopup() {
	var url = 'PatientAdmissionTransBSCNT.cnt?hmode=PRINTBILLSLIP';
	child = window.open(url, 'popupWindow',
			'status=yes,scrollbars=yes,height=600,width=500,left=400,top=100');
	child.moveTo(900, 250);
	child.focus();

}

function printThirdSlip() {
	window.print();
	var t = setTimeout("printThirdSlip1()", 2000);
}

function printThirdSlip1() {
	if (confirm("Whether Printed Successfully?")) {
		window.close();
		self.close();
	} else {
		printThirdSlip();
	}
}

function changeColor() {

}
function openPrintPopUp() {
	if (document.forms[0].strSaveFlag.value == '1'
			&& document.forms[0].strPatientCrNo.value != '') {
		printSlip();
		// document.forms[0].hmode.value="PRINTSLIP";
		// alert(document.forms[0].strPatientCrNo.value);
		// document.forms[0].strCrNo.value=document.forms[0].strPatientCrNo.value;
		//window.print();
		// document.forms[0].submit();
		// alert(document.forms[0].strCrNo.value);
		// alert(document.forms[0].hmode.value);

		// window.close();

	}

	// alert(window.matchMedia('print'));
	document.forms[0].strSaveFlag.value = 0;
	// window.onbeforeprint = beforePrint;
	// window.onafterprint = hidePrintableSlip();
	// setTimeout("hidePrintableSlip()",2000);
	// document.getElementById("printableSlip").style.display="none";
}
function hidePrintableSlip() {
	// alert("hide");
	document.getElementById("printableSlip").style.display = "none";
}
function printLastBill() {
	// alert("show");
	// document.getElementById("printableSlip").style.display="";
	if (document.getElementById("printableSlip").style.display == "") {
		// alert("showsdsdsdsdsd");
		alert("No Slip Generated Yet.");
		return;
	} else
		window.print();
}
function showPrintableSlip() {
	// alert("show");
	document.getElementById("printableSlip").style.display = "";
}
var globalCnt = 0;
String.prototype.replaceAll = function(target, replacement) {
	return this.split(target).join(replacement);
}

function onchangeState() {
	var cboCountry = document.getElementsByName("strCountry")[0];
	var cbo = document.getElementsByName("strState")[0];
	var cboDist = document.getElementsByName("strDistrictCode")[0];

	if (cboCountry.value == "IND") {

		var text = "";
		if (cbo.value != "0")
			text = cbo.options[cbo.selectedIndex].text;
		document.getElementsByName("strStateName")[0].value = text;
		var hmode = "GETDISTRICT";
		var url = 'PatientAdmissionTransBSCNT.cnt?hmode=' + hmode
				+ '&stateCode=' + cbo.value;
		ajaxFunction(url, "7");
	}

}
function onchangeCountry() {
	var cbo = document.getElementsByName("strCountry")[0];
	if (cbo != undefined) {
		if (cbo.value != "IND") {
			document.getElementById("stateTextBoxDiv").style.display = "";
			document.getElementById("stateComboDiv").style.display = "none";

			document.getElementById("districtTextBoxDiv").style.display = "";
			document.getElementById("districtSelectBoxDiv").style.display = "none";
		}
		if (cbo.value == "IND") {
			document.getElementById("stateTextBoxDiv").style.display = "none";
			document.getElementById("stateComboDiv").style.display = "";

			document.getElementById('districtTextBoxDiv').style.display = "none";
			document.getElementById('districtSelectBoxDiv').style.display = "";
		}
	}
}

function onchangeDistrict() {
	var cboDist = document.getElementsByName("strDistrictCode")[0];
	var texts = cboDist.options[cboDist.selectedIndex].text;
	document.getElementsByName("strDistrictName")[0].value = texts;
}

function validateModalAlert(obj, size, msg, type) {
	var validateModal = document.getElementById("validateModal");
	var mainDiv = document.getElementById("mainDiv")

	if (type == "1") {
		validateModal.classList.remove("modal");
		mainDiv.style.display = "none";

		if (obj.value.length == size)
			return true;
		else {
			mainDiv.style.display = "";
			validateModal.classList.add("modal");
			document.getElementById("warn").innerHTML = msg;
			document.getElementById("len").innerHTML = "[Current length="
					+ obj.value.length + "]";
			return false;
		}
	}

}
function preview() 
{
	if(document.forms[0].strIsAdmissionOnline.value=="1")//Online Adm-Advice Found
	{
		var dept = document.forms[0].strDepartmentName.value;		
		var unit=document.forms[0].strDeptUnitName.value;		
		var ward = document.getElementsByName("strWardName")[0].value;
		var con = document.forms[0].strConsultantName.value;
		var cat = document.getElementById("treatCat").innerHTML;
	}
	else
	{
		var dept = document.getElementsByName("strDeptCode")[0].options[document.getElementsByName("strDeptCode")[0].selectedIndex].text;
		var ward = document.getElementsByName("strWardCode")[0].options[document.getElementsByName("strWardCode")[0].selectedIndex].text;
		var con = document.getElementsByName("strConsultantCode")[0].options[document.getElementsByName("strConsultantCode")[0].selectedIndex].text;
		var cat = document.getElementsByName("strTreatmentCategoryCode")[0].options[document.getElementsByName("strTreatmentCategoryCode")[0].selectedIndex].text;
		var unit = con.split(":")[2];
		unit = unit.split("]")[0];
		con = con.split(":")[3];
	}
	var crno = document.getElementById("strCrNoId").value;
	var name = document.getElementById("patName1").innerHTML;
	
	var lDiv = "<div class='col-md-3' style='border-right:1px dotted black' align='Right'>";
	var rDiv = "<div class='col-md-9'>";
	var cDiv = "</div>"

	var tmpDiv = lDiv + "<b>CR No.</b>" + cDiv + rDiv + crno + cDiv;
	var tmpDiv = tmpDiv + lDiv + "<b>Name</b>" + cDiv + rDiv + name + cDiv;
	var tmpDiv = tmpDiv + lDiv + "<b>Department</b>" + cDiv + rDiv + dept
			+ cDiv;
	var tmpDiv = tmpDiv + lDiv + "<b>Unit</b>" + cDiv + rDiv + unit + cDiv;

	var tmpDiv = tmpDiv + lDiv + "<b>Ward </b>" + cDiv + rDiv + ward + cDiv;
	var tmpDiv = tmpDiv + lDiv + "<b>Consultant</b>" + cDiv + rDiv + con + cDiv;
	var tmpDiv = tmpDiv + lDiv + "<b>Category</b>" + cDiv + rDiv + cat + cDiv;

	document.getElementById("setPrevModal").innerHTML = tmpDiv;
	document.getElementById("savebutton").setAttribute("data-target","#previewModal");
}
function modalSlipPrint()
{
	var printableSlip=document.getElementById("printableSlip")
	
	if(printableSlip.innerHTML!="")
		{
		printableSlip.style.display="";
		printElement(printableSlip);
		}
		
}
function printElement(elem) {
    var domClone = elem.cloneNode(true);
    
    var $printSection = document.getElementById("printSection");
    
    if (!$printSection) {
        var $printSection = document.createElement("div");
        $printSection.id = "printSection";
        document.body.appendChild($printSection);
    }
    
    $printSection.innerHTML = "";
    $printSection.appendChild(domClone);
    
    window.print();
}
function openNewBornBabyAdm()
{
	document.forms[0].hmode.value="NEWBORNBABYADM";
	document.forms[0].submit();
					
}


function getPayMode(){
var url = "PatientAdmissionTransBSCNT.cnt?hmode=GETPAYMODE&tcat="+document.forms[0].strTreatmentCategoryCode.value;
ajaxFunction(url, "9");	
}


function getBedFrmNursingDesk(){
	
	var deptc=document.forms[0].strDeptCode;
	var unitc=document.forms[0].strConsultantCode;
	var wardc=document.forms[0].strWardCode;
	
	console.log("dept :::::::::::::::::::::::::::::::::::::"+deptc.value);
	console.log("unit :::::::::::::::::::::::::::::::::::::"+unitc.value);
	console.log("ward :::::::::::::::::::::::::::::::::::::"+wardc.value);
	
	
	console.log(deptc.value=="0" || unitc.value=="0" || wardc.value=="0");
	var url = "NursingDeskTransCNT.cnt?hmode=BEDCOMBO&deptCode="+deptc.value +"&deptUnitCode="+unitc.value.split("$")[1]+"&wardCode="+wardc.value.split("$")[0]+"&roomCode=0&shr_chk=1&previousBedCode=0";
	
	if(deptc.value=="0" || unitc.value=="0" || wardc.value=="0")
			document.forms[0].strBed.innerHTML="<option value='0'>Select Value</option>";
	else
		ajaxFunction(url, "10");	
}

/*function displayPayDetails() { //displayPayDetails(parent,payDtl,payEdit,payAmt , mode){

	//alert();
	
	
	
	
	
	if(document.forms[0].strPayMode.value.split("#")[0]=="3")
		{
		
		$("#payDtlCDMenu").css("display","block");
			document.forms[0].strPayMode.setAttribute("data-target", "#payModeModal"); 
		}
	
	

		
	
	
	var optVal = parent.value.split('#')[0];
	var creditpay=document.forms[0].strOffLineTreatmentCategory.value;
	var x=creditpay.split("^")[1];
	
		totalVal = parseFloat("0");
	
	if(mode == '0'){
		
		totalVal = document.forms[0].strOnlineTotalRecAmount.value;	  		  	
	  	totalVal = parseFloat(totalVal);
	  	
	}else{
		
		totalVal = document.forms[0].strOfflineTotalRecAmount.value;	  		  	
	  	totalVal = parseFloat(totalVal);
		
	}
	
	if(totalVal < 0){
		
			if(optVal == 4 || optVal == 5 || optVal == 6 || optVal == 8 || optVal == 9){
				
				alert("This Mode is Not Applicable for Refund");
				
				parent.selectedIndex = 0;
				document.getElementById(payAmt).focus(); 
				
				return false;
			}

	}
				
	
	gblPayMode = parent;
	 gblPayDtls = document.getElementById(payDtl);
	 gblPayEdit = document.getElementById(payEdit);
	 gblAmount  = document.getElementById(payAmt); 
	 
		document.forms[0].strPayCDDBankName.value = "";
		document.forms[0].strChequeDDNo.value = "";
		
	
	document.forms[0].strPayCDBankName.value = "";
	document.forms[0].strCardNo.value = "";
	document.forms[0].strAuthNo.value = "";
	
	document.forms[0].strCardType.selectedIndex = 0;
	
	document.forms[0].strPayCNTClientName.selectedIndex = 0;
	document.forms[0].strClientAuthNo.value = "";

	
	gblPayDtls.value = "";
	gblPayDtls.disabled = true;
	gblPayEdit.disabled = true;
	
    if(x=="3" || x=="4"){   //credit category
		
		if(optVal == 2 || optVal == 3 || optVal == 4 || optVal == 5 || optVal == 6 || optVal == 7 || optVal == 8 || optVal == 9)
		{
		alert("For Credit Category, No Payment Mode Other Than Cash Is Allowed.");
		gblPayMode.selectedIndex = 0;
        gblPayDtls.disabled = false;
		}
	}
	else{
	

	if(optVal == 2 || optVal == 3){
	
					
	
		
		popup('payDtlCDDMenu' , '250','250');
		
		document.forms[0].strPayCDBankName.focus();
		
	}else if(optVal == 4 || optVal == 5 || optVal == 8 || optVal == 9){
	
	
		popup('payDtlCDMenu' , '250','250');
		
	document.forms[0].strPayCDDBankName.focus();
	
	}else if(optVal == 6){
	
	
		
		popup('payDtlCLTMenu' , '250','250');
		
	document.forms[0].strPayCNTClientName.focus();
	
	}else if(optVal == 7){
		
		popup('payDtlWalletMenu' , '250','250');
		
    }else{
	
			
			
			gblPayDtls.disabled = false;
			
			gblAmount.focus();
	}
	}
}*/



$().ready(function(){
	$("#strPayMode").on("change",function(){
		
		document.forms[0].strPayDetail.value="";
		
		var paymode=$(this).val().split("#")[0];
		
		if(paymode=="2" || paymode=="3")
		{
			$("#payDtlCDDModal").modal("show");
		}
		
		if(paymode=="4" || paymode=="5")
			{
				$("#payModeModal").modal("show");
			}
		 if(paymode=="7"){
				$("#payDtlWalletMenu").modal("show");
			}	
		
	});
});


$()


function validateCreditDebit(){

	
	
var hisValidator = new HISValidator("paientAdmissionTransBean");  
	
	hisValidator.addValidation("strPayBankName", "req", "Bank Name is a Mandatory Field" );
	hisValidator.addValidation("strCardNo","req", "Card No. is a Mandatory Field" );
	hisValidator.addValidation("strCardNo","minlen=4", "Card No. must be 4 Digits" );
	hisValidator.addValidation("strAuthNo","req", "Transaction  No. is a Mandatory Field" );
	hisValidator.addValidation("strAuthDate", "req", "Transaction  Date is a Mandatory Field" );
	hisValidator.addValidation("strAuthDate", "date", "Please Enter a Valid Transaction  Date" );
	hisValidator.addValidation("strCardType", "dontselect=0", "Please Select a Card Type" );
	
	var retVal = hisValidator.validate(); 
	
	hisValidator.clearAllValidations();
		if(retVal){
			
				var resultVal = document.forms[0].strPayBankName.value+","+document.forms[0].strCardNo.value+","+document.forms[0].strAuthNo.value+","+document.forms[0].strAuthDate.value+","+document.forms[0].strCardType[document.forms[0].strCardType.selectedIndex].text;
				
				
				document.forms[0].strPayDetail.value=resultVal;
				
					$("#payModeModal").modal("hide");
				
				/*gblPayDtls.value = resultVal;
				gblPayEdit.disabled = false;
				
				hide_popup('payDtlCDMenu');
				
				//alert(gblAmount.value)
					gblAmount.focus();*/
		}else{
		
		return false;
		}

}


function validateCheckDD(){

	var hisValidator = new HISValidator("paientAdmissionTransBean");  
			
		hisValidator.addValidation("strPayCDDBankName", "req", "Bank Name is a Mandatory Field" );
		hisValidator.addValidation("strChequeDDNo","req", "Cheque / DD No. is a Mandatory Field" );
		hisValidator.addValidation("strChequeDDDate", "req", "Cheque / DD Date is a Mandatory Field" );
		hisValidator.addValidation("strChequeDDDate", "date", "Please enter a valid Cheque / DD Date" );
		var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
			if(retVal){
					
					var resultVal = document.forms[0].strPayCDDBankName.value+","+document.forms[0].strChequeDDNo.value+","+document.forms[0].strChequeDDDate.value;
					
					
					document.forms[0].strPayDetail.value=resultVal;
					/*gblPayDtls.value = resultVal;
					gblPayEdit.disabled = false;*/
					
					$("#payDtlCDDModal").modal("hide");
					
			}else{
			
			return false;
			}

	}

