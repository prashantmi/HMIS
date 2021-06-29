<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,registration.*"%>
<%@ page import="registration.config.RegistrationConfig"%>
<%@page import="registration.transactions.controller.fb.newSplRegFB"%>
<%@page import="hisglobal.hisconfig.Config"%>
<!DOCTYPE html>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />
<%
	try {
%>
<script type="text/javascript"
	src="./../../registration/js/commonFunctions.js"></script>

<style type="text/css">
@media print {
	#noprint {
		display: none;
	}
	#tabHeader {
		display: none;
	}
	#divCRNoLabel {
		display: block;
	}
	#divEpisodeDateLabel {
		display: block;
	}
	#divPatientNameLabel {
		display: block;
	}
	#divAgeLabel {
		display: block;
	}
	#divGenderLabel {
		display: block;
	}
	#divAreaLabel {
		display: block;
	}
	#divTokenLabel {
		display: block;
	}
	#divConsultantLabel {
		display: block;
	}
	#divDiagnosisLabel {
		display: block;
	}
	#divOccupationLabel {
		display: block;
	}
	#divOPDCardLabel {
		display: block;
	}
	#divOPDCardLogo {
		display: block;
	}
	#divOPDCardHeader {
		display: block;
	}
	#divPatientRegisteredLabel {
		display: none;
	}
	#divBlank1 {
		display: block;
	}
	#divBlank2 {
		display: block;
	}
	#divNoAppointmentAvailableLabel {
		display: none;
	}
	#divTitle {
		display: none;
	}
}

td.TdBorder {
	border: 2px solid black;
}

#divFooter {
	width: 779px;
	margin-top: 20px;
}

.border .div-table-col {
	border: 1px solid black;
}

.custbtncolor .ui-button-text {
	background: #086FA6;
	color: #FFFFFF;
	font-weight: bold
}

.custoverlay {
	opacity: 0.9;
}

.no-close .ui-dialog-titlebar-close {
	display: none
}

.label-yellow {
	background-image: url("none");
	/* color: #013157; */
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 15px;
	font-weight: bold;
	text-align: right;
	color: #e4b40d;
}
</style>

<link rel="stylesheet" href="/HIS/hisglobal/css/avai/Color.css">
<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet"
	type="text/css">
<link href="/HIS/hisglobal/css/buttons.css" rel="stylesheet"
	type="text/css">

<!-- Start:Sheeldarshi -->
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet"
	href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HIS/hisglobal/hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HIS/hisglobal/hisglobal/css/basic.css">
<link rel="stylesheet" href="/HIS/hisglobal/hisglobal/css/jquery-ui.css">
<!-- End: Sheeldarshi -->
<link rel="stylesheet"
	href="/HIS/hisglobal/datepicker/css/datepicker.css">



<!-- <script language="JavaScript" src="/AHIMS/registration/js/regValidation.js"></script> -->
<!-- <script language="JavaScript" src="/AHIMS/registration/js/popup.js"></script> -->
<!-- <script language="JavaScript" src="/AHIMS/registration/js/newRegistration.js"></script> -->

<script language="JavaScript"
	src="/HISRegistration/registration/transactions/js/newRegistration.js"></script>
<script language="JavaScript"
	src="/HISRegistration/registration/transactions/js/regValidation.js"></script>
<script language="JavaScript"
	src="/HISRegistration/registration/transactions/js/popup.js"></script>
<script language="JavaScript"
	src="/HISRegistration/hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/barcode_code39.js"></script>

<!-- Start:Sheeldarshi -->
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/jquery/basic.js"></script>

<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>

<!-- <script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script> -->
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/js/avai/popup.js"></script>
<script language="JavaScript" type="text/javascript"
	src="../../hisglobal/js/dateFunctions.js"></script>
<!-- End:Sheeldarshi -->
<script language="JavaScript" type="text/javascript"
	src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/moment.min.js"></script>

<script language="JavaScript"
	src="/HIS/hisglobal/js/generictemplate/validationFunctions.js"></script>
<script language="JavaScript"
	src="/HIS/hisglobal/js/utilityFunctions.js"></script>
<script language="JavaScript" src="/HIS/hisglobal/js/date_validator.js"></script>
<script type="text/javascript" src="./../../registration/transactions/js/saveBarcodeSlipPrintEntryInHMIS.js" /></script> 

<script>


var prCatGrpCode="";
function printPage() 
{
//var frameElement = parent.document.getElementById("f2"); 

//var frameElement1=document.getElementById("f2"); 
//alert("frameElement :"+frameElement);
//alert("frameElement1 :"+frameElement1);
//var win = frameElement.contentWindow ;
	window.print();
	if(document.getElementsByName('isBarcodeSlipPrint')[0].value == '1'){
		manageBarcodePrintProcess();
	}
}

function callThisOnload(){	
	//alert("onload");
	//alert(document.getElementsByName('print')[0].value)	
	if(typeof(document.getElementsByName('print')[0]) != "undefined"){
	if(document.getElementsByName('print')[0].value=='1')
	{
		//alert("Inside Print");
		//document.getElementById('divCardPrint').style.display="block"
		
		get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
		if(get_object("divBarCodeControlForBill")!=null)
		{
		get_object("divBarCodeControlForBill").innerHTML=DrawCode39Barcode(get_object("divBarCodeControlForBill").innerHTML, 0);
		}
		printPage();
		document.getElementsByName('print')[0].value='0';
		//document.getElementById('divCardPrint').style.display="none"
	}else if(document.getElementsByName('print')[0].value=='2'){
		if(document.getElementsByName('isBarcodeSlipPrint')[0].value == '1'){
			manageBarcodePrintProcess();
			document.getElementsByName('print')[0].value='0';//by warish for barcode slip priting error while selecting record
		}
	}
	//alert(document.getElementsByName("isIdRequired")[0].value);
	//document.getElementsByName("departmentCode")[0].focus();
	//alert(document.getElementsByName("patPrimaryCatCode")[0].value);
	//var patCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	//alert(document.getElementsByName("patPrimaryCatCode")[0].value);
	showState();
	//showLocation();
	document.getElementsByName("departmentCode")[0].focus;
	sendData();
	if(document.getElementsByName("isDuplicatePatientPopup")[0].value=="1"){
		
		//var path="/HISRegistration/registration/specialClinicNewRegWithAppointment.cnt?hmode=POPUP";
		//openPopup(path);
		showDuplicatePatientPopup();
	}
	
	//checkPatientCategory();
	//enableSpouseField();
	if(document.getElementsByName("errorMessage")[0].value!=""){
		//alert(document.getElementsByName("errorMessage")[0].value)
		document.getElementsByName("errorMessage")[0].value="";
	}
	//alert("Hi");
	//document.getElementsByName("patCardNo")[0].disabled=true;
	}
	// alert($('[name="localLanguage"]')[0].value);
	var LOCAL_LANGUAGE = $('[name="localLanguage"]')[0].value;
	initMultilingual(LOCAL_LANGUAGE); 
	
	ageWiseCheck();	
	document.getElementsByName('patAddPIN')[0].value="";
	
}

function ageSelection()
{
	//var today=new Date().toLocaleFormat('%d-%b-%Y');
	var today = moment().format('DD-MMM-YYYY');
	
	if(document.getElementsByName("isActualDob")[0].checked)
	{
		//document.getElementsByName("patDOB")[0].value="";
		document.getElementById("divAge").style.display="";
		document.getElementById("divDob").style.display="none";
		//document.getElementsByName("patDOB")[0].value=DateValidator.DATE_FORMAT;
	}
	else
	{
		document.getElementsByName("patAge")[0].value="";
		document.getElementsByName("patAgeUnit")[0].selectedIndex=0;
		document.getElementById("divAge").style.display="none";
		document.getElementById("divDob").style.display="block";		
	}
	
	/* $('#patDOBId').DatePicker({
		format: 'd-M-Y',default_position :'below', start_date:today, onSelect: function(d,i){if(d !== i.lastVal){$(this).change();futuredatecheck();}}
	}).val(today); */
	DateValidator.setup("divNewDOB", "patDOB_Dup", $("#patDOBId").val(), "dd-Mon-yyyy", "input45prcnt", "patDOBId_Dup", "1", "divNewDOBHidden");
}


function ageWiseCheck(){
	var age=document.getElementsByName("patAge")[0].value;
	var ageUnit=document.getElementsByName("patAgeUnit")[0].value;
	
	if((age<=17 && ageUnit=="Yr")||(age<=216 && ageUnit=="Mth")||(age<=939 && ageUnit=="Wk") ||(ageUnit=="D"))
	{
		document.getElementsByName("patHusbandName")[0].value="";
		document.getElementsByName("patHusbandName")[0].disabled=true;
	}
	else
	{
		document.getElementsByName("patHusbandName")[0].disabled=false;
	}
	
}

function futuredatecheck()
{
	var selDate=document.getElementsByName("patDOB")[0].value;
	var d1 = $.datepicker.parseDate("dd-M-yy", selDate);
	var d2=new Date();
	if(d1>d2){
		alert("Date of Birth Cannot be Greater than Current Date !");
		document.getElementsByName("patDOB")[0].value="";
		return false;
	}
	else
		return true;
	
	
}
function setCompany() {
	
	 //alert($("#clientCodeId option:selected").html());
	// alert(document.getElementsByName('clientCode')[0].);
	var element=document.getElementsByName('clientCode')[0];
	//alert(element.options[element.selectedIndex].text);
	$('[name="clientName"]')[0].value = element.options[element.selectedIndex].text;
	
}

function setRelation(objRel)
{
		$('[name="relationNameWithStaff"]')[0].value=objRel.options[objRel.selectedIndex].innerHTML;
}
function openBPLPopup(e)
{
		//openPopup('/AHIMS/registration/transaction/regBPLSearchPopup.jsp',e,300,600);
		var hmode = "CHECKBPLDETAILS"; 
		var bplCardNo=document.forms[0].patBPLCardNo.value;
		var url = "/HISRegistration/registration/specialClinicNewRegWithAppointment.cnt?hmode="+hmode+"&bplCardNo="+bplCardNo;
		ajaxFunction(url,"3");
}


