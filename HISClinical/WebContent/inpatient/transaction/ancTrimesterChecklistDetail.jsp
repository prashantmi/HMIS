<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="inpatient.InpatientConfig"%>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/inpatient/js/anc_validations.js"/>
<his:javascript src="/inpatient/js/anc_trimester_checklist.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:TitleTag key="ancTrimesterChklst">
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
<his:statusTransactionInProcess>
	<his:ContentTag>
		<table width="100%" align="center" border="0" cellpadding="2" cellspacing="1">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="pattrimester" />
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="ANCTrimesterChecklistDetailFB" property="trimester"/>
						</font>
					</div>
				</td>
				<td width="50%" class="tdfonthead" colspan="2">
					<div align="center">
						<a style="cursor:pointer" onclick="popupApgarDetail(event)">
							<b><bean:message key="allTriWiseChkList"/></b>
						</a>
					</div>
				</td>				
			</tr>
		</table>		
	</his:ContentTag>

	<his:SubTitleTag key="prevChklstDtl">
	</his:SubTitleTag>
	<logic:notEmpty name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_ADDED_DRUGS_CHECKLIST%>"> 
	<his:ContentTag>		
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr bgcolor="#c56b39"> 
				<td colspan="4">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">							
							<b>Drug Detail</b>	
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="chklstItem"/></b>
						</font>	
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="dateConduct"/></b>
						</font>	
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="trimester"/></b>
						</font>	
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>&nbsp;</b>
						</font>	
					</div>
				</td>
			</tr>
			<logic:iterate id="ancChkLstDtlVO" indexId="idxChkDrug" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_ADDED_DRUGS_CHECKLIST %>" type="hisglobal.vo.ANCChecklistDetailVO">
				<tr>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="checklistName"/>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="conductDate"/>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="trimester"/>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
						</div>	
					</td>
				</tr>
			</logic:iterate>
		</table>		
	</his:ContentTag>
	</logic:notEmpty>

	<logic:notEmpty name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_ADDED_INVESTIGATIONS_CHECKLIST%>">
	<his:ContentTag>		
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr bgcolor="#c56b39"> 
				<td colspan="4">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">							
							<b>Investigation Detail</b>	
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="chklstItem"/></b>
						</font>	
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="dateConduct"/></b>
						</font>	
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="trimester"/></b>
						</font>	
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="chllstresult"/></b>
						</font>	
					</div>
				</td>
			</tr>
			<logic:iterate id="ancChkLstDtlVO" indexId="idxChkDrug" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_ADDED_INVESTIGATIONS_CHECKLIST %>" type="hisglobal.vo.ANCChecklistDetailVO">
				<tr>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="checklistName"/>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="conductDate"/>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="trimester"/>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="result"/>
							</font>	
						</div>	
					</td>
				</tr>
			</logic:iterate>
		</table>		
	</his:ContentTag>
	</logic:notEmpty>

	<his:SubTitleTag name="CheckList Detail">
	</his:SubTitleTag> 
	<logic:notEmpty name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS%>">
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr bgcolor="#c56b39"> 
				<td colspan="4">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">							
							<b><bean:message key="drugimmu"/></b>	
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="chklstItem"/></b>
						</font>	
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="dateConduct"/></b>
						</font>	
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="trimester"/></b>
						</font>	
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>&nbsp;</b>
						</font>	
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfont">
					<div align="center">					
						<html:select name="ANCTrimesterChecklistDetailFB" property="drugChecklistId" tabindex="1" onchange="setDrugChklstName(this)">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS%>">
								<html:options collection="<%= InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS%>" property="checklistId" labelProperty="checklistName" />
							</logic:present>
						</html:select>
						<html:hidden name="ANCTrimesterChecklistDetailFB" property="drugChecklistName" />
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">
						<his:date name="drugConductDate" dateFormate="%d-%b-%Y" ></his:date>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">
						<html:hidden name="ANCTrimesterChecklistDetailFB" property="delDrugCheckListIndex" />						
						<img class="button" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' style='cursor:pointer' onclick ="addDrugRow()" onkeypress="if(event.keyCode==13) addDrugRow();">
					</div>	
				</td>
			</tr>
			<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS_ADDED%>">
			<input type="hidden" name="addedDrugCount" value="1" />
			<logic:iterate id="ancChkLstDtlVO" indexId="idxChkDrug" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS_ADDED %>" type="hisglobal.vo.ANCChecklistDetailVO">
				<tr>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="checklistName"/>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="conductDate"/>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="trimester"/>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<img class="button" src='<his:path src="/hisglobal/images/minus.gif"/>' style='cursor:pointer' onclick ="minusDrugRow('<%=idxChkDrug%>')" onkeypress="if(event.keyCode==13) minusDrugRow('<%=idxChkDrug%>');">
						</div>	
					</td>
				</tr>
			</logic:iterate>
			</logic:present>
		</table>		
	</his:ContentTag>
	</logic:notEmpty>

	<logic:notEmpty name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS%>">
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr bgcolor="#c56b39"> 
				<td colspan="5">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">							
							<b><bean:message key="chklstInvest"/></b>	
						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="chklstItem"/></b>
						</font>	
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="dateConduct"/></b>
						</font>	
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="chllstresult"/></b>
						</font>	
					</div>
				</td>
				<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="trimester"/></b>
						</font>	
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>&nbsp;</b>
						</font>	
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfont">
					<div align="center">
						<html:select name="ANCTrimesterChecklistDetailFB" property="invstChecklistId" tabindex="1" onchange="setInvstChklstName(this)">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS%>">
								<html:options collection="<%= InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS%>" property="checklistId" labelProperty="checklistName" />
							</logic:present>
						</html:select>
						<html:hidden name="ANCTrimesterChecklistDetailFB" property="invstChecklistName" />
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">
						<his:date name="invstConductDate" dateFormate="%d-%b-%Y" ></his:date>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">												
						<html:text name="ANCTrimesterChecklistDetailFB" property="invstResult" tabindex="1" maxlength="60" size="25" onkeypress="return validateAlphabetsOnly(event,this)"></html:text>
					</div>
				</td>
				<td width="15%" class="tdfont">
					<div align="center">
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:hidden name="ANCTrimesterChecklistDetailFB" property="delInvstCheckListIndex" />						
						<img class="button" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' style='cursor:pointer' onclick ="addInvstRow()" onkeypress="if(event.keyCode==13) addInvstRow();">
					</div>	
				</td>
			</tr>
			<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS_ADDED%>">
			<input type="hidden" name="addedInvestigationCount" value="1" />
			<logic:iterate id="ancChkLstDtlVO" indexId="idxChkInvst" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS_ADDED %>" type="hisglobal.vo.ANCChecklistDetailVO">
				<tr>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="checklistName"/>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="conductDate"/>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="result"/>
							</font>	
						</div>
					</td>
					<td width="15%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="ancChkLstDtlVO" property="trimester"/>
							</font>	
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
							<img class="button" src='<his:path src="/hisglobal/images/minus.gif"/>' style='cursor:pointer' onclick ="minusInvstRow('<%=idxChkInvst%>')" onkeypress="if(event.keyCode==13) minusInvstRow('<%=idxChkInvst%>');">
						</div>	
					</td>
				</tr>
			</logic:iterate>
			</logic:present>
		</table>		
	</his:ContentTag>
	</logic:notEmpty>
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer"  tabindex='2' onclick =  "submitFormOnValidate(validateSave(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateSave(),'SAVE');")>		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')">
	</his:statusTransactionInProcess>
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusUnsuccessfull>
</his:ButtonToolBarTag>
	

<html:hidden name="ANCTrimesterChecklistDetailFB" property="hmode"/>
<html:hidden name="ANCTrimesterChecklistDetailFB" property="entryDate"/>
<html:hidden name="ANCTrimesterChecklistDetailFB" property="patCrNo"/>
<html:hidden name="ANCTrimesterChecklistDetailFB" property="episodeCode"/>
<html:hidden name="ANCTrimesterChecklistDetailFB" property="episodeVisitNo"/>
<html:hidden name="ANCTrimesterChecklistDetailFB" property="admissionNo"/>
<html:hidden name="ANCTrimesterChecklistDetailFB" property="departmentUnitCode"/>
<html:hidden name="ANCTrimesterChecklistDetailFB" property="wardCode"/>
<html:hidden name="ANCTrimesterChecklistDetailFB" property="userSeatId"/>

<html:hidden name="ANCTrimesterChecklistDetailFB" property="gravidaNo"/>
<html:hidden name="ANCTrimesterChecklistDetailFB" property="trimester"/>
<html:hidden name="ANCTrimesterChecklistDetailFB" property="ancNo"/>
<html:hidden name="ANCTrimesterChecklistDetailFB" property="gestationPeriod"/>
<html:hidden name="ANCTrimesterChecklistDetailFB" property="gestationStartDate"/>