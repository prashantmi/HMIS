<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/popup.js" />

<his:javascript src="/opd/opdJs/opd.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
var htmlText="";
var arrayObj;
 

function addRow(obj)
{
	
	var index=obj.value;
	
	if(obj.checked)
	{
	var dianosticCodeValue=document.getElementsByName("diagnosticCode")[index].value;
	var dignosisNameValue=document.getElementsByName("dignosisName")[index].value;
	var diagnosticTypeNameValue=document.getElementsByName("diagnosticTypeName")[index].value
	var remarksValue=document.getElementsByName("remarks")[index].value;
	var episodeDateValue=document.getElementsByName("episodeDate")[index].value
	arrayObj[index]="<tr><td><div width='20%' align='center'>"+dianosticCodeValue+"</div></td>"+
					"<td><div width='20%' align='center'>"+dignosisNameValue+"</div></td>"+
					"<td><div width='20%' align='center'>"+diagnosticTypeNameValue+"</div></td>"+
					"<td><div width='20%' align='center'>"+remarksValue+"</div></td>"+
					"<td><div width='20%' align='center'>"+episodeDateValue+"</div></td></tr>";
	}
	else
	{
		arrayObj[index]="";
	}
}

function callThisOnload()
{ 
	arrayObj=new Array(document.getElementsByName("episodeDate").length);
	// alert("patProfileHeader "+document.getElementsByName("patProfileHeader")[0].value);
	// alert("selectedEpisode "+document.getElementsByName("episodeDate").length);
	htmlText=document.getElementsByName("htmlValue")[0].value+
			"<table><tr align='left'><h3> DIAGNOSIS DETAIL </h3></tr></table>"+
			"<table  width='100%'><tr><td><div width='20%' align='center'><b>DiagnosticCode</b></div></td>"+
			"<td><div width='20%' align='center'><b>DignosisName</b></div></td>"+
			"<td><div width='20%' align='center'><b>DiagnosticTypeName</b></div></td>"+
			"<td><div width='20%' align='center'><b>Remarks</b></div></td>"+
			"<td><div align='center'><b>VisitDate</b></div></td></tr>";
}

</script>
<his:TitleTag width="100%">
		<his:name>
			<bean:message key="patientProfile" />
		</his:name>
	</his:TitleTag>
<his:statusTransactionInProcess>
<his:SubTitleTag name=""  >
	    		<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b>
				<bean:message key="diagnosisDetail"/></b>
				</font>
				</div>
	</his:SubTitleTag>
	<logic:notEmpty name="<%=OpdConfig.OPD_PATIENT_PROFILE_EPISODEDIAGNOSISVO_ARRAY%>" >
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="5%"  class="tdfonthead">
				<div align="center">	
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<input type="checkbox" name="selectCheck" onclick="selectAll(this)"> 
				</font>
				</div>
				</td>
				<td width="20%"  class="tdfonthead">
				<div align="center">	   
				   
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="diagnosisCode"/></b>
				</font>
				</div>
				</td>
				
				<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="diagnosisName"/></b>
				</font>
				</div>
				</td>
				
				<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="diagonosisType"/></b>
				</font>
				</div>
				</td>
				
				<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="remarks"/></b>
				</font>
				</div>
				</td>
				
				<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="visitDate"/></b>
				</font>
				</div>
				</td>
			</tr>
		</table>
		<table width="100%" cellpadding="0" cellspacing="0">
		<logic:iterate id="episodeDiagnosisVoArray" name="<%=OpdConfig.OPD_PATIENT_PROFILE_EPISODEDIAGNOSISVO_ARRAY%>" indexId="id" type="hisglobal.vo.EpisodeDiagnosisVO" >
			<tr>
				<td width="5%"  class="tdfont">
					<div align="center">	           
					<html:checkbox name="OpdPatientProfileFB" property="selectedRow" value="<%=String.valueOf(id.intValue()) %>" onclick="addRow(this);" ></html:checkbox>
					</div>
	  				</td>  
				<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeDiagnosisVoArray" property="diagnosticCode" />
					<html:hidden name="episodeDiagnosisVoArray" property="diagnosticCode" />
					</div>
	  				</td>   
	  			<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeDiagnosisVoArray" property="dignosisName" />
					<html:hidden name="episodeDiagnosisVoArray" property="dignosisName" />
					</div>
	  				</td>   
	  			<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeDiagnosisVoArray" property="diagnosticTypeName" />
					<html:hidden name="episodeDiagnosisVoArray" property="diagnosticTypeName" />
					</div>
	  				</td>   
	  				
	  			<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeDiagnosisVoArray" property="remarks" />
					<html:hidden name="episodeDiagnosisVoArray" property="remarks" />
					</div>
	  				</td>   
	  		<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeDiagnosisVoArray" property="episodeDate" />
					<html:hidden name="episodeDiagnosisVoArray" property="episodeDate" />
					</div>
	  				</td>   
			</tr>
		</logic:iterate>
		</table>
	</his:ContentTag>
	</logic:notEmpty>
</his:statusTransactionInProcess>

<his:ButtonToolBarTag>
<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				 <td width="20%" style="background-color:#FFFFFF;background-repeat:repeat-x">
				 <div align="left">	           
				 <his:statusTransactionInProcess>
			 	 <img class="button" src='<his:path src="/hisglobal/images/backward.gif"/>' tabindex="1" style=cursor:pointer onkeypress="if(event.keyCode==13)submitForm('SHOWPROFILEMENU');" onclick ="submitForm('SHOWPROFILEMENU');" tabindex="1"/>
				 </his:statusTransactionInProcess> 
				 
				 </div>
				 </td >
				 <td width="20%"  style="background-color:#FFFFFF;background-repeat:repeat-x">
				 <div align="center">	           
				 <img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) showHtml();" tabindex="1" onclick ="showHtml();">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	        	 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('SHOWPROFILEDIAGNOSIS')" onkeypress="if(event.keyCode==13) submitForm('SHOWPROFILEDIAGNOSIS');">
				 </div>
				 </td>
				 <td width="20%"  style="background-color:#FFFFFF;background-repeat:repeat-x">
				 <div align="right">	           
				 
				 <his:statusTransactionInProcess>
				  <img class="button" src='<his:path src="/hisglobal/images/forwardward.gif"/>' tabindex="1" style=cursor:pointer onkeypress="if(event.keyCode==13)saveHtml();" onclick="saveHtml();" tabindex="1"/>
				 </his:statusTransactionInProcess>
				 </div>
				 </td>
			</tr>
</table>

</his:ButtonToolBarTag>

 
<input type="hidden" name="hmode" value="">
<html:hidden name="OpdPatientProfileFB" property="patCrNo"/>
<html:hidden name="OpdPatientProfileFB" property="profileType"/>
<html:hidden name="OpdPatientProfileFB" property="selectedEpisode"/>
<html:hidden name="OpdPatientProfileFB" property="patProfileHeader"/>
<html:hidden name="OpdPatientProfileFB" property="patProfileRemark"/>
<html:hidden name="OpdPatientProfileFB" property="effectiveFrom"/>
<html:hidden name="OpdPatientProfileFB" property="effectiveTo"/>
<html:hidden name="OpdPatientProfileFB" property="htmlValue"/>
<html:hidden name="OpdPatientProfileFB" property="fromDate"/>
<html:hidden name="OpdPatientProfileFB" property="toDate"/>