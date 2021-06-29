
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="opd.OpdConfig"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<title></title>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>    

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:css src="/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script>


</script>

<body>
<html:form action="/master/DeptUnitIcdMapping">
<html:hidden name="DeptUnitIcdMappingFB" property="hmode"/>
<html:hidden name="DeptUnitIcdMappingFB" property="selectedUnit"/>
	<his:TransactionContainer>
		<his:ContentTag>
		<logic:notEqual value="VIEW" name="DeptUnitIcdMappingFB" property="hmode">
			<logic:notEqual value="MODIFY" name="DeptUnitIcdMappingFB" property="hmode">
				<his:TitleTag name="Department Unit Icd Mapping>>Add ">
				</his:TitleTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="unit"/>
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<div align="left">
							<html:select name="DeptUnitIcdMappingFB" property="selectedUnit" multiple="true" style="width:200px;" size="6">
								<logic:present name="<%=OpdConfig.MAPPED_UNIT_LIST%>" >
								<html:options collection="<%=OpdConfig.MAPPED_UNIT_LIST %>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
							</div>
						</td>
						<td width="20%"  class="tdfonthead">
						</td>
						</tr>
						</table>
				
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="group"/>
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<div align="left">
								<html:select name="DeptUnitIcdMappingFB" property="icdGroupCode"  style="width:200px;" onchange="getSubGroup(this)">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP%>" property="icdGroupCode" labelProperty="icdGroup" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="20%" class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="subGroup"/>
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<div align="left">
								<html:select name="DeptUnitIcdMappingFB" property="icdSubgroupCode" style="width:200px;" onchange="getDisease(this)" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE%>" property="icdSubgroupCode" labelProperty="icdSubgroup" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="disease"/>
									</b>
								</font>
							</div>
						</td>
						<td width="20%"  class="tdfonthead"></td>
						
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="selectedDisease"/>
									</b>
								</font>
							</div>
						</td>
						<td width="10%"  class="tdfonthead"></td>
					</tr>
						
					<tr>
						<td width="30%"  class="tdfont">
							<div align="center"  >
								<html:select name="DeptUnitIcdMappingFB" property="diseaseCode"  multiple="true" size="6"  >
										<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE%>" >
										<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE%>" property="diseaseCode" labelProperty="disease" />
										</logic:present>
								</html:select>
							</div>
						</td>
						
						<td width="30%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="25%"  class="tdfont">
							<div align="center">
								<html:select name="DeptUnitIcdMappingFB" property="selectedDisease" multiple="true" size="6">
									</html:select>
							</div>
						</td>
						<td width="5%"  class="tdfont">
						<div align="center">
						<logic:present name="<%=OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT%>" >
									<img class="button" src='<his:path src="/hisglobal/images/plus.gif"/>' style="cursor:pointer" onclick="openPopup('/HISClinical/opd/master/DeptIcdMaster.cnt?hmode=ADD&departmentCode='+document.forms[0].departmentCode.value+'&valueChoice='+document.forms[0].valueChoice.value+'&departmentUnitCode='+document.forms[0].departmentUnitCode.value ,event,300,800);" >
									</logic:present>
						<div></td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead"></td>
						<td width="20%" class="tdfonthead"></td>
						<td width="35%" class="tdfonthead"></td>
						<td width="10%" class="tdfonthead"></td>
					</tr>
					</table>
					</logic:notEqual>
					</logic:notEqual>
					<!-- MODIFY -->
					
			<logic:equal value="MODIFY" name="DeptUnitIcdMappingFB" property="hmode">
			<his:TitleTag name="Department Unit Icd Mapping>>Modify ">
				</his:TitleTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="unit"/>
								</font>
							</div>
						</td>
						<td width="40%" class="tdfont">
							<div align="left">
							<bean:write name="DeptUnitIcdMappingFB" property="deptUnitName"/>
							</div>
						</td>
						<td width="20%"  class="tdfonthead">
						</td>
					</tr>
				</table>
				
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="group"/>
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<div align="left">
								<html:select name="DeptUnitIcdMappingFB" property="icdGroupCode"  style="width:200px;" onchange="getSubGroup(this)">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP%>" property="icdGroupCode" labelProperty="icdGroup" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="20%" class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="subGroup"/>
								</font>
							</div>
						</td>
						<td width="40%" class="tdfonthead">
							<div align="left">
								<html:select name="DeptUnitIcdMappingFB" property="icdSubgroupCode" style="width:200px;" onchange="getDisease(this)" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE%>" >
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE%>" property="icdSubgroupCode" labelProperty="icdSubgroup" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="disease"/>
									</b>
								</font>
							</div>
						</td>
						<td width="20%"  class="tdfonthead"></td>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="selectedDisease"/>
									</b>
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="30%"  class="tdfont">
							<div align="center">
								<html:select name="DeptUnitIcdMappingFB" property="diseaseCode"  multiple="true" size="6"  >
										<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE%>" >
										<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE%>" property="value" labelProperty="label" />
										</logic:present>
								</html:select>
							</div>
						</td>
						<td width="30%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="30%"  class="tdfont">
							<div align="center">
								<html:select name="DeptUnitIcdMappingFB" property="selectedDisease" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT%>" >
										<html:options collection="<%=OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT %>" property="value" labelProperty="label" />
								</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="40%" class="tdfonthead"></td>
						<td width="20%" class="tdfonthead"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
				</table>
			</logic:equal>
		<logic:notEqual value="MODIFY" name="DeptUnitIcdMappingFB" property="hmode">	
	<logic:equal value="VIEW" name="DeptUnitIcdMappingFB" property="hmode">
			<his:TitleTag name="Department Unit Icd Mapping>>">
				</his:TitleTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="40%"  class="tdfonthead">
								<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="unit"/>
								</font>
								</div>
							</td>
							<td width="40%" class="tdfont">
								<div align="left">
								<bean:write name="DeptUnitIcdMappingFB" property="deptUnitName"/>
								</div>
							</td>
							<td width="20%"  class="tdfonthead">
							</td>
						</tr>
				</table>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%" class="tdfonthead">
						</td>
						<td width="40%" class="tdfonthead">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="selectedDisease"/></b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfonthead"></td>
					</tr>
					<tr>
						<logic:present name="<%=OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT%>" >
							<logic:iterate id="entryObj" indexId="idx" name="<%=OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT%>" type="hisglobal.utility.Entry">
							<tr>
								<td width="40%" class="tdfonthead"></td>
								<td width="60%" class="tdfont">
									<div align="left">
											<bean:write name="entryObj" property="label"/>
									</div>
								</td>
							</tr>
							</logic:iterate>
						</logic:present>
					</tr>
				</table>
			</logic:equal>
		</logic:notEqual>
	</his:ContentTag>
				
				
				<his:ButtonToolBarTag>
				<logic:notEqual name="DeptUnitIcdMappingFB" property="hmode" value="MODIFY">	
					<logic:notEqual name="DeptUnitIcdMappingFB" property="hmode" value="VIEW">	
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateAdd() && submitForm('SAVE')" onkeypress="if(event.keyCode==13)validateAdd() && submitForm('SAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
				</logic:notEqual>
				</logic:notEqual>
					<logic:equal name="DeptUnitIcdMappingFB" property="hmode" value="MODIFY">	
					<logic:notEqual name="DeptUnitIcdMappingFB" property="hmode" value="VIEW">	
					<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style=cursor:pointer tabindex="1" onclick="validateModify() && submitForm('MODIFYSAVE')" onkeypress="if(event.keyCode==13)validateModify() && submitForm('MODIFYSAVE')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="modifyClear()" onkeypress="if(event.keyCode==13) modifyClear();">
				</logic:notEqual>
				</logic:equal>	
			
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
				</his:ButtonToolBarTag>
				
				<html:hidden name="DeptUnitIcdMappingFB" property="hmode"/>
				<html:hidden name="DeptUnitIcdMappingFB" property="deptUnitName"/>
				<html:hidden name="DeptUnitIcdMappingFB" property="deptUnitCode"/>
				<div id="status" align="left" style="display:none;" >
					<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	No disease Found</b>	
					</font>
				</div>
				
			</his:TransactionContainer>
		<div align="center">
			<center><b><his:status/></b></center>
		</div>
		
		</html:form>
	</body>
</html>