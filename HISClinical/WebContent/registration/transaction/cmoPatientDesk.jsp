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

<%@page import="registration.controller.fb.CMOPatientDeskFB"%>
<%@page import="hisglobal.vo.DailyPatientVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>

<his:javascript src="/opd/opdJs/opd.js" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	submitToDesk('NEW','PAGINATION');
}
</script>

<his:statusTransactionInProcess>
	<%
		PaginationFB fbPage= new PaginationFB();
		pageContext.setAttribute("fbPagination",fbPage);
		fbPage.setCurrentPage(((CMOPatientDeskFB)request.getAttribute("CMOPatientDeskFB")).getCurrentPage());
		fbPage.setObjArrName(DynamicDeskConfig.DESK_PATIENT_LIST);
		fbPage.setAppendInTitle("Patients");
		fbPage.setMaxRecords(15);
	%>
	<his:PaginationTag name="fbPagination"></his:PaginationTag>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
					<div align="center">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','0');" onclick="getOrderBy('COLORCODE','0');">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" onkeypress="if(event.keyCode==13)getOrderBy('COLORCODE','1');" onclick="getOrderBy('COLORCODE','1');">
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
			<%
				PatientDetailVO patList[] = (PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
				int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();

				for(int i= startIndex;i<=endIndex ; i++)					
				{
					DailyPatientVO patientList = patList[i];
					String color=patientList.getColorCode();
			%>
				<tr bgcolor="<%=color%>">
					<td width="5%" class="tdfont" nowrap="nowrap" valign="top" style="background-color: <%=color%>">						
						<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="CMOPatientDeskFB" property="patCrNo" value='<%=patientList.getPatCrNo()%>' onclick="checkForOnSelectDeskListItem(this);" onkeypress="checkForOnSelectDeskListItem(this);" />
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
			<%
				}
			%>
		</table>
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

<html:hidden name="CMOPatientDeskFB" property="selectedPatCrNo" />
<input type="hidden" name="hmode" value="">
<input type="hidden" name="orderBy" value="">
<html:hidden name="CMOPatientDeskFB" property="currentPage" />