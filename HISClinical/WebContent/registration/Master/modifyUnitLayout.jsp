<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<his:javascript src="/registration/js/validationCommon.js"/>
	<his:javascript src="/registration/js/validationCalls.js"/>
	<his:javascript src="/registration/js/registration.js"/>
	<his:javascript src="/registration/js/commonFunctions.js"/>
	<his:javascript src="/registration/js/calendar.js"/>
	<his:javascript src="/registration/js/dateFunctions.js"/>
	
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<script>
window.history.forward()

window.onload=function(){
	showDivChoice()
	load()
	if(document.getElementsByName("oldFilenorequired")[0].value!="<%=RegistrationConfig.FILE_NO_REQUIRED_FALSE%>"){
		document.getElementsByName("fileNoRequired")[0].disabled=true
		document.getElementsByName("filePrefix")[0].readOnly=true
		document.getElementsByName("startNo")[0].readOnly=true
		document.getElementsByName("isOtherDept")[0].disabled=true
		document.getElementsByName("isOtherDept")[1].disabled=true
		if(document.getElementsByName("fileDepartmentCode")[0])
			document.getElementsByName("fileDepartmentCode")[0].disabled=true
		if(document.getElementsByName("fileDepartmentUnitCode")[0])
			document.getElementsByName("fileDepartmentUnitCode")[0].disabled=true
		
	}
	
}

function load()
{
	//alert("onload");
	//alert("document.getElementsByName('fileNoRequired')[0] ="+document.getElementsByName("fileNoRequired")[0])
	if(document.getElementsByName("fileNoRequired")[0].value!="<%=RegistrationConfig.FILE_NO_REQUIRED_FALSE%>"
	  && document.getElementsByName("dataFoundDeptWise")[0].value=="0"){
		document.getElementsByName("dataFoundDeptWise")[0].value="1";
	}
	showFileGenOption(document.getElementsByName("fileNoRequired")[0])
	if(document.getElementsByName("isOtherDept")){
		if(document.getElementsByName("isOtherDept")[0].checked){
			showOtherdept(document.getElementsByName("isOtherDept")[0])
		}
		else
			showOtherdept(document.getElementsByName("isOtherDept")[1])
	}
}

function showDivChoice()
{

  if(document.getElementsByName("choice")[0].checked==true)
  val=document.getElementsByName("choice")[0].value;
  else
  val=document.getElementsByName("choice")[1].value;
 
  
  if(val==1){
  	 document.getElementsByName("transactionMode")[0].value="MISTAKE";
  	 
  	}
  else{
    document.getElementsByName("transactionMode")[0].value="ADDITION";
     
}
	showDoctorUnit();
}

function validateDate()
{
	var valid=true;
	var checkeffectiveDateFrom=document.getElementById("effectiveFrom1");
	
	if(checkeffectiveDateFrom !=null && checkeffectiveDateFrom != 'null')
	   {
	   
	        if(!compareDateCall(entryDate,effectiveFrom,2,"CurrentDate","Effective From Date"))
	        valid= false;
       }
    if(!document.getElementsByName('effectiveTo')[0].value=="")
	   {
	   if(!compareDateCall(effectiveFrom,effectiveTo,2,"Effective From Date","Effective To Date"))
	   valid= false;
	   }
	return valid;
}

function validateDayMonth()
{
	var valid=true;
	if(<%=Config.RENEWAL_TYPE%> =="4" || <%=Config.RENEWAL_TYPE%> =="5")
	{
		if(document.getElementsByName("isExpiry")[0].value==1)
		{ 
			valid=isSelected(document.forms[0].expiryMonth,"Expiry Month")
		}
		if(document.getElementsByName("isExpiry")[0].value==2)
		{
			valid=isEmpty(document.forms[0].expiryDay,"Expiry Day")
		}
	}
	return valid;
}

function validateRemarks()
{	var valid=true;
	if(document.getElementsByName('remarks')[0].value.length>50)
	 {
	 	alert("Characters in Remarks greater than 50");
	 	valid= false;
	 }
	 return valid;
}

function validateModifyUnitMaster()
{

	var valid=true;
       effectiveFrom = document.getElementsByName("effectiveFrom")[0];
       effectiveTo = document.getElementsByName("effectiveTo")[0];
       entryDate = document.getElementsByName("entryDate")[0];
            
	 if(isEmpty(document.forms[0].unitName,"Unit Name")&&
		isSelected(document.forms[0].isGeneral,"Unit Type") &&
		isSelected(document.forms[0].locationCode,"Unit Location") &&
		
		//isSelected(document.forms[0].diagnosisCodeType,"Diagnosis Code Type") &&
		
		isEmpty(document.forms[0].defaultCloseDays,"Default Close Day")&&
		validateIsExpiry() &&
		validateDayMonth() &&
		
		isEmpty(document.forms[0].effectiveFrom,"Effective From") &&
		validateDate() &&
		validateCardPrintFlag() &&
		validateIsFileRequired() && 
		validateFilePrintFlag() &&
		validateRemarks())
     
	    {
	  	valid=true;
	  	
	  	 submitTile('SAVE');
	  	 
	  	}
	  	  
	else
	{
		
		valid=false;
		
	}
	
}

