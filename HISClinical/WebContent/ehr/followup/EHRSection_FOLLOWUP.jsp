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
<title>Follow up</title> 

<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<!-- <link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet"> -->
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet">

<his:css src="/hisglobal/utility/generictemplate/css/newDropDownSrch.css"/>

<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
<his:javascript src="/hisglobal/utility/generictemplate/js/newDropDownSrch.js" />
<his:javascript src="/investigation/js/investigation.js" />
<his:javascript src="/emr/dataentry/spp/js/EHR_uni_page_prescription.js" />  
<his:javascript src="/ehr/js/EHR_spp_followup.js" /> 

<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

   
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

var callbck_index =function(ret_OUT){setValueFUP(ret_OUT);};

function followupOnLoad()
{
//	alert("inside onload");
setEpisodeCloseDataOnLoad();
endTreatmentForDeadPatient();
if(callThisOnload)
	callThisOnload();

if(document.getElementsByName("isConfirmed")[0].value==<%=RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED%>)
{FUSetDataExist();
	//alert("inside if")
	//alert(document.getElementsByName("keywords")[0].value)
if(document.getElementsByName("keywords")[0].value!="")
	{
	//alert("keyword")
	
	document.getElementById("txt-snomed-ct-search_FU1").value=document.getElementsByName("keywords")[0].value;
	}


if(document.getElementsByName("visitNotes")[0].value!="")
	{
	//alert("notes")
	document.getElementById("search_FU2").value=document.getElementsByName("visitNotes")[0].value;
	}

if(document.getElementsByName("episodeSummary")[0].value!="") 
	{
	//alert("episode")
	document.getElementById("txt-snomed-ct-search_FU3").value=document.getElementsByName("episodeSummary")[0].value;
	}

}


load_FU('FU1','','3310011000189109');
load_FU('FU2','','');
load_FU('FU3','','');

}	


function load_FU(elmtId,semantictag,refsetId)
{

//alert("inside load");
//alert(elmtId+','+semantictag+','+refsetId);

 if(elmtId=="FU1")
	{
	 id="episodekeywordVal";
	}
 if(elmtId=="FU2")
	{
	 id="visitNotes";
	}
 if(elmtId=="FU3")
	{
	 id="episodeSummary";
	}
 setTarget(id);

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

var callbck_index =function(ret_OUT){setValueFUP(ret_OUT);};


if(elmtId==null || elmtId==undefined)
	{
	elmtId="FU1"; semantictag="DISORDER";

	}
//elmtId="2";
//alert(elmtId);

//selectSNOMEDCTmulti('ACTIVE',semantictag,'SYNONYMS','10',refsetId,elmtId,callbck_index);


$("#conceptdiv_1").hide();
$("#norecorddiv_1").hide();
$("#conceptdiv_2").hide();
$("#norecorddiv_2").hide();

$("#conceptdiv_3").hide();
$("#norecorddiv_3").hide();
$("#conceptdiv_4").hide();
$("#norecorddiv_4").hide();



}


function setfreeTextFU()   //for free text
{
	//alert("Inside Follow UP");
	document.getElementsByName("keywords")[0].value=document.getElementsByName("txt-snomed-ct-search_FU1")[0].value;

//document.getElementsByName("visitNotes")[0].value=document.getElementsByName("search_FU2")[0].value;
		//alert(document.getElementById('snomd_data').value);
		var text_data_FU= document.getElementsByName("search_FU2")[0].value;
			if(text_data_FU.length > 0 )
			{
				var lastchar_FU= text_data_FU[text_data_FU.length - 1];
				//alert(lastchar_FU)
				if(lastchar_FU.match(";"))
				{
					//alert("Nilesh : "+document.getElementsByName("search_FU2")[0].value);
					document.getElementsByName("visitNotes")[0].value=document.getElementsByName("search_FU2")[0].value;
				}
				else
				{
					//alert("false condition");
					document.getElementsByName("visitNotes")[0].value = document.getElementsByName("search_FU2")[0].value+";";
				}
			}

	//document.getElementsByName("episodeSummary")[0].value=document.getElementsByName("txt-snomed-ct-search_FU3")[0].value;
	var text_data_FU3= document.getElementsByName("txt-snomed-ct-search_FU3")[0].value;
	if(text_data_FU3.length > 0 )
	{
		var lastchar_FU3= text_data_FU3[text_data_FU3.length - 1];
		//alert(lastchar_FU)
		if(lastchar_FU3.match(";"))
		{
			//alert("Nilesh : "+document.getElementsByName("search_FU2")[0].value);
			document.getElementsByName("episodeSummary")[0].value=document.getElementsByName("txt-snomed-ct-search_FU3")[0].value;
		}
		else
		{
			//alert("false condition");
			document.getElementsByName("episodeSummary")[0].value=document.getElementsByName("txt-snomed-ct-search_FU3")[0].value+";";
		}
	}

}

