<!--------------------------------------new department visit -------------------------------->
<%try {

				%>
<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,registration.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<LINK REL=StyleSheet HREF="/HISClinical/hisglobal/css/tab.css" TYPE="text/css" MEDIA=screen>
<script language="JavaScript" src="/HISClinical/registration/js/popup.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/commonFunctions.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/validationCommon.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/validationCalls.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/registration.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/utilityFunctions.js"></script>
<style type="text/css"> 
@media print { 
		#noprint 
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
var frameElement = parent.document.getElementById("frmMain"); 
var win = frameElement.contentWindow ;
win.print();
}

/*function callThisOnload(){	
	//alert("onload")
	//alert(document.getElementsByName('print')[0].value)
	if(document.getElementsByName('print')[0].value=='1')
	{
		//document.getElementById('divCardPrint').style.display="block"
		printPage();
		document.getElementsByName('print')[0].value='0'
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
	if(document.getElementsByName("errorMessage")[0].value!=""){
		alert(document.getElementsByName("errorMessage")[0].value)
		document.getElementsByName("errorMessage")[0].value=""
	}
}*/


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
		ValidateDepartmentNewDeptVisit();
	
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
function ValidateDepartmentOldDeptVisit(callerChecker) {
    var isValid = true;
 //   alert("document.getElementsByName('isRefferInOut')[0].checked "+document.getElementsByName("isRefferInOut")[0].checked)
//alert('inside ValidateDepartmentNewDeptVisit....');
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
		if(document.getElementsByName("renewalRequired")[0].value=="1" && document.getElementsByName('renewalAmount')[0].value!="0")			
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
	}	
	else
	
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
	else if(Obj.checked==false && oldvalue!="on")
	{
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
		document.getElementsByName('patRefHospitalDeptOther')[0].disabled=false
		document.getElementsByName('patRefHospitalDeptOther')[0].focus()
		}
	else
	{
	
	document.getElementsByName('patRefHospitalDeptOther')[0].disabled=true
	
	document.getElementsByName('patRefHospitalDeptOther')[0].value=""
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
		var isRef=document.getElementsByName("isReferred")[0]
		// alert("isRef.checked "+isRef.checked)
		var isRefInternalExternal=document.getElementsByName("referInternalExternal")[0].value;
		var index=document.getElementsByName("referingRowIndex")[0].value;
		if(isRef.checked==true){
			document.getElementById("checkReferral").style.display="block";
			document.getElementById("isRefferDiv").style.display="block";
			if(isRefInternalExternal=="I")
			{	
				
				document.getElementsByName("isRefferInOut")[0].checked=true;
			 	showInternalForGrouping();
			//  	alert(document.form[0].getElementsByName("isRefferInOut").value)
			 	if(index!="")
			 	{
			 // 		alert("index "+index)
			 		document.getElementsByName("refferringOPDEpisode")[parseInt(index)].checked=true;
			 		fromDeptCode=document.getElementsByName("selectedFromDept")[0].value;
			 		refIndex=document.getElementsByName("referingRowIndex")[0].value;
			 	}
	 		}
	 		if(document.getElementsByName("isRefferInOut")[1].checked)
			{	
				
				document.getElementsByName("isRefferInOut")[1].checked=true;
			 	showExternal(document.getElementsByName("isRefferInOut")[1])
	 		}
	 	} 
	 }	        
} 


function callThisOnload(){
//alert("isrefer "+document.getElementsByName("isPatReferByList")[0].value);
 //showDepartment();
// 	alert("fdfdfdfdf "+document.getElementsByName("isPatReferByList")[0].value)
//alert("onload")
	//alert(document.getElementsByName('print')[0].value)
	if(document.getElementsByName('print')[0].value=='1')
	{
		//document.getElementById('divCardPrint').style.display="block"
		printPage();
		document.getElementsByName('print')[0].value='0'
		//document.getElementById('divCardPrint').style.display="none"
	}
		focusCrNo();
		// document.getElementsByName("refferringOPDEpisode")[0].checked=true;
		// document.getElementsByName("isRefferInOut")[0].checked="on";
		getRefer();
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
	}
}

