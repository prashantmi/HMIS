<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.config.HISConfig"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="opd.OpdConfig"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="opd.transaction.controller.fb.OpdRosterSchedulePopupFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>


<%@page import="opd.transaction.controller.fb.OpdNextVisitDetailFB"%>
<%@page import="hisglobal.vo.EpisodeVO"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js"/>
<his:javascript src="/hisglobal/js/paginationTag.js"/>
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.8.10/themes/smoothness/jquery-ui.css" type="text/css">
<script type="text/javascript" src="//ajax.aspnetcdn.com/ajax/jquery.ui/1.8.10/jquery-ui.min.js"></script> -->

<script type="text/javascript">
<%-- var snomedServerURL = '<%=HISConfig.HIS_SNOMEDCT_SERVER_URL%>'; --%>
</script>

<script type="text/javascript" src="/HIS/appointment/js/appointment.js"></script>

<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/css/jquery-ui.css">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtool.js"></script>
<!-- <script type="text/javascript" src="/HIS/hisglobal/snomedct/js/demo.js"></script> -->
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolmultiautocomplete.js"></script>
<link href="/HIS/hisglobal/snomedct/css/searchtoolnew.css" rel="stylesheet">
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> 
<his:javascript src="/opd/js/opd_visit_summary.js"/>
<script type="text/javascript">



// SNOMED-CT Integration


// old modified on 09.06.2016
/* function selectValue(id)
{
	if(targetElement.name == "visitReason") // Visit Reason
	{
		var data = unescape(id);
		data = data.substring(data.indexOf("\'") + 1 , data.lastIndexOf("\'"));
		data = data.replace(" : ","#");
		if(targetElement.value=="") targetElement.value = data;
		else targetElement.value = targetElement.value + ", " + data;
	}
	if(targetElement.name == "episodekeywordVal") // Keywords
	{
		var data = unescape(id);
		data = data.substring(data.indexOf("\'") + 1 , data.lastIndexOf("\'"));
		var dataTxt = data.split(" : ")[1];
		var dataVal = data.split(" : ")[0];
		if(targetElement.value=="") targetElement.value = dataTxt;
		else targetElement.value = targetElement.value + ", " + dataTxt;
		
		var keywordTxt = document.getElementsByName("episodeKeyword")[0];
		var keywordVal = document.getElementsByName("episodeKeywordID")[0];
		if(keywordTxt.value=="") keywordTxt.value = dataTxt;
		else keywordTxt.value = keywordTxt.value + ", " + dataTxt;
		if(keywordVal.value=="") keywordVal.value = dataVal;
		else keywordVal.value = keywordVal.value + ", " + dataVal;
	}
	else
	{
		var data = unescape(id);
		data = data.substring(data.indexOf("\'") + 1 , data.lastIndexOf("\'"));
		targetElement.value = data;
	}
	$("#dialog-form").dialog( "close" );
}

 */
 var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	
	var callbck_index =function(ret_OUT){setValue(ret_OUT);};

  
 
window.onload = function()
{
	setEpisodeCloseDataOnLoad();
	endTreatmentForDeadPatient();
	if(callThisOnload)
		callThisOnload();
	
	if(document.getElementsByName("isConfirmed")[0].value==<%=RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED%>)
	{
	if(document.getElementsByName("keywords")[0].value!="") document.getElementById("txt-snomed-ct-search_2").value=document.getElementsByName("keywords")[0].value;
	
	if(document.getElementsByName("visitReason")[0].value!="") document.getElementById("txt-snomed-ct-search_1").value=document.getElementsByName("visitReason")[0].value;

	if(document.getElementsByName("visitNotes")[0].value!="") document.getElementById("txt-snomed-ct-search_3").value=document.getElementsByName("visitNotes")[0].value;
	
	if(document.getElementsByName("episodeSummary")[0].value!="") document.getElementById("txt-snomed-ct-search_4").value=document.getElementsByName("episodeSummary")[0].value;
	
	}
	
	//alert(document.getElementById("txt-snomed-ct-search_2").value);
	load1('1','','450970008');
	load1('2','','3310011000189109');
	load1('3','','');
	load1('4','','');
	
		
}

function load1(elmtId,semantictag,refsetId)
{
	
	//alert("inside load");
//	alert(elmtId+','+semantictag+','+refsetId);
	 if(elmtId=="1")
		{
		 id="visitReason";
		
		}
	 if(elmtId=="2")
		{
		 id="episodekeywordVal";
		}
	 if(elmtId=="3")
		{
		 id="visitNotes";
		}
	 if(elmtId=="4")
		{
		 id="episodeSummary";
		}
	 setTarget(id);
	
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	
	var callbck_index =function(ret_OUT){setValue(ret_OUT);};


	if(elmtId==null || elmtId==undefined)
		{
		elmtId="1"; semantictag="DISORDER";
	
		}
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


function setfreeText()   //for free text
{
	var str1=document.getElementsByName("txt-snomed-ct-search_1")[0].value;
	var str2=document.getElementsByName("txt-snomed-ct-search_2")[0].value;
	var str3=document.getElementsByName("txt-snomed-ct-search_3")[0].value;
	var str4=document.getElementsByName("txt-snomed-ct-search_4")[0].value;
	//alert(str4);
	var n1 = str1.endsWith(";"); 
	var n2 = str2.endsWith(";"); 
	var n3 = str3.endsWith(";"); 
	var n4 = str4.endsWith(";"); 
	
	if(n1)
		document.getElementsByName("visitReason")[0].value=str1;
	else
		{
		if(!isEmpty(str1))
		document.getElementsByName("visitReason")[0].value=str1+';';
		}
	if(n2)
		document.getElementsByName("keywords")[0].value=str2;
	else
		{
			if(!isEmpty(str2))
		    document.getElementsByName("keywords")[0].value=str2+';';
		}
	if(n3)
		document.getElementsByName("visitNotes")[0].value=str3;
	else
	{
		if(!isEmpty(str3))
	    document.getElementsByName("visitNotes")[0].value=str3+';';
	}
	if(n4)
		document.getElementsByName("episodeSummary")[0].value=str4;
	else
	{
		if(!isEmpty(str4))
	    document.getElementsByName("episodeSummary")[0].value=str4+';';
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
//		alert(selectedSNOMEDTerm);		
		/* var arr=selectedSNOMEDTerm.split("@@@");
		var str=arr[0];
		var str1=arr[1];
		 */
		 var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;
		
	
	
	if(target == "visitReason") // Visit Reason
	{
		//alert("inside visit");
		/* var data = unescape(id);
		data = data.substring(data.indexOf("\'") + 1 , data.lastIndexOf("\'"));
		data = data.replace(" : ","#");  alert(data);
		 */
		 
		 
		 targetPrefferedTerm=document.getElementsByName("snomdPTVisitReason")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdVisitReason")[0];   //concept Id
	
		 if(document.getElementsByName("visitReason")[0].value=="") document.getElementsByName("visitReason")[0].value = str;
		else document.getElementsByName("visitReason")[0].value = document.getElementsByName("visitReason")[0].value + "; " + str;
		
	}
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
		
	   // document.getElementsByName("targetId")[0].value="";
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
	
	if(id=="visitReason")
	{
	document.getElementsByName("targetId")[0].value="visitReason";}
	if(id=="episodekeywordVal")
	{
	document.getElementsByName("targetId")[0].value="episodekeywordVal";}
	if(id=="visitNotes")
	{
	document.getElementsByName("targetId")[0].value="visitNotes";}
	if(id=="episodeSummary")
	{
	document.getElementsByName("targetId")[0].value="episodeSummary";}
	
	
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
    
    //  document.getElementById(ClientsID.txtantcedentcausecptid).value = "";
    //  document.getElementById(ClientsID.txtccptid).value = "";


});


function clear(id)
{
	//alert(id); 
	var elmtid=id.split('_');
	var trgt=elmtid[1];
	var targetPrefferedTerm="";  
	 var targetConceptId="";
	 document.getElementById(id).value="";
	 if(trgt=="1")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTVisitReason")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdVisitReason")[0];   //concept Id
		 document.getElementsByName("visitReason")[0].value="";
		
		}
	 if(trgt=="2")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTKeywords")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdKeywords")[0];   //concept Id
		 document.getElementsByName("keywords")[0].value="";
		}
	 if(trgt=="3")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTVisitNotes")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomdCIdVisitNotes")[0];  //concept Id
		  document.getElementsByName("visitNotes")[0].value="";
		
		}
	 if(trgt=="4")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTEpisodeSummary")[0]; //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdEpisodeSummary")[0];  //concept Id
		 document.getElementsByName("episodeSummary")[0].value="";
		}

		    targetPrefferedTerm.value = "";
		    targetConceptId.value = "";
	 
}


