<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

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

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<bean:define id="srcflag" name="ModifyAllergyTypeFB" property="sourceFlag" type="java.lang.String"></bean:define>

<script>

function validateMaster()
{
	if(document.getElementsByName("allergiesType")[0].value=="")
		{
		alert("Please Enter Allergy type")
		document.forms[0].allergiesType.focus();
		return false;
		}
	else if(document.getElementsByName("allergiesDesc")[0].value=="")
	  {
		alert("Please Enter Allergy description")
		document.forms[0].allergiesDesc.focus();
		return false;
	  }
	
			validateChoice();
			{
				submitTile('UPDATE');
			}
}
		

function validateChoice()
{
		
	 if(document.getElementsByName('sourceFlag')[1].checked)
   {
     if(document.getElementsByName("longQuery")[0].value=="")
		{
		alert("Please Enter Query")
		document.forms[0].longQuery.focus();
		return false;
		}
		else
		{
		return true;
		}
	}
}
   
function submitTile(mode)
{
   			document.getElementsByName("transactionMode")[0].value=mode;  
    		document.forms[0].submit();
}

function showTableQuery()
{
			document.getElementById("staticDynamic").style.display="";
			document.getElementById("divTableQueryLabel").style.display="";
			document.getElementById("divTableQueryControl").style.display="";
}
function doNotShow()
{
			document.getElementById("staticDynamic").style.display="none";
			document.getElementById("divTableQueryLabel").style.display="none";
			document.getElementById("divTableQueryControl").style.display="none";
			document.getElementsByName("longQuery")[0].value="";
}

window.onload = function() 
{
 //alert("<%=srcflag%>");
 	//if(document.getElementsByName("sourceFlag")[0].value=="1")
 	if("<%=srcflag%>"=="1")
 	{
 		doNotShow();
 	}
 	else
 	{
 		showTableQuery();
 	}   
}

