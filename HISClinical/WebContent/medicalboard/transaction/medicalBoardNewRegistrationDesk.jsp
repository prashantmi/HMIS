<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ page import ="java.util.*,registration.*" %>


<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="medicalboard.MedicalBoardConfig"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/stateChangeAjax.js" />
<his:javascript src="/registration/js/cityLocationAjax.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/medicalboard/js/popup.js" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<his:javascript src="/medicalboard/js/medicalBoardNewRegistrationDesk.js" />


<script>
 
 
 
function callThisOnload(){	
 alert("on Load")
	showState();
	showLocation();
	ageSelection();
	enableSpouseField();
	 showOrgDetails(); 
 }
 
 
 
 function showState(){ 
	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	alert("country")
  alert(<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>)
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	 //alert("chcking conuntry code "+contryCode+"  "+defaltcontryCode);
	var stateCode= document.getElementsByName("patAddStateCode")[0];
	if(contryCode==defaltcontryCode)
	{
	   alert("default country")
		document.getElementById("stateMandotary").style.display="";
		document.getElementById("divpatAddStateCodeCombo").style.display="";
		document.getElementById("divpatAddStateCodeText").style.display="none";
		document.getElementById("stateNotMandotary").style.display="none";
		document.getElementById("districtCombo").style.display="";
		document.getElementById("districtTextBox").style.display="none";
		if(stateCode.options[stateCode.selectedIndex].value=="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>")
		{
		document.getElementById("divpatAddCityLocCode").style.display=""; 
	  	document.getElementById("divpatAddCityLocation").style.display="none"; 
	  	}
	// 	document.getElementsByName("patAddStateCode")[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
	}
	else{
	// alert("not default country")
		document.getElementById("stateMandotary").style.display="none";
		document.getElementById("divpatAddStateCodeCombo").style.display="none";
		document.getElementById("divpatAddStateCodeText").style.display="";
		document.getElementById("stateNotMandotary").style.display="";
		document.getElementById("divpatAddCityLocCode").style.display="none"; 
	  	document.getElementById("divpatAddCityLocation").style.display="";
	  	document.getElementById("districtCombo").style.display="none";
		document.getElementById("districtTextBox").style.display="";
		 
	  	document.getElementsByName('patAddCity')[0].value=""
	  	document.getElementsByName('patIsUrban')[0].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_AREA_CATEGORY_CODE%>";
	  	document.getElementsByName('patAddPIN')[0].value=""
	  	document.getElementsByName('patAddCityLocCode')[0].value="-1"
	  	document.getElementsByName('patAddDistrictCode')[0].value="-1"
	}
	//  showLocation();
}

 
 
