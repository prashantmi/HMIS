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

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.List"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:statusTransactionInProcess>
	<bean:define id="idPageChoice" name="CsultyPatientDeskFB" property="pageChoice" type="java.lang.String"></bean:define>
	<%
		List lstPatients = null;
		List lstPatientsWaiting = (List) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING);
		List lstPatientsAttended = (List) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED);
		List lstPatientsInTriage = (List) session.getAttribute(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE);

		if(idPageChoice.equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING))
			lstPatients = lstPatientsWaiting;
		else if(idPageChoice.equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED))
			lstPatients = lstPatientsAttended;
		else if(idPageChoice.equalsIgnoreCase(OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE))
			lstPatients = lstPatientsInTriage;
		
		// Show Attended or InTriage Option only if either Attended/InTriage Present
		if( (lstPatientsAttended!=null && lstPatientsAttended.size()>0) || (lstPatientsInTriage!=null && lstPatientsInTriage.size()>0))
		{
	%>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="100%" class="tdfonthead">
					<div align="right">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<html:radio name="CsultyPatientDeskFB" property="pageChoice" tabindex="1" value="<%=OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING%>" onchange="submitOnChangePageChoice('NEW')" />
							<b><bean:message key="waiting"/></b>
							<%	if(lstPatientsAttended!=null && lstPatientsAttended.size()>0)	{	%>						
								&nbsp;
								<html:radio name="CsultyPatientDeskFB" property="pageChoice" tabindex="1" value="<%=OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_ATTENDED%>" onchange="submitOnChangePageChoice('NEW')" />
								<b><bean:message key="attended"/></b>
							<%	}	if(lstPatientsInTriage!=null && lstPatientsInTriage.size()>0)	{	%>
								&nbsp;
								<html:radio name="CsultyPatientDeskFB" property="pageChoice" tabindex="1" value="<%=OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_INTRIAGE%>" onchange="submitOnChangePageChoice('NEW')" />
								<b><bean:message key="intriage"/></b>
							<%	} %>
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
			idPageChoice = OpdConfig.CASUALTY_DESK_PATIENT_LIST_PAGE_CHOICE_WAITING;
	%>
	<html:hidden name="CsultyPatientDeskFB" property="pageChoice" />	
	<%
		}
		String showWaiting = "none";
		String showAttended = "none";
		String showInTriage = "none";
		if(lstPatients==lstPatientsWaiting)	showWaiting = "block";
		else if(lstPatients==lstPatientsAttended)	showAttended = "block";
		else if(lstPatients==lstPatientsInTriage)	showInTriage = "block";
	%>
	
	<his:ContentTag>

	<div id="divIDWaiting" style="display: <%=showWaiting%>">
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','0');" onclick="getOrderBy('COLORCODE','0');">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','1');" onclick="getOrderBy('COLORCODE','1');">
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="qNo" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','0');" onclick="getOrderBy('QUEUENO','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','1');" onclick="getOrderBy('QUEUENO','1');">
						</font>
					</div>
				</td>
				<td width="19%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="crNo" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('CRNO','0');" onclick="getOrderBy('CRNO','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('CRNO','1');" onclick="getOrderBy('CRNO','1');">
						</font>
					</div>
				</td>
				<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="name" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('NAME','0');" onclick="getOrderBy('NAME','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('NAME','1');" onclick="getOrderBy('NAME','1');">
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="gender/age" /></b>
						</font>
					</div>
				</td>
				<td width="19%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" align="right">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="primCat" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('PATIENTCATEGORY','0');" onclick="getOrderBy('PATIENTCATEGORY','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('PATIENTCATEGORY','1');" onclick="getOrderBy('PATIENTCATEGORY','1');">
						</font>
					</div>
				</td>
			</tr>

			<logic:empty name="<%=OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING%>">
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

			<logic:notEmpty name="<%=OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING%>">
			<logic:iterate id="patientList" name="<%=OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_WAITING%>" type="hisglobal.vo.PatientDetailVO">
				<%	String color=patientList.getColorCode();	%>
				<tr bgcolor="<%=color%>">
					<td width="5%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">						
						<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="CsultyPatientDeskFB" property="patCrNo" value='<%=patientList.getPatCrNo()%>' onclick="checkForOnSelectDeskListItem(this);" onkeypress="checkForOnSelectDeskListItem(this);" />
									<input type="hidden" name="<%=patientList.getPatCrNo()%>" value="<%=false%>"/>
								</font>
						</div>						
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<% if(patientList.getQueNo()!=null) { %> <%=patientList.getQueNo()%> <% } %>
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatCrNo()%>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatFirstName()%>
								<% if(patientList.getPatMiddleName()!=null) { %> <%=patientList.getPatMiddleName()%> <% } %>
								<% if(patientList.getPatLastName()!=null) { %> <%=patientList.getPatLastName()%> <% } %>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatGenderAge()%>
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatPrimaryCat()%>
							</font>
						</div>
					</td>
				</tr>
			</logic:iterate>
			</logic:notEmpty>
		</table>
	</div>

	<div id="divIDAttended" style="display: <%=showAttended%>">
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','0');" onclick="getOrderBy('COLORCODE','0');">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','1');" onclick="getOrderBy('COLORCODE','1');">
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="qNo" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','0');" onclick="getOrderBy('QUEUENO','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','1');" onclick="getOrderBy('QUEUENO','1');">
						</font>
					</div>
				</td>
				<td width="19%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="crNo" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('CRNO','0');" onclick="getOrderBy('CRNO','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('CRNO','1');" onclick="getOrderBy('CRNO','1');">
						</font>
					</div>
				</td>
				<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="name" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('NAME','0');" onclick="getOrderBy('NAME','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('NAME','1');" onclick="getOrderBy('NAME','1');">
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="gender/age" /></b>
						</font>
					</div>
				</td>
				<td width="19%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" align="right">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="primCat" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('PATIENTCATEGORY','0');" onclick="getOrderBy('PATIENTCATEGORY','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('PATIENTCATEGORY','1');" onclick="getOrderBy('PATIENTCATEGORY','1');">
						</font>
					</div>
				</td>
			</tr>

			<logic:empty name="<%=OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED%>">
				<tr>
					<td width="100%" class="tdfont" nowrap valign="top" colspan="6">
						<div align="left">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>No Attended Patient Found</b>
							</font>
						</div>		
					</td>
				</tr>
			</logic:empty>

			<logic:notEmpty name="<%=OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED%>">
			<logic:iterate id="patientList" name="<%=OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_ATTENDED%>" type="hisglobal.vo.PatientDetailVO">
				<%	String color=patientList.getColorCode();	%>
				<tr bgcolor="<%=color%>">
					<td width="5%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">						
						<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="CsultyPatientDeskFB" property="patCrNo" value='<%=patientList.getPatCrNo()%>' onclick="checkForOnSelectDeskListItem(this);" onkeypress="checkForOnSelectDeskListItem(this);" />
									<input type="hidden" name="<%=patientList.getPatCrNo()%>" value="<%=false%>"/>
								</font>
						</div>						
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<% if(patientList.getQueNo()!=null) { %> <%=patientList.getQueNo()%> <% } %>
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatCrNo()%>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatFirstName()%>
								<% if(patientList.getPatMiddleName()!=null) { %> <%=patientList.getPatMiddleName()%> <% } %>
								<% if(patientList.getPatLastName()!=null) { %> <%=patientList.getPatLastName()%> <% } %>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatGenderAge()%>
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatPrimaryCat()%>
							</font>
						</div>
					</td>
				</tr>
			</logic:iterate>
			</logic:notEmpty>
		</table>
	</div>

	<div id="divIDInTriage" style="display: <%=showInTriage%>">
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','0');" onclick="getOrderBy('COLORCODE','0');">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','1');" onclick="getOrderBy('COLORCODE','1');">
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="qNo" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','0');" onclick="getOrderBy('QUEUENO','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','1');" onclick="getOrderBy('QUEUENO','1');">
						</font>
					</div>
				</td>
				<td width="19%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="crNo" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('CRNO','0');" onclick="getOrderBy('CRNO','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('CRNO','1');" onclick="getOrderBy('CRNO','1');">
						</font>
					</div>
				</td>
				<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="name" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('NAME','0');" onclick="getOrderBy('NAME','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('NAME','1');" onclick="getOrderBy('NAME','1');">
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap>
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="gender/age" /></b>
						</font>
					</div>
				</td>
				<td width="19%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" align="right">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="primCat" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('PATIENTCATEGORY','0');" onclick="getOrderBy('PATIENTCATEGORY','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('PATIENTCATEGORY','1');" onclick="getOrderBy('PATIENTCATEGORY','1');">
						</font>
					</div>
				</td>
				<td width="19%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" align="right">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="triageDuration" /></b>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('TRIAGEDUR','0');" onclick="getOrderBy('TRIAGEDUR','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('TRIAGEDUR','1');" onclick="getOrderBy('TRIAGEDUR','1');">
						</font>
					</div>
				</td>
			</tr>

			<logic:empty name="<%=OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE%>">
				<tr>
					<td width="100%" class="tdfont" nowrap valign="top" colspan="7">
						<div align="left">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>No Patient Found in Triage</b>
							</font>
						</div>		
					</td>
				</tr>
			</logic:empty>

			<logic:notEmpty name="<%=OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE%>">
			<logic:iterate id="patientList" name="<%=OpdConfig.CASUALTY_DESK_PATIENT_LIST_PATIENTDTL_VO_INTRIAGE%>" type="hisglobal.vo.PatientDetailVO">
				<%	String color=patientList.getColorCode();	%>
				<tr bgcolor="<%=color%>">
					<td width="5%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">						
						<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="CsultyPatientDeskFB" property="patCrNo" value='<%=patientList.getPatCrNo()%>' onclick="checkForOnSelectDeskListItem(this);" onkeypress="checkForOnSelectDeskListItem(this);" />
									<input type="hidden" name="<%=patientList.getPatCrNo()%>" value="<%=false%>"/>
								</font>
						</div>						
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<% if(patientList.getQueNo()!=null) { %> <%=patientList.getQueNo()%> <% } %>
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatCrNo()%>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatFirstName()%>
								<% if(patientList.getPatMiddleName()!=null) { %> <%=patientList.getPatMiddleName()%> <% } %>
								<% if(patientList.getPatLastName()!=null) { %> <%=patientList.getPatLastName()%> <% } %>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatGenderAge()%>
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getPatPrimaryCat()%>
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">
						<div align="center">
						<%	if(patientList.getTriageDurationHours()!=null)
							{
								int duration = Integer.parseInt(patientList.getTriageDurationHours());
								if(duration<=48)
								{
						%>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patientList.getTriageDuration()%>
							</font>
						<%		}
								else
								{
						%>
								<blink>
									<%	if(color.equalsIgnoreCase("#FF0000"))	{ %>
									<font color="#E0EBEB" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=patientList.getTriageDuration()%>
									</font>
									<%	}	else	{	%>
									<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=patientList.getTriageDuration()%>
									</font>
									<%	}	%>
								</blink>
						<%		}
							}
						%>
						</div>
					</td>
				</tr>
			</logic:iterate>
			</logic:notEmpty>
		</table>
	</div>
	</his:ContentTag>
	
	<his:ContentTag>		
		<table width="100%" cellpadding="0" cellspacing="1">
			<%	int c=0;	%>
			<logic:iterate name="<%=OpdConfig.OPD_PATIENT_LIST_COLOR_CODIFICATION_INFO%>" id="colorCode" type="hisglobal.utility.Entry">
			<%				
				if(c==0)
				{				
			%>  
				<tr>
			<%	}	%>
					<td width="20%" class="tdfont" nowrap="nowrap" >
						<label style="background-color: <%=colorCode.getValue()%>">&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>&nbsp;<%=colorCode.getLabel()%></b>
						</font>						
					</td>
			<%				
				if(c==4)
				{				
			%>
				</tr>
			<%	
				}
				c++;
				if(c==5)	c=0;
			%>
			</logic:iterate>
			<%	if(c!=0)	{	%>
				</tr>
			<%	}	%>
		</table>
	</his:ContentTag>
</his:statusTransactionInProcess>

<html:hidden name="CsultyPatientDeskFB" property="selectedPatCrNo" />

<input type="hidden" name="hmode" value="">
<input type="hidden" name="orderBy" value="">