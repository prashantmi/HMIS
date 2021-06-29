<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.nio.file.Files"%>
<%@page import="com.lowagie.text.PageSize"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.lowagie.text.rtf.RtfWriter2"%>
<%@page import="com.lowagie.text.pdf.PdfWriter"%>
<%@page import="com.lowagie.text.Document"%>
<%@page import="com.lowagie.text.Paragraph"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="new_investigation.vo.InvResultReportPrintingNewVO"%>
<%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>

<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>
<%@page import="new_investigation.vo.RequisitionListVO"%>
<%@page import="new_investigation.vo.OnlinePatientAcceptanceVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.transactions.controller.fb.InvResultReportPrintingNewFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
 <%@page import="java.io.File"%>
<%@page import="java.io.*"%>


<!DOCTYPE html>

<html lang="en">

<head>

  <title>Report Printing</title>

  <meta charset="utf-8">

  <meta name="viewport" content="width=device-width, initial-scale=1">


<his:javascript src="/new_investigation/reportprinting/reportprinting.js" />



  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

  
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.min.css"> 
    
    <link href='https://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet'>

<!--imp tools-->

	<!-- <link rel="stylesheet" type="text/css" href="drDeskAssets/css/style.css">
	<script src="new_opd/js/opdDrDeskSave.js"></script>
	<link rel="stylesheet" href="drDeskAssets/css/perfect-scrollbar.css">
	<script src="drDeskAssets/perfectScrollbar/perfect-scrollbar.js"></script>
	UIkit CSS
	<link rel="stylesheet" href="drDeskAssets/uikit/css/uikit.min.css" />
	UIkit JS
	<script src="drDeskAssets/uikit/js/uikit.min.js"></script>
	<script src="drDeskAssets/uikit/js/uikit-icons.min.js"></script>
	<script src="drDeskAssets/tippy/tippy.all.min.js"></script> -->


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
	
<!-- imp tools-->
	
  
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

  <script src="https://code.jquery.com/jquery-3.3.1.js"></script>

 <!-- <link rel="stylesheet" type="text/css" href="drDeskAssets/DataTables-1.10.18/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="drDeskAssets/Responsive-2.2.2/css/responsive.bootstrap4.min.css"/>
	<script type="text/javascript" src="drDeskAssets/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="drDeskAssets/DataTables-1.10.18/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="drDeskAssets/Responsive-2.2.2/js/dataTables.responsive.min.js"></script>
	<script type="text/javascript" src="drDeskAssets/Responsive-2.2.2/js/responsive.bootstrap4.min.js"></script>
 -->
 
   <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/DataTables-1.10.18/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/Responsive-2.2.2/css/responsive.bootstrap4.min.css"/>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/DataTables-1.10.18/js/dataTables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/Responsive-2.2.2/js/dataTables.responsive.min.js"></script>
	<script type="text/javascript" src="/HIS/hisglobal/drDeskAssets/Responsive-2.2.2/js/responsive.bootstrap4.min.js"></script>
	
<!--
  <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

  <script src="https://cdn.datatables.net/rowreorder/1.2.5/js/dataTables.rowReorder.min.js"></script>
  
      <script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
	  
  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

  <link rel="stylesheet" href="https://cdn.datatables.net/rowreorder/1.2.5/css/rowReorder.dataTables.min.css">

  <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.dataTables.min.css">
-->
  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/fontawesome.css">
	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />

<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/drDeskAssets/jstree/themes/default/style.min.css" />



<script>
// Initialize tooltip component
$(function () {
 // $('[data-toggle="tooltip"]').tooltip()
})

// Initialize popover component
$(function () {
  $('[data-toggle="popover"]').popover()
  
})

</script>

<style>
.popover{
   max-width: 120%;
   background: black;
    
}



.popover.bottom .arrow:after {
    border-bottom-color: black;
}


.searchtile {
  box-shadow: 0 4px 8px 0 gba(210,255,82,1);
  transition: 2.0s;

}

.searchtile:hover {
  box-shadow: 0 8px 16px 0 gba(210,255,82,1);

  -ms-transform: scale(1.5); /* IE 9 */
  -webkit-transform: scale(2.0); /* Safari 3-8 */
  transform: scale(1.01);
  }
  
  
