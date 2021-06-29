<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar.js"/>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />

<script type="text/javascript">

window.onload=function()
{
	if(document.getElementsByName("deceasedFrom")[0].checked)
	{
		ageSelection();
		unknownDetail();
	//	enableSpouseField();
		showPoliceRelativeBlock();
		showHandoverStorage();
		selectCheckBox();
		mlcDetail();
		showAssociatedHospital();
		showRelativeDtl();
	}
	else if (document.getElementsByName("deceasedFrom")[1].checked)
	{	
		ageSelection();
		onSpotUnknown();
		showHandoverStorage();
	//	enableSpouseField();
	}
	else
		{
		ageSelection();
		showHandoverStorage();
		}
}
function selectCheckBox()
{
	if(document.getElementsByName("unknownChkValue")[0].value==<%=MortuaryConfig.YES%>)
		document.getElementsByName("unidentifiedBody")[0].checked=true;
	if(document.getElementsByName("mlcChkValue")[0].value==<%=MortuaryConfig.YES%>)
		document.getElementsByName("isMlcCase")[0].checked=true;
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function showEntryDetail()
{
	/*if(document.getElementsByName("deceasedFrom")[0].checked)
	{
		document.getElementById("divOtherHospital").style.display="block";
		document.getElementById("divOnSpotDeath").style.display="none";
	}
	if(document.getElementsByName("deceasedFrom")[1].checked)
	{
		document.getElementById("divOtherHospital").style.display="none";
		document.getElementById("divOnSpotDeath").style.display="block";
	}*/
	submitPage('DETAIL');
}

function ageSelection()
{
	if(document.getElementsByName("isActualDob")[0].checked)
	{
		 document.getElementById("divAge").style.display="";
		 document.getElementById("divDob").style.display="none";
	 	 document.getElementsByName("patDOB")[0].value=DateValidator.DATE_FORMAT;
	}
	else
	{
		  document.getElementsByName("patAge")[0].value="";
		  document.getElementsByName("patAgeUnit")[0].selectedIndex=0;
		  document.getElementById("divAge").style.display="none";
		  document.getElementById("divDob").style.display="block";
	} 
}

function newDutyOfficerDetail()
{
	if(document.getElementsByName("dutyOfficeFlag")[1].checked)
	{
		document.getElementById('newDutyOfficerDetailId').style.display="block";
	}
	else
	{
		document.getElementById('newDutyOfficerDetailId').style.display="none";
	}
}

function enableSpouseField()
{
	var maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
	var maritalStatusSingle="<%=RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_SINGLE%>";
	
	if( (maritalStatus=="-1") || (maritalStatus==maritalStatusSingle))
	{
		document.getElementsByName("patHusbandName")[0].readOnly=true;
		document.getElementsByName("patHusbandName")[0].value="";
	}
	else
	{
		document.getElementsByName("patHusbandName")[0].readOnly=false;
		
	}
}	

function mlcDetail()
{
	if(document.getElementsByName("isMlcCase")[0].checked)
	{
		document.getElementById("divBlankLabel").style.display="none";
		document.getElementById("divBlankControl").style.display="none";
		document.getElementById("divMLCNoLabel").style.display="block";
		document.getElementById("divMLCNoControl").style.display="block";
		
	/*	document.getElementById("divMandatoryRelativeName").style.display="none";
		document.getElementById("divNonMandatoryRelativeName").style.display="block";
		document.getElementById("divMandatoryRelativeCode").style.display="none";
		document.getElementById("divNonMandatoryRelativeCode").style.display="block";
		document.getElementById("divMandatoryRelativeAdd").style.display="none";
		document.getElementById("divNonMandatoryRelativeAdd").style.display="block";
		document.getElementById("divMandatoryRelativeNo").style.display="none";
		document.getElementById("divNonMandatoryRelativeNo").style.display="block";*/
		
		
		showPoliceRelativeBlock();
	//	enableSpouseField();
	}
	else
	{
		document.getElementById("divBlankLabel").style.display="block";
		document.getElementById("divBlankControl").style.display="block";
		document.getElementById("divMLCNoLabel").style.display="none";
		document.getElementById("divMLCNoControl").style.display="none";
		
	/*	document.getElementById("divMandatoryRelativeName").style.display="block";
		document.getElementById("divNonMandatoryRelativeName").style.display="none";
		document.getElementById("divMandatoryRelativeCode").style.display="block";
		document.getElementById("divNonMandatoryRelativeCode").style.display="none";
		document.getElementById("divMandatoryRelativeAdd").style.display="block";
		document.getElementById("divNonMandatoryRelativeAdd").style.display="none";
		document.getElementById("divMandatoryRelativeNo").style.display="block";
		document.getElementById("divNonMandatoryRelativeNo").style.display="none";*/
		
		showPoliceRelativeBlock();
	//	enableSpouseField();
	}
}

function unknownDetail()
{
	if(document.getElementsByName("unidentifiedBody")[0].checked)
	{
		document.getElementsByName("patFirstName")[0].value="Unknown";
		document.getElementsByName("patMiddleName")[0].value="";
		document.getElementsByName("patLastName")[0].value="";
		document.getElementsByName("patGuardianName")[0].value="";
//		document.getElementsByName("patMotherName")[0].value="";
		//document.getElementsByName("patHusbandName")[0].value="";
//		document.getElementsByName("patMaritalStatusCode")[0].value="-1";
		document.getElementsByName("add1")[0].value="";
		document.getElementsByName("add2")[0].value="";
//		document.getElementsByName("contactNo")[0].value="";
		
		
		document.getElementsByName("patFirstName")[0].readOnly=true;
		document.getElementsByName("patMiddleName")[0].readOnly=true;
		document.getElementsByName("patLastName")[0].readOnly=true;
		document.getElementsByName("patGuardianName")[0].readOnly=true;
//		document.getElementsByName("patMotherName")[0].readOnly=true;
		//document.getElementsByName("patHusbandName")[0].readOnly=true;
//		document.getElementsByName("patMaritalStatusCode")[0].disabled=true;
		document.getElementsByName("add1")[0].readOnly=true;
		document.getElementsByName("add2")[0].readOnly=true;
//		document.getElementsByName("contactNo")[0].readOnly=true;
		
		document.getElementsByName("isMlcCase")[0].checked=true;
		document.getElementsByName("isMlcCase")[0].disabled=true;
		
		document.getElementById("divIdMark2Comp").style.display="block";
		document.getElementById("divIdMark1Comp").style.display="block";
		document.getElementById("divIdMark2").style.display="none";
		document.getElementById("divIdMark1").style.display="none";
		
	//	enableSpouseField();
		mlcDetail();
		showPoliceRelativeBlock();
	}
	else
	{
		if(typeof document.getElementsByName("hmode")[0] !='undefined' && document.getElementsByName("hmode")[0].value!="GETRACK")
			document.getElementsByName("patFirstName")[0].value="";
		
		document.getElementsByName("patFirstName")[0].readOnly=false;
		document.getElementsByName("patMiddleName")[0].readOnly=false;
		document.getElementsByName("patLastName")[0].readOnly=false;
		document.getElementsByName("patGuardianName")[0].readOnly=false;
//		document.getElementsByName("patMotherName")[0].readOnly=false;
		//document.getElementsByName("patHusbandName")[0].readOnly=false;
//		document.getElementsByName("patMaritalStatusCode")[0].disabled=false;
		document.getElementsByName("add1")[0].readOnly=false;
		document.getElementsByName("add2")[0].readOnly=false;
//		document.getElementsByName("contactNo")[0].readOnly=false;
		
		document.getElementsByName("isMlcCase")[0].checked=false;
		document.getElementsByName("isMlcCase")[0].disabled=false;
	
		document.getElementById("divIdMark2Comp").style.display="none";
		document.getElementById("divIdMark1Comp").style.display="none";
		document.getElementById("divIdMark2").style.display="block";
		document.getElementById("divIdMark1").style.display="block";
		
//		enableSpouseField();
		mlcDetail();
		showPoliceRelativeBlock();
	}
}
function onSpotUnknown()
{
	if(document.getElementsByName("unidentifiedBody")[0].checked)
	{
		document.getElementsByName("patFirstName")[0].value="Unknown";
		document.getElementsByName("patMiddleName")[0].value="";
		document.getElementsByName("patLastName")[0].value="";
		document.getElementsByName("patGuardianName")[0].value="";
//		document.getElementsByName("patMotherName")[0].value="";
//		document.getElementsByName("patMaritalStatusCode")[0].value="-1";
		document.getElementsByName("add1")[0].value="";
		document.getElementsByName("add2")[0].value="";
//		document.getElementsByName("contactNo")[0].value="";
		
		document.getElementsByName("patFirstName")[0].readOnly=true;
		document.getElementsByName("patMiddleName")[0].readOnly=true;
		document.getElementsByName("patLastName")[0].readOnly=true;
		document.getElementsByName("patGuardianName")[0].readOnly=true;
//		document.getElementsByName("patMotherName")[0].readOnly=true;
//		document.getElementsByName("patMaritalStatusCode")[0].disabled=true;
		document.getElementsByName("add1")[0].readOnly=true;
		document.getElementsByName("add2")[0].readOnly=true;
//		document.getElementsByName("contactNo")[0].readOnly=true;
		
		document.getElementById("divIdMark2Comp").style.display="block";
		document.getElementById("divIdMark1Comp").style.display="block";
		document.getElementById("divIdMark2").style.display="none";
		document.getElementById("divIdMark1").style.display="none";
		
//		enableSpouseField();
	}
	else
	{
		if(typeof document.getElementsByName("hmode")[0] !='undefined' && document.getElementsByName("hmode")[0].value!="GETRACK")
			document.getElementsByName("patFirstName")[0].value="";
		
		document.getElementsByName("patFirstName")[0].readOnly=false;
		document.getElementsByName("patMiddleName")[0].readOnly=false;
		document.getElementsByName("patLastName")[0].readOnly=false;
		document.getElementsByName("patGuardianName")[0].readOnly=false;
//		document.getElementsByName("patMotherName")[0].readOnly=false;
//		document.getElementsByName("patMaritalStatusCode")[0].disabled=false;
		document.getElementsByName("add1")[0].readOnly=false;
		document.getElementsByName("add2")[0].readOnly=false;
//		document.getElementsByName("contactNo")[0].readOnly=false;
	
		document.getElementById("divIdMark2Comp").style.display="none";
		document.getElementById("divIdMark1Comp").style.display="none";
		document.getElementById("divIdMark2").style.display="block";
		document.getElementById("divIdMark1").style.display="block";
		
	//	enableSpouseField();
	}
}

function showPoliceRelativeBlock()
{
	if(document.getElementsByName("unidentifiedBody")[0].checked)
	{
		document.getElementById("divPolice").style.display="block";
		document.getElementById("divRelative").style.display="none";
		document.getElementById("divAccRelative").style.display="none";
	}
	else
	{
		if(document.getElementsByName("isMlcCase")[0].checked)
		{
			document.getElementById("divPolice").style.display="block";
			document.getElementById("divRelative").style.display="none";
			document.getElementById("divAccRelative").style.display="block";
		}
		else
		{
			document.getElementById("divPolice").style.display="none";
			document.getElementById("divRelative").style.display="block";
			document.getElementById("divAccRelative").style.display="none";
		}
	}
}

function showHandoverStorage()
{
	if(document.getElementsByName("storageFlag")[0].checked)
		document.getElementById("divBodyStorageId").style.display="none";
	else
		document.getElementById("divBodyStorageId").style.display="block";
}

function getRack()
{
	var chamberId=document.getElementsByName("chamberId")[0].value;
	if(chamberId!="-1")
		submitForm('GETRACK');
}

function validateOtherHospitalSave()
{
	var valid=false;
	
	if(validateDeceasedDetail() && validateHospitalDetail() && validatePolice() && validateRelativeDetail() && validateStorageDetail())
	{
		if(document.forms[0].patDOB.value!="")
		{
			var dateOfBirth = convertStrToDate(document.forms[0].patDOB.value,"dd-MM-yyyy");
			document.forms[0].patDOB.maxLength = 11;
			document.forms[0].patDOB.value = convertDateToStr(dateOfBirth,"dd-Mon-yyyy");
		}
		valid=true;
	}	
	else
		valid=false;	
		
	return valid;	
}

function validatePolice()
{
	var valid=false;
	if(document.getElementsByName("unidentifiedBody")[0].checked)
	{
		valid=validatePoliceDetail();
	}
	else
	{
		if(document.getElementsByName("isMlcCase")[0].checked)
		{
			valid=validatePoliceDetail();
		}
		else
			valid=true;
	}
	return valid;
}

function validateDeceasedDetail()
{
	var valid=false;
	if(document.getElementsByName("unidentifiedBody")[0].checked)
	{
		valid=validateUnknown();
	}
	else
	{
		if(document.getElementsByName("isMlcCase")[0].checked)
		{
			valid=validateMlc();
		}
		else
		{
			valid=validateNormal();
		}
	}
	return valid;
}

function validateAgeOrDob()
{
	var valid=true;
	if(document.getElementsByName("isActualDob")[0].checked)
	{
		valid=isEmpty(document.forms[0].patAge,"Age"); 
	}
	else
	{
		valid=isEmpty(document.forms[0].patDOB,"Date of Birth");
		if(!DateValidator.validate(document.forms[0].patDOB,"Date of Birth"))
			valid = false;
	}
	/*if(!valid)
	{
		return valid;
	}*/
	
	return valid;	
}

function validateUnknown()
{
	var valid=false;
	if(validateAgeOrDob()
		&& validatePatientDOBAgainstSysDate()
		&& comboValidation(document.forms[0].patGenderCode,"Gender")
		&& isEmpty(document.forms[0].mlcNo,"MLC Number")
		&& isEmpty(document.forms[0].deathDate,"Date of Death")
		&& isEmpty(document.forms[0].deathTimeHr,"Death Hour")
		&& isEmpty(document.forms[0].deathTimeMin,"Death Min") 
		&& validateDeathDateTime()
		&& isEmpty(document.forms[0].patientIdMark1,"ID Mark 1")
		&& isEmpty(document.forms[0].patientIdMark2,"ID Mark 2")
		
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;	
}

function validateMlc()
{
	var valid=false;
	if(isEmpty(document.forms[0].patFirstName,"Deceased First Name")	
		&& validateAgeOrDob()
		&& validatePatientDOBAgainstSysDate()
		&& comboValidation(document.forms[0].patGenderCode,"Gender")
		&& isEmpty(document.forms[0].mlcNo,"MLC Number")
		&& isEmpty(document.forms[0].deathDate,"Date of Death")
		&& isEmpty(document.forms[0].deathTimeHr,"Death Hour")
		&& isEmpty(document.forms[0].deathTimeMin,"Death Min") 
		&& validateDeathDateTime()
		
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;	
}

function validateNormal()
{
	var valid=false;
	if(isEmpty(document.forms[0].patFirstName,"Deceased First Name")	
		&& validateAgeOrDob()
		&& validatePatientDOBAgainstSysDate()
		&& comboValidation(document.forms[0].patGenderCode,"Gender")
		&& isEmpty(document.forms[0].deathDate,"Date of Death")
		&& isEmpty(document.forms[0].deathTimeHr,"Death Hour")
		&& isEmpty(document.forms[0].deathTimeMin,"Death Min") 
		&& validateDeathDateTime()
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateHospitalDetail()
{
	var valid=false;
	if(validateExtHospitalName()
		&& isEmpty(document.forms[0].extPatCrNo,"CR No.")
		&& isEmpty(document.forms[0].extDeptName,"Department Name")
		&& isEmpty(document.forms[0].doctorName,"Doctor Name")
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;	
}

function validateExtHospitalName()
{
	var valid=false;
	if(document.getElementsByName("isAssociated")[1].checked)
	{
		valid=isEmpty(document.forms[0].extHospitalName,"Hospital Name")
	}
	else
	{
		valid=comboValidation(document.forms[0].assoHospitalCode,"Hospital Name");
	}
	
	return valid;
}

function validatePoliceDetail()
{
	var valid=false;
	if(isEmpty(document.forms[0].caseNo,"Case No")
		&& isEmpty(document.forms[0].policeStation,"Police Station")
		&& isEmpty(document.forms[0].docketNo,"Docket No")
		&& isEmpty(document.forms[0].officerIncharge,"Investigating Officer Name")
		&& isEmpty(document.forms[0].ioDesignation,"Investigating Officer Designation")
		&& isEmpty(document.forms[0].ioBatchNo,"Investigating Officer Badge No.")
		&& validateDutyOfficer()
		&& isEmpty(document.forms[0].caseRemarks,"Case Remarks")
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateDutyOfficer()
{
	var valid=false;
	if(document.getElementsByName("dutyOfficeFlag")[1].checked)
	{
		if(isEmpty(document.forms[0].dutyOffName,"Duty Officer Name")
			&& isEmpty(document.forms[0].dutyOffDesignation,"Duty Officer Designation")
			&& isEmpty(document.forms[0].dutyOffBatchNo,"Duty Officer Badge No.")
		)
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}
	else
	{
		valid=true;
	}
	return valid;	
}

function validateRelativeDetail()
{
	var valid=false;
	if(document.getElementsByName("unidentifiedBody")[0].checked)
	{
		valid=true;
	}
	else
	{
		if(document.getElementsByName("isMlcCase")[0].checked)
			valid=validateMLCRelative();
			//valid=validateRelative();
		else
			valid=validateNormalRelative();
	}
	return valid;
}

function validateMLCRelative()
{
	var valid=false;
	if(document.getElementsByName("isAccompained")[0].checked)
	{
		if(isEmpty(document.forms[0].accRelativeName,"Relative Name")
			&& comboValidation(document.forms[0].accRelativeCode,"Relationship")
			&& isEmpty(document.forms[0].accRelativeAddress,"Address of Relative")
			&& isEmpty(document.forms[0].accRelativeContactNo,"Relative Contact No")
		)
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}
	else
		valid=true;	
	return valid;
}

function validateNormalRelative()
{
	var valid=false;
	if(document.getElementsByName("isClaimed")[0].checked)
	{
		valid=validateRelative();
	}
	else
	{
		valid=validateBroughtPolice();
	}
	return valid;
}

function validateBroughtPolice()
{
	var valid=false;
	if(isEmpty(document.forms[0].brtOffName,"Duty Officer Name")
		&& isEmpty(document.forms[0].brtOffDesignation,"Duty Officer Designation")
		&& isEmpty(document.forms[0].brtOffBatchNo,"Duty Officer Badge No")
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateRelative()
{
	var valid=false;
	if(isEmpty(document.forms[0].relativeName,"Relative Name")
		&& comboValidation(document.forms[0].relativeCode,"Relationship")
		&& isEmpty(document.forms[0].relativeAddress,"Address of Relative")
		&& isEmpty(document.forms[0].relativeContactNo,"Relative Contact No")
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateStorageDetail()
{
	var valid=false;
	if(document.getElementsByName("storageFlag")[1].checked)
	{
		if(comboValidation(document.forms[0].chamberId,"Chamber")
			&& comboValidation(document.forms[0].chamberRackId,"Rack")
			&& comboValidation(document.forms[0].bodyPutBy,"Body Put By")
			&& validateStorageUpto()
		)
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}	
	else
		valid=true;
		
	return valid;
}


function validateStorageUpto()
{
	var valid=true;
	var maxUpto="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS%>"
	if(document.getElementsByName("storageUpto")[0].value=="" || document.getElementsByName("storageUpto")[0].value=="0" || document.getElementsByName("storageUpto")[0].value=="00")
	{
		alert("Please Enter The Storage Duration");
		document.getElementsByName("storageUpto")[0].focus();
		valid=false;
	}
	else
		{
			valid=true;
		}
	return valid;
}


function hideUnhide(imgObj)
{
	var divObj=document.getElementById("div"+imgObj.id.substr(3));
	if(divObj.style.display=="none")
	{
		divObj.style.display="block";
		imgObj.src = "/../HIS/hisglobal/images/avai/arrow-up.png";
	}
	else if(divObj.style.display=="block")
	{
		divObj.style.display="none";
		imgObj.src = "/../HIS/hisglobal/images/avai/arrow-down.png";
	}
}


//onkeypress="return onkeyTimeHour(this,event)"
function onkeyTimeHour(_hObj,_e) // 24-Hour Format
{
	var c=_e.charCode;
	var k=_e.keyCode;

	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);

	//alert("Key -> "+k+"  Char -> "+c+"  Value -> "+hh);

	// Only integer check
	if(k==0 && c>=48 && c<=57) return true;

	if(k==40 || k==38) // on Arrow press Down-40 Up-38
	{
		if(k==40)		hh=(hh+1)%24;
		else if(k==38)	hh=(24+hh-1)%24;

		// Setting Hour
		if(hh<10) _hObj.value="0"+hh;
		else		_hObj.value=hh;
	}
	if(k!=0)	return true;
	else		return false;
}

function onblurTimeHourCheck(_hObj)
{
	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);
	// Setting Hour
	if(hh<10)	_hObj.value="0"+hh;
	else			_hObj.value=hh;
}

//onkeypress="return onkeyTimeMin(this,document.getElementsByName('hour')[0],event)"
function onkeyTimeMin(_mObj,_hObj,_e) // 24-Hour Format
{
	var c=_e.charCode;
	var k=_e.keyCode;

	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);

	var mm=/^0/.test(_mObj.value)?_mObj.value.substr(1,1):_mObj.value;
	if(mm=="" || mm>=60) mm=0;
	mm=parseInt(mm);

	//alert("Key -> "+k+"  Char -> "+c+"  Value -> "+mm);

	// Only integer check
	if(k==0 && c>=48 && c<=57) return true;

	if(k==40 || k==38)	// on Arrow press Down-40 Up-38
	{
		if(k==40)
		{
			if(mm==59)	hh=(hh+1)%24;
			mm=(mm+1)%60;
		}
		else if(k==38)
		{
			if(mm==0)	hh=(24+hh-1)%24;
			mm=(60+mm-1)%60;
		}

		// Setting Hour
		if(hh<10) _hObj.value="0"+hh;
		else		_hObj.value=hh;

		// Setting Minutes
		if(mm<10) _mObj.value="0"+mm;
		else		_mObj.value=mm;
	}
	if(k!=0)	return true;
	else		return false;
}

function onblurTimeMinCheck(_mObj)
{
	var mm=/^0/.test(_mObj.value)?_mObj.value.substr(1,1):_mObj.value;
	if(mm=="" || mm>=60) mm=0;
	mm=parseInt(mm);
	// Setting Minutes
	if(mm<10)	_mObj.value="0"+mm;
	else			_mObj.value=mm;
}

function clearOtherHospForm()
{
	if(!document.getElementsByName("unidentifiedBody")[0].checked)
		document.getElementsByName("patFirstName")[0].value="";
	document.getElementsByName("patMiddleName")[0].value="";
	document.getElementsByName("patLastName")[0].value="";
	document.getElementsByName("patGenderCode")[0].value="-1";
	document.getElementsByName("patDOB")[0].value="";
	document.getElementsByName("isActualDob")[0].checked=true;
	document.getElementsByName("patAge")[0].value="";
	document.getElementsByName("patAgeUnit")[0].value="Y";
	document.getElementsByName("patGuardianName")[0].value="";
//	document.getElementsByName("patMotherName")[0].value="";
//	document.getElementsByName("patHusbandName")[0].value="";
//	document.getElementsByName("patMaritalStatusCode")[0].value="-1";
	document.getElementsByName("deathDate")[0].value="";
	document.getElementsByName("deathTimeHr")[0].value="";
	document.getElementsByName("deathTimeMin")[0].value="";
	document.getElementsByName("patientIdMark1")[0].value="";
	document.getElementsByName("patientIdMark2")[0].value="";
	document.getElementsByName("add1")[0].value="";
	document.getElementsByName("add2")[0].value="";
//	document.getElementsByName("contactNo")[0].value="";
	document.getElementsByName("mlcNo")[0].value="";
	
	document.getElementsByName("extHospitalName")[0].value="";
	document.getElementsByName("assoHospitalCode")[0].value="-1";
	document.getElementsByName("isAssociated")[0].checked=true;
	document.getElementsByName("extDeptName")[0].value="";
	document.getElementsByName("extPatCrNo")[0].value="";
	document.getElementsByName("extPatAdmNo")[0].value="";
	document.getElementsByName("extUnitName")[0].value="";
	document.getElementsByName("extWardName")[0].value="";
	document.getElementsByName("extBedNo")[0].value="";
	document.getElementsByName("doctorName")[0].value="";
	document.getElementsByName("extHospitalContactNo")[0].value="";
	
	
	
	document.getElementsByName("relativeAddress")[0].value="";
	document.getElementsByName("relativeContactNo")[0].value="";
	document.getElementsByName("relativeCode")[0].value="-1";
	document.getElementsByName("relativeName")[0].value="";
	
	clearPolice();
	clearStorage();
	
	showHandoverStorage();
	ageSelection();
}

function validateOnSpotSave()
{
	var valid=false;
	
	if(validateOnSpotDeceasedDetail() 
		&& validatePoliceDetail() 
		&& validateOnSpotRelativeDetail() 
		&& validateStorageDetail())
	{	
		if(document.forms[0].patDOB.value!="")
		{
			var dateOfBirth = convertStrToDate(document.forms[0].patDOB.value,"dd-MM-yyyy");
			document.forms[0].patDOB.maxLength = 11;
			document.forms[0].patDOB.value = convertDateToStr(dateOfBirth,"dd-Mon-yyyy");
		}
		valid=true;
	}	
	else
		valid=false;	
		
	return valid;	
	
}

function validateOtherPlcSave()
{
	var valid=false;
	if(validateNormal()
		&& validateStorageDetail())
	{	
		if(document.forms[0].patDOB.value!="")
		{
			var dateOfBirth = convertStrToDate(document.forms[0].patDOB.value,"dd-MM-yyyy");
			document.forms[0].patDOB.maxLength = 11;
			document.forms[0].patDOB.value = convertDateToStr(dateOfBirth,"dd-Mon-yyyy");
		}
		valid=true;
	}	
	else
		valid=false;	
	return valid;	
	
}


function validateOnSpotDeceasedDetail()
{
	var valid=false;
	if(document.getElementsByName("unidentifiedBody")[0].checked)
	{
		valid=validateOnSpotUnknown();
	}
	else
	{
		valid=validateOnSpotNormal();
	}
	
	return valid;	
}

function validateOnSpotUnknown()
{
	var valid=false;
	if(validateAgeOrDob()
		&& validatePatientDOBAgainstSysDate()
		&& comboValidation(document.forms[0].patGenderCode,"Gender")
		&& isEmpty(document.forms[0].deathDate,"Date of Death")
		&& isEmpty(document.forms[0].deathTimeHr,"Death Hour")
		&& isEmpty(document.forms[0].deathTimeMin,"Death Min") 
		&& validateDeathDateTime()
		&& isEmpty(document.forms[0].patientIdMark1,"ID Mark 1")
		&& isEmpty(document.forms[0].patientIdMark2,"ID Mark 2")
		&& isEmpty(document.forms[0].broughtLocation,"Brought From Location")
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;	
}

function validateOnSpotNormal()
{
	var valid=false;
	if(isEmpty(document.forms[0].patFirstName,"Deceased First Name")	
		&& validateAgeOrDob()
		&& comboValidation(document.forms[0].patGenderCode,"Gender")
		&& isEmpty(document.forms[0].deathDate,"Date of Death")
		&& isEmpty(document.forms[0].deathTimeHr,"Death Hour")
		&& isEmpty(document.forms[0].deathTimeMin,"Death Min") 
		&& validateDeathDateTime()
		&& isEmpty(document.forms[0].broughtLocation,"Brought From Location")
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateOnSpotRelativeDetail()
{
	var rName=document.getElementsByName("relativeName")[0].value;
	var rCode=document.getElementsByName("relativeCode")[0].value;
	var rAdd=document.getElementsByName("relativeAddress")[0].value;
	var rNo=document.getElementsByName("relativeContactNo")[0].value;
	if(rName!="" || rCode!="-1" || rAdd!="" || rNo!="")
	{
		valid=validateRelative();
	}
	else
		valid=true;
	
	return valid;	
}

function clearPolice()
{
	document.getElementsByName("caseNo")[0].value="";
	document.getElementsByName("policeStation")[0].value="";
	document.getElementsByName("docketNo")[0].value="";
	document.getElementsByName("officerIncharge")[0].value="";
	document.getElementsByName("ioDesignation")[0].value="";
	document.getElementsByName("ioBatchNo")[0].value="";
	document.getElementsByName("dutyOfficeFlag")[1].checked=true;
	document.getElementsByName("dutyOffName")[0].value="";
	document.getElementsByName("dutyOffDesignation")[0].value="";
	document.getElementsByName("dutyOffBatchNo")[0].value="";
	document.getElementsByName("caseRemarks")[0].value="";
}

function clearStorage()
{
	document.getElementsByName("storageFlag")[0].checked=true;
	document.getElementsByName("chamberId")[0].value="-1";
	document.getElementsByName("chamberRackId")[0].value="-1";
	document.getElementsByName("bodyPutBy")[0].value="-1";
	document.getElementsByName("storageUpto")[0].value="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS%>";
	document.getElementsByName("storageReason")[0].value="";
}

function clearOnSpotForm()
{
	document.getElementsByName("patAge")[0].value="";
	document.getElementsByName("patAgeUnit")[0].value="Y";
	document.getElementsByName("patGenderCode")[0].value="-1";
	document.getElementsByName("broughtLocation")[0].value="";
	document.getElementsByName("deathDate")[0].value="";
	document.getElementsByName("deathTimeHr")[0].value="";
	document.getElementsByName("deathTimeMin")[0].value="";
	document.getElementsByName("patientIdMark1")[0].value="";
	document.getElementsByName("patientIdMark2")[0].value="";
	clearStorage();
	clearPolice();
	showHandoverStorage();
}

function validateDeathDateTime()
{
	var valid=true;
	var deathDate=document.getElementsByName("deathDate")[0].value;
	var deathTimeHr=document.getElementsByName("deathTimeHr")[0].value;
	var deathTimeMin=document.getElementsByName("deathTimeMin")[0].value;
	
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var hiddenTimeHr=document.getElementsByName("hiddenTimeHr")[0].value;
	var hiddenTimeMin=document.getElementsByName("hiddenTimeMin")[0].value;
	
	days=parseInt((noOfDays(sysDate,deathDate)));
	
	if(days<0)
	{
		alert("Death Date Cannot be Greater Than "+sysDate+" "+hiddenTimeHr+":"+hiddenTimeMin);
		document.getElementsByName("deathDate")[0].focus();
		valid=false;
	}
	else
	{
		if(days==0)
		{
			if(parseInt(trimNum(document.getElementsByName("deathTimeHr")[0].value)) > hiddenTimeHr)
			{
				alert("Death Date Cannot be Greater Than "+sysDate+" "+hiddenTimeHr+":"+hiddenTimeMin);
				document.getElementsByName("deathTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("deathTimeHr")[0].value)) >= hiddenTimeHr && parseInt(trimNum(document.getElementsByName("deathTimeMin")[0].value)) > hiddenTimeMin)
			{
				alert("Death Date Cannot be Greater Than "+sysDate+" "+hiddenTimeHr+":"+hiddenTimeMin);
				document.getElementsByName("deathTimeMin")[0].focus();
				valid=false;
			}
		}
		else
		{
			valid=true;
		}
	}
	return valid;
}

function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}

function noOfDays(a,b)
{
	valid=true;
	var day=0;
	
    var aArray=a.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
    
      
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
    
     day=(adate-bdate)/86400000;
    return day;
}

function showRelativeDtl()
{
	if(document.getElementsByName("isClaimed")[0].checked)
	{
		document.getElementById("divHideRelative").style.display="block";
		document.getElementById("divBroughtByPolice").style.display="none";
	}
	else
	{
		document.getElementById("divHideRelative").style.display="none";
		document.getElementById("divBroughtByPolice").style.display="block";
	}
}

function showClaimedRelativeDtl()
{
	if(document.getElementsByName("isAccompained")[0].checked)
	{
		document.getElementById("divAccompainedRelativeDtl").style.display="block";
	}
	else
	{
		document.getElementById("divAccompainedRelativeDtl").style.display="none";
	}
}

function showAssociatedHospital()
{
	if(document.getElementsByName("isAssociated")[0].checked)
	{
		document.getElementById("divOtherHospital").style.display="none";
		document.getElementById("divAssociatedHospital").style.display="block";
	}
	else
	{
		document.getElementById("divOtherHospital").style.display="block";
		document.getElementById("divAssociatedHospital").style.display="none";
	}
}

function validatePatientDOBAgainstSysDate()
{
	if(document.getElementsByName('isActualDob')[1].checked)
	{
		//DateValidator.prototype.validateAgainstDateOnMode = function(_givenDate, _givenFormat, _mode, _dateName, _givenDateName)
		var ctrlObjPatDOB = document.forms[0].patDOB.Control_Object;
	//	alert(ctrlObjPatDOB);
		var elemCurrentDate = document.getElementsByName('sysDate')[0];		
		var flag = ctrlObjPatDOB.validateAgainstDateOnMode(elemCurrentDate.value,"dd-Mon-yyyy",DateValidator.COMPARE_MODES["LessNEqual"],"Date of Birth","Current Date");
	//	alert(flag);
		if(flag==true)
		{
			var dateOfBirth = convertStrToDate(document.forms[0].patDOB.value,"dd-MM-yyyy");
			var currentDate = convertStrToDate(elemCurrentDate.value,"dd-Mon-yyyy");
		//	alert(dateOfBirth);
			//alert(currentDate);
			
			// utilityFunctions.js :: function dateDifference(_bigDt,_smallDt,_format{"Y","M","D","H","MI","S"})
			var ageInYears = dateDifference(currentDate,dateOfBirth,"Y");
			//alert("Age : " + ageInYears +" Years");
			if(parseInt(ageInYears)>125)
			{
				alert("Age ("+ageInYears+" Years) cannot be greater than 125 Years");
				flag=false;
			}
		}		
		return flag;
	}
	else
		return true;
}
</script>

<% 
	String strdivage="\"\"";	
	String strdivdob="\"\""; 
%>
<body>
	<html:form action="/externalDeceasedAcceptance">
		<his:TransactionContainer>
			<his:TitleTag name="External Deceased Acceptance">
			</his:TitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="deceased"/>
									<bean:message key="brought"/>
									<bean:message key="from"/>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="otherHospital"/>
								</font>	
								<html:radio name="ExternalDeceasedAcceptanceFB" property="deceasedFrom" value="<%=MortuaryConfig.ENTRY_MODE_SEND_FROM_OTHER_HOSPITAL %>" onclick="showEntryDetail()" tabindex="1" ></html:radio>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="onSpotDeath"/>
								</font>
								<html:radio name="ExternalDeceasedAcceptanceFB" property="deceasedFrom" value="<%=MortuaryConfig.ENTRY_MODE_BROUGHT_DEAD_FROM_SPOT_BY_POLICE %>" onclick="showEntryDetail()" tabindex="1" ></html:radio>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="otherPlace"/>
								</font>
								<html:radio name="ExternalDeceasedAcceptanceFB" property="deceasedFrom" value="<%=MortuaryConfig.ENTRY_MODE_SEND_FROM_OTHER_PLACES %>" onclick="showEntryDetail()" tabindex="1" ></html:radio>
							</div>
						</td>
					</tr>
				</table>	
			</his:ContentTag>
			
		<his:statusTransactionInProcess>
		<bean:define name="ExternalDeceasedAcceptanceFB" property="sysDate" id="sysDate" type="java.lang.String" />
		<%
			if(sysDate==null||sysDate.equalsIgnoreCase(""))
			{
				sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			}
		%>
		<logic:equal name="ExternalDeceasedAcceptanceFB" property="deceasedFrom" value="<%=MortuaryConfig.ENTRY_MODE_SEND_FROM_OTHER_HOSPITAL %>">
			
				<his:SubTitleTag name="Deceased Detail">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td>
								<div align="right">
		  							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<B>
											<bean:message key="unknown"/>
										</B>
									</font>
									<html:checkbox name="ExternalDeceasedAcceptanceFB" property="unidentifiedBody" onclick="unknownDetail()" tabindex="1" ></html:checkbox>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<B>
											<bean:message key="mlc"/>
										</B>
									</font>
									<html:checkbox name="ExternalDeceasedAcceptanceFB" property="isMlcCase" onclick="mlcDetail()" tabindex="1" ></html:checkbox>
								</div>
							</td>
						</tr>
					</table>
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
							</td>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="firstName"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="middleName"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="lastName"/>
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="deceased"/>
										<bean:message key="name"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="patFirstName" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="patMiddleName" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="patLastName" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="center">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="age" />
									</font>
									<html:radio name="ExternalDeceasedAcceptanceFB" property="isActualDob" tabindex="1" value="0" onclick="ageSelection()" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="dob" />
									</font>
									<html:radio name="ExternalDeceasedAcceptanceFB" property="isActualDob" tabindex="1" value="1" onclick="ageSelection()" />
								</div>
							</td>
							<logic:equal name="ExternalDeceasedAcceptanceFB" property="isActualDob" value="1">
								<%
									strdivage = "none";
									strdivdob = "";
								%>
							</logic:equal>
							<logic:equal name="ExternalDeceasedAcceptanceFB" property="isActualDob"
								value="0">
								<%
									strdivage = "";
									strdivdob = "none";
								%>
							</logic:equal>
							<td width="25%" class="tdfont">
								<div id="divAge" style='display:<%=strdivage%>'>
									<html:text name="ExternalDeceasedAcceptanceFB" size="4" property="patAge" tabindex="1" maxlength="3" onkeypress="return validateNumeric(event)" /> 
									<html:select name="ExternalDeceasedAcceptanceFB" property="patAgeUnit" tabindex="1" styleClass="smallcombo">
										<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>">
											<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>" property="value" labelProperty="label" />
										</logic:present>	
									</html:select>
								</div>
								<div id="divDob" style='display:<%=strdivdob%>'>
									<bean:define name="ExternalDeceasedAcceptanceFB" property="patDOB" id="dob" type="java.lang.String" />
									<div id="divPatDOB"></div>
									<script type="text/javascript">DateValidator.setup("divPatDOB","patDOB","<%=dob%>","dd-Mon-yyyy","textbox");</script>
								</div>
							</td>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="gender" />
									</font>
								</div>
							</td>

							<td width="25%" class="tdfont" >
								<div align="left">
									<html:select name="ExternalDeceasedAcceptanceFB" property="patGenderCode" tabindex="1" styleClass="regcbo">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.ESS_ALL_GENDER_LIST %>">
											<html:options collection="<%=MortuaryConfig.ESS_ALL_GENDER_LIST %>" property="value" labelProperty="label" />
										</logic:present>	
									</html:select>
								</div>	
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="fathersName" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="patGuardianName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
							<td width="25%" class="tdfonthead" >
								<div align="right" id="divMLCNoLabel">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000" >*</font>
										<bean:message key="mlcnumber" />
									</font>
								</div>
								<div align="right" id="divBlankLabel">
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left" id="divMLCNoControl">
									<html:text name="ExternalDeceasedAcceptanceFB" property="mlcNo" maxlength="10" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
								<div align="right" id="divBlankControl">
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="deathdate"/>
									</font>
								</div>
							</td>
							<bean:define name="ExternalDeceasedAcceptanceFB" property="deathDate" id="deathDate" type="java.lang.String"/>  
							<td width="25%" class="tdfont">
								<div align="left">
									<his:date name="deathDate" dateFormate="%d-%b-%Y" value="<%=deathDate %>"/>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="deathtime"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" tabindex="1" property="deathTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
										<b>
											<bean:message key="colon"/>
										</b>
									<html:text name="ExternalDeceasedAcceptanceFB" tabindex="1" property="deathTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('deathTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
									<bean:message key="timeFormat"/>	
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right" id="divIdMark1Comp">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="idMark1"/>
									</font>
								</div>
								<div align="right" id="divIdMark1">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="idMark1"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:textarea name="ExternalDeceasedAcceptanceFB" property="patientIdMark1" rows="1" cols="30" tabindex="1" onkeypress="return (validateTextArea(event,this,'200') && validateAlphaNumericOnly(event,this))" ></html:textarea>
								</div>
							</td>
							
							<td width="25%" class="tdfonthead">
								<div align="right"id="divIdMark2">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="idMark2"/>
									</font>
								</div>
								<div align="right" id="divIdMark2Comp">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="idMark2"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:textarea name="ExternalDeceasedAcceptanceFB" property="patientIdMark2" rows="1" cols="30" tabindex="1"  onkeypress="return (validateTextArea(event,this,'200') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="address1" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="add1" size="33" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
							
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="address2" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="add2" size="33" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="remarks" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" colspan="3" >
								<div align="left">
									<html:textarea name="ExternalDeceasedAcceptanceFB" property="remarks" rows="1" cols="30" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1" ></html:textarea>
								</div>
							</td>
						</tr>
					</table>	
				</his:ContentTag>
				<his:SubTitleTag name="Hospital Detail">
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div align="right" >
									<img id="imgHospitalDetail" tabindex="1" style="cursor: pointer;" src="/../HIS/hisglobal/images/avai/arrow-down.png"; onclick="hideUnhide(this)"/>
								</div>
							</td>
						</tr>
					</table>
				</his:SubTitleTag>
				<div id="divHospitalDetail" style="display: none;">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								
								<td width="25%" class="tdfont" colspan="2">
									<div align="left">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="associated" />
											<bean:message key="hospitaldisees" />
										</font>
										<html:radio name="ExternalDeceasedAcceptanceFB" property="isAssociated" value="<%=MortuaryConfig.YES %>" onclick="showAssociatedHospital()" tabindex="1"></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="otherHospital" />
										</font>
										<html:radio name="ExternalDeceasedAcceptanceFB" property="isAssociated" value="<%=MortuaryConfig.NO %>" onclick="showAssociatedHospital()" tabindex="1"></html:radio>
									</div>
								</td>
							
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="hospname" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left" id="divOtherHospital">
										<html:text name="ExternalDeceasedAcceptanceFB" property="extHospitalName" size="30" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
									<div align="left" id="divAssociatedHospital">
										<html:select name="ExternalDeceasedAcceptanceFB" property="assoHospitalCode" tabindex="1" styleClass="regcbo" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_ASSOCIATED_HOSPITAL_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_ASSOCIATED_HOSPITAL_LIST %>" property="value" labelProperty="label" />
											</logic:present>	
										</html:select>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="crNo" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="extPatCrNo" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="admNo" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="extPatAdmNo" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="deptName" />
											<bean:message key="name" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="extDeptName" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="unit" />
											<bean:message key="name" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="extUnitName" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="ward" />
											<bean:message key="name" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="extWardName" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="bedNo" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="extBedNo" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="doctor" />
											<bean:message key="name" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="doctorName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="hospitalContactNo" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="extHospitalContactNo" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
						</table>	
					</his:ContentTag>
				</div>
				<div id="divPolice" style="display: none;">
					<his:SubTitleTag name="Police Verification Detail">
						<table width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<div align="right" >
										<img id="imgHidePolice" tabindex="1" style="cursor: pointer;" src="/../HIS/hisglobal/images/avai/arrow-down.png"; onclick="hideUnhide(this)"/>
									</div>
								</td>
							</tr>
						</table>
					</his:SubTitleTag>
					<div id="divHidePolice" style="display: none;">
						<his:ContentTag>
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td width="30%" nowrap="nowrap" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="policecaseno" />
											</font>
										</div>	
									</td>
									<td width="20%" nowrap="nowrap" class="tdfont">
										<html:text name="ExternalDeceasedAcceptanceFB"  maxlength="50" property="caseNo" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" />
									</td>
									<td width="30%" nowrap="nowrap" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="policestation" />
											</font>
										</div>	
									</td>
									<td width="20%" nowrap="nowrap" class="tdfont">
										<html:text name="ExternalDeceasedAcceptanceFB"  property="policeStation" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" />
									</td>
								</tr>
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="docketNo" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="ExternalDeceasedAcceptanceFB" property="docketNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</td>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="name" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="ExternalDeceasedAcceptanceFB" property="officerIncharge" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</td>
								</tr>
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="designation" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="ExternalDeceasedAcceptanceFB" property="ioDesignation" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</td>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="officerincharge" />
												<bean:message key="batchno" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="ExternalDeceasedAcceptanceFB" property="ioBatchNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</td>
								</tr>
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="dutyOfficer" />
												<bean:message key="detail" />
											</font>
										</div>	
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
										<html:radio name="ExternalDeceasedAcceptanceFB" property="dutyOfficeFlag" value="<%=MortuaryConfig.DUTY_OFFICER_IS_IO %>" onclick="newDutyOfficerDetail()" tabindex="1" ></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="io" />
										</font>		
										<html:radio name="ExternalDeceasedAcceptanceFB" property="dutyOfficeFlag" value="<%=MortuaryConfig.DUTY_OFFICER_IS_OTHER %>" onclick="newDutyOfficerDetail()" tabindex="1" ></html:radio>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="other" />
										</font>
									</td>
								</tr>
							</table>
							<div id="newDutyOfficerDetailId">
								<table width="100%" cellspacing="1" cellpadding="0">
									<tr>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="name" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%">
											<html:text name="ExternalDeceasedAcceptanceFB" property="dutyOffName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
										</td>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="designation" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%">
											<html:text name="ExternalDeceasedAcceptanceFB" property="dutyOffDesignation" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
										</td>
									</tr>
									<tr>
										<td class="tdfonthead" nowrap="nowrap" width="30%">
											<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="dutyOfficer" />
													<bean:message key="batchno" />
												</font>
											</div>	
										</td>
										<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
											<html:text name="ExternalDeceasedAcceptanceFB" property="dutyOffBatchNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
										</td>
									</tr>
								</table>
							</div>
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td colspan="4">
										<table width="100%" cellpadding="0" cellspacing="1">
											<tr>
												<td nowrap="nowrap" class="tdfonthead" width="30%">
													<div align="right">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
															<font color="#FF0000">*</font>
															<bean:message key="caseremarks" />
														</font>
													</div>	
												</td>
												<td colspan="2" nowrap="nowrap" width="70%" class="tdfont">
													<html:textarea name="ExternalDeceasedAcceptanceFB" tabindex="1" property="caseRemarks" rows="1" cols="90" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</his:ContentTag>
					</div>	
				</div>	
				
				<div id="divRelative" style="display: none;">
					<his:SubTitleTag name="">
						<!--<table width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<div align="right" >
										<img id="imgHideRelative" tabindex="1" style="cursor: pointer;" src="/HISClinical/hisglobal/images/arrow-down.png"; onclick="hideUnhide(this)"/>
									</div>
								</td>
							</tr>
						</table>-->
						<div align="left">
						<html:radio name="ExternalDeceasedAcceptanceFB" property="isClaimed" value="<%=MortuaryConfig.YES %>" onclick="showRelativeDtl()" tabindex="1"></html:radio>
							Accompained By Relative
						<html:radio name="ExternalDeceasedAcceptanceFB" property="isClaimed" value="<%=MortuaryConfig.NO %>" onclick="showRelativeDtl()" tabindex="1"></html:radio>
							Accompained By Police	
						</div>	
					</his:SubTitleTag>
					<div id="divHideRelative" style="display: none;">
						<his:ContentTag>
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right" id="divNonMandatoryRelativeName">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativename"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="ExternalDeceasedAcceptanceFB" property="relativeName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right" id="divNonMandatoryRelativeCode">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="realtionship"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:select name="ExternalDeceasedAcceptanceFB" property="relativeCode" tabindex="1" >
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION%>">
													<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION %>" property = "value" labelProperty = "label"/>
												</logic:present>
											</html:select>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right" id="divNonMandatoryRelativeAdd">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativeaddress"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:textarea name="ExternalDeceasedAcceptanceFB" property="relativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1" ></html:textarea>
										</div>
									</td>
									<td width="25%" class="tdfonthead" >
										<div align="right" id="divNonMandatoryRelativeNo">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativeContactNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="ExternalDeceasedAcceptanceFB" property="relativeContactNo" maxlength="30" onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
										</div>
									</td>
								</tr>
							</table>	
						</his:ContentTag>
					</div>	
					<div id="divBroughtByPolice" style="display: none;">
						<his:ContentTag>
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="dutyOfficer" />
											<bean:message key="name" />
										</font>
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="ExternalDeceasedAcceptanceFB" property="brtOffName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</td>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="dutyOfficer" />
											<bean:message key="designation" />
										</font>
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%">
										<html:text name="ExternalDeceasedAcceptanceFB" property="brtOffDesignation" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</td>
								</tr>
								<tr>
									<td class="tdfonthead" nowrap="nowrap" width="30%">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="dutyOfficer" />
											<bean:message key="batchno" />
										</font>
									</td>
									<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
										<html:text name="ExternalDeceasedAcceptanceFB" property="brtOffBatchNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</td>
								</tr>
								<tr>
									<td width="20%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="PoliceStnDtls"/>
											</font>
										</div>
									</td>
									<td width="30%" class="tdfont">
										<div align="left">
											<html:text name="ExternalDeceasedAcceptanceFB" property="policeStnAccmponiedBy" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
										</div>
									</td>
									<td width="20%" class="tdfonthead">
									<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="PoliceCntctNo"/>
											</font>
										</div>
									</td>
									<td width="30%" class="tdfont">
									<div align="left">
											<html:text name="ExternalDeceasedAcceptanceFB" property="policeContactNo" maxlength="15" onkeypress="return validatePositiveIntegerWithDashOnly(this,event)" tabindex="1"></html:text>
										</div>
									</td>
								</tr>
							</table>	
						</his:ContentTag>
					</div>
				</div>
				
				<div id="divAccRelative" >
					<his:SubTitleTag name="">
						<div align="left">
							<html:checkbox name="ExternalDeceasedAcceptanceFB" property="isAccompained"  onclick="showClaimedRelativeDtl()" tabindex="1"></html:checkbox>
								Is Accompained By Relative
						</div>	
					</his:SubTitleTag>
					<div id="divAccompainedRelativeDtl" style="display: none;">
						<his:ContentTag>
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right" id="divNonMandatoryRelativeName">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativename"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="ExternalDeceasedAcceptanceFB" property="accRelativeName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right" id="divNonMandatoryRelativeCode">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="realtionship"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:select name="ExternalDeceasedAcceptanceFB" property="accRelativeCode" tabindex="1" >
												<html:option value="-1">Select Value</html:option>
												<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION%>">
													<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION %>" property = "value" labelProperty = "label"/>
												</logic:present>
											</html:select>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right" id="divNonMandatoryRelativeAdd">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativeaddress"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:textarea name="ExternalDeceasedAcceptanceFB" property="accRelativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1" ></html:textarea>
										</div>
									</td>
									<td width="25%" class="tdfonthead" >
										<div align="right" id="divNonMandatoryRelativeNo">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<font color="#FF0000">*</font>
												<bean:message key="relativeContactNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<html:text name="ExternalDeceasedAcceptanceFB" property="accRelativeContactNo" maxlength="30" onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
										</div>
									</td>
								</tr>
							</table>	
						</his:ContentTag>
					</div>	
				</div>
				
				<his:SubTitleTag name="">
					<div align="left">
						<html:radio name="ExternalDeceasedAcceptanceFB" property="storageFlag" value="<%=MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER %>" onclick="showHandoverStorage()" tabindex="1" ></html:radio>
						<bean:message key="bodyInStreacher" />
						
						<html:radio name="ExternalDeceasedAcceptanceFB" property="storageFlag" value="<%=MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STORAGE %>" onclick="showHandoverStorage()" tabindex="1" ></html:radio>
						<bean:message key="bodyStorage" />
					</div>
				</his:SubTitleTag>
				
				<div id="divBodyStorageId" style="display: none;">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="chamber"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="ExternalDeceasedAcceptanceFB" property="chamberId" onchange="getRack()" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_CHAMBER %>">
												<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_CHAMBER %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
												<bean:message key="rack"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="ExternalDeceasedAcceptanceFB" property="chamberRackId" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
												<bean:message key="bodyPutBy"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="ExternalDeceasedAcceptanceFB" property="bodyPutBy" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="storageUpto"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="storageUpto" value="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS %>" maxlength="3" size="5" onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="reason"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="storageReason" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
								</td>
								<td width="25%" class="tdfont">
								</td>
							</tr>
						</table>
					</his:ContentTag>
				</div>		
			</logic:equal>
			
			<logic:equal name="ExternalDeceasedAcceptanceFB" property="deceasedFrom" value="<%=MortuaryConfig.ENTRY_MODE_BROUGHT_DEAD_FROM_SPOT_BY_POLICE %>">
				<his:SubTitleTag name="Deceased Detail">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td>
								<div align="right">
		  							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<B>
											<bean:message key="unknown"/>
										</B>
									</font>
									<html:checkbox name="ExternalDeceasedAcceptanceFB" property="unidentifiedBody" onclick="onSpotUnknown()" tabindex="1" ></html:checkbox>
								</div>
							</td>
						</tr>
					</table>
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
							</td>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="firstName"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="middleName"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="lastName"/>
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="deceased"/>
										<bean:message key="name"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="patFirstName" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="patMiddleName" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="patLastName" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="center">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="age" />
									</font>
									<html:radio name="ExternalDeceasedAcceptanceFB" property="isActualDob" tabindex="1" value="0" onclick="ageSelection()" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="dob" />
									</font>
									<html:radio name="ExternalDeceasedAcceptanceFB" property="isActualDob" tabindex="1" value="1" onclick="ageSelection()" />
								</div>
							</td>
							<logic:equal name="ExternalDeceasedAcceptanceFB" property="isActualDob" value="1">
								<%
									strdivage = "none";
									strdivdob = "";
								%>
							</logic:equal>
							<logic:equal name="ExternalDeceasedAcceptanceFB" property="isActualDob"
								value="0">
								<%
									strdivage = "";
									strdivdob = "none";
								%>
							</logic:equal>
							<td width="25%" class="tdfont">
								<div id="divAge" style='display:<%=strdivage%>'>
									<html:text name="ExternalDeceasedAcceptanceFB" size="4" property="patAge" tabindex="1" maxlength="3" onkeypress="return validateNumeric(event)" /> 
									<html:select name="ExternalDeceasedAcceptanceFB" property="patAgeUnit" tabindex="1" styleClass="smallcombo">
										<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>">
											<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>" property="value" labelProperty="label" />
										</logic:present>	
									</html:select>
								</div>
								<div id="divDob" style='display:<%=strdivdob%>'>
									<bean:define name="ExternalDeceasedAcceptanceFB" property="patDOB" id="dob" type="java.lang.String" />
									<div id="divPatDOB"></div>
									<script type="text/javascript">DateValidator.setup("divPatDOB","patDOB","<%=dob%>","dd-Mon-yyyy","textbox");</script>
								</div>
							</td>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="gender" />
									</font>
								</div>
							</td>

							<td width="25%" class="tdfont" >
								<div align="left">
									<html:select name="ExternalDeceasedAcceptanceFB" property="patGenderCode" tabindex="1" styleClass="regcbo">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.ESS_ALL_GENDER_LIST %>">
											<html:options collection="<%=MortuaryConfig.ESS_ALL_GENDER_LIST %>" property="value" labelProperty="label" />
										</logic:present>	
									</html:select>
								</div>	
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="fathersName" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" colspan="3">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="patGuardianName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
							
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="deathdate"/>
									</font>
								</div>
							</td>
							<bean:define name="ExternalDeceasedAcceptanceFB" property="deathDate" id="deathDate" type="java.lang.String"/>  
							<td width="25%" class="tdfont">
								<div align="left">
									<his:date name="deathDate" dateFormate="%d-%b-%Y" value="<%=deathDate %>"/>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="deathtime"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" tabindex="1" property="deathTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
										<b>
											<bean:message key="colon"/>
										</b>
									<html:text name="ExternalDeceasedAcceptanceFB" tabindex="1" property="deathTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('deathTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
									<bean:message key="timeFormat"/>	
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right" id="divIdMark1Comp">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="idMark1"/>
									</font>
								</div>
								<div align="right" id="divIdMark1">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="idMark1"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:textarea name="ExternalDeceasedAcceptanceFB" property="patientIdMark1" rows="1" cols="30" tabindex="1" onkeypress="return (validateTextArea(event,this,'200') && validateAlphaNumericOnly(event,this))" ></html:textarea>
								</div>
							</td>
							
							<td width="25%" class="tdfonthead">
								<div align="right"id="divIdMark2">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="idMark2"/>
									</font>
								</div>
								<div align="right" id="divIdMark2Comp">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="idMark2"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:textarea name="ExternalDeceasedAcceptanceFB" property="patientIdMark2" rows="1" cols="30" tabindex="1"  onkeypress="return (validateTextArea(event,this,'200') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="address1" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="add1" size="33" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
							
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="address2" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="add2" size="33" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="remarks" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" colspan="3" >
								<div align="left">
									<html:textarea name="ExternalDeceasedAcceptanceFB" property="remarks" rows="1" cols="30" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1" ></html:textarea>
								</div>
							</td>
						</tr>
					</table>	
				</his:ContentTag>
				<his:SubTitleTag name="Police Verification Detail">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr>
							<td width="30%" nowrap="nowrap" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="policecaseno" />
									</font>
								</div>	
							</td>
							<td width="20%" nowrap="nowrap" class="tdfont">
								<html:text name="ExternalDeceasedAcceptanceFB"  maxlength="50" property="caseNo" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" />
							</td>
							<td width="30%" nowrap="nowrap" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="policestation" />
									</font>
								</div>	
							</td>
							<td width="20%" nowrap="nowrap" class="tdfont">
								<html:text name="ExternalDeceasedAcceptanceFB" property="policeStation" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" />
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="30%">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="docketNo" />
									</font>
								</div>	
							</td>
							<td class="tdfont" nowrap="nowrap" width="20%">
								<html:text name="ExternalDeceasedAcceptanceFB" property="docketNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
							</td>
							<td class="tdfonthead" nowrap="nowrap" width="30%">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="officerincharge" />
										<bean:message key="name" />
									</font>
								</div>	
							</td>
							<td class="tdfont" nowrap="nowrap" width="20%">
								<html:text name="ExternalDeceasedAcceptanceFB" property="officerIncharge" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="30%">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="officerincharge" />
										<bean:message key="designation" />
									</font>
								</div>	
							</td>
							<td class="tdfont" nowrap="nowrap" width="20%">
								<html:text name="ExternalDeceasedAcceptanceFB" property="ioDesignation" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
							</td>
							<td class="tdfonthead" nowrap="nowrap" width="30%">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="officerincharge" />
										<bean:message key="batchno" />
									</font>
								</div>	
							</td>
							<td class="tdfont" nowrap="nowrap" width="20%">
								<html:text name="ExternalDeceasedAcceptanceFB" property="ioBatchNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" nowrap="nowrap" width="30%">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="dutyOfficer" />
										<bean:message key="detail" />
									</font>
								</div>	
							</td>
							<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
								<html:radio name="ExternalDeceasedAcceptanceFB" property="dutyOfficeFlag" value="<%=MortuaryConfig.DUTY_OFFICER_IS_IO %>" onclick="newDutyOfficerDetail()" tabindex="1" ></html:radio>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="io" />
								</font>		
								<html:radio name="ExternalDeceasedAcceptanceFB" property="dutyOfficeFlag" value="<%=MortuaryConfig.DUTY_OFFICER_IS_OTHER %>" onclick="newDutyOfficerDetail()" tabindex="1" ></html:radio>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="other" />
								</font>
							</td>
						</tr>
					</table>
					<div id="newDutyOfficerDetailId">
						<table width="100%" cellspacing="1" cellpadding="0">
							<tr>
								<td class="tdfonthead" nowrap="nowrap" width="30%">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="dutyOfficer" />
											<bean:message key="name" />
										</font>
									</div>	
								</td>
								<td class="tdfont" nowrap="nowrap" width="20%">
									<html:text name="ExternalDeceasedAcceptanceFB" property="dutyOffName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</td>
								<td class="tdfonthead" nowrap="nowrap" width="30%">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="dutyOfficer" />
											<bean:message key="designation" />
										</font>
									</div>	
								</td>
								<td class="tdfont" nowrap="nowrap" width="20%">
									<html:text name="ExternalDeceasedAcceptanceFB" property="dutyOffDesignation" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</td>
							</tr>
							<tr>
								<td class="tdfonthead" nowrap="nowrap" width="30%">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="dutyOfficer" />
											<bean:message key="batchno" />
										</font>
									</div>	
								</td>
								<td class="tdfont" nowrap="nowrap" width="20%" colspan="3">
									<html:text name="ExternalDeceasedAcceptanceFB" property="dutyOffBatchNo" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</td>
							</tr>
							
						</table>
					</div>
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr>
							<td width="30%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000" >*</font>
										<bean:message key="broughtFromLoacation" />
									</font>
								</div>
							</td>
							<td width="70%" class="tdfont" colspan="3">
								<div align="left">
									<html:textarea name="ExternalDeceasedAcceptanceFB" property="broughtLocation" rows="1" cols="90" tabindex="1" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" ></html:textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table width="100%" cellpadding="0" cellspacing="1">
									<tr>
										<td nowrap="nowrap" class="tdfonthead" width="30%">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<font color="#FF0000">*</font>
													<bean:message key="caseremarks" />
												</font>
											</div>	
										</td>
										<td colspan="2" nowrap="nowrap" width="70%" class="tdfont">
											<html:textarea name="ExternalDeceasedAcceptanceFB" tabindex="1" property="caseRemarks" rows="1" cols="90" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event,this))" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</his:ContentTag>
				
				<his:SubTitleTag name="Relative Detail">
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div align="right" >
									<img id="imgHideRelative" tabindex="1" style="cursor: pointer;" src="/../HIS/hisglobal/images/avai/arrow-down.png"; onclick="hideUnhide(this)"/>
								</div>
							</td>
						</tr>
					</table>
				</his:SubTitleTag>
				<div id="divHideRelative" style="display: none;">
					<his:ContentTag>
						<table width="100%" cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="relativename"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="relativeName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="realtionship"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="ExternalDeceasedAcceptanceFB" property="relativeCode" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION%>">
												<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="relativeaddress"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:textarea name="ExternalDeceasedAcceptanceFB" property="relativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1" ></html:textarea>
									</div>
								</td>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="relativeContactNo"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="relativeContactNo" maxlength="30" onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
						</table>	
					</his:ContentTag>
				</div>
				
				<his:SubTitleTag name="">
					<div align="left">
						<html:radio name="ExternalDeceasedAcceptanceFB" property="storageFlag" value="<%=MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER %>" onclick="showHandoverStorage()" tabindex="1" ></html:radio>
						<bean:message key="bodyInStreacher" />
						
						<html:radio name="ExternalDeceasedAcceptanceFB" property="storageFlag" value="<%=MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STORAGE %>" onclick="showHandoverStorage()" tabindex="1" ></html:radio>
						<bean:message key="bodyStorage" />
					</div>
				</his:SubTitleTag>
				
				<div id="divBodyStorageId" style="display: none;">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="chamber"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="ExternalDeceasedAcceptanceFB" property="chamberId" onchange="getRack()" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_CHAMBER %>">
												<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_CHAMBER %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
												<bean:message key="rack"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="ExternalDeceasedAcceptanceFB" property="chamberRackId" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
												<bean:message key="bodyPutBy"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="ExternalDeceasedAcceptanceFB" property="bodyPutBy" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="storageUpto"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="storageUpto" value="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS %>" maxlength="3" size="5" onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="reason"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="storageReason" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
								</td>
								<td width="25%" class="tdfont">
								</td>
							</tr>
						</table>
					</his:ContentTag>
				</div>
			</logic:equal>
			
			<logic:equal name="ExternalDeceasedAcceptanceFB" property="deceasedFrom" value="<%=MortuaryConfig.ENTRY_MODE_SEND_FROM_OTHER_PLACES %>">
				<his:SubTitleTag name="Deceased Detail">
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					</table>
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
							</td>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="firstName"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="middleName"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="lastName"/>
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="deceased"/>
										<bean:message key="name"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="patFirstName" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="patMiddleName" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="patLastName" maxlength="30" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="center">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="age" />
									</font>
									<html:radio name="ExternalDeceasedAcceptanceFB" property="isActualDob" tabindex="1" value="0" onclick="ageSelection()" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="dob" />
									</font>
									<html:radio name="ExternalDeceasedAcceptanceFB" property="isActualDob" tabindex="1" value="1" onclick="ageSelection()" />
								</div>
							</td>
							<logic:equal name="ExternalDeceasedAcceptanceFB" property="isActualDob" value="1">
								<%
									strdivage = "none";
									strdivdob = "";
								%>
							</logic:equal>
							<logic:equal name="ExternalDeceasedAcceptanceFB" property="isActualDob"
								value="0">
								<%
									strdivage = "";
									strdivdob = "none";
								%>
							</logic:equal>
							<td width="25%" class="tdfont">
								<div id="divAge" style='display:<%=strdivage%>'>
									<html:text name="ExternalDeceasedAcceptanceFB" size="4" property="patAge" tabindex="1" maxlength="3" onkeypress="return validateNumeric(event)" /> 
									<html:select name="ExternalDeceasedAcceptanceFB" property="patAgeUnit" tabindex="1" styleClass="smallcombo">
										<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>">
											<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>" property="value" labelProperty="label" />
										</logic:present>	
									</html:select>
								</div>
								<div id="divDob" style='display:<%=strdivdob%>'>
									<bean:define name="ExternalDeceasedAcceptanceFB" property="patDOB" id="dob" type="java.lang.String" />
									<div id="divPatDOB"></div>
									<script type="text/javascript">DateValidator.setup("divPatDOB","patDOB","<%=dob%>","dd-Mon-yyyy","textbox");</script>
								</div>
							</td>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="gender" />
									</font>
								</div>
							</td>

							<td width="25%" class="tdfont" >
								<div align="left">
									<html:select name="ExternalDeceasedAcceptanceFB" property="patGenderCode" tabindex="1" styleClass="regcbo">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.ESS_ALL_GENDER_LIST %>">
											<html:options collection="<%=MortuaryConfig.ESS_ALL_GENDER_LIST %>" property="value" labelProperty="label" />
										</logic:present>	
									</html:select>
								</div>	
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="fathersName" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" colspan="3">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="patGuardianName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
								</div>
							</td>
							
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="deathdate"/>
									</font>
								</div>
							</td>
							<bean:define name="ExternalDeceasedAcceptanceFB" property="deathDate" id="deathDate" type="java.lang.String"/>  
							<td width="25%" class="tdfont">
								<div align="left">
									<his:date name="deathDate" dateFormate="%d-%b-%Y" value="<%=deathDate %>"/>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="deathtime"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" tabindex="1" property="deathTimeHr" maxlength="2" size="3" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)"/>
										<b>
											<bean:message key="colon"/>
										</b>
									<html:text name="ExternalDeceasedAcceptanceFB" tabindex="1" property="deathTimeMin" maxlength="2" size="3" onkeypress="return onkeyTimeMin(this,document.getElementsByName('deathTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)"/>
									<bean:message key="timeFormat"/>	
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right" id="divIdMark1">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="idMark1"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:textarea name="ExternalDeceasedAcceptanceFB" property="patientIdMark1" rows="1" cols="30" tabindex="1" onkeypress="return (validateTextArea(event,this,'200') && validateAlphaNumericOnly(event,this))" ></html:textarea>
								</div>
							</td>
							
							<td width="25%" class="tdfonthead">
								<div align="right"id="divIdMark2">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="idMark2"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:textarea name="ExternalDeceasedAcceptanceFB" property="patientIdMark2" rows="1" cols="30" tabindex="1"  onkeypress="return (validateTextArea(event,this,'200') && validateAlphaNumericOnly(event,this))"></html:textarea>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="address1" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="add1" size="33" maxlength="100" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1" ></html:text>
								</div>
							</td>
							
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="address2" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" >
								<div align="left">
									<html:text name="ExternalDeceasedAcceptanceFB" property="add2" size="33" maxlength="100" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1" ></html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead" >
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="remarks" />
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" colspan="3" >
								<div align="left">
									<html:textarea name="ExternalDeceasedAcceptanceFB" property="remarks" rows="1" cols="30" onkeypress="return validateAlphaNumericOnly(event,this)" tabindex="1" ></html:textarea>
								</div>
							</td>
						</tr>
					</table>	
				</his:ContentTag>			
				<his:SubTitleTag name="Hospital Detail">
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div align="right" >
									<img id="imgHospitalDetail" tabindex="1" style="cursor: pointer;" src="/../HIS/hisglobal/images/avai/arrow-down.png"; onclick="hideUnhide(this)"/>
								</div>
							</td>
						</tr>
					</table>
				</his:SubTitleTag>
				<div id="divHospitalDetail" style="display: none;">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								
							
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="placeType" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left" id="divOtherHospital">
										<html:text name="ExternalDeceasedAcceptanceFB" property="placeType" size="30" maxlength="100" onkeypress="return validateAlphaNumericOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="UniqueID" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left" id="divOtherHospital">
										<html:text name="ExternalDeceasedAcceptanceFB" property="patID" maxlength="30" onkeypress="return validateAlphaNumericOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="PlcAddrs" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="plcAddress" maxlength="50" onkeypress="return validateAlphaNumericOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="hospitalContactNo" />
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="plcContact" maxlength="30" onkeypress="return  validatePositiveIntegerWithDashOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							
							
						</table>	
					</his:ContentTag>
				</div>
				
				<his:SubTitleTag name="Relative Detail">
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<div align="right" >
									<img id="imgHideRelative" tabindex="1" style="cursor: pointer;" src="/../HIS/hisglobal/images/avai/arrow-down.png"; onclick="hideUnhide(this)"/>
								</div>
							</td>
						</tr>
					</table>
				</his:SubTitleTag>
				<div id="divHideRelative" style="display: none;">
					<his:ContentTag>
						<table width="100%" cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="relativename"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="relativeName" maxlength="60" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="realtionship"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="ExternalDeceasedAcceptanceFB" property="relativeCode" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION%>">
												<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_PATIENT_RELATION %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="relativeaddress"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:textarea name="ExternalDeceasedAcceptanceFB" property="relativeAddress" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1" ></html:textarea>
									</div>
								</td>
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="relativeContactNo"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="relativeContactNo" maxlength="30" onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
						</table>	
					</his:ContentTag>
				</div>
				
				<his:SubTitleTag name="">
					<div align="left">
						<html:radio name="ExternalDeceasedAcceptanceFB" property="storageFlag" value="<%=MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER %>" onclick="showHandoverStorage()" tabindex="1" ></html:radio>
						<bean:message key="bodyInStreacher" />
						
						<html:radio name="ExternalDeceasedAcceptanceFB" property="storageFlag" value="<%=MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STORAGE %>" onclick="showHandoverStorage()" tabindex="1" ></html:radio>
						<bean:message key="bodyStorage" />
					</div>
				</his:SubTitleTag>
				
				<div id="divBodyStorageId" style="display: none;">
					<his:ContentTag>
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="chamber"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="ExternalDeceasedAcceptanceFB" property="chamberId" onchange="getRack()" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_CHAMBER %>">
												<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_CHAMBER %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
												<bean:message key="rack"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="ExternalDeceasedAcceptanceFB" property="chamberRackId" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
												<bean:message key="bodyPutBy"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="ExternalDeceasedAcceptanceFB" property="bodyPutBy" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="storageUpto"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="storageUpto" value="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS %>" maxlength="3" size="5" onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="reason"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="ExternalDeceasedAcceptanceFB" property="storageReason" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
								</td>
								<td width="25%" class="tdfont">
								</td>
							</tr>
						</table>
					</his:ContentTag>
				</div>
			</logic:equal>
			
			
			<html:hidden name="ExternalDeceasedAcceptanceFB" property="sysDate" value="<%=sysDate %>"/>
		</his:statusTransactionInProcess>	
			
			<his:ButtonToolBarTag>
				<logic:equal name="ExternalDeceasedAcceptanceFB" property="deceasedFrom" value="<%=MortuaryConfig.ENTRY_MODE_SEND_FROM_OTHER_HOSPITAL %>">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateOtherHospitalSave()) submitPage('SAVEOTHHOSP')" onkeypress="if(event.keyCode==13)if(validateOtherHospitalSave())submitPage('SAVEOTHHOSP')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearOtherHospForm()" onkeypress="if(event.keyCode==13)clearOtherHospForm()">
				</logic:equal>
				
				<logic:equal name="ExternalDeceasedAcceptanceFB" property="deceasedFrom" value="<%=MortuaryConfig.ENTRY_MODE_BROUGHT_DEAD_FROM_SPOT_BY_POLICE %>">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateOnSpotSave()) submitPage('SAVEONSPOT')" onkeypress="if(event.keyCode==13)if(validateOnSpotSave())submitPage('SAVEONSPOT')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearOnSpotForm()" onkeypress="if(event.keyCode==13)clearOnSpotForm()">
				</logic:equal>
				
				<logic:equal name="ExternalDeceasedAcceptanceFB" property="deceasedFrom" value="<%=MortuaryConfig.ENTRY_MODE_SEND_FROM_OTHER_PLACES %>">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateOtherPlcSave()) submitPage('SAVEOTHPLC')" onkeypress="if(event.keyCode==13)if(validateOtherPlcSave())submitPage('SAVEOTHPLC')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearOnSpotForm()" onkeypress="if(event.keyCode==13)clearOnSpotForm()">
				</logic:equal>
				
				
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
			</his:ButtonToolBarTag>
			
		</his:TransactionContainer>
		
		<html:hidden name="ExternalDeceasedAcceptanceFB" property="hmode"/>
		<html:hidden name="ExternalDeceasedAcceptanceFB" property="patCrNo"/>
		<html:hidden name="ExternalDeceasedAcceptanceFB" property="unknownChkValue"/>
		<html:hidden name="ExternalDeceasedAcceptanceFB" property="mlcChkValue"/>
		<html:hidden name="ExternalDeceasedAcceptanceFB" property="deceasedFrom"/>
		<html:hidden name="ExternalDeceasedAcceptanceFB" property="hiddenTimeHr"/>
		<html:hidden name="ExternalDeceasedAcceptanceFB" property="hiddenTimeMin"/>
		
	</html:form>
	<his:status/>
</body>			

</html>