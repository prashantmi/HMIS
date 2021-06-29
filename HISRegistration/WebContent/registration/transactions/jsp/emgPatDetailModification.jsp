<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet"	type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet"	type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet"	type="text/css">
<link rel="stylesheet"	href="./../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>


<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>

<script language="JavaScript"	src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script type="text/javascript"	src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript"	src="./../../registration/transactions/js/popup.js"></script>
<script type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<title>Emergency Patient Detail Modification</title>

</head>
<script type="text/javascript">
var isAddressAdded  = <%=RegistrationConfig.IS_ADDRESS_ADDED%>;
var isAddressModified = <%=RegistrationConfig.IS_ADDRESS_MODIFIED%>;

var registrationDeskDefaultCountryCode = '<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>';

$(window).on("load.loading1", function() 
{
	initMultilingual();	
	if (document.getElementsByName("AfterGo")[0].value != '0') 
	{
		document.getElementById("divAfterGo").style.display = "";
		document.getElementById("divAfterGoId").style.display = "";
		document.getElementById("divBeforeGo").style.display = "none";
		document.getElementById("divGoId").style.display = "none";
		emgPatDtlModification.fetchDefaultValues();
		//document.getElementById("broughtByDiv").style.display = "none";//Added by Vasu on 24.May.2018
	}
	
	callThisOnload();
});

