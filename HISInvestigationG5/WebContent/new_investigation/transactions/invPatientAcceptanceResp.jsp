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
  <title>Patient Acceptance</title>
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
  <!-- <script src="media/tooltip.js/1.3.2/umd/tooltip.min.js"></script> -->
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
  <!--Bootstrap Select -->
   <link href="media/dataTables/select/1.3.1/css/select.bootstrap.min.css" rel="stylesheet" type="text/css" />
   <script src="media/dataTables/select/1.3.1/js/select.bootstrap4.min.js" type="text/javascript"></script>
  <!-- <link href="media/dataTables/select/1.3.1/css/select.dataTables.min.css" rel="stylesheet" type="text/css" /> -->
  <script src="media/dataTables/select/1.3.1/js/dataTables.select.min.js" type="text/javascript"></script>
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
  <!-- <link rel="stylesheet" href="media/prashantUIUX/customBootstrap/css/customSelect.css" /> -->
  <!--Fonts--------------->
  <link rel="stylesheet" href="media/fonts/webfontkit-averta-family/stylesheet.css" type="text/css" />
  <!--Custom Resources----->
  <script type="text/javascript" src="media/prashantUIUX/searchTile/js/searchTile.js"></script>
  <link rel="stylesheet" href="media/prashantUIUX/searchTile/css/searchTile.css" type="text/css" />
  <script type="text/javascript" src="media/prashantUIUX/dataListingTile/js/dataListingTile.js"></script>
  <link rel="stylesheet" href="media/prashantUIUX/dataListingTile/css/dataListingTile.css" type="text/css" />
  
  <script type="text/javascript" src="media/services/js/invPatientAcceptanceResp.js"></script>
  <link rel="stylesheet" href="media/services/css/invPatientAcceptanceResp.css" />
  <!---New Resources Created by Prashant End------------------------------------------------------------------------------------------------------------------->
