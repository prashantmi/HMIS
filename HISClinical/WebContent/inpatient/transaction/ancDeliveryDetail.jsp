<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>	
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>

<his:css src="/inpatient/css/anc_style.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/time_validator.js"/>
<his:javascript src="/inpatient/js/anc_validations.js"/>
<his:javascript src="/inpatient/js/anc_delivery_detail.js"/>
<his:javascript src="/hisglobal/utility/dynamicdesk/js/deskNew.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
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
	
	var prevDelCount = 0;
	function onchangeDelCount(objDelCount)
	{
		// alert("obj del count"+objDelCount.value);
		var maxDeliveryCount=<%=Config.ANC_DETAIL_MAXIMUM_NO_OF_BIRTH%>;
		if(objDelCount.value != "")
		{
			var count = parseInt(objDelCount.value);
			if(count>0 && count<=maxDeliveryCount)
			{		
				setOutComeTable(count);			
				prevDelCount = count;
				return;
			}
			else if(count>6)
			{
					alert("Maximum Delivery Count"+" "+maxDeliveryCount);
					setOutComeTable(maxDeliveryCount);
					document.getElementsByName("deliveryCount")[0].value=maxDeliveryCount;
					prevDelCount=maxDeliveryCount;
					return;
			}
		}
		setOutComeTable(0);
		prevDelCount = 0;
	}

</script>

<his:TitleTag key="ancdeliverydtl">
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
	
