<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
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
      <title>OPD Prescription</title>      
    <!--  <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
     <link href="/HIS/hisglobal/bbpublic/assets/elements/css/style.css" rel="stylesheet"> 
     <link href="/HIS/hisglobal/bbpublic/assets/css/elements.css" rel="stylesheet"> 
     <link href="/HIS/hisglobal/bbpublic/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    
       <link href="/HIS/hisglobal/bbpublic/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
           <script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>
 -->
       
      <his:javascript src="/ehr/js/EHR_spp_diagnosis.js" />
  

   
 </head>
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
</style>
<%List<String> listOfString = new ArrayList<String>(); %>
<% HospitalMstVO hospVO = (HospitalMstVO)session.getAttribute(EHRConfig.HOSPITAL_DEATILS); %>
<% EHRSection_FollowupVO VisitVO = (EHRSection_FollowupVO)session.getAttribute(EHRConfig.FOLLOWUP_ESSENTAILS); %>
<%HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);
UserVO userVO=ControllerUTIL.getUserVO(request);

String hospCode = hospitalVO.getHospitalCode();
String imgPath = "/HIS/hisglobal/images/aiims_header_"+hospCode+".png";

String opdlocal=userVO.getUsrName(); %>
<body style="margin:0%">
<div class="pull-right ">
	<!-- <img  class="button" src="/../HIS/hisglobal/images/buttons/btn-sv.png"  style="cursor:pointer" id="previewBtn" tabindex="1" onclick = "submitData()" > -->
	<img  class="button " src="/HIS/hisglobal/images/buttons/btn-pnt.png"  style="cursor:pointer" tabindex="1"  onclick="printPage(event)">
</div>



<form>

<input type="hidden" name="xmlInFile" value="<test></test>">
<input type="hidden" name="xmlOutFile" value="">


 <input type="hidden" id="fd" name="formData" value="">


<input type="hidden" name="pdfSignRec" value="2^800^500^750^800">
<input type="hidden" name="dsTypeVal" value="pdf">

<html:hidden name="UniPagePrescriptionFB" property="htmlPreview"/>
<html:hidden name="UniPagePrescriptionFB" property="pdfFileIn"/>
<html:hidden name="UniPagePrescriptionFB" property="pdfFileOut" value=""/>


<!-- <div class="pull-right">

			 <object
        classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"
        codebase="http://java.sun.com/products/plugin/autodl/jinstall-1_4-windows-i586.cab#Version=1,4,0,0"
        width="130" height="25" name="DsApplet">
        <param name="type" value="application/x-java-applet;version=1.4">
        <param name="code" value="DsApplet">
        <param name="codebase" value="/HISClinical/emr">
        <param name="archive" value="DigitalSignerApplet.jar, itext-1.3.1.jar">
        <param name="mayscript" value="true">
        <param name="scriptable" value="true">
        <param name="dsTypeField" value="dsTypeVal">
        <param name="xmlInFileField" value="xmlInFile">
        <param name="xmlOutFileField" value="xmlOutFile">
        <param name="pdfInFileField" value="pdfFileIn">
        <param name="pdfOutFileField" value="pdfSignRec">
        <param name="pdfSignField" value="dsTypeVal">
        <param name="signButtonCaption" value="Digital Sign">
        
        <param name="aliaseName" value="aliaseNameVal">
		   <param name="certificateValidFrom" value="certificateValidFromVal">
		   <param name="certificateValidTo" value="certificateValidToVal">
		   <param name="certificateIssuedBy" value="certificateIssuedByVal">
		   <param name="certificateSerialNo" value="certificateSerialNoVal">

        <comment>
            <embed
                type="application/x-java-applet;version=1.4"
                pluginspage="http://java.sun.com/products/plugin/index.html#download"
                code="DsApplet"
                codebase="/HISClinical/emr/" 
                archive="DigitalSignerApplet.jar, itext-1.3.1.jar"
                width="130"
                height="25"
                mayscript="true"
                scriptable="true"
                dsTypeField="dsTypeVal"
                 xmlInFileField="xmlInFile"
                  xmlOutFileField="xmlOutFile"
                   pdfInFileField="pdfFileIn"
                    pdfOutFileField="pdfFileOut"
                    pdfSignField="pdfSignRec"
                signButtonCaption="Digital Sign" 
                aliaseName="aliaseNameVal"
                certificateValidFrom="certificateValidFromVal"
                certificateValidTo="certificateValidToVal"
                certificateIssuedBy="certificateIssuedByVal"
                certificateSerialNo="certificateSerialNoVal">
            <noembed>
                Document signing applet can not be started because 
                Java Plug-In version 1.4 or later is not installed.
            </noembed>
        </embed>
        </comment>
    </object>
</div>
 -->

