<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<logic:equal name="PatientAttendantFB" property="isDirectCall" value="DIRECT">
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	
	
	<his:javascript src="/hisglobal/js/commonFunctions.js" />
	<his:javascript src="/registration/js/validationCommon.js" />
	
	<his:javascript src="/registration/js/registration.js" />
	
	<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
</logic:equal>

<script type="text/javascript">

window.onload=function()
{
	showExistingNew();
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function showExistingNew()
{
	if(document.getElementsByName("existingNewFlag")[0].checked==true)
	{
		if(document.getElementsByName("isAttendantExist")[0].value==<%=OpdConfig.YES%>)
		{
			document.getElementById("newAttendantDiv").style.display="none";
			document.getElementById("existingAttendantDiv").style.display="block";
		}
		else
		{
			alert("No Attendant Exist");
			document.getElementsByName("existingNewFlag")[1].checked=true;
		}	
	}
	else
	{
		document.getElementById("newAttendantDiv").style.display="block";
		document.getElementById("existingAttendantDiv").style.display="none";
	}
}

function getAttendantDetail(obj)
{
	submitPage('GETATTDTL');
}

function clearForm()
{
	if(document.getElementsByName("existingNewFlag")[0].checked==true)
	{
		document.getElementsByName("attendantReasonId")[0].value="-1";
	}
	else
	{
		document.getElementsByName("attendantReasonId")[0].value="-1";
		document.getElementsByName("relativeName")[0].value="";
		document.getElementsByName("relationCode")[0].value="-1";
		document.getElementsByName("phoneNo")[0].value="";
		document.getElementsByName("mobileNo")[0].value="";
		document.getElementsByName("email")[0].value="";
		document.getElementsByName("address")[0].value="";
		document.getElementsByName("relativeName")[0].readOnly=false;
	}
}

function validateSave()
{
	var valid=false;
	if(document.getElementsByName("existingNewFlag")[0].checked==true)
	{
		valid=validateExisting();
	}
	else
	{
		valid=validateNew();
	}
	return valid;
}

function validateExisting()
{
	var valid=false;
	var count=0;
	
	for(var i=0;i<document.getElementsByName("selectedAttendant").length;i++)
	{
		if(document.getElementsByName("selectedAttendant")[i].checked)
			count++;
	}
	
	if(count==0)
	{
		alert("Please Select a Existing Relative");
		return false;
	}
	if(document.getElementsByName("attendantReasonId")[0].value=="-1")
	{
		alert("Please Select Attendant Reason");
		document.getElementsByName("attendantReasonId")[0].focus();
		return false;
	}
	return true;
}

function validateNew()
{
	var valid=false;
	if(comboValidation(document.forms[0].attendantReasonId,"Attendant Reason")
		&& comboValidation(document.forms[0].relationCode,"Relationship")
		&& isEmpty(document.forms[0].relativeName,"Relative Name")
		&& validateEmail(document.forms[0].email)
	)
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}


function checkExisting()
{
	/* From GBLT_RELATION_MST
		11--> Father
		12--> Mother
		13-> Husband
		14--> Wife
	*/

	var exist=false;
	var relationCode=document.getElementsByName("relationCode")[0].value;
	var fmsName=document.getElementsByName("patFarherMotherSpouceName")[0].value;
	if(relationCode== 11 || relationCode== 12 || relationCode== 13 || relationCode== 14)
	{
		for(var i=0;i<document.getElementsByName("hiddenRelationCode").length;i++)
		{
			if(relationCode==document.getElementsByName("hiddenRelationCode")[i].value)
			{
				alert("This Relationship is Added.\n Please Select From Existing Record");
				document.getElementsByName("relationCode")[0].value="-1";
				document.getElementsByName("existingNewFlag")[0].checked=true;
				showExistingNew();
				exist=true;
			}
		}
		
		if(!exist)
		{
			if(relationCode == 11)
			{
				document.getElementsByName("relativeName")[0].value=fmsName.split("@")[0];
				if(fmsName.split("@")[0]!="")
					document.getElementsByName("relativeName")[0].readOnly=true;
				else	
					document.getElementsByName("relativeName")[0].readOnly=false;
			}
			if(relationCode == 12)
			{
				document.getElementsByName("relativeName")[0].value=fmsName.split("@")[1];
				if(fmsName.split("@")[1]!="")
					document.getElementsByName("relativeName")[0].readOnly=true;
				else	
					document.getElementsByName("relativeName")[0].readOnly=false;	
			}
			if(relationCode == 13 || relationCode == 14)
			{
				document.getElementsByName("relativeName")[0].value=fmsName.split("@")[2];
				if(fmsName.split("@")[2]!="")
					document.getElementsByName("relativeName")[0].readOnly=true;
				else	
					document.getElementsByName("relativeName")[0].readOnly=false;	
			}
		}	
	}
	else
	{
		document.getElementsByName("relativeName")[0].value="";
		document.getElementsByName("relativeName")[0].readOnly=false;
	}
}
</script>

<logic:equal name="PatientAttendantFB" property="isDirectCall" value="DIRECT">
		<form action="/HISClinical/opd/patAttendantDtl.cnt" method="post">
	</logic:equal>
	
	<his:TransactionContainer>
		<his:TitleTag name="Patient Attendant Detail">
		</his:TitleTag>
		
		<his:statusNew>
			<logic:equal name="PatientAttendantFB" property="hmode" value="GETCRNO">
				<logic:empty name="PatientAttendantFB" property="patCrNo" >
					<his:InputCrNoTag name="PatientAttendantFB"></his:InputCrNoTag>
				</logic:empty>
			</logic:equal>		
		</his:statusNew>
		
		<his:statusUnsuccessfull>
			<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
		</his:statusUnsuccessfull>
		
		<his:statusList>
			<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
			<his:SubTitleTag name="Visited Department Unit">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="select"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deptName"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="unit"/>
									</b>	
								</font>
							</div>
						</td>
					</tr>
					<logic:iterate name="<%=OpdConfig.PATIENT_TODAY_VISITED_EPISODE %>" id="arrPatEpisodeVO" type="hisglobal.vo.EpisodeVO" indexId="idx">
						<tr>
							<td class="tdfontheadExam" width="10%" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<html:radio name="PatientAttendantFB" property="selectedEpisode" value="<%=idx.toString() %>" onclick="getAttendantDetail(this)" tabindex="1"></html:radio>
									</font>	
								</div>
							</td>
							<td class="tdfontheadExam" width="40%" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="arrPatEpisodeVO" property="department"/>
									</font>	
								</div>
							</td>
							<td class="tdfontheadExam" width="40%" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="arrPatEpisodeVO" property="departmentUnit"/>
									</font>	
								</div>
							</td>
						</tr>	
					</logic:iterate>
				</table>
			</his:ContentTag>			
		</his:statusList>
		
		<his:statusTransactionInProcess>
			<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
			<his:SubTitleTagBroad name="Attendant Detail">
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="100%" >
							<div align="right">
								<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="exist"/>
									</b>	
								</font>
								<html:radio name="PatientAttendantFB" property="existingNewFlag" value="<%=OpdConfig.PATIENT_ATTENDANT_EXISTING %>" onclick="showExistingNew()" tabindex="1" ></html:radio>
								<font color="#005B88" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="new"/>
									</b>
								</font>	
								<html:radio name="PatientAttendantFB" property="existingNewFlag" value="<%=OpdConfig.PATIENT_ATTENDANT_NEW%>" onclick="showExistingNew()" tabindex="1"  ></html:radio>
							</div>
						</td>
					</tr>
				</table>
		
			</his:SubTitleTagBroad>
			<div id="existingAttendantDiv">
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
						<tr>
							<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="select"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="relativename"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="realtionship"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="mobileNo"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="35%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" valign="top">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="address"/>
										</b>	
									</font>
								</div>
							</td>
						</tr>
						<logic:iterate name="<%=OpdConfig.PATIENT_ALL_ATTENDANT_VO_LIST %>" id="patAttendantVO" type="hisglobal.vo.PatientFamilyDtlVO" indexId="idx">
							<tr>
								<td class="tdfontheadExam" width="5%" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<html:radio name="PatientAttendantFB" property="selectedAttendant" value="<%=patAttendantVO.getPatRelativeId() %>" tabindex="1" ></html:radio>
										</font>	
									</div>
								</td>
								<td class="tdfontheadExam" width="30%" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="patAttendantVO" property="relativeName"/>
										</font>	
									</div>
								</td>
								<td class="tdfontheadExam" width="15%" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="patAttendantVO" property="relationName"/>
										</font>	
										<html:hidden name="PatientAttendantFB" property="hiddenRelationCode" value="<%=patAttendantVO.getRelationCode() %>"/>
									</div>
								</td>
								<td class="tdfontheadExam" width="15%" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="patAttendantVO" property="mobileNo"/>
										</font>	
									</div>
								</td>
								<td class="tdfontheadExam" width="35%" >
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:write name="patAttendantVO" property="address"/>
										</font>	
									</div>
								</td>
							</tr>	
						</logic:iterate>
					</table>
				</his:ContentTag>
			</div>
			<his:ContentTag>
				<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
					<tr>	
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="attendant"/>
									<bean:message key="reason"/>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont">
							<div align="left">
								<html:select name="PatientAttendantFB" property="attendantReasonId" tabindex="1">
									<html:option value="-1">Select value</html:option>
									<logic:present name="<%=OpdConfig.ALL_ATTENDANT_REASON_LIST %>">
										<html:options collection="<%=OpdConfig.ALL_ATTENDANT_REASON_LIST %>" property = "value" labelProperty = "label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				</table>		
			</his:ContentTag>
			<div id="newAttendantDiv">
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="realtionship"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="PatientAttendantFB" property="relationCode" onclick="checkExisting()" tabindex="1">
										<html:option value="-1">Select value</html:option>
										<logic:present name="<%=OpdConfig.ALL_RELATIONSHIP_LIST %>">
											<html:options collection="<%=OpdConfig.ALL_RELATIONSHIP_LIST %>" property = "value" labelProperty = "label"/>
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="relativename"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="PatientAttendantFB" property="relativeName" maxlength="60" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1">
									</html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="phoneNo"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="PatientAttendantFB" property="phoneNo" maxlength="20" onkeypress="return validateNumeric(event)" tabindex="1">
									</html:text>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="mobileNo"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="PatientAttendantFB" property="mobileNo" maxlength="20" onkeypress="return validateNumeric(event)" tabindex="1">
									</html:text>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="email"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:text name="PatientAttendantFB" property="email" maxlength="50"  tabindex="1">
									</html:text>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="address"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:textarea name="PatientAttendantFB" property="address" rows="1" cols="30" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))" tabindex="1">
									</html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</div>
		</his:statusTransactionInProcess>	
		
		<his:ButtonToolBarTag>
			<logic:equal name="PatientAttendantFB" property="isDirectCall" value="DESK">	
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick =" if(validateSave()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave()) submitForm('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</his:statusTransactionInProcess>
				<his:statusUnsuccessfull>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
				</his:statusUnsuccessfull>	
			</logic:equal>
			<logic:equal name="PatientAttendantFB" property="isDirectCall" value="DIRECT">
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave()) submitPage('SAVE')">
				</his:statusTransactionInProcess>	
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
				<his:statusNew>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitForm('GETCRNO')" onkeypress="if(event.keyCode==13) submitForm('GETCRNO')">
				</his:statusNew>
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</his:statusTransactionInProcess>
			</logic:equal>
		</his:ButtonToolBarTag>	
		
	</his:TransactionContainer>	
	
		<html:hidden name="PatientAttendantFB" property="hmode" />
		<html:hidden name="PatientAttendantFB" property="tempMode" />
		<html:hidden name="PatientAttendantFB" property="patCrNo"/>
		<html:hidden name="PatientAttendantFB" property="isDirectCall" />
		<html:hidden name="PatientAttendantFB" property="isAttendantExist" />
		<html:hidden name="PatientAttendantFB" property="hiddenIndex" />
		<html:hidden name="PatientAttendantFB" property="patFarherMotherSpouceName" />
	
<logic:equal name="PatientAttendantFB" property="isDirectCall" value="DIRECT">
	<his:status/>
	</form>
</logic:equal>	
	