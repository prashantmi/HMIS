<%try
{%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>
<%@page import="new_investigation.vo.BookMarkVO"%>

<%@page import="new_investigation.vo.Inv_ictc_VO"%>
<%@page import="new_investigation.transactions.controller.fb.InvestigationRaisingDtlreqFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>

<%@page import="java.util.*"%>
<his:javascript src="/hisglobal/js/calendar.js" />
<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>Online Requisition Raising Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- 
new added -->


<!-- <link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css"> -->
<!--  <link href="./../../hisglobal/css/layout.css" rel="stylesheet" type="text/css"> -->
<!-- <link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css"> -->

<!-- <link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" type="text/css" href="dateinput.css"/> -->
<link href="/HIS/hisglobal/customToolTip/css/writeOnToolTip.css" rel="stylesheet" type="text/css">
<!-- <link href="/HIS/hisglobal/css/dateinput_skin1.css" rel="stylesheet" type="text/css"> -->
<link href="/HIS/hisglobal/css/dateinput_small_skin1.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HIS/hisglobal/datepicker/css/datepicker.css">
   
   <!--  <link href="/HIS/hisglobal/customToolTip/css/writeOnToolTip.css" rel="stylesheet" type="text/css">
 <script src='/HIS/hisglobal/js/qtip/jquery.qtip.min.js'></script>
    <script src='/HIS/hisglobal/js/qtip/writeOnToolQTip.js'></script>
    --> 
    
<script src="/HIS/hisglobal/js/responsiveDataTablewithPlus/jquery-3.3.1.min.js"></script>




<%@include file="/hisglobal/invsnomed.html"%>



 
<link rel="stylesheet" href="/HISInvestigationG5/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<link rel="stylesheet" href="/HISInvestigationG5/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">

				 
<%@include file="/hisglobal/invsnomed.html"%>




<script src="https://code.jquery.com/jquery-migrate-3.0.0.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/basic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/barcode_code39.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.searchabledropdown-1.0.8.min.js"></script>
<%-- <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-migrate-1.0.0.js"></script> --%>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/drDeskAssets/tippy/tippy.all.min.js"></script>


<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.bpopup.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.bpopup.min.js"></script>

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhindic.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/pramukhime-common.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/multilingualSupport/multilingualSupport.js"></script>

<!-- <script type="text/javascript" src="/HIS/hisglobal/js/moment.min.js" ></script>
 <script type="text/javascript" src="/HIS/hisglobal/datepicker/js/datepicker.js"></script> -->
 <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/utilityFunctions.js"></script>
<script src='/HIS/hisglobal/js/qtip/jquery.qtip.min.js'></script>
<script src='/HIS/hisglobal/js/qtip/writeOnToolQTip.js'></script>



<!---New Resources Created by Prashant Start----------------------------------------------------------------------------------------------------------------->

  <!--JQuery -->
  <!-- Tooltip JS -->
  <script src="media/tooltip.js/1.3.2/umd/tooltip.min.js"></script>

  <!--Propper JS -->
<!--    <script src="media/popper.js/1.15.0/umd/popper.min.js"></script>
 -->
 <script src="media/jquery/3.4.1/jquery-3.4.1.min.js" type="text/javascript"></script>
  <script src="media/popper.js/1.15.0/umd/popper.min.js"></script>
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


  <!--Vue.js -->
  <script src="media/vue-js/vue.min.js" type="text/javascript"></script>
  <script src="media/vue-js/vuex.js" type="text/javascript"></script>
  <script src="media/vue-js/portal-vue.umd.min.js" type="text/javascript"></script>
  <link rel="stylesheet" href="media/vue-js/bootstrap-vue/bootstrap-vue.min.css" />
	
  <!--highchart js -->
  <script src="media/highcharts/7.2.0/highcharts.js"></script>
  <script src="media/highcharts/7.2.0/modules/exporting.js"></script>
  <script src="media/highcharts/7.2.0/modules/export-data.js"></script>
  
  <script type="text/javascript" src="media/tippy/5.1.2/dist/tippy-bundle.iife.min.js"></script>
  <link href="media/tippy/5.1.2/animations/perspective-extreme.css" rel="stylesheet" type="text/css" />
  <link href="media/tippy/5.1.2/animations/shift-away-extreme.css" rel="stylesheet" type="text/css" />
  <link href="media/tippy/5.1.2/themes/light.css" rel="stylesheet" type="text/css" />
  
  
  <!--Custome Resources----->
  <!-- <script type="text/javascript" src="media/reports/js/requisitionRaisingNew.js"></script> --> 
  <link rel="stylesheet" href="media/fonts/webfontkit-averta-family/stylesheet.css" type="text/css" />
  <link rel="stylesheet" href="media/reports/css/invTrackingReport.css" />
  <link rel="stylesheet" href="media/reports/css/requisitionRaisingNew.css" />
  <script type="text/javascript" src="media/reports/js/invTrackingIframe.js"></script>
  
 <!--  <script type="text/javascript" src="media/reports/js/highChart1.js"></script> -->

	

  <!---New Resources Created by Prashant End------------------------------------------------------------------------------------------------------------------->


<his:css src="/new_investigation/media/xray/display.css" />
<his:css src="/new_investigation/media/xray/sweetalert.css" />

<his:javascript src="/new_investigation/media/xray/sweetalert.js" />
<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />

<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
	
<his:javascript src="/new_investigation/js/appointment.js" />
	
<his:javascript src="/new_investigation/media/xray/modernizr.min.js" />

<%-- <his:css src="/new_investigation/media/xray/jquery.fancybox.css" />

<his:javascript src="/new_investigation/media/xray/jquery.fancybox.js" /> --%>

<script type="text/javascript" src="media/fancybox/3.5.7/jquery.fancybox.min.js"></script>
  <link rel="stylesheet" href="media/fancybox/3.5.7/jquery.fancybox.min.css" type="text/css" />
  
<his:css src="/new_investigation/media/xray/animate.min.css" />

<his:css src="/new_investigation/media/echo/bootstrap-multiselect.css" />
<his:javascript src="/new_investigation/media/bootstrap4//echo/bootstrap-multiselect.js" />



 <!-- <link href='/HIS/hisglobal/css/BootStrap4/NewResponsiveGUI.css' rel='stylesheet' /> -->

<!-- <script src="/HIS/hisglobal/js/responsiveDataTablewithPlus/jquery-3.3.1.min.js"></script> -->

<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/BootStrap4/FontAwesome/css/all.min.css"> -->

 
 


<%-- <his:css src="/new_investigation/media/echo/bootstrap.min.css" />

<his:javascript src="/new_investigation/media/bootstrap4/echo/jquery.min.js" />
<his:javascript src="/new_investigation/media/bootstrap4/echo/bootstrap.min.js" />
<his:css src="/new_investigation/media/echo/glymph.css" />
<his:css src="/new_investigation/media/echo/bootstrap-multiselect.css" />

<his:css src="/new_investigation/media/xray/font-awesome.min.css" />


<his:javascript src="/new_investigation/media/bootstrap4//echo/bootstrap-multiselect.js" />


<his:css src="/new_investigation/media/xray/display.css" /> --%>


	
	<%-- <his:javascript src="/new_investigation/media/xray/jquery.dataTables.min.js" />
	
	<his:javascript src="/new_investigation/media/xray/dataTables.buttons.min.js" />
	
	<his:javascript src="/new_investigation/media/xray/jszip.min.js" />
	<his:javascript src="/new_investigation/media/xray/pdfmake.min.js" />
	<his:javascript src="/new_investigation/media/xray/vfs_fonts.js" />
	<his:javascript src="/new_investigation/media/xray/buttons.html5.min.js" />
	<his:javascript src="/new_investigation/media/xray/buttons.print.min.js" />


<his:css src="/new_investigation/media/xray/sweetalert.css" />

	<his:javascript src="/new_investigation/media/xray/sweetalert.js" />

<his:css src="/new_investigation/media/xray/jquery.dataTables.min.css" />
<his:css src="/new_investigation/media/xray/buttons.dataTables.min.css" /> --%>

	
<%-- <script type="text/javascript"
	src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
	<his:css src="/hisglobal/css/jquery/jquery.ui.autocomplete.css" />
<his:css src="/hisglobal/css/jquery/jquery.ui.menu.css" />
<his:css src="/hisglobal/css/jquery-ui.css" />

<script type="text/javascript"
	src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js"
	djConfig="parseOnLoad: true"> </script>
	
		<his:javascript src="/new_investigation/js/appointment.js" />
	
	<his:css src="/new_investigation/media/xray/jquery.fancybox.css" />

	<his:javascript src="/new_investigation/media/xray/jquery.fancybox.js" />
	
	<his:javascript src="/new_investigation/media/xray/modernizr.min.js" />
	<his:css src="/new_investigation/media/xray/css/font-awesome.min.css" />
<his:css src="/new_investigation/media/xray/css/font-awesome.css" />
		
	<his:css src="/new_investigation/media/xray/animate.min.css" /> --%>

<%-- <script>alert("Inside Patient Revisit..");</script> --%>
<style type="text/css">



.blurred-box{
  position: relative;
  
  
  
  
  background: inherit;
  border-radius: 2px;
  overflow: hidden;
}

.blurred-box:after{
 content: '';
 
 
 background: inherit; 
 position: absolute;
 left: -25px;
 left position
 right: 0;
 top: -25px;  
 top position 
 bottom: 0;
 box-shadow: inset 0 0 0 200px rgba(255,255,255,0.05);
 filter: blur(10px);
}

.fancybox-content {
    border: 7px solid white;
    border-radius: .5rem!important;
}


.no-guttersNEW
{
margin-right:0;
margin-left:-35;
}
.no-guttersNEW2
{
margin-right:-33 important!;
margin-left:-40 important!;
}

@media print { 
		#nonprintableDiv1 
		{
		 display: none; 
		}
		#nonprintableDiv2 
		{
		 display: none; 
		}
		#nonprintableDiv3 
		{
		 display: none; 
		}
		#nonprintableDiv4 
		{
		 display: none; 
		}
		
}

.border .div-table-col{
border: 1px solid black;
}
</style>

<!--
 By Raj Kumar
@media for print Modal Content
  -->
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
    left: 5px;
    top: 5px;
    height: 100%;
    width: 100%
  }
} 
        
    </style>
    
    
<style>
.patSideList{
     display:none;
     position:fixed;
   	 width:320px;
   	 /* background-color:#fff; 
   	  background-color:#58d2f7;*/
   	 background-color:#d1d7d9;
   	 z-index:9999;
   	 right:0px;
   	 top:20vh;
   	 height:92vh;
   	 overflow-y:hidden;
   	 border-left:1px solid #dbdbdb;
	color: #fff;
	border-radius: 23px;
   }
   .patSideList legend{
   	 padding:10px 0;
   	 margin-bottom:5px;
   	 font-weight: 400;
   	 text-transform:uppercase;
   	 font-size: 18px;
   }
   .patSideList .patCatHeader{
   	 padding:5px 0;
   	 text-align: center;
   	 background-color: #dddddd;
   	 color: #535353;
   	 font-weight:600;
   	 font-size: 13px;
   	 letter-spacing:1px;
   	 text-transform:uppercase; 
   }
   .patSideList ul{
   	 list-style-type:none;
   	 width:100%;
   	 padding:0;
   	 margin:0;
   } 
   .patSideList ul li{
   	 list-style-type:none;
   	 width:100%;
   	 padding: 5px 10px;
   	 text-align:left; 
   	 border: none;
   	 color: #000 !important;
   	 /* nth-child(odd) { background: #61a2f2;} */
   }
   .patSideList ul li:hover{
	/*  background-color:#e7e7e7; */
	 background-color: #979797;
   }
   .patSideList ul li:not(:last-child){ 
   	 border-bottom:1px solid #dbdbdb;
   }
   .patSideList ul li img{
   	 margin: 3% 2%;float:left; width: 30px; min-width: 20px; box-shadow: 0px 5px 25px 0px rgba(0,0,0,0.2); border: 1px solid #fff;
   }      
 .totalPatHeader{
 background: #61a2f2 !important;
 }
 .radioTh{
 width: 14%;
 }
 .iconSize{
 font-size: 26px !important;
 }

input, select {
        	
			/* padding: 0px !important; */
            font-weight: 500 !important; 
            font-size: 14px !important;
        }
         .msgSucess{
  	
	color: green;
	font-size: 16px;
	font-weight: 600;
   }
 
</style>

<style>
		
		.container-fluid {
			margin-left:15px;
			margin-right:15px;
		}
 		.header {
            padding: 5px 4px;
            text-align: center;
            color: white;
            height: 31px;
            font-size: 20px;
            padding-bottom: 8px;
            margin-bottom: 8px;
            border-radius: 2px;
            background-image: linear-gradient(to right, #49B2F3, #02629C);
            box-shadow: 0 10px 4px -9px #0e0000;
		}
		.fontred {
        	color: red;
        	font-weight: 900;
        
        }
        .btn-outline-danger{ 
  			color: red;
  			border-color:red;
  		}
		.btn-outline-primary {
			color: blue;
			border-color:blue;
		}
		.btn-outline-success {
			color:green;
			border-color:green;
		}
 		.btn-circle {
    width: 37px;
    height: 34px;
    text-align: center;
    padding: 6px 0;
    font-size: 12px;
    line-height: 1.428571429;
    border-radius: 17px;
    margin: -3px;
}
       .label, label, font {
    		display: inline;
    		padding: .2em .6em .3em;
    		/* line-height: 1; */
    		color: rgba(75,75,75, 0.7);
  			font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
		    font-weight: 700 !important;
		    font-size: 14px;
			}
			
			.MainHeader{
			font-size: 21px;
			font-weight: 400 !important;
			}


 
		.subheadchk{
			color : #0b3da1;
			font-family: "Helvetica Neue", "Helvetica";
		    font-weight: 700 !important;
		    font-size: 14px;
		}
    	body {
            /* color: #000 !important; */
            background: #eaeef3; 
            /* color: rgba(33, 32, 32, 0.7); */
             color:  rgba(75,75,75, 0.7);
            font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
            font-size: 14px;
            font-weight: 500;
        }
        table{
        /* color: rgba(33, 32, 32, 0.7); */
         color:  rgba(75,75,75, 0.7);
        }
		fieldset
		{
		    border:1px solid #c2c4d9;
		    -moz-border-radius:5px;
		    -webkit-border-radius:5px;        
		    border-radius:5px;        
		    padding-left: 20px;
		    margin-left: 10px;
		    margin-right: 10px;
		    padding-right:20px;
		    /* padding-top:10px; */
		   /*  margin-top: -35px !important; */
		    
		}

div.viewport-a {
	
	position: relative;
}
div.viewport {
    
    box-sizing: border-box;
    height: 490px;
    overflow: auto;
    }
		legend {
    		 width:inherit; /* Or auto */
             padding:0 10px; /* To give a bit of padding on the left and right */
             border-bottom:none;
        }
 		.legend2 {
  			position: fixed;
  			top: 0.3em;
  			right: 100px;
  			
  			line-height:1.2em;
  		}
  		
  	.subHeaders {
            /* font-weight: 500 !important;
            color: rgba(33, 32, 32, 0.7);
            font-family: "Helvetica Neue", "Helvetica";
            font-size: 19px !important;
            padding-bottom: 6px !important; */
            font-weight: 500 !important;
			color: rgba(33, 32, 32, 0.7);
			color: rgba(75,75,75, 0.7);
			 font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
			font-size: 19px !important;
			padding-bottom: 6px !important;
			letter-spacing: 1px;

        }
        .divSection {
            margin-left: 0px;
            margin-right: 0px;
            background-color: #fff;
            padding: 1% 2% 1% 2%;
            margin: 2% 0;
            transition: 0.5s;
            box-shadow: 0 0.5px 5px 2px #b0acac;
            padding: 5px 20px;
            font-size: 14px;
            font-weight:500;
            border-radius: 5px;
        }
         .CrdivSection{
            margin-left: 0px;
			margin-right: 0px;
			background-color: #fff;
			padding: 1% 2% 1% 2%;
			margin: 0 13px;
			transition: 0.5s;
			box-shadow: 0 0.5px 5px 2px #b0acac;
			padding: 5px 20px;
			font-size: 14px;
			font-weight: 500;
			border-radius: 5px;
			padding: 45px;
        }
	        .errorMsg{
	    
	    color: red;
	   font-size: 16px;
		font-weight: 600;
	    
	    	}
	    	.clearBtns{
	    	color: #fff;
			background-color: #f0ad4e;
			border-color: #f0ad4e;
	    	}
	    	 .msgSucess{
  	
			color: green;
			font-size: 16px;
			font-weight: 600;
		   			}

        .divSection:hover {
            box-shadow: 0 2px 20px 2px #b0acac;


        }
  		
  		#PatientVisit_patAmountCollected, #divFeeVal2, #departmentCodeId {
  		height: 100% !important;
  		
  		}
  	#divNewDeptVisitId{
  	padding-bottom: 10px;
  	}
  	#divInternalReferId{
  	
		padding: 18px;
		margin: 2px;
		padding-top: 5px !important;
  	}
  	.clsSpace{
  	margin-bottom: 5px;
  	}
  	
  	#clearId : hover {	
  	background: #ff9e14 !important;
  	}
  	
  	
</style>

<style>

table { 
  width: 100%; 
  border-collapse: collapse; 
}
/* Zebra striping */
/* tr:nth-of-type(odd) { 
  background: #eee; 
}
th { 
  background: #333; 
  color: white; 
  font-weight: bold; 
}
td, th { 
  padding: 6px; 
  border: 1px solid #ccc; 
  text-align: left; 
} */
.ReceiptCard{
border: 1px solid #298ecb;

padding: 17px;

border-radius: 17px;
}
#patReferalDtlsID thead th, #patReferalDtlsID thead td{
border: none !important;
}
input[type="search"]{
 padding: 3px !important;
 border-radius: 5px !important;
 
}
select[name="patReferalDtlsID_length"]{
 padding: 3px !important;
 border-radius: 5px !important;

}
</style>
<!-- 
new end added  -->
<style>



.sidebar {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 0;
  right: 0;
  background-image:url(/HISInvestigationG5/hisglobal/images/bg9.jpg);
  overflow-x: auto;
  transition: 0.5s;
  padding-top: 60px;
   background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  border-style: solid;
  border-width: 1px;
}

.sidebar a {
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 15px;
  color: black;
  display: block;
  transition: 0.3s;
}

.sidebar a:hover {
  color: #f1f1f1;
}

.sidebar .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 25px;
  margin-left: 50px;
}

.openbtn {
  font-size:1.5rem;
  cursor: pointer;
  background-color: linear-gradient(to bottom, #3333cc 0%, #333399 100%);
  color: white;
  padding: 1px 1px;
  border: none;
}

.openbtn:hover {
  background-color: #444;
}

#main {
  transition: margin-right .5s;
  padding: 2px;
}

/* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
@media screen and (max-height: 450px) {
  .sidebar {padding-top: 15px;}
  .sidebar a {font-size: 18px;}
}


.img {
  opacity: 1;
  filter: alpha(opacity=100); /* For IE8 and earlier */
   
  z-index: -1;
  width : 100%;

 /* Add the blur effect */
  filter: blur(0px);
  -webkit-filter: blur(0px);

  /* Full height */
  height: 100%; 

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}

