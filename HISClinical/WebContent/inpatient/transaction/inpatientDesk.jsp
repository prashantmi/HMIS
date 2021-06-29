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

<%@page import="inpatient.transaction.controller.fb.InpatientDeskFB"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>

<%@page import="inpatient.InpatientConfig"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	submitToDesk('NEW','PAGINATION');
}

function onChangeSearchBy()
{
	document.getElementsByName("strSearchBy")[0].value = "";
	/*var obj = document.getElementsByName("strSearchBy")[0];
	if(document.getElementsByName("strSearchByType")[0].value == 1 || document.getElementsByName("strSearchByType")[0].value == 3)
		obj.maxLength = 15;
	else
		obj.maxLength = 100;
	obj.value = "";*/
}

function validateSearchString(evnt,obj)
{
	
	if(document.getElementsByName("strSearchByType")[0].value == 1 || document.getElementsByName("strSearchByType")[0].value == 3)
		return validatePositiveIntegerOnly(obj,evnt);
	else
		return validateAlphabetsOnly(evnt,obj);
}

function validateSearchBy()
{
	/*if(document.getElementsByName("strSearchString")[0].value == "")
	{
		if(document.getElementsByName("strSearchBy")[0].value == 1)
			alert("Please Enter CR No!");
		else
			alert("Please Enter Name!");
		document.getElementsByName("strSearchString")[0].focus();
		return false;
	}*/
	
	document.getElementsByName("mode")[0].value = "NEW";
	return true;
}

</script>

<his:statusTransactionInProcess>
	<%
		PaginationFB fbPage= new PaginationFB();
		pageContext.setAttribute("fbPagination",fbPage);
		fbPage.setCurrentPage(((InpatientDeskFB)request.getAttribute("InpatientDeskFB")).getCurrentPage());
		fbPage.setObjArrName(InpatientConfig.DESK_PATIENT_SEARCHED_LIST);
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
			PatientDetailVO patList[] = (PatientDetailVO[])session.getAttribute(InpatientConfig.DESK_PATIENT_SEARCHED_LIST);
			if(patList!=null && patList.length>0)
			{
				int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
				int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
				
				for(int i= startIndex;i<=endIndex ; i++)					
				{
					PatientDetailVO admittedPatList = patList[i];
					((InpatientDeskFB)request.getAttribute("InpatientDeskFB")).setPatCrNo(admittedPatList.getPatCrNo());
					
					
			
		%>
				<%
					String bgColor="";	
					if(InpatientConfig.IPD_NURSING_DESK_MLC_COLOR_CODIFICATION.equals(InpatientConfig.MLC_COLOR_CODIFICATION_YES) 
						&& admittedPatList.getIsMLC()!=null && admittedPatList.getIsMLC().equals("1"))
						bgColor="background-color:"+InpatientConfig.IPD_DESK_MLC_COLOR_CODIFICATION;
				%>
					<tr>
						<td width="5%" class="tdfont" style="<%=bgColor%>">
							<div align="center">
								<html:radio name="InpatientDeskFB" property="patCrNo" value="<%=admittedPatList.getPatCrNo() %>" onclick="checkForOnSelectDeskListItem(this);" onkeypress="checkForOnSelectDeskListItem(this);"></html:radio>
							</div>
						</td>
						<td width="10%" class="tdfont" style="<%=bgColor%>">
							<div align="center">
							 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getPatAdmNo() %>
							 	</font>
							</div>
						</td>
						<td width="15%" class="tdfont" style="<%=bgColor%>">
							<div align="center">
							 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getPatCrNo() %>
							 	</font>
							</div>
						</td>
						<td width="20%" class="tdfont" style="<%=bgColor%>">
							<div align="center">
							 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getPatName() %>
							 	</font>
							</div>
						</td>
						<td width="10%" class="tdfont" style="<%=bgColor%>">
							<div align="center">
								 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getPatGender() %>/
							 		<%=admittedPatList.getPatAge() %>
							 	</font>
							</div>
						</td>
						<td width="15%" class="tdfont" style="<%=bgColor%>">
							<div align="center">
							 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getAdmDateTime() %>
							 	</font>
							</div>
						</td>
						<td width="20%" class="tdfont" style="<%=bgColor%>">
							<div align="center">
							 	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getDepartmentUnitName() %>
							 	</font>
							</div>
						</td>
						<td width="5%" class="tdfont" style="<%=bgColor%>">
							<div align="center">
								 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=admittedPatList.getBedName() %>
							 	</font>
							</div>
						</td>
						
					</tr>
					
				<%}	} %>
		</table>
	</his:ContentTag>

	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1" border="0">
			<tr>
				<td width="100%" class="tdfonthead" valign="middle">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="search"/> <bean:message key="by"/>
							&nbsp; 
							<html:select name="InpatientDeskFB" property="strSearchByType" tabindex="1" onchange="onChangeSearchBy()">
								<html:option value="1">Admission No.</html:option>
								<html:option value="2">Patient Name</html:option>
								<html:option value="3">CR No.</html:option>
							</html:select>
							&nbsp;
							<html:text name="InpatientDeskFB" property="strSearchBy" tabindex="1" maxlength="100" size="25" 
								onkeypress="return validateSearchString(event,this);"></html:text>
							&nbsp;
							<img class="button" src='<his:path src="/hisglobal/images/btn-search.png"/>' tabindex="1" style="cursor: pointer"  
								title="Search Patients" onclick="submitFormOnValidate(validateSearchBy(),'SEARCHPATLIST')"
								onkeypress="if(event.keyCode==13) submitFormOnValidate(validateSearchBy(),'SEARCHPATLIST')">
						</font>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	<% if(InpatientConfig.IPD_NURSING_DESK_MLC_COLOR_CODIFICATION.equals(InpatientConfig.MLC_COLOR_CODIFICATION_YES)) {  %>
	<his:ContentTag>		
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="20%" class="tdfont" nowrap="nowrap" >
					<label style="background-color: <%=InpatientConfig.IPD_DESK_MLC_COLOR_CODIFICATION%>">&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>&nbsp;MLC Patients</b>
					</font>						
				</td>
			</tr>
		</table>
	</his:ContentTag>
	<% } %>
	<html:hidden name="InpatientDeskFB" property="currentPage"/>
	<html:hidden name="InpatientDeskFB" property="hmode" />
	<html:hidden name="InpatientDeskFB" property="orderBy" />
	<html:hidden name="InpatientDeskFB" property="patCrNo" />
</his:statusTransactionInProcess>

