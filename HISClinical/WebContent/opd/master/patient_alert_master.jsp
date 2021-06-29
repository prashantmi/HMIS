<!--@created by
	 
 -->
 <%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
 
<%@page info="Used for addition , modification and view of profile type master" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.hisconfig.*" %>
<%@page import="opd.OpdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/hisglobal/js/commonFunctions.js"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="hisglobal/js/utilityFunction.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script>


function finalSubmit(mode){
	//if(validateSave()){
		if(validate())
		{
		
		document.getElementsByName("hmode")[0].value=mode
		// trimData(document.getElementsByName("profileType")[0].value)
		document.forms[0].submit();
		}
		else
		{
			return false;
		}
	
}

function finalSubmitModify(mode){
	//if(validateSaveModify()){
		if(validate())
		{
		document.getElementsByName("hmode")[0].value=mode
		document.forms[0].submit();
		}
		else
		{
			return false;
		}	
}

function validateSaveModify()
{
		var valid=false;
	if(comboValidation(document.getElementsByName("profileType")[0],"Profile Type")
	&& validateIsActive()){
		valid=true;
	}
	else{
		valid=false;
	}
	
	return valid;
		
}


function submitPage(mode){
	document.getElementsByName("hmode")[0].value=mode
	document.forms[0].submit();
}

