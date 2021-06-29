
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@page import="opd.OpdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
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

<his:javascript src="/opd/js/icd_include_exclude_master.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script>
function setLabel(elemRecordType)
{
	var elem = document.getElementById("boldEntryName");
	if(elemRecordType.value=="-1")
	{
		elem.innerHTML = "";
		return;
	}
	elem.innerHTML = elemRecordType.options[elemRecordType.selectedIndex].text;

	document.getElementsByName("parentCode")[0].disabled = true;
	document.getElementsByName("parentCode")[0].value = "-1";
	if(elemRecordType.value=="<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_EXCLUDE%>")
		document.getElementsByName("parentCode")[0].disabled = false;
}

function setOnLoad()
{
	setLabel(document.getElementsByName("recordType")[0]);
	setSubGroup(document.getElementsByName("icdGroupCode")[0]);
	document.getElementsByName("icdSubgroupCode")[0].value = '<bean:write name="ICDIncludeExcludeFB" property="icdSubgroupCode"/>';
	setDiseaseList(document.getElementsByName("icdSubgroupCode")[0]);
	document.getElementsByName("diseaseCode")[0].value = '<bean:write name="ICDIncludeExcludeFB" property="diseaseCode"/>';
}
</script>

<body onload="setOnLoad()">
	<html:form action="/master/icdIncludeExcludeMst.cnt">
		<his:TransactionContainer>
		
			<his:statusTransactionInProcess>

				<his:TitleTag name=" ICD-10 Disease Include/Exclude/Synonym Master >> ADD">
				</his:TitleTag>
				
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">  
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*</font>&nbsp;<b><bean:message key="group"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
						            &nbsp;<bean:write name="ICDIncludeExcludeFB" property="icdGroup"/>
						            <html:hidden name="ICDIncludeExcludeFB" property="icdGroupCode" />
						            <html:hidden name="ICDIncludeExcludeFB" property="icdGroup" />

						          <%--   &nbsp;<html:select name="ICDIncludeExcludeFB" property="icdGroupCode" tabindex="1" style="width:250px" onchange="setSubGroup(this)">
							  			<html:option value="-1">Select Value</html:option>
							  			<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP%>">
							  				<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP%>" property="icdGroupCode" labelProperty="icdGroup" />
							  			</logic:present>
							  		</html:select> --%>
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*</font>&nbsp;<b><bean:message key="subGroup"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<%-- <div id="divHiddenSubGroups" style="display: none;">
						            <select name="icdSubgroupList">
										<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP%>">
											<logic:iterate id="objSubGroupVO" name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP%>" type="hisglobal.vo.IcdSubgroupMasterVO" >
												<option value='<%=objSubGroupVO.getIcdSubgroupCode()+"#"+objSubGroupVO.getIcdGroupCode()%>'><%=objSubGroupVO.getIcdSubgroup()%></option>
											</logic:iterate>
							  			</logic:present>
							  		</select> 
								</div> --%>
								<div align="left">
						            &nbsp;<bean:write name="ICDIncludeExcludeFB" property="icdSubgroup"/>
						            <html:hidden name="ICDIncludeExcludeFB" property="icdSubgroupCode" />
						            <html:hidden name="ICDIncludeExcludeFB" property="icdSubgroup" />
						           <%-- &nbsp;<html:select name="ICDIncludeExcludeFB" property="icdSubgroupCode" tabindex="1" style="width:250px" onchange="setDiseaseList(this)">
							  			<html:option value="-1">Select Value</html:option>
							  		</html:select> --%>
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*</font>&nbsp;<b><bean:message key="disease"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<%--<div id="divHiddenDisease" style="display: none;">
						            <select name="icdDiseaseList">
										<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBDISEASE%>">
											<logic:iterate id="objDiseaseVO" name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBDISEASE%>" type="hisglobal.vo.IcdDiseaseMasterVO" >
												<option value='<%=objDiseaseVO.getDiseaseCode()+"#"+objDiseaseVO.getIcdSubgroupCode()%>'><%=objDiseaseVO.getDisease()%></option>
											</logic:iterate>
							  			</logic:present>
							  		</select>
								</div>--%>
								<div align="left">
						            &nbsp;<bean:write name="ICDIncludeExcludeFB" property="mainDisease"/>
						            <html:hidden name="ICDIncludeExcludeFB" property="diseaseCode" />
						            <html:hidden name="ICDIncludeExcludeFB" property="mainDisease" />
						            <%--&nbsp;<html:select name="ICDIncludeExcludeFB" property="diseaseCode" tabindex="1" style="width:250px" onchange="diseaseSelected(this)">
							  			<html:option value="-1">Select Value</html:option>
							  		</html:select> --%>
								</div>
							</td>
						</tr>
					</table>

					<logic:equal name="ICDIncludeExcludeFB" property="isDiseaseDetail" value="<%=OpdConfig.YES%>">
					<div id="divDiseaseDetail">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="diseaseType"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
						            &nbsp;<bean:write name="ICDIncludeExcludeFB" property="diseaseTypeName"/>
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="iscommomly"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
					            	<logic:equal name="ICDIncludeExcludeFB" property="isCommon" value="<%=OpdConfig.ICD_DISEASE_IS_COMMONLY_USED_NOTCOMMON%>">
					            	<%=OpdConfig.ICD_DISEASE_IS_COMMONLY_USED_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_IS_COMMONLY_USED_NOTCOMMON)] %>
					            	</logic:equal>
					            	<logic:equal name="ICDIncludeExcludeFB" property="isCommon" value="<%=OpdConfig.ICD_DISEASE_IS_COMMONLY_USED_COMMON%>">
					            	<%=OpdConfig.ICD_DISEASE_IS_COMMONLY_USED_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_IS_COMMONLY_USED_COMMON)] %>
					            	</logic:equal>
								</div>
							</td>
						</tr>
					</table>
					</div>
					</logic:equal>

					<table width="100%" border="0" cellspacing="1" cellpadding="0">  
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*</font><b><bean:message key="recordType"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									&nbsp;<html:select name="ICDIncludeExcludeFB" property="recordType" tabindex="1" onchange="setLabel(this)">
							  			<html:option value="-1">Select Value</html:option>
										<html:option value="<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_INCLUDE %>"><%=OpdConfig.ICD_DISEASE_RECORD_TYPE_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_RECORD_TYPE_INCLUDE)] %></html:option>
										<html:option value="<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_EXCLUDE %>"><%=OpdConfig.ICD_DISEASE_RECORD_TYPE_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_RECORD_TYPE_EXCLUDE)] %></html:option>
										<html:option value="<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_SYNONYM %>"><%=OpdConfig.ICD_DISEASE_RECORD_TYPE_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_RECORD_TYPE_SYNONYM)] %></html:option>
					   				</html:select>
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*</font>&nbsp;<b id="boldEntryName"></b>&nbsp;
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									&nbsp;<html:textarea name="ICDIncludeExcludeFB" property="disease" tabindex="1" rows="2" cols="50" onkeypress="return (CheckMaxLength(event,this,200,3) && validateAlphaNumericOnly(event,this))" />
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="icdCode"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
						            &nbsp;<html:select name="ICDIncludeExcludeFB" property="parentCode" tabindex="1" style="width:250px" disabled="true">
							  			<html:option value="-1">Select Value</html:option>
							  			<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBDISEASE%>">
							  				<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBDISEASE%>" property="diseaseCode" labelProperty="diseaseCode" />
							  			</logic:present>
							  		</html:select>
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="remark"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
						            &nbsp;<html:textarea name="ICDIncludeExcludeFB" property="diseaseRemark" tabindex="1" rows="3" cols="50" onkeypress="return CheckMaxLength(event,this,500,3)" />
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</his:statusTransactionInProcess>
			
			<his:ButtonToolBarTag>
				<his:statusTransactionInProcess>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitFormOnValidate(validate(),'SAVE')" onkeypress="if(event.keyCode==13) submitFormOnValidate(validate(),'SAVE')">
		    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
		    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="clearTextFields();" onkeypress="if(event.keyCode==13) clearTextFields();">
	    		</his:statusTransactionInProcess>
	    		<his:statusUnsuccessfull>
		    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
	    		</his:statusUnsuccessfull>          	
			</his:ButtonToolBarTag>
			<center><b><his:status/></b></center>
		</his:TransactionContainer>

		<html:hidden name="ICDIncludeExcludeFB" property="hmode"/>
		<html:hidden name="ICDIncludeExcludeFB" property="isActive"/>
		<html:hidden name="ICDIncludeExcludeFB" property="slNo"/>

		<html:hidden name="ICDIncludeExcludeFB" property="mainDisease"/>
		<html:hidden name="ICDIncludeExcludeFB" property="isCommon"/>
		<html:hidden name="ICDIncludeExcludeFB" property="diseaseTypeCode"/>
		<html:hidden name="ICDIncludeExcludeFB" property="diseaseTypeName"/>
	</html:form>
</body>
</html>