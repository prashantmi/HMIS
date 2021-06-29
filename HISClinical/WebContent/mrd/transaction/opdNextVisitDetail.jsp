<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="opd.OpdConfig"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="opd.transaction.controller.fb.OpdRosterSchedulePopupFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>


<%@page import="opd.transaction.controller.fb.OpdNextVisitDetailFB"%>
<%@page import="hisglobal.vo.EpisodeVO"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/opd/js/opd_visit_summary.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
// On change of Next Visit Mode
function setNextVisitMode()
{
	var arrModes = document.getElementsByName("nextVisitCriteria");
	var elemDivDays = document.getElementById("divNextDateModeDays");
	var elemDivDate = document.getElementById("divNextDateModeDate");
	elemDivDays.style.display = "none";
	elemDivDate.style.display = "none";
	
	for(var i=0; i<arrModes.length; i++)
	{
		if(arrModes[i].checked)
		{
			if(arrModes[i].value == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>")
			{
				elemDivDays.style.display = "block";
			}
			else if(arrModes[i].value == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>")
			{
				elemDivDate.style.display = "block";
			} 
		}
	}
}

function setEpisodeCloseData()
{
	var episodeClose = null;
	var radios = document.getElementsByName('episodeIsOpen');
	for(var i=0;i<radios.length;i++)
	{
		if(radios[i].checked)
		{
			episodeClose = radios[i].value;
		}
	}
	if(episodeClose=="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>")
	{
		// Episode Summary
		var elemDivEpiSumm = document.getElementById("divEpisodeSummary");
		elemDivEpiSumm.style.display = "block";

		var elemDivModes = document.getElementById("divNextDateModes");
		elemDivModes.style.display = "none";

		var arrModes = document.getElementsByName("nextVisitCriteria");		
		for(var i=0; i<arrModes.length; i++)
		{
			arrModes[i].checked = false;
			arrModes[i].disabled = true;
		}
		setNextVisitMode();
	}
	else
	{
		// Episode Summary
		var elemDivEpiSumm = document.getElementById("divEpisodeSummary");
		elemDivEpiSumm.style.display = "none";

		var elemDivModes = document.getElementById("divNextDateModes");
		elemDivModes.style.display = "block";

		var arrModes = document.getElementsByName("nextVisitCriteria");
		for(var i=0; i<arrModes.length; i++)
		{
			arrModes[i].disabled = false;
		}
		arrModes[0].checked = true;
		setNextVisitMode();
	}
}

function validateNextVisitModes()
{
	var elemDivModes = document.getElementById("divNextDateModes");
	if(elemDivModes.style.display.toUpperCase()=="NONE")	return true;

	var arrModes = document.getElementsByName("nextVisitCriteria");
	var checked = false;	
	for(var i=0; i<arrModes.length; i++)
	{
		if(arrModes[i].checked)
		{
			checked = true;
			if(arrModes[i].value == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>")
			{
				if(document.getElementsByName("nextVisitDuration")[0].value=="")
				{
					alert("Please Enter Next Visit Duration");
					document.getElementsByName('nextVisitDuration')[0].focus();
					return false;
				}
				var dur = parseInt(document.getElementsByName("nextVisitDuration")[0].value);
				var durCri = document.getElementsByName("nextVisitDurationCriteria")[0].value;
				if(durCri == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC%>" && !(dur>0 && dur<=99))
				{
					alert("Please Enter Days in Range 1-99");
					document.getElementsByName('nextVisitDuration')[0].focus();
					return false;
				}
				if(durCri == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_WEEKS_SPECIFIC%>" && !(dur>0 && dur<=99))
				{
					alert("Please Enter Weeks in Range 1-99");
					document.getElementsByName('nextVisitDuration')[0].focus();
					return false;
				}
				if(durCri == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_MONTHS_SPECIFIC%>" && !(dur>0 && dur<=12))
				{
					alert("Please Enter Months in Range 1-12");
					document.getElementsByName('nextVisitDuration')[0].focus();
					return false;
				}
			}
			else if(arrModes[i].value == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>")
			{
				if(document.getElementsByName("episodeNextVisitDate")[0].value=="")
				{
					alert("Please Enter Next Visit Date from Schedule");
					document.getElementsByName('episodeNextVisitDate')[0].focus();
					return false;
				}
				var nextVisitDt = convertStrToDate(document.getElementsByName("episodeNextVisitDate")[0].value,"dd-Mon-yyyy");
				var entryDate = convertStrToDate(document.getElementsByName("entryDate")[0].value,"dd-Mon-yyyy");
				if(nextVisitDt<=entryDate)
				{
					alert("Next Visit Date should be greater than Current Date");
					document.getElementsByName('episodeNextVisitDate')[0].focus();
					return false;
				}
			} 
		}
	}
	return true;
}

function setEpisodeCloseDataOnLoad()
{
	var episodeClose = null;
	var radios = document.getElementsByName('episodeIsOpen');
	for(var i=0;i<radios.length;i++)
	{
		if(radios[i].checked)
		{
			episodeClose = radios[i].value;
		}
	}

	var elemDivModes = document.getElementById("divNextDateModes");
	var elemDivEpiSumm = document.getElementById("divEpisodeSummary");
	if(episodeClose=="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>")
	{
		for(var i=0;i<radios.length;i++)
			radios[i].disabled = true;

		elemDivEpiSumm.style.display = "block";
		elemDivModes.style.display = "none";

		var arrModes = document.getElementsByName("nextVisitCriteria");		
		for(var i=0; i<arrModes.length; i++)
		{
			arrModes[i].checked = false;
			arrModes[i].disabled = true;
		}
		setNextVisitMode();
	}
	else
	{
		elemDivEpiSumm.style.display = "none";
		elemDivModes.style.display = "block";
		setNextVisitMode();
	}
}

window.onload = function()
{
	setEpisodeCloseDataOnLoad();
	endTreatmentForDeadPatient();
}

function endTreatmentForDeadPatient()
{
	var elemDivModes = document.getElementById("divNextDateModes");
	if(document.getElementsByName("isPatDead")[0].value==<%=RegistrationConfig.YES%>)
	{
		document.getElementsByName("episodeIsOpen")[0].checked=true;
		document.getElementsByName("episodeIsOpen")[0].disabled=true;
		document.getElementsByName("episodeIsOpen")[1].disabled=true;
		
		elemDivModes.style.display = "none";
		setNextVisitMode();
	}
	
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName("hmode")[0].value = "PAGINATION";
	document.forms[0].submit();
}

function getUploadedProfile(event,path)
{
	openDependentPopup(path,event,600,700,'yes');
}

</script>

<his:TransactionContainer>
	<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); %>
	<html:hidden name="OpdNextVisitDetailFB" property="entryDate" value="<%=sysDate%>"/>
	<bean:define id="entrySeatId" name="OpdNextVisitDetailFB" property="seatId" type="java.lang.String"></bean:define>
	<bean:define id="unitCode" name="OpdNextVisitDetailFB" property="departmentUnitCode" type="java.lang.String"></bean:define>
	
	<his:TitleTag key="visitsumm">
	</his:TitleTag>
	
	<jsp:include page="/registration/patientDetail.cnt" flush="true"></jsp:include>
	
	<his:statusTransactionInProcess>

		
		<%
			PaginationFB fbPage= new PaginationFB();
			pageContext.setAttribute("fbPagination", fbPage);
			fbPage.setCurrentPage(((OpdNextVisitDetailFB)request.getAttribute("OpdNextVisitDetailFB")).getCurrentPage());
			fbPage.setObjArrName(OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST);
			fbPage.setAppendInTitle("Schedule Dates");
			fbPage.setMaxRecords(5);
			
			List lstPreviousVisits = (List)session.getAttribute(OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST); 
			if(lstPreviousVisits.size()>0)
			{
		%>
		<his:SubTitleTag key="prevVisitDtl">
		</his:SubTitleTag>

		<his:PaginationTag name="fbPagination"></his:PaginationTag>
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<logic:empty name="<%=OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST%>">
					<tr>
						<td width="100%" class="tdfont" nowrap valign="top">
							<div align="center">
								<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>No Previous Visit Detail Found</b>
								</font>
							</div>		
						</td>
					</tr>
				</logic:empty>
				<logic:notEmpty name="<%=OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST%>">
				<tr>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="visitNo"/></b>
							</font>
						</div>
					</td>
					<td width="35%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="visitDate"/></b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="progNote"/></b>
							</font>
						</div>
					</td>
					<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="plannedVisit"/></b>
							</font>
						</div>
					</td>
				</tr>				
				
				<%
					int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
					
					for(int i=startIndex;i<=endIndex;i++)
					{
						EpisodeVO prevVisitDtl = (EpisodeVO) lstPreviousVisits.get(i);
				%>
					<tr>
						<td width="10%" class="tdfont" nowrap valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><%=prevVisitDtl.getEpisodeVisitNo()%></b>
								</font>
							</div>
						</td>
						<td width="35%" class="tdfont" nowrap valign="top">
							<div align="center">
      	  						<%
								String path = ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" 
										+ ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS + "&" 
										+ ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.PATIENT_PROFILE_STORAGE_PATH_LINUX + "&" 
										+ ServletsUtilityConfig.FILE_NAME + "=" + prevVisitDtl.getPatCrNo().trim() + "_" + prevVisitDtl.getEpisodeDate().trim() + ".htm"; 
  								%>
   								<a style="cursor:pointer" onclick="getUploadedProfile(event,'<%=path%>')" >
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b><%=prevVisitDtl.getEpisodeDate()%></b>
									</font>
   								</a>
							</div>
						</td>
						<td width="25%" class="tdfont" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%	if(prevVisitDtl.getComplainDetail()!=null)	{	%>
									<img name="imgProgressNotes" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Instruction " onclick ="showProgressNotesInPopup(event,<%=Integer.toString(i-startIndex) %>)" onkeypress="if(event.keyCode==13) showProgressNotesInPopup(event,<%=Integer.toString(i-startIndex)%>);">								
									<%	}	 %>
									<input type="hidden" name="prevProgressNotesForPopup" value="<%=prevVisitDtl.getComplainDetail()%>"/>
								</font>
							</div>
						</td>
						<td width="30%" class="tdfont" nowrap valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><%=((prevVisitDtl.getEpisodeNextVisitDate()==null)?"":prevVisitDtl.getEpisodeNextVisitDate())+" "+((prevVisitDtl.getNextVisitDuration()==null)?"":prevVisitDtl.getNextVisitDuration())+" "+((prevVisitDtl.getNextVisitCriteria()==null)?"":OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(prevVisitDtl.getNextVisitCriteria())])%></b>
								</font>
							</div>
						</td>
					</tr>
				<%	} %>
				</logic:notEmpty>
			</table>
		</his:ContentTag>
		<%	} %>
	
	
	<logic:equal name="OpdNextVisitDetailFB" property="isConfirmed" value="<%=RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED%>">
		<his:SubTitleTag key="nextvisitdetail">
		</his:SubTitleTag>
		<logic:notPresent name="OpdNextVisitDetailFB" property="triageDuration" >
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;<bean:message key="keywords"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" valign="middle">
						<div align="left">
							<html:text name="OpdNextVisitDetailFB" property="episodeKeywords" tabindex="1" maxlength="100" size="50" onkeypress="return validateAlphaNumericOnly(event)" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:button value="Add Keywords" property="butKeyword" onclick="openUnitKeywords(event)" style='cursor:pointer'  tabindex='1'/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font>&nbsp;<bean:message key="endtreatment"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont">
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" value="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="yes"/>
						&nbsp;&nbsp;
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" value="<%=RegistrationConfig.EPISODE_ISOPEN_TRUE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="no"/>
					</td>
				</tr>
			</table>
			
			<div id="divNextDateModes" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr valign="middle">
						<td width="25%" class="tdfonthead" valign="middle">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><font color="#FF0000">*</font>&nbsp;<bean:message key="plannedVisit"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" valign="middle">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDays" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr valign="middle">
						<td width="25%" class="tdfonthead" valign="middle"></td>
						<td width="75%" class="tdfont" valign="middle">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:text name="OpdNextVisitDetailFB" property="nextVisitDuration" tabindex="1" maxlength="2" size="5" onkeypress="return validatePositiveIntegerOnly(this,event)"/>
									<html:select name="OpdNextVisitDetailFB" property="nextVisitDurationCriteria" tabindex="1">
										<html:option value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC%>">
										<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC)] %>
										</html:option>
										<html:option value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_WEEKS_SPECIFIC%>">
										<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_WEEKS_SPECIFIC)] %>
										</html:option>
										<html:option value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_MONTHS_SPECIFIC%>">
										<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_MONTHS_SPECIFIC)] %>
										</html:option>
									</html:select>
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDate" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
							<td width="75%" class="tdfont" style="vertical-align: middle">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<html:text name="OpdNextVisitDetailFB" property="episodeNextVisitDate" tabindex="1" size="15" readonly="true" />
										<img class="button" src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style="cursor:pointer" border="1" 
											onkeypress="if(event.keyCode==13) getSchedule(event)" onclick="getSchedule(event)" title="Click to Select Schedule Date">
									</font>
								</div>				
							</td>
					</tr>
				</table>
			</div>

			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" style="vertical-align: middle">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="progNote"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" style="vertical-align: middle">
						<div align="left">
							<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="visitNotes" tabindex="1" 
								onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<% String onClickMacroButton="openVisitNotesMacros('"+OpdConfig.MACRO_PROCESS_ID_VISIT_SUMMARY+"','"+unitCode+"',event)"; %>
							<html:button value="Add Macros" property="mybutton" onclick="<%=onClickMacroButton%>" style='cursor:pointer'  tabindex='1'/>
						</div>
					</td>
				</tr>
			</table>
			<div id="divEpisodeSummary" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="episode"/> <bean:message key="summary"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle">
							<div align="left">
								<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="episodeSummary" tabindex="1" 
									onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</his:ContentTag>
		</logic:notPresent>

		<logic:present name="OpdNextVisitDetailFB" property="triageDuration" >

		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;<bean:message key="keywords"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" valign="middle">
						<div align="left">
							<html:text name="OpdNextVisitDetailFB" property="episodeKeywords" tabindex="1" maxlength="100" size="50" onkeypress="return validateAlphaNumericOnly(event)" disabled="true" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:button value="Add Keywords" property="butKeyword" onclick="openUnitKeywords(event)" style='cursor:pointer'  tabindex='1'/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font>&nbsp;<bean:message key="endtreatment"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont">
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" value="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>" onchange="setEpisodeCloseData();" disabled="true" ></html:radio><bean:message key="yes"/>
						&nbsp;&nbsp;
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" value="<%=RegistrationConfig.EPISODE_ISOPEN_TRUE%>" onchange="setEpisodeCloseData();" disabled="true" ></html:radio><bean:message key="no"/>
					</td>
				</tr>
			</table>
			
			<div id="divNextDateModes" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><font color="#FF0000">*</font>&nbsp;<bean:message key="plannedVisit"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();" disabled="true" ></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();" disabled="true"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();" disabled="true"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDays" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
						<td width="75%" class="tdfont" style="vertical-align: middle">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:text name="OpdNextVisitDetailFB" property="nextVisitDuration" tabindex="1" maxlength="2" size="5" onkeypress="return validatePositiveIntegerOnly(this,event)" disabled="true" />
									<html:select name="OpdNextVisitDetailFB" property="nextVisitDurationCriteria" tabindex="1" disabled="true" >
										<html:option value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC%>">
										<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC)] %>
										</html:option>
										<html:option value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_WEEKS_SPECIFIC%>">
										<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_WEEKS_SPECIFIC)] %>
										</html:option>
										<html:option value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_MONTHS_SPECIFIC%>">
										<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_MONTHS_SPECIFIC)] %>
										</html:option>
									</html:select>
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDate" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
							<td width="75%" class="tdfont" style="vertical-align: middle">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<html:text name="OpdNextVisitDetailFB" property="episodeNextVisitDate" tabindex="1" size="15" disabled="true" />
									
									</font>
								</div>				
							</td>
					</tr>
				</table>
			</div>

			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" style="vertical-align: middle">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="progNote"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" style="vertical-align: middle">
						<div align="left">
							<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="visitNotes" tabindex="1" disabled="true"
								onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<% String onClickMacroButton1 ="openVisitNotesMacros('"+OpdConfig.MACRO_PROCESS_ID_VISIT_SUMMARY+"','"+unitCode+"',event)"; %>
							<html:button value="Add Macros" property="mybutton" onclick="<%=onClickMacroButton1%>" style='cursor:pointer'  tabindex='1' disabled="true" />
						</div>
					</td>
				</tr>
			</table>
			<div id="divEpisodeSummary" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="episode"/> <bean:message key="summary"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle">
							<div align="left">
								<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="episodeSummary" tabindex="1" disabled="true" 
									onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</his:ContentTag>
		
		</logic:present>
	</logic:equal>
	
	<logic:equal name="OpdNextVisitDetailFB" property="isConfirmed" value="<%=RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED%>">
		<his:SubTitleTag key="nextvisitdetail">			
		</his:SubTitleTag>

		<logic:notEmpty name="<%=OpdConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST%>">
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="progNote"/></b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="plannedVisit"/></b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="visitNo"/></b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="enteredBy"/></b>
							</font>
						</div>
					</td>
				</tr>				
				
				<logic:iterate id="prevSummDtl" name="<%=OpdConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST%>" type="hisglobal.vo.EpisodeSummaryDetailVO">
					<tr>
						<td width="40%" class="tdfont" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="prevSummDtl" property="visitNotes"/></b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont" nowrap="nowrap" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="prevSummDtl" property="episodeNextVisitDate"/> <bean:write name="prevSummDtl" property="nextVisitDuration"/> <%=(prevSummDtl.getNextVisitCriteria()==null)?"":OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(prevSummDtl.getNextVisitCriteria())]%></b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont" nowrap="nowrap" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="prevSummDtl" property="episodeVisitNo"/></b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont" nowrap="nowrap" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="prevSummDtl" property="empName"/></b>
								</font>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>
		</logic:notEmpty>

		<logic:equal name="OpdNextVisitDetailFB" property="loginSeatId" value="<%=entrySeatId%>">
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;<bean:message key="keywords"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" valign="middle">
						<div align="left">
							<html:text name="OpdNextVisitDetailFB" property="episodeKeywords" tabindex="1" maxlength="100" size="50" onkeypress="return validateAlphaNumericOnly(event)" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:button value="Add Keywords" property="butKeyword" onclick="openUnitKeywords(event)" style='cursor:pointer'  tabindex='1'/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font>&nbsp;<bean:message key="endtreatment"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont">
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" value="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="yes"/>
						&nbsp;&nbsp;
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" value="<%=RegistrationConfig.EPISODE_ISOPEN_TRUE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="no"/>
					</td>
				</tr>
			</table>
			
			<div id="divNextDateModes" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><font color="#FF0000">*</font>&nbsp;<bean:message key="plannedVisit"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDays" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
						<td width="75%" class="tdfont" style="vertical-align: middle">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:text name="OpdNextVisitDetailFB" property="nextVisitDuration" tabindex="1" maxlength="2" size="5" onkeypress="return validatePositiveIntegerOnly(this,event)"/>
									<html:select name="OpdNextVisitDetailFB" property="nextVisitDurationCriteria" tabindex="1">
										<html:option value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC%>">
										<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC)] %>
										</html:option>
										<html:option value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_WEEKS_SPECIFIC%>">
										<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_WEEKS_SPECIFIC)] %>
										</html:option>
										<html:option value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_MONTHS_SPECIFIC%>">
										<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_MONTHS_SPECIFIC)] %>
										</html:option>
									</html:select>
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDate" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
						<td width="75%" class="tdfont" style="vertical-align: middle">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:text name="OpdNextVisitDetailFB" property="episodeNextVisitDate" tabindex="1" size="15" readonly="true" />
									<img class="button" src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style="cursor:pointer" border="1" 
										onkeypress="if(event.keyCode==13) getSchedule(event)" onclick="getSchedule(event)" title="Click to Select Schedule Date">
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" style="vertical-align: middle">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="progNote"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" style="vertical-align: middle">
						<div align="left">
							<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="visitNotes" tabindex="1" 
								onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<% String onClickMacroButton2 ="openVisitNotesMacros('"+OpdConfig.MACRO_PROCESS_ID_VISIT_SUMMARY+"','"+unitCode+"',event)"; %>
							<html:button value="Add Macros" property="mybutton" onclick="<%=onClickMacroButton2%>" style='cursor:pointer'  tabindex='1'/>
						</div>
					</td>
				</tr>
			</table>
			<div id="divEpisodeSummary" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="episode"/> <bean:message key="summary"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle">
							<div align="left">
								<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="episodeSummary" tabindex="1" 
									onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</his:ContentTag>
		</logic:equal>
		
		<logic:notEqual name="OpdNextVisitDetailFB" property="loginSeatId" value="<%=entrySeatId%>">
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>&nbsp;<bean:message key="keywords"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" valign="middle">
						<div align="left">
							<html:text name="OpdNextVisitDetailFB" property="episodeKeywords" tabindex="1" maxlength="100" size="50" onkeypress="return validateAlphaNumericOnly(event)" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:button value="Add Keywords" property="butKeyword" onclick="openUnitKeywords(event)" style='cursor:pointer'  tabindex='1'/>
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font>&nbsp;<bean:message key="endtreatment"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont">
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" disabled="true" value="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="yes"/>
						&nbsp;&nbsp;
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" disabled="true" value="<%=RegistrationConfig.EPISODE_ISOPEN_TRUE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="no"/>
					</td>
				</tr>
			</table>
			
			<div id="divNextDateModes" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><font color="#FF0000">*</font>&nbsp;<bean:message key="plannedVisit"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" disabled="true" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" disabled="true" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" disabled="true" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDays" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
						<td width="75%" class="tdfont" style="vertical-align: middle">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="OpdNextVisitDetailFB" property="nextVisitDuration"/>
									<logic:present name="OpdNextVisitDetailFB" property="nextVisitDurationCriteria">
									<bean:define id="idNextVisitDurCriteria" name="OpdNextVisitDetailFB" property="nextVisitDurationCriteria" type="java.lang.String"></bean:define>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(idNextVisitDurCriteria)]%>
									</logic:present>
									
									<html:hidden name="OpdNextVisitDetailFB" property="nextVisitDuration" />
									<html:hidden name="OpdNextVisitDetailFB" property="nextVisitDurationCriteria" />
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDate" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
						<td width="75%" class="tdfont" style="vertical-align: middle">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="OpdNextVisitDetailFB" property="episodeNextVisitDate"/>
									<html:hidden name="OpdNextVisitDetailFB" property="episodeNextVisitDate"/>
								</font>
							</div>		
								
						</td>
					</tr>
				</table>
			</div>

			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" style="vertical-align: middle">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="progNote"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" style="vertical-align: middle">
						<!--<div align="left">
							<bean:write name="OpdNextVisitDetailFB" property="visitNotes"/>
							<html:hidden name="OpdNextVisitDetailFB" property="visitNotes"/>
						</div>-->
						<div align="left">
							<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="visitNotes" tabindex="1" disabled="true"
								onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							
							<html:button value="Add Macros" property="mybutton" disabled="true" style='cursor:pointer'  tabindex='1'/>
						</div>	
					</td>
				</tr>
			</table>
			<div id="divEpisodeSummary" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="episode"/> <bean:message key="summary"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle">
							<div align="left">
								<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="episodeSummary" tabindex="1" 
									onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</his:ContentTag>
		</logic:notEqual>		
		
	</logic:equal>
		
	</his:statusTransactionInProcess>
	
	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
			<logic:equal name="OpdNextVisitDetailFB" property="isConfirmed" value="<%=RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED%>">
				<logic:notPresent name="OpdNextVisitDetailFB" property="triageDuration" >
				<img class='button'	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex='1' onclick="submitFormOnValidate(validateSave(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateSave(),'SAVE');")>
				</logic:notPresent>
			</logic:equal>
			<logic:equal name="OpdNextVisitDetailFB" property="isConfirmed" value="<%=RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED%>">
				<logic:equal name="OpdNextVisitDetailFB" property="loginSeatId" value="<%=entrySeatId%>">
					<img class='button'	src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" tabindex='1' onclick="submitFormOnValidate(validateSave(),'MODIFYSAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateSave(),'MODIFYSAVE');")>
				</logic:equal>
			</logic:equal>
			<logic:notPresent name="OpdNextVisitDetailFB" property="triageDuration" >
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
			</logic:notPresent>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusTransactionInProcess>
		
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusUnsuccessfull>
	</his:ButtonToolBarTag>

</his:TransactionContainer>

<html:hidden name="OpdNextVisitDetailFB" property="hmode" />
<html:hidden name="OpdNextVisitDetailFB" property="serialNo" />
<html:hidden name="OpdNextVisitDetailFB" property="episodeCode" />
<html:hidden name="OpdNextVisitDetailFB" property="episodeVisitNo" />
<html:hidden name="OpdNextVisitDetailFB" property="admissionNo" />
<html:hidden name="OpdNextVisitDetailFB" property="empNo" />
<html:hidden name="OpdNextVisitDetailFB" property="loginSeatId" />
<html:hidden name="OpdNextVisitDetailFB" property="departmentUnitCode" />
<html:hidden name="OpdNextVisitDetailFB" property="deskType" />
<html:hidden name="OpdNextVisitDetailFB" property="isConfirmed" />

<html:hidden name="OpdNextVisitDetailFB" property="isEpisodeAlreadyOpen" />
<html:hidden name="OpdNextVisitDetailFB" property="seatId" />
<html:hidden name="OpdNextVisitDetailFB" property="episodeTypeCode" />
<html:hidden name="OpdNextVisitDetailFB" property="episodeDate" />
<html:hidden name="OpdNextVisitDetailFB" property="isPatDead" />

<html:hidden name="OpdNextVisitDetailFB" property="currentPage"/>
