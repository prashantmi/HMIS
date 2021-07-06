<!DOCTYPE html>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.*"%>
<html lang="en">
<head>
	<meta charset="utf-8" >
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
	<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
	<title>eSushrut - G6</title>   
    <link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/bootstrap/css/bootstrap.min.css">  
<!--     <script src="/HIS/hisglobal/drDeskAssets/jquery/jquery-3.3.1.min.js"></script>  -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script> -->
<script type="text/javascript" src="../../new_opd/assests/jquery.js"></script>
    <script src="/HIS/hisglobal/drDeskAssets/popper/popper.min.js"></script>
    <script src="/HIS/hisglobal/drDeskAssets/bootstrap/js/bootstrap.min.js"></script>  
    <script src="/HIS/hisglobal/drDeskAssets/gijgo/js/gijgo.min.js" type="text/javascript"></script>
    <link href="/HIS/hisglobal/drDeskAssets/gijgo/css/gijgo.min.css" rel="stylesheet" type="text/css" /> 
    <link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/fontawesome/css/all.css">
    <link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/fontawesome/css/fontawesome.min.css">
    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
	<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/css/style.css">
	<script src="/HISDRDESK/new_opd/js/opdDrDeskSave.js"></script>
	<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/css/perfect-scrollbar.css">
	<script src="/HIS/hisglobal/drDeskAssets/perfectScrollbar/perfect-scrollbar.js"></script>
	<!-- UIkit CSS -->
	<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/uikit/css/uikit.min.css" />
	<!-- UIkit JS -->
	<script src="/HIS/hisglobal/drDeskAssets/uikit/js/uikit.min.js"></script>
	<script src="/HIS/hisglobal/drDeskAssets/uikit/js/uikit-icons.min.js"></script>
	<script src="/HIS/hisglobal/drDeskAssets/tippy/tippy.all.min.js"></script>
	<script src="/HISDRDESK/new_opd/js/teleconsultancy.js"></script>
    <!-- JS Tooltip -->
    <!--   <link href="/HISDRDESK/new_opd/css/tooltip.css" rel="stylesheet" type="text/css" />
    <script src="/HISDRDESK/new_opd/js/tooltip.js" type="text/javascript"></script> -->
    <link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/material_icons/icon.css"> 
    <!-- <link href="https://fonts.googleapis.com/css?family=Cormorant+Upright" rel="stylesheet"> --> 
    <!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"> -->
    <!--  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script> 
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
	<script src="https://cdn.datatables.net/responsive/2.2.1/js/dataTables.responsive.min.js"></script> -->
<!-- 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
 -->
 
    <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/DataTables-1.10.18/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/Responsive-2.2.2/css/responsive.bootstrap4.min.css"/>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/DataTables-1.10.18/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/Responsive-2.2.2/js/dataTables.responsive.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/Responsive-2.2.2/js/responsive.bootstrap4.min.js"></script>
<!-- 	<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/jstree/themes/default/style.min.css" /> -->
	<script src="/HIS/hisglobal/drDeskAssets/jstree/jstree.min.js"></script> 
	<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/viewerjs/viewer.css"> 
	<script src="/HIS/hisglobal/drDeskAssets/viewerjs/viewer.js"></script>  
    <link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/SweetAlertNew/sweetalert.min.css">
    <link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/SweetAlertNew/sweetalert.min.css.map">
    <script src="/HIS/hisglobal/drDeskAssets/SweetAlertNew/sweetalert.min.js"></script>
    <script src="/HIS/hisglobal/drDeskAssets/chartjs/Chart.min.js"></script>
	<script src="/HIS/hisglobal/drDeskAssets/hotkeys/hotkeys.min.js"></script>
	<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/flexdatalist/jquery.flexdatalist.min.css">
	
	<link rel="stylesheet" href="/HISDRDESK/new_opd/assests/jquery-confirm.min.css">
	<script src="/HISDRDESK/new_opd/assests/jquery/jquery-confirm.min.js"></script>

	<link rel="stylesheet" href="/HISDRDESK/new_opd/css/mainDesk.css">
	<script src="/HISDRDESK/new_opd/js/mainDeskScript.js"></script>  
	<script src="/HISDRDESK/new_opd/js/Opdvital.js"></script>  
	<script src="/HISDRDESK/new_opd/js/VitalSave.js"></script> 
		<script src="/HISDRDESK/new_opd/js/ExtOpenTemplate.js"></script> 
	<!-- <script type="text/javascript" src="/HISDRDESK/new_opd/js/prescriptionScript.js"></script> --> 
	<style>
	
	
.loader{
  position: absolute;
  top:50%;
  left:50%;
}

.loader span{
  display: inline-block;
  width: 0.7rem;
  height: 3rem;
  background-color: #3498db;
}

.loader span:nth-child(1){
  animation: grow 1s ease-in-out infinite;
}

.loader span:nth-child(2){
  animation: grow 1s ease-in-out 0.15s infinite;
}

.loader span:nth-child(3){
  animation: grow 1s ease-in-out 0.30s infinite;
}

.loader span:nth-child(4){
  animation: grow 1s ease-in-out 0.45s infinite;
}

@keyframes grow{
  0%, 100%{
    -webkit-transform: scaleY(1);
    -ms-transform: scaleY(1);
    -o-transform: scaleY(1);
    transform: scaleY(1);
  }

  50%{
    -webkit-transform: scaleY(1.8);
    -ms-transform: scaleY(1.8);
    -o-transform: scaleY(1.8);
    transform: scaleY(1.8);
  }
}
	
	.modal-backdrop{
  z-index: -1 !important;
			}
			
	.blink {
      animation: blink 2s steps(5, start) infinite;
      -webkit-animation: blink 1s steps(5, start) infinite;
    }
    @keyframes blink {
      to {
        visibility: hidden;
      }
    }
    @-webkit-keyframes blink {
      to {
        visibility: hidden;
      }
    }
	/*		
	.patSideList .patCatHeader{
   	
   	 background-color: #ce2020 !important;
   	 text-align: left !important;
   	
   }
   */
  
	</style>
<script>
	/*
	$(document).ready(function(){
		  	$('input[name=prescMode]').on('change', function(e){
		  		if($('input[name=prescMode]:checked').val() == 2)
		  		{
		  			$('.mainHeaderSrch').hide();
		  			$('.prescNxtBtn').show();
		  		}
		  		else
		  		{
		  			$('.mainHeaderSrch').show();
		  			$('.prescNxtBtn').hide();
		  		} 
			});
	});
	*/
	
</script>
<script>

/* $(function(){
	$(".btn").on("click", function(){
		  $(document).find(".modal-backdrop").css({"z-index": "-1"});
		});
}); */
</script>
<!--  ################################# Added for IPD switch  ############################################# -->
<script>
function submitPage(mode)
{
	//alert(mode);
	document.forms[0].hmode.value=mode;
	//alert(document.forms[0].hmode.value);
	document.forms[0].submit();
}
function showIpdList(mode){
	
		//alert('Called');	
		submitPage("SHOWIPDLIST");

}

</script>
</head>
<body style="padding-top:15px; background-color:#eaeef3">


			<div class="modal fade" id="PatientReVisitTemplateModal" >
			    <div id="PatientReVisitTemplateModal12" style="width: 90%" class='modal-dialog modal-lg'>       <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header" style="text-align: center;font-size: 25px;">
			          <button type="button" class="close" data-dismiss="modal">&times;</button> 
			        </div>
			        <div class="modal-body">
			       		 <div class="text-center">
			       			<button type="button" class="btn btn-warning" onclick="$('#PatientReVisitTemplateModal').modal('hide');$('#PatientReVisitTemplateModalFrameId').remove();">Cancel</button>
							
							<!-- <button type="button" class="btn btn-info printPrescSAveBtn" onclick="">Save</button> -->
						 </div> 
			        </div>  
			      </div> 
			    </div>
			  </div> 
<div id="mainDeskLoadingMsgDiv" style="width:100vw;height:100vh;position:fixed;z-index:99999; background-color:rgba(0,0,0,0.6)">
	<h2 class="text-center" style="color:white; margin-top:13%"><i class="fa fa-spinner fa-spin"></i> Loading...</h2>
