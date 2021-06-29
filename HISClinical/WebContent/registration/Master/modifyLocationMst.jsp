<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.master.controller.fb.ModifyLocationMstFB"%>
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

	var valid=true;
if(
	isEmpty(document.forms[0].locationDescription,"Location Description") &&
	isSelected(document.forms[0].locationTypeCode,"Location Type")
	//&& 	isSelected(document.forms[0].building,"Building") &&
	//isSelected(document.forms[0].block,"Block") &&
	//isSelected(document.forms[0].floor,"Floor") 
	//&&	compareDateCall( document.getElementsByName('entryDate')[0], document.getElementsByName('effectiveDate')[0],2,"Current Date","Effective Date")
	 )
	{
	 submitTile('MODIFY');
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

if(mode=="CLEAR")
    {
document.forms[0].locationDescription.value="";
document.forms[0].building.value="-1";
document.forms[0].block.value="-1";
document.forms[0].floor.value="-1";
document.forms[0].room.value="-1";
document.forms[0].locationTypeCode.value="-1";
document.forms[0].landMark.value="";
document.forms[0].isValid.value="";
//document.forms[0].effectiveDate.value="";
    }
else
if(mode=="CANCEL")
    {

   document.getElementsByName("transactionMode")[0].value="CANCEL";
   document.getElementsByName("hmode")[0].value="LIST";  
    document.forms[0].submit();
    }
else
if(mode=="MODIFY")
    {
   
   document.getElementsByName("transactionMode")[0].value=mode;  
   document.getElementsByName("hmode")[0].value="LIST";  
    document.forms[0].submit();
    }

}

</script>

<body>
<html:form action="/master/modifyLocationMaster">
<his:TransactionContainer>

<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*,hisglobal.vo.LocationMasterVO;" %>
	
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
  String  currentDate="";
    currentDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy HH:mm");
    
  String  effectiveDate="";
  effectiveDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy");
String hmode=request.getParameter("hmode");
 %>
<his:statusNew>
	<%
	varIsNewStatus=true;
	
	 varStatus="New";
	 %>
</his:statusNew>

 <%boolean varReadOnly=false;%>	



<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
<his:TitleTag name="View Location">			
</his:TitleTag> 
</logic:equal>

<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
<his:TitleTag name="Modify Location">			
</his:TitleTag> 
</logic:equal>

