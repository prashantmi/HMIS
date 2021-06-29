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


      <title>PATIENT DETAIL</title>      
     <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
     <link href="/HIS/hisglobal/bbpublic/assets/elements/css/style.css" rel="stylesheet"> 
     <link href="/HIS/hisglobal/bbpublic/assets/css/elements.css" rel="stylesheet"> 
     <link href="/HIS/hisglobal/bbpublic/assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!--  <link href="/HIS/hisglobal/bbpublic/assets/elements/css/combined.css" rel="stylesheet">  -->
       <link href="/HIS/hisglobal/bbpublic/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
           <script src="/HIS/hisglobal/bbpublic/assets/js/jquery-1.11.1.min.js"></script>

       
  <!--     <script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script> -->
      
  

   <script src="/HIS/hisglobal/bbpublic/assets/elements/js/bootstrap.min.js"></script>
   
   <script>
   function validate_PAT_DTL_DEMO();
   {
	   return true;
   }
   </script>
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
    
    .half-rule { 
    margin-left: 0;
    text-align: left;
    width: 100%;
   
 }
</style>


<%System.out.println("helllllllleeeeeelll"); %>


<body style="margin:0%">
<form>
<hr class="half-rule">
<table id="tbPatientSummary" width="100%" cellpadding="1px" >
</table>
	
</form>
</body>

<script>
var ehrObj=null;
//alert('hi');
//showData(opener);
if(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0])
	ehrObj = JSON.parse(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0].value);
//	alert(opener.document.getElementsByName("clinicalDocCompSelectJSON")[0].value);
createDiagnosis('PATIENTSUMMARY',ehrObj);
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
    "PATIENTSUMMARY":{"list_selected":[]},
    "ENCINVESTIGATION":{"list_selected":[]}
   }
}; 
*/

 function createDiagnosis(key,jobj)
{
	//alert("inside createDiagnosis");
	//alert(jobj.patCompositionSelectData.PATIENTSUMMARY.list_selected);
	var obj=[];
	if(jobj.patCompositionSelectData.PATIENTSUMMARY.comp_select == "true")
	{
	obj=jobj.patCompositionSelectData.PATIENTSUMMARY.list_selected;
		
	// alert(obj.length);
	 //alert(key);
	  var content = '';
	
	 // alert(obj[0].drugName); 
    for(var k=0; k < obj.length;k++)
    {
    	
    	/* if(obj[k].remarks == undefined)
    	{
    	//alert("1");
    	  obj[k].remarks = "";
    	} */
    																														
  	 //  content += '<tr><td> CR No </td><td>' + obj[k].patCrNo  + '</td><td> Name </td><td>'  + obj[k].patFirstName+'&nbsp;'+obj[k].patMiddleName+'&nbsp;'+obj[k].patLastName  +'</td><td> Age/Gender </td><td>' + obj[k].patAge +'/'+ obj[k].patGender'</td></tr><br>';
	   content += '<tr><td width="16%"><b><font color="#126295"> CR No: </font></td><td width="16%"><div align="left">' + obj[k].patCrNo  + '</div></td><td width="16%"><b><font color="#126295"> Name: </font></td><td width="16%"><div align="left">' + obj[k].patFirstName + '&nbsp;'+ ((obj[k].patMiddleName == undefined) ? '' : obj[k].patMiddleName) +'&nbsp;'+  ((obj[k].patLastName == undefined) ? '' : obj[k].patLastName)  +'</div></td><td width="16%"><b><font color="#126295"> Age/Gender:</font> </td><td width="16%"><div align="left">'  + obj[k].patAge + '/'+obj[k].patGender+'</div></td></tr>';
	   content += '<tr><td width="16%"><b><font color="#126295"> Unit: </font> </td><td width="16%"><div align="left">' + obj[k].departmentUnitName + '</div></td><td width="16%"><b><font color="#126295"> Ward/ Bed: </font></td><td width="16%"><div align="left">'  + obj[k].ipdRoomName + '/'+obj[k].bedName+'</div></td><td width="16%"> <b><font color="#126295">Admission No: </font> </td><td width="16%"><div align="left">' + obj[k].patAdmNo  + '</div></td></tr>';
	   content += '<tr><td width="16%"><b><font color="#126295"> Patient Category: </font></td><td width="16%"><div align="left">' + obj[k].patCat  + '</div></td><td width="16%"><b><font color="#126295"> Father Name:</font> </td><td width="16%"><div align="left">'  + obj[k].patFatherName + '</div></td><td width="16%"> <b><font color="#126295">Spouse Name: </font></td><td width="16%"><div align="left">' +  ((obj[k].patSpouceName == undefined) ? '' : obj[k].patSpouceName) + '</div></td></tr>';
	   // <td width="16%"> <b><font color="#126295">District:</font> </td><td width="16%"><div align="left">' +  ((obj[k].patAddDistrict == undefined) ? '' : obj[k].patAddDistrict) + '</div></td> 
	  
    }

 
	//alert(content);
	$('#tbPatientSummary').append(content);   
	
	window.opener.jsonSelect.patCompositionSelectData.PATIENTSUMMARY.html = content;
	//alert(window.opener.jsonSelect.patCompositionSelectData.ADVICEONDISCHARGE.html);
	//alert(content);
	}
	
}   
</script>


</html>