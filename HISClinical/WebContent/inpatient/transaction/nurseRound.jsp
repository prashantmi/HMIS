<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/inpatient/js/nursingDesk.js"/>


<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<his:javascript src="/registration/js/registration.js"/>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<!-- <link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet"> -->
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtool.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script> -->
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">

<script type="text/javascript">



var callbck_index =function(ret_OUT){setValue(ret_OUT);};

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};

/*
* setValue() function is called in the callback function.This function is used to display the return value in the HTML control.In
* index.html page,the value is displayed in the "snomedcttext" textarea.The result includes- Concept id, Description-id and Description  
* param selectedSNOMEDTerm(var)-is the concept selected by the user from the search result list 
* 
*/


function selectValue(value, callback) 
{
	
	var data = unescape(value);

	var term = data.split('###$$$');

	var returnterm_OUT = term[1] +","+ term[0].replace("'", "");

	if (typeof callback === "function")
		callback(returnterm_OUT);

	$("#dialog-form").dialog("close");

}



function tog(v) { return v ? 'addClass' : 'removeClass'; }
$(document).on('mouseover', '.clearable', function () {
    $(this)[tog(this.value)]('x');
}).on('mousemove', '.x', function (e) {
    $(this)[tog(this.offsetWidth - 18 < e.clientX - this.getBoundingClientRect().left)]('onX');
}).on('touchstart click', '.onX', function (ev) {
    ev.preventDefault();
    $(this).removeClass('x onX').val('').change();
    var id = this.id;
    //alert("hi");
  		document.getElementsByName("snomdPTProgessNotes")[0].value="";  //preffered term
		document.getElementsByName("snomdCIdProgressNotes")[0].value="";   //concept Id
		document.getElementsByName("progressNote")[0].value="";
	

});





function setValue(selectedSNOMEDTerm)
{
	
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	//var targetElement=document.getElementsByName("targetId")[0].value;
	var targetElement="progressNotes";
	var targetPrefferedTerm="";
	var targetConceptId="";
	//alert(targetElement);
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
					
	//var arr=selectedSNOMEDTerm.split(",");
	//var str=arr[0];
	//var str1=arr[1];
	//alert(str[0]); alert(str1);
	
	     var str=selectedSNOMEDTerm.term;
		 var str1=selectedSNOMEDTerm.conceptId;
	
	
	
	 if(targetElement == "progressNotes") // progressNotes
	{
		  targetPrefferedTerm=document.getElementsByName("snomdPTProgessNotes")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomdCIdProgressNotes")[0];  //concept Id
		  document.getElementsByName("progressNote")[0].value=document.getElementsByName("progressNote")[0].value + ' ' + str;
		 
		 			
	}
	
	 
	    if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
		else targetPrefferedTerm.value = targetPrefferedTerm.value + "#" + str;
		
		if(targetConceptId.value=="")  targetConceptId.value = str1;
		else targetConceptId.value = targetConceptId.value + "#" + str1;
		
	    document.getElementsByName("targetId")[0].value="";
	    targetPrefferedTerm="";
	    targetConceptId="";
	
}
}

function setfreeText()
{
	  document.getElementsByName("progressNote")[0].value=document.getElementsByName("txt-snomed-ct-search_1")[0].value;  //for free text
		
}


function setTarget(id)
{
	if(id=="progressNotes")
	{
	document.getElementsByName("targetId")[0].value="progressNotes";}
	
}



function validateAdd()
{
	var valid=true;
	setfreeText();
	var progressNote = document.getElementsByName("progressNote")[0].value;
	//alert(progressNote);
	if(document.getElementsByName("progressNote")[0].value=="")
	{
		alert("Please Enter the Progress Notes");
		document.getElementsByName("progressNote").focus();
		valid=false;
	}
	
	// if(valid==true) setfreeText();
	return valid;
}
	
	
window.onload = function()
	{
		selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','1',callbck_index);
		menuColorflag();
		
	}
	
function getPrevProgress(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var path='/HISClinical/inpatient/nurseRound.cnt?hmode=PREVIOUS&patCrNo='+patCrNo;
	openPopup(createFHashAjaxQuery(path),event,400,700);
}

function addProgressNotes(event)
{
	var path='/HISClinical/inpatient/nurseRound.cnt?hmode=ADDNOTES&processId=1';
	openPopup(createFHashAjaxQuery(path),event,300,600);
}


function menuColorflag()
{
var patCrNo= document.getElementsByName("patCrNo")[0].value;
	
	checkForOnSelectDeskListItem(patCrNo);
	
}
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>


<his:TitleTag>
	<his:name>
		<bean:message key="nurseProgNote" />
	</his:name>
</his:TitleTag>

		<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />
		
		<his:SubTitleTag name="Progress Notes" >
			<table  width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					
					<td>
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<a style="cursor:pointer" onclick="getPrevProgress(event)" >	
										<bean:message key="previousProgress"/>
									</a>	
								</b>
							</font>
						</div>
					</td>
				</tr>
			</table>	
		</his:SubTitleTag>
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="#FF0000">*</font><b><bean:message key="progNote"/></b>
						</font>	
					</div>
				</td>
				<td width="70%" class="tdfont" style="vertical-align: middle;">
					<div align="left">
					
					<html:hidden name="NurseRoundFB" property="snomdPTProgessNotes" ></html:hidden>
					<html:hidden name="NurseRoundFB" property="snomdCIdProgressNotes" ></html:hidden>
					<html:hidden name="NurseRoundFB" property="progressNote"></html:hidden>
				<!-- 	<img  class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('progressNotes'); selectSNOMEDCT('ACTIVE','','SYNONYMS',10,'null',callbck_index);" onclick="setTarget('progressNotes'); selectSNOMEDCT('ACTIVE','','SYNONYMS', 10,'null',callbck_index);" title="Click to add SNOMED-CT Term">							
				 -->
				 
				  
				  <div id="dialog-form_1" >
					<div id="snomed-ct-search">
				<!-- 	<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
				 -->	<textarea  id="txt-snomed-ct-search_1" class="clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_1"  tabindex="1"  style="width:75%;color:#000000;" autocomplete="off" rows="5" cols="20" onfocus="setTarget('progressNotes');"  ></textarea>
					 </div>
					 
                     			</div>
				 
				 
				 	</div>
				</td>
				
				<td width="5%" class="tdfont" >
					<img class="button" src='<his:path src="/hisglobal/images/AddMacro.png"/>' title="Progress Notes Suggestions" style="cursor: pointer;vertical-align: middle;" tabindex="1" onclick="addProgressNotes(event)" onkeypress="if(event.keyCode==13) addProgressNotes(event)">
				
				</td>
			</tr>
		</table>
	</his:ContentTag>	
	
	<his:ButtonToolBarTag>
		<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "submitFormOnValidate(validateAdd(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateAdd(),'SAVE');")>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	</his:ButtonToolBarTag>
	
<html:hidden name="NurseRoundFB" property="hmode"/>
<html:hidden name="NurseRoundFB" property="patCrNo"/>	
<html:hidden name="NurseRoundFB" property="processId"/>			
 <input type="hidden" name="targetId" />	