.login-clean  .btn-primary:active{transform:translateY(1px)}
	.login-clean  .forgot .btn{padding:16px 32px;border:none;background:0 0;box-shadow:none;text-shadow:none;opacity:.9;
	text-transform:uppercase;font-weight:700;font-size:13px;letter-spacing:.4px;line-height:1;outline:0!important}
	.login-clean{background:#f1f7fc;padding:80px 0}
	.login-clean{max-width:320px;width:90%;margin:0 auto;background-color:#fff;padding:40px;border-radius:4px;color:#505e6c;box-shadow:1px 1px 5px rgba(0,0,0,.1)}
	.login-clean .illustration{text-align:center;padding:0 0 20px;font-size:100px;color:lightblack}
	.login-clean
	.form-control{background:#f7f9fc;border:none;border-bottom:1px solid #dfe7f1;border-radius:0;box-shadow:none;outline:0;color:inherit;text-indent:8px;height:42px}
	.login-clean
	.btn-success{background:;border:none;border-radius:4px;padding:11px;box-shadow:none;margin-top:26px;text-shadow:none;outline:0!important}
	.login-clean  
	.btn-danger:active,.login-clean .btn-success:hover{background:lightgreen}.login-clean  .forgot{font-size:12px;color:#6f7a85;opacity:.9}
	.login-clean .forgot:active,.login-clean 
	.forgot:hover{opacity:1;text-decoration:none}
	
.divtable .tr {
  overflow: hidden;
  clear: both;
  border-bottom: 1px solid #ccc;
}

.divtable .th,
.divtable .td {
  padding: 5px 10px;
  float: left;
}

.divtable .th { font-weight: bold; }

@media (max-width: 767px) {

.accordion-xs .headings { display: none; }

.accordion-xs .tr { border-bottom-color: #fff; }

.accordion-xs .th,
 .accordion-xs .td {
  float: none;
  width: auto;
  padding: 0;
}

.accordion-xs .accordion-xs-toggle {
 background-color:#337AB7;  color: black;
  padding: 10px;
  cursor: pointer;
}

.accordion-xs .accordion-xs-toggle:hover { background-color: ; }

.accordion-xs .accordion-xs-toggle:after {
  content: '\2b';
  float: right;
}

.accordion-xs .accordion-xs-toggle.collapsed:after { content: '\2212'; }

.accordion-xs-collapse .inner { padding: 5px; }

.accordion-xs-collapse .td:before { font-weight: bold; }



.accordion-xs .accordion-xs-toggle:after {
  position: relative;
  top: 1px;
  display: inline-block;
  font-family: 'Glyphicons Halflings';
  font-style: normal;
  font-weight: 400;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
}
}

#snackbar {
  visibility: hidden;
  min-width: 250px;
  margin-left: -160px;
  background-color: #333;
  color: #fff;
  text-align: center;
  border-radius: 2px;
  padding: 16px;
  position: fixed;
  z-index: 1;
  left: 13%;
  bottom: 20px;
  font-size: 17px;
}

#snackbar.show {
  visibility: visible;
  -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
  animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

@-webkit-keyframes fadein {
  from {bottom: 0; opacity: 0;} 
  to {bottom: 20px; opacity: 1;}
}

@keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 20px; opacity: 1;}
}

@-webkit-keyframes fadeout {
  from {bottom: 20px; opacity: 1;} 
  to {bottom: 0; opacity: 0;}
}

@keyframes fadeout {
  from {bottom: 20px; opacity: 1;}
  to {bottom: 0; opacity: 0;}
}


select.custom{
  background-image: url("data:image/svg+xml;charset=utf-8,%3Csvg%20version%3D%221.1%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20xmlns%3Axlink%3D%22http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink%22%20x%3D%220px%22%20y%3D%220px%22%20fill%3D%22%23555555%22%20%0A%09%20width%3D%2224px%22%20height%3D%2224px%22%20viewBox%3D%22-261%20145.2%2024%2024%22%20style%3D%22enable-background%3Anew%20-261%20145.2%2024%2024%3B%22%20xml%3Aspace%3D%22preserve%22%3E%0A%3Cpath%20d%3D%22M-245.3%2C156.1l-3.6-6.5l-3.7%2C6.5%20M-252.7%2C159l3.7%2C6.5l3.6-6.5%22%2F%3E%0A%3C%2Fsvg%3E");
  padding-right: 25px;
  background-repeat: no-repeat;
  background-position: right center;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}



select.custom::-ms-expand {
  display: none;
}

 .ui-autocomplete {
     max-height: 100px;
     overflow-y: auto;
     /* prevent horizontal scrollbar */
     overflow-x: hidden;
 }
 
 .ui-autocomplete{width:50vh!important;margin-left:16}
 
  .ui-autocompletesearch {
     max-height: 100px;
     overflow-y: auto;
     /* prevent horizontal scrollbar */
     overflow-x: hidden;
 }
 
 .ui-autocompletesearch{width:200px!important;margin-left:}
 
 
.navbarcrno{

  
   /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#cfe7fa+0,6393c1+100;Grey+Blue+3D */
background: rgb(207,231,250); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(207,231,250,1) 0%, rgba(99,147,193,1) 100%); /* FF3.6-15 */
background: -webkit-linear-gradient(top,  rgba(207,231,250,1) 0%,rgba(99,147,193,1) 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to bottom,  rgba(207,231,250,1) 0%,rgba(99,147,193,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#cfe7fa', endColorstr='#6393c1',GradientType=0 ); /* IE6-9 */

    }
    
    .navbar{

  
background: #36D1DC;  /* fallback for old browsers */
background: -webkit-linear-gradient(to bottom, #5B86E5, #36D1DC);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to bottom, #5B86E5, #36D1DC); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */



}
    
    
    
    .cc{

  
   /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#cfe7fa+0,6393c1+100;Grey+Blue+3D */
background: rgb(207,231,250); /* Old browsers */
background: -moz-linear-gradient(top,  rgba(207,231,250,1) 0%, rgba(99,147,193,1) 100%); /* FF3.6-15 */
background: -webkit-linear-gradient(top,  rgba(207,231,250,1) 0%,rgba(99,147,193,1) 100%); /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(to bottom,  rgba(207,231,250,1) 0%,rgba(99,147,193,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#cfe7fa', endColorstr='#6393c1',GradientType=0 ); /* IE6-9 */

    }
	
	
	a:hover {
	cursor: pointer;
}



.body{
font-family: 'Gentium Basic', serif;
font-family: 'Sanchez', serif;}

.navbar-nav > li > a {padding-top:0px !important; padding-bottom:1px !important;color:black;}
.navbar-header >a {color:black}
a:hover {
	cursor: pointer;
}

li:hover {
	cursor: pointer;
}


.anyClassscrollable {
	height: 360px;
	overflow-y: scroll;
}

div {margin: 0px 0px 0px 0px; } 

.anyClassscrollabletestshow {
	height: 250px;
	overflow-y: scroll;
}

.anybookmarkClassscrollable {
	height: 150px;
	overflow-y: scroll;
}

.anyappClassscrollable {
	height: 150px;
	overflow-y: scroll;
}

.anyClassscrollablepatdetails {
	height: 100px;
	overflow-y: scroll;
}

.bd-labWiseTestModal-modal-lg .modal-header {
	background-color: #9999ff;
	color: black;
}

.bd-labWiseTestModal-modal-lg .modal-body #testList {
	font-weight: bold;
	font-size: 10;
}

@-moz-document url-prefix () {fieldset {
	display: table-cell;
}
}


.selectboxit-container .selectboxit, .selectboxit-container .selectboxit-options {
  width: 400px; /* Width of the dropdown button */
  border-radius:0;
  max-height:240px;
}

.selectboxit-options .selectboxit-option .selectboxit-option-anchor {
    white-space: normal;
    min-height: 30px;
    height: auto;
}


</style>
<script type="text/javascript">

$(document).ready(function() { 

$(function(){

	$(".testselectset").selectBoxIt({
            autoWidth: false
	});
	
});

});


function setchecked(obj,idw)
{
	if(obj.checked)
		{
		
		 for(var g=0;g<document.getElementsByName("radioEpisode").length;g++)
		{
			if(document.getElementsByName("radioEpisode")[g].checked)
				{
				var diagnosis=document.getElementsByName("selectdiagnosisname")[g].value;
				var reasonscode=document.getElementsByName("selectreasons")[g].value;
				var reasonsname=document.getElementsByName("selectreasonscode")[g].value;
				
			//  alert("a");
			   unsetvistreasons(reasonscode,reasonsname);
			   unsetdiagnosis(diagnosis);
				
				}
			
		} 
		
		for(var g=0;g<document.getElementsByName("radioEpisode").length;g++)
		{
			
		      if(idw==g)
		    	  {
		    	  
		          document.getElementsByName("selectedEpisodedetails")[0].value=document.getElementsByName("radioEpisode")[g].value;
		            
		            document.getElementsByName("selectedEpisode")[0].value=document.getElementsByName("selectedepisodeee")[g].value;
		            document.getElementsByName("radioEpisode")[idw].checked=true;
		           
		            var diagnosis=document.getElementsByName("selectdiagnosisname")[g].value;
					var reasonscode=document.getElementsByName("selectreasons")[g].value;
					var reasonsname=document.getElementsByName("selectreasonscode")[g].value;
					
				   setdiagnosis(diagnosis);
				   setvistreasons(reasonscode,reasonsname);
					
					
				   
		    	  }
		      else
		      {
		    	  document.getElementsByName("radioEpisode")[g].checked=false;
		      }
		}
		
		
		}
}


function unsetdiagnosis(diagnosis)
{
	//alert(diagnosis);
	
	 if(diagnosis!="")
     {
    	 var newdiag=diagnosis;
    	 
    	 if(newdiag.includes("$"))
    	 {
    		var newdiagg=newdiag.split("$") ;
    		 
    		for(var k1=0;k1<newdiagg.length;k1++)
    		{
    			var totalcode=newdiagg[k1].split("@");
    			var diagcode=totalcode[0];
    			var diagname=totalcode[1];
    			
    			
    			
    			mapcheifcompaintsnew.delete(diagcode);
    			mapcheifcompaintsnew_alreadyraised.delete(diagcode);
    			//alert(diagname);
    			 var newwid="daignosis234setchiefcompaints";
    			 document.getElementById(newwid).innerHTML = "";
    		
    		}
    		 
    	 }
    	 else
    	 {
    		// alert(newdiag);
    		  var totalcode=newdiag.split("@");
    			var diagcode=totalcode[0];
    			var diagname=totalcode[1];
    		
            
    			
    			mapcheifcompaintsnew.delete(diagcode);
    			mapcheifcompaintsnew_alreadyraised.delete(diagcode);
    			 var newwid="daignosis234setchiefcompaints";
    			 document.getElementById(newwid).innerHTML = "";
    			 
    			//alert(diagname);
    		
    }
    	 
     }
	 else
		 {
		 var newwid="daignosis234setchiefcompaints";
		 document.getElementById(newwid).innerHTML = "";
		 
		 }
	 
	 
	 
	 
}

function setdiagnosis(diagnosis)
{
	
	//alert("setdiagnosis"+diagnosis);
	 if(diagnosis!="")
     {
    	 var newdiag=diagnosis;
    	 
    	 if(newdiag.includes("$"))
    	 {
    		var newdiagg=newdiag.split("$") ;
    		 
    		for(var k1=0;k1<newdiagg.length;k1++)
    		{
    			var totalcode=newdiagg[k1].split("@");
    			var diagcode=totalcode[0];
    			var diagname=totalcode[1];
    			
    			
    			
    			var diagcode=diagcode;
    			var diagname=diagname;
    			mapcheifcompaintsnew.set(diagcode,diagname);
    			mapcheifcompaintsnew_alreadyraised.set(diagcode,diagname);
    			//alert(diagname);
    			
    		
    		}
    		 
    	 }
    	 else
    	 {
    		  var totalcode=newdiag.split("@");
    			var diagcode=totalcode[0];
    			var diagname=totalcode[1];
    		
            
    			
    			var diagcode=diagcode;
    			var diagname=diagname;
    			mapcheifcompaintsnew.set(diagcode,diagname);
    			mapcheifcompaintsnew_alreadyraised.set(diagcode,diagname);
    			//alert(diagname);
    		
    }
     }
	 else
		 {
		 
		 for (const [key, value] of mapcheifcompaintsnew_alreadyraised.entries()) {
		  	  
		        // alert("key");

			 mapcheifcompaintsnew.delete(key);
			 mapcheifcompaintsnew_alreadyraised.delete(key);
		  	 }
		 
		 
		 }
    	 
    	
	 
	 var tot="";
     for (const [key, value] of mapcheifcompaintsnew.entries()) {
  	  
        // alert("key");

			if(mapcheifcompaintsnew_alreadyraised.has(key))
				{
				tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+" </span>";
				}
			else
				{
			tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+"<span id='"+key+"' class='fdl-remove' onclick='removecheiefmapnew(this);$(this).parent().remove();'><a> x</a></span> </span>";
				}
		      
  	 }
     
    // alert(tot);
	        var newwid="daignosis234setchiefcompaints";
		 document.getElementById(newwid).innerHTML = tot;
	 
    	 
    /*  }
	 else
		 {
		 
		 var tot="";
	     for (const [key, value] of mapcheifcompaintsnew.entries()) {
	  	  
	        // alert("key");

				if(mapcheifcompaintsnew_alreadyraised.has(key))
					{
					tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+" </span>";
					}
				else
					{
				tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+"<span id='"+key+"' class='fdl-remove' onclick='removecheiefmapnew(this);$(this).parent().remove();'><a> x</a></span> </span>";
					}
			      
	  	 }
	     
	     var newwid="daignosis234setchiefcompaints";
		 document.getElementById(newwid).innerHTML = tot;
		 
		 } */
	 
	 
	 
}



function unsetvistreasons(reasonscode,reasonsname)
{
	
	if(reasonscode!="" && reasonsname!="")
	 {
	  var reason=reasonsname.split("#");
	   var reasonname=reasonscode.split("#");




            	
	for(var kp=0;kp<reason.length;kp++)
		{
		
		
		var visitcode=reason[kp];
		var visitname=reasonname[kp];
		mapcheifcompaints.delete(visitname,visitcode);
		mapcheifcompaints_alreadyraised.delete(visitname,visitcode);
		 var newwid="chief234setchiefcompaints";
		 document.getElementById(newwid).innerHTML = "";
		}
	 }
	else
		{
		var newwid="chief234setchiefcompaints";
		 document.getElementById(newwid).innerHTML = "";
		
		}
	 
}


function setvistreasons(reasonscode,reasonsname)
{
	
	if(reasonscode!="" && reasonsname!="")
	 {
	  var reason=reasonsname.split("#");
	   var reasonname=reasonscode.split("#");




            	
	for(var kp=0;kp<reason.length;kp++)
		{
		
		
		var visitcode=reason[kp];
		var visitname=reasonname[kp];
		mapcheifcompaints.set(visitname,visitcode);
		mapcheifcompaints_alreadyraised.set(visitname,visitcode);
		}
	 }
	 else
	 {
	 
	 for (const [key, value] of mapcheifcompaints_alreadyraised.entries()) {
	  	  
	        // alert("key");

		 mapcheifcompaints.delete(key);
		 mapcheifcompaints_alreadyraised.delete(key);     
	  	 }
	 
	 
	 }
	 
	 
	 var tot="";
for (const [key, value] of mapcheifcompaints.entries()) {
 
// alert("key");

	if(mapcheifcompaints_alreadyraised.has(key))
			{
			tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+" </span>";
			}
		else
			{
			tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+"<span id='"+key+"' class='fdl-remove' onclick='removecheiefmap(this);$(this).parent().remove();'><a> x</a></span> </span>";
			}
     
}

//alert(tot);
   var newwid="chief234setchiefcompaints";
document.getElementById(newwid).innerHTML = tot;
	
}



$(document).ready(function() { 

     // alert("tt");
	 var tot="";
     for (const [key, value] of mapcheifcompaintsnew.entries()) {
  	  
        // alert("key");

			if(key!=null && key!="" && value!=null && value!="")
			tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+" </span>";
		    
		      
  	 }
     
    // alert(tot);
	        var newwid="daignosis234setchiefcompaints";
		 document.getElementById(newwid).innerHTML = tot;
		 
		 
});


$(document).ready(function() { 

    // alert("tt");
	 var tot="";
    for (const [key, value] of mapcheifcompaints.entries()) {
 	  
       // alert("key");

			if(key!=null && key!="" && value!=null && value!="")
			tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+" </span>";
		    
		      
 	 }
    
   // alert(tot);
	        var newwid="chief234setchiefcompaints";
		 document.getElementById(newwid).innerHTML = tot;
		 
		 
});



function snomedctsingle(id)
{
	 snomedidd=id;
	// alert("in snomed");
	 if(document.getElementById('txt-snomed-ct-search_'+snomedidd).value=="")
	 document.getElementById('txt-snomed-ct-search_'+snomedidd).innerHTML = "Enter 3 characters to search";
	// alert('val'+document.getElementById('txt-snomed-ct-search_'+snomedidd).value);
	var callbck_index =function(ret_OUT){setValue(ret_OUT,id);};
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	var elmtId = null;
	var semantictag='';
	if(elmtId==null || elmtId==undefined)
		{
		elmtId=id; semantictag="";
	
		}
	//elmtId="2";
	//alert(elmtId);
	
	selectSNOMEDCTauto('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
	 
	
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	
	}
	


function clearr()
{
	var dd=document.getElementsByName("chief234")[0].value;
//	alert(document.getElementsByName("chief234")[0].value);
	if(dd.length>=3)
	document.getElementsByName("chief234")[0].value="";	

	
}


var mapcheifcompaints=new Map();

function setValue(selectedSNOMEDTerm,id)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	/*var arr=selectedSNOMEDTerm.split(",");
	var str=arr[0];
	var str1=arr[1];
	*///alert(str[0]); alert(str1);
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
     //     alert(str);
   //      alert(str1);
	/* document.getElementsByName("prefferedTerm")[0].value=str;
	document.getElementsByName("conceptId")[0].value=str1;
	 */
	 var snomedname="txt-snomed-ct-search_"+snomedidd;
	 	 var snomedcode="txt-snomed-ct-search_code"+snomedidd;
	 
	 	 document.getElementById(snomedname).value="";
	   
	 	 document.getElementById(snomedcode).value="";
	    
	    mapcheifcompaints.set(str1,str);


       var tot="";
       
       for (const [key, value] of mapcheifcompaints.entries()) {
    	  
    	  
    		if(mapcheifcompaints_alreadyraised.has(key))
			{
			tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+" </span>";
			}
		else
			{
			tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+"<span id='"+key+"' class='fdl-remove' onclick='removecheiefmap(this);$(this).parent().remove();'><a> x</a></span> </span>";
			}
    		
		//	tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+"<span id='"+key+"' class='fdl-remove' onclick='removecheiefmap(this);$(this).parent().remove();'><a> x</a></span> </span>";
		    
		      
    	 }
       
	var newwid=id+"setchiefcompaints";
		 document.getElementById(newwid).innerHTML = tot;
		// alert(snomedname);
	 	 document.getElementById(snomedname).value="";

	 	snomedname="input#"+snomedname;
	 	
	 	$(snomedname).val("");
	 	
	    //alert("val"+document.getElementsByName("tt1").value);
		}
	else
		{
		document.getElementById(snomedname).value="-";
		document.getElementById(snomedcode).value="";
		
	//	alert("blank");
		/* document.getElementsByName("prefferedTerm")[0].value="";
		 document.getElementsByName("conceptId")[0].value="";*/
		}
}


var mapcheifcompaintsnew=new Map();
var mapcheifcompaintsnew_alreadyraised=new Map();
var mapcheifcompaints_alreadyraised=new Map();



function snomedctsinglenew(id)
{
	 snomedidd=id;
	// alert("in snomed");
	 if(document.getElementById('txt-snomed-ct-search_'+snomedidd).value=="")
	 document.getElementById('txt-snomed-ct-search_'+snomedidd).innerHTML = "Enter 3 characters to search";
	// alert('val'+document.getElementById('txt-snomed-ct-search_'+snomedidd).value);
	var callbck_index =function(ret_OUT){setValuenew(ret_OUT,id);};
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	var elmtId = null;
	var semantictag='';
	if(elmtId==null || elmtId==undefined)
		{
		elmtId=id; semantictag="";
	
		}
	//elmtId="2";
	//alert(elmtId);
	
	selectSNOMEDCTauto('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	}
	

var mapcheifcompaints=new Map();

function setValuenew(selectedSNOMEDTerm,id)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	/*var arr=selectedSNOMEDTerm.split(",");
	var str=arr[0];
	var str1=arr[1];
	*///alert(str[0]); alert(str1);
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
     //     alert(str);
   //      alert(str1);
	/* document.getElementsByName("prefferedTerm")[0].value=str;
	document.getElementsByName("conceptId")[0].value=str1;
	 */
	 var snomedname="txt-snomed-ct-search_"+snomedidd;
	 	 var snomedcode="txt-snomed-ct-search_code"+snomedidd;
	 
	 	 document.getElementById(snomedname).value="";
	   
	 	 document.getElementById(snomedcode).value="";
	    
	 	 
	 	
	    mapcheifcompaintsnew.set(str1,str);


       var tot="";
       
       for (const [key, value] of mapcheifcompaintsnew.entries()) {
    	  
    	  
			if(mapcheifcompaintsnew_alreadyraised.has(key))
				{
				tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+" </span>";
				}
			else
				{
			tot+="<span class='badge badge-pill badge-info' style='font-weight:200;font-size:12'>"+value+"<span id='"+key+"' class='fdl-remove' onclick='removecheiefmapnew(this);$(this).parent().remove();'><a> x</a></span> </span>";
				}
		      
    	 }
       
	var newwid=id+"setchiefcompaints";
		 document.getElementById(newwid).innerHTML = tot;
	 		 
		// alert(snomedname);
	 	 document.getElementById(snomedname).value="";

	 	snomedname="input#"+snomedname;
	 	
	 	$(snomedname).val("");
	 	
	    //alert("val"+document.getElementsByName("tt1").value);
		}
	else
		{
		document.getElementById(snomedname).value="-";
		document.getElementById(snomedcode).value="";
		
	//	alert("blank");
		/* document.getElementsByName("prefferedTerm")[0].value="";
		 document.getElementsByName("conceptId")[0].value="";*/
		}
}

function removecheiefmap(obj)
{

	var dd=obj.id;
	//alert(dd);
	mapcheifcompaints.delete(dd);
	
	
}


function removecheiefmapnew(obj)
{

	var dd=obj.id;
	//alert(dd);
	mapcheifcompaintsnew.delete(dd);
	
	
}



function showCashCollection()
{

	
	
	//alert("ajax fire");
	 	 var status=1;
	 	var mode="GO";
	 	//alert(testCode);
	 //	alert(document.getElementsByName('cashCrNo')[0].value);
      var crno=document.getElementsByName('cashCrNo')[0].value;
	 	
	 var url1='/HBIMS/billing/transactions/CashCollectionOnlineTransCNT.cnt?hmode='+mode+'&CRNO='+crno+'';
      //var url1='/HBIMS/billing/transactions/CashCollectionOnlineTransCNT.cnt?hmode='+mode+'';
      //clickMenu(url1,'Cash Collection Desk');	
      clickMenu(url1,'Cash_Collection_Desk');
      
      // mywindow=window.open (url1);
}



function clickMenu(url,menu){
	
	
	callMenulink(url,menu);


}

function callMenulink(url,menu)
{
var selMenu=menu;
parent.ajaxStartMenu();
menu=menu.toString().replace(/_/g,' ').replace(/XXY/g,'/').replace(/XXX/g,'&');
//url = Base64.decode(url).toString();
parent.callMenu(url,menu);
$("#"+selMenu).css("color","#6D00D5");
parent.ajaxCompleteMenu();

document.getElementById("billingid").style.display="none";
}


var mapdatacheckbox=new Map();


function setdata(obj)
{
	//alert(obj.checked);
	
	var setis=obj.id;
	var valuee=obj.value;
           if(obj.checked)
        	   {
        	   
        	  

        	//   alert(setis);
        	   mapdatacheckbox.set(setis,valuee);
        	   }
           else
        	   {
         		 

        	   mapdatacheckbox.delete(setis,valuee);
        	   
        	   }
           
           var countw="0";
          
           for(var k=0;k<document.getElementsByName('testshowforadd').length;k++)
   		{
                       
   			if(document.getElementsByName('testshowforadd')[k].checked==true && document.getElementsByName('testshowforadd')[k].disabled==false)
				{
   				countw="1";
				}
   		
   		}
           
         //  alert("aa"+countw);
           
           if(countw=="1")
        	   {
  				document.getElementById("addbtn").style.display="";

        	   }
           else
        	   {
        	   document.getElementById("addbtn").style.display="none";
        	   }
           
}


function callMenu(mode, url)
{
	var targetURL = url;     
    var elemFrame;
 	if(parent.document.getElementById("frmMain")!=null) elemFrame = parent.document.getElementById("frmMain"); 
 	else if(parent.parent.document.getElementById("frmMain")) elemFrame = parent.parent.document.getElementById("frmMain"); 
 	else elemFrame = parent.document.getElementById("frmMain"); 	
    if(elemFrame!=null){
         elemFrame.src=targetURL;
         elemFrame.refresh();
    }
    else{
    	if(mode==1)
    	{
    		if(typeof $('#tabframe')!='undefined'){
                var tab = window.parent.$('#tabframe').tabs('getSelected');
                var index = window.parent.$('#tabframe').tabs('getTabIndex',tab);
                window.parent.$('#tabframe').tabs('select',index-1);  
                window.parent.$('#tabframe').tabs('close',index);      

            }    	
    	}
    	else if(mode==2)
    	{
    		if(typeof $('#tabDivId')!='undefined'){
                var tab = window.parent.$('#tabDivId').tabs('getSelected');
                var index = window.parent.$('#tabDivId').tabs('getTabIndex',tab);
                window.parent.$('#tabDivId').tabs('select',index-1);  
                window.parent.$('#tabDivId').tabs('close',index);       

            }
    	}
    	else
    	{
    		//alert("No Mode Defined");
    		showAlert ("1", "No Mode Defined!");
    	}
    }
}


function submitForm(val)
{
	//alert("hi");
	showreq();
}


function callOpenerFetchPatDtlBasedOnPatCatCardNo(obj)
{
	//alert(obj.value);
	
	}


function buildSelect( table44 ) {
	
	  table44.columns().every( function () {
	//	  alert("aa");
	    var column = table44.column( this, {search: 'applied'} );
	    var select = $('<select><option value=""></option></select>')
	    .appendTo( $(column.footer()).empty() )
	    .on( 'change', function () {
	      var val = $.fn.dataTable.util.escapeRegex(
	        $(this).val()
	      );

	      column
	      .search( val ? '^'+val+'$' : '', true, false )
	      .draw();
	    } );

	    column.data().unique().sort().each( function ( d, j ) {
	      select.append( '<option value="'+d+'">'+d+'</option>' );
	    } );
	    
	    // The rebuild will clear the exisiting select, so it needs to be repopulated
	    var currSearch = column.search();
	    if ( currSearch ) {
	      select.val( currSearch.substring(1, currSearch.length-1) );
	    }
	  } );
	}
	
function openDetailedPatInfo()
{
	//alert(document.getElementById("patSideListId").style.display);
	if(document.getElementById("patSideListId").style.display=="none")
	{
		document.getElementById("patSideListId").style.display="block";
		document.getElementById("patSideListId").focus();
		
	}
	else
		document.getElementById("patSideListId").style.display="none";
}

$(document).ready(function(){ 
    tippy('#estimatebtn', {
        delay: 100,
        arrow: true,
        arrowType: 'round',
        size: 'large',
        duration: 500,
        
        //animation: 'perspective-extreme',
        placement: 'bottom',
        theme: 'light',
        content: '<div style="border:1"><strong>No <span style="color: blue;">Test/Group </span>Raised</strong></div>'
    
    });
    });


function closemodel()
{
	
	$('#myModal2').modal('hide');
	}

function showCashCollection()
{

	
	
	//alert("ajax fire");
	 	 var status=1;
	 	var mode="GO";
	 	//alert(testCode);
	 //	alert(document.getElementsByName('cashCrNo')[0].value);
      var crno=document.getElementsByName('cashCrNo')[0].value;
	 	//alert("crno"+crno);
	 var url1='/HBIMS/billing/transactions/CashCollectionOnlineTransCNT.cnt?hmode='+mode+'&CRNO='+crno+'';
      //var url1='/HBIMS/billing/transactions/CashCollectionOnlineTransCNT.cnt?hmode='+mode+'';
      //clickMenu(url1,'Cash Collection Desk');	
      clickMenu(url1,'Cash_Collection_Desk');
      
      // mywindow=window.open (url1);
}

function openPopupWithEventHeightWidth(url,eventObj, height, width)
{
/*	alert("Inside openPopupWithEvenHeightWidth..."+url);*/
/*if(eventObj.type=="click" || eventObj.keyCode==13)
 {
//	alert("reg :: openPopup2()");
   	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
 	

if(!child.opener)
   child.opener = self;
 }
 return child*/
 //"&varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
 //alert(document.getElementsByName("varSSOTicketGrantingTicket")[0].value);
 //url=url+"?varSSOTicketGrantingTicket=18.184.162.18.201.159.160.165.245.58.203.110.161.52.76.32.180.79.72.188.37.219.103.34.61.199.225.192.58.177.69.165.241.183.38.243.111.80.70.76";
 //alert(url);	
 $("#searchPopupModalID").find('iframe').attr('src',url);
    $("#searchPopupButtonID").trigger('click');
}

function openNav() {
	  document.getElementById("mySidebar").style.width = "250px";
	  document.getElementById("main").style.marginLeft = "0px";
	}

	function closeNav() {
	  document.getElementById("mySidebar").style.width = "0";
	  document.getElementById("main").style.marginLeft= "0";
	}
	
$(function() {    
	  var isXS = false,
	      $accordionXSCollapse = $('.accordion-xs-collapse');

	  // Window resize event (debounced)
	  var timer;
	  $(window).resize(function () {
	      if (timer) { clearTimeout(timer); }
	      timer = setTimeout(function () {
	          isXS = Modernizr.mq('only screen and (max-width: 767px)');
	          
	          // Add/remove collapse class as needed
	          if (isXS) {
	              $accordionXSCollapse.addClass('collapse');               
	          } else {
	              $accordionXSCollapse.removeClass('collapse');
	          }
	      }, 100);
	  }).trigger('resize'); //trigger window resize on pageload    
	  
	  // Initialise the Bootstrap Collapse
	  $accordionXSCollapse.each(function () {
	      $(this).collapse({ toggle: false });
	  });      
	  
	  // <a href="https://www.jqueryscript.net/accordion/">Accordion</a> toggle click event (live)
	  $(document).on('click', '.accordion-xs-toggle', function (e) {
	      e.preventDefault();
	      
	      var $thisToggle = $(this),
	          $targetRow = $thisToggle.parent('.tr'),
	          $targetCollapse = $targetRow.find('.accordion-xs-collapse');            
	    
	      if (isXS && $targetCollapse.length) { 
	          var $siblingRow = $targetRow.siblings('.tr'),
	              $siblingToggle = $siblingRow.find('.accordion-xs-toggle'),
	              $siblingCollapse = $siblingRow.find('.accordion-xs-collapse');
	          
	          $targetCollapse.collapse('toggle'); //toggle this collapse
	          $siblingCollapse.collapse('hide'); //close siblings
	          
	          $thisToggle.toggleClass('collapsed'); //class used for icon marker
	          $siblingToggle.removeClass('collapsed'); //remove sibling marker class
	      }
	  });
	});
	
var selectedtestbills=[];
var selectedtest=[];

var selectedlab=[];

var testfornotaddviews="";

var mymapvalue = new Map();
var newmymapvalue = new Map();
var viewsmymapvalue = new Map();

var newarrayappdatalababsed=[];

if(document.getElementsByName('newlabtestcodearray')!=undefined)
	{

	}
else
	{
	newarrayappdatalababsed=[];

	}


function closeprevreq()
{
	$('#myModalHorizontalprevreq').modal('hide');


	}


function deleteRowPrvReqDtl(labCode,testCode,reqNo,testGroupCode1,testname,grpname)

{
	// alert("testGroupCode1"+testGroupCode1);
	var msgg="Are You Sure to Delete Test "+testname+" ?";
	
	//alert(testGroupCode1);
	
	if(testGroupCode1!="0" && testGroupCode1!="")
		{
		msgg="Are You Sure to Delete Group "+grpname+" ?";
		}
	
	var sumsgg="Test "+testname+" has been Successfully deleted.";
	
	if(testGroupCode1!="0" && testGroupCode1!="")
	{
		sumsgg="Group "+grpname+" has been Successfully deleted.";
	}
	
	swal({
  title: msgg,
  text: "",
  type: "warning",
  showCancelButton: true,
  confirmButtonClass: "btn-danger",
  confirmButtonText: "Yes, delete it!",
  closeOnConfirm: false
},
function(){

	var isdelete=false;
	  if(checkBillingDetail(labCode,testCode,reqNo) == 1){

		//  alert("checkbill");
  		  isdelete= updateReqTable(labCode,testCode,reqNo,"1",testGroupCode1);

	  }
	  else
  	  {
		 // alert("not checkbill");
		  isdelete= updateReqTable(labCode,testCode,reqNo,"2",testGroupCode1);
  	  }
	 // alert("checkbillfinal"+isdelete);
	  showprevreqmodal(testname);
  swal("Deleted!", sumsgg, "success");
});
	
	 /* var retVal = "";
	 if(testGroupCode1=="0")
		 {
		  retVal = confirm("Are You Sure to Delete it?");
		 }
	 else
		 {
		  retVal = confirm("Are You Sure to Delete Group?");
		 }
		 
	 // var retVal = confirm("Are You Sure to Delete it?");
    if( retVal == true ){

  	  document.getElementsByName('delTestCode')[0].value=testCode;
  	  document.getElementsByName('delLabCode')[0].value=labCode;
  	  if(checkBillingDetail(labCode,testCode,reqNo) == 1){

  		  updateReqTable(labCode,testCode,reqNo,"1",testGroupCode1);
      	  
      	  showPrvDetail(); 
        }
  	  else
      	  {
      	 // alert("The Requisition can be cancelled only if billing has been done.");
  		  updateReqTable(labCode,testCode,reqNo,"2",testGroupCode1);
      	  
      	  showPrvDetail(); 

      	  }

      	
    }
    else{
   	  //alert("User does not want to continue!....");
       return false;
    } */
	 
	   
	  
}

function updateReqTable(labCode,testCode,reqNo,isbilledornot,groupraisedalready)
{
	//  alert("inside del");
	//alert(reqNo);
	/* document.getElementsByName("requisitionNo")[0].value=reqNo; */
	//document.getElementsByName('requisitionNo')[0].value=reqNo;
	var flg = false;
	var isRequisitonRaisingPresent = false;
	var _mode = "DELETEREQDTL";  
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&delLabCode="+labCode+"&delTestCode="+testCode+"&requisitionNo="+reqNo+"&isbilledornot="+isbilledornot+"&groupraisedalready="+groupraisedalready, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			isRequisitonRaisingPresent = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
            	//alert("No Kitchen Found");
            //alert(error+"Error while populating Meal Time Information");
            isRequisitonRaisingPresent = false;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return flg;
}


 function checkBillingDetail(labCode,testCode,reqNo)
{
	
	var flg = false;
	var isBillingDone = false;
	var _mode = "CHECKBILLDTL";  
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&delLabCode="+labCode+"&delTestCode="+testCode+"&requisitionNo="+reqNo, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert(data);
			isBillingDone = data;
			flg = true;
		},
        error: function(error)
        {
            //if(typeof objKitchenList == 'undefined' || objKitchenList==null || objKitchenList=="" || objKitchenList.length==0)
            	//alert("No Kitchen Found");
            //alert(error+"Error while populating Meal Time Information");
            isBillingDone = false;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return isBillingDone;
}

 var tblaa;
 
function settblsearchtest()
{
//alert("kk");
mapdatacheckbox=new Map();
  				document.getElementById("addbtn").style.display="none";

	 $('#testtablelistoftest').DataTable().destroy();
	 tblaa= $('#testtablelistoftest').DataTable( {
    	    "pageLength":5,
    	    "bSort" : false,
    	 //   "lengthMenu": [[10, 16, 20,50, 100,-1],[10, 16, 20,50, 100,All]]
	 "lengthMenu": [[5,10, 25, 50, -1], [5,10, 25, 50, "All"]],
    	    /* "dom": '<"row"<"col-12 col-md-6"l><"col-12 col-md-6"f>>rt<"row"<"col-12 col-md-6"i><"col-12 col-md-6"p>><"clear">', */
               
    	    "scrollY":        "190px",
    	              "scrollCollapse": true,
    	              "pagingType": "simple", 
              "drawCallback": function( settings ) {
      	    	settblsearchtestnew();
      	    },
      	  "columnDefs": [
              {
                  "targets": [ 3 ],
                  "visible": false,
                  "searchable": false
              },
              
          ],
      	  initComplete: function () {
              this.api().columns().every( function (i) {
                  var column = this;
                  if(i==1 || i==2 )
                	  {
                	  
                	  var labell="";
                	  if(i==1)
                		  {
                		  labell="Search By Test/Group";
                		  }
                	  
                	  if(i==2)
            		  {
            		  labell="Search By Lab";
            		  }
                	  labell="<select class='testselectset' style='width:300'><option value=''>"+labell+"</option></select>";
                	  
                  var select = $(labell)
                      .appendTo( $(column.header()).empty() )
                      .on( 'change', function () {
                          var val = $.fn.dataTable.util.escapeRegex(
                              $(this).val()
                          );
   
                          column
                              .search( val ? '^'+val+'$' : '', true, false )
                              .draw();
                      } );
   
                  column.data().unique().sort().each( function ( d, j ) {
                	 // alert(j);
                	  
                      select.append( '<option value="'+d+'">'+d+'</option>' )
                 //     alert(d);
                  } );
                	  }
              } );
          }
    	  } );
	 
	 
	   /* buildSelect( tblaa );
  	    tblaa.on( 'draw', function () {
  	      buildSelect( tblaa );
  	    } ); */
  	    
  	    

for(var g=0;g<document.getElementsByName("testshowforadd").length;g++)
{ 
	  document.getElementsByClassName("trlist")[g].removeAttribute("title");

	document.getElementsByName('testshowforadd')[g].checked=false;
	document.getElementsByName('testshowforadd')[g].disabled =false;

}

                     
if(document.getElementsByName("selectedtestdataatrray").length>0)
{
	//alert(document.getElementsByName("selectedtestdataatrray").length);
for(var k=0;k<document.getElementsByName('testshowforadd').length;k++)
	{

	//var tocheck=document.getElementsByName('testshowforadd')[k].value;
               var tocheck=document.getElementsByName('testshowforadd')[k].id;
	//var tocheckid=document.getElementById('testshowforadd')[k].getAttribute( 'id' );
             var tocheckid= document.getElementsByName('testshowforadd')[k].id ;
             
    //alert("tocheckid"+tocheckid);
		
	for(var g=0;g<document.getElementsByName("selectedtestdataatrray").length;g++)
	{
		
		               

		var val=document.getElementsByName("selectedtestdataatrray")[g].value;
		
		var grpcodeee=val.split("#")[10];
		var labcodee=val.split("#")[0];
		var testcodeee=val.split("#")[2];
		
		
		if( grpcodeee!=null && grpcodeee!="undefined" && grpcodeee!="null" && grpcodeee!="0")
    	  {	
			var idr=grpcodeee+"#"+labcodee;
			//alert(idr);
		//	document.getElementById(idr).checked=true;
		    if(document.getElementById(idr)!=null)
        	{
		    	
		    document.getElementById(idr).disabled =true;
        	
			var ides=grpcodeee+labcodee+"list";
			//alert("ides"+ides);
			 document.getElementById(ides).setAttribute('title', 'This Test/Group is Already Added. ');

        	}
    	  
    	  }
		
		
			var idr=testcodeee+""+labcodee;
			//alert(idr);
		//	document.getElementById(idr).checked=true;
		if(document.getElementById(idr)!=null)
			{
        	document.getElementById(idr).disabled =true;
			var ides=idr+"list";
			 document.getElementById(ides).setAttribute('title', 'This Test/Group is Already Added. ');

			}
		
		
		 //alert(val.split("#")[10]);
		
            /* if(val.includes(tocheck.split("#")[0]))
                {
            	document.getElementsByName('testshowforadd')[k].checked=true;
            	document.getElementsByName('testshowforadd')[k].disabled =true;
                } */
            
		
	}
			
	


	
	}

}

//alert(document.getElementById('1024#10001'));
       // document.getElementById('1024#10001').disabled =true;

//tblaa.order([1, 'desc']).draw();

setTimeout(function() {
	//tblaa.order([0, 'desc']).draw();
	tblaa.order([1, 'asc']).draw();
}, 500);

	}



function settblsearchtestnew()
{
//alert("kk");



for(var g=0;g<document.getElementsByName("testshowforadd").length;g++)
{ 

	document.getElementsByName('testshowforadd')[g].checked=false;
	document.getElementsByName('testshowforadd')[g].disabled =false;

}

                     
if(document.getElementsByName("selectedtestdataatrray").length>0)
{
	//alert(document.getElementsByName("selectedtestdataatrray").length);
for(var k=0;k<document.getElementsByName('testshowforadd').length;k++)
	{

	//var tocheck=document.getElementsByName('testshowforadd')[k].value;
               var tocheck=document.getElementsByName('testshowforadd')[k].id;
	//var tocheckid=document.getElementById('testshowforadd')[k].getAttribute( 'id' );
             var tocheckid= document.getElementsByName('testshowforadd')[k].id ;
             
    //alert("tocheckid"+tocheckid);
		
	for(var g=0;g<document.getElementsByName("selectedtestdataatrray").length;g++)
	{
		
		      //       alert("g"+g+":len"+document.getElementsByName("selectedtestdataatrray").length);  

		var val=document.getElementsByName("selectedtestdataatrray")[g].value;
		
		var grpcodeee=val.split("#")[10];
		var labcodee=val.split("#")[0];
		var testcodeee=val.split("#")[2];
		
		
		if( grpcodeee!=null && grpcodeee!="undefined" && grpcodeee!="null" && grpcodeee!="0")
    	  {	
			var idr=grpcodeee+"#"+labcodee;
		//	alert(idr);
		//	document.getElementById(idr).checked=true;
		if(document.getElementById(idr)!=null)
		{
			
			document.getElementById(idr).disabled =true;
			var ides=idr+"list";
			 document.getElementById(ides).setAttribute('title', 'This Test/Group is Already Added. ');

		
		}
    	  }
		
		
			var idr=testcodeee+""+labcodee;
		//	alert(idr);
		//	document.getElementById(idr).checked=true;
		if(document.getElementById(idr)!=null)
			{
        	
			document.getElementById(idr).disabled =true;
			var ides=idr+"list";
			 document.getElementById(ides).setAttribute('title', 'This Test/Group is Already Added. ');

			}
			
		
		 //alert(val.split("#")[10]);
		
            /* if(val.includes(tocheck.split("#")[0]))
                {
            	document.getElementsByName('testshowforadd')[k].checked=true;
            	document.getElementsByName('testshowforadd')[k].disabled =true;
                } */
            
		
	}
			
	


	
	}
	
	
	





}

//alert(document.getElementById('1024#10001'));
       // document.getElementById('1024#10001').disabled =true;

       
       
       if(mapdatacheckbox!=null)
	 {
for (const [key, value] of mapdatacheckbox.entries()) {
	

//alert(key);
if(document.getElementById(key)!=null)
document.getElementById(key).checked =true;


   }
}
       
	}



function showprevreqmodal(tests)
{
//alert("h");    

//var tests="Skeletal";
$('#myModalHorizontalprevreq').removeData();

$('#preqvreqtbl').dataTable( {
    "destroy": true,
    "oSearch": {"sSearch": tests },
    "pageLength":25
    
  } );


	}


function showprevreq11()
{
//alert("h");    

//var tests="Skeletal";
	
}

/* $(document).ready(function() {
    $(".iframee").fancybox({
    	tpl: {
    		//<img src='/HISInvestigationG5/hisglobal/css/close.png'  onClick="closeInstructions();">
    		  //closeBtn: '<button >aaa<img src="/HISInvestigationG5/hisglobal/css/close.png"></button>'
            closeBtn: '<a title="Close" class="fancybox-item fancybox-close"><img src="/HISInvestigationG5/hisglobal/css/cancel.svg"></a>'
        },
        type: 'iframe',
        'autoSize' : false,
        "width":1300,"height":1500,
        
    });
}); */


$(document).ready(function() {
	$(".iframee").fancybox({
		//closeExisting: false,
		type: 'iframe',
		toolbar  : false,
		smallBtn : true,
		transitionEffect: "zoom-in-out",
		transitionDuration: 366,
		iframe : {
			// Iframe template
			//tpl: '<iframe id="fancybox-frame{rnd}" name="fancybox-frame{rnd}" class="fancybox-iframe" allowfullscreen allow="autoplay; fullscreen" src=""></iframe>',
			preload : true,
			css : {
//				'width':$fancyboxWidth,
//				'height':$fancyboxHeight,
				'width'  : '100%',
				'height' : '100%',
			}
		},
		btnTpl: {
			close:
				'<button data-fancybox-close type="button"  class="fancybox-button fancybox-button--close" title="{{CLOSE}}">' +
				'<i class="far fa-window-close fa-w-16 fa-2x" style=" color: #82C91E;"></i>' +
				"</button>",

				smallBtn:
					'<button data-fancybox-close type="button"  class="fancybox-button fancybox-close-small " title="{{CLOSE}}">' +
					'<i class="far fa-window-close fa-w-16 fa-2x" style=" color: #82C91E;"></i>' +
					"</button>"
		},
		afterClose: function( instance, current ) {
			//actionOnFancyClose();
		}
	});
});


function isNumberKey(evt){
	    var charCode = (evt.which) ? evt.which : event.keyCode
	    if (charCode > 31 && (charCode < 48 || charCode > 57))
	        return false;
	    return true;
	}  



$(document).ready(function() {
    
	//alert(document.getElementsByName('gtypee')[0].value);
         var gtyp="";
         var gagee="";
         
         
         //alert(document.getElementsByName('ispat_vip')[0].value);
         
         var isvip=document.getElementsByName('ispat_vip')[0].value;
         
         if(isvip=="1")
        	 {
        	 
        	 document.getElementById('companyvip').value="5";
			   document.getElementById("companyvip").disabled = true;
			   document.getElementsByName("prioriotytype")[0].value="5";

			   
        	 }
         
         
         if(document.getElementsByName('gtypee')[0].value!=undefined)
        	 {
        	 gtyp=document.getElementsByName('gtypee')[0].value;
        	 }
         if(document.getElementsByName('gagee')[0].value!=undefined)
    	 {
        	 gagee=document.getElementsByName('gagee')[0].value;
    	 }
         
         if(gtyp=="F")
        	 {
        	 
        	 }
         else if(gtyp=="M")
         {
        	 document.getElementById("pr1").style.display="none";
        	 document.getElementById("pr2").style.display="none";

        	 document.getElementsByName('is_pregnant')[0].value="";
        	 
         }
         
       //  alert(gagee);
         if(gtyp=="F" && gagee.includes("Yr"))
		 {
     		var agee= gagee.replace(/Yr/g, '');

        //	 alert(agee);
        //	 alert(agee>=10);
        	 if(agee>=10)
        		 {
        	 document.getElementById("pr1").style.display="";
        	 document.getElementById("pr2").style.display="";
        		 }
        	 else
        		 {
        		 document.getElementById("pr1").style.display="none";
            	 document.getElementById("pr2").style.display="none";
            	 
            	 document.getElementsByName('is_pregnant')[0].value="";
            	 
        		 
        		 }
        	 
		 }
         else
        	 {
        	 
        	 document.getElementById("pr1").style.display="none";
        	 document.getElementById("pr2").style.display="none";
        	 
        	 document.getElementsByName('is_pregnant')[0].value="";
        	 
        	 
        	 }
         
         if(gagee.includes("Mth"))
		 {
	//	 alert("mth");
		var agee= gagee.replace(/Mth/g, '');
	//	alert("agee"+agee);
		
		if(agee>=8)
			{
			document.getElementById("nb1").style.display="none";
       	 document.getElementById("nb2").style.display="none";
       	document.getElementsByName('is_newborn')[0].value="";
			}
		else
			{
			  var isnewborn=document.getElementsByName('strIsNewBorn')[0].value;
	            //  alert(isnewborn);
			   if(isnewborn=="1")
			   {
				   document.getElementById('companynew').value="1";
				   document.getElementById("companynew").disabled = true;
				   document.getElementsByName("is_newborn")[0].value="2";

			   }
			   
			}
		
		 }
         else  if(gagee.includes("D"))
		 {
	//	 alert("mth");
		var agee= gagee.replace(/D/g, '');
	//	alert("agee"+agee);
		
		if(agee>=244)
			{
			document.getElementById("nb1").style.display="none";
       	 document.getElementById("nb2").style.display="none";
       	document.getElementsByName('is_newborn')[0].value="";
			}
		else
		{
	       var isnewborn=document.getElementsByName('strIsNewBorn')[0].value;
            //  alert(isnewborn);
		   if(isnewborn=="1")
		   {
			 //  alert("preee");
			   document.getElementById('companynew').value="2";
			   document.getElementById("companynew").disabled = true;
			   document.getElementsByName("is_newborn")[0].value="2";
		   }
		   
		}
		
		 }
         else  if(gagee.includes("wk"))
		 {
	//	 alert("mth");
		var agee= gagee.replace(/Mth/g, '');
	//	alert("agee"+agee);
		
		if(agee>=35)
			{
			document.getElementById("nb1").style.display="none";
       	 document.getElementById("nb2").style.display="none";
       	document.getElementsByName('is_newborn')[0].value="";
			}else
			{
				 var isnewborn=document.getElementsByName('strIsNewBorn')[0].value;
		            //  alert(isnewborn);
				   if(isnewborn=="1")
				   {
					 //  alert("preee");
					   document.getElementById('companynew').value="2";
					   document.getElementById("companynew").disabled = true;
					   document.getElementsByName("is_newborn")[0].value="2";
				   }
				   
				}
		
		 }
         else
        	 {
        		document.getElementById("nb1").style.display="none";
              	 document.getElementById("nb2").style.display="none";
              	document.getElementsByName('is_newborn')[0].value="";
        	 }
         
         
         
         
         
         
         
	//alert(gtyp);
	//alert(gagee);
   // $('#sd1').trigger('click');// for close popup
    


if(document.getElementsByName('isadmitted')[0]!=undefined && document.getElementsByName('isadmitted')[0].value=="0")
{
   

}
else
	{
	//alert(document.getElementsByName('gtypee')[0].value);
	var ff=<%=(String)session.getAttribute("load")%>;

	  if(document.getElementsByName('radioEpisode')[0]!=undefined && document.getElementsByName('radioEpisode').length>=2 && ff=="1")
		  {
	
		  jQuery('#myModal2').modal('show', {backdrop: 'static', keyboard: false});
	      }
	  }
   
	// document.getElementById("sd1").showModal(); 
});



/* $(document).ready(function() {
    $('#testtable').DataTable( {
    	responsive: true,
       
    	"lengthMenu": [ [5, 10, 15, 20, -1], [5, 10, 15, 20, "All"] ],
        buttons: [ 'pageLength'],
        "language": { "emptyTable": "Please Add Test in Group from above Search Box" },
            });
    $('#testtable').DataTable().page('last').draw('page');
} ); */


function showprevreq()
{
//alert("h");    


$('#preqvreqtbl').dataTable( {
    "destroy": true,
 
    "pageLength":25
    
  } );


	}


function cancel()
{

	document.getElementsByName('hmode')[0].value="NEW";
	document.forms[0].submit();
	
	}


function cancelFunc()
{
	window.parent.closeTab();
}


function setDateInApoitment(patCrNo,paraId,obj,divAptTagRow)
{
	   var date=document.getElementsByName('dateTag')[0].value;

	   var tocheckfromwherecall=paraId;

         if(tocheckfromwherecall.includes("^"))
             {
      	   tocheckfromwherecall=tocheckfromwherecall.split("^");
      	  
             }
		  
	  if(tocheckfromwherecall[1]=="0")
	  {
		  getAptSlotDetails(patCrNo,paraId,date,divAptTagRow,4);
	  }else
     {
		  getAptSlotDetails(patCrNo,paraId,date,divAptTagRow,2);
	      }
    
	  }
	  

function addall()
{

    if(mapdatacheckbox!=null)
	 {
for (const [key, value] of mapdatacheckbox.entries()) {
	
  // alert(value);
   
var tocheck=value;

var len=tocheck.split("#");
len=len.length;
//alert("len"+len);
if(len=="4")
{
var dataa=tocheck;
//  alert(dataa);
var  searchtyp=dataa.split("#")[2];

if(searchtyp=="2" || searchtyp=="3")
  {
  
  var testdata=dataa.split("#")[0];
   var maintestdata=testdata.split("^");
//    alert("111"+maintestdata);

         var isexist=false;
   for(var f=0;f<maintestdata.length;f++)
		{
		var testcode=maintestdata[f];
	//	alert("testciode"+testcode);
	  	isexist=checktestalreadyraisednew(testcode);
	 //   alert("isexist"+isexist);
	  	if(isexist.split("#")[0]=="0")
	  		{
	  		
	        var errmsg="Test "+isexist.split("#")[1]+" is Already Added! Please Remove first before adding this Group. ";
	          document.getElementById('automplete-testsearch').value="";
	   	   swal(
		                '',
		                errmsg,
		                'warning'
		            );	
					return false;
					
	  		return null;
	  		}
	  	
		}

   for(var f=0;f<maintestdata.length;f++)
		{
		var testcode=maintestdata[f];
	//	alert("testciode"+testcode);
       var testdatafinal=  ajaxhitforfetchingtestwisedata(testcode+"^"+dataa.split("#")[1]+"^2^"+dataa.split("#")[3]);
		
		maktestinrowtable(testdatafinal);
       
		}
   	
   
	
  }

}
else
{
var testdatafinal=  ajaxhitforfetchingtestwisedata(tocheck.split("#")[0]+"^"+tocheck.split("#")[1]);

if(testdatafinal=="1")
{

var errmsg="Patient Account Balance Going To Be Insufficient. Please Deposit Part Payment!!";
document.getElementById('automplete-testsearch').value="";
swal(
        '',
        errmsg,
        'warning'
    );	
	return false;
	
}
else
{
maktestinrowtable(testdatafinal);
}
}



	

  }
}
    
	//for(var k=0;k<document.getElementsByName('testshowforadd').length;k++)
	//{
/* 		alert("in"+document.getElementsByName('testshowforadd').length);

		if(document.getElementsByName('testshowforadd')[k].checked==true && document.getElementsByName('testshowforadd')[k].disabled==false)
		{
			
			 

			alert("in");
	var tocheck=document.getElementsByName('testshowforadd')[k].value;

	  var len=tocheck.split("#");
	len=len.length;
//	alert("len"+len);
	  if(len=="4")
		  {
		  var dataa=tocheck;
		//  alert(dataa);
		  var  searchtyp=dataa.split("#")[2];
		  
   	   if(searchtyp=="2" || searchtyp=="3")
   		   {
   		   
   		   var testdata=dataa.split("#")[0];
   		    var maintestdata=testdata.split("^");
   		//    alert("111"+maintestdata);
   		
                     var isexist=false;
   		    for(var f=0;f<maintestdata.length;f++)
   				{
   				var testcode=maintestdata[f];
   			//	alert("testciode"+testcode);
   			  	isexist=checktestalreadyraisednew(testcode);
       		 //   alert("isexist"+isexist);
   			  	if(isexist.split("#")[0]=="0")
   			  		{
   			  		
   			        var errmsg="Test "+isexist.split("#")[1]+" is Already Added! Please Remove first before adding this Group. ";
   			          document.getElementById('automplete-testsearch').value="";
   			   	   swal(
   				                '',
   				                errmsg,
   				                'warning'
   				            );	
   							return false;
   							
   			  		return null;
   			  		}
   			  	
   				}
   		 
   		    for(var f=0;f<maintestdata.length;f++)
   				{
   				var testcode=maintestdata[f];
   			//	alert("testciode"+testcode);
   		        var testdatafinal=  ajaxhitforfetchingtestwisedata(testcode+"^"+dataa.split("#")[1]+"^2^"+dataa.split("#")[3]);
   				
   				maktestinrowtable(testdatafinal);
   		        
   				}
   		    	
   		    
   			
   		   }
   	   
		  }
	  else
		  {
	var testdatafinal=  ajaxhitforfetchingtestwisedata(tocheck.split("#")[0]+"^"+tocheck.split("#")[1]);
	
	if(testdatafinal=="1")
		{
		
		  var errmsg="Patient Account Balance Going To Be Insufficient. Please Deposit Part Payment!!";
          document.getElementById('automplete-testsearch').value="";
   	   swal(
	                '',
	                errmsg,
	                'warning'
	            );	
				return false;
				
		}
	else
		{
	maktestinrowtable(testdatafinal);
		}
		  }

	    
				}
		else
			{
			// document.getElementById("addbtn").style.display="none";

			}
		 */
//	}

	
	$('#myModalHorizontal').modal('hide');
	

	}

function shwhide()
{

	//alert("shww1w");
	
	var shwhide1="0";
	var crnoshw="";
	var ff=<%=(String)session.getAttribute("load")%>;

	<%if(session.getAttribute("patcrno")!=null)
		{%>
		
		var patcrno='<%=(String)session.getAttribute("patcrno")%>';
		//document.getElementsByName("patCrNo")[0].value=patcrno;
		//alert("cr"+ document.getElementsByName("patCrNo")[0].value);
		<%}  else {}%>

	
	
	<%
	String addspane="0";
	String crnoo="";
	if(session.getAttribute("savesucc")!=null)
			{
		      addspane=(String)session.getAttribute("savesucc");
		      crnoo=(String)session.getAttribute("patcrnoo");
		%>
		 shwhide1=<%=addspane%>;
		 crnoshw='<%=crnoo%>';
		
			<%}
			
			%>
		//	alert(shwhide1);
			 if(shwhide1=="1")
             {
				// document.getElementsByName('patCrNo')[0].value="";
        	
        	 
             }
			 else
				 {
	        //	 document.getElementById("myspan").innerHTML = ""; //INSECURE!!

				 }
          
		//	 alert("shwww2");
			// alert("ff"+ff);
	if(ff=="1")
	{
		//alert("shwww3");
	 document.getElementById("cont1").style.display="none";

		document.getElementById("submitBothId").style.display="";

		document.getElementById("clearId").style.display="";

			        document.getElementById("cont3").style.display="";
					document.getElementById("cont4").style.display="";
					document.getElementById("cont5").style.display="";
					document.getElementById("cont6").style.display="";
					
	}
	else
		{
		//alert("shwww4");
		 document.getElementById("cont1").style.display="";

	        document.getElementById("cont3").style.display="none";
			document.getElementById("cont4").style.display="none";
			document.getElementById("cont5").style.display="none";
			document.getElementById("cont6").style.display="none";
			document.getElementById("submitBothId").style.display="none";

			document.getElementById("clearId").style.display="none";
		}			

	}
function setpriority(obj)
{

	//alert(obj.value);
	document.getElementsByName("prioriotytype")[0].value=obj.value;
}


function setpregnant(obj)
{

	//alert(obj.value);
	document.getElementsByName("is_pregnant")[0].value=obj.value;
}

function setnewborn(obj)
{

	//alert(obj.value);
	document.getElementsByName("is_newborn")[0].value=obj.value;
}


function save()
{

	//alert(document.getElementsByName("selectedtestdataatrray").length);
	
	//alert(document.getElementsByName("selectedtestviews")[0].value);

	
		for(var s=0;s<document.getElementsByName("selectedsite").length;s++)
			{
			
		//	alert(document.getElementsByName("selectedsite")[s].value);

			if(document.getElementsByName("selectedsite")[s].value=="" || document.getElementsByName("selectedsite")[s].value=="null" || document.getElementsByName("selectedsite")[s].value=="-1")
				{
				
				
				swal(
		                '',
		                "Please Select Sample ",
		                'warning'
		            );

				return null;
				}
			         
			}
		
		//return null;
/* 		if(document.getElementsByName("selectedsite")[0]==undefined)
			{
			
			swal(
	                '',
	                "No Test/group Selected",
	                'warning'
	            );

				return null;
				
			} */
		
	
	
		//alert("aa");
		
		if(mapcheifcompaints!=null)
			{
			
			 for (const [key, value] of mapcheifcompaints.entries()) {
		    	  
		    	  
				 
				 if(document.getElementsByName("chief_complaints_code")[0].value!=undefined && document.getElementsByName("chief_complaints_code")[0].value=="")
				 { 
					 document.getElementsByName("chief_complaints_code")[0].value=key;
				 }
				 else
					{
					 document.getElementsByName("chief_complaints_code")[0].value=document.getElementsByName("chief_complaints_code")[0].value+"$$"+key;
					 
					}
				 
				 if(document.getElementsByName("chief_complaints_name")[0].value!=undefined && document.getElementsByName("chief_complaints_name")[0].value=="")
					{
					 document.getElementsByName("chief_complaints_name")[0].value=value;
					}
				 else
					 {
					 document.getElementsByName("chief_complaints_name")[0].value=document.getElementsByName("chief_complaints_name")[0].value+"$$"+value;
					 }
				      
		    	 }
			 
			
			}
		
	
		if(mapcheifcompaintsnew!=null)
		{
		
		 for (const [key, value] of mapcheifcompaintsnew.entries()) {
	    	  
	    	  
			 if(document.getElementsByName("diagnosis_code")[0].value!=undefined && document.getElementsByName("diagnosis_code")[0].value=="")
			 { 
				 document.getElementsByName("diagnosis_code")[0].value=key;
			 }
			 else
				{
				 document.getElementsByName("diagnosis_code")[0].value=document.getElementsByName("diagnosis_code")[0].value+"$$"+key;
				 
				}
			 
			 
			 if(document.getElementsByName("diagnosis_name")[0].value!=undefined && document.getElementsByName("diagnosis_name")[0].value=="")
			 { 
				 document.getElementsByName("diagnosis_name")[0].value=value;
			 }
			 else
				{
				 document.getElementsByName("diagnosis_name")[0].value=document.getElementsByName("diagnosis_name")[0].value+"$$"+value;
				 
				}
			 
			 /* document.getElementsByName("diagnosis_code")[0].value=value;
	         document.getElementsByName("diagnosis_name")[0].value=key;
			  */
			      
	    	 }
		 
		
		}
		
	
		
		
		//return null;
		
		
		if(document.getElementsByName("selectedtestdataatrray")[0]==undefined)
			{
			
			swal(
	                '',
	                "No Test/group Selected",
	                'warning'
	            );

				return null;
				
			}
		
	
	
/* 
	if(document.getElementsByName("selectedtestdataatrray")[0]!=undefined)
		{

		for(var g=0;g<document.getElementsByName("selectedtestdataatrray").length;g++)
		{ 

			 if(document.getElementsByName("selectedtestdataatrray")[g].value!="")
			   {
				   
				 if(document.getElementsByName("selectedtestviews")[0]!=undefined)
					{

					for(var g=0;g<document.getElementsByName("selectedtestviews").length;g++)
					{ 
						 if(document.getElementsByName("selectedtestviews")[g].value=="")
						   {
							
							
								swal(
						                '',
						                "Please Select View for Test "+(document.getElementsByName("selectedtestdataatrray")[g].value).split("#")[3],
						                'warning'
						            );

									return null;
						   }
					}
					
					}
				  else
				 {

							
					  swal(
				                '',
				                "Please Select View for Test "+(document.getElementsByName("selectedtestdataatrray")[g].value).split("#")[3],
				                'warning'
				            );
			
					 		return null;
					 }

				   


			   }
			 else
			 {
				    
				 swal(
			                '',
			                'Please Select Test !',
			                'warning'
			            );			
						 		return null;
				 }

   
		}

		}
	else
		{


		//swal("Please Select Test ");
		swal(
                '',
                'Please Select Test !',
                'warning'
            );

		return null;
	} */
	

	for(var g=0;g<document.getElementsByName("radioEpisode").length;g++)
	{
		
//	alert(document.getElementsByName("radioEpisode")[g].value);
	       if(document.getElementsByName("radioEpisode")[g].checked)
		   {
		            document.getElementsByName("selectedEpisodedetails")[0].value=document.getElementsByName("radioEpisode")[g].value;
		            
		            document.getElementsByName("selectedEpisode")[0].value=document.getElementsByName("selectedepisodeee")[g].value;
		   }
	}
	

	//alert("aab"+document.getElementsByName("hmode")[0].value);
//	return null;
	
	var today = new Date();
	  		/* var date=convertDateToStr(today,"dd-Mon-yyyy");
	  		var Time=convertDateToStr(today,"hh:mm"); 
	  		var k=0;//variable for appointment array of date,time and refno
	  		 */var aptRefNo=[];
	  		var aptDate=[];
	  		var aptTime=[];

	  		var labCode=applabcode;
	  		var testCode="0";
	  		
			var divId="aptTagRow_"+labCode+"_"+testCode;

			/* if (islababsedapp=='1' && newarrayappdatalababsed.length>0)
			{ */
			if (true)
			{
			for(var g=0;g<newarrayappdatalababsed.length;g++)
				{
				
				var data=newarrayappdatalababsed[g];
				var datalen=data.split("$$");
			    var newdivid1=data.split("$$")[1];

				    var newdivid=data.split("$$")[1];
				  newdivid="aptForDate_"+newdivid;
				  if(typeof document.getElementsByName(newdivid)[0] != 'undefined' )
				{
				if(data.includes(labCode))
					{
					
					if(datalen.length==2)
						{
	  				var aptSatus=createAppointment(newdivid1);
	  				var objaptno=document.getElementsByName('appointmentRefNo')[0].value;
	  				aptRefNo.push(labCode+testCode+"#"+document.getElementsByName('appointmentRefNo')[0].value);
	  			newarrayappdatalababsed[g]=data+"$$"+labCode+testCode+"#"+document.getElementsByName('appointmentRefNo')[0].value;
	  			if(!aptSatus)
				 {
				// alert("returing false 1");
				return false;
				}
						}
					
					else if(datalen.length==3)
						{
						
						var datanew=datalen[2];
						aptRefNo.push(datanew);
						
						}
					
					
					}
				}
				
				 if(typeof document.getElementsByName(newdivid)[0] == 'undefined' && offAptNo=='null')
  					{

					alert("Can't raise Test: "+testName+" for Laboratory: "+labName+", as no slots are available");
					// alert("returing false 2");
					return false;
					 
  					}
				 
				if (typeof document.getElementsByName(newdivid+'')[0] != 'undefined' )
				{
					document.getElementsByName('appointmentTime')[0].value=document.getElementsByName('slotST_'+newdivid1+'')[0].value;
					document.getElementsByName('appointmentDate')[0].value=document.getElementsByName('aptForDate_'+newdivid1+'')[0].value;
					
					aptTime.push(labCode+testCode+"#"+document.getElementsByName('slotST_'+newdivid1+'')[0].value);
					aptDate.push(labCode+testCode+"#"+document.getElementsByName('aptForDate_'+newdivid1+'')[0].value);

				}
				
				
				if( typeof document.getElementsByName(newdivid+'')[0] == 'undefined' && offAptNo=='null' )
				{
				
				alert("Can't raise Test: "+testName+" for Laboratory: "+labName+", as no slots are available");
				// alert("returing false 2");
				return false;
				
				}
				
				
				  
				}
			
			}
	
	
		/* 	 var dateAndTime=document.getElementsByName('offlineAppoitmentDtl')[0].value;
				 var SplitWithSpace=dateAndTime.split(' ');
				 
				 var offlineAptDate=SplitWithSpace[0];
				 var offLineAptTime=SplitWithSpace[1];
			
			
					 document.getElementsByName('appointmentDate')[0].value=offlineAptDate;
			        document.getElementsByName('appointmentTime')[0].value=offLineAptTime;
				 
			        document.getElementsByName('appointmentRefNo')[0].value=document.getElementsByName('offlineAptDtl')[0].value
			        
			        aptTime.push(labCode+testCode+"#"+offLineAptTime);
					aptDate.push(labCode+testCode+"#"+offlineAptDate);
					aptRefNo.push(labCode+testCode+"#"+document.getElementsByName('appointmentRefNo')[0].value);


					
 */

 document.getElementsByName('labTestAptTime')[0].value=aptTime;
	document.getElementsByName('labTestAptDate')[0].value=aptDate;
	//alert("aptRefNo"+aptRefNo);
document.getElementsByName('labTestAptRefNo')[0].value=aptRefNo;

				    //   alert("hmode");
document.getElementsByName('hmode')[0].value="SAVEXRAYPROCESS";
	//alert("aab"+document.getElementsByName("hmode")[0].value);
	//document.forms[0].hmode.value='SAVEXRAYPROCESS';
	document.forms[0].submit();

	
}


function callviews(obj)
{

     var viewid=obj.id+"@";
      var classviews=obj.className;
      classviews = classviews.replace(/!/g, '#');
      classviews=classviews+"view";
var finval=document.getElementById(classviews).value;
      if(obj.checked==true)
          {
          if(finval.includes(viewid)==false)
     document.getElementById(classviews).value+=viewid;
          }
      else if(obj.checked==false)
          {
     var val= document.getElementById(classviews).value;
     val = val.replace(viewid, '');
     document.getElementById(classviews).value=val;
          }


      settestbill();
      
}

function callsite(obj)
{
  
	 var classviews=obj.id;
     classviews = classviews.replace(/box/g, '');
     classviews=classviews+"site";
     
    // alert(classviews);
    	 var siteval=obj.value;
    	 
    	     document.getElementById(classviews).value=siteval;
    	 
        

}


function callsample(obj)
{
  
	 var classviews=obj.id;
     classviews = classviews.replace(/box/g, '');
     classviews=classviews+"site";
     
    // alert(classviews);
    	 var siteval=obj.value;
    	 
    	     document.getElementById(classviews).value=siteval;
    	 
        

}

var mapbookmark=new Map();

function addbookmark(data)
{
	//alert("addbookmark"+data);
	
	// alert("aa"+mapbookmark.has(data.split("@")[0]));
	 
	 if(mapbookmark.has(data.split("@")[0]))
		 {
		 

		 swal({
			  title: "Are you sure To Delete Bookmark?",
			  text: "All test of this Bookmark will be deleted!",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-danger",
			  confirmButtonText: "Yes, delete it!",
			  cancelButtonText: "No",
			  closeOnConfirm: false,
			  closeOnCancel: false
			},
			function(isConfirm) {
			  if (isConfirm) {
				  
				  var newclassall="."+data.split("@")[0]+"bmarkk" ;
				//  alert("newclassall"+newclassall);
				  
							$(newclassall).remove();
							
							
						
					
						
						settestcount();
						settestbill();
						
						var inner1= document.getElementById("bmarkinside"+data.split("@")[0]).innerHTML
						inner1=inner1+"" ;
					//	document.getElementById("bmarkinside"+data.split("@")[0]).innerHTML=inner1;
						 $("#bmark"+data.split("@")[0]).attr('title', "Bookmark");
						 
						 if(document.getElementById("ct"+data.split("@")[0])!=null)
							 {
						 document.getElementById("ct"+data.split("@")[0]).style.display="none";
							 }
						 
						mapbookmark.delete(data.split("@")[0]);
					//	 document.getElementById("bmarkinside"+data.split("@")[0]).className = "badge badge-pill badge-info";
						// document.getElementById("bmark"+data.split("@")[0]).className = "badge badge-pill badge-info";
                   document.getElementById("bmarkinside"+data.split("@")[0]).style.color="blue";
                   map_isaddedtestwise=new Map();
					//$(newclassall).remove();
					
			    swal("Deleted!", "Succesfully", "success");
			  } else {
			    swal("Cancelled", "", "error");
			  }
			});
		 
			    return false;
	        	 
		 }
	 
	 
	// var labcode= data.split("@")[1];
	 var testdata=data.split("@")[1];
	 
	 
	 
	 
    var maintestdata=testdata.split("$$");
    
	
    for(var f=0;f<maintestdata.length;f++)
	{
	var testcode=maintestdata[f].split("#")[1];
	var labcode="";
    var grppcode="";
    var labcodep=maintestdata[f].split("#")[0];
    var grpco="";
  //  alert(maintestdata[f].split("#")[0]);
    
    if(labcodep.includes("!!"))
		{
		
    //	alert("labcodep[f]"+labcodep);
		labcode=labcodep.split("!!")[0];
		grppcode=labcodep.split("!!")[1];
	//	alert("grppcode"+grppcode);
		grpco=grppcode.split("^")[2];
	//	alert("grppcode"+grpco);

		}
	else
		{
		labcode=maintestdata[f].split("#")[0];
		}
    
    var isexist=false;

    isexist=checktestalreadyraisednew(testcode);
	   // alert("isexist"+isexist);
	  	if(isexist.split("#")[0]=="0" && grpco!="")
	  		{
	  		
	        var errmsg="Test "+isexist.split("#")[1]+" is Already Added! Please Remove first before adding this Bookmark. ";
	          document.getElementById('automplete-testsearch').value="";
	   	   swal(
		                '',
		                errmsg,
		                'warning'
		            );	
					return false;
					
	  		return null;
	  		}
	  	
	}
    
	
	for(var f=0;f<maintestdata.length;f++)
		{
		var testcode=maintestdata[f].split("#")[1];
		var labcode="";
	    var grppcode="";
	    var labcodep=maintestdata[f].split("#")[0];
	    var grpco="";
	  //  alert(maintestdata[f].split("#")[0]);
	    
	    if(labcodep.includes("!!"))
			{
			
	    //	alert("labcodep[f]"+labcodep);
			labcode=labcodep.split("!!")[0];
			grppcode=labcodep.split("!!")[1];
		//	alert("grppcode"+grppcode);
			grpco=grppcode.split("^")[2];
		//	alert("grppcode"+grpco);

			}
		else
			{
			labcode=maintestdata[f].split("#")[0];
			}
	//	alert("testciode"+testcode);
        var testdatafinal=  ajaxhitforfetchingtestwisedata(testcode+"^"+labcode+grppcode);
       // alert("testdatafinal"+testdatafinal);
		var dd2=maktestinrowtable(testdatafinal);
		//alert("dd2"+dd2);
		if(dd2==null)
			{
			
			//alert("cc");
			return null;
			}
		
		mapbookmark.set(data.split("@")[0],"1");
	//	alert("ddpppp");
	//	var li = document.getElementById("bmarkinside"+data.split("@")[0]);
		//alert(li.className);   
		// document.getElementById("bmarkinside"+data.split("@")[0]).className = "badge badge-pill badge-success";
		// document.getElementById("bmark"+data.split("@")[0]).className = "badge badge-pill badge-success";
		// alert(document.getElementById("bmarkinside"+data.split("@")[0]).innerHTML);
		 document.getElementById("bmarkinside"+data.split("@")[0]).style.color="green";
		//	alert("ddppppxx"+testcode);
				 var iddtestwise="";
				 
				 if(grppcode=="")
					 iddtestwise= document.getElementById(testcode+"idd").className;
				 else
					 iddtestwise= document.getElementById(grpco+"idd").className;
				
				 iddtestwise=iddtestwise+data.split("@")[0]+"bmarkk";
			//	alert("ddppppww");
				
				 if(grppcode=="")
					 document.getElementById(testcode+"idd").className=iddtestwise;
				 else
					document.getElementById(grpco+"idd").className=iddtestwise;
				 
			//		alert("ddpert");
		var inner= document.getElementById("bmarkinside"+data.split("@")[0]).innerHTML
		
	//	alert("xxx"+"   ct"+data.split("@")[0]+"xx"+document.getElementById("ct"+data.split("@")[0]));
	
		var dd="ct"+data.split("@")[0];
		
		if(document.getElementById("ct"+data.split("@")[0])!=null)
		 {
	
			 
		//	 alert("dd"+dd);
		 document.getElementById(dd).style.display="";
		 }
		else
			{
		inner=inner+"  <i id='ct"+data.split("@")[0]+"' class='fa fa-times' aria-hidden='true'></i>" ;
			}
		
		document.getElementById("bmarkinside"+data.split("@")[0]).innerHTML=inner;
		 $("#bmark"+data.split("@")[0]).attr('title', "This Bookmark is Already Added!");
		 
		// alert(document.getElementById(testcode).className);
		 if(document.getElementById("ct"+data.split("@")[0])!=null)
		 {
	
			 
		//	 alert("ddp");
		 document.getElementById(dd).style.display="";
		 }
		// alert("ddpww");
		 var classbmark="";
			 
		 if(grppcode=="")
			 classbmark= document.getElementById(testcode).className;
		 else
			 classbmark= document.getElementById(grpco).className;
		 
		// alert("doneee");
		 
		 classbmark=classbmark+"  "+data.split("@")[0]+"del" ;
		 
		 if(grppcode=="")
			 document.getElementById(testcode).className=classbmark;
		 else
			 document.getElementById(grpco).className=classbmark;
		 
		 
		// document.getElementById(testcode).className=classbmark;
		 
		//document.getElementsByClassName(li.className)[0].innerHTML="badge badge-pill badge-success";
	//	
	}
	
	}




$(function() {

    var advisedByList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_ADVISEDBY_NAMES_XRAYPROCESS)%>;
   setInterval(function() {
 $( "#automplete-4" ).autocomplete({
     source: advisedByList,
     select: function(event, ui) {
         $('#hiddenid4').val(ui.item.value);
         event.preventDefault();
        /// alert("advised_by"+ui.item.label);
         document.getElementsByName("adivcedbycode")[0].value=ui.item.label;
         document.getElementsByName("adivcedbyname")[0].value=ui.item.value;
         
        // alert("advised_by"+ui.item.value);
         $("#automplete-4").val(ui.item.label);
     },

 focus: function(event, ui) {
        event.preventDefault();
        $("#automplete-4").val(ui.item.label);}
  });
 }, 1000);
});



$(function() {

    var advisedByList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_ADVISEDBY_NAMES_XRAYPROCESS)%>;
   setInterval(function() {
 $( "#automplete-chief" ).autocomplete({
     source: advisedByList,
     select: function(event, ui) {
         $('#hiddenid4').val(ui.item.value);
         event.preventDefault();
        /// alert("advised_by"+ui.item.label);
         document.getElementsByName("chief_complaints_code")[0].value=ui.item.label;
         document.getElementsByName("chief_complaints_name")[0].value=ui.item.value;
         
        // alert("advised_by"+ui.item.value);
         $("#automplete-chief").val(ui.item.label);
     },

 focus: function(event, ui) {
        event.preventDefault();
        $("#automplete-chief").val(ui.item.label);}
  });
 }, 1000);
});




$(function() {

    var advisedByList=<%=(String)session.getAttribute(InvestigationConfig.ARRAY_ADVISEDBY_NAMES_XRAYPROCESS)%>;
   setInterval(function() {
 $( "#automplete-diagnosis" ).autocomplete({
     source: advisedByList,
     select: function(event, ui) {
         $('#hiddenid4').val(ui.item.value);
         event.preventDefault();
        /// alert("advised_by"+ui.item.label);
         document.getElementsByName("diagnosis_code")[0].value=ui.item.label;
         document.getElementsByName("diagnosis_name")[0].value=ui.item.value;
         
        // alert("advised_by"+ui.item.value);
         $("#automplete-diagnosis").val(ui.item.label);
     },

 focus: function(event, ui) {
        event.preventDefault();
        $("#automplete-diagnosis").val(ui.item.label);}
  });
 }, 1000);
});



$(function() {

   // var list1=document.getElementsByName('tstValArr')[0].value;
        	var availableTests =<%=(String)session.getAttribute(InvestigationConfig.ARRAY_TESTNAMES_XRAYPROCESS)%>;

	 setInterval(function() {

    $( "#automplete-testsearch" ).autocomplete({
        source: availableTests,
        select: function(event, ui) {
          //  alert(ui.item.value);
            $('#hiddenid2').val(ui.item.value);
            event.preventDefault();
            $("#automplete-testsearch").val(ui.item.label);
              //  alert("testwisesearch"+ui.item.value);
               if(ui.item.value!="")
               {

                   
            	   var dataa=ui.item.value;
            	   
            	  var  searchtyp=dataa.split("#")[2];
            	   
            	   if(searchtyp=="2" || searchtyp=="3")
            		   {
            		   
            		   var testdata=dataa.split("#")[0];
            		    var maintestdata=testdata.split("^");
            		  //  alert("111"+maintestdata);
            		
                              var isexist=false;
            		    for(var f=0;f<maintestdata.length;f++)
            				{
            				var testcode=maintestdata[f];
            			//	alert("testciode"+testcode);
            			  	isexist=checktestalreadyraisednew(testcode);
                		   // alert("isexist"+isexist);
            			  	if(isexist.split("#")[0]=="0")
            			  		{
            			  		
            			        var errmsg="Test "+isexist.split("#")[1]+" is Already Added! Please Remove first before adding this Group. ";
            			          document.getElementById('automplete-testsearch').value="";
            			   	   swal(
            				                '',
            				                errmsg,
            				                'warning'
            				            );	
            							return false;
            							
            			  		return null;
            			  		}
            			  	
            				}
            		 
            		    for(var f=0;f<maintestdata.length;f++)
            				{
            				var testcode=maintestdata[f];
            			//	alert("testciode"+testcode);
            		        var testdatafinal=  ajaxhitforfetchingtestwisedata(testcode+"^"+dataa.split("#")[1]+"^2^"+dataa.split("#")[3]);
            		  //  	alert("testdatafinal"+testdatafinal);
            				maktestinrowtable(testdatafinal);
            		        
            				}
            		    	
            		    
            			
            		   }
            	   else
            		   {
            	 //  alert("testcodee"+ui.item.value);
              var testdata=  ajaxhitforfetchingtestwisedata(ui.item.value);
            //  alert("testdata"+testdata);
              maktestinrowtable(testdata);
            		   }

               }
            //  alert("finaltestdata"+testdata);
      //      searchLabWiseTest2();
          //  availableTests= setTestComboUsingAjax2(ui.item.value);
        },

    focus: function(event, ui) {
           event.preventDefault();
           $("#automplete-testsearch").val(ui.item.label);}
     });
 }, 1000);
 });


var isapptshw="false";
var counter=0;
var applabcode="";
function maktestinrowtable(testdata)
{
      // alert(testdata);      
             
            var finaldata=testdata.split("@");
           var combo="";
             if(finaldata.length==2)
                 {
            	 combo=finaldata[1];
                 }
             var datattosave=finaldata[0];

             datattosave=datattosave.split("#");
             datattosave[4]="";
                 
             var newdatattosave="";
                	 
             for(var d=0;d<datattosave.length;d++)
            	 {
            	 newdatattosave+=datattosave[d]+"#";
            	 }
             
             datattosave=newdatattosave;
             
             var tests=finaldata[0].split("#");



        //  alert("rates"+tests[29]);
       if(checktestalreadyraised(tests[2]))
       {

           
         if(tests[29].includes('^'))
           {      
        //     alert("match");  
        //     alert("rates"+tests[29].split('^')[0]);
        //     alert("ee"+document.getElementById("estimaterate").innerHTML);
             var cc=document.getElementById("estimaterate").innerHTML;
             selectedtestbills.push(tests[29].split('^')[0]);
             selectedtest.push(tests[3]);
          //  alert((parseFloat(cc) + parseFloat(tests[29].split('^')[0])).toFixed(2));
             //document.getElementById("estimaterate").innerHTML = (parseFloat(cc) + parseFloat(tests[29].split('^')[0])).toFixed(2);
//alert("aa"+document.getElementById('estimatebtn').title);

             }
         else
             {
        	 selectedtestbills.push('0.00');
             selectedtest.push(tests[3]);
             }

         var dd="";
         for(var f=0;f<selectedtest.length;f++)
       	  {
       	   dd+=""+selectedtest[f]+ ":"+selectedtestbills[f]+" <i class='fa fa-inr' aria-hidden='true'></i><br/>";
             
       	  }

//alert(dd);

       //$('#estimatebtn').attr('data-content', dd);

       
          //document.getElementById("labshw").innerHTML = tests[1];
          
          
       // cols += '<td>Freechanks</td>';
      	var grptyp1= tests[10];
      	
      	  var colorcodee="";
      	  grptyp1=grptyp1;
      	 //alert("grptyp1"+grptyp1);
      	 var isgrp="test";
      	  if( grptyp1!=null && grptyp1!="undefined" && grptyp1!="null" && grptyp1!="0")
      	  {
      		testco1=grptyp1;
      	   testr1 = grptestratess[grptyp1];
      	   isgrp="group";
      	  }
           else
      	  {
      	  
      	//   alert("not grp"+testco);
          		testco1=tests[2];
      	   testr1 = testratess[testco1];
      	   isgrp="test" ;
      	  }
      	  
          selectedlab.push(tests[0]);
 	        var newRow = $("<tr id='"+testco1+"idd' data-toggle='collapse' data-target='#text-1' class='accordion-toggle collapsed "+testco1+"row '>");
	        var cols = "";

            if(tests[30]!="")
            {
            //	alert("in 30");
				var fncn="showprevreqmodal("+tests[3]+")";
				var crnos= $('[name="patCrNo"]').val();
				
		/* 		href='/HISInvestigationG5/new_investigation/onlineRequisitionRaisingprevreq.cnt?patCrNo="+crnos+"&searchTestName="+tests[3]+"'
		 */				
		 
		 if(tests[31]=="1")
         {
			 cols += "<td>"+tests[3]+"  &nbsp;&nbsp;<br/><a><img style='width:14' src='/HISInvestigationG5/hisglobal/css/spoon.svg'></a><span style='color:grey'> Fasting Required : <span style='color:green'>Yes</span><span style='color:green;font-size:11'> ("+tests[32]+" "+tests[33]+")</span></span></span> <br/><a class='iframee' href='#' >Last Requisition Date: "+tests[30]+"</a></td>";
				 
         }
		 else if(tests[31]=="2")
			 {
			 cols += "<td>"+tests[3]+"  &nbsp;&nbsp;<br/><a><img style='width:14' src='/HISInvestigationG5/hisglobal/css/bladder(1).svg'></a><span style='color:grey'> Full Bladder Required : <span style='color:green'>Yes</span><span style='color:green;font-size:9'> ("+tests[34]+")</span></span></span> <br/><a class='iframee' href='#' >Last Requisition Date: "+tests[30]+"</a></td>";
			 
			 }
		 else{
			 cols += "<td>"+tests[3]+"<br/><a class='iframee' href='#' >Last Requisition Date: "+tests[30]+"</a></td>";
		 }
		
			// 	 cols += "<td>"+tests[3]+"  &nbsp;&nbsp;<br/><svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'><g fill='none' fill-rule='evenodd'><path fill='#9B9B9B' fill-rule='nonzero' d='M7.333 6.2H6V2H4.667v4.2H3.333V2H2v4.2c0 1.272 1.107 2.304 2.5 2.382V14h1.667V8.582c1.393-.078 2.5-1.11 2.5-2.382V2H7.333v4.2zm3.334-1.8v4.8h1.666V14H14V2c-1.84 0-3.333 1.344-3.333 2.4z'></path><path d='M0 0h16v16H0z'></path></g></svg><span style='color:grey'> Fasting Required : <span style='color:green'>Yes (8-10 hrs After Meal)</span></span></span> <br/><a class='iframee' href='#' >Last Requisition Date: "+tests[30]+"</a></td>"; 
				// cols += "<td>"+tests[3]+"   <br/><a class='iframee' href='#' >Last Requisition Date: "+tests[30]+"</a></td>";
				 
				 
				 
            }
            else
                {
            	
           	 if(tests[31]=="1")
             {
    			 cols += "<td>"+tests[3]+"  &nbsp;&nbsp;<br/><a><img style='width:14' src='/HISInvestigationG5/hisglobal/css/spoon.svg'></a><span style='color:grey'> Fasting Required : <span style='color:green'>Yes</span><span style='color:green;font-size:11'> ("+tests[32]+" "+tests[33]+")</span></span></span></td>";
    				 
             }
    		 else if(tests[31]=="2")
    			 {
    			 cols += "<td>"+tests[3]+"  &nbsp;&nbsp;<br/><a><img style='width:14' src='/HISInvestigationG5/hisglobal/css/bladder(1).svg'></a><span style='color:grey'> Full Bladder Required : <span style='color:green'>Yes</span><span style='color:green;font-size:11'> ("+tests[34]+")</span></span></span></td>";
    			 
    			 }
    		 else{
    			 cols += "<td>"+tests[3]+" </td>";
    		 }
            //	alert("not in 30");
            	
			//	 cols += "<td>"+tests[3]+"&nbsp;&nbsp;<br/><svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'><g fill='none' fill-rule='evenodd'><path fill='#9B9B9B' fill-rule='nonzero' d='M7.333 6.2H6V2H4.667v4.2H3.333V2H2v4.2c0 1.272 1.107 2.304 2.5 2.382V14h1.667V8.582c1.393-.078 2.5-1.11 2.5-2.382V2H7.333v4.2zm3.334-1.8v4.8h1.666V14H14V2c-1.84 0-3.333 1.344-3.333 2.4z'></path><path d='M0 0h16v16H0z'></path></g></svg><span style='color:grey'> Fasting Required : <span style='color:green'>Yes</span>8-10 hrs (Before Meal)</span> </td>"; 
            //s	cols += "<td>"+tests[3]+" </td>";
            	
                }

            
/* 			    	cols += "<td><div> <select  id='"+tests[2]+"#"+tests[0]+"box' onchange='callsite(this)' class='form-control custom'> <option value='1'>NA</option> <option value='3'>Left</option> <option value='4'>Right</option> <option value='2'>Both</option> </select> </div></td>";
	    	cols += "<td>"+combo+"</td>";
	        cols += '<td><input type="text" class="form-control"></td>';
	        cols += "<td><a class='delete' title='Delete' data-toggle='tooltip' ><i class='fa fa-trash' aria-hidden='true'></i></a></td><input id='"+tests[2]+"#"+tests[0]+"' type='hidden' name='selectedtestdataatrray' value='"+datattosave+"'/><input id='"+tests[2]+"charge' type='hidden' name='testcharge' value='"+datattosave+"'/><input id='"+tests[2]+"#"+tests[0]+"view' type='hidden' name='selectedtestviews' value=''/><input id='"+tests[2]+"#"+tests[0]+"site' type='hidden' name='selectedsite' value='1'/>";
 */
 
 cols += "<td ><div>"+tests[1]+"</div></td>";
 
 if(tests[5]=="S") // Sample or Slide Based
	 cols += "<td><div> <select  id='"+tests[2]+"#"+tests[0]+"box' onchange='callsite(this)' class='form-control custom'>"+tests[4]+"</select><input id='"+tests[2]+"#"+tests[0]+"site' type='hidden' name='selectedsite' value='"+tests[8]+"'/> </div></td>";
 else
	 cols += "<td><div> <select  id='"+tests[2]+"#"+tests[0]+"box' onchange='callsite(this)' class='form-control custom'> <option value='1'>NA</option> <option value='3'>Left</option> <option value='4'>Right</option> <option value='2'>Both</option> </select> <input id='"+tests[2]+"#"+tests[0]+"site' type='hidden' name='selectedsite' value='1'/></div></td>";
	 
	
	  
	// alert("testr1"+testr1);
	
		  
	  var testrnew1="";
	  testrnew1=testr1;
	  

	 // alert("testcode  map_isaddedtestwise.has(testco1)"+testco1 +"ssssss"+ colorcodee);
	  
	  
	//alert("test  map_isaddedtestwise.has(testco1)"+map_isaddedtestwise.has(testco1));
	  
	if(! map_isaddedtestwise.has(testco1))
      	  {
		
		
      
	  if(testr1.includes("^"))
	  {
	//	  alert("true");
		  testr1=testr1.split("^")[0];
		  colorcodee=testrnew1.split("@#")[2];
	  }
	  else{
		  testr1=testr1.split("@#")[0];
		  colorcodee=testrnew1.split("@#")[2];
		//  alert("false");
	  }
	  
	  if(testr1=="0")
		 {
			  testr1="&nbsp;&nbsp;Free";
		 }
		 else
			 {
			 testr1="&nbsp;&#8377;&nbsp;"+testr1
			 }
	  
	  map_isaddedtestwise.set(testco1,colorcodee);
	  
      	  }
	else
		{
		colorcodee=map_isaddedtestwise.get(testco1);
		testr1="";
		}
	 // var tdtag="<div align='left' style='height:18 ;border-left : 4px solid "+colorcodee+" '>";
	  
	// cols += '<td>Freechanks</td>';
	
	
	 cols += "<td>"+"<div align='left' style='height:18 ;border-left : 4px solid "+colorcodee+" ' class='"+grptyp1+"' >"+testr1+"</div></td>";
	 
	 var testnamew="-";
	 
	 if( grptyp1!=null && grptyp1!="undefined" && grptyp1!="null" && grptyp1!="0")
 	  {
 		
 	  var  grpnamee = grptestratess[grptyp1];
 	
 	//   alert("grpnamee"+grpnamee);
 	   
 	
 	   if(grpnamee.includes("^"))
 		   {
 		   
 		  testnamew=grpnamee.split("@#")[1];
 		   }
 	   else if (grpnamee.includes("@"))
 		   {
 			  testnamew=grpnamee.split("@#")[1];
 		   }
 	   
 	  }
	 
	 cols += "<td>"+testnamew+"</td>";
	
	 cols += '<td><input type="text" onclick="isNumberKeynew()" name="instructiontestwise" class="textt form-control" maxlength="50"></td>';
     cols += "<td><a class='deletee "+testco1+isgrp+"' title='Delete' data-toggle='tooltip' id="+testco1+" ><i class='fa fa-trash' aria-hidden='true'></i></a></td><input id='"+tests[2]+"#"+tests[0]+"' type='hidden' name='selectedtestdataatrray' value='"+datattosave+"'/><input id='"+tests[2]+"charge' type='hidden' name='testcharge' value='"+datattosave+"'/><input id='"+tests[2]+"#"+tests[0]+"view' type='hidden' name='selectedtestviews' value=''/>";
    
     
	        
	        newRow.append(cols);
	        
	        

	        $("table.order-list").prepend(newRow);

	        var x = document.getElementById("snackbar");
            x.className = "show";
            setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);


        	if( grptyp1!=null && grptyp1!="undefined" && grptyp1!="null" && grptyp1!="0")
              	  {
        	             var isegrp=grptyp1;
        		       var len=document.getElementsByClassName(isegrp).length;
        		      // alert(len);
        		       for(var f=0;f<document.getElementsByClassName(isegrp).length;f++)
        		    	   {
        		    	   
        		    	          if(f==0)
        		    	        	  {
        		    	        	  
        		    	        	  testr1=  testrnew1 ;
        		    				  if(testr1.includes("^"))
        		    	  {
        		    	//	  alert("true");
        		    		  testr1=testr1.split("^")[0];
        		    		  colorcodee=testrnew1.split("@#")[2];
        		    	  }
        		    	  else{
        		    		  testr1=testr1.split("@#")[0];
        		    		  colorcodee=testrnew1.split("@#")[2];
        		    		//  alert("false");
        		    	  }
        		    	  
        		    	  if(testr1=="0")
        		    		 {
        		    			  testr1="&nbsp;&nbsp;Free";
        		    		 }
        		    		 else
        		    			 {
        		    			 testr1="&nbsp;&#8377;&nbsp;"+testr1
        		    			 }
        		    	        	  
        		    	        	  
        		    	        	  document.getElementsByClassName(isegrp)[f].innerHTML=testr1;
        		    	        	  }
        		    	          else
        		    	        	  {
        		    	        	  document.getElementsByClassName(isegrp)[f].innerHTML="";
        		    	        	  }
        		    	   
        		    	   }
        		
              	  }
        	
	        counter++;
	    document.getElementById('automplete-testsearch').value="";
	    if(isapptshw=="false" && tests[28]=="1")
	    {
	    	// document.getElementById('isdisapp').style.display=""; //needs to be uncomment for app
	    	
	        // setappointment(tests);
	         isapptshw="true";
	         applabcode=tests[0];

	}
	    settestcount();
	    settestbill();
       }
       else
           {
          var errmsg="Test "+tests[3]+" is Already Added!";
          document.getElementById('automplete-testsearch').value="";
   	   swal(
	                '',
	                errmsg,
	                'warning'
	            );	
				return null;

				
           }
       return "1" ;
            
       
       
       
}


function checktestalreadyraised(testtocheck)
{

	var flg=true;
	 if(document.getElementsByName("selectedtestdataatrray")!=undefined && document.getElementsByName("selectedtestdataatrray").length>0)
	    {    

  	      for(var f=0;f<document.getElementsByName("selectedtestdataatrray").length;f++)
	    	{

  	    	   var testcountdata=document.getElementsByName("selectedtestdataatrray")[f].value;
  	    	   //testcountdata=testcountdata.split("#");

               if(testcountdata.includes(testtocheck))
               {
            	   flg= false ;
            	   }

               else{
            	   
               }
  	    	  
  	    	  
	    	}

	    }
	 else{
		 
	 }
	 return flg;
	
}



function checktestalreadyraisednew(testtocheck)
{

	var flg="1#1";
	 if(document.getElementsByName("selectedtestdataatrray")!=undefined && document.getElementsByName("selectedtestdataatrray").length>0)
	    {    

  	      for(var f=0;f<document.getElementsByName("selectedtestdataatrray").length;f++)
	    	{

  	    	   var testcountdata=document.getElementsByName("selectedtestdataatrray")[f].value;
  	    	   //testcountdata=testcountdata.split("#");

               if(testcountdata.includes(testtocheck))
               {
            	   flg= "0#"+testcountdata.split("#")[3] ;
            	   }

               else{
            	   
               }
  	    	  
  	    	  
	    	}

	    }
	 else{
		 
	 }
	 return flg;
	
}




function settestcount()
{

	    var counttest=0;
	    var teststring="";
	    var map_lab=new Map();
	    var map_group=new Map();

	    if(document.getElementsByName("selectedtestdataatrray")!=undefined)
	    {    
     	 for(var f=0;f<document.getElementsByName("selectedtestdataatrray").length;f++)
	    	{
   var testcountdata=document.getElementsByName("selectedtestdataatrray")[f].value;
   testcountdata=testcountdata.split("#");
   
         
 /*   if(map_lab.has(testcountdata[0]))
	  {
	  // map_lab.set(testcountdata[0],testcountdata[1]);
	  }
      else
	  {
    	  alert("labinsert"+testcountdata[1]);
    	  map_lab.set(testcountdata[0],testcountdata[1]);
	  }
    */
   
   /* if(map_lab.has(testcountdata[0]))
	  {
	   
	   if(testcountdata[10]!=null && testcountdata[10]!="" && testcountdata[10]!="0")
 	  {
		   map_group.set(testcountdata[10],testcountdata[0]);
 	  }
   else
 	  {
	   map_group.set(testcountdata[2],testcountdata[0]);
 	  }
	   
	   
	  } */
   
	    	 counttest++;
	    	 if(teststring=="")
	   	  {
	   	  teststring=""+testcountdata[3];
	   	  }
	   	  else
	   	  {
	   	  teststring=""+teststring+","+testcountdata[3];    
	   	  }

		   	  
    		}
     	 
     	 

		  	/* var get_keys_labs = map_lab.keys(); 
		
		  	var iterator1 = map_lab.values();

		  	
		for(var ele of get_keys_labs) 
			{
			alert("eleele"+ele);
		   var labnamee=iterator1.next().value ;
		   alert(labnamee);
			
			} */
		
         /*  document.getElementById("labshwcount").innerHTML = "<b>\u00A0 \u00A0Test Count: "+counttest+"\u00A0 \u00A0</b>";
		   $('#labshwcount').attr('data-content', "Test:"+teststring+"");
	         */  
	    }
	    
			
}
	

function setappointment(tests)
{

   //  alert("tests"+tests);
	var labCode=tests[0];
	var testCode="0";

	var divAptTagRow="aptTagRow_"+labCode+"_"+testCode;  //+labCode+testCode
    
	var islabalreadycallapp=labCode+"#"+divAptTagRow+"@";
	
	
	var paraId=labCode+"^"+'0'+"^0^0^0^0^0";
	//document.getElementsByName('islabcallapp')[0].value=islabalreadycallapp;
	document.getElementById("app").innerHTML="<div id='"+divAptTagRow+"'></div> <div  align='left'><input type='hidden' name='dateTag' onchange='setDateInApoitment("+patCrNo+",\""+(paraId)+"\",this,\""+divAptTagRow+"\")' id='datepicker'></div>";
	$( "#datepicker" ).datepicker({
		dateFormat: 'dd-M-yy',
		showOn: "button",
		buttonImage: "/HISInvestigationG5/hisglobal/images/cal.png",
		buttonImageOnly: true,
		buttonText: "Select  "
		}).datepicker("setDate", new Date());
				//alert(td6.innerHTML);
				
						var paraId="";
                              var patCrNo=$('[name="patCrNo"]').val();
//						getAptSlotDetails('"+patCrNo+"','"+paraId+"','',divAptTagRow);
						
						paraId=labCode+"^"+'0'+"^0^0^0^0^0";
				        //alert("lab bsed call paraIdparaId"+paraId);
							getAptSlotDetails("\'"+patCrNo+"\'",paraId,'',divAptTagRow,4);
							
							newarrayappdatalababsed.push(labCode+"$$"+divAptTagRow);


							

	}





function settestbill()
{

	var finalcharge=0.00;

     //  alert("setestbill");
		map_isaddedcharge=new Map();
		
 	for(var f=0;f<document.getElementsByName("selectedtestdataatrray").length;f++)
	{
 	
 	//	alert("setestbillqq");
 		
 		var dataa=document.getElementsByName("selectedtestdataatrray")[f].value;
          var newdataa=document.getElementsByName("selectedtestdataatrray")[f].value;
        
          var datattofind=dataa.split("#");
          
          var grptyp1=datattofind[10] ;
    	  var colorcodee="";
    	 var   testr1 ;
    	  if( grptyp1!=null && grptyp1!="undefined" && grptyp1!="null" && grptyp1!="0")
    	  {
    		testco1=grptyp1;
    	   testr1 = grptestratess[grptyp1];
    	
    	  }
         else
    	  {
    	  
    	//   alert("not grp"+testco);
        		testco1=datattofind[2];
    	   testr1 = testratess[testco1];
    	
    	  }
    	  
    	  var testrnew1="";
    	  testrnew1=testr1;
    	//  alert("chargfe"+testco1);
    	  
    	//  alert("chargfe"+map_isaddedcharge.has(testco1));
    	  
    	  if(! map_isaddedcharge.has(testco1))
      	  {
    	  
    	  if(testr1.includes("^"))
    	  {
    	//	  alert("true");
    		  testr1=testr1.split("^")[0];
    		  colorcodee=testrnew1.split("@#")[2];
    	  }
    	  else{
    		  testr1=testr1.split("@#")[0];
    		  colorcodee=testrnew1.split("@#")[2];
    		//  alert("false");
    	  }
    	  
    	  /* 
    	  if(testr1=="0" || testr1=="0.00")
 		 {
 			  testr1="&nbsp;&nbsp;Free";
 		 }
 		 else
 			 {
 			 testr1="&nbsp;&#8377;&nbsp;"+testr1
 			 } */
 			// alert("popovers");
 			finalcharge+=parseFloat(testr1);
 			
 			map_isaddedcharge.set(testco1,"0");
 			setpopoversinchargebtn();
      	  }
    	  else
    		  {
    		  
    		  }
    	 
	}
	
 	if(document.getElementsByName("selectedtestdataatrray").length==0)
 		{
 		setpopoversinchargebtn();
 		}
 	
	document.getElementById("estimaterate").innerHTML=finalcharge;
				      //  $('#estimatebtn').attr('data-content', finalcharge);

				      

}

function setpopoversinchargebtn()
{

     var testsize=newmymapvalue.size;
      var stringg="<div class='border border-dark'><table class='table table-condensed table-responsive thead-light' width='100%' style='height:30%'>";


 		map_isaddedmodal=new Map();
		

   	for(var f=0;f<document.getElementsByName("selectedtestdataatrray").length;f++)
  	{
   	//	alert("neww");
   		  var dataa=document.getElementsByName("selectedtestdataatrray")[f].value;
            var newdataa=document.getElementsByName("selectedtestdataatrray")[f].value;
          
            var datattofind=dataa.split("#");
            
            var grptyp1=datattofind[10] ;
      	  var colorcodee="";
      	 var   testr1 ;
      	  if( grptyp1!=null && grptyp1!="undefined" && grptyp1!="null" && grptyp1!="0")
      	  {
      		testco1=grptyp1;
      	   testr1 = grptestratess[grptyp1];
      	
      	  }
           else
      	  {
      	  
      	//   alert("not grp"+testco);
          		testco1=datattofind[2];
      	   testr1 = testratess[testco1];
      	
      	  }
      	  
      	//  alert("popup map_isadded.has(testco1)sss"+testco1);

      	//  alert("popup map_isadded.has(testco1)"+map_isadded.has(testco1));
      	  
      	  if(!map_isaddedmodal.has(testco1))
      	  {
      		  
      	  var testrnew1="";
      	  testrnew1=testr1;
      	  
      	  var testname="";
      //	  alert("testr1testr1"+testr1);
      	  if(testr1.includes("^"))
      	  {
      	//	  alert("true");
      		  testr1=testr1.split("^")[0];
      		  colorcodee=testrnew1.split("@#")[2];
        		testname=testrnew1.split("@#")[1];

      	  }
      	  else{
      		  testr1=testr1.split("@#")[0];
      		  colorcodee=testrnew1.split("@#")[2];
        		testname=testrnew1.split("@#")[1];

      		//  alert("false");
      	  }
      	  
      	  
      	  if(testr1=="0" || testr1=="0.00")
   		 {
   			  testr1="&nbsp;&nbsp;Free";
   		 }
   		 else
   			 {
   			 testr1="&nbsp;&#8377;&nbsp;"+testr1
   			 }
   			 
      	stringg+="<tr><td>"+testname+"</td><td>"+ testr1 +"</td></tr>";
      
      	map_isaddedmodal.set(testco1,"1");
      	
      	  }
      	  else
      		  {
      		  
      		  }
  	}
   	
   	if(document.getElementsByName("selectedtestdataatrray").length==0)
   		{
   		stringg+="<tr><td><div style='border:1;font-size:12'><strong>No <span style='color: blue;'>Test/Group </span>Raised</strong></div></td></tr>";
   		}
   	
    stringg+="</table></div>";
   // alert("stringg"+stringg);
 	 //$('#estimatebtn').attr('data-content', stringg);
	
 	var buttonTippy = document.querySelector('#estimatebtn');
    tippy(buttonTippy);
    var estimateRateTippyInstance = buttonTippy._tippy;    
    estimateRateTippyInstance.setProps({
            content: stringg
            });
    //estimateRateTippyInstance.show(0);
	
    /* estimateRateTippyInstance.show(0) 
        setTimeout(function() {
        	estimateRateTippyInstance.hide();
        }, 500);
     */

	}


function checkbilling()
{
	//alert("ajaxhit");
      var crnootocheck=$('[name="patCrNo"]').val()
	//testlabcode = testlabcode.replace(/#/g, '^');
	//alert("new"+selectedText+"#"+reqdno);
	
	//alert(tmpSampleCode+"  "+tmpLabCode+"     "+tmpTestCode+"    "+tmpLabTestCodeArray);
	var remarks = "";
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_CHECKBILLING";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/onlineRequisitionRaisingreq.cnt?hmode="+_mode+"&patCrNo="+crnootocheck, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			remarks = data;
	    // alert(data);
			//labTestCodeArray = data;
			
		},
        error: function(error)
        {
        	
        }};
	var objDojoAjax = dojo.xhrPost(objXHR) ;
	//alert("new"+remarks);
	return remarks; 
	
}


