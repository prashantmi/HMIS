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


function validateAdd()
{
	var valid=true;
	if(isEmpty(document.forms[0].areaName,"Area Name")
		&& isEmpty(document.forms[0].areaDesc,"Area Description")
		&& comboValidation(document.forms[0].areaTypeCode,"Area Type")
		/* && comboValidation(document.forms[0].buildingCode,"Building")
		&& comboValidation(document.forms[0].blockId,"Block")
		&& comboValidation(document.forms[0].floorId,"Floor")
		&& comboValidation(document.forms[0].roomId,"Room") */
		
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
	if(isEmpty(document.forms[0].areaName,"Area Name")
		&& isEmpty(document.forms[0].areaDesc,"Area Description")
		&& comboValidation(document.forms[0].areaTypeCode,"Area Type")
		/* && comboValidation(document.forms[0].buildingCode,"Building")
		&& comboValidation(document.forms[0].blockId,"Block")
		&& comboValidation(document.forms[0].floorId,"Floor")
		&& comboValidation(document.forms[0].roomId,"Room") */
		 && comboValidation(document.forms[0].isActive,"Is Active")
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



function clearAddForm()
{
	document.getElementsByName("areaName")[0].value="";
	document.getElementsByName("areaDesc")[0].value="";
	document.getElementsByName("areaTypeCode")[0].value="-1";
	/* document.getElementsByName("roomNo")[0].value="";
	document.getElementsByName("buildingCode")[0].value="-1";
	document.getElementsByName("blockId")[0].value="-1";
	document.getElementsByName("floorId")[0].value="-1";
	document.getElementsByName("roomId")[0].value="-1"; */
	document.getElementsByName("empNo")[0].value="-1";
}

function clearModifyForm()
{
	document.getElementsByName("areaName")[0].value="";
	document.getElementsByName("areaDesc")[0].value="";
	document.getElementsByName("areaTypeCode")[0].value="-1";
	/* document.getElementsByName("roomNo")[0].value="";
	document.getElementsByName("buildingCode")[0].value="-1";
	document.getElementsByName("blockId")[0].value="-1";
	document.getElementsByName("floorId")[0].value="-1";
	document.getElementsByName("roomId")[0].value="-1"; */
	document.getElementsByName("isActive")[0].value="-1";
	document.getElementsByName("empNo")[0].value="-1";
}

</script>
<title>Mortuary Area Master</title>

<body>
	<html:form action="/master/MortuaryAreaMaster">
		<his:TransactionContainer>
			
			<logic:equal name="MortuaryAreaMasterFB" property="hmode" value="ADD">
				<his:TitleTag name="Mortuary Area Master >> ADD">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="MortuaryAreaMasterFB" property="hmode" value="MODIFY">
				<his:TitleTag name="Mortuary Area Master >> MODIFY">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="MortuaryAreaMasterFB" property="hmode" value="VIEW">
				<his:TitleTag name="Mortuary Area Master >> VIEW">
				</his:TitleTag>
			</logic:equal>
			<logic:notEqual name="MortuaryAreaMasterFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
	  								<b>
	  									<bean:message key="mortuary"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<b><bean:write name="MortuaryAreaMasterFB" property="mortuaryName"/></b> 
									<html:hidden name="MortuaryAreaMasterFB" property="mortuaryName"/>
								<html:hidden name="MortuaryAreaMasterFB" property="mortuaryCode"/>
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
	  									<bean:message key="areaName"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MortuaryAreaMasterFB" property="areaName" maxlength="50" size="30" onkeypress="return validateAlphaNumericOnly(event,this)">
								</html:text>
								<html:hidden name="MortuaryAreaMasterFB" property="slNo"/>
								<html:hidden name="MortuaryAreaMasterFB" property="areaCode"/>
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
	  									<bean:message key="areaDesc"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MortuaryAreaMasterFB" property="areaDesc" maxlength="100" size="30" onkeypress="return validateAlphaNumericOnly(event,this)">
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
	  									<bean:message key="areaType"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryAreaMasterFB" property="areaTypeCode" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.MORTUARY_AREA_TYPE_LIST %>">
										<html:options collection="<%=MortuaryConfig.MORTUARY_AREA_TYPE_LIST %>" labelProperty="label" property="value"/>
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
								<html:text name="MortuaryAreaMasterFB" property="roomNo" maxlength="20" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
								
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
	  									<bean:message key="building"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryAreaMasterFB" property="buildingCode" onchange="submitForm('BLOCK_LIST')">
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
								<html:select name="MortuaryAreaMasterFB" property="blockId" onchange="submitForm('FLOOR_LIST')">
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
								<html:select name="MortuaryAreaMasterFB" property="floorId" onchange="submitForm('ROOM_LIST')">
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
								<html:select name="MortuaryAreaMasterFB" property="roomId">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR %>" >
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR %>" labelProperty="label" property="value"/>
								    </logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				 --%>
			 <logic:notEqual name="MortuaryAreaMasterFB" property="hmode" value="ADD">
			 <logic:notEqual name="MortuaryAreaMasterFB" property="hmode" value="SAVE">
			 <logic:notEqual name="MortuaryAreaMasterFB" property="hmode" value="VIEW">
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
				     &nbsp;<html:select name="MortuaryAreaMasterFB" property="isActive"  style="width:160;">
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
				<logic:equal name="MortuaryAreaMasterFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
	  								<b>
	  									<bean:message key="mortuary"/>
	  									<bean:message key="name"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<b><bean:write name="MortuaryAreaMasterFB" property="mortuaryName"/></b> 
									<html:hidden name="MortuaryAreaMasterFB" property="mortuaryName"/>
								<html:hidden name="MortuaryAreaMasterFB" property="mortuaryCode"/>
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
	  									<bean:message key="areaName"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MortuaryAreaMasterFB" property="areaName" maxlength="50" disabled="true" size="30" >
								</html:text>
								<html:hidden name="MortuaryAreaMasterFB" property="slNo"/>
								<html:hidden name="MortuaryAreaMasterFB" property="areaCode"/>
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
	  									<bean:message key="areaDesc"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MortuaryAreaMasterFB" property="areaDesc" maxlength="100" disabled="true" size="30" >
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
	  									<bean:message key="areaType"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryAreaMasterFB" property="areaTypeCode" disabled="true" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.MORTUARY_AREA_TYPE_LIST %>">
										<html:options collection="<%=MortuaryConfig.MORTUARY_AREA_TYPE_LIST %>" labelProperty="label" property="value"/>
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
								<html:text name="MortuaryAreaMasterFB" property="roomNo" maxlength="20" disabled="true" ></html:text>
								
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
	  									<bean:message key="building"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="MortuaryAreaMasterFB" property="buildingCode"  disabled="true">
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
								<html:select name="MortuaryAreaMasterFB" property="blockId" disabled="true" >
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
								<html:select name="MortuaryAreaMasterFB" property="floorId" disabled="true" >
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
								<html:select name="MortuaryAreaMasterFB" property="roomId" disabled="true">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR %>" >
										<html:options collection="<%=MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR %>" labelProperty="label" property="value"/>
								    </logic:present>
								</html:select>
							</div>
						</td>
					</tr> --%>
				</table>	
			</his:ContentTag>
			</logic:equal>
			<his:ButtonToolBarTag>
			<logic:equal name="MortuaryAreaMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="if(validateAdd()) submitForm21('SAVE')" onkeypress="if(event.keyCode==13)if(validateAdd()) submitForm21('SAVE')">
			</logic:equal>
			
			<logic:equal name="MortuaryAreaMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateModify() && submitForm21('MODIFYSAVE')" onkeypress="if(event.keyCode==13)validateModify() && submitForm21('MODIFYSAVE')">
			</logic:equal>
			
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
			
			<logic:equal name="MortuaryAreaMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" clearAddForm()" onkeypress="if(event.keyCode==13)  clearAddForm();">
			</logic:equal>
			<logic:equal name="MortuaryAreaMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" clearModifyForm()" onkeypress="if(event.keyCode==13)  clearModifyForm();">
			</logic:equal>
				
			</his:ButtonToolBarTag>
			
			
			<html:hidden name="MortuaryAreaMasterFB" property="hmode"/>
			<html:hidden name="MortuaryAreaMasterFB" property="tempMode"/>
			
		</his:TransactionContainer>
		
		<his:status/>
	</html:form>
</body>
</html>			