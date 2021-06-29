<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.vo.PatientAlertsDetailVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="registration.RegistrationConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function submitForm(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

function check()
{
	if(document.getElementsByName("hiddenPatDeadStatus")[0].value==<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>)
		return true;
	else
		return false;	
}
function validateAdd()
{
	var valid=true;
	if(!check())
	{
		if(document.getElementsByName("patAlertId")[0].value=="-1")
		{
			alert("Please Select The Chronic Disease");
			document.getElementsByName("patAlertId")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("durationDate")[0].value=="")
		{
			alert("Please Enter The Duration in Days");
			document.getElementsByName("durationDate")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("durationDate")[0].value > checkDuration() )
		{
			alert("Duration Day Cannot Be Greater Than "+document.getElementsByName("dayOnAge")[0].value);
			document.getElementsByName("durationDate")[0].focus();
			valid=false;
		}
		/*else if(document.getElementsByName("remarks")[0].value=="")
		{
			alert("Please Enetr The Remarks");
			document.getElementsByName("remarks")[0].focus();
			valid=false;
		}*/
	}
	else
	{
		alert("Patient is Dead.");
		valid=false;
	}	
	return valid;
}	

function deleteRow(obj1,obj2)
{
	document.getElementsByName("hiddenAlertName")[0].value=obj1;
	document.getElementsByName("hiddenPatAlertId")[0].value=obj2;
	submitForm('DELETEROW');
}

function revokeAlerts(obj1,obj2)
{
	var alertId=obj1;
	var index=obj2;
	document.getElementsByName("revokeAlertId")[0].value=alertId;
	document.getElementsByName("index")[0].value=index;
	if(document.getElementsByName("revokeRemarks")[index].value=="")
	{
		alert("Please Enter The Revoke Remarks");
		document.getElementsByName("revokeRemarks")[index].focus();
		return false;
	}
	else
	{
		submitForm('REVOKE');
	}	
}

function getAllAlerts(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var path='/HISClinical/opd/genericPatientAlert.cnt?hmode=ALLALERT&patCrNo='+patCrNo;
	openPopup(createFHashAjaxQuery(path),event,400,800);
}

function showDayCalculator(event)
{
	var path='/HISClinical/opd/genericPatientAlert.cnt?hmode=DAYCAL';
	openPopup(createFHashAjaxQuery(path),event,200,500);
}

function checkDuration()
{
	var days=0;
	var dob=document.getElementsByName("dob")[0].value;
	var sDate=document.getElementsByName("sysDate")[0].value;
	var dobArray=dob.split("-");
	
	var dobday=dobArray[0];
    var dobmonth=dobArray[1];
    var dobyear=dobArray[2];
    var dobdate=new Date(dobmonth +" "+ dobday+" "+dobyear);
    
    var sysArray=sDate.split("-");
	
	var sysday=sysArray[0];
    var sysmonth=sysArray[1];
    var sysyear=sysArray[2];
    var sysdate=new Date(sysmonth +" "+ sysday+" "+sysyear);
   
    days=(sysdate-dobdate)/86400000;
    document.getElementsByName("dayOnAge")[0].value=days;
    return days;
}
</script>
<his:TransactionContainer>
<his:TitleTag name="Patient Chronic Disease">
</his:TitleTag>
 <bean:define name="GenericPatientAlertFB" property="sysDate" id="sDate" type="java.lang.String"/>
<%
         if(sDate==null||sDate.equalsIgnoreCase("")){  
         	Date dt=(Date)session.getAttribute(Config.SYSDATEOBJECT);
         	sDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
         }
%>
<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>

