
<!-- 
/**
 * @Copyright :CDAC
 * @Developer :Vasu,Prachi
 */
-->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>

<%@page import="emr.vo.PatientClinicalDocDetailVO"%>

<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="ehr.EHRConfig"%>
<%@page import="emr.vo.PatientClinicalDocDetailVO"%>
<%@page import="hisglobal.vo.UserVO"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>One Page Prescription</title>   
  <link href="/HIS/hisglobal/bbpublic/assets/elements/css/font-awesome.css" rel="stylesheet">
  <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">

<!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css" rel="stylesheet"> -->

<!-- Font Awesome -->
	<link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/fontawesome/css/all.css">
    <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/fontawesome/css/fontawesome.min.css">
 <!--  <link href="/HIS/hisglobal/css/font-awesome.css" rel="stylesheet" type="text/css"/>
  <link href="/HIS/hisglobal/css/font-awesome.css" rel="stylesheet" type="text/css"/> -->


<script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>
<script src="/HIS/hisglobal/DoctorDesk/bootstrap/js/bootstrap.min.js"></script>  
<script type="text/javascript" src="EHR_unipage_prescription.js"></script>

	<link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/fontawesome/css/all.css">
    <link rel="stylesheet" href="/HIS/hisglobal/DoctorDesk/fontawesome/css/fontawesome.min.css">



<script type="text/javascript" src="/HISClinical/hisglobal/js/commonFunctions.js"></script>


<script>

function validateText(evnt)
{
	var flag = true;
	var charCode;
	var keyCode;
	//alert(window.event.keyCode);
	try
	{
		if (window.event)
			keyCode = window.event.keyCode;
		else if (evnt){
			keyCode = evnt.which;
			if(keyCode==37 || keyCode==39)
				flag=true;
		}
		else
			keyCode = null;

		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			(charCode==45) || 
			(charCode >= 48 && charCode <= 57) )
			flag=true;
		else
			flag=false;

		var val = String.fromCharCode(charCode);
		var pattern=/^[\sa-zA-Z0-9,./+-=_%&();:<>]*$/;//,./+-=_%&();:<>
		
		//	var pattern=/^[^~!@#$%^&]*$/;
		flag = pattern.test(val);
		//alert(flag);
	}
	catch(e)
	{
		//alert("Error Message -> "+e.message);
	}
	return flag;
}
function submitPdf()
{
	window.submitForm('HEADER');
}