function ajaxhitforfetchingtestwisedata(testlabcode)
{
	//alert("ajaxhit"+testlabcode);
       var totaltests="";
	testlabcode = testlabcode.replace(/#/g, '^');
       
      
       
	//alert("new"+selectedText+"#"+reqdno);
	var cr=$('[name="patCrNo"]').val();
	var sele="";
	var mpp=new Map();
	
	if(document.getElementsByName("selectedtestdataatrray")!=null && document.getElementsByName("selectedtestdataatrray")!=undefined && document.getElementsByName("selectedtestdataatrray")[0]!=null  && document.getElementsByName("selectedtestdataatrray")[0]!=undefined)
		{
		
		for(var g=0;g<document.getElementsByName("selectedtestdataatrray").length;g++)
		{ 
                      var ff=document.getElementsByName("selectedtestdataatrray")[g].value;
                     // alert("ddd"+ff);
                      ff=ff.split("#");
                      
                      if(ff[10]!=null && ff[10]!="" && ff[10]!="0")
                    	  {
                    	  mpp.set(ff[10]+"@2","0");
                    	  }
                      else
                    	  {
                    	  mpp.set(ff[2]+"@1","0");
                    	  }
			
		}
		}
		
	
		
		 var searchtype=testlabcode.split("^")[2];
	       
	//	 alert("searchtype"+searchtype);
		 
	       if(searchtype=="2" || searchtype=="3")
	       {
	    	   
	    	  // alert(testlabcode);
	     	 
	         if(mpp.has(testlabcode.split("^")[3]))
	        	 {
	        	//alert("haskey"+testlabcode.split("^")[3]);
	        	 }
	         else
	        	 {
	        	// alert("testlabcode"+testlabcode.split("^")[3]);
	        	 mpp.set(testlabcode.split("^")[3]+"@2","0");
	        	// totaltests+=testlabcode.split("^")[3]+"@2$$" ;
	        	 }
	         
	       }
	       else
	       {
	    	   if(mpp.has(testlabcode.split("^")[0]))
	      	 {
	      	
	      	 }
	       else
	      	 {
	    	   mpp.set(testlabcode.split("^")[0]+"@1","0");
	      	// totaltests+=testlabcode.split("^")[0]+"@1$$" ;
	      	 }
	    	   
	       }
	       
		var get_keys = mpp.keys(); 
		
		for(var ele of get_keys) 
		  totaltests+=ele+"$$" ;
		 
		
		
		
		 testlabcode = testlabcode.replace(/\^/g, "@");
	
	//alert("totaltests"+testlabcode);
	//
	
	
//	return null;
	//alert(tmpSampleCode+"  "+tmpLabCode+"     "+tmpTestCode+"    "+tmpLabTestCodeArray);
	var remarks = "";
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_GETTESTWISEDATA";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/onlineRequisitionRaisingreq.cnt?hmode="+_mode+"&testlabcode="+testlabcode+"&patCrNo="+cr+"&totalselectedtest="+totaltests, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			remarks = data;
			
			if(remarks=="1")
				{
				
				  var errmsg="Patient Account Balance Going To Be Insufficient. Please Deposit Part Payment!!";
		          document.getElementById('automplete-testsearch').value="";
		   	   swal(
			                '',
			                errmsg,
			                'warning'
			            );	
						return false;
						
				}
			
	    // alert(data);
			//labTestCodeArray = data;
			
		},
        error: function(error)
        {
        	
        }};
	var objDojoAjax = dojo.xhrPost(objXHR) ;
	//alert("new"+remarks);
	return remarks; 
	
}


