<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/admissionDtl.tld" prefix="aDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld"  prefix="cmbPers"%>
<html>
<head><meta charset="utf-8" />
<title>Patient Final Discharge</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">

<style type='text/css'>
.dragme {
	cursor: move;
	color: #FFFFFF;
	background-color: #005680;
}
</style>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../js/patientDischarge.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="JavaScript" src="../../hisglobal/js/newMultiRow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/dropdown.js"></script>
<script language="JavaScript" src="../../hisglobal/js/common.js"></script>


<script type="text/javascript">


var keyupTimer;
var searchMode1;
var parentObj1
var eve1;
var vindex1;
function getIcdCodeValues1(searchMode , parentObj, eve , vindex)
{
   clearTimeout(keyupTimer);
   searchMode1=searchMode;
   parentObj1=parentObj;
   eve1=eve;
   vindex1=vindex;   
   // will activate when the user has stopped typing for 1 second
   keyupTimer = setTimeout("getIcdCodeValues2()",500); 
} 
function getIcdCodeValues2()
{
   var temp=vindex1.split("-")[1]-1;
   var tempId="strIcdCode1-"+temp;
   if(vindex1.split("-")[1]-(temp+1)=='0')
   {
   		getIcdCodeValues(searchMode1 , parentObj1, eve1 , vindex1);
   }
   else
   {
	   if(document.getElementById(tempId).value=="")
	   alert("Please Enter Data in Previous Row");
	   else
	   {
	   		getIcdCodeValues(searchMode1 , parentObj1, eve1 , vindex1);
	   }
   }
}
var tempCode = "";
var gblParentObj = "";
var gblIndex = "";
var child = null;
var popIndex = 0;
var gblCntrlObj = null;

function getIcdCodeValues(searchMode , parentObj , eve , vindex)
{
	gblParentObj = parentObj;
	gblIndex = vindex;
	var key;
	if(window.event)
		key = window.event.keyCode;
	else
	{
		if (eve)
			key = eve.which;
	}
	tempCode = key;//single quotes
 		if(tempCode == 222)
 		{
 			parentObj.value = parentObj.value.substring(0,parentObj.value.length-1);
 			return false;
 		}
		var url="";
		var hmode="";
		var searchContent = parentObj.value;
		if(parentObj.value.length>1)
		{
 			searchContent = parentObj.value.substring(0,1);
 		}
		if(searchContent.length == 1)
		{
		 	if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="0")
		 	{
				hmode = "ICDDIAGNOSIS"; 
				url = "/AHIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
			}
			if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
		 	{
				hmode = "HOSITALPDIAGNOSIS";
				url = "/AHIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
			}
			if(document.getElementsByName("strDiagnosisType")[1]!=undefined)
			{
				if(document.getElementsByName("strDiagnosisType")[1].checked && document.getElementsByName("strDiagnosisType")[1].value=="0")
			 	{
					hmode = "ICDDIAGNOSIS"; 
					url = "/AHIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
				}
				if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
			 	{
					hmode = "HOSITALPDIAGNOSIS"; 
					url = "/AHIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
				}
			}
			//alert("url :"+url);
			ajaxFunction(url,"20");
		}
		else
		{
				if(document.getElementById("dropdown1").innerHTML.length <=0)
				{
					var input = document.getElementById(parentObj.name+""+gblIndex).value;
					document.getElementById(parentObj.name+""+gblIndex).value = input.substring(0,input.length-1);
 					return false;					
				}
		searchSel(eve,parentObj.name+""+vindex,'1',parentObj);
	}
}


function getIndex(_these){
	var nTmpI;
	var strSelectedObjName=document.getElementsByName(_these.name)
	for(nTmpI=0;nTmpI<strSelectedObjName.length;nTmpI++)
		if(_these==strSelectedObjName[nTmpI])
			break;
	return nTmpI;
}

function cancelToDesk(){
	document.forms[0].hmode.value = "CANCELTODESK";
 	document.forms[0].submit();
}