</div>
	<html:form action="/transaction/DoctorDeskAction.cnt"  name="DoctorDeskFB" type="new_opd.transaction.controller.fb.DoctorDeskFB" method="post" >  
		
	<div class="savePrescAlert alert alert-success alert-dismissible fade in" style="display:none; position:fixed; z-index:9999; top:18vh; left:35vw; color: #fff; background-color: rgb(51, 143, 13); border-color: rgb(103, 183, 37); font-size: 16px; padding: 2% 5% 1% 5%;">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Success!</strong> Prescription saved successfully.
		<br><br>
	    <div class="text-center">
	        <small style="color: white;">Press Proceed for Next Patient</small><br>
	        <small class="text-light msgAlertTimer"></small><br><br>
		    <button class="btn btn-warning btn-sm savePrescAlertCancelBtn" type="button">Cancel</button>
		    <button class="btn btn-info btn-sm savePrescAlertOkBtn" style="margin-left:10px" type="button">Proceed</button>
	    </div>
	</div>
	
		<div id="patSideListId" class="patSideList">
			<legend class="text-center totalPatHeader">Patient List <span class="badge" style="background-color: #36c6D3;">5</span></legend>
			<p class="patCatHeader waitingPatHeader">Waiting <span class="badge" style="background-color: #ED6B75;">5</span></p>
			<ul id="waitingPatList"> 
			</ul>
			<p class="patCatHeader visitedPatHeader">Attended <span class="badge" style="background-color: #F1C40F;">5</span></p>
			<ul id="visitedPatList"> 
			</ul>
		</div>
		
		 <div id="patSideListId1" style="top: 2.5vh !important;" class="patSideList">
			<legend class="text-center totalPatHeader" style="text-transform: capitalize  !important;background:cadetblue;color:white">TeleConsultancy Request (Today) 
			
			<a href="#" class="closee-consultancy"  data-placement="bottom" data-toggle="tooltip" title="Close Patient Approval List" >
			<font>&times;</font>
			</a>
			</legend>
			
			<ul id="waitingPatList" style="overflow-y: auto;overflow-x: hidden;height: 89.5%;"> 
			<%
			ArrayList<String> teleConsultancyApprovalData = (ArrayList<String>)  request.getSession().getAttribute("teleConsultancyApprovalData");
			if(teleConsultancyApprovalData !=null && teleConsultancyApprovalData.size() >0){
			%>
			<input type="hidden" name="showpatientCount" id="showpatientCountId" value=<%=teleConsultancyApprovalData.size()%> >
		<%	
			}else{
				%>
			
				<input type="hidden" name="showpatientCount" id="showpatientCountId" value="0" >
			<%	
			}
			if(teleConsultancyApprovalData !=null && teleConsultancyApprovalData.size() >0){
				
				//System.out.println("teleConsultancyApprovalData"+teleConsultancyApprovalData.toString());
			for(int approvalval=0;approvalval<teleConsultancyApprovalData.size();approvalval++){
		     JSONObject approvaljsonObj=new JSONObject(teleConsultancyApprovalData.get(approvalval));
			//System.out.println(approvaljsonObj.get("hrgstr_pat_name")+"approvaljsonObj:::"+approvaljsonObj.toString());
			%>
			
			
			<li>
<!-- 			<img class="img-circle img-responsive" src="/HISDRDESK/new_opd/img/img_avatar3.png" style="margin: 3% 2%;float:left; 
			width: 30px; min-width: 20px; box-shadow: 0px 0px 0px 0px rgba(0,0,0,0.2); border: 1px solid #fff;"> -->
			<h5 style="padding: 0px; margin:0px;font-weight: bold;"><%=approvaljsonObj.get("hrgstr_pat_name") %> (<%=approvaljsonObj.get("hrgstr_pat_gender") %>/<%=approvaljsonObj.get("hrgstr_pat_age") %>/<%=approvaljsonObj.get("hrgnum_pat_mobile_no") %>)</h5>
			<p><lable style="font-weight: bold; color:#3ba4e3;"> CR No: <%=approvaljsonObj.get("hrgnum_puk") %></lable> <a href="#" id=<%=approvaljsonObj.get("hrgstr_req_id") %> class="hideme" onclick='approvalData(<%=approvaljsonObj.toString() %> )' data-toggle="tooltip" title="Click To Approve" ><i class="fa fa-check-circle"  style="float: right;"></i></a></p>
			
			<p>Unit:<%=approvaljsonObj.get("hrgstr_deptunit_name") %> </p>
			<p>Raised on:<%=approvaljsonObj.get("request_date") %> </p>
			<p><lable style=" font-weight: bold;">Appointment Slot: <%=approvaljsonObj.get("apointment_date") %>  </lable></p>
			<p>Raised By :self(<%=approvaljsonObj.get("hrgstr_req_id") %>) </p>
			<!-- <p>First right-click on the Wi-Fi icon at the lower right corner area of the screen, then click on Open Network and Sharing Center. Next, click on the Wi-Fi connection, which will</p> -->
			</li>
			<%
				}
			}else{
			%>
			<p align="center"> <lable style=" font-weight: bold;"> No Data Found </lable> </p>
			<%
			}
			%>
			</ul>
			<p class="patCatHeader visitedPatHeader" style="padding: 15px !important"> <span class="badge" style="background-color: #F1C40F;"></span></p>
			<ul id="visitedPatList"> 
			</ul>
		</div>
		
		
		
		
		
		
		
		<div id="patSideListId2" style="top: 2.5vh !important;" class="patSideList">
			<legend class="text-center totalPatHeader" style="text-transform: capitalize  !important;background:cadetblue;color:white">Referral Approval List 
			
			<a href="#" class="closee-consultancy1"  data-placement="bottom" data-toggle="tooltip" title="Close Patient Approval List" >
			<font>&times;</font>
			</a>
			</legend>
			
			<ul id="waitingPatList2" style="overflow-y: auto;overflow-x: hidden;height: 89.5%;"> 
			<%
			ArrayList<String> RefeConsultancyApprovalData = (ArrayList<String>)  request.getSession().getAttribute("RefeConsultancyApprovalData");
			//System.out.println("RefeConsultancyApprovalData.size()"+RefeConsultancyApprovalData.size());
			if(RefeConsultancyApprovalData !=null && RefeConsultancyApprovalData.size() >0){
			%>
			<input type="hidden" name="showpatientCount" id="showpatientCountId" value=<%=RefeConsultancyApprovalData.size()%> >
		<%	
			}else{
				%>
			
				<input type="hidden" name="showpatientCount" id="showpatientCountId" value="0" >
			<%	
			}
			if(RefeConsultancyApprovalData !=null && RefeConsultancyApprovalData.size() >0){
				
				
				//System.out.println("teleConsultancyApprovalData"+teleConsultancyApprovalData.toString());
			for(int approvalval=0;approvalval<RefeConsultancyApprovalData.size();approvalval++){
			JSONObject approvaljsonObj=new JSONObject(RefeConsultancyApprovalData.get(approvalval));
			//System.out.println("approvaljsonObj:::"+approvaljsonObj.toString());
			%>
			
			
			<li>
<!-- 			<img class="img-circle img-responsive" src="/HISDRDESK/new_opd/img/img_avatar3.png" style="margin: 3% 2%;float:left; 
			width: 30px; min-width: 20px; box-shadow: 0px 0px 0px 0px rgba(0,0,0,0.2); border: 1px solid #fff;"> -->
			<h5 style="padding: 0px; margin:0px;font-weight: bold;"><%=approvaljsonObj.get("patname") %> </h5>
			<p><lable style="font-weight: bold; color:#3ba4e3;"> CR No: <%=approvaljsonObj.get("patcrno") %></lable> <a href="#" id=<%=approvaljsonObj.get("fromdepartment") %> class="hideme" onclick='approvalData1(<%=approvaljsonObj.toString() %> )' data-toggle="tooltip" title="Click To Approve" ><i class="fa fa-check-circle"  style="float: right;"></i></a></p>
			
			<p>Refer from:<%=approvaljsonObj.get("fromdepartmentunit") %> </p>
			<p>Refer to:<%=approvaljsonObj.get("todepartmentunit") %> </p>
			<p><lable style=" font-weight: bold;">UMID: <%=approvaljsonObj.get("umid_no") %>  </lable></p>
			<p>Remarks :(<%=approvaljsonObj.get("remarks") %>) </p>
			<!-- <p>First right-click on the Wi-Fi icon at the lower right corner area of the screen, then click on Open Network and Sharing Center. Next, click on the Wi-Fi connection, which will</p> -->
			</li>
			<%
				}
			}else{
			%>
			<p align="center"> <lable style=" font-weight: bold;"> No Data Found </lable> </p>
			<%
			}
			%>
			</ul>
			<p class="patCatHeader visitedPatHeader" style="padding: 15px !important"> <span class="badge" style="background-color: #F1C40F;"></span></p>
			<ul id="visitedPatList2"> 
			</ul>
		</div>
		
		
		
		
		
		
		
		
		
		
