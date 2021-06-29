
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="opd.OpdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/opd/js/chartParameterMappingMst.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script>
function getParameter()
{
/* var a = document.getElementsByName("para")[0].value;
	alert(a); */
}


</script>
<body>
<his:TransactionContainer>
<html:form action="master/chartParameterMappingACTION" > 
	<his:statusNew>

	<his:TitleTag name="Chart Parameter Mapping">
	</his:TitleTag>

	<his:ContentTag>
		<table width="100%" border="0" cellspacing="2" cellpadding="0">
			<tr>
				<td width="50%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="chart"/>&nbsp;
						</font>
					</div>
				</td>
				<td width="50%" class="tdfont">
					<div align="left">
						&nbsp;<html:select name="chartParameterMappingFB" property="chartId" tabindex="1" styleClass="regcbo"  onchange="getPara(this)" style="width:150;" >
							<html:option value="-1">Select Value</html:option>
							<html:options collection="<%=OpdConfig.CHART_NAME_LIST %>" property="chartId" labelProperty="chartName" />
						</html:select>
					</div>
				</td>
			</tr>
		</table>
		
		<his:statusList>
		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
			<!-- Clinical Parameters -->
			<tr>
				<td width="35%" class="tdfonthead" colspan="4">
					Clinical Parameters
				</td>
			</tr>
			<tr>
				<td width="40%"  class="tdfont">
					<div align="right">
						<html:select name="chartParameterMappingFB" property="para" tabindex="1" multiple="true" size="6" onclick="getParameter()">
							<logic:notEqual name="chartParameterMappingFB" property="hmode"  value="NEW">
								<logic:present name="<%=OpdConfig.UNADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_CLINICAL%>">
									<html:options  collection="<%=OpdConfig.UNADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_CLINICAL%>" property="value" labelProperty="label" />
								</logic:present>
							</logic:notEqual>
						</html:select>
					</div>
				</td>
				<td width="20%"  class="tdfont">
					<div align="center">
						<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveSingle(document.getElementsByName("para")[0],document.getElementsByName("selectedPara")[0]);'/>
						<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveAll(document.getElementsByName("para")[0],document.getElementsByName("selectedPara")[0]);' />
						<br><br>
						<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveSingle(document.getElementsByName("selectedPara")[0],document.getElementsByName("para")[0]);'/>
						<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveAll(document.getElementsByName("selectedPara")[0],document.getElementsByName("para")[0]);'/>
					</div>
				</td>
				<td class="tdfonthead" valign="middle">
					<div align="left">
						<html:select name="chartParameterMappingFB" property="selectedPara" tabindex="1" multiple="true" size="6">
							<logic:notEqual name="chartParameterMappingFB" property="hmode"  value="NEW">
								<logic:present name="<%=OpdConfig.ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_CLINICAL%>">
									<html:options  collection="<%=OpdConfig.ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_CLINICAL%>" property="paraId" labelProperty="paraName" />
								</logic:present>
							</logic:notEqual>
						</html:select>
					</div>
				</td>
				<td class="tdfonthead" valign="middle">
					<div align="center">
						&nbsp;<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP(document.getElementsByName("selectedPara")[0]);' />
						<br>
						<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN(document.getElementsByName("selectedPara")[0]);' />
					</div>
				</td>
			</tr>
			<!-- Investigation Parameters -->
			<tr>
				<td width="35%" class="tdfonthead" colspan="4">
					Investigation Parameters
				</td>
			</tr>
			<tr>
				<td width="40%"  class="tdfont">
					<div align="right">
						<html:select name="chartParameterMappingFB" property="arrInvPara" tabindex="1" multiple="true" size="6">
							<logic:notEqual name="chartParameterMappingFB" property="hmode"  value="NEW">
								<logic:present name="<%=OpdConfig.UNADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INVESTIGATION%>">
									<html:options  collection="<%=OpdConfig.UNADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INVESTIGATION%>" property="value" labelProperty="label" />
								</logic:present>
							</logic:notEqual>
						</html:select>
					</div>
				</td>
				<td width="20%"  class="tdfont">
					<div align="center">
						<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveSingle(document.getElementsByName("arrInvPara")[0],document.getElementsByName("arrSelectedInvPara")[0]);'/>
						<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveAll(document.getElementsByName("arrInvPara")[0],document.getElementsByName("arrSelectedInvPara")[0]);' />
						<br><br>
						<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveSingle(document.getElementsByName("arrSelectedInvPara")[0],document.getElementsByName("arrInvPara")[0]);'/>
						<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveAll(document.getElementsByName("arrSelectedInvPara")[0],document.getElementsByName("arrInvPara")[0]);'/>
					</div>
				</td>
				<td class="tdfonthead" valign="middle">
					<div align="left">
						<html:select name="chartParameterMappingFB" property="arrSelectedInvPara" tabindex="1" multiple="true" size="6">
							<logic:notEqual name="chartParameterMappingFB" property="hmode"  value="NEW">
								<logic:present name="<%=OpdConfig.ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INVESTIGATION%>">
									<html:options  collection="<%=OpdConfig.ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INVESTIGATION%>" property="paraId" labelProperty="paraName" />
								</logic:present>
							</logic:notEqual>
						</html:select>
					</div>
				</td>
				<td class="tdfonthead" valign="middle">
					<div align="center">
						&nbsp;<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP(document.getElementsByName("arrSelectedInvPara")[0]);' />
						<br>
						<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN(document.getElementsByName("arrSelectedInvPara")[0]);' />
					</div>
				</td>
			</tr>
			<!-- Intake Output Parameters -->
			<logic:notEqual name="chartParameterMappingFB" property="chartCategory" value="<%=OpdConfig.CHART_CATEGORY_OPD%>">
			<tr>
				<td width="35%" class="tdfonthead" colspan="4">
					Intake Output Parameters
				</td>
			</tr>
			<tr>
				<td width="40%"  class="tdfont">
					<div align="right">
						<html:select name="chartParameterMappingFB" property="arrInOutPara" tabindex="1" multiple="true" size="6">
							<logic:notEqual name="chartParameterMappingFB" property="hmode"  value="NEW">
								<logic:present name="<%=OpdConfig.UNADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INTAKE_OUTPUT%>">
									<html:options  collection="<%=OpdConfig.UNADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INTAKE_OUTPUT%>" property="value" labelProperty="label" />
								</logic:present>
							</logic:notEqual>
						</html:select>
					</div>
				</td>
				<td width="20%"  class="tdfont">
					<div align="center">
						<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveSingle(document.getElementsByName("arrInOutPara")[0],document.getElementsByName("arrSelectedInOutPara")[0]);'/>
						<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveAll(document.getElementsByName("arrInOutPara")[0],document.getElementsByName("arrSelectedInOutPara")[0]);' />
						<br><br>
						<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveSingle(document.getElementsByName("arrSelectedInOutPara")[0],document.getElementsByName("arrInOutPara")[0]);'/>
						<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveAll(document.getElementsByName("arrSelectedInOutPara")[0],document.getElementsByName("arrInOutPara")[0]);'/>
					</div>
				</td>
				<td class="tdfonthead" valign="middle">
					<div align="left">
						<html:select name="chartParameterMappingFB" property="arrSelectedInOutPara" tabindex="1" multiple="true" size="6">
							<logic:notEqual name="chartParameterMappingFB" property="hmode"  value="NEW">
								<logic:present name="<%=OpdConfig.ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INTAKE_OUTPUT%>">
									<html:options  collection="<%=OpdConfig.ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INTAKE_OUTPUT%>" property="paraId" labelProperty="paraName" />
								</logic:present>
							</logic:notEqual>
						</html:select>
					</div>
				</td>
				<td class="tdfonthead" valign="middle">
					<div align="center">
						&nbsp;<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP(document.getElementsByName("arrSelectedInOutPara")[0]);' />
						<br>
						<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN(document.getElementsByName("arrSelectedInOutPara")[0]);' />
					</div>
				</td>
			</tr>
			</logic:notEqual>
		</table>
	</his:statusList>
	</his:ContentTag>
	</his:statusNew>
	
	<his:ButtonToolBarTag>
		<his:statusList>
			<logic:equal name="chartParameterMappingFB" property="hmode" value="GETPARA">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" tabindex="1" onclick="finalSubmit('SAVE')">
			</logic:equal>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
		</his:statusList>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
	</his:ButtonToolBarTag>

	<html:hidden name="chartParameterMappingFB" property="hmode"/>
	<html:hidden name="chartParameterMappingFB" property="chart"/>  
	<html:hidden name="chartParameterMappingFB" property="fetchedList"/> 
	<html:hidden name="chartParameterMappingFB" property="displayOrder"/> 
 	<%--  <html:hidden name="chartParameterMappingFB" property="selectedPara"/>
	<html:hidden name="chartParameterMappingFB" property="arrSelectedInvPara"/>
	<html:hidden name="chartParameterMappingFB" property="arrSelectedInOutPara"/>  --%>
</html:form>

<center><b><his:status/></b></center>

</his:TransactionContainer>
</body>
</html>