/*
function copyCode(these){
	document.getElementsByName("strIcdCode")[getIndex(these)].value=these.value;
}*/
/*
var gblParentObj = "";
var tempCode = "";
var gblIndex = "";
	 
function getIcdCodeValues(searchMode , parentObj , eve , vindex)
{
	gblParentObj = parentObj;
	gblIndex = vindex;	
	var key;
	if(window.event)
		key = window.event.keyCode;
	else
	{
		if (eve)
		key = eve.which;
	}		
	tempCode = key;
 	if(tempCode == 222)
 	{
 			parentObj.value = parentObj.value.substring(0,parentObj.value.length-1);
 			return false;
	}
	var searchContent = parentObj.value;
	if(searchContent.length == 1 )
	{
		if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="0")
		{	
				hmode = "ICDDIAGNOSIS"; 
				url = "/AHIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
		}
		if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
		{	
				hmode = "FINALPDIAGNOSIS";
				url = "/AHIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
		}
		if(document.getElementsByName("strDiagnosisType")[1]!=undefined)
		{
				if(document.getElementsByName("strDiagnosisType")[1].checked && document.getElementsByName("strDiagnosisType")[1].value=="0")
			 	{	
					hmode = "ICDDIAGNOSIS"; 
					url = "/AHIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
				}
				if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
			 	{	
					hmode = "FINALPDIAGNOSIS"; 
					url = "/AHIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
				}
		}
		
		
		//var hmode = "ICDDIAGNOSIS"; 
		//var url = "/AHIMS/ipd/transactions/PatientFinalDischargeCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
		//alert("url :"+url);
		ajaxFunction(url,"20");
	}
	else
	{
		if(document.getElementById("dropdown1").innerHTML.length <=0)
		{
					var input = document.getElementById(parentObj.name+""+gblIndex).value;
					document.getElementById(parentObj.name+""+gblIndex).value = input.substring(0,input.length-1);
 					return false;
		}
		searchSel(eve,parentObj.name+""+vindex,'1',parentObj);		
	} 		
}*/
function setIcdCodes(userValue , resultValue)
	{
		var resVals = resultValue.split('^');		
		document.getElementById("strProvisionDiagnosis"+userValue).value = resVals[0];
		document.getElementById("strIcdCode"+userValue).value = resVals[1];
		document.getElementById("strICD10CodeHidden"+userValue).value = resVals[0];
		//ICD
		if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="0")
		{
			document.getElementById("strDiagCodeType"+userValue).value = "0";
		}
		//Hospital
		if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
		{
			document.getElementById("strDiagCodeType"+userValue).value = "1";
		}
		//Both
		if(document.getElementsByName("strDiagnosisType")[1]!=undefined)
		{
			//ICD
			if(document.getElementsByName("strDiagnosisType")[1].checked && document.getElementsByName("strDiagnosisType")[1].value=="0")
		 	{
		 		document.getElementById("strDiagCodeType"+userValue).value = "0";	
			}
			//Hospital
			if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
		 	{
		 		document.getElementById("strDiagCodeType"+userValue).value = "1";
			}
		}
		//defined in multirow jsp
		document.getElementById("tariffFullNameDiv").style.display="none";
		
	}

function getDischargeData() // fro Setting Data on Dischrge type "strTransferUnit" 
{
	getDisBy();
	document.forms[0].strCurrentDeptUnitRoom.value=document.forms[0].curDept_Unt_RomCode.value;
	if(	document.getElementsByName("strTransferUnit")[0].value=="13")		// Dicharge Death - 13
	{
		document.getElementById("deathDetails").style.display="block";
		document.getElementById("dischargeAdvBy").innerHTML = "<font color='red'>*</font>Death Verified By";
	}
	else
	{
	document.getElementById("deathDetails").style.display="none";
	document.getElementById("dischargeAdvBy").innerHTML = "<font color='red'>*</font>Discharge Advised By";
	}
	if(	document.getElementsByName("strTransferUnit")[0].value=="1"		// Dicharge - 1 
		|| document.getElementsByName("strTransferUnit")[0].value=="5"	// Discharge On Request-5
		|| document.getElementsByName("strTransferUnit")[0].value=="7")	// Referred - 7
	{
		document.getElementById("adviseByID").style.display="block";
 		document.getElementById("visitOPD").style.display="block";
 		document.getElementById("idDischargeType").style.display="none";
	}
	else	// Case of Other Discharge Types
	{
		var currDtUtWrRmBd = document.forms[0].curDtUtWrRmBd.value;
		var mode="transOf";
		var url="/AHIMS/ipd/transactions/PatientFinalDischargeDeskTransCNT.cnt?hmode="+mode+"&modName="+document.getElementsByName("strTransferUnit")[0].value+"&currDtl="+currDtUtWrRmBd;
		ajaxFunction(url,"119");
    }
}

