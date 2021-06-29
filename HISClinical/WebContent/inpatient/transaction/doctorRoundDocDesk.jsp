<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.Date"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<!-- <link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet"> -->
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtool.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script>
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

window.onload= function()
{
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS',10,'null','1',callbck_index);
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS',10,'null','2',callbck_index);
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS',10,'null','3',callbck_index);
	
	
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
    clear(id);
        
});


function submitFormOnValidate1(flag,mode)
{

 // alert("cr no  "+document.getElementsByName("patCrNo")[0]);
  //alert("submitFormOnValidate1")
 	if(flag)
	{
	 
		submitForm21(mode);
	}
	else{
// 	alert("elesee")
		return false;
	}
	
}


function clear(id)
{
	//alert(id); 
	var targetPrefferedTerm ="";
	var targetConceptId="";
	var elmtid=id.split('_');
	var trgt=elmtid[1];
	var targetPrefferedTerm="";  
	 var targetConceptId="";
	 document.getElementById(id).value="";
	 
	 if(trgt=="1")// Visit Reason
		{
			 targetPrefferedTerm=document.getElementsByName("snomdPTVisitNotes")[0];  //preffered term
			 targetConceptId=document.getElementsByName("snomdCIdVisitNotes")[0];   //concept Id
			 document.getElementsByName("visitNote")[0].value="";
			 
		}
	 if(trgt=="2") // progressNotes
		{
			  targetPrefferedTerm=document.getElementsByName("snomdPTProgessNotes")[0]; //preffered term
			  targetConceptId=document.getElementsByName("snomdCIdProgressNotes")[0];  //concept Id
			  document.getElementsByName("progressNote")[0].value="";

		}
	 if(trgt=="3") //instruction
		{
			  targetPrefferedTerm=document.getElementsByName("snomdPTInstruction")[0];  //preffered term
			  targetConceptId=document.getElementsByName("snomdCIdInstruction")[0];   //concept Id
			  document.getElementsByName("instruction")[0].value="";

		}
		 

		    targetPrefferedTerm.value = "";
		    targetConceptId.value = "";
	 
}

/*
* setValue() function is called in the callback function.This function is used to display the return value in the HTML control.In
* index.html page,the value is displayed in the "snomedcttext" textarea.The result includes- Concept id, Description-id and Description  
* param selectedSNOMEDTerm(var)-is the concept selected by the user from the search result list 
* 
*/
function setValue1(selectedSNOMEDTerm)
{
if(targetElement.name == "visitNotes") // Visit Reason
{
	var data = unescape(id);
	data = data.substring(data.indexOf("\'") + 1 , data.lastIndexOf("\'"));
	data = data.replace(" : ","#");
	if(targetElement.value=="") targetElement.value = data;
	else targetElement.value = targetElement.value + ", " + data;
}

if(targetElement.name == "progressNotes") // progressNotes
{
	var data = unescape(id);
	data = data.substring(data.indexOf("\'") + 1 , data.lastIndexOf("\'"));
	data = data.replace(" : ","#");
	if(targetElement.value=="") targetElement.value = data;
	else targetElement.value = targetElement.value + ", " + data;
}

if(targetElement.name == "instruction") // instruction
{
	var data = unescape(id);
	data = data.substring(data.indexOf("\'") + 1 , data.lastIndexOf("\'"));
	data = data.replace(" : ","#");
	if(targetElement.value=="") targetElement.value = data;
	else targetElement.value = targetElement.value + ", " + data;
}

else
{
	var data = unescape(id);
	data = data.substring(data.indexOf("\'") + 1 , data.lastIndexOf("\'"));
	targetElement.value = data;
}
$("#dialog-form").dialog( "close" );
}



function selectValue(value, callback) 
{
	
	var data = unescape(value);

	var term = data.split('###$$$');

	var returnterm_OUT = term[1] +","+ term[0].replace("'", "");

	if (typeof callback === "function")
		callback(returnterm_OUT);

	$("#dialog-form").dialog("close");

}

