<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>	
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.vo.PatientMonitoringMstVO"%>

<%@page import="opd.OpdConfig"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="java.util.List"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function submitForm(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

function validateAdd()
{
	var valid=true;
	if(document.getElementsByName("paraId")[0].value=="-1")
	{
		alert("Please Select the Vital");
		document.getElementsByName("paraId")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("monitorMode")[0].value=="-1")
	{
		alert("Please Select The Duration");
		document.getElementsByName("monitorMode")[0].focus();
		valid=false;
	}
	return valid;
}	

function deleteRow(obj1,obj2)
{
	document.getElementsByName("hiddenParaName")[0].value=obj1;
	document.getElementsByName("hiddenParaId")[0].value=obj2;
	submitForm('DELETEROW');
}	

function enabledField()
{
	var len=document.getElementsByName("modChk").length;
	
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("modChk")[i].checked)
		{
			document.getElementsByName("modMonitorMode")[i].disabled=false;
			document.getElementsByName("modRemarks")[i].disabled=false;
			document.getElementsByName("arrSerialNo")[i].disabled=false;
		}
		else
		{
			document.getElementsByName("modMonitorMode")[i].disabled=true;
			document.getElementsByName("modRemarks")[i].disabled=true;
			document.getElementsByName("arrSerialNo")[i].disabled=true;
		}
	}
}
window.onload = function()
{
	enabledField();
}

function revokeVitals(obj)
{
	if(document.getElementsByName("hiddenPatDeadStatus")[0].value==<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>)
	{
		alert("Patient is dead");
		return false;
	}
	
	var diagCode=obj;
	document.getElementsByName("revokeParaId")[0].value=diagCode;
	submitForm('REVOKE');
}

function saveValidation()
{
	
	if(document.getElementsByName("hiddenPatDeadStatus")[0].value==<%=RegistrationConfig.PATIENT_STATUS_CODE_DEAD%>)
	{
		alert("Patient is dead");
		return false;
	}
	
	var len=document.getElementsByName("modChk").length;
	var count=0;
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("modChk")[i].checked)
		{
			count++;
		}
	}
	//alert("insideeeeeeeeeee");
	if(count==0)
	{
		if(typeof document.getElementsByName("paraId")[0] != 'undefined')
		{
			if(!validateAdd())
			return false;
		}
	}
	else
	{
		for(i=0;i<len;i++)
		{
			if(document.getElementsByName("modChk")[i].checked && document.getElementsByName("modMonitorMode")[i].value=="-1")
			{
				alert("Please Select The Duration");
				document.getElementsByName("modMonitorMode")[i].focus();
				return false;
			}
		}
		
		if(typeof document.getElementsByName("paraId")[0] != 'undefined')
		{
			var paraId=document.getElementsByName("paraId")[0];
			var duration=document.getElementsByName("monitorMode")[0];
			var remarks=document.getElementsByName("vitalRemarks")[0];
			if(paraId.value !="-1" || duration.value !="-1" || remarks.value != "")
			{
				if(!validateAdd())
					return false;
			}
		}
		
	}
	return true;	
}

