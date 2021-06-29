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

function validateDeptMaster()
{
	var valid=true;
      
	   entryDate = document.getElementsByName("entryDate")[0];
		

	
	if(isEmpty(document.forms[0].department,"Department Name")&&
	   isEmpty(document.forms[0].deptShortName,"Department Short Name")&&
	   validateDepartmentType() &&
	   validateCombo() 
       //&& validateRemarks()
       )       
	    {
	  	valid=true;
	  	submitTile('SAVE');
	  	}
	  	  
	else
	{
		valid=false;
		
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
<html:form action="/master/addDepartmentMaster">
<his:TransactionContainer>

<%@ page import ="java.util.*,registration.*,hisglobal.presentation.*" %>
	
<%
  boolean varIsNewStatus=false;	
  String varStatus="";
%>
<his:statusNew>
	<%varIsNewStatus=true;
	
	 varStatus="New";%>
</his:statusNew>

 <%boolean varReadOnly=false;%>	

<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT), "dd-MMM-yyyy"); %>
<his:TitleTag name="Add Department">			
</his:TitleTag> 
<%if (varIsNewStatus) 
{
%>

<his:ContentTag>
  <table width="100%" border="0" cellspacing="1" cellpadding="0">   
  <tr>
	   <td width="20%" class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font>&nbsp;<bean:message key="globalDepartment"/></b>
		              </font>
		            </div>
	   </td>
	
		 <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="AddDepartmentMstFB" tabindex="1" property="globalDepartment" styleClass="registrationCmb" onchange="if(this.value!='-1')submitTile('GETDETAILS')">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_GLOBAL_DEPARTMENT %>">
			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_GLOBAL_DEPARTMENT %>" property="value" labelProperty="label"/>
			 		</logic:present>
  			 		</html:select>
  			 		
	   		 	
	          </div>
       </td>  
	      	  
  </tr> 
  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="right">	           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b><font color="#FF0000">*</font>&nbsp;<bean:message key="department"/></b>
	  </font>
	  </div>
      </td>  
      
      <td width="20%" class="tdfont">	  
	  <div align="left">
	  <logic:notEmpty name="AddDepartmentMstFB" property="departmentCode">
    	  <%varReadOnly=true;%>
	   </logic:notEmpty>     
	  	<html:text name="AddDepartmentMstFB" tabindex="1" readonly="<%=varReadOnly%>" maxlength="50" size="52" property="department" onkeypress="return validateAlphabetsOnly(event,this)"/>  
	     
	  </div>
      
       </td>
  </tr>
  
  <tr>
  
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b><font color="#FF0000">*</font>&nbsp;<bean:message key="deptShortName"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	         
	         	<html:text name="AddDepartmentMstFB" maxlength="3" size="5" property="deptShortName" tabindex="1" onkeypress="return validateAlphabetsOnly(event,this)"/>	  
	               
	         </div>
      </td>                                           
  </tr>	   

   <tr>
	   <td width="20%" class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font>&nbsp;<bean:message key="departmentType"/></b>
		              </font>
		            </div>
	   </td>
	
		 <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="AddDepartmentMstFB" tabindex="1" property="departmentType" styleClass="registrationCmb">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT_TYPE %>">
			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT_TYPE %>" property="value" labelProperty="label"/>
			 		</logic:present>
  			 		</html:select>
  			 		
	   		 	
	          </div>
       </td>  
	      	  
  </tr> 
  
  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><bean:message key="hoddept"/></b>
		              </font>
		            </div>
	   </td>      
	   <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="AddDepartmentMstFB" tabindex="1" property="hodCode" styleClass="registrationCmb">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.EMPLOYEE_AS_CONSULTANT%>">
  			 		<html:options collection="<%=RegistrationConfig.EMPLOYEE_AS_CONSULTANT%>" property="value" labelProperty="label" />
  			 		</logic:present>
	   		 	</html:select>
	          </div>
       </td>                                                 
  </tr>

  <tr>
	   <td width="20%"  class="tdfonthead">
		           <div align="right">	           
		              <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		              <b><font color="#FF0000">*</font>&nbsp;<bean:message key="locationCode"/></b>
		              </font>
		            </div>
	   </td>      
	   <td width="20%"  class="tdfont">
	         <div align="left">
	                <html:select name="AddDepartmentMstFB" tabindex="1" property="locationCode" styleClass="registrationCmb">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>">
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>" property="value" labelProperty="label" />
  			 		</logic:present>
	   		 	</html:select>
	          </div>
       </td>                                                 
  </tr>


 
 
  	   
</table>
</his:ContentTag>  
<%} %>
<html:hidden name="AddDepartmentMstFB" property="transactionMode" />
<html:hidden name="AddDepartmentMstFB" property="entryDate" value="<%=sysDate%>" />

<html:hidden name="AddDepartmentMstFB" property="hl7Code" />
<html:hidden name="AddDepartmentMstFB" property="remarks" />

<his:ButtonToolBarTag>   
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick ="validateDeptMaster()" onkeypress="if(event.keyCode==13) validateDeptMaster()">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">          	
</his:ButtonToolBarTag>
 <his:status/>
</his:TransactionContainer>

</html:form>
</body>
</html>