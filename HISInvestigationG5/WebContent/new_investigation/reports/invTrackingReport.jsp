<!--
## Copyright Information	: C-DAC, Noida
## Project Name				: eSushrut
## Author          		   	: github.com/prashantmi
## Module Name				: INVESTIGATION
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page import="java.util.*"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>

<!doctype html>
<html lang="en">

<head>
  <title>Investigation Tracking Report</title>
  <meta charset="ISO-8859-1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> -->


  <!---New Resources Created by Prashant Start----------------------------------------------------------------------------------------------------------------->

  <!--JQuery -->
  <script src="media/jquery/3.4.1/jquery-3.4.1.min.js" type="text/javascript"></script>

  <!--Propper JS -->
  <!-- <script src="media/popper.js/1.16.0/umd/popper.min.js"></script> -->
  <script src="media/popper.js/2.0.6/umd/popper.min.js"></script>
  
  <!--Tooltip JS -->
  <script src="media/tooltip.js/1.3.2/umd/tooltip.min.js"></script>


  <!--JQueryUI -->
  <script src="media/jquery-ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
  <link href="media/jquery-ui/1.12.1/jquery-ui.min.css" rel="stylesheet" type="text/css" />
  <script src="media/jquery-ui/1.12.1/jquery.ui.datepicker.validation.min.js" type="text/javascript"></script>
  <script src="media/jquery-ui/1.12.1/jquery.validate.min.js" type="text/javascript"></script>

  <!--Bootstrap -->
  <link href="media/bootstrap/bootstrap-4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
  <!-- <link href="media/bootstrap/bootstrap-themes/css/litera/bootstrap.min.css" rel="stylesheet" type="text/css" />  -->
  <!-- <link href="media/bootstrap/bootstrap-themes/css/slate/bootstrap.min.css" rel="stylesheet" type="text/css" /> -->
  <!-- <link href="media/bootstrap/bootstrap-themes/css/material/bootstrap.min.css" rel="stylesheet" type="text/css" /> -->
  <script src="media/bootstrap/bootstrap-4.3.1/js/bootstrap.min.js" type="text/javascript"></script>


  <!--Bootstrap Datables -->
  <link href="media/dataTables/DataTables-1.10.18/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
  <script src="media/dataTables/DataTables-1.10.18/js/jquery.dataTables.min.js" type="text/javascript"></script>
  <script src="media/dataTables/DataTables-1.10.18/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>


  <!--Bootstrap Datatables Buttons -->
  <link href="media/dataTables/Buttons-1.5.6/css/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css" />
  <script src="media/dataTables/Buttons-1.5.6/js/dataTables.buttons.min.js" type="text/javascript"></script>
  <script src="media/dataTables/Buttons-1.5.6/js/buttons.bootstrap4.min.js" type="text/javascript"></script>

  <script src="media/dataTables/Buttons-1.5.6/js/buttons.colVis.min.js" type="text/javascript"></script>
  <script src="media/dataTables/Buttons-1.5.6/js/buttons.flash.min.js" type="text/javascript"></script>
  <script src="media/dataTables/Buttons-1.5.6/js/buttons.html5.min.js" type="text/javascript"></script>
  <script src="media/dataTables/Buttons-1.5.6/js/buttons.print.min.js" type="text/javascript"></script>


  <!--Excel HTML5 export button -->
  <script src="media/dataTables/JSZip-2.5.0/jszip.min.js" type="text/javascript"></script>

  <!--PDF HTML5 export button -->
  <script src="media/dataTables/pdfmake-0.1.36/pdfmake.min.js" type="text/javascript"></script>
  <script src="media/dataTables/pdfmake-0.1.36/vfs_fonts.js" type="text/javascript"></script>


  <!--Bootstrap Datables FixedHeader -->
  <link href="media/dataTables/FixedHeader-3.1.4/css/fixedHeader.bootstrap4.min.css" rel="stylesheet" type="text/css" />
  <script src="media/dataTables/FixedHeader-3.1.4/js/dataTables.fixedHeader.min.js" type="text/javascript"></script>
  <script src="media/dataTables/FixedHeader-3.1.4/js/fixedHeader.bootstrap4.min.js" type="text/javascript"></script>
  <!--not ness-->


  <!--Bootstrap Datables RowGroup -->
  <link href="media/dataTables/RowGroup-1.1.0/css/rowGroup.bootstrap4.min.css" rel="stylesheet" type="text/css" />
  <script src="media/dataTables/RowGroup-1.1.0/js/dataTables.rowGroup.min.js" type="text/javascript"></script>
  <script src="media/dataTables/RowGroup-1.1.0/js/rowGroup.bootstrap4.min.js" type="text/javascript"></script>
  <!--not ness-->


  <!--Bootstrap Datables Responsive -->
  <link href="media/dataTables/Responsive-2.2.2/css/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />
  <script src="media/dataTables/Responsive-2.2.2/js/dataTables.responsive.min.js" type="text/javascript"></script>
  <script src="media/dataTables/Responsive-2.2.2/js/responsive.bootstrap4.min.js" type="text/javascript"></script>

  <!-- Datables api->processing -->
  <script type="text/javascript" src="media/dataTables/api/processing().js"></script>

  <!--Fontawesome Icons---->
  <link rel="stylesheet" href="media/fontawesome/5.10.1/css/all.css" />


  <!---For Bootstrap 3 moment.js dattimepicker---->
  <!-- <link rel="stylesheet" href="media/bootstrap/bootstrap-3.3.7/css/bootstrap.min.css" /> -->
  <script type="text/javascript" src="media/moment-js/moment.js"></script>
  <script type="text/javascript" src="media/bootstrap/bootstrapDateTime/js/bootstrap-datetimepicker.min.js"></script>
  <link rel="stylesheet" href="media/bootstrap/bootstrapDateTime/css/bootstrap-datetimepicker.min.css" />

  <!---Datatables Date sorting plugin----->
  <!-- <script type="text/javascript" src="media/misc/moment.min.js"></script> -->
  <script type="text/javascript" src="media/moment-js/datetime-moment.js"></script>


  <!--Vue JS -->
  <script src="media/vue-js/vue.min.js" type="text/javascript"></script>
  <script src="media/vue-js/vuex.js" type="text/javascript"></script>
  <script src="media/vue-js/portal-vue.umd.min.js" type="text/javascript"></script>
  <link rel="stylesheet" href="media/vue-js/bootstrap-vue/bootstrap-vue.min.css" />
	
  <!--highchart JS -->
  <script src="media/highcharts/7.2.0/highcharts.js"></script>
  <script src="media/highcharts/7.2.0/modules/exporting.js"></script>
  <script src="media/highcharts/7.2.0/modules/export-data.js"></script>
  
  <!--Tippy JS----->
  <script type="text/javascript" src="media/tippy/6.0.0/dist/tippy-bundle.umd.min.js"></script>
  <link href="media/tippy/6.0.0/animations/perspective-extreme.css" rel="stylesheet" type="text/css" />
  <link href="media/tippy/6.0.0/animations/shift-away-extreme.css" rel="stylesheet" type="text/css" />
  <link href="media/tippy/6.0.0/themes/light.css" rel="stylesheet" type="text/css" />

  <!--TextFit JS----->
  <script type="text/javascript" src="media/textFit/2.4.0/textFit.min.js"></script>
  
  <!--Loading JS----->
  <!-- <link rel="stylesheet" type="text/css" href="media/loading-bar/dist/loading-bar.css"/>
  <script type="text/javascript" src="media/loading-bar/dist/loading-bar.js"></script>
  -->
   
  <!--Fancy Box----->
  <script type="text/javascript" src="media/fancybox/3.5.7/jquery.fancybox.min.js"></script>
  <link rel="stylesheet" href="media/fancybox/3.5.7/jquery.fancybox.min.css" type="text/css" />
	
  <!--Toastify CSS----->
  <link rel="stylesheet" href="media/toastify/1.6.1/toastify.min.css" type="text/css" />
	
  <!--patientTile----->
  <script type="text/javascript" src="media/prashantUIUX/patientTile/js/patientTile.js"></script>
  <link rel="stylesheet" href="media/prashantUIUX/patientTile/css/patientTile.css" type="text/css" />
  
  <!--borderUtilities----->
  <script type="text/javascript" src="media/prashantUIUX/borderUtilities/js/borderUtilities.js"></script>
  <link rel="stylesheet" href="media/prashantUIUX/borderUtilities/css/borderUtilities.css" type="text/css" />
  
  <!--loadingEffects----->
  <script type="text/javascript" src="media/prashantUIUX/loadingEffects/js/loadingEffects.js"></script>
  <link rel="stylesheet" href="media/prashantUIUX/loadingEffects/css/loadingEffects.css" type="text/css" />
  
  <!--customBootstrap----->
  <script type="text/javascript" src="media/prashantUIUX/customBootstrap/js/customBootstrap.js"></script>
  <link rel="stylesheet" href="media/prashantUIUX/customBootstrap/css/customBootstrap.css" type="text/css" />
  
  <!--Fonts--------------->
  <link rel="stylesheet" href="media/fonts/webfontkit-averta-family/stylesheet.css" type="text/css" />
  
  <!--Custom Resources----->
  <script type="text/javascript" src="media/prashantUIUX/searchTile/js/searchTile.js"></script>
  <link rel="stylesheet" href="media/prashantUIUX/searchTile/css/searchTile.css" type="text/css" />
  <script type="text/javascript" src="media/prashantUIUX/dataListingTile/js/dataListingTile.js"></script>
  <link rel="stylesheet" href="media/prashantUIUX/dataListingTile/css/dataListingTile.css" type="text/css" />
  
  <script type="text/javascript" src="media/reports/js/invTrackingReport.js"></script> 
  <script type="text/javascript" src="media/reports/js/invTrackingReportGraphTabFunction.js"></script>
  <link rel="stylesheet" href="media/reports/css/invTrackingReport.css" />
	

  <!---New Resources Created by Prashant End------------------------------------------------------------------------------------------------------------------->

