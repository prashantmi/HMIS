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
<%@page import="emr.vo.PatientClinicalDocDetailVO"%>
<%@ page buffer="none" %>
<%@page import="java.util.*"%>

<%@page import="hisglobal.vo.UserVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.HospitalMstVO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Patient Clinical Documents</title>      
 <link href="https://afeld.github.io/emoji-css/emoji.css" rel="stylesheet">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/style.css" rel="stylesheet"> 
<link href="/HIS/hisglobal/bbpublic/assets/css/elements.css" rel="stylesheet"> 
<link href="/HIS/hisglobal/bbpublic/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="/HIS/hisglobal/bbpublic/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/> 

<script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>
<!-- <script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script> -->
<%-- <his:javascript src="/registration/js/popup.js"/> --%>
<script src="/HIS/hisglobal/bbpublic/assets/elements/js/bootstrap.min.js"></script>
</head>
<style>
     
input, select, textarea{
border: 1px solid #bbb;
}
.panel-heading{
 background:linear-gradient(to bottom, #135d8c 0%,#1277b5 0%,#1277b5 32%,#135d8c 100%);
 padding:0px;
 
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
     
    
   
     
      .modal-dialog
     {
     	width:auto;
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

 .ui-autocomplete {
       z-index:2147483647;
     } 
     
     
     .rainbow {
  background-image: -webkit-gradient( linear, left top, right top, color-stop(0, #f22), color-stop(0.15, #f2f), color-stop(0.3, #22f), color-stop(0.45, #2ff), color-stop(0.6, #2f2),color-stop(0.75, #2f2), color-stop(0.9, #ff2), color-stop(1, #f22) );
  background-image: gradient( linear, left top, right top, color-stop(0, #f22), color-stop(0.15, #f2f), color-stop(0.3, #22f), color-stop(0.45, #2ff), color-stop(0.6, #2f2),color-stop(0.75, #2f2), color-stop(0.9, #ff2), color-stop(1, #f22) );
  color:transparent;
  -webkit-background-clip: text;
  background-clip: text;
 /*  font-size: 0.5em  */
  
}
  </style>

<script>

function submitForm1(mode)
{
  	 //alert(mode);
  	// convertHtmlToBase64();
  	 
     document.getElementsByName("hmode")[0].value='NEW';
	 document.forms[0].submit();
	 
}


function convertHtmlToBase64()
{	
	var htmlData = document.getElementsByName('documentHTML')[0].value;
	var enc = window.btoa(htmlData);
	//alert(enc);
	document.getElementsByName('documentHTML')[0].value = enc;
}


</script>
<%List<String> listOfString = new ArrayList<String>(); %>
<body style="margin:0%">
<form id="formOPP" action="/patientClinicalDocuments" method="POST" >

<div id= "mynav">
	
</div>
<div id= "mynav">
	<div class="pull-right">
		<img  class="button " src="/HIS/hisglobal/images/buttons/btn-sv.png"  style="cursor:pointer" id="saveId" tabindex="1"  >
	 	  <img  class="button " src="/HIS/hisglobal/images/buttons/btn-preview.png"  style="cursor:pointer" tabindex="1"  onclick="printPage(event)">
	 	 <!-- <img  class="button " src="/HIS/hisglobal/images/buttons/btn-preview.png"  style="cursor:pointer" tabindex="1"  onclick="submitPage('PREVIEW')"> -->
		<img  class="button " src="/HIS/hisglobal/images/buttons/btn-ccl.png"  style="cursor:pointer" id="saveId" tabindex="1"  onclick="submitForm1('NEW')">
 	</div>
</div>
<br><br>
<div id="savemsg" class="alert alert-success alert-dismissable success"  style="display:none; font-size: 16px; width: 50%;">
	<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
	<strong style="font-size: 16px;">Success!</strong> Data saved successfully
</div>

 <!-- <div class="modal-dialog" style="margin-top:30px;">
 	<div class="modal-content">
 	
	<div class="modal-body" style="padding:0%;">
  --> <%-- 	<div class="row" id="patTile" style="margin:5px;">
 		<!-- <fieldset class="scheduler-border">
    		<legend class="scheduler-border">Patient Tile</legend> -->
    		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" /> 
    		
   		<!-- </fieldset> -->
 	</div> --%>
 	  <!-- <div class="panel-group" id="accordion" role="tablist" >  -->
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
			/* if(key.equalsIgnoreCase("ENCDIAGNOSIS"))
			{ 
			exturl= "/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_ADDSAVE";
			selecturl= "/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_SELECT";
			viewurl= "/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_VIEW";
			} */  
			if(key.equalsIgnoreCase("DESKTREATMENT"))
			{ 
				//selecturl= "/emr/ehrSection_"+key+".cnt?hmode=NEW";
				selecturl= "/emr/ehrComposition_ADVICEONDISCHARGE.cnt?hmode=PATCLINICALDOC_ADVICEONDISCHARGE_SELECT";
			}
			
			else
			{ 
			  exturl= "/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_ADDSAVE";
			  selecturl= "/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_SELECT";
			  viewurl= "/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_VIEW";
			}
			
			/* else  
			{
			 exturl= "/emr/ehrComposition_"+key+".cnt?hmode=NEW";
			 selecturl="/emr/ehrComposition_"+key+".cnt?hmode=VIEW";
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
				
				
      <div class="panel-heading " role="tab" id="headingselect<%=key%>" style="height:28px; padding:0px;">
      <div class="row">
		<div class="col-xs-11 text-left">
		&nbsp; <input type="checkbox" name="ehrcomp_chk_select_<%=key%>" checked="checked"  onchange="setPatDocSectionViewHideJSON('<%=key%>')" />
        &nbsp; <a role="button"  data-toggle="collapse" data-parent="#accordion" href="#divselect<%=key%>" aria-expanded="true" aria-controls="divselect<%=key%>" style="color:white;">
      
       
 						<%=vo.getClinicalSectionName()%>
					</a>
      </div>
    <div class="col-xs-1 text-right">
	  <%-- <a data-toggle="modal" data-target="#myModal<%=key%>" style="color:white;"> 
						<span class="glyphicon glyphicon-new-window"></span>
					</a> --%>
					<a onclick="refreshTabbbb('<%=key%>')" style="color:white;">
						<span class="glyphicon glyphicon-refresh"></span>
					</a>
	  </div>
	
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
		        
		<div id="myModal<%=key%>" class="modal fade" role="dialog">
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
		</div>
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
<!-- <div id="savemsg" style="display:none;">
<h4 style="color:red">Data saved successfully</h4>

</div> -->


<div id="notsavemsg" style="display:none;">
	<h4 style="color:red">Data not saved </h4>
</div>


<div id="msg" class="animated bounce infinite" style="display:block;">
	<!-- <h2><span class="rainbow">Developed & Maintained By: Manisha Gangwar</span> <i class="em em-sunglasses"></i></h2> -->
</div>
<html:hidden name="PatientClinicalDocumentsFB" property="clinicalDocCompSelectJSON" />
<html:hidden name="PatientClinicalDocumentsFB" property="clinicalDocCompSelectJSONModify" />
<html:hidden name="PatientClinicalDocumentsFB" property="hmode" />
<html:hidden name="PatientClinicalDocumentsFB" property="patCrNo" />
<html:hidden name="PatientClinicalDocumentsFB" property="departmentUnitCode" />
<html:hidden name="PatientClinicalDocumentsFB" property="episodeCode" />
<html:hidden name="PatientClinicalDocumentsFB" property="flagPreview" />
<html:hidden name="PatientClinicalDocumentsFB" property="flagDefaultSelectionOnload" />
<html:hidden name="PatientClinicalDocumentsFB" property="mode" />
<html:hidden name="PatientClinicalDocumentsFB" property="documentId" />
<html:hidden name="PatientClinicalDocumentsFB" property="documentTypeDesc" />
<html:hidden name="PatientClinicalDocumentsFB" property="documentTitle" />
<html:hidden name="PatientClinicalDocumentsFB" property="docStatus" />
<html:hidden name="PatientClinicalDocumentsFB" property="serialNo" /> 
<html:hidden name="PatientClinicalDocumentsFB" property="clinicalDocumentMode" />
<html:hidden name="PatientClinicalDocumentsFB" property="documentHTML" />
<html:hidden name="PatientClinicalDocumentsFB" property="htmlDocHeader" />

<script>
//alert("inside default select");
var jsonSelect =  {"patCrNo":"","patDocId":"","patEncounterDtl":{},"patCompositionSelectData":{"PATIENTSUMMARY":{"comp_select":"true","chk_value":[],"list_selected":[],"html":""}, "DISCHARGESUMMARY":{"comp_select":"true","chk_value":[],"list_selected":[],"html":""},"COMPLAINTS": {"comp_select":"true","chk_value":[],"list_selected":[], "html":""},"OPDNEXTVISITDETAIL": {"comp_select":"true","chk_value":[],"list_selected":[], "html":""},"HISTORY": {"comp_select":"true","chk_value":[],"list_selected":[], "html":""},"EXAMINATION": {"comp_select":"true","chk_value":[],"list_selected":[], "html":""},"TREATMENTGIVEN": {"comp_select":"true","chk_value":[],"list_selected":[], "html":""},"HOSPITALCOURSE": {"comp_select":"true","chk_value":[],"list_selected":[], "html":""},"ADVICEONDISCHARGE": {"comp_select":"true","chk_value":[],"list_selected":[], "html":""} ,"TREATMENT":{"comp_select":"true","chk_value":[],"list_selected":[],"html":""},"ENCDIAGNOSIS":{"comp_select":"true","chk_value":[],"list_selected":[],"html":""},
	"DISCHARGESTATUS":{"comp_select":"true","chk_value":[],"list_selected":[],"html":""}, "FOLLOWUPADVICE":{"comp_select":"true","chk_value":[],"list_selected":[],"html":""}, "ENCINVESTIGATION":{"comp_select":"true","chk_value":[],"list_selected":[],"html":""} }};
//,"ALLERGIES","INVESTIGATION","CHRONICDISEASE","PROGRESSNOTES","INTAKEOUTTAKE","VISITREASON","OPERATION","EXTINESTIGATION","HISTORY","COMPLAINTS","EXAMINATION","SERVICEAREA"}} ; // initial pat doc json 
document.getElementsByName("clinicalDocCompSelectJSON")[0].value=JSON.stringify(jsonSelect);
document.getElementsByName("flagPreview")[0].value ="0";

//alert(document.getElementsByName("clinicalDocumentMode")[0].value);

if(document.getElementsByName("clinicalDocumentMode")[0].value=="CLINICALDOCUMENTS")
//{
//alert(document.getElementsByName("flagDefaultSelectionOnload")[0].value);
//if(document.getElementsByName("flagDefaultSelectionOnload")[0].value =="1")
{
	//alert("inside default select");
/* setPatDocSelectJSON('ENCDIAGNOSIS');
setPatDocSelectJSON('DISCHARGESTATUS');
setPatDocSelectJSON('FOLLOWUPADVICE');
setPatDocSelectJSON('ADVICEONDISCHARGE');
 */

 /* setPatDocSelectJSON('ADVICEONDISCHARGE'); */
 setPatDocSelectJSON('ENCDIAGNOSIS');
 setPatDocSelectJSON('FOLLOWUPADVICE');
 setPatDocSelectJSON('PATIENTSUMMARY');
 setPatDocSelectJSON('TREATMENTGIVEN');
 setPatDocSelectJSON('DISCHARGESUMMARY');
/*  setPatDocSelectJSON('PATIENTSUMMARY');
 setPatDocSelectJSON('OPDNEXTVISITDETAIL');
 setPatDocSelectJSON('FOLLOWUPADVICE'); */
 
<%-- <%for(int i=0;i<listOfString.size();i++)
{
	String value = listOfString.get(i).toString();
	System.out.println(value);
	
	if(value.equalsIgnoreCase("DESKTREATMENT"))
	{ 
		value = "ADVICEONDISCHARGE";
	}
%>

setPatDocSelectJSON('<%=value%>');

	
<%}%>
document.getElementsByName("flagDefaultSelectionOnload")[0].value ="0"; --%>
}

//}
//alert(document.getElementsByName("hmode")[0].value);
if(document.getElementsByName("clinicalDocumentMode")[0].value=="MODIFY")

{
	//alert("inside modify");
    
	<%for(int i=0;i<listOfString.size();i++)
	{
		String value = listOfString.get(i).toString();
		System.out.println("modify"+value);
	%>
	
	setPatDocSelectJSON_modify('<%=value%>');
	<%}%>
	

	
}


function setPatDocSelectJSON_modify(key)
{
	var jsonSelectModify=JSON.parse(document.getElementsByName("clinicalDocCompSelectJSONModify")[0].value);
	//alert(key);
	//alert(jsonSelectModify);
	//alert(JSON.stringify(jsonSelectModify));
	
	var modchkVal=[];
	modchkVal=jsonSelectModify.patCompositionSelectData[key].chk_value;
	//alert(modchkVal);
	//alert(document.getElementsByName("clinicalDocCompSelectJSONModify")[0].value);
	
	 if(jsonSelectModify.patCompositionSelectData[key].comp_select == "true")
	{
		// alert("inside true");
		jsonSelect.patCompositionSelectData[key].comp_select = "true";
		//document.getElementsByName("ehrcomp_chk_select_"+key)[0].checked="checked";
		//alert(modchkVal);
		var chks = document.getElementsByName("ehrcomp_"+key+"_chk_select");
		//alert(chks.length);
	
		for(var k=0; k<chks.length;k++)
	 	chks[k].checked="";
			
		for(var i=0; i<modchkVal.length;i++)
			{for(var j=0; j<chks.length;j++)
	 		{//alert(chks[j].value+ ","+ modchkVal[i]);
			if(chks[j].value==modchkVal[i])
				chks[j].checked="checked";
			}}
		
		 
	}
	 else
	  {
		// alert("inside false");
			jsonSelect.patCompositionSelectData[key].comp_select = "false";
			//alert(document.getElementsByName("ehrcomp_chk_select_"+key));
			document.getElementsByName("ehrcomp_chk_select_"+key)[0].checked="";
				
	  }
	 setPatDocSelectJSON(key);
	
	//document.getElementsByName("ClinicalDocumentMode")[0].value="MODIFY"; 
	//document.getElementsByName("clinicalDocCompSelectJSON")[0].value=JSON.stringify(jsonSelect); 
	//document.getElementsByName("flagPreview")[0].value ="1";
		
} 








	

function refreshTabbbb(key)
{
	alert(key);
	var urlToRefresh= "/HISClinical/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_SELECT";
	alert(urlToRefresh);
	$.ajax({
		 //url: "/HISClinical/emr/uniPagePrescription.cnt?hmode=SAVE", "/HISClinical/emr/patClinicalDocuments_DESKDIAGNOSIS.cnt?hmode=DESKDIAGNOSIS"
		    url: "/HISClinical/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_SELECT",
		    type : 'POST',
		    async: false,
			success: function(data) {
				 //  var newHTML = data;
				  alert(data);
				 //  $("#myModal").modal('show');
				   //$('#divselect'+key).modal('hide');
				   //$('#divselect'+key).modal('show');
				 //  document.getElementById('divselect'+key).innerHTML=newHTML;
				   $('#divselect'+key).html(data);
				//$('#divselect'+key).innerhtml=newHTML; 
				//alert($('#divselect'+key).innerhtml);
				//alert(document.getElementById('divselect'+keyyy).innerHTML);
				//window.opener.location.href = window.opener.location;		
				
		    	/*if(data=="true")
		    		{
		    		//alert("diagnosis data saved successfully");
		    		//saveFlag=1;
		    		//clearDiagnosis();
		    	    }
		    	    else{
		    	    	//saveFlag=0;
		    	    
		    	  // 	alert("diagnosis data not saved");
		    	  }*/
		      }
		
		}); 
	
	
//	$('#divselect'+key).load(document.URL+'#divselect'+key);

//location.reload(true);
}







function saveModal(key)
{  
	//alert(document.getElementsByName("flagDefaultSelectionOnload")[0].value);
	var hmode="SAVE";
	var id='#myModal'+key;
	if(key=="ADVICEONDISCHARGE")
	{
	key="ADVICEONDISCHARGE";
	hmode="ADVICEONDISCHARGE_SAVE";
	}
	
	
	if(key=="ENCDIAGNOSIS") 
	{
		key="ENCDIAGNOSIS";
		hmode="ENCDIAGNOSIS_SAVE";
		//alert(key);
	}
	
	if(key=="DISCHARGESTATUS") 
	{
		key="DISCHARGESTATUS";
		hmode="DISCHARGESTATUS_SAVE";
		setSavefreeTextSAD();
		//alert(key);
	}
	
	if(key=="FOLLOWUPADVICE") 
	{
		key="FOLLOWUPADVICE";
		hmode="FOLLOWUPADVICE_ADDSAVE";
		//alert(key);
	}
	
	
	
	
	
	
	// ********* TREATMENT SAVE ***********//
	var ajaxUrl="/HISClinical/emr/ehrComposition_"+key+".cnt?hmode="+hmode;
	alert(ajaxUrl);
	
	$.ajax({
		    url: ajaxUrl,
		    type : 'POST',
		    data : form.serialize(),
		    async: false,
			success: function(data) {
		    	if(data=="true")
		    		{
		    //		alert(" treatment data saved successfully");
		    		saveFlag=1;
		    		//document.getElementsByName("flagDefaultSelectionOnload")[0].value="1";
		    		//alert(document.getElementsByName("flagDefaultSelectionOnload")[0].value);
		    		//clearTreatment();
		    	    }
		    	    else
		    	    	{
		    	    	saveFlag=0;
		    	  // 	alert("treatment data not saved");
		    	    	}
		      }
		
		}); 
	
	
	
	
	
	//$(id).modal('hide');
	saveEHRCompFlag();
	//document.getElementsByName("flagDefaultSelectionOnload")[0].value="1";
	//alert(document.getElementsByName("flagDefaultSelectionOnload")[0].value);
	//callrefres();
	location.reload(true);

}

function setSavefreeTextSAD ()   //for free text
{
	//alert("Inside SAD");
	//document.getElementsByName("statusAtDischarge")[0].value=document.getElementsByName("txt-snomed-ct-search_DS")[0].value;
	var text_data_SAD= document.getElementsByName("txt-snomed-ct-search_DS")[0].value;
	if(text_data_SAD.length > 0 )
	{
		var lastchar_SAD= text_data_SAD[text_data_SAD.length - 1];
		//alert(lastchar_SAD)
		if(lastchar_SAD.match(";"))
		{
			//alert("Nilesh : "+document.getElementsByName("txt-snomed-ct-search_FU2")[0].value);
			document.getElementsByName("statusAtDischarge")[0].value=document.getElementsByName("txt-snomed-ct-search_DS")[0].value;
			
		}
		else
		{
			//alert("false condition");
			document.getElementsByName("txt-snomed-ct-search_DS")[0].value=document.getElementsByName("txt-snomed-ct-search_DS")[0].value+";";
			document.getElementsByName("statusAtDischarge")[0].value=document.getElementsByName("txt-snomed-ct-search_DS")[0].value; 
		}
	}
 
}

function callrefres()
{
	
			
var ajaxUrl="/HISClinical/emr/patientClinicalDocuments.cnt?hmode=CLINICALDOCUMENTS";
	
	
	$.ajax({
		    url: ajaxUrl,
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

}

function saveEHRCompFlag()
{
	//if(saveFlag==1)
	//	{
			document.getElementById("savemsgcomp").style.display="block";
			document.getElementById("notsavemsgcomp").style.display="none";
	//	}
//	else
	//	{
	//	document.getElementById("notsavemsg").style.display="block";
	//	document.getElementById("savemsg").style.display="none";
	//	}
	
}






var saveFlag;
var form = $('#commonTransactionLayoutFormId');
	
	$('#saveId').click(function(e) {
		
		//createAdviceonDischargeHTML();
		if(document.getElementsByName("flagPreview")[0].value =="1")
		alert("please preview your changes before save");
		
		if(document.getElementsByName("flagPreview")[0].value =="0")
		{
		document.getElementsByName("clinicalDocCompSelectJSON")[0].value=JSON.stringify(jsonSelect); 
		//  *********PATIENT CLINICAL DOCUMENTS SAVE ***********//
			$.ajax({
			    url: "/HISClinical/emr/patientClinicalDocuments.cnt?hmode=SAVE",
			    type : 'POST',
			    data : form.serialize(),
			    async: false,
				success: function(data) {
			    	if(data=="true")
			    		{
			    //		alert(" treatment data saved successfully");
			    		saveFlag=1;
			    		//clearTreatment();
			    	    }
			    	    else
			    	    	{
			    	    	saveFlag=0;
			    	  // 	alert("treatment data not saved");
			    	    	}
			      }
			
			}); 
		
			saveEHRFlag();	
			submitForm1('NEW');
		}
		
				
				
				
		
		
		
	});		    	
	
	function saveEHRFlag()
	{
		//if(saveFlag==1)
		//	{
			 //    document.getElementsByName("saveFlag")[0].value="1";
				//document.getElementById("savemsg").style.display="block";
				//document.getElementById("notsavemsg").style.display="none";
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
    	if(document.getElementsByName("diagnosisRecordStatus")[i].value=="2" || document.getElementsByName("treatmentRecordStatus")[i].value=="4")
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
		var jsonSelect=document.getElementsByName("clinicalDocCompSelectJSON")[0].value;
		document.getElementsByName("clinicalDocCompSelectJSON")[0].value = jsonSelect;
		//alert(jsonSelect);

		//var htmlData = document.getElementsByName('documentHTML')[0].value;
		//alert("htmlData:"+htmlData);
		
		var headerContent = $('<div/>').append($('#header').clone()).html();
		//alert(headerContent);

		document.getElementsByName("htmlDocHeader")[0].value = headerContent;
		
		 var encHeader = window.btoa(headerContent);
		//alert(encHeader);  
		
        var sectionContent =  $('<div/>').append($('#sections').clone()).html();
        //alert(sectionContent);

        var footer =  $('<div/>').append($('#footer').clone()).html();
        //alert(footer)
		
		/* var path = "/HISClinical/clinical/transaction/SinglePagePrescriptionPrint.jsp"; 
		openDependentPopup(path,event,500,800,'yes'); */
		//var url = "/HISClinical/emr/datafetch/patientClinicalDocuments/PatientClinicalDocumentsPrint.jsp";
		//var url = "/HISClinical/emr/patientClinicalDocuments.cnt?hmode=PREVIEW";
		//alert(jsonSelect);
		var CrNo=document.getElementsByName("patCrNo")[0].value;

		//var url = "/HISClinical/emr/patientClinicalDocuments.cnt?hmode=PREVIEW"+"&patCrNo="+crNo+"&clinicalDocCompSelectJSON="+jsonSelect;
		var url = "/HISClinical/emr/patientClinicalDocuments.cnt?hmode=PREVIEW&clinicalDocCompSelectJSON="+jsonSelect;
		openPopup(createFHashAjaxQuery(url),e,400,600);

		//var url = "/HISClinical/emr/datafetch/patientClinicalDocuments/PatientClinicalDocumentsPrint.jsp?patCrNo="+CrNo;//?jsonData="+jsonSelect;
		//window.open("/HISClinical/clinical/transaction/SinglePagePrescriptionPrint.jsp");
		//openPopup(url, e , 2480, 3508, 'yes');
		
		
		}
	
	
	function submitPage(mode)
	{
		alert(mode);
		document.getElementsByName("mode")[0].value=mode;
		document.forms[0].submit();
	}


	
	
	
	function setPatDocSelectJSON(key)
	{
		//alert(key);
		
	//	alert(jsonSelect);
		// based on key iterate over elem aaray of related chks
		//alert(JSON.stringify(jsonSelect));
	    if(jsonSelect.patCompositionSelectData[key].comp_select == "true")
	    {	
		var chks = document.getElementsByName("ehrcomp_"+key+"_chk_select");
		var lstSelectedData=[], chkVal=[], x=0,  y=0;
		//alert(chks.length);
		for(var i=0; i<chks.length;i++)
		{
			//alert(chks[i].checked);
			if(chks[i].checked)
			{
				//alert(document.getElementsByName("ehrcomp_"+key+"_dataelem_json_"+chks[i].value)[0].value);
				var jsonData = JSON.parse(document.getElementsByName("ehrcomp_"+key+"_dataelem_json_"+chks[i].value)[0].value);
			    lstSelectedData[x++] = jsonData;
				chkVal[y++]= chks[i].value;
				//alert(jsonData); 
				
				//document.getElementsByName("ehrcomp_chk_select")[0].checked=checked;
				//jsonSelect.patCompositionSelectData[key].comp_select = "true";
				
			}
		}
		//alert(lstSelectedData);
		// which chk is checked add json
		jsonSelect.patCompositionSelectData[key].list_selected = lstSelectedData;
		jsonSelect.patCompositionSelectData[key].chk_value = chkVal;
		//alert(jsonSelect.patCompositionSelectData[key].chk_value);
		//alert(jsonSelect.patCompositionSelectData[key].list_selected);
		//alert(JSON.stringify(jsonSelect));
	    }
		document.getElementsByName("clinicalDocCompSelectJSON")[0].value=JSON.stringify(jsonSelect); 
		//alert(document.getElementsByName("clinicalDocCompSelectJSON")[0].value);
		document.getElementsByName("flagPreview")[0].value ="1";
				
	} 
	
	
	
	function setPatDocSectionViewHideJSON(key)
	{
		//alert(key);
		
	//	alert(jsonSelect);
		// based on key iterate over elem aaray of related chks
		var chks = document.getElementsByName("ehrcomp_chk_select_"+key)[0];
		//alert(chks);
		//alert(chks.checked);
		if(chks.checked)
			{
			jsonSelect.patCompositionSelectData[key].comp_select = "true";
			
			}
		else
			{
			jsonSelect.patCompositionSelectData[key].comp_select = "false";
			}
		
			
		document.getElementsByName("clinicalDocCompSelectJSON")[0].value=JSON.stringify(jsonSelect); 
		//alert(JSON.stringify(jsonSelect));
				
	} 
	
	
	
	
	
	//var ehrObj=null;
	//alert('hi');
	//showData(opener);
	//if(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0])
	//	ehrObj = JSON.parse(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0].value);
	//createAdviceonDischarge('ADVICEONDISCHARGE',ehrObj);
	
	
	function createAdviceonDischargeHTML()
	{
		
		//alert(jobj.patCompositionSelectData.ADVICEONDISCHARGE.list_selected);
		var obj=[];
		obj=jsonSelect.patCompositionSelectData.ADVICEONDISCHARGE.list_selected;
	//	alert(obj.length);
		if(obj.length>0)
		{
		//alert(obj.length);
		//alert(key);
		  var content = '<div id= "div_ADVICEONDISCHARGE">';
		
		 // alert(obj[0].drugName);
	    /*for(var k=0; k < obj.length;k++)
	    {
	    	
		   content += '<tr><td>' + obj[k].drugName+'&nbsp;&nbsp;'+ obj[k].frequencyName  + '&nbsp;X&nbsp;'  + obj[k].days +'Days &nbsp;&nbsp;' + obj[k].drugAdminName + '</td></tr><br>';
		  
		 }
		content += '</div>';   
	 
		//alert(content);
		//$('#tbAdviceOnDischarge').append(content);   
		
		//jsonSelect.patCompositionSelectData[ADVICEONDISCHARGE].html = content;
		jsonSelect.patCompositionSelectData.ADVICEONDISCHARGE.html = content;*/
		//alert(jsonSelect.patCompositionSelectData.ADVICEONDISCHARGE.html);
		}
	} 

	function createDiagnosisHTML()
	{
		var obj = [];
		obj = jsonSelect.patCompositionSelectData.ENCDIAGNOSIS.list_selected;
		if(obj.length>0)
			{
			 var content = '<div id= "div_ENCDiagnosis">';
			}
		}  
	  
	
	  

	
	</script>
	
	
	<%	HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);
		UserVO userVO=ControllerUTIL.getUserVO(request);
		String opdlocal=userVO.getUsrName();%>
	
	<div class="container" style="display:none">
	<div class="row" id = "header">
		<div class="col-xs-2 text-left">
			<img src="/../HIS/hisglobal/images/login/logo.png" alt="logo" height="100px;" />
		</div>
		<div class="col-xs-9 text-center">
			<div class="row" style="text-align:center;">
				<h4 ><font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif"><b> <%=hospitalVO.getHospitalName().toUpperCase() %> </b></font></h4>
				 <h5><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <%=hospitalVO.getAddress1() %> </font></h5>
				<h5><font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif"> <%=hospitalVO.getAddress2() %> </font></h5>
		 	</div>
		</div>
		<div class="col-xs-1 text-right">
		<div class="row" style="text-align:center;">
		<img  class="button pull-right" src="/HIS/hisglobal/images/buttons/btn-pnt.png"  style="cursor:pointer" tabindex="1"  onclick="printForms()" id="printPageButton">	
		
		</div>
		</div>
	</div>
	
	 <div id ="sections">
	 <div class="row" style="text-align:center;">
	<div class="col-sm-4"></div>
	<div class="row" style="text-align:center;">
		<h5><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><b> DISCHARGE SUMMARY </b></font></h5>
	</div>
		
	</div>

<div id="patTile" style="margin:5px;">

 	</div>
	
		
	<% 

List lstMenusForPrint = (List)session.getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST);
if(lstMenusForPrint!=null)
{
	String prevClinicalSectionCode="", newClinicalSectionCode="";
	for(int i=0;i<lstMenusForPrint.size();i++)
	{
		PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstMenusForPrint.get(i);
		String key = vo.getClinicalSecCompKey();
		newClinicalSectionCode= vo.getClinicalSectionCode();
		System.out.println(key);
		String url="/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_VIEW";
		System.out.println(url);
	%>
	
	<table id="tbClinicalDocument" >
	
	
	   <%   if(!newClinicalSectionCode.equalsIgnoreCase(prevClinicalSectionCode)) {  %>
		     <%  if( !key.equalsIgnoreCase("HISTORY") && !key.equalsIgnoreCase("DISCHARGESTATUS")  )
			{ %>
		  <br/>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b> <%=vo.getClinicalSectionName()%> </b></font>
			  
		       <jsp:include page="<%=url%>" flush="true" />
		      
		       <%}}
	   else{
		   %> 
		    <jsp:include page="<%=url%>" flush="true" />
		   <%
		   
	   } prevClinicalSectionCode= newClinicalSectionCode;
		    	 }}%>
		      

</table>
</div>
      <div id="footer">
         <br/>  	 
	 <div class="row"> 
	<div class="col-sm-6 text-left">
		<h6><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif">Discharged By:</font></h6>
	</div>
	<div class="col-sm-6 text-right">
		<h6><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif">Date:</font></h6>
	</div>
	 </div>
	 <div class="row"> 
	<div class="col-sm-6 text-left">
		<h6><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif">Signature:</font></h6>
	</div>
	 </div>
	 </div>
 	</div>

</form>
</body>
</html>