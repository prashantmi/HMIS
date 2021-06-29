<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.*"%>

<%@page import="ehr.*"%>
<%@page import="emr.*"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="emr.vo.PatientClinicalDocDetailVO"%>

<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="ehr.EHRConfig"%>
<%@page import="ehr.followup.vo.EHRSection_FollowupVO"%>
<%@page import="emr.vo.PatientClinicalDocDetailVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.UserVO"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>One Page Prescription</title>      

<script>

function submitPdf()
{
	//alert("hello");
	window.submitForm('HEADER');
}



</script>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> 
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/popup.js"/>
</head>
<style>


/* #accordion a {
	background-image: linear-gradient(bottom, #F7F7F7 0%, #FFFFFF 0% !important);
	background-image: -o-linear-gradient(bottom, #F7F7F7 0%, #FFFFFF 0%) !important;
	background-image: -moz-linear-gradient(bottom, #F7F7F7 0%, #FFFFFF 0%) !important;
	background-image: -webkit-linear-gradient(bottom, #F7F7F7 0%, #FFFFFF 0%) !important;
	background-image: -ms-linear-gradient(bottom, #F7F7F7 0%, #FFFFFF 0%) !important;
    background-color: transparent !important;
    -webkit-border-radius: 0px !important;
    -moz-border-radius: 0px !important;
    border-radius: 0px !important;
    -webkit-box-shadow: 0px rgba(180, 180, 180, 0.1) !important;
    -moz-box-shadow: 0px rgba(180, 180, 180, 0.1) !important;
    box-shadow: 0px 0px 0px 0px rgba(180, 180, 180, 0.1) !important; 
    display: inline !important;    
    border: 0px solid #DDDDDD !important;
    text-shadow: 0 0px 0px #FFFFFF !important; 
} */
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

<html:hidden name="UniPagePrescriptionFB" property="htmlPreview"/>
<html:hidden name="UniPagePrescriptionFB" property="pdfFileIn"/>
<html:hidden name="UniPagePrescriptionFB" property="pdfFileOut" value=""/>




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

<%List<String> listOfString = new ArrayList<String>(); %>
<div id= "mynav">
	<div class="pull-right">
		<img  class="button " src="/../HIS/hisglobal/images/buttons/btn-sv.png"  style="cursor:pointer" id="saveId" tabindex="1" onclick = "" >
	 	<img  class="button " src="/HIS/hisglobal/images/buttons/btn-pnt.png"  style="cursor:pointer" tabindex="1"  onclick="printPage(event)">
	 	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	 		
 	</div>
</div>

 <!-- <div id="savemsg" class="alert alert-success alert-dismissable success"  style="display:none; font-size: 16px; width: 50%;">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    <strong style="font-size: 16px;">Success!</strong> Data saved successfully
  </div> -->
  <html:hidden name="UniPagePrescriptionFB" property="hmode" />
