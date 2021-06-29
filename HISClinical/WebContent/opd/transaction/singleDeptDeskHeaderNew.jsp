
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="/HISClinical/hisglobal/css/Color.css" rel="stylesheet" type="text/css">
<link href="/HISClinical/hisglobal/css/master.css" rel="stylesheet" type="text/css">
<link href="/HISClinical/hisglobal/css/hisStyle.css" rel="stylesheet" type="text/css">
<link href="/HISClinical/hisglobal/css/hisStyleExt.css" rel="stylesheet" type="text/css">
<link href="/HISClinical/hisglobal/css/calendar-blue2.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
 <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/fontawesome/css/all.css">
 <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/fontawesome/css/fontawesome.min.css">
<script language="javaScript" src="/HISClinical/hisglobal/js/commonFunctions.js"></script>
<script language="javaScript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djconfig="parseOnLoad: true"> </script>
<script type="text/javascript" src="/HISClinical/hisglobal/transactionutil/js/master.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/validation.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/util.js"></script>
<script type="text/javascript">
window.onload= function()
{
	setPatStats();
	setDeskHeader();
}

function setPatStats()
{	var jsonArray = updatePatientCounts();
	var arrayLength = jsonArray.length;
	var htmlStats = "";
	for (var j=0; j<arrayLength; j++) 
	{	
		var dataStats = jsonArray[j];
		if(dataStats!=null)
		{
			htmlStats += "<font color='#fffff' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b>";
			htmlStats += dataStats.header + "&nbsp;&nbsp;";
			for(var i=0; i<dataStats.counts.length; i++)
			{
				htmlStats += "<font color='#fffff' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";
				htmlStats += dataStats.counts[i].header + ":" ;
				if(i==0)
				{htmlStats += "<font color='#fffff' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";}
				if(i==1)
				{htmlStats += "<font color='#fffff' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";}
				if(i==2)
				{htmlStats += "<font color='#fffff' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";}				
				htmlStats += "<span class='badge' style='background-color: #33cc33'>"+dataStats.counts[i].counts+"</span>" + "&nbsp;&nbsp;";
			}
			htmlStats += "</b></font>";				
			if(j!=(arrayLength-1)){htmlStats;}
		}
		document.getElementById("divPatDeskStatistics").innerHTML = htmlStats;
	}
}

function setDeskHeader()
{	
	var htmlHeader = "";
	var _deskType = document.getElementsByName("deskType")[0].value
}


// AJAX Functions
function updatePatientCounts()
{
		var flg = false;
		var objPatientCount = null;
		var _mode = "AJX_G_PATIENTS_COUNT";
	
		var _deskType = document.getElementsByName("deskType")[0].value
		//alert(_deskType);
		if(_deskType==12)
		{
			var urlNew= "/HISClinical/opd/opdBayDeskHeaderNew.cnt?hmode="+_mode+'&deskType='+_deskType;
		}
		else if(_deskType==3)
		{
			var urlNew= "/HISClinical/opd/ipdNursingDeskHeaderNew.cnt?hmode="+_mode+'&deskType='+_deskType;
		}
		
		var objXHR = {url: createFHashAjaxQuery(urlNew), sync:true, postData: "", handleAs: "json",
			load: function(data) 
			{
				//alert("Data"+data.header +" "+data.counts);
				objPatientCount = data;
				flg = true;
			},
	        error: function(error)
	        {
	            //if(typeof objMenuMealTimeList == 'undefined' || objMenuMealTimeList==null || objMenuMealTimeList=="" || objMenuMealTimeList.length==0)
	            	//alert("No  Type");
	            alert(error+"Error while populating Information");
	            objPatientCount = null;
	            flg = false;
	        }};
	
		var objDojoAjax = dojo.xhrPost(objXHR);
		return objPatientCount;
}
</script>
<style type="text/css">
#doctorDeskHeader {
		
		font-weight: bold;
		font-family: arial;
		font-size: 14px;
		color: #FFFFFF;
		padding-top:2px;
		text-align: left;
		vertical-align:bottom;
		padding-left: 6px;
}

</style>
</head>

<body>
<form name="DoctorDeskFB" method="post" action="/HISClinical/opd/doctorDeskHeaderNew.cnt">
	<div class="container-fluid"  style="background:linear-gradient(to bottom,#3277b3,#88b5dd);border-radius:5px;">
		
		<div  id="doctorDeskHeader" class="col-xl-4 col-md-4 col-sm-4" >
		<i class='fas fa-user-md' style='font-size:20px'></i>
			<bean:define id="deskType" name="DoctorDeskFB" property="deskType" type="java.lang.String"></bean:define>	
			
			<%=DynamicDeskConfig.DESK_TYPES[Integer.parseInt(deskType)]%>
			
	
		</div>	
		<div align="right" class="col-xl-8 col-md-8 col-sm-8" id="divPatDeskStatistics">
		
		</div>
		
	</div>

<html:hidden name="DoctorDeskFB" property="hmode"/>
<html:hidden name="DoctorDeskFB" property="deskType"/>
</form>

</body>