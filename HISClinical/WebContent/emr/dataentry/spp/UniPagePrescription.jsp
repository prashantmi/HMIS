<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.*"%>

<%@page import="ehr.*"%>
<%@page import="emr.*"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>One Page Prescription</title>      
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/style.css" rel="stylesheet"> 
<link href="/HIS/hisglobal/bbpublic/assets/css/elements.css" rel="stylesheet"> 
<link href="/HIS/hisglobal/bbpublic/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="/HIS/hisglobal/bbpublic/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>
<!-- <script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script> -->
<his:javascript src="/registration/js/popup.js"/>
<script src="/HIS/hisglobal/bbpublic/assets/elements/js/bootstrap.min.js"></script>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/registration.js"/>
</head>
<style>

input, select, textarea{
border: 1px solid #bbb;
}
.panel-heading{
 background:linear-gradient(to bottom, #135d8c 0%,#1277b5 0%,#1277b5 32%,#135d8c 100%);
 
}
.panel-heading .accordion-toggle:after {
    /* symbol for "opening" panels */
    font-family: 'Glyphicons Halflings';  /* essential for enabling glyphicon */
    content: "\e114";    /* adjust as needed, taken from bootstrap.css */
    float: right;        /* adjust as needed */
    color: grey;         /* adjust as needed */
   
}
.panel-heading .accordion-toggle.collapsed:after {
    /* symbol for "collapsed" panels */
    content: "\e080";    /* adjust as needed, taken from bootstrap.css */
}
fieldset.scheduler-border {
    border: 1px groove blue !important;
    padding: 0 1.4em 1.4em 1.4em !important;
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
}

legend.scheduler-border {
        font-size: 1.2em !important;
        font-weight: bold !important;
        text-align: left !important;
        width:auto;
        padding:0px 5px 10px;
        border-bottom:none;
        color:
    }
    #mynav {
        top: 0;
        position: fixed;
        left: 0;
        right: 0;
        margin: 0 auto;
        z-index: 1030;
        height:5px;
        color:#aaa;
       /*  background-color:#222; */
        background-color:#ffff;
       
      }
      
     .modal-dialog
     {
     	width:1100px;
     }
     
     .textAlign
     {
     	text-align:left;
     }
     .panelHeadingPadding
     {
     	padding:1px;
     }
     .aPanelBody
     {
     	color:white;
     	font-weight:bold;
     }
     
      .success {
  -webkit-animation: seconds 1.0s forwards;
  -webkit-animation-iteration-count: 1;
  -webkit-animation-delay: 10s;
  animation: seconds 1.0s forwards;
  animation-iteration-count: 1;
  animation-delay: 10s;
  position: relative;
    
}
@-webkit-keyframes seconds {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
    left: -9999px; 
    position: absolute;   
  }
}
@keyframes seconds {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
    left: -9999px;
    position: absolute;     
  }
}

  </style>


<body style="margin:0%">

<form id="formOPP" action="/uniPagePrescription" method="POST">
<%-- <% String visitUrl = "/emr/uniPagePrescription.cnt?hmode=VISITREASON";  %>

<% String diagnosisUrl = "/emr/uniPagePrescription.cnt?hmode=DESKDIAGNOSIS";  %> --%>

<% String visitUrl = "/emr/ehrSection_OPDNEXTVISITDETAIL.cnt?hmode=OPDNEXTVISITDETAIL";  %>
<% String diagnosisUrl = "/emr/ehrSection_DESKDIAGNOSIS.cnt?hmode=DESKDIAGNOSIS";  %>
<% String treatmentUrl = "/emr/ehrSection_DESKTREATMENT.cnt?hmode=NEW";  %>
<% String investigationUrl = "/emr/ehrSection_REQUISITIONRAISING.cnt?hmode=REQUISITIONRAISING";  %>
<% String followupUrl = "/emr/ehrSection_FOLLOWUP.cnt?hmode=FOLLOWUP";  %>
<% String statusatdischarge = "/emr/ehrSection_DISCHARGE.cnt?hmode=DISCHARGE"; %><!-- Nilesh Gupta -->
<% String examinationUrl = "/emr/ehrSection_EXAMINATION.cnt?hmode=NEW";  %>
<% String complaintsUrl = "/emr/ehrSection_COMPLAINTS.cnt?hmode=NEW";  %>
<% String historyUrl = "/emr/ehrSection_HISTORY.cnt?hmode=NEW";  %>


