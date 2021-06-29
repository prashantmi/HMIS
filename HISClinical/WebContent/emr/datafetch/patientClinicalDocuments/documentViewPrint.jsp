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
<his:javascript src="/registration/js/popup.js"/>
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

<script type="text/javascript">
function printProfile(e)
{
	var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=NONE";
	openPopup(url,e,700,700);
}


function printForms()
{
	window.print();
}

</script>
 

<body style="margin:5%">
<his:TitleTag key="patientclinicalDocument">
		<div align="right">
			<a style="cursor:pointer" onclick="printProfile(event);" >
				<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					PRINT
				</font>
			</a>
		</div>
	</his:TitleTag>
	<!-- <div align="right">
	<img  class="button pull-right" src="/HIS/hisglobal/images/buttons/btn-pnt.png"  style="cursor:pointer" tabindex="1"  onclick="printForms()" id="printPageButton">	
	</div> -->
	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	<bean:write name="PatientClinicalDocumentsFB" property="documentHTML" filter="false"/>
	</font>
<br><br><br>
<center>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) window.close();" onclick="window.close();" />
</center>
</body>
</html>



<%}catch(Exception e){e.printStackTrace();}%>
