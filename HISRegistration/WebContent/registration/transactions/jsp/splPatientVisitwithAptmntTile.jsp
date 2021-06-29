<!--------------------------------------new department visit -------------------------------->
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%try {

				%>
<%@page autoFlush="true" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ page import="java.util.*,registration.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@ page import="registration.config.RegistrationConfig"%>

<!-- <LINK REL=StyleSheet HREF="/AHIMS/hisglobal/css/tab.css" TYPE="text/css" MEDIA=screen> -->

<!--By Mukund on 02.12.2016 for Token Application-->
<META http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>  
<!--End Mukund-->

<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/css/avai/Color.css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">
<link rel="stylesheet" href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HIS/hisglobal/hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HIS/hisglobal/hisglobal/css/basic.css">
<link rel="stylesheet" href="/HIS/hisglobal/hisglobal/css/jquery-ui.css">



<%-- <script language="JavaScript" src="/AHIMS/registration/js/popup.js"></script> --%>
<%-- <script language="JavaScript" src="/AHIMS/registration/js/commonFunctions.js"></script>
<script language="JavaScript" src="/AHIMS/registration/js/validationCommon.js"></script>
<script language="JavaScript" src="/HIS/hisglobal/js/avai/validationCalls.js"></script> --%>
<script language="JavaScript" src="/HISRegistration/registration/transactions/js/regValidation.js"></script>
<script language="JavaScript" src="/HIS/hisglobal/js/avai/calendar.js"></script>
<script language="JavaScript" src="/HIS/registration/js/utilityFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/avai/popup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/barcode_code39.js"></script>

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/basic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>

<script language="JavaScript" src="/HISRegistration/hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script language="JavaScript" type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/moment.min.js"></script>
<script type="text/javascript" src="./../../registration/transactions/js/saveBarcodeSlipPrintEntryInHMIS.js" /></script> 




<!-- Start:Sheeldarshi -->



<!-- <script language="JavaScript" src="/AHIMS/registration/js/regValidation.js"></script> -->
<!-- <script language="JavaScript" src="/AHIMS/registration/js/popup.js"></script> -->
<!-- <script language="JavaScript" src="/AHIMS/registration/js/newRegistration.js"></script> -->



<style type="text/css"> 
@media print { 
		#noprint 
		{
		 display: none; 
		}
			#divPatientRegisteredLabel 
		{
			display: none;
		}
		#divNoAppointmentAvailableLabel
		{
			display: none;
		}
		#tabHeader 
		{
			display: none;
		}
		#divCRNoLabel 
		{
			display: block;
		}
		#divEpisodeDateLabel 
		{
			display: block;
		}
		#divPatientNameLabel 
		{
			display: block;
		}
		#divAgeLabel 
		{
			display: block;
		}
		#divGenderLabel 
		{
			display: block;
		}
		#divAreaLabel 
		{
			display: block;
		}
		#divTokenLabel 
		{
			display: block;
		}
		#divConsultantLabel 
		{
			display: block;
		}
		#divDiagnosisLabel 
		{
			display: block;
		}
		#divOccupationLabel 
		{
			display: block;
		}
		#divOPDCardLabel 
		{
			display: block;
		}
		#divOPDCardLogo 
		{
			display: block;
		}
		#divOPDCardHeader 
		{
			display: block;
		}
		#divPatientVisitedLabel 
		{
			display: none;
		}
		#divBlank1 
		{
			display: block;
		}
		#divBlank2 
		{
			display: block;
		}
		
}
	#blanket {
	background-color: #111;
	opacity: 0.60;
	filter: alpha(opacity = 60);
	position: absolute;
	z-index: 9001;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
	}
 td.TdBorder{
 		 border:2px solid black;
		}
		#divFooter 
		{
            width:779px; 
            margin-top:20px;
        }
        
   .border .div-table-col{
		border: 1px solid black;
	}
 
 </style>
<script type="text/javascript"><!--
var countDeptVisit=0;

function calculategranttotal(index)
{
	//alert("In total calc");
	if(document.getElementsByName("renewalRequired")[0].value=="1")
	{
		//alert(getElementsByName("grandTotal")[0].value);
		var grandTotal=document.getElementsByName("grandTotal")[0].value;
		//document.getElementsByName("grandTotal")[0].value=parseInt(grandTotal)+parseInt(document.getElementsByName("renewalAmount")[index].value);
		//document.getElementsByName("grandTotal")[0].innerHTML=parseInt(grandTotal)+parseInt(document.getElementsByName("renewalAmount")[index].value);
		
	}
}

function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function submitForm(mode)
{
     document.getElementsByName("hmode")[0].value=mode;
	 document.forms[0].submit();
	 
}

function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}


function checkForRefer(obj)
{
//alert("size "+obj.checked)
var isRef=document.getElementsByName("isReferred")[0];
	if(obj.checked==true)
	{
		//countDeptVisit=countDeptVisit+1;
	}
	else
	{
		//countDeptVisit=countDeptVisit-1;
	}
	if( (countDeptVisit>1) && (isRef.checked==true))
	{
		
		isRef.checked=false;
		alert("Please Select Only One Department For Visit Stamp With Refer ")
		 checkIsReferred(isRef);
	}
	
}

function printPage() 
{
	//var frameElement = parent.document.getElementById("f2"); 
	//var win = frameElement.contentWindow ;
	if((typeof(document.getElementsByName('oldDepartmentVisit')[0])!="undefined") && document.getElementsByName('oldDepartmentVisit')[0].value=='on'){
		//do nothing
		}else{
	window.print();
	//manageBarcodePrintProcess();
	}
}
/* function callThisOnload(){	
	alert("onload");
	//alert(document.getElementsByName('print')[0].value)
	if(document.getElementsByName('print')[0].value=='1')
	{
		//document.getElementById('divCardPrint').style.display="block"
		printPage();
		document.getElementsByName('print')[0].value='0';
		//document.getElementById('divCardPrint').style.display="none"
	}
	//document.getElementsByName("departmentCode")[0].focus()
	//var patCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	//showState();
	//showLocation();
	//document.getElementsByName("departmentCode")[0].focus;
	//sendData();
	//checkPatientCategory();
	//enableSpouseField();
	/* if(document.getElementsByName("errorMessage")[0].value!=""){
		alert(document.getElementsByName("errorMessage")[0].value)
		document.getElementsByName("errorMessage")[0].value=""
	} */
//} */


function validateOnRequest(e){
	var valid=false;
	document.getElementsByName('onRequestVisit')[0].value=1;
 //document.getElementsByName("departmentsToVisitStamp")[0].value=e;
//alert('departmentsToVisitStamp...'+document.getElementsByName('departmentsToVisitStamp')[0].value);
if(countDeptVisit==0)
{
	document.getElementsByName('hiddenEpisode')[0].value=e;
	// alert('hiddenEpisode....'+document.getElementsByName('hiddenEpisode')[0].value);
	document.getElementsByName('hcode')[0].value=e;
	//alert(document.getElementsByName('oldDepartmentVisit')[0].value);
	if(document.getElementsByName('oldDepartmentVisit')[0].value=='on')
		ValidateDepartmentOldDeptVisit();
	else
		ValidateDepartmentNewDeptVisit(document.getElementsByName('departmentsToVisitStamp')[0]);
		//ValidateDepartmentNewDeptVisit();
	
	// alert('hcode....'+document.getElementsByName('hcode')[0].value);
	valid=true;
}
else
{	
		var isRef=document.getElementsByName("isReferred")[0];
		if(isRef.checked==true)
		{
		isRef.checked=false;
		alert("Please Select Only One Department For Visit Stamp With Refer")
		checkIsReferred(isRef);
		}
		else
		{
			valid=true;
		}
	
}
return valid;
}
//function ValidateDepartmentOldDeptVisit(callerChecker) {
function ValidateDepartmentOldDeptVisit() {
    var isValid = true;
  <%--   alert("Inside ValidateDepartmentOldDeptVisit()");
    alert("patPrimaryCatgrpCode: "+document.getElementsByName("patPrimaryCatGrpCode")[0].value);
   if(document.getElementsByName("patPrimaryCatGrpCode")[0].value==<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY%>)
	{
		var name= document.getElementsByName("clientCode")[0].options[document.getElementsByName("clientCode")[0].selectedIndex].text;
		document.getElementsByName("clientName")[0].value=name;
		//alert("clientName: "+document.getElementsByName("clientName")[0].value);
	} --%>
 //   alert("document.getElementsByName('isRefferInOut')[0].checked "+document.getElementsByName("isRefferInOut")[0].checked)
//alert('inside ValidateDepartmentOldDeptVisit....');
if(document.getElementsByName('onRequestVisit')[0].value!=1)
{
	if(document.getElementsByName("departmentsToVisitStamp").length!=0)
	{
	var isVisited=false;
		for(var visitIndex=0;visitIndex<document.getElementsByName("departmentsToVisitStamp").length;visitIndex++)
		{
			if(document.getElementsByName("departmentsToVisitStamp")[visitIndex].checked==true)
			{
				isVisited=true;
			}
		
		}
		if(isVisited==false)
		{
			alert("Please Select Department");
			return false;
		}
	}
	else
	{
	alert(" No department to visit");
	return false;
	}			
}

if(document.getElementsByName("isPatReferByList")[0].value==<%=RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE%>)
{
		return true;
		//alert("is valid true>>>>"+isValid);
}
else
{

if (document.getElementsByName("isReferred")[0].checked==true)
{	
// alert("referringInstType "+document.getElementsByName("referringInstType")[1].checked)
document.getElementsByName("isReferred")[0].value=<%=RegistrationConfig.IS_REFERRED_TRUE%>;



if (document.getElementsByName("isRefferInOut")[1].checked==true)
{	
	document.getElementsByName("isRefferInOut")[1].value=<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL%>;
	if(document.getElementsByName("patRefDoctor")[0].value=="")
		{
		alert("Enter the Doctor Name");
		document.getElementsByName("patRefDoctor")[0].focus();
		return false;
		}
	if (document.getElementsByName("referringInstType")[1].checked==true)
	{	
		document.getElementsByName("isAssociated")[0].value=<%=RegistrationConfig.IS_ASSOCIATED_FALSE%>;
		if(document.getElementsByName("patRefHospitalName")[0].value=="")
		{
		alert("Please Enter the Institute Name");
		document.getElementsByName("patRefHospitalName")[0].focus();
		return false;
		}
	}	
	else if (document.getElementsByName("referringInstType")[0].checked==true)
		{
		document.getElementsByName("isAssociated")[0].value=<%=RegistrationConfig.IS_ASSOCIATED_TRUE%>;
		//alert('document.getElementsByName("patRefGnctdHospitalCode")[0].options['+document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex+'].value   ='+document.getElementsByName("patRefGnctdHospitalCode")[0].options[document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex].value)
		if(document.getElementsByName("patRefGnctdHospitalCode")[0].options[document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex].value=="-1")
		{
		alert("Please Select the Institute Name");
		document.getElementsByName("patRefGnctdHospitalCode")[0].focus();
		return false;
		}
		if(typeof(document.getElementsByName('patRefGnctdHospitalCrno')[0]) != "undefined")
		{
		var referCrNo=document.getElementsByName('patRefGnctdHospitalCrno')[0].value;
		var referLen=referCrNo.length;
		if(referLen!=12 && referLen!=15 && referLen!=0)
		{
		alert("Referring Institute CR No. must be 12 or 15 digits only");	
		return false;
		}
		}
		}
		else
		{
		alert("Select Referring Institute Name");
		return false;
		}
}
else if (document.getElementsByName("isRefferInOut")[0].checked==true)
	{
		var flag=false;
		document.getElementsByName("isRefferInOut")[0].value=<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL%>;
	// 	alert('internal referral');
		var index=0;
		var refferringOPDEpisodeLength=document.getElementsByName("refferringOPDEpisode").length;
// 	alert('internal referral gfgfgf '+refferringOPDEpisodeLength);
	
		while(index<refferringOPDEpisodeLength)
		{
			if(document.getElementsByName("refferringOPDEpisode")[index].checked==true)
			{
			flag=true;
			break;
			}
			index=index+1;
		}
			if(flag==true)
			{
				return true;
			}
			else
			{
				alert("Please Select Internal Reffered Department");
				return false;
			}
	
	}
	else
	{
		alert("Select Referring Institute Name");
		return false;
	}
	
	
}
else
	document.getElementsByName("isReferred")[0].value=<%=RegistrationConfig.IS_REFERRED_FALSE%>;
	
	
	if(isVisited)
	{
		if(document.getElementsByName("renewalRequired")[0].value=="1" && document.getElementsByName('renewalAmount')[0].value!="0" && document.getElementsByName('renewalAmount')[0].value!="0.00")//By Mukund			
				alert('Please Collect Rs '+  document.getElementsByName('renewalAmount')[0].value);
	}
	
//if(isValid)
//{
//	var result=showMessage('Do You want to Print Card?');
//	enableBlanket();
//	if(result)
//		return isValid;
//	else
//		return false;
//		
//}
//else
//	return isValid;

	if(isValid)
		isValid=checkBeneficiaryEssentials('OLD');
	
	return isValid;

	
   

 } }

//function checkuncheckNewDepartment(Obj)
//{
//
	//if(Obj.checked==true)
	//{
	//	document.getElementsByName('newDepartmentVisit')[0].value='On';
	//}
	//else
	//{
	//	document.getElementsByName('newDepartmentVisit')[0].value='';
	//}
	//submitForm('NEWDEPARTMENTVISITDTL');
//}
//function checkuncheckOldDepartment(Obj)
//{

	//if(Obj.checked==true)
	//{
	//	document.getElementsByName('oldDepartmentVisit')[0].value='On';
	//}
	//else
	//{
	//	document.getElementsByName('oldDepartmentVisit')[0].value='';
	//}
	//submitForm('OLDDEPARTMENTVISITDTL');
//}


function checkuncheckNewDepartment(Obj)
{
  var oldvalue=document.getElementsByName('oldDepartmentVisit')[0].value;

	if(Obj.checked==true && oldvalue=="on")
	{
		document.getElementsByName('newDepartmentVisit')[0].value='On';
		document.getElementsByName('oldDepartmentVisit')[0].checked=true;
		submitForm('GETPATDTL');
	}
	else if(Obj.checked)
	{
		document.getElementsByName('newDepartmentVisit')[0].value='On';
		submitForm('NEWDEPARTMENTVISITDTL');
	}
	else if(Obj.checked==false && oldvalue!="on")
	{
		//alert("checkuncheckNewDepartment");
		submitForm('GETPATDTL');
	}		
	else{
	
		document.getElementsByName('oldDepartmentVisit')[0].value='On';
		submitForm('OLDDEPARTMENTVISITDTL');
	}
	//submitForm('NEWDEPARTMENTVISITDTL');
}
function checkuncheckOldDepartment(Obj)
{
	var newValue=document.getElementsByName('newDepartmentVisit')[0].value;
 if(Obj.checked==true && newValue=="on")
	{
		document.getElementsByName('oldDepartmentVisit')[0].value='On';
		document.getElementsByName('newDepartmentVisit')[0].checked=true;
		submitForm('GETPATDTL');
	}
	else if(Obj.checked)
	{
		document.getElementsByName('oldDepartmentVisit')[0].checked=true;
		submitForm('OLDDEPARTMENTVISITDTL');
	}
	
	else if(Obj.checked==false && newValue!="on")
	{
		//alert("checkuncheckOldDepartment");
		submitForm('GETPATDTL');
	}	
	else
	{
		document.getElementsByName('newDepartmentVisit')[0].value='On';
		submitForm('NEWDEPARTMENTVISITDTL');
	}
}
function checkReferDepartment(obj)
{

	if(document.getElementsByName('patRefGnctdHospitalDept')[0].value=='0')
	{
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false;
		document.getElementsByName('patRefHospitalDeptOther')[0].focus();
		}
	else
	{
	
	document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true;
	
	document.getElementsByName('patRefHospitalDeptOther')[0].value="";
	document.getElementsByName('patRefGnctdHospitalCrno')[0].focus();
	
	
	}
}

