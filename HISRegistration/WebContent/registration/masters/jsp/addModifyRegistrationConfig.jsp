<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: Surabhi Gupta
 	 Date			: 13 may 2016 
 	 
 -->

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="./../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="./../registration/masters/js/registration.js"></script>
<script language="JavaScript" src="./../registration/masters/js/addModifyRegistrationConfig.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script>
function saveRegistrationConfigMstAction()
{
	if(validateCheck())
	{
		document.forms[0].action="saveRegistrationConfigMst.action";
		document.forms[0].submit();
	}
	
}
function callselectedOpd(value)
{	
	var radiovalue=0;
			radiovalue = value;
	 if (radiovalue==1)	
		{
		$('[name="strNoOfHrsOpd"]').removeAttr("disabled");
		}
	 else if(radiovalue==0){
		 $('[name="strNoOfHrsOpd"]').attr('disabled','disabled');
		 $('[name="strNoOfHrsOpd"]').val('');
	 }
}
function callselectedSpl(value)
{	
	var radiovalue=0;
			radiovalue = value;
	 if (radiovalue==1)	
		{
		$('[name="strNoOfHrsSpl"]').removeAttr("disabled");
		}
	 else if(radiovalue==0){
		 $('[name="strNoOfHrsSpl"]').attr('disabled','disabled');
		 $('[name="strNoOfHrsSpl"]').val('');
	 }
}
function callselectedEmg(value)
{	
	var radiovalue=0;
			radiovalue = value;
	 if (radiovalue==1)	
		{
		$('[name="strNoOfHrsEmg"]').removeAttr("disabled");
		
		}
	 else if(radiovalue==0){
		 $('[name="strNoOfHrsEmg"]').attr('disabled','disabled');
		 
	 }
}
 function loadFormEmg()
{	
	var radiovalueEmg=0;
			radiovalueEmg = $('[name="stremgRenewalEmg"]:checked').val();
	 if (radiovalueEmg==1)	
		{
		$('[name="strNoOfHrsEmg"]').removeAttr("disabled");
		
		}
	 else if(radiovalueEmg==0){
		 $('[name="strNoOfHrsEmg"]').attr('disabled','disabled');
		 
	 }
}
function loadFormSpl()
{	
	var radiovalueSpl=0;
			radiovalueSpl = $('[name="stremgRenewalSpl"]:checked').val();
			//alert($('[name="stremgRenewalSpl"]:checked').val());
	 if (radiovalueSpl==1)	
		{
		$('[name="strNoOfHrsSpl"]').removeAttr("disabled");
		
		}
	 else if(radiovalueSpl==0){
		 $('[name="strNoOfHrsSpl"]').attr('disabled','disabled');
		 
	 }
}
 function loadFormOpd()
{	
	var radiovalueOpd=0;
			radiovalueOpd = $('[name="stremgRenewalOpd"]:checked').val();
			//alert("opd"+$('[name="stremgRenewalOpd"]:checked').val());
	 if (radiovalueOpd==1)	
		{
		$('[name="strNoOfHrsOpd"]').removeAttr("disabled");
		
		}
	 else if(radiovalueOpd==0){
		 $('[name="strNoOfHrsOpd"]').attr('disabled','disabled');
		 
	 }
}



</script>
</head>


<body onload="loadFormOpd(); loadFormSpl(); loadFormEmg();">
	<s:form action="RegistrationConfig">

