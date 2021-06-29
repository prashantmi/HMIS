<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page import="registration.config.RegistrationConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@page%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="./../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="./../../hisglobal/css/layout.css" rel="stylesheet"	type="text/css">
<link href="./../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">

<script language="JavaScript" type="text/javascript"
	src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>

<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>

<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>
<script type="text/javascript" src="../../hisglobal/js/barcode_code39.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script type="text/javascript" src="./../../registration/transactions/js/saveBarcodeSlipPrintEntryInHMIS.js" /></script> 

<title>Duplicate Booklet Issue</title>
<s:head />


</head>
<style type="text/css">

@media print { 
		#nonprintableDiv1 
		{
		 display: none; 
		}
		#nonprintableDiv2 
		{
		 display: none; 
		}
		#nonprintableDiv3 
		{
		 display: none; 
		}
		
}

.border .div-table-col{
border: 1px solid black;
}
</style>
<script type="text/javascript">

$(window).on("load.loading1", function() {
	
	

	
		var data=document.getElementsByName("printHtml")[0].value;
		var elem = document.getElementById("divPrintId");
		elem.innerHTML= data;
		
		
		
if(document.getElementsByName("afterGo")[0].value!='0')
{  
	document.getElementById("divAfterGo").style.display = "";
	document.getElementById("divAfterGoId").style.display ="";
	document.getElementById("divGoId").style.display = "none";

	dupCardGeneration.submitByChoice('6');	 //duplicate by default
}


var isPrintFlag  = document.getElementsByName("isPrintFlag")[0].value
var choice	     = document.getElementsByName("isChoiceBack")[0].value

if(isPrintFlag=="1"){
	/* if(choice!='1') 
	get_object("divBarCodeControl").innerHTML=DrawCode39Barcode(get_object("divBarCodeControl").innerHTML, 0);
	 window.print(); */ //commented as we are now printing brcd Slip in place of opd card in AIIMS Ptn 
	 manageBarcodePrintProcess();
	 document.getElementsByName("isPrintFlag")[0].value="0";
 }else{
	  $("#divPrintId").html("");
 }

});

function submitClear(cnt)
{
	document.forms[0].action =  cnt + ".action";
	document.forms[0].submit();
}

	function submitForm(mode) {
		document.forms[0].action = mode + "DuplicateCardGeneration.action";
		document.forms[0].submit();

	}
	function submitCancelAction(cnt) {
		document.forms[0].action = "cancel" + cnt + ".action";
		document.forms[0].submit();
	}
	function submitClearAction(cnt) {
		document.forms[0].action = "Clear" + cnt + ".action";
		document.forms[0].submit();
	}
	function submitFormonRadio(mode,obj) {
		document.forms[0].action = mode + "DuplicateCardGeneration.action?crno="+obj.value;
		document.forms[0].submit();

	}
	function populate(selectedarray)
	{
		var strHtml ="";
		var elem = document.getElementById("hiddenDivVerification");
		for(i=0; i<selectedarray.length; i++)
		{
			var arrayOfDocsData=selectedarray[i].split("|");
	  	  
			strHtml+="<input type='hidden' name='arrSelectedVerifyDocs' value='"+selectedarray[i]+"'/>	";
			strHtml+=arrayOfDocsData[1]+"&nbsp; &nbsp;";
		}
		elem.innerHTML= ":: &nbsp;" + strHtml;
		
	}  
	//By Mukund on 25.09.2017
	function manageBarcodePrintProcess()
	{
		saveEntryForBarcodePrinting();
	}
		
</script>
<body>
	<center>
		<s:form action="DuplicateCardGeneration">