//Submit Tile by setting hmode
var fromDeptCode="";
 var refIndex="";
/*function getBillAmountForGrouping(fromDept,index)
{	
	 //  alert("refferringOPDEpisode "+document.getElementsByName("refferringOPDEpisode")[0].value)
	//alert(document.getElementsByName("isRefferInOut")[0].checked)
	//alert("isRefferInOut "+document.getElementsByName("isRefferInOut")[0].value)
	var referInOut=document.getElementsByName("isRefferInOut")[0].checked;
	var toDeptCode=document.getElementsByName("departmentUnitCode")[0].value;
	
	if(fromDept!=="")
	{
	// document.getElementsByName("selectedFromDept")[0].value=fromDept;
	fromDeptCode=fromDept;
	refIndex=index;
	}
	else{}
	var isrefer=document.getElementsByName("isReferred")[0].checked;
	// alert("toDeptCode "+toDeptCode+" isrefer "+isrefer+" fromDeptCode "+fromDeptCode+" referInOut "+referInOut )
	if( ((toDeptCode!="") && (toDeptCode!="-1") ) && (isrefer==true) && (fromDeptCode!="") && (referInOut==true) )
	{
		//alert("inside If ")	
		//alert("isrefer before submit "+isrefer)
		document.getElementsByName("selectedFromDept")[0].value=fromDeptCode;
		document.getElementsByName("referingRowIndex")[0].value=refIndex;
		document.getElementsByName("entryDate")[0].value=document.getElementsByName("episodeDate")[parseInt(refIndex)].value;
		document.getElementsByName("episodeVisitNo")[0].value=document.getElementsByName("visitNo")[parseInt(refIndex)].value;
		document.getElementsByName("isReferred")[0].value="on";
		submitForm("GETREGFEE"); 
		//alert("isrefer "+isrefer)
	//	alert("selectedFromDept"+document.getElementsByName("selectedFromDept")[0].value)
		
	}
	else
	{
	//	alert("inside Else patBillAmountWithoutGrouping "+document.getElementsByName("patBillAmountWithoutGrouping")[0].value)	
		document.getElementsByName("patAmountCollected")[0].value=document.getElementsByName("patBillAmountWithoutGrouping")[0].value;
		
	}
	
}*/

function submitTile(mode){
	elem = document.getElementsByName('hmode')[0];
	elem.value = mode;
	//alert(elem.value);
	document.forms[0].submit();
} 

function getRefer()
{  //  alert("index "+index)
	if(document.getElementsByName("isReferred")[0]){
		var isRef=document.getElementsByName("isReferred")[0];
		 //alert("isRef.checked "+isRef.checked)
		var isRefInternalExternal=document.getElementsByName("referInternalExternal")[0].value;
		var index=document.getElementsByName("referingRowIndex")[0].value;
		if(isRef.checked==true){
			document.getElementById("checkReferral").style.display="block";
			document.getElementById("isRefferDiv").style.display="block";
			//alert("isRefInternalExternal"+isRefInternalExternal)
			if(isRefInternalExternal=="I")
			{	
				//alert("Inside isRefInternalExternal I");
				document.getElementsByName("isRefferInOut")[0].checked=true;
			 	showInternalForGrouping();
			 	showInternal(document.getElementsByName("isRefferInOut")[0]);
				//alert(document.form[0].getElementsByName("isRefferInOut").value)
			 	if(index!="")
			 	{
			 		document.getElementsByName("refferringOPDEpisode")[parseInt(index)].checked=true;
			 		fromDeptCode=document.getElementsByName("selectedFromDept")[0].value;
			 		refIndex=document.getElementsByName("referingRowIndex")[0].value;
			 	}
	 		}
	 		if(document.getElementsByName("isRefferInOut")[1].checked)
			{	
				
				document.getElementsByName("isRefferInOut")[1].checked=true;
			 	showExternal(document.getElementsByName("isRefferInOut")[1]);
	 		} 
	 	}
		
		//To show the Internal Referal Radio alone in the CrossConsulatant Cases added by Singaravelan on 06-Nov-2014
		if(document.getElementsByName("patIsCrossConsultantWithReferal")[0].value=="1"){
			document.getElementById("externalReferalRadio").style.display="none";
		}
	 }	        
}

/*By Mukund on 28.07.2016  */
function sendData(){
	var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
	//alert("today: "+today);
	var _todayPlus30Days=new Date();
	//alert("_todayPlus30Days: "+_todayPlus30Days);
		_todayPlus30Days.setDate(_todayPlus30Days.getDate() + 29);
		_todayPlus30Days=moment(_todayPlus30Days).format('DD-MMM-YYYY');//_todayPlus30Days.toLocaleFormat('%d-%b-%Y');
	//alert("_todayPlus30Days: "+_todayPlus30Days);
	//$("#creditLetterDateId").datepicker("setDate", today);
	//$("#cardvalidityDateId").datepicker("setDate", _todayPlus30Days);
		$('#creditLetterDateId').DatePicker({ format: 'd-M-Y',default_position :'below',start_date:today, onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}}).val(today);
		
		 $("#creditLetterDateId").change(function(){
				var _dateStr=$(this).val().replace(/-/g,' ');
				var _date = new Date(_dateStr);
				_date.setDate(_date.getDate() + 29);
				_date=moment(_date).format('DD-MMM-YYYY');//_date.toLocaleFormat('%d-%b-%Y');
				$('#cardvalidityDateId').DatePicker({format: 'd-M-Y',default_position :'below',start_date:_date, onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}}).val(_date);	
		});
		
		$('#cardvalidityDateId').DatePicker({format: 'd-M-Y',default_position :'below',start_date:_todayPlus30Days, onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}}).val(_todayPlus30Days);

}
/*End: Mukund */

function callThisOnload(){
//alert("isrefer "+document.getElementsByName("isPatReferByList")[0].value);
 //showDepartment();
// 	alert("fdfdfdfdf "+document.getElementsByName("isPatReferByList")[0].value)
	//alert("onload");
	//alert(document.getElementsByName('print')[0].value)
	if(document.getElementsByName('print')[0].value=='1')
	{	
		//document.getElementById('divCardPrint').style.display="block"
		if(get_object("divBarCodeControl")!=null)
			get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
		if(get_object("divBarCodeControlForBill")!=null)
		{
			get_object("divBarCodeControlForBill").innerHTML=DrawCode39Barcode(get_object("divBarCodeControlForBill").innerHTML, 0);
		}
		printPage();
		document.getElementsByName('print')[0].value='0'
		//document.getElementById('divCardPrint').style.display="none"
	}else if(document.getElementsByName('print')[0].value=='2'){
		manageBarcodePrintProcess();
		document.getElementsByName('print')[0].value='0'
		}
		focusCrNo();
		// document.getElementsByName("refferringOPDEpisode")[0].checked=true;
		// document.getElementsByName("isRefferInOut")[0].checked="on";
		getRefer();
		sendData();
		//alert(document.getElementsByName('htmlCode')[0].value!="null")
		
		if((document.getElementsByName('captureMandatoryField')[0].value=="true"))
		{
			enableBlanket();
		}
		
		if(document.getElementsByName("errorMessage")[0].value!=""){
			alert(document.getElementsByName("errorMessage")[0].value)
			document.getElementsByName("errorMessage")[0].value=""
		}
		
		
		//if(document.getElementsByName('saveSuccessful')[0].value=='true')
		//{
		/*valid=confirm('Do Not Print Card?');
		if(!valid)
		{
		submitForm("PRINT");
		}
		else	
		{
		<%//session.removeAttribute(RegistrationConfig.REGISTRATION_SLIP_OBJECT);%>
		}
		*/
		//alert("in success true");
		//showMessage('Do You want to Print Card?')
		//enableBlanket();
		//}

		
}

function showMessage(message){
	document.getElementById("confirmMsg").style.display="block"
	document.getElementById("alertMessage1").innerHTML=message;
	document.getElementsByName("noButton")[0].focus();
}

function closeAlert(flag){
	//alert("In close Alert");
	document.getElementById("confirmMsg").style.display="none"
	disableBlanket();
	if(flag){
		document.getElementsByName('isPrintCard')[0].value='L';
		//alert(document.getElementsByName('isPrintCard')[0].value);
		//submitForm("PRINT");
		submitFormOnValidate(true,'CHECKMANDATORY');			
		return true;
	}else{
		document.getElementsByName('saveSuccessful')[0].value='false';
		document.getElementsByName('isPrintCard')[0].value='D';
		//alert(document.getElementsByName('isPrintCard')[0].value);
		focusCrNo();
		submitFormOnValidate(true,'CHECKMANDATORY');			
		return true;
	}
}

function enableBlanket()
{
	//alert("in blanket");
	document.getElementById("blanket").style.display="block";
}
function disableBlanket()
{
	//alert("in blanket diable");
	document.getElementById("blanket").style.display="none";
}
	
function focusCrNo(){
// document.getElementsByName("crNo3")[0].focus();
/*if(document.getElementsByName("patPrimaryCatCode")[0].value!="" && document.getElementsByName("patPrimaryCatCode")[0].value!=null)
	document.getElementsByName("patPrimaryCatCode")[0].focus();
*/
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
		
 }
 
function populate(e){ 
document.getElementsByName('crNoToRetrieve')[0].value=e;
submitForm("DGNDETAIL"); 

} 

function checkIsReferred(e){

if (e.checked==true){
	
	//document.getElementById("common").style.display="none";
	document.getElementById("checkReferral").style.display="block";
	document.getElementById("isRefferDiv").style.display="block";
	//getBillAmountForGrouping('','');
	
	getChargeIsCrossConsultant();
	
	}
else{	
	//document.getElementById("common").style.display="block";
	if(refIndex!="")
	{
	document.getElementsByName("refferringOPDEpisode")[parseInt(refIndex)].checked=false;
	}
	// alert("sdsdsdsdkkko")
	document.getElementById("checkReferral").style.display="none";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("associated").style.display="none";
	document.getElementById("externalRefer").style.display="none";
	document.getElementById("internalRefer").style.display="none";
	document.getElementById("isRefferDiv").style.display="none";
	document.getElementsByName("referringInstType")[0].checked=false;
	document.getElementsByName("referringInstType")[1].checked=false;
	document.getElementsByName("isRefferInOut")[0].checked=false;
	document.getElementsByName("isRefferInOut")[1].checked=false;
// 	document.getElementsByName("refferringOPDEpisode")[parseInt(refIndex)].checked="false";
	// alert(refIndex);
	fromDeptCode="";
 	refIndex="";	
 	
	//getBillAmountForGrouping('','');
	
	getBackNormalChargeIsCrossConsultant();
	
	}
}

//To Show the Normal Change Again on Uncheck the is Refered added by Singaravelan on 16-Oct-2014
function getBackNormalChargeIsCrossConsultant(){
	
	var amtWithoutCrConsultation=document.getElementsByName("patAmountNCrConsultation")[0].value;
	if(document.getElementsByName("patIsCrossConsultantWithReferal")[0].value=="1"){
		document.getElementsByName("patAmountCollected")[0].value=amtWithoutCrConsultation;
	}
	
}

//To Show the CRoss Consultation Change Again on check the is Refered added by Singaravelan on 16-Oct-2014
function getChargeIsCrossConsultant(){
	
	var amtWithCrConsultation=document.getElementsByName("patAmountCrConsultation")[0].value;
	if(document.getElementsByName("patIsCrossConsultantWithReferal")[0].value=="1"){
		document.getElementsByName("patAmountCollected")[0].value=amtWithCrConsultation;
	}
	getRefer();
	
}


function showInternal(e){
	
	//alert("showInternal");
	document.getElementById("internalRefer").style.display="block";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("commonReferInternal").style.display="none";
	document.getElementById("commonReferInternal_1").style.display="none";	
	document.getElementById("commonReferExternal").style.display="none";
	document.getElementById("commonReferExternal_associated").style.display="none";
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("associated").style.display="none";
	document.getElementById("externalRefer").style.display="none";
	//getBillAmountForGrouping("","");
}

function showInternalForGrouping(){
	
	document.getElementById("internalRefer").style.display="block";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("commonReferInternal").style.display="none";
	document.getElementById("commonReferInternal_1").style.display="none";	
	document.getElementById("commonReferExternal").style.display="none";
	document.getElementById("commonReferExternal_associated").style.display="none";
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("associated").style.display="none";
	document.getElementById("externalRefer").style.display="none";
	
}

function showExternal(e){
	
	//document.getElementById("common").style.display="none";
	document.getElementById("commonRefer").style.display="none";
	document.getElementById("commonReferInternal").style.display="none";
	document.getElementById("commonReferInternal_1").style.display="none";
	document.getElementById("internalRefer").style.display="none";
	//document.getElementById("commonReferExternal").style.display="block";
	//document.getElementById("commonReferExternal_associated").style.display="none";
	//document.getElementById("commonReferExternal_other").style.display="none";
	//document.getElementById("associated").style.display="none";

	document.getElementById("externalRefer").style.display="block";
	//document.getElementById("internalRefer").style.display="none";
	document.getElementsByName("referringInstType")[0].checked=true;
	showAssociated(document.getElementsByName("referringInstType")[0]);
	//getBillAmountForGrouping("","");
}

function showAssociated(e){

	//document.getElementById("common").style.display="block";
	document.getElementById("commonRefer").style.display="block";
	document.getElementById("commonReferExternal").style.display="block";
	document.getElementById("commonReferExternal_associated").style.display="block";		
	document.getElementById("associated").style.display="block";
	document.getElementById("commonReferExternal_other").style.display="none";
	document.getElementById("internalRefer").style.display="none";

}

function showOthers(e){

	//document.getElementById("common").style.display="block";
	document.getElementById("commonRefer").style.display="block";
	document.getElementById("commonReferExternal").style.display="block";
	document.getElementById("commonReferExternal_other").style.display="block";
	document.getElementById("commonReferExternal_associated").style.display="none";		
	document.getElementById("associated").style.display="none";
	document.getElementById("internalRefer").style.display="none";
}

