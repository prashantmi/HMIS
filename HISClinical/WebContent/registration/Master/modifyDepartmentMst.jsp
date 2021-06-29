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

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />


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

function validateDate()
{
	var valid=true;
	var checkeffectiveDateFrom=document.getElementById("effectiveFrom1");
	
	if(checkeffectiveDateFrom !=null && checkeffectiveDateFrom != 'null')
	   {
	   
	        if(!compareDateCall(entryDate,effectiveFrom,2,"CurrentDate","Effective From Date"))
	        {
	        valid= false;
	        return valid
	        }
       }
    if(!document.getElementsByName('effectiveTo')[0].value=="")
	   {
	   if(!compareDateCall(effectiveFrom,effectiveTo,2,"Effective From Date","Effective To Date"))
	   {
	   valid= false;
	   return valid
	   }
	   }
	return valid;
}

function validateCombo()
{ var valid=true;
	
	
	if(document.getElementsByName('locationCode')[0].value=="-1")
		{
		alert("Please Select the Location for the Department");
		valid= false;
		return valid
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
      
       //&& validateDate()
       && validateRemarks()	 
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
if(mode=="CANCEL")
   document.getElementsByName('isActive')[0].value="1";
   
   document.getElementsByName("transactionMode")[0].value=mode;  
   document.forms[0].submit();
}

function clearForm(){
	document.getElementsByName("department")[0].value=""
	document.getElementsByName("deptShortName")[0].value=""
	document.getElementsByName("departmentType")[0].value="-1"
	document.getElementsByName("hodCode")[0].value="-1"
	document.getElementsByName("locationCode")[0].value="-1"
	document.getElementsByName("remarks")[0].value=""
	document.getElementsByName("isActive")[0].value="-1"
	
}

</script>

<body>
<html:form action="/master/modifyDepartmentMaster">
<his:TransactionContainer>

		<%@ page
			import="java.util.*,registration.*,hisglobal.presentation.*"%>
		<%boolean varReadOnly = false;
		  String strReadOnly="false";
		%>

		<logic:equal name="ModifyDepartmentMstFB" property="isValid" value="2">
			<%varReadOnly = true;
			strReadOnly="true";
			%>
		</logic:equal>
		
		<%String sysDate = WebUTIL
							.getCustomisedSysDate(
									(Date) session
											.getAttribute(RegistrationConfig.SYSADATEOBJECT),
									"dd-MMM-yyyy");

					%>

		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>"
			value="modify">
			<his:TitleTag name="Modify Department">
			</his:TitleTag>
		</logic:equal>
		<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
			<his:TitleTag name="View Department">
			</his:TitleTag>
		</logic:equal>

<%Date sysDateObject=(Date)session.getAttribute(RegistrationConfig.SYSADATEOBJECT); %>

		<his:statusList>
		<his:ContentTag>
			
			<div>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
					<td width="30%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="globalDepartment" /></b>
					</font>
					</div>
					</td>
					<td width="30%" class="tdfont">
						<bean:write name="ModifyDepartmentMstFB" property="globalDepartment"/>
					</td>
				</tr>
				<tr>
					<td width="30%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> 
						<b> <font color="#FF0000">*</font>&nbsp;<bean:message key="department" /></b>
					</font>
					</div>
					</td>
					<td width="30%" class="tdfont">
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
							<div align="left">
								<html:text name="ModifyDepartmentMstFB"  tabindex="1" maxlength="50" property="department" onkeypress="return validateAlphabetsOnly(event,this)"/>
							</div>
						</logic:equal> 
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
							<html:text name="ModifyDepartmentMstFB" maxlength="50" tabindex="1" property="department" readonly="true" />
						</div>
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td width="30%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<b><font color="#FF0000">*</font>&nbsp;<bean:message key="deptShortName" /> </b>
					</font>
					</div>
					</td>
					<td width="30%" class="tdfont">
					<div align="left">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<html:text name="ModifyDepartmentMstFB" maxlength="3" size="5" tabindex="1" property="deptShortName"  onkeypress="return validateAlphabetsOnly(event,this)" />
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<html:text name="ModifyDepartmentMstFB" maxlength="3" size="5" tabindex="1" property="deptShortName" readonly="true" />
					</logic:equal>
					</div>
					</td>
				</tr>
  <tr>
					<td width="30%" class="tdfonthead">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><font color="#FF0000">*</font>&nbsp;<bean:message key="departmentType" /></b> 
					</font>
					</div>
					</td>
					 <td width="20%"  class="tdfont">
	        		 <div align="left">
	        		 <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
	                <html:select name="ModifyDepartmentMstFB" tabindex="1" property="departmentType" styleClass="registrationCmb">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT_TYPE %>">
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT_TYPE %>" property="value" labelProperty="label"/>
  			 		</logic:present>
  			 		</html:select>
	   		 		</logic:equal>
  		 			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
  		 			  <html:select name="ModifyDepartmentMstFB" tabindex="1" property="departmentType" styleClass="registrationCmb" disabled="true">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT_TYPE %>">
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT_TYPE %>" property="value" labelProperty="label"/>
  			 		</logic:present>
  			 		</html:select>
  		 			</logic:equal>
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
	          <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	                
	                <html:select name="ModifyDepartmentMstFB" tabindex="1" property="hodCode" styleClass="registrationCmb">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.EMPLOYEE_AS_CONSULTANT%>">
  			 		<html:options collection="<%=RegistrationConfig.EMPLOYEE_AS_CONSULTANT%>" property="value" labelProperty="label" />
  			 		</logic:present>
	   		 	</html:select>
	   		 	
	   		 	
	   		 	</logic:equal>
  		 	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">   
  		 	 <html:select name="ModifyDepartmentMstFB" tabindex="1" property="hodCode" styleClass="registrationCmb" disabled="true">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.EMPLOYEE_AS_CONSULTANT%>">
  			 		<html:options collection="<%=RegistrationConfig.EMPLOYEE_AS_CONSULTANT%>" property="value" labelProperty="label" />
  			 		</logic:present>
	   		 	</html:select>
 	    	    
  		 	</logic:equal>	   		 	
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
	         <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	          
	                <html:select name="ModifyDepartmentMstFB" tabindex="1" property="locationCode" styleClass="registrationCmb">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>">
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>" property="value" labelProperty="label" />
  			 		</logic:present>
	   		 	</html:select>
	   		 	
	   		 	</logic:equal>
  		 	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">   
 	    	    <html:select name="ModifyDepartmentMstFB" tabindex="1" property="locationCode" styleClass="registrationCmb" disabled="true">
			 		<html:option value="-1">Select Value</html:option>
			 		<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>">
  			 		<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_LOCATION%>" property="value" labelProperty="label" />
  			 		</logic:present>
	   		 	</html:select>	         
  		 	</logic:equal>	   		 	
	          </div>
       </td>                                                 
  </tr>
  
  
				
  
   <!--  <tr>
      <td width="20%" class="tdfonthead">
	  <div align="right">           
	  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  <b>	 
	  <bean:message key="hl7Code"/></b>
	  </font>
	  </div>
      </td>      
      <td width="20%" class="tdfont">
	         <div align="left">	 
	            <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify"> 
	           		              
	         	  		<html:text name="ModifyDepartmentMstFB" maxlength="10" size="12" tabindex="1" property="hl7Code" onkeypress="return validateNumeric(event)"/>	         
	         		 
	         	</logic:equal>       
	         	<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">         
	         	  <html:text name="ModifyDepartmentMstFB" maxlength="10" size="12" tabindex="1" property="hl7Code" readonly="true"/>	         
	         	</logic:equal>	         	
	         </div>
      </td>                                           
  </tr>	    -->
     
   <tr>

					<td width="30%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<b> <bean:message key="remarks" /> </b> 
					</font>
					</div>
					</td>
					<td width="30%" class="tdfont">
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<div align="left">
						<html:textarea name="ModifyDepartmentMstFB" property="remarks" tabindex="1" onkeypress="return CheckMaxLength(event,this,50,1)"/>
						</div>
					</logic:equal> 
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<div align="left">
						<html:textarea name="ModifyDepartmentMstFB" property="remarks" tabindex="1" readonly="true" /></div>
					</logic:equal>
					</td>
					
	</tr>
	<tr>
      <td width="50%" class="tdfonthead">
		  <div align="right">	           
			  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <font color="#FF0000">*</font>
			  <b><bean:message key="isActive"/>
			  </b>
			  </font>
		  </div>
      </td>  
      <td width="50%" class="tdfont">
         <div align="left">	 
          <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
          <html:select name="ModifyDepartmentMstFB" tabindex="1" property="isActive" styleClass="regCbo">
		 	<html:option value="-1">Select Value</html:option>
		 	<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option>
			<html:option value="<%=Config.IS_VALID_INACTIVE %>">In Active</html:option>
   		 	</html:select>
   		 	</logic:equal>	
          <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
          <html:select name="ModifyDepartmentMstFB" tabindex="1" property="isActive" styleClass="regCbo" disabled="true">
		 	<html:option value="-1">Select Value</html:option>
		 	<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option>
			<html:option value="<%=Config.IS_VALID_INACTIVE %>">In Active</html:option>
   		 	</html:select>
   		 	</logic:equal>	
         </div>
      </td>  
  	</tr> 
 
</table>  

</his:ContentTag>   
</his:statusList>
<html:hidden name="ModifyDepartmentMstFB" property="transactionMode" />

		<html:hidden name="ModifyDepartmentMstFB" property="<%=RegistrationConfig.VIEWORMODIFY%>" />
		<%-- <html:hidden name="ModifyDepartmentMstFB" property="isActive" value="<%=Config.IS_VALID_ACTIVE%>"/> --%>
		<html:hidden name="ModifyDepartmentMstFB" property="chk" />
		<html:hidden name="ModifyDepartmentMstFB" property="entryDate" value="<%=sysDate%>"/>

<html:hidden name="ModifyDepartmentMstFB" property="hl7Code" />

		<his:ButtonToolBarTag>
			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')"
					onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
			</logic:equal>
			<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>"
				value="modify">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'
					style=cursor:pointer tabindex="1" onclick="validateDeptMaster()"
					onkeypress="if(event.keyCode==13) validateDeptMaster()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
					style=cursor:pointer tabindex="1" onclick="clearForm()"
					onkeypress="if(event.keyCode==13) clearForm();">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style=cursor:pointer tabindex="1" onclick="submitTile('CANCEL')"
					onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
			</logic:equal>
		</his:ButtonToolBarTag>
		<his:status/>
</his:TransactionContainer>
</html:form>

</body>
</html>