function getVitalCharting(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var path='/HISClinical/inpatient/monitorVitals.cnt?hmode=VITALCHART&patCrNo='+patCrNo;
	openPopup(createFHashAjaxQuery(path),event,400,800);
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:TitleTag name="Monitor Vitals">
	<logic:equal name="MonitorVitalsFB" property="vitalChartFlag" value="<%=OpdConfig.YES%>">
	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		<b>
			<a style="cursor:pointer" onclick="getVitalCharting(event)" >	
				<bean:message key="vitalchart"/>
			</a>	
		</b>
	</font>
	</logic:equal>
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />

<his:statusTransactionInProcess>

<%
	PatientMonitoringMstVO[] assignedPatVitals=(PatientMonitoringMstVO[]) session.getAttribute(InpatientConfig.SELECTED_PARAMETER_LIST);
	if(assignedPatVitals.length !=0){
%>
	<his:SubTitleTag name="Assigned Vital Details">
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%"  class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="vital"/>
						</b>	
					</font>
				</div>
			</td>
			<td width="20%"  class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<font color="#FF0000">*</font>
							<bean:message key="duration"/>
						</b>	
					</font>
				</div>
			</td>
			<td width="40%"  class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="remarks"/>
						</b>
					</font>
				</div>
			</td>
			<td width="5%"  class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="modify"/>
						</b>	
					</font>
				</div>
			</td>
			<td width="5%"  class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="revoke"/>
						</b>	
					</font>
				</div>
			</td>
			</tr>
			<logic:iterate id="assignedVitals" name="<%=InpatientConfig.SELECTED_PARAMETER_LIST %>" type="hisglobal.vo.PatientMonitoringMstVO">
				<tr>
					<td width="30%" class="tdfont" >
						<div align="center">
							<%=assignedVitals.getParaName() %>
						</div>
					</td>
					<td width="20%" class="tdfont" >
						<div align="center">
							<html:select name="MonitorVitalsFB" property="modMonitorMode" value="<%=assignedVitals.getMonitorMode() %>">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=InpatientConfig.ARR_MONITOR_MODE_VO%>">
								<html:options collection="<%= InpatientConfig.ARR_MONITOR_MODE_VO%>" property="modeId" labelProperty="modeName" />
								</logic:present>
							</html:select>
						</div>
					</td>
					<td width="40%" class="tdfont" >
						<div align="center">
							<html:text name="MonitorVitalsFB" property="modRemarks" value="<%=assignedVitals.getRemarks()%>" maxlength="500" size="50" onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
						</div>
					</td>
					<td width="5%"  class="tdfont">
						<div align="center">
							<html:checkbox name="MonitorVitalsFB" property="modChk" value="<%=assignedVitals.getParaId() %>" onclick="enabledField()"></html:checkbox>
							<html:hidden name="MonitorVitalsFB" property="arrSerialNo" value="<%=assignedVitals.getSerialNo() %>"/>
						</div>	
					</td>
					<td width="5%"  class="tdfont">
						<div align="center">
							<img class="button" id="revokeButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/Minus.png"/>'  title="Revoke Vitals" onclick="revokeVitals('<%=assignedVitals.getParaId() %>')">
						</div>	
					</td>
				</tr>
			</logic:iterate>
		</table>
	</his:ContentTag>		
	
<% } %>

<%
	List templateParaList= (List)session.getAttribute(InpatientConfig.TEMPLATE_PARAMETER_LIST);
	if(templateParaList.size()!=0 || session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR) !=null)
	{
%>
<his:SubTitleTag name="Vitals Detail">
</his:SubTitleTag>
<%		
	}
%>