function ajaxispatexist()
{
	//alert("ajaxhit");

	//alert("new"+selectedText+"#"+reqdno);
	
	//alert(tmpSampleCode+"  "+tmpLabCode+"     "+tmpTestCode+"    "+tmpLabTestCodeArray);
	var remarks = "";
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_CHECKPATCRNO";
	var patcr=$('[name="patCrNo"]').val();
	var objXHR = {url: "/HISInvestigationG5/new_investigation/onlineRequisitionRaisingreq.cnt?hmode="+_mode+"&patCrNo="+patcr, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			remarks = data;
	  //  alert("isexist"+data);
			//labTestCodeArray = data;
			
		},
        error: function(error)
        {
        	
        }};
	var objDojoAjax = dojo.xhrPost(objXHR) ;
	//alert("new"+remarks);
	return remarks; 
	
}

$(document).ready(function() {


$('.textt').keypress(function(event){
    
	
	if($('.textt').length){  
		
		console.log($('.textt').length);
	$('.textt').off().on("keypress", function (e) {
	    var regex = new RegExp("^[A-Za-z0-9? ,_-]+$");
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (regex.test(str)) {
	        return true;
	    }

	    e.preventDefault();
	    return false;
	});
	}
	
});


});

	function isNumberKeynew()
	{
		
		    // console.log("chankssssssss");
		if($('.textt').length){  
			
			console.log($('.textt').length);
		$('.textt').off().on("keypress", function (e) {
		    var regex = new RegExp("^[A-Za-z0-9? ,_-]+$");
		    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		    if (regex.test(str)) {
		        return true;
		    }

		    e.preventDefault();
		    return false;
		});
		}
		}


