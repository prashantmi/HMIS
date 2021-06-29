<!-- Checklist Master checklistMasterAddMod.jsp -->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page info="Used for addition , modification and view of Checklist Master" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="hisglobal.hisconfig.*" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
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
	/*if(document.getElementsByName("hmode")[0]!="ADD"){
		document.getElementsByName("disasterInfoId")[0].value=;
	}*/
	if(isEmpty(document.getElementsByName("checklist")[0],"Checklist")
	&& isSelected(document.getElementsByName("isCompulsory")[0],"Is Compulsory")
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
	
	document.getElementsByName("checklist")[0].value=""
	document.getElementsByName("isCompulsory")[0].value="-1"
	document.getElementsByName("remarks")[0].value=""
	if(document.getElementsByName("isValid")[0]){
		document.getElementsByName("isValid")[0].value="-1"
	}
}

function validateIsActive(){
	var valid=true
	if(document.getElementsByName("isValid")[0]){
		if(isSelected(document.getElementsByName("isValid")[0],"Is Active")){
			valid= true;
		}
		else{
			valid= false;
		}	
	}
	return valid;
}

function validateEmail(){
	var valid=true;
	var email=document.getElementsByName("organisationEmail")[0].value;
	//alert(email.length)
	if(email.length>0){
		var s=email.split("@");
		var index=email.indexOf("@");
		if(s.length!=2){
			valid=false;
		}
		else{
			//alert(email.lastIndexOf(".")- email.indexOf(".",index))
			//alert(email.length - email.lastIndexOf("."))
			if(s[1].split(".").length>2){
				if((email.length - email.lastIndexOf("."))==3 && ((email.lastIndexOf(".")- (email.indexOf(".",index))==3))){
					valid=true;
				}
				else{
					valid=false;
				}
				
			}
			else{
				if((email.length - email.lastIndexOf("."))==4){
					valid=true
				}
				else{
					valid=false;
				}
			}	
			if((email.indexOf(".",index)- index)==1){
				valid=false;
			}
		}
	}
	if(!valid){
		alert("Enter valid email")
		document.getElementsByName("organisationEmail")[0].focus();
	}	
	return valid;
}

</script>

<his:TransactionContainer>
 <body>

  <html:form action="/master/checklistMaster" > 
    <html:hidden name="checklistMasterFB" property="hmode"/>
    <html:hidden name="checklistMasterFB" property="serialNo"/>
        
   <%!boolean readOnly; %>
   <% this.readOnly=false; %>
  
       <logic:equal name="checklistMasterFB" property="hmode" value="ADD">
	   		<his:TitleTag name="Checklist Master >> Add">
			</his:TitleTag>
  	   </logic:equal>
       <logic:equal name="checklistMasterFB" property="hmode" value="MODIFY">
	   		<his:TitleTag name="Checklist Master >> Modify">
			</his:TitleTag>
  	   </logic:equal>
       <logic:equal name="checklistMasterFB" property="hmode" value="VIEW">
	   		<his:TitleTag name="Checklist Master >> View">
			</his:TitleTag>
	   		<% this.readOnly=true; %>
  	   </logic:equal>
       
	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="checklist"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			     &nbsp;<html:text name="checklistMasterFB" property="checklist" tabindex="1" size="35" maxlength="50" readonly="<%=this.readOnly %>" onkeypress="return validateAlphabetsOnly(event,this)" >
					  </html:text>
			     </td>  
	      </tr>
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					</font> 
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<b><bean:message key="isCompulsory"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      &nbsp;<html:select name="checklistMasterFB" property="isCompulsory" tabindex="1"  styleClass="regcbo" disabled="<%=this.readOnly %>" style="width:200px">
						<html:option value="-1">Select Value</html:option>
						<html:options collection="<%=MedicalBoardConfig.IS_COMPULSORY_OPTION_LIST %>" labelProperty="label" property="value"/>
	  				</html:select>
			     </td>  
		      </tr>
		   <tr>
		      <td width="50%" class="tdfonthead">
			     <div align="right">
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="remarks"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			            &nbsp;<html:textarea name="checklistMasterFB" property="remarks" tabindex="1" rows="2" cols="30" readonly="<%=this.readOnly %>" onkeypress="return CheckMaxLength(event,this,50,1)" >
							  </html:textarea>
					</div>
			     </td>  
		      </tr>
		  	  <logic:notEqual name="checklistMasterFB" property="hmode" value="ADD">
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
				     &nbsp;<html:select name="checklistMasterFB" property="isValid" disabled="<%=this.readOnly %>" styleClass="regcbo" tabindex="1">
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
			   
			    <logic:equal name="checklistMasterFB" property="hmode" value="ADD">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) return finalSubmit('SAVE')" onclick="return finalSubmit('SAVE')">
				</logic:equal>
				
				<logic:notEqual name="checklistMasterFB" property="hmode" value="VIEW">
				<logic:notEqual name="checklistMasterFB" property="hmode" value="ADD">
				   <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				</logic:notEqual> 
				</logic:notEqual>
				
				<logic:notEqual name="checklistMasterFB" property="hmode" value="VIEW">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual> 
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		 
		 <his:status/>
        <html:hidden name="checklistMasterFB" property="checklistID"/>
        <html:hidden name="checklistMasterFB" property="serialNo"/>
   </html:form>
  </body>
  </his:TransactionContainer>
</html>
		     
		   
		  