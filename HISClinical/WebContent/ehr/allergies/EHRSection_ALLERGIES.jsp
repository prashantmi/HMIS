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


<script>

function openRespectiveDeskTabPatientAllergies()
{
	var deskMenuName = "Patient Allergies";
	var deskMenuURL ="DESKPATIENTALERGIES";
	var deskMenuId = "268";
	redirectRespectiveTab(deskMenuName,deskMenuURL,deskMenuId);
	document.getElementById("refreshImageForAllergies").style.display="block";
}
function openRespectiveDeskTabDrugAllergies()
{
	var deskMenuName = "Drug Allergies";
	var deskMenuURL ="DESKALLERGIES";
	var deskMenuId = "7";
	redirectRespectiveTab(deskMenuName,deskMenuURL,deskMenuId);
	document.getElementById("refreshImageForAllergies").style.display="block";
}

function SaveDataAllergies()
{
//alert("hello");
 	var PatCrNo=document.getElementsByName("patCrNo")[0].value; 
 	var Ftlvalue=document.getElementsByName("ftlValueForAllergies")[0].value; 
 	
 
	$.ajax({
	url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_PAT_ALLERGY.cnt?hmode=PAT_ALLERGY_AJAX&patCrNo="+PatCrNo+""),  
    type : 'GET',
    datatype: "json",
    async : false,
	success: function(data)
	{
		 //alert(data);


       document.getElementsByName('ftlValueForAllergies')[0].value  = data;

    

		document.getElementById('allergies').innerHTML = data;
		
      },
	
      error: function(data)
      {
    	    alert('request failed :');
    	}

	});
	
	}
</script>
</head>
<body>
<table class="table">
	<tr>
		<td width="10%" style="font-size:1.2em;font-weight:bold;">
			Allergies&nbsp;
		</td>
		<td width="90%"> 
			<img id="refreshImageForAllergies" src="/HISClinical/hisglobal/images/icon_refresh.gif"  onclick="SaveDataAllergies(event);"  style="cursor:pointer;cursor:hand;"/> 
		</td>
	</tr>
	<!-- <tr>
		<td colspan="2">
  			<a onclick="openRespectiveDeskTabPatientAllergies()" href="#" style="font-size:12px;"><b>Patient Allergy</b></a>
 		 </td>
  	</tr>
  	<tr>
  		<td colspan="2">
   			<a onclick="openRespectiveDeskTabDrugAllergies()" href="#" style="font-size:12px;"><b>Drug Allergy</b></a>
		</td>
	</tr>  -->
  	<tr>
  		<td colspan="2">
			<div id = "allergies">
			  <bean:write name='EHRSection_AllergiesFB' property='ftlValueForAllergies'filter='false'/>
			</div>
		</td>
	</tr>
</table>  
<html:hidden name="EHRSection_AllergiesFB" property="patCrNo"/>
<html:hidden name="EHRSection_AllergiesFB" property="episodeCode"/>
<html:hidden name="EHRSection_AllergiesFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_AllergiesFB" property="departmentUnitCode"/>
<html:hidden name="EHRSection_AllergiesFB" property="roomCode"/>
<html:hidden name="EHRSection_AllergiesFB" property="ftlValueForAllergies"/>
</body>