<his:statusTransactionInProcess>
	
	
	<%PatientAlertsDetailVO[] patAssignedAlertVO=(PatientAlertsDetailVO[])session.getAttribute(OpdConfig.ARR_ASSIGNED_PATIENT_ALERT_VO);
		if(patAssignedAlertVO.length>0){
	%>
		<his:SubTitleTag name="Current Chronic Disease">
		</his:SubTitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td width="30%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="chronicDisease"/>
								</b>
							</font>	
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="duration"/>
									
								</b>
							</font>	
						</div>
					</td>
					<td width="30%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<font color="#FF0000">*</font>
									<bean:message key="revoke"/>
									<bean:message key="remarks"/>
								</b>
							</font>	
						</div>
					</td>
					
					<td width="15%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="revoke"/>
								</b>
							</font>	
						</div>
					</td>
				</tr>
				<logic:iterate id="assignedPatAlert" name="<%= OpdConfig.ARR_ASSIGNED_PATIENT_ALERT_VO%>" type="hisglobal.vo.PatientAlertsDetailVO" indexId="idx">
					<tr>
						<td class="tdfont">
							<div align="center">
								<%=assignedPatAlert.getAlertName() %>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<%=assignedPatAlert.getDurationDays() %>
							</div>
						</td>
						<td class="tdfont">
							<div align="center">
								<html:text name="GenericPatientAlertFB" property="revokeRemarks" value="" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
							</div>
						</td>
						
						<td class="tdfont">
							<div align="center">
								<img class="button" id="revokeButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/minus_new.png"/>'  title="Revoke Alerts" onclick="revokeAlerts('<%=assignedPatAlert.getPatAlertId() %>','<%=idx.toString() %>')">
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>	
		</his:ContentTag>
	<%} %>
	
	<his:SubTitleTag name="Patient Chronic Disease Detail">
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<a style="cursor:pointer" onclick="getAllAlerts(event)" >	
									<bean:message key="all"/>
									<bean:message key="chronicDisease"/>
								</a>	
							</b>
						</font>
					</div>
				</td>
			</tr>
		</table>
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="chronicDisease"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="25%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="duration"/>
								<bean:message key="dDays"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="30%"  class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								
								<bean:message key="remarks"/>
							</b>	
						</font>
					</div>
				</td>
				<td width="15%"  class="tdfonthead"></td>						
			</tr>
			<tr>
				<td width="30%" class="tdfont">	
					<div align="center">
						<html:select name="GenericPatientAlertFB" property="patAlertId" tabindex="1">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST%>" >
							<html:options collection="<%= OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
				</td>
				<td width="25%" class="tdfont">	
					<div align="center">
						<html:text name="GenericPatientAlertFB" property="durationDate" onkeypress="return validateNumeric(event)"  maxlength="5" size="5" tabindex="1"></html:text>
						<img class="button" src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Day Calculator " onclick ="showDayCalculator(event)" onkeypress="if(event.keyCode==13) showDayCalculator(event);">
					</div>
				</td>	
				<td width="30%" class="tdfont">	
					<div align="center">
						<html:text name="GenericPatientAlertFB" property="remarks" maxlength="100" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
					</div>
				</td>
				<td width="15%" class="tdfont">	
					<div align="center">
						<img class="button" src='<his:path src="/hisglobal/images/sign-plus.png"/>' style='cursor:pointer' onclick ="if(validateAdd()) submitForm('ADDROW')" onkeypress="if(event.keyCode==13) if(validateAdd()) submitForm('ADDROW');">
					</div>
				</td>
			</tr>
			<%if(session.getAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT) !=null){ %>
				<logic:iterate id="addedAlert" name="<%=OpdConfig.ARR_ADDED_PATIENT_ALERT %>" type="hisglobal.vo.PatientAlertsDetailVO">
					<tr>
						<td class="tdfont" width="30%" >
							<div align="center">
								<%=addedAlert.getAlertName() %>
							</div>
						</td>
						<td class="tdfont" width="25%" >
							<div align="center">
								<%=addedAlert.getDurationDate() %>
							</div>
						</td>
						<td class="tdfont" width="30%" >
							<div align="center">
								<%=addedAlert.getRemarks()%>
							</div>
						</td>
						<td class="tdfont" width="15%" >
							<div align="center">
								<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('<%=addedAlert.getAlertName() %>','<%=addedAlert.getPatAlertId() %>') ;" onclick=" deleteRow('<%=addedAlert.getAlertName() %>','<%=addedAlert.getPatAlertId() %>')">
								<html:hidden name="GenericPatientAlertFB" property="hiddenAlertName"/>
								<html:hidden name="GenericPatientAlertFB" property="hiddenPatAlertId"/>
							</div>
						</td>
					</tr>		
				</logic:iterate>
			<%} %>
		</table>
	</his:ContentTag>		

</his:statusTransactionInProcess>
	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
		 <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer onclick ="if(validateAdd()) submitForm('SAVE')" onkeypress="if(event.keyCode==13) if(validateAdd()) submitForm('SAVE')") tabindex="1">
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')" tabindex="1">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1">
		</his:statusTransactionInProcess>
		<his:statusUnsuccessfull>
	  		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')" tabindex="1">
		</his:statusUnsuccessfull>
		
	</his:ButtonToolBarTag>


<html:hidden name="GenericPatientAlertFB" property="hmode" />
<html:hidden name="GenericPatientAlertFB" property="patCrNo" />
<html:hidden name="GenericPatientAlertFB" property="revokeAlertId"/>
<html:hidden name="GenericPatientAlertFB" property="index"/>
<html:hidden name="GenericPatientAlertFB" property="dob"/>
<html:hidden name="GenericPatientAlertFB" property="sysDate" value="<%=sDate%>"/>						
<html:hidden name="GenericPatientAlertFB" property="dayOnAge"/>
<html:hidden name="GenericPatientAlertFB" property="hiddenPatDeadStatus"/>

</his:TransactionContainer>