<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/accountDtl.tld" prefix="acDtl"%>

<html>
<head>
<meta charset=utf-8>
<title>Admission Advice Offline-ADT</title>

<link href="../../ipd/css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">


<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/newMultiRow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/dropdown.js"></script>
<script language="JavaScript" src="../../hisglobal/js/common.js"></script>
<script language="JavaScript" src="../js/admAdviceWithoutDesk.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.js"></script>
<script language="Javascript" src="../../ipd/js/jquery.simplemodal.js"></script>
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
				url = "/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
			}
			if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
		 	{	
				hmode = "HOSITALPDIAGNOSIS";
				url = "/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
			}
			if(document.getElementsByName("strDiagnosisType")[1]!=undefined)
			{
				if(document.getElementsByName("strDiagnosisType")[1].checked && document.getElementsByName("strDiagnosisType")[1].value=="0")
			 	{	
					hmode = "ICDDIAGNOSIS"; 
					url = "/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
				}
				if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
			 	{	
					hmode = "HOSITALPDIAGNOSIS"; 
					url = "/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
				}
			}		
			//alert("url :"+url);
			ajaxFunction(url,"2");
		}
		else
		{	
			//	alert(parentObj.name+""+gblIndex);
				if(document.getElementById("dropdown1").innerHTML.length <=0)
				{					
					var input = document.getElementById(parentObj.name+""+gblIndex).value;
					document.getElementById(parentObj.name+""+gblIndex).value = input.substring(0,input.length-1);
 					return false;					
				}		 	
		searchSel(eve,parentObj.name+""+vindex,'1',parentObj);		
	}	
}
function invokeOnLoad()
{
		document.forms[0].setAttribute( "autocomplete", "off" );
		try
		{
		var mode=document.forms[0].strMode.value;
		if(mode==1)
		{
			document.getElementById('diseaseTypeLabel').className="CONTROL";
			document.getElementById('diseaseTypeWithoutLabel').className="CONTROL";
			//document.getElementById('icdDiagnosisId').style.display="block";
			//document.getElementsByName("strDiagnosisType")[1].checked=true;
		}
		else
		{
			document.getElementById('diseaseTypeValuesId').style.display="block";
			document.getElementById('diseaseTypeId').style.display="block";
			//document.getElementById('icdDiagnosisId').style.display="block";
			//document.getElementById('hospitalDiagnosisId').style.display="block";
		}
		
	    view();
	    
		}
		catch(e)
		{}
} 
function secondSubmit()
{
	
  
		if(document.forms[0].strDepartmentValue.length>1)
		document.forms[0].strDepartmentValue.value="${advanceAdviceTransBean.strDepartmentValue}";
		if(document.forms[0].strDepartmentValue.value.length==3)
		{
			getOfflineUnit();
			//showPatientDetails('pDtlsDivId');
			//showEpisodeDetails('episodeDtlDiv');
		}
}
function view()
{
		if(document.forms[0].strCrNo.value!="")
		{
			if(checkCrdef(document.getElementById("strCrNoId"))==false)//Complete CR No Entered
			{
				document.getElementById("patientDemographicID").style.display="block";
				//document.getElementById("patientEpisodeID").style.display="block";
				document.getElementById("prevoiusDiagId").style.display="block";
				document.getElementById("patientCrEdId").style.display="none";
				document.getElementById("admissionAdviceId").style.display="block";
				document.getElementById("patientCrId").style.display="block";
				document.getElementById("patientCrId").innerHTML=document.getElementsByName("strCrNo")[0].value;
				document.getElementById("remarksId").style.display="block";
				document.getElementById("mandCRId").style.display="none";
				document.getElementById("checkid").style.display="block";
				document.getElementById("billdet").style.display="block";
				getWard();
			}
			else//only predefined Digits in CR Field
			{
			
				document.getElementById("patientCrId").style.display="none";
				document.getElementById("patientDemographicID").style.display="none";
				//document.getElementById("patientEpisodeID").style.display="none";
				document.getElementById("prevoiusDiagId").style.display="none";
				document.getElementById("admissionAdviceId").style.display="none";
				document.getElementById("remarksId").style.display="none";
				document.getElementById("mandCRId").style.display="";
				document.getElementById("patientCrEdId").style.display="block";
				//document.getElementsByName("strCrNo")[0].focus();
				document.getElementById("normalMsg").style.display="none";
				//SetCursorToTextEnd('strCrNoId');
				SetSelectedCrNo();
				
				
			//alert('1');
			}
		}
		else
		{
			//SetCursorToTextEnd('strCrNoId');
			document.getElementById("patientCrId").style.display="none";
			document.getElementById("patientDemographicID").style.display="none";
			//document.getElementById("patientEpisodeID").style.display="none";
			document.getElementById("prevoiusDiagId").style.display="none";
			document.getElementById("admissionAdviceId").style.display="none";
			document.getElementById("remarksId").style.display="none";
			document.getElementById("mandCRId").style.display="";
			document.getElementById("patientCrEdId").style.display="block";
			//document.getElementsByName("strCrNo")[0].focus();
		}	
}

