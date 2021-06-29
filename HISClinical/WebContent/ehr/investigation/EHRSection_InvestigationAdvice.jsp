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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
	
    <script src="/HIS/hisglobal/drDeskAssets/jquery/jquery-3.3.1.min.js"></script> 
    
    <script src="/HIS/hisglobal/drDeskAssets/bootstrap/js/bootstrap.min.js"></script>
    

</head>
<script>

function openRespectiveDeskTabForINV(e)
{
	document.getElementById("RefreshInvImage").style.display="block";
	var deskMenuName = "Investigation Order";
	var deskMenuURL ="REQUISITIONRAISING";
	var deskMenuId = "11";
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var episodeCode=document.getElementsByName("episodeCode")[0].value;
	var episodeVisitNo=document.getElementsByName("episodeVisitNo")[0].value;
	var departmentUnitCode=document.getElementsByName("departmentUnitCode")[0].value;
	var roomCode=document.getElementsByName("roomCode")[0].value;
	document.getElementsByName("callFromDesk")[0].value = true;
	redirectRespectiveTab(deskMenuName,deskMenuURL,deskMenuId);

}


function getDataForInvAdvice()
{
//alert("hello");
 	var PatCrNo=document.getElementsByName("patCrNo")[0].value; 
 	var Ftlvalue=document.getElementsByName("ftlValueForInvestigation")[0].value; 
 	//var ftlvalue="";
 	//alert(Ftlvalue);
 
	$.ajax({
	url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_ENC_INV_ADV.cnt?hmode=ENC_INV_ADV_AJAX&patCrNo="+PatCrNo+""),  
    type : 'GET',
    datatype: "json",
    async : false,
	success: function(data)
	{
		
        document.getElementsByName('ftlValueForInvestigation')[0].value  = data;
		document.getElementById('investigationAdvice').innerHTML = data;
		
      },
	
      error: function(data)
      {
    	    alert('request failed :');
    	}

	});
	/* return JSON.parse(response);
	 }(); */
	}

	 $(document).ready(function() {
			var PatCrNo=document.getElementsByName("patCrNo")[0].value;
			//alert(PatCrNo);
			var episodeCode = document.getElementsByName("episodeCode")[0].value;
			var episodeVisitNo = document.getElementsByName("episodeVisitNo")[0].value;
			//alert(episodeCode);
			//alert(episodeVisitNo);
			var deptUnitCode = document.getElementsByName("departmentUnitCode")[0].value;
			var roomCode = document.getElementsByName("roomCode")[0].value;
			var isDesk = 1;
			var ssoTicket=parent.parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
			//alert(ssoTicket);
			var action = "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode=GETPATDTL&patCrNo="+PatCrNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&departmentUnitCode="+deptUnitCode+"&roomCode="+roomCode+"&isDesk="+isDesk+"&varSSOTicketGrantingTicket="+ssoTicket;
			 $('#investigationAdviceId').click(function(){
				 //alert(action);
				
		      targetmodal = '#myModal_INVAdvice'; 
		        $('#myFrameINV').attr("src", action);   
		      });
			  

			 $('#refreshInvestigationAdviceId').click(function(){
					
				 getDataForInvAdvice();  
				});

		     });
		
</script>

<body>


 <div class="modal fade" id="myModal_INVAdvice">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <!-- Modal Header -->
       <!--  <div class="modal-header">
          <h4 class="modal-title">Complaints</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        
        </div> -->
        
        <!-- Modal body -->
        <div class="modal-body">
          <iframe style="border: none; width: 100%; height: 100% !important; " id="myFrameINV"> </iframe>
        </div>
        
         <button class="btn btn-primary btn-sm" id="refreshInvestigationAdviceId" style="padding-top:0px;margin-top:0px;" data-dismiss="modal" aria-label="Close">Cancel</button>
      </div>
        
    </div>
  </div>
  


<table class="table">

	<tr>
		<td width="90%" style="font-size:1.2em;font-weight:bold;">
			Investigation Advised&nbsp;
		</td>
		<td width="5%" style="font-size:1.2em;font-weight:bold;">
			<button type="button" id="investigationAdviceId" class="btn btn-info btn-sm" data-toggle="modal" onclick = "" data-target="#myModal_INVAdvice">Add</button>
		</td>
		<td width="5%"> 
			<img id="RefreshInvImage" src="/HISClinical/hisglobal/images/icon_refresh.gif"  onclick="getDataForInvAdvice();"  style="cursor:pointer;cursor:hand;"/> 
		</td>
	</tr>
	
	
	
	<!-- <tr>
		<td colspan="2">
  			<a onclick="openRespectiveDeskTabForINV()" href="#" style="border:0;outline:none;"><b>Go To Respective Tab&nbsp;&nbsp;</b></a>
  		</td>
  	</tr> -->
	<tr>
		<td colspan="3">
			<div id = "investigationAdvice">
			  <bean:write name='EHRSection_InvestigationAdviceFB' property='ftlValueForInvestigation'filter='false'/>
			</div>
  		</td>
  	</tr>
</table> 


<html:hidden name="EHRSection_InvestigationAdviceFB" property="patCrNo"/>
<html:hidden name="EHRSection_InvestigationAdviceFB" property="episodeCode"/>
<html:hidden name="EHRSection_InvestigationAdviceFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_InvestigationAdviceFB" property="departmentUnitCode"/>
<html:hidden name="EHRSection_InvestigationAdviceFB" property="roomCode"/>
<html:hidden name="EHRSection_InvestigationAdviceFB" property="ftlValueForInvestigation"/>
<%-- <html:hidden name="EHRSection_InvestigationAdviceFB" property="callFromDesk"/> --%>


</body>

