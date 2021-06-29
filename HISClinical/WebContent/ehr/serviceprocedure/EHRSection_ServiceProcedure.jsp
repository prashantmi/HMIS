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

function openRespectiveDeskTabForServiceProcedure()
{
	var deskMenuName = "Service Procedure";
	var deskMenuURL ="SERVICEREQUISITION";
	var deskMenuId = "15";
	redirectRespectiveTab(deskMenuName,deskMenuURL,deskMenuId);
	document.getElementById("ImageRefreshForServiceProcedure").style.display="block";
}

function getServiceProcedureData()
{
//alert("hello");
 	var PatCrNo=document.getElementsByName("patCrNo")[0].value; 
 	var Ftlvalue=document.getElementsByName("ftlValueForServiceProcedure")[0].value; 
 	//var ftlvalue="";
 	//alert(Ftlvalue);
 
	$.ajax({
	url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_SERVICE_PROCEDURE.cnt?hmode=SERVICE_PROCEDURE_AJAX&patCrNo="+PatCrNo+""),  
    type : 'GET',
    datatype: "json",
    async : false,
	success: function(data)
	{
		 
       document.getElementsByName('ftlValueForServiceProcedure')[0].value  = data;

		document.getElementById('serviceProcedure').innerHTML = data;	
		
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
		<td width="15%" style="font-size:1.2em;font-weight:bold;">
			Service Procedure&nbsp;
		</td>
		<td width="85%"> 
			<img id="ImageRefreshForServiceProcedure" src="/HISClinical/hisglobal/images/icon_refresh.gif"  onclick="getServiceProcedureData(event);"  style="cursor:pointer;cursor:hand;"/> 
		</td>
	</tr>
	<!-- <tr>
		<td colspan="2">
  			<a onclick="openRespectiveDeskTabForServiceProcedure()" href="#" style="font-size:12px;"><b>Go To Respective Tab</b></a>
 		 </td>
  	</tr> 	 -->
	<tr>
		<td colspan="2">
			<div id = "serviceProcedure">
			  <bean:write name='EHRSection_ServiceProcedureFB' property='ftlValueForServiceProcedure'filter='false'/>
			</div>
 		 </td>
  	</tr> 	
</table>  
<html:hidden name="EHRSection_ServiceProcedureFB" property="patCrNo"/>
<html:hidden name="EHRSection_ServiceProcedureFB" property="episodeCode"/>
<html:hidden name="EHRSection_ServiceProcedureFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_ServiceProcedureFB" property="departmentUnitCode"/>
<html:hidden name="EHRSection_ServiceProcedureFB" property="roomCode"/>
<html:hidden name="EHRSection_ServiceProcedureFB" property="ftlValueForServiceProcedure"/>
</body>
</html>

