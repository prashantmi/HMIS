<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
%>
<%@page import="opd.OpdConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<!-- 

/**
 *  Developer : Pfiza Nooreen
 *  Created Date :10th March 2011  
 *  Process Name : Icd Index Master
 *  Last Modified Date : 14th March 2011  
 */

-->


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/opd/js/icdIndexMaster.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
</head>
<body onload="onLoadFunction()">

<html:form action="/master/IcdIndexMaster">

	<his:TransactionContainer>
		<his:TitleTag name="ICD Index Master Vol-3 ADD">
		</his:TitleTag>
		
		<his:statusNew>
		
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
			<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b>Diagnostic Term</b>
								</font>
							</div>
						</td>
						
					<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="IcdIndexMasterFB" property="diagnosticTerm" tabindex="1" maxlength="200" size="28" onkeypress="return validateIndexTerm(event);" />
							</div>					
						</td>
					<tr>
					<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>Hospital Synonym</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="IcdIndexMasterFB" property="hospitalSynonym" tabindex="1" maxlength="200" size="28" onkeypress="return validateIndexTerm(event);"/>
							</div>					
						</td>
						
				</tr>
				</table>
				<!-- Checkbox for Icd Disease Code -->
				
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
				
					<tr>
						<td width="100%" class="tdfont" colspan="2">
							<div align="left">
							<html:checkbox property="strDiseaseCodeChk" tabindex="1" onclick="showHideICDDiseaseCode(this)" >ICD Disease Code</html:checkbox>
							</div>					
						</td>
					</tr>
				</table>
				
				<div id="DIVIcdDiseaseCode" align="center" style="display:none;">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">	
						<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b>Group Code</b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexMasterFB" property="icdGroupCode" style="width:150px" tabindex="1" onchange="enableSubGroup();">
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
									<b>SubGroup Code</b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexMasterFB" property="icdSubgroupCode" style="width:150px" tabindex="1" onchange="enableDiseaseCode();">
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
									<b>Disease Code</b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexMasterFB" property="diseaseCode" style="width:150px" tabindex="1"  >
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
		
		<!--	Check box for Dual Icd Disease Code		-->
		
		   <table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						
						<td width="100%" class="tdfont" colspan="2">
							<div align="left">
							<html:checkbox property="strDualDiseaseCodeChk" tabindex="1" onclick="showHideDualICDDisease(this);">Dual ICD Disease Code</html:checkbox>
							</div>					
						</td>
					</tr>
				</table>
					<div id="DIVDualIcdDiseaseCode" align="center" style="display:none;">
		               <table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b>Dual Group Code</b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexMasterFB" property="strDualGroupCode" style="width:150px" tabindex="1" onchange="enableDualSubGroup();">
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
									<b>Dual SubGroup Code</b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexMasterFB" property="strDualSubGroupCode" style="width:150px" tabindex="1" onchange="enableDualDisease();">
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
									<b>Dual Disease Code</b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="IcdIndexMasterFB" property="strDualDiseaseCode" style="width:150px" tabindex="1"  >
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
	  									<b>Remarks</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									&nbsp;<html:textarea name="IcdIndexMasterFB" property="strRemark" tabindex="1" rows="2" cols="24" onkeypress="return (validateTextArea(event,this,100) && validateAlphaNumericOnly(event,this))" />
								</div>
							</td>
						</tr>
			</table>
						
		</his:ContentTag>
		</his:statusNew>
		
		<!-- Button Tool Bar -->
		
	<his:ButtonToolBarTag>
						<his:statusNew>
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick ="validateSave('SAVE')" onkeypress="if(event.keyCode==13) validateSave('SAVE')">
				    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
				    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="clearTextFields();" onkeypress="if(event.keyCode==13) clearTextFields();">
						</his:statusNew>	
			    		
			    		<his:statusUnsuccessfull>
				    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
			    		</his:statusUnsuccessfull>       
			    		   	
				</his:ButtonToolBarTag>
		<center><b><his:status/></b></center>
		</his:TransactionContainer>

		        <html:hidden name="IcdIndexMasterFB" property="hmode" />
		        <html:hidden name="IcdIndexMasterFB" property="indexCode" />
				<html:hidden name="IcdIndexMasterFB" property="slNo"/>
				<html:hidden name="IcdIndexMasterFB" property="isActive"/>
				
				
			
				<html:hidden name="IcdIndexMasterFB" property="icdGroupCode" />
				<html:hidden name="IcdIndexMasterFB" property="icdSubgroupCode" />
				<html:hidden name="IcdIndexMasterFB" property="diseaseCode" />
				
				<html:hidden name="IcdIndexMasterFB" property="strDualGroupCode" />
				<html:hidden name="IcdIndexMasterFB" property="strDualSubGroupCode" />
				<html:hidden name="IcdIndexMasterFB" property="strDualDiseaseCode" />
				
		</html:form>	

</body>
</html>