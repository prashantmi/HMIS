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
<!-- 	 <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
	 <script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>
   <script src="/HIS/hisglobal/DoctorDesk/bootstrap/js/bootstrap.min.js"></script>   -->
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script> -->
  
  <!-- <link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/bootstrap/css/bootstrap.min.css">  --> 
    <script src="/HIS/hisglobal/drDeskAssets/jquery/jquery-3.3.1.min.js"></script> 
    
    <script src="/HIS/hisglobal/drDeskAssets/bootstrap/js/bootstrap.min.js"></script>  

</head>
<script>

function openRespectiveDeskTabForComplaints()
{
	var deskMenuName = "Questionnaire";
	var deskMenuURL ="GENERICTEMPLATE";
	var deskMenuId = "277";
	redirectRespectiveTab(deskMenuName,deskMenuURL,deskMenuId);
	document.getElementById("ImageRefreshForQuestionnaire").style.display="block";
}

function getQuestionnaireData()
{
//alert("hello");
 	var PatCrNo=document.getElementsByName("patCrNo")[0].value; 
 	var Ftlvalue=document.getElementsByName("ftlValueForQuestionnaire")[0].value; 
 	//var ftlvalue="";
 	//alert(Ftlvalue);
 
	$.ajax({
	url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_QUESTIONNAIRE.cnt?hmode=ENC_QUESTIONNAIRE_AJAX&patCrNo="+PatCrNo+""),  
    type : 'GET',
    datatype: "json",
    async : false,
	success: function(data)
	{
		 
       document.getElementsByName('ftlValueForQuestionnaire')[0].value  = data;

	   document.getElementById('questionnaire').innerHTML = data;	
		
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
	var action = "/HISClinical/opd/opdTemplateTab.cnt?hmode=NEW&deskMenuId=277&isCallFromSinglePage=1&patCrNo="+PatCrNo;
	//alert("21");
//$("#myModal_portal").trigger("click");

//$("#myModal_portal").on("shown.bs.modal",function(){ 
	//$(document).on('shown.bs.modal','#myModal_portal', function() {
	 $('#questionnaireId').click(function(){
		// alert("24");
  //var frametarget = $(this).attr('href');
  targetmodal = '#myModal_portal_questionnaire'; 
        $('#myFrameQuestionnaire').attr("src", action);   
});
	  //alert(".......");
	  //$(this).find("iframe").attr("src",action);
	
//}); 

	 $('#refreshQuestionnaireId').click(function(){
			
		 getQuestionnaireData(event);  
		});

});
	

</script>
<body>

  <!--  <button type="button" id="complaintsId" class="btn btn-info btn-sm" data-toggle="modal" onclick = "openComplaints();" data-target="#myModal_portal">Open Modal</button>
 -->
  <div class="modal fade" id="myModal_portal_questionnaire">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Preventive Health (Questionnaire)&nbsp;</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <iframe style="border: none; width: 100%; height: 100% !important; " id="myFrameQuestionnaire"> </iframe>
        </div>
        
         <button class="btn btn-primary btn-sm" id="refreshQuestionnaireId" style="padding-top:0px;margin-top:0px;" data-dismiss="modal" aria-label="Close">Cancel</button>
      </div>
        
    </div>
  </div>

<table class="table">
	<tr>
		<td width="90%" style="font-size:1.2em;font-weight:bold;">
				Preventive Health (Questionnaire)&nbsp;
		</td>
		<td width="5%" style="font-size:1.2em;font-weight:bold;">
			<button type="button" id="questionnaireId" class="btn btn-info btn-sm" data-toggle="modal" onclick = "openQuestionnaire();" data-target="#myModal_portal_questionnaire">Add</button>
		</td>
		<td width="5%"> 
			<img id="ImageRefreshForQuestionnaire" src="/HISClinical/hisglobal/images/icon_refresh.gif"  onclick="getQuestionnaireData(event);"  style="cursor:pointer;cursor:hand;"/> 
		</td>
	</tr>
	<!-- <tr>
		<td colspan="2">
  			<a onclick="openRespectiveDeskTabForComplaints()" href="#" style="font-size:12px;"><b>Go To Respective Tab</b></a>
 		 </td>
  	</tr> -->	
	<tr>
		<td colspan="3">
			<div id = "questionnaire">
			  <bean:write name='EHRSection_QuestionnaireFB' property='ftlValueForQuestionnaire'filter='false'/>
			</div>
 		 </td>
  	</tr> 	
</table>  
<html:hidden name="EHRSection_QuestionnaireFB" property="patCrNo"/>
<html:hidden name="EHRSection_QuestionnaireFB" property="episodeCode"/>
<html:hidden name="EHRSection_QuestionnaireFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_QuestionnaireFB" property="departmentUnitCode"/>
<html:hidden name="EHRSection_QuestionnaireFB" property="roomCode"/>
<html:hidden name="EHRSection_QuestionnaireFB" property="ftlValueForQuestionnaire"/>
</body>