function setValue(selectedSNOMEDTerm)
{
	
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	var targetElement=document.getElementsByName("targetId")[0].value;
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
	
	
	if(targetElement == "visitNotes") // Visit Reason
	{
		 targetPrefferedTerm=document.getElementsByName("snomdPTVisitNotes")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdVisitNotes")[0];   //concept Id
		 document.getElementsByName("visitNote")[0].value=document.getElementsByName("visitNote")[0].value + ' ' + str;
		
	}
	 if(targetElement == "progressNotes") // progressNotes
	{
		  targetPrefferedTerm=document.getElementsByName("snomdPTProgessNotes")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomdCIdProgressNotes")[0];  //concept Id
		  document.getElementsByName("progressNote")[0].value=document.getElementsByName("progressNote")[0].value + ' ' + str;
			

	}
	 if(targetElement == "instruction") //instruction
	{
		  targetPrefferedTerm=document.getElementsByName("snomdPTInstruction")[0];  //preffered term
		  targetConceptId=document.getElementsByName("snomdCIdInstruction")[0];   //concept Id
		  document.getElementsByName("instruction")[0].value=document.getElementsByName("instruction")[0].value + ' ' + str;
		 
			
	}
	 
	    if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
		else targetPrefferedTerm.value = targetPrefferedTerm.value + "# " + str;
		
		if(targetConceptId.value=="")  targetConceptId.value = str1;
		else targetConceptId.value = targetConceptId.value + "# " + str1;
		
	    document.getElementsByName("targetId")[0].value="";
	    targetPrefferedTerm="";
	    targetConceptId="";
	
}
}



function setfreeText()
{
	document.getElementsByName("visitNote")[0].value=document.getElementsByName("txt-snomed-ct-search_1")[0].value;//for free text
	document.getElementsByName("progressNote")[0].value=document.getElementsByName("txt-snomed-ct-search_2")[0].value;
	document.getElementsByName("instruction")[0].value=document.getElementsByName("txt-snomed-ct-search_3")[0].value;
	
}

function setTarget(id)
{
	if(id=="visitNotes")
	{
	document.getElementsByName("targetId")[0].value="visitNotes";}
	if(id=="progressNotes")
	{
	document.getElementsByName("targetId")[0].value="progressNotes";}
	if(id=="instruction")
	{
	document.getElementsByName("targetId")[0].value="instruction";}
	
}

function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function showDetail(obj)
{
	document.getElementsByName("radioBtn")[0].value=obj.value;
	submitPage("GETDTL"); 
}




function validateDoctorAdd()
{
	var valid=true;
	/*if(document.getElementsByName("visitNote")[0].value=="")
	{
		alert("Please Enter the Visit Notes")
		document.getElementsByName("visitNote")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName('visitNote')[0].value.length>1000)
	 {
	 	alert("Characters in Visit Notes should be less than 1000");
	 	document.getElementsByName("visitNote")[0].focus();
	 	valid= false;
	 }
	else if(document.getElementsByName("progressNote")[0].value=="")
	{
		alert("Please Enter the Progress Notes")
		document.getElementsByName("progressNote")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName('progressNote')[0].value.length>500)
	 {
	 	alert("Characters in Progress Notes should be less than 500");
	 	document.getElementsByName("progressNote")[0].focus();
	 	valid= false;
	 }
	else if(document.getElementsByName("instruction")[0].value=="")
	{
		alert("Please Enter the Instruction")
		document.getElementsByName("instruction")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName('instruction')[0].value.length>100)
	{
		alert("Characters in Instruction should be less than 100");
		document.getElementsByName("instruction")[0].focus();
	 	valid= false;
	}*/
	var visitNote=document.getElementsByName("visitNote")[0].value;
	var progNote=document.getElementsByName("progressNote")[0].value;
	var instr=document.getElementsByName("instruction")[0].value;
	if(visitNote == "" && progNote == "" && instr == "")
	{
		alert("Please Enter At Least One Note ");
		valid= false;
	}
	else
	{
		if(visitNote !="" && visitNote.length > 1000)
		{
			alert("Characters in Visit Notes should be less than 1000");
		 	document.getElementsByName("visitNote")[0].focus();
		 	valid= false;
		}
		else if(progNote !="" && progNote.length > 500)
		{
			alert("Characters in Progress Notes should be less than 500");
		 	document.getElementsByName("progressNote")[0].focus();
		 	valid= false;
		}
		else if(instr !="" && instr.length > 1000)
		{
			alert("Characters in Instruction should be less than 1000");
			document.getElementsByName("instruction")[0].focus();
		 	valid= false;
		}
	}
	return valid;
}