<div id= "mynav">
	<div class="pull-right">
		<img  class="button " src="/../HIS/hisglobal/images/buttons/btn-sv.png"  style="cursor:pointer" id="saveId" tabindex="1"  >
	 	<img  class="button " src="/HIS/hisglobal/images/buttons/btn-pnt.png"  style="cursor:pointer" tabindex="1"  onclick="printPage(event)">	
	 	<!-- New Clear Button added -->
	 	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW');">
 	</div>
</div>

 <div id="savemsg" class="alert alert-success alert-dismissable success"  style="display:none; font-size: 16px; width: 50%;">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    <strong style="font-size: 16px;">Success!</strong> Data saved successfully
  </div>
  
 <div class="modal-dialog" style="margin-top:30px;">
 	<div class="modal-content">
 	
	<div class="modal-body" style="padding:0%;">
  	<div class="row" id="patTile" style="margin:5px;">
 		<!-- <fieldset class="scheduler-border">
    		<legend class="scheduler-border">Patient Tile</legend> -->
    		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" /> 
    		
   		<!-- </fieldset> -->
 	</div> 
 	<div class="row" style="margin:2px;">
 	 <div class="panel panel-default">
		        <div class="panel-heading"   data-toggle="collapse" data-target=".my-collapsevisit" style="padding:1px;">
		            <div class="row">
		                <div class="col-xs-10 textAlign">
		                    <a data-toggle="collapse" data-target=".my-collapsevisit" class="aPanelBody">Visit Detail</a>
		                </div>
		                <div class="col-xs-2 text-right">
		                    <a data-toggle="collapse" data-target=".my-collapsevisit" class="aPanelBody">
		                        <span class="glyphicon glyphicon-minus collapse out my-collapsevisit"></span>
		                        <span class="glyphicon glyphicon glyphicon-plus collapse in my-collapsevisit"></span>
		                    </a>
		                </div>
		            </div>
		        </div>
		        <div class="panel-body collapse out  my-collapsevisit "  style="padding:2px;" id="visitDetailBody">
		            <div class="row">
		                
		                   <jsp:include page="<%=visitUrl%>" flush="true" />  
		               
		            </div>
		        </div>
		    </div>
 	</div>
 	<div class="row" style="margin:2px;">
		     <div class="panel panel-default">
		        <div class="panel-heading "  style="padding:1px;" data-toggle="collapse" data-target=".my-collapse1">
		            <div class="row">
		                <div class="col-xs-10 textAlign">
		                    <a data-toggle="collapse" data-target=".my-collapse1" class="aPanelBody">Diagnosis</a>
		                </div>
		                <div class="col-xs-2 text-right">
		                    <a data-toggle="collapse" data-target=".my-collapse1" class="aPanelBody">
		                        <span class="glyphicon glyphicon-minus collapse out my-collapse1"></span>
		                        <span class="glyphicon glyphicon glyphicon-plus collapse in my-collapse1"></span>
		                    </a>
		                </div>
		            </div>
		        </div>
		        <div class="panel-body collapse out my-collapse1 " style="padding:1px;" id="diagnosisDetailBody">
		            <div class="row">
		                
		                    <jsp:include page="<%=diagnosisUrl %>" flush="true" />
		               
		            </div>
		        </div>
		    </div>  
		     <div class="panel panel-default">
		        <div class="panel-heading "  style="padding:1px;" data-toggle="collapse" data-target=".my-collapse4">
		            <div class="row">
		                <div class="col-xs-10 textAlign">
		                    <a data-toggle="collapse" data-target=".my-collapse4" class="aPanelBody">Complaints</a>
		                </div>
		                <div class="col-xs-2 text-right">
		                    <a data-toggle="collapse" data-target=".my-collapse4" class="aPanelBody">
		                        <span class="glyphicon glyphicon-minus collapse out my-collapse4"></span>
		                        <span class="glyphicon glyphicon glyphicon-plus collapse in my-collapse4"></span>
		                    </a>
		                </div>
		            </div>
		        </div>
		        <div class="panel-body collapse out my-collapse4 " style="padding:1px;" id="complaintsDetailBody">
		            <div class="row">
		                
		                    <jsp:include page="<%=complaintsUrl %>" flush="true" />
		               
		            </div>
		        </div>
		    </div>
		    
		   <div class="panel panel-default">
		        <div class="panel-heading "  style="padding:1px;" data-toggle="collapse" data-target=".my-collapse5">
		            <div class="row">
		                <div class="col-xs-10 textAlign">
		                    <a data-toggle="collapse" data-target=".my-collapse5" class="aPanelBody">History</a>
		                </div>
		                <div class="col-xs-2 text-right">
		                    <a data-toggle="collapse" data-target=".my-collapse5" class="aPanelBody">
		                        <span class="glyphicon glyphicon-minus collapse out my-collapse5"></span>
		                        <span class="glyphicon glyphicon glyphicon-plus collapse in my-collapse5"></span>
		                    </a>
		                </div>
		            </div>
		        </div>
		        <div class="panel-body collapse out my-collapse5 " style="padding:1px;" id="historyDetailBody">
		            <div class="row">
		                
		                    <jsp:include page="<%=historyUrl %>" flush="true" />
		               
		            </div>
		        </div>
		    </div>
		     	     <div class="panel panel-default">
		        <div class="panel-heading "  style="padding:1px;" data-toggle="collapse" data-target=".my-collapse3">
		            <div class="row">
		                <div class="col-xs-10 textAlign">
		                    <a data-toggle="collapse" data-target=".my-collapse3" class="aPanelBody">Examination</a>
		                </div>
		                <div class="col-xs-2 text-right">
		                    <a data-toggle="collapse" data-target=".my-collapse3" class="aPanelBody">
		                        <span class="glyphicon glyphicon-minus collapse out my-collapse3"></span>
		                        <span class="glyphicon glyphicon glyphicon-plus collapse in my-collapse3"></span>
		                    </a>
		                </div>
		            </div>
		        </div>
		        <div class="panel-body collapse out my-collapse3 " style="padding:1px;" id="examinationDetailBody">
		            <div class="row">
		                
		                    <jsp:include page="<%=examinationUrl %>" flush="true" />
		               
		            </div>
		        </div>
		    </div> 
		  
		      <div class="panel panel-default">
		        <div class="panel-heading " style="padding:1px;" data-toggle="collapse" data-target=".my-collapse2">
		            <div class="row">
		                <div class="col-xs-10 textAlign">
		                    <a data-toggle="collapse" data-target=".my-collapse2" class="aPanelBody">Treatment</a>
		                </div>
		                <div class="col-xs-2 text-right">
		                    <a data-toggle="collapse" data-target=".my-collapse2" class="aPanelBody">
		                        <span class="glyphicon glyphicon-minus collapse out my-collapse2"></span>
		                        <span class="glyphicon glyphicon glyphicon-plus collapse in my-collapse2"></span>
		                    </a>
		                </div>
		            </div>
		        </div>
		        <div class="panel-body collapse out my-collapse2 " style="padding:1px;" id="treatmentDetailBody">
		            <div class="row">
		              
		                             <jsp:include page="<%=treatmentUrl %>" flush="true" />
		         
		               
		            </div>
		        </div>
		    </div>
		     
		     <div class="panel panel-default">
		        <div class="panel-heading "  style="padding:1px;" data-toggle="collapse" data-target=".my-collapse14">
		            <div class="row">
		                <div class="col-xs-10 textAlign">
		                    <a data-toggle="collapse" data-target=".my-collapse14" class="aPanelBody">Investigation</a>
		                </div>
		                <div class="col-xs-2 text-right">
		                    <a data-toggle="collapse" data-target=".my-collapse14" class="aPanelBody">
		                        <span class="glyphicon glyphicon-minus collapse out my-collapse14"></span>
		                        <span class="glyphicon glyphicon glyphicon-plus collapse in my-collapse14"></span>
		                    </a>
		                </div>
		            </div>
		        </div>
		        <div class="panel-body collapse out my-collapse14 " style="padding:1px;" id="investigationDetailBody">
		            <div class="row">
		                
		                    <jsp:include page="<%=investigationUrl %>" flush="true" />
		               
		            </div>
		        </div>
		    </div>
		    
		    
		    
		     		    
		     <div class="panel panel-default">
		        <div class="panel-heading " style="padding:1px;" data-toggle="collapse" data-target=".my-collapseFollowup">
		            <div class="row">
		                <div class="col-xs-10 textAlign">
		                    <a data-toggle="collapse" data-target=".my-collapseFollowup" class="aPanelBody">FollowUp and Visit Summary</a>
		                </div>
		                <div class="col-xs-2 text-right">
		                    <a data-toggle="collapse" data-target=".my-collapsefollowup" class="aPanelBody">
		                        <span class="glyphicon glyphicon-minus collapse out my-collapseFollowup"></span>
		                        <span class="glyphicon glyphicon glyphicon-plus collapse in my-collapsefollowup"></span>
		                    </a>
		                </div>
		            </div>
		        </div>
		        <div class="panel-body collapse out my-collapseFollowup " style="padding:1px;" id="followupDetails">
		            <div class="row">
		               
		                    <jsp:include page="<%=followupUrl %>" flush="true" />
		                
		            </div>
		        </div>
		    </div>
		    
		    
		    <!-- Edited By Nilesh Gupta 17 OCT 2017 -->
		    
		       <div class="panel panel-default">
		        <div class="panel-heading " style="padding:1px;" data-toggle="collapse" data-target=".my-collapseDischarge">
		            <div class="row">
		                <div class="col-xs-10 textAlign">
		                    <a data-toggle="collapse" data-target=".my-collapseDischarge" class="aPanelBody">Status at Discharge</a>
		                </div>
		                <div class="col-xs-2 text-right">
		                    <a data-toggle="collapse" data-target=".my-collapseDischarge" class="aPanelBody">
		                        <span class="glyphicon glyphicon-minus collapse out my-collapseDischarge"></span>
		                        <span class="glyphicon glyphicon glyphicon-plus collapse in my-collapseDischarge"></span>
		                    </a>
		                </div>
		            </div>
		        </div>
		        <div class="panel-body collapse out my-collapseDischarge" style="padding:1px;" id="collapseDischarge">
		            <div class="row">
		                    <jsp:include page="<%=statusatdischarge%>" flush="true" />

		            </div>
		        </div>
		    </div>
   
  </div></div></div>
