<!-- 
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	Doctor Desk
## Process/Database Object Name	:	IPD Doctor Desk
## Purpose						:	To display a brief detail of admitted patient to IPD Doctor
## Date of Creation				: 	08-December-2014
## Modification Log				:					
##		Modify Date				: 	
##		Reason	(CR/PRS)		: 
##		Modify By				: 

 -->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link href="/HIS/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/easyui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/basic.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>

<script type='text/javascript' src='/HIS/hisglobal/masterutil/js/jquery/js/basic.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<!-- Metro JS -->
<script type="text/javascript" src="/HIS/hisglobal/eDesk/transactions/MetroJs/MetroJs.js"></script>
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/eDesk/transactions/MetroJs/MetroJs.css" />
<!-- end Metro JS -->

<!-- ScrollBar JS -->
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/js/jquery/scrollbar/css/prettify.css" />
<script type='text/javascript' src='/HIS/hisglobal/js/jquery/scrollbar/js/jquery.slimscroll.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/js/jquery/scrollbar/js/prettify.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/js/jquery/scrollbar/js/jquery.jquery.min.js'></script>
<!-- end ScrollBar -->

<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/eDesk/transactions/css/extrastyle.css" />

<!--  theme changer start-->
<link rel="stylesheet" type="text/css" title="blue" href="/HIS/hisglobal/css/buttons.css" />
<link rel="stylesheet" type="text/css" title="blue" href="/HIS/hisglobal/css/layout.css" />
<link rel="alternate stylesheet" type="text/css" title="green" href="/HIS/hisglobal/css/buttons-green.css" />
<link rel="alternate stylesheet" type="text/css" title="green" href="/HIS/hisglobal/css/layout-green.css" />
<link rel="stylesheet" type="text/css" title="normal" href="/HIS/hisglobal/css/normalfont.css" />
<link rel="alternate stylesheet" type="text/css" title="large" href="/HIS/hisglobal/css/largefont.css" />
<link rel="alternate stylesheet" type="text/css" title="small" href="/HIS/hisglobal/css/smallfont.css" />
<script type="text/javascript" src="/HISPis/hisglobal/js/themechanger.js"></script>
<!--  theme changer end-->

<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/validationCommon.js"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar-setup.js"/> 
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/opd/js/opd_desk_new.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript" src="/HISClinical/investigation/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<his:javascript src="/opd/js/opd_desk_new.js" />
<his:javascript src="/operationTheatre/js/showmodalfunctionality.js" />

<style type="text/css">
body div#content {
	max-width: 100%;
	margin: auto;
	left: 1%;
	right: 1%;
	position: absolute;
}

.fontBold {
  font-weight: bold;
}

.loading
{       
   background:Gray url(/HISPis/hisglobal/images/loading.gif) no-repeat center center;
}

.popUpDiv
{       
   height: 100%;
   width: 100%;
   opacity:0.5;
   position: absolute;
   z-index: 200;          
}

</style>

	
<script>
$(function() {
	deskPatInfo = getDeskPatInfoStr();
	parent.dynamicDeskControlPanelGenerator.addControlPanelMenuDetails('1', 'divDeskMenuPatLeft', 'LEFT', deskPatInfo);
	parent.dynamicDeskControlPanelGenerator.addControlPanelMenuDetails('1', 'divDeskMenuPatRight', 'RIGHT', deskPatInfo);
	parent.dynamicDeskControlPanelGenerator.addControlPanelMenuDetails('3', 'divDeskMenuPatBottom', 'BOTTOM', deskPatInfo);
	
	parent.updateDeskMenuState("1", deskPatInfo); 	// For Desk Only---
	//autoRefresh(); //for Refreshing Desk & Menus
	//$( "#allergies" ).resizable();
	$( "#deskTabs" ).accordion();

//SdeskMenuTileOnLoad();
});
</script>
<script type="text/javascript">
dojo.require("dijit.Dialog");
dojo.require("dijit.layout.TabContainer");
dojo.require("dijit.layout.ContentPane");
dojo.require("dijit.Toolbar");
dojo.require("dijit.form.Button");
dojo.require("dijit.layout.BorderContainer");
dojo.require("dijit.layout.AccordionContainer");
dojo.require("dijit.layout.AccordionPane");
</script>