function isNumberKeytestsearch(evt)
{
	
	 if(evt.keyCode==13)
		{

		return false;

		
		}
	 else
	 {
		 return true;
		 }
	
	

}  

           function showreq()
		  {
		  		      
        	 var isexistt= ajaxispatexist();

        	// alert("isexistt"+isexistt);

        	 var tocheck=isexistt.split("#");
        	// alert("tocheck"+tocheck);

        	 if(tocheck[0]=="1")
        	 {
            	// alert("enter");

        	               if(tocheck[1]=="1")
        	 			  {

        	              	 document.getElementsByName('patCrNo')[0].value="";
             	 			  
        	 			   swal(
        	 			                '',
        	 			                'Patient Category Is Expired.',
        	 			                'warning'
        	 			            );	
        	 						return false;
        	 			  }
        	 			  
        	 			  if(tocheck[2]=="1") //ipd
        	 			  {

            	 			  if(tocheck[3]=="1") //acc doesnt exist
            	 			  {
            	             	 document.getElementsByName('patCrNo')[0].value="";
                                 
            	 			   swal(
        	 			                '',
        	 			                'Patient Account Does Not Exist.',
        	 			                'warning'
        	 			            );	
   	 			            
        	 						return false;
            	 			  }

            	 			  
        	 			  }
        	 			  


        	 	        	
        	         	 
        					document.getElementsByName('hmode')[0].value="GETPATDTL";
        					
        					document.forms[0].submit();	
        					
        	 }
        	 else
        	 {
            	 //alert("not valida");
            	// document.getElementsByName('patCrNo')[0].value="";
            	 
            	  swal(
			                '',
			                'Please Enter Valid CrNo.',
			                'warning'
			            );

     		     return false;
     		     
          	            	 }


        	 
        	 
        	 

				
		  }
 
	function loadDatatable() {
		$('#prevRequisitionDtl').DataTable();
	}

	$(document).ready(function() {
		$('.multiselectt').multiselect({
			enableFiltering : true

		});
	  //  $(".testsearchmodal").prop('checked', $(this).prop("checked"));
	    
		//$('#testtablelistoftest').DataTable();
	/* 	 $('#testtablelistoftest').DataTable({
    responsive: true,
    ordering: false,
    
    "pageLength":12,

    "lengthMenu": [2,4,6,8, 12, 16, 20,50, 100,All],
    
    });
    */
  
		 

		 $('#testtablelistoftest').DataTable( {
    	
    	  } );
    
		  
	});

	$(document).ready(function() {
		$('.multiselectt').multiselect({
			enableFiltering : true

		});
	});

	$(document).ready(function() {

		$(document).on("click", ".deletee", function() {
			$(this).parent().parent().remove();
		
			var classname=this.className;
			var newclass1=classname.replace(/deletee /g, '');
		//	alert("newclass1"+newclass1);
			var newd="";
			var grp_test="";
			 if(newclass1.includes("del"))
			{
				//alert( "lennn"+document.getElementsByClassName(newclass1).length);
			
				 if(newclass1.includes("group"))
					 {
					  newd=newclass1.split("group")[1];
					  grp_test=newclass1.split("group")[0];
					// alert("newd"+newd);
					 }
				
				 
				 if(newclass1.includes("test"))
				 {
					 
					  newd=newclass1.split("test")[1];
					  grp_test=newclass1.split("test")[0];
					// alert("newd1"+newd);
					 
				 }
			 
				// alert("newd1"+newd);
				
					newd=newd.replace(/\s+/g, '');
				//	 alert("bmarkinside"+newd);
				
					 newd=newd.replace(/del/g, '');
					 mapbookmark.delete(newd);
					 $("#bmark"+newd).attr('title', "Bookmark");
				//	 alert("newdaa"+newd);
					   document.getElementById("bmarkinside"+newd).style.color="blue";
					   document.getElementById("ct"+newd).style.display="none";
				
				 
				newclassall="."+newd+"bmarkk";
			//	alert("grp enter");
				$(newclassall).remove();
				
			}
			 else if(newclass1.includes("group"))
			{
				//alert( "lennn"+document.getElementsByClassName(newclass1).length);
			
				var newclassall=newclass1.replace(/group/g, '');
				newclassall="."+newclassall+"row";
			//	alert("grp enter");
				$(newclassall).remove();
				
				
			
			}
			
			else
				{
				
				}
			
			var newclass=newclass1.replace(/group/g, '');
			newclass=newclass.replace(/test/g, '');
			
			//alert(newclass);

			//alert("a"+map_isaddedtestwise);
			//alert(newclass);
		//	alert(grp_test);
			grp_test=grp_test.replace(/group/g, '');
			grp_test=grp_test.replace(/test/g, '');
			
			map_isaddedtestwise=new Map();
			
			//alert("ff"+ this.className);
			
			
			settestcount();
			settestbill();
		});
	});

	$(document).ready(function() {
		$('[data-toggle="popover"]').popover({
			 html: true,
			placement : 'bottom',
			trigger : 'hover'
		});
	});

	$(document).ready(function() {
		$('[data-toggle="popoverselectedtest"]').popover({
			 html: true,
			placement : 'left',
			trigger : 'hover'
		});
	});
