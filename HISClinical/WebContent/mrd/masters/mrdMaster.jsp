<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.MrdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>    
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<script type="text/javascript">

window.onload=function()
{
	//document.getElementsByName("empNo")[0].value=document.getElementsByName("hod")[0].value;
	
}




function validateAddForm(mode)
{
	if(document.getElementsByName("mrdName")[0].value=="")
	{
		alert("Please Enter MRD Name");
		document.getElementsByName("mrdName")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("mrdShortName")[0].value=="")
	{
		alert("Please Enter MRD Short Name");
		document.getElementsByName("mrdShortName")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("mrdType")[0].value=="" && document.getElementsByName("mrdType")[0].value=="")
	{
		alert("Please Select MRD Type");
		document.getElementsByName("mrdType")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("departmentCode")[0].value=="-1")
	{
		alert("Please Select Department Code");
		document.getElementsByName("departmentCode")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("empNo")[0].value=="-1")
	{
		alert("Please Select MRD Incharge");
		document.getElementsByName("empNo")[0].focus();
		return false;
	}
	
	/*if(document.getElementsByName("buildingCode")[0].value=="-1")
	{
		alert("Please Select Building");
		document.getElementsByName("buildingCode")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("blockId")[0].value=="-1")
	{
		alert("Please Select Block");
		document.getElementsByName("blockId")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("floorId")[0].value=="-1")
	{
		alert("Please Select Floor");
		document.getElementsByName("floorId")[0].focus();
		return false;
	}*/
	/*
	if(document.getElementsByName("roomId")[0].value=="-1")
	{
		alert("Please Select Room");
		document.getElementsByName("roomId")[0].focus();
		return false;
	}
	*/
	
	submitForm21(mode);
}

</script>
<title>MRD Master</title>

<body>
	<html:form action="/master/mrdMater">
		<his:TransactionContainer>
			<logic:equal name="MrdMasterFB" property="hmode" value="ADD">
				<his:TitleTag name="MRD Master">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="MrdMasterFB" property="hmode" value="MODIFY">
				<his:TitleTag name="MRD Master">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="MrdMasterFB" property="hmode" value="VIEW">
				<his:TitleTag name="MRD Master">
				</his:TitleTag>
			</logic:equal>
			<logic:notEqual name="MrdMasterFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="mrd"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MrdMasterFB" property="mrdName" maxlength="50" size="30" >
								</html:text>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="mrd"/>
	  									<bean:message key="short"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MrdMasterFB" property="mrdShortName" maxlength="3" size="5" onkeypress="return validateAlphaOnly(this,event)">
								</html:text>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="mrd"/>
	  									<bean:message key="type"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="mainMrd"/>
	  								</b>
								</font>
								
								<logic:equal value="0" name="MrdMasterFB" property="mainMrdFlag">
									<html:radio name="MrdMasterFB" property="mrdType" value="<%=MrdConfig.MRD_TYPE_MAIN_MRD %>" ></html:radio>
								</logic:equal>
								
								<logic:notEqual value="0" name="MrdMasterFB" property="mainMrdFlag">
									<html:radio name="MrdMasterFB" property="mrdType" value="<%=MrdConfig.MRD_TYPE_MAIN_MRD %>" disabled="true"></html:radio>
								</logic:notEqual>
								
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="subMrd"/>
	  								</b>
								</font>
								<html:radio name="MrdMasterFB" property="mrdType" value="<%=MrdConfig.MRD_TYPE_SUB_MRD %>"></html:radio>
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="department"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MrdMasterFB" property="departmentCode" onchange="submitForm21('EMP_LIST');">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.ESSENTIAL_ALL_DEPARTMENT_LIST %>">
										<html:options collection="<%=MrdConfig.ESSENTIAL_ALL_DEPARTMENT_LIST %>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="mrd"/>
	  									<bean:message key="incharge"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MrdMasterFB" property="empNo">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT %>">
										<html:options collection="<%=MrdConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT %>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="locationDesc"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:textarea name="MrdMasterFB" property="locationDesc" rows="1" cols="35" onkeypress="return (validateTextArea(event,this,'100') && validateAlphaNumericOnly(event,this))"></html:textarea>
								
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  								<b>
	  									<bean:message key="building"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MrdMasterFB" property="buildingCode" onchange="submitForm21('BLOCK_LIST')">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.BUILDING_LIST %>">
										<html:options collection="<%=MrdConfig.BUILDING_LIST %>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  								<b>
	  									<bean:message key="block"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MrdMasterFB" property="blockId" onchange="submitForm21('FLOOR_LIST')">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.BLOCK_LIST %>">
										<html:options collection="<%=MrdConfig.BLOCK_LIST%>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  								<b>
	  									<bean:message key="floor"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MrdMasterFB" property="floorId" onchange="submitForm21('ROOM_LIST')">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.FLOOR_LIST%>">
										<html:options collection="<%=MrdConfig.FLOOR_LIST%>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="room"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MrdMasterFB" property="roomId">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MrdConfig.ROOM_ID_LIST%>">
										<html:options collection="<%=MrdConfig.ROOM_ID_LIST%>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					
			 	
				</table>	
			</his:ContentTag>
			</logic:notEqual>
			
			
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateAddForm('SAVE')" onkeypress="if(event.keyCode==13) validateAddForm('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" submitForm21('ADD')" onkeypress="if(event.keyCode==13)  submitForm21('ADD');">
			</his:ButtonToolBarTag>
			
			
			<html:hidden name="MrdMasterFB" property="hmode"/>
			<html:hidden name="MrdMasterFB" property="hod"/>
			<html:hidden name="MrdMasterFB" property="mainMrdFlag"/>
			<html:hidden name="MrdMasterFB" property="tempMode"/>
			<html:hidden name="MrdMasterFB" property="sereialNo"/>
			<html:hidden name="MrdMasterFB" property="mrdCode"/>
			
		</his:TransactionContainer>
		
		<his:status/>
	</html:form>
</body>
</html>			