<!-- 
/**
 * @author CDAC
 */
-->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />


<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/validationCommon.js" />

<his:javascript src="/opd/opdJs/opd.js" />


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function submitCmoLogin(mode)
{
	var frm=document.getElementById("cmoDeskLoginForm");
// 	alert(frm);
	frm.hmode.value=mode;
//	alert("hmode value="+frm.hmode.value);
	frm.submit();
}

function callThisOnload()
{
	hideMenuFrame();
	
}

</script>

<%@page import="opd.OpdConfig"%>
<html>
	<form id="cmoDeskLoginForm" action="/HISClinical/registration/cmoDeskLogin.cnt" method="post">	
	
		<his:ContentTag>
			<his:statusInProcess>
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="selectUnitForCmoDesk" /></b>
								</font>
							</div>
						</td>
		
						
					</tr>
					
					<tr>
						<td class="tdfont" width="20%">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:select name="CMODeskLoginFB" property="departmentUnitCode" tabindex="1" styleClass="regCbo" onchange="submitCmoLogin('SAVE');">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=OpdConfig.OPD_DESK_UNIT_LIST%>" >
										<html:options collection="<%=OpdConfig.OPD_DESK_UNIT_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</font>
							</div>
						</td>
						
						
					</tr>
				</table>
			</his:statusInProcess>
			
			<his:ButtonToolBarTag>
				<div align="center">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" onclick="submitPage('CANCEL');">
				</div>
			</his:ButtonToolBarTag>
		</his:ContentTag>
	
		<his:status />
	
		<html:hidden name="CMODeskLoginFB" property="hmode" />
		
	</form>
</html>