function callMenu(url)
{
	//alert('menu called with url: '+ url);
	var targetURL = url;// + "?varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
	
	
	var elemFrame = parent.document.getElementById("frmMain");
	if(elemFrame!=null){
		elemFrame.src=targetURL;
		elemFrame.refresh();
	}
	else{
		if(typeof $('#tabframe')!='undefined'){
			var tab = window.parent.$('#tabframe').tabs('getSelected');
			var index = window.parent.$('#tabframe').tabs('getTabIndex',tab);
			window.parent.$('#tabframe').tabs('close',index);			
		}
	}
}
function broughtBy(obj)
{
	if ($('[name="isBroughtDead"]')[0].checked)
	{
		if (!obj.checked) {
			$("#isUnknownId").show();
		}
		obj.checked = "true";
		$('[name="patIdMark1"]').validatebox({
			required : true,
			validType : 'consecutiveSpecialChar'
		});
		$('[name="patIdMark2"]').validatebox({
			required : true,
			validType : 'consecutiveSpecialChar'
		});
		
	} else {
		$("#isUnknownId").hide();
		
		$('[name="patIdMark1"]').validatebox({
			required : false,
			validType : 'consecutiveSpecialChar'
		});
		$('[name="patIdMark2"]').validatebox({
			required : false,
			validType : 'consecutiveSpecialChar'
		});
	}
	
	if(obj.checked==true)
	{
		$('[name="isRelative"]').validatebox({validType: ['selectCombo[-1]']});
 		$('[name="broughtByName"]').validatebox({required: false, validType: 'allowAlphaSpaceBracketDot'});
 		$('[name="broughtByLocation"]').validatebox({required: false,validType: 'allowAlphaSpaceBracketDot'});
 		$('[name="broughtByPhone"]').validatebox({required: false,validType: [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']});
 		$('[name="broughtByAddress"]').validatebox({required: false,validType: 'alphaNumericWithSpaces'});
// 		$('[name="patIdMark1"]').validatebox({required: true,validType: 'alphaNumeric'});
 //		$('[name="patIdMark2"]').validatebox({required: true,validType: 'alphaNumeric'});

		obj.value="1";
		document.getElementById("broughtById").style.display="";
	}
	else
	{
		//alert("fsfsdf");
		$('[name="broughtByRelationCode"]').validatebox({validType:null});
		$('[name="isRelative"]').validatebox({validType: null});
		$('[name="broughtByName"]').validatebox({required: false});
 		$('[name="broughtByLocation"]').validatebox({required: false});
 		$('[name="broughtByPhone"]').validatebox({required: false});
 		$('[name="broughtByAddress"]').validatebox({required:false});
 	//	$('[name="patIdMark1"]').validatebox({required:false});
 		//$('[name="patIdMark2"]').validatebox({required:false});
		obj.value="0";
		document.getElementById("broughtById").style.display="none";
		document.getElementsByName("isRelative")[0].value="-1"
		document.getElementsByName("broughtByRelationCode")[0].value="-1"
		document.getElementsByName("broughtByName")[0].value=""
		document.getElementsByName("broughtByLocation")[0].value=""
		document.getElementsByName("constableDesig")[0].value=""
		document.getElementsByName("constableBadgeNo")[0].value=""
		document.getElementsByName("broughtByPhone")[0].value=""
		document.getElementsByName("broughtByAddress")[0].value=""

               	document.getElementsByName("policeStation")[0].value="";
		enableRelation(document.getElementsByName("isRelative")[0])
	}

}


function checkMLC(obj)
{
	if(obj.checked==true){
		obj.value="1";
	}
	else{
		obj.value="0";
	}
}
function onBroughtDead(obj)
{
	if(obj.checked==true){
		obj.value="1";
		document.getElementsByName("isBroughtBy")[0].checked=true;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
	}
	else{
		obj.value="0";
		document.getElementsByName("isBroughtBy")[0].checked=false;
		broughtBy(document.getElementsByName("isBroughtBy")[0]);
	}
}


function submitForm(mode) 
{
	document.forms[0].action = mode + "EmgPatDetailMod.action";
	document.forms[0].submit();

}
function submitFormonRadio(mode,obj) {
	document.forms[0].action = mode + "EmgPatDetailMod.action?crno="+obj.value;
	document.forms[0].submit();

}
function submitCancelAction(cnt)
{
	document.forms[0].action = "cancel" + cnt + ".action";
	document.forms[0].submit();
}
function submitClearAction(cnt)
{
	document.forms[0].action = "Clear" + cnt + ".action";
	document.forms[0].submit();
}

function submitClear(cnt)
{
	document.forms[0].action =  cnt + ".action";
	document.forms[0].submit();
}

function populate(selectedarray)
{
	var strHtml = "";
	var elem = document.getElementById("hiddenDivVerification");
	for (i = 0; i < selectedarray.length; i++) 
	{
		var arrayOfDocsData = selectedarray[i].split("|");
		strHtml += "<input type='hidden' name='arrSelectedVerifyDocs' value='"+selectedarray[i]+"'/>	";
		strHtml += arrayOfDocsData[1] + "&nbsp; &nbsp;";
	}
	elem.innerHTML = ":: &nbsp;" + strHtml;
}

function callThisOnload() 
{
	if (document.getElementsByName("patCrNo")[0])
		document.getElementsByName("patCrNo")[0].focus();
	enableRelation(document.getElementsByName("isRelative")[0]);
	if(document.getElementsByName("isMLC")[0].value=='1')
	{
		document.getElementsByName("isMLC")[0].checked=true;
	}
	if(document.getElementsByName("isBroughtDead")[0].value=='1')
	{
		document.getElementsByName("isBroughtDead")[0].checked=true;
	}
	if(document.getElementsByName("isRelative")[0].value!='-1')
	{
		document.getElementsByName("isBroughtBy")[0].checked=true;
	}
	else
	{
		document.getElementsByName("isBroughtBy")[0].checked=false;
	}
	broughtBy(document.getElementsByName("isBroughtBy")[0]);
	if (document.getElementsByName("AfterGo")[0].value != '0') 
	{
	showDivAgeDob();
	setMotherValidRule();
	var a = document.getElementsByName("broughtByConsultant")[0]; 
	//alert(a.value);
	if(a.value==-1)
		a.value=0;   
	document.getElementsByName("broughtByDeclared")[0].value=a.value;
	}
}	

//added by manisha gangwar date:28.3.2018
function show(e)
{
	var processID = document.getElementsByName("processID")[0].value;
	var crNo = document.getElementsByName("crNo")[0].value;
	var filname = document.getElementsByName("filname")[0].value;
	//if(document.getElementsByName("isImageStoredFTP")[0].value=="0")
	if(document.getElementsByName("isImageStoredFTP")[0].value==null||document.getElementsByName("isImageStoredFTP")[0].value==''||document.getElementsByName("isImageStoredFTP")[0].value=="0")
	//var url = "../../registration/transactions/viewImageFromMongoDBEnlargedImage.action?strProcessId="+processID+"&strCRNoHospCode="+crNo+"&strFileName="+filname; 
	var url = "../../registration/transactions/EnlargedImage.action";
	else
	var url = "../../registration/transactions/EnlargedImage.action?strProcessId="+processID+"&strCRNoHospCode="+crNo+"&strFileName="+filname;
	openURLInPopup(url,400,400,0,0);
}

function enableRelation(obj)
{	
	
	if(obj.value=="1")///////relative
	{
		$('[name="broughtByAddress"]').validatebox({required: true,validType: 'alphaNumericWithSpaces'});
 		$('[name="broughtByPhone"]').validatebox({required: true,validType: [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']});
		
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' ></font>Relative Name";
		document.getElementsByName("broughtByRelationCode")[0].disabled=false;
		document.getElementById("phoneBroughtByDiv").style.display="block";
		//alert("asdsd");
		$('[name="broughtByRelationCode"]').validatebox({required: true,validType: ['selectCombo[0]']});
		$('[name="constableDesig"]').validatebox({
			required : false,
			validType : 'alphaNumeric'
		});
		$('[name="constableBadgeNo"]').validatebox({
			required : false,
			validType : 'alphaNumeric'
		});
               	$('[name="policeStation"]').validatebox({required:false});
		
		document.getElementById("policeDetailDiv").style.display = "none";
		document.getElementById("policeStnDiv").style.display = "none";
		document.getElementsByName("constableDesig")[0].value = ""
		document.getElementsByName("constableBadgeNo")[0].value = ""
		document.getElementsByName("policeStation")[0].value = ""
	}
	else 
	{
		$('[name="broughtByPhone"]').validatebox({required: false});
 		$('[name="broughtByAddress"]').validatebox({required:false});
		$("#broughtByRelationCodeId").removeClass('validatebox-invalid');
		document.getElementsByName("broughtByRelationCode")[0].disabled=true;
	}
	if(obj.value=="2"){/////////POlICE Detail
		//alert("2");
		$('[name="broughtByPhone"]').validatebox({required: false});
 		$('[name="broughtByAddress"]').validatebox({required:false});
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' ></font>Officer Name";
		document.getElementById("policeDetailDiv").style.display="block";
		document.getElementById("policeStnDiv").style.display="block";
		document.getElementById("phoneBroughtByDiv").style.display="none";
		$('[name="constableDesig"]').validatebox({required: false,validType: 'alphaNumeric'});
 		$('[name="constableBadgeNo"]').validatebox({required: false,validType: 'alphaNumeric'});
 		$('[name="broughtByRelationCode"]').validatebox({validType: null});
		document.getElementsByName("broughtByRelationCode")[0].value = "-1"
		
		document.getElementsByName("broughtByPhone")[0].value = ""
		document.getElementsByName("broughtByAddress")[0].value = ""
	}
	else {
		$('[name="broughtByAddress"]').validatebox({required: false,validType: 'alphaNumericWithSpaces'});
 		$('[name="broughtByPhone"]').validatebox({required: false,validType: [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']});
		document.getElementById("policeDetailDiv").style.display="none";
		document.getElementById("policeStnDiv").style.display="none";
		$('[name="constableDesig"]').validatebox({required: false});
 		$('[name="constableBadgeNo"]').validatebox({required: false});
 		$('[name="policeStation"]').validatebox({required : false});
	}
	// in order to change the label "name" of brought by to EMT name & to diplay the combo for vehicle in case of choice by 108
	if(obj.value=="3"){
		document.getElementById("phoneBroughtByDiv").style.display="block";
		document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' ></font>Name";
		
		$('[name="broughtByAddress"]').validatebox({
			required : false,
			validType : 'alphaNumericWithSpaces'
		});
		$('[name="broughtByPhone"]').validatebox({
			required : false,
			validType : [ 'numericwithoutspace', 'equalLengthNumeric[10]','DisableAllZero','mobileNostartwithseven']
		});
		//$('[name="broughtByRelationCode"]').validatebox({validType: null});
$('[name="broughtByRelationCode"]').validatebox({validType: null});
		//document.getElementById("nameBroughtBy").innerHTML="<font color='#FF0000' size='2' >*</font>Name";
		document.getElementById("policeDetailDiv").style.display = "none";
		document.getElementById("policeStnDiv").style.display = "none";
		//document.getElementById("phoneBroughtByDiv").innerHTML="Phone No.";
		$('[name="constableDesig"]').validatebox({
			required : false
		});
		$('[name="constableBadgeNo"]').validatebox({
			required : false
		});
		$('[name="policeStation"]').validatebox({
			required : false
		});
	
		document.getElementsByName("broughtByRelationCode")[0].value = "-1"
		document.getElementsByName("constableDesig")[0].value = ""
		document.getElementsByName("constableBadgeNo")[0].value = ""
		document.getElementsByName("policeStation")[0].value = ""
	}
	if (obj.value == "-1")///////relative
	{
		$('[name="broughtByRelationCode"]').validatebox({validType: null});
		document.getElementById("nameBroughtBy").innerHTML = "<font color='#FF0000' size='2' ></font> Name";
		document.getElementById("phoneBroughtByDiv").style.display = "block";
		document.getElementById("policeDetailDiv").style.display = "none";
		document.getElementById("policeStnDiv").style.display = "none";
		$('[name="broughtByAddress"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="broughtByPhone"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="broughtByRelationCode"]').validatebox({
			validType : null
		});
		$('[name="isRelative"]').validatebox({
			validType : null
		});
		$('[name="constableDesig"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="constableBadgeNo"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="broughtByLocation"]').validatebox({
			required : false,
			validType : null
		});
		$('[name="broughtByName"]').validatebox({
			required : false,
			validType : null
		});
		
		$('[name="policeStation"]').validatebox({required: false});

		document.getElementsByName("isRelative")[0].value = "-1"
		document.getElementsByName("broughtByRelationCode")[0].value = "-1"
		document.getElementsByName("broughtByName")[0].value = ""
		document.getElementsByName("broughtByLocation")[0].value = ""
		document.getElementsByName("constableDesig")[0].value = ""
		document.getElementsByName("constableBadgeNo")[0].value = ""
		document.getElementsByName("broughtByPhone")[0].value = ""
		document.getElementsByName("broughtByAddress")[0].value = ""
		document.getElementsByName("isRelative")[0].value = "-1"
		document.getElementsByName("policeStation")[0].value = ""

	}
	if(!document.getElementsByName("isBroughtBy")[0].checked){
		
		$('[name="broughtByPhone"]').validatebox({required: false});
 		$('[name="broughtByAddress"]').validatebox({required:false});
 		$('[name="constableDesig"]').validatebox({required: false});
 		$('[name="constableBadgeNo"]').validatebox({required: false});
               	$('[name="policeStation"]').validatebox({required: false});
	}
	
}


function showInit()
{
	var docTypeObj = $('[name="patDocType"]')[0];
	var docType= docTypeObj.options[docTypeObj.selectedIndex].value;

	if(docType!="-1"){
		$("#divCardNoId").show();
		
	}else{
		$('[name="patCardNo"]').validatebox({required: false});
		$("#divCardNoId").hide();
	}
	
	for(var i=0; i<veriyDocJSONArray.length; i++){
		if(veriyDocJSONArray[i].patDocType==docType){
			$('[name="patCardNo"]').attr('maxlength', veriyDocJSONArray[i].patDocIdSize);
			
			docValidType= patDtlModification.getDocValidtype(veriyDocJSONArray[i].patDocValidationType);
			
			$('[name="patCardNo"]').validatebox({required: true, validType: docValidType});
			
			break;
		}else{
			$('[name="patCardNo"]').validatebox({required: false});
		}
	}
}
function broughtDeadConsultant(obj){
	var ConsultantName =  obj.options[broughtByDeclaredId.selectedIndex].value;
	//alert(ConsultantName)
	document.getElementsByName("broughtByConsultant")[0].value=ConsultantName;
	}
</script>
<body >
	<center>

		<s:actionerror />

		<s:form action="EmgPatDetailMod">
			<s:set name="theme" value="simple" scope="page" />
			<div class="wrapper rounded ">
				<div class="div-table">
					<div class="div-table-row title">
						<div class="div-table-col  width100 "><s:text name="emergency"/>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="global.detail"/>&nbsp;<s:text name="modification"/></div>
						
					</div>
				</div>

				<his:InputCrNoTag name="EmgPatDetailMod"> </his:InputCrNoTag>
				<div id="divBeforeGo"> 
					<%int i=0; %>
				<s:iterator value="#session.patVoList" status = "key">
				<%i++;  %>
				</s:iterator>
				<%System.out.println(i); %>
				<%if(i>=0){ %>
				<div class="div-table-listing rounded">
				
				<div class="div-table-row ">
									<div class="div-table-col  width10 "><b><s:text name="select"/></b>
									</div>
									<div class="div-table-col  width20 ">
											<b><s:text name="crNO"/></b>
									</div>
									<div class="div-table-col  width20 ">
											<b><s:text name="first"/>&nbsp;<s:text name="global.name"/></b>
									</div>
									<div class="div-table-col  width20 ">
											<b><s:text name="middle"/>&nbsp;<s:text name="global.name"/></b>
									</div>
									<div class="div-table-col  width20 ">
											<b><s:text name="last"/>&nbsp;<s:text name="global.name"/></b>
									</div>
				</div>
				<s:iterator value="#session.patVoList" status = "key">
						<div class="div-table-row ">
								<div class="div-table-col  width10 ">
									<input type="radio"	name="crNoToModify" onclick="submitFormonRadio('GETPATDTL',this);" value='<s:property value="patCrNo"/>' tabindex="1" />
								</div>
								<div class="div-table-col  width20 ">
										<s:property value="patCrNo"/>
								</div>
								<div class="div-table-col  width20 ">
										<s:property value="patFirstName"/>&nbsp;
								</div>
								<div class="div-table-col  width20 ">
							  		<s:property value="patMiddleName"/>&nbsp;
								</div>
								<div class="div-table-col  width20 ">
										<s:property value="patLastName"/>
								</div>
						</div>
				</s:iterator>
				</div>
			<%} %>
			</div>		
				<div id="divAfterGo" style="display: none">
					<div class="div-table">
						<div class="div-table-row title">
							<div class="div-table-col "  style="width: 55%; "><s:text name="global.patient"/>&nbsp;<s:text name="global.details"/></div>
							
							<div class="div-table-col" style="width: 45%; ">
								<div  class="div-table" >
									<div class="div-table-row title">
									<!-- Modified by Vasu on 24.May.2018 as Brought dead is now replaced by Is Ambulatory Flag in Emg Registration -->
									   <div id="broughtByDiv">							
										 <div class="div-table-col " style="width: 30%;" align="right"><s:text name="global.is"/>&nbsp;<s:text name="brought"/>&nbsp;<s:text name="dead"/></div>
										<div  class="div-table-col " style="width: 10%;">
											<input type="checkbox" name="isBroughtDead" value='<s:property value="isBroughtDead"/>' onclick="onBroughtDead(this)">
										</div> 
										</div>
										  <div  class="div-table-col " style="width: 23%;display:none;" align="right"><s:text name="mlc"/></div>
										<div  class="div-table-col " style="width: 10%;display:none;">
											<input type="checkbox" name="isMLC" value='<s:property value="isMLC"/>' onclick="checkMLC(this)">
										</div>
									</div>
								</div>
							</div>
							</div>

						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;"><s:text name="global.patient"/>&nbsp;<s:text name="global.name"/></div>
							<div class="div-table-col" style="width: 16%;"> &nbsp;<font color="red">*</font><s:text name="first"/></div>
							<div class="div-table-col" style="width: 16%;">&nbsp;<s:text name="middle"/></div>
							<div class="div-table-col" style="width: 16%;">&nbsp;<s:text name="last"/></div>
						</div>
						
						<div class="div-table-row">
							<div class="div-table-col" style="width: 16%;"></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patFirstName"  maxlength="33" tabindex="1"
										  onkeyup="multilingualConversion(this,document.getElementById('patFirstNameInMultiLangId'));">
								</s:textfield>
							</div>
							<!-- <div class="div-table-col label" style="width: 16%;">Patient Middle Name</div> -->
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patMiddleName"  maxlength="33" tabindex="2"
										  onkeyup="multilingualConversion(this,document.getElementById('patMiddleNameInMultiLangId'));">
								</s:textfield>
							</div>
							<!-- <div class="div-table-col label" style="width: 16%;">Patient Last Name</div> -->
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patLastName"  maxlength="33" tabindex="2"
										  onkeyup="multilingualConversion(this,document.getElementById('patLastNameInMultiLangId'));">
								</s:textfield>
							</div>
							<s:if test="%{isImageUploaded == 1}">
							<div align="right" class="div-table-col control" style="width: 16%;">
		 						<img class="button" style="cursor:pointer" src='../../hisglobal/images/view.psd.1.gif' alt="View Photo" title="View Photo" 
								onclick="show(event);"/>
		 					</div>
		 					</s:if>
						</div>
						
						<div class="div-table-row" style="display:none;">
							<div class="div-table-col" style="width: 16%;"></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield  name="patFirstNameInMultiLang" id="patFirstNameInMultiLangId" maxlength="33" tabindex="1"
											onblur="callOnBlur();" onfocus="callOnClick();">
								</s:textfield>
							</div>
							<!-- <div class="div-table-col label" style="width: 16%;">Patient Middle Name</div> -->
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield  name="patMiddleNameInMultiLang" id="patMiddleNameInMultiLangId"  maxlength="33" tabindex="2"
											onblur="callOnBlur();" onfocus="callOnClick();">
								</s:textfield>
							</div>
							<!-- <div class="div-table-col label" style="width: 16%;">Patient Last Name</div> -->
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield  name="patLastNameInMultiLang" id="patLastNameInMultiLangId" maxlength="33" tabindex="2"
											onblur="callOnBlur();" onfocus="callOnClick();">
								</s:textfield>
							</div>
						</div>

						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;"><font color="red">*</font>
								<s:radio label="Age/Dob" name="isActualDob"	list="#{'0':'Age','1':'Dob'}" onclick="showDivAgeDob(this);setMotherValidRule();" value="isActualDob" />
							</div>
							 <div class="div-table-col" style="width: 84%" >
								<div id="divAge"  class="div-table">
									<div class="div-table-row">
										<div class="div-table-col " style="width: 100%;">
											<s:textfield id="patAgeId" name="patAge" maxlength="5" size="5" tabindex="1" onblur="setMotherValidRule();"></s:textfield>
											<select id="patAgeUnitId" name="patAgeUnit" style="width: 75px"  tabindex="1" onblur="setMotherValidRule();showDivAgeDob();"><option value="0">Select Value</option></select>
										</div>
									</div>
								</div>
								<div id="divDob">
								
									<div class="div-table-col control" id="" style="width: 16%;">
										<input id="patDOBId" type="text" name="patDOB" tabindex="1" onblur="setMotherValidRule();">
									</div>
									
								</div>
						
							</div>
						</div>

						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;">	<font color="red">*</font><s:text name="gender"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;" list="#{'0':'Select Value'}" name="patGenderCode" tabindex="1" value="patGenderCode" />
								<input name="patGender" type="hidden">
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="maritalStatus"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;" list="#{'0':'Select Value'}" name="patMaritalStatusCode"  tabindex="2" value="patMaritalStatusCode" />
							</div>
						</div>

		
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="fathersName"/></div>
								<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patGuardianName"  maxlength="60" tabindex="1">
								</s:textfield>
							</div>
							<div class="div-table-col" style="width: 16%;"> 
								<div class="div-table">
								<div class="div-table-row">
									<div class="div-table-col" style="width: 25%;display: inline;" align="left">
									(OR)&nbsp;</div>
									<div class="div-table-col label" style="width: 75%;display: inline;">
									<s:text name="husbandName"/></div>
								</div>
								</div>
							</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patHusbandName" maxlength="60" tabindex="1" >
								</s:textfield>
							</div>
							<div class="div-table-col" style="width: 16%;"> 
								<div class="div-table">
								<div class="div-table-row">
									<div class="div-table-col" style="width: 25%;display: inline;" align="left">
									(OR)&nbsp;</div>
									<div class="div-table-col label" style="width: 75%;display: inline;">
									<s:text name="motherName"/></div>
								</div>
								</div>
							</div>				
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patMotherName"  maxlength="60" tabindex="1">
								</s:textfield>
							</div>
						</div>
						
						<div class="div-table-row">

							<div class="div-table-col label" style="width: 16%;"><s:text name="patCaste"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patCasteCode"
									value="patCasteCode"  tabindex="2" />
								<input name="patCaste" type="hidden">
							</div>

							<div class="div-table-col label" style="width: 16%;"><s:text name="religion"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select  cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patReligionCode"
									value="patReligionCode"  tabindex="2" />
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="birplace"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patBirthPlace"  maxlength="50"  tabindex="2"  />
							</div>

						</div>
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;"><s:text name="patOccupation"/>
							</div>
							<div class="div-table-col control" style="width: 16%;">
								<select name="patOccupation" id="patOccupationId"  tabindex="2"
									style="width: 145px">
									<option value="0">Select Value</option>
								</select>
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="monthlyIncome"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patMonthlyIncome"  maxlength="11"  tabindex="2" >
								</s:textfield>
							</div>
							<div class="div-table-col label" style="width: 16%;"></div>
							<div class="div-table-col control" style="width: 16%;">
								<%-- <s:textfield name="patAmountCollected" size='10' maxlength="50" readonly="readonly">
							</s:textfield> --%>
							</div>
						</div>
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;">
								<s:text name="adhar"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patNationalId" maxlength="12"  tabindex="2">
								</s:textfield>
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="otherId"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patDocType" id="patDocTypeId"
									value="patDocType"  tabindex="2" />
									<input type="hidden" name="patDocTypeName"  />
							</div>
							<div id= "divCardNoId" class="div-table-col control" style="width: 32%;">
								<div class="div-table">
									<div class="div-table-row">
										<div class="div-table-col label" style="width: 50%;"><s:text name="global.cardno"/>
											</div>
										<div   class="div-table-col control" style="width: 50%;">
											<s:textfield name="patCardNo"   tabindex="2" >
											</s:textfield>
										</div>
									</div>
								</div>
							</div>

						</div>

			<div class="div-table-row">
			<%-- 	<div class="div-table-col label" style="width: 16%;">Visit Reason :</div>
				<div class="div-table-col control" style="width: 16%;">
					<s:textarea label="reason" name="patVisitReason" cols="15" rows="1"/>
				</div>
				<div class="div-table-col" style="width: 64%;"></div> --%>
			
			<div class="div-table-col label" style="width: 16%;"><s:text name="identitymark1"/></div>
			<div class="div-table-col control" style="width: 16%;">
			<s:textfield name="patIdMark1" maxlength="50"  tabindex="2" />
			</div>
			
			<div class="div-table-col label" style="width: 16%;"><s:text name="identitymark2"/></div>
			<div class="div-table-col control" style="width: 16%;">
				<s:textfield name="patIdMark2" maxlength="50"  tabindex="2" />
			</div>
			</div>

					</div>



			 	<div class='div-table'>
					<div class="div-table-row title">
						<div class="div-table-col " style="width: 36%;">
							<s:text name="address" />&nbsp;<s:text name="detail" />
						</div>
					<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="country"/> 
					</div>
					<div class="div-table-col control" style="width: 16%;">
						
							<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patAddCountryCode" id="patAddCountryCodeId"  tabindex="1"
									value="patAddCountryCode" />
						<input name="defaultpatAddCountryCode" type="hidden">
						<input name="patAddCountry" type="hidden">
					</div>
					<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="state"/> 
					</div>
					<div id="divStateComboId" class="div-table-col control" style="width: 16%;">
						<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patAddStateCode" id="patAddStateCodeId"  tabindex="1"
									value="patAddStateCode" />
						<input name="patAddState"  type="hidden">
					</div>
					<div id="divStateTextId" class="div-table-col control" style="width: 16%; display: none;">
							<s:textfield name="patAddState" maxlength="50"  tabindex="1" />
					</div>			
				    </div>
				</div>
						
	<div class="div-table">

					
				<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;"><s:text name="hno"/></div>
						<div class="div-table-col control" style="width: 16%;">
						<s:textfield name="patAddHNo" size='10' maxlength="60"  tabindex="2">
							</s:textfield>
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="street"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddStreet" maxlength="60"   tabindex="2" />
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="location"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddCityLoc" maxlength="60"  tabindex="2" />
						</div>

					</div>
					<div class="div-table-row">
					
					
					<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="district"/></div>
				<div id="divDistrictComboId" class="div-table-col control" style="width: 16%;">
					
					<% System.out.println("dffsaff"); %>
					<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patAddDistrictCode" id="patAddDistrictCodeId"  tabindex="1"
									value="patAddDistrictCode" />
					<input name="patAddDistrict" type="hidden">
					<input name="defaultpatAddDistrictCode" type="hidden">
				</div>
				<div id="divDistrictTextId" class="div-table-col control" style="width: 16%; display: none;">
					<s:textfield name="patAddDistrict" maxlength="15"   tabindex="1"/>
				</div>
				
				
						
						<div class="div-table-col label" style="width: 16%;"><s:text name="city"/>&nbsp;<s:text name="slash"/>&nbsp;<s:text name="village"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddCity" maxlength="60"  tabindex="2" />
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="pin"/>
							</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddPIN" maxlength="6"  tabindex="2" />
						</div>

					</div>
					<div class="div-table-row" >
						<div class="div-table-col label" style="width: 16%;"><s:text name="global.landmark"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddLandMarks" maxlength="60"  tabindex="2" />
						</div>
						
						<div class="div-table-col label" style="width: 16%;"><s:text name="areaCategory"/>  </div>
						<div class="div-table-col control" style="width: 16%;">
							<s:select cssStyle="width: 145px;"  list="#{'-1':'Select Value'}" name="patIsUrban"  tabindex="2"
									value="patIsUrban" />
						</div>
						
						<div class="div-table-col label" style="width: 16%;"><s:text name="global.email"/></div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddEmailId" maxlength="70"  tabindex="2"/>
						</div>
						
					</div>
					<div class="div-table-row">

						<div class="div-table-col label" style="width: 16%;"><s:text name="global.phone"/>&nbsp;<s:text name="number"/>
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddPhoneNo" maxlength="15"  tabindex="2" />
						</div>

						<div class="div-table-col label" style="width: 16%;"><s:text name="global.phone"/>&nbsp;<s:text name="owner"/>
							</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:select list="#{'1':'Self (patient)','3':'Other','5':'Family member'}" name="patAddPhoneOwner"  tabindex="2"
								value="patAddPhoneOwner" />
						</div>

						<div class="div-table-col label" style="width: 16%;"><s:text name="mobileNo"/>
							</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddMobileNo" maxlength="10"  tabindex="1" />
						</div>
					</div>
				
				
					
					</div>



	<div class="div-table" >
				<div class="div-table-row title">
					<div class="div-table-col" style="width: 50%">
						<s:text name="brought"/>&nbsp;<s:text name="by"/>&nbsp;<s:text name="global.detail"/>
					</div>
					<div class="div-table-col label" style=" width: 30%" align="right">
							<div style="display:none"  id="isUnknownId"><s:text name="brought"/>&nbsp;<s:text name="by"/>&nbsp;<s:text name="global.detail"/>&nbsp;<s:text name="global.is"/>&nbsp;<s:text name="compulsory"/></div>
					</div>
					
					<div class="div-table-col label" style="width: 20%">
							 <s:text name="global.is"/>&nbsp;<s:text name="brought"/>&nbsp;<s:text name="by"/> <input type="checkbox" name="isBroughtBy" value="0" tabindex="1" onclick="broughtBy(this)">
					</div>
				</div>
		</div>
	
		<div id="broughtById">
						<div class="div-table">
							<div class="div-table-row ">
								<div class="div-table-col label" style="width: 25%;">	
       							   <font color="#FF0000" size="2" >*</font>
	          						<s:text name="brought"/>&nbsp;<s:text name="by"/>
	                 			</div>
	    
								<div class="div-table-col control" style="width: 25%;">	
						        	<s:select list="#{'-1':'Select Value','1':'Relative','2':'Police','3':'Other'}" name="isRelative"  tabindex="1"
										value="isRelative" onchange="enableRelation(this)" />
								</div>
								
								
								<div class="div-table-col label" style="width: 25%;">	
       							   <font color="#FF0000" size="2" ></font>
	          						<s:text name="relationship"/>
	                 			</div>
	    
								<div class="div-table-col control" style="width: 25%;">	
								<s:select list="#{'-1':'Select Value'}" name="broughtByRelationCode" id="broughtByRelationCodeId" tabindex="1"
										value="broughtByRelationCode"  disabled="disabled" />
								</div>
							</div>
							<div class="div-table-row ">
								<div class="div-table-col label" style="width: 25%;">	
    							   <div id="nameBroughtBy">
       							   <font color="#FF0000" size="2" ></font>
	          						<s:text name="global.name"/>
	    							</div>      
	                 			</div>
	    
								<div class="div-table-col control" style="width: 25%;">	
								<s:textfield name="broughtByName" maxlength="90" tabindex="1" ></s:textfield>
								</div>
								<div class="div-table-col label" style="width: 25%;">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif"></font>
									<s:text name="brought"/>&nbsp;<s:text name="fromLocation"/>
								</div>
								<div class="div-table-col control" style="width: 25%;">
								<s:textfield name="broughtByLocation" maxlength="100" tabindex="1" ></s:textfield>	<!-- 3 -->
								</div>
	                 		</div>
         				</div>
         		
      <div id="policeDetailDiv" style="display: none">
	    	<div class="div-table">
	    		<div class="div-table-row ">
								<div class="div-table-col label" style="width: 25%;">	
    							   <div id="nameBroughtBy">
       							   <font color="#FF0000" size="2" ></font>
	          						<s:text name="designation"/>
	    							</div>      
	                 			</div>
	    
								<div class="div-table-col control" style="width: 25%;">	
									<s:textfield name="constableDesig" maxlength="50" tabindex="1" ></s:textfield>
								</div>
								<div class="div-table-col label" style="width: 25%;">	       	
								   <font color="#FF0000" size="2" ></font>
	          						<s:text name="badgeno"/>
	                 			</div>
	    
								<div class="div-table-col control" style="width: 25%;">	
									<s:textfield name="constableBadgeNo" maxlength="50" tabindex="1" ></s:textfield>
								</div>	
									
				</div>
			</div>		
		  </div>
		    <div id="phoneBroughtByDiv">
		   	<div class="div-table" >
	    		<div class="div-table-row width100">
								<div class="div-table-col label" style="width: 25%;">	
       							   <font color="#FF0000" size="2" ></font>
	          						<s:text name="mobileNo"/> 
	                 			</div>
	    
								<div class="div-table-col control" style="width: 25%;">	
								<s:textfield name="broughtByPhone" maxlength="10" tabindex="1" ></s:textfield>	
								</div>
								<div class="div-table-col label" style="width: 25%;">	
       							   <font color="#FF0000" size="2" ></font>
	          						 <s:text name="address"/> 
	                 			</div>
	    
								<div class="div-table-col control" style="width: 25%;">	
								<s:textarea label="Address" name="broughtByAddress" tabindex="1"  cols="15" rows="1"/>
								
								</div>	
									
				</div>
			</div>		
			</div>
			
			 <div id="policeStnDiv" style="display: none">
	    	<div class="div-table">
	    		<div class="div-table-row ">
								<div class="div-table-col label" style="width: 25%;">	       							 
									<!--By Mukund on 31.01.2017  -->
	          						<font color="#FF0000" size="2" ></font><s:text name="policestation"/>
	                 			</div>
								<div class="div-table-col control" style="width: 25%;">
									<!-- <input name="policeStation" maxlength="50" type="text" cssClass="alphainput"> -->
									<s:textfield name="policeStation" maxlength="50" tabindex="1"></s:textfield>
								</div>
							
									
				</div>
			</div>		
			
		  </div>
		  <div class="div-table">
		  	<div class="div-table-row"> 
		        <div class="div-table-col label" style="width: 25%;">Death Declared By</div>
				<div class="div-table-col control" style="width: 25%;">
					<s:select name="broughtByDeclared" list="#{'0':'Select Value'}" tabindex="2" id="broughtByDeclaredId" class="select77prcnt" onchange="broughtDeadConsultant(this)" value="broughtByDeclared" > </s:select> 
					   <input name="broughtByConsultant" value='<s:property value="broughtByConsultant"/>' type="hidden">
			    </div>
			</div>
		</div>
			</div>

			</div>
				<div class="div-table-button">
					<div class="div-table-row footerBar">
						<div class="div-table-col"></div>
					</div>
					<div class="div-table-row emptyBar">
						<div class="div-table-col"></div>
					</div>
					
					<div class="div-table-row" align="center">
					 <div id="divAfterGoId" style="display:none">  
				 	<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
					<a id="#" class="button"	onclick="submitClearAction('EmgPatDetailMod');"><span class="clear"><s:text name="clear"/></span></a> 
					<a href="#" class="button" onclick="submitCancelAction('EmgPatDetailMod');"><span class="cancel"><s:text name="cancel"/></span></a>
					</div> 
					 <div id="divGoId" >  
						<a id="#" class="button"	onclick="submitClear('EmgPatDetailMod');"><span class="clear"><s:text name="clear"/></span></a> 
						<a href="#" class="button" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>
					</div>
					</div>
				</div>


		
			<s:hidden name="AfterGo" value="%{AfterGo}" />
			<s:hidden name="addModify" />
			<s:hidden name="tempIsActualDOB" />
			<s:hidden name="defaultPatAge"></s:hidden>
			<s:hidden name="defaultPatAgeUnit"></s:hidden>
			<s:hidden name="defaultPatDOB"></s:hidden>
			<input type="hidden" name="processID" value="11"/>
			<input type="hidden" name="filname"  value='<s:property value="imageFileName"/>'/>
			<input type="hidden" name="crNo" value='<s:property value="patCrNo"/>'/>
			<s:hidden name="isImageStoredFTP"></s:hidden>
			<s:hidden name="patPrimaryCatCode"></s:hidden>
			<s:hidden name="seniorCitizenAgeLimit" ></s:hidden>
			<s:hidden name="seniorCitizenCatCode" ></s:hidden>
						
			</div>
			<cmbPers:cmbPers></cmbPers:cmbPers>
			<s:token></s:token>
		</s:form>

			<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col"> 
						<div class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="errorMessage" /></div>
					</div>
				</div>
			</div>
	<div class="div-table-simple" id="fatherorSpouseError" style="display: none">
		<div class="div-table-row">
			<div  class="div-table-col alignLeft fontError" style="width: 100%">Father Name Or Spouse Name Is Compulsory</div>
		</div>
	</div>
		<div class="div-table-simple" id="validateDocError" style="display: none">
		<div class="div-table-row">
			<div  class="div-table-col alignLeft fontError" style="width: 100%">Atleast one Verification Document is required</div>
		</div>
	</div>

		<script type="text/javascript"
			src="./../../registration/transactions/js/emgPatDtlModification.js" /></script>
	</center>
</body>
</html>