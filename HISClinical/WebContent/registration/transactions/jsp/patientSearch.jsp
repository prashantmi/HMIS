<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/HISClinical/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HISClinical/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HISClinical/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HISClinical/hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HISClinical/hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HISClinical/hisglobal/css/basic.css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">

<script language="JavaScript" type="text/javascript" src="/HISClinical/hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script type='text/javascript' src='/HISClinical/hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='/HISClinical/hisglobal/masterutil/js/jquery/basic.js'></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script type="text/javascript" src="/HISClinical/registration/masters/js/registration.js" /></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script>


<script language="JavaScript" type="text/javascript" src="/HISClinical/hisglobal/masterutil/js/jquery/security.js" ></script>
<title>Patient Search</title>
<script>
	$(window).on("load.loading1", function(){
		patientSearch.fetchDefaultValues();
	}); 
</script>
<style>
</style>

</head>

<body>
<center>
<s:actionerror/>

<s:form action="PatientSearch">
    <%-- <s:set name="theme" value="simple" scope="page" /> --%>
    <div class="wrapper rounded" id="nonprintableDiv1">
	    <!-- <h1>Patient Registration</h1> -->
		<div class="div-table">
		<div class="div-table-row ">
				<div class="div-table-col width70"></div>
				<div class="div-table-col width30">
						<s:radio id="isDemographicSearch" name="isDemographicSearch" value="%{isDemographicSearch}" list="#{'1':'Demographic Search','2':'Unique Id Search'}" onchange="patientSearch.showSearchTile();"></s:radio>
				</div>
			</div>
				<div id="uniqueIdSearchTile" class="div-table" style="display:none"> 			
			<div class="div-table-row ">
				<div class="div-table-col title" style="width: 50%;" >
						<s:text name="unique"/>&nbsp;<s:text name="global.id"/>&nbsp;<s:text name="search"/> 
				</div>
				<div class="div-table-col title" style="width: 22%;" >
						<s:radio id="isglobal" name="isGlobal" value="%{isGlobal}" list="#{'0':'Hospital Wise','1':'Global'}" onchange="patientSearch.showHospitalTile();"></s:radio>
				</div>
				<div class="div-table-col title" style="width: 8%;" ><font color="red">*</font>&nbsp;<s:text name="global.hospital"></s:text>
				</div>
				<div class="div-table-col title" style="width: 20%;"  id="divHospitalComboId" style="display:block">
						<s:select name="hospitalCode" id="hospitalCode" tabindex="1"
													list="#session.optionHospitalList"  listKey="value" listValue="label"/>
				</div>
				<div class="div-table-col title" style="width: 17%;display:none;"  id="divHospitalAllComboId">
						<s:select name="hospitalCode" id="hospitalCode" disabled="true"  
													list="#{'000':'All'}"/>
				</div>
			</div>
			<div class="div-table-row" >
				
				<div class="div-table-col control" style="width: 55%;">						
								</div>					
			</div>
			<div class="div-table-row">
