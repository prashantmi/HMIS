<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
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
 *  Created Date :16th March 2011  
 *  Process Name : Icd Cross Reference Master
 *  Last Modified Date : 
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
<his:javascript src="/opd/js/icdCrossRefMaster.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
</head>

<body>

<html:form action="/master/IcdCrossRefMaster">

	<his:TransactionContainer>
	
	<his:TitleTag name="ICD Cross Reference Master">
	</his:TitleTag>
<his:statusNew>		
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="50%" class="tdfonthead">
					<div align="right"><font color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <font
						color="#FF0000">*</font> <b><bean:message key="indexTerm" /></b>
					</font></div>
				</td>
				<td width="50%" class="tdfont">
					<div align="left">
						<html:select name="IcdCrossRefMasterFB" property="indexCode" style="width:150px" tabindex="1" onchange="setValue(this);enableIndexValues();">
							<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_INDEX_TERM%>">
							<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_INDEX_TERM%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
			</tr>
		</table>
		
		<logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="1">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_TERM%>">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
							color="#FF0000">*</font>
							<b><bean:message key="modifieratlevel1"/></b>
						</font></div>
					</td>
					<td width="50%" class="tdfont">
						<div align="left">
							<html:select name="IcdCrossRefMasterFB" property="strModifier1" style="width:150px" tabindex="1" onchange="setModifierValue(this,1);enableModifier(this,1); ">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_TERM%>">
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_TERM%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
				</tr>
			</logic:present>	
			</table>
		</logic:greaterEqual>
		
               <logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="2">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
			
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> 
							<font color="#FF0000">*</font><b><bean:message key="modifieratlevel2" />
						</b>
						</font></div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left"><html:select name="IcdCrossRefMasterFB" property="strModifier2" style="width:150px" tabindex="1" onchange="setModifierValue(this,2); enableModifier(this,2); " >
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL2_TERM%>">
							<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL2_TERM%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>
					</tr>
			
				</table>
            </logic:greaterEqual>  
            
            <logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="3">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
						color="#FF0000">*</font> <b><bean:message key="modifieratlevel3"/></b>
						</font></div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left"><html:select name="IcdCrossRefMasterFB" property="strModifier3" style="width:150px" tabindex="1" onchange="setModifierValue(this,3); enableModifier(this,3); ">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL3_TERM%>">
							<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL3_TERM%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>
					</tr>
				</table>
            </logic:greaterEqual>   
            
             <logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="4">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
						color="#FF0000">*</font> <b><bean:message key="modifieratlevel4"/> </b>
						</font></div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left"><html:select name="IcdCrossRefMasterFB" property="strModifier4" style="width:150px" tabindex="1" onchange=" setModifierValue(this,4); enableModifier(this,4);">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL4_TERM%>">
							<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL4_TERM%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>
					</tr>
				</table>
            </logic:greaterEqual>   
            
                <logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="5">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
						color="#FF0000">*</font> <b><bean:message key="modifieratlevel5"/></b>
						</font></div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left"><html:select name="IcdCrossRefMasterFB" property="strModifier5" style="width:150px" tabindex="1" onchange=" setModifierValue(this,5); enableModifier(this,5);">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL5_TERM%>">
							<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL5_TERM%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>
					</tr>
				</table>
            </logic:greaterEqual>
            
             <logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="6">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
						color="#FF0000">*</font> <b><bean:message key="modifieratlevel6" /></b>
						</font></div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left"><html:select name="IcdCrossRefMasterFB" property="strModifier6" style="width:150px" tabindex="1" onchange=" setModifierValue(this,6); enableModifier(this,6);">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL6_TERM%>">
							<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL6_TERM%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>
					</tr>
				</table>
            </logic:greaterEqual>   
            
               <logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="7">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
						color="#FF0000">*</font> <b><bean:message key="modifieratlevel7" /></b>
						</font></div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left"><html:select name="IcdCrossRefMasterFB" property="strModifier7" style="width:150px" tabindex="1" onchange=" setModifierValue(this,7); enableModifier(this,7);">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL7_TERM%>">
							<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL7_TERM%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>
					</tr>
				</table>
            </logic:greaterEqual>   
            
             <logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="8">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
						color="#FF0000">*</font> <b><bean:message key="modifieratlevel8"/></b>
						</font></div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left"><html:select name="IcdCrossRefMasterFB" property="strModifier8" style="width:150px" tabindex="1" onchange=" setModifierValue(this,8); enableModifier(this,8);">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL8_TERM%>">
							<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL8_TERM%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>
					</tr>
				</table>
            </logic:greaterEqual>   
            
             <logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="9">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
						color="#FF0000">*</font> <b><bean:message key="modifieratlevel9" /></b>
						</font></div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left"><html:select name="IcdCrossRefMasterFB" property="strModifier9" style="width:150px" tabindex="1" onchange="setModifierValue(this,9); enableSeeTermsForModNine(); ">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL9_TERM%>">
							<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL9_TERM%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>
					</tr>
				</table>
            </logic:greaterEqual>   
            
	</his:ContentTag>
	
	<his:ContentTag>
		<div id = "seeAndSeeAlsoTerms" >
    		<table width="100%" border="0" cellspacing="1" cellpadding="0">
    		<logic:equal value="1" name="IcdCrossRefMasterFB" property="seeTermsFlag" >	
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>see</b>	
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont">
						<div align="left">
							<logic:present name="IcdCrossRefMasterFB" property="seeTerm">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:hidden name="IcdCrossRefMasterFB" property="seeTermCode" />	
									<html:hidden name="IcdCrossRefMasterFB" property="seeTerm" />							
									<bean:write name="IcdCrossRefMasterFB" property="seeTerm"/>
								</font>
							</logic:present>	
						</div>
					</td>								
				</tr>
		
		
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>see also</b>	
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<html:hidden name="IcdCrossRefMasterFB" property="seeAlsoTermCode" />
								<html:hidden name="IcdCrossRefMasterFB" property="seeAlsoTerm" />
								<bean:write name="IcdCrossRefMasterFB" property="seeAlsoTerm"/>
							</font>
						</div>
					</td>
				</tr>
			</logic:equal>	
			</table>
		</div>			
		</his:ContentTag>