<div id="wrapper">
  <div class="overlay"></div>
  
  <!-- Sidebar -->
  <nav class="navbar navbar-inverse navbar-fixed-top containerNav" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
      <li class="sidebar-brand"> <a href="#" onclick="return false;"> Preferences <button class="btn btn-xs" type="button" onclick="hideOverlay()"><i style="color: black" class="glyphicon glyphicon-pushpin"></i></button></a> </li>
      <!-- <li> <a href="#"><i class="fa fa-fw fa-home"></i> Home</a> </li> -->
      <!-- <li> <a href="#"><i class="fa fa-fw fa-folder"></i> Page one</a> </li>
      <li> <a href="#"><i class="fa fa-fw fa-file-o"></i> Second page</a> </li>
      <li> <a href="#"><i class="fa fa-fw fa-cog"></i> Third page</a> </li> -->
      <!-- <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-fw fa-plus"></i> Dropdown <span class="caret"></span></a>
        <ul class="dropdown-menu" role="menu">
          <li class="dropdown-header">Dropdown heading</li>
          <li><a href="#">Action</a></li>
          <li><a href="#">Another action</a></li>
          <li><a href="#">Something else here</a></li>
          <li><a href="#">Separated link</a></li>
          <li><a href="#">One more separated link</a></li>
        </ul>
      </li> -->
      <!-- <li> <a href="index.html"><i class="fa fa-fw fa-bank"></i> Page1</a> </li> 
      <li> <a href="index2.html"><i class="fa fa-fw fa-bank"></i> Page2</a> </li>  -->
      <h4 style="margin: 16px 0 8px 0px;text-align: left;">Switch Between Desks</h4>
      <p style="margin: 1px 0px;"> <label><input style="vertical-align: top" class="uk-radio" type="radio" name="desk" checked> OPD</label> <label><input style="vertical-align: top" class="uk-radio" type="radio" name="desk" > IPD</label> <label><input style="vertical-align: top" class="uk-radio" type="radio" name="desk" > Casuality</label></p>
      <h4 style="margin: 16px 0 8px 0px;text-align: left;">Prescription Without List</h4>
      <p align="center" style="margin: 1px 0px;"><b style=" vertical-align: top;">Off</b>&nbsp;<label class="switch">
      				   <input type="checkbox" class="prescWithoutLst" onchange="onChangePrescWithoutLst(this, event)" value="1">
      				   <span class="slider round"></span> 
      				</label>&nbsp;<b style="vertical-align: top;">On</b></p>
      <h5 style="margin: 16px 0 8px 0px;text-align: left;">Switching Prescription Mode</h5>
      <p style="margin: 1px 0px;"> <label><input style="vertical-align: top" class="uk-radio" type="radio" name="prescMode" value="1"> Tile</label> <label><input style="vertical-align: top" class="uk-radio" type="radio" name="prescMode" value="2" checked> Page</label> <label><input style="vertical-align: top" class="uk-radio" type="radio" name="prescMode" value="3" > Modal</label></p>
      <h4 style="margin: 16px 0 8px 0px;text-align: left;">Patient Listing Criteria <i style="color: #EDC10F" class="fa fa-star"></i></h4>
      <p style="margin: 1px 0px;padding:2px 10px"><label><input style="vertical-align: top" class="uk-checkbox" onchange="patLstColorSrchNewVisit(this,event)" type="checkbox" name="patientListCriteria" checked> New Visit</label> <i class="fa fa-square" style="float: right; color: #A7FBD0; text-shadow: 2px 2px 4px black;"></i></p>
      <p style="margin: 1px 0px;padding:2px 10px"><label><input style="vertical-align: top" class="uk-checkbox" onchange="patLstColorSrchReffered(this,event)" type="checkbox" name="patientListCriteria" checked> Reffered</label> <i class="fa fa-square" style="float: right;  color: #D481FA; text-shadow: 2px 2px 4px black;"></i></p>
      <p style="margin: 1px 0px;padding:2px 10px"><label><input style="vertical-align: top" class="uk-checkbox" onchange="patLstColorSrchAppointment(this,event)" type="checkbox" name="patientListCriteria" checked> Appointment Based</label> <i class="fa fa-square" style="float: right;  color: #91061D; text-shadow: 2px 2px 4px black;"></i></p>
	  <p style="margin: 1px 0px;padding:2px 10px"><label><input style="vertical-align: top" class="uk-checkbox" onchange="patLstColorSrchOther(this,event)" type="checkbox" name="patientListCriteria" checked> Other</label> <i class="fa fa-square" style="float: right; color: #F9F9F9; text-shadow: 2px 2px 4px black;"></i></p>
      <p style="margin: 1px 0px;padding:2px 10px"><a href="#"> ...more</a></p>
      <h4 style="margin: 16px 0 8px 0px;text-align: left;">Queue No Sort By <i style="color: #EDC10F" class="fa fa-star"></i></h4>
      <p style="margin: 1px 0px;padding:2px 10px"> <label> <input style="vertical-align: top" class="uk-checkbox" type="checkbox" name="queueSortDept" checked> Dept</label> <label><input style="vertical-align: top" class="uk-checkbox" type="checkbox" name="queueSortUnit"> Unit</label></p>
      <h4 style="margin: 16px 0 8px 0px;text-align: left;">Prescription <i style="color: #EDC10F" class="fa fa-star"></i></h4>
      <p style="margin: 1px 0px;padding:2px 10px"><label><input style="vertical-align: top" class="uk-checkbox" type="checkbox" name="prescription" checked> Reason Of Visit</label></p>
      <p style="margin: 1px 0px;padding:2px 10px"><label><input style="vertical-align: top" class="uk-checkbox" type="checkbox" name="prescription"> Diagnosis</label></p>
      <h4 style="margin: 16px 0 8px 0px;text-align: left;">Auto Save <input style="vertical-align: top" class="uk-checkbox" type="checkbox" name="autoSave" checked></h4>
      <h4 style="margin: 16px 0 8px 0px;text-align: left;">Font Size <button class="btn btn-default btn-xs" type="button" onclick="resizeText(-1)"><small>A</small></button><button class="btn btn-default btn-sm" type="button" onclick="resizeText(1)">A</button></h4>
      <!-- <legend>Legend</legend>
      <p style="margin: 1px 0px;padding:2px 10px">P <i class="fa fa-arrow-right"></i> Provisional <i class="fa fa-square" style="float: right;  color: #d4d4d4; text-shadow: 2px 2px 4px black;"></i></p>
      <p style="margin: 1px 0px;padding:2px 10px">F <i class="fa fa-arrow-right"></i> Final <i class="fa fa-square" style="float: right;  color: rgba(35, 104, 194, 0.8); text-shadow: 2px 2px 4px black;"></i></p>
	  <div class="col-sm-10 col-sm-offset-1" style="border:1px solid grey; height: 100px; margin-bottom: 10px; padding: 4px 4px;">
	  	<h6 style="margin: 4px 0;">Summary Stats</h6>
	  	<h6 style="margin: 4px 0;">IPD/EMERG</h6>
	  </div>  -->     
	  <br> 
    </ul> 
  </nav>

  <!-- Page Content -->
  <div id="page-content-wrapper" style="background-color: #eaeef3;"> 
    <div class="container-fluid"> 
      <div class="row mainTopHeader" style="background-color: #fff;">
        
        <div class="col-sm-0">
        	 <button style="display:none;" type="button" id="hamburger" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas"> <span class="hamb-top"></span> <span class="hamb-middle"></span> <span class="hamb-bottom"></span> </button>
        </div>
        <div class="col-sm-12 " style="box-shadow: 0 0 10px 3px rgba(0,0,0,0.14); padding-bottom: 0px; padding-top: 15px; padding-right: 0px;padding-left: 5px;margin-bottom: 1px;" >
          <div class="row">
          	<div class="col-sm-2" style="padding-right: 0px;padding-left: 0px;">
          		<h2 style="margin:0px 15px;font-size: 1.6rem;display:inline-block"><i class="fa fa-user" style="color: #1a7bda;"></i> <a style="text-decoration:none;color: rgba(23, 97, 194, 0.74);font-weight: bold;font-size: 20px;" href="javascript:;"> DOCTOR DESK</a></h2>
          		<p class="text-center" style="font-size:0.95em;margin-top: 0px;margin-bottom: 0px;float: right;font-weight:bold; margin-right:10px"><font class="visitedPatCount" style="color: rgba(35, 104, 194, 0.8)"></font> of <a style="color:black" class="totalPatCount" title="Total : 100 Approx waiting" onclick="$('#patListStatModal').modal('show');"></a> attended</p>
          	</div> 
          	<div class="col-xs-12 col-sm-1">
          		<button style="float: right; color:#4067c5;font-size: 20px;" class="btn btn-link fullScreenDeskBtn" type="button" onclick=""><i class="glyphicon glyphicon-fullscreen"></i></button>
          	</div> 
          	<div class="clearfix visible-xs" style="margin: 10px;"></div>
          	<div class="col-xs-12 col-sm-5 form-horizontal">
          		<div class="form-group" style="margin-bottom:0px;">
          		<p style="color: #0b3da1; font-weight: bold;padding-top:0" class="control-label col-xs-4 col-sm-3" for="deptUnitName">DEPT/UNIT </p>
                <div class="col-xs-8 col-sm-7" style="">
                  <select name="deptUnitName" id="deptUnitName" class="form-control" style="color: #082b71;border-radius: 0px;" onchange="getDeltList(this);">
            		<!-- <option value="0#0#0">ALL</option> -->
                    <%
                    HashMap<String ,String> DeptCMB= (HashMap) request.getSession().getAttribute("DeptDTL");
                   	//System.out.println("DeptCMB"+DeptCMB);
                   	if(DeptCMB!=null)
                   	{
                   	for(Map.Entry m:DeptCMB.entrySet()){ 
                   	 String DeptCode=(String)m.getKey();
            		 String DeptName=(String)m.getValue();
            		 String SelectedDeptId=(String)request.getSession().getAttribute("SelectedDeptId");
            		 if(SelectedDeptId.equalsIgnoreCase(DeptCode))
            		 {
            			 %>
                      	<option value="<%=DeptCode%>" selected><%=DeptName %></option>
                     
                      <% 
            		 }else
            		 {
                   	 %>
                     	<option value="<%=DeptCode%>"><%=DeptName %></option>
                    
                     <%
            		 }
                   		}	
                     }
                     %>
                   
            	  </select>
                </div>
          		<button id="patSideListBtn1"  title="" style="float: right;padding:4px 12px; color:#4067c5 ;" class="btn btn-link" type="button">
          		<!-- <i   class="fa fa-list"></i> -->
          		<i   class="fa fa-user-md"></i>
          		<span style="position:absolute;top:-1px;right:1px;color:white;border-radius:25px;display:none;" id="patSideListSpan1" class="blink label label-danger">0</span>
          		<!-- <i style="position:absolute;top:-1px;right:1px;color:red;display:none" class="fa fa-phone"><span id="patSideListSpan1" style="color:tomato"></span></i> --></button>
          		
          		<!-- Old list button for referral list as in teleconsultancy -->
          		<!-- <button id="patSideListBtn2"  title="" style="float: right;padding:4px 12px; color:#4067c5;display:none" class="btn btn-link" type="button"> -->
          		
          		<button id="patSideListBtn3" onclick="OPENREveistProcess(this)"  title="" style="float: right;padding:4px 12px; color:#4067c5 ;" class="btn btn-link" type="button">
          		<i   class="fa fa-list"></i>
          		<span style="position:absolute;top:-1px;right:1px;color:white;border-radius:25px;display:none;" id="patSideListSpan2" class="blink label label-danger">0</span>
          		<!-- <i style="position:absolute;top:-1px;right:1px;color:red;display:none" class="fa fa-phone"><span id="patSideListSpan1" style="color:tomato"></span></i> --></button>
          		
          		
          		</div>
          	</div>
          	<div class="col-xs-12 col-sm-3 form-horizontal toggleForFullScreenDeskBtn">
          		<div class="form-group has-feedback" style="margin-bottom:0px;float:right">
          		
          		
	                <p style="color: #0b3da1; font-weight: bold;padding-top:0" for="listDateFilter" class="control-label col-xs-2 col-sm-2">DATE </p>
	                <div class="col-xs-9 col-sm-8">    
					  <input type="text" style="padding-top: 0;border-radius: 0px;" class="form-control" name="strPrevDate" id="listDateFilter" autocomplete="off"> 
			          <span class="glyphicon glyphicon-calendar form-control-feedback"></span>
	                </div> 

          	   </div>   
          	</div>
          	<!--  ################################# Added for IPD switch  ############################################# -->
          	<div class="col-xs-12 col-sm-2" style="display:none;">
          		<p style="display:block">
                          <label>IPD</label> &nbsp;
                          <label class="switch" style="vertical-align: middle;">
                             <input type="checkbox"  value="1" checked="" onclick="showIpdList('SHOWIPDLIST');">
                             <span class="slider round"></span> 
                          </label>
                          &nbsp;<label>OPD</label>
                        </p>
          	</div>
          	<!-- <div class="col-xs-12 col-sm-1">
          		<button style="float: left; color:#4067c5" class="btn btn-link fullScreenDeskBtn" type="button" onclick=""><i class="glyphicon glyphicon-fullscreen" style="font-size:25px;"></i></button>
          	</div> -->
          	<!-- <div class="col-xs-8 col-xs-offset-1 col-sm-2 col-sm-offset-0" style="padding:0px 0px;">
          		<h3 class="text-center prescNxtBtn" style="margin-top: 0px !important; display: none;"><a style="text-decoration: none; " class="nextPatientPresc"><i class="fa fa-wheelchair"></i> next<i class="fa fa-angle-double-right"></i></a></h3>
          	    <div class="uk-search uk-search-navbar mainHeaderSrch" style="border: 1px solid #d7d7d7; margin-bottom: 3px;">
 		       	    <span uk-search-icon="" class="uk-search-icon uk-icon"><svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><circle fill="none" stroke="#000" stroke-width="1.1" cx="10.5" cy="10.5" r="9.5"></circle><line fill="none" stroke="#000" stroke-width="1.1" x1="23" y1="23" x2="17" y2="17"></line></svg></span>
  	                <input class="uk-search-input" placeholder="Search..." onkeyup="globalSearch(this,event)" onblur="globalSearch(this,event)" onfocus="globalSearch(this,event)" maxlength="15" list="searchPatLst" type="search"> 
  	                <datalist id="searchPatLst"> 
  	                </datalist>
  	            </div>
          			<input type="search" placeholder="Search" class="form-control" name="" style="height: 4em"> 
          	</div> -->
          	<div class="col-xs-0 col-sm-0">
	  	           <!--  <a href=""><img src="/HISDRDESK/new_opd/img/scanQR_n.png" style="width: 3.95em;"></a>  -->
	  	            <div class="clearfix visible-xs-block"></div>
          	</div>
          	
           </div>
        </div>
       </div> 

       <!-- <br> -->
       <div class="row">
       	  <!-- <div class="row" style="margin-top: 2px; margin-left: 5px !important; margin-right: 10px !important; background-color: #009fef; box-shadow: 0 4px 10px 1px rgba(0,0,0,0.14)">
       		<div class="leftPanelHeader col-sm-12" style="padding: 5px 1px 5px 15px;">
       			<div class="col-xs-6 col-sm-3" style="padding-left: 2px;padding-right: 2px;"><p style="color: #fff;font-size: 1.29em;text-shadow: 0 0 10px #d4d4d4;">Patient <a style="text-decoration: none; color: white;" onclick="return false;"><i class="fa fa-sort"></i></a></p></div>
       			<div class="hidden-xs col-sm-3" style="padding-left: 2px;padding-right: 2px;"><p style="color: #fff;font-size: 1.29em;text-shadow: 0 0 10px #d4d4d4;">Gen/Age/Cat/Mobile</p></div>
       			<div class="hidden-xs col-sm-2" style="padding-left: 2px;padding-right: 2px;"><p style="color: #fff;font-size: 1.29em;text-shadow: 0 0 10px #d4d4d4;">Dept/Unit</p></div>
       			<div class="hidden-xs col-sm-3" style="padding-left: 2px;padding-right: 2px;"><p style="color: #fff;font-size: 1.29em;text-shadow: 0 0 10px #d4d4d4;">Visit Reason</p></div>
            	<div class="col-xs-6 col-sm-1 prescriptionColShow" style="padding-left: 2px;padding-right: 2px;"><p style="color: #fff;font-size: 1.29em;text-shadow: 0 0 10px #d4d4d4;">Advices/EMR</p></div>
       		</div>
       	   </div> -->
       	   <!-- <br> --> 
       	   <input type="hidden" name="strRailTailFlg" id="strRailTailFlgId" value="${DoctorDeskFB.strRailTailFlg}" />
		
       	   <div class="col-sm-12 uk-card uk-card-default leftPanel"  style="height: 95vh; padding-top: 5px">  
					<table id="example" class="table table-hover table-condensed table-checkable patientListMainTable" style="width:100%">
					  <thead>
					    <tr>
					      <th></th>
					      <th>Q. No.</th>
					      <th>Status</th>
					      <th>Name</th>
					      <th>Cr. No.</th>
					      <th>Gen/Age/Cat/Mobile</th>
					      <th>Dept/Unit</th>
					      <!-- <th>Visit No</th> -->
					      <th  >UMID</th>
					       <th >Room No</th>
					      <th>Action</th>
					      <th>Prescription</th>
					      <th></th>
					     </tr>
					    </thead>
					     <tbody>  
       	   
       	   <%
						HashMap<String ,List> map= (HashMap) request.getSession().getAttribute("OPDDRDESKDATA");
						//System.out.print("map--->"+map);	
						ArrayList<String> list=null;
								for(Map.Entry m:map.entrySet()){ 
									list=new  ArrayList<String>(); 
							   //System.out.println("NEWWWWWWWWW\n"+m.getKey()+" "+m.getValue());
							   String CrNo1=(String)m.getKey();
							   //System.out.println("List Value crno1::::::"+CrNo1);
							   list=(ArrayList) m.getValue();
							   //System.out.println("LISTDATA:"+list);
							   String CrNo=list.get(0);
							   //System.out.println("crno called --->"+ CrNo);
							  // System.out.println("list get---->"+ list.get(47)+" "+list.get(48)+" "+list.get(49));
								     %>
					              <tr class="patientListBlock isAttended_<%=list.get(24) %> visitType_<%=list.get(27) %>" attended="<%=list.get(24) %>">
					                       
				                       <%  
				                          if(list.get(3).split("/")[0].equals("F"))
				                          { 
				                          %>
				                          
				                        <td><img class="img-circle img-responsive patProfileImg" src="/HISDRDESK/new_opd/img/img_avatar3.png" style="width: 30px; min-width: 26px; display:inline-block; /* box-shadow: 0px 0px 15px 2px rgba(0,0,0,0.2); */ border: 1px solid #fff;">
				                        <% if(list.get(24).equals("1"))
				                        { %>
				                        <i class="isAttended attended"></i>
				                          <% } %>
				                          
				                           <% if(list.get(47) != null && list.get(47).equals("1"))
				                        	 {%>
				                        		<i class="fa fa-phone" aria-hidden="true" style="color: #74aeec;"></i>
				                        		<!-- <b style="color: #74aeec;font-size: 16px;">T</b> -->
				                           <%}%>
				                          </td>
				                         <% }
				                          else
				                          {
				                          %>
				                       <td><img class="img-circle img-responsive patProfileImg" src="/HISDRDESK/new_opd/img/img_avatar.png" style="width: 30px; min-width: 26px; display:inline-block; /* box-shadow: 0px 0px 15px 2px rgba(0,0,0,0.2); */ border: 1px solid #fff;">
										<% if(list.get(24).equals("1"))
				                        { %>
				                        <i class="isAttended attended"></i>
				                          <% } %>
				                          
				                           <% if(list.get(47) != null && list.get(47).equals("1"))
				                        	 {%>
				                        		<i class="fa fa-phone" aria-hidden="true" style="color: #74aeec;"></i>
				                        		<!-- <b style="color: #74aeec;font-size: 16px;">T</b> -->
				                           <%}%>
										</td> 
				                         <% } %>
				                          <td class="patQNo text-center"><%=list.get(1) %></td>
				                          <% if(list.get(24).equals("1")) { %>
				                          <td class="patStatus"><a style="cursor: default;" class="label label-success">Waiting</a></td>
				                          <% } else {%>
				                          <td class="patStatus"><a style="cursor: default;" class="label label-danger">Attended</a></td>
				                          <% } %>
				                          <% if(list.get(11).equals("1"))
				                          {
				                          %>
				                          <td class="patName" style="color:#7896e3; font-weight:bold;"><%=list.get(2) %></td>
				                          <td class="patCrNo" style="color:#7896e3; font-weight:bold;"><%=CrNo%></td>   
				                          <% } else 
				                        	  {%> 
				                          <td class="patName"><%=list.get(2) %></td>
				                          <td class="patCrNo"><%=CrNo%></td>  
				                          <% }%>
				                          <td class="patGenAgeCat"><%=list.get(3)%>/<%=list.get(4)%>/<%=list.get(43)%></td>
				                          <td class="patDeptUnit"><%=list.get(22) %>/<%=list.get(23) %></td>
				                          <%-- <td><a style="text-decoration:none;padding: .4em .5em .4em;font-size: 85%;border-radius: .1em; background-color: #ed6b75;" class="label label-danger"><% out.print(list.get(5).toString().length()<= 2 ? "": list.get(5));%></a></td> --%>
				                          <%-- <td><%=list.get(11)%></td> --%>  <!-- label label-danger -->
				                          <td class="patRoomNo"><% out.print(list.get(7)==null ? "-" : list.get(7)); %></td>
				                          <td class="patRoomNo"><% out.print(list.get(7)==null ? "-" : list.get(7)); %></td>
				                         
				                           <td>
					                          <font class="patEpisodeCode" style="display:none"><%=list.get(10)%></font>
					                          <font class="patEpisodeVisitNo" style="display:none"><%=list.get(11)%></font>
					                          <font class="patLastVisitDate" style="display:none"><%=list.get(18)==null?"":list.get(18)%></font>
					                          <font class="patVisitType" style="display:none"><%=list.get(27)==null?"":list.get(27).equals("1")?"OPD General":"" %><%=list.get(27)==null?"":list.get(27).equals("2")?"Emergency MLC":"" %><%=list.get(27)==null?"":list.get(27).equals("3")?"Emergency Non MLC":"" %><%=list.get(27)==null?"":list.get(27).equals("4")?"OPD Special":"" %></font>  <!-- Check for Null condition added by Timsi to handle NullPointerException. -->
					                          <%-- <font class="patVisitType" style="display:none"><%=list.get(27)==null?"":list.get(27).equals("1")?"OPD General":list.get(27).equals("2")?"Emergency MLC":list.get(27).equals("3")?"Emergency Non MLC":list.get(27).equals("4")?"OPD Special":"" %><font> --%>
					                          <%-- <% out.print(list.get(27)==null?"":list.get(27).equals("1")?"OPD General":list.get(27).equals("2")?"Emergency MLC":list.get(27).equals("3")?"Emergency Non MLC":list.get(27).equals("4")?"OPD Special":""); %> --%>
					                          <font class="patGaurdianName" style="display:none"><%=list.get(8)%></font>
					                          <font class="patHospitalCode" style="display:none"><%=list.get(26)%></font>
					                          <font class="patSeatId" style="display:none"><%=list.get(34)%></font>
					                          <font class="patConsultantName" style="display:none"><%=list.get(51) %></font>
					                          <font class="patOthersDetails" style="display:none"><%=list.get(52) %></font>
					                          <font class="UMIDFlagVal" style="display:none"><%=list.get(53) %></font>
					                          <%--<font class="loginConsultantName" style="display:none"><%=request.getSession().getAttribute("UserName").toString() %></font> --%>
					                      
					                        <% 
						                       if(list.get(31)==null){ //out.print(list.get(31)); %>
						                       		<button type="button" class="btn btn-outline-success btn-sm prescriptionColBtn" data-toggle="tooltip" title="click for patient Precription" onclick="return showPrescription(this,event)">Rx</button>
					                         <%   } 
					                           else if(list.get(31).equals("0")){ //out.print(list.get(31)); %>
					                        	   <button type="button" class="btn btn-outline-success btn-sm prescriptionColBtn" onclick="return showPrescription(this,event)">Rx</button>
					                         <%   } 
					                           else{ //out.print(list.get(31)); %>
					                        	   <button type="button" class="btn btn-referal btn-sm prescriptionColBtn" onclick="return showPrescription(this,event)">Rx</button>
				                        	   <%    }
					                        %> 
					                         <!-- <button type="button" class="btn btn-outline-success btn-sm prescriptionColBtn" onclick="return showPrescription(this,event)">Rx</button> -->
				                            <% 
						                       if(!list.get(44).equals("1")){
						                    	%>   
				                             <button type="button" class="btn btn-outline-info opdVitalBtn" style="background-color:#7C9CDE !important; border:0px;" onclick="openModalForVital(this,event);"  >Vitals/GE</button> <!-- add class "emrLink" for EMR-->
				                             <%    } %>
				                             </td>
				                             
				                          <td>
				                         
				                           <% 
				                           //out.print(list.get(42));
				                           if(!list.get(42).equals("0")) { %>
				                          	<button type="button" class="btn btn-outline-reprint btn-sm printPrescriptionMainDeskTriggerBtn" onclick="printPrescriptionMainDeskFun(this);">Reprint</button>
				                          <% } %>
				                          </td>
				                          <td class="patIsPrescUploaded"><a style="cursor: default;background-color:#cf4e24" class="label label-success"><%=(list.get(41).equals("0")? "":"P")%></a></td>  
				                          <input type="hidden" name="patCompleteGeneralDtl" value="<%=list.get(5)+"#"+list.get(6)+"#"+list.get(7)+"#"+list.get(8)+"#"+list.get(11)+"#"+list.get(13)+"#"+list.get(14)+"#"+list.get(17)+"#"+list.get(22)+"#"+list.get(23)+"#"+list.get(19)+"#"+list.get(35)+"#"+list.get(36)+"#"+list.get(37)+"#"+list.get(43)+"#"+list.get(42)+"#"+list.get(45)+"#"+list.get(46)+"#"+list.get(47)+"#"+list.get(48)+"#" %>" >
				                           <input type="hidden" name="patCompleteGeneralDtl1" value='<%=list.get(49)%>' >
				                           <input type="hidden" name="teleConsultancyRequestId" value='<%=list.get(50)%>' >
				                           <!-- ################ Added for reason for visit from registration #################### -->
				                           <input type="hidden" name="ReasonForVisitFormRegistration" value='<%=list.get(5)%>' >
				                           <!-- ################ Added for reason for visit from registration end #################### -->
									  </tr>
									   
									<%   
									    
							  }  
					%> 
					                   </tbody>
					                  </table>  
					                  <legend>Legends</legend>
					                  <p style="display:inline-block"><i class="fa fa-circle" style="color:#7896e3"></i> New Visit</p>
					                  <p style="display:inline-block; margin-left:20px;"><i class="fa fa-circle"></i> Old Visit</p>
					                  <p style="display:inline-block; margin-left:20px;"><i class="fa fa-circle" style="color:#E0A800"></i> Refered In</p> 
								    </div>  
   								<div class="col-sm-12 uk-card uk-card-default uk-card-hover rightPanel" style="display: none;background-color: #eaeef3;">
						      		<button style="float: right;padding:4px 12px;" class="btn btn-danger btn-sm rightPanelClose" type="button" onclick="hidePrescription(this,event)">x</button>
						      		<button style="float: right;padding:4px 12px;" class="btn btn-link shortcutInfoRightPanel" onclick="$('#shortcutKeysModal').modal('show');" type="button"><i class="fa fa-info"></i></button>
						      		<button style="float: right;padding:4px 12px; color:#87c540" class="btn btn-link patSearchRightPanel" onclick="$('#patientSearchModal').modal('show');" type="button"><i class="material-icons">search</i></button>
						      		<button id="patSideListBtn" style="float: right;padding:4px 12px; color:#4067c5" class="btn btn-link" type="button"><i class="fa fa-users"></i></button>						       		
						       		<button id="patientSumBtn" style="float: right;padding:4px 12px; color:#4067c5" class="btn btn-link patientSumBtn" type="button" onclick="patientSumBtnToggleFun()"><i class="fa fa-wheelchair"></i></button>	
						       		<button style="float: right;padding:4px 12px; color:#4067c5" class="btn btn-link fullScreenDeskBtn" type="button" onclick=""><i class="glyphicon glyphicon-fullscreen"></i></button>
						       		<button style="float: right;padding:4px 12px; color:#4067c5" class="btn btn-link videocallList" type="button" onclick="videocallList(this)"><i class="glyphicon glyphicon-facetime-video"></i></button>						       		
						       	</div>  
						       </div>  
						    </div>
						  </div>
						</div>       
       				

  <input type="hidden" name="hmode"/> 
  
  <input type="hidden" name="strInitialMode" value="${DoctorDeskFB.strInitialMode}"/>
  <input type="hidden" name="strHospitalAddres" value="${DoctorDeskFB.strHospitalAddres}"/>
