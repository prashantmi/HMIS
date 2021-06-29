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
    <script src="/HIS/hisglobal/drDeskAssets/jquery/jquery-3.3.1.min.js"></script> 
    
    <script src="/HIS/hisglobal/drDeskAssets/bootstrap/js/bootstrap.min.js"></script>  
<style>
@media print {
  body * {
    visibility: hidden;
  }
  #examination,
  #examination * {
    visibility: visible;
  }
  #examination {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background: yellow;
  }
}


</style>
</head>
<script>

function openRespectiveDeskTabForExamination()
{
	var deskMenuName = "Examination";
	var deskMenuURL ="GENERICTEMPLATE";
	var deskMenuId = "3";
	redirectRespectiveTab(deskMenuName,deskMenuURL,deskMenuId);
	document.getElementById("ImageRefreshForExamination").style.display="block";
}

function getExaminationData()
{
//alert("hello");
 	var PatCrNo=document.getElementsByName("patCrNo")[0].value; 
 	var Ftlvalue=document.getElementsByName("ftlValueForExamination")[0].value; 
 	//var ftlvalue="";
 	//alert(Ftlvalue);
 
	$.ajax({
	url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_ENC_EXAM.cnt?hmode=ENC_EXAM_AJAX&patCrNo="+PatCrNo+""),  
    type : 'GET',
    datatype: "json",
    async : false,
	success: function(data)
	{
		 
       document.getElementsByName('ftlValueForExamination')[0].value  = data;

	   document.getElementById('examination').innerHTML = data;	
		
      },
	
      error: function(data)
      {
    	    alert('request failed :');
    	}

	});
	
	}

$(document).ready(function() {
	var PatCrNo=document.getElementsByName("patCrNo")[0].value;
	//alert(PatCrNo);
	var action = "/HISClinical/opd/opdTemplateTab.cnt?hmode=NEW&deskMenuId=3&isCallFromSinglePage=1&patCrNo="+PatCrNo;
	//alert("21");
//$("#myModal_portal").trigger("click");

//$("#myModal_portal").on("shown.bs.modal",function(){ 
	//$(document).on('shown.bs.modal','#myModal_portal', function() {
	 $('#ExaminationId').click(function(){
		// alert("24");
  //var frametarget = $(this).attr('href');
  targetmodal = '#myModal_portalExamination'; 
        $('#myFrameExamination').attr("src", action);   
});
	  //alert(".......");
	  //$(this).find("iframe").attr("src",action);
	
//}); 
	 $('#refreshExaminationId').click(function(){
			
		 getExaminationData(event);  
		});

		
});
function openGrowthChart(event)
{
	//openPopFileUpload(event);
	url='/HISClinical/opd/opdTemplateTab.cnt?hmode=GrowthChart';
	 targetmodal = '#myModal_portalGrowthChart'; 
    $('#growthBody').load(url); 

    }

function printExam(event){

	 window.print();
     return false;


	}


</script>

<body>

<div class="modal fade" id="myModal_portalExamination">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header" style="height:45px;">
          <h4 class="modal-title"><b>Examination</b></h4>
          <button type="button" class="close" style="color:red;margin-top:0px;line-height:0px;" id="refreshExaminationId" data-dismiss="modal">&times;</button> 
        
        </div> 
        
        <!-- Modal body -->
        <div class="modal-body">
          <iframe style="border: none; width: 100%; height: 100% !important; " id="myFrameExamination"> </iframe>
        </div>
        
         <button class="btn btn-primary btn-sm" id="" onclick="getExaminationData(event);" style="padding-top:0px;margin-top:0px;" data-dismiss="modal" aria-label="Close">Cancel</button>
      </div>
     
      
    </div>
  </div>
  
  <div class="modal fade" id="myModal_portalGrowthChart">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header" style="height:45px;">
          <h4 class="modal-title"><b>Growth Chart</b></h4>
          <button type="button" class="close" style="color:red;margin-top:0px;line-height:0px;" id="cutGrowthChartId" data-dismiss="modal">&times;</button> 
        
        </div> 
        
        <!-- Modal body -->
        <div class="modal-body" id="growthBody">
         <!--  <iframe style="border: none; width: 100%; height: 100% !important; " id="myFrameExamination"> </iframe> -->
        </div>
        
         <button class="btn btn-primary btn-sm" id="cancelGrowthChartId" style="padding-top:0px;margin-top:0px;" data-dismiss="modal" aria-label="Close">Cancel</button>
      </div>
     
      
    </div>
  </div>

<table class="table">
<tr>
<td width="80%" style="font-size:1.2em;font-weight:bold;">
	Examination&nbsp;
	</td>
	<td width="5%" style="font-size:1.2em;font-weight:bold;">
			<button type="button" id="ExaminationId" class="btn btn-info btn-sm" data-toggle="modal" onclick = "" data-target="#myModal_portalExamination">Add</button>
		</td>
		<td width="5%" style="font-size:1.2em;font-weight:bold;">
			<button type="button" id="growthChartId" class="btn btn-info btn-sm" data-toggle="modal" onclick = "openGrowthChart(event)" data-target="#myModal_portalGrowthChart">Growth Chart</button>
		</td>
		
		<td width="5%" style="font-size:1.2em;font-weight:bold;">
			<button type="button" id="printExamId" class="btn btn-info btn-sm" onclick = "printExam(event)" >Print</button>
		</td>
	<td width="5%"> 
	<img id="ImageRefreshForExamination" src="/HISClinical/hisglobal/images/icon_refresh.gif"  onclick="getExaminationData(event);"  style="cursor:pointer;cursor:hand;"/> 
	
	</td>
	</tr>
	<!-- <tr>
		<td  colspan="2">
  			<a onclick="openRespectiveDeskTabForExamination()" href="#" style="font-size:12px;"><b>Go To Respective Tab</b></a>
 		 </td>
 		 
  	</tr> 	 -->
	<tr>
		<td  colspan="3">
			<div id = "examination">
			  <bean:write name='EHRSection_ExaminationFB' property='ftlValueForExamination'filter='false'/>
			</div>
 		 </td>
 		 
  	</tr>
</table>  
<html:hidden name="EHRSection_ExaminationFB" property="patCrNo"/>
<html:hidden name="EHRSection_ExaminationFB" property="episodeCode"/>
<html:hidden name="EHRSection_ExaminationFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_ExaminationFB" property="departmentUnitCode"/>
<html:hidden name="EHRSection_ExaminationFB" property="roomCode"/>
<html:hidden name="EHRSection_ExaminationFB" property="ftlValueForExamination"/>
</body>