<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
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
<his:javascript src="/medicalboard/js/roleMstAddModjs.js" />



<body>
<html:form action="/masters/RoleMasterACTION" >

	<html:hidden name="RoleMasterFB" property="hmode"/>
	<html:hidden name="RoleMasterFB" property="slNo"/>
    <html:hidden name="RoleMasterFB" property="roleID"/>
	
	<his:TransactionContainer>
		<his:TitleTag name="Role Master">
			
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
								<bean:message key="roleName"/>&nbsp;
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							
							<logic:notEqual name="RoleMasterFB" property="hmode" value="VIEW">
						 &nbsp;<html:text name="RoleMasterFB" property="roleName" maxlength="50" size="30" onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
						 </html:text>
							</logic:notEqual>
							<logic:equal name="RoleMasterFB" property="hmode" value="VIEW">
							  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						     <b><bean:write name="RoleMasterFB"  property="roleName" /> </b>
						   </font>  
							</logic:equal>
						</div>
					</td>
					</tr>
			</table>
	</his:ContentTag>
		<his:ButtonToolBarTag>
			  <span id="saveDiv">
			     <logic:notEqual name="RoleMasterFB" property="hmode" value="MODIFY">
			     <logic:notEqual name="RoleMasterFB" property="hmode" value="VIEW">
					 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateOnSave(),'SAVE')" onclick="submitFormOnValidate(validateOnSave(),'SAVE')" tabindex="1">
				     <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				 </logic:notEqual>
				 </logic:notEqual>
				 <logic:equal name="RoleMasterFB" property="hmode" value="MODIFY">
				       <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateOnSave(),'MODIFYSAVE')" onclick="submitFormOnValidate(validateOnSave(),'MODIFYSAVE')" tabindex="1">
				       <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				 </logic:equal>    
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
			</span>
		  </his:ButtonToolBarTag>
	</his:TransactionContainer>
	
 <his:status/>
</html:form>



</html>