function newRow()
{
	if(document.getElementById("dropdown1").style.display=="block")
	alert("Please Enter Data in Current Row");
	else
	{
		document.getElementById("diagLabel").style.display="";
		addRows(new Array('strProvisionDiagnosis','strIcdCode','strICD10CodeHidden','strDiagCodeType'),new Array('t','t','t','t'),'1','1','R','R');
	}
}
	
var checkWhetherFunctionNotCalled=true;	
function getAjaxResponse(res,mode)
{
		
		if(mode == '1')
		{
			var objEle = document.getElementById("diagnosisCombo");
			objEle.innerHTML = "<select name='strProvisionDiagnosis' id='strProvisionDiagnosis#delIndex#' >"+res+"</select>";
			addRows(new Array('strIcdCode','strProvisionDiagnosis'),new Array('t','s'),'1','1','R');
			getWard();
		}
		else if(mode == '2')
		{		  		
				document.getElementById("dropdown1").innerHTML = res;
				//document.getElementById("dropdown1").style.display="";
			 	searchSelWithCode(tempCode, gblParentObj.name+""+gblIndex ,'1',gblParentObj);
			//getWard();
		}
		else if(mode=='3')
		{
			try
			{  
				var objWard=document.getElementById("wardCombo");
				objWard.innerHTML="<select class='comboNormal' name='strWard' onChange='checkDuplicate();'>"+res+"</select>";
			}
			catch(e)
			{}
		}
		else if(mode=='4')
		{
			var a=parseInt(res);
			if(a>=1)
			{
				alert('Advice already raised');
				//var b=confirm("All the asscociated details corresponding to prevoius advice will be inactive,are you sure?");
				var b=confirm("All the asscociated details corresponding to previous advice will be inactive,are you sure?");
				if(b==true)
				{
					document.forms[0].flag.value='1';
				}
				else
				{
					document.forms[0].flag.value='0';
				}
			}
			else
			{
				document.forms[0].flag.value='1';
			}
			
			if(document.forms[0].strWard.value!=0)
			{
				getAdvanceAmountOnCatChange();
			}
			else
			{
				document.getElementsByName("strAdvanceAmount")[0].value="0";
			}
			
		}
		else if(mode=='5')
		{
		    
			var objWard=document.getElementById("offlineUnitId");
			objWard.innerHTML="<select class='comboNormal' onchange='getSubmitPage();getWard();' name='strUnitValue'>"+res+"</select>";
			
		 	if("${advanceAdviceTransBean.strUnitValue}" != "0")
			 	{
		 			//document.forms[0].strUnitValue.selectedIndex ="0";
		 			document.forms[0].strUnitValue.value="${advanceAdviceTransBean.strUnitValue}";
			 	}
				  

				
				
			document.forms[0].strAdvanceAmount.value="${advanceAdviceTransBean.strAdvanceAmount}";
			getWard();
		}
		else if(mode=='6')
		{
		    document.forms[0].strAdvanceAmount.value=res;
		    document.forms[0].advamt.value=res;
		    getPackageAmount();
		    if(document.forms[0].strPackageApply.value!="" && document.forms[0].strPackageApply.value!="0") 
			    alert("Applied Package has been reset. You need to re-apply");
		}
		else if(mode=='7')
		{
			var packapp=document.getElementById("pack");
			packapp.style="display:block";
			packapp.innerHTML="<select name='strPackageApply' class='comboNormal' onChange='setAdvAmt();' >"+res+"</select>";
			//setAdvAmt();
		}
}

function getCombo()
{
   if(document.getElementsByName("strIssueEstCertificate")[0].checked == true)
   {
      	document.getElementById("td1").style.display = "block";
      	document.getElementById("td2").style.display = "block";
   }
   else
   {
   		document.getElementById("td1").style.display = "none";
   		document.getElementById("td2").style.display = "none";
   }
}