function clearForm(){
	document.getElementsByName("alertName")[0].value="";
	document.getElementsByName("diseaseCode")[0].value = "-1";
	
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




function validate()
		{
			//if(document.forms[0].profileName.value=="") 
			if(document.getElementsByName("alertName")[0].value=="")
				{
			 	alert("Enter the Disease Name");
			 	document.forms[0].chronicDisease.focus();
			 	return false;
			 	
				}
				return true;
			
		}


</script>

<his:TransactionContainer>
 <body>

  <html:form action="master/patientAlertMaster" > 
    <html:hidden name="PatientAlertMasterFB" property="hmode"/>
    <html:hidden name="PatientAlertMasterFB" property="chk"/>
    
    <!-- Add Page -->
   <%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="PatientAlertMasterFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
   <his:ContentTag>
        <logic:equal name="PatientAlertMasterFB" property="hmode" value="ADD">

  	  	 <his:TitleTag name="Chronic Disease Master>>Add">
			</his:TitleTag>
       
		<table width="100%" border="0" cellspacing="2" cellpadding="0">
		   	<tr>
		      	<td width="50%" class="tdfonthead">
			     	<div align="right">
						<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						</font> 
				    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="chronicDisease"/>&nbsp;<bean:message key="name"/>&nbsp;</b>
						</font>
				  	</div>
				</td>
			    <td width="50%" class="tdfont" colspan="3">
					<div align="left">
						&nbsp;<html:text property="alertName" maxlength="50" size="20"  onkeypress="return validateAlphabetsWithDotsOnly(event,this);"></html:text>	
					</div>
				</td>
			</tr>
		 	
		 	<tr> 
				<td width="50%" class="tdfonthead">
					<div align="right">
					    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="disease"/>&nbsp;
						</font>
					  </div>
				</td>
				<td width="50%" class="tdfont">
					<div align="left">
						&nbsp;<html:select name="PatientAlertMasterFB" property="diseaseCode" tabindex="1" styleClass="regcbo">
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=OpdConfig.EssentialBO_LIST_HOSDIS_DISEASE %>" property="value" labelProperty="label"  />
								</html:select>
					</div>
				</td>
			</tr>
		</table>
    
     	</logic:equal>
		
		<!--  Modify Page -->
		
		  <logic:equal name="PatientAlertMasterFB" property="hmode" value="MODIFY">
	   		<his:TitleTag name="Chronic Disease Master>> Modify">
			</his:TitleTag>
  	   
       
	
		<table width="100%" border="0" cellspacing="2" cellpadding="0">
		   	<tr>
		      	<td width="50%" class="tdfonthead">
			     	<div align="right">
						<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						</font> 
				    	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="chronicDisease"/><bean:message key="name"/>&nbsp;</b>
						</font>
				  	</div>
				</td>
			    <td width="50%" class="tdfont" colspan="3">
					<div align="left">
						&nbsp;<html:text property="alertName" maxlength="50" size="20"  onkeypress="return keyhandler(event,this);"></html:text>	
					</div>
				</td>
			</tr>
		 	
		 	<tr> 
				<td width="50%" class="tdfonthead">
					<div align="right">
					    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="disease"/>&nbsp;
						</font>
					  </div>
				</td>
				<td width="50%" class="tdfont">
					<div align="left">
						&nbsp;<html:select name="PatientAlertMasterFB" property="diseaseCode" disabled="<%=this.readOnly %>" styleClass="regcbo">
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=OpdConfig.EssentialBO_LIST_HOSDIS_DISEASE %>" property="value" labelProperty="label"  />
								</html:select>
					</div>
				</td>
			</tr>
		 	 <logic:notEqual name="PatientAlertMasterFB" property="hmode" value="ADD">
			        <tr>
			         <td width="50%" class="tdfonthead">
			          <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b>	<bean:message key="isActive"/>&nbsp;</b>
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     &nbsp;<html:select name="PatientAlertMasterFB" property="isActive" disabled="<%=this.readOnly %>" styleClass="regcbo">
							<html:option value="<%=Config.IS_VALID_ACTIVE%>">Active</html:option>
							<html:option value="<%=Config.IS_VALID_INACTIVE%>">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
			   </logic:notEqual>
	    </table>
      </logic:equal>
      
      <!-- View page -->
      
      <logic:equal name="PatientAlertMasterFB" property="hmode" value="VIEW">
	   		<his:TitleTag name="Chronic Disease Master">
			</his:TitleTag>
  	   
       
	
		<table width="100%" border="0" cellspacing="2" cellpadding="0">
		
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="chronicDisease"/><bean:message key="name"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					<bean:write property="alertName" name="PatientAlertMasterFB"  />
					</div>
				</td>
		      </tr>
		      <tr> 
				<td width="50%" class="tdfonthead">
					<div align="right">
					    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="disease"/>&nbsp;
						</font>
					  </div>
				</td>
				<td width="50%" class="tdfont">
					<div align="left">
						<bean:write property="diseaseCode" name="PatientAlertMasterFB"  />
					</div>
				</td>
			</tr>
		 	 <logic:notEqual name="PatientAlertMasterFB" property="hmode" value="ADD">
			        <tr>
			         <td width="50%" class="tdfonthead">
			          <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b>	<bean:message key="isActive"/>&nbsp;</b>
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     &nbsp; <html:select name="PatientAlertMasterFB" property="isActive" disabled="<%=this.readOnly %>" styleClass="regcbo">
							<html:option value="<%=Config.IS_VALID_ACTIVE%>">Active</html:option>
							<html:option value="<%=Config.IS_VALID_INACTIVE%>">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
			   </logic:notEqual>
		 	   
		 
		  
	    </table>
      </logic:equal>
      
      </his:ContentTag>
       <his:ButtonToolBarTag>
			
			   
			    <logic:equal name="PatientAlertMasterFB" property="hmode" value="ADD">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) return finalSubmit('SAVE')" onclick="return finalSubmit('SAVE')",>
				</logic:equal>
				
				<logic:notEqual name="PatientAlertMasterFB" property="hmode" value="VIEW">
				<logic:notEqual name="PatientAlertMasterFB" property="hmode" value="ADD">
				   <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmitModify('MODIFYSAVE')" onclick="finalSubmitModify('MODIFYSAVE')">
				</logic:notEqual> 
				</logic:notEqual>
				
				<logic:notEqual name="PatientAlertMasterFB" property="hmode" value="VIEW">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual> 
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
		</his:ButtonToolBarTag>
		
	<center><b><his:status/></b></center>
	       
   </html:form>
  </body>
  </his:TransactionContainer>
</html>
		     
		   
		  