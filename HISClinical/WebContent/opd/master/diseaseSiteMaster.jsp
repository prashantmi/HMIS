<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="opd.*" %>	

<%@page import="hisglobal.hisconfig.Config"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/validationCommon.js"/>
<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script>
function setSubGroup(elemGroup)
{
	if(elemGroup.value=="-1")
	{
		doEmtyCombo(document.getElementsByName("icdSubgroupCode")[0]);
		doEmtyCombo(document.getElementsByName("diseaseCode")[0]);
	}
	else
	{
		submitForm("SETSUBGROUP");
	}
}

function doEmtyCombo(cbo)
{
	cbo.innerHTML = "";
	var opt = document.createElement("option");
	opt.text = "Select Value";
	opt.value = "-1";
	cbo.appendChild(opt);
}

function setOnCodeType(elem)
{
	var divSubgroup = document.getElementById("divSubGroup");
	var divDisease = document.getElementById("divDisease");
	var divSitCode = document.getElementById("divSiteCode");
	if(elem.value == "-1" || elem.value == "<%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED%>")
	{
		divSubgroup.style.display = "none";
		divDisease.style.display = "none";
		divSitCode.style.display = "none";
	}
	else if(elem.value == "<%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_SUBGROUP%>")
	{
		divSubgroup.style.display = "";
		divDisease.style.display = "none";
		divSitCode.style.display = "";
	}
	else if(elem.value == "<%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE%>")
	{
		divSubgroup.style.display = "";
		divDisease.style.display = "";
		divSitCode.style.display = "";
		if(document.getElementsByName("diseaseCode")[0].options.length==1)
		{
			document.getElementsByName("icdSubgroupCode")[0].value = "-1";
			doEmtyCombo(document.getElementsByName("diseaseCode")[0]);
		}
	}
}

function setDiseaseOnCodeType(elemSubGrp)
{
	var elemCodeType = document.getElementsByName("icdCodeType")[0];
	
	if(elemCodeType.value != "<%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE%>")
	{
		doEmtyCombo(document.getElementsByName("diseaseCode")[0]);
		return;
	}	
	if(elemSubGrp.value=="-1")
	{
		doEmtyCombo(document.getElementsByName("diseaseCode")[0]);
	}
	else
	{
		submitForm("SETDISEASE");
	}
}

function validate()
{
	if(trimData(document.getElementsByName("diseaseSite")[0].value)=="")
	{
		alert("Please Enter Diagnosis Site");
		document.getElementsByName("diseaseSite")[0].focus();
		return false;
	}	
	if(document.getElementsByName("icdCodeType")[0].value=="-1")
	{
		alert("Please Select Type");
		document.getElementsByName("icdCodeType")[0].focus();
		return false;
	}	
	if(document.getElementsByName("icdCodeType")[0].value=="<%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_SUBGROUP%>")
	{
		if(document.getElementsByName("icdGroupCode")[0].value=="-1")
		{
			alert("Please Select Group");
			document.getElementsByName("icdGroupCode")[0].focus();
			return false;
		}
		if(document.getElementsByName("icdSubgroupCode")[0].value=="-1")
		{
			alert("Please Select Sub Group");
			document.getElementsByName("icdSubgroupCode")[0].focus();
			return false;
		}
		if(trimData(document.getElementsByName("icdSiteCode")[0].value)=="")
		{
			alert("Please Enter Diagnosis Site Code");
			document.getElementsByName("icdSiteCode")[0].focus();
			return false;
		}
	}	
	if(document.getElementsByName("icdCodeType")[0].value=="<%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE%>")
	{
		if(document.getElementsByName("icdGroupCode")[0].value=="-1")
		{
			alert("Please Select Group");
			document.getElementsByName("icdGroupCode")[0].focus();
			return false;
		}
		if(document.getElementsByName("icdSubgroupCode")[0].value=="-1")
		{
			alert("Please Select Sub Group");
			document.getElementsByName("icdSubgroupCode")[0].focus();
			return false;
		}
		if(document.getElementsByName("diseaseCode")[0].value=="-1")
		{
			alert("Please Select Disease");
			document.getElementsByName("diseaseCode")[0].focus();
			return false;
		}
		if(trimData(document.getElementsByName("icdSiteCode")[0].value)=="")
		{
			alert("Please Enter Diagnosis Site Code");
			document.getElementsByName("icdSiteCode")[0].focus();
			return false;
		}
	}	
	return true;
}

