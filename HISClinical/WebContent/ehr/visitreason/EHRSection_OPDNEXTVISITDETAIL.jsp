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

<!-- <script src="/HISClinical/mrd/js/flexdatalist/jquery.flexdatalist.min.js"></script>    
<link rel="stylesheet" href="/HISClinical/mrd/js/flexdatalist/jquery.flexdatalist.min.cs"> -->

 <his:javascript src="/ehr/js/EHR_spp_visitReason.js" />
 </head>
 
<!-- <div> -->
<!-- <table class="table"> -->
<div class="row">
<div class="col-sm-12" style="font-size:1.2em;font-weight:bold;" align="left">
 Chief Complaints&nbsp;:
<img id="Image" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;cursor:hand;display:none;"/> 
<!-- <img id="ImageRefreshForExamination" src="/HISClinical/hisglobal/images/icon_refresh.gif"  onclick="getChiefComplaintsData();"   style="cursor:pointer;cursor:hand;"/> --> 
<!-- <a class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="top" title="Save"  onclick = "saveChiefComplaints();";id="saveIdForChiefComplaints"><span class="glyphicon glyphicon-save" > -->
</div>

</div>
<div class="row" style="margin-top: 15px;"></div>
<!-- <div class = "row"></div> -->

<div class = "row">	

<div class="col-sm-4" align="left">
						
							<html:hidden name="EHRSection_VisitReasonFB" property="snomdPTVisitReason" ></html:hidden>
					        <html:hidden name="EHRSection_VisitReasonFB" property="snomdCIdVisitReason" ></html:hidden>
						 <html:hidden name="EHRSection_VisitReasonFB" property="ehrVisitReason" ></html:hidden>
								
						 
						 		
				   <!-- <div id="dialog-form_1" > -->
					<!-- <div id="snomed-ct-search"> -->
					<!-- <span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span> -->
					<input autocomplete="off" placeholder="Chief Complaints" id="txt-snomed-ct-search_VR" class="clearable ui-autocomplete-input x onX form-control"   name="txt-snomed-ct-search_VR"  style="width:300px;color:#000000;" type="text" onfocus="load_UNIVD('VR','','450970008');clearGeneralComplaints();" onKeyUp="setSnomedChiefComplaints();">
					<!-- </div> -->
					  
                    <!--  <div class="concept" id="conceptdiv_1">                 
                    </div> -->
                        <!--  </div> -->
						
					</div>
					<div class="col-sm-2" align="left">
					 
                        <p style="padding-top: 10px" align="center"><b>OR</b></p>
                    
                     </div>
                      <div class="col-sm-4" align="left">
					 
					      <input type="text" placeholder="Generic Complaint" name="generalComplaint" id="generalComplaintId" tabindex=-1 class="form-control"style="width:300px;"onfocus="clearCheifComplaints();" onkeyup="setGeneralChiefComplaints();">
					  
					</div>
					
					<div class="col-sm-2" align="left">
					<div align="center">
					 <button class="btn btn-sm btn-info reasonOfVisitAdd" id="reasonOfVisitAddId" type="button" onClick ="addComplaints();">Add</button>
				    </div> 
				</div>
					
					
				
	</div>	
	<div class="row" style="margin-top: 15px;"></div>
	 <div class="row">
                      <div class="col-sm-10 col-md-11 prescriptionTileRowFirstCol" align="left">
                        <p class="reasonOfVisitAdded" id="reasonOfVisitAdded"><!-- <b>Chief Complaint :</b> --> 
                          <!-- <a style="text-decoration: none;" class="clearLnk">
                            <img style="width: 22px;" alt="" src="/HIS/hisglobal/drDeskAssets/img/clear3.png">
                          </a>
                          <button class="btn btn-xs btn-danger clearAllValues" type="button" data-toggle="tooltip" title="Clear All" onclick="$(this).parent().find('label').remove();" style="background-color: white;border: 0px;"><span class="glyphicon glyphicon-trash" style="color: red;"></span></button> 
                        -->
                        </p>
                      </div>
                    </div>
                    
	
<!-- </table> -->

<!-- <table class="table"> -->
<div class = "row">	
	<div class="col-sm-12" style="font-size:1.2em;font-weight:bold;" align="left">
 History of Present Illness: Reported by Patient or Representative of Patient&nbsp;:
<img id="Image" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;cursor:hand;display:none;"/> 
</div>