<%if (varIsNewStatus) 
{
%>

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
	    <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	  	
	  	 <html:text name="ModifyLocationFB"  maxlength="100" size="38" styleClass="textbox" property="locationDescription" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)" ></html:text>
	  	
	  	</logic:equal>
	  	
	  	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
	  	
	  			<html:text name="ModifyLocationFB"  maxlength="50" size="38" styleClass="textbox" property="locationDescription" tabindex="1" onkeypress="return validateAlphabetsOnly(event)" disabled="true"></html:text>
	          
	  	</logic:equal>
	   	  
	     
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
	         
	         <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	             <html:select name="ModifyLocationFB" tabindex="1" property="locationTypeCode" styleClass="regCbo"   >
			  		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION_TYPE%>" property="value" labelProperty="label" />
	   		 	</html:select>
	         </logic:equal>	 
	         	
	         <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW"> 
	             <html:select name="ModifyLocationFB" tabindex="1" property="locationTypeCode" styleClass="regCbo" disabled="true" >
			  		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION_TYPE%>" property="value" labelProperty="label" />
	   		 	</html:select>
	             
	          </logic:equal>	 
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
	  <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	 <html:text name="ModifyLocationFB"  maxlength="100" tabindex="1" size="38" styleClass="textbox" property="landMark" onkeypress="return validateAlphabetsOnly(event,this)" />
	  </logic:equal>
	  <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
	 <html:text name="ModifyLocationFB" maxlength="100" size="21" tabindex="1" property="landMark" readonly="true" />
	 
	 
	  </logic:equal>
	   	  
	     
	  </div>
      
       </td>
  </tr>
  
	  
  <%	
  if(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_NO))
  {
  
   %>
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
	         
	   <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	  	
	  	  <html:text name="ModifyLocationFB" property="building" styleClass="textbox" tabindex="1" ></html:text>
	  	
	  	</logic:equal>
	  	
	  	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
	  	
	  			<html:text name="ModifyLocationFB" property="building" styleClass="textbox" tabindex="1" disabled="true"></html:text>
	          
	  	</logic:equal>
			         		  
	               
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
	          
	     <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	  	
	  	    <html:text name="ModifyLocationFB" property="block" styleClass="textbox" tabindex="1" ></html:text>
	  	
	  	</logic:equal>
	  	
	  	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
	  	
	  			<html:text name="ModifyLocationFB" property="block" styleClass="textbox" tabindex="1" disabled="true"></html:text>
	          
	  	</logic:equal>
	          
	         		  
	               
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
	          
	   <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	  	
	  	    <html:text name="ModifyLocationFB" property="floor" styleClass="textbox" tabindex="1" ></html:text>
	  	
	  	</logic:equal>
	  	
	  	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
	  	
	  			 <html:text name="ModifyLocationFB" property="floor" styleClass="textbox" tabindex="1" disabled="true"></html:text>
	          
	  	</logic:equal>
	         	  			  
	               
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
	          
	           <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	  	     <html:text name="ModifyLocationFB" property="room" styleClass="textbox" tabindex="1" ></html:text>
	  	</logic:equal>
	  	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
	  	
	  			 <html:text name="ModifyLocationFB" property="room" styleClass="textbox" tabindex="1" disabled="true"></html:text>
	          
	  	</logic:equal>
	    
	         	  
	               
	         </div>
      </td>                                           
  </tr>	
  
  <% } 
  else 
  if(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES))
  
  { 
  
  
  %>
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
	     <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	  	     <html:select name="ModifyLocationFB" tabindex="1" property="building" styleClass="regCbo" onchange="submitForm('GET_BLOCK')" >
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_BUILDING%>" property="value" labelProperty="label" />
	   		 	</html:select>
	        
	  	</logic:equal>
	  	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
	  	
	  		
	          <html:select name="ModifyLocationFB" tabindex="1" property="building" styleClass="regCbo" onchange="submitForm('GET_BLOCK')" disabled="true">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_BUILDING%>" property="value" labelProperty="label" />
	   		 	</html:select>
	        
	  	</logic:equal>
	          
	         		  
	               
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
	     <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	  	      <html:select name="ModifyLocationFB" tabindex="1" property="block" styleClass="regCbo" onchange="submitForm('GET_FLOOR')" >
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_BLOCK%>" property="value" labelProperty="label" />
	   		 	</html:select>
	        
	  	</logic:equal>
	  	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
	  	
	  		
	          <html:select name="ModifyLocationFB" tabindex="1" property="block" styleClass="regCbo" onchange="submitForm('GET_FLOOR')" disabled="true">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_BLOCK%>" property="value" labelProperty="label" />
	   		 	</html:select>
	        
	  	</logic:equal>
	    
	    
	         		  
	               
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
	     <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	  	        <html:select name="ModifyLocationFB" tabindex="1" property="floor" styleClass="regCbo" onchange="submitForm('GET_ROOM')" >
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_FLOOR%>" property="value" labelProperty="label" />
	   		 	</html:select>
	  	
	  	</logic:equal>
	  	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
	  	
	  		
	           <html:select name="ModifyLocationFB" tabindex="1" property="floor" styleClass="regCbo" onchange="submitForm('GET_ROOM')" disabled="true">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_FLOOR%>" property="value" labelProperty="label" />
	   		 	</html:select>
	    
	  	</logic:equal>
	         
	         
	         		  
	               
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
	         
	    <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	  		
	          <html:select name="ModifyLocationFB" tabindex="1" property="room" styleClass="regCbo" >
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ESTATE_ROOM%>">
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ESTATE_ROOM%>" property="value" labelProperty="label" />
  			 		</logic:present>
	   		 	</html:select>
	    
	  	
	  	</logic:equal>
	  	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
	  	
	  		
	          <html:select name="ModifyLocationFB" tabindex="1" property="room" styleClass="regCbo" disabled="true">
			 		<html:option value="-1">Select Value</html:option>
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ESTATE_ROOM%>" property="value" labelProperty="label" />
	   		 	</html:select>
	    
	  	</logic:equal>
	  		
	          
	               
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
      
      String effDate=((ModifyLocationMstFB)(pageContext.findAttribute("ModifyLocationFB"))).getEffectiveDate();
          
       %>
      
      <td width="20%" class="tdfont">	  
	  		<div align="left">
	  		
	  		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	  		<his:date name="effectiveDate" dateFormate="%d-%b-%Y"  value="=effDate%>" />
	  		</logic:equal>
	  		
	  		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
			<html:text name="ModifyLocationFB"  maxlength="50" tabindex="1" size="38" styleClass="textbox" property="effectiveDate"  disabled="true"/>
	  		</logic:equal>
	  		
	  		
	  		
	    	</div>
      
       </td>
  </tr>-->
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
	    
	       <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	  		<html:select name="ModifyLocationFB" tabindex="1" property="isValid" styleClass="regCbo" >
			 	<html:option value="1">Active</html:option>
				<html:option value="2">In Active</html:option>
	   		 	</html:select>
	        
	  		</logic:equal>
	  	
	  		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="VIEW">
	  		<html:select name="ModifyLocationFB" tabindex="1" property="isValid" styleClass="regCbo" disabled="true">
			 	<html:option value="1">Active</html:option>
				<html:option value="2">In Active</html:option>
	   		 	</html:select>
	        
	  		</logic:equal>
	           		  
	               
	         </div>
      </td>  
  </tr> 
 
 
 
  	   
</table>
</his:ContentTag>  
<%} %>
<html:hidden name="ModifyLocationFB" property="transactionMode" />
<html:hidden name="ModifyLocationFB" property="chk" />
<html:hidden name="ModifyLocationFB" property="entryDate"  value="<%=effectiveDate%>" />
<html:hidden name="ModifyLocationFB" property="effectiveDate"/>
<html:hidden name="ModifyLocationFB" property="hmode"  value="<%=hmode%>" />

<his:ButtonToolBarTag>   

	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick ="validateLocationMaster()" onkeypress="if(event.keyCode==13) validateLocationMaster()">
	</logic:equal>
	
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
   	
   	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="MODIFY">
   	 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">          	
    </logic:equal>
    
    
         
</his:ButtonToolBarTag>

</his:TransactionContainer>
 <his:status/>
</html:form>
</body>
</html>