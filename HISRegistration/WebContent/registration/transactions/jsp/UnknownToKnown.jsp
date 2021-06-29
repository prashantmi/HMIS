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

<title>Unknown to known conversion</title>
<s:head />
</head>
<script type="text/javascript">
var isAddressAdded  = <%=RegistrationConfig.IS_ADDRESS_ADDED%>;
var isAddressModified = <%=RegistrationConfig.IS_ADDRESS_MODIFIED%>;
var registrationDeskDefaultCountryCode = '<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>';
$(window).on("load.loading1", function() 
{
	//alert("arrSelectedVerifyDocs1"+$('[name="arrSelectedVerifyDocs"]').val());
	if (document.getElementsByName("AfterGo")[0].value != '0') 
	{
		document.getElementById("divAfterGo").style.display = "";
		document.getElementById("divAfterGoId").style.display = "";
		document.getElementById("divGoId").style.display = "none";
		UnknownToKnown.fetchDefaultValues();
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
	document.forms[0].action = mode + "UnknownToKnown.action";
	document.forms[0].submit();

}
function submitFormonRadio(mode,obj) {
	document.forms[0].action = mode + "UnknownToKnown.action?crno="+obj.value;
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
	enableRelation();
	showpopuponload();
	if (document.getElementsByName("AfterGo")[0].value != '0') 
	{
	showDivAgeDob();
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
		document.getElementsByName("requestRelation")[0].disabled = true;
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
			//alert(hi);
			break;
		}else{
			$('[name="patCardNo"]').validatebox({required: false});
		}
	}
}

/*Added By Mukund Vinayak on 08.07.2016 for adding the document upload provision into unknown to known process  */

function upload(e)
{
	if($('[name="arrSelectedVerifyDocs"]')[0].value==''){
		alert("Please select verification documents first");
		return false;
	}
	var url = "../../registration/transactions/UploadFile.action";
	openURLInPopup(url,500,150,0,0);
}


function showDocuments(e)
{
	var processID = document.getElementsByName("processID")[0].value;
	var crNo = document.getElementsByName("crNo")[0].value;
	var filname = document.getElementsByName("filname")[0].value;
	var url = "../../registration/transactions/EnlargedImage.action?strProcessId="+processID+"&strCRNoHospCode="+crNo+"&strFileName="+filname; 
	if($('[name="arrSelectedVerifyDocs"]')[0].value==''){
		alert("Please select verification documents first");
		return false;
	}
	openPopup(url,e,300,600);
}

/*End Mukund */
</script>
<body >
	<center>

		<s:actionerror />

		<s:form action="UnknownToKnown">
			<s:set name="theme" value="simple" scope="page" />
			<div class="wrapper rounded ">
				<div class="div-table">
					<div class="div-table-row title">
						<div class="div-table-col  width100 "><s:text name="unknown"/>&nbsp;<s:text name="to"/>&nbsp;<s:text name="known"/>&nbsp;<s:text name="conversion"/></div>
						
					</div>
				</div>

				<his:InputCrNoTag name="UnknownToKnown"> </his:InputCrNoTag>
								<div id="divAfterGo" style="display: none">
					<div class="div-table">
						<div class="div-table-row title">
							<div class="div-table-col"  style="width: 60%; "><s:text name="global.patient"/>&nbsp;<s:text name="detail"/></div>
							<div class="div-table-col label"  style="width: 40%; "><s:text name="register"/>&nbsp;<s:text name="date"/>:<s:property value="registerDate"/></div>
						</div>

						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;"><s:text name="global.patient"/>&nbsp;<s:text name="global.name"/>:</div>
							<div class="div-table-col" style="width: 16%;"> &nbsp;<font color="red">*</font><s:text name="first"/></div>
							<div class="div-table-col" style="width: 16%;">&nbsp;<s:text name="middle"/></div>
							<div class="div-table-col" style="width: 16%;">&nbsp;<s:text name="last"/></div>
						</div>
						<div class="div-table-row">
							<div class="div-table-col" style="width: 16%;"></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patFirstName"  tabindex="1" maxlength="33"
										 >
								</s:textfield>
							</div>
							<!-- <div class="div-table-col label" style="width: 16%;">Patient Middle Name</div> -->
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patMiddleName" tabindex="2" maxlength="33"
										 >
								</s:textfield>
							</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patLastName" tabindex="2" maxlength="33"
										  >
								</s:textfield>
							</div>
						</div>
						
						<div class="div-table-row" style="display:none">
							<div class="div-table-col" style="width: 16%;"></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield  name="patFirstNameInMultiLang" tabindex="1" id="patFirstNameInMultiLangId" maxlength="33"
											onblur="callOnBlur();" onfocus="callOnClick();">
								</s:textfield>
							</div>
							<!-- <div class="div-table-col label" style="width: 16%;">Patient Middle Name</div> -->
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield  name="patMiddleNameInMultiLang" tabindex="2" id="patMiddleNameInMultiLangId"  maxlength="33"
											onblur="callOnBlur();" onfocus="callOnClick();">
								</s:textfield>
							</div>
							<!-- <div class="div-table-col label" style="width: 16%;">Patient Last Name</div> -->
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield  name="patLastNameInMultiLang" tabindex="2" id="patLastNameInMultiLangId" maxlength="33"
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
											<s:textfield id="patAgeId" name="patAge" tabindex="1" maxlength="5" size="5" onblur="setMotherValidRule();"></s:textfield>
											<select id="patAgeUnitId" name="patAgeUnit" tabindex="1" style="width: 75px" onblur="setMotherValidRule();showDivAgeDob();"><option value="0">Select Value</option></select>
										</div>
									</div>
								</div>
								<div id="divDob">
								
									<div class="div-table-col control" id="" style="width: 16%;">
										<input id="patDOBId" type="text" name="patDOB" tabindex="1" style="width: 100px;" onblur="setMotherValidRule();">
									</div>
									
								</div>
						
							</div>
						</div>

						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;">	<font color="red">*</font><s:text name="gender"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<%-- <s:select cssStyle="width: 145px;" list="#{'0':'Select Value'}" name="patGenderCode"	id="patGenderCodeId"	/> --%>
								<select name="patGenderCode" id="patGenderCodeId" tabindex="1" class="select77prcnt">
								<option value="-1">Select Value</option>
							</select> 
								<input name="patGender" type="hidden">
								<input name="defaultpatGenderCode" id="defaultpatGenderCodeId"
								type="hidden" value="-1"> <input name="defaultpatGender"
								id="defaultpatGenderId" type="hidden">
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="maritalStatus"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;" list="#{'0':'Select Value'}" name="patMaritalStatusCode" tabindex="2" value="patMaritalStatusCode" />
							</div>
						</div>

		
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="fathersName"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patGuardianName"  tabindex="1" maxlength="60">
								</s:textfield>
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="husbandName"/> </div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patHusbandName" tabindex="2" maxlength="60">
								</s:textfield>
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="motherName"/> </div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patMotherName" tabindex="2" maxlength="60">
								</s:textfield>
							</div>

						</div>
						
						<div class="div-table-row">

							<div class="div-table-col label" style="width: 16%;"><s:text name="patCaste"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patCasteCode" tabindex="2"
									value="patCasteCode" />
								<input name="patCaste" type="hidden">
							</div>

							<div class="div-table-col label" style="width: 16%;"><s:text name="religion"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select  cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patReligionCode" tabindex="2"
									value="patReligionCode" />
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="birplace"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patBirthPlace" tabindex="2" maxlength="50" />
							</div>

						</div>
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 16%;"><s:text name="patOccupation"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<select name="patOccupation" tabindex="2" id="patOccupationId"
									style="width: 145px">
									<option value="0">Select Value</option>
								</select>
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="monthlyIncome"/>
								</div>
							<div class="div-table-col control" style="width: 16%;">
								<s:textfield name="patMonthlyIncome" tabindex="2" maxlength="11">
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
								<s:textfield name="patNationalId" tabindex="2" maxlength="18">
								</s:textfield>
							</div>
							<div class="div-table-col label" style="width: 16%;"><s:text name="otherId"/></div>
							<div class="div-table-col control" style="width: 16%;">
								<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patDocType" id="patDocTypeId"
									tabindex="2" value="patDocType" />
									<input type="hidden" name="patDocTypeName"  />
							</div>
							<div id= "divCardNoId" class="div-table-col control" style="width: 32%;">
								<div class="div-table">
									<div class="div-table-row">
										<div class="div-table-col label" style="width: 50%;"><s:text name="global.cardno"/>
											</div>
										<div   class="div-table-col control" style="width: 50%;">
											<s:textfield name="patCardNo" tabindex="2" >
											</s:textfield>
										</div>
									</div>
								</div>
							</div>

						</div>

			<div class="div-table-row">
			
			<div class="div-table-col label" style="width: 16%;"><s:text name="identitymark1"/></div>
			<div class="div-table-col control" style="width: 16%;">
			<s:textfield name="patIdMark1" maxlength="50" tabindex="2" />
			</div>
			
			<div class="div-table-col label" style="width: 16%;"><s:text name="identitymark2"/></div>
			<div class="div-table-col control" style="width: 16%;">
				<s:textfield name="patIdMark2" maxlength="50" tabindex="2" />
			</div>
			</div>

					</div>



			 	<div class='div-table'>
					<div class="div-table-row title">
						<div class="div-table-col" style="width: 36%;">
							<s:text name="address" />&nbsp;<s:text name="detail" />
						</div>
					<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="country" /> 
					</div>
					<div class="div-table-col control" style="width: 16%;">
						
							<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patAddCountryCode" id="patAddCountryCodeId" tabindex="1"
									value="patAddCountryCode" />
						<input name="defaultpatAddCountryCode" type="hidden">
						<input name="patAddCountry" type="hidden">
					</div>
					<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="state" />  
					</div>
					<div id="divStateComboId" class="div-table-col control" style="width: 16%;">
						<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patAddStateCode" id="patAddStateCodeId"
							tabindex="1" value="patAddStateCode" />
						<input name="patAddState"  type="hidden">
					</div>
					<div id="divStateTextId" class="div-table-col control" style="width: 16%; display: none;">
							<s:textfield name="patAddState" maxlength="50" tabindex="1" />
					</div>			
				    </div>
				</div>
						
			<div class="div-table">
				<div class="div-table-row">
						<div class="div-table-col label" style="width: 16%;"><s:text name="hno" /></div>
						<div class="div-table-col control" style="width: 16%;">
						<s:textfield name="patAddHNo" size='10' tabindex="2" maxlength="60">
							</s:textfield>
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="street" />
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddStreet" maxlength="60" tabindex="2" />
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="location" />
						</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddCityLoc" maxlength="60" tabindex="2" />
						</div>

					</div>
					<div class="div-table-row">
					
					
					<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="district" /></div>
				<div id="divDistrictComboId" class="div-table-col control" style="width: 16%;">
					
					<% System.out.println("dffsaff"); %>
					<s:select cssStyle="width: 145px;"  list="#{'0':'Select Value'}" name="patAddDistrictCode" id="patAddDistrictCodeId"
						tabindex="2" value="patAddDistrictCode" />
					<input name="patAddDistrict" type="hidden">
					<input name="defaultpatAddDistrictCode" type="hidden">
				</div>
				<div id="divDistrictTextId" class="div-table-col control" style="width: 16%; display: none;">
					<s:textfield name="patAddDistrict" maxlength="15" tabindex="2" />
				</div>
				
				
						
						<div class="div-table-col label" style="width: 16%;"><s:text name="city" /><s:text name="slash" /><s:text name="village" /></div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddCity" maxlength="60" tabindex="2" />
						</div>
						<div class="div-table-col label" style="width: 16%;"><s:text name="pin" />
							</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddPIN" maxlength="6" tabindex="1"/>
						</div>

					</div>
					<div class="div-table-row" >
						<div class="div-table-col label" style="width: 16%;"><s:text name="global.landmark" /></div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddLandMarks" maxlength="60" tabindex="2" />
						</div>
						
						<div class="div-table-col label" style="width: 16%;"> <s:text name="areaCategory" /></div>
						<div class="div-table-col control" style="width: 16%;">
							<s:select cssStyle="width: 145px;"  list="#{'-1':'Select Value'}" name="patIsUrban"
									value="patIsUrban" tabindex="2" />
						</div>
						
						<div class="div-table-col label" style="width: 16%;"><s:text name="global.email" /> </div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddEmailId" maxlength="70"  tabindex="2" />
						</div>
						
					</div>
					<div class="div-table-row">

						<div class="div-table-col label" style="width: 16%;"><s:text name="global.phone" />&nbsp;<s:text name="number" />
							</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddPhoneNo" maxlength="15" tabindex="2" />
						</div>

						<div class="div-table-col label" style="width: 16%;"><s:text name="global.phone" />&nbsp;<s:text name="owner" />
							</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:select list="#{'1':'Self (patient)','2':'ANM','3':'Doctor or any other health provider','4':'Neighbour','5':'Family member'}" name="patAddPhoneOwner"
								value="patAddPhoneOwner"  tabindex="2" />
						</div>

						<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="mobileNo" />
							</div>
						<div class="div-table-col control" style="width: 16%;">
							<s:textfield name="patAddMobileNo" maxlength="10" tabindex="1" />
						</div>
					</div>
				
					</div>


             <div class="div-table">
						<div class="div-table-row title">
							<div class="div-table-col" style="width: 32%;">
								<s:text name="modification" />&nbsp;<s:text name="requestedby" />  </div>
						</div>
						<div class="div-table-row ">
							<div class="div-table-col label" style="width: 25%;">
								<font color="#FF0000" size="1"
									face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<s:text name="requestBy" />
							</div>


							<div class="div-table-col control" style="width: 25%;">
								<s:select
									list="#{'-1':'Select Value','0':'Self','1':'Relative','2':'Police'}"
									name="requestBy" value="requestBy" onchange="enableRelation()"  tabindex="1"/>

							</div>


							<!--//////////////////////////////add div for mcl no///////////////////  -->
							<div class="div-table-col label" style="width: 25%;">
								<div align="right" id="relationshipSelfId">
									<s:text name="Realtionship" />
								</div>
								<div align="right" id="relationshipRelativeId">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<s:text name="Realtionship" />
								</div>
							</div>
							<div class="div-table-col control" style="width: 25%;">
								<div id="relationshipRelativeId">
									<s:select list="#{'0':'Select Value'}" name="requestRelation" tabindex="1"
										value="requestRelation" onchange="enableRelation()" />
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
									<input name="requestByName"  maxlength="50" type="text" tabindex="1"
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
									<textarea name="requestByAddress"  requestByNamerows="1" tabindex="1" cols="15"></textarea>

								</div>

							</div>
						</div>
					</div>
					<div id="policeDetailDiv">
						<div class="div-table">
							<div class="div-table-row ">
								<div class="div-table-col label" style="width: 25%;">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<s:text name="designation" />
								</div>
								<div class="div-table-col control" style="width: 25%;">

									<input name="constableDesig" maxlength="50" type="text" tabindex="1"
										onkeypress="return validateAlphaNumericWithDotsOnly(event,this)">
								</div>
								<div class="div-table-col label" style="width: 25%;">
									<font color="#FF0000" size="1"
										face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<s:text name="batch" />&nbsp;<s:text name="number" />
								</div>

								<div class="div-table-col control" style="width: 25%;">
									<input name="constableBadgeNo" maxlength="50" type="text" tabindex="1"
										onkeypress="return validateAlphaNumericWithDotsOnly(event,this)">
								</div>
							</div>
						</div>
					</div>

					<div class="div-table">
						<div class="div-table-row title">
							<div class="div-table-col" style="width: 20%;">
								<div align="left" class="TitleTagFontStyle" valign="middle">
									<font color="#FF0000">*</font><s:text name="global.verificationDoc" /><img
										class="button"
										src='<his:path src="/hisglobal/images/icon-vrf.png"/>'
										style="cursor: pointer; vertical-align: middle;"
										alt="Verification Documents" title="Verification Documents"
										onclick="openPopup('<his:path src="/registration/transactions/verificationDoc.action?callerName=UnknownToKnownAction"/>',event,300,600);"
										onkeypress="if(event.keyCode==13) {openPopup('<his:path src="/registration/transactions/verificationDoc.action?callerName=UnknownToKnownAction"/>',event,300,600)}"
										size='7' tabindex="1">
								</div>
							</div>
							<div class="div-table-col" style="width: 50%;">
								<div align="left" class="TitleTagFontStyle"
									id="hiddenDivVerification">
									:: &nbsp; <font color="red"><s:text
											name="reqdVerificationDocument" /></font> <input type="hidden"
										name="arrSelectedVerifyDocs" value="" />
								</div>
							</div>
							
							<!--Added on 08.07.2016 for Addition of File Upload Functionality -->
							<div align="right" class="div-table-col" style="width: 30%;">
								 <s:text  name="Upload Verification Document"></s:text>
								 <img class="button" style="cursor:pointer" src='../../hisglobal/images/add.gif'
									alt="Upload Doc" title="Upload Doc" onkeypress="if(event.keyCode==13) upload(event)" 
									onclick="upload(event)" >
							 	 <img class="button" style="cursor:pointer" src='/HIS/hisglobal/images/A.png' alt="View Document" title="View Photo" 
							        onclick="showDocuments(event);"/>
							
					        </div>	
					        <!--End Added on 08.07.2016 for Addition of File Upload Functionality -->
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
				 	<a href="#" tabindex="1" class="button" id="submitId"><span class="save"><s:text name="save" /></span></a>
					<a id="#" tabindex="1" class="button"	onclick="submitClearAction('UnknownToKnown');"><span class="clear"><s:text name="clear" /></span></a> 
					<a href="#" tabindex="1" class="button" onclick="submitCancelAction('UnknownToKnown');"><span class="cancel"><s:text name="cancel" /></span></a>
					</div> 
					 <div id="divGoId" >  
						<a id="#" tabindex="1" class="button"	onclick="submitClear('UnknownToKnown');"><span class="clear"><s:text name="clear" /></span></a> 
						<a href="#" tabindex="1" class="button" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel" /></span></a>
					</div>
					</div>
				</div>
		
			<s:hidden name="AfterGo" value="%{AfterGo}" />
			<s:hidden name="addModify" />
			<s:hidden name="tempIsActualDOB" />

			<s:hidden name="verificationDocumentAdded" />
			<s:hidden name="isDuplicatePatient"/>
			<s:hidden name="seniorCitizenAgeLimit"  />
			<s:hidden name="seniorCitizenCatCode"  />
			<s:hidden name="patPrimaryCatCode"></s:hidden>

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
			
	<div class="div-table-simple" id="validateDocError" style="display: none">
		<div class="div-table-row">
			<div  class="div-table-col alignLeft fontError" style="width: 100%">Atleast one Verification Document is required</div>
		</div>
	</div>
	<div class="div-table-simple" id="validateNameError" style="display: none">
		<div class="div-table-row">
			<div  class="div-table-col alignLeft fontError" style="width: 100%">Patient first name is required</div>
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
			src="./../../registration/transactions/js/unknownToKnown.js" /></script>
	</center>
</body>
</html>