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

<%@page import="registration.RegistrationConfig"%>
<%@page import="opd.OpdConfig"%>
<%@page import="java.util.List"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function showLegends()
{
	document.getElementById("divLegends").style.display="block"; 
}

function showLegendsNone()
{
	document.getElementById("divLegends").style.display="none";
}

// Call Function onload 
window.onload=function()
{
	if(callThisOnload)
	{
		callThisOnload();
	}
}
</script>

<his:statusTransactionInProcess>
	<bean:define id="idPageChoice" name="OpdPatientDeskFB" property="pageChoice" type="java.lang.String"></bean:define>
	<%
		List lstPatients = null;
		List lstPatientsWaiting = (List) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
		List lstPatientsAttended = (List) session.getAttribute(OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);
		if(idPageChoice.equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
			lstPatients = lstPatientsWaiting;
		else if(idPageChoice.equalsIgnoreCase(OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
			lstPatients = lstPatientsAttended;
		
		// Show Attended Option only if Attended Present
		if(lstPatientsAttended!=null && lstPatientsAttended.size()>0)
		{
	%>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="100%" class="tdfonthead">
					<div align="right">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">						
							<html:radio name="OpdPatientDeskFB" property="pageChoice" tabindex="1" value="<%=OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING%>" onchange="submitOnChangePageChoice('NEW')" />
							<b><bean:message key="waiting"/></b>
							&nbsp;
							<html:radio name="OpdPatientDeskFB" property="pageChoice" tabindex="1" value="<%=OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED%>" onchange="submitOnChangePageChoice('NEW')" />
							<b><bean:message key="attended"/></b>
						</font>	
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>		
	<%
		}
		else
		{
			lstPatients = lstPatientsWaiting;
			idPageChoice = OpdConfig.OPD_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING;
	%>
	<html:hidden name="OpdPatientDeskFB" property="pageChoice" />	
	<%
		}
		String showWaiting = "none";
		String showAttended = "none";
		if(lstPatients==lstPatientsWaiting)	showWaiting = "block";
		else if(lstPatients==lstPatientsAttended)	showAttended = "block";
	%>
	
	<his:ContentTag>
	
	<div id="divIDWaiting" style="display: <%=showWaiting%>">
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<B>
								<bean:message key="select"/>
							</B>
						</font>	
					</div>
				</td>

				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="qNo"/></b>
						</font>
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('QUEUENO','0');" onClick="getOrderBy('QUEUENO','0');">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('QUEUENO','1');" onClick="getOrderBy('QUEUENO','1');">
					</div>
				</td>
				<td width="19%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="crNo"/></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('CRNO','0');" onClick="getOrderBy('CRNO','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('CRNO','1');" onClick="getOrderBy('CRNO','1');">
						</font>
					</div>
				</td>
				<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="name" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('NAME','0');" onClick="getOrderBy('NAME','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('NAME','1');" onClick="getOrderBy('NAME','1');">
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" nowrap style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="gender/age" /></b>
						</font>
					</div>
				</td>
				<td width="19%" class="tdfonthead" nowrap align="right" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="primCat" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('PATIENTCATEGORY','0');" onClick="getOrderBy('PATIENTCATEGORY','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('PATIENTCATEGORY','1');" onClick="getOrderBy('PATIENTCATEGORY','1');">
						</font>
					</div>
				</td>
			</tr>
			<logic:empty name="<%=OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING%>">
				<tr>
					<td width="100%" class="tdfont" nowrap valign="top" colspan="6">
						<div align="left">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>No Waiting Patient Found</b>
							</font>
						</div>		
					</td>
				</tr>
			</logic:empty>
			
			<logic:notEmpty name="<%=OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING%>">
			<logic:iterate id="patientList" name="<%=OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING%>" type="hisglobal.vo.PatientDetailVO">
				<%
					String color="",visitColor=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR;
					boolean isConfirmed = false;
					if(patientList.getIsConfirmed().equals(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED))
					{
						color = OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_STAMPED_COLOR;
						isConfirmed=false;
					}
					else if(patientList.getIsConfirmed().equals(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED))
					{
						color = OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_CONFIRMED_COLOR;
						isConfirmed=true;
					}
					if(patientList.getEpisodeVisitNo().trim().equals("1"))
						visitColor = OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR;
				%>
				<tr>
					<td width="5%" class="tdfont" nowrap valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="<%=visitColor%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<input type="hidden" name="<%=patientList.getPatCrNo()%>" value="<%=isConfirmed%>"/>
								<html:radio name="OpdPatientDeskFB" property="patCrNo" value='<%=patientList.getPatCrNo()%>' onclick="checkForOnSelectDeskListItem(this);" onkeypress="checkForOnSelectDeskListItem(this);" />
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="<%=visitColor%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patientList" property="queNo" />
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="<%=visitColor%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patientList" property="patCrNo" />
							</font>
						</div>
					</td>
					<td width="20%" class="tdfont" nowrap valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="<%=visitColor%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patientList" property="patFirstName" />
								<bean:write name="patientList" property="patMiddleName" />
								<bean:write name="patientList" property="patLastName" />
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="<%=visitColor%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patientList" property="patGenderAge" />
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="<%=visitColor%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patientList" property="patPrimaryCat" />
							</font>
						</div>
					</td>
				</tr>
			</logic:iterate>
			</logic:notEmpty>
		</table>
	</div>
	
	<div id="divIDAttendeed" style="display: <%=showAttended%>">
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<B>
								<bean:message key="select"/>
							</B>
						</font>	
					</div>
				</td>

				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="qNo"/></b>
						</font>
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('QUEUENO','0');" onClick="getOrderBy('QUEUENO','0');">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('QUEUENO','1');" onClick="getOrderBy('QUEUENO','1');">
					</div>
				</td>
				<td width="19%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="crNo"/></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('CRNO','0');" onClick="getOrderBy('CRNO','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('CRNO','1');" onClick="getOrderBy('CRNO','1');">
						</font>
					</div>
				</td>
				<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="name" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('NAME','0');" onClick="getOrderBy('NAME','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('NAME','1');" onClick="getOrderBy('NAME','1');">
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" nowrap style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="gender/age" /></b>
						</font>
					</div>
				</td>
				<td width="19%" class="tdfonthead" nowrap align="right" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="primCat" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('PATIENTCATEGORY','0');" onClick="getOrderBy('PATIENTCATEGORY','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('PATIENTCATEGORY','1');" onClick="getOrderBy('PATIENTCATEGORY','1');">
						</font>
					</div>
				</td>
			</tr>

			<logic:notEmpty name="<%=OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED%>">
			<logic:iterate id="patientList" name="<%=OpdConfig.OPD_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED%>" type="hisglobal.vo.PatientDetailVO">
				<%
					String color="",visitColor=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR;
					boolean isConfirmed = false;
					if(patientList.getIsConfirmed().equals(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED))
					{
						color = OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_STAMPED_COLOR;
						isConfirmed=false;
					}
					else if(patientList.getIsConfirmed().equals(RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED))
					{
						color = OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_CONFIRMED_COLOR;
						isConfirmed=true;
					}
					if(patientList.getEpisodeVisitNo().trim().equals("1"))
						visitColor = OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR;
				%>
				<tr>
					<td width="5%" class="tdfont" nowrap valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="<%=visitColor%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<input type="hidden" name="<%=patientList.getPatCrNo()%>" value="<%=isConfirmed%>"/>
								<html:radio name="OpdPatientDeskFB" property="patCrNo" value='<%=patientList.getPatCrNo()%>' onclick="checkForOnSelectDeskListItem(this);" onkeypress="checkForOnSelectDeskListItem(this);" />
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="<%=visitColor%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patientList" property="queNo" />
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="<%=visitColor%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patientList" property="patCrNo" />
							</font>
						</div>
					</td>
					<td width="20%" class="tdfont" nowrap valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="<%=visitColor%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patientList" property="patFirstName" />
								<bean:write name="patientList" property="patMiddleName" />
								<bean:write name="patientList" property="patLastName" />
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="<%=visitColor%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patientList" property="patGenderAge" />
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="<%=visitColor%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="patientList" property="patPrimaryCat" />
							</font>
						</div>
					</td>
				</tr>
			</logic:iterate>
			</logic:notEmpty>
		</table>
	</div>
	</his:ContentTag>

<his:SubTitleTag>
	<his:name>
		<bean:message key="legends"/>
	</his:name>
	
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td width="70%"></td>
			<td width="30%">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show</font>
					<img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' style="cursor: pointer;" onclick="showLegends();">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide</font>
					<img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' style="cursor: pointer;" onclick="showLegendsNone();">
				</div>
			</td>
		</tr>
	</table>
</his:SubTitleTag>

	
<div id="divLegends" style="display:block;">
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="10%">
					<font color="<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR_NAME%>: 
					</font>
				</td>
				<td width="90%">
					<div align="left">
						<font color="<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							First Visit
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="10%">
					<font color="<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR_NAME%>:
					</font>
				</td>
				<td width="90%">
					<div align="left">
						<font color="<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							Old Visit
						</font>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
</div>

</his:statusTransactionInProcess>

<html:hidden name="OpdPatientDeskFB" property="selectedPatCrNo" />

<input type="hidden" name="hmode" value="">
<input type="hidden" name="orderBy" value="">