function getDoctorPrevRoundDtl(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var path='/HISClinical/inpatient/doctorRound.cnt?hmode=DOCPREVROUNDDTL&patCrNo='+patCrNo;
	openPopup(createFHashAjaxQuery(path),event,400,700);
}

function addNotes(event,id)
{
	var a=id;
	var path='/HISClinical/inpatient/doctorRound.cnt?hmode=ADDNOTES&processId='+a;
	openPopup(createFHashAjaxQuery(path),event,300,600);
}
function getPrevProgress(event)
{
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var path='/HISClinical/inpatient/doctorRound.cnt?hmode=PREVIOUSNURSENOTES&patCrNo='+patCrNo;
	openPopup(createFHashAjaxQuery(path),event,400,700);
}

function validate1(mode){
	//var hisValidator = new HISValidator("DoctorRoundFB");
	var docVisitDate1=document.getElementsByName("docVisitDate")[0].value;
	var today = new Date();
	//alert("today's date :"+today);
	var millisecondsIn7Days = 7 * 24 * 60 * 60 * 1000;
	
	var sevenDaysAgo = new Date(today - millisecondsIn7Days);
	//alert("sevenDaysAgo :"+sevenDaysAgo);
	if(document.getElementsByName("docVisitDate")[0].value==""){
		alert("Please Enter Doctor Visit Date.");
		return false;
	}
    		
	docVisitDate1=convertStrToDate(docVisitDate1, "dd-Mon-yyyy");
	//alert("Doctor Visit Date :"+docVisitDate1);
	if(docVisitDate1>today){
		alert("Doctor Visit Date should not greater than Current Date");
		return false;
	}else if(docVisitDate1<sevenDaysAgo){
		alert("Doctor Visit Date should not before a weak(i.e 7 days)");
		return false;
	}
	var docVisitTimeHr=document.getElementsByName("docVisitTimeHr")[0].value;
	var docVisitTimeMin=document.getElementsByName("docVisitTimeMin")[0].value;
	
	if(docVisitTimeHr==""){
		alert("Please Enter Hr of time");
		return false;
	}else if(docVisitTimeHr>23 || docVisitTimeHr<0){
		alert("Please Enter Valid hour between 0-23");
		return false;
	}
	
	if(docVisitTimeMin==""){
		alert("Please Enter Min of time");
		return false;
	}else if(docVisitTimeMin>59 || docVisitTimeMin<0){
		alert("Please Enter Valid time between 0-59");
		return false;
	}
	/* var docVistMiliSecod=(24*60*60*1000)/(eval(docVisitTimeHr)*eval(docVisitTimeMin));
	var docVisitTime=new Date(today - docVistMiliSecod);
	alert("docVisitTime  :"+docVisitTime); */
	
	setfreeText();
	submitFormOnValidate1(validateDoctorAdd(),mode);
	
	
}