<html:hidden name="UniPagePrescriptionFB" property="patCrNo" />


	

 <div class="modal-dialog" style="margin-top:30px;">
 	<div class="modal-content">
 	
	<div class="modal-body" style="padding:0%;">
	
	
	<div class="row" id="prescriptionDetails" style="margin:5px">	
			<jsp:include page="/emr/dataentry/spp/PrescriptionDetails.jsp" flush="true" />
		</div>
	
	  	<div class="row" id="patTile" style="margin:5px;">
 		<!-- <fieldset class="scheduler-border">
    		<legend class="scheduler-border">Patient Tile</legend> -->
    		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" /> 
    		
   		<!-- </fieldset> -->
 	</div> 
 	
 	
 	 <!--  <div class="panel-group" id="accordion" role="tablist" > --> 
 	  <div class="panel-group" id="" role="tablist" > 
 	<%
	List lstMenus = (List)session.getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST);
	if(lstMenus!=null)
	{
		
		String prevClinicalSectionCode="", newClinicalSectionCode="";
		for(int i=0;i<lstMenus.size();i++)
		{
			String viewurl="", exturl="", selecturl="";
			PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstMenus.get(i);
			String key = vo.getClinicalSecCompKey();
			String target = vo.getClinicalSecParentShort();
			String interfaceKey = vo.getClinicalSecTemplateKey();
			/* if(key.equalsIgnoreCase("ENCDIAGNOSIS"))
			{ 
			exturl= "/emr/ehrSection_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_ADDSAVE";
			selecturl= "/emr/ehrSection_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_SELECT";
			viewurl= "/emr/ehrSection_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_VIEW";
			}*/
			
			
			//New Mapping 
			 selecturl= "/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_SELECT"; 
			   //selecturl= "/emr/ehrSection_"+target+".cnt?hmode="+key;// add interface key
			//End
			
			/* else  
			{
			 exturl= "/emr/ehrSection_"+key+".cnt?hmode=NEW";
			 selecturl="/emr/ehrSection_"+key+".cnt?hmode=VIEW";
			} */
			
		//	String newurl="/HISClinical/"+exturl;
			newClinicalSectionCode= vo.getClinicalSectionCode();
			listOfString.add(key);
	%>
	<%-- <script>
		var patClinicalDoc_ADDSAVE_url_<%=key%>= "<%=exturl%>";
		var patClinicalDoc_SELECT_url_<%=key%>= "<%=selecturl%>";
		var patClinicalDoc_VIEW_url_<%=key%>= "<%=viewurl%>";
	</script> --%>
 		
	<!-- <div class="panel panel-default"> --> <div class="panel panel-default aPanelBody">
	<%   if(!newClinicalSectionCode.equalsIgnoreCase(prevClinicalSectionCode)) {  %>
	
	<%-- 	<div class="panel-heading" style="padding:5px;">
			<div class="row">
				<div class="col-xs-11 text-left">
					<a class="accordion-toggle aPanelBody in" data-toggle="collapse" data-target=".comp_collapse<%=key%>"   data-tab-url="<%=selecturl%>"  > 
						<span class="glyphicon glyphicon-plus"></span>
				<input type="checkbox" name="ehrcomp_chk_select_<%=key%>" checked="checked"  onchange="setPatDocSectionViewHideJSON('<%=key%>')" />
 			         
 				
						<%=vo.getClinicalSectionName()%>
					</a>
				</div> --%>
				
				
      <div class="panel-heading " role="tab" id="headingselect<%=key%>" style="height:23px; padding:0px; ">
      <div class="row">
		<div class="col-xs-11 text-left">
		&nbsp;<%--  <input type="checkbox" name="ehrcomp_chk_select_<%=key%>" checked="checked"  onchange="setPatDocSectionViewHideJSON('<%=key%>')" /> --%>
        &nbsp; <a role="button"  data-toggle="collapse" data-parent="#accordion" href="#divselect<%=key%>" aria-expanded="true" aria-controls="divselect<%=key%>" style="color:white;">
      
       
 						<%=vo.getClinicalSectionName()%>
					</a>
      </div> <!--  ding dong     -->
   <%--  <div class="col-xs-1 text-right">
	  <a data-toggle="modal" data-target="#myModal<%=key%>" style="color:white;"> 
						<span class="glyphicon glyphicon-new-window"></span>
					</a>
					<a onclick="refreshTabbbb('<%=key%>')" style="color:white;">
						<span class="glyphicon glyphicon-refresh"></span>
					</a>
	  </div> --%><!--  ding dong   end   -->
	
				<%--<div class="col-xs-1 text-right">
					<a data-toggle="modal" data-target="#myModal<%=key%>" class="aPanelBody"> 
						<span class="glyphicon glyphicon-new-window"></span>
					</a> --%>
				<%-- 	<a data-toggle="modal" data-target="modalCompSELECT_<%=key%>" class="aPanelBody"> 
						<span class="glyphicon glyphicon-new-window"></span>
					</a>
 --%>

       				<%--  <a data-toggle="modal" data-target="#myModal<%=key%>" class="aPanelBody"> 
       				   <span class="glyphicon glyphicon-refresh"></span>
       				</a> --%>
					<%-- <a onclick="refreshTabbbb('<%=key%>')" class="aPanelBody">
						<span class="glyphicon glyphicon-refresh"></span>
					</a> 
				</div>--%>
        				 <%-- 
        				 <button class="aPanelBody" onclick="refreshTabbbb('<%=key%>')">
        				 <span class="glyphicon glyphicon-refresh"></span>
        				 </button> --%>
		               
		                <%-- <div class="col-xs-1  text-right">
		                
		                <a onclick="refreshTab(<%=url%>)">
      						    <span class="glyphicon glyphicon-refresh"></span>
       							 </a>
		                  		 <a href="<%=newurl%>">
        						  <span class="glyphicon glyphicon-new-window"></span>
        						</a> 
        					<a data-toggle="tab" data-target="#myModal<%=key%>" class="aPanelBody"> 
        				   <span class="glyphicon glyphicon-refresh"></span>
        				</a>	
        						
        				  
        						</div> --%>
			</div>	</div>	
			
			<div id="divselect<%=key%>" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingselect<%=key%>">
        <div class="panel-body">
			<jsp:include page="<%=selecturl%>" flush="false"  />
		</div>   </div>		
		
	<%}
	else
	{%>
	      
		<div id="divselect<%=key%>" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingselect<%=key%>">
         <%--  <div align="left" >
        &nbsp; <input type="checkbox" name="ehrcomp_chk_select_<%=key%>" checked="checked"  onchange="setPatDocSectionViewHideJSON('<%=key%>')" />
        </div> --%><div class="panel-body">
			<jsp:include page="<%=selecturl%>" flush="false"  />
		</div>   </div>	
	<%}
	prevClinicalSectionCode= newClinicalSectionCode; %>
	
		       <%--   <div class="panel-body collapse out  my-collapsevisit "  style="padding:2px;" ><b><%=vo.getClinicalSecCompTitle()%></b>
		            <div class="row">
		             <jsp:include page="<%=url%>" flush="true" />
		               <div id="myModal<%=key%>"  class="modal fade" role="dialog" >
 						 <div class="modal-dialog">

   						 <!-- Modal content-->
    					<div class="modal-content">
     					 <div class="modal-header">
     					 
     					  <button type="button" class="close" data-dismiss="modal">&times;</button>
		                <div class="modal-body">
		                   <jsp:include page="<%=url%>" flush="true" />  
		                   </div>
		                    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
		               </div></div>
		               </div></div> --%>
		   <!--  ding dong     -->
		<%-- <div id="myModal<%=key%>" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" >
						<jsp:include page="<%=exturl%>" flush="true" />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" onclick="saveModal('<%=key%>')" >Save</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
				<div id="savemsgcomp" style="display:none;">
					<h4 style="color:red">Data saved</h4>
				</div>
				<div id="notsavemsgcomp" style="display:none;">
					<h4 style="color:red">Data not saved </h4>
				</div>
		  	</div>
		</div> --%> <!--  ding dong end    -->
		<%-- <div id="divselect<%=key%>" class="panel-body collapse show  comp_collapse<%=key%> " style="padding:2px;"> --%><%--   style="padding:2px;" <b><%=vo.getClinicalSecCompTitle()%></b> --%>
			           <!--  <div class="row"> -->
        
		
			</div>
		
		<%-- <div id="modalCompSELECT_<%=key%>" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
		        </div>
		    </div>
		</div> --%>
		   <!--  </div>
 	</div>

 	</div></div>
</div> -->

	
<%}} %></div>
 	
 	
 	
 	
 	
 	
 	
 	
 	</div></div>
