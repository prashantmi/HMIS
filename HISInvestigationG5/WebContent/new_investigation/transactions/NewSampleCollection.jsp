<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_SampleCollectionVO"%>
<%@page import="new_investigation.transactions.controller.fb.SampleCollectionFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="new_investigation.transactions.controller.fb.SampleCollectionFB"%>

<head>
  <title>Sample Collection</title>
  <meta charset="ISO-8859-1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
	  <link rel="stylesheet" href="media/reports/css/invTrackingReport.css" />
	  
  
  <!--Sample Collection js/css--------------->
   <link rel="stylesheet" href="media/investigation/css/SampleCollectionNew.css" />
   <script type="text/javascript" src="media/investigation/js/SampleCollectionNew.js"></script>
	   <link rel="stylesheet" href="media/services/css/invResultValidationResp.css" />
	  
	  <his:css src="/new_investigation/media/xray/sweetalert.css" />

<his:javascript src="/new_investigation/media/xray/sweetalert.js" />
<style>

#confirm  {
    text-align: center;
 }
</style>

<script>
var pat_ward="";
var pat_crno="";
var pat_reqtype="";
function callonload()
{

	
	if(document.getElementsByName("hmode")[0].value=="NEWWW")
		
{
//	alert("12");	

 pat_crno=<%=request.getAttribute("patCrNo")%> ;
 pat_ward=<%=request.getAttribute("wardCode")%> ;
  pat_reqtype=<%=request.getAttribute("patStatusCode")%> ;

//alert(pat_ward);

document.getElementsByName("sampleAreaCode")[0].value=pat_ward;

$('#crNoInput').val=pat_crno;


//document.getElementsByName("patCrNo")[0].value="961012000000732";
document.getElementById("crNoInput").value=pat_crno;
//document.getElementById("patStatusCode")[0].value="";
document.getElementsByName("sampleAreaCode")[0].value=pat_ward;
document.getElementsByName("patStatusCode")[0].value=pat_reqtype;

var crno = $('#crNoInput').val();
$('#patDetails').removeClass("d-none");

var globalSearchParam={ crNo:crno, billNo:"",searchType:"1"};
AjaxGetPatDetails(globalSearchParam);
$.when( globalAjaxGetPatDtlObj ).then(function( data, textStatus, jqXHR ) {
	var patCategoryCode =globalPatientDetails[0].patCategoryCode;
	setPatCategoryCode(patCategoryCode);
});
setPatCategoryCode();
$.when(globalAjaxGetPatDtlObj ).then(function( data, textStatus, jqXHR ){
	//console.log("fffffff");
 //alert("Loading...");
	var patDtl = globalPatientDetails[0].patStatusCode;
	//alert(patDtl);
	billedUnbilledfun_ipd("2",pat_ward);
	$('#tabsId').removeClass("d-none");
	 });
	 
showLoding(false);
$('#container2ExpandBtn').click();
$("#container2ExpandBtn").off("click");
$('#container2ExpandBtn').addClass("d-none");
$('#CancelBtn1').addClass("d-none");




}
	
}


</script>

</head>
<body onload="callonload()">
<%-- <html:form action="/sampleCollection21"> --%>
<html:form action="/NewsampleCollection">

