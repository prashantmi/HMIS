<!DOCTYPE html>
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
    <script src="/HIS/hisglobal/drDeskAssets/jquery/jquery-3.3.1.min.js"></script> 
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
    <!-- JS Tooltip -->
    <!--   <link href="/HISDRDESK/new_opd/css/tooltip.css" rel="stylesheet" type="text/css" />
    <script src="/HISDRDESK/new_opd/js/tooltip.js" type="text/javascript"></script> -->
    <link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/material_icons/icon.css"> 
    <!-- <link href="https://fonts.googleapis.com/css?family=Cormorant+Upright" rel="stylesheet"> --> 
    <!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"> -->
    <!--  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script> 
	<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.1/css/responsive.dataTables.min.css">
	<script src="https://cdn.datatables.net/responsive/2.2.1/js/dataTables.responsive.min.js"></script> -->
    <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/DataTables-1.10.18/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/Responsive-2.2.2/css/responsive.bootstrap4.min.css"/>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/DataTables-1.10.18/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/Responsive-2.2.2/js/dataTables.responsive.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/Responsive-2.2.2/js/responsive.bootstrap4.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/jstree/themes/default/style.min.css" />
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
	<!-- <script type="text/javascript" src="/HISDRDESK/new_opd/js/prescriptionScript.js"></script> --> 
	
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
</head>
<body style="padding-top:15px; background-color:#eaeef3">
<!-- <div id="" style="width:100vw;height:100vh;position:fixed;z-index:99999; background-color:rgba(0,0,0,0.6)">
	<h2 class="text-center" style="color:white; margin-top:13%"><i class="fa fa-spinner fa-spin"></i> Loading...</h2>
</div> -->
	<html:form action="/transaction/DoctorDeskAction.cnt"  name="DoctorDeskFB" type="new_opd.transaction.controller.fb.DoctorDeskFB" method="post" >  
		
	<div class="" style="display:none; position:fixed; z-index:9999; top:18vh; left:35vw; color: #fff; background-color: rgb(51, 143, 13); border-color: rgb(103, 183, 37); font-size: 16px; padding: 2% 5% 1% 5%;">
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
            		 <option value="0#0#0">Select Value</option> 
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
          		</div>
          	</div>
          	<div class="col-xs-12 col-sm-4 form-horizontal toggleForFullScreenDeskBtn">
          		<div class="form-group has-feedback" style="margin-bottom:0px;">
	                <p style="color: #0b3da1; font-weight: bold;padding-top:0" for="listDateFilter" class="control-label col-xs-4 col-sm-4">DATE </p>
	                <div class="col-xs-8 col-sm-7">    
					  <input type="text" style="padding-top: 0;border-radius: 0px;" class="form-control" name="strPrevDate" id="listDateFilter" autocomplete="off"> 
			          <span class="glyphicon glyphicon-calendar form-control-feedback"></span>
	                </div> 
          	   </div>   
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
       
			    <input type="hidden" name="hmode"/> 
			    <input type="hidden" name="strInitialMode" value="${DoctorDeskFB.strInitialMode}"/>
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