// On change of Next Visit Mode
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
	openDependentPopup(createFHashAjaxQuery(path),event,600,700,'yes');
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName("hmode")[0].value = "PAGINATION";
	document.forms[0].submit();
}

/* 
 $(function() {
  //alert("HI");
 var keywords=document.getElementsByName("episodeKeywords")[0].value;
  //alert(keywords);
var availableTags =keywords.split("##");
function split( val ) { */
// return val.split( /,\s*/ );
//} 
/*
function extractLast( term ) {
return split( term ).pop();
}
$("#episodekeywordVal" )
// don't navigate away from the field on tab when selecting an item
.bind( "keydown", function( event ) {
if ( event.keyCode === $.ui.keyCode.TAB &&
$( this ).autocomplete( "instance" ).menu.active ) {
event.preventDefault();
}
})
.autocomplete({
minLength: 0,
source: function( request, response ) {
// delegate back to autocomplete, but extract the last term
response( $.ui.autocomplete.filter(
availableTags, extractLast( request.term ) ) );
},
focus: function() {
// prevent value inserted on focus
return false;
},
select: function( event, ui ) {
var terms = split( this.value );
// remove the current input
terms.pop();
// add the selected item

terms.push(ui.item.value);
 
// add placeholder to get the comma-and-space at the end
terms.push( "" );
this.value = terms.join( "," );
return false;
}
});
}); */


</script>