<input type="hidden" name="strHospitalName" value="${DoctorDeskFB.strHospitalName}"/>
  <input type="hidden" name="strSeatId" value="${DoctorDeskFB.strSeatId}"/>
  <input type="hidden" name="strHiddenDeptCode" value="${DoctorDeskFB.strHiddenDeptCode}"/>
  
  <div class="modal fade" id="shortcutKeysModal" role="dialog">
    <div class="modal-dialog">       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title">Shortcut Keys</h2>
        </div>
        <div class="modal-body text-left">   
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Key</th><th>Description</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Ctrl+s</td><td>Prescription Print Preview</td>
								</tr>
								<tr>
									<td>Ctrl+f</td><td>Open Search popup</td>
								</tr>
								<tr>
									<td>Ctrl+i</td><td>Open Investigation Reports</td>
								</tr>
								<tr>
									<td>Ctrl+m</td><td>Open Mini List</td>
								</tr>
								<tr>
									<td>Ctrl+p</td><td>Open Prescription Image</td>
								</tr>
								<!-- <tr>
									<td>Ctrl+shift+n</td><td>Next Patient Prescription</td>
								</tr> -->
								<tr>
									<td>Esc</td><td>To close any Popup</td>
								</tr>
							</tbody>
						</table> 
					</div>
				</div>
	      </div> 
	    </div>
	  </div>
