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

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="opd.transaction.controller.fb.ICDEntryFormNewFB"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Set"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/> 
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/utility/dynamicdesk/js/desk.js" />

<script language="JavaScript" src="/HISClinical/registration/js/validationCommon.js"></script>
<script language="JavaScript" src="/HISClinical/registration/js/popup.js"></script>
<script language="JavaScript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<his:javascript src="/hisglobal/js/validationCalls.js"/>

<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/registration.js"/>

<his:javascript src="/opd/js/opd_icd_entry_form.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<script>
// Call Function onload 
window.onload=function()
{
	if(callThisOnload)
	{
		callThisOnload();
	}
}
</script>


<his:statusNew>
	
	<%
		List lstPatients = (List) session.getAttribute(OpdConfig.ICD_ENTRY_FORM_PATIENT_LIST);
		Set setPatients = (Set) session.getAttribute(OpdConfig.ICD_ENTRY_FORM_SELECTED_PATIENT_SET); 
	%>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<bean:message key="crNo"/>
					</div>
				</td>
				<td width="15%" class="tdfonthead">
					<div align="center">
						<bean:message key="name"/>
					</div>
				</td>
				<td width="15%" class="tdfonthead">
					<div align="center">
						<bean:message key="fromDt"/>
					</div>
				</td>
				<td width="15%" class="tdfonthead">
					<div align="center">
						<bean:message key="toDate"/>
					</div>
				</td>
				<td width="15%" class="tdfonthead">
					<div align="center">
						<bean:message key="dept(unit)"/>
					</div>
				</td>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<bean:message key="room"/>
					</div>
				</td>
				<td width="15%" class="tdfonthead">
					<div align="center">
						<bean:message key="status"/>
					</div>
				</td>
				<td width="5%" class="tdfonthead">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:text name="ICDEntryFormNewFB" property="patCrNo" maxlength="<%=Config.CR_NO_FORMAT_SIZE%>" tabindex="1" size="15" 
							onkeydown="setPrevValue(this, event);"  onkeypress="if(event.keyCode!=13) return validateNumeric(event); else return false;"></html:text>
					</div>
				</td>
				<td width="15%" class="tdfont">
					<div align="center">
						<html:text name="ICDEntryFormNewFB" property="patName" maxlength="30" tabindex="1" size="20" 
							onkeypress="if(event.keyCode!=13) return validateAlphabetsOnly(event); else return false;"></html:text>
					</div>
				</td>
				<td class="tdfont" width="15%">
					<div align="center">
						&nbsp;<bean:define id="frmDate" name="ICDEntryFormNewFB" property="fromDate" type="java.lang.String"></bean:define>
						<his:date name="fromDate" value="<%=frmDate%>"></his:date>
					</div>
				</td>
				<td class="tdfont" width="15%">
					<div align="center">
						&nbsp;<bean:define id="strToDate" name="ICDEntryFormNewFB" property="toDate" type="java.lang.String"></bean:define>
						<his:date name="toDate" value="<%=strToDate%>"></his:date>
					</div>
				</td>
				<td class="tdfont" width="15%">
					<div align="center">
						<html:select name="ICDEntryFormNewFB" property="departmentUnitCode" tabindex="1" styleClass="comboList" style="width:100px;"  onchange="getRoom(this);">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS%>" >
								<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
				<td class="tdfont" width="10%">
					<div align="center">
						<bean:define id="roomCd" name="ICDEntryFormNewFB" property="roomCode" type="java.lang.String"></bean:define>
						<input type="hidden" name="hiddenRoomCode" value="<%=roomCd%>">
						<html:select name="ICDEntryFormNewFB" property="roomCode" tabindex="1" styleClass="comboList">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.OPD_ROOM_LIST%>" >
								<html:options collection="<%=OpdConfig.OPD_ROOM_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
				<td class="tdfont" width="15%">
					<div align="center">
						<html:select name="ICDEntryFormNewFB" property="icdCodeFlag" tabindex="1" styleClass="comboList">
							<html:option value="<%=OpdConfig.ICD_ENTRY_FORM_ICD_FLAG_UNREAD%>"><%=OpdConfig.ICD_ENTRY_FORM_ICD_FLAG_ARR[Integer.parseInt(OpdConfig.ICD_ENTRY_FORM_ICD_FLAG_UNREAD)]%></html:option>
							<html:option value="<%=OpdConfig.ICD_ENTRY_FORM_ICD_FLAG_READ_NOT_ICD%>"><%=OpdConfig.ICD_ENTRY_FORM_ICD_FLAG_ARR[Integer.parseInt(OpdConfig.ICD_ENTRY_FORM_ICD_FLAG_READ_NOT_ICD)]%></html:option>
						</html:select>
					</div>
				</td>
				<td width="5%" class="tdfonthead">
					<div align="right">
						<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<img class="button" src='<his:path src="/hisglobal/images/btn-search.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitFormOnValidate(validateSearch('<%=Config.CR_NO_FORMAT_SIZE%>'),'SEARCH')" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateSearch('<%=Config.CR_NO_FORMAT_SIZE%>'),'SEARCH')">
						</font>	
					</div>
				</td>
			</tr>
	</table>
	</his:ContentTag>		

	<%
		PaginationFB fbPage= new PaginationFB();
		pageContext.setAttribute("fbPagination",fbPage);
		fbPage.setCurrentPage(((ICDEntryFormNewFB)request.getAttribute("ICDEntryFormNewFB")).getCurrentPage());
		fbPage.setObjArrName(OpdConfig.ICD_ENTRY_FORM_PATIENT_LIST);
		fbPage.setTitleRequired(false);
		fbPage.setAppendInTitle("Patients");
		fbPage.setMaxRecords(10);
	%>

	<his:PaginationTag name="fbPagination"></his:PaginationTag>
	<his:ContentTag>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr valign="top"><td valign="top">
		<table width="100%" cellpadding="0" cellspacing="1" border="0">
			<tr>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<B>
							<bean:message key="select"/>
						</B>
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
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							<bean:message key="name"/>
						</b>
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" title="Ascending Order" onkeypress="if(event.keyCode==13)getOrderBy('PATNAME','0');" onClick="getOrderBy('PATNAME','0');">
						<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" title="Descending Order" onkeypress="if(event.keyCode==13)getOrderBy('PATNAME','1');" onClick="getOrderBy('PATNAME','1');">	
					</div>
				</td>
				<td width="35%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top" colspan="2">
					<div align="center">
						<b>
							<bean:message key="icdCode"/>
						</b>	
					</div>
				</td>
				<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							<bean:message key="dept(unit)"/>
						</b>	
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							<bean:message key="visitDate"/>
						</b>	
					</div>
				</td>
				<%--<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							<bean:message key="gender/age"/>
						</b>	
					</div>
				</td>
				<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							<bean:message key="primCat"/>
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_up.gif" tabindex="1" border="0" style="cursor: pointer" title="Ascending Order" onkeypress="if(event.keyCode==13)getOrderBy('PRICAT','0');" onClick="getOrderBy('PRICAT','0');">
							<img class="button" src="/HIS/hisglobal/images/avai/arrow_down.gif" tabindex="1" border="0" style="cursor: pointer" title="Descending Order" onkeypress="if(event.keyCode==13)getOrderBy('PRICAT','1');" onClick="getOrderBy('PRICAT','1');">	
						</b>	
					</div>
				</td>
				<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
					<div align="center">
						<b>
							&nbsp;
						</b>	
					</div>
				</td>--%>
			</tr>
		<%
			if(lstPatients!=null && lstPatients.size()>0)
			{
				int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
				int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
	
				for(int i= startIndex;i<=endIndex ; i++)					
				{
					PatientDetailVO voPatient = (PatientDetailVO)lstPatients.get(i);	
		%>
					<tr>
						<%	
							String color = "#000000";
							if(voPatient.getEpisodeVisitNo().equals("1"))
								color = OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR;
							else
								color = OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR;
						%>
						<td width="5%" class="tdfont">
							<div align="center">
								<% String chkFun = "enablePatRow(this,'"+voPatient.getPatCrNo()+"','"+voPatient.getEpisodeCode()+"','"+voPatient.getEpisodeVisitNo()+"',true,'"+voPatient.getEpisodeDate()+"')"; %>
								<% if(setPatients!=null && setPatients.contains(voPatient.getPatCrNo()+"#"+voPatient.getEpisodeCode()+"#"+voPatient.getEpisodeVisitNo())){ %>
								<%--<html:checkbox name="ICDEntryFormNewFB" property="selectedPatCrNo" value="<%=voPatient.getPatCrNo() %>" onchange="<%=chkFun%>" ></html:checkbox>--%>
								<input type="checkbox" name="selectedPatCrNo" value='<%=voPatient.getPatCrNo()+"#"+voPatient.getEpisodeCode()+"#"+voPatient.getEpisodeVisitNo()%>' checked="checked" onchange="<%=chkFun%>" />
								<%	} else { %>
								<%--<html:checkbox name="ICDEntryFormNewFB" property="selectedPatCrNo" value="<%=voPatient.getPatCrNo() %>" onchange="<%=chkFun%>" ></html:checkbox>--%>
								<input type="checkbox" name="selectedPatCrNo" value='<%=voPatient.getPatCrNo()+"#"+voPatient.getEpisodeCode()+"#"+voPatient.getEpisodeVisitNo()%>' onchange="<%=chkFun%>" />
								<%	} %>
							</div>
							<div id="divICDHide#<%=voPatient.getPatCrNo()%>#<%=voPatient.getEpisodeCode()%>#<%=voPatient.getEpisodeVisitNo()%>" style="display: none;">
								<html:text name="ICDEntryFormNewFB" property="selectedPatICD" value='<%=(voPatient.getPatDiagnosisDtl()!=null && !voPatient.getPatDiagnosisDtl().trim().equals(""))?voPatient.getPatDiagnosisDtl():""%>' disabled="true"/>
								<input name="selectedPatICDTxtLen" value="0">

								<input type="checkbox" name="seenPat" value='<%=voPatient.getPatCrNo()+"#"+voPatient.getEpisodeCode()+"#"+voPatient.getEpisodeVisitNo()%>'/>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
							 	<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=voPatient.getPatCrNo() %>
							 	</font>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
							 	<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<% //voPatient.setPatientName(); %>
							 		<%=voPatient.getPatFirstName() %>
							 	</font>
							</div>
						</td>

						<td width="34%" class="tdfont">
							<div id="divICD#<%=voPatient.getPatCrNo()%>#<%=voPatient.getEpisodeCode()%>#<%=voPatient.getEpisodeVisitNo()%>" align="center">
							</div>
						</td>
						<td width="1%" class="tdfont">
							<div id="divICDPlus#<%=voPatient.getPatCrNo()%>#<%=voPatient.getEpisodeCode()%>#<%=voPatient.getEpisodeVisitNo()%>" align="center" style="display: none;">
								<img class="button" src="/HIS/hisglobal/images/avai/plus.gif" tabindex="1" border="0" style="cursor: pointer" title="Add More ICD Code" onkeypress="if(event.keyCode==13) addMoreICD('<%=voPatient.getPatCrNo() %>','<%=voPatient.getEpisodeCode()%>','<%=voPatient.getEpisodeVisitNo()%>');" onClick="addMoreICD('<%=voPatient.getPatCrNo()%>','<%=voPatient.getEpisodeCode()%>','<%=voPatient.getEpisodeVisitNo()%>');">
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
								 <font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=voPatient.getDepartment() %> (<%=voPatient.getDepartmentUnit() %>)
							 	</font>
								<input type="hidden" name="patIsDocPresent" value="1" />								
								<script type="text/javascript">
									setICDCodesLists('<%=voPatient.getPatCrNo() %>','<%=voPatient.getEpisodeCode()%>','<%=voPatient.getEpisodeVisitNo()%>');
								</script>
							</div>
						</td>
						<td width="10%" class="tdfont">
							<div align="center">
								 <font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=voPatient.getEpisodeDate() %>
							 	</font>
							</div>
						</td>
						<%-- <td width="10%" class="tdfont">
							<div align="center">
								 <font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=voPatient.getPatGenderAge() %>
							 	</font>
							</div>
						</td>
						<td width="15%" class="tdfont">
							<div align="center">
							 	<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							 		<%=voPatient.getPatPrimaryCat() %>
							 	</font>
							</div>
						</td>
						<td width="5%" class="tdfont">
							<div align="center">
								<% if(voPatient.getPatIsDocPresent().equals("0")) { %>
									<img class="button" src='<his:path src="/hisglobal/images/Red.png"/>' width="90px" height="18px" onclick="getScanDocPopup(false)"  onkeypress="if(event.keyCode==13) getScanDocPopup(false)">
								<% } else { %>
									<img class="button" src='<his:path src="/hisglobal/images/Green.png"/>' width="90px" height="18px" style="cursor:pointer" tabindex="1" onclick="getScanDocPopup(true,'<%=voPatient.getPatCrNo()%>','<%=voPatient.getEpisodeCode()%>','<%=voPatient.getEpisodeVisitNo()%>','<%=voPatient.getEpisodeDate()%>')" 
										onkeypress="if(event.keyCode==13) getScanDocPopup(true,'<%=voPatient.getPatCrNo()%>','<%=voPatient.getEpisodeCode()%>','<%=voPatient.getEpisodeVisitNo()%>','<%=voPatient.getEpisodeDate()%>')">
								<% } %>
							</div>
						</td> --%>
					</tr>
		<%		
				}
			}
			else
			{
		%>
					<tr>
						<td width="100%" class="tdfont" colspan="6">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									No Patient Found
								</font>
							</div>
						</td>
					</tr>
		<%
			}
		%>
		</table></td>
		<td id="tdScannedDocs" valign="top">
			<div id="divScannedDocs" align="center">
			</div>
		</td>
		</tr></table>
	</his:ContentTag>		
	
	
	<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td width="70%"></td>
				<td width="30%">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show</font>
						<img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' style="cursor: pointer;" onclick="showLegends();">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide</font>
						<img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' style="cursor: pointer;" onclick="showLegendsNone();">
					</div>
				</td>
			</tr>
		</table>
	</his:SubTitleTag>
	<div id="divLegends" style="display:none;">
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
				<tr>
					<td width="10%">
						<font color="<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR_NAME%>: 
						</font>
					</td>
					<td width="90%">
						<div align="left">
							<font color="<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								First Visit
							</font>
						</div>
					</td>
				</tr>
				<tr>
					<td width="10%">
						<font color="<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR_NAME%>:
						</font>
					</td>
					<td width="90%">
						<div align="left">
							<font color="<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								Old Visit
							</font>
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
	</div>

</his:statusNew>

<his:ButtonToolBarTag>
		<his:statusNew>
			<logic:notEmpty name="<%=OpdConfig.ICD_ENTRY_FORM_PATIENT_LIST%>">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitFormOnValidate(validateSave(),'SAVE')" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateSave(),'SAVE')">
			</logic:notEmpty>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('NEW')" onclick="submitForm('NEW')">
		</his:statusNew>
		
		<his:statusRecordFound>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
		</his:statusRecordFound>
		<his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
		</his:statusTransactionInProcess>
</his:ButtonToolBarTag>

<html:hidden name="ICDEntryFormNewFB" property="hmode" value="NEW"/>
<html:hidden name="ICDEntryFormNewFB" property="selectedPatLen"/>
<html:hidden name="ICDEntryFormNewFB" property="currentPage"/>
<html:hidden name="ICDEntryFormNewFB" property="orderBy"/>
<html:hidden name="ICDEntryFormNewFB" property="tabMode"/>

