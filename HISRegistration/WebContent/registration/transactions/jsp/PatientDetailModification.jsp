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

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>
<script language="JavaScript"	src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script type="text/javascript"	src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript"	src="./../../registration/transactions/js/popup.js"></script>
<script type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>
<script language="Javascript" type="text/javascript" src="/HIS/hisglobal/js/moment.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<style type="text/css">
@media print {
	#nonprintableDiv1 {
		display: none;
	}
	#nonprintableDiv2 {
		display: none;
	}
	#nonprintableDiv3 {
		display: none;
	}
}

.border .div-table-col {
	border: 1px solid black;
}
</style>
<title>Patient Detail Modification</title>
<s:head />
</head>
<script type="text/javascript">
var isAddressAdded  = <%=RegistrationConfig.IS_ADDRESS_ADDED%>;
var isAddressModified = <%=RegistrationConfig.IS_ADDRESS_MODIFIED%>;
var doc_Affidavit	= <%=RegistrationConfig.VERIFICATION_DOCUMENT_AFFIDAVIT%>;

var registrationDeskDefaultCountryCode = '<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>';
var LOCAL_LANGUAGE = "<%= (String)request.getSession().getAttribute(RegistrationConfig.LOCAL_LANGUAGE) %>";

$(window).on("load.loading1", function() 
{
	initMultilingual(LOCAL_LANGUAGE);
	if (document.getElementsByName("AfterGo")[0].value != '0') 
	{
		document.getElementById("divAfterGo").style.display = "";
		document.getElementById("divAfterGoId").style.display = "";
		
		document.getElementById("divGoId").style.display = "none";
		patDtlModification.fetchDefaultValues();
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
function submitForm(mode) 
{
	document.forms[0].action = mode + "PatientDetailMod.action";
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
function upload(e)
{
	//var processID = document.getElementsByName("processID")[0].value;
	//var crNo = document.getElementsByName("crNoHosCode")[0].value;
	//var crNo = document.getElementsByName("crNo")[0].value;
	//var filname = document.getElementsByName("filename")[0].value;
	//var filname = document.getElementsByName("filname")[0].value;
	//var url = "/HISGlobal/hisglobal/filetransfer/UploadFile.action?strProcessId="+processID+"&strCRNoHospCode="+crNo+"&strFileName="+filname;
	//var url = "../../registration/transactions/UploadFile.action?strProcessId="+processID+"&strCRNoHospCode="+crNo+"&strFileName="+filname;
	/* if($('[name="arrSelectedVerifyDocs"]')[0].value==''){
		alert("Please select verification documents first");
		return false;
	} */
	/* else{
		alert("docs");
		var verificationDocValue=$('[name="arrSelectedVerifyDocs"]')[0].value;
		alert(cer);
		return false;
	} */
	
	/* alert($('[name="arrSelectedVerifyDocs"]')[0].value);
	var verificationDocValue=$('[name="arrSelectedVerifyDocs"]')[0].value;
	var isMultiple="1"; */
	var url = "../../registration/transactions/UploadFile.action";
	//alert(url);
	openURLInPopup(url,550,160,0,0);
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
	enableRelation();
	/*Modify Date			: 5thDec'14
	  Reason	(CR/PRS)	: Secondary UHID check incorporation
	  Modify By				: Sheeldarshi */
	showpopuponload();
	//End:Sheeldarshi
	if (document.getElementsByName("AfterGo")[0].value != '0') 
	{
	showDivAgeDob();
	$('[name="patAge"]').val($('[name="defaultPatAge"]').val());
	$('[name="patAgeUnit"]').val($('[name="defaultPatAgeUnit"]').val());
	//$("#patDOBId").val("");
	setMotherValidRule();
	}
}

function enableRelation() 
{
	var obj = document.getElementsByName("requestBy")[0];
	if (!obj)
		return;
	if (obj.value == "1")
		document.getElementsByName("requestRelation")[0].disabled = false;
	else	
		{
		$('[name="requestRelation"]')[0].value="0";
		document.getElementsByName("requestRelation")[0].disabled = true;
		}
		
	if (obj.value == "1") {
		document.getElementById("policeDetailDiv").style.display = "none";
		document.getElementById("requesterDIV").style.display = "block";
		document.getElementById("relationshipSelfId").style.display = "none";
		document.getElementById("relationshipRelativeId").style.display = "block";
	} else if (obj.value == "2") {
		document.getElementById("policeDetailDiv").style.display = "block";
		document.getElementById("requesterDIV").style.display = "block";
	} else {
		document.getElementById("policeDetailDiv").style.display = "none";
		document.getElementById("requesterDIV").style.display = "none";
		document.getElementById("relationshipSelfId").style.display = "block";
		document.getElementById("relationshipRelativeId").style.display = "none";
	}

}

</script>
<body >
	<center>

		<s:actionerror />

		<s:form action="PatientDetailModificationAction">
			<s:set name="theme" value="simple" scope="page" />
			<div class="wrapper rounded ">
				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100 "><s:text name="global.patient"/>&nbsp;<s:text name="detail"/>&nbsp;<s:text name="modification"/></div>
					</div>
				</div>

				<his:InputCrNoTag name="PatienModFB"> </his:InputCrNoTag>
				
				<div id="divAfterGo" style="display: none">
					<div class="div-table">
						<div class="div-table-row title">
							<div class="div-table-col"><s:text name="global.patient"/>&nbsp;<s:text name="details"/></div>
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
	
						</div>
						
						<div class="div-table-row"  style="display: none;">
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
								<s:textfield  name="patLastNameInMultiLang" id="patLastNameInMultiLangId" maxlength="33" readonly="true" tabindex="2"
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
											<select id="patAgeUnitId" name="patAgeUnit" style="width: 75px" tabindex="1" onblur="setMotherValidRule();showDivAgeDob();"><option value="0">Select Value</option></select>
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
							<%--Modify Date			: 5thDec'14
							  Reason	(CR/PRS)	: Secondary UHID check incorporation
				  				Modify By				: Sheeldarshi 
								 <s:select cssStyle="width: 145px;" list="#{'0':'Select Value'}" name="patGenderCode"		value="patGenderCode" /> --%>
								<select name="patGenderCode" id="patGenderCodeId" tabindex="1" class="select77prcnt">
							
								<option value="-1">Select Value</option>
							</select> 
								<input name="patGender" type="hidden">
								<input name="defaultpatGenderCode" id="defaultpatGenderCodeId"
								type="hidden" value="-1"> <input name="defaultpatGender"
								id="defaultpatGenderId" type="hidden">
									<!-- End:Sheeldarshi -->
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="maritalStatus"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;" list="#{'0':'Select Value'}" name="patMaritalStatusCode" value="patMaritalStatusCode" />
								<input name="patMaritalStatus" type="hidden">
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
								<s:textfield name="patHusbandName" maxlength="60" tabindex="1">
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

							<div class="div-table-col label" style="width: 16%;"><s:text name="patCaste"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patCasteCode"
									value="patCasteCode" tabindex="2"/>
								<input name="patCaste" type="hidden">
							</div>

							<div class="div-table-col label" style="width: 16%;"><s:text name="religion"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select  cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patReligionCode"
									value="patReligionCode" tabindex="2" />
								<input name="patReligion" type="hidden">								
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="birplace"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patBirthPlace"  maxlength="50"  tabindex="2"/>
							</div>

						</div>
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;"><s:text name="patOccupation"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<select name="patOccupation" id="patOccupationId" tabindex="2"
									style="width: 145px">
									<option value="0">Select Value</option>
								</select>
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="monthlyIncome"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patMonthlyIncome"  maxlength="10" tabindex="2">
								</s:textfield>
							</div>
							<div class="div-table-col label" style="width: 16%;"></div>
							<div class="div-table-col control" style="width: 16%;">
								<%-- <s:textfield name="patAmountCollected" size='10' maxlength="50" readonly="readonly">
							</s:textfield> --%>
							</div>
						</div>
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;"><s:text name="adhar"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patNationalId" maxlength="12" tabindex="2">
								</s:textfield>
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="otherId"/>
							</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patDocType" id="patDocTypeId" tabindex="2"
									value="patDocType" />
									<input type="hidden" name="patDocTypeName"  />
							</div>
							<div id= "divCardNoId" class="div-table-col control" style="width: 32%;">
								<div class="div-table">
									<div class="div-table-row">
										<div class="div-table-col label" style="width: 50%;"><s:text name="global.cardno"/>
											</div>
										<div  class="div-table-col control" style="width: 50%;">
											<s:textfield name="patCardNo" tabindex="2" >
											</s:textfield>
										</div>
									</div>
								</div>
							</div>

						</div>



					</div>



				<div class="div-table">
					<div class="div-table-row title">
					
						<div class="div-table-col" style="width: 20%;">
							<div align="left">
									<s:text name="addressType" />
							</div>
						</div>
						<div class="div-table-col" style="width: 50%;">
								<div align="left">
									<s:text name="address" />
								</div>
						</div>
							<div class="div-table-col label" style="width: 30%;">
								<img class="button"
									src='<his:path src="/hisglobal/images/plus.png"/>'
									style="cursor: pointer"
									onclick="patDtlModification.setAddType(this,'2');"
									onkeypress="if(event.keyCode==13) patDtlModification.setAddType(this,'2');">
							</div>
					</div>

						<s:iterator value="#session.arrAddressVO" status="key">
						
							<div class="div-table-row ">
								<div class="div-table-col" style="width: 20%;">
									<div align="left"><!--By Mukund on 29.08.2016  -->
										<%-- <input type="radio" name="patFBAddTypeCode"
											value="<s:property value='%{#session.arrAddressVO[#key.index].patAddTypeCode}'/>"
											onclick="patDtlModification.setSelectedAddress(this)" />
										<s:property value="patAddType" /> --%>
										<s:if test="patAddTypeCode!=2" >
											<!--By Mukund on 11.11.2016 Inside IF  -->
											<s:if test=" #session.arrAddressVO[#key.index].patAddTypeCode == 1 "><!--pat AddTypeCode is 1 for Current Address  -->
												<input type="radio" name="patFBAddTypeCode" value="<s:property value='%{#session.arrAddressVO[#key.index].patAddTypeCode}'/>"
												onclick="patDtlModification.setSelectedAddress(this)" checked="checked"/>
												<s:property value="patAddType" />
											</s:if>
											<s:else>
												<input type="radio" name="patFBAddTypeCode" value="<s:property value='%{#session.arrAddressVO[#key.index].patAddTypeCode}'/>"
												onclick="patDtlModification.setSelectedAddress(this)"/>
												<s:property value="patAddType" />
											</s:else>
										</s:if>
										<s:else>
											<!--Inside ELSE  -->
											<input type="radio" name="patFBAddTypeCode" value="<s:property value='%{#session.arrAddressVO[#key.index].patAddTypeCode}'/>"
											onclick="patDtlModification.setSelectedAddress(this)"/>
											<s:property value="patAddType" />
										</s:else><!-- End:Mukund  -->
										
									</div>
								</div>



								<div class="div-table-col" style="width: 80%;">
									<div align="left">
										<s:if test="patAddHNo!=''">
											<s:if test="patAddHNo!=null && patAddHNo!='' ">
												<s:property value="patAddHNo" />,
											</s:if>
										</s:if>
										<s:if test="patAddStreet!=null && patAddStreet!='' ">
											<s:if test="patAddStreet!=null">
												<s:property value="patAddStreet" />,
											</s:if>
										</s:if>
										<s:if test="patAddCityLoc!=null && patAddCityLoc!='' ">
											<s:if test="patAddCityLoc!=null">
												<s:property value="patAddCityLoc" />,
											</s:if>
										</s:if>
										<s:if test="patAddCity!=null && patAddCity!='' ">
											<s:if test="patAddCity!=null">
												<s:property value="patAddCity" />,
											</s:if>
										</s:if>
										<s:if test="patAddDistrictMstValue!=null && patAddDistrictMstValue!=''">
											<s:if test="patAddDistrictMstValue!=null">
												<s:property value="patAddDistrictMstValue" />,
											</s:if>
										</s:if>
										<s:if test="patAddPIN!=null && patAddPIN!='' && patAddPIN!='0' ">
											<s:if test="patAddPIN!=null">
												<s:property value="patAddPIN" />,
											</s:if>
										</s:if>
																				
										<s:if test="patAddState!=''&& patAddState!=null">
											<s:property value="patAddState" />,
										</s:if>
										<s:if test="patAddCountry!=''">
											<s:if test="patAddCountry!=null">
												<s:property value="patAddCountry" />
											</s:if>
										</s:if>
										
										
									</div>
								</div>
							</div>
						</s:iterator>
					</div>








					<div id="AddModdivIf" style="display: none">
						<%
							{
									String divdisplay = "\"\"";
									String divnddisplay = "\"\"";
						%>
						<div class="div-table">
							<div class="div-table-row title">
								<div class="div-table-col" style="width: 60%;">
									<div align="left">
										<s:text name="address" />
										<s:text name="modification" /><s:text name="slash" /><s:text name="addition" />
										<s:text name="detail" />
									</div>
								</div>
								<div class="div-table-col" style="width: 40%;">
									<div id="sameCurAddId" align="right">
											<input type="checkbox" name="patSameAsCurrentAddCheck"
											value="<s:property value='%{#session.arrAddressVO[0].patAddTypeCode}'/>"
											onchange="patDtlModification.setSameCurrentAddress(this)"/>
											<s:text name="Same As Current Address"></s:text>
									</div>
								</div>
							</div>
						</div>




						<div class="div-table">
							<s:if test="isAddressDelhi=='0'">
								<%
									divdisplay = "none";
												divnddisplay = "";
								%>
							</s:if>
							<s:if test="isAddressDelhi=='1'">
								<%
									divdisplay = "";
												divnddisplay = "none";
												//System.out.println("divnddisplay" + divnddisplay);
								%>
							</s:if>

							<div class="div-table-row ">
								<div class="div-table-col label" style="width: 16%;">
									<div align="right">
										<font color="#FF0000" size="1"
											face="Verdana, Arial, Helvetica, sans-serif">*</font>
										<s:text name="address" />
										<s:text name="type" />
									</div>
								</div>
								<div class="div-table-col control" style="width: 16%;"
									id="addTypeCombo">
									<select name="patAddTypeCode" style="width: 145px" tabindex="1">
										<option value="-1">Select Value</option>
									</select>
								</div>
								<div class="div-table-col control" style="width: 16%;"
									id="addTypeText">
									<input name="patAddTypeLable" maxlength="50" type="text"
										readonly="readonly">
								</div>
								<div class="div-table-col label" style="width: 16%;">
									<div align="right">
										<font color="#FF0000" size="1"
											face="Verdana, Arial, Helvetica, sans-serif">*</font>
										<s:text name="Country" />
									</div>
								</div>
								<div class="div-table-col control" style="width: 16%;">
									<div id="patCountryCombo">
										<select name="patAddCountryCode" id="patAddCountryCodeId" tabindex="1"
											style="width: 145px">
											<option value="0">Select Value</option>
										</select> <input name="defaultpatAddCountryCode" type="hidden">
										<input name="patAddCountry" type="hidden">
									</div>
								</div>

								<div class="div-table-col label" style="width: 16%;">
									<div id="stateMandotary" style="" align="right">
										<font color="red">*</font>
										<s:text name="State" />
									</div>

								</div>
								<div class="div-table-col control" style="width: 16%;">
									<div id="divStateComboId" align="left">
										<select name="patAddStateCode" id="patAddStateCodeId" tabindex="1" 
											style="width: 145px">
											<option value="0">Select Value</option>
										</select> <input name="patAddState" type="hidden">
									</div>
									<div id="divStateTextId" style="" align="left">
										<s:textfield name="patAddStateName"  maxlength="50"
											onkeypress="return validateAlphabetsWithDotsOnly(event,this);" />
									</div>
								</div>
							</div>


							<div class="div-table-row">
								<div class="div-table-col label" style="width: 16%;"><s:text name="hno" /></div>
								<div class="div-table-col control" style="width: 16%;">
									<input name="patAddHNo" maxlength="60" type="text" tabindex="2">
								</div>
								<div class="div-table-col label" style="width: 16%;"><s:text name="street" />
								</div>
								<div class="div-table-col control" style="width: 16%;">
									<input name="patAddStreet" maxlength="60" type="text" tabindex="2" >
								</div>
								<div class="div-table-col label" style="width: 16%;"><s:text name="location" />
								</div>
								<div class="div-table-col control" style="width: 16%;">
									<input name="patAddCityLoc" maxlength="60" type="text" tabindex="2" >
								</div>

							</div>
							<div class="div-table-row">


								<div class="div-table-col label" style="width: 16%;">
									<font color="red">*</font><s:text name="district" />
								</div>
								<div id="divDistrictComboId" class="div-table-col control"
									style="width: 16%;">
									<select name="patAddDistrictCode" id="patAddDistrictCodeId" tabindex="1"
										style="width: 145px">
										<option value="">Select Value</option>
									</select> <input name="patAddDistrict" type="hidden">
								</div>
								<div id="divDistrictTextId" class="div-table-col control"
									style="width: 16%; display: none;">
									<input name="patAddDistrict" maxlength="50" type="text">
								</div>



								<div class="div-table-col label" style="width: 16%;"><s:text name="city" /><s:text name="slash" /><s:text name="village" />
								</div>
								<div class="div-table-col control" style="width: 16%;">
									<input name="patAddCity" maxlength="60" type="text" tabindex="2">
								</div>
								<div class="div-table-col label" style="width: 16%;"><s:text name="pin" />
									</div>
								<div class="div-table-col control" style="width: 16%;">
									<input name="patAddPIN" maxlength="6" type="text" tabindex="2">
								</div>

							</div>
							<div class="div-table-row">
								<div class="div-table-col label" style="width: 16%;"><s:text name="global.landmark" />
								</div>
								<div class="div-table-col control" style="width: 16%;">
									<input name="patAddLandMarks" maxlength="60" type="text" tabindex="2">
								</div>

								<div class="div-table-col label" style="width: 16%;"><s:text name="areaCategory" />
								</div>
								<div class="div-table-col control" style="width: 16%;">
									<select name="patIsUrban" style="width: 145px" tabindex="2">
										<!-- <option value="-1">Select Value</option> -->
									</select>
								</div>

								<div class="div-table-col label" style="width: 16%;"><s:text name="global.email" />
								</div>
								<div class="div-table-col control" style="width: 16%;">
									<input name="patAddEmailId" maxlength="254" type="text" tabindex="2">
								</div>

							</div>
							<div class="div-table-row">

								<div class="div-table-col label" style="width: 16%;"><s:text name="global.phone" />&nbsp;<s:text name="number" />
									</div>
								<div class="div-table-col control" style="width: 16%;">
									<input name="patAddPhoneNo" maxlength="15" type="text" tabindex="2">
								</div>

								<div class="div-table-col label" style="width: 16%;"><s:text name="global.phone" />&nbsp;<s:text name="owner" />
									</div>
								<div class="div-table-col control" style="width: 16%;">
									<select name="patAddPhoneOwner" style="width: 145px" tabindex="2">
										<option value="1">Self (patient)</option>
										<!-- <option value="2">ANM</option> -->
										<option value="3">Other</option>
										<!-- <option value="4">Neighbour</option> -->
										<option value="5">Family member</option>
									</select>
								</div>

								<div class="div-table-col label" style="width: 16%;"><s:text name="mobileNo" />
									</div>
								<div class="div-table-col control" style="width: 16%;">
									<input name="patAddMobileNo" maxlength="10" type="text" tabindex="2">
								</div>
							</div>
							
							<div class="div-table-row">
								<div class="div-table-col label" style="width: 16%;"><s:text name="emergency" />&nbsp;<s:text name="contactNo" /></div>
								<div class="div-table-col control" style="width: 16%;">
									<input name="patEmgCntNo" maxlength="10" class="input75prcnt" tabindex="1" >
								</div>
							</div>
						
							<s:hidden name="patAddDistrictMstValue" value="%{patAddDistrict}"/>
							<s:hidden name="patVerificationStatus" value="%{patVerificationStatus}"/>
							<s:hidden name="patAddType" value="%{patAddType}"/>

						</div>

						<%
							}
						%>
					</div>

					<div class="div-table">
						<div class="div-table-row title">
							<div class="div-table-col" style="width: 32%;">
								<s:text name="modification" />&nbsp;<s:text name="requestedby" /></div>
						</div>
						<div class="div-table-row ">
							<div class="div-table-col label" style="width: 25%;">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<s:text name="requestBy" />
							</div>


							<div class="div-table-col control" style="width: 25%;">
								<s:select
									list="#{'-1':'Select Value','0':'Self','1':'Relative'}"
									name="requestBy" value="requestBy" tabindex="1" onchange="enableRelation()" />

							</div>


							<!--//////////////////////////////add div for mcl no///////////////////  -->
							<div class="div-table-col label" style="width: 25%;">
								<div align="right" id="relationshipSelfId">
									<s:text name="Relationship" />
								</div>
								<div align="right" id="relationshipRelativeId">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<s:text name="Relationship" />
								</div>
							</div>
							<div class="div-table-col control" style="width: 25%;">
								<div id="relationshipRelativeId">
									<s:select list="#{'0':'Select Value'}" name="requestRelation"
										value="requestRelation"  tabindex="1" onchange="enableRelation()" />
								</div>
							</div>

						</div>
					</div>


					<div id="requesterDIV">
						<div class="div-table">
							<div class="div-table-row ">
								<div class="div-table-col label" style="width: 25%;">
									<!-- 16 -->
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<s:text name="requestName" />
								</div>
								<div class="div-table-col control" style="width: 25%;">
									<!-- 18 -->
									<input name="requestByName" maxlength="50" type="text" tabindex="1"
										onkeypress="return validateAlphabetsWithDotsOnly(event,this)"
										onblur="isAlpha(this,'Middle Name')" maxlength="60">
								</div>
								<div class="div-table-col label" style="width: 25%;">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<s:text name="requestByAddress" />
								</div>
								<div class="div-table-col control" style="width: 25%;">
									<!-- 3 -->
									<textarea name="requestByAddress" rows="1" cols="15" tabindex="1"></textarea>

								</div>

							</div>
						</div>
					</div>
					<div id="policeDetailDiv">
						<div class="div-table">
							<div class="div-table-row ">
								<div class="div-table-col" style="width: 25%;">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<s:text name="designation" />
								</div>
								<div class="div-table-col" style="width: 25%;">

									<input name="constableDesig" maxlength="50" type="text"
										onkeypress="return validateAlphaNumericWithDotsOnly(event,this)">
								</div>
								<div class="div-table-col" style="width: 25%;">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<s:text name="batchNo" />
								</div>

								<div class="div-table-col" style="width: 25%;">
									<input name="constableBadgeNo" maxlength="50" type="text"
										onkeypress="return validateAlphaNumericWithDotsOnly(event,this)">
								</div>
							</div>
						</div>
					</div>

					<div class="div-table">
						 <div class="div-table-row title">
							<div class="div-table-col" style="width: 30%;">
								<div align="left" class="TitleTagFontStyle" valign="middle">
									<font color="#FF0000">*</font> <s:text name="global.verificationDoc" /> <img
										class="button"
										src='<his:path src="/hisglobal/images/icon-vrf.png"/>'
										style="cursor: pointer; vertical-align: middle;"
										alt="Verification Documents" title="Verification Documents"
										onclick="openVerDocPopup(event);"
										onkeypress="if(event.keyCode==13) {openVerDocPopup(event);}"
										size='7' tabindex="1"> 
										<!-- onclick="openPopup('<his:path src="/registration/transactions/verificationDoc.action"/>',event,300,600);"
										onkeypress="if(event.keyCode==13) {openPopup('<his:path src="/registration/transactions/verificationDoc.action"/>',event,300,600)}" -->
										
								</div>
							</div>
							<div class="div-table-col" style="width: 40%;">
								<div align="left" class="TitleTagFontStyle"
									id="hiddenDivVerification">
									:: &nbsp; <font color="red"><s:text
											name="reqdVerificationDocument" /></font> <input type="hidden"
										name="arrSelectedVerifyDocs" value="" />
								</div>
							</div>
							 <div align="right" class="div-table-col" style="width: 30%;">
						 <s:text  name="Upload Verification Document"></s:text>
							<img class="button" style="cursor:pointer" src='../../hisglobal/images/add.gif'
							alt="Upload Doc" title="Upload Doc" onkeypress="if(event.keyCode==13) upload(event)" 
							onclick="upload(event)" >
							<img class="button" style="cursor:pointer" src='/HIS/hisglobal/images/A.png' alt="View Document" title="View Photo" 
							onclick="show(event);"/>
							
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
				 	<a href="#" class="button" id="submitId"><span class="save"><s:text name="save" /></span></a>
					<a id="#" class="button"	onclick="submitClearAction('PatientDetailMod');"><span class="clear"><s:text name="clear" /></span></a> 
					<a href="#" class="button" onclick="submitCancelAction('PatientDetailMod');"><span class="cancel"><s:text name="cancel" /></span></a>
					</div> 
					 <div id="divGoId" >  
						<a id="#" class="button"	onclick="submitClear('PatientDetailMod');"><span class="clear"><s:text name="clear" /></span></a> 
						<a href="#" class="button" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel" /></span></a>
					</div>
					</div>
				</div>


		
			<s:hidden name="AfterGo" value="%{AfterGo}" />
			<s:hidden name="addModify" />

			<s:hidden name="verificationDocumentAdded" />
			<s:hidden name="tempIsActualDOB" />
			
			<s:hidden name="patIsDead" value="%{patIsDead}"/>		
			<s:hidden name="isImageUploaded" value="%{isImageUploaded}"/>		
			<s:hidden name="IsUnknown" value="%{IsUnknown}"/>		
			<s:hidden name="mlcNo" value="%{mlcDetail}"/>	
			<s:hidden name="isAffidavitDocumentAdded" value="1"/>
			<s:hidden name="isDuplicatePatient" value="%{isDuplicatePatient}"/>
			<s:hidden name="processID"/>
			<s:hidden name="filname"/>
			<s:hidden name="crNo" value="%{patCrNo}"/>
			
			<s:hidden name="defaultPatAge"></s:hidden>
			<s:hidden name="defaultPatAgeUnit"></s:hidden>
			<s:hidden name="defaultPatDOB"></s:hidden>
			<s:hidden name="patPrimaryCatCode"></s:hidden>
			<s:hidden name="seniorCitizenAgeLimit" ></s:hidden>
			<s:hidden name="seniorCitizenCatCode" ></s:hidden>
			<s:hidden name="selectedCRToVisit" ></s:hidden>

			</div>
			<cmbPers:cmbPers></cmbPers:cmbPers>
			<s:token/>
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
	<div class="div-table-simple" id="validateAffDocError" style="display: none">
		<div class="div-table-row">
			<div  class="div-table-col alignLeft fontError" style="width: 100%">Any one Verification Document must be Affidavit</div>
		</div>
	</div>

		<script type="text/javascript"
			src="./../../registration/transactions/js/opdPatDtlModification.js" /></script>
	</center>
</body>
</html>