</his:statusNew>
		<his:ButtonToolBarTag>
						<his:statusNew>
							
							<logic:notEqual name="IcdCrossRefMasterFB" property="seeTermsFlag" value="1">
								<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>' style="cursor:pointer" tabindex="1" onclick ="validate('ADD')" onkeypress="validate('ADD')">
							</logic:notEqual>
							
							<logic:equal name="IcdCrossRefMasterFB" property="seeTermsFlag" value="1">
				    			<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer"  tabindex="1" onclick ="validate('MODIFY')" onkeypress="if(event.keyCode==13) validate('MODIFY')">
				    		</logic:equal>
				    			    					    		
				    		
				    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
				    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="clearTextFields();" onkeypress="if(event.keyCode==13) clearTextFields();">
						</his:statusNew>	
		</his:ButtonToolBarTag>
		
		<center><b><his:status/></b></center>	
			
	</his:TransactionContainer>
	
	            <html:hidden name="IcdCrossRefMasterFB" property="hmode" />
		        <html:hidden name="IcdCrossRefMasterFB" property="strIndexTerm" />
	  	        
		        <html:hidden name="IcdCrossRefMasterFB" property="strModTerm1" />
		        <html:hidden name="IcdCrossRefMasterFB" property="strModTerm2" />
		        <html:hidden name="IcdCrossRefMasterFB" property="strModTerm3" />
		        <html:hidden name="IcdCrossRefMasterFB" property="strModTerm4" />
		        <html:hidden name="IcdCrossRefMasterFB" property="strModTerm5" />
		        <html:hidden name="IcdCrossRefMasterFB" property="strModTerm6" />
		        <html:hidden name="IcdCrossRefMasterFB" property="strModTerm7" />
		        <html:hidden name="IcdCrossRefMasterFB" property="strModTerm8" />
		        <html:hidden name="IcdCrossRefMasterFB" property="strModTerm9" />
		        <html:hidden name="IcdCrossRefMasterFB" property="strCheck" />
		        <html:hidden name="IcdCrossRefMasterFB" property="seeTermsFlag" />
		        <html:hidden name="IcdCrossRefMasterFB" property="pageFlag" value="NEW_PAGE"/>
				
				<html:hidden name="IcdCrossRefMasterFB" property="seeIndexModifierId" />
				<html:hidden name="IcdCrossRefMasterFB" property="seeAlsoIndexModifierId" />
	
</html:form>
</body>
</html>