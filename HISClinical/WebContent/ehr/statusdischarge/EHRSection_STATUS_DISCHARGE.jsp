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
      <title>Discharge Status</title> 
      <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
<his:javascript src="/investigation/js/investigation.js" />   
   <his:javascript src="/emr/dataentry/spp/js/EHR_uni_page_prescription.js" />   
    <his:javascript src="/ehr/js/EHR_spp_followup.js" />   
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
   
 </head>
    <style>


  </style>
<script>
var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

var callbck_index =function(ret_OUT){setValue(ret_OUT);};

function followupOnLoad()
{
//	alert("inside onload");
setEpisodeCloseDataOnLoad();
endTreatmentForDeadPatient();
if(callThisOnload)
	callThisOnload();

if(document.getElementsByName("isConfirmed")[0].value==<%=RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED%>)
{
	SDSetDataExist();
	

if(document.getElementsByName("statusAtDischarge")[0].value!="")
	{
	//alert("notes")
	document.getElementById("txt-snomed-ct-search_FU4").value=document.getElementsByName("statusAtDischarge")[0].value;
	}

}

load_FU('FU4','','');
}	


function load_FU(elmtId,semantictag,refsetId)
{

//alert("inside load");
//alert(elmtId+','+semantictag+','+refsetId);


 if(elmtId=="FU4")
	{
	 id="statusAtDischarge";
	}
 
 setTarget(id);

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

var callbck_index =function(ret_OUT){setValue(ret_OUT);};



//elmtId="2";
//alert(elmtId);

selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index);


$("#conceptdiv_1").hide();
$("#norecorddiv_1").hide();
$("#conceptdiv_2").hide();
$("#norecorddiv_2").hide();

$("#conceptdiv_3").hide();
$("#norecorddiv_3").hide();
$("#conceptdiv_4").hide();
$("#norecorddiv_4").hide();



}


function setfreeTextSAD()   //for free text
{
	//alert("Inside SAD");
	//document.getElementsByName("statusAtDischarge")[0].value=document.getElementsByName("txt-snomed-ct-search_FU4")[0].value;
	var text_data_SAD= document.getElementsByName("txt-snomed-ct-search_FU4")[0].value;
	if(text_data_SAD.length > 0 )
	{
		var lastchar_SAD= text_data_SAD[text_data_SAD.length - 1];
		//alert(lastchar_SAD)
		if(lastchar_SAD.match(";"))
		{
			//alert("Nilesh : "+document.getElementsByName("txt-snomed-ct-search_FU2")[0].value);
			document.getElementsByName("statusAtDischarge")[0].value=document.getElementsByName("txt-snomed-ct-search_FU4")[0].value;
		}
		else
		{
			//alert("false condition");
			document.getElementsByName("statusAtDischarge")[0].value=document.getElementsByName("txt-snomed-ct-search_FU4")[0].value +";";
		}
	}
 
}

function setValue(selectedSNOMEDTerm)
{
//alert("hi");
var target=document.getElementsByName("targetId")[0].value;

	
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
	

 if(target == "statusAtDischarge") //statusAtDischarge
	{
	//alert("inside vist note");	  
	 targetPrefferedTerm=document.getElementsByName("snomdPTStatusAtDischarge")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomdCIdStatusAtDischarge")[0];  //concept Id
		 
		  if(document.getElementsByName("statusAtDischarge")[0].value=="") document.getElementsByName("statusAtDischarge")[0].value = str;
		 else document.getElementsByName("statusAtDischarge")[0].value=document.getElementsByName("statusAtDischarge")[0].value + '; ' + str;
	}


 if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
	else targetPrefferedTerm.value = targetPrefferedTerm.value + "#" + str;
	
	if(targetConceptId.value=="")  targetConceptId.value = str1;
	else targetConceptId.value = targetConceptId.value + "#" + str1;
	  //alert(targetConceptId);
 
}
}

function setTarget(id)
{
if(id=="statusAtDischarge")
{
document.getElementsByName("targetId")[0].value="statusAtDischarge";}

}



</script>

<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); %>
	<html:hidden name="EHRSection_StatusAtDischargeFB" property="entryDate" value="<%=sysDate%>"/>
	
	<%-- <bean:define id="entrySeatId" name="EHRSection_StatusAtDischargeFB" property="seatId" type="java.lang.String"></bean:define>
	
	<bean:define id="unitCode" name="EHRSection_FollowupFB" property="departmentUnitCode" type="java.lang.String"></bean:define>
 --%>
			<table width="98%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" style="vertical-align: middle;">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="disNote"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" style="vertical-align: middle;">
						<div align="left">
						       	 		 	<html:hidden name="EHRSection_StatusAtDischargeFB" property="snomdPTStatusAtDischarge" ></html:hidden>
					        <html:hidden name="EHRSection_StatusAtDischargeFB" property="snomdCIdStatusAtDischarge" ></html:hidden>
					         <html:hidden name="EHRSection_StatusAtDischargeFB" property="statusAtDischarge" ></html:hidden>      	
						<textarea name="txt-snomed-ct-search_FU4" rows="3" cols="20" id="txt-snomed-ct-search_FU4" class="clearable ui-autocomplete-input x onX" value=""  onfocus="load_FU('FU4','','');" style="width:58%;" autocomplete="off"></textarea>
																
						</div>
					</td>
				</tr>
			</table>
			



<input type="hidden" name="targetId" />

<%-- <html:hidden name="EHRSection_StatusAtDischargeFB" property="hmode" /> --%>

<html:hidden name="EHRSection_StatusAtDischargeFB" property="episodeCode" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="admissionNo" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="departmentUnitCode" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="deskType" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="seatId" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="isSetSD" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="dataExist" />
 

					         
<script>followupOnLoad();</script>