</div> 
<button type="button" id="prescModalTriggerBtn" style="display: none;" href="#modal-container" uk-toggle></button>

<div id="modal-container" class="uk-modal-container" uk-modal>
    <div class="uk-modal-dialog uk-modal-body prescModalBody" style="height: 85vh;">
        <button class="uk-modal-close-default prescModalCloseBtn" type="button" uk-close></button> 
    </div>
</div>

<!-- <button id="prescModalTriggerBtn" style="display: none;" class="btn btn-default" type="button" data-toggle="modal" data-target="#prescModal"></button>
   <div class="modal fade" id="prescModal" role="dialog">
    <div class="modal-dialog modal-lg">
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Patient Clinical Details</h4>
        </div>
        <div class="modal-body prescModalBody" style="height: 85vh;">
          
        </div> 
      </div>
      
    </div>
  </div> -->

  <!-- Modal - Patient Detail-->
  <div class="modal fade" id="patListStatModal" role="dialog">
    <div class="modal-dialog">       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title">Patient Stats (Under Development)</h2>
        </div>
        <div class="modal-body text-left">   
        		<!-- <p>Total : 100</p>
        		<table class="table table-hover">
        			<thead>
        				<tr>
        					<th></th><th>Walk In</th><th>Appointment</th><th>Casuality</th>
        				</tr>
        			</thead>
        			<tbody>
        				<tr>
        					<td>Total Patient (100) : </td><td>70</td><td>20</td><td>10</td>
        				</tr>
        				<tr>
        					<td>Attended (14) : </td><td>3</td><td>6</td><td>5</td>
        				</tr>
        				<tr>
        					<td>Waiting (86) : </td><td>67</td><td>14</td><td>5</td>
        				</tr>
        			</tbody>
        		</table> -->

	      </div> 
	    </div>
	  </div>
