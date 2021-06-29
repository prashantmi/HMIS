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
<%@page import="ehr.treatmentdetail.vo.EHRSection_TreatmentVO"%>
<%@page import="emr.vo.*"%>
<%@page import="opd.OpdConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


      <title>TREATMENT_PRN</title>      
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

<%System.out.println("helllllllllll"); %>

<% 
List <EHRSection_TreatmentVO> drugDtlVO=(List<EHRSection_TreatmentVO>) session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST);
//EHRSection_VisitReasonVO VisitVO= patencountervo.getListSaveAllVisitReason();
//System.out.println(drugDtlVO.getDrugName());
System.out.println(drugDtlVO);

%>
<body style="margin:0%">
<form>

<table id="tbAdviceOnDischarge">
	</table>
</form>
</body>

<script>
var ehrObj=null;
//alert('hi');
//showData(opener);
if(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0])
	ehrObj = JSON.parse(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0].value);
createAdviceonDischarge('ADVICEONDISCHARGE',ehrObj);
//ehrObj=opener.objEHRSelect;
//alert(ehrObj);

window.onload=function()
{
	//alert('hi');
	//if(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0])
	//	ehrObj = JSON.parse(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0].value);
//	createAdviceonDischarge('ADVICEONDISCHARGE',ehrObj);
	//alert(opener.objEHRSelect);
	//alert(objEHRSelect);
	// ehrObj=document.getElementsByName("objEHRSelect")[0].value;	
	/* ehrObj=parent.objEHRSelect; */
	// createAdviceonDischarge('ADVICEONDISCHARGE',ehrObj);
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

//String jsonSelect=(String)session.getAttribute(EHRConfig.CLINICAL_SECTION_COMP_SELECT_JSON);
//String jobj=request.getParameter("jsonData");
//System.out.println("mkkkkkkkkk"+jobj);

//Gson gson = new Gson();
//JsonElement jelem = gson.fromJson(jsonSelect, JsonElement.class);
//JsonObject jobj = jelem.getAsJsonObject();
//System.out.println("mkkkkkkkkk"+jobj);

/* var jsonSelect =  {"patCrNo":"",
"patDocId":"",
"patEncounterDtl":{},
"patCompositionSelectData":
  {
	      "ADVICEONDISCHARGE": {"list_selected":[]},
    "TREATMENT":{"list_selected":[]},
    "ENCDIAGNOSIS":{"list_selected":[]},
    "ENCINVESTIGATION":{"list_selected":[]}
   }
}; 
*/

 function createAdviceonDischarge(key,jobj)
{
	//alert(jobj.patCompositionSelectData.ADVICEONDISCHARGE.list_selected);
	var obj=[];
	if(jobj.patCompositionSelectData.ADVICEONDISCHARGE.comp_select == "true")
	{
		obj=jobj.patCompositionSelectData.ADVICEONDISCHARGE.list_selected;
	//alert(obj.length);
	//alert(key);
	//  var content = '<div id= div_ADVICEONDISCHARGE>';
	  var content = '';
	 // alert(obj[0].drugName);
    for(var k=0; k < obj.length;k++)
    {
    	
	   content += '<tr><td>' + obj[k].drugName+'&nbsp;&nbsp;'+ obj[k].frequencyName  + '&nbsp;X&nbsp;'  + obj[k].days +'Days &nbsp;&nbsp;' + ((obj[k].drugAdminName == undefined) ? '' : obj[k].drugAdminName) + '</td></tr><br>';
	  
	 }
//	content += '</div>';   
 
	//alert(content);
	$('#tbAdviceOnDischarge').append(content);   
	
	window.opener.jsonSelect.patCompositionSelectData.ADVICEONDISCHARGE.html = content;
	//alert(window.opener.jsonSelect.patCompositionSelectData.ADVICEONDISCHARGE.html);
	
	}
}   
</script>


</html>