function validateIsExpiry()
{
	var valid=true;
	if(<%=Config.RENEWAL_TYPE%> =="4" || <%=Config.RENEWAL_TYPE%> =="5")
	{
		if(isSelected(document.forms[0].isExpiry,"Is Expiry"))
			valid=true;
		else	
			valid=false;
	}
	return valid;	
}

function compareDateCall(d1,d2,mode,l1,l2){
//alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmpty(d1,l1) && isEmpty(d2,l2)){
 if(compareDate(d1,d2, mode)){
		valid = true;
	}
 else {
	alert(l1+" cant be greater than "+l2);
	valid = false;
	}
}

else
valid=false;

return valid;
}



function submitTile(mode)
{
   document.getElementsByName("transactionMode")[0].value=mode;  
   
   document.forms[0].submit();
}



function showDoctorUnit()
{
	if(document.getElementsByName("isUnit")[0].checked)
		document.getElementById("divDoctorUnit").style.display="none";
	else
		document.getElementById("divDoctorUnit").style.display="block";	
}


function showDoctorNameInUnit()
{
	var elementName="unitDoctorName";
	var selectObject=document.getElementsByName(elementName)[0];
	var value=selectObject.value;
	var selectedIndex=selectObject.selectedIndex;
	var optionsArray=selectObject.options;
	var label=optionsArray[selectedIndex].text;
	label=label.substring(0,label.indexOf("("));
	

	var empCode=document.getElementsByName("unitDoctorName")[0].value;
	if(empCode=="-1")
		document.getElementsByName("unitName")[0].value="";
	else
		document.getElementsByName("unitName")[0].value=label;	
}

function clearForm(){
	document.getElementsByName("unitName")[0].value="";	
	document.getElementsByName("isGeneral")[0].value="-1";	
	document.getElementsByName("locationCode")[0].value="-1";	
	document.getElementsByName("diagnosisCodeType")[0].value="-1";	
	document.getElementsByName("defaultCloseDays")[0].value="";	
	document.getElementsByName("fileNoRequired")[0].value="";	
	document.getElementsByName("remarks")[0].value="";	
	document.getElementsByName("isValid")[0].value="-1";	


} 

function showFileGenOption(obj){
	if(obj.value!="<%=RegistrationConfig.FILE_NO_REQUIRED_FALSE%>" && obj.value!="-1"){
		document.getElementById("fileRequiredDiv").style.display="block"
		//alert(document.getElementsByName("filePrefix")[0].value)
		if(document.getElementsByName("dataFoundDeptWise")[0].value=="1"){
			document.getElementsByName("filePrefix")[0].readOnly=true
			document.getElementsByName("startNo")[0].readOnly=true
		}	
		document.getElementsByName("deptCode")[0].value=document.getElementsByName("departmentCode")[0].value
	}
	else{
		document.getElementById("fileRequiredDiv").style.display="none"
	}

}

function checkFileRequired(obj){
	showFileGenOption(obj);
	if(obj.value!="<%=RegistrationConfig.FILE_NO_REQUIRED_FALSE%>" && obj.value!="-1"){
		document.getElementsByName("deptCode")[0].value=document.getElementsByName("departmentCode")[0].value
		if(obj.value!="<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE%>"){
			document.getElementsByName("unitCode")[0].value=""
		}
		document.getElementsByName("transactionMode")[0].value="GETOTHERUNITDTL"
		document.forms[0].submit();	
	}
}

function showOtherdept(obj){
	//alert("other dept"+obj.value)
	if(obj.value=="1"){
		if(document.getElementsByName("fileNoRequired")[0].value=='<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE%>'){
			if(document.getElementById("deptDiv"))
			 	document.getElementById("deptDiv").style.display="block"
		} 
		else if(document.getElementsByName("fileNoRequired")[0].value=='<%=RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE%>'){
			if(document.getElementById("unitDiv"))
			 	document.getElementById("unitDiv").style.display="block"
		}	
		
	 	document.getElementsByName("filePrefix")[0].readOnly=false
		if(document.getElementsByName("dataFoundOtherDeptWise")[0].value=="1"){
		 	document.getElementsByName("startNo")[0].readOnly=true
		}
		else{
			document.getElementsByName("startNo")[0].readOnly=false
		}
	}	
	else{
		document.getElementsByName("filePrefix")[0].value=document.getElementsByName("oldPrefix")[0].value
		document.getElementsByName("startNo")[0].value=document.getElementsByName("oldStartNo")[0].value
	 	if(document.getElementsByName("dataFoundDeptWise")[0].value=="1"){
			document.getElementsByName("filePrefix")[0].readOnly=true
			document.getElementsByName("startNo")[0].readOnly=true
		}	
	 	else{
			document.getElementsByName("filePrefix")[0].readOnly=false
			document.getElementsByName("filePrefix")[0].value=""
			document.getElementsByName("startNo")[0].readOnly=false
			document.getElementsByName("startNo")[0].value=""
		}	
		if(document.getElementsByName("fileNoRequired")[0].value=='<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE%>'){
			if(document.getElementById("deptDiv"))
				document.getElementById("deptDiv").style.display="none"
		} 
		else if(document.getElementsByName("fileNoRequired")[0].value=='<%=RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE%>'){
			if(document.getElementById("unitDiv"))
				document.getElementById("unitDiv").style.display="none"
		}	
		
		if(document.getElementsByName("fileDepartmentCode")[0])
			document.getElementsByName("fileDepartmentCode")[0].value="-1"
		if(document.getElementsByName("fileDepartmentUnitCode")[0])	
			document.getElementsByName("fileDepartmentUnitCode")[0].value="-1"
		document.getElementsByName("unitCode")[0].value=""	
	}
	
}