</script>


</head>

<body onload="shwhide()" class="img responsive" >
	<html:form action="/onlineRequisitionRaisingreq">

<script>
var map_isadded = new Map();
var map_isaddedtestwise = new Map();
var map_isaddedcharge = new Map();
var map_isaddedmodal = new Map();
var isextracost="0" ;

</script>

<%

String data="0";

if(session.getAttribute("issuffientamountforgroup")!=null)
{
	data=	(String)session.getAttribute("issuffientamountforgroup");

}



%>


<script>

isextracost=<%=data%> ;

</script>
	
	<html:hidden name="InvestigationRaisingDtlreqFB" property="cashCrNo" />

<input type="hidden" name="hmode" />
<input type="hidden" name="reqchiefcode" />
<input type="hidden" name="reqchiefname" />
<input type="hidden" name="reqdiagcode" />
<input type="hidden" name="reqdiagname" />
<input type="hidden" name="varSSOTicketGrantingTicket" />


   <input type="hidden" name="cashCrNo" />
   
      <input type="hidden" name="selectedEpisodedetails" value="" />
                  <input type="hidden" name="shwdiv"  />
      
      
<input type="hidden"  name="appoitmentNo" />
<input type="hidden"  name="hidAptNo" />

<input type="hidden"  name="labTestAptDate" />
<input type="hidden"  name="labTestAptTime" />
<input type="hidden"  name="labTestAptRefNo" />


