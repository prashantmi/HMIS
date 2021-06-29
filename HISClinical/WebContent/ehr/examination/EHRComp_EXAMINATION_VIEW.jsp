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
<%@page import="ehr.treatmentdetail.vo.EHRSection_TreatmentVO"%>
<%@page import="emr.vo.*"%>



<%@page import="hisglobal.utility.HelperMethods"%>

<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<%@page import="opd.OpdConfig"%>
<%@page import="java.util.Map"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="hisglobal.vo.TemplateMasterVO"%>
<%@page import="ehr.examination.vo.EHRSection_ExaminationVO"%>
<%@page import="hisglobal.utility.generictemplate.controller.utl.GenericTemplateUTL"%>
<%@page import="hisglobal.tools.tag.GenericTemplateTag"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


 <title>EXAMINATION_PRN</title>      
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/style.css" rel="stylesheet"> 
<link href="/HIS/hisglobal/bbpublic/assets/css/elements.css" rel="stylesheet"> 
<link href="/HIS/hisglobal/bbpublic/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="/HIS/hisglobal/bbpublic/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>
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

<%System.out.println("helllllllllll"); %>


<body style="margin:0%">
<form>

<table id="tbExamination" width="70%" >
</table>



</form>
</body>

<script>
var ehrObj=null;
//alert('hi');

if(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0])
	ehrObj = JSON.parse(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0].value);
	//alert(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0].value);
createTemplateExam('EXAMINATION',ehrObj);


window.onload=function()
{
	//alert('hi');
	//if(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0])
	//	ehrObj = JSON.parse(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0].value);
//	createTemplateExam('ADVICEONDISCHARGE',ehrObj);
	//alert(opener.objEHRSelect);
	//alert(objEHRSelect);
	// ehrObj=document.getElementsByName("objEHRSelect")[0].value;	
	/* ehrObj=parent.objEHRSelect; */
	// createTemplateExam('ADVICEONDISCHARGE',ehrObj);
}

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


function createTemplateExam(key,jobj)
{

	var obj=[];
	if(jobj.patCompositionSelectData.EXAMINATION.comp_select == "true")
	{
	obj=jobj.patCompositionSelectData.EXAMINATION.list_selected;
	//alert(obj.length);
	var content = '';
	var i=0;
	 for(var i=0; i < obj.length; i++)
	{
		 content =  obj[i].templateDataBase64;
		 alert(content);
		 content = window.btoa(content);
		 alert(content);
	     content += '<tr><td width="40%">' + obj[i].entryDate +'</td><td width="40%">'+ obj[i].templateName + '</td><td width="20%"></td></tr>';
	     content += '<tr><td width="40%"></td><td width="60%" colspan="2"><div>'+content+'</div></td></tr>';
		    
			 
		 	// alert(content);    
		   
	}}	
	$('#tbExamination').append(content);   
	window.opener.jsonSelect.patCompositionSelectData.EXAMINATION.html = content;

}
   
</script>


</html>