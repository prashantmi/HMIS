<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<%@page import="inpatient.InpatientConfig"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="inpatient.transaction.controller.fb.DoctorRoundFB"%>
<%@page import="hisglobal.vo.DoctorRoundDtlVO"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%-- <%@page import="ipd.setup.IPDConfig"%> --%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function closeForm()
{
	self.close();
}



function submitDataToDesk(index)
{
	if(document.getElementsByName("hiddenProgNotes")[index].value!="")
	{
		window.opener.document.getElementsByName("progressNote")[0].value=document.getElementsByName("hiddenProgNotes")[index].value;
	}
	else
	{
		window.opener.document.getElementsByName("progressNote")[0].value="";
	}
	
	if(document.getElementsByName("hiddenVisitNotes")[index].value!="")
	{
		window.opener.document.getElementsByName("visitNote")[0].value=document.getElementsByName("hiddenVisitNotes")[index].value;
	}
	else
	{
		window.opener.document.getElementsByName("visitNote")[0].value="";
	}
	
	if(document.getElementsByName("hiddenInstruction")[index].value!="")
	{
		window.opener.document.getElementsByName("instruction")[0].value=document.getElementsByName("hiddenInstruction")[index].value;
	}
	else
	{
		window.opener.document.getElementsByName("instruction")[0].value="";
	}
		window.opener.document.getElementsByName("change")[0].value="<%=InpatientConfig.PREV_IPD_DOC_ROUND_DETAILS_CHANGE%>";
		
		
		var datetimedata=document.getElementsByName("entryDateTime")[index].value;
		var datetime = datetimedata.split(" ");
		   window.opener.document.getElementsByName("docVisitDate")[0].value=datetime[0];  
		 var time=datetime[1];
		var hoursmins= time.split(":");  
			window.opener.document.getElementsByName("docVisitTimeHr")[0].value=hoursmins[0]; 
			window.opener.document.getElementsByName("docVisitTimeMin")[0].value=hoursmins[1];
		var rounddateval=document.getElementsByName("roundDate")[index].value;
			window.opener.document.getElementsByName("roundDate")[0].value=rounddateval;
			window.opener.document.getElementsByName("roundNo")[0].value=document.getElementsByName("roundNo")[index].value;
			window.opener.document.getElementsByName("serialNo")[0].value=document.getElementsByName("serialNo")[index].value;
			window.opener.updatedata(); 
		 self.close();
	 // document.getElementsByName('hmode')[0].value='CHANGE';
	 //document.forms[0].submit();
}

function viewProgNotes(index)
{
//alert("obj1="+obj1)
//alert("obj2="+obj2)
//alert("obj3="+obj3)
	//document.getElementsByName("showProgNotes")[0].value=obj1;
	//document.getElementsByName("showVisitNotes")[0].value=obj2;
	//document.getElementsByName("showInsNotes")[0].value=obj3;
	
	
	if(document.getElementsByName("hiddenProgNotes")[index].value!="")
	{
		document.getElementsByName("showProgNotes")[0].value=document.getElementsByName("hiddenProgNotes")[index].value;
	}
	else
	{
		document.getElementsByName("showProgNotes")[0].value="No Progress Notes";
	}
	
	if(document.getElementsByName("hiddenVisitNotes")[index].value!="")
	{
		document.getElementsByName("showVisitNotes")[0].value=document.getElementsByName("hiddenVisitNotes")[index].value;
	}
	else
	{
		document.getElementsByName("showVisitNotes")[0].value="No Visit Notes";
	}
	
	if(document.getElementsByName("hiddenInstruction")[index].value!="")
	{
		document.getElementsByName("showInsNotes")[0].value=document.getElementsByName("hiddenInstruction")[index].value;
	}
	else
	{
		document.getElementsByName("showInsNotes")[0].value="No Instructions";
	}
	
	
	
	
	
	document.getElementById("divViewProgNotes").style.display="block"; 
	document.getElementById("divViewVisitNotes").style.display="block";
	document.getElementById("divViewInstruction").style.display="block"; 
}

function viewVisitNotes(obj)
{
	document.getElementsByName("showVisitNotes")[0].value=obj;
	document.getElementById("divViewVisitNotes").style.display="block"; 
	document.getElementById("divViewInstruction").style.display="none";
	document.getElementById("divViewProgNotes").style.display="none"; 
}

function viewInstruction(obj)
{
	document.getElementsByName("showInsNotes")[0].value=obj;
	document.getElementById("divViewInstruction").style.display="block"; 
	document.getElementById("divViewProgNotes").style.display="none"; 
	document.getElementById("divViewVisitNotes").style.display="none"; 
}

function doPagination(e,p)
{	
	
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='DOCPOPUPPAGINATION';
	document.forms[0].submit();
}
</script>

