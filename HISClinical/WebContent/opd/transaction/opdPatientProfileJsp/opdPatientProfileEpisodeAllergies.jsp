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
	var allergyTypeNameValue=document.getElementsByName("allergyTypeName")[index].value;
	var reasonNameValue=document.getElementsByName("reasonName")[index].value;
	var remarksValue=document.getElementsByName("remarks")[index].value
	var sensitivityNameValue=document.getElementsByName("sensitivityName")[index].value;
	var entryDateValue=document.getElementsByName("entryDate")[index].value
	arrayObj[index]="<tr><td><div width='20%' align='center'>"+allergyTypeNameValue+"</div></td>"+
					"<td><div width='20%' align='center'>"+reasonNameValue+"</div></td>"+
					"<td><div width='20%' align='center'>"+remarksValue+"</div></td>"+
					"<td><div width='20%' align='center'>"+sensitivityNameValue+"</div></td>"+
					"<td><div width='20%' align='center'>"+entryDateValue+"</div></td></tr>";
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
			"<table><tr align='left'><h3> ALLERGIES DETAIL </h3></tr></table>"+
			"<table  width='100%'><tr><td><div width='20%' align='center'><b>Allergy Type</b></div></td>"+
			"<td><div width='20%' align='center'><b>Reason</b></div></td>"+
			"<td><div width='20%' align='center'><b>Remarks</b></div></td>"+
			"<td><div width='20%' align='center'><b>Sensitivity</b></div></td>"+
			"<td><div width='20%' align='center'><b>Visit Date</b></div></td></tr>";
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
				<bean:message key="allergiesDetail"/></b>
				</font>
				</div>
	</his:SubTitleTag>
	<logic:notEmpty name="<%=OpdConfig.OPD_PATIENT_PROFILE_EPISODEALLERGIESVO_ARRAY%>" >
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
				<b><bean:message key="allergyType"/></b>
				</font>
				</div>
				</td>
				
				<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="reason"/></b>
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
				<b><bean:message key="sensitivity"/></b>
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
		<logic:iterate id="episodeAllergiesVoArray" name="<%=OpdConfig.OPD_PATIENT_PROFILE_EPISODEALLERGIESVO_ARRAY%>" indexId="id" type="hisglobal.vo.EpisodeAllergiesVO" >
			<tr>
				<td width="5%"  class="tdfont">
					<div align="center">	           
					<html:checkbox name="OpdPatientProfileFB" property="selectedRow" tabindex="1" value="<%=String.valueOf(id.intValue()) %>" onclick="addRow(this);" ></html:checkbox>
					</div>
	  				</td>  
				<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeAllergiesVoArray" property="allergyTypeName" />
					<html:hidden name="episodeAllergiesVoArray" property="allergyTypeName" />
					</div>
	  				</td>   
	  			<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeAllergiesVoArray" property="reasonName" />
					<html:hidden name="episodeAllergiesVoArray" property="reasonName" />
					</div>
	  				</td>   
	  			<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeAllergiesVoArray" property="remarks" />
					<html:hidden name="episodeAllergiesVoArray" property="remarks" />
					</div>
	  				</td>   
	  				
	  			<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeAllergiesVoArray" property="sensitivityName" />
					<html:hidden name="episodeAllergiesVoArray" property="sensitivityName" />
					</div>
	  				</td>   
	  		<td width="20%"  class="tdfont">
					<div align="center">	           
					<bean:write name="episodeAllergiesVoArray" property="entryDate" />
					<html:hidden name="episodeAllergiesVoArray" property="entryDate" />
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
	        	 <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitForm('SHOWPROFILEALLERGIES')" onkeypress="if(event.keyCode==13) submitForm('SHOWPROFILEALLERGIES');">
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