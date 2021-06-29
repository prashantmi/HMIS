
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="mrd.transaction.controller.fb.SummonInboxFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="hisglobal.vo.SummonDetailVO"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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
		alert("Please select criteria");
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
		
		if(document.getElementsByName("displayMode")[0].checked==false && document.getElementsByName("displayMode")[1].checked==false)
		{
			alert("Please select display mode");
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
			alert("Please select employee");
			document.getElementsByName("employeeId")[0].focus();
			return false;
		}
	}
	
	submitPage(mode);
}

function showSummonDetail(mode)
{
	submitPage(mode);
}
 
</script>
<body>
		

<his:TransactionContainer>
	<html:form action="/summonInbox">
	<his:TitleTag name="Summon Inbox">
	</his:TitleTag>
	
	<logic:present name="<%=MrdConfig.ALL_ASSIGNED_SUMMON_DETAIL_LIST %>">
	<logic:notEmpty name="<%=MrdConfig.ALL_ASSIGNED_SUMMON_DETAIL_LIST %>">
		<% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((SummonInboxFB)request.getAttribute("SummonInboxFB")).getCurrentPage());
				fbPage.setObjArrName(MrdConfig.ALL_ASSIGNED_SUMMON_DETAIL_LIST);
				fbPage.setAppendInTitle("Assignment");
				int maxRecord=15;
				fbPage.setMaxRecords(maxRecord);
		%>
		<html:hidden name="SummonInboxFB" property="currentPage"/>
		<his:PaginationTag name="fbPagination"></his:PaginationTag>
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="5%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="select"/>
			  			</font>
			  		</div>
		      	</td>
				<td class="tdfonthead" width="20%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="summonType"/>	
			  			</font>
			  		</div>
		      	</td>
				<td class="tdfonthead" width="25%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="hearingDate&Time"/>
			  			</font>
			  		</div>
		      	</td>
		      	<td class="tdfonthead" width="25%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<bean:message key="assignDate&Time"/>
			  			</font>
			  		</div>
		      	</td>
		    </tr>
			
			<%
				List summonDetailVOLst=(List)session.getAttribute(MrdConfig.ALL_ASSIGNED_SUMMON_DETAIL_LIST );
						
				int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX));
				int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX));
						
				for(int j=startIndex;j<=endIndex;j++)
				{
					Integer ind=new Integer(j);
					int indd=ind;
					for(int i=0;i<maxRecord;i++)
					{
						if(indd%maxRecord==i) ind=i;
					}
							
					String index=ind.toString();
					SummonDetailVO summonDetailVO=(SummonDetailVO)summonDetailVOLst.get(j);
			%>
			
			<%
				if(summonDetailVO.getStatus().equals(MrdConfig.SUMMON_ASSIGN))
				{
			%> 
			<tr>
				<td  width="5%" bgcolor="#F1ECE2">
				 	<div align="center" >
				 		<html:radio  property="selectedSummonId" value="<%=summonDetailVO.getSummonId() %>" onclick="showSummonDetail('GETSUMMONDTL')"></html:radio>	
				 	</div>
				 </td>	
				 <td  width="20%" bgcolor="#F1ECE2">
				 	<div align="center" >
				 		<%
				 			if(summonDetailVO.getSummonTypeDesc()!=null)
				 			{
				 		%>
				 		<b><%=summonDetailVO.getSummonTypeDesc() %></b>
				 		<%		
				 			}
				 		%>
				 		
				 	</div>
				 </td>	
				
				 <td  width="20%" bgcolor="#F1ECE2">
				 	<div align="center" >
				 		<%
				 			if(summonDetailVO.getHearingDateTime()!=null)
				 			{
				 		%>
				 		<b><%=summonDetailVO.getHearingDateTime() %></b>
				 		<%		
				 			}
				 		%>
				 		
				 	</div>
				 </td>
				  <td  width="20%" bgcolor="#F1ECE2">
				 	<div align="center">
				 		<%
				 			if(summonDetailVO.getAssignDate()!=null)
				 			{
				 		%>
				 		<b><%=summonDetailVO.getAssignDate() %></b>
				 		<%		
				 			}
				 		%>
				 		
				 	</div>
				 </td>
			</tr>
			<%
				}
				if(summonDetailVO.getStatus().equals(MrdConfig.SUMMON_ACCEPTED))
				{
			%>
			<tr>
				<td  width="5%" bgcolor="CCCCCC">
				 	<div align="center">
				 		<html:radio  property="selectedSummonId" value="<%=summonDetailVO.getSummonId() %>" onclick="showSummonDetail('GETSUMMONDTL')" ></html:radio>	
				 	</div>
				 </td>	
				 <td width="20%" bgcolor="CCCCCC">
				 	<div align="center">
				 		
				 		<%
				 			if(summonDetailVO.getSummonTypeDesc()!=null)
				 			{
				 		%>
				 		<%=summonDetailVO.getSummonTypeDesc() %>
				 		<%		
				 			}
				 		%>
				 		
				 	</div>
				 </td>	
				 <td width="20%" bgcolor="CCCCCC">
				 	<div align="center">
				 		
				 		<%
				 			if(summonDetailVO.getHearingDateTime()!=null)
				 			{
				 		%>
				 		<%=summonDetailVO.getHearingDateTime() %>
				 		<%		
				 			}
				 		%>
				 		
				 	</div>
				 </td>
				  <td width="20%" bgcolor="CCCCCC">
				 	<div align="center">
				 		
				 		<%
				 			if(summonDetailVO.getAssignDate()!=null)
				 			{
				 		%>
				 		<%=summonDetailVO.getAssignDate() %>
				 		<%		
				 			}
				 		%>
				 		
				 	</div>
				 </td>
			</tr>			
			<%
				}
			%>
			<%	
				} 
			%>
			
		</table>
		</logic:notEmpty>
		</logic:present>
		<logic:notEmpty name="<%=MrdConfig.SELECTED_SUMMON_ASSIGN_ACCEPTED_VO %>">
		<his:SubTitleTag name="Summon Detail">
		</his:SubTitleTag>
		<%
			SummonDetailVO summonDetailVO=(SummonDetailVO)session.getAttribute(MrdConfig.SELECTED_SUMMON_ASSIGN_ACCEPTED_VO);
		%>	
		<div id="divSummonReceivedDetail" style="display: block">
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="25%" >
					<div align="right">	           
				 		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 			<b>
				 				<bean:message key="recevingDate"/>
				 			</b>	
				  		</font>
				  	</div>
			    </td> 
			    <td width="25%" class="tdfont">
					<div align="left">
						<%
							if(summonDetailVO.getSummonReceiveDateTime()!=null)
							{
						%>
						<%=summonDetailVO.getSummonReceiveDateTime() %>
						<%		
							}
						%>
					</div>
				</td>
				<td class="tdfonthead" width="25%" >
					<div align="right">	           
				 		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 			<b>
				 				<bean:message key="hearingDate"/>
				 			</b>	
				  		</font>
				  	</div>
			    </td> 
			    <td width="25%" class="tdfont">
					<div align="left">
						<%
							if(summonDetailVO.getHearingDateTime()!=null)
							{
						%>
						<%=summonDetailVO.getHearingDateTime() %>
						<%		
							}
						%>
					</div>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
					<div align="right">	           
				 		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 			<b>
				 				<bean:message key="summonType"/>
				 			</b>	
				  		</font>
				  	</div>
			    </td> 
			    <td width="25%" class="tdfont" width="25%"  >
					<div align="left" >
						<%
							if(summonDetailVO.getSummonTypeDesc()!=null)
							{
						%>
						<%=summonDetailVO.getSummonTypeDesc() %>
						<%		
							}
						%>
					</div>
				</td>
				<td class="tdfonthead" width="25%" >
					<div align="right">	           
				 		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 			<b>
				 				<bean:message key="summonDate"/>
				 			</b>	
				  		</font>
				  	</div>
			     </td> 
			     <td width="25%" class="tdfont">
					<div align="left">
						<%
							if(summonDetailVO.getSummonDateTime()!=null)
							{
						%>
						<%=summonDetailVO.getSummonDateTime() %>
						<%		
							}
						%>
					</div>
				</td>	
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 		<b>
					 			<bean:message key="courtName"/>
					 		</b>	
					  	</font>
					</div>
				</td>
				<td width="25%" class="tdfont"  >
					<div align="left">
						<%
							if(summonDetailVO.getCourtName()!=null)
							{
						%>
						<%=summonDetailVO.getCourtName() %>
						<%		
							}
						%>
					</div>
				</td>
				<td class="tdfonthead" width="25%" >
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 		<b>
					 			<bean:message key="judgeName"/>
					 		</b>	
					  	</font>
					</div>
				</td>
				<td  class="tdfont" width="25%" >
					<div align="left">
						<%
							if(summonDetailVO.getJudgeName()!=null)
							{
						%>
						<%=summonDetailVO.getJudgeName() %>
						<%		
							}
						%>
					</div>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
					<div align="right">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 		<b>
					 			<bean:message key="courtAddress"/>
					 		</b>	
					  	</font>
					</div>
				</td>
				<td  class="tdfont" colspan="3" >
					<%
						if(summonDetailVO.getCourtAddress()!=null)
						{
					%>
					<%=summonDetailVO.getCourtAddress() %>
						<%		
									}
						%>
						</td> 
						
				    </tr>
				    <tr>
				    	<td class="tdfonthead" width="25%" >
					  		<div align="right">	           
					 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 				<b>
					 					<bean:message key="policeOfficerName"/>
					 				</b>	
					  			</font>
					  		</div>
				      	</td>
				      	 <td width="25%" class="tdfont" >
						 	<div align="left">
						 		<%
									if(summonDetailVO.getConstableName()!=null)
									{
								%>
								<%=summonDetailVO.getConstableName() %>
								<%		
									}
								%>
						 	</div>
						 </td>
						 <td class="tdfonthead" width="25%" >
					  		<div align="right">	           
					 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 				<b>
					 					<bean:message key="designation"/>
					 				</b>	
					  			</font>
					  		</div>
				      	</td>
				      	 <td class="tdfont" width="25%" >
						 	<div align="left">
						 		<%
									if(summonDetailVO.getConstableDesig()!=null)
									{
								%>
								<%=summonDetailVO.getConstableDesig() %>
								<%		
									}
								%>
						 	</div>
						 </td>
				    </tr>
				    
				    <tr>
				    	<td class="tdfonthead" width="25%" >
					  		<div align="right">	           
					 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 				<b>
					 					<bean:message key="badgeNo"/>
					 				</b>	
					  			</font>
					  		</div>
				      	</td>
				      	 <td width="25%" class="tdfont" >
						 	<div align="left">
						 		<%
									if(summonDetailVO.getConstableBadgeNo()!=null)
									{
								%>
								<%=summonDetailVO.getConstableBadgeNo() %>
								<%		
									}
								%>
						 	</div>
						 </td>
						 <td class="tdfonthead" width="25%" >
					  		<div align="right">	           
					 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 				<b>
					 					<bean:message key="caseNo"/>
					 				</b>	
					  			</font>
					  		</div>
				      	</td>
				      	 <td  class="tdfont" width="25%" >
						 	<div align="left">
						 		<%
									if(summonDetailVO.getCaseNo()!=null)
									{
								%>
								<%=summonDetailVO.getCaseNo() %>
								<%		
									}
								%>
						 	</div>
						 </td>
				    </tr>
				     <tr>
				    	<td class="tdfonthead" width="25%" >
					  		<div align="right">	           
					 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 				<b>
					 					<bean:message key="policeStationAddress"/>
					 				</b>	
					  			</font>
					  		</div>
				      	</td>
				      	<td  class="tdfont" colspan="3">
							<%
									if(summonDetailVO.getPoliceStation()!=null)
									{
								%>
								<%=summonDetailVO.getPoliceStation() %>
								<%		
									}
								%>
						</td>
							
				    </tr>
				    <tr>
				    		
				    	 <td class="tdfonthead" width="25%" >
					  		<div align="right">	           
					 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 				<b>
					 					<bean:message key="empName"/>
					 				</b>	
					  			</font>
					  		</div>
				      	</td>
				      	 <td width="25%" class="tdfont"  >
						 	<div align="left">
						 		<%
									if(summonDetailVO.getEmpName()!=null)
									{
								%>
								<%=summonDetailVO.getEmpName() %>
								<%		
									}
								%>
						 	</div>
						 </td>
						 <td class="tdfonthead" width="25%" >
					  		<div align="right">	           
					 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 				<b>
					 					<bean:message key="CIDNo"/>
					 				</b>	
					  			</font>
					  		</div>
				      	</td>
				      	 <td class="tdfont" width="25%" >
						 	<div align="left">
						 		<%
									if(summonDetailVO.getCIDNo()!=null)
									{
								%>
								<%=summonDetailVO.getCIDNo()%>
								<%		
									}
								%>
						 	</div>
						 </td>
				    </tr>
				</table>
				</his:ContentTag>
				</div>
		<his:SubTitleTag name="Patient Detail">
		</his:SubTitleTag>	
		<div id="divPatientDetail" style="display: block">
				<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" >
					<tr>
						<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="patientName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<%
							if(summonDetailVO.getPatName()!=null)
							{
						%>
						<%=summonDetailVO.getPatName() %>
						<%		
							}
						%>
				 		
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="age"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" nowrap="nowrap">
				 	<div align="left">
				 		<%
							if(summonDetailVO.getPatDOB()!=null)
							{
						%>
						<%=summonDetailVO.getPatDOB() %>
						<%		
							}
						%>
				 	</div>
				 </td>	
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="fatherName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<%
							if(summonDetailVO.getFatherName()!=null)
							{
						%>
						<%=summonDetailVO.getFatherName() %>
						<%		
							}
						%>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
		  			<div align="right">	           
		 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		 					<b>
		 						<bean:message key="gender"/>
		 					</b>	
		  				</font>
		  			</div>
	      		</td> 
	      		<td width="25%" class="tdfont" width="25%"  >
					<div align="left" >
						<%
							if(summonDetailVO.getPatGenderCode()!=null)
							{
						%>
						<%=summonDetailVO.getPatGenderCode() %>
						<%		
							}
						%>
					</div>
				</td>
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="spouseName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<%
							if(summonDetailVO.getSpouseName()!=null)
							{
						%>
						<%=summonDetailVO.getSpouseName() %>
						<%		
							}
						%>
				 		
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="motherName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<%
							if(summonDetailVO.getMotherName()!=null)
							{
						%>
						<%=summonDetailVO.getMotherName() %>
						<%		
							}
						%>
				 	</div>
				 </td>	
			</tr>
			 <tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="patientAddress"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<div align="left">
						<%
							if(summonDetailVO.getPatAddress()!=null)
							{
						%>
						<%=summonDetailVO.getPatAddress() %>
						<%		
							}
						%>
					</div>
				</td>
					
		    </tr>
		    <tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="crNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<%
							if(summonDetailVO.getPatCrNo()!=null)
							{
						%>
						<%=summonDetailVO.getPatCrNo() %>
						<%		
							}
						%>
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="mlcNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<%
							if(summonDetailVO.getMLCNo()!=null)
							{
						%>
						<%=summonDetailVO.getMLCNo() %>
						<%		
							}
						%>
				 	</div>
				 </td>	
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="postMortemNo"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<%
							if(summonDetailVO.getPostmortemId()!=null)
							{
						%>
						<%=summonDetailVO.getPostmortemId() %>
						<%		
							}
						%>
				 	</div>
				 </td>	
				 <td class="tdfonthead" width="25%" >
				 </td>
				 <td class="tdfont" width="25%" >
				 </td>
			</tr>
			 <tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="summonRemarks"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<%
						if(summonDetailVO.getSummonRemarks()!=null)
						{
					%>
					<%=summonDetailVO.getSummonRemarks() %>
					<%		
						}
					%>
				</td>
			</tr>	
		</table>
		</his:ContentTag>
		</div>
		</logic:notEmpty>
		
		<his:ButtonToolBarTag>
			<his:statusDone>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			</his:statusDone>
			<his:statusList>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('NEW');" onkeypress="if(event.keyCode==13) submitPage('NEW');">
			</his:statusList>
		</his:ButtonToolBarTag>
	
		<html:hidden name="SummonInboxFB" property="hmode" />
	
</html:form>
</his:TransactionContainer>
<his:status/>

</body>
</html>