<html:form action="/doctorRound">
	<body>
		
		<% 	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((DoctorRoundFB)request.getAttribute("DoctorRoundFB")).getCurrentPage());
				fbPage.setObjArrName(InpatientConfig.ARR_DOCTOR_PREV_ROUND_DETAIL );
				fbPage.setAppendInTitle("Previous Round Detail");
				int maxRecord=5;
				fbPage.setMaxRecords(maxRecord);
				
		%>
			<html:hidden name="DoctorRoundFB" property="currentPage"/>

			<his:PaginationTag name="fbPagination"></his:PaginationTag>
	<his:statusTransactionInProcess>	
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="15%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="SNo"/>
							</font>
						</div>
					</td>
					<td width="25%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="referredBy"/>
							</font>
						</div>
					</td>	
					<td width="25%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="entryDate"/>
							</font>
						</div>
					</td>	
					<td width="30%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								Doctor Round Date & Time
							</font>
						</div>
					</td>				
					<td width="30%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="progNote"/>
							</font>
						</div>
					</td>
					
				</tr>
			
				<%
				DoctorRoundDtlVO[] DoctorRoundDtlVOArray=(DoctorRoundDtlVO[])session.getAttribute(InpatientConfig.ARR_DOCTOR_PREV_ROUND_DETAIL);
						
						int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
						int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
						
												
						for(int j=startIndex;j<=endIndex;j++)
						{
							Integer ind=new Integer(j);
							int slNo=ind;
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
							
							DoctorRoundDtlVO doctorRoundDtlVO=(DoctorRoundDtlVO)DoctorRoundDtlVOArray[j];
	%>
				<tr>
					<td class="tdfont" width="15%" >
						<div align="center">
							<%=(slNo+1)%>
						</div>
					</td>
					<td class="tdfont" width="25%" >
						<div align="center">
							<%=doctorRoundDtlVO.getDoctorName()%>
						</div>
					</td>
					<td class="tdfont" width="25%" >
						<div align="center">
							<%=doctorRoundDtlVO.getEntryDate() %>
						</div>
					</td>
					<html:hidden name="DoctorRoundFB" property="entryDateTime"  value="<%=doctorRoundDtlVO.getEntryDate() %>"/>
					<html:hidden name="DoctorRoundFB" property="roundNo"  value="<%=doctorRoundDtlVO.getRoundNo()%>"/>
					<html:hidden name="DoctorRoundFB" property="serialNo"  value="<%=doctorRoundDtlVO.getSerialNo() %>"/>
					<html:hidden name="DoctorRoundFB" property="roundDate"  value="<%=doctorRoundDtlVO.getRoundDate() %>"/>
					<td class="tdfont" width="30%" >
						<div align="center">
							<%=doctorRoundDtlVO.getRoundTime() %>
						</div>
					</td>
					<td class="tdfont" width="30%" >
						<div align="center">
							
							<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>'  style=cursor:pointer tabindex="1" onclick ="viewProgNotes('<%=index %>')">
							<img class="button" src='<his:path src="/hisglobal/images/change.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitDataToDesk('<%=index %>')">
						</div>
						<div id="divProgNotesId" style="display: none;">
							<html:textarea name="DoctorRoundFB" property="hiddenProgNotes" rows="5" cols="130"  value="<%=doctorRoundDtlVO.getProgressNote()%>"></html:textarea>
						</div>
						<div id="divProgNotesId" style="display: none;">
							<html:textarea name="DoctorRoundFB" property="hiddenVisitNotes" rows="5" cols="130"  value="<%=doctorRoundDtlVO.getVisitNote()%>"></html:textarea>
						</div>
						<div id="divProgNotesId" style="display: none;">
							<html:textarea name="DoctorRoundFB" property="hiddenInstruction" rows="5" cols="130"  value="<%=doctorRoundDtlVO.getInstruction()%>"></html:textarea>
						</div>
					</td>		
							
				</tr>
			<%} %>	
			</table>	
		</his:ContentTag>
	</his:statusTransactionInProcess>
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        
       		<div id="divViewProgNotes" style="display: none;">
       			<his:SubTitleTag name="Progress Notes">
       			</his:SubTitleTag>
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="DoctorRoundFB" property="showProgNotes" rows="2" cols="125" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>
			
			<div id="divViewVisitNotes" style="display: none;">
       			<his:SubTitleTag name="Visit Notes">
       			</his:SubTitleTag>
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="DoctorRoundFB" property="showVisitNotes" rows="2" cols="125" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>
			
			<div id="divViewInstruction" style="display: none;">
       			<his:SubTitleTag name="Instruction">
       			</his:SubTitleTag>
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="DoctorRoundFB" property="showInsNotes" rows="2" cols="125" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>
		<html:hidden name="DoctorRoundFB" property="hmode"/>
		<html:hidden name="DoctorRoundFB" property="progressNote"/>
		<html:hidden name="DoctorRoundFB" property="visitNote"/>
		<html:hidden name="DoctorRoundFB" property="instruction"/>
		<html:hidden name="DoctorRoundFB" property="patCrNo"/>
		
	</body>
<his:status/>
</html:form>