function setValueFUP(selectedSNOMEDTerm)
{
//alert("hi");
var target=document.getElementsByName("targetIdFUP")[0].value;

	
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
	



if(target == "episodekeywordVal") // Keywords
{
	//alert("inside keyowrd");
	/* var data = unescape(id);
	data = data.substring(data.indexOf("\'") + 1 , data.lastIndexOf("\'"));
	var dataTxt = data.split(" : ")[1];
	var dataVal = data.split(" : ")[0];
   */	
   
   if(document.getElementsByName("keywords")[0].value=="") document.getElementsByName("keywords")[0].value = str;
	else document.getElementsByName("keywords")[0].value = document.getElementsByName("keywords")[0].value + "; " + str;
	
    targetPrefferedTerm=document.getElementsByName("snomdPTKeywords")[0];  //preffered term
	 targetConceptId=document.getElementsByName("snomdCIdKeywords")[0];   //concept Id
	}



 if(target == "visitNotes") //visitNotes
	{
	//alert("inside vist note");	  
	 targetPrefferedTerm=document.getElementsByName("snomdPTVisitNotes")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomdCIdVisitNotes")[0];  //concept Id
		   
		  if(document.getElementsByName("visitNotes")[0].value=="") document.getElementsByName("visitNotes")[0].value = str;
		 else document.getElementsByName("visitNotes")[0].value=document.getElementsByName("visitNotes")[0].value + '; ' + str;
	}
 
 if(target == "episodeSummary") // episode summary
	{
	//alert("inside episode summary")	  ;
	 targetPrefferedTerm=document.getElementsByName("snomdPTEpisodeSummary")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomdCIdEpisodeSummary")[0];  //concept Id
		  if(document.getElementsByName("episodeSummary")[0].value=="") document.getElementsByName("episodeSummary")[0].value = str;
		 else document.getElementsByName("episodeSummary")[0].value=document.getElementsByName("episodeSummary")[0].value + '; ' + str;

	}
 
 
 
 
 
 if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
	else targetPrefferedTerm.value = targetPrefferedTerm.value + "#" + str;
	
	if(targetConceptId.value=="")  targetConceptId.value = str1;
	else targetConceptId.value = targetConceptId.value + "#" + str1;
	
   // document.getElementsByName("targetIdFUP")[0].value="";
    //targetPrefferedTerm="";
    //targetConceptId="";


    /*else
{
	 var data = unescape(id);
	data = data.substring(data.indexOf("\'") + 1 , data.lastIndexOf("\'"));
	targetElement.value = data;
}
 $("#dialog-form").dialog( "close" ); */
}
}







function setTarget(id)
{


if(id=="episodekeywordVal")
{
document.getElementsByName("targetIdFUP")[0].value="episodekeywordVal";}
if(id=="visitNotes")
{
document.getElementsByName("targetIdFUP")[0].value="visitNotes";}
if(id=="episodeSummary")
{
document.getElementsByName("targetIdFUP")[0].value="episodeSummary";}


}






