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

<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>
<link href="/../HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<link href="/../HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/../HIS/hisglobal/snomedct/css/images/csnotk.ico">
<link rel="stylesheet" href="/../HIS/hisglobal/snomedct/css/jquery-ui.css">
<link href="/../HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">
  <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet"> 
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

var callbck_index_DS =function(ret_OUT){setValueDS(ret_OUT);};

function statusDischargeOnLoad()
{
//	alert("inside onload");
//setEpisodeCloseDataOnLoad();
//endTreatmentForDeadPatient();
//if(callThisOnload)
	//callThisOnload();

if(document.getElementsByName("statusAtDischarge")[0].value!="")
	{
	//alert("notes")
	document.getElementsByName("search_DS")[0].value=document.getElementsByName("statusAtDischarge")[0].value;
	//alert(document.getElementsByName("statusAtDischarge")[0].value);
	setfreeTextSAD();
	}

load_DS('DS','','');
}	


function load_DS(elmtId,semantictag,refsetId)
{

//alert("inside load");
//alert(elmtId+','+semantictag+','+refsetId);


 if(elmtId=="DS")
	{
	 id="statusAtDischarge";
	}
 
 setTarget(id);

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

//var callbck_index =function(ret_OUT){setValueDS(ret_OUT);};



//elmtId="2";
//alert(elmtId);

selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index_DS);


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
	 document.getElementsByName("statusAtDischarge")[0].value=document.getElementsByName("search_DS")[0].value;
	 //alert(document.getElementsByName("statusAtDischarge")[0].value);
	 /*var text_data_SAD= document.getElementsByName("txt-snomed-ct-search_DS")[0].value;
	if(text_data_SAD.length > 0 )
	{
		var lastchar_SAD= text_data_SAD[text_data_SAD.length - 1];
		//alert(lastchar_SAD)
		if(lastchar_SAD.match(";"))
		{
			//alert("Nilesh : "+document.getElementsByName("txt-snomed-ct-search_FU2")[0].value);
			//document.getElementsByName("statusAtDischarge")[0].value=document.getElementsByName("txt-snomed-ct-search_DS")[0].value;
			
		}
		else
		{
			//alert("false condition");
			document.getElementsByName("txt-snomed-ct-search_DS")[0].value=document.getElementsByName("txt-snomed-ct-search_DS")[0].value+";";
			//document.getElementsByName("statusAtDischarge")[0].value=document.getElementsByName("txt-snomed-ct-search_DS")[0].value; 
		}
	} */
 
}

function setValueDS(selectedSNOMEDTerm)
{

var target=document.getElementsByName("targetIdDS")[0].value;

	
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

 if(target == "statusAtDischarge") //statusAtDischarge
	{
	//alert("inside vist note");	  
	 targetPrefferedTerm=document.getElementsByName("snomdPTStatusAtDischarge")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomdCIdStatusAtDischarge")[0];  //concept Id
		 
		  if(document.getElementsByName("statusAtDischarge")[0].value=="" || document.getElementsByName("statusAtDischarge")[0].value==undefined)
			 document.getElementsByName("statusAtDischarge")[0].value = str;
		 else 
			 document.getElementsByName("statusAtDischarge")[0].value=document.getElementsByName("statusAtDischarge")[0].value + '; ' + str;
	}

//alert(targetPrefferedTerm.value);
    if(targetPrefferedTerm.value=="" || targetPrefferedTerm.value==undefined) 
        	document.getElementsByName("snomdPTStatusAtDischarge")[0].value = str;
        
    else
    	document.getElementsByName("snomdPTStatusAtDischarge")[0].value = document.getElementsByName("snomdPTStatusAtDischarge")[0].value + "#" + str;
	
	if(targetConceptId.value=="" || targetConceptId.value==undefined) 
		document.getElementsByName("snomdCIdStatusAtDischarge")[0].value = str1;
	else 
		document.getElementsByName("snomdCIdStatusAtDischarge")[0].value = document.getElementsByName("snomdCIdStatusAtDischarge")[0].value + "#" + str1;
	  
 
}
}

function setTarget(id)
{
//	alert("fdsfs");
if(id=="statusAtDischarge")
{
document.getElementsByName("targetIdDS")[0].value="statusAtDischarge";}

}



</script>

<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); %>
	<html:hidden name="EHRSection_StatusAtDischargeFB" property="entryDate" value="<%=sysDate%>"/>
	
			<table class=" table table-condensed table-responsive">
				<tr>
			
						<td style="font-size:1.2em;font-weight:bold;"width="100%">
							Condition At Discharge&nbsp;:
						<img id="ImageRefreshForServiceProcedure" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;cursor:hand;display:none;"/> 

					</td>
					</tr>
					<tr>
					<td >
						<html:hidden name="EHRSection_StatusAtDischargeFB" property="snomdPTStatusAtDischarge" ></html:hidden>
					        <html:hidden name="EHRSection_StatusAtDischargeFB" property="snomdCIdStatusAtDischarge" ></html:hidden>
					         <html:hidden name="EHRSection_StatusAtDischargeFB" property="statusAtDischarge" ></html:hidden>    
					         
					         <div class="form-group col-xs-12 col-md-11 col-sm-12">
                          <textarea class="form-control" id="search_DS" name="search_DS" maxlength="2000" onkeypress="return validateText(event)" onchange="setfreeTextSAD()"></textarea>
                          <div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#search_DS').val('');" class="btn btn-sm btn-default BtnCleanDischargeStatus"><i class="fa fa-times"></i></button>
							</div>
                        </div>  	
						<!-- <textarea name="txt-snomed-ct-search_DS" rows="2" cols="20" id="txt-snomed-ct-search_DS" onchange="setfreeTextSAD()" class="form-control" value=""  onfocus="load_DS('DS','','');" style="width:58%;" autocomplete="off"></textarea>
							 -->									
					
					</td>
				</tr>
			</table>
			

<input type="hidden" name="targetIdDS" />

<html:hidden name="EHRSection_StatusAtDischargeFB" property="hmode" />

<html:hidden name="EHRSection_StatusAtDischargeFB" property="episodeCode" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="admissionNo" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="departmentUnitCode" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="deskType" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="seatId" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="isSetSD" />
<html:hidden name="EHRSection_StatusAtDischargeFB" property="dataExist" />


 

					         
<script>statusDischargeOnLoad();</script>
