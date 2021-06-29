<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
      
      
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
      <title>Discharge Status</title> 

<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>
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
<script>
var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

var callbck_index_HC =function(ret_OUT){setValueHC(ret_OUT);};

function hospitalCourseOnLoad()
{

if(document.getElementsByName("hospitalCourse")[0].value!="")
	{
	//alert("notes")
	document.getElementsByName("txt-snomed-ct-search_HC")[0].value=document.getElementsByName("hospitalCourse")[0].value;
	setfreeTextHC();
	}

load_HC('HC','','');
}	


function load_HC(elmtId,semantictag,refsetId)
{
	
 if(elmtId=="HC")
	{
	 id="hospitalCourse";
	}
 
 setTarget(id);

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};



selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index_HC);


$("#conceptdiv_1").hide();
$("#norecorddiv_1").hide();
$("#conceptdiv_2").hide();
$("#norecorddiv_2").hide();

$("#conceptdiv_3").hide();
$("#norecorddiv_3").hide();
$("#conceptdiv_4").hide();
$("#norecorddiv_4").hide();

}

function setfreeTextHC()   //for free text
{

	 document.getElementsByName("hospitalCourse")[0].value=document.getElementsByName("txt-snomed-ct-search_HC")[0].value;

	// alert(document.getElementsByName("hospitalCourse")[0].value);
 
}

function setValueHC(selectedSNOMEDTerm)
{
//alert("hi");
var target=document.getElementsByName("targetIdHC")[0].value;

var targetPrefferedTerm="";
var targetConceptId="";

if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
{

	 var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;
	//alert(str1);

 if(target == "hospitalCourse") 
	{
	//alert("inside vist note");	  
	 targetPrefferedTerm=document.getElementsByName("snomdPTHospitalCourse")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomdCIdHospitalCourse")[0];  //concept Id
		 
		  if(document.getElementsByName("hospitalCourse")[0].value=="" || document.getElementsByName("hospitalCourse")[0].value==undefined)
			 document.getElementsByName("hospitalCourse")[0].value = str;
		 else 
			 document.getElementsByName("hospitalCourse")[0].value=document.getElementsByName("hospitalCourse")[0].value + '; ' + str;
	}

//alert(targetPrefferedTerm.value);
    if(targetPrefferedTerm.value=="" || targetPrefferedTerm.value==undefined) 
        	document.getElementsByName("snomdPTHospitalCourse")[0].value = str;
        
    else
    	document.getElementsByName("snomdPTHospitalCourse")[0].value = document.getElementsByName("snomdPTHospitalCourse")[0].value + "#" + str;
	
	if(targetConceptId.value=="" || targetConceptId.value==undefined) 
		document.getElementsByName("snomdCIdHospitalCourse")[0].value = str1;
	else 
		document.getElementsByName("snomdCIdHospitalCourse")[0].value = document.getElementsByName("snomdCIdHospitalCourse")[0].value + "#" + str1;
	  
 
}
}

function setTarget(id)
{
//	alert("fdsfs");
if(id=="hospitalCourse")
{
document.getElementsByName("targetIdHC")[0].value="hospitalCourse";}

}



</script>

<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); %>
	<html:hidden name="EHRSection_HospitalCourseFB" property="entryDate" value="<%=sysDate%>"/>
	
			<table class="table table-condensed table responsive">
				<tr>
					<td style="font-size:1.2em;font-weight:bold;"width="100%">
					Hospital Course&nbsp;:
					<img id="Image" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;cursor:hand;display:none;"/> 
					</td>
						</tr>
						<tr>
					<td  style="vertical-align: middle;">
						<div align="left">
						    <html:hidden name="EHRSection_HospitalCourseFB" property="snomdPTHospitalCourse" ></html:hidden>
					        <html:hidden name="EHRSection_HospitalCourseFB" property="snomdCIdHospitalCourse" ></html:hidden>
					         <html:hidden name="EHRSection_HospitalCourseFB" property="hospitalCourse" ></html:hidden>  
					         
					        <div class="form-group col-xs-12 col-md-11 col-sm-12">
                          <textarea class="form-control" id=txt-snomed-ct-search_HC name="txt-snomed-ct-search_HC" maxlength="2000" onkeypress="return validateText(event)"  onfocus="load_HC('HC','','');" onchange="setfreeTextHC()"  autocomplete="off"></textarea>
                          <div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#txt-snomed-ct-search_HC').val('');" class="btn btn-sm btn-default BtnCleanreasonOfVisit"><i class="fa fa-times"></i></button>
							</div>
                        </div>
                         	
						<!-- <textarea name="txt-snomed-ct-search_HC" rows="2" cols="20" id="txt-snomed-ct-search_HC" class="form-control" value=""  onfocus="load_HC('HC','','');" onchange="setfreeTextHC()" style="width:90%;" autocomplete="off"></textarea>
							 -->									
						</div>
					</td>
				</tr>
			</table>
			



<input type="hidden" name="targetIdHC" />

<html:hidden name="EHRSection_HospitalCourseFB" property="hmode" />

<html:hidden name="EHRSection_HospitalCourseFB" property="episodeCode" />
<html:hidden name="EHRSection_HospitalCourseFB" property="admissionNo" />
<html:hidden name="EHRSection_HospitalCourseFB" property="departmentUnitCode" />
<html:hidden name="EHRSection_HospitalCourseFB" property="deskType" />
<html:hidden name="EHRSection_HospitalCourseFB" property="seatId" />
<html:hidden name="EHRSection_HospitalCourseFB" property="isSetSD" />
<html:hidden name="EHRSection_HospitalCourseFB" property="dataExist" />


 

					         
<script>hospitalCourseOnLoad();</script>
