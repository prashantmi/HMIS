<%try{ %>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>

<%@page import="ehr.EHRConfig"%>
<%@page import="ehr.*"%>
<%@page import="emr.*"%>
<%@page import="emr.datafetch.patientClinicalDocuments.presentation.fb.PatientClinicalDocumentsFB"%>
<%@page import="emr.vo.PatientClinicalDocDetailVO"%>

<%@page import="hisglobal.vo.UserVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.vo.HospitalMstVO"%>


<%-- 
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonElement"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonParser"%> --%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="profileHtml">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Discharge Summary</title>      
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/style.css" rel="stylesheet"> 
<link href="/HIS/hisglobal/bbpublic/assets/css/elements.css" rel="stylesheet"> 
<link href="/HIS/hisglobal/bbpublic/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!--  <link href="/HIS/hisglobal/bbpublic/assets/elements/css/combined.css" rel="stylesheet">  -->
<link href="/HIS/hisglobal/bbpublic/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<his:javascript src="/ehr/js/EHR_spp_diagnosis.js" />
<script src="/HIS/hisglobal/bbpublic/assets/elements/js/bootstrap.min.js"></script>
<!--  <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.4.1/jspdf.debug.js"></script> -->
  <style>
    @CHARSET "UTF-8";
    .page-break {
      page-break-after: always;
      page-break-inside: avoid;
      clear:both;
    }
    .page-break-before {
      page-break-before: always;
      page-break-inside: avoid;
      clear:both;
    }
    #html-2-pdfwrapper{
      position: absolute; 
      left: 20px; 
      top: 50px; 
      bottom: 0; 
      overflow: auto; 
      width: 600px;
    }
  </style>

<style>

fieldset.scheduler-border {
    border: 1px groove black !important;
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
    @media print {
  #printPageButton {
    display: none;
  }
  #PatDetails
  {
  font-family:ARIAL;
  }
}


.half-rule { 
    margin-left: 0;
    text-align: left;
    width: 100%;
   
 }
 
 .table-condensed tr td, .table-condensed tr th{
      border-width:0 !important;
    }
</style>

<script>
var base64Img = null;
margins = {
  top: 70,
  bottom: 40,
  left: 30,
  width: 550
}

function generate()
{  /* alert("generate calling"); */
  var pdf = new jsPDF('p', 'pt', 'a4');
  /* pdf.setFontSize(18); */
  /* alert(document.getElementById('cont1')); */
  pdf.addHTML(document.body,function() {
	  var object = document.createElement('object');
	  object.setAttribute('style','height:90vh; width:100%; padding:20px;');
	  document.body.insertBefore(object,document.body.firstChild);
	  object.data = pdf.output('datauristring'); 
	 });
/*   pdf.fromHTML(document.getElementById('cont1'), 
    margins.left, // x coord
    margins.top,
    {
      // y coord
      width: margins.width// max width of content on PDF
    },function(dispose) {
       alert("header calling");
      headerFooterFormatting(pdf);  
    }, 
    margins);
 
  var object = document.createElement('object');
  object.setAttribute('style','height:60vh; width:100%; padding:20px;');
  document.body.appendChild(object);
  
  object.data = pdf.output('datauristring'); */
}

function headerFooterFormatting(doc)
{
    var totalPages  = doc.internal.getNumberOfPages();

    for(var i = totalPages; i >= 1; i--)
    { //make this page, the current page we are currently working on.
        doc.setPage(i);      
         alert("loop for pages"+i);
        header(doc,i);
        
      /*  footer(doc, i, totalPages);*/
        
    }
}

function header(doc,i)
{
    doc.setFontSize(30);
    doc.setTextColor(40);
    doc.setFontStyle('normal');
  
 /*    if (base64Img) {
       doc.addImage(base64Img, 'JPEG', margins.left, 10, 40,40);        
    } */
      
    doc.text("Report Header Template "+i, margins.left + 50, 40 );
  
    doc.line(3, 70, margins.width + 43,70); // horizontal line
}
 

</script>
<script>
	$(document).ready(function(){
		
		$('table:odd').addClass('table');
		$('table:odd').addClass('table-condensed');
	});
</script>
</head>
 

<%		HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);
		UserVO userVO=ControllerUTIL.getUserVO(request);
		String opdlocal=userVO.getUsrName();%>

 <%-- <% EHRSection_FollowupVO VisitVO = (EHRSection_FollowupVO)session.getAttribute(EHRConfig.FOLLOWUP_ESSENTAILS); %> --%>
 
<body onload="" style="margin:0%">
<div id="documentHtml">
<form id="formOPP" action="/patientClinicalDocuments" method="POST">

 <button class="btn btn-info" type="button" onclick="generate()">Generate PDF</button>

