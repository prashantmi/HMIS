<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<link rel="stylesheet" href="../../hisglobal/css/jquery-ui.css">

<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/basic.css"> -->

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/basic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/barcode_code39.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>

 <script language="JavaScript" type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>
<%--<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script> --%>
<style type="text/css">

@media print { 
		#nonprintableDiv1 
		{
		 display: none; 
		}
		#nonprintableDiv2 
		{
		 display: none; 
		}
		#nonprintableDiv3 
		{
		 display: none; 
		}
		#loadingmessage
		{
		 display: none;
		}
		#divCatGroupBeneficiaryId
		{
		 	border-style: solid;
    		border-color: #ff0000 #0000ff;
		}
		
}

.border .div-table-col{
		border: 1px solid black;
}
.custbtncolor .ui-button-text{
		background: #086FA6;
		color: #FFFFFF;
		font-weight: bold
}

.custoverlay{
   		opacity: 0.9;   		  
}
</style>

<title>Patient Registration</title>
<script>
	var LOCAL_LANGUAGE = "<%= RegistrationConfig.LOCAL_LANGUAGE %>";
	var PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITH_REFERENCE = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY %>";
	var PATIENT_REG_CATEGORY_GROUP_PAID = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_PAID %>";
	var PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE = "<%= RegistrationConfig.PATIENT_REG_CATEGORY_GROUP_BENEFICIARY_WITHOUT_REFERENCE %>";


</script>

</head>

<body>
<center>

<s:form action="ExternalPatientRegistration">
    
    
    <input type="hidden" name="flagHasActionError" id="flagHasActionErrorId" value='<s:property value="%{hasActionErrors()}"/>'>
  	<s:if test="%{!hasActionErrors()}">
    <div class="wrapper rounded" id="nonprintableDiv1">
	    <!-- <h1>Patient Registration</h1> -->
		<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 "><s:text name="External"/>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="registration"/>
				</div>
			</div>
		<%-- 	<div class="div-table-row ">
				<div class="div-table-col label" style="width: 48%">
						<font color="red">*</font><s:text name="visiting"/>&nbsp;<s:text name="global.department"/>
				</div>
				<div class="div-table-col width control" style="width: 52%">
					<select name="departmentCode" tabindex="1" class="select23prcnt" onchange='opdregistration.setDepartmentDependents(this)'>
						<option value="-1">Select Value</option>
					</select>
					<input type="hidden" name="ageRange">
				</div>
			</div> --%>
		</div>

		<div class="div-table">
			<div class="div-table-row title">
			<div class="div-table-col" style="width: 65%">
				<div class="div-table-col">
				<s:text name="global.patient"/>&nbsp;<s:text name="global.detail"/>		
				</div>
				
				<div class="div-table-col width label" style="width: 16%">
						<font color="red">*</font><s:text name="global.patient"/>&nbsp;<s:text name="global.category"/>	
				</div>
				<div class="div-table-col width control" style="width: 16%">
					<select name="patPrimaryCatCode" id="patPrimaryCatCodeId" tabindex="1" class="select90prcnt">
						<option value="-1">Select Value</option>
					</select>
					<input name="patPrimaryCat" type="hidden" />
					<input name="patCatShortName" type="hidden" />
					<input name="patPrimaryCatGrp" type="hidden" />
				</div>
				
				<div id="divCatCardId" class="div-table-col" style="width: 16%; display: none;">
					<div  class="div-table" >
						<div class="div-table-row title">
							<%-- <div id="divCatCardNoLabelId" class="div-table-col"  style="width: 50%">
								<font color="red">*</font><s:text name="global.cardno"/>
							</div> --%>
							<div class="div-table-col control" style="width: 50%">
								<input id="shownPatIdNoId" tabindex="1" name="patIdNo"  type="text" />
							</div>
						</div>
					</div>
				</div>
				<div id="divImgCatCardId" class="div-table-col control" style="width: 16%; display: none;">
					<img id="imgCatCardId" align="left" src="../../hisglobal/images/search_icon1.gif" />
				</div>
				<div id="divSpareCatId" class="div-table-col" style="width: 16%;"></div>
				</div>
				<div class="div-table-col" style="width: 35%">
				<div class="div-table-col label-yellow" id="divFeeLabel" style="width: 15%;display: none;""><font color="red">*</font><s:text name="registrationFee"/> 
				</div>
				<div class="div-table-col control" id="divFeeVal" style="width: 20%;;display: none;">
			<!--Modify Date				: 28th Nov'14
	  			Reason	(CR/PRS)		: Bug Id 7608
	 		    Modify By               : Sheeldarshi -->
	 		        <img id="INRId" align="left" src="../../hisglobal/images/INR.png" />&nbsp;&nbsp;&nbsp;
	 		        <div id="amount" class="div-table-col label-yellow" class="input100prcnt"></div>
					<input name="patAmountCollected" id="patAmountCollectedId" tabindex="3"  type="hidden"  readonly="readonly">
				<!-- End:Sheeldarshi-->
				</div>
				</div>
				
			</div>

			
			
			<input type="hidden" name="creditBillFlag" id="creditBillFlagId" value="0">
						