function openDivScreen()
{
	
}
function setCode(objVal,index)
{
	if(objVal.value.toUpperCase()!=""){
		document.getElementById("strProvisionDiagnosis"+index).value=objVal.value.toUpperCase();
		
		if(document.getElementById("strProvisionDiagnosis"+index).value=="")
			document.getElementById("strProvisionDiagnosis"+index).value="0";
	}	
	else
	{
		document.getElementById("strProvisionDiagnosis"+index).value="0";
	}
}



function getAjaxResponse(res,mode)
{
	    if(mode == '9')
	    {
		     var objEle =  document.getElementById("infoMLC");
		     var objEle1 =  document.getElementById("visitOPD");
		     var myRes=new Array();
		     myRes=res.split("^");
		     if(myRes[0]!="")
		     	document.getElementById("infoMLC").style.display="block";
		     objEle.innerHTML  = myRes[0];
		     objEle1.innerHTML = myRes[1];
		}
		else
		{
			if(res.split("####")[0]=="Error")
			{
				document.getElementById("errMsg").innerHTML=res.split("####")[1];
			}
			else if(mode == '1')
			{
				var objEle = document.getElementById("diagnosisCombo");
				objEle.innerHTML = "<font color='red'>*</font><select class='comboBig' name='strProvisionDiagnosis' id='strProvisionDiagnosis#delIndex#' >"+res+"</select>";
				addRows(new Array('strProvisionDiagnosis'),new Array('s'),'1','1','I');
				tmp();
			}
			else if(mode == '2')
			{
				var objEle = document.getElementById("diagnosisCombo");
				objEle.innerHTML = "<font color='red'>*</font><select class='comboBig' name='strProvisionDiagnosis' onchange='copyCode(this)' id='strProvisionDiagnosis#delIndex#'>"+res+"</select>";
				addRows(new Array('strProvisionDiagnosis'),new Array('s'),'1','1','I');
				document.getElementById("dropdown1").innerHTML = res;
				//document.getElementById("dropdown1").style.display="";				 
				 searchSelWithCode(tempCode, gblParentObj.name+""+gblIndex ,'1',gblParentObj);	
				 //document.getElementById(gblParentObj.name+""+gblIndex).focus();
				//tmp();	
			}
			
			else if(mode=='3')
			{
				var objEle =  document.getElementById("dischargeParam");
				if(res.trim()!="")
				{
					objEle.innerHTML = res;
					showDetails3();
				}
				document.getElementsByName("strTmpFlag")[0].value="1";
			}
			else if(mode == '500')
			{
	   			var val = res.split("###");    	
    			if(val[0] == "ERROR")
    			{
	    			document.getElementById("errMsg").innerHTML = val[1];
    			}
	 		}
		}
		if(mode == '20')
		{
				alert(res);
				document.getElementById("dropdown1").innerHTML = res;
			 	searchSelWithCode(tempCode, gblParentObj.name+""+gblIndex ,'1',gblParentObj);	
		}
		if(mode == '4') 
		{
			document.getElementsByName("strICD10CodeValues")[0].value=res.split("~^~")[0];
			try
			{
				document.getElementsByName("strHospICDCodeValues")[0].value=res.split("~^~")[1];
			}
			catch(_err){
			}
	 		try
	 		{
				arrValues=res.split("#")[0].split("^");
			}
			catch(_err){
			}
			try
			{
				arrValues1=res.split("#")[1].split("^");
			}
			catch(_err)
			{
			}
			try
			{
				createArray('icd10Id');
			}
			catch(_err){
			}
 		}
		if(mode=='119')
		{
			if(res.trim()=="")
			{
				document.getElementById("adviseByID").style.display="block";
				document.getElementById("visitOPD").style.display="block";
				document.getElementById("idDischargeType").style.display="none";
			}
			else
			{
				var temp=res.split('^');
				var objVal = document.getElementById("idDischargeType");
				objVal.innerHTML = temp[0];
				document.getElementById("idDischargeType").style.display="block";
				if(temp[1]==0)
				{
					document.getElementById("adviseByID").style.display="none";
					document.getElementById("visitOPD").style.display="none";
				}
			}
		}
		if(mode=='10')
		{
			//alert("res"+res);
			document.getElementById("idDisBy").innerHTML = "<select class='comboMax' title='Doctors List' name='strRmk'>"+res+"</select>";
			if(typeof(document.forms[0].strConsultantId)=="undefined")
				document.forms[0].strRmk.value=0;
			else
				document.forms[0].strRmk.value=document.forms[0].strConsultantId.value;			
		}
}
function autotab(original,destination)
{
		if (original.getAttribute&&original.value.length==original.getAttribute("maxlength"))
		destination.focus()
}


