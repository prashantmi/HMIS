 <%@page import="org.json.simple.*"%>
<%@page import="javax.json.JsonArray"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.collections.MultiMap"%>
<%@page import="org.apache.commons.collections.map.MultiValueMap"%>
<%@page import="org.json.*"%>

<meta charset="utf-8">


<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js"></script>  -->
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
 <link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/jquery-ui.css">  
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.min.js"></script> -->
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script> -->
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js"></script> --> 
<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script> -->
<script type="text/javascript" src="../../new_opd/assests/jquery.autocomplete.js"></script>

<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.devbridge-autocomplete/1.4.11/jquery.autocomplete.js"></script> -->
<!-- <script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.min.js"></script> -->
<!-- <script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script> -->
<!-- <script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.min.js"></script> -->
<!-- <script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script> -->

<script type="text/javascript" src="../../new_opd/assests/jquery-ui.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>  
<script src="/HIS/hisglobal/drDeskAssets/flexdatalist/jquery.flexdatalist.min.js"></script>    
<script src="/HIS/hisglobal/drDeskAssets/bootstrap/js/bootstrap.min.js"></script> 
<!--   <link href="/HISDRDESK/new_opd/assests/treeview/bootstrap-treeview.min.css" rel="stylesheet"> 
  <script type="text/javascript" src="/HISDRDESK/new_opd/assests/treeview/bootstrap-treeview.min.js"></script> -->
<!-- <link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/multi/bootstrap.min.css"> -->
    <!--  <script src="/HIS/hisglobal/drDeskAssets/multi/bootstrap.min.js"></script>
     <link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/multi/bootstrap-multiselect.css" type="text/css">
     <script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/multi/bootstrap-multiselect.js"></script> -->
  
<!-- 
<link rel="stylesheet" href="/HIS/hisglobal/js/jquery/multiselect/css/bootstrap-multiselect.css">
  <script type="text/javascript" src="/HIS/hisglobal/js/jquery/bootstrap-multiselect.js"></script>
   -->
<script type="text/javascript" src="/HISDRDESK/new_opd/js/myScript_new.js"></script> 
<script type="text/javascript" src="/HISDRDESK/new_opd/js/prescrptionProfile.js"></script> 
<script type="text/javascript" src="/HISDRDESK/new_opd/js/prescriptionScript.js"></script> 
<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/flexdatalist/jquery.flexdatalist.min.css">
<!-- <script type="text/javascript" src="../../new_opd/assests/jquery-ui.min.js"></script> -->

	<link rel="stylesheet" href="/HISDRDESK/new_opd/assests/jquery-confirm.min.css">
	<script src="/HISDRDESK/new_opd/assests/jquery/jquery-confirm.min.js"></script>
	<script type="text/javascript" src="/HISDRDESK/new_opd/js/Chief_Complaint.js"></script> 
	
	<link href="/HISDRDESK/new_opd/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="/HISDRDESK/new_opd/css/bootstrap.css" rel="stylesheet">
	
 	<script type="text/javascript" src="/HISDRDESK/new_opd/js/OpenTemplate.js"></script>
	<script type="text/javascript" src="/HISDRDESK/new_opd/js/ExtOpenTemplate.js"></script> 
	<link rel="stylesheet" type="text/css" href="/HISDRDESK/new_opd/css/fb.css"> 
	<link rel="stylesheet" type="text/css" href="/HISDRDESK/new_opd/css/opdtemplate.css"> 
	<script type="text/javascript" src="/HISDRDESK/new_opd/js/cimsintegration.js"></script> 


<!-- <link type="text/css" href="/HISDRDESK/cims/CSS/redmond/jquery-ui.css" rel="stylesheet">	-->
<link type="text/css" href="/HISDRDESK/cims/CSS/mims.css" rel="stylesheet">
<script type="text/javascript" src="/HISDRDESK/cims/Scripts/jjQuery JavaScript Library v2.1.3.js"></script>
<script type="text/javascript" src="/HISDRDESK/cims/Scripts/jquery-ui.min.js"></script>
<script type="text/javascript" src="/HISDRDESK/cims/Scripts/drugalert.js"></script>


<style>

	.nav-tabs .active a {
	    background-color: #2b438a !important;
	    color: #fff !important;
	 } 
	 ul#menu1 li {
	display:inline;
	margin: 0px !important;
	font-size: 14px;
	letter-spacing: inherit !important;
	color: #5a5a5a !important;
	font-weight: 600;
	text-align: justify;
	}
	
	
	#AllergyResponse h3{
	border:none !important;
	background:none !important;
	height:initial !important;
	padding-top:0px !important;
	}
	
	
	#MonographResponse p{
	border:none !important;
	background:none !important;
	height:initial !important;
	padding-top:0px !important;
	}

	
</style>
<style>

 .genericDesc{
  font-size:20px;
  color:#004080;
 }
 
 .drugTypeName{
 font-size:18px;
 }
 
 .modal-header{
 color:#41B4E1;
 font-size: large;
 }

</style>
 
<%@page import="java.util.*"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>

<!-- Buttons and Image tag for Uploaded Prescriptions starts here --> 
  <div class="btn-group btn-group-lg" id="prescImgControlBtnGrpFixed" style="display:none;position: fixed; top: 0px; z-index: 10000; right: 0px;">
    <button id="prescImgZoomOutBtnFixed" type="button" style="  */font-size: 18px; padding: 0px 7px;" class="btn btn-warning btn-sm"><i class="fa fa-minus-circle"></i></button>
	<button id="prescImgZoomInBtnFixed" type="button" style="font-size: 18px; padding: 0px 7px;" class="btn btn-warning btn-sm"><i class="fa fa-plus-circle"></i></button>
	<button id="prescImgOriginalBtnFixed" type="button" style=" font-size: 18px; padding: 0px 7px;" class="btn btn-warning btn-sm"><i class="fa">&#xf1c5;</i></button>
	<button id="" type="button" style=" font-size: 18px;padding: 0px 10px;" class="btn btn-warning btn-sm" onclick="printImg()"><i class="fa fa-print"></i></button>
	<button id="prescImgRotateLeftBtnFixed" type="button" style="font-size: 18px; padding: 0px 7px; " class="btn btn-warning btn-sm"><i class="fa">&#xf0e2;</i></button>
	<button id="prescImgRotateRightBtnFixed" type="button" style="  font-size: 18px; padding: 0px 7px; " class="btn btn-warning btn-sm"><i class="fa">&#xf01e;</i></button>
	<button id="prescImgCloseBtnFixed" type="button" style=" font-size: 18px;padding: 0px 10px;" class="btn btn-danger btn-sm">x</button>
  </div>
  <img id="prescImgFixed" src=""  style="position:fixed; display:none; right:0; z-index:9999;top:0px; width:auto;height:100vh"/> <!-- /HISDRDESK/new_opd/img/pat_presc.jpg -->
<!-- Buttons and Image tag for Uploaded Prescriptions Ends here --> 
	
	
 <script type="text/javascript">
function printImg() {
  pwin = window.open(document.getElementById("prescImgFixed").src,"_blank");
  pwin.print();
  pwin.close();
  /* setTimeout(function(){  }, 80);  */
}


</script>

<script type="text/javascript">
/* function my_code(){
	//location.reload(true/false);
	window.location.reload();
}

window.click=my_code();  */


/* document.getElementById("tabs-header-refresh").addEventListener("click", function() {
	window.location.reload();
	
});  */
/* $('#tabs-header-refresh').click(function(e){
	location.reload(true/false);
}); */
$(function(){
	 $("#tabs-header-refresh").on("click", function(e){
		 window.location.reload();
	 });

	});
</script>

  <!-- <button type="button" class="btn btn-default patientSumBtn">Patient Summary</button> -->
  
   <script>
/*     function prescTabSearch(e){ 
      var txt = e.value;
      txt = txt.toUpperCase(); 
      $('.nav-tabs li:not(:contains('+txt+'))').hide('slow',function(){
        $('.nav-tabs li:visible').eq(0).children('a').click();
      });
      $('.nav-tabs li:contains('+txt+')').show('slow',function(){
        $('.nav-tabs li:visible').eq(0).children('a').click();
      });
    } */

  /*   ehrVisitReasonOnLoad(); */
     /*  $(window).on("load", function() {

    	  ehrVisitReasonOnLoad();

    	}); */
  

  </script> 
  
  <!-- Modal -->
      <div class="modal fade" id="chronicDiseaseInstructionsModal" role="dialog">
        <div class="modal-dialog">       <!-- Modal content-->
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">x</button>
              <h2 class="modal-title">Chronic Disease Instructions</h2>
            </div>
            <div class="modal-body" style="text-align: left;">
               <p class="text-center">
                 Instructions of doctor will append here.
               </p>
            </div>  
          </div> 
        </div>
      </div>

      <!-- Modal -->
      <div class="modal fade" id="allergiesDtlInstructionsModal" role="dialog">
        <div class="modal-dialog">       <!-- Modal content-->
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">x</button>
              <h2 class="modal-title">Allergies Instructions</h2>
            </div>
            <div class="modal-body" style="text-align: left;">
               <p class="text-center">
                 Instructions of doctor will append here.
               </p>
            </div>  
          </div> 
        </div>
      </div> 
  
  		<!-- Modal -->
			      <div class="modal fade" id="opdEmrModal" role="dialog">
			          <div class="modal-dialog modal-xl">       <!-- Modal content-->
			          <div class="modal-content">
			              <div class="modal-header">
				              <button type="button" class="close" data-dismiss="modal">x</button>
				              <h2 class="modal-title text-left">Past Rx</h2>
		                  </div>
		                  <div class="modal-body" style="text-align: left;"> 
			              	<div class="row" style="min-height:300px;">
			                	<div class="col-sm-12">
			                		<div class="col-xs-5 col-sm-3 col-md-2"> 
							            <ul class="nav nav-tabs tabs-left nav-pills nav-stacked opdEmrModalNavMenu">
							                <li class="active"><a href="#home" data-toggle="tab">Select Date</a></li> 
							            </ul>
							        </div>
							        <div class="col-xs-7 col-sm-9 col-md-10">
							            <div class="tab-content opdEmrModalNavMenuContent">
							                <div class="tab-pane active" id="home">
												<ul class="patPreviousPrescriptionList" style="list-style: disclosure-closed;">
												</ul>
							                </div> 
							            </div>
							        </div>
			                  <div class="clearfix"></div>
			                </div>  
			              </div>
			              
			            </div>  
			              </div> 
			          </div>
			      </div>
                 <!--  -->
                 
                 <!-- Modal -->
			      <div class="modal fade" id="TreeStructurePrescriptionModal" role="dialog">
			          <div class="modal-dialog modal-xl" style="min-width:90vw">       <!-- Modal content-->
			          <div class="modal-content">
			              <div class="modal-header">
				              <button type="button" class="close" data-dismiss="modal">x</button>
				              <h2 class="modal-title text-left">OPD EMR</h2>
		                  </div>
		                  <div class="modal-body" style="text-align: left;"> 
			              	<div class="row" style="min-height:300px;">
			                	<div class="col-sm-12">
			                		<div class="col-xs-5 col-sm-3 col-md-3"> 
							            <ul class="nav nav-tabs tabs-left nav-pills nav-stacked TreeStructurePrescriptionModalNavMenu">
							                <li class="active"><a href="#TreeStructurePrescriptionModalTab1" data-toggle="tab">Select Category</a></li>
							                <!-- <li><a href="#TreeStructurePrescriptionModalTab2" data-toggle="tab">Profile</a></li>
							                <li><a href="#TreeStructurePrescriptionModalTab3" data-toggle="tab">Vitals Chart</a></li>
							                <li><a href="#TreeStructurePrescriptionModalTab4" data-toggle="tab">Chief Complaint</a></li>
							                <li><a href="#TreeStructurePrescriptionModalTab5" data-toggle="tab">Diagnosis</a></li>
							                <li><a href="#TreeStructurePrescriptionModalTab6" data-toggle="tab">Investigations</a></li>
							                <li><a href="#TreeStructurePrescriptionModalTab7" data-toggle="tab">Drug/Advices</a></li>
							                <li><a href="#TreeStructurePrescriptionModalTab8" data-toggle="tab">Clinical Note</a></li> --> 
							            </ul>
							        </div>
							        <div class="col-xs-7 col-sm-9 col-md-9">
							            <div class="tab-content TreeStructurePrescriptionModalNavMenuContent">
							                <div class="tab-pane active treeStructurePresc" id="TreeStructurePrescriptionModalTab1">
												<ul class="TreeStructurePrescriptionModalErrMsg" style="list-style: disclosure-closed;">
												</ul>
							                </div>
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab2">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab2');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div> 
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab3">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="$('#parameterVitalsTrendBtn').hide();printDiv('TreeStructurePrescriptionModalTab3');$('#parameterVitalsTrendBtn').show();" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div> 
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab4">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab4');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div> 
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab5">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab5');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div> 
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab6">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab6');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div> 
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab7">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab7');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div> 
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab8">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab8');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div>
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab9">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab9');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div> 
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab10">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab10');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div> 
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab11">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab11');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div>
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab12">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab12');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div>
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab13">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab13');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div>  
							                 <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab15">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab15');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div> 
							                
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab16">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab16');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div>  
							                
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab17">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab17');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div>  
							                
							                <div class="tab-pane treeStructurePresc" id="TreeStructurePrescriptionModalTab18">
							                	<div style="right:8px; position:fixed">
													<input type="button" onclick="printDiv('TreeStructurePrescriptionModalTab18');" value="Print" class="btn btn-info previousPrescDetailsPrintBtn pull-right" />
												</div>
							                </div>  
							            </div>
							        </div>
			                  <div class="clearfix"></div>
			                </div>  
			              </div>
			              
			            </div>  
			              </div> 
			          </div>
			      </div>
                 <!--  -->
  
  <!-- Modal -->
  <div class="modal fade" id="printAllModal" role="dialog">
    <div class="modal-dialog">       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title">Select Print Items</h2>
        </div>
        <div class="modal-body" style=" text-align: left;">
          <div class="row">
            <div class="col-sm-2 col-sm-offset-2">
              <input type="checkbox" form="form-control" name="" checked=""> 
            </div>
            <div class="col-sm-8">
              <p>Prescription</p> 
            </div>
          </div> 
          <div class="row">
            <div class="col-sm-2 col-sm-offset-2">
              <input type="checkbox" form="form-control" name="" checked=""> 
            </div>
            <div class="col-sm-8">
              <p>Complaint</p> 
            </div>
          </div> 
          <div class="row">
            <div class="col-sm-2 col-sm-offset-2">
              <input type="checkbox" form="form-control" name="" checked=""> 
            </div>
            <div class="col-sm-8">
              <p>Examination</p> 
            </div>
          </div> 
          <div class="row">
            <div class="col-sm-2 col-sm-offset-2">
              <input type="checkbox" form="form-control" name="" checked=""> 
            </div>
            <div class="col-sm-8">
              <p>Episode  Alergies</p> 
            </div>
          </div> 
          <div class="row">
            <div class="col-sm-2 col-sm-offset-2">
              <input type="checkbox" form="form-control" name="" checked=""> 
            </div>
            <div class="col-sm-8">
              <p>History</p> 
            </div>
          </div> 
          <div class="row">
            <div class="col-sm-2 col-sm-offset-2">
              <input type="checkbox" form="form-control" name="" checked=""> 
            </div>
            <div class="col-sm-8">
              <p>Patient Chronic Disease</p> 
            </div>
          </div> 
          <div class="row">
            <div class="col-sm-2 col-sm-offset-2">
              <input type="checkbox" form="form-control" name="" checked=""> 
            </div>
            <div class="col-sm-8">
              <p>Service Requisition Details</p> 
            </div>
          </div> 
          <div class="row">
            <div class="col-sm-2 col-sm-offset-2">
              <input type="checkbox" form="form-control" name="" checked=""> 
            </div>
            <div class="col-sm-8">
              <p>Admission Advice</p> 
            </div>
          </div>
          <div class="row">
            <div class="col-sm-2 col-sm-offset-2">
              <input type="checkbox" form="form-control" name="" checked=""> 
            </div>
            <div class="col-sm-8">
              <p>External Examination Details</p> 
            </div>
          </div>
          <br><br>
          <div class="row text-center">
           <!--  <button class="btn btn-primary savePrintPrescBtn" type="button">Print</button>   -->
          </div>
        </div> 
      </div> 
    </div>
  </div>

  <!-- Modal -->
  <div class="modal fade" id="followUpDocUploadModal" role="dialog">
    <div class="modal-dialog">       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title">Patient Document Upload</h2>
        </div>
        <div class="modal-body" style="text-align: left;">
        	<div id="followUpDocumentUpload" class="well center-block">
        		<div class="media">
				  <div class="media-left" style="width:20%">
				    <img src="/HIS/hisglobal/drDeskAssets/img/upload_icon2.png" class="media-object" style="width:60px">
				  </div>
				  <div class="media-body">
				    <h4 class="media-heading text-left">Upload File</h4>
				    <p>Drag and Drop...</p>
				  </div>
				</div>
        	</div> 
        	<div class="row">
        		<div class="col-sm-8 col-sm-offset-2">
        			<div class="dropzone-previews" id="followUpDocumentUploadPreview"></div>
        		</div>
        	</div>
        </div>  
      </div> 
    </div>
  </div> 

  <!-- Modal -->
     <%
          			MultiValueMap DrugProfileBookmark= (MultiValueMap) request.getSession().getAttribute("DrugProfile");
                   	
                     if(DrugProfileBookmark!=null)
                   	{
                   		Set<String> keys = DrugProfileBookmark.keySet();
                   	for(String key : keys){ 
                   		
                   	  %>
                   	  
					<div class="modal fade" id="investigationTestBundle<%=key.split("#")[0] %>" role="dialog">
					    <div class="modal-dialog">       <!-- Modal content-->
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h2 class="modal-title">Bookmark Drug Detail</h2>
					        </div>
					        <div class="modal-body" style="text-align: left;">
					          <div class="row">
					            <div class="col-sm-16 col-sm-offset-1">
					              <p>Bookmark Name : <font class="bookmarkTestName"><%=key.split("#")[1] %></font></p>
					              
					             <table id="" class="table table-condensed table-hover" style="margin-bottom: 0px;"> 
                            <thead>
                              <tr style="background: linear-gradient(to bottom, #dbdbdb /* #acd6ff */, white);">
                                <th><input type="checkbox" class="checkedInputAll" name="drugsAdvicesAll"></th><th>Drug</th><th>Dosage</th><th>Frequency</th><th class="drugAdviceListExcessCol">Start Date</th><th>Days</th><th>Quantity</th><th class="drugAdviceListExcessCol">Instructions</th><th>Change</th>
                              </tr>
                            </thead>
                            <tbody>
                            
				       <%
						JSONObject drugProfileJsonObj =null;
				      
						for(int i=0 ; i<(((List) DrugProfileBookmark.getCollection(key)).size()) ; i++){
								System.out.println("Json Obejct"+((List) DrugProfileBookmark.getCollection(key)).get(i));
								//List<String> jsonStr=((List) Unattented.getCollection(key1)).get(i);
								drugProfileJsonObj =  (JSONObject)(((List) DrugProfileBookmark.getCollection(key)).get(i));
								System.out.println("DRUGNAME ==="+drugProfileJsonObj.get("DRUGNAME"));
								String DRUGNAME=(String)drugProfileJsonObj.get("DRUGNAME");
								
								%>
								<tr id="rowId"> <td><input type="checkbox" class="checkedInput" checked="checked" name="drugsAdvicesBook" value="<%=drugProfileJsonObj.get("HIDDEN_VAL") %>" ></td><td><%=DRUGNAME %></td><td><%=drugProfileJsonObj.get("DOSENAME") %></td><td><%=drugProfileJsonObj.get("FREQUENCY_NAME") %></td><td><%=drugProfileJsonObj.get("CURR_DATE") %></td><td><%=drugProfileJsonObj.get("DAYS") %></td><td><%=drugProfileJsonObj.get("TOTAL_QTY") %></td><td><a class="drugAdvicesInstructionsModalBtn" style="color: #109f1c" druginstructions="Remaeks" onclick="$('#drugAdvicesInstructionsModal').modal('show');"><%=drugProfileJsonObj.get("REMARKS") %></a></td><td><button class="btn btn-xs drugEditBtn" type="button"><i class="fa fa-edit"></i></button></td></tr></tbody>
                             <%
						}
                             %>
                            
                           </table>
                           
					            </div> 
					          </div>  
					          <br> 
					          <div class="row text-center">
					            <button class="btn btn-primary savePrintPrescBtn drugBookmarkAddBtn" type="button">Add</button>  
					          </div>
					        </div>  
					      </div> 
					    </div>
					  </div> 
                   	  
                   	  <%		
                   	}
                   	}
                     
                     %>
  
  
  
  
  <% 
  	HashMap<String ,String> BookmarkTestMAP = (HashMap) request.getSession().getAttribute("BookmarkDTL");
    //System.out.print("MacrosDTL"+MacrosMap);	
	ArrayList<String> listBookMarkTestList=null;
	int j=0;
	 	if(BookmarkTestMAP!=null)
	 	{
			for(Map.Entry m:BookmarkTestMAP.entrySet()){ 
				listBookMarkTestList=new  ArrayList<String>();
				listBookMarkTestList=(ArrayList) m.getValue();
	//			System.out.println("::::"+m.getValue());
	
				%>
				  <div class="modal fade" id="investigationTestBundle<%=j %>" role="dialog">
				    <div class="modal-dialog">       <!-- Modal content-->
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h2 class="modal-title">Bookmark Test Detail</h2>
				        </div>
				        <div class="modal-body" style="text-align: left;">
				          <div class="row">
				            <div class="col-sm-8 col-sm-offset-2">
				              <p><input type="checkbox" name="bookmarkAllCheck" value="1" onchange="checkAllBookmarkItem(this)" checked> BOOKMARK NAME : <font class="bookmarkTestName"> <%=listBookMarkTestList.get(5) %></font></p>
				              <%
				              		System.out.println("listBookMarkTestList"+listBookMarkTestList.size());
				                   listBookMarkTestList.size();
				                   
				                   for(int l=0 ; l <listBookMarkTestList.size() ; l=l+8){
				                	   String TestCodeVal=listBookMarkTestList.get(l+2);
				              %>
				              
				              <p style="font-size:0.95em"><label><input type="checkbox" name="bookmarkSubTest" value="<%=TestCodeVal %>" checked> <%=listBookMarkTestList.get(l+4) %></label></p>
				            <% 
				             } %>
				            </div> 
				          </div>  
				          <br> 
				          <div class="row text-center">
				            <button class="btn btn-primary savePrintPrescBtn investigationBookmarkAddBtn" type="button">Add</button>  
				          </div>
				        </div>  
				      </div> 
				    </div>
				  </div> 
		<%	
		 j++;
			}
	 	} 
  %> 
  
  <!-- Modal -->
  

  <%
	MultiValueMap MacrosDTL= (MultiValueMap) request.getSession().getAttribute("MacrosDTL");
 	//System.out.println("DeptCMB"+RoomWise);
 	if(MacrosDTL!=null)
 	{
 		Set<String> keys = MacrosDTL.keySet();
 	for(String key : keys){ 
 		System.out.println("MAcros Keys"+key);
 		System.out.println("MAcros Keys Dtl"+MacrosDTL.get(key));
 		System.out.println("MAcros Keys Dtl"+((List) MacrosDTL.getCollection(key)).size());
 		
 		
 		JSONObject patJsonObj =null;
 		
 		%>

  
  <div class="modal fade" id="progressNoteMacroModal<%=key %>" role="dialog">
    <div class="modal-dialog">       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title">Macros</h2>
        </div>
        <div class="modal-body" style="text-align: left;">
          <div class="row">
            <div class="col-sm-8 col-sm-offset-2"> 
          <%
          
          for(int i=0 ; i<(((List) MacrosDTL.getCollection(key)).size()) ; i++){
				System.out.println("MaceosDTL::::::::::"+((List) MacrosDTL.getCollection(key)).get(i));
				//List<String> jsonStr=((List) Unattented.getCollection(key1)).get(i);
				patJsonObj =  (JSONObject)(((List) MacrosDTL.getCollection(key)).get(i));
				
          %>
				<p><label><input type="radio" name="macroNoteVal" value="<%=patJsonObj.get("HGSTR_MACRO_DESC")%>" > <%=patJsonObj.get("HGSTR_MACRO_HEADER")%></label></p>			   
              <!-- <p><label><input type="radio" name="macroNoteVal" value="Patient has to do oral rinse with warm water twice a day.Take pain killer inn case of major pain.In case of fever more then 101 visit hospital."> Marco 1</label></p> -->
              <!-- <p><label><input type="radio" name="macroNoteVal" value="Bedrest for 7 days, high liquid diet, Cold water soak."> Macro 2</label></p> --> 
           
           
           <%
           }
          %>
           
            </div> 
          </div>  
          <br> 
          <div class="row text-center">
            <button class="btn btn-primary savePrintPrescBtn addMacroToProgressNoteBtn" type="button">Add</button>  
          </div>
        </div>  
      </div> 
    </div>
    <input type="hidden" name="macrisHidden" id="macrisHiddenId" value="">
     <input type="hidden" name="macrisHidden" id="macrisHiddenIdval" value="">
  </div> 
  
 					 <%
 	}
                     }
                     %>
  
    <!-- Modal -->
  <div class="modal fade" id="printPrescriptionModal" role="dialog">
    <div id="printPrescriptionModalId12" class='modal-dialog modal-lg'>       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        </div>
        <div class="modal-body">
       		 <div class="text-center">
       			<button type="button" class="btn btn-warning" onclick="$('#printPrescriptionModal').modal('hide');$('#printPrescFrameId').remove();">Cancel</button>
				<!-- <button type="button" class="btn btn-info printPrescSAveBtn" onclick="">Save</button> -->
			 </div> 
        </div>  
      </div> 
    </div>
  </div>  
  
  
  
  
  
  
  <!-- Open Template -->
   <div class="modal fade" id="OpenTemplateModal" role="dialog">
    <div id="OpenTemplateModal12" class='modal-dialog modal-xl' style="min-width:98vw" >      
      <div class="modal-content">
        <div class="modal-header text-center">
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        </div>
        <div class="modal-body">
       		 <div class="text-center">
       			<button type="button" class="btn btn-warning" onclick="$('#OpenTemplateModal').modal('hide');$('#OpenTemplateModalFrameId').remove();">Cancel</button>
				
			 </div> 
        </div>  
      </div> 
    </div>
  </div>  
  
  
   <!-- Open e-Consultancy -->
   <div class="modal fade" id="OpeneConsultancyModal" role="dialog">
    <div id="OpeneConsultancyModal12" class='modal-dialog modal-lg'>       Modal content
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        </div>
        <div class="modal-body">
       		 <div class="text-center">
       			<button type="button" class="btn btn-warning" onclick="$('#OpeneConsultancyModal').modal('hide');$('#OpeneConsultancyModalFrameId').remove();">Cancel</button>
				<!-- x -->
				<!-- <button type="button" class="btn btn-info opdTemplatePres" onclick="saveHtmlData()">Save</button> -->
			 </div> 
        </div>  
      </div> 
    </div>
  </div>  
  
    <div class="modal fade" id="printComplaintModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Print Complaints</h4>
        </div>
        <div class="modal-body" id="printComplaintModalDivId">
          <!-- <p>Some text in the modal.</p> -->
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
  
  <div class="modal fade" id="printHistoryModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Print History</h4>
        </div>
        <div class="modal-body" id="printHistoryModalDivId">
          <!-- <p>Some text in the modal.</p> -->
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
  <div class="modal fade" id="printExaminationModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Print Examination</h4>
        </div>
        <div class="modal-body" id="printExaminationModalDivId">
          <!-- <p>Some text in the modal.</p> -->
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
  <div class="modal fade" id="printQuestionareModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Print Questionnaire</h4>
        </div>
        <div class="modal-body" id="printQuestionareModalDivId">
          <!-- <p>Some text in the modal.</p> -->
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
  
  <div class="modal fade" id="OpenComplaintsTemplateModal" role="dialog">
    <div id="OpenComplaintsTemplateModal12" class='modal-dialog modal-lg'>       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="text-align: center;font-size: 25px;">Complaint
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        </div>
        <div class="modal-body">
       		 <div class="text-center">
       			<button type="button" class="btn btn-warning" onclick="$('#OpenComplaintsTemplateModal').modal('hide');$('#OpenComplaintsTemplateModalFrameId').remove();">Cancel</button>
				
				<!-- <button type="button" class="btn btn-info printPrescSAveBtn" onclick="">Save</button> -->
			 </div> 
        </div>  
      </div> 
    </div>
  </div> 
  
  
  
  
  <div class="modal fade" id="AdmissionAdviceTemplateModal" role="dialog">
    <div id="OpenComplaintsTemplateModal12" style="width: 90%" class='modal-dialog modal-lg'>       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="text-align: center;font-size: 25px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        </div>
        <div class="modal-body">
       		 <div class="text-center">
       			<button type="button" class="btn btn-warning" onclick="$('#AdmissionAdviceTemplateModal').modal('hide');$('#AdmissionAdviceTemplateModalFrameId').remove();">Cancel</button>
				
				<!-- <button type="button" class="btn btn-info printPrescSAveBtn" onclick="">Save</button> -->
			 </div> 
        </div>  
      </div> 
    </div>
  </div> 
  
  
  
  <div class="modal fade" id="OpenHistoryTemplateModal" role="dialog">
    <div id="OpenHistoryTemplateModal12" class='modal-dialog modal-lg'>       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="text-align: center;font-size: 25px;">History
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        </div>
        <div class="modal-body">
       		 <div class="text-center">
       			<button type="button" class="btn btn-warning" onclick="$('#OpenHistoryTemplateModal').modal('hide');$('#OpenHistoryTemplateModalFrameId').remove();">Cancel</button>
				
				<!-- <button type="button" class="btn btn-info printPrescSAveBtn" onclick="">Save</button> -->
			 </div> 
        </div>  
      </div> 
    </div>
  </div> 
  
  
  
  <div class="modal fade" id="OpenQuestionnaireTemplateModal" role="dialog">
    <div id="OpenQuestionnaireTemplateModal12" class='modal-dialog modal-lg'>       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="text-align: center;font-size: 25px;">Questionnaire
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        </div>
        <div class="modal-body">
       		 <div class="text-center">
       			<button type="button" class="btn btn-warning" onclick="$('#OpenQuestionnaireTemplateModal').modal('hide');$('#OpenQuestionnaireTemplateModalFrameId').remove();">Cancel</button>
				
				<!-- <button type="button" class="btn btn-info printPrescSAveBtn" onclick="">Save</button> -->
			 </div> 
        </div>  
      </div> 
    </div>
  </div> 
  
  
  
  <div class="modal fade" id="OpenExaminationTemplateModal" role="dialog">
    <div id="OpenExaminationTemplateModal12" class='modal-dialog modal-lg'>       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="text-align: center;font-size: 25px;">Examination
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        </div>
        <div class="modal-body">
       		 <div class="text-center">
       			<button type="button" class="btn btn-warning" onclick="$('#OpenExaminationTemplateModal').modal('hide');$('#OpenExaminationTemplateModalFrameId').remove();">Cancel</button>
				
				<!-- <button type="button" class="btn btn-info printPrescSAveBtn" onclick="">Save</button> -->
			 </div> 
        </div>  
      </div> 
    </div>
  </div> 
  
  
    <!-- Modal -->
  <div class="modal fade" id="drugAdvicesInstructionsModal" role="dialog">
    <div class="modal-dialog">       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title">Drug Advice Instructions</h2>
        </div>
        <div class="modal-body" style="text-align: left;">
           <p class="text-center">
             Instructions of doctor will append here.
           </p>
        </div>  
      </div> 
    </div>
  </div> 
  
    <!-- Modal -->
  <div class="modal fade" id="investigationLabReportModal" role="dialog" >
    <div class="modal-dialog modal-lg">       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title text-left">Lab Report Analyzer</h2>
        </div>
        <div class="modal-body" style="text-align: left;"> 
			 <div class="row" style="min-height:300px;">
			    <div  class="col-sm-12">
			        <!-- <h3>Reports</h3>
			        <hr/> -->
			        <div class="col-xs-5 col-sm-2 col-md-3"> 
			            <ul class="nav nav-tabs tabs-left investigationModalNavMenu">
			                <li class="active"><a href="#home12" data-toggle="tab">My Reports</a></li> 
			            </ul>
			        </div>
			        <div class="col-xs-7 col-sm-10 col-md-9">
			            <!-- Tab panes -->
			            <div class="tab-content investigationModalNavMenuContent">
			                <div class="tab-pane active" id="home12">
								<!-- <h4 class="text-left">VIEW & DOWNLOAD LAB REPORTS</h4> -->
								<ul class="patInvestigationTestList" >
								</ul>
			                </div> 
			            </div>
			        </div>
			        <div class="clearfix"></div>
			    </div>  
			</div> 
           <!-- <object id="investigationLabReportPdf1" data="data:application/pdf;base64," type="application/pdf" style="width:100%;height:500px;"></object> -->
        </div>  
      </div> 
    </div>
  </div>
   
  <button id="patPrescViewModalTriggerBtn" type="button" style="display:none" data-toggle="modal" data-target="#patPrescViewModal"></button>
  <!-- Modal -->