<!-- 				         <div class="div-table-col control" style="width: 8%;" id="divUniqueCRNo"> -->
<!-- 								 <input type="radio" name="uniqueIdType" tabindex="1" value="1" onclick="showSerachBox(this)" /> -->
<%-- 								<div class="div-table-col label"><s:text name="crNO" /></div> --%>
<!-- 						</div> -->
						<div class="div-table-col label" style="width: 15%;" id="divUniqueUHID">
								<input type="radio" name="uniqueIdType" tabindex="1" value="2" onclick="showSerachBox(this)" />
   								<s:text name="aadharNo" />
						</div>
						<div class="div-table-col label" style="width: 15%;" id="divUniqueAlternateID">
								<input type="radio" name="uniqueIdType" tabindex="1" value="3" onclick="showSerachBox(this)" />
   								<s:text name="alternateId" />
						</div>
						<div class="div-table-col label" style="width: 15%;" id="divUniqueEmpID">
								<input type="radio" name="uniqueIdType" tabindex="1" value="4" onclick="showSerachBox(this)" />
   								<s:text name="employeeNo" />
						</div>
						<div class="div-table-col label" style="width: 15%;" id="divUniqueMobileNo">
								<input type="radio" name="uniqueIdType" tabindex="1" value="5" onclick="showSerachBox(this)" />
   								<s:text name="mobileNo" />
						</div>
						<div class="div-table-col label" style="width: 15%;" id="divUniqueMobileNo">
								<input type="radio" name="uniqueIdType" tabindex="1" value="6" onclick="showSerachBox(this)" />
   								<s:text name="global.email" />&nbsp;<s:text name="global.id" />
						</div>
						</div><br>
						<div class="div-table-row">
						<div id="divCatCardId" class="div-table-col" style="width: 45%; display: block;">
							    <div  class="div-table" >
								<div class="div-table-row">
									<div id="divCatCardNoLabelId" class="div-table-col label" style="width: 25%">
										<font color="red">*</font><s:text name="adhar" /> 
									</div>
									<div class="div-table-col control" style="width: 50%">
										<input name="patIdNo" tabindex="1" type="text" />
									</div>
								</div>
							</div>
				        </div>
				        <div class="div-table-col " id="divAlternateSearchDtlId" style="width:45%;display: none;">
				        <div class="div-table-col label" style="width: 25%;"><font color="red">*</font>&nbsp;<s:text name="search" />&nbsp;<s:text name="by" />&nbsp;<s:text name="global.id" /></div>
						<div class="div-table-col control" style="width: 50%;">
									<s:select name="patDocType" id="patDocTypeId" tabindex="1"
											list="#session.optionVerificationDoc"  listKey="value" listValue="label" 
											headerKey="-1" headerValue="Select Value" />
							<input type="hidden" name="patDocTypeName"  /></div>
   						</div>
				        <div id="divCardNoId" class="div-table-col" style="width: 35%; display: none;">
									<div class="div-table-col label" style="width: 50%; "><font color="red">*</font><s:text name="patCardNo" /></div>
									<div class="div-table-col control" style="width: 50%;">
										<input name="patCardNo" tabindex="1" type="text" >
								</div>
						</div>
				</div>
				</div></div>				
				        
			
		
		<div id="demographicSearchTile" style="display:none">
		<div class="div-table">
			<div class="div-table-row title">
				<div class="div-table-col" style="width: 85%">
					<s:text name="demographic" />&nbsp;<s:text name="global.detail" />&nbsp;<s:text name="search" />
				</div>
				
				<div class="div-table-col" style="width: 15%">
					<s:checkbox name="isUnknown" id="isUnknownId" onclick="checkUnknown" >&nbsp; <s:text name="unknown" /></s:checkbox>
				</div>
			</div>
			
			<div class="div-table-row" id="divFirstRow">
<!-- 			<div class="div-table-col" style="width: 30%"> -->
			<div class="div-table-col label" style="width: 10%"><font color="red">*</font><s:text name="first" />&nbsp;<s:text name="global.name" /> </div>
			<div class="div-table-col control" style="width: 14%">
			<s:textfield name="patFirstName" maxlength="33" tabindex="1" cssStyle="width:100%"></s:textfield>
<!-- 					<input name="patFirstName" maxlength="33" type="text" size="19"> -->
			</div>
<!-- 			</div> -->
<!-- 			<div class="div-table-col" style="width: 25%">			 -->
			<div class="div-table-col label" style="width: 10%"><s:text name="middle" />&nbsp;<s:text name="global.name" /></div>
			<div class="div-table-col control" style="width: 14%">
			<s:textfield name="patMiddleName" maxlength="33" tabindex="2" cssStyle="width:100%"></s:textfield>
<!-- 					<input name="patMiddleName" maxlength="33" type="text" size="19"> -->
			</div>
<!-- 			</div> -->
<!-- 			<div class="div-table-col" style="width: 25%">			 -->
			<div class="div-table-col label" style="width: 10%"><s:text name="last" />&nbsp;<s:text name="global.name" /> </div>
			<div class="div-table-col control" style="width: 14%">
			<s:textfield name="patLastName" maxlength="33" tabindex="2"  cssStyle="width:100%"></s:textfield>
<!-- 						<input name="patLastName" maxlength="33" type="text" size="19"> -->
			</div>
<!-- 			</div> -->
<!-- 			<div class="div-table-col" style="width: 20%">			 -->
			<div class="div-table-col label" style="width: 10%"><font color="red">*</font><s:text name="gender" />
				</div>
				<div class="div-table-col control" style="width: 14%">
						<s:select name="patGenderCode" id="patGenderCodeId" cssStyle="width:100%" tabindex="1"
									list="#session.optionGender"  listKey="value" listValue="label" 
									headerKey="-1" headerValue="Select Value" />
						
			</div>
<!-- 			</div> -->
			</div>
			
			<div class="div-table-row" id="divSecondRow">
<!-- 			<div class="div-table-col" style="width: 30%">						 -->
			<div class="div-table-col label" style="width: 10%"><s:text name="age" />&nbsp;<s:text name="range" /> </div>
			<div class="div-table-col control" style="width: 14%">
					<s:select name="patAge" id="patAge"  cssStyle="width:100%"
								list="#session.ageRangeOptionList"  tabindex="2" listKey="value" listValue="label" headerKey="-1" headerValue="Select Value" />
			</div>