/*
function createArray(_tableId){
	var array1 = new Array('<font size="2" title=\'Enter ICD10 Code\' color="red">*</font><input type=text tabIndex=1 name=strProvisionDiagnosis >(ICD10 Code) <font size="2" color="red">*</font><input tabIndex=1 type=text  name=strIcdCode id=\'#strIcdCode#index#\' style="width: 300px"  ><INPUT TYPE=HIDDEN NAME=\'strICD10CodeHidden\'>');
	var array = array1;
	multiRow(_tableId,array, 1, 1, true);
	var objICD10Text = document.getElementsByName('strIcdCode');
	var strProvisionDiagnosis = document.getElementsByName('strProvisionDiagnosis');
	
	if(document.getElementsByName('strDiagnosisType')[1].checked==true){
		var obj1 = new actb(objICD10Text[objICD10Text.length-1],document.getElementsByName("strICD10CodeValues")[0].value.split("#")[0].split("^"));
		obj1.actb_userdefine_func = "copyCode";
		var obj2 = new actb(strProvisionDiagnosis[strProvisionDiagnosis.length-1],document.getElementsByName("strICD10CodeValues")[0].value.split("#")[1].split("^"));
		obj2.actb_userdefine_func = "copyText";
	} else {
		var obj1 = new actb(objICD10Text[objICD10Text.length-1],document.getElementsByName("strHospICDCodeValues")[0].value.split("#")[0].split("^"));
		obj1.actb_userdefine_func = "copyCode";
		var obj2 = new actb(strProvisionDiagnosis[strProvisionDiagnosis.length-1],document.getElementsByName("strHospICDCodeValues")[0].value.split("#")[1].split("^"));
		obj2.actb_userdefine_func = "copyText";
	}
}*/
</script>


