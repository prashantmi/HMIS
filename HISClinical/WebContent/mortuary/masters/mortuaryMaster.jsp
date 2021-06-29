<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
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

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />


<script type="text/javascript">

window.onload=function()
{
	document.getElementsByName("empNo")[0].value=document.getElementsByName("hod")[0].value;
}
function validateAdd()
{
	var valid=true;
	if(isEmpty(document.forms[0].mortuaryName,"Mortuary Name")
		&& isEmpty(document.forms[0].mortuaryShortName,"Mortuary Short Name")
		&& comboValidation(document.forms[0].autopsyType,"Autopsy Type")
		&& comboValidation(document.forms[0].autopsyFor,"Autopsy For")
		&& comboValidation(document.forms[0].departmentCode,"Department Name")
		&& comboValidation(document.forms[0].empNo,"Mortuary InCharge")
		/* && comboValidation(document.forms[0].buildingCode,"Building")
		&& comboValidation(document.forms[0].blockId,"Block")
		&& comboValidation(document.forms[0].floorId,"Floor")
		&& comboValidation(document.forms[0].roomId,"Room") */
		&& validateDate()
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

function validateModify()
{
	var valid=true;
	if(isEmpty(document.forms[0].mortuaryName,"Mortuary Name")
		&& isEmpty(document.forms[0].mortuaryShortName,"Mortuary Short Name")
		&& comboValidation(document.forms[0].autopsyType,"Autopsy Type")
		&& comboValidation(document.forms[0].autopsyFor,"Autopsy For")
		&& comboValidation(document.forms[0].departmentCode,"Department Name")
		&& comboValidation(document.forms[0].empNo,"Mortuary InCharge")
		/* && comboValidation(document.forms[0].buildingCode,"Building")
		&& comboValidation(document.forms[0].blockId,"Block")
		&& comboValidation(document.forms[0].floorId,"Floor")
		&& comboValidation(document.forms[0].roomId,"Room") */
		&& validateDate() && comboValidation(document.forms[0].isActive,"Is Active")
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


function validateDate()
{
	effectiveFrom = document.getElementsByName("effectiveFrom")[0];
    sysDate = document.getElementsByName("sysDate")[0];

	if(compareDateCall(sysDate,effectiveFrom,2,"Current Date","Effective From")) 
	{
		if(!document.getElementsByName('effectiveTo')[0].value=="")
	   	{
		   	if(!compareDateCall(effectiveFrom,effectiveTo,2,"Effective From Date","Effective To Date"))
			{
			   valid= false;
			   return valid
			}
		}
		return true;
	}
	else
	{
		return false;
	}
	
}
function clearAddForm()
{
	document.getElementsByName("mortuaryName")[0].value="";
	document.getElementsByName("mortuaryShortName")[0].value="";
	document.getElementsByName("autopsyType")[0].value="-1";
	document.getElementsByName("autopsyFor")[0].value="-1";
	document.getElementsByName("departmentCode")[0].value="-1";
	document.getElementsByName("empNo")[0].value="-1";
	//document.getElementsByName("roomNo")[0].value="";
	document.getElementsByName("locationDesc")[0].value="";
	//document.getElementsByName("buildingCode")[0].value="-1";
	//document.getElementsByName("blockId")[0].value="-1";
	//document.getElementsByName("floorId")[0].value="-1";
	//document.getElementsByName("roomId")[0].value="-1";
	document.getElementsByName("mortuaryType")[0].checked=true;
	document.getElementsByName("effectiveFrom")[0].value=document.getElementsByName("sysDate")[0].value;
	document.getElementsByName("effectiveTo")[0].value=document.getElementsByName("sysDate")[0].value;
	
}

function clearModifyForm()
{
	document.getElementsByName("mortuaryName")[0].value="";
	document.getElementsByName("mortuaryShortName")[0].value="";
	document.getElementsByName("autopsyType")[0].value="-1";
	document.getElementsByName("autopsyFor")[0].value="-1";
	document.getElementsByName("departmentCode")[0].value="-1";
	//document.getElementsByName("roomNo")[0].value="";
	document.getElementsByName("empNo")[0].value="-1";
	document.getElementsByName("locationDesc")[0].value="";
	//document.getElementsByName("buildingCode")[0].value="-1";
	//document.getElementsByName("blockId")[0].value="-1";
	//document.getElementsByName("floorId")[0].value="-1";
	//document.getElementsByName("roomId")[0].value="-1";
	document.getElementsByName("mortuaryType")[0].checked=true;
	document.getElementsByName("effectiveFrom")[0].value=document.getElementsByName("sysDate")[0].value;
	document.getElementsByName("effectiveTo")[0].value=document.getElementsByName("sysDate")[0].value;
	document.getElementsByName("isActive")[0].value="-1";
}


</script>
<title>Mortuary Master</title>

<body>
	<html:form action="/master/MortuaryMaster">
		<his:TransactionContainer>
			<bean:define name="MortuaryMasterFB" property="sysDate" id="sysDate" type="java.lang.String" />
			<%
				if(sysDate==null||sysDate.equalsIgnoreCase(""))
				{
					sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
				}
			%>
			<%boolean varReadOnly=false;%>
			<logic:equal name="MortuaryMasterFB" property="hmode" value="ADD">
				<his:TitleTag name="Mortuary Master >> ADD">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="MortuaryMasterFB" property="hmode" value="MODIFY">
				<his:TitleTag name="Mortuary Master >> MODIFY">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="MortuaryMasterFB" property="hmode" value="VIEW">
				<his:TitleTag name="Mortuary Master >> VIEW">
				</his:TitleTag>
			</logic:equal>
			<logic:notEqual name="MortuaryMasterFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="mortuary"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MortuaryMasterFB" property="mortuaryName" maxlength="50" size="30" onkeypress="return validateAlphaNumericOnly(event,this)" >
								</html:text>
								<html:hidden name="MortuaryMasterFB" property="slNo"/>
								<html:hidden name="MortuaryMasterFB" property="mortuaryCode"/>
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
	  									<bean:message key="mortuary"/>
	  									<bean:message key="short"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MortuaryMasterFB" property="mortuaryShortName" maxlength="3" size="5" onkeypress="return validateAlphaNumericOnly(event,this)" >
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
	  									<bean:message key="mortuary"/>
	  									<bean:message key="type"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="normal"/>
	  								</b>
								</font>
								<html:radio name="MortuaryMasterFB" property="mortuaryType" value="<%=MortuaryConfig.MORTUARY_TYPE_NORMAL %>"></html:radio>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="emergency"/>
	  								</b>
								</font>
								<html:radio name="MortuaryMasterFB" property="mortuaryType" value="<%=MortuaryConfig.MORTUARY_TYPE_EMERGENCY %>"></html:radio>
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
	  									<bean:message key="autopsy"/>
	  									<bean:message key="type"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="autopsyType">
									<html:option value="-1">Select Value</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_TYPE_NO_AUTOPSY %>">No Autopsy Facility Available</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_TYPE_NORMAL_AUTOPSY %>">Normal Case Autopsy</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_TYPE_MLC_AUTOPSY %>">MLC Case Autopsy</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_TYPE_NORMAL_N_MLC_AUTOPSY %>">Normal/MLC Case Autopsy</html:option>
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
	  									<bean:message key="autopsy"/>
	  									<bean:message key="for"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="autopsyFor">
									<html:option value="-1">Select Value</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_FOR_ALL_PATIENT_INHOUSE %>">All Patient(In House & Brought Dead)</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_FOR_INHOUSE_DEATH_ONLY %>">In House Death</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_FOR_BROUGHT_DEATH_ONLY %>">Brought Death</html:option>
									
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
	  									<bean:message key="department"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="departmentCode" onchange="submitForm21('EMP_LIST');">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_DEPARTMENT %>">
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_DEPARTMENT %>" labelProperty="label" property="value"/>
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
	  									<bean:message key="mortuary"/>
	  									<bean:message key="incharge"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="empNo">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT %>">
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT %>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<%-- <tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="room"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MortuaryMasterFB" property="roomNo" maxlength="20" ></html:text>
								
							</div>
						</td>
					</tr> --%>
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
								<html:textarea name="MortuaryMasterFB" property="locationDesc" rows="1" cols="35" onkeypress="return validateAlphaNumericOnly(event,this); return CheckMaxLength(event,this,100,1)" ></html:textarea>
								
							</div>
						</td>
					</tr>
					
					<tr>
						<%-- <td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="building"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="buildingCode" onchange="submitForm('BLOCK_LIST')">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_BUILDING_BLOCK %>">
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_BUILDING_BLOCK %>" labelProperty="label" property="value"/>
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
	  									<bean:message key="block"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="blockId" onchange="submitForm('FLOOR_LIST')">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_BLOCK_BASED_ON_BUILDING %>" >
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_BLOCK_BASED_ON_BUILDING %>" labelProperty="label" property="value"/>
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
	  									<bean:message key="floor"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="floorId" onchange="submitForm('ROOM_LIST')">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_FLOOR_BASED_ON_BLOCK %>" >
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_FLOOR_BASED_ON_BLOCK %>" labelProperty="label" property="value"/>
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
	  									<bean:message key="room"/>
	  								</b>
								</font>
							</div>
						</td> 
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="roomId">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR %>" >
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR %>" labelProperty="label" property="value"/>
								    </logic:present>
								</html:select>
							</div>
						</td>--%>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="effectiveFrom"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<his:date name='effectiveFrom' dateFormate="%d-%b-%Y" value="<%=sysDate%>" />
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="effectiveTo"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<his:date name='effectiveTo' dateFormate="%d-%b-%Y" value="<%=sysDate%>" />
							</div>
						</td>
					</tr>
			 <logic:notEqual name="MortuaryMasterFB" property="hmode" value="ADD">
			 <logic:notEqual name="MortuaryMasterFB" property="hmode" value="SAVE">
			 <logic:notEqual name="MortuaryMasterFB" property="hmode" value="VIEW">
			      <tr>
			        <td width="50%" class="tdfonthead">
			         <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<b><bean:message key="isActive"/>&nbsp;</b>
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     &nbsp;<html:select name="MortuaryMasterFB" property="isActive"  style="width:160;">
						    <html:option value="-1">Select Value</html:option>
							<html:option value="1">Active</html:option>
							<html:option value="2">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
			   </logic:notEqual>
			   </logic:notEqual>
			   </logic:notEqual> 	
				</table>	
			</his:ContentTag>
		</logic:notEqual>
				<logic:equal name="MortuaryMasterFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="mortuary"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MortuaryMasterFB" property="mortuaryName" disabled="true" maxlength="50" size="30"  >
								</html:text>
								<html:hidden name="MortuaryMasterFB" property="slNo"/>
								<html:hidden name="MortuaryMasterFB" property="mortuaryCode"/>
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
	  									<bean:message key="mortuary"/>
	  									<bean:message key="short"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MortuaryMasterFB" property="mortuaryShortName" disabled="true" maxlength="3" size="5"  >
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
	  									<bean:message key="mortuary"/>
	  									<bean:message key="type"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="normal"/>
	  								</b>
								</font>
								<html:radio name="MortuaryMasterFB" property="mortuaryType" disabled="true" value="<%=MortuaryConfig.MORTUARY_TYPE_NORMAL %>"></html:radio>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="emergency"/>
	  								</b>
								</font>
								<html:radio name="MortuaryMasterFB" property="mortuaryType" disabled="true" value="<%=MortuaryConfig.MORTUARY_TYPE_EMERGENCY %>"></html:radio>
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
	  									<bean:message key="autopsy"/>
	  									<bean:message key="type"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="autopsyType" disabled="true">
									<html:option value="-1">Select Value</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_TYPE_NO_AUTOPSY %>">No Autopsy Facility Available</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_TYPE_NORMAL_AUTOPSY %>">Normal Case Autopsy</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_TYPE_MLC_AUTOPSY %>">MLC Case Autopsy</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_TYPE_NORMAL_N_MLC_AUTOPSY %>">Normal/MLC Case Autopsy</html:option>
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
	  									<bean:message key="autopsy"/>
	  									<bean:message key="for"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="autopsyFor" disabled="true">
									<html:option value="-1">Select Value</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_FOR_ALL_PATIENT_INHOUSE %>">All Patient(In House & Brought Dead)</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_FOR_INHOUSE_DEATH_ONLY %>">In House Death</html:option>
									<html:option value="<%=MortuaryConfig.AUTOPSY_FOR_BROUGHT_DEATH_ONLY %>">Brought Death</html:option>
									
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
	  									<bean:message key="department"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="departmentCode" disabled="true" onchange="submitForm21('EMP_LIST');">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_DEPARTMENT %>">
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_DEPARTMENT %>" labelProperty="label" property="value"/>
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
	  									<bean:message key="mortuary"/>
	  									<bean:message key="incharge"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="empNo" disabled="true">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT %>">
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT %>" labelProperty="label" property="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<%-- <tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="room"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MortuaryMasterFB" property="roomNo" maxlength="20" disabled="true" onkeypress="return validateAlphaNumericOnly(event,this)" ></html:text>
								
							</div>
						</td>
					</tr> --%>
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
								<html:textarea name="MortuaryMasterFB" property="locationDesc" disabled="true" rows="1" cols="35" onkeypress="return validateAlphabetsWithDotsOnly(event,this); return CheckMaxLength(event,this,100,1)"   ></html:textarea>
								
							</div>
						</td>
					</tr>
					
					<%-- <tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="building"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="buildingCode" disabled="true" onchange="submitForm('BLOCK_LIST')">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_BUILDING_BLOCK %>">
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_BUILDING_BLOCK %>" labelProperty="label" property="value"/>
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
	  									<bean:message key="block"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="blockId" disabled="true" onchange="submitForm('FLOOR_LIST')">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_BLOCK_BASED_ON_BUILDING %>" >
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_BLOCK_BASED_ON_BUILDING %>" labelProperty="label" property="value"/>
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
	  									<bean:message key="floor"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="floorId" disabled="true" onchange="submitForm('ROOM_LIST')">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_FLOOR_BASED_ON_BLOCK %>" >
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_FLOOR_BASED_ON_BLOCK %>" labelProperty="label" property="value"/>
								    </logic:present>
								</html:select>
							</div>
						</td>
					</tr> --%>
					<%-- <tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="room"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryMasterFB" property="roomId" disabled="true">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR %>" >
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR %>" labelProperty="label" property="value"/>
								    </logic:present>
								</html:select>
							</div>
						</td>
					</tr> --%>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="effectiveFrom"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
							
								<html:text name="MortuaryMasterFB" property="effectiveFrom" disabled="true" size="10"/>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="effectiveTo"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MortuaryMasterFB" property="effectiveTo" disabled="true" size="10"/>
							</div>
						</td>
					</tr>
				</table>	
			</his:ContentTag>
			</logic:equal>
			<his:ButtonToolBarTag>
			<logic:equal name="MortuaryMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="if(validateAdd()) submitForm21('SAVE')" onkeypress="if(event.keyCode==13)if(validateAdd()) submitForm21('SAVE')">
			</logic:equal>
			
			<logic:equal name="MortuaryMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateModify() && submitForm21('MODIFYSAVE')" onkeypress="if(event.keyCode==13)validateModify() && submitForm21('MODIFYSAVE')">
			</logic:equal>
			
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
			
			<logic:equal name="MortuaryMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" clearAddForm()" onkeypress="if(event.keyCode==13)  clearAddForm();">
			</logic:equal>
			<logic:equal name="MortuaryMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" clearModifyForm()" onkeypress="if(event.keyCode==13)  clearModifyForm();">
			</logic:equal>
				
			</his:ButtonToolBarTag>
			
			
			<html:hidden name="MortuaryMasterFB" property="hmode"/>
			<html:hidden name="MortuaryMasterFB" property="tempMode"/>
			<html:hidden name="MortuaryMasterFB" property="sysDate" value="<%=sysDate %>"/>
			<html:hidden name="MortuaryMasterFB" property="hod"/>
			<html:hidden name="MortuaryMasterFB" property="departmentCode"/>
			  
			
		</his:TransactionContainer>
		
		<his:status/>
	</html:form>
</body>
</html>			