<!-- --------------------------------------------OPD------------------------------------------------------------------------------------- -->


		<div class="div-table" id="divOPDId">
			<div class="div-table-row title">
			<div class="div-table-col" style="width: 70%">
				<div class="div-table-col">
				<INPUT TYPE='hidden' NAME='strconfigGroupOpd' value="1" tabindex="2">
					</div>
					<div class="div-table-col  width87"><s:text name="opd"/>&nbsp;</div>
				</div>
		    </div>
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><s:text name="duplicacycheck"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<%-- <input type="radio" name="strduplicacyChkOpd" tabindex="1" value="1"  />
								<s:text name="yes" />
						<input type="radio" name="strduplicacyChkOpd" tabindex="1" value="0"  checked="checked" />
						        <s:text name="no" /> --%>	
						        <s:radio label="duplicacycheck" name="strduplicacyChkOpd"	list="#{'1':'Yes','0':'No'}"  value="strduplicacyChkOpd" />
						        
						        </div>
			
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="tempregistration"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<s:select list="#{'1':'No','2':'Portal Only','3':'Old System Only','4':'Portal and Old System'}" name="strtempRegOpd"
										value="strtempRegOpd" tabindex="1" />
				
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="mlcmode"/></div>
				<div class="div-table-col control" style="width: 16%;">
				<%-- <input type="radio" name="strmodechoiceOpd" tabindex="1" value="1"  />
								<s:text name="online1" />
						<input type="radio" name="strmodechoiceOpd" tabindex="1" value="0"  checked="checked" />
						        <s:text name="offline1" />	 --%>	
						        <s:radio label="mlcmode" name="strmodechoiceOpd"	list="#{'1':'Online','0':'Offline'}"  value="strmodechoiceOpd" />	
				</div>
			</div>
			
			
			<div class="div-table-row">
			    <div class="div-table-col label" style="width: 16%;"><s:text name="crossconsultation"/></div>
			    
			    <div class="div-table-col" style="width: 16%" >
				<s:select list="#{'1':'OPD Only','2':'Special Only','3':'OPD and Special both'}" name="strcrossconsultOpd"
										value="strcrossconsultOpd" tabindex="1" />	
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="appointmentbase"/>
				</div>
				<div class="div-table-col control"  style="width: 16%;">
					<%-- <input type="radio" name="strappointmentbsOpd" tabindex="1" value="1"  />
								<s:text name="yes" />
						<input type="radio" name="strappointmentbsOpd" tabindex="1" value="0"  checked="checked" />
						        <s:text name="no" /> --%>	
						        <s:radio label="appointmentbase" name="strappointmentbsOpd"	list="#{'1':'Yes','0':'No'}"  value="strappointmentbsOpd" />
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="adharserviceintegration"/></div>
				<div class="div-table-col control" style="width: 16%;">
						<%-- <input type="radio" name="stradharintegrationOpd" tabindex="1" value="1"  />
								<s:text name="yes" />
						<input type="radio" name="stradharintegrationOpd" tabindex="1" value="0"  checked="checked" />
						        <s:text name="no" /> --%>
						        <s:radio label="adharserviceintegration" name="stradharintegrationOpd"	list="#{'1':'Yes','0':'No'}"  value="stradharintegrationOpd" />
				</div>
			    
			</div>
	
			
			<div class="div-table-row">
				
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="emergencyrenewal"/>
				</div>
				<div class="div-table-col control"  style="width: 16%;">
						<%-- <input type="radio" name="stremgRenewalOpd" id="stremgRenewalOpdId" tabindex="1" value="1" onclick="callselectedOpd()" />
								<s:text name="yes" />
						<input type="radio" name="stremgRenewalOpd" id="stremgRenewalOpdId" tabindex="1" value="0"  checked="checked" onfocus="callselectedOpd()" />
						        <s:text name="no" /> --%>
						         <s:radio label="emergencyrenewal" name="stremgRenewalOpd" id="stremgRenewalOpdId"	list="#{'1':'Yes','0':'No'}" onchange="callselectedOpd(this.value)" value="stremgRenewalOpd"/>
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="noofHrs"/></div>
				<div class="div-table-col control" style="width: 16%;">
						<input type="text" name="strNoOfHrsOpd" id="strNoOfHrsOpdId" class="input70prcnt" value='<s:property value="strNoOfHrsOpd"/>' maxlength="3" disabled=disabled onkeypress="return validateNumericOnly(this,event);">
						&nbsp;<font color="red"><s:text name="hours"/></font>
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="seniorcitizencategorycode"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<input type="text" name="strseniorCitizenCatCodeOpd" class="input70prcnt" value='<s:property value="strseniorCitizenCatCodeOpd"/>' />
						
				</div>
		    </div>
			    
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><s:text name="seniorcitizenagelimit"/>
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input type="text" name="strseniorCitizenAgeOpd" class="input70prcnt" value='<s:property value="strseniorCitizenAgeOpd"/>' maxlength="3" onkeypress="return validateNumericOnly(this,event);">
						&nbsp;<font color="red"><s:text name="years"/></font>
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="mobileserviceintegration"/>
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<%-- <input type="radio" name="strmobileServiceOpd" tabindex="1" value="1"  />
								<s:text name="yes" />
						<input type="radio" name="strmobileServiceOpd" tabindex="1" value="0"  checked="checked" />
						        <s:text name="no" /> --%>
						        <s:radio label="mobileserviceintegration" name="strmobileServiceOpd"	list="#{'1':'Yes','0':'No'}"  value="strmobileServiceOpd" />
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="imageuploadmode"/></div>
				<div class="div-table-col control" style="width: 20%;">
					<s:select list="#{'1':'FTP','2':'Mongo DB'}" name="strimgUploadModeOpd"
										value="strimgUploadModeOpd" tabindex="1" />
				</div>
				
			</div>
		</div>
		
	<!-- ----------------------------------------------------------------Special-------------------------------------------------------------------------- -->	
		
	 	<div class="div-table">
					<div class="div-table-row title">
						<div class="div-table-col" style="width: 70%"><div class="div-table-col">
				<INPUT TYPE='hidden' NAME='strconfigGroupSpl' value="2" tabindex="2">
					</div>
					<div class="div-table-col width87 "><s:text name="special"/>&nbsp;</div>
				</div>
				
					</div>
		</div>
				<div class="div-table" id="divSpclId" >
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><s:text name="duplicacycheck"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<%-- <input type="radio" name="strduplicacyChkSpl" tabindex="1" value="1"  />
								<s:text name="yes" />
						<input type="radio" name="strduplicacyChkSpl" tabindex="1" value="0"  checked="checked" />
						        <s:text name="no" /> --%>	
						        <s:radio label="duplicacycheck" name="strduplicacyChkSpl"	list="#{'1':'Yes','0':'No'}"  value="strduplicacyChkSpl" />
						        </div>
			
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="tempregistration"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<s:select list="#{'1':'No','2':'Portal Only','3':'Old System Only','4':'Portal and Old System'}" name="strtempRegSpl"
										value="strtempRegSpl" tabindex="1" />
				
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="mlcmode"/></div>
				<div class="div-table-col control" style="width: 16%;">
				<%-- <input type="radio" name="strmodechoiceSpl" tabindex="1" value="1"  />
								<s:text name="online1" />
						<input type="radio" name="strmodechoiceSpl" tabindex="1" value="0"  checked="checked" />
						        <s:text name="offline1" /> --%>		
						         <s:radio label="mlcmode" name="strmodechoiceSpl"	list="#{'1':'Online','0':'Offline'}"  value="strmodechoiceSpl" />		
				</div>
			</div>
			
			
			<div class="div-table-row">
			    <div class="div-table-col label" style="width: 16%;"><s:text name="crossconsultation"/></div>
			    
			    <div class="div-table-col" style="width: 16%" >
				<s:select list="#{'1':'OPD Only','2':'Special Only','3':'OPD and Special both'}" name="strcrossconsultSpl"
										value="strcrossconsultSpl" tabindex="1" />	
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="appointmentbase"/>
				</div>
				<div class="div-table-col control"  style="width: 16%;">
					<%-- <input type="radio" name="strappointmentbsSpl" tabindex="1" value="1"  />
								<s:text name="yes" />
						<input type="radio" name="strappointmentbsSpl" tabindex="1" value="0"  checked="checked" />
						        <s:text name="no" />	 --%>
				 <s:radio label="appointmentbase" name="strappointmentbsSpl"	list="#{'1':'Yes','0':'No'}"  value="strappointmentbsSpl" />
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="adharserviceintegration"/></div>
				<div class="div-table-col control" style="width: 16%;">
						<%-- <input type="radio" name="stradharintegrationSpl" tabindex="1" value="1"  />
								<s:text name="yes" />
						<input type="radio" name="stradharintegrationSpl" tabindex="1" value="0"  checked="checked" />
						        <s:text name="no" /> --%>
						        <s:radio label="adharserviceintegration" name="stradharintegrationSpl"	list="#{'1':'Yes','0':'No'}"  value="stradharintegrationSpl" />
				</div>
			    
			</div>
	
			
			<div class="div-table-row">
				
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="emergencyrenewal"/>
				</div>
				<div class="div-table-col control"  style="width: 16%;">
						<%-- <input type="radio" name="stremgRenewalSpl" id="stremgRenewalSplId" tabindex="1" value="1" onclick="callselectedSpl()" />
								<s:text name="yes" />
						<input type="radio" name="stremgRenewalSpl" id="stremgRenewalSplId" tabindex="1" value="0"  checked="checked" onclick="callselectedSpl()" />
						        <s:text name="no" /> --%>
						        <s:radio label="emergencyrenewal" name="stremgRenewalSpl"	list="#{'1':'Yes','0':'No'}"  value="stremgRenewalSpl" onchange="callselectedSpl(this.value)" />
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="noofHrs"/></div>
				<div class="div-table-col control" style="width: 16%;">
						<input type="text" name="strNoOfHrsSpl" id="strNoOfHrsSplId" class="input70prcnt" value='<s:property value="strNoOfHrsSpl"/>' maxlength="3" disabled=disabled onkeypress="return validateNumericOnly(this,event);">
						&nbsp;<font color="red"><s:text name="hours"/></font>
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="seniorcitizencategorycode"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<input type="text" name="strseniorCitizenCatCodeSpl" class="input70prcnt" value='<s:property value="strseniorCitizenCatCodeSpl"/>' />
						
				</div>
		    </div>
			    
						
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><s:text name="seniorcitizenagelimit"/>
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input type="text" name="strseniorCitizenAgeSpl" class="input70prcnt" value='<s:property value="strseniorCitizenAgeSpl"/>' maxlength="3" onkeypress="return validateNumericOnly(this,event);">
						&nbsp;<font color="red"><s:text name="years"/></font>
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="mobileserviceintegration"/>
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<%-- <input type="radio" name="strmobileServiceSpl" tabindex="1" value="1"  />
								<s:text name="yes" />
						<input type="radio" name="strmobileServiceSpl" tabindex="1" value="0"  checked="checked" />
						        <s:text name="no" /> --%>
						        <s:radio label="mobileserviceintegration" name="strmobileServiceSpl"	list="#{'1':'Yes','0':'No'}"  value="strmobileServiceSpl" />
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="imageuploadmode"/></div>
				<div class="div-table-col control" style="width: 20%;">
					<s:select list="#{'1':'FTP','2':'Mongo DB'}" name="strimgUploadModeSpl"
										value="strimgUploadModeSpl" tabindex="1" />
				</div>
				
			
			</div>
		</div>