<!--   <div class="modal fade" id="patPrescViewModal" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document">       Modal content
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2 class="modal-title">Patient Prescription View</h2>
        </div>
        <div class="modal-body" style="text-align: left; height:80vh">
           <img src="/HISDRDESK/new_opd/img/pat_presc.jpg" style="width: 90%; margin: 0 5%" />
            <div id="galley">
              <ul class="pictures">
                <li><img data-original="/HISDRDESK/new_opd/img/pat_presc.jpg" src="/HISDRDESK/new_opd/img/pat_presc.jpg" alt="Cuo Na Lake"></li>
                <li><img data-original="/HISDRDESK/new_opd/img/pat_presc.jpg" src="/HISDRDESK/new_opd/img/pat_presc.jpg" alt="Cuo Na Lake"></li>
                <li><img data-original="/HISDRDESK/new_opd/img/pat_presc.jpg" src="/HISDRDESK/new_opd/img/pat_presc.jpg" alt="Cuo Na Lake"></li> 
              </ul>
            </div>
           
        </div>  
      </div> 
    </div>
  </div> -->
  
    <!--  <div class="modal fade" id="patPrescViewModal" role="dialog" aria-labelledby="modalLabel" tabindex="-1">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modalLabel">Patient Prescription View</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body" style=" height:80vh">
            <div id="galley">
              <ul class="pictures">
                <li><img data-original="/HISDRDESK/new_opd/img/pat_presc.jpg" src="/HISDRDESK/new_opd/img/pat_presc.jpg" alt="Cuo Na Lake"></li>
                <li><img data-original="/HISDRDESK/new_opd/img/pat_presc.jpg" src="/HISDRDESK/new_opd/img/pat_presc.jpg" alt="Cuo Na Lake"></li>
                <li><img data-original="/HISDRDESK/new_opd/img/pat_presc.jpg" src="/HISDRDESK/new_opd/img/pat_presc.jpg" alt="Cuo Na Lake"></li>
              </ul>
            </div>
          </div> 
        </div>
      </div>
    </div>  --> 
    
    <div class="modal fade" id="patPrescViewModal" role="dialog" aria-labelledby="modalLabel" tabindex="-1">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modalLabel">Patient Prescription View</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body" style=" height:80vh">
            <div id="galley">
              <ul class="pictures">
                <li><img data-original="/HIS/hisglobal/drDeskAssets/img/pat_presc.jpg" src="/HIS/hisglobal/drDeskAssets/img/pat_presc.jpg" alt="Cuo Na Lake"></li>
              </ul>
            </div>
          </div> 
        </div>
      </div>
    </div> 
    
    <!-- Prescription Code Starts here -->
    
        <h2 style="text-align: left; margin-bottom: 0; margin-top:0">Rx Prescription 
        	<b style="font-weight: normal;font-size: 16px;font-family: courier;">[</b>
        	<b id="patDeptName" style="font-weight: normal;font-size: 16px;font-family: courier;"></b>
        	<b style="font-weight: normal;font-size: 16px;font-family: courier;">]</b>
        </h2>
        <h5 id="prescrightPanelHeaderPat" style="text-align: center; text-transform: uppercase; margin-right: 5px;line-height: 1em;margin-top: 0;letter-spacing: 3px;margin-bottom: 0;">
        	<font id="prevPatNamePrescriptionPanel" class="prevPatientName" style="vertical-align:super; color:#a4a4a4; display:none;"></font>
        	<a style="text-decoration: none; font-size: 3em; vertical-align: sub;font-weight: 300;margin-right: 3%;line-height: 1em;" class="prevPatientPresc">
        		<i class="fa fa-angle-double-left"></i>
        	</a>
        	<img src="/HISDRDESK/new_opd/img/patIcon.png" style="height: 3em;vertical-align: text-bottom;padding-right: 10px;">
        	<font id="patNamePrescriptionPanel" style="vertical-align: super; font-weight: bold;"> </font> 
        	<font style="vertical-align: super">(<font id="patGenAgeCatPrescriptionPanel"></font>) <b>CRN :</b></font>
        	<font id="patEpisodeCodePrescriptionPanel" style="display: none"></font>
        	<font id="patHospitalCodePrescriptionPanel" style="display: none"></font>
        	<font id="patSeatIdPrescriptionPanel" style="display: none"></font> 
        	<font id="patCrNoPrescriptionPanel" style="vertical-align: super;"></font>
        	<!-- <font style="vertical-align: super"><b>ADMNo :</b></font>
        	<font id="patAdmNoPrescriptionPanel" style="vertical-align: super;"></font>
        	<font style="vertical-align: super">(<font id="patRoomNoPrescriptionPanel"></font>)</font> -->
        	<a style="text-decoration: none; font-size: 3em; vertical-align: sub;font-weight: 300;margin-left: 3%;line-height: 1em;" class="nextPatientPresc">
        		<i class="fa fa-angle-double-right"></i>
        	</a>
        	<font id="nextPatNamePrescriptionPanel" class="nextPatientName" style="vertical-align:super; color:#a4a4a4;display:none;"></font>
        	<font id="patGaurdianNamePrescriptionPanel" style="display:none"></font><font id="patConsultantNamePrescriptionPanel" style="display:none"></font>
        </h5> 
        <h5 id="prescrightPanelHeaderPat" style="text-align: center; text-transform: uppercase; margin-right: 5px;line-height: 1em;margin-top: 0;letter-spacing: 3px;margin-bottom: 0;">
        	<font style="vertical-align: super"><b>ADMNo :</b></font>
        	<font id="patAdmNoPrescriptionPanel" style="vertical-align: super;"></font>
        	<font style="vertical-align: super">(<font id="patRoomNoPrescriptionPanel"></font>/<font id="patBedPrescriptionPanel"></font>/<font id="patWardPrescriptionPanel"></font>/<font id="patAdmDatePrescriptionPanel"></font>)</font>
        	</h5>
          <div class="clearfix visible-xs-block"></div>
          <!-- <p style="text-align: right; display: block;">Adm. No. <a style="text-decoration: none; cursor: auto;">151651656465463</a></p> -->
         <!--  <h3 class="text-right" style="margin-top: 0px !important;"><a style="text-decoration: none; " class="nextPatientPresc"><i class="fa fa-wheelchair"></i> next<i class="fa fa-angle-double-right"></i></a></h3> -->
          <!-- <p class="text-right"><button type="button" class="btn btn-primary btn-sm printAllPrescBtn" data-toggle="modal" data-target="#printAllModal">Print All</button></p>  -->
          <!-- <p class="text-right"><div style="width: 30vw; display: inline-block;"><input type="search" placeholder="Search tabs" class="form-control" name="prescTabSearchVal" onkeyup="prescTabSearch(this,event)"></div><button type="button" class="printAllPrescBtn" data-toggle="modal" data-target="#printAllModal">Print All</button></p> -->
           <!--<ul class="nav nav-tabs navDeskTabList">
            <li class="active" onclick="$(this).parent().find('li').attr('class','');$(this).attr('class','active');$('.tab-pane').attr('class','tab-pane fade'); $('.tab-content .tab-pane:first-child').attr('class','tab-pane fade in active'); $('.tab-content .tab-pane:first-child').show();"><a data-toggle="tab" href="#home">PRESCRIPTION</a></li>
            <li><a data-toggle="tab" href="#patreferal" onclick="openRefPage();">PATIENT REFERAL</a></li>
           	<li><a data-toggle="tab" href="#tab2">EXAMINATION</a></li>
            <li><a data-toggle="tab" href="#tab2">EXAMINATION</a></li>
            <li><a data-toggle="tab" href="#tab3">EPISODE ALERGIES</a></li>
            <li><a data-toggle="tab" href="#tab4">HISTORY</a></li>
            <li><a data-toggle="tab" href="#tab5">PATIENT CHRONIC DISEASE</a></li>
            <li><a data-toggle="tab" href="#tab6">SERVICE REQUISITION DETAILS</a></li>
            <li><a data-toggle="tab" href="#tab7">ADMISSION ADVICE</a></li>
            <li><a data-toggle="tab" href="#tab8">EXTERNAl EXAMINATION DETAIL</a></li>  
          </ul>-->

        <div class="tab-content">
          <div id="home" class="tab-pane fade in active">
            <div class="container-fluid prescriptionContainer" style="padding-left: 10px;padding-right: 10px;background-color: #eaeef3; ">
              <div class="row">
                <!-- <div class="col-xs-0 col-sm-0 col-md-1"></div> -->
                <div id="prescriptionTileContainer" class="col-sm-12 col-md-12" style="padding-left: 0px;padding-right: 10px;">
                 <p id="prescriptionPanelPatStatus" style="font-size: 22px; color: rgb(27, 191, 35);"></p>
                 
                 
                 
                 <div class="prescriptionTile">
                 	<p class="vitalsAdded"><b>Vitals & General Examination :</b> 
                    </p>
                    <div class="row"> 
                        <div class="col-sm-10 col-md-11 prescriptionTileRowFirstCol ">
							<p class="weight col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="weightpId"><i class="glyphicon glyphicon-scale"></i> Weight :  <span id="weightIdValue"  data-toggle="tooltip" ></span></p> 
                            <p class="height col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel"  id="heightpId"><i class="fa fa-male"></i> Height : <span id="heightIdValue"  data-toggle="tooltip" ></span></p> 
                            <p class="bmi col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel"  id="bmipId"><i class="fa fa-weight"></i> BMI : <span id="bmiIdValue" data-toggle="tooltip" ></span><span class="bmiIdErrMsg" style="display:none;">(Low Weight)</span></p> 
                            <p class="bloodPressure col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="bppid"><i class="fa fa-x-ray"></i> BP : <span id="bloodPressureIdValue" data-toggle="tooltip" ></span><span class="bloodPressureIdErrMsg" style="display:none;">(Hypertension Stage 1)</span></p> 
                            <p class="temperature col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="temppid"><i class="fas fa-thermometer"></i> Temp : <span id="temperatureIdValue"  data-toggle="tooltip" ></span><span class="temperatureIdErrMsg" style="display:none;">(High)</span></p> 
                            <p class="respRate col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="rrpid"><i class="fa fa-stethoscope"></i> RR : <span id="respRateIdValue"  data-toggle="tooltip" ></span><span class="temperatureIdErrMsg" style="display:none;">(High)</span></p> 
                            <p class="haemoglobin col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="hgbpid"><i class="fa fa-file-medical-alt"></i> Hgb : <span id="haemoglobinIdValue"  data-toggle="tooltip" ></span></p> 
                            <p class="fasting col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="bsfastpid"><i class="fa fa-cubes"></i> B.S.Fast :<span id="fastingIdValue"  data-toggle="tooltip" ></span></p> 
                            <p class="ppRate col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="bspppid"><i class="fa fa-cubes"></i> B.S. PP : <span id="ppRateIdValue"  data-toggle="tooltip" ></span></p>
                            <p class="hba1c col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="hba1cpid"><i class="fa fa-strikethrough"></i> HBA1C : <span id="hba1cIdValue"  data-toggle="tooltip" ></span></p>
                            <p class="hba1c col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="bloodgrouppid"><i class="glyphicon glyphicon-tint"></i> Blood Group : <span id="bloodgroupIdValue"  data-toggle="tooltip" ></span></p>
                            <!-- ----------------------added for cancer screening--------------- -->
                            <p class="cancerScreening col-sm-6 col-md-6 opdVitalsTile opdVitalsLabel" id="cancerscreeninggid"><i class="glyphicon glyphicon-tint"></i> Cancer Screening : <span id="cancerScreeningIdValue"  data-toggle="tooltip" ></span></p>  
                            <p class="hba1c col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="pulseratepid"><i class="fa fa-heartbeat" style="color:red"></i> Pulse Rate : <span id="pulserateIdValue"  data-toggle="tooltip" ></span></p>
                            <p class="hba1c col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="curcumferencepid"><i class="fa fa-circle-notch"></i> Circumference : <span id="curcumferenceIdValue"  data-toggle="tooltip" ></span></p>
                            <p class="hba1c col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="muacpid"><i class="fa fa-tape"></i> MUAC : <span id="muacIdvalue"  data-toggle="tooltip" ></span></p>                                                        
                            <p class="hba1c col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="Disabilityid"><i class="fab fa-accessible-icon"></i> Disability : <span id="DisabilityIdvalue"  data-toggle="tooltip" ></span></p>
                            <p class="hba1c col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="Smokingid"><i class="fa fa-smoking"></i> Smoking : <span id="SmokingIdvalue"  data-toggle="tooltip" ></span></p>
                            <p class="hba1c col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="Anemicid"><i class="fa fa-vial"></i> Anemic : <span id="AnemicIdvalue"  data-toggle="tooltip" ></span></p>
                          <!--  <p class="hba1c col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="Pregnancyid"><i class="material-icons">pregnant_woman</i> Pregnancy : <span id="PregnancyIdvalue"  data-toggle="tooltip" ></span></p>    -->                         
                            <p class="hba1c col-sm-3 col-md-3 opdVitalsTile opdVitalsLabel" id="Pregnancyid"><i class="fas fa-female"></i> Pregnancy : <span id="PregnancyIdvalue"  data-toggle="tooltip" ></span></p>                
                            <!-- <p class="pulse col-sm-3 col-md-3 opdVitalsTile"><u>Pulse</u> : <span data-toggle="tooltip" title="Good Pulse Rate" id="pulseIdValue"></span></p> -->                          
                            <p class="otherInfo col-sm-12 col-md-12 opdVitalsTile opdVitalsLabel" id="pidremarks"><i class="fa fa-comment-dots"></i> Interpretation/Remarks : <span id="otherInfoIdValue"></span></p>
                            <p class="otherInfo col-sm-12 col-md-12 opdVitalsTile opdVitalsLabel" id="patientcomplaintid"><i class="fa fa-phone-square"></i> Teleconsultancy Req Info : <span id="patientcomplaintidValue"></span></p>
                            <p class="otherInfo col-sm-12 col-md-12 opdVitalsTile opdVitalsLabel" id="RegReasonForVisitId" style="display: none;"><i class="fa fa-clinic-medical"></i> Reason for Visit : <span id="ReasonVisitFromRegistrationId"></span></p>
                             <!-- <p class="otherInfo col-sm-12 col-md-12 opdVitalsTile"><span id="ClassRangeIdValue" style="color: #6d6db7"></span></p> -->
                             <input type="hidden" id="vitalHiddenValId" name="vitalHiddenVal" value="">
                        </div>
                        
                        <!-- <div class="col-sm-2 col-md-1">
                              <div class="col-sm-12 col-md-12">
                        		<button type="button" id="opdEmrBtnId" class="btn btn-warning opdEmrBtn" style="font-size:11px;background-color:#30CFDB;border:0px" tabindex="0" data-toggle="tooltip" onclick="$('#opdEmrModal').modal('show');" title="OPD EMR">OPD EMR </button>
                        	</div>
                            <div class="col-sm-12 col-md-12" style="margin-top:5px;">
                            	<button type="button" id="treeStructurePrescBtnId" class="btn btn-warning treeStructurePrescBtn" style="font-size:11px;background-color:#DB305A;border:0px" tabindex="0" data-toggle="tooltip" onclick="$('#TreeStructurePrescriptionModal').modal('show');" title="Patient History">History</button>
                            </div>
                            <div class="col-sm-12 col-md-12" style="margin-top:5px;">
                            	<button type="button" id="vitalModify" class="btn btn-warning vitalModifyBtn" onclick="ModifyVital(this,event)" style="font-size:11px;background-color:#7C9CDE;border:0px" tabindex="0" data-toggle="tooltip" title="OPD EMR">Modify Vital</button>
                            </div>
                        </div> -->
                        <!-- <div class="col-sm-2 col-md-1" style="margin-top: 45px">
                            <button type="button" id="vitalModify" class="btn btn-warning vitalModifyBtn" onclick="ModifyVital(this,event)" style="font-size:11px;background-color:#DB7230;border:0px" tabindex="0" data-toggle="tooltip" title="OPD EMR">Modify Vital</button>
                        </div> -->
                     <div class="row">    
                        <div class="col-sm-5 col-md-5 prescriptionTileRowFirstCol ">
                        	 <button id="eteleconsultancyViewDocLinkClassID" disabled="disabled" class="btn btn-warning dropdown-toggle dropdown-toggle-split" type="button" style="display: none;" data-toggle="dropdown"><i class="glyphicon glyphicon-picture"></i> Uploads <span class="caret"></span></button>
									<ul class="dropdown-menu">
										<li><a class="eteleconsultancyViewDocLinkClass" href="#"><input type='hidden' value="1"><i class="glyphicon glyphicon-picture" style="color:grey"></i> Image (1)</a> </li>
										<li><a class="eteleconsultancyViewDocLinkClass" href="#"><input type='hidden' value="2"><i class="glyphicon glyphicon-picture" style="color:grey"></i> Image (2)</a> </li>
										<li><a class="eteleconsultancyViewDocLinkClass" href="#"><input type='hidden' value="3"><i class="glyphicon glyphicon-picture" style="color:grey"></i> Image (3)</a> </li>										
									</ul>
                        	<button type="button" id="opdEmrBtnId" class="btn btn-info opdEmrBtn AddToggleClass" tabindex="0" data-toggle="tooltip" onclick="$('#opdEmrModal').modal('show');" title="Past Rx">Past Rx </button>
                        	<button type="button" id="treeStructurePrescBtnId" class="btn btn-info treeStructurePrescBtn AddToggleClass" tabindex="0" data-toggle="tooltip" onclick="$('#TreeStructurePrescriptionModal').modal('show');" title="Patient OPD EMR">EMR</button>
                            <button type="button" id="vitalModify" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="ModifyVital(this,event)" tabindex="0" data-toggle="tooltip" title="Modify Vital">Modify Vital/GE</button>
                           
							                 
                        <!--    <button class="btn btn-info dropdown-toggle AddToggleClass" id="prescViewVisiBtnId" type="button" data-toggle="dropdown" disabled="disabled" tabindex="0"><i class="glyphicon glyphicon-align-justify"></i>Scanned<!-- style="padding:6px 5px;font-size:11px;background-color: #4985ff;border:0px" <span class="caret"></span></button>   -->                                                                                                                                                
                          <!--   
                            <select class="eteleconsultancyViewDocclass" style="" name="eteleconsultancyViewDoc" id="eteleconsultancyViewDocId" autocomplete="off">
                            <option value="" disabled selected>Scanned Images</option>
                            <option value="1">Image 1</option>
                            <option value="2">Image 2</option>
                            <option value="3">Image 3</option>
                            </select>
                         -->																							
						<!-- <button type="button"  class="btn btn-info  AddToggleClass"   data-toggle="modal" data-target="#bookmarkmodal">BookMark</button> -->                          
                        <!-- <button style="display: none;" type="button" id="vitalModify" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="OpenTemplate(this)" value="49" tabindex="0" data-toggle="tooltip" title="Click to Open Template">Questionaires Template</button> -->                         					
                       </div>
                        
                      <div class="col-sm-7 col-md-5 prescriptionTileRowsCol" style="margin-top:5px;">                    
                        <div class="col-sm-10 col-md-10">                          
                        	<input type='text'placeholder='Search Bookmark and Refill Rx' class="form-control flexdatalist flexdatalist-set" autocomplete="off" tabindex="-1" data-min-length='1' list='languages' 	name='SearchPara' id="SearchParaIs"    >
      					</div>
      					 
         <%  JSONArray PrescriptionBookMarkJsonArray1 = (JSONArray) request.getSession().getAttribute("PrescriptionBookMarkJsonArray");
         if(PrescriptionBookMarkJsonArray1 !=null){
         %>           
         <script> 
                      $(document).ready(function(){
                          <%  JSONArray PrescriptionBookMarkJsonArray = (JSONArray) request.getSession().getAttribute("PrescriptionBookMarkJsonArray");
                          
                          //System.out.println("PrescriptionBookMarkJsonArray"+PrescriptionBookMarkJsonArray);
                          %> 
                          try{ 
                            var tempJSON 
                            = <%=PrescriptionBookMarkJsonArray.toString() %>;
						    var testJsonObj = tempJSON; 
						    //console.log(tempJSON);   
							if(!localStorage.getItem('PrescriptionBookMarkJsonArrayObj'))
								localStorage.setItem('PrescriptionBookMarkJsonArrayObj',JSON.stringify(tempJSON).toString()); 
							else if(JSON.stringify(tempJSON).toString() != localStorage.getItem('PrescriptionBookMarkJsonArrayObj').toString() )
								{
									console.log('localStorage else');
									localStorage.setItem('PrescriptionBookMarkJsonArrayObj',JSON.stringify(tempJSON));  
								} 
							$('input[name=SearchPara]').flexdatalist({
							     minLength: 1,
							     focusFirstResult: true,
							     searchByWord: true,
							     maxShownResults: 50,
							     searchIn: 'SERACH_DATA', 
							     data: tempJSON
							 });  
                              }
                          catch(err){
                              console.log('err.message:>>>'+err.message);
                              }
                         }); 
                      </script>
       <%} %>
       			<div class="col-md-2 col-sm-2">
                          <div class="dropdown AddToggleClass" style="float:left;margin-left: 31px;">                                                                                  
                            <ul class="dropdown-menu prescViewVisitLst" role="menu" aria-labelledby="prescViewVisiBtnId">
                             <!--  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Presc1</a></li>    -->
                            </ul>
                            <button type="button"  class="btn btn-info rxprofilebtn" data-toggle="tooltip" title="Manage Bookmark"   data-toggle="modal" ><i class="glyphicon glyphicon-bookmark"></i></button>
                          </div>    
                          
                          </div>
                          <script>
                          $(document).ready(function(){
                              $(".dropdown-toggle").dropdown();
                             
                          });
                          </script>
                          <br><br>
                        <!-- <p><a class="clearLnk" style="color: rgba(35, 104, 194, 0.8)" title="Clear checked items"><img src="img/clear.png" style="width: 2.2em; height: 2.2em; min-width: 2em;"></a></p> -->
                        
                    </div>
                     </div>
                 </div></div>
                 <div id="bookmarkmodal" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Do you want to bookmark this Rx profile ?</h4>
      </div>
      <div class="modal-body">
     
       <div class="col-md-12">
      <!--  <label>Enter Book Mark Name</label> -->
       <input type="text" class="form-control" maxlength="1000" id="PresCriptionBookmarkNameId" placeholder="Enter BookMark Name" name="bookmarkName">
     	<br> 
       <textarea class="form-control" name="PresCriptionBookmarkDesc" placeholder="Enter Bookmark Description" id="PresCriptionBookmarkDescId" ></textarea>
       <br>
       <!-- <input type="checkbox" style="display: none;" name="deptonly" id="deptonlyid" value="1"><label style="display: none;">Restricted Access</label>
       <input type="checkbox" style="" name="AllDept" id="ALlDeptId" value="1">Accessible to Other Within Department
      -->
     
    			<input type="checkbox" id="ALlDeptId" class=" " name="AllDept" value="1"  checked="checked"/> <label> Accessible Within Department </label>
               <!--  <input type="checkbox" id="ALlDeptId" class=" checkboxcls" name="AllDept" value="2" /> <label> Department Access </label>
                <input type="checkbox" id="ALlDeptId" class=" checkboxcls" name="AllDept" value="3" /><label> All  </label>
                 -->
     
       </div>
       <br>
        <div class="row">
       
       </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="">Close</button> 
       <!--  <button type="button" class="btn btn-primary" onclick="Save('printSave',this , 0)">Skip</button> -->
        <button type="button" class="btn btn-success" onclick="SaveRxProfileData('printSave',this , 1)">Save bookmark & Preview Rx</button>
      </div>
    </div>

  </div>