window.onload = function()
{
	setOnCodeType(document.getElementsByName("icdCodeType")[0]);
}
</script>
</head>

<body>
<html:form action="/master/diseaseSiteMaster.cnt">
	<his:TransactionContainer>	
		<his:TitleTag name=" Diagnosis Site Master >> Add">
		</his:TitleTag>
		
		<his:statusTransactionInProcess>		
		<his:ContentTag>
		<logic:notEqual name="DiseaseSiteFB" property="hmode" value="VIEW">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font> <bean:message key="diagnosisSite"/>&nbsp;</b>
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							&nbsp;<html:text name="DiseaseSiteFB" property="diseaseSite" maxlength="100" size="50" onkeypress="return validateAlphabetsOnly(event,this)"></html:text>
						</div>
					</td>
				</tr>
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font> <bean:message key="type"/>&nbsp;</b>
							</font>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
					         &nbsp;<html:select name="DiseaseSiteFB" property="icdCodeType" onchange="setOnCodeType(this)" style="width:100px;">
							  <html:option value="-1">Select Value</html:option>
							  <html:option value="<%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED%>"><%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED)]%></html:option>
							  <html:option value="<%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_SUBGROUP%>"><%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_SUBGROUP)]%></html:option>
							  <html:option value="<%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE%>"><%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE)]%></html:option>
						   </html:select>
						</div>
					</td>
				</tr>
			</table>
				
			<div id="divSubGroup" style="display: none;">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font> <bean:message key="group"/>&nbsp;</b>
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
							&nbsp;<html:select name="DiseaseSiteFB" property="icdGroupCode" onchange="setSubGroup(this)" style="width:260px;">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP%>" property="icdGroupCode" labelProperty="icdGroup" />
								</logic:present>
							</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font> <bean:message key="subGroup"/>&nbsp;</b>
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left">
							&nbsp;<html:select name="DiseaseSiteFB" property="icdSubgroupCode" onchange="setDiseaseOnCodeType(this)" style="width:260px;">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE%>" property="icdSubgroupCode" labelProperty="icdSubgroup" />
								</logic:present>
							</html:select>
							</div>
						</td>
					</tr>
				</table>
			</div>	
			<div id="divDisease" style="display: none;">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font> <bean:message key="disease"/>&nbsp;</b>
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
							&nbsp;<html:select name="DiseaseSiteFB" property="diseaseCode" style="width:260px;">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE%>" property="diseaseCode" labelProperty="disease" />
								</logic:present>
							</html:select>
							</div>
						</td>
					</tr>
				</table>
			</div>	
			<div id="divSiteCode" style="display: none;">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font> <bean:message key="sitecode"/>&nbsp;</b>
							</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left">
							&nbsp;<html:text name="DiseaseSiteFB" property="icdSiteCode" maxlength="1" size="5" onkeypress="return validatePositiveIntegerOnly(this,event)"></html:text>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<logic:equal name="DiseaseSiteFB" property="hmode" value="MODIFY">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*</font><b><bean:message key="isvalid"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									&nbsp;<html:select name="DiseaseSiteFB" property="isValid" tabindex="1">
							  			<html:option value="-1">Select Value</html:option>
										<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option>
										<html:option value="<%=Config.IS_VALID_INACTIVE %>">Inactive</html:option>
					   				</html:select>
								</div>
							</td>
						</tr>
					</table>
				</logic:equal>
		</logic:notEqual>
		<logic:equal name="DiseaseSiteFB" property="hmode" value="VIEW">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<b><bean:message key="diagnosisSite"/>&nbsp;</b>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<b>&nbsp;<bean:write name="DiseaseSiteFB" property="diseaseSite"/></b>
							<html:hidden name="DiseaseSiteFB" property="diseaseSite" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<b><bean:message key="type"/>&nbsp;</b>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<bean:define name="DiseaseSiteFB" property="icdCodeType" id="codeType" type="java.lang.String"/>
								<b>&nbsp;<%=OpdConfig.ICD_DISEASE_SITE_CODETYPE_ARR[Integer.parseInt(codeType)]%></b>
								<html:hidden name="DiseaseSiteFB" property="icdCodeType" />
						</div>
					</td>
				</tr>
				<logic:notEmpty name="DiseaseSiteFB" property="icdGroupCode">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<b><bean:message key="group"/>&nbsp;</b>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
								<b>&nbsp;<bean:write name="DiseaseSiteFB" property="icdGroup"/></b>
								<html:hidden name="DiseaseSiteFB" property="icdGroupCode" />
						</div>
					</td>
				</tr>
				</logic:notEmpty>
				<logic:notEmpty name="DiseaseSiteFB" property="icdSubgroupCode">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
								<b><bean:message key="subGroup"/>&nbsp;</b>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
								<b>&nbsp;<bean:write name="DiseaseSiteFB" property="icdSubgroup"/></b>
								<html:hidden name="DiseaseSiteFB" property="icdSubgroupCode" />
						</div>
					</td>
				</tr>
				</logic:notEmpty>
				<logic:notEmpty name="DiseaseSiteFB" property="diseaseCode">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
								<b><bean:message key="disease"/>&nbsp;</b>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
								<b>&nbsp;<bean:write name="DiseaseSiteFB" property="disease"/></b>
								<html:hidden name="DiseaseSiteFB" property="diseaseCode" />
						</div>
					</td>
				</tr>
				</logic:notEmpty>
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">							
							<b><bean:message key="sitecode"/>&nbsp;</b>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
								<b>&nbsp;<bean:write name="DiseaseSiteFB" property="icdSiteCode"/></b>
								<html:hidden name="DiseaseSiteFB" property="icdSiteCode" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
 								<b><bean:message key="isvalid"/>&nbsp;</b>
						</div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<bean:define name="DiseaseSiteFB" property="isValid" id="valid" type="java.lang.String"/>
								<b>&nbsp;<%=Config.IS_VALID_ARR[Integer.parseInt(valid)]%></b>
								<html:hidden name="DiseaseSiteFB" property="isValid" />
						</div>
					</td>
				</tr>
			</table>
		</logic:equal>
		</his:ContentTag>
		</his:statusTransactionInProcess>
		<his:ButtonToolBarTag>
			<his:statusTransactionInProcess>
				<logic:equal name="DiseaseSiteFB" property="hmode" value="ADD">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer"  onclick ="submitFormOnValidate(validate(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validate(),'SAVE');">
				</logic:equal>
				<logic:equal name="DiseaseSiteFB" property="hmode" value="MODIFY">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer"  onclick ="submitFormOnValidate(validate(),'MODIFYSAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validate(),'MODIFYSAVE');">
				</logic:equal>
			</his:statusTransactionInProcess>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer"  onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
			<his:statusTransactionInProcess>
				<logic:equal name="DiseaseSiteFB" property="hmode" value="ADD">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer"  onclick ="submitForm('ADD')" onkeypress="if(event.keyCode==13) submitForm('ADD');">
				</logic:equal>
			</his:statusTransactionInProcess>			
			
		</his:ButtonToolBarTag>
		<center><b><his:status/></b></center>
	</his:TransactionContainer>
	
	<html:hidden name="DiseaseSiteFB" property="hmode"/>
	<html:hidden name="DiseaseSiteFB" property="diseaseSiteId"/>

</html:form>
</body>
</html>