function getOtherUnitDetail(obj){
	showOtherdept(obj);
	if(obj.value=="1"){
		if(document.getElementsByName("fileNoRequired")[0].value=='<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE%>'){
			document.getElementsByName("transactionMode")[0].value="GETDEPT"
		} 
		else if(document.getElementsByName("fileNoRequired")[0].value=='<%=RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE%>'){
			document.getElementsByName("transactionMode")[0].value="GETUNITFORFILE"
		}	
		
		document.forms[0].submit();	
	}
}

function getFileNoDetail(obj){
	if(obj.value!="-1"){
		if(document.getElementsByName("fileNoRequired")[0].value=='<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE%>'){
			document.getElementsByName("deptCode")[0].value=obj.value
		} 
		else if(document.getElementsByName("fileNoRequired")[0].value=='<%=RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE%>'){
			document.getElementsByName("deptCode")[0].value=document.getElementsByName("departmentCode")[0].value
			document.getElementsByName("unitCode")[0].value=obj.value
		}	
		
		document.getElementsByName("transactionMode")[0].value="GETOTHERUNITDTL"
		document.forms[0].submit();
	}		
}

function validateIsFileRequired(){
 	var isFileRequired=document.getElementsByName("fileNoRequired")[0].value
	var valid=false;
	//alert("isFileRequired "+isFileRequired)
	if(isFileRequired!='<%=RegistrationConfig.FILE_NO_REQUIRED_FALSE%>'){
		if(isFileRequired=='<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE%>'){
			if(document.getElementsByName("isOtherDept")[1].checked) {
				if(isSelected(document.getElementsByName("fileDepartmentCode")[0],"Department Grouping"))
					valid=true	
				else
					return false;	
			}	
			//alert("file required");
			if(validateFilePrefix() 
			 && isEmpty(document.getElementsByName("startNo")[0],"Start No.")){
			 	valid=true;
			 }
			 else
			 	return false;
		}
		
		if(isFileRequired=='<%=RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE%>'){
			if(document.getElementsByName("isOtherDept")[1].checked) {
				if(isSelected(document.getElementsByName("fileDepartmentUnitCode")[0],"Unit Grouping"))
					valid=true	
				else
					return false;	
			}	
			//alert("file required");
			if(validateFilePrefix() 
			 && isEmpty(document.getElementsByName("startNo")[0],"Start No.")){
			 	valid=true;
			 }
			 else
			 	return false;
		}
	}
	return true;
}

function validateFilePrefix(){
	
	//alert("validateFilePrefix")
	if(!isEmpty(document.getElementsByName("filePrefix")[0],"File Prefix")){
		return false;
	}
	var filePrefix=document.getElementsByName("filePrefix")[0].value
	if(filePrefix.indexOf("@")> "-1"){
		alert("File Prefix cannot contains @");
		document.getElementsByName("filePrefix")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("dataFoundOtherDeptWise")[0].value=="1" 
		&& document.getElementsByName("isOtherDept")[1].checked){
		if(document.getElementsByName("filePrefix")[0].value.length!=document.getElementsByName("prefixLength")[0].value){
			alert("File Prefix length should be " + document.getElementsByName("prefixLength")[0].value + " characters long")
			return false;
		}	
	}	
	return true;
}

function validateCardPrintFlag(){
	var valid=false;
	var cardPrintType=document.getElementsByName("cardPrintType");
	if(cardPrintType[0].checked==false && cardPrintType[1].checked==false){
		alert("Please Select Card Print Type");
		return false;
	}
	if(isSelected(document.getElementsByName("noOfCopiesOfCard")[0],"No. Of Copies for Card")){
		valid=true;
	}
	else
		 return false;
	return valid;	

}

function validateFilePrintFlag(){
	
	var valid=true;
	if(document.getElementsByName("fileNoRequired")[0].value!="0"){
		valid=false;
		var filePrintType=document.getElementsByName("filePrintType");
		var printingProcess=document.getElementsByName("printingProcess");
		if(filePrintType[0].checked==false && filePrintType[1].checked==false){
			alert("Please Select File Print Type");
			return false;
		}
		if(isSelected(document.getElementsByName("noOfCopiesOfFile")[0],"No. Of Copies for File")){
			valid=true;
		}
		else
		 return false;
		 
		if(printingProcess[0].checked==false && printingProcess[1].checked==false){
			alert("Please Select Printing Process");
			return false;
		} 
		 
	}	
	return valid;	

}

</script>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*" %>
	<%System.out.println("....1");%>  

<body >

<html:form action="/master/modifyUnitLayout.cnt">
<his:TransactionContainer>
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
%>
<his:statusNew>
	<%varIsNewStatus=true;
	 varStatus="New";%>
</his:statusNew>

<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy"); %>
<his:TitleTag name="Modify Unit">			
	
</his:TitleTag> 


