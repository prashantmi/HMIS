<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%> 


<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="dutyroster.DutyRosterConfig;"%>
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
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/dutyroster/js/otherAreaMstAddMod.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<his:css src="/css/calendar-blue2.css" />

 <body onload="focusFirstElementOnLoad()">

  <html:form action="/masters/OtherAreaMstAddModACT" > 
    
    
     <his:TransactionContainer>
       <his:TitleTag name="Other Area Master">
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
								<bean:message key="areaName"/>&nbsp;
					</font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			        
			        <logic:notEqual name="OtherAreaMstFB" property="hmode" value="VIEW">
			          &nbsp;<html:text name="OtherAreaMstFB" property="otherAreaName" tabindex="1"  maxlength="50" size="30" onkeypress="return validateAlphaNumericOnly(event,this)" >
							   </html:text>
					</logic:notEqual>
							   
				    <logic:equal name="OtherAreaMstFB" property="hmode" value="VIEW">
								<html:hidden name="OtherAreaMstFB" property="otherAreaName" />
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								  &nbsp;<b><bean:write name="OtherAreaMstFB" property="otherAreaName"/></b> 
								</font>
				     </logic:equal>
				     			   
				   </div>
			     </td>  
		      </tr>
		      
		      
		     
					
			  
			 
			 
			 <logic:equal name="OtherAreaMstFB" property="hmode" value="MODIFY">
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
				     &nbsp;<html:select name="OtherAreaMstFB" property="isValid" tabindex="1"  style="width:160;">
						    <html:option value="-1">Select Value</html:option>
							<html:option value="1">Active</html:option>
							<html:option value="2">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
			   </logic:equal>
			  
		      
	    </table>
      </his:ContentTag>
      
       <his:ButtonToolBarTag>
			<span id="saveDiv">
			    <logic:notEqual name="OtherAreaMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="OtherAreaMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"  style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="OtherAreaMstFB" property="hmode" value="MODIFY">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1"   style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"   style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:equal> 
	               <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
		
      
    </his:TransactionContainer>
    <his:status/>
   
   <html:hidden name="OtherAreaMstFB" property="hmode"/>
	<html:hidden name="OtherAreaMstFB" property="otherAreaCode"/>
	<html:hidden name="OtherAreaMstFB" property="serialNo"/>
    <html:hidden name="OtherAreaMstFB" property="chk"/>
   
   
   </html:form>
  </body>
</html>
		     
		   
		  