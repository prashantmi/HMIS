<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">


function validateAdd()
{
	var valid=true;
	if(isEmpty(document.forms[0].abortionType,"Abortion Type"))
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateModify()
{
	var valid=true;
	if(isEmpty(document.forms[0].abortionType,"Abortion Type")
		&& comboValidation(document.forms[0].isActive,"Is Active"))
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}


function clearAddForm()
{
	document.getElementsByName("abortionType")[0].value="";
		
}

function clearModifyForm()
{
	document.getElementsByName("abortionType")[0].value="";
	document.getElementsByName("isActive")[0].value="-1";
}

</script>
<title>Abortion Type Master</title>

<body>
	<html:form action="/master/AbortionTypeMaster">
		<his:TransactionContainer>
	
			<logic:equal name="AbortionTypeMasterFB" property="hmode" value="ADD">
				<his:TitleTag name="Abortion Type Master >> ADD">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="AbortionTypeMasterFB" property="hmode" value="MODIFY">
				<his:TitleTag name="Abortion Type Master >> MODIFY">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="AbortionTypeMasterFB" property="hmode" value="VIEW">
				<his:TitleTag name="Abortion Type Master >> VIEW">
				</his:TitleTag>
			</logic:equal>
			<logic:notEqual name="AbortionTypeMasterFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="abortionType"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="AbortionTypeMasterFB" property="abortionType" maxlength="50" size="30" onkeypress="return validateAlphaOnly(this,event)"  >
								</html:text>
								<html:hidden name="AbortionTypeMasterFB" property="slNo"/>
								<html:hidden name="AbortionTypeMasterFB" property="abortionTypeID"/>
							</div>
						</td>
					</tr>
				
				 <logic:notEqual name="AbortionTypeMasterFB" property="hmode" value="ADD">
			 <logic:notEqual name="AbortionTypeMasterFB" property="hmode" value="SAVE">
			 <logic:notEqual name="AbortionTypeMasterFB" property="hmode" value="VIEW">
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
				     &nbsp;<html:select name="AbortionTypeMasterFB" property="isActive"  style="width:160;">
						    <html:option value="-1">Select Value</html:option>
							<html:option value="1">Active</html:option>
							<html:option value="2">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
			   </logic:notEqual>
			   </logic:notEqual>
			   </logic:notEqual> 	
				</table>	
			</his:ContentTag>
		</logic:notEqual>
				<logic:equal name="AbortionTypeMasterFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="abortionType"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="AbortionTypeMasterFB" property="abortionType" disabled="true" maxlength="50" size="30" >
								</html:text>
							
							</div>
						</td>
					</tr>
				
				
				</table>		
			</his:ContentTag>
			</logic:equal>
			<his:ButtonToolBarTag>
			<logic:equal name="AbortionTypeMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="if(validateAdd()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateAdd()) submitForm('SAVE')">
			</logic:equal>
			
			<logic:equal name="AbortionTypeMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateModify() && submitForm('MODIFYSAVE')" onkeypress="if(event.keyCode==13)validateModify() && submitForm('MODIFYSAVE')">
			</logic:equal>
			
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
			
			<logic:equal name="AbortionTypeMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" clearAddForm()" onkeypress="if(event.keyCode==13)  clearAddForm();">
			</logic:equal>
			<logic:equal name="AbortionTypeMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" clearModifyForm()" onkeypress="if(event.keyCode==13)  clearModifyForm();">
			</logic:equal>
				
			</his:ButtonToolBarTag>
			
			
			<html:hidden name="AbortionTypeMasterFB" property="hmode"/>
			<html:hidden name="AbortionTypeMasterFB" property="tempMode"/>
						
		</his:TransactionContainer>
		
		<his:status/>
	</html:form>
</body>
</html>			