<form name="opdnextvisitdtl" id="opdnextvisitdtl">

	<%String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"); %>
	<html:hidden name="OpdNextVisitDetailFB" property="entryDate" value="<%=sysDate%>"/>
	
	<bean:define id="entrySeatId" name="OpdNextVisitDetailFB" property="seatId" type="java.lang.String"></bean:define>
	
	<bean:define id="unitCode" name="OpdNextVisitDetailFB" property="departmentUnitCode" type="java.lang.String"></bean:define>
	
	<his:TitleTag key="visitsumm">
	</his:TitleTag>
	
	
	<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
	
	<his:statusTransactionInProcess>

		
		<%
			PaginationFB fbPage= new PaginationFB();
			pageContext.setAttribute("fbPagination", fbPage);
			fbPage.setCurrentPage(((OpdNextVisitDetailFB)request.getAttribute("OpdNextVisitDetailFB")).getCurrentPage());
			fbPage.setObjArrName(OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST);
			fbPage.setAppendInTitle("Previous Visit Detail");
			fbPage.setMaxRecords(5);
			fbPage.setMaxPages(3);
			fbPage.setTitleRequired(false);
			
			fbPage.setBOverviewRequired(true);
			fbPage.setArrOverviewFields(new String[]{"EpisodeVisitNo","EpisodeDate"});
			
			List lstPreviousVisits = (List)session.getAttribute(OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST); 
			if(lstPreviousVisits.size()>0)
			{
		%>

		<his:PaginationTag name="fbPagination"></his:PaginationTag>
		
		
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<logic:empty name="<%=OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST%>">
					<tr>
						<td width="100%" class="tdfont" nowrap valign="top">
							<div align="center">
								<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>No Previous Visit Detail Found</b>
								</font>
							</div>		
						</td>
					</tr>
				</logic:empty>
				<logic:notEmpty name="<%=OpdConfig.OPD_ESSENTIAL_ALL_VISIT_OF_EPISODE_LIST%>">
				<tr>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="visitNo"/></b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="visitDate"/></b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="progNote"/></b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="reasonOfVisit"/></b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="plannedVisit"/></b>
							</font>
						</div>
					</td>
				</tr>				
				
				<%
					int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
					int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
					
					for(int i=startIndex;i<=endIndex;i++)
					{
						EpisodeVO prevVisitDtl = (EpisodeVO) lstPreviousVisits.get(i);
				%>
					<tr>
						<td width="10%" class="tdfont" nowrap style="vertical-align: middle;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><%=prevVisitDtl.getEpisodeVisitNo()%></b>
								</font>
							</div>
						</td>
						
						<td width="25%" class="tdfont" nowrap style="vertical-align: middle;">
							<div align="center">
      	  						<%
								String method ="openDynamicVisitSummary(event,'patCrNo="+prevVisitDtl.getPatCrNo()+"&episodeCode="+prevVisitDtl.getEpisodeCode()+"&episodeVisitNo="+prevVisitDtl.getEpisodeVisitNo()+"')"; 
									/*ServletsUtilityConfig.SERVLET_DISPLAY_FILE_URL + "?" 
										+ ServletsUtilityConfig.FILE_PATH_WINDOWS + "=" + Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS + "&" 
										+ ServletsUtilityConfig.FILE_PATH_LINUX + "=" + Config.PATIENT_PROFILE_STORAGE_PATH_LINUX + "&" 
										+ ServletsUtilityConfig.FILE_NAME + "=" + prevVisitDtl.getPatCrNo().trim() + "_" + prevVisitDtl.getEpisodeDate().trim() + ".htm";*/ 
  								%>
   								<a style="cursor:pointer" onclick="<%=method%>" >
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b><%=prevVisitDtl.getEpisodeDate()%></b>
									</font>
   								</a>
							</div>
						</td>
						<td width="25%" class="tdfont" style="vertical-align: middle;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%	if(prevVisitDtl.getComplainDetail()!=null)	{	%>
									<img name="imgProgressNotes" class="button" src='<his:path src="/hisglobal/images/icon-vrf.png"/>' style='cursor:pointer' title="Progress Notes" onclick ="showProgressNotesInPopup(event,<%=Integer.toString(i-startIndex) %>)" onkeypress="if(event.keyCode==13) showProgressNotesInPopup(event,<%=Integer.toString(i-startIndex)%>);">								
																	
									<%	}	 %>
									<input type="hidden" name="prevProgressNotesForPopup" value="<%=prevVisitDtl.getComplainDetail()%>"/>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont" nowrap style="vertical-align: middle;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><%=((prevVisitDtl.getVisitReason()==null)?"":prevVisitDtl.getVisitReason())%></b>
									
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont" nowrap style="vertical-align: middle;">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><%=((prevVisitDtl.getEpisodeNextVisitDate()==null)?"":prevVisitDtl.getEpisodeNextVisitDate())+" "+((prevVisitDtl.getNextVisitDuration()==null)?"":prevVisitDtl.getNextVisitDuration())+" "+((prevVisitDtl.getNextVisitCriteria()==null)?"":OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(prevVisitDtl.getNextVisitCriteria())])%></b>
								</font>
							</div>
						</td>
					</tr>					
					
				<%	} %>
				</logic:notEmpty>
			</table>
		</his:ContentTag>
		<%	} %>
	
	
	<logic:equal name="OpdNextVisitDetailFB" property="isConfirmed" value="<%=RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED%>">
		<his:SubTitleTag key="visitDetails">
		</his:SubTitleTag>
		<logic:notPresent name="OpdNextVisitDetailFB" property="triageDuration" >
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
				<td width="25%" class="tdfonthead" ><!-- rowspan="2" -->
					<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>&nbsp;<bean:message key="reasonOfVisit"/></b>								
							</font>
						</div>
					</td>
					<%-- <td width="10%" class="tdfonthead" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>&nbsp;<bean:message key="clinical"/></b>								
							</font>
						</div>
					</td> --%>
					
					<td width="75%" class="tdfont" style="vertical-align: middle;">
						<div align="left">
							<html:hidden name="OpdNextVisitDetailFB" property="snomdPTVisitReason" ></html:hidden>
					        <html:hidden name="OpdNextVisitDetailFB" property="snomdCIdVisitReason" ></html:hidden>
						 <html:hidden name="OpdNextVisitDetailFB" property="visitReason" ></html:hidden>
							<%-- <html:text name="OpdNextVisitDetailFB" property="visitReason" styleId="visitReason" tabindex="1" maxlength="150" size="50" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;"/>
							<img class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('visitReason'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','200',callbck_index)" onclick="setTarget('visitReason'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','200',callbck_index)" title="Click to add SNOMED-CT Term">							
						 --%>	
						 
						 		
				   <div id="dialog-form_1" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_1"  style="width:65%;color:#000000;" type="text"  onfocus="load1('1','','450970008');">
					</div>
					 <div id="norecorddiv_1">
					 <label style="display: inline;" id="reccnt">No. of records : </label>
					<span style="display: inline;" id="reccount" ></span>
					<label style="display: none;" id="nosuggestion">No suggestions found</label>
					<label style="display: none;" id="norec">No results found</label>
                     <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
                 
				    </div>         
                     <div class="concept" id="conceptdiv_1">                 
                    </div>
                         </div>
						</div>
					</td>
				</tr>
				<tr style="display:none">
					<td width="10%" class="tdfonthead"  >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<%-- <b>&nbsp;<bean:message key="keywords"/></b> --%>
								<b>&nbsp;<bean:message key="accidental"/></b>
								
								<%--
								<b>&nbsp;<bean:message key="chiefComplaints"/></b>		
								--%>						
							</font>
						</div>
					</td>
					<td width="65%" class="tdfont" style="vertical-align: middle;" >
						<div align="left">
						  <html:hidden name="OpdNextVisitDetailFB" property="snomdPTKeywords" ></html:hidden>
					        <html:hidden name="OpdNextVisitDetailFB" property="snomdCIdKeywords" ></html:hidden>
					        <html:hidden name="OpdNextVisitDetailFB" property="keywords" ></html:hidden>
						<%-- 	<input id="episodekeywordVal" size="50" name="keywordVal" value="<%=request.getAttribute("keywordData")%>"  maxlength="99" onkeypress="return chiefComplaintsLen()">
						 --%>	
						<%--  <html:text name="OpdNextVisitDetailFB" property="keywords" tabindex="1" maxlength="99" size="50" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;"/>
					
						 <img class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('episodekeywordVal'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','200',callbck_index);" onclick="setTarget('episodekeywordVal');selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','200',callbck_index);" title="Click to add SNOMED-CT Term">							
					 --%>
					 		
							<%--
							<html:text name="OpdNextVisitDetailFB" property="episodeKeywords" tabindex="1" maxlength="50" size="50" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;"/>
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<img class="button" src='<his:path src="/hisglobal/images/AddMacro.png"/>' title="Keyword Suggestions" style="cursor: pointer;vertical-align: middle;" tabindex="1" onclick="openUnitKeywords(event)" onkeypress="if(event.keyCode==13) openUnitKeywords(event)">
							--%>
							
							
							  <div id="dialog-form_2" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_2" class="clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_2"   style="width:65%;color:#000000;" type="text" onfocus="load1('2','','3310011000189109');">
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
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font>&nbsp;<bean:message key="endtreatment"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" colspan="2">
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" value="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="yes"/>
						&nbsp;&nbsp;
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" value="<%=RegistrationConfig.EPISODE_ISOPEN_TRUE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="no"/>
					</td>
				</tr>
			</table>
			
			<div id="divNextDateModes" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr valign="middle">
						<td width="25%" class="tdfonthead" valign="middle">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><font color="#FF0000">*</font>&nbsp;<bean:message key="plannedVisit"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" valign="middle">
							<div align="left">
								
									<logic:equal name="OpdNextVisitDetailFB" property="patNextAptNo" value="">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria"  value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria"  value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria"  value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
								</font>
								</logic:equal>
									<logic:notEqual name="OpdNextVisitDetailFB" property="patNextAptNo" value="">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria"  disabled="true" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" disabled="true" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria"  value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
								</font>
								</logic:notEqual>		
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDays" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr valign="middle">
						<td width="25%" class="tdfonthead" valign="middle"></td>
						<td width="75%" class="tdfont" valign="middle">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:text name="OpdNextVisitDetailFB" property="nextVisitDuration" tabindex="1" maxlength="2" size="5" onkeypress="return validatePositiveIntegerOnly(this,event)"/>
									<html:select name="OpdNextVisitDetailFB" property="nextVisitDurationCriteria" tabindex="1">
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
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDate" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
							<td width="75%" class="tdfont" style="vertical-align: middle;">
								<div align="left">
								
									<logic:equal name="OpdNextVisitDetailFB" property="patNextAptNo" value="">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									 <html:text name="OpdNextVisitDetailFB" property="episodeNextVisitDate" tabindex="1" size="15" readonly="true" style="vertical-align: middle;" />
									<img class="button" src='<his:path src="/hisglobal/images/scheduler.png"/>' style="cursor:pointer;vertical-align: middle;" 
										onkeypress="if(event.keyCode==13) getSchedule(event)" onclick="getSchedule(event)" title="Click to Select Schedule Date">
									</font>
								</logic:equal>
								
									<logic:notEqual name="OpdNextVisitDetailFB" property="patNextAptNo" value="">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="nextAppointment"/>
								<bean:write name="OpdNextVisitDetailFB" property="patNextAptNo" />
								<bean:write name="OpdNextVisitDetailFB" property="episodeNextVisitDate" />
								<bean:write name="OpdNextVisitDetailFB" property="patNextAptSlot" />
									
						<html:hidden name="OpdNextVisitDetailFB" property="episodeNextVisitDate"  />
						<html:hidden name="OpdNextVisitDetailFB" property="patNextAptNo" />
						<html:hidden name="OpdNextVisitDetailFB" property="patNextAptSlot" />
						
								<%-- 	<html:text name="OpdNextVisitDetailFB" property="episodeNextVisitDate" tabindex="1" size="15" readonly="true" style="vertical-align: middle;" />
							<bean:message key="nextAppointmentNo"/>
						
							 	<html:text name="OpdNextVisitDetailFB" property="patNextAptNo" tabindex="1" size="15" readonly="true" style="vertical-align: middle;" />
									<bean:message key="nextAptSlot"/>
								 	<html:text name="OpdNextVisitDetailFB" property="patNextAptSlot" tabindex="1" size="15" readonly="true" style="vertical-align: middle;" />
								--%></font>
								</logic:notEqual>
																					
								</div>
								<!-- Added by Singaravelan on 23-Mar-2015 for Apt Tag Integration  -->
								<div id="aptTagRow" style="display:none;"></div>												
							</td>
					</tr>
				</table>
			</div>
 
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" style="vertical-align: middle;">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="progNote"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" style="vertical-align: middle;">
						<div align="left">
							<html:hidden name="OpdNextVisitDetailFB" property="snomdPTVisitNotes" ></html:hidden>
					        <html:hidden name="OpdNextVisitDetailFB" property="snomdCIdVisitNotes" ></html:hidden>
					         <html:hidden name="OpdNextVisitDetailFB" property="visitNotes" ></html:hidden>
					         <%--  <html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="visitNotes" tabindex="1" style="vertical-align: middle;" 
								onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
						 --%>
						
						<!--  <input type="hidden" name="ctl00$ContentPlaceHolder1$txtccptid" id="ContentPlaceHolder1_txtccptid" />
				 
					
						<textarea name="ctl00$ContentPlaceHolder1$txtimmediatecause" rows="3" cols="20" id="ContentPlaceHolder1_txtimmediatecause" class="clearable ui-autocomplete-input" value="" onfocus="selectSNOMEDCTmulti(ClientsID.txtimmediatecause,'active','disorder','preferredexcludingfsn',50,callbck_index1);" style="width:48%;" autocomplete="off"></textarea>
					 -->	
						
							<!-- 	<img  class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('visitNotes');selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index)" onclick="setTarget('visitNotes'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index);" title="Click to add SNOMED-CT Term">							
	 -->
	 
	 		      	
					<textarea  maxLength="900" name="txt-snomed-ct-search_3" rows="3" cols="20" id="txt-snomed-ct-search_3" class="clearable ui-autocomplete-input x onX" value=""  onfocus="load1('3','','');" style="width:58%;" autocomplete="off"></textarea>
						
					
	 	
				    <!-- <div id="dialog-form_3" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_3" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_3"  style="width:65%;color:#000000;" type="text" onfocus="setTarget('visitNotes');">
					</div>
					 <div id="norecorddiv_3">
					 <label style="display: inline;" id="reccnt">No. of records : </label>
					<span style="display: inline;" id="reccount" ></span>
					<label style="display: none;" id="nosuggestion">No suggestions found</label>
					<label style="display: none;" id="norec">No results found</label>
                     <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
                 
				    </div>         
                     <div class="concept" id="conceptdiv_3">                 
                    </div>
                         </div> -->
					
					
						<% String onClickMacroButton="openVisitNotesMacros('"+OpdConfig.MACRO_PROCESS_ID_VISIT_SUMMARY+"','"+unitCode+"',event)"; %>
						
					
						 
							<img class="button" src='<his:path src="/hisglobal/images/AddMacro.png"/>' title="Visit Notes Suggestions" style="cursor: pointer;vertical-align: middle;" tabindex="1" onclick="<%=onClickMacroButton%>" onkeypress="if(event.keyCode==13) <%=onClickMacroButton%>"> 
						
						
						</div>
					</td>
				</tr>
			</table>
			<div id="divEpisodeSummary" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle;">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="episode"/> <bean:message key="summary"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle;">
							<div align="left">
							
								<html:hidden name="OpdNextVisitDetailFB" property="snomdPTEpisodeSummary" ></html:hidden>
					        <html:hidden name="OpdNextVisitDetailFB" property="snomdCIdEpisodeSummary" ></html:hidden>
				 <html:hidden name="OpdNextVisitDetailFB" property="episodeSummary" ></html:hidden>
							<%-- 
								<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="episodeSummary" tabindex="1" 
									onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
									<img  class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('episodeSummary');selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index)" onclick="setTarget('episodeSummary'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index);" title="Click to add SNOMED-CT Term">							
	 --%>
	 
	 
	 	<textarea name="txt-snomed-ct-search_4" rows="3" cols="20" id="txt-snomed-ct-search_4" class="clearable ui-autocomplete-input x onX" value=""  onfocus="load1('4','','');" style="width:58%;" autocomplete="off"></textarea>
						
				
	 <!--     <div id="dialog-form_4" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_4" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_4"  style="width:65%;color:#000000;" type="text" onfocus="setTarget('episodeSummary');">
					</div>
					 <div id="norecorddiv_4">
					 <label style="display: inline;" id="reccnt">No. of records : </label>
					<span style="display: inline;" id="reccount" ></span>
					<label style="display: none;" id="nosuggestion">No suggestions found</label>
					<label style="display: none;" id="norec">No results found</label>
                     <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
                 
				    </div>         
                     <div class="concept" id="conceptdiv_4">                 
                    </div>
                         </div> -->
							</div>
						</td>
					</tr>
				</table>
			</div>
		</his:ContentTag>
		</logic:notPresent>

		<logic:present name="OpdNextVisitDetailFB" property="triageDuration" >

		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr >
					<td width="25%" class="tdfonthead" ><!-- rowspan="2" --> 
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>&nbsp;<bean:message key="reasonOfVisit"/></b>								
							</font>
						</div>
					</td>
					
					<%-- <td width="10%" class="tdfonthead" rowspan="2" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>&nbsp;<bean:message key="clinical"/></b>								
							</font>
						</div>
					</td> --%>
					<td width="75%" class="tdfont" style="vertical-align: middle;">
						<div align="left">
							<html:text name="OpdNextVisitDetailFB" property="visitReason" tabindex="1" maxlength="50" size="50" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;"/>											
						</div>
					</td>
				</tr>
				<tr style="display:none">
					<td width="10%" class="tdfonthead" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
							<%-- 	<b>&nbsp;<bean:message key="keywords"/></b>
							 --%><b>&nbsp;<bean:message key="accidental"/></b>	
								<%--
								<b>&nbsp;<bean:message key="chiefComplaints"/></b>	
								--%>							
							</font>
						</div>
					</td>
					<td width="65%" class="tdfont" style="vertical-align: middle;">
						<div align="left">
							<input id="episodekeywordVal" size="50" name="keywordVal" value="<%=request.getAttribute("keywordData")%>"  maxlength="99" onkeypress="return chiefComplaintsLen()">
							
							<%--
							<html:text name="OpdNextVisitDetailFB" property="episodeKeywords" tabindex="1" maxlength="50" size="50" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;"/>
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<img class="button" src='<his:path src="/hisglobal/images/AddMacro.png"/>' title="Keyword Suggestions" style="cursor: pointer;vertical-align: middle;" tabindex="1" onclick="openUnitKeywords(event)" onkeypress="if(event.keyCode==13) openUnitKeywords(event)">
							--%>							
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font>&nbsp;<bean:message key="endtreatment"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" colspan="2">
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" value="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>" onchange="setEpisodeCloseData();" disabled="true" ></html:radio><bean:message key="yes"/>
						&nbsp;&nbsp;
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" value="<%=RegistrationConfig.EPISODE_ISOPEN_TRUE%>" onchange="setEpisodeCloseData();" disabled="true" ></html:radio><bean:message key="no"/>
					</td>
				</tr>
			</table>
			
			<div id="divNextDateModes" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle;">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><font color="#FF0000">*</font>&nbsp;<bean:message key="plannedVisit"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle;">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();" disabled="true" ></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();" disabled="true"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();" disabled="true"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDays" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
						<td width="75%" class="tdfont" style="vertical-align: middle;">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:text name="OpdNextVisitDetailFB" property="nextVisitDuration" tabindex="1" maxlength="2" size="5" onkeypress="return validatePositiveIntegerOnly(this,event)" disabled="true" />
									<html:select name="OpdNextVisitDetailFB" property="nextVisitDurationCriteria" tabindex="1" disabled="true" >
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
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDate" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
							<td width="75%" class="tdfont" style="vertical-align: middle;">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<html:text name="OpdNextVisitDetailFB" property="episodeNextVisitDate" tabindex="1" size="15" disabled="true" />
									
									</font>
								</div>				
							</td>
					</tr>
				</table>
			</div>

			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" style="vertical-align: middle;">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="progNote"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" style="vertical-align: middle;">
						<div align="left">
							<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="visitNotes" tabindex="1" disabled="true"
								onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
						</div>
					</td>
				</tr>
			</table>
			<div id="divEpisodeSummary" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle;">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="episode"/> <bean:message key="summary"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle;">
							<div align="left">
							
							
								<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="episodeSummary" tabindex="1" disabled="true" 
									onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</his:ContentTag>
		
		</logic:present>
	</logic:equal>
	
	<logic:equal name="OpdNextVisitDetailFB" property="isConfirmed" value="<%=RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED%>">
		<his:SubTitleTag key="visitDetails">			
		</his:SubTitleTag>

		<logic:notEmpty name="<%=OpdConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST%>">
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="progNote"/></b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="plannedVisit"/></b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="visitNo"/></b>
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;" nowrap="nowrap" valign="top">
						<div align="center">
							<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="enteredBy"/></b>
							</font>
						</div>
					</td>
				</tr>				
				
				<logic:iterate id="prevSummDtl" name="<%=OpdConfig.OPD_ESSENTIAL_ALL_VISIT_SUMMARY_OF_CURRENT_EPISODE_VISIT_LIST%>" type="hisglobal.vo.EpisodeSummaryDetailVO">
					<tr>
						<td width="40%" class="tdfont" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="prevSummDtl" property="visitNotes"/></b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont" nowrap="nowrap" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="prevSummDtl" property="episodeNextVisitDate"/> <bean:write name="prevSummDtl" property="nextVisitDuration"/> <%=(prevSummDtl.getNextVisitCriteria()==null)?"":OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(prevSummDtl.getNextVisitCriteria())]%></b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont" nowrap="nowrap" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="prevSummDtl" property="episodeVisitNo"/></b>
								</font>
							</div>
						</td>
						<td width="20%" class="tdfont" nowrap="nowrap" valign="top">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="prevSummDtl" property="empName"/></b>
								</font>
							</div>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>
		</logic:notEmpty>

		<logic:equal name="OpdNextVisitDetailFB" property="loginSeatId" value="<%=entrySeatId%>">
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr >
					<td width="25%" class="tdfonthead"> <!-- rowspan="2"  -->
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>&nbsp;<bean:message key="reasonOfVisit"/></b>								
							</font>
						</div>
					</td>
					<%-- <td width="10%" class="tdfonthead" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>&nbsp;<bean:message key="clinical"/></b>								
							</font>
						</div>
					</td> --%>
					<td width="75%" class="tdfont" style="vertical-align: middle;">
						<div align="left">
						
								<html:hidden name="OpdNextVisitDetailFB" property="snomdPTVisitReason" ></html:hidden>
					        <html:hidden name="OpdNextVisitDetailFB" property="snomdCIdVisitReason" ></html:hidden>
					    <html:hidden name="OpdNextVisitDetailFB" property="visitReason" ></html:hidden>
					 
				<%-- 	<html:text name="OpdNextVisitDetailFB" property="visitReason" tabindex="1" maxlength="50" size="50" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;"/>											
				 --%>		<%-- 		<img class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('visitReason'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','200',callbck_index)" onclick="setTarget('visitReason'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','200',callbck_index)"" title="Click to add SNOMED-CT Term">							
					 --%>
					 
					 
					 
					  <div id="dialog-form_1" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_1" class="clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_1"  style="width:65%;color:#000000;" type="text"  onfocus="load1('1','','450970008');">
					</div>
					 <div id="norecorddiv_1">
					 <label style="display: inline;" id="reccnt">No. of records : </label>
					<span style="display: inline;" id="reccount" ></span>
					<label style="display: none;" id="nosuggestion">No suggestions found</label>
					<label style="display: none;" id="norec">No results found</label>
                     <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
                 
				    </div>         
                     <div class="concept" id="conceptdiv_1">                 
                    </div>
                         </div>
					
					 
						</div>
					</td>
				</tr>
				<tr style="display:none">
					<td width="10%" class="tdfonthead"  >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<%-- <b>&nbsp;<bean:message key="keywords"/></b> --%>
								<b>&nbsp;<bean:message key="accidental"/></b>
								
								<%--
								<b>&nbsp;<bean:message key="chiefComplaints"/></b>	
								--%>	
							</font>
						</div>
					</td>
					<td width="65%" class="tdfont" style="vertical-align: middle;">
					
					  <html:hidden name="OpdNextVisitDetailFB" property="snomdPTKeywords" ></html:hidden>
					        <html:hidden name="OpdNextVisitDetailFB" property="snomdCIdKeywords" ></html:hidden>
				    <html:hidden name="OpdNextVisitDetailFB" property="keywords" ></html:hidden>
				
				<%--   <html:text name="OpdNextVisitDetailFB" property="keywords" tabindex="1" maxlength="99" size="50" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;"/>
				 --%>	
					
				
					<%-- 		<input id="episodekeywordVal" size="50" name="keywordVal" value="<%=request.getAttribute("keywordData")%>"  maxlength="99" onkeypress="return chiefComplaintsLen()">
					 --%>		
						 <%--  <html:hidden name="OpdNextVisitDetailFB" property="keywords" ></html:hidden>
					 --%>  
							<%--	<img class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('episodekeywordVal'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','200',callbck_index)" onclick="setTarget('episodekeywordVal'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','200',callbck_index)"" title="Click to add SNOMED-CT Term">							
						 --%>
						 
						  <div id="dialog-form_2" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_2" class="clearable ui-autocomplete-input x onX" name="txt-snomed-ct-search_2"  style="width:65%;color:#000000;" type="text"   onfocus="load1('2','','3310011000189109');">
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
					
						 
						 
							
							<%--
							<html:text name="OpdNextVisitDetailFB" property="episodeKeywords" tabindex="1" maxlength="50" size="50" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;"/>
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<img class="button" src='<his:path src="/hisglobal/images/AddMacro.png"/>' title="Keyword Suggestions" style="cursor: pointer;vertical-align: middle;" tabindex="1" onclick="openUnitKeywords(event)" onkeypress="if(event.keyCode==13) openUnitKeywords(event)">
							--%>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead"  >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font>&nbsp;<bean:message key="endtreatment"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" colspan="2">
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" value="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="yes"/>
						&nbsp;&nbsp;
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" value="<%=RegistrationConfig.EPISODE_ISOPEN_TRUE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="no"/>
					</td>
				</tr>
			</table>
			
			<div id="divNextDateModes" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle;">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><font color="#FF0000">*</font>&nbsp;<bean:message key="plannedVisit"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle;">
							<div align="left">
							<logic:equal name="OpdNextVisitDetailFB" property="patNextAptNo" value="">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria"  value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria"  value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria"  value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
								</font>
								</logic:equal>
									<logic:notEqual name="OpdNextVisitDetailFB" property="patNextAptNo" value="">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria"  disabled="true" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" disabled="true" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria"  value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
								</font>
								</logic:notEqual>						
							
								<%-- <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
								</font> --%>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDays" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
						<td width="75%" class="tdfont" style="vertical-align: middle;">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:text name="OpdNextVisitDetailFB" property="nextVisitDuration" tabindex="1" maxlength="2" size="5" onkeypress="return validatePositiveIntegerOnly(this,event)"/>
														
									
									<html:select name="OpdNextVisitDetailFB" property="nextVisitDurationCriteria" tabindex="1">
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
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDate" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
						<td width="75%" class="tdfont" style="vertical-align: middle;">
							<div align="left">
							
							<logic:equal name="OpdNextVisitDetailFB" property="patNextAptNo" value="">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
									 <html:text name="OpdNextVisitDetailFB" property="episodeNextVisitDate" tabindex="1" size="15" readonly="true" style="vertical-align: middle;" />
									<img class="button" src='<his:path src="/hisglobal/images/scheduler.png"/>' style="cursor:pointer;vertical-align: middle;" 
										onkeypress="if(event.keyCode==13) getSchedule(event)" onclick="getSchedule(event)" title="Click to Select Schedule Date">
									</font>
								</logic:equal>
								
									<logic:notEqual name="OpdNextVisitDetailFB" property="patNextAptNo" value="">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="nextAppointment"/>
								<bean:write name="OpdNextVisitDetailFB" property="patNextAptNo" />
								<bean:write name="OpdNextVisitDetailFB" property="episodeNextVisitDate"/>
								<bean:write name="OpdNextVisitDetailFB" property="patNextAptSlot" />
								<html:hidden name="OpdNextVisitDetailFB" property="episodeNextVisitDate"  />
								<html:hidden name="OpdNextVisitDetailFB" property="patNextAptNo" />
								<html:hidden name="OpdNextVisitDetailFB" property="patNextAptSlot" />
						</font>
								</logic:notEqual>
								<%-- 
								<html:text name="OpdNextVisitDetailFB" property="episodeNextVisitDate" tabindex="1" size="15" readonly="true" style="vertical-align: middle;" />
								<bean:message key="nextAppointmentNo"/>
							 	<html:text name="OpdNextVisitDetailFB" property="patNextAptNo" tabindex="1" size="15" readonly="true" style="vertical-align: middle;" />
									<bean:message key="nextAptSlot"/>
								<html:text name="OpdNextVisitDetailFB" property="patNextAptSlot" tabindex="1" size="15" readonly="true" style="vertical-align: middle;" />
							 --%>		
								
								<%-- <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="nextAppointmentDate"/>
								<html:text name="OpdNextVisitDetailFB" property="episodeNextVisitDate" tabindex="1" size="15" readonly="true" style="vertical-align: middle;" />
								<bean:message key="nextAppointmentNo"/>
									<html:text name="OpdNextVisitDetailFB" property="patNextAptNo" tabindex="1" size="15" readonly="true" style="vertical-align: middle;" />
									<bean:message key="nextAptSlot"/>
									<html:text name="OpdNextVisitDetailFB" property="patNextAptSlot" tabindex="1" size="15" readonly="true" style="vertical-align: middle;" />
								 --%>	
									<%-- <html:text name="OpdNextVisitDetailFB" property="episodeNextVisitDate" tabindex="1" size="15" readonly="true" style="vertical-align: middle;" />
									<img class="button" src='<his:path src="/hisglobal/images/scheduler.png"/>' style="cursor:pointer;vertical-align: middle;" 
										onkeypress="if(event.keyCode==13) getSchedule(event)" onclick="getSchedule(event)" title="Click to Select Schedule Date">
							 --%>	
							</div>				
							<div id="aptTagRow" style="display:none;"></div>											
							
						</td>
					</tr>
				</table>
			</div>

			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" style="vertical-align: middle;">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="progNote"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" style="vertical-align: middle;">
						<div align="left">
						
                         	<html:hidden name="OpdNextVisitDetailFB" property="snomdPTVisitNotes" ></html:hidden>
					        <html:hidden name="OpdNextVisitDetailFB" property="snomdCIdVisitNotes" ></html:hidden>
					        <html:hidden name="OpdNextVisitDetailFB" property="visitNotes" ></html:hidden>
				<%-- 	 <html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="visitNotes" tabindex="1" style="vertical-align: middle;" 
								onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))" />
				 --%>		
						<%--		<img  class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('visitNotes');selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index)" onclick="setTarget('visitNotes'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index);" title="Click to add SNOMED-CT Term">							
	 --%>
	 
	 
	 	<textarea name="txt-snomed-ct-search_3" rows="3"  cols="20" id="txt-snomed-ct-search_3" class="clearable ui-autocomplete-input x onX"    onfocus="load1('3','','');" style="width:58%;" autocomplete="off"></textarea>
						
				

					
							
							<%-- <% String onClickMacroButton2 ="openVisitNotesMacros('"+OpdConfig.MACRO_PROCESS_ID_VISIT_SUMMARY+"','"+unitCode+"',event)"; %>
							<img class="button" src='<his:path src="/hisglobal/images/AddMacro.png"/>' title="Visit Notes Suggestions" style="cursor: pointer;vertical-align: middle;" tabindex="1" onclick="<%=onClickMacroButton2%>" onkeypress="if(event.keyCode==13) <%=onClickMacroButton2%>"> --%>

						
						
							
						
						</div>
					</td>
				</tr>
			</table>
			<div id="divEpisodeSummary" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle;">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="episode"/> <bean:message key="summary"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle;">
							<div align="left">
							
									<html:hidden name="OpdNextVisitDetailFB" property="snomdPTEpisodeSummary" ></html:hidden>
					        <html:hidden name="OpdNextVisitDetailFB" property="snomdCIdEpisodeSummary" ></html:hidden>
					         <html:hidden name="OpdNextVisitDetailFB" property="episodeSummary" ></html:hidden>
					        
				      <%--  	<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="episodeSummary" tabindex="1" 
									onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
					 --%>			<%--	<img  class="button" src='/HIS/hisglobal/images/snomedct.png' style="cursor:pointer;vertical-align: middle;"
											onkeypress="if(event.keyCode==13) setTarget('episodeSummary');selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index)" onclick="setTarget('episodeSummary'); selectSNOMEDCT('ACTIVE','','PREFERRED_EXCLUDING_FSN','50',callbck_index);" title="Click to add SNOMED-CT Term">							
	
				 --%>	
				 	<textarea name="txt-snomed-ct-search_4" rows="3"    cols="20" id="txt-snomed-ct-search_4" class="clearable ui-autocomplete-input x onX"   onfocus="load1('4','','');" style="width:58%;" autocomplete="off"></textarea>
						
	
				 <!--  <div id="dialog-form_4" >
					<div id="snomed-ct-search">
					<span class="ui-helper-hidden-accessible" aria-live="polite" role="status"></span>
					<input autocomplete="off" placeholder="Enter 3 characters to search..." id="txt-snomed-ct-search_4" class="searchText ui-autocomplete-input" name="txt-snomed-ct-search_4"  style="width:65%;color:#000000;" type="text" onfocus="setTarget('episodeSummary');">
					</div>
					 <div id="norecorddiv_4">
					 <label style="display: inline;" id="reccnt">No. of records : </label>
					<span style="display: inline;" id="reccount" ></span>
					<label style="display: none;" id="nosuggestion">No suggestions found</label>
					<label style="display: none;" id="norec">No results found</label>
                     <label style="display: none;" id="msg3chars">Please enter atleast 3 characters</label>
                 
				    </div>         
                     <div class="concept" id="conceptdiv_4">                 
                    </div>
                         </div> -->
					
							</div>
						</td>
					</tr>
				</table>
			</div>
		</his:ContentTag>
		</logic:equal>
		
		<logic:notEqual name="OpdNextVisitDetailFB" property="loginSeatId" value="<%=entrySeatId%>">
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr >
					<td width="25%" class="tdfonthead"> <!--  rowspan="2" --> 
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>&nbsp;<bean:message key="reasonOfVisit"/></b>								
							</font>
						</div>
					</td>
					
					<%-- <td width="10%" class="tdfonthead" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
								<b>&nbsp;<bean:message key="clinical"/></b>								
							</font>
						</div>
					</td> --%>
					<td width="75%" class="tdfont" style="vertical-align: middle;">
						<div align="left">
							<html:text name="OpdNextVisitDetailFB" property="visitReason" tabindex="1" maxlength="50" size="50" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;"/>											
						</div>
					</td>
				</tr>
				<tr style="display: none;">
					<td width="10%" class="tdfonthead" >
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
							<%-- 	<b>&nbsp;<bean:message key="keywords"/></b> --%>
								<b><bean:message key="accidental"/></b>
								<%--
								<b>&nbsp;<bean:message key="chiefComplaints"/></b>	
								--%>							
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" style="vertical-align: middle;">
						<div align="left">
							<input id="episodekeywordVal" size="50" name="keywordVal" value="<%=request.getAttribute("keywordData")%>"  maxlength="99" onkeypress="return chiefComplaintsLen()">
							
							<%--
							<html:text name="OpdNextVisitDetailFB" property="episodeKeywords" tabindex="1" maxlength="50" size="50" onkeypress="return validateAlphaNumericOnly(event)" style="vertical-align: middle;"/>
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<img class="button" src='<his:path src="/hisglobal/images/AddMacro.png"/>' title="Keyword Suggestions" style="cursor: pointer;vertical-align: middle;" tabindex="1" onclick="openUnitKeywords(event)" onkeypress="if(event.keyCode==13) openUnitKeywords(event)">
							--%>							
						</div>
					</td>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" >
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><font color="#FF0000">*</font>&nbsp;<bean:message key="endtreatment"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" colspan="2">
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" disabled="true" value="<%=RegistrationConfig.EPISODE_ISOPEN_FALSE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="yes"/>
						&nbsp;&nbsp;
						<html:radio name="OpdNextVisitDetailFB" property="episodeIsOpen" disabled="true" value="<%=RegistrationConfig.EPISODE_ISOPEN_TRUE%>" onchange="setEpisodeCloseData();"></html:radio><bean:message key="no"/>
					</td>
				</tr>
			</table>
			
			<div id="divNextDateModes" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle;">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><font color="#FF0000">*</font>&nbsp;<bean:message key="plannedVisit"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle;">
							<div align="left">
							<logic:notEqual name="OpdNextVisitDetailFB" property="loginSeatId" value="<%=entrySeatId%>">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" disabled="true" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SOS)] %>
									&nbsp;
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" disabled="true" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_DAYS)] %>
									&nbsp;
									
									<html:radio name="OpdNextVisitDetailFB" property="nextVisitCriteria" disabled="true" value="<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE%>" onclick="setNextVisitMode();"></html:radio>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR_VIEW[Integer.parseInt(OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_SCHEDULED_DATE)] %>
								</font></logic:notEqual>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDays" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
						<td width="75%" class="tdfont" style="vertical-align: middle;">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="OpdNextVisitDetailFB" property="nextVisitDuration"/>
									<logic:present name="OpdNextVisitDetailFB" property="nextVisitDurationCriteria">
									<bean:define id="idNextVisitDurCriteria" name="OpdNextVisitDetailFB" property="nextVisitDurationCriteria" type="java.lang.String"></bean:define>
									<%=OpdConfig.OPD_VISIT_SUMMARY_NEXT_VISIT_MODE_ARR[Integer.parseInt(idNextVisitDurCriteria)]%>
									</logic:present>
									
									<html:hidden name="OpdNextVisitDetailFB" property="nextVisitDuration" />
									<html:hidden name="OpdNextVisitDetailFB" property="nextVisitDurationCriteria" />
								</font>
							</div>				
						</td>
					</tr>
				</table>
			</div>

			<div id="divNextDateModeDate" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
						<td width="75%" class="tdfont" style="vertical-align: middle;">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="OpdNextVisitDetailFB" property="episodeNextVisitDate"/>
									<html:hidden name="OpdNextVisitDetailFB" property="episodeNextVisitDate"/>
								</font>
							</div>		
								
						</td>
					</tr>
				</table>
			</div>

			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" class="tdfonthead" style="vertical-align: middle;">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="progNote"/></b>
							</font>
						</div>
					</td>
					<td width="75%" class="tdfont" style="vertical-align: middle;">
						<!--<div align="left">
							<bean:write name="OpdNextVisitDetailFB" property="visitNotes"/>
							<html:hidden name="OpdNextVisitDetailFB" property="visitNotes"/>
						</div>-->
						<div align="left">
							<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="visitNotes" tabindex="1" disabled="true"
								onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</div>	
					</td>
				</tr>
			</table>
			<div id="divEpisodeSummary" style="display: none;">
				<table width="100%" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead" style="vertical-align: middle;">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="episode"/> <bean:message key="summary"/></b>
								</font>
							</div>
						</td>
						<td width="75%" class="tdfont" style="vertical-align: middle;">
							<div align="left">
								<html:textarea rows="3" cols="50" name="OpdNextVisitDetailFB" property="episodeSummary" tabindex="1" 
									onkeypress="return (validateTextArea(event,this,'500') && validateAlphaNumericOnly(event))"/>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</his:ContentTag>
		</logic:notEqual>		
		
	</logic:equal>
		
	</his:statusTransactionInProcess>
	
	<his:ButtonToolBarTag>
		<his:statusTransactionInProcess>
			<logic:equal name="OpdNextVisitDetailFB" property="isConfirmed" value="<%=RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED%>">
				<!--<logic:notPresent name="OpdNextVisitDetailFB" property="triageDuration" >-->