</div>

 <div id="PresCriptionModal" class="modal fade" role="dialog">

  <div class="modal-dialog  modal-md">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Manage Your Bookmarks</h4>
      </div>
      <div class="modal-body">
      
       <table class="table table-bordered" id="example12">
          <thead>
            <tr>
              <th style="background-color:#ddd;color:black">#</th>
              <th style="background-color:#ddd;color:black">Name </th>
              <th style="background-color:#ddd;color:black">Description</th>
              <th style="background-color:#ddd;color:black">Created By </th>
              <th style="background-color:#ddd;color:black">Action </th>
            </tr>
          </thead>
          <tbody>
          
          <%
           /*  if(false){   */
       ArrayList<JSONObject> strPrescriptionProfileJSON = (ArrayList<JSONObject>) request.getSession().getAttribute("PrescriptionBookMark");
     	//System.out.println("strPrescriptionProfileJSON:::  "+strPrescriptionProfileJSON.toString());
       if(strPrescriptionProfileJSON !=null){
     		if(strPrescriptionProfileJSON.size() > 0){
     			for(int ii=0 ; ii< strPrescriptionProfileJSON.size();ii++){
     	//			JSONObject js=new JSONObject(strPrescriptionProfileJSON.get(i));
     				//JSONObject js1=new JSONObject(js.get("HJOSN_RX_COMPLETE"));
     				StringBuffer sb =new StringBuffer();
				System.out.println("");     				

     				sb.append("<lable style=' font-weight: bold; color:#3ba4e3;'> Chief Complaint: ");
     				sb.append("</lable>");
     				
     				org.json.simple.JSONArray ReasonOfVisitJsonArray= (org.json.simple.JSONArray)((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("ReasonOfVisitJsonArray");
     				for(int ReasonOfVisitJsonArrayvar=0 ; ReasonOfVisitJsonArrayvar < ReasonOfVisitJsonArray.size(); ReasonOfVisitJsonArrayvar++ ){
     					sb.append(new JSONObject( ReasonOfVisitJsonArray.get(ReasonOfVisitJsonArrayvar).toString()).getString("VisitReasonName"));
						sb.append(", ");
     					//System.out.println("ReasonOfVisitJsonArray"+new JSONObject( ReasonOfVisitJsonArray.get(ReasonOfVisitJsonArrayvar).toString()).getString("VisitReasonName"));
     				}
     				sb.append("<br>");
     				sb.append("<br><lable style=' font-weight: bold; color:#3ba4e3;'> Diagnosis: ");
     				sb.append("</lable>");
     				org.json.simple.JSONArray DiagnosisJsonArray= (org.json.simple.JSONArray)((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("DiagnosisJsonArray");
     				for(int DiagnosisJsonArrayvar=0 ; DiagnosisJsonArrayvar < DiagnosisJsonArray.size(); DiagnosisJsonArrayvar++ ){
     					sb.append(new JSONObject( DiagnosisJsonArray.get(DiagnosisJsonArrayvar).toString()).getString("DiagnosisName"));
						sb.append(", ");
     					System.out.println("DiagnosisJsonArray"+new JSONObject( DiagnosisJsonArray.get(DiagnosisJsonArrayvar).toString()).getString("DiagnosisName"));
     				}
     				sb.append("<br>");
     				org.json.simple.JSONArray invartiagtionJsonArray= (org.json.simple.JSONArray)((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("InvestigationJsonArray");
     				sb.append("<br><lable style=' font-weight: bold; color:#3ba4e3;'> Investigations: ");
     				sb.append("</lable>");
 					for(int investigationJsonArray=0 ; investigationJsonArray < invartiagtionJsonArray.size(); investigationJsonArray++ ){
     					System.out.println("investigationJsonArray"+new JSONObject( invartiagtionJsonArray.get(investigationJsonArray).toString()).getString("TestName"));
     				sb.append(new JSONObject( invartiagtionJsonArray.get(investigationJsonArray).toString()).getString("TestName") +"  ");
					sb.append(", ");
 					}
 					sb.append("<br>");
 					sb.append("<br><lable style=' font-weight: bold; color:#3ba4e3;'> Drugs: ");
     				sb.append("</lable>");
 					org.json.simple.JSONArray DrugJsonArray= (org.json.simple.JSONArray)((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("DrugJsonArray");
     				for(int DrugJsonArrayvar=0 ; DrugJsonArrayvar < DrugJsonArray.size(); DrugJsonArrayvar++ ){
     					sb.append(new JSONObject( DrugJsonArray.get(DrugJsonArrayvar).toString()).getString("DrugName"));
						sb.append(", ");
     					System.out.println("DrugJsonArray"+new JSONObject( DrugJsonArray.get(DrugJsonArrayvar).toString()).getString("DrugName"));
     				}
     				sb.append("<br>");
     				sb.append("<br><lable style=' font-weight: bold; color:#3ba4e3;'> Clinical Procedure: ");
     				sb.append("</lable>");
 					org.json.simple.JSONArray ClinicalProcedureJsonArray= (org.json.simple.JSONArray)((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("ClinicalProcedureJsonArray");
     				for(int ClinicalProcedureJsonArrayvar=0 ; ClinicalProcedureJsonArrayvar < ClinicalProcedureJsonArray.size(); ClinicalProcedureJsonArrayvar++ ){
     					sb.append(new JSONObject( ClinicalProcedureJsonArray.get(ClinicalProcedureJsonArrayvar).toString()).getString("ProceduresName"));
						sb.append(", ");
     					//System.out.println("ProceduresName"+new JSONObject( DrugJsonArray.get(ClinicalProcedureJsonArrayvar).toString()).getString("ProceduresName"));
     				}
     				sb.append("<br>");
     				sb.append("<br><lable style=' font-weight: bold; color:#3ba4e3;'> Investigation Notes: ");
     				sb.append("</lable>");
     				if(((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("InvestgationNote") !=null){
     					sb.append(((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("InvestgationNote"));
     				}
					sb.append("<br>");
     				sb.append("<br><lable style=' font-weight: bold; color:#3ba4e3;'> Treatment Advice Note: ");
     				sb.append("</lable>");
     				if(((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("strtreatmentAdvice") !=null){
     					sb.append(((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("strtreatmentAdvice"));
     				}
     					 System.out.println("1234567890::::::: \n"+((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("FOLLOW_UP"));
     					JSONArray js=new JSONArray(((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("FOLLOW_UP").toString());
     					System.out.println("1234567891:::::::"+((JSONObject)js.get(0)).getString("progressNote"));
     				
     					sb.append("<br>");
         				sb.append("<br><lable style=' font-weight: bold; color:#3ba4e3;'> Progress Note: ");
         				sb.append("</lable>");
         				if(((JSONObject)js.get(0)).getString("progressNote") !=null){
         					sb.append(((JSONObject)js.get(0)).getString("progressNote"));
         				}
     								
     				
     				     				%>
            <tr>
              <td><img alt="" src="../../hisglobal/images/details_close.png" style="display: none;" class="dtHide">
				  <img src="../../hisglobal/images/details_open.png" class="dtShow" />
             <!--  <button type="button" class="dtHide" ><i class="fas fa-minus"></i></button> -->
              </td>
             
              <td><%= ((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("strPresCriptionBookmarkNameval")  %></td>
              <td><%= ((JSONObject)((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HJOSN_RX_COMPLETE")).get("strPresCriptionBookmarkDescVal")  %></td>
              <td><%= (String) (((JSONObject)strPrescriptionProfileJSON.get(ii)).get("USER_NAME"))  %></td>
             
             <%
     				String PREOFILE_ACCESS =(String) (((JSONObject)strPrescriptionProfileJSON.get(ii)).get("PREOFILE_ACCESS"));
     				String action =(String) (((JSONObject)strPrescriptionProfileJSON.get(ii)).get("GNUM_ISVALID"));
     				if(PREOFILE_ACCESS.equalsIgnoreCase("1")){
     				if(action.equalsIgnoreCase("Active")){
     					%>
     					
              <td>
			  			<input type="radio" name="strPrescriptionAction" onclick="ActionRequiredForProfilePrescription(<%=(((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HRGNUM_PROFILE_ID"))%> , 3)" value=<%=(String) (((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HRGNUM_PROFILE_ID")) %> > Delete 
     					<input type="radio" name="strPrescriptionAction" onclick="ActionRequiredForProfilePrescription(<%=(((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HRGNUM_PROFILE_ID"))%> , 0)" value=<%=(String) (((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HRGNUM_PROFILE_ID")) %> > Disable
			  </td>
			  <%
     					} else{
     				%>
            <td>
            <input type="radio" name="strPrescriptionAction" onclick="ActionRequiredForProfilePrescription(<%=(((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HRGNUM_PROFILE_ID"))%> , 1)" value=<%=(String) (((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HRGNUM_PROFILE_ID")) %> >Active
     				<input type="radio" name="strPrescriptionAction" onclick="ActionRequiredForProfilePrescription(<%=(((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HRGNUM_PROFILE_ID"))%> , 3)" value=<%=(String) (((JSONObject)strPrescriptionProfileJSON.get(ii)).get("HRGNUM_PROFILE_ID")) %> >Delete
            
            </td>
            
            
     				<%
     				}
     			} else {	
     				%> 
     				<td>
     				</td>
     				<%} %>
     				
     				
            </tr>
			<tr  style="display:none" >
			<td style="display:none" >  <input type="button"></td>
			 <td style="display:none"  >  TEST</td>
			<td  style="display:none" >  DD</td>
			<td colspan="5"  >  <%=sb.toString() %></td>
			<td style="display:none">
			  			<input type="radio" >
     					<input type="radio">
			  </td>
			
			</tr>
            
            <% 
            sb.delete(0, sb.length());
     			}
     		}
     	}
      /*   }  */
            %>
                  </tbody>
        </table>
<script>
$(document).ready(function() {
    $('#example12').DataTable({

    		order: [[0, "asc"]],
    		   bSort: false
    }
    	    );

    
    $("#example12").on("click", ".dtShow", function(){
    	console.log('Data table click event show');
		$(this).parent().parent().next().css("display","");
		$(this).parent().find(".dtHide").css("display","");
		$(this).parent().find(".dtShow").css("display","none");
    	});
    
    
    $("#example12").on("click", ".dtHide", function(){
    	console.log('Data table click event show');
    	$(this).parent().parent().next().css("display","none");
		$(this).parent().find(".dtShow").css("display","");
		$(this).parent().find(".dtHide").css("display","none");
    	});
    
   /*  $(".dtShow").on("click",function(){
    	
        		console.log('Data table click event show');
        		$(this).parent().parent().next().css("display","");
        		$(this).parent().find(".dtHide").css("display","");
        		$(this).parent().find(".dtShow").css("display","none");
        		
        }); */

   /*  $(".dtHide").on("click",function(){
    	
		$(this).parent().parent().next().css("display","none");
		$(this).parent().find(".dtShow").css("display","");
		$(this).parent().find(".dtHide").css("display","none");
});  */
} );
</script>     
      
      	<!-- <div><a href="0"> Malerila</a></div>
		<div><a href="0"> Malerila</a></div>
		<div><a href="0"> Malerila</a></div>
		<div><a href="0"> Malerila</a></div>
		<div><a href="0"> Malerila</a></div> -->
		      
      </div>
	  
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="">Close</button>
      </div>
    </div>

  </div>
</div>
                 
                 
                  <div class="prescriptionTile" style="/* padding: 1% 3% 3% 3%; */">
                    <!-- <p class="text-right"><button type="button" class="btn btn-primary btn-sm savePrintPrescBtn">Save/Print</button></p> 
                    <br> -->
                    <div class="row">
                      <div class="col-sm-10 col-md-11 prescriptionTileRowFirstCol">
                        <p class="reasonOfVisitAdded" onclick=" $('#chiefComplainTile').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus').css({'color': '#2196F3',});"><i class="fa fa-plus" style="color:#2196F3 "></i><b>&nbsp;Chief Complaint :</b> 
                          <a style="text-decoration: none;" class="clearLnk">
                            <img style="width: 22px;" alt="" src="/HIS/hisglobal/drDeskAssets/img/clear3.png">
                          </a>
                          <button class="btn btn-xs btn-danger clearAllValues" type="button" data-toggle="tooltip" title="Clear All" onclick="$(this).parent().find('label').remove();" style="background-color: white;border: 0px;"><span class="glyphicon glyphicon-trash" style="color: red;"></span></button>
                          <!-- <button class="value btn btn-xs moreToggleVisitReason clearAllValues" type="button" data-toggle="tooltip" title="Clear All" ></button> 
                          <button tabindex="0" style="padding-left: 5px;font-weight:700;white-space: normal;" type="button" class="value btn btn-xs moreToggleVisitReason tooltipcss" title="Side" data-toggle="tooltip"></button> -->
                        <!--<a><label><input type="checkbox" class="checkedInput" name="visitReason" checked> Fever</label></a><a style="padding-left:5px"><label>
                        <input type="checkbox" class="checkedInput" name="visitReason" checked> Body Ache</label></a> -->
                        </p>
                      </div>
                      <div class="col-sm-2 col-md-1 prescriptionTileRowsCol">
                        
                      	<br><br>
                        <!-- <p><a class="clearLnk" style="color: rgba(35, 104, 194, 0.8)" title="Clear checked items"><img src="img/clear.png" style="width: 2.2em; height: 2.2em; min-width: 2em;"></a></p> -->
                      </div>
                      
                    </div>
                    <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                    
                    <div class="row chiefComplaintDiv" id="chiefComplainTile" style="display:none;">
                    	<div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      <div class="col-xs-14 col-sm-5 col-md-6 reasonOfVisitCol">
                        <div class="input-group">
                          <input type="text" placeholder="Chief Complaint" reasonofvisitcode="" class="form-control clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_VR" id="txt-snomed-ct-search_VR" autocomplete="on" onclick="load_UNIVD('VR','FINDING','450970008')" >   <!-- onclick="load_UNIVD('VR','','450970008')"  -->
                          <div class="input-group-btn">
                            <button class="btn btn-default reasonOfVisitCleanBtn" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                            
                            <!-- -->
							<!-- <div id="info" style="display:none;">
								<p id="info_start">Click on the microphone icon and begin speaking.</p>
								<p id="info_speak_now">Speak now.</p>
								<p id="info_no_speech">No speech was detected. You may need to adjust your
									<a href="//support.google.com/chrome/bin/answer.py?hl=en&amp;answer=1407892">microphone settings</a>.
								</p>
								<p id="info_no_microphone" style="display:none">
									No microphone was found. Ensure that a microphone is installed and that
									<a href="//support.google.com/chrome/bin/answer.py?hl=en&amp;answer=1407892">microphone settings</a> are configured correctly.
								</p>
								<p id="info_allow">Click the "Allow" button above to enable your microphone.</p>
								<p id="info_denied">Permission to use microphone was denied.</p>
								<p id="info_blocked">Permission to use microphone is blocked. To change, go to chrome://settings/contentExceptions#media-stream</p>
								<p id="info_upgrade">Web Speech API is not supported by this browser.
									Upgrade to <a href="//www.google.com/chrome">Chrome</a>
									version 25 or later.
								</p>
							</div>
							<button class="right" type="button" id="start_button" onclick="startButton(event)">
								<img id="start_img" src="/HISDRDESK/hisglobal/images/mic.gif" alt="Start" style="height:30px;width:30px;">
							</button>
							
							<div id="results">
							  <span id="interim_span" class="interim"></span>
							  <p>
							</div>
							<p>
							<div id="div_language" style="display:none;">
								<select id="select_language" onchange="updateCountry()"></select>
									&nbsp;&nbsp;
									<select id="select_dialect"></select>
							</div> -->
							<!-- -->
                            
                          </div>
                        </div>
                        <div class="concept" id="conceptdiv_1"></div>
                        <input type="hidden" name="targetId"> 
                      </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"  style="display: none;"></div>
                      <div class="col-xs-12 col-sm-1 col-md-1 optionalOrDiv" style="display: none;">
                        <p><b>OR</b></p>
                      </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"  style="display: none;"></div>
                      <div class="col-xs-12 col-sm-3 col-md-3"  style="display: none;">
                        <div class="input-group">
                          <input type="text" placeholder="Generic Complaint" name="generalComplaint" id="generalComplaintId" tabindex=-1 class="form-control">
                          <div class="input-group-btn">
                            <button tabindex=-1 class="btn btn-default extInvestigationCleanBtn" onclick="$('input[name=generalComplaint]').val('');" style="z-index:0;" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                            <!-- <button class="right" type="button" id="start_button_generic" onclick="startButton(event)">
								<img id="start_img_generic" src="/HISDRDESK/hisglobal/images/mic.gif" alt="Start" style="height:30px;width:30px;">
							</button> -->
                          </div>
                        </div>
                      </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      <div class="col-sm-1 col-md-1 paddingLeftRightZero siteDiv marginBottom">
                          <select class="form-control" name="chiefComplaintSite" id="chiefComplaintSiteId" autocomplete="off">
                            <option value="0">Side</option>
                            <option value="1">NR</option>
                            <option value="2">Left</option>
                            <option value="3">Right</option>
                            <option value="4">Bilateral</option>
                          </select>
                        </div>
                        <div class="col-xs-12 col-sm-3 col-md-2 marginBottom">
                          <div class="col-xs-4 col-sm-4 col-md-4 paddingRightZero chiefComplaintNoOfDays">
                            <input type="text" class="form-control" placeholder="No." name="chiefComplaintNoOfDays" id="chiefComplaintNoOfDaysId" onkeypress="return isNumber(event)">
                          </div>
                          <div class="col-xs-8 col-sm-8 col-md-8 alignLeftPaddingLeftZero chiefComplaintDuration">
                            <select class="form-control" name="chiefComplaintDuration"  id="chiefComplaintDurationId" autocomplete="off">
                            <!--  <option value="0">Duration</option> -->
                              <option value="1">Day/s</option>
                              <option value="2">Week/s</option>
                              <option value="3">Month/s</option>
                              <option value="4">Year/s</option>
                            </select>
                          </div>
                        </div>
                        <div class="col-sm-2 col-md-2">
                        <textarea class="form-control" name="reasonofvisitRemarks" id="reasonofvisitRemarksId" rows="1" style="height:34px;" placeholder="Remarks"></textarea>
                      </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      <div class="col-sm-1 col-md-1 prescriptionTileRowsCol">
                        <button class="btn btn-sm btn-info reasonOfVisitAdd" id="reasonOfVisitAddId" type="button">Add</button>
                      </div>
                    
                    </div>
                    <br>
                  
                    <div class="clearfix visible-xs" style="margin-top:10px;"></div>
                    <div class="row">
                        <div class="col-xs-12 col-md-3 col-sm-5">
                          <label for="hopi" style="color:rgba(75,75,75, 0.7);" onclick="$('#hopitile').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus').css({'color': '#2196F3',});">
                          <i class="fa fa-plus" style="color:#2196F3 "></i>&nbsp;<b>History Of Present Illness:</b></label>
                        </div>
                        </div>
                        <div class="row" id="hopitile" style="display:none;">
                        <div class="form-group col-xs-12 col-md-11 col-sm-12">
                          <textarea class="form-control" id="hopiId" name="hopi" maxlength="3000" ></textarea>
                          <div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#hopiId').val('');" class="btn btn-sm btn-default reasonOfVisitCleanBtn"><i class="glyphicon glyphicon-remove"></i></button>
							</div>
                        </div>
                        <!-- <div class="form-group col-xs-12 col-md-12 col-sm-12">
                          <div class="col-xs-3 col-md-1 col-sm-3">
                            <label for="hopi">Enter HOPI:</label>
                          </div>
                          <div class="col-xs-9 col-md-9 col-sm-9 alignLeftPaddingLeftZero">
                            <textarea class="form-control" id="hopiId" name="hopi" maxlength="2000"></textarea>
                          </div>
                        </div> -->
                        
                        <div class="col-xs-3 col-sm-1" style="padding-right: 0px; text-align: center;">
                          <button type="button" style="padding: 0px;color:#3696e9" class="btn btn-link progressNoteMacroBtn" id="hopiIdDiv" value="progressNoteMacroModal1Div" onclick="$('#progressNoteMacroModal1').modal('show');"> <!--  onclick="$('#progressNoteMacroModal').modal('show');" <i class="material-icons" style="font-size: 1.5em">message</i> --><i class="fa fa-comments" style="font-size: 1.5em"></i></button>
                        </div>
                    </div>
                   <!-- Code added by Timsi as suggested by Priyesh Sir   -->
                    <!-- <br>
                    <div class="clearfix visible-xs" style="margin-top:10px;"></div> -->
                    <div class="row">
                        <div class="col-xs-12 col-sm-10 col-md-11">
                          <button id="addCompleteHistoryBtn" style="margin: 5px;" class="btn btn-info AddToggleClass AddTogleclassBtn" type="button" onclick="if($('#systematicExaminationDiv').is(':visible')){$('#addSystematicExaminationBtn').click();}
                          else if($('#chronicDiseaseDiv').is(':visible')){$('#addChronicDiseaseBtn').click();}
                          $('#completeHistoryDiv').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus');"><i class="fa fa-plus"></i> Complete History</button>
                          <button id="addSystematicExaminationBtn" class="btn btn-info AddToggleClass AddTogleclassBtn" type="button" onclick="if($('#completeHistoryDiv').is(':visible')){$('#addCompleteHistoryBtn').click();}else if($('#chronicDiseaseDiv').is(':visible')){$('#addChronicDiseaseBtn').click();}$('#systematicExaminationDiv').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus');"><i class="fa fa-plus"></i> Examination</button>
                          <button id="addChronicDiseaseBtn" class="btn btn-info AddToggleClass" type="button" onclick="if($('#completeHistoryDiv').is(':visible')){$('#addCompleteHistoryBtn').click();}else if($('#systematicExaminationDiv').is(':visible')){$('#addSystematicExaminationBtn').click();}$('#chronicDiseaseDiv').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus');"><i class="fa fa-plus"></i> Chronic Disease</button>
                       
                       
                       <!--     <button type="button" id="complaintTemplateId" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="OpenComplaintTemplate(this,event)" tabindex="0" data-toggle="tooltip" title="Complaints">Complaints</button>
                            <button type="button" id="HistoryTemplateId" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="OpenHistoryTemplate(this,event)" tabindex="0" data-toggle="tooltip" title="History">History</button>
                            <button type="button" id="ExaminationTemplateId" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="OpenExaminationTemplate(this,event)" tabindex="0" data-toggle="tooltip" title="Examination">Examination</button>
                            <button type="button" id="QuestionnaireTemplateId" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="OpenQuestionnaireTemplate(this,event)" tabindex="0" data-toggle="tooltip" title="Questionnaire">Questionnaire</button>
                            
                            
                            <button type="button" id="PrintcomplaintTemplateId" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="getComplaintsTemplateHTMLString()" tabindex="0" data-toggle="tooltip" title="Print Complaints">Print Complaints</button>
                            <button type="button" id="PrintHistoryTemplateId" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="getHistoryTemplateHTMLString()" tabindex="0" data-toggle="tooltip" title="Print History">Print History</button>
                            <button type="button" id="PrintExaminationTemplateId" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="getExaminationTemplateHTMLString()" tabindex="0" data-toggle="tooltip" title="Print Examination">Print Examination</button>
                            <button type="button" id="PrintQuestionnaireTemplateId" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="getExaminationTemplateHTMLString()" tabindex="0" data-toggle="tooltip" title="Print Questionnaire">Print Questionnaire</button>
                            --> <button type="button" id="AdmissionAdviceTemplateId" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="OpenAdmissionadviceTemplate(this,event)" tabindex="0" data-toggle="tooltip" title="Admission Advice">Admission Advice</button>
                       <!--      <button type="button" id="strDyanmicHistoryTemplateId" value="46" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="OpenTemplate(this)" tabindex="0" data-toggle="tooltip" title="History Template">History Template</button>
                            <button type="button" id="strDyanmicExaminationTemplateId" value="47" class="btn btn-info vitalModifyBtn AddToggleClass" onclick="OpenTemplate(this)" tabindex="0" data-toggle="tooltip" title="Examination Template">Examination Template</button>
                         -->  
                         
           <!--
					<button type="button" id="vitalModify" class="btn:hover:focus btn-sm vitalModifyBtn AddToggleClass" onclick="OpenTemplate(this)" value="49" tabindex="0" data-toggle="tooltip" style="margin: 5px; float:right;color:#39f;font-weight: bold;border-radius: 50%;background-color: #f4f0f2" data-toggle="tooltip" title="Other Templates">OT</button>                     
                     
					 <button type="button" id="strDyanmicHistoryTemplateId" value="46" class="btn:hover:focus btn-sm vitalModifyBtn AddToggleClass" onclick="OpenTemplate(this)" tabindex="0" data-toggle="tooltip" style="margin: 5px; float:right;color:#39f;font-weight: bold;border-radius: 50%;background-color: #f4f0f2" data-toggle="tooltip" title="History Template">HT</button>
					 
					 <button type="button" id="strDyanmicExaminationTemplateId" value="47" class="btn:hover:focus btn-sm vitalModifyBtn AddToggleClass" onclick="OpenTemplate(this)" tabindex="0" data-toggle="tooltip" style="margin: 5px; float:right;color:#39f;font-weight: bold;border-radius: 50%;background-color: #f4f0f2" data-toggle="tooltip" title="Examination Template">ET</button>
					 
					  <button type="button" class="btn:hover:focus btn-sm " value="48" onclick='OpenTemplate(this)' style="margin: 5px; float:right;color:#39f;font-weight: bold;border-radius: 50%;background-color: #f4f0f2 " data-toggle="tooltip" title="Complaint Template">CT</button>                          
               -->  
                      <button type="button" id="vitalModify" class="btn:hover:focus btn-sm vitalModifyBtn AddToggleClass" onclick="OpenTemplate(this)" value="49" tabindex="0" data-toggle="tooltip" style="margin: 5px; float:right;color:#f5f7f9;font-weight: bold;border-radius: 50%;background-color: #ff0a84;" title="" data-original-title="Other Templates">OT</button>
                      <button type="button" id="strDyanmicHistoryTemplateId" value="46" class="btn:hover:focus btn-sm vitalModifyBtn AddToggleClass" onclick="OpenTemplate(this)" tabindex="0" data-toggle="tooltip" style="margin: 5px; float:right;color:#f8fbff;font-weight: bold;border-radius: 50%;background-color: #2361f7;" title="" data-original-title="History Template">HT</button>
                      <button type="button" id="strDyanmicExaminationTemplateId" value="47" class="btn:hover:focus btn-sm vitalModifyBtn AddToggleClass" onclick="OpenTemplate(this)" tabindex="0" data-toggle="tooltip" style="margin: 5px; float:right;color:#f5f7f9;font-weight: bold;border-radius: 50%;background-color: #ff7f00;" title="" data-original-title="Examination Template">ET</button>                                                      
                      <button type="button" class="btn:hover:focus btn-sm " value="48" onclick="OpenTemplate(this)" style="margin: 5px; float:right;color:#f5f7f9;font-weight: bold;border-radius: 50%;background-color: #0fd508; " data-toggle="tooltip" title="Complaint Template">CT</button>                          
                                                </div>
                       
                    </div>
                     
                    <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                    <div id="completeHistoryDiv" style="display:none;">
                        <br>
                        <div class="row">
                            <div class="col-xs-12 col-md-3 col-sm-5">
                              <label for="pastHistory" style="color:rgba(75,75,75, 0.7);"><b>Past History:</b></label>
                            </div>
                            <div class="form-group col-xs-12 col-md-11 col-sm-12">
                              <textarea class="form-control" id="pastHistoryId" name="pastHistory" maxlength="2000"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-3 col-sm-5">
                              <label for="personalHistory" style="color:rgba(75,75,75, 0.7);"><b>Personal History:</b></label>
                            </div>
                            <div class="form-group col-xs-12 col-md-11 col-sm-12">
                              <textarea class="form-control" id="personalHistoryId" name="personalHistory" maxlength="2000"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-3 col-sm-5">
                              <label for="familyHistory" style="color:rgba(75,75,75, 0.7);"><b>Family History:</b></label>
                            </div>
                            <div class="form-group col-xs-12 col-md-11 col-sm-12">
                              <textarea class="form-control" id="familyHistoryId" name="familyHistory" maxlength="2000"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-3 col-sm-5">
                              <label for="treatmentHistory" style="color:rgba(75,75,75, 0.7);"><b>Treatment History:</b></label>
                            </div>
                            <div class="form-group col-xs-12 col-md-11 col-sm-12">
                              <textarea class="form-control" id="treatmentHistoryId" name="treatmentHistory" maxlength="2000"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-3 col-sm-5">
                              <label for="surgicalHistory" style="color:rgba(75,75,75, 0.7);"><b>Surgical History:</b></label>
                            </div>
                            <div class="form-group col-xs-12 col-md-11 col-sm-12">
                              <textarea class="form-control" id="surgicalHistoryId" name="surgicalHistory" maxlength="2000"></textarea>
                            </div>
                        </div>
                        <!-- <div class="row">
                            <div class="form-group col-xs-12 col-md-12 col-sm-12">
                              <div class="col-xs-3 col-md-1 col-sm-3">
                                <label for="pastHistory"><b>Past History:</b></label>
                              </div>
                              <div class="col-xs-9 col-md-9 col-sm-9 alignLeftPaddingLeftZero">
                                <textarea class="form-control" id="pastHistoryId" name="pastHistory" maxlength="2000"></textarea>
                              </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12 col-md-12 col-sm-12">
                              <div class="col-xs-3 col-md-1 col-sm-3">
                                <label for="personalHistory"><b>Personal History:</b></label>
                              </div>
                              <div class="col-xs-9 col-md-9 col-sm-9 alignLeftPaddingLeftZero">
                                <textarea class="form-control" id="personalHistoryId" name="personalHistory" maxlength="2000"></textarea>
                              </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12 col-md-12 col-sm-12">
                              <div class="col-xs-3 col-md-1 col-sm-3">
                                <label for="familyHistory"><b>Family History:</b></label>
                              </div>
                              <div class="col-xs-9 col-md-9 col-sm-9 alignLeftPaddingLeftZero">
                                <textarea class="form-control" id="familyHistoryId" name="familyHistory" maxlength="2000"></textarea>
                              </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12 col-md-12 col-sm-12">
                              <div class="col-xs-3 col-md-1 col-sm-3">
                                <label for="treatmentHistory"><b>Treatment History:</b></label>
                              </div>
                              <div class="col-xs-9 col-md-9 col-sm-9 alignLeftPaddingLeftZero">
                                <textarea class="form-control" id="treatmentHistoryId" name="treatmentHistory" maxlength="2000"></textarea>
                              </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12 col-md-12 col-sm-12">
                              <div class="col-xs-3 col-md-1 col-sm-3">
                                <label for="surgicalHistory"><b>Surgical History:</b></label>
                              </div>
                              <div class="col-xs-9 col-md-9 col-sm-9 alignLeftPaddingLeftZero">
                                <textarea class="form-control" id="surgicalHistoryId" name="surgicalHistory" maxlength="2000"></textarea>
                              </div>
                            </div>
                        </div> -->
                    </div>
                    
                    <div id="systematicExaminationDiv" style="display:none;">   
                        <br>
                        <div class="row">
                            <div class="col-xs-8 col-md-2 col-sm-2">
                              <input type="checkbox" name="pallor" id="pallorId">&nbsp<label for="Pallor" style="color:rgba(75,75,75, 0.7);"><b>Pallor</b></label>
                            </div>
                            
                             <div class="col-xs-8 col-md-2 col-sm-2">
                              <input type="checkbox" name="icterus" id="icterusId">&nbsp<label for="icterus" style="color:rgba(75,75,75, 0.7);"><b>Icterus</b></label>
                            </div>
                            
                             <div class="col-xs-8 col-md-2 col-sm-2">
                              <input type="checkbox" name="cyanosis" id="cyanosisId">&nbsp<label for="cyanosis" style="color:rgba(75,75,75, 0.7);"><b>Cyanosis</b></label>
                            </div>
                            
                             <div class="col-xs-8 col-md-2 col-sm-2">
                              <input type="checkbox" name="clubbing" id="clubbingId">&nbsp<label for="clubbing" style="color:rgba(75,75,75, 0.7);"><b>Clubbing</b></label>
                            </div>
                            
                             <div class="col-xs-8 col-md-2 col-sm-2">
                              <input type="checkbox" name="iymphadenopathy" id="iymphadenopathyId">&nbsp<label for="iymphadenopathy" style="color:rgba(75,75,75, 0.7);"><b>Iymphadenopathy</b></label>
                            </div>
                            
                             <div class="col-xs-8 col-md-2 col-sm-2">
                              <input  type="checkbox" name="edema" id="edemaID">&nbsp<label for="Pallor" style="color:rgba(75,75,75, 0.7);"><b>Edema</b></label>
                            </div>
                           
                        </div>
                        
                        <div class="row">
                            <div class="col-xs-12 col-md-3 col-sm-5">
                              <label for="cvs" style="color:rgba(75,75,75, 0.7);"><b>CVS:</b></label>
                            </div>
                            <div class="form-group col-xs-12 col-md-11 col-sm-12">
                               <textarea class="form-control" id="cvsId" name="cvs" maxlength="2000"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-3 col-sm-5">
                              <label for="rs" style="color:rgba(75,75,75, 0.7);"><b>RS:</b></label>
                            </div>
                            <div class="form-group col-xs-12 col-md-11 col-sm-12">
                              <textarea class="form-control" id="rsId" name="rs" maxlength="2000"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-3 col-sm-5">
                              <label for="cns" style="color:rgba(75,75,75, 0.7);"><b>CNS:</b></label>
                            </div>
                            <div class="form-group col-xs-12 col-md-11 col-sm-12">                              
                              <textarea class="form-control" id="cnsId" name="cns" maxlength="2000"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-md-3 col-sm-5">
                              <label for="pA" style="color:rgba(75,75,75, 0.7);"><b>P/A:</b></label>
                            </div>
                            <div class="form-group col-xs-12 col-md-11 col-sm-12">
                              <textarea class="form-control" id="pAId" name="pA" maxlength="2000"></textarea>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-xs-12 col-md-3 col-sm-5">
                              <label for="otherExamn" style="color:rgba(75,75,75, 0.7);"><b>General Examination:</b></label>
                            </div>
                            <div class="form-group col-xs-12 col-md-11 col-sm-12">
                              <textarea class="form-control" id="otherExamnId" name="otherExamn" maxlength="2000"></textarea>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-xs-12 col-md-3 col-sm-5">
                              <label for="otherExamn" style="color:rgba(75,75,75, 0.7);"><b>Muscular Examination:</b></label>
                            </div>
                            <div class="form-group col-xs-12 col-md-11 col-sm-12">
                              <textarea class="form-control" id="muscularExamnId" name="muscularExamn" maxlength="2000"></textarea>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col-xs-12 col-md-3 col-sm-5">
                              <label for="LocalExamn" style="color:rgba(75,75,75, 0.7);"><b>Local Examination:</b></label>
                            </div>
                            <div class="form-group col-xs-12 col-md-11 col-sm-12">
                              <textarea class="form-control" id="LocalExamnId" name="LocalExamn" maxlength="2000"></textarea>
                            </div>
                        </div>
                        
                        
                      
                    </div>

                    <div id="chronicDiseaseDiv" style="display:none;">
                    	<br>
                        <div class="table-responsive">
                          <table id="chronicDiseaseListTable" class="table table-condensed table-hover" style="margin-bottom: 0px;"> 
                            <thead>
                              <tr style="background: linear-gradient(to bottom, #dbdbdb /* #acd6ff */, white);">
                                <th><!-- <input type="checkbox" class="chronicDiseaseChkAll" name="chronicDiseaseChkAll"> --></th><th>Chronic Disease</th><th>Duration(yrs)</th><th>Remarks</th><th>Action</th>
                              </tr>
                            </thead>
                            <tbody>
                            <!-- <tr> <td><input type="checkbox" class="chronicDiseaseChkAll" name="chronicDiseaseChk" value="234422006"></td><td>Swedish Poryphyria</td><td>1</td><td><a class="chronicDiseaseInstructionsModalBtn" style="color: #109f1c" chronicDiseaseInstructions="hbdjhcd" onclick="$('#chronicDiseaseInstructionsModal .modal-body p').text($(this).attr('chronicDiseaseInstructions'));$('#chronicDiseaseInstructionsModal').modal('show');">hbdj..</a></td><td><button class="btn btn-sm btn-danger chronicDiseaseRemoveRow" onclick="$(this).parent().parent().remove();" id="removeBtnId2">Remove</button></td></tr></tbody> -->
                          </table>
                        </div>
                        <div class="row">
                         <!--  <br>
                          <div class="clearfix visible-xs" style="margin-top:5px;"></div> -->
                          <div class="col-xs-12 col-sm-6 col-md-8 chronicDiseaseCol">
                            <p style="letter-spacing:0"><label>Chronic Disease</label></p>
                            <div class="input-group">
                              <input type="text" placeholder="Chronic Disease" chronicDiseasecode="" class="form-control clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_VR3" id="txt-snomed-ct-search_VR3" onclick="load_UNIVD3('VR3','','450970008')" autocomplete="on">   <!-- onclick="load_UNIVD3('VR3','','450970008')" tabindex="1" -->
                              <div class="input-group-btn">
                                <button class="btn btn-default chronicDiseaseCleanBtn" onclick="$('input[name=txt-snomed-ct-search_VR3]').val('');" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                              </div>
                            </div>
                            <div class="concept" id="conceptdiv_3"></div>
                            <input type="hidden" name="targetId3"> 
                          </div>
                          <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                          <div class="col-xs-12 col-sm-2 col-md-1 marginBottom">
                            <p style="letter-spacing:0"><label>Duration(yrs)</label></p>
                            <input type="text" class="form-control" placeholder="No." name="chronicDiseaseNoOfYears" id="chronicDiseaseNoOfYearsId" onkeypress="return isNumber(event)">
                          </div>
                          
                          <div class="col-xs-12 col-sm-3 col-md-2 marginBottom">
                            <p style="letter-spacing:0"><label>Remarks</label></p>
                            <textarea class="form-control" name="chronicDiseaseRemarks" id="chronicDiseaseRemarksId" rows="1" style="height:34px;" placeholder="Remarks"></textarea>
                          </div>
                          <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                          <div class="col-sm-1 col-md-1 prescriptionTileRowsCol">
                            <p style="letter-spacing:0"><label></label></p>
                            <button class="btn btn-sm btn-info chronicDiseaseAddRows" id="chronicDiseaseAddRowsId" type="button">Add</button>
                          </div>
                        </div>
                        
                      </div>
                    
                    <!--  -->                    
                  </div>
              
                  
                  <div class="clearfix visible-xs"></div>
                  <div class="prescriptionTile">
                    <div class="row">
                      <div class="col-sm-10 col-md-11">
                        <p class="diagnosisAdded" ><b>Diagnosis :</b> <a style="text-decoration: none;" class="clearLnk"><img style="width: 22px;" alt="" src="/HIS/hisglobal/drDeskAssets/img/clear3.png"></a>
                          <button class="btn btn-xs btn-danger clearAllValues" type="button" data-toggle="tooltip" title="Clear All" onclick="$(this).parent().find('label').remove();" style="background-color: white;border: 0px;"><span class="glyphicon glyphicon-trash" style="color: red;"></span></button>  
                           <p style="display:block">
                          <label>ICD Code</label>
                          <label class="switch" style="vertical-align: middle;">
                             <input type="checkbox" name="diagnosisDiseaseReference" value="1" checked="">
                             <span class="slider round"></span> 
                          </label>
                          &nbsp;<label>SNOMED-CT</label>
                        </p> 
                      </div>
                      <div class="col-sm-2 col-md-1 prescriptionTileRowsCol">
                      <!--   <p><a class="clearLnk" style="color: rgba(35, 104, 194, 0.8)" title="Clear checked items"><img src="img/clear.png" style="width: 2.2em; height: 2.2em; min-width: 2em;"></a></p> -->
                      </div>
                    </div>   
                   
                    <div class="row snomedCtDiseaseView">
                   
                      <div class="col-sm-3 col-md-4 marginBottom">
                        <!-- <input type="text" placeholder="SNOMED-CT Diagnosis" class="form-control" name="diagnosis">  --> 
                        <div class="input-group">
                          <input type="text" style="z-index:0;" placeholder="Enter Diagnosis" diagnosiscode="" class="form-control clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_VR2" id="txt-snomed-ct-search_VR2" autocomplete="on" onclick="load_UNIVD2('VR2','DISORDER','')" tabindex="2">  
                          <div class="input-group-btn">
                            <button class="btn btn-default diagnosisCleanBtn" style="z-index:0;" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                          </div>
                        </div>
                         <div class="concept" id="conceptdiv_2">  </div>
                          <input type="hidden" name="targetId2"> 
                      </div>
                      <div class="col-sm-2 col-md-2 paddingLeftRightZero siteDiv marginBottom">
                          <select class="form-control" name="diagnosisSite" id="diagnosisSiteId" autocomplete="off">
                              <option value="0">Side</option>
                            <option value="1">NR</option>
                            <option value="2">Left</option>
                            <option value="3">Right</option>
                            <option value="4">Bilateral</option>
                          </select>
                        </div>
                        <div class="col-xs-12 col-sm-3 col-md-2 marginBottom" style="display: none;">
                          <div class="col-xs-4 col-sm-4 col-md-4 paddingRightZero chiefComplaintNoOfDays">
                            <input type="text" class="form-control" placeholder="No." name="diagnosisNoOfDays" onkeypress="return isNumber(event)">
                          </div>
                          <div class="col-xs-8 col-sm-8 col-md-8 alignLeftPaddingLeftZero chiefComplaintDuration marginBottom">
                            <select class="form-control" name="diagnosisDuration" id="diagnosisDurationId" autocomplete="off">
                              <option value="0">Duration</option>
                              <option value="1">Days</option>
                              <option value="2">Weeks</option>
                              <option value="3">Months</option>
                              <option value="4">Years</option>
                            </select>
                          </div>
                        </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      <div class="col-sm-2 col-md-2 marginBottom">
                        <select class="form-control" name="diagnosisType" tabindex="3">
                          <!-- <option value="0">Select Diagnosis Type</option> -->
                          <option value="11">Provisional</option>
                          <!-- <option value="13">Working</option> -->
                          <option value="12">Differential</option>
                          <option value="14">Final</option>
                        </select>
                      </div>
                      <div class="col-sm-3 col-md-3">
                        <textarea class="form-control" name="diagnosisRemarks" id="diagnosisRemarksId" rows="1" style="height:34px;" placeholder="Remarks"></textarea>
                      </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      <div class="col-sm-1 col-md-1 prescriptionTileRowsCol">
                        <button class="btn btn-sm btn-info diagnosisAdd" id="diagnosisAddId" type="button">Add</button>
                      </div>
                    </div>
                      <div class="row ICDCodeDiseaseView" id="dignosistileForIcd" style="display: none;">
                      
                     
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                     <div class="col-xs-6 col-sm-2 col-md-2 marginBottom">
                        <input type="text" placeholder="ICD Code Search" class="form-control flexdatalist flexdatalist-set" name="icdCodeInput" id="icdCodeInputId" autocomplete="off" style="position: absolute; top: -14000px; left: -14000px;" tabindex="-1">

                        <datalist id="ICDcodeLst">    
                        </datalist>
                      </div>
                      
                      <script> 
                       
                      $(document).ready(function(){
                          try{
                              <% JSONArray mapIcdCodeLst = (JSONArray) request.getSession().getAttribute("DiagnosisDTL"); %>
                            var tempJSON = <%=mapIcdCodeLst.toString() %>;
						    var icdJsonObj = tempJSON; 
						    //console.log(tempJSON);   
							if(!localStorage.getItem('icdJsonObj'))
								localStorage.setItem('icdJsonObj',JSON.stringify(tempJSON).toString()); 
							else if(JSON.stringify(tempJSON).toString() != localStorage.getItem('icdJsonObj').toString() )
								{
									console.log('localStorage else');
									localStorage.setItem('icdJsonObj',JSON.stringify(tempJSON));  
								} 
							$('input[name=icdCodeInput]').flexdatalist({
							     minLength: 1,
							     focusFirstResult: true,
							     maxShownResults: 50,
							     searchIn: 'icdCode', 
							     data: tempJSON
							 }); 
							$('input[name=diseaseInput]').flexdatalist({
							     minLength: 1,
							     focusFirstResult: true,
							     maxShownResults: 50,
							     searchIn: 'diagnosisName', 
							     data: tempJSON
							 });  
                              }
                          catch(err){
                              console.log('err.message:>>>'+err.message);
                              }
                         }); 
                      </script>
                      
                      <div class="col-xs-6 col-sm-6 col-md-3 marginBottom">
                        <input type="text" placeholder="Disease" class="form-control flexdatalist flexdatalist-set" name="diseaseInput" style="position: absolute; top: -14000px; left: -14000px;" tabindex="-1">
                      </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      <div class="col-sm-1 col-md-1 paddingLeftRightZero siteDiv marginBottom">
                        <select class="form-control" name="diagnosisSiteIcd" id="diagnosisSiteIcdId" autocomplete="off">
                            <option value="0">Side</option>
                            <option value="1">NR</option>
                            <option value="2">Left</option>
                            <option value="3">Right</option>
                            <option value="4">Bilateral</option>
                        </select>
                      </div>
                      <div class="col-xs-12 col-sm-3 col-md-2 marginBottom" style="display: none;">
                          <div class="col-xs-4 col-sm-4 col-md-4 paddingRightZero chiefComplaintNoOfDays">
                            <input type="text" class="form-control" placeholder="No." name="diagnosisNoOfDaysIcd" id="diagnosisNoOfDaysIcdId">
                          </div>
                          <div class="col-xs-8 col-sm-8 col-md-8 alignLeftPaddingLeftZero chiefComplaintDuration marginBottom">
                            <select class="form-control" name="diagnosisDurationIcd" id="diagnosisDurationIcdId" autocomplete="off">
                              <option value="0">Duration</option>
                              <option value="1">Days</option>
                              <option value="2">Weeks</option>
                              <option value="3">Months</option>
                              <option value="4">Years</option>
                            </select>
                          </div>
                        </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      <div class="col-sm-2 col-md-2 marginBottom" >
                        <select class="form-control" name="diagnosisType" >
                          <!-- <option value="0">Diagnosis Type</option> -->
                          <option value="11">Provisional</option>
                          <!-- <option value="13">Working</option> -->
                          <option value="12">Differential</option>
                          <option value="14">Final</option>
                        </select>
                      </div>
                      <div class="col-sm-3 col-md-3">
                        <textarea class="form-control" name="diagnosisRemarksIcd" id="diagnosisRemarksIcdId" rows="1" style="height:34px;" placeholder="Remarks"></textarea>
                      </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      <div class="col-sm-2 col-md-1 prescriptionTileRowsCol">
                        <button class="btn btn-sm btn-info diagnosisAdd" type="button">Add</button>
                      </div>
                    </div>
                    
                   <!--  <p>(<font style="color: rgba(35, 104, 194, 0.8)"> Diagnosis Type : </font> <label><input type="radio" name="diagnosisTypeD" checked> Provisional</label>&nbsp;<label><input type="radio" name="diagnosisTypeD"> Working</label>&nbsp;<label><input type="radio" name="diagnosisTypeD"> Differential</label>&nbsp;<label style="color: rgba(35, 104, 194, 0.8)"><input type="radio" name="diagnosisTypeD"> Final </label> )</p> -->
                    <br>
                    <div class="clearfix visible-xs" style="margin-top:10px;"></div>
                    
               <!--      <button class="btn btn-info" id="externalDiagnosisbtn" type="button" onclick="$('#externalDiagnosis').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus');"><i class="fa fa-plus"></i> Other Diagnosis</button>
                    <button class="btn btn-info" id="confidentilabtn"  type="button" onclick="$('#confidentialDivId').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus');"><i class="fa fa-plus"></i> Confidential Info.</button>
 -->
 						
					 <button id="externalDiagnosisbtn" class="btn btn-info" type="button" onclick="if($('#confidentialDivId').is(':visible')){$('#confidentilabtn').click();}
                          $('#externalDiagnosis').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus');"><i class="fa fa-plus"></i> Other Diagnosis</button>
                    <button id="confidentilabtn" class="btn btn-info" type="button" onclick="if($('#externalDiagnosis').is(':visible')){$('#externalDiagnosisbtn').click();}
                    $('#confidentialDivId').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus');"><i class="fa fa-plus"></i> Confidential Info.</button>




                    <div class="row" id="externalDiagnosis" style="display: none;">
                        <br>
                        <div class="col-sm-10 col-md-11 marginBottom">
                          <div class="input-group">
                              <input type="text" placeholder="Enter Other Diagnosis" name="externalDiagnosisTxt" id="externalDiagnosisTxtId" class="form-control">
                              <div class="input-group-btn">
                                <button class="btn btn-default extInvestigationCleanBtn" onclick="$('input[name=externalDiagnosisTxt]').val('');" style="z-index:0;" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                              </div>
                          </div>
                        </div>
                        <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                        <div class="col-sm-2 col-md-1 prescriptionTileRowsCol">
                           <button class="btn btn-sm btn-info diagnosisAddExt" id="diagnosisAddExtId" type="button">Add</button>
                        </div>
                        <div class="clearfix visible-xs"></div>
                    </div>
                    
                    
                    <div class="row" id="confidentialDivId" style="display: none;">
                        <br>
                        <div class="col-sm-10 col-md-11 marginBottom">
                         
                         <div class="form-group col-xs-14 col-md-13 col-sm-13">
                        	<textarea class="form-control" id="ConfidentialInfoId"  placeholder="Enter  Confidential Information" name="ConfidentialInfoName" maxlength="2000"></textarea>
                     	 </div>
                       </div>
                        
                        <div class="clearfix visible-xs"></div>
                    </div>
                    
                    <div class="clearfix visible-xs" style="margin-top:10px;"></div>
                    <div class="row">
                                      <br>
                      <div class="col-xs-12 col-md-3 col-sm-5">
                        <label for="diagnosisNote" style="color:rgba(75,75,75, 0.7);" onclick="$('#diagnosisNote').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus').css({'color': '#2196F3',});">
                        <i class="fa fa-plus" style="color:#2196F3 "></i>&nbsp;<b>Diagnosis Note:</b></label>
                      </div>
                      </div>
                      <div class="row" id="diagnosisNote" style="display:none;">
                      <div class="form-group col-xs-12 col-md-11 col-sm-12">
                        <textarea class="form-control" id="diagnosisNoteId" name="diagnosisNote" maxlength="2000"></textarea>
                      </div>
                      <!-- <div class="form-group col-xs-12 col-md-12 col-sm-12">
                        <div class="col-xs-3 col-md-1 col-sm-3">
                          <label for="diagnosisNote">Diagnosis Note:</label>
                        </div>
                        <div class="col-xs-9 col-md-9 col-sm-9 alignLeftPaddingLeftZero">
                          <textarea class="form-control" id="diagnosisNoteId" name="diagnosisNote" maxlength="2000"></textarea>
                        </div>
                      </div> -->
                      <div class="col-xs-3 col-sm-1" style="padding-right: 0px; text-align: center;">
                          <button type="button" style="padding: 0px;color:#3696e9" class="btn btn-link progressNoteMacroBtn" id="diagnosisNoteIdDiv" value="progressNoteMacroModal2Div"  onclick="$('#progressNoteMacroModal2').modal('show');"> <!--  onclick="$('#progressNoteMacroModal').modal('show');" <i class="material-icons" style="font-size: 1.5em">message</i> --><i class="fa fa-comments" style="font-size: 1.5em"></i></button>
                        </div>   
                    </div>
                                                       
                    <!--  -->
                  </div>
                  
                  <div class="clearfix visible-xs"></div>
                  
                  
                  
                  <div class="prescriptionTile" style="/* padding: 1% 3% 3% 3% */">
                    <div class="row">
                      <div class="col-sm-10 col-md-11 prescriptionTileRowFirstCol">
                        <p class="investigationAdded" onclick="$('#investigationTile').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus').css({'color': '#2196F3',});"><i class="fa fa-plus" style="color:#2196F3 "></i>&nbsp;<b>Investigations :</b> 
                        <a style="text-decoration: none;" class="clearLnk"><img style="width: 22px;" alt="" src="/HIS/hisglobal/drDeskAssets/img/clear3.png"></a>
                        <button class="btn btn-xs btn-danger clearAllValues" type="button" data-toggle="tooltip" title="Clear All" onclick="$(this).parent().find('label').remove();" style="background-color: white;border: 0px;"><span class="glyphicon glyphicon-trash" style="color: red;"></span></button> 
                        <!-- <a><label><input type="checkbox" class="checkedInput" name="reasonOfVisit"  checked> CBC</label>
                        </a>
                        <a style="padding-left: 5px;"><label> 
                        <input type="checkbox" class="checkedInput" name="reasonOfVisit"  checked> BLOOD SUGAR</label>
                        </a> --></p>
                      </div>
                      <div class="col-sm-2 col-md-1 prescriptionTileRowsCol">
                        <button type="button" class="btn btn-info btn-sm investigationLabReport" onclick="$('#investigationLabReportModal').modal('show');" style="float:left;background-color:#ed6c68; border:0px;">Lab Reports</button>
                      	<br><br>
                        <!-- <p><a class="clearLnk" style="color: rgba(35, 104, 194, 0.8)" title="Clear checked items"><img src="img/clear.png" style="width: 2.2em; height: 2.2em; min-width: 2em;"></a></p> -->
                      </div>
                    </div>  
                    <div class="row">
                      <div class="col-sm-11">
                        
                        <%
						HashMap<String ,String> BookmarkMAP = (HashMap) request.getSession().getAttribute("BookmarkDTL");
						//System.out.print("MacrosDTL"+MacrosMap);	
						ArrayList<String> listBookMarkList=null;
						int i=0;
						 	if(BookmarkMAP!=null)
						 	{
						%>
						<p style="padding-left: 10px;display:none"><a><label><input type="radio" value="1" name="bookmarksToAdd" checked> Add through Bookmarks</label></a>
                        <!-- <a style="padding-left: 5px;"><label><input type="radio" value="2" name="bookmarksToAdd"> Lab Wise Test</label></a></p> -->
                        <p class="AddToBookMarkButtons" style="padding-left: 10px;">
                        <%
						 		for(Map.Entry m:BookmarkMAP.entrySet()){ 
									listBookMarkList=new  ArrayList<String>();
									listBookMarkList=(ArrayList) m.getValue();
						//			System.out.println("::::"+m.getValue());
						
									%>
									 <button class="btn btn-success btn-xs investigationTestTriggerBtn" type="button" onclick="$('#investigationTestBundle<%=i %>').modal('show');"><%=listBookMarkList.get(5) %></button>
							<%	
							 i++;
								}
                        	%>
                        	</p>
                        	<br>
                        	<%
						 	}	
							   %>
						<!-- <button class="btn btn-warning btn-sm investigationTestTriggerBtn" type="button" data-toggle="modal" data-target="#investigationTestBundle">Blood Sugar</button> -->
                        
                        
                        <p class="LabWiseTestButtons" style="padding-left: 10px; display: none;"><button class="btn btn-danger btn-sm investigationTestTriggerBtn" type="button" data-toggle="modal" data-target="#investigationTestBundle">Biochemistry</button><button class="btn btn-danger btn-sm investigationTestTriggerBtn" type="button" data-toggle="modal" data-target="#investigationTestBundle">Radiology</button><button class="btn btn-danger btn-sm investigationTestTriggerBtn" type="button" data-toggle="modal" data-target="#investigationTestBundle">Dental</button><button class="btn btn-danger btn-sm investigationTestTriggerBtn" type="button" data-toggle="modal" data-target="#investigationTestBundle">Clinical Pathology Lab</button><button class="btn btn-danger btn-sm investigationTestTriggerBtn" type="button" data-toggle="modal" data-target="#investigationTestBundle">Microbiology</button><button class="btn btn-danger btn-sm investigationTestTriggerBtn" type="button" data-toggle="modal" data-target="#investigationTestBundle">Dermotology</button><button class="btn btn-danger btn-sm investigationTestTriggerBtn" type="button" data-toggle="modal" data-target="#investigationTestBundle">Pathology</button>
                        </p>
                      </div>    
                    </div>
                    <!-- <p>(<font style="color: rgba(35, 104, 194, 0.8)"> Search On : </font> <label><input type="radio" name="investigationSearchOn" checked="true" value="1"> Test Name</label>&nbsp;<label>
                    <input type="radio" name="investigationSearchOn" value="2"> Test Code</label>
                    )</p> -->
                       <div class="row investigationsDiv" id="investigationTile" style="display:none;">
                      <div class="col-xs-12 col-sm-6 col-md-8 marginBottom">
                        <div class="input-group">
                          <input type="text" placeholder="Test Name" name="investigation" class="form-control flexdatalist flexdatalist-set" autocomplete="off" tabindex="-1" style="position: absolute; top: -14000px; left: -14000px;">
                          <div class="input-group-btn">
                            <button class="btn btn-default investigationCleanBtn" onclick="$('input[name=investigation]').val('');$('input[name=flexdatalist-investigation]').css('background-color','#ffffff');" style="z-index:0;" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                          </div>
                        </div>
                        <datalist id="investigationLstTest">          
                       <%
						/* HashMap<String ,List> mapTestDtl = (HashMap) request.getSession().getAttribute("TESTDTL");                       
	                        JSONArray mapTestDtlLst = new JSONArray();   
								for(Map.Entry mapTestDtlItem:mapTestDtl.entrySet()){  
			                          JSONObject mapTestDtlLstSubObj = new JSONObject(); 
			                          ArrayList lst = (ArrayList) mapTestDtlItem.getValue();
			                          mapTestDtlLstSubObj.put("testName", lst.get(1)!=null?lst.get(1):"");
			                          mapTestDtlLstSubObj.put("testId", lst.get(0)!=null?lst.get(0):"");
			                          mapTestDtlLstSubObj.put("labName", lst.get(2)!=null?lst.get(2):"");
									  mapTestDtlLst.put(mapTestDtlLstSubObj);
								} 
		                        System.out.println("obj inv::::::::::::::>>>>>>>>> "+mapTestDtlLst.toString()); */
 
						//System.out.print("mapTEST"+map);	
						%>
						<%-- <%
						 ArrayList<String> list=null;
								for(Map.Entry m:map.entrySet()){ 
									list=new  ArrayList<String>();
							  // System.out.println("NEWWWWWWWWW\n"+m.getKey()+" "+m.getValue());
							   String TestCode=(String)m.getKey();
							   //System.out.println("List Value::::::"+TestCode);
							   list=(ArrayList) m.getValue();
							   
							   %> 
			                        <option id="<%=TestCode%><%="^" %><%=list.get(2) %>" value="<%=list.get(1) %>"  ></option>
			       				<%
									}
								     %> --%>
					 	</datalist>
                      </div>
<div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      
                      <div class="col-sm-1 col-md-1 paddingLeftRightZero siteDiv marginBottom">
                          <select class="form-control" name="investigationSite" id="investigationSiteId" autocomplete="off">
                             <option value="0">Side</option>
                            <option value="1">NR</option>
                            <option value="2">Left</option>
                            <option value="3">Right</option>
                            <option value="4">Bilateral</option>
                          </select>
                        </div>
                        <div class="col-xs-12 col-sm-3 col-md-2 marginBottom">
                            <textarea class="form-control" name="InvestigationRemarks" id="InvestigationRemarksId" rows="1" style="height:34px;" placeholder="Remarks"></textarea>
                         </div>
                         
                      <div class="col-xs-12 col-sm-1 col-md-1 optionalOrDiv" style="display: none">
                        <p><b>OR</b></p>
                      </div>

                      <div class="col-xs-12 col-sm-5 col-md-4 marginBottom" style="display: none">
                          <div class="input-group">
                              <input type="text" tabindex=-1 placeholder="External Test Name" name="externalInvestigation" id="externalInvestigationId" class="form-control">
                              <div class="input-group-btn">
                                <button tabindex=-1  class="btn btn-default extInvestigationCleanBtn" onclick="$('input[name=externalInvestigation]').val('');" style="z-index:0;" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                              </div>
                          </div>
                        </div>

                      <!-- <div class="col-sm-2 col-md-1 paddingLeftRightZero siteDiv marginBottom">
                        <select class="form-control" name="investigationSite" id="investigationSiteId" autocomplete="off">
                         <option value="0">Side</option>
                            <option value="1">NR</option>
                            <option value="2">Left</option>
                            <option value="3">Right</option>
                            <option value="4">Bilateral</option>
                        </select>
                      </div>
                      <div class="col-sm-3 col-md-2">
                        <textarea class="form-control" name="investigationRemarks" id="investigationRemarksId" rows="1" style="height:34px;" placeholder="Instructions"></textarea>
                      </div> -->
                      <script> 
                      $(document).ready(function(){
                          <%  JSONArray mapTestDtlLst = (JSONArray) request.getSession().getAttribute("TESTDTL");   %> 
                          try{ 
                            var tempJSON = <%=mapTestDtlLst.toString() %>;
						    var testJsonObj = tempJSON; 
						    //console.log(tempJSON);   
							if(!localStorage.getItem('testJsonObj'))
								localStorage.setItem('testJsonObj',JSON.stringify(tempJSON).toString()); 
							else if(JSON.stringify(tempJSON).toString() != localStorage.getItem('testJsonObj').toString() )
								{
									console.log('localStorage else');
									localStorage.setItem('testJsonObj',JSON.stringify(tempJSON));  
								} 
							$('input[name=investigation]').flexdatalist({
							     minLength: 1,
							     focusFirstResult: true,
							     searchByWord: true,
							     maxShownResults: 50,
							     searchIn: 'testName', 
							     data: tempJSON
							 });  
                              }
                          catch(err){
                              console.log('err.message:>>>'+err.message);
                              }
                         }); 
                      </script>
                      	<div class="clearfix visible-xs" style="margin-top:5px;"></div>
			              <div class="col-sm-1 col-md-1 prescriptionTileRowsCol">
			                 <button class="btn btn-sm btn-info investigationAdd" id="investigationAddId" type="button">Add</button>
			              </div>
                      </div>
                      <br>
                   <div class="clearfix visible-xs" style="margin-top:10px;"></div>
                   <!-- <button class="btn btn-info" type="button" onclick="$('#externalInvestigationInput').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus');"><i class="fa fa-plus"></i> Add External Investigation</button> -->
                  <!--   <div class="clearfix visible-xs" style="margin-top:5px;"></div> -->
                    <div class="row">
                      <div class="col-xs-12 col-md-3 col-sm-5">
                        <label for="investigationNote" style="color:rgba(75,75,75, 0.7);" onclick="$('#investigationNote').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus').css({'color': '#2196F3',});">
                        <i class="fa fa-plus" style="color:#2196F3 "></i>&nbsp;<b>Investigation Note:</b></label>
                      </div>
                       </div>  
                      <div class="row" id="investigationNote" style="display:none;">
                      <div class="form-group col-xs-12 col-md-11 col-sm-12">
                        <textarea class="form-control" id="investigationNoteId" name="investigationNote" maxlength="2000"></textarea>
                      </div>
                     <!-- <div class="row" id="externalInvestigationInput" style="display:none;">   <br>
                       <div class="col-sm-10 col-md-11">
			             <div class="input-group">
			             	<input type="text" placeholder="Enter External Test Name" name="externalInvestigation" class="form-control">
				            <div class="input-group-btn">
							  <button class="btn btn-default extInvestigationCleanBtn" onclick="$('input[name=externalInvestigation]').val('');" style="z-index:0;" type="button"><i class="glyphicon glyphicon-remove"></i></button>
							</div>
			             </div>
			           </div>
			           <div class="clearfix visible-xs" style="margin-top:5px;"></div>
		               <div class="col-sm-2 col-md-1 prescriptionTileRowsCol">
		                 <button class="btn btn-sm btn-info externalInvestigationAdd" type="button">Add</button>
		               </div>-->
		               
		               <div class="col-xs-3 col-sm-1" style="padding-right: 0px; text-align: center;">
                          <button type="button" style="padding: 0px;color:#3696e9" class="btn btn-link progressNoteMacroBtn" id="investigationNoteIdDiv" value="progressNoteMacroModal3Div"  onclick="$('#progressNoteMacroModal3').modal('show');"> <!--  onclick="$('#progressNoteMacroModal').modal('show');" <i class="material-icons" style="font-size: 1.5em">message</i> --><i class="fa fa-comments" style="font-size: 1.5em"></i></button>
                        </div>
		               </div>
                   
               
                  </div>
                  
                  <div class="clearfix visible-xs"></div>
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  
                  <div class="prescriptionTile" style="/* padding: 1% 3% 3% 3%; */">
                    <!-- <p class="text-right"><button type="button" class="btn btn-primary btn-sm savePrintPrescBtn">Save/Print</button></p> 
                    <br> -->
                    <div class="row">
                      <div class="col-sm-10 col-md-11 prescriptionTileRowFirstCol">
                        <p class="clinicalProceduresAdded" onclick="$('#Clinicalprocedure').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus').css({'color': '#2196F3',});"
                        ><i class="fa fa-plus" style="color:#2196F3 "></i><b>&nbsp;Procedure(s) advised :</b> 
                          <a style="text-decoration: none;" class="clearLnk">
                            <img style="width: 22px;" alt="" src="/HIS/hisglobal/drDeskAssets/img/clear3.png">
                          </a>
                          <button class="btn btn-xs btn-danger clearAllValues" type="button" data-toggle="tooltip" title="Clear All" onclick="$(this).parent().find('label').remove();" style="background-color: white;border: 0px;"><span style="color: red;" class="glyphicon glyphicon-trash" style="color: red;"></span></button>
                        </p>
                      </div>
                    </div>
                    <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                    <div class="row ClinicalPrcoeduresDiv" id="Clinicalprocedure" style="display: none;">
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      <div class="col-xs-12 col-sm-6 col-md-8 clinicalProcedureCol">
                        <div class="input-group">
                         <!--  <input type="text" placeholder="Clinical Procedure" clinicalProcedureCode="" class="form-control clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_VR7" id="txt-snomed-ct-search_VR7" onclick="load_UNIVD7('VR7','','450970008')" autocomplete="on"> -->
                         <input type="text" placeholder="Clinical Procedure" name="clinicalProcedureName" class="form-control flexdatalist flexdatalist-set" autocomplete="off" tabindex="-1" style="position: absolute; top: -14000px; left: -14000px;">
                          <div class="input-group-btn">
                            <button class="btn btn-default clinicalProcedureCleanBtn" type="button" onclick="$('input[name=clinicalProcedureName]').val('');"><i class="glyphicon glyphicon-remove"></i></button>
                          </div>
                        </div>
                        <div class="concept" id="conceptdiv_7"></div>
                        <input type="hidden" name="targetId"> 
                        
                        <script> 
                      $(document).ready(function(){
                          <%  JSONArray mapTestDtlLst1 = (JSONArray) request.getSession().getAttribute("CILINICALPROCEDURE");   %> 
                          try{ 
                            var tempJSON = <%=mapTestDtlLst1.toString() %>;
						    var testJsonObj = tempJSON; 
						    //console.log(tempJSON);   
							if(!localStorage.getItem('ClinicalProcedureObj'))
								localStorage.setItem('ClinicalProcedureObj',JSON.stringify(tempJSON).toString()); 
							else if(JSON.stringify(tempJSON).toString() != localStorage.getItem('ClinicalProcedureObj').toString() )
								{
									console.log('localStorage else');
									localStorage.setItem('ClinicalProcedureObj',JSON.stringify(tempJSON));  
								} 
							$('input[name=clinicalProcedureName]').flexdatalist({
							     minLength: 1,
							     focusFirstResult: true,
							     searchByWord: true,
							     maxShownResults: 50,
							     searchIn: 'testName', 
							     data: tempJSON
							 });  
                              }
                          catch(err){
                              console.log('err.message:>>>'+err.message);
                              }
                         }); 
                      </script>
                        
                      </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      <div class="col-xs-12 col-sm-1 col-md-1 optionalOrDiv" style="display: none;">
                        <p><b>OR</b></p>
                      </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      <div class="col-xs-12 col-sm-3 col-md-3" style="display: none;">
                        <div class="input-group">
                          <input type="text" placeholder="Other Procedures" name="otherProcedures" id="otherProceduresId" tabindex=-1 class="form-control">
                          <div class="input-group-btn">
                            <button tabindex=-1 class="btn btn-default otherProceduresCleanBtn" onclick="$('input[name=otherProcedures]').val('');" style="z-index:0;" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                          </div>
                        </div>
                      </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      
                      <div class="col-sm-1 col-md-1 paddingLeftRightZero siteDiv marginBottom">
                          <select class="form-control" name="clinicalProceduresSite" id="clinicalProceduresSiteId" autocomplete="off">
                             <option value="0">Side</option>
                            <option value="1">NR</option>
                            <option value="2">Left</option>
                            <option value="3">Right</option>
                            <option value="4">Bilateral</option>
                          </select>
                        </div>
                        <div class="col-xs-12 col-sm-3 col-md-2 marginBottom">
                            <textarea class="form-control" name="clinicalProceduresRemarks" id="clinicalProceduresRemarksId" rows="1" style="height:34px;" placeholder="Remarks"></textarea>
                         </div>
                      <div class="clearfix visible-xs" style="margin-top:5px;"></div>
                      <div class="col-sm-1 col-md-1 prescriptionTileRowsCol">
                        <button class="btn btn-sm btn-info clinicalProceduresAdd" id="clinicalProceduresAddId" type="button">Add</button>
                      </div>
                    </div>
                                        
                  </div>
                  
                  <div class="clearfix visible-xs"></div>
                  
                  
                   <div class="clearfix visible-xs"></div>
                  <div class="prescriptionTile">
                  
                    <div class="row">
                      <div class="col-sm-12" style="padding-left: 7px;">
                        <p class="drugsAdvicesAdded" onclick="$('#drugAdviceTile').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus').css({'color': '#2196F3',});">
                        <i class="fa fa-plus" style="color:#2196F3 "></i>&nbsp;<b>Drugs/Advices :</b>
                        <a style="text-decoration: none;" class="clearLnk">
                            <img style="width: 22px;" alt="" src="/HIS/hisglobal/drDeskAssets/img/clear3.png">
                          </a>
                          <button class="btn btn-xs btn-danger clearAllValues cleaarAll1" type="button" data-toggle="tooltip" title="Clear All"  style="background-color: white;border: 0px;"><span style="color: red;" class="glyphicon glyphicon-trash" style="color: red;"></span></button>
						  <button type="button" class="btn btn-info" data-toggle="modal" data-target="CimsIntegrationId" style="float:Right;display:none;background-color:#ed6c68; border:0px;" onclick="loadDoc()">Drug Health Alert</button>
						  &nbsp;<button type="button" class="btn btn-info" data-toggle="modal" data-target="CimsAlleryId" onclick="CimsAlleryDoc()" style="display:none;float:right;margin-right: 11px;">Drug Interactions</button>
						</p>
                        <br>
                        <%
          			MultiValueMap DrugProfile= (MultiValueMap) request.getSession().getAttribute("DrugProfile");
                   	
                     if(DrugProfile!=null)
                   	{
                   		Set<String> keys = DrugProfile.keySet();
                   	for(String key : keys){ 
                   		/* System.out.println("RoomNo"+key);
                   		System.out.println("Patient Dtl"+DrugProfile.get(key));
                   		System.out.println("Patient Dtl"+((List) DrugProfile.getCollection(key)).size()); */
                   		
                   		%>
                   		 <button class="btn btn-primary btn-xs investigationTestTriggerBtn" type="button" style="display: none;" onclick="$('#investigationTestBundle<%=key.split("#")[0] %>').modal('show');"><%=key.split("#")[1] %></button>		
                   		<%
                   		
                   		
                   		
                   	}
                  }
                   		%>
                        
                      
                       
                      <!--  <button class="btn btn-success btn-xs investigationTestTriggerBtn" type="button" onclick="$('#investigationTestBundle0').modal('show');">Bookmark1</button> -->
                          
                       <!--    <div><br></div>  -->
                         <!-- <div class="table-responsive">
                          <table id="drugAdviceListTable" class="table table-condensed table-hover" style="margin-bottom: 0px;"> 
                            <thead>
                              <tr style="background: linear-gradient(to bottom, #dbdbdb /* #acd6ff */, white);">
                               <th><input type="checkbox" class="checkedInputAll" name="drugsAdvicesAll"></th><th>Drug</th><th>Dosage</th><th>Frequency</th><th class="drugAdviceListExcessCol">Start Date</th><th>Days</th><th>Quantity</th><th class="drugAdviceListExcessCol">Instructions</th><th>Change</th><th>Monogram</th>  
                              </tr>
                            </thead>
                            <tbody>
                            </tbody>
                           </table>
                          </div>   -->
                      </div>
                    
                     <!--  <div class="col-sm-0">
                        <p><a class="clearLnkDrug" style="color: rgba(35, 104, 194, 0.8)" title="Clear checked items"><img src="img/clear.png" style="width: 2.2em; height: 2.2em"></a></p>
                      </div> -->
                    </div>   
                    <div class="clearfix" style="margin:10px;"></div> 
                    <div class="row" id="drugAdviceTile" style="display: none;">
                    <div class="table-responsive">
                          <table id="drugAdviceListTable" class="table table-condensed table-hover" style="margin-bottom: 0px;"> 
                            <thead>
                              <tr style="background: linear-gradient(to bottom, #dbdbdb /* #acd6ff */, white);">
                               <th><input type="checkbox" class="checkedInputAll" name="drugsAdvicesAll"></th><th>Drug</th><th>Dosage</th><th>Frequency</th><th class="drugAdviceListExcessCol">Start Date</th><th>Days</th><th>Quantity</th><th class="drugAdviceListExcessCol">Instructions</th><th>Change</th><th>Monogram</th>  
                              </tr>
                            </thead>
                            <tbody>
                            </tbody>
                           </table>
                          </div>  
                           <div class="col-sm-0">
                        <!-- <p><a class="clearLnkDrug" style="color: rgba(35, 104, 194, 0.8)" title="Clear checked items"><img src="img/clear.png" style="width: 2.2em; height: 2.2em"></a></p> -->
                      </div>
                      <br>
                      <br>
                      <div class="col-md-4">	<!-- class name changed from "col-md-2"  to "col-md-4" by Timsi as suggested By Priyesh Sir  -->
                        <p style="letter-spacing:0"><label>Drug Name<sup style="color:red;">*</sup></label></p>
                        <div class="input-group">
                        	<input type="text" placeholder="Drug Name" class="form-control" name="drugName" value="" autocomplete="off"  tabindex="5">  
                        	<div class="input-group-btn">
							  <button class="btn btn-default drugNameCleanBtn" onclick="$('input[name=drugName]').val('');$('input[name=flexdatalist-drugName]').css('background-color','#ffffff');" style="z-index:0;" type="button"><i class="glyphicon glyphicon-remove"></i></button>
							</div>
                        </div>
                        <datalist id="drugNameLst">
                          <%
						/* HashMap<String ,List> map1 = (HashMap) request.getSession().getAttribute("DrugDTL");  
                          JSONArray obj2 = new JSONArray();   
							for(Map.Entry m1:map1.entrySet()){  
		                          JSONObject obj = new JSONObject(); 
		                          ArrayList lst = (ArrayList) m1.getValue();
								  obj.put("drugName", lst.get(1)!=null?lst.get(1):"");
								  obj.put("drugId", lst.get(0)!=null?lst.get(0):"");
								  obj.put("drugQuan", lst.get(2)!=null?(lst.get(2).equals("0")?"":lst.get(2)):"");
		                          obj2.put(obj);
							}
	                          System.out.println("obj::::::::::::::>>>>>>>>>"+obj2.toString()); 
	                          System.out.println("mapTestDtlLst::::::::::::::>>>>>>>>>"+mapTestDtlLst.toString()); 
		                        System.out.println("obj icd:::::::::::::>>>>>>>>> "+mapIcdCodeLst.toString()); */
	                          
						  //System.out.print("mapTEST::"+map1);	
						  %>
						 <%--  <%
						       ArrayList<String> list1=null;
								for(Map.Entry m1:map1.entrySet()){ 
									list1=new  ArrayList<String>();
							  // System.out.println("NEWWWWWWWWW\n"+m.getKey()+" "+m.getValue());
							   String DrugName=(String)m1.getKey();
						//	   System.out.println("List Value::::::"+DrugName);
							   list1=(ArrayList) m1.getValue(); 
							    
							   if(list1.get(2).equalsIgnoreCase("0"))
							   {
								%><option id="<%=list1.get(0) %>" value="<%=DrugName %>"></option>  <%
							   }else
							   {
								   String tempDruag=DrugName+"("+list1.get(2)+")";
                  				%> <option id="<%=list1.get(0) %>" value="<%=tempDruag%>"></option>  <%
	                            }
	                          }
	                          %>  --%>
                        </datalist>
                      </div>
                      <script>
                      $(document).ready(function(){
                          try{
                              <%  JSONArray obj2 = (JSONArray) request.getSession().getAttribute("DrugDTL");   
                              %>
                           var drugJsonObj = <%=obj2.toString() %>; 
							//console.log(drugJsonObj);
							if(!localStorage.getItem('drugJsonObj'))
								localStorage.setItem('drugJsonObj',JSON.stringify(drugJsonObj)); 
							else if(JSON.stringify(drugJsonObj).toString() != localStorage.getItem('drugJsonObj').toString() )
								localStorage.setItem('drugJsonObj',JSON.stringify(drugJsonObj)); 
							$('input[name=drugName]').flexdatalist({
							     minLength: 1,
							     focusFirstResult: true,
							     searchByWord: true,
							     maxShownResults: 50,
							     searchIn: 'drugName',
							     visibleProperties: ["drugName","drugQuan"], 
							     data: drugJsonObj
							});  
							$('input[name=drugName]').on('select:flexdatalist', function(event, set, options) {
							    console.log(set.drugName); 
							    console.log(set.drugQuan); 
							    console.log(set.drugId); 
							    $(this).attr('drugId',set.drugId);
							}); 
                              }
                          catch(err){
                              console.log('err.message:>>>'+err.message);
                              }
                      });
					  </script>
                        <div class="col-md-1 rmLeftPaddInInput">
                        <p style="letter-spacing:0"><label>Dosages<!-- <sup style="color:red;">*</sup> --></label></p>
                        <select class="form-control" name="drugDosage" id="drugDosageId" autocomplete="off"  tabindex="6">
                        <option value="0">Select</option>
                        	 <%
                        	 LinkedHashMap<String ,List> map2 = (LinkedHashMap) request.getSession().getAttribute("DosageDTL");
		                
								System.out.print("mapdasage::"+map2);	
								  ArrayList<String> list2=null;
										for(Map.Entry m2:map2.entrySet()){ 
											list2=new  ArrayList<String>();
									  // System.out.println("NEWWWWWWWWW\n"+m.getKey()+" "+m.getValue());
									   String DosageName=(String)m2.getKey();
									   //System.out.println("List Value::::::"+DosageName);
									   list2=(ArrayList) m2.getValue(); 
									   
									   %>
		                    
		                          <option value="<%=DosageName%>"><%=list2.get(1) %></option> 
		                          <%
		                          }
		                          %>
                        </select>
                       <%--  <input type="text" placeholder="" list="drugDosageLst" class="form-control" name="drugDosage" autocomplete="off"  tabindex="6"> 
                        <datalist id="drugDosageLst">
                        <%
						HashMap<String ,List> map2 = (HashMap) request.getSession().getAttribute("DosageDTL");
                
						System.out.print("mapdasage::"+map2);	
						  ArrayList<String> list2=null;
								for(Map.Entry m2:map2.entrySet()){ 
									list2=new  ArrayList<String>();
							  // System.out.println("NEWWWWWWWWW\n"+m.getKey()+" "+m.getValue());
							   String DosageName=(String)m2.getKey();
							   //System.out.println("List Value::::::"+DosageName);
							   list2=(ArrayList) m2.getValue(); 
							   
							   %>
                    
                          <option id="<%=DosageName%>" value="<%=list2.get(1) %>"></option> 
                          <%
                          }
                          %>
                        </datalist>  --%>
                      </div>
                      <div class="col-md-1 rmLeftPaddInInput">
                        <p style="letter-spacing:0"><label>Frequency<!-- <sup style="color:red;">*</sup> --></label></p>
                        <select class="form-control" name="drugFrequency"  tabindex="7"> 
                        	 <option value="0">Select</option>
							<option value="11">OD(1-0-0)</option>
							<option value="12">BD(1-0-1)</option>
							<option value="13">TDS(1-1-1)</option>
							<option value="14">SOS(As per Requirement)</option>
							<option value="15">QID(1-1-1-1)</option>
							<option value="16">HS(At Bed Time)</option>
							<option value="18">QW(Once a Week)</option>
							<option value="11">OD(0-1-0)</option> 
							<option value="12">BD(0-1-1)</option>
							<!-- <option value="HS">STAT</option> -->
                        </select> 
                      </div> 
                      <div class="col-md-2">
                        <p style="letter-spacing:0"><label>Start Date<!-- <sup style="color:red;">*</sup> --></label></p>
                        <input type="date" class="form-control" id="drugStartDate" name="drugStartDate" style="line-height: normal;"  tabindex="8">
                      </div>
                      <div class="col-md-1">
                        <p style="letter-spacing:0"><label>Days<!-- <sup style="color:red;">*</sup> --></label></p>
                        <input type="text" class="form-control" onkeypress="return isNumber(event)" name="drugDays" value=""  tabindex="9">
                      </div>
                      <div class="col-md-1">
                        <p style="letter-spacing:0"><label>Qua.<!-- <sup style="color:red;">*</sup> --></label></p>
                        <input type="text" class="form-control" onkeypress="return isNumber(event)" name="drugQuantity"  value=""  tabindex="10">
                      </div>
                      <div class="col-md-1">	<!-- class name changed from "col-md-3" to "col-md-1" by Timsi as suggested By Priyesh Sir  -->
                        <p style="letter-spacing:0"><label>Instructions</label></p>
                       <!--  <textarea class="form-control" name="drugInstructions" rows="1" tabindex="11" style="height:34px;"></textarea>  -->	
                        
                        <input type='text'  class="form-control flexdatalist flexdatalist-set"  autocomplete="off"   placeholder='Remarks'  list='drugInstructions' name='drugInstructions'>
                       
						<datalist id="drugInstructions">
						    <option value="After Food">After Food</option>
						    <option value="Before Food">Before Food</option>
						    <option value="After Breakfast">After Breakfast</option>
						    <option value="Before Breakfast">Before Breakfast</option>
						    <option value="Bed Rest">Bed Rest</option>
						   <option value="Local application">Local application</option>
						</datalist>
                        
                        <!-- Attribute style="height:34px;" added by Timsi because of class name change for parent div.-->
                      </div> 
                      <div class="col-md-1 prescriptionTileRowsCol" style="padding-top: 2vh;">  
                        <button style="margin-top: 2.8em;" class="btn btn-sm btn-info drugsAdviceAdd" type="button">Add</button>
                      </div>
                    </div>  
                    <br>
                    <div class="clearfix visible-xs visible-sm" style="margin-top:10px;"></div>
                   <button id="externalDrugBtn" class="btn btn-info" type="button" onclick="if($('#allergiesModal').is(':visible')){$('#allergiesAddBtn').click();}
                          $('#externalDrugInput').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus');"><i class="fa fa-plus"></i> External Drug</button>
                    <button id="allergiesAddBtn" class="btn btn-info" type="button" onclick="if($('#externalDrugInput').is(':visible')){$('#externalDrugBtn').click();}
                    $('#allergiesModal').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus');"><i class="fa fa-plus"></i> Allergies</button>
					 
      
    
      
    <!--  <button type="button" class="btn btn-info" data-toggle="modal" data-target="CimsIntegrationId" onclick="loadDoc()">
   CIMS Integration
  </button> -->

  <!-- The Modal -->
  <div class="modal fade" id="CimsIntegrationId">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h3 class="modal-title text-left">CIMS Drug Health Alert</h3>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div id="CimsResponse"> </div>
        </div>
        
        
      </div>
    </div>
  </div>
  
  
  
  
  
  
 <!--  <button type="button" class="btn btn-info" data-toggle="modal" data-target="CimsMonographId" onclick="MonographDoc()">
   Drug Monograph
  </button> -->

  <!-- The Modal -->
  <div class="modal fade" id="CimsMonographId">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h3 class="modal-title text-left">CIMS Drug Monogram</h3>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div id="MonographResponse"> </div>
        </div>
        
        
      </div>
    </div>
  </div>
            
            
            
     
            
           

  <!-- The Modal -->
  <div class="modal fade" id="CimsAlleryId">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
         <h3 class="modal-title text-left">CIMS Drug Interaction Alert</h3>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div id="AllergyResponse"> </div>
        </div>
        
        
      </div>
    </div>
  </div> 
  
 
 
  
  
  <!-- The Modal -->
  <div class="modal fade" id="GenericMonogramId">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
         <h3 class="modal-title text-left">SNOMED-CT Drug Monogram</h3>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div id="GenericMonogramResponse"> </div>    
        </div>     
      </div>
    </div>
  </div>
  
  
   
  <button type="button" class="btn btn-info"  data-toggle="modal" data-target="BrandMonogramId" onclick="BrandMonogramDoc()" style="display:none;">
  Brand Monogram
  </button> 
  
  
   <!-- The Modal -->
  <div class="modal fade" id="BrandMonogramId">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
         <h4 class="modal-title text-left">Brand Monogram</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div id="BrandMonogramResponse"> </div>    
        </div>     
      </div>
    </div>
  </div>
                    <br>
                    
                    <div class="clearfix visible-xs visible-sm" style="margin-top:10px;"></div>
                      <div  id="allergiesModal" style="display:none;">
                      <br>
                      <div class="table-responsive">
                          <table id="allergiesDtlListTable" class="table table-condensed table-hover" style="margin-bottom: 0px;"> 
                            <thead>
                              <tr style="background: linear-gradient(to bottom, #dbdbdb /* #acd6ff */, white);">
                                <th><input type="checkbox" class="allergiesDtlChkAll" name="allergiesDtlChkAll"></th><th>Allergy Name</th><th>Sensitivity</th><th>Duration(yrs)</th><th>Symptoms</th><th>Allergy Site</th><th>Advice</th><th>Action</th>
                              </tr>
                            </thead>
                            <tbody>
                             <!--  <tr> 
                                <td><input type="checkbox" class="allergiesDtlChkAll" name="allergiesDtlChk" value="91935009"></td>
                                <td>Allergy to Peanuts</td>
                                <td>Type 1</td>
                                <td>1</td>
                                <td>Swollen Face</td>
                                <td>Face</td>
                                <td><a class="allergiesDtlInstructionsModalBtn" style="color: #109f1c" allergyinstructions="Avoid Peanuts" onclick="$('#allergiesDtlInstructionsModal .modal-body p').text($(this).attr('allergyInstructions'));$('#allergiesDtlInstructionsModal').modal('show');">Avoi..</a></td>
                                <td><button class="btn btn-sm btn-danger allergiesDtlRemoveRow" onclick="$(this).parent().parent().remove();" id="removeBtnId2">Remove</button></td>
                              </tr> -->
                            </tbody> 
                        </table>
                      </div>

                      <br>

                      <div class="row">
                        <div class="form-group col-md-2">
                          <p style="letter-spacing:0"><label>Allergy Name</label></p>
                          <div class="input-group">
                            <input type="text" placeholder="Allergy Name" allergynamecode="" class="form-control clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_VR4" id="txt-snomed-ct-search_VR4" onclick="load_UNIVD4('VR4','SUBSTANCE','')" autocomplete="on">
                            <div class="input-group-btn">
                              <button class="btn btn-default allergyNameCleanBtn" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                            </div>
                          </div>
                        </div>
                        <div class="form-group col-md-2">
                            <p style="letter-spacing:0"><label>Sensitivity</label></p>
                            <select class="form-control" name="allergiesSensitivityCode" id="allergiesSensitivityCodeId">
                              <option value="-1" selected="selected">Select Value</option>    
                              <option value="11">Type 1</option>
                              <option value="12">Type 2</option>
                              <option value="13">Type 3</option>
                              <option value="14">Type 4</option>
                              <option value="15">Type 5</option>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                          <p style="letter-spacing:0"><label>Duration(yrs)</label></p>
                          <input type="text" class="form-control" placeholder="No." name="allergiesDuration" id="allergiesDurationId" onkeypress="return isNumber(event)">
                        </div>
                        <div class="form-group col-md-2">
                            <p style="letter-spacing:0"><label>Allergy Side</label></p>
                            <div class="input-group">
                              <input type="text" placeholder="Side" allergysitecode="" class="form-control clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_VR5" id="txt-snomed-ct-search_VR5" autocomplete="on" onclick="load_UNIVD5('VR5','','')">
                              <div class="input-group-btn">
                                <button class="btn btn-default allergySiteCleanBtn" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                              </div>
                            </div>
                        </div>
                        <div class="form-group col-md-2">
                          <p style="letter-spacing:0"><label>Symptoms</label></p>
                          <div class="input-group">
                            <input type="text" placeholder="Symptoms" allergysymptomscode="" class="form-control clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_VR6" id="txt-snomed-ct-search_VR6" autocomplete="on" onclick="load_UNIVD6('VR6','','')">
                            <div class="input-group-btn">
                              <button class="btn btn-default allergySymptomsCleanBtn" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                            </div>
                          </div>
                        </div>
                        <div class="form-group col-md-1">
                          <p style="letter-spacing:0"><label>Remarks</label></p>
                          <textarea class="form-control" name="allergiesDtlRemarks" id="allergiesDtlRemarksId" rows="1" style="height:34px;" placeholder="Remarks"></textarea>
                        </div>
                        <div class="form-group col-md-1">
                        	<p style="letter-spacing:0"><label></label></p>
                          <button class="btn btn-sm btn-info allergiesDtlAddRows" type="button">Add</button>
                        </div>
                      </div> 

                      <div class="row">
                        <div class="col-xs-12 col-md-3 col-sm-5">
                          <label for="otherAllergies" style="color:rgba(75,75,75, 0.7);"><b>Other Allergies:</b></label>
                        </div>
                        <div class="form-group col-xs-12 col-md-11 col-sm-12">
                          <textarea class="form-control" id="otherAllergiesId" name="otherAllergies" maxlength="2000"></textarea>
                        </div>
                      </div>  
                    </div>
                    
                    <div class="row" id="externalDrugInput" style="display:none;">
                      <div class="col-md-4">
                        <p style="letter-spacing:0"><label>Drug Name<sup style="color:red;">*</sup></label></p>
                        <div class="input-group">
                        	<input type="text" placeholder="External Drug Name" class="form-control" name="externalDrugName" id="externalDrugNameId" value="">
                        	<div class="input-group-btn">
							  <button class="btn btn-default extDrugNameCleanBtn" onclick="$('input[name=externalDrugName]').val('');" style="z-index:0;" type="button"><i class="glyphicon glyphicon-remove"></i></button>
							</div>
                        </div>   
                      </div>
                        <div class="col-md-1 rmLeftPaddInInput">
                        <p style="letter-spacing:0"><label>Dosages</label></p>
                        <select class="form-control" name="externalDrugDosage" autocomplete="off">
                        <option value="0">Select</option>
                        	<%
		                       	for(Map.Entry m2:map2.entrySet()){ 
									list2=new  ArrayList<String>();
							  // System.out.println("NEWWWWWWWWW\n"+m.getKey()+" "+m.getValue());
							   String DosageName=(String)m2.getKey();
							   //System.out.println("List Value::::::"+DosageName);
							   list2=(ArrayList) m2.getValue(); 
							   
							   %>
		            
			                  <option value="<%=DosageName%>"><%=list2.get(1) %></option> 
			                  <%
			                  }
	                 			 %>
                        </select>
                        <%-- <input type="text" placeholder="" list="externalDrugDosageLst" class="form-control" name="externalDrugDosage" value="" autocomplete="off">  
                        <datalist id="externalDrugDosageLst">
	                       	<%
	                       	for(Map.Entry m2:map2.entrySet()){ 
								list2=new  ArrayList<String>();
						  // System.out.println("NEWWWWWWWWW\n"+m.getKey()+" "+m.getValue());
						   String DosageName=(String)m2.getKey();
						   //System.out.println("List Value::::::"+DosageName);
						   list2=(ArrayList) m2.getValue(); 
						   
						   %>
	            
		                  <option id="<%=DosageName%>" value="<%=list2.get(1) %>"></option> 
		                  <%
		                  }
                 			 %>
                        </datalist>  --%>
                      </div>
                      <div class="col-md-1 rmLeftPaddInInput">
                        <p style="letter-spacing:0"><label>Frequency</label></p>
                        <select class="form-control" name="externalDrugFrequency"> 
                        	<option value="0">Select</option>
							<option value="11">OD(1-0-0)</option>
							<option value="12">BD(1-0-1)</option>
							<option value="13">TDS(1-1-1)</option>
							<option value="14">SOS(As per Requirement)</option>
							<option value="15">QID(1-1-1-1)</option>
							<option value="16">HS(At Bed Time)</option>
							<option value="18">QW(Once a Week)</option>
							<option value="11">OD(0-1-0)</option> 
							<option value="12">BD(0-1-1)</option>
                        </select> 
                      </div> 
                      <div class="col-md-2">
                        <p style="letter-spacing:0"><label>Start Date</label></p>
                        <input type="date" class="form-control"  id="externalDrugStartDate" name="externalDrugStartDate" style="line-height: normal;">
                      </div>
                      <div class="col-md-1">
                        <p style="letter-spacing:0"><label>Days</label></p>
                        <input type="text" class="form-control" onkeypress="return isNumber(event)" name="externalDrugDays" value="">
                      </div>
                      <div class="col-md-1">
                        <p style="letter-spacing:0"><label>Qua.</label></p>
                        <input type="text" class="form-control" onkeypress="return isNumber(event)" name="externalDrugQuantity"  value="">
                      </div>
                      <div class="col-md-1">
                        <p style="letter-spacing:0"><label>Instructions</label></p>
                        
                        <!-- <textarea class="form-control flexdatalist flexdatalist-set" name="externalDrugInstructions" placeholder='Remarks'  list='extdrugInstructions'  style="height:34px;"></textarea> -->
                        
                        <input type='text'  class="form-control flexdatalist flexdatalist-set"  autocomplete="off"   placeholder='Remarks'  list='extdrugInstructions' name='externalDrugInstructions'>
                        
                        
                        <datalist id="extdrugInstructions">
						    <option value="After Food">After Food</option>
						    <option value="Before Food">Before Food</option>
						    <option value="After Breakfast">After Breakfast</option>
						    <option value="Before Breakfast">Before Breakfast</option>
						    <option value="Bed Rest">Bed Rest</option>
						    <option value="Local application">Local application</option>
						</datalist>
                        
                      </div> 
                      <div class="col-md-1 prescriptionTileRowsCol" style="padding-top: 2vh;">  
                        <button style="margin-top: 2.1em;" class="btn btn-sm btn-info externalDrugsAdviceAdd" id="externalDrugsAdviceAddId" type="button">Add</button>
                      </div>
                    </div>
                    <!-- -->
                    <br>
                    
                    <!-- -->
                  </div>
                  
                  
                  
                  
                   <div class="clearfix visible-xs"></div>
                   
                   <div class="prescriptionTile" style="display: none;">
                   
                    <div class="row">
                    <div class="col-sm-10 col-md-11 prescriptionTileRowFirstCol">
                        <p class="refferalAdded" onclick="$('#referDept').slideToggle();$(this).find('i').toggleClass('fa-plus fa-minus').css({'color': '#2196F3',});"
                        ><i class="fa fa-plus" style="color:#2196F3 "></i>&nbsp;<b>Refer to another department :</b> 
                        <a style="text-decoration: none;" class="clearLnk"><img style="width: 22px;" alt="" src="/HIS/hisglobal/drDeskAssets/img/clear3.png"></a>
                        <button class="btn btn-xs btn-danger clearAllValues" type="button" data-toggle="tooltip" title="" onclick="$(this).parent().find('label').remove();" style="background-color: white;border: 0px;" data-original-title="Clear All"><span class="glyphicon glyphicon-trash" style="color: red;"></span></button> 
                       
                        
                        </p>
                      </div>
                    </div>
                   
                   <div class="row" id="referDept" style="display: none;">
                 <!--  <div class="col-xs-1 col-sm-1">
                          <p><label><b>Dept:</b></label></p>  
                        </div> -->
                        
                       <!--  <div class="col-sm-2" align="right"> -->
                          <!-- <div class="col-xs-4 col-sm-2" style="padding: 0 1px">
                           <label style="color:rgba(75,75,75, 0.7); letter-spacing:0.5; font-size:14px">Department</label>
                          </div> -->
                          <!--  </div> --> 
                            <!-- <div class="col-sm-8"> -->
                          <div class="col-xs-3 col-sm-3" >
                          
                          
							  <select class="form-control"  id="refferlPatientDeptId" name="refferlPatientDept" >
                             <option value="0#0#0#0#0#0#0#0">Select Department</option>
                        	<%
                        	HashMap<String ,List> map6 = (HashMap) request.getSession().getAttribute("ReffralDeptCmb");
		                      if(map6!=null && map6.size()>0){
                        	for(Map.Entry m6:map6.entrySet()){ 
								
                        		 String reffDeptCode=(String)m6.getKey();
                        		 String reffDeptName=(String)m6.getValue();
							   
							   %>
		            
			                  <option value="<%=reffDeptCode%>"><%=reffDeptName %></option> 
			                  
			                  <%
			                  }
                        	
                        	%>
                        	  <option value="100#100#1000#0#0##-1#">Other/External</option>
                        	   <%
		                      }else{
		                    	  
		                    	  %>
		      		            
				                  <option value="0#0#0#0#0#0#0#0">Select Department</option> 
				                  <%
		                    	  
		                      }
	                 			 %>
                        </select>
                            
                           
                            
                          </div>  
                          
                           <div class="col-xs-2 col-sm-2" style="padding: 0 1px">
                           <select class="form-control" name="referralType" id="referralTypeId" >
                           <option value="0">Refer Type</option>
                           <option value="1"> Routine</option>
                           <option value="2"> Emergency</option>
                           </select>
                          </div> 
                         
                        <!-- </div> -->
                        
                       <!--  <div class="col-sm-2" align="right"> -->
                          <div class="col-xs-4 col-sm-4" style="padding: 0 1px">
                           <input type="text" class="form-control" onkeypress="" name="refferalResonId"  id="refferalResonId" value="" placeholder="Enter Referral Reason">
                          </div>
                          
                          <div class="col-xs-3 col-sm-1" style="padding-right: 0px; text-align: center;">
							<button type="button" style="padding: 0px;color:#3696e9" class="btn btn-link progressNoteMacroBtn" id="refferalResonIdDiv" value="progressNoteMacroModal5Div" onclick="$('#progressNoteMacroModal5').modal('show');" > <!--  onclick="$('#progressNoteMacroModal').modal('show');" <i class="material-icons" style="font-size: 1.5em">message</i> --><i class="fa fa-comments" style="font-size: 1.5em"></i></button>
							</div>
							
							<div class="col-md-1 prescriptionTileRowsCol" style="padding-top: 2vh;">  
                        <button style="margin-top: -0.5em;" class="btn btn-sm btn-info refferaladdBtn" type="button">Add</button>
                      </div>
                  <!--  </div> -->
                   
                   </div>
                  
                  
                  </div>
                  
                  
                  
                  
                  
                  
                  <div class="clearfix visible-xs"></div>
                  <div class="prescriptionTile" style="/* padding:1% 3% 3% 3% */">
                  	 <button type="button" class="btn btn-info btn-sm followUpDocumentUpload" style="float:right; background-color:#517fa4; border:0px;display: none;" onclick="$('#followUpDocUploadModal').modal('show');">Upload Document</button>
                     <br><br>
                    
                     <div class="row">
                     <div class="col-sm-2">
                     <p><label>Date of Visit</label></p>
                     </div>
                     <div class="col-sm-2">
                     <input class="form-control" type="date" style="line-height: normal;" name="followUpPlannedVisitDate">
                     </div>
                     <div class="col-sm-2"></div>
                     <div class="col-sm-2">
                     <p><label>Time of Visit</label></p>
                     </div>
                     <div class="col-sm-4">
                     <div class="row">
                     <div class="col-sm-3">
                     <input type="text" class="form-control" name="VisitTimeHr" maxlength="2" size="2" tabindex="1" value="00">
					</div>
					<div class="col-sm-1"><p style="float: inline-end;">Hr:</p></div>
                     <div class="col-sm-3">
                     <input type="text" class="form-control" name="VisitTimeMin" maxlength="2" size="2" tabindex="1" value="00" > 
                      </div>
                     <div class="col-sm-1"><p style="float: inline-end;">Min</p></div>
                     <div class="col-sm-4"></div>
                     </div>
                     
					</div>
                     </div>
                     
                      
                      <div class="clearfix" style="padding:5px 0"></div>
                      <div class="row">
                        <div class="col-sm-2">
                          <p><label>Visit Notes</label></p>
                        </div>
                        <div class="col-xs-3 col-sm-1" style="padding-right: 0px; text-align: center;">
                          <button type="button" style="padding: 0px;color:#3696e9" class="btn btn-link progressNoteMacroBtn" id="" value="progressNoteMacroModal6Div" onclick="$('#progressNoteMacroModal6').modal('show');" ><!-- <i class="material-icons" style="font-size: 1.5em">message</i> --><i class="fa fa-comments" style="font-size: 1.5em"></i></button>
                        </div>
                        <div class="col-xs-9 col-sm-9">
                          <textarea class="form-control" id="visitNote" name="visitNote" rows="2" maxlength="500" placeholder="Enter Visit Notes"  tabindex="13"></textarea> 
						  <p class="no-browser-support" style="display: none;">Browser Doesn't Support Speech API (Use Google Chrome)</p> 
                        </div>
                        <div class="clearfix visible-xs-block" style="padding:5px;"></div>
                      </div>
                      <div class="clearfix" style="padding:5px 0"></div>
                      <div class="row">
                        <div class="col-sm-2">
                          <p><label>Progress Notes</label></p>
                        </div>
                        <div class="col-xs-3 col-sm-1" style="padding-right: 0px; text-align: center;">
                          <button type="button" style="padding: 0px;color:#3696e9" class="btn btn-link progressNoteMacroBtn" id="progressNoteDiv" value="progressNoteMacroModal6Div" onclick="$('#progressNoteMacroModal6').modal('show');" ><!-- <i class="material-icons" style="font-size: 1.5em">message</i> --><i class="fa fa-comments" style="font-size: 1.5em"></i></button>
                        </div>
                        <div class="col-xs-9 col-sm-9">
                          <textarea class="form-control" id="progressNote" name="progressNote" rows="2" maxlength="500" placeholder="Enter Progress Notes"  tabindex="13"></textarea> 
						  <p class="no-browser-support" style="display: none;">Browser Doesn't Support Speech API (Use Google Chrome)</p> 
                        </div>
                        <div class="clearfix visible-xs-block" style="padding:5px;"></div>
                      </div>
                      <div class="clearfix" style="padding:5px 0"></div>
                      <div class="row">
                        <div class="col-sm-2">
                          <p><label for="treatmentAdvice">Instructions/Treatment</label></p>
                        </div>
                        <div class="col-xs-3 col-sm-1" style="padding-right: 0px; text-align: center;">
                          <button type="button" style="padding: 0px;color:#3696e9" class="btn btn-link progressNoteMacroBtn" id="treatmentAdviceIdDiv" value="progressNoteMacroModal4Div" onclick="$('#progressNoteMacroModal4').modal('show');"> <!--  onclick="$('#progressNoteMacroModal').modal('show');" <i class="material-icons" style="font-size: 1.5em">message</i> --><i class="fa fa-comments" style="font-size: 1.5em"></i></button>
                        </div>
                        <div class="col-xs-9 col-sm-9">
                          <textarea class="form-control" id="treatmentAdviceId" name="treatmentAdvice" rows="2" maxlength="500" placeholder="Enter Instruction/Treatment Advice"  tabindex="13"></textarea>
						  <p class="no-browser-support" style="display: none;">Browser Doesn't Support Speech API (Use Google Chrome)</p> 
                        </div>
                        <div class="clearfix visible-xs-block" style="padding:5px;"></div>
                      </div>  
                      
                        <br>
                      <div class="row">
                        <div class="col-sm-9 col-sm-offset-3">
                          <div class="row">
                            <div class="col-md-12 col-sm-8 text-center">
                         <!--   	<div class="col-md-8 col-md-8 text-center">
                            	<a class="prescPrintBtn1" style="color: grey; text-decoration: none;color: #2796d2;" onclick="Save('printSave',this ,0)" tabindex="14"><img src="/HIS/hisglobal/drDeskAssets/img/print_icon.png" style="width:130px" /></a>
                            	<a type="button"  class="prescPrintBtn" style="color: grey; text-decoration: none;color: #2796d2;"   data-toggle="modal" ><img src="/HIS/hisglobal/drDeskAssets/img/print_icon1.png" style="width:130px;margin-bottom: 16px;" /></a> 
                            	</div>   -->  
                            	
                            	<button type="button" class="btn btn-primary" onclick="Save('printSave',this , 0)">Skip Bookmark & Preview Rx</button>
                            	<a  class="prescPrintBtn" data-toggle="modal" ><button class="btn btn-primary">Bookmark this Rx</button></a> 
                            	<br><br><br><br><br><br>
                            	
                              <!-- 	<div class="col-xs-6 col-sm-6 text-center">
		                        	<a class="patRefBtn" style="color: grey; text-decoration: none;" onclick="openRefPage(this);"><img src="/HIS/hisglobal/drDeskAssets/img/patReferal.png" style="width:90px" />&nbsp;Patient Referal</a>
		                        </div> -->
                            </div>
                          </div>
                        </div>
                      </div>
                      <!-- <p class="text-right"><button type="button" class="btn btn-primary btn-sm savePrintPrescBtn">Save/Print</button></p>  -->
                  </div>
<script>
function endTreatmentFun(e){
	if(e.value=='0')
	{
		$('input[name=endTreatment]').val('1');
			$('.followUpPlannedVisit').show();
		}
	else
	{
		$('input[name=endTreatment]').val('0');
		$('.followUpPlannedVisit').hide();
		}
	}
</script>
                </div>
                 <div class="col-sm-3 col-md-3 patSummaryTile" style="display:none; padding: 0px; background: url('/HISDRDESK/new_opd/img/bg2.png') no-repeat; background-size: cover;margin: 2% 0 0 0; min-height: 618px;">
                  <img id="patSummaryTileImg" src="/HISDRDESK/new_opd/img/img_avatar.png" class="img-circle img-responsive" style="width: 70px; float: right;">
                  <h2 style="margin-top: 0px;margin-bottom: 20px;font-family: 'Cormorant Upright', serif;">Patient Summary</h2>
                  <h5 style="margin: 0px;">Patient Name : <font id="patSummaryNameGenAgeCat"></font></h5>
                    <p style="font-weight: normal;"></p>
                    <br>
                  <h5 style="margin: 0px;">CRN : <font id="patSummaryCRN"></font></h5>
                    <p style="font-weight: normal;"></p>
                    <br> 
                  <h5 style="margin: 0px;">Current Visit No : <font id="patEpisodeVisitNoPrescriptionPanel"></font> (<font id="patVisitTypePrescriptionPanel"></font>)</h5>
                    <p style="font-weight: normal;"></p>
                    <br> 
                  <h5 style="margin: 0px;">Last Visited On : <font id="patLastVisitDatePrescriptionPanel"></font></h5>
                    <p style="font-weight: normal;"></p>
                    <br>
                   <h5 style="margin: 0px;">Vital Signs : <font id="patVitalSingsDatePrescriptionPanel"></font></h5>
                    <p style="font-weight: normal;"> </p>
                    <br>
                  <h5 style="margin: 0px;">Current Medication : <font id="patMedicineDatePrescriptionPanel"></font></h5>
                    <p style="font-weight: normal;"></p>
                    <br>  
                    <h5 style="margin: 0px;">Recent Lab Test :<font id="patRecentLabTestPrescriptionPanel"></font></h5>
                    <p style="font-weight: normal;"></p>
                    <br> 
                    <!-- <button class="btn btn-info prescImgFixedTriggerBtn" onclick="prescImgFixedTriggerFun('0')" type="button">page</button> -->
<!--                     <p><button type="button" style="padding-left:0;" class="btn btn-link patPrescView" onclick="$('#patPrescViewModalTriggerBtn').click();">Prescription View</button></p>
                    <div id="tree"></div> -->
                    
                </div> 
                <!-- <div class="col-xs-0 col-sm-0 col-md-1"></div> -->
              </div>   
          </div>
        </div> 
        
        
        <!-- <div class="modal fade" id="allergiesModal" role="dialog" data-backdrop="static" data-keyboard="false">
          <div class="modal-dialog modal-lg" style="width:80vw;">       Modal content
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">x</button>
                  <h2 class="modal-title text-left">Add Allergies</h2>
              </div>
              <div class="modal-body" style="text-align: left;"> 
                <div class="table-responsive">
                  <table id="allergiesDtlListTable" class="table table-condensed table-hover" style="margin-bottom: 0px;"> 
                    <thead>
                      <tr style="background: linear-gradient(to bottom, #dbdbdb /* #acd6ff */, white);">
                        <th><input type="checkbox" class="allergiesDtlChkAll" name="allergiesDtlChkAll"></th><th>Allergy Name</th><th>Sensitivity</th><th>Duration(yrs)</th><th>Symptoms</th><th>Allergy Site</th><th>Advice</th><th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr> 
                        <td><input type="checkbox" class="allergiesDtlChkAll" name="allergiesDtlChk" value="91935009"></td>
                        <td>Allergy to Peanuts</td>
                        <td>Type 1</td>
                        <td>1</td>
                        <td>Swollen Face</td>
                        <td>Face</td>
                        <td><a class="allergiesDtlInstructionsModalBtn" style="color: #109f1c" allergyInstructions="Avoid Peanuts" onclick="$('#allergiesDtlInstructionsModal .modal-body p').text($(this).attr('allergyInstructions'));$('#allergiesDtlInstructionsModal').modal('show');">Avoi..</a></td>
                        <td><button class="btn btn-sm btn-danger allergiesDtlRemoveRow" onclick="$(this).parent().parent().remove();" id="removeBtnId2">Remove</button></td>
                      </tr>
                    </tbody>
                </table>
              </div>

              <br>

              <div class="row">
                <div class="form-group col-md-2">
                  <p style="letter-spacing:0"><label>Allergy Name</label></p>
                  <div class="input-group">
                
                    <input type="text" placeholder="Chief Complaint" allergyNameCode="" class="form-control clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_VR4" id="txt-snomed-ct-search_VR4" autocomplete="on" onclick="load_UNIVD4('VR4','','450970008')" >
                    <div class="input-group-btn">
                      <button class="btn btn-default allergyNameCleanBtn" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                    </div>
                    <div class="concept" id="conceptdiv_4"></div>
				  <input type="hidden" name="targetId4"> 
                  </div>
                  
                </div>
                <div class="form-group col-md-2">
                    <p style="letter-spacing:0"><label>Sensitivity</label></p>
                    <select tabindex="1" class="form-control" name="allergiesSensitivityCode" id="allergiesSensitivityCodeId">
                      <option value="-1" selected="selected">Select Value</option>    
                      <option value="11">Type 1</option>
                      <option value="12">Type 2</option>
                      <option value="13">Type 3</option>
                      <option value="14">Type 4</option>
                      <option value="15">Type 5</option>
                    </select>
                </div>
                <div class="form-group col-md-2">
                  <p style="letter-spacing:0"><label>Duration(yrs)</label></p>
                  <input type="text" class="form-control" placeholder="No." name="allergiesDuration" id="allergiesDurationId" onkeypress="return isNumber(event)">
                </div>
                <div class="form-group col-md-2">
                    <p style="letter-spacing:0"><label>Allergy Site</label></p>
                    <div class="input-group">
                      <input type="text" placeholder="Site" allergySiteCode="" class="form-control clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_VR5" id="txt-snomed-ct-search_VR5" autocomplete="on">
                      <div class="input-group-btn">
                        <button class="btn btn-default allergySiteCleanBtn" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                      </div>
                    </div>
                </div>
                <div class="form-group col-md-2">
                  <p style="letter-spacing:0"><label>Sytmptoms</label></p>
                  <div class="input-group">
                    <input type="text" placeholder="Symptoms" allergySymptomsCode="" class="form-control clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_VR6" id="txt-snomed-ct-search_VR6" autocomplete="on">
                    <div class="input-group-btn">
                      <button class="btn btn-default allergySymptomsCleanBtn" type="button"><i class="glyphicon glyphicon-remove"></i></button>
                    </div>
                  </div>
                </div>
                <div class="form-group col-md-2">
                  <p style="letter-spacing:0"><label>Remarks</label></p>
                  <textarea class="form-control" name="allergiesDtlRemarks" id="allergiesDtlRemarksId" rows="1" style="height:34px;" placeholder="Remarks"></textarea>
                </div>
                <div class="form-group col-md-1">
                  <button class="btn btn-sm btn-info allergiesDtlAddRows">Add</button>
                </div>
              </div> 

              <div class="row">
                <div class="col-xs-12 col-md-3 col-sm-5">
                  <label for="otherAllergies"><b>Other Allergies:</b></label>
                </div>
                <div class="form-group col-xs-12 col-md-12 col-sm-12">
                  <textarea class="form-control" id="otherAllergiesId" name="otherAllergies" maxlength="2000"></textarea>
                </div>
              </div>   

            </div>  
          </div> 
			
            </div>  
              </div>  -->
              
              
         
          
<!-- Added by Timsi for Patient Referal Modal******************************************************* -->        
<div class="modal fade" id="patreferal" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" onclick="localStorage.setItem('refWindowFlag','0');ChangePatRefFlag(this);">&times;</button>
          <h4 class="modal-title" style="font-weight:bold">PATIENT REFERAL</h4>
        </div>
        <div class="modal-body">
          <!-- <div class="text-center">
       	  	<button type="button" class="btn btn-warning" onclick="$('#patreferal').modal('hide');$('#patreferalFrameId').remove();localStorage.setItem('refWindowFlag','0');">Cancel</button>
		  </div> -->
        </div>
        <!-- <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div> -->
      </div>
      
    </div>
  </div>

<!-- ******************************************************* -->
       		<!-- <div id="patreferal" class="tab-pane fade">
	          	<input type='button' onclick='openRefPage(this);'>
            </div>  -->
           
          
        </div>
 

      <input type="hidden" name="patGeneralDtl" id="patGeneralDtl" /> 
      <input type="hidden" name="patCompleteGeneralDtlPrescriptionPanel" id="patCompleteGeneralDtlPrescriptionPanel" />  
      <input type="hidden" name="eTeleconsultancyreq" id="eTeleconsultancyreqId" />  
        <input type="hidden" name="strHospitalAddres" value="<%=request.getSession().getAttribute("HOSPITAL_ADDRESS").toString() %>"/>
		<input type="hidden" name="strHospitalName" value="<%=request.getSession().getAttribute("HOSPITAL_NAME").toString() %>"/>
		<input type="hidden" name="strConsultantName" value="<%=request.getSession().getAttribute("UserName").toString() %>"/>
			 
			 
<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#progressNote').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#progressNote'); 

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
	noteContent += transcript+'. ';
	noteTextarea.val(noteTextarea.val()+" "+noteContent);
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


$('#progressNote').on('blur', function(e) {
if(recognition!=null & recognition!='')
recognition.stop(); 
});

}
catch(e) {
  console.error(e);
  $('.no-browser-support').show(); 
}


/*
$(document).ready(function(){
	var dtVal = new Date(); 
 	var curDate = dtVal.getDate();
 	curDate = curDate.toString().length>1 ? curDate : "0"+curDate;
 	var curMon = dtVal.getMonth();
 	//curMon = curMon.toString().length>1 ? curMon : "0"+curMon;
 	//alert(curDate+'------: '+curMon);

 	var months = new Array(12);
 	months[0] = "January";
 	months[1] = "February";
 	months[2] = "March";
 	months[3] = "April";
 	months[4] = "May";
 	months[5] = "June";
 	months[6] = "July";
 	months[7] = "August";
 	months[8] = "September";
 	months[9] = "October";
 	months[10] = "November";
 	months[11] = "December";

 	var maxDate = curDate+" "+months[curMon] +" "+dtVal.getFullYear();

 	//alert(months[curMon]);
 	//alert("maxDate---> "+maxDate.toString());
 	//alert("listdatefilter val --> "+$('#listDateFilter').val());
 	
 	if($('#listDateFilter').val() == maxDate.toString())
 	{
 		if($('#patreferal').find('iframe').length == 0)
 		{
 			window.patCrNo = $('#patCrNoPrescriptionPanel').text();
 			var targetURL= "/HISRegistration/registration/transactions/NEWDRDESKPatientReferral.action?varSSOTicketGrantingTicket=" + parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;

 			$('#patreferal .modal-body').prepend("<iframe  id='patreferalFrameId' src='"+targetURL+"' style='width:100%;height:100vh;border:0px solid grey;'></iframe>");
 			localStorage.setItem('refWindowFlag','0');
 			//$('#patreferal').modal('show');
 			//window.open(targetURL);
 			// <iframe style="width:100%; height:100%; border:0px solid grey;" src="/HISRegistration/registration/transactions/PatientReferral.action"></iframe>
 		}
 	 }
 	 else
 	{ 
 	 	swal('Patient did not visit today and hence cannot be referred.').then(function(){
 	 		$('#patreferal').find('.modal-footer button:first-child').click();
	 	});
 	 } 
		
});
*/

function openRefPage(e)
{
	var dtVal = new Date(); 
 	var curDate = dtVal.getDate();
 	curDate = curDate.toString().length>1 ? curDate : "0"+curDate;
 	var curMon = dtVal.getMonth();
 	//curMon = curMon.toString().length>1 ? curMon : "0"+curMon;
 	//alert(curDate+'------: '+curMon);

 	var months = new Array(12);
 	months[0] = "January";
 	months[1] = "February";
 	months[2] = "March";
 	months[3] = "April";
 	months[4] = "May";
 	months[5] = "June";
 	months[6] = "July";
 	months[7] = "August";
 	months[8] = "September";
 	months[9] = "October";
 	months[10] = "November";
 	months[11] = "December";

 	var maxDate = curDate+" "+months[curMon] +" "+dtVal.getFullYear();

 	$('#patreferal').modal({
	  backdrop: 'static',
	  keyboard: false
	});
 	//alert(months[curMon]);
 	//alert("maxDate---> "+maxDate.toString());
 	//alert("listdatefilter val --> "+$('#listDateFilter').val());
 	if($('#listDateFilter').val() == maxDate.toString())
 	{
 	 	if($('#patreferal').find('iframe').length == 0)
 		{ 
 	 		window.patCrNo = $('#patCrNoPrescriptionPanel').text();
 			var targetURL= "/HISRegistration/registration/transactions/NEWDRDESKPatientReferral.action?varSSOTicketGrantingTicket=" + parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
 			$('#patreferal .modal-body').prepend("<iframe id='patreferalFrameId' src='"+targetURL+"' style='width:100%;height:70vh;border:0px solid grey;'></iframe>");
			$('#patreferal').modal('show');
 			//window.open(targetURL);
 			// <iframe style="width:100%; height:100%; border:0px solid grey;" src="/HISRegistration/registration/transactions/PatientReferral.action"></iframe>
 		}
 	 }
 	else
 	{ 
 		swal('Patient did not visit today and hence cannot be referred.').then(function(){
 	 	 	$('#patreferal').find('.modal-footer button:first-child').click();
	 	});
 	 }
}

function ChangePatRefFlag(e){
	var patReferedOrNotFlag=localStorage.getItem('patReferedOrNotFlag');
	if(patReferedOrNotFlag=='1'){
		window.parent.patEpisodeCode=$('#patEpisodeCodePrescriptionPanel').text();
		window.parent.patHospitalCode=$('#patHospitalCodePrescriptionPanel').text();
		window.parent.patSeatId=$('#patSeatIdPrescriptionPanel').text();
		window.parent.patCrNo=$('#patCrNoPrescriptionPanel').text();
		window.parent.patVisitNo=$('#patEpisodeVisitNoPrescriptionPanel').text();
		//alert("episode code --->> "+window.parent.patEpisodeCode+ " "+window.parent.patHospitalCode+" "+window.parent.patSeatId+" "+window.parent.patVisitNo+" "+window.parent.patCrNo);
		var patRefJSON = {
			'CrNo':window.parent.patCrNo,
			'EpisodeCode':window.parent.patEpisodeCode,
			'HospitalCode':window.parent.patHospitalCode,
			'SeatId':window.parent.patSeatId,
			'VisitNo':window.parent.patVisitNo
		};
		//var data1=JSON.parse(patRefJSON);
		var data = JSON.stringify(patRefJSON);
		console.log("patRefJSON --->> "+patRefJSON); 
		//alert(patRefJSON);
		$.ajax({url:'/HISDRDESK/services/restful/DrDesk/referPatient/',type:'POST',data:{JsonData:data},success:function(result)
	    	{
	    		console.log(":::::patient refered successfully:::::::::");
	    	}
		});
		//alert("1");
	}
}

</script> 

<style>
		#drugAdvicesInstructionsModal .modal-body p{
 			word-wrap : break-word;
		}
		#chronicDiseaseInstructionsModal .modal-body p{
		    word-wrap : break-word;
		}
		#allergiesDtlInstructionsModal .modal-body p{
		 	word-wrap : break-word;
		}
</style>


<!-- <script>
$(document).ready(function(){
	//alert('2');
	$('#refferlPatientDeptId').multiselect();
	//alert('1');
	});
</script> -->
<script src="/HIS/hisglobal/drDeskAssets/dropzone/dropzone.js"></script> 

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
	
	<style>
		@media print {
		  .tooltip { visibility: hidden; }
		}
		.opdVitalsTile{
			 font-weight:normal !important;
			 font-size:1.0em !important;
		}
		.opdVitalsLabel{
			
			 color:DodgerBlue;
		}
		.opdVitalsLabel span{
			
			 color:grey;
		}
		body{
			padding-bottom:80px; 
		} 
		#printPrescPage{
			margin-bottom:45px; 
		} 
		.investigation{
			margin:0px !important;
		}
		.investigation li{
			text-align:left !important;
		} 
		.investigation li p{
			margin:0px !important;
			font-size:13px;
		}
		.printPrescTreatmentLst{
			margin:0px !important;
		} 
		.printPrescTreatmentLst li{
			text-align:left !important;
		} 
		.printPrescTreatmentLst li p{
			margin:0px !important;
			font-size:13px;
		}
		.printPreviousPrescPage{
			color:black !important;
			margin-bottom:45px;
		}
		.printPreviousPrescPage p{
			letter-spacing: inherit !important;
			color: #5a5a5a !important;
			font-weight:600;
		}
		.printPreviousPrescPage p small{
			font-weight: 400 !important;
			font-size: 90%;
		}
		.printPrescPatDtlTbl tr td{
			border-top: 0px solid #ddd !important;
			padding: 2px 5px;
		}
		.printPrescPatDtlTbl tr th{
			border-top: 0px solid #ddd !important;
			padding: 2px 5px;
		} 
		.printPrescPatDtlTbl{ 
			font-size: 13px;
			margin:10px 0;
		}
		.printPrescTreatmentTbl{
			font-size: 13px
		}
		.printPrescTreatmentTbl tr td{
		border-top: 0px solid #ddd !important;
		}
		.printPrescTreatmentTbl tr th{
		border-top: 0px solid #ddd !important;
		border-bottom: 0px solid #ddd !important;
		}  
		
		#watermark
		{
		 position:fixed;
		 top:42%;
		 left:25%;
		 opacity:0.1;
		 font-size: 90px;
		 z-index:-1;
		 color: black;
		 transform: rotate(-20deg);
		}
		
		.footerPastPresc{visibility: hidden;position:fixed;bottom:0;left:10px;color: #7e7e7e;} 
	
		/* @media print{
			tfoot {display: table-footer-group;}
		} */
		
		 /* @media print {
		    .previousPrescPrintBtn{
		    	visibility: hidden;
		    }
		    
		    body.modal-open {
		        visibility: hidden;
		    }
		    
		    #opdEmrModal body.modal-open .modal .modal-header{
		    	visibility: hidden;
		    }		    
		    
		    body.modal-open .modal .modal-body .opdEmrModalNavMenu .active,
		    body.modal-open .modal .modal-body .opdEmrModalNavMenuContent .active{
				visibility: visible; /* make visible modal body and header */
		    }
		} */
		#opdEmrModal.modal-body p{
		    word-wrap : break-word;
		}
		#drugAdvicesInstructionsModal.modal-body p{
		    word-wrap : break-word;
		}
		#chronicDiseaseInstructionsModal.modal-body p{
		    word-wrap : break-word;
		}
		.nav-pills>li>a {
	      background-color: #7087ce; 
	      border-color: #777777;
	      color:#fff;
	    }
	    .nav-pills>li.active>a, .nav-pills>li.active>a:hover, .nav-pills>li.active>a:focus {
	      color: #fff;
	      background-color: #666;
	      border: 1px solid #888888;
	    }
	    .nav-pills>li>a:hover {
	      border-color: #2b438a;
	      background-color: #2b438a;
	    }
	    .treeStructurePresc{
			color:black !important;
		}
		.treeStructurePresc p u{
			letter-spacing: inherit !important;
			color: #6d6db7 !important;
			font-weight:600;
		}
		.treeStructurePresc p small{
			font-weight: 400 !important;
			color: black !important;
			font-size: 90%;
		}
		/* .printPreviousPrescPage{
			width: 50em !important;
		}
		.treeStructurePresc{
			width: 50em !important;
		} */
		
		.optionalOrDiv{
	      text-align:center;
	      line-height: 2.6;
	    }
	    .alignRight{
	      text-align:right;
	    }
	    .alignLeftPaddingLeftZero{
	      text-align:left;
	      padding-left:0;
	    }
	    .paddingRightZero{
	      padding-right:0;
	    }
	    .paddingLeftRightZero{
	      padding-right:0;
	      padding-left:0;
	    }
	    .tooltip{
	        position:absolute;
	        z-index:1020;
	        display:block;
	        visibility:visible;
	        padding:5px;
	        font-size:13px;
	        opacity:0;
	        filter:alpha(opacity=0)
	    }
	    #home label{
	      font-weight: 400 !important;
	    }
	    .marginBottom{
	      margin-bottom:5px;
	    }
	    @media only screen and (max-width: 992px) and (min-width: 577px){
			 #opdEmrModal .modal-dialog {
		    	width: 95vw !important;
			}
			#TreeStructurePrescriptionModal .modal-dialog {
			    width: 95vw !important;
			}
			#VitalIDModal .modal-dialog {
			    width: 95vw !important;
			}
			#shortcutKeysModal .modal-dialog {
		    	width: 95vw !important;
			}
			#patientSearchModal .modal-dialog {
		    	width: 95vw !important;
			}
		}
		
		@media only screen and (max-width: 577px){
			#TreeStructurePrescriptionModal .TreeStructurePrescriptionModalNavMenu{
				font-size: 7px !important;	
			}
			#opdEmrModal .opdEmrModalNavMenu{
				font-size: 7px !important;	
			}
			#investigationLabReportModal .investigationModalNavMenu{
				font-size: 7px !important;	
			}
		}
	    
	</style>
	
	<style>
		@media screen {
		  #printSection {
		      display: none;
		  }
		}

		@media print {
		  body * {
		    visibility:hidden;
		  }
		  #printSection, #printSection * {
		    visibility:visible;
		  }
		  #printSection {
		    position:absolute;
		    left:0;
		    top:0;
		  }
		}
		/* .zoom:hover {
  			box-shadow: 0 1px 18px 0px #bababa; 
			 -webkit-transform: translateY(-4px);
			transform: translateY(-4px); 
		} */
		.value{
			display:inline-block;
			background:#eee;
			border-radius:3px;
			color:#777;
			line-height:20px;
			margin-left:5px;
			margin-right:5px;
		}
}
	</style>
	
 <!-- <script>
		var langs =
		[['Afrikaans',       ['af-ZA']],
		 ['Bahasa Indonesia',['id-ID']],
		 ['Bahasa Melayu',   ['ms-MY']],
		 ['Català',          ['ca-ES']],
		 ['Čeština',         ['cs-CZ']],
		 ['Deutsch',         ['de-DE']],
		 ['English',         ['en-AU', 'Australia'],
		                     ['en-CA', 'Canada'],
		                     ['en-IN', 'India'],
		                     ['en-NZ', 'New Zealand'],
		                     ['en-ZA', 'South Africa'],
		                     ['en-GB', 'United Kingdom'],
		                     ['en-US', 'United States']],
		 ['Español',         ['es-AR', 'Argentina'],
		                     ['es-BO', 'Bolivia'],
		                     ['es-CL', 'Chile'],
		                     ['es-CO', 'Colombia'],
		                     ['es-CR', 'Costa Rica'],
		                     ['es-EC', 'Ecuador'],
		                     ['es-SV', 'El Salvador'],
		                     ['es-ES', 'España'],
		                     ['es-US', 'Estados Unidos'],
		                     ['es-GT', 'Guatemala'],
		                     ['es-HN', 'Honduras'],
		                     ['es-MX', 'México'],
		                     ['es-NI', 'Nicaragua'],
		                     ['es-PA', 'Panamá'],
		                     ['es-PY', 'Paraguay'],
		                     ['es-PE', 'Perú'],
		                     ['es-PR', 'Puerto Rico'],
		                     ['es-DO', 'República Dominicana'],
		                     ['es-UY', 'Uruguay'],
		                     ['es-VE', 'Venezuela']],
		 ['Euskara',         ['eu-ES']],
		 ['Français',        ['fr-FR']],
		 ['Galego',          ['gl-ES']],
		 ['Hrvatski',        ['hr_HR']],
		 ['IsiZulu',         ['zu-ZA']],
		 ['Íslenska',        ['is-IS']],
		 ['Italiano',        ['it-IT', 'Italia'],
		                     ['it-CH', 'Svizzera']],
		 ['Magyar',          ['hu-HU']],
		 ['Nederlands',      ['nl-NL']],
		 ['Norsk bokmål',    ['nb-NO']],
		 ['Polski',          ['pl-PL']],
		 ['Português',       ['pt-BR', 'Brasil'],
		                     ['pt-PT', 'Portugal']],
		 ['Română',          ['ro-RO']],
		 ['Slovenčina',      ['sk-SK']],
		 ['Suomi',           ['fi-FI']],
		 ['Svenska',         ['sv-SE']],
		 ['Türkçe',          ['tr-TR']],
		 ['български',       ['bg-BG']],
		 ['Pусский',         ['ru-RU']],
		 ['Српски',          ['sr-RS']],
		 ['한국어',            ['ko-KR']],
		 ['中文',             ['cmn-Hans-CN', '普通话 (中国大陆)'],
		                     ['cmn-Hans-HK', '普通话 (香港)'],
		                     ['cmn-Hant-TW', '中文 (台灣)'],
		                     ['yue-Hant-HK', '粵語 (香港)']],
		 ['日本語',           ['ja-JP']],
		 ['Lingua latīna',   ['la']]];
		for (var i = 0; i < langs.length; i++) {
		  select_language.options[i] = new Option(langs[i][0], i);
		}
		select_language.selectedIndex = 6;
		updateCountry();
		select_dialect.selectedIndex = 6;
		showInfo('info_start');
		function updateCountry() {
		  for (var i = select_dialect.options.length - 1; i >= 0; i--) {
		    select_dialect.remove(i);
		  }
		  var list = langs[select_language.selectedIndex];
		  for (var i = 1; i < list.length; i++) {
		    select_dialect.options.add(new Option(list[i][1], list[i][0]));
		  }
		
		  select_dialect.style.visibility = list[1].length == 1 ? 'hidden' :
		'visible';
		}
		var create_email = false;
		var final_transcript = '';
		var recognizing = false;
		var ignore_onend;
		var start_timestamp;
		if (!('webkitSpeechRecognition' in window)) {
		  upgrade();
		} else {
			if($('#start_button').data('clicked')) {
				start_button.style.display = 'inline-block';
		    }
		  //start_button.style.display = 'inline-block';
		  var recognition = new webkitSpeechRecognition();
		  recognition.continuous = true;
		  recognition.interimResults = true;
		  recognition.onstart = function() {
		    recognizing = true;
		    showInfo('info_speak_now');
		    if($('#start_button').data('clicked')) {
		    	start_img.src = '/HISDRDESK/hisglobal/images/mic-animate.gif';
		    }
		    //start_img.src = '/HISDRDESK/hisglobal/images/mic-animate.gif';
		  };
		  recognition.onerror = function(event) {
		    if (event.error == 'no-speech') {
		    	if($('#start_button').data('clicked')) {
		    		start_img.src = '/HISDRDESK/hisglobal/images/mic.gif';
			    }
		      //start_img.src = '/HISDRDESK/hisglobal/images/mic.gif';
		      showInfo('info_no_speech');
		      ignore_onend = true;
		    }
		    if (event.error == 'audio-capture') {
		    	if($('#start_button').data('clicked')) {
		    		start_img.src = '/HISDRDESK/hisglobal/images/mic.gif';
			    }
		      //start_img.src = '/HISDRDESK/hisglobal/images/mic.gif';
		      showInfo('info_no_microphone');
		      ignore_onend = true;
		    }
		    if (event.error == 'not-allowed') {
		      if (event.timeStamp - start_timestamp < 100) {
		        showInfo('info_blocked');
		      } else {
		        showInfo('info_denied');
		      }
		      ignore_onend = true;
		    }
		  };
		  recognition.onend = function() {
		    recognizing = false;
		    if (ignore_onend) {
		      return;
		    }
		    if($('#start_button').data('clicked')) {
		    	start_img.src = '/HISDRDESK/hisglobal/images/mic.gif';
		    }
		    //start_img.src = '/HISDRDESK/hisglobal/images/mic.gif';
		    if (!final_transcript) {
		      showInfo('info_start');
		      return;
		    }
		    showInfo('');
		    if (window.getSelection) {
		      window.getSelection().removeAllRanges();
		      var range = document.createRange();

		      if($('#start_button').data('clicked')) {
		    	  range.selectNode(document.getElementById('txt-snomed-ct-search_VR'));
		      }
		      
		      //range.selectNode(document.getElementById('txt-snomed-ct-search_VR'));
		      window.getSelection().addRange(range);
		    }
		    if (create_email) {
		      create_email = false;
		      createEmail();
		    }
		  };
		  recognition.onresult = function(event) {
		    var interim_transcript = '';
		    for (var i = event.resultIndex; i < event.results.length; ++i) {
		      if (event.results[i].isFinal) {
		        final_transcript += event.results[i][0].transcript;
		      } else {
		        interim_transcript += event.results[i][0].transcript;
		      }
		    }
		    final_transcript = capitalize(final_transcript);
		  //  final_span.innerHTML = linebreak(final_transcript);
		    //interim_span.innerHTML = linebreak(interim_transcript);
		    if($('#start_button').data('clicked')) {
		    	document.getElementById("txt-snomed-ct-search_VR").value = linebreak(final_transcript);
		    }
		    //document.getElementById("txt-snomed-ct-search_VR").value = linebreak(final_transcript);
		   /* if (final_transcript || interim_transcript) {
		      showButtons('inline-block');
		    } */
		  };
		}
		function upgrade() {
			if($('#start_button').data('clicked')) {
				start_button.style.visibility = 'hidden';
		    }
		  //start_button.style.visibility = 'hidden';
		  showInfo('info_upgrade');
		}
		var two_line = /\n\n/g;
		var one_line = /\n/g;
		function linebreak(s) {
		  return s.replace(two_line, '<p></p>').replace(one_line, '<br>');
		}
		var first_char = /\S/;
		function capitalize(s) {
		  return s.replace(first_char, function(m) { return m.toUpperCase(); });
		}
		
		function startButton(event) {
		  if (recognizing) {
		    recognition.stop();
		    return;
		  }
		
		
		  final_transcript = '';
		  recognition.lang = select_dialect.value;
		  recognition.start();
		  ignore_onend = false;
		 // final_span.innerHTML = '';
		  interim_span.innerHTML = '';
		  if($('#start_button').data('clicked')) {
			  start_img.src = '/HISDRDESK/hisglobal/images/mic-slash.gif';
		    }
		  //start_img.src = '/HISDRDESK/hisglobal/images/mic-slash.gif';
		  showInfo('info_allow');
		  //showButtons('none');
		  start_timestamp = event.timeStamp;
		}
		function showInfo(s) {
		  if (s) {
		    for (var child = info.firstChild; child; child = child.nextSibling) {
		      if (child.style) {
		        child.style.display = child.id == s ? 'inline' : 'none';
		      }
		    }
		    info.style.visibility = 'visible';
		  } else {
		    info.style.visibility = 'hidden';
		  }
		}
		
		var current_style;
		function showButtons(style) {
		  if (style == current_style) {
		    return;
		  }
		  current_style = style;
		  //copy_button.style.display = style;
		  //email_button.style.display = style;
		  //copy_info.style.display = 'none';
		  //email_info.style.display = 'none';
		}