</div>
 
  <!-- Modal - Patient Detail-->
  <div class="modal fade" id="patientDtlModal" role="dialog">
    <div class="modal-dialog">       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title">Patient Detail</h2>
        </div>
        <div class="modal-body">
          <table class="table table-hover">
            <thead> 
            </thead>
            <tbody>
              <tr>
                <td><b>Name</b></td><td><p class="patName">Patient 1</p></td>
              </tr>
              <tr>
                <td><b>CR No.</b></td><td><p class="patCrNo">33456738328939</p></td>
              </tr>
              <tr>
                <td><b>Gender</b></td><td><p>Male</p></td>
              </tr>
              <tr>
                <td><b>Age</b></td><td><p>23</p></td>
              </tr>
              <tr>
                <td><b>Father Name</b></td><td><p>Father</p></td>
              </tr>
            </tbody>
          </table>
        </div>  
      </div> 
    </div>
  </div>
  
  <div class="modal fade" id="printPrescriptionMainDeskModal" role="dialog">
    <div class="modal-dialog modal-lg">       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        </div>
        <div class="modal-body">
       		 <div class="text-center">
       			<button type="button" class="btn btn-warning" onclick="$('#printPrescriptionMainDeskModal').modal('hide');$('#printPrescFrameId').remove();">Cancel</button>
			 </div> 
        </div>  
      </div> 
    </div>
  </div> 
				

<div id="patientSearchModal" class="modal fade" role="dialog">
  <div class="modal-dialog"> 
    <!-- Modal content-->
    <div class="modal-content">
     <!--  <div class="modal-header">
        <h4 class="modal-title">Search Patient</h4>
      </div> -->
      <div class="modal-body"> 
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <div class="uk-search uk-search-navbar mainHeaderSrch" style="border: 1px solid #d7d7d7; margin-bottom: 3px;margin-left: auto; margin-right:auto; display:block;">
 		    <span uk-search-icon="" class="uk-search-icon uk-icon"><svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><circle fill="none" stroke="#000" stroke-width="1.1" cx="10.5" cy="10.5" r="9.5"></circle><line fill="none" stroke="#000" stroke-width="1.1" x1="23" y1="23" x2="17" y2="17"></line></svg></span>
  	        <input id="uk-search-input2" class="uk-search-input" placeholder="Search..." onkeyup="globalSearch2(this,event)" onfocus="globalSearch2(this,event)" maxlength="15" list="searchPatLst2" type="search"> 
  	        <datalist id="searchPatLst2"> 
  	          </datalist>
  	        </div>
      </div> 
    </div> 
  </div>
</div>
   
<button id="emrModalTriggerBtn" style="display: none;" class="btn btn-default" type="button" data-toggle="modal" data-target="#emrModal"></button>
  <!-- Modal -->
  <div class="modal fade" id="emrModal" role="dialog">
    <div class="modal-dialog modal-lg" style="width: 90%;">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">EMR</h4>
        </div>
                <div class="modal-body" style="height: 95vh;">
        	<!-- <div class="row">
        		<div style="text-transform: uppercase;" class="col-sm-6">
        			<p class="emrPatName">Patient Name</p>
        		</div>
        		<div class="col-sm-6">
      			    <p class="emrPatCrNo" style="float: right">3353131321321</p>
        		</div>
        	</div> -->
        
        <div class="row">
          <div id="jstree_demo_div" class="col-sm-3" style="max-width: 30%; overflow-x: hidden;">
            <ul> 
              <li>All
                <ul> 
                  <li>CHB
                  <ul>
                    <li>OPD Wise
                      <ul>
                        <li>Cardiology(CY UT 01)
                          <ul>
                            <li>Episode::1
                              <ul>
                                <li>Enc::1 (12-Jun-2018)</li>
                              </ul>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </li>
                    <li>IPD Wise
                      <ul>
                        <li>AdmissionNo::211012018000209</li>
                      </ul>
                    </li> 
                  </ul>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
          <div class="col-sm-7">
             <ul class="nav nav-tabs">
              <li class="active"><a data-toggle="tab" href="#tab">Patient Detail</a></li>
              <li><a data-toggle="tab" href="#tab1">Reason Of Visit</a></li>
              <li><a data-toggle="tab" href="#tab2">Diagnosis</a></li>  
              <li><a data-toggle="tab" href="#tab3">Investigation</a></li>  
            </ul>
            <div class="tab-content">
              <div id="tab" class="tab-pane fade in active">
                <h4 style="background-color: #009eed; color: white;">Patient Detail</h4> 
                <table class="table table-condensed table-hover table-striped">
                  <tbody>
                    <tr>
                      <td>Name</td>
                      <td><b>Patient</b></td>
                      <td>CRN.</td>
                      <td><b>211011800004461</b></td>
                      <td>Age/Gender</td>
                      <td><b>22 Y/Male</b></td>
                    </tr>
                    <tr>
                      <td>Primary Category</td>
                      <td><b>General</b></td>
                      <td>Marital Status</td>
                      <td><b>Single</b></td>
                      <td rowspan="3">Picture</td>
                      <td rowspan="3"><img class="img-responsive img-circle" src="/HISDRDESK/new_opd/img/img_avatar.png" style="width: 75px"></td>
                    </tr> 
                    <tr>
                      <td>Father Name</td>
                      <td><b>General</b></td>
                      <td>Mother Name</td>
                      <td><b></b></td> 
                    </tr>
                    <tr>
                      <td>Spouse Name</td>
                      <td><b></b></td>
                      <td>Religion</td>
                      <td><b></b></td> 
                    </tr>
                    <tr>
                      <td>Monthly Income</td>
                      <td><b>Rs. 0.00</b></td>
                      <td>National ID</td>
                      <td><b></b></td> 
                    </tr>
                    <tr>
                      <td>Adm. No.</td>
                      <td><b>2111012018000209</b></td>
                      <td>Admission Date</td>
                      <td><b>12-Jun-2018</b></td>
                      <td>Department/Unit</td>
                      <td><b>Cardiology/ CY UT 01</b></td>
                    </tr>
                    <tr>
                      <td>Ward/ Room No.</td>
                      <td><b>Ward1/General Room 1</b></td>
                      <td>Bed No</td>
                      <td><b>Rtert</b></td>
                      <td></td>
                      <td><b></b></td>
                    </tr>
                  </tbody>
                </table> 
                <h4 style="background-color: #009eed; color: white; margin: 0px;">Address Detail</h4> 
                <table class="table table-hover table-condensed table-striped">
                  <thead>
                    <tr>
                      <th><i class="fa fa-home"></i> Current Address</th>
                      <th><i class="fa fa-home"></i> Permanent Address</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>324, Khordha, Sadfad, India</td>
                      <td>324, Khordha, Sadfad, India</td>
                    </tr>
                  </tbody>
                </table>
                <hr>
                <div class="row text-center">
                  <button type="button" class="btn btn-info">Cancel</button>
                </div>
              </div>
              <div id="tab1" class="tab-pane fade">
                <h4 style="background-color: #009eed; color: white;">Reason Of Visit</h4>
              </div>
              <div id="tab2" class="tab-pane fade">
                <h4 style="background-color: #009eed; color: white;">Diagnosis</h4>
              </div>
              <div id="tab3" class="tab-pane fade">
                <h4 style="background-color: #009eed; color: white;">Investigation</h4>
              </div>
            </div>
        </div>
        </div> 
      </div>
      </div>      
    </div> 
  </div> 
  
  
   <div class="modal fade " id="VitalIDModal" role="dialog">
 <!--   <div class="modal-dialog modal-lg"> -->
 
 <div class="modal-dialog modal-lg">