</div>
<!-- <div id="savemsg" style="display:none;">
<h4 style="color:red">Data saved successfully Nilesh</h4>

</div> -->


<div id="notsavemsg" style="display:none;">
<h4 style="color:red">Data not saved </h4>

</div>
<script>
var saveFlag;
var form = $('#commonTransactionLayoutFormId');
	
	$('#saveId').click(function(e) {
	
		setfreeTextVR();
		// ********* VISITREASON SAVE ***********//
		$.ajax({
			  url: createFHashAjaxQuery("/HISClinical/emr/ehrSection_OPDNEXTVISITDETAIL.cnt?hmode=SAVE"),
			    type : 'POST',
			    data : form.serialize(),
			    async: false,
				success: function(data) {
					   	if(data=="true")
			    		{
			    		//alert("visit data saved successfully");
			    		//clearOPP();
			    		saveFlag=1;
			    	    }
			    	    else
			    	    	{
			    	    	saveFlag=0;
			    	  // 	alert("visit data not saved");
			    	    	}
			      }
			
			}); 
		// ********* FOLLOW UP SAVE ***********//
		setfreeTextFU();
		$.ajax({
			    url: createFHashAjaxQuery("/HISClinical/emr/ehrSection_FOLLOWUP.cnt?hmode=SAVE"),
			    type : 'POST',
			    data : form.serialize(),
			    async: false,
				success: function(data) {
			    	if(data=="true")
			    		{
			    	//	alert(" follow up data saved successfully");
			    		//clearOPP();
			    		saveFlag=1;
			    	    }
			    	    else
			    	    	{saveFlag=0;
			    	   //	alert("follow up data not saved");
			    	   }
			      }
			
			});
			    	
			    	
	// ********* TREATMENT SAVE ***********//
			$.ajax({
				    url: createFHashAjaxQuery("/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=SAVE"),
				    type : 'POST',
				    data : form.serialize(),
				    async: false,
					success: function(data) {
				    	if(data=="true")
				    		{
				    //		alert(" treatment data saved successfully");
				    		saveFlag=1;
				    		clearTreatment();
				    	    }
				    	    else
				    	    	{
				    	    	saveFlag=0;
				    	  // 	alert("treatment data not saved");
				    	    	}
				      }
				
				}); 



			// ********* DISCHARGE SAVE ***********//
			setfreeTextSAD();
			$.ajax({  
			    url: "/HISClinical/emr/ehrSection_DISCHARGE.cnt?hmode=SAVE",
			    type : 'POST',
			    data : form.serialize(),
			    async: false,
				success: function(data) {
			    	if(data=="true")
			    		{
			    		//alert(" Status at Discharge data saved successfully");
			    		
			    		saveFlag=1;
			    	    }
			    	    else
			    	    	{saveFlag=0;
			    	   	//alert("Status at Discharge data not saved");
			    	   }
			      }
			
			});    	
			
				    	
			
			// ********* INVESTIGATION SAVE ***********//
 	$.ajax({
				    url: "/HISClinical/emr/ehrSection_REQUISITIONRAISING.cnt?hmode=SAVE",
				    type : 'POST',
				    data : form.serialize(),
				    async: false,
					success: function(data) {
				    	if(data=="true")
				    		{
				    		//alert("investigation data saved successfully");
				    		//clearOPP();
				    		saveFlag=1;
				    	    }
				    	    else
				    	   //	alert("investigation data not saved :(");
				    	    	saveFlag=0;
				      }
				
				}); 
				    	
				
					    	
				    	
				    	

				    	
  // ********* DIAGNOSIS SAVE ***********//
		//setfreeTextDiagnosis();
		$.ajax({
		//	 url: "/HISClinical/emr/uniPagePrescription.cnt?hmode=SAVE",
		  url: createFHashAjaxQuery("/HISClinical/emr/ehrSection_DESKDIAGNOSIS.cnt?hmode=SAVE"),
		    type : 'POST',
		    data : form.serialize(),
		    async: false,
			success: function(data) {
		    	if(data=="true")
		    		{
		    		//alert("diagnosis data saved successfully");
		    		saveFlag=1;
		    		clearDiagnosis();
		    	    }
		    	    else{
		    	    	saveFlag=0;
		    	    
		    	  // 	alert("diagnosis data not saved");
		    	  }
		      }
		
		}); 
		
		
		// ********* HISTORY SAVE ***********//
		$.ajax({
			  url: "/HISClinical/emr/ehrSection_HISTORY.cnt?hmode=SAVE",
			    type : 'POST',
			    data : form.serialize(),
			    async: false,
				success: function(data) {
					   	if(data=="true")
			    		{
			    		//alert("visit data saved successfully");
			    		//clearOPP();
			    		saveFlag=1;
			    	    }
			    	    else
			    	    	{
			    	    	saveFlag=0;
			    	  // 	alert("visit data not saved");
			    	    	}
			      }
			
			}); 
		
		// ********* COMPLAINTS SAVE ***********//
		$.ajax({
			  url: "/HISClinical/emr/ehrSection_COMPLAINTS.cnt?hmode=SAVE",
			    type : 'POST',
			    data : form.serialize(),
			    async: false,
				success: function(data) {
					   	if(data=="true")
			    		{
			    		//alert("visit data saved successfully");
			    		//clearOPP();
			    		saveFlag=1;
			    	    }
			    	    else
			    	    	{
			    	    	saveFlag=0;
			    	  // 	alert("visit data not saved");
			    	    	}
			      }
			
			}); 
		
		// ********* EXAMINATION SAVE ***********//
		$.ajax({
			  url: "/HISClinical/emr/ehrSection_EXAMINATION.cnt?hmode=SAVE",
			    type : 'POST',
			    data : form.serialize(),
			    async: false,
				success: function(data) {
					   	if(data=="true")
			    		{
			    		//alert("visit data saved successfully");
			    		//clearOPP();
			    		saveFlag=1;
			    	    }
			    	    else
			    	    	{
			    	    	saveFlag=0;
			    	  // 	alert("visit data not saved");
			    	    	}
			      }
			
			}); 
		
		
 	 $.ajax({
			  url: "/HISClinical/emr/uniPagePrescription.cnt?hmode=NEW",
			    type : 'POST',
			    data : form.serialize(),
			    async: false,
				success: function(data) {
					   	if(data=="true")
			    		{
			    		//alert("visit data saved successfully");
			    		//clearOPP();
			    		saveFlag=1;
			    	    }
			    	    else
			    	    	{
			    	    	saveFlag=0;
			    	  // 	alert("visit data not saved");
			    	    	}
			      }
			
			});  
		
		 
		
		  saveEHRFlag();
		 // window.location.reload();
		  document.getElementsByName("hmode")[0].value='NEW';
		  document.forms[0].submit();
			
		
	});		    	
	
	function saveEHRFlag()
	{
		//if(saveFlag==1)
		//	{
				document.getElementById("savemsg").style.display="block";
				document.getElementById("notsavemsg").style.display="none";
		//	}
	//	else
		//	{
		//	document.getElementById("notsavemsg").style.display="block";
		//	document.getElementById("savemsg").style.display="none";
		//	}
		
	}
	
		
		function clearTreatment()
		{
		  	AddRowToTableTreatment();
	    	var row=$("#treatmentTable tr").length;
	    	if(row>2)
	    	for(var i=1;i<row-1;i++)
	    	{
	    	if(document.getElementsByName("treatmentRecordStatus")[i].value=="2" || document.getElementsByName("treatmentRecordStatus")[i].value=="4")
	    		{
	    	document.getElementsByName("treatmentRecordStatus")[i].value="1";
	    	
	    		}
	    	}
		
	    	
		}	
		
		
	function clearDiagnosis()
	{
		//	$('table#diagnosisTable tr#trFirstRow').remove();
	//	$("#diagnosisTable").find("tr:eq(1)").remove();
    	//alert(document.getElementsByName("diagnosisRecordStatus")[0].value);
    	AddRowToTableDiag();
    	//document.getElementById("commonTransactionLayoutFormId").reset();
    	var row=$("#diagnosisTable tr").length;
    	if(row>2)
    		{
    	for(var i=1;i<row-1;i++)
    	{
    	if(document.getElementsByName("diagnosisRecordStatus")[i].value=="2" || document.getElementsByName("diagnosisRecordStatus")[i].value=="4")
    	document.getElementsByName("diagnosisRecordStatus")[i].value="1";
    	}
     		}
		
		//document.getElementsByName("snomedPTRemarks")[0].value="";
		//$(document).find("input[type=hidden]").each(function ()
    	//{
    	 //   if (this.value == "") { // your condition here
    	 //       $(this).remove();
    	 //   }
    	//});
    	
	}
	
	function printPage(e)
	{
		/* var path = "/HISClinical/clinical/transaction/SinglePagePrescriptionPrint.jsp"; 
		openDependentPopup(path,event,500,800,'yes'); */
		var url = "/HISClinical/emr/dataentry/spp/UniPagePrescriptionPrint.jsp";
		//window.open("/HISClinical/clinical/transaction/SinglePagePrescriptionPrint.jsp");
		openPopup(url, e , 2480, 3508, 'yes');
		}
	
	
	
	
	</script>


<html:hidden name="UniPagePrescriptionFB" property="hmode" />
<html:hidden name="UniPagePrescriptionFB" property="patCrNo" />
<html:hidden name="UniPagePrescriptionFB" property="departmentUnitCode" />
<html:hidden name="UniPagePrescriptionFB" property="episodeCode" />

</form>
</body>
</html>