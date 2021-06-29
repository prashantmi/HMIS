<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="mrd.EMRConfig"%>
<%@page import="emr.dataentry.spp.presentation.fb.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Visit details</title> 
      <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
<his:javascript src="/investigation/js/investigation.js" />   
   <his:javascript src="/emr/dataentry/spp/js/EHR_uni_page_prescription.js" />   
      
      
     <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
     <link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/jquery-ui.css">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script> -->
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">

<script>
function validate_ENC_CC_ROV()
{
	//alert("inside chief complaints visit");
	return true;
}
</script>
   
 </head>
    <style>


  </style>
<div class="container" style="height:50px;">
<div class="row">
	<div class="col-*-*" style="font-size:1.2em;font-weight:bold;text-align:left;">
Chief Complaints&nbsp;:
<img id="Image" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;cursor:hand;display:none;"/> 
</div>
</div>
	<div class="row">
			<div class="col-*-*" style="vertical-align: middle;">
						<div  align="left" class="col-*-*">
							<html:hidden name="EHRSection_VisitReasonFB" property="snomdPTVisitReason" ></html:hidden>
					        <html:hidden name="EHRSection_VisitReasonFB" property="snomdCIdVisitReason" ></html:hidden>
						 <html:hidden name="EHRSection_VisitReasonFB" property="ehrVisitReason" ></html:hidden>
								
				   <div id="dialog-form_1" class="col-*-*">
					<div id="snomed-ct-search" class="col-*-*">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_VR" class="clearable ui-autocomplete-input x onX form-control" name="txt-snomed-ct-search_VR"  style="width:90%;color:#000000;" type="text" onfocus="load_UNIVD('VR','','450970008');">
					</div>
					
                     <div class="concept col-*-*" id="conceptdiv_1">                 
                    </div>
                         </div>
						</div>
					</div>
</div>
	
</div>
 
 	

<input type="hidden" name="targetId" />
 <html:hidden name="EHRSection_VisitReasonFB" property="isSet_OPDNEXTVISITDETAIL" ></html:hidden>
<script>
ehrVisitReasonOnLoad();
function ehrVisitReasonOnLoad()
{
	//alert("hiii");
	newfunc();
	load_UNIVD('1','','450970008');
	//alert(document.getElementById("txt-snomed-ct-search_VR").value);
	


}
 /*  $(window).on("load", function() {

	  ehrVisitReasonOnLoad();

	}); */
 function newfunc()
 {
	//alert("dcasf");
		
		//alert(document.getElementsByName("ehrVisitReason")[0].value)
		if(document.getElementsByName("ehrVisitReason")[0].value!="")
			{
			//alert("adfwe");
			//document.getElementsByName("txt-snomed-ct-search_VR")[0].value=document.getElementsByName("ehrVisitReason")[0].value+';';
			
			document.getElementById("txt-snomed-ct-search_VR").value=document.getElementsByName("ehrVisitReason")[0].value;
			VRSetDataExist();
			}
		
 }

function load_UNIVD(elmtId,semantictag,refsetId)
{
	//newfunc();
	
//alert("inside load");
//alert(elmtId+','+semantictag+','+refsetId);
 if(elmtId=="VR")
	{
	var id="ehrVisitReason";
	
	}

 setTarget(id);

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

//alert("hii 2")
var callbck_index =function(ret_OUT){setValueVR(ret_OUT);};


if(elmtId==null || elmtId==undefined)
	{
	elmtId="VR"; semantictag="DISORDER";

	}
//elmtId="2";
//alert(elmtId);

selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index);


$("#conceptdiv_1").hide();
$("#norecorddiv_1").hide();


}

/*	Name: Nilesh Gupta
	Date: 2-11-2017
*/
function setfreeTextVR()   //for free text
{
	//document.getElementsByName("ehrVisitReason")[0].value=document.getElementsByName("txt-snomed-ct-search_VR")[0].value;
	
	var text_data_VR= document.getElementsByName("txt-snomed-ct-search_VR")[0].value;
	if(text_data_VR.length > 0 )
	{
		var lastchar_VR= text_data_VR[text_data_VR.length - 1];
		//alert(lastchar_VR)
		if(lastchar_VR.match(";"))
		{
			//alert("Nilesh VR : "+document.getElementsByName("txt-snomed-ct-search_FU2")[0].value);
			document.getElementsByName("ehrVisitReason")[0].value=document.getElementsByName("txt-snomed-ct-search_VR")[0].value;
		}
		else
		{
			//alert("false condition VR");
			document.getElementsByName("ehrVisitReason")[0].value=document.getElementsByName("txt-snomed-ct-search_VR")[0].value +";";
		}
	}

}

function setValueVR(selectedSNOMEDTerm)
{
//alert("hi");
//var target=document.getElementsByName("targetId")[0].value;
var target="ehrVisitReason";
	
var targetPrefferedTerm="";
var targetConceptId="";

if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
{
//	alert(selectedSNOMEDTerm);		
	/* var arr=selectedSNOMEDTerm.split("@@@");
	var str=arr[0];
	var str1=arr[1];
	 */
	 var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;
	//alert(str);
	//alert(str1);


if(target == "ehrVisitReason") // Visit Reason
{

	 targetPrefferedTerm=document.getElementsByName("snomdPTVisitReason")[0];  //preffered term
	 targetConceptId=document.getElementsByName("snomdCIdVisitReason")[0];   //concept Id

	 if(document.getElementsByName("ehrVisitReason")[0].value=="") document.getElementsByName("ehrVisitReason")[0].value = str;
	else document.getElementsByName("ehrVisitReason")[0].value = document.getElementsByName("ehrVisitReason")[0].value + "; " + str;
	
}

 
 if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
	else targetPrefferedTerm.value = targetPrefferedTerm.value + "#" + str;
	
	if(targetConceptId.value=="")  targetConceptId.value = str1;
	else targetConceptId.value = targetConceptId.value + "#" + str1;
	

}
else
{
document.getElementsByName("snomdPTVisitReason")[0].value="";
document.getElementsByName("snomdCIdVisitReason")[0].value="";
// document.getElementById("somectIddata").innerHTML ="";
}
}



function validate_ENC_CC_ROV()
{
	//alert("CC");
	document.getElementsByName("ehrVisitReason")[0].value=document.getElementsByName("txt-snomed-ct-search_VR")[0].value;
	//alert(document.getElementsByName("ehrVisitReason")[0].value);
	return true;
		
}



function setTarget(id)
{

if(id=="ehrVisitReason")
{
document.getElementsByName("targetId")[0].value="ehrVisitReason";}


}


</script>