//On change of Next Visit Mode
function setNextVisitMode()
{
var arrModes = document.getElementsByName("nextVisitCriteria");
var elemDivDays = document.getElementById("divNextDateModeDays");
var elemDivDate = document.getElementById("divNextDateModeDate");
elemDivDays.style.display = "none";
elemDivDate.style.display = "none";

for(var i=0; i<arrModes.length; i++)
{
	if(arrModes[i].checked)
	{
		if(arrModes[i].value == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>")
		{
			elemDivDays.style.display = "block";
		}
		else if(arrModes[i].value == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>")
		{
			elemDivDate.style.display = "block";
		} 
	}
}
}

function setEpisodeCloseData()
{
var episodeClose = null;
var radios = document.getElementsByName('episodeIsOpen');
for(var i=0;i<radios.length;i++)
{
	if(radios[i].checked)
	{
		episodeClose = radios[i].value;
	}
}
if(episodeClose=="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>")
{
	// Episode Summary
	var elemDivEpiSumm = document.getElementById("divEpisodeSummary");
	elemDivEpiSumm.style.display = "block";

	var elemDivModes = document.getElementById("divNextDateModes");
	elemDivModes.style.display = "none";

	var arrModes = document.getElementsByName("nextVisitCriteria");		
	for(var i=0; i<arrModes.length; i++)
	{
		arrModes[i].checked = false;
		arrModes[i].disabled = true;
	}
	setNextVisitMode();
}
else
{
	// Episode Summary
	var elemDivEpiSumm = document.getElementById("divEpisodeSummary");
	elemDivEpiSumm.style.display = "none";

	var elemDivModes = document.getElementById("divNextDateModes");
	elemDivModes.style.display = "block";

	var arrModes = document.getElementsByName("nextVisitCriteria");
	for(var i=0; i<arrModes.length; i++)
	{
		arrModes[i].disabled = false;
	}
	arrModes[0].checked = true;
	setNextVisitMode();
}
}

function validateNextVisitModes()
{
var elemDivModes = document.getElementById("divNextDateModes");
if(elemDivModes.style.display.toUpperCase()=="NONE")	return true;

var arrModes = document.getElementsByName("nextVisitCriteria");
var checked = false;	
for(var i=0; i<arrModes.length; i++)
{
	if(arrModes[i].checked)
	{
		checked = true;
		if(arrModes[i].value == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>")
		{
			if(document.getElementsByName("nextVisitDuration")[0].value=="")
			{
				alert("Please Enter Next Visit Duration");
				document.getElementsByName('nextVisitDuration')[0].focus();
				return false;
			}
			var dur = parseInt(document.getElementsByName("nextVisitDuration")[0].value);
			var durCri = document.getElementsByName("nextVisitDurationCriteria")[0].value;
			if(durCri == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC%>" && !(dur>0 && dur<=99))
			{
				alert("Please Enter Days in Range 1-99");
				document.getElementsByName('nextVisitDuration')[0].focus();
				return false;
			}
			if(durCri == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_WEEKS_SPECIFIC%>" && !(dur>0 && dur<=99))
			{
				alert("Please Enter Weeks in Range 1-99");
				document.getElementsByName('nextVisitDuration')[0].focus();
				return false;
			}
			if(durCri == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_MONTHS_SPECIFIC%>" && !(dur>0 && dur<=12))
			{
				alert("Please Enter Months in Range 1-12");
				document.getElementsByName('nextVisitDuration')[0].focus();
				return false;
			}
		}
		else if(arrModes[i].value == "<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>")
		{
			if(document.getElementsByName("episodeNextVisitDate")[0].value=="")
			{
				alert("Please Enter Next Visit Date from Schedule");
				document.getElementsByName('episodeNextVisitDate')[0].focus();
				return false;
			}
			var nextVisitDt = convertStrToDate(document.getElementsByName("episodeNextVisitDate")[0].value,"dd-Mon-yyyy");
			var entryDate = convertStrToDate(document.getElementsByName("entryDate")[0].value,"dd-Mon-yyyy");
			if(nextVisitDt<=entryDate)
			{
				alert("Next Visit Date should be greater than Current Date");
				document.getElementsByName('episodeNextVisitDate')[0].focus();
				return false;
			}
		} 
	}
}
return true;
}

function setEpisodeCloseDataOnLoad()
{
var episodeClose = null;
var radios = document.getElementsByName('episodeIsOpen');
for(var i=0;i<radios.length;i++)
{
	if(radios[i].checked)
	{
		episodeClose = radios[i].value;
	}
}

var elemDivModes = document.getElementById("divNextDateModes");
var elemDivEpiSumm = document.getElementById("divEpisodeSummary");
if(episodeClose=="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>")
{
	for(var i=0;i<radios.length;i++)
		radios[i].disabled = true;

	elemDivEpiSumm.style.display = "block";
	elemDivModes.style.display = "none";

	var arrModes = document.getElementsByName("nextVisitCriteria");		
	for(var i=0; i<arrModes.length; i++)
	{
		arrModes[i].checked = false;
		arrModes[i].disabled = true;
	}
	setNextVisitMode();
}
else
{
	elemDivEpiSumm.style.display = "none";
	elemDivModes.style.display = "block";
	setNextVisitMode();
}
}



function endTreatmentForDeadPatient()
{
var elemDivModes = document.getElementById("divNextDateModes");
if(document.getElementsByName("isPatDead")[0].value==<%=RegistrationConfig.YES%>)
{
	document.getElementsByName("episodeIsOpen")[0].checked=true;
	document.getElementsByName("episodeIsOpen")[0].disabled=true;
	document.getElementsByName("episodeIsOpen")[1].disabled=true;
	
	elemDivModes.style.display = "none";
	setNextVisitMode();
}

}

function getUploadedProfile(event,path)
{
openDependentPopup(path,event,600,700,'yes');
}

function doPagination(e,p)
{
document.getElementsByName('currentPage')[0].value=p;
document.getElementsByName("hmode")[0].value = "PAGINATION";
document.forms[0].submit();
}

function showEmployeepopup(e,fieldToPopulate,index)
{
	//var dept = document.getElementsByName("deptname")[0].value;
	var dept = "";
	var path="/HISClinical/mrd/employeePopUp.cnt?fieldToPopulate="+fieldToPopulate+"&index="+index+"&deptname="+dept
	openPopup(createFHashAjaxQuery(path),e,300,600);
}	


function setFreeText()
{
	 document.getElementsByName("visitNotes")[0].value=document.getElementsByName("search_FU2")[0].value;
}
</script>

<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); %>
	<html:hidden name="EHRSection_FollowupFB" property="entryDate" value="<%=sysDate%>"/>
	
	<bean:define id="entrySeatId" name="EHRSection_FollowupFB" property="seatId" type="java.lang.String"></bean:define>
	
	<bean:define id="unitCode" name="EHRSection_FollowupFB" property="departmentUnitCode" type="java.lang.String"></bean:define>
	


<table class="table table-condensed table-responsive">
	<tr>
		<td style="font-size:1.2em;font-weight:bold;"width="100%">
		Follow Up&nbsp;:
		<img id="Image" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;cursor:hand;display:none;"/> 
		</td>

	</tr>
<tr style="display:none">
					<td   >
						<div >
					
								<b>&nbsp;<bean:message key="accidental"/></b>
								
					
						</div>
					</td>
					<td  >
						<div >
						  <html:hidden name="EHRSection_FollowupFB" property="snomdPTKeywords" ></html:hidden>
					        <html:hidden name="EHRSection_FollowupFB" property="snomdCIdKeywords" ></html:hidden>
					        <html:hidden name="EHRSection_FollowupFB" property="keywords" ></html:hidden>
						
							
							  <div id="dialog-form_2" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_FU1" class="clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_FU1"   style="width:65%;color:#000000;" type="text" onfocus="load_FU('FU1','','3310011000189109');">
					</div>
					 <div id="norecorddiv_2">
					 <label style="display: inline;" id="reccnt">No. of records : </label>
					<span style="display: inline;" id="reccount" ></span>
					<label style="display: none;" id="nosuggestion">No suggestions found</label>
					<label style="display: none;" id="norec">No results found</label>
                     <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
                 
				    </div>         
                     <div class="concept" id="conceptdiv_2">                 
                    </div>
                         </div>
					
					 
										
						</div>
					</td>
				</tr>
				<tr>
					<td>
						
						
								<b><font color="#FF0000">*</font>&nbsp;<bean:message key="endtreatment"/></b>
						
							
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="EHRSection_FollowupFB" property="episodeIsOpen" styleClass="custom-control-input" value="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="yes"/>
						&nbsp;&nbsp;
						<html:radio name="EHRSection_FollowupFB" property="episodeIsOpen" styleClass="custom-control-input" value="<%=RegistrationConfig.EPISODE_ISOPEN_TRUE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="no"/>
						
					</td>
					
				</tr>
			</table>
			
			<div  id="divNextDateModes" style="display: none;">
				<table class="table table-condensed table-responsive">
					<tr >
						<td >
							
									<b><font color="#FF0000">*</font>&nbsp;<bean:message key="plannedVisit"/></b>
					
						&nbsp;&nbsp;&nbsp;
									<logic:equal name="EHRSection_FollowupFB" property="patNextAptNo" value="">
							
									<html:radio name="EHRSection_FollowupFB" property="nextVisitCriteria" styleClass="custom-control-input"  value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="EHRSection_FollowupFB" property="nextVisitCriteria" styleClass="custom-control-input"  value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									
									<html:radio name="EHRSection_FollowupFB" property="nextVisitCriteria" styleClass="custom-control-input"  value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
								
								</logic:equal>
									<logic:notEqual name="EHRSection_FollowupFB" property="patNextAptNo" value="">
																		<html:radio name="EHRSection_FollowupFB" property="nextVisitCriteria"  disabled="true" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="EHRSection_FollowupFB" property="nextVisitCriteria" styleClass="custom-control-input"  disabled="true" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									<html:radio name="EHRSection_FollowupFB" property="nextVisitCriteria"  styleClass="custom-control-input"  value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
							
								</logic:notEqual>		
						</td>
					</tr>
				</table>
			</div>
            
			<div   id="divNextDateModeDays" style="display: none;">
				<table class="table table-condensed table-responsive">
					<tr>
				<td align="right" width="25%">
						<html:text name="EHRSection_FollowupFB" property="nextVisitDuration" tabindex="1" maxlength="2" styleClass="form-control" size="5" style="width:120px;height:30px;" onkeypress="return validatePositiveIntegerOnly(this,event)"/>
						</td>
						<td align="left"  width="25%">
									<html:select name="EHRSection_FollowupFB" property="nextVisitDurationCriteria" styleClass="form-control" style="width:120px;height:30px;" tabindex="1">
										<html:option value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC%>">
										<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS_SPECIFIC)] %>
										</html:option>
										<html:option value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_WEEKS_SPECIFIC%>">
										<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_WEEKS_SPECIFIC)] %>
										</html:option>
										<html:option value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_MONTHS_SPECIFIC%>">
										<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_MONTHS_SPECIFIC)] %>
										</html:option>
									</html:select>
							
											
						</td>
						<td  width="50%"></td>
						
					</tr>
					
				</table>
			
			</div>

			<div   id="divNextDateModeDate" style="display: none;">
				<table class="table table-condensed table-responsive">
					<tr><td width="20%"></td>
					<logic:equal name="EHRSection_FollowupFB" property="patNextAptNo" value="">
							<td  width="10%">
								
								<html:text name="EHRSection_FollowupFB" property="episodeNextVisitDate" styleClass="form-control" style="width:120px;" tabindex="1" size="15" readonly="true" />
							</td>
							<td width="5%">
									<img class="button" src='<his:path src="/hisglobal/images/scheduler.png"/>' style="cursor:pointer;vertical-align: middle;" 
										onkeypress="if(event.keyCode==13) getSchedule(event)" onclick="getSchedule(event)" title="Click to Select Schedule Date">
							
								</logic:equal>
								
									<logic:notEqual name="EHRSection_FollowupFB" property="patNextAptNo" value="">
									
								<bean:message key="nextAppointment"/>
								<bean:write name="EHRSection_FollowupFB" property="patNextAptNo" />
								<bean:write name="EHRSection_FollowupFB" property="episodeNextVisitDate" />
								<bean:write name="EHRSection_FollowupFB" property="patNextAptSlot" />
									
						<html:hidden name="EHRSection_FollowupFB" property="episodeNextVisitDate"  />
						<html:hidden name="EHRSection_FollowupFB" property="patNextAptNo" />
						<html:hidden name="EHRSection_FollowupFB" property="patNextAptSlot" />
						
							
								</logic:notEqual>
																					
							
								<!-- Added by Singaravelan on 23-Mar-2015 for Apt Tag Integration  -->
								<div id="aptTagRow" style="display:none;"></div>												
							</td>
							<td width="65%"></td>
					</tr>
				</table>
			</div>
			
            <table class="table table-condensed table-responsive" >
             <tr>
				   <td width="10%">
				  
						  <font color="#FF0000">*</font>
							<b>
				               Prescribed By
				            </b>
						</td>
						<td width="10%">   
				       <html:hidden name="EHRSection_FollowupFB" property="requestById" />
                            <html:hidden name="EHRSection_FollowupFB" property="requestByEmpDept" />
							<html:text name="EHRSection_FollowupFB" property="requestByName" styleClass="form-control" style="width:120px;height:40px;" readonly = "true"></html:text>
							</td>
							<td width="5%">
							  <img class="button" src="<his:path src='/hisglobal/images/ico_myfriends.gif'/>" tabindex="1" border="0" style="cursor:pointer" onkeypress="if(event.keyCode==13) showEmployeepopup(event,'requestById@requestByName@requestByEmpDept','<%=0 %>');" 
							   onclick="showEmployeepopup(event,'requestById@requestByName@requestByEmpDept','<%=0 %>');" alt="Search Employee" title="Search Consultant">							
                            <html:hidden name="EHRSection_FollowupFB" property="requestByName"/>            
				   </td>
				 <td width="75%"></td>
				</tr>
            </table>
          
			<table class="table table-condensed table-responsive">
				<tr>
					<td style="vertical-align: middle;" width="11%">
						<b><font style="vertical-align: middle;">Follow Up Remarks</font></b> &nbsp;&nbsp;&nbsp;&nbsp;   
							<html:hidden name="EHRSection_FollowupFB" property="snomdPTVisitNotes" ></html:hidden>
					        <html:hidden name="EHRSection_FollowupFB" property="snomdCIdVisitNotes" ></html:hidden>
					         <html:hidden name="EHRSection_FollowupFB" property="visitNotes" ></html:hidden>
					        
	 		      	</td>
	 		      	<td width="84%" >
	 		      	<div class="form-group col-xs-12 col-md-11 col-sm-12">
	 		      	
	 		      	 <textarea class="form-control" id="search_FU2" name="search_FU2" maxlength="2000" onkeypress="return validateText(event)"  onchange="setFreeText()" autocomplete="off" style="width:100%"></textarea>
	 		      	
	 		      	
	 		      	<!-- 
					<textarea name="txt-snomed-ct-search_FU2" rows="2" cols="20" id="txt-snomed-ct-search_FU2" class="clearable ui-autocomplete-input x onX form-control" value="" onchange="setFreeText();"  onfocus="load_FU('FU2','','');" style="width:100%;" autocomplete="off"></textarea>
					 -->	<div style="position: absolute;top: 0;right: 16px;">
							  <button type="button" onclick="$(this).parent().parent().find('#search_FU2').val('');" class="btn btn-sm btn-default BtnCleanfolloUp"><i class="fa fa-times"></i></button>
							</div>

					
						<% String onClickMacroButton="openVisitNotesMacros('"+OpdConfig.MACRO_PROCESS_ID_VISIT_SUMMARY+"','"+unitCode+"',event)"; %>
						
					</div>
						</td>
						<td width="5%">
 							<img class="button" src='<his:path src="/hisglobal/images/AddMacro.png"/>' title="Visit Notes Suggestions" style="cursor: pointer;vertical-align: middle;" tabindex="1" onclick="<%=onClickMacroButton%>" onkeypress="if(event.keyCode==13) <%=onClickMacroButton%>">
 						
						
						
					</td>
				</tr>
			</table>
			<div id="divEpisodeSummary" style="display: none;">
				<table class="table">
					<tr>
						<td style="vertical-align: middle;">
							<div align="right">
								
									<b><bean:message key="episode"/> <bean:message key="summary"/></b>
							
							</div>
						</td>
						<td  style="vertical-align: middle;">
							<div align="left">
							
								<html:hidden name="EHRSection_FollowupFB" property="snomdPTEpisodeSummary" ></html:hidden>
					        <html:hidden name="EHRSection_FollowupFB" property="snomdCIdEpisodeSummary" ></html:hidden>
				 <html:hidden name="EHRSection_FollowupFB" property="episodeSummary" ></html:hidden>
							
	 
	 	<textarea name="txt-snomed-ct-search_FU3" rows="3" cols="20" id="txt-snomed-ct-search_FU3" maxlength="2000" onkeypress="return validateText(event)" class="clearable ui-autocomplete-input x onX" value=""  onfocus="load_FU('FU3','','');" style="width:58%;" autocomplete="off"></textarea>
						
	
							</div>
						</td>
					</tr>
				</table>
			</div>