function ValidateDepartmentNewDeptVisit(field)
{

	if(document.getElementsByName("patPrimaryCatGrpCode")[0].value==<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY%>)
	{
		var name= document.getElementsByName("clientCode")[0].options[document.getElementsByName("clientCode")[0].selectedIndex].text;
		document.getElementsByName("clientName")[0].value=name;
		//alert("clientName: "+document.getElementsByName("clientName")[0].value);
	}
	
	var isValid = true;
	//var depCode=document.getElementsByName("departmentUnitCode")[0].value;
	var depCode=document.getElementsByName("toDepartmentCode")[0].value;
	<%-- if((document.getElementsByName("isPatReferByList")[0].value==<%=RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE%>)||
		(document.getElementsByName("isPatReferByList")[0].value==<%=RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE%>))
    {
    	isValid=true;     
	alert("inside IF") ;
	}
	else
	{ --%>
	if( (depCode=="") || (depCode=="-1") )
	{
		alert("Please Select Department");
		isValid=false;
	}
	
	else
	{
    	<%-- if(validateAgeForDeptToVisit('<%=RegistrationConfig.MAX_AGE_TO_REGISTER_IN_CHILD_DEPT%>','<%=RegistrationConfig.CHILD_DEPT_CODE%>')
   			<his:referMandatory property="departmentUnitCode" /> 
    		){
    		isValid=true;
    	}
    	else{
    		return false;
    	} --%>
    	
    	
		//alert('inside ValidateDepartmentNewDeptVisit....');
		if (document.getElementsByName("isReferred")[0].checked==true)
		{	
			document.getElementsByName("isReferred")[0].value=<%=RegistrationConfig.IS_REFERRED_TRUE%>;
			if (document.getElementsByName("isRefferInOut")[1].checked==true)
			{	
				document.getElementsByName("isRefferInOut")[1].value=<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_EXTERNAL%>;
				if(document.getElementsByName("patRefDoctor")[0].value=="")
				{
					alert("Enter Doctor Name");
					document.getElementsByName("patRefDoctor")[0].focus();
					return false;
				}
				if (document.getElementsByName("referringInstType")[1].checked==true)
				{	
					document.getElementsByName("isAssociated")[0].value=<%=RegistrationConfig.IS_ASSOCIATED_FALSE%>;
					if(document.getElementsByName("patRefHospitalName")[0].value=="")
					{
						alert("Enter Referring Institute Name");
						document.getElementsByName("patRefHospitalName")[0].focus();
						return false;
					}
				}	
				else if (document.getElementsByName("referringInstType")[0].checked==true)
				{
					document.getElementsByName("isAssociated")[0].value=<%=RegistrationConfig.IS_ASSOCIATED_TRUE%>;
				 // alert('document.getElementsByName("patRefGnctdHospitalCode")[0].options['+document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex+'].value   ='+document.getElementsByName("patRefGnctdHospitalCode")[0].options[document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex].value)
					if(document.getElementsByName("patRefGnctdHospitalCode")[0].options[document.getElementsByName("patRefGnctdHospitalCode")[0].selectedIndex].value=="-1")
					{
						alert("Select Referring Institute Name");
						document.getElementsByName("patRefGnctdHospitalCode")[0].focus();
						return false;
					}
					if(typeof(document.getElementsByName('patRefGnctdHospitalCrno')[0]) != "undefined")
					{
					var referCrNo=document.getElementsByName('patRefGnctdHospitalCrno')[0].value;
					var referLen=referCrNo.length;
					if(referLen!=12 && referLen!=15 && referLen!=0)
					{
					alert("Referring Institute CR No. must be 12 or 15 digits only");	
					return false;
					}
					}
				}
				else
				{
					alert("Select Referring Institute Name");
					return false;
				}
			}
			else if (document.getElementsByName("isRefferInOut")[0].checked==true)
			{
		
				document.getElementsByName("isRefferInOut")[0].value=<%=RegistrationConfig.IS_REFERRED_IN_OUT_ACCEPT_INTERNAL%>;
		var index=0;
			var refferringOPDEpisodeLength=document.getElementsByName("refferringOPDEpisode").length;
	 	//alert('internal referral gfgfgf '+refferringOPDEpisodeLength);
		var flag=false;
			while(index<refferringOPDEpisodeLength)
			{
				if(document.getElementsByName("refferringOPDEpisode")[index].checked==true)
				{
				flag=true;
				break;
				}
				index=index+1;
			}
			//alert(flag);
				if(flag==true)
				{
					return true;
				}
				else
				{
					alert("Please Select Internal Referred Department");
					return false;
				}
		
			}
			else
			{
				alert("Select Referring Institute Name");
				return false;
			}
		
		
		}
		else{
			document.getElementsByName("isReferred")[0].value=<%=RegistrationConfig.IS_REFERRED_FALSE%>;

			value2=document.getElementsByName("departmentUnitCode")[0].value;
      		if(field)
      			value=field.value;
      		else
      			value="";
      
         	/* if ((value.length == 0)||(value=='-1'))
            { 
               	if(value2=='-1')
               	{
            		alert("Department is required");
                	isValid = false;
                	document.getElementsByName("departmentUnitCode")[0].focus();
              	}
            } */
		}
		
			//alert("inside ELSE") ;
	}
	//}
	if(document.getElementsByName('oldDepartmentVisit')[0].checked==true && document.getElementsByName('newDepartmentVisit')[0].checked==true)
	{
		if(isValid){
			isValid=ValidateDepartmentOldDeptVisit()&&checkBeneficiaryEssentials('NEW');
		}
	}	
	
	if((document.getElementsByName('oldDepartmentVisit')[0].checked==true && document.getElementsByName('newDepartmentVisit')[0].checked==true)||(document.getElementsByName('oldDepartmentVisit')[0].checked==true && document.getElementsByName('newDepartmentVisit')[0].checked==false))
	{
		isValid=checkBeneficiaryEssentials('NEW');
		return isValid;
	}
	else
	{
		if(isValid)
		{	
			isValid=checkBeneficiaryEssentials('NEW');
			if(document.getElementsByName("patAmountCollected")[0].value!="0" && document.getElementsByName("patAmountCollected")[0].value!="0.00")			
				alert('Please Collect Rs '+  document.getElementsByName("patAmountCollected")[0].value);
			//alert("checkbeneficiary returns: "+isValid);
			return isValid;
		}
	}
	
	//if(isValid)
	//{
	//var result=showMessage('Do You want to Print Card?');
	//alert(result);
	//enableBlanket();
	//if(result)
	//	return isValid;
	//else
	//	return false;
	//	
	//}
	//else
		//return isValid;
	
	//alert("is referred = "+document.getElementsByName("isReferred")[0].checked)
 	// alert(isValid);               
	// alert(isValid)
  	//return isValid;
} 

<%-- function checkBeneficiaryEssentials(mode)
{ 
	alert("Inside BeneficiaryessentialsCheck");
	var isValid=false;
	var today = new Date();
	today.setHours(0,0,0,0);
	
	
	//alert(document.getElementsByName("patPrimaryCatGrpCode")[0].value);
	if(document.getElementsByName("patPrimaryCatGrpCode")[0].value!=<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY%> &&
			document.getElementsByName("patPrimaryCatGrpCode")[0].value!=<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE%>){//alert("1");
		isValid=true;
	}
	else{
	
	//var tempDate=document.getElementsByName("creditLetterDate")[0].value.toString().split("-");
	//alert("tempDate: "+tempDate);
	//alert(document.getElementsByName("creditLetterDate")[0].value);
	//
		
	//var testDate=tempDate[1]+" "+tempDate[0]+","+tempDate[2];
	//alert("testDate: "+testDate);
	//var letterDate = new Date(testDate);
	
	if(document.getElementsByName("patPrimaryCatGrpCode")[0].value==<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY%>){
		alert("Inside Beneficiary validation1");
		alert(document.getElementsByName("creditLetterRefNo")[0].value)
		if(document.getElementsByName("creditLetterRefNo")[0].value==""){
			alert("Please Enter Reference Letter No.");
			document.getElementsByName("creditLetterRefNo")[0].focus();
			return false;
		}
		if(document.getElementsByName("creditLetterDate")[0].value==""){
			alert("Please Enter Reference Letter Date.");
			document.getElementsByName("creditLetterDate")[0].focus();
			return false;
		}
		if(document.getElementsByName("staffCardNo")[0].value==""){
			alert("Please Enter Staff No.");
			document.getElementsByName("staffCardNo")[0].focus();
			return false;
		}
		//by Mukund on 29.07.2016
		 if(document.getElementsByName("letterType")[0].value=="-1")
			{
			alert("Please select Letter Type");
			document.getElementsByName("letterType")[0].focus();
			return false;
			}
		
		if(dateNotInRange()){
			//alert("Reference Letter Date Should be lesser than Today.");
			//document.getElementsByName("creditLetterDate")[0].focus();
		 return false;
		}
		
		else
			{isValid=true;//alert(isValid+" :isValid in else");
			}
	
	}
	else if(document.getElementsByName("patPrimaryCatGrpCode")[0].value==<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE%>){
		alert("Inside Beneficiary validation2");
		if(document.getElementsByName("agsNo")[0].value==""){
			alert("Please Enter Arogyasri No.");
			document.getElementsByName("agsNo")[0].focus();
		}			
		else
			isValid=true;			
	}
	}	
	alert("is valid returned by check beneficiary "+isValid);
	return isValid;

} --%>
/*Added By Mukund on 01.08.2016 to checkBeneficiary Details based on mode */
function checkBeneficiaryEssentials(mode)
{	//alert("INSIDE CheckBeneficiary");
	var isValid=false;
	var today = new Date();
	today.setHours(0,0,0,0);
	var isRenewReq=document.getElementsByName("renewalRequired")[0].value;
	//alert(isRenewReq);
	if(document.getElementsByName("patPrimaryCatGrpCode")[0].value!=<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY%> &&
			document.getElementsByName("patPrimaryCatGrpCode")[0].value!=<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE%>)
	{
		isValid=true;
	}
	else
	{	
		if(mode=='NEW'||(mode=='OLD' && isRenewReq=="1"))
		{
			if(document.getElementsByName("patPrimaryCatGrpCode")[0].value==<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY%>)
			{
				/* if(document.getElementsByName("creditLetterRefNo")[0].value=="")
				{
					alert("Please Enter Reference Letter No.");
					document.getElementsByName("creditLetterRefNo")[0].focus();
					return false;
				}
				if(document.getElementsByName("creditLetterDate")[0].value=="")
				{
					alert("Please Enter Reference Letter Date.");
					document.getElementsByName("creditLetterDate")[0].focus();
					return false;
				}
				 if(document.getElementsByName("staffCardNo")[0].value=="")
				{
					alert("Please Enter Staff No.");
					document.getElementsByName("staffCardNo")[0].focus();
					return false;
				} 
				if(document.getElementsByName("letterType")[0].value=="-1")
				{
					alert("Please select Letter Type");
					document.getElementsByName("letterType")[0].focus();
					return false;
				}
				if(document.getElementsByName("clientCode")[0].value=="-1")
				{
				 alert("Please Select Company");
				 document.getElementsByName("clientCode")[0].focus();
				 return false;
				 
				} */
				if(dateNotInRange())
				{
					 return false;
				}
				else
				{
					isValid=true;
				}
		
			}
			else if(document.getElementsByName("patPrimaryCatGrpCode")[0].value==<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE%>)
			{
				if(document.getElementsByName("agsNo")[0].value=="")
				{
					alert("Please Enter Arogyasri No.");
					document.getElementsByName("agsNo")[0].focus();
				}			
				else
					isValid=true;			
			}
		}
		else if(mode=='OLD' && isRenewReq=="0")
		{
			isValid=true;
		}
	}	
		//alert("is valid returned by check beneficiary "+isValid);
		return isValid;

} 
//End :Mukund       

function renewalConfirmation()
{
	//alert("In renewalConfirmation");
	var valid=false;
	valid=confirm("DO you want to Renew the Registration ?");
	return valid;
}

function renewalValidation()
{
 	var len;
	var isValid = true;
	//alert("In renewalValidation");
	count=0;
	
	len=document.getElementsByName("departmentsToVisitStamp").length;
	
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName("departmentsToVisitStamp")[i].checked){
				count++;
				}
				}
	
	if(count==0){
		isValid = false;
		alert("Select a  department");
				}
	else
	{
	isValid = true;
	alert('Please Collect Rs '+  document.getElementsByName('amount')[0].value)
	}

return isValid;
}

function showDetail(obj)
{
	//alert("obj...."+obj.name);
	//alert(obj.value);
	var patCrNoWithIndex=obj.value;
	
	document.getElementsByName("isPatReferByList")[0].value="1";
	document.getElementsByName("onlineReferedIndex")[0].value=patCrNoWithIndex.substring(0,patCrNoWithIndex.indexOf('#'));
	document.getElementsByName("indexID")[0].value=patCrNoWithIndex.substring(0,patCrNoWithIndex.indexOf('#'));
	document.getElementsByName("selectedReferal")[0].value=patCrNoWithIndex.substring(patCrNoWithIndex.indexOf('#')+1,patCrNoWithIndex.indexOf('$'));
	document.getElementsByName("patCrNo")[0].value=patCrNoWithIndex.substring(patCrNoWithIndex.indexOf('$')+1);
	// alert("pat cr no "+showDetail.value)
//	alert("is refer by list === "+document.getElementsByName("isPatReferByList")[0].value)
//alert("in");
	submitForm("GETPATDTL"); 
}
function showDepartment()
{
	if(document.getElementsByName("isPatReferByList")[0].value=="1")
	{
//		alert("in true")
		document.getElementById("labelDepartment").style.display="none";
		document.getElementById("controlDepartment").style.display="";
		document.getElementById("labelDepartment1").style.display="none";
		document.getElementById("controlDepartment1").style.display="";
	//	document.getElementById("referDetailId").style.display="none";
		
	}
	else
	{
//		alert("in false")
		document.getElementById("labelDepartment").style.display="";
		document.getElementById("controlDepartment").style.display="none";
			document.getElementById("labelDepartment1").style.display="";
		document.getElementById("controlDepartment1").style.display="none";
	//	document.getElementById("referDetailId").style.display="";
	}	
}

function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

function enableBlanket()
{

	document.getElementById("blanket").style.display="block";
	document.getElementById("mandaatoryFields").style.display="block";
	//document.getElementsByName("patternRepeatAfter")[0].focus();
	
	
}
function disableBlanket()
{



	document.getElementById("blanket").style.display="none";
	document.getElementById("mandaatoryFields").style.display="none";
	document.getElementsByName('captureMandatoryField')[0].value="false"
	//alert(document.getElementsByName('captureMandatoryField')[0].value)

	
}

