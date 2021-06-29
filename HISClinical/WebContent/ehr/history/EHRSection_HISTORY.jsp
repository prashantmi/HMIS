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
	 

</head>
<script>

function openRespectiveDeskTabForHistory()
{
	var deskMenuName = "History";
	var deskMenuURL ="GENERICTEMPLATE";
	var deskMenuId = "2";
	redirectRespectiveTab(deskMenuName,deskMenuURL,deskMenuId);
	document.getElementById("ImageRefreshForHistory").style.display="block";
}

function getHistoryData()
{
//alert("hello");
 	var PatCrNo=document.getElementsByName("patCrNo")[0].value; 
 	var Ftlvalue=document.getElementsByName("ftlValueForHistory")[0].value; 
 	//var ftlvalue="";
 	//alert(Ftlvalue);
 
	$.ajax({
	url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_PAT_HISTORY.cnt?hmode=PAT_HISTORY_AJAX&patCrNo="+PatCrNo+""),  
    type : 'GET',
    datatype: "json",
    async : false,
	success: function(data)
	{
		 
       document.getElementsByName('ftlValueForHistory')[0].value  = data;

	   document.getElementById('patientHistory').innerHTML = data;	
		
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
	var action = "/HISClinical/opd/opdTemplateTab.cnt?hmode=NEW&deskMenuId=2&isCallFromSinglePage=1&patCrNo="+PatCrNo;
	//alert("21");
//$("#myModal_portal").trigger("click");

//$("#myModal_portal").on("shown.bs.modal",function(){ 
	//$(document).on('shown.bs.modal','#myModal_portal', function() {
	 $('#HistoryId').click(function(){
		// alert("24");
  //var frametarget = $(this).attr('href');
  targetmodal = '#myModal_portalHistory'; 
        $('#myFrameHistory').attr("src", action);   
});
	  //alert(".......");
	  //$(this).find("iframe").attr("src",action);
	
//}); 

	 $('#refreshHistoryId').click(function(){
			
		 getHistoryData(event);  
		});
});



</script>
<body>

 <div class="modal fade" id="myModal_portalHistory">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
           <div class="modal-header" style="height:45px;">
          <h4 class="modal-title">History</h4>
          <button type="button" class="close" style="color:red;margin-top:0px;line-height:0px;" id="refreshHistoryId" data-dismiss="modal">&times;</button> 
        
        </div> 
        
        <!-- Modal body -->
        <div class="modal-body">
          <iframe style="border: none; width: 100%; height: 100% !important; " id="myFrameHistory"> </iframe>
        </div>
        
 
   <button class="btn btn-primary btn-sm" id="" onclick="getHistoryData(event);" style="padding-top:0px;margin-top:0px;" data-dismiss="modal" aria-label="Close">Cancel</button>
    
      </div>
     
      
    </div>
  </div>

<table class="table">
	<tr>
		<td width="90%" style="font-size:1.2em;font-weight:bold;">
			History&nbsp;
		</td>
		<td width="5%" style="font-size:1.2em;font-weight:bold;">
			<button type="button" id="HistoryId" class="btn btn-info btn-sm" data-toggle="modal" onclick = "" data-target="#myModal_portalHistory">Add</button>
		</td>
		<td width="5%"> 
			<img id="ImageRefreshForHistory" src="/HISClinical/hisglobal/images/icon_refresh.gif"  onclick="getHistoryData(event);"  style="cursor:pointer;cursor:hand;"/> 
		</td>
	</tr>
	<!-- <tr>
		<td colspan="2">
  			<a onclick="openRespectiveDeskTabForHistory()" href="#" style="font-size:12px;"><b>Go To Respective Tab</b></a>
 		 </td>
  	</tr> --> 	
	<tr>
		<td colspan="3">
			<div id = "patientHistory">
			  <bean:write name='EHRSection_HistoryFB' property='ftlValueForHistory'filter='false'/>
			</div>
 		 </td>
  	</tr> 	
</table>  
<html:hidden name="EHRSection_HistoryFB" property="patCrNo"/>
<html:hidden name="EHRSection_HistoryFB" property="episodeCode"/>
<html:hidden name="EHRSection_HistoryFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_HistoryFB" property="departmentUnitCode"/>
<html:hidden name="EHRSection_HistoryFB" property="roomCode"/>
<html:hidden name="EHRSection_HistoryFB" property="ftlValueForHistory"/>
</body>