function validate1()
{
	if(document.getElementById("dropdown1").style.display=="block")
	{
		alert("Please Enter Data in Diagnosis & Close the List Opened");
		return false;
	}
	/*if(document.getElementsByName("strIssueEstCertificate")[0].checked == true)
	{
	    if(document.getElementsByName("strTariffNameValues")[0].value == "0")
	    {
	    alert("Please Select Estimation Name");
	    return false;
	    }
	}*/
	var diag = document.getElementsByName("strProvisionDiagnosis");
	var hiddendiag = document.getElementsByName("strICD10CodeHidden");
	for(var nTmpI =0 ; nTmpI<diag.length-1; nTmpI++)
	{
		if(diag[nTmpI].value!=hiddendiag[nTmpI].value)
		{
			alert("Please Select Proper ICD10 Code");
			return false;
		}
		if(document.getElementsByName("strDiagTypeCode")[nTmpI].value=='0')
		{
			alert("Please Select Diagnostic Type");
			document.getElementsByName("strDiagTypeCode")[nTmpI].focus();
			return false;
		}
	}
	if(document.forms[0].strPackageApply.value!="0")
	{
		/*var temp=document.forms[0].strPackageApply.value.split("^");
		if(document.forms[0].strAdvanceAmount.value<temp[1] && temp[1]!="")
		{
			alert("Advance Amount cannot be less than applied package OT amount");
			return false;
		}*/
		if(document.forms[0].strpack.value!="0^0")
		{
			alert("A Package has already been availed by patient. Another one is not allowed");
			return false;
		}
	}
	var retVal = false;
	var hisValidator = new HISValidator(document.forms[0].name);
	hisValidator.addValidation("strCrNo", "req","CR No is a mandatory Field.");
	if(document.forms[0].strAccountNo.value=="0" || document.forms[0].strAccountNo.value=="")
	{
		hisValidator.addValidation("strAdvanceAmount", "req","Advance Amount is a mandatory Field.");
    }
	hisValidator.addValidation("strDepartmentValue", "dontselect=0","Department is mandatory Field.");
	hisValidator.addValidation("strUnitValue", "dontselect=0" , "Unit is mandatory Field.");
	hisValidator.addValidation("strPropAdmissionDate", "req","Prop Adminssion Date is a Mandatory Field");
	hisValidator.addValidation("strPropAdmissionDate", "date","Prop Adminssion Date should be a valid Date");
	hisValidator.addValidation("strPropAdmissionDate", "dtgtet=${advanceAdviceTransBean.strCtDate}" , "Prop Admission Date should be Greater than or Equal to Current Date");

	if(document.getElementById("strPlannedSurgeryDate").value.trim().length > 0)
	{
	hisValidator.addValidation("strPlannedSurgeryDate", "dtgtet=${advanceAdviceTransBean.strCtDate}" , "Proposed Surgery Date should be Greater than or Equal to Current Date");
	//return false;
	}
	
	/*if(document.forms[0].strLenStay.value != ""){
		hisValidator.addValidation("strLenStay", "numgt=0","Length of Stay must be Grater Than 0");
		hisValidator.addValidation("strLenStay", "numltet=120","Length of Stay must be Less Than or Equal to 120");
	
	}*/
	hisValidator.addValidation("strTreatment", "dontselect=0","Please Select a treatment category");		
	hisValidator.addValidation("strWard", "dontselect=0","Please Select a Ward");
	
	hisValidator.addValidation("strRoomType", "numgt=0","Click Bed Status to Select a Room Type");
	hisValidator.addValidation("strBedType", "numgt=0","Click Bed Status to Select a Bed Type");
			
	//hisValidator.addValidation("strWaitPeriod", "req","Wait Period is a Mandatory Field");
	hisValidator.addValidation("strWaitPeriod", "numgt=0","Wait Period must be Grater Than 0");
	hisValidator.addValidation("strWaitPeriod", "numltet=100","Wait Period must be Less Than or Equal to 100");
	retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
	if(retVal)
	{
		retVal=checkDuplicateDiagnosis();
		if(retVal)
			retVal=checkMultirow('strProvisionDiagnosis', 'You have Selected Duplicate Data in Provisional Diagnosis Combo');
	}
	if(retVal)
	{
			hisValidator.addValidation("strConsultantName", "dontselect=0","Please Select a Consultant");
			if(document.forms[0].strRemarks.value != "")
			{
				hisValidator.addValidation("strRemarks", "maxlen=50","Remarks Cannot be More than 50 Characters");
			}
			retVal = hisValidator.validate(); 
			hisValidator.clearAllValidations()
		}
		if(retVal)
		{
			    if(!document.forms[0].strAccountNo.value=="0" || !document.forms[0].strAccountNo.value=="")
				{
				    alert('Advance Already Collected For This Patient. Billing Request For Advance Will Not Be Raised');
				    document.getElementsByName("strAdvanceAmount")[0].value="0";				    
				}
				/*if(parseInt(document.forms[0].strLenStay.value) >= 30 )
				{				
						if(!confirm("Length of Stay is More than 30 Days, Are you Sure?"))
						{
							document.forms[0].strLenStay.focus();
							return false;
						}
				}*/
				if(document.forms[0].flag.value==1)
				{
					if(document.forms[0].strTreatment.value == "0")
						document.forms[0].strTreatment.value=document.forms[0].strCategoryCode.value;					
					document.forms[0].hmode.value = "INSERT";
					document.forms[0].submit();
				}
				else
				{
					alert('Admission Advice Can Not Be generated,Previous Advice Found...');
					return false;
				}
		}
		else
		{
			return false;
		}
}