function showAppointmentDetail(obj)
{
	//alert("obj...."+obj.name);
	//alert(obj.value);
	var patCrNoWithIndex=obj.value;
	
	document.getElementsByName("isPatReferByList")[0].value="3";
	document.getElementsByName("onlineReferedIndex")[0].value=patCrNoWithIndex.substring(0,patCrNoWithIndex.indexOf('#'));
	document.getElementsByName("indexID")[0].value=patCrNoWithIndex.substring(0,patCrNoWithIndex.indexOf('#'));
	document.getElementsByName("selectedReferal")[0].value=patCrNoWithIndex.substring(patCrNoWithIndex.indexOf('#')+1,patCrNoWithIndex.indexOf('$'));
	document.getElementsByName("patCrNo")[0].value=patCrNoWithIndex.substring(patCrNoWithIndex.indexOf('$')+1,patCrNoWithIndex.indexOf('@'));
	document.getElementsByName("departmentCode")[0].value=patCrNoWithIndex.substring(patCrNoWithIndex.indexOf('@')+1,patCrNoWithIndex.indexOf('!'));
	document.getElementsByName("department")[0].value=patCrNoWithIndex.substring(patCrNoWithIndex.indexOf('!')+1,patCrNoWithIndex.indexOf('%'));
	document.getElementsByName("patAptNo")[0].value=patCrNoWithIndex.substring(patCrNoWithIndex.indexOf('%')+1);

	// alert("pat cr no "+showDetail.value)
//	alert("is refer by list === "+document.getElementsByName("isPatReferByList")[0].value)
	submitForm("GETPATDTL"); 
}
function dateNotInRange()
{
	if(document.forms[0].creditLetterDate.value!=""){
	var _sysDate=new Date();
	var _chkDate=convertStrToDate(document.forms[0].creditLetterDate.value,"dd-Mon-yyyy");

	var timeDiff = Math.abs(_sysDate.getTime() - _chkDate.getTime());
	var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
	if(_chkDate>_sysDate){
		alert("Refrence Letter Date Should not be Greater than Sysdate");
		document.forms[0].creditLetterDate.value="";
		return true;
	}		
	else if(diffDays>30){
		
		alert("Refrence Letter Date must be within 30 Days");
		document.forms[0].creditLetterDate.value="";
		return true;
	}
	else
		return false;
}
return false;
}
//By Mukund for patient list acccording to selected department/unit
function setPatientList(obj)
{
	var i;
	var allrows = document.getElementsByName("allPatientList");
	
	for(i=0;i<allrows.length;i++)
		allrows[i].style.display="none";
	
	for(i = 0; i < allrows.length; i++)
	{	var str = obj.value.toString();
		var depCode = allrows[i].id.split("@")[0];

		if(str==depCode)//this check is required to show department specific list only 
			allrows[i].style.display="";

		if(str==0)//this check is required to show all patient list when all unit is selected
			allrows[i].style.display="";

	}
	document.getElementsByName("searchId")[0].value="1";
	document.getElementsByName("searchValue")[0].value="";
}
//to search the patient list for desired values
function searchValueValidation()
{
	var schVal= document.getElementsByName("searchValue")[0].value;
	schVal=schVal.trim();
	if(schVal=="")
	{	
			alert("Please Enter Search Value");
			document.getElementsByName("searchValue")[0].focus();
			return false;
	}
	var searchType= document.getElementsByName("searchId")[0].value;
	if(searchType=="1")//Appointment no
	{
		var n = schVal.search(/[a-z,.>?:;!@#$%^&*()_]/i);
		if(n!="-1")
		{	alert("Please Enter Number Only");
			return false;}
		else
			searchPatientList(schVal, "1");
	}
	if(searchType=="2")//Name
	{
		var n = schVal.search(/[0-9,.>?:;!@#$%^&*()_]/i);
		if(n!="-1")
		{	alert("Please Enter alphabets Only");
			return false;}
		else
			searchPatientList(schVal, "2");
	}
	if(searchType=="3")//contact no
	{
		var n = schVal.search(/[a-z,.>?:;!@#$%^&*()_]/i);
		if(n!="-1")
		{	alert("Please Enter Number Only");
			return false;}
		else
			searchPatientList(schVal, "3");		
	}
	if(searchType=="4")//crno
	{
		var n = schVal.search(/[a-z,.>?:;!@#$%^&*()_]/i);
		if(n!="-1")
		{	alert("Please Enter Number Only");
			return false;}
		else
			searchPatientList(schVal, "4");		
	}
}
function searchPatientList(schVal, searchType)
{
	var allrows = document.getElementsByName("allPatientList");

	for(i=0;i<allrows.length;i++)
		allrows[i].style.display="none";
		
	for(i = 0; i < allrows.length; i++)
	{
		var str = allrows[i].id.split("@");
		if(searchType=="1")//appointment no
		{
			var aptno=str[1];
			if(aptno==schVal)
				allrows[i].style.display="";
		}
		else if(searchType=="2")//first name
		{
			var name1 = schVal.toString();
			name1 = name1.toUpperCase();
			
			var nameArr=str[2].split("&");
			var fname, mname, lname, fullName;
			
			fname = nameArr[0];
			mname =	nameArr[1];
			lname =	nameArr[2];
			
			fname = fname.toString().toUpperCase();
			mname = mname.toString().toUpperCase();
			lname = lname.toString().toUpperCase();
			
			//alert(name[0]+"\n"+name[1]+"\n"+name[2]);
		
			if(name1==fname||name1==mname||name1==lname)
				allrows[i].style.display="";

			if(mname!="")
				fullName=fname+" "+mname+" "+lname;
			else
				fullName=fname+" "+lname;
						//alert(fullName+"\n"+name1);
			if(fullName==name1)
				allrows[i].style.display="";
		}
		else if(searchType=="3")//contact no
		{
			var cntno=str[3]
			if(cntno==schVal)
				allrows[i].style.display="";
		}
		else if(searchType=="4")//contact no
		{
			var crno=str[4]
			if(crno==schVal)
				allrows[i].style.display="";
		}
		
	}
	document.getElementsByName('deptToSearchFrom')[0].value=0;//to reset the value of Department/unit back to All Units
}
//to reset the searching fields
function resetFields()
{
	var allrows = document.getElementsByName("allPatientList");

	for(i=0;i<allrows.length;i++)
		allrows[i].style.display="";
	document.getElementsByName('deptToSearchFrom')[0].value=0;
	document.getElementsByName("searchId")[0].value="1";
	document.getElementsByName("searchValue")[0].value="";
}
//End Mukund

//By Mukund on 25.09.2017
function manageBarcodePrintProcess()
{
	//alert(document.getElementsByName('oldDepartmentVisit')[0].value);

	if((typeof(document.getElementsByName('oldDepartmentVisit')[0])!="undefined") && document.getElementsByName('oldDepartmentVisit')[0].value=='on'){
	//do nothing
	}else{
		saveEntryForBarcodePrinting();
	}
}
  
 function saveEntryForBarcodePrinting() {
	 var url="/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";

	 var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=180,width=500,left=10,top=10');  
	   	child.moveTo(250,250);
	  	child.focus(); 
	 	if(!child.opener)
	    		child.opener = self;
/* 
	var action = "/HISRegistration/registration/transactions/SAVEFROMOUTSIDEBarcodeGeneration.action";
	//alert(action);
	$.ajax({
				url : action,
				type : "POST",
				async : true,
				dataType : "xml",
				success : function(action) {
						//alert("success!!\n"+action);
						//do nothing
				var url="/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";
			    
				var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=180,width=500,left=10,top=10');  
				  	child.moveTo(250,250);
				 	child.focus(); 
					if(!child.opener)
				   		child.opener = self;
				},
				error : function(errorMsg, textstatus, errorthrown) {
					alert('saveEntryForBarcodePrinting ' + errorMsg
							+ " textstatus::" + textstatus
							+ " errorthrown::" + errorthrown);

				}
			});
*/
/* 

	var windowWidth = 400;// $(window).width() - 50;
	var windowHeight = 350;

	var page = "/HISRegistration/registration/transactions/PRINTBarcodeGeneration.action";

	var $dialog = $('<div></div>')
			.html(
					'<iframe id="barcodeDialog_iframe" style="border: 0px; " src="'
							+ page
							+ '" width="100%" height="100%"></iframe>')
			.dialog(
					{
						autoOpen : false,
						modal : true,
						height : windowHeight,
						width : windowWidth,
						title : "Patient Barcode Slip",
						show : {
							effect : "clip"
						},
						resizable : true,
						position : [ 'top', 100 ],
						dialogClass : 'no-close custbtncolor',
						buttons : {
							"Close" : function() {
							$(this).dialog("close");
							}
						},
						open : function() {
							$('.ui-widget-overlay').addClass(
									'custoverlay');
							$('.ui-dialog-buttonpane')
									.find(
											'button:contains("Close")')
									.addClass('custbtncolor');
							$('.ui-dialog-buttonpane')
									.find(
											'button:contains("Print")')
									.addClass('custbtncolor');
						},
						close : function() {
							$(".ui-widget-overlay")
									.removeClass('custoverlay');
						}
					});
	$dialog.dialog('open');	
 */
}
//End on 25.09.2017


function validateDivDob()
{ 
	/* var PRIMARY_CATEGORY_SENIOR_CITIZEN = $('[name="seniorCitizenCatCode"]')[0].value;
	
	if ($('[name="isActualDob"]')[1].checked) // Case DOB
	{
		var DOBValActual = $("#patDOBId").val(); // in dd-Mon-yyyy
		var DOBVal = $("#patDOBId_Dup").val(); // in dd-mm-yyyy

		var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y'); // today in dd-Mon-yyyy
		
		// Date valid format
		var format = "dd-M-yy";
		var value = DOBValActual;
	 	var flg = true;
	 	//alert (value)
	 	//alert(format)
	 	var d1 = null;
	 	try
	 	{
	 		d1 = $.datepicker.parseDate(format, value);
	 	}
	 	catch(e)
	 	{
	 		flg = false;
	 	}
	 	
	 	if(!flg)
	 	{
	 		alert('This should be a date in dd-mm-yyyy format.For ex: (28-01-2014)');
	 		$("#patDOBId_Dup").focus();
	 		return false;
	 	}
	 	
	 	// Cannot be Greater Than Current Date
	 	flg = true;
		//var d1 = $.datepicker.parseDate(format, value);
		var ctdt = new Date();
		//alert("Current Date :"+ctdt);
		if(!(d1<=ctdt))
		{
	 		alert('Date Of Birth Cannot be Greater Than Current Date. Please Select Valid Date Of Birth in \'dd-mm-yyyy\'');
	 		$("#patDOBId_Dup").focus();
	 		return false;
		}
	 	
		// Age Limit
		if(document.forms[0].patPrimaryCatCode.value==PRIMARY_CATEGORY_SENIOR_CITIZEN && $('[name="seniorCitizenAgeLimit"]')[0].value !="")
		{
			var seniorAgeBoundRange = $('[name="seniorCitizenAgeLimit"]')[0].value;
			d1 = $.datepicker.parseDate(format, value);
			ctdt = new Date();
			var dmy = seniorAgeBoundRange;
			var dmyFlag='y';
			var month = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
			if(dmyFlag=="y")
				 ctdt.setFullYear(ctdt.getFullYear()-dmy);
			else if(dmyFlag=="m")
				 ctdt.setMonth(ctdt.getMonth()-dmy);
			else if(dmyFlag=="d")
				 ctdt.setDate(ctdt.getDate()-dmy);
			 
			var msg="Date Should be less than "+ctdt.getDate()+"-"+(month[ctdt.getMonth()])+"-"+ctdt.getFullYear()+".";
			if(!(d1 <= ctdt))
			{
		 		alert(msg);
		 		$("#patDOBId_Dup").focus();
		 		return false;
			}
		}
		else
		{
			d1 = $.datepicker.parseDate(format, value);
			ctdt = new Date();
			ctdt.setHours(0,0,0,0);
			d1.setHours(0,0,0,0);
			var dmy = 125;
			var dmyFlag='y';
			var month = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
			
			if(dmyFlag=="y")
				ctdt.setFullYear(ctdt.getFullYear()-dmy);
			else if(dmyFlag=="m")
				ctdt.setMonth(ctdt.getMonth()-dmy);
			else if(dmyFlag=="d")
				ctdt.setDate(ctdt.getDate()-dmy);
			 
			var msg="Date Should not be less than "+ctdt.getDate()+"-"+(month[ctdt.getMonth()])+"-"+ctdt.getFullYear()+".";
			if(!(d1 >= ctdt))
			{
		 		alert(msg);
		 		$("#patDOBId_Dup").focus();
		 		return false;
			}
		}
		//setMotherValidRule();

	} */
	return true;
}
function PaymentModeRefId(obj){
	var paymentModeCode=obj.value.split("#")[0];
		
	if(paymentModeCode!="" && paymentModeCode!=1 && paymentModeCode!=13 && paymentModeCode!=10){
		$('#divFeeVal3').show();
		$('[name="paymentModeRefId"]').validatebox({
			required : true,
			validType : ['numericwithoutspace','DisableAllZero','consecutiveSpecialCharOnly']
		});
	}else{
		$('[name="paymentModeRefId"]').val("");
		$('[name="paymentModeRefId"]').validatebox({
			required : false,
			validType : null
		});
		$('#divFeeVal3').hide();
	}
}

</script>
<script>
var oldVal='';
$(document).ready(function(){
	$("select[name='paymentModeCode']").on('focus', function(){
		oldVal=$(this).val();
		
	});
});


function paymentModeCodeOnChangeValidate(e){ 
	var val1=$(e).val();
	var Fval= val1.split('#')[0];
 if(val1.split('#')[1]=='1')
	{ 	if(val1.split('#')[2]==$('#patPrimaryCate').val())
			 {
		PaymentModeRefId(e);			
		return true;
			 }
				  
		   else
			   {
			   alert("Selected Payment Mode is not applicable for this Patient Billing Category!");
			   $(e).find('option').removeAttr('selected');
			   $(e).find('option[value='+oldVal+']').attr('selected','selected'); 
			  		  
			   PaymentModeRefId(e);
			   return false;
			   
			   }
 }
 else
	 {
	 
						
	 PaymentModeRefId(e);
	 return true;
	
	 }
	 
}



</script>

<%@ page import="java.util.*,registration.*,hisglobal.presentation.Status"%>
<div id="noprint">
<%String varStatus = "";
	String renewalStatus = "";
%>
<his:statusNew>
	<%varStatus = "New";%>

</his:statusNew>

<%
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
	
<his:TitleTag name="Patient Visit">



	<b><font size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> </font></b>

</his:TitleTag>
<his:statusNew>
<%-- 		 <his:InputCrNoTag name="SplPatientVisitFB"></his:InputCrNoTag> --%>
			
</his:statusNew>
<bean:define id="crNo" name="SplPatientVisitFB" property="patCrNo"
	type="java.lang.String" />


<his:statusList>

<logic:notEmpty name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT%>">
	<logic:notEmpty name="<%=RegistrationConfig.ARR_EPISODE_OLD_PAT_REFER_VO %>">
		<his:SubTitleTag name="Old Referred Patient List">
		
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td width="4%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;
						</font>
					</td>
					<td width="16%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="crNo" /> 
								</font>
							</b>
						</div>
					</td>
					<td width="16%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="name" /> 
								</font>
							</b>
						</div>
					</td>
					<td width="16%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									 <bean:message key="referFromDept" /> 
								</font>
							</b>
						</div>
					</td>
					<td width="16%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="referFromDeptUnit" />
								</font>
							</b>
						</div>
					</td>
					<td width="16%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">	
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="toDept" /> 
								</font>
							</b>
						</div>
					</td>
					<td width="16%" align="center" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<b>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="referDate" /> 
								</font>
							</b>
						</div>
					</td>
				</tr>
				
				<logic:iterate id="ARR_EPISODE_OLD_PAT_REFER_VO" name="<%=RegistrationConfig.ARR_EPISODE_OLD_PAT_REFER_VO %>" type="vo.registration.EpisodeRefDtlVO" indexId="index">
					<tr>
						<td width="4%" class="tdfont">
							<div align="center">
								<font size="2">
									<html:radio name="SplPatientVisitFB" property="selectedRefCrNo" value='<%=index.toString()+"#O$"+ARR_EPISODE_OLD_PAT_REFER_VO.getPatCrNo() %>' onclick="showDetail(this)"></html:radio>
									<html:hidden name="SplPatientVisitFB" property="indexID"/>
								</font>
							</div>	
						</td>
						
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2"> 
									<bean:write name="ARR_EPISODE_OLD_PAT_REFER_VO" property="patCrNo" /> 
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2"> 
									<bean:write name="ARR_EPISODE_OLD_PAT_REFER_VO" property="patName" /> 
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2"> 
									<bean:write name="ARR_EPISODE_OLD_PAT_REFER_VO" property="fromDepartment" /> 
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2"> 
									<bean:write name="ARR_EPISODE_OLD_PAT_REFER_VO" property="fromDepartmentUnit" />
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2"> 
									<bean:write name="ARR_EPISODE_OLD_PAT_REFER_VO" property="toDepartment" /> 
								</font>
							</div>
						</td>
						<td width="16%" class="tdfont">
							<div align="center">
								<font size="2"> 
									<bean:write name="ARR_EPISODE_OLD_PAT_REFER_VO" property="reffDateTime" /> 
								</font>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>	
		</his:ContentTag>
	</logic:notEmpty>


	<logic:notEmpty
		name="<%=RegistrationConfig.ARR_EPISODE_REFER_PAT_VO %>">
		<his:SubTitleTag name="Referred Patient List">

		</his:SubTitleTag>


		<his:ContentTag>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr>
					<td width="4%" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;</font></td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="crNo" /> </font></b></div>
					</td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="name" /> </font></b></div>
					</td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referFromDept" /> </font></b></div>
					</td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referFromDeptUnit" /> </font></b></div>
					</td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referToDept" /> </font></b></div>
					</td>
					<td width="16%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referDate" /> </font></b></div>
					</td>
					
				</tr>
				<logic:iterate id="ARR_EPISODE_REFER_PAT_VO" indexId="index" name="<%=RegistrationConfig.ARR_EPISODE_REFER_PAT_VO %>" 
					type="vo.registration.EpisodeRefDtlVO" >
					<tr>
					
						<td width="4%" class="tdfont">
						<div align="center"><font size="2"> 
						<logic:equal name="ARR_EPISODE_REFER_PAT_VO" property="deptUnitIsClosed" value="<%=RegistrationConfig.DEPT_UNIT_IS_CLOSED_FALSE %>">
							<logic:equal name="ARR_EPISODE_REFER_PAT_VO" property="deptUnitIsOnDuty" value="<%=RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_TRUE%>">
							<html:radio
								name="SplPatientVisitFB" property="selectedReffereCrNo"
								value='<%=index.toString()+"#N$"+ARR_EPISODE_REFER_PAT_VO.getPatCrNo()%>'
								onclick="showDetail(this)" tabindex="1">
							</html:radio> 
							</logic:equal>
							<logic:equal name="ARR_EPISODE_REFER_PAT_VO" property="deptUnitIsOnDuty" value="<%=RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_FALSE%>">
					 			<img src="/hisglobal/images/buttons/stop.png" title="Unit not on duty">
					 		</logic:equal>
					 	</logic:equal>
					 	<logic:equal name="ARR_EPISODE_REFER_PAT_VO" property="deptUnitIsClosed" value="<%=RegistrationConfig.DEPT_UNIT_IS_CLOSED_TRUE %>">
					 	</logic:equal>
						</font></div>
						</td> 
						<td width="16%" class="tdfont">
						<div align="center"><font size="2"> <bean:write
							name="ARR_EPISODE_REFER_PAT_VO" property="patCrNo" /> </font></div>
						</td>
						<td width="16%" class="tdfont">
						<div align="center"><font size="2"> <bean:write
							name="ARR_EPISODE_REFER_PAT_VO" property="patName" /> </font></div>
						</td>
						<td width="16%" class="tdfont">
						<div align="center"><font size="2"> <bean:write
							name="ARR_EPISODE_REFER_PAT_VO" property="fromDepartment" /> </font>
						</div>
						</td>
						<td width="16%" class="tdfont">
						<div align="center"><font size="2"> <bean:write
							name="ARR_EPISODE_REFER_PAT_VO" property="fromDepartmentUnit" />
						</font></div>
						</td>
						<td width="16%" class="tdfont">
						<div align="center"><font size="2"> <bean:write
							name="ARR_EPISODE_REFER_PAT_VO" property="toDepartment" /> </font></div>
						</td>
						<td width="16%" class="tdfont">
						<div align="center"><font size="2"> <bean:write
							name="ARR_EPISODE_REFER_PAT_VO" property="reffDateTime" /> </font>
						</div>
						</td>
						

					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>
	</logic:notEmpty>
	
	<%-- <logic:present
		name="<%=RegistrationConfig.ARR_APPOINTMENT_NEW_PAT_REGD_VO %>"> --%>
<logic:notEmpty name="<%=RegistrationConfig.ARR_APPOINTMENT_NEW_PAT_REGD_VO%>">
		<his:ContentTag>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				
				<!--By Mukund on 05.10.2016 for getting patient list department-unit wise  -->
				<tr>
					 <div class="div-table-col label" style="width:15%">
						<bean:message key="department"/>/<bean:message key="unit"/>
					</div>
					<%-- <logic:notEmpty name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT%>"> --%>
					 <div class="div-table-col width control" style="width: 40%">
						<html:select name="SplPatientVisitFB" property="deptToSearchFrom" tabindex="1" styleClass="regCbo" style="width:200%;" onchange="setPatientList(this);">
							<html:option value="0">All Units</html:option>
							<html:options collection="deptToSearchFrom" property="value" labelProperty="label" />
						</html:select>
					</div>
				<%-- 	</logic:notEmpty> --%>
								
					
					 
					<div class="div-table-col width control" >
						<html:select name="SplPatientVisitFB" property='searchId' style="width:139px;" styleClass="regCbo">
							<html:option value='1'>Appointment No</html:option>
							<html:option value='2'>Name</html:option>
							<html:option value='3'>Contact No</html:option>	
							<html:option value='4'>CRNo</html:option>	
						</html:select>
					</div>
					<div class="div-table-col" >
						<html:text name="SplPatientVisitFB" styleClass="regCbo" property="searchValue" maxlength="16" size="20" tabindex="1" ></html:text>
					</div>
					 
					<div class="div-table-col" ><a href="#" class="button" id="searchId"><span class="save" onclick="searchValueValidation();">Search</span></a></div>
					<div class="div-table-col" ><a tabindex="1" href="#" class="button" id="clearId" onclick="resetFields();"><span class="clear">Clear</span></a></div>						
						 
				</tr>
				<!--End:Mukund  -->
				
				<tr>
					<td width="2%" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;</font></td>
					<td width="15%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="crNo" /> </font></b></div>
					</td>
					<td width="15%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="Apt_appointment" />&nbsp;<bean:message key="number" /> </font></b></div>
					</td>
					<td width="20%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="name" /> </font></b></div>
					</td>
					<td width="8%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="age" /> </font></b></div>
					</td>
					<td width="10%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="contactNo" /> </font></b></div>
					</td>
					
					<td width="5%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="Apt_slot" /> </font></b></div>
					</td>
					<td width="25%" align="center" class="tdfonthead"
						style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="department" />/<bean:message
						key="unit" /></font></b></div>
					</td>
					
				</tr>
				<logic:iterate id="ARR_APPT_REGD_PAT_VO" indexId="index"
					name="<%=RegistrationConfig.ARR_APPOINTMENT_NEW_PAT_REGD_VO %>" 
					type="vo.registration.PatientVO"  >
					<tr name="allPatientList" id="<bean:write name="ARR_APPT_REGD_PAT_VO" property="departmentCode"/>@<bean:write name="ARR_APPT_REGD_PAT_VO" property="patAptNo"/>@<bean:write name="ARR_APPT_REGD_PAT_VO" property="patFirstName"/>&<bean:write name="ARR_APPT_REGD_PAT_VO" property="patMiddleName"/>&<bean:write name="ARR_APPT_REGD_PAT_VO" property="patLastName"/>@<bean:write name="ARR_APPT_REGD_PAT_VO" property="patAddContactNo"/>@<bean:write name="ARR_APPT_REGD_PAT_VO" property="patCrNo" />">
						<td width="2%" >
						<div align="center"><font size="2"> 
							<html:radio
								name="SplPatientVisitFB" property="selectedReffereCrNo"
								value='<%=index.toString()+"#A$"+ARR_APPT_REGD_PAT_VO.getPatCrNo()+"@"+ARR_APPT_REGD_PAT_VO.getDepartmentCode()+"!"+ARR_APPT_REGD_PAT_VO.getUnit()+"%"+ARR_APPT_REGD_PAT_VO.getPatAptNo()%>'
								onclick="showAppointmentDetail(this)" tabindex="1">
							</html:radio>					
					 	</font></div>
						</td> 
						<td width="15%" >
						<div align="center"><font size="2"> <bean:write
							name="ARR_APPT_REGD_PAT_VO" property="patCrNo" /> </font></div>
						</td>
						<td width="15%" >
						<div align="center"><font size="2"> <bean:write
							name="ARR_APPT_REGD_PAT_VO" property="patAptNo" /> </font></div>
						</td>
						<td width="20%" >
						<div align="center"><font size="2"> <bean:write name="ARR_APPT_REGD_PAT_VO" property="patFirstName"/> <bean:write name="ARR_APPT_REGD_PAT_VO" property="patMiddleName"/> <bean:write name="ARR_APPT_REGD_PAT_VO" property="patLastName"/> </font></div>
						</td>
						<td width="8%" >
						<div align="center"><font size="2"> <bean:write
							name="ARR_APPT_REGD_PAT_VO" property="patAge" /> </font>
						</div>
						</td>
						<td width="10%" >
						<div align="center"><font size="2"> <bean:write
							name="ARR_APPT_REGD_PAT_VO" property="patAddContactNo" />
						</font></div>
						</td>
						
						<td width="5%" >
						<div align="center"><font size="2"> <bean:write
							name="ARR_APPT_REGD_PAT_VO" property="patAptSlot" /> </font>
						</div>
						</td>
						<td width="25%" >
						<div align="center"><font size="2"> <bean:write
							name="ARR_APPT_REGD_PAT_VO" property="unit" /></font>
						</div>
						</td>

					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>
	<%-- </logic:present> --%>
	</logic:notEmpty>
	
	</logic:notEmpty>
	
	<table width="100%" style=" background-color:#086fa6;">
	<!--Commented By Mukund on 05.10.2016  -->
		 <%-- <tr>
		 <td width="50%"></td>
		 <td width="25%">
		 <div align="right"><html:select name="SplPatientVisitFB" property='searchId' style="width:139px;">
			<html:option value='1'>Appointment No</html:option>
			<html:option value='2'>Name</html:option>
			<html:option value='3'>Contact No</html:option>	
			<html:option value='4'>CRNo</html:option>	
			</html:select></div></td>
		 <td width="15%">
		 <div align="left"><html:text name="SplPatientVisitFB" 
						styleClass="textbox" property="searchValue" maxlength="16" size="20"
						tabindex="1" ></html:text></div></td>
		 
		 <!--  <td width="5%"><div align="left"><img src="/HIS/hisglobal/images/buttons/btn-search.png" style="cursor: pointer;" onkeypress="if(window.event.keyCode==13) submitForm('SEARCH');" onclick="submitForm('SEARCH');"></div></td>  -->
		 <td width="5%"><div align="left"><a  href="#" class="button" id="searchId"><span class="save" onclick="submitForm('SEARCH');">Search</span></a></div></td>
		 <!--  <td width="5%"><div align="left"><img src="/HIS/hisglobal/images/buttons/btn-clr.png" style="cursor: pointer;" onkeypress="if(window.event.keyCode==13) submitForm('NEW');" onclick="submitForm('NEW');"></div></td> -->
		 <td width="5%"><div align="left"> <a  tabindex="1" href="#" class="button" id="clearId" onclick="submitForm('NEW');"><span class="clear">Clear</span></a></div></td>						
		 
		 </tr> --%>
		 </table>
	
		
	
</his:statusList>
<%if (!crNo.trim().equals("")) {%>
<his:statusTransactionInProcess>
<div class="div-table-col title width100 " style="text-align: right;display: none">
	<a id="cashCollection" style="cursor: pointer;color: white;text-align: right;" onclick="openDependentPopup('/HISRegistration/registration/newSpecialClinickVisitwithAptmnt.cnt?hmode=CASHCOLLECTIONPOPUP',event,250,600);" 
	title="Cash Collection Detail"><font color="white" style=""><u>Total Cash</u></font></a>
</div>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />

<table width="100%">
<tr>

<logic:equal name="SplPatientVisitFB" property="modeCase"  value="1">
<td class="tdfonthead" nowrap="nowrap" width="25%">
				<div align="right">
				<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="new" />&nbsp;<bean:message key="department" />&nbsp;<bean:message key="global.visit" /></font></b></div></td>
<td class="tdfont" width="25%">

				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<logic:equal value="On" name="SplPatientVisitFB" property="newDepartmentVisit">
					<input type="checkbox" name="newDepartmentVisit" onclick="checkuncheckNewDepartment(this)" checked="checked"/>
					</logic:equal>
					<logic:notEqual value="On" name="SplPatientVisitFB" property="newDepartmentVisit">
					<input type="checkbox" name="newDepartmentVisit" onclick="checkuncheckNewDepartment(this);"/>
					</logic:notEqual>
					
				
				</font></td>
<td class="tdfonthead" nowrap="nowrap" width="25%">
				<div align="right">
				<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="old" />&nbsp;<bean:message key="department" />&nbsp;<bean:message key="global.visit" /></font></b></div></td><td class="tdfont" width="25%"><b>

				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><input type="checkbox" name="oldDepartmentVisit" disabled="disabled"/></font></b></td>
</logic:equal>
<logic:equal name="SplPatientVisitFB" property="modeCase"  value="0">
<td class="tdfonthead" nowrap="nowrap" width="25%">
				<div align="right">
				<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="new" />&nbsp;<bean:message key="department" />&nbsp;<bean:message key="global.visit" /></font></b></div></td>
<td class="tdfont" width="25%"><b>

				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><input type="checkbox" name="newDepartmentVisit" disabled="disabled"/></font></b></td>
<td class="tdfonthead" nowrap="nowrap" width="25%">
				<div align="right">
				<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="old" />&nbsp;<bean:message key="department" />&nbsp;<bean:message key="global.visit" /></font></b></div></td>
<td class="tdfont" width="25%"><b>

				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
					<logic:equal value="On" name="SplPatientVisitFB" property="oldDepartmentVisit">
					<input type="checkbox" name="oldDepartmentVisit" onclick="checkuncheckOldDepartment(this)" checked="checked"/>
					</logic:equal>
						<logic:notEqual value="On" name="SplPatientVisitFB" property="oldDepartmentVisit">
					<input type="checkbox" name="oldDepartmentVisit" onclick="checkuncheckOldDepartment(this)"/>
					</logic:notEqual>
				
				</font></b></td>


</logic:equal>
<logic:equal name="SplPatientVisitFB" property="modeCase"  value="2">
<td class="tdfonthead" nowrap="nowrap" width="25%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="new" />&nbsp;<bean:message key="department" />&nbsp;<bean:message key="global.visit" /></font></div></td>
<td class="tdfont" width="25%"><b>

				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<logic:equal value="On" name="SplPatientVisitFB" property="newDepartmentVisit">
					<input type="checkbox" name="newDepartmentVisit" onclick="checkuncheckNewDepartment(this)" checked="checked"/>
					</logic:equal>
						<logic:notEqual value="On" name="SplPatientVisitFB" property="newDepartmentVisit">
					<input type="checkbox" name="newDepartmentVisit" onclick="checkuncheckNewDepartment(this)"/>
					</logic:notEqual>
					
					</font></b></td>
<td class="tdfonthead" nowrap="nowrap" width="25%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="old" />&nbsp;<bean:message key="department" />&nbsp;<bean:message key="global.visit" /></font></div></td>
<td class="tdfont" width="25%"><b>				
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<logic:equal value="On" name="SplPatientVisitFB" property="oldDepartmentVisit">
				<input type="checkbox" name="oldDepartmentVisit" onclick="checkuncheckOldDepartment(this)" checked="checked"/>
				</logic:equal>
					<logic:notEqual value="On" name="SplPatientVisitFB" property="oldDepartmentVisit">
				<input type="checkbox" name="oldDepartmentVisit" onclick="checkuncheckOldDepartment(this)"/>
				</logic:notEqual>			
				</font></b></td>

</logic:equal>
</tr>
</table>
</his:statusTransactionInProcess>
<his:statusRecordFound>
	<%renewalStatus = "renew";%>
	<his:SubTitleTag name="Renewal of Registration">
		<his:name>
			<bean:message key="renewalOfregistration" />
		</his:name>
	</his:SubTitleTag>

	<his:ContentTag>

		<table width="100%" colspacing="1" colpadding="0">
			<tr>
				<td width="20%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <b><font
					color="#FF0000">*</font> <bean:message key="renewRegistration" /></b>
				</font></div>
				</td>
				<td width="20%" class="tdfont"><input type="checkbox" tabindex="1"
					name="renewal"
					onclick="if (renewalConfirmation()) submitForm('RENEWAL')" /></td>
				<td width="20%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <b><font
					color="#FF0000">*</font> <bean:message key="amount" /></b> </font>
				</div>
				</td>
				<td width="25%" class="tdfont">
				<div align="left"><html:text name="SplPatientVisitFB" property="amount"
					maxlength="3" size="4" readonly="true" /></div>
				</td>
			</tr>
		</table>

	</his:ContentTag>
</his:statusRecordFound>


<his:statusTransactionInProcess>
	<input type='hidden' name='crNoToRetrieve' />
	<%varStatus = "InProcess";
	String isShow="1";
	String refletterDate =  WebUTIL.getCustomisedSysDate(new Date(), "dd-MMM-yyyy");
	%>
	
	<logic:equal value="On" name="SplPatientVisitFB" property="oldDepartmentVisit">
	<logic:notEqual name="SplPatientVisitFB" property="splRenewalRequired"
		value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE %>">	
	<%
	 	isShow="2";
	%>
	</logic:notEqual>
	</logic:equal>
	
	<logic:notEqual value="On" name="SplPatientVisitFB" property="newDepartmentVisit">
		<logic:notEqual value="On" name="SplPatientVisitFB" property="oldDepartmentVisit">
	
	<%
	 	isShow="2";
	%>
		</logic:notEqual>
	</logic:notEqual>
	
	
	<!-- Credit Beneficiary Details comes here -->
	
	<logic:equal name="SplPatientVisitFB" property="patPrimaryCatGrpCode"
		value="<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY %>">		
	<%
	 	if(isShow.equalsIgnoreCase("1")){
	%>
		<his:SubTitleTag name="Beneficiary Details">
		</his:SubTitleTag>
		<his:ContentTag>
		<div id="divCatGroupBeneficiaryId" style="diplay:none">		
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
				bgcolor="#EBEBEB">
				
			<tr>

					<td width="16%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message
						key="mandatory1"/>&nbsp;<bean:message
						key="reference"/>&nbsp;<bean:message
						key="letter"/>&nbsp;<bean:message
						key="number"/></font></div>
					</td>
					<td class="tdfont" width="17%">
					<html:text name="SplPatientVisitFB"
						tabindex="1" property="creditLetterRefNo" maxlength="50" 
						styleClass="textbox" onkeypress="return validateAlphaNumericOnly(event,this)" />
					</td> 
				  <!--Added by Mukund on 27.07.2016  -->
					<td width="16%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message
						key="mandatory1"/>&nbsp;<bean:message
						key="letter"/>&nbsp;<bean:message
						key="type"/></font></div>
					</td>
					<td width="17%" class="tdfont">
					<html:select name="SplPatientVisitFB" property='letterType' 
					styleClass="regcbo" tabindex="1">
					<html:option value='-1'>Select Value</html:option>
					<html:options collection="<%=RegistrationConfig.ESSENTIALBO_LETTER_TYPE_OPTION_LIST%>"
						property="value" labelProperty="label" />
					</html:select>		
					</td>
				<!--End:Mukund  -->  
					<td width="17%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message
						key="mandatory1"/>&nbsp;<bean:message
						key="reference"/>&nbsp;<bean:message
						key="letter"/>&nbsp;<bean:message
						key="date"/></font></div>
					</td>

					<td width="16%" class="tdfont">
						<%--<div id="divDob"><bean:define
						name="SplPatientVisitFB" property="creditLetterDate" id="dob"
						type="java.lang.String" /> 
						<div id="divPatDOB"></div>
						<script type="text/javascript">DateValidator.setup("divPatDOB","creditLetterDate","<%=dob%>","dd-MM-yyyy","textbox");</script>
					</div> --%>
						<input type="text" name="creditLetterDate" id="creditLetterDateId"  tabindex="1"> 
					 	<%-- <his:date name='creditLetterDate' id="creditLetterDateId" dateFormate="%d-%b-%Y" value="<%=refletterDate%>" /> --%>
						</td>
				</tr>	
				
			<tr>

					<td width="16%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="company" />&nbsp; </font></div>
					</td>
					<%-- <logic:notEmpty name="SplPatientVisitFB" property="clientName">
					<td class="tdfont" width="17%">
					<!--<div id="labelDepartment1" align="left"></div>-->	
					<html:text name="SplPatientVisitFB"
						tabindex="1" property="clientName" maxlength="50"
						styleClass="textbox" onkeypress="return validateAlphabetsOnly(event,this)"/>				
					</td>
					<html:hidden name="SplPatientVisitFB" property="clientCode" />
					</logic:notEmpty> --%>
					<%-- <logic:empty name="SplPatientVisitFB" property="clientName"> --%>
					<td class="tdfont" width="17%">
					<!--<div id="labelDepartment1" align="left"></div>-->	
					<html:select name="SplPatientVisitFB"
						property="clientCode"  styleClass="regcbo" tabindex="1">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_CLIENT_OPTION_LIST%>"
							property="value" labelProperty="label" />
					</html:select>				
					</td>
					<html:hidden name="SplPatientVisitFB" property="clientName" />					
					<%-- </logic:empty> --%>
					
				<!-- Modified by Mukund on 27.07.2016 -->
					
					<td width="16%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message
						key="staff"/>&nbsp;<bean:message
						key="number"/></font></div>
					</td>
					<td class="tdfont" width="17%">
					<html:text name="SplPatientVisitFB"
						tabindex="1" property="staffCardNo" maxlength="50"
						styleClass="textbox" onkeypress="return validateAlphaNumericOnly(event,this)"/>
					</td>
					
				<!--End: Mukund  -->
					<td width="17%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="staff"/>&nbsp;<bean:message
						key="name"/> </font></div>
					</td>

					<td width="16%" class="tdfont"><html:text name="SplPatientVisitFB"
						tabindex="1" property="staffCardName" maxlength="25"
						styleClass="textbox" onkeypress="return validateAlphabetsOnly(event,this)"/></td>
				</tr>
				
				<tr>

					<td width="17%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message
						key="relationship"/></font></div>
					</td>

					<td width="16%" class="tdfont"><html:select name="SplPatientVisitFB"
						property="relationWithStaff"  styleClass="regcbo" tabindex="1">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL%>"
							property="value" labelProperty="label" />
					</html:select></td>
					
					<!-- By Mukund on 27.07.2016 -->
					<td class="LABEL" width="14%"><bean:message key="validfor"/></td>
					
					<td width="17%" height="25" class="tdfont">
						<%-- <his:date name='cardvalidityDate' id="cardvalidityDateId" dateFormate="%d-%b-%Y" value="_todayPlus30Days" />  --%>
					<input type="text" name="cardvalidityDate" id="cardvalidityDateId" readonly="readonly" >
					</td>
					
					<td width="16%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif">Credit Limit </font></div>
					</td>
					<td class="tdfont" width="17%">
					<html:text name="SplPatientVisitFB"
						tabindex="1" property="creditLimit" maxlength="11"
						styleClass="textbox" onkeypress="return validateNumeric(event)"/>	
					</td>
					<!--End: Mukund  -->
					
					
				</tr>	
				
		</table>
		</div>		
		</his:ContentTag>
		
		<%
			}
		%>
		
	</logic:equal>

	<logic:equal name="SplPatientVisitFB" property="patPrimaryCatGrpCode"
		value="<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE %>">

		<%
	 		if(isShow.equalsIgnoreCase("1")){
		%>
		<div id="divCatGroupBeneficiaryWORefId" style="diplay:none">		
		<his:SubTitleTag name="Beneficiary Details">
		</his:SubTitleTag>
		<his:ContentTag>
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
				bgcolor="#EBEBEB">
				
			<tr>

					<td width="16%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message
						key="district"/></font></div>
					</td>
					<td class="tdfont" width="17%">
					<html:select name="SplPatientVisitFB"
						property="agsDistrictCode"  styleClass="regcbo" tabindex="1">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>"
							property="value" labelProperty="label" />
					</html:select>
					</td>
			
					<td width="17%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message
						key="mandatory1"/>&nbsp;<bean:message
						key="arogyasri"/>&nbsp;<bean:message
						key="number"/></font></div>
					</td>

					<td width="16%" class="tdfont"><html:text name="SplPatientVisitFB"
						tabindex="1" property="agsNo" maxlength="15"
						styleClass="textbox" onkeypress="return validateAlphaNumericOnly(event,this)"/>						
					</td>
				</tr>	
				
			<tr>

					<td width="16%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="arogyamitra" />&nbsp;<bean:message
						key="global.counter" />&nbsp;<bean:message
						key="number" /></font></div>
					</td>
					<td class="tdfont" width="17%">
					<html:text name="SplPatientVisitFB"
						tabindex="1" property="agsCounterNo" maxlength="3"
						styleClass="textbox" onkeypress="return validateNumeric(event)"/>	
					</td>
								
					<!--By Mukund on 27.07.2016  -->
					<td width="16%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> Credit Limit</font></div>
					</td>
					<td class="tdfont" width="17%">
					<html:text name="SplPatientVisitFB"
						tabindex="1" property="agsCreditLimit" maxlength="11"
						styleClass="textbox" onkeypress="return validateNumeric(event)"/>	
					</td>
					<!--End: Mukund  -->	
				<!-- 	<td width="17%" class="tdfonthead"></td>
					<td width="16%" class="tdfont"></td> -->
				</tr>				
				
				
		</table>
		
		</his:ContentTag>
		</div>
		<%
	 		}
		%>
	</logic:equal>
	
<logic:equal value="On" name="SplPatientVisitFB" property="newDepartmentVisit">
	<his:SubTitleTag name="New Department to Visit Stamp">

	</his:SubTitleTag>

	<%String labelDepartment2 = "";
						String controlDepartment2 = "";
						String labelDepartment12 = "";
						String controlDepartment12 = "";

						%>
	<logic:equal name="SplPatientVisitFB" property="isPatReferByList"
		value="<%=RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE %>">
		<%labelDepartment2 = "display:none";
							controlDepartment2 = "";
							labelDepartment12 = "display:none";
							controlDepartment12 = "display:block";

						%>
	</logic:equal>
	<logic:equal name="SplPatientVisitFB" property="isPatReferByList"
		value="<%=RegistrationConfig.IS_PAT_REFER_BY_LIST_FALSE %>">
		<%labelDepartment2 = "";
							controlDepartment2 = "display:none";
							labelDepartment12 = "";
							controlDepartment12 = "display:none";

						%>
	</logic:equal>
	<logic:equal name="SplPatientVisitFB" property="isPatReferByList"
		value="<%=RegistrationConfig.IS_PAT_APPOINTMENT_BY_LIST_TRUE%>">
		<%		labelDepartment2 = "display:none";
				controlDepartment2 = "";
				labelDepartment12 = "display:none";
				controlDepartment12 = "display:block";
		%>
	</logic:equal>
<%-- 	<bean:define id="fileId" name="<%=Config.FILE_NO_GENERATION_FLAG_NAME %>" /> --%>
	
<%-- 	<%System.out.println("client name================================= "+fileId); %> --%>

		<his:ContentTag>
			<div id="common" style="display:block">
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				bgcolor="#EBEBEB">
				<bean:define id="isRefer" name="SplPatientVisitFB"
					property="isPatReferByList"></bean:define>

				<tr>

					<td width="16%" class="tdfonthead">
					<div align="right"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <font
						color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
						key="department" />&nbsp; </font></b></div>
					</td>
					<td class="tdfont" width="17%">
					<div id="labelDepartment1" style="<%=labelDepartment12%>"
						align="left"><html:select name="SplPatientVisitFB"
						property="departmentUnitCode"  styleClass="regcbo" tabindex="1">
						<html:option value="-1">Select Value</html:option>
						<html:options
							collection="<%=RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT%>"
							property="value" labelProperty="label" />
					</html:select>
					</div>
					<div id="controlDepartment1" style="<%=controlDepartment12%>" align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<!-- sheel -->
							<bean:write name="SplPatientVisitFB" property="toDepartment" />
						</font>	
					</div>
					<html:hidden name="SplPatientVisitFB" property="toDepartmentCode"/>
					</td>
			
					<td width="17%" class="tdfonthead">
					<div align="right"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="amountCollected" /> </font></b></div>
					</td>

					<td width="16%" class="tdfont"><html:text name="SplPatientVisitFB"
						tabindex="1" property="patAmountCollected" maxlength="6"
						styleClass="textbox" readonly="true" /></td>
						<!-- Added by warish for cash collection combo -->
					<td width="17%" class="tdfonthead">	
				<div class="div-table-col control" id="divFeeVal2" style="width:42%;">
					<html:select name="SplPatientVisitFB" property='paymentModeCode' 
					styleClass="regcbo" tabindex="1" onchange=' return paymentModeCodeOnChangeValidate(this)'>
					
					<html:options collection="<%=RegistrationConfig.PAYMENT_MODE_OPTION_LIST%>"
						property="value" labelProperty="label" />
					</html:select>
				</div>
				</td>
				<td width="17%" class="tdfonthead">
				<div class="div-table-col control" id="divFeeVal3" style="width:12%;display:none;">
					<input tabindex="1" name="paymentModeRefId"  maxlength="20" type="text" pattern= "[0-9]"/>
				</div>
				</td >
				<!-- End style="display: none;" -->
				</tr>
			</table>
			</div>  
                           
		</his:ContentTag>
</logic:equal>		
<logic:equal value="On" name="SplPatientVisitFB" property="oldDepartmentVisit">
<his:SubTitleTag name="Visit Details">
	</his:SubTitleTag>
	<logic:notEmpty name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR %>">
	  <bean:define id="EPISODEVO" name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>" type="vo.registration.EpisodeVO[]" />
	  <his:ContentTag>
  			<% if(EPISODEVO.length!=0) {
  				 %>
  				<%varStatus="InProcess";%>
		
				
					<table  width="100%" border="0" cellspacing="1" cellpadding="0">
						
						<tr>
						<td width="5%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="status"/></b></font></td>
						<td width="13%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="department"/></b></font></td>
						<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="unit/room"/></b></font></td>
						<td width="10%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="lastVisitdate"/></b></font></td>
						<!--By Mukund on 29.07.2016  -->
					<%--<td width="25%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="con"/></b></font></td> --%>
					<logic:equal name="SplPatientVisitFB" property="patPrimaryCatGrpCode" value="<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY %>">		
						<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>Reference Letter No./Date</b></font></td>
					</logic:equal>
						<td width="15%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>Visit Reason</b></font></td>
						<td width="8%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="renewalfee"/></b></font></td>
						</tr>
						<logic:iterate id="episode" type="vo.registration.EpisodeVO" indexId="i" name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR %>">
						
						<%
						String renewalType=(String)episode.getRenewalType();
						//System.out.println("Renewal type is"+renewalType);
						String unitType=(String)episode.getIsGeneral()==null ? "" : (String)episode.getIsGeneral();						
						%>
						
						<%--<bean:define name="episode" id="renewalType" property="renewalType"/>
						<bean:define name="episode" id="unitType" property="isGeneral"/>  --%>

						<%
						if(renewalType.equals("")) renewalType="0";
						String hh=(String)(renewalType!=null?renewalType:"0");
						int x=Integer.parseInt(hh);
						String backgroundColor="" ;
						String tdFontColor="class='tdfont'";
						if(unitType.equals(RegistrationConfig.UNIT_TYPE_SPECIALITY))
						{
							backgroundColor="bgcolor='#FFC0CB'" ;
							tdFontColor="";
						}
						
						%>
							<tr <%=backgroundColor %>>
								<td <%=tdFontColor %> nowrap >
								<!--start Unit Already Visit Today -->
								<logic:equal name="episode"  property="visitedToday" value="<%=RegistrationConfig.DEPT_UNIT_VISITED_TODAY_TRUE %>">
									<div align="center">
									<img src="/HIS/hisglobal/images/avai/icn-lock.png" title="Unit Already Visited Today">
									</div>
								</logic:equal>
								<!-- end unit already visited today -->
								<!--start Unit Not Visited Today and Unit Is ON Duty -->		
								<logic:equal name="episode"  property="visitedToday" value="<%=RegistrationConfig.DEPT_UNIT_VISITED_TODAY_FALSE %>">
									<logic:equal name="episode" property="deptUnitIsOnDuty" value="<%=RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_TRUE%>">
									<!--By Mukund On 29.07.2016  for renewal type=3 at same chnges are also done at six more places till line 2515(line nos are tentative to change without any prior notice :P) -->
									<%-- <% if(x==2 || x==4 || x==5){%> --%>
									<%if(x==3 || x==4){%>
											<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
											<div align="center">
											<input type="checkbox" tabindex="1" name="departmentsToVisitStamp" value='<bean:write name="episode"  property="episodeCode"/>' onclick="checkForRefer(this)" />
											</div>
											</logic:equal>
											
											<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
											<div align="center">
											<input type="checkbox" tabindex="1" name="departmentsToVisitStamp" onclick="calculategranttotal(<%=i%>);" value='<bean:write name="episode" property="episodeCode"/>'/>
											</div>
											</logic:equal>
										<%} else {%>
											<div align="center">
											<input type="checkbox" tabindex="1" name="departmentsToVisitStamp" value='<bean:write name="episode" property="episodeCode"/>' onclick="checkForRefer(this)" />
											</div>
										<%} %>
									</logic:equal>									
								</logic:equal>	
								<!-- end unit not visited today and unit is on duty  -->
								
								<!-- start unit not visited today and unit not on duty -->
								<logic:equal name="episode"  property="visitedToday" value="<%=RegistrationConfig.DEPT_UNIT_VISITED_TODAY_FALSE %>">
									<logic:equal name="episode" property="deptUnitIsOnDuty" value="<%=RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_FALSE%>">
										<!--start Unit type special -->
										<logic:equal name="episode" property="isGeneral" value="<%=RegistrationConfig.UNIT_TYPE_SPECIALITY%>">
											<div align="center">
												<img src="/HIS/hisglobal/images/avai/stop.png" title="Unit not on duty">
											</div>
										</logic:equal>
										<!-- end Unit type special -->
										<!-- start unit type general -->
										<logic:equal name="episode" property="isGeneral" value="<%=RegistrationConfig.UNIT_TYPE_GENERAL%>">
											<!-- start  other general unit working -->
											<logic:notEqual name="episode" property="isOtherUnitWorking" value="<%=RegistrationConfig.NO_DEPT_UNIT_WORKING_TRUE%>">
													<html:hidden name="SplPatientVisitFB" property="hiddenEpisode" />
													<div align="center">
														<img src="/HIS/hisglobal/images/avai/forward3.gif" title="Visit stamp on request" style=cursor:pointer tabindex="1" onclick = "submitFormOnValidate(validateOnRequest('<bean:write name="episode" property="episodeCode" />'),'SAVE');" onkeypress="if (event.keyCode==13) submitFormOnValidate(validateOnRequest('<bean:write name="episode" property="episodeCode" />'),'SAVE');" >
														
														</div>
											</logic:notEqual>
											<!-- end other general unit working -->
										
										
										<!-- start other general unit not working -->
										<logic:equal name="episode" property="isOtherUnitWorking" value="<%=RegistrationConfig.NO_DEPT_UNIT_WORKING_TRUE%>">
													<html:hidden name="SplPatientVisitFB" property="hiddenEpisode" />
													<div align="center">
														<img src="/HIS/hisglobal/images/avai/stop.png" title="No Unit Working In The Department" >
													</div>
										</logic:equal>
										<!-- end other general Unit Working -->
									</logic:equal>
									<!-- end unit type general -->
									</logic:equal>
								</logic:equal>
								<!-- end unit not visited today and unit not on duty -->
								
										
								
								
								</td>
								 <html:hidden name="episode" property="renewalRequired"/>
								<td <%=tdFontColor %>>
									<div align="center">
									<% if(x==3 || x==4){%>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="department"/>
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="department"/>
										</font>
									</logic:equal>
									<%}
									else{%>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="department"/>
										</font>
									<%} %>
									</div>
								</td>
								
								
								
								<td <%=tdFontColor %>>
									<div align="center">
									<% if(x==3 || x==4){%>
									 <logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="departmentUnit"/>/
										<bean:write name="episode" property="room"/>
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="departmentUnit"/>/
										<bean:write name="episode" property="room"/>
										</font>
									</logic:equal>
									<%}else{ %>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="departmentUnit"/>/
										<bean:write name="episode" property="room"/>
										</font>
										<%} %>
									</div>
								</td>								
								
															
								<td <%=tdFontColor %> >
									<div align="center">
									<% if(x==3 || x==4){%>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="episodeDate"/>
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="episodeDate"/>
										</font>
									</logic:equal>
									<%} else { %>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="episodeDate"/>
										</font>
									<%} %>
									</div>
								</td>
						<!--By Mukund on 29.07.2016  -->
								<%-- <td <%=tdFontColor %> >
									<div align="center">
									<% if(x==2 || x==4 || x==5){%>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="empName"/>
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="empName"/>
										</font>
									</logic:equal>
									<%} else { %>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="empName"/>
										</font>
									<%} %>
									</div>
								</td> --%>
						<!--For Reference Ltter no and date  -->
					<logic:equal name="SplPatientVisitFB" property="patPrimaryCatGrpCode" value="<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY %>">		
								<td <%=tdFontColor %> >
									<div align="center">
									<%if(x==3 || x==4){%>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="creditLetterRefNo"/>/
										<bean:write name="episode" property="creditLetterDate"/>
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="creditLetterRefNo"/>/
										<bean:write name="episode" property="creditLetterDate"/>
										</font>
									</logic:equal>
									<%} else { %>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="creditLetterRefNo"/> /
										<bean:write name="episode" property="creditLetterDate"/>
										</font>
									<%} %>
									</div>
								</td>
					</logic:equal>
						<!-- For Visit Reason-->
								<td <%=tdFontColor %> >
									<div align="center">
									<% if(x==3 || x==4){%>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="patVisitReason"/>
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="patVisitReason"/>
										</font>
									</logic:equal>
									<%} else { %>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="episode" property="patVisitReason"/>
										</font>
									<%} %>
									</div>
								</td>
						<!--End:Mukund  -->
							<!--By Mukund: The following are the changes for showing of renewal amount-->
								<td <%=tdFontColor %> >
									<div align="center">									
									<% if(x==3 || x==4){%>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<input type="text" name="episode" property="renewalAmount" <%-- value='<bean:write name="episode" property="renewalAmount"/>' --%> value='<bean:write name="SplPatientVisitFB" property="patRenewalAmountDeptWise"/>' readonly="readonly">
										<input type ="hidden" name="renewalAmount" value='<bean:write name="SplPatientVisitFB" property="patRenewalAmountDeptWise"/>'/>									
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											0.00							
										</font>
										<input type ="hidden" name="renewalAmount" value='0.00'/>	
										<input type="hidden" name="episode" property="renewalAmount" value='0.00'/>
									</logic:equal>
									
									<% }else { %>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											0.00
										</font>
										<input type ="hidden" name="renewalAmount" value='0.00'/>	
										<input type="hidden" name="episode" property="renewalAmount" value='0.00'/>
									<%} %>
									</div>
								</td>
						
							</tr>
							
										
						</logic:iterate>
						<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
						<tr>
						<td class="tdfonthead" colspan="5"><div align="right" ><b><bean:message key="renewalfee"/>:</b></div></td>
						<logic:equal name="episode" property="renewalAmount" value="<%=RegistrationConfig.PAT_CAT_PAID_FEE%>">
						<td class="tdfont" colspan="1"><div align="center"><input type="text"  name="grandTotal" value="5.00" disabled="disabled"></div> </td>
						</logic:equal>
						<logic:equal name="episode"  property="renewalAmount" value="<%=RegistrationConfig.PAT_CAT_FREE_FEE%>">
						<td class="tdfont" colspan="1"><div align="center"><input type="text" name="grandTotal" value="0.00" disabled="disabled"></div> </td>
						</logic:equal>
						</tr>
						</logic:equal>
						<html:hidden name="SplPatientVisitFB" property="hcode" />						
					</table>
			
<%} %>
		</his:ContentTag>
	</logic:notEmpty>
</logic:equal>
<logic:notEqual name="SplPatientVisitFB" property="isPatReferByList" value="1" >	

<his:SubTitleTag name="Refer Details">
	<table width="100%" cellspacing="1" cellpadding="0">
	  <tr>
		<td width="95%" >
			<div align="right"><b><font color ="#ffffff"><bean:message key="isreferred" /></font></b></div>
		</td>
		<td width="5%">
			<div align="left">
			<html:checkbox name="SplPatientVisitFB" property="isReferred" onclick="checkIsReferred(this)"
						onkeypress="if(event.keyCode==13) checkIsReferred(this);" />
		</div>
		</td>
	</tr>
	</table>		
</his:SubTitleTag>
<div id="isRefferDiv" style="display: none;">
 <his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="40%">
					<table width="100%" cellspacing="1" cellpadding="0">
					<tr id="checkReferral" style="display:none"><td width="50%">
						<div ><font color="#FF0000"
						size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> <b><font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"><bean:message
						key="referInternal" /> </font></b> <html:radio name="SplPatientVisitFB"
						property="isRefferInOut" tabindex="1" value="I"
						onclick="showInternal(this)" /></div></td>
						<td width="50%">
						<div id="externalReferalRadio"><b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referExternal" /> </font></b> <html:radio name="SplPatientVisitFB"
						property="isRefferInOut" tabindex="1" value="E"
						onclick="showExternal(this)"/></div></td>
						</tr>
						</table>
					</td>
					<td width="40%">
					<div id="externalRefer" style="display:none"><font color="#FF0000"
						size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> <b><font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="associatedInst" /> </font></b> <html:radio name="SplPatientVisitFB"
						property="referringInstType" tabindex="1" value="G"
						onclick="showAssociated(this)" /> <b><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="other" /> </font></b> <html:radio name="SplPatientVisitFB"
						property="referringInstType" value="O" tabindex="1"
						onclick="showOthers(this)" /> <html:hidden name="SplPatientVisitFB"
						property="isAssociated" /></div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
</div>		
<%-- 	<bean:define id="clientFlag" name="<%=Config.CLIENT_NAME%>"></bean:define> --%>

		<div id="commonRefer" style="display:none">


			<table width="100%" cellspacing="1" cellpadding="1">
				<tr>
					<td width="25%" class="tdfonthead">
					<div align="right"><font color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referredBy" /></font></div>
					</td>
					<td width="25%" class="tdfont"><html:text name="SplPatientVisitFB"
						styleClass="textbox" maxlength="60" property="patRefDoctor"
						onblur="isAlpha(this,'Referred By Doctor')" tabindex="1"
						onkeypress="return validateAlphabetsSpaceDotAndBracket(event,this)" /></td>
					<td width="25%" class="tdfonthead">
					<div id="commonReferInternal" style="display:none">&nbsp; &nbsp;
					&nbsp; &nbsp;</div>

					<div id="commonReferExternal" style="display:none">
					<div align="right"><font color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="institute" /> <bean:message key="name" /></font></div>
					</div>
					</td>

					<td width="25%" class="tdfont">
					<div id="commonReferInternal_1" style="display:none">&nbsp; &nbsp;
					&nbsp; &nbsp;</div>

					<div id="commonReferExternal_associated" style="display:none"><html:select
						name="SplPatientVisitFB" tabindex="1"
						property="patRefGnctdHospitalCode" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL %>">
						<html:options
							collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>"
							property="value" labelProperty="label" />
										</logic:present>
					
					</html:select>
					</div>

					<div id="commonReferExternal_other" style="display:none"><html:text
						name="SplPatientVisitFB" tabindex="1" property="patRefHospitalName"
						onkeypress="return validateAlphabetsSpaceDotAndBracket(event,this)" maxlength="100"
						styleClass="textbox" size="20" /></div>
					</td>
					</tr>
					
					<tr>
					
					<td width="25%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referFromDept" /> </font></div>
					</td>
					<td width="25%" class="tdfont">
						<html:select name="SplPatientVisitFB" property="patRefGnctdHospitalDept" tabindex="1" styleClass="regCbo" onchange="checkReferDepartment(this)">
						<html:option value="">Select Value</html:option>
										
						
						<html:option value="0">Other</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL %>">
						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL%>" property="value" labelProperty="label" /> 
						</logic:present>
						</html:select> 
					</td>
					<td width="25%" class="tdfonthead" >
    			 	<div  align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="other" />
					<bean:message key="department" /> </font>
					</div>
					
					</td>
					<td width="25%" class="tdfont">
					<html:text name="SplPatientVisitFB"  property="patRefHospitalDeptOther" maxlength="20"  tabindex="1" styleClass="textbox" disabled="true" onkeypress="return validateAlphabetsOnly(event,this)"/>
					</td>
				
				</tr>
			</table>



			<div id="associated" style="display:none">
			<table width="100%" cellspacing="1" cellpadding="1">
				<tr>
					<td width="25%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referring" /> <bean:message key="crNo" /> </font></div>
					</td>
					<td width="25%"  class="tdfont"><html:text name="SplPatientVisitFB"
						tabindex="1" property="patRefGnctdHospitalCrno" maxlength="12"
						onkeydown="setPrevValue(this, event);"
						onkeyup="moveToRight(this, event);"
						onkeypress="return validateNumeric(event)" styleClass="textbox" />
					</td>
					<td width="25%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referring" /> <bean:message key="unit" /> </font></div>
					</td>
					<td width="25%" class="tdfont"><html:text name="SplPatientVisitFB"
						property="patRefGnctdHospitalDeptUnit" tabindex="1" maxlength="15"
						styleClass="textbox" onkeypress="return validateAlphaNumericOnly(event,this)"/>
					</td>
					
				</tr>
				
			</table>
			</div> 

		</div>


		<div id="internalRefer" style="display:none"><!--- ReferInternal -------  Details of all open episodes-->


		
			<logic:notEmpty
				name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>">
				<bean:define id="OPD_OPEN_EPISODES"
					name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>"
					type="vo.registration.EpisodeRefDtlVO[]" />


				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<%int i = 0;%>
					<tr>
						<td width="5%" class="addtoolbar"
							style="border-top:outset black 2px; border-bottom:inset black 2px;"><b><font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">Select</font></b></td>

						<td width="30%" class="addtoolbar"
							style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<div align="center"><b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="referFromDept" /> </font> </b></div></td>



						<td width="45%" class="addtoolbar"
							style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">
						<div align="center"><b> <font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="referFromDeptUnit" /> </font> </b></div></td>



					</tr>

					<logic:iterate id="ARR_OPD_OPEN_EPISODE_VO"
						name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>" indexId="index" type="vo.registration.EpisodeRefDtlVO">
						<%
						 String epCode=ARR_OPD_OPEN_EPISODE_VO.getEpisodeCode();
						 String frmDep=ARR_OPD_OPEN_EPISODE_VO.getFromDepartment();
						 String frmDepUnit=ARR_OPD_OPEN_EPISODE_VO.getFromDepartmentUnit();
						 String frmDepCode=ARR_OPD_OPEN_EPISODE_VO.getFromDepartmentCode();
						 String frmDepUnitCode=ARR_OPD_OPEN_EPISODE_VO.getFromDepartmentUnitCode();
						%>
						<%--<bean:define name="ARR_OPD_OPEN_EPISODE_VO" property="episodeCode"
							id="epCode" />
						<bean:define name="ARR_OPD_OPEN_EPISODE_VO"
							property="fromDepartment" id="frmDep" />
						<bean:define name="ARR_OPD_OPEN_EPISODE_VO"
							property="fromDepartmentUnit" id="frmDepUnit" />
						<bean:define name="ARR_OPD_OPEN_EPISODE_VO"
							property="fromDepartmentCode" id="frmDepCode" />
						<bean:define name="ARR_OPD_OPEN_EPISODE_VO"
							property="fromDepartmentUnitCode" id="frmDepUnitCode" />--%>
						<%String hh = (String) epCode;

										%>
						<%String strFrmDep = (String) frmDep;
						//System.out.println("frmDep"+frmDep);
						//System.out.println("frmDepUnit"+frmDepUnit);
						//System.out.println("frmDepCode"+frmDepCode);
						//System.out.println("frmDepUnitCode"+frmDepUnitCode);

										%>
						<%String strFrmDepUnit = (String) frmDepUnit;

										%>
						<%String strFrmDepCode = (String) frmDepCode;

										%>
						<%String strFrmDepUnitCode = (String) frmDepUnitCode;

										%>
						<tr>
							<td width="5%" class="tdfont">
							<div align="center"><input type="radio"
								name="refferringOPDEpisode"
								 value="<%=hh %>" /></div>
							</td>
							
							<html:hidden name="SplPatientVisitFB"
								property="episodeCode" value="<%=hh%>" />
							
							<td width="30%" class="tdfont">
							<div align="center"><bean:write name="ARR_OPD_OPEN_EPISODE_VO"
								property="fromDepartment" /> <html:hidden name="SplPatientVisitFB"
								property="fromDepartment" value="<%=strFrmDepCode%>" />
								<input type="hidden" 
								name="episodeDate" value="<%=ARR_OPD_OPEN_EPISODE_VO.getEntryDate()%>" />
								<input type="hidden" 
								name="visitNo" value="<%=ARR_OPD_OPEN_EPISODE_VO.getEpisodeVisitNo()%>" />
								</div>
							</td>


							<td width="45%" class="tdfont">
							<div align="center"><bean:write name="ARR_OPD_OPEN_EPISODE_VO"
								property="fromDepartmentUnit" /> <html:hidden
								name="SplPatientVisitFB" property="fromDepartment"
								value="<%=strFrmDepUnitCode%>" /></div>

							</td>

						</tr>
					</logic:iterate>

				</table>

			</logic:notEmpty>
		 <!--- End ReferInternal ---- Details of all open episodes-->
		</div>
	</logic:notEqual>
	<logic:notEqual name="SplPatientVisitFB" property="modeCase"  value="1">
	<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src="/HIS/hisglobal/images/avai/arrow_down.gif" onclick="showLegends();" onkeypress="if(event.keyCode==13) showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src="/HIS/hisglobal/images/avai/arrow_up.gif" onclick="showLegendsNone();" onkeypress="if(event.keyCode==13) showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
	</logic:notEqual>
	<div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" colspacing="1" colpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="10%">
					<input type="checkbox">
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Visit stamp allowed
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<img src="/HIS/hisglobal/images/avai/icn-lock.png">
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Unit already visited today
					</div>
					</font>
				</td>				
			</tr>
			
			<tr>
				<td width="10%">
					<img src="/HIS/hisglobal/images/avai/stop.png">
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					No unit working in the department
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<img src="/HIS/hisglobal/images/avai/forward3.gif">
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					On request, visit stamp to other unit of same department (only for general units)
					</div>
					</font>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Red
					</div>
					</font>
				</td>
				<td width="90%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<div align="left">
					Registration expired, renewal required for this department
					</div>
					</font>
				</td>				
			</tr>
	
		</table>
	</his:ContentTag>
	</div>


</his:statusTransactionInProcess>
<%} 

				%>


<!------button Toolbar Started---------------------------------- -->
		<his:ButtonToolBarTag>
		<table cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr style="display:none">
					<td width="5"><img
						src="/HIS/hisglobal/images/avai/rnd-trans-ffffff-bdr.gif"></td>
					<td
						style="background: transparent url(/HIS/hisglobal/images/avai/border-top.gif) repeat-x scroll center top; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td width="5"><img
						src="/HIS/hisglobal/images/avai/rnd-trans-ffffff-bdr-RT.gif"></td>
				</tr>
				<tr>
					<td
						style="background: transparent url(/HIS/hisglobal/images/avai/border-left.gif) repeat-y scroll left center; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td style="background-color: rgb(255, 255, 255);" width="100%">
					<%Status objStatus = (Status) request
							.getAttribute(Config.STATUS_OBJECT);
					if (renewalStatus.equals("renew")) {

					%>
				<div align="center">
				 <!--  <img class="button" src="/HIS/hisglobal/images/buttons/btn-sv.png" tabindex="1"  style=cursor:pointer onclick="if(renewalValidation())submitForm('RENEWAL');" onkeypress="if (event.keyCode==13) if(renewalValidation())submitForm('RENEWAL');">
				 <img class="button" src="/HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1" onclick ="submitPage('NEW');">
				 <img class="button" src="/HIS/hisglobal/images/buttons/btn-clr.png" style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">-->
				 <a  tabindex="1" href="#" class="button" id="submitId" onclick="if(renewalValidation())submitForm('RENEWAL');" onkeypress="if (event.keyCode==13) if(renewalValidation())submitForm('RENEWAL');"><span class="save">Save</span></a>
				<a  tabindex="1" href="#" class="button" id="clearId" onclick="if(event.keyCode==13) submitPage('NEW');" tabindex="1" onclick ="submitPage('NEW'));"><span class="clear">Clear</span></a>
				<a  tabindex="1" href="#" class="button" id="cancelId" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');"><span class="cancel">Cancel</span></a>
				</div>
				<%} else if (objStatus != null) {
									HashSet statuslist = objStatus.getStatusList();
									if (statuslist.contains(objStatus.TRANSINPROCESS)
											&& (!statuslist.contains(objStatus.ERROR_DA))) {%>
			
				<div align="center">
				
				
				<logic:equal  name="SplPatientVisitFB"  property="newDepartmentVisit" value="On">
					<logic:equal  name="SplPatientVisitFB"  property="oldDepartmentVisit" value="">
		<!--<img class="button" src="/HIS/hisglobal/images/buttons/btn-sv.png" tabindex="1"  style="cursor: pointer;"  onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidateDepartmentNewDeptVisit(document.getElementsByName('departmentsToVisitStamp')[0]),'CHECKMANDATORY');" onclick="submitFormOnValidate(ValidateDepartmentNewDeptVisit(document.getElementsByName('departmentsToVisitStamp')[0]),'CHECKMANDATORY');">-->
			<a  tabindex="1" href="#" class="button" id="submitId" onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidateDepartmentNewDeptVisit(document.getElementsByName('departmentsToVisitStamp')[0]),'CHECKMANDATORY');" onclick="submitFormOnValidate(ValidateDepartmentNewDeptVisit(document.getElementsByName('departmentsToVisitStamp')[0]),'CHECKMANDATORY');"><span class="save">Save</span></a>	
				</logic:equal>
				</logic:equal>
				
				<logic:equal  name="SplPatientVisitFB"  property="oldDepartmentVisit" value="On">
				<logic:equal  name="SplPatientVisitFB"  property="newDepartmentVisit" value="">
				<!--  <img class="button" src="/HIS/hisglobal/images/buttons/btn-sv.png" tabindex="1"  style="cursor: pointer;" onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidateDepartmentOldDeptVisit(),'CHECKMANDATORY');" onclick="submitFormOnValidate(ValidateDepartmentOldDeptVisit(),'CHECKMANDATORY');">-->
				<a  tabindex="1" href="#" class="button" id="submitId"  onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidateDepartmentOldDeptVisit(),'CHECKMANDATORY');" onclick="submitFormOnValidate(ValidateDepartmentOldDeptVisit(),'CHECKMANDATORY');"><span class="save">Save</span></a>
				
				</logic:equal>
				</logic:equal>
				<logic:equal  name="SplPatientVisitFB"  property="oldDepartmentVisit" value="On">
				<logic:equal  name="SplPatientVisitFB"  property="newDepartmentVisit" value="On">
				<!--  <img class="button" src="/HIS/hisglobal/images/buttons/btn-sv.png" tabindex="1"  style="cursor: pointer;"  onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidateDepartmentOldDeptVisit(),'CHECKMANDATORY');" onclick="submitFormOnValidate(ValidateDepartmentNewDeptVisit(document.getElementsByName('departmentsToVisitStamp')[0]),'CHECKMANDATORY');">-->
				<a  tabindex="1" href="#" class="button" id="submitId" onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidateDepartmentOldDeptVisit(),'CHECKMANDATORY');" onclick="submitFormOnValidate(ValidateDepartmentNewDeptVisit(document.getElementsByName('departmentsToVisitStamp')[0]),'CHECKMANDATORY')"><span class="save">Save</span></a>
				
				</logic:equal>
				</logic:equal>				
				
				 <!--  <img class="button" src="/HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1"  style="cursor: pointer;"  GETPATDTL tabindex="1" onclick ="submitPage('NEW');">
				 <img class="button" src="/HIS/hisglobal/images/buttons/btn-clr.png" style=cursor:pointer tabindex="1"  onclick ="submitForm('GETPATDTL')" onkeypress="if(event.keyCode==13) submitForm('GETPATDTL');">-->
				 
				 <a  tabindex="1" href="#" class="button" id="clearId" onkeypress="if(event.keyCode==13) submitPage('GETPATDTL');"onclick="submitForm('GETPATDTL');"><span class="clear">Clear</span></a>
				<a  tabindex="1" href="#" class="button" id="cancelId" onclick="submitForm('NEW');"><span class="cancel">Cancel</span></a>
				 
				 
				 <!--
				
				 <logic:equal  name="SplPatientVisitFB"  property="newDepartmentVisit" value="On">
				<img class="button" src='../hisglobal/images/btn-reprint.png' tabindex="1" style="cursor: pointer;" onclick="document.forms[0].hmode.value='PRINT';document.forms[0].submit();" onkeypress="if(event.keyCode==13) document.forms[0].hmode.value='PRINT';document.forms[0].submit();">
				</logic:equal>
				
				--></div>
			<%} else {
			
									%>
				<div align="center">
				
				<a  tabindex="1" href="#" class="button" id="cancelId" onclick="window.parent.closeTab('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel">Cancel</span></a>
				
				 <!-- <img class="button" src="/HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1"  style="cursor:pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');"> -->
				 <img class="button" src="/HIS/hisglobal/images/buttons/btn-clr.png" style="cursor:pointer;display: none" tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
			</div>
				
				<%}
				}if (objStatus == null){%>
								<div align="center">
				 <img class="button" src="/HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
				 <img class="button" src="/HIS/hisglobal/images/buttons/btn-clr.png" style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
			</div>
				<%} %>
				</td>
					<td
						style="background: transparent url(/HIS/hisglobal/images/avai/border-right.gif) repeat-y scroll right center; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
				</tr>
				<tr style="display:none">
					<td tabindex="1" width="5"><img
						src="/HIS/hisglobal/images/buttons/rnd-trans-ffffff-bdr-LB.gif"></td>
					<td
						style="background: transparent url(/HIS/hisglobal/images/avai/border-bottom.gif) repeat-x scroll center bottom; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td tabindex="1" width="5"><img
						src="/HIS/hisglobal/images/buttons/rnd-trans-ffffff-bdr-RB.gif"></td>
				</tr>
			</tbody>
		</table>
		</his:ButtonToolBarTag>
		<!------button Toolbar End------------------------------------>	


<input type="hidden" id="patPrimaryCat" value="<%= session.getAttribute("PatsPrimaryCatCode") %>" />
<html:hidden name="SplPatientVisitFB" property="departmentdiv" />

<input type="hidden" name="crNo" />
<html:hidden name="SplPatientVisitFB" property="hmode" value="unspecified"/>
<html:hidden name="SplPatientVisitFB" property="patFirstName" />
<html:hidden name="SplPatientVisitFB" property="patMiddleName" />
<html:hidden name="SplPatientVisitFB" property="patLastName" />
<html:hidden name="SplPatientVisitFB" property="patGender" />           
<html:hidden name="SplPatientVisitFB" property="patAge" />
<html:hidden name="SplPatientVisitFB" property="patPrimaryCatCode" />
<html:hidden name="SplPatientVisitFB" property="patGuardianName" />
<html:hidden name="SplPatientVisitFB" property="patAmountCollected" />
<html:hidden name="SplPatientVisitFB" property="isPatReferByList" />
<html:hidden name="SplPatientVisitFB" property="selectedFromDept" />
<html:hidden name="SplPatientVisitFB" property="patBillAmountWithoutGrouping" />
<html:hidden name="SplPatientVisitFB" property="patCrNo" />
<html:hidden name="SplPatientVisitFB" property="referInternalExternal" />
<html:hidden name="SplPatientVisitFB" property="referingRowIndex" />
<html:hidden name="SplPatientVisitFB" property="onlineReferedIndex"/>
<html:hidden name="SplPatientVisitFB" property="errorMessage"/>
<html:hidden name="SplPatientVisitFB" property="entryDate"/>
<html:hidden name="SplPatientVisitFB" property="episodeVisitNo"/>
<html:hidden name="SplPatientVisitFB" property="onRequestVisit" />
<html:hidden name="SplPatientVisitFB" property="captureMandatoryField"/>
<html:hidden name="SplPatientVisitFB" property="modeCase" />
<html:hidden name="SplPatientVisitFB" property="selectedCheckBox" />
<html:hidden name="SplPatientVisitFB" property="print" />
<html:hidden name="SplPatientVisitFB" property="saveSuccessful" />
<html:hidden name="SplPatientVisitFB" property="isPrintCard" />
<html:hidden name="SplPatientVisitFB" property="indexID" />
<html:hidden name="SplPatientVisitFB" property="selectedReferal" />
<html:hidden name="SplPatientVisitFB" property="departmentCode" />
<html:hidden name="SplPatientVisitFB" property="department" />
<html:hidden name="SplPatientVisitFB" property="patAptNo" />
<html:hidden name="SplPatientVisitFB" property="patAptSlot" />
<html:hidden name="SplPatientVisitFB" property="patAptQueueNO" />
<html:hidden name="SplPatientVisitFB" property="isAppointment" />
<html:hidden name="SplPatientVisitFB" property="patAmountNCrConsultation" />
<html:hidden name="SplPatientVisitFB" property="patAmountCrConsultation" />
<html:hidden name="SplPatientVisitFB" property="patIsCrossConsultantWithReferal" />
<html:hidden name="SplPatientVisitFB" property="patPrimaryCatGrpCode" />
<html:hidden name="SplPatientVisitFB" property="patActualAmount" />
<html:hidden name="SplPatientVisitFB" property="strRenewalType" />
<html:hidden name="SplPatientVisitFB" property="renewalRequired" />

<html:hidden name="SplPatientVisitFB" property="patAmountDeptWise" />
<html:hidden name="SplPatientVisitFB" property="patRenewalAmountDeptWise" />

<html:hidden name="SplPatientVisitFB" property="cardvalidityDate" />



				



<div id="blanket" style="height: 580;width: 1024;display: none;"></div>

	<div id="mandaatoryFields" style="display: none; height: 110px;width: 310px;position:absolute; top: 20%;left: 30%;z-index: 9100;background-color: #FFEBD5;">
				<table width="100%">
					<tr>
						<td width="80%" class="tdfonthead" align="center">
							<font color="#FF0000" size="2"	face="Verdana, Arial, Helvetica, sans-serif">
							Enter Mandatory Fields	
							</font>
						</td>
						<td width="20%" class="tdfonthead">
							<div align="right">
								<img class="button" tabindex="1" src="/HIS/hisglobal/images/buttons/button_cancel.png" style="cursor: pointer; " onkeypress="disableBlanket()" onclick="disableBlanket()">		
							</div> 
						</td>
					</tr>
					
				</table>
				<table width="100%">
					
					<%if(request.getAttribute("HTML_CODE")!=null){%>
					<%=request.getAttribute("HTML_CODE") %>
					<%} %>
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfonthead">
							<div align="left">
								<img class="button" tabindex="1" src="/HIS/hisglobal/images/avai/GoNew.png" style="cursor: pointer; position: absolute;" onkeypress="if(validateMandatory()) submitForm('SAVE')" onclick="if(validateMandatory()) submitForm('SAVE')">		
							</div> 
						</td>
					</tr>
					
				</table>
				<br>
				
			</div>
</div>
<his:status />

<div id="blanket" style="height: 580;width: 1024;display: none;"></div>

<div id="confirmMsg" style="display: none; height: 110px;width: 310px;position:absolute; top: 20%;left: 30%;z-index: 9100;background-image:url('../hisglobal/images/buttons/confirmYesNoImage.png'); ">
	<br>
	<br>
	<br>
	<div id="alertMessage1" align="center" style="position: absolute;left: 30%;top: 30%;"></div>
	 <div id='yesNoButton' style='position:absolute;left: 33%; top: 58%;'>
	 <input type='button' value='Yes' name="yesButton" onclick='closeAlert(true)' onkeyPress='if(event.keyCode==13) closeAlert(true)'
	  tabindex='1' id='yesButton' style='font-weight:bold;margin-right:10px;cursor:pointer;left: '>
	 <input type='button' value='No' name="noButton" tabindex='1' id='noButton'  
	 style='font-weight:bold;margin-left:10px;cursor:pointer;' onclick='closeAlert(false)' 
	 onkeyPress='if(event.keyCode==13) closeAlert(false)'>
	 </div>
</div>



<%} catch (Exception e) {
				System.out.println("e: " + e);
				e.printStackTrace();
			}

		%>