function sendData()
{    
	//session.setAttribute("test","test");
	var LOCAL_LANGUAGE = $('[name="localLanguage"]')[0].value;
	initMultilingual(LOCAL_LANGUAGE);
	
	//commented code for fetching multilingual on page load
	//multilingualConversion(document.forms[0].patFirstName,document.getElementById('patFirstNameInMultiLangId'));
	//multilingualConversion(document.forms[0].patMiddleName,document.getElementById('patMiddleNameInMultiLangId'));
	//multilingualConversion(document.forms[0].patLastName,document.getElementById('patLastNameInMultiLangId')); 
	
	 var patientPriCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;

	 if(patientPriCatCode.split('#')[0]!=-1){
		$("#divFeeLabel").show();$("#divFeeVal").show();$("#divFeeVal2").show();
	}else{
		$('[name="paymentModeCode"]').val('1');PaymentModeRefId($('[name="paymentModeCode"]')[0]);
		$("#divFeeLabel").hide();$("#divFeeVal").hide();$("#divFeeVal2").hide();$("#divFeeVal3").hide();
	}
	//alert("Inside SendData ....patientPriCatCode="+patientPriCatCode)
 	 var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%>";
 	 
	 //Start: Sheeldarshi: 7thOct'14 ------------------------------------------------------------------------------
	/*  ## 		Modification Log							
 		##		Modify Date				:10thMar'15 
 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
 		##		Modify By				:Sheeldarshi 
		 */
		
		if(patientPriCatCode.split('#')[15]=="1"){
			document.getElementById('trRMO').style.display = "";
		}else{
			document.getElementById('trRMO').style.display = "none";
		}
		//End 
 	 if(patientPriCatCode.split('#').length>13){
 		
	 var PrimaryCatGroupCode=patientPriCatCode.split('#')[9];
	 var isIdRequired=patientPriCatCode.split('#')[3];
	 prCatGrpCode=PrimaryCatGroupCode;
	 
	 if(isIdRequired=='1'){
		document.getElementById("tdPatIdNoLabel").style.display="block";
		document.getElementById("tdPatIdNo").style.display="block";
	 }else{
		 document.getElementById("tdPatIdNoLabel").style.display="none";
		 document.getElementById("tdPatIdNo").style.display="none";
	 }
	 
	 if(PrimaryCatGroupCode=="<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY%>")
	 {		 
	 
		 
		 //document.getElementById("tdPatIdNoLabel").style.display="block";
		 //document.getElementById("tdPatIdNo").style.display="block";
		 document.getElementById("divCatGroupTestId").style.display="block";
	 	 document.getElementById("divCatGroupArogyaShreeBeneficiaryId").style.display="none";
		 /* $("#creditLetterDateId").datepicker({dateFormat: 'dd-M-yy',
		 onSelect: function(d,i){
	          if(d !== i.lastVal){
	              $(this).change();
	          }
	     }}).datepicker("setDate", new Date()); */
	     var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
		//var _todayPlus30Days=new Date();
		//_todayPlus30Days.setDate(_todayPlus30Days.getDate() + 29);
		///_todayPlus30Days=_todayPlus30Days.toLocaleFormat('%d-%b-%Y');
		var _todayPlus30Days = moment().add(30, 'days').format('DD-MMM-YYYY');
		
		$('#creditLetterDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
	
		 $("#creditLetterDateId").change(function(){
			 /*var date2 = $('#creditLetterDateId').datepicker('getDate');
				date2.setDate(date2.getDate()+30); 
				$("#cardvalidityDateId").datepicker({ dateFormat: "dd-M-yy" }).datepicker("setDate", new Date(date2));*/	
				var _dateStr=$(this).val().replace(/-/g,' ');
				var _date = new Date(_dateStr);
				_date.setDate(_date.getDate() + 29);

				var wrappedDate = moment(_date);
				_date=wrappedDate.format('DD-MMM-YYYY');
				//_date=_date.toLocaleFormat('%d-%b-%Y');
				
				$('#cardvalidityDateId').DatePicker({
					format: 'd-M-Y',default_position :'below',start_date:_date,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
				}).val(_date);	
				
			});
		 /*$("#cardvalidityDateId").datepicker({
			dateFormat : 'dd-M-yy'
		}).datepicker("setDate", "30");*/
		$('#cardvalidityDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:_todayPlus30Days,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(_todayPlus30Days);
		 $("#clientNameLabel")[0].innerHTML=patientPriCatCode.split('#')[14];
	 }
	 else if(PrimaryCatGroupCode=="<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE%>")
	 {
 		 $('[name="agsDistrictCode"]')[0].value = $('[name="patAddDistrictCode"]')[0].value;
		 //document.getElementById("tdPatIdNoLabel").style.display="none";
		 //document.getElementById("tdPatIdNo").style.display="none";		 
		 document.getElementById("divCatGroupTestId").style.display="none";
		 document.getElementById("divCatGroupArogyaShreeBeneficiaryId").style.display="block";
	
	 	 }
	 else{
		 document.getElementById("divCatGroupArogyaShreeBeneficiaryId").style.display="none";
		 //document.getElementById("tdPatIdNoLabel").style.display="none";
		 //document.getElementById("tdPatIdNo").style.display="none";		 
		 document.getElementById("divCatGroupTestId").style.display="none";
	 }
	 	
	 }
	//End: Sheeldarshi: 7thOct'14 ------------------------------------------------------------------------------
	
	
	 if(patientPriCatCode!=patEmployeeCode)
	 {	
	 	//if(patientPriCatCode==patBPLCode)
	 	//{
	 	//	document.getElementById("bplFamilyTrId").style.display="";
		//	document.getElementById("bplFamilyDivId").style.display="";
		//	document.getElementById("mandatoryAreaCat").style.display="";
	 	//}
	 	//else
	 	//{
	 	//	document.getElementById("bplFamilyTrId").style.display="none";
		//	document.getElementById("bplFamilyDivId").style.display="none";
		//	document.getElementById("mandatoryAreaCat").style.display="none";
	 	//}
	 	enableForNonEmployee();
	 	document.getElementById("bplFamilyTrId").style.display="none";
		document.getElementById("bplFamilyDivId").style.display="none";
	 	document.getElementById("divempIdLabel").style.display="none";
 	 	document.getElementById("divempIdControl").style.display="none";
	 }
	 if(patientPriCatCode==patEmployeeCode)
 	 {	
 	 	document.getElementById("divempIdLabel").style.display="";
 	 	document.getElementById("divempIdControl").style.display="";
 	 	document.getElementById("bplFamilyTrId").style.display="none";
		document.getElementById("bplFamilyDivId").style.display="none";
 	 	//document.getElementById("divstaffIdLabel").style.display="none";
 	 	//document.getElementById("divstaffIdControl").style.display="none";
 	 	document.getElementById("divIdTextLabel").style.display="none";
 	 	document.getElementById("divIdTextBox").style.display="none";
 	 	//document.getElementById("divCategoryAlignmentHead").style.display="none";
 	 	//document.getElementById("divCategoryAlignment").style.display="none";
 	 	disableForEmployee();
 	 }
 	 else
 	 {
 		
 	 	var hmode = "GETAMOUNTANDBPLDETAILS"; 

 	 	//alert("GETAMOUNTANDBPLDETAILS");
		var tariffIdValue='<%=RegistrationConfig.NEW_REGISTRATION_TARIFF_ID%>';
		var url = "/HISRegistration/registration/specialClinicNewRegWithAppointment.cnt?hmode="+hmode+"&tariffId="+tariffIdValue+"&patientCat="+patientPriCatCode;
		ajaxFunction(url,"1");
	}
}

function isPaidCostAvail(prCatGrpCode,obj)
{
	var rtnVal=true;
	//alert("Cat Group Code "+prCatGrpCode);
	//alert("Fee "+obj.value);
	if(prCatGrpCode=="<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_PAID%>" &&
			obj.value<='0')
	{	
		alert("It is paid category and charges are not defined in Charge Master.");
		document.getElementsByName("patPrimaryCatCode")[0].focus();
		rtnVal=false;
	}
	return rtnVal;
}

function getDistrictBasedOnState(obj)
{
	var stateCode=obj.value;
	var defaltStateCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";  
	if(stateCode!="" && stateCode!='-1')
	{
		if(stateCode==defaltStateCode)
		{
	 		document.getElementById("districtCombo").style.display="";
	 		document.getElementById("districtComboNonDefault").style.display="none";
	 	}
	 	else
	 	{
	 		var hmode = "GETDISTRICTLISTBASEDONSTATE"; 
			var url = "/HISRegistration/registration/specialClinicNewRegWithAppointment.cnt?hmode="+hmode+"&patAddStateCode="+stateCode;
			ajaxFunction(url,"2");
			document.getElementById("districtCombo").style.display="none";
			document.getElementById("districtComboNonDefault").style.display="";
		}
 	}
}
function getAjaxResponse(res,mode)
{
		//alert("Response"+res);
		//alert("Mode"+mode);
		if(mode == '1')
		{
			var patientPriCatCode=document.getElementsByName("patPrimaryCatCode")[0].value;
			var PrimaryCatGroupCode=patientPriCatCode.split('#')[9];
			//if(patientPriCatCode=='13')//BPL
			//{
			//	document.getElementById("bplFamilyTrId").style.display="";
			//	var objEle = document.getElementById("bplFamilyDivId");
				//objEle.innerHTML = res.split('^')[1];
			//	document.forms[0].patAmountCollected.value=res.split('^')[0];
			//	document.forms[0].patBPLCardNo.focus();
			//}
			//else
			//{
				document.getElementById("bplFamilyTrId").style.display="none";
				var amt=res.split('^')[0];
				if(amt=='-1'){
					amt=0;
				}
     			document.forms[0].patAmountCollected.value=amt;
				document.forms[0].isIdRequired.value=res.split('^')[1];
				document.getElementById('yellowAmount').innerHTML = amt; 
				//checkPatientCategory();
				//}
			
			var regFee=res.split('^')[0];
			
			if(PrimaryCatGroupCode!="<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY%>" &&
			 		PrimaryCatGroupCode!="<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE%>")
			 	{
			 		
			 		if(regFee=="-1")
			 			{
						// $('[name="patAmountCollected"]')[0].value=0;
						 $('[name="patActualAmount"]')[0].value=0;		
						}
					else
						{
							//$('[name="patAmountCollected"]')[0].value= document.forms[0].patAmountCollected.value;
							$('[name="patActualAmount"]')[0].value= regFee;
						}
			 		$('[name="creditBillFlag"]')[0].value="0";	
			 	}
				else
				{		
					
					if(regFee=="-1")
					{
						 $('[name="patAmountCollected"]')[0].value=0;
						 $('[name="patActualAmount"]')[0].value=0;		
					}
					else
						{
						$('[name="patActualAmount"]')[0].value= regFee;
						 $('[name="patAmountCollected"]')[0].value=0;  
						
						}
					$('[name="creditBillFlag"]')[0].value="1";	
				 }
			
			//To Check whether the Paid Categories Cost are defined in Charge Mst or not Added by Singaravelan on 03-Nov-2014 
			isPaidCostAvail(prCatGrpCode,$('[name="patActualAmount"]')[0]);
			
		}
		if(mode == '2')
		{
			var stateCode=document.getElementsByName("patAddStateCode")[0].value;
			var defaltStateCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";  
			
			if(stateCode==defaltStateCode)
			{
		 		document.getElementById("districtCombo").style.display="";
		 		document.getElementById("districtComboNonDefault").style.display="none";
	 		}
	 		else
	 		{
		 		document.getElementById("districtCombo").style.display="none";
				document.getElementById("districtComboNonDefault").innerHTML="<select name='patAddDistrictCode' tabindex='2' class='regcbo'><option value='-1'>Select Value</option>"+res+"</select>";
				document.getElementById("districtComboNonDefault").style.display="";
			}			
		}
		if(mode == '3')
		{
			if(res>0)
			{
				var path="/HISRegistration/registration/specialClinicNewRegWithAppointment.cnt?hmode=BPLPOPUP&bplCardNo="+document.forms[0].patBPLCardNo.value;
				openPopup(path);
			}
		}
}   
<%-- function checkPatientCategory() 
{
	 var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	 var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";
	 var patStaffCode="<%=Config.PRIMARY_CATEGORY_STAFF_CODE %>";
	 var dataFromTabletrue="<%=Config.PATCAT_EMPLOYEE_DATA_FROM_TABLE_TRUE%>";
	 var dataFromTableStatus=document.getElementsByName("patDataFromEmployeeTable")[0].value;
	 var dataFromTableStatusObject=document.getElementsByName("patDataFromEmployeeTable")[0];
	 var clientValue="<%=Config.CLIENT%>";
	 var clientPGIMER="<%=Config.CLIENT_PGIMER%>";
	 var patCatEmpFieldValue="<%=Config.PATCAT_EMPLOYEE_FIELD_VALUE%>";
	 var patCatEmpFieldEditFalse="<%=Config.PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE%>";
	 if(patPriCode!=patEmployeeCode)
	 {
	 	enableForNonEmployee(dataFromTableStatusObject,dataFromTabletrue,clientValue,clientPGIMER,patCatEmpFieldValue,patCatEmpFieldEditFalse);
	 	document.getElementsByName("patAddCountryCode")[0].value='<%= RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>';
		document.getElementsByName("patAddStateCode")[0].value='<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>';
		document.getElementsByName("patNationalityCode")[0].value='<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>';
		//showLocation();
	 }
	 
 	 if(patPriCode==patEmployeeCode)
 	 {	
 	 	//document.getElementById("divempIdLabel").style.display=""
 	 	//document.getElementById("divempIdControl").style.display=""
 	 	//document.getElementById("divstaffIdLabel").style.display="none"
 	 	//document.getElementById("divstaffIdControl").style.display="none"
 	 	document.getElementById("divIdTextLabel").style.display="none"
 	 	document.getElementById("divIdTextBox").style.display="none"
 	 	//document.getElementById("divCategoryAlignmentHead").style.display="none"
 	 	//document.getElementById("divCategoryAlignment").style.display="none"
 	 	//disableForEmployee(dataFromTableStatusObject,dataFromTabletrue,clientValue,clientPGIMER,patCatEmpFieldValue,patCatEmpFieldEditFalse);
 	 }
 	 else
 	 {
 	 	 //alert(document.getElementsByName("isIdRequired")[0].value);
 		 if(document.getElementsByName("isIdRequired")[0].value==<%=RegistrationConfig.PAT_CAT_ID_REQUIRED_YES%>)
	 	 {
	 	 	//document.getElementById("divempIdLabel").style.display="none";
	 	 	//document.getElementById("divempIdControl").style.display="none";
	 	 	//document.getElementById("divstaffIdLabel").style.display="none";
	 	 	//document.getElementById("divstaffIdControl").style.display="none";
	 	 	document.getElementById("divIdTextLabel").style.display="";
 	 		document.getElementById("divIdTextBox").style.display="";
 	 		//document.getElementById("divCategoryAlignmentHead").style.display="none";
 	 		//document.getElementById("divCategoryAlignment").style.display="none"; 	 	
 	 		//ageSelection()
	 	 }
	 	 else
	 	 {
	 	 	//document.getElementById("divempIdLabel").style.display="none";
	 	 	//document.getElementById("divempIdControl").style.display="none";
	 	 	//document.getElementById("divstaffIdLabel").style.display="none";
	 	 	//document.getElementById("divstaffIdControl").style.display="none";
	 	 	document.getElementById("divIdTextLabel").style.display="none";
 	 		document.getElementById("divIdTextBox").style.display="none";
 	 		//document.getElementById("divCategoryAlignmentHead").style.display="";
 	 		//document.getElementById("divCategoryAlignment").style.display=""; 	 		
 	 		//ageSelection()
	 	 }
	 	 	document.getElementsByName("patHusbandName")[0].disabled=false;
	 }	
	  
	 if(patPriCode=='<%=Config.PRIMARY_CATEGORY_POOR_FREE_CODE%>')
	 {
		//document.getElementById("familyHeadDiv").style.display="block";
		//document.getElementById("familyHeadTextDiv").style.display="block";
		//document.getElementById("relationDiv").style.display="block";
		//document.getElementById("relationComboDiv").style.display="block";
		//document.getElementById("mmjrkDiv").style.display="block";
		//document.getElementById("mmjrkTextDiv").style.display="block";
		//document.getElementsByName("patHusbandOccupation")[0].disabled=false;	
	}
	else
	{
		//document.getElementById("familyHeadDiv").style.display="none";
		//document.getElementById("familyHeadTextDiv").style.display="none";
		//document.getElementById("relationDiv").style.display="none";
		//document.getElementById("relationComboDiv").style.display="none";
		//document.getElementById("mmjrkDiv").style.display="none";
		//document.getElementById("mmjrkTextDiv").style.display="none";
	} 	
} --%>

 function PaymentModeRefId(obj){
	//alert("setPaymentModeRefId: "+obj.value);
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

function validateCategoryIDRequired()
{
	var valid=true
	var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value;
	var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%>";
	if(document.getElementsByName("isIdRequired")[0].value=="1")
	{
		if(patPriCode!=patEmployeeCode)
		{
			valid=isEmpty(document.getElementsByName("patCatIdNo")[0],"Patient Category Id")
		}
	}
	return valid
}
 
function showState()
{ 
	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	var stateCode= document.getElementsByName("patAddStateCode")[0];
	if(contryCode==defaltcontryCode)
	{
		document.getElementById("stateMandotary").style.display="";
		document.getElementById("divpatAddStateCodeCombo").style.display="";
		document.getElementById("divpatAddStateCodeText").style.display="none";
		document.getElementById("stateNotMandotary").style.display="none";
		document.getElementById("districtCombo").style.display="";
		document.getElementById("districtTextBox").style.display="none";
		document.getElementById("districtComboNonDefault").style.display="none";
		
	 	<%-- document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>"; --%>
		
	}
	else
	{
		document.getElementById("stateMandotary").style.display="none";
		document.getElementById("divpatAddStateCodeCombo").style.display="none";
		document.getElementById("divpatAddStateCodeText").style.display="";
		document.getElementById("stateNotMandotary").style.display="";
		//document.getElementById("divpatAddCityLocCode").style.display="none"; 
	  	document.getElementById("divpatAddCityLocation").style.display="";
	  	document.getElementById("districtCombo").style.display="none";
		document.getElementById("districtTextBox").style.display="";
		document.getElementById("districtComboNonDefault").style.display="none";
	  	document.getElementsByName('patAddCity')[0].value="";
	  	document.getElementsByName('patIsUrban')[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE%>";
	  	document.getElementsByName('patAddPIN')[0].value="";
	  	//document.getElementsByName('patAddCityLocCode')[0].value="-1";
	  	document.getElementsByName('patAddDistrictCode')[0].value="-1";
	  	
	}
	//  showLocation();
	
}
function Validate()
{ 
	//alert(document.getElementById('patMiddleNameInMultiLangId').value);
	var valid=true;	
	var dataFromTableStatus=document.getElementsByName("patDataFromEmployeeTable")[0].value;
	//var bb=isEmpty(document.forms[0].patAge,"Age");
	var patPriCode=document.getElementsByName("patPrimaryCatCode")[0].value.split("#")[0];
	//document.getElementsByName("hdnpatPrimaryCatCode")[0].value=document.getElementsByName("patPrimaryCatCode")[0].value;
	<%-- var patEmployeeCode="<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE %>";	 --%>
	var patEmployeeCode="<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_STAFF%>";
	getDepartmentName();
	//Start:Sheeldarshi
	
	if(document.getElementById('trRMO').style.display == "" && document.getElementsByName("patRMOEmployee")[0].value=="-1")
	{
	alert("Please select authorized by");
	document.getElementsByName("patRMOEmployee")[0].focus();
	return false;
	}
	var PrimaryCatGroupCode=document.getElementsByName("patPrimaryCatCode")[0].value.split('#')[9];
	var isIdRequired=document.getElementsByName("patPrimaryCatCode")[0].value.split('#')[3];

		
	if(isIdRequired==1){
		if(document.getElementsByName("shownPatIdNo")[0].value=="")
		{
		alert("Please Enter Id");
		document.getElementsByName("shownPatIdNo")[0].focus();
		return false;
		}
	}
	
	if(PrimaryCatGroupCode=="<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY%>")
		{
		//alert(document.getElementsByName("shownPatIdNo")[0].value);
		/* if(document.getElementsByName("shownPatIdNo")[0].value=="")
			{
			alert("Please Enter Id");
			document.getElementsByName("shownPatIdNo")[0].focus();
			return false;
			} */
		
		 if(document.getElementsByName("creditLetterRefNo")[0].value=="")
			{
			alert("Please Enter Refrence Letter No");
			document.getElementsByName("creditLetterRefNo")[0].focus();
			return false;
			}
		 
		 if(document.getElementsByName("creditLetterDate")[0].value!="")
			{
			 	if(dateNotInRange()){					
					return false;
			 	}
			}
		 if(document.getElementsByName("clientCode")[0].value=="-1")
			{
			 				
			 alert("Please Select Company");
			 document.getElementsByName("clientCode")[0].focus();
			 return false;
			 
			}
		//by Mukund on 26.07.2016
		 if(document.getElementsByName("letterType")[0].value=="-1")
			{
			alert("Please select Letter Type");
			document.getElementsByName("letterType")[0].focus();
			return false;
			}
		//by Mukund on 27.07.2016
		 if(document.getElementsByName("staffCardNo")[0].value=="")
			{
			alert("Please Enter Staff No");
			document.getElementsByName("staffCardNo")[0].focus();
			return false;
			}
		 
		}
	 	 else if(PrimaryCatGroupCode=="<%=RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE%>")
	 	{
	 		 //alert(document.getElementsByName("agsNo")[0].value);
	 		if(document.getElementsByName("agsNo")[0].value=="")
			{
			alert("Please Enter Aarogyasri No");
			return false;
			}
		 }  
	//End:Sheeldarshi
	if((patPriCode==patEmployeeCode))
	{ 
		if(dataFromTableStatus=="1")//True 
	 	valid=true;
	 	else
	 	{
		 	alert("Please Fetch Data For Employee");
		 	valid=false; 
		 	return valid;
	 	}
	}
	if((patPriCode==patEmployeeCode) && (dataFromTableStatus=="1") )
	{	
		 //if(comboValidation(document.forms[0].patPrimaryCatCode,"Patient Category")
				 if(comboValidation(document.getElementsByName("patPrimaryCatCode")[0],"Patient Category")
			 && validateDot(document.forms[0].patAddCityLoc,"Location")
			  && checkIsrefer()
		 	  && isEmpty(document.forms[0].patRefDoctor,"Referred By Doctor") 
		 	)
		 {
			  valid=true;
		 }
		 else
			 valid=false;
	}
	else
	{ 
		//if(comboValidation(document.forms[0].patPrimaryCatCode,"Patient Category")
				if(comboValidation(document.getElementsByName("patPrimaryCatCode")[0],"Patient Category")
		 && validateCategoryIDRequired()
		 //&& checkBPLMandatoryFields()
		 && isEmpty(document.forms[0].patFirstName,"First Name") 
		 && validateDot(document.forms[0].patFirstName,"First Name")
		 //&& isEmpty(document.forms[0].patLastName,"Last Name")
		 && validateSpecialCharacter('/',document.forms[0].patFirstName,"First Name")
		 && validateSpecialCharacter('-',document.forms[0].patFirstName,"First Name")
		 && validateDot(document.forms[0].patMiddleName,"Middle Name")
		 //&& isEmpty(document.forms[0].patLastName,"Last Name")
		 && validateDot(document.forms[0].patLastName,"Last Name")
		 //&& comboValidation(document.forms[0].patCasteCode,"Patient Caste")
		 && checkAgeOrDob()
		 && isValid(document.forms[0].patAge,"Age",125) 
		 && isEmpty(document.forms[0].patAgeUnit,"Age Unit")
		 //&& validatePatientDOBAgainstSysDate()
		<%--  && validateAgeForDeptReg('<%=RegistrationConfig.MAX_AGE_TO_REGISTER_IN_CHILD_DEPT%>','<%=RegistrationConfig.CHILD_DEPT_CODE%>') --%>
		 && comboValidation(document.forms[0].patGenderCode,"Gender")
		 && validateFatherNameSpouseName()
		 && validateSpouseName()
		 && validateDot(document.forms[0].patGuardianName,"Father Name")
		 && validateDot(document.forms[0].patHusbandName,"Spouse Name")
		 && validateDot(document.forms[0].patMotherName,"Mother Name")
		 //&& comboValidation(document.forms[0].patNationalityCode,"Nationality")
		 //&& comboValidation(document.forms[0].patReligionCode,"Religion")
		 && checkAnotherID()
		 && checkState()
		// && isEmpty(document.forms[0].patAddHNo,"Address")
		 && validateDot(document.forms[0].patAddCityLoc,"Location")
		 //&& validateDot(document.forms[0].patAddDistrict,"District")
		 //&& isEmpty(document.forms[0].patAddCity,"City / Village")
		 && validateDot(document.forms[0].patAddCity,"City / Village")
		 && validateMinimumLength(document.forms[0].patAddPIN,'Pin Number','6') 
		 && validatePinNumber(document.forms[0].patAddPIN)
		 //&& isEmpty(document.forms[0].patAddPIN,"Pin Code")
		 && isEmpty(document.forms[0].patAddMobileNo,"Mobile No")
		 && checkBPL()
		 && checkIsrefer()
	 	 && isEmpty(document.forms[0].patRefDoctor,"Referred By Doctor") 
	  ) 
	 {
	  	valid=true;
	 }
	 else
	 	valid=false;     
	 }
	// Setting Date from format 'dd-mm-yyyy' format to 'dd-Mon-yyyy'
	
	if(valid)
	{
		if(document.getElementsByName("patNationalId")[0].value!="" && document.getElementsByName("patNationalId")[0].value.length!=12){
			alert("Please Enter valid Aadhaar Id of 12 digits !");
			document.getElementsByName("patNationalId")[0].focus();
			valid=false;    
		}
	}
	
	var patPriCode = document.getElementsByName("patPrimaryCatCode")[0].value.split("#")[0];
	var patEmployeeCode = "<%=Config.PRIMARY_CATEGORY_EMPLOYEE_CODE%>";
	if(valid==true && patPriCode!=patEmployeeCode)
	{
		/* if(document.forms[0].patDOB.value!="")
		{
			var dateOfBirth = convertStrToDate(document.forms[0].patDOB.value,"dd-MM-yyyy");
			document.forms[0].patDOB.maxLength = 11;
			document.forms[0].patDOB.value = convertDateToStr(dateOfBirth,"dd-Mon-yyyy");
		} */
	}
	if(valid)
		valid=isPaidCostAvail(prCatGrpCode,$('[name="patActualAmount"]')[0]);
	
	return valid;    
}

function dateNotInRange()
{
	var _sysDate=new Date();
	var _chkDate=convertStrToDate(document.forms[0].creditLetterDate.value,"dd-Mon-yyyy");
	
	var timeDiff = Math.abs(_sysDate.getTime() - _chkDate.getTime());
	var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
	
	
	if(_chkDate>_sysDate){
		alert("Refrence Letter Date Should not be Greater than Sysdate");
		document.forms[0].creditLetterDate.focus();
		return true;
	}		
	else if(diffDays>30){
		alert("Refrence Letter Date must be within 30 Days");
		document.forms[0].creditLetterDate.focus();
		return true;
	}
	else
		return false;
	
}

function checkAnotherID()
{
	var valid=true;
	if(document.getElementsByName("patDocType")[0].value!="-1")
	{
		 if (document.getElementsByName("patCardNo")[0].value=="")
		{
			alert("Plaese Enter the Card No..!");
			document.getElementsByName("patCardNo")[0].focus();
			valid=false;
		} 
		
	}
	return valid;
}

function checkAgeOrDob()
{
	var valid=true;
	if(document.getElementsByName("isActualDob")[0].checked)
	{
		valid=isEmpty(document.forms[0].patAge,"Age");
		document.forms[0].patDOB.value="";
	}
	else
	{
		valid=isEmpty(document.forms[0].patDOB,"Date of Birth");
		 var d1 = $.datepicker.parseDate("dd-M-yy", document.forms[0].patDOB.value);
			
		 var ctdt = new Date();
		 var dmy = document.getElementsByName("seniorCitizenAgeLimit")[0].value;
		 var dmyFlag="y";
		 var month = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
		 if(dmyFlag=="y")
			 ctdt.setFullYear(ctdt.getFullYear()-dmy);
		 else if(dmyFlag=="m")
			 ctdt.setMonth(ctdt.getMonth()-dmy);
		 else if(dmyFlag=="d")
			 ctdt.setDate(ctdt.getDate()-dmy);
		 var msg="Date Should be less than "+ctdt.getDate()+"-"+(month[ctdt.getMonth()])+"-"+ctdt.getFullYear()+".";
		valid=d1 <= ctdt;
		//if(!valid)
		//alert(msg);
		// Validate Date of Birth
		//if(!DateValidator.validate(document.forms[0].patDOB,"Date of Birth"))
		//	valid = false;
	}
	if(!valid)	
	return valid;

	// Senior Citizen Check
	//Start:Sheeldarshi
	//Reason:Bug 10166 - System should be able to validate senior citizen category
	<%-- var seniorCitizenCode = '<%=Config.PRIMARY_CATEGORY_SENIOR_CITIZEN%>';
	var seniorCitizenAge = '<%=Config.SENIOR_CITIZEN_AGE%>'; --%>
	//alert(seniorCitizenCode);
		var PRIMARY_CATEGORY_SENIOR_CITIZEN = $('[name="seniorCitizenCatCode"]')[0].value;
		var seniorCitizenAge=document.getElementsByName("seniorCitizenAgeLimit")[0].value;
		var seniorCitizenCode = PRIMARY_CATEGORY_SENIOR_CITIZEN;
		var catCode=document.getElementsByName("patPrimaryCatCode")[0].value;
		var splitCatCode=catCode.split('#')[0];
	//if(document.forms[0].patPrimaryCatCode.value==seniorCitizenCode)
		//if(document.getElementsByName("patPrimaryCatCode")[0].value==seniorCitizenCode)
			if(splitCatCode==seniorCitizenCode && $('[name="seniorCitizenAgeLimit"]')[0].value !="")
				//End
	{
		//alert("validation for senior citizen");
		if(!validateForSeniorCitizen(parseInt(seniorCitizenAge)))	valid=false;
	}
	return valid;
}


function validateAlphaNumericWithSpecialCharacterOnlyforBPLCardNo(e)
{
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	//alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==47) || (key==45) || (key==95))
	   return true;
	
	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/-").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}

function changeSpouseField(obj)
{
	if (obj.value == "1" || obj.value == "4")	
			{
		 	$('[name="patHusbandName"]').val("");
			$('[name="patHusbandName"]').attr("disabled", "disabled");
			}
	else
			{
			$('[name="patHusbandName"]').removeAttr("disabled");
			}	
}

function changeCardNField(obj)
{
	//alert(document.getElementsByName("patCardNo")[0]);
	//alert(document.getElementById("#patCardNo")[0].display);
	 if(obj.value==-1)
{
		 document.getElementById('tdPatCardNoLbl').style.display="none";
		 document.getElementById('tdpatCardNo').style.display="none";
}
	else
		{
		
		document.getElementById('tdPatCardNoLbl').style.display=""; 
		document.getElementById('tdpatCardNo').style.display=""; 
}
 	//document.getElementsByName("patCardNo")[0].display="block";
 	
}

function validateSpouseName()
{
	var valid=true;
	if(document.getElementsByName("patMaritalStatusCode")[0].value==1)
	{	
		if(document.getElementsByName("patHusbandName")[0].value=="")
		{
			alert("For Married,Spouse Name Should not be Blank!");
			document.getElementsByName("patHusbandName")[0].focus();
			valid=false;
		}
	}
	return valid;	
}

function setPatAptDet()
{
	document.getElementsByName("patAptNo")[0].value=document.getElementsByName("patAptNoToRegister")[0].value;
	submitForm('NEWREGWAPT');
}

function submitForm(mode)
{	//alert(document.getElementsByName("patPrimaryCatCode")[0]);
	 document.getElementsByName("hmode")[0].value=mode;
	 /* if (typeof(document.getElementsByName("patPrimaryCatCode")[0]) != "undefined")
		{
	//alert(document.getElementsByName("patPrimaryCatCode")[0].value);
	alert(document.getElementsByName("hdnpatPrimaryCatCode")[0].value);
	document.getElementsByName("hdnpatPrimaryCatCode")[0].value=document.getElementsByName("patPrimaryCatCode")[0].value;
	alert(document.getElementsByName("hdnpatPrimaryCatCode")[0].value);
	
		} */
		/* if (typeof(document.getElementsByName("patPrimaryCatCode")[0]) != "undefined")
		{
	alert(document.getElementsByName("patPrimaryCatCode")[0].value);
		} */
	 document.forms[0].submit();
	/*  alert("in");
		alert(document.getElementsByName("hdnpatPrimaryCatCode"));
	 if (typeof(document.getElementsByName("patPrimaryCatCode")[0]) != "undefined")
		{
		//alert(document.getElementsByName("patPrimaryCatCode")[0].value);
		document.getElementsByName("hdnpatPrimaryCatCode")[0].value=document.getElementsByName("patPrimaryCatCode")[0].value;
		//alert(document.getElementsByName("hdnpatPrimaryCatCode")[0].value);
		} */
	 
}

function getDepartmentName(){

	var el = document.getElementsByName('departmentCode')[0];
	var text = el.options[el.selectedIndex].innerHTML;
	//alert(text);
	document.getElementsByName('department')[0].value=text;

}

function setName()
{
	
	var selOpt = document.getElementsByName('patAddCountryCode')[0];
	document.getElementsByName('patAddCountry')[0].value = selOpt.options[selOpt.selectedIndex].innerHTML;
	
	var selOpt = document.getElementsByName('patAddStateCode')[0];
	document.getElementsByName('patAddState')[0].value = selOpt.options[selOpt.selectedIndex].innerHTML;
	
	var selOpt = document.getElementsByName('patAddDistrictCode')[0];
	document.getElementsByName('patAddDistrict')[0].value = selOpt.options[selOpt.selectedIndex].innerHTML;
	
	return true;
}


function showDuplicatePatientPopup() {

	var windowWidth = $(window).width() - 100;
	var windowHeight = 185;
    var patPrimaryCatCode=document.getElementsByName("patPrimaryCatCode")[0].value ;
    //alert("INside showDuplicatePatientPopup"+patPrimaryCatCode);
	var page="/HISRegistration/registration/transactions/jsp/opdNewRegDuplicatePopup.jsp";

	var $dialog = $('<div></div>').html(
			'<iframe style="border: 0px; " src="' + page
					+ '" width="100%" height="100%"></iframe>').dialog({
		autoOpen : false,modal : true,height : windowHeight,width : windowWidth,
		title : "Patient Detail Exists",	show: { effect: "clip"},resizable: false,
		position: ['top', 100],dialogClass: 'no-close custbtncolor',
		buttons:{
			"Save As New Patient": function() {
				  $(this).dialog("close"); 
				 // NewSplRegwithAptmnt.saveAsNewPatientDtl();
				  submitForm('SAVEASNEWPATIENT');
		},
			"Cancel": function() {
				  $(this).dialog("close"); }
		},
		open: function() {
			 	$('.ui-widget-overlay').addClass('custoverlay');
			 	$('.ui-dialog-buttonpane').find('button:contains("Cancel")').addClass('custbtncolor');
			 	$('.ui-dialog-buttonpane').find('button:contains("Save As New Patient")').addClass('custbtncolor');
		},
		close: function() {
			$(".ui-widget-overlay").removeClass('custoverlay');
	    }   
	});
	$dialog.dialog('open');
}

function checkIsrefer(){
	var flag=false;
	
	if(document.getElementsByName("isReferred")[0].checked){
		//alert("inst value="+document.getElementsByName("referringInstType")[0].value); 
		if(document.getElementsByName("referringInstType")[0].checked){ 
				flag=isSelected(document.forms[0].patRefGnctdHospitalCode,"Referring institution");
			}
		else{
			flag=isEmpty(document.forms[0].isReferred,"Referring institution");
		}
	}
	else{
		flag=true;
	}
	if(flag!=false && typeof(document.getElementsByName('patRefGnctdHospitalCrno')[0]) != "undefined" && document.getElementsByName("isReferred")[0].checked)
	{
	var referCrNo=document.getElementsByName('patRefGnctdHospitalCrno')[0].value;
	var referLen=referCrNo.length;
	if(referLen!=0 && referLen!=12 && referLen!=15)
	{
	alert("Referring Institute CR No. must be 12 or 15 digits only");	
	flag= false;
	}
	}
	return flag; 
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
		
	}
	document.getElementsByName('deptToSearchFrom')[0].value=0;//to reset the value of Department/unit back to All Units
}
function clearTheCriteria()
{
	var allrows = document.getElementsByName("allPatientList");

	for(i=0;i<allrows.length;i++)
		allrows[i].style.display="";
	document.getElementsByName('deptToSearchFrom')[0].value=0;//to reset the value of Department/unit back to All Units
	document.getElementsByName("searchId")[0].value="1";
	document.getElementsByName("searchValue")[0].value="";
}
//End:Mukund

/*Start:  Verhoeff validation */

//multiplication table d
var d = [
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
[1, 2, 3, 4, 0, 6, 7, 8, 9, 5],
[2, 3, 4, 0, 1, 7, 8, 9, 5, 6],
[3, 4, 0, 1, 2, 8, 9, 5, 6, 7],
[4, 0, 1, 2, 3, 9, 5, 6, 7, 8],
[5, 9, 8, 7, 6, 0, 4, 3, 2, 1],
[6, 5, 9, 8, 7, 1, 0, 4, 3, 2],
[7, 6, 5, 9, 8, 2, 1, 0, 4, 3],
[8, 7, 6, 5, 9, 3, 2, 1, 0, 4],
[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
];

//permutation table p
var p = [
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
[1, 5, 7, 6, 2, 8, 3, 0, 9, 4],
[5, 8, 0, 3, 7, 9, 6, 1, 4, 2],
[8, 9, 1, 6, 0, 4, 3, 5, 2, 7],
[9, 4, 5, 3, 1, 2, 6, 8, 7, 0],
[4, 2, 8, 6, 5, 7, 3, 9, 0, 1],
[2, 7, 9, 3, 8, 0, 6, 4, 1, 5],
[7, 0, 4, 6, 9, 1, 3, 2, 5, 8]
];

//inverse table inv
var inv = [0, 4, 3, 2, 1, 5, 6, 7, 8, 9];

//converts string or number to an array and inverts it
function invArray_verhoeff(array) {

if (Object.prototype.toString.call(array) === "[object Number]") {
    array = String(array);
}

if (Object.prototype.toString.call(array) === "[object String]") {
    array = array.split("").map(Number);
}

return array.reverse();

}

//generates checksum
function generate(array) {

var c = 0;
var invertedArray = invArray_verhoeff(array);

for (var i = 0; i < invertedArray.length; i++) {
    c = d[c][p[((i + 1) % 8)][invertedArray[i]]];
}

return inv[c];
}

//validates checksum
function validate_verhoeff(array) {

var c = 0;
var invertedArray = invArray_verhoeff(array);

for (var i = 0; i < invertedArray.length; i++) {
    c = d[c][p[(i % 8)][invertedArray[i]]];
}

return (c === 0);
//return c;
}

//$('[name="patNationalId"]').bind("keyup change", function(e){ onChangeAadhaarField(e);})

function onChangeAadhaarField(e){
	
	var len = $('[name="patNationalId"]').val().length;
	
	if(len == 12)
	{
		var array = $('[name="patNationalId"]')[0].value;
		var ret = validate_verhoeff(array);
		if(!ret)
		{
			alert("Invalid Aadhaar Number!");
			$('[name="patNationalId"]').val("");
			$("#divAadharConsentId").hide();
		}
		else
		{
			$("#divAadharConsentId").show();
			$("#isAadharConsentYes").attr('checked', true);
			
			checkUncheckAadharConsent(document.getElementsByName("isAadharConsent")[0]);
		}	
	     
	}
	else
	{
		$("#divAadharConsentId").hide();
	}
	
}
function checkUncheckAadharConsent(event)
{
	//alert(event.value);
	$('[name="isAadharConsentGiven"]').val(event.value);
	//alert($('[name="isAadharConsentGiven"]').val());
}

/*End: Verhoeff validation  */

//By Mukund on 25.09.2017
 function manageBarcodePrintProcess()
 {
 	saveEntryForBarcodePrinting();
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
			$dialog.dialog('open');  */		
 }
 //End on 25.09.2017
 
 
function validateDivDob()
{ 
	var PRIMARY_CATEGORY_SENIOR_CITIZEN = $('[name="seniorCitizenCatCode"]')[0].value;
	
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

	}
	return true;
}

function isValid(obj,name,minVal){
// alert("isValid: "+obj.name);
  	if(obj!=null){
		var val	=	new String(obj.value);
		if(document.getElementsByName('isActualDob')[0].checked){
		if(val=="" || val>minVal){
			alert("Please Enter valid "+ name +"(<125)");
			obj.value=val;
			obj.focus();
			return false;
		}else{
			if(val=="0"){
				alert("Please Enter Valid"+name+"{>0}");
				obj.value=val;
				obj.focus();
				return false;
			}
		}
      }
     else
		obj.value=val;
	return true;
	}
	return false;

}
</script>
	<script>
	$(function(){
		
		//$("[name='patPrimaryCatCode'] option[value='11']").attr("selected", "selected");
		//$('#gouv option[value="' + ident + '"]').attr("selected", "selected");
		//alert($("[name='patPrimaryCatCode']").find('option:selected').val());
		/* var len = $("[name='patPrimaryCatCode']").length;
		for(i=0;i<len;i++){
			alert("i: "+$("[name='patPrimaryCatCode']")[i].value);
		} */
		/* $("[name='patPrimaryCatCode'] option").each(function(){
			alert($("[name='patPrimaryCatCode']").val());
			}); 
			$("select[name='patPrimaryCatCode']").prop('selectedIndex', 2);
		*/
		if(!$("[name='patPrimaryCatCode'] option:contains('General')")){
			$("[name='patPrimaryCatCode']").val('-1');
		}else{
			$("[name='patPrimaryCatCode'] option:contains('General')").attr('selected', 'selected');
		}	
		
		});
		
	
var oldVal='';
$(document).ready(function(){
	$("select[name='paymentModeCode']").on('focus', function(){
		oldVal=$(this).val();
	});
});
function paymentModeCodeOnChangeValidate(e){ 
	 var val=$(e).val();  
	
	  
	 if(val.split('#')[1]=='1')
		{ 	if(val.split('#')[2]==$("select[name='patPrimaryCatCode']").val())
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

 <!--By Surabhi for SNOMED CT word base  -->
<!-- <link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/searchtool.css"> -->
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/searchtoolnew.css">
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/jquery-ui.css">
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>

<script type="text/javascript">

function tog(v) { return v ? 'addClass' : 'removeClass'; }
$(document).on('mouseover', '.clearable', function () {
    $(this)[tog(this.value)]('x');
}).on('mousemove', '.x', function (e) {
    $(this)[tog(this.offsetWidth - 18 < e.clientX - this.getBoundingClientRect().left)]('onX');
}).on('touchstart click', '.onX', function (ev) {
    ev.preventDefault();
    $(this).removeClass('x onX').val('').change();
    var id = this.id;
    var elmt=id.split('_');
    var elmtid=elmt[1];
    clear(elmtid);
    document.getElementById("txt-snomed-ct-search_"+elmtid).value = "";
});

function clear(id)
{
	//var elmtid=id.split('_');
	var trgt=id;
	var targetPrefferedTerm="";  
	 var targetConceptId="";
	 //document.getElementById(id).value="";
	 if(trgt=="1")
	{
		 targetPrefferedTerm=document.getElementsByName("snomdPTVisitReason")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdVisitReason")[0];   //concept Id
		 document.getElementsByName("patVisitReason")[0].value="";
	}
		targetPrefferedTerm.value = "";
		targetConceptId.value = "";
}

function load1(elmtId,semantictag){
	if(elmtId==null || elmtId==undefined){
		elmtId="1"; semantictag="DISORDER";
	}else if(elmtId=="1"){
	 id="patVisitReason";
	}
	setTarget(id);
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};

	var callbck_index = function(ret_OUT){setValue(ret_OUT);};

	//selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
	if(<%=RegistrationConfig.SNOMED_SERVICE_ON%> == "1")//document.getElementsByName("isSnomedServiceOn")[0].value){
		selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
	}else{
		selectSNOMEDCTmulti('','','','-1','null','','');
		}
	
	//alert(document.getElementsByName("patVisitReason")[0].value);
	if(document.getElementsByName("patVisitReason")[0].value=="")
		{
		var s=document.getElementById("txt-snomed-ct-search_1").value;
		document.getElementsByName("patVisitReason")[0].value=s;
		
		}
	//alert(document.getElementsByName("patVisitReason")[0].value);
	//end
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	
}

window.onload = function(){
	alert("insideLoad1");
	load1('1','');
	alert("insideLoad2");
}
function setTarget(id)
{
	if(id=="patVisitReason")
	{
		document.getElementsByName("targetId")[0].value="patVisitReason";
	}	
	
}

function setValue(selectedSNOMEDTerm)
{	
	//alert("set value= "+selectedSNOMEDTerm.term);
	var target=document.getElementsByName("targetId")[0].value;
		
	var targetPrefferedTerm="";
	var targetConceptId="";
	
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
	{
		
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
	
		if(target == "patVisitReason") // Visit Reason
		{
			//alert("inside patVisitReason of setValue()");
			 
			 targetPrefferedTerm=document.getElementsByName("snomdPTVisitReason")[0];  //preffered term
			 targetConceptId=document.getElementsByName("snomdCIdVisitReason")[0];   //concept Id
		
			 if(document.getElementsByName("patVisitReason")[0].value=="") document.getElementsByName("patVisitReason")[0].value = str;
			else document.getElementsByName("patVisitReason")[0].value = document.getElementsByName("patVisitReason")[0].value + ", " + str;
			
		}
	 
		if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
		else targetPrefferedTerm.value = targetPrefferedTerm.value + "#" + str;
		
		if(targetConceptId.value=="")  targetConceptId.value = str1;
		else targetConceptId.value = targetConceptId.value + "#" + str1;
		
	}
}
//Added by Surabhi on 1st Feb'17 for free text if snomed field is disabled  

function freeText()
{
	if(document.getElementsByName("patVisitReason")[0].value=="")
	{
	var s=document.getElementById("txt-snomed-ct-search_1").value;
	document.getElementsByName("patVisitReason")[0].value=s;
	
	}
}
//end
</script>
 <!--End: Surabhi  -->
</head>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
<body>

	<input type='hidden' name='crNoToRetrieve' />
	<div id="divTitle">
		<his:TitleTag name="Special Clinic Registration with Appointment">

		</his:TitleTag>
	</div>

	<div id="noprint">
		<his:statusList>
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<%--By Mukund to get the to search dropdown for dept/unit --%>
					<logic:notEmpty name="<%=RegistrationConfig.PATIENT_VO_LIST%>">
						<tr>
							<div class="div-table-col label" style="width: 15%">
								<bean:message key="department" />
								/
								<bean:message key="unit" />
							</div>
							<div class="div-table-col width control" style="width: 40%">
								<html:select name="newSplRegFB" property="deptToSearchFrom"
									tabindex="1" styleClass="regCbo" style="width:200%;"
									onchange="setPatientList(this);">
									<html:option value="0">All Units</html:option>
									<html:options collection="deptToSearchFrom" property="value"
										labelProperty="label" />
								</html:select>
							</div>
							<div class="div-table-col width control" align="right" style="">
								<html:select name="newSplRegFB" property='searchId' tabindex="1"
									styleClass="regCbo" style="width:100%;">
									<html:option value='1'>Appointment No</html:option>
									<html:option value='2'>Name</html:option>
									<html:option value='3'>Contact No</html:option>
								</html:select>
							</div>
							<div class="div-table-col">
								<html:text name="newSplRegFB" styleClass="regCbo"
									property="searchValue" maxlength="16" size="20" tabindex="1"></html:text>
							</div>
							<div class="div-table-col">
								<a href="#" class="button" id="searchId"><span class="save"
									onclick="searchValueValidation();"><bean:message
											key="search" /></span></a>
								<!--once submitForm('SEARCH') is called to get the result for the search under the specific criteria-->
							</div>
							<div class="div-table-col">
								<a tabindex="1" href="#" class="button" id="clearId"
									onclick="clearTheCriteria();"><span class="clear"><bean:message
											key="clear" /></span></a>
								<!--once submitForm('NEW'); is called to clear the page -->
							</div>
						</tr>
					</logic:notEmpty>
					<!-- End: Mukund -->
					<tr>
						<td width="3%" align="center" class="tdfonthead"
							style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<b><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"></font>
							</div>
						</td>
						<td width="18%" align="center" class="tdfonthead"
							style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<b><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><bean:message
											key="Apt_appointment" />&nbsp;<bean:message key="number" /></font>
							</div>
						</td>
						<td width="20%" align="center" class="tdfonthead"
							style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<b><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><bean:message
											key="name" /></font>
							</div>
						</td>
						<td width="10%" align="center" class="tdfonthead"
							style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<b><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><bean:message
											key="age" /></font>
							</div>
						</td>
						<td width="13%" align="center" class="tdfonthead"
							style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<b><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><bean:message
											key="contactNo" /></font>
							</div>
						</td>
						<td width="10%" align="center" class="tdfonthead"
							style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<b><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><bean:message
											key="Apt_slot" /></font>
							</div>
						</td>
						<td width="20%" align="center" class="tdfonthead"
							style="border-top: outset black 2px; border-bottom: inset black 2px;">
							<div align="center">
								<b><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"><bean:message
											key="department" />/<bean:message key="unit" /></font>
							</div>
						</td>
					</tr>
					<logic:notEmpty name="<%=RegistrationConfig.PATIENT_VO_LIST%>">

						<logic:iterate id="PATIENT_VO"
							name="<%=RegistrationConfig.PATIENT_VO_LIST%>">

							<tr name="allPatientList"
								id="<bean:write name="PATIENT_VO" property="departmentCode"/>@<bean:write name="PATIENT_VO" property="patAptNo"/>@<bean:write name="PATIENT_VO" property="patFirstName"/>&<bean:write name="PATIENT_VO" property="patMiddleName"/>&<bean:write name="PATIENT_VO" property="patLastName"/>@<bean:write name="PATIENT_VO" property="patAddContactNo"/>">
								<td width="3%"><input type="radio"
									name="patAptNoToRegister" onclick="setPatAptDet();"
									value='<bean:write name="PATIENT_VO" property="patAptNo" />' /></td>
								<td width="18%"><div align="center">
										<font size="2"> <bean:write name="PATIENT_VO"
												property="patAptNo" /></font>
									</div></td>
								<td width="20%"><div align="center">
										<font size="2"> <bean:write name="PATIENT_VO"
												property="patFirstName" /> <bean:write name="PATIENT_VO"
												property="patMiddleName" /> <bean:write name="PATIENT_VO"
												property="patLastName" /></font>
									</div></td>
								<td width="10%"><div align="center">
										<font size="2"> <bean:write name="PATIENT_VO"
												property="patAge" /></font>
									</div></td>
								<td width="13%"><div align="center">
										<font size="2"> <bean:write name="PATIENT_VO"
												property="patAddContactNo" /></font>
									</div></td>
								<td width="10%"><div align="center">
										<font size="2"> <bean:write name="PATIENT_VO"
												property="patAptSlot" /></font>
									</div></td>
								<td width="20%"><div align="center">
										<font size="2"> <bean:write name="PATIENT_VO"
												property="department" />/<bean:write name="PATIENT_VO"
												property="unit" /></font>
									</div></td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
				</table>
			</his:ContentTag>
			<!--Commented by Mukund on 03.10.2016  searching criteria is shifted to the top-->
			<%-- <table width="100%" style=" background-color:#086fa6;">
		 <tr>
		 <td width="50%"></td>
		 <td width="25%">
		 <div align="right"><html:select name="newSplRegFB" property='searchId' style="width:139px;">
			<html:option value='1'>Appointment No</html:option>
			<html:option value='2'>Name</html:option>
			<html:option value='3'>Contact No</html:option>	
			<html:option value='4'>Email Id</html:option>
			</html:select></div></td>
		 <td width="15%">
		 <div align="left"><html:text name="newSplRegFB" 
						styleClass="textbox" property="searchValue" maxlength="16" size="20"
						tabindex="1" ></html:text></div></td>
		 
		 
		 
		 <td width="5%"><div align="left"><a  href="#" class="button" id="searchId"><span class="save" onclick="submitForm('SEARCH');"><bean:message key="search"/></span></a></div></td>
		 <td width="5%"><div align="left"> <a  tabindex="1" href="#" class="button" id="clearId" onclick="submitForm('NEW');"><span class="clear"><bean:message key="clear"/></span></a></div></td>						
		
		 </tr> 
		 
		</table>--%>
			<his:ButtonToolBarTag>
				<table class="TABLEWIDTH" align="center" cellspacing="1px">
					<tr>
						<td align="center">
							<div align="center">

								<a tabindex="1" href="#" class="button" id="cancelId"
									onclick="window.parent.closeTab('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span
									class="cancel"><bean:message key="cancel" /></span></a> <img
									class="button" src='/HIS/hisglobal/images/buttons/btn-clr.png'
									tabindex="1" style="cursor: pointer; display: none"
									onclick="submitForm('NEW')"
									onkeypress="if(event.keyCode==13) submitForm('NEW');">
							</div>
						</td>
					</tr>
				</table>
			</his:ButtonToolBarTag>
		</his:statusList>

		<his:statusTransactionInProcess>
			<div class="errMsg" id="errMsg"></div>
			<div class="warningMsg" id="warningMsg"></div>
			<div class="normalMsg" id="normalMsg"></div>

			<%
				String systemDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT),"dd-MMM-yyyy HH:mm");
												boolean flag = true;
												//Collection dept = (List) session
												//		.getAttribute(RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT);
												Collection dept = (List) session
												.getAttribute(RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT);
												Iterator itr = dept.iterator();
												System.out.println("------In JSP-------"+dept+"-----------------");
												newSplRegFB fb = (newSplRegFB) pageContext
												.findAttribute("newSplRegFB");

												if (!itr.hasNext() && fb.getDepartmentsToVisitStamp().length == 0) {
											flag = false;
												}
			%>
			<%
				if (flag == true) {
			%>

			<his:statusTransactionInProcess>
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						bgcolor="#EBEBEB">
						<tr>
							<td width="20%" class="LABEL"><font color="#FF0000" size="1"
								face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
									key="visitingDeptUnit" />&nbsp;</td>

							<td width="40%" class="tdfont"><bean:define
									id="deptCollection" type="java.util.List"
									name="<%=RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT%>"></bean:define>
								<html:select name="newSplRegFB" property="departmentCode"
									tabindex="1" styleClass="regCbo" style="width:200%;">
									<%
										if (deptCollection.size() > 1) {
									%>
									<html:option value="-1">Select Value</html:option>
									<%
										}
									%>
									<html:options
										collection="<%=RegistrationConfig.REGISTRATIONDESK_OPTION_DEPARTMENT%>"
										property="value" labelProperty="label" />
								</html:select></td>
						</tr>
					</table>
				</his:ContentTag>
				<his:SubTitleTag name="Patient Details">

					<td class="TitleTagFontStyle" width="10%" style=""><bean:message
							key="mandatory1" /> <bean:message key="patient" /> <bean:message
							key="category" />&nbsp;</td>
					<td width="15%"><font color="#000000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif"> <html:select
								name="newSplRegFB" property="patPrimaryCatCode" tabindex="1"
								styleClass="regcbo" onchange="sendData();">
								<html:option value="-1">Select Value</html:option>
								<html:options
									collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>"
									property="value" labelProperty="label" />
							</html:select>
					</font> <input type="hidden" name="hdnpatPrimaryCatCode" /></td>
					<div class="div-table-col title width25 " style="display: none">
						<a id="cashCollection" style="cursor: pointer; color: white"
							onclick="openDependentPopup('/HISRegistration/registration/specialClinicNewRegWithAppointment.cnt?hmode=CASHCOLLECTIONPOPUP',event,250,600);"
							title="Cash Collection Detail"><font color="white" style=""><u>Total
									Cash</u></font></a>

						<!-- start warish for show fee label -->
						<td style="width: 35%;">
							<div id="divFeeLabel" class="div-table-col label-yellow"
								style="display: none;">
								<font color="red">*</font>
								<bean:message key="registrationFee" />
							</div>
							<div id="divFeeVal" class="div-table-col control"
								style="display: none;">
								<img id="INRId" align="left"
									src="/HISRegistration/hisglobal/images/INR.png" />&nbsp;&nbsp;&nbsp;
								<div id="yellowAmount" class="div-table-col label-yellow"
									class="input100prcnt"></div>
							</div>
							<div class="div-table-col control" id="divFeeVal2"
								style="display: none;">
								<html:select name="newSplRegFB" property='paymentModeCode'
									styleClass="regcbo" tabindex="1"
									onchange='return paymentModeCodeOnChangeValidate(this)'>

									<html:options
										collection="<%=RegistrationConfig.PAYMENT_MODE_OPTION_LIST%>"
										property="value" labelProperty="label" />
								</html:select>
							</div>
							<div class="div-table-col control" id="divFeeVal3"
								style="display: none;">
								<input tabindex="1" name="paymentModeRefId" type="text" maxlength=20/>
							</div>
						</td>
						<!-- End:warish-->
					</div>

				</his:SubTitleTag>

				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr style="display: none;">
							<td width="100%" colspan="6">
								<table width="100%" border="0" cellspacing="1" cellpadding="0">
									<tr>
										<td width="17%" style="" class="LABEL"><bean:message
												key="mandatory1" /> <bean:message key="patient" /> <bean:message
												key="category" />&nbsp;</td>
										<td width="19%" class="tdfont"><font color="#000000"
											size="1" face="Verdana, Arial, Helvetica, sans-serif">
												<html:select name="newSplRegFB" property="patPrimaryCatCode"
													tabindex="1" styleClass="regcbo"
													onchange="if(this.value!='-1') sendData()"
													style="width:60%;">
													<html:option value="-1">Select Value</html:option>
													<html:options
														collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>"
														property="value" labelProperty="label" />
												</html:select>
										</font> <input type="hidden" name="hdnpatPrimaryCatCode" /></td>

										<td class="LABEL" width="15%">
											<div id="tdPatIdNoLabel" style="display: none;">
												Id&nbsp;</div>
										</td>
										<td width="15%" height="25" class="tdfont">
											<div id="tdPatIdNo" style="display: none;">
												<input name="shownPatIdNo" id="shownPatIdNoId" width="50%"
													tabindex="1" name="patIdNo" type="text"
													class="input75prcnt" maxlength="16"
													onkeypress="return validateAlphaNumericOnly(event,this);" />
											</div>
										</td>

										<td class="LABEL" width="17%">
											<div id="divempIdLabel" style="display: none;">
												<font color="#000000" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
														key="getEmploeeDetailWithDependent" /></font>
											</div>
											<div id="divIdTextLabel" style="display: none;">
												<font color="#000000" size="2"
													face="Verdana, Arial, Helvetica, sans-serif"> <font
													color="#FF0000" size="1"
													face="Verdana, Arial, Helvetica, sans-serif">*&nbsp;</font>
										</td>
										<td class="tdfont" width="17%" nowrap>
											<div id="divempIdControl" style="display: none;">
												<img class="button"
													src="../hisglobal/images/ico_myfriends.gif" tabindex="1"
													border="0" style="cursor: pointer"
													onkeypress="if(event.keyCode==13) showEmployeepopup(event);"
													onclick="showEmployeepopup(event);">
											</div>
											<div id="divIdTextBox" style="display: none;">
												<html:text name="newSplRegFB"
													onkeypress="return validateAlphaNumericOnly(event,this);"
													styleClass="textbox" property="patCatIdNo" maxlength="16"
													tabindex="1"></html:text>
											</div>
										</td>
									</tr>
								</table>
							</td>

						</tr>
						<!-- Start Sheeldarshi -->
						<tr>
							<td width="100%" colspan="6">
								<div id="divCatGroupTestId" style="display: none" width="100%">
									<table width="100%" border="0" cellspacing="1" cellpadding="0">
										<tr>
											<td width="17%" style="" class="LABEL"><font color="red">*</font>
												<bean:message key="reference" />&nbsp;<bean:message
													key="letter" />&nbsp;<bean:message key="number" />&nbsp;</td>
											<td width="17%" height="25" class="tdfont"><input
												type="text" name="creditLetterRefNo"
												id="creditLetterRefNoId" style="width: 65%;" maxlength="50"
												tabindex="1"></td>
											<!--By Mukund on 25.07.2016  -->
											<td class="LABEL" width="17%"><font color="red">*</font>Letter
												Type&nbsp;</td>
											<td width="17%" height="25" class="tdfont"><html:select
													name="newSplRegFB" property='letterType' style="width:65%;"
													styleClass='regcbo'>
													<html:option value='-1'>Select Value</html:option>
													<html:options
														collection="<%=RegistrationConfig.ESSENTIALBO_LETTER_TYPE_OPTION_LIST%>"
														property="value" labelProperty="label" />
												</html:select></td>
											<!--End:Mukund  -->
											<td width="17%" style="" class="LABEL"><font color="red">*</font>
												<bean:message key="letter" />&nbsp;<bean:message key="date" />&nbsp;</td>
											<td width="17%" height="25" class="tdfont"><input
												type="text" name="creditLetterDate" id="creditLetterDateId"
												readonly="readonly" style="width: 75%;" tabindex="1">
											</td>
											<td class="LABEL" width="17%"></td>
											<td width="17%" height="25" class="tdfont">
										</tr>
										<tr>
											<td width="17%" style="" class="LABEL"><font color="red">*</font>
												<bean:message key="company" />&nbsp;</td>

											<td width="17%" height="25">
												<!-- <select name="clientCode"  tabindex="2" id="clientCodeId"  onchange="setCompany();">
							<option value="-1">Select Value</option>
						</select> --> <html:select name="newSplRegFB"
													property="clientCode" style="width:65%;" tabindex="1"
													styleClass="regcbo" onchange="setCompany()">
													<html:option value="-1">Select Value</html:option>
													<html:options
														collection="<%=RegistrationConfig.ESSENTIALBO_CLIENT_OPTION_LIST%>"
														property="value" labelProperty="label" />
												</html:select>
											</td>
											<td width="1%" id="clientNameLabel" class="tdfont"
												style="display: none"></td>

											<td class="LABEL" width="17%"><font color="#FF0000"
												size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
												<bean:message key="staff" />&nbsp;<bean:message
													key="number" />&nbsp;</td>

											<td width="17%" height="25" class="tdfont"><input
												type="text" name="staffCardNo" id="staffCardNoId"
												style="width: 65%;" maxlength="50" tabindex="1"></td>
											<td class="LABEL" width="17%"><bean:message key="staff" />&nbsp;<bean:message
													key="global.name" />&nbsp;</td>
											<td width="17%" height="25" class="tdfont"><input
												type="text" name="staffCardName" id="staffCardNameId"
												style="width: 75%;" maxlength="100"
												onkeypress="return validateAlphabetsOnly(event,this);">
											</td>
										</tr>
										<tr>
											<td class="LABEL" width="17%"><bean:message
													key="relation" />&nbsp;<bean:message key="of" />&nbsp;<bean:message
													key="global.patient" />&nbsp;</td>
											<td width="17%" height="25" class="tdfont"><html:select
													name="newSplRegFB" property='relationWithStaff'
													style="width:65%;" styleClass='regcbo'
													onchange="setRelation(this)">
													<html:option value='-1'>Select Value</html:option>
													<html:options
														collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL%>"
														property="value" labelProperty="label" />
												</html:select></td>
											<td class="LABEL" width="14%"><bean:message
													key="validfor" />&nbsp;</td>

											<td width="17%" height="25" class="tdfont"><input
												type="text" name="cardvalidityDate" style="width: 65%;"
												id="cardvalidityDateId" readonly="readonly"></td>
											<!--By Mukund on 25.07.2016  -->
											<td class="LABEL" width="17%">Credit Limit&nbsp;</td>
											<td width="17%" class="tdfont" height="25"><html:text
													name="newSplRegFB" property="creditLimit"
													styleClass="textbox" style="width:75%;" maxlength="11"
													onkeypress="return validateNumericOnly(this,event)" /></td>
										</tr>

									</table>
								</div>
							</td>
						</tr>


						<tr>
							<td width="100%" colspan="6">
								<div id="divCatGroupArogyaShreeBeneficiaryId"
									style="display: none" width="100%">
									<table width="100%" border="0" cellspacing="1" cellpadding="0">
										<!-- <tr id="divCatGroupArogyaShreeBeneficiaryId" style="display:none"> -->
										<tr>
											<td class="LABEL" width="17%"><bean:message
													key="district" />&nbsp;</td>
											<td width="17%" height="25" class="tdfont"><html:select
													name="newSplRegFB" tabindex="2" style="width:65%;"
													styleClass="regcbo" property="agsDistrictCode">
													<html:option value="-1">Select Value</html:option>
													<html:options
														collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>"
														property="value" labelProperty="label" />
												</html:select></td>
											<td class="LABEL" width="17%"><font color="#FF0000"
												size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
												<bean:message key="arogyasri" />&nbsp;<bean:message
													key="number" />&nbsp;</td>
											<td width="17%" height="25" class="tdfont"><input
												type="text" name="agsNo" id="agsNoId" style="width: 65%;"
												maxlength="15" tabindex="1"
												onkeypress="return validateAlphaNumericOnly(event,this);">
											</td>
											<td class="LABEL" width="17%"><bean:message
													key="arogyamitra" />&nbsp;<bean:message
													key="global.counter" />&nbsp;<bean:message key="number" />&nbsp;
											</td>
											<td width="17%" height="25" class="tdfont"><input
												type="text" name="agsCounterNo" style="width: 75%;"
												id="agsCounterNoId" maxlength="3"></td>
										</tr>
										<!--By Mukund on 25.07.2016  -->
										<tr>
											<td class="LABEL" width="17%">Credit Limit&nbsp;</td>
											<td width="17%" class="tdfont" height="25"><html:text
													name="newSplRegFB" property="agsCreditLimit"
													style="width:65%;" styleClass="textbox" maxlength="11"
													onkeypress="return validateNumericOnly(this,event)" /></td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<!-- End Sheeldarshi -->

						<tr style="display: none;" id="bplFamilyTrId">
							<td width="100%" colspan="6">
								<div id="bplFamilyDivId">
									<table width='100%' cellspacing='1px' cellpadding='0'>
										<tr>
											<td width='17%' style="" class='tdfonthead'>
												<div align='right'>
													<font color='#FF0000' size='1'
														face='Verdana, Arial, Helvetica, sans-serif'>*</font> <font
														color='#000000' size='2'
														face='Verdana, Arial, Helvetica, sans-serif'> <bean:message
															key="patCardNo" />&nbsp;
													</font>
												</div>
											</td>
											<td width='17%' class='tdfont'><font color='#000000'
												size='1' face='Verdana, Arial, Helvetica, sans-serif'>
													<input type='text' name='patBPLCardNo' value=''
													style="width: 65%;" tabindex="1" class='textbox'
													maxlength='10' onblur="openBPLPopup(event);"
													onkeypress="return validateAlphaNumericWithSpecialCharacterOnlyforBPLCardNo(event,this)">
											</font></td>
											<td width='18%' nowrap class='tdfonthead'><div
													align='left'>
													<input type='button' name='BPL Search' value='BPL Search'
														onclick='openBPLPopup(event);'>
												</div></td>
											<td width='18%' nowrap class='tdfonthead'></td>
											<td width='19%' class='tdfont'></td>
											<td width='17%' nowrap class='tdfonthead'><div
													align='right'></div></td>
											<td width='19%' class='tdfont'></td>
										</tr>
										<tr>
											<td width='17%' nowrap class='tdfonthead'>
												<div align='right'>
													<font color='#000000' size='2'
														face='Verdana, Arial, Helvetica, sans-serif'> <bean:message
															key="familyHeadName" />&nbsp;
													</font>
												</div>
											</td>
											<td width='19%' class='tdfont'><font color='#000000'
												size='1' face='Verdana, Arial, Helvetica, sans-serif'>
													<input type='text' class='textbox' name='familyHeadName'
													value='' onclick='' maxlength='80'
													onkeypress="return validateAlphabetsOnly(event,this)">
											</font></td>
											<td width='17%' nowrap class='tdfonthead'>
												<div align='right'>
													<font color='#000000' size='2'
														face='Verdana, Arial, Helvetica, sans-serif'> <bean:message
															key="familyHeadRelation" />&nbsp;
													</font>
												</div>
											</td>
											<td width='19%' class='tdfont'><html:select
													name="newSplRegFB" property='bplRelationCode'
													styleClass='regcbo'>
													<html:option value='-1'>Select Value</html:option>
													<html:options
														collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL%>"
														property="value" labelProperty="label" />
												</html:select></td>
											<td width='17%' nowrap class='tdfonthead'><div
													align='right'>
													<font color='#000000' size='2'
														face='Verdana, Arial, Helvetica, sans-serif'><bean:message
															key="stateIdNo" />&nbsp;</font>
												</div></td>
											<td width='20%' class='tdfont'><input type='text'
												class='textbox' name='bplStateId' value='' onclick=''
												maxlength='10'></td>
										</tr>
										<tr>
											<td width='17%' nowrap class='tdfonthead'>
												<div align='right'>
													<font color='#000000' size='2'
														face='Verdana, Arial, Helvetica, sans-serif'> <bean:message
															key="mmjrkNo" />&nbsp;
													</font>
												</div>
											</td>
											<td width='19%' class='tdfont'><font color='#000000'
												size='1' face='Verdana, Arial, Helvetica, sans-serif'>
													<input type='text' name='bplMMJRKNo' value=''
													class='textbox' onclick='' maxlength='10'>
											</font></td>
											<td width='18%' nowrap class='tdfonthead'><div
													align='right'>
													<font color='#000000' size='2'
														face='Verdana, Arial, Helvetica, sans-serif'><bean:message
															key="bplFamilyId" />&nbsp;</font>
												</div></td>
											<td width='19%' class='tdfont'><input type='text'
												class='textbox' name='bplFamilyId' value='' onclick=''
												maxlength='10'></td>
											<td width='17%' nowrap class='tdfonthead'><div
													align='right'></div></td>
											<td width='19%' class='tdfont'></td>
										</tr>
									</table>
								</div>
							</td>
						</tr>

						<tr>
							<td class="LABEL" width="17%"><bean:message key="adhar" />&nbsp;
							</td>
							<td width="17%" class="tdfont"><html:text name="newSplRegFB"
									property="patNationalId" styleClass="textbox" maxlength="12"
									tabindex="2" onkeypress="return validateNumeric(event)"
									onchange="onChangeAadhaarField(this)" style="width:65%;" /></td>
							<td width="14%" class="LABEL"><bean:message key="otherId" />&nbsp;</td>
							<td width="17%" class="tdfont"><html:select
									name="newSplRegFB" property="patDocType" style="width:65%;"
									tabindex="2" styleClass="regcbo"
									onchange="changeCardNField(this)">
									<html:option value="-1">Select Value</html:option>
									<html:options
										collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS%>"
										property="value" labelProperty="label" />
								</html:select></td>
							<td id="tdPatCardNoLbl" class="LABEL" style="display: none;"><bean:message
									key="patCardNo" />&nbsp;</td>
							<td id="tdpatCardNo" class="tdfont" style="display: none"><html:text
									name="newSplRegFB" property="patCardNo" maxlength="11"
									style="width:75%;" styleClass="textbox" /></td>

						</tr>

						<!--By Mukund on 23.Mar.18  -->
						<tr id="divAadharConsentId" style="display: none">
							<td colspan=5 style='width: 100%' class="LABEL">
								<p style="padding-right: 25%">
									I, the holder of above Aadhaar Number, hereby give my consent
									to obtain my Aadhaar No and other demographic details for
									Authentication. I Agree<font color="red">*</font> <input
										name="isAadharConsent" id="isAadharConsentYes" value="1"
										onclick="checkUncheckAadharConsent(this)" type="radio">Yes
									<input name="isAadharConsent" id="isAadharConsentNo" value="0"
										onclick="checkUncheckAadharConsent(this)" type="radio">No
								</p>
							</td>
						</tr>
						<!--End on 23.03.18  -->

						<tr>
							<td width="17%" height="25" nowrap class="LABEL"><bean:message
									key="patient" /> <bean:message key="name" />&nbsp;<bean:message
									key="mandatory1" /> (<bean:message key="first" />) &nbsp;</td>
							<td width="17%" class="tdfont"><html:text name="newSplRegFB"
									property="patFirstName" styleClass="textbox" tabindex="1"
									maxlength="30" onchange="isAlpha(this,'First Name')"
									style="width:65%;"
									onkeypress="return validateAlphabetsWithDotsAndForwardSlashWithHypen(event,this)"
									onkeyup="multilingualConversion(this,document.getElementById('patFirstNameInMultiLangId'));" /></td>
							<td width="14%" class="LABEL">(<bean:message key="middle" />)&nbsp;
							</td>
							<td width="17%" class="tdfont" align="left"><html:text
									name="newSplRegFB" property="patMiddleName" tabindex="2"
									styleClass="textbox" onchange="isAlpha(this,'Middle Name')"
									maxlength="20"
									onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
									onkeyup="multilingualConversion(this,document.getElementById('patMiddleNameInMultiLangId'));"
									style="display:block;width:65%;" /></td>

							<td width="14%" class="LABEL">(<bean:message key="last" />)&nbsp;
							</td>
							<td width="19%" class="tdfont"><html:text name="newSplRegFB"
									property="patLastName" styleClass="textbox" maxlength="30"
									onchange="isAlpha(this,'Last Name')" style="width:75%;"
									onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
									onkeyup="multilingualConversion(this,document.getElementById('patLastNameInMultiLangId'));" /></td>
							<%-- <td width="17%" class="LABEL">
					<div id="relationDiv" style="display: none;"><font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="relation" />&nbsp; </font></div>
					</td>
					<td width="17%" class="tdfont">
					<div id="relationComboDiv" style="display: none;"></div>
					</td> --%>
						</tr>

						<!-- Start:Sheeldarshi -->
						<tr style="display: none;">
							<td width="17%" class="LABEL">&nbsp;</td>
							<td width="20%" class="tdfont"><input
								name="patFirstNameInMultiLang" id="patFirstNameInMultiLangId"
								tabindex="1" maxlength="33" type="text" onblur="callOnBlur();"
								onfocus="callOnClick();"></td>
							<td width="15%" class="tdfont" align="left"><input
								name="patMiddleNameInMultiLang" id="patMiddleNameInMultiLangId"
								tabindex="2" maxlength="33" type="text" onblur="callOnBlur();"
								onfocus="callOnClick();"></td>
							<td width="18%" class="tdfont"><input
								name="patLastNameInMultiLang" id="patLastNameInMultiLangId"
								tabindex="2" maxlength="33" type="text" onblur="callOnBlur();"
								onfocus="callOnClick();"></td>
							<td width="17%" class="LABEL"></td>
							<td class="tdfont" width="17%"></td>
						</tr>
						<!-- End:Sheeldarshi -->
						<tr>
							<td width="17%" nowrap class="LABEL"><bean:message
									key="mandatory1" /> <bean:message key="age" />&nbsp;<html:radio
									name="newSplRegFB" property="isActualDob" tabindex="2"
									value="0" onclick="ageSelection()" /> <%-- <bean:message
						key="dob" /> --%>Dob<html:radio name="newSplRegFB"
									property="isActualDob" tabindex="2" value="1"
									onclick="ageSelection()" />&nbsp;</td>
							<td width="17%" class="tdfont" nowrap="nowrap">
								<div id="divAge">
									<html:text name="newSplRegFB" style="width:29%;" size="5"
										property="patAge" tabindex="1" maxlength="5"
										onkeypress="return validateNumeric(event)"
										onchange="ageWiseCheck();" />
									<html:select name="newSplRegFB" property="patAgeUnit"
										onchange="ageWiseCheck();" tabindex="1"
										styleClass="smallcombo">
										<html:options
											collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE%>"
											property="value" labelProperty="label" />
									</html:select>
								</div>

								<div id="divDob" style="display: none;">
									<!-- <input id="patDOBId" tabindex="1" type="text" name="patDOB" style="width:40%;">&nbsp; -->
									<span class="div-table-col control" style="width: 100%;">
										<div id="divNewDOB">
											<input id="patDOBId_Dup" tabindex="1" type="text"
												name="patDOB_Dup" class="input45prcnt" />
										</div>
										<div id="divNewDOBHidden">
											<input type='hidden' name='patDOB' id='patDOBId' />
										</div>
									</span>
									<script type="text/javascript">
						/* $("#patDOBId").datepicker({dateFormat: 'dd-M-yy',
							onSelect: function(d,i){
						          if(d !== i.lastVal){
						              $(this).change();
						          }
						     }}).datepicker("setDate", new Date());	 */
					     
					</script>
								</div>

								<div class="div-table-row">
									<!-- 	<div class="div-table-col control" style="width: 100%;">
	   <input id="patDOBId" tabindex="1" type="text" name="patDOB" onblur="setMotherValidRule();" class="input50prcnt">&nbsp;
	</div> -->

								</div> <%-- <div id="divDob" style="display: none;"><bean:define
						name="newSplRegFB" property="patDOB" id="dob"
						type="java.lang.String" />
					<div id="divPatDOB">
					 <input id="" tabindex="1" type="text" name="patDOB" class="input50prcnt">&nbsp;
					</div>
					
					</div> --%>
							</td>
							<!-- Start:Sheeldarshi -->
							<!-- <td width="14%" class="LABEL" nowrap="nowrap"></td>
					<td width="17%" class="tdfont" nowrap="nowrap"></td> -->
							<td width="14%" class="LABEL" nowrap="nowrap"><bean:message
									key="mandatory1" /> <bean:message key="gender" />&nbsp;</td>
							<td width="17%" class="tdfont" nowrap="nowrap"><html:select
									name="newSplRegFB" property="patGenderCode" style="width:65%;"
									tabindex="1" styleClass="regcbo">
									<html:option value="-1">Select Value</html:option>
									<html:options
										collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER%>"
										property="value" labelProperty="label" />
								</html:select></td>
							<!-- <td width="17%" class="LABEL">
					<td width="17%" class="tdfont"> -->
							<td width="17%" class="LABEL">
								<div id="patCountryCombo" style="display: none; width: 0%">
									<html:select name="newSplRegFB" property="patNationalityCode"
										tabindex="1" styleClass="regcbo">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY%>"
											property="value" labelProperty="label" />
									</html:select>
								</div> <bean:message key="patCaste" />&nbsp;
							</td>
							<td class="tdfont" width="17%"><html:select
									name="newSplRegFB" property="patCasteCode" style="width:75%;"
									tabindex="2" styleClass="regcbo">
									<html:option value="-1">Select Value</html:option>
									<html:options
										collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_CASTE%>"
										property="value" labelProperty="label" />
								</html:select></td>
						</tr>

						<tr>
							<td width="17%" class="LABEL"><bean:message key="mandatory1" />
								<bean:message key="fathersName" />&nbsp;</td>
							<td width="17%" class="tdfont"><html:text name="newSplRegFB"
									property="patGuardianName" styleClass="textbox" tabindex="1"
									maxlength="60" onchange="isAlpha(this,'Guardian Name')"
									onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
									style="width:65%;" /></td>
							<td width="17%" class="LABEL">
								<table>
									<tr>
										<td width="5%" class="LABEL">(OR)</td>
										<td width="95%" class="LABEL"><bean:message
												key="husbandName" />&nbsp;
									</tr>
								</table>
							</td>
							<td width="17%" class="tdfont"><html:text name="newSplRegFB"
									property="patHusbandName" styleClass="textbox" tabindex="2"
									maxlength="60" onchange="isAlpha(this,'Guardian Name')"
									readonly="false"
									onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
									style="width:65%;" /></td>
							<td width="17%" class="LABEL">
								<table>
									<tr>
										<td width="5%" class="LABEL">(OR)</td>
										<td width="95%" class="LABEL"><bean:message
												key="motherName" /> &nbsp;
									</tr>
								</table>
							</td>
							<td width="17%" class="tdfont"><html:text name="newSplRegFB"
									property="patMotherName" styleClass="textbox" tabindex="2"
									maxlength="60" onchange="isAlpha(this,'Guardian Name')"
									onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
									style="width:75%;" /></td>
						</tr>

						<tr>

							<td width="17%" class="LABEL"><bean:message
									key="maritalStatus" />&nbsp;</td>
							<td width="17%" class="tdfont"><html:select
									name="newSplRegFB" property="patMaritalStatusCode"
									style="width:65%;" tabindex="2" styleClass="regcbo"
									onchange="changeSpouseField(this)">
									<html:option value="-1">Select Value</html:option>
									<html:options
										collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS%>"
										property="value" labelProperty="label" />
								</html:select></td>
							<!-- <td width="17%" class="LABEL">
					<td width="17%" class="tdfont"> -->
							<td width="17%" class="LABEL"><bean:message key="religion" />&nbsp;</td>
							<td width="17%" class="tdfont"><html:select
									name="newSplRegFB" property="patReligionCode"
									style="width:65%;" tabindex="2" styleClass="regcbo">
									<html:option value="-1">Select Value</html:option>
									<html:options
										collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION%>"
										property="value" labelProperty="label" />
								</html:select></td>

							<td width="17%" class="LABEL"><bean:message key="birplace" />&nbsp;
							</td>
							<td width="17%" class="tdfont"><html:text name="newSplRegFB"
									property="patBirthPlace" style="width:75%;"
									styleClass="textbox" tabindex="2" maxlength="60"
									onchange="isAlpha(this,'Guardian Name')"
									onkeypress="return validateAlphabetsWithDotsOnly(event,this)" /></td>

						</tr>
						<!-- End:Sheeldarshi -->

						<tr>
							<td width="17%" class="LABEL"><bean:message
									key="patOccupation" />&nbsp;</td>
							<td width="17%" class="tdfont"><html:select
									name="newSplRegFB" property="patOccupation" style="width:65%;"
									tabindex="2" styleClass="regcbo">
									<html:option value="-1">Select Value</html:option>
									<html:options
										collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL%>"
										property="value" labelProperty="label" />
								</html:select></td>
							<td class="LABEL" width="17%"><bean:message
									key="monthlyIncome" />&nbsp;</td>
							<td width="17%" class="tdfont"><html:text name="newSplRegFB"
									property="patMonthlyIncome" style="width:65%;"
									styleClass="textbox" maxlength="16" tabindex="2"
									onkeypress="return validateNumericOnly(this,event)" /></td>

							 <td class="LABEL" width="17%" ><bean:message key="visitreason" />&nbsp;
					</td>
					<td width="17%" class="tdfont">
						<%-- <html:textarea style="width:75%;" name="newSplRegFB" property="patVisitReason" styleClass="textarea" tabindex="2"/> --%>
						<!--By Surabhi on 07.11.2016-->
						<div align="left" id="dialog-form_1" >
							<input type="hidden" name="snomdPTVisitReason" />
					        <input type="hidden" name="snomdCIdVisitReason" />
							<input type="hidden" name="patVisitReason" />
											
							<div id="snomed-ct-search">
								<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
								<!-- <input maxlength="100" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="searchText ui-autocomplete-input tooltipClass" name="txt-snomed-ct-search_1"  style="width:65%;color:#000000;" onchange="load1('1','')"/> -->
							<textarea name="txt-snomed-ct-search_1" rows="2" cols="5" id="txt-snomed-ct-search_1" class="clearable ui-autocomplete-input tooltipClass" value="" onfocus="load1('1','');" style="width:86%;height:80%" autocomplete="off"></textarea>
							</div>
							 <div id="norecorddiv_1">
								<label style="display: none;" id="reccnt">No. of records : </label>
								<span style="display: inline;" id="reccount" ></span>
								<label style="display: none;" id="nosuggestion">No suggestions found</label>
								<label style="display: none;" id="norec">No results found</label>
			                    <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
		                 	</div>         
	                   		<div class="concept" id="conceptdiv_1"></div>
                    	</div>
					<!--End Surabhi  -->
						
					</td> 
							<%-- <td width="17%" class="LABEL"><bean:message
									key="amountCollected" />&nbsp;</td>
							<td width="17%" class="tdfont"><html:text name="newSplRegFB"
									property="patAmountCollected" style="width:75%;"
									styleClass="textbox" maxlength="11"  readonly="true" /> <bean:define
									name="newSplRegFB"  property="patAmountCollected"
									id="patAmountCollectedId"  /> <%
 	System.out.println("patAmountCollectedId :"+patAmountCollectedId);
 %></td> --%>

						</tr>

						<tr style="display:none;">
                       
                       <td width="17%" class="LABEL"><bean:message
									key="amountCollected" />&nbsp;</td>
							<td width="17%" class="tdfont"><html:text name="newSplRegFB"
									property="patAmountCollected" style="width:65%;"
									styleClass="textbox" maxlength="11"  readonly="true" /> <bean:define
									name="newSplRegFB"  property="patAmountCollected"
									id="patAmountCollectedId"  /> <%
 	System.out.println("patAmountCollectedId :"+patAmountCollectedId);
 %></td>
                       
                       
							<%-- <td class="LABEL" width="17%"><bean:message
									key="visitreason" />&nbsp;</td>
							<td width="17%" class="tdfont"><html:textarea
									style="width:65%;" name="newSplRegFB" property="patVisitReason"
									styleClass="textarea" tabindex="2" /></td>
							<td class="LABEL" width="17%"></td>
							<td width="17%" class="tdfont" />
							<td class="LABEL" width="17%"></td>
							<td width="17%" class="tdfont" />
 --%>
						</tr>

						<!-- ## 		Modification Log							
				 		##		Modify Date				:10thMar'15 
				 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
				 		##		Modify By				:Sheeldarshi  -->
						<tr id="trRMO" style="display: none;">
							<td class="LABEL" style="width: 17%;"><font color="red">*</font>Authorized
								By:
							<td class="tdfont" style="width: 17%;">
								<!-- <select name="patRMOEmployee"  tabindex="2" class="select77prcnt">
						<option value="-1">Select Value</option>
					</select> --> <html:select name="newSplRegFB"
									property="patRMOEmployee" style="width:65%" tabindex="1"
									styleClass="regcbo">
									<html:option value="-1">Select Value</html:option>
									<html:options
										collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RMO_EMP%>"
										property="value" labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<!-- End:Sheeldarshi -->
					</table>
					<!-- Start:Sheeldarshi -->
				</his:ContentTag>
				<his:SubTitleTagBroad name="Address Details">
					<table width="100%">
						<tr>
							<td width="40%" align="right"><bean:message key="mandatory1" />
								<font color="#ffffff"
								face="Verdana, Arial, Helvetica, sans-serif"> <b
									style="font-size: 11px"><bean:message key="country" />&nbsp;</b></font></td>
							<td><html:select name="newSplRegFB" style="width:80%"
									tabindex="1" property="patAddCountryCode" styleClass="regcbo"
									onchange="if(this.value!='-1') showState()">
									<html:option value="">Select Value</html:option>
									<html:options
										collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY%>"
										property="value" labelProperty="label" />
								</html:select></td>
							<td width="25%" align="right">
								<div id="stateMandotary">
									<bean:message key="mandatory1" />
									<font color="#ffffff" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b
										style="font-size: 11px"><bean:message key="state" /></b></font>
								</div>
								<div id="stateNotMandotary">
									<font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="state" /></font>
								</div>
							</td>
							<td>
								<div id="divpatAddStateCodeCombo" style="">
									<html:select name="newSplRegFB" tabindex="1" style="width:75%"
										property="patAddStateCode" styleClass="regcbo"
										onchange="if(this.value!='-1') getDistrictBasedOnState(this)">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE%>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
								<div id="divpatAddStateCodeText" style="display: none;">
									<html:text name="newSplRegFB" tabindex="1"
										property="patAddStateName" styleClass="regcbo">
									</html:text>
								</div>
							</td>
						</tr>
					</table>
				</his:SubTitleTagBroad>

				<his:ContentTag>
					<table width="97.5%" cellpadding="0">
						<tr>
							<td class="LABEL" width="17%"><bean:message key="hno" />&nbsp;</td>
							<td class="tdfont" width="17%"><html:text name="newSplRegFB"
									style="width:70%"
									onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
									property="patAddHNo" tabindex="2" maxlength="15"
									styleClass="textbox" /></td>
							<td class="LABEL" width="10%"><bean:message key="street" />&nbsp;</td>
							<td class="tdfont" width="10%"><html:text name="newSplRegFB"
									property="patAddStreet" style="width:65%"
									onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
									tabindex="2" maxlength="30" styleClass="textbox" /></td>
							<td class="LABEL" width="10%"><bean:message key="location" />&nbsp;
							</td>
							<td class="tdfont" width="12%">
								<div id="divpatAddCityLocCode"></div>
								<div id="divpatAddCityLocation">
									<html:text style="width:90%" name="newSplRegFB" tabindex="2"
										maxlength="50"
										onkeypress="return validateAlphaNumericWithDotsOnly(event,this);"
										styleClass="textbox" property="patAddCityLoc" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="LABEL" width="17%"><bean:message key="district" />&nbsp;
							</td>
							<td class="tdfont" width="17%">
								<div id="districtCombo">
									<html:select name="newSplRegFB" tabindex="2"
										styleClass="regcbo" style="width:70%"
										property="patAddDistrictCode">
										<html:option value="-1">Select Value</html:option>
										<html:options
											collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>"
											property="value" labelProperty="label" />
									</html:select>
								</div>
								<div id="districtComboNonDefault" style="display: none;">
									<select name="patAddDistrictCode" tabindex="2" class="regcbo">
										<option value="-1">Select Value</option>
									</select>
								</div>
								<div id="districtTextBox" style="display: none">
									<html:text name="newSplRegFB" tabindex="2"
										property="patAddDistrict" onchange="isAlpha(this,'District')"
										onkeypress="return validateAlphabetsWithDotsOnly(event)"
										maxlength="30" styleClass="textbox" />
								</div>
							</td>
							<td class="LABEL" width="10%"><bean:message key="city" /> <bean:message
									key="slash" /> <bean:message key="village" />&nbsp;</td>
							<td class="tdfont" width="10%"><html:text name="newSplRegFB"
									style="width:65%" property="patAddCity"
									onchange="isAlpha(this,'City')"
									onkeypress="return validateAlphabetsWithDotsOnly(event,this);"
									maxlength="30" styleClass="textbox" /></td>
							<td class="LABEL" width="10%">
								<%-- <bean:message key="mandatory1" /> --%> <bean:message
									key="pin" />&nbsp;
							</td>
							<td class="tdfont" width="12%"><html:text name="newSplRegFB"
									property="patAddPIN" size="17" style="width:90%" maxlength="6"
									onkeypress="return validateNumeric(event)" /> <!-- 					<input type="text" id="pintext" -->
								<!-- 						name="patAddPIN" tabindex="2" size="17" maxlength="6" -->
								<!-- 						onkeypress="return validateNumeric(event)" /> --></td>
						</tr>
						<tr>
							<td class="LABEL" width="17%">Land Mark&nbsp;</td>
							<td class="tdfont" width="17%">
								<div>
									<html:text name="newSplRegFB" style="width:70%" tabindex="2"
										property="strPatAddressTehsil" maxlength="30"
										styleClass="textbox"
										onkeypress="return validateAlphabetsOnly(event,this)" />
								</div>
							</td>
							<td width="10%" class="LABEL"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"
								id="mandatoryAreaCat" style="display: none;"> <bean:message
										key="mandatory1" /></font> <bean:message key="areaCategory" />&nbsp;
							</td>
							<td width="10%" class="tdfont"><html:select
									name="newSplRegFB" property="patIsUrban" style="width:65%"
									tabindex="2" styleClass="regcbo">
									<html:option value="">Select Value</html:option>
									<html:options
										collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY%>"
										property="value" labelProperty="label" />
								</html:select></td>
							<td class="LABEL" width="10%">Email&nbsp;</td>
							<td class="tdfont" width="12%">
								<div>
									<html:text name="newSplRegFB" style="width:90%" tabindex="2"
										property="patAddEmailId" maxlength="70" styleClass="textbox" />
									<!-- <input name="patAddEmailId" maxlength="70" type="text" class="input75prcnt"  tabindex="2"  /> -->
								</div>
							</td>

							<%-- <td class="LABEL" width="18%"><bean:message key="contactNo" /></td>
					<td class="tdfont" width="18%">
					<div><html:text name="newSplRegFB"
						tabindex="2" property="patAddContactNo"  maxlength ="30" styleClass="textbox" onkeypress="return validateNumeric(event)" />
					</div>
					</td> --%>
						</tr>
						<!-- Start:Sheeldarshi -->
						<tr>
							<td class="LABEL" width="17%"><bean:message
									key="global.phone" />&nbsp;<bean:message key="number" />&nbsp;
							</td>
							<td class="tdfont" width="17%">
								<div>
									<input name="patAddPhoneNo" id="patAddPhoneNo" maxlength="15"
										tabindex="2" type="text" class="input70prcnt">
								</div>
							</td>
							<td width="18%" class="LABEL"><bean:message
									key="global.phone" />&nbsp;<bean:message key="owner" />
								&nbsp;</td>
							<td width="18%" class="tdfont"><html:select
									name="newSplRegFB" property="patAddPhoneOwner"
									styleClass="regCbo" style="width:65%" tabindex="2">
									<html:option value="-1">Select Value</html:option>
									<html:option value="1">Self (patient)</html:option>
									<%-- <html:option value="2">ANM</html:option> --%>
									<html:option value="3">Other</html:option>
									<%-- <html:option value="4">Neighbour</html:option> --%>
									<html:option value="5">Family member</html:option>
								</html:select></td>
							<td class="LABEL" width="18%"><bean:message key="mandatory1" />
								<bean:message key="mobileNo" />&nbsp;</td>
							<td class="tdfont" width="18%">
								<div>
									<html:text name="newSplRegFB" tabindex="1"
										property="patAddMobileNo" style="width:90%" maxlength="10"
										styleClass="textbox"
										onkeypress="return validateNumeric(event)" />
									<!-- 						<input name="patAddMobileNo" maxlength="10" type="text" class="input75prcnt"  tabindex="2" onkeypress="return validateNumeric(event)" > -->
								</div>
							</td>
						</tr>
						<!-- 	End:Sheeldarshi -->
						<tr>
							<td class="LABEL" width="18%"><bean:message key="emergency" />&nbsp;<bean:message
									key="contactNo" />&nbsp;</td>
							<td class="tdfont" width="18%">
								<div>
									<html:text name="newSplRegFB" tabindex="1"
										property="patEmgCntNo" style="width:90%" maxlength="10"
										styleClass="textbox"
										onkeypress="return validateNumeric(event)" />
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
				<bean:define name="newSplRegFB" property="isReferred" id="isref"
					type="java.lang.String" />
				<!--................................... code for referred details........... -->

				<his:SubTitleTag name="Refer Details">
					<table width="100%" cellspacing="1" cellpadding="1">
					</table>
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr>
							<td width="90%">
								<div align="right">
									<b><font size="2"
										face="Verdana, Arial, Helvetica, sans-serif" color="#ffffff">
											<bean:message key="isreferred" />
									</font></b>
								</div>
							</td>
							<td>
								<div align="left">
									<html:checkbox name="newSplRegFB" tabindex="2"
										property="isReferred" onclick="Isreferred(this)" value="1" />
								</div>
							</td>
						</tr>
					</table>
				</his:SubTitleTag>
				<%-- <his:ContentTag> --%>
				<table width="100%" cellspacing="1" id="divRefDtlId" cellpadding="1"
					style="display: none;">
					<tr>
						<td width="75%" nowrap class="LABEL" colspan="2">
							<div id="divReferredInstitute" align="right">
								<bean:message key="mandatory1" />
								<font face="Verdana,Arial,Helvetica,sans-serif"> <bean:message
										key="associatedInst" />
								</font>
								<html:radio name="newSplRegFB" property="referringInstType"
									tabindex="1" value="G" onclick="showdivhoscode(this)" />
								<font face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="other" />
								</font>
								<html:radio name="newSplRegFB" property="referringInstType"
									value="O" tabindex="1" onclick="showdivhoscode(this)" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</td>
						<td width="100%" class="tdfont" nowrap>
							<div id='divRefHosCode'>
								&nbsp;&nbsp;&nbsp;
								<table>
									<tr width="100%">
										<td class="LABEL" width="80%"><bean:message
												key="mandatory1" /> <font
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
													key="institute" /> <bean:message key="name" /></font></td>
										<td width="50%"><html:select name="newSplRegFB"
												tabindex="1" property="patRefGnctdHospitalCode"
												styleClass="registrationCmb">
												<html:option value="">Select Value</html:option>
												<html:options
													collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL%>"
													property="value" labelProperty="label" />
											</html:select></td>
									</tr>
								</table>
							</div>
							<div id='divRefHosname' width="50%" style='display: none;'>
								&nbsp;&nbsp;&nbsp;
								<table>
									<tr width="100%">
										<td class="LABEL" width="80%"><bean:message
												key="mandatory1" /><font
											face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
													key="institute" /> <bean:message key="name" /></font></td>
										<td width="50%"><html:text name="newSplRegFB"
												tabindex="1" property="patRefHospitalName"
												onkeypress="return validateAlphabetsSpaceDotAndBracket(event,this)"
												maxlength="100" styleClass="textboxBig" size="20" /></td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<%-- </his:ContentTag> --%>
				<div id="divReferred" style="display: none;">
					<%-- <his:ContentTag> --%>
					<table width="100%" cellpadding="1" cellspacing="1">
						<tr align="center">
							<td width="20%" class="LABEL"><bean:message key="referring" />
								<bean:message key="institute" /> <bean:message key="crNo" /></td>
							<td width="35%" nowrap="nowrap" class="tdfont"><html:text
									name="newSplRegFB" tabindex="1"
									property="patRefGnctdHospitalCrno" maxlength="15"
									style="width:35%" onkeydown="setPrevValue(this, event);"
									onkeyup="moveToRight(this, event);"
									onkeypress="return validateNumeric(event)" styleClass="textbox" />
							</td>
							<td width="15%" class="LABEL" style="" align="center"><bean:message
									key="mandatory1" /> <bean:message key="referredBy" /></td>
							<td width="18%" colspan="2" class="tdfont">
								<div align="left">
									<html:text name="newSplRegFB" maxlength="60"
										property="patRefDoctor" style="width:60%"
										onchange="isAlpha(this,'Referred By Doctor')" tabindex="1"
										styleClass="textbox"
										onkeypress="return validateAlphabetsSpaceDotAndBracket(event,this)" />
								</div>
							</td>
						</tr>
						<tr align="center">
							<td width="20%" class="LABEL" style=""><bean:message
									key="referring" /> <bean:message key="institute" /> <bean:message
									key="department" /></td>
							<td width="35%" class="tdfont"><html:select
									name="newSplRegFB" style="width:35%"
									property="patRefGnctdHospitalDept" tabindex="1"
									styleClass="regCbo" onchange="checkReferDepartment(this)">
									<html:option value="-1">Select Value</html:option>
									<html:option value="0">Other</html:option>
									<html:options
										collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL%>"
										property="value" labelProperty="label" />
								</html:select>&nbsp; <html:text name="newSplRegFB"
									style="display:none;width:35%"
									property="patRefHospitalDeptOther" maxlength="20" tabindex="1"
									styleClass="textbox" disabled="true"
									onkeypress="return validateAlphabetsOnly(event,this)" /></td>

							<td width="25%" class="LABEL" align="center"><bean:message
									key="referring" /> <bean:message key="institute" /> <bean:message
									key="unit" /></td>
							<td width="25%" class="tdfont"><html:text name="newSplRegFB"
									style="width:60%" property="patRefGnctdHospitalDeptUnit"
									tabindex="1" maxlength="15" styleClass="textbox"
									onkeypress="return validateAlphaNumericandSpaceOnly(event,this)" /></td>
						</tr>

					</table>
					<%-- </his:ContentTag> --%>
				</div>

				<!--.......................... code for referred details......... ends here.......... -->

			</his:statusTransactionInProcess>

			<!-- <table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td align="center">
			<div align="center"><img class="button"
				src='/HIS/hisglobal/images/buttons/btn-sv.png' tabindex="1"
				style="cursor: pointer;"
				onkeypress="if(event.keyCode==13)submitFormOnValidate(ValidateDepartment(document.getElementsByName('departmentsToVisitStamp')[0]) && Validate(),'SAVE');"
				onclick="submitFormOnValidate(ValidateDepartment(document.getElementsByName('departmentsToVisitStamp')[0]) && Validate(),'SAVE');"
				tabindex="1" /> <img class="button"
				src='/HIS/hisglobal/images/buttons/btn-ccl.png' tabindex="1"
				style="cursor: pointer;"
				onkeypress="if(event.keyCode==13) submitForm('NEW');"
				tabindex="1" onclick="submitForm('NEW');"> <img
				class="button" src='/HIS/hisglobal/images/buttons/btn-clr.png' tabindex="1"
				style="cursor: pointer;" onclick="submitForm('NEWREG')"
				onkeypress="if(event.keyCode==13) submitForm('NEWREG');"> 
				<img
				class="button" src='../hisglobal/images/btn-reprint.png' tabindex="1"
				
				style="cursor: pointer;" onclick="printCard()"
				onkeypress="if(event.keyCode==13) printCard();">
				
				</div>
			</td>
		</tr>
	</table> -->


			<div class="div-table-button">
				<div class="div-table-row footerBar">
					<div class="div-table-col"></div>
				</div>
				<div class="div-table-row emptyBar">
					<div class="div-table-col"></div>
				</div>
				<div class="div-table-row" align="center">
					<a tabindex="1" href="#" class="button" id="submitId"
						onkeypress="if(event.keyCode==13)submitFormOnValidate(ValidateDepartment(document.getElementsByName('departmentsToVisitStamp')[0]) && Validate() && setName(),'SAVE');"
						onclick="submitFormOnValidate(ValidateDepartment(document.getElementsByName('departmentsToVisitStamp')[0]) && Validate() && setName(),'SAVE');"><span
						class="save"><bean:message key="save" /></span></a> <a tabindex="1"
						href="#" class="button" id="clearId"
						onkeypress="if(event.keyCode==13)submitForm('refresh');"
						onclick="submitForm('refresh');"><span class="clear"><bean:message
								key="clear" /></span></a> <a tabindex="1" href="#" class="button"
						id="cancelId"
						onkeypress="if(event.keyCode==13)submitForm('unspecified');"
						onclick="submitForm('unspecified');"><span class="cancel"><bean:message
								key="cancel" /></span></a>

				</div>

			</div>

			<input type="hidden" name="sysDate" value="<%=systemDate%>" />


			<html:hidden name="newSplRegFB" property="empIdChk" />
			<html:hidden name="newSplRegFB" property="patIdNo" />
			<html:hidden name="newSplRegFB" property="isIdRequired" />
			<html:hidden name="newSplRegFB" property="patDataFromEmployeeTable" />
			<html:hidden name="newSplRegFB" property="patAddDistrictCodeHidden" />
			<html:hidden name="newSplRegFB" property="patAddPINHidden" />
			<html:hidden name="newSplRegFB" property="patAddCityHidden" />
			<html:hidden name="newSplRegFB" property="patIsUrbanHidden" />
			<html:hidden name="newSplRegFB" property="isDuplicatePatientPopup" />
			<html:hidden name="newSplRegFB" property="patCrNo" />
			<html:hidden name="newSplRegFB" property="errorMessage" />
			<html:hidden name="newSplRegFB" property="isOldPatient" />
			<html:hidden name="newSplRegFB" property="oldPatCrNo" />

			<html:hidden name="newSplRegFB" property="patAptNo" />
			<html:hidden name="newSplRegFB" property="patAptSlot" />
			<html:hidden name="newSplRegFB" property="patAptQueueNO" />
			<html:hidden name="newSplRegFB" property="department" />
			<html:hidden name="newSplRegFB" property="patAddCountry" />
			<html:hidden name="newSplRegFB" property="patAddState" />
			<html:hidden name="newSplRegFB" property="patAddDistrict" />
			<html:hidden name="newSplRegFB" property="creditBillFlag" />
			<html:hidden name="newSplRegFB" property="seniorCitizenAgeLimit" />
			<html:hidden name="newSplRegFB" property="seniorCitizenCatCode" />
			<html:hidden name="newSplRegFB" property="isAadharConsentGiven" />

			<input type="hidden" name="patMaritalStatusCode">
			<input type="hidden" name="patAddHNo">
			<input type="hidden" name="patNickName">
			<input type="hidden" name="patHusbandOccupation">
			<input type="hidden" name="bplDetailsFound">
			<!-- Start:Sheeldarshi -->
			<input type="hidden" name="patCatDocCode" />
			<input type="hidden" name="patCatDocIsAlternateId" />
			<input type="hidden" name="clientName" />
			<input type="hidden" name="relationNameWithStaff" />
			<input type="hidden" name="patActualAmount" />
			<input type="hidden" name="isSnomedServiceOn" />
			<input type="hidden" name="targetId" />

			<!-- End:Sheeldarshi -->
			<%
				}
			%>
		</his:statusTransactionInProcess>
		<%-- <html:hidden name="newSplRegFB" property="patPrimaryCatCode" /> --%>
		<html:hidden name="newSplRegFB" property="configFlag" />
		<input type="hidden" name="hmode" value="unspecified" />
		<html:hidden name="newSplRegFB" property="patAptNo" />

		<html:hidden name="newSplRegFB" property="print" />
		<html:hidden name="newSplRegFB" property="localLanguage" />
		<html:hidden name="newSplRegFB" property="isBarcodeSlipPrint" />

	</div>
	<his:status />

</body>
</html>

<%
	}catch(Exception e)
{
	System.out.println("e: " + e);

e.printStackTrace();	
}
%>