<%-- 				<img class='button'	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' title="Save" style="cursor: pointer" tabindex='1' onclick="submitFormOnValidate(validateSave(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateSave(),'SAVE');")> --%>
					<!-- Save Method Calling changed by Singaravelan on 23-Mar-2015 for Apt Tag Integration  -->
					<img class='button'	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' title="Save" style="cursor: pointer" tabindex='1' onclick="saveVisitSummarywithAppointment();" onkeypress="if(event.keyCode==13)saveVisitSummarywithAppointment();")>
				<!--</logic:notPresent>-->
			</logic:equal>
			<logic:equal name="OpdNextVisitDetailFB" property="isConfirmed" value="<%=RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED%>">
				<!--<logic:equal name="OpdNextVisitDetailFB" property="loginSeatId" value="<%=entrySeatId%>">-->
<%-- 					<img class='button'	src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' title="Modify" style="cursor: pointer" tabindex='1' onclick="submitFormOnValidate(validateSave(),'MODIFYSAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateSave(),'MODIFYSAVE');")> --%>
						 <!-- Save Method Calling changed by Singaravelan on 23-Mar-2015 for Apt Tag Integration  -->
						<img class='button'	src='<his:path src="../HIS/hisglobal/images/buttons/btn-mo.png"/>' title="Modify" style="cursor: pointer" tabindex='1' onclick="modifyVisitSummarywithAppointment();" onkeypress="if(event.keyCode==13)modifyVisitSummarywithAppointment();")>	
				<!--</logic:equal>-->
			</logic:equal>
			<logic:notPresent name="OpdNextVisitDetailFB" property="triageDuration" >
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' title="Clear" tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
			</logic:notPresent>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' title="Cancel" style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusTransactionInProcess>
		
		<his:statusUnsuccessfull>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' title="Cancel" style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
		</his:statusUnsuccessfull>
	</his:ButtonToolBarTag>