function redirectRespectiveTab(deskMenuName,deskMenuURL,deskMenuId,e)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var episodeCode=document.getElementsByName("episodeCode")[0].value;
	var episodeVisitNo=document.getElementsByName("episodeVisitNo")[0].value;
	var departmentUnitCode=document.getElementsByName("departmentUnitCode")[0].value;
	var roomCode=document.getElementsByName("roomCode")[0].value;
	var callFromDesk=document.getElementsByName("callFromDesk")[0].value;
	url="/HISClinical/opd/genericPatientAlert.cnt&patCrNo="+patCrNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&departmentUnitCode="+departmentUnitCode+"&roomCode="+roomCode;
	//openPopup(createFHashAjaxQuery(url),e,400,600);
	parent.addTab(deskMenuName,deskMenuURL,deskMenuId,'1',false,"&patCrNo="+patCrNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&departmentUnitCode="+departmentUnitCode+"&roomCode="+roomCode+"&callFromDesk="+callFromDesk+"&isCallFromSinglePage="+"1",'1');
}
function printPage1(e)
{
	alert("SPP Print");
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

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> 
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/popup.js"/>
</head>
<style>
.table.table-borderless td, .table.table-borderless th {
    border: 0 !important;
}

.table.table-borderless {
    margin-bottom: 0px;
}

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
      #savemsg
      {
      	height:0px;
      }

     .textAlign
     {
     	text-align:left;
     }
     .panelHeadingPadding
     {
     	padding:1px;
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
<html:hidden name="UniPagePrescriptionFB" property="patCrNo"/>

<%List<String> listOfString = new ArrayList<String>(); %>
<div id= "mynav">
	<div  id ="SaveDiv" class="pull-right">
			<a class="btn btn-primary btn-sm"  id="saveId"><span class="glyphicon glyphicon-save"></span> Save</a>
	 		 <button type="button" class="btn btn-success btn-sm " id="generatebtn"  style=" display:none;" tabindex="1"  onclick="printPage(event)"><span class="glyphicon glyphicon-print"></span> Generate & Print</button>
	 		
	 	</div>
	 	<div class="pull-right" >
	 	<a href="#" class="btn btn-danger btn-sm" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
          <span class="glyphicon glyphicon-refresh"></span> Refresh
        </a>
	 	<!-- <button type="button" class="btn btn-danger btn-sm"  onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">Clear</button> -->
	 		 	</div>
</div>
<html:hidden name="UniPagePrescriptionFB" property="documentType" />
<html:hidden name="UniPagePrescriptionFB" property="hmode" />
<html:hidden name="UniPagePrescriptionFB" property="patCrNo" />

<html:hidden name="UniPagePrescriptionFB" property="episodeCode" />
<html:hidden name="UniPagePrescriptionFB" property="episodeVisitNo" />
<html:hidden name="UniPagePrescriptionFB" property="departmentUnitCode" />
<html:hidden name="UniPagePrescriptionFB" property="roomCode" />


	
 <div id="savemsg"  style="display:none;">
<h5 style="color:red">Data Saved Successfully! </h5>

</div>

 <div class="modal-dialog" id="divDrescription" style="margin-top:30px;">
 	<div class="modal-content">
 	
	<div class="modal-body" style="background-color: rgb(234, 238, 243);">

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
			
			//New Mapping 
			selecturl= "/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_SELECT";
			//End
			
			
			newClinicalSectionCode= vo.getClinicalSectionName();
			System.out.println(newClinicalSectionCode);
			listOfString.add(key);
	%>
	 <div class="panel panel-default">
	
	<%   if(newClinicalSectionCode!=null && !newClinicalSectionCode.equalsIgnoreCase("")) {  %>
   
			 <%  if(vo.getClinicalSectionName().equalsIgnoreCase("Patient Detail")) { %>
			<div id="divselect<%=key%>" style="background-color: rgb(234, 238, 243);" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingselect<%=key%>">
        <div  >
        
			<jsp:include page="<%=selecturl%>" flush="true"  />
			
		</div>   </div>		<%} 
	else
	{%>
	      
		<div id="divselect<%=key%>" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingselect<%=key%>">
        
      <div class="panel-body" style="padding-left:20px;padding-right:10px;padding-top:8px;padding-bottom:8px;">
       
			<jsp:include page="<%=selecturl%>" flush="true"  />
			
		</div>   </div>	
	<%} %>
	<%} %>

		       
        
		
			</div>
		
		

	
<%}} %></div>
 	
 	
 	
 	
 	
 	
 	
 	
 	</div></div>
</div>



 <div id="savemsg" style="display:none;">
<h4 style="color:red">Data saved successfully </h4>

</div> 


<div id="notsavemsg" style="display:none;">
<h4 style="color:red">Data not saved </h4>

</div>

<div id='ajax_loader' style="position: fixed; left: 50%; top: 50%; display: none;">
     <!-- <img src="https://www.drupal.org/files/issues/ajax-loader.gif"></img> -->
     <img src="/HISClinical/hisglobal/images/ajax-loader.gif"></img>  
</div>

<script>
$(document).ajaxStop(function(){
    console.debug("ajaxStop");
    $("#ajax_loader").hide();
 });
 $(document).ajaxStart(function(){
     console.debug("ajaxStart");
     $("#ajax_loader").show();
 });
 
/**Modified by Vasu on 2.Apr.2019 to ensure complete validation before AJAX fire Save Function for all Sections*/
$('#saveId').click(function(e) {
	var validationStatus = [];
	<%for(int i=0;i<listOfString.size();i++)
	{ 
	String value = listOfString.get(i).toString();
	%>  
	
	var a = validateClinicalSections('<%=value%>');
	//alert(a);
	validationStatus.push(a);
	
	<%}%>
	//alert(myArray);
	//alert(myArray.length);
	var numOfFalse = 0;
	 for(var i=0;i<validationStatus.length;i++){
		 //alert(myArray[i])
	    if(validationStatus[i])
		    {
		    //alert("true");
	    	//numOfFalse++;
		    }
	    else
		    { 
	    	 //alert("false");
	    	 numOfFalse++;
		    }
	} 
	//alert(numOfFalse);
	if(numOfFalse == 0)
		{
		document.getElementById("saveId").style.display="none";
	<%for(int i=0;i<listOfString.size();i++)
	{
	String value = listOfString.get(i).toString();
	%>  
	
	 saveTemplateDataThroughAjax('<%=value%>');
	 
	<%}%>
	saveEHRFlag();
		}
});	

