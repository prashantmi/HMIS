<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="mrd.EMRConfig"%>
<%@page import="ehr.chronicdisease.presentation.EHRSection_ChronicDiseaseFB"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <style>
@-moz-keyframes blink {0%{opacity:1;} 50%{opacity:0;} 100%{opacity:1;}} /* Firefox */
@-webkit-keyframes blink {0%{opacity:1;} 50%{opacity:0;} 100%{opacity:1;}} /* Webkit */
@-ms-keyframes blink {0%{opacity:1;} 50%{opacity:0;} 100%{opacity:1;}} /* IE */
@keyframes blink {0%{opacity:1;} 50%{opacity:0;} 100%{opacity:1;}} /* Opera and prob css3 final iteration */
#Image {
-moz-transition:all 1s ease-in-out;
-webkit-transition:all 1s ease-in-out;
-o-transition:all 1s ease-in-out;
-ms-transition:all 1s ease-in-out;
transition:all 1s ease-in-out;
/* order: name, direction, duration, iteration-count, timing-function */  
-moz-animation:blink normal 2s infinite ease-in-out; /* Firefox */
-webkit-animation:blink normal 2s infinite ease-in-out; /* Webkit */
-ms-animation:blink normal 2s infinite ease-in-out; /* IE */
animation:blink normal 2s infinite ease-in-out; /* Opera and prob css3 final iteration */
}​
</style> -->	
</head>
<script>

function openRespectiveDeskTab(e)
{
	document.getElementById("ImageForChronicDisease").style.display="block";
	var deskMenuName = "Patient Chronic Disease";
	var deskMenuURL ="GENERICPATIENTALERTS";
	var deskMenuId = "107";
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var episodeCode=document.getElementsByName("episodeCode")[0].value;
	var episodeVisitNo=document.getElementsByName("episodeVisitNo")[0].value;
	var departmentUnitCode=document.getElementsByName("departmentUnitCode")[0].value;
	var roomCode=document.getElementsByName("roomCode")[0].value;
	document.getElementsByName("callFromDesk")[0].value = true;
	
	redirectRespectiveTab(deskMenuName,deskMenuURL,deskMenuId);
	
}

function SaveDataChronic()
{
//alert("hello");
 	var PatCrNo=document.getElementsByName("patCrNo")[0].value; 
 	var Ftlvalue=document.getElementsByName("ftlValueForChrnonicDisease")[0].value; 
 	//var ftlvalue="";
 	//alert(Ftlvalue);
 
	$.ajax({
	url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_PAT_CHRONIC_DISEASE.cnt?hmode=PAT_CHRONIC_DISEASE_AJAX&patCrNo="+PatCrNo+""),  
    type : 'GET',
    datatype: "json",
    async : false,
	success: function(data)
	{
		 //alert(data);


       document.getElementsByName('ftlValueForChrnonicDisease')[0].value  = data;

 

		document.getElementById('chronicDisease').innerHTML = data;
		
		
      },
	
      error: function(data)
      {
    	    alert('request failed :');
    	}

	});
	/* return JSON.parse(response);
	 }(); */
	}


</script>

<body>
<form id="formChronicDisease" action="/ehrComposition_PAT_CHRONIC_DISEASE" >
<table class="table">
	<tr>
		<td width="15%" style="font-size:1.2em;font-weight:bold;">
			Chronic Disease&nbsp;
		</td>
		<td width="85%"> 
			<img id="ImageForChronicDisease" src="/HISClinical/hisglobal/images/icon_refresh.gif"  onclick="SaveDataChronic(event);"  style="cursor:pointer;cursor:hand;"/> 
		</td>
	</tr>
	<!-- <tr>
		<td colspan="2">
  			<a onclick="openRespectiveDeskTab()" href="#" style="border:0;outline:none;"><b>Go To Respective Tab&nbsp;&nbsp;</b></a>
  		</td>
  	</tr> -->
	<tr>
		<td colspan="2">
			<div id = "chronicDisease">
			  <bean:write name='EHRSection_ChronicDiseaseFB' property='ftlValueForChrnonicDisease'filter='false'/>
			</div>
  		</td>
  	</tr>
</table>
<html:hidden name="EHRSection_ChronicDiseaseFB" property="patCrNo"/>
<html:hidden name="EHRSection_ChronicDiseaseFB" property="episodeCode"/>
<html:hidden name="EHRSection_ChronicDiseaseFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_ChronicDiseaseFB" property="departmentUnitCode"/>
<html:hidden name="EHRSection_ChronicDiseaseFB" property="roomCode"/>
<html:hidden name="EHRSection_ChronicDiseaseFB" property="ftlValueForChrnonicDisease"/>
<html:hidden name="EHRSection_ChronicDiseaseFB" property="callFromDesk"/>
</form>
</body>