function showInternal(e){
	
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
	//alert('ValidateDepartmentNewDeptVisit');
	var isValid = true;
	var depCode=document.getElementsByName("departmentUnitCode")[0].value;
	if(document.getElementsByName("isPatReferByList")[0].value==<%=RegistrationConfig.IS_PAT_REFER_BY_LIST_TRUE%>)
    {
    	isValid=true;     
		//alert("inside IF") ;
	}
	else
	{
	if( (depCode=="") || (depCode=="-1") )
	{
		alert("Please Select Department");
		isValid=false;
	}
	else
	{
    	if(validateAgeForDeptToVisit('<%=RegistrationConfig.MAX_AGE_TO_REGISTER_IN_CHILD_DEPT%>','<%=RegistrationConfig.CHILD_DEPT_CODE%>')
   			<his:referMandatory property="departmentUnitCode" /> 
    		){
    		isValid=true;
    	}
    	else{
    		return false;
    	}
    	
    	
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
      	//alert(value2);
      		if(field)
      			value=field.value;
      		else
      			value="";
      
         	if ((value.length == 0)||(value=='-1'))
            { 
               	if(value2=='-1')
               	{
            		alert("Department is required");
                	isValid = false;
                	document.getElementsByName("departmentUnitCode")[0].focus();
              	}
            }
		}
		
			//alert("inside ELSE") 
	}
	}
	if(document.getElementsByName('oldDepartmentVisit')[0].checked==true && document.getElementsByName('newDepartmentVisit')[0].checked==true)
	{
		if(isValid)
			isValid=ValidateDepartmentOldDeptVisit();
	}	
	
	if((document.getElementsByName('oldDepartmentVisit')[0].checked==true && document.getElementsByName('newDepartmentVisit')[0].checked==true)||(document.getElementsByName('oldDepartmentVisit')[0].checked==true && document.getElementsByName('newDepartmentVisit')[0].checked==false))
	{
		return isValid;
	}
	else
	{
		if(isValid)
		{
			if(document.getElementsByName("patAmountCollected")[0].value!="0" && document.getElementsByName("patAmountCollected")[0].value!="0.00")			
				alert('Please Collect Rs '+  document.getElementsByName("patAmountCollected")[0].value);
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
       
function renewalConfirmation()
{
	alert("In renewalConfirmation");
	var valid=false;
	valid=confirm("DO you want to Renew the Registration ?");
	return valid;
}

function renewalValidation()
{
 	var len;
	var isValid = true;
	alert("In renewalValidation");
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



--></script>
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

 </his:statusNew>
 <his:InputCrNoTag name="SplPatientVisitFB"></his:InputCrNoTag>
<bean:define id="crNo" name="SplPatientVisitFB" property="patCrNo"
	type="java.lang.String" />


<his:statusList>
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
				
				<logic:iterate id="ARR_EPISODE_OLD_PAT_REFER_VO" name="<%=RegistrationConfig.ARR_EPISODE_OLD_PAT_REFER_VO %>" type="hisglobal.vo.EpisodeRefDtlVO" indexId="index">
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


	<logic:present
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
				<logic:iterate id="ARR_EPISODE_REFER_PAT_VO" indexId="index"
					name="<%=RegistrationConfig.ARR_EPISODE_REFER_PAT_VO %>" 
					type="hisglobal.vo.EpisodeRefDtlVO"  >
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
					 			<img src='<his:path src="/hisglobal/images/stop.png"/>' title="Unit not on duty">
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
	</logic:present>
	
	
	
</his:statusList>
<%if (!crNo.trim().equals("")) {%>
<his:statusTransactionInProcess>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />
<table width="100%">
<tr>

<logic:equal name="SplPatientVisitFB" property="modeCase"  value="1">
<td class="tdfonthead" nowrap="nowrap" width="25%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">New Department Visit</font></div></td>
<td class="tdfont" width="25%"><b>

				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<logic:equal value="On" name="SplPatientVisitFB" property="newDepartmentVisit">
					<input type="checkbox" name="newDepartmentVisit" onclick="checkuncheckNewDepartment(this)" checked="checked"/>
					</logic:equal>
					<logic:notEqual value="On" name="SplPatientVisitFB" property="newDepartmentVisit">
					<input type="checkbox" name="newDepartmentVisit" onclick="checkuncheckNewDepartment(this);"/>
					</logic:notEqual>
					
				
				</font></b></td>
<td class="tdfonthead" nowrap="nowrap" width="25%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Old Department Visit</font></div></td><td class="tdfont" width="25%"><b>

				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><input type="checkbox" name="oldDepartmentVisit" disabled="disabled"/></font></b></td>
</logic:equal>
<logic:equal name="SplPatientVisitFB" property="modeCase"  value="0">
<td class="tdfonthead" nowrap="nowrap" width="25%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">New Department Visit</font></div></td>
<td class="tdfont" width="25%"><b>

				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><input type="checkbox" name="newDepartmentVisit" disabled="disabled"/></font></b></td>
<td class="tdfonthead" nowrap="nowrap" width="25%">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Old Department Visit</font></div></td>
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
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">New Department Visit</font></div></td>
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
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Old Department Visit</font></div></td>
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
	<%varStatus = "InProcess";%>
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
	<bean:define id="fileId" name="<%=Config.FILE_NO_GENERATION_FLAG_NAME %>" />
	
	<%System.out.println("client name================================= "+fileId); %>
	

		<his:ContentTag>
			<div id="common" style="display:block">
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				bgcolor="#EBEBEB">
				<bean:define id="isRefer" name="SplPatientVisitFB"
					property="isPatReferByList"></bean:define>

				<tr>

					<td width="16%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <font
						color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
						key="department" />&nbsp; </font></div>
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
					<html:hidden name="SplPatientVisitFB" property="departmentUnitCode"/>
					</div>
					<div id="controlDepartment1" style="<%=controlDepartment12%>" align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="SplPatientVisitFB" property="toDepartment" />
						</font>	
					</div>
					<html:hidden name="SplPatientVisitFB" property="toDepartmentCode"/>
					</td>
			<logic:equal name="fileId"
				value="<%=Config.FILE_NO_GENRATION_MANNUAL_TRUE%>">		
					<td width="17%" class="tdfonthead">
					<div align="right" ><font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="fileNo" /> </font></div>
					</td> 

					<td width="17%" class="tdfont">
					<div align="left"  ><font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <html:text
						name="SplPatientVisitFB" tabindex="1" property="fileNo"
						maxlength="30" styleClass="textbox" /> </font></div>
					</td>
			</logic:equal>
					<td width="17%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="amountCollected" /> </font></div>
					</td>

					<td width="16%" class="tdfont"><html:text name="SplPatientVisitFB"
						tabindex="1" property="patAmountCollected" maxlength="6"
						styleClass="textbox" readonly="true" /></td>
				</tr>
			</table>
			</div>  
                           
		</his:ContentTag>
</logic:equal>		
<logic:equal value="On" name="SplPatientVisitFB" property="oldDepartmentVisit">
<his:SubTitleTag name="Visit Details">
	</his:SubTitleTag>
	<logic:notEmpty name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR %>">
	  <bean:define id="EPISODEVO" name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>" type="hisglobal.vo.EpisodeVO[]" />
	  <his:ContentTag>
  			<% if(EPISODEVO.length!=0) {
  				 %>
  				<%varStatus="InProcess";%>
		
				
					<table  width="100%" border="0" cellspacing="1" cellpadding="0">
						
						<tr>
						<td width="5%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="status"/></b></font></td>
						<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="department"/></b></font></td>
						<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="unit/room"/></b></font></td>
						<td width="10%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="lastVisitdate"/></b></font></td>
						<td width="25%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><bean:message key="con"/></b></font></td>
						<td width="25%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>Renewal Fee</b></font></td>
						</tr>
						<logic:iterate id="episode" name="<%=RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR%>" indexId="i"  type="hisglobal.vo.EpisodeVO">
						
						<%
						String renewalType=(String)episode.getRenewalType();
						String unitType=(String)episode.getIsGeneral()==null ? "" : (String)episode.getIsGeneral();						
						%>
						
						<%--<bean:define name="episode" id="renewalType" property="renewalType"/>
						<bean:define name="episode" id="unitType" property="isGeneral"/>  --%>

						<%String hh=(String)renewalType;
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
									<img src='<his:path src="/hisglobal/images/icn-lock.png"/>' title="Unit Already Visited Today">
									</div>
								</logic:equal>
								<!-- end unit already visited today -->
								<!--start Unit Not Visited Today and Unit Is ON Duty -->		
								<logic:equal name="episode"  property="visitedToday" value="<%=RegistrationConfig.DEPT_UNIT_VISITED_TODAY_FALSE %>">
									<logic:equal name="episode" property="deptUnitIsOnDuty" value="<%=RegistrationConfig.DEPT_UNIT_IS_ON_DUTY_TRUE%>">
									<% if(x==2 || x==4 || x==5){%>
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
												<img src='<his:path src="/hisglobal/images/stop.png"/>' title="Unit not on duty">
											</div>
										</logic:equal>
										<!-- end Unit type special -->
										<!-- start unit type general -->
										<logic:equal name="episode" property="isGeneral" value="<%=RegistrationConfig.UNIT_TYPE_GENERAL%>">
											<!-- start  other general unit working -->
											<logic:notEqual name="episode" property="isOtherUnitWorking" value="<%=RegistrationConfig.NO_DEPT_UNIT_WORKING_TRUE%>">
													<html:hidden name="SplPatientVisitFB" property="hiddenEpisode" />
													<div align="center">
														<img src='<his:path src="/hisglobal/images/forward3.gif"/>' title="Visit stamp on request" style=cursor:pointer tabindex="1" onclick = "submitFormOnValidate(validateOnRequest('<bean:write name="episode" property="episodeCode" />'),'SAVE');" onkeypress="if (event.keyCode==13) submitFormOnValidate(validateOnRequest('<bean:write name="episode" property="episodeCode" />'),'SAVE');" >
														
														</div>
											</logic:notEqual>
											<!-- end other general unit working -->
										
										
										<!-- start other general unit not working -->
										<logic:equal name="episode" property="isOtherUnitWorking" value="<%=RegistrationConfig.NO_DEPT_UNIT_WORKING_TRUE%>">
													<html:hidden name="SplPatientVisitFB" property="hiddenEpisode" />
													<div align="center">
														<img src='<his:path src="/hisglobal/images/stop.png"/>' title="No Unit Working In The Department" >
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
									<% if(x==2 || x==4 || x==5){%>
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
									<% if(x==2 || x==4 || x==5){%>
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
									<% if(x==2 || x==4 || x==5){%>
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
								
								<td <%=tdFontColor %> >
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
								</td>
								<td <%=tdFontColor %> >
								
								
									<div align="center">									
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<input type="text" name="renewalAmount" value='<bean:write name="episode" property="renewalAmount"/>' readonly="readonly">										
										</font>
									</logic:equal>
									<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_FALSE%>">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<input type="hidden" name="renewalAmount" value='<bean:write name="episode" property="renewalAmount"/>'>
										</font>
									</logic:equal>
									</div>
								</td>
						
							</tr>
							
										
						</logic:iterate>
						<logic:equal name="episode" property="renewalRequired" value="<%=RegistrationConfig.RENEWAL_REQUIRED_TRUE%>">
						<tr>
						<td class="tdfonthead" colspan="5"><div align="right" ><b>RENEWAL FEE:</b></div></td>
						<logic:equal name="episode" property="renewalAmount" value="<%=RegistrationConfig.PAT_CAT_PAID_FEE%>">
						<td class="tdfont" colspan="1"><div align="center"><input type="text" name="grandTotal" value="5.00" disabled="disabled"></div> </td>
						</logic:equal>
						<logic:equal name="episode" property="renewalAmount" value="<%=RegistrationConfig.PAT_CAT_FREE_FEE%>">
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
			<div align="right"><b><bean:message key="isreferred" /></b></div>
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
					<div id="checkReferral" style="display:none"><font color="#FF0000"
						size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referInternal" /> </font> <html:radio name="SplPatientVisitFB"
						property="isRefferInOut" tabindex="1" value="I"
						onclick="showInternal(this)" /> <font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="referExternal" /> </font> <html:radio name="SplPatientVisitFB"
						property="isRefferInOut" tabindex="1" value="E"
						onclick="showExternal(this)" /></div>
					</td>
					<td width="40%">
					<div id="externalRefer" style="display:none"><font color="#FF0000"
						size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="gnctd" /> </font> <html:radio name="SplPatientVisitFB"
						property="referringInstType" tabindex="1" value="G"
						onclick="showAssociated(this)" /> <font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="other" /> </font> <html:radio name="SplPatientVisitFB"
						property="referringInstType" value="O" tabindex="1"
						onclick="showOthers(this)" /> <html:hidden name="SplPatientVisitFB"
						property="isAssociated" /></div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
</div>		
	<bean:define id="clientFlag" name="<%=Config.CLIENT_NAME%>"></bean:define>

		<div id="commonRefer" style="display:none"><his:ContentTag>


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
						onkeypress="return validateAlphabetsOnly(event,this)" /></td>
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
						onkeypress="return validateAlphabetsOnly(event,this)" maxlength="100"
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
					<td width="25%" %  class="tdfont"><html:text name="SplPatientVisitFB"
						tabindex="1" property="patRefGnctdHospitalCrno" maxlength="12"
						tabindex="1" onkeydown="setPrevValue(this, event);"
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

		</his:ContentTag></div>


		<div id="internalRefer" style="display:none"><!--- ReferInternal -------  Details of all open episodes-->


		<his:ContentTag>
			<logic:notEmpty
				name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>">
				<bean:define id="OPD_OPEN_EPISODES"
					name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>"
					type="hisglobal.vo.EpisodeRefDtlVO[]" />


				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<%int i = 0;%>
					<tr>
						<td width="5%" class="addtoolbar"
							style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
							color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif">&nbsp;</td>

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
						name="<%=RegistrationConfig.ARR_OPD_OPEN_EPISODE_VO%>" indexId="index" type="hisglobal.vo.EpisodeRefDtlVO">
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
		</his:ContentTag> <!--- End ReferInternal ---- Details of all open episodes-->
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
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' onclick="showLegends();" onkeypress="if(event.keyCode==13) showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' onclick="showLegendsNone();" onkeypress="if(event.keyCode==13) showLegendsNone();">
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
					<img src='<his:path src="/hisglobal/images/icn-lock.png"/>'>
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
					<img src='<his:path src="/hisglobal/images/stop.png"/>'>
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
					<img src='<his:path src="/hisglobal/images/forward3.gif"/>'>
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
		<table cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td width="5"><img
						src="/HISClinical/hisglobal/images/rnd-trans-ffffff-bdr.gif"></td>
					<td
						style="background: transparent url(/HISClinical/hisglobal/images/border-top.gif) repeat-x scroll center top; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td width="5"><img
						src="/HISClinical/hisglobal/images/rnd-trans-ffffff-bdr-RT.gif"></td>
				</tr>
				<tr>
					<td
						style="background: transparent url(/HISClinical/hisglobal/images/border-left.gif) repeat-y scroll left center; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td style="background-color: rgb(255, 255, 255);" width="100%">
					<%Status objStatus = (Status) request
							.getAttribute(Config.STATUS_OBJECT);
					if (renewalStatus.equals("renew")) {

					%>
				<div align="center">
				 <img class="button" src="/../HIS/hisglobal/images/buttons/btn-sv.png" tabindex="1"  style=cursor:pointer onclick="if(renewalValidation())submitForm('RENEWAL');" onkeypress="if (event.keyCode==13) if(renewalValidation())submitForm('RENEWAL');">
				 <img class="button" src="/../HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
				 <img class="button" src="/../HIS/hisglobal/images/buttons/btn-clr.png" style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
				</div>
				<%} else if (objStatus != null) {
									HashSet statuslist = objStatus.getStatusList();
									if (statuslist.contains(objStatus.TRANSINPROCESS)
											&& (!statuslist.contains(objStatus.ERROR_DA))) {%>
			
				<div align="center">
				
				
				<logic:equal  name="SplPatientVisitFB"  property="newDepartmentVisit" value="On">
					<logic:equal  name="SplPatientVisitFB"  property="oldDepartmentVisit" value="">
				<img class="button" src="/../HIS/hisglobal/images/buttons/btn-sv.png" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidateDepartmentNewDeptVisit(document.getElementsByName('departmentsToVisitStamp')[0]),'CHECKMANDATORY');" onclick="submitFormOnValidate(ValidateDepartmentNewDeptVisit(document.getElementsByName('departmentsToVisitStamp')[0]),'CHECKMANDATORY');">
				
				</logic:equal>
				</logic:equal>
				
				<logic:equal  name="SplPatientVisitFB"  property="oldDepartmentVisit" value="On">
				<logic:equal  name="SplPatientVisitFB"  property="newDepartmentVisit" value="">
				<img class="button" src="/../HIS/hisglobal/images/buttons/btn-sv.png" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidateDepartmentOldDeptVisit(),'CHECKMANDATORY');" onclick="submitFormOnValidate(ValidateDepartmentOldDeptVisit(),'CHECKMANDATORY');">
				</logic:equal>
				</logic:equal>
				<logic:equal  name="SplPatientVisitFB"  property="oldDepartmentVisit" value="On">
				<logic:equal  name="SplPatientVisitFB"  property="newDepartmentVisit" value="On">
				<img class="button" src="/../HIS/hisglobal/images/buttons/btn-sv.png" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitFormOnValidate(ValidateDepartmentOldDeptVisit(),'CHECKMANDATORY');" onclick="submitFormOnValidate(ValidateDepartmentNewDeptVisit(document.getElementsByName('departmentsToVisitStamp')[0]),'CHECKMANDATORY');">
				</logic:equal>
				</logic:equal>				
				
				 <img class="button" src="/../HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
				 <img class="button" src="/../HIS/hisglobal/images/buttons/btn-clr.png" style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');"><!--
				
				 <logic:equal  name="SplPatientVisitFB"  property="newDepartmentVisit" value="On">
				<img class="button" src='../hisglobal/images/btn-reprint.png' tabindex="1" style="cursor: pointer;" onclick="document.forms[0].hmode.value='PRINT';document.forms[0].submit();" onkeypress="if(event.keyCode==13) document.forms[0].hmode.value='PRINT';document.forms[0].submit();">
				</logic:equal>
				
				--></div>
			
				<%} else {
			
									%>
				<div align="center">
				 <img class="button" src="/../HIS/hisglobal/images/buttons/btn-ccl.png" tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
				 <img class="button" src="/../HIS/hisglobal/images/buttons/btn-clr.png" style=cursor:pointer tabindex="1"  onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
			</div>
				
				<%}
								}%>
				</td>
					<td
						style="background: transparent url(/HISClinical/hisglobal/images/border-right.gif) repeat-y scroll right center; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
				</tr>
				<tr>
					<td tabindex="1" width="5"><img
						src="/HISClinical/hisglobal/images/rnd-trans-ffffff-bdr-LB.gif"></td>
					<td
						style="background: transparent url(/HISClinical/hisglobal/images/border-bottom.gif) repeat-x scroll center bottom; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial;"></td>
					<td tabindex="1" width="5"><img
						src="/HISClinical/hisglobal/images/rnd-trans-ffffff-bdr-RB.gif"></td>
				</tr>
			</tbody>
		</table>
		<!------button Toolbar End------------------------------------>	



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
								<img class="button" tabindex="1" src="/HISClinical/hisglobal/images/button_cancel.png" style="cursor: pointer; " onkeypress="disableBlanket()" onclick="disableBlanket()">		
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
								<img class="button" tabindex="1" src="/HIS/hisglobal/images/GoNew.png" style="cursor: pointer; position: absolute;" onkeypress="if(validateMandatory()) submitForm('SAVE')" onclick="if(validateMandatory()) submitForm('SAVE')">		
							</div> 
						</td>
					</tr>
					
				</table>
				<br>
				
			</div>
</div>
<his:status />

<div id="blanket" style="height: 580;width: 1024;display: none;"></div>

<div id="confirmMsg" style="display: none; height: 110px;width: 310px;position:absolute; top: 20%;left: 30%;z-index: 9100;background-image:url('../hisglobal/images/confirmYesNoImage.png'); ">
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