</script>
	<body>
		<html:form action="/master/allergyTypeModify">		
			<his:TransactionContainer>
				<%@ page import ="registration.*,opd.*" %>
				<%boolean varReadOnly=false; %>
				<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY %>" value="view">
						<%varReadOnly=true; %>
				</logic:equal>
				
				<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>"value="modify">
					<his:TitleTag name="Allergy Type Master >> Modify">
					</his:TitleTag>
				</logic:equal>
				
				<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>"value="view">
					<his:TitleTag name="Allergy Type Master >> View ">
					</his:TitleTag>
				</logic:equal>
				
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="40%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*
	  									</font>
	  									<b>
	  										<bean:message key="allergyType"/>
	  									</b>
	  								</font>
								</div>
							</td>
							<td width="60%" class="tdfont">
								<div align="left">
									&nbsp;<html:text name="ModifyAllergyTypeFB" property="allergiesType" maxlength="50" size="35" readonly="<%=varReadOnly %>"></html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="40%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*
	  									</font>
	  									<b>
	  										<bean:message key="allergyDesc"/>
	  									</b>
	  								</font>
								</div>
							</td>
							<td width="60%" class="tdfont">
								<div align="left">
									&nbsp;<html:text name="ModifyAllergyTypeFB" property="allergiesDesc" maxlength="500" size="35" readonly="<%=varReadOnly %>"></html:text>
								</div>
							</td>
						</tr>
						<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
							<tr>
			      <td width="50%" class="tdfonthead">
			          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 <b><bean:message key="sensitivity"/></b> 
			 		  </font>
		     	 
			     </td>
			     <td width="50%" class="tdfont">
			         &nbsp;<html:select name="ModifyAllergyTypeFB" property="allergySensitivity"  disabled="true">
						  <html:option value="-1">Select Value</html:option>
						  <logic:present name="<%=OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY%>" >
						  <html:options collection="<%=OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY%>" property="value" labelProperty="label" />
						  </logic:present>
					   </html:select>
				 
				 </td>
			  </tr></logic:equal>
			  <logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
			  	<tr>
			      <td width="50%" class="tdfonthead">
			          <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						 <b><bean:message key="sensitivity"/></b> 
			 		  </font>
		     	 
			     </td>
			     <td width="50%" class="tdfont">
			         &nbsp;<html:select name="ModifyAllergyTypeFB" property="allergySensitivity" >
						  <html:option value="-1">Select Value</html:option>
						  <logic:present name="<%=OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY%>" >
						  <html:options collection="<%=OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY%>" property="value" labelProperty="label" />
						  </logic:present>
					   </html:select>
				 
				 </td>
			  </tr></logic:equal>
						<tr>
							<td width="40%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*
	  									</font>
	  									<b>
	  										<bean:message key="isvalid"/>
	  									</b>
	  								</font>
								</div>
							</td>
						
							<td width="60%" class="tdfont"> 
								<div align="left">
									&nbsp;<html:select name="ModifyAllergyTypeFB" property="isValid" disabled="<%=varReadOnly%>">
										<html:option value="1">Valid</html:option>
										<html:option value="2">InValid</html:option>
									</html:select>
								</div>
							</td>
						</tr>
						
						<tr>
							<td width="40%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*
	  									</font>
	  									<b>
	  										<bean:message key="staticOrDynamic"/>
	  									</b>
	  								</font>
								</div>
							</td>
							<td width="60%" class="tdfont">
								<div align="left">
									&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		      							<%=OpdConfig.ALLERY_TYPE_SOURCE_FLAG_ARR[Integer.parseInt(OpdConfig.ALLERY_TYPE_SOURCE_FLAG_STATIC)] %>
		      						</font>
		      						<html:radio name="ModifyAllergyTypeFB" property="sourceFlag" tabindex="1"  disabled="<%=varReadOnly%>" value="<%=OpdConfig.ALLERY_TYPE_SOURCE_FLAG_STATIC%>" onclick="doNotShow()"/>
		       						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		       							<%=OpdConfig.ALLERY_TYPE_SOURCE_FLAG_ARR[Integer.parseInt(OpdConfig.ALLERY_TYPE_SOURCE_FLAG_DYNAMIC)] %>
		       						</font> 
		        					<html:radio name="ModifyAllergyTypeFB" property="sourceFlag" tabindex="1"  disabled="<%=varReadOnly%>" value="<%=OpdConfig.ALLERY_TYPE_SOURCE_FLAG_DYNAMIC%>" onclick="showTableQuery()"/>
								</div>
							</td>
						</tr>
					</table>	
					<div id="staticDynamic" style="display: none;">
				
					<table width="100%" border="0" cellspacing="1" cellpadding="0">  	
						<tr>
							<td width="40%" class="tdfonthead">
								<div align="right" id="divTableQueryLabel" >
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*
	  									</font>
	  									<b>
	  										<bean:message key="query"/>
	  									</b>
	  								</font>
								</div>
							</td>
							<td width="60%" class="tdfont">
								<div align="left" id="divTableQueryControl" >
								&nbsp;<html:textarea name="ModifyAllergyTypeFB" property="longQuery" cols="50" rows="3" readonly="<%=varReadOnly%>" ></html:textarea>
								</div>
							</td>
						</tr>
				</table>
				</div>
				</his:ContentTag>
				
				<his:ButtonToolBarTag>
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="view">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer  onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
					</logic:equal>
					<logic:equal name="<%=RegistrationConfig.VIEWORMODIFY%>" value="modify">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer  onclick ="validateMaster()" onkeypress="if(event.keyCode==13) validateMaster()">
    				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer  onclick ="submitTile('CANCEL')" onkeypress="if(event.keyCode==13) submitTile('CANCEL')">
    				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer  onclick ="submitTile('CLEAR')" onkeypress="if(event.keyCode==13) submitTile('CLEAR');">          	
					</logic:equal>
					
				</his:ButtonToolBarTag>
				<center><b><his:status/></b></center>
			</his:TransactionContainer>
			<html:hidden name="ModifyAllergyTypeFB" property="transactionMode"/>
			<html:hidden name="ModifyAllergyTypeFB" property="viewOrModify" />
			<html:hidden name="ModifyAllergyTypeFB" property="chk" />
			<html:hidden name="ModifyAllergyTypeFB" property="longQuery" />
		</html:form>	
	</body>
</html>