<fieldset class="m-2 px-1">
	<legend>
	
		<div class="container-fluid my-0 noUserSelect mx-0 px-0" id="container1">
			<div class="row align-items-center no-gutters"><!--  px-1 px-md-2  -->
			
				<div class="col-8 col-sm-6 col-md-3 col-lg-3 textFit">
					<p class="text-nowrap"  style="font-family:averta-extrabolduploaded_file;font-size:21px;">
						<span class="text-primary"><img  src='..\new_investigation\media\images\blood-sample.png'></span>
						Sample Collection
						<span class="fullScreenBtn"><img height="26" width="26" src='..\new_investigation\media\images\expand.svg'></span>
					</p>
				</div>
				
				<div class="col-4 col-sm-6 col-md-9 col-lg-9 d-flex justify-content-end mb-3">
					<hr class="lineBetween"></hr>
					<button type="button" class="btn btn-sm bg-transparent rounded-ld refreshBtn">
						<img src='..\new_investigation\media\images\refresh.svg' height="24" width="24" title="Refresh">
					</button>
					<button type="button" class="btn btn-sm bg-transparent rounded-ld closeBtn">
						<img src='..\new_investigation\media\images\close.svg' height="24" width="24" title="Close">
					</button>
					
				</div>
				
			</div>
		</div>
	</legend>

  		<div class="container-fluid mb-2" id="container2" style="margin-top:-15px;">

			
			<div class="mx-1 row  p-2 align-items-center shadow rounded bg-white  hover-shadow collapse show" id="container2Row1"> <!-- style="font-family: 'Roboto', sans-serif;" -->
				<div class="col-12 col-lg-4 rounded border-right-lg border-primary noUserSelect" >

					<div class="input-group d-flex flex-nowrap mb-2" >
						<div class="input-group-prepend">
							<div  id="combo1" class="input-group-text" style="font-weight:400;font-size:14px;" >Collection Area</div>
						</div>
								
					<logic:notEqual name="SampleCollectionFB" property="duplicateBarcodeGeneration" value="0">
					 				<logic:notEqual name="SampleCollectionFB" property="isSampleAreaSelected" value="1">
						 				  
						 				<html:select    name="SampleCollectionFB"  property="sampleAreaCode" tabindex="1"  styleClass="form-control custom-select">					
											<bean:define id="patSampleCollection" type="java.util.List" name="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>"></bean:define>
												<%if(patSampleCollection.size()>1){ %>
													<html:option value="-1">Select Value</html:option>
												<%} %>
 											 
											<html:options collection="<%=InvestigationConfig.LIST_SAMPLE_COLLECTION_VO%>" property="value" labelProperty="label"  />
										</html:select> 
									
									</logic:notEqual>
									 <logic:equal name="SampleCollectionFB" property="isSampleAreaSelected" value="1">
										
					 					  <b>&nbsp;&nbsp;<bean:write name="SampleCollectionFB" property="sampleAreaName"/></b>
					 			
					 					  <html:hidden name="SampleCollectionFB" property="sampleAreaCode"/>
					 				
									 </logic:equal>
				</logic:notEqual>
					</div>
					
					

				</div>
				
				<div class="col-12 col-lg-4 rounded border-right-lg border-primary noUserSelect " id="searchId">
					<div class="input-group d-flex flex-nowrap mb-2">
						<div class="input-group-prepend">
							<div id="combo2" class="input-group-text" style="font-weight:400;font-size:14px;">Cr No.</div>
						</div>
					<input type="text" class="form-control" id="crNoInput" placeholder="Enter Cr. No." maxlength="15" minlength="15" style="font-weight:400;font-size:14px;">
					</div>
				</div>
				
				<div class="col-12 col-lg-4 noUserSelect">
					<div class="form-row align-items-center d-flex">
							<div class="col-12 col-lg-4 mr-auto getDataBtnDiv" >
							<button id="getData" type="button" class="btn btn-primary ">
								<i class="text-nowrap" style="font-weight:700;font-size:14px;"> Search </i>
							</button>

							<button id="getingData" class="btn btn-primary text-nowrap d-none"  disabled>
								<span class="spinner-border spinner-border-sm" style="font-weight:700;font-size:14px;"></span> Loading..
							</button>
						</div>
						
							<div class="radio" style="vertical-align:middle;color:rgb(0, 0, 139);" >
      							<label style="font-weight:700;font-size:14px;"><input type="radio" name="duplicateBarcodeGeneration"  onclick="showduplicatebarcode()">&nbsp;Duplicate BarCode Printing</label>
						</div>
					
					</div>

				</div>
				
			</div>

				<div class="d-flex justify-content-end border-top-lg border-dark mx-1  ">
				<button id="container2ExpandBtn" class="btn btn-sm btn-warning ml-auto" type="button" data-toggle="collapse" data-target="#container2Row1" aria-expanded="true" aria-controls="collapseExample">
					<i class="fa fa-arrow-up "></i>
					<i class="fa fa-arrow-down d-none"></i>
				</button>
				</div>