#myBtn {
  display: none;
  position: fixed;
  bottom: 20px;
  right: 30px;
  z-index: 99;
  font-size: 18px;
  border: none;
  outline: none;
  background-color: red;
  color: white;
  cursor: pointer;
  padding: 15px;
  border-radius: 4px;
}

#myBtn:hover {
  background-color: #555;
}



</style>
  
  <script>

  window.onscroll = function() {scrollFunction()};

  function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
      document.getElementById("myBtn").style.display = "block";
    } else {
      document.getElementById("myBtn").style.display = "none";
    }
  }

  function topFunction() {
	  document.body.scrollTop = 0;
	  document.documentElement.scrollTop = 0;
	}
	
  </script>
  
</head>


<body>

<html:form action="/invResultReportPrintingNew" method="post">



<div class="card shadow p-3 mb-5 bg-white rounded" style="margin:10px 10px 10px 10px">
  <div class="card-body" style="padding-top:0px">
    
 <div class="col-sm-12 " style="; padding-bottom: 0px; padding-top: 15px; padding-right: 0px;padding-left: 5px;margin-bottom: -5px;" >
      
<div class="row">
          	<div class="col-sm-2" style="padding-right: 0px;padding-left: 0px;">
          		<h2 style="margin:0px 15px;font-size: 1.6rem;display:inline-block"> <a style="text-decoration:none;color: rgba(23, 97, 194, 0.74);font-weight: bold;font-size: 20px;" href="javascript:;"> Report Printing</a></h2>

            </div>

            <div class="col-xs-12 col-sm-1">
          		<button style="float: right; color:#4067c5;font-size: 20px;" class="btn  " type="button" onclick="" data-html="true" data-toggle="popover" data-trigger="hover"  data-content="<span style='color:white;'>Responsive&nbsp;View</span>"><i class="glyphicon glyphicon-fullscreen" ></i></button>


          	</div> 
<div class="clearfix visible-xs" style="margin: 10px;"></div>
          	<div class="col-xs-12 col-sm-5 form-horizontal">
          		<div class="form-group" style="margin-bottom:0px;">
          		

 <div class="container-fluid col-xs-6 col-sm-3" style="font-size: 1.6rem">

  <select class="custom-select" style="font-size: 1.6rem" >
  <option selected>Cr NO.</option>
  <option value="1">Billing NO</option>
  
</select>
</div>

<div class="col-xs-6 col-sm-7" style="">
                  <input type="text" class="form-control" id="usr" name="patCrNo">


<p class="text-center" style="font-size:0.95em;margin-top: 2px;margin-bottom: 0px;float: right;font-weight:bold; margin-right:-5px"><font style="color: rgba(35, 104, 194, 0.8)"></font> <a href="#">Advanced Search</a></p>
                </div>

</div>


</div>

<div class="col-xs-12 col-sm-3 " style="margin-bottom: 0px">
          		
               <button  width="100%" type="button" class="btn btn-info block button col-xs-12 col-sm-3" onclick="showall()"><span>Go</span></button>
                </div>





</div>

</div>


  </div>


</div>


<div class="card searchtile shadow p-3 mb-5  " style="background-color:light green;margin:-19px 10px 10px 10px;padding-bottom:0px;display:none" id="pattile">
  <div class="card-body" style="padding-top:0px" >

<div class="col-sm-5" style="padding-right: 0px;padding-left: 0px;margin-left:-20px;" >
          	
<p class="text-center" style="margin:0px 15px;font-size:1.6em;font-weight:bold;display:inline-block"><font style="color: grey"></font> <i class="fa fa-user" style="color: grey"></i><a >Patient Details:</a></p>

            </div>


 <div class="col-sm-12 uk-card uk-card-default leftPanel"  style="height:; padding-top: 2px">  


<table id="example1" class="table table-hover table-condensed table-checkable patientListMainTable" style="width:100%;margin-bottom:0px">
					  <thead>
					    <tr style="display:none">
					<th></th>
					
<th></th>
<th></th>
<th></th>
<th></th>
<th></th>
<th></th>
<th></th>
<th></th>
					     </tr>
					    </thead>
					     <tbody>
   <tr>
			 
			    
            <!--      <td>
<button type="button" class="btn btn-outline-info opdVitalBtn" style="background-color:#7C9CDE !important; border:0px;" onclick="openModalForVital(this,event);"  ><span style="color:white"><b>CR NO: 9610145236985623</b></span></button>
</td>
                <td><span style="font-weight:bold">Patient Name: </span>Tiger Nixon