<html:hidden name="OpdNextVisitDetailFB" property="hmode" />
<html:hidden name="OpdNextVisitDetailFB" property="serialNo" />
<html:hidden name="OpdNextVisitDetailFB" property="episodeCode" />
<html:hidden name="OpdNextVisitDetailFB" property="episodeVisitNo" />
<html:hidden name="OpdNextVisitDetailFB" property="admissionNo" />
<html:hidden name="OpdNextVisitDetailFB" property="empNo" />
<html:hidden name="OpdNextVisitDetailFB" property="loginSeatId" />
<html:hidden name="OpdNextVisitDetailFB" property="departmentUnitCode" />
<html:hidden name="OpdNextVisitDetailFB" property="deskType" />
<html:hidden name="OpdNextVisitDetailFB" property="isConfirmed" />

<html:hidden name="OpdNextVisitDetailFB" property="isEpisodeAlreadyOpen" />
<html:hidden name="OpdNextVisitDetailFB" property="seatId" />
<html:hidden name="OpdNextVisitDetailFB" property="episodeTypeCode" />
<html:hidden name="OpdNextVisitDetailFB" property="episodeDate" />
<html:hidden name="OpdNextVisitDetailFB" property="isPatDead" />

<html:hidden name="OpdNextVisitDetailFB" property="currentPage"/>
<html:hidden name="OpdNextVisitDetailFB" property="episodeKeyword"/>
<html:hidden name="OpdNextVisitDetailFB" property="episodeKeywords"/>
<html:hidden name="OpdNextVisitDetailFB" property="episodeKeywordID"/>
<html:hidden name="OpdNextVisitDetailFB" property="visitReason"/>
<html:hidden name="OpdNextVisitDetailFB" property="episodeIsOpen"/>

<html:hidden name="OpdNextVisitDetailFB" property="isUnitAppointmentBased"/>
<html:hidden name="OpdNextVisitDetailFB" property="patNextAptNo"/>
<html:hidden name="OpdNextVisitDetailFB" property="patNextAptSlot"/>
<html:hidden name="OpdNextVisitDetailFB" property="patNextAptQueueNo"/>


<!--  Added by Singaravelan on 23-Mar-2015 for Apt Tag Integration -->
<input type="hidden" name="appointmentDate"/>
<input type="hidden" name="showAppointmentDateInsidePopup" value="0"/>
<input type="hidden" name="targetId" />
 </form>