</div>
 <div id="savemsg" style="display:none;">
<h4 style="color:red">Data saved successfully </h4>

</div> 


<div id="notsavemsg" style="display:none;">
<h4 style="color:red">Data not saved </h4>

</div>
<script>
//var saveFlag;
//var form = $('#commonTransactionLayoutFormId');
//alert($('#commonTransactionLayoutFormId').serialize());
  // formOPP

//alert(form);
$('#saveId').click(function(e) {
	<%for(int i=0;i<listOfString.size();i++)
	{
	String value = listOfString.get(i).toString();
	%>  
	var a = validateClinicalSections('<%=value%>');
	//alert(a);
	if(a == false)
		{
		//alert("validation failed");
		   return;
		}
	else
		{
		//saveTemplateDataThroughAjax('<%=value%>');
		}
	<%}%>
	SaveTOAjax();
	saveEHRFlag();
});	

//Added by Vasu on 11.Feb.2019 to Validate Clinical Sections
function validateClinicalSections(key)
{
	//alert(key);
	/* var validateFlag = "";
	var functionString = "validate_"+key;
	if(eval("typeof " + functionString) == "function") // returns "undefined" or "function"
		{
		//return window.eval(functionString+"()");
		validateFlag = window.eval(functionString+"()");
		}
	else 
		{
		return true;
		validateFlag = true;
		}
	return validateFlag */
	return true;
   /*var str = "validate_"+key+"()";
   // var str = "validate_"+key;
    window.eval(str);
   
   // window[str]();
    var validate =  eval(str);

    return validate;*/
    
   /*  validate_ENC_CC_ROV();
    validate_ENC_DIAGNOSIS();
    validate_DISC_STATUS();
    validate_ENC_MED_ADV();
    validate_ENC_FOLLOWUP_DISCHARGE(); */
}

function saveTemplateDataThroughAjax(key)
{
	var form = $('#commonTransactionLayoutFormId');
	$.ajax({
			   url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_SAVE"),  
			    type : 'POST',
			    data : form.serialize(), 
			    async: false,
				success: function(data) {
					if(data=="true")
			    	{
			            //alert("Data saved successfully");
			    		saveFlag=1;
			       }
			    	    else
			    	    	{
			    	    	saveFlag=0;
			    	    	}
			      },
			      error: function(data)
			      {
			    	    //alert('request failed :');
			    }
			});
}

	
	//$('#saveId1').click(function(e) {