</td>
               
				<td><span style="font-weight:bold">Age/Gender: </span>M/26
</td>
				<td><span style="font-weight:bold">Mobile No: </span>8439697443
</td>
				<td >
  <button type="button" class="btn btn-outline-info opdVitalBtn" style="background-color:#7C9CDE !important; border:0px;" onclick="openModalForVital(this,event);"  ><span style="color:white"><b>Patient Status:IPD</b></span></button>
</td>

				<td><span style="font-weight:bold">Ward /Bed No: </span>5694
</td> -->
				
            </tr>
                                        

                                        

       	                                
					                   </tbody>
					                  </table>  

<!--
	<div class="col-xs-2 col-sm-2">
<span style="font-weight:bold">CR NO:</span>9610145236985623
</div>
<div class=" col-xs-2 col-sm-2">
<span style="font-weight:bold">Patient Name: </span>Tiger Nixon
</div><div class="col-xs-2 col-sm-2">
<span style="font-weight:bold">Age/Gender: </span>M/26
</div><div class="col-xs-2 col-sm-2">
<span style="font-weight:bold">Mobile No: </span>8439697443
</div><div class="col-xs-2 col-sm-2">
<span style="font-weight:bold">Patient Status: </span>IPD
</div><div class="col-xs-2 col-sm-2">
<span style="font-weight:bold">Ward /Bed No: </span>5694
</div>
-->

</div>

</div>
</div>

<div class="card searchtile shadow p-3 mb-5 bg-white rounded" style="margin:-19px 10px 1px 10px;display:none" id="crnodatattile">
  <div class="card-body" style="padding-top:0px" >
	<div class="col-xs-12 col-sm-12" >
      <button type="button" class="btn btn-info block button" style="float: right"  onclick="showreport1()" data-toggle="modal" data-target="#myModal"><span>Print Report</span></button>
</div>&nbsp;&nbsp;&nbsp;&nbsp;<br>

<button type="button"  onclick="topFunction()" id="myBtn" title="Go to top">Top</button>

  
 <div class="col-sm-12 uk-card uk-card-default leftPanel"  style="height: 95vh; padding-top: 10px">  
					<table id="example" class="table table-hover table-condensed table-checkable patientListMainTable" style="width:100%">
					  <thead>
					    <tr>
					 	<th></th>
			   
                <th><input type="checkbox">&nbsp;&nbsp;Lab Name</th>
                <th>Test/Group Name</th>
              <th>Pending Test</th>
                <th>Status</th>
               <th>Sample No/Lab NO</th>			  
               
			  <th>Report Ready Date</th>
              <th>Tentative Report Date</th>
			  <th>Test Conduct Date</th>
			  <th>Priority</th>
			  <th>Sample Collection Date</th>
			   <th>Last Printed Date</th>
			  
			  <th>Billing No</th>
					     </tr>
					    </thead>
					     <tbody>
   <tr>
			<!--  <td></td>
			    
                 <td><input type="checkbox"> &nbsp;&nbsp;Xray</td>
                <td>ABC</td>
               <td class="patStatus"><a style="cursor: default;font-weight:bold;color:white" class="label label-success">Report Generated</a></td>
				<td>123</td>
				<td>01-Aug-2019</td>
				<td>02-Aug-2019</td>
				<td>01-Aug-2019</td>
				<td>Normal</td>
				<td>01-Aug-2019</td>
				<td>-</td>
				<td>-</td>
				<td>1322366</td> -->
            </tr>
                                        

                                        

       	                                
					                   </tbody>
					                  </table>  
					                 
								    </div>  


</div>
</div>


<div class="modal fade" id="printPrescriptionMainDeskModal" role="dialog">
    <div class="modal-dialog modal-lg">       <!-- Modal content-->
      <div class="modal-content" >
        <div class="modal-header" style="height:5px">
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        </div>
        <div class="modal-body">
       		 <div class="text-center">
       			<button type="button" class="btn btn-info" onclick="$('#printPrescriptionMainDeskModal').modal('hide');$('#printPrescFrameId').remove();">Cancel</button>
			 </div> 
        </div>  
      </div> 
    </div>
  </div> 
  
  	
</html:form>
</body>


</html>

​