<!-- 			</div> -->
<!-- 			<div class="div-table-col" style="width: 25%">				 -->
			<div class="div-table-col label" style="width: 10%"><s:text name="fathersName"/></div>
			<div class="div-table-col control" style="width: 14%">
			<s:textfield name="patGuardianName" maxlength="33" tabindex="2" cssStyle="width:100%"></s:textfield>
<!-- 					<input name="patGuardianName" id="patGuardianName" maxlength="60" type="text" size="19"> -->
			</div>
<!-- 			</div> -->
<!-- 			<div class="div-table-col" style="width: 25%"> -->
			<div class="div-table-col label" style="width: 10%"><s:text name="husbandName"/></div>
			<div class="div-table-col control" style="width: 14%">
			<s:textfield name="patHusbandName" maxlength="33" tabindex="2" cssStyle="width:100%"></s:textfield>
<!-- 					<input name="patHusbandName" id="patHusbandName" maxlength="60" type="text" size="19"> -->
			</div>
<!-- 			</div> -->
<!-- 			<div class="div-table-col" style="width: 20%"> -->
			<div class="div-table-col label" style="width: 10%"><s:text name="motherName"/></div>
			<div class="div-table-col control" style="width: 14%">
			<s:textfield name="patMotherName" maxlength="33" tabindex="2" cssStyle="width:100%"></s:textfield>
<!-- 						<input name="patMotherName" id="patMotherName" maxlength="60" type="text" size="19">						 -->
			</div>
<!-- 			</div> -->			
			</div>
			
			<div class="div-table-row" id="divThirdRow">
<!-- 			<div class="div-table-col" style="width: 30%">						 -->
			<div class="div-table-col label" style="width: 10%"><font color="red">*</font><s:text name="country" /></div>
			<div class="div-table-col control" style="width: 14%">
					<s:select name="patAddCountryCode" id="patAddCountryCodeId"
									list="#session.optionCountry"  tabindex="1" listKey="value" listValue="label" cssStyle="width:100%"
									headerKey="-1" headerValue="Select Value" onchange="patientSearch.onchange_patAddCountryCode();"/>
						<input name="defaultpatAddCountryCode" tabindex="1" type="hidden">
						<input name="patAddCountry" tabindex="1" type="hidden">		
			</div>
<!-- 			</div> -->
<!-- 			<div class="div-table-col" style="width: 25%">				 -->
			<div class="div-table-col label" style="width: 10%"><s:text name="state" /></div>
			<div class="div-table-col control" style="width: 14%" id="divStateComboId">
						<s:select name="patAddStateCode" tabindex="1" id="patAddStateCodeId" 
									list="#session.optionState"  listKey="value" listValue="label" cssStyle="width:100%"
									headerKey="-1" headerValue="Select Value" onchange="patientSearch.onchange_patAddStateCode();"/>
						<input name="patAddState" tabindex="1"  type="hidden">
						<s:hidden name="defaultpatAddStateCode" tabindex="1" ></s:hidden>
			</div>
			<div class="div-table-col control"  id="divStateTextId" style="width: 14%;display:none ">
			<s:textfield name="patAddState" id="patAddStateId" maxlength="50" tabindex="1" cssStyle="width:100%"></s:textfield>
<!-- 							<input name="patAddState" id="patAddStateId" maxlength="50" type="text"> -->
			</div>			
<!-- 			</div> -->
<!-- 			<div class="div-table-col" style="width: 25%"> -->
			<div class="div-table-col label" style="width: 10%"><s:text name="district" /></div>
			<div class="div-table-col control" style="width: 14%" id="divDistrictComboId">
					<s:select name="patAddDistrictCode" id="patAddDistrictCodeId" cssStyle="width:100%"
							tabindex="1" list="#session.stateWiseDistrictList"  listKey="value" listValue="label" headerKey="-1" headerValue="Select Value" />
					<input name="patAddDistrict" tabindex="1" type="hidden">			
					<s:hidden name="defaultpatAddDistrictCode"></s:hidden>
					
			</div>
			<div class="div-table-col control" id="divDistrictTextId" style="width: 14%;display:none ">
			<s:textfield name="patAddDistrict" tabindex="1" id="patAddDistrictId" maxlength="15" cssStyle="width:100%"></s:textfield>
<!-- 					<input name="patAddDistrict" id="patAddDistrictId" maxlength="15" type="text" size="19"> -->
			</div>	
<!-- 			</div> -->
<!-- 			<div class="div-table-col" style="width: 20%"> -->
			<div class="div-table-col label" style="width: 10%"><s:text name="city" /></div>
			<div class="div-table-col control" style="width: 14%">
			<s:textfield name="patAddCity" maxlength="33" tabindex="2" cssStyle="width:100%"></s:textfield>
