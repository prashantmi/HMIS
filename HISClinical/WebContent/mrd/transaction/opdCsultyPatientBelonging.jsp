<!-- 
/**
 * @author CDAC
 */
-->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="opd.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head> 	
<logic:equal name="OpdCsultyPatientBelongingFB" property="isDirectCall" value="DIRECT">
		<his:css src="/hisglobal/css/tab.css" />
		<his:css src="/hisglobal/css/Color.css" />
		<his:css src="/hisglobal/css/master.css" />
		<his:css src="/hisglobal/css/hisStyle.css" />
		<his:css src="/hisglobal/css/hisStyleExt.css" />
		<his:css src="/hisglobal/css/calendar-blue2.css" />
		
		<his:javascript src="/registration/js/registration.js" />
		<his:javascript src="/registration/js/calendar.js" />
		<his:javascript src="/registration/js/validationCommon.js" />
		<his:javascript src="/registration/js/validationCalls.js" />
		<his:javascript src="/registration/js/commonFunctions.js" />
		<his:javascript src="/registration/js/dateFunctions.js" />
		<his:javascript src="/registration/js/popup.js" />
		<his:javascript src="/opd/opdJs/opd.js,registration.*" />
		<his:javascript src="/hisglobal/js/validation.js"/>
        <his:javascript src="/hisglobal/transactionutil/js/master.js"/>
        <his:javascript src="/hisglobal/js/util.js"/>
</logic:equal>

<script type="text/javascript">

function validateAddBelongingDetail()
{
	var valid=false
	var itemName=document.getElementsByName("belongingItemCode")[0];
	var itemDescription=document.getElementsByName("quantity")[0];
	var remarks=document.getElementsByName("remarks")[0];
	
	if(comboValidation(itemName,"Item Name") &&
		isEmpty(itemDescription,"Item Description") &&
		isEmpty(remarks,"Remarks"))
	{
		valid=true
	}
		return valid
}

function vaidateRemoveDetail(belongingItemCode)
{

	document.forms[0].removeBelongingCode.value=belongingItemCode;
	//alert("belongingItemCode"+document.forms[0].removeBelongingCode.value);
	return true;
}

</script>


<logic:equal name="OpdCsultyPatientBelongingFB" property="isDirectCall" value="DIRECT">
	<form action="/HISClinical/opd/opdCasualPatientBelonging.cnt" method="post">
</logic:equal>

<%
	String varStatus="";
String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
%>
<his:TransactionContainer>
<his:TitleTag name="Patient Belonging(Talaashi)">
<logic:equal name="OpdCsultyPatientBelongingFB" property="isDirectCall" value="DIRECT">
	<font size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
		
	</font>
</logic:equal>
</his:TitleTag>

<his:statusNew>
<%	
	varStatus="New";
%>
<logic:equal name="OpdCsultyPatientBelongingFB" property="hmode" value="GETCRNO">
<his:InputCrNoTag name="OpdCsultyPatientBelongingFB"> </his:InputCrNoTag>	
</logic:equal>
</his:statusNew>

<logic:equal name="OpdCsultyPatientBelongingFB" property="isDirectCall" value="DESK">
	<bean:define id="crNo" name="OpdCsultyPatientBelongingFB" property="patCrNo" type="java.lang.String" />
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
</logic:equal>