<his:ContentTag>
<table width="100%" border="0"  cellspacing="1" cellpadding="0">   
	
			
	<tr style="display: none;">
	<bean:define id="clientFlag" name="<%=Config.CLIENT_NAME%>"></bean:define>
		<td class="tdfonthead" align="left" width="25%">
		<font color="#000000" size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> <b>
		<bean:message key="updateMode" /> </b> </font>
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
		<bean:message key="correction" /> </font> 
		<html:radio name="ModifyUnitLayoutFB" property="choice" tabindex="1"
		value="<%=RegistrationConfig.CHOICE_MISTAKE%>" onclick="submitTile('MISTAKE')"/> <font color="#000000"
		size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
		key="modification" /> </font> 
		<html:radio name="ModifyUnitLayoutFB"
		property="choice" tabindex="1"
		value="<%=RegistrationConfig.CHOICE_ADDITION%>" onclick="submitTile('ADDITION')" /></td>
	</tr>
			
			
</table>
  <table width="100%" border="0"  cellspacing="1" cellpadding="0">    
  <tr>
      <td width="50%" class="tdfonthead" align="right">
	           <div align="right">	           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	             <bean:message key="department"/>&nbsp;
	              </font>
	            </div>
      </td>                        
      
      <td width="50%" class="tdfont">
	  <div align="left">
      
      <logic:equal name="ModifyUnitLayoutFB" property="source" value="fromview">
         <bean:write name="ModifyUnitLayoutFB" property="departmentName"/>
         <html:hidden name="ModifyUnitLayoutFB" property="departmentName"/>      
	     <html:hidden name="ModifyUnitLayoutFB" property="departmentCode"/>
	     <html:hidden name="ModifyUnitLayoutFB" property="unitCode"/>         
      </logic:equal>
      </div>
      <logic:notEqual name="ModifyUnitLayoutFB" property="source" value="fromview">
		  &nbsp;<html:select name="ModifyUnitLayoutFB" tabindex="1" property="departmentCode" styleClass="regcbo">
		  <html:option value="-1">Select Value</html:option>
	  	  <html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property="value" labelProperty="label" />
		  </html:select>
	  </logic:notEqual>
            </td>                                          
      <logic:notEqual name="ModifyUnitLayoutFB" property="source" value="fromview"> 
 
      <td width="50%"  class="tdfonthead">
	           <div align="right">	
	           <font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>           
	              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	              <bean:message key="unit"/>&nbsp;
	              </font>
	            </div>
      </td>      
      <td width="50%"  class="tdfont">
	         <div align="left">	           
	             &nbsp;<html:select name="ModifyUnitLayoutFB" tabindex="1" property="unitCode" styleClass="regcbo" onchange="if(this.value!='-1') submitTile('GETDETAILS')">
			 			<html:option value="-1">Select Value</html:option>
  			 			<html:options collection="<%=Config.ESSENTIALBO_OPTION_DEPT_UNIT%>" property="value" labelProperty="label" />
	   		 	</html:select>
	          </div>
      </td> 
      </logic:notEqual>                                            
 </tr>
 
 <tr>
 	<td width="20%" class="tdfonthead">
  		<div align="right">           
  			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
  				<font color="#FF0000">*</font>
	  				<bean:message key="isUnit"/>&nbsp;
  			</font>
	 	</div>
    </td> 
    <td width="20%" class="tdfont">
  		<div align="left">
  			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  			<html:radio name="ModifyUnitLayoutFB" property="isUnit" value="<%=RegistrationConfig.IS_UNIT_DEPARTMENT_UNIT %>" disabled="true"></html:radio>
	  			<bean:message key="deptUnitName"/>
	  			
	  			<html:radio name="ModifyUnitLayoutFB" property="isUnit" value="<%=RegistrationConfig.IS_UNIT_DOCTOR_UNIT %>" disabled="true"></html:radio>
	  			<bean:message key="doctorUnit"/>
	  			<html:hidden name="ModifyUnitLayoutFB" property="isUnit"/>
	  		</font>	  
  		</div>
  	</td>
