<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>

<%@page import="new_investigation.vo.Inv_ictc_VO"%>
<%@page import="new_investigation.transactions.controller.fb.InvestigationRaisingDtlXrayFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>

<%@page import="java.util.*"%>
<his:javascript src="/hisglobal/js/calendar.js" />
<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Xray Raising Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<his:css src="/new_investigation/media/echo/bootstrap.min.css" />

<his:javascript src="/new_investigation/media/bootstrap4/echo/jquery.min.js" />
<his:javascript src="/new_investigation/media/bootstrap4/echo/bootstrap.min.js" />
<his:css src="/new_investigation/media/echo/glymph.css" />
<his:css src="/new_investigation/media/echo/bootstrap-multiselect.css" />

<his:css src="/new_investigation/media/xray/font-awesome.min.css" />


<his:javascript src="/new_investigation/media/bootstrap4//echo/bootstrap-multiselect.js" />


<his:css src="/new_investigation/media/xray/display.css" />


	
	<his:javascript src="/new_investigation/media/xray/jquery.dataTables.min.js" />
	
	<his:javascript src="/new_investigation/media/xray/dataTables.buttons.min.js" />
	
	<his:javascript src="/new_investigation/media/xray/jszip.min.js" />
	<his:javascript src="/new_investigation/media/xray/pdfmake.min.js" />
	<his:javascript src="/new_investigation/media/xray/vfs_fonts.js" />
	<his:javascript src="/new_investigation/media/xray/buttons.html5.min.js" />
	<his:javascript src="/new_investigation/media/xray/buttons.print.min.js" />


<his:css src="/new_investigation/media/xray/sweetalert.css" />

	<his:javascript src="/new_investigation/media/xray/sweetalert.js" />

<his:css src="/new_investigation/media/xray/jquery.dataTables.min.css" />
<his:css src="/new_investigation/media/xray/buttons.dataTables.min.css" />

	
<script type="text/javascript"
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
		
	<his:css src="/new_investigation/media/xray/animate.min.css" />

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
  margin-left: -125px;
  background-color: #333;
  color: #fff;
  text-align: center;
  border-radius: 2px;
  padding: 16px;
  position: fixed;
  z-index: 1;
  left: 50%;
  bottom: 30px;
  font-size: 17px;
}

#snackbar.show {
  visibility: visible;
  -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
  animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

@-webkit-keyframes fadein {
  from {bottom: 0; opacity: 0;} 
  to {bottom: 30px; opacity: 1;}
}

@keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 30px; opacity: 1;}
}

@-webkit-keyframes fadeout {
  from {bottom: 30px; opacity: 1;} 
  to {bottom: 0; opacity: 0;}
}