</head>
<body onLoad="onLoadHideMLC(),butdis1(),hlpOnLoadDisParam(),printReport();">
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form action="/transactions/PatientFinalDischargeDeskTransCNT.cnt"
	method="post">
	<div id="menu1"></div>
	<div class="errMsg" id="errMsg"><bean:write
		name="patientDischargeBean" property="strErrMsgString" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="patientDischargeBean" property="strNormalMsgString" /></div>
	<tag:tab tabLabel="Patient Final Discharge" selectedTab="FIRST" onlyTabIndexing="1"
		align="center" width="TABLEWIDTH">
	</tag:tab>
	<div id="patTldglbdiv">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr class="HEADER">
			<td colspan="4">Patient Final Discharge</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px' style="display: none;">
		<tr>
			<td width="25%" class="LABEL" nowrap="nowrap"><font color="red" id="mandCRId">*</font>CR No.</td>
			<td width="75%" class="CONTROL"><crNo:crNo id="strCrNoId"
				value="${patientDischargeBean.strCrNo}"
				js=" onblur='Trim(this)' ;onkeyup='lTrim(this)'; onkeypress='return isEnter(event)';"></crNo:crNo> <img
				style="cursor: pointer; cursor: hand;"
				src="../../hisglobal/images/viewDetails.gif"
				title="Click here for Patient Search" align="middle"
				name='searchPatient'
				onclick="showPatientListingWindow('5',document.forms[0].strCrNo,'setSelectedCrNo');" />
			<img src="../../hisglobal/images/Go.png" align="top"
				onclick="return goFunc1('patientDischargeBean');"
				style="cursor: pointer;"></td>
		</tr>
		
	</table>
	</div>
	<div id="patDemDtlId" style="display: none">
	<!-- <table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width='100%'  class="TITLE" align='center' colspan="2">
			<div id="plusonLineId" style="display: none"><img
				style="cursor: pointer;" src="../../hisglobal/images/plus.gif"
				name="plusonLine" onclick="showDetails();" />&nbsp;&nbsp;Patient Demographic Details</div>
			<div id="minusonLineId"><img style="cursor: pointer;"
				src="../../hisglobal/images/minus.gif" name="minusonLine"
				onclick="hideDetails();">&nbsp;&nbsp;Patient Demographic Details</div>
			</td>
		</tr>	
	</table> -->
	</div>	
	<div id="patDtlHeader" style="display: none">
    </div>
	<div id="patDtlTld" style="display: block;">		
	<pDtl:patDtl crNo="${patientDischargeBean.strCrNo}" address="true"></pDtl:patDtl>
	</div>
	<div id="admDtlTldglbdiv" style="display: none">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width='100%' class='TITLE' align='center'>
			<div id="plusonLineId1" style="display: none"><img
				style="cursor: pointer;" src="../../hisglobal/images/plus.gif"
				name="plusonLine1" onclick="showDetails1();">&nbsp;&nbsp;Admission Details</div>
			<div id="minusonLineId1"><img style="cursor: pointer;"
				src="../../hisglobal/images/minus.gif" name="minusonLine1"
				onclick="hideDetails1();">&nbsp;&nbsp;Admission Details</div>
			</td>
		</tr>
	</table>
	</div>
	<div id="admDtlTld" style="display: none">
	<aDtl:addDtl crNo="${patientDischargeBean.strCrNo}"></aDtl:addDtl>
		</div>



	<div id="dischargeOnlineId">
	<div id="finalDischParam" style="display: none">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='1px'>
		<tr>
			<td width='100%' class='TITLE' align='center'>
			<div id="plusonLineId3"><img style="cursor: pointer;"
				src="../../hisglobal/images/plus.gif" name="plusonLine3"
				onclick="getPatDischargeParameterDetail();">&nbsp;&nbsp;Discharge Parameter</div>
			<div id="minusonLineId3" style="display: none"><img
				style="cursor: pointer;" src="../../hisglobal/images/minus.gif"
				name="minusonLine3" onclick="hideDetails3();">&nbsp;&nbsp;Discharge Parameter</div>
			</td>
		</tr>
	</table>
	</div>
	<div id="dischargeParam"><bean:write name="patientDischargeBean"
		property="strPatientDisParam" filter="false" /></div>


	<div id="finalDiag" style="display: none;">
	<table class="TABLEWIDTH" align="center" cellspacing="0px">
		<tr>
			<td width="5%" class="TITLE"><img
				src="../../hisglobal/images/plus.gif" id="plusImg"
				onclick="showDetails4()" style="cursor: pointer; display: none" />
			<img src="../../hisglobal/images/minus.gif" id="minusImg"
				onclick="hideDetails4()" style="cursor: pointer;" /></td>
			<td class="TITLE" width="90%">
			<div>Final Diagnosis</div>
			</td>
			<td width="5%" class="TITLE"></td>
		</tr>

	</table>
	</div>
	
	<div id="finalDetail">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<bean:write name="patientDischargeBean"
			property="strProvisionDiagnosisValues" filter="false" />
		<tr>
		<logic:equal name="patientDischargeBean" property="strDiagType" value="0">
		<td colspan="2" class="TITLE" width="50%">
		  <div id='HospitalDiagnosisId'>
		  <html:radio name="patientDischargeBean" property="strDiagnosisType" value="1" 
		  onclick="">Hospital Diagnosis </html:radio>&nbsp;&nbsp;</div>
		</td>
	 	<td colspan="1" class="TITLE" width="48%">
    	 <div id='icdDiagnosisId'>
    	 <html:radio name="patientDischargeBean" property="strDiagnosisType" value="0" 
    	 onclick="">ICD10 Diagnosis</html:radio></div></td>
		<td colspan="1" class="TITLE" width="2%"><img src="../../hisglobal/images/plus.gif"
				onclick="addRows(new Array('strProvisionDiagnosis','strIcdCode','strICD10CodeHidden'),new Array('t','t','t'),'1','1','R');"
				style="cursor: pointer;" /></td>
		</logic:equal>
		<logic:notEqual name="patientDischargeBean" property="strDiagType" value="0">
		<logic:equal name="patientDischargeBean" property="strDiagType" value="2">
		<td colspan="1" class="TITLE" width="98%">
	  	<div id='hospitalDiagnosisId'>
	  	<html:radio name="patientDischargeBean" property="strDiagnosisType" value="1" 
	  		onclick="changeMultiRows();">Hospital Diagnosis </html:radio>&nbsp;&nbsp;</div></td>
		 <td colspan="1" class="TITLE" width="2%"><img src="../../hisglobal/images/plus.gif"
				onclick="addRows(new Array('strProvisionDiagnosis','strIcdCode','strICD10CodeHidden'),new Array('t','t','t'),'1','1','R');"
				style="cursor: pointer;" /></td>
	 	</logic:equal>
		<logic:equal name="patientDischargeBean" property="strDiagType" value="1">
		<td colspan="1" class="TITLE" width="98%">
    	 <div id='icdDiagnosisId'>
    	 <html:radio name="patientDischargeBean" property="strDiagnosisType" value="0" 
    	 onclick="changeMultiRows();">ICD10 Diagnosis</html:radio></div></td>
		<td colspan="1" class="TITLE" width="2%"><img src="../../hisglobal/images/plus.gif"
			onclick="addRows(new Array('strProvisionDiagnosis','strIcdCode','strICD10CodeHidden'),new Array('t','t','t'),'1','1','R');"
			style="cursor: pointer;" /></td>
		</logic:equal>
		</logic:notEqual>
		</tr>
	</table>

	<table id="icd10Id"  class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
		<tr></tr>
		</table>
	</div>
	<div id='id1'></div>
