<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@page import="opd.OpdConfig"%>

<!-- 

/**
 *  Developer : Vivek Aggarwal
 *  Created Date : 1-Feb-2011
 *  Process Name : Icd Index Level Master
 *  Last Modified Date : 16-Mar-2011  
 */

 -->
 
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

<his:javascript src="/opd/js/icd_index_level_master.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
</head>

<body onload="onLoadFunction()">
	<html:form action="/master/IcdIndexLevelMaster" scope="request" >
			
		<his:TransactionContainer>
			<his:TitleTag name=" ICD Index Level Master >> Modify" >
			</his:TitleTag>
		<his:statusNew>		
			<his:ContentTag>
						
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="indexTerm" /></b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexLevelMasterFB" property="indexCode" disabled="true" style="width:150px" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_INDEX_TERM%>">
										<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_INDEX_TERM%>" property="value" labelProperty="label" />
									</logic:present> 
								</html:select>	
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="level" /></b>
								</font>
							</div>
						</td>	
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexLevelMasterFB" property="modifierLevel" style="width:150px" disabled="true" tabindex="1" onchange="populateParentModifier()">
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>							
									<html:option value="3">3</html:option>							
									<html:option value="4">4</html:option>							
									<html:option value="5">5</html:option>							
									<html:option value="6">6</html:option>							
									<html:option value="7">7</html:option>							
									<html:option value="8">8</html:option>							
									<html:option value="9">9</html:option>													
								</html:select>	
							</div>
						</td>
					</tr>
					
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="modifierTerm" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="IcdIndexLevelMasterFB" property="modifier" tabindex="1" maxlength="50" size="28" onkeypress="return validateAlphaNumericOnly(event,this)"/>
							</div>					
						</td>
					</tr>
					
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="isWith" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:radio name="IcdIndexLevelMasterFB" property="isWith" tabindex="1" value="1">Yes</html:radio>
								<html:radio name="IcdIndexLevelMasterFB" property="isWith" tabindex="1" value="0">No</html:radio>
							</div>					
						</td>
					</tr>
					
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="parentModifier" /></b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexLevelMasterFB" property="parentIndexModifierCode" style="width:150px" tabindex="1"  >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_PARENT_MODIFIER%>">
										<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_PARENT_MODIFIER%>" property="value" labelProperty="label"  />
									</logic:present> 
								</html:select>	
							</div>
						</td>
					</tr>
					
				</table>
			
<!--	Check box for Icd Disease Code				-->

				<table width="100%" border="0" cellspacing="1" cellpadding="0">
				
					<tr>
						<td width="100%" class="tdfont" colspan="2">
							<div align="left">
								<logic:empty name="IcdIndexLevelMasterFB" property="diseaseCode">
									<html:checkbox property="diseaseCodeChk"  onclick="showHideICDDis(this)" tabindex="1">ICD Disease Code</html:checkbox>
								</logic:empty>
								<logic:notEmpty name="IcdIndexLevelMasterFB" property="diseaseCode">
									<input type="checkbox" name="diseaseCodeChk"  onclick="showHideICDDis(this)" tabindex="1" checked="checked" >ICD Disease Code
								</logic:notEmpty>
							</div>					
						</td>
					</tr>
				</table>
			<div id="divIcdDiseaseCode" align="center" style="display: none;">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="group" /></b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexLevelMasterFB" property="icdGroupCode" style="width:150px" tabindex="1" onchange="populateIcdSubGroup();">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_LIST_ICD_GROUP%>">
										<html:options collection="<%=OpdConfig.OPD_LIST_ICD_GROUP%>" property="icdGroupCode" labelProperty="icdGroup"  />
									</logic:present> 
								</html:select>	
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="subGroup" /></b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexLevelMasterFB" property="icdSubgroupCode" style="width:150px" tabindex="1" onchange="populateIcdDisease()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_LIST_ICD_SUB_GROUP%>">
										<html:options collection="<%=OpdConfig.OPD_LIST_ICD_SUB_GROUP%>" property="icdSubgroupCode" labelProperty="icdSubgroup"  />
									</logic:present> 
								</html:select>	
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="disease" /></b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexLevelMasterFB" property="diseaseCode" style="width:150px" tabindex="1"  >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_LIST_ICD_DISEASE%>">
										<html:options collection="<%=OpdConfig.OPD_LIST_ICD_DISEASE%>" property="diseaseCode" labelProperty="disease"  />
									</logic:present> 
								</html:select>	
							</div>
						</td>
					</tr>					
				</table>
			</div>
			
					
