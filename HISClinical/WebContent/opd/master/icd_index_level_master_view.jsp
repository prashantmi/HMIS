<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!-- 

/**
 *  Developer : Vivek Aggarwal
 *  Created Date : 15-Mar-2011
 *  Process Name : Icd Index Level Master
 *  Last Modified Date : 28-Mar-2011  
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

<body>
	<html:form action="/master/IcdIndexLevelMaster" scope="request" >
			
		<his:TransactionContainer>
			<his:TitleTag name=" ICD Index Level Master >> View" >
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
								<bean:write name="IcdIndexLevelMasterFB" property="indexCode"/>
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
							<bean:write name="IcdIndexLevelMasterFB" property="modifierLevel"/>
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
								<bean:write name="IcdIndexLevelMasterFB" property="modifier"/>
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
								<html:radio name="IcdIndexLevelMasterFB" property="isWith" disabled="true" tabindex="1" value="1">Yes</html:radio>
								<html:radio name="IcdIndexLevelMasterFB" property="isWith" disabled="true" tabindex="1" value="0">No</html:radio>
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
								<bean:write name="IcdIndexLevelMasterFB" property="parentIndexModifierCode"/>
							</div>
						</td>
					</tr>
					
				</table>
			
<!--	For Icd Disease Code				-->


			<div id="divIcdDiseaseCode" align="center" style="display: block;">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="icdDisease" /></b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<bean:write name="IcdIndexLevelMasterFB" property="diseaseCode"/>					
							</div>
						</td>
					</tr>		
								
				</table>
			</div>
			
					
<!--	For Dual Icd Disease Code				-->		
					
			<div id="divDualIcdDiseaseCode" align="center" style="display: block;">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
									
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="dual" /> <bean:message key="icdDisease" /></b>
								</font>
							</div>
						</td>
						
						<td width="50%" class="tdfont">
							<div align="left">
								<bean:write name="IcdIndexLevelMasterFB" property="dualDiseaseCode"/>
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
									&nbsp;
								<bean:write name="IcdIndexLevelMasterFB" property="remark"/>
								</div>
							</td>
						</tr>
			</table>
				
				</his:ContentTag>
		</his:statusNew>
			
				<his:ButtonToolBarTag>
						<his:statusNew>
				    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">

						</his:statusNew>	
			    		
			    		<his:statusUnsuccessfull>
				    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
			    		</his:statusUnsuccessfull>       
			    		   	
				</his:ButtonToolBarTag>
	<his:status />
			
			
		
	</his:TransactionContainer>
			
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
		
		<html:hidden name="IcdIndexLevelMasterFB" property="pageFlag" value="VIEW_PAGE" />
		
		<html:hidden name="IcdIndexLevelMasterFB" property="indexModifierID" />


		
	</html:form>
</body>
</html>