</div>
	<div id="transChng" style="display: none">
	<table class="TABLEWIDTH" align="center" border='0' cellspacing='0px'>
		<tr>
			<td width='5%' class='TITLE' align='center'>
			<div id="plusonLineId2" style="display: none"><img
				style="cursor: pointer;" src="../../hisglobal/images/plus.gif"
				name="plusonLine2" onclick="showDetails2();"></div>
			<div id="minusonLineId2"><img style="cursor: pointer;"
				src="../../hisglobal/images/minus.gif" name="minusonLine2"
				onclick="hideDetails2();"></div>
			</td>
			<td width="45%" class="TITLE">Discharge Details</td>
			<td width="30%" class="TITLERight"><div id="disTypeId">Discharge Type</div></td>
			<td width="20%" class="TITLE"><div id="disTypeId1"><select
				name="strTransferUnit" class="comboNormal" title="Nature of Discharge" onChange="setDisType();">
				<bean:write name="patientDischargeBean"
					property="strPatientDischargeTypeComboValues" filter="false" />
			</select></div><div id="disTypeOnlineId"></div></td>
		</tr>
	</table>
	</div>
	<div id='idDischargeType'></div>
	<div id="infoMLC" style="display: block"><bean:write
		name="patientDischargeBean" property="strHlpMLC" filter="false" /></div>

	<div id="disBnR" style="display: none">
	<div style="display: none;" id="id2"></div>
	<bean:write name="patientDischargeBean" property="strDeathDetails"
		filter="false" />



	<div id="online" style="display: none;">
	<table class="TABLEWIDTH" border="0" align="center" border='0'
		cellspacing='1px'>
		<tr>
			<td width='25%' class='LABEL'>Discharge Advised By</td>
			<td width="25%" class="CONTROL"><bean:write name="patientDischargeBean" property="strAdvisedBy"></bean:write></td>
			<td width='25%' class='LABEL'>Discharge Advised Date</td>
			<td width="25%" class="CONTROL"><bean:write name="patientDischargeBean" property="strAdviceDate"></bean:write></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">
			<div align="right">Discharge Advice</div>
			</td>
			<td width="75%" colspan='3' class="CONTROL">
			<bean:write name="patientDischargeBean" property="strRemarksOnline" filter="false"></bean:write></td>
		</tr>
	</table>
	</div>
	<div id="offline">
		<div id='adviseByID'>
			<table class="TABLEWIDTH" border="0" align="center" border='0' cellspacing='1px'>
				<tr>
					<td width='25%' class='LABEL'>
						<div id="dischargeAdvBy" align="right"><font color='red'>*</font>Discharge Advised By</div>
					</td>
					<td width="75%" class="CONTROL"><div id="idDisBy">
						<select class="comboMax" title="Employee Code and List of Doctors" name="strRmk">
							<bean:write name="patientDischargeBean" property="strRmk" filter="false" />
						</select></div>
					</td>
				</tr>
				<tr id="dischargeAdvice">
					<td width="25%" class="LABEL">
						<div align="right"><font color="red">*</font>Discharge Advice</div>
					</td>
					<td width="75%" class="CONTROL">
						<textarea name="strRsn" cols="20" ></textarea>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