@keyframes fadeout {
  from {bottom: 30px; opacity: 1;}
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
 
 .ui-autocomplete{width:180px!important;margin-left:16}
 
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
	height: 250px;
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
</style>



<script type="text/javascript">


function seteventlis()
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

$(document).ready(function() {
  
	
	$('.textt').keypress(function (e) {
	    var regex = new RegExp("^[A-Za-z0-9? ,_-]+$");
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (regex.test(str)) {
	        return true;
	    }

	    e.preventDefault();
	    return false;
	});
	
	
});





function setnewborn(obj)
{

	//alert(obj.value);
	document.getElementsByName("isnewborn")[0].value=obj.value;
}


function setpreg(obj)
{

	//alert(obj.value);
	document.getElementsByName("ispregnant")[0].value=obj.value;
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


function deleteRowPrvReqDtl(labCode,testCode,reqNo,testGroupCode1,testname)

{
	// alert("testGroupCode1"+testGroupCode1);
	var msgg="Are You Sure to Delete Test "+testname+" ?";
	var sumsgg="Test "+testname+" has been Successfully deleted.";
	
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


 
function settblsearchtest()
{
//alert("kk");


for(var g=0;g<document.getElementsByName("testshowforadd").length;g++)
{ 

	document.getElementsByName('testshowforadd')[g].checked=false;
	document.getElementsByName('testshowforadd')[g].disabled =false;

}

if(document.getElementsByName("selectedtestdataatrray").length>0)
{
for(var k=0;k<document.getElementsByName('testshowforadd').length;k++)
	{

	var tocheck=document.getElementsByName('testshowforadd')[k].value;




		
	for(var g=0;g<document.getElementsByName("selectedtestdataatrray").length;g++)
	{ 

		var val=document.getElementsByName("selectedtestdataatrray")[g].value;
		
            if(val.includes(tocheck.split("#")[0]))
                {
            	document.getElementsByName('testshowforadd')[k].checked=true;
            	document.getElementsByName('testshowforadd')[k].disabled =true;
                }
            
		
	}
			
	


	
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

$(document).ready(function() {
    $(".iframee").fancybox({
        type: 'iframe',
        'autoSize' : false,
        "width":1200,"height":1500,
        afterShow : function() {
            $('.fancybox-skin').append('<a title="Close" class="fancybox-item fancybox-close" href="javascript:jQuery.fancybox.close();"><i  style=" color: #82C91E;"><img style="width:30" src="/HISInvestigationG5/hisglobal/css/close.png"> </i></a>');

        },
    });
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

	for(var k=0;k<document.getElementsByName('testshowforadd').length;k++)
	{

		if(document.getElementsByName('testshowforadd')[k].checked==true && document.getElementsByName('testshowforadd')[k].disabled==false)
				{
			
	var tocheck=document.getElementsByName('testshowforadd')[k].value;

	var testdatafinal=  ajaxhitforfetchingtestwisedata(tocheck.split("#")[0]+"^"+tocheck.split("#")[1]);
	
	maktestinrowtable(testdatafinal);


	    
				}
		
	}

	
	$('#myModalHorizontal').modal('hide');
	

	}

function shwhide()
{

	var shwhide1="0";
	var crnoshw="";
	var ff=<%=(String)session.getAttribute("load")%>;

	<%if(session.getAttribute("patcrno")!=null)
		{%>
		
		var patcrno='<%=(String)session.getAttribute("patcrno")%>';
		document.getElementsByName("patCrNo")[0].value=patcrno;
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
				 document.getElementsByName('patCrNo')[0].value="";
        	
        	 
             }
			 else
				 {
	        //	 document.getElementById("myspan").innerHTML = ""; //INSECURE!!

				 }
             
		// alert("ff"+ff);
		 
		 <% String loadfrmdesk="0";
		   if( session.getAttribute("loadfromdesk")!=null )
			   {
			   
			   
			   loadfrmdesk=(String)session.getAttribute("loadfromdesk");
			 
			   }
		   %>
                  var loaddeksk=<%=loadfrmdesk%> ;
               //   alert("loaddeksk"+loaddeksk);
       if(loaddeksk=="1")
    	   {
    	   
    	   document.getElementById("cont1").style.display="none";

	        document.getElementById("cont3").style.display="none";
			document.getElementById("cont4").style.display="none";
			document.getElementById("cont5").style.display="none";
			document.getElementById("cont6").style.display="none";
			
			document.getElementById("deskcase").style.display="";
			
			
    	   }
       else
    	   {
    	   
    	  
	if(ff=="1")
	{
	 document.getElementById("cont1").style.display="none";

			        document.getElementById("cont3").style.display="";
					document.getElementById("cont4").style.display="";
					document.getElementById("cont5").style.display="";
					document.getElementById("cont6").style.display="";
					document.getElementById("deskcase").style.display="none";
					
	}
	else
		{
		 document.getElementById("cont1").style.display="";

	        document.getElementById("cont3").style.display="none";
			document.getElementById("cont4").style.display="none";
			document.getElementById("cont5").style.display="none";
			document.getElementById("cont6").style.display="none";
			document.getElementById("deskcase").style.display="none";
		}	
}
       
       
       
       <% String deskhide="0";
	   if( session.getAttribute("loadfromdesk_new")!=null )
		   {
		   
		   
		   deskhide=(String)session.getAttribute("loadfromdesk_new");
		 
		   }
	   %>
              var deskhide=<%=deskhide%> ;
              
              if(deskhide=="1")
            	  {
            	  document.getElementById("cancellbtn").style.display="none";
            		document.getElementById("newb1").style.display="none";
            		document.getElementById("newb2").style.display="none";
            		document.getElementById("chief1").style.display="none";
            		document.getElementById("chief2").style.display="none";
            		document.getElementById("preg1").style.display="none";
            		document.getElementById("preg2").style.display="none";
            		document.getElementById("diag1").style.display="none";
            		document.getElementById("diag2").style.display="none";
            		//alert(document.getElementsByName('adivcedbyname')[0].value);
            		//document.getElementsByName('automplete-4')[0].value=
            		//	document.getElementById("automplete423").innerHTML = document.getElementsByName('adivcedbyname')[0].value ;
            			document.getElementById('automplete423').value = document.getElementsByName('adivcedbyname')[0].value  ;
            			document.getElementsByName('advisedby').value = document.getElementsByName('adivcedbyname')[0].value  ;
            			//document.getElementById('automplete423')
            			document.getElementById("automplete423").disabled = true;
            	  }

	}
function setpriority(obj)
{

	//alert(obj.value);
	document.getElementsByName("prioriotytype")[0].value=obj.value;
}



function save()
{

          // alert(document.getElementsByName("advisedby")[0].value);
	         // return null;
	
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
	}
	

	for(var g=0;g<document.getElementsByName("radioEpisode").length;g++)
	{
	       if(document.getElementsByName("radioEpisode")[g].checked==true)
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

function addbookmark(data)
{
	//alert("addbookmark"+data);
	var labcode= data.split("@")[1];
	
    var testdata=data.split("@")[2];
    var maintestdata=testdata.split("#");
	for(var f=0;f<maintestdata.length;f++)
		{
		var testcode=maintestdata[f];
	//	alert("testciode"+testcode);
        var testdatafinal=  ajaxhitforfetchingtestwisedata(testcode+"^"+labcode);
		
		maktestinrowtable(testdatafinal);
        
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
        //        alert("testwisesearch"+ui.item.value);
               if(ui.item.value!="")
               {

                   
              var testdata=  ajaxhitforfetchingtestwisedata(ui.item.value);
              maktestinrowtable(testdata);


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
         //   alert("maktestinrowtable"+testdata);
           //    var billingflag=checkbilling();

             //  alert("maktestinrowtable"+billingflag);
               
            var finaldata=testdata.split("@");
           var combo="";
             if(finaldata.length==2)
                 {
            	 combo=finaldata[1];
                 }
             var datattosave=finaldata[0];

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

       
          document.getElementById("labshw").innerHTML = tests[1];
          
          selectedlab.push(tests[0]);
 	        var newRow = $('<tr data-toggle="collapse" data-target="#text-1" class="accordion-toggle collapsed">');
	        var cols = "";

            if(tests[30]!="")
            {
				var fncn="showprevreqmodal("+tests[3]+")";
				var crnos= $('[name="patCrNo"]').val();
				
				 cols += "<td>"+tests[3]+"<br/><a href='/HISInvestigationG5/new_investigation/onlineRequisitionRaisingprevreq.cnt?patCrNo="+crnos+"&searchTestName="+tests[3]+"' class='iframee'>Last Requisition Date: "+tests[30]+"</td>";
            }
            else
                {
				 cols += "<td>"+tests[3]+"</td>";

                }

            
			    	cols += "<td><div> <select  id='"+tests[2]+"#"+tests[0]+"box' onchange='callsite(this)' class='form-control custom'> <option value='1'>NA</option> <option value='3'>Left</option> <option value='4'>Right</option> <option value='2'>Both</option> </select> </div></td>";
	    	cols += "<td>"+combo+"</td>";
	        cols += '<td><input  class="textt form-control" type="text" name="testinstructions" maxlength="40" ></td>';
	        cols += "<td><a class='delete' title='Delete' data-toggle='tooltip' ><i class='fa fa-trash' aria-hidden='true'></i></a></td><input id='"+tests[2]+"#"+tests[0]+"' type='hidden' name='selectedtestdataatrray' value='"+datattosave+"'/><input id='"+tests[2]+"charge' type='hidden' name='testcharge' value='"+datattosave+"'/><input id='"+tests[2]+"#"+tests[0]+"view' type='hidden' name='selectedtestviews' value=''/><input id='"+tests[2]+"#"+tests[0]+"site' type='hidden' name='selectedsite' value='1'/>";
           
	         
	       
	        newRow.append(cols);
	        $("table.order-list").append(newRow);
	
	        seteventlis();
	        
	        var x = document.getElementById("snackbar");
            x.className = "show";
            setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);

	        
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
				return false;

				
           }
       
		
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



function settestcount()
{

	    var counttest=0;
	    var teststring="";
	    if(document.getElementsByName("selectedtestdataatrray")!=undefined)
	    {    
     	 for(var f=0;f<document.getElementsByName("selectedtestdataatrray").length;f++)
	    	{
   var testcountdata=document.getElementsByName("selectedtestdataatrray")[f].value;
   testcountdata=testcountdata.split("#");
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
          document.getElementById("labshwcount").innerHTML = "<b>\u00A0 \u00A0Test Count: "+counttest+"\u00A0 \u00A0</b>";
		   $('#labshwcount').attr('data-content', "<span style='color:blue'>Test:"+teststring+"</span>");
	          
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

	//var noofviewslength=document.getElementsByName("selectedtestviews").split("@");
    var counternoofviews=0;
	for(var f=0;f<document.getElementsByName("selectedtestviews").length;f++)
	{

	      var viewsforcharge=document.getElementsByName("selectedtestviews")[f].value;
		  var nofviews=viewsforcharge.split("@");
		  var testnotaddcharge=nofviews[0].split("#")[0];
		  if(testfornotaddviews.includes(testnotaddcharge))
		   {

		   }
		  else
			  {
			  counternoofviews+=nofviews.length-1;

			  }

		  


		  var flg="0";
		var  newwcharge="0.0";
		var viewscharge="0";
	       for(var k=0;k<nofviews.length-1;k++)
		   {
		       var newviews=nofviews[k];
			   var newtestview=newviews.split("#")[0];
           

            
			   if(testfornotaddviews.includes(newtestview))
				   {
				   
                        if(flg=="0")
                          {
                        	var newtestviewcharge=newtestview+"charge";
         				   var testviewcharge=document.getElementById(newtestviewcharge).value;
         				   testviewcharge=testviewcharge.split("#")[29];
         					testviewcharge=testviewcharge.split("^")[0];
         					
         				   finalcharge=(parseFloat(finalcharge)+parseFloat(testviewcharge)).toFixed(2);
         				  newwcharge=(parseFloat(newwcharge)+parseFloat(testviewcharge)).toFixed(2);
         
       				   mymapvalue.set(newtestview, newwcharge);
       				   viewsmymapvalue.set(newtestview, "inc. Film");
       				   
         	                  flg="1";
                            }
				   }
			   else
				   {
				  // alert("testcode");

				   if(mymapvalue.get(newtestview)==undefined)
                     {   
					   mymapvalue.set(newtestview, '0.00');
					   newwcharge="0.0";
       				   viewsmymapvalue.set(newtestview, "0	");
       				     viewscharge="0";
	                       }
				   else
					   {    

					   }
				   
                var maptestcode=mymapvalue.get(newtestview);
				   
				   var newtestviewcharge=newtestview+"charge";
				   var testviewcharge=document.getElementById(newtestviewcharge).value;
				   testviewcharge=testviewcharge.split("#")[29];
					testviewcharge=testviewcharge.split("^")[0];
				   finalcharge=(parseFloat(finalcharge)+parseFloat(1)*parseFloat(testviewcharge)).toFixed(2);
				   newwcharge=(parseFloat(newwcharge)+parseFloat(1)*parseFloat(testviewcharge)).toFixed(2);
				   viewscharge++;
				   mymapvalue.set(newtestview, newwcharge);
   				   viewsmymapvalue.set(newtestview, viewscharge);

				   }


			   
			  

			   
			   
		   }
		  
	    //   alert("get key"+mymapvalue.get("12804")); // 3
	    //   alert("get key"+mymapvalue.get("12805")); // 3
	    //   alert("get key skeletal"+mymapvalue.get("12809")); // 3
			  
	}

	 for(var f=0;f<document.getElementsByName("selectedtestviews").length;f++)
	{

		
		      var viewsforcharge=document.getElementsByName("selectedtestviews")[f].value;

		      
              var nofviews=viewsforcharge.split("@");
			  var testnotaddcharge=nofviews[0].split("#")[0];
         var newcharged=mymapvalue.get(testnotaddcharge);
         
         newmymapvalue.set(testnotaddcharge,newcharged);

         if(viewsforcharge=="" && document.getElementsByName("selectedtestviews").length==1)
             {
        	 newmymapvalue=new Map();
        	 viewsmymapvalue=new Map();
        	 
             }
         
             
	}
	
	
   // alert("get key skeletal final map"+newmymapvalue.get("12804")); // 3
   // alert("get key skeletal final view"+viewsmymapvalue.get("12804")); // 3

	
	if(counternoofviews!=0)
		{
		
		   var newaddquot=counternoofviews/4;
		 					var newaddremainder=counternoofviews%4;
		 					if(newaddremainder!=0)
		 					{
		 					newaddquot=parseInt(newaddquot)+1;
		 					}
		 					finalcharge=(parseFloat(finalcharge))+(parseFloat(100))*(parseFloat(newaddquot));
		 					var charged=(parseFloat(100))*(parseFloat(newaddquot));
		 			   //    alert("fims used:"+newaddquot+" charged:"+charged); // 3
		 			      newmymapvalue.set("film",charged);
	       				   viewsmymapvalue.set("film", newaddquot);
			 			      
		}
	else
		{}
	
	document.getElementById("estimaterate").innerHTML=finalcharge;
				      //  $('#estimatebtn').attr('data-content', finalcharge);

				      setpopoversinchargebtn();

}

function setpopoversinchargebtn()
{

     var testsize=newmymapvalue.size;
      var stringg="<table class='table table-condensed table-responsive' >";

 	for(var f=0;f<document.getElementsByName("selectedtestdataatrray").length;f++)
	{

               var dataa=document.getElementsByName("selectedtestdataatrray")[f].value;
               var newdataa=document.getElementsByName("selectedtestdataatrray")[f].value;
               dataa=dataa.split("#")[2];
               var chargee=newmymapvalue.get(dataa);
               var viewss=viewsmymapvalue.get(dataa);

               if(viewss=="0")
               {
            	   viewss="";
               }
               else{

                   if(viewss!="inc. Film")
            	   viewss=viewss+" view";
               }
               

            	


               
               stringg+="<tr><td>"+newdataa.split("#")[3]+"<br/>("+ viewss +") </td><td>"+ Math.floor(chargee)+" INR"+"</td></tr>";

               
	}

    var chargee=newmymapvalue.get("film");
    var viewss=viewsmymapvalue.get("film");

    if(chargee!=undefined && document.getElementsByName("selectedtestdataatrray").length>0)
        {
    stringg+="<tr><td>Film"+"<br/>(Qty. "+ viewss +" ) </td><td>"+ Math.floor(chargee)+" INR"+"</td></tr>";

        }
    stringg+="</table>";
  //  alert("stringg"+stringg);
 	 $('#estimatebtn').attr('data-content', stringg);
	
     
	


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
	var objXHR = {url: "/HISInvestigationG5/new_investigation/onlineRequisitionRaisingXray.cnt?hmode="+_mode+"&patCrNo="+crnootocheck, sync:true, postData: "", handleAs: "text",
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
	//alert("ajaxhit");

	testlabcode = testlabcode.replace(/#/g, '^');
	//alert("new"+selectedText+"#"+reqdno);
	var cr=$('[name="patCrNo"]').val();
	//alert(tmpSampleCode+"  "+tmpLabCode+"     "+tmpTestCode+"    "+tmpLabTestCodeArray);
	var remarks = "";
	var flg = false;
	var labTestCodeArray = "";
	var _mode = "AJX_GETTESTWISEDATA";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/onlineRequisitionRaisingXray.cnt?hmode="+_mode+"&testlabcode="+testlabcode+"&patCrNo="+cr, sync:true, postData: "", handleAs: "text",
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
	var objXHR = {url: "/HISInvestigationG5/new_investigation/onlineRequisitionRaisingXray.cnt?hmode="+_mode+"&patCrNo="+patcr, sync:true, postData: "", handleAs: "text",
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

function isNumberKey(evt)
{
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
	{
			return false;
	}
	else if(evt.keyCode==13)
		{

		 showreq();

		
		}
	else
		{
	return true;
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
            	 document.getElementsByName('patCrNo')[0].value="";
            	 
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
		
		 $('#testtablelistoftest').dataTable( {
    	    "destroy": true,
    	    "pageLength":2,
    	
    	    "lengthMenu": [2,4,6,8, 12, 16, 20,50, 100],
    	    
    	    
    	  } );
 
	  	  
	});

	$(document).ready(function() {
		$('.multiselectt').multiselect({
			enableFiltering : true

		});
	});

	$(document).ready(function() {

		$(document).on("click", ".delete", function() {
			$(this).parent().parent().remove();
			settestcount();
			settestbill();
		});
	});

	$(document).ready(function() {
		$('[data-toggle="popover"]').popover({
			placement : 'bottom',
			trigger : 'hover'
		});
	});

	$(document).ready(function() {
		$('[data-toggle="popoverselectedtest"]').popover({
			placement : 'left',
			trigger : 'hover'
		});
	});
</script>


</head>

<body onload="shwhide()" class="img responsive" style="font-family: 'Roboto', sans-serif;background-image:url(/HISInvestigationG5/hisglobal/images/bg9.jpg);">
	<html:form action="/onlineRequisitionRaisingXray">

<input type="hidden" name="hmode" />
   <html:hidden name="InvestigationRaisingDtlXrayFB" property="patAdmNo" />
		<html:hidden name="InvestigationRaisingDtlXrayFB" property="departmentUnitCode" />
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
   
    
    </div>
    <div id="deskcase">
    <his:status />
    </div>

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
	 
	 <div class="container-fluid col-md-12" style="font-size:1.4rem; display:list-item ;margin-top:0px;margin-bottom:0px;margin-left:0px;margin-right:0px;" id="cont3">
	
<div class="divtable accordion-xs " align="center" style="background-color:#337AB7 " >
 
  <div class="tr" align="center">
    <div class="td patientname accordion-xs-toggle" align="center" style="color:white">
     
    <b><i class="fa fa-user" style="color:white" aria-hidden="true"></i> Patient Name: </b><a style="color:white"><b><%=lstPatVO.getPatFirstName() %></b></a>
 </div>
 <div class="td crno" align="center" style="color:white;">
    <b>CR No: </b><a style="color:white"><b><%=lstPatVO.getPatCRNo() %></b></a>
          <input type="hidden"  value="<%=lstPatVO.getPatCategoryCode()%>" name="patCatCode"/>
    
    </div>
    
    <div class="accordion-xs-collapse">
      <div class="inner">
     
        <div class="td status" style="color:white"><b>Age/Gender:</b><a style="color:white"><b><%=lstPatVO.getPatAge()+"/"+lstPatVO.getPatGenderCode() %></b></a></div>
        <div class="td category" style="color:white"><b>Category: </b><a style="color:white"><b><%=lstPatVO.getPatCategory() %></b></a></div>
        <div class="td fathername" style="color:white"><b>Father's Name: </b><a style="color:white"><b><%=lstPatVO.getPatGuardianName() %></b></a></div>
        <div class="td mobileno" style="color:white"><b>Mobile No: </b><a style="color:white"><b><%=lstPatVO.getPatMobileNo() %></b></a></div>
        <div class="td plus" data-toggle="collapse" href="#multiCollapseExample1"
					aria-expanded="false" aria-controls="multiCollapseExample1" align="center"><i
					class="fa fa-plus-square" aria-hidden="true" data-toggle="modal" style="color:white"
					data-target="#myModal2" ></i></div>
        
      </div>
      
      
      <div class="modal right fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
		<div class="modal-dialog" role="document">
		
		
			<div class="modal-content">

			

				<div class="modal-body">
				
				<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
					</button>
               
					 
					  
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
 	
 	    					<h4 >Episode Details</h4>
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
    		
 	
    %>
    
     
      <tr>
   <td>
   
   <% if(flg==true){ %>
   <input type="radio" name="radioEpisode" checked value="<%=voPatEpisode.getPatepisodecode()+'#'+voPatEpisode.getPatvisittypecode()%>"/>
   <%}else{ %>
   <input type="radio" name="radioEpisode" value="<%=voPatEpisode.getPatepisodecode()+'#'+voPatEpisode.getPatvisittypecode()%>"/>
   <%} %>
   <input type="hidden" name="selectedepisodeee" value="<%=voPatEpisode.getPatepisodecode()%>"  />
   </td>
        <td><%=voPatEpisode.getPatvisitdate() %></td>
        <td><%=voPatEpisode.getDepartment()+" / "+voPatEpisode.getPatdeptunit() %></td>
        <td><%=voPatEpisode.getDiagnosis() %></td>
      </tr>
			
  
  <%    flg=false;
 		}%>
 		
 		 </tbody>
  </table>
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
        
        
      </tr>
			  <thead>
      <tr>
      
      
        <th>Bed Name</th>
        <th>Consultant Name</th>
        <th>Diagnosis</th>
             
      </tr>
    </thead>
    
    
      <tr>
   
        <td><%=voPatEpisode.getBedname() %></td>
        <td><%=voPatEpisode.getConsultantName()%></td>
        <td><%=voPatEpisode.getDiagnosis() %></td>
        
        
      </tr>
      
  
  <%    flg=false;
 		}%>
 		
 		 </tbody>
  </table>
 		<%
 	}
    
  %>
  
  	</div>

			</div><!-- modal-content -->
		</div><!-- modal-dialog -->
	</div><!-- modal -->
	
    </div>
  </div>
  
</div>
</div>

        <!-- -------------end patient tile------------- -->

<!--    -----------------------start billing bookmark tile-------------------       -->

			<div id="cont4" class="container-fluid col-md-12 container-reponsive"style="font-family: 'Roboto', sans-serif;margin-bottom:1px;	;background-color:lightcyan" >
				
		
		
			
		<div class="col-md-3" align="center" style="margin-top:2.5px">

		
<button id="estimatebtn" style="background-color:green;color:white;margin-top:0px" type="button" class="btn btn-sm" data-toggle="popover"
					title="" data-html="true"
					data-content="">
					<b> Estimate:</b> <i class="fa fa-inr" aria-hidden="true"></i>	 <span id='estimaterate'>0.00</span>
				</button>
				
				<button style="background-color:34495E;margin-top:0px;color:white" type="button" class="btn btn-hover1 btn-sm" ><a style="color:white" id="prevreqshww"  class="iframee"><b>Previous Requisition</b></a>	
				
				</div>	    
		
	<div class="col-md-4" align="center" style="margin-top:0.5px;">
				
				
				<div class="input-group animated bounceInLeft slow">
    <input id="automplete-testsearch" autocomplete="off" onkeypress="return isNumberKeytestsearch(event)" type="text" class="form-control" placeholder="Test/Group Search">
    <div class="input-group-btn">
      <button class="btn btn-dark " type="button" style="background-color:black;color:white	" onclick="settblsearchtest()" data-toggle="modal"
					data-target="#myModalHorizontal">
        Search <i class="fa fa-search" style="color:white"></i>
      </button>
    </div>
  </div>
  
		<!-- 		<b><u>	<a style="color: "  onclick="settblsearchtest()" data-toggle="modal"
					data-target="#myModalHorizontal" ><b><u>Test/Group Search</u></b> <i class="fa fa-search"
						aria-hidden="true"></i></a></u></b>
						<input type="text" class="form-control " placeholder="Search"  id="automplete-testsearch" autocomplete="off" onkeypress="return isNumberKeytestsearch(event)">
		 -->
			</div>
				
				<script>
					   var crnos= $('[name="patCrNo"]').val();

					var urltag="/HISInvestigationG5/new_investigation/onlineRequisitionRaisingprevreq.cnt?patCrNo="+crnos;
					//alert("urltag"+urltag);
					//document.getElementById("prevreqshww").href.replace("userName", "test1");
				    document.getElementById("prevreqshww").setAttribute("href",urltag);
					</script>
					
			
	
			<div class="col-md-5" align="center" style="margin-top:5px">			
			<div class="divtable accordion-xs" style="background-color:#337AB7;margin-bottom:5px">
   <div class="tr" align="center" >
    <div class="td bookmarks accordion-xs-toggle" style="color:white;display:list-item;font-size:1.5rem">   
					<!-- 		<b><u >Bookmarks </u><i class="fa fa-bookmark" aria-hidden="true"></i></b> -->						
    </div>
    <div class="accordion-xs-collapse" style="color:white;">
      <div class="inner">
      
      <%
  
  Map<String,Map<String,List<String>>> mpBookMark=(Map<String,Map<String,List<String>>>)session.getAttribute(InvestigationConfig.MAP_BOOK_MARK_XRAYPROCESS);
  
  if(mpBookMark!=null)
  {

	  Iterator itrBookMark=mpBookMark.keySet().iterator();
	  while(itrBookMark.hasNext())
	  {
		  boolean flagg=false;
		  String bkCodeHashName=(String)itrBookMark.next();
		  String bookMarkCode=bkCodeHashName.split("#")[0];
		  String bookMarkName=bkCodeHashName.split("#")[1];
		  
		  Map<String,List<String>> bookmarktest=mpBookMark.get(bkCodeHashName);
		  
		  Iterator itrBookMarktest=bookmarktest.keySet().iterator();
		  String testcode="";
		  String labcode="";
		  while(itrBookMarktest.hasNext())
		  {
			  String testkey=(String)itrBookMarktest.next();
			  labcode=testkey;
			  List<String> testlist=bookmarktest.get(testkey);
			  for(String vv:testlist)
			  {
				  testcode+=vv+"#";
			  }
			  
			  
		  }
		  
		  
		  String newtestcode=bookMarkCode+"@"+labcode+"@"+testcode;
		  /* if(!bookMarkCode1.equals(""))
		  {

			  String[] bookmarks=bookMarkCode1.split("#");


			for(int i1=0;i1<bookmarks.length;i1++)
			{
			    if(bookmarks[i1].equals(bookMarkCode))
			    {
			    	flagg=true;
			    }
			}
		  } */
		  
  
  
  %>
     
   <%--   <div class="th "> <a href="#top" onclick="addbookmark('<%=newtestcode%>')" style="color:white;"><%=bookMarkName %></a></div> --%>
    
  
    
  <%}
  } %>
  
    <div id="mySidebar" class="sidebar">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
  <a><u>Bookmarks </u><i class="fa fa-bookmark" aria-hidden="true"></i></a>
  <a href="#">Reference 5</a>
  <a href="#">Reference 6</a>
  <a href="#">Reference 7</a>
  <a href="#">Reference 8</a>
</div>

  <!-- <div class="th more" id="main" style="color:white;"><a style="color:white;" data-toggle="collapse" href="#detailsRow_131" aria-expanded="false" aria-controls="detailsRow_131"><i onclick="openNav()" class=" fa fa-plus-square openbtn" style="color:white">
   -->
  
        </i></a></div>
        

        
      </div>
    </div>
  </div>
</div>
</div>

	
  

		</div>


<div id="cont5" class="col-md-10 container-responsive"  style="font-family: 'Roboto', sans-serif;overflow-x:auto;margin-top:0px;margin-bottom:0px;margin-left:0px;margin-right:0px;font-size:smaller;">
	
<!-- 	<div class=" alert row-responsive" style="font-family: 'Sanchez', serif;"> -->
	
	<div class="divtable accordion-xs" style="background-color:#337AB7;font-size:1.5rem">
  
  <div class="tr" align="center">
    <div class="td test accordion-xs-toggle" style="color:white">
    <strong><b>Lab: <span id="labshw"></span></b></strong>
   &nbsp; &nbsp;
   <span  id='labshwcount' data-toggle="popover"
					title="" data-html="true"
					data-content=""></span>
					
    </div>
    	
  
    
    
    <div class="accordion-xs-collapse" >
      <div class="inner" >
        <div class="th appoinment" id="isdisapp" style="display: none"><a class="Appointment" title="" data-toggle="tooltip"><b><i class="fa fa-calendar-check-o" aria-hidden="true"></i>Appointment</a></b></div>
        <div class="th date"><a class-=""><span id="app"></span></a></div>

        
      </div>
    </div>
  </div>
  
</div>


<!-- <div class="col-md-12 " style="margin-top:5px">
				 -->


				<div
					style="overflow-x: auto; margin-bottom: 0" class="anyClassscrollable"
					>
					
					<table id="testtable" class="table  table-xs table-condensed table-collapse-xs table-hover table-responsive-xs  order-list"  style="width:100%;font-size: small;">
						<thead>
							<tr>
								<th class="th-sm"width=25%>Test</th>
								<th class="th-sm" width=8%>Site</th>
								<th class="th-sm" width=35%>Views</th>
								<th class="th-sm" width=30%>Instruction</th>
								<th class="th-sm" width=2%>&nbsp;</th>

							</tr>
						</thead>
						<tbody>
							

						


						</tbody>
					</table>

				<!-- </div>
				</div> -->
<div class="col-md-2" id="snackbar">Test Added Successfully..</div>


	
	
	</div>
	<div class="container-fluid col-sm-12" style="font-size:1.5rem;margin-top:0.2px;" align="center">
	<div class="row" align="center" style="font-family: 'Roboto', serif;">
				<button type="button" class="btn btn-success btn-sm  "
					style="color:white" onclick="save()">
					Save <i class="fa fa-floppy-o" aria-hidden="true"></i>
				</button>
				<button id="cancellbtn"  type="button" class="btn  btn-danger btn-sm "
					style="color:white" onclick="cancel()">
					Cancel <i class="fa fa-ban" aria-hidden="true"></i>
				</button>
			</div>
			
		</div>
		</div>
		<!-- 	
		 -->
		
		
		
<div id="cont6" class="col-md-2 container-responsive" style="font-family: 'Roboto', sans-serif;overflow-x: auto;margin-bottom:0px;margin-top:0px;margin-left:0px;margin-right:0px;font-size:1.3rem">
                
                <!-- <div class="row alert row-responsive" style="font-family: 'Sanchez', serif;margin-top:0px;"> -->
                
                <div class="divtable accordion-xs" style="background-color:#337AB7">
  
  <div class="tr" align="center">
    <div class="th advice accordion-xs" style="color:white;">
   <b><u>Advice Notes</u> <i class="fa fa-list-alt" aria-hidden="true"></i></b>
    </div>
  </div>
  
</div>

<div class="row-md-2" align="center">
				<b> Priority :</b>
			</div>
			<div class="row-md-2" align="center">
				<select name="prioirty" style="" id="company" class="form-control custom" onchange="setpriority(this)">
					<option value="1">Normal</option>
					<option value="2">Urgent</option>
					<!-- <option value="3">Medium</option>
					<option value="4">Low</option> -->


				</select>
		
		     <input type="hidden" name="prioriotytype" value="1">
		
			</div>
			<div class="row-md-2" align="center" id="preg1">
				<b>Pregnant :</b>
			</div>
			<div class="row-md-2" align="center" id="preg2">
				<select style="" id="pregnantt"  class="form-control custom" onchange="setpreg(this)">
					<option value="0">No</option>
					<option value="1">Yes</option>

				</select>
			<input type="hidden" name="ispregnant" value="0">
				 <%
     if(lstPatVO.getPatGenderCode().equalsIgnoreCase("M")){
     %>
     <script type="text/javascript">
         	
         	  document.getElementById("pregnantt").disabled = true;
          	 
     </script>
     <%}else{} %>
     
			</div>
			
			<div class="row-md-2" align="center">
				<b> Advised By :</b>
			</div>
			<div class="row-md-2" align="center">
				<input class="textt form-control" name="advisedby" id="automplete423" type="text" maxlength="40">
		<!-- 		<input type="hidden" name="adivcedbycode" />
			    <input type="hidden" name="adivcedbyname" /> -->
				<html:hidden name="InvestigationRaisingDtlXrayFB" property="adivcedbycode" /> 
				<html:hidden name="InvestigationRaisingDtlXrayFB" property="adivcedbyname" /> 
			</div>

			<div class="row-md-2" align="center" id="chief1">
				<b> Chief Complaints :</b>
			</div>
			<div class="row-md-2" align="center" id="chief2">
				<input name="cheifcomplaints" type="text" class="textt form-control" maxlength="40" >
			</div>
			<div class="row-md-2" align="center" id="diag1">
				<b>Diagnosis :</b>
			</div>
			<div class="row-md-2" align="center" id="diag2">
				<input type="text" class="textt form-control" name="diagnosis" maxlength="40" >
			</div>
			
			
			<div class="row-md-2" align="center" id="newb1">
				<b> New Born :</b>
			</div>
			<div class="row-md-2" align="center" id="newb2">
				<select style="" id="company" class="form-control custom" onchange="setnewborn(this)">
					<option value="0">No</option>
					<option value="1">Yes</option>

				</select>
<input type="hidden" name="isnewborn" value="0">
			</div>

<div class="row-md-2" align="center">
				<b> Lab Instructions :</b>
			</div>
			<div class="row-md-2" align="center">
				<input type="text" class="textt form-control" name="labinstructions" maxlength="40" >
			</div>
			

</div>




    
   
		
	



	

	<div class="modal fade modal-tall" id="myModalHorizontal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog"  style="">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
					</button>
					 <span class="modal-title" id="myModalLabel" style="font-size: 17"><b>List of Test</b><div align="right"> <button align="" type="button" class="btn btn-primary" onclick="addall()">ADD
					</button></div> </span>
					  <span class="modal-title" id="" style="font-size: 17"><b>&nbsp;</b></span>
				</div>
        
				<!-- Modal Body -->
				<div class="modal-body">

					

  <%
    
	Map<String,LabTestVO> _mpp=(Map<String,LabTestVO>)session.getAttribute(InvestigationConfig.MAP_ALL_TEST_DATA_XRAY_PROCESS);
    
    
    if(_mpp!=null && _mpp.size()>0)
	{
    	
    	
 	%>
 	 <script>
 	
    </script>
 	<div
					style="overflow-x:auto; background-color: ;margin-top:-20"
					class="anyClassscrollabletestshow">
					<table id="testtablelistoftest" class="table  table-xs  table-hover table-responsive order-listoftst" >
			
    <thead>
      <tr>
              <th></th>
      
        
        
      </tr>
    </thead> 
    <tbody>
    
   
    
    <%
    	   boolean flg=true;
			  Iterator itrAuto=_mpp.keySet().iterator();

    	  while(itrAuto.hasNext())
 		{
    		  String testlab=(String)itrAuto.next();
    		//  String test=testlab.split("#")[0];
    		  LabTestVO voPatEpisode=_mpp.get(testlab);
 			
    		  System.out.println("aabc======================= voPatEpisode"+"size:"+_mpp.size()+" "+voPatEpisode.getTestCode()+""+voPatEpisode.getTestName());
 	
    %>
   
     <% if(voPatEpisode.getTestCode()!=null && voPatEpisode.getLabCode()!=null && voPatEpisode.getTestName()!=null){ %>
      <tr>
   
   
        <td>   <input class="testsearchmodal" type="checkbox" value='<%=voPatEpisode.getTestCode()+"#"+voPatEpisode.getLabCode()%>' name="testshowforadd" />
         &nbsp;&nbsp;<%= voPatEpisode.getTestName().trim()%></td>
       
       
       
      </tr>
			<%}else{
				
	    		  System.out.println("bbnull=========");

				
			} %>
  
  <%    flg=false;
 		}%>
 		
 		 </tbody>
  </table>
  
  </div>
 		<%
 	}
    



  %>
  




				</div>

				<!-- Modal Footer -->
				<div class="modal-footer">
					
					 <button type="button" class="btn btn-primary" onclick="addall()">ADD
					</button> 
				</div>
			</div>
		</div>
	</div>
	


	 <div class="modal fade" id="myModalHorizontalprevreq" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog"  style="width:1250px;background-color: ">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
					</button>
					 <span class="modal-title" id="myModalLabel" style="font-size: 17"><b>Previous Requisition</b></span>
					  <span class="modal-title" id="" style="font-size: 17"><b>&nbsp;</b></span>
				</div>
        
				<!-- Modal Body -->
				<div class="modal-body">

					

  <%
    
	List<LabTestVO> _mppprevreq=null;
    
    
    if(_mppprevreq!=null && _mppprevreq.size()>0)
	{
 	%>
 	 <script>
 	
    </script>
 	<div
					style="overflow-x:auto; background-color: ;margin-top:-20"
					class="anyClassscrollabletestshow">
					<table id="preqvreqtbl" class="table  table-xs  table-hover table-responsive order-listoftst" >
			
    <thead>
      <tr>
              <th>Test</th>
      
         <th>Laboratory</th>
         <th> Test Type </th>
         <th>Status</th>
         <th> Priority </th>
                 <th>  Requisition Date  </th>
                         <th>   </th>
        
      </tr>
    </thead> 
    <tbody>
    
   
    
    <%
    	   boolean flg=true;

    	  for(int k=0;k<_mppprevreq.size();k++)
 		{
    		  
    		//  String test=testlab.split("#")[0];
    		  LabTestVO voPatEpisodeprev=_mppprevreq.get(k);
 			
    		
 	
    %>
    
    
      
       
       <%
       String prvReqStatus=voPatEpisodeprev.getPrvReqStatus();
      String testgrpcode= (voPatEpisodeprev.getTestGroupCode()==null?"0":voPatEpisodeprev.getTestGroupCode());
    	 String color="";
  		 if(prvReqStatus.equals("2")||prvReqStatus.equals("5"))
  		 color="skyblue";
  		 if(prvReqStatus.equals("3"))
  			 color="silver";
  		 if(prvReqStatus.equals("4"))
  			 color="#CC99FF";
  		 if(prvReqStatus.equals("6"))
  			 color="#ffe6e6";
  		 if(prvReqStatus.equals("7"))
  			 color="aqua";
  		 if(prvReqStatus.equals("8"))
  			 color="purple";
  		 if(prvReqStatus.equals("9"))
  			 color="fuchsia";
  		 if(prvReqStatus.equals("10")||prvReqStatus.equals("11"))
  			 color="blue";
  		 if(prvReqStatus.equals("12"))
  			 color="olive";
  		 if(prvReqStatus.equals("13"))
  			 color="lime";
  		 if(prvReqStatus.equals("14"))
  			 color="gold";
  		 if(prvReqStatus.equals("15"))
  			 color="teal";
  		 if(prvReqStatus.equals("16"))
  			 color="#EB7273";
  		 if(prvReqStatus.equals("26"))
  			 color="brown";
  		 if(prvReqStatus.equals("17"))
  			 color="#FFA500";
  		 if(prvReqStatus.equals("18"))
  			 color="#FFA599";
  		 if(prvReqStatus.equals("55"))
  			 color="#9999FF";
  	
  		 String color1="";
  		 //color1="background-color:grey;color:"+color;
		 
       %>
        
      <tr style="<%=color1%>">
   
        <td> 
        
          <% if(voPatEpisodeprev.getTestGroupCode()==null || voPatEpisodeprev.getTestGroupCode().equals("0") || voPatEpisodeprev.getTestGroupCode().equals("")) {%>
         &nbsp;&nbsp;<%= voPatEpisodeprev.getTestName()%></td>
       <%}else{ %>
                &nbsp;&nbsp;<%= voPatEpisodeprev.getTestName()+"(Group: "+voPatEpisodeprev.getTestGroupCode()+")"%>
       <%} %>
       
       </td>
       
       <td ><%=voPatEpisodeprev.getLabName() %></td>
              <td><%="Patient Based" %></td>
       
              <td><%=voPatEpisodeprev.getStatus() %></td>
              <td><%=voPatEpisodeprev.getPriority() %></td>
              <td><%=voPatEpisodeprev.getReqDate() %></td>
            <!--   <td></td> -->
   <%        if(prvReqStatus.equals("2")||prvReqStatus.equals("5"))
	{ 
	%>  
	     <td><a class='' title='Delete' data-toggle='tooltip' onClick="deleteRowPrvReqDtl('<%=voPatEpisodeprev.getPrvLabCode()%>','<%=voPatEpisodeprev.getPrvTestCode()%>','<%=voPatEpisodeprev.getReqNo()%>','<%=testgrpcode%>','<%=voPatEpisodeprev.getTestName()%>')"><i class="fa fa-trash" aria-hidden="true"  ></i></a></td>
	     
         <%}else{ %>
          <td></td> 
         <%} %>
	<!-- td9.innerHTML="<div align='center' ><img src='/HISInvestigationG5/hisglobal/images/minus.gif' id='minusButton' onClick='deleteRowPrvReqDtl("+(parseInt(nRow)-1)+","+(labCode)+","+(testCode)+","+"\""+(reqNo)+"\""+","+"\""+(testGroupCode)+"\""+")'></div>";
	
	} -->
	
              
       
      </tr>
			
  
  <%    flg=false;
 		}%>
 		
 		 </tbody>
  </table>
  </div>
 		<%
 	}
    



  %>
  




				</div>

				<!-- Modal Footer -->
				<div class="modal-footer">
					
					 <button type="button" class="btn btn-primary" onclick="closeprevreq()">Close
					</button> 
				</div>
			</div>
		</div>
	</div> 
	
	
	
	
	<!-- Modal -->
	
	
	</logic:present>


		<%-- <html:hidden name="InvestigationRaisingDtlXrayFB" property="hmode" /> --%>
   
      			
      
      
</html:form>

</body>


</html>