<div class="modal-content">       
				<div class="modal-body" style="text-align: left;"> 
					<div class="row" style="min-height:400px;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">×</button>
							<h2 class="modal-title text-left">Vitals/GE</h2>
							<div class="row">
								<div class="col-md-5 col-sm-1">
								</div>
								<div class="col-md-6 col-sm-9 col-xs-11" style="text-align:right; font-size: small;">
									<b style="color:#7d7f84;"><span id="patName">Patient Name</span> / <span id="crNo">CR Number</span> / <span id="patAge">Age</span> <span id="patGender"></span></b>
								</div>
								<div class="col-md-1 col-sm-1 col-xs-1 alignRight" style="display:none;">
									<i class="fa fa-print" id="VitalsPrintBtn" data-toggle="tooltip" title="" data-original-title="Print Vitals" onclick="$('#VitalIDModal #VitalsPrintBtn [data-toggle=tooltip], .tooltip').tooltip('hide');$('#VitalsPrintBtn').hide();$('#saveBtn').hide();$('#VitalIDModal .close').hide();printDiv('VitalIDModal');$('#VitalsPrintBtn').show();$('#saveBtn').show();$('#VitalIDModal .close').show();$('#VitalIDModal #VitalsPrintBtn [data-toggle=tooltip], .tooltip').tooltip('show');"></i>
								</div>
								<div class="col-md-1 col-sm-1 col-xs-1 alignRight">
									<i class="" id="" data-toggle="tooltip" title=""></i>
								</div>
							</div>
						</div>	
						
						&nbsp;
							  
						<!-- <label for="patient detail">Patient Name / CR Number </label> -->
						 <div class="row">
							<div class="form-group col-md-3 col-sm-6 col-xs-12">
								<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
									<label for="weight">Weight:</label>
								</div>
								<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
									<input type="text" class="form-control" maxvalue="200" id="weightId" placeholder="kgs" name="weight">&nbsp;<span id="weightErrmsg" class="errorMessage"></span>
								</div>
							</div>
						
							<div class="form-group col-md-3 col-sm-6 col-xs-12">
								<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
									<label for="height">Height:</label>
								</div>
								<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
									<input type="text" class="form-control" id="heightId" placeholder="cms" name="height" >&nbsp;<span id="heightErrmsg" class="errorMessage"></span>
								</div>
							</div>

							<div class="form-group col-md-3 col-sm-6 col-xs-12">
								<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
									<label for="bmi">BMI:</label>
								</div>
								<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
									<input type="text" class="form-control" id="bmiId" placeholder="kg/m2" name="bmi" readonly>&nbsp;<span id="bmiErrmsg" class="errorMessage"></span>
								</div>
							</div>

							<div class="form-group col-md-3 col-sm-6 col-xs-12">
								<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
									<label for="temperature">Temp(F):</label>
								</div>
								<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
									<input type="text" class="form-control" id="temperatureId" placeholder="In Fahrenheit" name="temperature">
									&nbsp;<span id="temperatureErrmsg" class="errorMessage"></span>
								</div>
							</div>

						</div>	

				<div class="row">
					<div class="form-group col-md-3 col-sm-6 col-xs-12">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label for="respRate" id="respRate" data-toggle="tooltip" title="Respiration Rate">Resp.Rate:</label>
						</div>
						<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="respRateId" placeholder="breaths/min" name="respRate">&nbsp;<span id="respRateErrmsg" class="errorMessage"></span>
						</div>
					</div>
					<div class="form-group col-md-3 col-sm-6 col-xs-12">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label for="pulseRate" id="pulseRate" data-toggle="tooltip" title="Pulse Rate">Pulse Rate:</label>
						</div>
						<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" onkeypress="return isNumber(event)" id="pulseRateId" placeholder="Pulse Rate" name="haemoglobin">&nbsp;<span id="pulseRateErrmsg" class="errorMessage"></span>
						</div>
					</div>
					<div class="form-group col-md-6 col-sm-12 col-xs-12">
						<div class="col-md-3 col-sm-2 col-xs-3 alignRight" id="bloodPressureDivId">
							<label for="diastolic" id="bloodPressure" data-toggle="tooltip" title="Blood Pressure">BP(mmHg):</label>
						</div>
						<div class="col-md-5 col-sm-5 col-xs-5 alignLeftPaddingLeftZero">
							<div class="col-md-10 col-sm-10 col-xs-10 paddingLeftRightZero">
								<!-- <span data-toggle="tooltip" title="Blood Pressure"></span> -->
								<input type="text" class="form-control" id="systolicId"  data-toggle="tooltip" title="Systolic" placeholder="Systolic" name="systolic"><!-- &nbsp;<span id="bpErrmsg" class="errorMessage"></span> -->
								<input type="text" class="form-control" id="systolicId1" style="display: none;"  data-toggle="tooltip" title="Systolic" placeholder="Systolic" name="systolic1">&nbsp;<span id="bpErrmsg" class="errorMessage"></span>	
							</div>
							<div class="col-md-2 col-sm-2 col-xs-2 paddingRightZero" style="font-size:20px;">
								<p><b>/</b></p>
								<!-- <p><b>/</b></p> -->
							</div>
						</div>
						<!-- <div class="col-md-1 paddingLeftRightZero">
						  <label for="separator">/</label>
						</div> -->
						<div class="col-md-4 col-sm-5 col-xs-4 alignLeftPaddingLeftZero">
						<input type="text" class="form-control" id="diastolicId"  data-toggle="tooltip" title="Diastolic"  placeholder="Diastolic" name="diastolic"><!-- &nbsp;<span id="diastolicErrmsg" class="errorMessage"></span> -->
						<input type="text" class="form-control" id="diastolicId1"  data-toggle="tooltip" style="display: none;" title="Diastolic"  placeholder="Diastolic" name="diastolic1"><!-- &nbsp;<span id="diastolicErrmsg" class="errorMessage"></span> -->
						
						</div>
					</div>
				</div>

				<div class="row">
					
			    </div>

				<div class="row">
					<div class="form-group col-md-3 col-sm-12 col-xs-12">
						<div class="col-md-12 col-sm-12 col-xs-12 alignRight" id="bloodSugarDivId">
							<label for="bloodSugar" id="bloodSugarId" data-toggle="tooltip" title="Blood Sugar">B.S. Levels(mg/dl)</label>
						</div>
					</div>
					<div class="form-group col-md-3 col-sm-4 col-xs-12">
						<div class="col-md-6 col-sm-4 col-xs-4 alignRight">
							<label for="fasting" id="fasting" data-toggle="tooltip" title="Blood Sugar Fast">Fast:</label>
						</div>
						<div class="col-md-6 col-sm-8 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="fastingId" placeholder="Fasting" name="fasting">&nbsp;<span id="fastingErrmsg" class="errorMessage"></span>
						</div>
					</div>
					<div class="form-group col-md-3 col-sm-4 col-xs-12">
						<div class="col-md-6 col-sm-4 col-xs-4 alignRight">
							<label for="ppRate" id="ppRate" data-toggle="tooltip" title="Blood Sugar PP Rate">PP:</label>
						</div>
						<div class="col-md-6 col-sm-8 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="ppRateId" placeholder="PP" name="ppRate">&nbsp;<span id="ppRateErrmsg" class="errorMessage"></span>
						</div>
					</div>
					<div class="form-group col-md-3 col-sm-4 col-xs-12">
						<div class="col-md-6 col-sm-4 col-xs-4 alignRight">
							<label for="hba1c" id="hba1c" data-toggle="tooltip" title="Blood Sugar HBA1C Rate">HBA1C:</label>
						</div>
						<div class="col-md-6 col-sm-8 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="hba1cId" placeholder="%" name="hba1c">&nbsp;<span id="hba1cErrmsg" class="errorMessage"></span>
						</div>
					</div>
									
				</div>
				<div class="row">
				
				<div class="form-group col-md-3 col-sm-6 col-xs-12">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label for="haemoglobin" id="haemoglobin" data-toggle="tooltip" title="Haemoglobin">Hgb:</label>
						</div>
						<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text" class="form-control" id="haemoglobinId" placeholder="g/dL" name="haemoglobin">&nbsp;<span id="haemoglobinErrmsg" class="errorMessage"></span>
						</div>
					</div>
					
					
					<div class="form-group col-md-3 col-sm-6 col-xs-12">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label for="bloodGroup" id="bloodGroup" data-toggle="tooltip" title="Haemoglobin">Blood Group</label>
						</div>
						<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
							<select id="bloodGroupId" class="form-control">
								<option value="0">Select</option>
								<option value="A-">A-</option>
								<option value="B-">B-</option>
								<option value="O-">O-</option>
								<option value="AB-">AB-</option>
								<option value="A+">A+</option>
								<option value="B+">B+</option>
								<option value="O+">O+</option>
								<option value="AB+">AB+</option>
							</select>
						</div>
					</div>
					<!-- ----------------------added for cancer screening--------------- -->
					<div class="form-group col-md-6 col-sm-6 col-xs-12">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label for="cancerScreening" id="cancerScreening" data-toggle="tooltip" title="cancerScreening">Cancer Screening</label>
						</div>
						<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
							<select id="cancerScreeningId" class="form-control" multiple="multiple">
								<option value="0">Select</option>
								<option value="Basal cell carcinoma">Basal cell carcinoma</option>
								<option value="Breast cancer">Breast cancer</option>
								<option value="Carcinoma of Nasopharynx and Larynx">Carcinoma of Nasopharynx and Larynx</option>
								<option value="Cervical Cancer">Cervical Cancer</option>
								<option value="Colorectal cancer">Colorectal cancer</option>
								<option value="Hepatocellular carcinoma">Hepatocellular carcinoma</option>
								<option value="Kaposi sarcoma">Kaposi sarcoma</option>
								<option value="Lung cancer">Lung cancer</option>
								<option value="Lymphoproliferative lesions">Lymphoproliferative lesions</option>
								<option value="Malignant melanoma">Malignant melanoma</option>
								<option value="Occular surface squamous neoplasia">Occular surface squamous neoplasia</option>
								<option value="Oral cancer">Oral cancer</option>
								<option value="Oropharyngeal Cancer">Oropharyngeal Cancer</option>
								<option value="Prostate cancer">Prostate cancer</option>
								<option value="Retinoblastoma">Retinoblastoma</option>
								<option value="Sebaceous gland carcinoma">Sebaceous gland carcinoma</option>
								<option value="Secondary neck cancer">Secondary neck cancer</option>
								<option value="Skin cancer">Skin cancer</option>	
								<option value="Squamous cell carcinoma">Squamous cell carcinoma</option>
								<option value="Thyroid Cancer">Thyroid Cancer</option>
								<option value="Uveal melanoma">Uveal melanoma</option>
							</select>
						</div>
					</div>
					
					<div id="curcumferenceIdDivId" style="display: none;">
					<div class="form-group col-md-3 col-sm-6 col-xs-12">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label for="Curcumference" id="Curcumference" data-toggle="tooltip" title="Head Circumference">Head Circm:</label>
						</div>
						<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text"  class="form-control" id="curcumferenceId" placeholder="cms" name="curcumference">&nbsp;<span id="curcumferenceErrmsg" class="errorMessage"></span>
						</div>
					</div>
					</div>
					<div id="muacDivId" style="display: none;">
					<div  class="form-group col-md-3 col-sm-6 col-xs-12">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label for="MUAC" id="muac" data-toggle="tooltip" title="MUAC">MUAC:</label>
						</div>
						<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
							<input type="text" onkeypress="" class="form-control" id="muacId" placeholder="cms" name="MUAC">&nbsp;<span id="muacErrmsg" class="errorMessage"></span>
						</div>
					</div>
					
				</div>
				</div>

				<div class="row">
				
				<div class="form-group col-md-3 col-sm-6 col-xs-12">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label style="margin-top: 0px" for="Disability1" id="Disability1" data-toggle="tooltip" title="Disability">Disability:</label>
						</div>
						<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
							<input  type="radio" name="Disability" value="Yes">Yes &nbsp
							<input  type="radio" name="Disability" value="No" >No
						</div>
					</div>
					
					<div class="form-group col-md-3 col-sm-6 col-xs-12">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label style="margin-top: 0px" for="Smoking1" id="Smoking1" data-toggle="tooltip" title="Smoking">Smoking:</label>
						</div>
						<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
							<input  type="radio" name="Smoking" value="Yes">Yes &nbsp
							<input  type="radio" name="Smoking" value="No" >No
						</div>
					</div>
					
					
					
					<div class="form-group col-md-3 col-sm-6 col-xs-12">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label style="margin-top: 0px" for="Anemic1" id="Anemic1" data-toggle="tooltip" title="Anemic">Anemic:</label>
						</div>
						<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
							<input  type="radio" name="Anemic" value="Yes">Yes &nbsp
							<input  type="radio" name="Anemic" value="No" >No
						</div>
					</div>
					
					
					<div class="form-group col-md-3 col-sm-6 col-xs-12" id="PregnancyDivId">
						<div class="col-md-6 col-sm-5 col-xs-4 alignRight">
							<label style="margin-top: 0px" for="Pregnancy1" id="Pregnancy1" data-toggle="tooltip" title="Pregnancy">Pregnancy Week:</label>
						</div>
					<div class="col-md-6 col-sm-7 col-xs-6 alignLeftPaddingLeftZero">
					<input type="text" class="form-control" id="PregnancyId" placeholder="" name="Pregnancy" maxlength="2">
							<!-- <input  type="radio" name="Pregnancy" value="Yes">Yes &nbsp
							<input  type="radio" name="Pregnancy" value="No" checked="checked">No -->
						</div> 
					
						
					</div>
					
					

				</div>
				<div class="row">
					<div class="form-group col-md-12 col-sm-12 col-xs-12">
						<div class="col-md-2 col-sm-3 col-xs-3 alignRight">
							<label for="symptoms">Remarks:</label>
						</div>
						<div class="col-md-10 col-sm-9 col-xs-9 alignLeftPaddingLeftZero">
							<textarea class="form-control" id="symptomsId" name="symptoms" maxlength="2000"></textarea>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4">
					</div>
					<div class="col-md-4 alignCenter">
						<button class="btn btn-success saveBtn" id="saveBtn" type="button" onclick="VitalSave(this,event);"><i class="fa fa-save"></i> Save</button>
					</div>
					<div class="col-md-4">
					</div>
				</div>
				
				
				
			<div class="modal fade" id="abcd" >
			    <div  class='modal-dialog modal-lg'>       <!-- Modal content-->
			      <div class="modal-content">

			        <div class="modal-body">
			       		
			        </div>  
			      </div> 
			    </div>
			  </div> 		
				
				
				



				<input type="hidden" name="respRateErrMsgTxt" value=''> 
				<input type="hidden" name="bmiErrMsgTxt" value=''> 
				<input type="hidden" name="temperatureErrMsgTxt" value=''>
				<input type="hidden" name="diastolicErrMsgTxt" value=''>
				<input type="hidden" name="bpErrMsgTxt" value=''>
				<input type="hidden" name="systolicErrMsgTxt" value=''>
				<input type="hidden" name="fastingErrMsgTxt" value=''>
				<input type="hidden" name="haemoglobinErrMsgTxt" value=''>
				<input type="hidden" name="ppRateErrMsgTxt" value=''>
				<input type="hidden" name="hba1cErrMsgTxt" value=''>
				<input type="hidden" name="strPatientDtl" id="strPatientDtlId" value=''>
				<input type="hidden" name="strPatHiddenDtls" id="strPatHiddenDtlsId" value=''>
				<input type="hidden" name="strVitalModify" id="strVitalModifyId" value='0'>

	</div>
