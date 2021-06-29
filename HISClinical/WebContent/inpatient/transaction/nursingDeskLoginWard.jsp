<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script>

function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function submitNursingLogin(mode)
{
	var frm=document.getElementById("nursingDeskLoginForm");
	frm.hmode.value=mode;
	frm.submit();
	
}

function callThisOnload()
{
	hideMenuFrame();
	/*var check=document.getElementsByName("unitList")[0].value;
	if(check=="0")
	{
		document.getElementById("wardLabel").style.display="none"; 
 		document.getElementById("wardControl").style.display="none"; 
	}
	else
	{
		document.getElementById("wardLabel").style.display=""; 
 		document.getElementById("wardControl").style.display=""; 
	}*/
	
}
</script>

<%@page import="inpatient.InpatientConfig;"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<form action="/HISClinical/inpatient/nursingDeskLogin.cnt" method="post" id="nursingDeskLoginForm">
	<his:TransactionContainer>
		<his:ContentTag>
		<his:statusInProcess>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="20%" class="tdfonthead" id="wardLabel">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="selectWard"/>
								</b>
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="20%" class="tdfonthead" id="wardControl">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:select name="NursingDeskLoginFB" property="wardCode" tabindex="1" styleClass="regCbo" onchange="submitNursingLogin('GETROOM')">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_ROLE%>">
									<html:options collection="<%=InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_ROLE %>" property="value" labelProperty="label"/>
									</logic:present>
								</html:select>
							</font>
						</div>
					</td>
				</tr>			
			</table>
			</his:statusInProcess>
		</his:ContentTag>
	<his:ButtonToolBarTag>

		<div align="center">
			<img class="button"	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"	style="cursor: pointer"	onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"onclick="submitPage('CANCEL');">
		</div>

	</his:ButtonToolBarTag>
	
	</his:TransactionContainer>
	
	<html:hidden name="NursingDeskLoginFB" property="hmode"/>
</form>
<his:status/>

</html>