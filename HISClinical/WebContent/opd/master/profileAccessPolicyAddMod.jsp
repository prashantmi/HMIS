<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page info="Used for addition , modification and view of disaster type master" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="hisglobal.hisconfig.*" %>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>

<%@page import="opd.OpdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>    
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/opd/js/profileAccessPolicy.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>

<script>
function finalSubmit(mode){
	MoveToSelected();
	var isValidate=validateSave()
	//alert("validateSave() "+ isValidate)
	if(isValidate){
		document.getElementsByName("hmode")[0].value=mode
		document.forms[0].submit();
	}
	else{
		return false;
	}	
}

function finalSubmitModify(mode){
	if(validateSaveModify()){
		document.getElementsByName("hmode")[0].value=mode
		document.forms[0].submit();
	}
	else{
		return false;
	}	
}

function validateSaveModify(){
	var valid=false;
	if(//validateDate() &&
		comboValidation(document.getElementsByName("accessType")[0],"Access Type")
		&& comboValidation(document.getElementsByName("profileGroupId")[0],"Profile Group Name")
		&& validateIsActive()){
		valid=true;
	}
	else{
		valid=false;
	}
	
	return valid;

}

function validateSave(){
	var valid=false;
	if(comboValidation(document.getElementsByName("profileType")[0],"Profile Type")
	&& comboValidation(document.getElementsByName("policyType")[0],"Policy Type")
	&& validateUnit()
	&& comboValidation(document.getElementsByName("accessType")[0],"Access Type")
	&& comboValidation(document.getElementsByName("profileGroupId")[0],"Profile Group Name")
	//&& validateDate()
	&& validateIsActive()){
		valid=true;
	}
	else{
		valid=false;
	}
	
	return valid;

}

function validateDate()
{
	var valid=true;
	effectiveFrom = document.getElementsByName("effectiveFrom")[0];
	effectiveTo = document.getElementsByName("effectiveTo")[0];
    sysDate = document.getElementsByName("sysDate")[0];
	if(!isEmpty(effectiveFrom,"Effective From")){
		return false;
	}
	
	if(!compareDate(effectiveFrom,sysDate,2)){
		alert("Effective From Date cannot be greater than Current Date")
		return false;
	}	
	else{
		if(effectiveTo.value!=""){
			if(!compareDate(effectiveFrom,effectiveTo,2)){
				alert("Effective From Date cannot be greater than Effective To Date")
				return false;
			}
			else
				valid=true;
		}		
		else
			valid=true
	}
	
	return valid;
}

function submitPage(mode){
	document.getElementsByName("hmode")[0].value=mode
	document.forms[0].submit();
}

function clearForm(){
	if(document.getElementsByName("selectedDeptUnitCode")[0]){
		//moveLeftAll("1");
		document.getElementsByName("deptUnitCode")[0].length="0"
		document.getElementsByName("selectedDeptUnitCode")[0].length="0"
	}	
	if(document.getElementsByName("profileType")[0])
		document.getElementsByName("profileType")[0].value="-1"
	if(document.getElementsByName("policyType")[0])
		document.getElementsByName("policyType")[0].value="-1"
		document.getElementsByName("accessType")[0].value="-1"
	document.getElementsByName("userLevel")[0].value="-1"
	document.getElementsByName("profileGroupId")[0].value="-1"
	document.getElementsByName("effectiveFrom")[0].value=""
	document.getElementsByName("effectiveTo")[0].value=""
	if(document.getElementsByName("isActive")[0]){
		document.getElementsByName("isActive")[0].value="-1"
	}
}

function validateIsActive(){
	var valid=true
	if(document.getElementsByName("isActive")[0]){
		if(comboValidation(document.getElementsByName("isActive")[0],"Is Active")){
			valid= true;
		}
		else{
			valid= false;
		}	
	}
	return valid;
}

function validateUnit(){
	var unit=document.getElementsByName("selectedDeptUnitCode")[0]
	if(unit.length==0){
		alert("Please select at least one Department Unit");
		return false;
	}
	return true;
}

function getDepartmentUnit(obj){
	if(isSelected(document.getElementsByName("profileType")[0],"Profile Type")){
		if(obj.value!="-1"){
			submitPage("GETDEPTUNIT");
		}
	}
	else{
		obj.value="-1";
		return false;
	}
}
</script>

