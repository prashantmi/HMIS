<%@page autoFlush="true" %>
<!-- 
/**
 * @author CDAC
 */
-->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@ page import="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<logic:equal name="CsultyEmgMlcDtlFB" property="isDirectCall" value="DIRECT">
		
		<his:css src="/hisglobal/css/Color.css"/>
		<his:css src="/hisglobal/css/master.css"/>
		<his:css src="/hisglobal/css/hisStyle.css"/>
		<his:css src="/hisglobal/css/hisStyleExt.css"/>
		<his:css src="/hisglobal/css/calendar-blue2.css"/>
		
		<his:javascript src="/registration/js/popup.js" />
		<his:javascript src="/registration/js/calendar.js" />
		<his:javascript src="/registration/js/registration.js" />
		<his:javascript src="/registration/js/dateFunctions.js" />
		<his:javascript src="/registration/js/validationCalls.js" />
		<his:javascript src="/registration/js/commonFunctions.js" />
		<his:javascript src="/registration/js/validationCommon.js" />
		<his:javascript src="/opd/opdJs/opd.js"/>

</logic:equal>

<script>
function validatePercentage(obj){
var flag=true;

if(obj.value > 100){
	alert("Burn Percentage Cannot be Greater than 100 Percent");
	document.getElementsByName("burnPercentage")[0].focus();
	
	flag=false;
	}
	
	
	return flag;
}

function broughtBy(obj)
{
	if(obj){
		if(obj.checked==true)
			document.getElementById("broughtById").style.display="";
		else
			document.getElementById("broughtById").style.display="none";
	}		
}

function enableRelation(obj)
{
	if(obj.value=="1")
	{
		document.getElementsByName("broughtByRelationCode")[0].disabled=false;
		document.getElementById("policeBroughtById").style.display="none";
	}
	else
	{
		if(obj.value=="2")
		{
			document.getElementsByName("broughtByRelationCode")[0].disabled=true;
			document.getElementById("policeBroughtById").style.display="block";
		}
		else
		{
			document.getElementsByName("broughtByRelationCode")[0].disabled=true;
			document.getElementById("policeBroughtById").style.display="none";
		}
	}	
	
	
}

function submit4image()
{
	submitTile("REFRESHFORIMAGE");
}

