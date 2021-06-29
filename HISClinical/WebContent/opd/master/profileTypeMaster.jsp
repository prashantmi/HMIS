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
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="hisglobal/js/utilityFunction.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script><!--

function maxlength(field, size) {
    if(field.value.length >= size) {
         document.getElementsByName("profileDesc")[0].
eturnValue=false; 
          alert("more than " +size + " chars");
          return false;
       }

}

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

function validateSave(){
	var valid=false;
	if(comboValidation(document.getElementsByName("patientCategoryCode")[0],"Patient Category Code")
	&& comboValidation(document.getElementsByName("profileType")[0],"Profile Type")
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
	document.getElementsByName("profileName")[0].value="";
	document.getElementsByName("defaultName")[0].value="";
	document.getElementsByName("isUnique")[1].checked = true;
	document.getElementsByName("generationMode")[0].value = "-1";
	document.getElementsByName("profileUsablity")[0].value="-1";
	document.getElementsByName("isCdBurn")[1].checked = true;
	document.getElementsByName("isBilling")[1].checked = true;
	document.getElementsByName("isDesclaimerRequired")[1].checked = true;
	document.getElementsByName("profileDesc")[0].value="";
	document.getElementsByName("isActive")[0].value="1";
	
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

function keyhandler(e,param)
{
	var length=param.value.length;

	if(length==0)
	{
		if(e.which >=65 && e.which <= 90 || e.which >=97 && e.which <= 122 || e.which == 0 || e.which == 13 || e.which == 8)
			{
				return true;
			}
		else
			{
				alert("Enter alphabets only");
				return false;
			}
	}
	else if(length<=50)
	{  
  		if(e.which==0 || e.which==32 || e.which >=65 && e.which <= 90 || e.which >=97 && e.which <= 122 || e.which==13 || e.which==8)
    	return true;
  		else
  		{
	  		//alert ("Special characters and digits are not allowed.");
	  		return false;  
  		}
  	}
  	
  	
}

  function validateAlphaNumericWithSpaceOnly(e,param)
{
	var key;
	var keychar;
	var length=param.value.length;
	if(length<=200){
	
	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	//alert(key);
	// control keys
	if ((key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==95))
		{
	   return true;
		}

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ _/+-").indexOf(keychar) > -1))
	   			return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   			return true;
	   
	   
	   
	else
	   return false;
	   }
	   else
	   { 
	     return false;
	   	alert("Description should be maximum of 200 chanracters");
	   }
}



function validate()
		{
			//if(document.forms[0].profileName.value=="") 
			if(document.getElementsByName("profileName")[0].value=="")
				{
			 	alert("Enter the profile Type");
			 	document.forms[0].profileName.focus();
			 	return false;
			 	
				}
			else if(document.getElementsByName("generationMode")[0].value=="-1")
			{
				alert("Please Select the Generation Mode");
				document.forms[0].generationMode.focus();
					return false;
				
			} 
			else if(document.getElementsByName("generationMode")[0].value=="2")
			{
				var genrationMode =document.getElementsByName("generationMode")[0].value;
				var profileUsablity =document.getElementsByName("profileUsablity")[0].value;
				if((genrationMode==2) && (profileUsablity==1))
				{
					return true;
				}
				else
				{					
					alert("For 'Automatic At Current' Visit Usablity Should be 'OPD' ");	
					document.forms[0].generationMode.focus();
					return false;	
				}
				
			}
			else if(document.getElementsByName("profileUsablity")[0].value=="-1")
				{
					alert("Please Select the Usability");
					document.forms[0].profileUsablity.focus();
						return false;
					
				}
				return true;			
		}
		
function SetUsability(obj)
		{
			var value = obj.value;
			var Usablity = document.getElementsByName("profileUsablity")[0];
			Usablity.value = -1;
			if(value==2)
			{				
				Usablity.value = 1;
			}
		}

</script>

<his:TransactionContainer>
 <body>

  <html:form action="/master/profileTypeMaster" > 
    <html:hidden name="ProfileTypeMasterFB" property="hmode"/>
    <html:hidden name="ProfileTypeMasterFB" property="chk"/>
    
    <!-- Add Page -->
   <%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="ProfileTypeMasterFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
   <his:ContentTag>
        <logic:equal name="ProfileTypeMasterFB" property="hmode" value="ADD">
	   		
  	   <his:TitleTag name="Profile Type Master>> Add">
			</his:TitleTag>
       
	
		<table width="100%" border="0" cellspacing="2" cellpadding="0">
		
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="profileType"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp;<html:text property="profileName" maxlength="50" size="25"  onkeypress="return keyhandler(event,this);"></html:text>	
					</div>
				</td>
		      </tr>
		   
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="default"/><bean:message key="name"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp;<html:text property="defaultName" maxlength="50" size="25"  onkeypress="return keyhandler(event,this);"></html:text>	
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
							<b><bean:message key="isUnique"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					&nbsp;<html:radio property="isUnique" value="<%=OpdConfig.YES %>" tabindex="1"/> Yes	
					&nbsp;<html:radio property="isUnique" value="<%=OpdConfig.NO %>" tabindex="1"/> No	
				</td>
		      </tr>
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="generationMode"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp;<html:select property="generationMode" tabindex="1" styleClass="regcbo" onchange="SetUsability(this)" disabled="<%=this.readOnly %>">
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED %>">Customized</html:option>
						<html:option value="<%=OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT%>">Automatic At Current Visit</html:option>
						<html:option value="<%=OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE%>">Automatic At Whole Episode</html:option>
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
							<b><bean:message key="usablity"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp;<html:select property="profileUsablity" tabindex="1"  styleClass="regcbo" disabled="<%=this.readOnly %>">
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=OpdConfig.PROFILE_TYPE_USABLITY_OPD %>">OPD</html:option>
						<html:option value="<%=OpdConfig.PROFILE_TYPE_USABLITY_IPD %>">IPD</html:option>
						<html:option value="<%=OpdConfig.PROFILE_TYPE_USABLITY_OPD_AND_IPD %>">OPD and IPD</html:option>
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
							<b><bean:message key="isCdBurn"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					&nbsp;<html:radio property="isCdBurn" value="<%=OpdConfig.YES %>" tabindex="1"/> Yes	
					&nbsp;<html:radio property="isCdBurn" value="<%=OpdConfig.NO %>" tabindex="1"/> No	
					
				</td>
		      </tr>
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
			     	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="isBilling"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont" colspan="3">
					&nbsp;<html:radio property="isBilling" value="<%=OpdConfig.YES %>" tabindex="1"/> Yes	
					&nbsp;<html:radio property="isBilling" value="<%=OpdConfig.NO %>" tabindex="1"/> No	
				</td>
		      </tr>
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
			     	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="disclaimerRequired"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont" colspan="3">
					&nbsp;<html:radio property="isDesclaimerRequired" value="<%=OpdConfig.YES %>" tabindex="1"/> Yes	
					&nbsp;<html:radio property="isDesclaimerRequired" value="<%=OpdConfig.NO %>" tabindex="1"/> No	
				</td>
		      </tr>
		    <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="profileTypeDesc"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont" colspan="3">
			    &nbsp;<html:textarea property="profileDesc" rows="3" cols="40" onkeypress="return validateAlphaNumericWithSpaceOnly(event,this);" />	
				</td>
		      </tr>
		    
		 
		  
	    </table>
    
     
		 </logic:equal>
		
		<!--  Modify Page -->
		
		  <logic:equal name="ProfileTypeMasterFB" property="hmode" value="MODIFY">
	   		<his:TitleTag name="Profile Type Master>> Modify">
			</his:TitleTag>
  	   
       
	
		<table width="100%" border="0" cellspacing="2" cellpadding="0">
		
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="profileType"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp;<html:text name="ProfileTypeMasterFB" property="profileName" maxlength="50" size="25" disabled="<%=this.readOnly %>" onkeypress="return keyhandler(event,this);"></html:text>	
					</div>
				</td>
		      </tr>
		   
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="default"/><bean:message key="name"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp;<html:text name="ProfileTypeMasterFB" property="defaultName" maxlength="50" size="25" disabled="<%=this.readOnly %>" onkeypress="return keyhandler(event,this);"></html:text>	
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
							<b><bean:message key="isUnique"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isUnique" value="<%=OpdConfig.YES %>" disabled="<%=this.readOnly %>" /> Yes	
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isUnique" value="<%=OpdConfig.NO %>" disabled="<%=this.readOnly %>"/> No	
				</td>
		      </tr>
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="generationMode"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp;<html:select name="ProfileTypeMasterFB" property="generationMode" tabindex="1" onchange="SetUsability(this)" styleClass="regcbo" disabled="<%=this.readOnly %>">
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED %>">Customized</html:option>
						<html:option value="<%=OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT%>">Automatic At Current Visit</html:option>
						<html:option value="<%=OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE%>">Automatic At Whole Episode</html:option>
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
							<b><bean:message key="usablity"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp;<html:select name="ProfileTypeMasterFB" property="profileUsablity" tabindex="1" styleClass="regcbo" disabled="<%=this.readOnly %>">
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=OpdConfig.PROFILE_TYPE_USABLITY_OPD %>">OPD</html:option>
						<html:option value="<%=OpdConfig.PROFILE_TYPE_USABLITY_IPD %>">IPD</html:option>
						<html:option value="<%=OpdConfig.PROFILE_TYPE_USABLITY_OPD_AND_IPD %>">OPD and IPD</html:option>
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
							<b><bean:message key="isCdBurn"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isCdBurn" value="<%=OpdConfig.YES %>" disabled="<%=this.readOnly %>" /> Yes	
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isCdBurn" value="<%=OpdConfig.NO %>" disabled="<%=this.readOnly %>"/> No	
					
				</td>
		      </tr>
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
			     	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="isBilling"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont" colspan="3">
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isBilling" value="<%=OpdConfig.YES %>" disabled="<%=this.readOnly %>"/> Yes	
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isBilling" value="<%=OpdConfig.NO %>" disabled="<%=this.readOnly %>"/> No	
				</td>
		      </tr>
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
			     	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="disclaimerRequired"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont" colspan="3">
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isDesclaimerRequired" value="<%=OpdConfig.YES %>" disabled="<%=this.readOnly %>" /> Yes	
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isDesclaimerRequired" value="<%=OpdConfig.NO %>" disabled="<%=this.readOnly %>" /> No	
				</td>
		      </tr>
		    <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="profileTypeDesc"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont" colspan="3">
			    &nbsp;<html:textarea name="ProfileTypeMasterFB" property="profileDesc" rows="3" cols="40" disabled="<%=this.readOnly %>" onkeypress="return validateAlphaNumericWithSpaceOnly(event,this);" />	
				</td>
		      </tr>
		 	
		 	 <logic:notEqual name="ProfileTypeMasterFB" property="hmode" value="ADD">
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
				     &nbsp; <html:select name="ProfileTypeMasterFB" property="isActive" disabled="<%=this.readOnly %>" styleClass="regcbo">
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
      
      <logic:equal name="ProfileTypeMasterFB" property="hmode" value="VIEW">
	   		<his:TitleTag name="Profile Type Master">
			</his:TitleTag>
  	   
       
	
		<table width="100%" border="0" cellspacing="2" cellpadding="0">
		
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="profileType"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					<bean:write property="profileName" name="ProfileTypeMasterFB"  />
					</div>
				</td>
		      </tr>
		   
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="default"/><bean:message key="name"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					<bean:write property="defaultName" name="ProfileTypeMasterFB"  />
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
							<b><bean:message key="isUnique"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isUnique" value="<%=OpdConfig.YES %>" disabled="<%=this.readOnly %>" /> Yes	
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isUnique" value="<%=OpdConfig.NO %>" disabled="<%=this.readOnly %>"/> No	
				</td>
		      </tr>
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="generationMode"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					<bean:write property="generationMode" name="ProfileTypeMasterFB"  />
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
							<b><bean:message key="usablity"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					<bean:write property="profileUsablity" name="ProfileTypeMasterFB"/>
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
							<b><bean:message key="isCdBurn"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isCdBurn" value="<%=OpdConfig.YES %>" disabled="<%=this.readOnly %>" /> Yes	
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isCdBurn" value="<%=OpdConfig.NO %>" disabled="<%=this.readOnly %>"/> No	
					
				</td>
		      </tr>
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
			     	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="isBilling"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont" colspan="3">
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isBilling" value="<%=OpdConfig.YES %>" disabled="<%=this.readOnly %>"/> Yes	
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isBilling" value="<%=OpdConfig.NO %>" disabled="<%=this.readOnly %>"/> No	
				</td>
		      </tr>
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
			     	<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="disclaimerRequired"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont" colspan="3">
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isDesclaimerRequired" value="<%=OpdConfig.YES %>" disabled="<%=this.readOnly %>" /> Yes	
					&nbsp;<html:radio name="ProfileTypeMasterFB" property="isDesclaimerRequired" value="<%=OpdConfig.NO %>" disabled="<%=this.readOnly %>" /> No	
				</td>
		      </tr>
		    <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="profileTypeDesc"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont" colspan="3">
			    <bean:write property="profileDesc" name="ProfileTypeMasterFB"  />
				</td>
		      </tr>
		 	
		 	 <logic:notEqual name="ProfileTypeMasterFB" property="hmode" value="ADD">
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
				     &nbsp; <html:select name="ProfileTypeMasterFB" property="isActive" disabled="<%=this.readOnly %>" styleClass="regcbo">
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
			
			   
			    <logic:equal name="ProfileTypeMasterFB" property="hmode" value="ADD">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) return finalSubmit('SAVE')" onclick="return finalSubmit('SAVE')",>
				</logic:equal>
				
				<logic:notEqual name="ProfileTypeMasterFB" property="hmode" value="VIEW">
				<logic:notEqual name="ProfileTypeMasterFB" property="hmode" value="ADD">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmitModify('MODIFYSAVE')" onclick="finalSubmitModify('MODIFYSAVE')">
				</logic:notEqual> 
				</logic:notEqual>
				
				<logic:notEqual name="ProfileTypeMasterFB" property="hmode" value="VIEW">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual> 
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
		</his:ButtonToolBarTag>
		
	<center><b><his:status/></b></center>
	       
   </html:form>
  </body>
  </his:TransactionContainer>
</html>
		     
		   
		  