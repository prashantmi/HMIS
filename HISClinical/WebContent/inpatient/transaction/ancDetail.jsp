<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>

<his:css src="/inpatient/css/anc_style.css"/>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:javascript src="/inpatient/js/anc_validations.js"/>
<his:javascript src="/inpatient/js/anc_history_detail.js" />
<his:javascript src="/inpatient/js/anc_detail.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<script>
function submitToDesk(mode,hmode)
{
	//alert("A--"+" mode:- "+mode+" hmode:- "+hmode);
	//if(goToDashBoard)	goToDashBoard();
		
	if(parent.gotToMainTab)
		parent.gotToMainTab("Patient Dashboard", true);
	else if(parent.parent.gotToMainTab)
		parent.parent.gotToMainTab("Patient Dashboard", false);

}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:TitleTag key="antendtl">
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
<his:statusTransactionInProcess>

<html:hidden name="ANCDetailFB" property="ancDetailGetFlag"/>

	<his:SubTitleTag key="antendtl">
		<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
			<tr><td width="1%" align="right" valign="middle">				
				<img id="imgANDtl" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-up.png"; onclick="onclickImage(this)"/>
			</td></tr>
		</table>
	</his:SubTitleTag>
	
<div id="divANDtl" style="display: block;">
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<!-- <font color="#FF0000">*</font> -->
								<bean:message key="ancno"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDetailFB" property="ancNo" tabindex="1" maxlength="30" styleClass="ancTextboxNormal" onkeypress="return validateAlphaNumOnly(this,event)" ></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font color="#FF0000">*</font><bean:message key="gravidano"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDetailFB" property="gravidaNo" tabindex="1" maxlength="2" styleClass="ancTextboxSmall" onkeypress="return validateNumeric(event)" onblur="setHistoryRows(this)"></html:text>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="parityno"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDetailFB" property="parityNo" tabindex="1" maxlength="2" readonly="true" styleClass="ancTextboxSmall" onkeypress="return validateNumeric(event)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="abortusno"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDetailFB" property="abortusNo" tabindex="1" maxlength="2" readonly="true" styleClass="ancTextboxSmall" onkeypress="return validateNumeric(event)"></html:text>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="detectionMode"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:select name="ANCDetailFB" property="detectionMethod" tabindex="1" styleClass="ancComboNormal">
							<html:option value="-1">Select Value</html:option>
							<html:option value="<%=InpatientConfig.PREGNANCY_DETECTION_METHOD_ULTRA_SOUND%>"><%=InpatientConfig.PREGNANCY_DETECTION_ARR[Integer.parseInt(InpatientConfig.PREGNANCY_DETECTION_METHOD_ULTRA_SOUND)]%></html:option>
							<html:option value="<%=InpatientConfig.PREGNANCY_DETECTION_METHOD_UPT%>"><%=InpatientConfig.PREGNANCY_DETECTION_ARR[Integer.parseInt(InpatientConfig.PREGNANCY_DETECTION_METHOD_UPT)]%></html:option>
							<html:option value="<%=InpatientConfig.PREGNANCY_DETECTION_METHOD_URINE_TEST%>"><%=InpatientConfig.PREGNANCY_DETECTION_ARR[Integer.parseInt(InpatientConfig.PREGNANCY_DETECTION_METHOD_URINE_TEST)]%></html:option>
						</html:select>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="detectionDate"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<bean:define  name="ANCDetailFB" property="detectionDate"  id="detectiondate" type="java.lang.String"/>
   						<his:date name="detectionDate" dateFormate="%d-%b-%Y" value='<%=detectiondate%>' onchange="onchangeDetectionDate(this)"/>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						
							<b>
							<font color="#FF0000">*</font>
								<bean:message key="lmp"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<bean:define  name="ANCDetailFB" property="lmpDate"  id="lmpdate" type="java.lang.String"/>
   						<his:date name="lmpDate" dateFormate="%d-%b-%Y" value='<%=lmpdate%>' onchange="onchangelmpdate(this)"/>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="edd"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDetailFB" property="expectedDeliveryDate" tabindex="1" readonly="true" styleClass="ancTextboxNormal" ></html:text>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="gestationstrtdt"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<bean:define  name="ANCDetailFB" property="gestationStartDate"  id="gestationStartDt" type="java.lang.String"/>
   						<his:date name="gestationStartDate" dateFormate="%d-%b-%Y" value='<%=gestationStartDt%>' onchange="onchangeGestStartdate(this)" />						
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="gestationweek"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">						
						<html:text name="ANCDetailFB" property="gestationPeriod" tabindex="1" maxlength="3" readonly="true" styleClass="ancTextboxSmall" onkeypress="return validateNumeric(event)" />   												
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="usedd"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<bean:define  name="ANCDetailFB" property="ultraSoundEDD"  id="ultraSoundEDDDt" type="java.lang.String"/>
   						<his:date name="ultraSoundEDD" dateFormate="%d-%b-%Y" value='<%=ultraSoundEDDDt%>' onchange="onchangeUSEDD(this)" />						
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="isantidgiven"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont" valign="middle">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<html:radio name="ANCDetailFB" property="isAntiDGiven" tabindex="1" value="<%=OpdConfig.YES%>" />&nbsp;<bean:message key="yes"/>&nbsp;
							<html:radio name="ANCDetailFB" property="isAntiDGiven" tabindex="1" value="<%=OpdConfig.NO%>" />&nbsp;<bean:message key="no"/>
						</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="contraceptiveused"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont" valign="middle">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<html:radio name="ANCDetailFB" property="isContraceptiveUsed" tabindex="1" value="<%=OpdConfig.YES%>" onchange="onchangeContraceptiveUseFlag()" />&nbsp;<bean:message key="yes"/>&nbsp;
							<html:radio name="ANCDetailFB" property="isContraceptiveUsed" tabindex="1" value="<%=OpdConfig.NO%>" onchange="onchangeContraceptiveUseFlag()" />&nbsp;<bean:message key="no"/>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead"></td>
				<td width="25%" class="tdfont"></td>
			</tr>
		</table>
		
		<!-- Contraceptive Use Detail -->
		<% String isContraceptiveUseDisplay="display: none;";%>
		<div id="divContraceptiveUseDtl" style="<%=isContraceptiveUseDisplay%>">
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>			
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="contraceptiveRemarks"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="75%" class="tdfont">
					<div align="left">
						<html:textarea name="ANCDetailFB" property="contraceptiveRemarks" tabindex="1" styleClass="ancTextareaLong" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumOnly(this,event))" ></html:textarea>
					</div>
				</td>
			</tr>
		</table>
		</div>
		
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="queckeningWeek"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDetailFB" property="queckeningWeek" tabindex="1" maxlength="2" styleClass="ancTextboxSmall" onkeypress="return validatePositiveIntegerOnly(this,event)" onblur="onblurQueckenkening(this)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead"></td>
				<td width="25%" class="tdfont"></td>
			</tr>
		</table>
		
		<!-- Queckening Remarks Detail -->
		<% String queckeningRemarksDisplay="display: none;";%>
		<div id="divQueckeningRemarksDtl" style="<%=queckeningRemarksDisplay%>">
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>			
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="queckeningRemarks"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="75%"  class="tdfont">
					<div align="left">
						<html:textarea name="ANCDetailFB" property="queckeningRemarks" tabindex="1" styleClass="ancTextareaLong" onkeypress="return (validateTextArea(event,this,'50') && validateAlphaNumOnly(this,event))" ></html:textarea>
					</div>
				</td>
			</tr>
		</table>
		</div>
		
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="highriskpreg"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:checkbox name="ANCDetailFB" property="isHighriskPregnancy" tabindex="1" value='1' onchange="checkHighRiskPreg(this)"/>
					</div>
				</td>
				<td width="25%" class="tdfonthead"></td>
				<td width="25%"  class="tdfont"></td>
			</tr>
		</table>
		
		<!-- High Risk Complications Remarks Detail -->
		<% String highRiskCompsDisplay="display: none;";%>
		<div id="divComplications" style="<%=highRiskCompsDisplay%>">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="complications"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="75%" class="tdfont">
					<div align="left">
						<html:textarea name="ANCDetailFB" property="complications" tabindex="1" styleClass="ancTextareaLong" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumOnly(this,event))"></html:textarea>
					</div>
				</td>
			</tr>
		</table>
		</div>
		
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="visRemarks"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="50%"  class="tdfont">
					<div align="left">
						<html:textarea name="ANCDetailFB" property="remarks" tabindex="1" styleClass="ancTextareaLong" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumOnly(this,event))"></html:textarea>
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<% String macroVisitNotes ="openANCMacros('"+OpdConfig.MACRO_PROCESS_ID_ANC_VISIT_REMARKS+"',event)"; %>
						<html:button value="Add Visit Remarks" property="mybutton" onclick="<%=macroVisitNotes%>" style='cursor:pointer'  tabindex='1'/>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