</div>

<!-------------------------------------------------------------------------------duplicate barcode section  ----------------------------------------------------------------------------->
<div class="container-fluid mb-2 d-none" id="container21" style="margin-top:-15px;">

			
			<div class="mx-1 row  p-2 align-items-center shadow rounded bg-white  hover-shadow collapse show" id="container2Row1"> <!-- style="font-family: 'Roboto', sans-serif;" -->
				<div class="col-12 col-lg-4 rounded border-right-lg border-primary noUserSelect" >
					<div class="input-group d-flex flex-nowrap mb-2">
						<div class="input-group-prepend">
							<div id="combo2" class="input-group-text" style="font-weight:400;font-size:14px;">Cr No.</div>
						</div>
					<input type="text" class="form-control" id="crNoInput1" placeholder="Enter Cr. No." maxlength="15" minlength="15" style="font-weight:400;font-size:14px;">
					</div>
				
				</div>
				
				
				<div class="col-12 col-lg-4 noUserSelect">
					<div class="form-row align-items-center d-flex">
							<div class="col-12 col-lg-4 mr-auto getDataBtnDiv" >
							<button id="getDataForDupBarCode" type="button" class="btn btn-primary ">
								<i class="text-nowrap"> Search </i>
							</button>

							<button id="getingData" class="btn btn-primary text-nowrap d-none"  disabled>
								<span class="spinner-border spinner-border-sm"></span> Loading..
							</button>
						</div>
						
						</div>
						</div>
						<div class="col-12 col-lg-4 noUserSelect">
						<div class="form-row align-items-center d-flex">
							<div class="radio" style="vertical-align:middle;color:rgb(0, 0, 139);" >
      							<label style="font-weight:700;font-size:14px;" ><input type="radio" name="duplicateBarcodeGen" onclick="showduplicatebarcode()">&nbsp;Duplicate BarCode Printing</label>
							</div>
							&nbsp;&nbsp;
						<div  id="sampleBack" class="radio" style="vertical-align:middle;color:rgb(0, 0, 139);" >
      							<label style="font-weight:700;font-size:14px;"><input type="radio" name="samplesection"  onclick="showsamplecol()">&nbsp;Sample Collection</label>
						</div>
					</div>

				</div>
				
			</div>

				<div class="d-flex justify-content-end border-top-lg border-dark mx-1  ">
				<button id="container2ExpandBtn" class="btn btn-sm btn-warning ml-auto" type="button" data-toggle="collapse" data-target="#container2Row1" aria-expanded="true" aria-controls="collapseExample">
					<i class="fa fa-arrow-up "></i>
					<i class="fa fa-arrow-down d-none"></i>
				</button>
				</div>
</div>