//Added by Vasu on 26.Sept.2018
function updatedata()
{
	if(document.getElementsByName("change")[0].value==1)
		{
		document.getElementById("savedata").style.display="none"; 
		document.getElementById("updatedata").style.display=""; 
		}
	else
		{
		document.getElementById("savedata").style.display=""; 
		document.getElementById("updatedata").style.display="none"; 
		}
		return true;

}

</script>
<his:TitleTag>
	<his:name>
		<bean:message key="docVisitDtl" />
	</his:name>
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true" />

<%if(session.getAttribute(InpatientConfig.ARR_UNVERIFIED_RECORD_ENTERBY_NURSE)!=null) {%>
<his:statusList>

<his:SubTitleTag name="Unverified Record">
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td>
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<a style="cursor:pointer" onclick="getDoctorPrevRoundDtl(event)" >	
								<bean:message key="prevdocrounddtl"/>
							</a>	
						</b>
					</font>
				</div>
			</td>
			<td>
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<a style="cursor:pointer" onclick="getPrevProgress(event)" >	
								<bean:message key="previousProgressByNurse"/>
							</a>	
						</b>
					</font>
				</div>
			</td>
		</tr>
	</table>	
	
</his:SubTitleTag>

<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="select"/>
					</font>
				</div>
			</td>
			<td width="30%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="entryDate"/>
					</font>
				</div>
			</td>
			<td width="50%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="enterBy"/>
					</font>
				</div>
			</td>
		</tr>
		<logic:iterate id="unverifiedRecord" name="<%=InpatientConfig.ARR_UNVERIFIED_RECORD_ENTERBY_NURSE %>" type="hisglobal.vo.DoctorRoundDtlVO" indexId="idx">
			<tr>
				<td class="tdfont" width="20%" >
					<div align="center">
						<html:radio name="DoctorRoundFB" property="radioBtn" value="<%=idx.toString() %>" onclick="showDetail(this)"></html:radio>
					</div>
				</td>
				<td class="tdfont" width="30%" >
					<div align="center">
						<%=unverifiedRecord.getEntryDate()  %>
					</div>
				</td>
				<td class="tdfont" width="50%" >
					<div align="center">
						<%=unverifiedRecord.getUserName() %>
					</div>
				</td>	
			</tr>
		</logic:iterate>
	</table>	
</his:ContentTag>
</his:statusList>

<%}%>
<his:statusTransactionInProcess>
<his:SubTitleTag name="Doctor Notes">
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td>
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<a style="cursor:pointer" onclick="getDoctorPrevRoundDtl(event)" >	
								<bean:message key="prevdocrounddtl"/>
							</a>	
						</b>
					</font>
				</div>
			</td>
			<td>
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
							<a style="cursor:pointer" onclick="getPrevProgress(event)" >	
								<bean:message key="previousProgressByNurse"/>
							</a>	
						</b>
					</font>
				</div>
			</td>
		</tr>
	</table>					