</head>
<body class=""  style="" onLoad="getCollAreaNLabListOnLoad();">
<fieldset class="m-2 px-1">
	<legend>
		<div class="container-fluid my-0 noUserSelect mx-0 px-0" id="container1">
			<div class="row align-items-center no-gutters"><!--  px-1 px-md-2  -->
				<div class="col-9 col-sm-7 col-md-5 col-lg-3 textFit">
					<p class="text-nowrap" style="font-family: 'averta-extrabolduploaded_file';">
						<span class="text-primary"><img height="36" width="36" src='media/images/x-ray.svg'></span>
							Investigation Patient Acceptance
						<span class="fullScreenBtn"><img height="26" width="26" src='media/images/expand.svg'></span>
					</p>
				</div>
				<div class="col-3 col-sm-5 col-md-7 col-lg-9 d-flex justify-content-end mb-3">
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
	<html:form action="/invPatientAcceptanceResp" enctype="multipart/form-data">
		<bean:define name="InvPatientAcceptanceRespFB" property="fromDate" id="frDate" type="java.lang.String" />
		<bean:define name="InvPatientAcceptanceRespFB" property="toDate" id="tDate" type="java.lang.String" />
		<bean:define name="InvPatientAcceptanceRespFB" property="miscDate" id="mDate" type="java.lang.String" />

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
			<div class="mx-md-1 mx-md-point5 row  p-2 pt-lg-0 align-items-center shadow rounded-lg bg-white filertsGradient  hover-shadow collapse show" id="container2Row1">
				<div class="col-12 col-lg-4 rounded border-right-lg border-primary noUserSelect mt-lg-2 mb-2 mb-lg-0">
					<div class="input-group  flex-nowrap mb-2 d-none"> <!-- d-flex -->
						<div class="input-group-prepend">
							<div class="input-group-text">Collection Area</div>
						</div>
						<select class="form-control custom-select" id="collAreaSelectInput">
							<option value="-1">Choose Collection Area</option>
							<option selected value="0">All Collection Area</option>
						</select>
					</div>
					<div class="input-group d-flex flex-nowrap mb-2 labSelectInputGroup">
						<div class="input-group-prepend">
							<div class="input-group-text">Lab Name</div>
						</div>
						<select class="form-control custom-select" id="labSelectInput">
							<option value="-1">Choose Lab</option>
							<option selected value="1">All Labs</option>
						</select>
					</div>
					<div class="input-group d-flex flex-nowrap mb-2 mb-md-0">
						<div class="input-group-prepend">
							<div class="input-group-text">Patient Accep. Status</div>
						</div>
						<select class="form-control custom-select" id="valStatusSelectInput">
							<option value="-1" disabled>Choose Acceptance Status</option>
							<option selected value="1">Yet To Acceptance</option>
							<option value="2">Patient Accepted</option>
						</select>
					</div>
				</div>
				<div class="col-1 col-point5 noUserSelect px-0  ">
					<div class="custom-range-container ">
					  <input type="range" class="custom-range dateFiltersOrBothRange" min="0" max="2" id="dateFiltersOrBothRange" value="0">
					</div>
				</div>
				<div class="col-11 col-11-5 col-lg-7 col-lg-7-5 noUserSelect pl-lg-0  pt-lg-4">
					<div class="form-row align-items-center d-flex justify-content-center mb-md-2">
						<div class="col-12 col-lg-4 col-lg-4-5 mb-2 mb-lg-0 dateTypeSelectDiv">
							<div class="input-group d-flex flex-nowrap">
								<div class="input-group-prepend">
									<div class="input-group-text">Search Date</div>
								</div>
								<select class="form-control custom-select" id="dateTypeSelectInput">
									<option value="-1" disabled>Choose Date</option>
									<option selected value="1">Requisition Date</option>
									<option value="2">Patient Acceptance Date</option>
								</select>
							</div>
						</div>
						<div class='inputDates col-12 col-lg-4  mb-2 mb-lg-0'>
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
						<div class='inputDates col-12  col-lg-3 col-lg-3-5  mb-2 mb-lg-0'>
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
						<div class="col-12 col-lg-4 ml-auto ml-md-0 mr-md-auto mb-2 mb-md-0">
							<div class='input-group d-flex flex-nowrap inputFiltersDiv'>
								<div class="input-group-append">
									<select class="form-control" id="inputFilterType" disabled>
										<option value="1">Cr No :</option>
										<option value="2">Bill No :</option>
				                     	<option value="3">Sample No :</option>
				                    	<option value="4">Lab No :</option>
				                    	<option value="5">Requisition No :</option>
				                    	<option value="6">Requisition DNo :</option>
									</select>
								</div>
								<input type="text" class="form-control" id="crNoInput" placeholder="Enter Cr. No." maxlength="15" minlength="15" value="" disabled>
								<input type="text" class="form-control d-none" id="billNoInput" placeholder="Enter Bill No." maxlength="15"  value="" disabled>
								<input type="text" class="form-control d-none" id="sampleNoInput" placeholder="Enter Sample No." maxlength="10"  value="" disabled>
								<input type="text" class="form-control d-none" id="labNoInput" placeholder="Enter Lab No." maxlength="10"  value="" disabled>
								<input type="text" class="form-control d-none" id="requisitionNoInput" placeholder="Enter requisition No." maxlength="10"  value="" disabled>
								<input type="text" class="form-control d-none" id="requisitionDNoInput" placeholder="Enter requisition DNo." maxlength="10"  value="" disabled>
							</div>
							<small id="inputFilterTypeHelp" class="form-text text-muted">Disabled</small>
						</div>
						<div class="col-12 col-lg-6 divArchivalmsgCol d-none">
							<div class="divArchivalmsg ">
								<small class="card-subtitle text-muted">
										The Test Requisitions shown will be of last three months, To view request older than three months change the slider to enable date search criteria.
								</small>
							</div>
						</div>
						<div class="col-12 col-lg-2 ml-auto getDataBtnDiv" >
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
				<div class="col-12 col-md-11  rounded hover-shadow" id="patDetails-table-Opd">
					<table id="DataTableOPD" class="table table-sm dt-responsive nowrap table-condensed mx-md-0 rounded bg-white " style="width: 100%">
						<thead>
							<tr>
								<th>Patient Name</th>
								<th>CR Number</th>
								<th>Patient Status</th>
								<th>Gender</th>
								<th>Age</th>
								<th>GuardianName</th>
								<th>Category</th>
								<th>DOB</th>
								<th>Mobile No</th>
								<th>Address</th>
							</tr>
						</thead>
						<tbody> </tbody>
					</table>
				</div>
				<div class=" col-md-11  rounded hover-shadow d-none"
					id="patDetails-table-Ipd" >
					<table id="DataTableIPD" class="table table-sm dt-responsive nowrap table-condensed mx-md-0 rounded bg-white " style="width: 100%">
						<thead>
							<tr>
								<th>Patient Name</th>
								<th>CR Number</th>
								<th>Patient Status</th>
								<th>Gender</th>
								<th>Age</th>
								<th>GuardianName</th>
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
		<div id="EntryReqnListContr" class="container-fluid px-1 px-sm-3 mb-3 d-none ">
		  <span class="d-flex justify-content-end px-2 bar"><div class="rollingCircle d-none"></div><a class="lodingText d-none text-white pr-md-5 mr-md-5">Loading...</a></span>
			<div id='' class="mx-md-1 mx-md-point5 row  p-2 align-items-center shadow rounded-lg bg-white  hover-shadow ">
        		
        		<div class="entryReqnListFltrMan">
        		 <div class="d-flex justify-content-center">
        		 	<select class="form-control custom-select departNameFilterMan d-none" ><option class="text-light bg-dark" value="" selected >Department Filter</option></select>
				  	<select class="form-control custom-select groupTestFilterMan ml-1 d-none" ><option class="text-light bg-dark" value="" selected >Test|Group Filter</option></select>
				  	<select class="form-control custom-select labNameFilterMan ml-1 d-none" ><option class="text-light bg-dark" value="" selected >Lab Filter</option></select>
				  	<select class="form-control custom-select patStatusFilterMan ml-1 d-none" ><option class="text-light bg-dark" value="" selected >Patient Status</option></select>
				  	<select class="form-control custom-select machineNameFilterMan ml-1 d-none" ><option class="text-light bg-dark" value="" selected >Machine</option></select>
				  	<select class="form-control custom-select priorityFilterMan ml-1 d-none" ><option class="text-light bg-dark" value="" selected >Priority</option></select>
        		 </div>
				</div>
				
				<div class="entryReqnListFltrMchn d-none">
					<div class="d-flex justify-content-center">
						<select class="form-control custom-select departNameFilterMchn d-none" ><option class="text-light bg-dark" value="" selected >Department Filter</option></select>
					  	<select class="form-control custom-select groupTestFilterMchn ml-1 d-none" ><option class="text-light bg-dark" value="" selected >Test|Group Filter</option></select>
					  	<select class="form-control custom-select labNameFilterMchn ml-1 d-none" ><option class="text-light bg-dark" value="" selected >Lab Filter</option></select>
					  	<select class="form-control custom-select patStatusFilterMchn ml-1 d-none" ><option class="text-light bg-dark" value="" selected >Patient Status</option></select>
					  	<select class="form-control custom-select machineNameFilterMchn ml-1 d-none" ><option class="text-light bg-dark" value="" selected >Machine</option></select>
					  	<select class="form-control custom-select priorityFilterMchn ml-1 d-none" ><option class="text-light bg-dark" value="" selected >Priority</option></select>
	        		 </div>
				</div>

        <div class="col-12 pt-3" >
          <ul class="nav nav-tabs d-none" id="entryReqnListTabs" role="tablist">
            <li class="nav-item">
              <a class="nav-link active" id="manEntry-tab" data-toggle="tab" href="#manEntry" role="tab" aria-controls="manEntry" aria-selected="true">Manual Entry</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" id="mchnEntry-tab" data-toggle="tab" href="#mchnEntry" role="tab" aria-controls="mchnEntry" aria-selected="false">Machine Entry</a>
            </li>
          </ul>
          <div class="tab-content d-none" id="entryReqnListTabsContent">
            <div class="tab-pane fade show active" id="manEntry" role="tabpanel" aria-labelledby="manEntry-tab">
              <div class="row"></div>
            </div>
          </div> 
 
            <div id="allManEntryReqnList" class="col-12 pt-3 allEntryReqnList d-none" >
    					<table id="DataTable11" class="table table-sm  dt-responsive nowrap table-condensed mx-md-0 bg-white table-bordered " style="width: 100%">
    						<caption class="DataTable11Caption"></caption>
    						<thead>
                  <tr>
                    <th class="all expandCollapseTh">
                      <button type="button" class="btn btn-light btn-circle btn-circle-allRowSort collapseButton d-none">
                        <span class="" ><img height="17" width="17" src='media/images/upArrow1.svg'></span>
                      </button>
                      <button type="button" class="btn btn-light btn-circle btn-circle-allRowSort expandButton ">
                        <span class="" ><img height="17" width="17" src='media/images/downArrow1.svg'></span>
                      </button>
                      <!-- <i class="fas fa-chevron-circle-up text-danger d-none collapseButton"></i>
                      <i class="fas fa-chevron-circle-down text-success expandButton"></i> -->
                    </th>
                    <th class="">S.No</th>
                    <th class="">Requisition Date</th>
                    <th class="">RequisitionNoAnDate</th>
					<th class="">
						<div class="custom-control custom-checkbox">
						    <input type="checkbox" class="custom-control-input selectAllDtCheck1" id="selectAllDtCheck1">
						    <label class="custom-control-label" for="selectAllDtCheck1"></label>
						</div>
					</th>
					<th class="">CR NO</th>
					<th class="">Patient Name</th>
					<th class="">Age/Gender</th>
					<th class="">Department-Unit</th>
					<th class="">Test | Group Name</th>
					<th class="">Lab Name</th>
					<th class="">Patient Status</th>
					<th class="">Accession No.</th>
                    <th class="">IPD Ward/OPD Block</th>
                    <th class="">IPD Bed/OPD Room</th>
					<th class="">Machine Code</th>
                    <th class="">Patient Acceptance Date</th>
                    <th class="none">Priority</th>
                    <th class="none"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>

            <div id="patManEntryReqnList" class="col-12 pt-3 patEntryReqnList d-none" >
    					<table id="DataTable12" class="table table-sm  dt-responsive nowrap table-condensed mx-md-0 bg-white" style="width: 100%">
    						<caption class="DataTable12Caption"></caption>
    						<thead>
    							<tr>
	   								<th class="all expandCollapseTh">
	   										<button type="button" class="btn btn-light btn-circle btn-circle-allRowSort collapseButton d-none">
	   											<span class="" ><img height="17" width="17" src='media/images/upArrow1.svg'></span>
	   										</button>
	   										<button type="button" class="btn btn-light btn-circle btn-circle-allRowSort expandButton ">
	   											<span class="" ><img height="17" width="17" src='media/images/downArrow1.svg'></span>
	   										</button>
	   										<!-- <i class="fas fa-chevron-circle-up text-danger d-none collapseButton"></i>
	   										<i class="fas fa-chevron-circle-down text-success expandButton"></i> -->
	   								</th>
	   									<th class="">S.No</th>
	   									<th class="">Requisition Date</th>
	   									<th class="">RequisitionNoAnDate</th>
	   								<th class="">
	   									<div class="custom-control custom-checkbox">
	   									    <input type="checkbox" class="custom-control-input selectAllDtCheck2" id="selectAllDtCheck2">
	   									    <label class="custom-control-label" for="selectAllDtCheck2"></label>
	   									</div>
	   								</th>	
									<th class="">Department-Unit</th>
									<th class="">Test | Group Name</th>
									<th class="">Lab Name</th>
									<th class="">Machine Code</th>
									<th class="">Accession No.</th>
				                    <th class="">Patient Acceptance Date</th>
				                    <th class="none">Priority</th>
				                    <th class="none"></th>
    							</tr>
    						</thead>
    						<tbody>
    						</tbody>
    					</table>
    				</div>
          
            <div class="tab-pane fade d-none" id="mchnEntry" role="tabpanel" aria-labelledby="mchnEntry-tab">
              <div class="row">
                <div id="allMchnEntryReqnList" class="col-12 pt-3 allEntryReqnList d-none" >
        					<table id="DataTable21" class="table table-sm  dt-responsive nowrap table-condensed mx-md-0 bg-white " style="width: 100%">
        						<caption class="DataTable11Caption"></caption>
        						<thead>
                      <tr>
                        <th class="all expandCollapseTh">
                          <button type="button" class="btn btn-light btn-circle btn-circle-allRowSort collapseButton d-none">
                            <span class="" ><img height="17" width="17" src='media/images/upArrow1.svg'></span>
                          </button>
                          <button type="button" class="btn btn-light btn-circle btn-circle-allRowSort expandButton ">
                            <span class="" ><img height="17" width="17" src='media/images/downArrow1.svg'></span>
                          </button>
                          <!-- <i class="fas fa-chevron-circle-up text-danger d-none collapseButton"></i>
                          <i class="fas fa-chevron-circle-down text-success expandButton"></i> -->
                        </th>
                        <th class="">S.No</th>
                        <th class="">Requisition Date</th>
                        <th class="">RequisitionNoAnDate</th>
        								<th class="">
        									<div class="custom-control custom-checkbox">
        									    <input type="checkbox" class="custom-control-input selectAllDtCheck1" id="selectAllDtCheck1">
        									    <label class="custom-control-label" for="selectAllDtCheck1"></label>
        									</div>
        								</th>
        								<th class="">CR NO</th>
        								<th class="">Patient Name</th>
        								<th class="">Age/Gender</th>
        								<th class="">Department-Unit</th>
        								<th class="">Test | Group Name</th>
        								<th class="">Lab Name</th>
        								<th class="">Sample/Accession No.</th>
        								<th class="">Patient Status</th>
                        <th class="">Machine Code</th>
        								<th class="">Add/Modify</th>
                        <th class="none">Priority</th>
                        <th class="none">Sample Collection Date</th>
                        <th class="none">Sample Acceptance Date</th>
                        <th class="none">Patient Acceptance Date</th>
                        <th class="none">Result Entry Date</th>
                        <th class="none"></th>
        							</tr>
        						</thead>
        						<tbody>
        						</tbody>
        					</table>
        				</div>

                <div id="patMchnEntryReqnList" class="col-12 pt-3 patEntryReqnList d-none" >
        					<table id="DataTable22" class="table table-sm  dt-responsive nowrap table-condensed mx-md-0 bg-white" style="width: 100%">
        						<caption class="DataTable12Caption"></caption>
        						<thead>
        							<tr>
        									<th class="all expandCollapseTh">
        										<button type="button" class="btn btn-light btn-circle btn-circle-allRowSort collapseButton d-none">
        											<span class="" ><img height="17" width="17" src='media/images/upArrow1.svg'></span>
        										</button>
        										<button type="button" class="btn btn-light btn-circle btn-circle-allRowSort expandButton ">
        											<span class="" ><img height="17" width="17" src='media/images/downArrow1.svg'></span>
        										</button>
        										<!-- <i class="fas fa-chevron-circle-up text-danger d-none collapseButton"></i>
        										<i class="fas fa-chevron-circle-down text-success expandButton"></i> -->
        								</th>
        									<th class="">S.No</th>
        									<th class="">Requisition Date</th>
        									<th class="">RequisitionNoAnDate</th>
        								<th class="">
        									<div class="custom-control custom-checkbox">
        									    <input type="checkbox" class="custom-control-input selectAllDtCheck2" id="selectAllDtCheck2">
        									    <label class="custom-control-label" for="selectAllDtCheck2"></label>
        									</div>
        								</th>
        								<th class="">Department-Unit</th>
        								<th class="">Test | Group Name</th>
        								<th class="">Lab Name</th>
        								<th class="">Sample/Accession No.</th>
                        <th class="">Machine Code</th>
        								<th class="">Add/Modify</th>
                        <th class="none">Priority</th>
                        <th class="none">Sample Collection Date</th>
                        <th class="none">Sample Acceptance Date</th>
                        <th class="none">Patient Acceptance Date</th>
                        <th class="none">Result Entry Date</th>
                        <th class="none"></th>
        							</tr>
        						</thead>
        						<tbody>
        						</tbody>
        					</table>
        				</div>
              </div>
            </div>

          
        </div>

				<div class="col-12 pt-2 d-flex justify-content-center">
					<div class="enterResultBtnDiv d-none">
						<button id="enterResultBtn" type="button" class="btn btn-primary text-nowrap enterResultBtn" disabled>
							<span class=""></span> Accept Patient
						</button>
					</div>
					&emsp;
						<div class="modifyEntryBtnDiv">
							<button id="modifyEntryBtn" type="button" class="btn btn-primary text-nowrap modifyEntryBtn" disabled>
								<span class=""></span> Modify
						</button>
					</div>
				</div>
			</div>
		</div>
		
		<div id="notificationsRedirectContr" class="container-fluid px-1 px-sm-3 mb-3 d-none ">
			<div id="" class="mx-md-1 mx-md-point5 row  p-2 align-items-center shadow rounded-lg bg-white  hover-shadow ">
        		<div id="notificationsRedirectCol" class="col-12 pt-3" >
        		</div>
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
		<html:hidden name="InvPatientAcceptanceRespFB" property="hmode" />
		<%-- <html:hidden name="InvPatientAcceptanceRespFB" property="resultEntryStatusCode" /> --%>
		<html:hidden name="InvPatientAcceptanceRespFB" property="jsonResponse" value='{"responseFromProcess":" ","isSuccess":"0","errorMsg":" "}' />
		
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
	<!--Toastify Js----->
	<script type="text/javascript" src="media/toastify/1.6.1/toastify-js.js"></script>
</body>
</html>