<%
	//List templateParaList= (List)session.getAttribute(InpatientConfig.TEMPLATE_PARAMETER_LIST);
	if(templateParaList.size()!=0 || session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR) !=null)
	{
%>
<his:ContentTag>
	<table width="100%" cellpadding="0" cellspacing="1">
		<tr>
			<td width="30%"  class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<font color="#FF0000">*</font>
							<bean:message key="vital"/>
						</b>	
					</font>
				</div>
			</td>
			<td width="20%"  class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<font color="#FF0000">*</font>
							<bean:message key="duration"/>
						</b>	
					</font>
				</div>
			</td>
			<td width="40%"  class="tdfonthead">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<bean:message key="remarks"/>
						</b>	
					</font>
				</div>
			</td>
			<td width="10%"  class="tdfonthead"></td>						
		</tr>
	
		<logic:notEmpty name="<%=InpatientConfig.TEMPLATE_PARAMETER_LIST%>" >
		<tr>
			<td width="30%" class="tdfont">	
				<div align="center">
					<html:select name="MonitorVitalsFB" property="paraId">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=InpatientConfig.TEMPLATE_PARAMETER_LIST%>">
						<html:options collection="<%= InpatientConfig.TEMPLATE_PARAMETER_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
			</td>
			<td width="20%" class="tdfont">	
				<div align="center">
					<html:select name="MonitorVitalsFB" property="monitorMode">
						<html:option value="-1">Select Value</html:option>
						<logic:present name="<%=InpatientConfig.ARR_MONITOR_MODE_VO%>">
						<html:options collection="<%= InpatientConfig.ARR_MONITOR_MODE_VO%>" property="modeId" labelProperty="modeName" />
						</logic:present>
					</html:select>
				</div>
			</td>
			<td width="40%" class="tdfont">	
				<div align="center">
					<html:text name="MonitorVitalsFB" property="vitalRemarks" maxlength="500" size="50" onkeypress="return validateAlphaNumOnly(this,event)" ></html:text>
				</div>
			</td>
			<td width="10%" class="tdfont">	
				<div align="center">
					<img class="button" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' style='cursor:pointer' onclick ="if(validateAdd()) submitForm('ADDROW')" onkeypress="if(event.keyCode==13) if(validateAdd()) submitForm('ADDROW');">
				</div>
			</td>
		</tr>
		</logic:notEmpty>	
	</table>	
	</his:ContentTag>
	<%} %>		
	<his:ContentTag>	
		<table width="100%" cellpadding="0" cellspacing="1">
		<%if(session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR) !=null){ %>
		
			<logic:iterate id="patVital" name="<%=InpatientConfig.ARR_PAT_VITAL_MONITOR %>" type="hisglobal.vo.PatientMonitoringMstVO">
				<tr>
					<td class="tdfont" width="30%" >
						<div align="center">
							<%=patVital.getParaName()%>
						</div>
					</td>
					<td class="tdfont" width="20%" >
						<div align="center">
							<%=patVital.getMonitorModeName()%>
						</div>
					</td>
					<td class="tdfont" width="40%" >
						<div align="center">
							<%=patVital.getRemarks()%>
						</div>
					</td>
					<td class="tdfont" width="10%" >
						<div align="center">
							<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('<%=patVital.getParaName() %>','<%=patVital.getParaId() %>') ;" onclick=" deleteRow('<%=patVital.getParaName() %>','<%=patVital.getParaId() %>')">
							<html:hidden name="MonitorVitalsFB" property="hiddenParaName"/>
							<html:hidden name="MonitorVitalsFB" property="hiddenParaId"/>
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
	<%
		PatientMonitoringMstVO[] assignedPatVitals1=(PatientMonitoringMstVO[]) session.getAttribute(InpatientConfig.SELECTED_PARAMETER_LIST);
		List templateParaList1= (List)session.getAttribute(InpatientConfig.TEMPLATE_PARAMETER_LIST);
		if(assignedPatVitals1.length !=0 
				|| (templateParaList1.size()!=0 || session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR) !=null))
		{
	%>
		<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onclick ="if(saveValidation()) submitForm('SAVE')" onkeypress="if(event.keyCode==13) if(saveValidation()) submitForm('SAVE')") tabindex="1">
	<%	}	%>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')" tabindex="1">
	<%
		if(assignedPatVitals1.length !=0 
				|| (templateParaList1.size()!=0 || session.getAttribute(InpatientConfig.ARR_PAT_VITAL_MONITOR) !=null))
		{
	%>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1">
	<%	}	%>
	</his:statusTransactionInProcess>
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')" tabindex="1">
	</his:statusUnsuccessfull>
</his:ButtonToolBarTag>


<html:hidden name="MonitorVitalsFB" property="hmode"/>
<html:hidden name="MonitorVitalsFB" property="patCrNo"/>
<html:hidden name="MonitorVitalsFB" property="revokeParaId"/>
<html:hidden name="MonitorVitalsFB" property="hiddenPatDeadStatus"/>