<div class="container">
	<div class="row">
		<div class="col-xs-2 text-left">
		  <img src="/../HIS/hisglobal/images/login/logo.png" alt="logo" height="100px;" />  
		</div>
		<div class="col-xs-8 text-center">
			<div class="row" style="text-align:center;">
				<h4 ><font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif"><b> <%=hospitalVO.getHospitalName().toUpperCase() %> </b></font></h4>
				 <h5><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <%=hospitalVO.getAddress1() %> </font></h5>
				<h5><font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif"> <%=hospitalVO.getAddress2() %> </font></h5>
		 	</div>
		</div>
		<div class="col-xs-2 text-right">
		<div class="row" style="text-align:center;">
		<img  class="button pull-right" src="/HIS/hisglobal/images/buttons/btn-pnt.png"  style="cursor:pointer" tabindex="1"  onclick="printForms()" id="printPageButton">	
		
			<!-- <a style="cursor:pointer" onclick="printForms();" >
				<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					PRINT
				</font>
			</a> --></div>
		</div>
	</div>
	
	 <div class="row" style="text-align:center;">
	<div class="col-sm-4"></div>
	<div class="row" style="text-align:center;">
		<h5><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><b> DISCHARGE SUMMARY </b></font></h5>
	</div>
		
		<!-- <table>
    <tr style="border-bottom:10px solid black"><td colspan="100%"></td></tr>
   
</table> -->
	</div>

<div id="patTile" style="margin:5px;">
 		<!-- <fieldset class="scheduler-border">
    		<legend class="scheduler-border">Patient Tile</legend> -->
    	<%-- 	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />  --%>
    		
   		<!-- </fieldset> -->
 	</div>
	
		
	<% 

List lstMenus = (List)session.getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST);
if(lstMenus!=null)
{
	String prevClinicalSectionCode="", newClinicalSectionCode="";
	for(int i=0;i<lstMenus.size();i++)
	{
		PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstMenus.get(i);
		String key = vo.getClinicalSecCompKey();
		newClinicalSectionCode= vo.getClinicalSectionCode();
		System.out.println(key);
		String url = "";
		/* if(key.equalsIgnoreCase("DESKTREATMENT"))
		{ 
		  url= "/emr/ehrComposition_ADVICEONDISCHARGE.cnt?hmode=PATCLINICALDOC_ADVICEONDISCHARGE_VIEW";
		}
		else */ if(key.equalsIgnoreCase("DESKTREATMENT")||key.equalsIgnoreCase("HOSPITALCOURSE")||key.equalsIgnoreCase("DOCUMENTFOOTER"))
		{
			url = "";
		}
		else
		{
		 url="/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_VIEW";
		}
		System.out.println(url);
	%>
	
	<table id="tbClinicalDocument" >
	
	
	   <%   if(!newClinicalSectionCode.equalsIgnoreCase(prevClinicalSectionCode)) {  %>
		      <%--   <div class="panel-heading"   data-toggle="collapse" data-target=".comp_collapse<%=key%>" style="padding:1px;"> --%>
	
		 <!--  <div>  div_'+key+';
		       </div>  -->  
		     <%  if( !key.equalsIgnoreCase("HISTORY") && !key.equalsIgnoreCase("")  )
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
 <br/>  	 
	 <div id="footer">
	 <div class="row" style="">
	   <div class="col-sm-6 text-left">
		<h6><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><b> Discharge Prepared By: </b></font></h6>
		</div>
	</div>
	 <div class="row"> 
	<div class="col-sm-6 text-left">
		<h6><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><b>SIGNATURE OF PRESIDENT</b></font></h6>
	</div>
	<div class="col-sm-6 text-right">
		<h6><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><b>SIGNATURE OF HEAD OF UNIT</b></font></h6>
	</div>
	 </div>
	 <!-- <div class="row"> 
	<div class="col-sm-6 text-left">
		<h6><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif">Signature:</font></h6>
	</div>
	 </div> -->
	 </div>
	 
	<!-- </div> -->
</div>

</form></div>
</body>

 
<script>
 
function printForms()
{
	window.print();
}

window.opener.document.getElementsByName("flagPreview")[0].value=0;
opener.document.getElementsByName('documentHTML')[0].value = document.getElementById('documentHtml').innerHTML;



/* function generateProfile()
{	
	opener.document.getElementsByName('profileHTML')[0].value = document.getElementById('profileHtml').innerHTML;
	//opener.document.getElementsByName('hmode')[0].value = "GENRATEPROFILE";
	//opener.document.forms[0].hmode.value = "GENRATEPROFILE";
	//opener.submit();
	opener.submitForm('PROFILEGENERATION');
	
	window.close();
} */


</script>

</html><%}catch(Exception e){e.printStackTrace();}%>