<!-- -----------------------------------------------------------------------Emergency------------------------------------------------------------------- -->
	
	<div class="div-table">
					<div class="div-table-row title">
						<div class="div-table-col" style="width: 70%"><div class="div-table-col">
				<INPUT TYPE='hidden' NAME='strconfigGroupEmg' value="3" tabindex="2">
					</div>
					<div class="div-table-col width87 "><s:text name="emergency"/>&nbsp;</div>
				</div>
					</div>
		</div>
	
	<div class="div-table" id="divEmgId">
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><s:text name="duplicacycheck"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<%-- <input type="radio" name="strduplicacyChkEmg" tabindex="1" value="1"  />
								<s:text name="yes" />
						<input type="radio" name="strduplicacyChkEmg" tabindex="1" value="0"  checked="checked" />
						        <s:text name="no" /> --%>
						        <s:radio label="duplicacycheck" name="strduplicacyChkEmg"	list="#{'1':'Yes','0':'No'}"  value="strduplicacyChkEmg" />
						        </div>
			
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="tempregistration"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<s:select list="#{'1':'No','2':'Portal Only','3':'Old System Only','4':'Portal and Old System'}" name="strtempRegEmg"
										value="strtempRegEmg" tabindex="1" />
				
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="mlcmode"/></div>
				<div class="div-table-col control" style="width: 16%;">
				<%-- <input type="radio" name="strmodechoiceSpl" tabindex="1" value="1"  />
								<s:text name="online1" />
						<input type="radio" name="strmodechoiceSpl" tabindex="1" value="0"  checked="checked" />
						        <s:text name="offline1" /> --%>		
						         <s:radio label="mlcmode" name="strmodechoiceEmg"	list="#{'1':'Online','0':'Offline'}"  value="strmodechoiceEmg" />		
				</div>
			</div>
			
			
			<div class="div-table-row">
			    <div class="div-table-col label" style="width: 16%;"><s:text name="crossconsultation"/></div>
			    
			    <div class="div-table-col" style="width: 16%" >
				<s:select list="#{'1':'OPD Only','2':'Special Only','3':'OPD and Special both'}" name="strcrossconsultEmg"
										value="strcrossconsultEmg" tabindex="1" />	
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="appointmentbase"/>
				</div>
				<div class="div-table-col control"  style="width: 16%;">
					<%-- <input type="radio" name="strappointmentbsSpl" tabindex="1" value="1"  />
								<s:text name="yes" />
						<input type="radio" name="strappointmentbsSpl" tabindex="1" value="0"  checked="checked" />
						        <s:text name="no" />	 --%>
				 <s:radio label="appointmentbase" name="strappointmentbsEmg"	list="#{'1':'Yes','0':'No'}"  value="strappointmentbsEmg" />
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="adharserviceintegration"/></div>
				<div class="div-table-col control" style="width: 16%;">
						<%-- <input type="radio" name="stradharintegrationSpl" tabindex="1" value="1"  />
								<s:text name="yes" />
						<input type="radio" name="stradharintegrationSpl" tabindex="1" value="0"  checked="checked" />
						        <s:text name="no" /> --%>
						        <s:radio label="adharserviceintegration" name="stradharintegrationEmg"	list="#{'1':'Yes','0':'No'}"  value="stradharintegrationEmg" />
				</div>
			    
			</div>
	
			
			<div class="div-table-row">
				
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="emergencyrenewal"/>
				</div>
				<div class="div-table-col control"  style="width: 16%;">
						<%-- <input type="radio" name="stremgRenewalSpl" id="stremgRenewalSplId" tabindex="1" value="1" onclick="callselectedSpl()" />
								<s:text name="yes" />
						<input type="radio" name="stremgRenewalSpl" id="stremgRenewalSplId" tabindex="1" value="0"  checked="checked" onclick="callselectedSpl()" />
						        <s:text name="no" /> --%>
						        <s:radio label="emergencyrenewal" name="stremgRenewalEmg"	list="#{'1':'Yes','0':'No'}"  value="stremgRenewalEmg" onchange="callselectedEmg(this.value)" />
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="noofHrs"/></div>
				<div class="div-table-col control" style="width: 16%;">
						<input type="text" name="strNoOfHrsEmg" id="strNoOfHrsEmgId" class="input70prcnt" value='<s:property value="strNoOfHrsEmg"/>' maxlength="3" disabled=disabled onkeypress="return validateNumericOnly(this,event);">
						&nbsp;<font color="red"><s:text name="hours"/></font>
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="seniorcitizencategorycode"/></div>
				<div class="div-table-col control" style="width: 16%;">
					<input type="text" name="strseniorCitizenCatCodeEmg" class="input70prcnt" value='<s:property value="strseniorCitizenCatCodeEmg"/>' />
						
				</div>
		    </div>
			    
						
			
			<div class="div-table-row">
				<div class="div-table-col label" style="width: 16%;"><s:text name="seniorcitizenagelimit"/>
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<input type="text" name="strseniorCitizenAgeEmg" class="input70prcnt" value='<s:property value="strseniorCitizenAgeEmg"/>' maxlength="3" onkeypress="return validateNumericOnly(this,event);">
						&nbsp;<font color="red"><s:text name="years"/></font>
				</div>
				<div class="div-table-col label" style="width: 16%;"><s:text name="mobileserviceintegration"/>
				</div>
				<div class="div-table-col control" style="width: 16%;">
					<%-- <input type="radio" name="strmobileServiceSpl" tabindex="1" value="1"  />
								<s:text name="yes" />
						<input type="radio" name="strmobileServiceSpl" tabindex="1" value="0"  checked="checked" />
						        <s:text name="no" /> --%>
						        <s:radio label="mobileserviceintegration" name="strmobileServiceEmg"	list="#{'1':'Yes','0':'No'}"  value="strmobileServiceEmg" />
				</div>
				
				<div class="div-table-col label" style="width: 16%;"><s:text name="imageuploadmode"/></div>
				<div class="div-table-col control" style="width: 20%;">
					<s:select list="#{'1':'FTP','2':'Mongo DB'}" name="strimgUploadModeEmg"
										value="strimgUploadModeEmg" tabindex="1" />
				</div>
				
			
			</div>
			
		</div>
		
		<div class="div-table-button">
			<div class="div-table-row">
					<div class="div-table-col footerBar"></div>
					<div class="div-table-col emptyBar"></div>
				</div>
				
				<div class="div-table-row" align='center'>
					<a href="#" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a> <%-- <a href="#" class="button"
						onclick="return submitCancelAction('RenewalConfig');"><span
						class="cancel"><s:text name="cancel"/></span></a> --%>
						<a href="#" class="button"
						onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span
						class="cancel"><s:text name="cancel"/></span></a>
				</div>
			</div>
			<s:hidden name="strtemp" />
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
	</s:form>
	<script type="text/javascript" >


$('#submitId').click(function(e){
	//alert("in");
	$("#RegistrationConfig").attr('action',"/HISRegistration/registration/saveRegistrationConfig.action");
	if($('#RegistrationConfig').form('validate'))
		{
		sortandEncodebase64($("#RegistrationConfig"));
		$('#RegistrationConfig').submit();
		
		}
			});

	</script>
	<%System.out.println("dsfsdfsf"+request.getParameter("message")); %>
		<s:property value="message" />
	<h3>
	</h3>	
</body>
</html>
