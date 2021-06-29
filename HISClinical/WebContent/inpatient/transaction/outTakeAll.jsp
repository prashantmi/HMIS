<!-- 
/**
 * @copyright CDAC
 * @developer Hruday Meher
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.RegistrationConfig"%>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<script type="text/javascript">
function closeForm()
{
	self.close();
}

function viewReport(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var fromDate=document.getElementsByName("fromDate")[0].value;
	var toDate=document.getElementsByName("toDate")[0].value;
	var path='/HISClinical/inpatient/patientOutTake.cnt?hmode=VIEWSUMMARY&patCrNo='+patCrNo+'&fromDate='+fromDate+'&toDate='+toDate;
	openPopup(createFHashAjaxQuery(path),event,400,600);
}

function submitPage(mode) 
{ 
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
	
}

function printPage() 
{
	var frameElement = parent.document.getElementById("frmMain");//f2");  
	var win = frameElement.contentWindow ;
	document.getElementById("noPrint").style.display="none"; 
	win.print(); 
	document.getElementById("noPrint").style.display="block" ; 
 }
 
function clearForm()
{
	document.getElementsByName("fromDate")[0].value=document.getElementsByName("sysDate")[0].value;
	document.getElementsByName("toDate")[0].value=document.getElementsByName("sysDate")[0].value;
}
</script>

<html:form action="/patientOutTake">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body>
	<bean:define name="OutTakeFB" property="sysDate" id="sysDate" type="java.lang.String" />
	<%
		if(sysDate==null||sysDate.equalsIgnoreCase(""))
		{
			sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		}
	%>
	<html:hidden name="OutTakeFB" property="sysDate" value="<%=sysDate%>"/>
		<his:statusNew>
			<his:ContentTag>
				<table width="100%" cellpadding="0" cellspacing="1">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="fromDate"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<his:date name="fromDate" dateFormate="%d-%b-%Y" value="<%=sysDate%>" ></his:date>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="toDate"/>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<his:date name="toDate" dateFormate="%d-%b-%Y" value="<%=sysDate%>" ></his:date>
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>		
		
			
			<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>'style=cursor:pointer onkeypress="if(event.keyCode==13) viewReport(event);" tabindex="1" onclick = "viewReport(event);" />         
		         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13)closeForm() "/>
		         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();"/>
	         	
			</his:ButtonToolBarTag>
		</his:statusNew>
		
		<his:statusTransactionInProcess>
			<div id="noPrint" align="right"> 
				<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  tabindex="1" style="cursor: pointer" onclick="printPage()" onkeypress="if(event.keyCode==13) printPage()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitPage('ALLSUMMARY')" onkeypress="if(event.keyCode==13) submitPage('ALLSUMMARY')">
			</div>
			<br>
			<br>
			
			<div align="center">
				<font color="#000000" size="15" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						Patient Intake/Output Detail
					</b>
				</font>
			</div>
			<br>
			<div align="center">
				<font color="#000000" size="15" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						From Date:&nbsp;<bean:write name="OutTakeFB" property="fromDate"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						To Date:&nbsp;<bean:write name="OutTakeFB" property="toDate"/>
					</b>
				</font>
			</div>
			
			<br><br>
			
			<table width="100%" align="center" cellpadding="1" cellspacing="0" border="1" bordercolor="black" style="color: black;">
				<tr>
					<td width="50%" valign="top">
						<table width="100%" cellpadding="0" cellspacing="0" border="1" bordercolor="black" style="color: black;">
							<tr>
								<td colspan="4">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												InTake
											</b>
										</font>
									</div>
								</td>	
							</tr>
						
							 <tr>
								<td width="40%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												Date
											</b>	
										</font>
									</div>
								</td>
								<td width="20%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>	
												Name
											</b>	
										</font>
									</div>
								</td>
								<td width="20%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>	
												Route
											</b>	
										</font>
									</div>
								</td>
								<td width="20%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>	
												Volume
											</b>	
										</font>
									</div>
								</td>
							</tr>
							<logic:iterate name="<%=InpatientConfig.PATIENT_INTAKE_VIEW_SUMMARY %>"  id="intakeVO" type="hisglobal.vo.PatIntakeOutDtlVO">
								 <tr>
								<td width="40%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=intakeVO.getInTakeOutTime() %>
										</font>
									</div>
								</td>
								<td width="20%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=intakeVO.getInTakeOutParaName() %>
										</font>
									</div>
								</td>
								<td width="20%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=intakeVO.getRouteName() %>
										</font>
									</div>
								</td>
								<td width="20%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=intakeVO.getVolume() %>
										</font>
									</div>
								</td>
							</tr>
							</logic:iterate>
						</table>
					</td>
					<td width="50%" valign="top">
						<table width="100%" cellpadding="0" cellspacing="0" border="1" bordercolor="black" style="color: black;">
							<tr>
								<td colspan="4">
									<div align="center" >
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>	
												Output
											</b>	
										</font>
									</div>
								</td>
							</tr>
						
							 <tr>
								<td width="40%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>	
												Date
											</b>	
										</font>
									</div>
								</td>
								<td width="20%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>
												Name
											</b>	
										</font>
									</div>
								</td>
								<td width="20%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>	
												Route
											</b>	
										</font>
									</div>
								</td>
								<td width="20%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>	
												Volume
											</b>	
										</font>
									</div>
								</td>
							</tr>
							<logic:iterate name="<%=InpatientConfig.PATIENT_OUTTAKE_VIEW_SUMMARY %>"  id="outtakeVO" type="hisglobal.vo.PatIntakeOutDtlVO">
								 <tr>
								<td width="40%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=outtakeVO.getInTakeOutTime() %>
										</font>
									</div>
								</td>
								<td width="20%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=outtakeVO.getInTakeOutParaName() %>
										</font>
									</div>
								</td>
								<td width="20%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=outtakeVO.getRouteName() %>
										</font>
									</div>
								</td>
								<td width="20%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=outtakeVO.getVolume() %>
										</font>
									</div>
								</td>
							</tr>
							</logic:iterate>		
						</table>
					</td>
				</tr>
				<tr>
					<td width="50%">
						<table width="100%" cellpadding="0" cellspacing="0" border="1" bordercolor="black" style="color: black;">
							<tr>
								<td width="80%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>	
												Total Intake
											</b>	
										</font>
									</div>
								</td>
								<td width="20%">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>	
											<bean:write name="OutTakeFB" property="totalViewSummaryIntake"/> ML
										</b>
									</font>
								</td>
							</tr>
						</table>
					</td>
					<td width="50%">
						<table width="100%" cellpadding="0" cellspacing="0" border="1" bordercolor="black" style="color: black;">
							<tr>
								<td width="80%">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>	
												Total Output
											</b>	
										</font>
									</div>
								</td>
								<td width="20%">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>	
											<bean:write name="OutTakeFB" property="totalViewSummaryOuttake"/> ML
										</b>
									</font>		
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="50%"> 
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	
									Balance
								</b>	
							</font>
						</div>
					</td>
					<td width="50%"> 
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>	
								<bean:write name="OutTakeFB" property="viewSummarybalance"/> ML
							</b>
						</font>	
					</td>
				</tr>
			</table>
		</his:statusTransactionInProcess>
		
		 <html:hidden name="OutTakeFB" property="hmode"/>
		 <html:hidden name="OutTakeFB" property="patCrNo"/>
	</body>
</html:form>	