</div>

	<his:SubTitleTag key="otherdetail">
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr><td><div align="right">
				<img id="imgOtherDtl" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-down.png"; onclick="onclickImage(this)"/>
			</div></td></tr>
		</table>	
	</his:SubTitleTag>
	
<div id="divOtherDtl" style="display: none;">
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead" nowrap="nowrap" valign="middle">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="marriage" /> <bean:message key="age" /><logic:empty name="ANCDetailFB" property="patDateOfMarriage"><html:radio name="ANCDetailFB" property="isActualDateOfMarriage" tabindex="1" value="<%=OpdConfig.NO%>" onclick="ageOfMarriageSelection()" />&nbsp;
								<bean:message key="dom"/><html:radio name="ANCDetailFB" property="isActualDateOfMarriage" tabindex="1" value="<%=OpdConfig.YES%>" onclick="ageOfMarriageSelection()" /></logic:empty>
							</b>
						</font>		
					</div>
				</td>
				
				<td width="25%"  class="tdfont">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
					<html:hidden name="ANCDetailFB" property="patAge" />
					<logic:empty name="ANCDetailFB" property="patDateOfMarriage">
						<div id="divAgeOfMarriage" style='display:block;' align="left">
							<html:text name="ANCDetailFB" property="patAgeOfMarriage" tabindex="1" maxlength="2" styleClass="ancTextboxSmall" onkeypress="return validateNumeric(event)" /> 
							<html:hidden name="ANCDetailFB" property="patUnitAgeOfMarriage" value="Y" /> <bean:message key="years"/>
						</div>
						<div id="divDateOfMarriage" style='display:none;' align="left">
							<bean:define name="ANCDetailFB" property="patDateOfMarriage" id="dateOfMarriage" type="java.lang.String" />
							<his:date name="patDateOfMarriage" dateFormate="%d-%b-%Y" value='<%=dateOfMarriage%>' />
						</div>
					</logic:empty>
					<logic:notEmpty name="ANCDetailFB" property="patDateOfMarriage">
						<div align="left">
							&nbsp;<bean:write name="ANCDetailFB" property="patAgeOfMarriage"/> <bean:message key="years"/>
							<html:hidden name="ANCDetailFB" property="patAgeOfMarriage"/>
						</div>
					</logic:notEmpty>
					</b></font>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="pathusbandname"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patHusbandName">
							<html:text name="ANCDetailFB" property="patHusbandName" tabindex="1" maxlength="60" styleClass="ancTextboxNormal" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patHusbandName">
							&nbsp;<bean:write name="ANCDetailFB" property="patHusbandName"/>
							<html:hidden name="ANCDetailFB" property="patHusbandName"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="patbldgrp"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patBloodGroup">
							<html:select name="ANCDetailFB" property="patBloodGroup" tabindex="1" styleClass="ancComboNormal">
							<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
							</html:select>							
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patBloodGroup">
							<% String strPatBloodGroup = ""; %>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST %>">
								<bean:define id="valPatBloodGroup" name="ANCDetailFB" property="patBloodGroup"></bean:define>
								<logic:iterate id="idPatBloodGroup" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST %>" type="hisglobal.utility.Entry">
									<% if(idPatBloodGroup.getValue().equals(valPatBloodGroup)) strPatBloodGroup=idPatBloodGroup.getLabel();%>
								</logic:iterate>
							</logic:present>
							&nbsp;<%=strPatBloodGroup%>
							<html:hidden name="ANCDetailFB" property="patBloodGroup"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="husbldgrp"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patHusbandBloodGroup">
							<html:select name="ANCDetailFB" property="patHusbandBloodGroup" tabindex="1" styleClass="ancComboNormal">
							<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
							</html:select>							
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patHusbandBloodGroup">
							<% String strPatHusbandBloodGroup = ""; %>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST %>">
								<bean:define id="valPatHusbandBloodGroup" name="ANCDetailFB" property="patHusbandBloodGroup"></bean:define>
								<logic:iterate id="idPatHusbandBloodGroup" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST %>" type="hisglobal.utility.Entry">
									<% if(idPatHusbandBloodGroup.getValue().equals(valPatHusbandBloodGroup)) strPatHusbandBloodGroup=idPatHusbandBloodGroup.getLabel();%>
								</logic:iterate>
							</logic:present>
							&nbsp;<%=strPatHusbandBloodGroup%>
							<html:hidden name="ANCDetailFB" property="patHusbandBloodGroup"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="fatherName"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patGuardianName">
							<html:text name="ANCDetailFB" property="patGuardianName" tabindex="1" maxlength="60" styleClass="ancTextboxNormal" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patGuardianName">
							&nbsp;<bean:write name="ANCDetailFB" property="patGuardianName"/>
							<html:hidden name="ANCDetailFB" property="patGuardianName"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="motherName"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patMotherName">
							<html:text name="ANCDetailFB" property="patMotherName" tabindex="1" maxlength="60" styleClass="ancTextboxNormal" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patMotherName">
							&nbsp;<bean:write name="ANCDetailFB" property="patMotherName"/>
							<html:hidden name="ANCDetailFB" property="patMotherName"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="familyType"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patFamilyType">
							<html:select name="ANCDetailFB" property="patFamilyType" tabindex="1" styleClass="ancComboNormal">
								<html:option value="-1">Select Value</html:option>
								<html:option value="<%=InpatientConfig.FAMILY_TYPE_JOINT%>"><%=InpatientConfig.FAMILY_TYPE_ARR[Integer.parseInt(InpatientConfig.FAMILY_TYPE_JOINT)] %></html:option>
								<html:option value="<%=InpatientConfig.FAMILY_TYPE_NUCLEAR%>"><%=InpatientConfig.FAMILY_TYPE_ARR[Integer.parseInt(InpatientConfig.FAMILY_TYPE_NUCLEAR)] %></html:option>
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patFamilyType">
							<% String strPatFamilyType = ""; %>
							<logic:equal name="ANCDetailFB" property="patFamilyType" value="<%=InpatientConfig.FAMILY_TYPE_NUCLEAR%>">
								<%strPatFamilyType = InpatientConfig.FAMILY_TYPE_ARR[Integer.parseInt(InpatientConfig.FAMILY_TYPE_NUCLEAR)]; %>
							</logic:equal>
							<logic:equal name="ANCDetailFB" property="patFamilyType" value="<%=InpatientConfig.FAMILY_TYPE_JOINT%>">
								<%strPatFamilyType = InpatientConfig.FAMILY_TYPE_ARR[Integer.parseInt(InpatientConfig.FAMILY_TYPE_JOINT)]; %>
							</logic:equal>
							&nbsp;<%=strPatFamilyType%>
							<html:hidden name="ANCDetailFB" property="patFamilyType"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="caste"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patCaste">
							<html:select name="ANCDetailFB" property="patCaste" tabindex="1" styleClass="ancComboNormal">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_CASTE_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_CASTE_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patCaste">
							<% String strPatCaste = ""; %>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_CASTE_LIST %>">
								<bean:define id="valPatCaste" name="ANCDetailFB" property="patCaste"></bean:define>
								<logic:iterate id="idPatCaste" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_CASTE_LIST %>" type="hisglobal.utility.Entry">
									<% if(idPatCaste.getValue().equals(valPatCaste)) strPatCaste=idPatCaste.getLabel();%>
								</logic:iterate>
							</logic:present>
							&nbsp;<%=strPatCaste%>
							<html:hidden name="ANCDetailFB" property="patCaste"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="patOccupation"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patOccupation">
							<html:select name="ANCDetailFB" property="patOccupation" tabindex="1" styleClass="ancComboNormal">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patOccupation">
							<% String strPatOccupation = ""; %>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>">
								<bean:define id="valPatOccupation" name="ANCDetailFB" property="patOccupation"></bean:define>
								<logic:iterate id="idPatOccupation" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>" type="hisglobal.utility.Entry">
									<% if(idPatOccupation.getValue().equals(valPatOccupation)) strPatOccupation=idPatOccupation.getLabel();%>
								</logic:iterate>
							</logic:present>
							&nbsp;<%=strPatOccupation%>
							<html:hidden name="ANCDetailFB" property="patOccupation"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="patHusOccup"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patHusbandOccupation">
							<html:select name="ANCDetailFB" property="patHusbandOccupation" tabindex="1" styleClass="ancComboNormal">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patHusbandOccupation">
							<% String strPatHusbandOccupation = ""; %>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>">
								<bean:define id="valPatHusbandOccupation" name="ANCDetailFB" property="patHusbandOccupation"></bean:define>
								<logic:iterate id="idPatHusbandOccupation" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>" type="hisglobal.utility.Entry">
									<% if(idPatHusbandOccupation.getValue().equals(valPatHusbandOccupation)) strPatHusbandOccupation=idPatHusbandOccupation.getLabel();%>
								</logic:iterate>
							</logic:present>
							&nbsp;<%=strPatHusbandOccupation%>
							<html:hidden name="ANCDetailFB" property="patHusbandOccupation"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="patFatherOccupation"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patFatherOccupation">
							<html:select name="ANCDetailFB" property="patFatherOccupation" tabindex="1" styleClass="ancComboNormal">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patFatherOccupation">
							<% String strPatFatherOccupation = ""; %>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>">
								<bean:define id="valPatFatherOccupation" name="ANCDetailFB" property="patFatherOccupation"></bean:define>
								<logic:iterate id="idPatFatherOccupation" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>" type="hisglobal.utility.Entry">									
									<% if(idPatFatherOccupation.getValue().equals(valPatFatherOccupation)) strPatFatherOccupation=idPatFatherOccupation.getLabel();%>
								</logic:iterate>
							</logic:present>
							&nbsp;<%=strPatFatherOccupation%>
							<html:hidden name="ANCDetailFB" property="patFatherOccupation"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="momoccupation"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patMotherOccupation">
							<html:select name="ANCDetailFB" property="patMotherOccupation" tabindex="1" styleClass="ancComboNormal">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patMotherOccupation">
							<% String strPatMotherOccupation = ""; %>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>">
								<bean:define id="valPatMotherOccupation" name="ANCDetailFB" property="patMotherOccupation"></bean:define>
								<logic:iterate id="idPatMotherOccupation" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST %>" type="hisglobal.utility.Entry">
									<% if(idPatMotherOccupation.getValue().equals(valPatMotherOccupation)) strPatMotherOccupation=idPatMotherOccupation.getLabel();%>
								</logic:iterate>
							</logic:present>
							&nbsp;<%=strPatMotherOccupation%>
							<html:hidden name="ANCDetailFB" property="patMotherOccupation"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="patedustatus"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patEducationStatus">
							<html:select name="ANCDetailFB" property="patEducationStatus" tabindex="1" styleClass="ancComboNormal">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patEducationStatus">
							<% String strPatEducationStatus = ""; %>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>">
								<bean:define id="valPatEducationStatus" name="ANCDetailFB" property="patEducationStatus"></bean:define>
								<logic:iterate id="idPatEducationStatus" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>" type="hisglobal.utility.Entry">
									<% if(idPatEducationStatus.getValue().equals(valPatEducationStatus)) strPatEducationStatus=idPatEducationStatus.getLabel();%>
								</logic:iterate>
							</logic:present>
							&nbsp;<%=strPatEducationStatus%>
							<html:hidden name="ANCDetailFB" property="patEducationStatus"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="husedustatus"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patSpouseEducationStatus">
							<html:select name="ANCDetailFB" property="patSpouseEducationStatus" tabindex="1" styleClass="ancComboNormal">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patSpouseEducationStatus">
							<% String strPatSpouseEducationStatus = ""; %>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>">
								<bean:define id="valPatSpouseEducationStatus" name="ANCDetailFB" property="patSpouseEducationStatus"></bean:define>
								<logic:iterate id="idPatSpouseEducationStatus" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>" type="hisglobal.utility.Entry">
									<% if(idPatSpouseEducationStatus.getValue().equals(valPatSpouseEducationStatus)) strPatSpouseEducationStatus=idPatSpouseEducationStatus.getLabel();%>
								</logic:iterate>
							</logic:present>
							&nbsp;<%=strPatSpouseEducationStatus%>
							<html:hidden name="ANCDetailFB" property="patSpouseEducationStatus"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="fatedustatus"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patFatherEducationStatus">
							<html:select name="ANCDetailFB" property="patFatherEducationStatus" tabindex="1" styleClass="ancComboNormal">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patFatherEducationStatus">
							<% String strPatFatherEducationStatus = ""; %>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>">
								<bean:define id="valPatFatherEducationStatus" name="ANCDetailFB" property="patFatherEducationStatus"></bean:define>
								<logic:iterate id="idPatFatherEducationStatus" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>" type="hisglobal.utility.Entry">
									<% if(idPatFatherEducationStatus.getValue().equals(valPatFatherEducationStatus)) strPatFatherEducationStatus=idPatFatherEducationStatus.getLabel();%>
								</logic:iterate>
							</logic:present>
							&nbsp;<%=strPatFatherEducationStatus%>
							<html:hidden name="ANCDetailFB" property="patFatherEducationStatus"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="momedustatus"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
						<logic:empty name="ANCDetailFB" property="patMotherEducationStatus">
							<html:select name="ANCDetailFB" property="patMotherEducationStatus" tabindex="1" styleClass="ancComboNormal">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>">
									<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>" property="value" labelProperty="label" />
								</logic:present>								
							</html:select>
						</logic:empty>
						<logic:notEmpty name="ANCDetailFB" property="patMotherEducationStatus">
							<% String strPatMotherEducationStatus = ""; %>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>">
								<bean:define id="valPatMotherEducationStatus" name="ANCDetailFB" property="patMotherEducationStatus"></bean:define>
								<logic:iterate id="idPatMotherEducationStatus" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST %>" type="hisglobal.utility.Entry">
									<% if(idPatMotherEducationStatus.getValue().equals(valPatMotherEducationStatus)) strPatMotherEducationStatus=idPatMotherEducationStatus.getLabel();%>
								</logic:iterate>
							</logic:present>
							&nbsp;<%=strPatMotherEducationStatus%>
							<html:hidden name="ANCDetailFB" property="patMotherEducationStatus"/>
						</logic:notEmpty>
						</b></font>
					</div>
				</td>
			</tr>			
			<tr>				
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font><bean:message key="ageMenarche" />
								<!--  <bean:message key="age" /><html:radio name="ANCDetailFB" property="isActualAgeOfMenarche" tabindex="1" value="<%=OpdConfig.NO%>" onclick="ageOfMenarcheSelection()" />&nbsp;
								<bean:message key="date"/><html:radio name="ANCDetailFB" property="isActualAgeOfMenarche" tabindex="1" value="<%=OpdConfig.YES%>" onclick="ageOfMenarcheSelection()" /> -->
							</b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
					<logic:empty name="ANCDetailFB" property="patDateOfMarriage">
						<!-- <div id="divAgeOfMenarche" style='display:none;' align="left">  -->
							<html:text name="ANCDetailFB" property="patAgeOfMenarche" tabindex="1" maxlength="2" styleClass="ancTextboxSmall" onkeypress="return validateNumeric(event)" /> 
							<html:hidden name="ANCDetailFB" property="patUnitAgeOfMenarche" value="Y" /> <bean:message key="years"/>
						<!-- </div>
						<div id="divDateOfMenarche" style='display:block;' align="left">
							<bean:define name="ANCDetailFB" property="patDateOfMenarche" id="dateOfMenarche" type="java.lang.String" />
							<his:date name="patDateOfMenarche" dateFormate="%d-%b-%Y" value='dateOfMenarche%>' />
						</div> -->
					</logic:empty>
					<logic:notEmpty name="ANCDetailFB" property="patDateOfMarriage">
						<div align="left">
							<!-- <html:text name="ANCDetailFB" property="patAgeOfMenarche" disabled="true" tabindex="1" maxlength="2" styleClass="ancTextboxSmall" onkeypress="return validateNumeric(event)" />  
							<bean:message key="years"/> -->
							&nbsp;<bean:write  name="ANCDetailFB" property="patAgeOfMenarche" /> <bean:message key="years"/>
							<html:hidden name="ANCDetailFB" property="patAgeOfMenarche"/>
						</div>
					</logic:notEmpty>
					</b></font>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font><bean:message key="menstrualcycle"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<html:select name="ANCDetailFB" property="menstrualCycleId" tabindex="1" styleClass="ancComboNormal">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_MENSTRUAL_CYCLE_LIST %>">
								<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_MENSTRUAL_CYCLE_LIST %>" property="value" labelProperty="label" />
							</logic:present>								
						</html:select>
					</div>
				</td>
			</tr>
			<tr>				
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="menstrualcycledays"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<html:text name="ANCDetailFB" property="menstrualCycleDays" tabindex="1" maxlength="2" styleClass="ancTextboxSmall" onkeypress="return validateNumericOnly(this,event)" ></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead"></td>
				<td width="25%" class="tdfont"></td>
			</tr>
		</table>
	</his:ContentTag>	
