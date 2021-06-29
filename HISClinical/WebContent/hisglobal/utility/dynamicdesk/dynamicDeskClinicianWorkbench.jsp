<!DOCTYPE html>
<%@page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html lang="en">
<head>
	<meta charset="utf-8" >
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
	<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
	<%@page import="hisglobal.vo.UserVO"%>
	<%@page import="hisglobal.config.HISConfig"%>
	<title>eSushrut - G6</title>   
    <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/bootstrap/css/bootstrap.min.css">
    <link href="/HIS/hisglobal/DoctorDesk/gijgo/css/gijgo.min.css" rel="stylesheet" type="text/css" /> 
    <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/fontawesome/css/all.css">
    <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/fontawesome/css/fontawesome.min.css">
	<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/DoctorDesk/css/style.css"> 
	<link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/uikit/css/uikit.min.css" />
	<link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/css/perfect-scrollbar.css">
	<link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/material_icons/icon.css"> 
    <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/DoctorDesk/DataTables-1.10.18/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/DoctorDesk/Responsive-2.2.2/css/responsive.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/DoctorDesk/jstree/themes/default/style.min.css" />
    <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/SweetAlertNew/sweetalert.min.css">
    <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/SweetAlertNew/sweetalert.min.css.map">
    <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/flexdatalist/jquery.flexdatalist.min.css">
	<link rel="stylesheet" href="/HISDRDESK/new_opd/css/mainDesk.css">
    <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/viewerjs/viewer.css">
    
    <script src="/HIS/hisglobal/DoctorDesk/jquery/jquery-3.3.1.min.js"></script>
 	<script src="/HISClinical/hisglobal/utility/dynamicdesk/js/ClinicianWorkbench.js"></script>
    <script src="/HIS/hisglobal/DoctorDesk/popper/popper.min.js"></script>
    <script src="/HIS/hisglobal/DoctorDesk/bootstrap/js/bootstrap.min.js"></script>  
    <script src="/HIS/hisglobal/DoctorDesk/gijgo/js/gijgo.min.js" type="text/javascript"></script>
	<script src="/HIS/hisglobal/DoctorDesk/perfectScrollbar/perfect-scrollbar.js"></script>
	<script src="/HIS/hisglobal/DoctorDesk/uikit/js/uikit.min.js"></script>
	<script src="/HIS/hisglobal/DoctorDesk/uikit/js/uikit-icons.min.js"></script>
	<script src="/HIS/hisglobal/DoctorDesk/tippy/tippy.all.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/DoctorDesk/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/DoctorDesk/DataTables-1.10.18/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/DoctorDesk/Responsive-2.2.2/js/dataTables.responsive.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/DoctorDesk/Responsive-2.2.2/js/responsive.bootstrap4.min.js"></script>
	<script src="/HIS/hisglobal/DoctorDesk/jstree/jstree.min.js"></script> 
	<script src="/HIS/hisglobal/DoctorDesk/viewerjs/viewer.js"></script>  
	<script src="/HIS/hisglobal/DoctorDesk/SweetAlertNew/sweetalert.min.js"></script>
    <script src="/HIS/hisglobal/DoctorDesk/chartjs/Chart.min.js"></script>
	<script src="/HIS/hisglobal/DoctorDesk/hotkeys/hotkeys.min.js"></script>
	
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

<body style="background-color: #eaeef3">
<form id="formOPP" action="/enterNewWorkbench" method="POST" > 
<div class="container-fluid"  style="background:linear-gradient(to bottom,#3277b3,#88b5dd);border-radius:5px;">
	<div  id="doctorDeskHeader" class="col-xl-8 col-md-8 col-sm-8" >
		<i class='fas fa-user-md' style='font-size:20px;font-color:white;'></i>
			Doctor Workbench
			</div>	
		<div align="right" class="col-xl-4 col-md-4 col-sm-4" id="divClosewindow">
			 <button type="button" class="btn btn-danger btn-xs" style="align:left;border-radius:9px;width:60px;">Cancel<i class="fa fa-close"></i></button>
 				
		</div>
		
	</div>
	

   
<div style="border-radius:5px;padding-top:10px;"></div>
		
<div class="container-fluid" id="crNoGo" style="border-radius:5px;background:#fff;padding-top:5px;">
		
<div class="col-lg-7 col-sm-7"></div>

	         	<div class="col-lg-1 col-sm-1" style="text-align:right;">
          		<font class="control-label" style="font-weight:bold;">&nbsp;&nbsp;CR Number</font>
          		</div>
          		<div class="col-lg-3 col-sm-3" >
 				 <input type="text" name="CrNoGo" id="CrNoGoText" placeholder="Type CrNo here...."class="form-control"  style="color: #082b71;height:25px;padding-top:3px;width:350px;" />
				 </div>
				<div class="col-lg-1 col-sm-1" >
 				 <button type="button" class="btn btn-success btn-xs" id="goButton" style="align:left;border-radius:12px;width:60px;" onmouseover="GoButton();">Go</button>
 				 </div>
				
<%
UserVO userVO = (UserVO)session.getAttribute(HISConfig.USER_VO);

String hospitalCode = userVO.getHospitalCode();
%>        	
	<input name="hospCode" value="<%= hospitalCode%>" type="hidden">		
			<div id="CrNoList" class=" col-*-*" style="box-shadow: 0 0 10px 3px rgba(0,0,0,0.14); padding-bottom:5px; padding-top: 10px; padding-right: 0px;padding-left: 5px;margin-bottom: 1px;display:none;" >
		
      			<table id="opdCrNoList" class="table table-condensed table-responsive table-hover" style=padding-left:10px;>
      			<tr>
      				<b>Search result:</b>
      				<th>CR Number </th>
      				<th>Name</th>
      				<th>Age/Gender </th>
      				<th>Precription</th>
      			</tr>
      			</table>
       			</div>
   </div>
   			
<div class="container-fluid" id="listdata" style="padding-top:8px;">
<div id="accordion" style="background-color: #fff; border-radius:5px;">
<div  style="box-shadow: 0 0 10px 3px rgba(0,0,0,0.14); padding-bottom:5px; padding-right: 0px;padding-left: 5px;margin-bottom: 1px;" >	
<div class="card" >
      	<div class="card-header">
        <a class="card-link" data-toggle="collapse" href="#collapseOne" onClick="changeRow();">
        <font style="color:black; font-weight: bold;font-size:12px;text-align:left;" class="control-label">
         OPD Today Visited <span class="badge" style="background-color:green;">5</span>&nbsp;&nbsp;&nbsp;
					Waiting <span class="badge" style="background-color:blue;">10</span>&nbsp;&nbsp;&nbsp;
					Attended <span class="badge"style="background-color:red;">2</span></font>
        </a>
      </div>
      </div>      
	<div id="collapseOne" class="collapse" data-parent="#accordion">
        <div class="card-body" >
     <b> OPD Patient List </b>
      <div id="PatList" class="col-*-*">
      <table id="opdpatient" class="table table-condensed table-responsive table-hover">
      	<tr>
      		
      		<th>Q. No. </th>
      		<th>Status</th>
      		<th>Name </th>
      		<th>Cr No. </th>
      		<th>Gen/Age/Cat </th>
      		<th>Department</th>
      		<th>Unit</th>
      		<th>Room No.</th>
      		<th>Precription</th>
      	</tr>
      </table>
       </div>
      </div>
      </div>
      
     </div>
</div>	
 </div> 
 </form>        	
</body>