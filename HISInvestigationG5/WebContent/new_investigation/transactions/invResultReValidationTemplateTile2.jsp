
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>
<%@page import="new_investigation.vo.OnlinePatientAcceptanceVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.transactions.controller.fb.InvResultValidationRespFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />
<his:css src="/hisglobal/css/style.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/invPopupReport.css" />
<his:css src="/hisglobal/css/email.css" />
<his:css src="/hisglobal/css/textboxcss.css" />
<his:css src="/hisglobal/css/drop.css" />
<his:css src="/hisglobal/css/Cannedstyle.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/cannedMacroValidation.js" />
<his:javascript src="/hisglobal/js/cannedMacroAutocomplete.js" />
<his:javascript src="/new_investigation/js/reportsValidation.js" />
<his:javascript src="/new_investigation/js/onlinePatientAcceptance.js" />
<his:javascript src="/new_investigation/js/jquery-1.11.1.min.js" />
<his:javascript src="/new_investigation/js/jquery.validate.email.js" />
<his:javascript src="/new_investigation/js/additional-methods.min.js" />
<%-- <his:javascript src="/new_investigation/js/ckeditor/ckeditor.js" /> --%>
<script type="text/javascript" src="/HISInvestigationG5/new_investigation/js/ckeditor/ckeditor.js" charset="utf-8"></script>
<his:javascript src="/new_investigation/js/wysiwyg.js" />
<his:javascript src="/new_investigation/js/wysiwyg-settings.js" />
<his:javascript src="/hisglobal/js/css-pop-inv.js" />
<his:javascript src="/hisglobal/js/jquery-1.7.2.js" />
<his:javascript src="/new_investigation/js/saveToDraftJS.js" />
<%@include file="/hisglobal/invsnomed.html"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="new_investigation.vo.template.ResultEntryVO"%>
<%@page import="new_investigation.vo.InvResultValidationRespVO"%>
<%@page import="new_investigation.vo.template.ResultEntryVOGroupByValidatedBy"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="new_investigation.transactions.controller.fb.InvResultValidationRespFB"%>
<his:javascript src="/hisglobal/js/jquery-ui.js" />
<his:javascript src="/hisglobal/js/sweet-alert.min.js" />
<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<his:javascript src="/new_investigation/js/specialCharacterRemover.js" />
<his:javascript src="/new_investigation/media/fancybox/3.5.7/jquery.fancybox.min.js" />
<his:css src="/new_investigation/media/fancybox/3.5.7/jquery.fancybox.min.css" />
<!--Fontawesome Icons---->
<his:css src="/new_investigation/media/fontawesome/5.10.1/css/all.css" />
<his:javascript src="/new_investigation/js/invResultValidationTemplateTile.js" />
<his:css src="/new_investigation/media/services/css/invResultValidationTemplateTile2.css" />
<his:javascript src="/new_investigation/media/services/js/invResultValidationTemplateTile2.js" />
<%@page import="new_investigation.InvestigationConfig"%>
<html>
<head>
<meta charset="utf-8">
</head>
<style>
/* code removed from here to  invResultValidationTemplateTile2.css */
</style>
<script>

//code removed from here

//1st code removed from here to  invResultValidationTemplateTile2.js

function validateTodayOrDate()
{
	success=false;

    valFromDate=document.getElementsByName('fromDate')[0];
	valToDate=document.getElementsByName('toDate')[0];
	valFromCollDate=document.getElementsByName('fromCollDate')[0];
	valToCollDate=document.getElementsByName('toCollDate')[0];

	<%String systemdate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
	document.getElementsByName('sysDate')[0].value="<%=systemdate%>";
	valSysDate=document.getElementsByName('sysDate')[0];

	if(document.getElementsByName("searchBy")[0].checked==true){
		 if(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valToDate,valSysDate,2,"To Date","System Date"))
		    { success=true; }
	}else{
		if(compareDateCall(valFromCollDate,valToCollDate,2,"From Date","To Date") && compareDateCall(valToCollDate,valSysDate,2,"To Date","System Date"))
	    {  success=true; }
	}
    return success;
}

//2nd code removed from here to  invResultValidationTemplateTile2.js

var refrenceRangeColorCde = "";
<%Map<String, Map<String, List<String>>> mpDno = (Map<String, Map<String, List<String>>>) session.getAttribute("mapForRefRangeValidation");
	if (mpDno != null && mpDno.size() > 0) {
		String dno = (String) request.getAttribute("dnoForRangeValidation");
		String dnoListString = "";
		Iterator itr = mpDno.keySet().iterator();
		String dnoList = "";
		if (dno != null && !dno.equals("")) {
			List<String> dnoMapList = (List<String>) mpDno.get(dno);
			if (dnoMapList != null) {
				for (int i = 0; i < dnoMapList.size(); i++) {
					dnoListString += dnoMapList.get(i) + "@@@";
				}
			}
			System.out.println("str" + dnoListString);
		}
		if (!dnoListString.equals("")) {
			System.out.println("dnoListString" + dnoListString);%>
			refrenceRangeColorCde  = "<%=dnoListString%>";
			document.getElementsByName('dnoForRangeChange')[0].value = "<%=dnoListString%>";
<%} }%>


//3rd code removed from here to  invResultValidationTemplateTile2.js

</script>

<script> /* 4th code replace */ </script>
<!-- keypad shortcut functionality -->
<script> /* 5th code replaced */</script>
<script> /* 6th code replaced */ </script>
<script>

function actionOnSessionVariables() {
	var jsonResponse = jQuery.parseJSON('<%=session.getAttribute("resultValidationSessionJson")%>');
	'<%session.setAttribute("resultValidationSessionJson", null);%>'
		if(jsonResponse != null && jsonResponse.closeIframe=="1") {
			//window.parent.postMessage(jsonResponse, '*');

			var interWinEvent = new CustomEvent('iframeCustomEventForParent', { detail: jsonResponse })
			window.parent.document.dispatchEvent(interWinEvent);

			parent.jQuery.fancybox.getInstance().close();
		}
	}
</script>

<body onload="actionOnSessionVariables(); checkEntryType(); ">


<%

UserVO uservo77=ControllerUTIL.getUserVO(request);
										      String hospitalCode_new=uservo77.getHospitalCode();
				