</script>
</head>
<body onload="window.history.forward();secondSubmit();setcrNoselection();" onUnload="closePopUp();">


<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form action="/transactions/AdmissionAdviceTransCNT" method ="post">


<div class="errMsg" id="errMsg"><bean:write name="advanceAdviceTransBean" property="strMsgString"/></div>
<div class="warningMsg" id="warningMsg"><bean:write name="advanceAdviceTransBean" property="strWarning"/></div>
<div class="normalMsg" id="normalMsg"><bean:write name="advanceAdviceTransBean" property="strMsgString" /></div>
	<tag:tab tabLabel="Admission Advice" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
		
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
  <tr class="HEADER"> 
    <td colspan="4">Admission Advice</td>
  </tr>
  </table>
  
  <logic:notEqual value="1" property="strFlagForSave" name="advanceAdviceTransBean" >
  <table class="TABLEWIDTH" align="center"  cellspacing="1px" cellpadding="0px">
  <tr> 
    <td class="LABEL" width="20%"><font color="red" id="mandCRId">*</font>CR No.</td>
    <td  class="CONTROL" width="30%">
   		<div id="patientCrEdId" style="display: block;">
		<crNo:crNo id="strCrNoId" value="${advanceAdviceTransBean.patCrNo}" js=" onkeypress='return goRetFunc(event);return validateData(event,5);'"></crNo:crNo>
		
				<img src="../../hisglobal/images/Go.png"
				onClick="return goFunc();" align="top" style="cursor: pointer;" > 
			</div>
			<div id="patientCrId" style="display: none;"></div>
    </td>   
    <td class="LABEL" width="25%"><div align="right">Date</div></td>
    <td class="CONTROL" width="25%">
    	<font color="blue"><bean:write name="advanceAdviceTransBean" property="strCtDate"/></font>
    </td>
  </tr>
  </table>
  </logic:notEqual>
  
   <div id="patientDemographicID" style="display: none">
	
  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
  <tr class="HEADER"> 
    <td colspan="4" width="100%">
    	<img name="minus" src="/HBIMS/hisglobal/images/minus.gif" onClick="hidePatientDetails('pDtlsDivId'); " 
       style="cursor: pointer;display:none;" id="minusId">
       <img name="plus" src="/HBIMS/hisglobal/images/plus.gif"    
       onClick="showPatientDetails('pDtlsDivId');" style="cursor: pointer;" id="plusId">
    	&nbsp;&nbsp;<a style="cursor: pointer; color: " 
    	title="Patient Demographic Details" onclick="showPatientDetails('pDtlsDivId');">
    	Patient Name&nbsp;:&nbsp;<bean:write name="advanceAdviceTransBean" property="strPatName"/></a></td> 
  </tr>
  </table>
  </div>
   
   	<div id="pDtlsDivId" style="display:none;">
		 <table class="TABLEWIDTH" align="center" cellspacing="1px">
			<pDtl:patDtl crNo="${advanceAdviceTransBean.patCrNo}" address="false"></pDtl:patDtl>
		</table> 
		
	</div>
   
  
   
   <div id="admissionAdviceId" style="display: none">
   <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
     <tr> 
    <td colspan="4" class="TITLE">Admission Advice</td>   
  </tr>
  <tr>
    <td width="25%" class="LABEL"><div align="right"><font color="red">*</font>Department</div></td>
    <td width="25%" class="CONTROL">
    <html:select name="advanceAdviceTransBean" property="strDepartmentValue" onchange="getOfflineUnit()" styleClass="comboNormal">
    <bean:write name="advanceAdviceTransBean" property="strDeptComboValues" filter="false"></bean:write>
    </html:select>
    </td>
    <td width="25%" class="LABEL"><div align="right"><font color="red">*</font>Unit</div></td>
    <td width="25%" class="CONTROL"><div id="offlineUnitId">
    <select class='comboNormal' name='strUnitValue'><option value="0">Select Value</option></select></div></td>   
   </tr>
   <tr>
   <td width="25%" class="LABEL"><div align="right">Episode No.</div></td>
    <td width="25%" class="CONTROL">
    <logic:notEqual value="0" name="advanceAdviceTransBean" property="epFlag">
    <bean:write name="advanceAdviceTransBean" property="strEpisodeNumber"/>
    </logic:notEqual></td> 
    <td  class="LABEL" width="25%"><div align="right">Treatment Category</div></td>
    <td  class="CONTROL" width="25%">
    <select name="strTreatment" class="comboNormal" onchange="getAdvanceAmountOnCatChange();">
    <bean:write name="advanceAdviceTransBean" property="strTreatmentCategoryValues" filter="false"/></select></td>
  </tr>
   <tr>  	         
    <td  class="LABEL" width="25%" id='diseaseTypeLabel'>
    <div align="right" id='diseaseTypeId' style="display: none">Disease Type</div>
     </td>
    <td  class="CONTROL" width="25%" id='diseaseTypeWithoutLabel'>
    <div id='diseaseTypeValuesId' style='display: none'>
    <select name="strDiseaseType"  onchange="getWard();">
    			<bean:write name="advanceAdviceTransBean" property="strDiseaseTypeValues" filter="false"/>
    	</select></div>
    </td>
  </tr>
  </table>
  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px" >
  <tr>
  <td  class="LABEL" width='25%'><div align="right"><font color="red">*</font>Ward</div></td>
    <td  class="CONTROL" width='25%'>
    	<div id="wardCombo">
    		<select class="comboNormal" name="strWard" onChange="initBedAndRoom(),checkDuplicate();">
    			<option value="0">Select Value</option>
    		</select>
    	</div>
    </td>
    <td  class="LABEL" width="25%"><div align="right">Bed Status</div></td>
    <td  class="multiControl" width="25%">     
    <img src="/HBIMS/hisglobal/images/Bed_.gif"  style="cursor:pointer;width: 25px;" onClick="return showBedStatus();" 
    align="middle" onmouseover="" title="Click Here To Check Bed Status"/>
    </td>
  </tr>
  </table>
  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
  <tr>
    <td width="25%" class="LABEL"><div align="right">Room/Bed Type</div></td>
    <td width="25%" class="CONTROL">
    	<div id="roomBedId"></div>
    </td>
   	 <td  class="LABEL" width="25%"><div align="right">Admission Advice List</div></td>
    <td  class="multiControl" width="25%">
       <img src="/HBIMS/hisglobal/images/Check_List.png"  style="cursor:pointer;" 
       onClick="return showList();" align="middle" onmouseover="" style="cursor: pointer;" 
       title="Click Here TO View List Of Patient"/>
    </td>
  </tr>
  <tr>
    <!--  <td width="25%" class="LABEL"><div align="right">Admission Priority </div></td>
    <td width="25%" class="CONTROL">
    <html:radio name="advanceAdviceTransBean" property="strAdmissionPriority" value="0" >Normal</html:radio>
    <html:radio name="advanceAdviceTransBean" property="strAdmissionPriority" value="1">Urgent</html:radio>
    <input type="hidden" name="strLenStay" maxlength="3" onkeypress="return validateData(event,5);">
    </td>-->
    <td width="25%" class="LABEL"><div align="right"><font color="red">*</font>Advance Amount</div></td>
    <td width="25%" class="CONTROL">
    <input type="text" name="strAdvanceAmount" value="${advanceAdviceTransBean.strAdvanceAmount}" onkeypress="return validateData(event,7);" />
      <!--<bean:write name="advanceAdviceTransBean" property="strAdvanceAmount" filter="false"/>-->
    </td>
    <td width="25%" class="LABEL"><div align="right"><font color="red">*</font>Proposed Admission Date</div></td>
    <td width="25%" class="CONTROL">
    <date:date name="strPropAdmissionDate" value="${advanceAdviceTransBean.strCtDate}"></date:date></td>
    
    <!-- <td width="25%" class="LABEL" colspan="1"><div align="center">Length of Stay (in Days)</div></td>
    <td width="25%" class="CONTROL" colspan="3"></td>-->     
  </tr>
  
  <!-- Commented  By vikrant after discussion with amit kumar ateria sir.. -->
  <!--  <tr>
    <td width="25%" class="LABEL"><div align="right">Whether Issue Estimation Certificate:</div></td>
    <td width="25%" class="CONTROL">
    <input type="checkbox" name="strIssueEstCertificate" onclick="getCombo();"/>
    </td>
    <td width="25%" class="LABEL" ><div align="right" style="display: none;" id="td1">Whether Issue Estimation Certificate</div></td>
    <td width="25%" class="CONTROL" ><div  style="display: none;" id="td2"> 
    <select name="strTariffNameValues" class="comboNormal"  >
	<bean:write name="advanceAdviceTransBean" property="strTariffNameValues" filter="false"/>
	</select>
	</div>
	</td>

  </tr>   -->
  
  </table>
  </div>
  
  	<div id="billdet" style="display:none">
  	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
  <tr>
    <td colspan="4" class="TITLE" width="100%"><img name="minus"  src="/HBIMS/hisglobal/images/minus.gif" 
  	onClick="view2('BillPlusId','BillMinusId','BillDetailsId'); " 
       style="cursor: pointer;display:none;" id="BillMinusId">
       <img name="plus" src="/HBIMS/hisglobal/images/plus.gif" 
       onClick="view1('BillPlusId','BillMinusId','BillDetailsId');" 
       style="cursor: pointer;display:" id="BillPlusId">&nbsp;&nbsp;
       <a style="cursor: pointer; color: " title="Show Billing Details" 
    		onclick="view1('BillPlusId','BillMinusId','BillDetailsId');">
    		Account Details</a></td>
  </tr>
  </table>
 		  <div id="BillDetailsId" style="display: none">
	 		   <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
		 		   <tr>   	   
			    	   <td width="12%" class="LABEL"><div align="right">Account No.</div></td>
			    	   <td width="12%" class="CONTROL">
			    	   		<bean:write name="advanceAdviceTransBean" property="strAccountNo" filter="false"/>
					   </td>
			   		   <td width="12%" class="LABEL"><div align="right">Deposited Amount</div></td>
			    	   <td width="12%" class="CONTROL">
			    	   		<bean:write name="advanceAdviceTransBean" property="strAdvanceAmtpaid" filter="false"/>
			    	   </td>
			    	   <td width="12%" class="LABEL"><div align="right">Advance Deposit Date</div></td>
			    	   <td width="12%" class="CONTROL">
			    	   		<bean:write name="advanceAdviceTransBean" property="strAdvDepDate" filter="false"/>
			    	   </td>
			    	   <td width="12%" class="LABEL"><div align="right">Account Type</div></td>
			    	   <td width="12%" class="CONTROL">
			    	   		<bean:write name="advanceAdviceTransBean" property="strAccType" filter="false"/>
			    	   </td>
		    	   </tr>
	    	   </table>
 	   	   </div>
  
  </div>
   <div id="checkid" style="display: none">
  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
  <tr>
    <td colspan="4" class="TITLE" width="100%"><img name="minus"  src="/HBIMS/hisglobal/images/minus.gif" 
       onClick="view2('DiagPlusId','DiagMinusId','DiagDetailsId'); " 
       style="cursor: pointer;display:none;" id="DiagMinusId">
       <img name="plus" src="/HBIMS/hisglobal/images/plus.gif" 
       onClick="view1('DiagPlusId','DiagMinusId','DiagDetailsId');" 
       style="cursor: pointer;display:" id="DiagPlusId">&nbsp;&nbsp;
       <a style="cursor: pointer; color: " title="Show Previous Diagnosis" 
    		onclick="view1('DiagPlusId','DiagMinusId','DiagDetailsId');">
    		Admission Type Details</a></td>
  </tr>
  </table>
 		  <div id="DiagDetailsId" style="display: none">
 		   <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
 		   <tr>
           <td width="25%" class="LABEL"><div align="right">Planned Surgery Date:</div></td>
    	   <td width="25%" class="CONTROL">
    	   <date:date name="strPlannedSurgeryDate" value=""></date:date>
    	   </td>
    	   
    	   <td width="25%" class="LABEL"><div align="right">Admission Type</div></td>
    	   <td width="25%" class="CONTROL"><div id="offlineUnitId">
	    	   <select name="strAdmissionTypeValues" class="comboNormal"  >
			   	<bean:write name="advanceAdviceTransBean" property="strAdmissionTypeValues" filter="false"/>
			   </select>
		   </div>
		   </td>
   		   </tr>   		   
   		   <tr>
   		   <td width="25%" class="LABEL"><div align="right">Approximate Length Of Stay</div></td>
    	   <td width="25%" class="CONTROL">
    	   	<input type="text" name="strMaxLenStay" maxlength="2" onkeypress="return validateData(event,5);">
    	   </td>
    	   <td width="25%" class="LABEL">Apply OT Package</td>
    	   <td width="25%" class="CONTROL"><div id="pack" style="display:none">
	    	   <select name="strPackageApply" class="comboNormal" onChange="setAdvAmt();">
			   	<bean:write name="advanceAdviceTransBean" property="strPackageComboValues" filter="false"/>
			   </select></div>
    	   </td>
    	   </tr>    	    
    	   </table>
 	   	   </div>
 </div>
  
  <div id="prevoiusDiagId" style="display: none">
  
  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
  <tr>
    <td colspan="4" class="TITLE" width="100%"><img name="minus"  src="/HBIMS/hisglobal/images/minus.gif" 
       onClick="view2('prevoiusDiagPlusId','prevoiusDiagMinusId','prevoiusDiagDetailsId'); " 
       style="cursor: pointer;display:none;" id="prevoiusDiagMinusId">
       <img name="plus" src="/HBIMS/hisglobal/images/plus.gif" 
       onClick="view1('prevoiusDiagPlusId','prevoiusDiagMinusId','prevoiusDiagDetailsId');" 
       style="cursor: pointer;display:" id="prevoiusDiagPlusId">&nbsp;&nbsp;
       <a style="cursor: pointer; color: " title="Show Previous Diagnosis" 
    		onclick="view1('prevoiusDiagPlusId','prevoiusDiagMinusId','prevoiusDiagDetailsId');">
    		Previous Diagnosis Details</a></td>
  </tr>
  </table>
  
 <div id="prevoiusDiagDetailsId" style="display: none">
 <bean:write name="advanceAdviceTransBean" property="strPreviousDiagDtl" filter="false"/></div>
	<table  class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
	<bean:write name="advanceAdviceTransBean" property="strProvisionDiagnosisValues" filter="false"/>
	<tr>
	<logic:equal name="advanceAdviceTransBean" property="strDiagType" value="0">
	<td colspan="2" class="TITLE" width="50%">
	  <div id='hospitalDiagnosisId'>
	  <html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="1" 
	  onclick="">&nbsp;&nbsp;Hospital Diagnosis </html:radio>&nbsp;&nbsp;</div>	  
	 </td>
	 <td colspan="1" class="TITLE" width="48%">
    	 <div id='icdDiagnosisId'>
    	 <html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="0" 
    	 onclick="">&nbsp;&nbsp;ICD10 Diagnosis</html:radio></div>
    </td>
    <td colspan="1" class="TITLE" width="2%">	 
    	 <img src="/HBIMS/hisglobal/images/plus.gif"  onclick="newRow();" style="cursor: pointer;">
	</td>
	</logic:equal>
	<logic:notEqual name="advanceAdviceTransBean" property="strDiagType" value="0">
	<logic:equal name="advanceAdviceTransBean" property="strDiagType" value="2">
	<td colspan="1" class="TITLE" width="98%">
	  <div id='hospitalDiagnosisId'>
	  <html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="1" 
	  onclick="changeMultiRows();">&nbsp;&nbsp;Hospital Diagnosis </html:radio>&nbsp;&nbsp;</div>
	  </td>
	  <td colspan="1" class="TITLE" width="2%">
	  <img src="/HBIMS/hisglobal/images/plus.gif" onclick="newRow();" style="cursor: pointer;">
	 </td>
	 </logic:equal>
	 <logic:equal name="advanceAdviceTransBean" property="strDiagType" value="1">
	 <td colspan="1" class="TITLE" width="98%">
    	 <div id='icdDiagnosisId'>
    	 <html:radio name="advanceAdviceTransBean" property="strDiagnosisType" value="0" 
    	 onclick="changeMultiRows();">&nbsp;&nbsp;ICD10 Diagnosis</html:radio></div>
    </td>
    <td colspan="1" class="TITLE" width="2%">
    	 <img src="/HBIMS/hisglobal/images/plus.gif"  onclick="newRow();" style="cursor: pointer;">
	</td>
	</logic:equal>
	</logic:notEqual>	
	</tr>
	</table>
	<div id="diagLabel" style="display: none">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
	<tr>
		<td class="TITLE" width="15%"><div align="center">Code</div></td>
		<td class="TITLE" width="67%" align="left">
		<div align="center">Diagnosis Name</div></td>
		<td class="TITLE" width="16%" align="center">
		<div align="center">Type</div></td>
		<td class="TITLE" width="2%" align="right"><div align="center"></div></td>		
	</tr>
	</table>
	</div>
	<table id="icd10Id"  class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
		<tr></tr>
		</table>
		<div id='id1'></div>		
	</div>	 