<div id="tariffNamePartDivId">
	<table class="TABLEWIDTH" align="center" cellpadding="0px" cellspacing="0px">
		<tr>
			<td width="15%" class="CONTROL" style="color: blue; font-weight: bold">
			<div id="tariffFullNameDiv"></div>
			</td>
		</tr>
	</table>
	</div>

	<div id="visitOPD" style="display: block"><bean:write
		name="patientDischargeBean" property="strHlpOPD" filter="false" /></div>
	</div>
	<table class="TABLEWIDTH" border="0" align="center" cellspacing='1px'>
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	<table border='0' cellspacing='1px' class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
				<logic:notEmpty name="patientDischargeBean" property="strCrNo">
					<img src="../../hisglobal/images/btn-sv.png" onClick="startValidation1();" style="cursor: pointer;">
				</logic:notEmpty>
				<img src="../../hisglobal/images/btn-ccl.png" onClick="cancelToDesk();" style="cursor: pointer;">
			</td>
		</tr>
	</table>
	<input type="hidden" name="strDeathDateAndTime">
	<input type="hidden" name="strOnsetDeathDateAndTime">
	<input type="hidden" name="strVerifyDateAndTime">
	<input type="hidden" name="strHandoverDateAndTime">
	<input type="hidden" name="strShiftDateAndTime">

	<input type="hidden" name="strFOfflineDeath" value="false">
	<input type="hidden" name="strDeathFlag" >
	<input type="hidden" name="strTmpFlag">
	<input type="hidden" name="strTmpFlag1">
	<input type="hidden" name="hmode">
	<input type="hidden" name="strAbscondedValue"
		value="${patientDischargeBean.strAbscondedValue}" />
	<input type="hidden" name="strTempVal" value="">
	<input type="hidden" name="strErrMsgString"
		value="${patientDischargeBean.strErrMsgString}">
	<input type="hidden" name="strNormalMsgString"
		value="${patientDischargeBean.strNormalMsgString}">
	<input type="hidden" name="strPopUp_id">
	<input type="hidden" name="cnt" value="">
	<input type="hidden" name="strBId"
		value="${patientDischargeBean.strBId}">
	<input type="hidden" name="strDischrg_Mode"
		value="${patientDischargeBean.strDischrg_Mode}">
	<input type="hidden" name="strDischrg_Param_Req"
		value="${patientDischargeBean.strDischrg_Param_Req}">
	<input type="hidden" name="strDischargeDiagnosisRequired"
		value="${patientDischargeBean.strDischargeDiagnosisRequired}">
	<input type="hidden" name="strDischargeAdviceFieldRequired"
		value="${patientDischargeBean.strDischargeAdviceFieldRequired}">
	<input type="hidden" name="strDischargeSummaryPrintRequired"
		value="${patientDischargeBean.strDischargeSummaryPrintRequired}">
	<input type="hidden" name="strDisplay_MLC_Dtls"
		value="${patientDischargeBean.strDisplay_MLC_Dtls}">
	<input type="hidden" name="strDisplay_OPD_Visit_Dtls"
		value="${patientDischargeBean.strDisplay_OPD_Visit_Dtls}">
	<input type="hidden" name="strSaveStatus"
		value="${patientDischargeBean.strSaveStatus}" />
	<input type="hidden" name="strAdmissionNo"
		value="${patientDischargeBean.strAdmissionNo}" />
	<input type="hidden" name="strNormalMsgString"
		value="${patientDischargeBean.strNormalMsgString}" />
	<input type="hidden" name="strIsDead"
		value="${patientDischargeBean.strIsDead}" />
	<input type="hidden" name="strApplicationMode"
		value="${patientDischargeBean.strApplicationMode}" />
	<input type="hidden" name="strDischargeType" value="${patientDischargeBean.strDischargeType}">
	<input type="hidden" name="strAdmStatCode" value="${patientDischargeBean.strAdmStatCode}" />
	<input type="hidden" name="strICD10CodeValues">
	<input type="hidden" name="strHospICDCodeValues">
	<input type="hidden" name="strCurrentDeptUnitRoom">
