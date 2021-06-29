
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/opd/js/desk_treatment_detail.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:TitleTag>
	<his:name>
		<bean:message key="pattreatmentdtl" />
	</his:name>
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />

<%	String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");	%>
<html:hidden name="PatientTreatmentDetailFB" property="" value="<%=sysDate%>"/>

<his:statusTransactionInProcess>

	<logic:notEmpty name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST%>">
		<his:SubTitleTag name="">
			<his:name><bean:message key="previous" /> <bean:message key="drugdtl" /></his:name>
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table id="tblDrugDetail" width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="drugname" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="dosage" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="frequency" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="startday" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="endday" />
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="instructions" />
								</b>
							</font>
						</div>
					</td>
					<td width="5%" class="tdfonthead"></td>
				</tr>
				<logic:iterate id="drugDtlVO" indexId="i" name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST%>" type="hisglobal.vo.PatDrugTreatmentDetailVO">
				<tr id="trSpecimen">
					<td width="25%" class="tdfont">
						<div align="center">
							<html:hidden name="PatientTreatmentDetailFB" property="selDrugId" 
								value="<%=drugDtlVO.getDrugId()%>" />
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="drugName"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="doseName"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
							<html:select name="PatientTreatmentDetailFB" property="selFrequencyId" tabindex="1" disabled="true" 
								value="<%=drugDtlVO.getFrequencyId() %>">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" >
								<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" property="value" labelProperty="label"/>
								</logic:present>
							</html:select>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="startDate"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="endDate"/>
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="remarks"/>
								</b>
							</font>
						</div>
					</td>
					<td width="5%" class="tdfont">
					</td>
				</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>
	</logic:notEmpty>	
	<his:SubTitleTag name="">
		<his:name><bean:message key="drugdtl" /></his:name>
		<td width="10%" class="tdfonthead">
			<div align="center">
				<img class="button" style="cursor: pointer" alt="Search" title="Search"
				src='<his:path src="/hisglobal/images/btn-search.png"/>'  
				onclick="openPopup(createFHashAjaxQuery('/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=SEARCHDRUG'),event,300,600);">
			</div>
		</td>
	</his:SubTitleTag>
	<html:hidden name="PatientTreatmentDetailFB" property="drugDetailRows" />
	<his:ContentTag>
		<table id="tblDrugDetail" width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="drugname" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="dosage" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="frequency" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="dosedays" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="startday" />
							</b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="instructions" />
							</b>
						</font>
					</div>
				</td>
				<td width="5%" class="tdfonthead"></td>
			</tr>
			<logic:iterate id="drugDtlVO" indexId="i" name="<%=OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST%>" type="hisglobal.vo.PatDrugTreatmentDetailVO">
			<tr id="trSpecimen">
				<td width="25%" class="tdfont">
					<div align="center">
						<html:hidden name="PatientTreatmentDetailFB" property="selDrugId" 
							value='<%=drugDtlVO.getDrugId().split("#")[0]%>' />
						<html:hidden name="PatientTreatmentDetailFB" property="selDrugItemTypeId"
							value='<%=drugDtlVO.getDrugId().split("#")[1]%>' />
						<html:text name="PatientTreatmentDetailFB" property="selDrugName" maxlength="100" size="25" tabindex="1" 
							value="<%=drugDtlVO.getDrugName() %>" 
							onkeypress="return validateAlphaOnly(this,event)" 
							onkeyup="getDrugDropDownData(event,this);" 
							onblur="callOnBlur()"></html:text>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<% if(drugDtlVO.getDoseId().equals("0")) { %>
							<html:hidden name="PatientTreatmentDetailFB" property="selDoseId" value="<%=drugDtlVO.getDoseId()%>"></html:hidden>
							<html:text name="PatientTreatmentDetailFB" property="selDoseName" tabindex="1" 
								value="<%=drugDtlVO.getDoseName()%>" maxlength="20" size="8"
								onkeypress="return validateAlphaOnly(this,event)" ></html:text>
						<% } else { %>
							<html:select name="PatientTreatmentDetailFB" property="selDoseId" tabindex="1" value="<%=drugDtlVO.getDoseId() %>">
								<html:option value="<%=drugDtlVO.getDoseId()%>">
									<bean:write name="drugDtlVO" property="doseName"/> 
								</html:option>
							</html:select>
							<html:hidden name="PatientTreatmentDetailFB" property="selDoseName" value="<%=drugDtlVO.getDoseName()%>"></html:hidden>
						<% } %>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:select name="PatientTreatmentDetailFB" property="selFrequencyId" tabindex="1" 
							value="<%=drugDtlVO.getFrequencyId() %>">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:text name="PatientTreatmentDetailFB" property="selDays" maxlength="2" size="4" tabindex="1" 
							value="<%=drugDtlVO.getDays() %>" onkeypress="return validateIntegerOnly(this,event)"></html:text>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:text name="PatientTreatmentDetailFB" property="selStartDay" maxlength="2" size="4" tabindex="1" 
							value="<%=drugDtlVO.getStartDate() %>" onkeypress="return validateIntegerOnly(this,event)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">
						<html:text name="PatientTreatmentDetailFB" property="selInstructions" maxlength="500" size="25" tabindex="1" 
							value="<%=drugDtlVO.getRemarks() %>" onkeypress="return validateAlphaNumOnly(this,event)" onkeyup="goToNextIndex(event);"></html:text>
					</div>
				</td>
				<td width="5%" class="tdfont" id="tdButtonPlus">
					<%if(i==0){%>
					<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateRows(),'ADDDRUGROW');" onclick="submitFormOnValidate(validateRows(),'ADDDRUGROW');">
					<% } else { %>
					<img class="button" id="deleteButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) deleteRow(<%=i%>);" onclick="deleteRow(<%=i%>)">
					<%} %>
				</td>
			</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1" onclick="submitFormOnValidate(validateRows(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateRows(),'SAVE');")>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
</his:ButtonToolBarTag>

<div id="divDropDown" class="hisStyle.css" style="display: none; position: absolute;">
	<select id="cboDropDown" size="10" tabindex="1" onKeyDown="onKeyDownDrop(event)" onchange="onChangeDrop()" 
		ondblclick="setClickedValue()" onclick="setClickedValue()">
		<option value="-1"></option>
	</select>
</div>

<html:hidden name="PatientTreatmentDetailFB" property="hmode" />
<html:hidden name="PatientTreatmentDetailFB" property="patCrNo" />

<html:hidden name="PatientTreatmentDetailFB" property="episodeCode"/>
<html:hidden name="PatientTreatmentDetailFB" property="episodeVisitNo"/>
<html:hidden name="PatientTreatmentDetailFB" property="admissionNo"/>