<input type="hidden" 
			name="appointmentDate" />

		<input type="hidden" 
			name="appointmentTime" />
			<input type="hidden"  name="appointmentRefNo" />

					<input type="hidden" 
			name="offlineAppoitMentDate" />
		<input type="hidden" 
			name="offlineAppoitmentDtl" />
      	<input type="hidden" 
			name="offlineAptDtl" />
      



	
		<fieldset>
		
		<div class="modal" id="searchPopupModalID">
    			<div class="modal-dialog modal-lg" style="max-width:90vw; max-height: 65vh;">
      				<div class="modal-content">
      
        			<!-- Modal Header -->
        			<div class="modal-header" style="height:11vh;">
          				<h3 class="modal-title text-left" >Patient Search</h3>
          					<b><button type="button" class="close" data-dismiss="modal" style="margin-top:-28px;"><b><strong>&times;</strong></b></button></b>
        			</div>
        			
        
       				<!-- Modal body -->
        				<div class="modal-body" style="width:90vw; height: 65vh;">
          					<iframe src="" style="width: 100%; height: 100%; border: none;"></iframe>
        				</div>
        
       				</div>
    			</div>
  			</div>  
  			
			<legend style="text-align: left;"><label class="MainHeader">Online Requisition Raising</label>
					<div class="legend2" align="right">
							
				<div class="row">
					<div class="">
						<%-- <pDtl:patDtlNew crNo="961011900013787" address="false"></pDtl:patDtlNew> --%>
			     
					</div>
					</div>
						
								 <button  type="button" class=" btn btn-success btn-circle" id="submitBothId" style="cursor: pointer;display:none"  data-toggle="modal"  style="" onclick="save()">
									  <i class="fa fa-save" aria-hidden="true" title="Save" style="font-size: 16px"></i>
								</button>
							<!-- 	<button  type="button" class=" btn btn-warning btn-circle clearBtns" id="clearId" style="border: none;display:none" onclick="cancel()">
									  <img alt="Clear" src="/HIS/hisglobal/images/clear3.png" style="width: 23px; color: #fff;">
								</button>  -->
						
					 							
							<button  type="button" class=" btn btn-danger btn-circle "  onclick="cancel()" >
							<i class="fas fa-ban" aria-hidden="true" title="Cancel" style="font-size: 16px"></i>
							
							</button>        
						
									
			
						
							                                                
					</div>
			</legend>
			
		
					
			<%-- <div class="" id="nonprintableDiv3">
				<div class="row text-center">
					<div id='divErrorMsgId' class=" col-sm-12 errorMsg text-center" style="width: 100%">
						<s:property value="%{#errorMessageForJsp}"/>
					</div>
				</div>
			</div> --%>
			
			<%-- <s:set name="theme" value="simple" scope="page" /> --%>
				<div class="text-center" id="ismsg" style="display:none;float:left">
				<div class="row" style="float:left">
				
					<div id='divNormalMsgId' class="msgSucess text-center" style="width: 100%">
					
					</div>
				</div>
				
				</div>
			
	
					
			
			<%-- 	<div class="container-fluid"  id="cont1"> 
				
				
						<h3>
							 <div class="row">
								<div class="col-sm-12 header" style="background-image: linear-gradient(to right, #49B2F3, #02629C); color: #fff; padding: 4px; margin-top: -17px;" >
									<s:text name="Patient Revisit"/>&nbsp;
								</div>
		 					</div> 
						</h3>
					
					
		 <div id="inptCrid" class="" style="margin-left: -26px; margin-right: -28px;">
		 	
		 	
		 	
		 	<his:InputCrNoInvestigationTagnew name="InvestigationRaisingDtlreqFB">
			</his:InputCrNoInvestigationTagnew>
				<his:InputCrNoTag />
		 
		 <div class="text-center" id="ismsg" style="">
				<div class="row">
				
					<div id='divNormalMsgId' class="msgSucess text-center" style="width: 100%;margin-right:280">
					<his:status />
					</div>
				</div>
				
				</div>
				
				
		 </div>
		 
		 
				
		<!--  <div class="viewport"> -->
		</div> --%>
		
		
		
		
		<div class="card shadow p-3 mb-5 bg-white rounded" style="margin:10px 10px 10px 10px" id="cont1">
  <div class="card-body" style="padding-top:0px;padding-bottom:1">
    
 <div class="col-sm-12 " style="; padding-bottom: 0px; padding-top: 15px; padding-right: 0px;padding-left: 5px;" >
       
 
    <div id="inptCrid" class="" style="margin-left: -26px; margin-right: -28px;">
		 	
		 	
		 	
		 	<his:InputCrNoInvestigationTagnew name="InvestigationRaisingDtlreqFB">
			</his:InputCrNoInvestigationTagnew>
				<%-- <his:InputCrNoTag /> --%>
		 
		 <div class="text-center" id="ismsg" style="">
				<div class="row">
				
					<div id='divNormalMsgId' class="msgSucess text-center" style="width: 100%;margin-right: 280">
					<his:status />
					</div>
				</div>
				
				</div>
				
				
		 </div>



</div>


  </div>


</div>

		  
<%-- 
<div class="container-fluid container-responsive"  style="background-color:lightcyan" id="cont1">
	 	<div class="div divtable accordion-xs " style="background-color:337AB7;color:white" >
  <div class="tr" align="center">
    <b><i class="fa fa-user-md" aria-hidden="true"></i> XRAY Requisition Raising</b>
    
  </div></div>
  <br>
       <div class="login-clean animated infinite zoomInUp" style="animation-iteration-count:1">
            <div class="illustration"><i class="fa fa-user-md" aria-hidden="true"></i></div>
            <div class="form-group">      <input type="text" name="patCrNo" class="form-control" placeholder="Enter CR Number" value=""  onkeypress="return isNumberKey(event)"  required maxlength="15" ></div>
            <div class="form-group"><button class="btn btn-success btn-block" type="button" onclick="showreq()" id="getreq">Go <i class="fa fa-sign-in" aria-hidden="true"></i></button>
			<button type="button" class="btn btn-danger btn-block" onclick="cancelFunc()">Cancel <i class="fa fa-ban" aria-hidden="true"></i></button>
			<!-- <button class="btn btn-dark btn-block" style="background-color: gray;color:white" type="submit">Patient Search <i class="fa fa-search" aria-hidden="true"></i></button> --></div>
     <his:status />
    </div>
   
    
    </div> --%>
    
    
    <script>
    var testratess="";
var grptestratess="";
</script>


	<logic:present name="<%=InvestigationConfig.ALL_TEST_RATE%>">
				<%
				String testratesdata=	(String)session.getAttribute(InvestigationConfig.ALL_TEST_RATE);
			
%>
 <script>
   testratess=<%=testratesdata %>;

					</script>
					
</logic:present>


<logic:present name="<%=InvestigationConfig.ALL_GROUP_RATE%>">
				<%
				String grptestratesdata=	(String)session.getAttribute(InvestigationConfig.ALL_GROUP_RATE);
			
%>
 <script>
   grptestratess=<%=grptestratesdata %>;

					</script>
</logic:present>	
				

<logic:present name="<%=InvestigationConfig.PATIENT_VO%>">
               <%
               
              Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
           	String testnotaddviews=(String)session.getAttribute(InvestigationConfig.testfornotaddingviewscharge);
           	%>
           	<script>
           	testfornotaddviews='<%=testnotaddviews%>';
           	</script>
	<!-- <div class="container-fluid col-md-10" style="display:none" id="cont3">
	 -->
	 
	 
	 <div class="container-fluid mt-2 mb-3"  id="cont3">
	 
	 
   <input type="hidden"  value="<%=lstPatVO.getPatCategoryCode()%>" name="patCatCode"/>
   <input type="hidden" name="gtypee"  value="<%=lstPatVO.getPatGenderCode()%>">
			       <input type="hidden" name="gagee"  value="<%=lstPatVO.getPatAge()%>">
			       
	 	 <pDtl:patDtlNew crNo="<%=lstPatVO.getPatCRNo()%>" address="false"></pDtl:patDtlNew> 
	 
	 
	 
	
</div>
	 
	 
	   <div class="modal right fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" data-keyboard="false" data-backdrop="static">
					<div class="modal-dialog modal-lg blurred-box" role="document">
						<div class="modal-content " style="border: 7px solid white;border-radius: 2rem !important;background-color:gainsboro; ">
			
						
			
							<div class="modal-body " style="overflow-y: auto;">
							<ul class="list-group">
			    <%-- <li class="list-group-item">Category :<%=lstPatVO.getPatCategory() %>
			    		 	<input type="hidden"  value="<%=lstPatVO.getPatCategoryCode()%>" name="patCatCode" />
			    
			</li>
			    <li class="list-group-item">Father Name:  <%=lstPatVO.getPatGuardianName() %></li>
			        <li class="list-group-item">Mobile No: <%=lstPatVO.getPatMobileNo() %></li> --%>
			    </ul>
			    
			    <%List<Inv_EpisodeVO> lstPatEpisodeVO=(List<Inv_EpisodeVO>)session.getAttribute(InvestigationConfig.LIST_EPISODE_VO);
			    
			    if(lstPatEpisodeVO!=null && lstPatEpisodeVO.size()>0 )
			 	{%>
			 	
			 	    					<h4 style='text-align:center'>Episode Details</h4>
			 	            <input type="hidden" name="selectedEpisode" value="" />
			 	
			 	<table class="table">
			    <thead>
			      <tr>
			              <th></th>
			      
			        <th> Visit Date </th>
			        <th> Department/Unit </th>
			        <th>Diagnosis</th>
			      </tr>
			    </thead>
			    <tbody>
			    <%
			    	int sizePatEpisodeVO=lstPatEpisodeVO.size();
			    	   boolean flg=true;
			    	for(int k=0;k<sizePatEpisodeVO;k++)
			 		{
			    		Inv_EpisodeVO voPatEpisode=lstPatEpisodeVO.get(k);
			 			String patCrNo=voPatEpisode.getPatCRNo();
			    		String visit_reason=voPatEpisode.getVisit_reason_new()==null?"":voPatEpisode.getVisit_reason_new() ;
			    		String visit_reason_code=voPatEpisode.getVisit_reason_code()==null ? "" : voPatEpisode.getVisit_reason_code();
			    		String diagnosiss=voPatEpisode.getNewdiagnosis()==null ? "" : voPatEpisode.getNewdiagnosis();
					 	 
			    		   String reason[]=visit_reason_code.split("#");
			    		   String reasonname[]=visit_reason.split("#");

			   
			    
			    
                            if(flg==true)
                            {
			        	for(int kp=0;kp<reason.length;kp++)
			        		{
			        		
			        		%>
			        		
			        		<script>
			        		
			        		var visitcode='<%=reason[kp]%>';
		        			var visitname='<%=reasonname[kp]%>';
		        			
		        			if(visitname!=null && visitname!="" && visitcode!=null && visitcode!="")
		        				{
		        			mapcheifcompaints.set(visitname,visitcode);
		        			
		        			
		        			mapcheifcompaints_alreadyraised.set(visitname,visitcode);
		        				}
		        			</script>
			        	<%} }%>
			        		<%
			        		 if( flg==true && !diagnosiss.equals(""))
					         {
					        	 String newdiag=diagnosiss;
					        	 
					        	 if(newdiag.contains("$"))
					        	 {
					        		String newdiagg[]=newdiag.split("\\$") ;
					        		 
					        		for(int k1=0;k1<newdiagg.length;k1++)
					        		{
					        			String totalcode[]=newdiagg[k1].split("@");
					        			String diagcode=totalcode[0];
					        			String diagname=totalcode[1];
					        			%>
					        			
					        			<script>
					        			
					        			var diagcode='<%=diagcode%>';
					        			var diagname='<%=diagname%>';
					        			if(diagcode!=null && diagcode!="" && diagname!=null && diagname!="")
				        				{
					        			mapcheifcompaintsnew.set(diagcode,diagname);
					        			mapcheifcompaintsnew_alreadyraised.set(diagcode,diagname);
				        				}
					        			//alert(diagname);
					        			</script>
					        	<%		
					        		}
					        		 
					        	 }
					        	 else
					        	 {
					        		  String totalcode[]=newdiag.split("@");
					        			String diagcode=totalcode[0];
					        			String diagname=totalcode[1];
					        		
		                             %>	<script>
					        			
					        			var diagcode='<%=diagcode%>';
					        			var diagname='<%=diagname%>';
					        			if(diagcode!=null && diagcode!="" && diagname!=null && diagname!="")
				        				{
					        			mapcheifcompaintsnew.set(diagcode,diagname);
					        			mapcheifcompaintsnew_alreadyraised.set(diagcode,diagname);
				        				}
					        			//alert(diagname);
					        			</script>
					        	<% }
					        	 
					         }
			        		
			        		
			        		%>	
			     
			      <tr>
			   <td>
			   
			   <% if(flg==true){ %>
			   <input type="radio" name="radioEpisode" checked="checked" value="<%=voPatEpisode.getPatepisodecode()+'#'+voPatEpisode.getPatvisittypecode()%>" onclick="setchecked(this,'<%=k%>')"/>
			   
			   <%}else{ %>
			   <input type="radio" name="radioEpisode" value="<%=voPatEpisode.getPatepisodecode()+'#'+voPatEpisode.getPatvisittypecode()%>" onclick="setchecked(this,'<%=k%>')"/>
			   <%} %>
			   			   <input type="hidden" name="selectedepisodeee" value="<%=voPatEpisode.getPatepisodecode()%>"  />
			   			   			   <input type="hidden" name="selectdiagnosisname" value="<%=diagnosiss%>"  />
			   			   			   <input type="hidden" name="selectreasons" value="<%=visit_reason%>"  />
			   			   			   <input type="hidden" name="selectreasonscode" value="<%=visit_reason_code%>"  />
			   
			   </td>
			        <td><%=voPatEpisode.getPatvisitdate() %></td>
			        <td><%=voPatEpisode.getDepartment()+" / "+voPatEpisode.getPatdeptunit() %></td>
			        <td><%=voPatEpisode.getDiagnosis() %></td>
			      </tr>
						
			  
			  <%    flg=false;
			 		}%>
			 		
			 		 </tbody>
			  </table>
			  
			<div class="d-flex" >  <button type="button" class="btn btn-primary ml-auto" onclick="closemodel()">Done</button>
</div>  
			 		<%
			 	}
			    
			  %>
			  
			  
			  <%List<Inv_PatientAdmissionDtlVO> lstPatAdmissionVO=(List<Inv_PatientAdmissionDtlVO>)session.getAttribute(InvestigationConfig.LIST_ADMISSION_VO);
			    
			    if(lstPatAdmissionVO!=null && lstPatAdmissionVO.size()>0 )
			 	{%>
			 	
			 	    					<h4 >Admission Details</h4>
			 	
			 	<table class="table">
			    <thead>
			      <tr>
			      
			      
			        <th> Admitted Department Name </th>
			        <th> Ward Name </th>
			        <th>Room Name</th>
			             
			      </tr>
			    </thead>
			    <tbody>
			    <%
			    	int sizePatEpisodeVO=lstPatAdmissionVO.size();
			    	   boolean flg=true;
			    	for(int k=0;k<sizePatEpisodeVO;k++)
			 		{
			    		Inv_PatientAdmissionDtlVO voPatEpisode=lstPatAdmissionVO.get(k);
			    		
			 	
			    %>
			    
			     
			      <tr>
			   
			        <td><%=voPatEpisode.getAdmitteddepartmentname() %></td>
			        <td><%=voPatEpisode.getPatwardname()%></td>
			        <td><%=voPatEpisode.getPatroomname() %></td>
			        
			        <%
			         if(voPatEpisode.getNewdiagnosis()!=null && !voPatEpisode.getNewdiagnosis().equals(""))
			         {
			        	 String newdiag=voPatEpisode.getNewdiagnosis();
			        	 
			        	 if(newdiag.contains("$"))
			        	 {
			        		String newdiagg[]=newdiag.split("\\$") ;
			        		 
			        		for(int k1=0;k1<newdiagg.length;k1++)
			        		{
			        			String totalcode[]=newdiagg[k1].split("@");
			        			String diagcode=totalcode[0];
			        			String diagname=totalcode[1];
			        			%>
			        			
			        			<script>
			        			
			        			var diagcode='<%=diagcode%>';
			        			var diagname='<%=diagname%>';
			        			mapcheifcompaintsnew.set(diagcode,diagname);
			        			mapcheifcompaintsnew_alreadyraised.set(diagcode,diagname);
			        			//alert(diagname);
			        			</script>
			        	<%		
			        		}
			        		 
			        	 }
			        	 else
			        	 {
			        		  String totalcode[]=newdiag.split("@");
			        			String diagcode=totalcode[0];
			        			String diagname=totalcode[1];
			        		
                             %>	<script>
			        			
			        			var diagcode='<%=diagcode%>';
			        			var diagname='<%=diagname%>';
			        			mapcheifcompaintsnew.set(diagcode,diagname);
			        			mapcheifcompaintsnew_alreadyraised.set(diagcode,diagname);
			        			//alert(diagname);
			        			</script>
			        	<% }
			        	 
			         }
			        
			        
			        String visit_reason=voPatEpisode.getVisit_reason_new()==null?"":voPatEpisode.getVisit_reason_new() ;
		    		String visit_reason_code=voPatEpisode.getVisit_reason_code()==null ? "" : voPatEpisode.getVisit_reason_code();
		    		 
		    		if(!visit_reason.equals("") && !visit_reason_code.equals(""))
		    		{
		    		   String reason[]=visit_reason_code.split("#");
		    		   String reasonname[]=visit_reason.split("#");

		   
		    
		    
                             	
		        	for(int kp=0;kp<reason.length;kp++)
		        		{
		        		
		        		%>
		        		
		        		<script>
		        		
		        		var visitcode='<%=reason[kp]%>';
	        			var visitname='<%=reasonname[kp]%>';
	        			
	        			if( visitname!="" && visitcode!="")
	        				{
	        			mapcheifcompaints.set(visitname,visitcode);
	        			mapcheifcompaints_alreadyraised.set(visitname,visitcode);
	        				}
	        			</script>
		        	<%}} %>
			       
			        
			        
			      </tr>
						  <thead>
			      <tr>
			      
			      
			        <th>Bed Name</th>
			        <th>Consultant Name</th>
			        <th>Diagnosis</th>
			             
			      </tr>
			    </thead>
			    
			    
			      <tr>
			   
			        <td><%=voPatEpisode.getBedname()==null?"-":voPatEpisode.getBedname() %></td>
			        <td><%=voPatEpisode.getConsultantName()==null?"-":voPatEpisode.getConsultantName()%></td>
			        <td><%=voPatEpisode.getDiagnosis() %></td>
			        
			        
			      </tr>
			      
			  
			  <%    flg=false;
			 		}%>
			 		
			 		 </tbody>
			  </table>
			  
			  	<div class="d-flex" >  <button type="button" class="btn btn-primary ml-auto" onclick="closemodel()">Close</button>
</div>  

<input type="hidden" name="isadmitted" value="0">

			 		<%
			 	}
			    
			  %>
			  
			  	</div>
			
						</div><!-- modal-content -->
					</div><!-- modal-dialog -->
				</div><!-- modal -->
				

        <!-- -------------end patient tile------------- -->

<!--    -----------------------start billing bookmark tile-------------------       -->

			<div id="cont4" class=" container-fluid mt-2 mb-3" >
							
<div  id="cont4card1" class=" row p-2 align-items-center shadow rounded bg-white no-guttersNEW">
	
	<div class="col-md-3 d-flex justify-content-center prevReqd-col">
	
	<button id="estimatebtn" style="background-color:green;color:white;;font-size:12" type="button" class="btn btn-sm" role="button"  >
		<!-- <button id="estimatebtn" style="background-color:green;color:white; " type="button" class="btn btn-sm" data-toggle="popover" title="" data-html="true" data-content=""> -->
			<b> Estimate:</b> <i class="fa fa-inr" aria-hidden="true"></i>&#8377;&nbsp;<span id='estimaterate'>0.00</span>
		</button>
		
	
		
		
		&emsp;		
		<button style="background-color:34495E; color:white;font-size:12" type="button" class="btn btn-hover1 btn-sm" >
			<a style="color:white" id="prevreqshww"  class="iframee"><b>Previous Requisition</b></a>	</button>	
	</div> 

	<div class="col-md-4 d-flex justify-content-center search-col">
		<div class="input-group animated "><!-- //bounceInLeft slow -->
		    <input id="automplete-testsearch" autocomplete="off" onkeypress="return isNumberKeytestsearch(event)" type="text" class="form-control" placeholder="Test/Group Search">
		    <div class="input-group-btn">
		      <button class="btn btn-dark " type="button" style="background-color:black;color:white	" onclick="settblsearchtest()" data-toggle="modal" data-target="#myModalHorizontal">
		        Search <i class="fa fa-search" style="color:white"></i>
		      </button>
		    </div>
	  	</div>
	  
		<!--<b><u><a style="color: "  onclick="settblsearchtest()" data-toggle="modal" data-target="#myModalHorizontal" ><b><u>Test/Group Search</u></b>
			<i class="fa fa-search" aria-hidden="true"></i></a></u></b>
			<input type="text" class="form-control " placeholder="Search"  id="automplete-testsearch" autocomplete="off" onkeypress="return isNumberKeytestsearch(event)">
		-->
	</div>
				
