<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@page import="hisglobal.hisconfig.*" %>
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

function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
	if(isEmpty(document.forms[0].dutyBlockName,"Duty Block Name")
	&& isEmpty(document.forms[0].dutyBlockDesc,"Duty Block Description" )
	&& validateIsActive())
	{
		
		return true;
	}	
	else{	
		return false;
	}	
 } 	
function validateIsActive(){
	
	if(document.getElementsByName('hmode')[0].value=="MODIFY"){	
		if(comboValidation(document.forms[0].isActive,"Is Active Status"))
		{
			return true;
		}
		else{
			return false;
		}
	}
	else{
		return true;
	}		
    
} 
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
}

function clearForm()
 {
   
    document.getElementsByName('dutyBlockName')[0].value="";
    document.getElementsByName('dutyBlockDesc')[0].value="";
    if( document.getElementsByName('hmode')[0].value=="MODIFY"){
    	document.getElementsByName('isActive')[0].value="-1";
  	}
 }
  function validateAlphaNumOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		charCode==44 || 
		charCode==32 || 
		charCode==46 || 
		(charCode>=48 && charCode<=57) || 
		(charCode>=65 && charCode<=90) || 
		(charCode>=97 && charCode<=122) )
		return true;
	else
		return false;
}
</script>

 <body onload="focusFirstElementOnLoad()">
 
 
   <html:form action="/masters/dutyBlockMstAddModACT" > 
	    
	    
	     <%!boolean readOnly; %>
	   <% this.readOnly=false; %>
	   
	   <logic:equal name="dutyBlockMstFB" property="hmode" value="VIEW">
	   		<% this.readOnly=true; %>
	   </logic:equal>
        <logic:equal name="dutyBlockMstFB" property="hmode" value="ADD">
	   		<his:TitleTag name="Duty Block Master >> Add">
			</his:TitleTag>
  	   </logic:equal>
       <logic:equal name="dutyBlockMstFB" property="hmode" value="MODIFY">
	   		<his:TitleTag name="Duty Block Master >> Modify">
			</his:TitleTag>
  	   </logic:equal>
       <logic:equal name="dutyBlockMstFB" property="hmode" value="VIEW">
	   		<his:TitleTag name="Duty Block Master  >> View">
			</his:TitleTag>
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
						<b><bean:message key="dutyBlockName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      	<div align="left">
			            &nbsp;<html:text name="dutyBlockMstFB" property="dutyBlockName" tabindex="1"  maxlength="50" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)" >
							   </html:text>
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
						<b><bean:message key="dutyBlockDesc"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      	<div align="left">
			            &nbsp;<html:text name="dutyBlockMstFB" property="dutyBlockDesc" tabindex="1"  maxlength="100" size="30" readonly="<%=this.readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)" >
					   </html:text>
					</div>
			     </td>  
		      </tr>
		   	  <logic:notEqual name="dutyBlockMstFB" property="hmode" value="ADD">
			        <tr>
			         <td width="50%" class="tdfonthead">
			          <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b><bean:message key="isActive"/>&nbsp;</b>
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     &nbsp;<html:select name="dutyBlockMstFB" property="isActive" tabindex="1"  disabled="<%=this.readOnly %>" >
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
			    <logic:equal name="dutyBlockMstFB" property="hmode" value="ADD">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				</logic:equal>
				<logic:notEqual name="dutyBlockMstFB" property="hmode" value="VIEW">
				<logic:notEqual name="dutyBlockMstFB" property="hmode" value="ADD">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				</logic:notEqual> 
				</logic:notEqual>
				<logic:notEqual name="dutyBlockMstFB" property="hmode" value="VIEW">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual> 
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		<his:status/>
     
     	<html:hidden name="dutyBlockMstFB" property="hmode"/>
		<html:hidden name="dutyBlockMstFB" property="dutyBlockId"/>
		<html:hidden name="dutyBlockMstFB" property="serialNo"/>
	    <html:hidden name="dutyBlockMstFB" property="chk"/>
	  
	  
	  
     </html:form>
  </body>
</html>
		     
		   
		  