<!-- 					<input name="patAddCity" maxlength="60" type="text" size="19"> -->
			</div>
<!-- 			</div> -->
			</div>
			
			<div class="div-table-row" id="divFourthRow">
<!-- 				<div class="div-table-col" style="width: 30%">				 -->
				<div class="div-table-col label" style="width: 10%;"><s:text name="hno" /> 
				</div>
				<div class="div-table-col control" style="width: 14%;">
				<s:textfield name="patAddHNo" maxlength="33" tabindex="2" cssStyle="width:100%"></s:textfield>
<!-- 					<input name="patAddHNo" maxlength="60" type="text" size="19"> -->
				</div>
<!-- 				</div> -->
<!-- 				<div class="div-table-col" style="width: 25%"> -->
				<div class="div-table-col label" style="width: 10%;"><s:text name="street" /> 
				</div>
				<div class="div-table-col control" style="width: 14%;">
				<s:textfield name="patAddStreet" maxlength="33" tabindex="2" cssStyle="width:100%"></s:textfield>
<!-- 					<input name="patAddStreet" maxlength="60" type="text" size="19"> -->
				</div>
<!-- 				</div> -->
<!-- 				<div class="div-table-col" style="width: 25%"> -->
				<div class="div-table-col label" style="width: 10%;"><s:text name="location" />
				</div>
				<div class="div-table-col control" style="width: 14%;">
				<s:textfield name="patAddCityLoc" maxlength="33" tabindex="2" cssStyle="width:100%"></s:textfield>
<!-- 					<input name="patAddCityLoc" maxlength="60" type="text" size="19"> -->
				</div>
<!-- 				</div> -->
<!-- 				<div class="div-table-col" style="width: 20%"> -->
				<div class="div-table-col label" style="width: 10%;"><s:text name="pin" /></div>
				<div class="div-table-col control" style="width: 14%;">
				<s:textfield name="patAddPIN" maxlength="6" tabindex="2" cssStyle="width:100%"></s:textfield>
<!-- 					<input name="patAddPIN" maxlength="6" type="text" size="19"> -->
				</div>
<!-- 				</div> -->
				
				
			</div>
			<!--    ## 		Modification Log							
					##		Modify Date				:18thDec'14 
					##		Reason	(CR/PRS)		:Add From Date and To Date logic in patient search
					##		Modify By				:Sheeldarshi  -->
				<div class="div-table-row " id="dateRow" id="divFifthRow" >
				<div class="div-table-col label" style="width: 10%;">&nbsp;<s:text name="registrationBetween"/>&nbsp;&nbsp; </div>	
				<div class="div-table-col control" id="divFromDate" style="width: 14%">
					       <input id="patFromDateId" tabindex="1" type="text" readonly="readonly" name="fromDate"  style="width:100%" onblur="patientSearch.checkFromDateValid();">&nbsp;
				</div>	
				<div class="div-table-col label" style="width: 10%;">&nbsp; And </div>	
				<div class="div-table-col control" id="divToDate" style="width: 14%">
					       <input id="patToDateId" tabindex="1" type="text" readonly="readonly" name="toDate" style="width: 100%" onblur="patientSearch.checkToDateValid();">&nbsp;
				</div>	
			</div>
				<!-- End:Sheeldarshi -->
			</div></div>
						
			
		<div class="div-table-button">
			<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row" align="center">
				<a href="#" tabindex="1" class="button" id="searchId"><span class="save"><s:text name="search" /></span></a>			
				<a href="#" tabindex="1" class="button" id="submitId"><span class="save"><s:text name="search" /></span></a>
				<a href="#" tabindex="1" class="button" id="clearId" ><span class="clear"><s:text name="clear" /></span></a>
				<a href="#" tabindex="1" class="button" id="clearId" onclick=" window.close();"><span class="cancel"><s:text name="cancel" /></span></a>
			</div>
			
		</div>
		
		
					<div  class="div-table" id="divPatDetilId"></div>
	 				
	 	</div>
	 				
	
	<input type="hidden" name="hiddenPatUniqueIdType" />
	<input type="hidden" name="hiddenPatDocType" />
<s:token></s:token>	
</s:form>
		<script type="text/javascript"
			src="/HISClinical/registration/transactions/js/patientSearch.js" /></script>
		<script type="text/javascript">
// alert(!$("#uniqueIdSearchTile").is(':hidden'));
// if($("#uniqueIdSearchTile").is(':hidden')){
// if($('[name="isDemographicSearch"]')[0].checked){

// }}
$(window).on("load.loading1", function() 
		{
patientSearch.showDivDate();
		});
</script>
</center>
<div id='loadingmessage' style='display:none'>
       <img src='/HISClinical/hisglobal/images/ajax-loader.gif'/>
</div>
</body>
</html>