<his:TransactionContainer>
 
 	<bean:define name="ProfileAccessPolicyFB" property="sysDate" id="sysDate" type="java.lang.String" />
			<%
				if(sysDate==null||sysDate.equalsIgnoreCase(""))
				{
					sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
				}
			%>
 <body>

  <html:form action="/master/profileAccessPolicy" > 
    <html:hidden name="ProfileAccessPolicyFB" property="hmode"/>
    <html:hidden name="ProfileAccessPolicyFB" property="serialNo"/>
    <html:hidden name="ProfileAccessPolicyFB" property="deptUnitName"/>
    <html:hidden name="ProfileAccessPolicyFB" property="chk"/>
    <html:hidden name="ProfileAccessPolicyFB" property="profileTypeName"/>
    <html:hidden name="ProfileAccessPolicyFB" property="policyTypeName"/>
    <html:hidden name="ProfileAccessPolicyFB" property="sysDate" value="<%=sysDate%>"/>
   <%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="ProfileAccessPolicyFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
  	<his:TitleTag name="Profile Access Policy">
	</his:TitleTag>
  	   
	 <his:ContentTag>
	 	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		    <logic:equal name="ProfileAccessPolicyFB" property="hmode" value="ADD">
		   	<tr> 
			      <td width="50%" class="tdfonthead">
				     <div align="right">
						<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									*
						</font> 
					    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="profileType"/>&nbsp;
						</font>
					  </div>
				    </td>
				    <td width="50%" class="tdfont">
						<div align="left">
						&nbsp;	<html:select name="ProfileAccessPolicyFB" property="profileType" tabindex="1" styleClass="regcbo" disabled="<%=this.readOnly %>">
							<html:option value="-1">Select Value</html:option>
							<html:options collection="<%=OpdConfig.PROFILE_TYPE_LIST %>" property="value" labelProperty="label"  />
							</html:select>
						</div>
					</td>
			   </tr>
			   
			   <tr>
			      <td width="50%" class="tdfonthead">
				     <div align="right">
						<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									*
						</font> 
					    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="policyType"/>&nbsp;
						</font>
					  </div>
				    </td>
				    <td width="50%" class="tdfont">
						<div align="left">
						&nbsp;	<html:select name="ProfileAccessPolicyFB" property="policyType" tabindex="1" styleClass="regcbo" disabled="<%=this.readOnly %>" onchange="getDepartmentUnit(this)">
							<html:option value="-1">Select Value</html:option>
							<html:option value="<%=OpdConfig.PROFILE_ACCESS_POLICY_POLICY_TYPE_NORMAL%>">Normal</html:option>
							<html:option value="<%=OpdConfig.PROFILE_ACCESS_POLICY_POLICY_TYPE_RESTRICTED%>">Restricted</html:option>
							</html:select>
						</div>
					</td>
			   </tr>
		  </logic:equal>
		  <logic:notEqual name="ProfileAccessPolicyFB" property="hmode" value="ADD">
		       <tr>
			      <td width="50%" class="tdfonthead">
				     <div align="right">
						<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									*
						</font> 
					    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="profileType"/>&nbsp;
						</font>
					  </div>
				    </td>
				    <td width="50%" class="tdfont">
						<div align="left">
						&nbsp;	<bean:write name="ProfileAccessPolicyFB" property="profileTypeName"/>
						</div>
					</td>
			   </tr>
			   
			   <tr>
			      <td width="50%" class="tdfonthead">
				     <div align="right">
						<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									*
						</font> 
					    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="policyType"/>&nbsp;
						</font>
					  </div>
				    </td>
				    <td width="50%" class="tdfont">
						<div align="left">
						&nbsp;	<bean:write name="ProfileAccessPolicyFB" property="policyTypeName"/>
						</div>
					</td>
			   </tr>
			    <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="deptUnitName"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp;	<bean:write name="ProfileAccessPolicyFB" property="deptUnitName"/>
					</div>
				</td>
			   
		      </tr>
		  </logic:notEqual>
		   </table>
		    <logic:equal name="ProfileAccessPolicyFB" property="hmode" value="ADD">
	 	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		   <tr>
		  		<td width="40%" class="tdfonthead" colspan="3">
					<div align="center">
						<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									*
						</font> 
					    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="dept(unit)"/>
						</font>
					</div>
				</td>
			</tr>	
		   <tr>
		  		<td width="40%" class="tdfonthead" >
					<div align="right">
					&nbsp;	<html:select name="ProfileAccessPolicyFB" property="deptUnitCode" multiple="true"  size="5" tabindex="1" >
						<logic:notEqual name="ProfileAccessPolicyFB" property="policyType" value="-1">
						<html:options collection="<%=OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST %>" property="value"  labelProperty="label"  />
						</logic:notEqual>
						</html:select>
					</div>
				</td>
		   		<td width="20%"  class="tdfont">
					<div align="center">
						<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
						<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");' /> 	
						<br><br>
						<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
						<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
					</div>
				</td>
		   		<td width="40%" class="tdfonthead" >
					<div align="left">
					&nbsp;	<html:select name="ProfileAccessPolicyFB" property="selectedDeptUnitCode" multiple="true"  size="5" tabindex="1" >
						</html:select>
					</div>
				</td>
			 <tr>
		</table>
		</logic:equal>  
		<table width="100%" border="0" cellspacing="1" cellpadding="0">	   
			   <tr>
			      <td width="50%" class="tdfonthead">
				     <div align="right">
			     		<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						</font> 
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="accessType"/>&nbsp;
						</font>
					  </div>
				    </td>
				    <td width="50%" class="tdfont">
						<div align="left">
						&nbsp;	<html:select name="ProfileAccessPolicyFB" property="accessType" tabindex="1" styleClass="regcbo" disabled="<%=this.readOnly %>">
							<html:option value="-1">Select Value</html:option>
							<html:option value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL%>">To All</html:option>
							<html:option value="<%=OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER%>">Others</html:option>
							</html:select>
						</div>
					</td>
			   </tr>
			   <tr>
			      <td width="50%" class="tdfonthead">
				     <div align="right">
				     	 <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									*
						  </font> 
				    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="profileGroupName"/>&nbsp;
						</font>
					  </div>
				    </td>
				    <td width="50%" class="tdfont">
						<div align="left">
						&nbsp;	<html:select name="ProfileAccessPolicyFB" property="profileGroupId" tabindex="1" styleClass="regcbo" disabled="<%=this.readOnly %>">
							<html:option value="-1">Select Value</html:option>
							<html:options collection="<%=OpdConfig.PROFILE_ACCESS_POLICY_GROUP_LIST %>" property="value"  labelProperty="label"  />
							</html:select>
						</div>
					</td>
			   
		      	</tr>
			    <tr>
			      <td width="50%" class="tdfonthead">
				     <div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<bean:message key="userlevel"/>&nbsp;
						</font>
					  </div>
				    </td>
				    <td width="50%" class="tdfont">
						<div align="left">
						&nbsp;	<html:select name="ProfileAccessPolicyFB" property="userLevel" tabindex="1" styleClass="regcbo" disabled="<%=this.readOnly %>">
							<html:option value="-1">Select Value</html:option>
							<html:option value="0">No Level</html:option>
							<html:option value="1">Level 1</html:option>
							<html:option value="2">Level 2</html:option>
							<html:option value="3">Level 3</html:option>
							<html:option value="4">Level 4</html:option>
							<html:option value="5">Level 5</html:option>
							<html:option value="6">Level 6</html:option>
							</html:select>
						</div>
					</td>
			   </tr>
			   
			
		     <%--  
	     	 <tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								
	  									<bean:message key="effectiveFrom"/>
	  								
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp; <his:date name='effectiveFrom'  dateFormate="%d-%b-%Y" value="<%=sysDate%>" />
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
	  									<bean:message key="effectiveTo"/>
	  								
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp; <his:date name='effectiveTo' dateFormate="%d-%b-%Y" />
							</div>
						</td>
					</tr>
		       --%>
		  	  <logic:notEqual name="ProfileAccessPolicyFB" property="hmode" value="ADD">
			        <tr>
			         <td width="50%" class="tdfonthead">
			          <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="isActive"/>&nbsp;
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     &nbsp; <html:select name="ProfileAccessPolicyFB" property="isActive" disabled="<%=this.readOnly %>" styleClass="regcbo">
						    <html:option value="-1">Select Value</html:option>
							<html:option value="<%=Config.IS_VALID_ACTIVE%>">Active</html:option>
							<html:option value="<%=Config.IS_VALID_INACTIVE%>">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
			   </logic:notEqual>
	    	</table>
      </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			   
			    <logic:equal name="ProfileAccessPolicyFB" property="hmode" value="ADD">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) return finalSubmit('SAVE')" onclick="return finalSubmit('SAVE')">
				</logic:equal>
				
				<logic:notEqual name="ProfileAccessPolicyFB" property="hmode" value="VIEW">
				<logic:notEqual name="ProfileAccessPolicyFB" property="hmode" value="ADD">
				   <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmitModify('MODIFYSAVE')" onclick="finalSubmitModify('MODIFYSAVE')">
				</logic:notEqual> 
				</logic:notEqual>
				
				<logic:notEqual name="ProfileAccessPolicyFB" property="hmode" value="VIEW">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual> 
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		 
		 <center><b><his:status/></b></center>
        
   </html:form>
  </body>
  </his:TransactionContainer>
</html>
		     
		   
		  