</script> -->
	
<script>
	function multiSearchOr(text, searchWords){
		if(text.indexOf(searchWords)>=0){
			return true;
		}
		else{
			return false;
		}
	}
</script>

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#txt-snomed-ct-search_VR').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#txt-snomed-ct-search_VR'); 

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
	noteContent += transcript+'';
	 
	/* console.log("contains string ___>>>>> "+multiSearchOr(noteContent.trim(),"add"));
	console.log("transcript --->>>"+noteContent.trim()); 
	console.log(noteContent.trim().replace("add","")); */

	if(multiSearchOr(noteContent.trim(),"add")){
		noteContent.trim().replace("add","");
		 document.getElementById("reasonOfVisitAddId").click();
		 noteContent='';
	}
	//console.log("note textarea value before -->>"+noteTextarea.val());
	noteTextarea.val(noteTextarea.val()+noteContent.trim().replace("add",""));
	noteContent='';
	//console.log("note textarea value after -->>"+noteTextarea.val());

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


$('#txt-snomed-ct-search_VR').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#generalComplaintId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#generalComplaintId'); 

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
	console.log("contains string ___>>>>> "+multiSearchOr(noteContent.trim(),"add"));
	console.log("transcript --->>>"+noteContent.trim()); 
	console.log(noteContent.trim().replace("add",""));

	if(multiSearchOr(noteContent.trim(),"add")){
		noteContent.trim().replace("add","");
		 document.getElementById("reasonOfVisitAddId").click();
		 noteContent='';
	}
	noteTextarea.val(noteContent.trim().replace("add",""));
	noteContent='';
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