<s:if test="%{!hasActionErrors()}">
			<%-- <s:set name="theme" value="%{myTheme}" scope="page" /> --%>
			<s:set name="theme" value="simple" scope="page" />
			<div class="wrapper rounded " id="nonprintableDiv1">

				<div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col title width100 ">
						<%-- <s:text name="duplicate"/>&nbsp;<s:text name="global.card"/>&nbsp;<s:text name="generation"/></div> --%>
						<s:text name="duplicate"/>&nbsp;<s:text name="Booklet"/>&nbsp;<s:text name="Issue"/></div>
					</div>
				</div>

	<his:InputCrNoTag />
		
	<div id="divAfterGo" style="display: none"> 
	<!--By Mukund on 22.12.2016  --><s:if test="%{afterGo!=0}">
	<%-- <s:include value="/registration/transactions/jsp/patientDetailsTile.jsp"/> --%>
	<his:PatientTileTag crNo="${patCrNo }" ></his:PatientTileTag>
				<div class="div-table">
				<div class="div-table-row separatorBar">
						<div class="div-table-col width100"></div>
					</div>
					<div class="div-table-row" style="display: none;">
						<div class="div-table-col control" style="width: 25%;">
							<s:text name="cardprint" />
						</div>
						<%-- <div class="div-table-col label" style="width: 75%;">
						<input type="radio" name="choice" tabindex="1" value="1"   checked="checked"/>
						<b>	
							<s:text name="duplicate"/>
						</b>
						<input type="radio" name="choice" tabindex="1" value="2" />
						<b>	
							<s:text name="reprintNew"/>
						</b>
						<input type="radio" name="choice" tabindex="1" value="5"  />
						<b>	
							<s:text name="BackPagePrint"/>
						</b>	 
						</div>	 --%>
						 <div class="div-table-col label" style="width: 75%;" id="_divDupPrintChoiceId" style="display: none">
						 <input type="radio" name="choice" tabindex="1" value="6" onclick="dupCardGeneration.submitByChoice(this.value)" checked="checked"/>
						<b>	
							<s:text name="printReport"/>
						</b>
						<input type="radio" name="choice" tabindex="1" value="1" onclick="dupCardGeneration.submitByChoice(this.value)" style="display: none" />
						<%-- <b>	
							<s:text name="duplicate"/>
						</b> --%>
						<input type="radio" name="choice" tabindex="1" value="2" onclick="dupCardGeneration.submitByChoice(this.value)" style="display: none"/>
						<%-- <b>	
							<s:text name="reprintNew"/>
						</b> --%>
						<input type="radio" name="choice" tabindex="1" value="5" onclick="dupCardGeneration.submitByChoice(this.value)" />
						<b>	
							<s:text name="BackPagePrint"/>
						</b>
							 
						</div>	
					</div>
					
				</div>
					
				
				<div class="div-table"  id="divEpisodeId">
				</div>
				
				</s:if>
				</div>
				
				
				
				
				
				
				<div class="div-table-button">
					<div class="div-table-row footerBar">
						<div class="div-table-col"></div>
					</div>
					<div class="div-table-row emptyBar">
						<div class="div-table-col"></div>
					</div>
					
					<div class="div-table-row" align="center">
					<div id="divAfterGoId" style="display:none">  
				 		<%-- <a href="#" class="button" id="submitId"><span class="save"><s:text name="save" /></span></a> --%>
				 		<a href="#" class="button" id="submitId"><span class="print">Print</span></a>
						<a class="button" id="clearId" onclick="submitClearAction('DuplicateCardGeneration');"><span class="clear"><s:text name="clear" /></span></a> 
						<a href="#" class="button" onclick="submitCancelAction('DuplicateCardGeneration');"><span class="cancel"><s:text name="cancel" /></span></a>
					</div> 
					 <div id="divGoId" >  
						<a id="#" class="button"	onclick="submitClear('DuplicateCardGeneration');"><span class="clear"><s:text name="clear" /></span></a> 
						<a href="#" class="button" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel" /></span></a>
					</div>
					</div>
				</div>





<s:hidden name="afterGo" value="%{tempAfterGo}" />
<s:hidden id="printHtmlId" name="printHtml" value="%{printHtmlTemp}"/>
<s:hidden name="isPrintFlag" value="%{isPrintFlag}" />
<s:hidden name="isChoiceBack" value="%{isChoiceBack}" />



</div>
<div class="div-table" id="nonprintableDiv3">
				<div class="div-table-row ">
					<div class="div-table-col"> 
						<div class="div-table-col alignLeft fontError" style="width: 100%"><s:property value="errorMessage" /></div>
					</div>
				</div>
	<div id="divPrintId"></div><!--This should be placed outside div nonPrintableDiv3  for OPD Card Print-->		
</div>
<!-- <div id="divPrintId"></div> -->
</s:if>
<s:else>
		<div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						Duplicate Booklet Issue
				</div>
			</div>
		</div>
		<div id='divErrorMsgId' class="div-table-col alignLeft fontError" style="width: 100%">
			<s:actionerror/>
		</div>
	</s:else>

<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token/>

		</s:form>
			
		<script type="text/javascript"
	src="./../../registration/transactions/js/duplicateCardGeneration.js" /></script>
	</center>
</body>
</html>