<div id="remarksId" style="display: none">
<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td colspan="2" width="25%" class="LABEL">
			<div align="center">Remarks</div>
			</td>
			<td colspan="2" class="CONTROL"><textarea rows="2" cols="20" name="strRemarks"></textarea>			
		</tr>
		<tr>
			<td colspan="2" width="50%" class="LABEL">
			<div align="center"><font color="red">*</font>Advice By</div>
			</td>
			<td colspan="2" width="50%" class="multiControl"><div align="left">
			<select class="comboNormal" name="strConsultantName">
				<bean:write name="advanceAdviceTransBean" property="strConsultantNameValues" filter="false" />
			</select></div></td>
		</tr>
</table>
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
<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font>Mandatory Fields</td>
		</tr>
		<!--  <tr id="buttonId" align="center">-->
				
		<!-- </tr>-->
	</table>
	<div id="buttonId" align="center">
	<logic:notEmpty name="advanceAdviceTransBean" property="patCrNo">
				<a href="#" class="button" id="" onclick='return validate1();'><span class="save">Save</span></a>
			<!-- 		<img src="/HBIMS/hisglobal/images/btn-sv.png" onClick="return validate1();" style="cursor: pointer;">-->
				</logic:notEmpty>
				<!-- <img src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="cancelFunc();" style="cursor: pointer;"> -->
			
			
			<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