</tr>
</table>
	<div id="divDoctorUnit">
		<table width="100%" border="0" cellspacing="1" cellpadding="0"> 
			<tr>
		  		<td width="50%" class="tdfonthead">
			  		<div align="right">           
			  			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  				<font color="#FF0000">*</font>
				  			<bean:message key="referredBy"/>&nbsp;
			  			</font>
				 	</div>
			    </td> 
			    <td width="50%" class="tdfont">
			  		<div align="left">
			  			&nbsp;<html:select name="ModifyUnitLayoutFB" property="unitDoctorName" onchange="showDoctorNameInUnit()">
			  				<html:option value="-1">Select value</html:option>
			  				<logic:present name="<%=RegistrationConfig.EMPLOYEE_AS_CONSULTANT%>">
			  				<html:options collection="<%=RegistrationConfig.EMPLOYEE_AS_CONSULTANT%>" property="value" labelProperty="label" />
			  				</logic:present>
			  			</html:select>
			  		</div>
			  	</td>
			</tr>
		</table>
	</div>
	
	<table width="100%" border="0" cellspacing="1" cellpadding="0"> 
 <tr>
      <td width="50%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <font color="#FF0000">*</font>
	  <bean:message key="unit"/>&nbsp;
	  </font>
	  </div>
      </td>      
      <td width="50%" class="tdfont">
	         <div align="left">	           
	         	&nbsp;<html:text name="ModifyUnitLayoutFB" maxlength="50" property="unitName" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)"/>	         
	         </div>
      </td>                                           
  </tr>	   
  
  <tr>
	   <td width="50%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <font color="#FF0000">*</font><bean:message key="unitType"/>&nbsp;
		              </font>
		            </div>
	   </td>      
	   <td width="50%"  class="tdfont">
	         <div align="left">
	                &nbsp;<html:select name="ModifyUnitLayoutFB" disabled="true" tabindex="1" property="isGeneral" styleClass="regcbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNITTYPE%>" property="value" labelProperty="label" />
	   		 	</html:select>
	   		 	<html:hidden name="ModifyUnitLayoutFB" property="isGeneral"/>
	          </div>
       </td>                                                 
  </tr>
  <tr>
  		<td width="20%"  class="tdfonthead">
			<div align="right">	           
		    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		        	<font color="#FF0000">*</font>
		        	<bean:message key="unit"/>
		        	<bean:message key="location"/>&nbsp;
		        </font>
		    </div>
	   </td>      
	   <td width="20%"  class="tdfont">
	         <div align="left" >
	         	&nbsp;<html:select name="ModifyUnitLayoutFB" tabindex="1" property="locationCode" styleClass="regcbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>" property="value" labelProperty="label" />
	   		 	</html:select>
	         </div>
	    </td>     
  </tr>
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <font color="#FF0000">*</font><bean:message key="diagnosisTypeCode"/>&nbsp;
		              </font>
		            </div>
	   </td>      
	   <td width="20%"  class="tdfont">
	         <div align="left" >
	                &nbsp;<html:select name="ModifyUnitLayoutFB" tabindex="1" property="diagnosisCodeType" styleClass="regcbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:option value="0">Icd Code</html:option>  			 	
  			 		<!--<html:option value="1">Hospital Code</html:option>
  			  		<html:option value="2">Icd(Default) & Hospital Code</html:option>
  			 		<html:option value="3">Hospital(Default) & Icd Code</html:option>
  			 		<html:option value="4">ICD-O only</html:option>
  			 		<html:option value="5">ICD-O(Default) & ICD</html:option>
  			 		<html:option value="6">ICD(Default) & ICD-O</html:option>
  			 		--></html:select>
	          </div>
       </td>                                                 
  </tr>
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 <font color="#FF0000">*</font>
	  <bean:message key="episodeCloseDays"/>&nbsp;
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	           
	         	&nbsp;<html:text name="ModifyUnitLayoutFB" maxlength="3" size="3" onkeypress="return validateNumeric(event)" property="defaultCloseDays" tabindex="1"/>
	         	<label>Days</label>	         
	         </div>
      </td>                                           
  </tr>	
  
  <%if(Config.RENEWAL_TYPE=="4" || Config.RENEWAL_TYPE=="5"){ %>
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <font color="#FF0000">*</font><bean:message key="isExpiry"/>&nbsp;
		              </font>
		            </div>
	   </td>      
	   <td width="20%"  class="tdfont">
	         <div align="left" >
	                &nbsp;<html:select name="ModifyUnitLayoutFB" tabindex="1" property="isExpiry" onchange="submitTile('SHOWROW')" styleClass="regcbo" >
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:option value="0">Not Required</html:option>
  			 		<html:option value="1">Month Based</html:option>
  			 		<html:option value="2">Day Based</html:option>
	   		 	</html:select>
	          </div>
       </td>                                                 
  </tr>
  <logic:equal name="ModifyUnitLayoutFB" property="isExpiry" value="1">
  <tr id="rowMonth" >
  	<td width="20%" class="tdfonthead">
  		<div align="right"   id="divMonthLabel">
  			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
            <font color="#FF0000">*</font><bean:message key="month"/>
  		</div>
  	</td>
  	<td width="20%" class="tdfont">
  		<div align="left" id="divDayControl">
  			&nbsp;<html:select name="ModifyUnitLayoutFB" tabindex="1" property="expiryMonth" styleClass="regcbo" >
			<html:option value="-1">Select Value</html:option>
  			<html:option value="31-Jan">January</html:option>
  			<html:option value="28-Feb">February</html:option>
  			<html:option value="31-Mar">March</html:option>
  			<html:option value="30-Apr">April</html:option>
  			<html:option value="31-May">May</html:option>
  			<html:option value="30-Jun">June</html:option>
  			<html:option value="31-Jul">July</html:option>
  			<html:option value="31-Aug">August</html:option>
  			<html:option value="30-Sep">September</html:option>
  			<html:option value="31-Oct">October</html:option>
  			<html:option value="30-Nov">November</html:option>
  			<html:option value="31-Dec">December</html:option>
  			</html:select>
  		</div>
  	
  		
  	</td>
  </tr>
  </logic:equal>
   <logic:equal name="ModifyUnitLayoutFB" property="isExpiry" value="2">
  <tr id="rowDay" >
  	
  	<td width="20%"  class="tdfonthead">
  		<div align="right" id="divDayLabel">
  			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
            <font color="#FF0000">*</font><bean:message key="day"/>&nbsp;
  		</div>
  	</td>
  	<td width="20%" class="tdfont">	
  		
  		<div align="left" id="divMonthControl">
  			&nbsp;<html:text name="ModifyUnitLayoutFB" maxlength="3" size="3" property="expiryDay" tabindex="1"/>
  		</div>
  	</td>
  	
  
  </tr>
  </logic:equal>
  <%} %>
  
 
 	<tr>	  
	 	 <td width="50%"  class="tdfonthead" colspan="1">
				<div align="right">	           
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    		<font color="#FF0000">*</font>
			        	<bean:message key="cardPrintType"/>&nbsp;
			        	
			        </font>
			    </div>
		   </td>     
		   <td width="50%"  class="tdfont">
		         <div align="left" >
		         &nbsp;<html:radio  name="ModifyUnitLayoutFB" property="cardPrintType" value="<%=RegistrationConfig.PRINT_TYPE_CARD %>" tabindex="1"/>
		         		Card
		         <html:radio  name="ModifyUnitLayoutFB" property="cardPrintType" value="<%=RegistrationConfig.PRINT_TYPE_LABEL %>" tabindex="1"/>
		         		Label
		         </div>
		    </td>     
	  </tr>
	  <tr>	  
	 	 <td width="50%"  class="tdfonthead" colspan="1" style="display:none">
				<div align="right">	           
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    		<font color="#FF0000">*</font>
			        	<bean:message key="noOfCopies"/>&nbsp;
			        	
			        </font>
			    </div>
		   </td>     
		   <td width="50%"  class="tdfont" style="display:none">
		         <div align="left" >
		         &nbsp;<html:select name="ModifyUnitLayoutFB" tabindex="1" styleClass="regcbo" property="noOfCopiesOfCard"  >
						<html:option value="-1">Select Value</html:option>
			  			<html:option value="1">1</html:option>
			  			<html:option value="2">2</html:option>
			  			<html:option value="3">3</html:option>
			  			<html:option value="4">4</html:option>
			  			<html:option value="5">5</html:option>
			  			<html:option value="6">6</html:option>
			  			<html:option value="7">7</html:option>
			  			<html:option value="8">8</html:option>
			  			<html:option value="9">9</html:option>
			  			</html:select>
		         </div>
		    </td>     
	  </tr>
	  <tr>
	  		<td width="50%"  class="tdfonthead" style="display:none">
				<div align="right">	           
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    		
			        	<bean:message key="fileNoRequired"/>&nbsp;
			        	
			        </font>
			    </div>
		   </td>      
		   <td width="50%"  class="tdfont" style="display:none">
		         <div align="left" >
		         &nbsp;<html:select name="ModifyUnitLayoutFB" property="fileNoRequired" styleClass="regcbo" onchange="checkFileRequired(this)" tabindex="1">
		         		<html:option value="<%=RegistrationConfig.FILE_NO_REQUIRED_FALSE %>">Not Required</html:option>
		         		<html:option value="<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE %>">Department Wise</html:option>
		         		<html:option value="<%=RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE %>">Unit Wise</html:option>
		         	</html:select>
		         </div>
		    </td>     
	  </tr>
	  <tr>
	  <td colspan="2">
	  <div id="fileRequiredDiv" style="display: block;">
	  <table width="100%" cellspacing="0">
	  <tr>
	  		<td width="50%"  class="tdfonthead">
				<div align="right">	           
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		    		<font color="#FF0000">*</font>
			        	<logic:equal  name="ModifyUnitLayoutFB" property="fileNoRequired" value="<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE %>">
			        	<bean:message key="department"/>
			        	</logic:equal>
			        	<logic:equal  name="ModifyUnitLayoutFB" property="fileNoRequired" value="<%=RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE %>">
			        	<bean:message key="unit"/>
			        	</logic:equal>
			        	<bean:message key="grouping"/>&nbsp;
			        </font>
			    </div>
		   </td>      
		   <td width="50%"  class="tdfont">
		         <div align="left" >
		        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		         	<html:radio name="ModifyUnitLayoutFB" property="isOtherDept" value="0" onclick="getOtherUnitDetail(this)" tabindex="1">
		         	</html:radio>
		         	<bean:message key="same"/>
	         		<logic:equal  name="ModifyUnitLayoutFB" property="fileNoRequired" value="<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE %>">
		        	<bean:message key="department"/>
		        	</logic:equal>
		        	<logic:equal  name="ModifyUnitLayoutFB" property="fileNoRequired" value="<%=RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE %>">
		        	<bean:message key="unit"/>
		        	</logic:equal>
		         	<html:radio name="ModifyUnitLayoutFB" property="isOtherDept" value="1" onclick="getOtherUnitDetail(this)" tabindex="1">
		         	</html:radio>
		         	<bean:message key="other"/>
	         		<logic:equal  name="ModifyUnitLayoutFB" property="fileNoRequired" value="<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE %>">
		        	<bean:message key="department"/>
		        	</logic:equal>
		        	<logic:equal  name="ModifyUnitLayoutFB" property="fileNoRequired" value="<%=RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE %>">
		        	<bean:message key="unit"/>
		        	</logic:equal>
		         	</font>
		         </div>
		    </td>     
	  </tr>
	  
	  <logic:present name="<%=RegistrationConfig.DEPT_WHOSE_FILE_REQ_LIST%>">
	  <tr><td colspan="2">
	  <div id="deptDiv" style="display: none">
	  <table width="100%" cellspacing="0">
	  <tr>
	  		<td width="50%"  class="tdfonthead">
				<div align="right">	      
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<font color="#FF0000">*</font>   
			    		<bean:message key="department"/>
			    		<bean:message key="group"/>&nbsp;
			        </font>
			    </div>
		   </td>      
		   <td width="50%"  class="tdfont">
		         <div align="left" >
		        &nbsp;<html:select name="ModifyUnitLayoutFB" property="fileDepartmentCode" styleClass="regcbo" onchange="getFileNoDetail(this)" tabindex="1">
		         	<html:option value="-1">Select</html:option>
		         	<html:options collection="<%=RegistrationConfig.DEPT_WHOSE_FILE_REQ_LIST %>" labelProperty="label" property="value"/>
		         	</html:select>
		         </div>
		    </td>     
	  </tr>
	  </table>
	  </div>
	  </td></tr>
	  </logic:present>
	  
	  <logic:present name="<%=RegistrationConfig.UNIT_WHOSE_FILE_REQ_LIST%>">
	  <tr><td colspan="2">
	  <div id="unitDiv" style="display: none">
	  <table width="100%" cellspacing="0">
	  <tr>
	  		<td width="50%"  class="tdfonthead">
				<div align="right">	      
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<font color="#FF0000">*</font>   
			    		<bean:message key="unit"/> <bean:message key="group"/>&nbsp;
			        </font>
			    </div>
		   </td>      
		   <td width="50%"  class="tdfont">
		         <div align="left" >
		        &nbsp;<html:select name="ModifyUnitLayoutFB" property="fileDepartmentUnitCode" styleClass="regcbo" onchange="getFileNoDetail(this)" tabindex="1">
		         	<html:option value="-1">Select</html:option>
		         	<html:options collection="<%=RegistrationConfig.UNIT_WHOSE_FILE_REQ_LIST %>" labelProperty="label" property="value"/>
		         	</html:select>
		         </div>
		    </td>     
	  </tr>
	  </table>
	  </div>
	  </td></tr>
	 
	  </logic:present>
	  
	  <tr>
	  		<td width="50%"  class="tdfonthead">
				<div align="right">	
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<font color="#FF0000">*</font>     
			    		<bean:message key="filePrefix"/>&nbsp;
			        </font>
			    </div>
		   </td>      
		   <td width="50%"  class="tdfont">
		         <div align="left" >
		         	&nbsp;<html:text name="ModifyUnitLayoutFB" property="filePrefix" maxlength="20" tabindex="1"/>
		         </div>
		    </td>     
	  </tr>
	  
	  <tr>
	  		<td width="50%"  class="tdfonthead">
				<div align="right">	           
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    	<font color="#FF0000">*</font>
			    		<bean:message key="startNo"/>&nbsp;
			        </font>
			    </div>
		   </td>      
		   <td width="50%"  class="tdfont">
		         <div align="left" >
		         	&nbsp;<html:text name="ModifyUnitLayoutFB" property="startNo" maxlength="10" onkeypress="return validateNumeric(event);" tabindex="1"/>
		         </div>
		    </td>     
	  </tr>
	   <tr>	  
	 	 <td width="50%"  class="tdfonthead" colspan="1">
				<div align="right">	           
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    		<font color="#FF0000">*</font>
			        	<bean:message key="filePrintType"/>&nbsp;
			        	
			        </font>
			    </div>
		   </td>     
		   <td width="50%"  class="tdfont">
		         <div align="left" >
		         &nbsp;<html:radio  name="ModifyUnitLayoutFB" property="filePrintType" value="<%=RegistrationConfig.PRINT_TYPE_FILE %>" tabindex="1"/>
		         		File
		         <html:radio  name="ModifyUnitLayoutFB" property="filePrintType" value="<%=RegistrationConfig.PRINT_TYPE_LABEL %>" tabindex="1"/>
		         		Label
		         </div>
		    </td>     
	  </tr>
	  <tr>	  
	 	 <td width="50%"  class="tdfonthead" colspan="1">
				<div align="right">	           
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    		<font color="#FF0000">*</font>
			        	<bean:message key="noOfCopies"/>&nbsp;
			        	
			        </font>
			    </div>
		   </td>     
		   <td width="50%"  class="tdfont">
		         <div align="left" >
		         &nbsp;<html:select name="ModifyUnitLayoutFB" tabindex="1" styleClass="regcbo" property="noOfCopiesOfFile"  >
						<html:option value="-1">Select Value</html:option>
			  			<html:option value="1">1</html:option>
			  			<html:option value="2">2</html:option>
			  			<html:option value="3">3</html:option>
			  			<html:option value="4">4</html:option>
			  			<html:option value="5">5</html:option>
			  			<html:option value="6">6</html:option>
			  			<html:option value="7">7</html:option>
			  			<html:option value="8">8</html:option>
			  			<html:option value="9">9</html:option>
			  			</html:select>
		         </div>
		    </td>     
	  </tr>
	  <tr>	  
	 	 <td width="50%"  class="tdfonthead" colspan="1">
				<div align="right">	           
			    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			    		<font color="#FF0000">*</font>
			        	<bean:message key="printingProcess"/>&nbsp;
			        	
			        </font>
			    </div>
		   </td>     
		   <td width="50%"  class="tdfont">
		         <div align="left" >
		         &nbsp;<html:radio  name="ModifyUnitLayoutFB" property="printingProcess" value="<%=RegistrationConfig.PRINT_ON_SAME_SYSTEM %>" tabindex="1"/>
		         		Same
		         <html:radio  name="ModifyUnitLayoutFB" property="printingProcess" value="<%=RegistrationConfig.PRINT_ON_OTHER_SYSTEM %>" tabindex="1"/>
		         		Other
		         </div>
		    </td>     
	  </tr>
	  </table>
	 </div> 
	 </td>
	 </tr> 
  
  
   <tr style="display: none;">
	   <td width="50%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <font color="#FF0000">*</font>
		              <bean:message key="effectiveFrom"/>&nbsp;
		              </font>
		            </div>
	   </td>  
 <bean:define name="ModifyUnitLayoutFB" property="effectiveFrom" id="effFrom" type="java.lang.String" />   
 
	   <td class="tdfont" >
	  	   <logic:equal name="ModifyUnitLayoutFB" property="choice" value="<%=RegistrationConfig.CHOICE_ADDITION%>">
	  	   <div id="divDatePicker" align="left">
	       <his:date name="effectiveFrom" dateFormate="%d-%b-%Y" value="<%=effFrom %>"/>
	       </div>
	       </logic:equal>
	       <logic:equal name="ModifyUnitLayoutFB" property="choice" value="<%=RegistrationConfig.CHOICE_MISTAKE%>">
	  	   <div id="divDateText" align="left">
	       <html:text name="ModifyUnitLayoutFB" property="effectiveFrom" readonly="true" tabindex="1"/>
	       </div>
	       </logic:equal>
	   </td>
  </tr>
  
  <tr style="display: none;">
  
	   <td width="50%" class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <bean:message key="effectiveTo"/>&nbsp;
		              </font>
		            </div>
	   </td>     
