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
<his:javascript src="/ehr/js/EHR_spp_diagnosis.js" />
<script src="/HIS/hisglobal/bbpublic/assets/elements/js/bootstrap.min.js"></script>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>


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
</style>



 </head>
 

<%		HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);
		UserVO userVO=ControllerUTIL.getUserVO(request);
		String opdlocal=userVO.getUsrName();%>

 <%-- <% EHRSection_FollowupVO VisitVO = (EHRSection_FollowupVO)session.getAttribute(EHRConfig.FOLLOWUP_ESSENTAILS); %> --%>
 
<body style="margin:0%">
<div id="documentHtml">
<form id="formOPP" action="/patientClinicalDocuments" method="POST">

		<html:hidden name="PatientClinicalDocumentsFB" property="hmode" />
		<html:hidden name="PatientClinicalDocumentsFB" property="htmlDocHeader" />


</form></div>
</body>


<script>
 
function printForms()
{
	window.print();
}

window.onload = function()
{
	alert("loading");
var headerContent = window.opener.document.getElementsByName("htmlDocHeader")[0].value;
alert(headerContent);
//document.getElementsByName('htmlCode')[0].value=opener.document.getElementById('pdfPrintingHTMLData').innerHTML;
//var htmlHeaderData = document.getElementsByName('htmlCode')[0].value;
var encHeader = window.btoa(headerContent);
document.getElementsByName("htmlDocHeader")[0].value = headerContent;
//  removeAllEvenetsForSecurity(document.getElementsByName('htmlCode')[0].value);
document.getElementsByName('hmode')[0].value="PREVIEWPDF";
//document.forms[0].action = "/HISClinical/emr/patientClinicalDocuments.cnt?hmode=PREVIEWPDF";
document.forms[0].submit();
}

//opener.document.getElementsByName('documentHTML')[0].value = document.getElementById('documentHtml').innerHTML;



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
