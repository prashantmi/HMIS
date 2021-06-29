<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>
<%@page import="new_investigation.vo.InvPatientAcceptanceRespVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.transactions.controller.fb.InvPatientAcceptanceRespFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.config.HISConfig"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="new_investigation.transactions.controller.fb.InvPatientAcceptanceRespFB"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- Added by Prashant -->
<script type="text/javascript" src="media/jquery/3.4.1/jquery-3.4.1.min.js"></script>
<script src="media/jquery-ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<link href="media/jquery-ui/1.12.1/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<script src="media/jquery-ui/1.12.1/jquery.ui.datepicker.validation.min.js" type="text/javascript"></script>
<script src="media/jquery-ui/1.12.1/jquery.validate.min.js" type="text/javascript"></script>
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/hisglobal/js/additional-methods.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<script src="media/misc/datepicker1.js" type="text/javascript"></script>
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<!-- Added by Prashant -->
<link href="media/bootstrap/bootstrap-4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="media/prashantUIUX/customBootstrap/css/customBootstrap.css" type="text/css" />
<script src="media/dataTables/DataTables-1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="media/dataTables/DataTables-1.10.18/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<script src="media/dataTables/Responsive-2.2.2/js/dataTables.responsive.min.js" type="text/javascript"></script>
<script src="media/dataTables/Buttons-1.5.6/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="media/dataTables/Buttons-1.5.6/js/buttons.colVis.min.js" type="text/javascript"></script>
<link href="media/dataTables/Responsive-2.2.2/css/responsive.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="media/dataTables/Buttons-1.5.6/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="media/services/js/invPatientAcceptanceResp2.js"></script>

<script>
//1st
</script>
<script>
//2nd
</script>
<style>
#blanket {
	background-color: #111;
	opacity: 0.65;
	*background: none;
	position: absolute;
	z-index: 9001;
	top: 2px;
	left: 0px;
	width: 100%;
}

#popUpDiv5 {
	position: absolute;
	background: #CCE6FF;
	width: 400px;
	height: 240px;
	border: 5px solid #000;
	z-index: 9002;
}

#popUpDiv5 a {
	position: relative;
	top: 1px;
	left: 20px
}
</style>
<script type="text/javascript">
//3rd
//Function for displaying selected Lab List
function validateTodayOrDate()
{
	success=false;        	   
    
    valFromDate=document.getElementsByName('fromDate')[0];
	valToDate=document.getElementsByName('toDate')[0];
	
	<%String systemdate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
	valSysDate=document.getElementsByName('sysDate')[0];
      
    if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
    {
	    success=true;
    }             
    return success;        
}

</script>
<!-- Added by PrashantMi -->
<script>
//4th
</script>
<!-- Added by PrashantMi -->
<style>
table.dataTable td:nth-child(2), td:nth-child(3), td:nth-child(4), td:nth-child(4), td:nth-child(6),
	td:nth-child(7), td:nth-child(9), td:nth-child(11) {
	white-space: nowrap;
}

table.dataTable th {
	white-space: nowrap;
}

table.dataTable td:nth-child(10) {
	word-break: break-word;
	width: 300px;
	/* white-space: pre */
}
</style>
<style>
.scroll_div {
	width: 800px;
	height: 50px;
	overflow-y: hidden;
	overflow-x: scroll;
	text-align: justify;
	margin: 0;
	padding: 10px 10px 10px 10px;
	scrollbar-face-color: #666669;
	scrollbar-highlight-color: #030000;
	scrollbar-3dlight-color: #030000;
	scrollbar-darkshadow-color: #030000;
	scrollbar-shadow-color: #030000;
	scrollbar-arrow-color: #030000;
	scrollbar-track-color: #030000;
}
</style>
<style>
.textBoxCss {
	background: #ccc;
	color: #135d8c;
	width: 180px;
	padding: 4px 10px 4px 15px;
	border-radius: 20px;
	box-shadow: 0 1px 0 #ccc inset;
	transition: 500ms all ease;
	outline: 0;
}
</style>
<style>
/* Tooltip container */
.tooltip {
	position: relative;
	display: inline-block;
	border-bottom: 1px dotted black; /* If you want dots under the hoverable text */
}

/* Tooltip text */
.tooltip .tooltiptext {
	visibility: hidden;
	width: 120px;
	background-color: black;
	color: #fff;
	text-align: center;
	padding: 5px 0;
	border-radius: 6px;
	/* Position the tooltip text - see examples below! */
	position: absolute;
	z-index: 1;
}

/* Show the tooltip text when you mouse over the tooltip container */
.tooltip:hover .tooltiptext {
	visibility: visible;
}
</style>
<%
	String strdivage = "\"\"";
	String strdivdob = "\"\"";
%>

