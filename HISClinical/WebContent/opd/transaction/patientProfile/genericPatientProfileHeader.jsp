<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/opd/js/generic_patient_profile.js"/>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/js/generic_patient_profile.js"/>

<his:TransactionContainer> 

<his:statusNew>
	<his:SubTitleTag key="ProfileDtl">
	</his:SubTitleTag>

	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">



         <tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="profileHeader" />
						</font>
					</div>
				</td>
				<td width="50%" class="tdfont" >
					<div align="left">
						<html:text name="GenericPatientProfileFB" property="profileHeader" tabindex="1" size="50" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event);"></html:text>
					</div>
				</td>
				
				<td width="25%" class="tdfont"  colspan="2">
					<div align="right">
						<img class="button" src='<his:path src="/hisglobal/images/btn-next.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateForm1(),'PROFILEOPTIONSNEW');" onclick="submitFormOnValidate(validateForm1(),'PROFILEOPTIONSNEW');" tabindex="1" />
					</div>			
				</td>
			</tr>
			
</table>
	</his:ContentTag>
</his:statusNew>

<his:ButtonToolBarTag>	
	
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('SAVEPROFILEHEADER');" onclick="submitForm('SAVEPROFILEHEADER');" tabindex="1" />
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW')">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW');">
	
</his:ButtonToolBarTag>
</his:TransactionContainer>
<html:hidden name="GenericPatientProfileFB" property="hmode" />

<html:hidden name="GenericPatientProfileFB" property="patCrNo" />
<html:hidden name="GenericPatientProfileFB" property="episodeCode" />
<html:hidden name="GenericPatientProfileFB" property="episodeVisitNo" />
<html:hidden name="GenericPatientProfileFB" property="departmentUnitCode" />
<html:hidden name="GenericPatientProfileFB" property="wardCode" />
<html:hidden name="GenericPatientProfileFB" property="admissionNo" />
<html:hidden name="GenericPatientProfileFB" property="deskType" />
<html:hidden name="GenericPatientProfileFB" property="deskId" />
<html:hidden name="GenericPatientProfileFB" property="profileId" />
<html:hidden name="GenericPatientProfileFB" property="serialNo" />
<html:hidden name="GenericPatientProfileFB" property="remarks" />
<html:hidden name="GenericPatientProfileFB" property="profileType" />
<html:hidden name="GenericPatientProfileFB" property="dischargeModifyFlag" />
<html:hidden name="GenericPatientProfileFB" property="entryDate" />
<html:hidden name="GenericPatientProfileFB" property="reportMode" />
<html:hidden name="GenericPatientProfileFB"	property="selectedProfileId" />
<html:hidden name="GenericPatientProfileFB" property="selectedSerialNo" />
			