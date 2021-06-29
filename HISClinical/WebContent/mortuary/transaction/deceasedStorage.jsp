<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<script type="text/javascript">
window.onload=function()
{
	relativeDetails();
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function getDeceasedDetail(obj)
{
	document.getElementsByName("patCrNo")[0].value=obj.value.split("@")[1];
	document.getElementsByName("deceasedNo")[0].value=obj.value.split("@")[0];
	submitForm('DECEASEDDTL')
}

function getRack()
{
	var chamberId=document.getElementsByName("chamberId")[0].value;
	if(chamberId!="-1")
		submitForm('GETRACK');
}

function getChamber()
{
	var mortuaryAreaCode=document.getElementsByName("mortuaryAreaCode")[0].value;
	if(mortuaryAreaCode!="-1")
		submitForm('GETCHAMBER');
}

function getMortuaryArea()
{
	var mortuaryCode=document.getElementsByName("mortuaryCode")[0].value;
	if(mortuaryCode!="-1")
		submitForm('GETMORTUARYAREA');
}

function validateSave()
{
	var valid=false;
	if(comboValidation(document.forms[0].mortuaryCode,"Mortuary")
		&& comboValidation(document.forms[0].mortuaryAreaCode,"Mortuary Area")
		&& comboValidation(document.forms[0].chamberId,"Chamber")
		&& comboValidation(document.forms[0].chamberRackId,"Rack")
		&& comboValidation(document.forms[0].bodyPutBy,"Body Put By")
		&& validateStorageUpto()
	//	&& validateStorageRelative()
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

function validateStorageRelative()
{
	var valid=true;
	if(document.getElementsByName("isStorageByRelative")[0].checked)
	{
		if(isEmpty(document.forms[0].storageRelativeName,"Relative Name")
			&& comboValidation(document.forms[0].storageRelativeCode,"Relationship")
			&& isEmpty(document.forms[0].storageRelativeAddress,"Address of Relative")
			&& isEmpty(document.forms[0].storageRelativeContactNo,"Relative Contact No")
		)
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}
	else
		valid=true;
	
	return valid;
}

function validateStorageUpto()
{
	var valid=true;
	var maxUpto="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS%>"
	if(document.getElementsByName("storageUpto")[0].value=="" || document.getElementsByName("storageUpto")[0].value=="0" || document.getElementsByName("storageUpto")[0].value=="00")
	{
		alert("Please Enter The Storage Duration");
		document.getElementsByName("storageUpto")[0].focus();
		valid=false;
	}
	else
	{
		if(parseInt(document.getElementsByName("storageUpto")[0].value)>parseInt(maxUpto))
		{
			alert("Entered Storage Duration Cannot be Greater Than "+ maxUpto + " Hours");
			document.getElementsByName("storageUpto")[0].focus();
			valid=false;
		}
		else
		{
			valid=true;
		}
	}
	
	return valid;
}

function clearForm()
{
	document.getElementsByName("mortuaryCode")[0].value="-1";
	document.getElementsByName("mortuaryAreaCode")[0].value="-1";
	document.getElementsByName("chamberId")[0].value="-1";
	document.getElementsByName("chamberRackId")[0].value="-1";
	document.getElementsByName("bodyPutBy")[0].value="-1";
	document.getElementsByName("storageUpto")[0].value="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS%>";
	document.getElementsByName("storageReason")[0].value="";
	//document.getElementsByName("isStorageByRelative")[0].checked=false;
	document.getElementsByName("storageRelativeName")[0].value="";
	document.getElementsByName("storageRelativeAddress")[0].value="";
	document.getElementsByName("storageRelativeContactNo")[0].value="";
	document.getElementsByName("storageRelativeCode")[0].value="-1";
}

function relativeDetails()
{
	if(document.getElementsByName("isStorageByRelative")[0].checked)
	{
		document.getElementById("divRelativeStorageId").style.display="block";
		document.getElementsByName("isStorageByRelative")[0].value="<%=MortuaryConfig.IS_DECEASED_STORAGE_BY_RELATIVE_YES%>";
		
	}
	else
	{
		document.getElementById("divRelativeStorageId").style.display="none";
		document.getElementsByName("isStorageByRelative")[0].value="<%=MortuaryConfig.IS_DECEASED_STORAGE_BY_RELATIVE_NO%>";
		
	}
}
</script>

<body>
	<html:form action="/deceasedStorage">
		<his:TransactionContainer>
			<his:TitleTag name="Deceased Storage Detail">
			</his:TitleTag>
			<his:statusList>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="select"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deceasedNo"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deceased"/>
											<bean:message key="name"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="16%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deceased"/>
											<bean:message key="type"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="22%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="deathdate"/>
										</b>	
									</font>
								</div>
							</td>
							<td width="22%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>
											<bean:message key="receiveDate"/>
										</b>	
									</font>
								</div>
							</td>
							
						</tr>
						<logic:iterate id="arrDeceasedListVO" name="<%=MortuaryConfig.DECEASED_LIST_IN_STREACHER %>" type="hisglobal.vo.DeceasedDetailVO" indexId="idx">
							<tr>
								<td width="5%" class="tdfont">
									<div align="center">
										<html:radio name="DeceasedStorageFB" property="selectedDeceased" value="<%=arrDeceasedListVO.getDeceasedNo()+'@'+ arrDeceasedListVO.getPatCrNo()%>" onclick="getDeceasedDetail(this)" tabindex="1" ></html:radio>
									
									</div>
								</td>
								<td width="15%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeceasedNo() %>
										</font>
									</div>
								</td>
								<td width="20%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getPatFirstName()==null?"":arrDeceasedListVO.getPatFirstName() %>
											<%=arrDeceasedListVO.getPatMiddleName()==null?"":arrDeceasedListVO.getPatMiddleName() %>
											<%=arrDeceasedListVO.getPatLastName()==null?"":arrDeceasedListVO.getPatLastName() %>
										</font>	
									</div>
								</td>
								<td width="16%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeceasedType() %>
										</font>	
									</div>
								</td>
								<td width="22%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getDeathDate() %>
										</font>	
									</div>
								</td>
								
								<td width="22%" class="tdfont">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=arrDeceasedListVO.getReceivedDateTime() %>
										</font>	
									</div>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
			</his:statusList>
			
			<his:statusTransactionInProcess>
				<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
				<his:SubTitleTag name="Deceased Storage Detail">
				</his:SubTitleTag>
				
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="mortuary"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="DeceasedStorageFB" property="mortuaryCode" onchange="getMortuaryArea()" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_MORTUARY %>">
											<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_MORTUARY %>" property = "value" labelProperty = "label"/>
										</logic:present>
									</html:select>
									
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
											<bean:message key="mortuary"/>
											<bean:message key="area"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="DeceasedStorageFB" property="mortuaryAreaCode" tabindex="1" onchange="getChamber()" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_AREA_BASED_ON_MORTUARY %>">
												<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_AREA_BASED_ON_MORTUARY %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
							</tr>
						
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
										<bean:message key="chamber"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									<html:select name="DeceasedStorageFB" property="chamberId" onchange="getRack()" tabindex="1" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=MortuaryConfig.ESENTIAL_ALL_CHAMBER_BASED_ON_MORTUARY_AREA %>">
											<html:options collection="<%=MortuaryConfig.ESENTIAL_ALL_CHAMBER_BASED_ON_MORTUARY_AREA %>" property = "value" labelProperty = "label"/>
										</logic:present>
									</html:select>
									
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#FF0000">*</font>
											<bean:message key="rack"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="DeceasedStorageFB" property="chamberRackId" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_CHAMBER_RACK_LIST %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
												<bean:message key="bodyPutBy"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:select name="DeceasedStorageFB" property="bodyPutBy" tabindex="1" >
											<html:option value="-1">Select Value</html:option>
											<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST %>">
												<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_MORTUARY_EMPLOYEE_LIST %>" property = "value" labelProperty = "label"/>
											</logic:present>
										</html:select>
										
									</div>
								</td>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<font color="#FF0000">*</font>
											<bean:message key="storageUpto"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="DeceasedStorageFB" property="storageUpto" value="<%=MortuaryConfig.DECEASED_STORAGE_UPTO_HOURS %>" maxlength="2" size="5" onkeypress="return validateNumeric(event)" tabindex="1" ></html:text>
										<bean:message key="hrs"/>
									</div>
								</td>
							</tr>
							<tr>	
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="reason"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<html:text name="DeceasedStorageFB" property="storageReason" maxlength="100" onkeypress="return validateAlphabetsWithDotsOnly(event,this)" tabindex="1" ></html:text>
									</div>
								</td>
								<td width="25%" class="tdfonthead">
								</td>
								<td width="25%" class="tdfont">
								</td>
							</tr>
						</table>
						
				</his:ContentTag>
				
			</his:statusTransactionInProcess>
		
		
		
		<his:ButtonToolBarTag>
			<his:statusList>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
			</his:statusList>
				
			<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('FINALCANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
			</his:statusUnsuccessfull>
			
			<his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer tabindex="1" onclick ="if(validateSave()) submitPage('SAVE')" onkeypress="if(event.keyCode==13)if(validateSave())submitPage('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick =" clearForm()" onkeypress="if(event.keyCode==13)clearForm()">
			</his:statusTransactionInProcess>
			
		</his:ButtonToolBarTag>
		
		</his:TransactionContainer>
		
		<html:hidden name="DeceasedStorageFB" property="hmode"/>
		<html:hidden name="DeceasedStorageFB" property="patCrNo" />
		<html:hidden name="DeceasedStorageFB" property="deceasedNo" />
		
	</html:form>
	<his:status/>
</body>

</html>