</his:SubTitleTag>
	<his:ContentTag>
		<%
			String 	sysDate=null;
			try{
				sysDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
				System.out.println("sysDate :"+sysDate);
			}catch(Exception e){
			 e.printStackTrace();
			}
		%>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<logic:notEqual name="DoctorRoundFB" property="enterBy" value="">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="enterBy"/>
							</b>
						</font>	
					</div>
				</td>
				<td width="25%"  class="tdfont" colspan="2">
					<div align="left">
						&nbsp;<bean:write name="DoctorRoundFB" property="enterBy"/>
					</div>
				</td>
				<td width="25%" class="tdfont">
				</td>
			</tr>
			</</logic:notEqual>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>Date of Visit</b>	
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
							
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<his:date name="docVisitDate" dateFormate="%d-%b-%Y" onchange="" value="<%=sysDate %>"></his:date>
					</font>							
				</td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="red" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>Time of Visit</b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<html:text name="DoctorRoundFB" property="docVisitTimeHr" size="2" maxlength="2" value="00" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>
					Hr:
					<html:text name="DoctorRoundFB" property="docVisitTimeMin" size="2" maxlength="2" value="00" onkeypress="return validateNumeric(event)" tabindex="1"></html:text>Min 	
				</td>
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="visitNote"/>
							</b>
						</font>	
					</div>
				</td>
				<td width="25%"  class="tdfont" colspan="2">
					<div align="left">
						<html:hidden name="DoctorRoundFB" property="snomdPTVisitNotes" ></html:hidden>
					    <html:hidden name="DoctorRoundFB" property="snomdCIdVisitNotes" ></html:hidden>
					    <html:hidden name="DoctorRoundFB" property="visitNote" ></html:hidden>
					
				<%-- 	<html:textarea name="DoctorRoundFB" property="visitNote" rows="2" cols="60" onkeypress="return (validateTextArea(event,this,'1000') && validateAlphaNumericOnly(event))"></html:textarea>
		 --%>		<!-- 	<img  class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('visitNotes'); selectSNOMEDCT('ACTIVE','','SYNONYMS',10,'null',callbck_index);" onclick="setTarget('visitNotes'); selectSNOMEDCT('ACTIVE','','SYNONYMS',10,'null',callbck_index);" title="Click to add SNOMED-CT Term">							
			 -->
			 
			   <div id="dialog-form_1" >
					<div id="snomed-ct-search">
				<!-- 	<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
				 -->	<textarea  id="txt-snomed-ct-search_1" class="clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_1"  tabindex="1"  style="width:75%;color:#000000;" autocomplete="off" rows="5" cols="20"  onfocus="setTarget('visitNotes');" ></textarea>
					 </div>
					 
                     			</div>
			 
			 	</div>
				</td>
				 <td width="25%" class="tdfont" >
					<%-- <div align="center">
						<html:button value="Add Visit Notes"  property="mybutton" onclick="addNotes(event,3)" style='cursor:pointer'  tabindex='1'/>
						</div> --%>
				</td> 
			</tr>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="progNote"/>
							</b>
						</font>	
					</div>
				</td>
				<td width="25%"  class="tdfont" colspan="2">
					<div align="left">
					

					
						<html:hidden name="DoctorRoundFB" property="snomdPTProgessNotes" ></html:hidden>
						<html:hidden name="DoctorRoundFB" property="snomdCIdProgressNotes" ></html:hidden>
						<html:hidden name="DoctorRoundFB" property="progressNote" ></html:hidden>
				
					<%-- 	<html:textarea  name="DoctorRoundFB" property="progressNote" rows="2" cols="60" onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"></html:textarea>
					 --%>	<!-- <img  class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('progressNotes');selectSNOMEDCT('ACTIVE','','SYNONYMS',10,'null',callbck_index)" onclick="setTarget('progressNotes'); selectSNOMEDCT('ACTIVE','','SYNONYMS', 10,'null',callbck_index);" title="Click to add SNOMED-CT Term">							
					 -->
					 
					   <div id="dialog-form_2" >
					<div id="snomed-ct-search">
				<!-- 	<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
				 -->	<textarea  id="txt-snomed-ct-search_2" class="clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_2"  tabindex="1"  style="width:75%;color:#000000;" autocomplete="off" rows="5" cols="20"  onfocus="setTarget('progressNotes');"  ></textarea>
					 </div>
					 
                     			</div>
					</div>
				</td>
				<td width="25%" class="tdfont" >
					<%-- <div align="center">
						<html:button value="Add Progress Notes"  property="mybutton" onclick="addNotes(event,1)" style='cursor:pointer'  tabindex='1'/>
					</div> --%>
				</td>
			</tr>
			
			
			
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="instruction"/>
							</b>
						</font>	
					</div>
				</td>
				<td width="25%"  class="tdfont" colspan="2">
					<div align="left">
					<html:hidden name="DoctorRoundFB" property="snomdPTInstruction" ></html:hidden>
					<html:hidden name="DoctorRoundFB" property="snomdCIdInstruction" ></html:hidden>
					<html:hidden name="DoctorRoundFB" property="instruction" ></html:hidden>
					<!-- <img class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('instruction'); selectSNOMEDCT('ACTIVE','','SYNONYMS','10','null',callbck_index);" onclick="setTarget('instruction');selectSNOMEDCT('ACTIVE','','SYNONYMS',10,'null',callbck_index);" title="Click to add SNOMED-CT Term">							
				 -->
				 
				 
				   <div id="dialog-form_3" >
					<div id="snomed-ct-search">
				<!-- 	<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
				 -->	<textarea  id="txt-snomed-ct-search_3" class="clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_3"  tabindex="1"  style="width:75%;color:#000000;" autocomplete="off" rows="5" cols="20" onfocus="setTarget('instruction');"  ></textarea>
					 </div>
					 
                     			</div>		
					</div>
				</td>
				<td width="25%" class="tdfont" >
					<%-- <div align="center">
						<html:button value="Add Instruction"  property="mybutton" onclick="addNotes(event,2)" style='cursor:pointer'  tabindex='1'/>
					</div> --%>
				</td>
			</tr>
		</table>	
	</his:ContentTag>
