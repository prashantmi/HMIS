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


<script>
window.history.forward()

function isEmptyWithoutFocus(obj,name)
{
  	if(obj!=null && obj!='undefined')
	{
	var value=obj.value;
		if(value=="" || value=="-1")
		{
			alert("Please Enter the "+ name);
			obj.value=value;
			return false;
		}
		else
		return true;
	}
	return false;
}

function submitForm(transactionMode)
{

if(transactionMode=='GET_BLOCK')
{
document.forms[0].block.value=-1;
document.forms[0].floor.value=-1;
document.forms[0].room.value=-1;


}else
if(transactionMode=='GET_FLOOR')
{

document.forms[0].floor.value=-1;
document.forms[0].room.value=-1;

}else
if(transactionMode=='GET_ROOM')
{

document.forms[0].room.value=-1;

}
document.forms[0].transactionMode.value=transactionMode;
document.forms[0].submit();
}

function validateDepartmentType()
{ var valid=false;
	if(document.getElementsByName("departmentType")[0].value=="-1")
	{
	alert("Select the Type of department")
	valid=false
	}
	else
	{
		valid=true
	}
	
	return valid;
}


function validateCombo()
{ var valid=true;
	if(!document.getElementsByName('effectiveTo')[0].value=="")
	   {
	   if(!compareDateCall(effectiveFrom,effectiveTo,2,"Effective From Date","Effective To Date"))
	   valid= false;
	   }
	if(document.getElementsByName('hodCode')[0].value=="-1")
		{
		alert("Please Select the HOD for the Department");
		valid= false;
		}   
	if(document.getElementsByName('locationCode')[0].value=="-1")
		{
		alert("Please Select the Location for the Department");
		valid= false;
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

function validateLocationMaster()
{
//alert("hi");
	var valid=true;
if(
	isEmpty(document.forms[0].locationDescription,"Location Description") &&
	isSelected(document.forms[0].locationTypeCode,"Location Type") 
	// && isSelected(document.forms[0].building,"Building") &&
	//isSelected(document.forms[0].block,"Block") &&
	//isSelected(document.forms[0].floor,"Floor") 
	//&&	compareDateCall( document.getElementsByName('entryDate')[0], document.getElementsByName('effectiveDate')[0],2,"Current Date","Effective Date")
	 )
	{
	 submitForm('SAVE');
	}
}
	
	  
	       

function compareDateCall(d1,d2,mode,l1,l2){
//alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmptyWithoutFocus(d1,l1) && isEmptyWithoutFocus(d2,l2)){
 if(compareDate(d1,d2, mode)){
		valid = true;
	}
 else {
	alert(l1+" can not be greater than "+l2);
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



</script>

<body>
<html:form action="/master/addLocationMaster">
<his:TransactionContainer>

<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>
	
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
  String  currentDate="";
    currentDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy HH:mm");

 String  effectiveDate="";
    effectiveDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy");
%>
<his:statusNew>
	<%varIsNewStatus=true;
	
	 varStatus="New";%>
</his:statusNew>

 <%boolean varReadOnly=false;%>	


<his:TitleTag name="Add Location">			
</his:TitleTag> 
<%if (varIsNewStatus) 
{
System.out.println("inside sattus new tag");%>

<his:ContentTag>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">   
  
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="right">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b><font color="#FF0000">*</font>&nbsp;<bean:message key="locationDesc"/></b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	   	<html:text name="AddLocationFB"  maxlength="100" size="38" styleClass="textbox" property="locationDescription" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)"/>  
	     
	  </div>
      
       </td>
  </tr>
  
  <tr>
  
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b><font color="#FF0000">*</font>&nbsp;<bean:message key="locationType"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	          <html:select name="AddLocationFB" tabindex="1" property="locationTypeCode" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION_TYPE%>">
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION_TYPE%>" property="value" labelProperty="label" />
  			 		</logic:present>
	   		 	</html:select>
	         		  
	               
	         </div>
      </td>                                           
  </tr>	     
 <tr>
      <td width="20%" class="tdfonthead">
	  <div align="right">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	 
	  <b>
	  <bean:message key="landmark"/>
	  </b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	   	<html:text name="AddLocationFB"  maxlength="100" tabindex="1" size="38" styleClass="textbox" property="landMark" onkeypress="return validateAlphabetsOnly(event,this)"/>  
	     
	  </div>
      
       </td>
  </tr>
	  
  <%if(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_NO))
  { %>
  <tr>
  
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	 
	  <bean:message key="building"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	         
		<html:text name="AddLocationFB" property="building" styleClass="textbox" tabindex="1"></html:text>	         		  
	               
	         </div>
      </td>                                           
  </tr>	 
   <tr>
  
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  
	  <bean:message key="block"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	          
	         <html:text name="AddLocationFB" property="block" styleClass="textbox" tabindex="1"></html:text>		  
	               
	         </div>
      </td>                                           
  </tr>	   
   <tr>
  
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  
	  <bean:message key="floor"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	          
	         	  <html:text name="AddLocationFB" property="floor" styleClass="textbox" tabindex="1"></html:text>			  
	               
	         </div>
      </td>                                           
  </tr>	   
   <tr>
  
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  
	  <bean:message key="roomNo"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	          
	         	 <html:text name="AddLocationFB" property="room" styleClass="textbox" tabindex="1"></html:text>	  
	               
	         </div>
      </td>                                           
  </tr>	
  
  <% } else
  if(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES))
  
   { %>
  <tr>
  
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b><bean:message key="building"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	          <html:select name="AddLocationFB" tabindex="1" property="building" styleClass="regCbo" onchange="submitForm('GET_BLOCK')">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_BUILDING%>">
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_BUILDING%>" property="value" labelProperty="label" />
  			 		</logic:present>
	   		 	</html:select>
	         		  
	               
	         </div>
      </td>                                           
  </tr>	 
   <tr>
  
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b><bean:message key="block"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	          <html:select name="AddLocationFB" tabindex="1" property="block" styleClass="regCbo" onchange="submitForm('GET_FLOOR')">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_BLOCK%>">
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_BLOCK%>" property="value" labelProperty="label" />
  			 		</logic:present>
	   		 	</html:select>
	         		  
	               
	         </div>
      </td>                                           
  </tr>	   
   <tr>
  
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b><bean:message key="floor"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	          <html:select name="AddLocationFB" tabindex="1" property="floor" styleClass="regCbo" onchange="submitForm('GET_ROOM')">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_FLOOR%>">
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_FLOOR%>" property="value" labelProperty="label" />
  			 		</logic:present>
	   		 	</html:select>
	         		  
	               
	         </div>
      </td>                                           
  </tr>	   
   <tr>
  
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>
	  
	  <bean:message key="roomNo"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	          <html:select name="AddLocationFB" tabindex="1" property="room" styleClass="regCbo">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ESTATE_ROOM%>">
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ESTATE_ROOM%>" property="value" labelProperty="label" />
  			 		</logic:present>
	   		 	</html:select>
	         		  
	               
	         </div>
      </td>                                           
  </tr>	
  <%} %> 
 
 <!--  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="right">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b><font color="#FF0000">*</font>&nbsp;<bean:message key="effectiveDate"/></b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  		<div align="left">
	    	<his:date name="effectiveDate" dateFormate="%d-%b-%Y" value="<%=effectiveDate%>" />
	    	</div>
      
       </td>
  </tr> -->
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="right">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b><font color="#FF0000">*</font>&nbsp;<bean:message key="isActive"/></b>
	  </font>
	  </div>
      </td>  
      
     <td width="20%" class="tdfont">
	         <div align="left">	 
	          <html:select name="AddLocationFB" tabindex="1" property="isValid" styleClass="regCbo">
			 	<html:option value="1">Active</html:option>
				<html:option value="2">In Active</html:option>
	   		 	</html:select>
	         		  
	               
	         </div>
      </td>  
  </tr> 
 
 
 
  	   
</table>
</his:ContentTag>  
<%} %>
<html:hidden name="AddLocationFB" property="transactionMode" />
<html:hidden name="AddLocationFB" property="entryDate"  value="<%=effectiveDate%>" />

<his:ButtonToolBarTag>   
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="validateLocationMaster()" onkeypress="if(event.keyCode==13) validateLocationMaster()">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">          	
</his:ButtonToolBarTag>

</his:TransactionContainer>
 <his:status/>
</html:form>
</body>
</html>