</div>

	<his:SubTitleTag key="hisprevpregn">
		<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
			<tr><td width="1%" align="right" valign="middle">				
				<img id="imgANHisDtl" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-down.png"; onclick="onclickImage(this)"/>
			</td></tr>
		</table>
	</his:SubTitleTag>
<div id="divANHisDtl" style="display: none;">
<his:ContentTag>
	<table style="display: none;">
		<tr id="trMainHead">
			<td width="2%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="slNo"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<font color="#FF0000">*</font><bean:message key="delstatus"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<font color="#FF0000">*</font><bean:message key="dateofdel"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<font color="#FF0000">*</font><bean:message key="durofpreg"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="rempreg"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="plcofdel"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="duroflab"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="deliverytype"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="isantidgiven"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="remlab"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<font color="#FF0000">*</font><bean:message key="noofbirth"/>
						</b>
					</font>		
				</div>
			</td>
		</tr>
		<tr id="trSubHead">
			<td width="2%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="slNo"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<font color="#FF0000">*</font><bean:message key="birnature"/>
						</b>
					</font>		
				</div>
			</td>
 			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="genofbirth"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="wetofbirth"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="babybldgrp"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="presentchildhealth"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="deathAge"/>
						</b>
					</font>		
				</div>
			</td>
			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="deathcause"/>
						</b>
					</font>		
				</div>
			</td>
		</tr>
	</table>

	<div id="divANCHistoryPan" style="display: none;">
		<html:select name="ANCDetailFB" property="deliveryPlaceList">		
			<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_DELIVERY_PLACE_LIST %>">
				<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_DELIVERY_PLACE_LIST %>" property="value" labelProperty="label" />
			</logic:present>								
		</html:select>
					
		<html:select name="ANCDetailFB" property="genderList">
			<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_GENDER_LIST %>">
				<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_GENDER_LIST %>" property="value" labelProperty="label" />
			</logic:present>								
		</html:select>

		<html:select name="ANCDetailFB" property="deliveryTypeList">
			<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_DELIVERY_TYPE_LIST %>">
				<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_DELIVERY_TYPE_LIST %>" property="value" labelProperty="label" />
			</logic:present>								
		</html:select>

		<html:select name="ANCDetailFB" property="bloodGroupList">
			<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST %>">
				<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST %>" property="value" labelProperty="label" />
			</logic:present>								
		</html:select>
	</div>

	<table id="tblANCHistory" width="100%" border="0" cellspacing="1" cellpadding="0">		
	</table>

	<logic:empty name="<%=InpatientConfig.ANCDETAIL_PATIENT_ANC_HISTORY_DETAIL_LIST%>" >
		<script type="text/javascript"> ANCHistoryPan.setup('tblANCHistory',<bean:write name="ANCDetailFB" property="initalGravida"/>,<bean:write name="ANCDetailFB" property="parityNo"/>,<bean:write name="ANCDetailFB" property="abortusNo"/>,'<bean:write name="ANCDetailFB" property="entryDate"/>','<%=Config.ANC_DETAIL_MAXIMUM_NO_OF_BIRTH%>');</script>
	</logic:empty>
	<logic:notEmpty name="<%=InpatientConfig.ANCDETAIL_PATIENT_ANC_HISTORY_DETAIL_LIST%>" >
		<script type="text/javascript"> ANCHistoryPan.setup('tblANCHistory',<bean:write name="ANCDetailFB" property="initalGravida"/>,<bean:write name="ANCDetailFB" property="parityNo"/>,<bean:write name="ANCDetailFB" property="abortusNo"/>,'<bean:write name="ANCDetailFB" property="entryDate"/>','<%=Config.ANC_DETAIL_MAXIMUM_NO_OF_BIRTH%>','<bean:write name="ANCDetailFB" property="histPregData"/>');</script>
		<!-- <script type="text/javascript"> ANCHistoryPan.showAddedHistory('tblANCOldHistory',<bean:write name="ANCDetailFB" property="gravidaNo"/>,'<bean:write name="ANCDetailFB" property="histPregData"/>');</script> -->
	</logic:notEmpty>
</his:ContentTag>
</div>

</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer"  tabindex='2' onclick =  "submitFormOnValidate(validateAdd(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateAdd(),'SAVE');")>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW');">
	</his:statusTransactionInProcess>
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusUnsuccessfull>
</his:ButtonToolBarTag>
	

<html:hidden name="ANCDetailFB" property="hmode"/>
<html:hidden name="ANCDetailFB" property="entryDate"/>
<html:hidden name="ANCDetailFB" property="patCrNo"/>
<html:hidden name="ANCDetailFB" property="episodeCode"/>
<html:hidden name="ANCDetailFB" property="episodeVisitNo"/>
<html:hidden name="ANCDetailFB" property="admissionNo"/>
<html:hidden name="ANCDetailFB" property="departmentUnitCode"/>
<html:hidden name="ANCDetailFB" property="wardCode"/>
<html:hidden name="ANCDetailFB" property="userSeatId"/>

<html:hidden name="ANCDetailFB" property="initalGravida"/>
<html:hidden name="ANCDetailFB" property="histParityNo"/>					
<html:hidden name="ANCDetailFB" property="histAbortusNo"/>

