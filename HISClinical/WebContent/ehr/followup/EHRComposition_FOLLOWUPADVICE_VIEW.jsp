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

<%System.out.println("helllllllllll"); %>


<body style="margin:0%">
<form>

<table id="tbFollowUpAdvice" style="font-family:Verdana, Arial, Helvetica, sans-serif; color=#000000; font-size:1;">
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
 createFollowUpAdvice('',ehrObj);



 function createFollowUpAdvice(key,jobj)
 {
	var obj=[];
	
	if(jobj.patCompositionSelectData.FOLLOWUPADVICE.comp_select == "true")
	{
	obj=jobj.patCompositionSelectData.FOLLOWUPADVICE.list_selected;
	var content = '';
	
	//if(obj[0].visitNotes!=undefined) 
    //content += '<tr><td>' + obj[0].visitNotes +'</td></tr><br>';
    var fontcontent ='<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">'; 
    var fontcontentend='</font>';
    if(obj[0].episodeIsOpen=="0")
    {
   		content +='<tr>td> &nbsp;&nbsp;</td><td cellpadding="1" cellspacing="1"><b>EPISODE END </b> </td></tr>';
   		if(obj[0].visitNotes!=undefined && obj[0].visitNotes!=null ) 
   		content +='<tr>td> &nbsp;&nbsp;</td><td cellpadding="1" cellspacing="1"><b>PROGRESS NOTES: </b></td> <td>'+ obj[0].visitNotes +'</td></tr>';
   		if(obj[0].episodeSummary!=undefined && obj[0].episodeSummary!=null) 
   		content +='<tr>td> &nbsp;&nbsp;</td><td cellpadding="1" cellspacing="1"><b>EPISODE SUMMARY: </b></td><td>'+ obj[0].episodeSummary+'</td></tr>';
    }
    else
    {
    	if(obj[0].visitNotes!=undefined && obj[0].visitNotes!=null ) 
   		{
    	 content +='<tr><td> &nbsp;&nbsp;</td><td cellpadding="1" cellspacing="1"><b>PROGRESS NOTES: </b></td>';
   		 content += '<td cellpadding="1" cellspacing="1">'+obj[0].visitNotes+'</td></tr>';
   		}
    	
      content += '<tr><td> &nbsp;&nbsp;</td><td cellpadding="1" cellspacing="1"><b>FOLLOW UP: </b></td>';
	  if(obj[0].nextVisitCriteria!=null && obj[0].nextVisitCriteria!=undefined && obj[0].nextVisitCriteria=="0")
   	 	{
		 content +=  '<td cellpadding="1" cellspacing="1">SOS</td></tr>';
     	 } 

   	  if(obj[0].nextVisitCriteria !=null && obj[0].nextVisitCriteria!=undefined  && obj[0].nextVisitCriteria=="1")
    	{
    	 content +=	'<td cellpadding="1" cellspacing="1">'+obj[0].nextVisitDuration+'</td></tr>' ;
    	} 
    	
      if( obj[0].nextVisitCriteria !=null && obj[0].nextVisitCriteria!=undefined && obj[0].nextVisitCriteria=="4")
    	{
     	content += '<td cellpadding="1" cellspacing="1"><b>Tentative Next Visit Date: </b></td>';
     	content += '<td cellpadding="1" cellspacing="1">' +obj[0].episodeNextVisitDate+'</td></tr>' ;
    	}
    	
    } 

  /*   content +=' </font>'; */
	$('#tbFollowUpAdvice').append(content);   
	
	window.opener.jsonSelect.patCompositionSelectData.FOLLOWUPADVICE.html = content;
	
	}
	
}   







</script>


</html>