<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function closeForm()
{
	self.close();
}

function viewRemarks(obj)
{
	document.getElementsByName("showRemarks")[0].value=obj;
	document.getElementById("divViewRemarks").style.display="block"; 
	document.getElementById("divViewRevokeRemarks").style.display="none";
	
}

function viewRevokeRemarks(obj)
{
	document.getElementsByName("showRevokeRemarks")[0].value=obj;
	document.getElementById("divViewRevokeRemarks").style.display="block"; 
	document.getElementById("divViewRemarks").style.display="none";
	
}

</script>


<html:form action="/genericPatientAlert">
	<body>
		<his:TitleTag name="Patient All Previous Chronic Disease">
			
		</his:TitleTag>
		
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="12%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visitDate"/>
							</font>
						</div>
					</td>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="chronicDisease"/>
							</font>
						</div>
					</td>
					<td width="14%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="snomedCtId"/>
						</font>
					</div>				
				</td>
					<td width="15%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="duration"/>
								
							</font>
						</div>
					</td>
					<td width="13%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remark"/>
							</font>
						</div>
					</td>
					<td width="13%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="cancel"/> <bean:message key="date"/>
							</font>
						</div>
					</td>
					<td width="13%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="cancel"/> <bean:message key="remark"/>
							</font>
						</div>
					</td>
				</tr>
				<logic:iterate name="<%= OpdConfig.ARR_ALL_PATIENT_ALERT_VO%>" id="arrAllAlert" type="hisglobal.vo.PatientAlertsDetailVO">
					<tr>
						<td class="tdfont">
							<div align="center">
								<%= arrAllAlert.getEntryDate() %>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<%= arrAllAlert.getAlertName()  %>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<%= arrAllAlert.getPatAlertId()  %>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<%= arrAllAlert.getDurationDays() %>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<% if(arrAllAlert.getRemarks()!=null && !arrAllAlert.getRemarks().trim().equals("")){ %>
								<img class="button" src='<his:path src="/hisglobal/images/icon-vrf.png"/>' style='cursor:pointer' title="View Remark" 
								onclick="viewRemarks('<%=(arrAllAlert.getRemarks()==null)?"No Remark":arrAllAlert.getRemarks() %>')" 
								onkeypress="if(event.keyCode==13) viewRemarks('<%=(arrAllAlert.getRemarks()==null)?"No Remark":arrAllAlert.getRemarks() %>')">								
								<%} else {%>-<%} %>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<%= (arrAllAlert.getEffectiveTo()==null)?"-":arrAllAlert.getEffectiveTo()%>
							</div>
						</td>
						<td class="tdfont" width="20%" >
							<div align="center">
								<% if(arrAllAlert.getEffectiveTo()!=null){ %>
								<img class="button" src='<his:path src="/hisglobal/images/icon-vrf.png"/>' style='cursor:pointer' title="View Remark" 
								onclick="viewRevokeRemarks('<%=(arrAllAlert.getRevokeRemarks()==null)?"No Remark":arrAllAlert.getRevokeRemarks() %>')" 
								onkeypress="if(event.keyCode==13) viewRevokeRemarks('<%=(arrAllAlert.getRevokeRemarks()==null)?"No Remark":arrAllAlert.getRevokeRemarks() %>')">								
								<%} else {%>-<%} %>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>		
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
	</body>	
	
	
	<div id="divViewRemarks" style="display: none;">
       			<his:SubTitleTag name="Remark">
       			</his:SubTitleTag>
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="GenericPatientAlertFB" property="showRemarks" rows="5" cols="150" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>
	<div id="divViewRevokeRemarks" style="display: none;">
       			<his:SubTitleTag name="Revoke Remark">
       			</his:SubTitleTag>
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="GenericPatientAlertFB" property="showRevokeRemarks" rows="5" cols="150" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>		
<his:status/>
</html:form>