/**Added by Vasu on 03.Dec.2018 to save data through AJAX*/
function saveTemplateDataThroughAjax(key)
{
	//alert(key);
	var form = $('#commonTransactionLayoutFormId');
	var form1 = $('#drugTreatmentGiven');
	
	//alert(form1.serialize());
	 /* var a = validateClinicalSections(key);
	 //alert(a);
	if(a == "false")
		{
		alert("validation failed");
		   return;
		}  */
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
			
		var docType = document.getElementsByName("documentType")[0].value;
        //alert(docType);
      if(key == "ENC_DIAGNOSIS")
          {
             //alert("refresh diagnosis");
    	     clearDiagnosis();
          }
      if(docType=="51" && key == "ENC_TREATMENT")
          {
    	  //alert("refresh treatment");
    	     clearTreatment();
          }
     /*  if(key=="ENC_MED_ADV")
          {
          //alert("refresh advice on discharge");
    	     clearTreatment();
          }  */
}

//Added by Vasu on 11.Feb.2019 to Validate Clinical Sections
function validateClinicalSections(key)
{
	//alert(key);
	 var validateFlag = "";
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
	return validateFlag 
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


//Added by Vasu on 01.July.2019 to refresh each clinical sections after save

function refreshDataThroughAjax(key)
{
	 //alert(key);
	var form = $('#commonTransactionLayoutFormId');

	$.ajax({
		   url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_SELECT"),  
		    type : 'POST',
		    data : form.serialize(), 
		    async: false,
			success: function(data) {
				   	if(data=="true")
		    		{
		            //alert("Data saved successfully");
		    		//saveFlag=1;
		    	    }
		    	    else
		    	    	{
		    	    	//saveFlag=0;
		    	    	}
		      },
		      error: function(data)
		      {
		    	    //alert('request failed :');
		    	}

		
		});

}
/* function validate_PAT_DTL_DEMO()
{
	alert("ff")
	} */

	$('#saveId1').click(function(e) {
		var saveFlag;
		var form = $('#commonTransactionLayoutFormId');
		//alert($('#commonTransactionLayoutFormId').serialize());
		var base64FormData = window.btoa($('#commonTransactionLayoutFormId').serialize());
		//alert(base64FormData);
		
		// setfreeTextVR();
		
		
		 // ********* VISITREASON SAVE ***********//
		/* $.ajax({
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
			
			});  */
		

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
		  url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_ENC_DIAGNOSIS.cnt?hmode=SAVE"),
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

	
			


		// ********* TREATMENT SAVE ***********//
		/*$.ajax({
			    url: createFHashAjaxQuery('/HISClinical/emr/ehrComposition_DESK_TREATMENT.cnt?hmode=SAVE'),//&base64FormData='+base64FormData),
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
			
			});*/

		//***********ADVICE ON DISCHARGE SAVE*************//
		$.ajax({
			    url: createFHashAjaxQuery('/HISClinical/emr/ehrComposition_ENC_ADVICEONDISCHARGE.cnt?hmode=ADVICEONDISCHARGE_SAVE'),
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

		// ********* FOLLOW UP SAVE ***********//
		/* setfreeTextFU();
		$.ajax({
			    url: createFHashAjaxQuery('/HISClinical/emr/ehrComposition_FOLLOWUP_ADVICE.cnt?hmode=SAVE'),
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
			
			}); */

			
		  
		//saveEHRFlag();
	});
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
			  url: createFHashAjaxQuery("/HISClinical/emr/ehrSection_EXAMINATION.cnt?hmode=SAVE"),
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
		        //clearDiagnosis();
		        //clearTreatment();
				document.getElementById("savemsg").style.display="block";
				document.getElementById("notsavemsg").style.display="none";
				document.getElementById("saveId").style.display="none";
				document.getElementById('savemsg').scrollIntoView()
				
			/* 	if(document.getElementsByName("documentType")[0].value=="17")	
       {		 */
			document.getElementById("generatebtn").style.display="block";
     /*   } */
				
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
    	AddRowToTableDiag();
    	//document.getElementById("commonTransactionLayoutFormId").reset();
    	var row=$("#diagnosisTable tr").length;
    	//alert(row);
    	if(row>2)
    		{
    	for(var i=1;i<row-1;i++)
    	{
    	if(document.getElementsByName("diagnosisRecordStatus")[i].value=="2" || document.getElementsByName("diagnosisRecordStatus")[i].value=="4")
    	document.getElementsByName("diagnosisRecordStatus")[i].value="1";
    	}
     		}
		
	}
	
	function printPage(e)
	{
		var documentTypeDtl = document.getElementsByName("documentType")[0].value;
		//alert(documentTypeDtl);
		var crNo = document.getElementsByName("patCrNo")[0].value;
		var url = "/HISClinical/emr/uniPagePrescription.cnt?hmode=PREVIEWNGEN&patCrNo="+crNo+"&documentType="+documentTypeDtl;
		openPopup(createFHashAjaxQuery(url),e,400,600);
		
		}

	
	</script>



</form>
</body>
</html>