<script>
var reloadFlag="1";
function deskReload() {

	if(reloadFlag=="1"){reloadFlag="2"; location.reload();}    
}
</script>
<script>

function createTabSummary(tabObj, tabSummary)
{
	var summHTML = "<center><b><u>"+tabSummary.header+"</u></b></center><br>";
	for(var i=0;i<tabSummary.data.length;i++)
	{
		if(tabSummary.data[i].header!="")
			summHTML += "<b>"+tabSummary.data[i].header+":</b> ";
		summHTML += tabSummary.data[i].value;
		if(i!=tabSummary.data.length-1)	summHTML +="<br>";
	}			
	tabObj.innerHTML = summHTML;
}

var  diag = null;
var selectedTabURL = null;
var selecteTabMenuId = null;
var arrDeskMenuId = ["0","6","11","10"];
var arrDeskMenuURL = ["PATIENTSUMMARY","DESKDIAGNOSIS","REQUISITIONRAISING","DESKTREATMENTDETAIL"];
var arrDeskMenuName = ["Patient Summary","Diagnosis","Investigation Order","Treatment Detail","Medical History"];
var arrDeskMenuDetailURL = ["VISIT_DETAIL","DESKDIAGNOSIS_DETAIL","REQUISITIONRAISING_DETAIL","DESKTREATMENTDETAIL_DETAIL"];

function getDeskPatInfoStr()//----------------------
{
	var str = "&patCrNo="+ document.getElementsByName("patCrNo")[0].value;
	str += "&episodeCode="+ document.getElementsByName("episodeCode")[0].value;
	str += "&episodeVisitNo="+ document.getElementsByName("episodeVisitNo")[0].value;
	str += "&departmentUnitCode="+ document.getElementsByName("departmentUnitCode")[0].value;
	str += "&wardCode="+ document.getElementsByName("wardCode")[0].value;
	str += "&patAdmNo="+ document.getElementsByName("patAdmNo")[0].value;
	str += "&roomCode="+ document.getElementsByName("roomCode")[0].value;
	//alert(str);
	return str;
}

function open_Tab(deskMenuId, deskMenuURL)
{
	//alert("Open Dialog"+"  deskMenuId :"+deskMenuId+"  deskMenuURL :"+deskMenuURL);	
	
	parent.addTab(arrDeskMenuName,deskMenuURL,'0','1',true);	
	//document.getElementById("DeskPatDetailId").style.display = 'block';
	//document.getElementById("mainDeskDiv11").style.display = 'none';
	//var dtlURL ="?mode="+deskMenuURL+"&deskMenuId="+deskMenuId+"&hmode=NEW"+getDeskPatInfoStr();
	//document.getElementById('DeskPatDetailId').src="/HISClinical/hisglobal/utility/dynamicdesk/centerNew.cnt?mode="+deskMenuURL+"&deskMenuId="+deskMenuId+"&hmode=NEW"+getDeskPatInfoStr();
}


function open_Tab_New(arrDeskMenuId,arrDeskMenuName,deskMenuURL)
{
	//alert("summary tab opening....");
	//parent.addTab('Patient Summary','PATIENTSUMMARY','0','1',true);
	parent.addTab(arrDeskMenuName,deskMenuURL,arrDeskMenuId,'1',false,getDeskPatInfoStr(),'1');
}

//Added by Vasu on 15.Feb.219
function open_inv_tab()
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var episodeCode=document.getElementsByName("episodeCode")[0].value;
	var episodeVisitNo=document.getElementsByName("episodeVisitNo")[0].value;
	var departmentUnitCode=document.getElementsByName("departmentUnitCode")[0].value;
	var roomCode=document.getElementsByName("roomCode")[0].value;
	
	parent.addTab('Investigation Order','REQUISITIONRAISING','11','1',false,"&patCrNo="+patCrNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&departmentUnitCode="+departmentUnitCode+"&roomCode="+roomCode,'2','/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode=GETPATDTL');
}

function closeDialog()
{
	alert("closing");
	if(diag!=null)
	{
		//diag.destroyRecursive();
		diag.hide();
	}
}