</his:statusTransactionInProcess>

<div id="savedata" style="display: block">
<his:ButtonToolBarTag>
	<his:statusTransactionInProcess> 
	<logic:notEqual name="DoctorRoundFB" property="hmode" value="GETDTL">
		<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "validate1('DOCTORSAVE');" onkeypress="if(event.keyCode==13)validate1('DOCTORSAVE');")>
	</logic:notEqual>	
	<logic:equal name="DoctorRoundFB" property="hmode" value="GETDTL">	
		<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "submitFormOnValidate1(validateDoctorAdd(),'DOCTORUPDATE');" onkeypress="if(event.keyCode==13)submitFormOnValidate1(validateDoctorAdd(),'DOCTORUPDATE');")>
	</logic:equal>	
	</his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
       <his:statusTransactionInProcess> 
       	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
       </his:statusTransactionInProcess> 
</his:ButtonToolBarTag>

</div>

<div id="updatedata" style="display: none;">
	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess> 
		
			<logic:notEqual name="DoctorRoundFB" property="hmode" value="GETDTL">
				<img class='button' src='<his:path src="/hisglobal/images/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "validate1('DOCTORDETAILUPDATE');" onkeypress="if(event.keyCode==13)validate1('DOCTORDETAILUPDATE');")>				
			</logic:notEqual>	
		
		<logic:equal name="DoctorRoundFB" property="hmode" value="GETDTL">	
			<img class='button' src='<his:path src="/hisglobal/images/btn-sv.png"/>'  style=cursor:pointer  tabindex='2' onclick =  "submitFormOnValidate(validateDoctorAdd(),'DOCTORUPDATE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateDoctorAdd(),'DOCTORUPDATE');")>
		</logic:equal>	
		</his:statusTransactionInProcess>
			<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	       <his:statusTransactionInProcess> 
	       	<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	       </his:statusTransactionInProcess> 
	</his:ButtonToolBarTag>
</div>

<html:hidden name="DoctorRoundFB" property="hmode"/>
<html:hidden name="DoctorRoundFB" property="patCrNo"/>
<html:hidden name="DoctorRoundFB" property="radioBtn"/>	
<html:hidden name="DoctorRoundFB" property="processId"/>
<!-- Added by Vasu on 26.Sept.2018 -->
<html:hidden name="DoctorRoundFB" property="change"/>
<html:hidden name="DoctorRoundFB" property="roundDate"/>
<html:hidden name="DoctorRoundFB" property="roundNo"/>
<html:hidden name="DoctorRoundFB" property="serialNo"/>
<!-- End Vasu -->
<input type="hidden" name="targetId" />