</div>
	<input type="hidden" name="hmode" value="">
	<input type="hidden" name="strUnitCode" value="${advanceAdviceTransBean.strUnitCode}">
	<input type="hidden" name="strAccountNo" value="${advanceAdviceTransBean.strAccountNo}">
	<input type="hidden" name="strTreatmentCategory" value="${advanceAdviceTransBean.strTreatmentCategory}">
	<input type="hidden" name="strDepartment" value="${advanceAdviceTransBean.strDepartment}">	
	<input type="hidden" name="strEpisodeNumber" value="${advanceAdviceTransBean.strEpisodeNumber}">
	<input type="hidden" name="strVisitValue" value="${advanceAdviceTransBean.strVisitValue}">
	<input type="hidden" name="strSex" value="${advanceAdviceTransBean.strSex}">
	<input type="hidden" name="strAge" value="${advanceAdviceTransBean.strAge}">
	<input type="hidden" name="strPrimaryCategory" value="${advanceAdviceTransBean.strPrimaryCategory}">
	<input type="hidden" name="strSecondaryCategory" value="${advanceAdviceTransBean.strSecondaryCategory}">
	<input type="hidden" name="strRoomType" value="">
	<input type="hidden" name="strBedType" value="">
	<input type="hidden" name="patCrNo" value="${advanceAdviceTransBean.patCrNo}">
	<input type="hidden" name="flag" value="">
	<input type="hidden" name="strMode" value="${advanceAdviceTransBean.strMode}">
	<input type="hidden" name="strPatName" value="${advanceAdviceTransBean.strPatName}">
	<input type="hidden" name="advamt" value="">
	</html:form>

<jsp:include page="admissionadvice_dropdown_ipdtranswithoutDesk.jsp"></jsp:include>
<jsp:include page="admissionadvice_multirow_ipdtranswithoutDesk.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
<script type="text/javascript">
invokeOnLoad();
</script>
</body>
</html>