<div class="container" id="patientProfile">
		<div class="row">
			<div class="col-sm-4">
			</div>
			<div class="col-sm-5" style="text-align:center;">
				<!--  <img src='/HISClinical/hisglobal/images/aiims_header.png' height='100px'/>  -->
				 <img src="<%=imgPath%>" height='100px'/> 
 			</div>
		</div>
		
	<%--<div class="row">
		<div class="col-xs-4">
			<img src="/../HIS/hisglobal/images/login/logo2.png" alt="logo" height="100px;" />
		</div>
		<div class="col-xs-5">
			<div class="row" style="text-align:center;">
				<h4 ><font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif"><b> <%=hospitalVO.getHospitalName().toUpperCase() %> </b></font></h4>
				 <h5><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <%=hospitalVO.getAddress1() %> </font></h5>
				<h5><font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif"> <%=hospitalVO.getAddress2() %> </font></h5>
			</div>
		</div> --%>
	
		<div class="row">
			<div class="col-sm-4">
			</div>
			<div class="col-sm-5" style="text-align:center;">
				<h4><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><b>OPD Prescription</b></font></h4>
 			</div>
		</div>
	
		<div id="PatDetails">	
			<jsp:include page="/ehr/patientTile/EHRSection_PATDETAILS_PRINT.jsp" flush="true" />
		</div>
		
<%-- <%
			
		String prevClinicalSectionCode="", newClinicalSectionCode="";
		for(int i=0;i<lstMenus.size();i++)
		{
			String  path="";
			PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstMenus.get(i);
			String key = vo.getClinicalSecCompKey();
			vo.getDocumentTitle();
			newClinicalSectionCode= vo.getClinicalSectionCode();
			 if(!newClinicalSectionCode.equalsIgnoreCase(prevClinicalSectionCode)) { 
			 %>
			<div id="div_<%=key%>">	
			<%path=key;
			if(key.equalsIgnoreCase("DESKTREATMENT"))
				{key="TREATMENT"; path=key;}
			if(key.equalsIgnoreCase("DESKDIAGNOSIS"))
				{key="DIAGNOSIS";path=key;}
			if(key.equalsIgnoreCase("OPDNEXTVISITDETAIL"))
				{path="visitreason";}
			
			String url="/ehr/"+path.toLowerCase()+"/EHRSection_"+key+"_PRINT.jsp";
			%>
			<h5></h5>
			<jsp:include page="<%=url%>" flush="true" />
		</div>	 
		
		
		<% 	
	    }
			 newClinicalSectionCode= vo.getClinicalSectionCode();
			listOfString.add(key);
		}}
	%> --%>
 <%-- <div id="PatDetails">	
	<jsp:include page="/ehr/patientTile/EHRSection_PATDETAILS_PRINT.jsp" flush="true" />
</div>	 --%>

<h5></h5>
 <div id="visitDetails">	
	<jsp:include page="/ehr/visitreason/EHRSection_OPDNEXTVISITDETAIL_PRINT.jsp" flush="true" />
</div>   

<h5></h5>
<div id="diagnosis">	
	<jsp:include page="/ehr/diagnosis/EHRSection_DIAGNOSIS_PRINT.jsp" flush="true" />
</div>


 <%-- <h5></h5>
<div id="complaints">	
	<jsp:include page="/ehr/complaints/EHRSection_COMPLAINTS_PRINT.jsp" flush="true" />
</div> 

<h5></h5>
<div id="history">	
	<jsp:include page="/ehr/history/EHRSection_HISTORY_PRINT.jsp" flush="true" />
</div>

<h5></h5>
<div id="examination">	
	<jsp:include page="/ehr/examination/EHRSection_EXAMINATION_PRINT.jsp" flush="true" />
</div> --%>


<h5></h5>
<div id="treatment">	
	<jsp:include page="/ehr/treatment/EHRSection_TREATMENT_PRINT.jsp" flush="true" />
</div>
<h5></h5>
<div id="followup">	
	<jsp:include page="/ehr/followup/EHRSection_FOLLOWUP_PRINT.jsp" flush="true" />
</div> 
<h5></h5>

<div id="examination">	
	<jsp:include page="/ehr/examination/EHRSection_EXAMINATION_PRINT.jsp" flush="true" />
</div>
<h5></h5>

<div class="row">
	<div class="pull-left">
		<h6><font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif"><b> Sign of Consultant </b></font></h6>
	</div>
</div>
<div class="row">
	<div class="pull-right">
		
	</div>
</div>
<h5></h5>
<h5></h5>
<br/>
<div class="row">
	<div class="pull-left">
		<h6><font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif"><b> (<%=VisitVO.getRequestByName() %>) </b></font></h6>
	</div>
</div>


</div>





</form>

</body>
<script>

function submitData()
{

	 var signed = document.forms[0].pdfFileOut.value; 
	
	if(signed)
		{
		window.opener.document.getElementsByName("htmlPreview")[0].value = document.getElementsByName("htmlPreview")[0].value;
		window.opener.document.getElementsByName("pdfFileIn")[0].value = document.getElementsByName("pdfFileIn")[0].value;
		window.opener.document.getElementsByName("pdfFileOut")[0].value = document.getElementsByName("pdfFileOut")[0].value;

		window.opener.submitPdf()
		//if(window.opener.submitPdf())
			//{
				window.close();
			//}
		
		
			//alert(signed);
		//document.getElementById("saveBtn").style.display="block";
		}
	else
		{
			alert("Digital Signature Required to save the prescription !");
		}
	
		
}


function printPage(e)
{
	window.print();
}	

</script>
</html>