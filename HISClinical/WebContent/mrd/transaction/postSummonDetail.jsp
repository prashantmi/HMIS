
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.vo.SummonDetailVO"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="mrd.transaction.controller.fb.SummonDtlFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="java.util.List"%>
<%@page import="mrd.transaction.controller.fb.SummonAssigmentFB"%>
<%@page import="mrd.transaction.controller.fb.PostSummonDetailFB"%>
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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
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

function showSummonDetail(mode)
{
	var len=document.getElementsByName("selectedSummonId").length;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("selectedSummonId")[i].checked)
		{
			var selSummonId=document.getElementsByName("selectedSummonId")[i].value.split("#")[0];
			var hearingDate=document.getElementsByName("selectedSummonId")[i].value.split("#")[1];
	
			var sysdate=document.getElementsByName("sysDate")[0].value;
			var day=noOfDays(hearingDate,sysdate)
			if(day>0)
			{
				alert("You can not enter Post Summon \n Because Hearing Date greater than System Date");
				document.getElementsByName("selectedSummonId")[0].focus();
				return false;
			}
		}
	}
	
	submitPage(mode);
}
function hideUnhide(imgObj)
{
	var divObj=document.getElementById("div"+imgObj.id.substr(3));
	if(divObj.style.display=="none")
	{
		divObj.style.display="block";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-up.png";
	}
	else if(divObj.style.display=="block")
	{
		divObj.style.display="none";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-down.png";
	}
}

