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

<%@page import="inpatient.transaction.controller.fb.NursingStationPatListFB"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>

<script type="text/javascript">
function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	submitToDesk('NEW','PAGINATION');
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:statusTransactionInProcess>
	<%
		PaginationFB fbPage= new PaginationFB();
		pageContext.setAttribute("fbPagination",fbPage);
		fbPage.setCurrentPage(((NursingStationPatListFB)request.getAttribute("NursingStationPatListFB")).getCurrentPage());
		fbPage.setObjArrName(DynamicDeskConfig.DESK_PATIENT_LIST);
		fbPage.setAppendInTitle("Patients");
		fbPage.setMaxRecords(10);
	%>

	<his:PaginationTag name="fbPagination"></his:PaginationTag>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1" border="0">
			<tr>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<B>
							<bean:message key="select"/>
						</B>
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							<bean:message key="admNo"/>
						</b>
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" title="Ascending Order" onkeypress="if(event.keyCode==13)getOrderBy('ADMNO','0');" onClick="getOrderBy('ADMNO','0');">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" title="Descending Order" onkeypress="if(event.keyCode==13)getOrderBy('ADMNO','1');" onClick="getOrderBy('ADMNO','1');">	
					</div>
				</td>
				<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							<bean:message key="crNo"/>
						</b>
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" title="Ascending Order" onkeypress="if(event.keyCode==13)getOrderBy('CRNO','0');" onClick="getOrderBy('CRNO','0');">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" title="Descending Order" onkeypress="if(event.keyCode==13)getOrderBy('CRNO','1');" onClick="getOrderBy('CRNO','1');">	
					</div>
				</td>
				<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							<bean:message key="name"/>
						</b>
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" title="Ascending Order" onkeypress="if(event.keyCode==13)getOrderBy('PATNAME','0');" onClick="getOrderBy('PATNAME','0');">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" title="Descending Order" onkeypress="if(event.keyCode==13)getOrderBy('PATNAME','1');" onClick="getOrderBy('PATNAME','1');">	
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							<bean:message key="gender/age"/>
						</b>	
					</div>
				</td>
				<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							<bean:message key="admDate"/>
						</b>
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" title="Ascending Order" onkeypress="if(event.keyCode==13)getOrderBy('ADMDATE','0');" onClick="getOrderBy('ADMDATE','0');">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" title="Descending Order" onkeypress="if(event.keyCode==13)getOrderBy('ADMDATE','1');" onClick="getOrderBy('ADMDATE','1');">	
					</div>
				</td>
				<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							<bean:message key="dept/unit"/>
						</b>	
					</div>
				</td>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							<bean:message key="bedNo"/>
						</b>	
					</div>
				</td>
			</tr>
		<%
			PatientDetailVO patList[] = (PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
			int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();

			for(int i= startIndex;i<=endIndex ; i++)					
			{
				PatientDetailVO admittedPatList = patList[i];	
			
		%>
					<tr >
						<td width="5%" class="tdfont">
							<div align="center">
								<html:radio name="NursingStationPatListFB" property="patCrNo" value="<%=admittedPatList.getPatCrNo() %>" onclick="checkForOnSelectDeskListItem(this);" onkeypress="checkForOnSelectDeskListItem(this);"></html:radio>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
							 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getPatAdmNo() %>
							 	</font>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
							 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getPatCrNo() %>
							 	</font>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
							 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getPatName() %>
							 	</font>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getPatGender() %>/
							 		<%=admittedPatList.getPatAge() %>
							 	</font>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
							 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getAdmDateTime() %>
							 	</font>
							</div>
						</td>
						<td width="20%" class="tdfont">
							<div align="center">
							 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getDepartmentUnitName() %>
							 	</font>
							</div>
						</td>
						<td width="5%" class="tdfont">
							<div align="center">
								 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getBedName() %>
							 	</font>
							</div>
						</td>
						
					</tr>
					
				<%} %>
		</table>
	</his:ContentTag>		
	<html:hidden name="NursingStationPatListFB" property="currentPage"/>
	<html:hidden name="NursingStationPatListFB" property="hmode" />
	<html:hidden name="NursingStationPatListFB" property="orderBy" />
</his:statusTransactionInProcess>

