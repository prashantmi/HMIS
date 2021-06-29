<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="ehr.EHRConfig"%>
<%@page import="java.util.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="ehr.diagnosis.vo.EHRSection_DiagnosisVO"%>
<%@page import="ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO"%>
<%@page import="emr.vo.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


      <title>STATUS AT DISCHARGE</title>      
     <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
     <link href="/HIS/hisglobal/bbpublic/assets/elements/css/style.css" rel="stylesheet"> 
     <link href="/HIS/hisglobal/bbpublic/assets/css/elements.css" rel="stylesheet"> 
     <link href="/HIS/hisglobal/bbpublic/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!--  <link href="/HIS/hisglobal/bbpublic/assets/elements/css/combined.css" rel="stylesheet">  -->
       <link href="/HIS/hisglobal/bbpublic/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
           <script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>

       
  <!--     <script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script> -->
      
  

   <script src="/HIS/hisglobal/bbpublic/assets/elements/js/bootstrap.min.js"></script>
   
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
</style>
<%-- <%System.out.println("Vasu"); %>> --%>


<% 
EHRSection_StatusAtDischargeVO dischargeVO= (EHRSection_StatusAtDischargeVO) session.getAttribute(EHRConfig.STATUS_AT_DISCHARGE);//EHRSection_VisitReasonVO VisitVO= patencountervo.getListSaveAllVisitReason();
System.out.println(dischargeVO.getStatusAtDischarge());


%>

<%System.out.println("helllllllllll"); %>


<body style="margin:0%">
<form>

<table id="tbDischargeStatus">
	</table>
</form>
</body>

<script>
var ehrObj=null;
//alert('hi');
//showData(opener);
if(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0])
	ehrObj = JSON.parse(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0].value);
	//alert(ehrObj);
createStatusAtDischarge('',ehrObj);


function showData(obj)
{
	var h="";
	for(var d in obj)
	{
		h+=d+" = "+obj[d]+"\n";
		if(h.length>1000)
		{
			alert(h);
			h="";
		}
	}
	alert(h);
}



 function createStatusAtDischarge(key,jobj)
{
	//alert("inside createDiagnosis");
	//alert(jobj.patCompositionSelectData.ENCDIAGNOSIS.list_selected);
	var obj=[];
	var temp="<%=dischargeVO.getStatusAtDischarge()%>";
	//alert(temp);
	if(jobj.patCompositionSelectData.DISCHARGESTATUS.comp_select == "true")
	{
		obj=jobj.patCompositionSelectData.DISCHARGESTATUS.list_selected;
	 //alert(obj[0].statusAtDischarge);
	 //alert(key);
	  //var content = '<div id= div_DISCHARGESTATUS>';
	  var content = '';
	
	// alert(obj[0].statusAtDischarge);
     // content += '<tr><td>' + obj[k].diagnosticCode+'&nbsp;&nbsp;'+ obj[k].dignosisName  + '&nbsp;&nbsp;'  + obj[k].diagnosticTypeName+'&nbsp;&nbsp;' + obj[k].remarks + '</td></tr><br>';
	 //if(obj.length>0)
		 //{
    // if(obj[0].statusAtDischarge!=undefined) 
     //content += '<tr><td>' + obj[0].statusAtDischarge +'</td></tr><br>';
    	 content += '<tr><td width="100%"><div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">'+temp+'</font></div></td></tr>';
		 //}
    	 
	 
 
	//alert(content);
	$('#tbDischargeStatus').append(content);   
	
	window.opener.jsonSelect.patCompositionSelectData.DISCHARGESTATUS.html = content;
	//alert(window.opener.jsonSelect.patCompositionSelectData.ADVICEONDISCHARGE.html);
   }
	
}   
</script>


</html>