function showLocation(){

  var elmt= document.getElementsByName("patAddStateCode")[0];
  var locationRequired="<%=Config.IS_LOCATION_COMBO_REQ%>"
  var locationRequiredYes="<%=Config.IS_LOCATION_COMBO_REQ_YES%>"
  
  if(locationRequired==locationRequiredYes)
  { 
  
	  if(elmt.options[elmt.selectedIndex].value!=<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>)
	  	  {  
	  	   
		  document.getElementById("divpatAddCityLocCode").style.display="none"; 
		  document.getElementById("divpatAddCityLocation").style.display=""; 
		  }
	  else{
		 //alert("inside else hide divpatAddCityLocation") 
	  	  document.getElementById("divpatAddCityLocCode").style.display=""; 
		  document.getElementById("divpatAddCityLocation").style.display="none";   
		   document.getElementsByName("patAddCityLoc")[0].value="";
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	}
	else{
		  document.getElementById("divpatAddCityLocCode").style.display="none"; 
		  document.getElementById("divpatAddCityLocation").style.display="";   
		    // elmt.options[elmt.selectedIndex].value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>";
		 }
	
}
 
 
 function checkAgeOrDob()
 {
	if(document.getElementsByName("isActualDob")[0].checked){
	 return isEmpty(document.forms[0].patAge,"Age"); 
	 	 }
	 else{
	return isEmpty(document.forms[0].patDOB,"Date of Birth");}
 }

 
 
 
 
 function enlargeImagePopUp(event)
{
	var screenHieght=screen.availHeight-100;
	var screenWidth=screen.availWidth-100;
 child=openDependentPopup('<his:path src='/registration/enlargedImage.cnt'/>',event,screenHieght,screenWidth);
 child.moveTo(0,0);
}
 
 
 function enableSpouseField()
{
	var maritalStatus=document.getElementsByName("patMaritalStatusCode")[0].value;
	
	var maritalStatusSingle="<%=RegistrationConfig.REGISTRATIONDESK_MARITAL_STATUS_SINGLE%>";

	if( (maritalStatus=="-1") || (maritalStatus==maritalStatusSingle) )
	{
		document.getElementsByName("spouseName")[0].disabled=true;
	}
	else{
		document.getElementsByName("spouseName")[0].disabled=false;
	}
}
 
 
function showOrg(){
 if(document.getElementsByName('orgID')[0].value=="-1"){
 
     document.getElementById("divOrgAdd").style.display="none";
     document.getElementById("divOrgName").style.display="none";
     document.getElementById("divOrgTypel").style.display="none";
     document.getElementById("divOrgTypev").style.display="none";
     document.getElementById("divOrgTypevDisable").style.display="none";
     document.getElementById("divOrgAddDisable").style.display="none";
     document.getElementById("divOrgNameDisable").style.display="none";
  
   }
 else if(document.getElementsByName('orgID')[0].value=="11"){

     
     document.getElementsByName('orgTypeID')[0].value="-1";
     document.getElementsByName('orgAddress')[0].value="";
     document.getElementsByName('orgName')[0].value="";

	 document.getElementById("divOrgAdd").style.display="block";
     document.getElementById("divOrgName").style.display="block";
     document.getElementById("divOrgTypel").style.display="block";
     document.getElementById("divOrgTypev").style.display="block";
     document.getElementById("divOrgTypevDisable").style.display="none";
     document.getElementById("divOrgAddDisable").style.display="none";
     document.getElementById("divOrgNameDisable").style.display="none";
     }else {
     document.getElementById("divOrgAddDisable").style.display="block";
     //document.getElementById("divOrgNameDisable").style.display="block";
     document.getElementById("divOrgTypel").style.display="block";
     document.getElementById("divOrgTypevDisable").style.display="block";
     
     document.getElementById("divOrgAdd").style.display="none";
     document.getElementById("divOrgName").style.display="none";
    // document.getElementById("divOrgTypel").style.display="none";
     document.getElementById("divOrgTypev").style.display="none";
     
     }
}

function showOrgDetails(){
    if(document.getElementsByName('requestFrom')[1].checked==true){
      document.getElementById("showOrgDetails").style.display="block";
      showOrg()   }
    else {
      document.getElementById("showOrgDetails").style.display="none";
    }  
 }


function showCheckedDocument(chkDocument)
 {
   if(chkDocument.checked)
      {
        document.getElementsByName('checkListIdArray')[chkDocument.value].disabled=false;
        document.getElementsByName('checkByIdArray')[chkDocument.value].disabled=false;  
        document.getElementsByName('remarks')[chkDocument.value].disabled=false;
      }else{
        document.getElementsByName('checkListIdArray')[chkDocument.value].disabled=true;
        document.getElementsByName('checkByIdArray')[chkDocument.value].disabled=true;
        document.getElementsByName('remarks')[chkDocument.value].disabled=true;
      }
  }    


function validateLocation(){
		  var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	      var elmt= document.getElementsByName("patAddStateCode")[0];    
	      var locationRequired="<%=Config.IS_LOCATION_COMBO_REQ%>"
  			var locationRequiredYes="<%=Config.IS_LOCATION_COMBO_REQ_YES%>"   
		 //  alert("selected index value state combo"+elmt.options[elmt.selectedIndex].value);
		 //   alert("display style "+document.getElementsByName("patAddStateCode")[0].style.display)
		  //  alert("display style state name"+document.getElementsByName("patAddStateName")[0].style.display)
	if(locationRequired==locationRequiredYes)
  {
 	   if(contryCode==<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%> && elmt.options[elmt.selectedIndex].value==<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE%>){ 	        
		    value=document.getElementsByName('patAddCityLocCode')[0].value;  				    				     			
		   // alert("selected index value location combo"+value);
		  	if(value=="-1"){
		  	 alert("Please select Location");
       	     document.getElementsByName('patAddCityLocCode')[0].focus;	
		  	  return false;
		  	  }
		  	  else
		  	  return true;		  	
		   }
		   else{
		   // alert("inside else of validateLocation");
        	    value=document.getElementsByName('patAddCityLoc')[0].value;  				    				     					   
	       	    if (value==""){	       	     
	       	     alert("Please Enter The Location");
	       	     document.getElementsByName('patAddCityLoc')[0].focus;	       	     
	       	     return false;
	       	     }	       	     
	       	     else	       	     
	       	     return true;	       	    
	       	     }
	 }
	 else
	 {
	 	return true
	 }
}


function checkAgeOrDob()
{
	var valid=false;
	//alert(document.getElementsByName("isActualDob")[0].value)
	if(document.getElementsByName("isActualDob")[0].checked){
	 valid=isEmpty(document.forms[0].patAge,"Age"); 
	 	 }
	 else{
	 valid=isEmpty(document.forms[0].patDOB,"Date of Birth");
	}
	if(!valid){
		return valid;
	}
	
	var seniorCitizenCode='<%=Config.PRIMARY_CATEGORY_SENIOR_CITIZEN%>'
	var seniorCitizenAge='<%=Config.SENIOR_CITIZEN_AGE%>'
	//alert(seniorCitizenCode)
	if(document.forms[0].patPrimaryCatCode.value==seniorCitizenCode){
		//alert("validation for senior citizen");
		if(!validateForSeniorCitizen(seniorCitizenAge)){
			valid=false
		}
	}
	return valid;	
}

function checkState(){
 	var flag=false;
 	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	if(contryCode==defaltcontryCode){
		flag=isSelected(document.forms[0].patAddStateCode,"State");
		// alert("flag "+flag)
	}
	else{
	 //    flag=isEmpty(document.forms[0].patAddStateName,"State Name") ;
		// alert("flag "+flag)
		flag=true;
	}
	
	return flag;
 }
 
 
 function checkDistrict(){
 	var flag=false;
 	var contryCode=document.getElementsByName("patAddCountryCode")[0].value;
	var defaltcontryCode="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE%>";  
	if(contryCode==defaltcontryCode){
		flag=isSelected(document.forms[0].patAddDistrictCode,"District");
		// alert("flag "+flag)
	}
	else{
	     flag=isEmpty(document.forms[0].patAddDistrict,"District Name") ;
		// alert("flag "+flag)
	}
	
	return flag;
 }
 




 function validateOnSave()
 {
    var valid= true;
    
   if( comboValidation(document.getElementsByName('patPrimaryCatCode')[0],'Patient Catagory') &&
       comboValidation(document.getElementsByName('certificateTypeID')[0],'Certificate Type') &&
       isEmpty(document.getElementsByName('patFirstName')[0],'First Name') &&
       validateDot(document.forms[0].patFirstName,"First Name") && 
	   validateDot(document.forms[0].patMiddleName,"Middle Name") &&
	   validateDot(document.forms[0].patLastName,"Last Name") && 
	   isValid(document.forms[0].patAge,"Age",125) && 
	   isEmpty(document.forms[0].patAgeUnit,"Age Unit") && 
	   validateDateAgainstSysDate(document.forms[0].patDOB,"Date of Birth") && 
	   isEmpty(document.forms[0].patAgeUnit,"Age Unit") && 
	   comboValidation(document.forms[0].patGenderCode,"Gender") &&
	   validateDot(document.forms[0].patGuardianName,"Father Name") && 
	   validateDot(document.forms[0].patMotherName,"Mother Name") &&
	   checkState() && 
	   validateLocation() && 
	   checkDistrict() && 
	   validateDot(document.forms[0].patAddCityLoc,"Location") && 
	   validateDot(document.forms[0].patAddHNo,"House Number") && 
	   validateDot(document.forms[0].patAddStreet,"Street") && 
	   validateDot(document.forms[0].patAddDistrict,"District") && 
	   validateDot(document.forms[0].patAddCity,"City") && 
	   validateMinimumLength(document.getElementById("pintext"),'Pin Number','6') && 
	   validatePinNumber(document.getElementById("pintext"))  )
	  {
	    valid=true;
	  }else{
	    valid=false;
	  }
   return valid;    
 }
 
 
 
 function checkOrgDetail()
 {
    var valid=true;
   if(document.getElementsByName('requestFrom')[0].value=="2")
    {
     if(comboValidation(document.getElementsByName('orgID')[0],'Organization Name'))
      {
        if(document.getElementsByName('orgID')[0].value=="11")
         {
              
         }
            
      }
    }
 
 }
 
 
</script>

<%
String divdisplay="\"\"";
String divnddisplay="\"\"";
String divisrefdisplay="\"\"";
String divRefGnctdHospitalCode="\"\"";
String divRefHospname="\"\"";
String strdivage="\"\"";
String strdivdob="\"\"";
String RefGnctdHosNameradio=""; 
String RefGnctdHoscoderadio=""; 
	String divDocname="\"\"";
	String divDocTitle="\"\"";
	String divref="\"\"";
	String divRefGnctd="\"\"";
	String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
				
 %>
		   
<html:form action="/medicalBoardRegistration">	   
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:TransactionContainer>
		   

		   <his:TitleTag name="New Patient Registration"> 		
		   </his:TitleTag> 

    	<his:ContentTag>

			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
				 <td width="17%" class="tdfonthead">
				  <div align="right" id="fileidControl"">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<bean:message key="patient" /> 
						<bean:message key="category" /> </font>
					</div>
				  </td>
				  <td width="17%" class="tdfont" nowrap="nowrap">
					<div>
					 <html:select name="MbNewRegistrationFB" property="patPrimaryCatCode" tabindex="1"  styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>">
						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>" property="value" labelProperty="label" />
						</logic:present>
					 </html:select>
					</div>
				   </td>
				   <td width="17%" class="tdfonthead">
					<div align="right" id="fileidControl""><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><font
						color="#FF0000" size="1"
						face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
						key="certificatetype" /> </font></div>
				   </td>
					<td width="17%" class="tdfont" nowrap="nowrap">
					<div>
					<html:select name="MbNewRegistrationFB" property="certificateTypeID" tabindex="1" onchange="submitForm('GETCHECKLIST')" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE_LIST%>">
						<html:options collection="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					 </html:select>
					</div>
					</td>
					<td width="17%" class="tdfonthead"></td>
					<td width="17%" class="tdfonthead"></td>
				</tr>
				
			</table>
		</his:ContentTag>
		<his:statusNew>
		<his:SubTitleTag key="personalDetail">
		</his:SubTitleTag>
			<his:ContentTag>

				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="16%" height="25" nowrap class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana , Arial, Helvetica, sans-serif"> <bean:message
							key="patient" /> <bean:message key="name" /> </font></div>
						</td>
						<td width="20%" class="tdfonthead">
						<div align="left"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><font
							color="#FF0000" size="1"
							face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
							key="first" /> </font></div>
						</td>

						<td width="16%" class="tdfonthead">
						<div align="left"><font size="2" color="#000000"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="middle" /> </font></div>
						</td>

						<td width="16%" class="tdfonthead">
						<div align="left"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><bean:message
							key="last" /> </font></div>
						</td>
						<td class="tdfonthead" width="16%"><bean:message key="pic" />/<bean:message
							key="view" /><bean:message key="image" /></td>

						<td class="tdfonthead" width="16%">&nbsp;&nbsp;&nbsp; 
						<img class="button" style="cursor: pointer" src='<his:path src="/hisglobal/images/camera.png"/>' alt="Capture Photo" title="Capture Photo"
							onkeypress="if(event.keyCode==13) openPopup('<his:path src='registration/uploadFile.cnt'/>',event)"
							onclick="openPopup('<his:path src='registration/uploadFile.cnt'/>',event,300,600)">
						&nbsp;&nbsp;&nbsp; 
						<img class="button" style="cursor: pointer"
							src='<his:path src="/hisglobal/images/view.psd.1.gif"/>'
							alt="View Photo" title="View Photo"
							onkeypress="if(event.keyCode==13) enlargeImagePopUp(event)"
							onclick="enlargeImagePopUp(event)"></td>
					</tr>
				
					<tr>
						<td width="16%" class="tdfonthead">&nbsp;</td>

						<td width="20%" class="tdfont">
						<div align="left"><html:text name="MbNewRegistrationFB"
							property="patFirstName" styleClass="textbox" tabindex="1"
							maxlength="50" onchange="isAlpha(this,'First Name')"
							onkeypress="return validateAlphabetsWithDotsOnly(event)" /></div>
						</td>

						<td width="16%" class="tdfont">
						<div align="left"><html:text name="MbNewRegistrationFB"
							property="patMiddleName" tabindex="1" styleClass="textbox"
							maxlength="40" onchange="isAlpha(this,'Middle Name')"
							onkeypress="return validateAlphabetsWithDotsOnly(event)" /></div>
						</td>

						<td width="16%" class="tdfont">
						<div align="left"><html:text name="MbNewRegistrationFB"
							property="patLastName" tabindex="1" styleClass="textbox"
							maxlength="40" onchange="isAlpha(this,'Last Name')"
							onkeypress="return validateAlphabetsWithDotsOnly(event)" /></div>
						</td>
						<td width="16%" class="tdfonthead"></td>
						<td width="16%" class="tdfonthead"></td>
                    </tr>
                   
                  <tr>
					 <td width="16%" nowrap class="tdfonthead">
				      <div align="center">
				      <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
				      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 <bean:message key="age" /></font> 
					 <html:radio name="MbNewRegistrationFB" property="isActualDob" tabindex="1" value="0" onclick="ageSelection()" /> 
					 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 <bean:message key="dob" /></font> 
					 <html:radio name="MbNewRegistrationFB" property="isActualDob" tabindex="1" value="1" onclick="ageSelection()" /></div>
				</td>
				<logic:equal name="MbNewRegistrationFB" property="isActualDob"
					value="1">
					<% strdivage = "none";
						strdivdob = "";
					%>
				</logic:equal>
				<logic:equal name="MbNewRegistrationFB" property="isActualDob"
					value="0">
					<%System.out.println("inside case 0isActualDob:::::::::");
						strdivage = "";
						strdivdob = "none";
					%>
				</logic:equal>

				<td width="20%" class="tdfont" nowrap="nowrap" >
				 <div id="divAge" style='display:<%=strdivage%>'>
				 <html:text name="MbNewRegistrationFB" size="4" property="patAge" tabindex="1" maxlength="3" onkeypress="return validateNumeric(event)" /> 
				 <html:select
					name="MbNewRegistrationFB" property="patAgeUnit" tabindex="1"
					styleClass="smallcombo">
					 <logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>">
						<html:options
						collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE %>"
						property="value" labelProperty="label" />
					</logic:present>	
				 </html:select></div>

				<div id="divDob" style='display:<%=strdivdob%>' >
				<bean:define name="MbNewRegistrationFB" property="patDOB" id="dob" type="java.lang.String" />
					 <his:date name='<%="patDOB"%>' dateFormate="%d-%b-%Y" value='<%=dob%>'  /></div>
				</td>
						<td width="16%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"><font
							color="#FF0000" size="1"
							face="Verdana, Arial, Helvetica, sans-serif">*</font> <bean:message
							key="gender" /> </font></div>
						</td>

						<td width="16%" class="tdfont">
						<div align="left"><html:select name="MbNewRegistrationFB"
							property="patGenderCode" tabindex="1" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<logic:present
								name="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER%>">
								<html:options
									collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GENDER%>"
									property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>



                      <td width="16%" class="tdfonthead">
				       <div align="right"><font color="#000000" size="2"
					   face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					   key="maritalStatus" /> </font></div>
				      </td>
				      <td width="16%" class="tdfont"><html:select
					    name="MbNewRegistrationFB" property="patMaritalStatusCode" onchange="enableSpouseField()"  tabindex="1" styleClass="regcbo">
					    <html:option value="-1">Select Value</html:option>
					    <logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS %>">
					    <html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS %>"
						property="value" labelProperty="label" />
						</logic:present>
				        </html:select>
				      </td>
                          
                       
					
					</tr>

					<tr>
					
						<td width="16%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="fatherName" /> </font></div>
						</td>

						<td width="16%" class="tdfont">
						<div align="left"><html:text name="MbNewRegistrationFB"
							property="patGuardianName" styleClass="textbox" tabindex="1"
							maxlength="60" onchange="isAlpha(this,'Father Name')"
							onkeypress="return validateAlphabetsWithDotsOnly(event)" /></div>
						</td>
					
					   	<td width="17%" class="tdfonthead" >
					     <div align="right"><font color="#000000" size="2"
					     face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					     key="motherName" /></font></div>
				        </td>
				        <td width="17%" class="tdfont"><html:text name="MbNewRegistrationFB"
					      property="patMotherName" styleClass="textbox" tabindex="1"
					      maxlength="60" onchange="isAlpha(this,'Guardian Name')"
					      onkeypress="return validateAlphabetsWithDotsOnly(event)" />
					    </td>
					
					
						<td width="16%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="husbandName" /> </font></div>
						</td>

						<td width="20%" class="tdfont">
						<div align="left"><html:text name="MbNewRegistrationFB"
							property="spouseName" styleClass="textbox" tabindex="1"
							maxlength="60" onchange="isAlpha(this,'Spouse Name')"
							onkeypress="return validateAlphabetsWithDotsOnly(event)" /></div>
						</td>
                      </tr>
                      <tr>
						<td width="16%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="nationality" /> </font></div>
						</td>

						<td width="16%" class="tdfont">
						 <div align="left"><html:select name="MbNewRegistrationFB"
							property="patNationalityCode" tabindex="1" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY%>"> 
							<html:options
								collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY%>"
								property="value" labelProperty="label" />
							</logic:present>	
						  </html:select></div>
					     </td>
					     <td width="16%" class="tdfonthead" id="designationLabelId">
					       <div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
							key="designation" /> </font></div>
					      </td>
					      <td width="16%" class="tdfont">
						   <div align="left">
						    <html:text name="MbNewRegistrationFB"	property="patDesignation" styleClass="textbox" tabindex="1" maxlength="20" onkeypress="return validateAlphaNumericWithDotsOnly(event);" />
						   </div>
					      </td>
					      
					      <td width="17%" class="tdfonthead">
				          <div align="right"><font color="#000000" size="2"
					      face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					      key="amountCollected" /> </font></div>
				         </td>
				         <td width="17%" class="tdfont">
				         <html:text name="MbNewRegistrationFB" property="registrationFee"
					      styleClass="textbox" maxlength="11" tabindex="1" readonly="true" />
				       </td>
					</tr>
					
			
				</table>
		
	</his:ContentTag>
		  <his:SubTitleTagBroad name="Address Detail">
		   <table width="100%">
		   <tr>
             <td>	  
             		  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
                      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                      <bean:message key="country"/></font>
             </td>       
 			 <td>
 			 	       <html:select name="MbNewRegistrationFB" tabindex="1" property="patAddCountryCode"  styleClass="regcbo" onchange="if(this.value!='-1') showState()">
		                  	<html:option value="">Select Value</html:option>
		                  	<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY%>">
				  		    <html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY%>"property = "value" labelProperty = "label"/>
                            </logic:present>
                       </html:select>	
             </td>
            <td>
             
             <div id="stateMandotary" style="" >
             		  <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
             		  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<bean:message key="state"/>
             		  </font>
              </div>
              <div id="stateNotMandotary" style="" >
             		  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
             		  		<bean:message key="state"/>
             		  </font>
              </div>
             </td>
             <td>     
             		<div id="divpatAddStateCodeCombo" style="">                 
                     <html:select name="MbNewRegistrationFB" tabindex="1" property="patAddStateCode" styleClass="regcbo" onchange="if(this.value!='-1') sendDataForStateChange(this)" >
                       <html:option value="-1">Select Value</html:option>
                       <logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE%>">
                        <html:options collection = "<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE%>" property = "value" labelProperty = "label"/>
                       </logic:present>
                     </html:select>    
                     </div> 
                     <div id="divpatAddStateCodeText" style="" >
                     <html:text name="MbNewRegistrationFB" tabindex="1" property="patAddStateName" styleClass="regcbo" >
                     </html:text>
                     </div>                 
             </td>
             </tr>
             </table>
            </his:SubTitleTagBroad>
  <his:ContentTag>
 
   <table width="100%"  cellspacing="1" cellpadding="0">                    
     <tr>
             <td  class="tdfonthead" width="17%">
	            <div align="right">
	            <% if(Config.IS_LOCATION_COMBO_REQ.equals(Config.IS_LOCATION_COMBO_REQ_YES)){%>
	            <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	            <%} %>
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="location"/>
	            </font>
	            </div>
             </td>
 
 
         <td  class="tdfont" width="17%">
                <div id="divpatAddCityLocCode" >                
	            <html:select name="MbNewRegistrationFB" tabindex="1" styleClass="regcbo" property="patAddCityLocCode" onchange="if(this.value!='-1') sendDataForCityLocation(this)">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>">
				<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>" property = "value" labelProperty = "label"/>
	            </logic:present>
	            </html:select>
	            </div>	                    	                    	                    	                    	                    	                    	                    
	      
	            <div id="divpatAddCityLocation" >	                     
				<html:text  name="MbNewRegistrationFB" tabindex="1"  maxlength="30" onkeypress="return validateAlphaNumericWithDotsOnly(event);" styleClass="textbox" property="patAddCityLoc"/>
				</div>
		  </td>									         
                    <td  class="tdfonthead" width="14%" >
                    <div align="right">
	                     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	                     <bean:message key="hno"/>
	                     </font> 
                    </div>                    
                    </td>        
                                
                <td  class="tdfont" width="17%">
                <html:text  name="MbNewRegistrationFB" onkeypress="return validateAlphaNumericWithDotsOnly(event);" property="patAddHNo" tabindex="1" maxlength="15" styleClass="textbox"/>
                </td>
               
	            <td  class="tdfonthead" width="18%">                    
	            <div align="right">
	            
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="street"/>  
	            </font>
	            </div>                    
	            </td>
	                     
	            <td class="tdfont" width="17%">
	            <html:text  name="MbNewRegistrationFB" property="patAddStreet" onkeypress="return validateAlphaNumericWithDotsOnly(event);" tabindex="1" maxlength="30" styleClass ="textbox"/> 
	            </td>
                  
                
                </tr>
                 
                <tr>
                <td class="tdfonthead" width="14%">
                <div align="right">
                <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="district"/> 
	            </font>
	            </div>
                </td>                     
                
                <td class="tdfont" width="17%">
                <div id="districtCombo">
                <html:select name="MbNewRegistrationFB" tabindex="1" styleClass="regcbo" property="patAddDistrictCode">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>">
				<html:options collection ="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE%>" property = "value" labelProperty = "label"/>
	            </logic:present>
	            </html:select>
                </div>
                <div id="districtTextBox" style="display:none">
                <html:text  name="MbNewRegistrationFB" tabindex="1" property="patAddDistrict" onchange="isAlpha(this,'District')"  onkeypress="return validateAlphabetsWithDotsOnly(event)" maxlength="30" styleClass="textbox"/>
                </div>					
                </td>
                  
                <td  class="tdfonthead" width="18%" ><div align="right">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="city"/>
	            </font>
	            </div>
	            </td>
	            
	            <td class="tdfont" width="17%">
	            <html:text name="MbNewRegistrationFB" tabindex="1" property="patAddCity" onchange="isAlpha(this,'City')" onkeypress="return validateAlphabetsWithDotsOnly(event);" maxlength="30" styleClass="textbox"/> 
                </td>
                
                <td class="tdfonthead" width="14%">
	            <div align="right">
	            <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	            <bean:message key="pin"/>
	            </font> 
	            </div>
                </td>       
                    
                <td  class="tdfont" width="17%">
                <input type="text" id="pintext"  name="patAddPIN" tabindex="1"
                 size="17"  maxlength ="6"  onkeypress="return validateNumeric(event)"  /> 
                  </td>
              </tr>
              <tr>
                    <td  class="tdfonthead"  width="17%">
                    <div align="right">
                    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
                    <bean:message key="contactNo"/>
                    </font>
                    </div>
                    </td>
             		
                    <td  class="tdfont" width="17%">   
 		            <div >
         		    <html:text name="MbNewRegistrationFB" tabindex="1" property="patAddContactNo"  maxlength ="30" styleClass="textbox" onkeypress="return validateNumeric(event)"/> 
                    </div>
                    </td>
                    
             <td width="18%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="areaCategory" /> </font></div>
				</td>

				<td width="18%" class="tdfont"><html:select
					name="MbNewRegistrationFB" property="patIsUrban" tabindex="1"
					styleClass="regcbo">
					<html:option value="">Select Value</html:option>
					<logic:present name="<%= RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY %>">
					<html:options
						collection="<%= RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY %>"
						property="value" labelProperty="label" />
					</logic:present>	
				</html:select></td> 
             <td class="tdfonthead" width="18%"></td>
             <td class="tdfont" width="18%"></td>    
                  </tr>		
                </table>
   </his:ContentTag>

	
			<his:SubTitleTag name="">
			   <table >	
			   	 <tr>
					<td width="12%">
				       <div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="requestFrom"/>
						</font>
					  </div>
				    </td>
				    <td  width="40%">
				      <div align="left">
				        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="individual"/>
						</font>
						  <html:radio name="MbNewRegistrationFB" property="requestFrom" tabindex="1"  value="<%=MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_INDIVIDUAL %>" onclick="showOrgDetails()" />
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="organization"/>
						</font>
						  <html:radio name="MbNewRegistrationFB" property="requestFrom" tabindex="1"  value="<%=MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ORGANIZATION%>" onclick="showOrgDetails()" />
					  </div>
				     </td>
				     <td width="45%"></td>
				 </tr>	 
			   </table>	  
			</his:SubTitleTag>
		  <div id="showOrgDetails" style="display:none;">	
			<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="20%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="orgName"/>
						</font>
					</div>
				</td>
				<td width="20%" class="tdfont">
			          <div align="left">
			            <html:select name="MbNewRegistrationFB" property="orgID" style="width:156;" onkeypress="if(event.keyCOde==13)showOrg();" onchange="showOrg(); if(this.value!='11' || this.value!='-1'){submitForm('GETORGDTL')};" styleClass="registrationCmb" > 
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_ORGANISATION_NAME %>">
								<html:options collection="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_ORGANISATION_NAME %>" labelProperty="label" property="value"  />
							    </logic:present>
							</html:select> 
						</div>				
			      </td>
			      <td  width="27%" class="tdfont" >
			         <div id="divOrgName"  style="display:none" align="left">
			           <html:text name="MbNewRegistrationFB" property="orgName"  maxlength="50" size="34" onkeypress="return validateAlphaNumericOnly(event)"  >
					   </html:text> 
					 </div>
			      </td>
			      <td width="13%" class="tdfonthead">
						<div id="divOrgTypel"  style="display:none" align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="organizationType"/>
							</font>
						</div>
				  </td>
			      <td width="20%" class="tdfont">
			          <div id="divOrgTypev"  style="display:none" align="left">
			            <html:select name="MbNewRegistrationFB" property="orgTypeID" style="width:156;"  styleClass="registrationCmb" > 
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=MedicalBoardConfig.MEDICALBOARD_ORGANIZATIONTYPES %>" labelProperty="label" property="value"  />
							</html:select> 
						</div>	
						<div id="divOrgTypevDisable"  style="display:none" align="left">
			            <html:select name="MbNewRegistrationFB" property="orgTypeID"  style="width:156;" disabled="true" styleClass="registrationCmb" > 
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=MedicalBoardConfig.MEDICALBOARD_ORGANIZATIONTYPES %>" labelProperty="label" property="value"  />
							</html:select> 
						</div>					
			      </td>
			         
	            </tr>    
	          </table>
	          <his:statusInProcess>
	            <div id="divOrgAdd" style="display:none">
	              <table width="100%" border="0" cellspacing="1" cellpadding="0">
			        <tr>
				    <td width="20%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="address"/>
							</font>
						</div>
				    </td>
			        <td width="80%" class="tdfont" >
			          <div align="left">
			            <html:textarea name="MbNewRegistrationFB" property="orgAddress"  onkeypress="return validateAlphaNumericOnly(event)"  style="width:375px">
						 </html:textarea>
					  </div>
			        </td>
			         
			        </tr>
			        </table>
			       </div>
			        <div id="divOrgAddDisable" style="display:none">
	              <table width="100%" border="0" cellspacing="1" cellpadding="0">
			        <tr>
				    <td width="20%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="address"/>
							</font>
						</div>
				    </td>
			        <td width="80%" class="tdfont" >
			          <div align="left">
			            <html:textarea name="MbNewRegistrationFB" property="orgAddress" disabled="true" onkeypress="return validateAlphaNumericOnly(event)"  style="width:375px">
						 </html:textarea>
					  </div>
			        </td>
			         
			        </tr>
			        </table>
			       </div>
			 </his:statusInProcess>
			 </his:ContentTag>
		 </div>	
		 
	
		  <logic:present name="<%=MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_ARRAY %>" >
		      <his:SubTitleTag name="Documents Detail">
			   </his:SubTitleTag>
			   <his:ContentTag>
               <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                <tr>
					<td width="6%" class="tdfonthead">
					 <div align="center">
					 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <b><bean:message key="select"/></b>	
					 </font>
					 </div>
					</td>
					<td width="31%" class="tdfonthead">
					  <div align="center">
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					    <b><bean:message key="checklist"/></b>	
					   </font>
					  </div>
					 </td>
					 <td width="31%" class="tdfonthead">
					  <div align="center">
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					    <b><bean:message key="checkby"/></b>	
					   </font>
					  </div>
					 </td>
					 <td width="31%" class="tdfonthead">
					  <div align="center">
					  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					   <b><bean:message key="remarks"/></b>	
					  </font>
					 </div>
					</td>
                </tr>
               
                <logic:iterate id="checklistDtlVOs" indexId="idx" name="<%=MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_ARRAY %>" type="hisglobal.vo.MedicalBoardChecklistVO" >
                    <%String index=idx.toString(); %>
                <tr>
                	<td width="6%" class="tdfont">
						<div align="center">
						<html:checkbox name="MbNewRegistrationFB" property="selectedCheckListId" value="<%=index%>" onclick="showCheckedDocument(this)" ></html:checkbox>
						</div>
					</td>
                <td width="31%" class="tdfont">
						 <div align="center">
					      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					       <bean:write name="checklistDtlVOs" property="checklist"/>
					       <html:hidden name="MbNewRegistrationFB" property="checkListIdArray" value="<%=checklistDtlVOs.getChecklistID() %>" disabled="true"/>
					      </font>
					     </div>
				</td>
				
				  <td width="31%" class="tdfont">
			          <div  align="center">
			            <html:select name="MbNewRegistrationFB" property="checkByIdArray" style="width:156;" disabled="true" styleClass="registrationCmb" > 
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MedicalBoardConfig.MEDICAL_BOARD_CHECKLIST_BY_LIST %>">
								 <html:options collection="<%=MedicalBoardConfig.MEDICAL_BOARD_CHECKLIST_BY_LIST %>" labelProperty="label" property="value"  />
						        </logic:present>
							</html:select> 
						</div>				
			      </td>
				
				<td width="31%" class="tdfont">
					<div align="center">
			          &nbsp;<html:text name="MbNewRegistrationFB" property="remarks" disabled="true" maxlength="50" size="30"
							onkeypress="return validateAlphaNumericOnly(event)">
						</html:text>
						</div>
				</td>
                </tr>
                </logic:iterate>
               
                </table>
                </his:ContentTag>
              </logic:present>
		 
   	 </his:statusNew>
 		
 		
	<his:ButtonToolBarTag>
         
         <div align="center">		 
			 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style=cursor:pointer onkeypress="if(event.keyCode==13)submitFormOnValidate(validateOnSave(),'SAVE');" onclick =  "submitFormOnValidate(validateOnSave(),'SAVE');" tabindex="1"/>
	         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick ="submitPage('CANCEL');">
	         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
         
         </div>
     </his:ButtonToolBarTag>

   <input type="hidden" name="sysDate" value="<%=systemDate%>"/>
   <html:hidden name="MbNewRegistrationFB" property="hmode"/>
   <html:hidden name="MbNewRegistrationFB" property="departmentCode" />
   <html:hidden name="MbNewRegistrationFB" property="departmentUnitCode" />
   <html:hidden name="MbNewRegistrationFB" property="roomCode" />
  
   <html:hidden name="MbNewRegistrationFB" property="boardTypeID" />
   <html:hidden name="MbNewRegistrationFB" property="weekDay" />
   <html:hidden name="MbNewRegistrationFB" property="minRequest" />
   <html:hidden name="MbNewRegistrationFB" property="maxRequest" />

</his:TransactionContainer>

<his:status/>
</html:form>