<div class="container-fluid contianer-responsive  col-12 col-sm-12 col-md-12  d-none" id="barcodePatDivId" >

	<ul class="tabs">
		<li class="tab-link current" data-tab="tab-10"><h5 class="text-black"  style="font-weight:700;font-size:14px;">Sample Collected Details <span class="badge badge-primary" id="barcodeSpan"></span></h5></li>
	</ul>

	<div id="tab-10" class="tab-content current">
				<div id="alltab-1" class="col-12 pt-2 collapse show" >
					<table id="exampleBarCode"
						class="table table-sm dt-responsive nowrap table-condensed mx-md-0 rounded bg-white "
						style="width: 100%">
						<thead style="font-weight:700;font-size:14px;">
							<tr style="font-size:14px;">
								<th></th>
								<th><input type="checkbox" onclick="dupselectAll()" id="dupselectallchk"></th>
								<th>CR No</th>
								<th>Patient Name</th>
								<th>Age</th>
								<th>Lab Name</th>
								<th>Test Name</th>
								<th>Sample No.</th>
								<th>Requisition Date</th>
								<th>Patient Status</th>
								<th>Department Unit</th>
								<th>Visit Date</th> 
							</tr>
						</thead>
						<tbody style="font-weight: 400;font-size: 14px;">
						</tbody>
						
					</table>
				</div>
				
				<div class="col-12 pt-2 d-flex justify-content-center" id="save_id" style="font-size:0.9em;font-weight:bold; color:red; padding-left:0px;padding-top:5px; margin-left:0px;"></div>
				
				
				<div class="col-12 pt-2 d-flex justify-content-center">
				
					<div class="saveGENBtnDiv">
						<button id="saveGENBtn" type="button" class="btn btn-primary text-nowrap saveValidateBtn" disabled onClick="GenerateBarcode()">
							<span class=""></span> Go
						</button>
					</div>
					&emsp;
					
					<div class="CancelBtnDiv">
						<button id="CancelBtn2" type="button" class="btn btn-danger text-nowrap CancelBtn">
							<span class=""></span> Cancel
						</button>
					</div>
				</div>
			<div class="col-12 pt-2 d-flex justify-content-center" id="save_id" style="font-size:0.9em;font-weight:bold; color:red; padding-left:0px;padding-top:5px; margin-left:0px;"></div>
	</div>
	

</div>