<bean:define name="ModifyUnitLayoutFB" property="effectiveTo" id="effTo" type="java.lang.String" />
					<%if (effTo.equalsIgnoreCase("") || effTo == null)
							effTo = "";

						%>
	   <td class="tdfont">
	   		<div align="left">

	       	<his:date name="effectiveTo" dateFormate="%d-%b-%Y" value="<%=effTo %>" />
	       	</div>
	   </td>
  </tr> 
  
   
  
  
  
  
  <tr>

					<td width="50%" class="tdfonthead" align="right">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="remarks" />&nbsp; 
					</font>
					</div>
					</td>
					<td width="50%" class="tdfont">
					
						<div align="left">
						
						&nbsp;<html:textarea name="ModifyUnitLayoutFB" property="remarks" tabindex="1"/>
						
						
						</div>
					
					</td>
					
	</tr>
	<tr>
      <td width="50%" class="tdfonthead">
		  <div align="right">	           
			  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <font color="#FF0000">*</font>
			  <bean:message key="isActive"/>&nbsp;
			   </font>
		  </div>
      </td>  
      <td width="50%" class="tdfont">
         <div align="left">	 
          &nbsp;<html:select name="ModifyUnitLayoutFB" tabindex="1" property="isValid" styleClass="regCbo">
		 	<html:option value="-1">Select Value</html:option>
		 	<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option>
			<html:option value="<%=Config.IS_VALID_INACTIVE %>">In Active</html:option>
   		 	</html:select>
   		 </div>
      </td>  
  	</tr> 
	
 </table>
       
 </his:ContentTag>

 <html:hidden name="ModifyUnitLayoutFB" property="transactionMode"/>
  <html:hidden name="ModifyUnitLayoutFB" property="saveMode"/>
    <html:hidden name="ModifyUnitLayoutFB" property="source"/>
    <html:hidden name="ModifyUnitLayoutFB" property="entryDate" value="<%=sysDate %>"/>
    <html:hidden name="ModifyUnitLayoutFB" property="isValid" />
    <html:hidden name="ModifyUnitLayoutFB" property="departmentUnitCode"/>
    <html:hidden name="ModifyUnitLayoutFB" property="unitSerialNo"/>
	<html:hidden name="ModifyUnitLayoutFB" property="dataFoundDeptWise"/> 
	<html:hidden name="ModifyUnitLayoutFB" property="dataFoundOtherDeptWise"/> 
	<html:hidden name="ModifyUnitLayoutFB" property="deptCode"/> 
	<html:hidden name="ModifyUnitLayoutFB" property="prefixLength"/> 
	<html:hidden name="ModifyUnitLayoutFB" property="oldPrefix"/> 
	<html:hidden name="ModifyUnitLayoutFB" property="oldStartNo"/> 
	<html:hidden name="ModifyUnitLayoutFB" property="unitCode"/> 
	<html:hidden name="ModifyUnitLayoutFB" property="oldFilenorequired"/> 

 <his:ButtonToolBarTag>
    
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) validateModifyUnitMaster()" tabindex="1" onclick="validateModifyUnitMaster();" >
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('VIEW')" onkeypress="if(event.keyCode==13) submitTile('VIEW')">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
          
      
 </his:ButtonToolBarTag> 
 </his:TransactionContainer>
 </html:form>
 <his:status/>
 </body>
 </html>
 <%}catch(Exception e){e.printStackTrace();} %>
 