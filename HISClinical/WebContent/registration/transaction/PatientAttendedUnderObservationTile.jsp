<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import ="java.util.*,registration.*,hisglobal.vo.*"%>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/time.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script><!--
function validateForm()
{
	if(isEmpty(document.getElementsByName('remarks')[0],'Remarks'))
		return true;
	else
		return false
}
</script>
<his:TransactionContainer>
<his:TitleTag name="Kept Under Observation">
	
</his:TitleTag>
<jsp:include page="/registration/patientDetail.cnt" flush="true"></jsp:include>

     
    
 <div id = "att_tret_disposed" > 
 <his:ContentTag>
    <table width="100%" border="0" cellspacing="1" cellpadding="1">
 
  <tr> 
		     <td class="tdfonthead" width = "15%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <font color="#FF0000">*</font> <bean:message key="remarks"/></font></div></td>
		        <td class="tdfont" width="35%">
		        <html:textarea styleClass="textarea2" tabindex="1" name="PatientAttendedUnderObservationFB" property="remarks" onkeypress="return CheckMaxLength(event,this,300)"  />
		        
             </td>            
				<td class="tdfonthead" width = "15%"><div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			        <bean:message key="complaintdtl"/></font></div></td>
			        <td class="tdfont" width="35%">
			        <html:textarea styleClass="textarea2" tabindex="1" name="PatientAttendedUnderObservationFB" property="complainDtl" onkeypress="return CheckMaxLength(event,this,500)"  />		        
                </td>   
  </tr>           
            
          </table>
          </his:ContentTag>          
         
</his:TransactionContainer>         
<his:ButtonToolBarTag>
			<img class='button'	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex='1' onclick="submitFormOnValidate(validateForm(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateForm(),'SAVE');")>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusUnsuccessfull>
	
	
</his:ButtonToolBarTag>

  <html:hidden name="PatientAttendedUnderObservationFB" property="hmode" />
  <html:hidden  name='PatientAttendedUnderObservationFB' property='episodeActionCode' value="<%=RegistrationConfig.EPISODE_ACTION_CODE_ATTENDED_OBSERVATION %>"/>
  <html:hidden  name='PatientAttendedUnderObservationFB' property='episodeActionDate' />
  <html:hidden  name='PatientAttendedUnderObservationFB' property='episodeActionTime' />
  <html:hidden name="PatientAttendedUnderObservationFB" property="patCrNo" />
<html:hidden name="PatientAttendedUnderObservationFB" property="serialNo" />
<html:hidden name="PatientAttendedUnderObservationFB" property="episodeCode" />
<html:hidden name="PatientAttendedUnderObservationFB" property="episodeVisitNo" />
  

 
    