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
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:css src="/css/calendar-blue2.css" />
<script>
function checkOnLoad()
{
 if(document.getElementsByName("chamberType")[1].checked==true)
 		{
 		document.getElementsByName("rackNumbers")[0].disabled=true;
 		}
 		else
 		{
 		document.getElementsByName("rackNumbers")[0].disabled=false;
 		}


}
function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
  if(
       isEmpty(document.forms[0].chamberName,"Chamber Name") &&
       isEmpty(document.getElementsByName('rackNumbers')[0],"Rack Numbers") &&
	   validateIsActive()
	)
	{
		return true;
	}	
	else{	
		return false;
	}	
 } 	
function validateIsActive(){
	if(document.getElementsByName('hmode')[0].value=="MODIFY"){	
		if(comboValidation(document.forms[0].isValid,"Is Active Status"))
		{
			return true;
		}
		else{
			return false;
		}
	}
	return true;
    
} 
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}

function clearForm()
 {
   
    document.getElementsByName('chamberName')[0].value="";
    document.getElementsByName('chamberStatus')[1].checked=true;
    document.getElementsByName('chamberType')[0].checked=true;
    document.getElementsByName('rackNumbers')[0].value="";
    document.getElementsByName('rackNumbers')[0].disabled=false;
    document.getElementsByName('remarks')[0].value="";
    
    
    if( document.getElementsByName('hmode')[0].value=="MODIFY"){
    	document.getElementsByName('isValid')[0].value="-1";
  	}
 }
 
 function selectChamberType()
 {
 
 if(document.getElementsByName("chamberType")[1].checked==true)
 		{
 		document.getElementsByName("rackNumbers")[0].value="1";
 		document.getElementsByName("rackNumbers")[0].disabled=true;
 		}
 		else
 		{
 		document.getElementsByName("rackNumbers")[0].value="";
 		document.getElementsByName("rackNumbers")[0].disabled=false;
 		}
 
 				
 } 
 
 
 
</script>

 <body onload="focusFirstElementOnLoad(),checkOnLoad()">


  <html:form action="/master/ChamberMaster" > 
    
   <%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
    
 
   <logic:equal name="ChamberMasterFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   </logic:equal>
  
     <his:TransactionContainer>
       <logic:equal name="ChamberMasterFB" property="hmode" value="ADD">
	   		<his:TitleTag name="Chamber Master >> Add">
			</his:TitleTag>
  	   </logic:equal>
       <logic:equal name="ChamberMasterFB" property="hmode" value="MODIFY">
	   		<his:TitleTag name="Chamber Master >> Modify">
			</his:TitleTag>
  	   </logic:equal>
       <logic:equal name="ChamberMasterFB" property="hmode" value="VIEW">
	   		<his:TitleTag name="Chamber Master >> View">
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
								<b><bean:message key="mortuaryName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			            <bean:write property="mortuaryName" name="ChamberMasterFB"/>
			            
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
								<b><bean:message key="areaName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			           <bean:write property="areaName" name="ChamberMasterFB" />
			         
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
								<b><bean:message key="chamberName"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			            <html:text name="ChamberMasterFB" property="chamberName" tabindex="1"  maxlength="50" readonly="<%=this.readOnly %>" onkeypress="return validateAlphaNumericOnly(event,this)" />
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
								<b><bean:message key="chamberStatus"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			      <html:radio name="ChamberMasterFB" property="chamberStatus" value="0" tabindex="1" disabled="<%=this.readOnly %>" />Not Used
       			  <html:radio name="ChamberMasterFB" property="chamberStatus" value="1" tabindex="1" disabled="<%=this.readOnly %>"/>Working			            			      
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
								<b><bean:message key="chamberType"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			     	    <html:radio name="ChamberMasterFB" property="chamberType" value="1" tabindex="1" onclick="selectChamberType()" disabled="<%=this.readOnly %>"/>Rack Mount
       			  		<html:radio name="ChamberMasterFB" property="chamberType" value="2" tabindex="1" onclick="selectChamberType()" disabled="<%=this.readOnly %>"/>Chamber Room		            			      
				    
							   
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
								<b><bean:message key="rackNumbers"/>&nbsp;</b>
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			           <html:text property="rackNumbers"  name="ChamberMasterFB" maxlength="2" size="3" readonly="<%=this.readOnly %>" onkeypress="return validatePositiveIntegerOnly(this,event)" tabindex="1"/>
							  
					</div>
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
			         <html:textarea property="remarks" name="ChamberMasterFB" tabindex="1" onkeypress="return CheckMaxLength(event,this,100,1)"  disabled="<%=this.readOnly %>"/>
					</div>
			     </td>  
		      </tr>
		  
		
		  	  <logic:notEqual name="ChamberMasterFB" property="hmode" value="ADD">
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
				    <html:select name="ChamberMasterFB" property="isValid" tabindex="1" disabled="<%=this.readOnly %>" >
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
			   
			    <logic:equal name="ChamberMasterFB" property="hmode" value="ADD">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				</logic:equal>
				<logic:notEqual name="ChamberMasterFB" property="hmode" value="VIEW">
				<logic:notEqual name="ChamberMasterFB" property="hmode" value="ADD">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				</logic:notEqual> 
				</logic:notEqual>
				<logic:notEqual name="ChamberMasterFB" property="hmode" value="VIEW">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual> 
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')" >
			</span>
		</his:ButtonToolBarTag>
		 
		 <his:status/>
      </his:TransactionContainer>
   
   <html:hidden name="ChamberMasterFB" property="hmode"/>
	<html:hidden name="ChamberMasterFB" property="chamberId"/>
	<html:hidden name="ChamberMasterFB" property="serialNo"/>
    <html:hidden name="ChamberMasterFB" property="chk"/>
    <html:hidden name="ChamberMasterFB" property="areaCode"/>
    <html:hidden name="ChamberMasterFB" property="mortuaryCode"/>
    <html:hidden name="ChamberMasterFB" property="areaName"/>
    <html:hidden name="ChamberMasterFB" property="mortuaryName"/>

    
   </html:form>
  
   
  </body>
</html>
		     
		   
		  