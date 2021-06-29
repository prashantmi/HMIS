
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.RegistrationConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function checkMaxLenTextArea(e,elem,maxLen)
{
	key = e.keyCode;
	var valid=true;
	if(key==8 || key==46)
		return true;
	if(key==13)
		return true;
	val = elem.value; 
	if(val.length+1>maxLen)	return false;
}

function validateForm()
{
	if(isEmpty(document.getElementsByName('remarks')[0],'Remarks'))
		return true
	else
		return false	
}



</script>
<his:TransactionContainer>


<his:TitleTag>
	<his:name>
		<bean:message key="patNotAttended" />
	</his:name>
</his:TitleTag>
<jsp:include page="/registration/patientDetail.cnt" flush="true"></jsp:include>

	
	<his:ContentTag>
	
		<table width="100%" cellspacing="1" cellpadding="0">
		
			
			<tr>
				<td width="50%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<font color="#FF0000">*</font> 
							<bean:message key="remarks"/>
						</font>
					</div>
				</td>
				<td width="50%" class="tdfont">
					
					<div align="left">
						<html:textarea styleClass="textarea2" name="PatientNotAttendedFB" property="remarks" tabindex="1" 
							onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
					</div>
					
					
				</td>
			</tr>
		</table>
	</his:ContentTag>
	


<his:ButtonToolBarTag>
			<img class='button'	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex='1' onclick="submitFormOnValidate(validateForm(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateForm(),'SAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusUnsuccessfull>
</his:ButtonToolBarTag>
</his:TransactionContainer>
<html:hidden name="PatientNotAttendedFB" property="hmode" />
<html:hidden name="PatientNotAttendedFB" property="patCrNo" />
<html:hidden name="PatientNotAttendedFB" property="serialNo" />
<html:hidden name="PatientNotAttendedFB" property="episodeCode" />
<html:hidden name="PatientNotAttendedFB" property="episodeVisitNo" />
<html:hidden name="PatientNotAttendedFB" property="isConfirmed" />
<html:hidden name="PatientNotAttendedFB" property="episodeActionCode" value="<%=RegistrationConfig.EPISODE_ACTION_CODE_NOT_ATTENDED %>" />

