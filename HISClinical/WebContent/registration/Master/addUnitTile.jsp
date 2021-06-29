<%try{ %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>    

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>


<script type="text/javascript"><!--
window.onload=function()
{

	showDoctorUnit();
	load()
}


function load()
{
	//alert("onload");
	//alert("document.getElementsByName('fileNoRequired')[0] ="+document.getElementsByName("fileNoRequired")[0])
	showFileGenOption(document.getElementsByName("fileNoRequired")[0])
	if(document.getElementsByName("isOtherDept")){
		if(document.getElementsByName("isOtherDept")[0].checked){
			showOtherdept(document.getElementsByName("isOtherDept")[0])
		}
		else
			showOtherdept(document.getElementsByName("isOtherDept")[1])
	}
}

function validateDate()
{ var valid=true;
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

function ValidateAddUnitMaster(){


	effectiveFrom = document.getElementsByName("effectiveFrom")[0];
	effectiveTo = document.getElementsByName("effectiveTo")[0];
	entryDate = document.getElementsByName("entryDate")[0];

	result = true;
	//alert(result);
	if(validateDoctorUnit() &&
	isEmpty(document.forms[0].unitName,"Unit Name")&&
	isSelected(document.forms[0].isGeneral,"Unit Type") &&
	isSelected(document.forms[0].locationCode,"Unit Location") &&
	isSelected(document.forms[0].diagnosisCodeType,"Diagnosis Type Code") &&

	isEmpty(document.forms[0].defaultCloseDays,"Default Close Days")&&
	validateIsExpiry() &&
	validateDayMonth() &&
	compareDateCall(entryDate,effectiveFrom,2,"Current Date","Effective From") &&
	validateDate() 
	&& validateCardPrintFlag()
	//&& validateIsFileRequired()
	//&& validateFilePrintFlag()
	)
	{
	//alert("In end");
	return result;
	}

}

function validateIsExpiry()
{
	//alert("In validateIsExpiry");
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

function validateDoctorUnit()
{
	var valid=false;
	if(document.getElementsByName("isUnit")[0].checked)
		valid=true;
	else
	{
		if(isSelected(document.forms[0].unitDoctorName,"Doctor Name"))
			valid=true;
		else
			valid=false;	
	}
	
	return valid;	
}

function submitTile(mode)
{
   document.getElementsByName("transactionMode")[0].value=mode;  
    document.forms[0].submit();
}

function validateAlphabetsWithDotsOnly(e)
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
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;

	// alphas and space
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
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

function showFileGenOption(obj){
	if(obj.value!="<%=RegistrationConfig.FILE_NO_REQUIRED_FALSE%>" && obj.value!="-1"){
		document.getElementById("fileRequiredDiv").style.display="block"
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
		
		///document.getElementsByName("fileDepartmentCode")[0].value="-1"
		document.getElementsByName("unitCode")[0].value=""
		document.getElementsByName("fileDepartmentUnitCode")[0].value="-1"
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
	//alert("In validateCardPrintFlag");
	var valid=true;
	var cardPrintType=document.getElementsByName("cardPrintType");
	if(cardPrintType[0].checked==false && cardPrintType[1].checked==false){
		alert("Please Select Card Print Type");
		return false;
	}
	//if(isSelected(document.getElementsByName("noOfCopiesOfCard")[0],"No. Of Copies for Card")){
	//	valid=true;
	//}
	//else
	//	 return false;
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

--></script>

<body >
<html:form action="/master/addUnit">
<his:TransactionContainer>

<%@ page import ="java.util.*,registration.*,hisglobal.vo.*,hisglobal.presentation.*" %>
<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy"); %>
	
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
%>

<his:statusNew>
	<%varIsNewStatus=true;
	 varStatus="New";%>
</his:statusNew>


<his:TitleTag name="Add Unit">			
	
</his:TitleTag> 

<his:ContentTag>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">    
  <tr><bean:define id="clientFlag" name="<%=Config.CLIENT_NAME%>"></bean:define>
      <td width="20%" class="tdfonthead">
	  <div align="right">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	
	  <bean:message key="department"/>&nbsp;
	  </font>
	  </div>
      </td>  
       <td width="20%" class="tdfont">
	  <div align="left">
      
      <logic:equal name="AddUnitFB" property="source" value="fromview">
       &nbsp;<bean:write name="AddUnitFB" property="departmentName"/>
         <html:hidden name="AddUnitFB" property="departmentName"/>      
	     <html:hidden name="AddUnitFB" property="departmentCode"/>      
      </logic:equal>
      </div>
      <logic:notEqual name="AddUnitFB" property="source" value="fromview">
		  <html:select name="AddUnitFB" tabindex="1" property="departmentCode" styleClass="registrationCmb">
		  <html:option value="-1">Select Value</html:option>
	  	  <html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property="value" labelProperty="label" />
		  </html:select>
	  </logic:notEqual>
            </td>
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
		  			<html:radio name="AddUnitFB" property="isUnit" value="<%=RegistrationConfig.IS_UNIT_DEPARTMENT_UNIT %>" onclick="showDoctorUnit()"></html:radio>
		  			<bean:message key="deptUnitName"/>
		  			<html:radio name="AddUnitFB" property="isUnit" value="<%=RegistrationConfig.IS_UNIT_DOCTOR_UNIT %>" onclick="showDoctorUnit()"></html:radio>
		  			<bean:message key="doctorUnit"/>
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
			  			&nbsp;<html:select name="AddUnitFB" property="unitDoctorName" onchange="showDoctorNameInUnit()">
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
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">

	  <font color="#FF0000">*</font>
	  <bean:message key="unit"/>&nbsp;
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	           
	         &nbsp;<html:text name="AddUnitFB" maxlength="50" property="unitName" tabindex="1" onkeypress="return validateAlphaNumericOnly(event,this)" />	         
	         </div>
      </td>                                           
  </tr>	   
  
  	   
	  
         
	   
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		             <font color="#FF0000">*</font><bean:message key="unitType"/>&nbsp;
		              </font>
		            </div>
	   </td>      
	   <td width="20%"  class="tdfont">
	         <div align="left">
	              &nbsp;<html:select name="AddUnitFB" tabindex="1" property="isGeneral" styleClass="regcbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_UNITTYPE%>" property="value" labelProperty="label" />
	   		 	</html:select>
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
	         &nbsp;<html:select name="AddUnitFB" tabindex="1" property="locationCode" styleClass="regcbo">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>" property="value" labelProperty="label" />
	   		 	</html:select>
	         </div>
	    </td>     
  </tr>
  <tr>
	   <td width="20%"  class="tdfonthead" >
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		             <font color="#FF0000">*</font><bean:message key="diagnosisTypeCode"/>&nbsp;
		              </font>
		            </div>
	   </td>      
	   <td width="20%"  class="tdfont" >
	         <div align="left" >
	              &nbsp;<html:select name="AddUnitFB" tabindex="1" styleClass="regcbo" property="diagnosisCodeType"  >
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
	         &nbsp;<html:text name="AddUnitFB" maxlength="3" size="3"  tabindex="1" onkeypress="return validateNumeric(event)" property="defaultCloseDays"/>
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
	               &nbsp;<html:select name="AddUnitFB" tabindex="1" property="isExpiry" styleClass="regcbo" onchange="submitTile('SHOWROW')" >
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:option value="0">Not Required</html:option>
  			 		<html:option value="1">Month Based</html:option>
  			 		<html:option value="2">Day Based</html:option>
	   		 	</html:select>
	          </div>
       </td>                                                 
  </tr>
  <logic:equal name="AddUnitFB" property="isExpiry" value="1">
  <tr id="rowMonth" >
  	<td width="20%" class="tdfonthead">
  		<div align="right"   id="divMonthLabel">
  			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
            <font color="#FF0000">*</font><bean:message key="month"/>&nbsp;
  		</div>
  	</td>
  	<td width="20%" class="tdfont">
  		<div align="left" id="divDayControl">
  		&nbsp;<html:select name="AddUnitFB" tabindex="1" styleClass="regcbo" property="expiryMonth"  >
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
   <logic:equal name="AddUnitFB" property="isExpiry" value="2">
  <tr id="rowDay" >
  	
  	<td width="20%"  class="tdfonthead">
  		<div align="right" id="divDayLabel">
  			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
            <font color="#FF0000">*</font><bean:message key="day"/>&nbsp;
  		</div>
  	</td>
  	<td width="20%" class="tdfont">	
  		
  		<div align="left" id="divMonthControl">
  			&nbsp;<html:text name="AddUnitFB" maxlength="3" size="5" property="expiryDay" onkeypress="return validateNumeric(event)" tabindex="1"/>
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
		         &nbsp;<html:radio  name="AddUnitFB" property="cardPrintType" value="<%=RegistrationConfig.PRINT_TYPE_CARD %>" tabindex="1"/>
		         		Card
		         <html:radio  name="AddUnitFB" property="cardPrintType" value="<%=RegistrationConfig.PRINT_TYPE_LABEL %>" tabindex="1"/>
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
		         &nbsp;<html:select name="AddUnitFB" tabindex="1" styleClass="regcbo" property="noOfCopiesOfCard"  >
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
		         &nbsp;<html:select name="AddUnitFB" property="fileNoRequired" styleClass="regcbo" onchange="checkFileRequired(this)" tabindex="1">
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
			        	<logic:equal  name="AddUnitFB" property="fileNoRequired" value="<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE %>">
			        	<bean:message key="department"/>
			        	</logic:equal>
			        	<logic:equal  name="AddUnitFB" property="fileNoRequired" value="<%=RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE %>">
			        	<bean:message key="unit"/>
			        	</logic:equal>
			        	<bean:message key="grouping"/>&nbsp;
			        </font>
			    </div>
		   </td>      
		   <td width="50%"  class="tdfont">
		         <div align="left" >
		        	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		         	<html:radio name="AddUnitFB" property="isOtherDept" value="0" onclick="getOtherUnitDetail(this)" tabindex="1">
		         	</html:radio>
		         	<bean:message key="same"/>
	         		<logic:equal  name="AddUnitFB" property="fileNoRequired" value="<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE %>">
		        	<bean:message key="department"/>
		        	</logic:equal>
		        	<logic:equal  name="AddUnitFB" property="fileNoRequired" value="<%=RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE %>">
		        	<bean:message key="unit"/>
		        	</logic:equal>
		         	<html:radio name="AddUnitFB" property="isOtherDept" value="1" onclick="getOtherUnitDetail(this)" tabindex="1">
		         	</html:radio>
		         	<bean:message key="other"/>
	         		<logic:equal  name="AddUnitFB" property="fileNoRequired" value="<%=RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE %>">
		        	<bean:message key="department"/>
		        	</logic:equal>
		        	<logic:equal  name="AddUnitFB" property="fileNoRequired" value="<%=RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE %>">
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
		        &nbsp;<html:select property="fileDepartmentCode" styleClass="regcbo" onchange="getFileNoDetail(this)" tabindex="1">
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
		        &nbsp;<html:select property="fileDepartmentUnitCode" styleClass="regcbo" onchange="getFileNoDetail(this)" tabindex="1">
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
		         	&nbsp;<html:text name="AddUnitFB" property="filePrefix" maxlength="20" tabindex="1"/>
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
		         	&nbsp;<html:text name="AddUnitFB" property="startNo" maxlength="10" onkeypress="return validateNumeric(event);" tabindex="1"/>
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
		         &nbsp;<html:radio  name="AddUnitFB" property="filePrintType" value="<%=RegistrationConfig.PRINT_TYPE_FILE %>" tabindex="1"/>
		         		File
		         <html:radio  name="AddUnitFB" property="filePrintType" value="<%=RegistrationConfig.PRINT_TYPE_LABEL %>" tabindex="1"/>
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
		         &nbsp;<html:select name="AddUnitFB" tabindex="1" styleClass="regcbo" property="noOfCopiesOfFile"  >
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
		         &nbsp;<html:radio  name="AddUnitFB" property="printingProcess" value="<%=RegistrationConfig.PRINT_ON_SAME_SYSTEM %>" tabindex="1"/>
		         		Same
		         <html:radio  name="AddUnitFB" property="printingProcess" value="<%=RegistrationConfig.PRINT_ON_OTHER_SYSTEM %>" tabindex="1"/>
		         		Other
		         </div>
		    </td>     
	  </tr>
	  </table>
	 </div> 
	 </td>
	 </tr> 
  
  
  <tr style="display: none;">
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <font color="#FF0000">*</font>
		             <bean:message key="effectiveFrom"/>
		              </font>
		            </div>
	   </td>      
	   <td class="tdfont">
	       <div align="left">
	       <his:date name='<%="effectiveFrom"%>' dateFormate="%d-%b-%Y"  value="<%=sysDate%>"/>
	       </div>
	   </td>
  </tr>
  
  <tr style="display: none;">
  
	   <td width="20%" class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <bean:message key="effectiveTo"/>
		              </font>
		            </div>
	   </td>      
	   <td class="tdfont">
	   		<div align="left">

	       	<his:date name='<%="effectiveTo"%>' dateFormate="%d-%b-%Y" />
	       	</div>
	   </td>
  </tr> 
  
  
  
  
  
  
</table>  
</his:ContentTag>	 	   
 
<his:ButtonToolBarTag>   
	  	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick =" ValidateAddUnitMaster() && submitTile('SAVE')" onkeypress="if(event.keyCode==13) ValidateAddUnitMaster() && submitTile('SAVE')">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('VIEW')" onkeypress="if(event.keyCode==13) submitTile('VIEW')">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">          	
</his:ButtonToolBarTag>


<input type= "hidden" name="transactionMode" /> 
<html:hidden name="AddUnitFB" property="source" /> 
<html:hidden name="AddUnitFB" property="entryDate" value="<%=sysDate%>" /> 
<html:hidden name="AddUnitFB" property="dataFoundDeptWise"/> 
<html:hidden name="AddUnitFB" property="dataFoundOtherDeptWise"/> 
<html:hidden name="AddUnitFB" property="deptCode"/> 
<html:hidden name="AddUnitFB" property="prefixLength"/> 
<html:hidden name="AddUnitFB" property="oldPrefix"/> 
<html:hidden name="AddUnitFB" property="oldStartNo"/> 
<html:hidden name="AddUnitFB" property="unitCode"/> 
</his:TransactionContainer>
</html:form>
<his:status/>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();}%>