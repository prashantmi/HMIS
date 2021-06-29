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
<his:css src="/hisglobal/utility/dynamicdesk/css/dynamic_desk_style.css"/>

<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />

<his:javascript src="/opd/opdJs/opd.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>

function submitPage(mode){
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function submitOpdLogin(mode){
	
	var frm=document.getElementById("opdDeskLoginForm");
// 	alert(frm);
	frm.hmode.value=mode;
//	alert("hmode value="+frm.hmode.value);
	frm.submit();
}

function callThisOnload(){
	hideMenuFrame();
 	var flag=document.getElementsByName("showRommList")[0].value
 	
 	if(flag=="0"){
 		document.getElementById("roomLable").style.display="none"; 
 		document.getElementById("roomControl").style.display="none"; 
 	}
 	else{
 		document.getElementById("roomLable").style.display=""; 
 		document.getElementById("roomControl").style.display=""; 
 	}
 }
 
 

</script>
<%@page import="opd.OpdConfig"%>
<html>
<form id="opdDeskLoginForm" action="/HISClinical/opd/opdBayDeskLogin.cnt"
	method="post"><his:ContentTag>
	<his:statusInProcess>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="20%" class="tdfonthead">
				<div align="center"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
					key="selectUnitForOpdDesk" /></b> </font></div>
				</td>
				<logic:equal name="OpdBayDeskLoginFB" property="showRommList" value="1">
				<td id="roomLable" width="20%" class="tdfonthead">
				<div align="center"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
					key="selectRoomForOpdDesk" /></b> </font></div>
				</td>
				</logic:equal>
			</tr>
			<tr>
				<td class="tdfont" width="20%">
				<div align="center"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <html:select
					name="OpdBayDeskLoginFB" property="departmentUnitCode" tabindex="1"
					styleClass="comboList" onchange="submitOpdLogin('GETROOM');">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=OpdConfig.OPD_DESK_UNIT_LIST%>" >
					<html:options collection="<%=OpdConfig.OPD_DESK_UNIT_LIST%>"
						property="value" labelProperty="label" />
					</logic:present>
				</html:select> </font></div>
				</td>
				<logic:equal name="OpdBayDeskLoginFB" property="showRommList" value="1">
					<td id="roomControl" class="tdfont" width="20%">
					<div align="center"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <html:select
						name="OpdBayDeskLoginFB" property="roomCode" tabindex="1"
						styleClass="comboList" onchange="submitOpdLogin('SAVE');">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=OpdConfig.OPD_ROOM_LIST%>" >
						<html:options collection="<%=OpdConfig.OPD_ROOM_LIST%>"
							property="value" labelProperty="label" />
						</logic:present>
					</html:select> </font></div>
					<%--   <img class="button"  class="button" src="../hisglobal/images/GO.png" tabindex="1" border="0" style="cursor:pointer;background-color: gray" onkeypress="if(event.keyCode==13)submitOpdLogin('SAVE');" onClick="submitOpdLogin('SAVE');" > --%>
					</td>
				</logic:equal>
			</tr>
		</table>
	</his:statusInProcess>

	<his:ButtonToolBarTag>

		<div align="center"><img class="button"
			src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"
			style="cursor: pointer"
			onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
			onclick="submitPage('CANCEL');"></div>

	</his:ButtonToolBarTag>
</his:ContentTag> <his:status /> <html:hidden name="OpdBayDeskLoginFB" property="hmode" /> <html:hidden
	name="OpdBayDeskLoginFB" property="showRommList" /></form>
</html>