%>
<script>
var hospitalcode_new=<%=hospitalCode_new%> ;
</script>

	<html:form action="/invResultReValidationResp">
		<html:hidden name="InvResultValidationRespFB" property="hmode" />
		<html:hidden name="InvResultValidationRespFB" property="echovar" />
		<html:hidden name="InvResultValidationRespFB" property="fileuploaddata" />
		<html:hidden name="InvResultValidationRespFB" property="fileuploaddatabase64" />
		<html:hidden name="InvResultValidationRespFB" property="isSaveToDraft" />
		<html:hidden name="InvResultValidationRespFB" property="iscallfromonloaddynamic" value="2" />
		<html:hidden name="InvResultValidationRespFB" property="isPatDetailPage" />
		<html:hidden name="InvResultValidationRespFB" property="selectedCheckbox" />
		<html:hidden name="InvResultValidationRespFB" property="showStatus" />
		<html:hidden name="InvResultValidationRespFB" property="currentPage" />
		<html:hidden name="InvResultValidationRespFB" property="patCRNo" />
		<html:hidden name="InvResultValidationRespFB" property="sysDate" />
		<html:hidden name="InvResultValidationRespFB" property="getSearchType" />
		<html:hidden name="InvResultValidationRespFB" property="generationType" />
		<html:hidden name="InvResultValidationRespFB" property="onLoadValue" />
		<input type="hidden" name="selectValuemapping" value="" />
		<input type="hidden" name="hyperLinkTableSession" value="" />

		<!-- canned popup  -->
		<div id="blanketcanned" style="display: none;"></div>
		<div id="popUpDiv5canned" style="display: none;" align="center">
			<his:TitleTag name="Canned Details">
				<img src='/HISInvestigationG5/hisglobal/css/close.png' onClick="closecanned();">
			</his:TitleTag>
			<table width="100%" id="allInstructionscanned">
				<tr>
					<td width="25%" class="tdfont">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> Canned Code </font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <html:text property="cannedCode" name="InvResultValidationRespFB"></html:text>
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfont">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> Canned Name </font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <html:text property="cannedName" name="InvResultValidationRespFB"></html:text> <input type="hidden"
								name="cannedvall"
							>
							</font>
						</div>
					</td>
				</tr>
			</table>
			<img src='/HISInvestigationG5/hisglobal/images/btn-sv.png' onClick="closeInstructionscanned();">
		</div>
		<!--  //end -->

		<%! boolean readOnly; %>
		<% this.readOnly = false; %>
		<logic:equal name="InvResultValidationRespFB" property="hmode" value="VIEW">
			<% this.readOnly = true; %>
		</logic:equal>
		<his:TitleTag name="Result Validation Process">	</his:TitleTag>
		<his:ContentTag>
			<%
				String fromDateLabel = "";
				String toDateLabel = "";
				String fromDateControl = "";
				String toDateControl = "";
			%>
			<bean:define name="InvResultValidationRespFB" property="fromDate" id="frDate" type="java.lang.String" />
			<bean:define name="InvResultValidationRespFB" property="toDate" id="tDate" type="java.lang.String" />
			<bean:define name="InvResultValidationRespFB" property="fromCollDate" id="frCollDate" type="java.lang.String" />
			<bean:define name="InvResultValidationRespFB" property="toCollDate" id="tCollDate" type="java.lang.String" />
			<%
				if (frDate == null || frDate.equalsIgnoreCase("")) {
							Date dt = (Date) session.getAttribute(Config.SYSDATEOBJECT);
							frDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
						}

						if (tDate == null || tDate.equalsIgnoreCase("")) {
							Date dt = (Date) session.getAttribute(Config.SYSDATEOBJECT);
							tDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
						}

						if (frCollDate == null || frCollDate.equalsIgnoreCase("")) {
							Date dt = (Date) session.getAttribute(Config.SYSDATEOBJECT);
							frCollDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
						}

						if (tCollDate == null || tCollDate.equalsIgnoreCase("")) {
							Date dt = (Date) session.getAttribute(Config.SYSDATEOBJECT);
							tCollDate = WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
						}
			%>
			<logic:equal name="InvResultValidationRespFB" property="showStatus" value="0">
				<his:SubTitleTag name="Acceptance Details">	</his:SubTitleTag>
				<!-- added by chandan -->
				<logic:present name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>">
					<table width="100%">
						<tr>
							<td class="tdfont" width="25%">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="sampleColl" />&nbsp;
									</font>
								</div>
							</td>
							<td class="tdfont" width="25%"><logic:notEqual name="InvResultValidationRespFB" property="isSampleAreaSelected" value="1">
									<span class="custom-dropdown small"> <html:select name="InvResultValidationRespFB" property="sampleAreaCode" tabindex="1" onchange="showSearchDiv(this)">
											<bean:define id="patSampleCollection" type="java.util.List" name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"></bean:define>

											<%
												int patTypeCount = 0;
												if (patSampleCollection.size() > 1) {

													for (int l = 0; l < patSampleCollection.size(); l++) {
														String obj = patSampleCollection.get(l).toString();
														System.out.println("remaining = " + obj.split("#")[1].split("]")[0]);
														if (obj.split("#")[1].split("]")[0].equals("2")) {
															patTypeCount++;
															System.out.println("count + 1");
														}
													}
											%>
											<html:option value="-1">Select Value</html:option>
											<% } if (patTypeCount == patSampleCollection.size()) { System.out.println("all"); %>
											<html:option value="-2#2">All</html:option>
											<% } else { %>
											<html:option value="-2">All</html:option>
											<% } %>
											<%--  <html:option value="-2">All</html:option> --%>
											<html:options collection="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>" property="value" labelProperty="label" />
										</html:select>
									</span>
								</logic:notEqual> <logic:equal name="InvResultValidationRespFB" property="isSampleAreaSelected" value="1">
									<div align="left">
										<bean:write name="InvResultValidationRespFB" property="sampleAreaName" />
										<html:hidden name="InvResultValidationRespFB" property="sampleAreaCode" />
									</div>
								</logic:equal></td>
							<td class="tdfont" width="25%"></td>
							<td class="tdfont" width="25%"></td>
						</tr>
					</table>
					<!-- end chandan -->
					<logic:equal name="InvResultValidationRespFB" property="isSampleAreaSelected" value="1">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfont">
									<div align="right">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="LabType" />&nbsp;
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont"><logic:present name="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select name="InvResultValidationRespFB" property="labCode" tabindex="1" onchange="labBased()">
													<html:option value="%">All</html:option>
													<html:options collection="<%=InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present></td>
								<td width="25%" class="tdfont">
									<div align="right">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="ResultValidation"
											/>&nbsp;
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont"><html:radio name="InvResultValidationRespFB" tabindex="1" property="newEntry" value="1"></html:radio> <bean:message key="validate" />&nbsp; <html:radio
										name="InvResultValidationRespFB" tabindex="1" property="newEntry" value="2"
									></html:radio> <bean:message key="modifyValidate" />&nbsp; <html:radio name="InvResultValidationRespFB" tabindex="1" property="newEntry" value="3"></html:radio> Drafts &nbsp;</td>
							</tr>
							<!-- changed by ashu -->
							<tr>
								<td width="25%" class="tdfont">
									<div align="right">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> </font> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="searchBy"
											/>&nbsp;&nbsp;&nbsp;
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont"><bean:message key="reqNoDate" />&nbsp;
								 <html:radio name="InvResultValidationRespFB" property="searchBy" onclick="showReqDate();callGetDetails();" value="1" >
								 </html:radio>   <bean:message key="sampleCollDateTest" />&nbsp;
									<html:radio name="InvResultValidationRespFB" property="searchBy" onclick="showCollDate();callGetDetails();" value="0"></html:radio>
								</td>
							</tr>
							<!-- changed by ashu -->
							<tr>
								<td class="tdfont" width="25%">
									<div id='divfromDate' style='' align="right">
										<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="fromDate"
											/>&nbsp;
										</font>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div id='divfromDateControl' style='' align="left">
										&nbsp;&nbsp;&nbsp;
										<his:date name='fromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>" />
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div id='divToDate' style='' align="right">
										<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
												key="toDate"
											/>&nbsp;
										</font>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div id='divToDateControl' style='' align="left">
										&nbsp;&nbsp;&nbsp;
										<his:date name='toDate' dateFormate="%d-%b-%Y" value="<%=tDate%>" />
									</div>
								</td>
							</tr>
							<!-- changed by ashu -->
							<tr>
								<td class="tdfont" width="25%">
									<div id='divfromCollDate' style='' align="right">
										<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <!-- updated by krishnan nema on 01/10/2018 -->
											<bean:message key="fromSampleCollDateAcceptance" />&nbsp;
										</font>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div id='divfromCollDateControl' style='' align="left">
										&nbsp;&nbsp;&nbsp;
										<his:date name='fromCollDate' dateFormate="%d-%b-%Y" value="<%=frCollDate%>" />
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div id='divToCollDate' style='' align="right">
										<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\"> </font> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <!-- updated by krishnan nema on 01/10/2018-->
											<bean:message key="toSampleCollDateAcceptance" />&nbsp;
										</font>
									</div>
								</td>
								<td class="tdfont" width="25%">
									<div id='divToCollDateControl' style='' align="left">
										&nbsp;&nbsp;&nbsp;
										<his:date name='toCollDate' dateFormate="%d-%b-%Y" value="<%=tCollDate%>" />
									</div>
								</td>
							</tr>
							<!-- changed by ashu -->
							<tr>
								<td width="25%" class="tdfont">
									<div align="right">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> </font> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="ResultEntryType" />&nbsp;
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont" colspan="3">
									<div align="left">
										&nbsp;&nbsp;&nbsp;
										<logic:equal name="InvResultValidationRespFB" property="generationType" value="P">
											<input type="radio" name="patientWise" onclick="getDetails(this)" checked="checked" value="P" />
										</logic:equal>
										<logic:notEqual name="InvResultValidationRespFB" property="generationType" value="P">
											<input type="radio" name="patientWise" id="patient" onclick="getDetails(this)" value="P" />
										</logic:notEqual>
										<bean:message key="PatientWise" />
										&nbsp;&nbsp;&nbsp;
										<logic:equal name="InvResultValidationRespFB" property="generationType" value="T">
											<input type="radio" name="testWise" onclick="getDetails(this)" checked="checked" value="T" />
										</logic:equal>
										<logic:notEqual name="InvResultValidationRespFB" property="generationType" value="T">
											<input type="radio" name="testWise" onclick="getDetails(this)" value="T" />
										</logic:notEqual>
										<bean:message key="testWise" />
										<logic:notEqual name="InvResultValidationRespFB" property="patientType" value="2">
								&nbsp;&nbsp;&nbsp;
								<logic:equal name="InvResultValidationRespFB" property="generationType" value="S">
												<input type="radio" name="sampleNoWise" onclick="getDetails(this)" checked="checked" value="S" />
											</logic:equal>
											<logic:notEqual name="InvResultValidationRespFB" property="generationType" value="S">
												<input type="radio" name="sampleNoWise" onclick="getDetails(this)" value="S" />
											</logic:notEqual>
											<bean:message key="sampleNoWise" />
										</logic:notEqual>
										<logic:notEqual name="InvResultValidationRespFB" property="patientType" value="2">
								&nbsp;&nbsp;&nbsp;
								<logic:equal name="InvResultValidationRespFB" property="generationType" value="L">
												<input type="radio" name="labNoWise" onclick="getDetails(this)" checked="checked" value="L" />
											</logic:equal>
											<logic:notEqual name="InvResultValidationRespFB" property="generationType" value="L">
												<input type="radio" name="labNoWise" onclick="getDetails(this)" value="L" />
											</logic:notEqual>
											<bean:message key="labNOWise" />
										</logic:notEqual>
										<logic:equal name="InvResultValidationRespFB" property="patientType" value="2">
																&nbsp;&nbsp;&nbsp;
								<logic:equal name="InvResultValidationRespFB" property="generationType" value="L">
												<input type="radio" name="labNoWise" onclick="getDetails(this)" checked="checked" value="L" />
											</logic:equal>
											<logic:notEqual name="InvResultValidationRespFB" property="generationType" value="L">
												<input type="radio" name="labNoWise" onclick="getDetails(this)" value="L" />
											</logic:notEqual>
											<bean:message key="labNOWiseAcc" />
										</logic:equal>
										&nbsp;&nbsp;&nbsp;
										<logic:equal name="InvResultValidationRespFB" property="generationType" value="TG">
											<input type="radio" name="testGroupWise" onclick="getDetails(this)" checked="checked" value="TG" />
										</logic:equal>
										<logic:notEqual name="InvResultValidationRespFB" property="generationType" value="TG">
											<input type="radio" name="testGroupWise" onclick="getDetails(this)" value="TG" />
										</logic:notEqual>
										<bean:message key="testGrpWise" />
										&nbsp;&nbsp;&nbsp;
										<logic:equal name="InvResultValidationRespFB" property="generationType" value="AP">
											<input type="radio" name="allPatient" onclick="getDetails(this)" checked="checked" value="AP" />
										</logic:equal>
										<logic:notEqual name="InvResultValidationRespFB" property="generationType" value="AP">
											<input type="radio" name="allPatient" onclick="getDetails(this)" value="AP" />
										</logic:notEqual>
										<bean:message key="allPatient" />
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfont">
									<div align="right" id="showOnLoad" style="display: none">
										<bean:message key="crNO" />
										&nbsp;
									</div>
									<div align="right">
										<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> </font> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <logic:equal
												name="InvResultValidationRespFB" property="generationType" value="P" >
												<bean:message key="crNO" />&nbsp;
								</logic:equal> <logic:equal name="InvResultValidationRespFB" property="generationType" value="T">
												<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="testName" />&nbsp;
								</logic:equal>
											<logic:notEqual name="InvResultValidationRespFB" property="patientType" value="2">
												<logic:equal name="InvResultValidationRespFB" property="generationType" value="L">
													<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
													<bean:message key="fromLabNo" />&nbsp;
								</logic:equal>
											</logic:notEqual> <logic:equal name="InvResultValidationRespFB" property="patientType" value="2">
												<logic:equal name="InvResultValidationRespFB" property="generationType" value="L">
													<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
													<bean:message key="fromAccessionNo" />&nbsp;
								</logic:equal>
											</logic:equal> <logic:equal name="InvResultValidationRespFB" property="generationType" value="S">
												<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="fromSampleNo" />&nbsp;
								</logic:equal> <logic:equal name="InvResultValidationRespFB" property="generationType" value="TG">
												<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="tstgrpName" />&nbsp;
								</logic:equal>
										</font>
									</div>
								</td>
								<%
									UserVO uservo = ControllerUTIL.getUserVO(request);
														Date todayDateobj = new Date();
														SimpleDateFormat dateob = new SimpleDateFormat("yy");
														String strDate = dateob.format(todayDateobj);
														String hospitalCode = uservo.getHospitalCode();
														String val = uservo.getHospitalCode() + strDate;
								%>
								<td width="25%" class="tdfont">
									<div align="left" id="patientwise">
										&nbsp;&nbsp;&nbsp; <input type="text" id="textBoxCss" name="tempPatCRNo" value="<%=val%>" maxlength="15" size="20" onkeypress="return validateNumeric(event,this)" tabindex="1">
									</div> <logic:present name="<%=InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left" id="testwise" style="display: none;">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select name="InvResultValidationRespFB" property="testWiseCode" tabindex="1">
													<html:option value="-1">Select Value</html:option>
													<html:options collection="<%=InvestigationConfig.TEST_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present> <logic:present name="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left" id="samplenowise" style="display: none;">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select name="InvResultValidationRespFB" property="fromSampleNo" tabindex="1">
													<html:option value="-1">Select Value</html:option>
													<html:options collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present> <logic:present name="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left" id="labnowise" style="display: none;">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select name="InvResultValidationRespFB" property="fromLabNo" tabindex="1">
													<html:option value="-1">Select Value</html:option>
													<html:options collection="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present> <logic:present name="<%=InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left" id="testGrpWise" style="display: none;">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select name="InvResultValidationRespFB" property="testGroupCodeWise" tabindex="1">
													<html:option value="-1">Select Value</html:option>
													<html:options collection="<%=InvestigationConfig.TEST_GROUP_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present>
								</td>
								<td width="25%" class="tdfont">
									<div align="left" id="patientwisename">
										Search Name&nbsp;&nbsp;&nbsp;<input type="text" id="textBoxCss" name="tempPatName" maxlength="20" size="20" onkeypress="return validateAlphabetsOnly(event,this)" tabindex="1">
									</div>
									<div align="right" id="toLabSampleNo" style="display: none;">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<logic:notEqual name="InvResultValidationRespFB" property="patientType" value="2">
												<logic:equal name="InvResultValidationRespFB" property="generationType" value="L">
													<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
													<bean:message key="toLabNo" />&nbsp;
								    </logic:equal>
											</logic:notEqual> <logic:equal name="InvResultValidationRespFB" property="patientType" value="2">
												<logic:equal name="InvResultValidationRespFB" property="generationType" value="L">
													<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
													<bean:message key="toAccessionNo" />&nbsp;
								    </logic:equal>
											</logic:equal> <logic:equal name="InvResultValidationRespFB" property="generationType" value="S">
												<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> * </font>
												<bean:message key="toSampleNo" />&nbsp;
								</logic:equal>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont"><logic:present name="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left" id="labnowise2" style="display: none;">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select name="InvResultValidationRespFB" property="toLabNo" tabindex="1">
													<html:option value="-1">Select Value</html:option>
													<html:options collection="<%=InvestigationConfig.LABNO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present> <logic:present name="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>">
										<div align="left" id="samplenowise2" style="display: none;">
											&nbsp;&nbsp; <span class="custom-dropdown small"> <html:select name="InvResultValidationRespFB" property="toSampleNo" tabindex="1">
													<html:option value="-1">Select Value</html:option>
													<html:options collection="<%=InvestigationConfig.SAMPLENO_WISE_COMBO_FOR_RESULT_ENTRY%>" property="value" labelProperty="label" />
												</html:select>
											</span>
										</div>
									</logic:present></td>
							</tr>
							<tr>
								<th class="tdfont" align="left" colspan="4">
									<div align="center">
										<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' id="gob" style="cursor: pointer" onkeypress="if(event.keyCode==13) onClickGO('<%=hospitalCode%>')"
											onclick="onClickGO('<%=hospitalCode%>')" tabindex="1"
										> <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="gob" style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelFunc()" onclick="cancelFunc()"
											tabindex="1"
										>
									</div>
								</th>
							</tr>
						</table>
					</logic:equal>
				</logic:present>
			</logic:equal>
		</his:ContentTag>
		<%
			boolean flag = false;
		%>
		<%
			//Pagination Logic
				PaginationFB fbPage = new PaginationFB();
				pageContext.setAttribute("fbPagination", fbPage);
				fbPage.setCurrentPage(((InvResultValidationRespFB) request.getAttribute("InvResultValidationRespFB"))
						.getCurrentPage());
				fbPage.setObjArrName(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
				fbPage.setAppendInTitle("List ");
				int maxRecord = 15;
				fbPage.setMaxRecords(maxRecord);
		%>
		<logic:equal name="InvResultValidationRespFB" property="showStatus" value="0">
			<his:PaginationTag name="fbPagination"></his:PaginationTag>
			<logic:present name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
				<%
					flag = true;
				%>
				<table width="100%" bgcolor="#EBEBEB">
					<tr>
						<td width="3%" align="left"><b> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <input type="checkbox" id="selectAllCheckbox" onclick="allSelected()" />
							</font>
						</b></td>
						<td width="3%">
							<%
								List<InvResultValidationRespVO> lstPatVO = (List<InvResultValidationRespVO>) session
													.getAttribute(
															InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
											int i, size = 0, total;
											if (lstPatVO != null && lstPatVO.size() > 0)
												size = lstPatVO.size();
							%> <img class="button" title="Show All Test Details" src='<his:path src="/hisglobal/images/plusinv.png"/>' id="showAllTest" tabindex="1" onclick="displayAllTest('<%=size%>')"> <img
							class="button" title="Hide All Test Details" src='<his:path src="/hisglobal/images/Minus.png"/>' id="hideAllTest" style="display: none;" tabindex="1" onclick="hideAllTests('<%=size%>')"
						>
						</td>
						<td width="8%" align="left"><b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="crNO" />
							</font></b></td>
						<td width="8%" align="left"><b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="patientName" /></font></b></td>
						<td width="6%" align="left" style="display:;"><b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="age/gender" />
							</font></b></td>
						<td width="10%" align="center"><b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="departmentunit" /></font></b></td>
						<td width="10%" align="left"><b> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="TestName" /></font></b></td>
						<td width="10%" align="left"><b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="labName" /></font></b></td>
						<!-- updated by krishnan nema on 28/09/2018 -->
						<logic:notEqual name="InvResultValidationRespFB" property="patientType" value="2">
							<td width="10%" align="left"><b> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="sampLabNo" /></font></b></td>
						</logic:notEqual>
						<logic:equal name="InvResultValidationRespFB" property="patientType" value="2">
							<td width="10%" align="left"><b> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="accessionNowise" /></font></b></td>
						</logic:equal>
						<%-- 							<td width="9%" align="left"><b> <font color="#000000"
									size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
											key="sampLabNo" /></font></b></td> --%>
						<!-- <td width="7%" align="left"><b> <font color="#000000" -->
						<td width="10%" align="left"><b> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="patStatus" /></font></b></td>
						<td width="5%" align="left"><b> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> Send To Machine</font></b></td>
						<!-- commented by PrashantMi PITR -->
						<!-- <td width="2%"> -->
						<!-- changed 10% to 8% by PrashantMi PITR-->
						<td width="8%" align="left"><b> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Machine</font></b></td>
						<%-- <td width="3%">


							 <img class="button" title="Show All Test Details"
								src='<his:path src="/hisglobal/images/plusinv.png"/>'
								id="showAllTest" tabindex="1"
								onclick="displayAllTest('<%=size%>')"> <img class="button"
								title="Hide All Test Details"
								src='<his:path src="/hisglobal/images/Minus.png"/>'
								id="hideAllTest" style="display: none;" tabindex="1"
								onclick="hideAllTests('<%=size %>')">


							</td> --%>
						<td width="5%" align="left"><b> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Template Preview</font></b></td>
						<td width="4%" align="left"><b> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Compare</font></b></td>
					</tr>
				</table>
				<logic:empty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
					<center>
						<font color="red" size="4"> <bean:message key="noRecord" /></font>
					</center>
				</logic:empty>
				<bean:define name="InvResultValidationRespFB" property="startDisplay" id="startDisplay" type="java.lang.Integer" />
				<bean:define name="InvResultValidationRespFB" property="hideDisplay" id="hideDisplay" type="java.lang.Integer" />
				<logic:notEmpty name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
					<table width="100%">
						<%
							int startIndex = ((Integer) request.getAttribute(PaginationTag.PAGINATION_START_INDEX))
													.intValue();
											int endIndex = ((Integer) request.getAttribute(PaginationTag.PAGINATION_END_INDEX))
													.intValue();

											Map<String, List<String>> dnoMap = new HashMap();
											startDisplay = 1;
											hideDisplay = 1;
											String grpCode = "";

											for (int j = startIndex; j <= endIndex; j++) {
												int l = j;
												boolean firstTimeTravesall = true;

												if (j < size) {
													InvResultValidationRespVO voPat = lstPatVO.get(j);
													String chkVal = voPat.getPatCRNo() + "#" + voPat.getRequisitionNo() + "#"
															+ voPat.getRequisitionDNo() + "#" + voPat.getGroupCode();
													String chkSendToMachineVal = voPat.getPatCRNo() + "#" + voPat.getRequisitionDNo()
															+ "#" + voPat.getTempSampleNo();
													String isMachineBasedTest = voPat.getIsMachineBasedTest();
													List<String> mapList = new ArrayList();
													String value = voPat.getRequisitionNo() + voPat.getGroupCode();

													String color = "blue";
													int reportChangeValue = 0;
													if (voPat.getReportChangeFlag() != null)
														reportChangeValue = Integer.parseInt(voPat.getReportChangeFlag());

													switch (reportChangeValue) {
														case 1 :
															color = "red"; // report change addendum
															break;
														case 2 :
															color = "red"; //  report change addendum
															break;
														case 3 :
															color = "red"; // report change addendum
															break;
														default :
															color = "blue";
															break;
													}

													if (voPat.getGroupCode() != null) {

														grpCode += '&';
														String[] SplitGrpCode = grpCode.split("&");
														int length = SplitGrpCode.length;
														if (SplitGrpCode.length > 1)
															for (int x = 0; x < SplitGrpCode.length; x++) {
																if (SplitGrpCode[x]
																		.equals(voPat.getRequisitionNo() + voPat.getGroupCode())) {
																	firstTimeTravesall = false;
																} else {
																	firstTimeTravesall = true;
																}
															}

														grpCode += voPat.getRequisitionNo() + voPat.getGroupCode();
													}

													if (firstTimeTravesall) {
						%>
						<tr>
							<td width="3%" align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <input type="checkbox" class="jpCheckbox" name="chkSamplePatient"
									value='<%=chkVal%>' onclick="ValidateSameCrNo(this)"
								>
							</font></td>
							<td width="3%">
								<%
									String showTest = "showTestDetails(" + j + ",'" + value + "')";
																String hideTest = "hideTestDetails(" + j + ",'" + value + "')";
								%><font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <img class="button" title="Show Test Details" src='<his:path src="/hisglobal/images/plusinv.png"/>'
									id="showTest<%=j%>" tabindex="1" onclick="<%=showTest%>"
								> <img class="button" title="Hide Test Details" src='<his:path src="/hisglobal/images/Minus.png"/>' id="hideTest<%=j%>" style="display: none;" tabindex="1" onclick="<%=hideTest%>"></font>
							</td>
							<td width="8%" align="left">
								<div>
									<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatCRNo()%></font>
								</div>
							</td>
							<td width="8%" align="left">
								<div>
									<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatName()%></font>
								</div>
							</td>
							<td width="6%" align="left" style="display:;">
								<!-- <td width="8%" align="left"> -->
								<div>
									<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatAge() + "/" + voPat.getPatGender()%></font>
								</div>
							</td>
							<td width="10%" align="left">
								<div>
									<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatUnitName()%></font>
								</div>
							</td>
							<td width="10%" align="left">
								<div>
									<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getGroupName().equals("NA")
												? voPat.getTestName()
												: voPat.getGroupName()%>
									</font>
									<!--Added by PrashantMi for Repeat R -->
									<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getIsRepeat() == null || voPat.getIsRepeat().equals("0")
												|| voPat.getIsRepeat().equals("1")
														? ""
														: " [R]"/* +voPat.getIsRepeat() */%>
									</font>
								</div>
							</td>
							<td width="10%" align="left">
								<div>
									<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatLabName()%></font>
								</div>
							</td>
							<td width="10%" align="left">
								<div>
									<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getTempSampleNo() == null
												? voPat.getLabNo()
												: voPat.getTempSampleNo()%></font>
								</div>
							</td>
							<!-- 								<td width="7%" align="left">
									<div> -->
							<td width="10%" align="left">
								<div>
									<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getPatStatus()%></font>
								</div>
							</td>
							<td width="5%" align="left">
								<div>
									<%
										if (isMachineBasedTest != null && isMachineBasedTest.equals("1")) {
									%>
									<input type="checkbox" class="jpCheckbox1" name="chkSendToMachine" value='<%=chkSendToMachineVal%>'>
									<%
										} else {
									%>
									<input type="checkbox" class="jpCheckbox1" name="chkSendToMachine" value='<%=chkSendToMachineVal%>' disabled="disabled">
									<%
										}
									%>
								</div>
							</td>
							<td width="8%" align="left">
								<div>
									<font color="<%=color%>" size="2" <%if (voPat.getMachineCode() != null && voPat.getMachineCode().equals("-1")) {
											voPat.setMachineCode(null);
										}%>
										face="Verdana, Arial, Helvetica, sans-serif"
									> <%=voPat.getMachineCode() == null ? "-" : voPat.getMachineCode()%></font>
								</div>
							</td>
							<%-- 	<td width="3%">
									<%  String showTest="showTestDetails("+j+",'"+value+"')";
						 String hideTest="hideTestDetails("+j+",'"+value+"')";

			 %><font color="<%=color%>" size="2"
											face="Verdana, Arial, Helvetica, sans-serif">	 <img class="button" title="Show Test Details"
									src='<his:path src="/hisglobal/images/plusinv.png"/>'
									id="showTest<%=j%>" tabindex="1" onclick="<%=showTest%>">
									<img class="button" title="Hide Test Details"
									src='<his:path src="/hisglobal/images/Minus.png"/>'
									id="hideTest<%=j%>" style="display: none;" tabindex="1"
									onclick="<%=hideTest %>"></font>






								</td> --%>
							<!-- added by chandan -->
							<td width="5%" align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <a href="#" onclick="displaySamplePatDetailsPopUp('<%=chkVal%>')">View</a> <%-- <input
										type="checkbox" class="jpCheckbox" name="chkSamplePatient1"
										value='<%=chkVal%>' > --%>
							</font></td>
							<!-- Added by PrashantMi PITR-->
							<td width="4%" align="left"><b> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <a
										onclick="openInvTrackingToCompare('<%=voPat.getPatCRNo()%>','<%=voPat.getTestCode()%>','<%=voPat.getGroupCode()%>','<%=voPat.getRequisitionDNo()%>','<%=voPat.getRequisitionNo()%>');"
									> <span class="text-primary"><img height="25" width="25" src='media/images/compare.svg'></span>
									</a>
								</font>
							</b></td>
							<%
								if (voPat.getDeptReport() != null) {
							%>
							<td width="1%" align="left"><img title='Show Department Report' src='/HISInvestigationG5/hisglobal/images/add_remarks_sml.jpg' onclick="printReport('<%=voPat.getDeptReport()%>')"></td>
							<%
								}
							%>
						</tr>
						<%
							}
						%>
						<tr>
							<th colspan="1"></th>
							<th colspan="7">
								<table bgcolor="#F8F8F8" style="display: none;" width="100%" id="headings<%=j%>" border="1">
									<tr>
										<th width="20%"><font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <u><bean:message key="TestParaCombo" /></u></font></th>
										<th width="20%"><font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"><u><bean:message key="TestParaValue" /></u></font></th>
										<th width="20%"><font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif"><u><bean:message key="refRange" /></u></font></th>


									</tr>
									<!-- to display test and its value -->
									<%
										String[] parameters = voPat.getTestParameterName().split("`");
																int paraSize = parameters.length;

																System.out.println(
																		"reqdno" + voPat.getRequisitionDNo() + "testcode" + voPat.getTestCode());

																for (int iterate = 0; iterate < paraSize; iterate++) {
																	String[] paraValues = parameters[iterate].split("#@");
																	String paracode = paraValues[0];
																	String paraName = paraValues[1];
																	String refRange = "";
																	if (paraValues.length > 2)
																		refRange = paraValues[2];
																	String displayRef = "";
																	String rangeTypeFinal = "";
																	String[] refreValueFinal = null;

																	//updated by krishnan nema on 17/10/2018
																	String paraEntry = "";
																	if (paraValues.length > 3) {
																		paraEntry = paraValues[3];
																		if (paraValues[3].equals("--")) {
																			paraEntry = "";
																		} else {

																		}
																	}

																	if (!paraEntry.contains("<"))
																		paraEntry = paraEntry.replace("\r\n", "<br>");
																	if (paraEntry.contains("$"))
																		paraEntry = paraEntry.replace("$", "<br>");

																	boolean flagg = false;
																	if (refRange != null || !refRange.equals("")) {
																		refRange = refRange.replace("$", "@");
																		String[] refValues = refRange.split("@");
																		refreValueFinal = refValues;
																		if (refValues.length > 1) {
																			String checkRangetyp = refValues[0];
																			rangeTypeFinal = checkRangetyp;
																			if (checkRangetyp.equals("1")) {
																				String highValue = refValues[1];

																				String lowValue = refValues[2];

																				if ((highValue.matches("\\d*\\.?\\d+"))
																						&& (lowValue.matches("\\d*\\.?\\d+"))) {

																					flagg = true;
																				}
																				String highValueUom = refValues[3];
																				String lowValueUom = refValues[4];
																				displayRef = lowValue + " " + lowValueUom + " - " + highValue + " "
																						+ highValueUom;
																			} else if (checkRangetyp.equals("2")) {

																				String rangetyp = ">";

																				String tovalue = refValues[2];
																				String tovalueunit = refValues[1];

																				if ((tovalue.matches("\\d*\\.?\\d+"))) {

																					flagg = true;
																				}

																				displayRef = rangetyp + " " + tovalue + "  " + tovalueunit;

																			}

																			else if (checkRangetyp.equals("3")) {
																				String rangetyp = ">=";

																				String tovalue = "";
																				if (refValues.length > 2)
																					tovalue = refValues[2];
																				String tovalueunit = "";
																				if (refValues.length > 1)
																					tovalueunit = refValues[1];

																				if ((tovalue.matches("\\d*\\.?\\d+"))) {

																					flagg = true;
																				}

																				displayRef = rangetyp + " " + tovalue + "  " + tovalueunit;

																			} else if (checkRangetyp.equals("4")) {
																				String rangetyp = "<";

																				String tovalue = "";
																				if (refValues.length > 2)
																					tovalue = refValues[2];
																				String tovalueunit = "";
																				if (refValues.length > 1)
																					tovalueunit = refValues[1];
																				if ((tovalue.matches("\\d*\\.?\\d+"))) {

																					flagg = true;
																				}
																				displayRef = rangetyp + " " + tovalue + "  " + tovalueunit;

																			} else if (checkRangetyp.equals("5")) {
																				String rangetyp = "<=";

																				String tovalue = "";
																				if (refValues.length > 2)
																					tovalue = refValues[2];
																				String tovalueunit = "";
																				if (refValues.length > 1)
																					tovalueunit = refValues[1];
																				if ((tovalue.matches("\\d*\\.?\\d+"))) {

																					flagg = true;
																				}
																				displayRef = rangetyp + " " + tovalue + "  " + tovalueunit;

																			}

																		}
																	} else
																		displayRef = "";

																	/*  String paraEntry=paraValues[3];
																	if(paraValues[3].equals("--")  )
																	{
																		 paraEntry="";
																	}
																	else
																	{

																	}


																	if(!paraEntry.contains("<"))
																	paraEntry=paraEntry.replace("\r\n","<br>");
																	if(paraEntry.contains("$"))
																		paraEntry=paraEntry.replace("$","<br>"); */

																	//updated by krishnan nema on 17/10/2018

																	boolean numeric = false;
																	//	try {
																	//Double num = Double.parseDouble(paraEntry);
																	if (paraEntry.matches("\\d*\\.?\\d+")) {
																		numeric = true;
																	}
																	//} catch (NumberFormatException e)
																	else {
																		numeric = false;
																	}

																	if (numeric && flagg) {

																		if (refreValueFinal != null) {
																			if (rangeTypeFinal.equals("1")) {

																				String highValue = "";
																				if (refreValueFinal.length > 1)
																					highValue = refreValueFinal[1];
																				String lowValue = "";
																				if (refreValueFinal.length > 2)
																					lowValue = refreValueFinal[2];

																				if ((Float.valueOf(paraEntry) > Float.valueOf(highValue))
																						|| (Float.valueOf(paraEntry) < Float.valueOf(lowValue))) {
																					String valu = voPat.getRequisitionDNo() + "#" + "null"
																							+ "#template#" + voPat.getTestCode() + paracode;
																					mapList.add(valu);
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										} else {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										}

																			} else if (rangeTypeFinal.equals("2")) {

																				String tovalue = refreValueFinal[2];
																				if ((Float.valueOf(paraEntry) < Float.valueOf(tovalue))) {
																					String valu = voPat.getRequisitionDNo() + "#" + "null"
																							+ "#template#" + voPat.getTestCode() + paracode;
																					mapList.add(valu);
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										} else {
									%>
									<%
										if (Float.compare(Float.valueOf(paraEntry),
																							Float.valueOf(tovalue)) == 0) {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										} else {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										}
									%>
									<%
										}

																			} else if (rangeTypeFinal.equals("3")) {

																				String tovalue = refreValueFinal[2];
																				if ((Float.valueOf(paraEntry) <= Float.valueOf(tovalue))) {
																					String valu = voPat.getRequisitionDNo() + "#" + "null"
																							+ "#template#" + voPat.getTestCode() + paracode;
																					mapList.add(valu);
									%>
									<%
										if (Float.compare(Float.valueOf(paraEntry),
																							Float.valueOf(tovalue)) == 0) {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										} else {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										}
									%>
									<%
										} else {
									%>
									<%
										if (Float.compare(Float.valueOf(paraEntry),
																							Float.valueOf(tovalue)) == 0) {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										} else {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										}
									%>
									<%
										}

																			} else if (rangeTypeFinal.equals("4")) {

																				String tovalue = refreValueFinal[2];
																				if ((Float.valueOf(paraEntry) > Float.valueOf(tovalue))) {
																					String valu = voPat.getRequisitionDNo() + "#" + "null"
																							+ "#template#" + voPat.getTestCode() + paracode;
																					mapList.add(valu);
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										} else {
									%>
									<%
										if (Float.compare(Float.valueOf(paraEntry),
																							Float.valueOf(tovalue)) == 0) {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										} else {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										}
									%>
									<%
										}

																			} else if (rangeTypeFinal.equals("5")) {

																				String tovalue = refreValueFinal[2];
																				if ((Float.valueOf(paraEntry) >= Float.valueOf(tovalue))) {
																					String valu = voPat.getRequisitionDNo() + "#" + "null"
																							+ "#template#" + voPat.getTestCode() + paracode;
																					mapList.add(valu);
									%>
									<%
										if (Float.compare(Float.valueOf(paraEntry),
																							Float.valueOf(tovalue)) == 0) {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										} else {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										}
									%>
									<%
										} else {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										}

																			} else {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										}

																		} else {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%></font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										}
																	} else {
									%>
									<tr>
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraName%></font></th>
										<th id="paraEntryColor" width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=paraEntry%> <%
 	if (paraEntry.equals("File Uploaded")) {
 										String valu1 = voPat.getRequisitionDNo() + "#" + "null" + "#template#"
 												+ voPat.getTestCode() + paracode;
 %> <a id="view<%=valu1%>" class="view@@<%=valu1%>" href="#" onclick="myuploadFunction(this)"> View File</a> <%
 	}
 %>
										</font></th>
										<th width="20%"><%=displayRef%></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										}
																}

																dnoMap.put(voPat.getRequisitionDNo(), mapList);
									%>
									<%
										if (voPat.getFinalRemarkReqd() != null && voPat.getFinalRemarkReqd().equals("1")) {
									%>
									<tr>
									
											                 	<%
		  				if(voPat.getFinalRemarkString()==null)
		  				{
		  					voPat.setFinalRemarkString("");
		  				}
		  				%> 
		  				
										<th width="20%"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> Comments</font></th>
								
																	<th id="paraEntryColor" width="20%"><font color="#FF0000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getFinalRemarkString()==null?"":voPat.getFinalRemarkString().replaceAll("&lt;","<").replaceAll("&gt;",">") %></font>
								</th>
										<th width="20%"></th>
										<!-- 	<th width="20%">ml</th> -->
									</tr>
									<%
										}
									%>
								</table>
							</th>
						</tr>
						<!--end to display test and its value  -->
						<%
							}
											}

											session.setAttribute("mapForRefRangeValidation", dnoMap);
						%>
						<input type="hidden" name="chkValue" value="<%=grpCode%>" />
					</table>
				</logic:notEmpty>
			</logic:present>
		</logic:equal>
		<logic:present name="<%=InvestigationConfig.LIST_RESULT_VALIDATION_RESP_TEST_TEMPLATE_VO%>">
			<div class="subDivStyle">
				<his:SubTitleTag name="Requisition Forms">
					<img class="button" title="Show Patinet Details" src='<his:path src="/hisglobal/images/plusinv.png"/>' id="reqformshow" tabindex="1" onclick="f1()">
					<img class="button" title="Hide Patient Details" src='<his:path src="/hisglobal/images/Minus.png"/>' id="reqformhide" tabindex="1" onclick="f2()">
				</his:SubTitleTag>
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
				//Map<String,List<InvResultValidationRespVO>> _mpp=(Map<String,List<InvResultValidationRespVO>>)session.getAttribute(InvestigationConfig.LIST_RESULT_VALIDATION_RESP_TEST_TEMPLATE_VO);
						Map<String, List<ResultEntryVO>> _mpp = (Map<String, List<ResultEntryVO>>) session
								.getAttribute(InvestigationConfig.LIST_RESULT_VALIDATION_RESP_TEST_TEMPLATE_VO);
						Map<String, String> pidmap = (Map<String, String>) session
								.getAttribute(InvestigationConfig.LIST_RESULT_VALIDATION_RESP_TEST_TEMPLATE_VO_PID);

						boolean sameSampleNO = false;

						Map<String, List<String>> mpAutoList = (Map<String, List<String>>) session
								.getAttribute(InvestigationConfig.AUTOCOMPLETE_LIST_VALUES);
						List<String> finalRemarkCodeList = new ArrayList<String>();

						if (mpAutoList != null) {
							Iterator itrAuto = mpAutoList.keySet().iterator();
							while (itrAuto.hasNext()) {
								String autoCode = (String) itrAuto.next();
								String autoList = (String) mpAutoList.get(autoCode).get(0);

								String hiddenCode = "#hiddenid" + autoCode;
								String hid = "hiddenid" + autoCode;
								autoCode = "#" + autoCode;
			%>
			<script>

var objList = JSON.parse('<%=autoList%>');
//alert(objList);
var autoId='<%=autoCode%>';
var hiddenId='<%=hiddenCode%>';
//alert("autoid"+autoId);

		generateAuto(objList,autoId,hiddenId);

			</script>
			<input type="hidden" id='<%=hid%>' name="userCannedCode" />
			<%
				}
						}

						Iterator itrr = _mpp.keySet().iterator();
						int size = 0;
						int i = 0;

						while (itrr.hasNext()) {
							boolean firstTimeTravesa = true;
							boolean firstTimeRemark = true;
							String pidnoo = "";

							String sampleNo = (String) itrr.next();

							if (pidmap != null && pidmap.size() > 0) {
								if (pidmap.containsKey(sampleNo)) {

									pidnoo = (String) pidmap.get(sampleNo);
								}

							}

							//List<InvResultValidationRespVO> lstInvResultValidationRespVO=_mpp.get(sampleNo);
							List<ResultEntryVO> lstInvResultValidationRespVO = _mpp.get(sampleNo);
							if (lstInvResultValidationRespVO != null && lstInvResultValidationRespVO.size() > 0)
								size = lstInvResultValidationRespVO.size();
							if (size > 1)
								sameSampleNO = true;
							//InvResultValidationRespVO invResultValidationRespVO=new InvResultValidationRespVO();
							ResultEntryVO invResultValidationRespVO = new ResultEntryVO();
							HashMap<String, String> reqNoGroupCodeChkTestValueMap = new HashMap<String, String>();
							for (int k = 0; k < size; k++) {
								invResultValidationRespVO = new ResultEntryVO();
								invResultValidationRespVO = lstInvResultValidationRespVO.get(k);
								ResultEntryVO actualRvo = invResultValidationRespVO.getResultEntryVOListValidatedBy()
										.get(0);
								String chkTestValue = "";
								if (actualRvo.getDynamnicTemplateResultEntryGroup().equals("1")
										&& actualRvo.isDoCreateTemplate() == true && actualRvo.getGroupCode() != null) {
									for (int m = 0; m < size; m++) {
										//InvResultValidationRespVO tempInvRVO=lstInvResultValidationRespVO.get(m);
										ResultEntryVO tempInvRVO = lstInvResultValidationRespVO.get(m);
										String key = tempInvRVO.getRequisitionNo() + tempInvRVO.getGroupCode();
										if (reqNoGroupCodeChkTestValueMap.containsKey(key)) {
											String tempChkTestValue = tempInvRVO.getPatCRNo() + "#"
													+ tempInvRVO.getRequisitionNo() + "#" + tempInvRVO.getRequisitionDNo()
													+ "#" + tempInvRVO.getTestCode() + "#" + tempInvRVO.getSampleNo() + "#"
													+ tempInvRVO.getLabCode() + "#" + tempInvRVO.getPatAge() + "#"
													+ tempInvRVO.getPatGender() + "#" + tempInvRVO.getReportAvailableAfter()
													+ "#" + tempInvRVO.getPatVisitDat() + "#" + tempInvRVO.getPatVisitNo()
													+ "#" + tempInvRVO.getLabNo() + "#" + tempInvRVO.getEpisodeCode() + "#"
													+ tempInvRVO.getDepartmentcode() + "#" + tempInvRVO.getPatdeptunitcode()
													+ "#" + tempInvRVO.getRequisitionTypeCode() + "#"
													+ tempInvRVO.getUomCode() + "#" + tempInvRVO.getTestName() + "#"
													+ tempInvRVO.getPatLabName() + "#" + tempInvRVO.getSampleName() + "#"
													+ tempInvRVO.getTempSampleNo() + "#" + tempInvRVO.getGroupCode() + "#"
													+ tempInvRVO.getDynamicTemplatePrintedGroup();
											String oldChkTestValue = reqNoGroupCodeChkTestValueMap.get(key);
											oldChkTestValue += "!" + tempChkTestValue;
											reqNoGroupCodeChkTestValueMap.put(key, oldChkTestValue);
										} else {
											String tempChkTestValue = tempInvRVO.getPatCRNo() + "#"
													+ tempInvRVO.getRequisitionNo() + "#" + tempInvRVO.getRequisitionDNo()
													+ "#" + tempInvRVO.getTestCode() + "#" + tempInvRVO.getSampleNo() + "#"
													+ tempInvRVO.getLabCode() + "#" + tempInvRVO.getPatAge() + "#"
													+ tempInvRVO.getPatGender() + "#" + tempInvRVO.getReportAvailableAfter()
													+ "#" + tempInvRVO.getPatVisitDat() + "#" + tempInvRVO.getPatVisitNo()
													+ "#" + tempInvRVO.getLabNo() + "#" + tempInvRVO.getEpisodeCode() + "#"
													+ tempInvRVO.getDepartmentcode() + "#" + tempInvRVO.getPatdeptunitcode()
													+ "#" + tempInvRVO.getRequisitionTypeCode() + "#"
													+ tempInvRVO.getUomCode() + "#" + tempInvRVO.getTestName() + "#"
													+ tempInvRVO.getPatLabName() + "#" + tempInvRVO.getSampleName() + "#"
													+ tempInvRVO.getTempSampleNo() + "#" + tempInvRVO.getGroupCode() + "#"
													+ tempInvRVO.getDynamicTemplatePrintedGroup();
											reqNoGroupCodeChkTestValueMap.put(key, tempChkTestValue);
										}
									}
									chkTestValue = reqNoGroupCodeChkTestValueMap
											.get(invResultValidationRespVO.getRequisitionNo()
													+ invResultValidationRespVO.getGroupCode());
								} else {
									chkTestValue = invResultValidationRespVO.getPatCRNo() + "#"
											+ invResultValidationRespVO.getRequisitionNo() + "#"
											+ invResultValidationRespVO.getRequisitionDNo() + "#"
											+ invResultValidationRespVO.getTestCode() + "#"
											+ invResultValidationRespVO.getSampleNo() + "#"
											+ invResultValidationRespVO.getLabCode() + "#"
											+ invResultValidationRespVO.getPatAge() + "#"
											+ invResultValidationRespVO.getPatGender() + "#"
											+ invResultValidationRespVO.getReportAvailableAfter() + "#"
											+ invResultValidationRespVO.getPatVisitDat() + "#"
											+ invResultValidationRespVO.getPatVisitNo() + "#"
											+ invResultValidationRespVO.getLabNo() + "#"
											+ invResultValidationRespVO.getEpisodeCode() + "#"
											+ invResultValidationRespVO.getDepartmentcode() + "#"
											+ invResultValidationRespVO.getPatdeptunitcode() + "#"
											+ invResultValidationRespVO.getRequisitionTypeCode() + "#"
											+ invResultValidationRespVO.getUomCode() + "#"
											+ invResultValidationRespVO.getTestName() + "#"
											+ invResultValidationRespVO.getPatLabName() + "#"
											+ invResultValidationRespVO.getSampleName() + "#"
											+ invResultValidationRespVO.getTempSampleNo() + "#"
											+ invResultValidationRespVO.getGroupCode() + "#"
											+ invResultValidationRespVO.getDynamicTemplatePrintedGroup() + "#"
											+ invResultValidationRespVO.getPatName() + "#"
											+ invResultValidationRespVO.getRefRange() + "#"
											+ invResultValidationRespVO.getDetpUnitCode() + "#"
											+ invResultValidationRespVO.getPatUnitName() + "#"
											+ invResultValidationRespVO.getIsrequisitionformneeded();
								}

								//String chkTestValue=invResultValidationRespVO.getPatCRNo()+"#"+invResultValidationRespVO.getRequisitionNo()+"#"+invResultValidationRespVO.getRequisitionDNo()+"#"+invResultValidationRespVO.getTestCode()+"#"+invResultValidationRespVO.getSampleNo()+"#"+invResultValidationRespVO.getLabCode()+"#"+invResultValidationRespVO.getPatAge()+"#"+invResultValidationRespVO.getPatGender()+"#"+invResultValidationRespVO.getReportAvailableAfter()+"#"+invResultValidationRespVO.getPatVisitDat()+"#"+invResultValidationRespVO.getPatVisitNo()+"#"+invResultValidationRespVO.getLabNo()+"#"+invResultValidationRespVO.getEpisodeCode()+"#"+invResultValidationRespVO.getDepartmentcode()+"#"+invResultValidationRespVO.getPatdeptunitcode()+"#"+invResultValidationRespVO.getRequisitionTypeCode()+"#"+invResultValidationRespVO.getUomCode()+"#"+invResultValidationRespVO.getTestName()+"#"+invResultValidationRespVO.getPatLabName()+"#"+invResultValidationRespVO.getSampleName()+"#"+invResultValidationRespVO.getTempSampleNo();
								String labCode = invResultValidationRespVO.getLabCode();
								String reqDno = invResultValidationRespVO.getRequisitionDNo();
								//String chkTestValue=invResultValidationRespVO.getPatCRNo()+"#"+invResultValidationRespVO.getRequisitionNo()+"#"+invResultValidationRespVO.getRequisitionDNo()+"#"+invResultValidationRespVO.getTestCode()+"#"+invResultValidationRespVO.getSampleNo()+"#"+invResultValidationRespVO.getLabCode()+"#"+invResultValidationRespVO.getPatAge()+"#"+invResultValidationRespVO.getPatGender()+"#"+invResultValidationRespVO.getReportAvailableAfter()+"#"+invResultValidationRespVO.getPatVisitDat()+"#"+invResultValidationRespVO.getPatVisitNo()+"#"+invResultValidationRespVO.getLabNo()+"#"+invResultValidationRespVO.getEpisodeCode()+"#"+invResultValidationRespVO.getDepartmentcode()+"#"+invResultValidationRespVO.getPatdeptunitcode()+"#"+invResultValidationRespVO.getRequisitionTypeCode()+"#"+invResultValidationRespVO.getUomCode();

								if (firstTimeTravesa) {
			%>
			<%
				String header = "";
									if (!pidnoo.equals("")) {
										header = "Patient Details <font style='color:yellow;'>(PID Number: " + pidnoo
												+ ")</font>";
			%>
			<%-- <font  style="color: white;text-align">PID Number:<%=pidnoo %> </font> --%>
			<%
				} else {
										header = "Patient Details";
									}
			%>
			<his:SubTitleTag name="<%=header%>">
				<%
					String showDetail = "showPatDetails(" + i + ")";
											String hideDetail = "hidePatDetails(" + i + ")";
				%>
				<%
					if (invResultValidationRespVO.getDeptReport() != null) {
				%>
				<a onclick="printReport('<%=invResultValidationRespVO.getDeptReport()%>')"><font color="white">View Departmental Report</font></a>
				<%
					}
				%>
				<img class="button" title="Show Patinet Details" src='<his:path src="/hisglobal/images/plusinv.png"/>' id="show<%=i%>" tabindex="1" onclick="<%=showDetail%>">
				<img class="button" title="Hide Patient Details" src='<his:path src="/hisglobal/images/Minus.png"/>' id="hide<%=i%>" style="display: none;" tabindex="1" onclick="<%=hideDetail%>">
			</his:SubTitleTag>
			<table width="100%">
				<tr>
					<td class="tdfont" width="15%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="crNO" />&nbsp;
							</font>
						</div>
					</td>
					<td class="tdfonthead" width="16%">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=invResultValidationRespVO.getPatCRNo()%>
							</font>
						</div>
					</td>
					<td class="tdfont" width="15%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="patientName" />&nbsp;
							</font>
						</div>
					</td>
					<td class="tdfonthead" width="16%">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=invResultValidationRespVO.getPatName()%>
							</font>
						</div>
					</td>
					<td class="tdfont" width="15%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%
 	if (invResultValidationRespVO.getLabtype() != null
 								&& invResultValidationRespVO.getLabtype().equals("2")) {
 %> Accession No. <%
 	} else {
 %> <bean:message key="sampleNo" /> <%
 	}
 %>
							</font>
						</div>
					</td>
					<td class="tdfonthead" width="16%">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=invResultValidationRespVO.getTempSampleNo() == null ? "NA"
										: invResultValidationRespVO.getTempSampleNo()%>
							</font>
						</div>
					</td>
				</tr>
			</table>
			<div id="showhide<%=i%>" style="display: none;">
				<table width="100%">
					<tr>
						<td class="tdfont" width="15%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="age/gender" />&nbsp;
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="16%">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=invResultValidationRespVO.getPatAge() + "/"
										+ invResultValidationRespVO.getPatGender()%>
								</font>
							</div>
						</td>
						<td class="tdfont" width="15%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%
 	if (invResultValidationRespVO.getLabtype() != null
 								&& invResultValidationRespVO.getLabtype().equals("2")) {
 %> <bean:message key="visitDate" />&nbsp; <%
 	} else {
 %> <bean:message key="SampleName" />&nbsp; <%
 	}
 %>
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="16%">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%
 	if (invResultValidationRespVO.getLabtype() != null
 								&& invResultValidationRespVO.getLabtype().equals("2")) {
 %> <%=invResultValidationRespVO.getPatVisitDat()%> <%
 	} else {
 %> <%=invResultValidationRespVO.getSampleName()%> <%
 	}
 %>
								</font>
							</div>
						</td>
						<td class="tdfont" width="15%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="labName" />&nbsp;
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="16%">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=invResultValidationRespVO.getPatLabName()%>
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td class="tdfont" width="15%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="departmentunit" />&nbsp;
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="16%">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=invResultValidationRespVO.getPatUnitName()%>
								</font>
							</div>
						</td>
						<td class="tdfont" width="15%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%
 	if (invResultValidationRespVO.getLabtype() != null
 								&& !invResultValidationRespVO.getLabtype().equals("2")) {
 %> <bean:message key="visitDate" />&nbsp; <%
 	}
 %>
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="16%">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%
 	if (invResultValidationRespVO.getLabtype() != null
 								&& !invResultValidationRespVO.getLabtype().equals("2")) {
 %> <%=invResultValidationRespVO.getPatVisitDat()%> <%
 	}
 %>
								</font>
							</div>
						</td>
						<td class="tdfont" width="15%">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <bean:message key="TestName" />
								</font>
							</div>
						</td>
						<td class="tdfonthead" width="16%">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=invResultValidationRespVO.getGroupName().equals("NA")
										? invResultValidationRespVO.getTestName()
										: invResultValidationRespVO.getGroupName()%>
								</font>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<table width="100%">
				<tr>
					<td class="tdfont" width="15%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> Indications </font>
						</div>
					</td>
					<td class="tdfonthead" width="79%">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <input type="text" id="indicationInput#<%=invResultValidationRespVO.getRequisitionNo()%>"
								class="indicationInputC#<%=invResultValidationRespVO.getRequisitionNo()%>" onchange="indicationInputChange(this);"
								value="<%=invResultValidationRespVO.getVisitReason() == null ? ""
										: invResultValidationRespVO.getVisitReason()%>"
							>
							</font>
							<html:hidden name="InvResultValidationRespFB" property="visitReason" />
						</div>
					</td>
				</tr>
			</table>
			<%-- <div class="subDivStyle">
                                <his:SubTitleTag name="Req Form">
											<img class="button" title="Show Patinet Details"  src='<his:path src="/hisglobal/images/plusinv.png"/>' id="reqformshow"      tabindex="1" onclick ="f1()" >
										<img class="button" title="Hide Patient Details"  src='<his:path src="/hisglobal/images/Minus.png"/>' id="reqformhide"      tabindex="1" onclick ="f2()" >
											 </his:SubTitleTag>
                 </div>
                           <div class="subDivStyle" id="reqformss" style="display: none">
                               <table width="100%" border="1" id="tbll">
                               <tr>
                       <td style="font-weight: bold;color: brown;">TEST NAME</td>
                      <td style="font-weight: bold;color: brown;">REQUISITION FORMS</td>
                      </tr>
                               </table>
                 </div> --%>
			<his:SubTitleTag name="Result Validation Form">
				<logic:equal name="InvResultValidationRespFB" property="ispreview" value="1">
					<html:checkbox name="InvResultValidationRespFB" property="saveReVal" value="<%=invResultValidationRespVO.getPatCRNo() + '#'
													+ invResultValidationRespVO.getRequisitionNo()%>"
						onclick="selectSaveReVal(this)"
					></html:checkbox>
					<u>Send to Re-Validation</u>
				</logic:equal>
			</his:SubTitleTag>
			<input type="hidden" value="<%=invResultValidationRespVO.getPatLabName()%>" name="chkLabName" />
			<%
				}
								if (sameSampleNO)
									firstTimeTravesa = false;
			%>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<%
						if (lstInvResultValidationRespVO.get(k).getResultEntryVOListValidatedBy().get(0)
												.getTemplateDocumentString() != null
												&& (lstInvResultValidationRespVO.get(k).getResultEntryVOListValidatedBy().get(0)
														.isDoCreateTemplate() == true))

										{
					%>
					<logic:equal name="InvResultValidationRespFB" property="ispreview" value="1">
						<td width="2%"><input type="checkbox" id="<%=i%><%=k%><%=i%>chkBOx" name="chkResultValidationPatient" value='<%=chkTestValue%>'> <html:hidden name="InvResultValidationRespFB"
								styleId="<%=reqDno%>" value="<%=labCode%>" property="cannedLabCode"
							/> <html:hidden name="InvResultValidationRespFB" property="cannedOrMacroCheck" /></td>
						<%-- 	<td class="tdfont" width="15%">
							<div align="right">
								<font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
										key="TestName" />&nbsp;
								</font>
							</div>
						</td> --%>
						<td class="tdfonthead" width="10%">
							<div align="left">
								<font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=lstInvResultValidationRespVO.get(k).getResultEntryVOListValidatedBy()
											.get(0).getDynamnicTemplateResultEntryGroup().equals("1")
													? invResultValidationRespVO.getGroupName()
													: invResultValidationRespVO.getTestName()%>
								</font>
								<!-- ddddddddddd -->
								<%
									String inst = invResultValidationRespVO.getInstructionPat() == null ? "NA"
																	: invResultValidationRespVO.getInstructionPat();

															inst = inst.replace("\r\n", "<br>");

															/* String inst="d"; */
								%>
								<%
									if (!inst.equals("NA")) {
								%>
								<img class="button" title="Show Instructions" src='<his:path src="/hisglobal/images/add_remarks_sml.jpg"/>' tabindex="1" onclick="showInstructions5('<%=inst%>');">
								<%
									} else {
															}
								%>
								<%
									if (!invResultValidationRespVO.getIsrequisitionformneeded().equals("0")) {
								%>
								<%-- &nbsp&nbsp<img height='20px' title='Requisition Form' src='/HISInvestigationG5/hisglobal/images/reqForm.png' onClick="ShowRequistionForm('<%=invResultValidationRespVO.getTestCode()%>','<%=invResultValidationRespVO.getTestName()%>','<%=invResultValidationRespVO.getLabCode()%>','<%=invResultValidationRespVO.getLabName()%>','<%=invResultValidationRespVO.getRequisitionDNo()%>')"> --%>
								<%
									}
								%>
								<div id="blanket" style="display: none;"></div>
								<div id="popUpDiv5" style="display: none;" align="center">
									<his:TitleTag name="Instructions For Technician">
										<img src='/HISInvestigationG5/hisglobal/css/close.png' onClick="closeInstructions();">
									</his:TitleTag>
									<table width="100%" id="allInstructions">
										<tr>
											<!-- <td class="tdfonthead" width="20%">
		<div align="left"><b>Instructions for:</b></div>
		</td>

		<td class="tdfonthead" width="80%">
		<div align="center"><b>Instructions</b></div>
		</td> -->
										</tr>
									</table>
									<img src='/HISInvestigationG5/hisglobal/images/ok.gif' onClick="closeInstructions();">
								</div>
								<!-- END -- TO DISPLAY INSTRUCTIONS -->
							</div>
						</td>
					</logic:equal>
					<td width='99.99%' id="<%=i%><%=k%><%=i%>templateValue"><%=lstInvResultValidationRespVO.get(k).getResultEntryVOListValidatedBy().get(0)
										.getTemplateDocumentString()%> <%
 	} else if (lstInvResultValidationRespVO.get(k).getResultEntryVOListValidatedBy().get(0)
 							.getTemplateDocumentString() != null
 							&& lstInvResultValidationRespVO.get(k).getResultEntryVOListValidatedBy().get(0)
 									.isDoCreateTemplate() == false) {
 						// do nothing
 					} else {
 %> <left> <font color="red" size="4"> <bean:message key="resultEntryNot" />&nbsp;<%=invResultValidationRespVO.getTestName()%>
						</font> </left></td>
					<%
						}
					%>
				</tr>
			</table>
			<%
				}

							if (!finalRemarkCodeList.contains(invResultValidationRespVO.getPatCRNo() + '#'
									+ invResultValidationRespVO.getRequisitionNo() + '#'
									+ invResultValidationRespVO.getTempSampleNo())) {
								finalRemarkCodeList.add(invResultValidationRespVO.getPatCRNo() + '#'
										+ invResultValidationRespVO.getRequisitionNo() + '#'
										+ invResultValidationRespVO.getTempSampleNo());

								if (invResultValidationRespVO.getFinalRemarkReqd().equals("1")) {
			%>
			<!-- send to revalidation process -->
			<%-- 	<table width="100%" cellpadding="0" cellspacing="0" border="0">
	  				<tr>
	  				<td width="2%" >
	  				</td>

	  				<td width="19%" class="tdfonthead">
	  				</td>
	  				<td width="2%" class="tdfonthead">
	  				<html:checkbox name="InvResultValidationRespFB" property="saveReVal" value="<%=invResultValidationRespVO.getPatCRNo()+'#'+invResultValidationRespVO.getRequisitionNo()%>"></html:checkbox>
	  				</td>
	  				<td width="77%" class="tdfonthead">
	  					<div align="left">
	  				&nbsp;&nbsp;&nbsp;&nbsp;Send to Re-Validation
	  				</div>
	  				</td>
	  				</tr>
	  				</table>
	  				 --%>
			<!-- for final remark -->
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td width="2%"></td>
					<logic:equal name="InvResultValidationRespFB" property="ispreview" value="1">
						<td width="20%" class="tdfonthead">
							<div align="right">Comments&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
						</td>
						<td width="78%" class="tdfonthead">
							<div align="left">
								<%
									String sp = "/";
								%>
								
								                 	<%
		  				if(invResultValidationRespVO.getFinalRemarkString()==null)
		  				{
		  					invResultValidationRespVO.setFinalRemarkString("");
		  				}
		  				%> 
								<html:textarea style="width: 900px;" value='<%=invResultValidationRespVO.getFinalRemarkString().replaceAll("&lt;","<").replaceAll("&gt;",">")%>'
									styleId="<%=invResultValidationRespVO.getPatCRNo() + '#'
												+ invResultValidationRespVO.getRequisitionNo() + '#'
												+ invResultValidationRespVO.getTempSampleNo().split(sp)[0]%>"
									name="InvResultValidationRespFB" property="finalRemarksValue"
								>
								</html:textarea>
								<html:hidden name="InvResultValidationRespFB" property="crNoReqNoString"
									value="<%=invResultValidationRespVO.getPatCRNo() + '#'
											+ invResultValidationRespVO.getRequisitionNo() + '#'
											+ invResultValidationRespVO.getTempSampleNo().split(sp)[0]%>"
								/>
							</div>
						</td>
					</logic:equal>
				</tr>
			</table>
			<%
				}
							}

							if (invResultValidationRespVO.getAddendumRemarkValue() != null) {
			%>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td width="2%"></td>
					<td width="20%" class="tdfonthead">
						<div align="right">&nbsp;&nbsp;&nbsp;&nbsp;Addendum Remarks</div>
					</td>
					<td width="78%" class="tdfonthead">
						<div align="left">
							<html:textarea style="width: 900px;" value="<%=invResultValidationRespVO.getAddendumRemarkValue()%>"
								styleId="<%=invResultValidationRespVO.getPatCRNo() + '#'
										+ invResultValidationRespVO.getRequisitionNo()%>" name="InvResultValidationRespFB" property="addendumRemarks"
							>
							</html:textarea>
							<html:hidden name="InvResultValidationRespFB" property="crNoReqNoStringAddendum"
								value="<%=invResultValidationRespVO.getPatCRNo() + '#'
									+ invResultValidationRespVO.getRequisitionNo()%>"
							/>
						</div>
					</td>
				</tr>
			</table>
			<%
				}

							i++;
						}
			%>
			<div id="cannedField" style="display: none; position: fixed; overflow-y: auto;">
				<div draggable="true">
					<his:TitleTag name="Canned List">
						<%-- <his:insertDateTag/> --%>
						<img src='/HISInvestigationG5/hisglobal/css/close.png' onClick="autocompleteBox_close();">
					</his:TitleTag>
					<table width="100%" id="autoCannedCombo">
						<html:hidden name="InvResultValidationRespFB" property="currentElement" />
						<html:hidden name="InvResultValidationRespFB" property="currentElementName" />
						<html:hidden name="InvResultValidationRespFB" property="editorName" />
						<tr>
							<td class="tdfonthead" width="20%">
								<div align="right">
									<b><bean:message key="CanedCode" /></b>
								</div>
							</td>
							<td class="tdfont" width="20%">
								<div align="left" class="ui-widget">
									<input type="text" id="automplete-4" name="userCanned" size="30" style="width: 100px;"
										onkeypress="if(event.keyCode==13) addCannedDetail('CANNED');return validateAlphaNumericOnly(event,this);"
									>
								</div>
							</td>
						</tr>
					</table>
					<table id="addMoreValue" width="100%"></table>
					<div align="center">
						<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"  />' onclick="setCannedDetail('CANNED');autocompleteBox_close();">
					</div>
				</div>
			</div>
			<div id="macroField" style="display: none; position: fixed; overflow-y: auto;">
				<div draggable="true">
					<his:TitleTag name="Macro List">
						<%-- <his:insertDateTag/> --%>
						<img src='/HISInvestigationG5/hisglobal/css/close.png' onClick="autocompleteBox_close_macro();">
					</his:TitleTag>
					<table width="100%" id="autoMacroCombo">
						<html:hidden name="InvResultValidationRespFB" property="currentElement" />
						<html:hidden name="InvResultValidationRespFB" property="currentElementName" />
						<html:hidden name="InvResultValidationRespFB" property="editorName" />
						<tr>
							<td class="tdfonthead" width="20%">
								<div align="right">
									<b><bean:message key="macroCode" /></b>
								</div>
							</td>
							<td class="tdfont" width="20%">
								<div align="left" class="ui-widget">
									<input type="text" id="automplete-m" name="userMacro" size="30" style="width: 100px;" onkeypress="if(event.keyCode==13) addCannedDetail('MACRO');return validateAlphaNumericOnly(event,this);">
								</div>
							</td>
						</tr>
					</table>
					<table id="addMoreValuemacro" width="100%"></table>
					<div align="center">
						<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"  />' onclick="setCannedDetail('MACRO');autocompleteBox_close_macro();">
					</div>
				</div>
			</div>
			<div id="canned">
				<div draggable="true">
					<his:TitleTag name="Canned List">
						<%-- <his:insertDateTag/> --%>
						<img src='/HISInvestigationG5/hisglobal/css/close.png' onClick="lightbox_close();">
					</his:TitleTag>
					<table id="addMoreValue" width="100%">
						<html:hidden name="InvResultValidationRespFB" property="currentElement" />
						<html:hidden name="InvResultValidationRespFB" property="currentElementName" />
					</table>
					<his:ButtonToolBarTag>
						<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"  />' onclick="setCannedDetail('CANNED')">
					</his:ButtonToolBarTag>
				</div>
			</div>
			<div id="macro">
				<div draggable="true">
					<his:TitleTag name="Macro List">
						<%-- <his:insertDateTag/> --%>
						<img src='/HISInvestigationG5/hisglobal/css/close.png' onClick="lightbox_close_macro();">
					</his:TitleTag>
					<table id="addMoreValuemacro" width="100%">
						<html:hidden name="InvResultValidationRespFB" property="currentElement" />
						<html:hidden name="InvResultValidationRespFB" property="currentElementName" />
					</table>
					<his:ButtonToolBarTag>
						<img class="button" src='<his:path src="/hisglobal/images/btn-ok.png"  />' onclick="setCannedDetail('MACRO')">
					</his:ButtonToolBarTag>
				</div>
			</div>
		</logic:present>
		<!-- report Container -->
		<div id="container">
			<div id="blanket" style="display: none;"></div>
			<div draggable="true" id="popUpDiv" style="display: none;">
				<his:SubTitleTag name="Patient Report">
					<div class="vertpan pic">
						<img src='/HISInvestigationG5/hisglobal/images/delete_on.gif' onclick="popupClose('popUpDiv')"'>
					</div>
				</his:SubTitleTag>
				<table id="reportTable" width="100%">
					<tr id="deleteRow">
					</tr>
				</table>
				<left></left>
			</div>
			<!-- end #mainContent -->
		</div>
		<his:ButtonToolBarTag>
			<logic:equal name="InvResultValidationRespFB" property="newEntry" value="1">
				<img class="button" src='<his:path src="/hisglobal/images/btn-save-validate.png"/>' id="nextDiv1" style="cursor: pointer; display: none" tabindex="1" onclick="revalidateSamplePatDetails();">
			</logic:equal>
			<img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="nextDiv" style="cursor: pointer; display: none" tabindex="1" onclick="displaySamplePatDetails();">
			<logic:present name="<%=InvestigationConfig.LIST_RESULT_VALIDATION_RESP_TEST_TEMPLATE_VO%>">
				<logic:equal name="InvResultValidationRespFB" property="newEntry" value="1">
					<img class="button" src='<his:path src="/hisglobal/images/btn-save-validate-all.png"/>' id="saveDivAll" onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13) onSave();"
						tabindex="1" onclick="setIndication(); selectAll();"
					>
					<img class="button" src='<his:path src="/hisglobal/images/btn-save-validate.png"/>' id="saveDiv" onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13) onSave();" tabindex="1"
						onclick="setIndication(); onSave();"
					>
					<!-- added by krishnan nema on 01/02/2019 for save to draft change -->
					<img class="button" src='<his:path src="/hisglobal/images/saveToDraft.png"/>' id="saveDivDraft" onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13) onSaveValidationToDraft();"
						tabindex="1" onclick="setIndication(); onSaveValidationToDraft();"
					>
				</logic:equal>
				<logic:equal name="InvResultValidationRespFB" property="newEntry" value="2">
					<img class="button" src='<his:path src="/hisglobal/images/btn-mo-all.png"/>' id="saveDivAll" onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13) onSave();" tabindex="1"
						onclick="setIndication(); modifyAll();"
					>
					<img class="button" src='<his:path src="/hisglobal/images/btn-mo.png"/>' id="saveDiv" onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13) onSave();" tabindex="1"
						onclick="setIndication(); onModify();"
					>
				</logic:equal>
				<!-- added by krishnan nema on 01/02/2019 for save to draft changes -->
				<logic:equal name="InvResultValidationRespFB" property="newEntry" value="3">
					<img class="button" src='<his:path src="/hisglobal/images/btn-save-validate-all.png"/>' tabindex="1" onclick="setIndication(); selectAll();">
					<img class="button" src='<his:path src="/hisglobal/images/btn-save-validate.png"/>' id="saveDiv" onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13) onSave();" tabindex="1"
						onclick="setIndication(); onSave();"
					>
					<!-- added by krishnan nema on 01/02/2019 for save to draft change -->
					<img class="button" src='<his:path src="/hisglobal/images/saveToDraft.png"/>' id="saveDivDraft" onkeypress="if(event.keyCode==13)setIndication(); if(event.keyCode==13) onSaveValidationToDraft();"
						tabindex="1" onclick="setIndication(); onSaveValidationToDraft();"
					>
				</logic:equal>
				<logic:equal name="InvResultValidationRespFB" property="ispreview" value="1">
					<img class="button" src='<his:path src="/hisglobal/images/cannedFile.png"/>' onclick="popupCallCanned();" tabindex="1">
					<img class="button" src='<his:path src="/hisglobal/images/macro.png"/>' onclick="popupCallMacro();" tabindex="1">
				</logic:equal>
			</logic:present>
			<logic:present name="<%=InvestigationConfig.LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
				<logic:equal name="InvResultValidationRespFB" property="ispreview" value="1">
					<logic:present name="<%=InvestigationConfig.LIST_RESULT_VALIDATION_RESP_TEST_TEMPLATE_VO%>">
						<!-- to hide ccl button on list page -->
						<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' id="cancel" tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFor();" tabindex="1"
							onclick="submitFor();"
						>
					</logic:present>
				</logic:equal>
			</logic:present>
		</his:ButtonToolBarTag>
		<logic:equal name="InvResultValidationRespFB" property="ispreview" value="1">
			<logic:present name="<%=InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO%>">
				<his:SubTitleTag>
					<his:name>
						<bean:message key="legends" />
					</his:name>
					<table width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td width="70%"></td>
							<td width="30%">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' tabindex="1" onclick="showLegends1();"
										onkeypress="if(event.keyCode==13) showLegends1();"
									> <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide </font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' tabindex="1"
										onclick="showLegendsNone1();" onkeypress="if(event.keyCode==13) showLegendsNone1();"
									>
								</div>
							</td>
						</tr>
					</table>
				</his:SubTitleTag>
			</logic:present>
		</logic:equal>
		<logic:equal name="InvResultValidationRespFB" property="showStatus" value="1">
			<%-- <his:SubTitleTag>
			<his:name>
				<bean:message key="legends" />
			</his:name>
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="70%"></td>
					<td width="30%">
						<div align="right">
							<font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">Show </font><img
								src='<his:path src="/hisglobal/images/arrow_down.gif"/>'
								tabindex="1" onclick="showLegends();"
								onkeypress="if(event.keyCode==13) showLegends();"> <font
								color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif">Hide </font><img
								src='<his:path src="/hisglobal/images/arrow_up.gif"/>'
								tabindex="1" onclick="showLegendsNone();"
								onkeypress="if(event.keyCode==13) showLegendsNone();">
						</div>
					</td>
				</tr>
			</table>
		</his:SubTitleTag> --%>
		</logic:equal>
		<div id="divLegends" style="display: none">
			<his:ContentTag>
				<table width="100%" colspacing="1" colpadding="0" style="clear: both; border-left: 1px solid #003366; border-top: 1px solid #003366">
					<tr>
						<td width="20%"><font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Ctrl + F9</div>
						</font></td>
						<td width="80%"><font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Open Canned File</div>
						</font></td>
					</tr>
					<tr>
						<td width="20%"><font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Ctrl + F12</div>
						</font></td>
						<td width="80%"><font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Close Canned File</div>
						</font></td>
					</tr>
					<tr>
						<td width="20%"><font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Shift + F9</div>
						</font></td>
						<td width="80%"><font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Open Macro File</div>
						</font></td>
					</tr>
					<tr>
						<td width="20%"><font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Shift + F12</div>
						</font></td>
						<td width="80%"><font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Close Macro File</div>
						</font></td>
					</tr>
				</table>
			</his:ContentTag>
		</div>
		<div id="divLegends1" style="display: none">
			<his:ContentTag>
				<table width="100%" colspacing="1" colpadding="0" style="clear: both; border-left: 1px solid #003366; border-top: 1px solid #003366">
					<tr>
						<td width="20%"><font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Blue Color Code</div>
						</font></td>
						<td width="80%"><font color="blue" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Normal Validation Record</div>
						</font></td>
					</tr>
					<tr>
						<td width="20%"><font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Red Color Code</div>
						</font></td>
						<td width="80%"><font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<div align="left">Demographic/Amendment/Addendum record</div>
						</font></td>
					</tr>
				</table>
			</his:ContentTag>
		</div>
		<!-- Added by PrashantMi PITR-->
		<a style="color: white" id="iframeInvTracking" class="iframeInvTracking"></a>
		<div id="dialogg" style="display: none;">
			<div>
				<iframe style="margin-top: 20px" width="1960" height="960" frameborder="0" id="frame"></iframe>
			</div>
		</div>
		<html:hidden name="InvResultValidationRespFB" property="resultValidationTemplateValue" />
		<html:hidden name="InvResultValidationRespFB" property="parameterCode" />
		<html:hidden name="InvResultValidationRespFB" property="parantParameterCode" />
		<html:hidden name="InvResultValidationRespFB" property="requisitionDNo" />
		<html:hidden name="InvResultValidationRespFB" property="resultValidationTemplateValueWithHash" />
		<html:hidden name="InvResultValidationRespFB" property="startDisplay" />
		<html:hidden name="InvResultValidationRespFB" property="hideDisplay" />
		<input type="hidden" name="cannedDataCount" value="0" />
		<input type="hidden" name="cannedDetails" value="0" />
		<input type="hidden" id="hiddenid4" name="userCannedCode" />
		<input type="hidden" id="hiddenidm" name="userMacroCode" />
		<input type="hidden" name="macroDataCount" value="0" />
		<input type="hidden" name="macroDetails" value="0" />
		<html:hidden name="InvResultValidationRespFB" property="fileName" />
		<html:hidden name="InvResultValidationRespFB" property="sampleAreaCode" />
		<html:hidden name="InvResultValidationRespFB" property="sampleAreaName" />
		<html:hidden name="InvResultValidationRespFB" property="isSampleAreaSelected" />
		<html:hidden name="InvResultValidationRespFB" property="ispreview" />
		<html:hidden name="InvResultValidationRespFB" property="patientType" />
		<his:status />
	</html:form>
</body>
</html>