</head>

<body class="">
	
	<fieldset class="m-2 px-1">
		<legend>
			<div class="container-fluid my-0 noUserSelect mx-0 px-0" id="container1">
				<div class="row align-items-center no-gutters"><!--  px-1 px-md-2  -->
				
					<div class="col-9 col-sm-7 col-md-5 col-lg-2 col-lg-2-5 textFit">
						<p class="text-nowrap" style="font-family: 'averta-extrabolduploaded_file';">
							<span class="text-primary"><img height="35" width="35" src='media/images/reportGradiant.svg'></span>
								Investigation Tracking
							<span class="fullScreenBtn"><img height="26" width="26" src='media/images/expand.svg'></span>
						</p>
					</div>
					
					<div class="col-3 col-sm-5 col-md-7 col-lg-9 col-lg-9-5 d-flex justify-content-end mb-3">
						<hr class="lineBetween"></hr>
						<button type="button" class="btn btn-sm rounded-lg refreshBtn">
							<img src='media/images/refresh.svg' height="24" width="24" title="Refresh">
						</button>
						<button type="button" class="btn btn-sm rounded-lg closeBtn">
							<img src='media/images/close.svg' height="24" width="24" title="Close">
						</button>
						<!-- <button type="button" class="btn btn-danger btn-circle " onclick="cancel()">
							<i class="fas fa-ban" aria-hidden="true" title="Cancel" style="font-size: 0.8rem"></i>
						</button> -->
					</div>
					
				</div>
			</div>
		</legend>
	
	<html:form action="/InvestigationTrackingReport" enctype="multipart/form-data">

		<bean:define name="InvTrackingReportFB" property="fromDate" id="frDate" type="java.lang.String" />
		<bean:define name="InvTrackingReportFB" property="toDate" id="tDate" type="java.lang.String" />
		<bean:define name="InvTrackingReportFB" property="miscDate" id="mDate" type="java.lang.String" />

 <%
	 if(frDate==null||frDate.equalsIgnoreCase("")) {
		 long milliSec = System.currentTimeMillis() - 3*24*60*60*1000; 
		 Date date = new Date(milliSec);
	     SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy");
	     frDate=formatter.format(date); }
	 if(tDate==null||tDate.equalsIgnoreCase("")) {
		 Date date = new Date(System.currentTimeMillis());
	     SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy");
	     tDate=formatter.format(date); }
 %>
 <%
	Date date = new Date(System.currentTimeMillis());
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy");
	
	cal.add(Calendar.DATE, -90);
	date=cal.getTime();
	mDate=formatter.format(date);
 %>
 
		<div class="container-fluid px-1 px-sm-3 mb-2" id="container2">
			<div class="mx-md-1 mx-md-point5 row  p-2 pt-lg-0 align-items-center justify-content-center shadow rounded-lg bg-white filertsGradient  hover-shadow collapse show" id="container2Row1">
				<div class="col-1 col-point5 noUserSelect px-0  ">
					<div class="custom-range-container ">
					  <input type="range" class="custom-range dateFiltersOrBothRange" min="1" max="2" id="dateFiltersOrBothRange" value="2">
					</div>
				</div>
				<div class="col-11 col-11-5 col-lg-7 col-lg-7-5 noUserSelect pl-lg-0  pt-lg-4">
					<div class="form-row align-items-center d-flex justify-content-center mb-md-2">
						<div class="col-12 col-lg-4 col-lg-4-5 mb-2 mb-lg-0 dateTypeSelectDiv d-none">
							<div class="input-group d-flex flex-nowrap">
								<div class="input-group-prepend">
									<div class="input-group-text">Search Date</div>
								</div>
								<select class="form-control custom-select" id="dateTypeSelectInput">
									<option value="-1" disabled>Choose Date</option>
									<option selected value="1">Requisition Date</option>
									<option value="2">Sample Collection Date</option>
									<option value="3">Acceptance Date</option>
									<option value="4">Result Entry Date</option>
									<option value="5">Result Validation Date</option>
									<option value="6">Report Printing Date</option>
								</select>
							</div>
						</div>
						<div class='inputDates col-12 col-lg-4  mb-2 mb-lg-0 d-none'>
							<div class="">
								<div class='input-group date d-flex flex-nowrap' id='datetimepicker-from'>
									<div class="input-group-prepend">
										<div class="input-group-text">From Date:</div>
									</div>
									<input type='text' class="form-control fromDateInput border-right-0 " value="<%=frDate%>" onkeydown="return false" />
									<span class="input-group-addon border border-left-0 d-flex px-1">
										 <i class="fas fa-calendar-alt my-auto" style="color: #007BFF; font-size: 21px;"></i>
									</span>
								</div>
							</div>
						</div>
						<div class='inputDates col-12  col-lg-3 col-lg-3-5  mb-2 mb-lg-0 d-none'>
							<div class="">
								<div class='input-group date d-flex flex-nowrap' id='datetimepicker-to'>
									<div class="input-group-prepend">
										<div class="input-group-text">To Date:</div>
									</div>
									<input type='text' class="form-control toDateInput border-right-0 " value="<%=tDate%>" onkeydown="return false" />
									<span class="input-group-addon border border-left-0 d-flex px-1">
										<i class="fas fa-calendar-alt my-auto" style="color: #007BFF; font-size: 21px;"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="form-row d-flex"> <!-- align-items-center  -->
						<div class="col-12 col-lg-5 ml-auto ml-md-0 mr-md-auto mb-2 mb-md-0">
							<div class='input-group d-flex flex-nowrap inputFiltersDiv'>
								<div class="input-group-append">
									<select class="form-control" id="inputFilterType">
										<option value="1">Cr No :</option>
										<option value="2">Bill No :</option>
				                     	<!-- <option value="3">Sample No :</option>
				                    	<option value="4">Lab No :</option>
				                    	<option value="5">Requisition No :</option>
				                    	<option value="6">Requisition DNo :</option> -->
									</select>
								</div>
								<input type="text" class="form-control" id="crNoInput" placeholder="Enter Cr. No." maxlength="15" minlength="15" value="">
								<input type="text" class="form-control d-none" id="billNoInput" placeholder="Enter Bill No." maxlength="15"  value="">
								<input type="text" class="form-control d-none" id="sampleNoInput" placeholder="Enter Sample No." maxlength="10"  value="">
								<input type="text" class="form-control d-none" id="labNoInput" placeholder="Enter Lab No." maxlength="10"  value="">
								<input type="text" class="form-control d-none" id="requisitionNoInput" placeholder="Enter requisition No." maxlength="10"  value="">
								<input type="text" class="form-control d-none" id="requisitionDNoInput" placeholder="Enter requisition DNo." maxlength="10"  value="">
							</div>
							<small id="inputFilterTypeHelp" class="form-text text-muted">Search Based On Cr No.</small>
						</div>
						<div class="col-12 col-lg-6 divArchivalmsgCol">
							<div class="divArchivalmsg ">
								<small class="card-subtitle text-muted">
										The Test Requisitions shown will be of last three months, To view request older than three months change the slider to enable date search criteria.
								</small>
							</div>
						</div>
						<div class="col-12 col-lg-1 ml-auto getDataBtnDiv" >
							<button id="getData" type="button" class="btn btn-primary ">
								<i class="text-nowrap"> Go </i>
							</button>
							<button id="getingData" class="btn btn-primary text-nowrap d-none"  disabled>
								<span class="spinner-border spinner-border-sm"></span> Loading..
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="mx-md-1 mx-md-point5 d-flex justify-content-end border-top-lg border-primary">
				<button id="container2ExpandBtn" class="btn btn-sm btn-warning ml-auto" type="button" data-toggle="collapse" data-target="#container2Row1" aria-expanded="true" aria-controls="collapseExample">
					<i class="fas fa-chevron-up "></i>
					<i class="fas fa-chevron-down d-none"></i>
				</button>
			</div>
		</div>
		
		
		<div class="container-fluid px-1 px-sm-3 mb-2 patDetailsContainer d-none" id="patDetails">
			<div class="mx-md-1 mx-md-point5 row p-2 align-items-center shadow rounded-lg bg-white no-gutters">

				<div class="col-12 col-md-1 " id="patDetails-icon">
					<div class="row align-items-center no-gutters pb-1">
					
						<p id="patDetails-icon-p" class="col-1 col-md-12 d-flex justify-content-center" style="font-weight: bold; font-size: 26px;">
							<i class="fas fa-female d-none" id="patDetails-icon-w" style="color: #E83E8C; font-size: 40px;"></i>
							<i class="fas fa-male d-none" id="patDetails-icon-m" style="color: #007BFF; font-size: 40px;"></i>
							<span class="d-none" id="patDetails-icon-t"><img height="35" width="35" src='media/images/transgenderSign.svg'></span> 
							<i class="fas fa-child d-none" id="patDetails-icon-g" style="color: #E83E8C; font-size: 40px;"></i> 
							<i class="fas fa-child d-none" id="patDetails-icon-b" style="color: #007BFF; font-size: 40px;"></i> 
							<i class="fas fa-baby d-none" id="patDetails-icon-gc" style="color: #E83E8C; font-size: 40px;"></i>
							<i class="fas fa-baby d-none" id="patDetails-icon-bc" style="color: #007BFF; font-size: 40px;"></i>
						</p>
						 
						<div id="patDetails-icon-status" class="col-11 col-md-12 d-flex justify-content-center overflow-hidden">
							<span class="patStatusSpan opdSpan d-none" >OPD</span>
							<span class="patStatusSpan emgcOpdSpan d-none" >EMERGENCY OPD</span>
							<span class="patStatusSpan spclOpdSpan d-none" >SPECIAL OPD</span>
							<span class="patStatusSpan ipdSpan d-none" >ADMITTED</span>
							<span class="ml-1 patStatusSpan unknownSpan d-none" >UNKNOWN</span>
							<span class="ml-1 patStatusSpan deadSpan d-none" >DEAD</span>
							<span class="ml-1 patStatusSpan mlcSpan d-none" >MLC</span>
							<span class="ml-1 patStatusSpan newBornSpan d-none" >NEWBORN</span>
							<span class="ml-1 patStatusSpan pregnantSpan d-none" >PREGNANT</span>
							<span class="ml-1 patStatusSpan vipSpan d-none" >VIP</span>
							<span class="ml-1 patStatusSpan confidentialSpan d-none" >CONFIDENTIAL</span>
						</div>
						
					</div>
				</div>

				<div class="col-12 col-md-11  rounded hover-shadow overflow-auto" id="patDetails-table-Opd">
					<table id="DataTableOPD" class="table table-sm dt-responsive nowrap table-condensed mx-md-0 rounded bg-white " style="width: 100%">
						<thead>
							<tr>
								<th>Patient Name</th>
								<th>CR Number</th>
								<th>Patient Status</th>
								<th>Gender</th>
								<th>Age</th>
								<th>Guardian Name</th>
								<th>Category</th>
								<th>DOB</th>
								<th>Mobile No</th>
								<th>Address</th>
							</tr>
						</thead>
						<tbody> </tbody>
					</table>
				</div>

				<div class=" col-md-11  rounded hover-shadow overflow-auto d-none" id="patDetails-table-Ipd" >
					<table id="DataTableIPD" class="table table-sm dt-responsive nowrap table-condensed mx-md-0 rounded bg-white " style="width: 100%">
						<thead>
							<tr>
								<th>Patient Name</th>
								<th>CR Number</th>
								<th>Patient Status</th>
								<th>Gender</th>
								<th>Age</th>
								<th>Guardian Name</th>
								<th>Category</th>
								<th>DOB</th>
								<th>Admission Date</th>
								<th>Admission No.</th>
								<th>Department Name</th>
								<th>Ward Name</th>
								<th>Room Name</th>
								<th>Bed Name</th>
								<th>Diagnosis</th>
								<th>Consultant Name</th>
								<th>Mobile No</th>
								<th>Address</th>
							</tr>
						</thead>
						<tbody> </tbody>
					</table>
				</div>

			</div>
		</div>


		<div id="patReqnList" class="container-fluid px-1 px-sm-3 mb-3 d-none">
			<span class="d-flex justify-content-end px-2 bar"><div class="rollingCircle d-none"></div><a class="lodingText d-none text-white pr-md-5 mr-md-5">Loading...</a></span>
			<div id='app1' class="mx-md-1 mx-md-point5 p-2 shadow rounded-lg bg-white hover-shadow">
				<b-tabs content-class="" v-model="tabIndex" >
				
				<!-- <b-tab title="Home">
				<div class="row no-gutters">

					<div class="col-md-9">

						<div class="row p-2 align-items-center no-gutters">
							<div class="card col-md-4 hoverCards" style="width: 18rem;">
								<div class="card-body">
									<h5 class="card-title">Pending Tests</h5>
									<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
									<p class="card-text">Some quick example text to build on
										the card title and make up the bulk of the card's content.</p>
									<a href="#" class="card-link">Card link</a> <a href="#"
										class="card-link">Another link</a>
								</div>
							</div>

							<div class="card col-md-4 hoverCards" style="width: 18rem;">
								<div class="card-body">
									<h5 class="card-title">Apcoming Appointments</h5>
									<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
									<p class="card-text">Some quick example text to build on
										the card title and make up the bulk of the card's content.</p>
									<a href="#" class="card-link">Card link</a> <a href="#"
										class="card-link">Another link</a>
								</div>
							</div>

							<div class="card col-md-4 hoverCards" style="width: 18rem;">
								<div class="card-body">
									<h5 class="card-title">Previous Requisitions</h5>
									<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
									<p class="card-text">Some quick example text to build on
										the card title and make up the bulk of the card's content.</p>
									<a href="#" class="card-link">Card link</a> <a href="#"
										class="card-link">Another link</a>
								</div>
							</div>
						</div>

						<div class="row p-2 align-items-center no-gutters">
							<div class="card col-md-4 hoverCards" style="width: 18rem;">
								<div class="card-body">
									<h5 class="card-title">Abnormal Tests Reports</h5>
									<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
									<p class="card-text">Some quick example text to build on
										the card title and make up the bulk of the card's content.</p>
									<a href="#" class="card-link">Card link</a> <a href="#"
										class="card-link">Another link</a>
								</div>
							</div>

							<div class="card col-md-4 hoverCards" style="width: 18rem;">
								<div class="card-body">
									<h5 class="card-title">Pending Reports</h5>
									<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
									<p class="card-text">Some quick example text to build on
										the card title and make up the bulk of the card's content.</p>
									<a href="#" class="card-link">Card link</a> <a href="#"
										class="card-link">Another link</a>
								</div>
							</div>

							<div class="card col-md-4 hoverCards" style="width: 18rem;">
								<div class="card-body">
									<h5 class="card-title">Card title</h5>
									<h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
									<p class="card-text">Some quick example text to build on
										the card title and make up the bulk of the card's content.</p>
									<a href="#" class="card-link">Card link</a> <a href="#"
										class="card-link">Another link</a>
								</div>
							</div>
						</div>

					</div>

					<div class="col-md-3" style="border-left: 3px solid gray;">
						<p class="d-flex justify-content-center">Notification Space</p>
					</div>

				</div>
				</b-tab>
				 -->
				
				<b-tab title="Graph" id="graphTab" active>
				 <template v-slot:title>
        			<!-- <b-spinner type="grow" small></b-spinner> I'm <i>Custom</i> <strong>Title</strong> -->
        			Graph  &nbsp;<b-badge variant="primary"><a class="graphBadgeCount">0</a><span class="sr-only">Graph & Trends</span></b-badge>
     			 </template>
     			 
				<div class="row p-2 ">

					<div class="col-md-2 rounded  graphTabTestOrGroupList-col">
						<div class="overflow-auto list-group list-group-flush  graphTabTestOrGroupList">
							<label class="bg-warning text-light list-group-item graphTab-testLabel text-center font-weight-bold d-none">Test</label>
							<div class="graphTab-testList">
								<!-- Here Comes Dynamic HTML Code -->
							</div>
							<label class="bg-warning text-light list-group-item graphTab-groupLabel text-center font-weight-bold d-none">Group Test</label>
							<div class="graphTab-goupList">
								<!-- Here Comes Dynamic HTML Code -->
							</div>
						</div>
					</div>
					
					<div class="col-md-10 graphTabTestGraphnTable-col">
						<div class="row  pl-3 no-gutters d-none showGraphBtnRow">
							<div class="custom-control custom-switch">
								<input type="checkbox" class="custom-control-input" id="showGraphBtn" data-toggle="collapse" data-target="#graphTabTestGraph-col" aria-expanded="true" checked>
								<label class="custom-control-label" for="showGraphBtn">Show/Hide Graph</label>
							</div>
						</div>
						
						<div class="row  no-gutters">
							<div class="col-md-5 graphTabTestGraph-col p-1 d-none  collapse show " id="graphTabTestGraph-col">
								<div id="testParamChart" class="">
									<!-- Here Comes Dynamic HTML Code -->
								</div>
							</div>
								
							<!-- col-md-12 will be removed by js if chart is shown-->
							<div class="col-md-7 col-md-12 graphTabTestOrGroupTable-col p-1">
								<div id="testParamTable-Div" class="testParamTable-Div">
									<!-- Here Comes Dynamic HTML Code -->
								</div>
							</div>
							
						</div>
					</div>

				</div>
				</b-tab> <b-tab title="SampleBased Test" id="sampleBasedTab" class="pt-3" >
				<template v-slot:title>
        			SampleBased Test  &nbsp;<b-badge variant="primary"><a class="sampleTestBadgeCount">0</a> <span class="sr-only">SampleBased Test</span></b-badge>
     			 </template>
     			 
				<table id="DataTable4"
					class="table table-sm  dt-responsive  nowrap table-condensed mx-md-0 rounded bg-white"
					style="width: 100%">
					<caption>Investigation Tracking List By Cr. No.</caption>
					<thead>
						<tr>
							<th class="all expandCollapseTh">
								<button type="button" class="btn btn-light btn-circle btn-circle-allRowSort collapseButton d-none">
									<span class="" ><img height="17" width="17" src='media/images/upArrow1.svg'></span>
								</button>
								<button type="button" class="btn btn-light btn-circle btn-circle-allRowSort expandButton ">
									<span class="" ><img height="17" width="17" src='media/images/downArrow1.svg'></span>
								</button>
							</th>
							<th class="">S.No</th>
							<th class="">RowGroupingOnRequisitionNo</th>
							<th class="">Requisition No</th>
							<th class="">Lab Name</th>
							<th class="">Advised By Dep.</th>
							<th class="">Group/Test Name</th>
							<th class="">Status</th>
							<th class="">Turn Around Time</th>
							<th class="">Sample No.</th>
							<th class="">Rate</th>
							<th class="">B/UnBilled</th>
							<th class="">Note</th>

							<th class="none">Requisition Date</th>
							<th class="none">Requisition By</th>
							<th class="none">Advised By Doc.</th>
							<th class="none">Group/Test Name</th>
							<th class="none">Appointment DateTime</th>
							<th class="none">Bill No.</th>
							<th class="none">Bill Date</th>

							<th class="none">Sample Name</th>
							<th class="none">Sample No.</th>
							<th class="none">Sample Coll. Date</th>
							<th class="none">Sample Coll. Area</th>
							<th class="none">Sample Coll. By</th>

							<th class="none">PackingList No.</th>
							<th class="none">Lab No.</th>
							<th class="none">PackingList DateTime</th>
							<th class="none">PackingList Gene. By</th>

							<th class="none">Lab No.</th>
							<th class="none">Sample Accep. Date</th>
							<th class="none">Sample Accep. By</th>
							<th class="none">Sample Accep. Mode</th>
							<th class="none">Machine Name</th>

							<th class="none">Sample Rejec. Date</th>
							<th class="none">Sample Rejec. By</th>
							<th class="none">Sample Rejec. Reason</th>

							<th class="none">Result Entry Date</th>
							<th class="none">Result Entry By</th>
							<th class="none">Result Valid. Date</th>
							<th class="none">Result Valid. By</th>
							<th class="none">Result ReValid. Date</th>
							<th class="none">Result ReValid. By</th>

							<th class="none">Report Gen Date</th>
							<th class="none">Report Print Date</th>
							<th class="none">Report Print By</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
					</tfoot>
				</table>
				</b-tab> <b-tab title="PatientBased Test" id="patientBasedTab" class="pt-3">
				<template v-slot:title>
        			PatientBased Test  &nbsp;<b-badge variant="primary"><a class="patientTestBadgeCount">0</a> <span class="sr-only">PatientBased Test</span></b-badge>
     			 </template>
     			 
				<table id="DataTable5"
					class="table table-sm  dt-responsive  nowrap table-condensed mx-md-0 rounded bg-white"
					style="width: 100%">
					<caption>Investigation Tracking List By Cr. No.</caption>
					<thead>
						<tr>
						<tr>
							<th class="all expandCollapseTh">
								<button type="button" class="btn btn-light btn-circle btn-circle-allRowSort collapseButton d-none">
									<span class="" ><img height="17" width="17" src='media/images/upArrow1.svg'></span>
								</button>
								<button type="button" class="btn btn-light btn-circle btn-circle-allRowSort expandButton ">
									<span class="" ><img height="17" width="17" src='media/images/downArrow1.svg'></span>
								</button>
							</th>
							<th class="">S.No</th>
							<th class="">RowGroupingOnRequisitionNo</th>
							<th class="">Requisition No</th>
							<th class="">Lab Name</th>
							<th class="">Advised By Dep.</th>
							<th class="">Group/Test Name</th>
							<th class="">Status</th>
							<th class="">Turn Around Time</th>
							<th class="">Accession No.</th>
							<th class="">Rate</th>
							<th class="">B/UnBilled</th>
							<th class="">Note</th>

							<th class="none">Requisition Date</th>
							<th class="none">Requisition By</th>
							<th class="none">Advised By Doc.</th>
							<th class="none">Group/Test Name</th>
							<th class="none">Appointment DateTime</th>
							<th class="none">Bill No.</th>
							<th class="none">Bill Date</th>

							<th class="none">Accession No.</th>
							<th class="none">Patient Accep. Date</th>
							<th class="none">Patient Accep. Area</th>
							<th class="none">Patient Accep. By</th>
							<th class="none">Patient Accep. Mode</th>
							<th class="none">Machine Name</th>

							<th class="none">Patient Rejec. Date</th>
							<th class="none">Patient Rejec. By</th>
							<th class="none">Patient Rejec. Reason</th>

							<th class="none">Result Entry Date</th>
							<th class="none">Result Entry By</th>
							<th class="none">Result Valid. Date</th>
							<th class="none">Result Valid. By</th>
							<th class="none">Result ReValid. Date</th>
							<th class="none">Result ReValid. By</th>

							<th class="none">Report Gen Date</th>
							<th class="none">Report Print Date</th>
							<th class="none">Report Print By</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
					</tfoot>
				</table>
				</b-tab> </b-tabs>
			</div>
		</div>

		<div class="modal fade" id="showReportPdfModal" role="dialog">
			<div class="modal-dialog modal-lg">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header" style="height: 5px">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<div class="text-center">
							<button type="button" class="btn btn-info"
								onclick="$('#showReportPdfModal').modal('hide');$('#printPrescFrameId').remove();">Cancel</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<html:hidden name="InvTrackingReportFB" property="hmode" />
		<html:hidden name="InvTrackingReportFB" property="hospitalCode" />		
		<html:hidden name="InvTrackingReportFB" property="dateTypeCode" />
		<html:hidden name="InvTrackingReportFB" property="fromDate" />
		<html:hidden name="InvTrackingReportFB" property="toDate" />
		<html:hidden name="InvTrackingReportFB" property="crNo" />		
		<html:hidden name="InvTrackingReportFB" property="billNo" />
		<html:hidden name="InvTrackingReportFB" property="sampleNo" />
		<html:hidden name="InvTrackingReportFB" property="labNo" />
		<html:hidden name="InvTrackingReportFB" property="searchType" />
		<html:hidden name="InvTrackingReportFB" property="isGroup" />
		<html:hidden name="InvTrackingReportFB" property="testCode" />
		<html:hidden name="InvTrackingReportFB" property="groupCode" />
		<html:hidden name="InvTrackingReportFB" property="requisitionNo" />
		<html:hidden name="InvTrackingReportFB" property="requisitionDNo" />
		<html:hidden name="InvTrackingReportFB" property="forTestOrGroupOrAll" />		
		<html:hidden name="InvTrackingReportFB" property="showGraph" />
		
		
	</html:form>
	
	<a data-fancybox data-type="iframe" id="fancyBoxIFrame" class="fancyBoxIFrame"></a>

  	<a data-fancybox data-src=".advisedNotesContainer" id="fancyBoxAdvisedNotes" class="fancyBoxAdvisedNotes"></a>

	<div class=" advisedNotesContainer p-0 m-1" style="display: none; min-width: 300px;">
		<div class="row p-0 m-1">
			<div class="advisedNotesCol m-1 col-12">
				<table style="width:100%;">
					<tr class="instrucGenTr"><th>Instructions Generic :	</th> <td></td> </tr>
					<tr class="instrucFlgTr"><th>Instruction Flag :	</th> <td></td> </tr>
					<tr class="chfComplntTr"><th>Chief Complaints :		</th> <td></td> </tr>
					<tr class="diagnosisTr"><th>Diagnosis :				</th> <td></td> </tr>
					<tr class="notesTr"><th>Notes :				</th> <td></td> </tr>
					<tr class="advisedByTr"><th>Advised By :			</th> <td></td> </tr>
				</table>
			</div>
		</div>
	</div>
	</fieldset>
	<script type="text/javascript" src="media/vue-js/bootstrap-vue/bootstrap-vue.min.js"></script>
	<script type="text/javascript" src="media/vue-js/bootstrap-vue/polyfill.min.js"></script>
	
	
	
</body>

<script>

var vm = new Vue({
    el: "#app1",
    data: {
      name: "Sitepoint",
    	  tabIndex: 0
    },
      
  methods: {
	    showGraphTab: function (event) {
	      // `this` inside methods point to the Vue instance
	      //alert('Hello ' + this.name + '!')
	      // `event` is the native DOM event
	     // alert(event.target.tagName)
	       //alert(this.tabIndex)
	      this.tabIndex=0
	    }
  }
  
});
    
</script>

<style>
@media (max-width: 991px){
.custom-range-container {
    min-width: 65px !important;
    margin-top: 0px !important;
    
}
}
</style>

</html>