</div>	
</div>
</div>
</div>

        <div class="loader"  style="display:none">
          <span></span>
          <span></span>
          <span></span>
          <span></span>
        </div>

</html:form>
</body>

<style>
	.errorMessage{
		color: #193595 !important;
		font-size: 12px !important;
	}
</style>

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#uk-search-input2').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#uk-search-input2'); 

	  var noteContent = '';
	   

	  /*-----------------------------
	  Voice Recognition 
	------------------------------*/

	//If false, the recording will stop after a few seconds of silence.
	//When true, the silence period is longer (about 15 seconds),
	//allowing us to keep recording even when the user pauses. 
	recognition.continuous = true;

	//This block is called every time the Speech APi captures a line. 
	recognition.onresult = function(event) {

	// event is a SpeechRecognitionEvent object.
	// It holds all the lines we have captured so far. 
	// We only need the current one.
	var current = event.resultIndex;

	// Get a transcript of what was said.
	var transcript = event.results[current][0].transcript;

	// Add the current transcript to the contents of our Note.
	// There is a weird bug on mobile, where everything is repeated twice.
	// There is no official solution so far so we have to handle an edge case.
	var mobileRepeatBug = (current == 1 && transcript == event.results[0][0].transcript);

	if(!mobileRepeatBug) {
	noteContent += transcript+' ';
	
	noteTextarea.val(noteContent.trim());
	}
	};

	recognition.onstart = function() { 
	/*instructions.text('Voice recognition activated. Try speaking into the microphone.');*/
	}

	recognition.onspeechend = function() {
	/*instructions.text('You were quiet for a while so voice recognition turned itself off.');*/
	}

	recognition.onerror = function(event) {
	if(event.error == 'no-speech') {
	/*instructions.text('No speech was detected. Try again.');  */
	};
	}
if (noteContent.length) {
noteContent += ' ';
}

recognition.start();
});


$('#uk-search-input2').on('blur', function(e) {
	if(recognition!=null & recognition!=''){
		recognition.stop(); 
	}
});

}
catch(e) {
  console.error(e);
  $('.no-browser-support').show(); 
}

</script>

<!-- \
<script>
$(document).ready(function(){
	$('#example_filter input').prop('id','example_filter_searchId');
});
</script>
-->

<!-- 
<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#example_filter_searchId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	console.log("1");
	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#example_filter_searchId'); 

	  var noteContent = '';
	   
	  console.log("2");
	  /*-----------------------------
	  Voice Recognition 
	------------------------------*/

	//If false, the recording will stop after a few seconds of silence.
	//When true, the silence period is longer (about 15 seconds),
	//allowing us to keep recording even when the user pauses. 
	recognition.continuous = true;

	//This block is called every time the Speech APi captures a line. 
	recognition.onresult = function(event) {

	// event is a SpeechRecognitionEvent object.
	// It holds all the lines we have captured so far. 
	// We only need the current one.
	var current = event.resultIndex;

	// Get a transcript of what was said.
	var transcript = event.results[current][0].transcript;

	// Add the current transcript to the contents of our Note.
	// There is a weird bug on mobile, where everything is repeated twice.
	// There is no official solution so far so we have to handle an edge case.
	var mobileRepeatBug = (current == 1 && transcript == event.results[0][0].transcript);

	if(!mobileRepeatBug) {
	noteContent += transcript+' ';
	
	noteTextarea.val(noteContent.trim());
	console.log("3");
	}
	};

	recognition.onstart = function() { 
	/*instructions.text('Voice recognition activated. Try speaking into the microphone.');*/
	}

	recognition.onspeechend = function() {
	/*instructions.text('You were quiet for a while so voice recognition turned itself off.');*/
	}

	recognition.onerror = function(event) {
	if(event.error == 'no-speech') {
		console.log("4");
	/*instructions.text('No speech was detected. Try again.');  */
	};
	}
if (noteContent.length) {
noteContent += ' ';
}

recognition.start();
});


$('#example_filter_searchId').on('blur', function(e) {
	if(recognition!=null & recognition!=''){
		console.log("5");
		recognition.stop(); 
	}
});

}
catch(e) {
  console.error(e);
  $('.no-browser-support').show(); 
}

</script> 
-->

<style>
	@media only screen and (max-width: 468px){
			 #patientSearchModal .mainHeaderSrch {
		    	display:inline-block !important;
			}
	}
	@media print {
	  .tooltip { visibility: hidden; }
	}
</style>

<script>
console.log($("#showpatientCountId").val()>0);

if($("#showpatientCountId").val()>0){
	$("#patSideListSpan1").css("display","");
	$("#patSideListSpan1").text($("#showpatientCountId").val());
}else{
	$("#patSideListSpan1").css("display","none");
	$("#patSideListSpan1").text("0");
}

			
</script>

<script>



	function printDiv(divName) {

		 $('.pull-right').attr('type','hidden');
	     var printContents = document.getElementById(divName).innerHTML;
	     var originalContents = document.body.innerHTML;

	     document.body.innerHTML = printContents;

	     window.print();

	     document.body.innerHTML = originalContents;
		 $('.pull-right').attr('type','button');

		 $('#opdEmrModal .close').click(function(){
			 $('#opdEmrModal').modal('toggle');
			 $('body').removeClass('modal-open');
			 $('.modal-backdrop').remove();
		 });

		 $('#TreeStructurePrescriptionModal .close').click(function(){
			 $('#TreeStructurePrescriptionModal').modal('toggle');
			 $('body').removeClass('modal-open');
			 $('.modal-backdrop').remove();
		 });

		 $('#VitalIDModal .close').click(function(){
			 $('#VitalIDModal').modal('toggle');
			 $('body').removeClass('modal-open');
			 $('.modal-backdrop').remove();
		 });
		 

		}
	/* function CloseModalPopup() {       
        $("#opdEmrModal").modal('hide');
		} */
	</script>
	
	
</html>

