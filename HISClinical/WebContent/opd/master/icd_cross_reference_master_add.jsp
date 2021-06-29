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
<body onload="onLoadCall();">

<html:form action="/master/IcdCrossRefMaster">

	<his:TransactionContainer>

		<his:TitleTag name="ICD Cross Reference Master >> Add">
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
							<html:hidden name="IcdCrossRefMasterFB"	property="strIndexTerm" />
							<html:hidden name="IcdCrossRefMasterFB" property="indexCode" /> 
					 		<bean:write	name="IcdCrossRefMasterFB" property="strIndexTerm" />
					 	</div>
					</td>
				</tr>
			</table>

			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="1">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="modifieratlevel1" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:hidden name="IcdCrossRefMasterFB" property="strModTerm1" />
								<html:hidden name="IcdCrossRefMasterFB" property="strModifier1" />
								<bean:write name="IcdCrossRefMasterFB" property="strModTerm1" />
							</div>
						</td>
					</tr>
				</logic:greaterEqual>

				<logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="2">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="modifieratlevel2" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:hidden name="IcdCrossRefMasterFB" property="strModTerm2" />
								<html:hidden name="IcdCrossRefMasterFB" property="strModifier2" />
								<bean:write name="IcdCrossRefMasterFB" property="strModTerm2" />
							</div>
						</td>
					</tr>
				</logic:greaterEqual>

				<logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="3">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="modifieratlevel3" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:hidden name="IcdCrossRefMasterFB" property="strModTerm3" />
								<html:hidden name="IcdCrossRefMasterFB" property="strModifier3" />
								<bean:write name="IcdCrossRefMasterFB" property="strModTerm3" />
							</div>
						</td>
					</tr>
				</logic:greaterEqual>

				<logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="4">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="modifieratlevel4" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:hidden name="IcdCrossRefMasterFB" property="strModTerm4" />
								<html:hidden name="IcdCrossRefMasterFB" property="strModifier4" />
								<bean:write name="IcdCrossRefMasterFB" property="strModTerm4" />
							</div>
						</td>
					</tr>
				</logic:greaterEqual>

				<logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="5">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="modifieratlevel5" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:hidden name="IcdCrossRefMasterFB" property="strModTerm5" />
								<html:hidden name="IcdCrossRefMasterFB" property="strModifier5" />
								<bean:write name="IcdCrossRefMasterFB" property="strModTerm5" />
							</div>
						</td>
					</tr>
				</logic:greaterEqual>

				<logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="6">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="modifieratlevel6" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:hidden name="IcdCrossRefMasterFB" property="strModTerm6" />
								<html:hidden name="IcdCrossRefMasterFB" property="strModifier6" />
								<bean:write name="IcdCrossRefMasterFB" property="strModTerm6" />
							</div>
						</td>
					</tr>
				</logic:greaterEqual>

				<logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="7">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="modifieratlevel7" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:hidden name="IcdCrossRefMasterFB" property="strModTerm7" />
								<html:hidden name="IcdCrossRefMasterFB" property="strModifier7" />
								<bean:write name="IcdCrossRefMasterFB" property="strModTerm7" />
							</div>
						</td>
					</tr>
				</logic:greaterEqual>

				<logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="8">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="modifieratlevel8" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:hidden name="IcdCrossRefMasterFB" property="strModTerm8" />
								<html:hidden name="IcdCrossRefMasterFB" property="strModifier8" />
								<bean:write name="IcdCrossRefMasterFB" property="strModTerm8" />
							</div>
						</td>
					</tr>
				</logic:greaterEqual>

				<logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheck" value="9">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="modifieratlevel9" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:hidden name="IcdCrossRefMasterFB" property="strModTerm9" />
								<html:hidden name="IcdCrossRefMasterFB" property="strModifier9" />
								<bean:write name="IcdCrossRefMasterFB" property="strModTerm9" />
							</div>
						</td>
					</tr>
				</logic:greaterEqual>
			</table>

			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="100%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
								<html:radio name="IcdCrossRefMasterFB" property="seeTermRadio" value="0" />See &nbsp;&nbsp; 
								<html:radio name="IcdCrossRefMasterFB" property="seeTermRadio" value="1" />See Also 
							</font>
						</div>
					</td>
				</tr>
			</table>

			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="50%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*</font>
					  			<b>Reference <bean:message key="indexTerm" /></b>
					 		</font>
					 	</div>
					</td>
					
					<td width="50%" class="tdfont">
						<div align="left">
							<html:select name="IcdCrossRefMasterFB" property="refIndexCode" style="width:150px" tabindex="1"
								onchange="setStrIndexTerm(this);enableRefIndexValues();">
								<html:option value="-1">Select Value</html:option>
								<logic:present	name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_INDEX_TERM%>">
									<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_INDEX_TERM%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
						</div>
					</td>
				</tr>
			</table>

			<logic:greaterEqual name="IcdCrossRefMasterFB" property="strCheckSee" value="1">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
										<b>Reference <bean:message key="modifieratlevel1" /></b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:select name="IcdCrossRefMasterFB" property="strRefModifier1" style="width:150px" tabindex="1" onchange="setStrIndexTermPlusModifier(this);" >
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_REFTERM%>">
											<html:options collection="<%=OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_REFTERM%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
						</tr>
				</table>
			</logic:greaterEqual>

				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
										<b>Reference Term</b>
									</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									<html:hidden name="IcdCrossRefMasterFB" property="strIndexTermPlus" />
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b id="idRefSeeTerm"></b>
									</font>
								</div>
							</td>
						</tr>
				</table>
		</his:ContentTag>
	</his:statusNew>

		<his:ButtonToolBarTag>
			<his:statusNew>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'
					style="cursor: pointer" tabindex="1" onclick="validateAddModify('SAVE')"
					onkeypress="if(event.keyCode==13) validateAddModify('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style="cursor: pointer" tabindex="1" onclick="submitForm('CANCEL')"
					onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
					style="cursor: pointer" tabindex="1" onclick="clearTextFields();"
					onkeypress="if(event.keyCode==13) clearTextFields();">
			</his:statusNew>

			<his:statusUnsuccessfull>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style="cursor: pointer" tabindex="1" onclick="submitForm('CANCEL')"
					onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
			</his:statusUnsuccessfull>

		</his:ButtonToolBarTag>
		<his:status />
	</his:TransactionContainer>

	<html:hidden name="IcdCrossRefMasterFB" property="hmode" />
	<html:hidden name="IcdCrossRefMasterFB" property="strCheck" />
	<html:hidden name="IcdCrossRefMasterFB" property="strCheckSee" />
	
	<html:hidden name="IcdCrossRefMasterFB" property="pageFlag" value="ADD_PAGE" />
	<html:hidden name="IcdCrossRefMasterFB" property="seeIndexModifierId" />
	<html:hidden name="IcdCrossRefMasterFB" property="seeAlsoIndexModifierId" />
	
</html:form>
</body>
</html>