<his:statusTransactionInProcess>
	<%varStatus="InProcess";%>

	<logic:equal name="OpdCsultyPatientBelongingFB" property="isDirectCall" value="DIRECT">
		<bean:define id="crNo" name="OpdCsultyPatientBelongingFB" property="patCrNo" type="java.lang.String" />
		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
	</logic:equal>

	<%
	if(session.getAttribute(OpdConfig.PATIENT_BELONGING_DETAILS_VOS)!=null)
	{
	%>

	<his:SubTitleTag name="Patient Belonging Detail">
		<input type="button" name="modify" value="Modify" onclick="submitForm('MODIFY')" tabindex="1">
		<input type="button" name="handOver" value="Hand Over" onclick="submitForm('HANDOVER')" tabindex="1">
		<input type="button" name="handOveritem" value="Handed Over Item" onclick="submitForm('HANDOVERLIST')" tabindex="1">
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="itemName" /></b>
						</font>
					</div>
				</td>
				
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="description" /></b>
						</font>
					</div>
				</td>
				
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remarks" /></b>
						</font>
					</div>
				</td>
				
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="collectionDate" /></b>
						</font>
					</div>
				</td>
			</tr>

			<logic:iterate id="patBelongingVOs" indexId="idx" name="<%=OpdConfig.PATIENT_BELONGING_DETAILS_VOS%>" type="hisglobal.vo.PatientBelongingVO">
				<logic:empty name="patBelongingVOs" property="handOverTo">
					<tr>
						<td class="tdfont">
							<div align="center">
								<%= patBelongingVOs.getBelongingItemName()%>
							</div>
						</td>
						
						<td class="tdfont">
							<div align="center">
								<%= patBelongingVOs.getQuantity()%>
							</div>
						</td>
						
						<td class="tdfont">
							<div align="center">
								<%= patBelongingVOs.getRemarks() %>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<%= patBelongingVOs.getCollectionDate() %>
							</div>
						</td>
					</tr>
				</logic:empty>
			</logic:iterate>
		</table>
	</his:ContentTag>

	<%
	}
	%>

	<his:SubTitleTag name="Add Patient Belonging Detail">
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="32%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>							
								<font color="#FF0000">*</font>
								<bean:message key="itemName" />
						</b></font>
					</div>
				</td>
				
				<td width="32%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<font color="#FF0000">*</font>
							<bean:message key="description" />
						</b></font>
					</div>
				</td>
				
				<td width="32%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
							<font color="#FF0000">*</font>
							<bean:message key="remarks" />
						</b></font>
					</div>
				</td>
				
				<td width="4%" class="tdfonthead"></td>
			</tr>

			<tr>
				<td class="tdfont">
					<div align="center">
						<html:select name="OpdCsultyPatientBelongingFB" tabindex="1" property="belongingItemCode" styleClass="regcbo">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.ESSENTIALBO_BELONGING_LIST%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALBO_BELONGING_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
				
				<td class="tdfont">
					<div align="center">
						<html:text name="OpdCsultyPatientBelongingFB" tabindex="1" property="quantity" styleClass="textbox" maxlength="20" 
						onkeypress="return validateAlphaNumericOnly(event,this)" ></html:text>
					</div>
				</td>
				
				<td class="tdfont">
					<div align="center">
						<html:text name="OpdCsultyPatientBelongingFB" property="remarks" tabindex="1" 
						styleClass="textbox" maxlength="50" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
					</div>
				</td>
				
				<td class="tdfont">
					<div align="center">
						<img class="button" id="addButton" tabindex="1" style="cursor: pointer" src='<his:path src="/hisglobal/images/sign-plus.png"/>' title="Add Belonging Detail"
						 onclick="submitFormOnValidate(validateAddBelongingDetail(),'ADDDETAIL')" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateAddBelongingDetail(),'ADDDETAIL') ">
					</div>
				</td>
			</tr>

			<logic:notEmpty name="<%=OpdConfig.PATIENT_BELONGING_MAP %>">
				<logic:iterate id="belongingMap" name="<%=OpdConfig.PATIENT_BELONGING_MAP %>" type="java.util.Map.Entry">
					<bean:define id="key" name="belongingMap" property="key"></bean:define>
					<bean:define id="list" name="belongingMap" property="value" type="java.util.ArrayList"></bean:define>

					<tr>
						<td class="tdfont">
							<div align="center"><%=list.get(0) %></div>
						</td>
						
						<td class="tdfont">
							<div align="center"><%=list.get(2) %></div>
						</td>
						
						<td class="tdfont">
							<div align="center"><%=list.get(3) %></div>
						</td>
						
						<td class="tdfont">
							<div align="center">
								<img class="button" id="deleteButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/sign-minus.png"/>'
								 title="Remove Detail" onclick="submitFormOnValidate(vaidateRemoveDetail('<%=key %>'),'REMOVEDETAIL')" tabindex="1" onkeypress="if(event.keyCode==13) submitFormOnValidate(vaidateRemoveDetail('<%=key %>'),'REMOVEDETAIL')">
							</div>
						</td>
					</tr>

				</logic:iterate>
			</logic:notEmpty>

		</table>
	</his:ContentTag>
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
	<logic:equal name="OpdCsultyPatientBelongingFB" property="isDirectCall" value="DIRECT">
		<%	if(varStatus.equals("InProcess"))	{	%>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onclick="submitFormOnValidate(validateAddBelongingDetail(),'SAVE');" onkeypress="if (event.keyCode==13) submitFormOnValidate(validateAddBelongingDetail(),'SAVE');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('CANCEL');" onkeypress="if(event.keyCode==13) submitForm('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO');">
		<%	} else {	%>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('CANCEL');" onkeypress="if(event.keyCode==13) submitForm('CANCEL');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO');">
		<%	}	%>
	</logic:equal>
	<logic:equal name="OpdCsultyPatientBelongingFB" property="isDirectCall" value="DESK">
		<%	if(varStatus.equals("InProcess"))	{	%>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onclick="submitFormOnValidate(validateAddBelongingDetail(),'SAVE');" onkeypress="if (event.keyCode==13) submitFormOnValidate(validateAddBelongingDetail(),'SAVE');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
		<%	} else {	%>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitToDesk('NEW','NEW');" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
		<%	}	%>
	</logic:equal>
</his:ButtonToolBarTag>
</his:TransactionContainer>
<html:hidden name="OpdCsultyPatientBelongingFB" property="hmode"/>
<html:hidden name="OpdCsultyPatientBelongingFB" property="patCrNo"/>
<html:hidden name="OpdCsultyPatientBelongingFB" property="episodeCode"/>
<html:hidden name="OpdCsultyPatientBelongingFB" property="episodeVisitNo"/>
<html:hidden name="OpdCsultyPatientBelongingFB" property="removeBelongingCode" />
<html:hidden name="OpdCsultyPatientBelongingFB" property="isDirectCall"/>

<logic:equal name="OpdCsultyPatientBelongingFB" property="isDirectCall" value="DIRECT">
	</form>
</logic:equal>