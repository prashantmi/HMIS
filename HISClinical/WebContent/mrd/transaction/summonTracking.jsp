<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<html>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>

<%@page import="mrd.MrdConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function noOfDays(a,b)
{
	valid=true;
	var day=0;
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	var bArray=b.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	day=(adate-bdate)/86400000;
	return day;
}

function showEmployee()
{
	//alert("date "+document.getElementsByName("fromDate")[0].value);
	if(document.getElementsByName("selectCriteria")[0].value==<%=MrdConfig.PARTICULAR_EMPLOYEE%>)
	{
		document.getElementsByName("employeeId")[0].disabled=false;
	}
	else
	{
		document.getElementsByName("employeeId")[0].value="-1";
		document.getElementsByName("employeeId")[0].disabled=true;
	}
	
	if(document.getElementsByName("selectCriteria")[0].value==<%=MrdConfig.SUMMON_TYPE%>)
	{
		document.getElementsByName("summonTypeId")[0].disabled=false;
	}
	else
	{
		document.getElementsByName("summonTypeId")[0].value="-1";
		document.getElementsByName("summonTypeId")[0].disabled=true;
	}
}

function clearForm()
{
	document.getElementsByName("selectCriteria")[0].value="-1";
	document.getElementsByName("fromDate")[0].value="";
	document.getElementsByName("toDate")[0].value="";
}

function validateForm(mode)
{
	if(document.getElementsByName("selectCriteria")[0].value=="-1")
	{
		alert("Please select Criteria");
		document.getElementsByName("selectCriteria")[0].focus();
		return false;
	}
	if(document.getElementsByName("selectCriteria")[0].value!=<%=MrdConfig.NEXT_HEARING%>)
	{
		if(document.getElementsByName("fromDate")[0].value=="")
		{
			alert("Please select from date");
			document.getElementsByName("fromDate")[0].focus();
			return false;
		}
		
		if(document.getElementsByName("toDate")[0].value=="")
		{
			alert("Please select to date");
			document.getElementsByName("toDate")[0].focus();
			return false;
		}
		
		var todateDay=noOfDays(document.getElementsByName("toDate")[0].value,document.getElementsByName("sysdate")[0].value)
		if(todateDay>0)
		{
			alert("To Date can not be greater than System Date");
			document.getElementsByName("toDate")[0].focus();
			return false;
		}
		
		var Day=noOfDays(document.getElementsByName("toDate")[0].value,document.getElementsByName("fromDate")[0].value)
		if(Day<0)
		{
			alert("From Date can not be greater than To Date");
			document.getElementsByName("toDate")[0].focus();
			return false;
		}
		
		if(document.getElementsByName("displayMode")[0].checked==false && document.getElementsByName("displayMode")[1].checked==false)
		{
			alert("Please select Display mode");
			document.getElementsByName("displayMode")[0].focus();
			return false;
		}
	}
	
	
	var noOfday=noOfDays(document.getElementsByName("toDate")[0].value,document.getElementsByName("fromDate")[0].value);
	if(noOfday<0)
	{
		alert("From date can not be greater than to date");
		document.getElementsByName("fromDate")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("selectCriteria")[0].value==<%=MrdConfig.PARTICULAR_EMPLOYEE%>)
	{
		if(document.getElementsByName("employeeId")[0].value=="-1")
		{
			alert("Please select Employee");
			document.getElementsByName("employeeId")[0].focus();
			return false;
		}
	}
	
	if(document.getElementsByName("selectCriteria")[0].value==<%=MrdConfig.SUMMON_TYPE%>)
	{
		if(document.getElementsByName("summonTypeId")[0].value=="-1")
		{
			alert("Please select Summon Type");
			document.getElementsByName("summonTypeId")[0].focus();
			return false;
		}
	}
	
	submitPage(mode);
}
 
</script>
<body onload="showEmployee()" >
		