function updateTab()
{
	obj = document.getElementById("tabSummary#"+selectedTabURL+"#"+selecteTabMenuId);
		var tabSummary = getTabSummary(selectedTabURL,selecteTabMenuId);
		if(tabSummary!=null)
		{
				createTabSummary(obj, tabSummary);
			//alert(tabSummary);
			/*var summHTML = "";
			for(var i=0;i<tabSummary.length;i++)
			{
				summHTML += tabSummary[i].summary;
				if(i!=tabSummary.length-1)	summHTML +="<br>";
			}			
			if(obj)	obj.innerHTML = summHTML;*/
		}
}
var zoomInOutFlag= "0";
function zoomInOutFun(obj)
{ //alert("In zoomInOutFun .."+zoomInOutFlag)
if(zoomInOutFlag=="0"){ zoomInOutFlag="1"; zoomOut(obj);}
else{ zoomInOutFlag="0"; zoomIn();}
}
function zoomOut(obj)
{
	//alert("sadasda");
	
	//if(obj=="test4"){alert(obj);}
	document.getElementById("test0").style.height = "0%";
	document.getElementById("test0").style.width = "0%";
	
	document.getElementById("test1").style.height = "0%";
	document.getElementById("test1").style.width = "0%";
	
	document.getElementById("test5").style.height = "0%";
	document.getElementById("test5").style.width = "0%";
	
	document.getElementById("test3").style.height = "0%";
	document.getElementById("test3").style.width = "0%";
	
	document.getElementById("test4").style.height = "0%";
	document.getElementById("test4").style.width = "0%";
	
	if(obj=="test3")
	{
		document.getElementById(obj).style.height = "100%";
		document.getElementById(obj).style.width = "100%";
	}
	else if(obj=="test4")
	{
		document.getElementById(obj).style.height = "100%";
		document.getElementById(obj).style.width = "100%";
	}
	else
	{
		document.getElementById(obj).style.height = "100%";
		document.getElementById(obj).style.width = "100%";
	}
	
}
function zoomIn()
{
	document.getElementById("test0").style.height = "49%";
	document.getElementById("test0").style.width = "35%";
	
	document.getElementById("test1").style.height = "49%";
	document.getElementById("test1").style.width = "35%";
	
	document.getElementById("test5").style.height = "100%";
	document.getElementById("test5").style.width = "30%";
	
	document.getElementById("test3").style.height = "49%";
	document.getElementById("test3").style.width = "35%";
	
	document.getElementById("test4").style.height = "49%";
	document.getElementById("test4").style.width = "35%";
}

function onLoadFun()
{
	
	<!-- // added by manisha gangwar for alert on patient dashboard for zero balance -->
	//alert("hi");
	if(document.getElementsByName("patBalance")[0].value!= null)
	{
	if(document.getElementsByName("patBalance")[0].value<=0.0)
	{
		//alert("Patient Account Balance is not sufficient..");		
	}}
}

function patientSummaryTab()
{
	//alert("summary tab opening....");
	parent.addTab('Admission Summary','PATIENTSUMMARY','0','1',true);
}

//Added by Vasu on 13.Feb.2019
function patientSummaryTabNew()
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var episodeCode=document.getElementsByName("episodeCode")[0].value;
	var episodeVisitNo=document.getElementsByName("episodeVisitNo")[0].value;
	var departmentUnitCode=document.getElementsByName("departmentUnitCode")[0].value;
	var roomCode=document.getElementsByName("roomCode")[0].value;
	
	parent.addTab('Single Page Discharge','DM_SP_E_DISCHARGE_SUMMARY','35','1',false,"&patCrNo="+patCrNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&departmentUnitCode="+departmentUnitCode+"&roomCode="+roomCode,'1');
}
</script>

</head>

<body onload="onLoadFun()">