$('#generalComplaintId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#hopiId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#hopiId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#hopiId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#pastHistoryId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#pastHistoryId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#pastHistoryId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#personalHistoryId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#personalHistoryId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#personalHistoryId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#familyHistoryId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#familyHistoryId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#familyHistoryId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#treatmentHistoryId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#treatmentHistoryId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#treatmentHistoryId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#surgicalHistoryId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#surgicalHistoryId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#surgicalHistoryId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#cvsId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#cvsId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#cvsId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#rsId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#rsId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#rsId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#cnsId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#cnsId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#cnsId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#pAId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#pAId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#pAId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#otherExamnId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#otherExamnId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#otherExamnId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#txt-snomed-ct-search_VR3').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#txt-snomed-ct-search_VR3'); 

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

	if(multiSearchOr(noteContent.trim(),"add")){
		noteContent.trim().replace("add","");
		 document.getElementById("chronicDiseaseAddRowsId").click();
		 noteContent='';
	}
	
	noteTextarea.val(noteContent.trim().replace("add",""));
	noteContent='';
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


$('#txt-snomed-ct-search_VR3').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#externalDiagnosisTxtId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#externalDiagnosisTxtId'); 

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

	if(multiSearchOr(noteContent.trim(),"add")){
		noteContent.trim().replace("add","");
		 document.getElementById("diagnosisAddExtId").click();
		 this.value='';
	}
	
	noteTextarea.val(noteContent.trim().replace("add",""));
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


