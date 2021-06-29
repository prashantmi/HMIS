<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page info="Used for addition , modification and view of disaster type master" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="hisglobal.hisconfig.*" %>

<%@page import="opd.OpdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style type="text/css">
@import url(../css/calendar-blue2.css);
</style>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />
<script>
function finalSubmit(mode){
	if(validateSave()){
		document.getElementsByName("hmode")[0].value=mode
		document.forms[0].submit();
	}
	else{
		return false;
	}	
}

function validateSave(){
	var valid=false;
	if(isEmpty(document.getElementsByName("profileGroupName")[0],"Profile Group Name")
	//&& comboValidation(document.getElementsByName("deptUnitCode")[0],"Department Unit")
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
	document.getElementsByName("profileGroupName")[0].value=""
	document.getElementsByName("deptUnitCode")[0].value="-1"
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
</script>

<his:TransactionContainer>
 <body>

  <html:form action="/master/profileGroupMaster" > 
    <html:hidden name="ProfileGroupMasterFB" property="hmode"/>
    <html:hidden name="ProfileGroupMasterFB" property="serialNo"/>
    <html:hidden name="ProfileGroupMasterFB" property="profileGroupId"/>
   <%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="ProfileGroupMasterFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
  
        
	   		<his:TitleTag name="Profile Group Master">
			</his:TitleTag>
  	   
       
	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="profileGroupName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			  
			     <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp;	<html:text name="ProfileGroupMasterFB" property="profileGroupName" tabindex="1"   styleClass="textbox" maxlength="50" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)" >
							   </html:text>

					</div>
				</td>
		      </tr>
		  
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
<!--			     <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> -->
<!--								*-->
<!--					</font> -->
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<b><bean:message key="deptUnitName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont" colspan="3">
					<div align="left">
					&nbsp;	<html:select name="ProfileGroupMasterFB" property="deptUnitCode" tabindex="1" styleClass="regcbo" disabled="<%=this.readOnly %>" style="width:400px">
						<html:option value="-1">Select Value</html:option>
						<html:options collection="<%=OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST %>" property="value"  labelProperty="label"  />
						</html:select>
					</div>
				</td>
			   
		      </tr>
		       
		  	  <logic:notEqual name="ProfileGroupMasterFB" property="hmode" value="ADD">
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
				     &nbsp; <html:select name="ProfileGroupMasterFB" property="isActive" disabled="<%=this.readOnly %>" styleClass="regcbo">
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
			   
			    <logic:equal name="ProfileGroupMasterFB" property="hmode" value="ADD">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) return finalSubmit('SAVE')" onclick="return finalSubmit('SAVE')">
				</logic:equal>
				
				<logic:notEqual name="ProfileGroupMasterFB" property="hmode" value="VIEW">
				<logic:notEqual name="ProfileGroupMasterFB" property="hmode" value="ADD">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				</logic:notEqual> 
				</logic:notEqual>
				
				<logic:notEqual name="ProfileGroupMasterFB" property="hmode" value="VIEW">
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
		     
		   
		  