<his:TransactionContainer>
		<html:form action="/summonTracking">
		<his:TitleTag name="Summon Tracking">
		</his:TitleTag>
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="15%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="selectCriteria"/>
			  			</font>
			  		</div>
		      	</td>
		      	<td width="25%" class="tdfont" width="15%"  >
					<div align="left" >
						<html:select name="SummonTrackingFB" property="selectCriteria" tabindex="1" onchange="showEmployee()"> 
							<html:option value="-1">Select Value</html:option>
							<html:option value="<%=MrdConfig.ALL_RECORD %>">All Record</html:option>
							<html:option value="<%=MrdConfig.ATTENDED_SUMMON %>">Attended Summon</html:option>
							<html:option value="<%=MrdConfig.NOT_ATTENDED %>">Not Attended Summon</html:option>
							<html:option value="<%=MrdConfig.POST_DETAIL %>">Post Detail</html:option>
							<html:option value="<%=MrdConfig.PARTICULAR_EMPLOYEE %>">Particular Employee</html:option>
							<html:option value="<%=MrdConfig.SUMMON_TYPE %>">Summon Type</html:option>
							
						</html:select>
					</div>
				</td>
				<td class="tdfonthead" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="employee"/>	
			  			</font>
			  		</div>
		      	</td>
		      	<td width="25%" class="tdfont" >
					<div align="left" >
						<html:select name="SummonTrackingFB" property="employeeId" tabindex="1" disabled="true"> 
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=MrdConfig.ALL_EMPLOYEE_LIST%>">
								<html:options collection="<%=MrdConfig.ALL_EMPLOYEE_LIST%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
					</div>
				</td>
			<tr>	
				<td class="tdfonthead" width="10%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="fromDate"/>
			  			</font>
			  		</div>
		      	</td>
		      	
		      	<td width="25%" class="tdfont" width="25%"  >
					<div align="left" >
						<bean:define id="fromDateId" name="SummonTrackingFB" property="fromDate"></bean:define>
						<%String fromDate=(String)fromDateId; %>
						<his:date name="fromDate" dateFormate="%d-%b-%Y" value="<%=fromDate %>"></his:date>
					</div>
				</td>
				<td class="tdfonthead" width="10%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="toDate"/>	
			  			</font>
			  		</div>
		      	</td>
		      	<td width="25%" class="tdfont" width="25%"  >
					<div align="left" >
						<bean:define id="toDateId" name="SummonTrackingFB" property="toDate"></bean:define>
						<%String toDate=(String)toDateId; %>
						<his:date name="toDate" dateFormate="%d-%b-%Y" value="<%=toDate %>"></his:date>
					</div>
				</td>
			</tr>
			<tr>
				
				<td class="tdfonthead" colspan="1" nowrap="nowrap">
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="diplayMode"/>	
			  			</font>
			  		</div>
		      	</td>
				<td width="25%" class="tdfont" >
					<div align="left" >
						Listing<html:radio property="displayMode" value="<%=MrdConfig.DISPLAY_MODE_IS_LISTING %>"></html:radio>
						Statistical<html:radio property="displayMode" value="<%=MrdConfig.DISPLAY_MODE_IS_STASTICAL %>"></html:radio>
					</div>
				</td>
				<td class="tdfonthead" nowrap="nowrap">
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<font color="#FF0000">*</font>
			 				<bean:message key="summonType"/>	
			  			</font>
			  		</div>
		      	</td>
		      	<td width="25%" class="tdfont" >
					<div align="left" >
						<html:select name="SummonTrackingFB" property="summonTypeId" tabindex="1" disabled="true"> 
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=MrdConfig.ALL_SUMMON_TYPE_LIST%>">
								<html:options collection="<%=MrdConfig.ALL_SUMMON_TYPE_LIST%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
					</div>
				</td>
					
			</tr>
		</table>
		</his:ContentTag>
		<his:ButtonToolBarTag>
			<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/btn-search.png"/>' alt="Search" title="Search" onclick="validateForm('SEARCH');" onkeypress="if(event.keyCode==13)validateForm('SEARCH');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('NEW');" onkeypress="if(event.keyCode==13) submitPage('NEW');">
		</his:ButtonToolBarTag>
		
		<logic:present name="<%=MrdConfig.ALL_SEARCH_SUMMON_DTL_VO_LIST %>">
		<logic:notEmpty name="<%=MrdConfig.ALL_SEARCH_SUMMON_DTL_VO_LIST %>">
		<logic:equal value="<%=MrdConfig.DISPLAY_MODE_IS_LISTING %>" property="displayMode" name="SummonTrackingFB">
		<his:SubTitleTag name="Summon Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td class="tdfonthead" width="10%" nowrap="nowrap">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<b><bean:message key="summonType"/></b>
				 					
				  			</font>
				  		</div>
		      		</td>
			      	<td class="tdfonthead" width="15%" nowrap="nowrap">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<b><bean:message key="patientName"/></b>
				 					
				  			</font>
				  		</div>
			      	</td>
			      	<td class="tdfonthead" width="15%" nowrap="nowrap">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<b><bean:message key="assigneeName"/></b>
				 			</font>
				  		</div>
			      	</td>
			      	<td class="tdfonthead" width="20%" nowrap="nowrap">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<B><bean:message key="receivingDate&Time"/></B>	
				  			</font>
				  		</div>
			      	</td>
			      	<td class="tdfonthead" width="15%" nowrap="nowrap">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<b><bean:message key="hearingDate&Time"/></b>
				 				
				  			</font>
				  		</div>
			      	</td>
			      	<td class="tdfonthead" width="15%" nowrap="nowrap">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<b><bean:message key="status"/></b>
				 			</font>
				  		</div>
			      	</td>	
				</tr>
			<logic:iterate id="summonDtlVO" indexId="i" name="<%=MrdConfig.ALL_SEARCH_SUMMON_DTL_VO_LIST %>" type="hisglobal.vo.SummonDetailVO">
				<tr>
					<td width="25%" class="tdfontheadExam" width="10%"  nowrap="nowrap">
						<div align="center" >
							<bean:write name="summonDtlVO" property="summonTypeDesc"/>
						</div>
					</td>
					<td width="25%" class="tdfontheadExam" width="15%"  nowrap="nowrap">
						<div align="center" >
							<bean:write name="summonDtlVO" property="patName"/>
						</div>
					</td>
					<td width="25%" class="tdfontheadExam" width="15%"  nowrap="nowrap">
						<div align="center" >
							<bean:write name="summonDtlVO" property="assignEmpName"/>
						</div>
					</td>
					<td width="25%" class="tdfontheadExam" width="15%"  nowrap="nowrap">
						<div align="center" >
							<bean:write name="summonDtlVO" property="summonReceiveDateTime"/>
						</div>
					</td>
					<td width="25%" class="tdfontheadExam" width="20%"  nowrap="nowrap">
						<div align="center" >
							<bean:write name="summonDtlVO" property="hearingDateTime"/>
						</div>
					</td>
					<td width="25%" class="tdfontheadExam" width="15%"  nowrap="nowrap">
						<div align="center" >
							<bean:write name="summonDtlVO" property="statusDesc"/>
						</div>
					</td>
				</tr>
			</logic:iterate>	
			</table>
		</his:ContentTag>	
		</logic:equal>		
		</logic:notEmpty>
		</logic:present>
		
		<logic:equal value="<%=MrdConfig.DISPLAY_MODE_IS_STASTICAL %>" name="SummonTrackingFB" property="displayMode">
			<his:SubTitleTag name="Summon Detail">
			</his:SubTitleTag>
			<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td class="tdfonthead" width="50%">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<B><bean:message key="summonStatus"/></B>	
				  			</font>
				  		</div>
			      	</td>
			      	<td class="tdfonthead" width="50%">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<B><bean:message key="noOfRecord"/></B>	
				  			</font>
				  		</div>
			      	</td>	
				</tr>
			</table>
			</his:ContentTag>
			<bean:define id="status" name="SummonTrackingFB" property="selectCriteria"></bean:define>
			<%
				if(status.equals(MrdConfig.ALL_RECORD) || status.equals(MrdConfig.PARTICULAR_EMPLOYEE) || status.equals(MrdConfig.SUMMON_TYPE))
				{
			%>
				<his:ContentTag>	
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				
				<tr>
					<td class="tdfontheadExam" width="50%">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="noOfReceivedSummon"/>	
				  			</font>
				  		</div>
			      	</td>
			      	<td  class="tdfontheadExam" width="50%">
						<div align="center" >
							<bean:define id="noOfReceivedSummon" name="SummonTrackingFB" property="noOfReceivedSummon"></bean:define>
							<%=noOfReceivedSummon %>
						</div>
					</td>	
				</tr>
				<tr>
					<td class="tdfontheadExam" width="50%">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="noOfAssignedSummon"/>
				  			</font>
				  		</div>
			      	</td>
			      	<td  class="tdfontheadExam" width="50%">
						<div align="center" >
							<bean:define id="noOfAssignedSummon" name="SummonTrackingFB" property="noOfAssignedSummon"></bean:define>
							<%=noOfAssignedSummon %>
						</div>
					</td>	
				</tr>
				<tr>
					<td class="tdfontheadExam" width="50%">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="noOfAttendedSummon"/>	
				  			</font>
				  		</div>
			      	</td>
			      	<td  class="tdfontheadExam" width="50%">
						<div align="center" >
							<bean:define id="noOfAttendedSummon" name="SummonTrackingFB" property="noOfAttendedSummon"></bean:define>
							<%=noOfAttendedSummon %>
						</div>
					</td>	
				</tr>
				<tr>
					<td class="tdfontheadExam" width="50%">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="noOfNotAttendedSummon"/>
				  			</font>
				  		</div>
			      	</td>
			      	<td  class="tdfontheadExam" width="50%">
						<div align="center" >
							<bean:define id="noOfNotAttendedSummon" name="SummonTrackingFB" property="noOfNotAttendedSummon"></bean:define>
							<%=noOfNotAttendedSummon %>
						</div>
					</td>	
				</tr>
				<tr>
					<td class="tdfontheadExam" width="50%">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<bean:message key="noOfAcceptedSummon"/>	
				  			</font>
				  		</div>
			      	</td>
			      	<td  class="tdfontheadExam" width="50%">
						<div align="center" >
							<bean:define id="noOfAcceptedSummon" name="SummonTrackingFB" property="noOfAcceptedSummon"></bean:define>
							<%=noOfAcceptedSummon %>
						</div>
					</td>	
				</tr>
				
				<tr>
					<td class="tdfontheadExam" width="50%">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<b>
				 					<bean:message key="toatlNoOfRecord"/>
				 				</b>	
				  			</font>
				  		</div>
			      	</td>
			      	<td  class="tdfontheadExam" width="50%">
			      	
						<div align="center" >
							<bean:define id="noOfRecord" name="SummonTrackingFB" property="noOfRecord"></bean:define>
							<%=noOfRecord %>
						</div>
					</td>	
				</tr>
				
			</table>
		</his:ContentTag>	
			
			<%		
				}
			%>
			<%
				if(status.equals(MrdConfig.ATTENDED_SUMMON))
				{
			%>	
			<his:ContentTag>	
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				
				<tr>
					<td class="tdfontheadExam" width="50%">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<b>
				 					<bean:message key="noOfAttendedSummon"/>
				 				</b>	
				  			</font>
				  		</div>
			      	</td>
			      	<td  class="tdfontheadExam" width="50%">
						<div align="center" >
							<bean:define id="noOfAttendedSummon" name="SummonTrackingFB" property="noOfAttendedSummon"></bean:define>
							<%=noOfAttendedSummon %>
						</div>
					</td>	
				</tr>
			</table>
		</his:ContentTag>		
			<%
				}
			%>
			<%
				if(status.equals(MrdConfig.NOT_ATTENDED))
				{
			%>	
			<his:ContentTag>	
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				
				
				<tr>
					<td class="tdfontheadExam" width="50%">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<b>
				 					<bean:message key="noOfNotAttendedSummon"/>
				 				</b>	
				  			</font>
				  		</div>
			      	</td>
			      	<td  class="tdfontheadExam" width="50%">
						<div align="center" >
							<bean:define id="noOfNotAttendedSummon" name="SummonTrackingFB" property="noOfNotAttendedSummon"></bean:define>
							<%=noOfNotAttendedSummon %>
						</div>
					</td>	
				</tr>
			</table>
		</his:ContentTag>		
			<%
				}
			%>	
			<%
				if(status.equals(MrdConfig.POST_DETAIL))
				{
			%>	
			<his:ContentTag>	
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td class="tdfontheadExam" width="50%">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<b>
				 					<bean:message key="noOfAttendedSummon"/>
				 				</b>	
				  			</font>
				  		</div>
			      	</td>
			      	<td  class="tdfontheadExam" width="50%">
						<div align="center" >
							<bean:define id="noOfAttendedSummon" name="SummonTrackingFB" property="noOfAttendedSummon"></bean:define>
							<%=noOfAttendedSummon %>
						</div>
					</td>	
				</tr>
				<tr>
					<td class="tdfontheadExam" width="50%">
				  		<div align="center">	           
				 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 				<b>
				 					<bean:message key="noOfNotAttendedSummon"/>
				 				</b>	
				  			</font>
				  		</div>
			      	</td>
			      	<td  class="tdfontheadExam" width="50%">
						<div align="center" >
							<bean:define id="noOfNotAttendedSummon" name="SummonTrackingFB" property="noOfNotAttendedSummon"></bean:define>
							<%=noOfNotAttendedSummon %>
						</div>
					</td>	
				</tr>
			</table>
		</his:ContentTag>		
			<%
				}
			%>		
			
		</logic:equal>
				
		
	
	<html:hidden name="SummonTrackingFB" property="hmode" />
	<html:hidden name="SummonTrackingFB" property="sysdate" />
	
	
	
	
	
</html:form>
</his:TransactionContainer>
<his:status/>

</body>
</html>