<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="mrd.EMRConfig"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="emr.dataentry.spp.presentation.fb.*"%>
<%@page import="ehr.medicationAdvice.dataentry.EHRSection_Medication_AdviceFB"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css" />
<link href="/../HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<link href="/../HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/../HIS/hisglobal/snomedct/css/images/csnotk.ico">
<link rel="stylesheet" href="/../HIS/hisglobal/snomedct/css/jquery-ui.css">
<link href="/../HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">

<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
<his:javascript src="/investigation/js/investigation.js" />
<his:javascript src="/emr/dataentry/spp/js/EHR_uni_page_prescription.js" />
<his:javascript src="/ehr/js/EHR_spp_followup.js" />

<script type="text/javascript" src="/../HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<script type="text/javascript" src="/../HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="/../HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="/../HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/../HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/../HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>
</head>

     <style>
 .ui-autocomplete {
       z-index:2147483647;
     } 

  </style> 
<script type="text/javascript">
var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

var callbck_index_CS =function(ret_OUT){setValueCS(ret_OUT);};

function medicationOnLoad()
{

if(document.getElementsByName("medication")[0].value!="")
	{
	//alert("notes")
	document.getElementsByName("search_CS1")[0].value=document.getElementsByName("medication")[0].value;
	setfreeTextCS();
	}

load_CS('CS','','');
}	


function load_CS(elmtId,semantictag,refsetId)
{


 if(elmtId=="CS")
	{
	 id="medication";
	}
 
 setTarget(id);

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};



selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index_CS);


$("#conceptdiv_1").hide();
$("#norecorddiv_1").hide();
$("#conceptdiv_2").hide();
$("#norecorddiv_2").hide();

$("#conceptdiv_3").hide();
$("#norecorddiv_3").hide();
$("#conceptdiv_4").hide();
$("#norecorddiv_4").hide();



}


function setfreeTextCS()   //for free text
{
	
	 document.getElementsByName("medication")[0].value=document.getElementsByName("search_CS1")[0].value;
	 
 
}

function setValueCS(selectedSNOMEDTerm)
{

var target=document.getElementsByName("targetIdCS")[0].value;

	
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
	//alert(str1);

 if(target == "medication") 
	{
	//alert("inside vist note");	  
	 targetPrefferedTerm=document.getElementsByName("snomdPTMedication")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomdCIDMedication")[0];  //concept Id
		 
		  if(document.getElementsByName("medication")[0].value=="" || document.getElementsByName("medication")[0].value==undefined)
			 document.getElementsByName("medication")[0].value = str;
		 else 
			 document.getElementsByName("medication")[0].value=document.getElementsByName("medication")[0].value + '; ' + str;
	}

//alert(targetPrefferedTerm.value);
    if(targetPrefferedTerm.value=="" || targetPrefferedTerm.value==undefined) 
        	document.getElementsByName("snomdPTMedication")[0].value = str;
        
    else
    	document.getElementsByName("snomdPTMedication")[0].value = document.getElementsByName("snomdPTMedication")[0].value + "#" + str;
	
	if(targetConceptId.value=="" || targetConceptId.value==undefined) 
		document.getElementsByName("snomdCIDMedication")[0].value = str1;
	else 
		document.getElementsByName("snomdCIDMedication")[0].value = document.getElementsByName("snomdCIDMedication")[0].value + "#" + str1;
	  
 
}
}

function setTarget(id)
{
//	alert("fdsfs");
if(id=="medication")
{
document.getElementsByName("targetIdCS")[0].value="medication";}

}

function validate_ENC_MED_ADV_TEXT()
{
	//alert( document.getElementsByName("medication")[0].value);
	 document.getElementsByName("medication")[0].value=document.getElementsByName("search_CS1")[0].value;
	 //alert(document.getElementsByName("caseSummary")[0].value);
	 var caseSummaryLength = document.getElementsByName("medication")[0].value.length;
	 //alert(caseSummaryLength);
	 if(caseSummaryLength>20000)
		 {
            alert("Maximum 20000 characters are allowed in Medication Advice..");
            document.getElementsByName("search_CS1")[0].focus();
            return false;
		 }
	 else
		 {
		   return true;
		 }
}
//}


</script>

<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); %>
	<html:hidden name="EHRSection_Medication_AdviceFB" property="entryDate" value="<%=sysDate%>"/>
	
			<table class="table">
				<tr>
					<td style="font-size:1.2em;font-weight:bold;"width="100%">
						Other Advices&nbsp;:
							<img id="Image" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;cursor:hand;display:none;"/> 
					</td>
				</tr>
				<tr>
					<td  style="vertical-align: middle;">
						<div align="left">
						     <html:hidden name="EHRSection_Medication_AdviceFB" property="snomdPTMedication" ></html:hidden>
					        <html:hidden name="EHRSection_Medication_AdviceFB" property="snomdCIDMedication" ></html:hidden>
					         <html:hidden name="EHRSection_Medication_AdviceFB" property="medication" ></html:hidden>     
					          	
					         <div class="form-group col-xs-12 col-md-11 col-sm-12">
                          <textarea class="form-control" id="search_CS" name="search_CS1" maxlength="2000" onkeypress="return validateText(event)"  onchange="setfreeTextCS()" autocomplete="off"></textarea>
                          <div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#search_CS').val('');" class="btn btn-sm btn-default BtnCleanOT"><i class="fa fa-times"></i></button>
							</div>
                        </div>	
						 <!-- <textarea name="txt-snomed-ct-search_CS1" rows="2" cols="20" id="txt-snomed-ct-search_CS" onchange="setfreeTextCS()" class="form-control" value=""  onfocus="load_CS('CS','','');" style="width:90%;" autocomplete="off"></textarea>		 -->
						</div>
					</td>
				</tr>
			</table>
			
<input type="hidden" name="targetIdCS" />

<html:hidden name="EHRSection_Medication_AdviceFB" property="hmode" />

	<html:hidden name="EHRSection_Medication_AdviceFB" property="medication" />
	<html:hidden name="EHRSection_Medication_AdviceFB" property="snomdPTMedication" />
	<html:hidden name="EHRSection_Medication_AdviceFB" property="snomdCIDMedication" />
<html:hidden name="EHRSection_Medication_AdviceFB" property="episodeCode" />
<html:hidden name="EHRSection_Medication_AdviceFB" property="admissionNo" />
<html:hidden name="EHRSection_Medication_AdviceFB" property="departmentUnitCode" />
<html:hidden name="EHRSection_Medication_AdviceFB" property="deskType" />
<html:hidden name="EHRSection_Medication_AdviceFB" property="seatId" />
<html:hidden name="EHRSection_Medication_AdviceFB" property="isSetSD" />
<html:hidden name="EHRSection_Medication_AdviceFB" property="dataExist" />


 

					         
<script>medicationOnLoad();</script>


