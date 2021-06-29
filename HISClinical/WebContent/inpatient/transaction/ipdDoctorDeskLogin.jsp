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
	//alert("ipdDocDeskLogin -->>inside submitNursingLogin :"+mode);
	var frm=document.getElementById("ipdDoctorDeskLoginForm");
	frm.hmode.value=mode;
	frm.submit();
	
}

function callThisOnload()
{
	hideMenuFrame();
	/*var checkUnit=document.getElementsByName("unitList")[0].value;
	var checkRoom=document.getElementsByName("roomList")[0].value;
	if(checkUnit=="0")
	{
		if(checkRoom=="0")
		{alert("1");
			document.getElementById("wardLabel").style.display="none"; 
	 		document.getElementById("wardControl").style.display="none"; 
	 		document.getElementById("roomLabel").style.display="none"; 
	 		document.getElementById("roomControl").style.display="none";
	 	}
	 		 
	}
	else
	{
		if(checkRoom=="1")
		{alert("2");
			document.getElementById("wardLabel").style.display=""; 
	 		document.getElementById("wardControl").style.display=""; 
	 		document.getElementById("roomLabel").style.display=""; 
	 		document.getElementById("roomControl").style.display="";
	 	}
	 	else
	 	{alert("3");
	 		document.getElementById("wardLabel").style.display=""; 
	 		document.getElementById("wardControl").style.display=""; 
	 		document.getElementById("roomLabel").style.display="none"; 
	 		document.getElementById("roomControl").style.display="none";
	 	}	
	}*/
	
}
</script>

<%@page import="inpatient.InpatientConfig;"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<form action="/HISClinical/inpatient/ipdDoctorDeskLogin.cnt" method="post" id="ipdDoctorDeskLoginForm">
	<his:TransactionContainer>
		<his:ContentTag>
		<his:statusInProcess>
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="20%" class="tdfonthead" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="selectUnitForDoctorDesk"/>
								</b>
							</font>
						</div>
					</td>
					<logic:equal name="IpdDoctorDeskLoginFB" property="unitList" value="1">
						<td width="20%" class="tdfonthead" id="wardLabel">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="selectWard"/>
									</b>
								</font>
							</div>
						</td>
					</logic:equal>
					
					<logic:equal name="IpdDoctorDeskLoginFB" property="unitList" value="1">
						<logic:equal name="IpdDoctorDeskLoginFB" property="roomList" value="1">
							<td width="20%" class="tdfonthead" id="roomLabel">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="selectRoomForOpdDesk"/>
										</b>
									</font>
								</div>
							</td>
						</logic:equal>
					</logic:equal>
				</tr>
				<tr>
					<td width="20%" class="tdfonthead" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:select name="IpdDoctorDeskLoginFB" property="departmentUnitCode" tabindex="1" styleClass="regCbo"  onchange="submitNursingLogin('GETWARD')">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=InpatientConfig.NURSING_DESK_DEPT_UNIT_LIST%>">
									<html:options collection="<%=InpatientConfig.NURSING_DESK_DEPT_UNIT_LIST %>" property="value" labelProperty="label"/>
									</logic:present>
								</html:select>
							</font>
						</div>
					</td>
					<logic:equal name="IpdDoctorDeskLoginFB" property="unitList" value="1">
					<td width="20%" class="tdfonthead" id="wardControl">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:select name="IpdDoctorDeskLoginFB" property="wardCode" tabindex="1" styleClass="regCbo" onchange="submitNursingLogin('GETROOM')">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_UNIT%>">
									<html:options collection="<%=InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_UNIT %>" property="value" labelProperty="label"/>
									</logic:present>
								</html:select>
							</font>
						</div>
					</td>
					</logic:equal>
					<logic:equal name="IpdDoctorDeskLoginFB" property="unitList" value="1">
						<logic:equal name="IpdDoctorDeskLoginFB" property="roomList" value="1">
							<td width="20%" class="tdfonthead" id="roomControl">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<html:select name="IpdDoctorDeskLoginFB" property="roomCode" tabindex="1" styleClass="regCbo" onchange="submitNursingLogin('SAVE')">
											<html:option value="-1">Select Value</html:option>
											<html:option value="<%=InpatientConfig.ROOM_ALL_CODE%>">All</html:option>
											<logic:present name="<%=InpatientConfig.NURSING_DESK_ROOM_LIST_ON_BASIS_WARD%>">
											<html:options collection="<%=InpatientConfig.NURSING_DESK_ROOM_LIST_ON_BASIS_WARD %>" property="value" labelProperty="label"/>
											</logic:present>
										</html:select>
									</font>
								</div>
							</td>
						</logic:equal>
					</logic:equal>
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
	
	<html:hidden name="IpdDoctorDeskLoginFB" property="hmode"/>
	<html:hidden name="IpdDoctorDeskLoginFB" property="unitList"/>
	<html:hidden name="IpdDoctorDeskLoginFB" property="roomList"/>
</form>
<his:status/>

</html>