<script>
var crnos= $('[name="patCrNo"]').val();
var urltag="/HISInvestigationG5/new_investigation/onlineRequisitionRaisingprevreqNEW.cnt?patCrNo="+crnos;
//alert("urltag"+urltag);
//document.getElementById("prevreqshww").href.replace("userName", "test1");
document.getElementById("prevreqshww").setAttribute("href",urltag);
</script>
					
		
<div class="col-md-5 pl-3 bookmark-col">			
	<div class="row no-guttersNEW" style="margin-left: 90;">
		
		   <!--  <div class="col-4 col-md-2  py-1 text-nowrap "  data-toggle="collapse" data-target="#bookmark-List" aria-expanded="true" aria-controls="collapseExample">   
			<b><u style="color: black;"> Bookmarks</u>&nbsp;<i class="fa fa-bookmark" ></i></b>						
		    </div> -->
		    
		    <div class=" col-8 col-md-9  pl-md-3  py-1  text-nowrap collapse show overflow-auto bookmark-List" id="bookmark-List" >
		    
		      <%
				  Map<String,Map<String,List<BookMarkVO>>> mpBookMark=(Map<String,Map<String,List<BookMarkVO>>>)session.getAttribute(InvestigationConfig.MAP_BOOK_MARK_XRAYPROCESS);
				  
				  if(mpBookMark!=null)
				  {
					  Iterator itrBookMark=mpBookMark.keySet().iterator();
					  while(itrBookMark.hasNext())
					  {
						  boolean flagg=false;
						  String bkCodeHashName=(String)itrBookMark.next();
						  String bookMarkCode=bkCodeHashName.split("#")[0];
						  String bookMarkName=bkCodeHashName.split("#")[1];
						  
						  Map<String,List<BookMarkVO>> bookmarktest=mpBookMark.get(bkCodeHashName);
						  
						  Iterator itrBookMarktest=bookmarktest.keySet().iterator();
						  String testcode="";
						  String labcode="";
						  String grpbook="";
						  String grptestcode="";
                           Map<String,String> mpgrpbookmark=new HashMap<String,String>();
						  while(itrBookMarktest.hasNext())
						  {
							  String testkey=(String)itrBookMarktest.next();
							  labcode=testkey;
							  List<BookMarkVO> testlist=bookmarktest.get(testkey);
							  
							  if(testlist!=null && testlist.size()>0)
							  {
								  for(int k=0;k<testlist.size();k++)
								  {
									  BookMarkVO vp=testlist.get(k);
									  
									  if(vp!=null)
									  {
										  
										  
										  
										/*   if(vp.getGroupCode()!=null &&  !vp.getGroupCode().equals("-1"))
										  {
											  
											  if(mpgrpbookmark.containsKey(vp.getLabCode()+"#2#"+vp.getGroupCode()))
											  {
												  
											      String newtestcode=mpgrpbookmark.get(vp.getLabCode()+"#2#"+vp.getGroupCode())+"^"+vp.getTestCode();
											      
												  mpgrpbookmark.put(vp.getLabCode()+"#2#"+vp.getGroupCode(), newtestcode) ;
											  }
											  else
											  mpgrpbookmark.put(vp.getLabCode()+"#2#"+vp.getGroupCode(), vp.getTestCode()) ;
										      
									       }
										  else
										  { */
											  if(testcode.equals(""))
											  {
												  
											  if(vp.getGroupCode().equals("-1"))
											testcode+=labcode+"#"+vp.getTestCode();
											  else
												  testcode+=labcode+"!!^2^"+vp.getGroupCode()+"#"+vp.getTestCode(); 
												  
											  }
										          else
										          {
										        	  
										        	  
										        	  if(vp.getGroupCode().equals("-1"))
															testcode+="$$"+labcode+"#"+vp.getTestCode();
															  else
																  testcode+="$$"+labcode+"!!^2^"+vp.getGroupCode()+"#"+vp.getTestCode(); 
										        	  
										        	   
										          
										          }
										 // }
									  }
									  
								  }
							  }
							  
							 /*  for(BookMarkVO vv:testlist)
							  {
								  
							          if(testcode.equals(""))
								  testcode+=labcode+"#"+vv;
							          else
							       testcode+="$$"+labcode+"#"+vv; 
							          
							  }	 */				  
							  
						  }  
						  
					/* 	  if(mpgrpbookmark!=null)
						  {
							  Iterator itrBookMarkgrp=mpgrpbookmark.keySet().iterator();
							  while(itrBookMarkgrp.hasNext())
							  {
								  String labgrpcode=(String)itrBookMarkgrp.next();
							  
								  
								  String grptestcodenew=mpgrpbookmark.get(labgrpcode);
								  
								  if(grptestcode.equals(""))
								   {
								   grptestcode=grptestcodenew+"#"+labgrpcode;
							       }
								  else
								  {
									  grptestcode+="``"+grptestcodenew+"#"+labgrpcode;
								  }
								  
							  }
						  } */
						  
						  String newtestcode="";
						  
						  //if(grptestcode.equals(""))
							  newtestcode=bookMarkCode+"@"+testcode;
						  //else
							//  newtestcode=bookMarkCode+"@"+testcode+"$$!!"+grptestcode;
					
						  //String newtestcode=bookMarkCode+"@"+testcode+"@@"+grptestcode;
						  /* if(!bookMarkCode1.equals("")) {
							  String[] bookmarks=bookMarkCode1.split("#");
							for(int i1=0;i1<bookmarks.length;i1++) {
							    if(bookmarks[i1].equals(bookMarkCode))
							    { flagg=true;  }
							} } */ 
				  %>  
				<%--  <a  style="color:" class="" onclick="addbookmark('<%=newtestcode%>')" id="bmark<%=bookMarkCode%>" style="" data-toggle="tooltip" data-placement="bottom" title="Bookmark">  <span id="bmarkinside<%=bookMarkCode%>" class="badge badge-pill badge-info" style="font-size:14">&nbsp;<i class="fa fa-bookmark" ></i>&nbsp;<%=bookMarkName %>&nbsp;</span></a> --%>
				 
				  <a  href="#" style="color:blue" class="" onclick="addbookmark('<%=newtestcode%>')" id="bmark<%=bookMarkCode%>" style="" data-toggle="tooltip" data-placement="bottom" title="Bookmark">  <span id="bmarkinside<%=bookMarkCode%>" class="" style="font-size:14">&nbsp;<i class="fa fa-bookmark" ></i>&nbsp;<%=bookMarkName %>&nbsp;</span></a>
			<%--   <a  style="color:" class="" onclick="addbookmark('<%=newtestcode%>')" style="">  <span class="badge badge-pill badge-info" style="font-size:14">&nbsp;<%=bookMarkName %>&nbsp;</span></a>
			  <a  style="color:" class="" onclick="addbookmark('<%=newtestcode%>')" style="">  <span class="badge badge-pill badge-info" style="font-size:14">&nbsp;<%=bookMarkName %>&nbsp;</span></a>
			  <a  style="color:" class="" onclick="addbookmark('<%=newtestcode%>')" style="">  <span class="badge badge-pill badge-info" style="font-size:14">&nbsp;<%=bookMarkName %>&nbsp;</span></a>
			  --%>  <%} } %>
			    <!-- <div id="mySidebar" class="sidebar">
				  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
				  <a><u>Bookmarks </u><i class="fa fa-bookmark" aria-hidden="true"></i></a>
				  <a href="#">Reference 5</a>
				  <a href="#">Reference 6</a>
				  <a href="#">Reference 7</a>
				  <a href="#">Reference 8</a>
				</div> -->
		
		 </div>
		  
   	</div>
  </div>
  
</div>
		
</div>





<div class=" container-fluid mt-2 mb-3" id="cont5">
							
	<div class=" row p-2 shadow rounded bg-white no-guttersNEW">
		
			<div  class="col-12 col-md-10 rounded">
				
				<div style=" background: #15658B;"  id="cont5card1" class="p-2  rounded accordion">
				  <div  id="appointmentHead" >
				    <div style="color:white" data-toggle="collapse" data-target="#appointmentBody" aria-expanded="true" aria-controls="appointmentBody">
					  <!--  <b>Lab: <span id="labshw"></span></b>&nbsp; &nbsp; -->
					   <!-- <span  id='labshwcount' data-toggle="popover" title="" data-html="true" data-content=""></span> -->		
				    </div>
				  </div>
				    	
				  <div class="collapse show" id="appointmentBody" aria-labelledby="appointmentHead" data-parent="#cont5card1">
				      <div class="appoinment" id="isdisapp" style="display: none"><a class="Appointment" title="" data-toggle="tooltip"><b><i class="fa fa-calendar-check-o" aria-hidden="true"></i>Appointment</a></b></div>
				     <!--  <div class="date"><a class-=""><span id="app"></span></a></div> -->
				  </div>
				</div>
				
				<div class="" style="max-height: 350px !important; overflow-y:auto;">
					<table id="testtable" class="table  table-xs table-condensed table-hover table-responsive-xs order-list table-striped"  style="width:100%;font-size: small;" >
						<thead class="bg-white  rounded border-bottom border-primary" style=" position: -webkit-sticky; position: sticky; top: 0;">
							<tr>
								<th class="th-sm"width=25%>Test</th>
								<th class="th-sm" width=15%>Laboratory</th>
								<th class="th-sm" width=15%>Sample/Site</th>
								<th class="th-sm" width=15%>Rates</th>
								<th class="th-sm" width=15%>Group</th>
								<th class="th-sm" width=25%>Instruction</th>
								<th class="th-sm" width=5%>&nbsp;</th>

							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				
				<div class="" id="snackbar">Test/Group Added Successfully..</div>
				
			</div>
			
			<div id="cont6" class="col-12 col-md-2 pl-2 rounded">               
				<div  id="cont6card1" class="accordion">
				
			 		<div class="p-2 rounded" id="adviceNotesHead" style="background: #15658B;"> 
			 			<div class="text-light text-nowrap"  align="center" data-toggle="collapse" data-target="#cont6card1div2" aria-expanded="true" aria-controls="cont6card1div2">   
							<b>Advice Notes&nbsp;<i class="fa fa-list-alt" ></i></b>&ensp;						
					    </div>
					</div>
					 
			 		<div id="cont6card1div2" class="collapse show" aria-labelledby="adviceNotesHead" data-parent="#cont6card1">
						
						<div id="pp1" align="center"> <b> Priority :</b> </div>
						<div id="pp2" align="center">
							<select name="prioirty" id="companyvip" class="form-control custom" onchange="setpriority(this)">
								<option value="1">Routine</option>
								<option value="2">Urgent</option>
								<!-- <option value="1">Normal</option>
								<option value="2">High</option>
								<option value="3">Medium</option>
								<option value="4">Low</option> -->
							</select>
					     	
					     	<input type="hidden" name="prioriotytype" value="1">
					     	
						</div>
						
						
						
						<div align="center"> <b> Advised By :</b> </div>
						<div align="center">
							<input name="advisedby" id="automplete-4" type="text" class="form-control ">
							<input type="hidden" name="adivcedbycode" />
						    <input type="hidden" name="adivcedbyname" />
						</div>
			
			
                      
						<div align="center"> <b> Chief Complaints :</b> </div>
						
						<!-- <div class="row-md-2" align="center">
						<button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">  <span class="text">Fever </span><span class="fdl-remove" onclick="$(this).parent().remove();">x</span></button>
						</div> -->
						<div class="row-md-2 " align="" >
<span id="chief234setchiefcompaints">

<!-- <span class="badge badge-pill badge-info" style="font-weight:200;font-size:12">Q fever <span class="fdl-remove" onclick="$(this).parent().remove();"><a>x</a></span> </span>
 -->

</span>
<!-- <span class="badge badge-pill badge-info">Malaria</span>
			
		<span class="badge badge-pill badge-success" style="font-weight:200;font-size:12">Q fever <span class="fdl-remove" onclick="$(this).parent().remove();"><a>x</a></span> </span>
		
		 <span class="badge badge-primary">Primary</span>	 -->
		 		
						</div>
						
						<div class="row-md-2 input-group" align="center"> 
						
						<!-- <input id="automplete-chief"  name="cheifcomplaints" type="text" class="form-control">
						 -->
						<span id="addc">
						
						
								
									
						</span>
    
						 <input id="txt-snomed-ct-search_chief234"  name="chief234" type="text" class="searchText form-control"
						 onkeypress="snomedctsingle('chief234')" 
						 size="25" value="" autocomplete="off" placeholder="Enter 3 characters to search.."
						 >
						 
						 <!-- <input tabindex="1" type="text" name="961011000119123010000101#null#template#127733604" id="txt-snomed-ct-search_961011000119123010000101#null#template#127733604" class="searchText ui-autocomplete-input" 
						 placeholder="Enter 3 characters to search " onfocus="callOnFocus(this)" 
						 onkeypress="snomedctsingle('961011000119123010000101#null#template#127733604')" 
						 size="25" value="" autocomplete="off"> -->
                 
                 <input type="hidden" id="txt-snomed-ct-search_codechief234">
                 
						 </div>
							<input type="hidden" name="chief_complaints_code" />
						    <input type="hidden" name="chief_complaints_name" />
						<div align="center" input-group> <b>Diagnosis :</b> </div>
						
						<div class="row-md-2 " align="" >
<span id="daignosis234setchiefcompaints">

<!-- <span class="badge badge-pill badge-info" style="font-weight:200;font-size:12">Q fever <span class="fdl-remove" onclick="$(this).parent().remove();"><a>x</a></span> </span>
 -->

</span>
<!-- <span class="badge badge-pill badge-info">Malaria</span>
			
		<span class="badge badge-pill badge-success" style="font-weight:200;font-size:12">Q fever <span class="fdl-remove" onclick="$(this).parent().remove();"><a>x</a></span> </span>
		
		 <span class="badge badge-primary">Primary</span>	 -->
		 		
						</div>
						
						
						<div align="center"> 
						
						<!-- <input id="automplete-diagnosis"  type="text" class="form-control"> 
						 -->
						
						 <input id="txt-snomed-ct-search_daignosis234"  name="daignosis234" type="text" class="searchText form-control"
						 onkeypress="snomedctsinglenew('daignosis234')" 
						 size="25" value="" autocomplete="off" placeholder="Enter 3 characters to search.."
						 >
						 
						 <!-- <input tabindex="1" type="text" name="961011000119123010000101#null#template#127733604" id="txt-snomed-ct-search_961011000119123010000101#null#template#127733604" class="searchText ui-autocomplete-input" 
						 placeholder="Enter 3 characters to search " onfocus="callOnFocus(this)" 
						 onkeypress="snomedctsingle('961011000119123010000101#null#template#127733604')" 
						 size="25" value="" autocomplete="off"> -->
                 
                 <input type="hidden" id="txt-snomed-ct-search_codedaignosis234">
                 
						
						</div>
							<input type="hidden" name="diagnosis_code" />
						    <input type="hidden" name="diagnosis_name" />
							<html:hidden name="InvestigationRaisingDtlreqFB" property="cashCrNo" />
						
			
						<div align="center"> <b> Notes :</b> </div>
						<div align="center"> <input  type="text"  name="notes" class="textt form-control"  maxlength="50"> </div>
						
						<div id="pr1" align="center"> <b>Pregnant :</b> </div>
						<div id="pr2" align="center">
							<select id="pregnantt"  class="form-control custom" onchange="setpregnant(this)">
								<option value="1">No</option>
								<option value="2">Yes</option>
							</select>
								<input type="hidden" name="is_pregnant" value="1"/>
							
							<% if(lstPatVO.getPatGenderCode().equalsIgnoreCase("M")){ %>
				     			<script type="text/javascript">
					         		document.getElementById("pregnantt").disabled = true;
					         		document.getElementsByName("is_pregnant")[0].value = "";
					         		
						     	</script>
			     			<%}else{} %>
			     			
			     			
						  
						</div>
						
						
						<div id="nb1" align="center"> <b> New Born :</b> </div>
						<div id="nb2" align="center">
							<select id="companynew" class="form-control custom" name="company1" onchange="setnewborn(this)">
								<option value="1">No</option>
								<option value="2">Yes</option>
							</select>
						</div>
							<input type="hidden" name="is_newborn" value="1"/>
						   
						
					</div>	
						
				</div>
			</div>
						
	</div>
	
	 <div class="row mt-3">
			<div class="col-md-11" style="font-size:1.1rem;">
				<div class="d-flex justify-content-center">
					<button type="button" class="btn btn-success " style="color:white" onclick="save()">
						Save <i class="fa fa-floppy-o" aria-hidden="true"></i>
					</button>
					&ensp;
					<button type="button" class="btn  btn-danger " style="color:white" onclick="cancel()">
						Cancel <i class="fa fa-ban" aria-hidden="true"></i>
					</button>
				</div>
			</div>
	</div>
			
</div>

   
		
	


<!-- <div class="modal fade modal-tall" id="myModalHorizontal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog"  style="width:1250px;background-color: ">
			<div class="modal-content" style="width:900;height:500">
				Modal Header
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
					</button>
					 <span class="modal-title" id="myModalLabel" style="font-size: 17"><b>List of Test</b><div align="right"> <button align="" style="border: 2px solid rgba(108, 89, 179, 0.75);border-radius: 40px;" type="button" class="btn btn-primary" onclick="addall()"><span >ADD</span>
					</button></div> </span>
					  <span class="modal-title" id="" style="font-size: 17"><b>&nbsp;</b></span>
				</div>
        
				Modal Body
				<div class="modal-body"> -->

			
			<div class="modal fade" id="myModalHorizontal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
       <span style="font-size:17"> <b>List of Tests/Groups</b></span>
       <div align="right" style="margin-left:500"><button  type="button" class="btn btn-primary " onclick="addall()"  id="addbtn" style="display:none;font-size:12;border: 2px solid rgba(108, 89, 179, 0.75);border-radius: 40px;">ADD</button></div>  
        <div align="right" >
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        </div>
      </div>
      <div class="modal-body">
     		

  <% Map<String,LabTestVO> _mpp=(Map<String,LabTestVO>)session.getAttribute(InvestigationConfig.MAP_ALL_TEST_DATA_XRAY_PROCESS);
    if(_mpp!=null && _mpp.size()>0) { 
    	
    %>
 

    	
 <!-- 	<div style="overflow-x:auto; background-color: ;margin-top:1px;" class="anyClassscrollabletestshow"> -->
 	<!-- 	<table id="testtablelistoftest" class=" table table-condensed  table-xs  table-hover table-responsive order-listoftst" style="font-size: smaller; width:100%" > -->
			<table id="testtablelistoftest" class="table table-striped table-bordered " style="">
		
			<tfoot>
 		 	<tr>
 		 	<th></th>
 		 	<th></th>
 		 	<th></th>
 		 	<th></th>
 		 	</tr>
 		 	</tfoot>
			<thead >
	      		<tr ><th width=""></th>  <th width="30%">Test</th> <th width="">Lab Name</th><th></th> </tr>
	    	</thead> 
    		<tbody>
			    <%
					boolean flg=true;
			  		Iterator itrAuto=_mpp.keySet().iterator();

    	  			while(itrAuto.hasNext()) {
    		  			String testlab=(String)itrAuto.next();
    					//  String test=testlab.split("#")[0];
    		  			LabTestVO voPatEpisode=_mpp.get(testlab);
			 	%>
			 	  <script>
				       var testingcode="<%=voPatEpisode.getTestCode()%>"+"idd";
				   //    alert(testingcode);
				     // alert("cc"+ document.getElementById(testingcode).length);
				       
				       </script>
      			<tr  id='<%=voPatEpisode.getTestCode()+""+voPatEpisode.getLabCode()%>list' class="trlist">
			    <td width="10%">
			       <input id='<%=voPatEpisode.getTestCode()+voPatEpisode.getLabCode()%>' class="testsearchmodal <%=voPatEpisode.getTestCode()+voPatEpisode.getLabCode()%>" type="checkbox" value='<%=voPatEpisode.getTestCode()+"#"+voPatEpisode.getLabCode()%>' name="testshowforadd" onclick="setdata(this)" />
				     
			    </td>
			    	<td width="30%">
				     
				       
				         <%= voPatEpisode.getTestName()%>
			        </td>
			        <td width="60%">
				         <%= voPatEpisode.getLabName()%>
			        </td>
			        <td>Test Wise</td>
		      	</tr>
			
 				<% flg=false; }%>
 		
 		
 		

 		<% Map<String,LabTestVO> _mppgrp=(Map<String,LabTestVO>)session.getAttribute(InvestigationConfig.MAP_ALL_GROUP_DATA_NEWREQ_PROCESS);
    if(_mppgrp!=null && _mppgrp.size()>0) { 
    
    	
    %>
 	<%Iterator itrAutogrp=_mppgrp.keySet().iterator();
                       int i=0;
    	  			while(itrAutogrp.hasNext()) { 
    	  				i++;
    	  				String testlabgrp=(String)itrAutogrp.next();
    					//  String test=testlab.split("#")[0];
    		  			LabTestVO voPatEpisodegrp=_mppgrp.get(testlabgrp);
    	  				
    		  			if(voPatEpisodegrp.getTestGroupCode().equals("1025"))
    		  			{
    		  				//System.out.println("ddd");
    		  				
    		  			}
    		  			
    	  				if(voPatEpisodegrp.getTestCode()!=null && voPatEpisodegrp.getTestGroupCode()!=null && voPatEpisodegrp.getLabCode()!=null && voPatEpisodegrp.getTestGroupName()!=null )
    	  				{
    	  					String idd=voPatEpisodegrp.getTestCode()+"#"+voPatEpisodegrp.getLabCode()+"#2#"+voPatEpisodegrp.getTestGroupCode();
    	  					String idd2=voPatEpisodegrp.getTestGroupCode()+"#"+voPatEpisodegrp.getLabCode();
    	  					//System.out.println("ddd"+idd+"ss"+idd2);
    	  				%>
    	  			
    	  			<tr  class="trlist" id='<%=voPatEpisodegrp.getTestGroupCode()+voPatEpisodegrp.getLabCode()%>list'>
			    	<td>
			    	<input type="checkbox" id="<%=idd2%>" class="testsearchmodal <%=idd2%>"  value='<%=idd%>' name="testshowforadd" onclick="setdata(this)"/>
			    	</td>
			    	<td>
				        
				         <%= voPatEpisodegrp.getTestGroupName()%>
			        </td>
			        <td>
				         <%= voPatEpisodegrp.getLabName()%>
			        </td>
			        <td>Group wise</td>
		      	</tr>
    	  			
    	  			<%}
    	  				} %>
 		
 		<%} %> 
 		
 		
 		 	</tbody>
 		 
  		</table>
 <!--  </div> -->
 <% } %>
 	
 	
 	</div>

				<!-- Modal Footer -->
				<!-- <div class="modal-footer">
					
					 <button type="button" class="btn btn-primary" onclick="addall()">ADD
					</button> 
				</div>
			</div> -->
			
				
		</div>
	</div>
	
	

	
	
	
	
	<!-- Modal -->
	
	
	</logic:present>


		<%-- <html:hidden name="InvestigationRaisingDtlreqFB" property="hmode" /> --%>
   
      			
      			
      			 <!-- Button to Open the Modal -->
  			<button type="button" id="searchPopupButtonID" style="visibility: hidden;" 
  				class="btn btn-primary" data-toggle="modal" data-target="#searchPopupModalID">
  				
  			</button>

  			<!-- The Modal -->
  			<!-- <div class="modal" id="searchPopupModalID">
    			<div class="modal-dialog modal-lg" style="max-width:90vw; max-height: 65vh;">
      				<div class="modal-content" >
      
        			Modal Header
        			<div class="modal-header" style="height:11vh;">
          				<h3 class="modal-title text-left" >Patient Search</h3>
          					<button type="button" class="close" data-dismiss="modal" style="margin-top:-36px;">&times;</button>
        			</div>
        
       				Modal body
        				<div class="modal-body" style="width:90vw; height: 65vh;">
          					<iframe src="/HISRegistration/registration/transactions/PatientSearch.action" style="width: 50%; height: 100%; border: none;"></iframe>
        				</div>
        
       				</div>
    			</div>
  			</div>   --> 
      

              
  			
		  </fieldset>
      
</html:form>


</body>


</html>
<%}
catch(Exception e)
{e.printStackTrace();
	
	}

%>