<div style="margin-top: 1px; width: 99%; overflow: hidden" class="wrapper roundednew">
	<!-- Starting Patient Details TAB div -->
	<%
		List lstTop = (List)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TOP_MENU_DTL);
		List lstLeft = (List)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_LEFT_MENU_DTL);
		List lstRight = (List)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_RIGHT_MENU_DTL);
		List lstBottom = (List)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_BOTTOM_MENU_DTL);
	%>
	<html:hidden name="DoctorDeskFB" property="patCrNo"/>
	<html:hidden name="DoctorDeskFB" property="episodeCode"/>
	<html:hidden name="DoctorDeskFB" property="episodeVisitNo"/>
	<html:hidden name="DoctorDeskFB" property="departmentUnitCode"/>
	<html:hidden name="DoctorDeskFB" property="wardCode"/>
	<html:hidden name="DoctorDeskFB" property="patAdmNo"/>
	<html:hidden name="DoctorDeskFB" property="roomCode"/>
	<bean:define id="crno" name="DoctorDeskFB" property="patCrNo"></bean:define>
	<bean:define id="episodecode" name="DoctorDeskFB" property="episodeCode"></bean:define>
	<bean:define id="episodevisitno" name="DoctorDeskFB" property="episodeVisitNo"></bean:define>
	<bean:define id="patadmno" name="DoctorDeskFB" property="patAdmNo"></bean:define>
	
	<% String url = "/inpatient/inpatientDetail.cnt?patCrNo="+crno +"&tileType=NEW";  %> 

	<div><jsp:include page="<%=url%>" flush="true"></jsp:include></div>		
	</div><!-- closed class wraper round -->
	
		<% String admissionSummaryUrl = "/opd/doctorDeskDashboard.cnt?hmode="+"ADMISSION_DETAIL"+"&patCrNo="+crno+"&episodeVisitNo="+episodevisitno+"&episodeCode="+episodecode+"&patAdmNo="+patadmno;  %>
		<% String diagnosisUrl = "/opd/doctorDeskDashboard.cnt?hmode="+"DIAGNOSIS_DETAIL"+"&patCrNo="+crno+"&episodeVisitNo="+episodevisitno+"&episodeCode="+episodecode+"&patAdmNo="+patadmno;  %>
		<% String treatmentUrl = "/opd/doctorDeskDashboard.cnt?hmode="+"TREATMENT_DETAIL"+"&patCrNo="+crno+"&episodeVisitNo="+episodevisitno+"&episodeCode="+episodecode+"&patAdmNo="+patadmno;  %>
		<% String medicalHistoryUrl = "/opd/doctorDeskDashboard.cnt?hmode="+"MEDICAL_HISTORY"+"&patCrNo="+crno+"&episodeVisitNo="+episodevisitno+"&episodeCode="+episodecode+"&patAdmNo="+patadmno;  %>
	    <% String investigationDetailUrl = "/opd/doctorDeskDashboard.cnt?hmode="+"INVESTIGATION_DETAIL"+"&patCrNo="+crno+"&episodeVisitNo="+episodevisitno+"&episodeCode="+episodecode+"&patAdmNo="+patadmno;  %>

	
	
	<div id="mainDeskDiv11" align="center">	 
		
		<table width="98%" height="80%" border=0>
			<tr>
				<td id="test0"  width="35%" height="49%">

			<div class="tiles white" align="center" style="width: 100%; height: 100%;" >	
				<div class="live-tile roundeddarkgreen" data-mode="slide" data-speed="600" data-stack="true" 
					data-start-now="false" data-delay="0" data-repeat="0" data-direction="vertical" style="width: 100%; height: 100%;" ondblclick="zoomInOutFun('test0');">
						<!-- adding the 'full' class to an 'img' or 'a' tag causes it to fill the entire tile -->
						<div id="visitSummaryId">
							<div class="roundeddarkgreen1">
							<img src="/HISClinical/hisglobal/images/icon_visit_summary.png" align="left"/>							
							<p style="color: black">								
								<!-- <a onclick="patientSummaryTab();" class="metroLargerLower fontBold"  href="#" style="font-size: small; font: bold"><font color="#25ba33">Admission Summary</font> </a> -->
								<a onclick="" class="metroLargerLower fontBold"  href="#" style="font-size: small; font: bold"><font color="#25ba33">Admission Summary</font> </a>
								<!--<img src="/HISClinical/hisglobal/images/icon-pat-summary.png"  align="right" onclick="patientSummaryTab();"/>
								-->
							</p>	
							</div>
							<jsp:include page="<%=admissionSummaryUrl%>" flush="true"></jsp:include>																												
							<!--<span class="tile-title">front title</span>-->
							<div class="tile-title" style="background:  url('/HIS/hisglobal/eDesk/transactions/MetroJs/images/trans-black-50.png') repeat scroll 0% 0% transparent">
								
								<!-- <a onclick="open_Tab_New(arrDeskMenuId[0],arrDeskMenuName[0],arrDeskMenuURL[0]);" style="display: none;margin: 3px;padding: 2px;height: 15px;text-decoration: none;" class="metroSmall tilefooter roundeddarkgreen">
								<span class="getReport">Detail </span></a> -->
								<a onclick="patientSummaryTabNew();" style="display: none;margin: 3px;padding: 2px;height: 15px;text-decoration: none;" class="metroSmall tilefooter roundeddarkgreen">
								<span class="getReport">Detail </span></a>
							</div>
						</div>						
					</div>
			</div>
			</td>	
			<td id="test1" width="35%" height="49%">
		<div class="tiles white" align="center" style="width: 100%; height: 100%;">					
				<div  class="live-tile roundedred" data-mode="slide" data-speed="600" data-stack="true" 
					data-start-now="false" data-delay="0" data-repeat="0" data-direction="vertical" style="width: 100%; height: 100%;" ondblclick="zoomInOutFun('test1');">
						<!-- adding the 'full' class to an 'img' or 'a' tag causes it to fill the entire tile -->
						<div id="diagnosisId">
							<div class="roundedred1">
							<img src="/HISClinical/hisglobal/images/icon_diagnosis.png" align="left"/>
							<p>
								<a class="metroLargerLower fontBold" style="font-size: small; font: bold"><font color="#c12b31">Diagnosis</font></a>
							</p>	
							</div>						
							<jsp:include page="<%=diagnosisUrl%>" flush="true"></jsp:include>
							<!--<span class="tile-title">front title</span>-->
							<div class="tile-title" style="background:  url('/HIS/hisglobal/eDesk/transactions/MetroJs/images/trans-black-50.png') repeat scroll 0% 0% transparent">
								
								<a onclick="open_Tab_New(arrDeskMenuId[1],arrDeskMenuName[1],arrDeskMenuURL[1]);" style="display: none;margin: 3px;padding: 2px;height: 15px;text-decoration: none;" class="metroSmall tilefooter roundedred">
								<span class="getReport">Detail </span></a>
							</div>
						</div>						
					</div>	
		</div>
			</td>
			<td id="test5" width="30%" height="98%" rowspan="2">
		<div class="tiles white" align="center" style="width: 100%; height: 100%;">			
				<div  class="live-tile roundedblue" data-mode="slide" data-speed="600" data-stack="true"
					data-start-now="false" data-delay="0" data-repeat="0" data-direction="vertical" style="width: 100%; height: 100%;" ondblclick="zoomInOutFun('test5');">
						<!-- adding the 'full' class to an 'img' or 'a' tag causes it to fill the entire tile -->
						<div>
							<div class="roundedblue1">
							<img src="/HISClinical/hisglobal/images/icon_medical_history.png" align="left"/>
							<p>								
								<a class="metroLargerLower fontBold" style="font-size: small; font: bold"><font color="#017299">Patient Allergies</font></a>
							</p>
							</div>
							<jsp:include page="<%=medicalHistoryUrl%>" flush="true"></jsp:include>
							
						</div>						
					</div>
		</div>
			</td>
		</tr>
		<tr>
			<td id="test3"  width="35%" height="49%">
		<div class="tiles white" align="center" style="width: 100%; height: 100%;">
				<div class="live-tile roundedyellow" data-mode="slide" data-speed="600" data-stack="true" 
					data-start-now="false" data-delay="0" data-repeat="0" data-direction="vertical" style="width: 100%; height: 100%;" margin-top: -17%;" ondblclick="zoomInOutFun('test3');" >
						<!-- adding the 'full' class to an 'img' or 'a' tag causes it to fill the entire tile -->
						<div>
							<div class="roundedyellow1">
							<img src="/HISClinical/hisglobal/images/icon_investigation.png" align="left"/>
							<p>
								<a class="metroLargerLower fontBold" style="font-size: small; font: bold"><font color="#c81dc9">Investigation</font></a>
							</p>
							</div>
							<jsp:include page="<%=investigationDetailUrl%>" flush="true"></jsp:include>												
							<!--<span class="tile-title">front title</span>-->
							<div class="tile-title" style="background:  url('/HIS/hisglobal/eDesk/transactions/MetroJs/images/trans-black-50.png') repeat scroll 0% 0% transparent">
								
								<!-- <a onclick="open_Tab_New(arrDeskMenuId[2],arrDeskMenuName[2],arrDeskMenuURL[2]);" style="display: none;margin: 3px;padding: 2px;height: 15px;text-decoration: none;" class="metroSmall tilefooter roundedyellow">
								<span class="getReport">Detail </span></a> -->
								<a onclick="open_inv_tab();" style="display: none;margin: 3px;padding: 2px;height: 15px;text-decoration: none;" class="metroSmall tilefooter roundedyellow">
								<span class="getReport">Detail </span></a>
							</div>
						</div>						
					</div>
		</div>
			</td>
			<td id="test4"  width="35%" height="49%">
		<div class="tiles white" align="center" style="width: 100%; height: 100%;">		
				<div class="live-tile roundedgreen" data-mode="slide" data-speed="600" data-stack="true" 
					data-start-now="false" data-delay="0" data-repeat="0" data-direction="vertical" style="width: 100%; height: 100%;" ondblclick="zoomInOutFun('test4');" >
						<!-- adding the 'full' class to an 'img' or 'a' tag causes it to fill the entire tile -->
						<div>
							<div class="roundedgreen1">
							<img src="/HISClinical/hisglobal/images/icon_treatment.png" align="left"/>
							<p>
								<a class="metroLargerLower fontBold" style="font-size: small; font: bold"><font color="#026702">Drug Treatment</font></a>
							</p>
							</div>
							<jsp:include page="<%=treatmentUrl%>" flush="true"></jsp:include>
																		
							<!--<span class="tile-title">front title</span>-->
							<div class="tile-title" style="background:  url('/HIS/hisglobal/eDesk/transactions/MetroJs/images/trans-black-50.png') repeat scroll 0% 0% transparent">
								
								<a onclick="open_Tab_New(arrDeskMenuId[3],arrDeskMenuName[3],arrDeskMenuURL[3]);" style="display: none;margin: 3px;padding: 2px;height: 15px;text-decoration: none;" class="metroSmall tilefooter roundedgreen">
								<span class="getReport">Detail </span></a>
							</div>
						</div>						
					</div>						
			</div>
			</td>
		</tr>	
		</table>		
	</div>
		<div class="div-table-col width100">
			<iframe id="DeskPatDetailId" name="deskPatDetail" src="/HISClinical/hisglobal/images/load_list.gif" style="display: none" 
			scrolling="yes"  frameborder="0" width="100%" height="100%" marginwidth="0" marginheight="0" ></iframe>
		</div>	
<!-- script -->
			<script type="text/javascript">
				$(function() {
					$(window)
							.on(
									'resize',
									function resize() {
										$(window).off('resize', resize);
										setTimeout(
												function() {
													var content = $('#content');
													var top = (window.innerHeight - content
															.height()) / 2;
													content.css('top', Math
															.max(0, top)
															+ 'px');
													$(window).on('resize',
															resize);
												}, 50);
									}).resize();
				});

				//flip mode simple
				//using data attributes
				$(".live-tile").liveTile();
				var $tile1 = $(".live-tile").liveTile();
				$('.live-tile').hover(function() {
					//alert("pp");
					$(this).find('.tilefooter').slideDown(250);
				}, function() {
					$(this).find('.tilefooter').slideUp(250);
				});

				//jQuery UI 
				//http://jqueryui.com/sortable/#display-grid
				$(".tiles").sortable();
				$(".tiles").disableSelection();
				
			</script>

 <div id="div1" dojoType="dijit.Dialog"></div>
</body>
</html>