<cmbPers:cmbPers />
</html:form>
<script>
function setDisType() // Called on Change of Discharge Type "strTransferUnit"
{
	var strAdmStatus = document.getElementsByName("strAdmStatCode")[0].value;
	if(strAdmStatus==11 || strAdmStatus==15 || strAdmStatus==17)	// Admission Status 11-On Transit 15-On Leave, 17-??-On Transit(outside)
		document.getElementsByName("strTransferUnit")[0].value=3;	// Setting Discharge Type-"3" Absconded
	getDischargeData();
}

if(document.getElementsByName("strCrNo")[0].value!="")
	setDisType();

if(document.getElementsByName("strDischargeType")[0].value==1){
	document.getElementById("offline").style.display="none";
	document.getElementById("online").style.display="block";
	
	document.getElementById("disTypeOnlineId").innerHTML="<font size='2' color='blue'>Discharge Type: "+"${patientDischargeBean.strDischargeTypeName}"+"</font>";
	document.getElementById("disTypeId").style.display="none";
	document.getElementById("disTypeId1").style.display="none";
	document.getElementById("dischargeOnlineId").style.display="none";
}else if(document.getElementsByName("strCrNo")[0].value.length<=11){
	document.getElementById("dischargeOnlineId").style.display="none";
}else{
	document.getElementById("dischargeOnlineId").style.display="block";
}
//if(document.getElementsByName("strCrNo")[0].value.length>=12 && document.getElementsByName("strDischargeType")[0].value==2)
//	getICDDetails();
</script>
<jsp:include page="admissionadvice_dropdown_ipdtranswithoutDesk.jsp"></jsp:include>
<jsp:include page="patFinalDischarge_multirow_ipdtranswithoutDesk.jsp"></jsp:include>

</body>
</html>