</div>
<div class="row" style="margin-top: 15px;"></div>
<div class="row">		
			
				<div class="col-sm-12" align="left">
								
				   <html:hidden name="EHRSection_VisitReasonFB" property="presentIllnessHistory" ></html:hidden>					 		
					
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					
					<div class="form-group col-xs-12 col-md-11 col-sm-12">
                          <textarea class="form-control" id="presentIllnessHistoryText" name="presentIllnessHistoryText" maxlength="3000" onkeypress="return validateText(event)" onchange="setPresentHistory();"></textarea>
                          <div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#presentIllnessHistoryText').val('');" class="btn btn-sm btn-default BtnCleanreasonOfVisit"><i class="fa fa-times"></i></button>
							</div>
                        </div>
                          <!-- <i class="fa fa-comment" style="font-size: 2em; v-align:middle;"></i>
                        <div class="col-xs-3 col-sm-1" style="padding-right: 0px; text-align: center;">
                          <button type="button" style="padding: 0px;color:#3696e9" class="btn btn-link progressNoteMacroBtn" id="hopiIdDiv" value="hopiIdDiv">  onclick="$('#progressNoteMacroModal').modal('show');" <i class="material-icons" style="font-size: 1.5em">message</i></button>
                        </div> -->
					<!-- <textarea name="presentIllnessHistoryText" rows="3" cols="20" id="" class="form-control" value="" onchange="setPresentHistory();"  onfocus="" style="width:100%;" autocomplete="off"></textarea> -->
					
					</div>
								
	</div>
	
	
<!-- </table> -->

<!-- </div>  -->
 	

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

 function newfunc()
 {
	//alert("dcasf");
		
		//alert(document.getElementsByName("ehrVisitReason")[0].value)
		/* if(document.getElementsByName("ehrVisitReason")[0].value!="")
			{
			//alert("adfwe");
			//document.getElementsByName("txt-snomed-ct-search_VR")[0].value=document.getElementsByName("ehrVisitReason")[0].value+';';
			
			document.getElementById("txt-snomed-ct-search_VR").value=document.getElementsByName("ehrVisitReason")[0].value;
			VRSetDataExist();
			} */
			//alert(document.getElementsByName("ehrVisitReason")[0].value);
			var complaints = document.getElementsByName("ehrVisitReason")[0].value;
            
			var reasonOfVisitVAl = complaints.split(';');
			//alert(reasonOfVisitVAl);

			for(var i=0;i<(reasonOfVisitVAl.length-1);i++)
			{
			 $('#reasonOfVisitAdded').append('<label><button tabindex="0" style="padding-left: 5px;font-weight:700;" type="button" class="value btn btn-xs">'+
								    	 		'  '+
								    	 		'<span class="text">'+reasonOfVisitVAl[i]+' </span>'+
								    	 		'<span class="fdl-remove" onclick="$(this).parent().remove();">x</span>'+
								    	 		'</button></label>');
			 $('#reasonOfVisitAdded').append('&nbsp;&nbsp');
			}
			
			document.getElementsByName("presentIllnessHistoryText")[0].value=document.getElementsByName("presentIllnessHistory")[0].value;
 }

function load_UNIVD(elmtId,semantictag,refsetId)
{

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
	
			document.getElementsByName("ehrVisitReason")[0].value=document.getElementsByName("txt-snomed-ct-search_VR")[0].value;

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

	 var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;
	


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
	//alert("1");
	
	//alert(document.getElementById('reasonOfVisitAdded').textContent);
	var cc = document.getElementById('reasonOfVisitAdded').textContent.trim();
	
	var finalCC = cc.replace(/\x/g,";");
	//alert(finalCC);
	
	document.getElementsByName("ehrVisitReason")[0].value = finalCC;
	//alert(document.getElementsByName("presentIllnessHistory")[0].value);
	//document.getElementsByName("ehrVisitReason")[0].value=document.getElementsByName("txt-snomed-ct-search_VR")[0].value;
	//alert(document.getElementsByName("ehrVisitReason")[0].value);
	return true;
}	



function setTarget(id)
{

if(id=="ehrVisitReason")
{
document.getElementsByName("targetId")[0].value="ehrVisitReason";}


}

function setPresentHistory()
{
	document.getElementsByName("presentIllnessHistory")[0].value=document.getElementsByName("presentIllnessHistoryText")[0].value;
}

/* $('#txt-snomed-ct-search_VR').on('keyup',function(event){
	if(event.which == 13)
	{
		//alert('hiii');
		 $('#addButton').click();
		}
	 }); */
 
/* $('input[name=txt-snomed-ct-search_VR]').on('select:flexdatalist',function(event, set, options){
	$('input[name=snomdPTVisitReason]').val(set.diagnosisName);
	 $("p").text(set.diagnosisName);
});


}); */

		
</script>