$('#externalDiagnosisTxtId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#diagnosisNoteId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#diagnosisNoteId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#diagnosisNoteId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#externalInvestigationId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#externalInvestigationId'); 

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

	if(multiSearchOr(noteContent.trim(),"add")){
		noteContent.trim().replace("add","");
		 document.getElementById("investigationAddId").click();
		 this.val='';
	}
	
	noteTextarea.val(noteContent.trim().replace("add",""));
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


$('#externalInvestigationId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#investigationNoteId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#investigationNoteId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
	
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


$('#investigationNoteId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#otherProceduresId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#otherProceduresId'); 

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

	if(multiSearchOr(noteContent.trim(),"add")){
		noteContent.trim().replace("add","");
		 document.getElementById("clinicalProceduresAddId").click();
		 this.val='';
	}
	
	noteTextarea.val(noteContent.trim().replace("add",""));
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


$('#otherProceduresId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#treatmentAdviceId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#treatmentAdviceId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#treatmentAdviceId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#otherAllergiesId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#otherAllergiesId'); 

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
	
	noteTextarea.val(noteTextarea.val()+" "+noteContent.trim().replace("add",""));
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


$('#otherAllergiesId').on('blur', function(e) {
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


<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#txt-snomed-ct-search_VR2').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#txt-snomed-ct-search_VR2'); 

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
	noteContent += transcript+'';
	 
	/* console.log("contains string ___>>>>> "+multiSearchOr(noteContent.trim(),"add"));
	console.log("transcript --->>>"+noteContent.trim()); 
	console.log(noteContent.trim().replace("add","")); */

	if(multiSearchOr(noteContent.trim(),"add")){
		noteContent.trim().replace("add","");
		 document.getElementById("diagnosisAddId").click();
		 noteContent='';
	}
	//console.log("note textarea value before -->>"+noteTextarea.val());
	noteTextarea.val(noteTextarea.val()+noteContent.trim().replace("add",""));
	noteContent='';
	//console.log("note textarea value after -->>"+noteTextarea.val());

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


$('#txt-snomed-ct-search_VR2').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#txt-snomed-ct-search_VR7').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#txt-snomed-ct-search_VR7'); 

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
	noteContent += transcript+'';
	 
	/* console.log("contains string ___>>>>> "+multiSearchOr(noteContent.trim(),"add"));
	console.log("transcript --->>>"+noteContent.trim()); 
	console.log(noteContent.trim().replace("add","")); */

	if(multiSearchOr(noteContent.trim(),"add")){
		noteContent.trim().replace("add","");
		 document.getElementById("clinicalProceduresAddId").click();
		 noteContent='';
	}
	//console.log("note textarea value before -->>"+noteTextarea.val());
	noteTextarea.val(noteTextarea.val()+noteContent.trim().replace("add",""));
	noteContent='';
	//console.log("note textarea value after -->>"+noteTextarea.val());

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


$('#txt-snomed-ct-search_VR7').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#txt-snomed-ct-search_VR4').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#txt-snomed-ct-search_VR4'); 

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
	noteContent += transcript+'';
	 
	/* console.log("contains string ___>>>>> "+multiSearchOr(noteContent.trim(),"add"));
	console.log("transcript --->>>"+noteContent.trim()); 
	console.log(noteContent.trim().replace("add","")); */

	if(multiSearchOr(noteContent.trim(),"add")){
		noteContent.trim().replace("add","");
		 document.getElementById("allergiesDtlAddRows").click();
		 noteContent='';
	}
	//console.log("note textarea value before -->>"+noteTextarea.val());
	noteTextarea.val(noteTextarea.val()+noteContent.trim().replace("add",""));
	noteContent='';
	//console.log("note textarea value after -->>"+noteTextarea.val());

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


$('#txt-snomed-ct-search_VR4').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#txt-snomed-ct-search_VR5').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#txt-snomed-ct-search_VR5'); 

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
	noteContent += transcript+'';
	 
	/* console.log("contains string ___>>>>> "+multiSearchOr(noteContent.trim(),"add"));
	console.log("transcript --->>>"+noteContent.trim()); 
	console.log(noteContent.trim().replace("add","")); */

	if(multiSearchOr(noteContent.trim(),"add")){
		noteContent.trim().replace("add","");
		 document.getElementById("allergiesDtlAddRows").click();
		 noteContent='';
	}
	//console.log("note textarea value before -->>"+noteTextarea.val());
	noteTextarea.val(noteTextarea.val()+noteContent.trim().replace("add",""));
	noteContent='';
	//console.log("note textarea value after -->>"+noteTextarea.val());

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


$('#txt-snomed-ct-search_VR5').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#txt-snomed-ct-search_VR6').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#txt-snomed-ct-search_VR6'); 

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
	noteContent += transcript+'';
	 
	/* console.log("contains string ___>>>>> "+multiSearchOr(noteContent.trim(),"add"));
	console.log("transcript --->>>"+noteContent.trim()); 
	console.log(noteContent.trim().replace("add","")); */

	if(multiSearchOr(noteContent.trim(),"add")){
		noteContent.trim().replace("add","");
		 document.getElementById("allergiesDtlAddRows").click();
		 noteContent='';
	}
	//console.log("note textarea value before -->>"+noteTextarea.val());
	noteTextarea.val(noteTextarea.val()+noteContent.trim().replace("add",""));
	noteContent='';
	//console.log("note textarea value after -->>"+noteTextarea.val());

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


$('#txt-snomed-ct-search_VR6').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#chronicDiseaseRemarksId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#chronicDiseaseRemarksId'); 

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
	
	noteTextarea.val(noteContent.trim().replace("add",""));
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


$('#chronicDiseaseRemarksId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#diagnosisRemarksIcdId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#diagnosisRemarksIcdId'); 

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
	
	noteTextarea.val(noteContent.trim().replace("add",""));
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


$('#diagnosisRemarksIcdId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#diagnosisRemarksId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#diagnosisRemarksId'); 

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
	
	noteTextarea.val(noteContent.trim().replace("add",""));
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


$('#diagnosisRemarksId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#clinicalProceduresRemarksId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#clinicalProceduresRemarksId'); 

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
	
	noteTextarea.val(noteContent.trim().replace("add",""));
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


$('#clinicalProceduresRemarksId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#allergiesDtlRemarksId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#allergiesDtlRemarksId'); 

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
	
	noteTextarea.val(noteContent.trim().replace("add",""));
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


$('#allergiesDtlRemarksId').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#drugInstructions').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#drugInstructions'); 

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
	
	noteTextarea.val(noteContent.trim().replace("add",""));
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


$('#drugInstructions').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#externalDrugInstructions').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#externalDrugInstructions'); 

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
	
	noteTextarea.val(noteContent.trim().replace("add",""));
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


$('#externalDrugInstructions').on('blur', function(e) {
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

<script>
try {  

/*-----------------------------
  App buttons and input 
------------------------------*/
var recognition;

$('#externalDrugNameId').on('focus', function(e) {
	var SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
	if(!SpeechRecognition)
		return false;

	  recognition = new SpeechRecognition();


	  var noteTextarea = $('#externalDrugNameId'); 

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
	noteContent += transcript+'';
	 
	/* console.log("contains string ___>>>>> "+multiSearchOr(noteContent.trim(),"add"));
	console.log("transcript --->>>"+noteContent.trim()); 
	console.log(noteContent.trim().replace("add","")); */

	if(multiSearchOr(noteContent.trim(),"add")){
		noteContent.trim().replace("add","");
		 document.getElementById("externalDrugsAdviceAddId").click();
		 noteContent='';
	}
	//console.log("note textarea value before -->>"+noteTextarea.val());
	noteTextarea.val(noteTextarea.val()+noteContent.trim().replace("add",""));
	noteContent='';
	//console.log("note textarea value after -->>"+noteTextarea.val());

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


$('#externalDrugNameId').on('blur', function(e) {
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