function SaveTOAjax()
{


		//var saveFlag;
		var form = $('#commonTransactionLayoutFormId');
		//alert($('#commonTransactionLayoutFormId').serialize());
		var base64FormData = window.btoa($('#commonTransactionLayoutFormId').serialize());
		//alert(base64FormData);
		
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
			    url: createFHashAjaxQuery('/HISClinical/emr/ehrSection_FOLLOWUP.cnt?hmode=SAVE'),
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
				    url: createFHashAjaxQuery('/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=SAVE'),//&base64FormData='+base64FormData),
				    type : 'POST',
				    data : form.serialize(),//base64FormData,//form.serialize(),
				    async: false,
					success: function(data) {
						//alert(data);
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
			/* setfreeTextSAD();
			$.ajax({  
			    url: createFHashAjaxQuery("/HISClinical/emr/ehrSection_DISCHARGE.cnt?hmode=SAVE"),
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
			
			});    	 */
			
				    	
			
			// ********* INVESTIGATION SAVE ***********//
 	/* $.ajax({
				    url: createFHashAjaxQuery("/HISClinical/emr/ehrSection_REQUISITIONRAISING.cnt?hmode=SAVE"),
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
				
				});  */
				    	
				
					    	
				    	
				    	

				    	
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

		$.ajax({
			  url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_ENC_EXAM.cnt?hmode=SAVE"),
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
	}

//);
		// ********* HISTORY SAVE ***********//
		/* $.ajax({
			  url: createFHashAjaxQuery("/HISClinical/emr/ehrSection_HISTORY.cnt?hmode=SAVE"),
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
			
			});  */
		
		// ********* COMPLAINTS SAVE ***********//
		/* $.ajax({
			  url: createFHashAjaxQuery("/HISClinical/emr/ehrSection_COMPLAINTS.cnt?hmode=SAVE"),
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
			
			});  */
		
		// ********* EXAMINATION SAVE ***********//
		/* $.ajax({
			  url: createFHashAjaxQuery("/HISClinical/emr/ehrSection_ENC_EXAM.cnt?hmode=SAVE"),
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
			  url: createFHashAjaxQuery("/HISClinical/emr/uniPagePrescription.cnt?hmode=NEW"),
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
			
		
	});	 */	    	
	
	function saveEHRFlag()
	{
		//alert("success");
		//if(saveFlag==1)
		//	{
				document.getElementById("savemsg").style.display="block";
				document.getElementById("notsavemsg").style.display="none";
				document.getElementById("saveId").style.display="none";
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
		var patCr = document.getElementsByName("patCrNo")[0].value;
		//alert(patCr);
		var url = "/HISClinical/emr/uniPagePrescription.cnt?hmode=PREVIEW&patCrNo="+patCr;
		openPopup(createFHashAjaxQuery(url),e,400,600);
		
		//var url = "/HISClinical/emr/dataentry/spp/UniPagePrescriptionPrint.jsp";
		//window.open("/HISClinical/clinical/transaction/SinglePagePrescriptionPrint.jsp");
		//openPopup(createFHashAjaxQuery(url),e,400,600);
		//openPopup(createFHashAjaxQuery(url), e , 2480, 3508, 'yes');
		}

	
	</script>



<%-- <html:hidden name="UniPagePrescriptionFB" property="departmentUnitCode" />
<html:hidden name="UniPagePrescriptionFB" property="episodeCode" /> --%>
<%listOfString = new ArrayList<String>(); %>
<% HospitalMstVO hospVO = (HospitalMstVO)session.getAttribute(EHRConfig.HOSPITAL_DEATILS); %>
<% EHRSection_FollowupVO VisitVO = (EHRSection_FollowupVO)session.getAttribute(EHRConfig.FOLLOWUP_ESSENTAILS); %>
<%HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);
UserVO userVO=ControllerUTIL.getUserVO(request);
String opdlocal=userVO.getUsrName(); %>

<div id ="header" style = "display: none">
		<div>
			<div class="row" style="text-align:center;">
				<h4 ><font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif"><b> <%=hospitalVO.getHospitalName().toUpperCase() %> </b></font></h4>
				 <h5><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <%=hospitalVO.getAddress1() %> </font></h5>
				<h5><font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif"> <%=hospitalVO.getAddress2() %> </font></h5>
			</div>
		</div>
</div>
</form>
</body>
</html>