<script>
function actionOnSessionVariables() {
var jsonResponse = jQuery.parseJSON('<%= session.getAttribute("patientAcceptanceSessionJson") %>');
'<% session.setAttribute("patientAcceptanceSessionJson", null); %>'
	console.log(jsonResponse);
	if(jsonResponse != null && jsonResponse.closeIframe=="1") {
		//window.parent.postMessage(jsonResponse, '*');
		
		var interWinEvent = new CustomEvent('iframeCustomEventForParent', { detail: jsonResponse })
		window.parent.document.dispatchEvent(interWinEvent);
		
		parent.jQuery.fancybox.getInstance().close();
	}
}
</script>

<body onload="actionOnSessionVariables();">
<div class="container-fluid">
<div class="row">
<div class="col-12 pt-1" >
	<form >
		<html:form action="/invPatientAcceptanceResp">
			<html:hidden name="InvPatientAcceptanceRespFB" property="hmode" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="entryDate" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="isPatDetailPage" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="selectedCheckbox" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="showStatus" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="currentPage" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="patCRNo" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="sysDate" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="tmpTestCode" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="config" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="sampleAreaName" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="isSampleAreaSelected" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="patientType" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="selectedmachineCode" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="acceptedToNotAccepted" />
			<%
				boolean UserListDisplayed = false;
			%>
			<!-- To display userlist only one time -->
			<%
				int accCount = 0;
			%>
			<%!boolean readOnly;%>
			<%
				this.readOnly = false;
			%>
			<logic:equal name="InvPatientAcceptanceRespFB" property="hmode" value="VIEW">
				<%
					this.readOnly = true;
				%>
			</logic:equal>
			<his:TitleTag name="Online Patient Acceptance"> </his:TitleTag>
			
				<%
					String fromDateLabel = "";
							String toDateLabel = "";
							String fromDateControl = "";
							String toDateControl = "";
				%>
				<bean:define name="InvPatientAcceptanceRespFB" property="fromDate" id="frDate" type="java.lang.String" />
				<bean:define name="InvPatientAcceptanceRespFB" property="toDate" id="tDate" type="java.lang.String" />
				<%
					if (frDate == null || frDate.equalsIgnoreCase("")) {
								Date dt = (Date) session.getAttribute(Config.SYSDATEOBJECT);
								frDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT),
										"dd-MMM-yyyy");
							}

							if (tDate == null || tDate.equalsIgnoreCase("")) {
								Date dt = (Date) session.getAttribute(Config.SYSDATEOBJECT);
								tDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT),
										"dd-MMM-yyyy");
							}
				%>
				
				<logic:notEmpty name="<%=InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED%>">
					<logic:present name="<%=InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED%>">
					<% if (UserListDisplayed == false) { %>
					<!-- To Display UserList Only One Time -->
					<!-- PrashantMi to give permission to user for result entry starts-->
					<table>
						<tr>
							<div style=" width: 100%; background-color: #006A9F; color: white; height: 20; margin-bottom: 1px" align="left">
								<b></b>
							</div>
						</tr>
					</table>
					<table>
						<tr>
							<td class="font-weight-bold">Set User For Result Entry</td>
							<td class="">
								<div class="">
									<%
										Map<String, String> userList = new HashMap<String, String>();
															userList = (HashMap<String, String>) session
																	.getAttribute(InvestigationConfig.LIST_USERLIST);
									%>
									<html:select name="InvPatientAcceptanceRespFB" property="userHasPermission" style="width:160;">
										<html:option value="0">All Users</html:option>
										<%
											UserVO userVO = null;
																	userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
										%>
										<html:option value="<%=userVO.getUserId()%>"><%=userVO.getUsrName()%></html:option>
										<%
											for (Map.Entry<String, String> userListx : userList.entrySet()) {
										%>
										<html:option value="<%=userListx.getKey()%>"><%=userListx.getValue()%></html:option>
										<%
											}
										%>
									</html:select>
									<span class="tooltiptext d-none">The user selected here can do the "Result Entry". Please select "All User" if you don't known what it is.</span>
								</div>
							<td class="">
								<div align="left">Accepted Date</div> 
								<% if (accCount == 0) { %> 
								<% String straccept = "onChangeTopAcceptanceDate(this," + -1 + ",'" + 2 + "')"; %>
								<div align="left">
									<input type="text" id="-1acceptancedate" class="acceptancedateTC" onchange="<%=straccept%>" readonly>
								</div>
								<%} accCount++; %>
							</td>
						</tr>
					</table>
					
					<% UserListDisplayed = true; } %>
					<!-- PrashantMi to give permission to user for result entry ends-->
					</logic:present>
					</logic:notEmpty>
					
				<logic:present name="<%=InvestigationConfig.LIST_PATIENT_VO%>">
					<%
						String crno = "";
						String crno11 = "";
						String crnounbilled = "";
						String crnounbilledd = "";
						boolean flagg = false;
						boolean flaggunbilled = false;
						List<InvPatientAcceptanceRespVO> onlinePatientvvv = (List<InvPatientAcceptanceRespVO>) session.getAttribute(InvestigationConfig.LIST_PATIENT_VO);
						for (int l = 0; l < onlinePatientvvv.size(); l++) {
							InvPatientAcceptanceRespVO onlinePatientvo = onlinePatientvvv.get(l);

							if (onlinePatientvo.getPatStatus() != null
									&& (crno.equals("") || !crno.contains(onlinePatientvo.getPatPuk()))
									&& (crno11.equals("") || !crno11.contains(onlinePatientvo.getPatPuk()))) {

								if (flagg == false) {
									crno11 += onlinePatientvo.getPatPuk() + "#";

								}

								crno = onlinePatientvo.getPatPuk() + "#";

								flagg = true;
								flaggunbilled = true;
					%>
					<% String staffImage = (String) session.getAttribute(InvestigationConfig.STAFF_DEPENDENT_IMAGE); %>
					
					<div style="border-color: #fffff;"><br> </div>
					<div style="border:3px solid #ff9100 !important;">
						
						<div style="font-size: 18px; width: 100%; background-color: #006A9F; color: white; height: 20; margin-bottom: 0px" align="left">
							<b>Patient Details</b>
						</div>
						<div id="ipddDiv">
							<table width="100%">
								<tr>
									<td class="" width="25%">
										<div align="right">
											<bean:message key="crNO" />
											&nbsp;
										</div>
									</td>
									<td class="" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatPuk()%>
										</div>
									</td>
									<td class="" width="25%">
										<div align="right">
											<bean:message key="patientName" />
											&nbsp;
										</div>
									</td>
									<td class="" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatName()%>
										</div>
									</td>
								</tr>
								<tr>
									<td class="" width="25%">
										<div align="right">
											<bean:message key="age/gender" />
											&nbsp;
										</div>
									</td>
									<td class="" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatAge() + "/" + onlinePatientvo.getPatGender()%>
										</div>
									</td>
									<td class="" width="25%">
										<div align="right">
											<bean:message key="patStatus" />
											&nbsp;
										</div>
									</td>
									<td class="" width="25%">
										<div align="left">
											<%=onlinePatientvo.getPatStatus()%>
										</div>
									</td>
								</tr>
								<%
									if (staffImage != "") {
								%>
								<tr>
									<td class="" width="25%">
										<div align="right">Staff Dependent's Photo</div>
									</td>
									<td class="" width="25%">
										<div align="left">
											<img id="imageid" src="data:image/png;base64,<%=staffImage%>" style="width: 100px; height: 100px;" border="1">
										</div>
									</td>
								</tr>
								<%
									}
								%>
							</table>
						</div>
						<div id="<%=l%>show" style="display: none">
						<div style="font-size: 18px; width: 100%; background-color: #006A9F; color: white; height: 20; margin-bottom: 0px" align="left">
							<b>Patient Personal Details</b>
						</div>
							
							<table width="100%">
								<tr>
									<td class="" width="25%">
										<div align="right">
											<bean:message key="mobile" />
										</div>
									</td>
									<td class="" width="25%">
										<div align="left">
											<input type="text" class="textBoxCss" name="mobileNo" value="<%=onlinePatientvo.getMobileNo()%>" maxlength="10" size="25" onkeypress="return validateNumeric(event,this)" tabindex="1" />
										</div>
									</td>
									<td class="" width="15%">
										<div align="right">
											<label for="field"> <bean:message key="mail" />
											</label>
										</div>
									</td>
									<td class="" width="35%">
										<div id="myform" align="left">
											<input type="text" class="textBoxCss" id="field" value="<%=onlinePatientvo.getEmailId()%>" maxlength="50" size="25" onkeypress="onKeyP()" tabindex="1" name="emailId" />
										</div>
									</td>
								</tr>
							</table>
							<%
								String patcetcode = "";
													if (onlinePatientvo.getPatCategoryCode() != null) {
														patcetcode = onlinePatientvo.getPatCategoryCode();
													}
							%>
							<html:hidden name="InvPatientAcceptanceRespFB" property="patCategoryCode" value="<%=patcetcode%>" />
							<%
								if (onlinePatientvo.getPatStatus().equals("IPD")) {
							%>
						
						<div style="font-size: 18px; width: 100%; background-color: #006A9F; color: white; height: 20; margin-bottom: 0px" align="left">
							<b>Ipd Details</b>
						</div>	
						
							<div id="ipddDiv">
								<table width="100%">
									<tr>
										<td class="" width="25%">
											<div align="right">
												<bean:message key="admitdept" />
											</div>
										</td>
										<td class="" width="25%">
											<div align="left">
												<%=onlinePatientvo.getPatUnitName()%>
											</div>
										</td>
										<td class="" width="25%">
											<div align="right">
												<bean:message key="wardName" />
											</div>
										</td>
										<td class="" width="25%">
											<div align="left">
												<%=onlinePatientvo.getPatWardName()%>
											</div>
										</td>
									</tr>
									<tr>
										<td class="" width="25%">
											<div align="right">
												<bean:message key="roomName" />
											</div>
										</td>
										<td class="" width="25%">
											<div align="left">
												<%=onlinePatientvo.getPatRoomName()%>
											</div>
										</td>
										<td class="" width="25%">
											<div align="right">
												<bean:message key="bedName" />
											</div>
										</td>
										<td class="" width="25%">
											<div align="left">
												<%=onlinePatientvo.getPatBedName()%>
											</div>
										</td>
									</tr>
									<tr>
										<td class="" width="25%">
											<div align="right">
												<bean:message key="consultant" />
											</div>
										</td>
										<td class="" width="25%">
											<div align="left">
												<%=onlinePatientvo.getPatOrderByDoc()%>
											</div>
										</td>
										<td class="" width="25%"></td>
										<td class="" width="25%"></td>
										
									</tr>
								</table>
							</div>
							<%
								}
							%>
							<div class="subDivStyle">
							<div style="font-size: 18px; width: 100%; background-color: #006A9F; color: white; height: 20; margin-bottom: 0px" align="left">
								<b>Requisition Forms</b>
								<img class="button" title="Show Patinet Details" src='<his:path src="/hisglobal/images/plusinv.png"/>' id="reqformshow" tabindex="1" onclick="f1()">
								<img class="button" title="Hide Patient Details" src='<his:path src="/hisglobal/images/Minus.png"/>' id="reqformhide" tabindex="1" onclick="f2()">
							</div>	
								
							</div>
							<div class="subDivStyle" id="reqformss" style="display: none">
								<table width="100%" border="1" id="tbll">
									<tr>
										<td style="font-weight: bold; color: brown;">TEST NAME</td>
										<td style="font-weight: bold; color: brown;">REQUISITION FORMS</td>
									</tr>
								</table>
							</div>
							<%
								if (onlinePatientvo.getPatStatus().equals("OPD")) {
							%>
							<div style="font-size: 18px; width: 100%; background-color: #006A9F; color: white; height: 20; margin-bottom: 0px" align="left">
								<b>Opd Details</b>
							</div>	
							
							
							<div id="opdEmerencyDIV">
								<table width="100%">
									<tr>
										<td class="" width="25%">
											<div align="right">Department/Unit Name</div>
										</td>
										<td class="" width="25%">
											<div align="left">
												<%=onlinePatientvo.getPatUnitName()%>
											</div>
										</td>
										<td class="" width="25%">
											<div align="right">
												<bean:message key="age/gender" />
												&nbsp;
											</div>
										</td>
										<td class="" width="25%">
											<div align="left">
												<%=onlinePatientvo.getPatAge() + "/" + onlinePatientvo.getPatGender()%>
											</div>
										</td>
									</tr>
									<tr>
										<td class="" width="25%">
											<div align="right">
												<bean:message key="visitDate" />
												&nbsp;
											</div>
										</td>
										<td class="" width="25%">
											<div align="left">
												<%=onlinePatientvo.getPatVisitDat()%>
											</div>
										</td>
										<td class="" width="25%">
											<div align="right">
												<bean:message key="patCat" />
											</div>
										</td>
										<td class="" width="25%">
											<div align="left">
												<%=(onlinePatientvo.getPatCategory() == null
											? ""
											: onlinePatientvo.getPatCategory())%>
											</div>
										</td>
									</tr>
									<tr>
										<td class="" width="25%">
											<div align="right">
												<bean:message key="consultant" />
											</div>
										</td>
										<td class="" width="25%">
											<div align="left">
												<%=(onlinePatientvo.getPatOrderByDoc() == null
											? ""
											: onlinePatientvo.getPatOrderByDoc())%>
											</div>
										</td>
										<td class="" width="25%"></td>
										<td class="" width="25%"></td>
										
									</tr>
								</table>
							</div>
							<%
								}
							%>
						</div>
						<% } %>
						
						<html:hidden name="InvPatientAcceptanceRespFB" property="sampleAreaCode" />
						
						<!--  Billed Details -->
						<logic:notEmpty name="<%=InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED%>">
							<logic:present name="<%=InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED%>">
								<%
									List<InvPatientAcceptanceRespVO> listPatientCollectio = (List<InvPatientAcceptanceRespVO>) session.getAttribute(InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED);
									int sizeSamp = listPatientCollectio.size();

									int x = 0;
									if (x < sizeSamp && flagg == true) {
								%>
								<html:hidden name="InvPatientAcceptanceRespFB" property="sampleAreaCode" />
								
								<div style="font-size: 18px; width: 100%; background-color: #006A9F; color: white; height: 20; margin-bottom: 0px" align="left">
									<b>Billed Details</b>
								</div>
								
								<table width="100%">
									<tr>
										<td class="">
											<div align="left">
												<bean:message key="select" />
											</div>
										</td>
										<td class="">
											<div align="left">
												<bean:message key="labName" />
												&nbsp;
											</div>
										</td>
										<td class="">
											<div align="left">
												<bean:message key="reqDate" />
											</div>
										</td>
										<td class="">
											<div align="left">
												<bean:message key="TestName" />
											</div>
										</td>
										
										<td class="">
											<div align="left">
												<bean:message key="accessionNowise" />
											</div>
										</td>
										<td class="">
											<div align="left">
												<bean:message key="accepted" />
											</div>
										</td>
										<td class="">
											<div align="left">Accepted Date</div> 
										</td>
										<td id="showthi" style="display: none" class="">
											<div align="left">
												<bean:message key="rejectionAction" />
											</div>
										</td>
										<td id="showthis" style="display: none" class="">
											<div align="left">
												<bean:message key="RejectionReason" />
											</div>
										</td>
										<td class="">
											<div align="left">
												<bean:message key="priority" />
											</div>
										</td>
									</tr>
									
								<% } %>
									<%
										String crno1 = "";
							List<InvPatientAcceptanceRespVO> listPatientCollection = (List<InvPatientAcceptanceRespVO>) session
									.getAttribute(InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED);
							int sizeSampleV = listPatientCollection.size();
							Map<String, String> checkforgrp = new HashMap<String, String>();

							for (int k = 0; k < sizeSampleV; k++) {
								InvPatientAcceptanceRespVO voPatientCollection = listPatientCollection.get(k);
								String color = "black";
								int priorityCode = 1;

								if (crno.contains(voPatientCollection.getPatPuk()) && flagg == true) {

									if (checkforgrp.containsKey(voPatientCollection.getRequisitionNo()
											+ voPatientCollection.getGroupCode())) {
									} else {
										checkforgrp.put(voPatientCollection.getRequisitionNo()
												+ voPatientCollection.getGroupCode(), "1");

									}

									if (voPatientCollection.getPriorityCode() != null) // Normal;Urgent;Confidential
									{
										priorityCode = Integer.parseInt(voPatientCollection.getPriorityCode());
										switch (priorityCode) {
											case 1 :
												color = "blue"; // Normal Priority;
												break;
											case 2 :
												color = "red"; // Urgent Priority;
												break;
											case 3 :
												color = "brown"; // Confidential Priority;
												break;
											default :
												color = "blue";
												break;
										}

									}

									String chkSampleVal = voPatientCollection.getPatPuk() + "#"
											+ voPatientCollection.getRequisitionNo() + "#"
											+ voPatientCollection.getRequisitionDNo() + "#"
											+ voPatientCollection.getLabCode() + "#"
											+ voPatientCollection.getBillDetail() + "#"
											+ voPatientCollection.getTestName() + "#"
											+ voPatientCollection.getTestCode() + "#"
											+ voPatientCollection.getLabNoConfiguration() + "#"
											+ voPatientCollection.getLabName() + "#"
											+ voPatientCollection.getIsrequisitionformneeded() + "#"
											+ voPatientCollection.getGroupCode() + "#" + Integer.toString(k);
									System.out.println("chkSampleVal : " + chkSampleVal);
									%>
									<tr>
										<%
											String chkBox = "onClickSavecheckedgrp(this," + k + ",'0')";
										%>
										<td class="" align="left">
											<div align="left">
												<input type="checkbox" id="<%=k%>chkBox" name="chkSamplePatientOnSave" value='<%=chkSampleVal%>' onChange="<%=chkBox%>" /> <input type="hidden" id="<%=k%>chkBoxonload"
													name="chkSamplePatientOnSaveonload" value='<%=voPatientCollection.getCheckboxajaxvalues()%>'
												/>
											</div>
										</td>
										<td class="">
											<div align="left">
												<font color="<%=color%>"> <%=voPatientCollection.getLabName()%>
												</font>
											</div>
										</td>
										<td class="">
											<div align="left">
												<!-- Modified  by Prashant -->
												<p style="color: blue;"><%=voPatientCollection.getRequisitionDate()%></p>
												<input type="hidden" id="<%=k%>requisitionDate" class="requisitionDateC" value="<%=voPatientCollection.getRequisitionDate()%>">
											</div>
										</td>
										<td class="">
											<div align="left">
												<font color="<%=color%>"> <%=voPatientCollection.getTestName()%> <%
 									String inst = voPatientCollection.getInstructionPat();
 									String collInstr = voPatientCollection.getInstructionColl();
 									inst = inst.replace("\r\n", "<br>");
 									collInstr = collInstr.replace("\r\n", "<br>");

 									/* String inst="d"; */
									 %> <%
									 	if ((inst.equals("Patient#") && collInstr.equals("Technician#"))) {
									 %> <%
									 	} else {
									 %> <img class="button" title="Show Instructions" src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>' tabindex="1" onclick="showInstructions5('<%=inst%>','<%=collInstr%>');">
								<% } %>
								 <% if (!voPatientCollection.getIsrequisitionformneeded().equals("0")) { %> 
									<% } %>
												</font>
											</div>
											<div id="blanket" style="display: none;"></div>
											<div id="popUpDiv5" style="display: none;" align="center">
												<his:TitleTag name="Instructions For Patients">
													<img src='/HISInvestigationG5/hisglobal/css/close.png' onClick="closeInstructions();">
												</his:TitleTag>
												<table width="100%" id="allInstructions">
													<tr>
														
													</tr>
												</table>
												<img src='/HISInvestigationG5/hisglobal/images/ok.gif' onClick="closeInstructions();">
											</div>
										</td>
										<%
											String labNO = voPatientCollection.getLabNoConfiguration();

																			String labNoConfiguration2 = null;
																			if (voPatientCollection.getAcceptedToNotAccepted().equals("2")) {
																				labNoConfiguration2 = voPatientCollection.getLabNoConfiguration2();
																			} else {
																				labNoConfiguration2 = "";
																			}

																			if (labNO.equals(InvestigationConfig.MANUAL_LAB)) {
										%>
										<td class="">
											<div align="left">
												<font color="<%=color%>"> <input type="text" class="textBoxCss" id="<%=k%>dailyNO" name="labNoConfiguration" value="<%=labNoConfiguration2%>" maxlength="20" size="25"
													onkeypress="return validateAlphaNumericOnly(event,this)" onblur="chkDailyLabNoDuplicacyThroughAjax(this,event);" tabindex="1"
												/>
												</font>
											</div>
										</td>
										<%
											} else {
										%>
										<td class="">
											<div align="left">
												<font color="<%=color%>"> <input type="hidden" class="textBoxCss" id="<%=k%>dailyNO" name="labNoConfiguration" value="<%=labNO%>" maxlength="20" size="25"
													onkeypress="return validateNumeric(event,this)" onblur="chkDailyLabNoDuplicacyThroughAjax(this,event);" tabindex="1"
												/>
												</font>
											</div>
										</td>
										<%
											}
										%>
										<%
											String classs = "0";
																			if (voPatientCollection.getGroupCode() != null
																					&& !voPatientCollection.getGroupCode().equals("0")) {
																				classs = voPatientCollection.getRequisitionNo()
																						+ voPatientCollection.getGroupCode();
										%>
										<%
											}
										%>
										<td class="">
											<div align="left">
												<%
													String str = "onChane(this," + k + ",'" + classs + "')";
												%>
												<span class="custom-dropdown small"> <select style="width: 160;" name="accepted" tabindex="<%=Integer.toString(k)%>" class="<%=classs%>" onchange="<%=str%>">
														<option value="1">Yes</option>
														<option value="0">No</option>
												</select> 
												</span>
											</div>
										</td>
										<!-- Added by Prashant -->
										<%
											String straccept = "onChangeAcceptanceDate(this," + k + ",'" + 0 + "')";
										%>
										<td class="">
											<div align="left">
												<input type="text" id="<%=k%>acceptancedate" class="acceptancedateC" onchange="<%=straccept%>" readonly> <input type="hidden" id="<%=k%>acceptancedateHidden"
													value=<%=voPatientCollection.getAcceptanceDate()%>
												>
											</div>
										</td>
										<td id="showthi_<%=k%>" style="display: none" class="">
											<div align="left">
												<span class="custom-dropdown small"> <html:select name="InvPatientAcceptanceRespFB" property="rejectionAction" style="width:160;" tabindex="1">
														<html:option value="1">
															<bean:message key="cancelled" />
														</html:option>
														<%-- <html:option value="2"> <bean:message key="rescheduled"/> </html:option> --%>
													</html:select>
												</span>
											</div>
										</td>
										<td id="showth_<%=k%>" style="display: none" class=""><logic:present name="<%=InvestigationConfig.TEST_REASON_COMBO%>">
												<div align="left">
													<%
														String strreason = "reasonextrashw(this," + k + ")";
													%>
													<span class="custom-dropdown small"> <html:select onchange="<%=strreason%>" name="InvPatientAcceptanceRespFB" property="rejectionReason" tabindex="1">
															<html:option value="-1">Select Value</html:option>
															<html:option value="-2">Other</html:option>
															<html:options collection="<%=InvestigationConfig.TEST_REASON_COMBO%>" property="value" labelProperty="label" />
														</html:select>
													</span>
												</div>
											</logic:present>
											<div id="rej<%=k%>" style="display: none" align="left">
												<input type="text" id="rejj<%=k%>" name="extrarejectionReason" />
											</div></td>
										
										<td class="">
											<div align="left">
												<font color="<%=color%>"> <%=voPatientCollection.getPriority() == null
													? ""
													: voPatientCollection.getPriority()%>
												</font>
											</div>
										</td>
									</tr>
								<% } } flagg = false; %>
									
								</table>

							</logic:present>
						</logic:notEmpty>
						
						<!-- unbilled -->
						<logic:notEmpty name="<%=InvestigationConfig.LIST_PAT_PATIENT_UNBILLED%>">
							<logic:present name="<%=InvestigationConfig.LIST_PAT_PATIENT_UNBILLED%>">
										<%
											String crnoo = "";
											boolean flag = false;
											List<InvPatientAcceptanceRespVO> listPatientColle = (List<InvPatientAcceptanceRespVO>) session.getAttribute(InvestigationConfig.LIST_PAT_PATIENT_UNBILLED);
											int sizeS = listPatientColle.size();
											boolean isshw = true;

											for (int p = 0; p < sizeS; p++) {

												InvPatientAcceptanceRespVO vp = listPatientColle.get(p);

												if (crno.contains(vp.getPatPuk()))
													flag = true;

												boolean fgg = false;
												if (crnounbilledd.contains(vp.getPatPuk())) {
													fgg = false;

												} else
													fgg = true;

												int z = 0;
												if ((z < sizeS && (crno.contains(vp.getPatPuk()) && fgg == true))) {
													crnounbilled = vp.getPatPuk() + "#";
													if (isshw) {
										%>
										
										<div style="font-size: 18px; width: 100%; background-color: #006A9F; color: white; height: 20; margin-bottom: 0px" align="left">
											<b>Unbilled Details</b>
										</div>
										
										<table width="100%">
											<tr>
												<td class="">
													<div align="left">
														<bean:message key="labName" />
														&nbsp;
													</div>
												</td>
												<td class="">
													<div align="left">
														<bean:message key="reqDate" />
													</div>
												</td>
												
												<td class="">
													<div align="left">
														<bean:message key="TestName" />
														&nbsp;
													</div>
												</td>
												<td class="">
													<div align="left">
														<bean:message key="priority" />
													</div>
												</td>
											</tr>
											<%
												}
																						isshw = false;

																					}

																				}
											%>
											<%
												List<InvPatientAcceptanceRespVO> listPateintCollection = (List<InvPatientAcceptanceRespVO>) session
																						.getAttribute(InvestigationConfig.LIST_PAT_PATIENT_UNBILLED);
																				int sizeSample = listPateintCollection.size();
																				crnounbilledd = "";
																				for (int k = 0; k < sizeSample; k++) {

																					InvPatientAcceptanceRespVO voPatientCollection = listPateintCollection
																							.get(k);
																					boolean fg = false;
																					if (crnounbilledd.contains(voPatientCollection.getPatPuk())) {
																						fg = false;

																					} else
																						fg = true;
																					String color = "blue";
																					if (crno.contains(voPatientCollection.getPatPuk())) {
																						crnounbilledd += voPatientCollection.getPatPuk() + "#";

																						int priorityCode = 1;
																						if (voPatientCollection.getPriorityCode() != null) // Normal;Urgent;Confidential
																						{
																							priorityCode = Integer
																									.parseInt(voPatientCollection.getPriorityCode());
																							switch (priorityCode) {
																								case 1 :
																									color = "blue"; // Normal Priority;
																									break;
																								case 2 :
																									color = "red"; // Urgent Priority;
																									break;
																								case 3 :
																									color = "brown"; // Confidential Priority;
																									break;
																								default :
																									color = "blue";
																									break;
																							}
																						}
																					}
																					//String chkSampleVal=voPatientCollection.getPatCRNo()+"#"+voPatientCollection.getRequisitionNo()+"#"+voPatientCollection.getSampleCode()+"#"+voSampleCollection.getRequisitionDNo();

																					if (crno.contains(voPatientCollection.getPatPuk())
																							&& flaggunbilled == true) {
											%>
											<tr>
												<td class="">
													<div align="left">
														<font color="<%=color%>"> <%=voPatientCollection.getLabName()%>
														</font>
													</div>
												</td>
												<td class="">
													<div align="left">
														<font color="<%=color%>"> <%=voPatientCollection.getRequisitionDate()%>
														</font>
													</div>
												</td>
												
												<td class="">
													<div align="left">
														<font color="<%=color%>"> <%=voPatientCollection.getTestName()%>
														</font>
													</div>
												</td>
												<td class="">
													<div align="left">
														<font color="<%=color%>"> <%=voPatientCollection.getPriority() == null
														? ""
														: voPatientCollection.getPriority()%>
														</font>
													</div>
												</td>
												<td class="">
													<div align="left">
														<font color="<%=color%>"> <img class="button" title="Show Instructions" src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>' tabindex="1" onclick="showInstructions5();">
														</font>
													</div>
												</td>
											</tr>
											<%
												}

																				}
																				flaggunbilled = false;
											%>
										</table>
										
									</logic:present>
						</logic:notEmpty>
								
					</div>	
				<% } %>
					
				</logic:present>
				
				<!-- Unbilled requisition -->
				
					<logic:empty name="<%=InvestigationConfig.LIST_REQUISITION_PATIENT_BILLED%>">
						<logic:notEmpty name="<%=InvestigationConfig.LIST_PAT_PATIENT_UNBILLED%>">
							<logic:present name="<%=InvestigationConfig.LIST_PAT_PATIENT_UNBILLED%>">
								<%
									String crnoo = "";
									boolean flag = false;
									List<InvPatientAcceptanceRespVO> listPatientColle = (List<InvPatientAcceptanceRespVO>) session.getAttribute(InvestigationConfig.LIST_PAT_PATIENT_UNBILLED);
									int sizeS = listPatientColle.size();

									for (int p = 0; p < sizeS; p++) {
										InvPatientAcceptanceRespVO vp = listPatientColle.get(p);

										int z = 0;
										if (z < sizeS) {
								%>
								<div style="font-size: 18px; width: 100%; background-color: #006A9F; color: white; height: 20; margin-bottom: 0px" align="left">
									<b>Unbilled Details</b>
								</div>
								<table width="100%">
									<tr>
										<td class="">
											<div align="left">
												<bean:message key="labName" />
												&nbsp;
											</div>
										</td>
										<td class="">
											<div align="left">
												<bean:message key="reqDate" />
											</div>
										</td>
										
										<td class="">
											<div align="left">
												<bean:message key="TestName" />
												&nbsp;
											</div>
										</td>
										<td class="">
											<div align="left">
												<bean:message key="priority" />
											</div>
										</td>
									</tr>
									<%
										}
																	break;
																}
									%>
									<%
										List<InvPatientAcceptanceRespVO> listPateintCollection = (List<InvPatientAcceptanceRespVO>) session.getAttribute(InvestigationConfig.LIST_PAT_PATIENT_UNBILLED);
																int sizeSample = listPateintCollection.size();

																for (int k = 0; k < sizeSample; k++) {

																	InvPatientAcceptanceRespVO voPatientCollection = listPateintCollection.get(k);
																	boolean fg = false;

																	//crnounbilledd+=voPatientCollection.getPatPuk()+"#";
																	String color = "black";
																	int priorityCode = 1;
																	if (voPatientCollection.getPriorityCode() != null) // Normal;Urgent;Confidential
																	{
																		priorityCode = Integer.parseInt(voPatientCollection.getPriorityCode());
																		switch (priorityCode) {
																			case 1 :
																				color = "blue"; // Normal Priority;
																				break;
																			case 2 :
																				color = "red"; // Urgent Priority;
																				break;
																			case 3 :
																				color = "brown"; // Confidential Priority;
																				break;
																			default :
																				color = "blue";
																				break;
																		}
																	}

									%>
									<tr>
										<td class="">
											<div align="left">
												<font color="<%=color%>"> <%=voPatientCollection.getLabName()%>
												</font>
											</div>
										</td>
										<td class="">
											<div align="left">
												<font color="<%=color%>"> <%=voPatientCollection.getRequisitionDate()%>
												</font>
											</div>
										</td>
									
										<td class="">
											<div align="left">
												<font color="<%=color%>"> <%=voPatientCollection.getTestName()%>
												</font>
											</div>
										</td>
										<td class="">
											<div align="left">
												<font color="<%=color%>"> <%=voPatientCollection.getPriority() == null
												? ""
												: voPatientCollection.getPriority()%>
												</font>
											</div>
										</td>
										<td class="">
											<div align="left">
												<font color="<%=color%>"> <img class="button" title="Show Instructions" src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>' tabindex="1" onclick="showInstructions5();">
												</font>
											</div>
										</td>
									</tr>
									<%
										}
									%>
								</table>
							</logic:present>
						</logic:notEmpty>
					</logic:empty>
				
			</div>
			<div class="col-12 pt-2 d-flex justify-content-center">
				<button type="button" class="btn btn-primary text-nowrap " id="nextDiv" style="cursor: pointer; display: none" tabindex="1" onclick="displaySamplePatDetails();">
					<span class=""></span> Next
				</button>
				
				<button type="button" class="btn btn-primary text-nowrap " id="saveDiv" style="cursor: pointer; display: none" onkeypress="if(event.keyCode==13) onSave();" tabindex="1" onclick="onSave();">
					<span class=""></span> Save
				</button>
				
				<button type="button" class="btn btn-primary text-nowrap " id="cancel" tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc();" tabindex="1" onclick="cancelFunc();">
					<span class=""></span> Cancel
				</button>
			</div>	
		
			
			
			<div align="left"> <his:status /> </div>
		
			<html:hidden name="InvPatientAcceptanceRespFB" property="labCode" />
			<html:hidden name="InvPatientAcceptanceRespFB" property="patType" />
		</html:form>
	</form>
	</div>
</div>
	
</body>