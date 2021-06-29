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
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<his:javascript src="/ehr/js/EHR_spp_diagnosis.js" />
<script src="/HIS/hisglobal/bbpublic/assets/elements/js/bootstrap.min.js"></script>


 </head>
 

<script type="text/javascript">
function generateDocument()
{	alert( document.getElementById('documentHtml').innerHTML);

	opener.document.getElementsByName('documentHTML')[0].value = document.getElementById('documentHtml').innerHTML;
	opener.submitForm('GENRATEDOCUMENT');
	window.close();
}
</script>
 

<%		HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);
		UserVO userVO=ControllerUTIL.getUserVO(request);
		String opdlocal=userVO.getUsrName();%>

 <%-- <% EHRSection_FollowupVO VisitVO = (EHRSection_FollowupVO)session.getAttribute(EHRConfig.FOLLOWUP_ESSENTAILS); %> --%>
 
<body style="margin:5%">
<div id="documentHtml">

<div class="container">
	<div class="row">
		<div class="col-xs-2 text-left">
			<img src="/../HIS/hisglobal/images/login/logo.png" alt="logo" height="100px;" />
		</div>
		<div class="col-xs-9 text-center">
			<div class="row" style="text-align:center;">
				<h4><font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif"><b> <%=hospitalVO.getHospitalName().toUpperCase() %> </b></font></h4>
				 <h5><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"> <%=hospitalVO.getAddress1() %> </font></h5>
				<h5><font color="#000000"  face="Verdana, Arial, Helvetica, sans-serif"> <%=hospitalVO.getAddress2() %> </font></h5>
		 	</div>
		</div>
		<div class="col-xs-1 text-right">
		<div class="row" style="text-align:center;">
		<!-- <img  class="button pull-right" src="/HIS/hisglobal/images/buttons/btn-pnt.png"  style="cursor:pointer" tabindex="1"  onclick="printForms()" id="printPageButton">	
		 -->
			<!-- <a style="cursor:pointer" onclick="printForms();" >
				<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					PRINT
				</font>
			</a> --></div>
		</div>
	</div>
	<%
 
List lstMenus = (List)session.getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST_HTML);
if(lstMenus!=null)
{
	PatientClinicalDocDetailVO doctypevo  = (PatientClinicalDocDetailVO)lstMenus.get(0);
	%>
	 <div class="row" style="text-align:center;">
	<div class="col-sm-12"></div>
		<div class="col-sm-12">
			<h4><font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><b> <%=doctypevo.getDocumentTitle().toUpperCase() %>  </b></font></h4>
		</div>
		
		<!-- <table>
    <tr style="border-bottom:10px solid black"><td colspan="100%"></td></tr>
   
</table> -->
	</div>
	
	<hr class="half-rule">

<%-- <div id="patTile" style="margin:5px;">
 		<!-- <fieldset class="scheduler-border">
    		<legend class="scheduler-border">Patient Tile</legend> -->
    		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" /> 
    		
   		<!-- </fieldset> -->
 	</div> --%>
	
	</div>


	
<%
	String prevClinicalSectionCode="", newClinicalSectionCode="";
	for(int i=0;i<lstMenus.size();i++)
	{
		PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstMenus.get(i);
		String key = vo.getClinicalSecCompKey();
		newClinicalSectionCode= vo.getClinicalSectionCode();
		System.out.println(key);
		//String url="/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_VIEW";
	%>
	
		   <%   if(!newClinicalSectionCode.equalsIgnoreCase(prevClinicalSectionCode)) {  %>
		<br> <font color="#000000" face="Verdana, Arial, Helvetica, sans-serif"><b>  <%=vo.getClinicalSectionName()%> </b></font>
		 <%System.out.println( vo.getClinicalDocCompHTMLJSON()); %>
		 
		 <table width="100%" cellpadding="0px" cellspacing="0px" style="margin:0%">
			 <%=vo.getClinicalDocCompHTMLJSON().substring(1, vo.getClinicalDocCompHTMLJSON().length()-1)%></table>
		      <%}
		   else{
			   %> 
			    <table width="50%" cellpadding="0px" cellspacing="0px" style="margin:0%">
			 <%=vo.getClinicalDocCompHTMLJSON().substring(1, vo.getClinicalDocCompHTMLJSON().length()-1)%> </table>
		        <% }prevClinicalSectionCode= newClinicalSectionCode;
		    	 }}%>



</div>
	
<br><br><br>
<center>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-generate.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) generateDocument();" onclick="generateDocument();" tabindex="1" />
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) window.close();" onclick="window.close();" tabindex="1" />
</center>
</body>
</html>


<%}catch(Exception e){e.printStackTrace();}%>