<!-- 			<div id="divCatGroupBeneficiaryId" style="display: none;" class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-front ui-dialog-buttons ui-draggable">			 -->
			<div id="divCatGroupBeneficiaryId" style="display: none;">
			<div class="div-table">
			
			    <div class="div-table-row">
					<div class="div-table-col width16 label">
						<font color="red">*</font><s:text name="reference"/>&nbsp;<s:text name="letter"/>&nbsp;<s:text name="number"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="creditLetterRefNo" id="creditLetterRefNoId" class="input75prcnt" maxlength="50">
					</div>
					<div class="div-table-col width16 label">
						<font color="red">*</font><s:text name="letter"/>&nbsp;<s:text name="date"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="creditLetterDate" id="creditLetterDateId" readonly="readonly" class="input60prcnt">
					</div>
				</div>
			
				<div class="div-table-row">
					<div class="div-table-col width16 label"><s:text name="company"/>
					</div>
					<div class="div-table-col width16 control"  style="display: none">
						<select name="clientCode"  tabindex="2" id="clientCodeId" class="select77prcnt" onchange="setCompany();">
							<option value="-1">Select Value</option>
						</select>
					</div>
					<div class="div-table-col width16 control"  id="clientNameLabel">
					</div>
					<div class="div-table-col width16 label" id="staffCardNoLabel"><s:text name="staff"/>&nbsp;<s:text name="number"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="staffCardNo" id="staffCardNoId" class="input60prcnt" maxlength="50">
					</div>
					<div class="div-table-col width16 label"><s:text name="staff"/>&nbsp;<s:text name="global.name"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="staffCardName" id="staffCardNameId" class="input75prcnt" maxlength="100" onkeyup="setPatNameSelf();">
					</div>
				</div>
				
				<div class="div-table-row" >
					<div class="div-table-col width16 label"><s:text name="relation"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="global.patient"/>
					</div>
					<div class="div-table-col width16 control" >
						<select name="relationWithStaff"  tabindex="2" id="relationWithStaffId" class="select77prcnt" onchange="setRelation()">
							<option value="-1">Select Value</option>
						</select>					
					</div>
					<div class="div-table-col width16 label"><s:text name="validfor"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="cardvalidityDate" id="cardvalidityDateId" readonly="readonly" class="input60prcnt">
					</div>
					
				</div>
			</div>
			</div>
			
			<div id="divCatGroupArogyaShreeBeneficiaryId" style="display: none;">
			<div class="div-table">
				<div class="div-table-row" >
					<div class="div-table-col width16 label" ><s:text name="district"/>
					</div>
					<div class="div-table-col width16 control" >
						<select name="agsDistrictCode"  tabindex="2" id="agsDistrictCodeId" class="select77prcnt" >
							<option value="-1">Select Value</option>
						</select>
					</div>
					<div class="div-table-col width16 label"><font color="red">*</font><s:text name="arogyasri"/>&nbsp;<s:text name="number"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="agsNo" id="agsNoId" class="input75prcnt" maxlength="15">
					</div>
					<div class="div-table-col width16 label"><s:text name="arogyamitra"/>&nbsp;<s:text name="global.counter"/>&nbsp;<s:text name="number"/>
					</div>
					<div class="div-table-col width16 control" >
						<input type="text" name="agsCounterNo" id="agsCounterNoId" class="input75prcnt" maxlength="3">
					</div>					
				</div>
			</div>
			</div>
			
			<%-- <div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"></div>
				<div class="div-table-col" style="width: 28%;">&nbsp;<font color="red">*</font><s:text name="first"/></div>
				<div class="div-table-col" style="width: 28%;">&nbsp;<s:text name="middle"/></div>
				<div class="div-table-col" style="width: 28%;">&nbsp;<s:text name="last"/></div>
			</div> --%>
			<div class="div-table-row">
				<div class="div-table-col label
				" style="width: 16%;"><s:text name="global.patient"/>&nbsp;<s:text name="global.name"/>:&nbsp;<font color="red">*</font>(<s:text name="first"/>)</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patFirstName"  tabindex="1" maxlength="33" type="text" 
						onkeyup="multilingualConversion(this,document.getElementById('patFirstNameInMultiLangId'));" class="input75prcnt">
										</div>
			
				
				<div class="div-table-col label" style="width: 16%;">(<s:text name="middle"/>)</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patMiddleName" tabindex="2" maxlength="33" type="text" 
						onkeyup="multilingualConversion(this,document.getElementById('patMiddleNameInMultiLangId'));" class="input75prcnt">
				
				
				</div>
				<div class="div-table-col label" style="width: 16%;">(<s:text name="last"/>)</div>
				<div class="div-table-col control" style="width: 16%;">
				<input name="patLastName" tabindex="2" maxlength="33" type="text" 
					onkeyup="multilingualConversion(this,document.getElementById('patLastNameInMultiLangId'));" class="input75prcnt">
								
				</div>
			</div>
			<div class="div-table-row" style="display:none;">
				<div class="div-table-col label
				" style="width: 16%;"><s:text name="global.patient"/>&nbsp;<s:text name="global.name"/>:<font color="red">*</font>(<s:text name="first"/>)</div>
				<div class="div-table-col control" style="width: 16%;">
						<input name="patFirstNameInMultiLang" id="patFirstNameInMultiLangId" tabindex="1" maxlength="33" type="text" 
						 onblur="callOnBlur();" onfocus="callOnClick();">
				</div>
			
				
				<div class="div-table-col label" style="width: 16%;">(<s:text name="middle"/>)</div>
				<div class="div-table-col control" style="width: 16%;">
					
				
					<input name="patMiddleNameInMultiLang" id="patMiddleNameInMultiLangId" tabindex="2" maxlength="33" type="text" 
						 onblur="callOnBlur();" onfocus="callOnClick();">
				</div>
				<div class="div-table-col label" style="width: 16%;">(<s:text name="last"/>)</div>
				<div class="div-table-col control" style="width: 16%;">
				
								<input name="patLastNameInMultiLang" id="patLastNameInMultiLangId" tabindex="2" maxlength="33" type="text" 
					 onblur="callOnBlur();" onfocus="callOnClick();" >
				</div>
				
				
			</div>
			
			<div class="div-table-row">
			    <div class="div-table-col label" style="width: 16%;"><font color="red">*</font>
			    	<input type="radio" name="isActualDob" value="0" onclick="showDivAgeDob();" checked="checked"/><s:text name="age"/> &nbsp;
			    	<input type="radio" name="isActualDob" value="1" onclick="showDivAgeDob();"/><s:text name="bob"/>&nbsp;
			    </div>
			    
			    <div class="div-table-col" style="width: 16%" >
					<div id="divAge" class="div-table">
						<div class="div-table-row">
							<div class="div-table-col" style="width: 100%;">
						       <input id="patAgeId" tabindex="1" name="patAge" type="text" class="input30prcnt" maxlength="3" onblur="setMotherValidRule();" >
						    
						    	<select id="patAgeUnitId" tabindex="1" name="patAgeUnit" class="select45prcnt" onblur="setMotherValidRule();">
									<option value="-1">Select Value</option>
								</select>
							</div>	
						</div>
					</div>
				
					<div id="divDob" class="div-table" style="display: none;">
						<div class="div-table-row">
							<div class="div-table-col control" style="width: 100%;">
						       <input id="patDOBId" tabindex="1" type="text" name="patDOB" onblur="setMotherValidRule();" class="input50prcnt">&nbsp;
						    	
							</div>	
							
						</div>
					</div>
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="gender"/>
				</div>
				<div class="div-table-col control"  style="width: 16%;">
						<select name="patGenderCode" id="patGenderCodeId" tabindex="1" class="select77prcnt">
							<option value="-1">Select Value</option>
						</select>
						<input name="patGender" type="hidden">
						<input name="defaultpatGenderCode" id="defaultpatGenderCodeId" type="hidden" value="-1">
						<input name="defaultpatGender" id="defaultpatGenderId" type="hidden">
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="patCaste"/></div>
				<div class="div-table-col control" style="width: 16%;">
						<select name="patCasteCode" tabindex="2"  id="patCasteCodeId" class="select77prcnt">
							<option value="-1">Select Value</option>
						</select>
						<input name="patCaste" type="hidden">
				</div>
			    
			</div>
	
	<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="fathersName"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patGuardianName"  tabindex="1" id="patGuardianName" maxlength="60" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="husbandName"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patHusbandName" tabindex="1" maxlength="60" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="motherName"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patMotherName"  tabindex="2" maxlength="60" type="text" class="input75prcnt">
				</div>
			</div>
			
			<div class="div-table-row">
				
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="maritalStatus"/>
				</div>
				<div class="div-table-col control"  style="width: 16%;">
						<select name="patMaritalStatusCode" tabindex="2" id="patMaritalStatusCodeId" class="select77prcnt">
							<option value="-1">Select Value</option>
						</select>
						<input type="hidden" name="patMaritalStatus">
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="religion"/></div>
				<div class="div-table-col control" style="width: 16%;">
						<select name="patReligionCode"  tabindex="2" id="patReligionCodeId" class="select77prcnt" >
							<option value="-1">Select Value</option>
						</select>
						<input type="hidden" name="patReligion">
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="birplace"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patBirthPlace" tabindex="2"  maxlength="50" type="text" class="input75prcnt">
				</div>
		    </div>
			    
						
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><s:text name="adhar"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patNationalId"  tabindex="2" maxlength="18" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;"> <s:text name="otherId"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<select name="patDocType"  tabindex="2" id="patDocTypeId" class="select77prcnt">
						<option value="-1">Select Value</option>
					</select>
					<input type="hidden" name="patDocTypeName"  />
				</div>
				<div class="div-table-col control" style="width: 32%;">
					<div id="divCardNoId" class="div-table" style="width: 100%; display: none;">
						<div class="div-table-row">
							<div class="div-table-col label" style="width: 50%; "><font color="red">*</font><s:text name="global.cardno"/></div>
							<div class="div-table-col control" style="width: 50%;">
								<input name="patCardNo"  tabindex="2" type="text" class="input75prcnt">
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><s:text name="patOccupation"/>
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<select name="patOccupation"  tabindex="2" class="select77prcnt">
						<option value="-1">Select Value</option>
					</select>
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="monthlyIncome"/>
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patMonthlyIncome"  tabindex="2" maxlength="11" type="text" class="input75prcnt">
				</div>
				
			</div>
			
		</div>
	
		<div class="div-table">
			<div class="div-table-row title">
				<div class="div-table-col" style="width:32%;">
						<s:text name="adddetail"/>
				</div>
				<div class="div-table-col label" style="width: 16%;"><font color="red">*</font> <s:text name="country"/>
					</div>
					<div class="div-table-col control" style="width: 16%;">
						<select name="patAddCountryCode"  tabindex="1" id="patAddCountryCodeId" class="select77prcnt">
							<option value="-1">Select Value</option>
						</select>
						<input name="defaultpatAddCountryCode" type="hidden">
						<input name="patAddCountry" type="hidden">
					</div>
					<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="state"/> 
					</div>
					<div id="divStateComboId" class="div-table-col control" style="width: 16%;">
						<select name="patAddStateCode"  tabindex="1" id="patAddStateCodeId" class="select77prcnt">
							<option value="-1">Select Value</option>
						</select>
						<input name="defaultpatAddStateCode" type="hidden">
						<input name="patAddState"  type="hidden">
					</div>
					<div id="divStateTextId" class="div-table-col control" style="width: 16%; display: none;">

						<input name="patAddState" tabindex="1"  id="patAddStateId" maxlength="50" type="text" class="input75prcnt">

					</div>
			</div>

			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><s:text name="hno"/> 
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddHNo" tabindex="2" maxlength="60" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="street"/> 
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddStreet" tabindex="2" maxlength="60" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="location"/> 
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddCityLoc" tabindex="2"  maxlength="60" type="text" class="input75prcnt">
				</div>
				
			</div>
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="district"/> </div>
				<div id="divDistrictComboId" class="div-table-col control" style="width: 16%;">
					<select name="patAddDistrictCode"  tabindex="1"  id="patAddDistrictCodeId" class="select77prcnt">
						<option value="-1">Select Value</option>
					</select>
					<input name="defaultpatAddDistrictCode" type="hidden">
					<input name="patAddDistrict" type="hidden">
				</div>
				<div id="divDistrictTextId" class="div-table-col control" style="width: 16%; display: none;">
					<input name="patAddDistrict" id="patAddDistrictId"  tabindex="1"  maxlength="15" type="text" class="input75prcnt">
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="city"/>&nbsp;<s:text name="slash"/>&nbsp;<s:text name="village"/> 
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddCity" maxlength="60" type="text" class="input75prcnt"  tabindex="2" >
				</div>
				<div class="div-table-col label" style="width: 16%;"><!-- <font color="red">*</font> --><s:text name="pin"/>
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddPIN" maxlength="6" type="text" class="input75prcnt"  tabindex="2" >
					<input name="defaultpatAddPIN" type="hidden">					
				</div>
				
			</div>
				
			<div class="div-table-row" >

				<div class="div-table-col label" style="width: 16%;"><s:text name="global.landmark"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddLandMarks" maxlength="60" type="text" class="input75prcnt"  tabindex="2" >
				</div>
				
				<div class="div-table-col label" style="width: 16%;"> <s:text name="areaCategory"/>	 </div>
				<div class="div-table-col control" style="width: 16%;">
					<select name="patIsUrban" class="select77prcnt"  tabindex="2" >
						<option value="-1">Select Value</option>
					</select>
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="global.email"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddEmailId" maxlength="70" type="text" class="input75prcnt"  tabindex="2" >
				</div>
				
			</div>
			<div class="div-table-row">
						
				<div class="div-table-col label" style="width: 16%;"><s:text name="global.phone"/>&nbsp;<s:text name="number"/> </div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddPhoneNo" id="patAddPhoneNo" maxlength="16"  tabindex="2"  type="text" class="input75prcnt">
				</div>
				
				<div class="div-table-col label" style="width: 16%;"> <s:text name="global.phone"/>&nbsp;<s:text name="owner"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<select name="patAddPhoneOwner" class="select77prcnt"  tabindex="2" >
						<option value="-1">Select Value</option>
						<option value="1">Self (patient)</option>
						<option value="2">ANM</option>
						<option value="3">Doctor or any other health provider</option>
						<option value="4">Neighbour</option>
						<option value="5">Family member</option>
					</select>
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><font color="red">*</font><s:text name="mobileNo"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<input name="patAddMobileNo" maxlength="10" type="text" class="input75prcnt"  tabindex="2" >
				</div>
			</div>
		</div>
		
		
				
		<div class="div-table-button">
			<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row" align="center">
				<a  tabindex="1" href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
				<a  tabindex="1" href="#" class="button" id="clearId" ><span class="clear"><s:text name="clear"/></span></a>
				<a  tabindex="1" href="#" class="button" id="clearId" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>
			</div>
			
		</div>
	</div>
	<div class="div-table" >
		<div class="div-table-row emptyBar ">
			<div class="div-table-col"> </div>
		</div>
	</div>
	<div class="div-table-simple" id="fatherorSpouseError" style="display: none">
		<div class="div-table-row">
			<div  class="div-table-col alignLeft fontError" style="width: 100%">Father Name Or Spouse Name Is Compulsary</div>
		</div>
	</div>
	
	<div class="div-table-simple" id="nonprintableDiv2">
		<div class="div-table-row">
			<div id='divErrorMsgId' class="div-table-col alignLeft fontError" style="width: 100%"></div>
		</div>
	</div>
	
	<div class="div-table" id="nonprintableDiv3">
		<div class="div-table-row">
			<div id='divNormalMsgId' class="div-table-col alignLeft" style="width: 100%"></div>
		</div>
	</div>
	</s:if>
	<s:else>
		<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="new"/>&nbsp;<s:text name="global.patient"/>&nbsp;<s:text name="registration"/>
				</div>
			</div>
		</div>
		<div id='divErrorMsgId' class="div-table-col alignLeft fontError" style="width: 100%">
			<s:actionerror/>
		</div>
	</s:else>
	<div id="divPrintId" ></div>
	<input type="hidden" name="isIdRequired" />	
	<input type="hidden" name="isMandataryReadOnlyTrue" id="isMandataryReadOnlyTrueId" value="0" />	
	<input type="hidden" name="hiddenPatIdNo"  />
	<input type="hidden" name="prevCrNo" />	
	<input type="hidden" name="departmentUnitCode" />	
	<input type="hidden" name="alreadyRegisteredFromRenewalConfig" />
	<input type="hidden" name="strAreadyRegisteredFlag" />
	<input type="hidden" name="patCatDocCode" />
	<input type="hidden" name="patCatDocIsAlternateId" />
	<input type="hidden" name="clientName" />
	<input type="hidden" name="relationNameWithStaff" />
	<input type="hidden" name="patActualAmount" />	
	<input type="hidden" name="isDuplicatePatientPopup" />	
	
	<input type="hidden" name="hiddenCatOrRegstrdPopupFlag" id="hiddenCatOrRegstrdPopupFlagId" />
	<cmbPers:cmbPers></cmbPers:cmbPers>
	<s:token/>
</s:form>
<script type="text/javascript" src="./../../registration/transactions/js/ExternalPatientRegistration.js" /></script>
</center>

</body>
</html>