<input type="hidden" name="targetIdFUP" />

<%--  <html:hidden name="EHRSection_FollowupFB" property="hmode" /> --%>
<html:hidden name="EHRSection_FollowupFB" property="serialNo" />
<html:hidden name="EHRSection_FollowupFB" property="episodeCode" />
<html:hidden name="EHRSection_FollowupFB" property="episodeVisitNo" />
<html:hidden name="EHRSection_FollowupFB" property="admissionNo" />
<html:hidden name="EHRSection_FollowupFB" property="empNo" />
<html:hidden name="EHRSection_FollowupFB" property="loginSeatId" />
<html:hidden name="EHRSection_FollowupFB" property="departmentUnitCode" />
<html:hidden name="EHRSection_FollowupFB" property="deskType" />
<html:hidden name="EHRSection_FollowupFB" property="isConfirmed" />

<html:hidden name="EHRSection_FollowupFB" property="isEpisodeAlreadyOpen" />
<html:hidden name="EHRSection_FollowupFB" property="seatId" />
<html:hidden name="EHRSection_FollowupFB" property="episodeTypeCode" />
<html:hidden name="EHRSection_FollowupFB" property="episodeDate" />
<html:hidden name="EHRSection_FollowupFB" property="isPatDead" />

<html:hidden name="EHRSection_FollowupFB" property="currentPage"/>
<html:hidden name="EHRSection_FollowupFB" property="episodeKeyword"/>
<html:hidden name="EHRSection_FollowupFB" property="episodeKeywords"/>
<html:hidden name="EHRSection_FollowupFB" property="episodeKeywordID"/>
<html:hidden name="EHRSection_FollowupFB" property="visitReason"/>
<html:hidden name="EHRSection_FollowupFB" property="episodeIsOpen"/>

<html:hidden name="EHRSection_FollowupFB" property="isUnitAppointmentBased"/>
<html:hidden name="EHRSection_FollowupFB" property="patNextAptNo"/>
<html:hidden name="EHRSection_FollowupFB" property="patNextAptSlot"/>
<html:hidden name="EHRSection_FollowupFB" property="patNextAptQueueNo"/>
<html:hidden name="EHRSection_FollowupFB" property="isSetFOLLOWUP"/>
 
<script>followupOnLoad();</script>