<his:statusTransactionInProcess>

	<% String entryDate=WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");  %>

	<his:SubTitleTag key="antendtl">
		<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
			<tr><td width="1%" align="right" valign="middle">				
				<img id="imgANCDtl" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-up.png"; onclick="onclickImage(this)"/>
			</td></tr>
		</table>
	</his:SubTitleTag> 

	<div id="divANCDtl" style="display: block;">
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="gravidano"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:hidden name="ANCDeliveryDetailFB" property="gravidaNo"/>
						<html:hidden name="ANCDeliveryDetailFB" property="parityNo"/>
						<html:hidden name="ANCDeliveryDetailFB" property="abortusNo"/>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>							
						&nbsp;<bean:write name="ANCDeliveryDetailFB" property="gravidaNo"/>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="gestationweek"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>							
						&nbsp;<bean:write name="ANCDeliveryDetailFB" property="pregnancyDuration"/>
						</b></font>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>	

	<his:SubTitleTag key="labdtl">
		<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
			<tr><td width="1%" align="right" valign="middle">				
				<img id="imgLabDtl" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-down.png"; onclick="onclickImage(this)"/>
			</td></tr>
		</table>
	</his:SubTitleTag>
	
	<div id="divLabDtl" style="display: none;">
	<!-- Rupture Detail --> 	
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="rupturedate"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:hidden name="ANCDeliveryDetailFB" property="ruptureDateTime"/>
						<his:date name="ruptureDate" dateFormate="%d-%b-%Y" onchange="onchangeRupDateTime()" />
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="rupturetime"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDeliveryDetailFB" property="ruptureTime" tabindex="1" maxlength="5" styleClass="ancTextboxSmallMedium" onkeypress="return (validatePositiveIntegerOnly(this,event) || validateWithCharacters(this,event,':'));" onchange="onchangeRupDateTime()"></html:text>   						
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="rupturetype"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:select name="ANCDeliveryDetailFB" property="ruptureType" tabindex="1" styleClass="ancComboNormal">
							<html:option value="-1">Select Value</html:option>
							<html:option value="<%=InpatientConfig.RUPTURE_TYPE_SPONTANEOUS%>"><%=InpatientConfig.RUPTURE_TYPE_ARR[Integer.parseInt(InpatientConfig.RUPTURE_TYPE_SPONTANEOUS)]%></html:option>
							<html:option value="<%=InpatientConfig.RUPTURE_TYPE_ARTIFICIAL%>"><%=InpatientConfig.RUPTURE_TYPE_ARR[Integer.parseInt(InpatientConfig.RUPTURE_TYPE_ARTIFICIAL)]%></html:option>
							<html:option value="<%=InpatientConfig.RUPTURE_TYPE_PROM%>"><%=InpatientConfig.RUPTURE_TYPE_ARR[Integer.parseInt(InpatientConfig.RUPTURE_TYPE_PROM)]%></html:option>
						</html:select>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="rupturedur"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDeliveryDetailFB" property="ruptureDuration" tabindex="1" maxlength="2" styleClass="ancTextboxSmallMedium" onkeypress="return validatePositiveIntegerOnly(this,event);"></html:text>   						
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="isinduced"/>
						</b></font>		
					</div>
				</td>
				<td width="25%" class="tdfont" valign="middle">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<html:radio name="ANCDeliveryDetailFB" property="isInduced" tabindex="1" value="<%=OpdConfig.YES%>" onchange="onchangeInducedFlag()"/>&nbsp;<bean:message key="yes"/>&nbsp;
							<html:radio name="ANCDeliveryDetailFB" property="isInduced" tabindex="1" value="<%=OpdConfig.NO%>" onchange="onchangeInducedFlag()"/>&nbsp;<bean:message key="no"/>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
				</td>
				<td width="25%"  class="tdfont">
				</td>
			</tr>
		</table>
	
		<!-- Induction Detail -->
		<div id="divInductionDtl" style="display: none;">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="indicinduction"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDeliveryDetailFB" property="indicationOfInduction" tabindex="1" maxlength="100" styleClass="ancTextboxNormal" onkeypress="return validateAlphaOnly(this,event)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="inductionmethod"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:select name="ANCDeliveryDetailFB" property="inductionMethodId" tabindex="1" styleClass="ancComboNormal">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_INDUCTION_METHOD_LIST %>">
								<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_INDUCTION_METHOD_LIST %>" property="value" labelProperty="label" />
							</logic:present>								
   						</html:select>
					</div>
				</td>
			</tr>
		</table>
		</div>

	<!-- Labour Detail -->
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="labour"/> <bean:message key="stage1dur"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDeliveryDetailFB" property="labourStage1Duration" tabindex="1" maxlength="2" styleClass="ancTextboxSmallMedium" onkeypress="return validatePositiveIntegerOnly(this,event)" onchange="onchangeLaborStageDur()"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="stage1Rem"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:textarea name="ANCDeliveryDetailFB" property="labourStage1Remarks" tabindex="1" styleClass="ancTextareaNormal" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumOnly(this,event))" ></html:textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="labour"/> <bean:message key="stage2dur"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDeliveryDetailFB" property="labourStage2Duration" tabindex="1" maxlength="2" styleClass="ancTextboxSmallMedium" onkeypress="return validatePositiveIntegerOnly(this,event)" onchange="onchangeLaborStageDur()"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="stage2Rem"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:textarea name="ANCDeliveryDetailFB" property="labourStage2Remarks" tabindex="1" styleClass="ancTextareaNormal" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumOnly(this,event))" ></html:textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="labour"/> <bean:message key="stage3dur"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDeliveryDetailFB" property="labourStage3Duration" tabindex="1" maxlength="2" styleClass="ancTextboxSmallMedium" onkeypress="return validatePositiveIntegerOnly(this,event)" onchange="onchangeLaborStageDur()"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="stage3Rem"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:textarea name="ANCDeliveryDetailFB" property="labourStage3Remarks" tabindex="1" styleClass="ancTextareaNormal" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumOnly(this,event))" ></html:textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="duroflab"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDeliveryDetailFB" property="labourDuration" tabindex="1" readonly="true" maxlength="2" styleClass="ancTextboxSmallMedium" onkeypress="return validatePositiveIntegerOnly(this,event)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="remlab"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:textarea name="ANCDeliveryDetailFB" property="labourRemarks" tabindex="1" styleClass="ancTextareaNormal" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumOnly(this,event))" ></html:textarea>
					</div>
				</td>
			</tr>
	<!-- Foetal Distress Detail -->
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="foetaldistress"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont" valign="middle">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<html:radio name="ANCDeliveryDetailFB" property="isFoetalDistress" tabindex="1" value="<%=OpdConfig.YES%>" />&nbsp;<bean:message key="yes"/>&nbsp;
							<html:radio name="ANCDeliveryDetailFB" property="isFoetalDistress" tabindex="1" value="<%=OpdConfig.NO%>" />&nbsp;<bean:message key="no"/>
						</b></font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="eas"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont" valign="middle">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<html:radio name="ANCDeliveryDetailFB" property="isEAS" tabindex="1" value="<%=OpdConfig.YES%>" />&nbsp;<bean:message key="yes"/>&nbsp;
							<html:radio name="ANCDeliveryDetailFB" property="isEAS" tabindex="1" value="<%=OpdConfig.NO%>" />&nbsp;<bean:message key="no"/>
						</b></font>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	</div>

	<his:SubTitleTag key="ancdeliverydtl">
		<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
			<tr><td width="1%" align="right" valign="middle">				
				<img id="imgANCDelDtl" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-up.png"; onclick="onclickImage(this)"/>
			</td></tr>
		</table>
	</his:SubTitleTag>

	<div id="divLaborRoomAreaList" style="display: none;">
		<html:select name="ANCDeliveryDetailFB" property="listLaborRoomArea" tabindex="1" styleClass="ancComboNormal">
			<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_LABOR_ROOM_AREA_LIST %>">
				<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_LABOR_ROOM_AREA_LIST %>" property="value" labelProperty="label" />
			</logic:present>								
		</html:select>
	</div>
	<div id="divANCDelDtl" style="display: block;">
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<font color="#FF0000">*</font>
							<bean:message key="dateofdelonly"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:hidden name="ANCDeliveryDetailFB" property="lmpDate"/>
						<html:hidden name="ANCDeliveryDetailFB" property="gestationStartDate"/>
						<his:date name="deliveryDate" dateFormate="%d-%b-%Y" value='<%=entryDate%>' onchange="onchangeDelDate(this)"/>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<font color="#FF0000">*</font>
							<bean:message key="durofpreg"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDeliveryDetailFB" property="pregnancyDuration" tabindex="1" readonly="true" maxlength="2" styleClass="ancTextboxSmallMedium" onkeypress="return validatePositiveIntegerOnly(this,event)" />
						<html:hidden name="ANCDeliveryDetailFB" property="deliveryStatus" value="<%=InpatientConfig.DELIVERY_STATUS_DELIVERY%>"/>						
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<font color="#FF0000">*</font>
							<bean:message key="labRoom"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:select name="ANCDeliveryDetailFB" property="labourRoomId" tabindex="1" styleClass="ancComboNormal" onchange="fillLaborRoomArea(this)">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_LABOR_ROOM_LIST %>">
								<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_LABOR_ROOM_LIST %>" property="value" labelProperty="label" />
							</logic:present>								
						</html:select>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<font color="#FF0000">*</font>
							<bean:message key="labRoomarea"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:select name="ANCDeliveryDetailFB" property="labourRoomAreaId" tabindex="1" styleClass="ancComboNormal">
							<html:option value="-1">Select Value</html:option>
						</html:select>
					</div>
				</td>
			</tr>
	<!-- Delivery Detail -->
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<font color="#FF0000">*</font>
							<bean:message key="deliverytype"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:select name="ANCDeliveryDetailFB" property="deliveryTypeId" tabindex="1" styleClass="ancComboNormal">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_DELIVERY_TYPE_LIST %>">
								<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_DELIVERY_TYPE_LIST %>" property="value" labelProperty="label" />
							</logic:present>								
   						</html:select>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="indicdeliverytype"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:textarea name="ANCDeliveryDetailFB" property="indicationOfDeliveyType" tabindex="1" styleClass="ancTextareaNormal" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumOnly(this,event))" ></html:textarea>
					</div>
				</td>
			</tr>
	<!-- Final Remarks -->
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
				<td width="25%" class="tdfont">
					<div align="left">
   						<html:select name="ANCDeliveryDetailFB" property="complicationId" tabindex="1" styleClass="ancComboNormal">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_COMPLICATIONS_LIST %>">
								<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_COMPLICATIONS_LIST %>" property="value" labelProperty="label" />
							</logic:present>								
   						</html:select>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="complirem"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<html:textarea name="ANCDeliveryDetailFB" property="complicationRemarks" tabindex="1" styleClass="ancTextareaNormal" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumOnly(this,event))"></html:textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="rempreg"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="75%" class="tdfont" colspan="3">
					<div align="left">
						<html:textarea name="ANCDeliveryDetailFB" property="pregnancyRemarks" tabindex="1" styleClass="ancTextareaLong" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumOnly(this,event))"></html:textarea>
					</div>
				</td>
			</tr>
	<!-- Mother Status  -->
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="motherstatus"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="75%"  class="tdfont" colspan="3">
					<div align="left">
						<% String motherStatusOnChangeFun = "onchangeMotherStatus(this,"+InpatientConfig.MOTHER_STATUS_DEAD+")";%>
						<html:select name="ANCDeliveryDetailFB" property="motherStatus" tabindex="1" styleClass="ancComboNormal" onchange="<%=motherStatusOnChangeFun%>">
							<html:option value="-1">Select Value</html:option>
							<html:option value="<%=InpatientConfig.MOTHER_STATUS_DEAD%>"><%=InpatientConfig.MOTHER_STATUS_ARR[Integer.parseInt(InpatientConfig.MOTHER_STATUS_DEAD)]%></html:option>
							<html:option value="<%=InpatientConfig.MOTHER_STATUS_ALIVE%>"><%=InpatientConfig.MOTHER_STATUS_ARR[Integer.parseInt(InpatientConfig.MOTHER_STATUS_ALIVE)]%></html:option>
						</html:select>
					</div>
				</td>
			</tr>
		</table>
		<!-- Mother Death Cause Detail -->
		<div id="divDeathCause" style="display: none;">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="deathcause"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="75%"  class="tdfont" colspan="3">
					<div align="left">
						<html:select name="ANCDeliveryDetailFB" property="deathCause" tabindex="1" styleClass="ancComboNormal">
							<html:option value="-1">Select Value</html:option>
							<html:option value="<%=InpatientConfig.MOTHER_DEATH_CAUSE_OBSTETRIC%>"><%=InpatientConfig.MOTHER_DEATH_CAUSE_ARR[Integer.parseInt(InpatientConfig.MOTHER_DEATH_CAUSE_OBSTETRIC)]%></html:option>
							<html:option value="<%=InpatientConfig.MOTHER_DEATH_CAUSE_NON_OBSTETRIC%>"><%=InpatientConfig.MOTHER_DEATH_CAUSE_ARR[Integer.parseInt(InpatientConfig.MOTHER_DEATH_CAUSE_NON_OBSTETRIC)]%></html:option>
						</html:select>
					</div>
				</td>
			</tr>
		</table>
		</div>
	</his:ContentTag>
	</div>

	<his:SubTitleTag key="ancdeloutcmdtl">
		<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0">
			<tr><td width="1%" align="right" valign="middle">				
				<img id="imgANCDelOutcome" tabindex="1" style="cursor: pointer;" src="/HIS/hisglobal/images/avai/arrow-down.png"; onclick="onclickImage(this)"/>
			</td></tr>
		</table>
	</his:SubTitleTag>

	<div id="divANCDelOutcome" style="display: none;">
	<!-- Placenta Detail -->
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<bean:message key="placentaweight"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDeliveryDetailFB" property="placentaWeight" tabindex="1" maxlength="4" styleClass="ancTextboxSmallMedium" onkeypress="return validateNumericOnly(this,event)" ></html:text>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
								<bean:message key="placentatype"/>
						</b></font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:select name="ANCDeliveryDetailFB" property="placentaTypeId" tabindex="1" styleClass="ancComboNormal">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_PLACENTA_TYPE_LIST %>">
								<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_PLACENTA_TYPE_LIST %>" property="value" labelProperty="label" />
							</logic:present>								
   						</html:select>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="placentamorphology"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:textarea name="ANCDeliveryDetailFB" property="placentaMorphology" tabindex="1" styleClass="ancTextareaNormal" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumOnly(this,event))" ></html:textarea>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="placentahistopath"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:textarea name="ANCDeliveryDetailFB" property="placentaHistopath" tabindex="1" styleClass="ancTextareaNormal" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumOnly(this,event))" ></html:textarea>
					</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="placentaremovalmethod"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
   						<html:select name="ANCDeliveryDetailFB" property="placentaRemovalMethodId" tabindex="1" styleClass="ancComboNormal">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_PLACENTA_REMOVAL_METHOD_LIST %>">
								<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_PLACENTA_REMOVAL_METHOD_LIST %>" property="value" labelProperty="label" />
							</logic:present>								
   						</html:select>
					</div>
				</td>
				<!-- deliveryCount -->
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><font color="#FF0000">*</font></b>
							<b><bean:message key="delcount"/></b>
						</font>		
					</div>
				</td>
				<td width="25%"  class="tdfont">
					<div align="left">
						<html:text name="ANCDeliveryDetailFB" property="deliveryCount" tabindex="1" maxlength="2" styleClass="ancTextboxSmallMedium" onchange="onchangeDelCount(this)" onkeypress="return validatePositiveIntegerOnly(this,event)" ></html:text>
					</div>
				</td>
			</tr>
		</table>
		<table id="tblOutcome" style="display: none;" width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr id="trHeaderOutcome">
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
								<font color="#FF0000">*</font>
								<bean:message key="birdatetime"/>
							</b>
						</font>		
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="birnature"/>
							</b>
						</font>		
					</div>
				</td>
	 			<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">			
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
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
			<tr id="trDataOutcome" style="display: none;">
				<td width="2%" class="tdfonthead">			
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b id="boldSlNo#">
						0.
						</b></font>		
					</div>
				</td>
				<td width="10%" class="tdfonthead" nowrap="nowrap">			
					<div align="center">
						<html:hidden name="ANCDeliveryDetailFB" property="birthDateTime"/>
						<html:text name="ANCDeliveryDetailFB" property="birthDate" value="dd-mm-yyyy" tabindex="1" maxlength="10" size="11" onfocus="DateValidator.setupOnFocus(this)"></html:text>
						<html:text name="ANCDeliveryDetailFB" property="birthTime" value="hh:mm" tabindex="1" maxlength="5" size="6" onfocus="TimeValidator.setupOnFocus(this)"></html:text>						
						<!--<html:text name="ANCDeliveryDetailFB" tabindex="1" property="birthTime" maxlength="5" size="6" onkeypress="return (validatePositiveIntegerOnly(this,event) || validateWithCharacters(this,event,':'));" onchange="onchangeRupDateTime()"></html:text>-->   						
					</div>
				</td>
				<td width="10%" class="tdfonthead">			
					<div align="center">
						<% String onchangeNatureId="onChangeBirthNature(this,'"+InpatientConfig.BIRTH_NATURE_LIVE_BIRTH+"')"; %>						
						<html:select name="ANCDeliveryDetailFB" property="birthNatureId" tabindex="1" styleClass="ancComboSmallMedium" onblur="<%=onchangeNatureId%>">
							<html:option value="-1">Select</html:option>
							<html:option value="<%=InpatientConfig.BIRTH_NATURE_LIVE_BIRTH%>"><%=InpatientConfig.BIRTH_NATURE_ARR[Integer.parseInt(InpatientConfig.BIRTH_NATURE_LIVE_BIRTH)]%></html:option>
							<html:option value="<%=InpatientConfig.BIRTH_NATURE_STILL_BIRTH%>"><%=InpatientConfig.BIRTH_NATURE_ARR[Integer.parseInt(InpatientConfig.BIRTH_NATURE_STILL_BIRTH)]%></html:option>
							<html:option value="<%=InpatientConfig.BIRTH_NATURE_NEONATAL_DEATH%>"><%=InpatientConfig.BIRTH_NATURE_ARR[Integer.parseInt(InpatientConfig.BIRTH_NATURE_NEONATAL_DEATH)]%></html:option>
						</html:select>
					</div>
				</td>
	 			<td width="10%" class="tdfonthead">			
					<div align="center">
						<html:select name="ANCDeliveryDetailFB" property="birthGenderCode" tabindex="1" styleClass="ancComboSmallMedium">
							<html:option value="-1">Select</html:option>
							<logic:present name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_GENDER_LIST %>">
								<html:options collection="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_GENDER_LIST %>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
				<td width="10%" class="tdfonthead">			
					<div align="center">
						<html:text name="ANCDeliveryDetailFB" property="birthWeight" tabindex="1" maxlength="3" styleClass="ancTextboxSmallMedium" onkeypress="return validatePositiveNumericOnly(this,event)" />
					</div>
				</td>
				<td width="10%" class="tdfonthead">			
					<div align="center">
						<html:text name="ANCDeliveryDetailFB" property="birthPresentHealth" tabindex="1" maxlength="100" styleClass="ancTextboxNormal" onkeypress="return validateAlphaOnly(this,event)"></html:text>
					</div>
				</td>
				<td width="10%" class="tdfonthead">			
					<div align="center">
						<html:text name="ANCDeliveryDetailFB" property="birthDeathAge" tabindex="1" maxlength="2" styleClass="ancTextboxSmallMedium" onkeypress="return validatePositiveIntegerOnly(this,event)" />
					</div>
				</td>
				<td width="10%" class="tdfonthead">			
					<div align="center">
						<html:text name="ANCDeliveryDetailFB" property="birthDeathCause" tabindex="1" maxlength="100" styleClass="ancTextboxNormal" onkeypress="return validateAlphaOnly(this,event)"></html:text>
					</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>	
	</div>

</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer"  tabindex='2' onclick =  "submitFormOnValidate(validateAdd(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateAdd(),'SAVE');")>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</his:statusTransactionInProcess>
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusUnsuccessfull>
</his:ButtonToolBarTag>
	

<html:hidden name="ANCDeliveryDetailFB" property="hmode"/>
<html:hidden name="ANCDeliveryDetailFB" property="entryDate"/>
<html:hidden name="ANCDeliveryDetailFB" property="entryTime"/>
<html:hidden name="ANCDeliveryDetailFB" property="patCrNo"/>
<html:hidden name="ANCDeliveryDetailFB" property="episodeCode"/>
<html:hidden name="ANCDeliveryDetailFB" property="episodeVisitNo"/>
<html:hidden name="ANCDeliveryDetailFB" property="admissionNo"/>
<html:hidden name="ANCDeliveryDetailFB" property="departmentUnitCode"/>
<html:hidden name="ANCDeliveryDetailFB" property="wardCode"/>
<html:hidden name="ANCDeliveryDetailFB" property="userSeatId"/>