function doPagination(e,p)
{	
	
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function showPostDetail()
{
	if(document.getElementsByName("hearingAttendedFlag")[1].checked)
	{
		document.getElementById("rowNotAttendedReason").style.display="";
		document.getElementsByName("visitRemarks")[0].value="";
		//document.getElementsByName("nextVisitDate")[0].value="";
		//document.getElementsByName("nextHearingTimeHr")[0].value="";
		//document.getElementsByName("nextHearingTimeMin")[0].value="";
		document.getElementById("rowVisitRemarks").style.display="none";
		//document.getElementById("rowNextVisit").style.display="none";
	}
	else
	{
		document.getElementsByName("notAttendedReason")[0].value="";
		document.getElementById("rowNotAttendedReason").style.display="none";
		document.getElementById("rowVisitRemarks").style.display="";
		//document.getElementById("rowNextVisit").style.display="";
	}
}

function validateForm(mode)
{
	if(document.getElementsByName("hearingAttendedFlag")[0].checked==false && document.getElementsByName("hearingAttendedFlag")[1].checked==false)
	{
		alert("Please select hearing attended")
		document.getElementsByName("hearingAttendedFlag")[0].focus();
		return false;
	}
	
		
	if(document.getElementsByName("hearingAttendedFlag")[0].checked)
	{
		if(document.getElementsByName("visitRemarks")[0].value=="")
		{
			alert("Please enter visit remarks");
			document.getElementsByName("visitRemarks")[0].focus();
			return false;
		}
	}
	
	if(document.getElementsByName("hearingAttendedFlag")[1].checked)
	{
		if(document.getElementsByName("notAttendedReason")[0].value=="")
		{
			alert("Please enter reason");
			document.getElementsByName("notAttendedReason")[0].focus();
			return false;
		}
	}
	
	submitPage(mode);
}

function clearForm()
{
	document.getElementsByName("notAttendedReason")[0].value="";
	document.getElementsByName("visitRemarks")[0].value="";
	//document.getElementsByName("nextVisitDate")[0].value="";
	//document.getElementsByName("nextHearingTimeHr")[0].value="";
	//document.getElementsByName("nextHearingTimeMin")[0].value="";
	document.getElementsByName("hearingAttendedFlag")[0].checked=false;
	document.getElementsByName("hearingAttendedFlag")[1].checked=false;
	document.getElementById("rowNotAttendedReason").style.display="none";
	document.getElementById("rowVisitRemarks").style.display="none";
	//document.getElementById("rowNextVisit").style.display="none";
}
 
</script>
<body >
		

<his:TransactionContainer>
		<html:form action="/postSummonDetail">
		<his:TitleTag name="Post Summon Detail">
		</his:TitleTag>
		<logic:present name="<%=MrdConfig.POST_SUMMON_LIST %>">
		<logic:notEmpty name="<%=MrdConfig.POST_SUMMON_LIST %>">
		<% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((PostSummonDetailFB)request.getAttribute("PostSummonDetailFB")).getCurrentPage());
				fbPage.setObjArrName(MrdConfig.POST_SUMMON_LIST);
				fbPage.setAppendInTitle("Post Summon");
				int maxRecord=10;
				fbPage.setMaxRecords(maxRecord);
				
		%>
			<html:hidden name="PostSummonDetailFB" property="currentPage"/>

			<his:PaginationTag name="fbPagination"></his:PaginationTag>
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="5%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="select"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
				<td class="tdfonthead" width="20%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="summonType"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	
		      	<td class="tdfonthead" width="25%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="hearingDate&Time"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td class="tdfonthead" width="25%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="empName"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td class="tdfonthead" width="10%" >
			  		<div align="center">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="empDesignation"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>	
			</tr>
			
			<%
				List summonDetailVOLst=(List)session.getAttribute(MrdConfig.POST_SUMMON_LIST );
						
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
							
							// if(indd%4==0) ind=0;
							// if(indd%4==1) ind=1;
							// if(indd%4==2) ind=2;
							// if(indd%4==3) ind=3;
								
							String index=ind.toString();
							
							SummonDetailVO summonDetailVO=(SummonDetailVO)summonDetailVOLst.get(j);
			%>
			
			<tr>
				<td class="tdfont" width="5%" >
				 	<div align="center">
				 		<html:radio  property="selectedSummonId" value='<%=summonDetailVO.getSummonId()+"#"+summonDetailVO.getHearingDateTime() %>' onclick="showSummonDetail('GETSUMMONDETAIL')"></html:radio>	
				 	</div>
				 </td>	
				 <td class="tdfont" width="20%" >
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
				
				 <td class="tdfont" width="20%" >
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
				  <td class="tdfont" width="20%" >
				 	<div align="center">
				 		<%
				 			if(summonDetailVO.getAssignEmpName()!=null)
				 			{
				 		%>
				 		<%=summonDetailVO.getAssignEmpName() %>
				 		<%		
				 			}
				 		%>
				 		
				 	</div>
				 </td>
				 
				 <td class="tdfont" width="20%" >
				 	<div align="center">
				 		<%
				 			if(summonDetailVO.getAssignEmpDesignation()!=null)
				 			{
				 		%>
				 		<%=summonDetailVO.getAssignEmpDesignation() %>
				 		<%		
				 			}
				 		%>
				 		
				 	</div>
				 </td>
				 
			</tr>
			<%} %>
			
		</table>
		</logic:notEmpty>
		</logic:present>
		<logic:notEmpty name="<%=MrdConfig.SELECTED_POST_SUMMON_VO %>">
				<his:SubTitleTag name="Summon Received Detail">
				<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<div align="right" >
							<img id="imgSummonReceivedDetail" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-up.png"; onclick="hideUnhide(this)"/>
						</div>
					</td>
				</tr>
			</table>
			</his:SubTitleTag>
			
		<%
			SummonDetailVO summonDetailVO=(SummonDetailVO)session.getAttribute(MrdConfig.SELECTED_POST_SUMMON_VO);
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
		<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<div align="right" >
							<img id="imgPatientDetail" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-up.png"; onclick="hideUnhide(this)"/>
						</div>
					</td>
				</tr>
			</table>		
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
							if(summonDetailVO.getPatAge()!=null)
							{
						%>
						<%=summonDetailVO.getPatAge() %>
						<%		
							}
							else
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
		<his:SubTitleTag name="Assignment Detail">
		<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<div align="right" >
							<img id="imgAssignmentDetail" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-up.png"; onclick="hideUnhide(this)"/>
						</div>
					</td>
				</tr>
			</table>		
		</his:SubTitleTag>
		<div id="divAssignmentDetail" style="display: block">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0" >
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
			      		<td width="25%" class="tdfont">
							<div align="left">
								<%
									if(summonDetailVO.getAssignEmpName()!=null)
									{
								%>
								<%=summonDetailVO.getAssignEmpName() %>
								<%		
									}
								%>
							</div>
						</td>
						<td class="tdfonthead" width="25%" >
				  			<div align="right">	           
				 				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 					<b>
				 						<bean:message key="empId"/>
				 					</b>	
				  				</font>
				  			</div>
			      		</td> 
			      		<td width="25%" class="tdfont">
							<div align="left">
								<%
									if(summonDetailVO.getAssignTo()!=null)
									{
								%>
								<%=summonDetailVO.getAssignTo() %>
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
				 						<bean:message key="designation"/>
				 					</b>	
				  				</font>
				  			</div>
			      		</td> 
			      		<td width="25%" class="tdfont">
							<div align="left">
								<%
									if(summonDetailVO.getAssignEmpDesignation()!=null)
									{
								%>
								<%=summonDetailVO.getAssignEmpDesignation() %>
								<%		
									}
								%>
							</div>
						</td>
						<td class="tdfonthead" width="25%" >
						</td>
						<td width="25%" class="tdfont">
						</td>
					</tr>
				</table>
			</his:ContentTag>	
		</div>	
		<his:SubTitleTag name="Post Summon Detail">
		</his:SubTitleTag>
		<div id="divPostSummonDetail" style="display: block;">
		
		<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="hearingAttended"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 
		      	<td  class="tdfont" width="25%">
					<div align="left">	
						Yes<html:radio property="hearingAttendedFlag" value="<%=MrdConfig.IS_HEARING_ATTENDED_YES %>" onclick="showPostDetail()"></html:radio>      
						No<html:radio property="hearingAttendedFlag" value="<%=MrdConfig.IS_HEARING_ATTENDED_NO %>" onclick="showPostDetail()"></html:radio>
					</div>
				</td>
				<td class="tdfonthead" width="25%" >
				</td>
				<td  class="tdfont" width="25%">
				</td>
					
		    </tr>
		    
		    <tr id="rowNextVisit" style="display: none;">
		    	
			</tr>
			<tr id="rowVisitRemarks" style="display: none;">
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="visitRemarks"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="PostSummonDetailFB" tabindex="1" rows="2" cols="100" property="visitRemarks" onkeypress="return (validateTextArea(event,this,'100'))">
						</html:textarea>
					</font>
				</td>
				
			</tr>
			<tr id="rowNotAttendedReason" style="display: none;">
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="reason"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="PostSummonDetailFB" tabindex="1" rows="2" cols="100" property="notAttendedReason" onkeypress="return (validateTextArea(event,this,'100'))">
						</html:textarea>
					</font>
				</td>
			</tr>
		</table>
		</his:ContentTag>
		</div>
		</logic:notEmpty>
		
		
		<his:ButtonToolBarTag>
		<logic:notPresent name="<%=MrdConfig.POST_SUMMON_LIST %>">
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick ="validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="clearForm();" onkeypress="if(event.keyCode==13) clearForm();">
		</logic:notPresent>
		<logic:present name="<%=MrdConfig.POST_SUMMON_LIST %>">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('INITCANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
		</logic:present>
		</his:ButtonToolBarTag>
		

	<html:hidden name="PostSummonDetailFB" property="hmode" />
	<html:hidden name="PostSummonDetailFB" property="sysDate" />
	
</html:form>
</his:TransactionContainer>
<his:status/>

</body>
</html>