<!--	Check box for Dual Icd Disease Code				-->		
		
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						
						<td width="100%" class="tdfont" colspan="2">
							<div align="left">
								<logic:empty name="IcdIndexLevelMasterFB" property="dualDiseaseCode">
									<html:checkbox property="dualDiseaseCodeChk"  onclick="showHideDualICDDis(this)" tabindex="1">Dual ICD Disease Code</html:checkbox>
								</logic:empty>
								<logic:notEmpty name="IcdIndexLevelMasterFB" property="dualDiseaseCode">
									<input type="checkbox" name="dualDiseaseCodeChk"  onclick="showHideDualICDDis" tabindex="1" checked="checked" />Dual ICD Disease Code
								</logic:notEmpty>
							</div>					
						</td>
					</tr>
				</table>
					
			<div id="divDualIcdDiseaseCode" align="center" style="display: none;">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="group" /></b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexLevelMasterFB" property="dualIcdGroupCode" style="width:150px" tabindex="1" onchange="populateDualIcdSubGroup()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_LIST_ICD_GROUP%>">
										<html:options collection="<%=OpdConfig.OPD_LIST_ICD_GROUP%>" property="icdGroupCode" labelProperty="icdGroup"  />
									</logic:present> 
								</html:select>	
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="subGroup" /></b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexLevelMasterFB" property="dualIcdSubGroupCode" style="width:150px" tabindex="1" onchange="populateDualIcdDisease()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_LIST_DUAL_ICD_SUB_GROUP%>">
										<html:options collection="<%=OpdConfig.OPD_LIST_DUAL_ICD_SUB_GROUP%>" property="icdSubgroupCode" labelProperty="icdSubgroup"  />
									</logic:present> 
								</html:select>	
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="disease" /></b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexLevelMasterFB" property="dualDiseaseCode" style="width:150px" tabindex="1"  >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.OPD_LIST_DUAL_ICD_DISEASE%>">
										<html:options collection="<%=OpdConfig.OPD_LIST_DUAL_ICD_DISEASE%>" property="diseaseCode" labelProperty="disease"  />
									</logic:present> 
								</html:select>	
							</div>
						</td>
					</tr>
					
					
				</table>
			</div>
			
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="remarks" /></b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									&nbsp;<html:textarea name="IcdIndexLevelMasterFB" property="remark" tabindex="1" rows="2" cols="24" onkeypress="return (validateTextArea(event,this,100) && validateAlphaNumericOnly(event,this))" />
								</div>
							</td>
						</tr>
			</table>
				
				</his:ContentTag>
		</his:statusNew>
			
				<his:ButtonToolBarTag>
						<his:statusNew>
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick ="validateAddModify('MODIFYSAVE')" onkeypress="if(event.keyCode==13) validateAddModify('MODIFYSAVE')">
				    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
				    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('MODIFY')" onkeypress="if(event.keyCode==13)  submitForm('MODIFY')">
						</his:statusNew>	
			    		
			    		<his:statusUnsuccessfull>
				    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
			    		</his:statusUnsuccessfull>       
			    		   	
				</his:ButtonToolBarTag>
	<his:status />
			
			
		
	</his:TransactionContainer>

		<html:hidden name="IcdIndexLevelMasterFB" property="chk" />
			
		<html:hidden name="IcdIndexLevelMasterFB" property="hmode" />
		<html:hidden name="IcdIndexLevelMasterFB" property="isValid"/>
		<html:hidden name="IcdIndexLevelMasterFB" property="slNo"/>
		<html:hidden name="IcdIndexLevelMasterFB" property="indexCode" />
		<html:hidden name="IcdIndexLevelMasterFB" property="modifierLevel" />
		
		<html:hidden name="IcdIndexLevelMasterFB" property="icdGroup" />
		<html:hidden name="IcdIndexLevelMasterFB" property="icdGroupCode" />
		
		<html:hidden name="IcdIndexLevelMasterFB" property="icdSubgroup" />
		<html:hidden name="IcdIndexLevelMasterFB" property="icdSubgroupCode" />
		
		<html:hidden name="IcdIndexLevelMasterFB" property="diseaseCode" />
		
		<html:hidden name="IcdIndexLevelMasterFB" property="dualIcdGroupCode" />
		<html:hidden name="IcdIndexLevelMasterFB" property="dualIcdSubGroupCode" />
		<html:hidden name="IcdIndexLevelMasterFB" property="dualDiseaseCode" />
		
		<html:hidden name="IcdIndexLevelMasterFB" property="pageFlag" value="MODIFY_PAGE" />
		
		<html:hidden name="IcdIndexLevelMasterFB" property="indexModifierID" />


		
	</html:form>
</body>
</html>
