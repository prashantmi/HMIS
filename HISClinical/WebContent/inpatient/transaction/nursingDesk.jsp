<!-- 
/**
 * @author CDAC
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="org.apache.struts.tiles.ComponentContext"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/utility/dynamicdesk/js/desk.js" />

<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 

<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/validationCommon.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar-setup.js"/> 
<his:javascript src="/registration/js/registration.js"/>

<his:javascript src="/inpatient/js/nursingDesk.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<%
	ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
	String header=(String)compContext.getAttribute("header");
	String body=(String)compContext.getAttribute("body");
	String footer=(String)compContext.getAttribute("footer");
	
	String msg=(String)request.getAttribute("MESSAGE");
	String validUser=(String)request.getAttribute("VALID_USER");
	if(msg==null)		msg="";
	if(validUser==null)	validUser="";
%>

<html>
	<head>
		

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
function checkBlanketOnLoad()
{
	var msg="<%=msg%>";
	var validUser="<%=validUser%>";
	
	//In case of valid user the user is allowed to login
	if(validUser=="YES")	
	{
		document.forms[0].mode.value=document.forms[0].menuMode.value;
		document.forms[0].deskMenuId.value=document.forms[0].menuId.value;
		document.getElementsByName("enableBlanket")[0].value="NO";
		document.forms[0].submit();
	}
	//in case you want the blanket
	else if(validUser!="YES" && document.getElementsByName("enableBlanket")[0].value=="YES"  )
	{
		if(window.XMLHttpRequest) // Mozilla
		{
			parent.document.getElementById("frmDynamicDeskCenter").contentDocument.getElementById('blanket').style.display="block";
			parent.document.getElementById("frmDynamicDeskCenter").contentDocument.getElementById('userIdDiv').style.display="block";
			parent.document.getElementById("frmDynamicDeskCenter").contentDocument.getElementById('userName').focus();
		}
		else //if (window.ActiveXObject)
		{
			parent.document.getElementById("frmDynamicDeskCenter").Document.getElementById('blanket').style.display="block";
			parent.document.getElementById("frmDynamicDeskCenter").Document.getElementById('userIdDiv').style.display="block";
			parent.document.getElementById("frmDynamicDeskCenter").Document.getElementById('userName').focus();
		}	
	}
}
		</script>
	</head>

	<body onload="checkBlanketOnLoad();checkForOnSelectDeskListItem(null)" class="tundra">
		<html:form action="/nursingDesk" styleId="commonTransactionLayoutFormId">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr><td width="100%" align="center">
					<jsp:include page="<%=header%>" flush="true"></jsp:include>
				</td></tr>

				<tr><td width="100%" align="center">
					<jsp:include page="<%=body%>" flush="true"></jsp:include>
				</td></tr>

				<tr><td width="100%" align="center">
					<jsp:include page="<%=footer%>" flush="true"></jsp:include>
				</td></tr>
			</table>
			
			<div id="blanket" style="height: 580;width: 1024;display: none;"></div>
			<div id="userIdDiv" style="display: none; height: 110px;width: 310px;position:relative; top: 40%;left: 30%;z-index: 9100;background-color: #E0EBEB;">
				<div align="right">
					<img class="button" tabindex="1" src="/HISClinical/hisglobal/images/stop.png" style="cursor: pointer; " onkeypress="cancel(event)" onclick="cancel(event)">
				</div>	
				<table width="100%">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									User Id
								</font>
							</div>
						</td>
						<td width="50%" class="tdfonthead">
							<div align="left">
								<select name="uid" id="userName" class="textbox">
									<option value='-1'>Select Employee</option>
									<%=session.getAttribute(DynamicDeskConfig.ESSENTIAL_BO_LOGIN_USER_LIST) %>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Password
								</font>
							</div>
						</td>
						<td width="50%" class="tdfonthead">
							<div align="left">
								<input type="password" name="pwd" id="password"	maxlength="20" tabindex="1" onkeypress="finalSubmit(event)" >
							</div> 
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead"></td>
						<td width="50%" class="tdfonthead">
							<div align="left">
								<img class="button" tabindex="1" src="/HIS/hisglobal/images/GoNew.png" style="cursor: pointer; position: absolute;" onkeypress="finalSubmit(event)" onclick="finalSubmit(event)">		
							</div> 
						</td>
					</tr>
				</table>
				<br>
				<div align="left" style="color: red;font-weight: bold;">
					<%=msg%>
				</div>
			</div>
				
			<his:status />
			
			<html:hidden name="NursingDeskFB" property="mode" />
			<html:hidden name="NursingDeskFB" property="deskMenuId" />
			<html:hidden name="NursingDeskFB" property="patCrNo" />
			<html:hidden name="NursingDeskFB" property="crNoSelected" />
			
			<html:hidden name="NursingDeskFB" property="enableBlanket" />			
			<input type="hidden" name="transactionMode" value="<%=request.getParameter("transactionMode")%>">
			<input type="hidden" name="menuMode" value="<%=request.getParameter("menuMode")%>">
			<input type="hidden" name="menuId" value="<%=request.getParameter("menuId")%>">
			<html:hidden name="NursingDeskFB" property="roleId" />
		</html:form>
	</body>
</html>