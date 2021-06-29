<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="mrd.EMRConfig"%>
<%@page import="emr.dataentry.spp.presentation.fb.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		 <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
	 

</head>
<script>

function openRespectiveDeskTabForPatReferral()
{
	var deskMenuName = "Patient Referral";
	var deskMenuURL ="DESKREFERPATIENT";
	var deskMenuId = "8";
	redirectRespectiveTab(deskMenuName,deskMenuURL,deskMenuId);
	document.getElementById("ImageRefreshForRefer").style.display="block";
}

function SaveDataReferral()
{
//alert("hello");
 	var PatCrNo=document.getElementsByName("patCrNo")[0].value; 
 	var Ftlvalue=document.getElementsByName("ftlValueForPatReferral")[0].value; 
 	//var ftlvalue="";
 	//alert(Ftlvalue);
 
	$.ajax({
	url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_PAT_REFERRAL.cnt?hmode=PAT_REFERRAL_AJAX&patCrNo="+PatCrNo+""),  
    type : 'GET',
    datatype: "json",
    async : false,
	success: function(data)
	{
		 
       document.getElementsByName('ftlValueForPatReferral')[0].value  = data;

		document.getElementById('patientReferral').innerHTML = data;	
		
      },
	
      error: function(data)
      {
    	    alert('request failed :');
    	}

	});
	
	}

</script>
<body>
<table class="table">
	<tr>
		<td width="15%" style="font-size:1.2em;font-weight:bold;">
			Patient Referral&nbsp;
		</td>
		<td width="85%"> 
			<img id="ImageRefreshForRefer" src="/HISClinical/hisglobal/images/icon_refresh.gif"  onclick="SaveDataReferral(event);"  style="cursor:pointer;cursor:hand;"/> 
		</td>
	</tr>
	<!-- <tr>
		<td colspan="2">
  			<a onclick="openRespectiveDeskTabForPatReferral()" href="#" style="font-size:12px;"><b>Go To Respective Tab</b></a>
 		 </td>
  	</tr> 	 -->
	<tr>
		<td colspan="2">
			<div id = "patientReferral">
			  <bean:write name='EHRSection_PatientReferralFB' property='ftlValueForPatReferral'filter='false'/>
			</div>
 		 </td>
  	</tr> 	
</table>  
<html:hidden name="EHRSection_PatientReferralFB" property="patCrNo"/>
<html:hidden name="EHRSection_PatientReferralFB" property="episodeCode"/>
<html:hidden name="EHRSection_PatientReferralFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_PatientReferralFB" property="departmentUnitCode"/>
<html:hidden name="EHRSection_PatientReferralFB" property="roomCode"/>
<html:hidden name="EHRSection_PatientReferralFB" property="ftlValueForPatReferral"/>
</body>