function submitTile(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function callThisOnload()
{
	if(document.getElementsByName('patCrNo')[0]){
		document.getElementsByName('patCrNo')[0].focus()
	}
	if(document.getElementsByName('cmoType')[0]!=null)
	{
		document.getElementsByName('cmoType')[0].checked=true;
		showdivEmployee(document.getElementsByName('cmoType')[0]);
	}
	broughtBy(document.getElementsByName("isBroughtBy")[0]);
	enableRelation(document.getElementsByName("isRelative")[0]);
	focusCrNo();		
}

function populate(e)
{
	document.getElementsByName('crNoToRetrieve')[0].value=e;
	submitForm("DGNDETAIL");
}
function validateMlcDateTime()
{
	valid=true;
	if( validateMlcSysDate())
	{
		valid=validateMlcEpiDate();
		
	}
	else
	{
		valid=false;
	}	
	return valid;
}
function validateMLC()
{

	if( !(validateObjects(document.forms[0].patMlcNo,"MLC Number") && validateMlcDate(document.forms[0].mlcDate,"MLC Date")) )		
		return false;
	
	if(!validateMlcDateTime())
	{
		return false;
	}
	
	/*if(document.getElementsByName("mlcTimeHr")[0].value=="")
	{
		alert("Enter Patient MLC Time Hour ...");
		document.getElementsByName("mlcTimeHr")[0].focus();
		return false;
	}
	if(document.getElementsByName("mlcTimeMin")[0].value=="")
	{
		alert("Enter Patient MLC Time Minute ...");
		document.getElementsByName("mlcTimeMin")[0].focus();
		return false;
	}*/
	onblurTimeHourCheck(document.getElementsByName("mlcTimeHr")[0]);
	onblurTimeMinCheck(document.getElementsByName("mlcTimeMin")[0]);
	document.getElementsByName("mlcTime")[0].value=document.getElementsByName("mlcTimeHr")[0].value+":"+document.getElementsByName("mlcTimeMin")[0].value;
	
	if(!(validateObjects(document.forms[0].injuryTypeCode,"Type of Injury")&&
		validateObjects(document.forms[0].patStatusCode,"Patient Status")  &&
		validatePercentage(document.getElementsByName("burnPercentage")[0]) &&
		validatePoliceVerification() &&  validateBroughtBy()) )		
		return false;
		
		
		
	return true;
}

function validatePoliceVerification()
{
var valid=true
var verificationRequired=<%=Config.MLC_DETAIL_POLICE_VERIFICATION_REQUIRED %>
var verificationRequiredYes=<%=Config.MLC_DETAIL_POLICE_VERIFICATION_REQUIRED_YES %>
if(verificationRequired==verificationRequiredYes)
{
		if(document.getElementsByName('isBroughtByPolice')[0].checked==true)
			{
		
				if(validateObjects(document.forms[0].caseNo,"Case Number") &&
				validateObjects(document.forms[0].policeStation,"Police Station") &&
				validateObjects(document.forms[0].docketNo,"Docket No") &&
				validateObjects(document.forms[0].officerIncharge,"Investigating Ofiicer Name") &&
				validateObjects(document.forms[0].ioDesignation,"Investigating Ofiicer Designation") &&
				validateObjects(document.forms[0].ioBatchNo,"Investigating Ofiicer Badge No") &&
				validateDutyDetail()&& 
				validateObjects(document.forms[0].caseRemarks,"Case Remark"))
				{
					valid= true;
				}	
				else
				{
					valid= false;
				}
			}
}	

	return valid
		
}

function parseInteger(val)
{
	if(val.length<1 || !(/^[0-9]*$/.test(val)))
		return 0;
	do
	{
		if(val.length==1)	return parseInt(val);
		if(/^0/.test(val))	val=val.substring(1);
		else				return parseInt(val);
	} while(true);
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

function addPatientCondition(event,id)
{
	var path='/HISClinical/opd/casuality/casualmlcDtl.cnt?hmode=ADDPATCONDITION&processId='+id;
	openPopup(path,event,300,600);
}

function DutyOfficerDetail()
{
	if(document.getElementsByName("dutyOfficeFlag")[1].checked)
	{
		document.getElementById('dutyOfficerDetailId').style.display="block";
	}
	else
	{
		document.getElementById('dutyOfficerDetailId').style.display="none";
	}
}

function handOverDetail()
{
	if(document.getElementsByName("handoverToFlag")[2].checked)
	{
		document.getElementById('handOvereDetailId').style.display="block";
	}
	else
	{
		document.getElementById('handOvereDetailId').style.display="none";
	}
}

function validateDutyDetail()
{
	valid=true;
	if(document.getElementsByName("dutyOfficeFlag")[1].checked)
	{
		if(validateObjects(document.forms[0].dutyOffName,"Duty Officer Name") &&
			validateObjects(document.forms[0].dutyOffDesignation,"Duty Officer Designation") &&
			validateObjects(document.forms[0].dutyOffBatchNo,"Duty Officer Badge No"))
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

function validateHandOverToDetail()
{
	valid=true;
	if(document.getElementsByName("handoverToFlag")[2].checked)
	{
		if(validateObjects(document.forms[0].handoverToName,"Handover To Name") &&
			validateObjects(document.forms[0].handoverToDesignation,"Handover To Designation") &&
			validateObjects(document.forms[0].handoverToBatchNo,"Handover To Badge No"))
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

function enableBroughtBy()
{
	if(document.getElementsByName("isBroughtByPolice")[0].checked)
	{
		document.getElementsByName("isBroughtBy")[0].checked=true;
		document.getElementsByName("isRelative")[0].value="2"
		document.getElementsByName("isRelative")[0].disabled=true;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
		enableRelation(document.getElementsByName("isRelative")[0]);
	}
	else
	{
		document.getElementsByName("isBroughtBy")[0].checked=false;
		document.getElementsByName("isRelative")[0].value="-1"
		document.getElementsByName("isRelative")[0].disabled=false;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
		enableRelation(document.getElementsByName("isRelative")[0]);
	}
}

function validateMlcSysDate()
{
	valid=true;
	var days=0;
	var a=document.getElementsByName("mlcDate")[0].value;
    var aArray=a.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
      
    var b=document.getElementsByName("sysDate")[0].value; 
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
    
    var c=document.getElementsByName("epiDate")[0].value; 
    var cArray=c.split("-");
     
    var cday=cArray[0];
    var cmonth=cArray[1];
    var cyear=cArray[2];
    var cdate=new Date(cmonth +" "+ cday+" "+cyear);
      
    
    days=((bdate-adate)/86400000);
    
    if(days==0)
    {
    	valid=checkMlcSysTime();
    }
    else
    {
    	if(days<0)
    	{
    		alert("MLC Date cannot be Greater than Today");
    		valid=false;
    	}
    	else
    	{
    		valid=true;
    	}
    }
    
    
    
    return valid;
}

function validateMlcEpiDate()
{
	valid=true;
	var day=0;
	var a=document.getElementsByName("mlcDate")[0].value;
    var aArray=a.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
    
     var c=document.getElementsByName("epiDate")[0].value; 
    var cArray=c.split("-");
     
    var cday=cArray[0];
    var cmonth=cArray[1];
    var cyear=cArray[2];
    var cdate=new Date(cmonth +" "+ cday+" "+cyear);
    
     day=(adate-cdate)/86400000;
    if(day==0)
    {
    	valid=checkMlcEpiTime();
    }
    else
    {
    	if(day<0)
    	{
    		alert("MLC Date cannot be Less than Patient Visit Date");
    		valid=false;
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

function checkMlcSysTime()
{
	valid=true;
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var mlcDate=document.getElementsByName("mlcDate")[0].value;
	var epiDate=document.getElementsByName("epiDate")[0].value; 
	var sysDate=document.getElementsByName("sysDate")[0].value; 
	
	if(document.getElementsByName("mlcTimeHr")[0].value=="")
	{
		alert("Please Enter the MLC Hour");
		document.getElementsByName("mlcTimeHr")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeHr")[0].value)) > hour)
	{
		alert("MLC Date Cannot be Greater than "+sysDate+" "+hour+":"+min);
		document.getElementsByName("mlcTimeHr")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("mlcTimeMin")[0].value=="")
	{
		alert("Please Enter the MLC Minute");
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeMin")[0].value)) > parseInt("59"))
	{
		alert("Minute Cannot be Greater than 59");
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("mlcTimeMin")[0].value)) > min)
	{
		alert("MLC Date Cannot be Greater than "+sysDate+" "+hour+":"+ (min.length==1?"0"+min:min));
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	return valid;
}

function checkMlcEpiTime()
{
	valid=true;
	var hour=parseInt(trimNum(document.getElementsByName("epiTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("epiTimeMin")[0].value));
	var mlcDate=document.getElementsByName("mlcDate")[0].value;
	var epiDate=document.getElementsByName("epiDate")[0].value; 
	var sysDate=document.getElementsByName("sysDate")[0].value; 
	
	if(document.getElementsByName("mlcTimeHr")[0].value=="")
	{
		alert("Please Enter the MLC Hour");
		document.getElementsByName("mlcTimeHr")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeHr")[0].value)) < hour)
	{
		alert("MLC Date Cannot be Less than "+epiDate+" "+hour+":"+(min.length==1?"0"+min:min));
		document.getElementsByName("mlcTimeHr")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("mlcTimeMin")[0].value=="")
	{
		alert("Please Enter the MLC Minute");
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeMin")[0].value)) > parseInt("59"))
	{
		alert("Minute Cannot be Greater than 59");
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeHr")[0].value)) <= hour && parseInt(trimNum(document.getElementsByName("mlcTimeMin")[0].value)) < min)
	{
		alert("MLC Date Cannot be Less than "+epiDate+" "+hour+":"+(min.length==1?"0"+min:min));
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	return valid;
	
}

function validateBroughtBy()
{
 	if(document.forms[0].isBroughtBy.checked)
 	{
	 	if(comboValidation(document.forms[0].isRelative,"Brought By") &&
	 	isEmpty(document.getElementsByName('broughtByName')[0],'Brought By Name') &&
	 	isEmpty(document.getElementsByName('broughtByLocation')[0],'Brought From Location') && 
	 	validateBroughtByPolice())
	 	{
	 		return true
	 	}
	 	else
	 	{
	 		return false
	 	}
	 }
	 else
	 {
	 	return true
	 }
 }
 
function validateBroughtByPolice()
 {
 	valid=true
 	if(document.forms[0].isRelative.value== <%=RegistrationConfig.PATIENT_BROUGHT_BY_POLICE %>)
 	{
 		if(isEmpty(document.getElementsByName('constableDesig')[0],'Designation' ) &&
 		isEmpty(document.getElementsByName('constableBadgeNo')[0],'Batch No.'))
 		{
 			valid=true
 		}
 		else
 		{
 			valid=false
 		}
 	}
 	return valid
 } 
 
 function enableBlanket()
{

	document.getElementById("blanket").style.display="block";
	document.getElementById("burnImage").style.display="block";
	
	
	
}
function disableBlanket()
{

	document.getElementById("blanket").style.display="none";
	document.getElementById("burnImage").style.display="none";
	
}
</script>

<his:TransactionContainer>
<logic:equal name="CsultyEmgMlcDtlFB" property="isDirectCall" value="DIRECT">
	<form action="/HISClinical/opd/casuality/casualmlcDtlPGI.cnt" method="post">
</logic:equal>

<%
	String varStatus="";
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>

<html><head><link href="/HISClinical/hisglobal/css/Color.css" rel="stylesheet" type="text/css">
		<link href="/HISClinical/hisglobal/css/master.css" rel="stylesheet" type="text/css">
		<link href="/HISClinical/hisglobal/css/hisStyle.css" rel="stylesheet" type="text/css">
		<link href="/HISClinical/hisglobal/css/hisStyleExt.css" rel="stylesheet" type="text/css">
		<link href="/HISClinical/hisglobal/css/calendar-blue2.css" rel="stylesheet" type="text/css">
		
		<script language="javaScript" src="/HISClinical/registration/js/popup.js"></script>
		<script language="javaScript" src="/HISClinical/registration/js/calendar.js"></script>
		<script language="javaScript" src="/HISClinical/registration/js/registration.js"></script>
		<script language="javaScript" src="/HISClinical/registration/js/dateFunctions.js"></script>
		<script language="javaScript" src="/HISClinical/registration/js/validationCalls.js"></script>
		<script language="javaScript" src="/HISClinical/registration/js/commonFunctions.js"></script>
		<script language="javaScript" src="/HISClinical/registration/js/validationCommon.js"></script>
		<script language="javaScript" src="/HISClinical/opd/opdJs/opd.js"></script>



<script>
function validatePercentage(obj){
var flag=true;

if(obj.value > 100){
	alert("Burn Percentage Cannot be Greater than 100 Percent");
	document.getElementsByName("burnPercentage")[0].focus();
	
	flag=false;
	}
	
	
	return flag;
}

function broughtBy(obj)
{
	if(obj){
		if(obj.checked==true)
			document.getElementById("broughtById").style.display="";
		else
			document.getElementById("broughtById").style.display="none";
	}		
}

function enableRelation(obj)
{
	if(obj.value=="1")
	{
		document.getElementsByName("broughtByRelationCode")[0].disabled=false;
		document.getElementById("policeBroughtById").style.display="none";
	}
	else
	{
		if(obj.value=="2")
		{
			document.getElementsByName("broughtByRelationCode")[0].disabled=true;
			document.getElementById("policeBroughtById").style.display="block";
		}
		else
		{
			document.getElementsByName("broughtByRelationCode")[0].disabled=true;
			document.getElementById("policeBroughtById").style.display="none";
		}
	}	
	
	
}
function enableWeapontype(obj)
{
	if(obj.value=="1")
	{
		
			document.getElementsByName("typeOfWeapon")[0].style.visibility="visible";
			document.getElementsByName("toW")[0].style.visibility="visible";
			document.getElementsByName("burnper")[0].style.visibility="hidden";
			document.getElementsByName("burnPercentage")[0].style.visibility="hidden";
			document.getElementsByName("addimage")[0].style.visibility="hidden";
			document.getElementsByName("burndegree")[0].style.visibility="hidden";
			document.getElementsByName("doburn")[0].style.visibility="hidden";
		
	}
	else
	{
		if(obj.value=="2")
		{
			document.getElementsByName("top")[0].style.visibility="visible";
			document.getElementsByName("sop")[0].style.visibility="visible";
			document.getElementsByName("poisonsubstance")[0].style.visibility="visible";
			document.getElementsByName("typeofpoisoning")[0].style.visibility="visible";
			document.getElementsByName("typeOfWeapon")[0].style.visibility="hidden";
		  	document.getElementsByName("toW")[0].style.visibility="hidden";
		 	document.getElementsByName("burnPercentage")[0].style.visibility="hidden";
			document.getElementsByName("burnper")[0].style.visibility="hidden";	
			document.getElementsByName("addimage")[0].style.visibility="hidden";	
			document.getElementsByName("burndegree")[0].style.visibility="hidden";
			document.getElementsByName("doburn")[0].style.visibility="hidden";
			return;
		}
		
		if(obj.value=="11")
		{
		  	document.getElementsByName("typeOfWeapon")[0].style.visibility="hidden";
		  	document.getElementsByName("toW")[0].style.visibility="hidden";
		 	
			 document.getElementsByName("burnPercentage")[0].style.visibility="visible";
			 document.getElementsByName("burnper")[0].style.visibility="visible";	
			 document.getElementsByName("addimage")[0].style.visibility="visible";	
			 document.getElementsByName("burndegree")[0].style.visibility="visible";
			 document.getElementsByName("doburn")[0].style.visibility="visible";
			 document.getElementsByName("top")[0].style.visibility="hidden";
			document.getElementsByName("sop")[0].style.visibility="hidden";
			document.getElementsByName("poisonsubstance")[0].style.visibility="hidden";
			document.getElementsByName("typeofpoisoning")[0].style.visibility="hidden";
			return;
		}
		else(obj.value=="12"||obj.value=="13"||obj.value=="14")
		{
		
			document.getElementsByName("typeOfWeapon")[0].style.visibility="hidden";
			document.getElementsByName("burnper")[0].style.visibility="hidden";
			document.getElementsByName("burnPercentage")[0].style.visibility="hidden";
			document.getElementsByName("addimage")[0].style.visibility="hidden";
			document.getElementsByName("burndegree")[0].style.visibility="hidden";
			document.getElementsByName("doburn")[0].style.visibility="hidden";
			document.getElementsByName("toW")[0].style.visibility="hidden";
			 document.getElementsByName("top")[0].style.visibility="hidden";
			document.getElementsByName("sop")[0].style.visibility="hidden";
			document.getElementsByName("poisonsubstance")[0].style.visibility="hidden";
			document.getElementsByName("typeofpoisoning")[0].style.visibility="hidden";
			return;
		}
	}
	
}

function submit4image()
{
	submitTile("REFRESHFORIMAGE");
}

function submitTile(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function callThisOnload()
{
	if(document.getElementsByName('patCrNo')[0]){
		document.getElementsByName('patCrNo')[0].focus()
	}
	if(document.getElementsByName('cmoType')[0]!=null)
	{
		document.getElementsByName('cmoType')[0].checked=true;
		showdivEmployee(document.getElementsByName('cmoType')[0]);
	}
	broughtBy(document.getElementsByName("isBroughtBy")[0]);
	enableRelation(document.getElementsByName("isRelative")[0]);
	focusCrNo();		
}

function populate(e)
{
	document.getElementsByName('crNoToRetrieve')[0].value=e;
	submitForm("DGNDETAIL");
}
function validateMlcDateTime()
{
	valid=true;
	if( validateMlcSysDate())
	{
		valid=validateMlcEpiDate();
		
	}
	else
	{
		valid=false;
	}	
	return valid;
}
function validateMLC()
{

	if( !(validateObjects(document.forms[0].patMlcNo,"MLC Number") && validateMlcDate(document.forms[0].mlcDate,"MLC Date")) )		
		return false;
	
	if(!validateMlcDateTime())
	{
		return false;
	}
	
	/*if(document.getElementsByName("mlcTimeHr")[0].value=="")
	{
		alert("Enter Patient MLC Time Hour ...");
		document.getElementsByName("mlcTimeHr")[0].focus();
		return false;
	}
	if(document.getElementsByName("mlcTimeMin")[0].value=="")
	{
		alert("Enter Patient MLC Time Minute ...");
		document.getElementsByName("mlcTimeMin")[0].focus();
		return false;
	}*/
	onblurTimeHourCheck(document.getElementsByName("mlcTimeHr")[0]);
	onblurTimeMinCheck(document.getElementsByName("mlcTimeMin")[0]);
	document.getElementsByName("mlcTime")[0].value=document.getElementsByName("mlcTimeHr")[0].value+":"+document.getElementsByName("mlcTimeMin")[0].value;
	
	if(!(validateObjects(document.forms[0].injuryTypeCode,"Type of Injury")&&
		validateObjects(document.forms[0].patStatusCode,"Patient Status")  &&
		validatePercentage(document.getElementsByName("burnPercentage")[0]) &&
		validatePoliceVerification() &&  validateBroughtBy()) )		
		return false;
		
		
		
	return true;
}

function validatePoliceVerification()
{
var valid=true
var verificationRequired=1
var verificationRequiredYes=1
if(verificationRequired==verificationRequiredYes)
{
		if(document.getElementsByName('isBroughtByPolice')[0].checked==true)
			{
		
				if(validateObjects(document.forms[0].caseNo,"Case Number") &&
				validateObjects(document.forms[0].policeStation,"Police Station") &&
				validateObjects(document.forms[0].docketNo,"Docket No") &&
				validateObjects(document.forms[0].officerIncharge,"Investigating Ofiicer Name") &&
				validateObjects(document.forms[0].ioDesignation,"Investigating Ofiicer Designation") &&
				validateObjects(document.forms[0].ioBatchNo,"Investigating Ofiicer Badge No") &&
				validateDutyDetail()&& 
				validateObjects(document.forms[0].caseRemarks,"Case Remark"))
				{
					valid= true;
				}	
				else
				{
					valid= false;
				}
			}
}	

	return valid
		
}

function parseInteger(val)
{
	if(val.length<1 || !(/^[0-9]*$/.test(val)))
		return 0;
	do
	{
		if(val.length==1)	return parseInt(val);
		if(/^0/.test(val))	val=val.substring(1);
		else				return parseInt(val);
	} while(true);
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

function popItUp(url) {
	newwindow=window.open(url,'name','height=400,width=450');
	if (window.focus) {newwindow.focus()}
	return false;
}



function addPatientCondition(event,id)
{
	var path='/HISClinical/opd/casuality/casualmlcDtl.cnt?hmode=ADDPATCONDITION&processId='+id;
	openPopup(path,event,300,600);
}

function DutyOfficerDetail()
{
	if(document.getElementsByName("dutyOfficeFlag")[1].checked)
	{
		document.getElementById('dutyOfficerDetailId').style.display="block";
	}
	else
	{
		document.getElementById('dutyOfficerDetailId').style.display="none";
	}
}

function handOverDetail()
{
	if(document.getElementsByName("handoverToFlag")[2].checked)
	{
		document.getElementById('handOvereDetailId').style.display="block";
	}
	else
	{
		document.getElementById('handOvereDetailId').style.display="none";
	}
}

function validateDutyDetail()
{
	valid=true;
	if(document.getElementsByName("dutyOfficeFlag")[1].checked)
	{
		if(validateObjects(document.forms[0].dutyOffName,"Duty Officer Name") &&
			validateObjects(document.forms[0].dutyOffDesignation,"Duty Officer Designation") &&
			validateObjects(document.forms[0].dutyOffBatchNo,"Duty Officer Badge No"))
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

function validateHandOverToDetail()
{
	valid=true;
	if(document.getElementsByName("handoverToFlag")[2].checked)
	{
		if(validateObjects(document.forms[0].handoverToName,"Handover To Name") &&
			validateObjects(document.forms[0].handoverToDesignation,"Handover To Designation") &&
			validateObjects(document.forms[0].handoverToBatchNo,"Handover To Badge No"))
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

function enableBroughtBy()
{
	if(document.getElementsByName("isBroughtByPolice")[0].checked)
	{
		document.getElementsByName("isBroughtBy")[0].checked=true;
		document.getElementsByName("isRelative")[0].value="2"
		document.getElementsByName("isRelative")[0].disabled=true;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
		enableRelation(document.getElementsByName("isRelative")[0]);
	}
	else
	{
		document.getElementsByName("isBroughtBy")[0].checked=false;
		document.getElementsByName("isRelative")[0].value="-1"
		document.getElementsByName("isRelative")[0].disabled=false;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
		enableRelation(document.getElementsByName("isRelative")[0]);
	}
}

function validateMlcSysDate()
{
	valid=true;
	var days=0;
	var a=document.getElementsByName("mlcDate")[0].value;
    var aArray=a.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
      
    var b=document.getElementsByName("sysDate")[0].value; 
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
    
    var c=document.getElementsByName("epiDate")[0].value; 
    var cArray=c.split("-");
     
    var cday=cArray[0];
    var cmonth=cArray[1];
    var cyear=cArray[2];
    var cdate=new Date(cmonth +" "+ cday+" "+cyear);
      
    
    days=((bdate-adate)/86400000);
    
    if(days==0)
    {
    	valid=checkMlcSysTime();
    }
    else
    {
    	if(days<0)
    	{
    		alert("MLC Date cannot be Greater than Today");
    		valid=false;
    	}
    	else
    	{
    		valid=true;
    	}
    }
    
    
    
    return valid;
}

function validateMlcEpiDate()
{
	valid=true;
	var day=0;
	var a=document.getElementsByName("mlcDate")[0].value;
    var aArray=a.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
    
     var c=document.getElementsByName("epiDate")[0].value; 
    var cArray=c.split("-");
     
    var cday=cArray[0];
    var cmonth=cArray[1];
    var cyear=cArray[2];
    var cdate=new Date(cmonth +" "+ cday+" "+cyear);
    
     day=(adate-cdate)/86400000;
    if(day==0)
    {
    	valid=checkMlcEpiTime();
    }
    else
    {
    	if(day<0)
    	{
    		alert("MLC Date cannot be Less than Patient Visit Date");
    		valid=false;
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

function checkMlcSysTime()
{
	valid=true;
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var mlcDate=document.getElementsByName("mlcDate")[0].value;
	var epiDate=document.getElementsByName("epiDate")[0].value; 
	var sysDate=document.getElementsByName("sysDate")[0].value; 
	
	if(document.getElementsByName("mlcTimeHr")[0].value=="")
	{
		alert("Please Enter the MLC Hour");
		document.getElementsByName("mlcTimeHr")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeHr")[0].value)) > hour)
	{
		alert("MLC Date Cannot be Greater than "+sysDate+" "+hour+":"+min);
		document.getElementsByName("mlcTimeHr")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("mlcTimeMin")[0].value=="")
	{
		alert("Please Enter the MLC Minute");
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeMin")[0].value)) > parseInt("59"))
	{
		alert("Minute Cannot be Greater than 59");
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("mlcTimeMin")[0].value)) > min)
	{
		alert("MLC Date Cannot be Greater than "+sysDate+" "+hour+":"+ (min.length==1?"0"+min:min));
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	return valid;
}

function checkMlcEpiTime()
{
	valid=true;
	var hour=parseInt(trimNum(document.getElementsByName("epiTimeHr")[0].value));
	var min=parseInt(trimNum(document.getElementsByName("epiTimeMin")[0].value));
	var mlcDate=document.getElementsByName("mlcDate")[0].value;
	var epiDate=document.getElementsByName("epiDate")[0].value; 
	var sysDate=document.getElementsByName("sysDate")[0].value; 
	
	if(document.getElementsByName("mlcTimeHr")[0].value=="")
	{
		alert("Please Enter the MLC Hour");
		document.getElementsByName("mlcTimeHr")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeHr")[0].value)) < hour)
	{
		alert("MLC Date Cannot be Less than "+epiDate+" "+hour+":"+(min.length==1?"0"+min:min));
		document.getElementsByName("mlcTimeHr")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("mlcTimeMin")[0].value=="")
	{
		alert("Please Enter the MLC Minute");
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeMin")[0].value)) > parseInt("59"))
	{
		alert("Minute Cannot be Greater than 59");
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	else if(parseInt(trimNum(document.getElementsByName("mlcTimeHr")[0].value)) <= hour && parseInt(trimNum(document.getElementsByName("mlcTimeMin")[0].value)) < min)
	{
		alert("MLC Date Cannot be Less than "+epiDate+" "+hour+":"+(min.length==1?"0"+min:min));
		document.getElementsByName("mlcTimeMin")[0].focus();
		valid=false;
	}
	return valid;
	
}

function validateBroughtBy()
{
 	if(document.forms[0].isBroughtBy.checked)
 	{
	 	if(comboValidation(document.forms[0].isRelative,"Brought By") &&
	 	isEmpty(document.getElementsByName('broughtByName')[0],'Brought By Name') &&
	 	isEmpty(document.getElementsByName('broughtByLocation')[0],'Brought From Location') && 
	 	validateBroughtByPolice())
	 	{
	 		return true
	 	}
	 	else
	 	{
	 		return false
	 	}
	 }
	 else
	 {
	 	return true
	 }
 }
 
function validateBroughtByPolice()
 {
 	valid=true
 	if(document.forms[0].isRelative.value== 2)
 	{
 		if(isEmpty(document.getElementsByName('constableDesig')[0],'Designation' ) &&
 		isEmpty(document.getElementsByName('constableBadgeNo')[0],'Batch No.'))
 		{
 			valid=true
 		}
 		else
 		{
 			valid=false
 		}
 	}
 	return valid
 } 
</script>

<!-- Transaction Started --></head><body><div id="shortCutKey"></div><table width="100%" cellpadding="0" cellspacing="0"><tbody><tr><td style="background: url('/HISClinical/hisglobal/images/bkg-spacefiller.jpg'); background-repeat:repeat-x;">&nbsp;</td><td width="800" valign="top"><table width="100%" cellpadding="0" cellspacing="1"><tbody><tr><td>


	<form action="/HISClinical/opd/casuality/casualmlcDtl.cnt" method="post">





<!-- TitleBar Started -->

<table width="100%" cellpadding="0" cellspacing="0">
<tbody><tr><td style="background-color: #FFFFFF;"></td></tr>
<tr><td class="ShadedTitleTagImage">
<span>
<table width="100%" cellspacing="0" cellpadding="5">

<tbody><tr><td class="TitleTagFontStyle"><div align="left"><b>MLC Details PGI</b></div></td><td class="TitleTagFontStyle"><div align="right"><b>



	<font size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
		<b>
			</b>
	</font>


</b></div></td></tr></tbody></table>
</span>
</td></tr></tbody></table>
<!-- TitleBar Finished -->



	
	





		
		
		










	
<!-- SubTitleBar Started -->

<table width="100%" cellpadding="2" cellspacing="0">
<tbody><tr><td class="applicationBackgroundColor"></td></tr>
<tr><td class="ShadedSubTitleTagImage">
<span>
<table width="100%" cellspacing="0" cellpadding="0">
<tbody><tr><td class="TitleTagFontStyle"><div align="left"><b>
	 Patient  Detail
	 </b></div></td><td class="TitleTagFontStyle"><div align="right"><b>


	
	 <b>
		Registration
		Date
		08-Nov-2010</b>
	
</b></div></td></tr></tbody></table></span>
</td></tr></tbody></table>
<!-- SubTitleBar Finished -->



	
<!-- Content Started -->

<table width="100%" cellpadding="0" cellspacing="0"><tbody><tr><td height="2" class="applicationBackgroundColor"></td></tr>
<tr><td height="2"><div align="center">
<table width="100%" cellpadding="0" cellspacing="1" class="ContentTagSideLine"><tbody><tr><td style="background-color: #FFFFFF" width="100%">

		<table width="100%">
			<tbody><tr>
				<td class="tdfonthead" width="25%">
				<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Name</font></div>
				</td>
				<td width="25%" class="tdfont">
				<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				Kamini
				
				
				</font>
				</b>
				</td>
				
				<td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				CR No.
				</font>
				</div>
				</td>
				
				
			<td class="tdfont">
				<div align="left">
				<b>201001000100
						
				</b>
				</div>
				</td>
			
			</tr>
			
			<tr>
				
				<td width="25%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				Age
				/
				Gender
				</font></div>
				</td>
				
				<td width="25%" nowrap="" class="tdfont"><b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">23 Y/
				Female</font></b></td>
				
				<td width="25%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Patient Category </font></div>
				</td>
				
				<td width="25%" class="tdfont" nowrap=""><b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Normal </font></b></td>
				
			</tr>
			<tr>
			
				<td class="tdfonthead" width="25%" nowrap="">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				Father Name
				
				</font></div></td><td width="25%" class="tdfont"><b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				Ghanshyam
				</font>
				</b>
				</td>
				
				<td class="tdfonthead" width="25%" nowrap="">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				Spouse Name
				
				</font></div></td><td width="25%" class="tdfont"><b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
				</font>
				</b>
				</td>
			</tr>
				<!-- <td width="25%" nowrap class="tdfont">
				<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    
			    </font>
			    </b>
			    </td>-->
			  
			<!--<tr>
			 <td width="25%" class="tdfonthead">
			<font color="#000000" size="2"	face="Verdana, Arial, Helvetica, sans-serif">
					Registration
					Date
					&
    				Time
			</td>
			<td colspan="3" class="tdfont">
			<b><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">08-Nov-2010</font></b>
			</td>
			</tr>-->
			
		</tbody></table>

 


	
</td></tr></tbody></table></div></td></tr>
<tr><td height="1" class="applicationBackgroundColor"></td></tr>
</tbody></table>
<!--Content of the Page ends -->



	
	




	

	

	
	
	
<!-- SubTitleBar Started -->

<table width="100%" cellpadding="2" cellspacing="0">
<tbody><tr><td class="applicationBackgroundColor"></td></tr>
<tr><td class="ShadedSubTitleTagImage">
<span>
<table width="100%" cellspacing="0" cellpadding="0">
<tbody><tr><td class="TitleTagFontStyle"><div align="left"><b>MLC Details PGI</b></div></td><td class="TitleTagFontStyle"><div align="right"><b>


		<table width="100%">
			<tbody><tr>
				<td>	  
	            	<div width="75%" align="right">		
						<input type="checkbox" name="isBroughtByPolice" tabindex="1" value="on" onclick="enableBroughtBy()">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b>
								Brought By Police
							</b>	 
						</font>
					</div>
	            </td>       
	 		</tr>
	    </tbody></table>
	
</b></div></td></tr></tbody></table></span>
</td></tr></tbody></table>
<!-- SubTitleBar Finished -->

	
	
<!-- Content Started -->

<table width="100%" cellpadding="0" cellspacing="0"><tbody><tr><td height="2" class="applicationBackgroundColor"></td></tr>
<tr><td height="2"><div align="center">
<table width="100%" cellpadding="0" cellspacing="1" class="ContentTagSideLine"><tbody><tr><td style="background-color: #FFFFFF" width="100%">

		<table width="100%" cellspacing="1" cellpadding="0">
			
			<tbody><tr>
				<td width="15%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						MLC Number
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<input type="text" name="patMlcNo" maxlength="10" tabindex="1" value="" onkeypress="return validateAlphaNumericOnly(event,this)">
				</td>
				
				<td width="35%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Is Referred
					</font>
				</div>
				</td>

				

				<td width="25%" class="tdfont">
					<input type="checkbox" name="isTransfer" tabindex="1" value="1" disabled="disabled">
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						MLR
						Book No.
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<input type="text" name="bookNo" maxlength="20" tabindex="1" value="" onkeypress="return validateAlphaNumericOnly(event,this)">
				</td>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						MLR
						Page No.
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<input type="text" name="pageNo" maxlength="4" tabindex="1" value="" onkeypress="return validateNumeric(event)">
				</td>
			</tr>
			
			
			
			<tr>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#FF0000">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						MLC Date
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<input type="text" name="mlcDate" id="mlcDate" readonly="" value="08-Nov-2010" size="12"> 	<img src="/HISClinical/hisglobal/images/imgDatepicker.png" id="mlcDate1" style="cursor: pointer; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; border-top-color: red; border-right-color: red; border-bottom-color: red; border-left-color: red; " title="Date selector" tabindex="1" onmouseover="this.style.background='red';" onmouseout="this.style.background=''"> 	<script language="JavaScript"> Calendar.setup( { inputField     :    "mlcDate",mapkey : '32', ifFormat       :    "%d-%b-%Y", button         :    "mlcDate1" , singleClick    :    true });	</script>
				</td>

				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#FF0000">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						MLC Time
					</font>
				</div>
				</td>

				<td width="25%" class="tdfont">
					<input type="hidden" name="mlcTime" value="">
					<span>
						<input type="text" name="mlcTimeHr" maxlength="2" size="3" tabindex="1" value="" onkeypress="return onkeyTimeHour(this,event)" onblur="onblurTimeHourCheck(this)">
						<b>	:</b>
					</span>
					<span>
						<input type="text" name="mlcTimeMin" maxlength="2" size="3" tabindex="1" value="" onkeypress="return onkeyTimeMin(this,document.getElementsByName('mlcTimeHr')[0],event)" onblur="onblurTimeMinCheck(this)">
						(HH:MM 24 Hrs)
					</span>
				</td>
			</tr>
			
			<tr>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#FF0000">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Type Of Injury
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
				
					<select name="injuryTypeCode" tabindex="1" class="regcbo"	onchange=enableWeapontype(this)><option value="-1">Select Value</option>
						
						<option value="14">Blunt</option>
						<option value="1">Assault</option>
						<option value="2">poisioning</option>
<option value="11">Burn</option>
<option value="13">Cut</option>
<option value="12">Fracture</option></select>
				</td>
				
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#FF0000">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						GSV Value
					</font>
				</div>
				</td>

<td width="5%" class="tdfonthead">

				<div align="left"><font color="#FF0000">*</font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">E</font>
					<select	name="patStatusCode1" tabindex="1" class="regcbo">
						<option value="-1">Select Value</option>
						<option value="13">E1 Opens Eyes In Response To Painful Stimuli</option>
						<option value="15">E2 Opens Eyes In Response To Voice</option>
						<option value="16">E3 Opens Eyes Spontaneously</option>
					</select>	
				</div>
				<div align="left">
					<font color="#FF0000">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">V</font>
					<select name="patStatusCode1" tabindex="1" class="regcbo"><option value="-1">Select Value</option>
						<option value="13">V1 Makes No Sounds</option>
						<option value="15">V2 Incomprehensible Sounds</option>
						<option value="16">V3 Utters Inappropriate Words</option>
						<option value="11">V4 Confused, Disorientated</option>
						<option value="14">V5 Oriented, Converses Normally</option>
					</select>
				</div>
				<div align="left">
					<font color="#FF0000">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">M</font>
					<select name="patStatusCode1" tabindex="1" class="regcbo">
						<option value="-1">Select Value</option>
						<option value="13">M1 Makes No Movements</option>
						<option value="15">M1 No Motor Response</option>
						<option value="16">M2 Extension To Pain</option>
						<option value="11">M2 Extension To Painful Stimuli</option>
						<option value="14">M3 Abnormal Flexion To Pain</option>
						<option value="12">M4 Flexion/Withdrawal To Pain</option>
						<option value="12">M5 Localizes To Pain.</option>
						<option value="12">M6 Obeys Commands</option>

					</select>
				</div>
							
				

				</td>


				
			</tr>
			<tr>
				<td width="15%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Nature of Injury
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<select name="injuryNatureCode" tabindex="1" class="regcbo"><option value="-1">To Be Given Later</option>
						
						<option value="13">Dangerous</option>
<option value="12">Grievous</option>
<option value="11">Simple</option>
</select>
				</td>
			   
			    <td class="tdfonthead" width="35%" align="right">
			    <div align="right">
            		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Fit To Make Statement 
				 	</font>
				 </div>
				</td>
				<td width="25%" class="tdfont">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
				        <b>
							<input type="radio" name="fitnessStatus" tabindex="1" value="1" checked="checked">
							Fit
							<input type="radio" name="fitnessStatus" tabindex="1" value="0">
							Unfit
					    </b>
					</font>
				</td>	
			</tr>
		
			<tr>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" name="toW" size="2" face="Verdana, Arial, Helvetica, sans-serif" style="visibility:hidden">
						Type of Weapon
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
					<input type="text" name="typeOfWeapon" maxlength="100" size="40" tabindex="1" value=""  style="visibility:hidden" onkeypress="return validateAlphaNumericOnly(event,this)">
				</td>
			   
			    <td class="tdfonthead" width="25%" align="right">
			    <div align="right">
            		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Injury Size
				 	</font>
				 </div>
				</td>
				<td width="25%" class="tdfont">
					<input type="text" name="injurySize" maxlength="100" size="40" tabindex="1" value=""   onkeypress="return validateAlphaNumericOnly(event,this)">
				</td>	
			</tr>
			
			<tr>
				<td width="25%" class="tdfonthead" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Injury Depth
					</font>
				</div>
				<br>
				<div align="right">
			   			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Injury duration
				 		</font>
				 	</div>
				</td>
				
				<td width="25%" class="tdfont">
					<div>
					<input type="text" name="injuryDepth" maxlength="100" size="45" tabindex="1" value="" onkeypress="return validateAlphaNumericOnly(event,this)">
					</div>
					<br>
					<div>
					<input type="text" name="injury duration" maxlength="3" tabindex="1">
					</div>
				</td>
			   
			    <td class="tdfonthead" width="25%" align="right">
			    <div align="right">
			   		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Injury Site
				 	</font>
				 </div>
				</td>
				<td width="25%" class="tdfont" height="100px">
					
					
					<select name="injurySite"	tabindex="1"  multiple="multiple" size="4"><option value="-1" selected="selected">Select Value</option>
						
						<option value="13">Ear</option>
<option value="14">Left Kidney</option>
<option value="12">Leg</option>
<option value="11">Neck</option>
<option value="11">Right Leg</option>
<option value="11">Left Leg </option>
<option value="11">Right Hand</option>
<option value="11">Left Hand</option>
<option value="11">Ankel</option>
<option value="11">Head</option>
<option value="15">Right Kidney</option></select>
					
				</td>	
			</tr>
			
			<tr>
				<td width="25%" class="tdfonthead" align="right">
			
				
					<font color="#000000" name="burnper" size="2" face="Verdana, Arial, Helvetica, sans-serif"  style="visibility:hidden">
						Burn Percentage
					</font>
						
				 <div align="right">
			   		<font color="#000000" name="top" size="2" face="Verdana, Arial, Helvetica, sans-serif" style="visibility:hidden">
						Type of Poisoning
				 	</font>
				 </div>
				</td>
				
				<td width="25%" class="tdfont"align="right">
				
				
				<div>
					<input type="text" name="burnPercentage" maxlength="3" size="3" tabindex="1" value="" 	style="visibility:hidden"	onkeypress="return validateNumeric(event)" onblur="validatePercentage(this)">
					<button name="addimage" type="button"  value="add image" size="5"  style="visibility:hidden"  onclick="enableBlanket()" >Show image</button>
				</div>
				
					
					<select name="typeofpoisoning" tabindex="1" size="1" style="visibility:hidden">
					
					<option value="-1" selected="selected">Select Value</option>
						
						<option value="1">accidental</option>
						<option value="2">suicidal</option>
						<option value="3">homicidal	</option>
						
					
				</td>
			   
			    <td class="tdfonthead" width="25%" align="right">
			    
				<div align="right">
					<font color="#000000" name="doburn" size="2" face="Verdana, Arial, Helvetica, sans-serif"  style="visibility:hidden">
						Degree of burn
					</font>
				</div>
				
				
				 <div align="right">
			   		<font color="#000000" name="sop" size="2" face="Verdana, Arial, Helvetica, sans-serif" style="visibility:hidden">
						Substance of poisioning
				 	</font>
				 </div>
				 
				 
				</td>
				<td width="25%" class="tdfont">
						
					<select name="burndegree" tabindex="1" size="1"	 style="visibility:hidden"	>
					<option value="-1" selected="selected">Select Value</option>
						
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>

				
						
					<div>
					<input type="text" name="poisonsubstance"	maxlength="15" size="40"	tabindex="1" style="visibility:hidden">
					</div>
				</td>
				
			</tr>
		</tbody></table>	
	
	<table width="100%" cellspacing="1" cellpadding="0">
			<tbody><tr>
				<td class="tdfonthead" colspan="1" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Particulars
					</font>
				</div>
				</td>
				<td class="tdfont" colspan="3">
					<textarea name="patCondition" tabindex="1" cols="90" rows="5"	onkeypress="return (validateTextArea(event,this,'500') &amp;&amp; validateAlphaNumericOnly(event,this))"></textarea>
					<input type="button" name="mybutton" tabindex="1" value=" Add Macros"	onclick="addPatientCondition(event,5)" style="cursor:pointer">
				</td>
			</tr>
			
			
			<tr>
				<td width="25%" class="tdfonthead" nowrap="nowrap" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Referred By Doctor Name
					</font>
				</div>
				<br>
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Other Doctor
					</font>
				</div>
				</td>
				
				<td width="25%" class="tdfont">
				<div>
					<select name="referDoctorCode" tabindex="1" class="regcbo"><option value="-1">Select Value</option>
						
						<option value="PGIADC10000016">Adfafa Adfadfa Adfadfasd(JRD)(PGIADC10000016)</option></select>
				</div>
				<br>
				<div>
				
				<input type="text" name="otherdoctor"	maxlength="15" size="25"	tabindex="1">
				
				</div>
				</td>

				<td width="25%" class="tdfonthead" align="right">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Referred Remarks
					</font>
					</div>
				</td>

				<td class="tdfont" width="25%">
					<textarea name="referDocotorRemarks" tabindex="1" onkeypress="return (validateTextArea(event,this,'100') &amp;&amp; validateAlphaNumericOnly(event,this))" style="width: 190px;font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px;"></textarea>
				</td>
			</tr>
		</tbody></table>
	
</td></tr></tbody></table></div></td></tr>
<tr><td height="1" class="applicationBackgroundColor"></td></tr>
</tbody></table>
<!--Content of the Page ends -->

	
	
	
<!-- SubTitleBar Started -->

<table width="100%" cellpadding="2" cellspacing="0">
<tbody><tr><td class="applicationBackgroundColor"></td></tr>
<tr><td class="ShadedSubTitleTagImage">
<span>
<table width="100%" cellspacing="0" cellpadding="0">
<tbody><tr><td class="TitleTagFontStyle"><div align="left"><b>Police Verification Details</b></div></td><td class="TitleTagFontStyle"><div align="right"><b>


	
</b></div></td></tr></tbody></table></span>
</td></tr></tbody></table>
<!-- SubTitleBar Finished -->


	
<!-- Content Started -->

<table width="100%" cellpadding="0" cellspacing="0"><tbody><tr><td height="2" class="applicationBackgroundColor"></td></tr>
<tr><td height="2"><div align="center">
<table width="100%" cellpadding="0" cellspacing="1" class="ContentTagSideLine"><tbody><tr><td style="background-color: #FFFFFF" width="100%">

		<table width="100%" cellspacing="1" cellpadding="0">
			<tbody><tr>
				<td width="25%" nowrap="nowrap" class="tdfonthead" align="right">
				<div align="right">
					
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Fir No.
					</font>
				</div>
				</td>

				<td width="25%" nowrap="nowrap" class="tdfont">
					<input type="text" name="caseNo" maxlength="50" tabindex="1" value="" onkeypress="return validateAlphaNumericOnly(event,this)" class="textboxBig">
				</td>

				<td width="25%" nowrap="nowrap" class="tdfonthead" align="right">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Police Station
					</font>
					</div>
				</td>

				<td width="25%" nowrap="nowrap" class="tdfont">
					<input type="text" name="policeStation" maxlength="100" tabindex="1" value="" onkeypress="return validateAlphaNumOnly(this,event)" class="textboxBig">
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						DD No.
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<input type="text" name="docketNo" maxlength="50" tabindex="1" value="" onkeypress="return validateAlphaNumericOnly(event,this)" class="textboxBig">
				</td>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" "face="Verdana, Arial, Helvetica, sans-serif">
						Investigating Officer
						Name
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<input type="text" name="officerIncharge" maxlength="60" tabindex="1" value="" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" class="textboxBig">
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Investigating Officer
						Designation
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<input type="text" name="ioDesignation" maxlength="50" tabindex="1" value="" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" class="textboxBig">
				</td>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Investigating Officer
						Belt No.
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<input type="text" name="ioBatchNo" maxlength="50" tabindex="1" value="" onkeypress="return validateAlphaNumericOnly(event,this)" class="textboxBig">
				</td>
			</tr>
			
			<tr>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Duty Officer
						Detail
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%" colspan="3">
					<input type="radio" name="dutyOfficeFlag" tabindex="1" value="0" onclick="DutyOfficerDetail()">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						IO
					</font>		
					<input type="radio" name="dutyOfficeFlag" tabindex="1" value="1" checked="checked" onclick="DutyOfficerDetail()">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Other
					</font>
				</td>
			</tr>
		</tbody></table>
			
		<div id="dutyOfficerDetailId">
			<table width="100%" cellspacing="1" cellpadding="0">
				<tbody><tr>
					<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Duty Officer
							Name
						</font>
						</div>
					</td>
					<td class="tdfont" nowrap="nowrap" width="25%">
						<input type="text" name="dutyOffName" maxlength="60" tabindex="1" value="" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" class="textboxBig">
					</td>
					<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Duty Officer
							Designation
						</font>
						</div>
					</td>
					<td class="tdfont" nowrap="nowrap" width="25%">
						<input type="text" name="dutyOffDesignation" maxlength="50" tabindex="1" value="" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" class="textboxBig">
					</td>
				</tr>
				<tr>
					<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Duty Officer
							Belt No.
						</font>
						</div>
					</td>
					<td class="tdfont" nowrap="nowrap" width="25%" colspan="3">
						<input type="text" name="dutyOffBatchNo" maxlength="50" tabindex="1" value="" onkeypress="return validateAlphaNumericOnly(event,this)" class="textboxBig">
					</td>
				
				</tr>
			</tbody></table>
		</div>
		
		
			
		<table width="100%" cellspacing="1" cellpadding="0">
			<tbody><tr>
				<td colspan="4">
					<table width="100%" cellpadding="0" cellspacing="1">
						<tbody><tr>
							<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Brought Dead
								</font>
							</div>
							</td>
			
							<td class="tdfont" nowrap="nowrap" width="75%">
								<input type="checkbox" name="isBroughtDead" tabindex="1" value="1">
							</td>
						</tr>
						<tr>
							<td nowrap="nowrap" class="tdfonthead" width="25%" align="right">
								<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Case Remarks
								</font>
								</div>
							</td>
			
							<td colspan="2" nowrap="nowrap" width="75%" class="tdfont">
								<textarea name="caseRemarks" tabindex="1" cols="90" rows="1" onkeypress="return (validateTextArea(event,this,'500') &amp;&amp; validateAlphaNumericOnly(event,this))"></textarea>
							</td>
						</tr>
					</tbody></table>
				</td>
			</tr>
		</tbody></table>
	
</td></tr></tbody></table></div></td></tr>
<tr><td height="1" class="applicationBackgroundColor"></td></tr>
</tbody></table>
<!--Content of the Page ends -->

	
	
	
<!-- SubTitleBar Started -->

<table width="100%" cellpadding="2" cellspacing="0">
<tbody><tr><td class="applicationBackgroundColor"></td></tr>
<tr><td class="ShadedSubTitleTagImage">
<span>
<table width="100%" cellspacing="0" cellpadding="0">
<tbody><tr><td class="TitleTagFontStyle"><div align="left"><b>Handover To Detail</b></div></td><td class="TitleTagFontStyle"><div align="right"><b>

 
	
</b></div></td></tr></tbody></table></span>
</td></tr></tbody></table>
<!-- SubTitleBar Finished -->

	
<!-- Content Started -->

<table width="100%" cellpadding="0" cellspacing="0"><tbody><tr><td height="2" class="applicationBackgroundColor"></td></tr>
<tr><td height="2"><div align="center">
<table width="100%" cellpadding="0" cellspacing="1" class="ContentTagSideLine"><tbody><tr><td style="background-color: #FFFFFF" width="100%">

		<table width="100%" cellspacing="1" cellpadding="0">
			<tbody><tr>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Name
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<input type="text" name="handoverOffName" maxlength="60" tabindex="1" value="" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" class="textboxBig">
				</td>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Designation
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<input type="text" name="handoverOffDesg" maxlength="50" tabindex="1" value="" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" class="textboxBig">
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Belt No.
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<input type="text" name="handoverOffBadgeNo" maxlength="50" tabindex="1" value="" onkeypress="return validateAlphaNumericOnly(event,this)" class="textboxBig">
				</td>
				<td class="tdfonthead" nowrap="nowrap" width="25%" align="right">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Handover Date
					</font>
				</div>
				</td>
				<td class="tdfont" nowrap="nowrap" width="25%">
					<input type="text" name="handoverDateTime" id="handoverDateTime" readonly="" value="08-Nov-2010" size="12"> 	<img src="/HISClinical/hisglobal/images/imgDatepicker.png" id="handoverDateTime1" style="cursor: pointer; border: 1px solid red;" title="Date selector" tabindex="1" onmouseover="this.style.background='red';" onmouseout="this.style.background=''"> 	<script language="JavaScript"> Calendar.setup( { inputField     :    "handoverDateTime",mapkey : '32', ifFormat       :    "%d-%b-%Y", button         :    "handoverDateTime1" , singleClick    :    true });	</script>
				</td>
			</tr>
		</tbody></table>
	
</td></tr></tbody></table></div></td></tr>
<tr><td height="1" class="applicationBackgroundColor"></td></tr>
</tbody></table>
<!--Content of the Page ends -->

				
	
	
		
<!-- SubTitleBar Started -->

<table width="100%" cellpadding="2" cellspacing="0">
<tbody><tr><td class="applicationBackgroundColor"></td></tr>
<tr><td class="ShadedSubTitleTagImage">
<span>
<table width="100%" cellspacing="0" cellpadding="0">
<tbody><tr><td class="TitleTagFontStyle"><div align="left"><b></b></div></td><td class="TitleTagFontStyle"><div align="right"><b>


			<div align="left">
				<input type="checkbox" name="isBroughtBy" tabindex="1" value="1" onclick="broughtBy(this)">
				Brought By Detail
			</div>
		
</b></div></td></tr></tbody></table></span>
</td></tr></tbody></table>
<!-- SubTitleBar Finished -->

		<div id="broughtById" style="display: none; ">
			
<!-- Content Started -->

<table width="100%" cellpadding="0" cellspacing="0"><tbody><tr><td height="2" class="applicationBackgroundColor"></td></tr>
<tr><td height="2"><div align="center">
<table width="100%" cellpadding="0" cellspacing="1" class="ContentTagSideLine"><tbody><tr><td style="background-color: #FFFFFF" width="100%">

				<table width="100%" cellspacing="1" cellpadding="0">
					<tbody><tr>
						<td width="25%" class="tdfonthead" align="right">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								Brought By
							</font>
						</div>
						</td>
						
						<td width="25%" class="tdfont">
							<select name="isRelative" tabindex="1" onclick="enableRelation(this)"><option value="-1">Select Value</option>
								<option value="1">Relative</option>
								<option value="2">Police</option>
								<option value="0">Others</option></select>
						</td>
						
						<td width="25%" class="tdfonthead" align="right">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								Relationship
							</font>
						</div>
						</td>
						
						<td width="25%" class="tdfont">
							<select name="broughtByRelationCode" tabindex="1" disabled="" class="regcbo"><option value="-1">Select Value</option>
								
								<option value="16">Brother</option>
<option value="18">Daughter</option>
<option value="11">Father</option>
<option value="13">Husband</option>
<option value="12">Mother</option>
<option value="10">Self</option>
<option value="15">Sister</option>
<option value="17">Son</option>
<option value="14">Wife</option></select>
						</td>
					</tr>
					<tr>
						
						<td width="25%" class="tdfonthead" align="right">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								Name
							</font>
						</div>
						</td>
						<td width="25%" class="tdfont">
							<input type="text" name="broughtByName" maxlength="60" tabindex="1" value="" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" class="textboxBig">
						</td>
						<td width="25%" class="tdfonthead" align="right">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								Brought From Location
							</font>
						</div>
						</td>
						<td width="25%" class="tdfont">
							<input type="text" name="broughtByLocation" maxlength="100" tabindex="1" value="" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" class="textboxBig">

						</td>
					</tr>
				</tbody></table>	
			<div id="policeBroughtById" style="display: none; ">
				<table width="100%" cellspacing="1" cellpadding="0">	
					<tbody><tr>
						<td width="25%" class="tdfonthead" align="right">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								Designation
							</font>
						</div>
						</td>
						
						<td width="25%" class="tdfont">
							<input type="text" name="constableDesig" maxlength="50" tabindex="1" value="" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" class="textboxBig">
						</td>
						<td width="25%" class="tdfonthead" align="right">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								Batch No. 
							</font>
							</div>
						</td>
		
						<td width="25%" class="tdfont">
							<input type="text" name="constableBadgeNo" maxlength="50" tabindex="1" value="" onkeypress="return validateAlphaNumericOnly(event,this)" class="textboxBig">
						</td>
					</tr>
				</tbody></table>	
			</div>
				<table width="100%" cellspacing="1" cellpadding="0">
					<tbody><tr> 
				    	<td width="50%" height="100%">
				       		<table width="100%" cellspacing="1" cellpadding="0">
				       			<tbody><tr>
				       				<td width="50%" align="right" style="background-color:#FFEBD5;height:45;font-family: 'MS Sans Serif';	font-size:18px; color:#653232;text-align:right;">
				        				<div align="right">
				        				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					          				Phone  NO        
					        			</font>
					        			</div>
				       				</td>
				     				<td width="50%" style="background-Color:#F1ECE2;height:45;color:#990000;text-align:left;text-transform: capitalize;font-family:'Trebuchet MS';	font-size: 10px;font-weight: bold;font-variant: normal;">
				         				<input type="text" name="broughtByPhone" maxlength="30" tabindex="1" value="" onkeypress="return validateNumeric(event)">
				       				</td>
				       			</tr>
				       		</tbody></table>
				       	</td>
				       	<td width="50%">
				       		<table width="100%" cellspacing="1" cellpadding="0">
				        		<tbody><tr>
				       				<td width="25%" valign="top" rowspan="2" class="tdfonthead" align="right"><!-- 18 --> 
				          				<div align="right">
				          				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				             				Address
				       		 			</font>
				       		 			</div>
				       				</td>
				      				<td colspan="2" width="35%" rowspan="2" class="tdfont" valign="top"><!-- 3 -->
				            			<textarea name="broughtByAddress" tabindex="1" onkeypress="return (validateTextArea(event,this,'100') &amp;&amp; validateAlphaNumericOnly(event,this))" class="textarea2"></textarea>
				        			</td>
				        		</tr>
				        	</tbody></table>
				        </td>
				    </tr>

				</tbody></table>
			
</td></tr></tbody></table></div></td></tr>
<tr><td height="1" class="applicationBackgroundColor"></td></tr>
</tbody></table>
<!--Content of the Page ends -->

		</div>
	

	
	
	<input type="hidden" name="sysDate" value="08-Nov-2010">
	



<!-- Button toolbar Started --><table width="100%" cellspacing="0" cellpadding="0">
<tbody><tr><td width="5px"><img src="/HISClinical/hisglobal/images/rnd-trans-ffffff-bdr.gif"></td>
<td style="background-color:#FFFFFF; background: url('/HISClinical/hisglobal/images/border-top.gif') top;  background-repeat:repeat-x;"></td>
<td width="5px"><img src="/HISClinical/hisglobal/images/rnd-trans-ffffff-bdr-RT.gif"></td></tr>
<tr><td style="background-color:#FFFFFF; background: url('/HISClinical/hisglobal/images/border-left.gif') left; background-repeat:repeat-y;">
</td><td style="background-color:#FFFFFF;" width="100%">
<div align="center">


	
		
			
			<img class="button" src="/../HIS/hisglobal/images/buttons/btn-sv.png" style="cursor: pointer" tabindex="1" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateMLC(),'SAVE');" onclick="submitFormOnValidate(validateMLC(),'SAVE');">
			<img class="button" src="/../HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" onclick="submitForm('CANCEL');">
			<img class="button" src="/../HIS/hisglobal/images/buttons/btn-clr.png" style="cursor: pointer" onclick="submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO');" tabindex="1">
			
		
	
	
	
</div></td>
<td style="background-color:#FFFFFF; background: url('/HISClinical/hisglobal/images/border-right.gif') right;  background-repeat:repeat-y;"></td></tr>
<tr><td width="5px"><img src="/HISClinical/hisglobal/images/rnd-trans-ffffff-bdr-LB.gif"></td>
<td style="background: url('/HISClinical/hisglobal/images/border-bottom.gif') bottom;  background-repeat:repeat-x;"></td>
<td width="5px"><img src="/HISClinical/hisglobal/images/rnd-trans-ffffff-bdr-RB.gif"></td></tr></tbody></table>
<!-- Button toolbar Finished -->




	<br><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b></b></font><br><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b></b></font><br><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b></b></font>


</form></td></tr></tbody></table></td><td style="background: url('/HISClinical/hisglobal/images/bkg-spacefiller.jpg'); background-repeat:repeat-x;">&nbsp;</td></tr> </tbody></table><!-- Transaction Finished -->

<input type="hidden" name="hmode" value="GETPATDTL">
<input type="hidden" name="patCrNo" value="201001000100">
<input type="hidden" name="episodeCode" value="10115001">
<input type="hidden" name="episodeVisitNo" value="1">
<input type="hidden" name="isDirectCall" value="DIRECT">
<input type="hidden" name="isPoliceVerReq" value="1">
<input type="hidden" name="hiddenTimeHr" value="16">
<input type="hidden" name="hiddenTimeMin" value="54">
<input type="hidden" name="epiDate" value="08-Nov-2010">
<input type="hidden" name="epiTimeHr" value="14">
<input type="hidden" name="epiTimeMin" value="09">


	
</body></html>

</his:TransactionContainer>
<html:hidden name="CsultyEmgMlcDtlFB" property="hmode"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="patCrNo"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="episodeCode"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="episodeVisitNo"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="isDirectCall"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="isPoliceVerReq"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="hiddenTimeHr"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="hiddenTimeMin"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="epiDate"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="epiTimeHr"/>
<html:hidden name="CsultyEmgMlcDtlFB" property="epiTimeMin"/>
<div id="blanket" style="height: 580;width: 1024;display: none;"></div>

	<div id="burnImage" style="display: none; height: 400px;width: 310px;position:absolute; top: 20%;left: 30%;z-index: 9100;background-color: #FFEBD5;">
				<table width="100%">
					<tr>
					
						<td width="100%"  align="center">
							<button name="close" type="button" align="right"  value="close" size="5"  onclick="disableBlanket()" >CloseImage</button>
						</td>
					</tr>
					<tr>
						<td width="100%"  align="center">
						<img src='/HISClinical/hisglobal/images/Burn Percentage.gif'/>
						</td>	
					</tr>
					
				</table>
				
				
			</div>

<logic:equal name="CsultyEmgMlcDtlFB" property="isDirectCall" value="DIRECT">
</form>	
</logic:equal>