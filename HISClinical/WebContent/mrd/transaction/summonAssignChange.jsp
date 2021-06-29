<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="mrd.transaction.controller.fb.SummonAssigmentChangeFB"%>
<%@page import="hisglobal.vo.SummonDetailVO"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
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

function showSummonDetail(mode)
{
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


function validateForm(mode)
{
	var alreadyAssignEmpName=document.getElementsByName("alreadyAssignEmpName")[0].value ;
	
	if(document.getElementsByName("selectedEmpId")[0].value=="")
	{
		alert("Please assign employee"+ '\n' + " For this click on search button");
		document.getElementsByName("selectedEmpId")[0].focus();
		return false;
	}
	/*
	if(document.getElementsByName("alreadyAssignEmpId")[0].value==document.getElementsByName("selectedEmpId")[0].value)
	{
		alert("Please assign other employee"+'\n'+" "+ alreadyAssignEmpName +" already assign for this summon");
		document.getElementsByName("selectedEmpId")[0].focus();
		return false;
	}
	*/
	if(document.getElementsByName("changeAssignReason")[0].value=="")
	{
		alert("Please enter change assignment reason")
		document.getElementsByName("changeAssignReason")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("assignFlag")[0].checked==false && document.getElementsByName("assignFlag")[1].checked==false)
	{
		alert("Please select assign employee")
		document.getElementsByName("assignFlag")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("assignFlag")[1].checked)
	{
		if(document.getElementsByName("otherAssignReason")[0].value=="")
		{
			alert("Please enter other employee assign reason");
			document.getElementsByName("otherAssignReason")[0].focus();
			return false;
		}
	}
	
	submitPage(mode);
}

function clearForm()
{
	
	document.getElementsByName("selectedEmpId")[0].value="";
	document.getElementsByName("empName")[0].value="";
	document.getElementsByName("changeAssignReason")[0].value="";
	document.getElementsByName("empDesignation")[0].value="";
	document.getElementsByName("empGenderCode")[0].value="";
	document.getElementsByName("empAddress")[0].value="";
	document.getElementsByName("empAge")[0].value="";
	document.getElementsByName("otherAssignReason")[0].value="";
	document.getElementsByName("assignFlag")[0].checked=false;
	document.getElementsByName("assignFlag")[1].checked=false;
	document.getElementById("rowOtherAssignReason").style.display="none";
}

function showReason()
{
	if(document.getElementsByName("assignFlag")[1].checked)
	{
		document.getElementById("rowOtherAssignReason").style.display="";
	}
	else
	{
		document.getElementsByName("otherAssignReason")[0].value="";
		document.getElementById("rowOtherAssignReason").style.display="none";
	}
} 
</script>
<body >
		

<his:TransactionContainer>
		<html:form action="/summonAssignmentChangeDtl">
		
		<his:TitleTag name="Summon Assignment Change Detail">
		</his:TitleTag>
		
		<logic:present name="<%=MrdConfig.SUMMON_ASSIGN_CHANGE_LIST %>">
		<logic:notEmpty name="<%=MrdConfig.SUMMON_ASSIGN_CHANGE_LIST %>">
		<% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((SummonAssigmentChangeFB)request.getAttribute("SummonAssigmentChangeFB")).getCurrentPage());
				fbPage.setObjArrName(MrdConfig.SUMMON_ASSIGN_CHANGE_LIST);
				fbPage.setAppendInTitle("Assignment");
				int maxRecord=10;
				fbPage.setMaxRecords(maxRecord);
				
		%>
			<html:hidden name="SummonAssigmentChangeFB" property="currentPage"/>

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
				List summonDetailVOLst=(List)session.getAttribute(MrdConfig.SUMMON_ASSIGN_CHANGE_LIST );
						
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
				 		<html:radio  property="selectedSummonId" value="<%=summonDetailVO.getSummonId() %>" onclick="showSummonDetail('GETSUMMONDETAIL')"></html:radio>	
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
		
		<logic:notEmpty name="<%=MrdConfig.SELECTED_SUMMON_ASSIGN_CHANGE_VO %>">
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
			SummonDetailVO summonDetailVO=(SummonDetailVO)session.getAttribute(MrdConfig.SELECTED_SUMMON_ASSIGN_CHANGE_VO);
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
								
								<html:hidden name="SummonAssigmentChangeFB" property="alreadyAssignEmpName" value="<%=summonDetailVO.getAssignEmpName() %>"/>
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
								<html:hidden name="SummonAssigmentChangeFB" property="alreadyAssignEmpId" value="<%=summonDetailVO.getAssignTo() %>"/>
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
		
		<his:SubTitleTag name="Change Assignment Detail">
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<div align="right">
					<img class="button" style="cursor: pointer" alt="Search" title="Search"	src='<his:path src="/hisglobal/images/btn-search.png"/>' onclick="openPopup('/HISClinical/mrd/summonAssignmentChangeDtl.cnt?hmode=SEARCHEMP',event,400,700);">
				</div>
			</tr>
		</table>
		</his:SubTitleTag>
		<div id="divAssignmentDetail" style="display: block;">
		
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
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonAssigmentChangeFB" property="empName"  size="20" tabindex="1" maxlength="60" onkeypress="return validateAlphaOnly(this,event)" readonly="true" />
				 	</div>
				 </td>
				 <td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="empDesignation"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonAssigmentChangeFB" property="empDesignation" size="20" tabindex="1" maxlength="60" onkeypress="return validateAlphaOnly(this,event)" readonly="true"/>
				 	</div>
				 </td>
				 	
			</tr>
			<tr>
				<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="age"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	 <td class="tdfont" width="25%" >
				 	<div align="left">
				 		<html:text name="SummonAssigmentChangeFB" property="empAge" size="4" tabindex="1" maxlength="3" onkeypress="return validateNumeric(event)" readonly="true"/>
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
						<html:text name="SummonAssigmentChangeFB" property="empGenderCode" size="8" tabindex="1" readonly="true"/>
					</div>
				</td>
			</tr>
			<tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<bean:message key="empAddress"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="SummonAssigmentChangeFB" tabindex="1" rows="2" cols="100" property="empAddress" value="" onkeypress="return (validateTextArea(event,this,'100'))" readonly="true">
						</html:textarea>
					</font>
				</td>
					
		    </tr>
		     <tr >
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="changeAssignReason"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="SummonAssigmentChangeFB" tabindex="1" rows="2" cols="100" property="changeAssignReason" onkeypress="return (validateTextArea(event,this,'100'))">
						</html:textarea>
					</font>
				</td>
			</tr>
		    <tr>
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="assignEmployee"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<div align="left">	
						Employee as required in summon<html:radio property="assignFlag" value="<%=MrdConfig.EMPLOYEE_AS_REQUIRED_IN_SUMMON %>" onclick="showReason()"></html:radio>      
						Arrangement to attend summon<html:radio property="assignFlag" value="<%=MrdConfig.ARRANGEMENT_TO_ATTEND_SUMMON %>" onclick="showReason()"></html:radio>
					</div>
				</td>
					
		    </tr>
		    
		    <tr id="rowOtherAssignReason" style="display: none;">
		    	<td class="tdfonthead" width="25%" >
			  		<div align="right">	           
			 			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 				<b>
			 					<font color="#FF0000">*</font>
			 					<bean:message key="otherEmpReason"/>
			 				</b>	
			  			</font>
			  		</div>
		      	</td>
		      	<td  class="tdfont" colspan="3">
					<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif"> 
						<html:textarea name="SummonAssigmentChangeFB" tabindex="1" rows="2" cols="100" property="otherAssignReason" onkeypress="return (validateTextArea(event,this,'100'))">
						</html:textarea>
					</font>
				</td>
			</tr>
		</table>
		</his:ContentTag>
		</div>
		</logic:notEmpty>
		
		
		<his:ButtonToolBarTag>
		<logic:notPresent name="<%=MrdConfig.SUMMON_ASSIGN_CHANGE_LIST %>">
			<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='1' onclick ="validateForm('SAVE');" onkeypress="if(event.keyCode==13)validateForm('SAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="clearForm();" onkeypress="if(event.keyCode==13) clearForm();">
		</logic:notPresent>
		<logic:present name="<%=MrdConfig.SUMMON_ASSIGN_CHANGE_LIST %>">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('INITCANCEL');" onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
		</logic:present>
		</his:ButtonToolBarTag>
		

	<html:hidden name="SummonAssigmentChangeFB" property="hmode" />
	<html:hidden name="SummonAssigmentChangeFB" property="selectedEmpId"/>
	
</html:form>
</his:TransactionContainer>
<his:status/>

</body>
</html>