<!----------------------------------------------------------------------End  ----------------------------------------------------------------->
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
			 
					<table id="DataTableOPD" class="table table-sm dt-responsive nowrap table-condensed mx-md-0 rounded bg-white " style="width: 100% ; ">
						<thead style="font-weight: 700;font-size: 14px;">
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
						<tbody style="font-weight: 400;font-size: 14px;"> </tbody>
					</table>
				</div>
				
  <input type="hidden" name="patCategoryCode" id="patCategoryCode_samplecoll01" value="">
			
				<div class=" col-md-11  rounded hover-shadow overflow-auto d-none" id="patDetails-table-Ipd" >
			
					<table id="DataTableIPD" class="table table-sm dt-responsive nowrap table-condensed mx-md-0 rounded bg-white " style="width: 100% ; ">
						<thead style="font-weight: 700;font-size: 14px;">
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
						<tbody style="font-weight: 400;font-size: 14px;"> </tbody>
					</table>
				</div>

			</div>
		</div>

		<div class="container-fluid contianer-responsive  col-12 col-sm-12 col-md-12  d-none" id="tabsId" >

	<ul class="tabs">
		<li class="tab-link current" data-tab="tab-1"><h5 class="text-black"  style="font-weight:700;font-size:14px;">Billed <span class="badge badge-primary" id="billedSpan"></span></h5></li>
		<li class="tab-link" data-tab="tab-2"><h5 class="text-black"  style="font-weight:700;font-size:14px;">UnBilled <span class="badge badge-danger" id="UnbilledSpan"></span></h5></li>
		
	</ul>

	<div id="tab-1" class="tab-content current">
				<div id="allValReqnList_tab-1" class="col-12 pt-2 collapse show" >
					<table id="example" class=" table table-sm  dt-responsive nowrap table-condensed mx-md-0 bg-white " style="width: 100%;">
						<thead  style="font-weight:700;font-size:14px;">
							<tr>
								<th></th>
								<th><input type="checkbox" class="selectAllDtCheck1" id="selectAllDtCheck1"></th>
								<th>Lab Name</th>
								 <th>Test Name</th>
								<th>Requsition Date</th>
								<th>Specimen</th>
								<th>Sample No.</th>
								<th>Collection Vol.</th>
								<th>Unit Of Measurement</th>
								<th>Container</th>
								<th>Priority</th>
								<th>Machine</th> 
								<th>Reqno</th> 
								<th>Reqno</th> 
								
							</tr>
						</thead>
						<tbody style="font-weight: 400;font-size: 14px;">
						</tbody>
						
					</table>
				</div>
				
				
				
				<div class="col-12 pt-2 d-flex justify-content-center">
				
					<div class="saveValidateBtnDiv">
						<button id="saveValidateBtn" type="button" class="btn btn-primary text-nowrap saveValidateBtn" disabled onClick="Save()">
							<!-- <i class="text-nowrap"> Go </i> -->
							<span class=""></span> Save
						</button>
					</div>
					&emsp;
					
					<div class="CancelBtnDiv">
						<button id="CancelBtn1" type="button" class="btn btn-danger text-nowrap CancelBtn">
							<span class=""></span> Cancel
						</button>
					</div>
				</div>
				<div class="col-12 pt-2 d-flex justify-content-center" id="save_id" style="font-size:0.9em;font-weight:bold; color:red; padding-left:0px;padding-top:5px; margin-left:0px;"></div>
				<div class="col-12 pt-2 d-flex justify-content-center" id="save_nor_id" style="font-size:0.9em;font-weight:bold; color:red; padding-left:0px;padding-top:5px; margin-left:0px;"></div>
				
	</div>
	
	
	<div id="tab-2" class="tab-content ">
		 <div id="allValReqnList_tab-2" class="col-12 pt-2 collapse " >
					<table id="example1" class="display table table-sm  nowrap table-condensed mx-md-0 shadow rounded bg-white hover-shadow" style="width: 100%;">
						<thead style="font-weight:700;font-size: 14px;">
							<tr>
				
                <th>Lab Name</th>
               	<th>Test Name</th>
                <th>Requsition Date</th>
                <th>Priority</th>
			    <th>Priority</th>
			
							</tr>
						</thead>
						<tbody style="font-weight:400;font-size: 14px;">
						</tbody>
						
					</table>
				</div>
				
				
				
				<div class="col-12 pt-2 d-flex justify-content-center">
				
					
					<div class="cnclBtnDiv">
						<button id="CancelBtn3" type="button" class="btn btn-danger text-nowrap " >
							<span class=""></span> Cancel
						</button>
					</div>
				</div>
				<div class="col-12 pt-2 d-flex justify-content-center" id="save_id" style="font-size:0.9em;font-weight:bold; color:red; padding-left:0px;padding-top:5px; margin-left:0px;"></div>
				
	</div>
	

</div><!-- container -->

</fieldset>

<html:hidden name="SampleCollectionFB" property="duplicateBarcodeGeneration" />
 
			<html:hidden name="SampleCollectionFB" property="hmode" />
			<html:hidden name="SampleCollectionFB" property="flagforipddesk" />
			<html:hidden name="SampleCollectionFB" property="patCRNo"/>
			<html:hidden name="SampleCollectionFB" property="statuschange" />
			<html:hidden name="SampleCollectionFB" property="modebarcode" />
			<html:hidden name="SampleCollectionFB" property="selectedCheckbox" />
			 <html:hidden name="SampleCollectionFB" property="showStatus" />
			<html:hidden name="SampleCollectionFB" property="sampleAreaName" />
			<html:hidden name="SampleCollectionFB" property="sampleAreaCode" />
			<html:hidden name="SampleCollectionFB" property="isSampleAreaSelected" />
			<html:hidden name="SampleCollectionFB" property="isSearchclicked" />
			<html:hidden name="SampleCollectionFB" property="patStatusCode" />

</html:form>

<a data-fancybox data-type="iframe" id="fancyBoxIFrame" class="fancyBoxIFrame"></a>
  	

</body>
</html>